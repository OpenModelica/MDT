lexer grammar InternalModification;
@header {
package org.openmodelica.modelicaml.editor.xtext.modification.ui.contentassist.antlr.internal;

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
T28 : 'class' ;
T29 : 'model' ;
T30 : 'record' ;
T31 : 'block' ;
T32 : 'type' ;
T33 : 'package' ;
T34 : 'function' ;
T35 : 'operator' ;
T36 : ':' ;
T37 : 'flow' ;
T38 : 'stream' ;
T39 : 'discrete' ;
T40 : 'parameter' ;
T41 : 'constant' ;
T42 : 'input' ;
T43 : 'output' ;
T44 : 'return' ;
T45 : 'break' ;
T46 : '^' ;
T47 : '.^' ;
T48 : '=' ;
T49 : '.' ;
T50 : ':=' ;
T51 : '(' ;
T52 : ')' ;
T53 : 'each' ;
T54 : 'final' ;
T55 : 'replaceable' ;
T56 : 'constrainedby' ;
T57 : 'redeclare' ;
T58 : ',' ;
T59 : 'if' ;
T60 : 'encapsulated' ;
T61 : 'partial' ;
T62 : 'expandable' ;
T63 : 'connector' ;
T64 : 'end' ;
T65 : 'enumeration' ;
T66 : 'der' ;
T67 : 'extends' ;
T68 : 'public' ;
T69 : 'protected' ;
T70 : 'external' ;
T71 : ';' ;
T72 : 'inner' ;
T73 : 'outer' ;
T74 : 'initial' ;
T75 : 'equation' ;
T76 : 'when' ;
T77 : 'then' ;
T78 : 'elsewhen' ;
T79 : 'elseif' ;
T80 : 'else' ;
T81 : 'for' ;
T82 : 'loop' ;
T83 : 'connect' ;
T84 : 'algorithm' ;
T85 : 'while' ;
T86 : 'annotation' ;
T87 : 'import' ;
T88 : 'or' ;
T89 : 'and' ;
T90 : 'not' ;
T91 : '[' ;
T92 : ']' ;
T93 : '{' ;
T94 : '}' ;
T95 : 'in' ;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19801
RULE_UNSIGNED_NUMBER : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19803
RULE_BOOL_VAL : ('true'|'false');

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19805
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19807
RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19809
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'.')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19811
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19813
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19815
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19817
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openmodelica.modelicaml.editor.xtext.modification.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/modification/ui/contentassist/antlr/internal/InternalModification.g" 19819
RULE_ANY_OTHER : .;


