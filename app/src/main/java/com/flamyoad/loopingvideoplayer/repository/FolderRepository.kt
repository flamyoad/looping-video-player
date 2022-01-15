package com.flamyoad.loopingvideoplayer.repository

import com.flamyoad.loopingvideoplayer.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    suspend fun getFolders(): Flow<List<Folder>>
    suspend fun refresh()
}