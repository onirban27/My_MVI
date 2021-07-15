package com.imaginers.mymvi

import android.app.Application
import android.content.Context
import com.imaginers.mymvi.data.db.AppDatabase
import com.imaginers.mymvi.data.repository.UserRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()