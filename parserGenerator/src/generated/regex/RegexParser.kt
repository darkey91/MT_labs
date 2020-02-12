package generated.regex

import java.lang.Integer.parseInt
                
class RegexParser() {
    private var lexer: RegexLexer? = null
    
    private fun nextToken() {
        lexer!!.nextToken()
    }

    private fun currentToken() = lexer!!.curToken
    private fun currentChar() = lexer!!.curChar
    private fun currentWord() = lexer!!.curWord


    private fun getErrorMessage(token: Token, funcName: String): String = "Unexpected token " + token + " in " + funcName
    
    private fun expr(): Tree {
        val result = Tree("expr")
        when(currentToken()) {
			Token.LETTER, Token.LPAREN -> {                val base_expr = base_expr( )
result.addChild(base_expr)
				val union = union( )
result.addChild(union)
                    
                }    
            else -> {
                throw RegexParserException(getErrorMessage(currentToken(), "expr}()"))
            }
        }
        return result
    }

    private fun union(): Tree {
        val result = Tree("union")
        when(currentToken()) {
			Token.VBAR -> {                val VBAR = Tree("VBAR", currentWord())
result.addChild(VBAR)
nextToken()
				val base_expr = base_expr( )
result.addChild(base_expr)
				val union = union( )
result.addChild(union)
                    
                }    
			Token.RPAREN, Token.REGEX_END_TOKEN -> {
    
}    
            else -> {
                throw RegexParserException(getErrorMessage(currentToken(), "union}()"))
            }
        }
        return result
    }



    private fun base_expr(): Tree {
        val result = Tree("base_expr")
        when(currentToken()) {
			Token.LETTER, Token.LPAREN -> {                val spec_expr = spec_expr( )
result.addChild(spec_expr)
				val high_expr = high_expr( )
result.addChild(high_expr)
                    
                }    
            else -> {
                throw RegexParserException(getErrorMessage(currentToken(), "base_expr}()"))
            }
        }
        return result
    }

    private fun high_expr(): Tree {
        val result = Tree("high_expr")
        when(currentToken()) {
			Token.LETTER, Token.LPAREN -> {                val spec_expr = spec_expr( )
result.addChild(spec_expr)
				val high_expr = high_expr( )
result.addChild(high_expr)
                    
                }    
			Token.RPAREN, Token.VBAR, Token.REGEX_END_TOKEN -> {
    
}    
            else -> {
                throw RegexParserException(getErrorMessage(currentToken(), "high_expr}()"))
            }
        }
        return result
    }



    private fun spec_expr(): Tree {
        val result = Tree("spec_expr")
        when(currentToken()) {
			Token.LETTER, Token.LPAREN -> {                val unit = unit( )
result.addChild(unit)
				val with_star = with_star( )
result.addChild(with_star)
                    
                }    
            else -> {
                throw RegexParserException(getErrorMessage(currentToken(), "spec_expr}()"))
            }
        }
        return result
    }

    private fun with_star(): Tree {
        val result = Tree("with_star")
        when(currentToken()) {
			Token.STAR -> {                val STAR = Tree("STAR", currentWord())
result.addChild(STAR)
nextToken()
                    
                }    
			Token.LETTER, Token.RPAREN, Token.LPAREN, Token.VBAR, Token.REGEX_END_TOKEN -> {
    
}    
            else -> {
                throw RegexParserException(getErrorMessage(currentToken(), "with_star}()"))
            }
        }
        return result
    }



    private fun unit(): Tree {
        val result = Tree("unit")
        when(currentToken()) {
			Token.LPAREN -> {                val LPAREN = Tree("LPAREN", currentWord())
result.addChild(LPAREN)
nextToken()
				val expr = expr( )
result.addChild(expr)
				val RPAREN = Tree("RPAREN", currentWord())
result.addChild(RPAREN)
nextToken()
                    
                }    
			Token.LETTER -> {                val LETTER = Tree("LETTER", currentWord())
result.addChild(LETTER)
nextToken()
                    
                }    
            else -> {
                throw RegexParserException(getErrorMessage(currentToken(), "unit}()"))
            }
        }
        return result
    }


    
    fun parse(expression: String): Tree {
        lexer = RegexLexer(expression)
        nextToken()
        val root = expr()
        
        if (currentToken() == Token.REGEX_END_TOKEN) return root else throw RegexParserException(getErrorMessage(currentToken(), "parse"))
    }
}

class RegexParserException(message: String?) : RuntimeException(message)

class Tree {
    var node: String
        private set
    
    

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