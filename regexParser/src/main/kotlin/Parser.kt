import guru.nidi.graphviz.attribute.Color
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import guru.nidi.graphviz.model.*
import guru.nidi.graphviz.model.Factory.*
import java.io.File
import java.io.InputStream
import java.text.ParseException
import kotlin.collections.ArrayList
import guru.nidi.graphviz.model.MutableGraph
import java.time.LocalDateTime

/**
    R -> T R'
    R' -> '|' T R' | EPS
    T -> F T'
    T' -> F T' | EPS
    F -> H F'
    F' -> '*' | EPS
    H -> LETTER | '(' R ')'
 **/

/**
 * to allow a** :  F' -> '*' F' | EPS
 */

class Parser {
    private var lexer: LexicalAnalyzer? = null


    private fun getErrorMessage(token: Token, funcName: String): String = "Unexpected token $token in $funcName"

    private fun R(): Tree {
        return when (currentToken()) {
            /**    R -> T R'    **/
            Token.LPAREN, Token.LETTER -> {
                Tree("R", T(), RPrime())
            }
            else -> throw ParseException(getErrorMessage(currentToken(), "R()"), lexer!!.pointer)
        }
    }

    private fun RPrime(): Tree {
        val result = Tree("RPrime")

        return when (currentToken()) {
            /**     R' -> eps    **/
            Token.END, Token.RPAREN -> {
                result
            }

            /**     R' -> '|' T R'     **/
            Token.VBAR -> {
                assert(currentChar() == '|')

                result.addChild(Tree("|"))
                nextToken()

                result.addChildren(T(), RPrime())
                result
            }
            else -> throw ParseException(getErrorMessage(currentToken(), "RPrime()"), lexer!!.pointer)
        }
    }

    private fun T(): Tree {
        when (currentToken()) {
            /**    T -> F T'    **/
            Token.LPAREN, Token.LETTER -> {
                return Tree("T", F(), TPrime())
            }
            else -> throw ParseException(getErrorMessage(currentToken(), "T()"), lexer!!.pointer)
        }
    }

    private fun TPrime(): Tree {
        val result = Tree("TPrime")
        return when (currentToken()) {
            /**     T' -> F T'      **/
            Token.LPAREN, Token.LETTER -> {
                result.addChildren(F(), TPrime())
                result
            }
            /**     T' -> eps      **/
            Token.RPAREN, Token.VBAR, Token.END -> {
                result
            }

            else -> throw ParseException(getErrorMessage(currentToken(), "TPrime()"), lexer!!.pointer)
        }
    }

    private fun F(): Tree {
        return when (currentToken()) {
            /**    F -> H F'    **/
            Token.LETTER, Token.LPAREN -> {
                Tree("F", H(), FPrime())
            }
            else -> throw ParseException(getErrorMessage(currentToken(), "F()"), lexer!!.pointer)
        }
    }

    private fun FPrime(): Tree {
        val result = Tree("FPrime")
        return when (currentToken()) {
            /**    F' -> '*'    **/
            Token.STAR -> {
                assert(currentChar() == '*')
                result.addChild(Tree("*"))
                nextToken()
                result.addChild(FPrime())
                result
            }
            /**    F' -> eps    **/
            Token.LETTER, Token.RPAREN, Token.LPAREN, Token.END, Token.VBAR -> {
                result
            }

            else -> throw ParseException(getErrorMessage(currentToken(), "FPrime()"), lexer!!.pointer)
        }
    }

    private fun H(): Tree {
        val result = Tree("H")

        when (currentToken()) {
            /**    H -> ( R )    **/
            Token.LPAREN -> {
                assert(currentChar() == '(')

                result.addChild(Tree("("))
                nextToken()
                result.addChild(R())

                assert(currentChar() == ')')
                var br = currentChar()
                result.addChild(Tree(")"))
                nextToken()
                return result
            }

            /**    H -> LETTER    **/
            Token.LETTER -> {
                assert(currentChar() in 'a'..'z')

                result.addChild(Tree(currentChar().toString()))
                nextToken()
                return result

            }
            else -> throw ParseException(getErrorMessage(currentToken(), "T()"), lexer!!.pointer)
        }
    }

    private fun nextToken() {
        lexer!!.nextToken()
    }

    private fun currentToken() = lexer!!.curToken
    private fun currentChar() = lexer!!.curChar()

    fun parse(ins: InputStream): Tree {
        lexer = LexicalAnalyzer(ins)
        nextToken()
        val root = R()
        if (currentToken() == Token.END) return root else throw ParseException(getErrorMessage(currentToken(), "parse"), lexer!!.pointer)
    }
}

class Tree {
    var node: String
        private set

    val children = ArrayList<Tree>()

    constructor(node: String) {
        this.node = node
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

class TreeVisualizer(private val name: String = "G") {
    private var g: MutableGraph = mutGraph(this.name)

    init {
        g.isDirected = true
    }

    private fun getNodeId(node: Tree): String = System.identityHashCode(node).toString()

    private fun buildGraph(nodeTree: Tree, node: MutableNode) {

        if (nodeTree.children.isEmpty())
            node.add(Color.GREEN1)

        nodeTree.children.forEach {
            val curNode = mutNode(getNodeId(it), true).add("label", it.node)
            g.add(curNode)
            node.addLink(curNode)
            buildGraph(it, curNode)
        }
    }

    fun visualize(root: Tree) {
        val rootLocal = mutNode(getNodeId(root), true).add("label", root.node)
        g.add(rootLocal)
        buildGraph(root, rootLocal)
        Graphviz.fromGraph(g).render(Format.PNG).toFile(File("src/img/${LocalDateTime.now()}.png"))
    }
}

fun main() {
    val parser = Parser()
    val re: String = "a**"
    val reAst = parser.parse(re.byteInputStream())
    TreeVisualizer().visualize(reAst)
}