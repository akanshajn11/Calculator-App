package com.example.mycalculator

import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Character.isDigit

class Operations {

    private fun getFormattedResult(res: Double): String {

        var numAfterDecimal =
            res.toString().substring(res.toString().indexOf(".") + 1, res.toString().length)

        if (numAfterDecimal.length > 8) {

            return if (String.format("%.8f", res).last() == '0')
                removeEndingZeroes(String.format("%.8f", res))
            else
                String.format("%.8f", res)
        }

        if (numAfterDecimal.last() == '0')
            return removeEndingZeroes(res.toString())

        return res.toString()
    }

    private fun removeEndingZeroes(exp: String): String {

        var expr: String = exp

        for (x in expr.toString()
            .substring(expr.toString().indexOf("."), expr.toString().length).reversed()) {

            if (x == '0') {
                expr = expr.substring(0, expr.length - 1)
                continue
            }

            if (x == '.')
                return expr.substring(0, expr.length - 1)


            if (x.toInt() > 0)
                return expr


        }
        return expr
    }

    //To check if the current number already has a decimal
    private fun hasDecimal(exp: String, opArr: List<Char>): Boolean {
        if (opArr.isNullOrEmpty()) // when no operator in the expression
        {
            if (exp.contains("."))
                return true
        } else // when there is a operator in the expression
        {
            if (exp.substring(
                    exp.lastIndexOf(opArr.last()) + 1,
                    exp.length
                ) // considering the number after last operator in exp
                    .contains(".")
            )
                return true
        }
        return false
    }

    private fun checkBalancedBracket(exp: String): String { // return an expression valid for evaluation
        var stack =
            ArrayDeque<Char>() //opening bracket will be pushed here, they will be popped off for every occurrence of closing bracket
        var indexes = mutableListOf<Int>() // list of indices of opening brackets in expression
        var expr: String = exp  // storing expression in a mutable string

        //below loop performs the push and pop logic
        for (x in expr.indices) {
            if (expr[x] == '(') {
                stack.add(expr[x]) //pushing element
                indexes.add(x)  //pushing index of element
                continue
            }
            if (expr[x] == ')') {
                stack.removeLast()  //popping off element
                indexes.removeLast() //popping off index
            }
        }

        if (stack.isEmpty()) //it will be true when brackets are balanced
            return expr

        // below logic would convert the expression as valid for evaluation
        for (x in indexes.asReversed()) {

            expr =
                if (x == 0)  // to remove unbalanced opening bracket at index 0, ex : (2+3  will be converted to 2+3
                    expr.replaceFirst("(", "", ignoreCase = false)
                else if (x == indexes.last() && x < expr.length - 1 && isDigit(expr[expr.length - 1])) // to add a balancing closing bracket for last unbalanced opening bracket, ex: 2x(3+1 will be converted to 2x(3+1)
                    expr.plus(')')
                else  // all other in between occurrences
                    expr.substring(0, x) + "" + expr.substring(x + 1)
        }

        return expr
    }

    //count no of occurrences of a char in string
    private fun countOccurrences(s: String, ch: Char): Int {
        return s.filter { it == ch }.count()
    }

    fun evaluateExpression(exp: String): String {  // most important function for the application, it will evaluate the expression typed in by the user

        if (exp.isNullOrEmpty())
            return ""

        var expr: String = exp // storing expression in mutable string

        /*Handling operators at the end of expression, if any*/
        if (!isDigit(expr.last()))
            expr = handleIncompleteExp(expr)
        if (expr.isNullOrEmpty())
            return ""

        /*Handling Brackets*/
        if (expr.contains("("))// minimum condition for an expression to have bracket, always there would be opening bracket first
            expr = checkBalancedBracket(expr)
        if (expr.isNullOrEmpty())
            return ""


        /*Handling multiplication by replacing 'x' with '*' */
        if (expr.contains("x"))
            expr = expr.replace("x", "*", ignoreCase = false)

        /*Now we have a valid expression, so we would evaluate */
        val eval = ExpressionBuilder(expr).build()
        val res = eval.evaluate()

        /*Now we would format the decimal in result*/
        return getFormattedResult(res)
    }

    private fun handleIncompleteExp(exp: String): String {

        var expr: String = exp

        for (x in exp.reversed()) {

            if (!isDigit(x)) {
                expr = expr.substring(0, expr.length - 1)
                continue
            }

            if (isDigit(x))
                return expr
        }
        return expr
    }

    fun addBracket(exp: String, lastBracket: String): Array<Any> {

        //storing values in mutable string
        var expr: String = exp

        if (expr.isNullOrEmpty())
            return arrayOf(expr.plus("("), "(")

        if (expr.last() == '(')
            return arrayOf(expr.plus('('), lastBracket)

        if (expr.last() == ')') {
            return if (countOccurrences(expr, ')') < countOccurrences(expr, '('))
                arrayOf(expr.plus(')'), ')')
            else
                arrayOf(expr, ')')
        }

        if (listOf('+', '-', 'x', '/').any { it == expr.last() }) {
            return when (lastBracket) {
                "(" -> arrayOf(expr, lastBracket)
                ")" -> arrayOf(expr.plus("("), "(")
                else -> arrayOf(expr.plus("("), "(")
            }
        }

        if (expr.last() == '.')
            return arrayOf(expr, lastBracket)

        if (isDigit(expr.last())) {
            return when (lastBracket) {
                "(" -> arrayOf(expr.plus(")"), ")")
                ")" -> arrayOf(expr.plus("("), "(")
                else -> arrayOf(expr.plus("("), "(")
            }
        }
        return arrayOf(expr, lastBracket)
    }

    fun addOperation(exp: String, opText: String): String {

        var expr: String = exp

        if (expr.isNullOrEmpty()) {
            return if (opText == "-")
                opText
            else
                expr
        }

        if (expr.last() == '(') {
            return if (opText == "-")
                expr.plus(opText)
            else
                expr
        }

        if (expr.last() == ')')
            return expr.plus(opText)

        if (listOf('x', '/').any { it == exp.last() } && opText == "-")
            return expr.plus(opText)

        if (expr.last() == '.')
            return expr

        if (isDigit(expr.last()))
            return expr.plus(opText)

        return expr

    }

    fun isValidDecimal(exp: String, opArray: List<Char>): Boolean {

        if (exp.isNullOrEmpty())
            return true

        var expr: String = exp

        if (expr.last() == '(')
            return true

        if (expr.last() == ')')
            return true

        if (listOf('+', '-', 'x', '/').any { it == expr.last() })
            return true

        if (expr.last() == '.')
            return false

        if (isDigit(expr.last()) && !hasDecimal(expr, opArray))
            return true

        return false

    }

    fun getLastBracket(exp: String): String {
        if (exp.isNullOrEmpty())
            return ""

        var lastindexO: Int = -1
        var lastIndexC: Int = -1

        if (exp.any { it == '(' })
            lastindexO = exp.lastIndexOf('(')

        if (exp.any { it == ')' })
            lastIndexC = exp.lastIndexOf(')')

        if (lastIndexC > lastindexO)
            return ")"

        if (lastindexO > lastIndexC)
            return "("

        return ""
    }
}

