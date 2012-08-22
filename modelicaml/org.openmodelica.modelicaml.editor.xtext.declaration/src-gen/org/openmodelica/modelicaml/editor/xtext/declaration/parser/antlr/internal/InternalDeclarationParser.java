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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.editor.xtext.declaration.parser.antlr.internal; 

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
import org.openmodelica.modelicaml.editor.xtext.declaration.services.DeclarationGrammarAccess;

@SuppressWarnings("all")
public class InternalDeclarationParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNSIGNED_NUMBER", "RULE_INT", "RULE_STRING", "RULE_BOOL_VAL", "RULE_IDENT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'='", "':='", "'if'", "':'", "'then'", "'elseif'", "'else'", "'or'", "'and'", "'not'", "'^'", "'.^'", "'('", "')'", "'['", "';'", "']'", "'{'", "'}'", "'end'", "'initial'", "'der'", "','", "'.'", "'for'", "'in'", "'*'", "'/'", "'.*'", "'./'", "'+'", "'-'", "'.+'", "'.-'", "'<'", "'<='", "'>'", "'>='", "'=='", "'<>'"
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
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__51=51;
    public static final int T__15=15;
    public static final int T__52=52;
    public static final int T__18=18;
    public static final int T__53=53;
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


        public InternalDeclarationParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalDeclarationParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalDeclarationParser.tokenNames; }
    public String getGrammarFileName() { return "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g"; }



     	private DeclarationGrammarAccess grammarAccess;
     	
        public InternalDeclarationParser(TokenStream input, DeclarationGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "component_declaration";	
       	}
       	
       	@Override
       	protected DeclarationGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRulecomponent_declaration"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:67:1: entryRulecomponent_declaration returns [EObject current=null] : iv_rulecomponent_declaration= rulecomponent_declaration EOF ;
    public final EObject entryRulecomponent_declaration() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_declaration = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:68:2: (iv_rulecomponent_declaration= rulecomponent_declaration EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:69:2: iv_rulecomponent_declaration= rulecomponent_declaration EOF
            {
             newCompositeNode(grammarAccess.getComponent_declarationRule()); 
            pushFollow(FOLLOW_rulecomponent_declaration_in_entryRulecomponent_declaration75);
            iv_rulecomponent_declaration=rulecomponent_declaration();

            state._fsp--;

             current =iv_rulecomponent_declaration; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_declaration85); 

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
    // $ANTLR end "entryRulecomponent_declaration"


    // $ANTLR start "rulecomponent_declaration"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:76:1: rulecomponent_declaration returns [EObject current=null] : ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )? ;
    public final EObject rulecomponent_declaration() throws RecognitionException {
        EObject current = null;

        EObject lv_Mod_0_0 = null;

        EObject lv_Conditional_Att_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:79:28: ( ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:80:1: ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:80:1: ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=14 && LA2_0<=15)) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:80:2: ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:80:2: ( (lv_Mod_0_0= rulemodification ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:81:1: (lv_Mod_0_0= rulemodification )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:81:1: (lv_Mod_0_0= rulemodification )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:82:3: lv_Mod_0_0= rulemodification
                    {
                     
                    	        newCompositeNode(grammarAccess.getComponent_declarationAccess().getModModificationParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulemodification_in_rulecomponent_declaration131);
                    lv_Mod_0_0=rulemodification();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getComponent_declarationRule());
                    	        }
                           		set(
                           			current, 
                           			"Mod",
                            		lv_Mod_0_0, 
                            		"modification");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:98:2: ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==16) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:99:1: (lv_Conditional_Att_1_0= ruleconditional_attribute )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:99:1: (lv_Conditional_Att_1_0= ruleconditional_attribute )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:100:3: lv_Conditional_Att_1_0= ruleconditional_attribute
                            {
                             
                            	        newCompositeNode(grammarAccess.getComponent_declarationAccess().getConditional_AttConditional_attributeParserRuleCall_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleconditional_attribute_in_rulecomponent_declaration152);
                            lv_Conditional_Att_1_0=ruleconditional_attribute();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getComponent_declarationRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"Conditional_Att",
                                    		lv_Conditional_Att_1_0, 
                                    		"conditional_attribute");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }
                            break;

                    }

                     
                            newCompositeNode(grammarAccess.getComponent_declarationAccess().getCommentParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_rulecomment_in_rulecomponent_declaration169);
                    rulecomment();

                    state._fsp--;

                     
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
    // $ANTLR end "rulecomponent_declaration"


    // $ANTLR start "entryRulemodification"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:132:1: entryRulemodification returns [EObject current=null] : iv_rulemodification= rulemodification EOF ;
    public final EObject entryRulemodification() throws RecognitionException {
        EObject current = null;

        EObject iv_rulemodification = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:133:2: (iv_rulemodification= rulemodification EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:134:2: iv_rulemodification= rulemodification EOF
            {
             newCompositeNode(grammarAccess.getModificationRule()); 
            pushFollow(FOLLOW_rulemodification_in_entryRulemodification205);
            iv_rulemodification=rulemodification();

            state._fsp--;

             current =iv_rulemodification; 
            match(input,EOF,FOLLOW_EOF_in_entryRulemodification215); 

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
    // $ANTLR end "entryRulemodification"


    // $ANTLR start "rulemodification"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:141:1: rulemodification returns [EObject current=null] : ( (otherlv_0= '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | (otherlv_2= ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) ) ;
    public final EObject rulemodification() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_Expr_1_0 = null;

        EObject lv_Expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:144:28: ( ( (otherlv_0= '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | (otherlv_2= ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:145:1: ( (otherlv_0= '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | (otherlv_2= ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:145:1: ( (otherlv_0= '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | (otherlv_2= ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            else if ( (LA3_0==15) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:145:2: (otherlv_0= '=' ( (lv_Expr_1_0= ruleexpression ) ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:145:2: (otherlv_0= '=' ( (lv_Expr_1_0= ruleexpression ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:145:4: otherlv_0= '=' ( (lv_Expr_1_0= ruleexpression ) )
                    {
                    otherlv_0=(Token)match(input,14,FOLLOW_14_in_rulemodification253); 

                        	newLeafNode(otherlv_0, grammarAccess.getModificationAccess().getEqualsSignKeyword_0_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:149:1: ( (lv_Expr_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:150:1: (lv_Expr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:150:1: (lv_Expr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:151:3: lv_Expr_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getModificationAccess().getExprExpressionParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulemodification274);
                    lv_Expr_1_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModificationRule());
                    	        }
                           		set(
                           			current, 
                           			"Expr",
                            		lv_Expr_1_0, 
                            		"expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:168:6: (otherlv_2= ':=' ( (lv_Expr_3_0= ruleexpression ) ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:168:6: (otherlv_2= ':=' ( (lv_Expr_3_0= ruleexpression ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:168:8: otherlv_2= ':=' ( (lv_Expr_3_0= ruleexpression ) )
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_15_in_rulemodification294); 

                        	newLeafNode(otherlv_2, grammarAccess.getModificationAccess().getColonEqualsSignKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:172:1: ( (lv_Expr_3_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:173:1: (lv_Expr_3_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:173:1: (lv_Expr_3_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:174:3: lv_Expr_3_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getModificationAccess().getExprExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulemodification315);
                    lv_Expr_3_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getModificationRule());
                    	        }
                           		set(
                           			current, 
                           			"Expr",
                            		lv_Expr_3_0, 
                            		"expression");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


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
    // $ANTLR end "rulemodification"


    // $ANTLR start "entryRuleconditional_attribute"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:198:1: entryRuleconditional_attribute returns [EObject current=null] : iv_ruleconditional_attribute= ruleconditional_attribute EOF ;
    public final EObject entryRuleconditional_attribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_attribute = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:199:2: (iv_ruleconditional_attribute= ruleconditional_attribute EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:200:2: iv_ruleconditional_attribute= ruleconditional_attribute EOF
            {
             newCompositeNode(grammarAccess.getConditional_attributeRule()); 
            pushFollow(FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute352);
            iv_ruleconditional_attribute=ruleconditional_attribute();

            state._fsp--;

             current =iv_ruleconditional_attribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_attribute362); 

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
    // $ANTLR end "entryRuleconditional_attribute"


    // $ANTLR start "ruleconditional_attribute"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:207:1: ruleconditional_attribute returns [EObject current=null] : (otherlv_0= 'if' ( (lv_Expr_1_0= ruleexpression ) ) ) ;
    public final EObject ruleconditional_attribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_Expr_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:210:28: ( (otherlv_0= 'if' ( (lv_Expr_1_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:211:1: (otherlv_0= 'if' ( (lv_Expr_1_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:211:1: (otherlv_0= 'if' ( (lv_Expr_1_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:211:3: otherlv_0= 'if' ( (lv_Expr_1_0= ruleexpression ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleconditional_attribute399); 

                	newLeafNode(otherlv_0, grammarAccess.getConditional_attributeAccess().getIfKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:215:1: ( (lv_Expr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:216:1: (lv_Expr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:216:1: (lv_Expr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:217:3: lv_Expr_1_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_attributeAccess().getExprExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_attribute420);
            lv_Expr_1_0=ruleexpression();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getConditional_attributeRule());
            	        }
                   		set(
                   			current, 
                   			"Expr",
                    		lv_Expr_1_0, 
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
    // $ANTLR end "ruleconditional_attribute"


    // $ANTLR start "entryRuleexpression"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:241:1: entryRuleexpression returns [EObject current=null] : iv_ruleexpression= ruleexpression EOF ;
    public final EObject entryRuleexpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:242:2: (iv_ruleexpression= ruleexpression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:243:2: iv_ruleexpression= ruleexpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleexpression_in_entryRuleexpression456);
            iv_ruleexpression=ruleexpression();

            state._fsp--;

             current =iv_ruleexpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression466); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:250:1: ruleexpression returns [EObject current=null] : ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) ;
    public final EObject ruleexpression() throws RecognitionException {
        EObject current = null;

        EObject lv_Expr_0_0 = null;

        EObject this_conditional_expr_1 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:253:28: ( ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:254:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:254:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=RULE_UNSIGNED_NUMBER && LA4_0<=RULE_IDENT)||LA4_0==23||LA4_0==26||LA4_0==28||LA4_0==31||(LA4_0>=33 && LA4_0<=35)||LA4_0==37||(LA4_0>=44 && LA4_0<=47)) ) {
                alt4=1;
            }
            else if ( (LA4_0==16) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:254:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:254:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:255:1: (lv_Expr_0_0= rulesimple_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:255:1: (lv_Expr_0_0= rulesimple_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:256:3: lv_Expr_0_0= rulesimple_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getExprSimple_expressionParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulesimple_expression_in_ruleexpression512);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:274:5: this_conditional_expr_1= ruleconditional_expr
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getConditional_exprParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleconditional_expr_in_ruleexpression540);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:290:1: entryRulesimple_expression returns [EObject current=null] : iv_rulesimple_expression= rulesimple_expression EOF ;
    public final EObject entryRulesimple_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesimple_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:291:2: (iv_rulesimple_expression= rulesimple_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:292:2: iv_rulesimple_expression= rulesimple_expression EOF
            {
             newCompositeNode(grammarAccess.getSimple_expressionRule()); 
            pushFollow(FOLLOW_rulesimple_expression_in_entryRulesimple_expression575);
            iv_rulesimple_expression=rulesimple_expression();

            state._fsp--;

             current =iv_rulesimple_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesimple_expression585); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:299:1: rulesimple_expression returns [EObject current=null] : ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) ;
    public final EObject rulesimple_expression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_Log_Exp_0_0 = null;

        EObject lv_S_Logical_expression_2_0 = null;

        EObject lv_L_Logical_expression_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:302:28: ( ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:303:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:303:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:303:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:303:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:304:1: (lv_Log_Exp_0_0= rulelogical_expression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:304:1: (lv_Log_Exp_0_0= rulelogical_expression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:305:3: lv_Log_Exp_0_0= rulelogical_expression
            {
             
            	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getLog_ExpLogical_expressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression631);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:321:2: (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:321:4: otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    {
                    otherlv_1=(Token)match(input,17,FOLLOW_17_in_rulesimple_expression644); 

                        	newLeafNode(otherlv_1, grammarAccess.getSimple_expressionAccess().getColonKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:325:1: ( (lv_S_Logical_expression_2_0= rulelogical_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:326:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:326:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:327:3: lv_S_Logical_expression_2_0= rulelogical_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getS_Logical_expressionLogical_expressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression665);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:343:2: (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==17) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:343:4: otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            {
                            otherlv_3=(Token)match(input,17,FOLLOW_17_in_rulesimple_expression678); 

                                	newLeafNode(otherlv_3, grammarAccess.getSimple_expressionAccess().getColonKeyword_1_2_0());
                                
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:347:1: ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:348:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:348:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:349:3: lv_L_Logical_expression_4_0= rulelogical_expression
                            {
                             
                            	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression699);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:373:1: entryRuleconditional_expr returns [EObject current=null] : iv_ruleconditional_expr= ruleconditional_expr EOF ;
    public final EObject entryRuleconditional_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_expr = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:374:2: (iv_ruleconditional_expr= ruleconditional_expr EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:375:2: iv_ruleconditional_expr= ruleconditional_expr EOF
            {
             newCompositeNode(grammarAccess.getConditional_exprRule()); 
            pushFollow(FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr739);
            iv_ruleconditional_expr=ruleconditional_expr();

            state._fsp--;

             current =iv_ruleconditional_expr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_expr749); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:382:1: ruleconditional_expr returns [EObject current=null] : (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:385:28: ( (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:386:1: (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:386:1: (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:386:3: otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleconditional_expr786); 

                	newLeafNode(otherlv_0, grammarAccess.getConditional_exprAccess().getIfKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:390:1: ( (lv_ifexpr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:391:1: (lv_ifexpr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:391:1: (lv_ifexpr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:392:3: lv_ifexpr_1_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getIfexprExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr807);
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

            otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleconditional_expr819); 

                	newLeafNode(otherlv_2, grammarAccess.getConditional_exprAccess().getThenKeyword_2());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:412:1: ( (lv_thenexpr_3_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:413:1: (lv_thenexpr_3_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:413:1: (lv_thenexpr_3_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:414:3: lv_thenexpr_3_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getThenexprExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr840);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:430:2: (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:430:4: otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) )
            	    {
            	    otherlv_4=(Token)match(input,19,FOLLOW_19_in_ruleconditional_expr853); 

            	        	newLeafNode(otherlv_4, grammarAccess.getConditional_exprAccess().getElseifKeyword_4_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:434:1: ( (lv_elseifexpr_5_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:435:1: (lv_elseifexpr_5_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:435:1: (lv_elseifexpr_5_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:436:3: lv_elseifexpr_5_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditional_exprAccess().getElseifexprExpressionParserRuleCall_4_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr874);
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

            	    otherlv_6=(Token)match(input,18,FOLLOW_18_in_ruleconditional_expr886); 

            	        	newLeafNode(otherlv_6, grammarAccess.getConditional_exprAccess().getThenKeyword_4_2());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:456:1: ( (lv_trueexpr_7_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:457:1: (lv_trueexpr_7_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:457:1: (lv_trueexpr_7_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:458:3: lv_trueexpr_7_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditional_exprAccess().getTrueexprExpressionParserRuleCall_4_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr907);
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
            	    break loop7;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:474:4: (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:474:6: otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) )
            {
            otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleconditional_expr922); 

                	newLeafNode(otherlv_8, grammarAccess.getConditional_exprAccess().getElseKeyword_5_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:478:1: ( (lv_falseexpr_9_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:479:1: (lv_falseexpr_9_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:479:1: (lv_falseexpr_9_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:480:3: lv_falseexpr_9_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getFalseexprExpressionParserRuleCall_5_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr943);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:504:1: entryRulelogical_expression returns [EObject current=null] : iv_rulelogical_expression= rulelogical_expression EOF ;
    public final EObject entryRulelogical_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:505:2: (iv_rulelogical_expression= rulelogical_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:506:2: iv_rulelogical_expression= rulelogical_expression EOF
            {
             newCompositeNode(grammarAccess.getLogical_expressionRule()); 
            pushFollow(FOLLOW_rulelogical_expression_in_entryRulelogical_expression980);
            iv_rulelogical_expression=rulelogical_expression();

            state._fsp--;

             current =iv_rulelogical_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_expression990); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:513:1: rulelogical_expression returns [EObject current=null] : (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) ;
    public final EObject rulelogical_expression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_logical_term_0 = null;

        EObject lv_Logical_term_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:516:28: ( (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:517:1: (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:517:1: (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:518:5: this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression1037);
            this_logical_term_0=rulelogical_term();

            state._fsp--;

             
                    current = this_logical_term_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:526:1: (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==21) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:526:3: otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    {
            	    otherlv_1=(Token)match(input,21,FOLLOW_21_in_rulelogical_expression1049); 

            	        	newLeafNode(otherlv_1, grammarAccess.getLogical_expressionAccess().getOrKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:530:1: ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:531:1: (lv_Logical_term_2_0= rulelogical_term )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:531:1: (lv_Logical_term_2_0= rulelogical_term )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:532:3: lv_Logical_term_2_0= rulelogical_term
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termLogical_termParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression1070);
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
            	    break loop8;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:556:1: entryRulelogical_term returns [EObject current=null] : iv_rulelogical_term= rulelogical_term EOF ;
    public final EObject entryRulelogical_term() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_term = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:557:2: (iv_rulelogical_term= rulelogical_term EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:558:2: iv_rulelogical_term= rulelogical_term EOF
            {
             newCompositeNode(grammarAccess.getLogical_termRule()); 
            pushFollow(FOLLOW_rulelogical_term_in_entryRulelogical_term1108);
            iv_rulelogical_term=rulelogical_term();

            state._fsp--;

             current =iv_rulelogical_term; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_term1118); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:565:1: rulelogical_term returns [EObject current=null] : (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) ;
    public final EObject rulelogical_term() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_logical_factor_0 = null;

        EObject lv_Logical_factor_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:568:28: ( (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:569:1: (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:569:1: (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:570:5: this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term1165);
            this_logical_factor_0=rulelogical_factor();

            state._fsp--;

             
                    current = this_logical_factor_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:578:1: (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==22) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:578:3: otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    {
            	    otherlv_1=(Token)match(input,22,FOLLOW_22_in_rulelogical_term1177); 

            	        	newLeafNode(otherlv_1, grammarAccess.getLogical_termAccess().getAndKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:582:1: ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:583:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:583:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:584:3: lv_Logical_factor_2_0= rulelogical_factor
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorLogical_factorParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term1198);
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
            	    break loop9;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:608:1: entryRulelogical_factor returns [EObject current=null] : iv_rulelogical_factor= rulelogical_factor EOF ;
    public final EObject entryRulelogical_factor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_factor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:609:2: (iv_rulelogical_factor= rulelogical_factor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:610:2: iv_rulelogical_factor= rulelogical_factor EOF
            {
             newCompositeNode(grammarAccess.getLogical_factorRule()); 
            pushFollow(FOLLOW_rulelogical_factor_in_entryRulelogical_factor1236);
            iv_rulelogical_factor=rulelogical_factor();

            state._fsp--;

             current =iv_rulelogical_factor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_factor1246); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:617:1: rulelogical_factor returns [EObject current=null] : ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) ;
    public final EObject rulelogical_factor() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_Relation_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:620:28: ( ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:621:1: ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:621:1: ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:621:2: (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:621:2: (otherlv_0= 'not' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:621:4: otherlv_0= 'not'
                    {
                    otherlv_0=(Token)match(input,23,FOLLOW_23_in_rulelogical_factor1284); 

                        	newLeafNode(otherlv_0, grammarAccess.getLogical_factorAccess().getNotKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:625:3: ( (lv_Relation_1_0= rulerelation ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:626:1: (lv_Relation_1_0= rulerelation )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:626:1: (lv_Relation_1_0= rulerelation )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:627:3: lv_Relation_1_0= rulerelation
            {
             
            	        newCompositeNode(grammarAccess.getLogical_factorAccess().getRelationRelationParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulerelation_in_rulelogical_factor1307);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:651:1: entryRulerelation returns [EObject current=null] : iv_rulerelation= rulerelation EOF ;
    public final EObject entryRulerelation() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:652:2: (iv_rulerelation= rulerelation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:653:2: iv_rulerelation= rulerelation EOF
            {
             newCompositeNode(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_rulerelation_in_entryRulerelation1343);
            iv_rulerelation=rulerelation();

            state._fsp--;

             current =iv_rulerelation; 
            match(input,EOF,FOLLOW_EOF_in_entryRulerelation1353); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:660:1: rulerelation returns [EObject current=null] : (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:663:28: ( (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:664:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:664:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:665:5: this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1400);
            this_arithmetic_expression_0=rulearithmetic_expression();

            state._fsp--;

             
                    current = this_arithmetic_expression_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:673:1: ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=48 && LA12_0<=53)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:673:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:673:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:674:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:674:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:675:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:675:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    int alt11=6;
                    switch ( input.LA(1) ) {
                    case 48:
                        {
                        alt11=1;
                        }
                        break;
                    case 49:
                        {
                        alt11=2;
                        }
                        break;
                    case 50:
                        {
                        alt11=3;
                        }
                        break;
                    case 51:
                        {
                        alt11=4;
                        }
                        break;
                    case 52:
                        {
                        alt11=5;
                        }
                        break;
                    case 53:
                        {
                        alt11=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }

                    switch (alt11) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:676:3: lv_op_1_1= rulerel_op_Less_then
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_thenParserRuleCall_1_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_then_in_rulerelation1423);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:691:8: lv_op_1_2= rulerel_op_Less_equal
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_equalParserRuleCall_1_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_equal_in_rulerelation1442);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:706:8: lv_op_1_3= rulerel_op_greater_then
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_thenParserRuleCall_1_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_then_in_rulerelation1461);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:721:8: lv_op_1_4= rulerel_op_greater_equal
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_equalParserRuleCall_1_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_equal_in_rulerelation1480);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:736:8: lv_op_1_5= rulerel_op_assignment
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_assignmentParserRuleCall_1_0_0_4()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_assignment_in_rulerelation1499);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:751:8: lv_op_1_6= rulerel_op_Oper
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_OperParserRuleCall_1_0_0_5()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Oper_in_rulerelation1518);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:769:2: ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:770:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:770:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:771:3: lv_Arithmetic_expression_2_0= rulearithmetic_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1542);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:795:1: entryRulearithmetic_expression returns [EObject current=null] : iv_rulearithmetic_expression= rulearithmetic_expression EOF ;
    public final EObject entryRulearithmetic_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearithmetic_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:796:2: (iv_rulearithmetic_expression= rulearithmetic_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:797:2: iv_rulearithmetic_expression= rulearithmetic_expression EOF
            {
             newCompositeNode(grammarAccess.getArithmetic_expressionRule()); 
            pushFollow(FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1580);
            iv_rulearithmetic_expression=rulearithmetic_expression();

            state._fsp--;

             current =iv_rulearithmetic_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearithmetic_expression1590); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:804:1: rulearithmetic_expression returns [EObject current=null] : ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:807:28: ( ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:808:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:808:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:808:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:808:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=44 && LA14_0<=47)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:809:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:809:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:810:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:810:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    int alt13=4;
                    switch ( input.LA(1) ) {
                    case 44:
                        {
                        alt13=1;
                        }
                        break;
                    case 45:
                        {
                        alt13=2;
                        }
                        break;
                    case 46:
                        {
                        alt13=3;
                        }
                        break;
                    case 47:
                        {
                        alt13=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 0, input);

                        throw nvae;
                    }

                    switch (alt13) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:811:3: lv_opr_0_1= ruleadd_op_plus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_plusParserRuleCall_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1638);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:826:8: lv_opr_0_2= ruleadd_op_minus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_minusParserRuleCall_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1657);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:841:8: lv_opr_0_3= ruleadd_op_dotplus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotplusParserRuleCall_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1676);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:856:8: lv_opr_0_4= ruleadd_op_dotminus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotminusParserRuleCall_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1695);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:874:3: ( (lv_Term_1_0= ruleterm ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:875:1: (lv_Term_1_0= ruleterm )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:875:1: (lv_Term_1_0= ruleterm )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:876:3: lv_Term_1_0= ruleterm
            {
             
            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTermTermParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1720);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:892:2: ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=44 && LA16_0<=47)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:892:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:892:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:893:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:893:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:894:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:894:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    int alt15=4;
            	    switch ( input.LA(1) ) {
            	    case 44:
            	        {
            	        alt15=1;
            	        }
            	        break;
            	    case 45:
            	        {
            	        alt15=2;
            	        }
            	        break;
            	    case 46:
            	        {
            	        alt15=3;
            	        }
            	        break;
            	    case 47:
            	        {
            	        alt15=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 15, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt15) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:895:3: lv_Oper1_2_1= ruleadd_op_plus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_plusParserRuleCall_2_0_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1744);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:910:8: lv_Oper1_2_2= ruleadd_op_minus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_minusParserRuleCall_2_0_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1763);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:925:8: lv_Oper1_2_3= ruleadd_op_dotplus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotplusParserRuleCall_2_0_0_2()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1782);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:940:8: lv_Oper1_2_4= ruleadd_op_dotminus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotminusParserRuleCall_2_0_0_3()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1801);
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

            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:958:2: ( (lv_Term1_3_0= ruleterm ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:959:1: (lv_Term1_3_0= ruleterm )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:959:1: (lv_Term1_3_0= ruleterm )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:960:3: lv_Term1_3_0= ruleterm
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTerm1TermParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1825);
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
    // $ANTLR end "rulearithmetic_expression"


    // $ANTLR start "entryRuleterm"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:984:1: entryRuleterm returns [EObject current=null] : iv_ruleterm= ruleterm EOF ;
    public final EObject entryRuleterm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleterm = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:985:2: (iv_ruleterm= ruleterm EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:986:2: iv_ruleterm= ruleterm EOF
            {
             newCompositeNode(grammarAccess.getTermRule()); 
            pushFollow(FOLLOW_ruleterm_in_entryRuleterm1863);
            iv_ruleterm=ruleterm();

            state._fsp--;

             current =iv_ruleterm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleterm1873); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:993:1: ruleterm returns [EObject current=null] : (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:996:28: ( (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:997:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:997:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:998:5: this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getTermAccess().getFactorParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulefactor_in_ruleterm1920);
            this_factor_0=rulefactor();

            state._fsp--;

             
                    current = this_factor_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1006:1: ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=40 && LA18_0<=43)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1006:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1006:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1007:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1007:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1008:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1008:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    int alt17=4;
            	    switch ( input.LA(1) ) {
            	    case 40:
            	        {
            	        alt17=1;
            	        }
            	        break;
            	    case 41:
            	        {
            	        alt17=2;
            	        }
            	        break;
            	    case 42:
            	        {
            	        alt17=3;
            	        }
            	        break;
            	    case 43:
            	        {
            	        alt17=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 17, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt17) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1009:3: lv_op_1_1= rulemul_op_mul
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_mulParserRuleCall_1_0_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_mul_in_ruleterm1943);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1024:8: lv_op_1_2= rulemul_op_div
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_divParserRuleCall_1_0_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_div_in_ruleterm1962);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1039:8: lv_op_1_3= rulemul_op_dotmul
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotmulParserRuleCall_1_0_0_2()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotmul_in_ruleterm1981);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1054:8: lv_op_1_4= rulemul_op_dotdiv
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotdivParserRuleCall_1_0_0_3()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotdiv_in_ruleterm2000);
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

            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1072:2: ( (lv_Factor_2_0= rulefactor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1073:1: (lv_Factor_2_0= rulefactor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1073:1: (lv_Factor_2_0= rulefactor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1074:3: lv_Factor_2_0= rulefactor
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTermAccess().getFactorFactorParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulefactor_in_ruleterm2024);
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
            	    break loop18;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1098:1: entryRulefactor returns [EObject current=null] : iv_rulefactor= rulefactor EOF ;
    public final EObject entryRulefactor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefactor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1099:2: (iv_rulefactor= rulefactor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1100:2: iv_rulefactor= rulefactor EOF
            {
             newCompositeNode(grammarAccess.getFactorRule()); 
            pushFollow(FOLLOW_rulefactor_in_entryRulefactor2062);
            iv_rulefactor=rulefactor();

            state._fsp--;

             current =iv_rulefactor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefactor2072); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1107:1: rulefactor returns [EObject current=null] : (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) ;
    public final EObject rulefactor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject this_primary_0 = null;

        EObject lv_Primary_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1110:28: ( (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1111:1: (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1111:1: (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1112:5: this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getFactorAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleprimary_in_rulefactor2119);
            this_primary_0=ruleprimary();

            state._fsp--;

             
                    current = this_primary_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1120:1: ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=24 && LA20_0<=25)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1120:2: (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1120:2: (otherlv_1= '^' | otherlv_2= '.^' )
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==24) ) {
                        alt19=1;
                    }
                    else if ( (LA19_0==25) ) {
                        alt19=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1120:4: otherlv_1= '^'
                            {
                            otherlv_1=(Token)match(input,24,FOLLOW_24_in_rulefactor2132); 

                                	newLeafNode(otherlv_1, grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1125:7: otherlv_2= '.^'
                            {
                            otherlv_2=(Token)match(input,25,FOLLOW_25_in_rulefactor2150); 

                                	newLeafNode(otherlv_2, grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1129:2: ( (lv_Primary_3_0= ruleprimary ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1130:1: (lv_Primary_3_0= ruleprimary )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1130:1: (lv_Primary_3_0= ruleprimary )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1131:3: lv_Primary_3_0= ruleprimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getFactorAccess().getPrimaryPrimaryParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleprimary_in_rulefactor2172);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1155:1: entryRuleprimary returns [EObject current=null] : iv_ruleprimary= ruleprimary EOF ;
    public final EObject entryRuleprimary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleprimary = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1156:2: (iv_ruleprimary= ruleprimary EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1157:2: iv_ruleprimary= ruleprimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_ruleprimary_in_entryRuleprimary2210);
            iv_ruleprimary=ruleprimary();

            state._fsp--;

             current =iv_ruleprimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleprimary2220); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1164:1: ruleprimary returns [EObject current=null] : ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1167:28: ( ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1168:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1168:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )
            int alt22=12;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1168:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1168:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1169:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1169:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1170:3: lv_num_0_0= RULE_UNSIGNED_NUMBER
                    {
                    lv_num_0_0=(Token)match(input,RULE_UNSIGNED_NUMBER,FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary2262); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1187:6: ( (lv_int_1_0= RULE_INT ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1187:6: ( (lv_int_1_0= RULE_INT ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1188:1: (lv_int_1_0= RULE_INT )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1188:1: (lv_int_1_0= RULE_INT )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1189:3: lv_int_1_0= RULE_INT
                    {
                    lv_int_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleprimary2290); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1206:6: ( (lv_str_2_0= RULE_STRING ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1206:6: ( (lv_str_2_0= RULE_STRING ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1207:1: (lv_str_2_0= RULE_STRING )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1207:1: (lv_str_2_0= RULE_STRING )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1208:3: lv_str_2_0= RULE_STRING
                    {
                    lv_str_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleprimary2318); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1225:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1225:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1226:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1226:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1227:3: lv_Bool_3_0= RULE_BOOL_VAL
                    {
                    lv_Bool_3_0=(Token)match(input,RULE_BOOL_VAL,FOLLOW_RULE_BOOL_VAL_in_ruleprimary2346); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1245:5: this_name_Function_4= rulename_Function
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getName_FunctionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_rulename_Function_in_ruleprimary2379);
                    this_name_Function_4=rulename_Function();

                    state._fsp--;

                     
                            current = this_name_Function_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1255:5: this_initial_ref_5= ruleinitial_ref
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getInitial_refParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleinitial_ref_in_ruleprimary2406);
                    this_initial_ref_5=ruleinitial_ref();

                    state._fsp--;

                     
                            current = this_initial_ref_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1265:5: this_ExprDer_6= ruleExprDer
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExprDerParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleExprDer_in_ruleprimary2433);
                    this_ExprDer_6=ruleExprDer();

                    state._fsp--;

                     
                            current = this_ExprDer_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1274:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1274:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1275:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1275:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1276:3: lv_Component_reference_7_0= rulecomponent_reference
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getComponent_referenceComponent_referenceParserRuleCall_7_0()); 
                    	    
                    pushFollow(FOLLOW_rulecomponent_reference_in_ruleprimary2459);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1293:6: (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1293:6: (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1293:8: otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')'
                    {
                    otherlv_8=(Token)match(input,26,FOLLOW_26_in_ruleprimary2478); 

                        	newLeafNode(otherlv_8, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_8_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1297:1: ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1298:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1298:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1299:3: lv_output_expr_list_9_0= ruleoutput_expression_list
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getOutput_expr_listOutput_expression_listParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleoutput_expression_list_in_ruleprimary2499);
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

                    otherlv_10=(Token)match(input,27,FOLLOW_27_in_ruleprimary2511); 

                        	newLeafNode(otherlv_10, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_8_2());
                        

                    }


                    }
                    break;
                case 10 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1320:6: (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1320:6: (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1320:8: otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']'
                    {
                    otherlv_11=(Token)match(input,28,FOLLOW_28_in_ruleprimary2531); 

                        	newLeafNode(otherlv_11, grammarAccess.getPrimaryAccess().getLeftSquareBracketKeyword_9_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1324:1: ( (lv_Expre_list_12_0= ruleexpression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1325:1: (lv_Expre_list_12_0= ruleexpression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1325:1: (lv_Expre_list_12_0= ruleexpression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1326:3: lv_Expre_list_12_0= ruleexpression_list
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpre_listExpression_listParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2552);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1342:2: (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==29) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1342:4: otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    {
                    	    otherlv_13=(Token)match(input,29,FOLLOW_29_in_ruleprimary2565); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getPrimaryAccess().getSemicolonKeyword_9_2_0());
                    	        
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1346:1: ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1347:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    {
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1347:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1348:3: lv_Expression_list_14_0= ruleexpression_list
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listExpression_listParserRuleCall_9_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2586);
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
                    	    break loop21;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,30,FOLLOW_30_in_ruleprimary2600); 

                        	newLeafNode(otherlv_15, grammarAccess.getPrimaryAccess().getRightSquareBracketKeyword_9_3());
                        

                    }


                    }
                    break;
                case 11 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1369:6: (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1369:6: (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1369:8: otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}'
                    {
                    otherlv_16=(Token)match(input,31,FOLLOW_31_in_ruleprimary2620); 

                        	newLeafNode(otherlv_16, grammarAccess.getPrimaryAccess().getLeftCurlyBracketKeyword_10_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1373:1: ( (lv_f_arguments_17_0= rulefunction_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1374:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1374:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1375:3: lv_f_arguments_17_0= rulefunction_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getF_argumentsFunction_argumentsParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_ruleprimary2641);
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

                    otherlv_18=(Token)match(input,32,FOLLOW_32_in_ruleprimary2653); 

                        	newLeafNode(otherlv_18, grammarAccess.getPrimaryAccess().getRightCurlyBracketKeyword_10_2());
                        

                    }


                    }
                    break;
                case 12 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1396:6: ( (lv_End_19_0= 'end' ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1396:6: ( (lv_End_19_0= 'end' ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1397:1: (lv_End_19_0= 'end' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1397:1: (lv_End_19_0= 'end' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1398:3: lv_End_19_0= 'end'
                    {
                    lv_End_19_0=(Token)match(input,33,FOLLOW_33_in_ruleprimary2678); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1419:1: entryRulename_Function returns [EObject current=null] : iv_rulename_Function= rulename_Function EOF ;
    public final EObject entryRulename_Function() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename_Function = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1420:2: (iv_rulename_Function= rulename_Function EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1421:2: iv_rulename_Function= rulename_Function EOF
            {
             newCompositeNode(grammarAccess.getName_FunctionRule()); 
            pushFollow(FOLLOW_rulename_Function_in_entryRulename_Function2727);
            iv_rulename_Function=rulename_Function();

            state._fsp--;

             current =iv_rulename_Function; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_Function2737); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1428:1: rulename_Function returns [EObject current=null] : (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) ;
    public final EObject rulename_Function() throws RecognitionException {
        EObject current = null;

        EObject this_name_0 = null;

        EObject lv_Function_call_args_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1431:28: ( (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1432:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1432:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1433:5: this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            {
             
                    newCompositeNode(grammarAccess.getName_FunctionAccess().getNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulename_in_rulename_Function2784);
            this_name_0=rulename();

            state._fsp--;

             
                    current = this_name_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1441:1: ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1442:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1442:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1443:3: lv_Function_call_args_1_0= rulefunction_call_args
            {
             
            	        newCompositeNode(grammarAccess.getName_FunctionAccess().getFunction_call_argsFunction_call_argsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_rulename_Function2804);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1467:1: entryRuleinitial_ref returns [EObject current=null] : iv_ruleinitial_ref= ruleinitial_ref EOF ;
    public final EObject entryRuleinitial_ref() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitial_ref = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1468:2: (iv_ruleinitial_ref= ruleinitial_ref EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1469:2: iv_ruleinitial_ref= ruleinitial_ref EOF
            {
             newCompositeNode(grammarAccess.getInitial_refRule()); 
            pushFollow(FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2840);
            iv_ruleinitial_ref=ruleinitial_ref();

            state._fsp--;

             current =iv_ruleinitial_ref; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleinitial_ref2850); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1476:1: ruleinitial_ref returns [EObject current=null] : (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args ) ;
    public final EObject ruleinitial_ref() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject this_function_call_args_1 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1479:28: ( (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1480:1: (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1480:1: (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1480:3: otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args
            {
            otherlv_0=(Token)match(input,34,FOLLOW_34_in_ruleinitial_ref2887); 

                	newLeafNode(otherlv_0, grammarAccess.getInitial_refAccess().getInitialKeyword_0());
                
             
                    newCompositeNode(grammarAccess.getInitial_refAccess().getFunction_call_argsParserRuleCall_1()); 
                
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleinitial_ref2909);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1501:1: entryRuleExprDer returns [EObject current=null] : iv_ruleExprDer= ruleExprDer EOF ;
    public final EObject entryRuleExprDer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExprDer = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1502:2: (iv_ruleExprDer= ruleExprDer EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1503:2: iv_ruleExprDer= ruleExprDer EOF
            {
             newCompositeNode(grammarAccess.getExprDerRule()); 
            pushFollow(FOLLOW_ruleExprDer_in_entryRuleExprDer2944);
            iv_ruleExprDer=ruleExprDer();

            state._fsp--;

             current =iv_ruleExprDer; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExprDer2954); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1510:1: ruleExprDer returns [EObject current=null] : (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) ;
    public final EObject ruleExprDer() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_functionArgs_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1513:28: ( (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1514:1: (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1514:1: (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1514:3: otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            {
            otherlv_0=(Token)match(input,35,FOLLOW_35_in_ruleExprDer2991); 

                	newLeafNode(otherlv_0, grammarAccess.getExprDerAccess().getDerKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1518:1: ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1519:1: (lv_functionArgs_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1519:1: (lv_functionArgs_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1520:3: lv_functionArgs_1_0= rulefunction_call_args
            {
             
            	        newCompositeNode(grammarAccess.getExprDerAccess().getFunctionArgsFunction_call_argsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleExprDer3012);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1544:1: entryRulefunction_call_args returns [EObject current=null] : iv_rulefunction_call_args= rulefunction_call_args EOF ;
    public final EObject entryRulefunction_call_args() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_call_args = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1545:2: (iv_rulefunction_call_args= rulefunction_call_args EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1546:2: iv_rulefunction_call_args= rulefunction_call_args EOF
            {
             newCompositeNode(grammarAccess.getFunction_call_argsRule()); 
            pushFollow(FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args3048);
            iv_rulefunction_call_args=rulefunction_call_args();

            state._fsp--;

             current =iv_rulefunction_call_args; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_call_args3058); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1553:1: rulefunction_call_args returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' ) ;
    public final EObject rulefunction_call_args() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_f_arg_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1556:28: ( ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1557:1: ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1557:1: ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1557:2: () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')'
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1557:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1558:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_rulefunction_call_args3104); 

                	newLeafNode(otherlv_1, grammarAccess.getFunction_call_argsAccess().getLeftParenthesisKeyword_1());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1567:1: ( (lv_f_arg_2_0= rulefunction_arguments ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=RULE_UNSIGNED_NUMBER && LA23_0<=RULE_IDENT)||LA23_0==16||LA23_0==23||LA23_0==26||LA23_0==28||LA23_0==31||(LA23_0>=33 && LA23_0<=35)||LA23_0==37||(LA23_0>=44 && LA23_0<=47)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1568:1: (lv_f_arg_2_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1568:1: (lv_f_arg_2_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1569:3: lv_f_arg_2_0= rulefunction_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_call_argsAccess().getF_argFunction_argumentsParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_rulefunction_call_args3125);
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

            otherlv_3=(Token)match(input,27,FOLLOW_27_in_rulefunction_call_args3138); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1597:1: entryRuleexpression_list returns [EObject current=null] : iv_ruleexpression_list= ruleexpression_list EOF ;
    public final EObject entryRuleexpression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1598:2: (iv_ruleexpression_list= ruleexpression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1599:2: iv_ruleexpression_list= ruleexpression_list EOF
            {
             newCompositeNode(grammarAccess.getExpression_listRule()); 
            pushFollow(FOLLOW_ruleexpression_list_in_entryRuleexpression_list3174);
            iv_ruleexpression_list=ruleexpression_list();

            state._fsp--;

             current =iv_ruleexpression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression_list3184); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1606:1: ruleexpression_list returns [EObject current=null] : ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) ;
    public final EObject ruleexpression_list() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expr_0_0 = null;

        EObject lv_Expre_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1609:28: ( ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1610:1: ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1610:1: ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1610:2: ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1610:2: ( (lv_expr_0_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1611:1: (lv_expr_0_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1611:1: (lv_expr_0_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1612:3: lv_expr_0_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getExpression_listAccess().getExprExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list3230);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1628:2: (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==36) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1628:4: otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) )
            	    {
            	    otherlv_1=(Token)match(input,36,FOLLOW_36_in_ruleexpression_list3243); 

            	        	newLeafNode(otherlv_1, grammarAccess.getExpression_listAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1632:1: ( (lv_Expre_2_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1633:1: (lv_Expre_2_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1633:1: (lv_Expre_2_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1634:3: lv_Expre_2_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression_listAccess().getExpreExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list3264);
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
    // $ANTLR end "ruleexpression_list"


    // $ANTLR start "entryRulename"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1658:1: entryRulename returns [EObject current=null] : iv_rulename= rulename EOF ;
    public final EObject entryRulename() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1659:2: (iv_rulename= rulename EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1660:2: iv_rulename= rulename EOF
            {
             newCompositeNode(grammarAccess.getNameRule()); 
            pushFollow(FOLLOW_rulename_in_entryRulename3302);
            iv_rulename=rulename();

            state._fsp--;

             current =iv_rulename; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename3312); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1667:1: rulename returns [EObject current=null] : ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) ;
    public final EObject rulename() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_ID_1_0=null;
        Token otherlv_2=null;
        Token lv_nam_ID_3_0=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1670:28: ( ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1671:1: ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1671:1: ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1671:2: (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1671:2: (otherlv_0= '.' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==37) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1671:4: otherlv_0= '.'
                    {
                    otherlv_0=(Token)match(input,37,FOLLOW_37_in_rulename3350); 

                        	newLeafNode(otherlv_0, grammarAccess.getNameAccess().getFullStopKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1675:3: ( (lv_name_ID_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1676:1: (lv_name_ID_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1676:1: (lv_name_ID_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1677:3: lv_name_ID_1_0= RULE_IDENT
            {
            lv_name_ID_1_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3369); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1693:2: (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==37) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1693:4: otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    {
            	    otherlv_2=(Token)match(input,37,FOLLOW_37_in_rulename3387); 

            	        	newLeafNode(otherlv_2, grammarAccess.getNameAccess().getFullStopKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1697:1: ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1698:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1698:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1699:3: lv_nam_ID_3_0= RULE_IDENT
            	    {
            	    lv_nam_ID_3_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3404); 

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
    // $ANTLR end "rulename"


    // $ANTLR start "entryRulecomponent_reference"
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1723:1: entryRulecomponent_reference returns [EObject current=null] : iv_rulecomponent_reference= rulecomponent_reference EOF ;
    public final EObject entryRulecomponent_reference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_reference = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1724:2: (iv_rulecomponent_reference= rulecomponent_reference EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1725:2: iv_rulecomponent_reference= rulecomponent_reference EOF
            {
             newCompositeNode(grammarAccess.getComponent_referenceRule()); 
            pushFollow(FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3447);
            iv_rulecomponent_reference=rulecomponent_reference();

            state._fsp--;

             current =iv_rulecomponent_reference; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_reference3457); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1732:1: rulecomponent_reference returns [EObject current=null] : ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1735:28: ( ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1736:1: ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1736:1: ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1736:2: (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1736:2: (otherlv_0= '.' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==37) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1736:4: otherlv_0= '.'
                    {
                    otherlv_0=(Token)match(input,37,FOLLOW_37_in_rulecomponent_reference3495); 

                        	newLeafNode(otherlv_0, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1740:3: ( (lv_ref_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1741:1: (lv_ref_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1741:1: (lv_ref_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1742:3: lv_ref_1_0= RULE_IDENT
            {
            lv_ref_1_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3514); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1758:2: ( (lv_subscripts1_2_0= rulearray_subscripts ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==28) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1759:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1759:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1760:3: lv_subscripts1_2_0= rulearray_subscripts
                    {
                     
                    	        newCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscripts1Array_subscriptsParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3540);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1776:3: (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==37) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1776:5: otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    {
            	    otherlv_3=(Token)match(input,37,FOLLOW_37_in_rulecomponent_reference3554); 

            	        	newLeafNode(otherlv_3, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_3_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1780:1: ( (lv_ref1_4_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1781:1: (lv_ref1_4_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1781:1: (lv_ref1_4_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1782:3: lv_ref1_4_0= RULE_IDENT
            	    {
            	    lv_ref1_4_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3571); 

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

            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1798:2: ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    int alt29=2;
            	    int LA29_0 = input.LA(1);

            	    if ( (LA29_0==28) ) {
            	        alt29=1;
            	    }
            	    switch (alt29) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1799:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1799:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1800:3: lv_subscripts_5_0= rulearray_subscripts
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscriptsArray_subscriptsParserRuleCall_3_2_0()); 
            	            	    
            	            pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3597);
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
            	    break loop30;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1824:1: entryRuleoutput_expression_list returns [EObject current=null] : iv_ruleoutput_expression_list= ruleoutput_expression_list EOF ;
    public final EObject entryRuleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleoutput_expression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1825:2: (iv_ruleoutput_expression_list= ruleoutput_expression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1826:2: iv_ruleoutput_expression_list= ruleoutput_expression_list EOF
            {
             newCompositeNode(grammarAccess.getOutput_expression_listRule()); 
            pushFollow(FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3636);
            iv_ruleoutput_expression_list=ruleoutput_expression_list();

            state._fsp--;

             current =iv_ruleoutput_expression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleoutput_expression_list3646); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1833:1: ruleoutput_expression_list returns [EObject current=null] : ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) ;
    public final EObject ruleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_epr_1_0 = null;

        EObject lv_Expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1836:28: ( ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1837:1: ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1837:1: ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1837:2: () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1837:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1838:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOutput_expression_listAccess().getOutput_expression_listAction_0(),
                        current);
                

            }

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1843:2: ( (lv_epr_1_0= ruleexpression ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_UNSIGNED_NUMBER && LA31_0<=RULE_IDENT)||LA31_0==16||LA31_0==23||LA31_0==26||LA31_0==28||LA31_0==31||(LA31_0>=33 && LA31_0<=35)||LA31_0==37||(LA31_0>=44 && LA31_0<=47)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1844:1: (lv_epr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1844:1: (lv_epr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1845:3: lv_epr_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getOutput_expression_listAccess().getEprExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3701);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1861:3: (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==36) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1861:5: otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )?
            	    {
            	    otherlv_2=(Token)match(input,36,FOLLOW_36_in_ruleoutput_expression_list3715); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1865:1: ( (lv_Expr_3_0= ruleexpression ) )?
            	    int alt32=2;
            	    int LA32_0 = input.LA(1);

            	    if ( ((LA32_0>=RULE_UNSIGNED_NUMBER && LA32_0<=RULE_IDENT)||LA32_0==16||LA32_0==23||LA32_0==26||LA32_0==28||LA32_0==31||(LA32_0>=33 && LA32_0<=35)||LA32_0==37||(LA32_0>=44 && LA32_0<=47)) ) {
            	        alt32=1;
            	    }
            	    switch (alt32) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1866:1: (lv_Expr_3_0= ruleexpression )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1866:1: (lv_Expr_3_0= ruleexpression )
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1867:3: lv_Expr_3_0= ruleexpression
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getOutput_expression_listAccess().getExprExpressionParserRuleCall_2_1_0()); 
            	            	    
            	            pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3736);
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
            	    break loop33;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1891:1: entryRulearray_subscripts returns [EObject current=null] : iv_rulearray_subscripts= rulearray_subscripts EOF ;
    public final EObject entryRulearray_subscripts() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearray_subscripts = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1892:2: (iv_rulearray_subscripts= rulearray_subscripts EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1893:2: iv_rulearray_subscripts= rulearray_subscripts EOF
            {
             newCompositeNode(grammarAccess.getArray_subscriptsRule()); 
            pushFollow(FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3775);
            iv_rulearray_subscripts=rulearray_subscripts();

            state._fsp--;

             current =iv_rulearray_subscripts; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearray_subscripts3785); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1900:1: rulearray_subscripts returns [EObject current=null] : (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' ) ;
    public final EObject rulearray_subscripts() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_Sub_1_0 = null;

        EObject lv_Subscript_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1903:28: ( (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1904:1: (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1904:1: (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1904:3: otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_rulearray_subscripts3822); 

                	newLeafNode(otherlv_0, grammarAccess.getArray_subscriptsAccess().getLeftSquareBracketKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1908:1: ( (lv_Sub_1_0= rulesubscript ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1909:1: (lv_Sub_1_0= rulesubscript )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1909:1: (lv_Sub_1_0= rulesubscript )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1910:3: lv_Sub_1_0= rulesubscript
            {
             
            	        newCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubSubscriptParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3843);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1926:2: (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==36) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1926:4: otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) )
            	    {
            	    otherlv_2=(Token)match(input,36,FOLLOW_36_in_rulearray_subscripts3856); 

            	        	newLeafNode(otherlv_2, grammarAccess.getArray_subscriptsAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1930:1: ( (lv_Subscript_3_0= rulesubscript ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1931:1: (lv_Subscript_3_0= rulesubscript )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1931:1: (lv_Subscript_3_0= rulesubscript )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1932:3: lv_Subscript_3_0= rulesubscript
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubscriptSubscriptParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3877);
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
            	    break loop34;
                }
            } while (true);

            otherlv_4=(Token)match(input,30,FOLLOW_30_in_rulearray_subscripts3891); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1960:1: entryRulesubscript returns [EObject current=null] : iv_rulesubscript= rulesubscript EOF ;
    public final EObject entryRulesubscript() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesubscript = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1961:2: (iv_rulesubscript= rulesubscript EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1962:2: iv_rulesubscript= rulesubscript EOF
            {
             newCompositeNode(grammarAccess.getSubscriptRule()); 
            pushFollow(FOLLOW_rulesubscript_in_entryRulesubscript3927);
            iv_rulesubscript=rulesubscript();

            state._fsp--;

             current =iv_rulesubscript; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesubscript3937); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1969:1: rulesubscript returns [EObject current=null] : ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulesubscript() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1972:28: ( ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1973:1: ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1973:1: ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==17) ) {
                alt35=1;
            }
            else if ( ((LA35_0>=RULE_UNSIGNED_NUMBER && LA35_0<=RULE_IDENT)||LA35_0==16||LA35_0==23||LA35_0==26||LA35_0==28||LA35_0==31||(LA35_0>=33 && LA35_0<=35)||LA35_0==37||(LA35_0>=44 && LA35_0<=47)) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1973:2: ( () otherlv_1= ':' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1973:2: ( () otherlv_1= ':' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1973:3: () otherlv_1= ':'
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1973:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1974:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getSubscriptAccess().getSubscriptAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,17,FOLLOW_17_in_rulesubscript3984); 

                        	newLeafNode(otherlv_1, grammarAccess.getSubscriptAccess().getColonKeyword_0_1());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1984:6: ( (lv_expr_2_0= ruleexpression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1984:6: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1985:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1985:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1986:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSubscriptAccess().getExprExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulesubscript4012);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2010:1: entryRulefunction_arguments returns [EObject current=null] : iv_rulefunction_arguments= rulefunction_arguments EOF ;
    public final EObject entryRulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2011:2: (iv_rulefunction_arguments= rulefunction_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2012:2: iv_rulefunction_arguments= rulefunction_arguments EOF
            {
             newCompositeNode(grammarAccess.getFunction_argumentsRule()); 
            pushFollow(FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments4048);
            iv_rulefunction_arguments=rulefunction_arguments();

            state._fsp--;

             current =iv_rulefunction_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_arguments4058); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2019:1: rulefunction_arguments returns [EObject current=null] : ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) ;
    public final EObject rulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject lv_ArgExp_1_0 = null;

        EObject lv_Fun_Arg_Expr_2_0 = null;

        EObject lv_Fun_Arg_For_3_0 = null;

        EObject lv_name_arg_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2022:28: ( ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2023:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2023:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( ((LA37_0>=RULE_UNSIGNED_NUMBER && LA37_0<=RULE_BOOL_VAL)||LA37_0==16||LA37_0==23||LA37_0==26||LA37_0==28||LA37_0==31||(LA37_0>=33 && LA37_0<=35)||LA37_0==37||(LA37_0>=44 && LA37_0<=47)) ) {
                alt37=1;
            }
            else if ( (LA37_0==RULE_IDENT) ) {
                int LA37_2 = input.LA(2);

                if ( (LA37_2==EOF||LA37_2==17||(LA37_2>=21 && LA37_2<=22)||(LA37_2>=24 && LA37_2<=28)||LA37_2==32||(LA37_2>=36 && LA37_2<=38)||(LA37_2>=40 && LA37_2<=53)) ) {
                    alt37=1;
                }
                else if ( (LA37_2==14) ) {
                    alt37=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2023:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2023:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2023:3: () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2023:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2024:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getFunction_argumentsAccess().getFunction_argumentsAction_0_0(),
                                current);
                        

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2029:2: ( (lv_ArgExp_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2030:1: (lv_ArgExp_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2030:1: (lv_ArgExp_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2031:3: lv_ArgExp_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getArgExpExpressionParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefunction_arguments4114);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2047:2: ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    int alt36=3;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==36) ) {
                        alt36=1;
                    }
                    else if ( (LA36_0==38) ) {
                        alt36=2;
                    }
                    switch (alt36) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2047:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2047:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2048:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2048:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2049:3: lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp
                            {
                             
                            	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments4136);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2066:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2066:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2067:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2067:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2068:3: lv_Fun_Arg_For_3_0= ruleFun_Arguments_for
                            {
                             
                            	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments4163);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2085:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2085:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2086:1: (lv_name_arg_4_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2086:1: (lv_name_arg_4_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2087:3: lv_name_arg_4_0= rulenamed_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getName_argNamed_argumentsParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulefunction_arguments4193);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2111:1: entryRuleFun_Arguments_exp returns [EObject current=null] : iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF ;
    public final EObject entryRuleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_exp = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2112:2: (iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2113:2: iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF
            {
             newCompositeNode(grammarAccess.getFun_Arguments_expRule()); 
            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp4229);
            iv_ruleFun_Arguments_exp=ruleFun_Arguments_exp();

            state._fsp--;

             current =iv_ruleFun_Arguments_exp; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_exp4239); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2120:1: ruleFun_Arguments_exp returns [EObject current=null] : (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) ;
    public final EObject ruleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_Args_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2123:28: ( (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2124:1: (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2124:1: (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2124:3: otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) )
            {
            otherlv_0=(Token)match(input,36,FOLLOW_36_in_ruleFun_Arguments_exp4276); 

                	newLeafNode(otherlv_0, grammarAccess.getFun_Arguments_expAccess().getCommaKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2128:1: ( (lv_Args_1_0= rulefunction_arguments ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2129:1: (lv_Args_1_0= rulefunction_arguments )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2129:1: (lv_Args_1_0= rulefunction_arguments )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2130:3: lv_Args_1_0= rulefunction_arguments
            {
             
            	        newCompositeNode(grammarAccess.getFun_Arguments_expAccess().getArgsFunction_argumentsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp4297);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2154:1: entryRuleFun_Arguments_for returns [EObject current=null] : iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF ;
    public final EObject entryRuleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_for = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2155:2: (iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2156:2: iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF
            {
             newCompositeNode(grammarAccess.getFun_Arguments_forRule()); 
            pushFollow(FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for4333);
            iv_ruleFun_Arguments_for=ruleFun_Arguments_for();

            state._fsp--;

             current =iv_ruleFun_Arguments_for; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_for4343); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2163:1: ruleFun_Arguments_for returns [EObject current=null] : (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) ;
    public final EObject ruleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_For_indices_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2166:28: ( (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2167:1: (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2167:1: (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2167:3: otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) )
            {
            otherlv_0=(Token)match(input,38,FOLLOW_38_in_ruleFun_Arguments_for4380); 

                	newLeafNode(otherlv_0, grammarAccess.getFun_Arguments_forAccess().getForKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2171:1: ( (lv_For_indices_1_0= rulefor_indices ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2172:1: (lv_For_indices_1_0= rulefor_indices )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2172:1: (lv_For_indices_1_0= rulefor_indices )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2173:3: lv_For_indices_1_0= rulefor_indices
            {
             
            	        newCompositeNode(grammarAccess.getFun_Arguments_forAccess().getFor_indicesFor_indicesParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4401);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2197:1: entryRulenamed_arguments returns [EObject current=null] : iv_rulenamed_arguments= rulenamed_arguments EOF ;
    public final EObject entryRulenamed_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2198:2: (iv_rulenamed_arguments= rulenamed_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2199:2: iv_rulenamed_arguments= rulenamed_arguments EOF
            {
             newCompositeNode(grammarAccess.getNamed_argumentsRule()); 
            pushFollow(FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4437);
            iv_rulenamed_arguments=rulenamed_arguments();

            state._fsp--;

             current =iv_rulenamed_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_arguments4447); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2206:1: rulenamed_arguments returns [EObject current=null] : (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) ;
    public final EObject rulenamed_arguments() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_named_argument_0 = null;

        EObject lv_Named_arguments_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2209:28: ( (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2210:1: (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2210:1: (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2211:5: this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulenamed_argument_in_rulenamed_arguments4494);
            this_named_argument_0=rulenamed_argument();

            state._fsp--;

             
                    current = this_named_argument_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2219:1: (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==36) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2219:3: otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    {
                    otherlv_1=(Token)match(input,36,FOLLOW_36_in_rulenamed_arguments4506); 

                        	newLeafNode(otherlv_1, grammarAccess.getNamed_argumentsAccess().getCommaKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2223:1: ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2224:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2224:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2225:3: lv_Named_arguments_2_0= rulenamed_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentsNamed_argumentsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulenamed_arguments4527);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2249:1: entryRulenamed_argument returns [EObject current=null] : iv_rulenamed_argument= rulenamed_argument EOF ;
    public final EObject entryRulenamed_argument() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_argument = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2250:2: (iv_rulenamed_argument= rulenamed_argument EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2251:2: iv_rulenamed_argument= rulenamed_argument EOF
            {
             newCompositeNode(grammarAccess.getNamed_argumentRule()); 
            pushFollow(FOLLOW_rulenamed_argument_in_entryRulenamed_argument4565);
            iv_rulenamed_argument=rulenamed_argument();

            state._fsp--;

             current =iv_rulenamed_argument; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_argument4575); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2258:1: rulenamed_argument returns [EObject current=null] : ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulenamed_argument() throws RecognitionException {
        EObject current = null;

        Token lv_arg_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2261:28: ( ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:2: ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:2: ( (lv_arg_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2263:1: (lv_arg_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2263:1: (lv_arg_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2264:3: lv_arg_0_0= RULE_IDENT
            {
            lv_arg_0_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulenamed_argument4617); 

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

            otherlv_1=(Token)match(input,14,FOLLOW_14_in_rulenamed_argument4634); 

                	newLeafNode(otherlv_1, grammarAccess.getNamed_argumentAccess().getEqualsSignKeyword_1());
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2284:1: ( (lv_expr_2_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2285:1: (lv_expr_2_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2285:1: (lv_expr_2_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2286:3: lv_expr_2_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getNamed_argumentAccess().getExprExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulenamed_argument4655);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2310:1: entryRulefor_indices returns [EObject current=null] : iv_rulefor_indices= rulefor_indices EOF ;
    public final EObject entryRulefor_indices() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_indices = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2311:2: (iv_rulefor_indices= rulefor_indices EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2312:2: iv_rulefor_indices= rulefor_indices EOF
            {
             newCompositeNode(grammarAccess.getFor_indicesRule()); 
            pushFollow(FOLLOW_rulefor_indices_in_entryRulefor_indices4691);
            iv_rulefor_indices=rulefor_indices();

            state._fsp--;

             current =iv_rulefor_indices; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_indices4701); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2319:1: rulefor_indices returns [EObject current=null] : (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) ;
    public final EObject rulefor_indices() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_for_index_0 = null;

        EObject lv_For_index_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2322:28: ( (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2323:1: (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2323:1: (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2324:5: this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4748);
            this_for_index_0=rulefor_index();

            state._fsp--;

             
                    current = this_for_index_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2332:1: (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==36) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2332:3: otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) )
            	    {
            	    otherlv_1=(Token)match(input,36,FOLLOW_36_in_rulefor_indices4760); 

            	        	newLeafNode(otherlv_1, grammarAccess.getFor_indicesAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2336:1: ( (lv_For_index_2_0= rulefor_index ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2337:1: (lv_For_index_2_0= rulefor_index )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2337:1: (lv_For_index_2_0= rulefor_index )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2338:3: lv_For_index_2_0= rulefor_index
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexFor_indexParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4781);
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
            	    break loop39;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2362:1: entryRulefor_index returns [EObject current=null] : iv_rulefor_index= rulefor_index EOF ;
    public final EObject entryRulefor_index() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_index = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2363:2: (iv_rulefor_index= rulefor_index EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2364:2: iv_rulefor_index= rulefor_index EOF
            {
             newCompositeNode(grammarAccess.getFor_indexRule()); 
            pushFollow(FOLLOW_rulefor_index_in_entryRulefor_index4819);
            iv_rulefor_index=rulefor_index();

            state._fsp--;

             current =iv_rulefor_index; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_index4829); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2371:1: rulefor_index returns [EObject current=null] : ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) ;
    public final EObject rulefor_index() throws RecognitionException {
        EObject current = null;

        Token lv_index_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2374:28: ( ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2375:1: ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2375:1: ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2375:2: ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2375:2: ( (lv_index_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2376:1: (lv_index_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2376:1: (lv_index_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2377:3: lv_index_0_0= RULE_IDENT
            {
            lv_index_0_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulefor_index4871); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2393:2: (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==39) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2393:4: otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) )
                    {
                    otherlv_1=(Token)match(input,39,FOLLOW_39_in_rulefor_index4889); 

                        	newLeafNode(otherlv_1, grammarAccess.getFor_indexAccess().getInKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2397:1: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2398:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2398:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2399:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFor_indexAccess().getExprExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefor_index4910);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2423:1: entryRulemul_op_mul returns [String current=null] : iv_rulemul_op_mul= rulemul_op_mul EOF ;
    public final String entryRulemul_op_mul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_mul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2424:2: (iv_rulemul_op_mul= rulemul_op_mul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2425:2: iv_rulemul_op_mul= rulemul_op_mul EOF
            {
             newCompositeNode(grammarAccess.getMul_op_mulRule()); 
            pushFollow(FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4949);
            iv_rulemul_op_mul=rulemul_op_mul();

            state._fsp--;

             current =iv_rulemul_op_mul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_mul4960); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2432:1: rulemul_op_mul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken rulemul_op_mul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2435:28: (kw= '*' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2437:2: kw= '*'
            {
            kw=(Token)match(input,40,FOLLOW_40_in_rulemul_op_mul4997); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2450:1: entryRulemul_op_div returns [String current=null] : iv_rulemul_op_div= rulemul_op_div EOF ;
    public final String entryRulemul_op_div() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_div = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2451:2: (iv_rulemul_op_div= rulemul_op_div EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2452:2: iv_rulemul_op_div= rulemul_op_div EOF
            {
             newCompositeNode(grammarAccess.getMul_op_divRule()); 
            pushFollow(FOLLOW_rulemul_op_div_in_entryRulemul_op_div5037);
            iv_rulemul_op_div=rulemul_op_div();

            state._fsp--;

             current =iv_rulemul_op_div.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_div5048); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2459:1: rulemul_op_div returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '/' ;
    public final AntlrDatatypeRuleToken rulemul_op_div() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2462:28: (kw= '/' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2464:2: kw= '/'
            {
            kw=(Token)match(input,41,FOLLOW_41_in_rulemul_op_div5085); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2477:1: entryRulemul_op_dotmul returns [String current=null] : iv_rulemul_op_dotmul= rulemul_op_dotmul EOF ;
    public final String entryRulemul_op_dotmul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotmul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2478:2: (iv_rulemul_op_dotmul= rulemul_op_dotmul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2479:2: iv_rulemul_op_dotmul= rulemul_op_dotmul EOF
            {
             newCompositeNode(grammarAccess.getMul_op_dotmulRule()); 
            pushFollow(FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul5125);
            iv_rulemul_op_dotmul=rulemul_op_dotmul();

            state._fsp--;

             current =iv_rulemul_op_dotmul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotmul5136); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2486:1: rulemul_op_dotmul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.*' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotmul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2489:28: (kw= '.*' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2491:2: kw= '.*'
            {
            kw=(Token)match(input,42,FOLLOW_42_in_rulemul_op_dotmul5173); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2504:1: entryRulemul_op_dotdiv returns [String current=null] : iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF ;
    public final String entryRulemul_op_dotdiv() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotdiv = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2505:2: (iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2506:2: iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF
            {
             newCompositeNode(grammarAccess.getMul_op_dotdivRule()); 
            pushFollow(FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv5213);
            iv_rulemul_op_dotdiv=rulemul_op_dotdiv();

            state._fsp--;

             current =iv_rulemul_op_dotdiv.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotdiv5224); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2513:1: rulemul_op_dotdiv returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= './' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotdiv() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2516:28: (kw= './' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2518:2: kw= './'
            {
            kw=(Token)match(input,43,FOLLOW_43_in_rulemul_op_dotdiv5261); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2531:1: entryRuleadd_op_plus returns [String current=null] : iv_ruleadd_op_plus= ruleadd_op_plus EOF ;
    public final String entryRuleadd_op_plus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_plus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2532:2: (iv_ruleadd_op_plus= ruleadd_op_plus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2533:2: iv_ruleadd_op_plus= ruleadd_op_plus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_plusRule()); 
            pushFollow(FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus5301);
            iv_ruleadd_op_plus=ruleadd_op_plus();

            state._fsp--;

             current =iv_ruleadd_op_plus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_plus5312); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2540:1: ruleadd_op_plus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_plus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2543:28: (kw= '+' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2545:2: kw= '+'
            {
            kw=(Token)match(input,44,FOLLOW_44_in_ruleadd_op_plus5349); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2558:1: entryRuleadd_op_minus returns [String current=null] : iv_ruleadd_op_minus= ruleadd_op_minus EOF ;
    public final String entryRuleadd_op_minus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_minus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2559:2: (iv_ruleadd_op_minus= ruleadd_op_minus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2560:2: iv_ruleadd_op_minus= ruleadd_op_minus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_minusRule()); 
            pushFollow(FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5389);
            iv_ruleadd_op_minus=ruleadd_op_minus();

            state._fsp--;

             current =iv_ruleadd_op_minus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_minus5400); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2567:1: ruleadd_op_minus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_minus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2570:28: (kw= '-' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2572:2: kw= '-'
            {
            kw=(Token)match(input,45,FOLLOW_45_in_ruleadd_op_minus5437); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2585:1: entryRuleadd_op_dotplus returns [String current=null] : iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF ;
    public final String entryRuleadd_op_dotplus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotplus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2586:2: (iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2587:2: iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_dotplusRule()); 
            pushFollow(FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5477);
            iv_ruleadd_op_dotplus=ruleadd_op_dotplus();

            state._fsp--;

             current =iv_ruleadd_op_dotplus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotplus5488); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2594:1: ruleadd_op_dotplus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotplus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2597:28: (kw= '.+' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2599:2: kw= '.+'
            {
            kw=(Token)match(input,46,FOLLOW_46_in_ruleadd_op_dotplus5525); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2612:1: entryRuleadd_op_dotminus returns [String current=null] : iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF ;
    public final String entryRuleadd_op_dotminus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotminus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2613:2: (iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2614:2: iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_dotminusRule()); 
            pushFollow(FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5565);
            iv_ruleadd_op_dotminus=ruleadd_op_dotminus();

            state._fsp--;

             current =iv_ruleadd_op_dotminus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotminus5576); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2621:1: ruleadd_op_dotminus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotminus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2624:28: (kw= '.-' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2626:2: kw= '.-'
            {
            kw=(Token)match(input,47,FOLLOW_47_in_ruleadd_op_dotminus5613); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2639:1: entryRulerel_op_Less_then returns [String current=null] : iv_rulerel_op_Less_then= rulerel_op_Less_then EOF ;
    public final String entryRulerel_op_Less_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2640:2: (iv_rulerel_op_Less_then= rulerel_op_Less_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2641:2: iv_rulerel_op_Less_then= rulerel_op_Less_then EOF
            {
             newCompositeNode(grammarAccess.getRel_op_Less_thenRule()); 
            pushFollow(FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5653);
            iv_rulerel_op_Less_then=rulerel_op_Less_then();

            state._fsp--;

             current =iv_rulerel_op_Less_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_then5664); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2648:1: rulerel_op_Less_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2651:28: (kw= '<' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2653:2: kw= '<'
            {
            kw=(Token)match(input,48,FOLLOW_48_in_rulerel_op_Less_then5701); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2666:1: entryRulerel_op_Less_equal returns [String current=null] : iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF ;
    public final String entryRulerel_op_Less_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2667:2: (iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2668:2: iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF
            {
             newCompositeNode(grammarAccess.getRel_op_Less_equalRule()); 
            pushFollow(FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5741);
            iv_rulerel_op_Less_equal=rulerel_op_Less_equal();

            state._fsp--;

             current =iv_rulerel_op_Less_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_equal5752); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2675:1: rulerel_op_Less_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<=' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2678:28: (kw= '<=' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2680:2: kw= '<='
            {
            kw=(Token)match(input,49,FOLLOW_49_in_rulerel_op_Less_equal5789); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2693:1: entryRulerel_op_greater_then returns [String current=null] : iv_rulerel_op_greater_then= rulerel_op_greater_then EOF ;
    public final String entryRulerel_op_greater_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2694:2: (iv_rulerel_op_greater_then= rulerel_op_greater_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2695:2: iv_rulerel_op_greater_then= rulerel_op_greater_then EOF
            {
             newCompositeNode(grammarAccess.getRel_op_greater_thenRule()); 
            pushFollow(FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5829);
            iv_rulerel_op_greater_then=rulerel_op_greater_then();

            state._fsp--;

             current =iv_rulerel_op_greater_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_then5840); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2702:1: rulerel_op_greater_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2705:28: (kw= '>' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2707:2: kw= '>'
            {
            kw=(Token)match(input,50,FOLLOW_50_in_rulerel_op_greater_then5877); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2720:1: entryRulerel_op_greater_equal returns [String current=null] : iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF ;
    public final String entryRulerel_op_greater_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2721:2: (iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2722:2: iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF
            {
             newCompositeNode(grammarAccess.getRel_op_greater_equalRule()); 
            pushFollow(FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5917);
            iv_rulerel_op_greater_equal=rulerel_op_greater_equal();

            state._fsp--;

             current =iv_rulerel_op_greater_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_equal5928); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2729:1: rulerel_op_greater_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>=' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2732:28: (kw= '>=' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2734:2: kw= '>='
            {
            kw=(Token)match(input,51,FOLLOW_51_in_rulerel_op_greater_equal5965); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2747:1: entryRulerel_op_assignment returns [String current=null] : iv_rulerel_op_assignment= rulerel_op_assignment EOF ;
    public final String entryRulerel_op_assignment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_assignment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2748:2: (iv_rulerel_op_assignment= rulerel_op_assignment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2749:2: iv_rulerel_op_assignment= rulerel_op_assignment EOF
            {
             newCompositeNode(grammarAccess.getRel_op_assignmentRule()); 
            pushFollow(FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment6005);
            iv_rulerel_op_assignment=rulerel_op_assignment();

            state._fsp--;

             current =iv_rulerel_op_assignment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_assignment6016); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2756:1: rulerel_op_assignment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '==' ;
    public final AntlrDatatypeRuleToken rulerel_op_assignment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2759:28: (kw= '==' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2761:2: kw= '=='
            {
            kw=(Token)match(input,52,FOLLOW_52_in_rulerel_op_assignment6053); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2774:1: entryRulerel_op_Oper returns [String current=null] : iv_rulerel_op_Oper= rulerel_op_Oper EOF ;
    public final String entryRulerel_op_Oper() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Oper = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2775:2: (iv_rulerel_op_Oper= rulerel_op_Oper EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2776:2: iv_rulerel_op_Oper= rulerel_op_Oper EOF
            {
             newCompositeNode(grammarAccess.getRel_op_OperRule()); 
            pushFollow(FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper6093);
            iv_rulerel_op_Oper=rulerel_op_Oper();

            state._fsp--;

             current =iv_rulerel_op_Oper.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Oper6104); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2783:1: rulerel_op_Oper returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<>' ;
    public final AntlrDatatypeRuleToken rulerel_op_Oper() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2786:28: (kw= '<>' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2788:2: kw= '<>'
            {
            kw=(Token)match(input,53,FOLLOW_53_in_rulerel_op_Oper6141); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2801:1: entryRulecomment returns [String current=null] : iv_rulecomment= rulecomment EOF ;
    public final String entryRulecomment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulecomment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2802:2: (iv_rulecomment= rulecomment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2803:2: iv_rulecomment= rulecomment EOF
            {
             newCompositeNode(grammarAccess.getCommentRule()); 
            pushFollow(FOLLOW_rulecomment_in_entryRulecomment6181);
            iv_rulecomment=rulecomment();

            state._fsp--;

             current =iv_rulecomment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomment6192); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2810:1: rulecomment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_string_comment_0= rulestring_comment ;
    public final AntlrDatatypeRuleToken rulecomment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_string_comment_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2813:28: (this_string_comment_0= rulestring_comment )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2815:5: this_string_comment_0= rulestring_comment
            {
             
                    newCompositeNode(grammarAccess.getCommentAccess().getString_commentParserRuleCall()); 
                
            pushFollow(FOLLOW_rulestring_comment_in_rulecomment6238);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2833:1: entryRulestring_comment returns [String current=null] : iv_rulestring_comment= rulestring_comment EOF ;
    public final String entryRulestring_comment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulestring_comment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2834:2: (iv_rulestring_comment= rulestring_comment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2835:2: iv_rulestring_comment= rulestring_comment EOF
            {
             newCompositeNode(grammarAccess.getString_commentRule()); 
            pushFollow(FOLLOW_rulestring_comment_in_entryRulestring_comment6283);
            iv_rulestring_comment=rulestring_comment();

            state._fsp--;

             current =iv_rulestring_comment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulestring_comment6294); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2842:1: rulestring_comment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? ;
    public final AntlrDatatypeRuleToken rulestring_comment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token kw=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2845:28: ( (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2846:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2846:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==RULE_STRING) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2846:6: this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )*
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment6334); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_0()); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2853:1: (kw= '+' this_STRING_2= RULE_STRING )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==44) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2854:2: kw= '+' this_STRING_2= RULE_STRING
                    	    {
                    	    kw=(Token)match(input,44,FOLLOW_44_in_rulestring_comment6353); 

                    	            current.merge(kw);
                    	            newLeafNode(kw, grammarAccess.getString_commentAccess().getPlusSignKeyword_1_0()); 
                    	        
                    	    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment6368); 

                    	    		current.merge(this_STRING_2);
                    	        
                    	     
                    	        newLeafNode(this_STRING_2, grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_1_1()); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop41;
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


    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA22_eotS =
        "\21\uffff";
    static final String DFA22_eofS =
        "\6\uffff\1\17\11\uffff\1\17";
    static final String DFA22_minS =
        "\1\4\4\uffff\1\10\1\6\6\uffff\1\10\2\uffff\1\6";
    static final String DFA22_maxS =
        "\1\45\4\uffff\1\10\1\65\6\uffff\1\10\2\uffff\1\65";
    static final String DFA22_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1"+
        "\uffff\1\5\1\10\1\uffff";
    static final String DFA22_specialS =
        "\21\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\1\1\2\1\3\1\4\1\6\21\uffff\1\11\1\uffff\1\12\2\uffff\1\13"+
            "\1\uffff\1\14\1\7\1\10\1\uffff\1\5",
            "",
            "",
            "",
            "",
            "\1\6",
            "\1\17\11\uffff\7\17\1\uffff\2\17\1\16\4\17\1\uffff\1\17\3"+
            "\uffff\1\17\1\15\1\17\1\uffff\16\17",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\20",
            "",
            "",
            "\1\17\11\uffff\7\17\1\uffff\2\17\1\16\4\17\1\uffff\1\17\3"+
            "\uffff\1\17\1\15\1\17\1\uffff\16\17"
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1168:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_rulecomponent_declaration_in_entryRulecomponent_declaration75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_declaration85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemodification_in_rulecomponent_declaration131 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_ruleconditional_attribute_in_rulecomponent_declaration152 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_rulecomment_in_rulecomponent_declaration169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemodification_in_entryRulemodification205 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemodification215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rulemodification253 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulemodification274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rulemodification294 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulemodification315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute352 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_attribute362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleconditional_attribute399 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_attribute420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_entryRuleexpression456 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleexpression512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_ruleexpression540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_entryRulesimple_expression575 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesimple_expression585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression631 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_rulesimple_expression644 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression665 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_rulesimple_expression678 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr739 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_expr749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleconditional_expr786 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr807 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleconditional_expr819 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr840 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_19_in_ruleconditional_expr853 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr874 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleconditional_expr886 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr907 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_ruleconditional_expr922 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_entryRulelogical_expression980 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_expression990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression1037 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_rulelogical_expression1049 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression1070 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_rulelogical_term_in_entryRulelogical_term1108 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_term1118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term1165 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_rulelogical_term1177 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term1198 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_entryRulelogical_factor1236 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_factor1246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rulelogical_factor1284 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulerelation_in_rulelogical_factor1307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerelation_in_entryRulerelation1343 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerelation1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1400 = new BitSet(new long[]{0x003F000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_rulerelation1423 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_rulerelation1442 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_rulerelation1461 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_rulerelation1480 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_rulerelation1499 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_rulerelation1518 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1580 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearithmetic_expression1590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1638 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1657 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1676 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1695 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1720 = new BitSet(new long[]{0x0000F00000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1744 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1763 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1782 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1801 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1825 = new BitSet(new long[]{0x0000F00000000002L});
    public static final BitSet FOLLOW_ruleterm_in_entryRuleterm1863 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleterm1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm1920 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_ruleterm1943 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulemul_op_div_in_ruleterm1962 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_ruleterm1981 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_ruleterm2000 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm2024 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_rulefactor_in_entryRulefactor2062 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefactor2072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor2119 = new BitSet(new long[]{0x0000000003000002L});
    public static final BitSet FOLLOW_24_in_rulefactor2132 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_25_in_rulefactor2150 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor2172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_entryRuleprimary2210 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleprimary2220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary2262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleprimary2290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleprimary2318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_VAL_in_ruleprimary2346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_ruleprimary2379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_ruleprimary2406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_ruleprimary2433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleprimary2459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleprimary2478 = new BitSet(new long[]{0x0000F03E9C8101F0L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_ruleprimary2499 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleprimary2511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleprimary2531 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2552 = new BitSet(new long[]{0x0000000060000000L});
    public static final BitSet FOLLOW_29_in_ruleprimary2565 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2586 = new BitSet(new long[]{0x0000000060000000L});
    public static final BitSet FOLLOW_30_in_ruleprimary2600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleprimary2620 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleprimary2641 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleprimary2653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleprimary2678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_entryRulename_Function2727 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_Function2737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_in_rulename_Function2784 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_rulename_Function2804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2840 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleinitial_ref2850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleinitial_ref2887 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleinitial_ref2909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_entryRuleExprDer2944 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExprDer2954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleExprDer2991 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleExprDer3012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args3048 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_call_args3058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulefunction_call_args3104 = new BitSet(new long[]{0x0000F02E9C8101F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_rulefunction_call_args3125 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_rulefunction_call_args3138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list_in_entryRuleexpression_list3174 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression_list3184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list3230 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_ruleexpression_list3243 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list3264 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_rulename_in_entryRulename3302 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename3312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rulename3350 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3369 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_rulename3387 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3404 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3447 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_reference3457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rulecomponent_reference3495 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3514 = new BitSet(new long[]{0x0000002010000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3540 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_rulecomponent_reference3554 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3571 = new BitSet(new long[]{0x0000002010000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3597 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3636 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleoutput_expression_list3646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3701 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_ruleoutput_expression_list3715 = new BitSet(new long[]{0x0000F03E948101F2L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3736 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3775 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearray_subscripts3785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rulearray_subscripts3822 = new BitSet(new long[]{0x0000F02E948301F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3843 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_36_in_rulearray_subscripts3856 = new BitSet(new long[]{0x0000F02E948301F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3877 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_30_in_rulearray_subscripts3891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesubscript_in_entryRulesubscript3927 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesubscript3937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rulesubscript3984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulesubscript4012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments4048 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_arguments4058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefunction_arguments4114 = new BitSet(new long[]{0x0000005000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments4136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments4163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulefunction_arguments4193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp4229 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_exp4239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleFun_Arguments_exp4276 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp4297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for4333 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_for4343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleFun_Arguments_for4380 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4437 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_arguments4447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_rulenamed_arguments4494 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_rulenamed_arguments4506 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulenamed_arguments4527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_entryRulenamed_argument4565 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_argument4575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulenamed_argument4617 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_rulenamed_argument4634 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulenamed_argument4655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_indices_in_entryRulefor_indices4691 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_indices4701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4748 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_rulefor_indices4760 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4781 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_entryRulefor_index4819 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_index4829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulefor_index4871 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39_in_rulefor_index4889 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_index4910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4949 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_mul4960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rulemul_op_mul4997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_div_in_entryRulemul_op_div5037 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_div5048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rulemul_op_div5085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul5125 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotmul5136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rulemul_op_dotmul5173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv5213 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotdiv5224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rulemul_op_dotdiv5261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus5301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_plus5312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleadd_op_plus5349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5389 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_minus5400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleadd_op_minus5437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5477 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotplus5488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleadd_op_dotplus5525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5565 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotminus5576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleadd_op_dotminus5613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5653 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_then5664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_rulerel_op_Less_then5701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5741 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_equal5752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rulerel_op_Less_equal5789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5829 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_then5840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_rulerel_op_greater_then5877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5917 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_equal5928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rulerel_op_greater_equal5965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment6005 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_assignment6016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_rulerel_op_assignment6053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper6093 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Oper6104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_rulerel_op_Oper6141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomment_in_entryRulecomment6181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomment6192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_rulecomment6238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_entryRulestring_comment6283 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulestring_comment6294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment6334 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_44_in_rulestring_comment6353 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment6368 = new BitSet(new long[]{0x0000100000000002L});

}