package com.example.quizapp2.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[User::class, Player::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun playerDao() : PlayerDao
}

