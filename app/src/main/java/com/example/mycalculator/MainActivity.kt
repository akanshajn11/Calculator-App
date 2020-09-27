package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

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
    private lateinit var buttonPoint: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonAC: Button

    private lateinit var textCalculation: TextView
    private lateinit var textResult: TextView

    private val operations: Operations = Operations()
    private var operand1: Double? = null
    private var operand2: Double? = null
    private var operator: Char? = null
    private var hasDecimal: Boolean = false
    private var currentNumber: String = ""


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
        buttonPoint = findViewById(R.id.button_point)
        buttonPlus = findViewById(R.id.button_plus)
        buttonMinus = findViewById(R.id.button_minus)
        buttonMultiply = findViewById(R.id.button_multiply)
        buttonDivide = findViewById(R.id.button_divide)
        buttonAC = findViewById(R.id.button_AC)
        textCalculation = findViewById(R.id.text_calculation)
        textResult = findViewById(R.id.text_result)

        numberClick() //0 to 9
        decimal() // decimal operation
        add() // add '+' operation
        subtract() // add '-' operation
        multiply() // add 'x' operation
        divide()  // add '/' operation
        allClear() //clear all text
        equals() // add '=' operation
    }

    private fun numberClick() {
        button0.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button0.text
            currentNumber = currentNumber.plus(button0.text)
        }

        button1.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button1.text
            currentNumber = currentNumber.plus(button1.text.toString())
        }

        button2.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button2.text
            currentNumber = currentNumber.plus(button2.text)
        }

        button3.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button3.text
            currentNumber = currentNumber.plus(button3.text)
        }

        button4.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button4.text
            currentNumber = currentNumber.plus(button4.text)
        }

        button5.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button5.text
            currentNumber = currentNumber.plus(button5.text)
        }

        button6.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button6.text
            currentNumber = currentNumber.plus(button6.text)
        }

        button7.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button7.text
            currentNumber = currentNumber.plus(button7.text)
        }

        button8.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button8.text
            currentNumber = currentNumber.plus(button8.text)
        }

        button9.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button9.text
            currentNumber = currentNumber.plus(button9.text)
        }
    }

    private fun decimal() {
        buttonPoint.setOnClickListener() {
            if (!hasDecimal) {
                textCalculation.text = textCalculation.text.toString() + buttonPoint.text
                currentNumber = currentNumber.plus(buttonPoint.text)
                hasDecimal = true
            }
        }
    }

    private fun add() {
        buttonPlus.setOnClickListener() {
            addOperation(buttonPlus.text.toString(), '+')
        }
    }

    private fun subtract() {
        buttonMinus.setOnClickListener() {
            addOperation(buttonMinus.text.toString(), '-')
        }
    }

    private fun multiply() {
        var test = buttonMultiply.text.toString()
        buttonMultiply.setOnClickListener() {
            addOperation(buttonMultiply.text.toString(), 'x')
        }
    }

    private fun divide() {
        buttonDivide.setOnClickListener() {
            addOperation(buttonDivide.text.toString(), '/')
        }

    }

    private fun allClear() {
        buttonAC.setOnClickListener() {
            textCalculation.text = ""
            textResult.text = ""
            operand1 = null
            operand2 = null
            operator = null
            hasDecimal = false
            currentNumber = ""
        }
    }

    private fun addOperation(opText: String, op: Char) {

        if (operand1 == null)
            operand1 = currentNumber.toDouble()
        else if (operand1 != null && operand2 == null && currentNumber != "") {
            calculate()
        }

        textCalculation.text = textCalculation.text.toString() + opText
        currentNumber = ""
        hasDecimal = false
        operator = op
    }


    private fun equals() {
        button_equals.setOnClickListener() {
            calculate()
        }
    }

    private fun calculate() {
        operand2 = currentNumber.toDouble()
        var result: String = operations.calculate(operand1!!, operand2!!, operator!!)
        textResult.text = result
        operand1 = result.toDouble()
        operand2 = null
        currentNumber = ""
    }
}

