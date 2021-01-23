package com.example.quizapp2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "options"
)
data class GameConfig (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name="difficulty") var difficulty: Int,
    @ColumnInfo(name = "eqn") var eqn: Int,
    @ColumnInfo(name = "hints") var hints : Int,
    @ColumnInfo(name = "active") var active : Int
)