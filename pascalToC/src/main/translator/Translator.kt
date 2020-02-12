package main.translator

import main.exception.TranslatorException
import main.generated.PascalBaseVisitor
import main.generated.PascalParser
import main.generated.PascalParser.*
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import java.lang.Exception

class Translator : PascalBaseVisitor<String>() {
    var currentFunctionName = ""

    companion object {
        private var lineSeparator = System.lineSeparator()
        private const val EQUAL = "="
        private const val SEMI = ";"
        private const val COMMA = ","
    }

    private fun validateContext(ctx: ParserRuleContext?, ruleName: String) {
        if (ctx == null) {
            throw TranslatorException("$ruleName: context is null")
        }
    }

    override fun visitProgram(ctx: ProgramContext?): String {
        validateContext(ctx, "program")

        return visit(ctx!!.children[0]) + lineSeparator + visit(ctx.children[1])
    }

    override fun visitProgramHeader(ctx: PascalParser.ProgramHeaderContext?): String {
        return "#include <stdio.h>${lineSeparator}#include <math.h>${lineSeparator}#include <stdbool.h>${lineSeparator}"
    }

    override fun visitBlocks(ctx: PascalParser.BlocksContext?): String {
        validateContext(ctx, "blocks")

        val code = StringBuilder()
        ctx!!.children.forEach {
            if (it !== ctx.children.last()) {
                code.append(visit(it))
                        .append(lineSeparator)
            }
        }

        code.append("int main() ");
        code.append(visit(ctx.children.last()))

        return code.toString()
    }

    override fun visitConstBlock(ctx: ConstBlockContext?): String {
        validateContext(ctx, "constBlocks")
        val code = StringBuilder()
        ctx!!.children.forEach {
            code.append(visit(it))
                    .append(lineSeparator)
        }
        return code.toString()
    }

    override fun visitConstantDefinition(ctx: ConstantDefinitionContext?): String {
        validateContext(ctx, "constantDefinition")
        val code = StringBuilder()
        code.append("const ")
                .append(visit(ctx!!.children[2]))
                .append(" ")
                .append(ctx.children[0].text)
                .append(" = ")
                .append(ctx.children[5].text)
                .append(SEMI)
        return code.toString()
    }

    override fun visitVarBlock(ctx: VarBlockContext?): String {
        val code = StringBuilder()
        for (i in 1 until ctx!!.children.size) {
            code.append(visit(ctx.children[i]))
        }
        return code.toString()
    }

    override fun visitVarDeclaration(ctx: PascalParser.VarDeclarationContext?): String {
        val code = StringBuilder()
        var i = 0
        while (i < ctx!!.children.size) {
            code.append("${visit(ctx.children[i])};$lineSeparator")
            i += 2
        }
        return code.toString()
    }

    override fun visitVarIdent(ctx: VarIdentContext?): String {
        return "${visit(ctx!!.children[2])} ${visit(ctx.children[0])}"
    }


    override fun visitBody(ctx: BodyContext?): String {
        validateContext(ctx, "body")
        val code = "{$lineSeparator${visit(ctx!!.children[1])}$lineSeparator}"
        return code
    }

    override fun visitFunctionProcedureBlock(ctx: FunctionProcedureBlockContext?): String {
        validateContext(ctx, "functionProcedureDefinition")
        val isFun = ctx!!.children[0].getChild(0).text.equals("function", true)
        val header = visit(ctx.children[0])
        val vars = StringBuilder(if (ctx.childCount == 4) visit(ctx.children[1]) else "")
        if (isFun) {
            if (ctx.children[0].childCount === 8) {
                vars.append("${visit(ctx.children[0].getChild(6))} result; $lineSeparator")
            } else {
                vars.append("${visit(ctx.children[0].getChild(4))} result; $lineSeparator")
            }
            currentFunctionName = ctx.children[0].getChild(1).text
        }
        val body = visit(ctx.children[ctx.children.size - 2])
        val code = StringBuilder("$header{$lineSeparator")
        code.append(vars.toString() + body.substring(2, body.length - 2))
        code.append(if (isFun) "return result;" else "")
        code.append("$lineSeparator}")
        return code.toString()
    }

    private fun funcDefinition(type: String, ident: String, args: String = ""): String {
        val code = StringBuilder()
        code.append(type)
                .append(" ")
                .append(ident)
                .append("(")
                .append(args)
                .append(") ")
        return code.toString()
    }

    override fun visitFunctionDefinition(ctx: FunctionDefinitionContext?): String {
        validateContext(ctx, "functionDefinition")
        when (ctx!!.children.size) {
            8 -> return funcDefinition(visit(ctx.children[6]), ctx.children[1].text, visit(ctx.children[3]))
            7 -> throw TranslatorException("Did you forget parentheses?")
        }

        if (ctx.children[2].childCount != 0) {
            throw TranslatorException("Add parentheses if you want function with arguments")
        }
        return funcDefinition(visit(ctx.children[4]), ctx.children[1].text)
    }

