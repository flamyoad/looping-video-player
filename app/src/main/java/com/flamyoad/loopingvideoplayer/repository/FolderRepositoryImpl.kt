package com.flamyoad.loopingvideoplayer.repository

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.flamyoad.loopingvideoplayer.di.DefaultDispatcher
import com.flamyoad.loopingvideoplayer.di.MediaStorageCollection
import com.flamyoad.loopingvideoplayer.model.Folder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @MediaStorageCollection private val collectionUri: Uri,
) : FolderRepository {

    override fun getFolders(): Flow<List<Folder>> = flow {
        val projection = arrayOf(
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        )

        val sortOrder = MediaStore.Video.Media.DATE_TAKEN

        val query = context.contentResolver.query(
            collectionUri,
            projection,
            null,
            null,
            sortOrder,
        )

        // Todo: Find mutableSortedSet and replace...
        val folderList = mutableListOf<Folder>()
        query?.use { cursor ->
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val name = cursor.getString(nameColumn) ?: ""
                // This is because the cursor searches for video items which may share the same directory
                // So if a folder has been found once, skip it
                if (folderList.any { it.name == name }) {
                    continue
                }
                folderList += Folder(name, 0)
                emit(folderList)
            }
        }
    }

    override fun refresh() {

    }
}