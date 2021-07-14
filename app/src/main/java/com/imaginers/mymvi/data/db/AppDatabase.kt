package com.imaginers.mymvi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imaginers.mymvi.data.db.entity.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

        companion object {

            private const val DATABASE_NAME: String = "mvi_app.db"

            // For Singleton instantiation
            @Volatile private var instance: AppDatabase? = null

            fun getInstance(context: Context): AppDatabase {
                return instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also { instance = it }
                }
            }

            private fun buildDatabase(context: Context): AppDatabase {
                return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }

    }
}