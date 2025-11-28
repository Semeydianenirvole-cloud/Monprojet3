package com.example.monprojet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (data != null) { // Check that we have data returned
            val question = intent.getStringExtra("question_clef")
            val answer = intent.getStringExtra("reponse_clef")

            //log the value of the stings for easier debugging
            Log.i("MainActivity", "string1: $question")
            Log.i("MainActivity", "string2: $answer")
            findViewById<TextView>(R.id.milie).text = question
            findViewById<TextView>(R.id.milie2).text = answer
        } else {
            Log.i("MainActivity","Returned null data from MainActivity2")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val questionText = findViewById<TextView>(R.id.milie)
        val answertext = findViewById<TextView>(R.id.milie2)
        val icon = findViewById<ImageView>(R.id.imageView)

           icon.setOnClickListener {
               val intent = Intent(this, MainActivity2::class.java)
               resultLauncher.launch(intent)
           }
        questionText.setOnClickListener {
            questionText.visibility = View.INVISIBLE
            answertext.visibility = View.VISIBLE
        }
        answertext.setOnClickListener {
            questionText.visibility = View.VISIBLE
            answertext.visibility = View.INVISIBLE
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}