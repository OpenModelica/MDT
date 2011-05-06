lexer grammar InternalClient;
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
T26 : 'getResult' ;
T27 : ':' ;
T28 : '.' ;
T29 : 'if' ;
T30 : 'then' ;
T31 : 'elseif' ;
T32 : 'else' ;
T33 : 'or' ;
T34 : 'and' ;
T35 : 'not' ;
T36 : '^' ;
T37 : '.^' ;
T38 : 'initial' ;
T39 : 'der' ;
T40 : ',' ;
T41 : 'for' ;
T42 : '=' ;
T43 : 'in' ;
T44 : '*' ;
T45 : '/' ;
T46 : '.*' ;
T47 : './' ;
T48 : '+' ;
T49 : '-' ;
T50 : '.+' ;
T51 : '.-' ;
T52 : '<' ;
T53 : '<=' ;
T54 : '>' ;
T55 : '>=' ;
T56 : '==' ;
T57 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3819
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3821
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3823
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3825
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3827
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3829
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3831
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3833
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3835
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.client/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/parser/antlr/internal/InternalClient.g" 3837
RULE_ANY_OTHER : .;


