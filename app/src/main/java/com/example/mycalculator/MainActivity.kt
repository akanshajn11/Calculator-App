package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

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
    private lateinit var buttonDelete: Button

    private lateinit var textCalculation: TextView
    private lateinit var textResult: TextView

    private val operations: Operations = Operations()
    private var operand1: Double? = null
    private var operand2: Double? = null
    private var operator: Char? = null
    private var currentNumber: String = ""
    private var dynamicResult: String = ""
    private var opArray = mutableListOf<String>()

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
        buttonDelete = findViewById(R.id.button_delete)
        textCalculation = findViewById(R.id.text_calculation)
        textResult = findViewById(R.id.text_result)

        numberClick() //0 to 9
        decimal() // decimal operation
        add() // add '+' operation
        subtract() // add '-' operation
        multiply() // add 'x' operation
        divide()  // add '/' operation
        allClear() //clear all text
        delete() // delete last character
        equals() // add '=' operation
    }

    private fun numberClick() {
        button0.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button0.text
            evaluateExpression(textCalculation.text.toString())
        }

        button1.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button1.text
            evaluateExpression(textCalculation.text.toString())
        }

        button2.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button2.text
            evaluateExpression(textCalculation.text.toString())
        }

        button3.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button3.text
            evaluateExpression(textCalculation.text.toString())
        }

        button4.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button4.text
            evaluateExpression(textCalculation.text.toString())
        }

        button5.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button5.text
            evaluateExpression(textCalculation.text.toString())
        }

        button6.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button6.text
            evaluateExpression(textCalculation.text.toString())
        }

        button7.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button7.text
            evaluateExpression(textCalculation.text.toString())
        }

        button8.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button8.text
            evaluateExpression(textCalculation.text.toString())
        }

        button9.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button9.text
            evaluateExpression(textCalculation.text.toString())
        }
    }

    private fun decimal() {
        buttonPoint.setOnClickListener() {
            if (!operations.hasDecimal(textCalculation.text.toString(), opArray)) {
                textCalculation.text = textCalculation.text.toString() + buttonPoint.text
            }
        }
    }

    private fun add() {
        buttonPlus.setOnClickListener() {
            addOperation(buttonPlus.text.toString(), '+')
            opArray.add(buttonPlus.text.toString())
        }
    }

    private fun subtract() {
        buttonMinus.setOnClickListener() {
            addOperation(buttonMinus.text.toString(), '-')
            opArray.add(buttonMinus.text.toString())
        }
    }

    private fun multiply() {
        buttonMultiply.setOnClickListener() {
            addOperation("*", '*')
            opArray.add("*")
        }
    }

    private fun divide() {
        buttonDivide.setOnClickListener() {
            addOperation(buttonDivide.text.toString(), '/')
            opArray.add(buttonDivide.text.toString())
        }
    }

    private fun allClear() {
        buttonAC.setOnClickListener() {
            textCalculation.text = ""
            textResult.text = ""
            operand1 = null
            operand2 = null
            operator = null
            currentNumber = ""
            dynamicResult = ""
        }
    }

    private fun delete() {
        buttonDelete.setOnClickListener() {
            var lastChar = textCalculation.text.toString()
                .substring(textCalculation.text.toString().length - 1)
            textCalculation.text = textCalculation.text.toString()
                .substring(0, textCalculation.text.toString().length - 1)

            evaluateExpression(textCalculation.text.toString())

            when (lastChar) {
                in listOf<String>("+", "-", "*", "/") -> opArray.removeLast()
            }
        }
    }

    private fun addOperation(opText: String, op: Char) {
        operator = op
        textCalculation.text = textCalculation.text.toString() + opText
    }

    private fun evaluateExpression(exp: String) {

        if (exp.isNullOrEmpty())
            textResult.text = ""
        else {
            var expr: String = if (!(exp.last().isDigit()))
                exp.substring(0, exp.length - 1)
            else
                exp
            val eval = ExpressionBuilder(expr).build()
            val res = eval.evaluate()
            val resFormatted: Number
            resFormatted = operations.getFormattedResult(res)
            if (operator != null)
                textResult.text = resFormatted.toString()
        }
    }

    private fun equals() {
        button_equals.setOnClickListener() {
            evaluateExpression(textCalculation.text.toString())
        }
    }
}

