package com.flamyoad.loopingvideoplayer.ui.video_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.flamyoad.loopingvideoplayer.base.BaseViewModel
import com.flamyoad.loopingvideoplayer.di.DefaultDispatcher
import com.flamyoad.loopingvideoplayer.model.Video
import com.flamyoad.loopingvideoplayer.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val videoRepository: VideoRepository,
    private val savedState: SavedStateHandle,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    ) : BaseViewModel() {

    val videoList: LiveData<List<Video>> = videoRepository
        .getVideos(savedState.get<String>("bucketId")!!)
        .asLiveData(dispatcher + viewModelScope.coroutineContext)
}