package com.flamyoad.loopingvideoplayer.core.persistence


interface PersistentManager {
    fun getSdCardLocation(): PersistentObject<String>
}