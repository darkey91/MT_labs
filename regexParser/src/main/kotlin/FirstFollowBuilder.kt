val eps = "eps"

class FirstFollowBuilder(
    private val start: String,
    private val rules: ArrayList<Pair<String, ArrayList<String>>>,
    private val terminals: HashSet<String>
) {
    val first = HashMap<String, HashSet<String>>()
    val follow = HashMap<String, HashSet<String>>()


    init {
        rules.forEach { (key, value) ->
            first[key] = HashSet()
            follow[key] = HashSet()
        }
    }

    private fun getFirst(rightRuleSide: List<String>): Set<String> {
        if (rightRuleSide.isEmpty()) {
            return HashSet()
        }

        when {
            rightRuleSide[0] === eps -> return hashSetOf(eps)
            terminals.contains(rightRuleSide[0]) -> {

                return hashSetOf(rightRuleSide[0])

            }
            else -> {
                val newFirst = HashSet<String>()

                assert(first[rightRuleSide[0]] !== null)

                if (first[rightRuleSide[0]] == null) {
                    println("suka")
                }

                newFirst.addAll(first[rightRuleSide[0]]!!
                    .filter { it !== eps }
                )
                if (first[rightRuleSide[0]]!!.contains(eps)) {
                    newFirst.addAll(getFirst(rightRuleSide.subList(1, rightRuleSide.size)))
                }
                return newFirst
            }
        }
    }

    fun buildFirst() {
        var changed = true

        while (changed) {
            changed = false
            rules.forEach { (key, value) ->
                assert(first[key] !== null)
                val oldSize = first[key]!!.size
                first[key]!!.addAll(getFirst(value))
                if (oldSize != first[key]!!.size)
                    changed = true
            }
        }
    }

    fun buildFollow() {
        follow[start]!!.add("$")

        var changed = true

        while (changed) {
            changed = false
            rules.forEach { (key, value) ->

                for (i in 0 until value.size) {
                    if (!terminals.contains(value[i]) && value[i] != "eps") {
                        val oldSize = follow[value[i]]!!.size
                        var containsEps = true
                        var was = false
                        var hasPrevEps = true
                        var j = i + 1
                        while (j < value.size && hasPrevEps) {
                            if (terminals.contains(value[j])) {
                                follow[value[i]]!!.add(value[j])
                                j++
                                hasPrevEps = false
                                containsEps = false
                                continue
                            }
                            was = true

                            follow[value[i]]!!.addAll(
                                first[value[j]]!!.filter { it !== eps }
                            )
                            if (!first[value[j]]!!.contains(eps)) {
                                containsEps = false
                                hasPrevEps = false
                            }

                            ++j
                        }

                        if ((containsEps && was) || i == value.size - 1) {
                            follow[value[i]]!!.addAll(follow[key]!!)
                        }
                        if (follow[value[i]]!!.size != oldSize) {
                            changed = true
                        }
                    }
                }
            }
        }
    }
}


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
        "multiarg" to arrayListOf("arg", "multiarg")
    )

    val terminals = hashSetOf("IDENTIFIER", "(", ")", "*", ";", ",")

    val firstBuilder = FirstFollowBuilder("function", rules, terminals)
    firstBuilder.buildFirst()

    firstBuilder.first.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    println("\n--------FOLLOW--------")
    firstBuilder.buildFollow()
    firstBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    /*val rules = arrayListOf(
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

    println("\n--------FOLLOW--------")
    firstBuilder.buildFollow()
    firstBuilder.follow.forEach { (k, v) ->
        print("\n$k : \n")
        v.forEach { print("$it  ") }
    }

    *//**Parenthesis**//*

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


    *//***ArifAexpression****//*

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

    *//**** TT ****//*

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
*/
}