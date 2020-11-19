package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels

class Options : AppCompatActivity() {
    companion object {
        const val RESULT_SETTINGS_CONFIG = RESULT_FIRST_USER

        const val EXTRA_CATEGORIES_TEXT = "com.example.quizapp2.categories_text"
        const val EXTRA_DIFFICULTY_LEVEL = "com.example.quizapp2.difficulty_level"
        const val EXTRA_QUESTION_NUMBERS = "com.example.quizapp2.question_number"
        const val EXTRA_HINT_OPTION = "com.example.quizapp2.hint_option"

        fun createIntent(packageContext: Context, difficulty: Int): Intent {
            return Intent(packageContext, Options::class.java).apply {
                putExtra(EXTRA_DIFFICULTY_LEVEL, difficulty)
            }
        }
    }
    val gameModel: GameModel by viewModels()

    private val categoriesCheckboxes = mutableListOf<CheckBox>()
    private lateinit var allCheckB: CheckBox

    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    private lateinit var easyRadio: RadioButton
    private lateinit var normalRadio: RadioButton
    private lateinit var hardRadio: RadioButton

    private lateinit var hintSwitch: Switch

    private lateinit var spinQnum: Spinner
    private lateinit var spinHnum: Spinner

    private var selectedCat: Int = 6
    private var items = mutableListOf<String>()
    private var i: Int = 5
    private var hintsItems = listOf<Int>(1, 2, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        while (i <= (selectedCat * 5)) {
            items.add(i.toString())
            i++
        }
        categoriesCheckboxes.add(findViewById(R.id.art_checkb))
        categoriesCheckboxes.add(findViewById(R.id.science_checkb))
        categoriesCheckboxes.add(findViewById(R.id.history_checkb))
        categoriesCheckboxes.add(findViewById(R.id.literature_checkb))
        categoriesCheckboxes.add(findViewById(R.id.movies_checkb))
        categoriesCheckboxes.add(findViewById(R.id.geography_checkb))

        allCheckB = findViewById(R.id.all_checkb)

        saveButton = findViewById(R.id.save_button)
        cancelButton = findViewById(R.id.cancel_button)

        easyRadio = findViewById(R.id.easy_radiob)
        normalRadio = findViewById(R.id.medium_radiob)
        hardRadio = findViewById(R.id.hard_rb)

        hintSwitch = findViewById(R.id.hint_switch)

        spinQnum = findViewById(R.id.qnumber_spinner)
        spinHnum = findViewById(R.id.hnumber_spinner)

        spinQnum.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinHnum.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hintsItems)

        val getDifficulty = intent.getIntExtra(EXTRA_DIFFICULTY_LEVEL, 0)

        when (getDifficulty) {
            0 -> easyRadio.isChecked = true
            1 -> normalRadio.isChecked = true
            2 -> hardRadio.isChecked = true
        }

        saveButton.setOnClickListener { _ ->
            val sendCat = gameModel.optionCategories
         //   val questionNumber
            setResult(RESULT_SETTINGS_CONFIG, Intent().apply {
                putExtra(EXTRA_CATEGORIES_TEXT, sendCat)
                putExtra(EXTRA_DIFFICULTY_LEVEL, gameModel.diffID)
                putExtra(EXTRA_QUESTION_NUMBERS, spinQnum.selectedItemPosition+5)
                putExtra(EXTRA_HINT_OPTION, if (hintSwitch.isChecked) {spinHnum.selectedItemPosition + 1} else {0})
            })
            finish()
        }

        cancelButton.setOnClickListener { _ ->
            finish()
        }

        spinHnum.isEnabled = gameModel.enableSpinn

    }

    fun onDifficultyChange(view: View) {
        val difficultyRadio = view as RadioButton
        val checked = difficultyRadio.isChecked
        when (difficultyRadio.id) {
            easyRadio.id -> if (checked) {
                gameModel.diffID = 0
            }
            normalRadio.id -> if (checked) {
                gameModel.diffID = 1
            }
            hardRadio.id -> if (checked) {
                gameModel.diffID = 2
            }
        }
    }

    fun onCategoryClick(view: View) {
        val categoryCheck = view as CheckBox
        val checked = categoryCheck.isChecked
        val categoryList = mutableListOf<String>()
        when (categoryCheck.id) {
            allCheckB.id -> (if (checked) {
                categoriesCheckboxes.forEach { it.isChecked = true }
                selectedCat = 6
                allCheckB.isEnabled = false
                gameModel.optionCategories = "All"
            })
            else -> {
                selectedCat = 0
                categoriesCheckboxes.forEach {
                    if (it.isChecked) {
                        selectedCat++
                        if (selectedCat == 6) {
                            allCheckB.isChecked = true
                            allCheckB.isEnabled = false
                            categoryList.clear()
                            categoryList.add("All")
                        } else {
                            categoryList.add(it.text.toString())
                            allCheckB.isEnabled = true
                            allCheckB.isChecked = false
                        }
                    }
                }
                gameModel.optionCategories = categoryList.joinToString()
            }
        }
        if (selectedCat == 0) {
            selectedCat++
            categoryCheck.isChecked = true
        }
        i = 5
        items.clear()
        while (i <= (selectedCat * 5)) {
            items.add(i.toString())
            i++
        }
        spinQnum.setSelection(0)
    }

    fun onHintSwitchChange(view: View) {
        spinHnum.isEnabled = !gameModel.enableSpinn
        gameModel.enableSpinn = !gameModel.enableSpinn
    }

}