package com.kay.learninghiragana

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kay.learninghiragana.databinding.ActivityMainBinding
import com.kay.learninghiragana.databinding.ActivityQuizQuestionsBinding


class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    // (A global variables for current position and questions list.)
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null


    // (A global variables for selected option.)
    private var mSelectedOptionPosition: Int = 0

    private lateinit var binding: ActivityQuizQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mQuestionsList = Constants.getQuestions()

        setQuestion()

        // make the buttons do something when clicked (to make "this" work we need to add View.OnClickListener to the class)
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(binding.tvOptionOne, 1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(binding.tvOptionTwo, 2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(binding.tvOptionThree, 3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(binding.tvOptionFour, 4)
            }
        }
    }

    // (Create a function to set the question in the UI components which we have done earlier the onCreate method. And make some of the variables global which we will be using later.)
    // Function 2
    private fun setQuestion() {
        // (Setting the question in the UI from the list.)
        mCurrentPosition = 1 // Default and the first question position
        val question =
            mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.

        // set everything to default before clicking the buttons
        defaultOptionsView()

        binding.progressBar.progress =
            mCurrentPosition // Setting the current progress in the progressbar using the position of question
        binding.tvProgress.text =
            "$mCurrentPosition" + "/" + binding.progressBar.max// Setting up the progress text

        // Now set the current question and the options in the UI
        binding.tvQuestion.text = question!!.question
        binding.ivImageQuestion.setImageResource(question.image)

        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
    }

    // TODO (Create a function for view for highlighting the selected option.)
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)// <-- set to default
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
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT // <-- set to default
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }
}