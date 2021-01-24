package com.example.quizapp2

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp2.db.AppDatabase

class TopScores : AppCompatActivity() {

    private lateinit var db : AppDatabase

    private lateinit var user1 : TextView
    private lateinit var user2 : TextView
    private lateinit var user3 : TextView
    private lateinit var user4 : TextView
    private lateinit var user5 : TextView

    private lateinit var score1 : TextView
    private lateinit var score2 : TextView
    private lateinit var score3 : TextView
    private lateinit var score4 : TextView
    private lateinit var score5 : TextView

    private lateinit var final_score : TextView
    private lateinit var final_image : ImageView
    private lateinit var final_hints_used : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_scores)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "quizzapp.db"
        ).allowMainThreadQueries()
            .addCallback( object: RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    //db.execSQL("INSERT INTO users(id, username, selected) VALUES(-1, 'No user', 0)")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 1','mm/dd/yy',00,false)")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 2','mm/dd/yy',00,true)")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 3','mm/dd/yy',00,false)")
                    //db.execSQL("INSERT INTO scoresInfo(username, date, score, hints) VALUES ('Default 4','mm/dd/yy',00,false)")
                } }
            ).build()

        user1 = findViewById(R.id.tv_user1)
        user2 = findViewById(R.id.tv_user2)
        user3 = findViewById(R.id.tv_user3)
        user4 = findViewById(R.id.tv_user4)
        user5 = findViewById(R.id.tv_user5)

        score1 = findViewById(R.id.tv_score1)
        score2 = findViewById(R.id.tv_score2)
        score3 = findViewById(R.id.tv_score3)
        score4 = findViewById(R.id.tv_score4)
        score5 = findViewById(R.id.tv_score5)

        var scoresDesc = db.playerDao().getScoresDesc()
        var lastScore = db.playerDao().getScoreIdDesc()[0]

        final_score = findViewById(R.id.tv_score)
        final_image = findViewById(R.id.img_score)
        final_hints_used = findViewById(R.id.tv_hints_used)
        final_score.text = "Final Score: " + lastScore.score.toString()
        if (lastScore.hints) {
            final_hints_used.text = "Hints used"
        }
        if (lastScore.score == 90) {
            final_image.setImageResource(R.drawable.result1)
        } else if (lastScore.score >= 60) {
            final_image.setImageResource(R.drawable.result2)
        } else if (lastScore.score >= 30) {
            final_image.setImageResource(R.drawable.result3)
        } else {
            final_image.setImageResource(R.drawable.result4)
        }

        if (scoresDesc.size >= 1) {
            user1.text = scoresDesc[0].name
            score1.text = scoresDesc[0].score.toString()
            if (scoresDesc[0].hints) {
                score1.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        if (scoresDesc.size >= 2) {
            user2.text = scoresDesc[1].name
            score2.text = scoresDesc[1].score.toString()
            if (scoresDesc[1].hints) {
                score2.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        if (scoresDesc.size >= 3) {
            user3.text = scoresDesc[2].name
            score3.text = scoresDesc[2].score.toString()
            if (scoresDesc[2].hints) {
                score3.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        if (scoresDesc.size >= 4) {
            user4.text = scoresDesc[3].name
            score4.text = scoresDesc[3].score.toString()
            if (scoresDesc[3].hints) {
                score4.setTextColor(Color.parseColor("#FF0000"))
            }
        }
        if (scoresDesc.size >= 5) {
            user5.text = scoresDesc[4].name
            score5.text = scoresDesc[4].score.toString()
            if (scoresDesc[4].hints) {
                score5.setTextColor(Color.parseColor("#FF0000"))
            }
        }



    }
}