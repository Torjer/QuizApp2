package com.example.quizapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.view.*

private const val OPTIONS_ACTIVITY_REQUEST_CODE = 0;
private const val GAME_ACTIVITY_REQUEST_CODE = 1;

class MainActivity : AppCompatActivity() {

    private lateinit var  playButton : Button
    private lateinit var setButton : Button


    val gameModel: GameModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.play_button)
        setButton = findViewById(R.id.settings_button)

        playButton.setOnClickListener { _ ->
            startActivityForResult(
                Game.createIntent(this,gameModel.categories,gameModel.difficulty,gameModel.nquestions,gameModel.ghints),
                GAME_ACTIVITY_REQUEST_CODE
            )
        }

        setButton.setOnClickListener { _ ->
            startActivityForResult(
                Options.createIntent(this,gameModel.difficulty),
                OPTIONS_ACTIVITY_REQUEST_CODE
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            OPTIONS_ACTIVITY_REQUEST_CODE ->
                when(resultCode){
                    Options.RESULT_SETTINGS_CONFIG -> {
                        gameModel.categories = data!!.getStringExtra(Options.EXTRA_CATEGORIES_TEXT).toString()
                        gameModel.difficulty = data.getIntExtra(Options.EXTRA_DIFFICULTY_LEVEL,0)
                        gameModel.nquestions = data.getIntExtra(Options.EXTRA_QUESTION_NUMBERS,5)
                        gameModel.ghints = data.getIntExtra(Options.EXTRA_HINT_OPTION,0)
                        Toast.makeText(this,gameModel.categories, Toast.LENGTH_SHORT).show()
                    }
                }

            else->super.onActivityResult(requestCode, resultCode, data)
        }
    }
}