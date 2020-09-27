package com.example.mycalculator

class Operations {


    fun calculate(operand1: Double, operand2: Double, operator: Char): String {
        var result: Double = 0.0

     result=  when(operator){
           '+' -> operand1 + operand2
            '-' -> operand1 - operand2
            'x' -> operand1 * operand2
            '/' -> operand1 / operand2
         else -> 0.0
     }

        return getFormattedResult(operand1, operand2, result)
    }


    private fun getFormattedResult(operand1: Double, operand2: Double, result: Double): String {
        var decimalCount: Number = if (getDecimal(operand1).toInt() > getDecimal(operand2).toInt()) getDecimal(operand1) else getDecimal(operand2)
        return String.format("%.${decimalCount}f", result)
    }

    //To get number of digits after decimal
    private fun getDecimal(num: Double): Number {
        return num.toString().substring(num.toString().indexOf(".") + 1).length
    }
}