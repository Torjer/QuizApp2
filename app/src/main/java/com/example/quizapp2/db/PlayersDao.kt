package com.example.quizapp2.db

import androidx.room.*

@Dao
interface PlayerDao {
    @Query("SELECT * FROM scoresInfo ORDER BY score")
    fun getInfo(): List<Player>
}