lexer grammar InternalStatetransitionguardexpression;
@header {
package org.openmodelica.modelicaml.editor.xtext.state.ui.contentassist.antlr.internal;

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
T28 : '^' ;
T29 : '.^' ;
T30 : '(' ;
T31 : ')' ;
T32 : '[' ;
T33 : ']' ;
T34 : ';' ;
T35 : '{' ;
T36 : '}' ;
T37 : 'AFTER' ;
T38 : 'SIGNAL' ;
T39 : 'EVENT' ;
T40 : 'CHANGE' ;
T41 : ':' ;
T42 : 'or' ;
T43 : 'and' ;
T44 : 'not' ;
T45 : 'initial' ;
T46 : 'der' ;
T47 : ',' ;
T48 : '.' ;
T49 : 'for' ;
T50 : '=' ;
T51 : 'in' ;
T52 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6775
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6777
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6779
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6781
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6783
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6785
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6787
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6789
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6791
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.statetransitionguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/state/ui/contentassist/antlr/internal/InternalStatetransitionguardexpression.g" 6793
RULE_ANY_OTHER : .;


