package com.flamyoad.loopingvideoplayer.repository

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.flamyoad.loopingvideoplayer.di.MediaStorageCollection
import com.flamyoad.loopingvideoplayer.model.Folder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @MediaStorageCollection private val collectionUri: Uri,
) : FolderRepository {

    override fun getFolders(): Flow<List<Folder>> = flow {
        // Seems adding DISTINCT does not work anymore in Android Q or above
        // https://stackoverflow.com/questions/58601599/illegalargumentexception-invalid-column-distinct-bucket-display-name
        val projection = arrayOf(
            MediaStore.Video.Media.BUCKET_ID,
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
            val bucketIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val bucketId = cursor.getStringOrNull(bucketIdColumn) ?: continue
                val name = cursor.getStringOrNull(nameColumn) ?: continue
                // This is because the cursor searches for video items which may share the same directory
                // So if a folder has been found once, skip it
                if (folderList.any { it.name == name }) {
                    continue
                }
                folderList += Folder(bucketId, name, 0)
                emit(folderList)
            }
        }
    }

    override fun refresh() {

    }
}