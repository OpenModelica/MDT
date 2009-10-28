lexer grammar InternalModelicaBase;
@header {
package org.modelica.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T42 : 'within' ;
T43 : 'final' ;
T44 : 'encapsulated' ;
T45 : 'partial' ;
T46 : 'expandable' ;
T47 : 'extends' ;
T48 : 'end' ;
T49 : 'function' ;
T50 : 'der' ;
T51 : 'overload' ;
T52 : 'enumeration' ;
T53 : 'external' ;
T54 : 'public' ;
T55 : 'protected' ;
T56 : 'redeclare' ;
T57 : 'inner' ;
T58 : 'outer' ;
T59 : 'replaceable' ;
T60 : 'import' ;
T61 : 'constrainedby' ;
T62 : 'flow' ;
T63 : 'stream' ;
T64 : 'discrete' ;
T65 : 'parameter' ;
T66 : 'constant' ;
T67 : 'input' ;
T68 : 'output' ;
T69 : 'if' ;
T70 : 'each' ;
T71 : 'equation' ;
T72 : 'algorithm' ;
T73 : 'initial' ;
T74 : 'break' ;
T75 : 'return' ;
T76 : 'then' ;
T77 : 'else' ;
T78 : 'for' ;
T79 : 'loop' ;
T80 : 'while' ;
T81 : 'when' ;
T82 : 'elsewhen' ;
T83 : 'elseif' ;
T84 : 'connect' ;
T85 : 'in' ;
T86 : 'or' ;
T87 : 'and' ;
T88 : 'not' ;
T89 : 'false' ;
T90 : 'true' ;
T91 : 'annotation' ;
T92 : 'class' ;
T93 : 'model' ;
T94 : 'record' ;
T95 : 'block' ;
T96 : 'connector' ;
T97 : 'type' ;
T98 : 'package' ;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6731
RULE_DOT : '.';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6733
RULE_LPAR : '(';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6735
RULE_RPAR : ')';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6737
RULE_LBRACK : '[';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6739
RULE_RBRACK : ']';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6741
RULE_LBRACE : '{';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6743
RULE_RBRACE : '}';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6745
RULE_EQUALS : '=';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6747
RULE_ASSIGN : ':=';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6749
RULE_COMMA : ',';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6751
RULE_COLON : ':';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6753
RULE_SEMICOLON : ';';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6755
RULE_PLUS_EW : '.+';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6757
RULE_MINUS_EW : '.-';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6759
RULE_STAR_EW : '.*';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6761
RULE_SLASH_EW : './';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6763
RULE_POWER_EW : '.^';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6765
RULE_STAR : '*' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6767
RULE_MINUS : '-' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6769
RULE_PLUS : '+' ('.'|'&')?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6771
RULE_LESS : '<' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6773
RULE_LESSEQ : '<=' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6775
RULE_LESSGT : ('!=' '.'?|'<>' '.'?);

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6777
RULE_GREATER : '>' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6779
RULE_GREATEREQ : '>=' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6781
RULE_EQEQ : '==' ('.'|'&')?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6783
RULE_POWER : '^' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6785
RULE_SLASH : '/' '.'?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6787
RULE_UNSIGNED_REAL : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6789
RULE_UNSIGNED_INTEGER : ('0'..'9')+;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6791
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6793
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6795
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6797
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6799
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6801
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6803
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g" 6805
RULE_ANY_OTHER : .;


