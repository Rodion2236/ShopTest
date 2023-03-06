package com.rodion2236.shopmobiletask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodion2236.shopmobiletask.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var APP_DB: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            return APP_DB ?: synchronized(this) {
                APP_DB ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "user_db"
                )
                    .fallbackToDestructiveMigration()
                    .enableMultiInstanceInvalidation()
                    .build()
                    .also { db -> APP_DB = db }
            }
        }
    }
}