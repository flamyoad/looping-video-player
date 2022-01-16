package com.flamyoad.loopingvideoplayer.repository

import com.flamyoad.loopingvideoplayer.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideos(bucketId: String): Flow<List<Video>>
    fun refresh()
}