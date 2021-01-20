package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp2.db.Player

class Scores : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object{
        const val EXTRA_CATEGORIES_TEXT = "com.example.quizapp2.categories_text"
        const val EXTRA_DIFFICULTY_LEVEL = "com.example.quizapp2.difficulty_level"
        const val EXTRA_QUESTION_NUMBERS = "com.example.quizapp2.question_number"
        const val EXTRA_HINT_OPTION = "com.example.quizapp2.hint_option"

        fun createIntent(packageContext: Context, categories: String, difficulty: Int, qnumbers: Int, ghint: Int): Intent {
            return Intent(packageContext, Scores::class.java).apply{
                putExtra(EXTRA_CATEGORIES_TEXT, categories)
                putExtra(EXTRA_DIFFICULTY_LEVEL, difficulty)
                putExtra(EXTRA_QUESTION_NUMBERS,qnumbers)
                putExtra(EXTRA_HINT_OPTION,ghint)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
/*
        val playersArray = arrayOf(
            Player("Pepe", "10/08/20", 10, true),
            Player("Javier", "08/08/21", 15, false),
            Player("Erika", "05/24/19", 20, false),
            Player("Pedro", "07/10/20", 40, true),
            Player("Miguel", "02/01/21", 26, false)
        )*/
        viewManager = LinearLayoutManager(this)
       // viewAdapter = PlayersAdapter(playersArray)

        recyclerView = findViewById<RecyclerView>(R.id.scores_rv).apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@Scores)

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }
}