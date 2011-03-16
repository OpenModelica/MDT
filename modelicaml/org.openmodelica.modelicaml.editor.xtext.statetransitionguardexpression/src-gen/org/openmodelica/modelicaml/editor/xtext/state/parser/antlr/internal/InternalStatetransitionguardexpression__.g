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
T22 : 'AFTER' ;
T23 : 'SIGNAL' ;
T24 : 'EVENT' ;
T25 : 'CHANGE' ;
T26 : ':' ;
T27 : 'or' ;
T28 : 'and' ;
T29 : 'not' ;
T30 : '^' ;
T31 : '.^' ;
T32 : 'initial' ;
T33 : 'der' ;
T34 : ',' ;
T35 : '.' ;
T36 : 'for' ;
T37 : '=' ;
T38 : 'in' ;
T39 : '*' ;
T40 : '/' ;
T41 : '.*' ;
T42 : './' ;
T43 : '+' ;
T44 : '-' ;
T45 : '.+' ;
T46 : '.-' ;
T47 : '<' ;
T48 : '<=' ;
T49 : '>' ;
T50 : '>=' ;
T51 : '==' ;
T52 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3485
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3487
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3489
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3491
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3493
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3495
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3497
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3499
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3501
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/state/parser/antlr/internal/InternalStatetransitionguardexpression.g" 3503
RULE_ANY_OTHER : .;


