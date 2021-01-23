package com.example.quizapp2.db

import androidx.room.*

@Dao
interface GameConfigDao {
    @Query("SELECT * FROM options ORDER BY id")
    fun getOptions(): List<GameConfig>

    @Query("SELECT * FROM options WHERE id = :id")
    fun getSelectedOptions(id: Int) : GameConfig

    @Query("INSERT INTO options(id, category, difficulty, eqn, hints, active) VALUES(:id, :category, :difficulty, :eqn, :hints, :active)")
    fun insertOptions(id: Int, category:String, difficulty:Int, eqn:Int, hints:Int, active: Int)

    @Query("UPDATE options SET category = :category, difficulty = :difficulty, eqn= :eqn, hints= :hints, active= :active WHERE id= :id" )
    fun updateOptions(id: Int, category:String, difficulty:Int, eqn:Int, hints:Int, active:Int)

    @Query("DELETE FROM options WHERE id = :id")
    fun deleteOptions(id: Int)
}