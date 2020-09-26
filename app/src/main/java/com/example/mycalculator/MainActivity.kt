package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var textCalculation: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        textCalculation = findViewById(R.id.text_calculation)

        numberClick()
    }

    fun numberClick() {
        button0.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button0.text
        }

        button1.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button1.text
        }

        button2.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button2.text
        }

        button3.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button3.text
        }

        button4.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button4.text
        }

        button5.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button5.text
        }

        button6.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button6.text
        }

        button7.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button7.text
        }

        button8.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button8.text
        }

        button9.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button9.text
        }
    }
}

