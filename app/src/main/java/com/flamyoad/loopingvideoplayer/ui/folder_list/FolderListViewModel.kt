package com.flamyoad.loopingvideoplayer.ui.folder_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.flamyoad.loopingvideoplayer.base.BaseViewModel
import com.flamyoad.loopingvideoplayer.di.DefaultDispatcher
import com.flamyoad.loopingvideoplayer.model.Folder
import com.flamyoad.loopingvideoplayer.repository.FolderRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FolderListViewModel @Inject constructor(
    private val folderRepository: FolderRepository,
    private val savedState: SavedStateHandle,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    val folderList: LiveData<List<Folder>> = folderRepository.getFolders()
//        .catch {  }
        .asLiveData(dispatcher + viewModelScope.coroutineContext)
}