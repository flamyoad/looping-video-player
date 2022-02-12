package com.flamyoad.loopingvideoplayer.core.persistence

import javax.inject.Inject


class PersistentManagerImpl @Inject constructor(
    private val persistentObjectRegistry: PersistentObjectRegistry
) : PersistentManager {

    override fun getSdCardLocation(): PersistentObject<String> {
        return persistentObjectRegistry.getString(SDCARD_LOCATION)
    }

    companion object {
        private const val SDCARD_LOCATION = "SDCARD_LOCATION"
    }
}