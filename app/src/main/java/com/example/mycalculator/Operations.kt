package com.example.mycalculator

class Operations {


    fun calculate(operand1: Double, operand2: Double, operator: Char): String {
        var result: Double = 0.0
        var resultString =""
        var decimalCount:Number= 0
        if (operator == '+') {
            result = operand1 + operand2
            if(getDecimal(operand1).toInt() > getDecimal(operand2).toInt())
                decimalCount=getDecimal(operand1)
            else
                decimalCount=getDecimal(operand2)
            resultString= String.format("%.${decimalCount}f",result)
        }

        return resultString
    }

    //To get number of digits after decimal
    fun getDecimal(num : Double): Number {
       var number = num.toString()
       var index= number.indexOf(".")
       var decNum= number.substring(index+1)
       return decNum.length
    }
}