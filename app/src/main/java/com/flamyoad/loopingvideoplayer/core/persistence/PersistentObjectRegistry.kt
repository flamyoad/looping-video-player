package com.flamyoad.loopingvideoplayer.core.persistence


interface PersistentObjectRegistry {

    fun getString(key: String): PersistentObject<String>

    fun getInt(key: String): PersistentObject<Int>

    fun getDouble(key: String): PersistentObject<Double>

    fun getFloat(key: String): PersistentObject<Float>
}