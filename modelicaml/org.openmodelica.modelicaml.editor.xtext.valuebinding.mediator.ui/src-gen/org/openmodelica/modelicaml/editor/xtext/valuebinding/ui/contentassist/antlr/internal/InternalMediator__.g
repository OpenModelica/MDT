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
T41 : 'avr' ;
T42 : 'size' ;
T43 : 'getResult' ;
T44 : ':' ;
T45 : '.' ;
T46 : 'if' ;
T47 : 'then' ;
T48 : 'elseif' ;
T49 : 'else' ;
T50 : 'or' ;
T51 : 'and' ;
T52 : 'not' ;
T53 : 'initial' ;
T54 : 'der' ;
T55 : ',' ;
T56 : 'for' ;
T57 : '=' ;
T58 : 'in' ;
T59 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7764
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7766
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7768
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7770
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7772
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7774
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7776
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7778
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7780
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/valuebinding/ui/contentassist/antlr/internal/InternalMediator.g" 7782
RULE_ANY_OTHER : .;


