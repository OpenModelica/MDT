lexer grammar InternalEquationsection;
@header {
package org.openmodelica.modelicaml.editor.xtext.equation.ui.contentassist.antlr.internal;

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
T30 : ';' ;
T31 : '=' ;
T32 : 'when' ;
T33 : 'then' ;
T34 : 'end' ;
T35 : 'elsewhen' ;
T36 : 'if' ;
T37 : 'elseif' ;
T38 : 'else' ;
T39 : 'for' ;
T40 : 'loop' ;
T41 : 'connect' ;
T42 : '(' ;
T43 : ',' ;
T44 : ')' ;
T45 : ':' ;
T46 : 'or' ;
T47 : 'and' ;
T48 : 'not' ;
T49 : '[' ;
T50 : ']' ;
T51 : '{' ;
T52 : '}' ;
T53 : 'initial' ;
T54 : 'der' ;
T55 : '.' ;
T56 : 'in' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8717
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8719
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8721
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8723
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8725
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8727
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8729
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8731
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8733
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.equationsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/ui/contentassist/antlr/internal/InternalEquationsection.g" 8735
RULE_ANY_OTHER : .;


