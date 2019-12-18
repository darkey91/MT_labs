import java.io.IOException
import java.io.InputStream
import java.text.ParseException

enum class Token {
    START, LPAREN, RPAREN, VBAR, LETTER, STAR, END
}

class LexicalAnalyzer(private var inStream: InputStream? = null) {
    var curToken: Token = Token.START;
    private var currentCharInt: Int = -1

    var pointer = 0

    private fun nextChar() {
        if (inStream == null) {
            throw ParseException("InputStream wasn't initialized", pointer)
        }
        ++pointer
        try {
            currentCharInt = inStream!!.read()
        } catch (e: IOException) {
            throw ParseException(e.message, pointer)
        }
    }

    fun curChar(): Char = currentCharInt.toChar()

    fun nextToken() {
        //вообще в regex пробелы норм штучка, если они запрещены, то недопустимы
        nextChar()

        while ( currentCharInt != -1 && curChar().isWhitespace()) {
            nextChar()
        }

        if (currentCharInt == -1) {
            curToken = Token.END
            return
        }

        curToken = when (curChar()) {
            '(' -> {
                Token.LPAREN
            }
            ')' -> {
                Token.RPAREN
            }
            '|' -> {
                Token.VBAR
            }
            '*' -> {
                Token.STAR
            }
            else -> {
                when {

                    curChar() in 'a'..'z' -> {
                        Token.LETTER
                    }
                    else -> {
                        throw ParseException("Illegal character ${curChar()}", pointer)
                    }
                }
            }
        }

    }

}
