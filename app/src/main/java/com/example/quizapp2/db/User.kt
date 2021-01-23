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
    @ColumnInfo(name="selected") var selected: Int
)

