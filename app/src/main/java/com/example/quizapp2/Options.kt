package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Options : AppCompatActivity() {
    companion object{
        const val EXTRA_CATEGORIES_TEXT = "com.example.quizapp2.categories_text"
        const val EXTRA_DIFFICULTY_LEVEL = "com.example.quizapp2.difficulty_level"
        const val EXTRA_QUESTION_NUMBERS = "com.example.quizapp2.question_numbers"
        const val EXTRA_HINT_OPTION = "com.example.quizapp2.hint_option"

        fun createIntent(packageContext: Context, categories: String, difficulty: Int, nQuestions: Int, hints: Boolean):Intent{
            return Intent(packageContext, Options::class.java).apply{
                putExtra(EXTRA_CATEGORIES_TEXT,categories)
                putExtra(EXTRA_DIFFICULTY_LEVEL,difficulty)
                putExtra(EXTRA_QUESTION_NUMBERS,nQuestions)
                putExtra(EXTRA_HINT_OPTION,hints)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
    }
}