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
T39 : 'product' ;
T40 : 'sum' ;
T41 : 'min' ;
T42 : 'max' ;
T43 : 'avr' ;
T44 : 'size' ;
T45 : 'getResult' ;
T46 : ':' ;
T47 : 'if' ;
T48 : 'then' ;
T49 : 'elseif' ;
T50 : 'else' ;
T51 : 'or' ;
T52 : 'and' ;
T53 : 'not' ;
T54 : 'initial' ;
T55 : 'der' ;
T56 : ',' ;
T57 : 'for' ;
T58 : 'in' ;
T59 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8215
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8217
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8219
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8221
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8223
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8225
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8227
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8229
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8231
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalClient.g" 8233
RULE_ANY_OTHER : .;


