package com.flamyoad.loopingvideoplayer.core.persistence

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import javax.inject.Inject


class PersistentObjectRegistryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PersistentObjectRegistry {

    override fun getString(key: String): PersistentObject<String> {
        return PersistentObjectImpl(
            key = stringPreferencesKey(key),
            defaultValue = "",
            dataStore = dataStore
        )
    }

    override fun getInt(key: String): PersistentObject<Int> {
        return PersistentObjectImpl(
            key = intPreferencesKey(key),
            defaultValue = 0,
            dataStore = dataStore
        )
    }

    override fun getDouble(key: String): PersistentObject<Double> {
        return PersistentObjectImpl(
            key = doublePreferencesKey(key),
            defaultValue = 0.0,
            dataStore = dataStore
        )
    }

    override fun getFloat(key: String): PersistentObject<Float> {
        return PersistentObjectImpl(
            key = floatPreferencesKey(key),
            defaultValue = 0.0f,
            dataStore = dataStore
        )
    }
}