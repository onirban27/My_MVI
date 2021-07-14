package com.imaginers.mymvi.data.cache

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.imaginers.mymvi.util.C.AUTH_DATASTORE_KEY
import com.imaginers.mymvi.util.C.AUTH_DATASTORE_NAME
import kotlinx.coroutines.flow.first

class LocalCache constructor(
    private val context: Context
    ){

    private val Context.dataStore by preferencesDataStore(name = AUTH_DATASTORE_NAME)
    private val authDataStoreKey = booleanPreferencesKey(AUTH_DATASTORE_KEY)

    suspend fun loginUser() {

        context.dataStore.edit { mutablePref->
            mutablePref[authDataStoreKey] = true
        }
    }


    suspend fun isUserLoggedIn(): Boolean {
        val loginStatus = context.dataStore.data.first()

        return loginStatus[authDataStoreKey]  ?: false //If null return false else return value in datastore
    }


    suspend fun logOutUser() {
        context.dataStore.edit { mutablePref ->
            mutablePref[authDataStoreKey] = false
        }
    }


}