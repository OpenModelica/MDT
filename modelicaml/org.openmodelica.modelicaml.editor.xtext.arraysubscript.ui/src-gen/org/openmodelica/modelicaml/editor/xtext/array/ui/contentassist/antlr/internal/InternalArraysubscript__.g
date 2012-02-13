lexer grammar InternalArraysubscript;
@header {
package org.openmodelica.modelicaml.editor.xtext.array.ui.contentassist.antlr.internal;

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
T28 : ':' ;
T29 : '^' ;
T30 : '.^' ;
T31 : 'if' ;
T32 : 'then' ;
T33 : 'elseif' ;
T34 : 'else' ;
T35 : 'or' ;
T36 : 'and' ;
T37 : 'not' ;
T38 : '(' ;
T39 : ')' ;
T40 : '[' ;
T41 : ']' ;
T42 : ';' ;
T43 : '{' ;
T44 : '}' ;
T45 : 'initial' ;
T46 : 'der' ;
T47 : ',' ;
T48 : '.' ;
T49 : 'for' ;
T50 : '=' ;
T51 : 'in' ;
T52 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6387
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6389
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6391
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6393
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6395
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6397
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6399
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6401
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6403
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.arraysubscript.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/array/ui/contentassist/antlr/internal/InternalArraysubscript.g" 6405
RULE_ANY_OTHER : .;


