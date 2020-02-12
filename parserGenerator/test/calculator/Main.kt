package calculator

import generated.calculator.CalculatorLexer
import generated.calculator.CalculatorParser
import generated.calculator.Token

fun main() {
    val calcParser = CalculatorParser()
    val root = calcParser.parse("1--1")
    println(root.value)

}
