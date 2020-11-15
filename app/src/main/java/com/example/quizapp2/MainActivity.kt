package com.example.quizapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.view.*

private const val OPTIONS_ACTIVITY_REQUEST_CODE = 0;

class MainActivity : AppCompatActivity() {

    private lateinit var  playButton : Button
    private lateinit var setButton : Button
    private var categories : String = "Hola"
    private var difficulty : Int = 0
    private var nquestions : Int = 0
    private var ghints : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.play_button)
        setButton = findViewById(R.id.settings_button)

        playButton.setOnClickListener { _ ->
        }

        setButton.setOnClickListener { _ ->
            startActivityForResult(
                Options.createIntent(
                    this,
                    categories,
                    difficulty,
                    nquestions,
                    ghints
                ),
                OPTIONS_ACTIVITY_REQUEST_CODE
            )
        }

    }
}