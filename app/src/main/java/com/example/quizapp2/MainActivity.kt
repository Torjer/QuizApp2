package com.example.quizapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp2.db.AppDatabase
import com.example.quizapp2.db.GameConfig
import com.example.quizapp2.db.Player
import com.example.quizapp2.db.User
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyStore
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val OPTIONS_ACTIVITY_REQUEST_CODE = 0;
private const val GAME_ACTIVITY_REQUEST_CODE = 1;
private const val USER_ACTIVITY_REQUEST_CODE = 2;
private const val SCORES_ACTIVITY_REQUEST_CODE = 3;

class MainActivity : AppCompatActivity() {

    private lateinit var  playButton : Button
    private lateinit var setButton : Button
    private lateinit var scoreButton: Button
    private lateinit var userText : TextView


    private lateinit var userButton : Button
    private var SelUser: String = "Please add a new user to start the game"
    private var UserList = mutableListOf<String>()
    private var added = mutableListOf<String>()
    private var deleted = mutableListOf<String>()
    private var dateInfo = mutableListOf<String>("01/02/98","08/15/97","12/20/2018")
    private var nameInfo = mutableListOf<String>("Nina","Max","Oli")
    private var scoreInfo = mutableListOf<String>("98","74","60")
    private var hintsInfo = mutableListOf<String>("true","false","true")
    private lateinit var db : AppDatabase
    private lateinit var users : List<User>
    private lateinit var gConfig : List<GameConfig>
    //private lateinit var dao : usersDao()
    val gameModel: GameModel by viewModels()

    fun displayCurrentDate(): String {
        val now = LocalDate.now()
        var formatter =  DateTimeFormatter .ofPattern("MM-dd-yyyy")

        return  formatter.format(now).toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.play_button)
        setButton = findViewById(R.id.settings_button)
        scoreButton = findViewById(R.id.scores_button)
        userButton = findViewById(R.id.user_button)
        userText = findViewById(R.id.active_user)


        Stetho.initializeWithDefaults(this)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quizzapp.db"
        ).allowMainThreadQueries()
            .addCallback( object: RoomDatabase.Callback(){
               override fun onCreate(db: SupportSQLiteDatabase) {
                   super.onCreate(db)
                   db.execSQL("INSERT INTO users(id, username, selected) VALUES(-1, 'No user', 0)")
                   //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 1','mm/dd/yy',00,false)")
                   //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 2','mm/dd/yy',00,true)")
                   //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 3','mm/dd/yy',00,false)")
                   //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 4','mm/dd/yy',00,false)")
               } }
            ).build()
        users = db.userDao().getUsers()
        users.forEach {
            if(it.selected==1){
                SelUser = it.username
            }
            else{
                playButton.isEnabled = false
                setButton.isEnabled = false
            }
        }
        active_user.text = "Active user: " + SelUser

        //db.gameConfigDao().insertOptions(0,"All",0,5,0)

        playButton.setOnClickListener { _ ->
            val options = db.gameConfigDao().getSelectedOptions(db.userDao().getUsers(SelUser).id)
            startActivityForResult(
                Game.createIntent(this,options.category,options.difficulty,options.eqn,options.hints),
                GAME_ACTIVITY_REQUEST_CODE
            )
        }

        setButton.setOnClickListener { _ ->
            val options = db.gameConfigDao().getSelectedOptions(db.userDao().getUsers(SelUser).id)
            startActivityForResult(
                Options.createIntent(this,options.category,options.difficulty,options.eqn,options.hints),
                OPTIONS_ACTIVITY_REQUEST_CODE
            )
        }

        scoreButton.setOnClickListener { _->
            startActivityForResult(
            Scores.createIntent(this, nameInfo.toTypedArray(), dateInfo.toTypedArray(), scoreInfo.toTypedArray(), hintsInfo.toTypedArray()),
                SCORES_ACTIVITY_REQUEST_CODE
            )
        }

        userButton.setOnClickListener { _ ->
            users = db.userDao().getUsers()
            var tempusers = mutableListOf<String>()
            users.forEach {
                tempusers.add(it.username)
            }
            startActivityForResult(
                UserConfig.createIntent(this, SelUser, tempusers.toTypedArray()),
                USER_ACTIVITY_REQUEST_CODE
            )
            //db.userDao().updateUser(users)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            OPTIONS_ACTIVITY_REQUEST_CODE -> {
                when (resultCode) {
                    Options.RESULT_SETTINGS_CONFIG -> {
                        gameModel.categories = data!!.getStringExtra(Options.EXTRA_CATEGORIES_TEXT).toString()
                        gameModel.difficulty = data.getIntExtra(Options.EXTRA_DIFFICULTY_LEVEL, 0)
                        gameModel.nquestions = data.getIntExtra(Options.EXTRA_QUESTION_NUMBERS, 5)
                        gameModel.ghints = data.getIntExtra(Options.EXTRA_HINT_OPTION, 0)

                        gConfig = db.gameConfigDao().getOptions()
                        gConfig.forEach {
                            if(db.userDao().getUsers(SelUser).id == it.id){
                                db.gameConfigDao().updateOptions(it.id ,gameModel.categories,gameModel.difficulty,gameModel.nquestions,gameModel.ghints)
                            }
                        }
                    }
                }
            }

            USER_ACTIVITY_REQUEST_CODE->{
                when(resultCode){
                    UserConfig.RESULT_SETTINGS_CONFIG->{
                        SelUser = data!!.getStringExtra(UserConfig.EXTRA_SELECTED_USER).toString()
                        added = data.getStringArrayExtra(UserConfig.EXTRA_ADD_USERS)!!.toMutableList()
                        deleted = data.getStringArrayExtra(UserConfig.EXTRA_DEL_USERS)!!.toMutableList()

                        deleted.forEach{
                            if(db.userDao().getUsers(it).id >= 0) {
                                db.gameConfigDao().deleteOptions(db.userDao().getUsers(it).id)
                            }
                            db.userDao().deleteUser(it)
                        }
                        var id = 0
                        users.forEach {
                            id = it.id
                            if (SelUser == it.username){
                                db.userDao().updateUser(it.id, 1)
                            }
                            else{db.userDao().updateUser(it.id, 0)}
                        }
                        added.forEach {
                            db.userDao().insertUser(id+1,it,0)
                            db.gameConfigDao().insertOptions(id+1,"All",0,5,0)
                            id++
                        }
                        active_user.text = "Active user: " + SelUser
                        playButton.isEnabled = true
                        setButton.isEnabled = true
                    }
                }
            }

            else->super.onActivityResult(requestCode, resultCode, data)

        }
    }
}