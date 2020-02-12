import generator.ParserGenerator
import generator.GrammarBuilder
import generator.LexerGenerator

fun main() {
    val grammarCalcFileName = "test/calculator/Calculator.gram"
    val grammarCalc = GrammarBuilder(grammarCalcFileName).build()
    val lexerCalc = LexerGenerator(grammarCalc).generate()
    val generatorCalc = ParserGenerator(grammarCalc).generate()

    val grammarRegexName = "test/regex/Regex.gram"
    val grammarReg = GrammarBuilder(grammarRegexName).build()
    val lexerReg = LexerGenerator(grammarReg).generate()
    val generatorReg = ParserGenerator(grammarReg).generate()

}