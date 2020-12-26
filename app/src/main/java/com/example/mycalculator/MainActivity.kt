package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Character.isDigit

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
    private lateinit var buttonBraces: Button

    private lateinit var textCalculation: TextView
    private lateinit var textResult: TextView

    private val operations: Operations = Operations()
    private var opArray = mutableListOf<Char>()
    private var lastBracket = ""


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
        buttonBraces = findViewById(R.id.button_braces)
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
        bracket() // add '()' operation
    }

    private fun numberClick() {
        button0.setOnClickListener() {
            if (textCalculation.text.toString().isNullOrEmpty() || (!textCalculation.text.toString()
                    .isNullOrEmpty() && textCalculation.text.toString().last() != '/')
            ) // to avoid division by 0
            {
                textCalculation.text = textCalculation.text.toString() + button0.text
                textResult.text = operations.evaluateExpression(textCalculation.text.toString())
            }
        }

        button1.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button1.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button2.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button2.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button3.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button3.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button4.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button4.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button5.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button5.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button6.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button6.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button7.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button7.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button8.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button8.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }

        button9.setOnClickListener() {
            textCalculation.text = textCalculation.text.toString() + button9.text
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }
    }

    private fun decimal() {
        buttonPoint.setOnClickListener() {
            if (operations.isValidDecimal(textCalculation.text.toString(), opArray))
                textCalculation.text = textCalculation.text.toString() + buttonPoint.text
        }
    }

    private fun add() {
        buttonPlus.setOnClickListener() {
            addOperation(buttonPlus.text.toString())
        }
    }

    private fun subtract() {
        buttonMinus.setOnClickListener() {
            addOperation(buttonMinus.text.toString())
        }
    }

    private fun multiply() {
        buttonMultiply.setOnClickListener() {
            addOperation(buttonMultiply.text.toString())
        }
    }

    private fun divide() {
        buttonDivide.setOnClickListener() {
            addOperation(buttonDivide.text.toString())
        }
    }

    private fun bracket() {
        buttonBraces.setOnClickListener() {
            var res: Array<Any> =
                operations.addBracket(textCalculation.text.toString(), lastBracket)
            textCalculation.text = res[0].toString()
            lastBracket = res[1].toString()
            textResult.text = operations.evaluateExpression(res[0].toString())
        }
    }

    private fun allClear() {
        buttonAC.setOnClickListener() {
            textCalculation.text = ""
            textResult.text = ""
        }
    }

    private fun delete() {
        buttonDelete.setOnClickListener() {

            when (textCalculation.text.toString()
                .substring(textCalculation.text.toString().length - 1)) {
                in listOf("+", "-", "*", "/") -> opArray.removeLast()
                in listOf("(", ")") -> lastBracket = operations.getLastBracket(
                    textCalculation.text.toString()
                        .substring(0, textCalculation.text.toString().length - 1)
                )
            }
            textCalculation.text = textCalculation.text.toString()
                .substring(0, textCalculation.text.toString().length - 1)
            textResult.text = operations.evaluateExpression(
                textCalculation.text.toString()
            )
        }
    }

    private fun addOperation(opText: String) {
        textCalculation.text = operations.addOperation(textCalculation.text.toString(), opText)
        when (opText) {
            in listOf("+", "-", "x", "/") -> opArray.add(opText.first())
        }
    }

    private fun equals() {
        button_equals.setOnClickListener() {
            textResult.text = operations.evaluateExpression(textCalculation.text.toString())
        }
    }
}

