package com.kay.learninghiragana

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kay.learninghiragana.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // (validate name is entered or not and launch the QuizQuestion Activity, with a toast)

        binding.btnStart.setOnClickListener {
            // Check if the edit text (ET) field is empty
            if (binding.etName.toString().isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                // If edit text is not empty then move to the next activity screen (QuizQuestionActivity).
            } else {
                // Intent is a messaging object used to request an action -> Intent is used to start an Activity
                val intent = Intent(this, QuizQuestionsActivity::class.java) // <- This means from this class (MainActivity) jumping over to QuizQuestionsActivity
                intent.putExtra(Constants.USER_NAME, binding.etName.text.toString())
                // this below will start the activity we just prepared
                startActivity(intent)
                // and we can close the current activity
                finish()
            }
        }
    }
}
