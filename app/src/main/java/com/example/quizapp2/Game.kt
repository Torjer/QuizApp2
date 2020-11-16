package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_game.*

class Game : AppCompatActivity() {

    private lateinit var hintButton: Button

    companion object{

        const val EXTRA_CATEGORIES_TEXT = "com.example.quizapp2.categories_text"
        const val EXTRA_DIFFICULTY_LEVEL = "com.example.quizapp2.difficulty_level"
        const val EXTRA_QUESTION_NUMBERS = "com.example.quizapp2.question_number"
        const val EXTRA_HINT_OPTION = "com.example.quizapp2.hint_option"

        fun createIntent(packageContext: Context,categories: String, difficulty: Int, qnumbers: Int, ghint: Int):Intent{
            return Intent(packageContext, Game::class.java).apply{
                putExtra(EXTRA_CATEGORIES_TEXT, categories)
                putExtra(EXTRA_DIFFICULTY_LEVEL, difficulty)
                putExtra(EXTRA_QUESTION_NUMBERS,qnumbers)
                putExtra(EXTRA_HINT_OPTION,ghint)
            }
        }
    }

    private val question = listOf<Question>(
        Question("Art",R.string.question_text_A1, R.string.Canswer_text_A1),
        Question("Art",R.string.question_text_A2, R.string.Canswer_text_A2),
        Question("Art",R.string.question_text_A3, R.string.Canswer_text_A3),
        Question("Art",R.string.question_text_A4, R.string.Canswer_text_A4),
        Question("Art",R.string.question_text_A5, R.string.Canswer_text_A5),
        Question("Geography",R.string.question_text_G1, R.string.Canswer_text_G1),
        Question("Geography",R.string.question_text_G2, R.string.Canswer_text_G2),
        Question("Geography",R.string.question_text_G3, R.string.Canswer_text_G3),
        Question("Geography",R.string.question_text_G4, R.string.Canswer_text_G4),
        Question("Geography",R.string.question_text_G5, R.string.Canswer_text_G5),
        Question("History",R.string.question_text_H1, R.string.Canswer_text_H1),
        Question("History",R.string.question_text_H2, R.string.Canswer_text_H2),
        Question("History",R.string.question_text_H3, R.string.Canswer_text_H3),
        Question("History",R.string.question_text_H4, R.string.Canswer_text_H4),
        Question("History",R.string.question_text_H5, R.string.Canswer_text_H5),
        Question("Movies",R.string.question_text_M1, R.string.Canswer_text_M1),
        Question("Movies",R.string.question_text_M2, R.string.Canswer_text_M2),
        Question("Movies",R.string.question_text_M3, R.string.Canswer_text_M3),
        Question("Movies",R.string.question_text_M4, R.string.Canswer_text_M4),
        Question("Movies",R.string.question_text_M5, R.string.Canswer_text_M5),
        Question("Literature",R.string.question_text_L1, R.string.Canswer_text_L1),
        Question("Literature",R.string.question_text_L2, R.string.Canswer_text_L2),
        Question("Literature",R.string.question_text_L3, R.string.Canswer_text_L3),
        Question("Literature",R.string.question_text_L4, R.string.Canswer_text_L4),
        Question("Literature",R.string.question_text_L5, R.string.Canswer_text_L5),
        Question("Science",R.string.question_text_S1, R.string.Canswer_text_S1),
        Question("Science",R.string.question_text_S2, R.string.Canswer_text_S2),
        Question("Science",R.string.question_text_S3, R.string.Canswer_text_S3),
        Question("Science",R.string.question_text_S4, R.string.Canswer_text_S4),
        Question("Science",R.string.question_text_S5, R.string.Canswer_text_S5)
    )
    private var selCategories = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        hintButton = findViewById(R.id.hint_button)
        selCategories = EXTRA_CATEGORIES_TEXT.toString().split(",").map{it.trim()}
        var getHints = intent.getIntExtra(EXTRA_HINT_OPTION,0)
        if(getHints == 0){
            hintButton.setVisibility(View.INVISIBLE)
        }
        else {hintButton.setVisibility(View.VISIBLE)}

        hintButton.setOnClickListener{_->
            getHints = getHints -1
            if(getHints < 1){
                !hintButton.isEnabled
            }
            else {hintButton.isEnabled}
        }

    }
}