package com.flamyoad.loopingvideoplayer.di

import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MediaStorageCollection

@Module
@InstallIn(SingletonComponent::class)
object MediaStorageModule {

    @Provides
    @MediaStorageCollection
    fun provideCollectionUri(): Uri {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }
    }
}