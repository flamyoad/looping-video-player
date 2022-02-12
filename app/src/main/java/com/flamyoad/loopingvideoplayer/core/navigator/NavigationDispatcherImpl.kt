package com.flamyoad.loopingvideoplayer.core.navigator

import androidx.navigation.NavDirections
import com.flamyoad.loopingvideoplayer.model.Folder
import com.flamyoad.loopingvideoplayer.model.Video
import com.flamyoad.loopingvideoplayer.ui.folder_list.FolderListFragmentDirections
import com.flamyoad.loopingvideoplayer.ui.video_list.VideoListFragmentDirections
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject


@ActivityRetainedScoped
class NavigationDispatcherImpl @Inject constructor(
    private val navigationQueue: NavigationQueue
): NavigationDispatcher {

    override fun navigationQueue(): NavigationQueue {
        return navigationQueue
    }

    override fun toVideoList(folder: Folder) {
        FolderListFragmentDirections.actionFolderListFragmentToVideoListFragment(
            bucketId = folder.bucketId,
            folderName = folder.name,
        ).enqueueCommand()
    }

    override fun toVideoPlayer(video: Video) {
        VideoListFragmentDirections.actionVideoListFragmentToVideoPlayerFragment(
            videoUri = video.videoUri
        ).enqueueCommand()
    }

    private fun NavDirections.enqueueCommand() {
        navigationQueue.enqueue(this)
    }
}