//import generated.regex.RegexLexer
//import generated.regex.RegexParser
//import generated.regex.RegexParserException
//import org.junit.jupiter.api.Test
//import java.text.ParseException
//import java.time.LocalDateTime
//import kotlin.random.Random
//
//class RegexTests {
//    private val correctSymbol = "qwertyuiopasdfghjklzxcvbnm".toCharArray()
//    private val incorrectSymbols = "./,\\-+^&".toCharArray()
//    private val maxLen = 20
//
//    companion object {
//        val correctRe = arrayListOf(
//            "(a|\tb)",
//            "iu*i((e |io*)*)    lo|l*    ",
//            "((abc*b|a)*ab(aa|b\n*)b)*",
//            "(a    b|c)(j|a * i k)\n",
//            "ya  (a\nb(aa|b*)b)*",
//            "(ust*|ala) | (n*ad|o)*  ",
//            "(do)|del*a(t)",
//            "la (la)*(l|\rp*)",
//            "(s|k)*o*|r|o| (po*g|(ul)(ya)*u)*\t"
//        )
//    }
//
//    private fun rand(): Int = Random.nextInt(0, Int.MAX_VALUE)
//
//    private fun getRandomSimpleRe(): String {
//        val n = (0 until 10).random()
//        val str = StringBuilder("")
//
//        for (i in 0 until n) {
//            if (rand() % 3 == 0) {
//                str.append(
//                    incorrectSymbols[(incorrectSymbols.indices).random()]
//                )
//            } else {
//                str.append(
//                    correctSymbol[(correctSymbol.indices).random()]
//                )
//            }
//
//            str.append(
//                when (rand() % 8) {
//                    0 -> ' '
////                    1 -> '\n'
////                    2 -> '\t'
////                    3 -> '\r'
//                    4 -> '*'
//                    5 -> '|'
//                    else -> ("")
//                }
//            )
//        }
//        return str.toString()
//    }
//
//    private fun getRandomRe(): String {
//        val str = StringBuilder("")
//
//        for (i in 0 until maxLen) {
//            var leftParenthesis = false
//            if (rand() % 4 == 0) {
//                leftParenthesis = true
//                str.append('(')
//            }
//            if (rand() % 3 == 0) {
//                str.append(getRandomSimpleRe())
//            }
//            if (rand() % 2 == 0) {
//                str.append(correctRe.indices.random())
//            }
//
//            if (leftParenthesis) {
//                if (rand() % 2 == 0) {
//                    str.append(')')
//                }
//            } else if (rand() % 7 == 0) {
//                str.append(')')
//            }
//        }
//
//        return str.toString()
//    }
//
//    @Test
//    fun `Test for error handling`() {
//        val parser = RegexParser()
//
//        for (i in 0..20) {
//            val re = getRandomRe()
//            try {
//                parser.parse(re)
//                System.err.println("Successfully parsed: $re")
//            } catch (e: RegexParserException) {
//                System.err.println("ParseException: $re")
//            } catch (e: RegexLexer.LexicalException) {
//                System.err.println("ParseException: $re")
//            }
//        }
//    }
//
//    @Test
//    fun `Test for correctness`() {
//        val parser = RegexParser()
//        correctRe.forEach {
//            try {
//                val root = parser.parse(it)
//            } catch (e: RegexLexer.LexicalException) {
//                System.err.println("ParseException: $it")
//                throw e
//            } catch (e: StackOverflowError) {
//                System.err.println("ParseException: $it")
//                throw e
//            }
//        }
//    }
//
//}