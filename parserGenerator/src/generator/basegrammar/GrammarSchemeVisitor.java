// Generated from /home/darkey/univer/mt/generator2/src/generator/basegrammar/GrammarScheme.g4 by ANTLR 4.8
package generator.basegrammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarSchemeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarSchemeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#grammarSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarSpec(GrammarSchemeParser.GrammarSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#grammarName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarName(GrammarSchemeParser.GrammarNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(GrammarSchemeParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFields(GrammarSchemeParser.FieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(GrammarSchemeParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(GrammarSchemeParser.RulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#grammarRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarRule(GrammarSchemeParser.GrammarRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#tokenRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTokenRule(GrammarSchemeParser.TokenRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#syntaxRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSyntaxRule(GrammarSchemeParser.SyntaxRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNames(GrammarSchemeParser.NamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#moreNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreNames(GrammarSchemeParser.MoreNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(GrammarSchemeParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(GrammarSchemeParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(GrammarSchemeParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarSchemeParser#argumentWithType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentWithType(GrammarSchemeParser.ArgumentWithTypeContext ctx);
}