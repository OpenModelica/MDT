lexer grammar InternalAlgorithmsection;
@header {
package org.openmodelica.modelicaml.editor.xtext.algorithm.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : ';' ;
T15 : 'return' ;
T16 : 'break' ;
T17 : 'GEN_SIGNAL' ;
T18 : '(' ;
T19 : ')' ;
T20 : 'GEN_INC' ;
T21 : 'GEN_CHANGE' ;
T22 : ':=' ;
T23 : 'when' ;
T24 : 'then' ;
T25 : 'elsewhen' ;
T26 : 'end' ;
T27 : 'while' ;
T28 : 'loop' ;
T29 : 'for' ;
T30 : 'if' ;
T31 : 'elseif' ;
T32 : 'else' ;
T33 : ':' ;
T34 : 'or' ;
T35 : 'and' ;
T36 : 'not' ;
T37 : '^' ;
T38 : '.^' ;
T39 : '[' ;
T40 : ']' ;
T41 : '{' ;
T42 : '}' ;
T43 : 'initial' ;
T44 : 'der' ;
T45 : ',' ;
T46 : '.' ;
T47 : '=' ;
T48 : 'in' ;
T49 : '*' ;
T50 : '/' ;
T51 : '.*' ;
T52 : './' ;
T53 : '+' ;
T54 : '-' ;
T55 : '.+' ;
T56 : '.-' ;
T57 : '<' ;
T58 : '<=' ;
T59 : '>' ;
T60 : '>=' ;
T61 : '==' ;
T62 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4493
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4495
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4497
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4499
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4501
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4503
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4505
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4507
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4509
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g" 4511
RULE_ANY_OTHER : .;


