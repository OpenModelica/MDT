lexer grammar InternalModelicaBase;
@header {
package org.modelica.xtext.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;
}

T42 : 'flow' ;
T43 : 'stream' ;
T44 : 'discrete' ;
T45 : 'parameter' ;
T46 : 'constant' ;
T47 : 'input' ;
T48 : 'output' ;
T49 : 'break' ;
T50 : 'return' ;
T51 : 'false' ;
T52 : 'true' ;
T53 : 'end' ;
T54 : 'class' ;
T55 : 'model' ;
T56 : 'record' ;
T57 : 'block' ;
T58 : 'connector' ;
T59 : 'type' ;
T60 : 'package' ;
T61 : 'function' ;
T62 : 'within' ;
T63 : 'final' ;
T64 : 'extends' ;
T65 : 'der' ;
T66 : 'overload' ;
T67 : 'enumeration' ;
T68 : 'external' ;
T69 : 'public' ;
T70 : 'protected' ;
T71 : 'redeclare' ;
T72 : 'inner' ;
T73 : 'outer' ;
T74 : 'replaceable' ;
T75 : 'import' ;
T76 : 'constrainedby' ;
T77 : 'if' ;
T78 : 'each' ;
T79 : 'equation' ;
T80 : 'algorithm' ;
T81 : 'initial' ;
T82 : 'then' ;
T83 : 'else' ;
T84 : 'for' ;
T85 : 'loop' ;
T86 : 'while' ;
T87 : 'when' ;
T88 : 'elsewhen' ;
T89 : 'elseif' ;
T90 : 'connect' ;
T91 : 'in' ;
T92 : 'or' ;
T93 : 'and' ;
T94 : 'not' ;
T95 : 'annotation' ;
T96 : 'encapsulated' ;
T97 : 'partial' ;
T98 : 'expandable' ;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14230
RULE_DOT : '.';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14232
RULE_LPAR : '(';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14234
RULE_RPAR : ')';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14236
RULE_LBRACK : '[';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14238
RULE_RBRACK : ']';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14240
RULE_LBRACE : '{';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14242
RULE_RBRACE : '}';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14244
RULE_EQUALS : '=';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14246
RULE_ASSIGN : ':=';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14248
RULE_COMMA : ',';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14250
RULE_COLON : ':';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14252
RULE_SEMICOLON : ';';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14254
RULE_PLUS_EW : '.+';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14256
RULE_MINUS_EW : '.-';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14258
RULE_STAR_EW : '.*';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14260
RULE_SLASH_EW : './';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14262
RULE_POWER_EW : '.^';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14264
RULE_STAR : '*' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14266
RULE_MINUS : '-' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14268
RULE_PLUS : '+' ('.'|'&')?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14270
RULE_LESS : '<' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14272
RULE_LESSEQ : '<=' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14274
RULE_LESSGT : ('!=' '.'?|'<>' '.'?);

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14276
RULE_GREATER : '>' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14278
RULE_GREATEREQ : '>=' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14280
RULE_EQEQ : '==' ('.'|'&')?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14282
RULE_POWER : '^' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14284
RULE_SLASH : '/' '.'?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14286
RULE_UNSIGNED_REAL : (('0'..'9')+ '.' ('0'..'9')* (('E'|'e') ('+'|'-')? ('0'..'9')+)?|('0'..'9')+ ('E'|'e') ('+'|'-')? ('0'..'9')+);

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14288
RULE_UNSIGNED_INTEGER : ('0'..'9')+;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14290
RULE_IDENT : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14292
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14294
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14296
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14298
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14300
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14302
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.modelica.xtext.ui/src-gen/org/modelica/xtext/contentassist/antlr/internal/InternalModelicaBase.g" 14304
RULE_ANY_OTHER : .;


