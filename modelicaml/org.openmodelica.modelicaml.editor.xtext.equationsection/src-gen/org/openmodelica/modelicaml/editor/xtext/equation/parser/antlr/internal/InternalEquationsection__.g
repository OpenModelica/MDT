lexer grammar InternalEquationsection;
@header {
package org.openmodelica.modelicaml.editor.xtext.equation.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : ';' ;
T15 : '=' ;
T16 : 'when' ;
T17 : 'then' ;
T18 : 'elsewhen' ;
T19 : 'end' ;
T20 : 'if' ;
T21 : 'elseif' ;
T22 : 'else' ;
T23 : 'for' ;
T24 : 'loop' ;
T25 : 'connect' ;
T26 : '(' ;
T27 : ',' ;
T28 : ')' ;
T29 : ':' ;
T30 : 'or' ;
T31 : 'and' ;
T32 : 'not' ;
T33 : '^' ;
T34 : '.^' ;
T35 : '[' ;
T36 : ']' ;
T37 : '{' ;
T38 : '}' ;
T39 : 'initial' ;
T40 : 'der' ;
T41 : '.' ;
T42 : 'in' ;
T43 : '*' ;
T44 : '/' ;
T45 : '.*' ;
T46 : './' ;
T47 : '+' ;
T48 : '-' ;
T49 : '.+' ;
T50 : '.-' ;
T51 : '<' ;
T52 : '<=' ;
T53 : '>' ;
T54 : '>=' ;
T55 : '==' ;
T56 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3968
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3970
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3972
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3974
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3976
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3978
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3980
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3982
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3984
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g" 3986
RULE_ANY_OTHER : .;


