package com.example.userapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userapp.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}