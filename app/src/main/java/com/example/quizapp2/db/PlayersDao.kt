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

    @Query("INSERT INTO scoresInfo(userid,username,date,score,hints) VALUES(:userid, :username, :date, :score, :hints)")
    fun insertInfo(userid:Int,username:String, date:String, score:Int, hints:Boolean)

    @Insert
    fun insertPlayer(player: Player)
}