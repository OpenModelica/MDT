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
package org.openmodelica.modelicaml.editor.xtext.equation.parser.antlr.internal; 

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.openmodelica.modelicaml.editor.xtext.equation.services.EquationsectionGrammarAccess;

@SuppressWarnings("all")
public class InternalEquationsectionParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNSIGNED_NUMBER", "RULE_INT", "RULE_STRING", "RULE_BOOL_VAL", "RULE_IDENT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "';'", "'='", "'when'", "'then'", "'elsewhen'", "'end'", "'if'", "'elseif'", "'else'", "'for'", "'loop'", "'connect'", "'('", "','", "')'", "':'", "'or'", "'and'", "'not'", "'^'", "'.^'", "'['", "']'", "'{'", "'}'", "'initial'", "'der'", "'.'", "'in'", "'*'", "'/'", "'.*'", "'./'", "'+'", "'-'", "'.+'", "'.-'", "'<'", "'<='", "'>'", "'>='", "'=='", "'<>'"
    };
    public static final int RULE_ID=9;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_UNSIGNED_NUMBER=4;
    public static final int EOF=-1;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__51=51;
    public static final int T__15=15;
    public static final int T__52=52;
    public static final int T__18=18;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__17=17;
    public static final int T__14=14;
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
    public static final int RULE_STRING=6;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int RULE_IDENT=8;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_WS=12;

    // delegates
    // delegators


        public InternalEquationsectionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalEquationsectionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalEquationsectionParser.tokenNames; }
    public String getGrammarFileName() { return "../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g"; }



     	private EquationsectionGrammarAccess grammarAccess;
     	
        public InternalEquationsectionParser(TokenStream input, EquationsectionGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "equation_section";	
       	}
       	
       	@Override
       	protected EquationsectionGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleequation_section"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:67:1: entryRuleequation_section returns [EObject current=null] : iv_ruleequation_section= ruleequation_section EOF ;
    public final EObject entryRuleequation_section() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleequation_section = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:68:2: (iv_ruleequation_section= ruleequation_section EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:69:2: iv_ruleequation_section= ruleequation_section EOF
            {
             newCompositeNode(grammarAccess.getEquation_sectionRule()); 
            pushFollow(FOLLOW_ruleequation_section_in_entryRuleequation_section75);
            iv_ruleequation_section=ruleequation_section();

            state._fsp--;

             current =iv_ruleequation_section; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleequation_section85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleequation_section"


    // $ANTLR start "ruleequation_section"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:76:1: ruleequation_section returns [EObject current=null] : ( () ( ( (lv_Eqn_1_0= ruleequation ) ) otherlv_2= ';' )* ) ;
    public final EObject ruleequation_section() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_Eqn_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:79:28: ( ( () ( ( (lv_Eqn_1_0= ruleequation ) ) otherlv_2= ';' )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:80:1: ( () ( ( (lv_Eqn_1_0= ruleequation ) ) otherlv_2= ';' )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:80:1: ( () ( ( (lv_Eqn_1_0= ruleequation ) ) otherlv_2= ';' )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:80:2: () ( ( (lv_Eqn_1_0= ruleequation ) ) otherlv_2= ';' )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:80:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:81:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getEquation_sectionAccess().getEquation_sectionAction_0(),
                        current);
                

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:86:2: ( ( (lv_Eqn_1_0= ruleequation ) ) otherlv_2= ';' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_UNSIGNED_NUMBER && LA1_0<=RULE_IDENT)||LA1_0==16||(LA1_0>=19 && LA1_0<=20)||LA1_0==23||(LA1_0>=25 && LA1_0<=26)||LA1_0==32||LA1_0==35||LA1_0==37||(LA1_0>=39 && LA1_0<=41)||(LA1_0>=47 && LA1_0<=50)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:86:3: ( (lv_Eqn_1_0= ruleequation ) ) otherlv_2= ';'
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:86:3: ( (lv_Eqn_1_0= ruleequation ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:87:1: (lv_Eqn_1_0= ruleequation )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:87:1: (lv_Eqn_1_0= ruleequation )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:88:3: lv_Eqn_1_0= ruleequation
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getEquation_sectionAccess().getEqnEquationParserRuleCall_1_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_in_ruleequation_section141);
            	    lv_Eqn_1_0=ruleequation();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getEquation_sectionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Eqn",
            	            		lv_Eqn_1_0, 
            	            		"equation");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_2=(Token)match(input,14,FOLLOW_14_in_ruleequation_section153); 

            	        	newLeafNode(otherlv_2, grammarAccess.getEquation_sectionAccess().getSemicolonKeyword_1_1());
            	        

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleequation_section"


    // $ANTLR start "entryRuleequation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:116:1: entryRuleequation returns [EObject current=null] : iv_ruleequation= ruleequation EOF ;
    public final EObject entryRuleequation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleequation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:117:2: (iv_ruleequation= ruleequation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:118:2: iv_ruleequation= ruleequation EOF
            {
             newCompositeNode(grammarAccess.getEquationRule()); 
            pushFollow(FOLLOW_ruleequation_in_entryRuleequation191);
            iv_ruleequation=ruleequation();

            state._fsp--;

             current =iv_ruleequation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleequation201); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleequation"


    // $ANTLR start "ruleequation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:125:1: ruleequation returns [EObject current=null] : ( ( ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) | this_if_equation_3= ruleif_equation | this_for_equation_4= rulefor_equation | this_connect_clause_5= ruleconnect_clause | this_when_equation_6= rulewhen_equation ) ( (lv_Comment_7_0= rulecomment ) ) ) ;
    public final EObject ruleequation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_sim_0_0 = null;

        EObject lv_expr_2_0 = null;

        EObject this_if_equation_3 = null;

        EObject this_for_equation_4 = null;

        EObject this_connect_clause_5 = null;

        EObject this_when_equation_6 = null;

        AntlrDatatypeRuleToken lv_Comment_7_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:128:28: ( ( ( ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) | this_if_equation_3= ruleif_equation | this_for_equation_4= rulefor_equation | this_connect_clause_5= ruleconnect_clause | this_when_equation_6= rulewhen_equation ) ( (lv_Comment_7_0= rulecomment ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:1: ( ( ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) | this_if_equation_3= ruleif_equation | this_for_equation_4= rulefor_equation | this_connect_clause_5= ruleconnect_clause | this_when_equation_6= rulewhen_equation ) ( (lv_Comment_7_0= rulecomment ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:1: ( ( ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) | this_if_equation_3= ruleif_equation | this_for_equation_4= rulefor_equation | this_connect_clause_5= ruleconnect_clause | this_when_equation_6= rulewhen_equation ) ( (lv_Comment_7_0= rulecomment ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:2: ( ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) | this_if_equation_3= ruleif_equation | this_for_equation_4= rulefor_equation | this_connect_clause_5= ruleconnect_clause | this_when_equation_6= rulewhen_equation ) ( (lv_Comment_7_0= rulecomment ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:2: ( ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) | this_if_equation_3= ruleif_equation | this_for_equation_4= rulefor_equation | this_connect_clause_5= ruleconnect_clause | this_when_equation_6= rulewhen_equation )
            int alt2=5;
            switch ( input.LA(1) ) {
            case RULE_UNSIGNED_NUMBER:
            case RULE_INT:
            case RULE_STRING:
            case RULE_BOOL_VAL:
            case RULE_IDENT:
            case 19:
            case 26:
            case 32:
            case 35:
            case 37:
            case 39:
            case 40:
            case 41:
            case 47:
            case 48:
            case 49:
            case 50:
                {
                alt2=1;
                }
                break;
            case 20:
                {
                alt2=2;
                }
                break;
            case 23:
                {
                alt2=3;
                }
                break;
            case 25:
                {
                alt2=4;
                }
                break;
            case 16:
                {
                alt2=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:3: ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:3: ( ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:4: ( (lv_sim_0_0= rulesimple_expression ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:129:4: ( (lv_sim_0_0= rulesimple_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:130:1: (lv_sim_0_0= rulesimple_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:130:1: (lv_sim_0_0= rulesimple_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:131:3: lv_sim_0_0= rulesimple_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getEquationAccess().getSimSimple_expressionParserRuleCall_0_0_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulesimple_expression_in_ruleequation249);
                    lv_sim_0_0=rulesimple_expression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getEquationRule());
                    	        }
                           		set(
                           			current, 
                           			"sim",
                            		lv_sim_0_0, 
                            		"simple_expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_1=(Token)match(input,15,FOLLOW_15_in_ruleequation261); 

                        	newLeafNode(otherlv_1, grammarAccess.getEquationAccess().getEqualsSignKeyword_0_0_1());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:151:1: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:152:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:152:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:153:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getEquationAccess().getExprExpressionParserRuleCall_0_0_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleequation282);
                    lv_expr_2_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getEquationRule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_2_0, 
                            		"expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:171:5: this_if_equation_3= ruleif_equation
                    {
                     
                            newCompositeNode(grammarAccess.getEquationAccess().getIf_equationParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleif_equation_in_ruleequation311);
                    this_if_equation_3=ruleif_equation();

                    state._fsp--;

                     
                            current = this_if_equation_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:181:5: this_for_equation_4= rulefor_equation
                    {
                     
                            newCompositeNode(grammarAccess.getEquationAccess().getFor_equationParserRuleCall_0_2()); 
                        
                    pushFollow(FOLLOW_rulefor_equation_in_ruleequation338);
                    this_for_equation_4=rulefor_equation();

                    state._fsp--;

                     
                            current = this_for_equation_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:191:5: this_connect_clause_5= ruleconnect_clause
                    {
                     
                            newCompositeNode(grammarAccess.getEquationAccess().getConnect_clauseParserRuleCall_0_3()); 
                        
                    pushFollow(FOLLOW_ruleconnect_clause_in_ruleequation365);
                    this_connect_clause_5=ruleconnect_clause();

                    state._fsp--;

                     
                            current = this_connect_clause_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:201:5: this_when_equation_6= rulewhen_equation
                    {
                     
                            newCompositeNode(grammarAccess.getEquationAccess().getWhen_equationParserRuleCall_0_4()); 
                        
                    pushFollow(FOLLOW_rulewhen_equation_in_ruleequation392);
                    this_when_equation_6=rulewhen_equation();

                    state._fsp--;

                     
                            current = this_when_equation_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:209:2: ( (lv_Comment_7_0= rulecomment ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:210:1: (lv_Comment_7_0= rulecomment )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:210:1: (lv_Comment_7_0= rulecomment )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:211:3: lv_Comment_7_0= rulecomment
            {
             
            	        newCompositeNode(grammarAccess.getEquationAccess().getCommentCommentParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleequation413);
            lv_Comment_7_0=rulecomment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEquationRule());
            	        }
                   		set(
                   			current, 
                   			"Comment",
                    		lv_Comment_7_0, 
                    		"comment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleequation"


    // $ANTLR start "entryRulewhen_equation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:235:1: entryRulewhen_equation returns [EObject current=null] : iv_rulewhen_equation= rulewhen_equation EOF ;
    public final EObject entryRulewhen_equation() throws RecognitionException {
        EObject current = null;

        EObject iv_rulewhen_equation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:236:2: (iv_rulewhen_equation= rulewhen_equation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:237:2: iv_rulewhen_equation= rulewhen_equation EOF
            {
             newCompositeNode(grammarAccess.getWhen_equationRule()); 
            pushFollow(FOLLOW_rulewhen_equation_in_entryRulewhen_equation449);
            iv_rulewhen_equation=rulewhen_equation();

            state._fsp--;

             current =iv_rulewhen_equation; 
            match(input,EOF,FOLLOW_EOF_in_entryRulewhen_equation459); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulewhen_equation"


    // $ANTLR start "rulewhen_equation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:244:1: rulewhen_equation returns [EObject current=null] : (otherlv_0= 'when' ( (lv_whenEpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_Then_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elsewhen' ( (lv_elseWhenEpr_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )* )* otherlv_10= 'end' otherlv_11= 'when' ) ;
    public final EObject rulewhen_equation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        EObject lv_whenEpr_1_0 = null;

        EObject lv_Eqn_Then_3_0 = null;

        EObject lv_elseWhenEpr_6_0 = null;

        EObject lv_Eqn_Then_8_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:247:28: ( (otherlv_0= 'when' ( (lv_whenEpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_Then_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elsewhen' ( (lv_elseWhenEpr_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )* )* otherlv_10= 'end' otherlv_11= 'when' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:248:1: (otherlv_0= 'when' ( (lv_whenEpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_Then_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elsewhen' ( (lv_elseWhenEpr_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )* )* otherlv_10= 'end' otherlv_11= 'when' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:248:1: (otherlv_0= 'when' ( (lv_whenEpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_Then_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elsewhen' ( (lv_elseWhenEpr_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )* )* otherlv_10= 'end' otherlv_11= 'when' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:248:3: otherlv_0= 'when' ( (lv_whenEpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_Then_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elsewhen' ( (lv_elseWhenEpr_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )* )* otherlv_10= 'end' otherlv_11= 'when'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_rulewhen_equation496); 

                	newLeafNode(otherlv_0, grammarAccess.getWhen_equationAccess().getWhenKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:252:1: ( (lv_whenEpr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:253:1: (lv_whenEpr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:253:1: (lv_whenEpr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:254:3: lv_whenEpr_1_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getWhen_equationAccess().getWhenEprExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulewhen_equation517);
            lv_whenEpr_1_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getWhen_equationRule());
            	        }
                   		set(
                   			current, 
                   			"whenEpr",
                    		lv_whenEpr_1_0, 
                    		"expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_rulewhen_equation529); 

                	newLeafNode(otherlv_2, grammarAccess.getWhen_equationAccess().getThenKeyword_2());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:274:1: ( ( (lv_Eqn_Then_3_0= ruleequation ) ) otherlv_4= ';' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==19) ) {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==15||(LA3_2>=29 && LA3_2<=31)||(LA3_2>=33 && LA3_2<=34)||(LA3_2>=43 && LA3_2<=56)) ) {
                        alt3=1;
                    }


                }
                else if ( ((LA3_0>=RULE_UNSIGNED_NUMBER && LA3_0<=RULE_IDENT)||LA3_0==16||LA3_0==20||LA3_0==23||(LA3_0>=25 && LA3_0<=26)||LA3_0==32||LA3_0==35||LA3_0==37||(LA3_0>=39 && LA3_0<=41)||(LA3_0>=47 && LA3_0<=50)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:274:2: ( (lv_Eqn_Then_3_0= ruleequation ) ) otherlv_4= ';'
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:274:2: ( (lv_Eqn_Then_3_0= ruleequation ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:275:1: (lv_Eqn_Then_3_0= ruleequation )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:275:1: (lv_Eqn_Then_3_0= ruleequation )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:276:3: lv_Eqn_Then_3_0= ruleequation
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getWhen_equationAccess().getEqn_ThenEquationParserRuleCall_3_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_in_rulewhen_equation551);
            	    lv_Eqn_Then_3_0=ruleequation();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getWhen_equationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Eqn_Then",
            	            		lv_Eqn_Then_3_0, 
            	            		"equation");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_4=(Token)match(input,14,FOLLOW_14_in_rulewhen_equation563); 

            	        	newLeafNode(otherlv_4, grammarAccess.getWhen_equationAccess().getSemicolonKeyword_3_1());
            	        

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:296:3: (otherlv_5= 'elsewhen' ( (lv_elseWhenEpr_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )* )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==18) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:296:5: otherlv_5= 'elsewhen' ( (lv_elseWhenEpr_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )*
            	    {
            	    otherlv_5=(Token)match(input,18,FOLLOW_18_in_rulewhen_equation578); 

            	        	newLeafNode(otherlv_5, grammarAccess.getWhen_equationAccess().getElsewhenKeyword_4_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:300:1: ( (lv_elseWhenEpr_6_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:301:1: (lv_elseWhenEpr_6_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:301:1: (lv_elseWhenEpr_6_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:302:3: lv_elseWhenEpr_6_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getWhen_equationAccess().getElseWhenEprExpressionParserRuleCall_4_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_rulewhen_equation599);
            	    lv_elseWhenEpr_6_0=ruleexpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getWhen_equationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"elseWhenEpr",
            	            		lv_elseWhenEpr_6_0, 
            	            		"expression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_7=(Token)match(input,17,FOLLOW_17_in_rulewhen_equation611); 

            	        	newLeafNode(otherlv_7, grammarAccess.getWhen_equationAccess().getThenKeyword_4_2());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:322:1: ( ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';' )*
            	    loop4:
            	    do {
            	        int alt4=2;
            	        int LA4_0 = input.LA(1);

            	        if ( (LA4_0==19) ) {
            	            int LA4_1 = input.LA(2);

            	            if ( (LA4_1==15||(LA4_1>=29 && LA4_1<=31)||(LA4_1>=33 && LA4_1<=34)||(LA4_1>=43 && LA4_1<=56)) ) {
            	                alt4=1;
            	            }


            	        }
            	        else if ( ((LA4_0>=RULE_UNSIGNED_NUMBER && LA4_0<=RULE_IDENT)||LA4_0==16||LA4_0==20||LA4_0==23||(LA4_0>=25 && LA4_0<=26)||LA4_0==32||LA4_0==35||LA4_0==37||(LA4_0>=39 && LA4_0<=41)||(LA4_0>=47 && LA4_0<=50)) ) {
            	            alt4=1;
            	        }


            	        switch (alt4) {
            	    	case 1 :
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:322:2: ( (lv_Eqn_Then_8_0= ruleequation ) ) otherlv_9= ';'
            	    	    {
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:322:2: ( (lv_Eqn_Then_8_0= ruleequation ) )
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:323:1: (lv_Eqn_Then_8_0= ruleequation )
            	    	    {
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:323:1: (lv_Eqn_Then_8_0= ruleequation )
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:324:3: lv_Eqn_Then_8_0= ruleequation
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getWhen_equationAccess().getEqn_ThenEquationParserRuleCall_4_3_0_0()); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleequation_in_rulewhen_equation633);
            	    	    lv_Eqn_Then_8_0=ruleequation();

            	    	    state._fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = createModelElementForParent(grammarAccess.getWhen_equationRule());
            	    	    	        }
            	    	           		add(
            	    	           			current, 
            	    	           			"Eqn_Then",
            	    	            		lv_Eqn_Then_8_0, 
            	    	            		"equation");
            	    	    	        afterParserOrEnumRuleCall();
            	    	    	    

            	    	    }


            	    	    }

            	    	    otherlv_9=(Token)match(input,14,FOLLOW_14_in_rulewhen_equation645); 

            	    	        	newLeafNode(otherlv_9, grammarAccess.getWhen_equationAccess().getSemicolonKeyword_4_3_1());
            	    	        

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop4;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_10=(Token)match(input,19,FOLLOW_19_in_rulewhen_equation661); 

                	newLeafNode(otherlv_10, grammarAccess.getWhen_equationAccess().getEndKeyword_5());
                
            otherlv_11=(Token)match(input,16,FOLLOW_16_in_rulewhen_equation673); 

                	newLeafNode(otherlv_11, grammarAccess.getWhen_equationAccess().getWhenKeyword_6());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulewhen_equation"


    // $ANTLR start "entryRuleif_equation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:360:1: entryRuleif_equation returns [EObject current=null] : iv_ruleif_equation= ruleif_equation EOF ;
    public final EObject entryRuleif_equation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleif_equation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:361:2: (iv_ruleif_equation= ruleif_equation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:362:2: iv_ruleif_equation= ruleif_equation EOF
            {
             newCompositeNode(grammarAccess.getIf_equationRule()); 
            pushFollow(FOLLOW_ruleif_equation_in_entryRuleif_equation709);
            iv_ruleif_equation=ruleif_equation();

            state._fsp--;

             current =iv_ruleif_equation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleif_equation719); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleif_equation"


    // $ANTLR start "ruleif_equation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:369:1: ruleif_equation returns [EObject current=null] : (otherlv_0= 'if' ( (lv_exprtrue_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elseif' ( (lv_exprStilltrue_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )* )* (otherlv_10= 'else' ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )* )? otherlv_13= 'end' otherlv_14= 'if' ) ;
    public final EObject ruleif_equation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        EObject lv_exprtrue_1_0 = null;

        EObject lv_Eqn_3_0 = null;

        EObject lv_exprStilltrue_6_0 = null;

        EObject lv_thenEqn_8_0 = null;

        EObject lv_elseEqn_11_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:372:28: ( (otherlv_0= 'if' ( (lv_exprtrue_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elseif' ( (lv_exprStilltrue_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )* )* (otherlv_10= 'else' ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )* )? otherlv_13= 'end' otherlv_14= 'if' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:373:1: (otherlv_0= 'if' ( (lv_exprtrue_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elseif' ( (lv_exprStilltrue_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )* )* (otherlv_10= 'else' ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )* )? otherlv_13= 'end' otherlv_14= 'if' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:373:1: (otherlv_0= 'if' ( (lv_exprtrue_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elseif' ( (lv_exprStilltrue_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )* )* (otherlv_10= 'else' ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )* )? otherlv_13= 'end' otherlv_14= 'if' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:373:3: otherlv_0= 'if' ( (lv_exprtrue_1_0= ruleexpression ) ) otherlv_2= 'then' ( ( (lv_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* (otherlv_5= 'elseif' ( (lv_exprStilltrue_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )* )* (otherlv_10= 'else' ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )* )? otherlv_13= 'end' otherlv_14= 'if'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_20_in_ruleif_equation756); 

                	newLeafNode(otherlv_0, grammarAccess.getIf_equationAccess().getIfKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:377:1: ( (lv_exprtrue_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:378:1: (lv_exprtrue_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:378:1: (lv_exprtrue_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:379:3: lv_exprtrue_1_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getIf_equationAccess().getExprtrueExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleif_equation777);
            lv_exprtrue_1_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getIf_equationRule());
            	        }
                   		set(
                   			current, 
                   			"exprtrue",
                    		lv_exprtrue_1_0, 
                    		"expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleif_equation789); 

                	newLeafNode(otherlv_2, grammarAccess.getIf_equationAccess().getThenKeyword_2());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:399:1: ( ( (lv_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==19) ) {
                    int LA6_2 = input.LA(2);

                    if ( (LA6_2==15||(LA6_2>=29 && LA6_2<=31)||(LA6_2>=33 && LA6_2<=34)||(LA6_2>=43 && LA6_2<=56)) ) {
                        alt6=1;
                    }


                }
                else if ( ((LA6_0>=RULE_UNSIGNED_NUMBER && LA6_0<=RULE_IDENT)||LA6_0==16||LA6_0==20||LA6_0==23||(LA6_0>=25 && LA6_0<=26)||LA6_0==32||LA6_0==35||LA6_0==37||(LA6_0>=39 && LA6_0<=41)||(LA6_0>=47 && LA6_0<=50)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:399:2: ( (lv_Eqn_3_0= ruleequation ) ) otherlv_4= ';'
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:399:2: ( (lv_Eqn_3_0= ruleequation ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:400:1: (lv_Eqn_3_0= ruleequation )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:400:1: (lv_Eqn_3_0= ruleequation )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:401:3: lv_Eqn_3_0= ruleequation
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getIf_equationAccess().getEqnEquationParserRuleCall_3_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_in_ruleif_equation811);
            	    lv_Eqn_3_0=ruleequation();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getIf_equationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Eqn",
            	            		lv_Eqn_3_0, 
            	            		"equation");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleif_equation823); 

            	        	newLeafNode(otherlv_4, grammarAccess.getIf_equationAccess().getSemicolonKeyword_3_1());
            	        

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:421:3: (otherlv_5= 'elseif' ( (lv_exprStilltrue_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )* )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==21) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:421:5: otherlv_5= 'elseif' ( (lv_exprStilltrue_6_0= ruleexpression ) ) otherlv_7= 'then' ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )*
            	    {
            	    otherlv_5=(Token)match(input,21,FOLLOW_21_in_ruleif_equation838); 

            	        	newLeafNode(otherlv_5, grammarAccess.getIf_equationAccess().getElseifKeyword_4_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:425:1: ( (lv_exprStilltrue_6_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:426:1: (lv_exprStilltrue_6_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:426:1: (lv_exprStilltrue_6_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:427:3: lv_exprStilltrue_6_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getIf_equationAccess().getExprStilltrueExpressionParserRuleCall_4_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleif_equation859);
            	    lv_exprStilltrue_6_0=ruleexpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getIf_equationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exprStilltrue",
            	            		lv_exprStilltrue_6_0, 
            	            		"expression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_7=(Token)match(input,17,FOLLOW_17_in_ruleif_equation871); 

            	        	newLeafNode(otherlv_7, grammarAccess.getIf_equationAccess().getThenKeyword_4_2());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:447:1: ( ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';' )*
            	    loop7:
            	    do {
            	        int alt7=2;
            	        int LA7_0 = input.LA(1);

            	        if ( (LA7_0==19) ) {
            	            int LA7_2 = input.LA(2);

            	            if ( (LA7_2==15||(LA7_2>=29 && LA7_2<=31)||(LA7_2>=33 && LA7_2<=34)||(LA7_2>=43 && LA7_2<=56)) ) {
            	                alt7=1;
            	            }


            	        }
            	        else if ( ((LA7_0>=RULE_UNSIGNED_NUMBER && LA7_0<=RULE_IDENT)||LA7_0==16||LA7_0==20||LA7_0==23||(LA7_0>=25 && LA7_0<=26)||LA7_0==32||LA7_0==35||LA7_0==37||(LA7_0>=39 && LA7_0<=41)||(LA7_0>=47 && LA7_0<=50)) ) {
            	            alt7=1;
            	        }


            	        switch (alt7) {
            	    	case 1 :
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:447:2: ( (lv_thenEqn_8_0= ruleequation ) ) otherlv_9= ';'
            	    	    {
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:447:2: ( (lv_thenEqn_8_0= ruleequation ) )
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:448:1: (lv_thenEqn_8_0= ruleequation )
            	    	    {
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:448:1: (lv_thenEqn_8_0= ruleequation )
            	    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:449:3: lv_thenEqn_8_0= ruleequation
            	    	    {
            	    	     
            	    	    	        newCompositeNode(grammarAccess.getIf_equationAccess().getThenEqnEquationParserRuleCall_4_3_0_0()); 
            	    	    	    
            	    	    pushFollow(FOLLOW_ruleequation_in_ruleif_equation893);
            	    	    lv_thenEqn_8_0=ruleequation();

            	    	    state._fsp--;


            	    	    	        if (current==null) {
            	    	    	            current = createModelElementForParent(grammarAccess.getIf_equationRule());
            	    	    	        }
            	    	           		add(
            	    	           			current, 
            	    	           			"thenEqn",
            	    	            		lv_thenEqn_8_0, 
            	    	            		"equation");
            	    	    	        afterParserOrEnumRuleCall();
            	    	    	    

            	    	    }


            	    	    }

            	    	    otherlv_9=(Token)match(input,14,FOLLOW_14_in_ruleif_equation905); 

            	    	        	newLeafNode(otherlv_9, grammarAccess.getIf_equationAccess().getSemicolonKeyword_4_3_1());
            	    	        

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop7;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:469:5: (otherlv_10= 'else' ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )* )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==22) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:469:7: otherlv_10= 'else' ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )*
                    {
                    otherlv_10=(Token)match(input,22,FOLLOW_22_in_ruleif_equation922); 

                        	newLeafNode(otherlv_10, grammarAccess.getIf_equationAccess().getElseKeyword_5_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:473:1: ( ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';' )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==19) ) {
                            int LA9_1 = input.LA(2);

                            if ( (LA9_1==15||(LA9_1>=29 && LA9_1<=31)||(LA9_1>=33 && LA9_1<=34)||(LA9_1>=43 && LA9_1<=56)) ) {
                                alt9=1;
                            }


                        }
                        else if ( ((LA9_0>=RULE_UNSIGNED_NUMBER && LA9_0<=RULE_IDENT)||LA9_0==16||LA9_0==20||LA9_0==23||(LA9_0>=25 && LA9_0<=26)||LA9_0==32||LA9_0==35||LA9_0==37||(LA9_0>=39 && LA9_0<=41)||(LA9_0>=47 && LA9_0<=50)) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:473:2: ( (lv_elseEqn_11_0= ruleequation ) ) otherlv_12= ';'
                    	    {
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:473:2: ( (lv_elseEqn_11_0= ruleequation ) )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:474:1: (lv_elseEqn_11_0= ruleequation )
                    	    {
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:474:1: (lv_elseEqn_11_0= ruleequation )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:475:3: lv_elseEqn_11_0= ruleequation
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getIf_equationAccess().getElseEqnEquationParserRuleCall_5_1_0_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleequation_in_ruleif_equation944);
                    	    lv_elseEqn_11_0=ruleequation();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getIf_equationRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"elseEqn",
                    	            		lv_elseEqn_11_0, 
                    	            		"equation");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }

                    	    otherlv_12=(Token)match(input,14,FOLLOW_14_in_ruleif_equation956); 

                    	        	newLeafNode(otherlv_12, grammarAccess.getIf_equationAccess().getSemicolonKeyword_5_1_1());
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_13=(Token)match(input,19,FOLLOW_19_in_ruleif_equation972); 

                	newLeafNode(otherlv_13, grammarAccess.getIf_equationAccess().getEndKeyword_6());
                
            otherlv_14=(Token)match(input,20,FOLLOW_20_in_ruleif_equation984); 

                	newLeafNode(otherlv_14, grammarAccess.getIf_equationAccess().getIfKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleif_equation"


    // $ANTLR start "entryRulefor_equation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:511:1: entryRulefor_equation returns [EObject current=null] : iv_rulefor_equation= rulefor_equation EOF ;
    public final EObject entryRulefor_equation() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_equation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:512:2: (iv_rulefor_equation= rulefor_equation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:513:2: iv_rulefor_equation= rulefor_equation EOF
            {
             newCompositeNode(grammarAccess.getFor_equationRule()); 
            pushFollow(FOLLOW_rulefor_equation_in_entryRulefor_equation1020);
            iv_rulefor_equation=rulefor_equation();

            state._fsp--;

             current =iv_rulefor_equation; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_equation1030); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulefor_equation"


    // $ANTLR start "rulefor_equation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:520:1: rulefor_equation returns [EObject current=null] : (otherlv_0= 'for' ( (lv_for_loop_1_0= rulefor_indices ) ) otherlv_2= 'loop' ( ( (lv_For_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* otherlv_5= 'end' otherlv_6= 'for' ) ;
    public final EObject rulefor_equation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_for_loop_1_0 = null;

        EObject lv_For_Eqn_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:523:28: ( (otherlv_0= 'for' ( (lv_for_loop_1_0= rulefor_indices ) ) otherlv_2= 'loop' ( ( (lv_For_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* otherlv_5= 'end' otherlv_6= 'for' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:524:1: (otherlv_0= 'for' ( (lv_for_loop_1_0= rulefor_indices ) ) otherlv_2= 'loop' ( ( (lv_For_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* otherlv_5= 'end' otherlv_6= 'for' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:524:1: (otherlv_0= 'for' ( (lv_for_loop_1_0= rulefor_indices ) ) otherlv_2= 'loop' ( ( (lv_For_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* otherlv_5= 'end' otherlv_6= 'for' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:524:3: otherlv_0= 'for' ( (lv_for_loop_1_0= rulefor_indices ) ) otherlv_2= 'loop' ( ( (lv_For_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )* otherlv_5= 'end' otherlv_6= 'for'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_rulefor_equation1067); 

                	newLeafNode(otherlv_0, grammarAccess.getFor_equationAccess().getForKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:528:1: ( (lv_for_loop_1_0= rulefor_indices ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:529:1: (lv_for_loop_1_0= rulefor_indices )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:529:1: (lv_for_loop_1_0= rulefor_indices )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:530:3: lv_for_loop_1_0= rulefor_indices
            {
             
            	        newCompositeNode(grammarAccess.getFor_equationAccess().getFor_loopFor_indicesParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_rulefor_equation1088);
            lv_for_loop_1_0=rulefor_indices();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFor_equationRule());
            	        }
                   		set(
                   			current, 
                   			"for_loop",
                    		lv_for_loop_1_0, 
                    		"for_indices");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,24,FOLLOW_24_in_rulefor_equation1100); 

                	newLeafNode(otherlv_2, grammarAccess.getFor_equationAccess().getLoopKeyword_2());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:550:1: ( ( (lv_For_Eqn_3_0= ruleequation ) ) otherlv_4= ';' )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==19) ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==15||(LA11_1>=29 && LA11_1<=31)||(LA11_1>=33 && LA11_1<=34)||(LA11_1>=43 && LA11_1<=56)) ) {
                        alt11=1;
                    }


                }
                else if ( ((LA11_0>=RULE_UNSIGNED_NUMBER && LA11_0<=RULE_IDENT)||LA11_0==16||LA11_0==20||LA11_0==23||(LA11_0>=25 && LA11_0<=26)||LA11_0==32||LA11_0==35||LA11_0==37||(LA11_0>=39 && LA11_0<=41)||(LA11_0>=47 && LA11_0<=50)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:550:2: ( (lv_For_Eqn_3_0= ruleequation ) ) otherlv_4= ';'
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:550:2: ( (lv_For_Eqn_3_0= ruleequation ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:551:1: (lv_For_Eqn_3_0= ruleequation )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:551:1: (lv_For_Eqn_3_0= ruleequation )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:552:3: lv_For_Eqn_3_0= ruleequation
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFor_equationAccess().getFor_EqnEquationParserRuleCall_3_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_in_rulefor_equation1122);
            	    lv_For_Eqn_3_0=ruleequation();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFor_equationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"For_Eqn",
            	            		lv_For_Eqn_3_0, 
            	            		"equation");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_4=(Token)match(input,14,FOLLOW_14_in_rulefor_equation1134); 

            	        	newLeafNode(otherlv_4, grammarAccess.getFor_equationAccess().getSemicolonKeyword_3_1());
            	        

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_5=(Token)match(input,19,FOLLOW_19_in_rulefor_equation1148); 

                	newLeafNode(otherlv_5, grammarAccess.getFor_equationAccess().getEndKeyword_4());
                
            otherlv_6=(Token)match(input,23,FOLLOW_23_in_rulefor_equation1160); 

                	newLeafNode(otherlv_6, grammarAccess.getFor_equationAccess().getForKeyword_5());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulefor_equation"


    // $ANTLR start "entryRuleconnect_clause"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:588:1: entryRuleconnect_clause returns [EObject current=null] : iv_ruleconnect_clause= ruleconnect_clause EOF ;
    public final EObject entryRuleconnect_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconnect_clause = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:589:2: (iv_ruleconnect_clause= ruleconnect_clause EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:590:2: iv_ruleconnect_clause= ruleconnect_clause EOF
            {
             newCompositeNode(grammarAccess.getConnect_clauseRule()); 
            pushFollow(FOLLOW_ruleconnect_clause_in_entryRuleconnect_clause1196);
            iv_ruleconnect_clause=ruleconnect_clause();

            state._fsp--;

             current =iv_ruleconnect_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconnect_clause1206); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleconnect_clause"


    // $ANTLR start "ruleconnect_clause"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:597:1: ruleconnect_clause returns [EObject current=null] : (otherlv_0= 'connect' otherlv_1= '(' ( (lv_connector1_2_0= rulecomponent_reference ) ) otherlv_3= ',' ( (lv_connector2_4_0= rulecomponent_reference ) ) otherlv_5= ')' ) ;
    public final EObject ruleconnect_clause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_connector1_2_0 = null;

        EObject lv_connector2_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:600:28: ( (otherlv_0= 'connect' otherlv_1= '(' ( (lv_connector1_2_0= rulecomponent_reference ) ) otherlv_3= ',' ( (lv_connector2_4_0= rulecomponent_reference ) ) otherlv_5= ')' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:601:1: (otherlv_0= 'connect' otherlv_1= '(' ( (lv_connector1_2_0= rulecomponent_reference ) ) otherlv_3= ',' ( (lv_connector2_4_0= rulecomponent_reference ) ) otherlv_5= ')' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:601:1: (otherlv_0= 'connect' otherlv_1= '(' ( (lv_connector1_2_0= rulecomponent_reference ) ) otherlv_3= ',' ( (lv_connector2_4_0= rulecomponent_reference ) ) otherlv_5= ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:601:3: otherlv_0= 'connect' otherlv_1= '(' ( (lv_connector1_2_0= rulecomponent_reference ) ) otherlv_3= ',' ( (lv_connector2_4_0= rulecomponent_reference ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_25_in_ruleconnect_clause1243); 

                	newLeafNode(otherlv_0, grammarAccess.getConnect_clauseAccess().getConnectKeyword_0());
                
            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleconnect_clause1255); 

                	newLeafNode(otherlv_1, grammarAccess.getConnect_clauseAccess().getLeftParenthesisKeyword_1());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:609:1: ( (lv_connector1_2_0= rulecomponent_reference ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:610:1: (lv_connector1_2_0= rulecomponent_reference )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:610:1: (lv_connector1_2_0= rulecomponent_reference )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:611:3: lv_connector1_2_0= rulecomponent_reference
            {
             
            	        newCompositeNode(grammarAccess.getConnect_clauseAccess().getConnector1Component_referenceParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_rulecomponent_reference_in_ruleconnect_clause1276);
            lv_connector1_2_0=rulecomponent_reference();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConnect_clauseRule());
            	        }
                   		set(
                   			current, 
                   			"connector1",
                    		lv_connector1_2_0, 
                    		"component_reference");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleconnect_clause1288); 

                	newLeafNode(otherlv_3, grammarAccess.getConnect_clauseAccess().getCommaKeyword_3());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:631:1: ( (lv_connector2_4_0= rulecomponent_reference ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:632:1: (lv_connector2_4_0= rulecomponent_reference )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:632:1: (lv_connector2_4_0= rulecomponent_reference )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:633:3: lv_connector2_4_0= rulecomponent_reference
            {
             
            	        newCompositeNode(grammarAccess.getConnect_clauseAccess().getConnector2Component_referenceParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_rulecomponent_reference_in_ruleconnect_clause1309);
            lv_connector2_4_0=rulecomponent_reference();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConnect_clauseRule());
            	        }
                   		set(
                   			current, 
                   			"connector2",
                    		lv_connector2_4_0, 
                    		"component_reference");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,28,FOLLOW_28_in_ruleconnect_clause1321); 

                	newLeafNode(otherlv_5, grammarAccess.getConnect_clauseAccess().getRightParenthesisKeyword_5());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleconnect_clause"


    // $ANTLR start "entryRuleexpression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:661:1: entryRuleexpression returns [EObject current=null] : iv_ruleexpression= ruleexpression EOF ;
    public final EObject entryRuleexpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:662:2: (iv_ruleexpression= ruleexpression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:663:2: iv_ruleexpression= ruleexpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleexpression_in_entryRuleexpression1357);
            iv_ruleexpression=ruleexpression();

            state._fsp--;

             current =iv_ruleexpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression1367); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleexpression"


    // $ANTLR start "ruleexpression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:670:1: ruleexpression returns [EObject current=null] : ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) ;
    public final EObject ruleexpression() throws RecognitionException {
        EObject current = null;

        EObject lv_Expr_0_0 = null;

        EObject this_conditional_expr_1 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:673:28: ( ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:674:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:674:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_UNSIGNED_NUMBER && LA12_0<=RULE_IDENT)||LA12_0==19||LA12_0==26||LA12_0==32||LA12_0==35||LA12_0==37||(LA12_0>=39 && LA12_0<=41)||(LA12_0>=47 && LA12_0<=50)) ) {
                alt12=1;
            }
            else if ( (LA12_0==20) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:674:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:674:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:675:1: (lv_Expr_0_0= rulesimple_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:675:1: (lv_Expr_0_0= rulesimple_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:676:3: lv_Expr_0_0= rulesimple_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getExprSimple_expressionParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulesimple_expression_in_ruleexpression1413);
                    lv_Expr_0_0=rulesimple_expression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionRule());
                    	        }
                           		set(
                           			current, 
                           			"Expr",
                            		lv_Expr_0_0, 
                            		"simple_expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:694:5: this_conditional_expr_1= ruleconditional_expr
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getConditional_exprParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleconditional_expr_in_ruleexpression1441);
                    this_conditional_expr_1=ruleconditional_expr();

                    state._fsp--;

                     
                            current = this_conditional_expr_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleexpression"


    // $ANTLR start "entryRulesimple_expression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:710:1: entryRulesimple_expression returns [EObject current=null] : iv_rulesimple_expression= rulesimple_expression EOF ;
    public final EObject entryRulesimple_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesimple_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:711:2: (iv_rulesimple_expression= rulesimple_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:712:2: iv_rulesimple_expression= rulesimple_expression EOF
            {
             newCompositeNode(grammarAccess.getSimple_expressionRule()); 
            pushFollow(FOLLOW_rulesimple_expression_in_entryRulesimple_expression1476);
            iv_rulesimple_expression=rulesimple_expression();

            state._fsp--;

             current =iv_rulesimple_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesimple_expression1486); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulesimple_expression"


    // $ANTLR start "rulesimple_expression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:719:1: rulesimple_expression returns [EObject current=null] : ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) ;
    public final EObject rulesimple_expression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_Log_Exp_0_0 = null;

        EObject lv_S_Logical_expression_2_0 = null;

        EObject lv_L_Logical_expression_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:722:28: ( ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:723:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:723:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:723:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:723:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:724:1: (lv_Log_Exp_0_0= rulelogical_expression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:724:1: (lv_Log_Exp_0_0= rulelogical_expression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:725:3: lv_Log_Exp_0_0= rulelogical_expression
            {
             
            	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getLog_ExpLogical_expressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression1532);
            lv_Log_Exp_0_0=rulelogical_expression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSimple_expressionRule());
            	        }
                   		set(
                   			current, 
                   			"Log_Exp",
                    		lv_Log_Exp_0_0, 
                    		"logical_expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:741:2: (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==29) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:741:4: otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    {
                    otherlv_1=(Token)match(input,29,FOLLOW_29_in_rulesimple_expression1545); 

                        	newLeafNode(otherlv_1, grammarAccess.getSimple_expressionAccess().getColonKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:745:1: ( (lv_S_Logical_expression_2_0= rulelogical_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:746:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:746:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:747:3: lv_S_Logical_expression_2_0= rulelogical_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getS_Logical_expressionLogical_expressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression1566);
                    lv_S_Logical_expression_2_0=rulelogical_expression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSimple_expressionRule());
                    	        }
                           		set(
                           			current, 
                           			"S_Logical_expression",
                            		lv_S_Logical_expression_2_0, 
                            		"logical_expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:763:2: (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==29) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:763:4: otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            {
                            otherlv_3=(Token)match(input,29,FOLLOW_29_in_rulesimple_expression1579); 

                                	newLeafNode(otherlv_3, grammarAccess.getSimple_expressionAccess().getColonKeyword_1_2_0());
                                
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:767:1: ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:768:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:768:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:769:3: lv_L_Logical_expression_4_0= rulelogical_expression
                            {
                             
                            	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression1600);
                            lv_L_Logical_expression_4_0=rulelogical_expression();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getSimple_expressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"L_Logical_expression",
                                    		lv_L_Logical_expression_4_0, 
                                    		"logical_expression");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulesimple_expression"


    // $ANTLR start "entryRuleconditional_expr"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:793:1: entryRuleconditional_expr returns [EObject current=null] : iv_ruleconditional_expr= ruleconditional_expr EOF ;
    public final EObject entryRuleconditional_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_expr = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:794:2: (iv_ruleconditional_expr= ruleconditional_expr EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:795:2: iv_ruleconditional_expr= ruleconditional_expr EOF
            {
             newCompositeNode(grammarAccess.getConditional_exprRule()); 
            pushFollow(FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr1640);
            iv_ruleconditional_expr=ruleconditional_expr();

            state._fsp--;

             current =iv_ruleconditional_expr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_expr1650); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleconditional_expr"


    // $ANTLR start "ruleconditional_expr"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:802:1: ruleconditional_expr returns [EObject current=null] : (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) ;
    public final EObject ruleconditional_expr() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_ifexpr_1_0 = null;

        EObject lv_thenexpr_3_0 = null;

        EObject lv_elseifexpr_5_0 = null;

        EObject lv_trueexpr_7_0 = null;

        EObject lv_falseexpr_9_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:805:28: ( (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:806:1: (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:806:1: (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:806:3: otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_20_in_ruleconditional_expr1687); 

                	newLeafNode(otherlv_0, grammarAccess.getConditional_exprAccess().getIfKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:810:1: ( (lv_ifexpr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:811:1: (lv_ifexpr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:811:1: (lv_ifexpr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:812:3: lv_ifexpr_1_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getIfexprExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr1708);
            lv_ifexpr_1_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditional_exprRule());
            	        }
                   		set(
                   			current, 
                   			"ifexpr",
                    		lv_ifexpr_1_0, 
                    		"expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleconditional_expr1720); 

                	newLeafNode(otherlv_2, grammarAccess.getConditional_exprAccess().getThenKeyword_2());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:832:1: ( (lv_thenexpr_3_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:833:1: (lv_thenexpr_3_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:833:1: (lv_thenexpr_3_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:834:3: lv_thenexpr_3_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getThenexprExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr1741);
            lv_thenexpr_3_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditional_exprRule());
            	        }
                   		set(
                   			current, 
                   			"thenexpr",
                    		lv_thenexpr_3_0, 
                    		"expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:850:2: (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==21) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:850:4: otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) )
            	    {
            	    otherlv_4=(Token)match(input,21,FOLLOW_21_in_ruleconditional_expr1754); 

            	        	newLeafNode(otherlv_4, grammarAccess.getConditional_exprAccess().getElseifKeyword_4_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:854:1: ( (lv_elseifexpr_5_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:855:1: (lv_elseifexpr_5_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:855:1: (lv_elseifexpr_5_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:856:3: lv_elseifexpr_5_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditional_exprAccess().getElseifexprExpressionParserRuleCall_4_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr1775);
            	    lv_elseifexpr_5_0=ruleexpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getConditional_exprRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"elseifexpr",
            	            		lv_elseifexpr_5_0, 
            	            		"expression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_6=(Token)match(input,17,FOLLOW_17_in_ruleconditional_expr1787); 

            	        	newLeafNode(otherlv_6, grammarAccess.getConditional_exprAccess().getThenKeyword_4_2());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:876:1: ( (lv_trueexpr_7_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:877:1: (lv_trueexpr_7_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:877:1: (lv_trueexpr_7_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:878:3: lv_trueexpr_7_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditional_exprAccess().getTrueexprExpressionParserRuleCall_4_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr1808);
            	    lv_trueexpr_7_0=ruleexpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getConditional_exprRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"trueexpr",
            	            		lv_trueexpr_7_0, 
            	            		"expression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:894:4: (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:894:6: otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) )
            {
            otherlv_8=(Token)match(input,22,FOLLOW_22_in_ruleconditional_expr1823); 

                	newLeafNode(otherlv_8, grammarAccess.getConditional_exprAccess().getElseKeyword_5_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:898:1: ( (lv_falseexpr_9_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:899:1: (lv_falseexpr_9_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:899:1: (lv_falseexpr_9_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:900:3: lv_falseexpr_9_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getFalseexprExpressionParserRuleCall_5_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr1844);
            lv_falseexpr_9_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditional_exprRule());
            	        }
                   		set(
                   			current, 
                   			"falseexpr",
                    		lv_falseexpr_9_0, 
                    		"expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleconditional_expr"


    // $ANTLR start "entryRulelogical_expression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:924:1: entryRulelogical_expression returns [EObject current=null] : iv_rulelogical_expression= rulelogical_expression EOF ;
    public final EObject entryRulelogical_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:925:2: (iv_rulelogical_expression= rulelogical_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:926:2: iv_rulelogical_expression= rulelogical_expression EOF
            {
             newCompositeNode(grammarAccess.getLogical_expressionRule()); 
            pushFollow(FOLLOW_rulelogical_expression_in_entryRulelogical_expression1881);
            iv_rulelogical_expression=rulelogical_expression();

            state._fsp--;

             current =iv_rulelogical_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_expression1891); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulelogical_expression"


    // $ANTLR start "rulelogical_expression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:933:1: rulelogical_expression returns [EObject current=null] : (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) ;
    public final EObject rulelogical_expression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_logical_term_0 = null;

        EObject lv_Logical_term_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:936:28: ( (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:937:1: (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:937:1: (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:938:5: this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression1938);
            this_logical_term_0=rulelogical_term();

            state._fsp--;

             
                    current = this_logical_term_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:946:1: (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==30) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:946:3: otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    {
            	    otherlv_1=(Token)match(input,30,FOLLOW_30_in_rulelogical_expression1950); 

            	        	newLeafNode(otherlv_1, grammarAccess.getLogical_expressionAccess().getOrKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:950:1: ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:951:1: (lv_Logical_term_2_0= rulelogical_term )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:951:1: (lv_Logical_term_2_0= rulelogical_term )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:952:3: lv_Logical_term_2_0= rulelogical_term
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termLogical_termParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression1971);
            	    lv_Logical_term_2_0=rulelogical_term();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getLogical_expressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Logical_term",
            	            		lv_Logical_term_2_0, 
            	            		"logical_term");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulelogical_expression"


    // $ANTLR start "entryRulelogical_term"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:976:1: entryRulelogical_term returns [EObject current=null] : iv_rulelogical_term= rulelogical_term EOF ;
    public final EObject entryRulelogical_term() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_term = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:977:2: (iv_rulelogical_term= rulelogical_term EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:978:2: iv_rulelogical_term= rulelogical_term EOF
            {
             newCompositeNode(grammarAccess.getLogical_termRule()); 
            pushFollow(FOLLOW_rulelogical_term_in_entryRulelogical_term2009);
            iv_rulelogical_term=rulelogical_term();

            state._fsp--;

             current =iv_rulelogical_term; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_term2019); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulelogical_term"


    // $ANTLR start "rulelogical_term"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:985:1: rulelogical_term returns [EObject current=null] : (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) ;
    public final EObject rulelogical_term() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_logical_factor_0 = null;

        EObject lv_Logical_factor_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:988:28: ( (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:989:1: (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:989:1: (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:990:5: this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term2066);
            this_logical_factor_0=rulelogical_factor();

            state._fsp--;

             
                    current = this_logical_factor_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:998:1: (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==31) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:998:3: otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    {
            	    otherlv_1=(Token)match(input,31,FOLLOW_31_in_rulelogical_term2078); 

            	        	newLeafNode(otherlv_1, grammarAccess.getLogical_termAccess().getAndKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1002:1: ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1003:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1003:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1004:3: lv_Logical_factor_2_0= rulelogical_factor
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorLogical_factorParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term2099);
            	    lv_Logical_factor_2_0=rulelogical_factor();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getLogical_termRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Logical_factor",
            	            		lv_Logical_factor_2_0, 
            	            		"logical_factor");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulelogical_term"


    // $ANTLR start "entryRulelogical_factor"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1028:1: entryRulelogical_factor returns [EObject current=null] : iv_rulelogical_factor= rulelogical_factor EOF ;
    public final EObject entryRulelogical_factor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_factor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1029:2: (iv_rulelogical_factor= rulelogical_factor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1030:2: iv_rulelogical_factor= rulelogical_factor EOF
            {
             newCompositeNode(grammarAccess.getLogical_factorRule()); 
            pushFollow(FOLLOW_rulelogical_factor_in_entryRulelogical_factor2137);
            iv_rulelogical_factor=rulelogical_factor();

            state._fsp--;

             current =iv_rulelogical_factor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_factor2147); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulelogical_factor"


    // $ANTLR start "rulelogical_factor"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1037:1: rulelogical_factor returns [EObject current=null] : ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) ;
    public final EObject rulelogical_factor() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_Relation_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1040:28: ( ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1041:1: ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1041:1: ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1041:2: (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1041:2: (otherlv_0= 'not' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==32) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1041:4: otherlv_0= 'not'
                    {
                    otherlv_0=(Token)match(input,32,FOLLOW_32_in_rulelogical_factor2185); 

                        	newLeafNode(otherlv_0, grammarAccess.getLogical_factorAccess().getNotKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1045:3: ( (lv_Relation_1_0= rulerelation ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1046:1: (lv_Relation_1_0= rulerelation )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1046:1: (lv_Relation_1_0= rulerelation )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1047:3: lv_Relation_1_0= rulerelation
            {
             
            	        newCompositeNode(grammarAccess.getLogical_factorAccess().getRelationRelationParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulerelation_in_rulelogical_factor2208);
            lv_Relation_1_0=rulerelation();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLogical_factorRule());
            	        }
                   		set(
                   			current, 
                   			"Relation",
                    		lv_Relation_1_0, 
                    		"relation");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulelogical_factor"


    // $ANTLR start "entryRulerelation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1071:1: entryRulerelation returns [EObject current=null] : iv_rulerelation= rulerelation EOF ;
    public final EObject entryRulerelation() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1072:2: (iv_rulerelation= rulerelation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1073:2: iv_rulerelation= rulerelation EOF
            {
             newCompositeNode(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_rulerelation_in_entryRulerelation2244);
            iv_rulerelation=rulerelation();

            state._fsp--;

             current =iv_rulerelation; 
            match(input,EOF,FOLLOW_EOF_in_entryRulerelation2254); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerelation"


    // $ANTLR start "rulerelation"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1080:1: rulerelation returns [EObject current=null] : (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) ;
    public final EObject rulerelation() throws RecognitionException {
        EObject current = null;

        EObject this_arithmetic_expression_0 = null;

        AntlrDatatypeRuleToken lv_op_1_1 = null;

        AntlrDatatypeRuleToken lv_op_1_2 = null;

        AntlrDatatypeRuleToken lv_op_1_3 = null;

        AntlrDatatypeRuleToken lv_op_1_4 = null;

        AntlrDatatypeRuleToken lv_op_1_5 = null;

        AntlrDatatypeRuleToken lv_op_1_6 = null;

        EObject lv_Arithmetic_expression_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1083:28: ( (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1084:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1084:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1085:5: this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation2301);
            this_arithmetic_expression_0=rulearithmetic_expression();

            state._fsp--;

             
                    current = this_arithmetic_expression_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1093:1: ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=51 && LA20_0<=56)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1093:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1093:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1094:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1094:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1095:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1095:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    int alt19=6;
                    switch ( input.LA(1) ) {
                    case 51:
                        {
                        alt19=1;
                        }
                        break;
                    case 52:
                        {
                        alt19=2;
                        }
                        break;
                    case 53:
                        {
                        alt19=3;
                        }
                        break;
                    case 54:
                        {
                        alt19=4;
                        }
                        break;
                    case 55:
                        {
                        alt19=5;
                        }
                        break;
                    case 56:
                        {
                        alt19=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }

                    switch (alt19) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1096:3: lv_op_1_1= rulerel_op_Less_then
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_thenParserRuleCall_1_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_then_in_rulerelation2324);
                            lv_op_1_1=rulerel_op_Less_then();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getRelationRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"op",
                                    		lv_op_1_1, 
                                    		"rel_op_Less_then");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1111:8: lv_op_1_2= rulerel_op_Less_equal
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_equalParserRuleCall_1_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_equal_in_rulerelation2343);
                            lv_op_1_2=rulerel_op_Less_equal();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getRelationRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"op",
                                    		lv_op_1_2, 
                                    		"rel_op_Less_equal");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1126:8: lv_op_1_3= rulerel_op_greater_then
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_thenParserRuleCall_1_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_then_in_rulerelation2362);
                            lv_op_1_3=rulerel_op_greater_then();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getRelationRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"op",
                                    		lv_op_1_3, 
                                    		"rel_op_greater_then");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 4 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1141:8: lv_op_1_4= rulerel_op_greater_equal
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_equalParserRuleCall_1_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_equal_in_rulerelation2381);
                            lv_op_1_4=rulerel_op_greater_equal();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getRelationRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"op",
                                    		lv_op_1_4, 
                                    		"rel_op_greater_equal");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 5 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1156:8: lv_op_1_5= rulerel_op_assignment
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_assignmentParserRuleCall_1_0_0_4()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_assignment_in_rulerelation2400);
                            lv_op_1_5=rulerel_op_assignment();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getRelationRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"op",
                                    		lv_op_1_5, 
                                    		"rel_op_assignment");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 6 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1171:8: lv_op_1_6= rulerel_op_Oper
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_OperParserRuleCall_1_0_0_5()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Oper_in_rulerelation2419);
                            lv_op_1_6=rulerel_op_Oper();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getRelationRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"op",
                                    		lv_op_1_6, 
                                    		"rel_op_Oper");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1189:2: ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1190:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1190:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1191:3: lv_Arithmetic_expression_2_0= rulearithmetic_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation2443);
                    lv_Arithmetic_expression_2_0=rulearithmetic_expression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getRelationRule());
                    	        }
                           		set(
                           			current, 
                           			"Arithmetic_expression",
                            		lv_Arithmetic_expression_2_0, 
                            		"arithmetic_expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerelation"


    // $ANTLR start "entryRulearithmetic_expression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1215:1: entryRulearithmetic_expression returns [EObject current=null] : iv_rulearithmetic_expression= rulearithmetic_expression EOF ;
    public final EObject entryRulearithmetic_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearithmetic_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1216:2: (iv_rulearithmetic_expression= rulearithmetic_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1217:2: iv_rulearithmetic_expression= rulearithmetic_expression EOF
            {
             newCompositeNode(grammarAccess.getArithmetic_expressionRule()); 
            pushFollow(FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression2481);
            iv_rulearithmetic_expression=rulearithmetic_expression();

            state._fsp--;

             current =iv_rulearithmetic_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearithmetic_expression2491); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulearithmetic_expression"


    // $ANTLR start "rulearithmetic_expression"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1224:1: rulearithmetic_expression returns [EObject current=null] : ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) ;
    public final EObject rulearithmetic_expression() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_opr_0_1 = null;

        AntlrDatatypeRuleToken lv_opr_0_2 = null;

        AntlrDatatypeRuleToken lv_opr_0_3 = null;

        AntlrDatatypeRuleToken lv_opr_0_4 = null;

        EObject lv_Term_1_0 = null;

        AntlrDatatypeRuleToken lv_Oper1_2_1 = null;

        AntlrDatatypeRuleToken lv_Oper1_2_2 = null;

        AntlrDatatypeRuleToken lv_Oper1_2_3 = null;

        AntlrDatatypeRuleToken lv_Oper1_2_4 = null;

        EObject lv_Term1_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1227:28: ( ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1228:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1228:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1228:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1228:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=47 && LA22_0<=50)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1229:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1229:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1230:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1230:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    int alt21=4;
                    switch ( input.LA(1) ) {
                    case 47:
                        {
                        alt21=1;
                        }
                        break;
                    case 48:
                        {
                        alt21=2;
                        }
                        break;
                    case 49:
                        {
                        alt21=3;
                        }
                        break;
                    case 50:
                        {
                        alt21=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 0, input);

                        throw nvae;
                    }

                    switch (alt21) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1231:3: lv_opr_0_1= ruleadd_op_plus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_plusParserRuleCall_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression2539);
                            lv_opr_0_1=ruleadd_op_plus();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"opr",
                                    		lv_opr_0_1, 
                                    		"add_op_plus");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1246:8: lv_opr_0_2= ruleadd_op_minus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_minusParserRuleCall_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression2558);
                            lv_opr_0_2=ruleadd_op_minus();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"opr",
                                    		lv_opr_0_2, 
                                    		"add_op_minus");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1261:8: lv_opr_0_3= ruleadd_op_dotplus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotplusParserRuleCall_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression2577);
                            lv_opr_0_3=ruleadd_op_dotplus();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"opr",
                                    		lv_opr_0_3, 
                                    		"add_op_dotplus");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 4 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1276:8: lv_opr_0_4= ruleadd_op_dotminus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotminusParserRuleCall_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression2596);
                            lv_opr_0_4=ruleadd_op_dotminus();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"opr",
                                    		lv_opr_0_4, 
                                    		"add_op_dotminus");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1294:3: ( (lv_Term_1_0= ruleterm ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1295:1: (lv_Term_1_0= ruleterm )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1295:1: (lv_Term_1_0= ruleterm )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1296:3: lv_Term_1_0= ruleterm
            {
             
            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTermTermParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression2621);
            lv_Term_1_0=ruleterm();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
            	        }
                   		set(
                   			current, 
                   			"Term",
                    		lv_Term_1_0, 
                    		"term");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1312:2: ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=47 && LA24_0<=50)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1312:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1312:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1313:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1313:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1314:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1314:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    int alt23=4;
            	    switch ( input.LA(1) ) {
            	    case 47:
            	        {
            	        alt23=1;
            	        }
            	        break;
            	    case 48:
            	        {
            	        alt23=2;
            	        }
            	        break;
            	    case 49:
            	        {
            	        alt23=3;
            	        }
            	        break;
            	    case 50:
            	        {
            	        alt23=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 23, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt23) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1315:3: lv_Oper1_2_1= ruleadd_op_plus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_plusParserRuleCall_2_0_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression2645);
            	            lv_Oper1_2_1=ruleadd_op_plus();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"Oper1",
            	                    		lv_Oper1_2_1, 
            	                    		"add_op_plus");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1330:8: lv_Oper1_2_2= ruleadd_op_minus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_minusParserRuleCall_2_0_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression2664);
            	            lv_Oper1_2_2=ruleadd_op_minus();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"Oper1",
            	                    		lv_Oper1_2_2, 
            	                    		"add_op_minus");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 3 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1345:8: lv_Oper1_2_3= ruleadd_op_dotplus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotplusParserRuleCall_2_0_0_2()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression2683);
            	            lv_Oper1_2_3=ruleadd_op_dotplus();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"Oper1",
            	                    		lv_Oper1_2_3, 
            	                    		"add_op_dotplus");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 4 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1360:8: lv_Oper1_2_4= ruleadd_op_dotminus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotminusParserRuleCall_2_0_0_3()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression2702);
            	            lv_Oper1_2_4=ruleadd_op_dotminus();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"Oper1",
            	                    		lv_Oper1_2_4, 
            	                    		"add_op_dotminus");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1378:2: ( (lv_Term1_3_0= ruleterm ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1379:1: (lv_Term1_3_0= ruleterm )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1379:1: (lv_Term1_3_0= ruleterm )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1380:3: lv_Term1_3_0= ruleterm
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTerm1TermParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression2726);
            	    lv_Term1_3_0=ruleterm();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getArithmetic_expressionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Term1",
            	            		lv_Term1_3_0, 
            	            		"term");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulearithmetic_expression"


    // $ANTLR start "entryRuleterm"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1404:1: entryRuleterm returns [EObject current=null] : iv_ruleterm= ruleterm EOF ;
    public final EObject entryRuleterm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleterm = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1405:2: (iv_ruleterm= ruleterm EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1406:2: iv_ruleterm= ruleterm EOF
            {
             newCompositeNode(grammarAccess.getTermRule()); 
            pushFollow(FOLLOW_ruleterm_in_entryRuleterm2764);
            iv_ruleterm=ruleterm();

            state._fsp--;

             current =iv_ruleterm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleterm2774); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleterm"


    // $ANTLR start "ruleterm"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1413:1: ruleterm returns [EObject current=null] : (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) ;
    public final EObject ruleterm() throws RecognitionException {
        EObject current = null;

        EObject this_factor_0 = null;

        AntlrDatatypeRuleToken lv_op_1_1 = null;

        AntlrDatatypeRuleToken lv_op_1_2 = null;

        AntlrDatatypeRuleToken lv_op_1_3 = null;

        AntlrDatatypeRuleToken lv_op_1_4 = null;

        EObject lv_Factor_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1416:28: ( (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1417:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1417:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1418:5: this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getTermAccess().getFactorParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulefactor_in_ruleterm2821);
            this_factor_0=rulefactor();

            state._fsp--;

             
                    current = this_factor_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1426:1: ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=43 && LA26_0<=46)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1426:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1426:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1427:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1427:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1428:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1428:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    int alt25=4;
            	    switch ( input.LA(1) ) {
            	    case 43:
            	        {
            	        alt25=1;
            	        }
            	        break;
            	    case 44:
            	        {
            	        alt25=2;
            	        }
            	        break;
            	    case 45:
            	        {
            	        alt25=3;
            	        }
            	        break;
            	    case 46:
            	        {
            	        alt25=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 25, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt25) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1429:3: lv_op_1_1= rulemul_op_mul
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_mulParserRuleCall_1_0_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_mul_in_ruleterm2844);
            	            lv_op_1_1=rulemul_op_mul();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getTermRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"op",
            	                    		lv_op_1_1, 
            	                    		"mul_op_mul");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1444:8: lv_op_1_2= rulemul_op_div
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_divParserRuleCall_1_0_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_div_in_ruleterm2863);
            	            lv_op_1_2=rulemul_op_div();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getTermRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"op",
            	                    		lv_op_1_2, 
            	                    		"mul_op_div");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 3 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1459:8: lv_op_1_3= rulemul_op_dotmul
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotmulParserRuleCall_1_0_0_2()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotmul_in_ruleterm2882);
            	            lv_op_1_3=rulemul_op_dotmul();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getTermRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"op",
            	                    		lv_op_1_3, 
            	                    		"mul_op_dotmul");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;
            	        case 4 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1474:8: lv_op_1_4= rulemul_op_dotdiv
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotdivParserRuleCall_1_0_0_3()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotdiv_in_ruleterm2901);
            	            lv_op_1_4=rulemul_op_dotdiv();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getTermRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"op",
            	                    		lv_op_1_4, 
            	                    		"mul_op_dotdiv");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1492:2: ( (lv_Factor_2_0= rulefactor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1493:1: (lv_Factor_2_0= rulefactor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1493:1: (lv_Factor_2_0= rulefactor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1494:3: lv_Factor_2_0= rulefactor
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTermAccess().getFactorFactorParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulefactor_in_ruleterm2925);
            	    lv_Factor_2_0=rulefactor();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTermRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Factor",
            	            		lv_Factor_2_0, 
            	            		"factor");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleterm"


    // $ANTLR start "entryRulefactor"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1518:1: entryRulefactor returns [EObject current=null] : iv_rulefactor= rulefactor EOF ;
    public final EObject entryRulefactor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefactor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1519:2: (iv_rulefactor= rulefactor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1520:2: iv_rulefactor= rulefactor EOF
            {
             newCompositeNode(grammarAccess.getFactorRule()); 
            pushFollow(FOLLOW_rulefactor_in_entryRulefactor2963);
            iv_rulefactor=rulefactor();

            state._fsp--;

             current =iv_rulefactor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefactor2973); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulefactor"


    // $ANTLR start "rulefactor"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1527:1: rulefactor returns [EObject current=null] : (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) ;
    public final EObject rulefactor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject this_primary_0 = null;

        EObject lv_Primary_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1530:28: ( (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1531:1: (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1531:1: (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1532:5: this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getFactorAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleprimary_in_rulefactor3020);
            this_primary_0=ruleprimary();

            state._fsp--;

             
                    current = this_primary_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1540:1: ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=33 && LA28_0<=34)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1540:2: (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1540:2: (otherlv_1= '^' | otherlv_2= '.^' )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==33) ) {
                        alt27=1;
                    }
                    else if ( (LA27_0==34) ) {
                        alt27=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }
                    switch (alt27) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1540:4: otherlv_1= '^'
                            {
                            otherlv_1=(Token)match(input,33,FOLLOW_33_in_rulefactor3033); 

                                	newLeafNode(otherlv_1, grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1545:7: otherlv_2= '.^'
                            {
                            otherlv_2=(Token)match(input,34,FOLLOW_34_in_rulefactor3051); 

                                	newLeafNode(otherlv_2, grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1549:2: ( (lv_Primary_3_0= ruleprimary ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1550:1: (lv_Primary_3_0= ruleprimary )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1550:1: (lv_Primary_3_0= ruleprimary )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1551:3: lv_Primary_3_0= ruleprimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getFactorAccess().getPrimaryPrimaryParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleprimary_in_rulefactor3073);
                    lv_Primary_3_0=ruleprimary();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFactorRule());
                    	        }
                           		set(
                           			current, 
                           			"Primary",
                            		lv_Primary_3_0, 
                            		"primary");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulefactor"


    // $ANTLR start "entryRuleprimary"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1575:1: entryRuleprimary returns [EObject current=null] : iv_ruleprimary= ruleprimary EOF ;
    public final EObject entryRuleprimary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleprimary = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1576:2: (iv_ruleprimary= ruleprimary EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1577:2: iv_ruleprimary= ruleprimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_ruleprimary_in_entryRuleprimary3111);
            iv_ruleprimary=ruleprimary();

            state._fsp--;

             current =iv_ruleprimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleprimary3121); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleprimary"


    // $ANTLR start "ruleprimary"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1584:1: ruleprimary returns [EObject current=null] : ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) ) ;
    public final EObject ruleprimary() throws RecognitionException {
        EObject current = null;

        Token lv_num_0_0=null;
        Token lv_int_1_0=null;
        Token lv_str_2_0=null;
        Token lv_Bool_3_0=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token lv_End_19_0=null;
        EObject this_name_Function_4 = null;

        EObject this_initial_ref_5 = null;

        EObject this_ExprDer_6 = null;

        EObject lv_Component_reference_7_0 = null;

        EObject lv_output_expr_list_9_0 = null;

        EObject lv_Expre_list_12_0 = null;

        EObject lv_Expression_list_14_0 = null;

        EObject lv_f_arguments_17_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1587:28: ( ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1588:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1588:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )
            int alt30=12;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1588:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1588:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1589:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1589:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1590:3: lv_num_0_0= RULE_UNSIGNED_NUMBER
                    {
                    lv_num_0_0=(Token)match(input,RULE_UNSIGNED_NUMBER,FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary3163); 

                    			newLeafNode(lv_num_0_0, grammarAccess.getPrimaryAccess().getNumUNSIGNED_NUMBERTerminalRuleCall_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getPrimaryRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"num",
                            		lv_num_0_0, 
                            		"UNSIGNED_NUMBER");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1607:6: ( (lv_int_1_0= RULE_INT ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1607:6: ( (lv_int_1_0= RULE_INT ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1608:1: (lv_int_1_0= RULE_INT )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1608:1: (lv_int_1_0= RULE_INT )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1609:3: lv_int_1_0= RULE_INT
                    {
                    lv_int_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleprimary3191); 

                    			newLeafNode(lv_int_1_0, grammarAccess.getPrimaryAccess().getIntINTTerminalRuleCall_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getPrimaryRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"int",
                            		lv_int_1_0, 
                            		"INT");
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1626:6: ( (lv_str_2_0= RULE_STRING ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1626:6: ( (lv_str_2_0= RULE_STRING ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1627:1: (lv_str_2_0= RULE_STRING )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1627:1: (lv_str_2_0= RULE_STRING )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1628:3: lv_str_2_0= RULE_STRING
                    {
                    lv_str_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleprimary3219); 

                    			newLeafNode(lv_str_2_0, grammarAccess.getPrimaryAccess().getStrSTRINGTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getPrimaryRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"str",
                            		lv_str_2_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1645:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1645:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1646:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1646:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1647:3: lv_Bool_3_0= RULE_BOOL_VAL
                    {
                    lv_Bool_3_0=(Token)match(input,RULE_BOOL_VAL,FOLLOW_RULE_BOOL_VAL_in_ruleprimary3247); 

                    			newLeafNode(lv_Bool_3_0, grammarAccess.getPrimaryAccess().getBoolBOOL_VALTerminalRuleCall_3_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getPrimaryRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"Bool",
                            		lv_Bool_3_0, 
                            		"BOOL_VAL");
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1665:5: this_name_Function_4= rulename_Function
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getName_FunctionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_rulename_Function_in_ruleprimary3280);
                    this_name_Function_4=rulename_Function();

                    state._fsp--;

                     
                            current = this_name_Function_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1675:5: this_initial_ref_5= ruleinitial_ref
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getInitial_refParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleinitial_ref_in_ruleprimary3307);
                    this_initial_ref_5=ruleinitial_ref();

                    state._fsp--;

                     
                            current = this_initial_ref_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1685:5: this_ExprDer_6= ruleExprDer
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExprDerParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleExprDer_in_ruleprimary3334);
                    this_ExprDer_6=ruleExprDer();

                    state._fsp--;

                     
                            current = this_ExprDer_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1694:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1694:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1695:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1695:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1696:3: lv_Component_reference_7_0= rulecomponent_reference
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getComponent_referenceComponent_referenceParserRuleCall_7_0()); 
                    	    
                    pushFollow(FOLLOW_rulecomponent_reference_in_ruleprimary3360);
                    lv_Component_reference_7_0=rulecomponent_reference();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPrimaryRule());
                    	        }
                           		set(
                           			current, 
                           			"Component_reference",
                            		lv_Component_reference_7_0, 
                            		"component_reference");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 9 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1713:6: (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1713:6: (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1713:8: otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')'
                    {
                    otherlv_8=(Token)match(input,26,FOLLOW_26_in_ruleprimary3379); 

                        	newLeafNode(otherlv_8, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_8_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1717:1: ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1718:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1718:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1719:3: lv_output_expr_list_9_0= ruleoutput_expression_list
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getOutput_expr_listOutput_expression_listParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleoutput_expression_list_in_ruleprimary3400);
                    lv_output_expr_list_9_0=ruleoutput_expression_list();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPrimaryRule());
                    	        }
                           		set(
                           			current, 
                           			"output_expr_list",
                            		lv_output_expr_list_9_0, 
                            		"output_expression_list");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_10=(Token)match(input,28,FOLLOW_28_in_ruleprimary3412); 

                        	newLeafNode(otherlv_10, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_8_2());
                        

                    }


                    }
                    break;
                case 10 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1740:6: (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1740:6: (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1740:8: otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']'
                    {
                    otherlv_11=(Token)match(input,35,FOLLOW_35_in_ruleprimary3432); 

                        	newLeafNode(otherlv_11, grammarAccess.getPrimaryAccess().getLeftSquareBracketKeyword_9_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1744:1: ( (lv_Expre_list_12_0= ruleexpression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1745:1: (lv_Expre_list_12_0= ruleexpression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1745:1: (lv_Expre_list_12_0= ruleexpression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1746:3: lv_Expre_list_12_0= ruleexpression_list
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpre_listExpression_listParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary3453);
                    lv_Expre_list_12_0=ruleexpression_list();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPrimaryRule());
                    	        }
                           		set(
                           			current, 
                           			"Expre_list",
                            		lv_Expre_list_12_0, 
                            		"expression_list");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1762:2: (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==14) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1762:4: otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    {
                    	    otherlv_13=(Token)match(input,14,FOLLOW_14_in_ruleprimary3466); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getPrimaryAccess().getSemicolonKeyword_9_2_0());
                    	        
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1766:1: ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1767:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    {
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1767:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1768:3: lv_Expression_list_14_0= ruleexpression_list
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listExpression_listParserRuleCall_9_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary3487);
                    	    lv_Expression_list_14_0=ruleexpression_list();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getPrimaryRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"Expression_list",
                    	            		lv_Expression_list_14_0, 
                    	            		"expression_list");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,36,FOLLOW_36_in_ruleprimary3501); 

                        	newLeafNode(otherlv_15, grammarAccess.getPrimaryAccess().getRightSquareBracketKeyword_9_3());
                        

                    }


                    }
                    break;
                case 11 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1789:6: (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1789:6: (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1789:8: otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}'
                    {
                    otherlv_16=(Token)match(input,37,FOLLOW_37_in_ruleprimary3521); 

                        	newLeafNode(otherlv_16, grammarAccess.getPrimaryAccess().getLeftCurlyBracketKeyword_10_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1793:1: ( (lv_f_arguments_17_0= rulefunction_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1794:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1794:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1795:3: lv_f_arguments_17_0= rulefunction_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getF_argumentsFunction_argumentsParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_ruleprimary3542);
                    lv_f_arguments_17_0=rulefunction_arguments();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPrimaryRule());
                    	        }
                           		set(
                           			current, 
                           			"f_arguments",
                            		lv_f_arguments_17_0, 
                            		"function_arguments");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_18=(Token)match(input,38,FOLLOW_38_in_ruleprimary3554); 

                        	newLeafNode(otherlv_18, grammarAccess.getPrimaryAccess().getRightCurlyBracketKeyword_10_2());
                        

                    }


                    }
                    break;
                case 12 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1816:6: ( (lv_End_19_0= 'end' ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1816:6: ( (lv_End_19_0= 'end' ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1817:1: (lv_End_19_0= 'end' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1817:1: (lv_End_19_0= 'end' )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1818:3: lv_End_19_0= 'end'
                    {
                    lv_End_19_0=(Token)match(input,19,FOLLOW_19_in_ruleprimary3579); 

                            newLeafNode(lv_End_19_0, grammarAccess.getPrimaryAccess().getEndEndKeyword_11_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getPrimaryRule());
                    	        }
                           		setWithLastConsumed(current, "End", lv_End_19_0, "end");
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleprimary"


    // $ANTLR start "entryRulename_Function"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1839:1: entryRulename_Function returns [EObject current=null] : iv_rulename_Function= rulename_Function EOF ;
    public final EObject entryRulename_Function() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename_Function = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1840:2: (iv_rulename_Function= rulename_Function EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1841:2: iv_rulename_Function= rulename_Function EOF
            {
             newCompositeNode(grammarAccess.getName_FunctionRule()); 
            pushFollow(FOLLOW_rulename_Function_in_entryRulename_Function3628);
            iv_rulename_Function=rulename_Function();

            state._fsp--;

             current =iv_rulename_Function; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_Function3638); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulename_Function"


    // $ANTLR start "rulename_Function"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1848:1: rulename_Function returns [EObject current=null] : (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) ;
    public final EObject rulename_Function() throws RecognitionException {
        EObject current = null;

        EObject this_name_0 = null;

        EObject lv_Function_call_args_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1851:28: ( (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1852:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1852:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1853:5: this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            {
             
                    newCompositeNode(grammarAccess.getName_FunctionAccess().getNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulename_in_rulename_Function3685);
            this_name_0=rulename();

            state._fsp--;

             
                    current = this_name_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1861:1: ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1862:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1862:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1863:3: lv_Function_call_args_1_0= rulefunction_call_args
            {
             
            	        newCompositeNode(grammarAccess.getName_FunctionAccess().getFunction_call_argsFunction_call_argsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_rulename_Function3705);
            lv_Function_call_args_1_0=rulefunction_call_args();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getName_FunctionRule());
            	        }
                   		set(
                   			current, 
                   			"Function_call_args",
                    		lv_Function_call_args_1_0, 
                    		"function_call_args");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulename_Function"


    // $ANTLR start "entryRuleinitial_ref"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1887:1: entryRuleinitial_ref returns [EObject current=null] : iv_ruleinitial_ref= ruleinitial_ref EOF ;
    public final EObject entryRuleinitial_ref() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitial_ref = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1888:2: (iv_ruleinitial_ref= ruleinitial_ref EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1889:2: iv_ruleinitial_ref= ruleinitial_ref EOF
            {
             newCompositeNode(grammarAccess.getInitial_refRule()); 
            pushFollow(FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref3741);
            iv_ruleinitial_ref=ruleinitial_ref();

            state._fsp--;

             current =iv_ruleinitial_ref; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleinitial_ref3751); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleinitial_ref"


    // $ANTLR start "ruleinitial_ref"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1896:1: ruleinitial_ref returns [EObject current=null] : (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args ) ;
    public final EObject ruleinitial_ref() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject this_function_call_args_1 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1899:28: ( (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1900:1: (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1900:1: (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1900:3: otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args
            {
            otherlv_0=(Token)match(input,39,FOLLOW_39_in_ruleinitial_ref3788); 

                	newLeafNode(otherlv_0, grammarAccess.getInitial_refAccess().getInitialKeyword_0());
                
             
                    newCompositeNode(grammarAccess.getInitial_refAccess().getFunction_call_argsParserRuleCall_1()); 
                
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleinitial_ref3810);
            this_function_call_args_1=rulefunction_call_args();

            state._fsp--;

             
                    current = this_function_call_args_1; 
                    afterParserOrEnumRuleCall();
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleinitial_ref"


    // $ANTLR start "entryRuleExprDer"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1921:1: entryRuleExprDer returns [EObject current=null] : iv_ruleExprDer= ruleExprDer EOF ;
    public final EObject entryRuleExprDer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExprDer = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1922:2: (iv_ruleExprDer= ruleExprDer EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1923:2: iv_ruleExprDer= ruleExprDer EOF
            {
             newCompositeNode(grammarAccess.getExprDerRule()); 
            pushFollow(FOLLOW_ruleExprDer_in_entryRuleExprDer3845);
            iv_ruleExprDer=ruleExprDer();

            state._fsp--;

             current =iv_ruleExprDer; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExprDer3855); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExprDer"


    // $ANTLR start "ruleExprDer"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1930:1: ruleExprDer returns [EObject current=null] : (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) ;
    public final EObject ruleExprDer() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_functionArgs_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1933:28: ( (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1934:1: (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1934:1: (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1934:3: otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            {
            otherlv_0=(Token)match(input,40,FOLLOW_40_in_ruleExprDer3892); 

                	newLeafNode(otherlv_0, grammarAccess.getExprDerAccess().getDerKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1938:1: ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1939:1: (lv_functionArgs_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1939:1: (lv_functionArgs_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1940:3: lv_functionArgs_1_0= rulefunction_call_args
            {
             
            	        newCompositeNode(grammarAccess.getExprDerAccess().getFunctionArgsFunction_call_argsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleExprDer3913);
            lv_functionArgs_1_0=rulefunction_call_args();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExprDerRule());
            	        }
                   		set(
                   			current, 
                   			"functionArgs",
                    		lv_functionArgs_1_0, 
                    		"function_call_args");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExprDer"


    // $ANTLR start "entryRulefunction_call_args"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1964:1: entryRulefunction_call_args returns [EObject current=null] : iv_rulefunction_call_args= rulefunction_call_args EOF ;
    public final EObject entryRulefunction_call_args() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_call_args = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1965:2: (iv_rulefunction_call_args= rulefunction_call_args EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1966:2: iv_rulefunction_call_args= rulefunction_call_args EOF
            {
             newCompositeNode(grammarAccess.getFunction_call_argsRule()); 
            pushFollow(FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args3949);
            iv_rulefunction_call_args=rulefunction_call_args();

            state._fsp--;

             current =iv_rulefunction_call_args; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_call_args3959); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulefunction_call_args"


    // $ANTLR start "rulefunction_call_args"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1973:1: rulefunction_call_args returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' ) ;
    public final EObject rulefunction_call_args() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_f_arg_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1976:28: ( ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1977:1: ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1977:1: ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1977:2: () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')'
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1977:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1978:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_rulefunction_call_args4005); 

                	newLeafNode(otherlv_1, grammarAccess.getFunction_call_argsAccess().getLeftParenthesisKeyword_1());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1987:1: ( (lv_f_arg_2_0= rulefunction_arguments ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_UNSIGNED_NUMBER && LA31_0<=RULE_IDENT)||(LA31_0>=19 && LA31_0<=20)||LA31_0==26||LA31_0==32||LA31_0==35||LA31_0==37||(LA31_0>=39 && LA31_0<=41)||(LA31_0>=47 && LA31_0<=50)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1988:1: (lv_f_arg_2_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1988:1: (lv_f_arg_2_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:1989:3: lv_f_arg_2_0= rulefunction_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_call_argsAccess().getF_argFunction_argumentsParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_rulefunction_call_args4026);
                    lv_f_arg_2_0=rulefunction_arguments();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFunction_call_argsRule());
                    	        }
                           		set(
                           			current, 
                           			"f_arg",
                            		lv_f_arg_2_0, 
                            		"function_arguments");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,28,FOLLOW_28_in_rulefunction_call_args4039); 

                	newLeafNode(otherlv_3, grammarAccess.getFunction_call_argsAccess().getRightParenthesisKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulefunction_call_args"


    // $ANTLR start "entryRuleexpression_list"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2017:1: entryRuleexpression_list returns [EObject current=null] : iv_ruleexpression_list= ruleexpression_list EOF ;
    public final EObject entryRuleexpression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2018:2: (iv_ruleexpression_list= ruleexpression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2019:2: iv_ruleexpression_list= ruleexpression_list EOF
            {
             newCompositeNode(grammarAccess.getExpression_listRule()); 
            pushFollow(FOLLOW_ruleexpression_list_in_entryRuleexpression_list4075);
            iv_ruleexpression_list=ruleexpression_list();

            state._fsp--;

             current =iv_ruleexpression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression_list4085); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleexpression_list"


    // $ANTLR start "ruleexpression_list"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2026:1: ruleexpression_list returns [EObject current=null] : ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) ;
    public final EObject ruleexpression_list() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expr_0_0 = null;

        EObject lv_Expre_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2029:28: ( ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2030:1: ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2030:1: ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2030:2: ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2030:2: ( (lv_expr_0_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2031:1: (lv_expr_0_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2031:1: (lv_expr_0_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2032:3: lv_expr_0_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getExpression_listAccess().getExprExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list4131);
            lv_expr_0_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExpression_listRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_0_0, 
                    		"expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2048:2: (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==27) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2048:4: otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) )
            	    {
            	    otherlv_1=(Token)match(input,27,FOLLOW_27_in_ruleexpression_list4144); 

            	        	newLeafNode(otherlv_1, grammarAccess.getExpression_listAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2052:1: ( (lv_Expre_2_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2053:1: (lv_Expre_2_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2053:1: (lv_Expre_2_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2054:3: lv_Expre_2_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression_listAccess().getExpreExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list4165);
            	    lv_Expre_2_0=ruleexpression();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getExpression_listRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Expre",
            	            		lv_Expre_2_0, 
            	            		"expression");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleexpression_list"


    // $ANTLR start "entryRulename"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2078:1: entryRulename returns [EObject current=null] : iv_rulename= rulename EOF ;
    public final EObject entryRulename() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2079:2: (iv_rulename= rulename EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2080:2: iv_rulename= rulename EOF
            {
             newCompositeNode(grammarAccess.getNameRule()); 
            pushFollow(FOLLOW_rulename_in_entryRulename4203);
            iv_rulename=rulename();

            state._fsp--;

             current =iv_rulename; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename4213); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulename"


    // $ANTLR start "rulename"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2087:1: rulename returns [EObject current=null] : ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) ;
    public final EObject rulename() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_ID_1_0=null;
        Token otherlv_2=null;
        Token lv_nam_ID_3_0=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2090:28: ( ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2091:1: ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2091:1: ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2091:2: (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2091:2: (otherlv_0= '.' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==41) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2091:4: otherlv_0= '.'
                    {
                    otherlv_0=(Token)match(input,41,FOLLOW_41_in_rulename4251); 

                        	newLeafNode(otherlv_0, grammarAccess.getNameAccess().getFullStopKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2095:3: ( (lv_name_ID_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2096:1: (lv_name_ID_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2096:1: (lv_name_ID_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2097:3: lv_name_ID_1_0= RULE_IDENT
            {
            lv_name_ID_1_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename4270); 

            			newLeafNode(lv_name_ID_1_0, grammarAccess.getNameAccess().getName_IDIDENTTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNameRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name_ID",
                    		lv_name_ID_1_0, 
                    		"IDENT");
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2113:2: (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==41) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2113:4: otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    {
            	    otherlv_2=(Token)match(input,41,FOLLOW_41_in_rulename4288); 

            	        	newLeafNode(otherlv_2, grammarAccess.getNameAccess().getFullStopKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2117:1: ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2118:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2118:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2119:3: lv_nam_ID_3_0= RULE_IDENT
            	    {
            	    lv_nam_ID_3_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename4305); 

            	    			newLeafNode(lv_nam_ID_3_0, grammarAccess.getNameAccess().getNam_IDIDENTTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getNameRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"nam_ID",
            	            		lv_nam_ID_3_0, 
            	            		"IDENT");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulename"


    // $ANTLR start "entryRulecomponent_reference"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2143:1: entryRulecomponent_reference returns [EObject current=null] : iv_rulecomponent_reference= rulecomponent_reference EOF ;
    public final EObject entryRulecomponent_reference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_reference = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2144:2: (iv_rulecomponent_reference= rulecomponent_reference EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2145:2: iv_rulecomponent_reference= rulecomponent_reference EOF
            {
             newCompositeNode(grammarAccess.getComponent_referenceRule()); 
            pushFollow(FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference4348);
            iv_rulecomponent_reference=rulecomponent_reference();

            state._fsp--;

             current =iv_rulecomponent_reference; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_reference4358); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulecomponent_reference"


    // $ANTLR start "rulecomponent_reference"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2152:1: rulecomponent_reference returns [EObject current=null] : ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) ;
    public final EObject rulecomponent_reference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_ref_1_0=null;
        Token otherlv_3=null;
        Token lv_ref1_4_0=null;
        EObject lv_subscripts1_2_0 = null;

        EObject lv_subscripts_5_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2155:28: ( ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2156:1: ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2156:1: ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2156:2: (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2156:2: (otherlv_0= '.' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==41) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2156:4: otherlv_0= '.'
                    {
                    otherlv_0=(Token)match(input,41,FOLLOW_41_in_rulecomponent_reference4396); 

                        	newLeafNode(otherlv_0, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2160:3: ( (lv_ref_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2161:1: (lv_ref_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2161:1: (lv_ref_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2162:3: lv_ref_1_0= RULE_IDENT
            {
            lv_ref_1_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference4415); 

            			newLeafNode(lv_ref_1_0, grammarAccess.getComponent_referenceAccess().getRefIDENTTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getComponent_referenceRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"ref",
                    		lv_ref_1_0, 
                    		"IDENT");
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2178:2: ( (lv_subscripts1_2_0= rulearray_subscripts ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==35) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2179:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2179:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2180:3: lv_subscripts1_2_0= rulearray_subscripts
                    {
                     
                    	        newCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscripts1Array_subscriptsParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference4441);
                    lv_subscripts1_2_0=rulearray_subscripts();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getComponent_referenceRule());
                    	        }
                           		set(
                           			current, 
                           			"subscripts1",
                            		lv_subscripts1_2_0, 
                            		"array_subscripts");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2196:3: (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==41) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2196:5: otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    {
            	    otherlv_3=(Token)match(input,41,FOLLOW_41_in_rulecomponent_reference4455); 

            	        	newLeafNode(otherlv_3, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_3_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2200:1: ( (lv_ref1_4_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2201:1: (lv_ref1_4_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2201:1: (lv_ref1_4_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2202:3: lv_ref1_4_0= RULE_IDENT
            	    {
            	    lv_ref1_4_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference4472); 

            	    			newLeafNode(lv_ref1_4_0, grammarAccess.getComponent_referenceAccess().getRef1IDENTTerminalRuleCall_3_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getComponent_referenceRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"ref1",
            	            		lv_ref1_4_0, 
            	            		"IDENT");
            	    	    

            	    }


            	    }

            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2218:2: ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    int alt37=2;
            	    int LA37_0 = input.LA(1);

            	    if ( (LA37_0==35) ) {
            	        alt37=1;
            	    }
            	    switch (alt37) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2219:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2219:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2220:3: lv_subscripts_5_0= rulearray_subscripts
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscriptsArray_subscriptsParserRuleCall_3_2_0()); 
            	            	    
            	            pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference4498);
            	            lv_subscripts_5_0=rulearray_subscripts();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getComponent_referenceRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"subscripts",
            	                    		lv_subscripts_5_0, 
            	                    		"array_subscripts");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulecomponent_reference"


    // $ANTLR start "entryRuleoutput_expression_list"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2244:1: entryRuleoutput_expression_list returns [EObject current=null] : iv_ruleoutput_expression_list= ruleoutput_expression_list EOF ;
    public final EObject entryRuleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleoutput_expression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2245:2: (iv_ruleoutput_expression_list= ruleoutput_expression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2246:2: iv_ruleoutput_expression_list= ruleoutput_expression_list EOF
            {
             newCompositeNode(grammarAccess.getOutput_expression_listRule()); 
            pushFollow(FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list4537);
            iv_ruleoutput_expression_list=ruleoutput_expression_list();

            state._fsp--;

             current =iv_ruleoutput_expression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleoutput_expression_list4547); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleoutput_expression_list"


    // $ANTLR start "ruleoutput_expression_list"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2253:1: ruleoutput_expression_list returns [EObject current=null] : ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) ;
    public final EObject ruleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_epr_1_0 = null;

        EObject lv_Expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2256:28: ( ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2257:1: ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2257:1: ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2257:2: () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2257:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2258:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOutput_expression_listAccess().getOutput_expression_listAction_0(),
                        current);
                

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2263:2: ( (lv_epr_1_0= ruleexpression ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( ((LA39_0>=RULE_UNSIGNED_NUMBER && LA39_0<=RULE_IDENT)||(LA39_0>=19 && LA39_0<=20)||LA39_0==26||LA39_0==32||LA39_0==35||LA39_0==37||(LA39_0>=39 && LA39_0<=41)||(LA39_0>=47 && LA39_0<=50)) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2264:1: (lv_epr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2264:1: (lv_epr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2265:3: lv_epr_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getOutput_expression_listAccess().getEprExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list4602);
                    lv_epr_1_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOutput_expression_listRule());
                    	        }
                           		set(
                           			current, 
                           			"epr",
                            		lv_epr_1_0, 
                            		"expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2281:3: (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==27) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2281:5: otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )?
            	    {
            	    otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleoutput_expression_list4616); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2285:1: ( (lv_Expr_3_0= ruleexpression ) )?
            	    int alt40=2;
            	    int LA40_0 = input.LA(1);

            	    if ( ((LA40_0>=RULE_UNSIGNED_NUMBER && LA40_0<=RULE_IDENT)||(LA40_0>=19 && LA40_0<=20)||LA40_0==26||LA40_0==32||LA40_0==35||LA40_0==37||(LA40_0>=39 && LA40_0<=41)||(LA40_0>=47 && LA40_0<=50)) ) {
            	        alt40=1;
            	    }
            	    switch (alt40) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2286:1: (lv_Expr_3_0= ruleexpression )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2286:1: (lv_Expr_3_0= ruleexpression )
            	            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2287:3: lv_Expr_3_0= ruleexpression
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getOutput_expression_listAccess().getExprExpressionParserRuleCall_2_1_0()); 
            	            	    
            	            pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list4637);
            	            lv_Expr_3_0=ruleexpression();

            	            state._fsp--;


            	            	        if (current==null) {
            	            	            current = createModelElementForParent(grammarAccess.getOutput_expression_listRule());
            	            	        }
            	                   		add(
            	                   			current, 
            	                   			"Expr",
            	                    		lv_Expr_3_0, 
            	                    		"expression");
            	            	        afterParserOrEnumRuleCall();
            	            	    

            	            }


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleoutput_expression_list"


    // $ANTLR start "entryRulearray_subscripts"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2311:1: entryRulearray_subscripts returns [EObject current=null] : iv_rulearray_subscripts= rulearray_subscripts EOF ;
    public final EObject entryRulearray_subscripts() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearray_subscripts = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2312:2: (iv_rulearray_subscripts= rulearray_subscripts EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2313:2: iv_rulearray_subscripts= rulearray_subscripts EOF
            {
             newCompositeNode(grammarAccess.getArray_subscriptsRule()); 
            pushFollow(FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts4676);
            iv_rulearray_subscripts=rulearray_subscripts();

            state._fsp--;

             current =iv_rulearray_subscripts; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearray_subscripts4686); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulearray_subscripts"


    // $ANTLR start "rulearray_subscripts"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2320:1: rulearray_subscripts returns [EObject current=null] : (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' ) ;
    public final EObject rulearray_subscripts() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_Sub_1_0 = null;

        EObject lv_Subscript_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2323:28: ( (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2324:1: (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2324:1: (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2324:3: otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,35,FOLLOW_35_in_rulearray_subscripts4723); 

                	newLeafNode(otherlv_0, grammarAccess.getArray_subscriptsAccess().getLeftSquareBracketKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2328:1: ( (lv_Sub_1_0= rulesubscript ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2329:1: (lv_Sub_1_0= rulesubscript )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2329:1: (lv_Sub_1_0= rulesubscript )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2330:3: lv_Sub_1_0= rulesubscript
            {
             
            	        newCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubSubscriptParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts4744);
            lv_Sub_1_0=rulesubscript();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getArray_subscriptsRule());
            	        }
                   		set(
                   			current, 
                   			"Sub",
                    		lv_Sub_1_0, 
                    		"subscript");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2346:2: (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==27) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2346:4: otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) )
            	    {
            	    otherlv_2=(Token)match(input,27,FOLLOW_27_in_rulearray_subscripts4757); 

            	        	newLeafNode(otherlv_2, grammarAccess.getArray_subscriptsAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2350:1: ( (lv_Subscript_3_0= rulesubscript ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2351:1: (lv_Subscript_3_0= rulesubscript )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2351:1: (lv_Subscript_3_0= rulesubscript )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2352:3: lv_Subscript_3_0= rulesubscript
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubscriptSubscriptParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts4778);
            	    lv_Subscript_3_0=rulesubscript();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getArray_subscriptsRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"Subscript",
            	            		lv_Subscript_3_0, 
            	            		"subscript");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

            otherlv_4=(Token)match(input,36,FOLLOW_36_in_rulearray_subscripts4792); 

                	newLeafNode(otherlv_4, grammarAccess.getArray_subscriptsAccess().getRightSquareBracketKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulearray_subscripts"


    // $ANTLR start "entryRulesubscript"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2380:1: entryRulesubscript returns [EObject current=null] : iv_rulesubscript= rulesubscript EOF ;
    public final EObject entryRulesubscript() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesubscript = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2381:2: (iv_rulesubscript= rulesubscript EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2382:2: iv_rulesubscript= rulesubscript EOF
            {
             newCompositeNode(grammarAccess.getSubscriptRule()); 
            pushFollow(FOLLOW_rulesubscript_in_entryRulesubscript4828);
            iv_rulesubscript=rulesubscript();

            state._fsp--;

             current =iv_rulesubscript; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesubscript4838); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulesubscript"


    // $ANTLR start "rulesubscript"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2389:1: rulesubscript returns [EObject current=null] : ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulesubscript() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2392:28: ( ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2393:1: ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2393:1: ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==29) ) {
                alt43=1;
            }
            else if ( ((LA43_0>=RULE_UNSIGNED_NUMBER && LA43_0<=RULE_IDENT)||(LA43_0>=19 && LA43_0<=20)||LA43_0==26||LA43_0==32||LA43_0==35||LA43_0==37||(LA43_0>=39 && LA43_0<=41)||(LA43_0>=47 && LA43_0<=50)) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2393:2: ( () otherlv_1= ':' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2393:2: ( () otherlv_1= ':' )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2393:3: () otherlv_1= ':'
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2393:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2394:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getSubscriptAccess().getSubscriptAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,29,FOLLOW_29_in_rulesubscript4885); 

                        	newLeafNode(otherlv_1, grammarAccess.getSubscriptAccess().getColonKeyword_0_1());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2404:6: ( (lv_expr_2_0= ruleexpression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2404:6: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2405:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2405:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2406:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSubscriptAccess().getExprExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulesubscript4913);
                    lv_expr_2_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSubscriptRule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_2_0, 
                            		"expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulesubscript"


    // $ANTLR start "entryRulefunction_arguments"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2430:1: entryRulefunction_arguments returns [EObject current=null] : iv_rulefunction_arguments= rulefunction_arguments EOF ;
    public final EObject entryRulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2431:2: (iv_rulefunction_arguments= rulefunction_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2432:2: iv_rulefunction_arguments= rulefunction_arguments EOF
            {
             newCompositeNode(grammarAccess.getFunction_argumentsRule()); 
            pushFollow(FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments4949);
            iv_rulefunction_arguments=rulefunction_arguments();

            state._fsp--;

             current =iv_rulefunction_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_arguments4959); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulefunction_arguments"


    // $ANTLR start "rulefunction_arguments"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2439:1: rulefunction_arguments returns [EObject current=null] : ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) ;
    public final EObject rulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject lv_ArgExp_1_0 = null;

        EObject lv_Fun_Arg_Expr_2_0 = null;

        EObject lv_Fun_Arg_For_3_0 = null;

        EObject lv_name_arg_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2442:28: ( ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2443:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2443:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( ((LA45_0>=RULE_UNSIGNED_NUMBER && LA45_0<=RULE_BOOL_VAL)||(LA45_0>=19 && LA45_0<=20)||LA45_0==26||LA45_0==32||LA45_0==35||LA45_0==37||(LA45_0>=39 && LA45_0<=41)||(LA45_0>=47 && LA45_0<=50)) ) {
                alt45=1;
            }
            else if ( (LA45_0==RULE_IDENT) ) {
                int LA45_2 = input.LA(2);

                if ( (LA45_2==15) ) {
                    alt45=2;
                }
                else if ( (LA45_2==EOF||LA45_2==23||(LA45_2>=26 && LA45_2<=31)||(LA45_2>=33 && LA45_2<=35)||LA45_2==38||LA45_2==41||(LA45_2>=43 && LA45_2<=56)) ) {
                    alt45=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2443:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2443:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2443:3: () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2443:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2444:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getFunction_argumentsAccess().getFunction_argumentsAction_0_0(),
                                current);
                        

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2449:2: ( (lv_ArgExp_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2450:1: (lv_ArgExp_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2450:1: (lv_ArgExp_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2451:3: lv_ArgExp_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getArgExpExpressionParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefunction_arguments5015);
                    lv_ArgExp_1_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFunction_argumentsRule());
                    	        }
                           		add(
                           			current, 
                           			"ArgExp",
                            		lv_ArgExp_1_0, 
                            		"expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2467:2: ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    int alt44=3;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==27) ) {
                        alt44=1;
                    }
                    else if ( (LA44_0==23) ) {
                        alt44=2;
                    }
                    switch (alt44) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2467:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2467:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2468:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2468:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2469:3: lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp
                            {
                             
                            	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments5037);
                            lv_Fun_Arg_Expr_2_0=ruleFun_Arguments_exp();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getFunction_argumentsRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"Fun_Arg_Expr",
                                    		lv_Fun_Arg_Expr_2_0, 
                                    		"Fun_Arguments_exp");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2486:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2486:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2487:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2487:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2488:3: lv_Fun_Arg_For_3_0= ruleFun_Arguments_for
                            {
                             
                            	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments5064);
                            lv_Fun_Arg_For_3_0=ruleFun_Arguments_for();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getFunction_argumentsRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"Fun_Arg_For",
                                    		lv_Fun_Arg_For_3_0, 
                                    		"Fun_Arguments_for");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2505:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2505:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2506:1: (lv_name_arg_4_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2506:1: (lv_name_arg_4_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2507:3: lv_name_arg_4_0= rulenamed_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getName_argNamed_argumentsParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulefunction_arguments5094);
                    lv_name_arg_4_0=rulenamed_arguments();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFunction_argumentsRule());
                    	        }
                           		set(
                           			current, 
                           			"name_arg",
                            		lv_name_arg_4_0, 
                            		"named_arguments");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulefunction_arguments"


    // $ANTLR start "entryRuleFun_Arguments_exp"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2531:1: entryRuleFun_Arguments_exp returns [EObject current=null] : iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF ;
    public final EObject entryRuleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_exp = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2532:2: (iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2533:2: iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF
            {
             newCompositeNode(grammarAccess.getFun_Arguments_expRule()); 
            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp5130);
            iv_ruleFun_Arguments_exp=ruleFun_Arguments_exp();

            state._fsp--;

             current =iv_ruleFun_Arguments_exp; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_exp5140); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFun_Arguments_exp"


    // $ANTLR start "ruleFun_Arguments_exp"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2540:1: ruleFun_Arguments_exp returns [EObject current=null] : (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) ;
    public final EObject ruleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_Args_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2543:28: ( (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2544:1: (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2544:1: (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2544:3: otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) )
            {
            otherlv_0=(Token)match(input,27,FOLLOW_27_in_ruleFun_Arguments_exp5177); 

                	newLeafNode(otherlv_0, grammarAccess.getFun_Arguments_expAccess().getCommaKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2548:1: ( (lv_Args_1_0= rulefunction_arguments ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2549:1: (lv_Args_1_0= rulefunction_arguments )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2549:1: (lv_Args_1_0= rulefunction_arguments )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2550:3: lv_Args_1_0= rulefunction_arguments
            {
             
            	        newCompositeNode(grammarAccess.getFun_Arguments_expAccess().getArgsFunction_argumentsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp5198);
            lv_Args_1_0=rulefunction_arguments();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFun_Arguments_expRule());
            	        }
                   		set(
                   			current, 
                   			"Args",
                    		lv_Args_1_0, 
                    		"function_arguments");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFun_Arguments_exp"


    // $ANTLR start "entryRuleFun_Arguments_for"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2574:1: entryRuleFun_Arguments_for returns [EObject current=null] : iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF ;
    public final EObject entryRuleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_for = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2575:2: (iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2576:2: iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF
            {
             newCompositeNode(grammarAccess.getFun_Arguments_forRule()); 
            pushFollow(FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for5234);
            iv_ruleFun_Arguments_for=ruleFun_Arguments_for();

            state._fsp--;

             current =iv_ruleFun_Arguments_for; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_for5244); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFun_Arguments_for"


    // $ANTLR start "ruleFun_Arguments_for"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2583:1: ruleFun_Arguments_for returns [EObject current=null] : (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) ;
    public final EObject ruleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_For_indices_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2586:28: ( (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2587:1: (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2587:1: (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2587:3: otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleFun_Arguments_for5281); 

                	newLeafNode(otherlv_0, grammarAccess.getFun_Arguments_forAccess().getForKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2591:1: ( (lv_For_indices_1_0= rulefor_indices ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2592:1: (lv_For_indices_1_0= rulefor_indices )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2592:1: (lv_For_indices_1_0= rulefor_indices )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2593:3: lv_For_indices_1_0= rulefor_indices
            {
             
            	        newCompositeNode(grammarAccess.getFun_Arguments_forAccess().getFor_indicesFor_indicesParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_ruleFun_Arguments_for5302);
            lv_For_indices_1_0=rulefor_indices();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFun_Arguments_forRule());
            	        }
                   		set(
                   			current, 
                   			"For_indices",
                    		lv_For_indices_1_0, 
                    		"for_indices");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFun_Arguments_for"


    // $ANTLR start "entryRulenamed_arguments"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2617:1: entryRulenamed_arguments returns [EObject current=null] : iv_rulenamed_arguments= rulenamed_arguments EOF ;
    public final EObject entryRulenamed_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2618:2: (iv_rulenamed_arguments= rulenamed_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2619:2: iv_rulenamed_arguments= rulenamed_arguments EOF
            {
             newCompositeNode(grammarAccess.getNamed_argumentsRule()); 
            pushFollow(FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments5338);
            iv_rulenamed_arguments=rulenamed_arguments();

            state._fsp--;

             current =iv_rulenamed_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_arguments5348); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulenamed_arguments"


    // $ANTLR start "rulenamed_arguments"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2626:1: rulenamed_arguments returns [EObject current=null] : (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) ;
    public final EObject rulenamed_arguments() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_named_argument_0 = null;

        EObject lv_Named_arguments_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2629:28: ( (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2630:1: (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2630:1: (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2631:5: this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulenamed_argument_in_rulenamed_arguments5395);
            this_named_argument_0=rulenamed_argument();

            state._fsp--;

             
                    current = this_named_argument_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2639:1: (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==27) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2639:3: otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    {
                    otherlv_1=(Token)match(input,27,FOLLOW_27_in_rulenamed_arguments5407); 

                        	newLeafNode(otherlv_1, grammarAccess.getNamed_argumentsAccess().getCommaKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2643:1: ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2644:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2644:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2645:3: lv_Named_arguments_2_0= rulenamed_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentsNamed_argumentsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulenamed_arguments5428);
                    lv_Named_arguments_2_0=rulenamed_arguments();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getNamed_argumentsRule());
                    	        }
                           		set(
                           			current, 
                           			"Named_arguments",
                            		lv_Named_arguments_2_0, 
                            		"named_arguments");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulenamed_arguments"


    // $ANTLR start "entryRulenamed_argument"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2669:1: entryRulenamed_argument returns [EObject current=null] : iv_rulenamed_argument= rulenamed_argument EOF ;
    public final EObject entryRulenamed_argument() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_argument = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2670:2: (iv_rulenamed_argument= rulenamed_argument EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2671:2: iv_rulenamed_argument= rulenamed_argument EOF
            {
             newCompositeNode(grammarAccess.getNamed_argumentRule()); 
            pushFollow(FOLLOW_rulenamed_argument_in_entryRulenamed_argument5466);
            iv_rulenamed_argument=rulenamed_argument();

            state._fsp--;

             current =iv_rulenamed_argument; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_argument5476); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulenamed_argument"


    // $ANTLR start "rulenamed_argument"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2678:1: rulenamed_argument returns [EObject current=null] : ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulenamed_argument() throws RecognitionException {
        EObject current = null;

        Token lv_arg_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2681:28: ( ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2682:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2682:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2682:2: ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2682:2: ( (lv_arg_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2683:1: (lv_arg_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2683:1: (lv_arg_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2684:3: lv_arg_0_0= RULE_IDENT
            {
            lv_arg_0_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulenamed_argument5518); 

            			newLeafNode(lv_arg_0_0, grammarAccess.getNamed_argumentAccess().getArgIDENTTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNamed_argumentRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"arg",
                    		lv_arg_0_0, 
                    		"IDENT");
            	    

            }


            }

            otherlv_1=(Token)match(input,15,FOLLOW_15_in_rulenamed_argument5535); 

                	newLeafNode(otherlv_1, grammarAccess.getNamed_argumentAccess().getEqualsSignKeyword_1());
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2704:1: ( (lv_expr_2_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2705:1: (lv_expr_2_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2705:1: (lv_expr_2_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2706:3: lv_expr_2_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getNamed_argumentAccess().getExprExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulenamed_argument5556);
            lv_expr_2_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNamed_argumentRule());
            	        }
                   		set(
                   			current, 
                   			"expr",
                    		lv_expr_2_0, 
                    		"expression");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulenamed_argument"


    // $ANTLR start "entryRulefor_indices"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2730:1: entryRulefor_indices returns [EObject current=null] : iv_rulefor_indices= rulefor_indices EOF ;
    public final EObject entryRulefor_indices() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_indices = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2731:2: (iv_rulefor_indices= rulefor_indices EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2732:2: iv_rulefor_indices= rulefor_indices EOF
            {
             newCompositeNode(grammarAccess.getFor_indicesRule()); 
            pushFollow(FOLLOW_rulefor_indices_in_entryRulefor_indices5592);
            iv_rulefor_indices=rulefor_indices();

            state._fsp--;

             current =iv_rulefor_indices; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_indices5602); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulefor_indices"


    // $ANTLR start "rulefor_indices"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2739:1: rulefor_indices returns [EObject current=null] : (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) ;
    public final EObject rulefor_indices() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_for_index_0 = null;

        EObject lv_For_index_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2742:28: ( (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2743:1: (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2743:1: (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2744:5: this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulefor_index_in_rulefor_indices5649);
            this_for_index_0=rulefor_index();

            state._fsp--;

             
                    current = this_for_index_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2752:1: (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==27) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2752:3: otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) )
            	    {
            	    otherlv_1=(Token)match(input,27,FOLLOW_27_in_rulefor_indices5661); 

            	        	newLeafNode(otherlv_1, grammarAccess.getFor_indicesAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2756:1: ( (lv_For_index_2_0= rulefor_index ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2757:1: (lv_For_index_2_0= rulefor_index )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2757:1: (lv_For_index_2_0= rulefor_index )
            	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2758:3: lv_For_index_2_0= rulefor_index
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexFor_indexParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulefor_index_in_rulefor_indices5682);
            	    lv_For_index_2_0=rulefor_index();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFor_indicesRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"For_index",
            	            		lv_For_index_2_0, 
            	            		"for_index");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulefor_indices"


    // $ANTLR start "entryRulefor_index"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2782:1: entryRulefor_index returns [EObject current=null] : iv_rulefor_index= rulefor_index EOF ;
    public final EObject entryRulefor_index() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_index = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2783:2: (iv_rulefor_index= rulefor_index EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2784:2: iv_rulefor_index= rulefor_index EOF
            {
             newCompositeNode(grammarAccess.getFor_indexRule()); 
            pushFollow(FOLLOW_rulefor_index_in_entryRulefor_index5720);
            iv_rulefor_index=rulefor_index();

            state._fsp--;

             current =iv_rulefor_index; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_index5730); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulefor_index"


    // $ANTLR start "rulefor_index"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2791:1: rulefor_index returns [EObject current=null] : ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) ;
    public final EObject rulefor_index() throws RecognitionException {
        EObject current = null;

        Token lv_index_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2794:28: ( ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2795:1: ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2795:1: ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2795:2: ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2795:2: ( (lv_index_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2796:1: (lv_index_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2796:1: (lv_index_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2797:3: lv_index_0_0= RULE_IDENT
            {
            lv_index_0_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulefor_index5772); 

            			newLeafNode(lv_index_0_0, grammarAccess.getFor_indexAccess().getIndexIDENTTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getFor_indexRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"index",
                    		lv_index_0_0, 
                    		"IDENT");
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2813:2: (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==42) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2813:4: otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) )
                    {
                    otherlv_1=(Token)match(input,42,FOLLOW_42_in_rulefor_index5790); 

                        	newLeafNode(otherlv_1, grammarAccess.getFor_indexAccess().getInKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2817:1: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2818:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2818:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2819:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFor_indexAccess().getExprExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefor_index5811);
                    lv_expr_2_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getFor_indexRule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_2_0, 
                            		"expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulefor_index"


    // $ANTLR start "entryRulemul_op_mul"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2843:1: entryRulemul_op_mul returns [String current=null] : iv_rulemul_op_mul= rulemul_op_mul EOF ;
    public final String entryRulemul_op_mul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_mul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2844:2: (iv_rulemul_op_mul= rulemul_op_mul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2845:2: iv_rulemul_op_mul= rulemul_op_mul EOF
            {
             newCompositeNode(grammarAccess.getMul_op_mulRule()); 
            pushFollow(FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul5850);
            iv_rulemul_op_mul=rulemul_op_mul();

            state._fsp--;

             current =iv_rulemul_op_mul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_mul5861); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulemul_op_mul"


    // $ANTLR start "rulemul_op_mul"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2852:1: rulemul_op_mul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken rulemul_op_mul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2855:28: (kw= '*' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2857:2: kw= '*'
            {
            kw=(Token)match(input,43,FOLLOW_43_in_rulemul_op_mul5898); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getMul_op_mulAccess().getAsteriskKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulemul_op_mul"


    // $ANTLR start "entryRulemul_op_div"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2870:1: entryRulemul_op_div returns [String current=null] : iv_rulemul_op_div= rulemul_op_div EOF ;
    public final String entryRulemul_op_div() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_div = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2871:2: (iv_rulemul_op_div= rulemul_op_div EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2872:2: iv_rulemul_op_div= rulemul_op_div EOF
            {
             newCompositeNode(grammarAccess.getMul_op_divRule()); 
            pushFollow(FOLLOW_rulemul_op_div_in_entryRulemul_op_div5938);
            iv_rulemul_op_div=rulemul_op_div();

            state._fsp--;

             current =iv_rulemul_op_div.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_div5949); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulemul_op_div"


    // $ANTLR start "rulemul_op_div"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2879:1: rulemul_op_div returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '/' ;
    public final AntlrDatatypeRuleToken rulemul_op_div() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2882:28: (kw= '/' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2884:2: kw= '/'
            {
            kw=(Token)match(input,44,FOLLOW_44_in_rulemul_op_div5986); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getMul_op_divAccess().getSolidusKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulemul_op_div"


    // $ANTLR start "entryRulemul_op_dotmul"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2897:1: entryRulemul_op_dotmul returns [String current=null] : iv_rulemul_op_dotmul= rulemul_op_dotmul EOF ;
    public final String entryRulemul_op_dotmul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotmul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2898:2: (iv_rulemul_op_dotmul= rulemul_op_dotmul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2899:2: iv_rulemul_op_dotmul= rulemul_op_dotmul EOF
            {
             newCompositeNode(grammarAccess.getMul_op_dotmulRule()); 
            pushFollow(FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul6026);
            iv_rulemul_op_dotmul=rulemul_op_dotmul();

            state._fsp--;

             current =iv_rulemul_op_dotmul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotmul6037); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulemul_op_dotmul"


    // $ANTLR start "rulemul_op_dotmul"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2906:1: rulemul_op_dotmul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.*' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotmul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2909:28: (kw= '.*' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2911:2: kw= '.*'
            {
            kw=(Token)match(input,45,FOLLOW_45_in_rulemul_op_dotmul6074); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getMul_op_dotmulAccess().getFullStopAsteriskKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulemul_op_dotmul"


    // $ANTLR start "entryRulemul_op_dotdiv"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2924:1: entryRulemul_op_dotdiv returns [String current=null] : iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF ;
    public final String entryRulemul_op_dotdiv() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotdiv = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2925:2: (iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2926:2: iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF
            {
             newCompositeNode(grammarAccess.getMul_op_dotdivRule()); 
            pushFollow(FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv6114);
            iv_rulemul_op_dotdiv=rulemul_op_dotdiv();

            state._fsp--;

             current =iv_rulemul_op_dotdiv.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotdiv6125); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulemul_op_dotdiv"


    // $ANTLR start "rulemul_op_dotdiv"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2933:1: rulemul_op_dotdiv returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= './' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotdiv() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2936:28: (kw= './' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2938:2: kw= './'
            {
            kw=(Token)match(input,46,FOLLOW_46_in_rulemul_op_dotdiv6162); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getMul_op_dotdivAccess().getFullStopSolidusKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulemul_op_dotdiv"


    // $ANTLR start "entryRuleadd_op_plus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2951:1: entryRuleadd_op_plus returns [String current=null] : iv_ruleadd_op_plus= ruleadd_op_plus EOF ;
    public final String entryRuleadd_op_plus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_plus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2952:2: (iv_ruleadd_op_plus= ruleadd_op_plus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2953:2: iv_ruleadd_op_plus= ruleadd_op_plus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_plusRule()); 
            pushFollow(FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus6202);
            iv_ruleadd_op_plus=ruleadd_op_plus();

            state._fsp--;

             current =iv_ruleadd_op_plus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_plus6213); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleadd_op_plus"


    // $ANTLR start "ruleadd_op_plus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2960:1: ruleadd_op_plus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_plus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2963:28: (kw= '+' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2965:2: kw= '+'
            {
            kw=(Token)match(input,47,FOLLOW_47_in_ruleadd_op_plus6250); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAdd_op_plusAccess().getPlusSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleadd_op_plus"


    // $ANTLR start "entryRuleadd_op_minus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2978:1: entryRuleadd_op_minus returns [String current=null] : iv_ruleadd_op_minus= ruleadd_op_minus EOF ;
    public final String entryRuleadd_op_minus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_minus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2979:2: (iv_ruleadd_op_minus= ruleadd_op_minus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2980:2: iv_ruleadd_op_minus= ruleadd_op_minus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_minusRule()); 
            pushFollow(FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus6290);
            iv_ruleadd_op_minus=ruleadd_op_minus();

            state._fsp--;

             current =iv_ruleadd_op_minus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_minus6301); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleadd_op_minus"


    // $ANTLR start "ruleadd_op_minus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2987:1: ruleadd_op_minus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_minus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2990:28: (kw= '-' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:2992:2: kw= '-'
            {
            kw=(Token)match(input,48,FOLLOW_48_in_ruleadd_op_minus6338); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAdd_op_minusAccess().getHyphenMinusKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleadd_op_minus"


    // $ANTLR start "entryRuleadd_op_dotplus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3005:1: entryRuleadd_op_dotplus returns [String current=null] : iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF ;
    public final String entryRuleadd_op_dotplus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotplus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3006:2: (iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3007:2: iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_dotplusRule()); 
            pushFollow(FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus6378);
            iv_ruleadd_op_dotplus=ruleadd_op_dotplus();

            state._fsp--;

             current =iv_ruleadd_op_dotplus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotplus6389); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleadd_op_dotplus"


    // $ANTLR start "ruleadd_op_dotplus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3014:1: ruleadd_op_dotplus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotplus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3017:28: (kw= '.+' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3019:2: kw= '.+'
            {
            kw=(Token)match(input,49,FOLLOW_49_in_ruleadd_op_dotplus6426); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAdd_op_dotplusAccess().getFullStopPlusSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleadd_op_dotplus"


    // $ANTLR start "entryRuleadd_op_dotminus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3032:1: entryRuleadd_op_dotminus returns [String current=null] : iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF ;
    public final String entryRuleadd_op_dotminus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotminus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3033:2: (iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3034:2: iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_dotminusRule()); 
            pushFollow(FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus6466);
            iv_ruleadd_op_dotminus=ruleadd_op_dotminus();

            state._fsp--;

             current =iv_ruleadd_op_dotminus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotminus6477); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleadd_op_dotminus"


    // $ANTLR start "ruleadd_op_dotminus"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3041:1: ruleadd_op_dotminus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotminus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3044:28: (kw= '.-' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3046:2: kw= '.-'
            {
            kw=(Token)match(input,50,FOLLOW_50_in_ruleadd_op_dotminus6514); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getAdd_op_dotminusAccess().getFullStopHyphenMinusKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleadd_op_dotminus"


    // $ANTLR start "entryRulerel_op_Less_then"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3059:1: entryRulerel_op_Less_then returns [String current=null] : iv_rulerel_op_Less_then= rulerel_op_Less_then EOF ;
    public final String entryRulerel_op_Less_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3060:2: (iv_rulerel_op_Less_then= rulerel_op_Less_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3061:2: iv_rulerel_op_Less_then= rulerel_op_Less_then EOF
            {
             newCompositeNode(grammarAccess.getRel_op_Less_thenRule()); 
            pushFollow(FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then6554);
            iv_rulerel_op_Less_then=rulerel_op_Less_then();

            state._fsp--;

             current =iv_rulerel_op_Less_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_then6565); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerel_op_Less_then"


    // $ANTLR start "rulerel_op_Less_then"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3068:1: rulerel_op_Less_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3071:28: (kw= '<' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3073:2: kw= '<'
            {
            kw=(Token)match(input,51,FOLLOW_51_in_rulerel_op_Less_then6602); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getRel_op_Less_thenAccess().getLessThanSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerel_op_Less_then"


    // $ANTLR start "entryRulerel_op_Less_equal"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3086:1: entryRulerel_op_Less_equal returns [String current=null] : iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF ;
    public final String entryRulerel_op_Less_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3087:2: (iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3088:2: iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF
            {
             newCompositeNode(grammarAccess.getRel_op_Less_equalRule()); 
            pushFollow(FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal6642);
            iv_rulerel_op_Less_equal=rulerel_op_Less_equal();

            state._fsp--;

             current =iv_rulerel_op_Less_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_equal6653); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerel_op_Less_equal"


    // $ANTLR start "rulerel_op_Less_equal"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3095:1: rulerel_op_Less_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<=' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3098:28: (kw= '<=' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3100:2: kw= '<='
            {
            kw=(Token)match(input,52,FOLLOW_52_in_rulerel_op_Less_equal6690); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getRel_op_Less_equalAccess().getLessThanSignEqualsSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerel_op_Less_equal"


    // $ANTLR start "entryRulerel_op_greater_then"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3113:1: entryRulerel_op_greater_then returns [String current=null] : iv_rulerel_op_greater_then= rulerel_op_greater_then EOF ;
    public final String entryRulerel_op_greater_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3114:2: (iv_rulerel_op_greater_then= rulerel_op_greater_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3115:2: iv_rulerel_op_greater_then= rulerel_op_greater_then EOF
            {
             newCompositeNode(grammarAccess.getRel_op_greater_thenRule()); 
            pushFollow(FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then6730);
            iv_rulerel_op_greater_then=rulerel_op_greater_then();

            state._fsp--;

             current =iv_rulerel_op_greater_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_then6741); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerel_op_greater_then"


    // $ANTLR start "rulerel_op_greater_then"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3122:1: rulerel_op_greater_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3125:28: (kw= '>' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3127:2: kw= '>'
            {
            kw=(Token)match(input,53,FOLLOW_53_in_rulerel_op_greater_then6778); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getRel_op_greater_thenAccess().getGreaterThanSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerel_op_greater_then"


    // $ANTLR start "entryRulerel_op_greater_equal"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3140:1: entryRulerel_op_greater_equal returns [String current=null] : iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF ;
    public final String entryRulerel_op_greater_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3141:2: (iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3142:2: iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF
            {
             newCompositeNode(grammarAccess.getRel_op_greater_equalRule()); 
            pushFollow(FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal6818);
            iv_rulerel_op_greater_equal=rulerel_op_greater_equal();

            state._fsp--;

             current =iv_rulerel_op_greater_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_equal6829); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerel_op_greater_equal"


    // $ANTLR start "rulerel_op_greater_equal"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3149:1: rulerel_op_greater_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>=' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3152:28: (kw= '>=' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3154:2: kw= '>='
            {
            kw=(Token)match(input,54,FOLLOW_54_in_rulerel_op_greater_equal6866); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getRel_op_greater_equalAccess().getGreaterThanSignEqualsSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerel_op_greater_equal"


    // $ANTLR start "entryRulerel_op_assignment"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3167:1: entryRulerel_op_assignment returns [String current=null] : iv_rulerel_op_assignment= rulerel_op_assignment EOF ;
    public final String entryRulerel_op_assignment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_assignment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3168:2: (iv_rulerel_op_assignment= rulerel_op_assignment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3169:2: iv_rulerel_op_assignment= rulerel_op_assignment EOF
            {
             newCompositeNode(grammarAccess.getRel_op_assignmentRule()); 
            pushFollow(FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment6906);
            iv_rulerel_op_assignment=rulerel_op_assignment();

            state._fsp--;

             current =iv_rulerel_op_assignment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_assignment6917); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerel_op_assignment"


    // $ANTLR start "rulerel_op_assignment"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3176:1: rulerel_op_assignment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '==' ;
    public final AntlrDatatypeRuleToken rulerel_op_assignment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3179:28: (kw= '==' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3181:2: kw= '=='
            {
            kw=(Token)match(input,55,FOLLOW_55_in_rulerel_op_assignment6954); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getRel_op_assignmentAccess().getEqualsSignEqualsSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerel_op_assignment"


    // $ANTLR start "entryRulerel_op_Oper"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3194:1: entryRulerel_op_Oper returns [String current=null] : iv_rulerel_op_Oper= rulerel_op_Oper EOF ;
    public final String entryRulerel_op_Oper() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Oper = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3195:2: (iv_rulerel_op_Oper= rulerel_op_Oper EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3196:2: iv_rulerel_op_Oper= rulerel_op_Oper EOF
            {
             newCompositeNode(grammarAccess.getRel_op_OperRule()); 
            pushFollow(FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper6994);
            iv_rulerel_op_Oper=rulerel_op_Oper();

            state._fsp--;

             current =iv_rulerel_op_Oper.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Oper7005); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerel_op_Oper"


    // $ANTLR start "rulerel_op_Oper"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3203:1: rulerel_op_Oper returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<>' ;
    public final AntlrDatatypeRuleToken rulerel_op_Oper() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3206:28: (kw= '<>' )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3208:2: kw= '<>'
            {
            kw=(Token)match(input,56,FOLLOW_56_in_rulerel_op_Oper7042); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getRel_op_OperAccess().getLessThanSignGreaterThanSignKeyword()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerel_op_Oper"


    // $ANTLR start "entryRulecomment"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3221:1: entryRulecomment returns [String current=null] : iv_rulecomment= rulecomment EOF ;
    public final String entryRulecomment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulecomment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3222:2: (iv_rulecomment= rulecomment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3223:2: iv_rulecomment= rulecomment EOF
            {
             newCompositeNode(grammarAccess.getCommentRule()); 
            pushFollow(FOLLOW_rulecomment_in_entryRulecomment7082);
            iv_rulecomment=rulecomment();

            state._fsp--;

             current =iv_rulecomment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomment7093); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulecomment"


    // $ANTLR start "rulecomment"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3230:1: rulecomment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_string_comment_0= rulestring_comment ;
    public final AntlrDatatypeRuleToken rulecomment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_string_comment_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3233:28: (this_string_comment_0= rulestring_comment )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3235:5: this_string_comment_0= rulestring_comment
            {
             
                    newCompositeNode(grammarAccess.getCommentAccess().getString_commentParserRuleCall()); 
                
            pushFollow(FOLLOW_rulestring_comment_in_rulecomment7139);
            this_string_comment_0=rulestring_comment();

            state._fsp--;


            		current.merge(this_string_comment_0);
                
             
                    afterParserOrEnumRuleCall();
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulecomment"


    // $ANTLR start "entryRulestring_comment"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3253:1: entryRulestring_comment returns [String current=null] : iv_rulestring_comment= rulestring_comment EOF ;
    public final String entryRulestring_comment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulestring_comment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3254:2: (iv_rulestring_comment= rulestring_comment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3255:2: iv_rulestring_comment= rulestring_comment EOF
            {
             newCompositeNode(grammarAccess.getString_commentRule()); 
            pushFollow(FOLLOW_rulestring_comment_in_entryRulestring_comment7184);
            iv_rulestring_comment=rulestring_comment();

            state._fsp--;

             current =iv_rulestring_comment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulestring_comment7195); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulestring_comment"


    // $ANTLR start "rulestring_comment"
    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3262:1: rulestring_comment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? ;
    public final AntlrDatatypeRuleToken rulestring_comment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token kw=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3265:28: ( (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? )
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3266:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3266:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==RULE_STRING) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3266:6: this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )*
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment7235); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_0()); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3273:1: (kw= '+' this_STRING_2= RULE_STRING )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==47) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.equationsection/src-gen/org/openmodelica/modelicaml/editor/xtext/equation/parser/antlr/internal/InternalEquationsection.g:3274:2: kw= '+' this_STRING_2= RULE_STRING
                    	    {
                    	    kw=(Token)match(input,47,FOLLOW_47_in_rulestring_comment7254); 

                    	            current.merge(kw);
                    	            newLeafNode(kw, grammarAccess.getString_commentAccess().getPlusSignKeyword_1_0()); 
                    	        
                    	    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment7269); 

                    	    		current.merge(this_STRING_2);
                    	        
                    	     
                    	        newLeafNode(this_STRING_2, grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_1_1()); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulestring_comment"

    // Delegated rules


    protected DFA30 dfa30 = new DFA30(this);
    static final String DFA30_eotS =
        "\21\uffff";
    static final String DFA30_eofS =
        "\6\uffff\1\15\11\uffff\1\15";
    static final String DFA30_minS =
        "\1\4\4\uffff\1\10\1\6\7\uffff\1\10\1\uffff\1\6";
    static final String DFA30_maxS =
        "\1\51\4\uffff\1\10\1\70\7\uffff\1\10\1\uffff\1\70";
    static final String DFA30_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1"+
        "\10\1\uffff\1\5\1\uffff";
    static final String DFA30_specialS =
        "\21\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\1\1\2\1\3\1\4\1\6\12\uffff\1\14\6\uffff\1\11\10\uffff\1"+
            "\12\1\uffff\1\13\1\uffff\1\7\1\10\1\5",
            "",
            "",
            "",
            "",
            "\1\6",
            "\1\15\7\uffff\2\15\1\uffff\1\15\3\uffff\4\15\1\uffff\1\17"+
            "\5\15\1\uffff\4\15\1\uffff\1\15\2\uffff\1\16\1\uffff\16\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\20",
            "",
            "\1\15\7\uffff\2\15\1\uffff\1\15\3\uffff\4\15\1\uffff\1\17"+
            "\5\15\1\uffff\4\15\1\uffff\1\15\2\uffff\1\16\1\uffff\16\15"
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "1588:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleequation_section_in_entryRuleequation_section75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleequation_section85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleequation_in_ruleequation_section141 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleequation_section153 = new BitSet(new long[]{0x000783A9069901F2L});
    public static final BitSet FOLLOW_ruleequation_in_entryRuleequation191 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleequation201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleequation249 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleequation261 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleequation282 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleif_equation_in_ruleequation311 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rulefor_equation_in_ruleequation338 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ruleconnect_clause_in_ruleequation365 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rulewhen_equation_in_ruleequation392 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rulecomment_in_ruleequation413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulewhen_equation_in_entryRulewhen_equation449 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulewhen_equation459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rulewhen_equation496 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulewhen_equation517 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_rulewhen_equation529 = new BitSet(new long[]{0x000783A9069D41F0L});
    public static final BitSet FOLLOW_ruleequation_in_rulewhen_equation551 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_rulewhen_equation563 = new BitSet(new long[]{0x000783A9069D41F0L});
    public static final BitSet FOLLOW_18_in_rulewhen_equation578 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulewhen_equation599 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_rulewhen_equation611 = new BitSet(new long[]{0x000783A9069D41F0L});
    public static final BitSet FOLLOW_ruleequation_in_rulewhen_equation633 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_rulewhen_equation645 = new BitSet(new long[]{0x000783A9069D41F0L});
    public static final BitSet FOLLOW_19_in_rulewhen_equation661 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_rulewhen_equation673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleif_equation_in_entryRuleif_equation709 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleif_equation719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleif_equation756 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleif_equation777 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleif_equation789 = new BitSet(new long[]{0x000783A906F941F0L});
    public static final BitSet FOLLOW_ruleequation_in_ruleif_equation811 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleif_equation823 = new BitSet(new long[]{0x000783A906F941F0L});
    public static final BitSet FOLLOW_21_in_ruleif_equation838 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleif_equation859 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleif_equation871 = new BitSet(new long[]{0x000783A906F941F0L});
    public static final BitSet FOLLOW_ruleequation_in_ruleif_equation893 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleif_equation905 = new BitSet(new long[]{0x000783A906F941F0L});
    public static final BitSet FOLLOW_22_in_ruleif_equation922 = new BitSet(new long[]{0x000783A9069941F0L});
    public static final BitSet FOLLOW_ruleequation_in_ruleif_equation944 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleif_equation956 = new BitSet(new long[]{0x000783A9069941F0L});
    public static final BitSet FOLLOW_19_in_ruleif_equation972 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleif_equation984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_equation_in_entryRulefor_equation1020 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_equation1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rulefor_equation1067 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_indices_in_rulefor_equation1088 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_rulefor_equation1100 = new BitSet(new long[]{0x000783A9069941F0L});
    public static final BitSet FOLLOW_ruleequation_in_rulefor_equation1122 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_rulefor_equation1134 = new BitSet(new long[]{0x000783A9069941F0L});
    public static final BitSet FOLLOW_19_in_rulefor_equation1148 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_rulefor_equation1160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconnect_clause_in_entryRuleconnect_clause1196 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconnect_clause1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleconnect_clause1243 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleconnect_clause1255 = new BitSet(new long[]{0x0000020000000100L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleconnect_clause1276 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleconnect_clause1288 = new BitSet(new long[]{0x0000020000000100L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleconnect_clause1309 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleconnect_clause1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_entryRuleexpression1357 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression1367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleexpression1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_ruleexpression1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_entryRulesimple_expression1476 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesimple_expression1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression1532 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_rulesimple_expression1545 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression1566 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_rulesimple_expression1579 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression1600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr1640 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_expr1650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleconditional_expr1687 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr1708 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleconditional_expr1720 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr1741 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_21_in_ruleconditional_expr1754 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr1775 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleconditional_expr1787 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr1808 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_22_in_ruleconditional_expr1823 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_entryRulelogical_expression1881 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_expression1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression1938 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_rulelogical_expression1950 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression1971 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_rulelogical_term_in_entryRulelogical_term2009 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_term2019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term2066 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_31_in_rulelogical_term2078 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term2099 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_entryRulelogical_factor2137 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_factor2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rulelogical_factor2185 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulerelation_in_rulelogical_factor2208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerelation_in_entryRulerelation2244 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerelation2254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation2301 = new BitSet(new long[]{0x01F8000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_rulerelation2324 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_rulerelation2343 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_rulerelation2362 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_rulerelation2381 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_rulerelation2400 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_rulerelation2419 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation2443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression2481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearithmetic_expression2491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression2539 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression2558 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression2577 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression2596 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression2621 = new BitSet(new long[]{0x0007800000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression2645 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression2664 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression2683 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression2702 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression2726 = new BitSet(new long[]{0x0007800000000002L});
    public static final BitSet FOLLOW_ruleterm_in_entryRuleterm2764 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleterm2774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm2821 = new BitSet(new long[]{0x0000780000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_ruleterm2844 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulemul_op_div_in_ruleterm2863 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_ruleterm2882 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_ruleterm2901 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm2925 = new BitSet(new long[]{0x0000780000000002L});
    public static final BitSet FOLLOW_rulefactor_in_entryRulefactor2963 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefactor2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor3020 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_33_in_rulefactor3033 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_34_in_rulefactor3051 = new BitSet(new long[]{0x000783A9040801F0L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor3073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_entryRuleprimary3111 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleprimary3121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary3163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleprimary3191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleprimary3219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_VAL_in_ruleprimary3247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_ruleprimary3280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_ruleprimary3307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_ruleprimary3334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleprimary3360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleprimary3379 = new BitSet(new long[]{0x000783A91C1801F0L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_ruleprimary3400 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleprimary3412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleprimary3432 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary3453 = new BitSet(new long[]{0x0000001000004000L});
    public static final BitSet FOLLOW_14_in_ruleprimary3466 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary3487 = new BitSet(new long[]{0x0000001000004000L});
    public static final BitSet FOLLOW_36_in_ruleprimary3501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleprimary3521 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleprimary3542 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleprimary3554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleprimary3579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_entryRulename_Function3628 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_Function3638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_in_rulename_Function3685 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_rulename_Function3705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref3741 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleinitial_ref3751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleinitial_ref3788 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleinitial_ref3810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_entryRuleExprDer3845 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExprDer3855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleExprDer3892 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleExprDer3913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args3949 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_call_args3959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulefunction_call_args4005 = new BitSet(new long[]{0x000783A9141801F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_rulefunction_call_args4026 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_rulefunction_call_args4039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list_in_entryRuleexpression_list4075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression_list4085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list4131 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_ruleexpression_list4144 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list4165 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_rulename_in_entryRulename4203 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename4213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rulename4251 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename4270 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_rulename4288 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename4305 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference4348 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_reference4358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rulecomponent_reference4396 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference4415 = new BitSet(new long[]{0x0000020800000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference4441 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_rulecomponent_reference4455 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference4472 = new BitSet(new long[]{0x0000020800000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference4498 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list4537 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleoutput_expression_list4547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list4602 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_ruleoutput_expression_list4616 = new BitSet(new long[]{0x000783A90C1801F2L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list4637 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts4676 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearray_subscripts4686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rulearray_subscripts4723 = new BitSet(new long[]{0x000783A9241801F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts4744 = new BitSet(new long[]{0x0000001008000000L});
    public static final BitSet FOLLOW_27_in_rulearray_subscripts4757 = new BitSet(new long[]{0x000783A9241801F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts4778 = new BitSet(new long[]{0x0000001008000000L});
    public static final BitSet FOLLOW_36_in_rulearray_subscripts4792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesubscript_in_entryRulesubscript4828 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesubscript4838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rulesubscript4885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulesubscript4913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments4949 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_arguments4959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefunction_arguments5015 = new BitSet(new long[]{0x0000000008800002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments5037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments5064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulefunction_arguments5094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp5130 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_exp5140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleFun_Arguments_exp5177 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp5198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for5234 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_for5244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleFun_Arguments_for5281 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_indices_in_ruleFun_Arguments_for5302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments5338 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_arguments5348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_rulenamed_arguments5395 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_rulenamed_arguments5407 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulenamed_arguments5428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_entryRulenamed_argument5466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_argument5476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulenamed_argument5518 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_rulenamed_argument5535 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulenamed_argument5556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_indices_in_entryRulefor_indices5592 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_indices5602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices5649 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_rulefor_indices5661 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices5682 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_rulefor_index_in_entryRulefor_index5720 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_index5730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulefor_index5772 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_rulefor_index5790 = new BitSet(new long[]{0x000783A9041801F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_index5811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul5850 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_mul5861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rulemul_op_mul5898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_div_in_entryRulemul_op_div5938 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_div5949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_rulemul_op_div5986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul6026 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotmul6037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_rulemul_op_dotmul6074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv6114 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotdiv6125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_rulemul_op_dotdiv6162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus6202 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_plus6213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleadd_op_plus6250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus6290 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_minus6301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleadd_op_minus6338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus6378 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotplus6389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleadd_op_dotplus6426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus6466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotminus6477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleadd_op_dotminus6514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then6554 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_then6565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rulerel_op_Less_then6602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal6642 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_equal6653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_rulerel_op_Less_equal6690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then6730 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_then6741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_rulerel_op_greater_then6778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal6818 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_equal6829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_rulerel_op_greater_equal6866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment6906 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_assignment6917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_rulerel_op_assignment6954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper6994 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Oper7005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_rulerel_op_Oper7042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomment_in_entryRulecomment7082 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomment7093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_rulecomment7139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_entryRulestring_comment7184 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulestring_comment7195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment7235 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_47_in_rulestring_comment7254 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment7269 = new BitSet(new long[]{0x0000800000000002L});

}