package com.example.quizapp2.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[User::class, Player::class, GameConfig::class, InGameQ::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun playerDao() : PlayerDao
    abstract fun gameConfigDao() : GameConfigDao
    abstract fun inGameQDao() : InGameQDao
}

