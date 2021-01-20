package com.example.quizapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.quizapp2.Game.Companion.createIntent
import com.example.quizapp2.Options.Companion.createIntent
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp2.db.AppDatabase
import com.example.quizapp2.db.User
import com.facebook.stetho.Stetho
import java.security.KeyStore

private const val OPTIONS_ACTIVITY_REQUEST_CODE = 0;
private const val GAME_ACTIVITY_REQUEST_CODE = 1;
private const val USER_ACTIVITY_REQUEST_CODE = 2;
private const val SCORES_ACTIVITY_REQUEST_CODE = 3;

class MainActivity : AppCompatActivity() {

    private lateinit var  playButton : Button
    private lateinit var setButton : Button
    private lateinit var scoreButton: Button


    private lateinit var userButton : Button
    private var SelUser: String = " "
    private var UserList = mutableListOf<String>()
    //private lateinit var dao : usersDao()
    val gameModel: GameModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.play_button)
        setButton = findViewById(R.id.settings_button)
        scoreButton = findViewById(R.id.scores_button)
        userButton = findViewById(R.id.user_button)

        Stetho.initializeWithDefaults(this)

           val db = Room.databaseBuilder(
               applicationContext,
            AppDatabase::class.java,
            "quizzapp.db"
      ).allowMainThreadQueries()
          .addCallback( object: RoomDatabase.Callback(){
               override fun onCreate(db: SupportSQLiteDatabase) {
                   super.onCreate(db)
                   db.execSQL("INSERT INTO users(id, username, selected) VALUES(0, 'Default 1', 0)")
                   db.execSQL("INSERT INTO users(id, username, selected) VALUES(1, 'Default 2', 0)")
                   db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 1','mm/dd/yy',00,false)")
                   db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 2','mm/dd/yy',00,true)")
                   db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 3','mm/dd/yy',00,false)")
                   db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 4','mm/dd/yy',00,false)")
               } }
            ).build()
        var users = db.userDao().getUsers()
        var scoresInfo = db.playerDao().getInfo()

        playButton.setOnClickListener { _ ->
            startActivityForResult(
                Game.createIntent(this,gameModel.categories,gameModel.difficulty,gameModel.nquestions,gameModel.ghints),
                GAME_ACTIVITY_REQUEST_CODE
            )
        }

        setButton.setOnClickListener { _ ->
            startActivityForResult(
                Options.createIntent(this,gameModel.categories,gameModel.difficulty,gameModel.nquestions,gameModel.ghints),
                OPTIONS_ACTIVITY_REQUEST_CODE
            )
        }

        scoreButton.setOnClickListener { _->
            startActivityForResult(
            Scores.createIntent(this, gameModel.categories,gameModel.difficulty,gameModel.nquestions,gameModel.ghints),
                SCORES_ACTIVITY_REQUEST_CODE
            )
        }

        userButton.setOnClickListener { _ ->
            val tempusers = mutableListOf<String>()
            users.forEach {
                tempusers.add(it.username)
                db.userDao().deleteUser(it)
            }
            startActivityForResult(
                UserConfig.createIntent(this, SelUser, tempusers.toTypedArray()),
                USER_ACTIVITY_REQUEST_CODE
            )
            var i = 0
            UserList.forEach {
                if(it == SelUser){
                    db.userDao().insertUser(i,it,1)
                }
                else {
                    db.userDao().insertUser(i,it,0)
                }
                i++
            }
            users = db.userDao().getUsers()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            OPTIONS_ACTIVITY_REQUEST_CODE -> {
                when (resultCode) {
                    Options.RESULT_SETTINGS_CONFIG -> {
                        gameModel.categories =
                            data!!.getStringExtra(Options.EXTRA_CATEGORIES_TEXT).toString()
                        gameModel.difficulty = data.getIntExtra(Options.EXTRA_DIFFICULTY_LEVEL, 0)
                        gameModel.nquestions = data.getIntExtra(Options.EXTRA_QUESTION_NUMBERS, 5)
                        gameModel.ghints = data.getIntExtra(Options.EXTRA_HINT_OPTION, 0)
                    }
                }
            }

            USER_ACTIVITY_REQUEST_CODE->{
                when(resultCode){
                    UserConfig.RESULT_SETTINGS_CONFIG->{
                        SelUser = data!!.getStringExtra(UserConfig.EXTRA_SELECTED_USER).toString()
                        UserList = data.getStringArrayExtra(UserConfig.EXTRA_LIST_USERS)!!.toMutableList()
                    }
                }
            }
            else->super.onActivityResult(requestCode, resultCode, data)
        }
    }
}