package com.flamyoad.loopingvideoplayer.di

import com.flamyoad.loopingvideoplayer.repository.FolderRepository
import com.flamyoad.loopingvideoplayer.repository.FolderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindfolderRepository(folderRepositoryImpl: FolderRepositoryImpl): FolderRepository
}