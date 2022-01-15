package com.flamyoad.loopingvideoplayer.ui

import androidx.lifecycle.SavedStateHandle
import com.flamyoad.loopingvideoplayer.base.BaseViewModel
import com.flamyoad.loopingvideoplayer.repository.FolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val folderRepository: FolderRepository, )
    : BaseViewModel() {
//    val folderList = viewModelScope.launch {
//        folderRepository.getFolders().toStateFlow(emptyList())
//    }
}