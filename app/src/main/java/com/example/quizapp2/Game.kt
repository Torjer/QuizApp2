package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_game.*

class Game : AppCompatActivity() {

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

    private lateinit var AnsButton1: Button
    private lateinit var AnsButton2: Button
    private lateinit var AnsButton3: Button
    private lateinit var AnsButton4: Button
    private lateinit var hintButton: Button
    private lateinit var questionText: TextView
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button

    private val question = listOf<Question>(
        Question("Art",R.string.question_text_A1, R.string.Canswer_text_A1,0, R.string.I1answer_text_A1, R.string.I2answer_text_A1, R.string.I3answer_text_A1),
        Question("Art",R.string.question_text_A2, R.string.Canswer_text_A2,0, R.string.I1answer_text_A2, R.string.I2answer_text_A2, R.string.I3answer_text_A2),
        Question("Art",R.string.question_text_A3, R.string.Canswer_text_A3,0, R.string.I1answer_text_A3, R.string.I2answer_text_A3, R.string.I3answer_text_A3),
        Question("Art",R.string.question_text_A4, R.string.Canswer_text_A4,0, R.string.I1answer_text_A4, R.string.I2answer_text_A4, R.string.I3answer_text_A4),
        Question("Art",R.string.question_text_A5, R.string.Canswer_text_A5,0, R.string.I1answer_text_A5, R.string.I2answer_text_A5, R.string.I3answer_text_A5),
        Question("Geography",R.string.question_text_G1, R.string.Canswer_text_G1,0, R.string.I1answer_text_G1, R.string.I2answer_text_G1, R.string.I3answer_text_G1),
        Question("Geography",R.string.question_text_G2, R.string.Canswer_text_G2,0, R.string.I1answer_text_G2, R.string.I2answer_text_G2, R.string.I3answer_text_G2),
        Question("Geography",R.string.question_text_G3, R.string.Canswer_text_G3,0, R.string.I1answer_text_G3, R.string.I2answer_text_G3, R.string.I3answer_text_G3),
        Question("Geography",R.string.question_text_G4, R.string.Canswer_text_G4,0, R.string.I1answer_text_G4, R.string.I2answer_text_G4, R.string.I3answer_text_G4),
        Question("Geography",R.string.question_text_G5, R.string.Canswer_text_G5,0, R.string.I1answer_text_G5, R.string.I2answer_text_G5, R.string.I3answer_text_G5),
        Question("History",R.string.question_text_H1, R.string.Canswer_text_H1,0, R.string.I1answer_text_H1, R.string.I2answer_text_H1, R.string.I3answer_text_H1),
        Question("History",R.string.question_text_H2, R.string.Canswer_text_H2,0, R.string.I1answer_text_H2, R.string.I2answer_text_H2, R.string.I3answer_text_H2),
        Question("History",R.string.question_text_H3, R.string.Canswer_text_H3,0, R.string.I1answer_text_H3, R.string.I2answer_text_H3, R.string.I3answer_text_H3),
        Question("History",R.string.question_text_H4, R.string.Canswer_text_H4,0, R.string.I1answer_text_H4, R.string.I2answer_text_H4, R.string.I3answer_text_H4),
        Question("History",R.string.question_text_H5, R.string.Canswer_text_H5,0, R.string.I1answer_text_H5, R.string.I2answer_text_H5, R.string.I3answer_text_H5),
        Question("Movies",R.string.question_text_M1, R.string.Canswer_text_M1,0, R.string.I1answer_text_M1, R.string.I2answer_text_M1, R.string.I3answer_text_M1),
        Question("Movies",R.string.question_text_M2, R.string.Canswer_text_M2,0, R.string.I1answer_text_M2, R.string.I2answer_text_M2, R.string.I3answer_text_M2),
        Question("Movies",R.string.question_text_M3, R.string.Canswer_text_M3,0, R.string.I1answer_text_M3, R.string.I2answer_text_M3, R.string.I3answer_text_M3),
        Question("Movies",R.string.question_text_M4, R.string.Canswer_text_M4,0, R.string.I1answer_text_M4, R.string.I2answer_text_M4, R.string.I3answer_text_M4),
        Question("Movies",R.string.question_text_M5, R.string.Canswer_text_M5,0, R.string.I1answer_text_M5, R.string.I2answer_text_M5, R.string.I3answer_text_M5),
        Question("Literature",R.string.question_text_L1, R.string.Canswer_text_L1,0, R.string.I1answer_text_L1, R.string.I2answer_text_L1, R.string.I3answer_text_L1),
        Question("Literature",R.string.question_text_L2, R.string.Canswer_text_L2,0, R.string.I1answer_text_L2, R.string.I2answer_text_L2, R.string.I3answer_text_L2),
        Question("Literature",R.string.question_text_L3, R.string.Canswer_text_L3,0, R.string.I1answer_text_L3, R.string.I2answer_text_L3, R.string.I3answer_text_L3),
        Question("Literature",R.string.question_text_L4, R.string.Canswer_text_L4,0, R.string.I1answer_text_L4, R.string.I2answer_text_L4, R.string.I3answer_text_L4),
        Question("Literature",R.string.question_text_L5, R.string.Canswer_text_L5,0, R.string.I1answer_text_L5, R.string.I2answer_text_L5, R.string.I3answer_text_L5),
        Question("Science",R.string.question_text_S1, R.string.Canswer_text_S1,0, R.string.I1answer_text_S1, R.string.I2answer_text_S1, R.string.I3answer_text_S1),
        Question("Science",R.string.question_text_S2, R.string.Canswer_text_S2,0, R.string.I1answer_text_S2, R.string.I2answer_text_S2, R.string.I3answer_text_S2),
        Question("Science",R.string.question_text_S3, R.string.Canswer_text_S3,0, R.string.I1answer_text_S3, R.string.I2answer_text_S3, R.string.I3answer_text_S3),
        Question("Science",R.string.question_text_S4, R.string.Canswer_text_S4,0, R.string.I1answer_text_S4, R.string.I2answer_text_S4, R.string.I3answer_text_S4),
        Question("Science",R.string.question_text_S5, R.string.Canswer_text_S5,0, R.string.I1answer_text_S5, R.string.I2answer_text_S5, R.string.I3answer_text_S5)
    )
    private var inGameQuestions = mutableListOf<Question>()
    private var selCategories = listOf<String>()
    private var HintsMax = 0
    private var currentQuestionIndex = 0
    private val currentQuestion : Question
        get() = inGameQuestions[currentQuestionIndex]
    private val currentAnswers = mutableListOf<Int>()
    private lateinit var actualQuestion : Question


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        selCategories = intent.getStringExtra(EXTRA_CATEGORIES_TEXT).toString().split(",").map { it.trim() }
        AnsButton1 = findViewById(R.id.opt1_button)
        AnsButton2 = findViewById(R.id.opt2_button)
        AnsButton3 = findViewById(R.id.opt3_button)
        AnsButton4 = findViewById(R.id.opt4_button)
        hintButton = findViewById(R.id.hint_button)
        questionText = findViewById(R.id.question_text)
        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)


        var getHints = intent.getIntExtra(EXTRA_HINT_OPTION,0)
        if(getHints == 0){
            hintButton.setVisibility(View.INVISIBLE)
            tv_hintnumber.setVisibility(View.INVISIBLE)
            tv_hint.setVisibility(View.INVISIBLE)
        }
        else {
            hintButton.setVisibility(View.VISIBLE)
            tv_hintnumber.setVisibility(View.VISIBLE)
            tv_hint.setVisibility(View.VISIBLE)
        }

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

        inGameQuestions = inGameQuestions.shuffled().toMutableList()

        currentAnswers.add(currentQuestion.answer)
        currentAnswers.add(currentQuestion.ianswera)
        currentAnswers.add(currentQuestion.ianswerb)
        currentAnswers.add(currentQuestion.ianswerc)
        currentAnswers.shuffle()

        questionText.setText(currentQuestion.resID)
        AnsButton1.setText(currentAnswers[0])
        AnsButton2.setText(currentAnswers[1])
        AnsButton3.setText(currentAnswers[2])
        AnsButton4.setText(currentAnswers[3])

        currentAnswers.clear()


        HintsMax = getHints
        hintButton.setOnClickListener{_->
            tv_hintnumber.text = (getHints -1).toString() + "/" + HintsMax
            getHints = getHints -1
            if(getHints < 1){
                !hintButton.isEnabled
            }
            else {hintButton.isEnabled}
        }

        nextButton.setOnClickListener{_->
            currentQuestionIndex = (currentQuestionIndex + 1) % intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            currentAnswers.add(currentQuestion.answer)
            currentAnswers.add(currentQuestion.ianswera)
            currentAnswers.add(currentQuestion.ianswerb)
            currentAnswers.add(currentQuestion.ianswerc)
            currentAnswers.shuffle()

            questionText.setText(currentQuestion.resID)
            AnsButton1.setText(currentAnswers[0])
            AnsButton2.setText(currentAnswers[1])
            AnsButton3.setText(currentAnswers[2])
            AnsButton4.setText(currentAnswers[3])

            currentAnswers.clear()
        }

        prevButton.setOnClickListener{_->
            currentQuestionIndex = (intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5) + currentQuestionIndex -1 ) % intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            currentAnswers.add(currentQuestion.answer)
            currentAnswers.add(currentQuestion.ianswera)
            currentAnswers.add(currentQuestion.ianswerb)
            currentAnswers.add(currentQuestion.ianswerc)
            currentAnswers.shuffle()

            questionText.setText(currentQuestion.resID)
            AnsButton1.setText(currentAnswers[0])
            AnsButton2.setText(currentAnswers[1])
            AnsButton3.setText(currentAnswers[2])
            AnsButton4.setText(currentAnswers[3])

            currentAnswers.clear()
        }
    }
}