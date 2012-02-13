package org.openmodelica.modelicaml.editor.xtext.condition.parser.antlr.internal; 

import java.io.InputStream;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.parser.IAstFactory;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.openmodelica.modelicaml.editor.xtext.condition.services.ConditionalattributeGrammarAccess;

@SuppressWarnings("all")
public class InternalConditionalattributeParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNSIGNED_NUMBER", "RULE_INT", "RULE_STRING", "RULE_BOOL_VAL", "RULE_IDENT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'if'", "':'", "'then'", "'elseif'", "'else'", "'or'", "'and'", "'not'", "'^'", "'.^'", "'('", "')'", "'['", "';'", "']'", "'{'", "'}'", "'end'", "'initial'", "'der'", "','", "'.'", "'for'", "'='", "'in'", "'*'", "'/'", "'.*'", "'./'", "'+'", "'-'", "'.+'", "'.-'", "'<'", "'<='", "'>'", "'>='", "'=='", "'<>'"
    };
    public static final int RULE_ID=9;
    public static final int RULE_STRING=6;
    public static final int RULE_IDENT=8;
    public static final int RULE_ANY_OTHER=13;
    public static final int RULE_INT=5;
    public static final int RULE_UNSIGNED_NUMBER=4;
    public static final int RULE_BOOL_VAL=7;
    public static final int RULE_WS=12;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=10;

        public InternalConditionalattributeParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g"; }



     	private ConditionalattributeGrammarAccess grammarAccess;
     	
        public InternalConditionalattributeParser(TokenStream input, IAstFactory factory, ConditionalattributeGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "conditional_attribute";	
       	}
       	
       	@Override
       	protected ConditionalattributeGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleconditional_attribute
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:77:1: entryRuleconditional_attribute returns [EObject current=null] : iv_ruleconditional_attribute= ruleconditional_attribute EOF ;
    public final EObject entryRuleconditional_attribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_attribute = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:78:2: (iv_ruleconditional_attribute= ruleconditional_attribute EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:79:2: iv_ruleconditional_attribute= ruleconditional_attribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConditional_attributeRule(), currentNode); 
            pushFollow(FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute75);
            iv_ruleconditional_attribute=ruleconditional_attribute();
            _fsp--;

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
    // $ANTLR end entryRuleconditional_attribute


    // $ANTLR start ruleconditional_attribute
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:86:1: ruleconditional_attribute returns [EObject current=null] : ( 'if' ( (lv_expr_1_0= ruleexpression ) ) )? ;
    public final EObject ruleconditional_attribute() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:91:6: ( ( 'if' ( (lv_expr_1_0= ruleexpression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:92:1: ( 'if' ( (lv_expr_1_0= ruleexpression ) ) )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:92:1: ( 'if' ( (lv_expr_1_0= ruleexpression ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==14) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:92:3: 'if' ( (lv_expr_1_0= ruleexpression ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleconditional_attribute120); 

                            createLeafNode(grammarAccess.getConditional_attributeAccess().getIfKeyword_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:96:1: ( (lv_expr_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:97:1: (lv_expr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:97:1: (lv_expr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:98:3: lv_expr_1_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConditional_attributeAccess().getExprExpressionParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_attribute141);
                    lv_expr_1_0=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConditional_attributeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expr",
                    	        		lv_expr_1_0, 
                    	        		"expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleconditional_attribute


    // $ANTLR start entryRuleexpression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:128:1: entryRuleexpression returns [EObject current=null] : iv_ruleexpression= ruleexpression EOF ;
    public final EObject entryRuleexpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:129:2: (iv_ruleexpression= ruleexpression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:130:2: iv_ruleexpression= ruleexpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleexpression_in_entryRuleexpression178);
            iv_ruleexpression=ruleexpression();
            _fsp--;

             current =iv_ruleexpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression188); 

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
    // $ANTLR end entryRuleexpression


    // $ANTLR start ruleexpression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:137:1: ruleexpression returns [EObject current=null] : ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) ;
    public final EObject ruleexpression() throws RecognitionException {
        EObject current = null;

        EObject lv_Expr_0_0 = null;

        EObject this_conditional_expr_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:142:6: ( ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:143:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:143:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
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
                    new NoViableAltException("143:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:143:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:143:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:144:1: (lv_Expr_0_0= rulesimple_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:144:1: (lv_Expr_0_0= rulesimple_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:145:3: lv_Expr_0_0= rulesimple_expression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getExprSimple_expressionParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulesimple_expression_in_ruleexpression234);
                    lv_Expr_0_0=rulesimple_expression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExpressionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Expr",
                    	        		lv_Expr_0_0, 
                    	        		"simple_expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:169:5: this_conditional_expr_1= ruleconditional_expr
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getConditional_exprParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleconditional_expr_in_ruleexpression262);
                    this_conditional_expr_1=ruleconditional_expr();
                    _fsp--;

                     
                            current = this_conditional_expr_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleexpression


    // $ANTLR start entryRulesimple_expression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:185:1: entryRulesimple_expression returns [EObject current=null] : iv_rulesimple_expression= rulesimple_expression EOF ;
    public final EObject entryRulesimple_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesimple_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:186:2: (iv_rulesimple_expression= rulesimple_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:187:2: iv_rulesimple_expression= rulesimple_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSimple_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulesimple_expression_in_entryRulesimple_expression297);
            iv_rulesimple_expression=rulesimple_expression();
            _fsp--;

             current =iv_rulesimple_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesimple_expression307); 

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
    // $ANTLR end entryRulesimple_expression


    // $ANTLR start rulesimple_expression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:194:1: rulesimple_expression returns [EObject current=null] : ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) ;
    public final EObject rulesimple_expression() throws RecognitionException {
        EObject current = null;

        EObject lv_Log_Exp_0_0 = null;

        EObject lv_S_Logical_expression_2_0 = null;

        EObject lv_L_Logical_expression_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:199:6: ( ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:200:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:200:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:200:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:200:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:201:1: (lv_Log_Exp_0_0= rulelogical_expression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:201:1: (lv_Log_Exp_0_0= rulelogical_expression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:202:3: lv_Log_Exp_0_0= rulelogical_expression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getSimple_expressionAccess().getLog_ExpLogical_expressionParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression353);
            lv_Log_Exp_0_0=rulelogical_expression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getSimple_expressionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"Log_Exp",
            	        		lv_Log_Exp_0_0, 
            	        		"logical_expression", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:224:2: ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:224:4: ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    {
                    match(input,15,FOLLOW_15_in_rulesimple_expression364); 

                            createLeafNode(grammarAccess.getSimple_expressionAccess().getColonKeyword_1_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:228:1: ( (lv_S_Logical_expression_2_0= rulelogical_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:229:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:229:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:230:3: lv_S_Logical_expression_2_0= rulelogical_expression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSimple_expressionAccess().getS_Logical_expressionLogical_expressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression385);
                    lv_S_Logical_expression_2_0=rulelogical_expression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSimple_expressionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"S_Logical_expression",
                    	        		lv_S_Logical_expression_2_0, 
                    	        		"logical_expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:252:2: ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==15) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:252:4: ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            {
                            match(input,15,FOLLOW_15_in_rulesimple_expression396); 

                                    createLeafNode(grammarAccess.getSimple_expressionAccess().getColonKeyword_1_2_0(), null); 
                                
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:256:1: ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:257:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:257:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:258:3: lv_L_Logical_expression_4_0= rulelogical_expression
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getSimple_expressionAccess().getL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression417);
                            lv_L_Logical_expression_4_0=rulelogical_expression();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getSimple_expressionRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"L_Logical_expression",
                            	        		lv_L_Logical_expression_4_0, 
                            	        		"logical_expression", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulesimple_expression


    // $ANTLR start entryRuleconditional_expr
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:288:1: entryRuleconditional_expr returns [EObject current=null] : iv_ruleconditional_expr= ruleconditional_expr EOF ;
    public final EObject entryRuleconditional_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_expr = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:289:2: (iv_ruleconditional_expr= ruleconditional_expr EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:290:2: iv_ruleconditional_expr= ruleconditional_expr EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConditional_exprRule(), currentNode); 
            pushFollow(FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr457);
            iv_ruleconditional_expr=ruleconditional_expr();
            _fsp--;

             current =iv_ruleconditional_expr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_expr467); 

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
    // $ANTLR end entryRuleconditional_expr


    // $ANTLR start ruleconditional_expr
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:297:1: ruleconditional_expr returns [EObject current=null] : ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) ;
    public final EObject ruleconditional_expr() throws RecognitionException {
        EObject current = null;

        EObject lv_ifexpr_1_0 = null;

        EObject lv_thenexpr_3_0 = null;

        EObject lv_elseifexpr_5_0 = null;

        EObject lv_trueexpr_7_0 = null;

        EObject lv_falseexpr_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:302:6: ( ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:303:1: ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:303:1: ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:303:3: 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            {
            match(input,14,FOLLOW_14_in_ruleconditional_expr502); 

                    createLeafNode(grammarAccess.getConditional_exprAccess().getIfKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:307:1: ( (lv_ifexpr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:308:1: (lv_ifexpr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:308:1: (lv_ifexpr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:309:3: lv_ifexpr_1_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getIfexprExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr523);
            lv_ifexpr_1_0=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConditional_exprRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"ifexpr",
            	        		lv_ifexpr_1_0, 
            	        		"expression", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,16,FOLLOW_16_in_ruleconditional_expr533); 

                    createLeafNode(grammarAccess.getConditional_exprAccess().getThenKeyword_2(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:335:1: ( (lv_thenexpr_3_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:336:1: (lv_thenexpr_3_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:336:1: (lv_thenexpr_3_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:337:3: lv_thenexpr_3_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getThenexprExpressionParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr554);
            lv_thenexpr_3_0=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConditional_exprRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"thenexpr",
            	        		lv_thenexpr_3_0, 
            	        		"expression", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:359:2: ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==17) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:359:4: 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) )
            	    {
            	    match(input,17,FOLLOW_17_in_ruleconditional_expr565); 

            	            createLeafNode(grammarAccess.getConditional_exprAccess().getElseifKeyword_4_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:363:1: ( (lv_elseifexpr_5_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:364:1: (lv_elseifexpr_5_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:364:1: (lv_elseifexpr_5_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:365:3: lv_elseifexpr_5_0= ruleexpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getElseifexprExpressionParserRuleCall_4_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr586);
            	    lv_elseifexpr_5_0=ruleexpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getConditional_exprRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"elseifexpr",
            	    	        		lv_elseifexpr_5_0, 
            	    	        		"expression", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,16,FOLLOW_16_in_ruleconditional_expr596); 

            	            createLeafNode(grammarAccess.getConditional_exprAccess().getThenKeyword_4_2(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:391:1: ( (lv_trueexpr_7_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:392:1: (lv_trueexpr_7_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:392:1: (lv_trueexpr_7_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:393:3: lv_trueexpr_7_0= ruleexpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getTrueexprExpressionParserRuleCall_4_3_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr617);
            	    lv_trueexpr_7_0=ruleexpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getConditional_exprRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"trueexpr",
            	    	        		lv_trueexpr_7_0, 
            	    	        		"expression", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:415:4: ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:415:6: 'else' ( (lv_falseexpr_9_0= ruleexpression ) )
            {
            match(input,18,FOLLOW_18_in_ruleconditional_expr630); 

                    createLeafNode(grammarAccess.getConditional_exprAccess().getElseKeyword_5_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:419:1: ( (lv_falseexpr_9_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:420:1: (lv_falseexpr_9_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:420:1: (lv_falseexpr_9_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:421:3: lv_falseexpr_9_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getFalseexprExpressionParserRuleCall_5_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr651);
            lv_falseexpr_9_0=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConditional_exprRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"falseexpr",
            	        		lv_falseexpr_9_0, 
            	        		"expression", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleconditional_expr


    // $ANTLR start entryRulelogical_expression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:451:1: entryRulelogical_expression returns [EObject current=null] : iv_rulelogical_expression= rulelogical_expression EOF ;
    public final EObject entryRulelogical_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:452:2: (iv_rulelogical_expression= rulelogical_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:453:2: iv_rulelogical_expression= rulelogical_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_expression_in_entryRulelogical_expression688);
            iv_rulelogical_expression=rulelogical_expression();
            _fsp--;

             current =iv_rulelogical_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_expression698); 

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
    // $ANTLR end entryRulelogical_expression


    // $ANTLR start rulelogical_expression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:460:1: rulelogical_expression returns [EObject current=null] : (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) ;
    public final EObject rulelogical_expression() throws RecognitionException {
        EObject current = null;

        EObject this_logical_term_0 = null;

        EObject lv_Logical_term_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:465:6: ( (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:466:1: (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:466:1: (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:467:5: this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression745);
            this_logical_term_0=rulelogical_term();
            _fsp--;

             
                    current = this_logical_term_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:475:1: ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==19) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:475:3: 'or' ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    {
            	    match(input,19,FOLLOW_19_in_rulelogical_expression755); 

            	            createLeafNode(grammarAccess.getLogical_expressionAccess().getOrKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:479:1: ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:480:1: (lv_Logical_term_2_0= rulelogical_term )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:480:1: (lv_Logical_term_2_0= rulelogical_term )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:481:3: lv_Logical_term_2_0= rulelogical_term
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termLogical_termParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression776);
            	    lv_Logical_term_2_0=rulelogical_term();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getLogical_expressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"Logical_term",
            	    	        		lv_Logical_term_2_0, 
            	    	        		"logical_term", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulelogical_expression


    // $ANTLR start entryRulelogical_term
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:511:1: entryRulelogical_term returns [EObject current=null] : iv_rulelogical_term= rulelogical_term EOF ;
    public final EObject entryRulelogical_term() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_term = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:512:2: (iv_rulelogical_term= rulelogical_term EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:513:2: iv_rulelogical_term= rulelogical_term EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_termRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_term_in_entryRulelogical_term814);
            iv_rulelogical_term=rulelogical_term();
            _fsp--;

             current =iv_rulelogical_term; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_term824); 

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
    // $ANTLR end entryRulelogical_term


    // $ANTLR start rulelogical_term
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:520:1: rulelogical_term returns [EObject current=null] : (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) ;
    public final EObject rulelogical_term() throws RecognitionException {
        EObject current = null;

        EObject this_logical_factor_0 = null;

        EObject lv_Logical_factor_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:525:6: ( (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:526:1: (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:526:1: (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:527:5: this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term871);
            this_logical_factor_0=rulelogical_factor();
            _fsp--;

             
                    current = this_logical_factor_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:535:1: ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==20) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:535:3: 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    {
            	    match(input,20,FOLLOW_20_in_rulelogical_term881); 

            	            createLeafNode(grammarAccess.getLogical_termAccess().getAndKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:539:1: ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:540:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:540:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:541:3: lv_Logical_factor_2_0= rulelogical_factor
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorLogical_factorParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term902);
            	    lv_Logical_factor_2_0=rulelogical_factor();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getLogical_termRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"Logical_factor",
            	    	        		lv_Logical_factor_2_0, 
            	    	        		"logical_factor", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulelogical_term


    // $ANTLR start entryRulelogical_factor
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:571:1: entryRulelogical_factor returns [EObject current=null] : iv_rulelogical_factor= rulelogical_factor EOF ;
    public final EObject entryRulelogical_factor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_factor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:572:2: (iv_rulelogical_factor= rulelogical_factor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:573:2: iv_rulelogical_factor= rulelogical_factor EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_factorRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_factor_in_entryRulelogical_factor940);
            iv_rulelogical_factor=rulelogical_factor();
            _fsp--;

             current =iv_rulelogical_factor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_factor950); 

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
    // $ANTLR end entryRulelogical_factor


    // $ANTLR start rulelogical_factor
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:580:1: rulelogical_factor returns [EObject current=null] : ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) ;
    public final EObject rulelogical_factor() throws RecognitionException {
        EObject current = null;

        EObject lv_Relation_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:585:6: ( ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:586:1: ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:586:1: ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:586:2: ( 'not' )? ( (lv_Relation_1_0= rulerelation ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:586:2: ( 'not' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==21) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:586:4: 'not'
                    {
                    match(input,21,FOLLOW_21_in_rulelogical_factor986); 

                            createLeafNode(grammarAccess.getLogical_factorAccess().getNotKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:590:3: ( (lv_Relation_1_0= rulerelation ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:591:1: (lv_Relation_1_0= rulerelation )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:591:1: (lv_Relation_1_0= rulerelation )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:592:3: lv_Relation_1_0= rulerelation
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getLogical_factorAccess().getRelationRelationParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulerelation_in_rulelogical_factor1009);
            lv_Relation_1_0=rulerelation();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getLogical_factorRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"Relation",
            	        		lv_Relation_1_0, 
            	        		"relation", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulelogical_factor


    // $ANTLR start entryRulerelation
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:622:1: entryRulerelation returns [EObject current=null] : iv_rulerelation= rulerelation EOF ;
    public final EObject entryRulerelation() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:623:2: (iv_rulerelation= rulerelation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:624:2: iv_rulerelation= rulerelation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRelationRule(), currentNode); 
            pushFollow(FOLLOW_rulerelation_in_entryRulerelation1045);
            iv_rulerelation=rulerelation();
            _fsp--;

             current =iv_rulerelation; 
            match(input,EOF,FOLLOW_EOF_in_entryRulerelation1055); 

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
    // $ANTLR end entryRulerelation


    // $ANTLR start rulerelation
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:631:1: rulerelation returns [EObject current=null] : (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) ;
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


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:636:6: ( (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:637:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:637:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:638:5: this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1102);
            this_arithmetic_expression_0=rulearithmetic_expression();
            _fsp--;

             
                    current = this_arithmetic_expression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:646:1: ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=47 && LA10_0<=52)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:646:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:646:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:647:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:647:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:648:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:648:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
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
                            new NoViableAltException("648:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )", 9, 0, input);

                        throw nvae;
                    }

                    switch (alt9) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:649:3: lv_op_1_1= rulerel_op_Less_then
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_thenParserRuleCall_1_0_0_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_then_in_rulerelation1125);
                            lv_op_1_1=rulerel_op_Less_then();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"op",
                            	        		lv_op_1_1, 
                            	        		"rel_op_Less_then", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:670:8: lv_op_1_2= rulerel_op_Less_equal
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_equalParserRuleCall_1_0_0_1(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_equal_in_rulerelation1144);
                            lv_op_1_2=rulerel_op_Less_equal();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"op",
                            	        		lv_op_1_2, 
                            	        		"rel_op_Less_equal", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:691:8: lv_op_1_3= rulerel_op_greater_then
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_thenParserRuleCall_1_0_0_2(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_then_in_rulerelation1163);
                            lv_op_1_3=rulerel_op_greater_then();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"op",
                            	        		lv_op_1_3, 
                            	        		"rel_op_greater_then", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 4 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:712:8: lv_op_1_4= rulerel_op_greater_equal
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_equalParserRuleCall_1_0_0_3(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_equal_in_rulerelation1182);
                            lv_op_1_4=rulerel_op_greater_equal();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"op",
                            	        		lv_op_1_4, 
                            	        		"rel_op_greater_equal", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 5 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:733:8: lv_op_1_5= rulerel_op_assignment
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_assignmentParserRuleCall_1_0_0_4(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_assignment_in_rulerelation1201);
                            lv_op_1_5=rulerel_op_assignment();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"op",
                            	        		lv_op_1_5, 
                            	        		"rel_op_assignment", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 6 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:754:8: lv_op_1_6= rulerel_op_Oper
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_OperParserRuleCall_1_0_0_5(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Oper_in_rulerelation1220);
                            lv_op_1_6=rulerel_op_Oper();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"op",
                            	        		lv_op_1_6, 
                            	        		"rel_op_Oper", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;

                    }


                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:778:2: ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:779:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:779:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:780:3: lv_Arithmetic_expression_2_0= rulearithmetic_expression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1244);
                    lv_Arithmetic_expression_2_0=rulearithmetic_expression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Arithmetic_expression",
                    	        		lv_Arithmetic_expression_2_0, 
                    	        		"arithmetic_expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulerelation


    // $ANTLR start entryRulearithmetic_expression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:810:1: entryRulearithmetic_expression returns [EObject current=null] : iv_rulearithmetic_expression= rulearithmetic_expression EOF ;
    public final EObject entryRulearithmetic_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearithmetic_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:811:2: (iv_rulearithmetic_expression= rulearithmetic_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:812:2: iv_rulearithmetic_expression= rulearithmetic_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArithmetic_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1282);
            iv_rulearithmetic_expression=rulearithmetic_expression();
            _fsp--;

             current =iv_rulearithmetic_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearithmetic_expression1292); 

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
    // $ANTLR end entryRulearithmetic_expression


    // $ANTLR start rulearithmetic_expression
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:819:1: rulearithmetic_expression returns [EObject current=null] : ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) ;
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


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:824:6: ( ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:825:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:825:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:825:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:825:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=43 && LA12_0<=46)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:826:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:826:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:827:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:827:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
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
                            new NoViableAltException("827:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )", 11, 0, input);

                        throw nvae;
                    }

                    switch (alt11) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:828:3: lv_opr_0_1= ruleadd_op_plus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_plusParserRuleCall_0_0_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1340);
                            lv_opr_0_1=ruleadd_op_plus();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"opr",
                            	        		lv_opr_0_1, 
                            	        		"add_op_plus", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:849:8: lv_opr_0_2= ruleadd_op_minus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_minusParserRuleCall_0_0_1(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1359);
                            lv_opr_0_2=ruleadd_op_minus();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"opr",
                            	        		lv_opr_0_2, 
                            	        		"add_op_minus", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:870:8: lv_opr_0_3= ruleadd_op_dotplus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotplusParserRuleCall_0_0_2(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1378);
                            lv_opr_0_3=ruleadd_op_dotplus();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"opr",
                            	        		lv_opr_0_3, 
                            	        		"add_op_dotplus", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;
                        case 4 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:891:8: lv_opr_0_4= ruleadd_op_dotminus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotminusParserRuleCall_0_0_3(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1397);
                            lv_opr_0_4=ruleadd_op_dotminus();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"opr",
                            	        		lv_opr_0_4, 
                            	        		"add_op_dotminus", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:915:3: ( (lv_Term_1_0= ruleterm ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:916:1: (lv_Term_1_0= ruleterm )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:916:1: (lv_Term_1_0= ruleterm )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:917:3: lv_Term_1_0= ruleterm
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTermTermParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1422);
            lv_Term_1_0=ruleterm();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"Term",
            	        		lv_Term_1_0, 
            	        		"term", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:939:2: ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=43 && LA14_0<=46)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:939:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:939:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:940:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:940:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:941:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:941:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
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
            	            new NoViableAltException("941:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )", 13, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt13) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:942:3: lv_Oper1_2_1= ruleadd_op_plus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_plusParserRuleCall_2_0_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1446);
            	            lv_Oper1_2_1=ruleadd_op_plus();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"Oper1",
            	            	        		lv_Oper1_2_1, 
            	            	        		"add_op_plus", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:963:8: lv_Oper1_2_2= ruleadd_op_minus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_minusParserRuleCall_2_0_0_1(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1465);
            	            lv_Oper1_2_2=ruleadd_op_minus();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"Oper1",
            	            	        		lv_Oper1_2_2, 
            	            	        		"add_op_minus", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;
            	        case 3 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:984:8: lv_Oper1_2_3= ruleadd_op_dotplus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotplusParserRuleCall_2_0_0_2(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1484);
            	            lv_Oper1_2_3=ruleadd_op_dotplus();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"Oper1",
            	            	        		lv_Oper1_2_3, 
            	            	        		"add_op_dotplus", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;
            	        case 4 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1005:8: lv_Oper1_2_4= ruleadd_op_dotminus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotminusParserRuleCall_2_0_0_3(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1503);
            	            lv_Oper1_2_4=ruleadd_op_dotminus();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"Oper1",
            	            	        		lv_Oper1_2_4, 
            	            	        		"add_op_dotminus", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1029:2: ( (lv_Term1_3_0= ruleterm ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1030:1: (lv_Term1_3_0= ruleterm )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1030:1: (lv_Term1_3_0= ruleterm )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1031:3: lv_Term1_3_0= ruleterm
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTerm1TermParserRuleCall_2_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1527);
            	    lv_Term1_3_0=ruleterm();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getArithmetic_expressionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"Term1",
            	    	        		lv_Term1_3_0, 
            	    	        		"term", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulearithmetic_expression


    // $ANTLR start entryRuleterm
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1061:1: entryRuleterm returns [EObject current=null] : iv_ruleterm= ruleterm EOF ;
    public final EObject entryRuleterm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleterm = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1062:2: (iv_ruleterm= ruleterm EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1063:2: iv_ruleterm= ruleterm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTermRule(), currentNode); 
            pushFollow(FOLLOW_ruleterm_in_entryRuleterm1565);
            iv_ruleterm=ruleterm();
            _fsp--;

             current =iv_ruleterm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleterm1575); 

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
    // $ANTLR end entryRuleterm


    // $ANTLR start ruleterm
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1070:1: ruleterm returns [EObject current=null] : (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) ;
    public final EObject ruleterm() throws RecognitionException {
        EObject current = null;

        EObject this_factor_0 = null;

        AntlrDatatypeRuleToken lv_op_1_1 = null;

        AntlrDatatypeRuleToken lv_op_1_2 = null;

        AntlrDatatypeRuleToken lv_op_1_3 = null;

        AntlrDatatypeRuleToken lv_op_1_4 = null;

        EObject lv_Factor_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1075:6: ( (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1076:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1076:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1077:5: this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getTermAccess().getFactorParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulefactor_in_ruleterm1622);
            this_factor_0=rulefactor();
            _fsp--;

             
                    current = this_factor_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1085:1: ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=39 && LA16_0<=42)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1085:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1085:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1086:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1086:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1087:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1087:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
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
            	            new NoViableAltException("1087:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )", 15, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt15) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1088:3: lv_op_1_1= rulemul_op_mul
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_mulParserRuleCall_1_0_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_mul_in_ruleterm1645);
            	            lv_op_1_1=rulemul_op_mul();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getTermRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"op",
            	            	        		lv_op_1_1, 
            	            	        		"mul_op_mul", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1109:8: lv_op_1_2= rulemul_op_div
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_divParserRuleCall_1_0_0_1(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_div_in_ruleterm1664);
            	            lv_op_1_2=rulemul_op_div();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getTermRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"op",
            	            	        		lv_op_1_2, 
            	            	        		"mul_op_div", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;
            	        case 3 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1130:8: lv_op_1_3= rulemul_op_dotmul
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotmulParserRuleCall_1_0_0_2(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotmul_in_ruleterm1683);
            	            lv_op_1_3=rulemul_op_dotmul();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getTermRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"op",
            	            	        		lv_op_1_3, 
            	            	        		"mul_op_dotmul", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;
            	        case 4 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1151:8: lv_op_1_4= rulemul_op_dotdiv
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotdivParserRuleCall_1_0_0_3(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotdiv_in_ruleterm1702);
            	            lv_op_1_4=rulemul_op_dotdiv();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getTermRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"op",
            	            	        		lv_op_1_4, 
            	            	        		"mul_op_dotdiv", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1175:2: ( (lv_Factor_2_0= rulefactor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1176:1: (lv_Factor_2_0= rulefactor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1176:1: (lv_Factor_2_0= rulefactor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1177:3: lv_Factor_2_0= rulefactor
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getFactorFactorParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulefactor_in_ruleterm1726);
            	    lv_Factor_2_0=rulefactor();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getTermRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"Factor",
            	    	        		lv_Factor_2_0, 
            	    	        		"factor", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleterm


    // $ANTLR start entryRulefactor
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1207:1: entryRulefactor returns [EObject current=null] : iv_rulefactor= rulefactor EOF ;
    public final EObject entryRulefactor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefactor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1208:2: (iv_rulefactor= rulefactor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1209:2: iv_rulefactor= rulefactor EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFactorRule(), currentNode); 
            pushFollow(FOLLOW_rulefactor_in_entryRulefactor1764);
            iv_rulefactor=rulefactor();
            _fsp--;

             current =iv_rulefactor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefactor1774); 

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
    // $ANTLR end entryRulefactor


    // $ANTLR start rulefactor
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1216:1: rulefactor returns [EObject current=null] : (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) ;
    public final EObject rulefactor() throws RecognitionException {
        EObject current = null;

        EObject this_primary_0 = null;

        EObject lv_Primary_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1221:6: ( (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1222:1: (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1222:1: (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1223:5: this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFactorAccess().getPrimaryParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleprimary_in_rulefactor1821);
            this_primary_0=ruleprimary();
            _fsp--;

             
                    current = this_primary_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1231:1: ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=22 && LA18_0<=23)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1231:2: ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1231:2: ( '^' | '.^' )
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
                            new NoViableAltException("1231:2: ( '^' | '.^' )", 17, 0, input);

                        throw nvae;
                    }
                    switch (alt17) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1231:4: '^'
                            {
                            match(input,22,FOLLOW_22_in_rulefactor1832); 

                                    createLeafNode(grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1236:7: '.^'
                            {
                            match(input,23,FOLLOW_23_in_rulefactor1848); 

                                    createLeafNode(grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1(), null); 
                                

                            }
                            break;

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1240:2: ( (lv_Primary_3_0= ruleprimary ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1241:1: (lv_Primary_3_0= ruleprimary )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1241:1: (lv_Primary_3_0= ruleprimary )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1242:3: lv_Primary_3_0= ruleprimary
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFactorAccess().getPrimaryPrimaryParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleprimary_in_rulefactor1870);
                    lv_Primary_3_0=ruleprimary();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFactorRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Primary",
                    	        		lv_Primary_3_0, 
                    	        		"primary", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefactor


    // $ANTLR start entryRuleprimary
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1272:1: entryRuleprimary returns [EObject current=null] : iv_ruleprimary= ruleprimary EOF ;
    public final EObject entryRuleprimary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleprimary = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1273:2: (iv_ruleprimary= ruleprimary EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1274:2: iv_ruleprimary= ruleprimary EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrimaryRule(), currentNode); 
            pushFollow(FOLLOW_ruleprimary_in_entryRuleprimary1908);
            iv_ruleprimary=ruleprimary();
            _fsp--;

             current =iv_ruleprimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleprimary1918); 

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
    // $ANTLR end entryRuleprimary


    // $ANTLR start ruleprimary
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1281:1: ruleprimary returns [EObject current=null] : ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) ) ;
    public final EObject ruleprimary() throws RecognitionException {
        EObject current = null;

        Token lv_num_0_0=null;
        Token lv_int_1_0=null;
        Token lv_str_2_0=null;
        Token lv_Bool_3_0=null;
        Token lv_End_19_0=null;
        EObject this_name_Function_4 = null;

        EObject this_initial_ref_5 = null;

        EObject this_ExprDer_6 = null;

        EObject lv_Component_reference_7_0 = null;

        EObject lv_output_expr_list_9_0 = null;

        EObject lv_Expre_list_12_0 = null;

        EObject lv_Expression_list_14_0 = null;

        EObject lv_f_arguments_17_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1286:6: ( ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1287:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1287:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) )
            int alt20=12;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1287:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1287:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1288:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1288:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1289:3: lv_num_0_0= RULE_UNSIGNED_NUMBER
                    {
                    lv_num_0_0=(Token)input.LT(1);
                    match(input,RULE_UNSIGNED_NUMBER,FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary1960); 

                    			createLeafNode(grammarAccess.getPrimaryAccess().getNumUNSIGNED_NUMBERTerminalRuleCall_0_0(), "num"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"num",
                    	        		lv_num_0_0, 
                    	        		"UNSIGNED_NUMBER", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1312:6: ( (lv_int_1_0= RULE_INT ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1312:6: ( (lv_int_1_0= RULE_INT ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1313:1: (lv_int_1_0= RULE_INT )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1313:1: (lv_int_1_0= RULE_INT )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1314:3: lv_int_1_0= RULE_INT
                    {
                    lv_int_1_0=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleprimary1988); 

                    			createLeafNode(grammarAccess.getPrimaryAccess().getIntINTTerminalRuleCall_1_0(), "int"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"int",
                    	        		lv_int_1_0, 
                    	        		"INT", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1337:6: ( (lv_str_2_0= RULE_STRING ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1337:6: ( (lv_str_2_0= RULE_STRING ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1338:1: (lv_str_2_0= RULE_STRING )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1338:1: (lv_str_2_0= RULE_STRING )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1339:3: lv_str_2_0= RULE_STRING
                    {
                    lv_str_2_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleprimary2016); 

                    			createLeafNode(grammarAccess.getPrimaryAccess().getStrSTRINGTerminalRuleCall_2_0(), "str"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"str",
                    	        		lv_str_2_0, 
                    	        		"STRING", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1362:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1362:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1363:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1363:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1364:3: lv_Bool_3_0= RULE_BOOL_VAL
                    {
                    lv_Bool_3_0=(Token)input.LT(1);
                    match(input,RULE_BOOL_VAL,FOLLOW_RULE_BOOL_VAL_in_ruleprimary2044); 

                    			createLeafNode(grammarAccess.getPrimaryAccess().getBoolBOOL_VALTerminalRuleCall_3_0(), "Bool"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Bool",
                    	        		lv_Bool_3_0, 
                    	        		"BOOL_VAL", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1388:5: this_name_Function_4= rulename_Function
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getName_FunctionParserRuleCall_4(), currentNode); 
                        
                    pushFollow(FOLLOW_rulename_Function_in_ruleprimary2077);
                    this_name_Function_4=rulename_Function();
                    _fsp--;

                     
                            current = this_name_Function_4; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 6 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1398:5: this_initial_ref_5= ruleinitial_ref
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getInitial_refParserRuleCall_5(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleinitial_ref_in_ruleprimary2104);
                    this_initial_ref_5=ruleinitial_ref();
                    _fsp--;

                     
                            current = this_initial_ref_5; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 7 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1408:5: this_ExprDer_6= ruleExprDer
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExprDerParserRuleCall_6(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleExprDer_in_ruleprimary2131);
                    this_ExprDer_6=ruleExprDer();
                    _fsp--;

                     
                            current = this_ExprDer_6; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 8 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1417:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1417:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1418:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1418:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1419:3: lv_Component_reference_7_0= rulecomponent_reference
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getComponent_referenceComponent_referenceParserRuleCall_7_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulecomponent_reference_in_ruleprimary2157);
                    lv_Component_reference_7_0=rulecomponent_reference();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Component_reference",
                    	        		lv_Component_reference_7_0, 
                    	        		"component_reference", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 9 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1442:6: ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1442:6: ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1442:8: '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')'
                    {
                    match(input,24,FOLLOW_24_in_ruleprimary2174); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_8_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1446:1: ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1447:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1447:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1448:3: lv_output_expr_list_9_0= ruleoutput_expression_list
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getOutput_expr_listOutput_expression_listParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleoutput_expression_list_in_ruleprimary2195);
                    lv_output_expr_list_9_0=ruleoutput_expression_list();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"output_expr_list",
                    	        		lv_output_expr_list_9_0, 
                    	        		"output_expression_list", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    match(input,25,FOLLOW_25_in_ruleprimary2205); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_8_2(), null); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1475:6: ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1475:6: ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1475:8: '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']'
                    {
                    match(input,26,FOLLOW_26_in_ruleprimary2223); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getLeftSquareBracketKeyword_9_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1479:1: ( (lv_Expre_list_12_0= ruleexpression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1480:1: (lv_Expre_list_12_0= ruleexpression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1480:1: (lv_Expre_list_12_0= ruleexpression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1481:3: lv_Expre_list_12_0= ruleexpression_list
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExpre_listExpression_listParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2244);
                    lv_Expre_list_12_0=ruleexpression_list();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Expre_list",
                    	        		lv_Expre_list_12_0, 
                    	        		"expression_list", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1503:2: ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==27) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1503:4: ';' ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    {
                    	    match(input,27,FOLLOW_27_in_ruleprimary2255); 

                    	            createLeafNode(grammarAccess.getPrimaryAccess().getSemicolonKeyword_9_2_0(), null); 
                    	        
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1507:1: ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1508:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    {
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1508:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1509:3: lv_Expression_list_14_0= ruleexpression_list
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listExpression_listParserRuleCall_9_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2276);
                    	    lv_Expression_list_14_0=ruleexpression_list();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"Expression_list",
                    	    	        		lv_Expression_list_14_0, 
                    	    	        		"expression_list", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);

                    match(input,28,FOLLOW_28_in_ruleprimary2288); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getRightSquareBracketKeyword_9_3(), null); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1536:6: ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1536:6: ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1536:8: '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}'
                    {
                    match(input,29,FOLLOW_29_in_ruleprimary2306); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getLeftCurlyBracketKeyword_10_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1540:1: ( (lv_f_arguments_17_0= rulefunction_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1541:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1541:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1542:3: lv_f_arguments_17_0= rulefunction_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getF_argumentsFunction_argumentsParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_ruleprimary2327);
                    lv_f_arguments_17_0=rulefunction_arguments();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"f_arguments",
                    	        		lv_f_arguments_17_0, 
                    	        		"function_arguments", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    match(input,30,FOLLOW_30_in_ruleprimary2337); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getRightCurlyBracketKeyword_10_2(), null); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1569:6: ( (lv_End_19_0= 'end' ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1569:6: ( (lv_End_19_0= 'end' ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1570:1: (lv_End_19_0= 'end' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1570:1: (lv_End_19_0= 'end' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1571:3: lv_End_19_0= 'end'
                    {
                    lv_End_19_0=(Token)input.LT(1);
                    match(input,31,FOLLOW_31_in_ruleprimary2362); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getEndEndKeyword_11_0(), "End"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPrimaryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "End", lv_End_19_0, "end", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleprimary


    // $ANTLR start entryRulename_Function
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1598:1: entryRulename_Function returns [EObject current=null] : iv_rulename_Function= rulename_Function EOF ;
    public final EObject entryRulename_Function() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename_Function = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1599:2: (iv_rulename_Function= rulename_Function EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1600:2: iv_rulename_Function= rulename_Function EOF
            {
             currentNode = createCompositeNode(grammarAccess.getName_FunctionRule(), currentNode); 
            pushFollow(FOLLOW_rulename_Function_in_entryRulename_Function2411);
            iv_rulename_Function=rulename_Function();
            _fsp--;

             current =iv_rulename_Function; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_Function2421); 

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
    // $ANTLR end entryRulename_Function


    // $ANTLR start rulename_Function
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1607:1: rulename_Function returns [EObject current=null] : (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) ;
    public final EObject rulename_Function() throws RecognitionException {
        EObject current = null;

        EObject this_name_0 = null;

        EObject lv_Function_call_args_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1612:6: ( (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1613:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1613:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1614:5: this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            {
             
                    currentNode=createCompositeNode(grammarAccess.getName_FunctionAccess().getNameParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulename_in_rulename_Function2468);
            this_name_0=rulename();
            _fsp--;

             
                    current = this_name_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1622:1: ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1623:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1623:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1624:3: lv_Function_call_args_1_0= rulefunction_call_args
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getName_FunctionAccess().getFunction_call_argsFunction_call_argsParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_rulename_Function2488);
            lv_Function_call_args_1_0=rulefunction_call_args();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getName_FunctionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"Function_call_args",
            	        		lv_Function_call_args_1_0, 
            	        		"function_call_args", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulename_Function


    // $ANTLR start entryRuleinitial_ref
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1654:1: entryRuleinitial_ref returns [EObject current=null] : iv_ruleinitial_ref= ruleinitial_ref EOF ;
    public final EObject entryRuleinitial_ref() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitial_ref = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1655:2: (iv_ruleinitial_ref= ruleinitial_ref EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1656:2: iv_ruleinitial_ref= ruleinitial_ref EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInitial_refRule(), currentNode); 
            pushFollow(FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2524);
            iv_ruleinitial_ref=ruleinitial_ref();
            _fsp--;

             current =iv_ruleinitial_ref; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleinitial_ref2534); 

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
    // $ANTLR end entryRuleinitial_ref


    // $ANTLR start ruleinitial_ref
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1663:1: ruleinitial_ref returns [EObject current=null] : ( 'initial' this_function_call_args_1= rulefunction_call_args ) ;
    public final EObject ruleinitial_ref() throws RecognitionException {
        EObject current = null;

        EObject this_function_call_args_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1668:6: ( ( 'initial' this_function_call_args_1= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1669:1: ( 'initial' this_function_call_args_1= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1669:1: ( 'initial' this_function_call_args_1= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1669:3: 'initial' this_function_call_args_1= rulefunction_call_args
            {
            match(input,32,FOLLOW_32_in_ruleinitial_ref2569); 

                    createLeafNode(grammarAccess.getInitial_refAccess().getInitialKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getInitial_refAccess().getFunction_call_argsParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleinitial_ref2591);
            this_function_call_args_1=rulefunction_call_args();
            _fsp--;

             
                    current = this_function_call_args_1; 
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleinitial_ref


    // $ANTLR start entryRuleExprDer
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1690:1: entryRuleExprDer returns [EObject current=null] : iv_ruleExprDer= ruleExprDer EOF ;
    public final EObject entryRuleExprDer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExprDer = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1691:2: (iv_ruleExprDer= ruleExprDer EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1692:2: iv_ruleExprDer= ruleExprDer EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExprDerRule(), currentNode); 
            pushFollow(FOLLOW_ruleExprDer_in_entryRuleExprDer2626);
            iv_ruleExprDer=ruleExprDer();
            _fsp--;

             current =iv_ruleExprDer; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExprDer2636); 

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
    // $ANTLR end entryRuleExprDer


    // $ANTLR start ruleExprDer
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1699:1: ruleExprDer returns [EObject current=null] : ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) ;
    public final EObject ruleExprDer() throws RecognitionException {
        EObject current = null;

        EObject lv_functionArgs_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1704:6: ( ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1705:1: ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1705:1: ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1705:3: 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            {
            match(input,33,FOLLOW_33_in_ruleExprDer2671); 

                    createLeafNode(grammarAccess.getExprDerAccess().getDerKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1709:1: ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1710:1: (lv_functionArgs_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1710:1: (lv_functionArgs_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1711:3: lv_functionArgs_1_0= rulefunction_call_args
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getExprDerAccess().getFunctionArgsFunction_call_argsParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleExprDer2692);
            lv_functionArgs_1_0=rulefunction_call_args();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getExprDerRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"functionArgs",
            	        		lv_functionArgs_1_0, 
            	        		"function_call_args", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleExprDer


    // $ANTLR start entryRulefunction_call_args
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1741:1: entryRulefunction_call_args returns [EObject current=null] : iv_rulefunction_call_args= rulefunction_call_args EOF ;
    public final EObject entryRulefunction_call_args() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_call_args = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1742:2: (iv_rulefunction_call_args= rulefunction_call_args EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1743:2: iv_rulefunction_call_args= rulefunction_call_args EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFunction_call_argsRule(), currentNode); 
            pushFollow(FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args2728);
            iv_rulefunction_call_args=rulefunction_call_args();
            _fsp--;

             current =iv_rulefunction_call_args; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_call_args2738); 

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
    // $ANTLR end entryRulefunction_call_args


    // $ANTLR start rulefunction_call_args
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1750:1: rulefunction_call_args returns [EObject current=null] : ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' ) ;
    public final EObject rulefunction_call_args() throws RecognitionException {
        EObject current = null;

        EObject lv_f_arg_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1755:6: ( ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1756:1: ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1756:1: ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1756:2: () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')'
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1756:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1757:5: 
            {
             
                    temp=factory.create(grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,24,FOLLOW_24_in_rulefunction_call_args2782); 

                    createLeafNode(grammarAccess.getFunction_call_argsAccess().getLeftParenthesisKeyword_1(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1771:1: ( (lv_f_arg_2_0= rulefunction_arguments ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=RULE_UNSIGNED_NUMBER && LA21_0<=RULE_IDENT)||LA21_0==14||LA21_0==21||LA21_0==24||LA21_0==26||LA21_0==29||(LA21_0>=31 && LA21_0<=33)||LA21_0==35||(LA21_0>=43 && LA21_0<=46)) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1772:1: (lv_f_arg_2_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1772:1: (lv_f_arg_2_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1773:3: lv_f_arg_2_0= rulefunction_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFunction_call_argsAccess().getF_argFunction_argumentsParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_rulefunction_call_args2803);
                    lv_f_arg_2_0=rulefunction_arguments();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFunction_call_argsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"f_arg",
                    	        		lv_f_arg_2_0, 
                    	        		"function_arguments", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            match(input,25,FOLLOW_25_in_rulefunction_call_args2814); 

                    createLeafNode(grammarAccess.getFunction_call_argsAccess().getRightParenthesisKeyword_3(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefunction_call_args


    // $ANTLR start entryRuleexpression_list
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1807:1: entryRuleexpression_list returns [EObject current=null] : iv_ruleexpression_list= ruleexpression_list EOF ;
    public final EObject entryRuleexpression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1808:2: (iv_ruleexpression_list= ruleexpression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1809:2: iv_ruleexpression_list= ruleexpression_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpression_listRule(), currentNode); 
            pushFollow(FOLLOW_ruleexpression_list_in_entryRuleexpression_list2850);
            iv_ruleexpression_list=ruleexpression_list();
            _fsp--;

             current =iv_ruleexpression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression_list2860); 

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
    // $ANTLR end entryRuleexpression_list


    // $ANTLR start ruleexpression_list
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1816:1: ruleexpression_list returns [EObject current=null] : ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) ;
    public final EObject ruleexpression_list() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_0_0 = null;

        EObject lv_Expre_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1821:6: ( ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1822:1: ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1822:1: ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1822:2: ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1822:2: ( (lv_expr_0_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1823:1: (lv_expr_0_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1823:1: (lv_expr_0_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1824:3: lv_expr_0_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getExpression_listAccess().getExprExpressionParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list2906);
            lv_expr_0_0=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getExpression_listRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"expr",
            	        		lv_expr_0_0, 
            	        		"expression", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1846:2: ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==34) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1846:4: ',' ( (lv_Expre_2_0= ruleexpression ) )
            	    {
            	    match(input,34,FOLLOW_34_in_ruleexpression_list2917); 

            	            createLeafNode(grammarAccess.getExpression_listAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1850:1: ( (lv_Expre_2_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1851:1: (lv_Expre_2_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1851:1: (lv_Expre_2_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1852:3: lv_Expre_2_0= ruleexpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getExpression_listAccess().getExpreExpressionParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list2938);
            	    lv_Expre_2_0=ruleexpression();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getExpression_listRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"Expre",
            	    	        		lv_Expre_2_0, 
            	    	        		"expression", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleexpression_list


    // $ANTLR start entryRulename
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1882:1: entryRulename returns [EObject current=null] : iv_rulename= rulename EOF ;
    public final EObject entryRulename() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1883:2: (iv_rulename= rulename EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1884:2: iv_rulename= rulename EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNameRule(), currentNode); 
            pushFollow(FOLLOW_rulename_in_entryRulename2976);
            iv_rulename=rulename();
            _fsp--;

             current =iv_rulename; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename2986); 

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
    // $ANTLR end entryRulename


    // $ANTLR start rulename
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1891:1: rulename returns [EObject current=null] : ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) ;
    public final EObject rulename() throws RecognitionException {
        EObject current = null;

        Token lv_name_ID_1_0=null;
        Token lv_nam_ID_3_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1896:6: ( ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1897:1: ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1897:1: ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1897:2: ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1897:2: ( '.' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==35) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1897:4: '.'
                    {
                    match(input,35,FOLLOW_35_in_rulename3022); 

                            createLeafNode(grammarAccess.getNameAccess().getFullStopKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1901:3: ( (lv_name_ID_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1902:1: (lv_name_ID_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1902:1: (lv_name_ID_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1903:3: lv_name_ID_1_0= RULE_IDENT
            {
            lv_name_ID_1_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3041); 

            			createLeafNode(grammarAccess.getNameAccess().getName_IDIDENTTerminalRuleCall_1_0(), "name_ID"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNameRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name_ID",
            	        		lv_name_ID_1_0, 
            	        		"IDENT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1925:2: ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==35) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1925:4: '.' ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    {
            	    match(input,35,FOLLOW_35_in_rulename3057); 

            	            createLeafNode(grammarAccess.getNameAccess().getFullStopKeyword_2_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1929:1: ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1930:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1930:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1931:3: lv_nam_ID_3_0= RULE_IDENT
            	    {
            	    lv_nam_ID_3_0=(Token)input.LT(1);
            	    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3074); 

            	    			createLeafNode(grammarAccess.getNameAccess().getNam_IDIDENTTerminalRuleCall_2_1_0(), "nam_ID"); 
            	    		

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getNameRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"nam_ID",
            	    	        		lv_nam_ID_3_0, 
            	    	        		"IDENT", 
            	    	        		lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulename


    // $ANTLR start entryRulecomponent_reference
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1961:1: entryRulecomponent_reference returns [EObject current=null] : iv_rulecomponent_reference= rulecomponent_reference EOF ;
    public final EObject entryRulecomponent_reference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_reference = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1962:2: (iv_rulecomponent_reference= rulecomponent_reference EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1963:2: iv_rulecomponent_reference= rulecomponent_reference EOF
            {
             currentNode = createCompositeNode(grammarAccess.getComponent_referenceRule(), currentNode); 
            pushFollow(FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3117);
            iv_rulecomponent_reference=rulecomponent_reference();
            _fsp--;

             current =iv_rulecomponent_reference; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_reference3127); 

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
    // $ANTLR end entryRulecomponent_reference


    // $ANTLR start rulecomponent_reference
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1970:1: rulecomponent_reference returns [EObject current=null] : ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) ;
    public final EObject rulecomponent_reference() throws RecognitionException {
        EObject current = null;

        Token lv_ref_1_0=null;
        Token lv_ref1_4_0=null;
        EObject lv_subscripts1_2_0 = null;

        EObject lv_subscripts_5_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1975:6: ( ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1976:1: ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1976:1: ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1976:2: ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1976:2: ( '.' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==35) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1976:4: '.'
                    {
                    match(input,35,FOLLOW_35_in_rulecomponent_reference3163); 

                            createLeafNode(grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1980:3: ( (lv_ref_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1981:1: (lv_ref_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1981:1: (lv_ref_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:1982:3: lv_ref_1_0= RULE_IDENT
            {
            lv_ref_1_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3182); 

            			createLeafNode(grammarAccess.getComponent_referenceAccess().getRefIDENTTerminalRuleCall_1_0(), "ref"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getComponent_referenceRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"ref",
            	        		lv_ref_1_0, 
            	        		"IDENT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2004:2: ( (lv_subscripts1_2_0= rulearray_subscripts ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==26) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2005:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2005:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2006:3: lv_subscripts1_2_0= rulearray_subscripts
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscripts1Array_subscriptsParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3208);
                    lv_subscripts1_2_0=rulearray_subscripts();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getComponent_referenceRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"subscripts1",
                    	        		lv_subscripts1_2_0, 
                    	        		"array_subscripts", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2028:3: ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==35) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2028:5: '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    {
            	    match(input,35,FOLLOW_35_in_rulecomponent_reference3220); 

            	            createLeafNode(grammarAccess.getComponent_referenceAccess().getFullStopKeyword_3_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2032:1: ( (lv_ref1_4_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2033:1: (lv_ref1_4_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2033:1: (lv_ref1_4_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2034:3: lv_ref1_4_0= RULE_IDENT
            	    {
            	    lv_ref1_4_0=(Token)input.LT(1);
            	    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3237); 

            	    			createLeafNode(grammarAccess.getComponent_referenceAccess().getRef1IDENTTerminalRuleCall_3_1_0(), "ref1"); 
            	    		

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getComponent_referenceRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"ref1",
            	    	        		lv_ref1_4_0, 
            	    	        		"IDENT", 
            	    	        		lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }


            	    }

            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2056:2: ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    int alt27=2;
            	    int LA27_0 = input.LA(1);

            	    if ( (LA27_0==26) ) {
            	        alt27=1;
            	    }
            	    switch (alt27) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2057:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2057:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2058:3: lv_subscripts_5_0= rulearray_subscripts
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscriptsArray_subscriptsParserRuleCall_3_2_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3263);
            	            lv_subscripts_5_0=rulearray_subscripts();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getComponent_referenceRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"subscripts",
            	            	        		lv_subscripts_5_0, 
            	            	        		"array_subscripts", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulecomponent_reference


    // $ANTLR start entryRuleoutput_expression_list
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2088:1: entryRuleoutput_expression_list returns [EObject current=null] : iv_ruleoutput_expression_list= ruleoutput_expression_list EOF ;
    public final EObject entryRuleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleoutput_expression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2089:2: (iv_ruleoutput_expression_list= ruleoutput_expression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2090:2: iv_ruleoutput_expression_list= ruleoutput_expression_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getOutput_expression_listRule(), currentNode); 
            pushFollow(FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3302);
            iv_ruleoutput_expression_list=ruleoutput_expression_list();
            _fsp--;

             current =iv_ruleoutput_expression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleoutput_expression_list3312); 

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
    // $ANTLR end entryRuleoutput_expression_list


    // $ANTLR start ruleoutput_expression_list
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2097:1: ruleoutput_expression_list returns [EObject current=null] : ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) ;
    public final EObject ruleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        EObject lv_epr_1_0 = null;

        EObject lv_Expr_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2102:6: ( ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2103:1: ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2103:1: ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2103:2: () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2103:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2104:5: 
            {
             
                    temp=factory.create(grammarAccess.getOutput_expression_listAccess().getOutput_expression_listAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getOutput_expression_listAccess().getOutput_expression_listAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2114:2: ( (lv_epr_1_0= ruleexpression ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_UNSIGNED_NUMBER && LA29_0<=RULE_IDENT)||LA29_0==14||LA29_0==21||LA29_0==24||LA29_0==26||LA29_0==29||(LA29_0>=31 && LA29_0<=33)||LA29_0==35||(LA29_0>=43 && LA29_0<=46)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2115:1: (lv_epr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2115:1: (lv_epr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2116:3: lv_epr_1_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutput_expression_listAccess().getEprExpressionParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3367);
                    lv_epr_1_0=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutput_expression_listRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"epr",
                    	        		lv_epr_1_0, 
                    	        		"expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2138:3: ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==34) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2138:5: ',' ( (lv_Expr_3_0= ruleexpression ) )?
            	    {
            	    match(input,34,FOLLOW_34_in_ruleoutput_expression_list3379); 

            	            createLeafNode(grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2142:1: ( (lv_Expr_3_0= ruleexpression ) )?
            	    int alt30=2;
            	    int LA30_0 = input.LA(1);

            	    if ( ((LA30_0>=RULE_UNSIGNED_NUMBER && LA30_0<=RULE_IDENT)||LA30_0==14||LA30_0==21||LA30_0==24||LA30_0==26||LA30_0==29||(LA30_0>=31 && LA30_0<=33)||LA30_0==35||(LA30_0>=43 && LA30_0<=46)) ) {
            	        alt30=1;
            	    }
            	    switch (alt30) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2143:1: (lv_Expr_3_0= ruleexpression )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2143:1: (lv_Expr_3_0= ruleexpression )
            	            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2144:3: lv_Expr_3_0= ruleexpression
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getOutput_expression_listAccess().getExprExpressionParserRuleCall_2_1_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3400);
            	            lv_Expr_3_0=ruleexpression();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getOutput_expression_listRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"Expr",
            	            	        		lv_Expr_3_0, 
            	            	        		"expression", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleoutput_expression_list


    // $ANTLR start entryRulearray_subscripts
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2174:1: entryRulearray_subscripts returns [EObject current=null] : iv_rulearray_subscripts= rulearray_subscripts EOF ;
    public final EObject entryRulearray_subscripts() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearray_subscripts = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2175:2: (iv_rulearray_subscripts= rulearray_subscripts EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2176:2: iv_rulearray_subscripts= rulearray_subscripts EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArray_subscriptsRule(), currentNode); 
            pushFollow(FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3439);
            iv_rulearray_subscripts=rulearray_subscripts();
            _fsp--;

             current =iv_rulearray_subscripts; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearray_subscripts3449); 

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
    // $ANTLR end entryRulearray_subscripts


    // $ANTLR start rulearray_subscripts
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2183:1: rulearray_subscripts returns [EObject current=null] : ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' ) ;
    public final EObject rulearray_subscripts() throws RecognitionException {
        EObject current = null;

        EObject lv_Sub_1_0 = null;

        EObject lv_Subscript_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2188:6: ( ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2189:1: ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2189:1: ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2189:3: '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']'
            {
            match(input,26,FOLLOW_26_in_rulearray_subscripts3484); 

                    createLeafNode(grammarAccess.getArray_subscriptsAccess().getLeftSquareBracketKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2193:1: ( (lv_Sub_1_0= rulesubscript ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2194:1: (lv_Sub_1_0= rulesubscript )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2194:1: (lv_Sub_1_0= rulesubscript )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2195:3: lv_Sub_1_0= rulesubscript
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubSubscriptParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3505);
            lv_Sub_1_0=rulesubscript();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getArray_subscriptsRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"Sub",
            	        		lv_Sub_1_0, 
            	        		"subscript", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2217:2: ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==34) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2217:4: ',' ( (lv_Subscript_3_0= rulesubscript ) )
            	    {
            	    match(input,34,FOLLOW_34_in_rulearray_subscripts3516); 

            	            createLeafNode(grammarAccess.getArray_subscriptsAccess().getCommaKeyword_2_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2221:1: ( (lv_Subscript_3_0= rulesubscript ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2222:1: (lv_Subscript_3_0= rulesubscript )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2222:1: (lv_Subscript_3_0= rulesubscript )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2223:3: lv_Subscript_3_0= rulesubscript
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubscriptSubscriptParserRuleCall_2_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3537);
            	    lv_Subscript_3_0=rulesubscript();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getArray_subscriptsRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"Subscript",
            	    	        		lv_Subscript_3_0, 
            	    	        		"subscript", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            match(input,28,FOLLOW_28_in_rulearray_subscripts3549); 

                    createLeafNode(grammarAccess.getArray_subscriptsAccess().getRightSquareBracketKeyword_3(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulearray_subscripts


    // $ANTLR start entryRulesubscript
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2257:1: entryRulesubscript returns [EObject current=null] : iv_rulesubscript= rulesubscript EOF ;
    public final EObject entryRulesubscript() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesubscript = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2258:2: (iv_rulesubscript= rulesubscript EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2259:2: iv_rulesubscript= rulesubscript EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSubscriptRule(), currentNode); 
            pushFollow(FOLLOW_rulesubscript_in_entryRulesubscript3585);
            iv_rulesubscript=rulesubscript();
            _fsp--;

             current =iv_rulesubscript; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesubscript3595); 

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
    // $ANTLR end entryRulesubscript


    // $ANTLR start rulesubscript
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2266:1: rulesubscript returns [EObject current=null] : ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulesubscript() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2271:6: ( ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2272:1: ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2272:1: ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
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
                    new NoViableAltException("2272:1: ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2272:2: ( () ':' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2272:2: ( () ':' )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2272:3: () ':'
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2272:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2273:5: 
                    {
                     
                            temp=factory.create(grammarAccess.getSubscriptAccess().getSubscriptAction_0_0().getType().getClassifier());
                            current = temp; 
                            temp = null;
                            CompositeNode newNode = createCompositeNode(grammarAccess.getSubscriptAccess().getSubscriptAction_0_0(), currentNode.getParent());
                        newNode.getChildren().add(currentNode);
                        moveLookaheadInfo(currentNode, newNode);
                        currentNode = newNode; 
                            associateNodeWithAstElement(currentNode, current); 
                        

                    }

                    match(input,15,FOLLOW_15_in_rulesubscript3640); 

                            createLeafNode(grammarAccess.getSubscriptAccess().getColonKeyword_0_1(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2288:6: ( (lv_expr_2_0= ruleexpression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2288:6: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2289:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2289:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2290:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSubscriptAccess().getExprExpressionParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulesubscript3668);
                    lv_expr_2_0=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSubscriptRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expr",
                    	        		lv_expr_2_0, 
                    	        		"expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulesubscript


    // $ANTLR start entryRulefunction_arguments
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2320:1: entryRulefunction_arguments returns [EObject current=null] : iv_rulefunction_arguments= rulefunction_arguments EOF ;
    public final EObject entryRulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2321:2: (iv_rulefunction_arguments= rulefunction_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2322:2: iv_rulefunction_arguments= rulefunction_arguments EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFunction_argumentsRule(), currentNode); 
            pushFollow(FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments3704);
            iv_rulefunction_arguments=rulefunction_arguments();
            _fsp--;

             current =iv_rulefunction_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_arguments3714); 

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
    // $ANTLR end entryRulefunction_arguments


    // $ANTLR start rulefunction_arguments
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2329:1: rulefunction_arguments returns [EObject current=null] : ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) ;
    public final EObject rulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject lv_ArgExp_1_0 = null;

        EObject lv_Fun_Arg_Expr_2_0 = null;

        EObject lv_Fun_Arg_For_3_0 = null;

        EObject lv_name_arg_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2334:6: ( ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2335:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2335:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=RULE_UNSIGNED_NUMBER && LA35_0<=RULE_BOOL_VAL)||LA35_0==14||LA35_0==21||LA35_0==24||LA35_0==26||LA35_0==29||(LA35_0>=31 && LA35_0<=33)||LA35_0==35||(LA35_0>=43 && LA35_0<=46)) ) {
                alt35=1;
            }
            else if ( (LA35_0==RULE_IDENT) ) {
                int LA35_2 = input.LA(2);

                if ( (LA35_2==37) ) {
                    alt35=2;
                }
                else if ( (LA35_2==EOF||LA35_2==15||(LA35_2>=19 && LA35_2<=20)||(LA35_2>=22 && LA35_2<=26)||LA35_2==30||(LA35_2>=34 && LA35_2<=36)||(LA35_2>=39 && LA35_2<=52)) ) {
                    alt35=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2335:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )", 35, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2335:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2335:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2335:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2335:3: () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2335:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2336:5: 
                    {
                     
                            temp=factory.create(grammarAccess.getFunction_argumentsAccess().getFunction_argumentsAction_0_0().getType().getClassifier());
                            current = temp; 
                            temp = null;
                            CompositeNode newNode = createCompositeNode(grammarAccess.getFunction_argumentsAccess().getFunction_argumentsAction_0_0(), currentNode.getParent());
                        newNode.getChildren().add(currentNode);
                        moveLookaheadInfo(currentNode, newNode);
                        currentNode = newNode; 
                            associateNodeWithAstElement(currentNode, current); 
                        

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2346:2: ( (lv_ArgExp_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2347:1: (lv_ArgExp_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2347:1: (lv_ArgExp_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2348:3: lv_ArgExp_1_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getArgExpExpressionParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefunction_arguments3770);
                    lv_ArgExp_1_0=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFunction_argumentsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"ArgExp",
                    	        		lv_ArgExp_1_0, 
                    	        		"expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2370:2: ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
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
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2370:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2370:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2371:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2371:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2372:3: lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments3792);
                            lv_Fun_Arg_Expr_2_0=ruleFun_Arguments_exp();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getFunction_argumentsRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"Fun_Arg_Expr",
                            	        		lv_Fun_Arg_Expr_2_0, 
                            	        		"Fun_Arguments_exp", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2395:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2395:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2396:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2396:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2397:3: lv_Fun_Arg_For_3_0= ruleFun_Arguments_for
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments3819);
                            lv_Fun_Arg_For_3_0=ruleFun_Arguments_for();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getFunction_argumentsRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"Fun_Arg_For",
                            	        		lv_Fun_Arg_For_3_0, 
                            	        		"Fun_Arguments_for", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2420:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2420:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2421:1: (lv_name_arg_4_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2421:1: (lv_name_arg_4_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2422:3: lv_name_arg_4_0= rulenamed_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getName_argNamed_argumentsParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulefunction_arguments3849);
                    lv_name_arg_4_0=rulenamed_arguments();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFunction_argumentsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name_arg",
                    	        		lv_name_arg_4_0, 
                    	        		"named_arguments", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefunction_arguments


    // $ANTLR start entryRuleFun_Arguments_exp
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2452:1: entryRuleFun_Arguments_exp returns [EObject current=null] : iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF ;
    public final EObject entryRuleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_exp = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2453:2: (iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2454:2: iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFun_Arguments_expRule(), currentNode); 
            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp3885);
            iv_ruleFun_Arguments_exp=ruleFun_Arguments_exp();
            _fsp--;

             current =iv_ruleFun_Arguments_exp; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_exp3895); 

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
    // $ANTLR end entryRuleFun_Arguments_exp


    // $ANTLR start ruleFun_Arguments_exp
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2461:1: ruleFun_Arguments_exp returns [EObject current=null] : ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) ;
    public final EObject ruleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        EObject lv_Args_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2466:6: ( ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2467:1: ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2467:1: ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2467:3: ',' ( (lv_Args_1_0= rulefunction_arguments ) )
            {
            match(input,34,FOLLOW_34_in_ruleFun_Arguments_exp3930); 

                    createLeafNode(grammarAccess.getFun_Arguments_expAccess().getCommaKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2471:1: ( (lv_Args_1_0= rulefunction_arguments ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2472:1: (lv_Args_1_0= rulefunction_arguments )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2472:1: (lv_Args_1_0= rulefunction_arguments )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2473:3: lv_Args_1_0= rulefunction_arguments
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFun_Arguments_expAccess().getArgsFunction_argumentsParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp3951);
            lv_Args_1_0=rulefunction_arguments();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFun_Arguments_expRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"Args",
            	        		lv_Args_1_0, 
            	        		"function_arguments", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleFun_Arguments_exp


    // $ANTLR start entryRuleFun_Arguments_for
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2503:1: entryRuleFun_Arguments_for returns [EObject current=null] : iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF ;
    public final EObject entryRuleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_for = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2504:2: (iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2505:2: iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFun_Arguments_forRule(), currentNode); 
            pushFollow(FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for3987);
            iv_ruleFun_Arguments_for=ruleFun_Arguments_for();
            _fsp--;

             current =iv_ruleFun_Arguments_for; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_for3997); 

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
    // $ANTLR end entryRuleFun_Arguments_for


    // $ANTLR start ruleFun_Arguments_for
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2512:1: ruleFun_Arguments_for returns [EObject current=null] : ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) ;
    public final EObject ruleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        EObject lv_For_indices_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2517:6: ( ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2518:1: ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2518:1: ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2518:3: 'for' ( (lv_For_indices_1_0= rulefor_indices ) )
            {
            match(input,36,FOLLOW_36_in_ruleFun_Arguments_for4032); 

                    createLeafNode(grammarAccess.getFun_Arguments_forAccess().getForKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2522:1: ( (lv_For_indices_1_0= rulefor_indices ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2523:1: (lv_For_indices_1_0= rulefor_indices )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2523:1: (lv_For_indices_1_0= rulefor_indices )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2524:3: lv_For_indices_1_0= rulefor_indices
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFun_Arguments_forAccess().getFor_indicesFor_indicesParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4053);
            lv_For_indices_1_0=rulefor_indices();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFun_Arguments_forRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"For_indices",
            	        		lv_For_indices_1_0, 
            	        		"for_indices", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleFun_Arguments_for


    // $ANTLR start entryRulenamed_arguments
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2554:1: entryRulenamed_arguments returns [EObject current=null] : iv_rulenamed_arguments= rulenamed_arguments EOF ;
    public final EObject entryRulenamed_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2555:2: (iv_rulenamed_arguments= rulenamed_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2556:2: iv_rulenamed_arguments= rulenamed_arguments EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamed_argumentsRule(), currentNode); 
            pushFollow(FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4089);
            iv_rulenamed_arguments=rulenamed_arguments();
            _fsp--;

             current =iv_rulenamed_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_arguments4099); 

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
    // $ANTLR end entryRulenamed_arguments


    // $ANTLR start rulenamed_arguments
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2563:1: rulenamed_arguments returns [EObject current=null] : (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) ;
    public final EObject rulenamed_arguments() throws RecognitionException {
        EObject current = null;

        EObject this_named_argument_0 = null;

        EObject lv_Named_arguments_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2568:6: ( (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2569:1: (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2569:1: (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2570:5: this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulenamed_argument_in_rulenamed_arguments4146);
            this_named_argument_0=rulenamed_argument();
            _fsp--;

             
                    current = this_named_argument_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2578:1: ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==34) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2578:3: ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    {
                    match(input,34,FOLLOW_34_in_rulenamed_arguments4156); 

                            createLeafNode(grammarAccess.getNamed_argumentsAccess().getCommaKeyword_1_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2582:1: ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2583:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2583:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2584:3: lv_Named_arguments_2_0= rulenamed_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentsNamed_argumentsParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulenamed_arguments4177);
                    lv_Named_arguments_2_0=rulenamed_arguments();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNamed_argumentsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Named_arguments",
                    	        		lv_Named_arguments_2_0, 
                    	        		"named_arguments", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulenamed_arguments


    // $ANTLR start entryRulenamed_argument
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2614:1: entryRulenamed_argument returns [EObject current=null] : iv_rulenamed_argument= rulenamed_argument EOF ;
    public final EObject entryRulenamed_argument() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_argument = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2615:2: (iv_rulenamed_argument= rulenamed_argument EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2616:2: iv_rulenamed_argument= rulenamed_argument EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamed_argumentRule(), currentNode); 
            pushFollow(FOLLOW_rulenamed_argument_in_entryRulenamed_argument4215);
            iv_rulenamed_argument=rulenamed_argument();
            _fsp--;

             current =iv_rulenamed_argument; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_argument4225); 

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
    // $ANTLR end entryRulenamed_argument


    // $ANTLR start rulenamed_argument
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2623:1: rulenamed_argument returns [EObject current=null] : ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulenamed_argument() throws RecognitionException {
        EObject current = null;

        Token lv_arg_0_0=null;
        EObject lv_expr_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2628:6: ( ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2629:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2629:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2629:2: ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2629:2: ( (lv_arg_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2630:1: (lv_arg_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2630:1: (lv_arg_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2631:3: lv_arg_0_0= RULE_IDENT
            {
            lv_arg_0_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulenamed_argument4267); 

            			createLeafNode(grammarAccess.getNamed_argumentAccess().getArgIDENTTerminalRuleCall_0_0(), "arg"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNamed_argumentRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"arg",
            	        		lv_arg_0_0, 
            	        		"IDENT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            match(input,37,FOLLOW_37_in_rulenamed_argument4282); 

                    createLeafNode(grammarAccess.getNamed_argumentAccess().getEqualsSignKeyword_1(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2657:1: ( (lv_expr_2_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2658:1: (lv_expr_2_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2658:1: (lv_expr_2_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2659:3: lv_expr_2_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNamed_argumentAccess().getExprExpressionParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulenamed_argument4303);
            lv_expr_2_0=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNamed_argumentRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"expr",
            	        		lv_expr_2_0, 
            	        		"expression", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulenamed_argument


    // $ANTLR start entryRulefor_indices
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2689:1: entryRulefor_indices returns [EObject current=null] : iv_rulefor_indices= rulefor_indices EOF ;
    public final EObject entryRulefor_indices() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_indices = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2690:2: (iv_rulefor_indices= rulefor_indices EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2691:2: iv_rulefor_indices= rulefor_indices EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_indicesRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_indices_in_entryRulefor_indices4339);
            iv_rulefor_indices=rulefor_indices();
            _fsp--;

             current =iv_rulefor_indices; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_indices4349); 

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
    // $ANTLR end entryRulefor_indices


    // $ANTLR start rulefor_indices
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2698:1: rulefor_indices returns [EObject current=null] : (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) ;
    public final EObject rulefor_indices() throws RecognitionException {
        EObject current = null;

        EObject this_for_index_0 = null;

        EObject lv_For_index_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2703:6: ( (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2704:1: (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2704:1: (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2705:5: this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4396);
            this_for_index_0=rulefor_index();
            _fsp--;

             
                    current = this_for_index_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2713:1: ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==34) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2713:3: ',' ( (lv_For_index_2_0= rulefor_index ) )
            	    {
            	    match(input,34,FOLLOW_34_in_rulefor_indices4406); 

            	            createLeafNode(grammarAccess.getFor_indicesAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2717:1: ( (lv_For_index_2_0= rulefor_index ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2718:1: (lv_For_index_2_0= rulefor_index )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2718:1: (lv_For_index_2_0= rulefor_index )
            	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2719:3: lv_For_index_2_0= rulefor_index
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexFor_indexParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4427);
            	    lv_For_index_2_0=rulefor_index();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getFor_indicesRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"For_index",
            	    	        		lv_For_index_2_0, 
            	    	        		"for_index", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

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

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefor_indices


    // $ANTLR start entryRulefor_index
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2749:1: entryRulefor_index returns [EObject current=null] : iv_rulefor_index= rulefor_index EOF ;
    public final EObject entryRulefor_index() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_index = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2750:2: (iv_rulefor_index= rulefor_index EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2751:2: iv_rulefor_index= rulefor_index EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_indexRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_index_in_entryRulefor_index4465);
            iv_rulefor_index=rulefor_index();
            _fsp--;

             current =iv_rulefor_index; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_index4475); 

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
    // $ANTLR end entryRulefor_index


    // $ANTLR start rulefor_index
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2758:1: rulefor_index returns [EObject current=null] : ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) ;
    public final EObject rulefor_index() throws RecognitionException {
        EObject current = null;

        Token lv_index_0_0=null;
        EObject lv_expr_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2763:6: ( ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2764:1: ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2764:1: ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2764:2: ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2764:2: ( (lv_index_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2765:1: (lv_index_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2765:1: (lv_index_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2766:3: lv_index_0_0= RULE_IDENT
            {
            lv_index_0_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulefor_index4517); 

            			createLeafNode(grammarAccess.getFor_indexAccess().getIndexIDENTTerminalRuleCall_0_0(), "index"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFor_indexRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"index",
            	        		lv_index_0_0, 
            	        		"IDENT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2788:2: ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==38) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2788:4: 'in' ( (lv_expr_2_0= ruleexpression ) )
                    {
                    match(input,38,FOLLOW_38_in_rulefor_index4533); 

                            createLeafNode(grammarAccess.getFor_indexAccess().getInKeyword_1_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2792:1: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2793:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2793:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2794:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFor_indexAccess().getExprExpressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefor_index4554);
                    lv_expr_2_0=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFor_indexRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expr",
                    	        		lv_expr_2_0, 
                    	        		"expression", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefor_index


    // $ANTLR start entryRulemul_op_mul
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2824:1: entryRulemul_op_mul returns [String current=null] : iv_rulemul_op_mul= rulemul_op_mul EOF ;
    public final String entryRulemul_op_mul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_mul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2825:2: (iv_rulemul_op_mul= rulemul_op_mul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2826:2: iv_rulemul_op_mul= rulemul_op_mul EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_mulRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4593);
            iv_rulemul_op_mul=rulemul_op_mul();
            _fsp--;

             current =iv_rulemul_op_mul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_mul4604); 

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
    // $ANTLR end entryRulemul_op_mul


    // $ANTLR start rulemul_op_mul
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2833:1: rulemul_op_mul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken rulemul_op_mul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2838:6: (kw= '*' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2840:2: kw= '*'
            {
            kw=(Token)input.LT(1);
            match(input,39,FOLLOW_39_in_rulemul_op_mul4641); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getMul_op_mulAccess().getAsteriskKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulemul_op_mul


    // $ANTLR start entryRulemul_op_div
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2853:1: entryRulemul_op_div returns [String current=null] : iv_rulemul_op_div= rulemul_op_div EOF ;
    public final String entryRulemul_op_div() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_div = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2854:2: (iv_rulemul_op_div= rulemul_op_div EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2855:2: iv_rulemul_op_div= rulemul_op_div EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_divRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_div_in_entryRulemul_op_div4681);
            iv_rulemul_op_div=rulemul_op_div();
            _fsp--;

             current =iv_rulemul_op_div.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_div4692); 

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
    // $ANTLR end entryRulemul_op_div


    // $ANTLR start rulemul_op_div
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2862:1: rulemul_op_div returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '/' ;
    public final AntlrDatatypeRuleToken rulemul_op_div() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2867:6: (kw= '/' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2869:2: kw= '/'
            {
            kw=(Token)input.LT(1);
            match(input,40,FOLLOW_40_in_rulemul_op_div4729); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getMul_op_divAccess().getSolidusKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulemul_op_div


    // $ANTLR start entryRulemul_op_dotmul
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2882:1: entryRulemul_op_dotmul returns [String current=null] : iv_rulemul_op_dotmul= rulemul_op_dotmul EOF ;
    public final String entryRulemul_op_dotmul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotmul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2883:2: (iv_rulemul_op_dotmul= rulemul_op_dotmul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2884:2: iv_rulemul_op_dotmul= rulemul_op_dotmul EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_dotmulRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul4769);
            iv_rulemul_op_dotmul=rulemul_op_dotmul();
            _fsp--;

             current =iv_rulemul_op_dotmul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotmul4780); 

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
    // $ANTLR end entryRulemul_op_dotmul


    // $ANTLR start rulemul_op_dotmul
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2891:1: rulemul_op_dotmul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.*' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotmul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2896:6: (kw= '.*' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2898:2: kw= '.*'
            {
            kw=(Token)input.LT(1);
            match(input,41,FOLLOW_41_in_rulemul_op_dotmul4817); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getMul_op_dotmulAccess().getFullStopAsteriskKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulemul_op_dotmul


    // $ANTLR start entryRulemul_op_dotdiv
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2911:1: entryRulemul_op_dotdiv returns [String current=null] : iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF ;
    public final String entryRulemul_op_dotdiv() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotdiv = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2912:2: (iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2913:2: iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_dotdivRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv4857);
            iv_rulemul_op_dotdiv=rulemul_op_dotdiv();
            _fsp--;

             current =iv_rulemul_op_dotdiv.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotdiv4868); 

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
    // $ANTLR end entryRulemul_op_dotdiv


    // $ANTLR start rulemul_op_dotdiv
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2920:1: rulemul_op_dotdiv returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= './' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotdiv() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2925:6: (kw= './' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2927:2: kw= './'
            {
            kw=(Token)input.LT(1);
            match(input,42,FOLLOW_42_in_rulemul_op_dotdiv4905); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getMul_op_dotdivAccess().getFullStopSolidusKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulemul_op_dotdiv


    // $ANTLR start entryRuleadd_op_plus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2940:1: entryRuleadd_op_plus returns [String current=null] : iv_ruleadd_op_plus= ruleadd_op_plus EOF ;
    public final String entryRuleadd_op_plus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_plus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2941:2: (iv_ruleadd_op_plus= ruleadd_op_plus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2942:2: iv_ruleadd_op_plus= ruleadd_op_plus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_plusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus4945);
            iv_ruleadd_op_plus=ruleadd_op_plus();
            _fsp--;

             current =iv_ruleadd_op_plus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_plus4956); 

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
    // $ANTLR end entryRuleadd_op_plus


    // $ANTLR start ruleadd_op_plus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2949:1: ruleadd_op_plus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_plus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2954:6: (kw= '+' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2956:2: kw= '+'
            {
            kw=(Token)input.LT(1);
            match(input,43,FOLLOW_43_in_ruleadd_op_plus4993); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getAdd_op_plusAccess().getPlusSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleadd_op_plus


    // $ANTLR start entryRuleadd_op_minus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2969:1: entryRuleadd_op_minus returns [String current=null] : iv_ruleadd_op_minus= ruleadd_op_minus EOF ;
    public final String entryRuleadd_op_minus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_minus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2970:2: (iv_ruleadd_op_minus= ruleadd_op_minus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2971:2: iv_ruleadd_op_minus= ruleadd_op_minus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_minusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5033);
            iv_ruleadd_op_minus=ruleadd_op_minus();
            _fsp--;

             current =iv_ruleadd_op_minus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_minus5044); 

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
    // $ANTLR end entryRuleadd_op_minus


    // $ANTLR start ruleadd_op_minus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2978:1: ruleadd_op_minus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_minus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2983:6: (kw= '-' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2985:2: kw= '-'
            {
            kw=(Token)input.LT(1);
            match(input,44,FOLLOW_44_in_ruleadd_op_minus5081); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getAdd_op_minusAccess().getHyphenMinusKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleadd_op_minus


    // $ANTLR start entryRuleadd_op_dotplus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2998:1: entryRuleadd_op_dotplus returns [String current=null] : iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF ;
    public final String entryRuleadd_op_dotplus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotplus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:2999:2: (iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3000:2: iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_dotplusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5121);
            iv_ruleadd_op_dotplus=ruleadd_op_dotplus();
            _fsp--;

             current =iv_ruleadd_op_dotplus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotplus5132); 

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
    // $ANTLR end entryRuleadd_op_dotplus


    // $ANTLR start ruleadd_op_dotplus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3007:1: ruleadd_op_dotplus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotplus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3012:6: (kw= '.+' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3014:2: kw= '.+'
            {
            kw=(Token)input.LT(1);
            match(input,45,FOLLOW_45_in_ruleadd_op_dotplus5169); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getAdd_op_dotplusAccess().getFullStopPlusSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleadd_op_dotplus


    // $ANTLR start entryRuleadd_op_dotminus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3027:1: entryRuleadd_op_dotminus returns [String current=null] : iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF ;
    public final String entryRuleadd_op_dotminus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotminus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3028:2: (iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3029:2: iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_dotminusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5209);
            iv_ruleadd_op_dotminus=ruleadd_op_dotminus();
            _fsp--;

             current =iv_ruleadd_op_dotminus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotminus5220); 

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
    // $ANTLR end entryRuleadd_op_dotminus


    // $ANTLR start ruleadd_op_dotminus
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3036:1: ruleadd_op_dotminus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotminus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3041:6: (kw= '.-' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3043:2: kw= '.-'
            {
            kw=(Token)input.LT(1);
            match(input,46,FOLLOW_46_in_ruleadd_op_dotminus5257); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getAdd_op_dotminusAccess().getFullStopHyphenMinusKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleadd_op_dotminus


    // $ANTLR start entryRulerel_op_Less_then
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3056:1: entryRulerel_op_Less_then returns [String current=null] : iv_rulerel_op_Less_then= rulerel_op_Less_then EOF ;
    public final String entryRulerel_op_Less_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3057:2: (iv_rulerel_op_Less_then= rulerel_op_Less_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3058:2: iv_rulerel_op_Less_then= rulerel_op_Less_then EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_Less_thenRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5297);
            iv_rulerel_op_Less_then=rulerel_op_Less_then();
            _fsp--;

             current =iv_rulerel_op_Less_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_then5308); 

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
    // $ANTLR end entryRulerel_op_Less_then


    // $ANTLR start rulerel_op_Less_then
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3065:1: rulerel_op_Less_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3070:6: (kw= '<' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3072:2: kw= '<'
            {
            kw=(Token)input.LT(1);
            match(input,47,FOLLOW_47_in_rulerel_op_Less_then5345); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getRel_op_Less_thenAccess().getLessThanSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulerel_op_Less_then


    // $ANTLR start entryRulerel_op_Less_equal
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3085:1: entryRulerel_op_Less_equal returns [String current=null] : iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF ;
    public final String entryRulerel_op_Less_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3086:2: (iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3087:2: iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_Less_equalRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5385);
            iv_rulerel_op_Less_equal=rulerel_op_Less_equal();
            _fsp--;

             current =iv_rulerel_op_Less_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_equal5396); 

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
    // $ANTLR end entryRulerel_op_Less_equal


    // $ANTLR start rulerel_op_Less_equal
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3094:1: rulerel_op_Less_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<=' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3099:6: (kw= '<=' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3101:2: kw= '<='
            {
            kw=(Token)input.LT(1);
            match(input,48,FOLLOW_48_in_rulerel_op_Less_equal5433); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getRel_op_Less_equalAccess().getLessThanSignEqualsSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulerel_op_Less_equal


    // $ANTLR start entryRulerel_op_greater_then
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3114:1: entryRulerel_op_greater_then returns [String current=null] : iv_rulerel_op_greater_then= rulerel_op_greater_then EOF ;
    public final String entryRulerel_op_greater_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3115:2: (iv_rulerel_op_greater_then= rulerel_op_greater_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3116:2: iv_rulerel_op_greater_then= rulerel_op_greater_then EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_greater_thenRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5473);
            iv_rulerel_op_greater_then=rulerel_op_greater_then();
            _fsp--;

             current =iv_rulerel_op_greater_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_then5484); 

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
    // $ANTLR end entryRulerel_op_greater_then


    // $ANTLR start rulerel_op_greater_then
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3123:1: rulerel_op_greater_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3128:6: (kw= '>' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3130:2: kw= '>'
            {
            kw=(Token)input.LT(1);
            match(input,49,FOLLOW_49_in_rulerel_op_greater_then5521); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getRel_op_greater_thenAccess().getGreaterThanSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulerel_op_greater_then


    // $ANTLR start entryRulerel_op_greater_equal
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3143:1: entryRulerel_op_greater_equal returns [String current=null] : iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF ;
    public final String entryRulerel_op_greater_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3144:2: (iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3145:2: iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_greater_equalRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5561);
            iv_rulerel_op_greater_equal=rulerel_op_greater_equal();
            _fsp--;

             current =iv_rulerel_op_greater_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_equal5572); 

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
    // $ANTLR end entryRulerel_op_greater_equal


    // $ANTLR start rulerel_op_greater_equal
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3152:1: rulerel_op_greater_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>=' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3157:6: (kw= '>=' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3159:2: kw= '>='
            {
            kw=(Token)input.LT(1);
            match(input,50,FOLLOW_50_in_rulerel_op_greater_equal5609); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getRel_op_greater_equalAccess().getGreaterThanSignEqualsSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulerel_op_greater_equal


    // $ANTLR start entryRulerel_op_assignment
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3172:1: entryRulerel_op_assignment returns [String current=null] : iv_rulerel_op_assignment= rulerel_op_assignment EOF ;
    public final String entryRulerel_op_assignment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_assignment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3173:2: (iv_rulerel_op_assignment= rulerel_op_assignment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3174:2: iv_rulerel_op_assignment= rulerel_op_assignment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_assignmentRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment5649);
            iv_rulerel_op_assignment=rulerel_op_assignment();
            _fsp--;

             current =iv_rulerel_op_assignment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_assignment5660); 

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
    // $ANTLR end entryRulerel_op_assignment


    // $ANTLR start rulerel_op_assignment
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3181:1: rulerel_op_assignment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '==' ;
    public final AntlrDatatypeRuleToken rulerel_op_assignment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3186:6: (kw= '==' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3188:2: kw= '=='
            {
            kw=(Token)input.LT(1);
            match(input,51,FOLLOW_51_in_rulerel_op_assignment5697); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getRel_op_assignmentAccess().getEqualsSignEqualsSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulerel_op_assignment


    // $ANTLR start entryRulerel_op_Oper
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3201:1: entryRulerel_op_Oper returns [String current=null] : iv_rulerel_op_Oper= rulerel_op_Oper EOF ;
    public final String entryRulerel_op_Oper() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Oper = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3202:2: (iv_rulerel_op_Oper= rulerel_op_Oper EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3203:2: iv_rulerel_op_Oper= rulerel_op_Oper EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_OperRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper5737);
            iv_rulerel_op_Oper=rulerel_op_Oper();
            _fsp--;

             current =iv_rulerel_op_Oper.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Oper5748); 

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
    // $ANTLR end entryRulerel_op_Oper


    // $ANTLR start rulerel_op_Oper
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3210:1: rulerel_op_Oper returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<>' ;
    public final AntlrDatatypeRuleToken rulerel_op_Oper() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3215:6: (kw= '<>' )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3217:2: kw= '<>'
            {
            kw=(Token)input.LT(1);
            match(input,52,FOLLOW_52_in_rulerel_op_Oper5785); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getRel_op_OperAccess().getLessThanSignGreaterThanSignKeyword(), null); 
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulerel_op_Oper


    // $ANTLR start entryRulestring_comment
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3232:1: entryRulestring_comment returns [String current=null] : iv_rulestring_comment= rulestring_comment EOF ;
    public final String entryRulestring_comment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulestring_comment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3233:2: (iv_rulestring_comment= rulestring_comment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3234:2: iv_rulestring_comment= rulestring_comment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getString_commentRule(), currentNode); 
            pushFollow(FOLLOW_rulestring_comment_in_entryRulestring_comment5827);
            iv_rulestring_comment=rulestring_comment();
            _fsp--;

             current =iv_rulestring_comment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulestring_comment5838); 

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
    // $ANTLR end entryRulestring_comment


    // $ANTLR start rulestring_comment
    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3241:1: rulestring_comment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? ;
    public final AntlrDatatypeRuleToken rulestring_comment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token kw=null;
        Token this_STRING_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3246:6: ( (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? )
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3247:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3247:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==RULE_STRING) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3247:6: this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )*
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment5878); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3254:1: (kw= '+' this_STRING_2= RULE_STRING )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==43) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.conditionalattribute/src-gen/org/openmodelica/modelicaml/editor/xtext/condition/parser/antlr/internal/InternalConditionalattribute.g:3255:2: kw= '+' this_STRING_2= RULE_STRING
                    	    {
                    	    kw=(Token)input.LT(1);
                    	    match(input,43,FOLLOW_43_in_rulestring_comment5897); 

                    	            current.merge(kw);
                    	            createLeafNode(grammarAccess.getString_commentAccess().getPlusSignKeyword_1_0(), null); 
                    	        
                    	    this_STRING_2=(Token)input.LT(1);
                    	    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment5912); 

                    	    		current.merge(this_STRING_2);
                    	        
                    	     
                    	        createLeafNode(grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_1_1(), null); 
                    	        

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

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulestring_comment


    protected DFA20 dfa20 = new DFA20(this);
    static final String DFA20_eotS =
        "\21\uffff";
    static final String DFA20_eofS =
        "\6\uffff\1\17\11\uffff\1\17";
    static final String DFA20_minS =
        "\1\4\4\uffff\1\10\1\17\6\uffff\1\10\2\uffff\1\17";
    static final String DFA20_maxS =
        "\1\43\4\uffff\1\10\1\64\6\uffff\1\10\2\uffff\1\64";
    static final String DFA20_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1\uffff"+
        "\1\5\1\10\1\uffff";
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
            "\6\17\1\uffff\2\17\1\16\4\17\1\uffff\1\17\3\uffff\1\17\1\15"+
            "\1\17\2\uffff\16\17",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\20",
            "",
            "",
            "\6\17\1\uffff\2\17\1\16\4\17\1\uffff\1\17\3\uffff\1\17\1\15"+
            "\1\17\2\uffff\16\17"
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
            return "1287:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_attribute85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleconditional_attribute120 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_attribute141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_entryRuleexpression178 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleexpression234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_ruleexpression262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_entryRulesimple_expression297 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesimple_expression307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression353 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_rulesimple_expression364 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression385 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_rulesimple_expression396 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr457 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_expr467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleconditional_expr502 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr523 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleconditional_expr533 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr554 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_ruleconditional_expr565 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr586 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleconditional_expr596 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr617 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_18_in_ruleconditional_expr630 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_entryRulelogical_expression688 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_expression698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression745 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_rulelogical_expression755 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression776 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_rulelogical_term_in_entryRulelogical_term814 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_term824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term871 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_20_in_rulelogical_term881 = new BitSet(new long[]{0x0000780BA52001F0L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term902 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_entryRulelogical_factor940 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_factor950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rulelogical_factor986 = new BitSet(new long[]{0x0000780BA50001F0L});
    public static final BitSet FOLLOW_rulerelation_in_rulelogical_factor1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerelation_in_entryRulerelation1045 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerelation1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1102 = new BitSet(new long[]{0x001F800000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_rulerelation1125 = new BitSet(new long[]{0x0000780BA50001F0L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_rulerelation1144 = new BitSet(new long[]{0x0000780BA50001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_rulerelation1163 = new BitSet(new long[]{0x0000780BA50001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_rulerelation1182 = new BitSet(new long[]{0x0000780BA50001F0L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_rulerelation1201 = new BitSet(new long[]{0x0000780BA50001F0L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_rulerelation1220 = new BitSet(new long[]{0x0000780BA50001F0L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1282 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearithmetic_expression1292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1340 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1359 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1378 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1397 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1422 = new BitSet(new long[]{0x0000780000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1446 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1465 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1484 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1503 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1527 = new BitSet(new long[]{0x0000780000000002L});
    public static final BitSet FOLLOW_ruleterm_in_entryRuleterm1565 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleterm1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm1622 = new BitSet(new long[]{0x0000078000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_ruleterm1645 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_rulemul_op_div_in_ruleterm1664 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_ruleterm1683 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_ruleterm1702 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm1726 = new BitSet(new long[]{0x0000078000000002L});
    public static final BitSet FOLLOW_rulefactor_in_entryRulefactor1764 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefactor1774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor1821 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_22_in_rulefactor1832 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_23_in_rulefactor1848 = new BitSet(new long[]{0x0000000BA50001F0L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor1870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_entryRuleprimary1908 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleprimary1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary1960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleprimary1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleprimary2016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_VAL_in_ruleprimary2044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_ruleprimary2077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_ruleprimary2104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_ruleprimary2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleprimary2157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleprimary2174 = new BitSet(new long[]{0x0000780FA72041F0L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_ruleprimary2195 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleprimary2205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleprimary2223 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2244 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_27_in_ruleprimary2255 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2276 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_28_in_ruleprimary2288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleprimary2306 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleprimary2327 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleprimary2337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleprimary2362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_entryRulename_Function2411 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_Function2421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_in_rulename_Function2468 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_rulename_Function2488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2524 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleinitial_ref2534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleinitial_ref2569 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleinitial_ref2591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_entryRuleExprDer2626 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExprDer2636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleExprDer2671 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleExprDer2692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args2728 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_call_args2738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rulefunction_call_args2782 = new BitSet(new long[]{0x0000780BA72041F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_rulefunction_call_args2803 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_rulefunction_call_args2814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list_in_entryRuleexpression_list2850 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression_list2860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list2906 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_ruleexpression_list2917 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list2938 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rulename_in_entryRulename2976 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename2986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rulename3022 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3041 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_rulename3057 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3074 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3117 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_reference3127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rulecomponent_reference3163 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3182 = new BitSet(new long[]{0x0000000804000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3208 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_rulecomponent_reference3220 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3237 = new BitSet(new long[]{0x0000000804000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3263 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3302 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleoutput_expression_list3312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3367 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_ruleoutput_expression_list3379 = new BitSet(new long[]{0x0000780FA52041F2L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3400 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3439 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearray_subscripts3449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulearray_subscripts3484 = new BitSet(new long[]{0x0000780BA520C1F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3505 = new BitSet(new long[]{0x0000000410000000L});
    public static final BitSet FOLLOW_34_in_rulearray_subscripts3516 = new BitSet(new long[]{0x0000780BA520C1F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3537 = new BitSet(new long[]{0x0000000410000000L});
    public static final BitSet FOLLOW_28_in_rulearray_subscripts3549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesubscript_in_entryRulesubscript3585 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesubscript3595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rulesubscript3640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulesubscript3668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments3704 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_arguments3714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefunction_arguments3770 = new BitSet(new long[]{0x0000001400000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments3792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments3819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulefunction_arguments3849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp3885 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_exp3895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleFun_Arguments_exp3930 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp3951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for3987 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_for3997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleFun_Arguments_for4032 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4089 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_arguments4099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_rulenamed_arguments4146 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_rulenamed_arguments4156 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulenamed_arguments4177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_entryRulenamed_argument4215 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_argument4225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulenamed_argument4267 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_rulenamed_argument4282 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulenamed_argument4303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_indices_in_entryRulefor_indices4339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_indices4349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4396 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_rulefor_indices4406 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4427 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rulefor_index_in_entryRulefor_index4465 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_index4475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulefor_index4517 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_rulefor_index4533 = new BitSet(new long[]{0x0000780BA52041F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_index4554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4593 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_mul4604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_rulemul_op_mul4641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_div_in_entryRulemul_op_div4681 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_div4692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rulemul_op_div4729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul4769 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotmul4780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rulemul_op_dotmul4817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv4857 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotdiv4868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rulemul_op_dotdiv4905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus4945 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_plus4956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleadd_op_plus4993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5033 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_minus5044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleadd_op_minus5081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotplus5132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleadd_op_dotplus5169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5209 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotminus5220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleadd_op_dotminus5257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5297 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_then5308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_rulerel_op_Less_then5345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5385 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_equal5396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_rulerel_op_Less_equal5433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5473 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_then5484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rulerel_op_greater_then5521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5561 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_equal5572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_rulerel_op_greater_equal5609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment5649 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_assignment5660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rulerel_op_assignment5697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper5737 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Oper5748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_rulerel_op_Oper5785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_entryRulestring_comment5827 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulestring_comment5838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment5878 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_rulestring_comment5897 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment5912 = new BitSet(new long[]{0x0000080000000002L});

}