package com.flamyoad.loopingvideoplayer.core.persistence

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking


interface PersistentObject<T> {

    fun get(): Flow<T>

    fun getBlocking(): T

    suspend fun set(newValue: T)

    fun setBlocking(newValue: T)

    suspend fun clearValue()
}

class PersistentObjectImpl<T>(
    private val key: Preferences.Key<T>,
    private val defaultValue: T,
    private val dataStore: DataStore<Preferences>
) : PersistentObject<T> {

    override fun get(): Flow<T> {
        return dataStore.data.map { prefs -> prefs[key] ?: defaultValue }
    }

    override fun getBlocking(): T {
        return runBlocking {
            get().firstOrNull() ?: defaultValue
        }
    }

    override suspend fun set(newValue: T) {
        dataStore.edit {
            it[key] = newValue
        }
    }

    override fun setBlocking(newValue: T) {
        runBlocking {
            set(newValue)
        }
    }

    override suspend fun clearValue() {
        dataStore.edit {
            if (it.contains(key)) {
                it.remove(key)
            }
        }
    }
}