package com.flamyoad.loopingvideoplayer.repository

import android.content.ContentUris
import android.content.Context
import android.database.DatabaseUtils
import android.net.Uri
import android.provider.MediaStore
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.flamyoad.loopingvideoplayer.di.MediaStorageCollection
import com.flamyoad.loopingvideoplayer.model.Video
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @MediaStorageCollection private val collectionUri: Uri,
) : VideoRepository {

    override fun getVideos(bucketId: String): Flow<List<Video>> = flow {
        val projection = arrayOf(
            MediaStore.Video.VideoColumns.BUCKET_ID,
            MediaStore.Video.VideoColumns._ID,
            MediaStore.Video.VideoColumns.DISPLAY_NAME,
            MediaStore.Video.VideoColumns.DURATION,
        )

        val selection = "${MediaStore.Video.VideoColumns.BUCKET_ID} LIKE ?"

        val selectionArgs = arrayOf(bucketId)

        val sortOrder = MediaStore.Video.Media.DATE_TAKEN

        val query = context.contentResolver.query(
            collectionUri,
            projection,
            selection,
            selectionArgs,
            sortOrder,
        )

        val videoList = mutableListOf<Video>()
        query?.use { cursor ->
            DatabaseUtils.dumpCursor(cursor)
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val name = cursor.getStringOrNull(nameColumn) ?: continue
                val id = cursor.getLongOrNull(idColumn) ?: continue
//                 This is because the cursor searches for video items which may share the same directory
//                 So if a folder has been found once, skip it
                if (videoList.any { it.name == name }) {
                    continue
                }
                videoList += Video(
                    name,
                    ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
                )
                emit(videoList)
            }
        }
    }

    override fun refresh() {
    }
}