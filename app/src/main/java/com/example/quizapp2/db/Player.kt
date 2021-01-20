package com.example.quizapp2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "scoresInfo"
)

data class Player
    (
    @PrimaryKey @ColumnInfo(name = "username") val name:String,
    @ColumnInfo(name = "date") val gamedate:String,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "hints") val hints: Boolean
)
