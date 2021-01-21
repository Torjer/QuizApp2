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
        const val EXTRA_LNAME_INFO = "com.example.quizapp2.lname_info"
        const val EXTRA_DATE_INFO = "com.example.quizapp2.date_info"
        const val EXTRA_SCORE_INFO = "com.example.quizapp2.score_info"
        const val EXTRA_HINTS_INFO = "com.example.quizapp2.hints_info"

        fun createIntent(
            packageContext: Context, nameInfo: Array<String>, dateInfo: Array<String>,
            scoreInfo: Array<String>,
            hintsInfo: Array<String>
        ): Intent {
            return Intent(packageContext, Scores::class.java).apply{
                putExtra(EXTRA_LNAME_INFO, nameInfo)
                putExtra(EXTRA_DATE_INFO, dateInfo)
                putExtra(EXTRA_SCORE_INFO,scoreInfo)
                putExtra(EXTRA_HINTS_INFO,hintsInfo)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        var playersList = mutableListOf<Player>()

        val userinfoList = intent.getStringArrayExtra(EXTRA_LNAME_INFO)
        val dateinfoList = intent.getStringArrayExtra(EXTRA_DATE_INFO)
        val scoreinfoList = intent.getStringArrayExtra(EXTRA_SCORE_INFO)
        val hintsinfoList = intent.getStringArrayExtra(EXTRA_HINTS_INFO)
        var i = 0
        userinfoList?.forEach {
            val player = Player(userinfoList[i], dateinfoList!![i], scoreinfoList!![i].toInt(),hintsinfoList!![i].toBoolean())
            playersList.add(player)
            i++
        }

        //Es para la prueba
        //var playersArray = arrayOf(
        //    Player("Pepe","12/06/20",98,true),
        //    Player("Fer","15099",80,false),
        //    Player("Caro","12/06/20",34,true)
        //)
        //Fin de prueba

        viewManager = LinearLayoutManager(this)
        viewAdapter = PlayersAdapter(playersList)

        recyclerView = findViewById<RecyclerView>(R.id.scores_rv).apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@Scores)

            adapter = viewAdapter

        }
    }
}