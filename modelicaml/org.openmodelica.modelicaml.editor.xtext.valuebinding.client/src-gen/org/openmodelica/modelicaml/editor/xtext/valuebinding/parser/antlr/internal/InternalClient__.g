lexer grammar InternalClient;
@header {
package org.openmodelica.modelicaml.editor.xtext.valuebinding.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : '=' ;
T15 : ';' ;
T16 : '.' ;
T17 : '(' ;
T18 : ')' ;
T19 : '[' ;
T20 : ']' ;
T21 : '{' ;
T22 : '}' ;
T23 : 'end' ;
T24 : 'product' ;
T25 : 'sum' ;
T26 : 'min' ;
T27 : 'max' ;
T28 : 'avr' ;
T29 : 'size' ;
T30 : 'getResult' ;
T31 : ':' ;
T32 : 'if' ;
T33 : 'then' ;
T34 : 'elseif' ;
T35 : 'else' ;
T36 : 'or' ;
T37 : 'and' ;
T38 : 'not' ;
T39 : '^' ;
T40 : '.^' ;
T41 : 'initial' ;
T42 : 'der' ;
T43 : ',' ;
T44 : 'for' ;
T45 : 'in' ;
T46 : '*' ;
T47 : '/' ;
T48 : '.*' ;
T49 : './' ;
T50 : '+' ;
T51 : '-' ;
T52 : '.+' ;
T53 : '.-' ;
T54 : '<' ;
T55 : '<=' ;
T56 : '>' ;
T57 : '>=' ;
T58 : '==' ;
T59 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4108
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4110
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4112
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4114
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4116
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4118
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4120
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4122
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4124
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 4126
RULE_ANY_OTHER : .;


