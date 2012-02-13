lexer grammar InternalStatetransitionguardexpression;
@header {
package org.openmodelica.modelicaml.editor.xtext.state.parser.antlr.internal;

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
T22 : 'else' ;
T23 : 'AFTER' ;
T24 : 'SIGNAL' ;
T25 : 'EVENT' ;
T26 : 'CHANGE' ;
T27 : ':' ;
T28 : 'or' ;
T29 : 'and' ;
T30 : 'not' ;
T31 : '^' ;
T32 : '.^' ;
T33 : 'initial' ;
T34 : 'der' ;
T35 : ',' ;
T36 : '.' ;
T37 : 'for' ;
T38 : '=' ;
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

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3507
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3509
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3511
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3513
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3515
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3517
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3519
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3521
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3523
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3525
RULE_ANY_OTHER : .;


