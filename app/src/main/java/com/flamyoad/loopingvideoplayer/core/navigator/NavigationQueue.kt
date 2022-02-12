package com.flamyoad.loopingvideoplayer.core.navigator

import androidx.navigation.NavDirections
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject


interface NavigationQueue {

    fun get(): SharedFlow<NavDirections>

    fun enqueue(directions: NavDirections)
}

@ActivityRetainedScoped
class NavigationQueueImpl @Inject constructor(): NavigationQueue {

    private val queue = MutableSharedFlow<NavDirections>(extraBufferCapacity = 5)

    override fun get(): SharedFlow<NavDirections> {
        return queue.asSharedFlow()
    }

    override fun enqueue(directions: NavDirections) {
        queue.tryEmit(directions)
    }
}