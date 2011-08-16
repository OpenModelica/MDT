lexer grammar InternalMediator;
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
T30 : '(' ;
T31 : ')' ;
T32 : '[' ;
T33 : ']' ;
T34 : ';' ;
T35 : '{' ;
T36 : '}' ;
T37 : 'product' ;
T38 : 'sum' ;
T39 : 'min' ;
T40 : 'max' ;
T41 : 'avg' ;
T42 : 'size' ;
T43 : 'toArray' ;
T44 : 'AND' ;
T45 : 'OR' ;
T46 : 'XOR' ;
T47 : 'getSingleProvider' ;
T48 : ':' ;
T49 : '.' ;
T50 : 'if' ;
T51 : 'then' ;
T52 : 'elseif' ;
T53 : 'else' ;
T54 : 'or' ;
T55 : 'and' ;
T56 : 'not' ;
T57 : 'initial' ;
T58 : 'der' ;
T59 : ',' ;
T60 : 'for' ;
T61 : '=' ;
T62 : 'in' ;
T63 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8212
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8214
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8216
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8218
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8220
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8222
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8224
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8226
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8228
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 8230
RULE_ANY_OTHER : .;