    override fun visitProcedureDefinition(ctx: ProcedureDefinitionContext?): String {
        validateContext(ctx, "procedureDefinition")

        when (ctx!!.children.size) {
            6 -> funcDefinition("void", ctx.children[1].text, visit(ctx.children[3]))
            5 -> throw TranslatorException("Did you forget parentheses?")
        }
        if (ctx.children[2].childCount != 0) {
            throw TranslatorException("Add parentheses if you want function with arguments")
        }
        return funcDefinition("void", ctx.children[1].text)
    }

    override fun visitArguments(ctx: ArgumentsContext?): String {
        validateContext(ctx, "arguments")

        if (ctx!!.children == null || ctx.children.size == 0) {
            return "";
        }

        val code = StringBuilder()
        var index = 0
        while (index < ctx.children.size) {
            val argNames = visit(ctx.children[index]).split(COMMA)
            val type = visit(ctx.children[index + 2])
            argNames.forEach {
                code.append(type)
                        .append(" ")
                        .append(it)
                if (it !== argNames.last()) {
                    code.append(", ")
                }
            }

            index += 4
        }

        return code.toString();
    }

    override fun visitStatements(ctx: StatementsContext?): String {
        validateContext(ctx, "statements")

        val code = StringBuilder()

        var ind = 0;
        while (ind < ctx!!.children.size) {
            code.append("${visit(ctx.children[ind])}$lineSeparator")
            ind += 2
        }
        return code.toString()
    }

    override fun visitStatement(ctx: StatementContext?): String {
        validateContext(ctx, "statement")
        return visit(ctx!!.children[0])
    }

    override fun visitSimpleStatement(ctx: SimpleStatementContext?): String {
        validateContext(ctx, "simpleStatement")
        if (ctx!!.children === null || ctx!!.children.isEmpty()) {
            return ""
        }
        return "${visit(ctx.children[0])}$SEMI"
    }

    override fun visitAssignmentStatement(ctx: AssignmentStatementContext?): String {
        validateContext(ctx, "assignmentStatement")
        return "${visit(ctx!!.children[0])} = ${visit(ctx.children[2])}"
    }

    override fun visitProcedureStatement(ctx: ProcedureStatementContext?): String {
        validateContext(ctx, "procedureStatement")

        val funcName = ctx!!.children[0].text.toLowerCase()
        val code = StringBuilder()

        if (funcName.startsWith("write")) {
            code.append("printf(\"%d")

            if (funcName == "writeln") {
                code.append("\\n")
            }
            if (ctx.children.size != 4)
                throw TranslatorException("write() and writeln() functions should have arguments!")
            code.append("\", ${visit(ctx.children[2])})")
        } else {
            code.append("${visit(ctx.children[0])}(")
            if (ctx.children.size == 4)
                code.append(visit(ctx.children[2]))
            else if (ctx.children.size == 2 || (ctx.children.size == 3 && ctx.children.last().text != ")") ) {
                throw TranslatorException("Syntax for PROCEDURE NAME call: NAME(args) or  NAME() or NAME")
            }
            code.append(")")
        }

        return code.toString()
    }

    private fun expressionCommonPart(ctx: ParserRuleContext?, ruleName: String): String {
        validateContext(ctx, ruleName)

        val code = StringBuilder(visit(ctx!!.children[0]))
        for (i in 1 until ctx.children.size) {
            code.append(" ${visit(ctx.children[i])}")
        }

        return code.toString()
    }

    override fun visitExpression(ctx: ExpressionContext?): String {
        return expressionCommonPart(ctx, "expressionContext")
    }

    override fun visitSimpleExpression(ctx: SimpleExpressionContext?): String {
        return expressionCommonPart(ctx, "simpleExpression")
    }

    override fun visitTerm(ctx: TermContext?): String {
        return expressionCommonPart(ctx, "term")
    }

    override fun visitSignedFactor(ctx: SignedFactorContext?): String {
        validateContext(ctx, "signedFactor")

        return if (ctx!!.children.size == 2)
            "${ctx.children[0].text}${visit(ctx.children[1])}"
        else
            visit(ctx.children[0])
    }

    override fun visitFactor(ctx: FactorContext?): String {
        validateContext(ctx, "factor")

        return when (ctx!!.children.size) {
            1 -> visit(ctx.children[0])
            2 -> "!${visit(ctx.children[1])}"
            3 -> "(${visit(ctx.children[1])})"
            else -> throw TranslatorException("Wrong \'children\' amount in \'visitFactor\'")
        }
    }

    override fun visitNumberConst(ctx: NumberConstContext?): String {
        return ctx!!.text
    }

    override fun visitLiteralConst(ctx: LiteralConstContext?): String {
        return "\"${ctx!!.text.substring(1, ctx.text.length - 1)}\""
    }

    override fun visitNullConst(ctx: NullConstContext?): String {
        return "NULL"
    }

