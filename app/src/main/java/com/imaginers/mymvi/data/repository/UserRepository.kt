package com.imaginers.mymvi.data.repository

import com.imaginers.mymvi.data.db.entity.User
import com.imaginers.mymvi.data.db.UserDao

class UserRepository(
    private val userDao: UserDao
){
    suspend fun create(user: User) = userDao.register(user)
    fun loginUser(email: String, password: String)= userDao.loginUser(email, password)

//    suspend fun delete(user: User) = userDao.delete(user)
//    fun getLoggedInUser() = userDao.getLoggedInUser()

}