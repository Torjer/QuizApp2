package com.example.quizapp2

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel


class GameModel : ViewModel() {

    var categories : String = "All"
    var difficulty : Int = 0
    var nquestions : Int = 5
    var ghints : Int = 0


    var diffID: Int = 0
    var optionCategories : String = "All"
    var enableSpinn : Boolean = false

    private val question = listOf<Question>(
        Question("Art",R.string.question_text_A1, R.string.Canswer_text_A1,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Art",R.string.question_text_A2, R.string.Canswer_text_A2,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Art",R.string.question_text_A3, R.string.Canswer_text_A3,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Art",R.string.question_text_A4, R.string.Canswer_text_A4,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Art",R.string.question_text_A5, R.string.Canswer_text_A5,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Geography",R.string.question_text_G1, R.string.Canswer_text_G1,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Geography",R.string.question_text_G2, R.string.Canswer_text_G2,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Geography",R.string.question_text_G3, R.string.Canswer_text_G3,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Geography",R.string.question_text_G4, R.string.Canswer_text_G4,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Geography",R.string.question_text_G5, R.string.Canswer_text_G5,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("History",R.string.question_text_H1, R.string.Canswer_text_H1,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("History",R.string.question_text_H2, R.string.Canswer_text_H2,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("History",R.string.question_text_H3, R.string.Canswer_text_H3,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("History",R.string.question_text_H4, R.string.Canswer_text_H4,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("History",R.string.question_text_H5, R.string.Canswer_text_H5,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Movies",R.string.question_text_M1, R.string.Canswer_text_M1,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Movies",R.string.question_text_M2, R.string.Canswer_text_M2,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Movies",R.string.question_text_M3, R.string.Canswer_text_M3,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Movies",R.string.question_text_M4, R.string.Canswer_text_M4,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Movies",R.string.question_text_M5, R.string.Canswer_text_M5,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Literature",R.string.question_text_L1, R.string.Canswer_text_L1,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Literature",R.string.question_text_L2, R.string.Canswer_text_L2,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Literature",R.string.question_text_L3, R.string.Canswer_text_L3,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Literature",R.string.question_text_L4, R.string.Canswer_text_L4,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Literature",R.string.question_text_L5, R.string.Canswer_text_L5,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Science",R.string.question_text_S1, R.string.Canswer_text_S1,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Science",R.string.question_text_S2, R.string.Canswer_text_S2,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Science",R.string.question_text_S3, R.string.Canswer_text_S3,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Science",R.string.question_text_S4, R.string.Canswer_text_S4,"#000000", listOf<Int>(0), listOf<Int>()),
        Question("Science",R.string.question_text_S5, R.string.Canswer_text_S5,"#000000", listOf<Int>(0), listOf<Int>())
    )

    var ans = listOf<Answer>(
        Answer(R.string.question_text_A1, R.string.I1answer_text_A1),
        Answer(R.string.question_text_A1, R.string.I2answer_text_A1),
        Answer(R.string.question_text_A1, R.string.I3answer_text_A1),
        Answer(R.string.question_text_A2, R.string.I1answer_text_A2),
        Answer(R.string.question_text_A2, R.string.I2answer_text_A2),
        Answer(R.string.question_text_A2, R.string.I3answer_text_A2),
        Answer(R.string.question_text_A3, R.string.I1answer_text_A3),
        Answer(R.string.question_text_A3, R.string.I2answer_text_A3),
        Answer(R.string.question_text_A3, R.string.I3answer_text_A3),
        Answer(R.string.question_text_A4, R.string.I3answer_text_A4),
        Answer(R.string.question_text_A4, R.string.I1answer_text_A4),
        Answer(R.string.question_text_A4, R.string.I2answer_text_A4),
        Answer(R.string.question_text_A5, R.string.I3answer_text_A5),
        Answer(R.string.question_text_A5, R.string.I1answer_text_A5),
        Answer(R.string.question_text_A5, R.string.I2answer_text_A5),
        Answer(R.string.question_text_G1, R.string.I1answer_text_G1),
        Answer(R.string.question_text_G1, R.string.I2answer_text_G1),
        Answer(R.string.question_text_G1, R.string.I3answer_text_G1),
        Answer(R.string.question_text_G2, R.string.I1answer_text_G2),
        Answer(R.string.question_text_G2, R.string.I2answer_text_G2),
        Answer(R.string.question_text_G2, R.string.I3answer_text_G2),
        Answer(R.string.question_text_G3, R.string.I1answer_text_G3),
        Answer(R.string.question_text_G3, R.string.I2answer_text_G3),
        Answer(R.string.question_text_G3, R.string.I3answer_text_G3),
        Answer(R.string.question_text_G4, R.string.I3answer_text_G4),
        Answer(R.string.question_text_G4, R.string.I1answer_text_G4),
        Answer(R.string.question_text_G4, R.string.I2answer_text_G4),
        Answer(R.string.question_text_G5, R.string.I3answer_text_G5),
        Answer(R.string.question_text_G5, R.string.I1answer_text_G5),
        Answer(R.string.question_text_G5, R.string.I2answer_text_G5),
        Answer(R.string.question_text_H1, R.string.I1answer_text_H1),
        Answer(R.string.question_text_H1, R.string.I2answer_text_H1),
        Answer(R.string.question_text_H1, R.string.I3answer_text_H1),
        Answer(R.string.question_text_H2, R.string.I1answer_text_H2),
        Answer(R.string.question_text_H2, R.string.I2answer_text_H2),
        Answer(R.string.question_text_H2, R.string.I3answer_text_H2),
        Answer(R.string.question_text_H3, R.string.I1answer_text_H3),
        Answer(R.string.question_text_H3, R.string.I2answer_text_H3),
        Answer(R.string.question_text_H3, R.string.I3answer_text_H3),
        Answer(R.string.question_text_H4, R.string.I3answer_text_H4),
        Answer(R.string.question_text_H4, R.string.I1answer_text_H4),
        Answer(R.string.question_text_H4, R.string.I2answer_text_H4),
        Answer(R.string.question_text_H5, R.string.I3answer_text_H5),
        Answer(R.string.question_text_H5, R.string.I1answer_text_H5),
        Answer(R.string.question_text_H5, R.string.I2answer_text_H5),
        Answer(R.string.question_text_M1, R.string.I1answer_text_M1),
        Answer(R.string.question_text_M1, R.string.I2answer_text_M1),
        Answer(R.string.question_text_M1, R.string.I3answer_text_M1),
        Answer(R.string.question_text_M2, R.string.I1answer_text_M2),
        Answer(R.string.question_text_M2, R.string.I2answer_text_M2),
        Answer(R.string.question_text_M2, R.string.I3answer_text_M2),
        Answer(R.string.question_text_M3, R.string.I1answer_text_M3),
        Answer(R.string.question_text_M3, R.string.I2answer_text_M3),
        Answer(R.string.question_text_M3, R.string.I3answer_text_M3),
        Answer(R.string.question_text_M4, R.string.I3answer_text_M4),
        Answer(R.string.question_text_M4, R.string.I1answer_text_M4),
        Answer(R.string.question_text_M4, R.string.I2answer_text_M4),
        Answer(R.string.question_text_M5, R.string.I3answer_text_M5),
        Answer(R.string.question_text_M5, R.string.I1answer_text_M5),
        Answer(R.string.question_text_M5, R.string.I2answer_text_M5),
        Answer(R.string.question_text_L1, R.string.I1answer_text_L1),
        Answer(R.string.question_text_L1, R.string.I2answer_text_L1),
        Answer(R.string.question_text_L1, R.string.I3answer_text_L1),
        Answer(R.string.question_text_L2, R.string.I1answer_text_L2),
        Answer(R.string.question_text_L2, R.string.I2answer_text_L2),
        Answer(R.string.question_text_L2, R.string.I3answer_text_L2),
        Answer(R.string.question_text_L3, R.string.I1answer_text_L3),
        Answer(R.string.question_text_L3, R.string.I2answer_text_L3),
        Answer(R.string.question_text_L3, R.string.I3answer_text_L3),
        Answer(R.string.question_text_L4, R.string.I3answer_text_L4),
        Answer(R.string.question_text_L4, R.string.I1answer_text_L4),
        Answer(R.string.question_text_L4, R.string.I2answer_text_L4),
        Answer(R.string.question_text_L5, R.string.I3answer_text_L5),
        Answer(R.string.question_text_L5, R.string.I1answer_text_L5),
        Answer(R.string.question_text_L5, R.string.I2answer_text_L5),
        Answer(R.string.question_text_S1, R.string.I1answer_text_S1),
        Answer(R.string.question_text_S1, R.string.I2answer_text_S1),
        Answer(R.string.question_text_S1, R.string.I3answer_text_S1),
        Answer(R.string.question_text_S2, R.string.I1answer_text_S2),
        Answer(R.string.question_text_S2, R.string.I2answer_text_S2),
        Answer(R.string.question_text_S2, R.string.I3answer_text_S2),
        Answer(R.string.question_text_S3, R.string.I1answer_text_S3),
        Answer(R.string.question_text_S3, R.string.I2answer_text_S3),
        Answer(R.string.question_text_S3, R.string.I3answer_text_S3),
        Answer(R.string.question_text_S4, R.string.I3answer_text_S4),
        Answer(R.string.question_text_S4, R.string.I1answer_text_S4),
        Answer(R.string.question_text_S4, R.string.I2answer_text_S4),
        Answer(R.string.question_text_S5, R.string.I3answer_text_S5),
        Answer(R.string.question_text_S5, R.string.I1answer_text_S5),
        Answer(R.string.question_text_S5, R.string.I2answer_text_S5)
    )


    var inGameQuestions = mutableListOf<Question>()
    var selCategories = listOf<String>()
    var HintsMax = 0
    var usedHints = 0
    var scoreMultplier = 0
    var totalScore = 0
    var Aquestions = 0
    var currentQuestionIndex = 0
    val currentQuestion : Question
        get() = inGameQuestions[currentQuestionIndex]
    var h1 = false
    var h2 = false
    var h3 = false
    var h4 = false

    var firstTime = true
    var finished = false


    fun difficultyChanges(dif: Int, quest: Question, ans: List<Int>): MutableList<Int> {

        var temp = mutableListOf<Int>()
        temp.add(quest.answer)
        temp.add(ans[0])
        when(dif){
            0 ->{
                scoreMultplier=1
                temp.shuffle()
                temp.add(ans[1])
                temp.add(ans[2])
                h1 = true
                h2 = true
                return temp

            }
            1->{
                scoreMultplier=2
                temp.add(ans[1])
                temp.shuffle()
                temp.add(ans[2])
                val mrandomNumber =(0..2).random()
                when(mrandomNumber){
                    0->{
                        h1 = true
                        h2 = true
                        h3 = false
                    }
                    1->{
                        h1 = true
                        h2 = false
                        h3 = true
                    }
                    2->{
                        h1 = false
                        h2 = true
                        h3 = true
                    }
                }
                return temp
            }
            2->{
                scoreMultplier=3
                temp.add(ans[1])
                temp.add(ans[2])
                temp.shuffle()
                val drandomNumber =(0..3).random()
                when(drandomNumber){
                    0->{
                        h1 = true
                        h2 = true
                        h3 = false
                        h4 = false
                    }
                    1->{
                        h1 = false
                        h2 = false
                        h3 = true
                        h4 = true
                    }
                    2->{
                        h1 = true
                        h2 = false
                        h3 = false
                        h4 = true
                    }
                    3->{
                        h1 = false
                        h2 = true
                        h3 = true
                        h4 = false
                    }
                }
                return temp
            }
        }
        return temp
    }

    fun randomHint(dif: Int){
        when(dif){
            0 ->{
                h1 = true
                h2 = true
            }
            1->{
                val mrandomNumber =(0..2).random()
                when(mrandomNumber){
                    0->{
                        h1 = true
                        h2 = true
                        h3 = false
                    }
                    1->{
                        h1 = true
                        h2 = false
                        h3 = true
                    }
                    2->{
                        h1 = false
                        h2 = true
                        h3 = true
                    }
                }
            }
            2->{
                val drandomNumber =(0..3).random()
                when(drandomNumber){
                    0->{
                        h1 = true
                        h2 = true
                        h3 = false
                        h4 = false
                    }
                    1->{
                        h1 = false
                        h2 = false
                        h3 = true
                        h4 = true
                    }
                    2->{
                        h1 = true
                        h2 = false
                        h3 = false
                        h4 = true
                    }
                    3->{
                        h1 = false
                        h2 = true
                        h3 = true
                        h4 = false
                    }
                }
            }
        }
    }


    fun chooseQuestions() {
        selCategories.forEach { cat ->
            if(cat.equals("All")){
                question.forEach {
                    inGameQuestions.add(it)
                }
            } else {
                question.forEach {
                    if (it.category.equals(cat)) {
                        inGameQuestions.add(it)
                    }
                }
            }
        }
    }


    fun shuffleQuestions() {
        inGameQuestions = inGameQuestions.shuffled().toMutableList()
    }

    fun makeHFalse() {
        h1 = false
        h2 = false
        h3 = false
        h4 = false
    }


}