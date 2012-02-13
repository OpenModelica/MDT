lexer grammar InternalMediator;
@header {
package org.openmodelica.modelicaml.editor.xtext.valuebinding.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : '(' ;
T15 : ')' ;
T16 : '[' ;
T17 : ';' ;
T18 : ']' ;
T19 : '{' ;
T20 : '}' ;
T21 : 'end' ;
T22 : 'product' ;
T23 : 'sum' ;
T24 : 'min' ;
T25 : 'max' ;
T26 : 'avg' ;
T27 : 'size' ;
T28 : 'toArray' ;
T29 : 'AND' ;
T30 : 'OR' ;
T31 : 'XOR' ;
T32 : 'getSingleProvider' ;
T33 : ':' ;
T34 : '.' ;
T35 : 'if' ;
T36 : 'then' ;
T37 : 'elseif' ;
T38 : 'else' ;
T39 : 'or' ;
T40 : 'and' ;
T41 : 'not' ;
T42 : '^' ;
T43 : '.^' ;
T44 : 'initial' ;
T45 : 'der' ;
T46 : ',' ;
T47 : 'for' ;
T48 : '=' ;
T49 : 'in' ;
T50 : '*' ;
T51 : '/' ;
T52 : '.*' ;
T53 : './' ;
T54 : '+' ;
T55 : '-' ;
T56 : '.+' ;
T57 : '.-' ;
T58 : '<' ;
T59 : '<=' ;
T60 : '>' ;
T61 : '>=' ;
T62 : '==' ;
T63 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4207
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4209
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4211
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4213
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4215
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4217
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4219
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4221
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4223
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalMediator.g" 4225
RULE_ANY_OTHER : .;


