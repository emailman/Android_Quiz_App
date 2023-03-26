package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvScore = findViewById<TextView>(R.id.tv_score)
        val btnFinish = findViewById<Button>(R.id.btn_finish)

        // Get the values to be shown in this activity from
        // the prior activity
        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions =
            intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers =
            intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvScore.text =
            getString(R.string.score, correctAnswers, totalQuestions)

        // Return to the main activity
        btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}