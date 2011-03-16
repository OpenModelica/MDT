lexer grammar InternalAlgorithmsection;
@header {
package org.openmodelica.modelicaml.editor.xtext.algorithm.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T14 : '*' ;
T15 : '/' ;
T16 : '.*' ;
T17 : './' ;
T18 : '+' ;
T19 : '-' ;
T20 : '.+' ;
T21 : '.-' ;
T22 : '<' ;
T23 : '<=' ;
T24 : '>' ;
T25 : '>=' ;
T26 : '==' ;
T27 : '<>' ;
T28 : 'return' ;
T29 : 'break' ;
T30 : '^' ;
T31 : '.^' ;
T32 : ';' ;
T33 : 'GEN_SIGNAL' ;
T34 : '(' ;
T35 : ')' ;
T36 : 'GEN_INC' ;
T37 : 'GEN_CHANGE' ;
T38 : ':=' ;
T39 : 'when' ;
T40 : 'then' ;
T41 : 'end' ;
T42 : 'elsewhen' ;
T43 : 'while' ;
T44 : 'loop' ;
T45 : 'for' ;
T46 : 'if' ;
T47 : 'elseif' ;
T48 : 'else' ;
T49 : ':' ;
T50 : 'or' ;
T51 : 'and' ;
T52 : 'not' ;
T53 : '[' ;
T54 : ']' ;
T55 : '{' ;
T56 : '}' ;
T57 : 'initial' ;
T58 : 'der' ;
T59 : ',' ;
T60 : '.' ;
T61 : '=' ;
T62 : 'in' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9848
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9850
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9852
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9854
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9856
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9858
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9860
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9862
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9864
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g" 9866
RULE_ANY_OTHER : .;


