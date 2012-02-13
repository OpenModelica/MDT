lexer grammar InternalActivitycontrolflowguardexpression;
@header {
package org.openmodelica.modelicaml.editor.xtext.activity.ui.contentassist.antlr.internal;

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
T30 : ':' ;
T31 : 'or' ;
T32 : 'and' ;
T33 : 'not' ;
T34 : '(' ;
T35 : ')' ;
T36 : '[' ;
T37 : ']' ;
T38 : ';' ;
T39 : '{' ;
T40 : '}' ;
T41 : 'initial' ;
T42 : 'der' ;
T43 : ',' ;
T44 : '.' ;
T45 : 'for' ;
T46 : '=' ;
T47 : 'in' ;
T48 : 'else' ;
T49 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5938
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5940
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5942
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5944
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5946
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5948
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5950
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5952
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5954
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.activitycontrolflowguardexpression.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/activity/ui/contentassist/antlr/internal/InternalActivitycontrolflowguardexpression.g" 5956
RULE_ANY_OTHER : .;


