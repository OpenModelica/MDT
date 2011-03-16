lexer grammar InternalModification;
@header {
package org.openmodelica.modelicaml.editor.xtext.modification.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T14 : '=' ;
T15 : '.' ;
T16 : ':=' ;
T17 : '(' ;
T18 : ')' ;
T19 : 'each' ;
T20 : 'final' ;
T21 : 'replaceable' ;
T22 : 'constrainedby' ;
T23 : 'redeclare' ;
T24 : ',' ;
T25 : 'if' ;
T26 : 'encapsulated' ;
T27 : 'partial' ;
T28 : 'class' ;
T29 : 'model' ;
T30 : 'record' ;
T31 : 'block' ;
T32 : 'expandable' ;
T33 : 'connector' ;
T34 : 'type' ;
T35 : 'package' ;
T36 : 'function' ;
T37 : 'operator' ;
T38 : 'end' ;
T39 : 'enumeration' ;
T40 : ':' ;
T41 : 'der' ;
T42 : 'extends' ;
T43 : 'flow' ;
T44 : 'stream' ;
T45 : 'discrete' ;
T46 : 'parameter' ;
T47 : 'constant' ;
T48 : 'input' ;
T49 : 'output' ;
T50 : 'public' ;
T51 : 'protected' ;
T52 : 'external' ;
T53 : ';' ;
T54 : 'inner' ;
T55 : 'outer' ;
T56 : 'initial' ;
T57 : 'equation' ;
T58 : 'when' ;
T59 : 'then' ;
T60 : 'elsewhen' ;
T61 : 'elseif' ;
T62 : 'else' ;
T63 : 'for' ;
T64 : 'loop' ;
T65 : 'connect' ;
T66 : 'algorithm' ;
T67 : 'return' ;
T68 : 'break' ;
T69 : 'while' ;
T70 : 'annotation' ;
T71 : 'import' ;
T72 : '*' ;
T73 : 'or' ;
T74 : 'and' ;
T75 : 'not' ;
T76 : '^' ;
T77 : '.^' ;
T78 : '[' ;
T79 : ']' ;
T80 : '{' ;
T81 : '}' ;
T82 : 'in' ;
T83 : '/' ;
T84 : '.*' ;
T85 : './' ;
T86 : '+' ;
T87 : '-' ;
T88 : '.+' ;
T89 : '.-' ;
T90 : '<' ;
T91 : '<=' ;
T92 : '>' ;
T93 : '>=' ;
T94 : '==' ;
T95 : '<>' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8155
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8157
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8159
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8161
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8163
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8165
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8167
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8169
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8171
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/parser/antlr/internal/InternalModification.g" 8173
RULE_ANY_OTHER : .;


