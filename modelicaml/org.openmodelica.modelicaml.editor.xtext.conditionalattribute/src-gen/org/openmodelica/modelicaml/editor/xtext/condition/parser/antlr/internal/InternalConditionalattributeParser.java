package org.openmodelica.modelicaml.editor.xtext.condition.parser.antlr.internal; 

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
import org.openmodelica.modelicaml.editor.xtext.condition.services.ConditionalattributeGrammarAccess;

@SuppressWarnings("all")
public class InternalConditionalattributeParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNSIGNED_NUMBER", "RULE_INT", "RULE_STRING", "RULE_BOOL_VAL", "RULE_IDENT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'if'", "':'", "'then'", "'elseif'", "'else'", "'or'", "'and'", "'not'", "'^'", "'.^'", "'('", "')'", "'['", "';'", "']'", "'{'", "'}'", "'end'", "'initial'", "'der'", "','", "'.'", "'for'", "'='", "'in'", "'*'", "'/'", "'.*'", "'./'", "'+'", "'-'", "'.+'", "'.-'", "'<'", "'<='", "'>'", "'>='", "'=='", "'<>'"
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


        public InternalConditionalattributeParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalConditionalattributeParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalConditionalattributeParser.tokenNames; }
    public String getGrammarFileName() { return "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g"; }



     	private ConditionalattributeGrammarAccess grammarAccess;
     	
        public InternalConditionalattributeParser(TokenStream input, ConditionalattributeGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "conditional_attribute";	
       	}
       	
       	@Override
       	protected ConditionalattributeGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleconditional_attribute"
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:67:1: entryRuleconditional_attribute returns [EObject current=null] : iv_ruleconditional_attribute= ruleconditional_attribute EOF ;
    public final EObject entryRuleconditional_attribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_attribute = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:68:2: (iv_ruleconditional_attribute= ruleconditional_attribute EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:69:2: iv_ruleconditional_attribute= ruleconditional_attribute EOF
            {
             newCompositeNode(grammarAccess.getConditional_attributeRule()); 
            pushFollow(FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute75);
            iv_ruleconditional_attribute=ruleconditional_attribute();

            state._fsp--;

             current =iv_ruleconditional_attribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_attribute85); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:76:1: ruleconditional_attribute returns [EObject current=null] : (otherlv_0= 'if' ( (lv_expr_1_0= ruleexpression ) ) )? ;
    public final EObject ruleconditional_attribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expr_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:79:28: ( (otherlv_0= 'if' ( (lv_expr_1_0= ruleexpression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:80:1: (otherlv_0= 'if' ( (lv_expr_1_0= ruleexpression ) ) )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:80:1: (otherlv_0= 'if' ( (lv_expr_1_0= ruleexpression ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==14) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:80:3: otherlv_0= 'if' ( (lv_expr_1_0= ruleexpression ) )
                    {
                    otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleconditional_attribute122); 

                        	newLeafNode(otherlv_0, grammarAccess.getConditional_attributeAccess().getIfKeyword_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:84:1: ( (lv_expr_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:85:1: (lv_expr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:85:1: (lv_expr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:86:3: lv_expr_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getConditional_attributeAccess().getExprExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_attribute143);
                    lv_expr_1_0=ruleexpression();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getConditional_attributeRule());
                    	        }
                           		set(
                           			current, 
                           			"expr",
                            		lv_expr_1_0, 
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
    // $ANTLR end "ruleconditional_attribute"


    // $ANTLR start "entryRuleexpression"
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:110:1: entryRuleexpression returns [EObject current=null] : iv_ruleexpression= ruleexpression EOF ;
    public final EObject entryRuleexpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:111:2: (iv_ruleexpression= ruleexpression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:112:2: iv_ruleexpression= ruleexpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_ruleexpression_in_entryRuleexpression180);
            iv_ruleexpression=ruleexpression();

            state._fsp--;

             current =iv_ruleexpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression190); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:119:1: ruleexpression returns [EObject current=null] : ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) ;
    public final EObject ruleexpression() throws RecognitionException {
        EObject current = null;

        EObject lv_Expr_0_0 = null;

        EObject this_conditional_expr_1 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:122:28: ( ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:123:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:123:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=RULE_UNSIGNED_NUMBER && LA2_0<=RULE_IDENT)||LA2_0==21||LA2_0==24||LA2_0==26||LA2_0==29||(LA2_0>=31 && LA2_0<=33)||LA2_0==35||(LA2_0>=43 && LA2_0<=46)) ) {
                alt2=1;
            }
            else if ( (LA2_0==14) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:123:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:123:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:124:1: (lv_Expr_0_0= rulesimple_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:124:1: (lv_Expr_0_0= rulesimple_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:125:3: lv_Expr_0_0= rulesimple_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionAccess().getExprSimple_expressionParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulesimple_expression_in_ruleexpression236);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:143:5: this_conditional_expr_1= ruleconditional_expr
                    {
                     
                            newCompositeNode(grammarAccess.getExpressionAccess().getConditional_exprParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleconditional_expr_in_ruleexpression264);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:159:1: entryRulesimple_expression returns [EObject current=null] : iv_rulesimple_expression= rulesimple_expression EOF ;
    public final EObject entryRulesimple_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesimple_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:160:2: (iv_rulesimple_expression= rulesimple_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:161:2: iv_rulesimple_expression= rulesimple_expression EOF
            {
             newCompositeNode(grammarAccess.getSimple_expressionRule()); 
            pushFollow(FOLLOW_rulesimple_expression_in_entryRulesimple_expression299);
            iv_rulesimple_expression=rulesimple_expression();

            state._fsp--;

             current =iv_rulesimple_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesimple_expression309); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:168:1: rulesimple_expression returns [EObject current=null] : ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) ;
    public final EObject rulesimple_expression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_Log_Exp_0_0 = null;

        EObject lv_S_Logical_expression_2_0 = null;

        EObject lv_L_Logical_expression_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:171:28: ( ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:172:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:172:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:172:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) ) (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:172:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:173:1: (lv_Log_Exp_0_0= rulelogical_expression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:173:1: (lv_Log_Exp_0_0= rulelogical_expression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:174:3: lv_Log_Exp_0_0= rulelogical_expression
            {
             
            	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getLog_ExpLogical_expressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression355);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:190:2: (otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:190:4: otherlv_1= ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    {
                    otherlv_1=(Token)match(input,15,FOLLOW_15_in_rulesimple_expression368); 

                        	newLeafNode(otherlv_1, grammarAccess.getSimple_expressionAccess().getColonKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:194:1: ( (lv_S_Logical_expression_2_0= rulelogical_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:195:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:195:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:196:3: lv_S_Logical_expression_2_0= rulelogical_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getS_Logical_expressionLogical_expressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression389);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:212:2: (otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==15) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:212:4: otherlv_3= ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            {
                            otherlv_3=(Token)match(input,15,FOLLOW_15_in_rulesimple_expression402); 

                                	newLeafNode(otherlv_3, grammarAccess.getSimple_expressionAccess().getColonKeyword_1_2_0());
                                
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:216:1: ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:217:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:217:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:218:3: lv_L_Logical_expression_4_0= rulelogical_expression
                            {
                             
                            	        newCompositeNode(grammarAccess.getSimple_expressionAccess().getL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression423);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:242:1: entryRuleconditional_expr returns [EObject current=null] : iv_ruleconditional_expr= ruleconditional_expr EOF ;
    public final EObject entryRuleconditional_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_expr = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:243:2: (iv_ruleconditional_expr= ruleconditional_expr EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:244:2: iv_ruleconditional_expr= ruleconditional_expr EOF
            {
             newCompositeNode(grammarAccess.getConditional_exprRule()); 
            pushFollow(FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr463);
            iv_ruleconditional_expr=ruleconditional_expr();

            state._fsp--;

             current =iv_ruleconditional_expr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_expr473); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:251:1: ruleconditional_expr returns [EObject current=null] : (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:254:28: ( (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:255:1: (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:255:1: (otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:255:3: otherlv_0= 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) otherlv_2= 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            {
            otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleconditional_expr510); 

                	newLeafNode(otherlv_0, grammarAccess.getConditional_exprAccess().getIfKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:259:1: ( (lv_ifexpr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:260:1: (lv_ifexpr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:260:1: (lv_ifexpr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:261:3: lv_ifexpr_1_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getIfexprExpressionParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr531);
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

            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleconditional_expr543); 

                	newLeafNode(otherlv_2, grammarAccess.getConditional_exprAccess().getThenKeyword_2());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:281:1: ( (lv_thenexpr_3_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:282:1: (lv_thenexpr_3_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:282:1: (lv_thenexpr_3_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:283:3: lv_thenexpr_3_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getThenexprExpressionParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr564);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:299:2: (otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==17) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:299:4: otherlv_4= 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) otherlv_6= 'then' ( (lv_trueexpr_7_0= ruleexpression ) )
            	    {
            	    otherlv_4=(Token)match(input,17,FOLLOW_17_in_ruleconditional_expr577); 

            	        	newLeafNode(otherlv_4, grammarAccess.getConditional_exprAccess().getElseifKeyword_4_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:303:1: ( (lv_elseifexpr_5_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:304:1: (lv_elseifexpr_5_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:304:1: (lv_elseifexpr_5_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:305:3: lv_elseifexpr_5_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditional_exprAccess().getElseifexprExpressionParserRuleCall_4_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr598);
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

            	    otherlv_6=(Token)match(input,16,FOLLOW_16_in_ruleconditional_expr610); 

            	        	newLeafNode(otherlv_6, grammarAccess.getConditional_exprAccess().getThenKeyword_4_2());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:325:1: ( (lv_trueexpr_7_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:326:1: (lv_trueexpr_7_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:326:1: (lv_trueexpr_7_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:327:3: lv_trueexpr_7_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getConditional_exprAccess().getTrueexprExpressionParserRuleCall_4_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr631);
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
            	    break loop5;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:343:4: (otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:343:6: otherlv_8= 'else' ( (lv_falseexpr_9_0= ruleexpression ) )
            {
            otherlv_8=(Token)match(input,18,FOLLOW_18_in_ruleconditional_expr646); 

                	newLeafNode(otherlv_8, grammarAccess.getConditional_exprAccess().getElseKeyword_5_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:347:1: ( (lv_falseexpr_9_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:348:1: (lv_falseexpr_9_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:348:1: (lv_falseexpr_9_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:349:3: lv_falseexpr_9_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getConditional_exprAccess().getFalseexprExpressionParserRuleCall_5_1_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr667);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:373:1: entryRulelogical_expression returns [EObject current=null] : iv_rulelogical_expression= rulelogical_expression EOF ;
    public final EObject entryRulelogical_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:374:2: (iv_rulelogical_expression= rulelogical_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:375:2: iv_rulelogical_expression= rulelogical_expression EOF
            {
             newCompositeNode(grammarAccess.getLogical_expressionRule()); 
            pushFollow(FOLLOW_rulelogical_expression_in_entryRulelogical_expression704);
            iv_rulelogical_expression=rulelogical_expression();

            state._fsp--;

             current =iv_rulelogical_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_expression714); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:382:1: rulelogical_expression returns [EObject current=null] : (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) ;
    public final EObject rulelogical_expression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_logical_term_0 = null;

        EObject lv_Logical_term_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:385:28: ( (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:386:1: (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:386:1: (this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:387:5: this_logical_term_0= rulelogical_term (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression761);
            this_logical_term_0=rulelogical_term();

            state._fsp--;

             
                    current = this_logical_term_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:395:1: (otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==19) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:395:3: otherlv_1= 'or' ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    {
            	    otherlv_1=(Token)match(input,19,FOLLOW_19_in_rulelogical_expression773); 

            	        	newLeafNode(otherlv_1, grammarAccess.getLogical_expressionAccess().getOrKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:399:1: ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:400:1: (lv_Logical_term_2_0= rulelogical_term )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:400:1: (lv_Logical_term_2_0= rulelogical_term )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:401:3: lv_Logical_term_2_0= rulelogical_term
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termLogical_termParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression794);
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
            	    break loop6;
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:425:1: entryRulelogical_term returns [EObject current=null] : iv_rulelogical_term= rulelogical_term EOF ;
    public final EObject entryRulelogical_term() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_term = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:426:2: (iv_rulelogical_term= rulelogical_term EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:427:2: iv_rulelogical_term= rulelogical_term EOF
            {
             newCompositeNode(grammarAccess.getLogical_termRule()); 
            pushFollow(FOLLOW_rulelogical_term_in_entryRulelogical_term832);
            iv_rulelogical_term=rulelogical_term();

            state._fsp--;

             current =iv_rulelogical_term; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_term842); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:434:1: rulelogical_term returns [EObject current=null] : (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) ;
    public final EObject rulelogical_term() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_logical_factor_0 = null;

        EObject lv_Logical_factor_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:437:28: ( (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:438:1: (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:438:1: (this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:439:5: this_logical_factor_0= rulelogical_factor (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term889);
            this_logical_factor_0=rulelogical_factor();

            state._fsp--;

             
                    current = this_logical_factor_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:447:1: (otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==20) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:447:3: otherlv_1= 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    {
            	    otherlv_1=(Token)match(input,20,FOLLOW_20_in_rulelogical_term901); 

            	        	newLeafNode(otherlv_1, grammarAccess.getLogical_termAccess().getAndKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:451:1: ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:452:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:452:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:453:3: lv_Logical_factor_2_0= rulelogical_factor
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorLogical_factorParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term922);
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
            	    break loop7;
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:477:1: entryRulelogical_factor returns [EObject current=null] : iv_rulelogical_factor= rulelogical_factor EOF ;
    public final EObject entryRulelogical_factor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_factor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:478:2: (iv_rulelogical_factor= rulelogical_factor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:479:2: iv_rulelogical_factor= rulelogical_factor EOF
            {
             newCompositeNode(grammarAccess.getLogical_factorRule()); 
            pushFollow(FOLLOW_rulelogical_factor_in_entryRulelogical_factor960);
            iv_rulelogical_factor=rulelogical_factor();

            state._fsp--;

             current =iv_rulelogical_factor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_factor970); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:486:1: rulelogical_factor returns [EObject current=null] : ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) ;
    public final EObject rulelogical_factor() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_Relation_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:489:28: ( ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:490:1: ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:490:1: ( (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:490:2: (otherlv_0= 'not' )? ( (lv_Relation_1_0= rulerelation ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:490:2: (otherlv_0= 'not' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==21) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:490:4: otherlv_0= 'not'
                    {
                    otherlv_0=(Token)match(input,21,FOLLOW_21_in_rulelogical_factor1008); 

                        	newLeafNode(otherlv_0, grammarAccess.getLogical_factorAccess().getNotKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:494:3: ( (lv_Relation_1_0= rulerelation ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:495:1: (lv_Relation_1_0= rulerelation )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:495:1: (lv_Relation_1_0= rulerelation )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:496:3: lv_Relation_1_0= rulerelation
            {
             
            	        newCompositeNode(grammarAccess.getLogical_factorAccess().getRelationRelationParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulerelation_in_rulelogical_factor1031);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:520:1: entryRulerelation returns [EObject current=null] : iv_rulerelation= rulerelation EOF ;
    public final EObject entryRulerelation() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:521:2: (iv_rulerelation= rulerelation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:522:2: iv_rulerelation= rulerelation EOF
            {
             newCompositeNode(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_rulerelation_in_entryRulerelation1067);
            iv_rulerelation=rulerelation();

            state._fsp--;

             current =iv_rulerelation; 
            match(input,EOF,FOLLOW_EOF_in_entryRulerelation1077); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:529:1: rulerelation returns [EObject current=null] : (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:532:28: ( (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:533:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:533:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:534:5: this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1124);
            this_arithmetic_expression_0=rulearithmetic_expression();

            state._fsp--;

             
                    current = this_arithmetic_expression_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:542:1: ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=47 && LA10_0<=52)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:542:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:542:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:543:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:543:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:544:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:544:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    int alt9=6;
                    switch ( input.LA(1) ) {
                    case 47:
                        {
                        alt9=1;
                        }
                        break;
                    case 48:
                        {
                        alt9=2;
                        }
                        break;
                    case 49:
                        {
                        alt9=3;
                        }
                        break;
                    case 50:
                        {
                        alt9=4;
                        }
                        break;
                    case 51:
                        {
                        alt9=5;
                        }
                        break;
                    case 52:
                        {
                        alt9=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                        throw nvae;
                    }

                    switch (alt9) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:545:3: lv_op_1_1= rulerel_op_Less_then
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_thenParserRuleCall_1_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_then_in_rulerelation1147);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:560:8: lv_op_1_2= rulerel_op_Less_equal
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_equalParserRuleCall_1_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_equal_in_rulerelation1166);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:575:8: lv_op_1_3= rulerel_op_greater_then
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_thenParserRuleCall_1_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_then_in_rulerelation1185);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:590:8: lv_op_1_4= rulerel_op_greater_equal
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_equalParserRuleCall_1_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_equal_in_rulerelation1204);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:605:8: lv_op_1_5= rulerel_op_assignment
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_assignmentParserRuleCall_1_0_0_4()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_assignment_in_rulerelation1223);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:620:8: lv_op_1_6= rulerel_op_Oper
                            {
                             
                            	        newCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_OperParserRuleCall_1_0_0_5()); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Oper_in_rulerelation1242);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:638:2: ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:639:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:639:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:640:3: lv_Arithmetic_expression_2_0= rulearithmetic_expression
                    {
                     
                    	        newCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1266);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:664:1: entryRulearithmetic_expression returns [EObject current=null] : iv_rulearithmetic_expression= rulearithmetic_expression EOF ;
    public final EObject entryRulearithmetic_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearithmetic_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:665:2: (iv_rulearithmetic_expression= rulearithmetic_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:666:2: iv_rulearithmetic_expression= rulearithmetic_expression EOF
            {
             newCompositeNode(grammarAccess.getArithmetic_expressionRule()); 
            pushFollow(FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1304);
            iv_rulearithmetic_expression=rulearithmetic_expression();

            state._fsp--;

             current =iv_rulearithmetic_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearithmetic_expression1314); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:673:1: rulearithmetic_expression returns [EObject current=null] : ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:676:28: ( ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:677:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:677:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:677:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:677:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=43 && LA12_0<=46)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:678:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:678:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:679:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:679:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    int alt11=4;
                    switch ( input.LA(1) ) {
                    case 43:
                        {
                        alt11=1;
                        }
                        break;
                    case 44:
                        {
                        alt11=2;
                        }
                        break;
                    case 45:
                        {
                        alt11=3;
                        }
                        break;
                    case 46:
                        {
                        alt11=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }

                    switch (alt11) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:680:3: lv_opr_0_1= ruleadd_op_plus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_plusParserRuleCall_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1362);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:695:8: lv_opr_0_2= ruleadd_op_minus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_minusParserRuleCall_0_0_1()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1381);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:710:8: lv_opr_0_3= ruleadd_op_dotplus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotplusParserRuleCall_0_0_2()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1400);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:725:8: lv_opr_0_4= ruleadd_op_dotminus
                            {
                             
                            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotminusParserRuleCall_0_0_3()); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1419);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:743:3: ( (lv_Term_1_0= ruleterm ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:744:1: (lv_Term_1_0= ruleterm )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:744:1: (lv_Term_1_0= ruleterm )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:745:3: lv_Term_1_0= ruleterm
            {
             
            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTermTermParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1444);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:761:2: ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=43 && LA14_0<=46)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:761:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:761:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:762:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:762:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:763:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:763:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    int alt13=4;
            	    switch ( input.LA(1) ) {
            	    case 43:
            	        {
            	        alt13=1;
            	        }
            	        break;
            	    case 44:
            	        {
            	        alt13=2;
            	        }
            	        break;
            	    case 45:
            	        {
            	        alt13=3;
            	        }
            	        break;
            	    case 46:
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:764:3: lv_Oper1_2_1= ruleadd_op_plus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_plusParserRuleCall_2_0_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1468);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:779:8: lv_Oper1_2_2= ruleadd_op_minus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_minusParserRuleCall_2_0_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1487);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:794:8: lv_Oper1_2_3= ruleadd_op_dotplus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotplusParserRuleCall_2_0_0_2()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1506);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:809:8: lv_Oper1_2_4= ruleadd_op_dotminus
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotminusParserRuleCall_2_0_0_3()); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1525);
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

            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:827:2: ( (lv_Term1_3_0= ruleterm ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:828:1: (lv_Term1_3_0= ruleterm )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:828:1: (lv_Term1_3_0= ruleterm )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:829:3: lv_Term1_3_0= ruleterm
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTerm1TermParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1549);
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
            	    break loop14;
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:853:1: entryRuleterm returns [EObject current=null] : iv_ruleterm= ruleterm EOF ;
    public final EObject entryRuleterm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleterm = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:854:2: (iv_ruleterm= ruleterm EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:855:2: iv_ruleterm= ruleterm EOF
            {
             newCompositeNode(grammarAccess.getTermRule()); 
            pushFollow(FOLLOW_ruleterm_in_entryRuleterm1587);
            iv_ruleterm=ruleterm();

            state._fsp--;

             current =iv_ruleterm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleterm1597); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:862:1: ruleterm returns [EObject current=null] : (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:865:28: ( (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:866:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:866:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:867:5: this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getTermAccess().getFactorParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulefactor_in_ruleterm1644);
            this_factor_0=rulefactor();

            state._fsp--;

             
                    current = this_factor_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:875:1: ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=39 && LA16_0<=42)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:875:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:875:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:876:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:876:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:877:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:877:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    int alt15=4;
            	    switch ( input.LA(1) ) {
            	    case 39:
            	        {
            	        alt15=1;
            	        }
            	        break;
            	    case 40:
            	        {
            	        alt15=2;
            	        }
            	        break;
            	    case 41:
            	        {
            	        alt15=3;
            	        }
            	        break;
            	    case 42:
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:878:3: lv_op_1_1= rulemul_op_mul
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_mulParserRuleCall_1_0_0_0()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_mul_in_ruleterm1667);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:893:8: lv_op_1_2= rulemul_op_div
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_divParserRuleCall_1_0_0_1()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_div_in_ruleterm1686);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:908:8: lv_op_1_3= rulemul_op_dotmul
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotmulParserRuleCall_1_0_0_2()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotmul_in_ruleterm1705);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:923:8: lv_op_1_4= rulemul_op_dotdiv
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotdivParserRuleCall_1_0_0_3()); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotdiv_in_ruleterm1724);
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

            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:941:2: ( (lv_Factor_2_0= rulefactor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:942:1: (lv_Factor_2_0= rulefactor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:942:1: (lv_Factor_2_0= rulefactor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:943:3: lv_Factor_2_0= rulefactor
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTermAccess().getFactorFactorParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulefactor_in_ruleterm1748);
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
    // $ANTLR end "ruleterm"


    // $ANTLR start "entryRulefactor"
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:967:1: entryRulefactor returns [EObject current=null] : iv_rulefactor= rulefactor EOF ;
    public final EObject entryRulefactor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefactor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:968:2: (iv_rulefactor= rulefactor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:969:2: iv_rulefactor= rulefactor EOF
            {
             newCompositeNode(grammarAccess.getFactorRule()); 
            pushFollow(FOLLOW_rulefactor_in_entryRulefactor1786);
            iv_rulefactor=rulefactor();

            state._fsp--;

             current =iv_rulefactor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefactor1796); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:976:1: rulefactor returns [EObject current=null] : (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) ;
    public final EObject rulefactor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject this_primary_0 = null;

        EObject lv_Primary_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:979:28: ( (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:980:1: (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:980:1: (this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:981:5: this_primary_0= ruleprimary ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getFactorAccess().getPrimaryParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleprimary_in_rulefactor1843);
            this_primary_0=ruleprimary();

            state._fsp--;

             
                    current = this_primary_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:989:1: ( (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=22 && LA18_0<=23)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:989:2: (otherlv_1= '^' | otherlv_2= '.^' ) ( (lv_Primary_3_0= ruleprimary ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:989:2: (otherlv_1= '^' | otherlv_2= '.^' )
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==22) ) {
                        alt17=1;
                    }
                    else if ( (LA17_0==23) ) {
                        alt17=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 17, 0, input);

                        throw nvae;
                    }
                    switch (alt17) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:989:4: otherlv_1= '^'
                            {
                            otherlv_1=(Token)match(input,22,FOLLOW_22_in_rulefactor1856); 

                                	newLeafNode(otherlv_1, grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0());
                                

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:994:7: otherlv_2= '.^'
                            {
                            otherlv_2=(Token)match(input,23,FOLLOW_23_in_rulefactor1874); 

                                	newLeafNode(otherlv_2, grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1());
                                

                            }
                            break;

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:998:2: ( (lv_Primary_3_0= ruleprimary ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:999:1: (lv_Primary_3_0= ruleprimary )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:999:1: (lv_Primary_3_0= ruleprimary )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1000:3: lv_Primary_3_0= ruleprimary
                    {
                     
                    	        newCompositeNode(grammarAccess.getFactorAccess().getPrimaryPrimaryParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleprimary_in_rulefactor1896);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1024:1: entryRuleprimary returns [EObject current=null] : iv_ruleprimary= ruleprimary EOF ;
    public final EObject entryRuleprimary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleprimary = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1025:2: (iv_ruleprimary= ruleprimary EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1026:2: iv_ruleprimary= ruleprimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_ruleprimary_in_entryRuleprimary1934);
            iv_ruleprimary=ruleprimary();

            state._fsp--;

             current =iv_ruleprimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleprimary1944); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1033:1: ruleprimary returns [EObject current=null] : ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1036:28: ( ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1037:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1037:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )
            int alt20=12;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1037:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1037:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1038:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1038:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1039:3: lv_num_0_0= RULE_UNSIGNED_NUMBER
                    {
                    lv_num_0_0=(Token)match(input,RULE_UNSIGNED_NUMBER,FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary1986); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1056:6: ( (lv_int_1_0= RULE_INT ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1056:6: ( (lv_int_1_0= RULE_INT ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1057:1: (lv_int_1_0= RULE_INT )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1057:1: (lv_int_1_0= RULE_INT )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1058:3: lv_int_1_0= RULE_INT
                    {
                    lv_int_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleprimary2014); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1075:6: ( (lv_str_2_0= RULE_STRING ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1075:6: ( (lv_str_2_0= RULE_STRING ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1076:1: (lv_str_2_0= RULE_STRING )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1076:1: (lv_str_2_0= RULE_STRING )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1077:3: lv_str_2_0= RULE_STRING
                    {
                    lv_str_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleprimary2042); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1094:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1094:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1095:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1095:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1096:3: lv_Bool_3_0= RULE_BOOL_VAL
                    {
                    lv_Bool_3_0=(Token)match(input,RULE_BOOL_VAL,FOLLOW_RULE_BOOL_VAL_in_ruleprimary2070); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1114:5: this_name_Function_4= rulename_Function
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getName_FunctionParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_rulename_Function_in_ruleprimary2103);
                    this_name_Function_4=rulename_Function();

                    state._fsp--;

                     
                            current = this_name_Function_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1124:5: this_initial_ref_5= ruleinitial_ref
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getInitial_refParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleinitial_ref_in_ruleprimary2130);
                    this_initial_ref_5=ruleinitial_ref();

                    state._fsp--;

                     
                            current = this_initial_ref_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1134:5: this_ExprDer_6= ruleExprDer
                    {
                     
                            newCompositeNode(grammarAccess.getPrimaryAccess().getExprDerParserRuleCall_6()); 
                        
                    pushFollow(FOLLOW_ruleExprDer_in_ruleprimary2157);
                    this_ExprDer_6=ruleExprDer();

                    state._fsp--;

                     
                            current = this_ExprDer_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1143:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1143:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1144:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1144:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1145:3: lv_Component_reference_7_0= rulecomponent_reference
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getComponent_referenceComponent_referenceParserRuleCall_7_0()); 
                    	    
                    pushFollow(FOLLOW_rulecomponent_reference_in_ruleprimary2183);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1162:6: (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1162:6: (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1162:8: otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')'
                    {
                    otherlv_8=(Token)match(input,24,FOLLOW_24_in_ruleprimary2202); 

                        	newLeafNode(otherlv_8, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_8_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1166:1: ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1167:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1167:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1168:3: lv_output_expr_list_9_0= ruleoutput_expression_list
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getOutput_expr_listOutput_expression_listParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleoutput_expression_list_in_ruleprimary2223);
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

                    otherlv_10=(Token)match(input,25,FOLLOW_25_in_ruleprimary2235); 

                        	newLeafNode(otherlv_10, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_8_2());
                        

                    }


                    }
                    break;
                case 10 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1189:6: (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1189:6: (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1189:8: otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']'
                    {
                    otherlv_11=(Token)match(input,26,FOLLOW_26_in_ruleprimary2255); 

                        	newLeafNode(otherlv_11, grammarAccess.getPrimaryAccess().getLeftSquareBracketKeyword_9_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1193:1: ( (lv_Expre_list_12_0= ruleexpression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1194:1: (lv_Expre_list_12_0= ruleexpression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1194:1: (lv_Expre_list_12_0= ruleexpression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1195:3: lv_Expre_list_12_0= ruleexpression_list
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpre_listExpression_listParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2276);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1211:2: (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==27) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1211:4: otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    {
                    	    otherlv_13=(Token)match(input,27,FOLLOW_27_in_ruleprimary2289); 

                    	        	newLeafNode(otherlv_13, grammarAccess.getPrimaryAccess().getSemicolonKeyword_9_2_0());
                    	        
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1215:1: ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1216:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    {
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1216:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1217:3: lv_Expression_list_14_0= ruleexpression_list
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listExpression_listParserRuleCall_9_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2310);
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
                    	    break loop19;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,28,FOLLOW_28_in_ruleprimary2324); 

                        	newLeafNode(otherlv_15, grammarAccess.getPrimaryAccess().getRightSquareBracketKeyword_9_3());
                        

                    }


                    }
                    break;
                case 11 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1238:6: (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1238:6: (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1238:8: otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}'
                    {
                    otherlv_16=(Token)match(input,29,FOLLOW_29_in_ruleprimary2344); 

                        	newLeafNode(otherlv_16, grammarAccess.getPrimaryAccess().getLeftCurlyBracketKeyword_10_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1242:1: ( (lv_f_arguments_17_0= rulefunction_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1243:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1243:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1244:3: lv_f_arguments_17_0= rulefunction_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getPrimaryAccess().getF_argumentsFunction_argumentsParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_ruleprimary2365);
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

                    otherlv_18=(Token)match(input,30,FOLLOW_30_in_ruleprimary2377); 

                        	newLeafNode(otherlv_18, grammarAccess.getPrimaryAccess().getRightCurlyBracketKeyword_10_2());
                        

                    }


                    }
                    break;
                case 12 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1265:6: ( (lv_End_19_0= 'end' ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1265:6: ( (lv_End_19_0= 'end' ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1266:1: (lv_End_19_0= 'end' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1266:1: (lv_End_19_0= 'end' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1267:3: lv_End_19_0= 'end'
                    {
                    lv_End_19_0=(Token)match(input,31,FOLLOW_31_in_ruleprimary2402); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1288:1: entryRulename_Function returns [EObject current=null] : iv_rulename_Function= rulename_Function EOF ;
    public final EObject entryRulename_Function() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename_Function = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1289:2: (iv_rulename_Function= rulename_Function EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1290:2: iv_rulename_Function= rulename_Function EOF
            {
             newCompositeNode(grammarAccess.getName_FunctionRule()); 
            pushFollow(FOLLOW_rulename_Function_in_entryRulename_Function2451);
            iv_rulename_Function=rulename_Function();

            state._fsp--;

             current =iv_rulename_Function; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_Function2461); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1297:1: rulename_Function returns [EObject current=null] : (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) ;
    public final EObject rulename_Function() throws RecognitionException {
        EObject current = null;

        EObject this_name_0 = null;

        EObject lv_Function_call_args_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1300:28: ( (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1301:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1301:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1302:5: this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            {
             
                    newCompositeNode(grammarAccess.getName_FunctionAccess().getNameParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulename_in_rulename_Function2508);
            this_name_0=rulename();

            state._fsp--;

             
                    current = this_name_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1310:1: ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1311:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1311:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1312:3: lv_Function_call_args_1_0= rulefunction_call_args
            {
             
            	        newCompositeNode(grammarAccess.getName_FunctionAccess().getFunction_call_argsFunction_call_argsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_rulename_Function2528);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1336:1: entryRuleinitial_ref returns [EObject current=null] : iv_ruleinitial_ref= ruleinitial_ref EOF ;
    public final EObject entryRuleinitial_ref() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitial_ref = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1337:2: (iv_ruleinitial_ref= ruleinitial_ref EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1338:2: iv_ruleinitial_ref= ruleinitial_ref EOF
            {
             newCompositeNode(grammarAccess.getInitial_refRule()); 
            pushFollow(FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2564);
            iv_ruleinitial_ref=ruleinitial_ref();

            state._fsp--;

             current =iv_ruleinitial_ref; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleinitial_ref2574); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1345:1: ruleinitial_ref returns [EObject current=null] : (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args ) ;
    public final EObject ruleinitial_ref() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject this_function_call_args_1 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1348:28: ( (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1349:1: (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1349:1: (otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1349:3: otherlv_0= 'initial' this_function_call_args_1= rulefunction_call_args
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleinitial_ref2611); 

                	newLeafNode(otherlv_0, grammarAccess.getInitial_refAccess().getInitialKeyword_0());
                
             
                    newCompositeNode(grammarAccess.getInitial_refAccess().getFunction_call_argsParserRuleCall_1()); 
                
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleinitial_ref2633);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1370:1: entryRuleExprDer returns [EObject current=null] : iv_ruleExprDer= ruleExprDer EOF ;
    public final EObject entryRuleExprDer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExprDer = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1371:2: (iv_ruleExprDer= ruleExprDer EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1372:2: iv_ruleExprDer= ruleExprDer EOF
            {
             newCompositeNode(grammarAccess.getExprDerRule()); 
            pushFollow(FOLLOW_ruleExprDer_in_entryRuleExprDer2668);
            iv_ruleExprDer=ruleExprDer();

            state._fsp--;

             current =iv_ruleExprDer; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExprDer2678); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1379:1: ruleExprDer returns [EObject current=null] : (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) ;
    public final EObject ruleExprDer() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_functionArgs_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1382:28: ( (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1383:1: (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1383:1: (otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1383:3: otherlv_0= 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            {
            otherlv_0=(Token)match(input,33,FOLLOW_33_in_ruleExprDer2715); 

                	newLeafNode(otherlv_0, grammarAccess.getExprDerAccess().getDerKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1387:1: ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1388:1: (lv_functionArgs_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1388:1: (lv_functionArgs_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1389:3: lv_functionArgs_1_0= rulefunction_call_args
            {
             
            	        newCompositeNode(grammarAccess.getExprDerAccess().getFunctionArgsFunction_call_argsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleExprDer2736);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1413:1: entryRulefunction_call_args returns [EObject current=null] : iv_rulefunction_call_args= rulefunction_call_args EOF ;
    public final EObject entryRulefunction_call_args() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_call_args = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1414:2: (iv_rulefunction_call_args= rulefunction_call_args EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1415:2: iv_rulefunction_call_args= rulefunction_call_args EOF
            {
             newCompositeNode(grammarAccess.getFunction_call_argsRule()); 
            pushFollow(FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args2772);
            iv_rulefunction_call_args=rulefunction_call_args();

            state._fsp--;

             current =iv_rulefunction_call_args; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_call_args2782); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1422:1: rulefunction_call_args returns [EObject current=null] : ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' ) ;
    public final EObject rulefunction_call_args() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_f_arg_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1425:28: ( ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1426:1: ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1426:1: ( () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1426:2: () otherlv_1= '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? otherlv_3= ')'
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1426:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1427:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_rulefunction_call_args2828); 

                	newLeafNode(otherlv_1, grammarAccess.getFunction_call_argsAccess().getLeftParenthesisKeyword_1());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1436:1: ( (lv_f_arg_2_0= rulefunction_arguments ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=RULE_UNSIGNED_NUMBER && LA21_0<=RULE_IDENT)||LA21_0==14||LA21_0==21||LA21_0==24||LA21_0==26||LA21_0==29||(LA21_0>=31 && LA21_0<=33)||LA21_0==35||(LA21_0>=43 && LA21_0<=46)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1437:1: (lv_f_arg_2_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1437:1: (lv_f_arg_2_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1438:3: lv_f_arg_2_0= rulefunction_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_call_argsAccess().getF_argFunction_argumentsParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_rulefunction_call_args2849);
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

            otherlv_3=(Token)match(input,25,FOLLOW_25_in_rulefunction_call_args2862); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1466:1: entryRuleexpression_list returns [EObject current=null] : iv_ruleexpression_list= ruleexpression_list EOF ;
    public final EObject entryRuleexpression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1467:2: (iv_ruleexpression_list= ruleexpression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1468:2: iv_ruleexpression_list= ruleexpression_list EOF
            {
             newCompositeNode(grammarAccess.getExpression_listRule()); 
            pushFollow(FOLLOW_ruleexpression_list_in_entryRuleexpression_list2898);
            iv_ruleexpression_list=ruleexpression_list();

            state._fsp--;

             current =iv_ruleexpression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression_list2908); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1475:1: ruleexpression_list returns [EObject current=null] : ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) ;
    public final EObject ruleexpression_list() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expr_0_0 = null;

        EObject lv_Expre_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1478:28: ( ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1479:1: ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1479:1: ( ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1479:2: ( (lv_expr_0_0= ruleexpression ) ) (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1479:2: ( (lv_expr_0_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1480:1: (lv_expr_0_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1480:1: (lv_expr_0_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1481:3: lv_expr_0_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getExpression_listAccess().getExprExpressionParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list2954);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1497:2: (otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==34) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1497:4: otherlv_1= ',' ( (lv_Expre_2_0= ruleexpression ) )
            	    {
            	    otherlv_1=(Token)match(input,34,FOLLOW_34_in_ruleexpression_list2967); 

            	        	newLeafNode(otherlv_1, grammarAccess.getExpression_listAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1501:1: ( (lv_Expre_2_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1502:1: (lv_Expre_2_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1502:1: (lv_Expre_2_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1503:3: lv_Expre_2_0= ruleexpression
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression_listAccess().getExpreExpressionParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list2988);
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
            	    break loop22;
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1527:1: entryRulename returns [EObject current=null] : iv_rulename= rulename EOF ;
    public final EObject entryRulename() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1528:2: (iv_rulename= rulename EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1529:2: iv_rulename= rulename EOF
            {
             newCompositeNode(grammarAccess.getNameRule()); 
            pushFollow(FOLLOW_rulename_in_entryRulename3026);
            iv_rulename=rulename();

            state._fsp--;

             current =iv_rulename; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename3036); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1536:1: rulename returns [EObject current=null] : ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) ;
    public final EObject rulename() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_ID_1_0=null;
        Token otherlv_2=null;
        Token lv_nam_ID_3_0=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1539:28: ( ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1540:1: ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1540:1: ( (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1540:2: (otherlv_0= '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1540:2: (otherlv_0= '.' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==35) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1540:4: otherlv_0= '.'
                    {
                    otherlv_0=(Token)match(input,35,FOLLOW_35_in_rulename3074); 

                        	newLeafNode(otherlv_0, grammarAccess.getNameAccess().getFullStopKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1544:3: ( (lv_name_ID_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1545:1: (lv_name_ID_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1545:1: (lv_name_ID_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1546:3: lv_name_ID_1_0= RULE_IDENT
            {
            lv_name_ID_1_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3093); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1562:2: (otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==35) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1562:4: otherlv_2= '.' ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    {
            	    otherlv_2=(Token)match(input,35,FOLLOW_35_in_rulename3111); 

            	        	newLeafNode(otherlv_2, grammarAccess.getNameAccess().getFullStopKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1566:1: ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1567:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1567:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1568:3: lv_nam_ID_3_0= RULE_IDENT
            	    {
            	    lv_nam_ID_3_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3128); 

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
    // $ANTLR end "rulename"


    // $ANTLR start "entryRulecomponent_reference"
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1592:1: entryRulecomponent_reference returns [EObject current=null] : iv_rulecomponent_reference= rulecomponent_reference EOF ;
    public final EObject entryRulecomponent_reference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_reference = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1593:2: (iv_rulecomponent_reference= rulecomponent_reference EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1594:2: iv_rulecomponent_reference= rulecomponent_reference EOF
            {
             newCompositeNode(grammarAccess.getComponent_referenceRule()); 
            pushFollow(FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3171);
            iv_rulecomponent_reference=rulecomponent_reference();

            state._fsp--;

             current =iv_rulecomponent_reference; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_reference3181); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1601:1: rulecomponent_reference returns [EObject current=null] : ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1604:28: ( ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1605:1: ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1605:1: ( (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1605:2: (otherlv_0= '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1605:2: (otherlv_0= '.' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==35) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1605:4: otherlv_0= '.'
                    {
                    otherlv_0=(Token)match(input,35,FOLLOW_35_in_rulecomponent_reference3219); 

                        	newLeafNode(otherlv_0, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0());
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1609:3: ( (lv_ref_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1610:1: (lv_ref_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1610:1: (lv_ref_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1611:3: lv_ref_1_0= RULE_IDENT
            {
            lv_ref_1_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3238); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1627:2: ( (lv_subscripts1_2_0= rulearray_subscripts ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==26) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1628:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1628:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1629:3: lv_subscripts1_2_0= rulearray_subscripts
                    {
                     
                    	        newCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscripts1Array_subscriptsParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3264);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1645:3: (otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==35) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1645:5: otherlv_3= '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    {
            	    otherlv_3=(Token)match(input,35,FOLLOW_35_in_rulecomponent_reference3278); 

            	        	newLeafNode(otherlv_3, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_3_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1649:1: ( (lv_ref1_4_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1650:1: (lv_ref1_4_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1650:1: (lv_ref1_4_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1651:3: lv_ref1_4_0= RULE_IDENT
            	    {
            	    lv_ref1_4_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3295); 

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

            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1667:2: ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    int alt27=2;
            	    int LA27_0 = input.LA(1);

            	    if ( (LA27_0==26) ) {
            	        alt27=1;
            	    }
            	    switch (alt27) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1668:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1668:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1669:3: lv_subscripts_5_0= rulearray_subscripts
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscriptsArray_subscriptsParserRuleCall_3_2_0()); 
            	            	    
            	            pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3321);
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
            	    break loop28;
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1693:1: entryRuleoutput_expression_list returns [EObject current=null] : iv_ruleoutput_expression_list= ruleoutput_expression_list EOF ;
    public final EObject entryRuleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleoutput_expression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1694:2: (iv_ruleoutput_expression_list= ruleoutput_expression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1695:2: iv_ruleoutput_expression_list= ruleoutput_expression_list EOF
            {
             newCompositeNode(grammarAccess.getOutput_expression_listRule()); 
            pushFollow(FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3360);
            iv_ruleoutput_expression_list=ruleoutput_expression_list();

            state._fsp--;

             current =iv_ruleoutput_expression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleoutput_expression_list3370); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1702:1: ruleoutput_expression_list returns [EObject current=null] : ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) ;
    public final EObject ruleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_epr_1_0 = null;

        EObject lv_Expr_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1705:28: ( ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1706:1: ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1706:1: ( () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1706:2: () ( (lv_epr_1_0= ruleexpression ) )? (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1706:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1707:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOutput_expression_listAccess().getOutput_expression_listAction_0(),
                        current);
                

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1712:2: ( (lv_epr_1_0= ruleexpression ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_UNSIGNED_NUMBER && LA29_0<=RULE_IDENT)||LA29_0==14||LA29_0==21||LA29_0==24||LA29_0==26||LA29_0==29||(LA29_0>=31 && LA29_0<=33)||LA29_0==35||(LA29_0>=43 && LA29_0<=46)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1713:1: (lv_epr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1713:1: (lv_epr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1714:3: lv_epr_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getOutput_expression_listAccess().getEprExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3425);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1730:3: (otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==34) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1730:5: otherlv_2= ',' ( (lv_Expr_3_0= ruleexpression ) )?
            	    {
            	    otherlv_2=(Token)match(input,34,FOLLOW_34_in_ruleoutput_expression_list3439); 

            	        	newLeafNode(otherlv_2, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1734:1: ( (lv_Expr_3_0= ruleexpression ) )?
            	    int alt30=2;
            	    int LA30_0 = input.LA(1);

            	    if ( ((LA30_0>=RULE_UNSIGNED_NUMBER && LA30_0<=RULE_IDENT)||LA30_0==14||LA30_0==21||LA30_0==24||LA30_0==26||LA30_0==29||(LA30_0>=31 && LA30_0<=33)||LA30_0==35||(LA30_0>=43 && LA30_0<=46)) ) {
            	        alt30=1;
            	    }
            	    switch (alt30) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1735:1: (lv_Expr_3_0= ruleexpression )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1735:1: (lv_Expr_3_0= ruleexpression )
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1736:3: lv_Expr_3_0= ruleexpression
            	            {
            	             
            	            	        newCompositeNode(grammarAccess.getOutput_expression_listAccess().getExprExpressionParserRuleCall_2_1_0()); 
            	            	    
            	            pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3460);
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
            	    break loop31;
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1760:1: entryRulearray_subscripts returns [EObject current=null] : iv_rulearray_subscripts= rulearray_subscripts EOF ;
    public final EObject entryRulearray_subscripts() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearray_subscripts = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1761:2: (iv_rulearray_subscripts= rulearray_subscripts EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1762:2: iv_rulearray_subscripts= rulearray_subscripts EOF
            {
             newCompositeNode(grammarAccess.getArray_subscriptsRule()); 
            pushFollow(FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3499);
            iv_rulearray_subscripts=rulearray_subscripts();

            state._fsp--;

             current =iv_rulearray_subscripts; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearray_subscripts3509); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1769:1: rulearray_subscripts returns [EObject current=null] : (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' ) ;
    public final EObject rulearray_subscripts() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_Sub_1_0 = null;

        EObject lv_Subscript_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1772:28: ( (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1773:1: (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1773:1: (otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1773:3: otherlv_0= '[' ( (lv_Sub_1_0= rulesubscript ) ) (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_rulearray_subscripts3546); 

                	newLeafNode(otherlv_0, grammarAccess.getArray_subscriptsAccess().getLeftSquareBracketKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1777:1: ( (lv_Sub_1_0= rulesubscript ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1778:1: (lv_Sub_1_0= rulesubscript )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1778:1: (lv_Sub_1_0= rulesubscript )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1779:3: lv_Sub_1_0= rulesubscript
            {
             
            	        newCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubSubscriptParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3567);
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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1795:2: (otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==34) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1795:4: otherlv_2= ',' ( (lv_Subscript_3_0= rulesubscript ) )
            	    {
            	    otherlv_2=(Token)match(input,34,FOLLOW_34_in_rulearray_subscripts3580); 

            	        	newLeafNode(otherlv_2, grammarAccess.getArray_subscriptsAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1799:1: ( (lv_Subscript_3_0= rulesubscript ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1800:1: (lv_Subscript_3_0= rulesubscript )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1800:1: (lv_Subscript_3_0= rulesubscript )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1801:3: lv_Subscript_3_0= rulesubscript
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubscriptSubscriptParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3601);
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
            	    break loop32;
                }
            } while (true);

            otherlv_4=(Token)match(input,28,FOLLOW_28_in_rulearray_subscripts3615); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1829:1: entryRulesubscript returns [EObject current=null] : iv_rulesubscript= rulesubscript EOF ;
    public final EObject entryRulesubscript() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesubscript = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1830:2: (iv_rulesubscript= rulesubscript EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1831:2: iv_rulesubscript= rulesubscript EOF
            {
             newCompositeNode(grammarAccess.getSubscriptRule()); 
            pushFollow(FOLLOW_rulesubscript_in_entryRulesubscript3651);
            iv_rulesubscript=rulesubscript();

            state._fsp--;

             current =iv_rulesubscript; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesubscript3661); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1838:1: rulesubscript returns [EObject current=null] : ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulesubscript() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1841:28: ( ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1842:1: ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1842:1: ( ( () otherlv_1= ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==15) ) {
                alt33=1;
            }
            else if ( ((LA33_0>=RULE_UNSIGNED_NUMBER && LA33_0<=RULE_IDENT)||LA33_0==14||LA33_0==21||LA33_0==24||LA33_0==26||LA33_0==29||(LA33_0>=31 && LA33_0<=33)||LA33_0==35||(LA33_0>=43 && LA33_0<=46)) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1842:2: ( () otherlv_1= ':' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1842:2: ( () otherlv_1= ':' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1842:3: () otherlv_1= ':'
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1842:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1843:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getSubscriptAccess().getSubscriptAction_0_0(),
                                current);
                        

                    }

                    otherlv_1=(Token)match(input,15,FOLLOW_15_in_rulesubscript3708); 

                        	newLeafNode(otherlv_1, grammarAccess.getSubscriptAccess().getColonKeyword_0_1());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1853:6: ( (lv_expr_2_0= ruleexpression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1853:6: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1854:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1854:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1855:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getSubscriptAccess().getExprExpressionParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulesubscript3736);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1879:1: entryRulefunction_arguments returns [EObject current=null] : iv_rulefunction_arguments= rulefunction_arguments EOF ;
    public final EObject entryRulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1880:2: (iv_rulefunction_arguments= rulefunction_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1881:2: iv_rulefunction_arguments= rulefunction_arguments EOF
            {
             newCompositeNode(grammarAccess.getFunction_argumentsRule()); 
            pushFollow(FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments3772);
            iv_rulefunction_arguments=rulefunction_arguments();

            state._fsp--;

             current =iv_rulefunction_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_arguments3782); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1888:1: rulefunction_arguments returns [EObject current=null] : ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) ;
    public final EObject rulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject lv_ArgExp_1_0 = null;

        EObject lv_Fun_Arg_Expr_2_0 = null;

        EObject lv_Fun_Arg_For_3_0 = null;

        EObject lv_name_arg_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1891:28: ( ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1892:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1892:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=RULE_UNSIGNED_NUMBER && LA35_0<=RULE_BOOL_VAL)||LA35_0==14||LA35_0==21||LA35_0==24||LA35_0==26||LA35_0==29||(LA35_0>=31 && LA35_0<=33)||LA35_0==35||(LA35_0>=43 && LA35_0<=46)) ) {
                alt35=1;
            }
            else if ( (LA35_0==RULE_IDENT) ) {
                int LA35_2 = input.LA(2);

                if ( (LA35_2==EOF||LA35_2==15||(LA35_2>=19 && LA35_2<=20)||(LA35_2>=22 && LA35_2<=26)||LA35_2==30||(LA35_2>=34 && LA35_2<=36)||(LA35_2>=39 && LA35_2<=52)) ) {
                    alt35=1;
                }
                else if ( (LA35_2==37) ) {
                    alt35=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1892:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1892:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1892:3: () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1892:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1893:5: 
                    {

                            current = forceCreateModelElement(
                                grammarAccess.getFunction_argumentsAccess().getFunction_argumentsAction_0_0(),
                                current);
                        

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1898:2: ( (lv_ArgExp_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1899:1: (lv_ArgExp_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1899:1: (lv_ArgExp_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1900:3: lv_ArgExp_1_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getArgExpExpressionParserRuleCall_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefunction_arguments3838);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1916:2: ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    int alt34=3;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==34) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==36) ) {
                        alt34=2;
                    }
                    switch (alt34) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1916:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1916:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1917:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1917:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1918:3: lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp
                            {
                             
                            	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments3860);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1935:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1935:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1936:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1936:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1937:3: lv_Fun_Arg_For_3_0= ruleFun_Arguments_for
                            {
                             
                            	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0()); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments3887);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1954:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1954:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1955:1: (lv_name_arg_4_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1955:1: (lv_name_arg_4_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1956:3: lv_name_arg_4_0= rulenamed_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getFunction_argumentsAccess().getName_argNamed_argumentsParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulefunction_arguments3917);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1980:1: entryRuleFun_Arguments_exp returns [EObject current=null] : iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF ;
    public final EObject entryRuleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_exp = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1981:2: (iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1982:2: iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF
            {
             newCompositeNode(grammarAccess.getFun_Arguments_expRule()); 
            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp3953);
            iv_ruleFun_Arguments_exp=ruleFun_Arguments_exp();

            state._fsp--;

             current =iv_ruleFun_Arguments_exp; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_exp3963); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1989:1: ruleFun_Arguments_exp returns [EObject current=null] : (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) ;
    public final EObject ruleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_Args_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1992:28: ( (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1993:1: (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1993:1: (otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1993:3: otherlv_0= ',' ( (lv_Args_1_0= rulefunction_arguments ) )
            {
            otherlv_0=(Token)match(input,34,FOLLOW_34_in_ruleFun_Arguments_exp4000); 

                	newLeafNode(otherlv_0, grammarAccess.getFun_Arguments_expAccess().getCommaKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1997:1: ( (lv_Args_1_0= rulefunction_arguments ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1998:1: (lv_Args_1_0= rulefunction_arguments )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1998:1: (lv_Args_1_0= rulefunction_arguments )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1999:3: lv_Args_1_0= rulefunction_arguments
            {
             
            	        newCompositeNode(grammarAccess.getFun_Arguments_expAccess().getArgsFunction_argumentsParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp4021);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2023:1: entryRuleFun_Arguments_for returns [EObject current=null] : iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF ;
    public final EObject entryRuleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_for = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2024:2: (iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2025:2: iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF
            {
             newCompositeNode(grammarAccess.getFun_Arguments_forRule()); 
            pushFollow(FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for4057);
            iv_ruleFun_Arguments_for=ruleFun_Arguments_for();

            state._fsp--;

             current =iv_ruleFun_Arguments_for; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_for4067); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2032:1: ruleFun_Arguments_for returns [EObject current=null] : (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) ;
    public final EObject ruleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_For_indices_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2035:28: ( (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2036:1: (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2036:1: (otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2036:3: otherlv_0= 'for' ( (lv_For_indices_1_0= rulefor_indices ) )
            {
            otherlv_0=(Token)match(input,36,FOLLOW_36_in_ruleFun_Arguments_for4104); 

                	newLeafNode(otherlv_0, grammarAccess.getFun_Arguments_forAccess().getForKeyword_0());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2040:1: ( (lv_For_indices_1_0= rulefor_indices ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2041:1: (lv_For_indices_1_0= rulefor_indices )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2041:1: (lv_For_indices_1_0= rulefor_indices )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2042:3: lv_For_indices_1_0= rulefor_indices
            {
             
            	        newCompositeNode(grammarAccess.getFun_Arguments_forAccess().getFor_indicesFor_indicesParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4125);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2066:1: entryRulenamed_arguments returns [EObject current=null] : iv_rulenamed_arguments= rulenamed_arguments EOF ;
    public final EObject entryRulenamed_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2067:2: (iv_rulenamed_arguments= rulenamed_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2068:2: iv_rulenamed_arguments= rulenamed_arguments EOF
            {
             newCompositeNode(grammarAccess.getNamed_argumentsRule()); 
            pushFollow(FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4161);
            iv_rulenamed_arguments=rulenamed_arguments();

            state._fsp--;

             current =iv_rulenamed_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_arguments4171); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2075:1: rulenamed_arguments returns [EObject current=null] : (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) ;
    public final EObject rulenamed_arguments() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_named_argument_0 = null;

        EObject lv_Named_arguments_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2078:28: ( (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2079:1: (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2079:1: (this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2080:5: this_named_argument_0= rulenamed_argument (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            {
             
                    newCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulenamed_argument_in_rulenamed_arguments4218);
            this_named_argument_0=rulenamed_argument();

            state._fsp--;

             
                    current = this_named_argument_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2088:1: (otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==34) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2088:3: otherlv_1= ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    {
                    otherlv_1=(Token)match(input,34,FOLLOW_34_in_rulenamed_arguments4230); 

                        	newLeafNode(otherlv_1, grammarAccess.getNamed_argumentsAccess().getCommaKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2092:1: ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2093:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2093:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2094:3: lv_Named_arguments_2_0= rulenamed_arguments
                    {
                     
                    	        newCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentsNamed_argumentsParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulenamed_arguments4251);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2118:1: entryRulenamed_argument returns [EObject current=null] : iv_rulenamed_argument= rulenamed_argument EOF ;
    public final EObject entryRulenamed_argument() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_argument = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2119:2: (iv_rulenamed_argument= rulenamed_argument EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2120:2: iv_rulenamed_argument= rulenamed_argument EOF
            {
             newCompositeNode(grammarAccess.getNamed_argumentRule()); 
            pushFollow(FOLLOW_rulenamed_argument_in_entryRulenamed_argument4289);
            iv_rulenamed_argument=rulenamed_argument();

            state._fsp--;

             current =iv_rulenamed_argument; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_argument4299); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2127:1: rulenamed_argument returns [EObject current=null] : ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulenamed_argument() throws RecognitionException {
        EObject current = null;

        Token lv_arg_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2130:28: ( ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2131:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2131:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2131:2: ( (lv_arg_0_0= RULE_IDENT ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleexpression ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2131:2: ( (lv_arg_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2132:1: (lv_arg_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2132:1: (lv_arg_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2133:3: lv_arg_0_0= RULE_IDENT
            {
            lv_arg_0_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulenamed_argument4341); 

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

            otherlv_1=(Token)match(input,37,FOLLOW_37_in_rulenamed_argument4358); 

                	newLeafNode(otherlv_1, grammarAccess.getNamed_argumentAccess().getEqualsSignKeyword_1());
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2153:1: ( (lv_expr_2_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2154:1: (lv_expr_2_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2154:1: (lv_expr_2_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2155:3: lv_expr_2_0= ruleexpression
            {
             
            	        newCompositeNode(grammarAccess.getNamed_argumentAccess().getExprExpressionParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulenamed_argument4379);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2179:1: entryRulefor_indices returns [EObject current=null] : iv_rulefor_indices= rulefor_indices EOF ;
    public final EObject entryRulefor_indices() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_indices = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2180:2: (iv_rulefor_indices= rulefor_indices EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2181:2: iv_rulefor_indices= rulefor_indices EOF
            {
             newCompositeNode(grammarAccess.getFor_indicesRule()); 
            pushFollow(FOLLOW_rulefor_indices_in_entryRulefor_indices4415);
            iv_rulefor_indices=rulefor_indices();

            state._fsp--;

             current =iv_rulefor_indices; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_indices4425); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2188:1: rulefor_indices returns [EObject current=null] : (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) ;
    public final EObject rulefor_indices() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_for_index_0 = null;

        EObject lv_For_index_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2191:28: ( (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2192:1: (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2192:1: (this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2193:5: this_for_index_0= rulefor_index (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            {
             
                    newCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_0()); 
                
            pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4472);
            this_for_index_0=rulefor_index();

            state._fsp--;

             
                    current = this_for_index_0; 
                    afterParserOrEnumRuleCall();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2201:1: (otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==34) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2201:3: otherlv_1= ',' ( (lv_For_index_2_0= rulefor_index ) )
            	    {
            	    otherlv_1=(Token)match(input,34,FOLLOW_34_in_rulefor_indices4484); 

            	        	newLeafNode(otherlv_1, grammarAccess.getFor_indicesAccess().getCommaKeyword_1_0());
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2205:1: ( (lv_For_index_2_0= rulefor_index ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2206:1: (lv_For_index_2_0= rulefor_index )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2206:1: (lv_For_index_2_0= rulefor_index )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2207:3: lv_For_index_2_0= rulefor_index
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexFor_indexParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4505);
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
            	    break loop37;
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2231:1: entryRulefor_index returns [EObject current=null] : iv_rulefor_index= rulefor_index EOF ;
    public final EObject entryRulefor_index() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_index = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2232:2: (iv_rulefor_index= rulefor_index EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2233:2: iv_rulefor_index= rulefor_index EOF
            {
             newCompositeNode(grammarAccess.getFor_indexRule()); 
            pushFollow(FOLLOW_rulefor_index_in_entryRulefor_index4543);
            iv_rulefor_index=rulefor_index();

            state._fsp--;

             current =iv_rulefor_index; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_index4553); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2240:1: rulefor_index returns [EObject current=null] : ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) ;
    public final EObject rulefor_index() throws RecognitionException {
        EObject current = null;

        Token lv_index_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2243:28: ( ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2244:1: ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2244:1: ( ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2244:2: ( (lv_index_0_0= RULE_IDENT ) ) (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2244:2: ( (lv_index_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2245:1: (lv_index_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2245:1: (lv_index_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2246:3: lv_index_0_0= RULE_IDENT
            {
            lv_index_0_0=(Token)match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulefor_index4595); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2262:2: (otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==38) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2262:4: otherlv_1= 'in' ( (lv_expr_2_0= ruleexpression ) )
                    {
                    otherlv_1=(Token)match(input,38,FOLLOW_38_in_rulefor_index4613); 

                        	newLeafNode(otherlv_1, grammarAccess.getFor_indexAccess().getInKeyword_1_0());
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2266:1: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2267:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2267:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2268:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        newCompositeNode(grammarAccess.getFor_indexAccess().getExprExpressionParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefor_index4634);
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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2292:1: entryRulemul_op_mul returns [String current=null] : iv_rulemul_op_mul= rulemul_op_mul EOF ;
    public final String entryRulemul_op_mul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_mul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2293:2: (iv_rulemul_op_mul= rulemul_op_mul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2294:2: iv_rulemul_op_mul= rulemul_op_mul EOF
            {
             newCompositeNode(grammarAccess.getMul_op_mulRule()); 
            pushFollow(FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4673);
            iv_rulemul_op_mul=rulemul_op_mul();

            state._fsp--;

             current =iv_rulemul_op_mul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_mul4684); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2301:1: rulemul_op_mul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken rulemul_op_mul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2304:28: (kw= '*' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2306:2: kw= '*'
            {
            kw=(Token)match(input,39,FOLLOW_39_in_rulemul_op_mul4721); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2319:1: entryRulemul_op_div returns [String current=null] : iv_rulemul_op_div= rulemul_op_div EOF ;
    public final String entryRulemul_op_div() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_div = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2320:2: (iv_rulemul_op_div= rulemul_op_div EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2321:2: iv_rulemul_op_div= rulemul_op_div EOF
            {
             newCompositeNode(grammarAccess.getMul_op_divRule()); 
            pushFollow(FOLLOW_rulemul_op_div_in_entryRulemul_op_div4761);
            iv_rulemul_op_div=rulemul_op_div();

            state._fsp--;

             current =iv_rulemul_op_div.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_div4772); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2328:1: rulemul_op_div returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '/' ;
    public final AntlrDatatypeRuleToken rulemul_op_div() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2331:28: (kw= '/' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2333:2: kw= '/'
            {
            kw=(Token)match(input,40,FOLLOW_40_in_rulemul_op_div4809); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2346:1: entryRulemul_op_dotmul returns [String current=null] : iv_rulemul_op_dotmul= rulemul_op_dotmul EOF ;
    public final String entryRulemul_op_dotmul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotmul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2347:2: (iv_rulemul_op_dotmul= rulemul_op_dotmul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2348:2: iv_rulemul_op_dotmul= rulemul_op_dotmul EOF
            {
             newCompositeNode(grammarAccess.getMul_op_dotmulRule()); 
            pushFollow(FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul4849);
            iv_rulemul_op_dotmul=rulemul_op_dotmul();

            state._fsp--;

             current =iv_rulemul_op_dotmul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotmul4860); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2355:1: rulemul_op_dotmul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.*' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotmul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2358:28: (kw= '.*' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2360:2: kw= '.*'
            {
            kw=(Token)match(input,41,FOLLOW_41_in_rulemul_op_dotmul4897); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2373:1: entryRulemul_op_dotdiv returns [String current=null] : iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF ;
    public final String entryRulemul_op_dotdiv() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotdiv = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2374:2: (iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2375:2: iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF
            {
             newCompositeNode(grammarAccess.getMul_op_dotdivRule()); 
            pushFollow(FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv4937);
            iv_rulemul_op_dotdiv=rulemul_op_dotdiv();

            state._fsp--;

             current =iv_rulemul_op_dotdiv.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotdiv4948); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2382:1: rulemul_op_dotdiv returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= './' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotdiv() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2385:28: (kw= './' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2387:2: kw= './'
            {
            kw=(Token)match(input,42,FOLLOW_42_in_rulemul_op_dotdiv4985); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2400:1: entryRuleadd_op_plus returns [String current=null] : iv_ruleadd_op_plus= ruleadd_op_plus EOF ;
    public final String entryRuleadd_op_plus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_plus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2401:2: (iv_ruleadd_op_plus= ruleadd_op_plus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2402:2: iv_ruleadd_op_plus= ruleadd_op_plus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_plusRule()); 
            pushFollow(FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus5025);
            iv_ruleadd_op_plus=ruleadd_op_plus();

            state._fsp--;

             current =iv_ruleadd_op_plus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_plus5036); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2409:1: ruleadd_op_plus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_plus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2412:28: (kw= '+' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2414:2: kw= '+'
            {
            kw=(Token)match(input,43,FOLLOW_43_in_ruleadd_op_plus5073); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2427:1: entryRuleadd_op_minus returns [String current=null] : iv_ruleadd_op_minus= ruleadd_op_minus EOF ;
    public final String entryRuleadd_op_minus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_minus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2428:2: (iv_ruleadd_op_minus= ruleadd_op_minus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2429:2: iv_ruleadd_op_minus= ruleadd_op_minus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_minusRule()); 
            pushFollow(FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5113);
            iv_ruleadd_op_minus=ruleadd_op_minus();

            state._fsp--;

             current =iv_ruleadd_op_minus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_minus5124); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2436:1: ruleadd_op_minus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_minus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2439:28: (kw= '-' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2441:2: kw= '-'
            {
            kw=(Token)match(input,44,FOLLOW_44_in_ruleadd_op_minus5161); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2454:1: entryRuleadd_op_dotplus returns [String current=null] : iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF ;
    public final String entryRuleadd_op_dotplus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotplus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2455:2: (iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2456:2: iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_dotplusRule()); 
            pushFollow(FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5201);
            iv_ruleadd_op_dotplus=ruleadd_op_dotplus();

            state._fsp--;

             current =iv_ruleadd_op_dotplus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotplus5212); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2463:1: ruleadd_op_dotplus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotplus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2466:28: (kw= '.+' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2468:2: kw= '.+'
            {
            kw=(Token)match(input,45,FOLLOW_45_in_ruleadd_op_dotplus5249); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2481:1: entryRuleadd_op_dotminus returns [String current=null] : iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF ;
    public final String entryRuleadd_op_dotminus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotminus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2482:2: (iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2483:2: iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF
            {
             newCompositeNode(grammarAccess.getAdd_op_dotminusRule()); 
            pushFollow(FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5289);
            iv_ruleadd_op_dotminus=ruleadd_op_dotminus();

            state._fsp--;

             current =iv_ruleadd_op_dotminus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotminus5300); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2490:1: ruleadd_op_dotminus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotminus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2493:28: (kw= '.-' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2495:2: kw= '.-'
            {
            kw=(Token)match(input,46,FOLLOW_46_in_ruleadd_op_dotminus5337); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2508:1: entryRulerel_op_Less_then returns [String current=null] : iv_rulerel_op_Less_then= rulerel_op_Less_then EOF ;
    public final String entryRulerel_op_Less_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2509:2: (iv_rulerel_op_Less_then= rulerel_op_Less_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2510:2: iv_rulerel_op_Less_then= rulerel_op_Less_then EOF
            {
             newCompositeNode(grammarAccess.getRel_op_Less_thenRule()); 
            pushFollow(FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5377);
            iv_rulerel_op_Less_then=rulerel_op_Less_then();

            state._fsp--;

             current =iv_rulerel_op_Less_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_then5388); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2517:1: rulerel_op_Less_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2520:28: (kw= '<' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2522:2: kw= '<'
            {
            kw=(Token)match(input,47,FOLLOW_47_in_rulerel_op_Less_then5425); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2535:1: entryRulerel_op_Less_equal returns [String current=null] : iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF ;
    public final String entryRulerel_op_Less_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2536:2: (iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2537:2: iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF
            {
             newCompositeNode(grammarAccess.getRel_op_Less_equalRule()); 
            pushFollow(FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5465);
            iv_rulerel_op_Less_equal=rulerel_op_Less_equal();

            state._fsp--;

             current =iv_rulerel_op_Less_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_equal5476); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2544:1: rulerel_op_Less_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<=' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2547:28: (kw= '<=' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2549:2: kw= '<='
            {
            kw=(Token)match(input,48,FOLLOW_48_in_rulerel_op_Less_equal5513); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2562:1: entryRulerel_op_greater_then returns [String current=null] : iv_rulerel_op_greater_then= rulerel_op_greater_then EOF ;
    public final String entryRulerel_op_greater_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2563:2: (iv_rulerel_op_greater_then= rulerel_op_greater_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2564:2: iv_rulerel_op_greater_then= rulerel_op_greater_then EOF
            {
             newCompositeNode(grammarAccess.getRel_op_greater_thenRule()); 
            pushFollow(FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5553);
            iv_rulerel_op_greater_then=rulerel_op_greater_then();

            state._fsp--;

             current =iv_rulerel_op_greater_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_then5564); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2571:1: rulerel_op_greater_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2574:28: (kw= '>' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2576:2: kw= '>'
            {
            kw=(Token)match(input,49,FOLLOW_49_in_rulerel_op_greater_then5601); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2589:1: entryRulerel_op_greater_equal returns [String current=null] : iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF ;
    public final String entryRulerel_op_greater_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2590:2: (iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2591:2: iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF
            {
             newCompositeNode(grammarAccess.getRel_op_greater_equalRule()); 
            pushFollow(FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5641);
            iv_rulerel_op_greater_equal=rulerel_op_greater_equal();

            state._fsp--;

             current =iv_rulerel_op_greater_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_equal5652); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2598:1: rulerel_op_greater_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>=' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2601:28: (kw= '>=' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2603:2: kw= '>='
            {
            kw=(Token)match(input,50,FOLLOW_50_in_rulerel_op_greater_equal5689); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2616:1: entryRulerel_op_assignment returns [String current=null] : iv_rulerel_op_assignment= rulerel_op_assignment EOF ;
    public final String entryRulerel_op_assignment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_assignment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2617:2: (iv_rulerel_op_assignment= rulerel_op_assignment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2618:2: iv_rulerel_op_assignment= rulerel_op_assignment EOF
            {
             newCompositeNode(grammarAccess.getRel_op_assignmentRule()); 
            pushFollow(FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment5729);
            iv_rulerel_op_assignment=rulerel_op_assignment();

            state._fsp--;

             current =iv_rulerel_op_assignment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_assignment5740); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2625:1: rulerel_op_assignment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '==' ;
    public final AntlrDatatypeRuleToken rulerel_op_assignment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2628:28: (kw= '==' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2630:2: kw= '=='
            {
            kw=(Token)match(input,51,FOLLOW_51_in_rulerel_op_assignment5777); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2643:1: entryRulerel_op_Oper returns [String current=null] : iv_rulerel_op_Oper= rulerel_op_Oper EOF ;
    public final String entryRulerel_op_Oper() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Oper = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2644:2: (iv_rulerel_op_Oper= rulerel_op_Oper EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2645:2: iv_rulerel_op_Oper= rulerel_op_Oper EOF
            {
             newCompositeNode(grammarAccess.getRel_op_OperRule()); 
            pushFollow(FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper5817);
            iv_rulerel_op_Oper=rulerel_op_Oper();

            state._fsp--;

             current =iv_rulerel_op_Oper.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Oper5828); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2652:1: rulerel_op_Oper returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<>' ;
    public final AntlrDatatypeRuleToken rulerel_op_Oper() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2655:28: (kw= '<>' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2657:2: kw= '<>'
            {
            kw=(Token)match(input,52,FOLLOW_52_in_rulerel_op_Oper5865); 

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


    // $ANTLR start "entryRulestring_comment"
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2672:1: entryRulestring_comment returns [String current=null] : iv_rulestring_comment= rulestring_comment EOF ;
    public final String entryRulestring_comment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulestring_comment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2673:2: (iv_rulestring_comment= rulestring_comment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2674:2: iv_rulestring_comment= rulestring_comment EOF
            {
             newCompositeNode(grammarAccess.getString_commentRule()); 
            pushFollow(FOLLOW_rulestring_comment_in_entryRulestring_comment5907);
            iv_rulestring_comment=rulestring_comment();

            state._fsp--;

             current =iv_rulestring_comment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulestring_comment5918); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2681:1: rulestring_comment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? ;
    public final AntlrDatatypeRuleToken rulestring_comment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token kw=null;
        Token this_STRING_2=null;

         enterRule(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2684:28: ( (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2685:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2685:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==RULE_STRING) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2685:6: this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )*
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment5958); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_0()); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2692:1: (kw= '+' this_STRING_2= RULE_STRING )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==43) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2693:2: kw= '+' this_STRING_2= RULE_STRING
                    	    {
                    	    kw=(Token)match(input,43,FOLLOW_43_in_rulestring_comment5977); 

                    	            current.merge(kw);
                    	            newLeafNode(kw, grammarAccess.getString_commentAccess().getPlusSignKeyword_1_0()); 
                    	        
                    	    this_STRING_2=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment5992); 

                    	    		current.merge(this_STRING_2);
                    	        
                    	     
                    	        newLeafNode(this_STRING_2, grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_1_1()); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop39;
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


    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA20_eotS =
        "\21\uffff";
    static final String DFA20_eofS =
        "\6\uffff\1\15\11\uffff\1\15";
    static final String DFA20_minS =
        "\1\4\4\uffff\1\10\1\17\7\uffff\1\10\1\uffff\1\17";
    static final String DFA20_maxS =
        "\1\43\4\uffff\1\10\1\64\7\uffff\1\10\1\uffff\1\64";
    static final String DFA20_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1"+
        "\10\1\uffff\1\5\1\uffff";
    static final String DFA20_specialS =
        "\21\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\1\1\2\1\3\1\4\1\6\17\uffff\1\11\1\uffff\1\12\2\uffff\1\13"+
            "\1\uffff\1\14\1\7\1\10\1\uffff\1\5",
            "",
            "",
            "",
            "",
            "\1\6",
            "\6\15\1\uffff\2\15\1\17\4\15\1\uffff\1\15\3\uffff\1\15\1\16"+
            "\1\15\2\uffff\16\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\20",
            "",
            "\6\15\1\uffff\2\15\1\17\4\15\1\uffff\1\15\3\uffff\1\15\1\16"+
            "\1\15\2\uffff\16\15"
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "1037:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | (otherlv_8= '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) otherlv_10= ')' ) | (otherlv_11= '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) (otherlv_13= ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* otherlv_15= ']' ) | (otherlv_16= '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) otherlv_18= '}' ) | ( (lv_End_19_0= 'end' ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_attribute85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleconditional_attribute122 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_attribute143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_entryRuleexpression180 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleexpression236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_ruleexpression264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_entryRulesimple_expression299 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesimple_expression309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression355 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_rulesimple_expression368 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression389 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_rulesimple_expression402 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr463 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_expr473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleconditional_expr510 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr531 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleconditional_expr543 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr564 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_ruleconditional_expr577 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr598 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleconditional_expr610 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr631 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_18_in_ruleconditional_expr646 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_entryRulelogical_expression704 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_expression714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression761 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_rulelogical_expression773 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression794 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rulelogical_term_in_entryRulelogical_term832 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_term842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term889 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_rulelogical_term901 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term922 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_entryRulelogical_factor960 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_factor970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rulelogical_factor1008 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulerelation_in_rulelogical_factor1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerelation_in_entryRulerelation1067 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerelation1077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1124 = new BitSet(new long[]{0x001F800000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_rulerelation1147 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_rulerelation1166 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_rulerelation1185 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_rulerelation1204 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_rulerelation1223 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_rulerelation1242 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1304 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearithmetic_expression1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1362 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1381 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1400 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1419 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1444 = new BitSet(new long[]{0x0000780000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1468 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1487 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1506 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1525 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1549 = new BitSet(new long[]{0x0000780000000002L});
    public static final BitSet FOLLOW_ruleterm_in_entryRuleterm1587 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleterm1597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm1644 = new BitSet(new long[]{0x0000078000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_ruleterm1667 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulemul_op_div_in_ruleterm1686 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_ruleterm1705 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_ruleterm1724 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm1748 = new BitSet(new long[]{0x0000078000000002L});
    public static final BitSet FOLLOW_rulefactor_in_entryRulefactor1786 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefactor1796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor1843 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_22_in_rulefactor1856 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_23_in_rulefactor1874 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_entryRuleprimary1934 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleprimary1944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary1986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleprimary2014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleprimary2042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_VAL_in_ruleprimary2070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_ruleprimary2103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_ruleprimary2130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_ruleprimary2157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleprimary2183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleprimary2202 = new BitSet(new long[]{0x0000780FA72041F0L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_ruleprimary2223 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleprimary2235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleprimary2255 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2276 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_27_in_ruleprimary2289 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2310 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_28_in_ruleprimary2324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleprimary2344 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleprimary2365 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleprimary2377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleprimary2402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_entryRulename_Function2451 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_Function2461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_in_rulename_Function2508 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_rulename_Function2528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2564 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleinitial_ref2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleinitial_ref2611 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleinitial_ref2633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_entryRuleExprDer2668 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExprDer2678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleExprDer2715 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleExprDer2736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args2772 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_call_args2782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rulefunction_call_args2828 = new BitSet(new long[]{0x0000780BA72041F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_rulefunction_call_args2849 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_rulefunction_call_args2862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list_in_entryRuleexpression_list2898 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression_list2908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list2954 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_ruleexpression_list2967 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list2988 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rulename_in_entryRulename3026 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename3036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rulename3074 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3093 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_rulename3111 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3128 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3171 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_reference3181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rulecomponent_reference3219 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3238 = new BitSet(new long[]{0x0000000804000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3264 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_rulecomponent_reference3278 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3295 = new BitSet(new long[]{0x0000000804000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3321 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3360 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleoutput_expression_list3370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3425 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_ruleoutput_expression_list3439 = new BitSet(new long[]{0x0000780FA52041F2L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3460 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3499 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearray_subscripts3509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulearray_subscripts3546 = new BitSet(new long[]{0x0000780BA520C1F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3567 = new BitSet(new long[]{0x0000000410000000L});
    public static final BitSet FOLLOW_34_in_rulearray_subscripts3580 = new BitSet(new long[]{0x0000780BA520C1F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3601 = new BitSet(new long[]{0x0000000410000000L});
    public static final BitSet FOLLOW_28_in_rulearray_subscripts3615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesubscript_in_entryRulesubscript3651 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesubscript3661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rulesubscript3708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulesubscript3736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments3772 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_arguments3782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefunction_arguments3838 = new BitSet(new long[]{0x0000001400000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments3860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments3887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulefunction_arguments3917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp3953 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_exp3963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleFun_Arguments_exp4000 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp4021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for4057 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_for4067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleFun_Arguments_for4104 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4161 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_arguments4171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_rulenamed_arguments4218 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_rulenamed_arguments4230 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulenamed_arguments4251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_entryRulenamed_argument4289 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_argument4299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulenamed_argument4341 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_rulenamed_argument4358 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulenamed_argument4379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_indices_in_entryRulefor_indices4415 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_indices4425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4472 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_rulefor_indices4484 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4505 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rulefor_index_in_entryRulefor_index4543 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_index4553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulefor_index4595 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_rulefor_index4613 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_index4634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4673 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_mul4684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rulemul_op_mul4721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_div_in_entryRulemul_op_div4761 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_div4772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rulemul_op_div4809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul4849 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotmul4860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rulemul_op_dotmul4897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv4937 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotdiv4948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rulemul_op_dotdiv4985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus5025 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_plus5036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleadd_op_plus5073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5113 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_minus5124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleadd_op_minus5161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5201 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotplus5212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleadd_op_dotplus5249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5289 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotminus5300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleadd_op_dotminus5337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5377 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_then5388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_rulerel_op_Less_then5425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5465 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_equal5476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_rulerel_op_Less_equal5513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5553 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_then5564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rulerel_op_greater_then5601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5641 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_equal5652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_rulerel_op_greater_equal5689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment5729 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_assignment5740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rulerel_op_assignment5777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper5817 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Oper5828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_rulerel_op_Oper5865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_entryRulestring_comment5907 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulestring_comment5918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment5958 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_rulestring_comment5977 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment5992 = new BitSet(new long[]{0x0000080000000002L});

}