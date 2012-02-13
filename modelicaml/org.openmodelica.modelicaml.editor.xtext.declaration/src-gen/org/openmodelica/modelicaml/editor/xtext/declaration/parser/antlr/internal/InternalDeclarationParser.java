package org.openmodelica.modelicaml.editor.xtext.declaration.parser.antlr.internal; 

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
import org.openmodelica.modelicaml.editor.xtext.declaration.services.DeclarationGrammarAccess;

@SuppressWarnings("all")
public class InternalDeclarationParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNSIGNED_NUMBER", "RULE_INT", "RULE_STRING", "RULE_BOOL_VAL", "RULE_IDENT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'='", "':='", "'if'", "':'", "'then'", "'elseif'", "'else'", "'or'", "'and'", "'not'", "'^'", "'.^'", "'('", "')'", "'['", "';'", "']'", "'{'", "'}'", "'end'", "'initial'", "'der'", "','", "'.'", "'for'", "'in'", "'*'", "'/'", "'.*'", "'./'", "'+'", "'-'", "'.+'", "'.-'", "'<'", "'<='", "'>'", "'>='", "'=='", "'<>'"
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

        public InternalDeclarationParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g"; }



     	private DeclarationGrammarAccess grammarAccess;
     	
        public InternalDeclarationParser(TokenStream input, IAstFactory factory, DeclarationGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "component_declaration";	
       	}
       	
       	@Override
       	protected DeclarationGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRulecomponent_declaration
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:77:1: entryRulecomponent_declaration returns [EObject current=null] : iv_rulecomponent_declaration= rulecomponent_declaration EOF ;
    public final EObject entryRulecomponent_declaration() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_declaration = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:78:2: (iv_rulecomponent_declaration= rulecomponent_declaration EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:79:2: iv_rulecomponent_declaration= rulecomponent_declaration EOF
            {
             currentNode = createCompositeNode(grammarAccess.getComponent_declarationRule(), currentNode); 
            pushFollow(FOLLOW_rulecomponent_declaration_in_entryRulecomponent_declaration75);
            iv_rulecomponent_declaration=rulecomponent_declaration();
            _fsp--;

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
    // $ANTLR end entryRulecomponent_declaration


    // $ANTLR start rulecomponent_declaration
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:86:1: rulecomponent_declaration returns [EObject current=null] : ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )? ;
    public final EObject rulecomponent_declaration() throws RecognitionException {
        EObject current = null;

        EObject lv_Mod_0_0 = null;

        EObject lv_Conditional_Att_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:91:6: ( ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:92:1: ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:92:1: ( ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=14 && LA2_0<=15)) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:92:2: ( (lv_Mod_0_0= rulemodification ) ) ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )? rulecomment
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:92:2: ( (lv_Mod_0_0= rulemodification ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:93:1: (lv_Mod_0_0= rulemodification )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:93:1: (lv_Mod_0_0= rulemodification )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:94:3: lv_Mod_0_0= rulemodification
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getComponent_declarationAccess().getModModificationParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulemodification_in_rulecomponent_declaration131);
                    lv_Mod_0_0=rulemodification();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getComponent_declarationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Mod",
                    	        		lv_Mod_0_0, 
                    	        		"modification", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:116:2: ( (lv_Conditional_Att_1_0= ruleconditional_attribute ) )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==16) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:117:1: (lv_Conditional_Att_1_0= ruleconditional_attribute )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:117:1: (lv_Conditional_Att_1_0= ruleconditional_attribute )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:118:3: lv_Conditional_Att_1_0= ruleconditional_attribute
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getComponent_declarationAccess().getConditional_AttConditional_attributeParserRuleCall_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleconditional_attribute_in_rulecomponent_declaration152);
                            lv_Conditional_Att_1_0=ruleconditional_attribute();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getComponent_declarationRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        try {
                            	       		set(
                            	       			current, 
                            	       			"Conditional_Att",
                            	        		lv_Conditional_Att_1_0, 
                            	        		"conditional_attribute", 
                            	        		currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }


                            }
                            break;

                    }

                     
                            currentNode=createCompositeNode(grammarAccess.getComponent_declarationAccess().getCommentParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_rulecomment_in_rulecomponent_declaration169);
                    rulecomment();
                    _fsp--;

                     
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
    // $ANTLR end rulecomponent_declaration


    // $ANTLR start entryRulemodification
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:156:1: entryRulemodification returns [EObject current=null] : iv_rulemodification= rulemodification EOF ;
    public final EObject entryRulemodification() throws RecognitionException {
        EObject current = null;

        EObject iv_rulemodification = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:157:2: (iv_rulemodification= rulemodification EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:158:2: iv_rulemodification= rulemodification EOF
            {
             currentNode = createCompositeNode(grammarAccess.getModificationRule(), currentNode); 
            pushFollow(FOLLOW_rulemodification_in_entryRulemodification205);
            iv_rulemodification=rulemodification();
            _fsp--;

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
    // $ANTLR end entryRulemodification


    // $ANTLR start rulemodification
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:165:1: rulemodification returns [EObject current=null] : ( ( '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | ( ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) ) ;
    public final EObject rulemodification() throws RecognitionException {
        EObject current = null;

        EObject lv_Expr_1_0 = null;

        EObject lv_Expr_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:170:6: ( ( ( '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | ( ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:171:1: ( ( '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | ( ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:171:1: ( ( '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | ( ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) )
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
                    new NoViableAltException("171:1: ( ( '=' ( (lv_Expr_1_0= ruleexpression ) ) ) | ( ':=' ( (lv_Expr_3_0= ruleexpression ) ) ) )", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:171:2: ( '=' ( (lv_Expr_1_0= ruleexpression ) ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:171:2: ( '=' ( (lv_Expr_1_0= ruleexpression ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:171:4: '=' ( (lv_Expr_1_0= ruleexpression ) )
                    {
                    match(input,14,FOLLOW_14_in_rulemodification251); 

                            createLeafNode(grammarAccess.getModificationAccess().getEqualsSignKeyword_0_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:175:1: ( (lv_Expr_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:176:1: (lv_Expr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:176:1: (lv_Expr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:177:3: lv_Expr_1_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getModificationAccess().getExprExpressionParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulemodification272);
                    lv_Expr_1_0=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getModificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Expr",
                    	        		lv_Expr_1_0, 
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
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:200:6: ( ':=' ( (lv_Expr_3_0= ruleexpression ) ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:200:6: ( ':=' ( (lv_Expr_3_0= ruleexpression ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:200:8: ':=' ( (lv_Expr_3_0= ruleexpression ) )
                    {
                    match(input,15,FOLLOW_15_in_rulemodification290); 

                            createLeafNode(grammarAccess.getModificationAccess().getColonEqualsSignKeyword_1_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:204:1: ( (lv_Expr_3_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:205:1: (lv_Expr_3_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:205:1: (lv_Expr_3_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:206:3: lv_Expr_3_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getModificationAccess().getExprExpressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulemodification311);
                    lv_Expr_3_0=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getModificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
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
    // $ANTLR end rulemodification


    // $ANTLR start entryRuleconditional_attribute
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:236:1: entryRuleconditional_attribute returns [EObject current=null] : iv_ruleconditional_attribute= ruleconditional_attribute EOF ;
    public final EObject entryRuleconditional_attribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_attribute = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:237:2: (iv_ruleconditional_attribute= ruleconditional_attribute EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:238:2: iv_ruleconditional_attribute= ruleconditional_attribute EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConditional_attributeRule(), currentNode); 
            pushFollow(FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute348);
            iv_ruleconditional_attribute=ruleconditional_attribute();
            _fsp--;

             current =iv_ruleconditional_attribute; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_attribute358); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:245:1: ruleconditional_attribute returns [EObject current=null] : ( 'if' ( (lv_Expr_1_0= ruleexpression ) ) ) ;
    public final EObject ruleconditional_attribute() throws RecognitionException {
        EObject current = null;

        EObject lv_Expr_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:250:6: ( ( 'if' ( (lv_Expr_1_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:251:1: ( 'if' ( (lv_Expr_1_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:251:1: ( 'if' ( (lv_Expr_1_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:251:3: 'if' ( (lv_Expr_1_0= ruleexpression ) )
            {
            match(input,16,FOLLOW_16_in_ruleconditional_attribute393); 

                    createLeafNode(grammarAccess.getConditional_attributeAccess().getIfKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:255:1: ( (lv_Expr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:256:1: (lv_Expr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:256:1: (lv_Expr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:257:3: lv_Expr_1_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_attributeAccess().getExprExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_attribute414);
            lv_Expr_1_0=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConditional_attributeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"Expr",
            	        		lv_Expr_1_0, 
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
    // $ANTLR end ruleconditional_attribute


    // $ANTLR start entryRuleexpression
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:287:1: entryRuleexpression returns [EObject current=null] : iv_ruleexpression= ruleexpression EOF ;
    public final EObject entryRuleexpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:288:2: (iv_ruleexpression= ruleexpression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:289:2: iv_ruleexpression= ruleexpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleexpression_in_entryRuleexpression450);
            iv_ruleexpression=ruleexpression();
            _fsp--;

             current =iv_ruleexpression; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression460); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:296:1: ruleexpression returns [EObject current=null] : ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) ;
    public final EObject ruleexpression() throws RecognitionException {
        EObject current = null;

        EObject lv_Expr_0_0 = null;

        EObject this_conditional_expr_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:301:6: ( ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:302:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:302:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )
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
                    new NoViableAltException("302:1: ( ( (lv_Expr_0_0= rulesimple_expression ) ) | this_conditional_expr_1= ruleconditional_expr )", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:302:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:302:2: ( (lv_Expr_0_0= rulesimple_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:303:1: (lv_Expr_0_0= rulesimple_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:303:1: (lv_Expr_0_0= rulesimple_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:304:3: lv_Expr_0_0= rulesimple_expression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getExprSimple_expressionParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulesimple_expression_in_ruleexpression506);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:328:5: this_conditional_expr_1= ruleconditional_expr
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getConditional_exprParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleconditional_expr_in_ruleexpression534);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:344:1: entryRulesimple_expression returns [EObject current=null] : iv_rulesimple_expression= rulesimple_expression EOF ;
    public final EObject entryRulesimple_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesimple_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:345:2: (iv_rulesimple_expression= rulesimple_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:346:2: iv_rulesimple_expression= rulesimple_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSimple_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulesimple_expression_in_entryRulesimple_expression569);
            iv_rulesimple_expression=rulesimple_expression();
            _fsp--;

             current =iv_rulesimple_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesimple_expression579); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:353:1: rulesimple_expression returns [EObject current=null] : ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) ;
    public final EObject rulesimple_expression() throws RecognitionException {
        EObject current = null;

        EObject lv_Log_Exp_0_0 = null;

        EObject lv_S_Logical_expression_2_0 = null;

        EObject lv_L_Logical_expression_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:358:6: ( ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:359:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:359:1: ( ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:359:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) ) ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:359:2: ( (lv_Log_Exp_0_0= rulelogical_expression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:360:1: (lv_Log_Exp_0_0= rulelogical_expression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:360:1: (lv_Log_Exp_0_0= rulelogical_expression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:361:3: lv_Log_Exp_0_0= rulelogical_expression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getSimple_expressionAccess().getLog_ExpLogical_expressionParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression625);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:383:2: ( ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )? )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==17) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:383:4: ':' ( (lv_S_Logical_expression_2_0= rulelogical_expression ) ) ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    {
                    match(input,17,FOLLOW_17_in_rulesimple_expression636); 

                            createLeafNode(grammarAccess.getSimple_expressionAccess().getColonKeyword_1_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:387:1: ( (lv_S_Logical_expression_2_0= rulelogical_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:388:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:388:1: (lv_S_Logical_expression_2_0= rulelogical_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:389:3: lv_S_Logical_expression_2_0= rulelogical_expression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSimple_expressionAccess().getS_Logical_expressionLogical_expressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression657);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:411:2: ( ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) ) )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==17) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:411:4: ':' ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            {
                            match(input,17,FOLLOW_17_in_rulesimple_expression668); 

                                    createLeafNode(grammarAccess.getSimple_expressionAccess().getColonKeyword_1_2_0(), null); 
                                
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:415:1: ( (lv_L_Logical_expression_4_0= rulelogical_expression ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:416:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:416:1: (lv_L_Logical_expression_4_0= rulelogical_expression )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:417:3: lv_L_Logical_expression_4_0= rulelogical_expression
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getSimple_expressionAccess().getL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expression689);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:447:1: entryRuleconditional_expr returns [EObject current=null] : iv_ruleconditional_expr= ruleconditional_expr EOF ;
    public final EObject entryRuleconditional_expr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_expr = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:448:2: (iv_ruleconditional_expr= ruleconditional_expr EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:449:2: iv_ruleconditional_expr= ruleconditional_expr EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConditional_exprRule(), currentNode); 
            pushFollow(FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr729);
            iv_ruleconditional_expr=ruleconditional_expr();
            _fsp--;

             current =iv_ruleconditional_expr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_expr739); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:456:1: ruleconditional_expr returns [EObject current=null] : ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) ;
    public final EObject ruleconditional_expr() throws RecognitionException {
        EObject current = null;

        EObject lv_ifexpr_1_0 = null;

        EObject lv_thenexpr_3_0 = null;

        EObject lv_elseifexpr_5_0 = null;

        EObject lv_trueexpr_7_0 = null;

        EObject lv_falseexpr_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:461:6: ( ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:462:1: ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:462:1: ( 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:462:3: 'if' ( (lv_ifexpr_1_0= ruleexpression ) ) 'then' ( (lv_thenexpr_3_0= ruleexpression ) ) ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )* ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            {
            match(input,16,FOLLOW_16_in_ruleconditional_expr774); 

                    createLeafNode(grammarAccess.getConditional_exprAccess().getIfKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:466:1: ( (lv_ifexpr_1_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:467:1: (lv_ifexpr_1_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:467:1: (lv_ifexpr_1_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:468:3: lv_ifexpr_1_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getIfexprExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr795);
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

            match(input,18,FOLLOW_18_in_ruleconditional_expr805); 

                    createLeafNode(grammarAccess.getConditional_exprAccess().getThenKeyword_2(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:494:1: ( (lv_thenexpr_3_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:495:1: (lv_thenexpr_3_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:495:1: (lv_thenexpr_3_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:496:3: lv_thenexpr_3_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getThenexprExpressionParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr826);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:518:2: ( 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==19) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:518:4: 'elseif' ( (lv_elseifexpr_5_0= ruleexpression ) ) 'then' ( (lv_trueexpr_7_0= ruleexpression ) )
            	    {
            	    match(input,19,FOLLOW_19_in_ruleconditional_expr837); 

            	            createLeafNode(grammarAccess.getConditional_exprAccess().getElseifKeyword_4_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:522:1: ( (lv_elseifexpr_5_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:523:1: (lv_elseifexpr_5_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:523:1: (lv_elseifexpr_5_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:524:3: lv_elseifexpr_5_0= ruleexpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getElseifexprExpressionParserRuleCall_4_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr858);
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

            	    match(input,18,FOLLOW_18_in_ruleconditional_expr868); 

            	            createLeafNode(grammarAccess.getConditional_exprAccess().getThenKeyword_4_2(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:550:1: ( (lv_trueexpr_7_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:551:1: (lv_trueexpr_7_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:551:1: (lv_trueexpr_7_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:552:3: lv_trueexpr_7_0= ruleexpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getTrueexprExpressionParserRuleCall_4_3_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr889);
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
            	    break loop7;
                }
            } while (true);

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:574:4: ( 'else' ( (lv_falseexpr_9_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:574:6: 'else' ( (lv_falseexpr_9_0= ruleexpression ) )
            {
            match(input,20,FOLLOW_20_in_ruleconditional_expr902); 

                    createLeafNode(grammarAccess.getConditional_exprAccess().getElseKeyword_5_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:578:1: ( (lv_falseexpr_9_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:579:1: (lv_falseexpr_9_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:579:1: (lv_falseexpr_9_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:580:3: lv_falseexpr_9_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_exprAccess().getFalseexprExpressionParserRuleCall_5_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_expr923);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:610:1: entryRulelogical_expression returns [EObject current=null] : iv_rulelogical_expression= rulelogical_expression EOF ;
    public final EObject entryRulelogical_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:611:2: (iv_rulelogical_expression= rulelogical_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:612:2: iv_rulelogical_expression= rulelogical_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_expression_in_entryRulelogical_expression960);
            iv_rulelogical_expression=rulelogical_expression();
            _fsp--;

             current =iv_rulelogical_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_expression970); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:619:1: rulelogical_expression returns [EObject current=null] : (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) ;
    public final EObject rulelogical_expression() throws RecognitionException {
        EObject current = null;

        EObject this_logical_term_0 = null;

        EObject lv_Logical_term_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:624:6: ( (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:625:1: (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:625:1: (this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:626:5: this_logical_term_0= rulelogical_term ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression1017);
            this_logical_term_0=rulelogical_term();
            _fsp--;

             
                    current = this_logical_term_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:634:1: ( 'or' ( (lv_Logical_term_2_0= rulelogical_term ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==21) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:634:3: 'or' ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    {
            	    match(input,21,FOLLOW_21_in_rulelogical_expression1027); 

            	            createLeafNode(grammarAccess.getLogical_expressionAccess().getOrKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:638:1: ( (lv_Logical_term_2_0= rulelogical_term ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:639:1: (lv_Logical_term_2_0= rulelogical_term )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:639:1: (lv_Logical_term_2_0= rulelogical_term )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:640:3: lv_Logical_term_2_0= rulelogical_term
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termLogical_termParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression1048);
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
            	    break loop8;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:670:1: entryRulelogical_term returns [EObject current=null] : iv_rulelogical_term= rulelogical_term EOF ;
    public final EObject entryRulelogical_term() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_term = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:671:2: (iv_rulelogical_term= rulelogical_term EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:672:2: iv_rulelogical_term= rulelogical_term EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_termRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_term_in_entryRulelogical_term1086);
            iv_rulelogical_term=rulelogical_term();
            _fsp--;

             current =iv_rulelogical_term; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_term1096); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:679:1: rulelogical_term returns [EObject current=null] : (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) ;
    public final EObject rulelogical_term() throws RecognitionException {
        EObject current = null;

        EObject this_logical_factor_0 = null;

        EObject lv_Logical_factor_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:684:6: ( (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:685:1: (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:685:1: (this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:686:5: this_logical_factor_0= rulelogical_factor ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term1143);
            this_logical_factor_0=rulelogical_factor();
            _fsp--;

             
                    current = this_logical_factor_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:694:1: ( 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==22) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:694:3: 'and' ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    {
            	    match(input,22,FOLLOW_22_in_rulelogical_term1153); 

            	            createLeafNode(grammarAccess.getLogical_termAccess().getAndKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:698:1: ( (lv_Logical_factor_2_0= rulelogical_factor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:699:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:699:1: (lv_Logical_factor_2_0= rulelogical_factor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:700:3: lv_Logical_factor_2_0= rulelogical_factor
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorLogical_factorParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term1174);
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
            	    break loop9;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:730:1: entryRulelogical_factor returns [EObject current=null] : iv_rulelogical_factor= rulelogical_factor EOF ;
    public final EObject entryRulelogical_factor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulelogical_factor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:731:2: (iv_rulelogical_factor= rulelogical_factor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:732:2: iv_rulelogical_factor= rulelogical_factor EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_factorRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_factor_in_entryRulelogical_factor1212);
            iv_rulelogical_factor=rulelogical_factor();
            _fsp--;

             current =iv_rulelogical_factor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_factor1222); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:739:1: rulelogical_factor returns [EObject current=null] : ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) ;
    public final EObject rulelogical_factor() throws RecognitionException {
        EObject current = null;

        EObject lv_Relation_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:744:6: ( ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:745:1: ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:745:1: ( ( 'not' )? ( (lv_Relation_1_0= rulerelation ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:745:2: ( 'not' )? ( (lv_Relation_1_0= rulerelation ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:745:2: ( 'not' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:745:4: 'not'
                    {
                    match(input,23,FOLLOW_23_in_rulelogical_factor1258); 

                            createLeafNode(grammarAccess.getLogical_factorAccess().getNotKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:749:3: ( (lv_Relation_1_0= rulerelation ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:750:1: (lv_Relation_1_0= rulerelation )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:750:1: (lv_Relation_1_0= rulerelation )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:751:3: lv_Relation_1_0= rulerelation
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getLogical_factorAccess().getRelationRelationParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulerelation_in_rulelogical_factor1281);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:781:1: entryRulerelation returns [EObject current=null] : iv_rulerelation= rulerelation EOF ;
    public final EObject entryRulerelation() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelation = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:782:2: (iv_rulerelation= rulerelation EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:783:2: iv_rulerelation= rulerelation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRelationRule(), currentNode); 
            pushFollow(FOLLOW_rulerelation_in_entryRulerelation1317);
            iv_rulerelation=rulerelation();
            _fsp--;

             current =iv_rulerelation; 
            match(input,EOF,FOLLOW_EOF_in_entryRulerelation1327); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:790:1: rulerelation returns [EObject current=null] : (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:795:6: ( (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:796:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:796:1: (this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:797:5: this_arithmetic_expression_0= rulearithmetic_expression ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1374);
            this_arithmetic_expression_0=rulearithmetic_expression();
            _fsp--;

             
                    current = this_arithmetic_expression_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:805:1: ( ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=48 && LA12_0<=53)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:805:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) ) ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:805:2: ( ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:806:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:806:1: ( (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:807:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:807:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )
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
                            new NoViableAltException("807:1: (lv_op_1_1= rulerel_op_Less_then | lv_op_1_2= rulerel_op_Less_equal | lv_op_1_3= rulerel_op_greater_then | lv_op_1_4= rulerel_op_greater_equal | lv_op_1_5= rulerel_op_assignment | lv_op_1_6= rulerel_op_Oper )", 11, 0, input);

                        throw nvae;
                    }

                    switch (alt11) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:808:3: lv_op_1_1= rulerel_op_Less_then
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_thenParserRuleCall_1_0_0_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_then_in_rulerelation1397);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:829:8: lv_op_1_2= rulerel_op_Less_equal
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_Less_equalParserRuleCall_1_0_0_1(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Less_equal_in_rulerelation1416);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:850:8: lv_op_1_3= rulerel_op_greater_then
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_thenParserRuleCall_1_0_0_2(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_then_in_rulerelation1435);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:871:8: lv_op_1_4= rulerel_op_greater_equal
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_greater_equalParserRuleCall_1_0_0_3(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_greater_equal_in_rulerelation1454);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:892:8: lv_op_1_5= rulerel_op_assignment
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_assignmentParserRuleCall_1_0_0_4(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_assignment_in_rulerelation1473);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:913:8: lv_op_1_6= rulerel_op_Oper
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getOpRel_op_OperParserRuleCall_1_0_0_5(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulerel_op_Oper_in_rulerelation1492);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:937:2: ( (lv_Arithmetic_expression_2_0= rulearithmetic_expression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:938:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:938:1: (lv_Arithmetic_expression_2_0= rulearithmetic_expression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:939:3: lv_Arithmetic_expression_2_0= rulearithmetic_expression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation1516);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:969:1: entryRulearithmetic_expression returns [EObject current=null] : iv_rulearithmetic_expression= rulearithmetic_expression EOF ;
    public final EObject entryRulearithmetic_expression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearithmetic_expression = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:970:2: (iv_rulearithmetic_expression= rulearithmetic_expression EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:971:2: iv_rulearithmetic_expression= rulearithmetic_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArithmetic_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1554);
            iv_rulearithmetic_expression=rulearithmetic_expression();
            _fsp--;

             current =iv_rulearithmetic_expression; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearithmetic_expression1564); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:978:1: rulearithmetic_expression returns [EObject current=null] : ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:983:6: ( ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:984:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:984:1: ( ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:984:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )? ( (lv_Term_1_0= ruleterm ) ) ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:984:2: ( ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=44 && LA14_0<=47)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:985:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:985:1: ( (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:986:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:986:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )
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
                            new NoViableAltException("986:1: (lv_opr_0_1= ruleadd_op_plus | lv_opr_0_2= ruleadd_op_minus | lv_opr_0_3= ruleadd_op_dotplus | lv_opr_0_4= ruleadd_op_dotminus )", 13, 0, input);

                        throw nvae;
                    }

                    switch (alt13) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:987:3: lv_opr_0_1= ruleadd_op_plus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_plusParserRuleCall_0_0_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1612);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1008:8: lv_opr_0_2= ruleadd_op_minus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_minusParserRuleCall_0_0_1(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1631);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1029:8: lv_opr_0_3= ruleadd_op_dotplus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotplusParserRuleCall_0_0_2(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1650);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1050:8: lv_opr_0_4= ruleadd_op_dotminus
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotminusParserRuleCall_0_0_3(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1669);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1074:3: ( (lv_Term_1_0= ruleterm ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1075:1: (lv_Term_1_0= ruleterm )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1075:1: (lv_Term_1_0= ruleterm )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1076:3: lv_Term_1_0= ruleterm
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTermTermParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1694);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1098:2: ( ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=44 && LA16_0<=47)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1098:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) ) ( (lv_Term1_3_0= ruleterm ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1098:3: ( ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1099:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1099:1: ( (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1100:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1100:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )
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
            	            new NoViableAltException("1100:1: (lv_Oper1_2_1= ruleadd_op_plus | lv_Oper1_2_2= ruleadd_op_minus | lv_Oper1_2_3= ruleadd_op_dotplus | lv_Oper1_2_4= ruleadd_op_dotminus )", 15, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt15) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1101:3: lv_Oper1_2_1= ruleadd_op_plus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_plusParserRuleCall_2_0_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1718);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1122:8: lv_Oper1_2_2= ruleadd_op_minus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_minusParserRuleCall_2_0_0_1(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1737);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1143:8: lv_Oper1_2_3= ruleadd_op_dotplus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotplusParserRuleCall_2_0_0_2(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1756);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1164:8: lv_Oper1_2_4= ruleadd_op_dotminus
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotminusParserRuleCall_2_0_0_3(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1775);
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

            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1188:2: ( (lv_Term1_3_0= ruleterm ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1189:1: (lv_Term1_3_0= ruleterm )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1189:1: (lv_Term1_3_0= ruleterm )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1190:3: lv_Term1_3_0= ruleterm
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTerm1TermParserRuleCall_2_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression1799);
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
    // $ANTLR end rulearithmetic_expression


    // $ANTLR start entryRuleterm
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1220:1: entryRuleterm returns [EObject current=null] : iv_ruleterm= ruleterm EOF ;
    public final EObject entryRuleterm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleterm = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1221:2: (iv_ruleterm= ruleterm EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1222:2: iv_ruleterm= ruleterm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTermRule(), currentNode); 
            pushFollow(FOLLOW_ruleterm_in_entryRuleterm1837);
            iv_ruleterm=ruleterm();
            _fsp--;

             current =iv_ruleterm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleterm1847); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1229:1: ruleterm returns [EObject current=null] : (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1234:6: ( (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1235:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1235:1: (this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1236:5: this_factor_0= rulefactor ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getTermAccess().getFactorParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulefactor_in_ruleterm1894);
            this_factor_0=rulefactor();
            _fsp--;

             
                    current = this_factor_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1244:1: ( ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=40 && LA18_0<=43)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1244:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) ) ( (lv_Factor_2_0= rulefactor ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1244:2: ( ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1245:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1245:1: ( (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1246:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1246:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )
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
            	            new NoViableAltException("1246:1: (lv_op_1_1= rulemul_op_mul | lv_op_1_2= rulemul_op_div | lv_op_1_3= rulemul_op_dotmul | lv_op_1_4= rulemul_op_dotdiv )", 17, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt17) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1247:3: lv_op_1_1= rulemul_op_mul
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_mulParserRuleCall_1_0_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_mul_in_ruleterm1917);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1268:8: lv_op_1_2= rulemul_op_div
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_divParserRuleCall_1_0_0_1(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_div_in_ruleterm1936);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1289:8: lv_op_1_3= rulemul_op_dotmul
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotmulParserRuleCall_1_0_0_2(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotmul_in_ruleterm1955);
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
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1310:8: lv_op_1_4= rulemul_op_dotdiv
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getOpMul_op_dotdivParserRuleCall_1_0_0_3(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulemul_op_dotdiv_in_ruleterm1974);
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

            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1334:2: ( (lv_Factor_2_0= rulefactor ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1335:1: (lv_Factor_2_0= rulefactor )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1335:1: (lv_Factor_2_0= rulefactor )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1336:3: lv_Factor_2_0= rulefactor
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getTermAccess().getFactorFactorParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulefactor_in_ruleterm1998);
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
            	    break loop18;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1366:1: entryRulefactor returns [EObject current=null] : iv_rulefactor= rulefactor EOF ;
    public final EObject entryRulefactor() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefactor = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1367:2: (iv_rulefactor= rulefactor EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1368:2: iv_rulefactor= rulefactor EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFactorRule(), currentNode); 
            pushFollow(FOLLOW_rulefactor_in_entryRulefactor2036);
            iv_rulefactor=rulefactor();
            _fsp--;

             current =iv_rulefactor; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefactor2046); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1375:1: rulefactor returns [EObject current=null] : (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) ;
    public final EObject rulefactor() throws RecognitionException {
        EObject current = null;

        EObject this_primary_0 = null;

        EObject lv_Primary_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1380:6: ( (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1381:1: (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1381:1: (this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1382:5: this_primary_0= ruleprimary ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFactorAccess().getPrimaryParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleprimary_in_rulefactor2093);
            this_primary_0=ruleprimary();
            _fsp--;

             
                    current = this_primary_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1390:1: ( ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=24 && LA20_0<=25)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1390:2: ( '^' | '.^' ) ( (lv_Primary_3_0= ruleprimary ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1390:2: ( '^' | '.^' )
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
                            new NoViableAltException("1390:2: ( '^' | '.^' )", 19, 0, input);

                        throw nvae;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1390:4: '^'
                            {
                            match(input,24,FOLLOW_24_in_rulefactor2104); 

                                    createLeafNode(grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1395:7: '.^'
                            {
                            match(input,25,FOLLOW_25_in_rulefactor2120); 

                                    createLeafNode(grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1(), null); 
                                

                            }
                            break;

                    }

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1399:2: ( (lv_Primary_3_0= ruleprimary ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1400:1: (lv_Primary_3_0= ruleprimary )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1400:1: (lv_Primary_3_0= ruleprimary )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1401:3: lv_Primary_3_0= ruleprimary
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFactorAccess().getPrimaryPrimaryParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleprimary_in_rulefactor2142);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1431:1: entryRuleprimary returns [EObject current=null] : iv_ruleprimary= ruleprimary EOF ;
    public final EObject entryRuleprimary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleprimary = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1432:2: (iv_ruleprimary= ruleprimary EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1433:2: iv_ruleprimary= ruleprimary EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrimaryRule(), currentNode); 
            pushFollow(FOLLOW_ruleprimary_in_entryRuleprimary2180);
            iv_ruleprimary=ruleprimary();
            _fsp--;

             current =iv_ruleprimary; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleprimary2190); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1440:1: ruleprimary returns [EObject current=null] : ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) ) ;
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
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1445:6: ( ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1446:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1446:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) )
            int alt22=12;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1446:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1446:2: ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1447:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1447:1: (lv_num_0_0= RULE_UNSIGNED_NUMBER )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1448:3: lv_num_0_0= RULE_UNSIGNED_NUMBER
                    {
                    lv_num_0_0=(Token)input.LT(1);
                    match(input,RULE_UNSIGNED_NUMBER,FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary2232); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1471:6: ( (lv_int_1_0= RULE_INT ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1471:6: ( (lv_int_1_0= RULE_INT ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1472:1: (lv_int_1_0= RULE_INT )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1472:1: (lv_int_1_0= RULE_INT )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1473:3: lv_int_1_0= RULE_INT
                    {
                    lv_int_1_0=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleprimary2260); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1496:6: ( (lv_str_2_0= RULE_STRING ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1496:6: ( (lv_str_2_0= RULE_STRING ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1497:1: (lv_str_2_0= RULE_STRING )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1497:1: (lv_str_2_0= RULE_STRING )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1498:3: lv_str_2_0= RULE_STRING
                    {
                    lv_str_2_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleprimary2288); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1521:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1521:6: ( (lv_Bool_3_0= RULE_BOOL_VAL ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1522:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1522:1: (lv_Bool_3_0= RULE_BOOL_VAL )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1523:3: lv_Bool_3_0= RULE_BOOL_VAL
                    {
                    lv_Bool_3_0=(Token)input.LT(1);
                    match(input,RULE_BOOL_VAL,FOLLOW_RULE_BOOL_VAL_in_ruleprimary2316); 

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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1547:5: this_name_Function_4= rulename_Function
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getName_FunctionParserRuleCall_4(), currentNode); 
                        
                    pushFollow(FOLLOW_rulename_Function_in_ruleprimary2349);
                    this_name_Function_4=rulename_Function();
                    _fsp--;

                     
                            current = this_name_Function_4; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 6 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1557:5: this_initial_ref_5= ruleinitial_ref
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getInitial_refParserRuleCall_5(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleinitial_ref_in_ruleprimary2376);
                    this_initial_ref_5=ruleinitial_ref();
                    _fsp--;

                     
                            current = this_initial_ref_5; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 7 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1567:5: this_ExprDer_6= ruleExprDer
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExprDerParserRuleCall_6(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleExprDer_in_ruleprimary2403);
                    this_ExprDer_6=ruleExprDer();
                    _fsp--;

                     
                            current = this_ExprDer_6; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 8 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1576:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1576:6: ( (lv_Component_reference_7_0= rulecomponent_reference ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1577:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1577:1: (lv_Component_reference_7_0= rulecomponent_reference )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1578:3: lv_Component_reference_7_0= rulecomponent_reference
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getComponent_referenceComponent_referenceParserRuleCall_7_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulecomponent_reference_in_ruleprimary2429);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1601:6: ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1601:6: ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1601:8: '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')'
                    {
                    match(input,26,FOLLOW_26_in_ruleprimary2446); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_8_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1605:1: ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1606:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1606:1: (lv_output_expr_list_9_0= ruleoutput_expression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1607:3: lv_output_expr_list_9_0= ruleoutput_expression_list
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getOutput_expr_listOutput_expression_listParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleoutput_expression_list_in_ruleprimary2467);
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

                    match(input,27,FOLLOW_27_in_ruleprimary2477); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_8_2(), null); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1634:6: ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1634:6: ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1634:8: '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']'
                    {
                    match(input,28,FOLLOW_28_in_ruleprimary2495); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getLeftSquareBracketKeyword_9_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1638:1: ( (lv_Expre_list_12_0= ruleexpression_list ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1639:1: (lv_Expre_list_12_0= ruleexpression_list )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1639:1: (lv_Expre_list_12_0= ruleexpression_list )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1640:3: lv_Expre_list_12_0= ruleexpression_list
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExpre_listExpression_listParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2516);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1662:2: ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==29) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1662:4: ';' ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    {
                    	    match(input,29,FOLLOW_29_in_ruleprimary2527); 

                    	            createLeafNode(grammarAccess.getPrimaryAccess().getSemicolonKeyword_9_2_0(), null); 
                    	        
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1666:1: ( (lv_Expression_list_14_0= ruleexpression_list ) )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1667:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    {
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1667:1: (lv_Expression_list_14_0= ruleexpression_list )
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1668:3: lv_Expression_list_14_0= ruleexpression_list
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listExpression_listParserRuleCall_9_2_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary2548);
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
                    	    break loop21;
                        }
                    } while (true);

                    match(input,30,FOLLOW_30_in_ruleprimary2560); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getRightSquareBracketKeyword_9_3(), null); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1695:6: ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1695:6: ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1695:8: '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}'
                    {
                    match(input,31,FOLLOW_31_in_ruleprimary2578); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getLeftCurlyBracketKeyword_10_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1699:1: ( (lv_f_arguments_17_0= rulefunction_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1700:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1700:1: (lv_f_arguments_17_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1701:3: lv_f_arguments_17_0= rulefunction_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getF_argumentsFunction_argumentsParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_ruleprimary2599);
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

                    match(input,32,FOLLOW_32_in_ruleprimary2609); 

                            createLeafNode(grammarAccess.getPrimaryAccess().getRightCurlyBracketKeyword_10_2(), null); 
                        

                    }


                    }
                    break;
                case 12 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1728:6: ( (lv_End_19_0= 'end' ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1728:6: ( (lv_End_19_0= 'end' ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1729:1: (lv_End_19_0= 'end' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1729:1: (lv_End_19_0= 'end' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1730:3: lv_End_19_0= 'end'
                    {
                    lv_End_19_0=(Token)input.LT(1);
                    match(input,33,FOLLOW_33_in_ruleprimary2634); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1757:1: entryRulename_Function returns [EObject current=null] : iv_rulename_Function= rulename_Function EOF ;
    public final EObject entryRulename_Function() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename_Function = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1758:2: (iv_rulename_Function= rulename_Function EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1759:2: iv_rulename_Function= rulename_Function EOF
            {
             currentNode = createCompositeNode(grammarAccess.getName_FunctionRule(), currentNode); 
            pushFollow(FOLLOW_rulename_Function_in_entryRulename_Function2683);
            iv_rulename_Function=rulename_Function();
            _fsp--;

             current =iv_rulename_Function; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_Function2693); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1766:1: rulename_Function returns [EObject current=null] : (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) ;
    public final EObject rulename_Function() throws RecognitionException {
        EObject current = null;

        EObject this_name_0 = null;

        EObject lv_Function_call_args_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1771:6: ( (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1772:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1772:1: (this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1773:5: this_name_0= rulename ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            {
             
                    currentNode=createCompositeNode(grammarAccess.getName_FunctionAccess().getNameParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulename_in_rulename_Function2740);
            this_name_0=rulename();
            _fsp--;

             
                    current = this_name_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1781:1: ( (lv_Function_call_args_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1782:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1782:1: (lv_Function_call_args_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1783:3: lv_Function_call_args_1_0= rulefunction_call_args
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getName_FunctionAccess().getFunction_call_argsFunction_call_argsParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_rulename_Function2760);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1813:1: entryRuleinitial_ref returns [EObject current=null] : iv_ruleinitial_ref= ruleinitial_ref EOF ;
    public final EObject entryRuleinitial_ref() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitial_ref = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1814:2: (iv_ruleinitial_ref= ruleinitial_ref EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1815:2: iv_ruleinitial_ref= ruleinitial_ref EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInitial_refRule(), currentNode); 
            pushFollow(FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2796);
            iv_ruleinitial_ref=ruleinitial_ref();
            _fsp--;

             current =iv_ruleinitial_ref; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleinitial_ref2806); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1822:1: ruleinitial_ref returns [EObject current=null] : ( 'initial' this_function_call_args_1= rulefunction_call_args ) ;
    public final EObject ruleinitial_ref() throws RecognitionException {
        EObject current = null;

        EObject this_function_call_args_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1827:6: ( ( 'initial' this_function_call_args_1= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1828:1: ( 'initial' this_function_call_args_1= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1828:1: ( 'initial' this_function_call_args_1= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1828:3: 'initial' this_function_call_args_1= rulefunction_call_args
            {
            match(input,34,FOLLOW_34_in_ruleinitial_ref2841); 

                    createLeafNode(grammarAccess.getInitial_refAccess().getInitialKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getInitial_refAccess().getFunction_call_argsParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleinitial_ref2863);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1849:1: entryRuleExprDer returns [EObject current=null] : iv_ruleExprDer= ruleExprDer EOF ;
    public final EObject entryRuleExprDer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExprDer = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1850:2: (iv_ruleExprDer= ruleExprDer EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1851:2: iv_ruleExprDer= ruleExprDer EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExprDerRule(), currentNode); 
            pushFollow(FOLLOW_ruleExprDer_in_entryRuleExprDer2898);
            iv_ruleExprDer=ruleExprDer();
            _fsp--;

             current =iv_ruleExprDer; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExprDer2908); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1858:1: ruleExprDer returns [EObject current=null] : ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) ;
    public final EObject ruleExprDer() throws RecognitionException {
        EObject current = null;

        EObject lv_functionArgs_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1863:6: ( ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1864:1: ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1864:1: ( 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1864:3: 'der' ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            {
            match(input,35,FOLLOW_35_in_ruleExprDer2943); 

                    createLeafNode(grammarAccess.getExprDerAccess().getDerKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1868:1: ( (lv_functionArgs_1_0= rulefunction_call_args ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1869:1: (lv_functionArgs_1_0= rulefunction_call_args )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1869:1: (lv_functionArgs_1_0= rulefunction_call_args )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1870:3: lv_functionArgs_1_0= rulefunction_call_args
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getExprDerAccess().getFunctionArgsFunction_call_argsParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefunction_call_args_in_ruleExprDer2964);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1900:1: entryRulefunction_call_args returns [EObject current=null] : iv_rulefunction_call_args= rulefunction_call_args EOF ;
    public final EObject entryRulefunction_call_args() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_call_args = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1901:2: (iv_rulefunction_call_args= rulefunction_call_args EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1902:2: iv_rulefunction_call_args= rulefunction_call_args EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFunction_call_argsRule(), currentNode); 
            pushFollow(FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args3000);
            iv_rulefunction_call_args=rulefunction_call_args();
            _fsp--;

             current =iv_rulefunction_call_args; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_call_args3010); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1909:1: rulefunction_call_args returns [EObject current=null] : ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' ) ;
    public final EObject rulefunction_call_args() throws RecognitionException {
        EObject current = null;

        EObject lv_f_arg_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1914:6: ( ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1915:1: ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1915:1: ( () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1915:2: () '(' ( (lv_f_arg_2_0= rulefunction_arguments ) )? ')'
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1915:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1916:5: 
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

            match(input,26,FOLLOW_26_in_rulefunction_call_args3054); 

                    createLeafNode(grammarAccess.getFunction_call_argsAccess().getLeftParenthesisKeyword_1(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1930:1: ( (lv_f_arg_2_0= rulefunction_arguments ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=RULE_UNSIGNED_NUMBER && LA23_0<=RULE_IDENT)||LA23_0==16||LA23_0==23||LA23_0==26||LA23_0==28||LA23_0==31||(LA23_0>=33 && LA23_0<=35)||LA23_0==37||(LA23_0>=44 && LA23_0<=47)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1931:1: (lv_f_arg_2_0= rulefunction_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1931:1: (lv_f_arg_2_0= rulefunction_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1932:3: lv_f_arg_2_0= rulefunction_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFunction_call_argsAccess().getF_argFunction_argumentsParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulefunction_arguments_in_rulefunction_call_args3075);
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

            match(input,27,FOLLOW_27_in_rulefunction_call_args3086); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1966:1: entryRuleexpression_list returns [EObject current=null] : iv_ruleexpression_list= ruleexpression_list EOF ;
    public final EObject entryRuleexpression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexpression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1967:2: (iv_ruleexpression_list= ruleexpression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1968:2: iv_ruleexpression_list= ruleexpression_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpression_listRule(), currentNode); 
            pushFollow(FOLLOW_ruleexpression_list_in_entryRuleexpression_list3122);
            iv_ruleexpression_list=ruleexpression_list();
            _fsp--;

             current =iv_ruleexpression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression_list3132); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1975:1: ruleexpression_list returns [EObject current=null] : ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) ;
    public final EObject ruleexpression_list() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_0_0 = null;

        EObject lv_Expre_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1980:6: ( ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1981:1: ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1981:1: ( ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1981:2: ( (lv_expr_0_0= ruleexpression ) ) ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1981:2: ( (lv_expr_0_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1982:1: (lv_expr_0_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1982:1: (lv_expr_0_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:1983:3: lv_expr_0_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getExpression_listAccess().getExprExpressionParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list3178);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2005:2: ( ',' ( (lv_Expre_2_0= ruleexpression ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==36) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2005:4: ',' ( (lv_Expre_2_0= ruleexpression ) )
            	    {
            	    match(input,36,FOLLOW_36_in_ruleexpression_list3189); 

            	            createLeafNode(grammarAccess.getExpression_listAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2009:1: ( (lv_Expre_2_0= ruleexpression ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2010:1: (lv_Expre_2_0= ruleexpression )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2010:1: (lv_Expre_2_0= ruleexpression )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2011:3: lv_Expre_2_0= ruleexpression
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getExpression_listAccess().getExpreExpressionParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list3210);
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
    // $ANTLR end ruleexpression_list


    // $ANTLR start entryRulename
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2041:1: entryRulename returns [EObject current=null] : iv_rulename= rulename EOF ;
    public final EObject entryRulename() throws RecognitionException {
        EObject current = null;

        EObject iv_rulename = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2042:2: (iv_rulename= rulename EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2043:2: iv_rulename= rulename EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNameRule(), currentNode); 
            pushFollow(FOLLOW_rulename_in_entryRulename3248);
            iv_rulename=rulename();
            _fsp--;

             current =iv_rulename; 
            match(input,EOF,FOLLOW_EOF_in_entryRulename3258); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2050:1: rulename returns [EObject current=null] : ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) ;
    public final EObject rulename() throws RecognitionException {
        EObject current = null;

        Token lv_name_ID_1_0=null;
        Token lv_nam_ID_3_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2055:6: ( ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2056:1: ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2056:1: ( ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2056:2: ( '.' )? ( (lv_name_ID_1_0= RULE_IDENT ) ) ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2056:2: ( '.' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==37) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2056:4: '.'
                    {
                    match(input,37,FOLLOW_37_in_rulename3294); 

                            createLeafNode(grammarAccess.getNameAccess().getFullStopKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2060:3: ( (lv_name_ID_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2061:1: (lv_name_ID_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2061:1: (lv_name_ID_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2062:3: lv_name_ID_1_0= RULE_IDENT
            {
            lv_name_ID_1_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3313); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2084:2: ( '.' ( (lv_nam_ID_3_0= RULE_IDENT ) ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==37) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2084:4: '.' ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    {
            	    match(input,37,FOLLOW_37_in_rulename3329); 

            	            createLeafNode(grammarAccess.getNameAccess().getFullStopKeyword_2_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2088:1: ( (lv_nam_ID_3_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2089:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2089:1: (lv_nam_ID_3_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2090:3: lv_nam_ID_3_0= RULE_IDENT
            	    {
            	    lv_nam_ID_3_0=(Token)input.LT(1);
            	    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename3346); 

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
            	    break loop26;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2120:1: entryRulecomponent_reference returns [EObject current=null] : iv_rulecomponent_reference= rulecomponent_reference EOF ;
    public final EObject entryRulecomponent_reference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_reference = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2121:2: (iv_rulecomponent_reference= rulecomponent_reference EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2122:2: iv_rulecomponent_reference= rulecomponent_reference EOF
            {
             currentNode = createCompositeNode(grammarAccess.getComponent_referenceRule(), currentNode); 
            pushFollow(FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3389);
            iv_rulecomponent_reference=rulecomponent_reference();
            _fsp--;

             current =iv_rulecomponent_reference; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_reference3399); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2129:1: rulecomponent_reference returns [EObject current=null] : ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) ;
    public final EObject rulecomponent_reference() throws RecognitionException {
        EObject current = null;

        Token lv_ref_1_0=null;
        Token lv_ref1_4_0=null;
        EObject lv_subscripts1_2_0 = null;

        EObject lv_subscripts_5_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2134:6: ( ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2135:1: ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2135:1: ( ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2135:2: ( '.' )? ( (lv_ref_1_0= RULE_IDENT ) ) ( (lv_subscripts1_2_0= rulearray_subscripts ) )? ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2135:2: ( '.' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==37) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2135:4: '.'
                    {
                    match(input,37,FOLLOW_37_in_rulecomponent_reference3435); 

                            createLeafNode(grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0(), null); 
                        

                    }
                    break;

            }

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2139:3: ( (lv_ref_1_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2140:1: (lv_ref_1_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2140:1: (lv_ref_1_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2141:3: lv_ref_1_0= RULE_IDENT
            {
            lv_ref_1_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3454); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2163:2: ( (lv_subscripts1_2_0= rulearray_subscripts ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==28) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2164:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2164:1: (lv_subscripts1_2_0= rulearray_subscripts )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2165:3: lv_subscripts1_2_0= rulearray_subscripts
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscripts1Array_subscriptsParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3480);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2187:3: ( '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )? )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==37) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2187:5: '.' ( (lv_ref1_4_0= RULE_IDENT ) ) ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    {
            	    match(input,37,FOLLOW_37_in_rulecomponent_reference3492); 

            	            createLeafNode(grammarAccess.getComponent_referenceAccess().getFullStopKeyword_3_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2191:1: ( (lv_ref1_4_0= RULE_IDENT ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2192:1: (lv_ref1_4_0= RULE_IDENT )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2192:1: (lv_ref1_4_0= RULE_IDENT )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2193:3: lv_ref1_4_0= RULE_IDENT
            	    {
            	    lv_ref1_4_0=(Token)input.LT(1);
            	    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference3509); 

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

            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2215:2: ( (lv_subscripts_5_0= rulearray_subscripts ) )?
            	    int alt29=2;
            	    int LA29_0 = input.LA(1);

            	    if ( (LA29_0==28) ) {
            	        alt29=1;
            	    }
            	    switch (alt29) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2216:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2216:1: (lv_subscripts_5_0= rulearray_subscripts )
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2217:3: lv_subscripts_5_0= rulearray_subscripts
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getComponent_referenceAccess().getSubscriptsArray_subscriptsParserRuleCall_3_2_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference3535);
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
            	    break loop30;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2247:1: entryRuleoutput_expression_list returns [EObject current=null] : iv_ruleoutput_expression_list= ruleoutput_expression_list EOF ;
    public final EObject entryRuleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleoutput_expression_list = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2248:2: (iv_ruleoutput_expression_list= ruleoutput_expression_list EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2249:2: iv_ruleoutput_expression_list= ruleoutput_expression_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getOutput_expression_listRule(), currentNode); 
            pushFollow(FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3574);
            iv_ruleoutput_expression_list=ruleoutput_expression_list();
            _fsp--;

             current =iv_ruleoutput_expression_list; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleoutput_expression_list3584); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2256:1: ruleoutput_expression_list returns [EObject current=null] : ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) ;
    public final EObject ruleoutput_expression_list() throws RecognitionException {
        EObject current = null;

        EObject lv_epr_1_0 = null;

        EObject lv_Expr_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2261:6: ( ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:1: ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:1: ( () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:2: () ( (lv_epr_1_0= ruleexpression ) )? ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2262:2: ()
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2263:5: 
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2273:2: ( (lv_epr_1_0= ruleexpression ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_UNSIGNED_NUMBER && LA31_0<=RULE_IDENT)||LA31_0==16||LA31_0==23||LA31_0==26||LA31_0==28||LA31_0==31||(LA31_0>=33 && LA31_0<=35)||LA31_0==37||(LA31_0>=44 && LA31_0<=47)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2274:1: (lv_epr_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2274:1: (lv_epr_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2275:3: lv_epr_1_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutput_expression_listAccess().getEprExpressionParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3639);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2297:3: ( ',' ( (lv_Expr_3_0= ruleexpression ) )? )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==36) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2297:5: ',' ( (lv_Expr_3_0= ruleexpression ) )?
            	    {
            	    match(input,36,FOLLOW_36_in_ruleoutput_expression_list3651); 

            	            createLeafNode(grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2301:1: ( (lv_Expr_3_0= ruleexpression ) )?
            	    int alt32=2;
            	    int LA32_0 = input.LA(1);

            	    if ( ((LA32_0>=RULE_UNSIGNED_NUMBER && LA32_0<=RULE_IDENT)||LA32_0==16||LA32_0==23||LA32_0==26||LA32_0==28||LA32_0==31||(LA32_0>=33 && LA32_0<=35)||LA32_0==37||(LA32_0>=44 && LA32_0<=47)) ) {
            	        alt32=1;
            	    }
            	    switch (alt32) {
            	        case 1 :
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2302:1: (lv_Expr_3_0= ruleexpression )
            	            {
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2302:1: (lv_Expr_3_0= ruleexpression )
            	            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2303:3: lv_Expr_3_0= ruleexpression
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getOutput_expression_listAccess().getExprExpressionParserRuleCall_2_1_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleexpression_in_ruleoutput_expression_list3672);
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
            	    break loop33;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2333:1: entryRulearray_subscripts returns [EObject current=null] : iv_rulearray_subscripts= rulearray_subscripts EOF ;
    public final EObject entryRulearray_subscripts() throws RecognitionException {
        EObject current = null;

        EObject iv_rulearray_subscripts = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2334:2: (iv_rulearray_subscripts= rulearray_subscripts EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2335:2: iv_rulearray_subscripts= rulearray_subscripts EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArray_subscriptsRule(), currentNode); 
            pushFollow(FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3711);
            iv_rulearray_subscripts=rulearray_subscripts();
            _fsp--;

             current =iv_rulearray_subscripts; 
            match(input,EOF,FOLLOW_EOF_in_entryRulearray_subscripts3721); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2342:1: rulearray_subscripts returns [EObject current=null] : ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' ) ;
    public final EObject rulearray_subscripts() throws RecognitionException {
        EObject current = null;

        EObject lv_Sub_1_0 = null;

        EObject lv_Subscript_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2347:6: ( ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2348:1: ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2348:1: ( '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2348:3: '[' ( (lv_Sub_1_0= rulesubscript ) ) ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )* ']'
            {
            match(input,28,FOLLOW_28_in_rulearray_subscripts3756); 

                    createLeafNode(grammarAccess.getArray_subscriptsAccess().getLeftSquareBracketKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2352:1: ( (lv_Sub_1_0= rulesubscript ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2353:1: (lv_Sub_1_0= rulesubscript )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2353:1: (lv_Sub_1_0= rulesubscript )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2354:3: lv_Sub_1_0= rulesubscript
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubSubscriptParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3777);
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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2376:2: ( ',' ( (lv_Subscript_3_0= rulesubscript ) ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==36) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2376:4: ',' ( (lv_Subscript_3_0= rulesubscript ) )
            	    {
            	    match(input,36,FOLLOW_36_in_rulearray_subscripts3788); 

            	            createLeafNode(grammarAccess.getArray_subscriptsAccess().getCommaKeyword_2_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2380:1: ( (lv_Subscript_3_0= rulesubscript ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2381:1: (lv_Subscript_3_0= rulesubscript )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2381:1: (lv_Subscript_3_0= rulesubscript )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2382:3: lv_Subscript_3_0= rulesubscript
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubscriptSubscriptParserRuleCall_2_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts3809);
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
            	    break loop34;
                }
            } while (true);

            match(input,30,FOLLOW_30_in_rulearray_subscripts3821); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2416:1: entryRulesubscript returns [EObject current=null] : iv_rulesubscript= rulesubscript EOF ;
    public final EObject entryRulesubscript() throws RecognitionException {
        EObject current = null;

        EObject iv_rulesubscript = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2417:2: (iv_rulesubscript= rulesubscript EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2418:2: iv_rulesubscript= rulesubscript EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSubscriptRule(), currentNode); 
            pushFollow(FOLLOW_rulesubscript_in_entryRulesubscript3857);
            iv_rulesubscript=rulesubscript();
            _fsp--;

             current =iv_rulesubscript; 
            match(input,EOF,FOLLOW_EOF_in_entryRulesubscript3867); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2425:1: rulesubscript returns [EObject current=null] : ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulesubscript() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2430:6: ( ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2431:1: ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2431:1: ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )
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
                    new NoViableAltException("2431:1: ( ( () ':' ) | ( (lv_expr_2_0= ruleexpression ) ) )", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2431:2: ( () ':' )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2431:2: ( () ':' )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2431:3: () ':'
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2431:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2432:5: 
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

                    match(input,17,FOLLOW_17_in_rulesubscript3912); 

                            createLeafNode(grammarAccess.getSubscriptAccess().getColonKeyword_0_1(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2447:6: ( (lv_expr_2_0= ruleexpression ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2447:6: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2448:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2448:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2449:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSubscriptAccess().getExprExpressionParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulesubscript3940);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2479:1: entryRulefunction_arguments returns [EObject current=null] : iv_rulefunction_arguments= rulefunction_arguments EOF ;
    public final EObject entryRulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefunction_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2480:2: (iv_rulefunction_arguments= rulefunction_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2481:2: iv_rulefunction_arguments= rulefunction_arguments EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFunction_argumentsRule(), currentNode); 
            pushFollow(FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments3976);
            iv_rulefunction_arguments=rulefunction_arguments();
            _fsp--;

             current =iv_rulefunction_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_arguments3986); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2488:1: rulefunction_arguments returns [EObject current=null] : ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) ;
    public final EObject rulefunction_arguments() throws RecognitionException {
        EObject current = null;

        EObject lv_ArgExp_1_0 = null;

        EObject lv_Fun_Arg_Expr_2_0 = null;

        EObject lv_Fun_Arg_For_3_0 = null;

        EObject lv_name_arg_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2493:6: ( ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2494:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2494:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( ((LA37_0>=RULE_UNSIGNED_NUMBER && LA37_0<=RULE_BOOL_VAL)||LA37_0==16||LA37_0==23||LA37_0==26||LA37_0==28||LA37_0==31||(LA37_0>=33 && LA37_0<=35)||LA37_0==37||(LA37_0>=44 && LA37_0<=47)) ) {
                alt37=1;
            }
            else if ( (LA37_0==RULE_IDENT) ) {
                int LA37_2 = input.LA(2);

                if ( (LA37_2==14) ) {
                    alt37=2;
                }
                else if ( (LA37_2==EOF||LA37_2==17||(LA37_2>=21 && LA37_2<=22)||(LA37_2>=24 && LA37_2<=28)||LA37_2==32||(LA37_2>=36 && LA37_2<=38)||(LA37_2>=40 && LA37_2<=53)) ) {
                    alt37=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2494:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )", 37, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2494:1: ( ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? ) | ( (lv_name_arg_4_0= rulenamed_arguments ) ) )", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2494:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2494:2: ( () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )? )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2494:3: () ( (lv_ArgExp_1_0= ruleexpression ) ) ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2494:3: ()
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2495:5: 
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2505:2: ( (lv_ArgExp_1_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2506:1: (lv_ArgExp_1_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2506:1: (lv_ArgExp_1_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2507:3: lv_ArgExp_1_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getArgExpExpressionParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefunction_arguments4042);
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

                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2529:2: ( ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) ) | ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) ) )?
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2529:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2529:3: ( (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2530:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2530:1: (lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2531:3: lv_Fun_Arg_Expr_2_0= ruleFun_Arguments_exp
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments4064);
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
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2554:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2554:6: ( (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for ) )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2555:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            {
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2555:1: (lv_Fun_Arg_For_3_0= ruleFun_Arguments_for )
                            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2556:3: lv_Fun_Arg_For_3_0= ruleFun_Arguments_for
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments4091);
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
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2579:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2579:6: ( (lv_name_arg_4_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2580:1: (lv_name_arg_4_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2580:1: (lv_name_arg_4_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2581:3: lv_name_arg_4_0= rulenamed_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getName_argNamed_argumentsParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulefunction_arguments4121);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2611:1: entryRuleFun_Arguments_exp returns [EObject current=null] : iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF ;
    public final EObject entryRuleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_exp = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2612:2: (iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2613:2: iv_ruleFun_Arguments_exp= ruleFun_Arguments_exp EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFun_Arguments_expRule(), currentNode); 
            pushFollow(FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp4157);
            iv_ruleFun_Arguments_exp=ruleFun_Arguments_exp();
            _fsp--;

             current =iv_ruleFun_Arguments_exp; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_exp4167); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2620:1: ruleFun_Arguments_exp returns [EObject current=null] : ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) ;
    public final EObject ruleFun_Arguments_exp() throws RecognitionException {
        EObject current = null;

        EObject lv_Args_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2625:6: ( ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2626:1: ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2626:1: ( ',' ( (lv_Args_1_0= rulefunction_arguments ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2626:3: ',' ( (lv_Args_1_0= rulefunction_arguments ) )
            {
            match(input,36,FOLLOW_36_in_ruleFun_Arguments_exp4202); 

                    createLeafNode(grammarAccess.getFun_Arguments_expAccess().getCommaKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2630:1: ( (lv_Args_1_0= rulefunction_arguments ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2631:1: (lv_Args_1_0= rulefunction_arguments )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2631:1: (lv_Args_1_0= rulefunction_arguments )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2632:3: lv_Args_1_0= rulefunction_arguments
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFun_Arguments_expAccess().getArgsFunction_argumentsParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp4223);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2662:1: entryRuleFun_Arguments_for returns [EObject current=null] : iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF ;
    public final EObject entryRuleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFun_Arguments_for = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2663:2: (iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2664:2: iv_ruleFun_Arguments_for= ruleFun_Arguments_for EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFun_Arguments_forRule(), currentNode); 
            pushFollow(FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for4259);
            iv_ruleFun_Arguments_for=ruleFun_Arguments_for();
            _fsp--;

             current =iv_ruleFun_Arguments_for; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFun_Arguments_for4269); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2671:1: ruleFun_Arguments_for returns [EObject current=null] : ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) ;
    public final EObject ruleFun_Arguments_for() throws RecognitionException {
        EObject current = null;

        EObject lv_For_indices_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2676:6: ( ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2677:1: ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2677:1: ( 'for' ( (lv_For_indices_1_0= rulefor_indices ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2677:3: 'for' ( (lv_For_indices_1_0= rulefor_indices ) )
            {
            match(input,38,FOLLOW_38_in_ruleFun_Arguments_for4304); 

                    createLeafNode(grammarAccess.getFun_Arguments_forAccess().getForKeyword_0(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2681:1: ( (lv_For_indices_1_0= rulefor_indices ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2682:1: (lv_For_indices_1_0= rulefor_indices )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2682:1: (lv_For_indices_1_0= rulefor_indices )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2683:3: lv_For_indices_1_0= rulefor_indices
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFun_Arguments_forAccess().getFor_indicesFor_indicesParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4325);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2713:1: entryRulenamed_arguments returns [EObject current=null] : iv_rulenamed_arguments= rulenamed_arguments EOF ;
    public final EObject entryRulenamed_arguments() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_arguments = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2714:2: (iv_rulenamed_arguments= rulenamed_arguments EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2715:2: iv_rulenamed_arguments= rulenamed_arguments EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamed_argumentsRule(), currentNode); 
            pushFollow(FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4361);
            iv_rulenamed_arguments=rulenamed_arguments();
            _fsp--;

             current =iv_rulenamed_arguments; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_arguments4371); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2722:1: rulenamed_arguments returns [EObject current=null] : (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) ;
    public final EObject rulenamed_arguments() throws RecognitionException {
        EObject current = null;

        EObject this_named_argument_0 = null;

        EObject lv_Named_arguments_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2727:6: ( (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2728:1: (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2728:1: (this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2729:5: this_named_argument_0= rulenamed_argument ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulenamed_argument_in_rulenamed_arguments4418);
            this_named_argument_0=rulenamed_argument();
            _fsp--;

             
                    current = this_named_argument_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2737:1: ( ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==36) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2737:3: ',' ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    {
                    match(input,36,FOLLOW_36_in_rulenamed_arguments4428); 

                            createLeafNode(grammarAccess.getNamed_argumentsAccess().getCommaKeyword_1_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2741:1: ( (lv_Named_arguments_2_0= rulenamed_arguments ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2742:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2742:1: (lv_Named_arguments_2_0= rulenamed_arguments )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2743:3: lv_Named_arguments_2_0= rulenamed_arguments
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_argumentsNamed_argumentsParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulenamed_arguments4449);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2773:1: entryRulenamed_argument returns [EObject current=null] : iv_rulenamed_argument= rulenamed_argument EOF ;
    public final EObject entryRulenamed_argument() throws RecognitionException {
        EObject current = null;

        EObject iv_rulenamed_argument = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2774:2: (iv_rulenamed_argument= rulenamed_argument EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2775:2: iv_rulenamed_argument= rulenamed_argument EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamed_argumentRule(), currentNode); 
            pushFollow(FOLLOW_rulenamed_argument_in_entryRulenamed_argument4487);
            iv_rulenamed_argument=rulenamed_argument();
            _fsp--;

             current =iv_rulenamed_argument; 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_argument4497); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2782:1: rulenamed_argument returns [EObject current=null] : ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) ) ;
    public final EObject rulenamed_argument() throws RecognitionException {
        EObject current = null;

        Token lv_arg_0_0=null;
        EObject lv_expr_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2787:6: ( ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2788:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2788:1: ( ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2788:2: ( (lv_arg_0_0= RULE_IDENT ) ) '=' ( (lv_expr_2_0= ruleexpression ) )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2788:2: ( (lv_arg_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2789:1: (lv_arg_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2789:1: (lv_arg_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2790:3: lv_arg_0_0= RULE_IDENT
            {
            lv_arg_0_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulenamed_argument4539); 

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

            match(input,14,FOLLOW_14_in_rulenamed_argument4554); 

                    createLeafNode(grammarAccess.getNamed_argumentAccess().getEqualsSignKeyword_1(), null); 
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2816:1: ( (lv_expr_2_0= ruleexpression ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2817:1: (lv_expr_2_0= ruleexpression )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2817:1: (lv_expr_2_0= ruleexpression )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2818:3: lv_expr_2_0= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNamed_argumentAccess().getExprExpressionParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulenamed_argument4575);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2848:1: entryRulefor_indices returns [EObject current=null] : iv_rulefor_indices= rulefor_indices EOF ;
    public final EObject entryRulefor_indices() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_indices = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2849:2: (iv_rulefor_indices= rulefor_indices EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2850:2: iv_rulefor_indices= rulefor_indices EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_indicesRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_indices_in_entryRulefor_indices4611);
            iv_rulefor_indices=rulefor_indices();
            _fsp--;

             current =iv_rulefor_indices; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_indices4621); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2857:1: rulefor_indices returns [EObject current=null] : (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) ;
    public final EObject rulefor_indices() throws RecognitionException {
        EObject current = null;

        EObject this_for_index_0 = null;

        EObject lv_For_index_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2862:6: ( (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2863:1: (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2863:1: (this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )* )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2864:5: this_for_index_0= rulefor_index ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4668);
            this_for_index_0=rulefor_index();
            _fsp--;

             
                    current = this_for_index_0; 
                    currentNode = currentNode.getParent();
                
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2872:1: ( ',' ( (lv_For_index_2_0= rulefor_index ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==36) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2872:3: ',' ( (lv_For_index_2_0= rulefor_index ) )
            	    {
            	    match(input,36,FOLLOW_36_in_rulefor_indices4678); 

            	            createLeafNode(grammarAccess.getFor_indicesAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2876:1: ( (lv_For_index_2_0= rulefor_index ) )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2877:1: (lv_For_index_2_0= rulefor_index )
            	    {
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2877:1: (lv_For_index_2_0= rulefor_index )
            	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2878:3: lv_For_index_2_0= rulefor_index
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexFor_indexParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulefor_index_in_rulefor_indices4699);
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
            	    break loop39;
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2908:1: entryRulefor_index returns [EObject current=null] : iv_rulefor_index= rulefor_index EOF ;
    public final EObject entryRulefor_index() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_index = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2909:2: (iv_rulefor_index= rulefor_index EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2910:2: iv_rulefor_index= rulefor_index EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_indexRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_index_in_entryRulefor_index4737);
            iv_rulefor_index=rulefor_index();
            _fsp--;

             current =iv_rulefor_index; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_index4747); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2917:1: rulefor_index returns [EObject current=null] : ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) ;
    public final EObject rulefor_index() throws RecognitionException {
        EObject current = null;

        Token lv_index_0_0=null;
        EObject lv_expr_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2922:6: ( ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2923:1: ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2923:1: ( ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2923:2: ( (lv_index_0_0= RULE_IDENT ) ) ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2923:2: ( (lv_index_0_0= RULE_IDENT ) )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2924:1: (lv_index_0_0= RULE_IDENT )
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2924:1: (lv_index_0_0= RULE_IDENT )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2925:3: lv_index_0_0= RULE_IDENT
            {
            lv_index_0_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulefor_index4789); 

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

            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2947:2: ( 'in' ( (lv_expr_2_0= ruleexpression ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==39) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2947:4: 'in' ( (lv_expr_2_0= ruleexpression ) )
                    {
                    match(input,39,FOLLOW_39_in_rulefor_index4805); 

                            createLeafNode(grammarAccess.getFor_indexAccess().getInKeyword_1_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2951:1: ( (lv_expr_2_0= ruleexpression ) )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2952:1: (lv_expr_2_0= ruleexpression )
                    {
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2952:1: (lv_expr_2_0= ruleexpression )
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2953:3: lv_expr_2_0= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getFor_indexAccess().getExprExpressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulefor_index4826);
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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2983:1: entryRulemul_op_mul returns [String current=null] : iv_rulemul_op_mul= rulemul_op_mul EOF ;
    public final String entryRulemul_op_mul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_mul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2984:2: (iv_rulemul_op_mul= rulemul_op_mul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2985:2: iv_rulemul_op_mul= rulemul_op_mul EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_mulRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4865);
            iv_rulemul_op_mul=rulemul_op_mul();
            _fsp--;

             current =iv_rulemul_op_mul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_mul4876); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2992:1: rulemul_op_mul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '*' ;
    public final AntlrDatatypeRuleToken rulemul_op_mul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2997:6: (kw= '*' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:2999:2: kw= '*'
            {
            kw=(Token)input.LT(1);
            match(input,40,FOLLOW_40_in_rulemul_op_mul4913); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3012:1: entryRulemul_op_div returns [String current=null] : iv_rulemul_op_div= rulemul_op_div EOF ;
    public final String entryRulemul_op_div() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_div = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3013:2: (iv_rulemul_op_div= rulemul_op_div EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3014:2: iv_rulemul_op_div= rulemul_op_div EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_divRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_div_in_entryRulemul_op_div4953);
            iv_rulemul_op_div=rulemul_op_div();
            _fsp--;

             current =iv_rulemul_op_div.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_div4964); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3021:1: rulemul_op_div returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '/' ;
    public final AntlrDatatypeRuleToken rulemul_op_div() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3026:6: (kw= '/' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3028:2: kw= '/'
            {
            kw=(Token)input.LT(1);
            match(input,41,FOLLOW_41_in_rulemul_op_div5001); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3041:1: entryRulemul_op_dotmul returns [String current=null] : iv_rulemul_op_dotmul= rulemul_op_dotmul EOF ;
    public final String entryRulemul_op_dotmul() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotmul = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3042:2: (iv_rulemul_op_dotmul= rulemul_op_dotmul EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3043:2: iv_rulemul_op_dotmul= rulemul_op_dotmul EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_dotmulRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul5041);
            iv_rulemul_op_dotmul=rulemul_op_dotmul();
            _fsp--;

             current =iv_rulemul_op_dotmul.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotmul5052); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3050:1: rulemul_op_dotmul returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.*' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotmul() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3055:6: (kw= '.*' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3057:2: kw= '.*'
            {
            kw=(Token)input.LT(1);
            match(input,42,FOLLOW_42_in_rulemul_op_dotmul5089); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3070:1: entryRulemul_op_dotdiv returns [String current=null] : iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF ;
    public final String entryRulemul_op_dotdiv() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulemul_op_dotdiv = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3071:2: (iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3072:2: iv_rulemul_op_dotdiv= rulemul_op_dotdiv EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMul_op_dotdivRule(), currentNode); 
            pushFollow(FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv5129);
            iv_rulemul_op_dotdiv=rulemul_op_dotdiv();
            _fsp--;

             current =iv_rulemul_op_dotdiv.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulemul_op_dotdiv5140); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3079:1: rulemul_op_dotdiv returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= './' ;
    public final AntlrDatatypeRuleToken rulemul_op_dotdiv() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3084:6: (kw= './' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3086:2: kw= './'
            {
            kw=(Token)input.LT(1);
            match(input,43,FOLLOW_43_in_rulemul_op_dotdiv5177); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3099:1: entryRuleadd_op_plus returns [String current=null] : iv_ruleadd_op_plus= ruleadd_op_plus EOF ;
    public final String entryRuleadd_op_plus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_plus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3100:2: (iv_ruleadd_op_plus= ruleadd_op_plus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3101:2: iv_ruleadd_op_plus= ruleadd_op_plus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_plusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus5217);
            iv_ruleadd_op_plus=ruleadd_op_plus();
            _fsp--;

             current =iv_ruleadd_op_plus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_plus5228); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3108:1: ruleadd_op_plus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_plus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3113:6: (kw= '+' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3115:2: kw= '+'
            {
            kw=(Token)input.LT(1);
            match(input,44,FOLLOW_44_in_ruleadd_op_plus5265); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3128:1: entryRuleadd_op_minus returns [String current=null] : iv_ruleadd_op_minus= ruleadd_op_minus EOF ;
    public final String entryRuleadd_op_minus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_minus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3129:2: (iv_ruleadd_op_minus= ruleadd_op_minus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3130:2: iv_ruleadd_op_minus= ruleadd_op_minus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_minusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5305);
            iv_ruleadd_op_minus=ruleadd_op_minus();
            _fsp--;

             current =iv_ruleadd_op_minus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_minus5316); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3137:1: ruleadd_op_minus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_minus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3142:6: (kw= '-' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3144:2: kw= '-'
            {
            kw=(Token)input.LT(1);
            match(input,45,FOLLOW_45_in_ruleadd_op_minus5353); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3157:1: entryRuleadd_op_dotplus returns [String current=null] : iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF ;
    public final String entryRuleadd_op_dotplus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotplus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3158:2: (iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3159:2: iv_ruleadd_op_dotplus= ruleadd_op_dotplus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_dotplusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5393);
            iv_ruleadd_op_dotplus=ruleadd_op_dotplus();
            _fsp--;

             current =iv_ruleadd_op_dotplus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotplus5404); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3166:1: ruleadd_op_dotplus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.+' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotplus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3171:6: (kw= '.+' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3173:2: kw= '.+'
            {
            kw=(Token)input.LT(1);
            match(input,46,FOLLOW_46_in_ruleadd_op_dotplus5441); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3186:1: entryRuleadd_op_dotminus returns [String current=null] : iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF ;
    public final String entryRuleadd_op_dotminus() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleadd_op_dotminus = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3187:2: (iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3188:2: iv_ruleadd_op_dotminus= ruleadd_op_dotminus EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAdd_op_dotminusRule(), currentNode); 
            pushFollow(FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5481);
            iv_ruleadd_op_dotminus=ruleadd_op_dotminus();
            _fsp--;

             current =iv_ruleadd_op_dotminus.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleadd_op_dotminus5492); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3195:1: ruleadd_op_dotminus returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '.-' ;
    public final AntlrDatatypeRuleToken ruleadd_op_dotminus() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3200:6: (kw= '.-' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3202:2: kw= '.-'
            {
            kw=(Token)input.LT(1);
            match(input,47,FOLLOW_47_in_ruleadd_op_dotminus5529); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3215:1: entryRulerel_op_Less_then returns [String current=null] : iv_rulerel_op_Less_then= rulerel_op_Less_then EOF ;
    public final String entryRulerel_op_Less_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3216:2: (iv_rulerel_op_Less_then= rulerel_op_Less_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3217:2: iv_rulerel_op_Less_then= rulerel_op_Less_then EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_Less_thenRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5569);
            iv_rulerel_op_Less_then=rulerel_op_Less_then();
            _fsp--;

             current =iv_rulerel_op_Less_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_then5580); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3224:1: rulerel_op_Less_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3229:6: (kw= '<' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3231:2: kw= '<'
            {
            kw=(Token)input.LT(1);
            match(input,48,FOLLOW_48_in_rulerel_op_Less_then5617); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3244:1: entryRulerel_op_Less_equal returns [String current=null] : iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF ;
    public final String entryRulerel_op_Less_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Less_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3245:2: (iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3246:2: iv_rulerel_op_Less_equal= rulerel_op_Less_equal EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_Less_equalRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5657);
            iv_rulerel_op_Less_equal=rulerel_op_Less_equal();
            _fsp--;

             current =iv_rulerel_op_Less_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Less_equal5668); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3253:1: rulerel_op_Less_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<=' ;
    public final AntlrDatatypeRuleToken rulerel_op_Less_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3258:6: (kw= '<=' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3260:2: kw= '<='
            {
            kw=(Token)input.LT(1);
            match(input,49,FOLLOW_49_in_rulerel_op_Less_equal5705); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3273:1: entryRulerel_op_greater_then returns [String current=null] : iv_rulerel_op_greater_then= rulerel_op_greater_then EOF ;
    public final String entryRulerel_op_greater_then() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_then = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3274:2: (iv_rulerel_op_greater_then= rulerel_op_greater_then EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3275:2: iv_rulerel_op_greater_then= rulerel_op_greater_then EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_greater_thenRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5745);
            iv_rulerel_op_greater_then=rulerel_op_greater_then();
            _fsp--;

             current =iv_rulerel_op_greater_then.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_then5756); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3282:1: rulerel_op_greater_then returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_then() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3287:6: (kw= '>' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3289:2: kw= '>'
            {
            kw=(Token)input.LT(1);
            match(input,50,FOLLOW_50_in_rulerel_op_greater_then5793); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3302:1: entryRulerel_op_greater_equal returns [String current=null] : iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF ;
    public final String entryRulerel_op_greater_equal() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_greater_equal = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3303:2: (iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3304:2: iv_rulerel_op_greater_equal= rulerel_op_greater_equal EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_greater_equalRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5833);
            iv_rulerel_op_greater_equal=rulerel_op_greater_equal();
            _fsp--;

             current =iv_rulerel_op_greater_equal.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_greater_equal5844); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3311:1: rulerel_op_greater_equal returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '>=' ;
    public final AntlrDatatypeRuleToken rulerel_op_greater_equal() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3316:6: (kw= '>=' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3318:2: kw= '>='
            {
            kw=(Token)input.LT(1);
            match(input,51,FOLLOW_51_in_rulerel_op_greater_equal5881); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3331:1: entryRulerel_op_assignment returns [String current=null] : iv_rulerel_op_assignment= rulerel_op_assignment EOF ;
    public final String entryRulerel_op_assignment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_assignment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3332:2: (iv_rulerel_op_assignment= rulerel_op_assignment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3333:2: iv_rulerel_op_assignment= rulerel_op_assignment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_assignmentRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment5921);
            iv_rulerel_op_assignment=rulerel_op_assignment();
            _fsp--;

             current =iv_rulerel_op_assignment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_assignment5932); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3340:1: rulerel_op_assignment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '==' ;
    public final AntlrDatatypeRuleToken rulerel_op_assignment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3345:6: (kw= '==' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3347:2: kw= '=='
            {
            kw=(Token)input.LT(1);
            match(input,52,FOLLOW_52_in_rulerel_op_assignment5969); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3360:1: entryRulerel_op_Oper returns [String current=null] : iv_rulerel_op_Oper= rulerel_op_Oper EOF ;
    public final String entryRulerel_op_Oper() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerel_op_Oper = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3361:2: (iv_rulerel_op_Oper= rulerel_op_Oper EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3362:2: iv_rulerel_op_Oper= rulerel_op_Oper EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRel_op_OperRule(), currentNode); 
            pushFollow(FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper6009);
            iv_rulerel_op_Oper=rulerel_op_Oper();
            _fsp--;

             current =iv_rulerel_op_Oper.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerel_op_Oper6020); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3369:1: rulerel_op_Oper returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '<>' ;
    public final AntlrDatatypeRuleToken rulerel_op_Oper() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3374:6: (kw= '<>' )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3376:2: kw= '<>'
            {
            kw=(Token)input.LT(1);
            match(input,53,FOLLOW_53_in_rulerel_op_Oper6057); 

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


    // $ANTLR start entryRulecomment
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3389:1: entryRulecomment returns [String current=null] : iv_rulecomment= rulecomment EOF ;
    public final String entryRulecomment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulecomment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3390:2: (iv_rulecomment= rulecomment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3391:2: iv_rulecomment= rulecomment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCommentRule(), currentNode); 
            pushFollow(FOLLOW_rulecomment_in_entryRulecomment6097);
            iv_rulecomment=rulecomment();
            _fsp--;

             current =iv_rulecomment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomment6108); 

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
    // $ANTLR end entryRulecomment


    // $ANTLR start rulecomment
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3398:1: rulecomment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_string_comment_0= rulestring_comment ;
    public final AntlrDatatypeRuleToken rulecomment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_string_comment_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3403:6: (this_string_comment_0= rulestring_comment )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3405:5: this_string_comment_0= rulestring_comment
            {
             
                    currentNode=createCompositeNode(grammarAccess.getCommentAccess().getString_commentParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_rulestring_comment_in_rulecomment6154);
            this_string_comment_0=rulestring_comment();
            _fsp--;


            		current.merge(this_string_comment_0);
                
             
                    currentNode = currentNode.getParent();
                

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
    // $ANTLR end rulecomment


    // $ANTLR start entryRulestring_comment
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3423:1: entryRulestring_comment returns [String current=null] : iv_rulestring_comment= rulestring_comment EOF ;
    public final String entryRulestring_comment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulestring_comment = null;


        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3424:2: (iv_rulestring_comment= rulestring_comment EOF )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3425:2: iv_rulestring_comment= rulestring_comment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getString_commentRule(), currentNode); 
            pushFollow(FOLLOW_rulestring_comment_in_entryRulestring_comment6199);
            iv_rulestring_comment=rulestring_comment();
            _fsp--;

             current =iv_rulestring_comment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulestring_comment6210); 

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
    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3432:1: rulestring_comment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? ;
    public final AntlrDatatypeRuleToken rulestring_comment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token kw=null;
        Token this_STRING_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3437:6: ( (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )? )
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3438:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            {
            // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3438:1: (this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )* )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==RULE_STRING) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3438:6: this_STRING_0= RULE_STRING (kw= '+' this_STRING_2= RULE_STRING )*
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment6250); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_0(), null); 
                        
                    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3445:1: (kw= '+' this_STRING_2= RULE_STRING )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==44) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // ../org.openmodelica.modelicaml.editor.xtext.declaration/src-gen/org/openmodelica/modelicaml/editor/xtext/declaration/parser/antlr/internal/InternalDeclaration.g:3446:2: kw= '+' this_STRING_2= RULE_STRING
                    	    {
                    	    kw=(Token)input.LT(1);
                    	    match(input,44,FOLLOW_44_in_rulestring_comment6269); 

                    	            current.merge(kw);
                    	            createLeafNode(grammarAccess.getString_commentAccess().getPlusSignKeyword_1_0(), null); 
                    	        
                    	    this_STRING_2=(Token)input.LT(1);
                    	    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment6284); 

                    	    		current.merge(this_STRING_2);
                    	        
                    	     
                    	        createLeafNode(grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_1_1(), null); 
                    	        

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


    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA22_eotS =
        "\21\uffff";
    static final String DFA22_eofS =
        "\6\uffff\1\15\11\uffff\1\15";
    static final String DFA22_minS =
        "\1\4\4\uffff\1\10\1\6\7\uffff\1\10\1\uffff\1\6";
    static final String DFA22_maxS =
        "\1\45\4\uffff\1\10\1\65\7\uffff\1\10\1\uffff\1\65";
    static final String DFA22_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\6\1\7\1\11\1\12\1\13\1\14\1\10"+
        "\1\uffff\1\5\1\uffff";
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
            "\1\15\11\uffff\7\15\1\uffff\2\15\1\17\4\15\1\uffff\1\15\3\uffff"+
            "\1\15\1\16\1\15\1\uffff\16\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\20",
            "",
            "\1\15\11\uffff\7\15\1\uffff\2\15\1\17\4\15\1\uffff\1\15\3\uffff"+
            "\1\15\1\16\1\15\1\uffff\16\15"
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
            return "1446:1: ( ( (lv_num_0_0= RULE_UNSIGNED_NUMBER ) ) | ( (lv_int_1_0= RULE_INT ) ) | ( (lv_str_2_0= RULE_STRING ) ) | ( (lv_Bool_3_0= RULE_BOOL_VAL ) ) | this_name_Function_4= rulename_Function | this_initial_ref_5= ruleinitial_ref | this_ExprDer_6= ruleExprDer | ( (lv_Component_reference_7_0= rulecomponent_reference ) ) | ( '(' ( (lv_output_expr_list_9_0= ruleoutput_expression_list ) ) ')' ) | ( '[' ( (lv_Expre_list_12_0= ruleexpression_list ) ) ( ';' ( (lv_Expression_list_14_0= ruleexpression_list ) ) )* ']' ) | ( '{' ( (lv_f_arguments_17_0= rulefunction_arguments ) ) '}' ) | ( (lv_End_19_0= 'end' ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_rulecomponent_declaration_in_entryRulecomponent_declaration75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_declaration85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemodification_in_rulecomponent_declaration131 = new BitSet(new long[]{0x0000000000010042L});
    public static final BitSet FOLLOW_ruleconditional_attribute_in_rulecomponent_declaration152 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_rulecomment_in_rulecomponent_declaration169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemodification_in_entryRulemodification205 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemodification215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rulemodification251 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulemodification272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rulemodification290 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulemodification311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_attribute_in_entryRuleconditional_attribute348 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_attribute358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleconditional_attribute393 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_attribute414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_entryRuleexpression450 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleexpression506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_ruleexpression534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_entryRulesimple_expression569 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesimple_expression579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression625 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_rulesimple_expression636 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression657 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_rulesimple_expression668 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expression689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_expr_in_entryRuleconditional_expr729 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_expr739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleconditional_expr774 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr795 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleconditional_expr805 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr826 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_19_in_ruleconditional_expr837 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr858 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleconditional_expr868 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr889 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_20_in_ruleconditional_expr902 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_expr923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_entryRulelogical_expression960 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_expression970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression1017 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_rulelogical_expression1027 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression1048 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_rulelogical_term_in_entryRulelogical_term1086 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_term1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term1143 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_22_in_rulelogical_term1153 = new BitSet(new long[]{0x0000F02E948001F0L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term1174 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_entryRulelogical_factor1212 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_factor1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rulelogical_factor1258 = new BitSet(new long[]{0x0000F02E940001F0L});
    public static final BitSet FOLLOW_rulerelation_in_rulelogical_factor1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerelation_in_entryRulerelation1317 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerelation1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1374 = new BitSet(new long[]{0x003F000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_rulerelation1397 = new BitSet(new long[]{0x0000F02E940001F0L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_rulerelation1416 = new BitSet(new long[]{0x0000F02E940001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_rulerelation1435 = new BitSet(new long[]{0x0000F02E940001F0L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_rulerelation1454 = new BitSet(new long[]{0x0000F02E940001F0L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_rulerelation1473 = new BitSet(new long[]{0x0000F02E940001F0L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_rulerelation1492 = new BitSet(new long[]{0x0000F02E940001F0L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression1554 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearithmetic_expression1564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1612 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1631 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1650 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1669 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1694 = new BitSet(new long[]{0x0000F00000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_rulearithmetic_expression1718 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_rulearithmetic_expression1737 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_rulearithmetic_expression1756 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_rulearithmetic_expression1775 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression1799 = new BitSet(new long[]{0x0000F00000000002L});
    public static final BitSet FOLLOW_ruleterm_in_entryRuleterm1837 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleterm1847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm1894 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_ruleterm1917 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_rulemul_op_div_in_ruleterm1936 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_ruleterm1955 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_ruleterm1974 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm1998 = new BitSet(new long[]{0x00000F0000000002L});
    public static final BitSet FOLLOW_rulefactor_in_entryRulefactor2036 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefactor2046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor2093 = new BitSet(new long[]{0x0000000003000002L});
    public static final BitSet FOLLOW_24_in_rulefactor2104 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_25_in_rulefactor2120 = new BitSet(new long[]{0x0000002E940001F0L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_entryRuleprimary2180 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleprimary2190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_NUMBER_in_ruleprimary2232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleprimary2260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleprimary2288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOL_VAL_in_ruleprimary2316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_ruleprimary2349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_ruleprimary2376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_ruleprimary2403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleprimary2429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleprimary2446 = new BitSet(new long[]{0x0000F03E9C8101F0L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_ruleprimary2467 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleprimary2477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleprimary2495 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2516 = new BitSet(new long[]{0x0000000060000000L});
    public static final BitSet FOLLOW_29_in_ruleprimary2527 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary2548 = new BitSet(new long[]{0x0000000060000000L});
    public static final BitSet FOLLOW_30_in_ruleprimary2560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleprimary2578 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleprimary2599 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleprimary2609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleprimary2634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_Function_in_entryRulename_Function2683 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_Function2693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_in_rulename_Function2740 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_rulename_Function2760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_ref_in_entryRuleinitial_ref2796 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleinitial_ref2806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleinitial_ref2841 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleinitial_ref2863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExprDer_in_entryRuleExprDer2898 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExprDer2908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleExprDer2943 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_ruleExprDer2964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_call_args_in_entryRulefunction_call_args3000 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_call_args3010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulefunction_call_args3054 = new BitSet(new long[]{0x0000F02E9C8101F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_rulefunction_call_args3075 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_rulefunction_call_args3086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list_in_entryRuleexpression_list3122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression_list3132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list3178 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_ruleexpression_list3189 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list3210 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_rulename_in_entryRulename3248 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename3258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rulename3294 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3313 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_rulename3329 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename3346 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference3389 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_reference3399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rulecomponent_reference3435 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3454 = new BitSet(new long[]{0x0000002010000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3480 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_rulecomponent_reference3492 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference3509 = new BitSet(new long[]{0x0000002010000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference3535 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_ruleoutput_expression_list_in_entryRuleoutput_expression_list3574 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleoutput_expression_list3584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3639 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_ruleoutput_expression_list3651 = new BitSet(new long[]{0x0000F03E948101F2L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleoutput_expression_list3672 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts3711 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearray_subscripts3721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rulearray_subscripts3756 = new BitSet(new long[]{0x0000F02E948301F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3777 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_36_in_rulearray_subscripts3788 = new BitSet(new long[]{0x0000F02E948301F0L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts3809 = new BitSet(new long[]{0x0000001040000000L});
    public static final BitSet FOLLOW_30_in_rulearray_subscripts3821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesubscript_in_entryRulesubscript3857 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesubscript3867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rulesubscript3912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulesubscript3940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments3976 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_arguments3986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefunction_arguments4042 = new BitSet(new long[]{0x0000005000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_rulefunction_arguments4064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_rulefunction_arguments4091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulefunction_arguments4121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_exp_in_entryRuleFun_Arguments_exp4157 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_exp4167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleFun_Arguments_exp4202 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_ruleFun_Arguments_exp4223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFun_Arguments_for_in_entryRuleFun_Arguments_for4259 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFun_Arguments_for4269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleFun_Arguments_for4304 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_indices_in_ruleFun_Arguments_for4325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments4361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_arguments4371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_rulenamed_arguments4418 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_rulenamed_arguments4428 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulenamed_arguments4449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_entryRulenamed_argument4487 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_argument4497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulenamed_argument4539 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_rulenamed_argument4554 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulenamed_argument4575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_indices_in_entryRulefor_indices4611 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_indices4621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4668 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_rulefor_indices4678 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices4699 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_entryRulefor_index4737 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_index4747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulefor_index4789 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39_in_rulefor_index4805 = new BitSet(new long[]{0x0000F02E948101F0L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_index4826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_mul_in_entryRulemul_op_mul4865 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_mul4876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_rulemul_op_mul4913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_div_in_entryRulemul_op_div4953 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_div4964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_rulemul_op_div5001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotmul_in_entryRulemul_op_dotmul5041 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotmul5052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_rulemul_op_dotmul5089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemul_op_dotdiv_in_entryRulemul_op_dotdiv5129 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemul_op_dotdiv5140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_rulemul_op_dotdiv5177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_plus_in_entryRuleadd_op_plus5217 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_plus5228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleadd_op_plus5265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_minus_in_entryRuleadd_op_minus5305 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_minus5316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleadd_op_minus5353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotplus_in_entryRuleadd_op_dotplus5393 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotplus5404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleadd_op_dotplus5441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleadd_op_dotminus_in_entryRuleadd_op_dotminus5481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleadd_op_dotminus5492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleadd_op_dotminus5529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_then_in_entryRulerel_op_Less_then5569 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_then5580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_rulerel_op_Less_then5617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Less_equal_in_entryRulerel_op_Less_equal5657 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Less_equal5668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rulerel_op_Less_equal5705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_then_in_entryRulerel_op_greater_then5745 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_then5756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_rulerel_op_greater_then5793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_greater_equal_in_entryRulerel_op_greater_equal5833 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_greater_equal5844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_rulerel_op_greater_equal5881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_assignment_in_entryRulerel_op_assignment5921 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_assignment5932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_rulerel_op_assignment5969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerel_op_Oper_in_entryRulerel_op_Oper6009 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerel_op_Oper6020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_rulerel_op_Oper6057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomment_in_entryRulecomment6097 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomment6108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_rulecomment6154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_entryRulestring_comment6199 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulestring_comment6210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment6250 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_44_in_rulestring_comment6269 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment6284 = new BitSet(new long[]{0x0000100000000002L});

}