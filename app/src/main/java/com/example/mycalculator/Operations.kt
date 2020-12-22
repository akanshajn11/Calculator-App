package com.example.mycalculator

class Operations {

    fun calculate(operand1: Double, operand2: Double, operator: Char): String {
        var result: Double = 0.0

        result = when (operator) {
            '+' -> operand1 + operand2
            '-' -> operand1 - operand2
            'x' -> operand1 * operand2
            '/' -> operand1 / operand2
            else -> 0.0
        }

        return getFormattedResult(operand1, operand2, result)
    }


    private fun getFormattedResult(operand1: Double, operand2: Double, result: Double): String {
        var decimalCount: Number =
            if (getDecimal(operand1).toInt() > getDecimal(operand2).toInt()) getDecimal(operand1) else getDecimal(
                operand2
            )
        return String.format("%.${decimalCount}f", result)
    }

    //To get number of digits after decimal
    private fun getDecimal(num: Double): Number {
        return num.toString().substring(num.toString().indexOf(".") + 1).length
    }

    //Get Integer value for result of type x.0
    public fun getInteger(res: Double): Number {
        if (res.toString()
                .substring(res.toString().indexOf(".") + 1, res.toString().length) == "0"
        ) {
            return res.toString().substring(0, res.toString().indexOf(".")).toInt()
        }
        return res
    }

    //To check if the current number already has a decimal
    public fun hasDecimal(exp: String, opArr: MutableList<String>): Boolean {
        if (opArr.isNullOrEmpty()) // when no operator in the expression
        {
            if (opArr.contains(".")) return true
        } else // when there is a operator in the expression
        {
            if (exp.substring(exp.lastIndexOf(opArr.last()) + 1, exp.length).contains(".")) return true
        }
        return false
    }
}