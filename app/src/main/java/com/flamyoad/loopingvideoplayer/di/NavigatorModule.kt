package com.flamyoad.loopingvideoplayer.di

import com.flamyoad.loopingvideoplayer.core.navigator.NavigationDispatcher
import com.flamyoad.loopingvideoplayer.core.navigator.NavigationDispatcherImpl
import com.flamyoad.loopingvideoplayer.core.navigator.NavigationQueue
import com.flamyoad.loopingvideoplayer.core.navigator.NavigationQueueImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
interface NavigatorModule {

    @Binds
    fun navigationDispatcher(navigationDispatcherImpl: NavigationDispatcherImpl): NavigationDispatcher

    @Binds
    fun navigationQueue(navigationQueueImpl: NavigationQueueImpl): NavigationQueue
}