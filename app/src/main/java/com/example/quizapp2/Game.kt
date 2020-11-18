package com.example.quizapp2

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
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
    private lateinit var tvQuestionNumber : TextView
    private lateinit var tvHint : TextView

    private val question = listOf<Question>(
        Question("Art",R.string.question_text_A1, R.string.Canswer_text_A1,"#000000", listOf<Int>(0), 0),
        Question("Art",R.string.question_text_A2, R.string.Canswer_text_A2,"#000000", listOf<Int>(0), 0),
        Question("Art",R.string.question_text_A3, R.string.Canswer_text_A3,"#000000", listOf<Int>(0), 0),
        Question("Art",R.string.question_text_A4, R.string.Canswer_text_A4,"#000000", listOf<Int>(0), 0),
        Question("Art",R.string.question_text_A5, R.string.Canswer_text_A5,"#000000", listOf<Int>(0), 0),
        Question("Geography",R.string.question_text_G1, R.string.Canswer_text_G1,"#000000", listOf<Int>(0), 0),
        Question("Geography",R.string.question_text_G2, R.string.Canswer_text_G2,"#000000", listOf<Int>(0), 0),
        Question("Geography",R.string.question_text_G3, R.string.Canswer_text_G3,"#000000", listOf<Int>(0), 0),
        Question("Geography",R.string.question_text_G4, R.string.Canswer_text_G4,"#000000", listOf<Int>(0), 0),
        Question("Geography",R.string.question_text_G5, R.string.Canswer_text_G5,"#000000", listOf<Int>(0), 0),
        Question("History",R.string.question_text_H1, R.string.Canswer_text_H1,"#000000", listOf<Int>(0), 0),
        Question("History",R.string.question_text_H2, R.string.Canswer_text_H2,"#000000", listOf<Int>(0), 0),
        Question("History",R.string.question_text_H3, R.string.Canswer_text_H3,"#000000", listOf<Int>(0), 0),
        Question("History",R.string.question_text_H4, R.string.Canswer_text_H4,"#000000", listOf<Int>(0), 0),
        Question("History",R.string.question_text_H5, R.string.Canswer_text_H5,"#000000", listOf<Int>(0), 0),
        Question("Movies",R.string.question_text_M1, R.string.Canswer_text_M1,"#000000", listOf<Int>(0), 0),
        Question("Movies",R.string.question_text_M2, R.string.Canswer_text_M2,"#000000", listOf<Int>(0), 0),
        Question("Movies",R.string.question_text_M3, R.string.Canswer_text_M3,"#000000", listOf<Int>(0), 0),
        Question("Movies",R.string.question_text_M4, R.string.Canswer_text_M4,"#000000", listOf<Int>(0), 0),
        Question("Movies",R.string.question_text_M5, R.string.Canswer_text_M5,"#000000", listOf<Int>(0), 0),
        Question("Literature",R.string.question_text_L1, R.string.Canswer_text_L1,"#000000", listOf<Int>(0), 0),
        Question("Literature",R.string.question_text_L2, R.string.Canswer_text_L2,"#000000", listOf<Int>(0), 0),
        Question("Literature",R.string.question_text_L3, R.string.Canswer_text_L3,"#000000", listOf<Int>(0), 0),
        Question("Literature",R.string.question_text_L4, R.string.Canswer_text_L4,"#000000", listOf<Int>(0), 0),
        Question("Literature",R.string.question_text_L5, R.string.Canswer_text_L5,"#000000", listOf<Int>(0), 0),
        Question("Science",R.string.question_text_S1, R.string.Canswer_text_S1,"#000000", listOf<Int>(0), 0),
        Question("Science",R.string.question_text_S2, R.string.Canswer_text_S2,"#000000", listOf<Int>(0), 0),
        Question("Science",R.string.question_text_S3, R.string.Canswer_text_S3,"#000000", listOf<Int>(0), 0),
        Question("Science",R.string.question_text_S4, R.string.Canswer_text_S4,"#000000", listOf<Int>(0), 0),
        Question("Science",R.string.question_text_S5, R.string.Canswer_text_S5,"#000000", listOf<Int>(0), 0)
    )
    private var ans = listOf<Answer>(
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

    private var inGameQuestions = mutableListOf<Question>()
    private var selCategories = listOf<String>()
    private var HintsMax = 0
    private var scoreMultplier = 0
    private var totalScore = 0
    private var Aquestions = 0
    private var currentQuestionIndex = 0
    private val currentQuestion : Question
        get() = inGameQuestions[currentQuestionIndex]

    private fun isAnswered(color: String){
        if(color != "#000000"){
            AnsButton1.setTextColor(Color.parseColor("#a2a2a2"))
            AnsButton2.setTextColor(Color.parseColor("#a2a2a2"))
            AnsButton3.setTextColor(Color.parseColor("#a2a2a2"))
            AnsButton4.setTextColor(Color.parseColor("#a2a2a2"))
            AnsButton1.isEnabled = false
            AnsButton2.isEnabled = false
            AnsButton3.isEnabled = false
            AnsButton4.isEnabled = false
            when (currentQuestion.butanswered) {
                1 -> AnsButton1.setTextColor(Color.parseColor(color))
                2 -> AnsButton2.setTextColor(Color.parseColor(color))
                3 -> AnsButton3.setTextColor(Color.parseColor(color))
                4 -> AnsButton4.setTextColor(Color.parseColor(color))
            }
        }
        else{
            AnsButton1.setTextColor(Color.parseColor("#000000"))
            AnsButton2.setTextColor(Color.parseColor("#000000"))
            AnsButton3.setTextColor(Color.parseColor("#000000"))
            AnsButton4.setTextColor(Color.parseColor("#000000"))
            AnsButton1.isEnabled = true
            AnsButton2.isEnabled = true
            AnsButton3.isEnabled = true
            AnsButton4.isEnabled = true
        }
    }

    private fun difficultyChanges(dif: Int, quest: Question, ans: List<Int>): MutableList<Int> {

        var temp = mutableListOf<Int>()
        temp.add(quest.answer)
        temp.add(ans[0])
        when(dif){
            0 ->{
                scoreMultplier=1
                temp.shuffle()
                temp.add(ans[1])
                temp.add(ans[2])
                return temp
            }
            1->{
                scoreMultplier=2
                temp.add(ans[1])
                temp.shuffle()
                temp.add(ans[2])
                return temp
            }
            2->{
                scoreMultplier=3
                temp.add(ans[1])
                temp.add(ans[2])
                temp.shuffle()
                return temp
            }
        }
        return temp
    }

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
        tvQuestionNumber = findViewById(R.id.tv_questionnumber)
        tvHint = findViewById(R.id.tv_hint)

        var getHints = intent.getIntExtra(EXTRA_HINT_OPTION,0)
        val difSet = intent.getIntExtra(EXTRA_DIFFICULTY_LEVEL,0)
        HintsMax = getHints

        when(difSet){
            0->{
                AnsButton3.visibility=View.INVISIBLE
                AnsButton4.visibility=View.INVISIBLE
            }
            1->{AnsButton4.visibility=View.INVISIBLE}
        }

        if(getHints == 0){
            hintButton.setVisibility(View.INVISIBLE)
            tv_hintnumber.setVisibility(View.INVISIBLE)
            tv_hint.setVisibility(View.INVISIBLE)
        }
        else {
            hintButton.setVisibility(View.VISIBLE)
            tv_hintnumber.setVisibility(View.VISIBLE)
            tv_hint.setVisibility(View.VISIBLE)
            tv_hintnumber.text = (getHints).toString() + "/" + HintsMax
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

        inGameQuestions.forEach{ gQ->
            var temp = mutableListOf<Int>()
            ans.forEach {
                if(it.qresID == gQ.resID){
                    temp.add(it.wresID)
                }
            }
            temp = difficultyChanges(difSet,gQ,temp)
            gQ.wanswers = temp
        }

        tvQuestionNumber.text = (currentQuestionIndex + 1).toString() + "/" + intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)

        inGameQuestions = inGameQuestions.shuffled().toMutableList()

        questionText.setText(currentQuestion.resID)
        questionText.setTextColor(Color.parseColor(currentQuestion.qcolor))
        AnsButton1.setText(currentQuestion.wanswers[0])
        AnsButton2.setText(currentQuestion.wanswers[1])
        AnsButton3.setText(currentQuestion.wanswers[2])
        AnsButton4.setText(currentQuestion.wanswers[3])

        hintButton.setOnClickListener{_->
            questionText.setTextColor(Color.parseColor("#0000FF"))
            if(getHints < 1){
                !hintButton.isEnabled
            }
            else {
                hintButton.isEnabled
                getHints = getHints -1
                totalScore--
                tv_hintnumber.text = (getHints).toString() + "/" + HintsMax
            }
        }

        nextButton.setOnClickListener{_->
            currentQuestionIndex = (currentQuestionIndex + 1) % intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            tvQuestionNumber.text = (currentQuestionIndex + 1).toString() + "/" + intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            questionText.setText(currentQuestion.resID)
            questionText.setTextColor(Color.parseColor(currentQuestion.qcolor))
            isAnswered(currentQuestion.qcolor)
            AnsButton1.setText(currentQuestion.wanswers[0])
            AnsButton2.setText(currentQuestion.wanswers[1])
            AnsButton3.setText(currentQuestion.wanswers[2])
            AnsButton4.setText(currentQuestion.wanswers[3])
        }

        prevButton.setOnClickListener{_->
            currentQuestionIndex = (intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5) + currentQuestionIndex -1 ) % intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            questionText.setText(currentQuestion.resID)
            tvQuestionNumber.text = (currentQuestionIndex + 1).toString() + "/" + intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            questionText.setTextColor(Color.parseColor(currentQuestion.qcolor))
            isAnswered(currentQuestion.qcolor)
            AnsButton1.setText(currentQuestion.wanswers[0])
            AnsButton2.setText(currentQuestion.wanswers[1])
            AnsButton3.setText(currentQuestion.wanswers[2])
            AnsButton4.setText(currentQuestion.wanswers[3])
        }
    }

    fun onAnswerClick(view: View){
        val selAnswer = view as Button
        Aquestions++
        if(selAnswer.text == getText(currentQuestion.answer)){
            currentQuestion.qcolor = "#5f6f2e"
            questionText.setTextColor(Color.parseColor(currentQuestion.qcolor))
            totalScore++

        }
        else{
            currentQuestion.qcolor = "#e30118"
            questionText.setTextColor(Color.parseColor(currentQuestion.qcolor))
        }
        selAnswer.setTextColor(Color.parseColor(currentQuestion.qcolor))
        when (selAnswer.id) {
            opt1_button.id -> currentQuestion.butanswered = 1
            opt2_button.id -> currentQuestion.butanswered = 2
            opt3_button.id -> currentQuestion.butanswered = 3
            opt4_button.id -> currentQuestion.butanswered = 4
        }
        isAnswered(currentQuestion.qcolor)
        if(Aquestions==intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)){
            finish() //sustituir por el score y la image
        }
    }
}