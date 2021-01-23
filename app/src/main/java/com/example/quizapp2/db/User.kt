package com.example.quizapp2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices =[Index(value = ["username"], unique=true)]
)
data class User (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name="selected") var selected: Int,
    @ColumnInfo(name="playing") var playing: Int,
    @ColumnInfo(name="questids") var questids: String,
    @ColumnInfo(name="qcolors") var qcolors: String,
    @ColumnInfo(name="answers") var answers: String,
    @ColumnInfo(name="buttonsstatus") var buttonsstatus: String
)

