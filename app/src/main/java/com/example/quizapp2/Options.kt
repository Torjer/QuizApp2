package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

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
    private lateinit var easyRadio : RadioButton
    private lateinit var normalRadio : RadioButton
    private lateinit var hardRadio : RadioButton
    private var diffID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        saveButton = findViewById(R.id.save_button)
        cancelButton = findViewById(R.id.cancel_button)
        easyRadio = findViewById(R.id.easy_radiob)
        normalRadio = findViewById(R.id.medium_radiob)
        hardRadio = findViewById(R.id.hard_rb)

        saveButton.setOnClickListener { _ ->
            setResult(RESULT_SETTINGS_CONFIG,Intent().apply {
                putExtra(EXTRA_CATEGORIES_TEXT, "Hola")
                putExtra(EXTRA_DIFFICULTY_LEVEL, diffID)
                putExtra(EXTRA_QUESTION_NUMBERS,6)
                putExtra(EXTRA_HINT_OPTION,true)
            })
        }
    }
    fun onDifficultyChange(view: View){
        val difficultyRadio = view as RadioButton
        diffID = difficultyRadio.id
    }
}