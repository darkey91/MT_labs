grammar GrammarScheme;

options {
    language = Java;
}

grammarSpec
    : grammarName header? fields? rules ;
grammarName
    : GRAMMAR IDENTIFIER SEMI;
header
    : HEADER code ;
fields
    : FIELDS SQUARE_OPEN field+ SQUARE_CLOSE;
field
    : (VAR | VAL) IDENTIFIER COLON IDENTIFIER SEMI ;
rules
    : grammarRule+ ;
grammarRule
    : tokenRule | syntaxRule ;
tokenRule
    : TOKEN_NAME COLON REGEX code? SKIP_RULE? SEMI ;
syntaxRule
    : IDENTIFIER argumentWithType? COLON names SEMI ;
names
    : moreNames | names OR names | EPS;
moreNames
    : name+ code? ;
name
    : TOKEN_NAME | IDENTIFIER argument? ;
code                          : CODE ;
argument                      : ARGUMENT ;
argumentWithType              : SQUARE_OPEN IDENTIFIER COLON IDENTIFIER SQUARE_CLOSE ;

VAR :
    'var';
VAL :
    'val';
OR                            : '|' ;
COLON                         : ':' ;
SEMI                          : ';' ;
CURLY_OPEN                    : '{' ;
CURLY_CLOSE                   : '}' ;
OPEN                          : '(' ;
CLOSE                         : ')' ;
DOT                           : '.' ;
ASTERISK                      : '*' ;
SQUARE_OPEN                   : '[' ;
SQUARE_CLOSE                  : ']' ;
EQUALS_SIGN                   : '=' ;
GRAMMAR                       : 'grammar' ;
IMPORT                        : 'import' ;
HEADER                        : 'header' ;
FIELDS                        : 'fields' ;
RETURNS                       : 'returns' ;
PACKAGE                       : 'package' ;
SKIP_RULE                     : '-> skip' ;
TOKEN_NAME                    : [A-Z_]+ ;
IDENTIFIER                    : [A-Z]?[a-z_]+ ;
CODE                          : CURLY_OPEN .+? CURLY_CLOSE ;
ARGUMENT                      : OPEN .+? CLOSE ;
REGEX                         : '"'.*?'"' ;
WHITESPACE                    : [ \t\r\n] -> skip ;
EPS : 'EPS';