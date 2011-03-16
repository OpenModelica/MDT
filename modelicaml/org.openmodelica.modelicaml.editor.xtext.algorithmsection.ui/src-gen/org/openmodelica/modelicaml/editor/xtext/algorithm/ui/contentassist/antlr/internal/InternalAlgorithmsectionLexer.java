package org.openmodelica.modelicaml.editor.xtext.algorithm.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;

@SuppressWarnings("all")
public class InternalAlgorithmsectionLexer extends Lexer {
    public static final int RULE_ID=9;
    public static final int RULE_ANY_OTHER=13;
    public static final int RULE_UNSIGNED_NUMBER=5;
    public static final int T29=29;
    public static final int T28=28;
    public static final int T27=27;
    public static final int T26=26;
    public static final int T25=25;
    public static final int EOF=-1;
    public static final int T24=24;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int T62=62;
    public static final int RULE_INT=6;
    public static final int T38=38;
    public static final int T37=37;
    public static final int T39=39;
    public static final int T34=34;
    public static final int T33=33;
    public static final int T36=36;
    public static final int T35=35;
    public static final int T30=30;
    public static final int T61=61;
    public static final int T32=32;
    public static final int T60=60;
    public static final int T31=31;
    public static final int T49=49;
    public static final int T48=48;
    public static final int RULE_BOOL_VAL=7;
    public static final int T43=43;
    public static final int Tokens=63;
    public static final int RULE_SL_COMMENT=11;
    public static final int T42=42;
    public static final int T41=41;
    public static final int T40=40;
    public static final int T47=47;
    public static final int T46=46;
    public static final int T45=45;
    public static final int RULE_ML_COMMENT=10;
    public static final int T44=44;
    public static final int RULE_STRING=4;
    public static final int T50=50;
    public static final int RULE_IDENT=8;
    public static final int T59=59;
    public static final int T14=14;
    public static final int T52=52;
    public static final int T15=15;
    public static final int RULE_WS=12;
    public static final int T51=51;
    public static final int T16=16;
    public static final int T54=54;
    public static final int T17=17;
    public static final int T53=53;
    public static final int T18=18;
    public static final int T56=56;
    public static final int T19=19;
    public static final int T55=55;
    public static final int T58=58;
    public static final int T57=57;
    public InternalAlgorithmsectionLexer() {;} 
    public InternalAlgorithmsectionLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g"; }

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:10:5: ( '*' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:10:7: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:11:5: ( '/' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:11:7: '/'
            {
            match('/'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:12:5: ( '.*' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:12:7: '.*'
            {
            match(".*"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:13:5: ( './' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:13:7: './'
            {
            match("./"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:14:5: ( '+' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:14:7: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:15:5: ( '-' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:15:7: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:16:5: ( '.+' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:16:7: '.+'
            {
            match(".+"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:17:5: ( '.-' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:17:7: '.-'
            {
            match(".-"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:18:5: ( '<' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:18:7: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:19:5: ( '<=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:19:7: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:20:5: ( '>' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:20:7: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:21:5: ( '>=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:21:7: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:22:5: ( '==' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:22:7: '=='
            {
            match("=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:23:5: ( '<>' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:23:7: '<>'
            {
            match("<>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:24:5: ( 'return' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:24:7: 'return'
            {
            match("return"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:25:5: ( 'break' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:25:7: 'break'
            {
            match("break"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:26:5: ( '^' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:26:7: '^'
            {
            match('^'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:27:5: ( '.^' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:27:7: '.^'
            {
            match(".^"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:28:5: ( ';' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:28:7: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:29:5: ( 'GEN_SIGNAL' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:29:7: 'GEN_SIGNAL'
            {
            match("GEN_SIGNAL"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:30:5: ( '(' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:30:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:31:5: ( ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:31:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:32:5: ( 'GEN_INC' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:32:7: 'GEN_INC'
            {
            match("GEN_INC"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:33:5: ( 'GEN_CHANGE' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:33:7: 'GEN_CHANGE'
            {
            match("GEN_CHANGE"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:34:5: ( ':=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:34:7: ':='
            {
            match(":="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:35:5: ( 'when' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:35:7: 'when'
            {
            match("when"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:36:5: ( 'then' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:36:7: 'then'
            {
            match("then"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:37:5: ( 'end' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:37:7: 'end'
            {
            match("end"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start T42
    public final void mT42() throws RecognitionException {
        try {
            int _type = T42;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:38:5: ( 'elsewhen' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:38:7: 'elsewhen'
            {
            match("elsewhen"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T42

    // $ANTLR start T43
    public final void mT43() throws RecognitionException {
        try {
            int _type = T43;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:39:5: ( 'while' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:39:7: 'while'
            {
            match("while"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T43

    // $ANTLR start T44
    public final void mT44() throws RecognitionException {
        try {
            int _type = T44;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:40:5: ( 'loop' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:40:7: 'loop'
            {
            match("loop"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T44

    // $ANTLR start T45
    public final void mT45() throws RecognitionException {
        try {
            int _type = T45;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:41:5: ( 'for' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:41:7: 'for'
            {
            match("for"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T45

    // $ANTLR start T46
    public final void mT46() throws RecognitionException {
        try {
            int _type = T46;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:42:5: ( 'if' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:42:7: 'if'
            {
            match("if"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T46

    // $ANTLR start T47
    public final void mT47() throws RecognitionException {
        try {
            int _type = T47;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:43:5: ( 'elseif' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:43:7: 'elseif'
            {
            match("elseif"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T47

    // $ANTLR start T48
    public final void mT48() throws RecognitionException {
        try {
            int _type = T48;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:44:5: ( 'else' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:44:7: 'else'
            {
            match("else"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T48

    // $ANTLR start T49
    public final void mT49() throws RecognitionException {
        try {
            int _type = T49;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:45:5: ( ':' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:45:7: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T49

    // $ANTLR start T50
    public final void mT50() throws RecognitionException {
        try {
            int _type = T50;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:46:5: ( 'or' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:46:7: 'or'
            {
            match("or"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T50

    // $ANTLR start T51
    public final void mT51() throws RecognitionException {
        try {
            int _type = T51;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:47:5: ( 'and' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:47:7: 'and'
            {
            match("and"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T51

    // $ANTLR start T52
    public final void mT52() throws RecognitionException {
        try {
            int _type = T52;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:48:5: ( 'not' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:48:7: 'not'
            {
            match("not"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T52

    // $ANTLR start T53
    public final void mT53() throws RecognitionException {
        try {
            int _type = T53;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:49:5: ( '[' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:49:7: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T53

    // $ANTLR start T54
    public final void mT54() throws RecognitionException {
        try {
            int _type = T54;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:50:5: ( ']' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:50:7: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T54

    // $ANTLR start T55
    public final void mT55() throws RecognitionException {
        try {
            int _type = T55;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:51:5: ( '{' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:51:7: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T55

    // $ANTLR start T56
    public final void mT56() throws RecognitionException {
        try {
            int _type = T56;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:52:5: ( '}' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:52:7: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T56

    // $ANTLR start T57
    public final void mT57() throws RecognitionException {
        try {
            int _type = T57;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:53:5: ( 'initial' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:53:7: 'initial'
            {
            match("initial"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T57

    // $ANTLR start T58
    public final void mT58() throws RecognitionException {
        try {
            int _type = T58;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:54:5: ( 'der' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:54:7: 'der'
            {
            match("der"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T58

    // $ANTLR start T59
    public final void mT59() throws RecognitionException {
        try {
            int _type = T59;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:55:5: ( ',' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:55:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T59

    // $ANTLR start T60
    public final void mT60() throws RecognitionException {
        try {
            int _type = T60;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:56:5: ( '.' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:56:7: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T60

    // $ANTLR start T61
    public final void mT61() throws RecognitionException {
        try {
            int _type = T61;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:57:5: ( '=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:57:7: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T61

    // $ANTLR start T62
    public final void mT62() throws RecognitionException {
        try {
            int _type = T62;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:58:5: ( 'in' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:58:7: 'in'
            {
            match("in"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T62

    // $ANTLR start RULE_UNSIGNED_NUMBER
    public final void mRULE_UNSIGNED_NUMBER() throws RecognitionException {
        try {
            int _type = RULE_UNSIGNED_NUMBER;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:22: ( ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:25: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:25: ( '0' .. '9' )+
                    int cnt1=0;
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt1 >= 1 ) break loop1;
                                EarlyExitException eee =
                                    new EarlyExitException(1, input);
                                throw eee;
                        }
                        cnt1++;
                    } while (true);

                    match('.'); 
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:41: ( '0' .. '9' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:42: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:53: ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='E'||LA5_0=='e') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:54: ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse =
                                    new MismatchedSetException(null,input);
                                recover(mse);    throw mse;
                            }

                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:64: ( '+' | '-' )?
                            int alt3=2;
                            int LA3_0 = input.LA(1);

                            if ( (LA3_0=='+'||LA3_0=='-') ) {
                                alt3=1;
                            }
                            switch (alt3) {
                                case 1 :
                                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:
                                    {
                                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse =
                                            new MismatchedSetException(null,input);
                                        recover(mse);    throw mse;
                                    }


                                    }
                                    break;

                            }

                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:75: ( '0' .. '9' )+
                            int cnt4=0;
                            loop4:
                            do {
                                int alt4=2;
                                int LA4_0 = input.LA(1);

                                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                                    alt4=1;
                                }


                                switch (alt4) {
                            	case 1 :
                            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:76: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt4 >= 1 ) break loop4;
                                        EarlyExitException eee =
                                            new EarlyExitException(4, input);
                                        throw eee;
                                }
                                cnt4++;
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:89: ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:89: ( '0' .. '9' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:90: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);

                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recover(mse);    throw mse;
                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:111: ( '+' | '-' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='+'||LA7_0=='-') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse =
                                    new MismatchedSetException(null,input);
                                recover(mse);    throw mse;
                            }


                            }
                            break;

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:122: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9848:123: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);


                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_UNSIGNED_NUMBER

    // $ANTLR start RULE_BOOL_VAL
    public final void mRULE_BOOL_VAL() throws RecognitionException {
        try {
            int _type = RULE_BOOL_VAL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9850:15: ( ( 'true' | 'false' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9850:17: ( 'true' | 'false' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9850:17: ( 'true' | 'false' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='t') ) {
                alt10=1;
            }
            else if ( (LA10_0=='f') ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("9850:17: ( 'true' | 'false' )", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9850:18: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9850:25: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_BOOL_VAL

    // $ANTLR start RULE_INT
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9852:10: ( ( '0' .. '9' )+ )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9852:12: ( '0' .. '9' )+
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9852:12: ( '0' .. '9' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9852:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_STRING
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9854:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9854:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9854:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
            loop12:
            do {
                int alt12=3;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='\\') ) {
                    alt12=1;
                }
                else if ( ((LA12_0>='\u0000' && LA12_0<='!')||(LA12_0>='#' && LA12_0<='[')||(LA12_0>=']' && LA12_0<='\uFFFE')) ) {
                    alt12=2;
                }


                switch (alt12) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9854:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
            	    {
            	    match('\\'); 
            	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9854:61: ~ ( ( '\\\\' | '\"' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_IDENT
    public final void mRULE_IDENT() throws RecognitionException {
        try {
            int _type = RULE_IDENT;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9856:12: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '.' )* )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9856:14: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '.' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9856:38: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '.' )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='.'||(LA13_0>='0' && LA13_0<='9')||(LA13_0>='A' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='z')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_IDENT

    // $ANTLR start RULE_ID
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9858:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9858:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9858:11: ( '^' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='^') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9858:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9858:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='0' && LA15_0<='9')||(LA15_0>='A' && LA15_0<='Z')||LA15_0=='_'||(LA15_0>='a' && LA15_0<='z')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_ML_COMMENT
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9860:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9860:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9860:24: ( options {greedy=false; } : . )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='*') ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1=='/') ) {
                        alt16=2;
                    }
                    else if ( ((LA16_1>='\u0000' && LA16_1<='.')||(LA16_1>='0' && LA16_1<='\uFFFE')) ) {
                        alt16=1;
                    }


                }
                else if ( ((LA16_0>='\u0000' && LA16_0<=')')||(LA16_0>='+' && LA16_0<='\uFFFE')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9860:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            match("*/"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\u0000' && LA17_0<='\t')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\uFFFE')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:40: ( ( '\\r' )? '\\n' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='\n'||LA19_0=='\r') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:41: ( '\\r' )? '\\n'
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:41: ( '\\r' )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0=='\r') ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9862:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    // $ANTLR start RULE_WS
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9864:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9864:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9864:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>='\t' && LA20_0<='\n')||LA20_0=='\r'||LA20_0==' ') ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ANY_OTHER
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9866:16: ( . )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:9866:18: .
            {
            matchAny(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ANY_OTHER

    public void mTokens() throws RecognitionException {
        // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:8: ( T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | RULE_UNSIGNED_NUMBER | RULE_BOOL_VAL | RULE_INT | RULE_STRING | RULE_IDENT | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt21=59;
        alt21 = dfa21.predict(input);
        switch (alt21) {
            case 1 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:10: T14
                {
                mT14(); 

                }
                break;
            case 2 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:14: T15
                {
                mT15(); 

                }
                break;
            case 3 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:18: T16
                {
                mT16(); 

                }
                break;
            case 4 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:22: T17
                {
                mT17(); 

                }
                break;
            case 5 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:26: T18
                {
                mT18(); 

                }
                break;
            case 6 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:30: T19
                {
                mT19(); 

                }
                break;
            case 7 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:34: T20
                {
                mT20(); 

                }
                break;
            case 8 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:38: T21
                {
                mT21(); 

                }
                break;
            case 9 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:42: T22
                {
                mT22(); 

                }
                break;
            case 10 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:46: T23
                {
                mT23(); 

                }
                break;
            case 11 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:50: T24
                {
                mT24(); 

                }
                break;
            case 12 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:54: T25
                {
                mT25(); 

                }
                break;
            case 13 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:58: T26
                {
                mT26(); 

                }
                break;
            case 14 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:62: T27
                {
                mT27(); 

                }
                break;
            case 15 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:66: T28
                {
                mT28(); 

                }
                break;
            case 16 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:70: T29
                {
                mT29(); 

                }
                break;
            case 17 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:74: T30
                {
                mT30(); 

                }
                break;
            case 18 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:78: T31
                {
                mT31(); 

                }
                break;
            case 19 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:82: T32
                {
                mT32(); 

                }
                break;
            case 20 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:86: T33
                {
                mT33(); 

                }
                break;
            case 21 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:90: T34
                {
                mT34(); 

                }
                break;
            case 22 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:94: T35
                {
                mT35(); 

                }
                break;
            case 23 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:98: T36
                {
                mT36(); 

                }
                break;
            case 24 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:102: T37
                {
                mT37(); 

                }
                break;
            case 25 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:106: T38
                {
                mT38(); 

                }
                break;
            case 26 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:110: T39
                {
                mT39(); 

                }
                break;
            case 27 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:114: T40
                {
                mT40(); 

                }
                break;
            case 28 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:118: T41
                {
                mT41(); 

                }
                break;
            case 29 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:122: T42
                {
                mT42(); 

                }
                break;
            case 30 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:126: T43
                {
                mT43(); 

                }
                break;
            case 31 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:130: T44
                {
                mT44(); 

                }
                break;
            case 32 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:134: T45
                {
                mT45(); 

                }
                break;
            case 33 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:138: T46
                {
                mT46(); 

                }
                break;
            case 34 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:142: T47
                {
                mT47(); 

                }
                break;
            case 35 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:146: T48
                {
                mT48(); 

                }
                break;
            case 36 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:150: T49
                {
                mT49(); 

                }
                break;
            case 37 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:154: T50
                {
                mT50(); 

                }
                break;
            case 38 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:158: T51
                {
                mT51(); 

                }
                break;
            case 39 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:162: T52
                {
                mT52(); 

                }
                break;
            case 40 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:166: T53
                {
                mT53(); 

                }
                break;
            case 41 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:170: T54
                {
                mT54(); 

                }
                break;
            case 42 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:174: T55
                {
                mT55(); 

                }
                break;
            case 43 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:178: T56
                {
                mT56(); 

                }
                break;
            case 44 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:182: T57
                {
                mT57(); 

                }
                break;
            case 45 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:186: T58
                {
                mT58(); 

                }
                break;
            case 46 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:190: T59
                {
                mT59(); 

                }
                break;
            case 47 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:194: T60
                {
                mT60(); 

                }
                break;
            case 48 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:198: T61
                {
                mT61(); 

                }
                break;
            case 49 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:202: T62
                {
                mT62(); 

                }
                break;
            case 50 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:206: RULE_UNSIGNED_NUMBER
                {
                mRULE_UNSIGNED_NUMBER(); 

                }
                break;
            case 51 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:227: RULE_BOOL_VAL
                {
                mRULE_BOOL_VAL(); 

                }
                break;
            case 52 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:241: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 53 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:250: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 54 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:262: RULE_IDENT
                {
                mRULE_IDENT(); 

                }
                break;
            case 55 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:273: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 56 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:281: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 57 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:297: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 58 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:313: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 59 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection.ui/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/ui/contentassist/antlr/internal/InternalAlgorithmsection.g:1:321: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA21 dfa21 = new DFA21(this);
    static final String DFA9_eotS =
        "\4\uffff";
    static final String DFA9_eofS =
        "\4\uffff";
    static final String DFA9_minS =
        "\1\60\1\56\2\uffff";
    static final String DFA9_maxS =
        "\1\71\1\145\2\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA9_specialS =
        "\4\uffff}>";
    static final String[] DFA9_transitionS = {
            "\12\1",
            "\1\2\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "9848:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )";
        }
    }
    static final String DFA21_eotS =
        "\2\uffff\1\50\1\56\2\uffff\1\63\1\65\1\67\2\71\1\74\1\uffff\1\71"+
        "\2\uffff\1\103\11\71\4\uffff\1\71\1\uffff\1\131\1\44\1\71\25\uffff"+
        "\1\71\1\uffff\2\71\3\uffff\1\71\4\uffff\10\71\1\150\1\152\1\153"+
        "\2\71\4\uffff\1\71\2\uffff\1\131\3\uffff\7\71\1\166\3\71\1\172\1"+
        "\uffff\1\71\2\uffff\1\174\1\175\1\176\4\71\1\u0085\1\u0086\1\u0087"+
        "\1\uffff\1\u008a\1\u008b\1\71\1\uffff\1\71\3\uffff\1\71\1\u008f"+
        "\3\71\1\u0093\3\uffff\2\71\2\uffff\1\u0086\1\71\1\u0097\1\uffff"+
        "\3\71\1\uffff\1\71\1\u009c\1\71\1\uffff\1\71\1\u009f\2\71\1\uffff"+
        "\1\u00a2\1\71\1\uffff\1\71\1\u00a5\1\uffff\2\71\1\uffff\1\u00a8"+
        "\1\u00a9\2\uffff";
    static final String DFA21_eofS =
        "\u00aa\uffff";
    static final String DFA21_minS =
        "\1\0\1\uffff\2\52\2\uffff\3\75\2\60\1\101\1\uffff\1\60\2\uffff\1"+
        "\75\11\60\4\uffff\1\60\1\uffff\1\56\1\0\1\60\25\uffff\1\60\1\uffff"+
        "\2\60\3\uffff\1\60\4\uffff\10\60\3\56\2\60\4\uffff\1\60\2\uffff"+
        "\1\56\3\uffff\7\60\1\56\3\60\1\56\1\uffff\1\60\2\uffff\3\56\4\60"+
        "\3\56\1\uffff\2\56\1\60\1\uffff\1\60\3\uffff\1\60\1\56\3\60\1\56"+
        "\3\uffff\2\60\2\uffff\1\56\1\60\1\56\1\uffff\3\60\1\uffff\1\60\1"+
        "\56\1\60\1\uffff\1\60\1\56\2\60\1\uffff\1\56\1\60\1\uffff\1\60\1"+
        "\56\1\uffff\2\60\1\uffff\2\56\2\uffff";
    static final String DFA21_maxS =
        "\1\ufffe\1\uffff\1\57\1\136\2\uffff\1\76\2\75\3\172\1\uffff\1\172"+
        "\2\uffff\1\75\11\172\4\uffff\1\172\1\uffff\1\145\1\ufffe\1\172\25"+
        "\uffff\1\172\1\uffff\2\172\3\uffff\1\172\4\uffff\15\172\4\uffff"+
        "\1\172\2\uffff\1\145\3\uffff\14\172\1\uffff\1\172\2\uffff\12\172"+
        "\1\uffff\3\172\1\uffff\1\172\3\uffff\6\172\3\uffff\2\172\2\uffff"+
        "\3\172\1\uffff\3\172\1\uffff\3\172\1\uffff\4\172\1\uffff\2\172\1"+
        "\uffff\2\172\1\uffff\2\172\1\uffff\2\172\2\uffff";
    static final String DFA21_acceptS =
        "\1\uffff\1\1\2\uffff\1\5\1\6\6\uffff\1\23\1\uffff\1\25\1\26\12\uffff"+
        "\1\50\1\51\1\52\1\53\1\uffff\1\56\3\uffff\1\72\1\73\1\1\1\70\1\71"+
        "\1\2\1\10\1\7\1\3\1\4\1\22\1\57\1\5\1\6\1\12\1\16\1\11\1\14\1\13"+
        "\1\15\1\60\1\uffff\1\66\2\uffff\1\21\1\67\1\23\1\uffff\1\25\1\26"+
        "\1\31\1\44\15\uffff\1\50\1\51\1\52\1\53\1\uffff\1\56\1\62\1\uffff"+
        "\1\64\1\65\1\72\14\uffff\1\41\1\uffff\1\61\1\45\12\uffff\1\34\3"+
        "\uffff\1\40\1\uffff\1\46\1\47\1\55\6\uffff\1\32\1\63\1\33\2\uffff"+
        "\1\43\1\37\3\uffff\1\20\3\uffff\1\36\3\uffff\1\17\4\uffff\1\42\2"+
        "\uffff\1\27\2\uffff\1\54\2\uffff\1\35\2\uffff\1\24\1\30";
    static final String DFA21_specialS =
        "\u00aa\uffff}>";
    static final String[] DFA21_transitionS = {
            "\11\44\2\43\2\44\1\43\22\44\1\43\1\44\1\41\5\44\1\16\1\17\1"+
            "\1\1\4\1\37\1\5\1\3\1\2\12\40\1\20\1\14\1\6\1\10\1\7\2\44\6"+
            "\42\1\15\23\42\1\32\1\44\1\33\1\13\1\42\1\44\1\30\1\12\1\42"+
            "\1\36\1\23\1\25\2\42\1\26\2\42\1\24\1\42\1\31\1\27\2\42\1\11"+
            "\1\42\1\22\2\42\1\21\3\42\1\34\1\44\1\35\uff81\44",
            "",
            "\1\46\4\uffff\1\47",
            "\1\53\1\52\1\uffff\1\51\1\uffff\1\54\56\uffff\1\55",
            "",
            "",
            "\1\61\1\62",
            "\1\64",
            "\1\66",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\70\25\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\21\72\1\73\10\72",
            "\32\75\4\uffff\1\75\1\uffff\32\75",
            "",
            "\12\72\7\uffff\4\72\1\77\25\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "",
            "\1\102",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\7\72\1\104\22\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\7\72\1\106\11\72\1"+
            "\105\10\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\13\72\1\110\1\72\1"+
            "\107\14\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\16\72\1\111\13\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\1\112\15\72\1\113"+
            "\13\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\5\72\1\114\7\72\1"+
            "\115\14\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\21\72\1\116\10\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\15\72\1\117\14\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\16\72\1\120\13\72",
            "",
            "",
            "",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\125\25\72",
            "",
            "\1\127\1\uffff\12\130\13\uffff\1\127\37\uffff\1\127",
            "\uffff\132",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\23\72\1\134\6\72",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\135\25\72",
            "",
            "",
            "",
            "\12\72\7\uffff\15\72\1\136\14\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "",
            "",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\140\3\72\1"+
            "\137\21\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\24\72\1\141\5\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\142\25\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\3\72\1\143\26\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\22\72\1\144\7\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\16\72\1\145\13\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\13\72\1\146\16\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\21\72\1\147\10\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\10\72"+
            "\1\151\21\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\3\72\1\154\26\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\23\72\1\155\6\72",
            "",
            "",
            "",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\21\72\1\156\10\72",
            "",
            "",
            "\1\127\1\uffff\12\130\13\uffff\1\127\37\uffff\1\127",
            "",
            "",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\24\72\1\157\5\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\1\160\31\72",
            "\12\72\7\uffff\32\72\4\uffff\1\161\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\13\72\1\162\16\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\15\72\1\163\14\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\164\25\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\15\72\1\165\14\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\167\25\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\17\72\1\170\12\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\22\72\1\171\7\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\23\72\1\173\6\72",
            "",
            "",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\21\72\1\177\10\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\12\72\1\u0080\17\72",
            "\12\72\7\uffff\2\72\1\u0083\5\72\1\u0082\11\72\1\u0081\7\72"+
            "\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\u0084\25\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\10\72"+
            "\1\u0089\15\72\1\u0088\3\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\u008c\25\72",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\10\72\1\u008d\21\72",
            "",
            "",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\15\72\1\u008e\14\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\10\72\1\u0090\21\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\15\72\1\u0091\14\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\7\72\1\u0092\22\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\7\72\1\u0094\22\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\5\72\1\u0095\24\72",
            "",
            "",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\1\u0096\31\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "\12\72\7\uffff\6\72\1\u0098\23\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\2\72\1\u0099\27\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\1\u009a\31\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\4\72\1\u009b\25\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\13\72\1\u009d\16\72",
            "",
            "\12\72\7\uffff\15\72\1\u009e\14\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\15\72\1\u00a0\14\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\15\72\1\u00a1\14\72",
            "",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\1\u00a3\31\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "\12\72\7\uffff\6\72\1\u00a4\23\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "\12\72\7\uffff\13\72\1\u00a6\16\72\4\uffff\1\72\1\uffff\32\72",
            "\12\72\7\uffff\4\72\1\u00a7\25\72\4\uffff\1\72\1\uffff\32\72",
            "",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "\1\71\1\uffff\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72",
            "",
            ""
    };

    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;

    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | RULE_UNSIGNED_NUMBER | RULE_BOOL_VAL | RULE_INT | RULE_STRING | RULE_IDENT | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
    }
 

}