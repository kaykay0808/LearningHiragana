package com.kay.learninghiragana

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kay.learninghiragana.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    // (A global variables for current position and questions list.)
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null

    // (A global variables for selected option.)
    private var mSelectedOptionPosition: Int = 0
    // A variable for how many times we had the correct answer
    private var mCorrectAnswer: Int = 0
    private var mUserName: String? = null

    private lateinit var binding: ActivityQuizQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME) // <- getting the username that we stored

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        // make the buttons do something when clicked (to make "this" work we need to add View.OnClickListener to the class)
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    // This function is highlighting the selected option.
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(binding.tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(binding.tvOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(binding.tvOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(binding.tvOptionFour, 4)
            }
            // Adding a click event for submit button, and change the questions and check the selected answers.
            R.id.btn_submit -> {
                // if the selected position is 0 (that means you have not choose anything
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        // when question number is lesser or equal to total question size.
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion() // This will go to the next question
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    // if the user have selected an option
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    // if we selected the wrong option
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        highlightAnswer(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswer++
                    }
                    highlightAnswer(question.correctAnswer, R.drawable.correct_option_border_bg)

                    // check if we are on the last question
                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit.text = "FINISH"
                    } else {
                        binding.btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    // we need to asign this to go to the next question
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    // A function for answer view which is used to highlight the answer is wrong or right.
    private fun highlightAnswer(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            2 -> binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3 -> binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            4 -> binding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    // (Create a function to set the question in the UI components)
    // Function 2
    private fun setQuestion() {
        // (Setting the question in the UI from the list.)

        val question = mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.( The index always start in 0 that is why we have -1)

        // set everything to default before clicking the buttons
        defaultOptionsView()

        // Check here if the position of question is last, then change the button text of the button to finish.
        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "SUBMIT"
        }

        binding.progressBar.progress = mCurrentPosition // Setting the current progress in the progressbar using the position of question
        binding.tvProgress.text = "$mCurrentPosition / ${binding.progressBar.max}" // Setting up the progress text

        // set the current question and the options in the UI
        binding.tvQuestion.text = question!!.question
        binding.ivImageQuestion.setImageResource(question.image)

        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
    }

    // (Create a function for view for highlighting the selected option.)
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD) // <-- set to default
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun defaultOptionsView() {
        // Create an Arraylist of the options text view
        val options = ArrayList<TextView>()

        // add the options to your arraylist
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        // Create a for loop that go through all your options
        for (option in options) {
            // set the options to default color (The text color)
            option.setTextColor(Color.parseColor("#7A8089")) // <-- set the text color to default
            option.typeface =
                Typeface.DEFAULT // <-- set to default // <-- set the typeFace to default (font, style, size)
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            ) // <-- set the default background
        }
    }
}
