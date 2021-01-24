package com.example.quizapp2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "questions"
)

data class InGameQ
    (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "qId") val qID:Int,
    @ColumnInfo(name = "cIndex") val cIndex:Int,
    @ColumnInfo(name = "aquestions") val aquestions:Int,
    @ColumnInfo(name = "cScore") val cScore:Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "userid") val userid:Int,
    @ColumnInfo(name = "resID") val resID:Int,
    @ColumnInfo(name = "answer") val answer: Int,
    @ColumnInfo(name = "qcolor") var qcolor: String,
    @ColumnInfo(name = "wanswer") val wanswer: String,
    @ColumnInfo(name = "butanswered") var butanswered: String
)
