import java.io.IOException
import java.io.InputStream
import java.text.ParseException

enum class Token {
    START, LPAREN, RPAREN, VBAR, LETTER, STAR, END
}

class LexicalAnalyzer(private var inStream: InputStream) {
    var curToken: Token = Token.START;
    private var currentCharInt: Int = -1

    var pointer = 0

    private fun nextChar() {
        /*if (inStream == null) {
            throw ParseException("InputStream wasn't initialized", pointer)
        }*/
        ++pointer
        currentCharInt = inStream.read()
    }

    fun curChar(): Char = currentCharInt.toChar()

    fun nextToken() {
        //вообще в regex пробелы норм штучка, если они запрещены, то недопустимы
        nextChar()

        val curCharSeq = curChar().toString()

        while (currentCharInt != -1 && ("[ \\t\\r\\n]".toRegex().matches(curCharSeq))) {
            nextChar()
        }

        if (currentCharInt == -1) {
            curToken = Token.END
            return
        }

        curToken = when {
            "\\(".toRegex().matches(curCharSeq) -> {
                Token.LPAREN
            }
            "\\)".toRegex().matches(curCharSeq) -> {
                Token.RPAREN
            }
            "\\|".toRegex().matches(curCharSeq) -> {
                Token.VBAR
            }
            "\\*".toRegex().matches(curCharSeq) -> {
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
