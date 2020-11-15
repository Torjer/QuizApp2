package com.example.quizapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*

private const val OPTIONS_ACTIVITY_REQUEST_CODE = 0;

class MainActivity : AppCompatActivity() {

    private lateinit var  playButton : Button
    private lateinit var setButton : Button

    private lateinit var categories : String
    private var difficulty : Int = 0
    private var nquestions : Int = 0
    private var ghints : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.play_button)
        setButton = findViewById(R.id.settings_button)

        playButton.setOnClickListener { _ ->
            Toast.makeText(this, difficulty.toString(),Toast.LENGTH_SHORT).show()
        }

        setButton.setOnClickListener { _ ->
            startActivityForResult(
                Options.createIntent(this),
                OPTIONS_ACTIVITY_REQUEST_CODE
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            OPTIONS_ACTIVITY_REQUEST_CODE ->
                when(resultCode){
                    Options.RESULT_SETTINGS_CONFIG -> {
                        categories = data!!.getStringExtra(Options.EXTRA_CATEGORIES_TEXT).toString()
                        difficulty = data.getIntExtra(Options.EXTRA_DIFFICULTY_LEVEL,0)
                        nquestions = data.getIntExtra(Options.EXTRA_QUESTION_NUMBERS,5)
                        ghints = data.getBooleanExtra(Options.EXTRA_HINT_OPTION,false)
                    }
                    else-> null
                }
            else->super.onActivityResult(requestCode, resultCode, data)
        }
    }
}