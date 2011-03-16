lexer grammar InternalConditionalattribute;
@header {
package org.openmodelica.modelicaml.editor.xtext.condition.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : 'if' ;
T15 : ':' ;
T16 : 'then' ;
T17 : 'elseif' ;
T18 : 'else' ;
T19 : 'or' ;
T20 : 'and' ;
T21 : 'not' ;
T22 : '^' ;
T23 : '.^' ;
T24 : '(' ;
T25 : ')' ;
T26 : '[' ;
T27 : ';' ;
T28 : ']' ;
T29 : '{' ;
T30 : '}' ;
T31 : 'end' ;
T32 : 'initial' ;
T33 : 'der' ;
T34 : ',' ;
T35 : '.' ;
T36 : 'for' ;
T37 : '=' ;
T38 : 'in' ;
T39 : '*' ;
T40 : '/' ;
T41 : '.*' ;
T42 : './' ;
T43 : '+' ;
T44 : '-' ;
T45 : '.+' ;
T46 : '.-' ;
T47 : '<' ;
T48 : '<=' ;
T49 : '>' ;
T50 : '>=' ;
T51 : '==' ;
T52 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3274
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3276
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3278
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3280
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3282
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3284
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3286
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3288
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3290
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g" 3292
RULE_ANY_OTHER : .;


