package generator

import java.io.File

class LexerGenerator(private val grammar: Grammar) {
    private val tokens = grammar.terminals
    private val name = grammar.name

    private val defaultDir = "generated/"

    private var START_TOKEN = "_START_TOKEN"
    private var END_TOKEN = "_END_TOKEN"

    private var file: File
    private var path: String = ""

    init {
        START_TOKEN = "${grammar.name.toUpperCase()}$START_TOKEN"
        END_TOKEN = "${grammar.name.toUpperCase()}$END_TOKEN"

        path = "./src/${defaultDir}${grammar.name.toLowerCase()}"
        if (!File(path).exists()) {
            File(path).mkdirs()
        }

        file = File("${path}/${grammar.name}Lexer.kt")

    }

    companion object {
        private const val importPart = "\nimport java.io.IOException\n" +
                "import java.io.InputStream\n" +
                "import java.text.ParseException\n"
    }

    private fun getTokens(): String {
        val sb = StringBuilder()
        val tokensStr = tokens.joinToString(", ") {
            it.name
        }

        sb.append(
            """enum class Token {
    ${tokensStr},
    $START_TOKEN, $END_TOKEN
}    
""".trimIndent()
        )
        return sb.toString()
    }

    private fun prepareRegexStr(str: String) = str.replace("\\", "\\\\").replace("\"", "\\\"")

    private fun writeCode() {
        file.writeText(
            """${grammar.header}
$importPart

${getTokens()}

class ${name}Lexer(private var expression: String){ 
    var curToken: Token = Token.${START_TOKEN}
    var pointer = 0
    var curWord = ""
    var curChar = ""

    private val supportedSymbols = "${grammar.terminals.joinToString("|") { prepareRegexStr(it.str!!) }}".toRegex()
    private val ignoredSymbols = "${grammar.skipTerminals.joinToString("|") { prepareRegexStr(it.str!!) }}".toRegex()
    private val symbolsAndTokens = listOf<Pair<Regex, Token>>(
        ${tokens.joinToString(",\n\t\t") { "\"${prepareRegexStr(it.str!!)}\".toRegex() to Token.${it.name}" }}
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
            curToken = Token.${END_TOKEN}
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
""".trimIndent()
        )
    }

    fun generate() {
        writeCode()
    }
}

