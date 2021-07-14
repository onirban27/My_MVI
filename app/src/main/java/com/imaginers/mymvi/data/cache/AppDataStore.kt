package com.imaginers.mymvi.data.cache

import android.app.Application

// TODO: 7/14/2021 under work
object AppDataStore {

    private fun providesAuthDatastore(application: Application): LocalCache {
        return LocalCache(application)
    }

    operator fun invoke() {
        providesAuthDatastore(application = Application())
    }

}