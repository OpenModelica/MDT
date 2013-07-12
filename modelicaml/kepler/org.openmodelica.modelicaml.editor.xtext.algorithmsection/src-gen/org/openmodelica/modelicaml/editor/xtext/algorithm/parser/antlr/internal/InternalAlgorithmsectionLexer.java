/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.eclipse.xtext.parser.antlr.Lexer;

@SuppressWarnings("all")
public class InternalAlgorithmsectionLexer extends Lexer {
    public static final int RULE_ID=9;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__62=62;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__20=20;
    public static final int RULE_UNSIGNED_NUMBER=4;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int EOF=-1;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__19=19;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__16=16;
    public static final int T__51=51;
    public static final int T__15=15;
    public static final int T__52=52;
    public static final int T__18=18;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__17=17;
    public static final int T__14=14;
    public static final int T__59=59;
    public static final int RULE_INT=5;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_BOOL_VAL=7;
    public static final int RULE_SL_COMMENT=11;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_STRING=6;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int RULE_IDENT=8;
    public static final int T__39=39;
    public static final int RULE_WS=12;

    // delegates
    // delegators

    public InternalAlgorithmsectionLexer() {;} 
    public InternalAlgorithmsectionLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalAlgorithmsectionLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:11:7: ( ';' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:11:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:12:7: ( 'return' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:12:9: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:13:7: ( 'break' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:13:9: 'break'
            {
            match("break"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:14:7: ( 'GEN_SIGNAL' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:14:9: 'GEN_SIGNAL'
            {
            match("GEN_SIGNAL"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:15:7: ( '(' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:15:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:16:7: ( ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:16:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:17:7: ( 'GEN_INC' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:17:9: 'GEN_INC'
            {
            match("GEN_INC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:18:7: ( 'GEN_CHANGE' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:18:9: 'GEN_CHANGE'
            {
            match("GEN_CHANGE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:19:7: ( ':=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:19:9: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:20:7: ( 'when' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:20:9: 'when'
            {
            match("when"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:21:7: ( 'then' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:21:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:22:7: ( 'elsewhen' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:22:9: 'elsewhen'
            {
            match("elsewhen"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:23:7: ( 'end' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:23:9: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:24:7: ( 'while' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:24:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:25:7: ( 'loop' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:25:9: 'loop'
            {
            match("loop"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:26:7: ( 'for' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:26:9: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:27:7: ( 'if' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:27:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:28:7: ( 'elseif' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:28:9: 'elseif'
            {
            match("elseif"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:29:7: ( 'else' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:29:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:30:7: ( ':' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:30:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:31:7: ( 'or' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:31:9: 'or'
            {
            match("or"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:32:7: ( 'and' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:32:9: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:33:7: ( 'not' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:33:9: 'not'
            {
            match("not"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:34:7: ( '^' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:34:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:35:7: ( '.^' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:35:9: '.^'
            {
            match(".^"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:36:7: ( '[' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:36:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:37:7: ( ']' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:37:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:38:7: ( '{' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:38:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:39:7: ( '}' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:39:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:40:7: ( 'initial' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:40:9: 'initial'
            {
            match("initial"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:41:7: ( 'der' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:41:9: 'der'
            {
            match("der"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:42:7: ( ',' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:42:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:43:7: ( '.' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:43:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:44:7: ( '=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:44:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:45:7: ( 'in' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:45:9: 'in'
            {
            match("in"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:46:7: ( '*' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:46:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:47:7: ( '/' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:47:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:48:7: ( '.*' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:48:9: '.*'
            {
            match(".*"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:49:7: ( './' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:49:9: './'
            {
            match("./"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:50:7: ( '+' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:50:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:51:7: ( '-' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:51:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:52:7: ( '.+' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:52:9: '.+'
            {
            match(".+"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:53:7: ( '.-' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:53:9: '.-'
            {
            match(".-"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:54:7: ( '<' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:54:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:55:7: ( '<=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:55:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:56:7: ( '>' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:56:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:57:7: ( '>=' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:57:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:58:7: ( '==' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:58:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:59:7: ( '<>' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:59:9: '<>'
            {
            match("<>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "RULE_UNSIGNED_NUMBER"
    public final void mRULE_UNSIGNED_NUMBER() throws RecognitionException {
        try {
            int _type = RULE_UNSIGNED_NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:22: ( ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:25: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:25: ( '0' .. '9' )+
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
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:26: '0' .. '9'
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
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:41: ( '0' .. '9' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:42: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:53: ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='E'||LA5_0=='e') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:54: ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:64: ( '+' | '-' )?
                            int alt3=2;
                            int LA3_0 = input.LA(1);

                            if ( (LA3_0=='+'||LA3_0=='-') ) {
                                alt3=1;
                            }
                            switch (alt3) {
                                case 1 :
                                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:
                                    {
                                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}


                                    }
                                    break;

                            }

                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:75: ( '0' .. '9' )+
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
                            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:76: '0' .. '9'
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
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:89: ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:89: ( '0' .. '9' )+
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
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:90: '0' .. '9'
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
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:111: ( '+' | '-' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='+'||LA7_0=='-') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:122: ( '0' .. '9' )+
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
                    	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3713:123: '0' .. '9'
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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_UNSIGNED_NUMBER"

    // $ANTLR start "RULE_BOOL_VAL"
    public final void mRULE_BOOL_VAL() throws RecognitionException {
        try {
            int _type = RULE_BOOL_VAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3715:15: ( ( 'true' | 'false' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3715:17: ( 'true' | 'false' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3715:17: ( 'true' | 'false' )
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
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3715:18: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3715:25: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BOOL_VAL"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3717:10: ( ( '0' .. '9' )+ )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3717:12: ( '0' .. '9' )+
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3717:12: ( '0' .. '9' )+
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
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3717:13: '0' .. '9'
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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3719:13: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3719:15: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3719:19: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
            loop12:
            do {
                int alt12=3;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='\\') ) {
                    alt12=1;
                }
                else if ( ((LA12_0>='\u0000' && LA12_0<='!')||(LA12_0>='#' && LA12_0<='[')||(LA12_0>=']' && LA12_0<='\uFFFF')) ) {
                    alt12=2;
                }


                switch (alt12) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3719:20: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
            	    {
            	    match('\\'); 
            	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;
            	case 2 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3719:61: ~ ( ( '\\\\' | '\"' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_IDENT"
    public final void mRULE_IDENT() throws RecognitionException {
        try {
            int _type = RULE_IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3721:12: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '.' )* )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3721:14: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '.' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3721:38: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | '.' )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='.'||(LA13_0>='0' && LA13_0<='9')||(LA13_0>='A' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='z')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_IDENT"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3723:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3723:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3723:11: ( '^' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='^') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3723:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3723:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='0' && LA15_0<='9')||(LA15_0>='A' && LA15_0<='Z')||LA15_0=='_'||(LA15_0>='a' && LA15_0<='z')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3725:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3725:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3725:24: ( options {greedy=false; } : . )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0=='*') ) {
                    int LA16_1 = input.LA(2);

                    if ( (LA16_1=='/') ) {
                        alt16=2;
                    }
                    else if ( ((LA16_1>='\u0000' && LA16_1<='.')||(LA16_1>='0' && LA16_1<='\uFFFF')) ) {
                        alt16=1;
                    }


                }
                else if ( ((LA16_0>='\u0000' && LA16_0<=')')||(LA16_0>='+' && LA16_0<='\uFFFF')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3725:52: .
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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\u0000' && LA17_0<='\t')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:40: ( ( '\\r' )? '\\n' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='\n'||LA19_0=='\r') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:41: ( '\\r' )? '\\n'
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:41: ( '\\r' )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0=='\r') ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3727:41: '\\r'
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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3729:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3729:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3729:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3731:16: ( . )
            // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:3731:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | RULE_UNSIGNED_NUMBER | RULE_BOOL_VAL | RULE_INT | RULE_STRING | RULE_IDENT | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt21=59;
        alt21 = dfa21.predict(input);
        switch (alt21) {
            case 1 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:64: T__23
                {
                mT__23(); 

                }
                break;
            case 11 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:70: T__24
                {
                mT__24(); 

                }
                break;
            case 12 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:76: T__25
                {
                mT__25(); 

                }
                break;
            case 13 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:82: T__26
                {
                mT__26(); 

                }
                break;
            case 14 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:88: T__27
                {
                mT__27(); 

                }
                break;
            case 15 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:94: T__28
                {
                mT__28(); 

                }
                break;
            case 16 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:100: T__29
                {
                mT__29(); 

                }
                break;
            case 17 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:106: T__30
                {
                mT__30(); 

                }
                break;
            case 18 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:112: T__31
                {
                mT__31(); 

                }
                break;
            case 19 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:118: T__32
                {
                mT__32(); 

                }
                break;
            case 20 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:124: T__33
                {
                mT__33(); 

                }
                break;
            case 21 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:130: T__34
                {
                mT__34(); 

                }
                break;
            case 22 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:136: T__35
                {
                mT__35(); 

                }
                break;
            case 23 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:142: T__36
                {
                mT__36(); 

                }
                break;
            case 24 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:148: T__37
                {
                mT__37(); 

                }
                break;
            case 25 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:154: T__38
                {
                mT__38(); 

                }
                break;
            case 26 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:160: T__39
                {
                mT__39(); 

                }
                break;
            case 27 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:166: T__40
                {
                mT__40(); 

                }
                break;
            case 28 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:172: T__41
                {
                mT__41(); 

                }
                break;
            case 29 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:178: T__42
                {
                mT__42(); 

                }
                break;
            case 30 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:184: T__43
                {
                mT__43(); 

                }
                break;
            case 31 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:190: T__44
                {
                mT__44(); 

                }
                break;
            case 32 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:196: T__45
                {
                mT__45(); 

                }
                break;
            case 33 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:202: T__46
                {
                mT__46(); 

                }
                break;
            case 34 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:208: T__47
                {
                mT__47(); 

                }
                break;
            case 35 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:214: T__48
                {
                mT__48(); 

                }
                break;
            case 36 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:220: T__49
                {
                mT__49(); 

                }
                break;
            case 37 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:226: T__50
                {
                mT__50(); 

                }
                break;
            case 38 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:232: T__51
                {
                mT__51(); 

                }
                break;
            case 39 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:238: T__52
                {
                mT__52(); 

                }
                break;
            case 40 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:244: T__53
                {
                mT__53(); 

                }
                break;
            case 41 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:250: T__54
                {
                mT__54(); 

                }
                break;
            case 42 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:256: T__55
                {
                mT__55(); 

                }
                break;
            case 43 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:262: T__56
                {
                mT__56(); 

                }
                break;
            case 44 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:268: T__57
                {
                mT__57(); 

                }
                break;
            case 45 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:274: T__58
                {
                mT__58(); 

                }
                break;
            case 46 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:280: T__59
                {
                mT__59(); 

                }
                break;
            case 47 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:286: T__60
                {
                mT__60(); 

                }
                break;
            case 48 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:292: T__61
                {
                mT__61(); 

                }
                break;
            case 49 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:298: T__62
                {
                mT__62(); 

                }
                break;
            case 50 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:304: RULE_UNSIGNED_NUMBER
                {
                mRULE_UNSIGNED_NUMBER(); 

                }
                break;
            case 51 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:325: RULE_BOOL_VAL
                {
                mRULE_BOOL_VAL(); 

                }
                break;
            case 52 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:339: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 53 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:348: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 54 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:360: RULE_IDENT
                {
                mRULE_IDENT(); 

                }
                break;
            case 55 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:371: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 56 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:379: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 57 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:395: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 58 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:411: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 59 :
                // ../org.openmodelica.modelicaml.editor.xtext.algorithmsection/src-gen/org/openmodelica/modelicaml/editor/xtext/algorithm/parser/antlr/internal/InternalAlgorithmsection.g:1:419: RULE_ANY_OTHER
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
            return "3713:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( 'E' | 'e' ) ( '+' | '-' )? ( '0' .. '9' )+ )";
        }
    }
    static final String DFA21_eotS =
        "\2\uffff\3\47\2\uffff\1\56\11\47\1\74\1\103\4\uffff\1\47\1\uffff"+
        "\1\113\1\uffff\1\117\2\uffff\1\124\1\126\1\131\1\44\1\47\3\uffff"+
        "\1\47\1\uffff\3\47\4\uffff\10\47\1\150\1\152\1\153\2\47\14\uffff"+
        "\1\47\17\uffff\1\131\3\uffff\10\47\1\167\1\47\1\171\1\47\1\uffff"+
        "\1\47\2\uffff\1\174\1\175\1\176\3\47\1\u0084\1\47\1\u0086\1\u0087"+
        "\1\u008a\1\uffff\1\u008b\1\uffff\2\47\3\uffff\1\47\1\u008f\3\47"+
        "\1\uffff\1\u0093\2\uffff\2\47\2\uffff\1\u0087\1\47\1\u0097\1\uffff"+
        "\3\47\1\uffff\1\47\1\u009c\1\47\1\uffff\1\47\1\u009f\2\47\1\uffff"+
        "\1\u00a2\1\47\1\uffff\1\47\1\u00a5\1\uffff\2\47\1\uffff\1\u00a8"+
        "\1\u00a9\2\uffff";
    static final String DFA21_eofS =
        "\u00aa\uffff";
    static final String DFA21_minS =
        "\1\0\1\uffff\3\60\2\uffff\1\75\11\60\1\101\1\52\4\uffff\1\60\1"+
        "\uffff\1\75\1\uffff\1\52\2\uffff\2\75\1\56\1\0\1\60\3\uffff\1\60"+
        "\1\uffff\3\60\4\uffff\10\60\3\56\2\60\14\uffff\1\60\17\uffff\1\56"+
        "\3\uffff\10\60\1\56\1\60\1\56\1\60\1\uffff\1\60\2\uffff\3\56\3\60"+
        "\1\56\1\60\3\56\1\uffff\1\56\1\uffff\2\60\3\uffff\1\60\1\56\3\60"+
        "\1\uffff\1\56\2\uffff\2\60\2\uffff\1\56\1\60\1\56\1\uffff\3\60\1"+
        "\uffff\1\60\1\56\1\60\1\uffff\1\60\1\56\2\60\1\uffff\1\56\1\60\1"+
        "\uffff\1\60\1\56\1\uffff\2\60\1\uffff\2\56\2\uffff";
    static final String DFA21_maxS =
        "\1\uffff\1\uffff\3\172\2\uffff\1\75\12\172\1\136\4\uffff\1\172"+
        "\1\uffff\1\75\1\uffff\1\57\2\uffff\1\76\1\75\1\145\1\uffff\1\172"+
        "\3\uffff\1\172\1\uffff\3\172\4\uffff\15\172\14\uffff\1\172\17\uffff"+
        "\1\145\3\uffff\14\172\1\uffff\1\172\2\uffff\13\172\1\uffff\1\172"+
        "\1\uffff\2\172\3\uffff\5\172\1\uffff\1\172\2\uffff\2\172\2\uffff"+
        "\3\172\1\uffff\3\172\1\uffff\3\172\1\uffff\4\172\1\uffff\2\172\1"+
        "\uffff\2\172\1\uffff\2\172\1\uffff\2\172\2\uffff";
    static final String DFA21_acceptS =
        "\1\uffff\1\1\3\uffff\1\5\1\6\14\uffff\1\32\1\33\1\34\1\35\1\uffff"+
        "\1\40\1\uffff\1\44\1\uffff\1\50\1\51\5\uffff\1\72\1\73\1\1\1\uffff"+
        "\1\66\3\uffff\1\5\1\6\1\11\1\24\15\uffff\1\30\1\67\1\31\1\46\1\47"+
        "\1\52\1\53\1\41\1\32\1\33\1\34\1\35\1\uffff\1\40\1\60\1\42\1\44"+
        "\1\70\1\71\1\45\1\50\1\51\1\55\1\61\1\54\1\57\1\56\1\62\1\uffff"+
        "\1\64\1\65\1\72\14\uffff\1\21\1\uffff\1\43\1\25\13\uffff\1\15\1"+
        "\uffff\1\20\2\uffff\1\26\1\27\1\37\5\uffff\1\12\1\uffff\1\13\1\63"+
        "\2\uffff\1\23\1\17\3\uffff\1\3\3\uffff\1\16\3\uffff\1\2\4\uffff"+
        "\1\22\2\uffff\1\7\2\uffff\1\36\2\uffff\1\14\2\uffff\1\4\1\10";
    static final String DFA21_specialS =
        "\1\1\40\uffff\1\0\u0088\uffff}>";
    static final String[] DFA21_transitionS = {
            "\11\44\2\43\2\44\1\43\22\44\1\43\1\44\1\41\5\44\1\5\1\6\1\32"+
            "\1\34\1\30\1\35\1\22\1\33\12\40\1\7\1\1\1\36\1\31\1\37\2\44"+
            "\6\42\1\4\23\42\1\23\1\44\1\24\1\21\1\42\1\44\1\17\1\3\1\42"+
            "\1\27\1\12\1\14\2\42\1\15\2\42\1\13\1\42\1\20\1\16\2\42\1\2"+
            "\1\42\1\11\2\42\1\10\3\42\1\25\1\44\1\26\uff82\44",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\46\25\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\51\10\50",
            "\12\50\7\uffff\4\50\1\52\25\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\55",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\57\22\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\60\11\50"+
            "\1\61\10\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\62\1\50"+
            "\1\63\14\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\64\13\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\66\15\50\1\65"+
            "\13\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\5\50\1\67\7\50\1"+
            "\70\14\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\71\10\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\72\14\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\73\13\50",
            "\32\75\4\uffff\1\75\1\uffff\32\75",
            "\1\77\1\101\1\uffff\1\102\1\uffff\1\100\56\uffff\1\76",
            "",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\110\25\50",
            "",
            "\1\112",
            "",
            "\1\115\4\uffff\1\116",
            "",
            "",
            "\1\122\1\123",
            "\1\125",
            "\1\127\1\uffff\12\130\13\uffff\1\127\37\uffff\1\127",
            "\0\132",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\134\6\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\135\25\50",
            "\12\50\7\uffff\15\50\1\136\14\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\137\3\50"+
            "\1\140\21\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\141\25\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\24\50\1\142\5\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\143\7\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\144\26\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\16\50\1\145\13\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\146\10\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\147\16\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10"+
            "\50\1\151\21\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\3\50\1\154\26\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\155\6\50",
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
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\156\10\50",
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
            "\1\127\1\uffff\12\130\13\uffff\1\127\37\uffff\1\127",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\24\50\1\157\5\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\160\31\50",
            "\12\50\7\uffff\32\50\4\uffff\1\161\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\162\14\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\163\16\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\164\14\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\165\25\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\166\25\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\17\50\1\170\12\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\22\50\1\172\7\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\23\50\1\173\6\50",
            "",
            "",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\21\50\1\177\10\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\12\50\1\u0080\17"+
            "\50",
            "\12\50\7\uffff\2\50\1\u0083\5\50\1\u0082\11\50\1\u0081\7\50"+
            "\4\uffff\1\50\1\uffff\32\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u0085\25"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10"+
            "\50\1\u0089\15\50\1\u0088\3\50",
            "",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u008c\25"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\10\50\1\u008d\21"+
            "\50",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u008e\14"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\10\50\1\u0090\21\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\15\50\1\u0091\14\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\7\50\1\u0092\22\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\7\50\1\u0094\22"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\5\50\1\u0095\24"+
            "\50",
            "",
            "",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\1\u0096\31\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "",
            "\12\50\7\uffff\6\50\1\u0098\23\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\2\50\1\u0099\27\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\1\u009a\31\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\4\50\1\u009b\25"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\13\50\1\u009d\16"+
            "\50",
            "",
            "\12\50\7\uffff\15\50\1\u009e\14\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\15\50\1\u00a0\14\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\15\50\1\u00a1\14"+
            "\50",
            "",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\1\u00a3\31\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\6\50\1\u00a4\23\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "",
            "\12\50\7\uffff\13\50\1\u00a6\16\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\12\50\7\uffff\4\50\1\u00a7\25\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
            "\1\47\1\uffff\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32"+
            "\50",
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
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | RULE_UNSIGNED_NUMBER | RULE_BOOL_VAL | RULE_INT | RULE_STRING | RULE_IDENT | RULE_ID | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA21_33 = input.LA(1);

                        s = -1;
                        if ( ((LA21_33>='\u0000' && LA21_33<='\uFFFF')) ) {s = 90;}

                        else s = 36;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA21_0 = input.LA(1);

                        s = -1;
                        if ( (LA21_0==';') ) {s = 1;}

                        else if ( (LA21_0=='r') ) {s = 2;}

                        else if ( (LA21_0=='b') ) {s = 3;}

                        else if ( (LA21_0=='G') ) {s = 4;}

                        else if ( (LA21_0=='(') ) {s = 5;}

                        else if ( (LA21_0==')') ) {s = 6;}

                        else if ( (LA21_0==':') ) {s = 7;}

                        else if ( (LA21_0=='w') ) {s = 8;}

                        else if ( (LA21_0=='t') ) {s = 9;}

                        else if ( (LA21_0=='e') ) {s = 10;}

                        else if ( (LA21_0=='l') ) {s = 11;}

                        else if ( (LA21_0=='f') ) {s = 12;}

                        else if ( (LA21_0=='i') ) {s = 13;}

                        else if ( (LA21_0=='o') ) {s = 14;}

                        else if ( (LA21_0=='a') ) {s = 15;}

                        else if ( (LA21_0=='n') ) {s = 16;}

                        else if ( (LA21_0=='^') ) {s = 17;}

                        else if ( (LA21_0=='.') ) {s = 18;}

                        else if ( (LA21_0=='[') ) {s = 19;}

                        else if ( (LA21_0==']') ) {s = 20;}

                        else if ( (LA21_0=='{') ) {s = 21;}

                        else if ( (LA21_0=='}') ) {s = 22;}

                        else if ( (LA21_0=='d') ) {s = 23;}

                        else if ( (LA21_0==',') ) {s = 24;}

                        else if ( (LA21_0=='=') ) {s = 25;}

                        else if ( (LA21_0=='*') ) {s = 26;}

                        else if ( (LA21_0=='/') ) {s = 27;}

                        else if ( (LA21_0=='+') ) {s = 28;}

                        else if ( (LA21_0=='-') ) {s = 29;}

                        else if ( (LA21_0=='<') ) {s = 30;}

                        else if ( (LA21_0=='>') ) {s = 31;}

                        else if ( ((LA21_0>='0' && LA21_0<='9')) ) {s = 32;}

                        else if ( (LA21_0=='\"') ) {s = 33;}

                        else if ( ((LA21_0>='A' && LA21_0<='F')||(LA21_0>='H' && LA21_0<='Z')||LA21_0=='_'||LA21_0=='c'||(LA21_0>='g' && LA21_0<='h')||(LA21_0>='j' && LA21_0<='k')||LA21_0=='m'||(LA21_0>='p' && LA21_0<='q')||LA21_0=='s'||(LA21_0>='u' && LA21_0<='v')||(LA21_0>='x' && LA21_0<='z')) ) {s = 34;}

                        else if ( ((LA21_0>='\t' && LA21_0<='\n')||LA21_0=='\r'||LA21_0==' ') ) {s = 35;}

                        else if ( ((LA21_0>='\u0000' && LA21_0<='\b')||(LA21_0>='\u000B' && LA21_0<='\f')||(LA21_0>='\u000E' && LA21_0<='\u001F')||LA21_0=='!'||(LA21_0>='#' && LA21_0<='\'')||(LA21_0>='?' && LA21_0<='@')||LA21_0=='\\'||LA21_0=='`'||LA21_0=='|'||(LA21_0>='~' && LA21_0<='\uFFFF')) ) {s = 36;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 21, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}