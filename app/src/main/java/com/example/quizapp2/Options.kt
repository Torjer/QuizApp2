package com.example.quizapp2

import android.content.Context
import android.widget.CheckBox
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

        fun createIntent(packageContext: Context, difficulty: Int):Intent{
            return Intent(packageContext, Options::class.java).apply{
                putExtra(EXTRA_DIFFICULTY_LEVEL,difficulty)
            }
        }
    }

    private lateinit var allCheckB: CheckBox
    private lateinit var artCheckB: CheckBox
    private lateinit var scienceCheckB: CheckBox
    private lateinit var historyCheckB: CheckBox
    private lateinit var literatureCheckB: CheckBox
    private lateinit var moviesCheckB: CheckBox
    private lateinit var geographyCheckB: CheckBox

    private lateinit var saveButton : Button
    private lateinit var cancelButton : Button
    private lateinit var easyRadio : RadioButton
    private lateinit var normalRadio : RadioButton
    private lateinit var hardRadio : RadioButton
    private var diffID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        allCheckB = findViewById(R.id.all_checkb)
        artCheckB = findViewById(R.id.art_checkb)
        scienceCheckB = findViewById(R.id.science_checkb)
        historyCheckB = findViewById(R.id.history_checkb)
        literatureCheckB = findViewById(R.id.literature_checkb)
        moviesCheckB = findViewById(R.id.movies_checkb)
        geographyCheckB = findViewById(R.id.geography_checkb)

        saveButton = findViewById(R.id.save_button)
        cancelButton = findViewById(R.id.cancel_button)
        easyRadio = findViewById(R.id.easy_radiob)
        normalRadio = findViewById(R.id.medium_radiob)
        hardRadio = findViewById(R.id.hard_rb)

        val getDifficulty = intent.getIntExtra(EXTRA_DIFFICULTY_LEVEL,0)

        //Lógica de habilitación de Checkboxes
        allCheckB.setOnClickListener {
            if (allCheckB.isChecked == true) {
                artCheckB.isChecked = true
                scienceCheckB.isChecked = true
                historyCheckB.isChecked = true
                literatureCheckB.isChecked = true
                moviesCheckB.isChecked = true
                geographyCheckB.isChecked = true
                allCheckB.isEnabled = false
            }
        }

        artCheckB.setOnClickListener {
            if (artCheckB.isChecked == false) {
                allCheckB.isChecked = false
                allCheckB.isEnabled = true
            } else if (scienceCheckB.isChecked == true && historyCheckB.isChecked == true && literatureCheckB.isChecked == true && moviesCheckB.isChecked == true && geographyCheckB.isChecked == true) {
                allCheckB.isChecked = true
                allCheckB.isEnabled = false
            }
        }

        scienceCheckB.setOnClickListener {
            if (scienceCheckB.isChecked == false) {
                allCheckB.isChecked = false
                allCheckB.isEnabled = true
            } else if (artCheckB.isChecked == true && historyCheckB.isChecked == true && literatureCheckB.isChecked == true && moviesCheckB.isChecked == true && geographyCheckB.isChecked == true) {
                allCheckB.isChecked = true
                allCheckB.isEnabled = false
            }
        }

        historyCheckB.setOnClickListener {
            if (historyCheckB.isChecked == false) {
                allCheckB.isChecked = false
                allCheckB.isEnabled = true
            } else if (scienceCheckB.isChecked == true && artCheckB.isChecked == true && literatureCheckB.isChecked == true && moviesCheckB.isChecked == true && geographyCheckB.isChecked == true) {
                allCheckB.isChecked = true
                allCheckB.isEnabled = false
            }
        }

        literatureCheckB.setOnClickListener {
            if (literatureCheckB.isChecked == false) {
                allCheckB.isChecked = false
                allCheckB.isEnabled = true
            } else if (scienceCheckB.isChecked == true && historyCheckB.isChecked == true && artCheckB.isChecked == true && moviesCheckB.isChecked == true && geographyCheckB.isChecked == true) {
                allCheckB.isChecked = true
                allCheckB.isEnabled = false
            }
        }

        moviesCheckB.setOnClickListener {
            if (moviesCheckB.isChecked == false) {
                allCheckB.isChecked = false
                allCheckB.isEnabled = true
            } else if (scienceCheckB.isChecked == true && historyCheckB.isChecked == true && literatureCheckB.isChecked == true && artCheckB.isChecked == true && geographyCheckB.isChecked == true) {
                allCheckB.isChecked = true
                allCheckB.isEnabled = false
            }
        }

        geographyCheckB.setOnClickListener {
            if (geographyCheckB.isChecked == false) {
                allCheckB.isChecked = false
                allCheckB.isEnabled = true
            } else if (scienceCheckB.isChecked == true && historyCheckB.isChecked == true && literatureCheckB.isChecked == true && moviesCheckB.isChecked == true && artCheckB.isChecked == true) {
                allCheckB.isChecked = true
                allCheckB.isEnabled = false
            }
        }

        when(getDifficulty){
            0 -> easyRadio.isChecked = true
            1 -> normalRadio.isChecked = true
            2 -> hardRadio.isChecked = true
        }

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
        val checked = difficultyRadio.isChecked
        when(difficultyRadio.id){
            easyRadio.id -> if(checked){diffID = 0}
            normalRadio.id -> if(checked){diffID = 1}
            hardRadio.id -> if(checked){diffID = 2}
        }
    }
}