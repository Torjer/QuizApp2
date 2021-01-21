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
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import kotlinx.android.synthetic.main.score_dialog.*
import androidx.activity.viewModels
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp2.db.AppDatabase


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

    lateinit var AnsButton1: Button
    lateinit var AnsButton2: Button
    lateinit var AnsButton3: Button
    lateinit var AnsButton4: Button
    lateinit var hintButton: Button
    lateinit var questionText: TextView
    lateinit var nextButton: Button
    lateinit var prevButton: Button
    lateinit var tvQuestionNumber : TextView
    lateinit var tvHint : TextView

    private lateinit var db : AppDatabase

    val gameModel: GameModel by viewModels()

    private fun isAnswered(quest: Question){
        if(quest.qcolor == "#008000" || quest.qcolor == "#e30118"){
            AnsButton1.isEnabled = false
            AnsButton2.isEnabled = false
            AnsButton3.isEnabled = false
            AnsButton4.isEnabled = false
            hintButton.isEnabled = false
            AnsButton1.setTextColor(quest.butanswered[0])
            AnsButton2.setTextColor(quest.butanswered[1])
            AnsButton3.setTextColor(quest.butanswered[2])
            AnsButton4.setTextColor(quest.butanswered[3])
        }
        else if(quest.qcolor == "#0000FF"){
            hintButton.isEnabled = true
            AnsButton1.isEnabled = true
            AnsButton2.isEnabled = true
            AnsButton3.isEnabled = true
            AnsButton4.isEnabled = true
            AnsButton1.setTextColor(quest.butanswered[0])
            AnsButton2.setTextColor(quest.butanswered[1])
            AnsButton3.setTextColor(quest.butanswered[2])
            AnsButton4.setTextColor(quest.butanswered[3])
            var temp = mutableListOf<Button>()
            temp.add(AnsButton1)
            temp.add(AnsButton2)
            temp.add(AnsButton3)
            temp.add(AnsButton4)
            temp.forEach {
                if(it.currentTextColor == questionText.currentTextColor){
                    it.isEnabled = false
                }
            }
        }
        else if(quest.qcolor == "#000000"){
            AnsButton2.setTextColor(Color.parseColor("#8ADDAE"))
            AnsButton3.setTextColor(Color.parseColor("#2f4950"))
            AnsButton4.setTextColor(Color.parseColor("#8ADDAE"))
            AnsButton1.setTextColor(Color.parseColor("#2f4950"))
            AnsButton1.isEnabled = true
            AnsButton2.isEnabled = true
            AnsButton3.isEnabled = true
            AnsButton4.isEnabled = true
            hintButton.isEnabled = true
        }
    }

    private fun autoAnswer(){
        if (AnsButton1.text == getText(gameModel.currentQuestion.answer)) {
            AnsButton1.callOnClick()
        } else if (AnsButton2.text == getText(gameModel.currentQuestion.answer)) {
            AnsButton2.callOnClick()
        } else if (AnsButton3.text == getText(gameModel.currentQuestion.answer)) {
            AnsButton3.callOnClick()
        } else if (AnsButton4.text == getText(gameModel.currentQuestion.answer)) {
            AnsButton4.callOnClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameModel.selCategories = intent.getStringExtra(EXTRA_CATEGORIES_TEXT).toString().split(",").map { it.trim() }

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quizzapp.db"
        ).allowMainThreadQueries()
            .addCallback( object: RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("INSERT INTO users(id, username, selected, playing, questids, qcolors, answers, buttonsstatus) VALUES(0, 'No user', 1, 0, '', '', '', '')")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 1','mm/dd/yy',00,false)")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 2','mm/dd/yy',00,true)")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 3','mm/dd/yy',00,false)")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 4','mm/dd/yy',00,false)")
                } }
            ).build()


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

        gameModel.HintsMax = getHints
        gameModel.remainingHints = gameModel.HintsMax-gameModel.usedHints

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
            tv_hintnumber.text = (gameModel.HintsMax-gameModel.usedHints).toString() + "/" + gameModel.HintsMax
        }

        if (gameModel.firstTime) {
            gameModel.chooseQuestions()
            gameModel.hintsPerQ(difSet)
            gameModel.inGameQuestions.forEach{ gQ->
                var temp = mutableListOf<Int>()
                gameModel.ans.forEach {
                    if(it.qresID == gQ.resID){
                        temp.add(it.wresID)
                    }
                }
                temp = gameModel.difficultyChanges(difSet,gQ,temp)
                gQ.wanswers = temp
            }
            gameModel.firstTime = false
            gameModel.shuffleQuestions()
        }

        tvQuestionNumber.text = (gameModel.currentQuestionIndex + 1).toString() + "/" + intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)

        questionText.setText(gameModel.currentQuestion.resID)
        questionText.setTextColor(Color.parseColor(gameModel.currentQuestion.qcolor))
        AnsButton1.setText(gameModel.currentQuestion.wanswers[0])
        AnsButton2.setText(gameModel.currentQuestion.wanswers[1])
        AnsButton3.setText(gameModel.currentQuestion.wanswers[2])
        AnsButton4.setText(gameModel.currentQuestion.wanswers[3])

        if(gameModel.answered) {
            isAnswered(gameModel.currentQuestion)
        }

        gameModel.answered = true

        hintButton.setOnClickListener{_->
            if(gameModel.remainingHints < 1){
                hintButton.isEnabled = false
            }
            else {
                gameModel.usedHints++
                hintButton.isEnabled
                gameModel.currentQuestion.qcolor = "#0000FF"
                gameModel.remainingHints--
                tv_hintnumber.text =
                    (gameModel.remainingHints).toString() + "/" + gameModel.HintsMax
                gameModel.randomHint(difSet)
                if ((AnsButton1.text != getText(gameModel.currentQuestion.answer)) && gameModel.h1) {
                    AnsButton1.isEnabled = false
                    AnsButton1.setTextColor(Color.parseColor("#0000FF"))
                    gameModel.makeHFalse()
                } else if ((AnsButton2.text != getText(gameModel.currentQuestion.answer)) && gameModel.h2) {
                    AnsButton2.isEnabled = false
                    AnsButton2.setTextColor(Color.parseColor("#0000FF"))
                    gameModel.makeHFalse()
                } else if ((AnsButton3.text != getText(gameModel.currentQuestion.answer)) && gameModel.h3) {
                    AnsButton3.isEnabled = false
                    AnsButton3.setTextColor(Color.parseColor("#0000FF"))
                    gameModel.makeHFalse()
                } else if ((AnsButton4.text != getText(gameModel.currentQuestion.answer)) && gameModel.h4) {
                    AnsButton4.isEnabled = false
                    AnsButton4.setTextColor(Color.parseColor("#0000FF"))
                    gameModel.makeHFalse()
                }
                var temp = mutableListOf<Int>()
                temp.add(AnsButton1.currentTextColor)
                temp.add(AnsButton2.currentTextColor)
                temp.add(AnsButton3.currentTextColor)
                temp.add(AnsButton4.currentTextColor)

                gameModel.currentQuestion.butanswered = temp
                questionText.setTextColor(Color.parseColor(gameModel.currentQuestion.qcolor))

                var i = 0
                gameModel.currentQuestion.butanswered.forEach {
                    if (it == Color.parseColor("#0000FF")) {
                        i++
                    }
                }
                when(difSet){
                    0-> if (i==1){autoAnswer()}
                    1-> if (i==2){autoAnswer()}
                    2-> if (i==3){autoAnswer()}
                }
            }
        }

        nextButton.setOnClickListener{_->
            gameModel.currentQuestionIndex = (gameModel.currentQuestionIndex + 1) % intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            tvQuestionNumber.text = (gameModel.currentQuestionIndex + 1).toString() + "/" + intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            questionText.setText(gameModel.currentQuestion.resID)
            questionText.setTextColor(Color.parseColor(gameModel.currentQuestion.qcolor))
            isAnswered(gameModel.currentQuestion)
            AnsButton1.setText(gameModel.currentQuestion.wanswers[0])
            AnsButton2.setText(gameModel.currentQuestion.wanswers[1])
            AnsButton3.setText(gameModel.currentQuestion.wanswers[2])
            AnsButton4.setText(gameModel.currentQuestion.wanswers[3])
        }

        prevButton.setOnClickListener{_->
            gameModel.currentQuestionIndex = (intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5) + gameModel.currentQuestionIndex -1 ) % intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            questionText.setText(gameModel.currentQuestion.resID)
            tvQuestionNumber.text = (gameModel.currentQuestionIndex + 1).toString() + "/" + intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)
            questionText.setTextColor(Color.parseColor(gameModel.currentQuestion.qcolor))
            isAnswered(gameModel.currentQuestion)
            AnsButton1.setText(gameModel.currentQuestion.wanswers[0])
            AnsButton2.setText(gameModel.currentQuestion.wanswers[1])
            AnsButton3.setText(gameModel.currentQuestion.wanswers[2])
            AnsButton4.setText(gameModel.currentQuestion.wanswers[3])
        }

        if (gameModel.finished) {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.score_dialog, null)
            var final_score = dialogView.findViewById<TextView>(R.id.tv_score)
            var final_image = dialogView.findViewById<ImageView>(R.id.img_score)
            var final_hints_used = dialogView.findViewById<TextView>(R.id.tv_hints_used)
            final_score.text = "Final Score: " + gameModel.totalScore.toString()
            if (gameModel.usedHints > 0) {
                final_hints_used.text = "Hints used: " + gameModel.usedHints.toString()
            }
            if (gameModel.totalScore == 90) {
                final_image.setImageResource(R.drawable.result1)
            } else if (gameModel.totalScore >= 60) {
                final_image.setImageResource(R.drawable.result2)
            } else if (gameModel.totalScore >= 30) {
                final_image.setImageResource(R.drawable.result3)
            } else {
                final_image.setImageResource(R.drawable.result4)
            }
            dialog.setView(dialogView)
            dialog.setCancelable(false)
            dialog.setPositiveButton("OK", { dialogInterface: DialogInterface, i: Int -> gameModel.finished=false})
            dialog.show()
        }
    }

    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.score_dialog, null)
        var final_score = dialogView.findViewById<TextView>(R.id.tv_score)
        final_score.text = "¿Do you want to exit?"
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.setNegativeButton("NO", { dialogInterface: DialogInterface, i: Int ->  gameModel.finished = false})
        dialog.setPositiveButton("YES", { dialogInterface: DialogInterface, i: Int ->
            var currentUser = db.userDao().getCurrentUser()
            currentUser.questids = ""
            currentUser.qcolors = ""
            currentUser.answers = ""
            currentUser.buttonsstatus = ""
            gameModel.inGameQuestions.forEach{
                currentUser.questids += it.resID.toString() + " "
                currentUser.qcolors += it.qcolor + " "
                currentUser.answers += it.wanswers.joinToString(",") + " "
                currentUser.buttonsstatus += it.butanswered.joinToString(",") + " "
                currentUser.playing = 1
                db.userDao().updateUser(currentUser)
            }
            super.onBackPressed()})
        dialog.show()
    }

    fun onAnswerClick(view: View){
        val selAnswer = view as Button
        gameModel.Aquestions++
        if(selAnswer.text == getText(gameModel.currentQuestion.answer)){
            gameModel.currentQuestion.qcolor = "#008000"
            questionText.setTextColor(Color.parseColor(gameModel.currentQuestion.qcolor))
            gameModel.totalScore++

        }
        else{
            gameModel.currentQuestion.qcolor = "#e30118"
            questionText.setTextColor(Color.parseColor(gameModel.currentQuestion.qcolor))
        }
        selAnswer.setTextColor(Color.parseColor(gameModel.currentQuestion.qcolor))
        var temp = mutableListOf<Int>()

        temp.add(AnsButton1.currentTextColor)
        temp.add(AnsButton2.currentTextColor)
        temp.add(AnsButton3.currentTextColor)
        temp.add(AnsButton4.currentTextColor)

        gameModel.currentQuestion.butanswered = temp

        isAnswered(gameModel.currentQuestion)
        if(gameModel.Aquestions==intent.getIntExtra(EXTRA_QUESTION_NUMBERS,5)){

            gameModel.totalScore = gameModel.totalScore*gameModel.scoreMultplier - gameModel.usedHints
            if(gameModel.totalScore<0){gameModel.totalScore=0}
            //Diálogo de puntaje
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.score_dialog, null)
            var final_score = dialogView.findViewById<TextView>(R.id.tv_score)
            var final_image = dialogView.findViewById<ImageView>(R.id.img_score)
            var final_hints_used = dialogView.findViewById<TextView>(R.id.tv_hints_used)
            final_score.text = "Final Score: " + gameModel.totalScore.toString()
            if (gameModel.usedHints > 0) {
                final_hints_used.text = "Hints used: " + gameModel.usedHints.toString()
            }
            if (gameModel.totalScore == 90) {
                final_image.setImageResource(R.drawable.result1)
            } else if (gameModel.totalScore >= 60) {
                final_image.setImageResource(R.drawable.result2)
            } else if (gameModel.totalScore >= 30) {
                final_image.setImageResource(R.drawable.result3)
            } else {
                final_image.setImageResource(R.drawable.result4)
            }
            gameModel.finished = true
            dialog.setView(dialogView)
            dialog.setCancelable(false)
            dialog.setPositiveButton("OK", { dialogInterface: DialogInterface, i: Int ->  gameModel.finished = false})
            dialog.show()
        }
    }
}