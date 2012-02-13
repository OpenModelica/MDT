lexer grammar InternalClient;
@header {
package org.openmodelica.modelicaml.editor.xtext.valuebinding.ui.contentassist.antlr.internal;

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
T30 : '=' ;
T31 : ';' ;
T32 : '.' ;
T33 : '(' ;
T34 : ')' ;
T35 : '[' ;
T36 : ']' ;
T37 : '{' ;
T38 : '}' ;
T39 : 'getBinding' ;
T40 : ':' ;
T41 : 'if' ;
T42 : 'then' ;
T43 : 'elseif' ;
T44 : 'else' ;
T45 : 'or' ;
T46 : 'and' ;
T47 : 'not' ;
T48 : 'initial' ;
T49 : 'der' ;
T50 : ',' ;
T51 : 'for' ;
T52 : 'in' ;
T53 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7399
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7401
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7403
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7405
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7407
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7409
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7411
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7413
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7415
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 7417
RULE_ANY_OTHER : .;


