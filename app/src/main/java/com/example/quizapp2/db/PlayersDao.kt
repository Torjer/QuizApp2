package com.example.quizapp2.db

import androidx.room.*

@Dao
interface PlayerDao {
    @Query("SELECT * FROM scoresInfo ORDER BY score")
    fun getInfo(): List<Player>

    @Update
    fun updateInfo(player: Player)

    @Update
    fun updateInfo(player: List<Player>)

    @Query("INSERT INTO scoresInfo(username,date,score,hints) VALUES(:username, :date, :score, :hints)")
    fun insertInfo(username:String, date:String, score:Int, hints:Boolean)

    @Query("DELETE FROM scoresInfo WHERE username LIKE :text")
    fun deleteScore(text: String)

    @Insert
    fun insertPlayer(player: Player)

    @Query("SELECT * FROM scoresInfo ORDER BY score DESC, hints ASC")
    fun getScoresDesc() : List<Player>

    @Query("SELECT * FROM scoresInfo ORDER BY userid DESC")
    fun getScoreIdDesc() : List<Player>
}