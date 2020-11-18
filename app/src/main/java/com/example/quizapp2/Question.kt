package com.example.quizapp2

data class Question(val category: String, val resID: Int, val answer: Int, var qcolor: String, var wanswers : List<Int>, var butanswered : Int)

data class Answer(val qresID: Int, val wresID: Int)