// Generated from /home/darkey/univer/mt/generator2/src/generator/basegrammar/GrammarScheme.g4 by ANTLR 4.8
package generator.basegrammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarSchemeParser}.
 */
public interface GrammarSchemeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#grammarSpec}.
	 * @param ctx the parse tree
	 */
	void enterGrammarSpec(GrammarSchemeParser.GrammarSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#grammarSpec}.
	 * @param ctx the parse tree
	 */
	void exitGrammarSpec(GrammarSchemeParser.GrammarSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#grammarName}.
	 * @param ctx the parse tree
	 */
	void enterGrammarName(GrammarSchemeParser.GrammarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#grammarName}.
	 * @param ctx the parse tree
	 */
	void exitGrammarName(GrammarSchemeParser.GrammarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(GrammarSchemeParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(GrammarSchemeParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(GrammarSchemeParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(GrammarSchemeParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(GrammarSchemeParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(GrammarSchemeParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(GrammarSchemeParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(GrammarSchemeParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void enterGrammarRule(GrammarSchemeParser.GrammarRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#grammarRule}.
	 * @param ctx the parse tree
	 */
	void exitGrammarRule(GrammarSchemeParser.GrammarRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#tokenRule}.
	 * @param ctx the parse tree
	 */
	void enterTokenRule(GrammarSchemeParser.TokenRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#tokenRule}.
	 * @param ctx the parse tree
	 */
	void exitTokenRule(GrammarSchemeParser.TokenRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#syntaxRule}.
	 * @param ctx the parse tree
	 */
	void enterSyntaxRule(GrammarSchemeParser.SyntaxRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#syntaxRule}.
	 * @param ctx the parse tree
	 */
	void exitSyntaxRule(GrammarSchemeParser.SyntaxRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#names}.
	 * @param ctx the parse tree
	 */
	void enterNames(GrammarSchemeParser.NamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#names}.
	 * @param ctx the parse tree
	 */
	void exitNames(GrammarSchemeParser.NamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#moreNames}.
	 * @param ctx the parse tree
	 */
	void enterMoreNames(GrammarSchemeParser.MoreNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#moreNames}.
	 * @param ctx the parse tree
	 */
	void exitMoreNames(GrammarSchemeParser.MoreNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(GrammarSchemeParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(GrammarSchemeParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(GrammarSchemeParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(GrammarSchemeParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(GrammarSchemeParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(GrammarSchemeParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarSchemeParser#argumentWithType}.
	 * @param ctx the parse tree
	 */
	void enterArgumentWithType(GrammarSchemeParser.ArgumentWithTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarSchemeParser#argumentWithType}.
	 * @param ctx the parse tree
	 */
	void exitArgumentWithType(GrammarSchemeParser.ArgumentWithTypeContext ctx);
}