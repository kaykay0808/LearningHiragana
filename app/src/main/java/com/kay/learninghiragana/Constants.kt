package com.kay.learninghiragana

// TODO (Create a Constants Object.)
object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    // (Create a list of questions using the Question data model class. And add all the images to the drawable folder to add it in the list.)
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1,
            "What symbol is this?",
            R.drawable.a_hiragana,
            "A",
            "I",
            "U",
            "E",
            1
        )
        questionsList.add(que1)

        // 2
        val que2 = Question(
            2,
            "What symbol is this?",
            R.drawable.i_hirigana,
            "A",
            "I",
            "U",
            "E",
            2
        )
        questionsList.add(que2)

        // 3
        val que3 = Question(
            3,
            "What symbol is this?",
            R.drawable.u_hiragana,
            "A",
            "I",
            "U",
            "E",
            3
        )
        questionsList.add(que3)

        // 4
        val que4 = Question(
            4,
            "What symbol is this?",
            R.drawable.e_hiragana,
            "A",
            "O",
            "U",
            "E",
            4
        )
        questionsList.add(que4)

        // 5
        val que5 = Question(
            5,
            "What symbol is this?",
            R.drawable.o_hiragana,
            "A",
            "O",
            "U",
            "E",
            2
        )
        questionsList.add(que5)

        return questionsList
    }
}
