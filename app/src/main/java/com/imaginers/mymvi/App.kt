package com.imaginers.mymvi

import android.app.Application
import android.content.Context
import com.imaginers.mymvi.data.db.AppDatabase
import com.imaginers.mymvi.data.repository.UserRepository

// TODO: 7/14/2021 Need to implement Hilt For DI

class App : Application() {

    private val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { UserRepository(database.userDao()) }

    companion object{
        lateinit var appContext: App
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}