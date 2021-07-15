package com.imaginers.mymvi.data.repository

import com.imaginers.mymvi.data.db.AppDatabase
import com.imaginers.mymvi.data.db.entity.User
import com.imaginers.mymvi.data.db.UserDao
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


class UserRepository @Inject constructor(private val database: AppDatabase) {

    suspend fun create(user: User) = database.userDao().register(user)

    fun loginUser(email: String, password: String) = database.userDao().loginUser(email, password)

}