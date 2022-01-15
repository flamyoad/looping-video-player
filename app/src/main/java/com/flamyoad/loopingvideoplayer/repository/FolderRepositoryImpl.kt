package com.flamyoad.loopingvideoplayer.repository

import android.content.Context
import android.os.Build
import android.provider.MediaStore
import com.flamyoad.loopingvideoplayer.di.DefaultDispatcher
import com.flamyoad.loopingvideoplayer.model.Folder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    @DefaultDispatcher val dispatcher: CoroutineDispatcher
) : FolderRepository {

    override suspend fun getFolders(): Flow<List<Folder>> {
        return flowOf(getFoldersFromMediaStorage())
    }

    override suspend fun refresh() {
    }

    private suspend fun getFoldersFromMediaStorage(): List<Folder> = withContext(dispatcher) {
        val collection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Video.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }

        val projection = arrayOf(
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        )

        val sortOrder = MediaStore.Video.Media.DATE_TAKEN

        val query = context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder,
        )

        val folderList = mutableListOf<Folder>()
        query?.use { cursor ->
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val name = cursor.getString(nameColumn)
                folderList += Folder(name, 0)
            }
        }

        return@withContext emptyList()
    }
}