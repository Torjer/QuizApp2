package com.example.quizapp2.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[User::class, Player::class, GameConfig::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun playerDao() : PlayerDao
    abstract fun gameConfigDao() : GameConfigDao
}

