package generator

import generator.GrammarBuilder.Companion.EPS
import generator.GrammarBuilder.Companion.EPS_TERM
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

//    private val grammar: Grammar

class FirstFollowBuilder(
    private val grammar: Grammar
) {
    val first = HashMap<String, HashSet<Terminal>>()
    val follow = HashMap<String, HashSet<Terminal>>()
    val firstForRule = HashMap<Rule, HashSet<Terminal>>()
    private var firstBuilt = false
    private var followBuilt = false

    private val END_TERM = Terminal("${grammar.name.toUpperCase()}_END_TOKEN", "$")
    private val start: String

    init {
        grammar.rules.forEach { rule ->
            first[rule.left.name] = HashSet()
            follow[rule.left.name] = HashSet()
            firstForRule[rule] = HashSet()
        }

        start = grammar.rules[0].left.name
        println("Start: $start")
    }

    private fun getFirst(rightRuleSide: List<GrammarUnit>): Set<Terminal> {
        if (rightRuleSide.isEmpty()) {
            return HashSet()
        }

        when {
            rightRuleSide[0] === EPS_TERM -> return hashSetOf(EPS_TERM)
            rightRuleSide[0] is Terminal -> return hashSetOf((rightRuleSide[0] as Terminal))

            else -> {
                val newFirst = HashSet<Terminal>()

                assert(first[rightRuleSide[0].name] !== null)

                if (first[rightRuleSide[0].name] == null) {
                    println("suka")
                }

                newFirst.addAll(first[rightRuleSide[0].name]!!
                    .filter { it.str!! !== EPS }
                )
                if (first[rightRuleSide[0].name]!!.contains(EPS_TERM)) {
                    newFirst.addAll(getFirst(rightRuleSide.subList(1, rightRuleSide.size)))
                }
                return newFirst
            }
        }
    }

    private fun buildFirst(): FirstFollowBuilder {
        var changed = true

        while (changed) {
            changed = false
            grammar.rules.forEach { rule ->
                assert(first[rule.left.name] !== null)
                val oldSize = first[rule.left.name]!!.size
                first[rule.left.name]!!.addAll(getFirst(rule.right))
                if (oldSize != first[rule.left.name]!!.size)
                    changed = true
            }
        }
        firstBuilt = true
        return this
    }

    private fun buildFollow(): FirstFollowBuilder {
        if (!firstBuilt)
            throw Exception("Build FIRST at first!")
        follow[start]!!.add(END_TERM)

        var changed = true

        while (changed) {
            changed = false
            grammar.rules.forEach { rule ->

                for (i in rule.right.indices) {
                    if (rule.right[i] !is Terminal && rule.right[i] != EPS_TERM) {
                        val oldSize = follow[rule.right[i].name]!!.size
                        var containsEps = true
                        var was = false
                        var hasPrevEps = true
                        var j = i + 1
                        while (j < rule.right.size && hasPrevEps) {
                            if (rule.right[j] is Terminal) {
                                follow[rule.right[i].name]!!.add((rule.right[j] as Terminal))
                                j++
                                hasPrevEps = false
                                containsEps = false
                                continue
                            }
                            was = true

                            follow[rule.right[i].name]!!.addAll(
                                first[rule.right[j].name]!!.filter {  it.str!! !== EPS }
                            )
                            if (!first[rule.right[j].name]!!.contains(EPS_TERM)) {
                                containsEps = false
                                hasPrevEps = false
                            }

                            ++j
                        }

                        if ((containsEps && was) || i == rule.right.size - 1) {
                            follow[rule.right[i].name]!!.addAll(follow[rule.left.name]!!)
                        }
                        if (follow[rule.right[i].name]!!.size != oldSize) {
                            changed = true
                        }
                    }
                }
            }
        }
        followBuilt = true
        return this
    }

    fun getFirstForRules(): HashMap<Rule, HashSet<Terminal>> {
        this.buildFirst()
        this.buildFollow()

        for (j in grammar.rules.indices) {
            val rule = grammar.rules[j]
            var containsRightEps = true
            var toCont = false
            for (i in rule.right.indices) {
                val gramUnit = rule.right[i]
                if (gramUnit !is Terminal) {
                    first[gramUnit.name]!!.forEach { symbol ->
                        if (symbol.str!! != EPS) {
                            firstForRule[rule]!!.add(symbol)
                            containsRightEps = false
                        }
                    }
                    if (!containsRightEps) {
                        toCont = true
                        break
                    }
                } else if (gramUnit != EPS_TERM) {
                    firstForRule[rule]!!.add((gramUnit as Terminal))
                    toCont = true
                    break
                }
            }

            if (toCont) continue

            if (containsRightEps) {
                firstForRule[rule]!!.addAll(follow[rule.left.name]!!.map { it })
            }
        }
        return this.firstForRule
    }

    fun aa() {
        buildFirst().buildFollow().getFirstForRules()
        print("FIRST")
        first.forEach { (k, v) ->
            println("\n$k : ")
            v.forEach { print("$it  ") }
        }
        print("\nFOLLOW")
        follow.forEach { (k, v) ->
            println("\n$k : ")
            v.forEach { print("$it  ") }
        }

        println("\nFIRST RULES")
        grammar.rules.forEach {
            print("${it.left.name} -> ${it.right[0].name} : ")
            firstForRule[it]!!.forEach { f ->
                print(f.str!! + " ");
            }
            println()
        }
    }
}

