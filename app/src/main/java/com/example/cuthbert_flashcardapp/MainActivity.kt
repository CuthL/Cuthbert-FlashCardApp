package com.example.cuthbert_flashcardapp

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE

        }
        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
        }



                val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                { result ->
                    val data: Intent? = result.data
                    if (data != null) {
                        val questionString = data.getStringExtra("string1")
                        val answerString = data.getStringExtra("string2")

                        flashcardQuestion.text = questionString
                        flashcardAnswer.text = answerString


                        Log.i("MainActivity", "question: $questionString")
                        Log.i("MainActivity", "answer: $answerString")
                    } else {
                        Log.i("MainActivity", "Returned null data from AddCardActivity")
                    }

                }

                val addQuestionButton = findViewById<ImageView>(R.id.add_question_button)
                addQuestionButton.setOnClickListener {
                    val intent = Intent(this, AddCardActivity::class.java)
                    resultLauncher.launch(intent)

                }
            }
        }

