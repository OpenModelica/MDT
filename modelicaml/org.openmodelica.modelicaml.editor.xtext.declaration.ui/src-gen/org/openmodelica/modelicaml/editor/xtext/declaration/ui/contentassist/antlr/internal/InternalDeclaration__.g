lexer grammar InternalDeclaration;
@header {
package org.openmodelica.modelicaml.editor.xtext.declaration.ui.contentassist.antlr.internal;

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
T31 : ':=' ;
T32 : 'if' ;
T33 : ':' ;
T34 : 'then' ;
T35 : 'elseif' ;
T36 : 'else' ;
T37 : 'or' ;
T38 : 'and' ;
T39 : 'not' ;
T40 : '(' ;
T41 : ')' ;
T42 : '[' ;
T43 : ']' ;
T44 : ';' ;
T45 : '{' ;
T46 : '}' ;
T47 : 'initial' ;
T48 : 'der' ;
T49 : ',' ;
T50 : '.' ;
T51 : 'for' ;
T52 : 'in' ;
T53 : 'end' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6875
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6877
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6879
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6881
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6883
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6885
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6887
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6889
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6891
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.declaration.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/ui/contentassist/antlr/internal/InternalDeclaration.g" 6893
RULE_ANY_OTHER : .;


