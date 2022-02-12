package com.flamyoad.loopingvideoplayer.core.navigator

import com.flamyoad.loopingvideoplayer.model.Folder
import com.flamyoad.loopingvideoplayer.model.Video


interface NavigationDispatcher {

    fun navigationQueue(): NavigationQueue

    fun toVideoList(folder: Folder)

    fun toVideoPlayer(video: Video)
}