    override fun visitFunctionCall(ctx: FunctionCallContext?): String {
        validateContext(ctx, "functionCall")

        val funcName = ctx!!.children[0].text.toLowerCase()
        val code = StringBuilder()

        if (funcName.startsWith("write")) {
            code.append("printf(\"%d")

            if (funcName == "writeln") {
                code.append(lineSeparator)
            }
            if (ctx.children.size != 4)
                throw TranslatorException("write() and writeln() functions should have arguments!")
            code.append("\", ${visit(ctx.children[2])})")
        } else {
            code.append("${ctx.children[0]}(")
            if (ctx.children.size == 4)
                code.append(visit(ctx.children[2]))
            else if (ctx.children.size == 2 || (ctx.children.size == 3 && ctx.children.last().text != ")") ) {
                throw TranslatorException("Syntax for FUNCTION NAME call: NAME(args) or  NAME() or NAME")
            }
            code.append(")")
        }

        return code.toString()
    }

    override fun visitParameterList(ctx: ParameterListContext?): String {
        val code = StringBuilder(visit(ctx!!.children[0]))
        var i = 2
        while (i < ctx.children.size) {
            code.append(", ${visit(ctx.children[i])}")
            i += 2
        }
        return code.toString()
    }

    override fun visitMultiplicativeoperator(ctx: PascalParser.MultiplicativeoperatorContext?): String {
        return when (ctx!!.text.toLowerCase()) {
            "mod" -> "%"
            "and" -> "&&"
            "div" -> "/"
            else -> ctx.text
        }
    }

    override fun visitAdditiveoperator(ctx: PascalParser.AdditiveoperatorContext?): String {
        if (ctx!!.text.toLowerCase() == "or") {
            return "||"
        }
        return ctx.text
    }

    override fun visitRelationaloperator(ctx: PascalParser.RelationaloperatorContext?): String {

        return when (ctx!!.text.toLowerCase()) {
            "=" -> "=="
            "<>" -> "!="
            else -> ctx.text
        }
    }

    override fun visitVarName(ctx: VarNameContext?): String {
        return if (ctx!!.text == currentFunctionName) "result" else ctx.text
    }

    override fun visitStructuredStatement(ctx: PascalParser.StructuredStatementContext?): String {
        return visit(ctx!!.children[0])
    }

    override fun visitConditionalStatement(ctx: PascalParser.ConditionalStatementContext?): String {
        return visit(ctx!!.children[0])
    }

    override fun visitIfStatement(ctx: IfStatementContext?): String {
        val code = StringBuilder("if (${visit(ctx!!.children[1])}) {$lineSeparator")
        code.append("${visit(ctx.children[3])}$lineSeparator")

        code.append("}")
        if (ctx.children.size > 4) {
            code.append(" else {$lineSeparator")
            code.append("${(visit(ctx.children[5]))}$lineSeparator")
            code.append("}")
        }
        return code.toString()
    }

    override fun visitRepetetiveStatement(ctx: RepetetiveStatementContext?): String {
        return visit(ctx!!.children[0])
    }

    override fun visitWhileStatement(ctx: WhileStatementContext?): String {
        return "while(${visit(ctx!!.children[1])}) {$lineSeparator${visit(ctx.children[3])}$lineSeparator}"
    }

    override fun visitRepeatStatement(ctx: PascalParser.RepeatStatementContext?): String {
        return "do { $lineSeparator${visit(ctx!!.getChild(1))}$lineSeparator} while(!${visit(ctx.getChild(3))})"
    }

    override fun visitForStatement(ctx: PascalParser.ForStatementContext?): String {
        var sign = ""
        var operator = ""
        if (ctx!!.children[4].text.toLowerCase() == "to") {
            sign = "<"
            operator = "++"
        }

        return "for(int ${ctx.children[1].text} = ${visit(ctx.children[3])}$SEMI " +
                " ${ctx.children[1].text} $sign ${visit(ctx.children[5])}$SEMI" +
                " ${ctx.children[1].text}$operator)" +
                " {$lineSeparator${visit(ctx.children[7])}$lineSeparator}"
    }

    override fun visitType(ctx: PascalParser.TypeContext?): String {
        return when (ctx!!.getChild(0).text.toLowerCase()) {
            "integer" -> "int"
            "real" -> "double"
            "bool" -> "bool"
            "string" -> "char *"
            "char" -> "char"
            else -> throw TranslatorException("Unsupported type")
        }
    }

    override fun visitIdentList(ctx: IdentListContext?): String {

        val code = StringBuilder(ctx!!.children[0].text)
        var i = 2
        while (i < ctx.children.size) {
            code.append(", ${ctx.children[i].text}")
            i += 2
        }

        return code.toString()
    }

    override fun visitBool(ctx: PascalParser.BoolContext?): String {
        return ctx!!.text.toLowerCase()
    }

    override fun visitUnsignedNumber(ctx: PascalParser.UnsignedNumberContext?): String {
        return ctx!!.text
    }

}