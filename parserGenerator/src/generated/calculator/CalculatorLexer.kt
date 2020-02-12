package generated.calculator


import java.io.IOException
import java.io.InputStream
import java.text.ParseException


enum class Token {
    NUMBER, MULTIPLICATION, CLOSE, DIVISION, MINUS, PLUS, OPEN,
    CALCULATOR_START_TOKEN, CALCULATOR_END_TOKEN
}    

class CalculatorLexer(private var expression: String){ 
    var curToken: Token = Token.CALCULATOR_START_TOKEN
    var pointer = 0
    var curWord = ""
    var curChar = ""

    private val supportedSymbols = "[0-9]+|\\*|\\)|/|-|\\+|\\(".toRegex()
    private val ignoredSymbols = "[ \\t\\r\\n]".toRegex()
    private val symbolsAndTokens = listOf<Pair<Regex, Token>>(
        "[0-9]+".toRegex() to Token.NUMBER,
		"\\*".toRegex() to Token.MULTIPLICATION,
		"\\)".toRegex() to Token.CLOSE,
		"/".toRegex() to Token.DIVISION,
		"-".toRegex() to Token.MINUS,
		"\\+".toRegex() to Token.PLUS,
		"\\(".toRegex() to Token.OPEN
    )

    private fun isEnded(): Boolean = pointer >= expression.length

    private fun nextChar() {
        ++pointer
        curChar = if (pointer >= expression.length) "" else expression[pointer].toString()
    }

    private fun prevChar() {
        if (pointer == 0) {
            throw LexicalException("Can't move to previous char")
        }
        --pointer
        curChar = if (pointer >= expression.length) "" else expression[pointer].toString()
    }

    private fun findTokens(symbols: String, pairs: List<Pair<Regex, Token>>): List<Pair<Regex, Token>> {
        return pairs.filter {
            it.first.matchEntire(symbols) != null
        }.toList()
    }
    
    fun nextToken() {
        curWord = ""
        val word = StringBuilder()
        
        var isIgnored = ignoredSymbols.matchEntire(curChar) != null
        while(!isEnded() && isIgnored) {
            nextChar()
            isIgnored = ignoredSymbols.matchEntire(curChar) != null
        }
 
        if (isEnded()) {
            curToken = Token.CALCULATOR_END_TOKEN
            return
        }
        curChar = expression[pointer].toString()
        
        if (supportedSymbols.matchEntire(curChar) === null) {
            throw LexicalException("Unexpected token " + curChar + " at " + "pointer")
        }
        
        var suitableSymbolsAndTokens = symbolsAndTokens
        var prevTokens: List<Pair<Regex, Token>>
        
        while (!isEnded()) {
            word.append(curChar)  

            prevTokens = suitableSymbolsAndTokens
            suitableSymbolsAndTokens = findTokens(word.toString(), symbolsAndTokens)
            
            if (suitableSymbolsAndTokens.isNotEmpty()) {
                nextChar()
                if (suitableSymbolsAndTokens.size == 1) {
                     curToken = suitableSymbolsAndTokens[0].second
                     curWord = word.toString()
                }
            } else if (ignoredSymbols.matchEntire(curChar) != null) {
                if (prevTokens.size > 1) {
                    throw LexicalException("Ambiguous grammar")
                }
                curWord = word.substring(0, word.length - 1)
                break
            } else {
                if (prevTokens.size > 1) {
                    throw LexicalException("Ambiguous grammar")
                }
                curWord = word.substring(0, word.length - 1)
                break
            } 
        }        
    }
    
    class LexicalException(message: String?) : RuntimeException(message)
}    