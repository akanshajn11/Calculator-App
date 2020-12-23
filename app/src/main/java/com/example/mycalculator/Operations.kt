package com.example.mycalculator

class Operations {


    fun getFormattedResult(res: Double): Number {

        var numAfterDecimal =
            res.toString().substring(res.toString().indexOf(".") + 1, res.toString().length)
        return when {
            (numAfterDecimal) == "0" -> res.toString().substring(0, res.toString().indexOf("."))
                .toInt()
            (numAfterDecimal.length > 8) -> String.format("%.8f", res).toDouble()
            else -> res
        }
    }

    //To check if the current number already has a decimal
    public fun hasDecimal(exp: String, opArr: MutableList<String>): Boolean {
        if (opArr.isNullOrEmpty()) // when no operator in the expression
        {
            if (opArr.contains(".")) return true
        } else // when there is a operator in the expression
        {
            if (exp.substring(exp.lastIndexOf(opArr.last()) + 1, exp.length)
                    .contains(".")
            ) return true
        }
        return false
    }
}