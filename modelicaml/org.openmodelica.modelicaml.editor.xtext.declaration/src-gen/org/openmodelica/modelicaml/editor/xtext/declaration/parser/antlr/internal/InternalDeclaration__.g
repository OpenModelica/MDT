lexer grammar InternalDeclaration;
@header {
package org.openmodelica.modelicaml.editor.xtext.declaration.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : '=' ;
T15 : ':=' ;
T16 : 'if' ;
T17 : ':' ;
T18 : 'then' ;
T19 : 'elseif' ;
T20 : 'else' ;
T21 : 'or' ;
T22 : 'and' ;
T23 : 'not' ;
T24 : '^' ;
T25 : '.^' ;
T26 : '(' ;
T27 : ')' ;
T28 : '[' ;
T29 : ';' ;
T30 : ']' ;
T31 : '{' ;
T32 : '}' ;
T33 : 'end' ;
T34 : 'initial' ;
T35 : 'der' ;
T36 : ',' ;
T37 : '.' ;
T38 : 'for' ;
T39 : 'in' ;
T40 : '*' ;
T41 : '/' ;
T42 : '.*' ;
T43 : './' ;
T44 : '+' ;
T45 : '-' ;
T46 : '.+' ;
T47 : '.-' ;
T48 : '<' ;
T49 : '<=' ;
T50 : '>' ;
T51 : '>=' ;
T52 : '==' ;
T53 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3465
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3467
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3469
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3471
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3473
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3475
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3477
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3479
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3481
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g" 3483
RULE_ANY_OTHER : .;


