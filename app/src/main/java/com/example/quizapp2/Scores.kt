package com.example.quizapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp2.db.Player
import kotlinx.android.synthetic.main.activity_scores.*

class Scores : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var playersList = mutableListOf<Player>()
    lateinit var orderBy: List<Player>
    lateinit var orderByDate: List<Player>
    lateinit var savedState : List<Player>
    companion object{
        const val EXTRA_ID_INFO = "com.example.quizapp2.id_info"
        const val EXTRA_LNAME_INFO = "com.example.quizapp2.lname_info"
        const val EXTRA_DATE_INFO = "com.example.quizapp2.date_info"
        const val EXTRA_SCORE_INFO = "com.example.quizapp2.score_info"
        const val EXTRA_HINTS_INFO = "com.example.quizapp2.hints_info"

        fun createIntent(
            packageContext: Context, idInfo: Array<String>, nameInfo: Array<String>, dateInfo: Array<String>, scoreInfo: Array<String>, hintsInfo: Array<String>
        ): Intent {
            return Intent(packageContext, Scores::class.java).apply{
                putExtra(EXTRA_ID_INFO, idInfo)
                putExtra(EXTRA_LNAME_INFO, nameInfo)
                putExtra(EXTRA_DATE_INFO, dateInfo)
                putExtra(EXTRA_SCORE_INFO,scoreInfo)
                putExtra(EXTRA_HINTS_INFO,hintsInfo)
            }
        }

        private lateinit var spinOptions : Spinner
        private lateinit var spinUser : Spinner

    }
    val order_list = arrayOf("Score","Date")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        val idinfoList = intent.getStringArrayExtra(EXTRA_ID_INFO)
        val userinfoList = intent.getStringArrayExtra(EXTRA_LNAME_INFO)
        val dateinfoList = intent.getStringArrayExtra(EXTRA_DATE_INFO)
        val scoreinfoList = intent.getStringArrayExtra(EXTRA_SCORE_INFO)
        val hintsinfoList = intent.getStringArrayExtra(EXTRA_HINTS_INFO)
        val fullUser  = userinfoList!!.toMutableList()
        fullUser.add(0,"All users")
        val userList = fullUser.distinct()
        spinOptions = findViewById(R.id.order_spinner)
        spinUser= findViewById(R.id.userselect_spinner)


        spinOptions.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, order_list)
        spinUser.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, userList!!)
        orderBy = playersList

        spinOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if(position == 0) {
                    savedState  = playersList.sortedByDescending{it.score}
                    orderBy = orderBy.sortedByDescending{it.score}
                    recyclerView.adapter= PlayersAdapter(orderBy)
                    viewAdapter.notifyDataSetChanged()}
                else{
                    savedState  = playersList.sortedByDescending{it.gamedate}
                    orderBy = orderBy.sortedByDescending{it.gamedate}
                    recyclerView.adapter= PlayersAdapter(orderBy)
                    viewAdapter.notifyDataSetChanged()
                }
            }

        }



        spinUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                orderBy = savedState
                var spinOptions_item = spinOptions.selectedItem.toString()
                if(spinOptions_item == "Score" || spinOptions_item == "Date"){
                    var userScore = mutableListOf<Player>()
                    orderBy.forEach{
                        if(spinUser.selectedItem.toString() == it.name){
                            userScore.add(it)
                        }
                        else if(spinUser.selectedItem.toString() == "All users"){
                            userScore = savedState.toMutableList()
                        }
                        orderBy = userScore
                        recyclerView.adapter= PlayersAdapter(orderBy)
                        viewAdapter.notifyDataSetChanged()
                    }
                }
                else{
                    orderBy = playersList.sortedByDescending { it.score }
                    recyclerView.adapter= PlayersAdapter(orderBy)
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }





        var i = 0
        userinfoList?.forEach {
            val player = Player(idinfoList!![i].toInt() ,userinfoList[i], dateinfoList!![i], scoreinfoList!![i].toInt(),hintsinfoList!![i].toBoolean())
            playersList.add(player)
            i++
        }



        viewManager = LinearLayoutManager(this)
        viewAdapter = PlayersAdapter(playersList)

        recyclerView = findViewById<RecyclerView>(R.id.scores_rv).apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@Scores)

            adapter = viewAdapter

        }
    }
}