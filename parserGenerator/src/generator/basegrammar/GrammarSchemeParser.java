// Generated from /home/darkey/univer/mt/generator2/src/generator/basegrammar/GrammarScheme.g4 by ANTLR 4.8
package generator.basegrammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarSchemeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, VAL=2, OR=3, COLON=4, SEMI=5, CURLY_OPEN=6, CURLY_CLOSE=7, OPEN=8, 
		CLOSE=9, DOT=10, ASTERISK=11, SQUARE_OPEN=12, SQUARE_CLOSE=13, EQUALS_SIGN=14, 
		GRAMMAR=15, IMPORT=16, HEADER=17, FIELDS=18, RETURNS=19, PACKAGE=20, SKIP_RULE=21, 
		TOKEN_NAME=22, IDENTIFIER=23, CODE=24, ARGUMENT=25, REGEX=26, WHITESPACE=27, 
		EPS=28;
	public static final int
		RULE_grammarSpec = 0, RULE_grammarName = 1, RULE_header = 2, RULE_fields = 3, 
		RULE_field = 4, RULE_rules = 5, RULE_grammarRule = 6, RULE_tokenRule = 7, 
		RULE_syntaxRule = 8, RULE_names = 9, RULE_moreNames = 10, RULE_name = 11, 
		RULE_code = 12, RULE_argument = 13, RULE_argumentWithType = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarSpec", "grammarName", "header", "fields", "field", "rules", "grammarRule", 
			"tokenRule", "syntaxRule", "names", "moreNames", "name", "code", "argument", 
			"argumentWithType"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'var'", "'val'", "'|'", "':'", "';'", "'{'", "'}'", "'('", "')'", 
			"'.'", "'*'", "'['", "']'", "'='", "'grammar'", "'import'", "'header'", 
			"'fields'", "'returns'", "'package'", "'-> skip'", null, null, null, 
			null, null, null, "'EPS'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "VAR", "VAL", "OR", "COLON", "SEMI", "CURLY_OPEN", "CURLY_CLOSE", 
			"OPEN", "CLOSE", "DOT", "ASTERISK", "SQUARE_OPEN", "SQUARE_CLOSE", "EQUALS_SIGN", 
			"GRAMMAR", "IMPORT", "HEADER", "FIELDS", "RETURNS", "PACKAGE", "SKIP_RULE", 
			"TOKEN_NAME", "IDENTIFIER", "CODE", "ARGUMENT", "REGEX", "WHITESPACE", 
			"EPS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GrammarScheme.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarSchemeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GrammarSpecContext extends ParserRuleContext {
		public GrammarNameContext grammarName() {
			return getRuleContext(GrammarNameContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public FieldsContext fields() {
			return getRuleContext(FieldsContext.class,0);
		}
		public GrammarSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterGrammarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitGrammarSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitGrammarSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarSpecContext grammarSpec() throws RecognitionException {
		GrammarSpecContext _localctx = new GrammarSpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			grammarName();
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HEADER) {
				{
				setState(31);
				header();
				}
			}

			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FIELDS) {
				{
				setState(34);
				fields();
				}
			}

			setState(37);
			rules();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrammarNameContext extends ParserRuleContext {
		public TerminalNode GRAMMAR() { return getToken(GrammarSchemeParser.GRAMMAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GrammarSchemeParser.IDENTIFIER, 0); }
		public TerminalNode SEMI() { return getToken(GrammarSchemeParser.SEMI, 0); }
		public GrammarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterGrammarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitGrammarName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitGrammarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarNameContext grammarName() throws RecognitionException {
		GrammarNameContext _localctx = new GrammarNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_grammarName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(GRAMMAR);
			setState(40);
			match(IDENTIFIER);
			setState(41);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderContext extends ParserRuleContext {
		public TerminalNode HEADER() { return getToken(GrammarSchemeParser.HEADER, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(HEADER);
			setState(44);
			code();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldsContext extends ParserRuleContext {
		public TerminalNode FIELDS() { return getToken(GrammarSchemeParser.FIELDS, 0); }
		public TerminalNode SQUARE_OPEN() { return getToken(GrammarSchemeParser.SQUARE_OPEN, 0); }
		public TerminalNode SQUARE_CLOSE() { return getToken(GrammarSchemeParser.SQUARE_CLOSE, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public FieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitFields(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitFields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldsContext fields() throws RecognitionException {
		FieldsContext _localctx = new FieldsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fields);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(FIELDS);
			setState(47);
			match(SQUARE_OPEN);
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				field();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VAR || _la==VAL );
			setState(53);
			match(SQUARE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(GrammarSchemeParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(GrammarSchemeParser.IDENTIFIER, i);
		}
		public TerminalNode COLON() { return getToken(GrammarSchemeParser.COLON, 0); }
		public TerminalNode SEMI() { return getToken(GrammarSchemeParser.SEMI, 0); }
		public TerminalNode VAR() { return getToken(GrammarSchemeParser.VAR, 0); }
		public TerminalNode VAL() { return getToken(GrammarSchemeParser.VAL, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==VAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(56);
			match(IDENTIFIER);
			setState(57);
			match(COLON);
			setState(58);
			match(IDENTIFIER);
			setState(59);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulesContext extends ParserRuleContext {
		public List<GrammarRuleContext> grammarRule() {
			return getRuleContexts(GrammarRuleContext.class);
		}
		public GrammarRuleContext grammarRule(int i) {
			return getRuleContext(GrammarRuleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				grammarRule();
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TOKEN_NAME || _la==IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GrammarRuleContext extends ParserRuleContext {
		public TokenRuleContext tokenRule() {
			return getRuleContext(TokenRuleContext.class,0);
		}
		public SyntaxRuleContext syntaxRule() {
			return getRuleContext(SyntaxRuleContext.class,0);
		}
		public GrammarRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterGrammarRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitGrammarRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitGrammarRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarRuleContext grammarRule() throws RecognitionException {
		GrammarRuleContext _localctx = new GrammarRuleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_grammarRule);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				tokenRule();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				syntaxRule();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TokenRuleContext extends ParserRuleContext {
		public TerminalNode TOKEN_NAME() { return getToken(GrammarSchemeParser.TOKEN_NAME, 0); }
		public TerminalNode COLON() { return getToken(GrammarSchemeParser.COLON, 0); }
		public TerminalNode REGEX() { return getToken(GrammarSchemeParser.REGEX, 0); }
		public TerminalNode SEMI() { return getToken(GrammarSchemeParser.SEMI, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public TerminalNode SKIP_RULE() { return getToken(GrammarSchemeParser.SKIP_RULE, 0); }
		public TokenRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tokenRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterTokenRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitTokenRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitTokenRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenRuleContext tokenRule() throws RecognitionException {
		TokenRuleContext _localctx = new TokenRuleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tokenRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(TOKEN_NAME);
			setState(71);
			match(COLON);
			setState(72);
			match(REGEX);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CODE) {
				{
				setState(73);
				code();
				}
			}

			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SKIP_RULE) {
				{
				setState(76);
				match(SKIP_RULE);
				}
			}

			setState(79);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SyntaxRuleContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GrammarSchemeParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(GrammarSchemeParser.COLON, 0); }
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GrammarSchemeParser.SEMI, 0); }
		public ArgumentWithTypeContext argumentWithType() {
			return getRuleContext(ArgumentWithTypeContext.class,0);
		}
		public SyntaxRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_syntaxRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterSyntaxRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitSyntaxRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitSyntaxRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SyntaxRuleContext syntaxRule() throws RecognitionException {
		SyntaxRuleContext _localctx = new SyntaxRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_syntaxRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(IDENTIFIER);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SQUARE_OPEN) {
				{
				setState(82);
				argumentWithType();
				}
			}

			setState(85);
			match(COLON);
			setState(86);
			names(0);
			setState(87);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamesContext extends ParserRuleContext {
		public MoreNamesContext moreNames() {
			return getRuleContext(MoreNamesContext.class,0);
		}
		public TerminalNode EPS() { return getToken(GrammarSchemeParser.EPS, 0); }
		public List<NamesContext> names() {
			return getRuleContexts(NamesContext.class);
		}
		public NamesContext names(int i) {
			return getRuleContext(NamesContext.class,i);
		}
		public TerminalNode OR() { return getToken(GrammarSchemeParser.OR, 0); }
		public NamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamesContext names() throws RecognitionException {
		return names(0);
	}

	private NamesContext names(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NamesContext _localctx = new NamesContext(_ctx, _parentState);
		NamesContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_names, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_NAME:
			case IDENTIFIER:
				{
				setState(90);
				moreNames();
				}
				break;
			case EPS:
				{
				setState(91);
				match(EPS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NamesContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_names);
					setState(94);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(95);
					match(OR);
					setState(96);
					names(3);
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MoreNamesContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public MoreNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moreNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterMoreNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitMoreNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitMoreNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoreNamesContext moreNames() throws RecognitionException {
		MoreNamesContext _localctx = new MoreNamesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_moreNames);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(103); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(102);
					name();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(105); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(107);
				code();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode TOKEN_NAME() { return getToken(GrammarSchemeParser.TOKEN_NAME, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GrammarSchemeParser.IDENTIFIER, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_name);
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(TOKEN_NAME);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(IDENTIFIER);
				setState(113);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(112);
					argument();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeContext extends ParserRuleContext {
		public TerminalNode CODE() { return getToken(GrammarSchemeParser.CODE, 0); }
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_code);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(CODE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public TerminalNode ARGUMENT() { return getToken(GrammarSchemeParser.ARGUMENT, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(ARGUMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentWithTypeContext extends ParserRuleContext {
		public TerminalNode SQUARE_OPEN() { return getToken(GrammarSchemeParser.SQUARE_OPEN, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(GrammarSchemeParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(GrammarSchemeParser.IDENTIFIER, i);
		}
		public TerminalNode COLON() { return getToken(GrammarSchemeParser.COLON, 0); }
		public TerminalNode SQUARE_CLOSE() { return getToken(GrammarSchemeParser.SQUARE_CLOSE, 0); }
		public ArgumentWithTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentWithType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).enterArgumentWithType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarSchemeListener ) ((GrammarSchemeListener)listener).exitArgumentWithType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarSchemeVisitor ) return ((GrammarSchemeVisitor<? extends T>)visitor).visitArgumentWithType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentWithTypeContext argumentWithType() throws RecognitionException {
		ArgumentWithTypeContext _localctx = new ArgumentWithTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_argumentWithType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(SQUARE_OPEN);
			setState(122);
			match(IDENTIFIER);
			setState(123);
			match(COLON);
			setState(124);
			match(IDENTIFIER);
			setState(125);
			match(SQUARE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return names_sempred((NamesContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean names_sempred(NamesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u0082\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\5\2#\n\2\3"+
		"\2\5\2&\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\6\5\64\n\5"+
		"\r\5\16\5\65\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\6\7A\n\7\r\7\16\7B\3"+
		"\b\3\b\5\bG\n\b\3\t\3\t\3\t\3\t\5\tM\n\t\3\t\5\tP\n\t\3\t\3\t\3\n\3\n"+
		"\5\nV\n\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\5\13_\n\13\3\13\3\13\3\13\7\13"+
		"d\n\13\f\13\16\13g\13\13\3\f\6\fj\n\f\r\f\16\fk\3\f\5\fo\n\f\3\r\3\r\3"+
		"\r\5\rt\n\r\5\rv\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\2\3\24\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\3\3\2\3\4\2\u0080"+
		"\2 \3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\b\60\3\2\2\2\n9\3\2\2\2\f@\3\2\2\2\16"+
		"F\3\2\2\2\20H\3\2\2\2\22S\3\2\2\2\24^\3\2\2\2\26i\3\2\2\2\30u\3\2\2\2"+
		"\32w\3\2\2\2\34y\3\2\2\2\36{\3\2\2\2 \"\5\4\3\2!#\5\6\4\2\"!\3\2\2\2\""+
		"#\3\2\2\2#%\3\2\2\2$&\5\b\5\2%$\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\5\f\7"+
		"\2(\3\3\2\2\2)*\7\21\2\2*+\7\31\2\2+,\7\7\2\2,\5\3\2\2\2-.\7\23\2\2./"+
		"\5\32\16\2/\7\3\2\2\2\60\61\7\24\2\2\61\63\7\16\2\2\62\64\5\n\6\2\63\62"+
		"\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\678\7"+
		"\17\2\28\t\3\2\2\29:\t\2\2\2:;\7\31\2\2;<\7\6\2\2<=\7\31\2\2=>\7\7\2\2"+
		">\13\3\2\2\2?A\5\16\b\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\r\3\2"+
		"\2\2DG\5\20\t\2EG\5\22\n\2FD\3\2\2\2FE\3\2\2\2G\17\3\2\2\2HI\7\30\2\2"+
		"IJ\7\6\2\2JL\7\34\2\2KM\5\32\16\2LK\3\2\2\2LM\3\2\2\2MO\3\2\2\2NP\7\27"+
		"\2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\7\7\2\2R\21\3\2\2\2SU\7\31\2\2TV"+
		"\5\36\20\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\6\2\2XY\5\24\13\2YZ\7\7\2"+
		"\2Z\23\3\2\2\2[\\\b\13\1\2\\_\5\26\f\2]_\7\36\2\2^[\3\2\2\2^]\3\2\2\2"+
		"_e\3\2\2\2`a\f\4\2\2ab\7\5\2\2bd\5\24\13\5c`\3\2\2\2dg\3\2\2\2ec\3\2\2"+
		"\2ef\3\2\2\2f\25\3\2\2\2ge\3\2\2\2hj\5\30\r\2ih\3\2\2\2jk\3\2\2\2ki\3"+
		"\2\2\2kl\3\2\2\2ln\3\2\2\2mo\5\32\16\2nm\3\2\2\2no\3\2\2\2o\27\3\2\2\2"+
		"pv\7\30\2\2qs\7\31\2\2rt\5\34\17\2sr\3\2\2\2st\3\2\2\2tv\3\2\2\2up\3\2"+
		"\2\2uq\3\2\2\2v\31\3\2\2\2wx\7\32\2\2x\33\3\2\2\2yz\7\33\2\2z\35\3\2\2"+
		"\2{|\7\16\2\2|}\7\31\2\2}~\7\6\2\2~\177\7\31\2\2\177\u0080\7\17\2\2\u0080"+
		"\37\3\2\2\2\20\"%\65BFLOU^eknsu";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}