package com.flamyoad.loopingvideoplayer.repository

import com.flamyoad.loopingvideoplayer.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    fun getFolders(): Flow<List<Folder>>
    fun refresh()
}