package generated.calculator

import java.lang.Integer.parseInt

class CalculatorParser() {
    private var lexer: CalculatorLexer? = null

    private fun nextToken() {
        lexer!!.nextToken()
    }

    private fun currentToken() = lexer!!.curToken
    private fun currentChar() = lexer!!.curChar
    private fun currentWord() = lexer!!.curWord


    private fun getErrorMessage(token: Token, funcName: String): String =
        "Unexpected token " + token + " in " + funcName

    private fun expr(): Tree {
        val result = Tree("expr")
        when (currentToken()) {
            Token.OPEN, Token.NUMBER, Token.MINUS -> {
                val second = second()
                result.addChild(second)
                val expr_new = expr_new(second.value!!)
                result.addChild(expr_new)
                result.value = expr_new.value
            }
            else -> {
                throw CalculatorParserException(getErrorMessage(currentToken(), "expr}()"))
            }
        }
        return result
    }

    private fun expr_new(acc: Int): Tree {
        val result = Tree("expr_new")
        when (currentToken()) {
            Token.MINUS -> {
                val MINUS = Tree("MINUS", currentWord())
                result.addChild(MINUS)
                nextToken()
                val second = second()
                result.addChild(second)
                val expr_new = expr_new(acc - second.value!!!!)
                result.addChild(expr_new)
                result.value = expr_new.value
            }
            Token.CLOSE, Token.CALCULATOR_END_TOKEN -> {
                result.value = acc
            }
            Token.PLUS -> {
                val PLUS = Tree("PLUS", currentWord())
                result.addChild(PLUS)
                nextToken()
                val second = second()
                result.addChild(second)
                val expr_new = expr_new(acc + second.value!!!!)
                result.addChild(expr_new)
                result.value = expr_new.value
            }
            else -> {
                throw CalculatorParserException(getErrorMessage(currentToken(), "expr_new}()"))
            }
        }
        return result
    }


    private fun second(): Tree {
        val result = Tree("second")
        when (currentToken()) {
            Token.OPEN, Token.NUMBER, Token.MINUS -> {
                val unar = unar()
                result.addChild(unar)
                val second_new = second_new(unar.value!!)
                result.addChild(second_new)
                result.value = second_new.value
            }
            else -> {
                throw CalculatorParserException(getErrorMessage(currentToken(), "second}()"))
            }
        }
        return result
    }

    private fun second_new(acc: Int): Tree {
        val result = Tree("second_new")
        when (currentToken()) {
            Token.CLOSE, Token.CALCULATOR_END_TOKEN, Token.PLUS, Token.MINUS -> {
                result.value = acc
            }
            Token.DIVISION -> {
                val DIVISION = Tree("DIVISION", currentWord())
                result.addChild(DIVISION)
                nextToken()
                val unar = unar()
                result.addChild(unar)
                val second_new = second_new(acc / unar.value!!!!)
                result.addChild(second_new)
                result.value = unar.value
            }
            Token.MULTIPLICATION -> {
                val MULTIPLICATION = Tree("MULTIPLICATION", currentWord())
                result.addChild(MULTIPLICATION)
                nextToken()
                val unar = unar()
                result.addChild(unar)
                val second_new = second_new(acc * unar.value!!!!)
                result.addChild(second_new)
                result.value = second_new.value
            }
            else -> {
                throw CalculatorParserException(getErrorMessage(currentToken(), "second_new}()"))
            }
        }
        return result
    }


    private fun unar(): Tree {
        val result = Tree("unar")
        when (currentToken()) {
            Token.MINUS -> {
                val MINUS = Tree("MINUS", currentWord())
                result.addChild(MINUS)
                nextToken()
                val first = first()
                result.addChild(first)
                result.value = -first.value!!
            }
            Token.OPEN, Token.NUMBER -> {
                val first = first()
                result.addChild(first)
                result.value = first.value
            }
            else -> {
                throw CalculatorParserException(getErrorMessage(currentToken(), "unar}()"))
            }
        }
        return result
    }


    private fun first(): Tree {
        val result = Tree("first")
        when (currentToken()) {
            Token.NUMBER -> {
                val NUMBER = Tree("NUMBER", currentWord())
                result.addChild(NUMBER)
                nextToken()
                result.value = parseInt(NUMBER.text)
            }
            Token.OPEN -> {
                val OPEN = Tree("OPEN", currentWord())
                result.addChild(OPEN)
                nextToken()
                val expr = expr()
                result.addChild(expr)
                val CLOSE = Tree("CLOSE", currentWord())
                result.addChild(CLOSE)
                nextToken()
                result.value = expr.value
            }
            else -> {
                throw CalculatorParserException(getErrorMessage(currentToken(), "first}()"))
            }
        }
        return result
    }


    fun parse(expression: String): Tree {
        lexer = CalculatorLexer(expression)
        nextToken()
        val root = expr()

        if (currentToken() == Token.CALCULATOR_END_TOKEN) return root else throw CalculatorParserException(
            getErrorMessage(currentToken(), "parse")
        )
    }
}

class CalculatorParserException(message: String?) : RuntimeException(message)

class Tree {
    var node: String
        private set

    var value: Int? = null

    var text = ""
    val children = ArrayList<Tree>()

    constructor(node: String) {
        this.node = node
    }

    constructor(node: String, text: String) {
        this.node = node
        this.text = text
    }

    constructor(node: String, vararg children: Tree) {
        this.node = node

        for (ch in children) {
            this.children.add(ch)
        }
    }

    fun addChild(ch: Tree) {
        children.add(ch)
    }

    fun addChildren(vararg children: Tree) {
        for (ch in children) {
            this.children.add(ch)
        }
    }
}