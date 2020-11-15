package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Options : AppCompatActivity() {
    companion object{
        const val RESULT_SETTINGS_CONFIG = RESULT_FIRST_USER

        const val EXTRA_CATEGORIES_TEXT = "com.example.quizapp2.categories_text"
        const val EXTRA_DIFFICULTY_LEVEL = "com.example.quizapp2.difficulty_level"
        const val EXTRA_QUESTION_NUMBERS = "com.example.quizapp2.question_number"
        const val EXTRA_HINT_OPTION = "com.example.quizapp2.hint_option"

        fun createIntent(packageContext: Context):Intent{
            return Intent(packageContext, Options::class.java).apply{
            }
        }
    }

    private lateinit var saveButton : Button
    private lateinit var cancelButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        saveButton = findViewById(R.id.save_button)
        cancelButton = findViewById(R.id.cancel_button)

        saveButton.setOnClickListener { _ ->
            setResult(RESULT_SETTINGS_CONFIG,Intent().apply {
                putExtra(EXTRA_CATEGORIES_TEXT, "Hola")
                putExtra(EXTRA_DIFFICULTY_LEVEL, 1)
                putExtra(EXTRA_QUESTION_NUMBERS,6)
                putExtra(EXTRA_HINT_OPTION,true)
            })
        }
    }
}