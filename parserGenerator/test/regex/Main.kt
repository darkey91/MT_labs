//package regex
//
//import generated.calculator.CalculatorLexer
//import generated.calculator.Token
//
//fun main() {
//    val calcLexer = CalculatorLexer(("5 * 5 + 1 + 3 * (5 + 0)"))
//    while (calcLexer.curToken != Token.CALCULATOR_END_TOKEN) {
//        println(calcLexer.curToken)
//        calcLexer.nextToken()
//    }
//}