/*
fun main() {

    val rules = arrayListOf(
        "function" to arrayListOf("type", "name", "(", "args", ")", ";"),
        "type" to arrayListOf("IDENTIFIER"),
        "name" to arrayListOf("IDENTIFIER"),
        "args" to arrayListOf(eps),
        "args" to arrayListOf("arg"),
        "args" to arrayListOf("multiargs"),
        "arg" to arrayListOf("type", "variable"),
        "variable" to arrayListOf("*", "variable"),
        "variable" to arrayListOf("name"),
        "multiargs" to arrayListOf("arg", ",", "multiarg"),
        "multiarg" to arrayListOf("arg"),
        "multia
    ffBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    println("\n--------FOLLOW--------")
    ffBuilder.buildFollow()
    ffBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }rg" to arrayListOf("arg", "multiarg")
    )

    val terminals = hashSetOf("IDENTIFIER", "(", ")", "*", ";", ",")

    val firstBuilder = FirstFollowBuilder("function", rules, terminals)
    firstBuilder.buildFirst()

    firstBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    ffBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    println("\n--------FOLLOW--------")
    ffBuilder.buildFollow()
    ffBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }
    println("\n--------FOLLOW--------")
    firstBuilder.buildFollow()
    firstBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    val rules = arrayListOf(
        "R" to arrayListOf("T", "R1"),
        "R1" to arrayListOf("|", "T", "R"),
        "R1" to arrayListOf(eps),
        "T" to arrayListOf("F", "T1"),
        "T1" to arrayListOf("F", "T1"),
        "T1" to arrayListOf(eps),
        "F" to arrayListOf("H", "F1"),
        "F1" to arrayListOf("*", "F1"),
        "F1" to arrayListOf(eps),
        "H" to arrayListOf("letter"),
        "H" to arrayListOf("(", "R", ")")

    )

    val terminals = hashSetOf("letter", "(", ")", "*", "|")

    val firstBuilder = FirstFollowBuilder("R", rules, terminals)
    firstBuilder.buildFirst()

    firstBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }



    ln("\n--------FOLLOW--------")
    firstBuilder.buildFollow()
    firstBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    */
/**Parenthesis**//*

    val rulesParen = arrayListOf(
        "S" to arrayListOf("(", "S", ")", "So"),
        "S" to arrayListOf("eps"),
        "So" to arrayListOf("S", "So"),
        "So" to arrayListOf("eps")
    )

    val termi = hashSetOf("(", ")")

    val ffBuilder = FirstFollowBuilder("S", rulesParen, termi)

    ffBuilder.buildFirst()

    println("\n--------FIRST--------")

    ffBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    println("\n--------FOLLOW--------")
    ffBuilder.buildFollow()
    ffBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }


    */
/***ArifAexpression****//*

    val rulesExpr = arrayListOf(
        "E" to arrayListOf("T", "Eo"),
        "Eo" to arrayListOf(eps),
        "Eo" to arrayListOf("+", "T", "Eo"),
        "T" to arrayListOf("F", "To"),
        "To" to arrayListOf(eps),
        "To" to arrayListOf("*", "F", "To"),
        "F" to arrayListOf("(", "E", ")"),
        "F" to arrayListOf("n")
    )

    val termExpr = hashSetOf("n", "(", ")", "*", "+")

    val AEBuilder = FirstFollowBuilder("E", rulesExpr, termExpr)

    println("\n--------FIRST--------")
    AEBuilder.buildFirst()
    AEBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    println("\n--------FOLLOW--------")
    AEBuilder.buildFollow()
    AEBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    */
/**** TT ****//*

    val rulesTT = arrayListOf(
        "Expr" to arrayListOf("Appl", "Expr1"),
        "Expr" to arrayListOf("\\", "Var", ".", "Expr"),
        "Expr1" to arrayListOf(eps),
        "Expr1" to arrayListOf("\\", "Var", ".", "Expr"),
        "Appl" to arrayListOf("Atom", "Appl1"),
        "Appl1" to arrayListOf(eps),
        "Appl1" to arrayListOf("Atom", "Appl1"),
        "Atom" to arrayListOf("Var"),
        "Atom" to arrayListOf("(","Expr"  ,")"),
        "Var" to arrayListOf("letter")
    )

    val termTT = hashSetOf("letter", "(", ")", "\\", ".")

    val TTBuilder = FirstFollowBuilder("Expr", rulesTT, termTT)

    println("\n--------FIRST--------")
    TTBuilder.buildFirst()
    TTBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    println("\n--------FOLLOW--------")
    TTBuilder.buildFollow()
    TTBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

}*/