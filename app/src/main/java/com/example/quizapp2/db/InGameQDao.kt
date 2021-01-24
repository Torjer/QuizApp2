package com.example.quizapp2.db

import androidx.room.*

@Dao
interface InGameQDao {
    @Query("SELECT * FROM questions WHERE userid = :userid ORDER BY qId")
    fun getInGameQ(userid: Int) : List<InGameQ>

    @Query("UPDATE questions SET cIndex = :cIndex, category = :category, resID = :resID, answer = :answer, qcolor = :qcolor, wanswer = :wanswer, butanswered = :butanswered WHERE qId = :qId")
    fun updateInGameQ(cIndex: Int,  category: String, resID: Int, answer: Int, qcolor: String, wanswer: String, butanswered: String, qId: Int)

    @Query("INSERT INTO questions(cIndex, category, userid, resID, answer, qcolor, wanswer, butanswered) VALUES(:cIndex, :category, :userid, :resID, :answer, :qcolor, :wanser, :butanswered)")
    fun addInGameQ(cIndex:Int, category: String, userid : Int, resID : Int, answer: Int, qcolor : String, wanser : String, butanswered : String)
}