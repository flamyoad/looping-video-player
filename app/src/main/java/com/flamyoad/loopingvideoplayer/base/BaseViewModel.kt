package com.flamyoad.loopingvideoplayer.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

open class BaseViewModel() : ViewModel() {

    protected fun <T> Flow<T>.toStateFlow(
        initialValue: T,
        sharingStarted: SharingStarted = SharingStarted.WhileSubscribed(),
    ) = stateIn(viewModelScope, sharingStarted, initialValue)
}