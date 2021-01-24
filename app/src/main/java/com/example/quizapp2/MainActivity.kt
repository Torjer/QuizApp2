package com.example.quizapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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

    private lateinit var db : AppDatabase
    private lateinit var users : List<User>
    private lateinit var scoresInfo : List<Player>
    private lateinit var gConfig : List<GameConfig>
    //private lateinit var dao : usersDao()
    val gameModel: GameModel by viewModels()


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
                   //db.execSQL("INSERT INTO users(id, username, selected) VALUES(-1, 'No user', 0)")
                   //db.execSQL("INSERT INTO scoresInfo(userid,username, date, score, hints) VALUES (1,'Default 1','mm/dd/yy',90,false)")
                   //db.execSQL("INSERT INTO scoresInfo(userid,username, date, score, hints) VALUES (2,'Default 2','mm/dd/yy',70,true)")
                   //db.execSQL("INSERT INTO scoresInfo(userid,username, date, score, hints) VALUES (3,'Default 3','mm/dd/yy',100,false)")
                   //db.execSQL("INSERT INTO scoresInfo(userid,username, date, score, hints) VALUES (4,'Default 4','mm/dd/yy',00,false)")
               } }
            ).build()
     //db.playerDao().insertInfo(1,"Default 1","2021/08/01",2,false)
     //db.playerDao().insertInfo(2,"Default 1","2021/09/01",1,true)
     //db.playerDao().insertInfo(3,"Default 1","2021/01/01",5,true)
     //db.playerDao().insertInfo(4,"Default 1","2020/08/01",4,false)
     //db.playerDao().insertInfo(5,"Default 2","2021/05/17",2,true)
     //db.playerDao().insertInfo(6,"Default 2","2021/05/30",560,true)
     //db.playerDao().insertInfo(7,"Default 3","2021/05/23",40,false)
     //db.playerDao().insertInfo(8,"Default 4","2020/05/12",60,false)
        users = db.userDao().getUsers()
        scoresInfo = db.playerDao().getInfo()
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
                Game.createIntent(this,options.category,options.difficulty,options.eqn,options.hints, db.userDao().getUsers(SelUser).id),
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

        scoreButton.setOnClickListener { _ ->
            scoresInfo = db.playerDao().getInfo()
            var tempId = mutableListOf<String>()
            var tempName = mutableListOf<String>()
            var tempDate = mutableListOf<String>()
            var tempScore = mutableListOf<String>()
            var tempHints = mutableListOf<String>()
            scoresInfo.forEach{
                tempId.add(it.userid.toString())
                tempName.add(it.name)
                tempDate.add(it.gamedate)
                tempScore.add(it.score.toString())
                tempHints.add(it.hints.toString())
            }
            startActivityForResult(
                Scores.createIntent(this, tempId.toTypedArray(), tempName.toTypedArray(), tempDate.toTypedArray(), tempScore.toTypedArray(), tempHints.toTypedArray()),
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
                                db.gameConfigDao().updateOptions(it.id ,gameModel.categories,gameModel.difficulty,gameModel.nquestions,gameModel.ghints, it.active)
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
                        }
                        added.forEach {
                            db.userDao().insertUser(id+1,it,1)
                            db.gameConfigDao().insertOptions(id+1,"All",0,5,0,0)
                            id++
                        }
                        users.forEach {
                            if (SelUser == it.username){
                                db.userDao().updateUser(it.id,1)
                            }
                            else{db.userDao().updateUser(it.id,0)}
                        }

                        if(db.gameConfigDao().getSelectedOptions(db.userDao().getUsers(SelUser).id).active==1) {
                            val dialog = AlertDialog.Builder(this)
                            with(dialog) {
                                dialog.setTitle("Do you want to continue your last game?")
                                setPositiveButton("Yes") { dialog, which ->
                                    dialog.dismiss()
                                }
                                setNegativeButton("No") { dialog, which ->
                                    db.gameConfigDao().updateOptions(db.userDao().getUsers(SelUser).id, 0)
                                }
                                show()
                            }
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