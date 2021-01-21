package com.example.quizapp2.db

import androidx.room.*

@Dao
interface GameConfigDao {
    @Query("SELECT * FROM options ORDER BY id")
    fun getOptions(): List<GameConfig>

    @Query("SELECT * FROM options WHERE id = :id")
    fun getSelectedOptions(id: Int) : GameConfig

    @Query("INSERT INTO options(id, category, difficulty, eqn, hints) VALUES(:id, :category, :difficulty, :eqn, :hints)")
    fun insertOptions(id: Int, category:String, difficulty:Int, eqn:Int, hints:Int)

    @Query("UPDATE options SET category = :category, difficulty = :difficulty, eqn= :eqn, hints= :hints WHERE id= :id" )
    fun updateOptions(id: Int, category:String, difficulty:Int, eqn:Int, hints:Int)

    @Query("DELETE FROM options WHERE id = :id")
    fun deleteOptions(id: Int)
}