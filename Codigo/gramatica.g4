/**
 * Definicao da gramatica xpp
 */ 
grammar Xcc;

// producoes para o parser. Alguns tokens sao definidos impliscitamente
program : (classlist)?;
classlist : (classdecl)+;
classdecl : CLASS IDENT (EXTENDS IDENT)?;
classbody : ABRCHAVE (classlist)? (vardecl PTVIR)* (constructdecl)* (methoddecl)* FECHCHAVE;
vardecl : (INT | STRING | IDENT) IDENT (ABRCOL FECHCOL)* (VIRG IDENT ( ABRCOL FECHCOL)*)*;
constructdecl : CONSTRUCTOR methodbody;
methoddecl : (INT | STRING | IDENT) (ABRCOL FECHCOL)* IDENT methodbody;
methodbody : ABRPAR paramlist FECHPAR statement;
paramlist : ((INT | STRING | IDENT) IDENT (ABRCOL FECHCOL)* (VIRG (INT | STRING | IDENT) IDENT (ABRCOL FECHCOL)*)*)?;
statement : (vardecl PTVIR | atribstat PTVIR | readstat PTVIR | returnstat PTVIR | ifstat | forstat PTVIR | ABRCHAVE statlist FECHCHAVE | BREAK PTVIR | PTVIR);
atribstat : lvalue ATR (expression | alocexpression);
printstat : PRINT expression;
readstat : READ lvalue;
returnstat : RETURN (expression)?;
superstat : SUPER ABRPAR arglist FECHPAR;
ifstat : IF ABRPAR expression FECHPAR statement (ELSE statement)?;
forstat : FOR ABRPAR (atribstat)? PTVIR (expression)? PTVIR (atribstat)? FECHPAR statement;
statlist : statement (statlist)?;
lvalue : IDENT (ABRCOL expression FECHCOL | PONTO IDENT ( ABRPAR arglist FECHPAR )?)*;
alocexpression : NEW (IDENT ABRPAR arglist FECHPAR | (INT | STRING | IDENT)( ABRCOL expression FECHCOL)+);
expression : numexpression (( MENOR | MAIOR | MENORIG | MAIORIG | IGUAL | DIFER) numexpression)?;
numexpression : term ((OPMAIS | OPMENOS) term)*;
term : unaryexpr (( OPVEZES | OPDIV | OPMOD) unaryexpr)*;
unaryexpr : (( OPMAIS | OPMENOS ))? factor;
factor : (INTCONSTANT | STRINGCONSTANT | NULL | lvalue | ABRPAR expression FECHPAR);
arglist : (expression (VIRG expression)*)?;

// regras do alex, no formato TOKEN : lexema;
CLASS			: 'class';
EXTENDS 		: 'extends';
ABRCHAVE 		: '{';
PTVIR 			: ';';
FECHCHAVE 		: '}';
INT 			: 'int';
STRING 			: 'string';
ABRCOL 			: '[';
FECHCOL 		: ']';
VIRG 			: ',';
CONSTRUCTOR 	: 'constructor';
ABRPAR 			: '(';
FECHPAR			: ')';
BREAK 			: 'break';
ATR 			: '=';
PRINT 			: 'print';
READ 			: 'read';
RETURN 			: 'return';
SUPER 			: 'super';
IF 				: 'if';
ELSE 			: 'else';
FOR 			: 'for';
PONTO 			: '.';
NEW 			: 'new';
MENOR 			: '<';
MAIOR 			: '>';
MENORIG 		: '<=';
MAIORIG 		: '>=';
IGUAL 			: '==';
DIFER 			: '!=';
OPMAIS 			: '+';
OPMENOS 		: '-';
OPVEZES 		: '*';
OPDIV 			: '/';
OPMOD 			: '%';
NULL 			: 'null';

fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];
fragment DIGIT: [0-9];
fragment ESPECIAL:
(
':'
| '|'
| ';'
| '+'
| '-'
| '?'
| ' '
| '_'
| '['
| ']'
| '('
| ')'
| '{'
| '}'
| '.'
| '<'
| '>'
| '/'
| '*'
| '@'
| '#'
| '$'
| '%'
| '^'
| '&'
| '~'
| ','
| [\u0060]
| [\u005C]
| [\u00C0-\u00C5]
| [\u00C7-\u00CF]
| [\u00D1-\u00D6]
| [\u00D9-\u00DC]
| [\u00E0-\u00E4]
| [\u00E7-\u00EF]
| [\u00F2-\u00F6]
| [\u00F9-\u00FC]
);

INTCONSTANT : (DIGIT)+;
STRINGCONSTANT : ["](LOWERCASE | UPPERCASE | DIGIT | ESPECIAL)*["];
IDENT : [A-Za-z_][A-Za-z_0-9]*;
// ANTLR4 resolve a ambiguidade ao usar a primeira declaracao, por isso declaramos 
// os tokens das palavras reservadas antes do token do IDENT

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
