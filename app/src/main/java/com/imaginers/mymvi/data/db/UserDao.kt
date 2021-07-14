package com.imaginers.mymvi.data.db

import androidx.room.*
import com.imaginers.mymvi.data.db.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM `users`")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM `users` WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM `users` WHERE user_name LIKE :user_name LIMIT 1")
    suspend fun findByName(user_name: String): User

    @Insert
    suspend fun register(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM `users` WHERE user_name= :username AND Password= :password")
    fun loginUser(username: String, password: String): Flow<User?>

    @Query("SELECT * FROM `users`")
    fun getLoggedInUser(): Flow<User>

    @Query("DELETE FROM `users`")
    suspend fun logOutUser()
}