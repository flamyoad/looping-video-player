package com.flamyoad.loopingvideoplayer.repository

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.flamyoad.loopingvideoplayer.di.MediaStorageCollection
import com.flamyoad.loopingvideoplayer.model.Folder
import com.flamyoad.loopingvideoplayer.model.Video
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoRepositoryImpl(
    @ApplicationContext private val context: Context,
    @MediaStorageCollection private val collectionUri: Uri,
) : VideoRepository {

    override fun getVideos(folderName: String): Flow<List<Video>> = flow {
        val projection = arrayOf(
            MediaStore.Video.VideoColumns._ID,  
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.VideoColumns.DURATION,
        )

        val sortOrder = MediaStore.Video.Media.DATE_TAKEN

        val query = context.contentResolver.query(
            collectionUri,
            projection,
            null,
            null,
            sortOrder,
        )

        val videoList = mutableListOf<Video>()
        query?.use { cursor ->
            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.ALBUM)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val name = cursor.getString(nameColumn) ?: ""
                // This is because the cursor searches for video items which may share the same directory
                // So if a folder has been found once, skip it
                if (videoList.any { it.name == name }) {
                    continue
                }
                videoList += Video(name, Uri.EMPTY)
                emit(videoList)
            }
        }
    }

    override fun refresh() {
    }
}