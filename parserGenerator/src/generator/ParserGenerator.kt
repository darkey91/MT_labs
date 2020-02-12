package generator

import java.io.File
import java.lang.StringBuilder
import kotlin.test.currentStackTrace

class ParserGenerator(private val grammar: Grammar) {
    private var ffBuilder: FirstFollowBuilder? = null
    private var firstForRules: HashMap<Rule, HashSet<Terminal>>? = null
    private val name = grammar.name
    private val defaultDir = "generated/"
    private val usedNonterm = HashSet<String>()
    private var file: File
    private var path: String = ""

    init {
        path = "./src/${defaultDir}${name.toLowerCase()}"
        if (!File(path).exists()) {
            File(path).mkdirs()
        }

        file = File("${path}/${grammar.name}Parser.kt")
    }

    private fun changeTextToken(text: String) : String  {
        if (text.length == 0)
            return ""
        if(text.length == 2 && text.startsWith("\\") && !text[text.lastIndex].isLetter()) {
                return text[text.lastIndex].toString()
            }
        return text

    }

    fun generate() {
        ffBuilder = FirstFollowBuilder(grammar)
        firstForRules = ffBuilder!!.getFirstForRules()
//        ffBuilder!!.aa()
        write()
    }

    private fun createSwitcher(rule: Rule, terminals: HashSet<Terminal>): String {
        val code = StringBuilder()
        code.append("\t\t\t${terminals.toTypedArray().joinToString(", ") { "Token.${it.name}" }} -> {")
        code.append(
            """
                ${rule.right.joinToString("\n\t\t\t\t") {
                if (it is Terminal) {
                    if (it.str!! != "EPS") {
                        """
                            val ${it.name} = Tree("${it.name}", currentWord())
                            result.addChild(${it.name})
                            nextToken()
                        """.trimIndent()
                    } else {
                        ""
                    }
                } else {
                    """
                        val ${it.name} = ${it.name}(${(it as NonTerminal).args} ${if ((it as NonTerminal).args != "") "!!" else ""})
                        result.addChild(${it.name})
                    """.trimIndent()
                }
            }}
                    ${rule.code}
                }    
            """.trimIndent()
        )
        return code.toString()
    }

    private fun createFunction(nonterm: NonTerminal): String {
        val rulesForNonterm = firstForRules!!.filter { it.key.left.name == nonterm.name }
        val switcher = StringBuilder()

        val onlyRules = mutableListOf<Rule>()
        onlyRules.addAll(rulesForNonterm.keys)

        rulesForNonterm.forEach { (r, terms) ->
            if (r.right.isNotEmpty()) {
                run {
                    switcher.append("\n${createSwitcher(r, terms)}")
                }
            }
        }

        val code = StringBuilder(
            """
    private fun ${nonterm.name}(${nonterm.args}): Tree {
        val result = Tree("${nonterm.name}")
        when(currentToken()) {${switcher}
            else -> {
                throw ${name}ParserException(getErrorMessage(currentToken(), "${nonterm.name}}()"))
            }
        }
        return result
    }
""".trimIndent()
        )
        return code.toString()
    }

    private val startFunctionName = grammar.startName

    private fun write() {
        val functionsCode =
            grammar.rules.joinToString("\n\n") {
                if (!usedNonterm.contains(it.left.name)) {
                    usedNonterm.add(it.left.name)
                    createFunction(it.left)
                } else ""
            }

        file.writeText(
            """${grammar.header}
import java.lang.Integer.parseInt
                
class ${name}Parser() {
    private var lexer: ${name}Lexer? = null
    
    private fun nextToken() {
        lexer!!.nextToken()
    }

    private fun currentToken() = lexer!!.curToken
    private fun currentChar() = lexer!!.curChar
    private fun currentWord() = lexer!!.curWord


    private fun getErrorMessage(token: Token, funcName: String): String = "Unexpected token " + token + " in " + funcName
    
$functionsCode
    
    fun parse(expression: String): Tree {
        lexer = ${name}Lexer(expression)
        nextToken()
        val root = ${startFunctionName}()
        
        if (currentToken() == Token.${name.toUpperCase()}_END_TOKEN) return root else throw ${name}ParserException(getErrorMessage(currentToken(), "parse"))
    }
}

class ${name}ParserException(message: String?) : RuntimeException(message)

class Tree {
    var node: String
        private set
    
    ${grammar.fields.joinToString("\n\t") { it }}

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
""".trimMargin()
        )
    }
}
