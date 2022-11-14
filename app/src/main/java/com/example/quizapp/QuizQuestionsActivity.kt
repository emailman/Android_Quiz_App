package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var currentPosition = 1
    private lateinit var questionsList: ArrayList<Question>
    private var selectedOptionPosition = 0
    private lateinit var userName: String
    private var correctAnswers = 0

    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var ivImage: ImageView
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME).toString()

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)

        questionsList = Constants.getQuestions()

        progressBar.max = questionsList.size

        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionsView()
        val question = questionsList[currentPosition - 1]
        ivImage.setImageResource(question.image)
        progressBar.progress = currentPosition
        tvProgress.text = getString(R.string.x_of_y, currentPosition, progressBar.max)
        tvQuestion.text = question.question
        tvOptionOne.text = question.option1
        tvOptionTwo.text = question.option3
        tvOptionThree.text = question.option3
        tvOptionFour.text = question.option4

        if (currentPosition == questionsList.size)
            btnSubmit.text = getString(R.string.finish)
        else
            btnSubmit.text = getString(R.string.submit)
    }

    private fun defaultOptionsView() {
        // Restore all the options to default
        val options = ArrayList<TextView>()
        options.add(tvOptionOne)
        options.add(tvOptionTwo)
        options.add(tvOptionThree)
        options.add(tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8389"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionsView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        // Set the selected option color, typeface, border
        selectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_option_one ->
                selectedOptionsView(tvOptionOne, 1)
            R.id.tv_option_two ->
                selectedOptionsView(tvOptionTwo, 2)
            R.id.tv_option_three ->
                selectedOptionsView(tvOptionThree, 3)
            R.id.tv_option_four ->
                selectedOptionsView(tvOptionFour, 4)
            R.id.btn_submit -> {
                if (selectedOptionPosition == 0) {
                    currentPosition++

                    when{
                        currentPosition <= questionsList.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = questionsList[currentPosition - 1]
                    if (question.correctAnswer != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        correctAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (currentPosition == questionsList.size) {
                        btnSubmit.text = getString((R.string.finish))
                    } else {
                        btnSubmit.text = getString(R.string.go_to_next_question)
                    }

                    selectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOptionOne.background = ContextCompat.getDrawable(this,
                drawableView)
            }
            2 -> {
                tvOptionTwo.background = ContextCompat.getDrawable(this,
                    drawableView)
            }
            3 -> {
                tvOptionThree.background = ContextCompat.getDrawable(this,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour.background = ContextCompat.getDrawable(this,
                    drawableView
                )
            }
        }
    }
}
