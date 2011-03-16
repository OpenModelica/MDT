lexer grammar InternalActivitycontrolflowguardexpression;
@header {
package org.openmodelica.modelicaml.editor.xtext.activity.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : 'else' ;
T15 : ':' ;
T16 : 'or' ;
T17 : 'and' ;
T18 : 'not' ;
T19 : '^' ;
T20 : '.^' ;
T21 : '(' ;
T22 : ')' ;
T23 : '[' ;
T24 : ';' ;
T25 : ']' ;
T26 : '{' ;
T27 : '}' ;
T28 : 'end' ;
T29 : 'initial' ;
T30 : 'der' ;
T31 : ',' ;
T32 : '.' ;
T33 : 'for' ;
T34 : '=' ;
T35 : 'in' ;
T36 : '*' ;
T37 : '/' ;
T38 : '.*' ;
T39 : './' ;
T40 : '+' ;
T41 : '-' ;
T42 : '.+' ;
T43 : '.-' ;
T44 : '<' ;
T45 : '<=' ;
T46 : '>' ;
T47 : '>=' ;
T48 : '==' ;
T49 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3074
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3076
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3078
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3080
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3082
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3084
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3086
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3088
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3090
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/parser/antlr/internal/InternalActivitycontrolflowguardexpression.g" 3092
RULE_ANY_OTHER : .;


