package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Game : AppCompatActivity() {

    companion object{

        const val EXTRA_CATEGORIES_TEXT = "com.example.quizapp2.categories_text"
        const val EXTRA_DIFFICULTY_LEVEL = "com.example.quizapp2.difficulty_level"
        const val EXTRA_QUESTION_NUMBERS = "com.example.quizapp2.question_number"
        const val EXTRA_HINT_OPTION = "com.example.quizapp2.hint_option"

        fun createIntent(packageContext: Context,categories: String, difficulty: Int, qnumbers: Int, ghint: Boolean):Intent{
            return Intent(packageContext, Game::class.java).apply{
                putExtra(EXTRA_CATEGORIES_TEXT, categories)
                putExtra(EXTRA_DIFFICULTY_LEVEL, difficulty)
                putExtra(EXTRA_QUESTION_NUMBERS,qnumbers)
                putExtra(EXTRA_HINT_OPTION,ghint)
            }
        }
    }

    private val question = listOf<Question>(
        Question(1,R.string.question_text_A1, R.string.Canswer_text_A1),
        Question(1,R.string.question_text_A2, R.string.Canswer_text_A2),
        Question(1,R.string.question_text_A3, R.string.Canswer_text_A3),
        Question(1,R.string.question_text_A4, R.string.Canswer_text_A4),
        Question(1,R.string.question_text_A5, R.string.Canswer_text_A5),
        Question(2,R.string.question_text_G1, R.string.Canswer_text_G1),
        Question(2,R.string.question_text_G2, R.string.Canswer_text_G2),
        Question(2,R.string.question_text_G3, R.string.Canswer_text_G3),
        Question(2,R.string.question_text_G4, R.string.Canswer_text_G4),
        Question(2,R.string.question_text_G5, R.string.Canswer_text_G5),
        Question(3,R.string.question_text_H1, R.string.Canswer_text_H1),
        Question(3,R.string.question_text_H2, R.string.Canswer_text_H2),
        Question(3,R.string.question_text_H3, R.string.Canswer_text_H3),
        Question(3,R.string.question_text_H4, R.string.Canswer_text_H4),
        Question(3,R.string.question_text_H5, R.string.Canswer_text_H5),
        Question(4,R.string.question_text_M1, R.string.Canswer_text_M1),
        Question(4,R.string.question_text_M2, R.string.Canswer_text_M2),
        Question(4,R.string.question_text_M3, R.string.Canswer_text_M3),
        Question(4,R.string.question_text_M4, R.string.Canswer_text_M4),
        Question(4,R.string.question_text_M5, R.string.Canswer_text_M5),
        Question(5,R.string.question_text_L1, R.string.Canswer_text_L1),
        Question(5,R.string.question_text_L2, R.string.Canswer_text_L2),
        Question(5,R.string.question_text_L3, R.string.Canswer_text_L3),
        Question(5,R.string.question_text_L4, R.string.Canswer_text_L4),
        Question(5,R.string.question_text_L5, R.string.Canswer_text_L5),
        Question(6,R.string.question_text_S1, R.string.Canswer_text_S1),
        Question(6,R.string.question_text_S2, R.string.Canswer_text_S2),
        Question(6,R.string.question_text_S3, R.string.Canswer_text_S3),
        Question(6,R.string.question_text_S4, R.string.Canswer_text_S4),
        Question(6,R.string.question_text_S5, R.string.Canswer_text_S5)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



    }
}