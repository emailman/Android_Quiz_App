package com.example.quizapp

object Constants {
    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val CORRECT_ANSWERS = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val question1 = Question(
            1, "What country's flag is this?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionsList.add(question1)

        val question2 = Question(
            1, "What country's flag is this?",
            R.drawable.ic_flag_of_belgium,
            "Belgium",
            "Brazil",
            "Bolivia",
            "Bosnia",
            1
        )
        questionsList.add(question2)

        val question3 = Question(
            1, "What country's flag is this?",
            R.drawable.ic_flag_of_india,
            "India",
            "Italy",
            "Israel",
            "Zambia",
            1
        )
        questionsList.add(question3)


        return questionsList
    }
}