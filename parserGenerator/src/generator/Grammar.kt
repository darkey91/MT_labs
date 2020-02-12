package generator

import generator.basegrammar.GrammarSchemeLexer
import generator.basegrammar.GrammarSchemeParser
import generator.basegrammar.GrammarSchemeParser.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.lang.Exception

class GrammarException(private val msg: String) : Exception(msg) {}

data class Grammar(
    val name: String,
    val rules: List<Rule>,
    val terminals: List<Terminal>,
    val skipTerminals: List<Terminal>,
    val header: String,
    val fields: List<String>
) {
    val startName = rules[0].left.name
}

sealed class GrammarUnit(open val name: String) {

    override fun equals(other: Any?): Boolean {
        if (other !is GrammarUnit) {
            throw GrammarException("Can't compare GrammarUnit with ${other!!::class.java.name}")
        }
        return name == (other as GrammarUnit).name
    }
}

data class NonTerminal(override val name: String, var args: String = "") : GrammarUnit(name) {}

data class Terminal(override var name: String, var termCode: String = "") : GrammarUnit(name) {
    var str: String? = null
    var skip: Boolean = false

    constructor(name: String, str: String, skip: Boolean = false) : this(name) {
        this.name = name
        this.str = str
        this.skip = skip
    }

    fun copy(other: Terminal) {
        this.name = other.name
        this.str = other.str
        this.skip = other.skip
        this.termCode = other.termCode
    }
}

data class Code(var code: String) {}

data class Rule(val left: NonTerminal, val right: List<GrammarUnit>, var code: String = "") {}

class GrammarBuilder(private val grammarPath: String, private val outputDir: String? = null) {
    private var grammarContext: GrammarSpecContext

    private val grammarRules = ArrayList<Rule>()
    private val terminals = HashMap<String, Terminal>()
    private val skipTerminals = ArrayList<Terminal>()

    companion object {
        const val EPS = "EPS"
        val EPS_TERM = Terminal("EPS")
    }

    init {
        EPS_TERM.str = EPS
        val inputGrammarFile: File?
        inputGrammarFile = File(grammarPath)
//        println(inputGrammarFile.absolutePath)
        if (!inputGrammarFile.exists()) {
            throw Exception("File doesn't exist")
        }
        val code = inputGrammarFile.readText()
        val grammarLexer = GrammarSchemeLexer(CharStreams.fromString(code))
        val tokens = CommonTokenStream(grammarLexer)
        val grammarParser = GrammarSchemeParser(tokens)
        grammarContext = grammarParser.grammarSpec()
        terminals[EPS] = EPS_TERM
    }

    private fun fillGrammarUnits(ruleName: String, names: NamesContext, units: ArrayList<GrammarUnit>, codeRule: Code, args: String) {
        if (names.OR() === null) {
            val unitNames = names.moreNames().name()
            val code = names.moreNames().code()?.str() ?: ""
            val cnt = if (code == "") names.moreNames().childCount else names.moreNames().childCount - 1

            for (i in 0 until cnt) {
                val it = unitNames[i]
                when {
                    it.TOKEN_NAME() != null -> {
                        units.add(Terminal(it.TOKEN_NAME().text))
                    }
                    it.IDENTIFIER() != null -> {
                        var arguments = if (it.argument() != null) it.argument().ARGUMENT().text else ""
                        if (arguments != "") arguments = arguments.substring(1, arguments.length - 1).trim()
                        units.add(NonTerminal(it.IDENTIFIER().text, arguments))
                    }
                    else -> {
                        throw GrammarException("Strange behaviour in fillGrammarUnits()")
                    }
                }
            }
            codeRule.code = code
            return
        }

        names.names().forEach {
            fillGrammarUnits(ruleName, it, units, codeRule, args)
            val rightRulePart = ArrayList<GrammarUnit>()
            rightRulePart.addAll(units)
            grammarRules.add(Rule(NonTerminal(ruleName, args), rightRulePart, codeRule.code))
            units.clear()
        }
    }

    private fun ArgumentWithTypeContext.str() : String {
        return "${getChild(1)}: ${getChild(3)}"
    }

    private fun makeGrammarRules(rules: RulesContext) {
        rules.grammarRule().forEach {
            val tokenRule = it.tokenRule()
            val syntaxRule = it.syntaxRule()

            when {
                syntaxRule != null -> {
                    val args = syntaxRule.argumentWithType()?.str() ?: ""
                    val codeForRule = Code("")
                    val units = ArrayList<GrammarUnit>()
                    val names = syntaxRule.names()
                    fillGrammarUnits(syntaxRule.IDENTIFIER().text, names, units, codeForRule, args)
                    val rightRulePart = ArrayList<GrammarUnit>()
                    rightRulePart.addAll(units)

                    if (rightRulePart.isNotEmpty()) {
                        val leftPart = NonTerminal(syntaxRule.IDENTIFIER().text, args)
                        grammarRules.add(
                            Rule(
                                leftPart,
                                rightRulePart,
                                codeForRule.code
                            )
                        )
                    }
                }
                tokenRule != null -> {
                    val term = Terminal(
                        tokenRule.TOKEN_NAME().text,
                        tokenRule.REGEX().text.substring(1, tokenRule.REGEX().text.length - 1),
                        tokenRule.SKIP_RULE() != null
                    )

                    if (term.skip) {
                        skipTerminals.add(term)
                    } else {
                        terminals[term.name] = term
                    }

//                    println("TokenRuleText = ${tokenRule.TOKEN_NAME().text}    Text = ${tokenRule.REGEX().text}")
                }
                else -> {
                    throw GrammarException("Strange behaviour in makeGrammarRule()")
                }
            }
        }

        grammarRules.forEach {
            /*       print("${it.left.name} -> ")
                   it.right.forEach { r ->
                       print("${r.name} ")
                   }
                   println()*/

            it.right.forEach { unit ->
                if (unit is Terminal) {
                    val term = terminals[unit.name] ?: throw GrammarException("${unit.name} was't declared")
                    unit.copy(term)
                }
            }
        }
    }

    private fun CodeContext.str(): String {
        return text.substring(1, text.length - 1).split("\n").joinToString("\n") { it.trim() }
    }

    private fun FieldContext.str(): String = "${if (VAR() != null) "var" else "val"} ${IDENTIFIER(0).text}: ${IDENTIFIER(1).text}? = null"

    private fun makeFields(fields: FieldsContext?): List<String> {
        if (fields == null) return emptyList()
        val fieldsStr = ArrayList<String>()
        for (i in 0 until fields.childCount - 3) {
            fieldsStr.add(fields.field(i).str())
        }

        return fieldsStr
    }

    fun build(): Grammar {
        makeGrammarRules(grammarContext.rules())
        return Grammar(
            grammarContext.grammarName().IDENTIFIER().text,
            grammarRules,
            terminals.entries.filter { it.value.str != EPS }.map { it.value },
            skipTerminals,
            if (grammarContext.header() === null) "" else grammarContext.header().code().str(),
            makeFields(grammarContext.fields())
        )

    }
}
