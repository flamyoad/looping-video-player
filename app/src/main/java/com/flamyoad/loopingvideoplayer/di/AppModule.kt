package com.flamyoad.loopingvideoplayer.di

import com.flamyoad.loopingvideoplayer.repository.FolderRepository
import com.flamyoad.loopingvideoplayer.repository.FolderRepositoryImpl
import com.flamyoad.loopingvideoplayer.repository.VideoRepository
import com.flamyoad.loopingvideoplayer.repository.VideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindFolderRepository(folderRepositoryImpl: FolderRepositoryImpl): FolderRepository

    @Binds
    fun bindVideoRepository(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository
}