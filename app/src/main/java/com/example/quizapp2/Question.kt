package com.example.quizapp2

data class Question(var category: String, var resID: Int, var answer: Int, var qcolor: Int, var wanswers : List<Int>)

data class Answer(var qresID: Int, var wresID: Int)