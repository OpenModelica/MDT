package org.modelica.xtext.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.modelica.xtext.services.ModelicaBaseGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalModelicaBaseParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_SEMICOLON", "RULE_IDENT", "RULE_EQUALS", "RULE_LPAR", "RULE_COMMA", "RULE_COLON", "RULE_RPAR", "RULE_STRING", "RULE_ASSIGN", "RULE_DOT", "RULE_LESS", "RULE_LESSEQ", "RULE_GREATER", "RULE_GREATEREQ", "RULE_EQEQ", "RULE_LESSGT", "RULE_PLUS", "RULE_MINUS", "RULE_PLUS_EW", "RULE_MINUS_EW", "RULE_STAR", "RULE_SLASH", "RULE_STAR_EW", "RULE_SLASH_EW", "RULE_POWER", "RULE_POWER_EW", "RULE_UNSIGNED_INTEGER", "RULE_UNSIGNED_REAL", "RULE_LBRACK", "RULE_RBRACK", "RULE_LBRACE", "RULE_RBRACE", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'within'", "'final'", "'encapsulated'", "'partial'", "'expandable'", "'extends'", "'end'", "'function'", "'der'", "'overload'", "'enumeration'", "'external'", "'public'", "'protected'", "'redeclare'", "'inner'", "'outer'", "'replaceable'", "'import'", "'constrainedby'", "'flow'", "'stream'", "'discrete'", "'parameter'", "'constant'", "'input'", "'output'", "'if'", "'each'", "'equation'", "'algorithm'", "'initial'", "'break'", "'return'", "'then'", "'else'", "'for'", "'loop'", "'while'", "'when'", "'elsewhen'", "'elseif'", "'connect'", "'in'", "'or'", "'and'", "'not'", "'false'", "'true'", "'annotation'", "'class'", "'model'", "'record'", "'block'", "'connector'", "'type'", "'package'"
    };
    public static final int RULE_MINUS_EW=23;
    public static final int RULE_ID=36;
    public static final int RULE_GREATER=16;
    public static final int RULE_SLASH_EW=27;
    public static final int RULE_RBRACE=35;
    public static final int RULE_LPAR=7;
    public static final int RULE_RBRACK=33;
    public static final int RULE_PLUS_EW=22;
    public static final int RULE_SEMICOLON=4;
    public static final int RULE_ANY_OTHER=41;
    public static final int RULE_STAR_EW=26;
    public static final int RULE_POWER_EW=29;
    public static final int RULE_PLUS=20;
    public static final int RULE_COMMA=8;
    public static final int RULE_SL_COMMENT=39;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=38;
    public static final int RULE_ASSIGN=12;
    public static final int RULE_MINUS=21;
    public static final int RULE_COLON=9;
    public static final int RULE_LESSEQ=15;
    public static final int RULE_STRING=11;
    public static final int RULE_DOT=13;
    public static final int RULE_UNSIGNED_INTEGER=30;
    public static final int RULE_STAR=24;
    public static final int RULE_LESSGT=19;
    public static final int RULE_RPAR=10;
    public static final int RULE_IDENT=5;
    public static final int RULE_EQEQ=18;
    public static final int RULE_LBRACK=32;
    public static final int RULE_INT=37;
    public static final int RULE_LESS=14;
    public static final int RULE_LBRACE=34;
    public static final int RULE_EQUALS=6;
    public static final int RULE_SLASH=25;
    public static final int RULE_POWER=28;
    public static final int RULE_WS=40;
    public static final int RULE_UNSIGNED_REAL=31;
    public static final int RULE_GREATEREQ=17;

        public InternalModelicaBaseParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g"; }


     
     	private ModelicaBaseGrammarAccess grammarAccess;
     	
        public InternalModelicaBaseParser(TokenStream input, IAstFactory factory, ModelicaBaseGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "StorageUnit";	
       	} 



    // $ANTLR start entryRuleStorageUnit
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:73:1: entryRuleStorageUnit returns [EObject current=null] : iv_ruleStorageUnit= ruleStorageUnit EOF ;
    public final EObject entryRuleStorageUnit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStorageUnit = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:73:53: (iv_ruleStorageUnit= ruleStorageUnit EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:74:2: iv_ruleStorageUnit= ruleStorageUnit EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStorageUnitRule(), currentNode); 
            pushFollow(FOLLOW_ruleStorageUnit_in_entryRuleStorageUnit73);
            iv_ruleStorageUnit=ruleStorageUnit();
            _fsp--;

             current =iv_ruleStorageUnit; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStorageUnit83); 

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
    // $ANTLR end entryRuleStorageUnit


    // $ANTLR start ruleStorageUnit
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:81:1: ruleStorageUnit returns [EObject current=null] : ( ( 'within' ( rulename_path )? RULE_SEMICOLON )? ( ( 'final' )? (lv_classDefinition_4= ruleClassDefinition ) ';' )* ) ;
    public final EObject ruleStorageUnit() throws RecognitionException {
        EObject current = null;

        EObject lv_classDefinition_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:86:6: ( ( ( 'within' ( rulename_path )? RULE_SEMICOLON )? ( ( 'final' )? (lv_classDefinition_4= ruleClassDefinition ) ';' )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:87:1: ( ( 'within' ( rulename_path )? RULE_SEMICOLON )? ( ( 'final' )? (lv_classDefinition_4= ruleClassDefinition ) ';' )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:87:1: ( ( 'within' ( rulename_path )? RULE_SEMICOLON )? ( ( 'final' )? (lv_classDefinition_4= ruleClassDefinition ) ';' )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:87:2: ( 'within' ( rulename_path )? RULE_SEMICOLON )? ( ( 'final' )? (lv_classDefinition_4= ruleClassDefinition ) ';' )*
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:87:2: ( 'within' ( rulename_path )? RULE_SEMICOLON )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==42) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:87:3: 'within' ( rulename_path )? RULE_SEMICOLON
                    {
                    match(input,42,FOLLOW_42_in_ruleStorageUnit118); 

                            createLeafNode(grammarAccess.getStorageUnitAccess().getWithinKeyword_0_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:91:1: ( rulename_path )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==RULE_IDENT) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:91:2: rulename_path
                            {
                            pushFollow(FOLLOW_rulename_path_in_ruleStorageUnit128);
                            rulename_path();
                            _fsp--;


                            }
                            break;

                    }

                    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleStorageUnit131); 
                     
                        createLeafNode(grammarAccess.getStorageUnitAccess().getSEMICOLONTerminalRuleCall_0_2(), null); 
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:95:3: ( ( 'final' )? (lv_classDefinition_4= ruleClassDefinition ) ';' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=43 && LA4_0<=46)||LA4_0==49||(LA4_0>=92 && LA4_0<=98)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:95:4: ( 'final' )? (lv_classDefinition_4= ruleClassDefinition ) ';'
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:95:4: ( 'final' )?
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==43) ) {
            	        alt3=1;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:95:5: 'final'
            	            {
            	            match(input,43,FOLLOW_43_in_ruleStorageUnit143); 

            	                    createLeafNode(grammarAccess.getStorageUnitAccess().getFinalKeyword_1_0(), null); 
            	                

            	            }
            	            break;

            	    }

            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:99:3: (lv_classDefinition_4= ruleClassDefinition )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:102:6: lv_classDefinition_4= ruleClassDefinition
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getStorageUnitAccess().getClassDefinitionClassDefinitionParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleClassDefinition_in_ruleStorageUnit179);
            	    lv_classDefinition_4=ruleClassDefinition();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getStorageUnitRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "classDefinition", lv_classDefinition_4, "ClassDefinition", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleStorageUnit192); 

            	            createLeafNode(grammarAccess.getStorageUnitAccess().getSemicolonKeyword_1_2(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop4;
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
    // $ANTLR end ruleStorageUnit


    // $ANTLR start entryRuleClassDefinition
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:133:1: entryRuleClassDefinition returns [EObject current=null] : iv_ruleClassDefinition= ruleClassDefinition EOF ;
    public final EObject entryRuleClassDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassDefinition = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:133:57: (iv_ruleClassDefinition= ruleClassDefinition EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:134:2: iv_ruleClassDefinition= ruleClassDefinition EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClassDefinitionRule(), currentNode); 
            pushFollow(FOLLOW_ruleClassDefinition_in_entryRuleClassDefinition229);
            iv_ruleClassDefinition=ruleClassDefinition();
            _fsp--;

             current =iv_ruleClassDefinition; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassDefinition239); 

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
    // $ANTLR end entryRuleClassDefinition


    // $ANTLR start ruleClassDefinition
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:141:1: ruleClassDefinition returns [EObject current=null] : ( (lv_encapsulated_0= 'encapsulated' )? (lv_partial_1= 'partial' )? (lv_expandable_2= 'expandable' )? (lv_classType_3= ruleClassType ) ( ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? ) | ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? ) ) (lv_elemList_10= ruleElementList ) ( (lv_elemList_11= ruleElementList2 ) | (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause ) | (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause ) | (lv_equationClauses_14= ruleequation_rule_clause ) | (lv_algorithmClauses_15= rulealgorithm_clause ) )* (lv_extClause_16= ruleexternal_clause )? 'end' RULE_IDENT ) ;
    public final EObject ruleClassDefinition() throws RecognitionException {
        EObject current = null;

        Token lv_encapsulated_0=null;
        Token lv_partial_1=null;
        Token lv_expandable_2=null;
        Token lv_name_4=null;
        Enumerator lv_classType_3 = null;

        AntlrDatatypeRuleToken lv_comment_5 = null;

        EObject lv_modification_8 = null;

        EObject lv_elemList_10 = null;

        EObject lv_elemList_11 = null;

        EObject lv_initialEquationClauses_12 = null;

        EObject lv_initialAlgorithmClauses_13 = null;

        EObject lv_equationClauses_14 = null;

        EObject lv_algorithmClauses_15 = null;

        EObject lv_extClause_16 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:146:6: ( ( (lv_encapsulated_0= 'encapsulated' )? (lv_partial_1= 'partial' )? (lv_expandable_2= 'expandable' )? (lv_classType_3= ruleClassType ) ( ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? ) | ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? ) ) (lv_elemList_10= ruleElementList ) ( (lv_elemList_11= ruleElementList2 ) | (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause ) | (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause ) | (lv_equationClauses_14= ruleequation_rule_clause ) | (lv_algorithmClauses_15= rulealgorithm_clause ) )* (lv_extClause_16= ruleexternal_clause )? 'end' RULE_IDENT ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:147:1: ( (lv_encapsulated_0= 'encapsulated' )? (lv_partial_1= 'partial' )? (lv_expandable_2= 'expandable' )? (lv_classType_3= ruleClassType ) ( ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? ) | ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? ) ) (lv_elemList_10= ruleElementList ) ( (lv_elemList_11= ruleElementList2 ) | (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause ) | (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause ) | (lv_equationClauses_14= ruleequation_rule_clause ) | (lv_algorithmClauses_15= rulealgorithm_clause ) )* (lv_extClause_16= ruleexternal_clause )? 'end' RULE_IDENT )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:147:1: ( (lv_encapsulated_0= 'encapsulated' )? (lv_partial_1= 'partial' )? (lv_expandable_2= 'expandable' )? (lv_classType_3= ruleClassType ) ( ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? ) | ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? ) ) (lv_elemList_10= ruleElementList ) ( (lv_elemList_11= ruleElementList2 ) | (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause ) | (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause ) | (lv_equationClauses_14= ruleequation_rule_clause ) | (lv_algorithmClauses_15= rulealgorithm_clause ) )* (lv_extClause_16= ruleexternal_clause )? 'end' RULE_IDENT )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:147:2: (lv_encapsulated_0= 'encapsulated' )? (lv_partial_1= 'partial' )? (lv_expandable_2= 'expandable' )? (lv_classType_3= ruleClassType ) ( ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? ) | ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? ) ) (lv_elemList_10= ruleElementList ) ( (lv_elemList_11= ruleElementList2 ) | (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause ) | (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause ) | (lv_equationClauses_14= ruleequation_rule_clause ) | (lv_algorithmClauses_15= rulealgorithm_clause ) )* (lv_extClause_16= ruleexternal_clause )? 'end' RULE_IDENT
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:147:2: (lv_encapsulated_0= 'encapsulated' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==44) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:149:6: lv_encapsulated_0= 'encapsulated'
                    {
                    lv_encapsulated_0=(Token)input.LT(1);
                    match(input,44,FOLLOW_44_in_ruleClassDefinition285); 

                            createLeafNode(grammarAccess.getClassDefinitionAccess().getEncapsulatedEncapsulatedKeyword_0_0(), "encapsulated"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "encapsulated", true, "encapsulated", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:168:3: (lv_partial_1= 'partial' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==45) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:170:6: lv_partial_1= 'partial'
                    {
                    lv_partial_1=(Token)input.LT(1);
                    match(input,45,FOLLOW_45_in_ruleClassDefinition320); 

                            createLeafNode(grammarAccess.getClassDefinitionAccess().getPartialPartialKeyword_1_0(), "partial"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "partial", true, "partial", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:189:3: (lv_expandable_2= 'expandable' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==46) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:191:6: lv_expandable_2= 'expandable'
                    {
                    lv_expandable_2=(Token)input.LT(1);
                    match(input,46,FOLLOW_46_in_ruleClassDefinition355); 

                            createLeafNode(grammarAccess.getClassDefinitionAccess().getExpandableExpandableKeyword_2_0(), "expandable"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "expandable", true, "expandable", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:210:3: (lv_classType_3= ruleClassType )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:213:6: lv_classType_3= ruleClassType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getClassTypeClassTypeEnumRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleClassType_in_ruleClassDefinition403);
            lv_classType_3=ruleClassType();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "classType", lv_classType_3, "ClassType", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:231:2: ( ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? ) | ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_IDENT) ) {
                alt11=1;
            }
            else if ( (LA11_0==47) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("231:2: ( ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? ) | ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? ) )", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:231:3: ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:231:3: ( (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )? )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:231:4: (lv_name_4= RULE_IDENT ) (lv_comment_5= rulestring_comment )?
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:231:4: (lv_name_4= RULE_IDENT )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:233:6: lv_name_4= RULE_IDENT
                    {
                    lv_name_4=(Token)input.LT(1);
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleClassDefinition431); 

                    		createLeafNode(grammarAccess.getClassDefinitionAccess().getNameIDENTTerminalRuleCall_4_0_0_0(), "name"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_4, "IDENT", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:251:2: (lv_comment_5= rulestring_comment )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==RULE_STRING) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:254:6: lv_comment_5= rulestring_comment
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getCommentString_commentParserRuleCall_4_0_1_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_rulestring_comment_in_ruleClassDefinition473);
                            lv_comment_5=rulestring_comment();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "comment", lv_comment_5, "string_comment", currentNode);
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
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:273:6: ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:273:6: ( 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )? )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:273:7: 'extends' RULE_IDENT (lv_modification_8= ruleclass_modification )? ( rulestring_comment )?
                    {
                    match(input,47,FOLLOW_47_in_ruleClassDefinition495); 

                            createLeafNode(grammarAccess.getClassDefinitionAccess().getExtendsKeyword_4_1_0(), null); 
                        
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleClassDefinition504); 
                     
                        createLeafNode(grammarAccess.getClassDefinitionAccess().getIDENTTerminalRuleCall_4_1_1(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:281:1: (lv_modification_8= ruleclass_modification )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==RULE_LPAR) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:284:6: lv_modification_8= ruleclass_modification
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getModificationClass_modificationParserRuleCall_4_1_2_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleclass_modification_in_ruleClassDefinition537);
                            lv_modification_8=ruleclass_modification();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "modification", lv_modification_8, "class_modification", currentNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	        currentNode = currentNode.getParent();
                            	    

                            }
                            break;

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:302:3: ( rulestring_comment )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==RULE_STRING) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:302:4: rulestring_comment
                            {
                            pushFollow(FOLLOW_rulestring_comment_in_ruleClassDefinition552);
                            rulestring_comment();
                            _fsp--;


                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:302:26: (lv_elemList_10= ruleElementList )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:305:6: lv_elemList_10= ruleElementList
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getElemListElementListParserRuleCall_5_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleElementList_in_ruleClassDefinition582);
            lv_elemList_10=ruleElementList();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		add(current, "elemList", lv_elemList_10, "ElementList", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:323:2: ( (lv_elemList_11= ruleElementList2 ) | (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause ) | (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause ) | (lv_equationClauses_14= ruleequation_rule_clause ) | (lv_algorithmClauses_15= rulealgorithm_clause ) )*
            loop12:
            do {
                int alt12=6;
                switch ( input.LA(1) ) {
                case 54:
                case 55:
                    {
                    alt12=1;
                    }
                    break;
                case 73:
                    {
                    int LA12_3 = input.LA(2);

                    if ( (LA12_3==72) ) {
                        alt12=3;
                    }
                    else if ( (LA12_3==71) ) {
                        alt12=2;
                    }


                    }
                    break;
                case 71:
                    {
                    alt12=4;
                    }
                    break;
                case 72:
                    {
                    alt12=5;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:323:3: (lv_elemList_11= ruleElementList2 )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:323:3: (lv_elemList_11= ruleElementList2 )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:326:6: lv_elemList_11= ruleElementList2
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getElemListElementList2ParserRuleCall_6_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleElementList2_in_ruleClassDefinition621);
            	    lv_elemList_11=ruleElementList2();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elemList", lv_elemList_11, "ElementList2", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:345:6: (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:345:6: (lv_initialEquationClauses_12= ruleinitial_equation_rule_clause )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:348:6: lv_initialEquationClauses_12= ruleinitial_equation_rule_clause
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getInitialEquationClausesInitial_equation_rule_clauseParserRuleCall_6_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleinitial_equation_rule_clause_in_ruleClassDefinition665);
            	    lv_initialEquationClauses_12=ruleinitial_equation_rule_clause();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "initialEquationClauses", lv_initialEquationClauses_12, "initial_equation_rule_clause", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 3 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:367:6: (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:367:6: (lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:370:6: lv_initialAlgorithmClauses_13= ruleinitial_algorithm_clause
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getInitialAlgorithmClausesInitial_algorithm_clauseParserRuleCall_6_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleinitial_algorithm_clause_in_ruleClassDefinition709);
            	    lv_initialAlgorithmClauses_13=ruleinitial_algorithm_clause();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "initialAlgorithmClauses", lv_initialAlgorithmClauses_13, "initial_algorithm_clause", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:389:6: (lv_equationClauses_14= ruleequation_rule_clause )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:389:6: (lv_equationClauses_14= ruleequation_rule_clause )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:392:6: lv_equationClauses_14= ruleequation_rule_clause
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getEquationClausesEquation_rule_clauseParserRuleCall_6_3_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_clause_in_ruleClassDefinition753);
            	    lv_equationClauses_14=ruleequation_rule_clause();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "equationClauses", lv_equationClauses_14, "equation_rule_clause", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;
            	case 5 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:411:6: (lv_algorithmClauses_15= rulealgorithm_clause )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:411:6: (lv_algorithmClauses_15= rulealgorithm_clause )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:414:6: lv_algorithmClauses_15= rulealgorithm_clause
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getAlgorithmClausesAlgorithm_clauseParserRuleCall_6_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulealgorithm_clause_in_ruleClassDefinition797);
            	    lv_algorithmClauses_15=rulealgorithm_clause();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "algorithmClauses", lv_algorithmClauses_15, "algorithm_clause", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:432:4: (lv_extClause_16= ruleexternal_clause )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==53) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:435:6: lv_extClause_16= ruleexternal_clause
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getClassDefinitionAccess().getExtClauseExternal_clauseParserRuleCall_7_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexternal_clause_in_ruleClassDefinition837);
                    lv_extClause_16=ruleexternal_clause();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClassDefinitionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "extClause", lv_extClause_16, "external_clause", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,48,FOLLOW_48_in_ruleClassDefinition851); 

                    createLeafNode(grammarAccess.getClassDefinitionAccess().getEndKeyword_8(), null); 
                
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleClassDefinition860); 
             
                createLeafNode(grammarAccess.getClassDefinitionAccess().getIDENTTerminalRuleCall_9(), null); 
                

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
    // $ANTLR end ruleClassDefinition


    // $ANTLR start entryRulePartialDerivative
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:468:1: entryRulePartialDerivative returns [EObject current=null] : iv_rulePartialDerivative= rulePartialDerivative EOF ;
    public final EObject entryRulePartialDerivative() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePartialDerivative = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:468:59: (iv_rulePartialDerivative= rulePartialDerivative EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:469:2: iv_rulePartialDerivative= rulePartialDerivative EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPartialDerivativeRule(), currentNode); 
            pushFollow(FOLLOW_rulePartialDerivative_in_entryRulePartialDerivative892);
            iv_rulePartialDerivative=rulePartialDerivative();
            _fsp--;

             current =iv_rulePartialDerivative; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePartialDerivative902); 

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
    // $ANTLR end entryRulePartialDerivative


    // $ANTLR start rulePartialDerivative
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:476:1: rulePartialDerivative returns [EObject current=null] : ( 'function' (lv_name_1= RULE_IDENT ) RULE_EQUALS 'der' '(' rulename_path ',' ruleident_list ')' (lv_comment_9= rulecomment ) ) ;
    public final EObject rulePartialDerivative() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        EObject lv_comment_9 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:481:6: ( ( 'function' (lv_name_1= RULE_IDENT ) RULE_EQUALS 'der' '(' rulename_path ',' ruleident_list ')' (lv_comment_9= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:482:1: ( 'function' (lv_name_1= RULE_IDENT ) RULE_EQUALS 'der' '(' rulename_path ',' ruleident_list ')' (lv_comment_9= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:482:1: ( 'function' (lv_name_1= RULE_IDENT ) RULE_EQUALS 'der' '(' rulename_path ',' ruleident_list ')' (lv_comment_9= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:482:2: 'function' (lv_name_1= RULE_IDENT ) RULE_EQUALS 'der' '(' rulename_path ',' ruleident_list ')' (lv_comment_9= rulecomment )
            {
            match(input,49,FOLLOW_49_in_rulePartialDerivative936); 

                    createLeafNode(grammarAccess.getPartialDerivativeAccess().getFunctionKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:486:1: (lv_name_1= RULE_IDENT )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:488:6: lv_name_1= RULE_IDENT
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulePartialDerivative958); 

            		createLeafNode(grammarAccess.getPartialDerivativeAccess().getNameIDENTTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPartialDerivativeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "IDENT", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_rulePartialDerivative975); 
             
                createLeafNode(grammarAccess.getPartialDerivativeAccess().getEQUALSTerminalRuleCall_2(), null); 
                
            match(input,50,FOLLOW_50_in_rulePartialDerivative983); 

                    createLeafNode(grammarAccess.getPartialDerivativeAccess().getDerKeyword_3(), null); 
                
            match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_rulePartialDerivative992); 

                    createLeafNode(grammarAccess.getPartialDerivativeAccess().getLeftParenthesisKeyword_4(), null); 
                
            pushFollow(FOLLOW_rulename_path_in_rulePartialDerivative1001);
            rulename_path();
            _fsp--;

            match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulePartialDerivative1002); 

                    createLeafNode(grammarAccess.getPartialDerivativeAccess().getCommaKeyword_6(), null); 
                
            pushFollow(FOLLOW_ruleident_list_in_rulePartialDerivative1011);
            ruleident_list();
            _fsp--;

            match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_rulePartialDerivative1012); 

                    createLeafNode(grammarAccess.getPartialDerivativeAccess().getRightParenthesisKeyword_8(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:526:1: (lv_comment_9= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:529:6: lv_comment_9= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getPartialDerivativeAccess().getCommentCommentParserRuleCall_9_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_rulePartialDerivative1046);
            lv_comment_9=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPartialDerivativeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_9, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end rulePartialDerivative


    // $ANTLR start entryRuleClassModification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:554:1: entryRuleClassModification returns [EObject current=null] : iv_ruleClassModification= ruleClassModification EOF ;
    public final EObject entryRuleClassModification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassModification = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:554:59: (iv_ruleClassModification= ruleClassModification EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:555:2: iv_ruleClassModification= ruleClassModification EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClassModificationRule(), currentNode); 
            pushFollow(FOLLOW_ruleClassModification_in_entryRuleClassModification1083);
            iv_ruleClassModification=ruleClassModification();
            _fsp--;

             current =iv_ruleClassModification; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassModification1093); 

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
    // $ANTLR end entryRuleClassModification


    // $ANTLR start ruleClassModification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:562:1: ruleClassModification returns [EObject current=null] : ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS ruletype_prefix (lv_typeSpecifier_4= ruletype_specifier ) (lv_modification_5= ruleclass_modification )? (lv_comment_6= rulecomment ) ) ;
    public final EObject ruleClassModification() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        Enumerator lv_classType_0 = null;

        EObject lv_typeSpecifier_4 = null;

        EObject lv_modification_5 = null;

        EObject lv_comment_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:567:6: ( ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS ruletype_prefix (lv_typeSpecifier_4= ruletype_specifier ) (lv_modification_5= ruleclass_modification )? (lv_comment_6= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:568:1: ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS ruletype_prefix (lv_typeSpecifier_4= ruletype_specifier ) (lv_modification_5= ruleclass_modification )? (lv_comment_6= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:568:1: ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS ruletype_prefix (lv_typeSpecifier_4= ruletype_specifier ) (lv_modification_5= ruleclass_modification )? (lv_comment_6= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:568:2: (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS ruletype_prefix (lv_typeSpecifier_4= ruletype_specifier ) (lv_modification_5= ruleclass_modification )? (lv_comment_6= rulecomment )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:568:2: (lv_classType_0= ruleClassType )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:571:6: lv_classType_0= ruleClassType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getClassModificationAccess().getClassTypeClassTypeEnumRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleClassType_in_ruleClassModification1152);
            lv_classType_0=ruleClassType();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassModificationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "classType", lv_classType_0, "ClassType", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:589:2: (lv_name_1= RULE_IDENT )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:591:6: lv_name_1= RULE_IDENT
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleClassModification1178); 

            		createLeafNode(grammarAccess.getClassModificationAccess().getNameIDENTTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassModificationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "IDENT", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleClassModification1195); 
             
                createLeafNode(grammarAccess.getClassModificationAccess().getEQUALSTerminalRuleCall_2(), null); 
                
            pushFollow(FOLLOW_ruletype_prefix_in_ruleClassModification1203);
            ruletype_prefix();
            _fsp--;

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:613:16: (lv_typeSpecifier_4= ruletype_specifier )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:616:6: lv_typeSpecifier_4= ruletype_specifier
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getClassModificationAccess().getTypeSpecifierType_specifierParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruletype_specifier_in_ruleClassModification1229);
            lv_typeSpecifier_4=ruletype_specifier();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassModificationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "typeSpecifier", lv_typeSpecifier_4, "type_specifier", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:634:2: (lv_modification_5= ruleclass_modification )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_LPAR) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:637:6: lv_modification_5= ruleclass_modification
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getClassModificationAccess().getModificationClass_modificationParserRuleCall_5_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleclass_modification_in_ruleClassModification1267);
                    lv_modification_5=ruleclass_modification();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClassModificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "modification", lv_modification_5, "class_modification", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:655:3: (lv_comment_6= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:658:6: lv_comment_6= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getClassModificationAccess().getCommentCommentParserRuleCall_6_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleClassModification1306);
            lv_comment_6=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassModificationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_6, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end ruleClassModification


    // $ANTLR start entryRuleClassOverloading
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:683:1: entryRuleClassOverloading returns [EObject current=null] : iv_ruleClassOverloading= ruleClassOverloading EOF ;
    public final EObject entryRuleClassOverloading() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassOverloading = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:683:58: (iv_ruleClassOverloading= ruleClassOverloading EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:684:2: iv_ruleClassOverloading= ruleClassOverloading EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClassOverloadingRule(), currentNode); 
            pushFollow(FOLLOW_ruleClassOverloading_in_entryRuleClassOverloading1343);
            iv_ruleClassOverloading=ruleClassOverloading();
            _fsp--;

             current =iv_ruleClassOverloading; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassOverloading1353); 

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
    // $ANTLR end entryRuleClassOverloading


    // $ANTLR start ruleClassOverloading
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:691:1: ruleClassOverloading returns [EObject current=null] : ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'overload' '(' rulename_list ')' (lv_comment_7= rulecomment ) ) ;
    public final EObject ruleClassOverloading() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        Enumerator lv_classType_0 = null;

        EObject lv_comment_7 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:696:6: ( ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'overload' '(' rulename_list ')' (lv_comment_7= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:697:1: ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'overload' '(' rulename_list ')' (lv_comment_7= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:697:1: ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'overload' '(' rulename_list ')' (lv_comment_7= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:697:2: (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'overload' '(' rulename_list ')' (lv_comment_7= rulecomment )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:697:2: (lv_classType_0= ruleClassType )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:700:6: lv_classType_0= ruleClassType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getClassOverloadingAccess().getClassTypeClassTypeEnumRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleClassType_in_ruleClassOverloading1412);
            lv_classType_0=ruleClassType();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassOverloadingRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "classType", lv_classType_0, "ClassType", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:718:2: (lv_name_1= RULE_IDENT )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:720:6: lv_name_1= RULE_IDENT
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleClassOverloading1438); 

            		createLeafNode(grammarAccess.getClassOverloadingAccess().getNameIDENTTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassOverloadingRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "IDENT", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleClassOverloading1455); 
             
                createLeafNode(grammarAccess.getClassOverloadingAccess().getEQUALSTerminalRuleCall_2(), null); 
                
            match(input,51,FOLLOW_51_in_ruleClassOverloading1463); 

                    createLeafNode(grammarAccess.getClassOverloadingAccess().getOverloadKeyword_3(), null); 
                
            match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_ruleClassOverloading1472); 

                    createLeafNode(grammarAccess.getClassOverloadingAccess().getLeftParenthesisKeyword_4(), null); 
                
            pushFollow(FOLLOW_rulename_list_in_ruleClassOverloading1481);
            rulename_list();
            _fsp--;

            match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_ruleClassOverloading1482); 

                    createLeafNode(grammarAccess.getClassOverloadingAccess().getRightParenthesisKeyword_6(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:754:1: (lv_comment_7= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:757:6: lv_comment_7= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getClassOverloadingAccess().getCommentCommentParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleClassOverloading1516);
            lv_comment_7=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClassOverloadingRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_7, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end ruleClassOverloading


    // $ANTLR start entryRuleEnumeration
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:782:1: entryRuleEnumeration returns [EObject current=null] : iv_ruleEnumeration= ruleEnumeration EOF ;
    public final EObject entryRuleEnumeration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumeration = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:782:53: (iv_ruleEnumeration= ruleEnumeration EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:783:2: iv_ruleEnumeration= ruleEnumeration EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEnumerationRule(), currentNode); 
            pushFollow(FOLLOW_ruleEnumeration_in_entryRuleEnumeration1553);
            iv_ruleEnumeration=ruleEnumeration();
            _fsp--;

             current =iv_ruleEnumeration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEnumeration1563); 

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
    // $ANTLR end entryRuleEnumeration


    // $ANTLR start ruleEnumeration
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:790:1: ruleEnumeration returns [EObject current=null] : ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'enumeration' RULE_LPAR ( ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* ) | RULE_COLON ) RULE_RPAR (lv_comment_12= rulecomment ) ) ;
    public final EObject ruleEnumeration() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        Token lv_ident_5=null;
        Token lv_ident_8=null;
        Enumerator lv_classType_0 = null;

        EObject lv_enumComments_6 = null;

        EObject lv_enumComments_9 = null;

        EObject lv_comment_12 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:795:6: ( ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'enumeration' RULE_LPAR ( ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* ) | RULE_COLON ) RULE_RPAR (lv_comment_12= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:796:1: ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'enumeration' RULE_LPAR ( ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* ) | RULE_COLON ) RULE_RPAR (lv_comment_12= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:796:1: ( (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'enumeration' RULE_LPAR ( ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* ) | RULE_COLON ) RULE_RPAR (lv_comment_12= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:796:2: (lv_classType_0= ruleClassType ) (lv_name_1= RULE_IDENT ) RULE_EQUALS 'enumeration' RULE_LPAR ( ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* ) | RULE_COLON ) RULE_RPAR (lv_comment_12= rulecomment )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:796:2: (lv_classType_0= ruleClassType )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:799:6: lv_classType_0= ruleClassType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEnumerationAccess().getClassTypeClassTypeEnumRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleClassType_in_ruleEnumeration1622);
            lv_classType_0=ruleClassType();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEnumerationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "classType", lv_classType_0, "ClassType", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:817:2: (lv_name_1= RULE_IDENT )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:819:6: lv_name_1= RULE_IDENT
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleEnumeration1648); 

            		createLeafNode(grammarAccess.getEnumerationAccess().getNameIDENTTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEnumerationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "IDENT", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleEnumeration1665); 
             
                createLeafNode(grammarAccess.getEnumerationAccess().getEQUALSTerminalRuleCall_2(), null); 
                
            match(input,52,FOLLOW_52_in_ruleEnumeration1673); 

                    createLeafNode(grammarAccess.getEnumerationAccess().getEnumerationKeyword_3(), null); 
                
            match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_ruleEnumeration1682); 
             
                createLeafNode(grammarAccess.getEnumerationAccess().getLPARTerminalRuleCall_4(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:849:1: ( ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* ) | RULE_COLON )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_IDENT) ) {
                alt16=1;
            }
            else if ( (LA16_0==RULE_COLON) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("849:1: ( ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* ) | RULE_COLON )", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:849:2: ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:849:2: ( (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )* )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:849:3: (lv_ident_5= RULE_IDENT ) (lv_enumComments_6= rulecomment ) ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )*
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:849:3: (lv_ident_5= RULE_IDENT )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:851:6: lv_ident_5= RULE_IDENT
                    {
                    lv_ident_5=(Token)input.LT(1);
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleEnumeration1705); 

                    		createLeafNode(grammarAccess.getEnumerationAccess().getIdentIDENTTerminalRuleCall_5_0_0_0(), "ident"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEnumerationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "ident", lv_ident_5, "IDENT", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:869:2: (lv_enumComments_6= rulecomment )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:872:6: lv_enumComments_6= rulecomment
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getEnumerationAccess().getEnumCommentsCommentParserRuleCall_5_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulecomment_in_ruleEnumeration1747);
                    lv_enumComments_6=rulecomment();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getEnumerationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "enumComments", lv_enumComments_6, "comment", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:890:2: ( RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==RULE_COMMA) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:890:3: RULE_COMMA (lv_ident_8= RULE_IDENT ) (lv_enumComments_9= rulecomment )
                    	    {
                    	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_ruleEnumeration1761); 
                    	     
                    	        createLeafNode(grammarAccess.getEnumerationAccess().getCOMMATerminalRuleCall_5_0_2_0(), null); 
                    	        
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:894:1: (lv_ident_8= RULE_IDENT )
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:896:6: lv_ident_8= RULE_IDENT
                    	    {
                    	    lv_ident_8=(Token)input.LT(1);
                    	    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleEnumeration1782); 

                    	    		createLeafNode(grammarAccess.getEnumerationAccess().getIdentIDENTTerminalRuleCall_5_0_2_1_0(), "ident"); 
                    	    	

                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEnumerationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode, current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "ident", lv_ident_8, "IDENT", lastConsumedNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	    

                    	    }

                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:914:2: (lv_enumComments_9= rulecomment )
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:917:6: lv_enumComments_9= rulecomment
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getEnumerationAccess().getEnumCommentsCommentParserRuleCall_5_0_2_2_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_rulecomment_in_ruleEnumeration1824);
                    	    lv_enumComments_9=rulecomment();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getEnumerationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "enumComments", lv_enumComments_9, "comment", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:936:6: RULE_COLON
                    {
                    match(input,RULE_COLON,FOLLOW_RULE_COLON_in_ruleEnumeration1846); 
                     
                        createLeafNode(grammarAccess.getEnumerationAccess().getCOLONTerminalRuleCall_5_1(), null); 
                        

                    }
                    break;

            }

            match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_ruleEnumeration1855); 
             
                createLeafNode(grammarAccess.getEnumerationAccess().getRPARTerminalRuleCall_6(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:944:1: (lv_comment_12= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:947:6: lv_comment_12= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEnumerationAccess().getCommentCommentParserRuleCall_7_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleEnumeration1888);
            lv_comment_12=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEnumerationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_12, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end ruleEnumeration


    // $ANTLR start entryRuleident_list
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:972:1: entryRuleident_list returns [String current=null] : iv_ruleident_list= ruleident_list EOF ;
    public final String entryRuleident_list() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleident_list = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:972:51: (iv_ruleident_list= ruleident_list EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:973:2: iv_ruleident_list= ruleident_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIdent_listRule(), currentNode); 
            pushFollow(FOLLOW_ruleident_list_in_entryRuleident_list1926);
            iv_ruleident_list=ruleident_list();
            _fsp--;

             current =iv_ruleident_list.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleident_list1937); 

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
    // $ANTLR end entryRuleident_list


    // $ANTLR start ruleident_list
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:980:1: ruleident_list returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENT_0= RULE_IDENT (this_COMMA_1= RULE_COMMA this_IDENT_2= RULE_IDENT )* ) ;
    public final AntlrDatatypeRuleToken ruleident_list() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        Token this_COMMA_1=null;
        Token this_IDENT_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:986:6: ( (this_IDENT_0= RULE_IDENT (this_COMMA_1= RULE_COMMA this_IDENT_2= RULE_IDENT )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:987:1: (this_IDENT_0= RULE_IDENT (this_COMMA_1= RULE_COMMA this_IDENT_2= RULE_IDENT )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:987:1: (this_IDENT_0= RULE_IDENT (this_COMMA_1= RULE_COMMA this_IDENT_2= RULE_IDENT )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:987:6: this_IDENT_0= RULE_IDENT (this_COMMA_1= RULE_COMMA this_IDENT_2= RULE_IDENT )*
            {
            this_IDENT_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleident_list1977); 

            		current.merge(this_IDENT_0);
                
             
                createLeafNode(grammarAccess.getIdent_listAccess().getIDENTTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:994:1: (this_COMMA_1= RULE_COMMA this_IDENT_2= RULE_IDENT )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_COMMA) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:994:6: this_COMMA_1= RULE_COMMA this_IDENT_2= RULE_IDENT
            	    {
            	    this_COMMA_1=(Token)input.LT(1);
            	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_ruleident_list1998); 

            	    		current.merge(this_COMMA_1);
            	        
            	     
            	        createLeafNode(grammarAccess.getIdent_listAccess().getCOMMATerminalRuleCall_1_0(), null); 
            	        
            	    this_IDENT_2=(Token)input.LT(1);
            	    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleident_list2018); 

            	    		current.merge(this_IDENT_2);
            	        
            	     
            	        createLeafNode(grammarAccess.getIdent_listAccess().getIDENTTerminalRuleCall_1_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleident_list


    // $ANTLR start entryRulename_list
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1016:1: entryRulename_list returns [String current=null] : iv_rulename_list= rulename_list EOF ;
    public final String entryRulename_list() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulename_list = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1016:50: (iv_rulename_list= rulename_list EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1017:2: iv_rulename_list= rulename_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getName_listRule(), currentNode); 
            pushFollow(FOLLOW_rulename_list_in_entryRulename_list2064);
            iv_rulename_list=rulename_list();
            _fsp--;

             current =iv_rulename_list.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_list2075); 

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
    // $ANTLR end entryRulename_list


    // $ANTLR start rulename_list
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1024:1: rulename_list returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_name_path_0= rulename_path (kw= ',' this_name_path_2= rulename_path )* ) ;
    public final AntlrDatatypeRuleToken rulename_list() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_name_path_0 = null;

        AntlrDatatypeRuleToken this_name_path_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1030:6: ( (this_name_path_0= rulename_path (kw= ',' this_name_path_2= rulename_path )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1031:1: (this_name_path_0= rulename_path (kw= ',' this_name_path_2= rulename_path )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1031:1: (this_name_path_0= rulename_path (kw= ',' this_name_path_2= rulename_path )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1032:5: this_name_path_0= rulename_path (kw= ',' this_name_path_2= rulename_path )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getName_listAccess().getName_pathParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulename_path_in_rulename_list2122);
            this_name_path_0=rulename_path();
            _fsp--;


            		current.merge(this_name_path_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1042:1: (kw= ',' this_name_path_2= rulename_path )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_COMMA) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1043:2: kw= ',' this_name_path_2= rulename_path
            	    {
            	    kw=(Token)input.LT(1);
            	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulename_list2141); 

            	            current.merge(kw);
            	            createLeafNode(grammarAccess.getName_listAccess().getCommaKeyword_1_0(), null); 
            	        
            	     
            	            currentNode=createCompositeNode(grammarAccess.getName_listAccess().getName_pathParserRuleCall_1_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_rulename_path_in_rulename_list2163);
            	    this_name_path_2=rulename_path();
            	    _fsp--;


            	    		current.merge(this_name_path_2);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

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
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulename_list


    // $ANTLR start entryRuleexternal_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1067:1: entryRuleexternal_clause returns [EObject current=null] : iv_ruleexternal_clause= ruleexternal_clause EOF ;
    public final EObject entryRuleexternal_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleexternal_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1067:57: (iv_ruleexternal_clause= ruleexternal_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1068:2: iv_ruleexternal_clause= ruleexternal_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExternal_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleexternal_clause_in_entryRuleexternal_clause2208);
            iv_ruleexternal_clause=ruleexternal_clause();
            _fsp--;

             current =iv_ruleexternal_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexternal_clause2218); 

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
    // $ANTLR end entryRuleexternal_clause


    // $ANTLR start ruleexternal_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1075:1: ruleexternal_clause returns [EObject current=null] : ( 'external' (lv_languageSpecification_1= RULE_STRING )? ( ruleexternal_function_call )? (lv_annotation_3= ruleannotation )? RULE_SEMICOLON ( (lv_extAnnotation_5= ruleannotation ) RULE_SEMICOLON )? ) ;
    public final EObject ruleexternal_clause() throws RecognitionException {
        EObject current = null;

        Token lv_languageSpecification_1=null;
        EObject lv_annotation_3 = null;

        EObject lv_extAnnotation_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1080:6: ( ( 'external' (lv_languageSpecification_1= RULE_STRING )? ( ruleexternal_function_call )? (lv_annotation_3= ruleannotation )? RULE_SEMICOLON ( (lv_extAnnotation_5= ruleannotation ) RULE_SEMICOLON )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1081:1: ( 'external' (lv_languageSpecification_1= RULE_STRING )? ( ruleexternal_function_call )? (lv_annotation_3= ruleannotation )? RULE_SEMICOLON ( (lv_extAnnotation_5= ruleannotation ) RULE_SEMICOLON )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1081:1: ( 'external' (lv_languageSpecification_1= RULE_STRING )? ( ruleexternal_function_call )? (lv_annotation_3= ruleannotation )? RULE_SEMICOLON ( (lv_extAnnotation_5= ruleannotation ) RULE_SEMICOLON )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1081:2: 'external' (lv_languageSpecification_1= RULE_STRING )? ( ruleexternal_function_call )? (lv_annotation_3= ruleannotation )? RULE_SEMICOLON ( (lv_extAnnotation_5= ruleannotation ) RULE_SEMICOLON )?
            {
            match(input,53,FOLLOW_53_in_ruleexternal_clause2252); 

                    createLeafNode(grammarAccess.getExternal_clauseAccess().getExternalKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1085:1: (lv_languageSpecification_1= RULE_STRING )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_STRING) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1087:6: lv_languageSpecification_1= RULE_STRING
                    {
                    lv_languageSpecification_1=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleexternal_clause2274); 

                    		createLeafNode(grammarAccess.getExternal_clauseAccess().getLanguageSpecificationSTRINGTerminalRuleCall_1_0(), "languageSpecification"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExternal_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "languageSpecification", lv_languageSpecification_1, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1105:3: ( ruleexternal_function_call )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_IDENT) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1105:4: ruleexternal_function_call
                    {
                    pushFollow(FOLLOW_ruleexternal_function_call_in_ruleexternal_clause2293);
                    ruleexternal_function_call();
                    _fsp--;


                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1105:32: (lv_annotation_3= ruleannotation )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==91) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1108:6: lv_annotation_3= ruleannotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExternal_clauseAccess().getAnnotationAnnotationParserRuleCall_3_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleannotation_in_ruleexternal_clause2321);
                    lv_annotation_3=ruleannotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExternal_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "annotation", lv_annotation_3, "annotation", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleexternal_clause2335); 
             
                createLeafNode(grammarAccess.getExternal_clauseAccess().getSEMICOLONTerminalRuleCall_4(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1130:1: ( (lv_extAnnotation_5= ruleannotation ) RULE_SEMICOLON )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==91) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1130:2: (lv_extAnnotation_5= ruleannotation ) RULE_SEMICOLON
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1130:2: (lv_extAnnotation_5= ruleannotation )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1133:6: lv_extAnnotation_5= ruleannotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExternal_clauseAccess().getExtAnnotationAnnotationParserRuleCall_5_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleannotation_in_ruleexternal_clause2369);
                    lv_extAnnotation_5=ruleannotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExternal_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "extAnnotation", lv_extAnnotation_5, "annotation", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleexternal_clause2382); 
                     
                        createLeafNode(grammarAccess.getExternal_clauseAccess().getSEMICOLONTerminalRuleCall_5_1(), null); 
                        

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
    // $ANTLR end ruleexternal_clause


    // $ANTLR start entryRuleexternal_function_call
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1162:1: entryRuleexternal_function_call returns [String current=null] : iv_ruleexternal_function_call= ruleexternal_function_call EOF ;
    public final String entryRuleexternal_function_call() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleexternal_function_call = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1162:63: (iv_ruleexternal_function_call= ruleexternal_function_call EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1163:2: iv_ruleexternal_function_call= ruleexternal_function_call EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExternal_function_callRule(), currentNode); 
            pushFollow(FOLLOW_ruleexternal_function_call_in_entryRuleexternal_function_call2417);
            iv_ruleexternal_function_call=ruleexternal_function_call();
            _fsp--;

             current =iv_ruleexternal_function_call.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexternal_function_call2428); 

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
    // $ANTLR end entryRuleexternal_function_call


    // $ANTLR start ruleexternal_function_call
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1170:1: ruleexternal_function_call returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_component_reference_0= rulecomponent_reference this_EQUALS_1= RULE_EQUALS )? this_IDENT_2= RULE_IDENT this_LPAR_3= RULE_LPAR (this_expression_list_4= ruleexpression_list )? this_RPAR_5= RULE_RPAR ) ;
    public final AntlrDatatypeRuleToken ruleexternal_function_call() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_EQUALS_1=null;
        Token this_IDENT_2=null;
        Token this_LPAR_3=null;
        Token this_RPAR_5=null;
        AntlrDatatypeRuleToken this_component_reference_0 = null;

        AntlrDatatypeRuleToken this_expression_list_4 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1176:6: ( ( (this_component_reference_0= rulecomponent_reference this_EQUALS_1= RULE_EQUALS )? this_IDENT_2= RULE_IDENT this_LPAR_3= RULE_LPAR (this_expression_list_4= ruleexpression_list )? this_RPAR_5= RULE_RPAR ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1177:1: ( (this_component_reference_0= rulecomponent_reference this_EQUALS_1= RULE_EQUALS )? this_IDENT_2= RULE_IDENT this_LPAR_3= RULE_LPAR (this_expression_list_4= ruleexpression_list )? this_RPAR_5= RULE_RPAR )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1177:1: ( (this_component_reference_0= rulecomponent_reference this_EQUALS_1= RULE_EQUALS )? this_IDENT_2= RULE_IDENT this_LPAR_3= RULE_LPAR (this_expression_list_4= ruleexpression_list )? this_RPAR_5= RULE_RPAR )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1177:2: (this_component_reference_0= rulecomponent_reference this_EQUALS_1= RULE_EQUALS )? this_IDENT_2= RULE_IDENT this_LPAR_3= RULE_LPAR (this_expression_list_4= ruleexpression_list )? this_RPAR_5= RULE_RPAR
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1177:2: (this_component_reference_0= rulecomponent_reference this_EQUALS_1= RULE_EQUALS )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_IDENT) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==RULE_EQUALS||LA23_1==RULE_DOT||LA23_1==RULE_LBRACK) ) {
                    alt23=1;
                }
            }
            switch (alt23) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1178:5: this_component_reference_0= rulecomponent_reference this_EQUALS_1= RULE_EQUALS
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExternal_function_callAccess().getComponent_referenceParserRuleCall_0_0(), currentNode); 
                        
                    pushFollow(FOLLOW_rulecomponent_reference_in_ruleexternal_function_call2476);
                    this_component_reference_0=rulecomponent_reference();
                    _fsp--;


                    		current.merge(this_component_reference_0);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    this_EQUALS_1=(Token)input.LT(1);
                    match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleexternal_function_call2496); 

                    		current.merge(this_EQUALS_1);
                        
                     
                        createLeafNode(grammarAccess.getExternal_function_callAccess().getEQUALSTerminalRuleCall_0_1(), null); 
                        

                    }
                    break;

            }

            this_IDENT_2=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleexternal_function_call2518); 

            		current.merge(this_IDENT_2);
                
             
                createLeafNode(grammarAccess.getExternal_function_callAccess().getIDENTTerminalRuleCall_1(), null); 
                
            this_LPAR_3=(Token)input.LT(1);
            match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_ruleexternal_function_call2538); 

            		current.merge(this_LPAR_3);
                
             
                createLeafNode(grammarAccess.getExternal_function_callAccess().getLPARTerminalRuleCall_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1209:1: (this_expression_list_4= ruleexpression_list )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_IDENT||LA24_0==RULE_LPAR||LA24_0==RULE_STRING||(LA24_0>=RULE_PLUS && LA24_0<=RULE_MINUS_EW)||(LA24_0>=RULE_UNSIGNED_INTEGER && LA24_0<=RULE_LBRACK)||LA24_0==RULE_LBRACE||LA24_0==48||LA24_0==50||LA24_0==69||LA24_0==73||(LA24_0>=88 && LA24_0<=90)) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1210:5: this_expression_list_4= ruleexpression_list
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExternal_function_callAccess().getExpression_listParserRuleCall_3(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleexternal_function_call2566);
                    this_expression_list_4=ruleexpression_list();
                    _fsp--;


                    		current.merge(this_expression_list_4);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }

            this_RPAR_5=(Token)input.LT(1);
            match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_ruleexternal_function_call2588); 

            		current.merge(this_RPAR_5);
                
             
                createLeafNode(grammarAccess.getExternal_function_callAccess().getRPARTerminalRuleCall_4(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleexternal_function_call


    // $ANTLR start entryRuleElementList
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1235:1: entryRuleElementList returns [EObject current=null] : iv_ruleElementList= ruleElementList EOF ;
    public final EObject entryRuleElementList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementList = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1235:53: (iv_ruleElementList= ruleElementList EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1236:2: iv_ruleElementList= ruleElementList EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementListRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementList_in_entryRuleElementList2631);
            iv_ruleElementList=ruleElementList();
            _fsp--;

             current =iv_ruleElementList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementList2641); 

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
    // $ANTLR end entryRuleElementList


    // $ANTLR start ruleElementList
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1243:1: ruleElementList returns [EObject current=null] : ( ( (lv_elements_0= ruleClassElement ) | (lv_annotations_1= ruleannotation ) ) RULE_SEMICOLON )* ;
    public final EObject ruleElementList() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0 = null;

        EObject lv_annotations_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1248:6: ( ( ( (lv_elements_0= ruleClassElement ) | (lv_annotations_1= ruleannotation ) ) RULE_SEMICOLON )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1249:1: ( ( (lv_elements_0= ruleClassElement ) | (lv_annotations_1= ruleannotation ) ) RULE_SEMICOLON )*
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1249:1: ( ( (lv_elements_0= ruleClassElement ) | (lv_annotations_1= ruleannotation ) ) RULE_SEMICOLON )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_IDENT||(LA26_0>=43 && LA26_0<=47)||LA26_0==49||(LA26_0>=56 && LA26_0<=60)||(LA26_0>=62 && LA26_0<=68)||(LA26_0>=91 && LA26_0<=98)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1249:2: ( (lv_elements_0= ruleClassElement ) | (lv_annotations_1= ruleannotation ) ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1249:2: ( (lv_elements_0= ruleClassElement ) | (lv_annotations_1= ruleannotation ) )
            	    int alt25=2;
            	    int LA25_0 = input.LA(1);

            	    if ( (LA25_0==RULE_IDENT||(LA25_0>=43 && LA25_0<=47)||LA25_0==49||(LA25_0>=56 && LA25_0<=60)||(LA25_0>=62 && LA25_0<=68)||(LA25_0>=92 && LA25_0<=98)) ) {
            	        alt25=1;
            	    }
            	    else if ( (LA25_0==91) ) {
            	        alt25=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("1249:2: ( (lv_elements_0= ruleClassElement ) | (lv_annotations_1= ruleannotation ) )", 25, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt25) {
            	        case 1 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1249:3: (lv_elements_0= ruleClassElement )
            	            {
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1249:3: (lv_elements_0= ruleClassElement )
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1252:6: lv_elements_0= ruleClassElement
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getElementListAccess().getElementsClassElementParserRuleCall_0_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleClassElement_in_ruleElementList2701);
            	            lv_elements_0=ruleClassElement();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getElementListRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        
            	            	        try {
            	            	       		add(current, "elements", lv_elements_0, "ClassElement", currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1271:6: (lv_annotations_1= ruleannotation )
            	            {
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1271:6: (lv_annotations_1= ruleannotation )
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1274:6: lv_annotations_1= ruleannotation
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getElementListAccess().getAnnotationsAnnotationParserRuleCall_0_1_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleannotation_in_ruleElementList2745);
            	            lv_annotations_1=ruleannotation();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getElementListRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        
            	            	        try {
            	            	       		add(current, "annotations", lv_annotations_1, "annotation", currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }


            	            }
            	            break;

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleElementList2759); 
            	     
            	        createLeafNode(grammarAccess.getElementListAccess().getSEMICOLONTerminalRuleCall_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


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
    // $ANTLR end ruleElementList


    // $ANTLR start entryRuleElementList2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1303:1: entryRuleElementList2 returns [EObject current=null] : iv_ruleElementList2= ruleElementList2 EOF ;
    public final EObject entryRuleElementList2() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementList2 = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1303:54: (iv_ruleElementList2= ruleElementList2 EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1304:2: iv_ruleElementList2= ruleElementList2 EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementList2Rule(), currentNode); 
            pushFollow(FOLLOW_ruleElementList2_in_entryRuleElementList22792);
            iv_ruleElementList2=ruleElementList2();
            _fsp--;

             current =iv_ruleElementList2; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementList22802); 

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
    // $ANTLR end entryRuleElementList2


    // $ANTLR start ruleElementList2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1311:1: ruleElementList2 returns [EObject current=null] : ( ( 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )* ) | ( 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )* ) ) ;
    public final EObject ruleElementList2() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_2 = null;

        EObject lv_annotations_3 = null;

        EObject lv_elements_7 = null;

        EObject lv_annotations_8 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1316:6: ( ( ( 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )* ) | ( 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )* ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1317:1: ( ( 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )* ) | ( 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )* ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1317:1: ( ( 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )* ) | ( 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )* ) )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==54) ) {
                alt31=1;
            }
            else if ( (LA31_0==55) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1317:1: ( ( 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )* ) | ( 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )* ) )", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1317:2: ( 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )* )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1317:2: ( 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )* )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1317:3: 'public' () ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )*
                    {
                    match(input,54,FOLLOW_54_in_ruleElementList22837); 

                            createLeafNode(grammarAccess.getElementList2Access().getPublicKeyword_0_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1321:1: ()
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1322:5: 
                    {
                     
                            temp=factory.create(grammarAccess.getElementList2Access().getPublicElementListAction_0_1().getType().getClassifier());
                            current = temp; 
                            temp = null;
                            CompositeNode newNode = createCompositeNode(grammarAccess.getElementList2Access().getPublicElementListAction_0_1(), currentNode.getParent());
                        newNode.getChildren().add(currentNode);
                        moveLookaheadInfo(currentNode, newNode);
                        currentNode = newNode; 
                            associateNodeWithAstElement(currentNode, current); 
                        

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1332:2: ( ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==RULE_IDENT||(LA28_0>=43 && LA28_0<=47)||LA28_0==49||(LA28_0>=56 && LA28_0<=60)||(LA28_0>=62 && LA28_0<=68)||(LA28_0>=91 && LA28_0<=98)) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1332:3: ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) ) RULE_SEMICOLON
                    	    {
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1332:3: ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) )
                    	    int alt27=2;
                    	    int LA27_0 = input.LA(1);

                    	    if ( (LA27_0==RULE_IDENT||(LA27_0>=43 && LA27_0<=47)||LA27_0==49||(LA27_0>=56 && LA27_0<=60)||(LA27_0>=62 && LA27_0<=68)||(LA27_0>=92 && LA27_0<=98)) ) {
                    	        alt27=1;
                    	    }
                    	    else if ( (LA27_0==91) ) {
                    	        alt27=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("1332:3: ( (lv_elements_2= ruleClassElement ) | (lv_annotations_3= ruleannotation ) )", 27, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt27) {
                    	        case 1 :
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1332:4: (lv_elements_2= ruleClassElement )
                    	            {
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1332:4: (lv_elements_2= ruleClassElement )
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1335:6: lv_elements_2= ruleClassElement
                    	            {
                    	             
                    	            	        currentNode=createCompositeNode(grammarAccess.getElementList2Access().getElementsClassElementParserRuleCall_0_2_0_0_0(), currentNode); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleClassElement_in_ruleElementList22882);
                    	            lv_elements_2=ruleClassElement();
                    	            _fsp--;


                    	            	        if (current==null) {
                    	            	            current = factory.create(grammarAccess.getElementList2Rule().getType().getClassifier());
                    	            	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	            	        }
                    	            	        
                    	            	        try {
                    	            	       		add(current, "elements", lv_elements_2, "ClassElement", currentNode);
                    	            	        } catch (ValueConverterException vce) {
                    	            				handleValueConverterException(vce);
                    	            	        }
                    	            	        currentNode = currentNode.getParent();
                    	            	    

                    	            }


                    	            }
                    	            break;
                    	        case 2 :
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1354:6: (lv_annotations_3= ruleannotation )
                    	            {
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1354:6: (lv_annotations_3= ruleannotation )
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1357:6: lv_annotations_3= ruleannotation
                    	            {
                    	             
                    	            	        currentNode=createCompositeNode(grammarAccess.getElementList2Access().getAnnotationsAnnotationParserRuleCall_0_2_0_1_0(), currentNode); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleannotation_in_ruleElementList22926);
                    	            lv_annotations_3=ruleannotation();
                    	            _fsp--;


                    	            	        if (current==null) {
                    	            	            current = factory.create(grammarAccess.getElementList2Rule().getType().getClassifier());
                    	            	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	            	        }
                    	            	        
                    	            	        try {
                    	            	       		add(current, "annotations", lv_annotations_3, "annotation", currentNode);
                    	            	        } catch (ValueConverterException vce) {
                    	            				handleValueConverterException(vce);
                    	            	        }
                    	            	        currentNode = currentNode.getParent();
                    	            	    

                    	            }


                    	            }
                    	            break;

                    	    }

                    	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleElementList22940); 
                    	     
                    	        createLeafNode(grammarAccess.getElementList2Access().getSEMICOLONTerminalRuleCall_0_2_1(), null); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1380:6: ( 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )* )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1380:6: ( 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )* )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1380:7: 'protected' () ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )*
                    {
                    match(input,55,FOLLOW_55_in_ruleElementList22958); 

                            createLeafNode(grammarAccess.getElementList2Access().getProtectedKeyword_1_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1384:1: ()
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1385:5: 
                    {
                     
                            temp=factory.create(grammarAccess.getElementList2Access().getProtectesElementListAction_1_1().getType().getClassifier());
                            current = temp; 
                            temp = null;
                            CompositeNode newNode = createCompositeNode(grammarAccess.getElementList2Access().getProtectesElementListAction_1_1(), currentNode.getParent());
                        newNode.getChildren().add(currentNode);
                        moveLookaheadInfo(currentNode, newNode);
                        currentNode = newNode; 
                            associateNodeWithAstElement(currentNode, current); 
                        

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1395:2: ( ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==RULE_IDENT||(LA30_0>=43 && LA30_0<=47)||LA30_0==49||(LA30_0>=56 && LA30_0<=60)||(LA30_0>=62 && LA30_0<=68)||(LA30_0>=91 && LA30_0<=98)) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1395:3: ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) ) RULE_SEMICOLON
                    	    {
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1395:3: ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) )
                    	    int alt29=2;
                    	    int LA29_0 = input.LA(1);

                    	    if ( (LA29_0==RULE_IDENT||(LA29_0>=43 && LA29_0<=47)||LA29_0==49||(LA29_0>=56 && LA29_0<=60)||(LA29_0>=62 && LA29_0<=68)||(LA29_0>=92 && LA29_0<=98)) ) {
                    	        alt29=1;
                    	    }
                    	    else if ( (LA29_0==91) ) {
                    	        alt29=2;
                    	    }
                    	    else {
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("1395:3: ( (lv_elements_7= ruleClassElement ) | (lv_annotations_8= ruleannotation ) )", 29, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt29) {
                    	        case 1 :
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1395:4: (lv_elements_7= ruleClassElement )
                    	            {
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1395:4: (lv_elements_7= ruleClassElement )
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1398:6: lv_elements_7= ruleClassElement
                    	            {
                    	             
                    	            	        currentNode=createCompositeNode(grammarAccess.getElementList2Access().getElementsClassElementParserRuleCall_1_2_0_0_0(), currentNode); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleClassElement_in_ruleElementList23003);
                    	            lv_elements_7=ruleClassElement();
                    	            _fsp--;


                    	            	        if (current==null) {
                    	            	            current = factory.create(grammarAccess.getElementList2Rule().getType().getClassifier());
                    	            	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	            	        }
                    	            	        
                    	            	        try {
                    	            	       		add(current, "elements", lv_elements_7, "ClassElement", currentNode);
                    	            	        } catch (ValueConverterException vce) {
                    	            				handleValueConverterException(vce);
                    	            	        }
                    	            	        currentNode = currentNode.getParent();
                    	            	    

                    	            }


                    	            }
                    	            break;
                    	        case 2 :
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1417:6: (lv_annotations_8= ruleannotation )
                    	            {
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1417:6: (lv_annotations_8= ruleannotation )
                    	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1420:6: lv_annotations_8= ruleannotation
                    	            {
                    	             
                    	            	        currentNode=createCompositeNode(grammarAccess.getElementList2Access().getAnnotationsAnnotationParserRuleCall_1_2_0_1_0(), currentNode); 
                    	            	    
                    	            pushFollow(FOLLOW_ruleannotation_in_ruleElementList23047);
                    	            lv_annotations_8=ruleannotation();
                    	            _fsp--;


                    	            	        if (current==null) {
                    	            	            current = factory.create(grammarAccess.getElementList2Rule().getType().getClassifier());
                    	            	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	            	        }
                    	            	        
                    	            	        try {
                    	            	       		add(current, "annotations", lv_annotations_8, "annotation", currentNode);
                    	            	        } catch (ValueConverterException vce) {
                    	            				handleValueConverterException(vce);
                    	            	        }
                    	            	        currentNode = currentNode.getParent();
                    	            	    

                    	            }


                    	            }
                    	            break;

                    	    }

                    	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleElementList23061); 
                    	     
                    	        createLeafNode(grammarAccess.getElementList2Access().getSEMICOLONTerminalRuleCall_1_2_1(), null); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);


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
    // $ANTLR end ruleElementList2


    // $ANTLR start entryRuleClassElement
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1449:1: entryRuleClassElement returns [EObject current=null] : iv_ruleClassElement= ruleClassElement EOF ;
    public final EObject entryRuleClassElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassElement = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1449:54: (iv_ruleClassElement= ruleClassElement EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1450:2: iv_ruleClassElement= ruleClassElement EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClassElementRule(), currentNode); 
            pushFollow(FOLLOW_ruleClassElement_in_entryRuleClassElement3096);
            iv_ruleClassElement=ruleClassElement();
            _fsp--;

             current =iv_ruleClassElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClassElement3106); 

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
    // $ANTLR end entryRuleClassElement


    // $ANTLR start ruleClassElement
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1457:1: ruleClassElement returns [EObject current=null] : (this_import_clause_0= ruleimport_clause | this_extends_clause_1= ruleextends_clause | ( ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause ) ) ) ;
    public final EObject ruleClassElement() throws RecognitionException {
        EObject current = null;

        EObject this_import_clause_0 = null;

        EObject this_extends_clause_1 = null;

        EObject this_ClassDefinition_6 = null;

        EObject this_component_clause_7 = null;

        EObject this_ReplaceableElementClause_8 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1462:6: ( (this_import_clause_0= ruleimport_clause | this_extends_clause_1= ruleextends_clause | ( ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause ) ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1463:1: (this_import_clause_0= ruleimport_clause | this_extends_clause_1= ruleextends_clause | ( ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause ) ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1463:1: (this_import_clause_0= ruleimport_clause | this_extends_clause_1= ruleextends_clause | ( ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause ) ) )
            int alt38=3;
            switch ( input.LA(1) ) {
            case 60:
                {
                alt38=1;
                }
                break;
            case 47:
                {
                alt38=2;
                }
                break;
            case RULE_IDENT:
            case 43:
            case 44:
            case 45:
            case 46:
            case 49:
            case 56:
            case 57:
            case 58:
            case 59:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
                {
                alt38=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1463:1: (this_import_clause_0= ruleimport_clause | this_extends_clause_1= ruleextends_clause | ( ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause ) ) )", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1464:5: this_import_clause_0= ruleimport_clause
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getClassElementAccess().getImport_clauseParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleimport_clause_in_ruleClassElement3153);
                    this_import_clause_0=ruleimport_clause();
                    _fsp--;

                     
                            current = this_import_clause_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1474:5: this_extends_clause_1= ruleextends_clause
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getClassElementAccess().getExtends_clauseParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleextends_clause_in_ruleClassElement3180);
                    this_extends_clause_1=ruleextends_clause();
                    _fsp--;

                     
                            current = this_extends_clause_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1483:6: ( ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause ) )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1483:6: ( ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause ) )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1483:7: ( 'redeclare' )? ( 'final' )? ( 'inner' )? ( 'outer' )? ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1483:7: ( 'redeclare' )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==56) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1483:8: 'redeclare'
                            {
                            match(input,56,FOLLOW_56_in_ruleClassElement3196); 

                                    createLeafNode(grammarAccess.getClassElementAccess().getRedeclareKeyword_2_0(), null); 
                                

                            }
                            break;

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1487:3: ( 'final' )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==43) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1487:4: 'final'
                            {
                            match(input,43,FOLLOW_43_in_ruleClassElement3208); 

                                    createLeafNode(grammarAccess.getClassElementAccess().getFinalKeyword_2_1(), null); 
                                

                            }
                            break;

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1491:3: ( 'inner' )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==57) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1491:4: 'inner'
                            {
                            match(input,57,FOLLOW_57_in_ruleClassElement3220); 

                                    createLeafNode(grammarAccess.getClassElementAccess().getInnerKeyword_2_2(), null); 
                                

                            }
                            break;

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1495:3: ( 'outer' )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==58) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1495:4: 'outer'
                            {
                            match(input,58,FOLLOW_58_in_ruleClassElement3232); 

                                    createLeafNode(grammarAccess.getClassElementAccess().getOuterKeyword_2_3(), null); 
                                

                            }
                            break;

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1499:3: ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause )
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==RULE_IDENT||(LA37_0>=44 && LA37_0<=46)||LA37_0==49||(LA37_0>=62 && LA37_0<=68)||(LA37_0>=92 && LA37_0<=98)) ) {
                        alt37=1;
                    }
                    else if ( (LA37_0==59) ) {
                        alt37=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("1499:3: ( (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause ) | this_ReplaceableElementClause_8= ruleReplaceableElementClause )", 37, 0, input);

                        throw nvae;
                    }
                    switch (alt37) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1499:4: (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause )
                            {
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1499:4: (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause )
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( ((LA36_0>=44 && LA36_0<=46)||LA36_0==49||(LA36_0>=92 && LA36_0<=98)) ) {
                                alt36=1;
                            }
                            else if ( (LA36_0==RULE_IDENT||(LA36_0>=62 && LA36_0<=68)) ) {
                                alt36=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("1499:4: (this_ClassDefinition_6= ruleClassDefinition | this_component_clause_7= rulecomponent_clause )", 36, 0, input);

                                throw nvae;
                            }
                            switch (alt36) {
                                case 1 :
                                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1500:5: this_ClassDefinition_6= ruleClassDefinition
                                    {
                                     
                                            currentNode=createCompositeNode(grammarAccess.getClassElementAccess().getClassDefinitionParserRuleCall_2_4_0_0(), currentNode); 
                                        
                                    pushFollow(FOLLOW_ruleClassDefinition_in_ruleClassElement3258);
                                    this_ClassDefinition_6=ruleClassDefinition();
                                    _fsp--;

                                     
                                            current = this_ClassDefinition_6; 
                                            currentNode = currentNode.getParent();
                                        

                                    }
                                    break;
                                case 2 :
                                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1510:5: this_component_clause_7= rulecomponent_clause
                                    {
                                     
                                            currentNode=createCompositeNode(grammarAccess.getClassElementAccess().getComponent_clauseParserRuleCall_2_4_0_1(), currentNode); 
                                        
                                    pushFollow(FOLLOW_rulecomponent_clause_in_ruleClassElement3285);
                                    this_component_clause_7=rulecomponent_clause();
                                    _fsp--;

                                     
                                            current = this_component_clause_7; 
                                            currentNode = currentNode.getParent();
                                        

                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1520:5: this_ReplaceableElementClause_8= ruleReplaceableElementClause
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getClassElementAccess().getReplaceableElementClauseParserRuleCall_2_4_1(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleReplaceableElementClause_in_ruleClassElement3313);
                            this_ReplaceableElementClause_8=ruleReplaceableElementClause();
                            _fsp--;

                             
                                    current = this_ReplaceableElementClause_8; 
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

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
    // $ANTLR end ruleClassElement


    // $ANTLR start entryRuleReplaceableElementClause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1535:1: entryRuleReplaceableElementClause returns [EObject current=null] : iv_ruleReplaceableElementClause= ruleReplaceableElementClause EOF ;
    public final EObject entryRuleReplaceableElementClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReplaceableElementClause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1535:66: (iv_ruleReplaceableElementClause= ruleReplaceableElementClause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1536:2: iv_ruleReplaceableElementClause= ruleReplaceableElementClause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getReplaceableElementClauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleReplaceableElementClause_in_entryRuleReplaceableElementClause3347);
            iv_ruleReplaceableElementClause=ruleReplaceableElementClause();
            _fsp--;

             current =iv_ruleReplaceableElementClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReplaceableElementClause3357); 

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
    // $ANTLR end entryRuleReplaceableElementClause


    // $ANTLR start ruleReplaceableElementClause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1543:1: ruleReplaceableElementClause returns [EObject current=null] : ( 'replaceable' (lv_entity_1= ( ruleClassDefinition | rulecomponent_clause ) ) (lv_constrain_2= ruleconstraining_clause )? ) ;
    public final EObject ruleReplaceableElementClause() throws RecognitionException {
        EObject current = null;

        Token lv_entity_1=null;
        EObject lv_constrain_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1548:6: ( ( 'replaceable' (lv_entity_1= ( ruleClassDefinition | rulecomponent_clause ) ) (lv_constrain_2= ruleconstraining_clause )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1549:1: ( 'replaceable' (lv_entity_1= ( ruleClassDefinition | rulecomponent_clause ) ) (lv_constrain_2= ruleconstraining_clause )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1549:1: ( 'replaceable' (lv_entity_1= ( ruleClassDefinition | rulecomponent_clause ) ) (lv_constrain_2= ruleconstraining_clause )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1549:2: 'replaceable' (lv_entity_1= ( ruleClassDefinition | rulecomponent_clause ) ) (lv_constrain_2= ruleconstraining_clause )?
            {
            match(input,59,FOLLOW_59_in_ruleReplaceableElementClause3391); 

                    createLeafNode(grammarAccess.getReplaceableElementClauseAccess().getReplaceableKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1553:1: (lv_entity_1= ( ruleClassDefinition | rulecomponent_clause ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1555:6: lv_entity_1= ( ruleClassDefinition | rulecomponent_clause )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1555:18: ( ruleClassDefinition | rulecomponent_clause )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( ((LA39_0>=44 && LA39_0<=46)||LA39_0==49||(LA39_0>=92 && LA39_0<=98)) ) {
                alt39=1;
            }
            else if ( (LA39_0==RULE_IDENT||(LA39_0>=62 && LA39_0<=68)) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1555:18: ( ruleClassDefinition | rulecomponent_clause )", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1555:20: ruleClassDefinition
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getReplaceableElementClauseAccess().getEntityClassDefinitionParserRuleCall_1_0_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleClassDefinition_in_ruleReplaceableElementClause3417);
                    ruleClassDefinition();
                    _fsp--;

                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1562:7: rulecomponent_clause
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getReplaceableElementClauseAccess().getEntityComponent_clauseParserRuleCall_1_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulecomponent_clause_in_ruleReplaceableElementClause3431);
                    rulecomponent_clause();
                    _fsp--;

                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getReplaceableElementClauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "entity", lv_entity_1, null, lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1583:2: (lv_constrain_2= ruleconstraining_clause )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==47||LA40_0==61) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1586:6: lv_constrain_2= ruleconstraining_clause
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getReplaceableElementClauseAccess().getConstrainConstraining_clauseParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleconstraining_clause_in_ruleReplaceableElementClause3474);
                    lv_constrain_2=ruleconstraining_clause();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getReplaceableElementClauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "constrain", lv_constrain_2, "constraining_clause", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

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
    // $ANTLR end ruleReplaceableElementClause


    // $ANTLR start entryRuleimport_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1611:1: entryRuleimport_clause returns [EObject current=null] : iv_ruleimport_clause= ruleimport_clause EOF ;
    public final EObject entryRuleimport_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleimport_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1611:55: (iv_ruleimport_clause= ruleimport_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1612:2: iv_ruleimport_clause= ruleimport_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getImport_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleimport_clause_in_entryRuleimport_clause3512);
            iv_ruleimport_clause=ruleimport_clause();
            _fsp--;

             current =iv_ruleimport_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleimport_clause3522); 

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
    // $ANTLR end entryRuleimport_clause


    // $ANTLR start ruleimport_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1619:1: ruleimport_clause returns [EObject current=null] : ( 'import' ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) ) (lv_comment_5= rulecomment ) ) ;
    public final EObject ruleimport_clause() throws RecognitionException {
        EObject current = null;

        Token lv_asName_1=null;
        AntlrDatatypeRuleToken lv_name_3 = null;

        AntlrDatatypeRuleToken lv_name_4 = null;

        EObject lv_comment_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1624:6: ( ( 'import' ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) ) (lv_comment_5= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1625:1: ( 'import' ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) ) (lv_comment_5= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1625:1: ( 'import' ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) ) (lv_comment_5= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1625:2: 'import' ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) ) (lv_comment_5= rulecomment )
            {
            match(input,60,FOLLOW_60_in_ruleimport_clause3556); 

                    createLeafNode(grammarAccess.getImport_clauseAccess().getImportKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1629:1: ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_IDENT) ) {
                int LA41_1 = input.LA(2);

                if ( (LA41_1==EOF||LA41_1==RULE_SEMICOLON||LA41_1==RULE_STRING||LA41_1==RULE_DOT||LA41_1==RULE_STAR_EW||LA41_1==91) ) {
                    alt41=2;
                }
                else if ( (LA41_1==RULE_EQUALS) ) {
                    alt41=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1629:1: ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) )", 41, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1629:1: ( ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) ) | (lv_name_4= rulename_path_star ) )", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1629:2: ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1629:2: ( (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path ) )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1629:3: (lv_asName_1= RULE_IDENT ) RULE_EQUALS (lv_name_3= rulename_path )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1629:3: (lv_asName_1= RULE_IDENT )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1631:6: lv_asName_1= RULE_IDENT
                    {
                    lv_asName_1=(Token)input.LT(1);
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleimport_clause3580); 

                    		createLeafNode(grammarAccess.getImport_clauseAccess().getAsNameIDENTTerminalRuleCall_1_0_0_0(), "asName"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getImport_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "asName", lv_asName_1, "IDENT", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleimport_clause3597); 
                     
                        createLeafNode(grammarAccess.getImport_clauseAccess().getEQUALSTerminalRuleCall_1_0_1(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1653:1: (lv_name_3= rulename_path )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1656:6: lv_name_3= rulename_path
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getImport_clauseAccess().getNameName_pathParserRuleCall_1_0_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulename_path_in_ruleimport_clause3630);
                    lv_name_3=rulename_path();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getImport_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_3, "name_path", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1675:6: (lv_name_4= rulename_path_star )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1675:6: (lv_name_4= rulename_path_star )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1678:6: lv_name_4= rulename_path_star
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getImport_clauseAccess().getNameName_path_starParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulename_path_star_in_ruleimport_clause3675);
                    lv_name_4=rulename_path_star();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getImport_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_4, "name_path_star", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1696:3: (lv_comment_5= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1699:6: lv_comment_5= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getImport_clauseAccess().getCommentCommentParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleimport_clause3714);
            lv_comment_5=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getImport_clauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_5, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end ruleimport_clause


    // $ANTLR start entryRuleextends_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1724:1: entryRuleextends_clause returns [EObject current=null] : iv_ruleextends_clause= ruleextends_clause EOF ;
    public final EObject entryRuleextends_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleextends_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1724:56: (iv_ruleextends_clause= ruleextends_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1725:2: iv_ruleextends_clause= ruleextends_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExtends_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleextends_clause_in_entryRuleextends_clause3751);
            iv_ruleextends_clause=ruleextends_clause();
            _fsp--;

             current =iv_ruleextends_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleextends_clause3761); 

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
    // $ANTLR end entryRuleextends_clause


    // $ANTLR start ruleextends_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1732:1: ruleextends_clause returns [EObject current=null] : ( 'extends' (lv_baseClass_1= rulename_path ) (lv_modification_2= ruleclass_modification )? (lv_annotation_3= ruleannotation )? ) ;
    public final EObject ruleextends_clause() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_baseClass_1 = null;

        EObject lv_modification_2 = null;

        EObject lv_annotation_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1737:6: ( ( 'extends' (lv_baseClass_1= rulename_path ) (lv_modification_2= ruleclass_modification )? (lv_annotation_3= ruleannotation )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1738:1: ( 'extends' (lv_baseClass_1= rulename_path ) (lv_modification_2= ruleclass_modification )? (lv_annotation_3= ruleannotation )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1738:1: ( 'extends' (lv_baseClass_1= rulename_path ) (lv_modification_2= ruleclass_modification )? (lv_annotation_3= ruleannotation )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1738:2: 'extends' (lv_baseClass_1= rulename_path ) (lv_modification_2= ruleclass_modification )? (lv_annotation_3= ruleannotation )?
            {
            match(input,47,FOLLOW_47_in_ruleextends_clause3795); 

                    createLeafNode(grammarAccess.getExtends_clauseAccess().getExtendsKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1742:1: (lv_baseClass_1= rulename_path )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1745:6: lv_baseClass_1= rulename_path
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getExtends_clauseAccess().getBaseClassName_pathParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulename_path_in_ruleextends_clause3829);
            lv_baseClass_1=rulename_path();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getExtends_clauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "baseClass", lv_baseClass_1, "name_path", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1763:2: (lv_modification_2= ruleclass_modification )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==RULE_LPAR) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1766:6: lv_modification_2= ruleclass_modification
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExtends_clauseAccess().getModificationClass_modificationParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleclass_modification_in_ruleextends_clause3867);
                    lv_modification_2=ruleclass_modification();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExtends_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "modification", lv_modification_2, "class_modification", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1784:3: (lv_annotation_3= ruleannotation )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==91) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1787:6: lv_annotation_3= ruleannotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getExtends_clauseAccess().getAnnotationAnnotationParserRuleCall_3_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleannotation_in_ruleextends_clause3906);
                    lv_annotation_3=ruleannotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getExtends_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "annotation", lv_annotation_3, "annotation", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

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
    // $ANTLR end ruleextends_clause


    // $ANTLR start entryRuleconstraining_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1812:1: entryRuleconstraining_clause returns [EObject current=null] : iv_ruleconstraining_clause= ruleconstraining_clause EOF ;
    public final EObject entryRuleconstraining_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconstraining_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1812:61: (iv_ruleconstraining_clause= ruleconstraining_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1813:2: iv_ruleconstraining_clause= ruleconstraining_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConstraining_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleconstraining_clause_in_entryRuleconstraining_clause3944);
            iv_ruleconstraining_clause=ruleconstraining_clause();
            _fsp--;

             current =iv_ruleconstraining_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconstraining_clause3954); 

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
    // $ANTLR end entryRuleconstraining_clause


    // $ANTLR start ruleconstraining_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1820:1: ruleconstraining_clause returns [EObject current=null] : ( ( ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? ) | ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? ) ) (lv_comment_6= rulecomment ) ) ;
    public final EObject ruleconstraining_clause() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1 = null;

        EObject lv_modification_2 = null;

        AntlrDatatypeRuleToken lv_name_4 = null;

        EObject lv_modification_5 = null;

        EObject lv_comment_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1825:6: ( ( ( ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? ) | ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? ) ) (lv_comment_6= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1826:1: ( ( ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? ) | ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? ) ) (lv_comment_6= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1826:1: ( ( ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? ) | ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? ) ) (lv_comment_6= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1826:2: ( ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? ) | ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? ) ) (lv_comment_6= rulecomment )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1826:2: ( ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? ) | ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? ) )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==47) ) {
                alt46=1;
            }
            else if ( (LA46_0==61) ) {
                alt46=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1826:2: ( ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? ) | ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? ) )", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1826:3: ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1826:3: ( 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )? )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1826:4: 'extends' (lv_name_1= rulename_path ) (lv_modification_2= ruleclass_modification )?
                    {
                    match(input,47,FOLLOW_47_in_ruleconstraining_clause3990); 

                            createLeafNode(grammarAccess.getConstraining_clauseAccess().getExtendsKeyword_0_0_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1830:1: (lv_name_1= rulename_path )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1833:6: lv_name_1= rulename_path
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConstraining_clauseAccess().getNameName_pathParserRuleCall_0_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulename_path_in_ruleconstraining_clause4024);
                    lv_name_1=rulename_path();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConstraining_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_1, "name_path", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1851:2: (lv_modification_2= ruleclass_modification )?
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==RULE_LPAR) ) {
                        alt44=1;
                    }
                    switch (alt44) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1854:6: lv_modification_2= ruleclass_modification
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getConstraining_clauseAccess().getModificationClass_modificationParserRuleCall_0_0_2_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleclass_modification_in_ruleconstraining_clause4062);
                            lv_modification_2=ruleclass_modification();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getConstraining_clauseRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "modification", lv_modification_2, "class_modification", currentNode);
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
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1873:6: ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1873:6: ( 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )? )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1873:7: 'constrainedby' (lv_name_4= rulename_path ) (lv_modification_5= ruleclass_modification )?
                    {
                    match(input,61,FOLLOW_61_in_ruleconstraining_clause4084); 

                            createLeafNode(grammarAccess.getConstraining_clauseAccess().getConstrainedbyKeyword_0_1_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1877:1: (lv_name_4= rulename_path )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1880:6: lv_name_4= rulename_path
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConstraining_clauseAccess().getNameName_pathParserRuleCall_0_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulename_path_in_ruleconstraining_clause4118);
                    lv_name_4=rulename_path();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConstraining_clauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "name", lv_name_4, "name_path", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1898:2: (lv_modification_5= ruleclass_modification )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==RULE_LPAR) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1901:6: lv_modification_5= ruleclass_modification
                            {
                             
                            	        currentNode=createCompositeNode(grammarAccess.getConstraining_clauseAccess().getModificationClass_modificationParserRuleCall_0_1_2_0(), currentNode); 
                            	    
                            pushFollow(FOLLOW_ruleclass_modification_in_ruleconstraining_clause4156);
                            lv_modification_5=ruleclass_modification();
                            _fsp--;


                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getConstraining_clauseRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "modification", lv_modification_5, "class_modification", currentNode);
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

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1919:5: (lv_comment_6= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1922:6: lv_comment_6= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConstraining_clauseAccess().getCommentCommentParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleconstraining_clause4197);
            lv_comment_6=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConstraining_clauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_6, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end ruleconstraining_clause


    // $ANTLR start entryRulecomponent_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1947:1: entryRulecomponent_clause returns [EObject current=null] : iv_rulecomponent_clause= rulecomponent_clause EOF ;
    public final EObject entryRulecomponent_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1947:58: (iv_rulecomponent_clause= rulecomponent_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1948:2: iv_rulecomponent_clause= rulecomponent_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getComponent_clauseRule(), currentNode); 
            pushFollow(FOLLOW_rulecomponent_clause_in_entryRulecomponent_clause4234);
            iv_rulecomponent_clause=rulecomponent_clause();
            _fsp--;

             current =iv_rulecomponent_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_clause4244); 

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
    // $ANTLR end entryRulecomponent_clause


    // $ANTLR start rulecomponent_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1955:1: rulecomponent_clause returns [EObject current=null] : ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) (lv_declarations_2= rulecomponent_declaration ) ( RULE_COMMA (lv_declarations_4= rulecomponent_declaration ) )* ) ;
    public final EObject rulecomponent_clause() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_typePrefix_0 = null;

        EObject lv_typeSpecifier_1 = null;

        EObject lv_declarations_2 = null;

        EObject lv_declarations_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1960:6: ( ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) (lv_declarations_2= rulecomponent_declaration ) ( RULE_COMMA (lv_declarations_4= rulecomponent_declaration ) )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1961:1: ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) (lv_declarations_2= rulecomponent_declaration ) ( RULE_COMMA (lv_declarations_4= rulecomponent_declaration ) )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1961:1: ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) (lv_declarations_2= rulecomponent_declaration ) ( RULE_COMMA (lv_declarations_4= rulecomponent_declaration ) )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1961:2: (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) (lv_declarations_2= rulecomponent_declaration ) ( RULE_COMMA (lv_declarations_4= rulecomponent_declaration ) )*
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1961:2: (lv_typePrefix_0= ruletype_prefix )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1964:6: lv_typePrefix_0= ruletype_prefix
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getComponent_clauseAccess().getTypePrefixType_prefixParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruletype_prefix_in_rulecomponent_clause4303);
            lv_typePrefix_0=ruletype_prefix();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getComponent_clauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "typePrefix", lv_typePrefix_0, "type_prefix", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1982:2: (lv_typeSpecifier_1= ruletype_specifier )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:1985:6: lv_typeSpecifier_1= ruletype_specifier
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getComponent_clauseAccess().getTypeSpecifierType_specifierParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruletype_specifier_in_rulecomponent_clause4341);
            lv_typeSpecifier_1=ruletype_specifier();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getComponent_clauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "typeSpecifier", lv_typeSpecifier_1, "type_specifier", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2003:2: (lv_declarations_2= rulecomponent_declaration )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2006:6: lv_declarations_2= rulecomponent_declaration
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getComponent_clauseAccess().getDeclarationsComponent_declarationParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomponent_declaration_in_rulecomponent_clause4379);
            lv_declarations_2=rulecomponent_declaration();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getComponent_clauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		add(current, "declarations", lv_declarations_2, "component_declaration", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2024:2: ( RULE_COMMA (lv_declarations_4= rulecomponent_declaration ) )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==RULE_COMMA) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2024:3: RULE_COMMA (lv_declarations_4= rulecomponent_declaration )
            	    {
            	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulecomponent_clause4393); 
            	     
            	        createLeafNode(grammarAccess.getComponent_clauseAccess().getCOMMATerminalRuleCall_3_0(), null); 
            	        
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2028:1: (lv_declarations_4= rulecomponent_declaration )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2031:6: lv_declarations_4= rulecomponent_declaration
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getComponent_clauseAccess().getDeclarationsComponent_declarationParserRuleCall_3_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulecomponent_declaration_in_rulecomponent_clause4426);
            	    lv_declarations_4=rulecomponent_declaration();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getComponent_clauseRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "declarations", lv_declarations_4, "component_declaration", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop47;
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
    // $ANTLR end rulecomponent_clause


    // $ANTLR start entryRuletype_prefix
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2056:1: entryRuletype_prefix returns [String current=null] : iv_ruletype_prefix= ruletype_prefix EOF ;
    public final String entryRuletype_prefix() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruletype_prefix = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2056:52: (iv_ruletype_prefix= ruletype_prefix EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2057:2: iv_ruletype_prefix= ruletype_prefix EOF
            {
             currentNode = createCompositeNode(grammarAccess.getType_prefixRule(), currentNode); 
            pushFollow(FOLLOW_ruletype_prefix_in_entryRuletype_prefix4466);
            iv_ruletype_prefix=ruletype_prefix();
            _fsp--;

             current =iv_ruletype_prefix.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuletype_prefix4477); 

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
    // $ANTLR end entryRuletype_prefix


    // $ANTLR start ruletype_prefix
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2064:1: ruletype_prefix returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= 'flow' | kw= 'stream' )? (kw= 'discrete' | kw= 'parameter' | kw= 'constant' )? (kw= 'input' | kw= 'output' )? ) ;
    public final AntlrDatatypeRuleToken ruletype_prefix() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2070:6: ( ( (kw= 'flow' | kw= 'stream' )? (kw= 'discrete' | kw= 'parameter' | kw= 'constant' )? (kw= 'input' | kw= 'output' )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2071:1: ( (kw= 'flow' | kw= 'stream' )? (kw= 'discrete' | kw= 'parameter' | kw= 'constant' )? (kw= 'input' | kw= 'output' )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2071:1: ( (kw= 'flow' | kw= 'stream' )? (kw= 'discrete' | kw= 'parameter' | kw= 'constant' )? (kw= 'input' | kw= 'output' )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2071:2: (kw= 'flow' | kw= 'stream' )? (kw= 'discrete' | kw= 'parameter' | kw= 'constant' )? (kw= 'input' | kw= 'output' )?
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2071:2: (kw= 'flow' | kw= 'stream' )?
            int alt48=3;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==62) ) {
                alt48=1;
            }
            else if ( (LA48_0==63) ) {
                alt48=2;
            }
            switch (alt48) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2072:2: kw= 'flow'
                    {
                    kw=(Token)input.LT(1);
                    match(input,62,FOLLOW_62_in_ruletype_prefix4516); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getType_prefixAccess().getFlowKeyword_0_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2079:2: kw= 'stream'
                    {
                    kw=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_ruletype_prefix4535); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getType_prefixAccess().getStreamKeyword_0_1(), null); 
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2084:3: (kw= 'discrete' | kw= 'parameter' | kw= 'constant' )?
            int alt49=4;
            switch ( input.LA(1) ) {
                case 64:
                    {
                    alt49=1;
                    }
                    break;
                case 65:
                    {
                    alt49=2;
                    }
                    break;
                case 66:
                    {
                    alt49=3;
                    }
                    break;
            }

            switch (alt49) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2085:2: kw= 'discrete'
                    {
                    kw=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_ruletype_prefix4551); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getType_prefixAccess().getDiscreteKeyword_1_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2092:2: kw= 'parameter'
                    {
                    kw=(Token)input.LT(1);
                    match(input,65,FOLLOW_65_in_ruletype_prefix4570); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getType_prefixAccess().getParameterKeyword_1_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2099:2: kw= 'constant'
                    {
                    kw=(Token)input.LT(1);
                    match(input,66,FOLLOW_66_in_ruletype_prefix4589); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getType_prefixAccess().getConstantKeyword_1_2(), null); 
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2104:3: (kw= 'input' | kw= 'output' )?
            int alt50=3;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==67) ) {
                alt50=1;
            }
            else if ( (LA50_0==68) ) {
                alt50=2;
            }
            switch (alt50) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2105:2: kw= 'input'
                    {
                    kw=(Token)input.LT(1);
                    match(input,67,FOLLOW_67_in_ruletype_prefix4605); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getType_prefixAccess().getInputKeyword_2_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2112:2: kw= 'output'
                    {
                    kw=(Token)input.LT(1);
                    match(input,68,FOLLOW_68_in_ruletype_prefix4624); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getType_prefixAccess().getOutputKeyword_2_1(), null); 
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruletype_prefix


    // $ANTLR start entryRuletype_specifier
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2125:1: entryRuletype_specifier returns [EObject current=null] : iv_ruletype_specifier= ruletype_specifier EOF ;
    public final EObject entryRuletype_specifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruletype_specifier = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2125:56: (iv_ruletype_specifier= ruletype_specifier EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2126:2: iv_ruletype_specifier= ruletype_specifier EOF
            {
             currentNode = createCompositeNode(grammarAccess.getType_specifierRule(), currentNode); 
            pushFollow(FOLLOW_ruletype_specifier_in_entryRuletype_specifier4664);
            iv_ruletype_specifier=ruletype_specifier();
            _fsp--;

             current =iv_ruletype_specifier; 
            match(input,EOF,FOLLOW_EOF_in_entryRuletype_specifier4674); 

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
    // $ANTLR end entryRuletype_specifier


    // $ANTLR start ruletype_specifier
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2133:1: ruletype_specifier returns [EObject current=null] : ( (lv_name_0= rulename_path ) (lv_subscripts_1= rulearray_subscripts )? ) ;
    public final EObject ruletype_specifier() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0 = null;

        AntlrDatatypeRuleToken lv_subscripts_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2138:6: ( ( (lv_name_0= rulename_path ) (lv_subscripts_1= rulearray_subscripts )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2139:1: ( (lv_name_0= rulename_path ) (lv_subscripts_1= rulearray_subscripts )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2139:1: ( (lv_name_0= rulename_path ) (lv_subscripts_1= rulearray_subscripts )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2139:2: (lv_name_0= rulename_path ) (lv_subscripts_1= rulearray_subscripts )?
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2139:2: (lv_name_0= rulename_path )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2142:6: lv_name_0= rulename_path
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getType_specifierAccess().getNameName_pathParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulename_path_in_ruletype_specifier4733);
            lv_name_0=rulename_path();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getType_specifierRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_0, "name_path", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2160:2: (lv_subscripts_1= rulearray_subscripts )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==RULE_LBRACK) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2163:6: lv_subscripts_1= rulearray_subscripts
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getType_specifierAccess().getSubscriptsArray_subscriptsParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_ruletype_specifier4771);
                    lv_subscripts_1=rulearray_subscripts();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getType_specifierRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "subscripts", lv_subscripts_1, "array_subscripts", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

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
    // $ANTLR end ruletype_specifier


    // $ANTLR start entryRulecomponent_declaration
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2188:1: entryRulecomponent_declaration returns [EObject current=null] : iv_rulecomponent_declaration= rulecomponent_declaration EOF ;
    public final EObject entryRulecomponent_declaration() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomponent_declaration = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2188:63: (iv_rulecomponent_declaration= rulecomponent_declaration EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2189:2: iv_rulecomponent_declaration= rulecomponent_declaration EOF
            {
             currentNode = createCompositeNode(grammarAccess.getComponent_declarationRule(), currentNode); 
            pushFollow(FOLLOW_rulecomponent_declaration_in_entryRulecomponent_declaration4809);
            iv_rulecomponent_declaration=rulecomponent_declaration();
            _fsp--;

             current =iv_rulecomponent_declaration; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_declaration4819); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2196:1: rulecomponent_declaration returns [EObject current=null] : ( RULE_IDENT (lv_subscripts_1= rulearray_subscripts )? (lv_modification_2= rulemodification )? ( 'if' (lv_condition_4= ruleexpression ) )? (lv_comment_5= rulecomment ) ) ;
    public final EObject rulecomponent_declaration() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_subscripts_1 = null;

        EObject lv_modification_2 = null;

        AntlrDatatypeRuleToken lv_condition_4 = null;

        EObject lv_comment_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2201:6: ( ( RULE_IDENT (lv_subscripts_1= rulearray_subscripts )? (lv_modification_2= rulemodification )? ( 'if' (lv_condition_4= ruleexpression ) )? (lv_comment_5= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2202:1: ( RULE_IDENT (lv_subscripts_1= rulearray_subscripts )? (lv_modification_2= rulemodification )? ( 'if' (lv_condition_4= ruleexpression ) )? (lv_comment_5= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2202:1: ( RULE_IDENT (lv_subscripts_1= rulearray_subscripts )? (lv_modification_2= rulemodification )? ( 'if' (lv_condition_4= ruleexpression ) )? (lv_comment_5= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2202:2: RULE_IDENT (lv_subscripts_1= rulearray_subscripts )? (lv_modification_2= rulemodification )? ( 'if' (lv_condition_4= ruleexpression ) )? (lv_comment_5= rulecomment )
            {
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_declaration4853); 
             
                createLeafNode(grammarAccess.getComponent_declarationAccess().getIDENTTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2206:1: (lv_subscripts_1= rulearray_subscripts )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==RULE_LBRACK) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2209:6: lv_subscripts_1= rulearray_subscripts
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getComponent_declarationAccess().getSubscriptsArray_subscriptsParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_declaration4886);
                    lv_subscripts_1=rulearray_subscripts();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getComponent_declarationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "subscripts", lv_subscripts_1, "array_subscripts", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2227:3: (lv_modification_2= rulemodification )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( ((LA53_0>=RULE_EQUALS && LA53_0<=RULE_LPAR)||LA53_0==RULE_ASSIGN) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2230:6: lv_modification_2= rulemodification
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getComponent_declarationAccess().getModificationModificationParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulemodification_in_rulecomponent_declaration4925);
                    lv_modification_2=rulemodification();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getComponent_declarationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "modification", lv_modification_2, "modification", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2248:3: ( 'if' (lv_condition_4= ruleexpression ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==69) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2248:4: 'if' (lv_condition_4= ruleexpression )
                    {
                    match(input,69,FOLLOW_69_in_rulecomponent_declaration4940); 

                            createLeafNode(grammarAccess.getComponent_declarationAccess().getIfKeyword_3_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2252:1: (lv_condition_4= ruleexpression )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2255:6: lv_condition_4= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getComponent_declarationAccess().getConditionExpressionParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_rulecomponent_declaration4974);
                    lv_condition_4=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getComponent_declarationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "condition", lv_condition_4, "expression", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2273:4: (lv_comment_5= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2276:6: lv_comment_5= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getComponent_declarationAccess().getCommentCommentParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_rulecomponent_declaration5014);
            lv_comment_5=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getComponent_declarationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_5, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end rulecomponent_declaration


    // $ANTLR start entryRulemodification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2301:1: entryRulemodification returns [EObject current=null] : iv_rulemodification= rulemodification EOF ;
    public final EObject entryRulemodification() throws RecognitionException {
        EObject current = null;

        EObject iv_rulemodification = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2301:54: (iv_rulemodification= rulemodification EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2302:2: iv_rulemodification= rulemodification EOF
            {
             currentNode = createCompositeNode(grammarAccess.getModificationRule(), currentNode); 
            pushFollow(FOLLOW_rulemodification_in_entryRulemodification5051);
            iv_rulemodification=rulemodification();
            _fsp--;

             current =iv_rulemodification; 
            match(input,EOF,FOLLOW_EOF_in_entryRulemodification5061); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2309:1: rulemodification returns [EObject current=null] : (this_class_modification_0= ruleclass_modification | this_SimpleModification_1= ruleSimpleModification ) ;
    public final EObject rulemodification() throws RecognitionException {
        EObject current = null;

        EObject this_class_modification_0 = null;

        EObject this_SimpleModification_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2314:6: ( (this_class_modification_0= ruleclass_modification | this_SimpleModification_1= ruleSimpleModification ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2315:1: (this_class_modification_0= ruleclass_modification | this_SimpleModification_1= ruleSimpleModification )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2315:1: (this_class_modification_0= ruleclass_modification | this_SimpleModification_1= ruleSimpleModification )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==RULE_LPAR) ) {
                alt55=1;
            }
            else if ( (LA55_0==RULE_EQUALS||LA55_0==RULE_ASSIGN) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2315:1: (this_class_modification_0= ruleclass_modification | this_SimpleModification_1= ruleSimpleModification )", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2316:5: this_class_modification_0= ruleclass_modification
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getModificationAccess().getClass_modificationParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleclass_modification_in_rulemodification5108);
                    this_class_modification_0=ruleclass_modification();
                    _fsp--;

                     
                            current = this_class_modification_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2326:5: this_SimpleModification_1= ruleSimpleModification
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getModificationAccess().getSimpleModificationParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleSimpleModification_in_rulemodification5135);
                    this_SimpleModification_1=ruleSimpleModification();
                    _fsp--;

                     
                            current = this_SimpleModification_1; 
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
    // $ANTLR end rulemodification


    // $ANTLR start entryRuleSimpleModification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2341:1: entryRuleSimpleModification returns [EObject current=null] : iv_ruleSimpleModification= ruleSimpleModification EOF ;
    public final EObject entryRuleSimpleModification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleModification = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2341:60: (iv_ruleSimpleModification= ruleSimpleModification EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2342:2: iv_ruleSimpleModification= ruleSimpleModification EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSimpleModificationRule(), currentNode); 
            pushFollow(FOLLOW_ruleSimpleModification_in_entryRuleSimpleModification5167);
            iv_ruleSimpleModification=ruleSimpleModification();
            _fsp--;

             current =iv_ruleSimpleModification; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleModification5177); 

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
    // $ANTLR end entryRuleSimpleModification


    // $ANTLR start ruleSimpleModification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2349:1: ruleSimpleModification returns [EObject current=null] : ( ( RULE_EQUALS (lv_expression_1= ruleexpression ) ) | ( RULE_ASSIGN (lv_expression_3= ruleexpression ) ) ) ;
    public final EObject ruleSimpleModification() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_expression_1 = null;

        AntlrDatatypeRuleToken lv_expression_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2354:6: ( ( ( RULE_EQUALS (lv_expression_1= ruleexpression ) ) | ( RULE_ASSIGN (lv_expression_3= ruleexpression ) ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2355:1: ( ( RULE_EQUALS (lv_expression_1= ruleexpression ) ) | ( RULE_ASSIGN (lv_expression_3= ruleexpression ) ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2355:1: ( ( RULE_EQUALS (lv_expression_1= ruleexpression ) ) | ( RULE_ASSIGN (lv_expression_3= ruleexpression ) ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==RULE_EQUALS) ) {
                alt56=1;
            }
            else if ( (LA56_0==RULE_ASSIGN) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2355:1: ( ( RULE_EQUALS (lv_expression_1= ruleexpression ) ) | ( RULE_ASSIGN (lv_expression_3= ruleexpression ) ) )", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2355:2: ( RULE_EQUALS (lv_expression_1= ruleexpression ) )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2355:2: ( RULE_EQUALS (lv_expression_1= ruleexpression ) )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2355:3: RULE_EQUALS (lv_expression_1= ruleexpression )
                    {
                    match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleSimpleModification5212); 
                     
                        createLeafNode(grammarAccess.getSimpleModificationAccess().getEQUALSTerminalRuleCall_0_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2359:1: (lv_expression_1= ruleexpression )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2362:6: lv_expression_1= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSimpleModificationAccess().getExpressionExpressionParserRuleCall_0_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleSimpleModification5245);
                    lv_expression_1=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSimpleModificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "expression", lv_expression_1, "expression", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2381:6: ( RULE_ASSIGN (lv_expression_3= ruleexpression ) )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2381:6: ( RULE_ASSIGN (lv_expression_3= ruleexpression ) )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2381:7: RULE_ASSIGN (lv_expression_3= ruleexpression )
                    {
                    match(input,RULE_ASSIGN,FOLLOW_RULE_ASSIGN_in_ruleSimpleModification5266); 
                     
                        createLeafNode(grammarAccess.getSimpleModificationAccess().getASSIGNTerminalRuleCall_1_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2385:1: (lv_expression_3= ruleexpression )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2388:6: lv_expression_3= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSimpleModificationAccess().getExpressionExpressionParserRuleCall_1_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleSimpleModification5299);
                    lv_expression_3=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSimpleModificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "expression", lv_expression_3, "expression", currentNode);
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
    // $ANTLR end ruleSimpleModification


    // $ANTLR start entryRuleclass_modification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2413:1: entryRuleclass_modification returns [EObject current=null] : iv_ruleclass_modification= ruleclass_modification EOF ;
    public final EObject entryRuleclass_modification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleclass_modification = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2413:60: (iv_ruleclass_modification= ruleclass_modification EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2414:2: iv_ruleclass_modification= ruleclass_modification EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClass_modificationRule(), currentNode); 
            pushFollow(FOLLOW_ruleclass_modification_in_entryRuleclass_modification5337);
            iv_ruleclass_modification=ruleclass_modification();
            _fsp--;

             current =iv_ruleclass_modification; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleclass_modification5347); 

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
    // $ANTLR end entryRuleclass_modification


    // $ANTLR start ruleclass_modification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2421:1: ruleclass_modification returns [EObject current=null] : ( RULE_LPAR ( (lv_arguments_1= ruleargument ) ( RULE_COMMA (lv_arguments_3= ruleargument ) )* )? RULE_RPAR ( RULE_EQUALS (lv_expression_6= ruleexpression ) )? ) ;
    public final EObject ruleclass_modification() throws RecognitionException {
        EObject current = null;

        EObject lv_arguments_1 = null;

        EObject lv_arguments_3 = null;

        AntlrDatatypeRuleToken lv_expression_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2426:6: ( ( RULE_LPAR ( (lv_arguments_1= ruleargument ) ( RULE_COMMA (lv_arguments_3= ruleargument ) )* )? RULE_RPAR ( RULE_EQUALS (lv_expression_6= ruleexpression ) )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2427:1: ( RULE_LPAR ( (lv_arguments_1= ruleargument ) ( RULE_COMMA (lv_arguments_3= ruleargument ) )* )? RULE_RPAR ( RULE_EQUALS (lv_expression_6= ruleexpression ) )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2427:1: ( RULE_LPAR ( (lv_arguments_1= ruleargument ) ( RULE_COMMA (lv_arguments_3= ruleargument ) )* )? RULE_RPAR ( RULE_EQUALS (lv_expression_6= ruleexpression ) )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2427:2: RULE_LPAR ( (lv_arguments_1= ruleargument ) ( RULE_COMMA (lv_arguments_3= ruleargument ) )* )? RULE_RPAR ( RULE_EQUALS (lv_expression_6= ruleexpression ) )?
            {
            match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_ruleclass_modification5381); 
             
                createLeafNode(grammarAccess.getClass_modificationAccess().getLPARTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2431:1: ( (lv_arguments_1= ruleargument ) ( RULE_COMMA (lv_arguments_3= ruleargument ) )* )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_IDENT||LA58_0==43||LA58_0==56||LA58_0==59||LA58_0==70) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2431:2: (lv_arguments_1= ruleargument ) ( RULE_COMMA (lv_arguments_3= ruleargument ) )*
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2431:2: (lv_arguments_1= ruleargument )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2434:6: lv_arguments_1= ruleargument
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getClass_modificationAccess().getArgumentsArgumentParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleargument_in_ruleclass_modification5415);
                    lv_arguments_1=ruleargument();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClass_modificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "arguments", lv_arguments_1, "argument", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2452:2: ( RULE_COMMA (lv_arguments_3= ruleargument ) )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==RULE_COMMA) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2452:3: RULE_COMMA (lv_arguments_3= ruleargument )
                    	    {
                    	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_ruleclass_modification5429); 
                    	     
                    	        createLeafNode(grammarAccess.getClass_modificationAccess().getCOMMATerminalRuleCall_1_1_0(), null); 
                    	        
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2456:1: (lv_arguments_3= ruleargument )
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2459:6: lv_arguments_3= ruleargument
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getClass_modificationAccess().getArgumentsArgumentParserRuleCall_1_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleargument_in_ruleclass_modification5462);
                    	    lv_arguments_3=ruleargument();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getClass_modificationRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "arguments", lv_arguments_3, "argument", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_ruleclass_modification5479); 
             
                createLeafNode(grammarAccess.getClass_modificationAccess().getRPARTerminalRuleCall_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2481:1: ( RULE_EQUALS (lv_expression_6= ruleexpression ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==RULE_EQUALS) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2481:2: RULE_EQUALS (lv_expression_6= ruleexpression )
                    {
                    match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleclass_modification5488); 
                     
                        createLeafNode(grammarAccess.getClass_modificationAccess().getEQUALSTerminalRuleCall_3_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2485:1: (lv_expression_6= ruleexpression )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2488:6: lv_expression_6= ruleexpression
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getClass_modificationAccess().getExpressionExpressionParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleexpression_in_ruleclass_modification5521);
                    lv_expression_6=ruleexpression();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getClass_modificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "expression", lv_expression_6, "expression", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

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
    // $ANTLR end ruleclass_modification


    // $ANTLR start entryRuleargument
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2513:1: entryRuleargument returns [EObject current=null] : iv_ruleargument= ruleargument EOF ;
    public final EObject entryRuleargument() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleargument = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2513:50: (iv_ruleargument= ruleargument EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2514:2: iv_ruleargument= ruleargument EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArgumentRule(), currentNode); 
            pushFollow(FOLLOW_ruleargument_in_entryRuleargument5560);
            iv_ruleargument=ruleargument();
            _fsp--;

             current =iv_ruleargument; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleargument5570); 

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
    // $ANTLR end entryRuleargument


    // $ANTLR start ruleargument
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2521:1: ruleargument returns [EObject current=null] : ( ( ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause ) ) | this_ElementRedeclaration_4= ruleElementRedeclaration ) ;
    public final EObject ruleargument() throws RecognitionException {
        EObject current = null;

        EObject this_ElementModification_2 = null;

        EObject this_ElementReplaceableClause_3 = null;

        EObject this_ElementRedeclaration_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2526:6: ( ( ( ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause ) ) | this_ElementRedeclaration_4= ruleElementRedeclaration ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2527:1: ( ( ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause ) ) | this_ElementRedeclaration_4= ruleElementRedeclaration )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2527:1: ( ( ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause ) ) | this_ElementRedeclaration_4= ruleElementRedeclaration )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==RULE_IDENT||LA63_0==43||LA63_0==59||LA63_0==70) ) {
                alt63=1;
            }
            else if ( (LA63_0==56) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2527:1: ( ( ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause ) ) | this_ElementRedeclaration_4= ruleElementRedeclaration )", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2527:2: ( ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause ) )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2527:2: ( ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause ) )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2527:3: ( 'each' )? ( 'final' )? (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2527:3: ( 'each' )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==70) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2527:4: 'each'
                            {
                            match(input,70,FOLLOW_70_in_ruleargument5606); 

                                    createLeafNode(grammarAccess.getArgumentAccess().getEachKeyword_0_0(), null); 
                                

                            }
                            break;

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2531:3: ( 'final' )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==43) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2531:4: 'final'
                            {
                            match(input,43,FOLLOW_43_in_ruleargument5618); 

                                    createLeafNode(grammarAccess.getArgumentAccess().getFinalKeyword_0_1(), null); 
                                

                            }
                            break;

                    }

                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2535:3: (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause )
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==RULE_IDENT) ) {
                        alt62=1;
                    }
                    else if ( (LA62_0==59) ) {
                        alt62=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2535:3: (this_ElementModification_2= ruleElementModification | this_ElementReplaceableClause_3= ruleElementReplaceableClause )", 62, 0, input);

                        throw nvae;
                    }
                    switch (alt62) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2536:5: this_ElementModification_2= ruleElementModification
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getArgumentAccess().getElementModificationParserRuleCall_0_2_0(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleElementModification_in_ruleargument5643);
                            this_ElementModification_2=ruleElementModification();
                            _fsp--;

                             
                                    current = this_ElementModification_2; 
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;
                        case 2 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2546:5: this_ElementReplaceableClause_3= ruleElementReplaceableClause
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getArgumentAccess().getElementReplaceableClauseParserRuleCall_0_2_1(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleElementReplaceableClause_in_ruleargument5670);
                            this_ElementReplaceableClause_3=ruleElementReplaceableClause();
                            _fsp--;

                             
                                    current = this_ElementReplaceableClause_3; 
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2556:5: this_ElementRedeclaration_4= ruleElementRedeclaration
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getArgumentAccess().getElementRedeclarationParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleElementRedeclaration_in_ruleargument5699);
                    this_ElementRedeclaration_4=ruleElementRedeclaration();
                    _fsp--;

                     
                            current = this_ElementRedeclaration_4; 
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
    // $ANTLR end ruleargument


    // $ANTLR start entryRuleElementModification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2571:1: entryRuleElementModification returns [EObject current=null] : iv_ruleElementModification= ruleElementModification EOF ;
    public final EObject entryRuleElementModification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementModification = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2571:61: (iv_ruleElementModification= ruleElementModification EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2572:2: iv_ruleElementModification= ruleElementModification EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementModificationRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementModification_in_entryRuleElementModification5731);
            iv_ruleElementModification=ruleElementModification();
            _fsp--;

             current =iv_ruleElementModification; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementModification5741); 

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
    // $ANTLR end entryRuleElementModification


    // $ANTLR start ruleElementModification
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2579:1: ruleElementModification returns [EObject current=null] : ( (lv_componetRef_0= rulecomponent_reference ) (lv_modification_1= rulemodification )? (lv_comment_2= rulestring_comment )? ) ;
    public final EObject ruleElementModification() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_componetRef_0 = null;

        EObject lv_modification_1 = null;

        AntlrDatatypeRuleToken lv_comment_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2584:6: ( ( (lv_componetRef_0= rulecomponent_reference ) (lv_modification_1= rulemodification )? (lv_comment_2= rulestring_comment )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2585:1: ( (lv_componetRef_0= rulecomponent_reference ) (lv_modification_1= rulemodification )? (lv_comment_2= rulestring_comment )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2585:1: ( (lv_componetRef_0= rulecomponent_reference ) (lv_modification_1= rulemodification )? (lv_comment_2= rulestring_comment )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2585:2: (lv_componetRef_0= rulecomponent_reference ) (lv_modification_1= rulemodification )? (lv_comment_2= rulestring_comment )?
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2585:2: (lv_componetRef_0= rulecomponent_reference )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2588:6: lv_componetRef_0= rulecomponent_reference
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementModificationAccess().getComponetRefComponent_referenceParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomponent_reference_in_ruleElementModification5800);
            lv_componetRef_0=rulecomponent_reference();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementModificationRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "componetRef", lv_componetRef_0, "component_reference", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2606:2: (lv_modification_1= rulemodification )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( ((LA64_0>=RULE_EQUALS && LA64_0<=RULE_LPAR)||LA64_0==RULE_ASSIGN) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2609:6: lv_modification_1= rulemodification
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementModificationAccess().getModificationModificationParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulemodification_in_ruleElementModification5838);
                    lv_modification_1=rulemodification();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementModificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "modification", lv_modification_1, "modification", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2627:3: (lv_comment_2= rulestring_comment )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==RULE_STRING) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2630:6: lv_comment_2= rulestring_comment
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementModificationAccess().getCommentString_commentParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulestring_comment_in_ruleElementModification5877);
                    lv_comment_2=rulestring_comment();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementModificationRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "comment", lv_comment_2, "string_comment", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

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
    // $ANTLR end ruleElementModification


    // $ANTLR start entryRuleElementRedeclaration
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2655:1: entryRuleElementRedeclaration returns [EObject current=null] : iv_ruleElementRedeclaration= ruleElementRedeclaration EOF ;
    public final EObject entryRuleElementRedeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementRedeclaration = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2655:62: (iv_ruleElementRedeclaration= ruleElementRedeclaration EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2656:2: iv_ruleElementRedeclaration= ruleElementRedeclaration EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementRedeclarationRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementRedeclaration_in_entryRuleElementRedeclaration5915);
            iv_ruleElementRedeclaration=ruleElementRedeclaration();
            _fsp--;

             current =iv_ruleElementRedeclaration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementRedeclaration5925); 

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
    // $ANTLR end entryRuleElementRedeclaration


    // $ANTLR start ruleElementRedeclaration
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2663:1: ruleElementRedeclaration returns [EObject current=null] : ( 'redeclare' ( 'each' )? ( 'final' )? ( (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause ) | this_ElementReplaceableClause_5= ruleElementReplaceableClause ) ) ;
    public final EObject ruleElementRedeclaration() throws RecognitionException {
        EObject current = null;

        EObject this_ClassDefinition_3 = null;

        EObject this_ElementComponentClause_4 = null;

        EObject this_ElementReplaceableClause_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2668:6: ( ( 'redeclare' ( 'each' )? ( 'final' )? ( (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause ) | this_ElementReplaceableClause_5= ruleElementReplaceableClause ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2669:1: ( 'redeclare' ( 'each' )? ( 'final' )? ( (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause ) | this_ElementReplaceableClause_5= ruleElementReplaceableClause ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2669:1: ( 'redeclare' ( 'each' )? ( 'final' )? ( (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause ) | this_ElementReplaceableClause_5= ruleElementReplaceableClause ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2669:2: 'redeclare' ( 'each' )? ( 'final' )? ( (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause ) | this_ElementReplaceableClause_5= ruleElementReplaceableClause )
            {
            match(input,56,FOLLOW_56_in_ruleElementRedeclaration5959); 

                    createLeafNode(grammarAccess.getElementRedeclarationAccess().getRedeclareKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2673:1: ( 'each' )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==70) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2673:2: 'each'
                    {
                    match(input,70,FOLLOW_70_in_ruleElementRedeclaration5969); 

                            createLeafNode(grammarAccess.getElementRedeclarationAccess().getEachKeyword_1(), null); 
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2677:3: ( 'final' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==43) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2677:4: 'final'
                    {
                    match(input,43,FOLLOW_43_in_ruleElementRedeclaration5981); 

                            createLeafNode(grammarAccess.getElementRedeclarationAccess().getFinalKeyword_2(), null); 
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2681:3: ( (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause ) | this_ElementReplaceableClause_5= ruleElementReplaceableClause )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==RULE_IDENT||(LA69_0>=44 && LA69_0<=46)||LA69_0==49||(LA69_0>=62 && LA69_0<=68)||(LA69_0>=92 && LA69_0<=98)) ) {
                alt69=1;
            }
            else if ( (LA69_0==59) ) {
                alt69=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2681:3: ( (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause ) | this_ElementReplaceableClause_5= ruleElementReplaceableClause )", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2681:4: (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2681:4: (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause )
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( ((LA68_0>=44 && LA68_0<=46)||LA68_0==49||(LA68_0>=92 && LA68_0<=98)) ) {
                        alt68=1;
                    }
                    else if ( (LA68_0==RULE_IDENT||(LA68_0>=62 && LA68_0<=68)) ) {
                        alt68=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("2681:4: (this_ClassDefinition_3= ruleClassDefinition | this_ElementComponentClause_4= ruleElementComponentClause )", 68, 0, input);

                        throw nvae;
                    }
                    switch (alt68) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2682:5: this_ClassDefinition_3= ruleClassDefinition
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getElementRedeclarationAccess().getClassDefinitionParserRuleCall_3_0_0(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleClassDefinition_in_ruleElementRedeclaration6007);
                            this_ClassDefinition_3=ruleClassDefinition();
                            _fsp--;

                             
                                    current = this_ClassDefinition_3; 
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;
                        case 2 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2692:5: this_ElementComponentClause_4= ruleElementComponentClause
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getElementRedeclarationAccess().getElementComponentClauseParserRuleCall_3_0_1(), currentNode); 
                                
                            pushFollow(FOLLOW_ruleElementComponentClause_in_ruleElementRedeclaration6034);
                            this_ElementComponentClause_4=ruleElementComponentClause();
                            _fsp--;

                             
                                    current = this_ElementComponentClause_4; 
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2702:5: this_ElementReplaceableClause_5= ruleElementReplaceableClause
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getElementRedeclarationAccess().getElementReplaceableClauseParserRuleCall_3_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleElementReplaceableClause_in_ruleElementRedeclaration6062);
                    this_ElementReplaceableClause_5=ruleElementReplaceableClause();
                    _fsp--;

                     
                            current = this_ElementReplaceableClause_5; 
                            currentNode = currentNode.getParent();
                        

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
    // $ANTLR end ruleElementRedeclaration


    // $ANTLR start entryRuleElementReplaceableClause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2717:1: entryRuleElementReplaceableClause returns [EObject current=null] : iv_ruleElementReplaceableClause= ruleElementReplaceableClause EOF ;
    public final EObject entryRuleElementReplaceableClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementReplaceableClause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2717:66: (iv_ruleElementReplaceableClause= ruleElementReplaceableClause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2718:2: iv_ruleElementReplaceableClause= ruleElementReplaceableClause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementReplaceableClauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementReplaceableClause_in_entryRuleElementReplaceableClause6095);
            iv_ruleElementReplaceableClause=ruleElementReplaceableClause();
            _fsp--;

             current =iv_ruleElementReplaceableClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementReplaceableClause6105); 

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
    // $ANTLR end entryRuleElementReplaceableClause


    // $ANTLR start ruleElementReplaceableClause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2725:1: ruleElementReplaceableClause returns [EObject current=null] : ( 'replaceable' (lv_element_1= ( ruleClassDefinition | ruleElementComponentClause ) ) (lv_constraint_2= ruleconstraining_clause )? ) ;
    public final EObject ruleElementReplaceableClause() throws RecognitionException {
        EObject current = null;

        Token lv_element_1=null;
        EObject lv_constraint_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2730:6: ( ( 'replaceable' (lv_element_1= ( ruleClassDefinition | ruleElementComponentClause ) ) (lv_constraint_2= ruleconstraining_clause )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2731:1: ( 'replaceable' (lv_element_1= ( ruleClassDefinition | ruleElementComponentClause ) ) (lv_constraint_2= ruleconstraining_clause )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2731:1: ( 'replaceable' (lv_element_1= ( ruleClassDefinition | ruleElementComponentClause ) ) (lv_constraint_2= ruleconstraining_clause )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2731:2: 'replaceable' (lv_element_1= ( ruleClassDefinition | ruleElementComponentClause ) ) (lv_constraint_2= ruleconstraining_clause )?
            {
            match(input,59,FOLLOW_59_in_ruleElementReplaceableClause6139); 

                    createLeafNode(grammarAccess.getElementReplaceableClauseAccess().getReplaceableKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2735:1: (lv_element_1= ( ruleClassDefinition | ruleElementComponentClause ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2737:6: lv_element_1= ( ruleClassDefinition | ruleElementComponentClause )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2737:19: ( ruleClassDefinition | ruleElementComponentClause )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( ((LA70_0>=44 && LA70_0<=46)||LA70_0==49||(LA70_0>=92 && LA70_0<=98)) ) {
                alt70=1;
            }
            else if ( (LA70_0==RULE_IDENT||(LA70_0>=62 && LA70_0<=68)) ) {
                alt70=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2737:19: ( ruleClassDefinition | ruleElementComponentClause )", 70, 0, input);

                throw nvae;
            }
            switch (alt70) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2737:21: ruleClassDefinition
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getElementReplaceableClauseAccess().getElementClassDefinitionParserRuleCall_1_0_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleClassDefinition_in_ruleElementReplaceableClause6165);
                    ruleClassDefinition();
                    _fsp--;

                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2744:7: ruleElementComponentClause
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getElementReplaceableClauseAccess().getElementElementComponentClauseParserRuleCall_1_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleElementComponentClause_in_ruleElementReplaceableClause6179);
                    ruleElementComponentClause();
                    _fsp--;

                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementReplaceableClauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "element", lv_element_1, null, lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2765:2: (lv_constraint_2= ruleconstraining_clause )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==47||LA71_0==61) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2768:6: lv_constraint_2= ruleconstraining_clause
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementReplaceableClauseAccess().getConstraintConstraining_clauseParserRuleCall_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleconstraining_clause_in_ruleElementReplaceableClause6222);
                    lv_constraint_2=ruleconstraining_clause();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementReplaceableClauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "constraint", lv_constraint_2, "constraining_clause", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

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
    // $ANTLR end ruleElementReplaceableClause


    // $ANTLR start entryRuleElementComponentClause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2793:1: entryRuleElementComponentClause returns [EObject current=null] : iv_ruleElementComponentClause= ruleElementComponentClause EOF ;
    public final EObject entryRuleElementComponentClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementComponentClause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2793:64: (iv_ruleElementComponentClause= ruleElementComponentClause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2794:2: iv_ruleElementComponentClause= ruleElementComponentClause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementComponentClauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementComponentClause_in_entryRuleElementComponentClause6260);
            iv_ruleElementComponentClause=ruleElementComponentClause();
            _fsp--;

             current =iv_ruleElementComponentClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementComponentClause6270); 

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
    // $ANTLR end entryRuleElementComponentClause


    // $ANTLR start ruleElementComponentClause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2801:1: ruleElementComponentClause returns [EObject current=null] : ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) RULE_IDENT (lv_subscripts_3= rulearray_subscripts )? (lv_modification_4= rulemodification )? (lv_comment_5= rulecomment ) ) ;
    public final EObject ruleElementComponentClause() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_typePrefix_0 = null;

        EObject lv_typeSpecifier_1 = null;

        AntlrDatatypeRuleToken lv_subscripts_3 = null;

        EObject lv_modification_4 = null;

        EObject lv_comment_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2806:6: ( ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) RULE_IDENT (lv_subscripts_3= rulearray_subscripts )? (lv_modification_4= rulemodification )? (lv_comment_5= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2807:1: ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) RULE_IDENT (lv_subscripts_3= rulearray_subscripts )? (lv_modification_4= rulemodification )? (lv_comment_5= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2807:1: ( (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) RULE_IDENT (lv_subscripts_3= rulearray_subscripts )? (lv_modification_4= rulemodification )? (lv_comment_5= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2807:2: (lv_typePrefix_0= ruletype_prefix ) (lv_typeSpecifier_1= ruletype_specifier ) RULE_IDENT (lv_subscripts_3= rulearray_subscripts )? (lv_modification_4= rulemodification )? (lv_comment_5= rulecomment )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2807:2: (lv_typePrefix_0= ruletype_prefix )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2810:6: lv_typePrefix_0= ruletype_prefix
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementComponentClauseAccess().getTypePrefixType_prefixParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruletype_prefix_in_ruleElementComponentClause6329);
            lv_typePrefix_0=ruletype_prefix();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementComponentClauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "typePrefix", lv_typePrefix_0, "type_prefix", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2828:2: (lv_typeSpecifier_1= ruletype_specifier )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2831:6: lv_typeSpecifier_1= ruletype_specifier
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementComponentClauseAccess().getTypeSpecifierType_specifierParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruletype_specifier_in_ruleElementComponentClause6367);
            lv_typeSpecifier_1=ruletype_specifier();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementComponentClauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "typeSpecifier", lv_typeSpecifier_1, "type_specifier", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleElementComponentClause6380); 
             
                createLeafNode(grammarAccess.getElementComponentClauseAccess().getIDENTTerminalRuleCall_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2853:1: (lv_subscripts_3= rulearray_subscripts )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==RULE_LBRACK) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2856:6: lv_subscripts_3= rulearray_subscripts
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementComponentClauseAccess().getSubscriptsArray_subscriptsParserRuleCall_3_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulearray_subscripts_in_ruleElementComponentClause6413);
                    lv_subscripts_3=rulearray_subscripts();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementComponentClauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "subscripts", lv_subscripts_3, "array_subscripts", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2874:3: (lv_modification_4= rulemodification )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( ((LA73_0>=RULE_EQUALS && LA73_0<=RULE_LPAR)||LA73_0==RULE_ASSIGN) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2877:6: lv_modification_4= rulemodification
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementComponentClauseAccess().getModificationModificationParserRuleCall_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulemodification_in_ruleElementComponentClause6452);
                    lv_modification_4=rulemodification();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementComponentClauseRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "modification", lv_modification_4, "modification", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2895:3: (lv_comment_5= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2898:6: lv_comment_5= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementComponentClauseAccess().getCommentCommentParserRuleCall_5_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleElementComponentClause6491);
            lv_comment_5=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementComponentClauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_5, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end ruleElementComponentClause


    // $ANTLR start entryRuleequation_rule_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2923:1: entryRuleequation_rule_clause returns [EObject current=null] : iv_ruleequation_rule_clause= ruleequation_rule_clause EOF ;
    public final EObject entryRuleequation_rule_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleequation_rule_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2923:62: (iv_ruleequation_rule_clause= ruleequation_rule_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2924:2: iv_ruleequation_rule_clause= ruleequation_rule_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEquation_rule_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleequation_rule_clause_in_entryRuleequation_rule_clause6528);
            iv_ruleequation_rule_clause=ruleequation_rule_clause();
            _fsp--;

             current =iv_ruleequation_rule_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleequation_rule_clause6538); 

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
    // $ANTLR end entryRuleequation_rule_clause


    // $ANTLR start ruleequation_rule_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2931:1: ruleequation_rule_clause returns [EObject current=null] : ( 'equation' ( ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ ) ;
    public final EObject ruleequation_rule_clause() throws RecognitionException {
        EObject current = null;

        EObject lv_equationRules_1 = null;

        EObject lv_annotations_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2936:6: ( ( 'equation' ( ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2937:1: ( 'equation' ( ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2937:1: ( 'equation' ( ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2937:2: 'equation' ( ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+
            {
            match(input,71,FOLLOW_71_in_ruleequation_rule_clause6572); 

                    createLeafNode(grammarAccess.getEquation_rule_clauseAccess().getEquationKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2941:1: ( ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+
            int cnt74=0;
            loop74:
            do {
                int alt74=3;
                switch ( input.LA(1) ) {
                case 48:
                    {
                    int LA74_2 = input.LA(2);

                    if ( (LA74_2==RULE_SEMICOLON||LA74_2==RULE_EQUALS||LA74_2==RULE_COLON||LA74_2==RULE_STRING||(LA74_2>=RULE_LESS && LA74_2<=RULE_POWER_EW)||(LA74_2>=86 && LA74_2<=87)||LA74_2==91) ) {
                        alt74=1;
                    }


                    }
                    break;
                case 73:
                    {
                    int LA74_3 = input.LA(2);

                    if ( (LA74_3==RULE_LPAR) ) {
                        alt74=1;
                    }


                    }
                    break;
                case RULE_IDENT:
                case RULE_LPAR:
                case RULE_STRING:
                case RULE_PLUS:
                case RULE_MINUS:
                case RULE_PLUS_EW:
                case RULE_MINUS_EW:
                case RULE_UNSIGNED_INTEGER:
                case RULE_UNSIGNED_REAL:
                case RULE_LBRACK:
                case RULE_LBRACE:
                case 50:
                case 69:
                case 78:
                case 81:
                case 84:
                case 88:
                case 89:
                case 90:
                    {
                    alt74=1;
                    }
                    break;
                case 91:
                    {
                    alt74=2;
                    }
                    break;

                }

                switch (alt74) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2941:2: ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2941:2: ( (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2941:3: (lv_equationRules_1= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2941:3: (lv_equationRules_1= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2944:6: lv_equationRules_1= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getEquation_rule_clauseAccess().getEquationRulesEquation_ruleParserRuleCall_1_0_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_ruleequation_rule_clause6608);
            	    lv_equationRules_1=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getEquation_rule_clauseRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "equationRules", lv_equationRules_1, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleequation_rule_clause6621); 
            	     
            	        createLeafNode(grammarAccess.getEquation_rule_clauseAccess().getSEMICOLONTerminalRuleCall_1_0_1(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2967:6: ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2967:6: ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2967:7: (lv_annotations_3= ruleannotation ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2967:7: (lv_annotations_3= ruleannotation )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2970:6: lv_annotations_3= ruleannotation
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getEquation_rule_clauseAccess().getAnnotationsAnnotationParserRuleCall_1_1_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleannotation_in_ruleequation_rule_clause6662);
            	    lv_annotations_3=ruleannotation();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getEquation_rule_clauseRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "annotations", lv_annotations_3, "annotation", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleequation_rule_clause6675); 
            	     
            	        createLeafNode(grammarAccess.getEquation_rule_clauseAccess().getSEMICOLONTerminalRuleCall_1_1_1(), null); 
            	        

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt74 >= 1 ) break loop74;
                        EarlyExitException eee =
                            new EarlyExitException(74, input);
                        throw eee;
                }
                cnt74++;
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
    // $ANTLR end ruleequation_rule_clause


    // $ANTLR start entryRulealgorithm_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2999:1: entryRulealgorithm_clause returns [EObject current=null] : iv_rulealgorithm_clause= rulealgorithm_clause EOF ;
    public final EObject entryRulealgorithm_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulealgorithm_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:2999:58: (iv_rulealgorithm_clause= rulealgorithm_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3000:2: iv_rulealgorithm_clause= rulealgorithm_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAlgorithm_clauseRule(), currentNode); 
            pushFollow(FOLLOW_rulealgorithm_clause_in_entryRulealgorithm_clause6710);
            iv_rulealgorithm_clause=rulealgorithm_clause();
            _fsp--;

             current =iv_rulealgorithm_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulealgorithm_clause6720); 

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
    // $ANTLR end entryRulealgorithm_clause


    // $ANTLR start rulealgorithm_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3007:1: rulealgorithm_clause returns [EObject current=null] : ( 'algorithm' ( ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ ) ;
    public final EObject rulealgorithm_clause() throws RecognitionException {
        EObject current = null;

        EObject lv_algorithmRules_1 = null;

        EObject lv_annotations_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3012:6: ( ( 'algorithm' ( ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3013:1: ( 'algorithm' ( ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3013:1: ( 'algorithm' ( ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+ )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3013:2: 'algorithm' ( ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+
            {
            match(input,72,FOLLOW_72_in_rulealgorithm_clause6754); 

                    createLeafNode(grammarAccess.getAlgorithm_clauseAccess().getAlgorithmKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3017:1: ( ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON ) | ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON ) )+
            int cnt75=0;
            loop75:
            do {
                int alt75=3;
                switch ( input.LA(1) ) {
                case 48:
                    {
                    int LA75_2 = input.LA(2);

                    if ( (LA75_2==RULE_SEMICOLON||LA75_2==RULE_EQUALS||LA75_2==RULE_COLON||(LA75_2>=RULE_STRING && LA75_2<=RULE_ASSIGN)||(LA75_2>=RULE_LESS && LA75_2<=RULE_POWER_EW)||(LA75_2>=86 && LA75_2<=87)||LA75_2==91) ) {
                        alt75=1;
                    }


                    }
                    break;
                case 73:
                    {
                    int LA75_3 = input.LA(2);

                    if ( (LA75_3==RULE_LPAR) ) {
                        alt75=1;
                    }


                    }
                    break;
                case RULE_IDENT:
                case RULE_LPAR:
                case RULE_STRING:
                case RULE_PLUS:
                case RULE_MINUS:
                case RULE_PLUS_EW:
                case RULE_MINUS_EW:
                case RULE_UNSIGNED_INTEGER:
                case RULE_UNSIGNED_REAL:
                case RULE_LBRACK:
                case RULE_LBRACE:
                case 50:
                case 69:
                case 74:
                case 75:
                case 78:
                case 80:
                case 81:
                case 88:
                case 89:
                case 90:
                    {
                    alt75=1;
                    }
                    break;
                case 91:
                    {
                    alt75=2;
                    }
                    break;

                }

                switch (alt75) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3017:2: ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3017:2: ( (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3017:3: (lv_algorithmRules_1= rulealgorithm ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3017:3: (lv_algorithmRules_1= rulealgorithm )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3020:6: lv_algorithmRules_1= rulealgorithm
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAlgorithm_clauseAccess().getAlgorithmRulesAlgorithmParserRuleCall_1_0_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulealgorithm_in_rulealgorithm_clause6790);
            	    lv_algorithmRules_1=rulealgorithm();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAlgorithm_clauseRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "algorithmRules", lv_algorithmRules_1, "algorithm", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulealgorithm_clause6803); 
            	     
            	        createLeafNode(grammarAccess.getAlgorithm_clauseAccess().getSEMICOLONTerminalRuleCall_1_0_1(), null); 
            	        

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3043:6: ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON )
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3043:6: ( (lv_annotations_3= ruleannotation ) RULE_SEMICOLON )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3043:7: (lv_annotations_3= ruleannotation ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3043:7: (lv_annotations_3= ruleannotation )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3046:6: lv_annotations_3= ruleannotation
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAlgorithm_clauseAccess().getAnnotationsAnnotationParserRuleCall_1_1_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleannotation_in_rulealgorithm_clause6844);
            	    lv_annotations_3=ruleannotation();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAlgorithm_clauseRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "annotations", lv_annotations_3, "annotation", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulealgorithm_clause6857); 
            	     
            	        createLeafNode(grammarAccess.getAlgorithm_clauseAccess().getSEMICOLONTerminalRuleCall_1_1_1(), null); 
            	        

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt75 >= 1 ) break loop75;
                        EarlyExitException eee =
                            new EarlyExitException(75, input);
                        throw eee;
                }
                cnt75++;
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
    // $ANTLR end rulealgorithm_clause


    // $ANTLR start entryRuleinitial_equation_rule_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3075:1: entryRuleinitial_equation_rule_clause returns [EObject current=null] : iv_ruleinitial_equation_rule_clause= ruleinitial_equation_rule_clause EOF ;
    public final EObject entryRuleinitial_equation_rule_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitial_equation_rule_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3075:70: (iv_ruleinitial_equation_rule_clause= ruleinitial_equation_rule_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3076:2: iv_ruleinitial_equation_rule_clause= ruleinitial_equation_rule_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInitial_equation_rule_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleinitial_equation_rule_clause_in_entryRuleinitial_equation_rule_clause6892);
            iv_ruleinitial_equation_rule_clause=ruleinitial_equation_rule_clause();
            _fsp--;

             current =iv_ruleinitial_equation_rule_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleinitial_equation_rule_clause6902); 

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
    // $ANTLR end entryRuleinitial_equation_rule_clause


    // $ANTLR start ruleinitial_equation_rule_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3083:1: ruleinitial_equation_rule_clause returns [EObject current=null] : ( 'initial' this_equation_rule_clause_1= ruleequation_rule_clause ) ;
    public final EObject ruleinitial_equation_rule_clause() throws RecognitionException {
        EObject current = null;

        EObject this_equation_rule_clause_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3088:6: ( ( 'initial' this_equation_rule_clause_1= ruleequation_rule_clause ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3089:1: ( 'initial' this_equation_rule_clause_1= ruleequation_rule_clause )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3089:1: ( 'initial' this_equation_rule_clause_1= ruleequation_rule_clause )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3089:2: 'initial' this_equation_rule_clause_1= ruleequation_rule_clause
            {
            match(input,73,FOLLOW_73_in_ruleinitial_equation_rule_clause6936); 

                    createLeafNode(grammarAccess.getInitial_equation_rule_clauseAccess().getInitialKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getInitial_equation_rule_clauseAccess().getEquation_rule_clauseParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleequation_rule_clause_in_ruleinitial_equation_rule_clause6958);
            this_equation_rule_clause_1=ruleequation_rule_clause();
            _fsp--;

             
                    current = this_equation_rule_clause_1; 
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
    // $ANTLR end ruleinitial_equation_rule_clause


    // $ANTLR start entryRuleinitial_algorithm_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3109:1: entryRuleinitial_algorithm_clause returns [EObject current=null] : iv_ruleinitial_algorithm_clause= ruleinitial_algorithm_clause EOF ;
    public final EObject entryRuleinitial_algorithm_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitial_algorithm_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3109:66: (iv_ruleinitial_algorithm_clause= ruleinitial_algorithm_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3110:2: iv_ruleinitial_algorithm_clause= ruleinitial_algorithm_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInitial_algorithm_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleinitial_algorithm_clause_in_entryRuleinitial_algorithm_clause6990);
            iv_ruleinitial_algorithm_clause=ruleinitial_algorithm_clause();
            _fsp--;

             current =iv_ruleinitial_algorithm_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleinitial_algorithm_clause7000); 

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
    // $ANTLR end entryRuleinitial_algorithm_clause


    // $ANTLR start ruleinitial_algorithm_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3117:1: ruleinitial_algorithm_clause returns [EObject current=null] : ( 'initial' this_algorithm_clause_1= rulealgorithm_clause ) ;
    public final EObject ruleinitial_algorithm_clause() throws RecognitionException {
        EObject current = null;

        EObject this_algorithm_clause_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3122:6: ( ( 'initial' this_algorithm_clause_1= rulealgorithm_clause ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3123:1: ( 'initial' this_algorithm_clause_1= rulealgorithm_clause )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3123:1: ( 'initial' this_algorithm_clause_1= rulealgorithm_clause )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3123:2: 'initial' this_algorithm_clause_1= rulealgorithm_clause
            {
            match(input,73,FOLLOW_73_in_ruleinitial_algorithm_clause7034); 

                    createLeafNode(grammarAccess.getInitial_algorithm_clauseAccess().getInitialKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getInitial_algorithm_clauseAccess().getAlgorithm_clauseParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_rulealgorithm_clause_in_ruleinitial_algorithm_clause7056);
            this_algorithm_clause_1=rulealgorithm_clause();
            _fsp--;

             
                    current = this_algorithm_clause_1; 
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
    // $ANTLR end ruleinitial_algorithm_clause


    // $ANTLR start entryRuleequation_rule
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3143:1: entryRuleequation_rule returns [EObject current=null] : iv_ruleequation_rule= ruleequation_rule EOF ;
    public final EObject entryRuleequation_rule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleequation_rule = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3143:55: (iv_ruleequation_rule= ruleequation_rule EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3144:2: iv_ruleequation_rule= ruleequation_rule EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEquation_ruleRule(), currentNode); 
            pushFollow(FOLLOW_ruleequation_rule_in_entryRuleequation_rule7088);
            iv_ruleequation_rule=ruleequation_rule();
            _fsp--;

             current =iv_ruleequation_rule; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleequation_rule7098); 

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
    // $ANTLR end entryRuleequation_rule


    // $ANTLR start ruleequation_rule
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3151:1: ruleequation_rule returns [EObject current=null] : ( ( ruleequality_equation_rule | this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e | this_for_clause_e_2= rulefor_clause_e | ruleconnect_clause | this_when_clause_e_4= rulewhen_clause_e ) (lv_comment_5= rulecomment ) ) ;
    public final EObject ruleequation_rule() throws RecognitionException {
        EObject current = null;

        EObject this_conditional_equation_rule_e_1 = null;

        EObject this_for_clause_e_2 = null;

        EObject this_when_clause_e_4 = null;

        EObject lv_comment_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3156:6: ( ( ( ruleequality_equation_rule | this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e | this_for_clause_e_2= rulefor_clause_e | ruleconnect_clause | this_when_clause_e_4= rulewhen_clause_e ) (lv_comment_5= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3157:1: ( ( ruleequality_equation_rule | this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e | this_for_clause_e_2= rulefor_clause_e | ruleconnect_clause | this_when_clause_e_4= rulewhen_clause_e ) (lv_comment_5= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3157:1: ( ( ruleequality_equation_rule | this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e | this_for_clause_e_2= rulefor_clause_e | ruleconnect_clause | this_when_clause_e_4= rulewhen_clause_e ) (lv_comment_5= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3157:2: ( ruleequality_equation_rule | this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e | this_for_clause_e_2= rulefor_clause_e | ruleconnect_clause | this_when_clause_e_4= rulewhen_clause_e ) (lv_comment_5= rulecomment )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3157:2: ( ruleequality_equation_rule | this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e | this_for_clause_e_2= rulefor_clause_e | ruleconnect_clause | this_when_clause_e_4= rulewhen_clause_e )
            int alt76=5;
            switch ( input.LA(1) ) {
            case RULE_IDENT:
            case RULE_LPAR:
            case RULE_STRING:
            case RULE_PLUS:
            case RULE_MINUS:
            case RULE_PLUS_EW:
            case RULE_MINUS_EW:
            case RULE_UNSIGNED_INTEGER:
            case RULE_UNSIGNED_REAL:
            case RULE_LBRACK:
            case RULE_LBRACE:
            case 48:
            case 50:
            case 73:
            case 88:
            case 89:
            case 90:
                {
                alt76=1;
                }
                break;
            case 69:
                {
                alt76=2;
                }
                break;
            case 78:
                {
                alt76=3;
                }
                break;
            case 84:
                {
                alt76=4;
                }
                break;
            case 81:
                {
                alt76=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("3157:2: ( ruleequality_equation_rule | this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e | this_for_clause_e_2= rulefor_clause_e | ruleconnect_clause | this_when_clause_e_4= rulewhen_clause_e )", 76, 0, input);

                throw nvae;
            }

            switch (alt76) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3157:3: ruleequality_equation_rule
                    {
                    pushFollow(FOLLOW_ruleequality_equation_rule_in_ruleequation_rule7133);
                    ruleequality_equation_rule();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3159:5: this_conditional_equation_rule_e_1= ruleconditional_equation_rule_e
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEquation_ruleAccess().getConditional_equation_rule_eParserRuleCall_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleconditional_equation_rule_e_in_ruleequation_rule7153);
                    this_conditional_equation_rule_e_1=ruleconditional_equation_rule_e();
                    _fsp--;

                     
                            current = this_conditional_equation_rule_e_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3169:5: this_for_clause_e_2= rulefor_clause_e
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEquation_ruleAccess().getFor_clause_eParserRuleCall_0_2(), currentNode); 
                        
                    pushFollow(FOLLOW_rulefor_clause_e_in_ruleequation_rule7180);
                    this_for_clause_e_2=rulefor_clause_e();
                    _fsp--;

                     
                            current = this_for_clause_e_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3178:6: ruleconnect_clause
                    {
                    pushFollow(FOLLOW_ruleconnect_clause_in_ruleequation_rule7194);
                    ruleconnect_clause();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3180:5: this_when_clause_e_4= rulewhen_clause_e
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getEquation_ruleAccess().getWhen_clause_eParserRuleCall_0_4(), currentNode); 
                        
                    pushFollow(FOLLOW_rulewhen_clause_e_in_ruleequation_rule7214);
                    this_when_clause_e_4=rulewhen_clause_e();
                    _fsp--;

                     
                            current = this_when_clause_e_4; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3188:2: (lv_comment_5= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3191:6: lv_comment_5= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEquation_ruleAccess().getCommentCommentParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_ruleequation_rule7248);
            lv_comment_5=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEquation_ruleRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_5, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end ruleequation_rule


    // $ANTLR start entryRulealgorithm
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3216:1: entryRulealgorithm returns [EObject current=null] : iv_rulealgorithm= rulealgorithm EOF ;
    public final EObject entryRulealgorithm() throws RecognitionException {
        EObject current = null;

        EObject iv_rulealgorithm = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3216:51: (iv_rulealgorithm= rulealgorithm EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3217:2: iv_rulealgorithm= rulealgorithm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAlgorithmRule(), currentNode); 
            pushFollow(FOLLOW_rulealgorithm_in_entryRulealgorithm7285);
            iv_rulealgorithm=rulealgorithm();
            _fsp--;

             current =iv_rulealgorithm; 
            match(input,EOF,FOLLOW_EOF_in_entryRulealgorithm7295); 

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
    // $ANTLR end entryRulealgorithm


    // $ANTLR start rulealgorithm
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3224:1: rulealgorithm returns [EObject current=null] : ( ( ruleassign_clause_a | this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a | this_for_clause_a_2= rulefor_clause_a | this_while_clause_3= rulewhile_clause | this_when_clause_a_4= rulewhen_clause_a | 'break' | 'return' ) (lv_comment_7= rulecomment ) ) ;
    public final EObject rulealgorithm() throws RecognitionException {
        EObject current = null;

        EObject this_conditional_equation_rule_a_1 = null;

        EObject this_for_clause_a_2 = null;

        EObject this_while_clause_3 = null;

        EObject this_when_clause_a_4 = null;

        EObject lv_comment_7 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3229:6: ( ( ( ruleassign_clause_a | this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a | this_for_clause_a_2= rulefor_clause_a | this_while_clause_3= rulewhile_clause | this_when_clause_a_4= rulewhen_clause_a | 'break' | 'return' ) (lv_comment_7= rulecomment ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3230:1: ( ( ruleassign_clause_a | this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a | this_for_clause_a_2= rulefor_clause_a | this_while_clause_3= rulewhile_clause | this_when_clause_a_4= rulewhen_clause_a | 'break' | 'return' ) (lv_comment_7= rulecomment ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3230:1: ( ( ruleassign_clause_a | this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a | this_for_clause_a_2= rulefor_clause_a | this_while_clause_3= rulewhile_clause | this_when_clause_a_4= rulewhen_clause_a | 'break' | 'return' ) (lv_comment_7= rulecomment ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3230:2: ( ruleassign_clause_a | this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a | this_for_clause_a_2= rulefor_clause_a | this_while_clause_3= rulewhile_clause | this_when_clause_a_4= rulewhen_clause_a | 'break' | 'return' ) (lv_comment_7= rulecomment )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3230:2: ( ruleassign_clause_a | this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a | this_for_clause_a_2= rulefor_clause_a | this_while_clause_3= rulewhile_clause | this_when_clause_a_4= rulewhen_clause_a | 'break' | 'return' )
            int alt77=7;
            switch ( input.LA(1) ) {
            case RULE_IDENT:
            case RULE_LPAR:
            case RULE_STRING:
            case RULE_PLUS:
            case RULE_MINUS:
            case RULE_PLUS_EW:
            case RULE_MINUS_EW:
            case RULE_UNSIGNED_INTEGER:
            case RULE_UNSIGNED_REAL:
            case RULE_LBRACK:
            case RULE_LBRACE:
            case 48:
            case 50:
            case 73:
            case 88:
            case 89:
            case 90:
                {
                alt77=1;
                }
                break;
            case 69:
                {
                alt77=2;
                }
                break;
            case 78:
                {
                alt77=3;
                }
                break;
            case 80:
                {
                alt77=4;
                }
                break;
            case 81:
                {
                alt77=5;
                }
                break;
            case 74:
                {
                alt77=6;
                }
                break;
            case 75:
                {
                alt77=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("3230:2: ( ruleassign_clause_a | this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a | this_for_clause_a_2= rulefor_clause_a | this_while_clause_3= rulewhile_clause | this_when_clause_a_4= rulewhen_clause_a | 'break' | 'return' )", 77, 0, input);

                throw nvae;
            }

            switch (alt77) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3230:3: ruleassign_clause_a
                    {
                    pushFollow(FOLLOW_ruleassign_clause_a_in_rulealgorithm7330);
                    ruleassign_clause_a();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3232:5: this_conditional_equation_rule_a_1= ruleconditional_equation_rule_a
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAlgorithmAccess().getConditional_equation_rule_aParserRuleCall_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleconditional_equation_rule_a_in_rulealgorithm7350);
                    this_conditional_equation_rule_a_1=ruleconditional_equation_rule_a();
                    _fsp--;

                     
                            current = this_conditional_equation_rule_a_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3242:5: this_for_clause_a_2= rulefor_clause_a
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAlgorithmAccess().getFor_clause_aParserRuleCall_0_2(), currentNode); 
                        
                    pushFollow(FOLLOW_rulefor_clause_a_in_rulealgorithm7377);
                    this_for_clause_a_2=rulefor_clause_a();
                    _fsp--;

                     
                            current = this_for_clause_a_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3252:5: this_while_clause_3= rulewhile_clause
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAlgorithmAccess().getWhile_clauseParserRuleCall_0_3(), currentNode); 
                        
                    pushFollow(FOLLOW_rulewhile_clause_in_rulealgorithm7404);
                    this_while_clause_3=rulewhile_clause();
                    _fsp--;

                     
                            current = this_while_clause_3; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 5 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3262:5: this_when_clause_a_4= rulewhen_clause_a
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getAlgorithmAccess().getWhen_clause_aParserRuleCall_0_4(), currentNode); 
                        
                    pushFollow(FOLLOW_rulewhen_clause_a_in_rulealgorithm7431);
                    this_when_clause_a_4=rulewhen_clause_a();
                    _fsp--;

                     
                            current = this_when_clause_a_4; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 6 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3271:6: 'break'
                    {
                    match(input,74,FOLLOW_74_in_rulealgorithm7445); 

                            createLeafNode(grammarAccess.getAlgorithmAccess().getBreakKeyword_0_5(), null); 
                        

                    }
                    break;
                case 7 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3276:6: 'return'
                    {
                    match(input,75,FOLLOW_75_in_rulealgorithm7460); 

                            createLeafNode(grammarAccess.getAlgorithmAccess().getReturnKeyword_0_6(), null); 
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3280:2: (lv_comment_7= rulecomment )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3283:6: lv_comment_7= rulecomment
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAlgorithmAccess().getCommentCommentParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulecomment_in_rulealgorithm7495);
            lv_comment_7=rulecomment();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAlgorithmRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "comment", lv_comment_7, "comment", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

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
    // $ANTLR end rulealgorithm


    // $ANTLR start entryRuleassign_clause_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3308:1: entryRuleassign_clause_a returns [String current=null] : iv_ruleassign_clause_a= ruleassign_clause_a EOF ;
    public final String entryRuleassign_clause_a() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleassign_clause_a = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3308:56: (iv_ruleassign_clause_a= ruleassign_clause_a EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3309:2: iv_ruleassign_clause_a= ruleassign_clause_a EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAssign_clause_aRule(), currentNode); 
            pushFollow(FOLLOW_ruleassign_clause_a_in_entryRuleassign_clause_a7533);
            iv_ruleassign_clause_a=ruleassign_clause_a();
            _fsp--;

             current =iv_ruleassign_clause_a.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleassign_clause_a7544); 

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
    // $ANTLR end entryRuleassign_clause_a


    // $ANTLR start ruleassign_clause_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3316:1: ruleassign_clause_a returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_simple_expression_0= rulesimple_expression ( (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression ) | (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression ) )? ) ;
    public final AntlrDatatypeRuleToken ruleassign_clause_a() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ASSIGN_1=null;
        Token this_EQUALS_3=null;
        AntlrDatatypeRuleToken this_simple_expression_0 = null;

        AntlrDatatypeRuleToken this_expression_2 = null;

        AntlrDatatypeRuleToken this_expression_4 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3322:6: ( (this_simple_expression_0= rulesimple_expression ( (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression ) | (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression ) )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3323:1: (this_simple_expression_0= rulesimple_expression ( (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression ) | (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression ) )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3323:1: (this_simple_expression_0= rulesimple_expression ( (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression ) | (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression ) )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3324:5: this_simple_expression_0= rulesimple_expression ( (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression ) | (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getAssign_clause_aAccess().getSimple_expressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulesimple_expression_in_ruleassign_clause_a7591);
            this_simple_expression_0=rulesimple_expression();
            _fsp--;


            		current.merge(this_simple_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3334:1: ( (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression ) | (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression ) )?
            int alt78=3;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==RULE_ASSIGN) ) {
                alt78=1;
            }
            else if ( (LA78_0==RULE_EQUALS) ) {
                alt78=2;
            }
            switch (alt78) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3334:2: (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3334:2: (this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3334:7: this_ASSIGN_1= RULE_ASSIGN this_expression_2= ruleexpression
                    {
                    this_ASSIGN_1=(Token)input.LT(1);
                    match(input,RULE_ASSIGN,FOLLOW_RULE_ASSIGN_in_ruleassign_clause_a7613); 

                    		current.merge(this_ASSIGN_1);
                        
                     
                        createLeafNode(grammarAccess.getAssign_clause_aAccess().getASSIGNTerminalRuleCall_1_0_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getAssign_clause_aAccess().getExpressionParserRuleCall_1_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_in_ruleassign_clause_a7640);
                    this_expression_2=ruleexpression();
                    _fsp--;


                    		current.merge(this_expression_2);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3353:6: (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3353:6: (this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3353:11: this_EQUALS_3= RULE_EQUALS this_expression_4= ruleexpression
                    {
                    this_EQUALS_3=(Token)input.LT(1);
                    match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleassign_clause_a7668); 

                    		current.merge(this_EQUALS_3);
                        
                     
                        createLeafNode(grammarAccess.getAssign_clause_aAccess().getEQUALSTerminalRuleCall_1_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getAssign_clause_aAccess().getExpressionParserRuleCall_1_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_in_ruleassign_clause_a7695);
                    this_expression_4=ruleexpression();
                    _fsp--;


                    		current.merge(this_expression_4);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleassign_clause_a


    // $ANTLR start entryRuleequality_equation_rule
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3379:1: entryRuleequality_equation_rule returns [String current=null] : iv_ruleequality_equation_rule= ruleequality_equation_rule EOF ;
    public final String entryRuleequality_equation_rule() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleequality_equation_rule = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3379:63: (iv_ruleequality_equation_rule= ruleequality_equation_rule EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3380:2: iv_ruleequality_equation_rule= ruleequality_equation_rule EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEquality_equation_ruleRule(), currentNode); 
            pushFollow(FOLLOW_ruleequality_equation_rule_in_entryRuleequality_equation_rule7742);
            iv_ruleequality_equation_rule=ruleequality_equation_rule();
            _fsp--;

             current =iv_ruleequality_equation_rule.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleequality_equation_rule7753); 

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
    // $ANTLR end entryRuleequality_equation_rule


    // $ANTLR start ruleequality_equation_rule
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3387:1: ruleequality_equation_rule returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_simple_expression_0= rulesimple_expression (this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )? ) ;
    public final AntlrDatatypeRuleToken ruleequality_equation_rule() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_EQUALS_1=null;
        AntlrDatatypeRuleToken this_simple_expression_0 = null;

        AntlrDatatypeRuleToken this_expression_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3393:6: ( (this_simple_expression_0= rulesimple_expression (this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3394:1: (this_simple_expression_0= rulesimple_expression (this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3394:1: (this_simple_expression_0= rulesimple_expression (this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3395:5: this_simple_expression_0= rulesimple_expression (this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getEquality_equation_ruleAccess().getSimple_expressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulesimple_expression_in_ruleequality_equation_rule7800);
            this_simple_expression_0=rulesimple_expression();
            _fsp--;


            		current.merge(this_simple_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3405:1: (this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==RULE_EQUALS) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3405:6: this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression
                    {
                    this_EQUALS_1=(Token)input.LT(1);
                    match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_ruleequality_equation_rule7821); 

                    		current.merge(this_EQUALS_1);
                        
                     
                        createLeafNode(grammarAccess.getEquality_equation_ruleAccess().getEQUALSTerminalRuleCall_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getEquality_equation_ruleAccess().getExpressionParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_in_ruleequality_equation_rule7848);
                    this_expression_2=ruleexpression();
                    _fsp--;


                    		current.merge(this_expression_2);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleequality_equation_rule


    // $ANTLR start entryRuleconditional_equation_rule_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3431:1: entryRuleconditional_equation_rule_e returns [EObject current=null] : iv_ruleconditional_equation_rule_e= ruleconditional_equation_rule_e EOF ;
    public final EObject entryRuleconditional_equation_rule_e() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_equation_rule_e = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3431:69: (iv_ruleconditional_equation_rule_e= ruleconditional_equation_rule_e EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3432:2: iv_ruleconditional_equation_rule_e= ruleconditional_equation_rule_e EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConditional_equation_rule_eRule(), currentNode); 
            pushFollow(FOLLOW_ruleconditional_equation_rule_e_in_entryRuleconditional_equation_rule_e7893);
            iv_ruleconditional_equation_rule_e=ruleconditional_equation_rule_e();
            _fsp--;

             current =iv_ruleconditional_equation_rule_e; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_equation_rule_e7903); 

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
    // $ANTLR end entryRuleconditional_equation_rule_e


    // $ANTLR start ruleconditional_equation_rule_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3439:1: ruleconditional_equation_rule_e returns [EObject current=null] : ( 'if' ruleexpression 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseifClauses_5= ruleequation_rule_elseif )* ( 'else' ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )* )? 'end' 'if' ) ;
    public final EObject ruleconditional_equation_rule_e() throws RecognitionException {
        EObject current = null;

        EObject lv_thenEquationClauses_3 = null;

        EObject lv_elseifClauses_5 = null;

        EObject lv_elseEquationClauses_7 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3444:6: ( ( 'if' ruleexpression 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseifClauses_5= ruleequation_rule_elseif )* ( 'else' ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )* )? 'end' 'if' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3445:1: ( 'if' ruleexpression 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseifClauses_5= ruleequation_rule_elseif )* ( 'else' ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )* )? 'end' 'if' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3445:1: ( 'if' ruleexpression 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseifClauses_5= ruleequation_rule_elseif )* ( 'else' ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )* )? 'end' 'if' )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3445:2: 'if' ruleexpression 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseifClauses_5= ruleequation_rule_elseif )* ( 'else' ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )* )? 'end' 'if'
            {
            match(input,69,FOLLOW_69_in_ruleconditional_equation_rule_e7937); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_eAccess().getIfKeyword_0(), null); 
                
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_equation_rule_e7946);
            ruleexpression();
            _fsp--;

            match(input,76,FOLLOW_76_in_ruleconditional_equation_rule_e7947); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_eAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3453:1: ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==48) ) {
                    int LA80_2 = input.LA(2);

                    if ( (LA80_2==RULE_SEMICOLON||LA80_2==RULE_EQUALS||LA80_2==RULE_COLON||LA80_2==RULE_STRING||(LA80_2>=RULE_LESS && LA80_2<=RULE_POWER_EW)||(LA80_2>=86 && LA80_2<=87)||LA80_2==91) ) {
                        alt80=1;
                    }


                }
                else if ( (LA80_0==RULE_IDENT||LA80_0==RULE_LPAR||LA80_0==RULE_STRING||(LA80_0>=RULE_PLUS && LA80_0<=RULE_MINUS_EW)||(LA80_0>=RULE_UNSIGNED_INTEGER && LA80_0<=RULE_LBRACK)||LA80_0==RULE_LBRACE||LA80_0==50||LA80_0==69||LA80_0==73||LA80_0==78||LA80_0==81||LA80_0==84||(LA80_0>=88 && LA80_0<=90)) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3453:2: (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3453:2: (lv_thenEquationClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3456:6: lv_thenEquationClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_equation_rule_eAccess().getThenEquationClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_ruleconditional_equation_rule_e7982);
            	    lv_thenEquationClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getConditional_equation_rule_eRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenEquationClauses", lv_thenEquationClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_e7995); 
            	     
            	        createLeafNode(grammarAccess.getConditional_equation_rule_eAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3478:3: (lv_elseifClauses_5= ruleequation_rule_elseif )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==83) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3481:6: lv_elseifClauses_5= ruleequation_rule_elseif
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_equation_rule_eAccess().getElseifClausesEquation_rule_elseifParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_elseif_in_ruleconditional_equation_rule_e8030);
            	    lv_elseifClauses_5=ruleequation_rule_elseif();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getConditional_equation_rule_eRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elseifClauses", lv_elseifClauses_5, "equation_rule_elseif", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3499:3: ( 'else' ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )* )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==77) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3499:4: 'else' ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )*
                    {
                    match(input,77,FOLLOW_77_in_ruleconditional_equation_rule_e8045); 

                            createLeafNode(grammarAccess.getConditional_equation_rule_eAccess().getElseKeyword_5_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3503:1: ( (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==48) ) {
                            int LA82_1 = input.LA(2);

                            if ( (LA82_1==RULE_SEMICOLON||LA82_1==RULE_EQUALS||LA82_1==RULE_COLON||LA82_1==RULE_STRING||(LA82_1>=RULE_LESS && LA82_1<=RULE_POWER_EW)||(LA82_1>=86 && LA82_1<=87)||LA82_1==91) ) {
                                alt82=1;
                            }


                        }
                        else if ( (LA82_0==RULE_IDENT||LA82_0==RULE_LPAR||LA82_0==RULE_STRING||(LA82_0>=RULE_PLUS && LA82_0<=RULE_MINUS_EW)||(LA82_0>=RULE_UNSIGNED_INTEGER && LA82_0<=RULE_LBRACK)||LA82_0==RULE_LBRACE||LA82_0==50||LA82_0==69||LA82_0==73||LA82_0==78||LA82_0==81||LA82_0==84||(LA82_0>=88 && LA82_0<=90)) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3503:2: (lv_elseEquationClauses_7= ruleequation_rule ) RULE_SEMICOLON
                    	    {
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3503:2: (lv_elseEquationClauses_7= ruleequation_rule )
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3506:6: lv_elseEquationClauses_7= ruleequation_rule
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getConditional_equation_rule_eAccess().getElseEquationClausesEquation_ruleParserRuleCall_5_1_0_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleequation_rule_in_ruleconditional_equation_rule_e8080);
                    	    lv_elseEquationClauses_7=ruleequation_rule();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getConditional_equation_rule_eRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "elseEquationClauses", lv_elseEquationClauses_7, "equation_rule", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }

                    	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_e8093); 
                    	     
                    	        createLeafNode(grammarAccess.getConditional_equation_rule_eAccess().getSEMICOLONTerminalRuleCall_5_1_1(), null); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,48,FOLLOW_48_in_ruleconditional_equation_rule_e8105); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_eAccess().getEndKeyword_6(), null); 
                
            match(input,69,FOLLOW_69_in_ruleconditional_equation_rule_e8114); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_eAccess().getIfKeyword_7(), null); 
                

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
    // $ANTLR end ruleconditional_equation_rule_e


    // $ANTLR start entryRuleconditional_equation_rule_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3543:1: entryRuleconditional_equation_rule_a returns [EObject current=null] : iv_ruleconditional_equation_rule_a= ruleconditional_equation_rule_a EOF ;
    public final EObject entryRuleconditional_equation_rule_a() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleconditional_equation_rule_a = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3543:69: (iv_ruleconditional_equation_rule_a= ruleconditional_equation_rule_a EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3544:2: iv_ruleconditional_equation_rule_a= ruleconditional_equation_rule_a EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConditional_equation_rule_aRule(), currentNode); 
            pushFollow(FOLLOW_ruleconditional_equation_rule_a_in_entryRuleconditional_equation_rule_a8147);
            iv_ruleconditional_equation_rule_a=ruleconditional_equation_rule_a();
            _fsp--;

             current =iv_ruleconditional_equation_rule_a; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconditional_equation_rule_a8157); 

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
    // $ANTLR end entryRuleconditional_equation_rule_a


    // $ANTLR start ruleconditional_equation_rule_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3551:1: ruleconditional_equation_rule_a returns [EObject current=null] : ( 'if' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* (lv_elseifClauses_5= rulealgorithm_elseif )* ( 'else' ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )* )? 'end' 'if' ) ;
    public final EObject ruleconditional_equation_rule_a() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_thenAlgorithmClauses_3 = null;

        EObject lv_elseifClauses_5 = null;

        EObject lv_elseAlgotithmClauses_7 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3556:6: ( ( 'if' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* (lv_elseifClauses_5= rulealgorithm_elseif )* ( 'else' ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )* )? 'end' 'if' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3557:1: ( 'if' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* (lv_elseifClauses_5= rulealgorithm_elseif )* ( 'else' ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )* )? 'end' 'if' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3557:1: ( 'if' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* (lv_elseifClauses_5= rulealgorithm_elseif )* ( 'else' ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )* )? 'end' 'if' )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3557:2: 'if' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* (lv_elseifClauses_5= rulealgorithm_elseif )* ( 'else' ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )* )? 'end' 'if'
            {
            match(input,69,FOLLOW_69_in_ruleconditional_equation_rule_a8191); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_aAccess().getIfKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3561:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3564:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConditional_equation_rule_aAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleconditional_equation_rule_a8225);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConditional_equation_rule_aRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,76,FOLLOW_76_in_ruleconditional_equation_rule_a8238); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_aAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3586:1: ( (lv_thenAlgorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==48) ) {
                    int LA84_2 = input.LA(2);

                    if ( (LA84_2==RULE_SEMICOLON||LA84_2==RULE_EQUALS||LA84_2==RULE_COLON||(LA84_2>=RULE_STRING && LA84_2<=RULE_ASSIGN)||(LA84_2>=RULE_LESS && LA84_2<=RULE_POWER_EW)||(LA84_2>=86 && LA84_2<=87)||LA84_2==91) ) {
                        alt84=1;
                    }


                }
                else if ( (LA84_0==RULE_IDENT||LA84_0==RULE_LPAR||LA84_0==RULE_STRING||(LA84_0>=RULE_PLUS && LA84_0<=RULE_MINUS_EW)||(LA84_0>=RULE_UNSIGNED_INTEGER && LA84_0<=RULE_LBRACK)||LA84_0==RULE_LBRACE||LA84_0==50||LA84_0==69||(LA84_0>=73 && LA84_0<=75)||LA84_0==78||(LA84_0>=80 && LA84_0<=81)||(LA84_0>=88 && LA84_0<=90)) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3586:2: (lv_thenAlgorithmClauses_3= rulealgorithm ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3586:2: (lv_thenAlgorithmClauses_3= rulealgorithm )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3589:6: lv_thenAlgorithmClauses_3= rulealgorithm
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_equation_rule_aAccess().getThenAlgorithmClausesAlgorithmParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulealgorithm_in_ruleconditional_equation_rule_a8273);
            	    lv_thenAlgorithmClauses_3=rulealgorithm();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getConditional_equation_rule_aRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenAlgorithmClauses", lv_thenAlgorithmClauses_3, "algorithm", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_a8286); 
            	     
            	        createLeafNode(grammarAccess.getConditional_equation_rule_aAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop84;
                }
            } while (true);

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3611:3: (lv_elseifClauses_5= rulealgorithm_elseif )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==83) ) {
                    alt85=1;
                }


                switch (alt85) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3614:6: lv_elseifClauses_5= rulealgorithm_elseif
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getConditional_equation_rule_aAccess().getElseifClausesAlgorithm_elseifParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulealgorithm_elseif_in_ruleconditional_equation_rule_a8321);
            	    lv_elseifClauses_5=rulealgorithm_elseif();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getConditional_equation_rule_aRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elseifClauses", lv_elseifClauses_5, "algorithm_elseif", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop85;
                }
            } while (true);

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3632:3: ( 'else' ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )* )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==77) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3632:4: 'else' ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )*
                    {
                    match(input,77,FOLLOW_77_in_ruleconditional_equation_rule_a8336); 

                            createLeafNode(grammarAccess.getConditional_equation_rule_aAccess().getElseKeyword_5_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3636:1: ( (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON )*
                    loop86:
                    do {
                        int alt86=2;
                        int LA86_0 = input.LA(1);

                        if ( (LA86_0==48) ) {
                            int LA86_1 = input.LA(2);

                            if ( (LA86_1==RULE_SEMICOLON||LA86_1==RULE_EQUALS||LA86_1==RULE_COLON||(LA86_1>=RULE_STRING && LA86_1<=RULE_ASSIGN)||(LA86_1>=RULE_LESS && LA86_1<=RULE_POWER_EW)||(LA86_1>=86 && LA86_1<=87)||LA86_1==91) ) {
                                alt86=1;
                            }


                        }
                        else if ( (LA86_0==RULE_IDENT||LA86_0==RULE_LPAR||LA86_0==RULE_STRING||(LA86_0>=RULE_PLUS && LA86_0<=RULE_MINUS_EW)||(LA86_0>=RULE_UNSIGNED_INTEGER && LA86_0<=RULE_LBRACK)||LA86_0==RULE_LBRACE||LA86_0==50||LA86_0==69||(LA86_0>=73 && LA86_0<=75)||LA86_0==78||(LA86_0>=80 && LA86_0<=81)||(LA86_0>=88 && LA86_0<=90)) ) {
                            alt86=1;
                        }


                        switch (alt86) {
                    	case 1 :
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3636:2: (lv_elseAlgotithmClauses_7= rulealgorithm ) RULE_SEMICOLON
                    	    {
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3636:2: (lv_elseAlgotithmClauses_7= rulealgorithm )
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3639:6: lv_elseAlgotithmClauses_7= rulealgorithm
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getConditional_equation_rule_aAccess().getElseAlgotithmClausesAlgorithmParserRuleCall_5_1_0_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_rulealgorithm_in_ruleconditional_equation_rule_a8371);
                    	    lv_elseAlgotithmClauses_7=rulealgorithm();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getConditional_equation_rule_aRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        
                    	    	        try {
                    	    	       		add(current, "elseAlgotithmClauses", lv_elseAlgotithmClauses_7, "algorithm", currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }

                    	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_a8384); 
                    	     
                    	        createLeafNode(grammarAccess.getConditional_equation_rule_aAccess().getSEMICOLONTerminalRuleCall_5_1_1(), null); 
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop86;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,48,FOLLOW_48_in_ruleconditional_equation_rule_a8396); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_aAccess().getEndKeyword_6(), null); 
                
            match(input,69,FOLLOW_69_in_ruleconditional_equation_rule_a8405); 

                    createLeafNode(grammarAccess.getConditional_equation_rule_aAccess().getIfKeyword_7(), null); 
                

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
    // $ANTLR end ruleconditional_equation_rule_a


    // $ANTLR start entryRulefor_clause_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3676:1: entryRulefor_clause_e returns [EObject current=null] : iv_rulefor_clause_e= rulefor_clause_e EOF ;
    public final EObject entryRulefor_clause_e() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_clause_e = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3676:54: (iv_rulefor_clause_e= rulefor_clause_e EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3677:2: iv_rulefor_clause_e= rulefor_clause_e EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_clause_eRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_clause_e_in_entryRulefor_clause_e8438);
            iv_rulefor_clause_e=rulefor_clause_e();
            _fsp--;

             current =iv_rulefor_clause_e; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_clause_e8448); 

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
    // $ANTLR end entryRulefor_clause_e


    // $ANTLR start rulefor_clause_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3684:1: rulefor_clause_e returns [EObject current=null] : ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_equationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* 'end' 'for' ) ;
    public final EObject rulefor_clause_e() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_forIndices_1 = null;

        EObject lv_equationClauses_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3689:6: ( ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_equationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* 'end' 'for' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3690:1: ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_equationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* 'end' 'for' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3690:1: ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_equationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* 'end' 'for' )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3690:2: 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_equationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* 'end' 'for'
            {
            match(input,78,FOLLOW_78_in_rulefor_clause_e8482); 

                    createLeafNode(grammarAccess.getFor_clause_eAccess().getForKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3694:1: (lv_forIndices_1= rulefor_indices )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3697:6: lv_forIndices_1= rulefor_indices
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFor_clause_eAccess().getForIndicesFor_indicesParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_rulefor_clause_e8516);
            lv_forIndices_1=rulefor_indices();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFor_clause_eRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "forIndices", lv_forIndices_1, "for_indices", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,79,FOLLOW_79_in_rulefor_clause_e8529); 

                    createLeafNode(grammarAccess.getFor_clause_eAccess().getLoopKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3719:1: ( (lv_equationClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop88:
            do {
                int alt88=2;
                int LA88_0 = input.LA(1);

                if ( (LA88_0==48) ) {
                    int LA88_1 = input.LA(2);

                    if ( (LA88_1==RULE_SEMICOLON||LA88_1==RULE_EQUALS||LA88_1==RULE_COLON||LA88_1==RULE_STRING||(LA88_1>=RULE_LESS && LA88_1<=RULE_POWER_EW)||(LA88_1>=86 && LA88_1<=87)||LA88_1==91) ) {
                        alt88=1;
                    }


                }
                else if ( (LA88_0==RULE_IDENT||LA88_0==RULE_LPAR||LA88_0==RULE_STRING||(LA88_0>=RULE_PLUS && LA88_0<=RULE_MINUS_EW)||(LA88_0>=RULE_UNSIGNED_INTEGER && LA88_0<=RULE_LBRACK)||LA88_0==RULE_LBRACE||LA88_0==50||LA88_0==69||LA88_0==73||LA88_0==78||LA88_0==81||LA88_0==84||(LA88_0>=88 && LA88_0<=90)) ) {
                    alt88=1;
                }


                switch (alt88) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3719:2: (lv_equationClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3719:2: (lv_equationClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3722:6: lv_equationClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getFor_clause_eAccess().getEquationClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_rulefor_clause_e8564);
            	    lv_equationClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getFor_clause_eRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "equationClauses", lv_equationClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulefor_clause_e8577); 
            	     
            	        createLeafNode(grammarAccess.getFor_clause_eAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop88;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_rulefor_clause_e8587); 

                    createLeafNode(grammarAccess.getFor_clause_eAccess().getEndKeyword_4(), null); 
                
            match(input,78,FOLLOW_78_in_rulefor_clause_e8596); 

                    createLeafNode(grammarAccess.getFor_clause_eAccess().getForKeyword_5(), null); 
                

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
    // $ANTLR end rulefor_clause_e


    // $ANTLR start entryRulefor_clause_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3759:1: entryRulefor_clause_a returns [EObject current=null] : iv_rulefor_clause_a= rulefor_clause_a EOF ;
    public final EObject entryRulefor_clause_a() throws RecognitionException {
        EObject current = null;

        EObject iv_rulefor_clause_a = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3759:54: (iv_rulefor_clause_a= rulefor_clause_a EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3760:2: iv_rulefor_clause_a= rulefor_clause_a EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_clause_aRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_clause_a_in_entryRulefor_clause_a8629);
            iv_rulefor_clause_a=rulefor_clause_a();
            _fsp--;

             current =iv_rulefor_clause_a; 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_clause_a8639); 

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
    // $ANTLR end entryRulefor_clause_a


    // $ANTLR start rulefor_clause_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3767:1: rulefor_clause_a returns [EObject current=null] : ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'for' ) ;
    public final EObject rulefor_clause_a() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_forIndices_1 = null;

        EObject lv_algorithmClauses_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3772:6: ( ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'for' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3773:1: ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'for' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3773:1: ( 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'for' )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3773:2: 'for' (lv_forIndices_1= rulefor_indices ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'for'
            {
            match(input,78,FOLLOW_78_in_rulefor_clause_a8673); 

                    createLeafNode(grammarAccess.getFor_clause_aAccess().getForKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3777:1: (lv_forIndices_1= rulefor_indices )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3780:6: lv_forIndices_1= rulefor_indices
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getFor_clause_aAccess().getForIndicesFor_indicesParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulefor_indices_in_rulefor_clause_a8707);
            lv_forIndices_1=rulefor_indices();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFor_clause_aRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "forIndices", lv_forIndices_1, "for_indices", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,79,FOLLOW_79_in_rulefor_clause_a8720); 

                    createLeafNode(grammarAccess.getFor_clause_aAccess().getLoopKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3802:1: ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )*
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==48) ) {
                    int LA89_1 = input.LA(2);

                    if ( (LA89_1==RULE_SEMICOLON||LA89_1==RULE_EQUALS||LA89_1==RULE_COLON||(LA89_1>=RULE_STRING && LA89_1<=RULE_ASSIGN)||(LA89_1>=RULE_LESS && LA89_1<=RULE_POWER_EW)||(LA89_1>=86 && LA89_1<=87)||LA89_1==91) ) {
                        alt89=1;
                    }


                }
                else if ( (LA89_0==RULE_IDENT||LA89_0==RULE_LPAR||LA89_0==RULE_STRING||(LA89_0>=RULE_PLUS && LA89_0<=RULE_MINUS_EW)||(LA89_0>=RULE_UNSIGNED_INTEGER && LA89_0<=RULE_LBRACK)||LA89_0==RULE_LBRACE||LA89_0==50||LA89_0==69||(LA89_0>=73 && LA89_0<=75)||LA89_0==78||(LA89_0>=80 && LA89_0<=81)||(LA89_0>=88 && LA89_0<=90)) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3802:2: (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3802:2: (lv_algorithmClauses_3= rulealgorithm )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3805:6: lv_algorithmClauses_3= rulealgorithm
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getFor_clause_aAccess().getAlgorithmClausesAlgorithmParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulealgorithm_in_rulefor_clause_a8755);
            	    lv_algorithmClauses_3=rulealgorithm();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getFor_clause_aRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "algorithmClauses", lv_algorithmClauses_3, "algorithm", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulefor_clause_a8768); 
            	     
            	        createLeafNode(grammarAccess.getFor_clause_aAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop89;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_rulefor_clause_a8778); 

                    createLeafNode(grammarAccess.getFor_clause_aAccess().getEndKeyword_4(), null); 
                
            match(input,78,FOLLOW_78_in_rulefor_clause_a8787); 

                    createLeafNode(grammarAccess.getFor_clause_aAccess().getForKeyword_5(), null); 
                

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
    // $ANTLR end rulefor_clause_a


    // $ANTLR start entryRulewhile_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3842:1: entryRulewhile_clause returns [EObject current=null] : iv_rulewhile_clause= rulewhile_clause EOF ;
    public final EObject entryRulewhile_clause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulewhile_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3842:54: (iv_rulewhile_clause= rulewhile_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3843:2: iv_rulewhile_clause= rulewhile_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWhile_clauseRule(), currentNode); 
            pushFollow(FOLLOW_rulewhile_clause_in_entryRulewhile_clause8820);
            iv_rulewhile_clause=rulewhile_clause();
            _fsp--;

             current =iv_rulewhile_clause; 
            match(input,EOF,FOLLOW_EOF_in_entryRulewhile_clause8830); 

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
    // $ANTLR end entryRulewhile_clause


    // $ANTLR start rulewhile_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3850:1: rulewhile_clause returns [EObject current=null] : ( 'while' (lv_condition_1= ruleexpression ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'while' ) ;
    public final EObject rulewhile_clause() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_algorithmClauses_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3855:6: ( ( 'while' (lv_condition_1= ruleexpression ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'while' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3856:1: ( 'while' (lv_condition_1= ruleexpression ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'while' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3856:1: ( 'while' (lv_condition_1= ruleexpression ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'while' )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3856:2: 'while' (lv_condition_1= ruleexpression ) 'loop' ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )* 'end' 'while'
            {
            match(input,80,FOLLOW_80_in_rulewhile_clause8864); 

                    createLeafNode(grammarAccess.getWhile_clauseAccess().getWhileKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3860:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3863:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getWhile_clauseAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulewhile_clause8898);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getWhile_clauseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,79,FOLLOW_79_in_rulewhile_clause8911); 

                    createLeafNode(grammarAccess.getWhile_clauseAccess().getLoopKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3885:1: ( (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==48) ) {
                    int LA90_1 = input.LA(2);

                    if ( (LA90_1==RULE_SEMICOLON||LA90_1==RULE_EQUALS||LA90_1==RULE_COLON||(LA90_1>=RULE_STRING && LA90_1<=RULE_ASSIGN)||(LA90_1>=RULE_LESS && LA90_1<=RULE_POWER_EW)||(LA90_1>=86 && LA90_1<=87)||LA90_1==91) ) {
                        alt90=1;
                    }


                }
                else if ( (LA90_0==RULE_IDENT||LA90_0==RULE_LPAR||LA90_0==RULE_STRING||(LA90_0>=RULE_PLUS && LA90_0<=RULE_MINUS_EW)||(LA90_0>=RULE_UNSIGNED_INTEGER && LA90_0<=RULE_LBRACK)||LA90_0==RULE_LBRACE||LA90_0==50||LA90_0==69||(LA90_0>=73 && LA90_0<=75)||LA90_0==78||(LA90_0>=80 && LA90_0<=81)||(LA90_0>=88 && LA90_0<=90)) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3885:2: (lv_algorithmClauses_3= rulealgorithm ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3885:2: (lv_algorithmClauses_3= rulealgorithm )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3888:6: lv_algorithmClauses_3= rulealgorithm
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getWhile_clauseAccess().getAlgorithmClausesAlgorithmParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulealgorithm_in_rulewhile_clause8946);
            	    lv_algorithmClauses_3=rulealgorithm();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getWhile_clauseRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "algorithmClauses", lv_algorithmClauses_3, "algorithm", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulewhile_clause8959); 
            	     
            	        createLeafNode(grammarAccess.getWhile_clauseAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_rulewhile_clause8969); 

                    createLeafNode(grammarAccess.getWhile_clauseAccess().getEndKeyword_4(), null); 
                
            match(input,80,FOLLOW_80_in_rulewhile_clause8978); 

                    createLeafNode(grammarAccess.getWhile_clauseAccess().getWhileKeyword_5(), null); 
                

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
    // $ANTLR end rulewhile_clause


    // $ANTLR start entryRulewhen_clause_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3925:1: entryRulewhen_clause_e returns [EObject current=null] : iv_rulewhen_clause_e= rulewhen_clause_e EOF ;
    public final EObject entryRulewhen_clause_e() throws RecognitionException {
        EObject current = null;

        EObject iv_rulewhen_clause_e = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3925:55: (iv_rulewhen_clause_e= rulewhen_clause_e EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3926:2: iv_rulewhen_clause_e= rulewhen_clause_e EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWhen_clause_eRule(), currentNode); 
            pushFollow(FOLLOW_rulewhen_clause_e_in_entryRulewhen_clause_e9011);
            iv_rulewhen_clause_e=rulewhen_clause_e();
            _fsp--;

             current =iv_rulewhen_clause_e; 
            match(input,EOF,FOLLOW_EOF_in_entryRulewhen_clause_e9021); 

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
    // $ANTLR end entryRulewhen_clause_e


    // $ANTLR start rulewhen_clause_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3933:1: rulewhen_clause_e returns [EObject current=null] : ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_e )* 'end' 'when' ) ;
    public final EObject rulewhen_clause_e() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_thenEquationClauses_3 = null;

        EObject lv_elseWhenClauses_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3938:6: ( ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_e )* 'end' 'when' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3939:1: ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_e )* 'end' 'when' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3939:1: ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_e )* 'end' 'when' )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3939:2: 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_e )* 'end' 'when'
            {
            match(input,81,FOLLOW_81_in_rulewhen_clause_e9055); 

                    createLeafNode(grammarAccess.getWhen_clause_eAccess().getWhenKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3943:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3946:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getWhen_clause_eAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulewhen_clause_e9089);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getWhen_clause_eRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,76,FOLLOW_76_in_rulewhen_clause_e9102); 

                    createLeafNode(grammarAccess.getWhen_clause_eAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3968:1: ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop91:
            do {
                int alt91=2;
                int LA91_0 = input.LA(1);

                if ( (LA91_0==48) ) {
                    int LA91_2 = input.LA(2);

                    if ( (LA91_2==RULE_SEMICOLON||LA91_2==RULE_EQUALS||LA91_2==RULE_COLON||LA91_2==RULE_STRING||(LA91_2>=RULE_LESS && LA91_2<=RULE_POWER_EW)||(LA91_2>=86 && LA91_2<=87)||LA91_2==91) ) {
                        alt91=1;
                    }


                }
                else if ( (LA91_0==RULE_IDENT||LA91_0==RULE_LPAR||LA91_0==RULE_STRING||(LA91_0>=RULE_PLUS && LA91_0<=RULE_MINUS_EW)||(LA91_0>=RULE_UNSIGNED_INTEGER && LA91_0<=RULE_LBRACK)||LA91_0==RULE_LBRACE||LA91_0==50||LA91_0==69||LA91_0==73||LA91_0==78||LA91_0==81||LA91_0==84||(LA91_0>=88 && LA91_0<=90)) ) {
                    alt91=1;
                }


                switch (alt91) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3968:2: (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3968:2: (lv_thenEquationClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3971:6: lv_thenEquationClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getWhen_clause_eAccess().getThenEquationClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_rulewhen_clause_e9137);
            	    lv_thenEquationClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getWhen_clause_eRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenEquationClauses", lv_thenEquationClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulewhen_clause_e9150); 
            	     
            	        createLeafNode(grammarAccess.getWhen_clause_eAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop91;
                }
            } while (true);

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3993:3: (lv_elseWhenClauses_5= ruleelse_when_e )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==82) ) {
                    alt92=1;
                }


                switch (alt92) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:3996:6: lv_elseWhenClauses_5= ruleelse_when_e
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getWhen_clause_eAccess().getElseWhenClausesElse_when_eParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleelse_when_e_in_rulewhen_clause_e9185);
            	    lv_elseWhenClauses_5=ruleelse_when_e();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getWhen_clause_eRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elseWhenClauses", lv_elseWhenClauses_5, "else_when_e", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop92;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_rulewhen_clause_e9199); 

                    createLeafNode(grammarAccess.getWhen_clause_eAccess().getEndKeyword_5(), null); 
                
            match(input,81,FOLLOW_81_in_rulewhen_clause_e9208); 

                    createLeafNode(grammarAccess.getWhen_clause_eAccess().getWhenKeyword_6(), null); 
                

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
    // $ANTLR end rulewhen_clause_e


    // $ANTLR start entryRuleelse_when_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4029:1: entryRuleelse_when_e returns [EObject current=null] : iv_ruleelse_when_e= ruleelse_when_e EOF ;
    public final EObject entryRuleelse_when_e() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleelse_when_e = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4029:53: (iv_ruleelse_when_e= ruleelse_when_e EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4030:2: iv_ruleelse_when_e= ruleelse_when_e EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElse_when_eRule(), currentNode); 
            pushFollow(FOLLOW_ruleelse_when_e_in_entryRuleelse_when_e9241);
            iv_ruleelse_when_e=ruleelse_when_e();
            _fsp--;

             current =iv_ruleelse_when_e; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleelse_when_e9251); 

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
    // $ANTLR end entryRuleelse_when_e


    // $ANTLR start ruleelse_when_e
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4037:1: ruleelse_when_e returns [EObject current=null] : ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) ;
    public final EObject ruleelse_when_e() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_thenEquationClauses_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4042:6: ( ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4043:1: ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4043:1: ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4043:2: 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            {
            match(input,82,FOLLOW_82_in_ruleelse_when_e9285); 

                    createLeafNode(grammarAccess.getElse_when_eAccess().getElsewhenKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4047:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4050:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElse_when_eAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleelse_when_e9319);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElse_when_eRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,76,FOLLOW_76_in_ruleelse_when_e9332); 

                    createLeafNode(grammarAccess.getElse_when_eAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4072:1: ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( (LA93_0==48) ) {
                    int LA93_1 = input.LA(2);

                    if ( (LA93_1==RULE_SEMICOLON||LA93_1==RULE_EQUALS||LA93_1==RULE_COLON||LA93_1==RULE_STRING||(LA93_1>=RULE_LESS && LA93_1<=RULE_POWER_EW)||(LA93_1>=86 && LA93_1<=87)||LA93_1==91) ) {
                        alt93=1;
                    }


                }
                else if ( (LA93_0==RULE_IDENT||LA93_0==RULE_LPAR||LA93_0==RULE_STRING||(LA93_0>=RULE_PLUS && LA93_0<=RULE_MINUS_EW)||(LA93_0>=RULE_UNSIGNED_INTEGER && LA93_0<=RULE_LBRACK)||LA93_0==RULE_LBRACE||LA93_0==50||LA93_0==69||LA93_0==73||LA93_0==78||LA93_0==81||LA93_0==84||(LA93_0>=88 && LA93_0<=90)) ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4072:2: (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4072:2: (lv_thenEquationClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4075:6: lv_thenEquationClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElse_when_eAccess().getThenEquationClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_ruleelse_when_e9367);
            	    lv_thenEquationClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElse_when_eRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenEquationClauses", lv_thenEquationClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleelse_when_e9380); 
            	     
            	        createLeafNode(grammarAccess.getElse_when_eAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop93;
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
    // $ANTLR end ruleelse_when_e


    // $ANTLR start entryRulewhen_clause_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4104:1: entryRulewhen_clause_a returns [EObject current=null] : iv_rulewhen_clause_a= rulewhen_clause_a EOF ;
    public final EObject entryRulewhen_clause_a() throws RecognitionException {
        EObject current = null;

        EObject iv_rulewhen_clause_a = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4104:55: (iv_rulewhen_clause_a= rulewhen_clause_a EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4105:2: iv_rulewhen_clause_a= rulewhen_clause_a EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWhen_clause_aRule(), currentNode); 
            pushFollow(FOLLOW_rulewhen_clause_a_in_entryRulewhen_clause_a9414);
            iv_rulewhen_clause_a=rulewhen_clause_a();
            _fsp--;

             current =iv_rulewhen_clause_a; 
            match(input,EOF,FOLLOW_EOF_in_entryRulewhen_clause_a9424); 

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
    // $ANTLR end entryRulewhen_clause_a


    // $ANTLR start rulewhen_clause_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4112:1: rulewhen_clause_a returns [EObject current=null] : ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_a )* 'end' 'when' ) ;
    public final EObject rulewhen_clause_a() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_thenAlgorithmClauses_3 = null;

        EObject lv_elseWhenClauses_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4117:6: ( ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_a )* 'end' 'when' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4118:1: ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_a )* 'end' 'when' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4118:1: ( 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_a )* 'end' 'when' )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4118:2: 'when' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* (lv_elseWhenClauses_5= ruleelse_when_a )* 'end' 'when'
            {
            match(input,81,FOLLOW_81_in_rulewhen_clause_a9458); 

                    createLeafNode(grammarAccess.getWhen_clause_aAccess().getWhenKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4122:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4125:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getWhen_clause_aAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulewhen_clause_a9492);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getWhen_clause_aRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,76,FOLLOW_76_in_rulewhen_clause_a9505); 

                    createLeafNode(grammarAccess.getWhen_clause_aAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4147:1: ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==48) ) {
                    int LA94_2 = input.LA(2);

                    if ( (LA94_2==RULE_SEMICOLON||LA94_2==RULE_EQUALS||LA94_2==RULE_COLON||LA94_2==RULE_STRING||(LA94_2>=RULE_LESS && LA94_2<=RULE_POWER_EW)||(LA94_2>=86 && LA94_2<=87)||LA94_2==91) ) {
                        alt94=1;
                    }


                }
                else if ( (LA94_0==RULE_IDENT||LA94_0==RULE_LPAR||LA94_0==RULE_STRING||(LA94_0>=RULE_PLUS && LA94_0<=RULE_MINUS_EW)||(LA94_0>=RULE_UNSIGNED_INTEGER && LA94_0<=RULE_LBRACK)||LA94_0==RULE_LBRACE||LA94_0==50||LA94_0==69||LA94_0==73||LA94_0==78||LA94_0==81||LA94_0==84||(LA94_0>=88 && LA94_0<=90)) ) {
                    alt94=1;
                }


                switch (alt94) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4147:2: (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4147:2: (lv_thenAlgorithmClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4150:6: lv_thenAlgorithmClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getWhen_clause_aAccess().getThenAlgorithmClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_rulewhen_clause_a9540);
            	    lv_thenAlgorithmClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getWhen_clause_aRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenAlgorithmClauses", lv_thenAlgorithmClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulewhen_clause_a9553); 
            	     
            	        createLeafNode(grammarAccess.getWhen_clause_aAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop94;
                }
            } while (true);

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4172:3: (lv_elseWhenClauses_5= ruleelse_when_a )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==82) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4175:6: lv_elseWhenClauses_5= ruleelse_when_a
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getWhen_clause_aAccess().getElseWhenClausesElse_when_aParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleelse_when_a_in_rulewhen_clause_a9588);
            	    lv_elseWhenClauses_5=ruleelse_when_a();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getWhen_clause_aRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elseWhenClauses", lv_elseWhenClauses_5, "else_when_a", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop95;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_rulewhen_clause_a9602); 

                    createLeafNode(grammarAccess.getWhen_clause_aAccess().getEndKeyword_5(), null); 
                
            match(input,81,FOLLOW_81_in_rulewhen_clause_a9611); 

                    createLeafNode(grammarAccess.getWhen_clause_aAccess().getWhenKeyword_6(), null); 
                

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
    // $ANTLR end rulewhen_clause_a


    // $ANTLR start entryRuleelse_when_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4208:1: entryRuleelse_when_a returns [EObject current=null] : iv_ruleelse_when_a= ruleelse_when_a EOF ;
    public final EObject entryRuleelse_when_a() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleelse_when_a = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4208:53: (iv_ruleelse_when_a= ruleelse_when_a EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4209:2: iv_ruleelse_when_a= ruleelse_when_a EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElse_when_aRule(), currentNode); 
            pushFollow(FOLLOW_ruleelse_when_a_in_entryRuleelse_when_a9644);
            iv_ruleelse_when_a=ruleelse_when_a();
            _fsp--;

             current =iv_ruleelse_when_a; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleelse_when_a9654); 

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
    // $ANTLR end entryRuleelse_when_a


    // $ANTLR start ruleelse_when_a
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4216:1: ruleelse_when_a returns [EObject current=null] : ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) ;
    public final EObject ruleelse_when_a() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_thenAlgorithmClauses_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4221:6: ( ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4222:1: ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4222:1: ( 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4222:2: 'elsewhen' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            {
            match(input,82,FOLLOW_82_in_ruleelse_when_a9688); 

                    createLeafNode(grammarAccess.getElse_when_aAccess().getElsewhenKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4226:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4229:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElse_when_aAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleelse_when_a9722);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElse_when_aRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,76,FOLLOW_76_in_ruleelse_when_a9735); 

                    createLeafNode(grammarAccess.getElse_when_aAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4251:1: ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( (LA96_0==48) ) {
                    int LA96_1 = input.LA(2);

                    if ( (LA96_1==RULE_SEMICOLON||LA96_1==RULE_EQUALS||LA96_1==RULE_COLON||LA96_1==RULE_STRING||(LA96_1>=RULE_LESS && LA96_1<=RULE_POWER_EW)||(LA96_1>=86 && LA96_1<=87)||LA96_1==91) ) {
                        alt96=1;
                    }


                }
                else if ( (LA96_0==RULE_IDENT||LA96_0==RULE_LPAR||LA96_0==RULE_STRING||(LA96_0>=RULE_PLUS && LA96_0<=RULE_MINUS_EW)||(LA96_0>=RULE_UNSIGNED_INTEGER && LA96_0<=RULE_LBRACK)||LA96_0==RULE_LBRACE||LA96_0==50||LA96_0==69||LA96_0==73||LA96_0==78||LA96_0==81||LA96_0==84||(LA96_0>=88 && LA96_0<=90)) ) {
                    alt96=1;
                }


                switch (alt96) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4251:2: (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4251:2: (lv_thenAlgorithmClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4254:6: lv_thenAlgorithmClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getElse_when_aAccess().getThenAlgorithmClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_ruleelse_when_a9770);
            	    lv_thenAlgorithmClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getElse_when_aRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenAlgorithmClauses", lv_thenAlgorithmClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleelse_when_a9783); 
            	     
            	        createLeafNode(grammarAccess.getElse_when_aAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop96;
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
    // $ANTLR end ruleelse_when_a


    // $ANTLR start entryRuleequation_rule_elseif
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4283:1: entryRuleequation_rule_elseif returns [EObject current=null] : iv_ruleequation_rule_elseif= ruleequation_rule_elseif EOF ;
    public final EObject entryRuleequation_rule_elseif() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleequation_rule_elseif = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4283:62: (iv_ruleequation_rule_elseif= ruleequation_rule_elseif EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4284:2: iv_ruleequation_rule_elseif= ruleequation_rule_elseif EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEquation_rule_elseifRule(), currentNode); 
            pushFollow(FOLLOW_ruleequation_rule_elseif_in_entryRuleequation_rule_elseif9817);
            iv_ruleequation_rule_elseif=ruleequation_rule_elseif();
            _fsp--;

             current =iv_ruleequation_rule_elseif; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleequation_rule_elseif9827); 

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
    // $ANTLR end entryRuleequation_rule_elseif


    // $ANTLR start ruleequation_rule_elseif
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4291:1: ruleequation_rule_elseif returns [EObject current=null] : ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) ;
    public final EObject ruleequation_rule_elseif() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_thenEquationClauses_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4296:6: ( ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4297:1: ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4297:1: ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4297:2: 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            {
            match(input,83,FOLLOW_83_in_ruleequation_rule_elseif9861); 

                    createLeafNode(grammarAccess.getEquation_rule_elseifAccess().getElseifKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4301:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4304:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getEquation_rule_elseifAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_ruleequation_rule_elseif9895);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEquation_rule_elseifRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,76,FOLLOW_76_in_ruleequation_rule_elseif9908); 

                    createLeafNode(grammarAccess.getEquation_rule_elseifAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4326:1: ( (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==48) ) {
                    int LA97_2 = input.LA(2);

                    if ( (LA97_2==RULE_SEMICOLON||LA97_2==RULE_EQUALS||LA97_2==RULE_COLON||LA97_2==RULE_STRING||(LA97_2>=RULE_LESS && LA97_2<=RULE_POWER_EW)||(LA97_2>=86 && LA97_2<=87)||LA97_2==91) ) {
                        alt97=1;
                    }


                }
                else if ( (LA97_0==RULE_IDENT||LA97_0==RULE_LPAR||LA97_0==RULE_STRING||(LA97_0>=RULE_PLUS && LA97_0<=RULE_MINUS_EW)||(LA97_0>=RULE_UNSIGNED_INTEGER && LA97_0<=RULE_LBRACK)||LA97_0==RULE_LBRACE||LA97_0==50||LA97_0==69||LA97_0==73||LA97_0==78||LA97_0==81||LA97_0==84||(LA97_0>=88 && LA97_0<=90)) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4326:2: (lv_thenEquationClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4326:2: (lv_thenEquationClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4329:6: lv_thenEquationClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getEquation_rule_elseifAccess().getThenEquationClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_ruleequation_rule_elseif9943);
            	    lv_thenEquationClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getEquation_rule_elseifRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenEquationClauses", lv_thenEquationClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleequation_rule_elseif9956); 
            	     
            	        createLeafNode(grammarAccess.getEquation_rule_elseifAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop97;
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
    // $ANTLR end ruleequation_rule_elseif


    // $ANTLR start entryRulealgorithm_elseif
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4358:1: entryRulealgorithm_elseif returns [EObject current=null] : iv_rulealgorithm_elseif= rulealgorithm_elseif EOF ;
    public final EObject entryRulealgorithm_elseif() throws RecognitionException {
        EObject current = null;

        EObject iv_rulealgorithm_elseif = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4358:58: (iv_rulealgorithm_elseif= rulealgorithm_elseif EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4359:2: iv_rulealgorithm_elseif= rulealgorithm_elseif EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAlgorithm_elseifRule(), currentNode); 
            pushFollow(FOLLOW_rulealgorithm_elseif_in_entryRulealgorithm_elseif9990);
            iv_rulealgorithm_elseif=rulealgorithm_elseif();
            _fsp--;

             current =iv_rulealgorithm_elseif; 
            match(input,EOF,FOLLOW_EOF_in_entryRulealgorithm_elseif10000); 

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
    // $ANTLR end entryRulealgorithm_elseif


    // $ANTLR start rulealgorithm_elseif
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4366:1: rulealgorithm_elseif returns [EObject current=null] : ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) ;
    public final EObject rulealgorithm_elseif() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_condition_1 = null;

        EObject lv_thenAlgorithmClauses_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4371:6: ( ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4372:1: ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4372:1: ( 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4372:2: 'elseif' (lv_condition_1= ruleexpression ) 'then' ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            {
            match(input,83,FOLLOW_83_in_rulealgorithm_elseif10034); 

                    createLeafNode(grammarAccess.getAlgorithm_elseifAccess().getElseifKeyword_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4376:1: (lv_condition_1= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4379:6: lv_condition_1= ruleexpression
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getAlgorithm_elseifAccess().getConditionExpressionParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleexpression_in_rulealgorithm_elseif10068);
            lv_condition_1=ruleexpression();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAlgorithm_elseifRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        
            	        try {
            	       		set(current, "condition", lv_condition_1, "expression", currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }

            match(input,76,FOLLOW_76_in_rulealgorithm_elseif10081); 

                    createLeafNode(grammarAccess.getAlgorithm_elseifAccess().getThenKeyword_2(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4401:1: ( (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==48) ) {
                    int LA98_2 = input.LA(2);

                    if ( (LA98_2==RULE_SEMICOLON||LA98_2==RULE_EQUALS||LA98_2==RULE_COLON||LA98_2==RULE_STRING||(LA98_2>=RULE_LESS && LA98_2<=RULE_POWER_EW)||(LA98_2>=86 && LA98_2<=87)||LA98_2==91) ) {
                        alt98=1;
                    }


                }
                else if ( (LA98_0==RULE_IDENT||LA98_0==RULE_LPAR||LA98_0==RULE_STRING||(LA98_0>=RULE_PLUS && LA98_0<=RULE_MINUS_EW)||(LA98_0>=RULE_UNSIGNED_INTEGER && LA98_0<=RULE_LBRACK)||LA98_0==RULE_LBRACE||LA98_0==50||LA98_0==69||LA98_0==73||LA98_0==78||LA98_0==81||LA98_0==84||(LA98_0>=88 && LA98_0<=90)) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4401:2: (lv_thenAlgorithmClauses_3= ruleequation_rule ) RULE_SEMICOLON
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4401:2: (lv_thenAlgorithmClauses_3= ruleequation_rule )
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4404:6: lv_thenAlgorithmClauses_3= ruleequation_rule
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getAlgorithm_elseifAccess().getThenAlgorithmClausesEquation_ruleParserRuleCall_3_0_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleequation_rule_in_rulealgorithm_elseif10116);
            	    lv_thenAlgorithmClauses_3=ruleequation_rule();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getAlgorithm_elseifRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "thenAlgorithmClauses", lv_thenAlgorithmClauses_3, "equation_rule", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }

            	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_rulealgorithm_elseif10129); 
            	     
            	        createLeafNode(grammarAccess.getAlgorithm_elseifAccess().getSEMICOLONTerminalRuleCall_3_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop98;
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
    // $ANTLR end rulealgorithm_elseif


    // $ANTLR start entryRuleconnect_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4433:1: entryRuleconnect_clause returns [String current=null] : iv_ruleconnect_clause= ruleconnect_clause EOF ;
    public final String entryRuleconnect_clause() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleconnect_clause = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4433:55: (iv_ruleconnect_clause= ruleconnect_clause EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4434:2: iv_ruleconnect_clause= ruleconnect_clause EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConnect_clauseRule(), currentNode); 
            pushFollow(FOLLOW_ruleconnect_clause_in_entryRuleconnect_clause10164);
            iv_ruleconnect_clause=ruleconnect_clause();
            _fsp--;

             current =iv_ruleconnect_clause.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconnect_clause10175); 

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
    // $ANTLR end entryRuleconnect_clause


    // $ANTLR start ruleconnect_clause
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4441:1: ruleconnect_clause returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'connect' this_LPAR_1= RULE_LPAR this_connector_ref_2= ruleconnector_ref this_COMMA_3= RULE_COMMA this_connector_ref_4= ruleconnector_ref this_RPAR_5= RULE_RPAR ) ;
    public final AntlrDatatypeRuleToken ruleconnect_clause() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_LPAR_1=null;
        Token this_COMMA_3=null;
        Token this_RPAR_5=null;
        AntlrDatatypeRuleToken this_connector_ref_2 = null;

        AntlrDatatypeRuleToken this_connector_ref_4 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4447:6: ( (kw= 'connect' this_LPAR_1= RULE_LPAR this_connector_ref_2= ruleconnector_ref this_COMMA_3= RULE_COMMA this_connector_ref_4= ruleconnector_ref this_RPAR_5= RULE_RPAR ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4448:1: (kw= 'connect' this_LPAR_1= RULE_LPAR this_connector_ref_2= ruleconnector_ref this_COMMA_3= RULE_COMMA this_connector_ref_4= ruleconnector_ref this_RPAR_5= RULE_RPAR )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4448:1: (kw= 'connect' this_LPAR_1= RULE_LPAR this_connector_ref_2= ruleconnector_ref this_COMMA_3= RULE_COMMA this_connector_ref_4= ruleconnector_ref this_RPAR_5= RULE_RPAR )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4449:2: kw= 'connect' this_LPAR_1= RULE_LPAR this_connector_ref_2= ruleconnector_ref this_COMMA_3= RULE_COMMA this_connector_ref_4= ruleconnector_ref this_RPAR_5= RULE_RPAR
            {
            kw=(Token)input.LT(1);
            match(input,84,FOLLOW_84_in_ruleconnect_clause10213); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getConnect_clauseAccess().getConnectKeyword_0(), null); 
                
            this_LPAR_1=(Token)input.LT(1);
            match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_ruleconnect_clause10228); 

            		current.merge(this_LPAR_1);
                
             
                createLeafNode(grammarAccess.getConnect_clauseAccess().getLPARTerminalRuleCall_1(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getConnect_clauseAccess().getConnector_refParserRuleCall_2(), currentNode); 
                
            pushFollow(FOLLOW_ruleconnector_ref_in_ruleconnect_clause10255);
            this_connector_ref_2=ruleconnector_ref();
            _fsp--;


            		current.merge(this_connector_ref_2);
                
             
                    currentNode = currentNode.getParent();
                
            this_COMMA_3=(Token)input.LT(1);
            match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_ruleconnect_clause10275); 

            		current.merge(this_COMMA_3);
                
             
                createLeafNode(grammarAccess.getConnect_clauseAccess().getCOMMATerminalRuleCall_3(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getConnect_clauseAccess().getConnector_refParserRuleCall_4(), currentNode); 
                
            pushFollow(FOLLOW_ruleconnector_ref_in_ruleconnect_clause10302);
            this_connector_ref_4=ruleconnector_ref();
            _fsp--;


            		current.merge(this_connector_ref_4);
                
             
                    currentNode = currentNode.getParent();
                
            this_RPAR_5=(Token)input.LT(1);
            match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_ruleconnect_clause10322); 

            		current.merge(this_RPAR_5);
                
             
                createLeafNode(grammarAccess.getConnect_clauseAccess().getRPARTerminalRuleCall_5(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleconnect_clause


    // $ANTLR start entryRuleconnector_ref
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4505:1: entryRuleconnector_ref returns [String current=null] : iv_ruleconnector_ref= ruleconnector_ref EOF ;
    public final String entryRuleconnector_ref() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleconnector_ref = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4505:54: (iv_ruleconnector_ref= ruleconnector_ref EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4506:2: iv_ruleconnector_ref= ruleconnector_ref EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConnector_refRule(), currentNode); 
            pushFollow(FOLLOW_ruleconnector_ref_in_entryRuleconnector_ref10366);
            iv_ruleconnector_ref=ruleconnector_ref();
            _fsp--;

             current =iv_ruleconnector_ref.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconnector_ref10377); 

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
    // $ANTLR end entryRuleconnector_ref


    // $ANTLR start ruleconnector_ref
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4513:1: ruleconnector_ref returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_connector_ref_2_3= ruleconnector_ref_2 )? ) ;
    public final AntlrDatatypeRuleToken ruleconnector_ref() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        Token this_DOT_2=null;
        AntlrDatatypeRuleToken this_array_subscripts_1 = null;

        AntlrDatatypeRuleToken this_connector_ref_2_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4519:6: ( (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_connector_ref_2_3= ruleconnector_ref_2 )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4520:1: (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_connector_ref_2_3= ruleconnector_ref_2 )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4520:1: (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_connector_ref_2_3= ruleconnector_ref_2 )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4520:6: this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_connector_ref_2_3= ruleconnector_ref_2 )?
            {
            this_IDENT_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleconnector_ref10417); 

            		current.merge(this_IDENT_0);
                
             
                createLeafNode(grammarAccess.getConnector_refAccess().getIDENTTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4527:1: (this_array_subscripts_1= rulearray_subscripts )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==RULE_LBRACK) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4528:5: this_array_subscripts_1= rulearray_subscripts
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getConnector_refAccess().getArray_subscriptsParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulearray_subscripts_in_ruleconnector_ref10445);
                    this_array_subscripts_1=rulearray_subscripts();
                    _fsp--;


                    		current.merge(this_array_subscripts_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4538:3: (this_DOT_2= RULE_DOT this_connector_ref_2_3= ruleconnector_ref_2 )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==RULE_DOT) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4538:8: this_DOT_2= RULE_DOT this_connector_ref_2_3= ruleconnector_ref_2
                    {
                    this_DOT_2=(Token)input.LT(1);
                    match(input,RULE_DOT,FOLLOW_RULE_DOT_in_ruleconnector_ref10468); 

                    		current.merge(this_DOT_2);
                        
                     
                        createLeafNode(grammarAccess.getConnector_refAccess().getDOTTerminalRuleCall_2_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getConnector_refAccess().getConnector_ref_2ParserRuleCall_2_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleconnector_ref_2_in_ruleconnector_ref10495);
                    this_connector_ref_2_3=ruleconnector_ref_2();
                    _fsp--;


                    		current.merge(this_connector_ref_2_3);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleconnector_ref


    // $ANTLR start entryRuleconnector_ref_2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4564:1: entryRuleconnector_ref_2 returns [String current=null] : iv_ruleconnector_ref_2= ruleconnector_ref_2 EOF ;
    public final String entryRuleconnector_ref_2() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleconnector_ref_2 = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4564:56: (iv_ruleconnector_ref_2= ruleconnector_ref_2 EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4565:2: iv_ruleconnector_ref_2= ruleconnector_ref_2 EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConnector_ref_2Rule(), currentNode); 
            pushFollow(FOLLOW_ruleconnector_ref_2_in_entryRuleconnector_ref_210541);
            iv_ruleconnector_ref_2=ruleconnector_ref_2();
            _fsp--;

             current =iv_ruleconnector_ref_2.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleconnector_ref_210552); 

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
    // $ANTLR end entryRuleconnector_ref_2


    // $ANTLR start ruleconnector_ref_2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4572:1: ruleconnector_ref_2 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? ) ;
    public final AntlrDatatypeRuleToken ruleconnector_ref_2() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        AntlrDatatypeRuleToken this_array_subscripts_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4578:6: ( (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4579:1: (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4579:1: (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4579:6: this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )?
            {
            this_IDENT_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_ruleconnector_ref_210592); 

            		current.merge(this_IDENT_0);
                
             
                createLeafNode(grammarAccess.getConnector_ref_2Access().getIDENTTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4586:1: (this_array_subscripts_1= rulearray_subscripts )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==RULE_LBRACK) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4587:5: this_array_subscripts_1= rulearray_subscripts
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getConnector_ref_2Access().getArray_subscriptsParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulearray_subscripts_in_ruleconnector_ref_210620);
                    this_array_subscripts_1=rulearray_subscripts();
                    _fsp--;


                    		current.merge(this_array_subscripts_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleconnector_ref_2


    // $ANTLR start entryRuleexpression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4605:1: entryRuleexpression returns [String current=null] : iv_ruleexpression= ruleexpression EOF ;
    public final String entryRuleexpression() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleexpression = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4605:51: (iv_ruleexpression= ruleexpression EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4606:2: iv_ruleexpression= ruleexpression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleexpression_in_entryRuleexpression10666);
            iv_ruleexpression=ruleexpression();
            _fsp--;

             current =iv_ruleexpression.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression10677); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4613:1: ruleexpression returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_if_expression_0= ruleif_expression | this_simple_expression_1= rulesimple_expression ) ;
    public final AntlrDatatypeRuleToken ruleexpression() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_if_expression_0 = null;

        AntlrDatatypeRuleToken this_simple_expression_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4619:6: ( (this_if_expression_0= ruleif_expression | this_simple_expression_1= rulesimple_expression ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4620:1: (this_if_expression_0= ruleif_expression | this_simple_expression_1= rulesimple_expression )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4620:1: (this_if_expression_0= ruleif_expression | this_simple_expression_1= rulesimple_expression )
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==69) ) {
                alt102=1;
            }
            else if ( (LA102_0==RULE_IDENT||LA102_0==RULE_LPAR||LA102_0==RULE_STRING||(LA102_0>=RULE_PLUS && LA102_0<=RULE_MINUS_EW)||(LA102_0>=RULE_UNSIGNED_INTEGER && LA102_0<=RULE_LBRACK)||LA102_0==RULE_LBRACE||LA102_0==48||LA102_0==50||LA102_0==73||(LA102_0>=88 && LA102_0<=90)) ) {
                alt102=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4620:1: (this_if_expression_0= ruleif_expression | this_simple_expression_1= rulesimple_expression )", 102, 0, input);

                throw nvae;
            }
            switch (alt102) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4621:5: this_if_expression_0= ruleif_expression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getIf_expressionParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleif_expression_in_ruleexpression10724);
                    this_if_expression_0=ruleif_expression();
                    _fsp--;


                    		current.merge(this_if_expression_0);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4633:5: this_simple_expression_1= rulesimple_expression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExpressionAccess().getSimple_expressionParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulesimple_expression_in_ruleexpression10757);
                    this_simple_expression_1=rulesimple_expression();
                    _fsp--;


                    		current.merge(this_simple_expression_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRuleif_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4653:1: entryRuleif_expression returns [String current=null] : iv_ruleif_expression= ruleif_expression EOF ;
    public final String entryRuleif_expression() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleif_expression = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4653:54: (iv_ruleif_expression= ruleif_expression EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4654:2: iv_ruleif_expression= ruleif_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIf_expressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleif_expression_in_entryRuleif_expression10803);
            iv_ruleif_expression=ruleif_expression();
            _fsp--;

             current =iv_ruleif_expression.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleif_expression10814); 

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
    // $ANTLR end entryRuleif_expression


    // $ANTLR start ruleif_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4661:1: ruleif_expression returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'if' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression (this_elseif_expression_4= ruleelseif_expression )* kw= 'else' this_expression_6= ruleexpression ) ;
    public final AntlrDatatypeRuleToken ruleif_expression() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_expression_1 = null;

        AntlrDatatypeRuleToken this_expression_3 = null;

        AntlrDatatypeRuleToken this_elseif_expression_4 = null;

        AntlrDatatypeRuleToken this_expression_6 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4667:6: ( (kw= 'if' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression (this_elseif_expression_4= ruleelseif_expression )* kw= 'else' this_expression_6= ruleexpression ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4668:1: (kw= 'if' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression (this_elseif_expression_4= ruleelseif_expression )* kw= 'else' this_expression_6= ruleexpression )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4668:1: (kw= 'if' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression (this_elseif_expression_4= ruleelseif_expression )* kw= 'else' this_expression_6= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4669:2: kw= 'if' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression (this_elseif_expression_4= ruleelseif_expression )* kw= 'else' this_expression_6= ruleexpression
            {
            kw=(Token)input.LT(1);
            match(input,69,FOLLOW_69_in_ruleif_expression10852); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getIf_expressionAccess().getIfKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getIf_expressionAccess().getExpressionParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_ruleif_expression10874);
            this_expression_1=ruleexpression();
            _fsp--;


            		current.merge(this_expression_1);
                
             
                    currentNode = currentNode.getParent();
                
            kw=(Token)input.LT(1);
            match(input,76,FOLLOW_76_in_ruleif_expression10892); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getIf_expressionAccess().getThenKeyword_2(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getIf_expressionAccess().getExpressionParserRuleCall_3(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_ruleif_expression10914);
            this_expression_3=ruleexpression();
            _fsp--;


            		current.merge(this_expression_3);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4702:1: (this_elseif_expression_4= ruleelseif_expression )*
            loop103:
            do {
                int alt103=2;
                int LA103_0 = input.LA(1);

                if ( (LA103_0==83) ) {
                    alt103=1;
                }


                switch (alt103) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4703:5: this_elseif_expression_4= ruleelseif_expression
            	    {
            	     
            	            currentNode=createCompositeNode(grammarAccess.getIf_expressionAccess().getElseif_expressionParserRuleCall_4(), currentNode); 
            	        
            	    pushFollow(FOLLOW_ruleelseif_expression_in_ruleif_expression10942);
            	    this_elseif_expression_4=ruleelseif_expression();
            	    _fsp--;


            	    		current.merge(this_elseif_expression_4);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);

            kw=(Token)input.LT(1);
            match(input,77,FOLLOW_77_in_ruleif_expression10962); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getIf_expressionAccess().getElseKeyword_5(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getIf_expressionAccess().getExpressionParserRuleCall_6(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_ruleif_expression10984);
            this_expression_6=ruleexpression();
            _fsp--;


            		current.merge(this_expression_6);
                
             
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleif_expression


    // $ANTLR start entryRuleelseif_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4738:1: entryRuleelseif_expression returns [String current=null] : iv_ruleelseif_expression= ruleelseif_expression EOF ;
    public final String entryRuleelseif_expression() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleelseif_expression = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4738:58: (iv_ruleelseif_expression= ruleelseif_expression EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4739:2: iv_ruleelseif_expression= ruleelseif_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElseif_expressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleelseif_expression_in_entryRuleelseif_expression11028);
            iv_ruleelseif_expression=ruleelseif_expression();
            _fsp--;

             current =iv_ruleelseif_expression.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleelseif_expression11039); 

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
    // $ANTLR end entryRuleelseif_expression


    // $ANTLR start ruleelseif_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4746:1: ruleelseif_expression returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'elseif' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression ) ;
    public final AntlrDatatypeRuleToken ruleelseif_expression() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_expression_1 = null;

        AntlrDatatypeRuleToken this_expression_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4752:6: ( (kw= 'elseif' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4753:1: (kw= 'elseif' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4753:1: (kw= 'elseif' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4754:2: kw= 'elseif' this_expression_1= ruleexpression kw= 'then' this_expression_3= ruleexpression
            {
            kw=(Token)input.LT(1);
            match(input,83,FOLLOW_83_in_ruleelseif_expression11077); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getElseif_expressionAccess().getElseifKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getElseif_expressionAccess().getExpressionParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_ruleelseif_expression11099);
            this_expression_1=ruleexpression();
            _fsp--;


            		current.merge(this_expression_1);
                
             
                    currentNode = currentNode.getParent();
                
            kw=(Token)input.LT(1);
            match(input,76,FOLLOW_76_in_ruleelseif_expression11117); 

                    current.merge(kw);
                    createLeafNode(grammarAccess.getElseif_expressionAccess().getThenKeyword_2(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getElseif_expressionAccess().getExpressionParserRuleCall_3(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_ruleelseif_expression11139);
            this_expression_3=ruleexpression();
            _fsp--;


            		current.merge(this_expression_3);
                
             
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleelseif_expression


    // $ANTLR start entryRulefor_indices
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4795:1: entryRulefor_indices returns [String current=null] : iv_rulefor_indices= rulefor_indices EOF ;
    public final String entryRulefor_indices() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulefor_indices = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4795:52: (iv_rulefor_indices= rulefor_indices EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4796:2: iv_rulefor_indices= rulefor_indices EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_indicesRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_indices_in_entryRulefor_indices11183);
            iv_rulefor_indices=rulefor_indices();
            _fsp--;

             current =iv_rulefor_indices.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_indices11194); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4803:1: rulefor_indices returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_for_index_0= rulefor_index (this_COMMA_1= RULE_COMMA this_for_index_2= rulefor_index )* ) ;
    public final AntlrDatatypeRuleToken rulefor_indices() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_COMMA_1=null;
        AntlrDatatypeRuleToken this_for_index_0 = null;

        AntlrDatatypeRuleToken this_for_index_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4809:6: ( (this_for_index_0= rulefor_index (this_COMMA_1= RULE_COMMA this_for_index_2= rulefor_index )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4810:1: (this_for_index_0= rulefor_index (this_COMMA_1= RULE_COMMA this_for_index_2= rulefor_index )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4810:1: (this_for_index_0= rulefor_index (this_COMMA_1= RULE_COMMA this_for_index_2= rulefor_index )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4811:5: this_for_index_0= rulefor_index (this_COMMA_1= RULE_COMMA this_for_index_2= rulefor_index )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulefor_index_in_rulefor_indices11241);
            this_for_index_0=rulefor_index();
            _fsp--;


            		current.merge(this_for_index_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4821:1: (this_COMMA_1= RULE_COMMA this_for_index_2= rulefor_index )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==RULE_COMMA) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4821:6: this_COMMA_1= RULE_COMMA this_for_index_2= rulefor_index
            	    {
            	    this_COMMA_1=(Token)input.LT(1);
            	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulefor_indices11262); 

            	    		current.merge(this_COMMA_1);
            	        
            	     
            	        createLeafNode(grammarAccess.getFor_indicesAccess().getCOMMATerminalRuleCall_1_0(), null); 
            	        
            	     
            	            currentNode=createCompositeNode(grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_1_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_rulefor_index_in_rulefor_indices11289);
            	    this_for_index_2=rulefor_index();
            	    _fsp--;


            	    		current.merge(this_for_index_2);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4847:1: entryRulefor_index returns [String current=null] : iv_rulefor_index= rulefor_index EOF ;
    public final String entryRulefor_index() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulefor_index = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4847:50: (iv_rulefor_index= rulefor_index EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4848:2: iv_rulefor_index= rulefor_index EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_indexRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_index_in_entryRulefor_index11335);
            iv_rulefor_index=rulefor_index();
            _fsp--;

             current =iv_rulefor_index.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_index11346); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4855:1: rulefor_index returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENT_0= RULE_IDENT (kw= 'in' this_expression_2= ruleexpression )? ) ;
    public final AntlrDatatypeRuleToken rulefor_index() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        Token kw=null;
        AntlrDatatypeRuleToken this_expression_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4861:6: ( (this_IDENT_0= RULE_IDENT (kw= 'in' this_expression_2= ruleexpression )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4862:1: (this_IDENT_0= RULE_IDENT (kw= 'in' this_expression_2= ruleexpression )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4862:1: (this_IDENT_0= RULE_IDENT (kw= 'in' this_expression_2= ruleexpression )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4862:6: this_IDENT_0= RULE_IDENT (kw= 'in' this_expression_2= ruleexpression )?
            {
            this_IDENT_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulefor_index11386); 

            		current.merge(this_IDENT_0);
                
             
                createLeafNode(grammarAccess.getFor_indexAccess().getIDENTTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4869:1: (kw= 'in' this_expression_2= ruleexpression )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==85) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4870:2: kw= 'in' this_expression_2= ruleexpression
                    {
                    kw=(Token)input.LT(1);
                    match(input,85,FOLLOW_85_in_rulefor_index11405); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getFor_indexAccess().getInKeyword_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getFor_indexAccess().getExpressionParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_in_rulefor_index11427);
                    this_expression_2=ruleexpression();
                    _fsp--;


                    		current.merge(this_expression_2);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRulesimple_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4894:1: entryRulesimple_expression returns [String current=null] : iv_rulesimple_expression= rulesimple_expression EOF ;
    public final String entryRulesimple_expression() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulesimple_expression = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4894:58: (iv_rulesimple_expression= rulesimple_expression EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4895:2: iv_rulesimple_expression= rulesimple_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSimple_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulesimple_expression_in_entryRulesimple_expression11473);
            iv_rulesimple_expression=rulesimple_expression();
            _fsp--;

             current =iv_rulesimple_expression.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulesimple_expression11484); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4902:1: rulesimple_expression returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_simple_expr_0= rulesimple_expr ;
    public final AntlrDatatypeRuleToken rulesimple_expression() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_simple_expr_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4908:6: (this_simple_expr_0= rulesimple_expr )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4910:5: this_simple_expr_0= rulesimple_expr
            {
             
                    currentNode=createCompositeNode(grammarAccess.getSimple_expressionAccess().getSimple_exprParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_rulesimple_expr_in_rulesimple_expression11530);
            this_simple_expr_0=rulesimple_expr();
            _fsp--;


            		current.merge(this_simple_expr_0);
                
             
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRulesimple_expr
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4928:1: entryRulesimple_expr returns [String current=null] : iv_rulesimple_expr= rulesimple_expr EOF ;
    public final String entryRulesimple_expr() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulesimple_expr = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4928:52: (iv_rulesimple_expr= rulesimple_expr EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4929:2: iv_rulesimple_expr= rulesimple_expr EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSimple_exprRule(), currentNode); 
            pushFollow(FOLLOW_rulesimple_expr_in_entryRulesimple_expr11573);
            iv_rulesimple_expr=rulesimple_expr();
            _fsp--;

             current =iv_rulesimple_expr.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulesimple_expr11584); 

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
    // $ANTLR end entryRulesimple_expr


    // $ANTLR start rulesimple_expr
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4936:1: rulesimple_expr returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_logical_expression_0= rulelogical_expression (this_COLON_1= RULE_COLON this_logical_expression_2= rulelogical_expression (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )? )? ) ;
    public final AntlrDatatypeRuleToken rulesimple_expr() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_COLON_1=null;
        Token this_COLON_3=null;
        AntlrDatatypeRuleToken this_logical_expression_0 = null;

        AntlrDatatypeRuleToken this_logical_expression_2 = null;

        AntlrDatatypeRuleToken this_logical_expression_4 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4942:6: ( (this_logical_expression_0= rulelogical_expression (this_COLON_1= RULE_COLON this_logical_expression_2= rulelogical_expression (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )? )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4943:1: (this_logical_expression_0= rulelogical_expression (this_COLON_1= RULE_COLON this_logical_expression_2= rulelogical_expression (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )? )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4943:1: (this_logical_expression_0= rulelogical_expression (this_COLON_1= RULE_COLON this_logical_expression_2= rulelogical_expression (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )? )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4944:5: this_logical_expression_0= rulelogical_expression (this_COLON_1= RULE_COLON this_logical_expression_2= rulelogical_expression (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )? )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getSimple_exprAccess().getLogical_expressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expr11631);
            this_logical_expression_0=rulelogical_expression();
            _fsp--;


            		current.merge(this_logical_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4954:1: (this_COLON_1= RULE_COLON this_logical_expression_2= rulelogical_expression (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )? )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==RULE_COLON) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4954:6: this_COLON_1= RULE_COLON this_logical_expression_2= rulelogical_expression (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )?
                    {
                    this_COLON_1=(Token)input.LT(1);
                    match(input,RULE_COLON,FOLLOW_RULE_COLON_in_rulesimple_expr11652); 

                    		current.merge(this_COLON_1);
                        
                     
                        createLeafNode(grammarAccess.getSimple_exprAccess().getCOLONTerminalRuleCall_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getSimple_exprAccess().getLogical_expressionParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expr11679);
                    this_logical_expression_2=rulelogical_expression();
                    _fsp--;


                    		current.merge(this_logical_expression_2);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4972:1: (this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression )?
                    int alt106=2;
                    int LA106_0 = input.LA(1);

                    if ( (LA106_0==RULE_COLON) ) {
                        alt106=1;
                    }
                    switch (alt106) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4972:6: this_COLON_3= RULE_COLON this_logical_expression_4= rulelogical_expression
                            {
                            this_COLON_3=(Token)input.LT(1);
                            match(input,RULE_COLON,FOLLOW_RULE_COLON_in_rulesimple_expr11700); 

                            		current.merge(this_COLON_3);
                                
                             
                                createLeafNode(grammarAccess.getSimple_exprAccess().getCOLONTerminalRuleCall_1_2_0(), null); 
                                
                             
                                    currentNode=createCompositeNode(grammarAccess.getSimple_exprAccess().getLogical_expressionParserRuleCall_1_2_1(), currentNode); 
                                
                            pushFollow(FOLLOW_rulelogical_expression_in_rulesimple_expr11727);
                            this_logical_expression_4=rulelogical_expression();
                            _fsp--;


                            		current.merge(this_logical_expression_4);
                                
                             
                                    currentNode = currentNode.getParent();
                                

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
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulesimple_expr


    // $ANTLR start entryRulelogical_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4998:1: entryRulelogical_expression returns [String current=null] : iv_rulelogical_expression= rulelogical_expression EOF ;
    public final String entryRulelogical_expression() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulelogical_expression = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4998:59: (iv_rulelogical_expression= rulelogical_expression EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:4999:2: iv_rulelogical_expression= rulelogical_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_expression_in_entryRulelogical_expression11775);
            iv_rulelogical_expression=rulelogical_expression();
            _fsp--;

             current =iv_rulelogical_expression.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_expression11786); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5006:1: rulelogical_expression returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_logical_term_0= rulelogical_term (kw= 'or' this_logical_term_2= rulelogical_term )* ) ;
    public final AntlrDatatypeRuleToken rulelogical_expression() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_logical_term_0 = null;

        AntlrDatatypeRuleToken this_logical_term_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5012:6: ( (this_logical_term_0= rulelogical_term (kw= 'or' this_logical_term_2= rulelogical_term )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5013:1: (this_logical_term_0= rulelogical_term (kw= 'or' this_logical_term_2= rulelogical_term )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5013:1: (this_logical_term_0= rulelogical_term (kw= 'or' this_logical_term_2= rulelogical_term )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5014:5: this_logical_term_0= rulelogical_term (kw= 'or' this_logical_term_2= rulelogical_term )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression11833);
            this_logical_term_0=rulelogical_term();
            _fsp--;


            		current.merge(this_logical_term_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5024:1: (kw= 'or' this_logical_term_2= rulelogical_term )*
            loop108:
            do {
                int alt108=2;
                int LA108_0 = input.LA(1);

                if ( (LA108_0==86) ) {
                    alt108=1;
                }


                switch (alt108) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5025:2: kw= 'or' this_logical_term_2= rulelogical_term
            	    {
            	    kw=(Token)input.LT(1);
            	    match(input,86,FOLLOW_86_in_rulelogical_expression11852); 

            	            current.merge(kw);
            	            createLeafNode(grammarAccess.getLogical_expressionAccess().getOrKeyword_1_0(), null); 
            	        
            	     
            	            currentNode=createCompositeNode(grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_1_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_rulelogical_term_in_rulelogical_expression11874);
            	    this_logical_term_2=rulelogical_term();
            	    _fsp--;


            	    		current.merge(this_logical_term_2);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop108;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5049:1: entryRulelogical_term returns [String current=null] : iv_rulelogical_term= rulelogical_term EOF ;
    public final String entryRulelogical_term() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulelogical_term = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5049:53: (iv_rulelogical_term= rulelogical_term EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5050:2: iv_rulelogical_term= rulelogical_term EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_termRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_term_in_entryRulelogical_term11920);
            iv_rulelogical_term=rulelogical_term();
            _fsp--;

             current =iv_rulelogical_term.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_term11931); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5057:1: rulelogical_term returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_logical_factor_0= rulelogical_factor (kw= 'and' this_logical_factor_2= rulelogical_factor )* ) ;
    public final AntlrDatatypeRuleToken rulelogical_term() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_logical_factor_0 = null;

        AntlrDatatypeRuleToken this_logical_factor_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5063:6: ( (this_logical_factor_0= rulelogical_factor (kw= 'and' this_logical_factor_2= rulelogical_factor )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5064:1: (this_logical_factor_0= rulelogical_factor (kw= 'and' this_logical_factor_2= rulelogical_factor )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5064:1: (this_logical_factor_0= rulelogical_factor (kw= 'and' this_logical_factor_2= rulelogical_factor )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5065:5: this_logical_factor_0= rulelogical_factor (kw= 'and' this_logical_factor_2= rulelogical_factor )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term11978);
            this_logical_factor_0=rulelogical_factor();
            _fsp--;


            		current.merge(this_logical_factor_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5075:1: (kw= 'and' this_logical_factor_2= rulelogical_factor )*
            loop109:
            do {
                int alt109=2;
                int LA109_0 = input.LA(1);

                if ( (LA109_0==87) ) {
                    alt109=1;
                }


                switch (alt109) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5076:2: kw= 'and' this_logical_factor_2= rulelogical_factor
            	    {
            	    kw=(Token)input.LT(1);
            	    match(input,87,FOLLOW_87_in_rulelogical_term11997); 

            	            current.merge(kw);
            	            createLeafNode(grammarAccess.getLogical_termAccess().getAndKeyword_1_0(), null); 
            	        
            	     
            	            currentNode=createCompositeNode(grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_1_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_rulelogical_factor_in_rulelogical_term12019);
            	    this_logical_factor_2=rulelogical_factor();
            	    _fsp--;


            	    		current.merge(this_logical_factor_2);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop109;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5100:1: entryRulelogical_factor returns [String current=null] : iv_rulelogical_factor= rulelogical_factor EOF ;
    public final String entryRulelogical_factor() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulelogical_factor = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5100:55: (iv_rulelogical_factor= rulelogical_factor EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5101:2: iv_rulelogical_factor= rulelogical_factor EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLogical_factorRule(), currentNode); 
            pushFollow(FOLLOW_rulelogical_factor_in_entryRulelogical_factor12065);
            iv_rulelogical_factor=rulelogical_factor();
            _fsp--;

             current =iv_rulelogical_factor.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulelogical_factor12076); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5108:1: rulelogical_factor returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= 'not' )? this_relation_1= rulerelation ) ;
    public final AntlrDatatypeRuleToken rulelogical_factor() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_relation_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5114:6: ( ( (kw= 'not' )? this_relation_1= rulerelation ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5115:1: ( (kw= 'not' )? this_relation_1= rulerelation )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5115:1: ( (kw= 'not' )? this_relation_1= rulerelation )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5115:2: (kw= 'not' )? this_relation_1= rulerelation
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5115:2: (kw= 'not' )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==88) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5116:2: kw= 'not'
                    {
                    kw=(Token)input.LT(1);
                    match(input,88,FOLLOW_88_in_rulelogical_factor12115); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getLogical_factorAccess().getNotKeyword_0(), null); 
                        

                    }
                    break;

            }

             
                    currentNode=createCompositeNode(grammarAccess.getLogical_factorAccess().getRelationParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_rulerelation_in_rulelogical_factor12139);
            this_relation_1=rulerelation();
            _fsp--;


            		current.merge(this_relation_1);
                
             
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5140:1: entryRulerelation returns [String current=null] : iv_rulerelation= rulerelation EOF ;
    public final String entryRulerelation() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerelation = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5140:49: (iv_rulerelation= rulerelation EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5141:2: iv_rulerelation= rulerelation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRelationRule(), currentNode); 
            pushFollow(FOLLOW_rulerelation_in_entryRulerelation12183);
            iv_rulerelation=rulerelation();
            _fsp--;

             current =iv_rulerelation.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulerelation12194); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5148:1: rulerelation returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_arithmetic_expression_0= rulearithmetic_expression ( (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT ) this_arithmetic_expression_7= rulearithmetic_expression )? ) ;
    public final AntlrDatatypeRuleToken rulerelation() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_LESS_1=null;
        Token this_LESSEQ_2=null;
        Token this_GREATER_3=null;
        Token this_GREATEREQ_4=null;
        Token this_EQEQ_5=null;
        Token this_LESSGT_6=null;
        AntlrDatatypeRuleToken this_arithmetic_expression_0 = null;

        AntlrDatatypeRuleToken this_arithmetic_expression_7 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5154:6: ( (this_arithmetic_expression_0= rulearithmetic_expression ( (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT ) this_arithmetic_expression_7= rulearithmetic_expression )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5155:1: (this_arithmetic_expression_0= rulearithmetic_expression ( (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT ) this_arithmetic_expression_7= rulearithmetic_expression )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5155:1: (this_arithmetic_expression_0= rulearithmetic_expression ( (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT ) this_arithmetic_expression_7= rulearithmetic_expression )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5156:5: this_arithmetic_expression_0= rulearithmetic_expression ( (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT ) this_arithmetic_expression_7= rulearithmetic_expression )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation12241);
            this_arithmetic_expression_0=rulearithmetic_expression();
            _fsp--;


            		current.merge(this_arithmetic_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5166:1: ( (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT ) this_arithmetic_expression_7= rulearithmetic_expression )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( ((LA112_0>=RULE_LESS && LA112_0<=RULE_LESSGT)) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5166:2: (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT ) this_arithmetic_expression_7= rulearithmetic_expression
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5166:2: (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT )
                    int alt111=6;
                    switch ( input.LA(1) ) {
                    case RULE_LESS:
                        {
                        alt111=1;
                        }
                        break;
                    case RULE_LESSEQ:
                        {
                        alt111=2;
                        }
                        break;
                    case RULE_GREATER:
                        {
                        alt111=3;
                        }
                        break;
                    case RULE_GREATEREQ:
                        {
                        alt111=4;
                        }
                        break;
                    case RULE_EQEQ:
                        {
                        alt111=5;
                        }
                        break;
                    case RULE_LESSGT:
                        {
                        alt111=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("5166:2: (this_LESS_1= RULE_LESS | this_LESSEQ_2= RULE_LESSEQ | this_GREATER_3= RULE_GREATER | this_GREATEREQ_4= RULE_GREATEREQ | this_EQEQ_5= RULE_EQEQ | this_LESSGT_6= RULE_LESSGT )", 111, 0, input);

                        throw nvae;
                    }

                    switch (alt111) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5166:7: this_LESS_1= RULE_LESS
                            {
                            this_LESS_1=(Token)input.LT(1);
                            match(input,RULE_LESS,FOLLOW_RULE_LESS_in_rulerelation12263); 

                            		current.merge(this_LESS_1);
                                
                             
                                createLeafNode(grammarAccess.getRelationAccess().getLESSTerminalRuleCall_1_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5174:10: this_LESSEQ_2= RULE_LESSEQ
                            {
                            this_LESSEQ_2=(Token)input.LT(1);
                            match(input,RULE_LESSEQ,FOLLOW_RULE_LESSEQ_in_rulerelation12289); 

                            		current.merge(this_LESSEQ_2);
                                
                             
                                createLeafNode(grammarAccess.getRelationAccess().getLESSEQTerminalRuleCall_1_0_1(), null); 
                                

                            }
                            break;
                        case 3 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5182:10: this_GREATER_3= RULE_GREATER
                            {
                            this_GREATER_3=(Token)input.LT(1);
                            match(input,RULE_GREATER,FOLLOW_RULE_GREATER_in_rulerelation12315); 

                            		current.merge(this_GREATER_3);
                                
                             
                                createLeafNode(grammarAccess.getRelationAccess().getGREATERTerminalRuleCall_1_0_2(), null); 
                                

                            }
                            break;
                        case 4 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5190:10: this_GREATEREQ_4= RULE_GREATEREQ
                            {
                            this_GREATEREQ_4=(Token)input.LT(1);
                            match(input,RULE_GREATEREQ,FOLLOW_RULE_GREATEREQ_in_rulerelation12341); 

                            		current.merge(this_GREATEREQ_4);
                                
                             
                                createLeafNode(grammarAccess.getRelationAccess().getGREATEREQTerminalRuleCall_1_0_3(), null); 
                                

                            }
                            break;
                        case 5 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5198:10: this_EQEQ_5= RULE_EQEQ
                            {
                            this_EQEQ_5=(Token)input.LT(1);
                            match(input,RULE_EQEQ,FOLLOW_RULE_EQEQ_in_rulerelation12367); 

                            		current.merge(this_EQEQ_5);
                                
                             
                                createLeafNode(grammarAccess.getRelationAccess().getEQEQTerminalRuleCall_1_0_4(), null); 
                                

                            }
                            break;
                        case 6 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5206:10: this_LESSGT_6= RULE_LESSGT
                            {
                            this_LESSGT_6=(Token)input.LT(1);
                            match(input,RULE_LESSGT,FOLLOW_RULE_LESSGT_in_rulerelation12393); 

                            		current.merge(this_LESSGT_6);
                                
                             
                                createLeafNode(grammarAccess.getRelationAccess().getLESSGTTerminalRuleCall_1_0_5(), null); 
                                

                            }
                            break;

                    }

                     
                            currentNode=createCompositeNode(grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulearithmetic_expression_in_rulerelation12421);
                    this_arithmetic_expression_7=rulearithmetic_expression();
                    _fsp--;


                    		current.merge(this_arithmetic_expression_7);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5232:1: entryRulearithmetic_expression returns [String current=null] : iv_rulearithmetic_expression= rulearithmetic_expression EOF ;
    public final String entryRulearithmetic_expression() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulearithmetic_expression = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5232:62: (iv_rulearithmetic_expression= rulearithmetic_expression EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5233:2: iv_rulearithmetic_expression= rulearithmetic_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArithmetic_expressionRule(), currentNode); 
            pushFollow(FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression12467);
            iv_rulearithmetic_expression=rulearithmetic_expression();
            _fsp--;

             current =iv_rulearithmetic_expression.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulearithmetic_expression12478); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5240:1: rulearithmetic_expression returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_unary_arithmetic_expression_0= ruleunary_arithmetic_expression ( (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW ) this_term_5= ruleterm )* ) ;
    public final AntlrDatatypeRuleToken rulearithmetic_expression() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_PLUS_1=null;
        Token this_MINUS_2=null;
        Token this_PLUS_EW_3=null;
        Token this_MINUS_EW_4=null;
        AntlrDatatypeRuleToken this_unary_arithmetic_expression_0 = null;

        AntlrDatatypeRuleToken this_term_5 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5246:6: ( (this_unary_arithmetic_expression_0= ruleunary_arithmetic_expression ( (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW ) this_term_5= ruleterm )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5247:1: (this_unary_arithmetic_expression_0= ruleunary_arithmetic_expression ( (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW ) this_term_5= ruleterm )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5247:1: (this_unary_arithmetic_expression_0= ruleunary_arithmetic_expression ( (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW ) this_term_5= ruleterm )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5248:5: this_unary_arithmetic_expression_0= ruleunary_arithmetic_expression ( (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW ) this_term_5= ruleterm )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getUnary_arithmetic_expressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleunary_arithmetic_expression_in_rulearithmetic_expression12525);
            this_unary_arithmetic_expression_0=ruleunary_arithmetic_expression();
            _fsp--;


            		current.merge(this_unary_arithmetic_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5258:1: ( (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW ) this_term_5= ruleterm )*
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( ((LA114_0>=RULE_PLUS && LA114_0<=RULE_MINUS_EW)) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5258:2: (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW ) this_term_5= ruleterm
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5258:2: (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW )
            	    int alt113=4;
            	    switch ( input.LA(1) ) {
            	    case RULE_PLUS:
            	        {
            	        alt113=1;
            	        }
            	        break;
            	    case RULE_MINUS:
            	        {
            	        alt113=2;
            	        }
            	        break;
            	    case RULE_PLUS_EW:
            	        {
            	        alt113=3;
            	        }
            	        break;
            	    case RULE_MINUS_EW:
            	        {
            	        alt113=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("5258:2: (this_PLUS_1= RULE_PLUS | this_MINUS_2= RULE_MINUS | this_PLUS_EW_3= RULE_PLUS_EW | this_MINUS_EW_4= RULE_MINUS_EW )", 113, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt113) {
            	        case 1 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5258:7: this_PLUS_1= RULE_PLUS
            	            {
            	            this_PLUS_1=(Token)input.LT(1);
            	            match(input,RULE_PLUS,FOLLOW_RULE_PLUS_in_rulearithmetic_expression12547); 

            	            		current.merge(this_PLUS_1);
            	                
            	             
            	                createLeafNode(grammarAccess.getArithmetic_expressionAccess().getPLUSTerminalRuleCall_1_0_0(), null); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5266:10: this_MINUS_2= RULE_MINUS
            	            {
            	            this_MINUS_2=(Token)input.LT(1);
            	            match(input,RULE_MINUS,FOLLOW_RULE_MINUS_in_rulearithmetic_expression12573); 

            	            		current.merge(this_MINUS_2);
            	                
            	             
            	                createLeafNode(grammarAccess.getArithmetic_expressionAccess().getMINUSTerminalRuleCall_1_0_1(), null); 
            	                

            	            }
            	            break;
            	        case 3 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5274:10: this_PLUS_EW_3= RULE_PLUS_EW
            	            {
            	            this_PLUS_EW_3=(Token)input.LT(1);
            	            match(input,RULE_PLUS_EW,FOLLOW_RULE_PLUS_EW_in_rulearithmetic_expression12599); 

            	            		current.merge(this_PLUS_EW_3);
            	                
            	             
            	                createLeafNode(grammarAccess.getArithmetic_expressionAccess().getPLUS_EWTerminalRuleCall_1_0_2(), null); 
            	                

            	            }
            	            break;
            	        case 4 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5282:10: this_MINUS_EW_4= RULE_MINUS_EW
            	            {
            	            this_MINUS_EW_4=(Token)input.LT(1);
            	            match(input,RULE_MINUS_EW,FOLLOW_RULE_MINUS_EW_in_rulearithmetic_expression12625); 

            	            		current.merge(this_MINUS_EW_4);
            	                
            	             
            	                createLeafNode(grammarAccess.getArithmetic_expressionAccess().getMINUS_EWTerminalRuleCall_1_0_3(), null); 
            	                

            	            }
            	            break;

            	    }

            	     
            	            currentNode=createCompositeNode(grammarAccess.getArithmetic_expressionAccess().getTermParserRuleCall_1_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_ruleterm_in_rulearithmetic_expression12653);
            	    this_term_5=ruleterm();
            	    _fsp--;


            	    		current.merge(this_term_5);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop114;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRuleunary_arithmetic_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5308:1: entryRuleunary_arithmetic_expression returns [String current=null] : iv_ruleunary_arithmetic_expression= ruleunary_arithmetic_expression EOF ;
    public final String entryRuleunary_arithmetic_expression() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleunary_arithmetic_expression = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5308:68: (iv_ruleunary_arithmetic_expression= ruleunary_arithmetic_expression EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5309:2: iv_ruleunary_arithmetic_expression= ruleunary_arithmetic_expression EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUnary_arithmetic_expressionRule(), currentNode); 
            pushFollow(FOLLOW_ruleunary_arithmetic_expression_in_entryRuleunary_arithmetic_expression12699);
            iv_ruleunary_arithmetic_expression=ruleunary_arithmetic_expression();
            _fsp--;

             current =iv_ruleunary_arithmetic_expression.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleunary_arithmetic_expression12710); 

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
    // $ANTLR end entryRuleunary_arithmetic_expression


    // $ANTLR start ruleunary_arithmetic_expression
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5316:1: ruleunary_arithmetic_expression returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_PLUS_0= RULE_PLUS this_term_1= ruleterm ) | (this_MINUS_2= RULE_MINUS this_term_3= ruleterm ) | (this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm ) | (this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm ) | this_term_8= ruleterm ) ;
    public final AntlrDatatypeRuleToken ruleunary_arithmetic_expression() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_PLUS_0=null;
        Token this_MINUS_2=null;
        Token this_PLUS_EW_4=null;
        Token this_MINUS_EW_6=null;
        AntlrDatatypeRuleToken this_term_1 = null;

        AntlrDatatypeRuleToken this_term_3 = null;

        AntlrDatatypeRuleToken this_term_5 = null;

        AntlrDatatypeRuleToken this_term_7 = null;

        AntlrDatatypeRuleToken this_term_8 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5322:6: ( ( (this_PLUS_0= RULE_PLUS this_term_1= ruleterm ) | (this_MINUS_2= RULE_MINUS this_term_3= ruleterm ) | (this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm ) | (this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm ) | this_term_8= ruleterm ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5323:1: ( (this_PLUS_0= RULE_PLUS this_term_1= ruleterm ) | (this_MINUS_2= RULE_MINUS this_term_3= ruleterm ) | (this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm ) | (this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm ) | this_term_8= ruleterm )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5323:1: ( (this_PLUS_0= RULE_PLUS this_term_1= ruleterm ) | (this_MINUS_2= RULE_MINUS this_term_3= ruleterm ) | (this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm ) | (this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm ) | this_term_8= ruleterm )
            int alt115=5;
            switch ( input.LA(1) ) {
            case RULE_PLUS:
                {
                alt115=1;
                }
                break;
            case RULE_MINUS:
                {
                alt115=2;
                }
                break;
            case RULE_PLUS_EW:
                {
                alt115=3;
                }
                break;
            case RULE_MINUS_EW:
                {
                alt115=4;
                }
                break;
            case RULE_IDENT:
            case RULE_LPAR:
            case RULE_STRING:
            case RULE_UNSIGNED_INTEGER:
            case RULE_UNSIGNED_REAL:
            case RULE_LBRACK:
            case RULE_LBRACE:
            case 48:
            case 50:
            case 73:
            case 89:
            case 90:
                {
                alt115=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("5323:1: ( (this_PLUS_0= RULE_PLUS this_term_1= ruleterm ) | (this_MINUS_2= RULE_MINUS this_term_3= ruleterm ) | (this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm ) | (this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm ) | this_term_8= ruleterm )", 115, 0, input);

                throw nvae;
            }

            switch (alt115) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5323:2: (this_PLUS_0= RULE_PLUS this_term_1= ruleterm )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5323:2: (this_PLUS_0= RULE_PLUS this_term_1= ruleterm )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5323:7: this_PLUS_0= RULE_PLUS this_term_1= ruleterm
                    {
                    this_PLUS_0=(Token)input.LT(1);
                    match(input,RULE_PLUS,FOLLOW_RULE_PLUS_in_ruleunary_arithmetic_expression12751); 

                    		current.merge(this_PLUS_0);
                        
                     
                        createLeafNode(grammarAccess.getUnary_arithmetic_expressionAccess().getPLUSTerminalRuleCall_0_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getUnary_arithmetic_expressionAccess().getTermParserRuleCall_0_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12778);
                    this_term_1=ruleterm();
                    _fsp--;


                    		current.merge(this_term_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5342:6: (this_MINUS_2= RULE_MINUS this_term_3= ruleterm )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5342:6: (this_MINUS_2= RULE_MINUS this_term_3= ruleterm )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5342:11: this_MINUS_2= RULE_MINUS this_term_3= ruleterm
                    {
                    this_MINUS_2=(Token)input.LT(1);
                    match(input,RULE_MINUS,FOLLOW_RULE_MINUS_in_ruleunary_arithmetic_expression12806); 

                    		current.merge(this_MINUS_2);
                        
                     
                        createLeafNode(grammarAccess.getUnary_arithmetic_expressionAccess().getMINUSTerminalRuleCall_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getUnary_arithmetic_expressionAccess().getTermParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12833);
                    this_term_3=ruleterm();
                    _fsp--;


                    		current.merge(this_term_3);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5361:6: (this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5361:6: (this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5361:11: this_PLUS_EW_4= RULE_PLUS_EW this_term_5= ruleterm
                    {
                    this_PLUS_EW_4=(Token)input.LT(1);
                    match(input,RULE_PLUS_EW,FOLLOW_RULE_PLUS_EW_in_ruleunary_arithmetic_expression12861); 

                    		current.merge(this_PLUS_EW_4);
                        
                     
                        createLeafNode(grammarAccess.getUnary_arithmetic_expressionAccess().getPLUS_EWTerminalRuleCall_2_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getUnary_arithmetic_expressionAccess().getTermParserRuleCall_2_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12888);
                    this_term_5=ruleterm();
                    _fsp--;


                    		current.merge(this_term_5);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5380:6: (this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5380:6: (this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5380:11: this_MINUS_EW_6= RULE_MINUS_EW this_term_7= ruleterm
                    {
                    this_MINUS_EW_6=(Token)input.LT(1);
                    match(input,RULE_MINUS_EW,FOLLOW_RULE_MINUS_EW_in_ruleunary_arithmetic_expression12916); 

                    		current.merge(this_MINUS_EW_6);
                        
                     
                        createLeafNode(grammarAccess.getUnary_arithmetic_expressionAccess().getMINUS_EWTerminalRuleCall_3_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getUnary_arithmetic_expressionAccess().getTermParserRuleCall_3_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12943);
                    this_term_7=ruleterm();
                    _fsp--;


                    		current.merge(this_term_7);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;
                case 5 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5400:5: this_term_8= ruleterm
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getUnary_arithmetic_expressionAccess().getTermParserRuleCall_4(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12977);
                    this_term_8=ruleterm();
                    _fsp--;


                    		current.merge(this_term_8);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleunary_arithmetic_expression


    // $ANTLR start entryRuleterm
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5418:1: entryRuleterm returns [String current=null] : iv_ruleterm= ruleterm EOF ;
    public final String entryRuleterm() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleterm = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5418:45: (iv_ruleterm= ruleterm EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5419:2: iv_ruleterm= ruleterm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTermRule(), currentNode); 
            pushFollow(FOLLOW_ruleterm_in_entryRuleterm13021);
            iv_ruleterm=ruleterm();
            _fsp--;

             current =iv_ruleterm.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleterm13032); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5426:1: ruleterm returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_factor_0= rulefactor ( (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW ) this_factor_5= rulefactor )* ) ;
    public final AntlrDatatypeRuleToken ruleterm() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STAR_1=null;
        Token this_SLASH_2=null;
        Token this_STAR_EW_3=null;
        Token this_SLASH_EW_4=null;
        AntlrDatatypeRuleToken this_factor_0 = null;

        AntlrDatatypeRuleToken this_factor_5 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5432:6: ( (this_factor_0= rulefactor ( (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW ) this_factor_5= rulefactor )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5433:1: (this_factor_0= rulefactor ( (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW ) this_factor_5= rulefactor )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5433:1: (this_factor_0= rulefactor ( (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW ) this_factor_5= rulefactor )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5434:5: this_factor_0= rulefactor ( (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW ) this_factor_5= rulefactor )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getTermAccess().getFactorParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulefactor_in_ruleterm13079);
            this_factor_0=rulefactor();
            _fsp--;


            		current.merge(this_factor_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5444:1: ( (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW ) this_factor_5= rulefactor )*
            loop117:
            do {
                int alt117=2;
                int LA117_0 = input.LA(1);

                if ( ((LA117_0>=RULE_STAR && LA117_0<=RULE_SLASH_EW)) ) {
                    alt117=1;
                }


                switch (alt117) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5444:2: (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW ) this_factor_5= rulefactor
            	    {
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5444:2: (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW )
            	    int alt116=4;
            	    switch ( input.LA(1) ) {
            	    case RULE_STAR:
            	        {
            	        alt116=1;
            	        }
            	        break;
            	    case RULE_SLASH:
            	        {
            	        alt116=2;
            	        }
            	        break;
            	    case RULE_STAR_EW:
            	        {
            	        alt116=3;
            	        }
            	        break;
            	    case RULE_SLASH_EW:
            	        {
            	        alt116=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("5444:2: (this_STAR_1= RULE_STAR | this_SLASH_2= RULE_SLASH | this_STAR_EW_3= RULE_STAR_EW | this_SLASH_EW_4= RULE_SLASH_EW )", 116, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt116) {
            	        case 1 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5444:7: this_STAR_1= RULE_STAR
            	            {
            	            this_STAR_1=(Token)input.LT(1);
            	            match(input,RULE_STAR,FOLLOW_RULE_STAR_in_ruleterm13101); 

            	            		current.merge(this_STAR_1);
            	                
            	             
            	                createLeafNode(grammarAccess.getTermAccess().getSTARTerminalRuleCall_1_0_0(), null); 
            	                

            	            }
            	            break;
            	        case 2 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5452:10: this_SLASH_2= RULE_SLASH
            	            {
            	            this_SLASH_2=(Token)input.LT(1);
            	            match(input,RULE_SLASH,FOLLOW_RULE_SLASH_in_ruleterm13127); 

            	            		current.merge(this_SLASH_2);
            	                
            	             
            	                createLeafNode(grammarAccess.getTermAccess().getSLASHTerminalRuleCall_1_0_1(), null); 
            	                

            	            }
            	            break;
            	        case 3 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5460:10: this_STAR_EW_3= RULE_STAR_EW
            	            {
            	            this_STAR_EW_3=(Token)input.LT(1);
            	            match(input,RULE_STAR_EW,FOLLOW_RULE_STAR_EW_in_ruleterm13153); 

            	            		current.merge(this_STAR_EW_3);
            	                
            	             
            	                createLeafNode(grammarAccess.getTermAccess().getSTAR_EWTerminalRuleCall_1_0_2(), null); 
            	                

            	            }
            	            break;
            	        case 4 :
            	            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5468:10: this_SLASH_EW_4= RULE_SLASH_EW
            	            {
            	            this_SLASH_EW_4=(Token)input.LT(1);
            	            match(input,RULE_SLASH_EW,FOLLOW_RULE_SLASH_EW_in_ruleterm13179); 

            	            		current.merge(this_SLASH_EW_4);
            	                
            	             
            	                createLeafNode(grammarAccess.getTermAccess().getSLASH_EWTerminalRuleCall_1_0_3(), null); 
            	                

            	            }
            	            break;

            	    }

            	     
            	            currentNode=createCompositeNode(grammarAccess.getTermAccess().getFactorParserRuleCall_1_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_rulefactor_in_ruleterm13207);
            	    this_factor_5=rulefactor();
            	    _fsp--;


            	    		current.merge(this_factor_5);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop117;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5494:1: entryRulefactor returns [String current=null] : iv_rulefactor= rulefactor EOF ;
    public final String entryRulefactor() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulefactor = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5494:47: (iv_rulefactor= rulefactor EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5495:2: iv_rulefactor= rulefactor EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFactorRule(), currentNode); 
            pushFollow(FOLLOW_rulefactor_in_entryRulefactor13253);
            iv_rulefactor=rulefactor();
            _fsp--;

             current =iv_rulefactor.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulefactor13264); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5502:1: rulefactor returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_primary_0= ruleprimary ( (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW ) this_primary_3= ruleprimary )? ) ;
    public final AntlrDatatypeRuleToken rulefactor() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_POWER_1=null;
        Token this_POWER_EW_2=null;
        AntlrDatatypeRuleToken this_primary_0 = null;

        AntlrDatatypeRuleToken this_primary_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5508:6: ( (this_primary_0= ruleprimary ( (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW ) this_primary_3= ruleprimary )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5509:1: (this_primary_0= ruleprimary ( (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW ) this_primary_3= ruleprimary )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5509:1: (this_primary_0= ruleprimary ( (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW ) this_primary_3= ruleprimary )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5510:5: this_primary_0= ruleprimary ( (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW ) this_primary_3= ruleprimary )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFactorAccess().getPrimaryParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleprimary_in_rulefactor13311);
            this_primary_0=ruleprimary();
            _fsp--;


            		current.merge(this_primary_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5520:1: ( (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW ) this_primary_3= ruleprimary )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( ((LA119_0>=RULE_POWER && LA119_0<=RULE_POWER_EW)) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5520:2: (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW ) this_primary_3= ruleprimary
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5520:2: (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW )
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==RULE_POWER) ) {
                        alt118=1;
                    }
                    else if ( (LA118_0==RULE_POWER_EW) ) {
                        alt118=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("5520:2: (this_POWER_1= RULE_POWER | this_POWER_EW_2= RULE_POWER_EW )", 118, 0, input);

                        throw nvae;
                    }
                    switch (alt118) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5520:7: this_POWER_1= RULE_POWER
                            {
                            this_POWER_1=(Token)input.LT(1);
                            match(input,RULE_POWER,FOLLOW_RULE_POWER_in_rulefactor13333); 

                            		current.merge(this_POWER_1);
                                
                             
                                createLeafNode(grammarAccess.getFactorAccess().getPOWERTerminalRuleCall_1_0_0(), null); 
                                

                            }
                            break;
                        case 2 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5528:10: this_POWER_EW_2= RULE_POWER_EW
                            {
                            this_POWER_EW_2=(Token)input.LT(1);
                            match(input,RULE_POWER_EW,FOLLOW_RULE_POWER_EW_in_rulefactor13359); 

                            		current.merge(this_POWER_EW_2);
                                
                             
                                createLeafNode(grammarAccess.getFactorAccess().getPOWER_EWTerminalRuleCall_1_0_1(), null); 
                                

                            }
                            break;

                    }

                     
                            currentNode=createCompositeNode(grammarAccess.getFactorAccess().getPrimaryParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleprimary_in_rulefactor13387);
                    this_primary_3=ruleprimary();
                    _fsp--;


                    		current.merge(this_primary_3);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5554:1: entryRuleprimary returns [String current=null] : iv_ruleprimary= ruleprimary EOF ;
    public final String entryRuleprimary() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleprimary = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5554:48: (iv_ruleprimary= ruleprimary EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5555:2: iv_ruleprimary= ruleprimary EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPrimaryRule(), currentNode); 
            pushFollow(FOLLOW_ruleprimary_in_entryRuleprimary13433);
            iv_ruleprimary=ruleprimary();
            _fsp--;

             current =iv_ruleprimary.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleprimary13444); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5562:1: ruleprimary returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_UNSIGNED_INTEGER_0= RULE_UNSIGNED_INTEGER | this_UNSIGNED_REAL_1= RULE_UNSIGNED_REAL | this_STRING_2= RULE_STRING | kw= 'false' | kw= 'true' | this_component_reference__function_call_5= rulecomponent_reference__function_call | (kw= 'der' this_function_call_7= rulefunction_call ) | (this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR ) | (this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK ) | (this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE ) | kw= 'end' ) ;
    public final AntlrDatatypeRuleToken ruleprimary() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_UNSIGNED_INTEGER_0=null;
        Token this_UNSIGNED_REAL_1=null;
        Token this_STRING_2=null;
        Token kw=null;
        Token this_LPAR_8=null;
        Token this_RPAR_10=null;
        Token this_LBRACK_11=null;
        Token this_SEMICOLON_13=null;
        Token this_RBRACK_15=null;
        Token this_LBRACE_16=null;
        Token this_RBRACE_18=null;
        AntlrDatatypeRuleToken this_component_reference__function_call_5 = null;

        AntlrDatatypeRuleToken this_function_call_7 = null;

        AntlrDatatypeRuleToken this_expression_list_9 = null;

        AntlrDatatypeRuleToken this_expression_list_12 = null;

        AntlrDatatypeRuleToken this_expression_list_14 = null;

        AntlrDatatypeRuleToken this_for_or_expression_list_17 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5568:6: ( (this_UNSIGNED_INTEGER_0= RULE_UNSIGNED_INTEGER | this_UNSIGNED_REAL_1= RULE_UNSIGNED_REAL | this_STRING_2= RULE_STRING | kw= 'false' | kw= 'true' | this_component_reference__function_call_5= rulecomponent_reference__function_call | (kw= 'der' this_function_call_7= rulefunction_call ) | (this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR ) | (this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK ) | (this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE ) | kw= 'end' ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5569:1: (this_UNSIGNED_INTEGER_0= RULE_UNSIGNED_INTEGER | this_UNSIGNED_REAL_1= RULE_UNSIGNED_REAL | this_STRING_2= RULE_STRING | kw= 'false' | kw= 'true' | this_component_reference__function_call_5= rulecomponent_reference__function_call | (kw= 'der' this_function_call_7= rulefunction_call ) | (this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR ) | (this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK ) | (this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE ) | kw= 'end' )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5569:1: (this_UNSIGNED_INTEGER_0= RULE_UNSIGNED_INTEGER | this_UNSIGNED_REAL_1= RULE_UNSIGNED_REAL | this_STRING_2= RULE_STRING | kw= 'false' | kw= 'true' | this_component_reference__function_call_5= rulecomponent_reference__function_call | (kw= 'der' this_function_call_7= rulefunction_call ) | (this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR ) | (this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK ) | (this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE ) | kw= 'end' )
            int alt121=11;
            switch ( input.LA(1) ) {
            case RULE_UNSIGNED_INTEGER:
                {
                alt121=1;
                }
                break;
            case RULE_UNSIGNED_REAL:
                {
                alt121=2;
                }
                break;
            case RULE_STRING:
                {
                alt121=3;
                }
                break;
            case 89:
                {
                alt121=4;
                }
                break;
            case 90:
                {
                alt121=5;
                }
                break;
            case RULE_IDENT:
            case 73:
                {
                alt121=6;
                }
                break;
            case 50:
                {
                alt121=7;
                }
                break;
            case RULE_LPAR:
                {
                alt121=8;
                }
                break;
            case RULE_LBRACK:
                {
                alt121=9;
                }
                break;
            case RULE_LBRACE:
                {
                alt121=10;
                }
                break;
            case 48:
                {
                alt121=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("5569:1: (this_UNSIGNED_INTEGER_0= RULE_UNSIGNED_INTEGER | this_UNSIGNED_REAL_1= RULE_UNSIGNED_REAL | this_STRING_2= RULE_STRING | kw= 'false' | kw= 'true' | this_component_reference__function_call_5= rulecomponent_reference__function_call | (kw= 'der' this_function_call_7= rulefunction_call ) | (this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR ) | (this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK ) | (this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE ) | kw= 'end' )", 121, 0, input);

                throw nvae;
            }

            switch (alt121) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5569:6: this_UNSIGNED_INTEGER_0= RULE_UNSIGNED_INTEGER
                    {
                    this_UNSIGNED_INTEGER_0=(Token)input.LT(1);
                    match(input,RULE_UNSIGNED_INTEGER,FOLLOW_RULE_UNSIGNED_INTEGER_in_ruleprimary13484); 

                    		current.merge(this_UNSIGNED_INTEGER_0);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getUNSIGNED_INTEGERTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5577:10: this_UNSIGNED_REAL_1= RULE_UNSIGNED_REAL
                    {
                    this_UNSIGNED_REAL_1=(Token)input.LT(1);
                    match(input,RULE_UNSIGNED_REAL,FOLLOW_RULE_UNSIGNED_REAL_in_ruleprimary13510); 

                    		current.merge(this_UNSIGNED_REAL_1);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getUNSIGNED_REALTerminalRuleCall_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5585:10: this_STRING_2= RULE_STRING
                    {
                    this_STRING_2=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleprimary13536); 

                    		current.merge(this_STRING_2);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getSTRINGTerminalRuleCall_2(), null); 
                        

                    }
                    break;
                case 4 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5594:2: kw= 'false'
                    {
                    kw=(Token)input.LT(1);
                    match(input,89,FOLLOW_89_in_ruleprimary13560); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getPrimaryAccess().getFalseKeyword_3(), null); 
                        

                    }
                    break;
                case 5 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5601:2: kw= 'true'
                    {
                    kw=(Token)input.LT(1);
                    match(input,90,FOLLOW_90_in_ruleprimary13579); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getPrimaryAccess().getTrueKeyword_4(), null); 
                        

                    }
                    break;
                case 6 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5608:5: this_component_reference__function_call_5= rulecomponent_reference__function_call
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getComponent_reference__function_callParserRuleCall_5(), currentNode); 
                        
                    pushFollow(FOLLOW_rulecomponent_reference__function_call_in_ruleprimary13607);
                    this_component_reference__function_call_5=rulecomponent_reference__function_call();
                    _fsp--;


                    		current.merge(this_component_reference__function_call_5);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 7 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5619:6: (kw= 'der' this_function_call_7= rulefunction_call )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5619:6: (kw= 'der' this_function_call_7= rulefunction_call )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5620:2: kw= 'der' this_function_call_7= rulefunction_call
                    {
                    kw=(Token)input.LT(1);
                    match(input,50,FOLLOW_50_in_ruleprimary13632); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getPrimaryAccess().getDerKeyword_6_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getFunction_callParserRuleCall_6_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulefunction_call_in_ruleprimary13654);
                    this_function_call_7=rulefunction_call();
                    _fsp--;


                    		current.merge(this_function_call_7);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;
                case 8 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5637:6: (this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5637:6: (this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5637:11: this_LPAR_8= RULE_LPAR this_expression_list_9= ruleexpression_list this_RPAR_10= RULE_RPAR
                    {
                    this_LPAR_8=(Token)input.LT(1);
                    match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_ruleprimary13682); 

                    		current.merge(this_LPAR_8);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getLPARTerminalRuleCall_7_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listParserRuleCall_7_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary13709);
                    this_expression_list_9=ruleexpression_list();
                    _fsp--;


                    		current.merge(this_expression_list_9);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    this_RPAR_10=(Token)input.LT(1);
                    match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_ruleprimary13729); 

                    		current.merge(this_RPAR_10);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getRPARTerminalRuleCall_7_2(), null); 
                        

                    }


                    }
                    break;
                case 9 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5663:6: (this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5663:6: (this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5663:11: this_LBRACK_11= RULE_LBRACK this_expression_list_12= ruleexpression_list (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )* this_RBRACK_15= RULE_RBRACK
                    {
                    this_LBRACK_11=(Token)input.LT(1);
                    match(input,RULE_LBRACK,FOLLOW_RULE_LBRACK_in_ruleprimary13757); 

                    		current.merge(this_LBRACK_11);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getLBRACKTerminalRuleCall_8_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listParserRuleCall_8_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary13784);
                    this_expression_list_12=ruleexpression_list();
                    _fsp--;


                    		current.merge(this_expression_list_12);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5681:1: (this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list )*
                    loop120:
                    do {
                        int alt120=2;
                        int LA120_0 = input.LA(1);

                        if ( (LA120_0==RULE_SEMICOLON) ) {
                            alt120=1;
                        }


                        switch (alt120) {
                    	case 1 :
                    	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5681:6: this_SEMICOLON_13= RULE_SEMICOLON this_expression_list_14= ruleexpression_list
                    	    {
                    	    this_SEMICOLON_13=(Token)input.LT(1);
                    	    match(input,RULE_SEMICOLON,FOLLOW_RULE_SEMICOLON_in_ruleprimary13805); 

                    	    		current.merge(this_SEMICOLON_13);
                    	        
                    	     
                    	        createLeafNode(grammarAccess.getPrimaryAccess().getSEMICOLONTerminalRuleCall_8_2_0(), null); 
                    	        
                    	     
                    	            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getExpression_listParserRuleCall_8_2_1(), currentNode); 
                    	        
                    	    pushFollow(FOLLOW_ruleexpression_list_in_ruleprimary13832);
                    	    this_expression_list_14=ruleexpression_list();
                    	    _fsp--;


                    	    		current.merge(this_expression_list_14);
                    	        
                    	     
                    	            currentNode = currentNode.getParent();
                    	        

                    	    }
                    	    break;

                    	default :
                    	    break loop120;
                        }
                    } while (true);

                    this_RBRACK_15=(Token)input.LT(1);
                    match(input,RULE_RBRACK,FOLLOW_RULE_RBRACK_in_ruleprimary13854); 

                    		current.merge(this_RBRACK_15);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getRBRACKTerminalRuleCall_8_3(), null); 
                        

                    }


                    }
                    break;
                case 10 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5707:6: (this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5707:6: (this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5707:11: this_LBRACE_16= RULE_LBRACE this_for_or_expression_list_17= rulefor_or_expression_list this_RBRACE_18= RULE_RBRACE
                    {
                    this_LBRACE_16=(Token)input.LT(1);
                    match(input,RULE_LBRACE,FOLLOW_RULE_LBRACE_in_ruleprimary13882); 

                    		current.merge(this_LBRACE_16);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getLBRACETerminalRuleCall_9_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getPrimaryAccess().getFor_or_expression_listParserRuleCall_9_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulefor_or_expression_list_in_ruleprimary13909);
                    this_for_or_expression_list_17=rulefor_or_expression_list();
                    _fsp--;


                    		current.merge(this_for_or_expression_list_17);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    this_RBRACE_18=(Token)input.LT(1);
                    match(input,RULE_RBRACE,FOLLOW_RULE_RBRACE_in_ruleprimary13929); 

                    		current.merge(this_RBRACE_18);
                        
                     
                        createLeafNode(grammarAccess.getPrimaryAccess().getRBRACETerminalRuleCall_9_2(), null); 
                        

                    }


                    }
                    break;
                case 11 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5734:2: kw= 'end'
                    {
                    kw=(Token)input.LT(1);
                    match(input,48,FOLLOW_48_in_ruleprimary13954); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getPrimaryAccess().getEndKeyword_10(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRulecomponent_reference__function_call
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5747:1: entryRulecomponent_reference__function_call returns [String current=null] : iv_rulecomponent_reference__function_call= rulecomponent_reference__function_call EOF ;
    public final String entryRulecomponent_reference__function_call() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulecomponent_reference__function_call = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5747:75: (iv_rulecomponent_reference__function_call= rulecomponent_reference__function_call EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5748:2: iv_rulecomponent_reference__function_call= rulecomponent_reference__function_call EOF
            {
             currentNode = createCompositeNode(grammarAccess.getComponent_reference__function_callRule(), currentNode); 
            pushFollow(FOLLOW_rulecomponent_reference__function_call_in_entryRulecomponent_reference__function_call13993);
            iv_rulecomponent_reference__function_call=rulecomponent_reference__function_call();
            _fsp--;

             current =iv_rulecomponent_reference__function_call.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_reference__function_call14004); 

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
    // $ANTLR end entryRulecomponent_reference__function_call


    // $ANTLR start rulecomponent_reference__function_call
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5755:1: rulecomponent_reference__function_call returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )? ) | (kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR ) ) ;
    public final AntlrDatatypeRuleToken rulecomponent_reference__function_call() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_LPAR_3=null;
        Token this_RPAR_4=null;
        AntlrDatatypeRuleToken this_component_reference_0 = null;

        AntlrDatatypeRuleToken this_function_call_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5761:6: ( ( (this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )? ) | (kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5762:1: ( (this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )? ) | (kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5762:1: ( (this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )? ) | (kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR ) )
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==RULE_IDENT) ) {
                alt123=1;
            }
            else if ( (LA123_0==73) ) {
                alt123=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5762:1: ( (this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )? ) | (kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR ) )", 123, 0, input);

                throw nvae;
            }
            switch (alt123) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5762:2: (this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )? )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5762:2: (this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )? )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5763:5: this_component_reference_0= rulecomponent_reference (this_function_call_1= rulefunction_call )?
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getComponent_reference__function_callAccess().getComponent_referenceParserRuleCall_0_0(), currentNode); 
                        
                    pushFollow(FOLLOW_rulecomponent_reference_in_rulecomponent_reference__function_call14052);
                    this_component_reference_0=rulecomponent_reference();
                    _fsp--;


                    		current.merge(this_component_reference_0);
                        
                     
                            currentNode = currentNode.getParent();
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5773:1: (this_function_call_1= rulefunction_call )?
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==RULE_LPAR) ) {
                        alt122=1;
                    }
                    switch (alt122) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5774:5: this_function_call_1= rulefunction_call
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getComponent_reference__function_callAccess().getFunction_callParserRuleCall_0_1(), currentNode); 
                                
                            pushFollow(FOLLOW_rulefunction_call_in_rulecomponent_reference__function_call14080);
                            this_function_call_1=rulefunction_call();
                            _fsp--;


                            		current.merge(this_function_call_1);
                                
                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5785:6: (kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5785:6: (kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5786:2: kw= 'initial' this_LPAR_3= RULE_LPAR this_RPAR_4= RULE_RPAR
                    {
                    kw=(Token)input.LT(1);
                    match(input,73,FOLLOW_73_in_rulecomponent_reference__function_call14108); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getComponent_reference__function_callAccess().getInitialKeyword_1_0(), null); 
                        
                    this_LPAR_3=(Token)input.LT(1);
                    match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_rulecomponent_reference__function_call14123); 

                    		current.merge(this_LPAR_3);
                        
                     
                        createLeafNode(grammarAccess.getComponent_reference__function_callAccess().getLPARTerminalRuleCall_1_1(), null); 
                        
                    this_RPAR_4=(Token)input.LT(1);
                    match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_rulecomponent_reference__function_call14143); 

                    		current.merge(this_RPAR_4);
                        
                     
                        createLeafNode(grammarAccess.getComponent_reference__function_callAccess().getRPARTerminalRuleCall_1_2(), null); 
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulecomponent_reference__function_call


    // $ANTLR start entryRulename_path
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5813:1: entryRulename_path returns [String current=null] : iv_rulename_path= rulename_path EOF ;
    public final String entryRulename_path() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulename_path = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5813:50: (iv_rulename_path= rulename_path EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5814:2: iv_rulename_path= rulename_path EOF
            {
             currentNode = createCompositeNode(grammarAccess.getName_pathRule(), currentNode); 
            pushFollow(FOLLOW_rulename_path_in_entryRulename_path14188);
            iv_rulename_path=rulename_path();
            _fsp--;

             current =iv_rulename_path.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_path14199); 

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
    // $ANTLR end entryRulename_path


    // $ANTLR start rulename_path
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5821:1: rulename_path returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENT_0= RULE_IDENT | (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path ) ) ;
    public final AntlrDatatypeRuleToken rulename_path() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        Token this_IDENT_1=null;
        Token this_DOT_2=null;
        AntlrDatatypeRuleToken this_name_path_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5827:6: ( (this_IDENT_0= RULE_IDENT | (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5828:1: (this_IDENT_0= RULE_IDENT | (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5828:1: (this_IDENT_0= RULE_IDENT | (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path ) )
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==RULE_IDENT) ) {
                int LA124_1 = input.LA(2);

                if ( (LA124_1==RULE_DOT) ) {
                    alt124=2;
                }
                else if ( (LA124_1==EOF||(LA124_1>=RULE_SEMICOLON && LA124_1<=RULE_IDENT)||(LA124_1>=RULE_LPAR && LA124_1<=RULE_COMMA)||(LA124_1>=RULE_RPAR && LA124_1<=RULE_STRING)||LA124_1==RULE_LBRACK||LA124_1==91) ) {
                    alt124=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("5828:1: (this_IDENT_0= RULE_IDENT | (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path ) )", 124, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5828:1: (this_IDENT_0= RULE_IDENT | (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path ) )", 124, 0, input);

                throw nvae;
            }
            switch (alt124) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5828:6: this_IDENT_0= RULE_IDENT
                    {
                    this_IDENT_0=(Token)input.LT(1);
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename_path14239); 

                    		current.merge(this_IDENT_0);
                        
                     
                        createLeafNode(grammarAccess.getName_pathAccess().getIDENTTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5836:6: (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5836:6: (this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5836:11: this_IDENT_1= RULE_IDENT this_DOT_2= RULE_DOT this_name_path_3= rulename_path
                    {
                    this_IDENT_1=(Token)input.LT(1);
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename_path14266); 

                    		current.merge(this_IDENT_1);
                        
                     
                        createLeafNode(grammarAccess.getName_pathAccess().getIDENTTerminalRuleCall_1_0(), null); 
                        
                    this_DOT_2=(Token)input.LT(1);
                    match(input,RULE_DOT,FOLLOW_RULE_DOT_in_rulename_path14286); 

                    		current.merge(this_DOT_2);
                        
                     
                        createLeafNode(grammarAccess.getName_pathAccess().getDOTTerminalRuleCall_1_1(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getName_pathAccess().getName_pathParserRuleCall_1_2(), currentNode); 
                        
                    pushFollow(FOLLOW_rulename_path_in_rulename_path14313);
                    this_name_path_3=rulename_path();
                    _fsp--;


                    		current.merge(this_name_path_3);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulename_path


    // $ANTLR start entryRulename_path_star
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5869:1: entryRulename_path_star returns [String current=null] : iv_rulename_path_star= rulename_path_star EOF ;
    public final String entryRulename_path_star() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulename_path_star = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5869:55: (iv_rulename_path_star= rulename_path_star EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5870:2: iv_rulename_path_star= rulename_path_star EOF
            {
             currentNode = createCompositeNode(grammarAccess.getName_path_starRule(), currentNode); 
            pushFollow(FOLLOW_rulename_path_star_in_entryRulename_path_star14358);
            iv_rulename_path_star=rulename_path_star();
            _fsp--;

             current =iv_rulename_path_star.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulename_path_star14369); 

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
    // $ANTLR end entryRulename_path_star


    // $ANTLR start rulename_path_star
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5877:1: rulename_path_star returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? ) | (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star ) ) ;
    public final AntlrDatatypeRuleToken rulename_path_star() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        Token this_STAR_EW_1=null;
        Token this_IDENT_2=null;
        Token this_DOT_3=null;
        AntlrDatatypeRuleToken this_name_path_star_4 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5883:6: ( ( (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? ) | (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5884:1: ( (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? ) | (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5884:1: ( (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? ) | (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star ) )
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==RULE_IDENT) ) {
                int LA126_1 = input.LA(2);

                if ( (LA126_1==RULE_DOT) ) {
                    alt126=2;
                }
                else if ( (LA126_1==EOF||LA126_1==RULE_SEMICOLON||LA126_1==RULE_STRING||LA126_1==RULE_STAR_EW||LA126_1==91) ) {
                    alt126=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("5884:1: ( (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? ) | (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star ) )", 126, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5884:1: ( (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? ) | (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star ) )", 126, 0, input);

                throw nvae;
            }
            switch (alt126) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5884:2: (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5884:2: (this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )? )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5884:7: this_IDENT_0= RULE_IDENT (this_STAR_EW_1= RULE_STAR_EW )?
                    {
                    this_IDENT_0=(Token)input.LT(1);
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename_path_star14410); 

                    		current.merge(this_IDENT_0);
                        
                     
                        createLeafNode(grammarAccess.getName_path_starAccess().getIDENTTerminalRuleCall_0_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5891:1: (this_STAR_EW_1= RULE_STAR_EW )?
                    int alt125=2;
                    int LA125_0 = input.LA(1);

                    if ( (LA125_0==RULE_STAR_EW) ) {
                        alt125=1;
                    }
                    switch (alt125) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5891:6: this_STAR_EW_1= RULE_STAR_EW
                            {
                            this_STAR_EW_1=(Token)input.LT(1);
                            match(input,RULE_STAR_EW,FOLLOW_RULE_STAR_EW_in_rulename_path_star14431); 

                            		current.merge(this_STAR_EW_1);
                                
                             
                                createLeafNode(grammarAccess.getName_path_starAccess().getSTAR_EWTerminalRuleCall_0_1(), null); 
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5899:6: (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5899:6: (this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5899:11: this_IDENT_2= RULE_IDENT this_DOT_3= RULE_DOT this_name_path_star_4= rulename_path_star
                    {
                    this_IDENT_2=(Token)input.LT(1);
                    match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulename_path_star14461); 

                    		current.merge(this_IDENT_2);
                        
                     
                        createLeafNode(grammarAccess.getName_path_starAccess().getIDENTTerminalRuleCall_1_0(), null); 
                        
                    this_DOT_3=(Token)input.LT(1);
                    match(input,RULE_DOT,FOLLOW_RULE_DOT_in_rulename_path_star14481); 

                    		current.merge(this_DOT_3);
                        
                     
                        createLeafNode(grammarAccess.getName_path_starAccess().getDOTTerminalRuleCall_1_1(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getName_path_starAccess().getName_path_starParserRuleCall_1_2(), currentNode); 
                        
                    pushFollow(FOLLOW_rulename_path_star_in_rulename_path_star14508);
                    this_name_path_star_4=rulename_path_star();
                    _fsp--;


                    		current.merge(this_name_path_star_4);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulename_path_star


    // $ANTLR start entryRulecomponent_reference
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5932:1: entryRulecomponent_reference returns [String current=null] : iv_rulecomponent_reference= rulecomponent_reference EOF ;
    public final String entryRulecomponent_reference() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulecomponent_reference = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5932:60: (iv_rulecomponent_reference= rulecomponent_reference EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5933:2: iv_rulecomponent_reference= rulecomponent_reference EOF
            {
             currentNode = createCompositeNode(grammarAccess.getComponent_referenceRule(), currentNode); 
            pushFollow(FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference14553);
            iv_rulecomponent_reference=rulecomponent_reference();
            _fsp--;

             current =iv_rulecomponent_reference.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomponent_reference14564); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5940:1: rulecomponent_reference returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_component_reference_3= rulecomponent_reference )? ) ;
    public final AntlrDatatypeRuleToken rulecomponent_reference() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        Token this_DOT_2=null;
        AntlrDatatypeRuleToken this_array_subscripts_1 = null;

        AntlrDatatypeRuleToken this_component_reference_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5946:6: ( (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_component_reference_3= rulecomponent_reference )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5947:1: (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_component_reference_3= rulecomponent_reference )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5947:1: (this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_component_reference_3= rulecomponent_reference )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5947:6: this_IDENT_0= RULE_IDENT (this_array_subscripts_1= rulearray_subscripts )? (this_DOT_2= RULE_DOT this_component_reference_3= rulecomponent_reference )?
            {
            this_IDENT_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulecomponent_reference14604); 

            		current.merge(this_IDENT_0);
                
             
                createLeafNode(grammarAccess.getComponent_referenceAccess().getIDENTTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5954:1: (this_array_subscripts_1= rulearray_subscripts )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==RULE_LBRACK) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5955:5: this_array_subscripts_1= rulearray_subscripts
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getComponent_referenceAccess().getArray_subscriptsParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulearray_subscripts_in_rulecomponent_reference14632);
                    this_array_subscripts_1=rulearray_subscripts();
                    _fsp--;


                    		current.merge(this_array_subscripts_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5965:3: (this_DOT_2= RULE_DOT this_component_reference_3= rulecomponent_reference )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==RULE_DOT) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5965:8: this_DOT_2= RULE_DOT this_component_reference_3= rulecomponent_reference
                    {
                    this_DOT_2=(Token)input.LT(1);
                    match(input,RULE_DOT,FOLLOW_RULE_DOT_in_rulecomponent_reference14655); 

                    		current.merge(this_DOT_2);
                        
                     
                        createLeafNode(grammarAccess.getComponent_referenceAccess().getDOTTerminalRuleCall_2_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getComponent_referenceAccess().getComponent_referenceParserRuleCall_2_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulecomponent_reference_in_rulecomponent_reference14682);
                    this_component_reference_3=rulecomponent_reference();
                    _fsp--;


                    		current.merge(this_component_reference_3);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRulefunction_call
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5991:1: entryRulefunction_call returns [String current=null] : iv_rulefunction_call= rulefunction_call EOF ;
    public final String entryRulefunction_call() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulefunction_call = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5991:54: (iv_rulefunction_call= rulefunction_call EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5992:2: iv_rulefunction_call= rulefunction_call EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFunction_callRule(), currentNode); 
            pushFollow(FOLLOW_rulefunction_call_in_entryRulefunction_call14728);
            iv_rulefunction_call=rulefunction_call();
            _fsp--;

             current =iv_rulefunction_call.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_call14739); 

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
    // $ANTLR end entryRulefunction_call


    // $ANTLR start rulefunction_call
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:5999:1: rulefunction_call returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_LPAR_0= RULE_LPAR this_function_arguments_1= rulefunction_arguments this_RPAR_2= RULE_RPAR ) ;
    public final AntlrDatatypeRuleToken rulefunction_call() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_LPAR_0=null;
        Token this_RPAR_2=null;
        AntlrDatatypeRuleToken this_function_arguments_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6005:6: ( (this_LPAR_0= RULE_LPAR this_function_arguments_1= rulefunction_arguments this_RPAR_2= RULE_RPAR ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6006:1: (this_LPAR_0= RULE_LPAR this_function_arguments_1= rulefunction_arguments this_RPAR_2= RULE_RPAR )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6006:1: (this_LPAR_0= RULE_LPAR this_function_arguments_1= rulefunction_arguments this_RPAR_2= RULE_RPAR )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6006:6: this_LPAR_0= RULE_LPAR this_function_arguments_1= rulefunction_arguments this_RPAR_2= RULE_RPAR
            {
            this_LPAR_0=(Token)input.LT(1);
            match(input,RULE_LPAR,FOLLOW_RULE_LPAR_in_rulefunction_call14779); 

            		current.merge(this_LPAR_0);
                
             
                createLeafNode(grammarAccess.getFunction_callAccess().getLPARTerminalRuleCall_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getFunction_callAccess().getFunction_argumentsParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_rulefunction_arguments_in_rulefunction_call14806);
            this_function_arguments_1=rulefunction_arguments();
            _fsp--;


            		current.merge(this_function_arguments_1);
                
             
                    currentNode = currentNode.getParent();
                
            this_RPAR_2=(Token)input.LT(1);
            match(input,RULE_RPAR,FOLLOW_RULE_RPAR_in_rulefunction_call14826); 

            		current.merge(this_RPAR_2);
                
             
                createLeafNode(grammarAccess.getFunction_callAccess().getRPARTerminalRuleCall_2(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefunction_call


    // $ANTLR start entryRulefunction_arguments
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6039:1: entryRulefunction_arguments returns [String current=null] : iv_rulefunction_arguments= rulefunction_arguments EOF ;
    public final String entryRulefunction_arguments() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulefunction_arguments = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6039:59: (iv_rulefunction_arguments= rulefunction_arguments EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6040:2: iv_rulefunction_arguments= rulefunction_arguments EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFunction_argumentsRule(), currentNode); 
            pushFollow(FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments14870);
            iv_rulefunction_arguments=rulefunction_arguments();
            _fsp--;

             current =iv_rulefunction_arguments.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulefunction_arguments14881); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6047:1: rulefunction_arguments returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_for_or_expression_list_0= rulefor_or_expression_list )? (this_named_arguments_1= rulenamed_arguments )? ) ;
    public final AntlrDatatypeRuleToken rulefunction_arguments() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_for_or_expression_list_0 = null;

        AntlrDatatypeRuleToken this_named_arguments_1 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6053:6: ( ( (this_for_or_expression_list_0= rulefor_or_expression_list )? (this_named_arguments_1= rulenamed_arguments )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6054:1: ( (this_for_or_expression_list_0= rulefor_or_expression_list )? (this_named_arguments_1= rulenamed_arguments )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6054:1: ( (this_for_or_expression_list_0= rulefor_or_expression_list )? (this_named_arguments_1= rulenamed_arguments )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6054:2: (this_for_or_expression_list_0= rulefor_or_expression_list )? (this_named_arguments_1= rulenamed_arguments )?
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6054:2: (this_for_or_expression_list_0= rulefor_or_expression_list )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==RULE_LPAR||LA129_0==RULE_STRING||(LA129_0>=RULE_PLUS && LA129_0<=RULE_MINUS_EW)||(LA129_0>=RULE_UNSIGNED_INTEGER && LA129_0<=RULE_LBRACK)||LA129_0==RULE_LBRACE||LA129_0==48||LA129_0==50||LA129_0==69||LA129_0==73||(LA129_0>=88 && LA129_0<=90)) ) {
                alt129=1;
            }
            else if ( (LA129_0==RULE_IDENT) ) {
                int LA129_2 = input.LA(2);

                if ( (LA129_2==EOF||LA129_2==RULE_IDENT||(LA129_2>=RULE_LPAR && LA129_2<=RULE_RPAR)||(LA129_2>=RULE_DOT && LA129_2<=RULE_POWER_EW)||LA129_2==RULE_LBRACK||LA129_2==78||(LA129_2>=86 && LA129_2<=87)) ) {
                    alt129=1;
                }
            }
            switch (alt129) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6055:5: this_for_or_expression_list_0= rulefor_or_expression_list
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getFor_or_expression_listParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_rulefor_or_expression_list_in_rulefunction_arguments14929);
                    this_for_or_expression_list_0=rulefor_or_expression_list();
                    _fsp--;


                    		current.merge(this_for_or_expression_list_0);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6065:3: (this_named_arguments_1= rulenamed_arguments )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==RULE_IDENT) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6066:5: this_named_arguments_1= rulenamed_arguments
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getFunction_argumentsAccess().getNamed_argumentsParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulenamed_arguments_in_rulefunction_arguments14959);
                    this_named_arguments_1=rulenamed_arguments();
                    _fsp--;


                    		current.merge(this_named_arguments_1);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRulefor_or_expression_list
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6084:1: entryRulefor_or_expression_list returns [String current=null] : iv_rulefor_or_expression_list= rulefor_or_expression_list EOF ;
    public final String entryRulefor_or_expression_list() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulefor_or_expression_list = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6084:63: (iv_rulefor_or_expression_list= rulefor_or_expression_list EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6085:2: iv_rulefor_or_expression_list= rulefor_or_expression_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_or_expression_listRule(), currentNode); 
            pushFollow(FOLLOW_rulefor_or_expression_list_in_entryRulefor_or_expression_list15005);
            iv_rulefor_or_expression_list=rulefor_or_expression_list();
            _fsp--;

             current =iv_rulefor_or_expression_list.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_or_expression_list15016); 

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
    // $ANTLR end entryRulefor_or_expression_list


    // $ANTLR start rulefor_or_expression_list
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6092:1: rulefor_or_expression_list returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_expression_0= ruleexpression ( (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) | (kw= 'for' this_for_indices_4= rulefor_indices ) )? ) ;
    public final AntlrDatatypeRuleToken rulefor_or_expression_list() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_COMMA_1=null;
        Token kw=null;
        AntlrDatatypeRuleToken this_expression_0 = null;

        AntlrDatatypeRuleToken this_for_or_expression_list2_2 = null;

        AntlrDatatypeRuleToken this_for_indices_4 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6098:6: ( (this_expression_0= ruleexpression ( (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) | (kw= 'for' this_for_indices_4= rulefor_indices ) )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6099:1: (this_expression_0= ruleexpression ( (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) | (kw= 'for' this_for_indices_4= rulefor_indices ) )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6099:1: (this_expression_0= ruleexpression ( (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) | (kw= 'for' this_for_indices_4= rulefor_indices ) )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6100:5: this_expression_0= ruleexpression ( (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) | (kw= 'for' this_for_indices_4= rulefor_indices ) )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFor_or_expression_listAccess().getExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_rulefor_or_expression_list15063);
            this_expression_0=ruleexpression();
            _fsp--;


            		current.merge(this_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6110:1: ( (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) | (kw= 'for' this_for_indices_4= rulefor_indices ) )?
            int alt132=3;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==RULE_COMMA) ) {
                alt132=1;
            }
            else if ( (LA132_0==78) ) {
                alt132=2;
            }
            switch (alt132) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6110:2: (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6110:2: (this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )? )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6110:7: this_COMMA_1= RULE_COMMA (this_for_or_expression_list2_2= rulefor_or_expression_list2 )?
                    {
                    this_COMMA_1=(Token)input.LT(1);
                    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulefor_or_expression_list15085); 

                    		current.merge(this_COMMA_1);
                        
                     
                        createLeafNode(grammarAccess.getFor_or_expression_listAccess().getCOMMATerminalRuleCall_1_0_0(), null); 
                        
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6117:1: (this_for_or_expression_list2_2= rulefor_or_expression_list2 )?
                    int alt131=2;
                    int LA131_0 = input.LA(1);

                    if ( (LA131_0==RULE_LPAR||LA131_0==RULE_STRING||(LA131_0>=RULE_PLUS && LA131_0<=RULE_MINUS_EW)||(LA131_0>=RULE_UNSIGNED_INTEGER && LA131_0<=RULE_LBRACK)||LA131_0==RULE_LBRACE||LA131_0==48||LA131_0==50||LA131_0==69||LA131_0==73||(LA131_0>=88 && LA131_0<=90)) ) {
                        alt131=1;
                    }
                    else if ( (LA131_0==RULE_IDENT) ) {
                        int LA131_2 = input.LA(2);

                        if ( (LA131_2==EOF||LA131_2==RULE_IDENT||(LA131_2>=RULE_LPAR && LA131_2<=RULE_RPAR)||(LA131_2>=RULE_DOT && LA131_2<=RULE_POWER_EW)||LA131_2==RULE_LBRACK||LA131_2==RULE_RBRACE||(LA131_2>=86 && LA131_2<=87)) ) {
                            alt131=1;
                        }
                    }
                    switch (alt131) {
                        case 1 :
                            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6118:5: this_for_or_expression_list2_2= rulefor_or_expression_list2
                            {
                             
                                    currentNode=createCompositeNode(grammarAccess.getFor_or_expression_listAccess().getFor_or_expression_list2ParserRuleCall_1_0_1(), currentNode); 
                                
                            pushFollow(FOLLOW_rulefor_or_expression_list2_in_rulefor_or_expression_list15113);
                            this_for_or_expression_list2_2=rulefor_or_expression_list2();
                            _fsp--;


                            		current.merge(this_for_or_expression_list2_2);
                                
                             
                                    currentNode = currentNode.getParent();
                                

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6129:6: (kw= 'for' this_for_indices_4= rulefor_indices )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6129:6: (kw= 'for' this_for_indices_4= rulefor_indices )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6130:2: kw= 'for' this_for_indices_4= rulefor_indices
                    {
                    kw=(Token)input.LT(1);
                    match(input,78,FOLLOW_78_in_rulefor_or_expression_list15141); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getFor_or_expression_listAccess().getForKeyword_1_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getFor_or_expression_listAccess().getFor_indicesParserRuleCall_1_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulefor_indices_in_rulefor_or_expression_list15163);
                    this_for_indices_4=rulefor_indices();
                    _fsp--;


                    		current.merge(this_for_indices_4);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefor_or_expression_list


    // $ANTLR start entryRulefor_or_expression_list2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6154:1: entryRulefor_or_expression_list2 returns [String current=null] : iv_rulefor_or_expression_list2= rulefor_or_expression_list2 EOF ;
    public final String entryRulefor_or_expression_list2() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulefor_or_expression_list2 = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6154:64: (iv_rulefor_or_expression_list2= rulefor_or_expression_list2 EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6155:2: iv_rulefor_or_expression_list2= rulefor_or_expression_list2 EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFor_or_expression_list2Rule(), currentNode); 
            pushFollow(FOLLOW_rulefor_or_expression_list2_in_entryRulefor_or_expression_list215210);
            iv_rulefor_or_expression_list2=rulefor_or_expression_list2();
            _fsp--;

             current =iv_rulefor_or_expression_list2.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulefor_or_expression_list215221); 

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
    // $ANTLR end entryRulefor_or_expression_list2


    // $ANTLR start rulefor_or_expression_list2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6162:1: rulefor_or_expression_list2 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) ;
    public final AntlrDatatypeRuleToken rulefor_or_expression_list2() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_COMMA_1=null;
        AntlrDatatypeRuleToken this_expression_0 = null;

        AntlrDatatypeRuleToken this_for_or_expression_list2_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6168:6: ( (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_for_or_expression_list2_2= rulefor_or_expression_list2 )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6169:1: (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_for_or_expression_list2_2= rulefor_or_expression_list2 )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6169:1: (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_for_or_expression_list2_2= rulefor_or_expression_list2 )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6170:5: this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_for_or_expression_list2_2= rulefor_or_expression_list2 )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getFor_or_expression_list2Access().getExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_rulefor_or_expression_list215268);
            this_expression_0=ruleexpression();
            _fsp--;


            		current.merge(this_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6180:1: (this_COMMA_1= RULE_COMMA this_for_or_expression_list2_2= rulefor_or_expression_list2 )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==RULE_COMMA) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6180:6: this_COMMA_1= RULE_COMMA this_for_or_expression_list2_2= rulefor_or_expression_list2
                    {
                    this_COMMA_1=(Token)input.LT(1);
                    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulefor_or_expression_list215289); 

                    		current.merge(this_COMMA_1);
                        
                     
                        createLeafNode(grammarAccess.getFor_or_expression_list2Access().getCOMMATerminalRuleCall_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getFor_or_expression_list2Access().getFor_or_expression_list2ParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulefor_or_expression_list2_in_rulefor_or_expression_list215316);
                    this_for_or_expression_list2_2=rulefor_or_expression_list2();
                    _fsp--;


                    		current.merge(this_for_or_expression_list2_2);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulefor_or_expression_list2


    // $ANTLR start entryRulenamed_arguments
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6206:1: entryRulenamed_arguments returns [String current=null] : iv_rulenamed_arguments= rulenamed_arguments EOF ;
    public final String entryRulenamed_arguments() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulenamed_arguments = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6206:56: (iv_rulenamed_arguments= rulenamed_arguments EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6207:2: iv_rulenamed_arguments= rulenamed_arguments EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamed_argumentsRule(), currentNode); 
            pushFollow(FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments15362);
            iv_rulenamed_arguments=rulenamed_arguments();
            _fsp--;

             current =iv_rulenamed_arguments.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_arguments15373); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6214:1: rulenamed_arguments returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_named_arguments2_0= rulenamed_arguments2 ;
    public final AntlrDatatypeRuleToken rulenamed_arguments() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_named_arguments2_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6220:6: (this_named_arguments2_0= rulenamed_arguments2 )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6222:5: this_named_arguments2_0= rulenamed_arguments2
            {
             
                    currentNode=createCompositeNode(grammarAccess.getNamed_argumentsAccess().getNamed_arguments2ParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_rulenamed_arguments2_in_rulenamed_arguments15419);
            this_named_arguments2_0=rulenamed_arguments2();
            _fsp--;


            		current.merge(this_named_arguments2_0);
                
             
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRulenamed_arguments2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6240:1: entryRulenamed_arguments2 returns [String current=null] : iv_rulenamed_arguments2= rulenamed_arguments2 EOF ;
    public final String entryRulenamed_arguments2() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulenamed_arguments2 = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6240:57: (iv_rulenamed_arguments2= rulenamed_arguments2 EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6241:2: iv_rulenamed_arguments2= rulenamed_arguments2 EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamed_arguments2Rule(), currentNode); 
            pushFollow(FOLLOW_rulenamed_arguments2_in_entryRulenamed_arguments215462);
            iv_rulenamed_arguments2=rulenamed_arguments2();
            _fsp--;

             current =iv_rulenamed_arguments2.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_arguments215473); 

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
    // $ANTLR end entryRulenamed_arguments2


    // $ANTLR start rulenamed_arguments2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6248:1: rulenamed_arguments2 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_named_argument_0= rulenamed_argument (this_COMMA_1= RULE_COMMA this_named_argument_2= rulenamed_argument )* ) ;
    public final AntlrDatatypeRuleToken rulenamed_arguments2() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_COMMA_1=null;
        AntlrDatatypeRuleToken this_named_argument_0 = null;

        AntlrDatatypeRuleToken this_named_argument_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6254:6: ( (this_named_argument_0= rulenamed_argument (this_COMMA_1= RULE_COMMA this_named_argument_2= rulenamed_argument )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6255:1: (this_named_argument_0= rulenamed_argument (this_COMMA_1= RULE_COMMA this_named_argument_2= rulenamed_argument )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6255:1: (this_named_argument_0= rulenamed_argument (this_COMMA_1= RULE_COMMA this_named_argument_2= rulenamed_argument )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6256:5: this_named_argument_0= rulenamed_argument (this_COMMA_1= RULE_COMMA this_named_argument_2= rulenamed_argument )*
            {
             
                    currentNode=createCompositeNode(grammarAccess.getNamed_arguments2Access().getNamed_argumentParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_rulenamed_argument_in_rulenamed_arguments215520);
            this_named_argument_0=rulenamed_argument();
            _fsp--;


            		current.merge(this_named_argument_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6266:1: (this_COMMA_1= RULE_COMMA this_named_argument_2= rulenamed_argument )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==RULE_COMMA) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6266:6: this_COMMA_1= RULE_COMMA this_named_argument_2= rulenamed_argument
            	    {
            	    this_COMMA_1=(Token)input.LT(1);
            	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulenamed_arguments215541); 

            	    		current.merge(this_COMMA_1);
            	        
            	     
            	        createLeafNode(grammarAccess.getNamed_arguments2Access().getCOMMATerminalRuleCall_1_0(), null); 
            	        
            	     
            	            currentNode=createCompositeNode(grammarAccess.getNamed_arguments2Access().getNamed_argumentParserRuleCall_1_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_rulenamed_argument_in_rulenamed_arguments215568);
            	    this_named_argument_2=rulenamed_argument();
            	    _fsp--;


            	    		current.merge(this_named_argument_2);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulenamed_arguments2


    // $ANTLR start entryRulenamed_argument
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6292:1: entryRulenamed_argument returns [String current=null] : iv_rulenamed_argument= rulenamed_argument EOF ;
    public final String entryRulenamed_argument() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulenamed_argument = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6292:55: (iv_rulenamed_argument= rulenamed_argument EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6293:2: iv_rulenamed_argument= rulenamed_argument EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNamed_argumentRule(), currentNode); 
            pushFollow(FOLLOW_rulenamed_argument_in_entryRulenamed_argument15614);
            iv_rulenamed_argument=rulenamed_argument();
            _fsp--;

             current =iv_rulenamed_argument.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulenamed_argument15625); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6300:1: rulenamed_argument returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_IDENT_0= RULE_IDENT this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression ) ;
    public final AntlrDatatypeRuleToken rulenamed_argument() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_IDENT_0=null;
        Token this_EQUALS_1=null;
        AntlrDatatypeRuleToken this_expression_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6306:6: ( (this_IDENT_0= RULE_IDENT this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6307:1: (this_IDENT_0= RULE_IDENT this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6307:1: (this_IDENT_0= RULE_IDENT this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6307:6: this_IDENT_0= RULE_IDENT this_EQUALS_1= RULE_EQUALS this_expression_2= ruleexpression
            {
            this_IDENT_0=(Token)input.LT(1);
            match(input,RULE_IDENT,FOLLOW_RULE_IDENT_in_rulenamed_argument15665); 

            		current.merge(this_IDENT_0);
                
             
                createLeafNode(grammarAccess.getNamed_argumentAccess().getIDENTTerminalRuleCall_0(), null); 
                
            this_EQUALS_1=(Token)input.LT(1);
            match(input,RULE_EQUALS,FOLLOW_RULE_EQUALS_in_rulenamed_argument15685); 

            		current.merge(this_EQUALS_1);
                
             
                createLeafNode(grammarAccess.getNamed_argumentAccess().getEQUALSTerminalRuleCall_1(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getNamed_argumentAccess().getExpressionParserRuleCall_2(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_rulenamed_argument15712);
            this_expression_2=ruleexpression();
            _fsp--;


            		current.merge(this_expression_2);
                
             
                    currentNode = currentNode.getParent();
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRuleexpression_list
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6340:1: entryRuleexpression_list returns [String current=null] : iv_ruleexpression_list= ruleexpression_list EOF ;
    public final String entryRuleexpression_list() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleexpression_list = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6340:56: (iv_ruleexpression_list= ruleexpression_list EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6341:2: iv_ruleexpression_list= ruleexpression_list EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpression_listRule(), currentNode); 
            pushFollow(FOLLOW_ruleexpression_list_in_entryRuleexpression_list15756);
            iv_ruleexpression_list=ruleexpression_list();
            _fsp--;

             current =iv_ruleexpression_list.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression_list15767); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6348:1: ruleexpression_list returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_expression_list2_0= ruleexpression_list2 ;
    public final AntlrDatatypeRuleToken ruleexpression_list() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_expression_list2_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6354:6: (this_expression_list2_0= ruleexpression_list2 )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6356:5: this_expression_list2_0= ruleexpression_list2
            {
             
                    currentNode=createCompositeNode(grammarAccess.getExpression_listAccess().getExpression_list2ParserRuleCall(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_list2_in_ruleexpression_list15813);
            this_expression_list2_0=ruleexpression_list2();
            _fsp--;


            		current.merge(this_expression_list2_0);
                
             
                    currentNode = currentNode.getParent();
                

            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRuleexpression_list2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6374:1: entryRuleexpression_list2 returns [String current=null] : iv_ruleexpression_list2= ruleexpression_list2 EOF ;
    public final String entryRuleexpression_list2() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleexpression_list2 = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6374:57: (iv_ruleexpression_list2= ruleexpression_list2 EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6375:2: iv_ruleexpression_list2= ruleexpression_list2 EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExpression_list2Rule(), currentNode); 
            pushFollow(FOLLOW_ruleexpression_list2_in_entryRuleexpression_list215856);
            iv_ruleexpression_list2=ruleexpression_list2();
            _fsp--;

             current =iv_ruleexpression_list2.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleexpression_list215867); 

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
    // $ANTLR end entryRuleexpression_list2


    // $ANTLR start ruleexpression_list2
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6382:1: ruleexpression_list2 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_expression_list2_2= ruleexpression_list2 )? ) ;
    public final AntlrDatatypeRuleToken ruleexpression_list2() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_COMMA_1=null;
        AntlrDatatypeRuleToken this_expression_0 = null;

        AntlrDatatypeRuleToken this_expression_list2_2 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6388:6: ( (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_expression_list2_2= ruleexpression_list2 )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6389:1: (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_expression_list2_2= ruleexpression_list2 )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6389:1: (this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_expression_list2_2= ruleexpression_list2 )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6390:5: this_expression_0= ruleexpression (this_COMMA_1= RULE_COMMA this_expression_list2_2= ruleexpression_list2 )?
            {
             
                    currentNode=createCompositeNode(grammarAccess.getExpression_list2Access().getExpressionParserRuleCall_0(), currentNode); 
                
            pushFollow(FOLLOW_ruleexpression_in_ruleexpression_list215914);
            this_expression_0=ruleexpression();
            _fsp--;


            		current.merge(this_expression_0);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6400:1: (this_COMMA_1= RULE_COMMA this_expression_list2_2= ruleexpression_list2 )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==RULE_COMMA) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6400:6: this_COMMA_1= RULE_COMMA this_expression_list2_2= ruleexpression_list2
                    {
                    this_COMMA_1=(Token)input.LT(1);
                    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_ruleexpression_list215935); 

                    		current.merge(this_COMMA_1);
                        
                     
                        createLeafNode(grammarAccess.getExpression_list2Access().getCOMMATerminalRuleCall_1_0(), null); 
                        
                     
                            currentNode=createCompositeNode(grammarAccess.getExpression_list2Access().getExpression_list2ParserRuleCall_1_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_list2_in_ruleexpression_list215962);
                    this_expression_list2_2=ruleexpression_list2();
                    _fsp--;


                    		current.merge(this_expression_list2_2);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleexpression_list2


    // $ANTLR start entryRulearray_subscripts
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6426:1: entryRulearray_subscripts returns [String current=null] : iv_rulearray_subscripts= rulearray_subscripts EOF ;
    public final String entryRulearray_subscripts() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulearray_subscripts = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6426:57: (iv_rulearray_subscripts= rulearray_subscripts EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6427:2: iv_rulearray_subscripts= rulearray_subscripts EOF
            {
             currentNode = createCompositeNode(grammarAccess.getArray_subscriptsRule(), currentNode); 
            pushFollow(FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts16008);
            iv_rulearray_subscripts=rulearray_subscripts();
            _fsp--;

             current =iv_rulearray_subscripts.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulearray_subscripts16019); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6434:1: rulearray_subscripts returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_LBRACK_0= RULE_LBRACK this_subscript_1= rulesubscript (this_COMMA_2= RULE_COMMA this_subscript_3= rulesubscript )* this_RBRACK_4= RULE_RBRACK ) ;
    public final AntlrDatatypeRuleToken rulearray_subscripts() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_LBRACK_0=null;
        Token this_COMMA_2=null;
        Token this_RBRACK_4=null;
        AntlrDatatypeRuleToken this_subscript_1 = null;

        AntlrDatatypeRuleToken this_subscript_3 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6440:6: ( (this_LBRACK_0= RULE_LBRACK this_subscript_1= rulesubscript (this_COMMA_2= RULE_COMMA this_subscript_3= rulesubscript )* this_RBRACK_4= RULE_RBRACK ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6441:1: (this_LBRACK_0= RULE_LBRACK this_subscript_1= rulesubscript (this_COMMA_2= RULE_COMMA this_subscript_3= rulesubscript )* this_RBRACK_4= RULE_RBRACK )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6441:1: (this_LBRACK_0= RULE_LBRACK this_subscript_1= rulesubscript (this_COMMA_2= RULE_COMMA this_subscript_3= rulesubscript )* this_RBRACK_4= RULE_RBRACK )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6441:6: this_LBRACK_0= RULE_LBRACK this_subscript_1= rulesubscript (this_COMMA_2= RULE_COMMA this_subscript_3= rulesubscript )* this_RBRACK_4= RULE_RBRACK
            {
            this_LBRACK_0=(Token)input.LT(1);
            match(input,RULE_LBRACK,FOLLOW_RULE_LBRACK_in_rulearray_subscripts16059); 

            		current.merge(this_LBRACK_0);
                
             
                createLeafNode(grammarAccess.getArray_subscriptsAccess().getLBRACKTerminalRuleCall_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubscriptParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts16086);
            this_subscript_1=rulesubscript();
            _fsp--;


            		current.merge(this_subscript_1);
                
             
                    currentNode = currentNode.getParent();
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6459:1: (this_COMMA_2= RULE_COMMA this_subscript_3= rulesubscript )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==RULE_COMMA) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6459:6: this_COMMA_2= RULE_COMMA this_subscript_3= rulesubscript
            	    {
            	    this_COMMA_2=(Token)input.LT(1);
            	    match(input,RULE_COMMA,FOLLOW_RULE_COMMA_in_rulearray_subscripts16107); 

            	    		current.merge(this_COMMA_2);
            	        
            	     
            	        createLeafNode(grammarAccess.getArray_subscriptsAccess().getCOMMATerminalRuleCall_2_0(), null); 
            	        
            	     
            	            currentNode=createCompositeNode(grammarAccess.getArray_subscriptsAccess().getSubscriptParserRuleCall_2_1(), currentNode); 
            	        
            	    pushFollow(FOLLOW_rulesubscript_in_rulearray_subscripts16134);
            	    this_subscript_3=rulesubscript();
            	    _fsp--;


            	    		current.merge(this_subscript_3);
            	        
            	     
            	            currentNode = currentNode.getParent();
            	        

            	    }
            	    break;

            	default :
            	    break loop136;
                }
            } while (true);

            this_RBRACK_4=(Token)input.LT(1);
            match(input,RULE_RBRACK,FOLLOW_RULE_RBRACK_in_rulearray_subscripts16156); 

            		current.merge(this_RBRACK_4);
                
             
                createLeafNode(grammarAccess.getArray_subscriptsAccess().getRBRACKTerminalRuleCall_3(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6492:1: entryRulesubscript returns [String current=null] : iv_rulesubscript= rulesubscript EOF ;
    public final String entryRulesubscript() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulesubscript = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6492:50: (iv_rulesubscript= rulesubscript EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6493:2: iv_rulesubscript= rulesubscript EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSubscriptRule(), currentNode); 
            pushFollow(FOLLOW_rulesubscript_in_entryRulesubscript16200);
            iv_rulesubscript=rulesubscript();
            _fsp--;

             current =iv_rulesubscript.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulesubscript16211); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6500:1: rulesubscript returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_expression_0= ruleexpression | this_COLON_1= RULE_COLON ) ;
    public final AntlrDatatypeRuleToken rulesubscript() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_COLON_1=null;
        AntlrDatatypeRuleToken this_expression_0 = null;


         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6506:6: ( (this_expression_0= ruleexpression | this_COLON_1= RULE_COLON ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6507:1: (this_expression_0= ruleexpression | this_COLON_1= RULE_COLON )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6507:1: (this_expression_0= ruleexpression | this_COLON_1= RULE_COLON )
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==RULE_IDENT||LA137_0==RULE_LPAR||LA137_0==RULE_STRING||(LA137_0>=RULE_PLUS && LA137_0<=RULE_MINUS_EW)||(LA137_0>=RULE_UNSIGNED_INTEGER && LA137_0<=RULE_LBRACK)||LA137_0==RULE_LBRACE||LA137_0==48||LA137_0==50||LA137_0==69||LA137_0==73||(LA137_0>=88 && LA137_0<=90)) ) {
                alt137=1;
            }
            else if ( (LA137_0==RULE_COLON) ) {
                alt137=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("6507:1: (this_expression_0= ruleexpression | this_COLON_1= RULE_COLON )", 137, 0, input);

                throw nvae;
            }
            switch (alt137) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6508:5: this_expression_0= ruleexpression
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getSubscriptAccess().getExpressionParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleexpression_in_rulesubscript16258);
                    this_expression_0=ruleexpression();
                    _fsp--;


                    		current.merge(this_expression_0);
                        
                     
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6519:10: this_COLON_1= RULE_COLON
                    {
                    this_COLON_1=(Token)input.LT(1);
                    match(input,RULE_COLON,FOLLOW_RULE_COLON_in_rulesubscript16284); 

                    		current.merge(this_COLON_1);
                        
                     
                        createLeafNode(grammarAccess.getSubscriptAccess().getCOLONTerminalRuleCall_1(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRulecomment
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6534:1: entryRulecomment returns [EObject current=null] : iv_rulecomment= rulecomment EOF ;
    public final EObject entryRulecomment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulecomment = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6534:49: (iv_rulecomment= rulecomment EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6535:2: iv_rulecomment= rulecomment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCommentRule(), currentNode); 
            pushFollow(FOLLOW_rulecomment_in_entryRulecomment16327);
            iv_rulecomment=rulecomment();
            _fsp--;

             current =iv_rulecomment; 
            match(input,EOF,FOLLOW_EOF_in_entryRulecomment16337); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6542:1: rulecomment returns [EObject current=null] : ( (lv_string_0= rulestring_comment )? (lv_annotation_1= ruleannotation )? ) ;
    public final EObject rulecomment() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_string_0 = null;

        EObject lv_annotation_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6547:6: ( ( (lv_string_0= rulestring_comment )? (lv_annotation_1= ruleannotation )? ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6548:1: ( (lv_string_0= rulestring_comment )? (lv_annotation_1= ruleannotation )? )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6548:1: ( (lv_string_0= rulestring_comment )? (lv_annotation_1= ruleannotation )? )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6548:2: (lv_string_0= rulestring_comment )? (lv_annotation_1= ruleannotation )?
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6548:2: (lv_string_0= rulestring_comment )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==RULE_STRING) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6551:6: lv_string_0= rulestring_comment
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCommentAccess().getStringString_commentParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_rulestring_comment_in_rulecomment16396);
                    lv_string_0=rulestring_comment();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCommentRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "string", lv_string_0, "string_comment", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }

            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6569:3: (lv_annotation_1= ruleannotation )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==91) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6572:6: lv_annotation_1= ruleannotation
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getCommentAccess().getAnnotationAnnotationParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleannotation_in_rulecomment16435);
                    lv_annotation_1=ruleannotation();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getCommentRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "annotation", lv_annotation_1, "annotation", currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

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
    // $ANTLR end rulecomment


    // $ANTLR start entryRulestring_comment
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6597:1: entryRulestring_comment returns [String current=null] : iv_rulestring_comment= rulestring_comment EOF ;
    public final String entryRulestring_comment() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulestring_comment = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6597:55: (iv_rulestring_comment= rulestring_comment EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6598:2: iv_rulestring_comment= rulestring_comment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getString_commentRule(), currentNode); 
            pushFollow(FOLLOW_rulestring_comment_in_entryRulestring_comment16474);
            iv_rulestring_comment=rulestring_comment();
            _fsp--;

             current =iv_rulestring_comment.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulestring_comment16485); 

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
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6605:1: rulestring_comment returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING (this_PLUS_1= RULE_PLUS this_STRING_2= RULE_STRING )* ) ;
    public final AntlrDatatypeRuleToken rulestring_comment() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_PLUS_1=null;
        Token this_STRING_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6611:6: ( (this_STRING_0= RULE_STRING (this_PLUS_1= RULE_PLUS this_STRING_2= RULE_STRING )* ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6612:1: (this_STRING_0= RULE_STRING (this_PLUS_1= RULE_PLUS this_STRING_2= RULE_STRING )* )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6612:1: (this_STRING_0= RULE_STRING (this_PLUS_1= RULE_PLUS this_STRING_2= RULE_STRING )* )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6612:6: this_STRING_0= RULE_STRING (this_PLUS_1= RULE_PLUS this_STRING_2= RULE_STRING )*
            {
            this_STRING_0=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment16525); 

            		current.merge(this_STRING_0);
                
             
                createLeafNode(grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_0(), null); 
                
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6619:1: (this_PLUS_1= RULE_PLUS this_STRING_2= RULE_STRING )*
            loop140:
            do {
                int alt140=2;
                int LA140_0 = input.LA(1);

                if ( (LA140_0==RULE_PLUS) ) {
                    alt140=1;
                }


                switch (alt140) {
            	case 1 :
            	    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6619:6: this_PLUS_1= RULE_PLUS this_STRING_2= RULE_STRING
            	    {
            	    this_PLUS_1=(Token)input.LT(1);
            	    match(input,RULE_PLUS,FOLLOW_RULE_PLUS_in_rulestring_comment16546); 

            	    		current.merge(this_PLUS_1);
            	        
            	     
            	        createLeafNode(grammarAccess.getString_commentAccess().getPLUSTerminalRuleCall_1_0(), null); 
            	        
            	    this_STRING_2=(Token)input.LT(1);
            	    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulestring_comment16566); 

            	    		current.merge(this_STRING_2);
            	        
            	     
            	        createLeafNode(grammarAccess.getString_commentAccess().getSTRINGTerminalRuleCall_1_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop140;
                }
            } while (true);


            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
            	    lastConsumedDatatypeToken = current;
                
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


    // $ANTLR start entryRuleannotation
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6641:1: entryRuleannotation returns [EObject current=null] : iv_ruleannotation= ruleannotation EOF ;
    public final EObject entryRuleannotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleannotation = null;


        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6641:52: (iv_ruleannotation= ruleannotation EOF )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6642:2: iv_ruleannotation= ruleannotation EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAnnotationRule(), currentNode); 
            pushFollow(FOLLOW_ruleannotation_in_entryRuleannotation16611);
            iv_ruleannotation=ruleannotation();
            _fsp--;

             current =iv_ruleannotation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleannotation16621); 

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
    // $ANTLR end entryRuleannotation


    // $ANTLR start ruleannotation
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6649:1: ruleannotation returns [EObject current=null] : ( 'annotation' this_class_modification_1= ruleclass_modification ) ;
    public final EObject ruleannotation() throws RecognitionException {
        EObject current = null;

        EObject this_class_modification_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6654:6: ( ( 'annotation' this_class_modification_1= ruleclass_modification ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6655:1: ( 'annotation' this_class_modification_1= ruleclass_modification )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6655:1: ( 'annotation' this_class_modification_1= ruleclass_modification )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6655:2: 'annotation' this_class_modification_1= ruleclass_modification
            {
            match(input,91,FOLLOW_91_in_ruleannotation16655); 

                    createLeafNode(grammarAccess.getAnnotationAccess().getAnnotationKeyword_0(), null); 
                
             
                    currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getClass_modificationParserRuleCall_1(), currentNode); 
                
            pushFollow(FOLLOW_ruleclass_modification_in_ruleannotation16677);
            this_class_modification_1=ruleclass_modification();
            _fsp--;

             
                    current = this_class_modification_1; 
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
    // $ANTLR end ruleannotation


    // $ANTLR start ruleClassType
    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6675:1: ruleClassType returns [Enumerator current=null] : ( ( 'class' ) | ( 'model' ) | ( 'record' ) | ( 'block' ) | ( 'connector' ) | ( 'type' ) | ( 'package' ) | ( 'function' ) ) ;
    public final Enumerator ruleClassType() throws RecognitionException {
        Enumerator current = null;

         setCurrentLookahead(); resetLookahead(); 
        try {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6679:6: ( ( ( 'class' ) | ( 'model' ) | ( 'record' ) | ( 'block' ) | ( 'connector' ) | ( 'type' ) | ( 'package' ) | ( 'function' ) ) )
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6680:1: ( ( 'class' ) | ( 'model' ) | ( 'record' ) | ( 'block' ) | ( 'connector' ) | ( 'type' ) | ( 'package' ) | ( 'function' ) )
            {
            // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6680:1: ( ( 'class' ) | ( 'model' ) | ( 'record' ) | ( 'block' ) | ( 'connector' ) | ( 'type' ) | ( 'package' ) | ( 'function' ) )
            int alt141=8;
            switch ( input.LA(1) ) {
            case 92:
                {
                alt141=1;
                }
                break;
            case 93:
                {
                alt141=2;
                }
                break;
            case 94:
                {
                alt141=3;
                }
                break;
            case 95:
                {
                alt141=4;
                }
                break;
            case 96:
                {
                alt141=5;
                }
                break;
            case 97:
                {
                alt141=6;
                }
                break;
            case 98:
                {
                alt141=7;
                }
                break;
            case 49:
                {
                alt141=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("6680:1: ( ( 'class' ) | ( 'model' ) | ( 'record' ) | ( 'block' ) | ( 'connector' ) | ( 'type' ) | ( 'package' ) | ( 'function' ) )", 141, 0, input);

                throw nvae;
            }

            switch (alt141) {
                case 1 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6680:2: ( 'class' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6680:2: ( 'class' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6680:4: 'class'
                    {
                    match(input,92,FOLLOW_92_in_ruleClassType16723); 

                            current = grammarAccess.getClassTypeAccess().getCLASSEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getCLASSEnumLiteralDeclaration_0(), null); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6686:6: ( 'model' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6686:6: ( 'model' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6686:8: 'model'
                    {
                    match(input,93,FOLLOW_93_in_ruleClassType16738); 

                            current = grammarAccess.getClassTypeAccess().getMODELEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getMODELEnumLiteralDeclaration_1(), null); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6692:6: ( 'record' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6692:6: ( 'record' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6692:8: 'record'
                    {
                    match(input,94,FOLLOW_94_in_ruleClassType16753); 

                            current = grammarAccess.getClassTypeAccess().getRECORDEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getRECORDEnumLiteralDeclaration_2(), null); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6698:6: ( 'block' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6698:6: ( 'block' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6698:8: 'block'
                    {
                    match(input,95,FOLLOW_95_in_ruleClassType16768); 

                            current = grammarAccess.getClassTypeAccess().getBLOCKEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getBLOCKEnumLiteralDeclaration_3(), null); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6704:6: ( 'connector' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6704:6: ( 'connector' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6704:8: 'connector'
                    {
                    match(input,96,FOLLOW_96_in_ruleClassType16783); 

                            current = grammarAccess.getClassTypeAccess().getCONNECTOREnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getCONNECTOREnumLiteralDeclaration_4(), null); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6710:6: ( 'type' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6710:6: ( 'type' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6710:8: 'type'
                    {
                    match(input,97,FOLLOW_97_in_ruleClassType16798); 

                            current = grammarAccess.getClassTypeAccess().getTYPEEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getTYPEEnumLiteralDeclaration_5(), null); 
                        

                    }


                    }
                    break;
                case 7 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6716:6: ( 'package' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6716:6: ( 'package' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6716:8: 'package'
                    {
                    match(input,98,FOLLOW_98_in_ruleClassType16813); 

                            current = grammarAccess.getClassTypeAccess().getPACKAGEEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getPACKAGEEnumLiteralDeclaration_6(), null); 
                        

                    }


                    }
                    break;
                case 8 :
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6722:6: ( 'function' )
                    {
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6722:6: ( 'function' )
                    // ../org.modelica.xtext/src-gen/org/modelica/xtext/parser/antlr/internal/InternalModelicaBase.g:6722:8: 'function'
                    {
                    match(input,49,FOLLOW_49_in_ruleClassType16828); 

                            current = grammarAccess.getClassTypeAccess().getFUNCTIONEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                            createLeafNode(grammarAccess.getClassTypeAccess().getFUNCTIONEnumLiteralDeclaration_7(), null); 
                        

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
    // $ANTLR end ruleClassType


 

    public static final BitSet FOLLOW_ruleStorageUnit_in_entryRuleStorageUnit73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStorageUnit83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleStorageUnit118 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_rulename_path_in_ruleStorageUnit128 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleStorageUnit131 = new BitSet(new long[]{0x0002780000000002L,0x00000007F0000000L});
    public static final BitSet FOLLOW_43_in_ruleStorageUnit143 = new BitSet(new long[]{0x0002700000000000L,0x00000007F0000000L});
    public static final BitSet FOLLOW_ruleClassDefinition_in_ruleStorageUnit179 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleStorageUnit192 = new BitSet(new long[]{0x0002780000000002L,0x00000007F0000000L});
    public static final BitSet FOLLOW_ruleClassDefinition_in_entryRuleClassDefinition229 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassDefinition239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleClassDefinition285 = new BitSet(new long[]{0x0002600000000000L,0x00000007F0000000L});
    public static final BitSet FOLLOW_45_in_ruleClassDefinition320 = new BitSet(new long[]{0x0002400000000000L,0x00000007F0000000L});
    public static final BitSet FOLLOW_46_in_ruleClassDefinition355 = new BitSet(new long[]{0x0002000000000000L,0x00000007F0000000L});
    public static final BitSet FOLLOW_ruleClassType_in_ruleClassDefinition403 = new BitSet(new long[]{0x0000800000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleClassDefinition431 = new BitSet(new long[]{0xDFE3F80000000820L,0x00000007F800039FL});
    public static final BitSet FOLLOW_rulestring_comment_in_ruleClassDefinition473 = new BitSet(new long[]{0xDFE3F80000000020L,0x00000007F800039FL});
    public static final BitSet FOLLOW_47_in_ruleClassDefinition495 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleClassDefinition504 = new BitSet(new long[]{0xDFE3F800000008A0L,0x00000007F800039FL});
    public static final BitSet FOLLOW_ruleclass_modification_in_ruleClassDefinition537 = new BitSet(new long[]{0xDFE3F80000000820L,0x00000007F800039FL});
    public static final BitSet FOLLOW_rulestring_comment_in_ruleClassDefinition552 = new BitSet(new long[]{0xDFE3F80000000020L,0x00000007F800039FL});
    public static final BitSet FOLLOW_ruleElementList_in_ruleClassDefinition582 = new BitSet(new long[]{0x00E1000000000000L,0x0000000000000380L});
    public static final BitSet FOLLOW_ruleElementList2_in_ruleClassDefinition621 = new BitSet(new long[]{0x00E1000000000000L,0x0000000000000380L});
    public static final BitSet FOLLOW_ruleinitial_equation_rule_clause_in_ruleClassDefinition665 = new BitSet(new long[]{0x00E1000000000000L,0x0000000000000380L});
    public static final BitSet FOLLOW_ruleinitial_algorithm_clause_in_ruleClassDefinition709 = new BitSet(new long[]{0x00E1000000000000L,0x0000000000000380L});
    public static final BitSet FOLLOW_ruleequation_rule_clause_in_ruleClassDefinition753 = new BitSet(new long[]{0x00E1000000000000L,0x0000000000000380L});
    public static final BitSet FOLLOW_rulealgorithm_clause_in_ruleClassDefinition797 = new BitSet(new long[]{0x00E1000000000000L,0x0000000000000380L});
    public static final BitSet FOLLOW_ruleexternal_clause_in_ruleClassDefinition837 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_ruleClassDefinition851 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleClassDefinition860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePartialDerivative_in_entryRulePartialDerivative892 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePartialDerivative902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_rulePartialDerivative936 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulePartialDerivative958 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_rulePartialDerivative975 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_rulePartialDerivative983 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_LPAR_in_rulePartialDerivative992 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_in_rulePartialDerivative1001 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulePartialDerivative1002 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleident_list_in_rulePartialDerivative1011 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_rulePartialDerivative1012 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_rulePartialDerivative1046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassModification_in_entryRuleClassModification1083 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassModification1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassType_in_ruleClassModification1152 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleClassModification1178 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleClassModification1195 = new BitSet(new long[]{0xC000000000000020L,0x000000000000001FL});
    public static final BitSet FOLLOW_ruletype_prefix_in_ruleClassModification1203 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruletype_specifier_in_ruleClassModification1229 = new BitSet(new long[]{0x0000000000000882L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleclass_modification_in_ruleClassModification1267 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleClassModification1306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassOverloading_in_entryRuleClassOverloading1343 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassOverloading1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassType_in_ruleClassOverloading1412 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleClassOverloading1438 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleClassOverloading1455 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleClassOverloading1463 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_LPAR_in_ruleClassOverloading1472 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_list_in_ruleClassOverloading1481 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_ruleClassOverloading1482 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleClassOverloading1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEnumeration_in_entryRuleEnumeration1553 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEnumeration1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassType_in_ruleEnumeration1622 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleEnumeration1648 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleEnumeration1665 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_ruleEnumeration1673 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_LPAR_in_ruleEnumeration1682 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleEnumeration1705 = new BitSet(new long[]{0x0000000000000D00L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleEnumeration1747 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_RULE_COMMA_in_ruleEnumeration1761 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleEnumeration1782 = new BitSet(new long[]{0x0000000000000D00L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleEnumeration1824 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_RULE_COLON_in_ruleEnumeration1846 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_ruleEnumeration1855 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleEnumeration1888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleident_list_in_entryRuleident_list1926 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleident_list1937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleident_list1977 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_COMMA_in_ruleident_list1998 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleident_list2018 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rulename_list_in_entryRulename_list2064 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_list2075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_path_in_rulename_list2122 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulename_list2141 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_in_rulename_list2163 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ruleexternal_clause_in_entryRuleexternal_clause2208 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexternal_clause2218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleexternal_clause2252 = new BitSet(new long[]{0x0000000000000830L,0x0000000008000000L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleexternal_clause2274 = new BitSet(new long[]{0x0000000000000030L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleexternal_function_call_in_ruleexternal_clause2293 = new BitSet(new long[]{0x0000000000000010L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleannotation_in_ruleexternal_clause2321 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleexternal_clause2335 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleannotation_in_ruleexternal_clause2369 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleexternal_clause2382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexternal_function_call_in_entryRuleexternal_function_call2417 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexternal_function_call2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleexternal_function_call2476 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleexternal_function_call2496 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleexternal_function_call2518 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_LPAR_in_ruleexternal_function_call2538 = new BitSet(new long[]{0x00050005C0F00CA0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleexternal_function_call2566 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_ruleexternal_function_call2588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementList_in_entryRuleElementList2631 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementList2641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClassElement_in_ruleElementList2701 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleannotation_in_ruleElementList2745 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleElementList2759 = new BitSet(new long[]{0xDF02F80000000022L,0x00000007F800001FL});
    public static final BitSet FOLLOW_ruleElementList2_in_entryRuleElementList22792 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementList22802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleElementList22837 = new BitSet(new long[]{0xDF02F80000000022L,0x00000007F800001FL});
    public static final BitSet FOLLOW_ruleClassElement_in_ruleElementList22882 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleannotation_in_ruleElementList22926 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleElementList22940 = new BitSet(new long[]{0xDF02F80000000022L,0x00000007F800001FL});
    public static final BitSet FOLLOW_55_in_ruleElementList22958 = new BitSet(new long[]{0xDF02F80000000022L,0x00000007F800001FL});
    public static final BitSet FOLLOW_ruleClassElement_in_ruleElementList23003 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleannotation_in_ruleElementList23047 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleElementList23061 = new BitSet(new long[]{0xDF02F80000000022L,0x00000007F800001FL});
    public static final BitSet FOLLOW_ruleClassElement_in_entryRuleClassElement3096 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClassElement3106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleimport_clause_in_ruleClassElement3153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleextends_clause_in_ruleClassElement3180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleClassElement3196 = new BitSet(new long[]{0xCE02780000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_43_in_ruleClassElement3208 = new BitSet(new long[]{0xCE02700000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_57_in_ruleClassElement3220 = new BitSet(new long[]{0xCC02700000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_58_in_ruleClassElement3232 = new BitSet(new long[]{0xC802700000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_ruleClassDefinition_in_ruleClassElement3258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_clause_in_ruleClassElement3285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplaceableElementClause_in_ruleClassElement3313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReplaceableElementClause_in_entryRuleReplaceableElementClause3347 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReplaceableElementClause3357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ruleReplaceableElementClause3391 = new BitSet(new long[]{0xC002700000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_ruleClassDefinition_in_ruleReplaceableElementClause3417 = new BitSet(new long[]{0x2000800000000002L});
    public static final BitSet FOLLOW_rulecomponent_clause_in_ruleReplaceableElementClause3431 = new BitSet(new long[]{0x2000800000000002L});
    public static final BitSet FOLLOW_ruleconstraining_clause_in_ruleReplaceableElementClause3474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleimport_clause_in_entryRuleimport_clause3512 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleimport_clause3522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleimport_clause3556 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleimport_clause3580 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleimport_clause3597 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_in_ruleimport_clause3630 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulename_path_star_in_ruleimport_clause3675 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleimport_clause3714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleextends_clause_in_entryRuleextends_clause3751 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleextends_clause3761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleextends_clause3795 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_in_ruleextends_clause3829 = new BitSet(new long[]{0x0000000000000082L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleclass_modification_in_ruleextends_clause3867 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleannotation_in_ruleextends_clause3906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconstraining_clause_in_entryRuleconstraining_clause3944 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconstraining_clause3954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleconstraining_clause3990 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_in_ruleconstraining_clause4024 = new BitSet(new long[]{0x0000000000000882L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleclass_modification_in_ruleconstraining_clause4062 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_61_in_ruleconstraining_clause4084 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_in_ruleconstraining_clause4118 = new BitSet(new long[]{0x0000000000000882L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleclass_modification_in_ruleconstraining_clause4156 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleconstraining_clause4197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_clause_in_entryRulecomponent_clause4234 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_clause4244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruletype_prefix_in_rulecomponent_clause4303 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruletype_specifier_in_rulecomponent_clause4341 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulecomponent_declaration_in_rulecomponent_clause4379 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulecomponent_clause4393 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulecomponent_declaration_in_rulecomponent_clause4426 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ruletype_prefix_in_entryRuletype_prefix4466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuletype_prefix4477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruletype_prefix4516 = new BitSet(new long[]{0x0000000000000002L,0x000000000000001FL});
    public static final BitSet FOLLOW_63_in_ruletype_prefix4535 = new BitSet(new long[]{0x0000000000000002L,0x000000000000001FL});
    public static final BitSet FOLLOW_64_in_ruletype_prefix4551 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_65_in_ruletype_prefix4570 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_66_in_ruletype_prefix4589 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000018L});
    public static final BitSet FOLLOW_67_in_ruletype_prefix4605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruletype_prefix4624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruletype_specifier_in_entryRuletype_specifier4664 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuletype_specifier4674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_path_in_ruletype_specifier4733 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_ruletype_specifier4771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_declaration_in_entryRulecomponent_declaration4809 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_declaration4819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_declaration4853 = new BitSet(new long[]{0x00000001000018C2L,0x0000000008000020L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_declaration4886 = new BitSet(new long[]{0x00000000000018C2L,0x0000000008000020L});
    public static final BitSet FOLLOW_rulemodification_in_rulecomponent_declaration4925 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000020L});
    public static final BitSet FOLLOW_69_in_rulecomponent_declaration4940 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_rulecomponent_declaration4974 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_rulecomponent_declaration5014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulemodification_in_entryRulemodification5051 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulemodification5061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleclass_modification_in_rulemodification5108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleModification_in_rulemodification5135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleModification_in_entryRuleSimpleModification5167 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleModification5177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleSimpleModification5212 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleSimpleModification5245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ASSIGN_in_ruleSimpleModification5266 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleSimpleModification5299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleclass_modification_in_entryRuleclass_modification5337 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleclass_modification5347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LPAR_in_ruleclass_modification5381 = new BitSet(new long[]{0x0900080000000420L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleargument_in_ruleclass_modification5415 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_RULE_COMMA_in_ruleclass_modification5429 = new BitSet(new long[]{0x0900080000000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleargument_in_ruleclass_modification5462 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_RULE_RPAR_in_ruleclass_modification5479 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleclass_modification5488 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleclass_modification5521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleargument_in_entryRuleargument5560 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleargument5570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_ruleargument5606 = new BitSet(new long[]{0x0800080000000020L});
    public static final BitSet FOLLOW_43_in_ruleargument5618 = new BitSet(new long[]{0x0800000000000020L});
    public static final BitSet FOLLOW_ruleElementModification_in_ruleargument5643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementReplaceableClause_in_ruleargument5670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementRedeclaration_in_ruleargument5699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementModification_in_entryRuleElementModification5731 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementModification5741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_ruleElementModification5800 = new BitSet(new long[]{0x00000000000018C2L});
    public static final BitSet FOLLOW_rulemodification_in_ruleElementModification5838 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_rulestring_comment_in_ruleElementModification5877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementRedeclaration_in_entryRuleElementRedeclaration5915 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementRedeclaration5925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleElementRedeclaration5959 = new BitSet(new long[]{0xC802780000000020L,0x00000007F000005FL});
    public static final BitSet FOLLOW_70_in_ruleElementRedeclaration5969 = new BitSet(new long[]{0xC802780000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_43_in_ruleElementRedeclaration5981 = new BitSet(new long[]{0xC802700000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_ruleClassDefinition_in_ruleElementRedeclaration6007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComponentClause_in_ruleElementRedeclaration6034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementReplaceableClause_in_ruleElementRedeclaration6062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementReplaceableClause_in_entryRuleElementReplaceableClause6095 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementReplaceableClause6105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ruleElementReplaceableClause6139 = new BitSet(new long[]{0xC002700000000020L,0x00000007F000001FL});
    public static final BitSet FOLLOW_ruleClassDefinition_in_ruleElementReplaceableClause6165 = new BitSet(new long[]{0x2000800000000002L});
    public static final BitSet FOLLOW_ruleElementComponentClause_in_ruleElementReplaceableClause6179 = new BitSet(new long[]{0x2000800000000002L});
    public static final BitSet FOLLOW_ruleconstraining_clause_in_ruleElementReplaceableClause6222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementComponentClause_in_entryRuleElementComponentClause6260 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementComponentClause6270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruletype_prefix_in_ruleElementComponentClause6329 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruletype_specifier_in_ruleElementComponentClause6367 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleElementComponentClause6380 = new BitSet(new long[]{0x00000001000018C2L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_ruleElementComponentClause6413 = new BitSet(new long[]{0x00000000000018C2L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulemodification_in_ruleElementComponentClause6452 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleElementComponentClause6491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleequation_rule_clause_in_entryRuleequation_rule_clause6528 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleequation_rule_clause6538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_ruleequation_rule_clause6572 = new BitSet(new long[]{0x00050005C0F008A0L,0x000000000F124220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_ruleequation_rule_clause6608 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleequation_rule_clause6621 = new BitSet(new long[]{0x00050005C0F008A2L,0x000000000F124220L});
    public static final BitSet FOLLOW_ruleannotation_in_ruleequation_rule_clause6662 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleequation_rule_clause6675 = new BitSet(new long[]{0x00050005C0F008A2L,0x000000000F124220L});
    public static final BitSet FOLLOW_rulealgorithm_clause_in_entryRulealgorithm_clause6710 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulealgorithm_clause6720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_rulealgorithm_clause6754 = new BitSet(new long[]{0x00050005C0F008A0L,0x000000000F034E20L});
    public static final BitSet FOLLOW_rulealgorithm_in_rulealgorithm_clause6790 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulealgorithm_clause6803 = new BitSet(new long[]{0x00050005C0F008A2L,0x000000000F034E20L});
    public static final BitSet FOLLOW_ruleannotation_in_rulealgorithm_clause6844 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulealgorithm_clause6857 = new BitSet(new long[]{0x00050005C0F008A2L,0x000000000F034E20L});
    public static final BitSet FOLLOW_ruleinitial_equation_rule_clause_in_entryRuleinitial_equation_rule_clause6892 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleinitial_equation_rule_clause6902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleinitial_equation_rule_clause6936 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_ruleequation_rule_clause_in_ruleinitial_equation_rule_clause6958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleinitial_algorithm_clause_in_entryRuleinitial_algorithm_clause6990 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleinitial_algorithm_clause7000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleinitial_algorithm_clause7034 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_rulealgorithm_clause_in_ruleinitial_algorithm_clause7056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleequation_rule_in_entryRuleequation_rule7088 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleequation_rule7098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleequality_equation_rule_in_ruleequation_rule7133 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleconditional_equation_rule_e_in_ruleequation_rule7153 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulefor_clause_e_in_ruleequation_rule7180 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleconnect_clause_in_ruleequation_rule7194 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulewhen_clause_e_in_ruleequation_rule7214 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_ruleequation_rule7248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulealgorithm_in_entryRulealgorithm7285 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulealgorithm7295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleassign_clause_a_in_rulealgorithm7330 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleconditional_equation_rule_a_in_rulealgorithm7350 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulefor_clause_a_in_rulealgorithm7377 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulewhile_clause_in_rulealgorithm7404 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulewhen_clause_a_in_rulealgorithm7431 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_74_in_rulealgorithm7445 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_75_in_rulealgorithm7460 = new BitSet(new long[]{0x0000000000000802L,0x0000000008000000L});
    public static final BitSet FOLLOW_rulecomment_in_rulealgorithm7495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleassign_clause_a_in_entryRuleassign_clause_a7533 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleassign_clause_a7544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleassign_clause_a7591 = new BitSet(new long[]{0x0000000000001042L});
    public static final BitSet FOLLOW_RULE_ASSIGN_in_ruleassign_clause_a7613 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleassign_clause_a7640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleassign_clause_a7668 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleassign_clause_a7695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleequality_equation_rule_in_entryRuleequality_equation_rule7742 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleequality_equation_rule7753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleequality_equation_rule7800 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_ruleequality_equation_rule7821 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleequality_equation_rule7848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_equation_rule_e_in_entryRuleconditional_equation_rule_e7893 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_equation_rule_e7903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleconditional_equation_rule_e7937 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_equation_rule_e7946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleconditional_equation_rule_e7947 = new BitSet(new long[]{0x00050005C0F008A0L,0x00000000071A6220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_ruleconditional_equation_rule_e7982 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_e7995 = new BitSet(new long[]{0x00050005C0F008A0L,0x00000000071A6220L});
    public static final BitSet FOLLOW_ruleequation_rule_elseif_in_ruleconditional_equation_rule_e8030 = new BitSet(new long[]{0x0001000000000000L,0x0000000000082000L});
    public static final BitSet FOLLOW_77_in_ruleconditional_equation_rule_e8045 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_ruleconditional_equation_rule_e8080 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_e8093 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007124220L});
    public static final BitSet FOLLOW_48_in_ruleconditional_equation_rule_e8105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleconditional_equation_rule_e8114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconditional_equation_rule_a_in_entryRuleconditional_equation_rule_a8147 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconditional_equation_rule_a8157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleconditional_equation_rule_a8191 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleconditional_equation_rule_a8225 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleconditional_equation_rule_a8238 = new BitSet(new long[]{0x00050005C0F008A0L,0x00000000070B6E20L});
    public static final BitSet FOLLOW_rulealgorithm_in_ruleconditional_equation_rule_a8273 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_a8286 = new BitSet(new long[]{0x00050005C0F008A0L,0x00000000070B6E20L});
    public static final BitSet FOLLOW_rulealgorithm_elseif_in_ruleconditional_equation_rule_a8321 = new BitSet(new long[]{0x0001000000000000L,0x0000000000082000L});
    public static final BitSet FOLLOW_77_in_ruleconditional_equation_rule_a8336 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007034E20L});
    public static final BitSet FOLLOW_rulealgorithm_in_ruleconditional_equation_rule_a8371 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleconditional_equation_rule_a8384 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007034E20L});
    public static final BitSet FOLLOW_48_in_ruleconditional_equation_rule_a8396 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleconditional_equation_rule_a8405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_clause_e_in_entryRulefor_clause_e8438 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_clause_e8448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_rulefor_clause_e8482 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulefor_indices_in_rulefor_clause_e8516 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_rulefor_clause_e8529 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_rulefor_clause_e8564 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulefor_clause_e8577 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007124220L});
    public static final BitSet FOLLOW_48_in_rulefor_clause_e8587 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_rulefor_clause_e8596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_clause_a_in_entryRulefor_clause_a8629 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_clause_a8639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_rulefor_clause_a8673 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulefor_indices_in_rulefor_clause_a8707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_rulefor_clause_a8720 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007034E20L});
    public static final BitSet FOLLOW_rulealgorithm_in_rulefor_clause_a8755 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulefor_clause_a8768 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007034E20L});
    public static final BitSet FOLLOW_48_in_rulefor_clause_a8778 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_rulefor_clause_a8787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulewhile_clause_in_entryRulewhile_clause8820 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulewhile_clause8830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_rulewhile_clause8864 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_rulewhile_clause8898 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_rulewhile_clause8911 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007034E20L});
    public static final BitSet FOLLOW_rulealgorithm_in_rulewhile_clause8946 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulewhile_clause8959 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007034E20L});
    public static final BitSet FOLLOW_48_in_rulewhile_clause8969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_rulewhile_clause8978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulewhen_clause_e_in_entryRulewhen_clause_e9011 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulewhen_clause_e9021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_rulewhen_clause_e9055 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_rulewhen_clause_e9089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_rulewhen_clause_e9102 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007164220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_rulewhen_clause_e9137 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulewhen_clause_e9150 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007164220L});
    public static final BitSet FOLLOW_ruleelse_when_e_in_rulewhen_clause_e9185 = new BitSet(new long[]{0x0001000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_48_in_rulewhen_clause_e9199 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_rulewhen_clause_e9208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleelse_when_e_in_entryRuleelse_when_e9241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleelse_when_e9251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleelse_when_e9285 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleelse_when_e9319 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleelse_when_e9332 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_ruleelse_when_e9367 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleelse_when_e9380 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_rulewhen_clause_a_in_entryRulewhen_clause_a9414 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulewhen_clause_a9424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_rulewhen_clause_a9458 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_rulewhen_clause_a9492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_rulewhen_clause_a9505 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007164220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_rulewhen_clause_a9540 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulewhen_clause_a9553 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007164220L});
    public static final BitSet FOLLOW_ruleelse_when_a_in_rulewhen_clause_a9588 = new BitSet(new long[]{0x0001000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_48_in_rulewhen_clause_a9602 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_rulewhen_clause_a9611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleelse_when_a_in_entryRuleelse_when_a9644 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleelse_when_a9654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleelse_when_a9688 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleelse_when_a9722 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleelse_when_a9735 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_ruleelse_when_a9770 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleelse_when_a9783 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleequation_rule_elseif_in_entryRuleequation_rule_elseif9817 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleequation_rule_elseif9827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleequation_rule_elseif9861 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleequation_rule_elseif9895 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleequation_rule_elseif9908 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_ruleequation_rule_elseif9943 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleequation_rule_elseif9956 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_rulealgorithm_elseif_in_entryRulealgorithm_elseif9990 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulealgorithm_elseif10000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_rulealgorithm_elseif10034 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_rulealgorithm_elseif10068 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_rulealgorithm_elseif10081 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleequation_rule_in_rulealgorithm_elseif10116 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_rulealgorithm_elseif10129 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007124220L});
    public static final BitSet FOLLOW_ruleconnect_clause_in_entryRuleconnect_clause10164 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconnect_clause10175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_ruleconnect_clause10213 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_LPAR_in_ruleconnect_clause10228 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleconnector_ref_in_ruleconnect_clause10255 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_COMMA_in_ruleconnect_clause10275 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleconnector_ref_in_ruleconnect_clause10302 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_ruleconnect_clause10322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconnector_ref_in_entryRuleconnector_ref10366 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconnector_ref10377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleconnector_ref10417 = new BitSet(new long[]{0x0000000100002002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_ruleconnector_ref10445 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_RULE_DOT_in_ruleconnector_ref10468 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleconnector_ref_2_in_ruleconnector_ref10495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleconnector_ref_2_in_entryRuleconnector_ref_210541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleconnector_ref_210552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_ruleconnector_ref_210592 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_ruleconnector_ref_210620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_entryRuleexpression10666 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression10677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleif_expression_in_ruleexpression10724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_ruleexpression10757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleif_expression_in_entryRuleif_expression10803 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleif_expression10814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleif_expression10852 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleif_expression10874 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleif_expression10892 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleif_expression10914 = new BitSet(new long[]{0x0000000000000000L,0x0000000000082000L});
    public static final BitSet FOLLOW_ruleelseif_expression_in_ruleif_expression10942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000082000L});
    public static final BitSet FOLLOW_77_in_ruleif_expression10962 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleif_expression10984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleelseif_expression_in_entryRuleelseif_expression11028 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleelseif_expression11039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleelseif_expression11077 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleelseif_expression11099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleelseif_expression11117 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleelseif_expression11139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_indices_in_entryRulefor_indices11183 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_indices11194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices11241 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulefor_indices11262 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulefor_index_in_rulefor_indices11289 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rulefor_index_in_entryRulefor_index11335 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_index11346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulefor_index11386 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_rulefor_index11405 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_index11427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expression_in_entryRulesimple_expression11473 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesimple_expression11484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expr_in_rulesimple_expression11530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesimple_expr_in_entryRulesimple_expr11573 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesimple_expr11584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expr11631 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_RULE_COLON_in_rulesimple_expr11652 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000200L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expr11679 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_RULE_COLON_in_rulesimple_expr11700 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000200L});
    public static final BitSet FOLLOW_rulelogical_expression_in_rulesimple_expr11727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_expression_in_entryRulelogical_expression11775 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_expression11786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression11833 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_rulelogical_expression11852 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000200L});
    public static final BitSet FOLLOW_rulelogical_term_in_rulelogical_expression11874 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_rulelogical_term_in_entryRulelogical_term11920 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_term11931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term11978 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_rulelogical_term11997 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000200L});
    public static final BitSet FOLLOW_rulelogical_factor_in_rulelogical_term12019 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_rulelogical_factor_in_entryRulelogical_factor12065 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulelogical_factor12076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_rulelogical_factor12115 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_rulerelation_in_rulelogical_factor12139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulerelation_in_entryRulerelation12183 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulerelation12194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation12241 = new BitSet(new long[]{0x00000000000FC002L});
    public static final BitSet FOLLOW_RULE_LESS_in_rulerelation12263 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_LESSEQ_in_rulerelation12289 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_GREATER_in_rulerelation12315 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_GREATEREQ_in_rulerelation12341 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_EQEQ_in_rulerelation12367 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_LESSGT_in_rulerelation12393 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_rulerelation12421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearithmetic_expression_in_entryRulearithmetic_expression12467 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearithmetic_expression12478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleunary_arithmetic_expression_in_rulearithmetic_expression12525 = new BitSet(new long[]{0x0000000000F00002L});
    public static final BitSet FOLLOW_RULE_PLUS_in_rulearithmetic_expression12547 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_MINUS_in_rulearithmetic_expression12573 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_PLUS_EW_in_rulearithmetic_expression12599 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_MINUS_EW_in_rulearithmetic_expression12625 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_ruleterm_in_rulearithmetic_expression12653 = new BitSet(new long[]{0x0000000000F00002L});
    public static final BitSet FOLLOW_ruleunary_arithmetic_expression_in_entryRuleunary_arithmetic_expression12699 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleunary_arithmetic_expression12710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_PLUS_in_ruleunary_arithmetic_expression12751 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_MINUS_in_ruleunary_arithmetic_expression12806 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_PLUS_EW_in_ruleunary_arithmetic_expression12861 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_MINUS_EW_in_ruleunary_arithmetic_expression12916 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleterm_in_ruleunary_arithmetic_expression12977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleterm_in_entryRuleterm13021 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleterm13032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm13079 = new BitSet(new long[]{0x000000000F000002L});
    public static final BitSet FOLLOW_RULE_STAR_in_ruleterm13101 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_SLASH_in_ruleterm13127 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_STAR_EW_in_ruleterm13153 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_SLASH_EW_in_ruleterm13179 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_rulefactor_in_ruleterm13207 = new BitSet(new long[]{0x000000000F000002L});
    public static final BitSet FOLLOW_rulefactor_in_entryRulefactor13253 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefactor13264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor13311 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_RULE_POWER_in_rulefactor13333 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_RULE_POWER_EW_in_rulefactor13359 = new BitSet(new long[]{0x00050005C00008A0L,0x0000000006000200L});
    public static final BitSet FOLLOW_ruleprimary_in_rulefactor13387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleprimary_in_entryRuleprimary13433 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleprimary13444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_INTEGER_in_ruleprimary13484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UNSIGNED_REAL_in_ruleprimary13510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleprimary13536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_ruleprimary13560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ruleprimary13579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference__function_call_in_ruleprimary13607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleprimary13632 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rulefunction_call_in_ruleprimary13654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LPAR_in_ruleprimary13682 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary13709 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_ruleprimary13729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LBRACK_in_ruleprimary13757 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary13784 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_RULE_SEMICOLON_in_ruleprimary13805 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_list_in_ruleprimary13832 = new BitSet(new long[]{0x0000000200000010L});
    public static final BitSet FOLLOW_RULE_RBRACK_in_ruleprimary13854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LBRACE_in_ruleprimary13882 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_rulefor_or_expression_list_in_ruleprimary13909 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_RULE_RBRACE_in_ruleprimary13929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleprimary13954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference__function_call_in_entryRulecomponent_reference__function_call13993 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_reference__function_call14004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_rulecomponent_reference__function_call14052 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_rulefunction_call_in_rulecomponent_reference__function_call14080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_rulecomponent_reference__function_call14108 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_LPAR_in_rulecomponent_reference__function_call14123 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_rulecomponent_reference__function_call14143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_path_in_entryRulename_path14188 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_path14199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename_path14239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename_path14266 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RULE_DOT_in_rulename_path14286 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_in_rulename_path14313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulename_path_star_in_entryRulename_path_star14358 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulename_path_star14369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename_path_star14410 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_RULE_STAR_EW_in_rulename_path_star14431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulename_path_star14461 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RULE_DOT_in_rulename_path_star14481 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulename_path_star_in_rulename_path_star14508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_entryRulecomponent_reference14553 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomponent_reference14564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulecomponent_reference14604 = new BitSet(new long[]{0x0000000100002002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_rulecomponent_reference14632 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_RULE_DOT_in_rulecomponent_reference14655 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulecomponent_reference_in_rulecomponent_reference14682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_call_in_entryRulefunction_call14728 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_call14739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LPAR_in_rulefunction_call14779 = new BitSet(new long[]{0x00050005C0F00CA0L,0x0000000007000220L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_rulefunction_call14806 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_RULE_RPAR_in_rulefunction_call14826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefunction_arguments_in_entryRulefunction_arguments14870 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefunction_arguments14881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_or_expression_list_in_rulefunction_arguments14929 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_rulefunction_arguments14959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_or_expression_list_in_entryRulefor_or_expression_list15005 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_or_expression_list15016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_or_expression_list15063 = new BitSet(new long[]{0x0000000000000102L,0x0000000000004000L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulefor_or_expression_list15085 = new BitSet(new long[]{0x00050005C0F008A2L,0x0000000007000220L});
    public static final BitSet FOLLOW_rulefor_or_expression_list2_in_rulefor_or_expression_list15113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_rulefor_or_expression_list15141 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulefor_indices_in_rulefor_or_expression_list15163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulefor_or_expression_list2_in_entryRulefor_or_expression_list215210 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulefor_or_expression_list215221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulefor_or_expression_list215268 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulefor_or_expression_list215289 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_rulefor_or_expression_list2_in_rulefor_or_expression_list215316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments_in_entryRulenamed_arguments15362 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_arguments15373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments2_in_rulenamed_arguments15419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_arguments2_in_entryRulenamed_arguments215462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_arguments215473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulenamed_argument_in_rulenamed_arguments215520 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulenamed_arguments215541 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rulenamed_argument_in_rulenamed_arguments215568 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rulenamed_argument_in_entryRulenamed_argument15614 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulenamed_argument15625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_IDENT_in_rulenamed_argument15665 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_EQUALS_in_rulenamed_argument15685 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_in_rulenamed_argument15712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list_in_entryRuleexpression_list15756 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression_list15767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list2_in_ruleexpression_list15813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_list2_in_entryRuleexpression_list215856 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleexpression_list215867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_ruleexpression_list215914 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_COMMA_in_ruleexpression_list215935 = new BitSet(new long[]{0x00050005C0F008A0L,0x0000000007000220L});
    public static final BitSet FOLLOW_ruleexpression_list2_in_ruleexpression_list215962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulearray_subscripts_in_entryRulearray_subscripts16008 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulearray_subscripts16019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LBRACK_in_rulearray_subscripts16059 = new BitSet(new long[]{0x00050005C0F00AA0L,0x0000000007000220L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts16086 = new BitSet(new long[]{0x0000000200000100L});
    public static final BitSet FOLLOW_RULE_COMMA_in_rulearray_subscripts16107 = new BitSet(new long[]{0x00050005C0F00AA0L,0x0000000007000220L});
    public static final BitSet FOLLOW_rulesubscript_in_rulearray_subscripts16134 = new BitSet(new long[]{0x0000000200000100L});
    public static final BitSet FOLLOW_RULE_RBRACK_in_rulearray_subscripts16156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulesubscript_in_entryRulesubscript16200 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulesubscript16211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleexpression_in_rulesubscript16258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_COLON_in_rulesubscript16284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulecomment_in_entryRulecomment16327 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulecomment16337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_rulecomment16396 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_ruleannotation_in_rulecomment16435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulestring_comment_in_entryRulestring_comment16474 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulestring_comment16485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment16525 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_RULE_PLUS_in_rulestring_comment16546 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulestring_comment16566 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_ruleannotation_in_entryRuleannotation16611 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleannotation16621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ruleannotation16655 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleclass_modification_in_ruleannotation16677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_ruleClassType16723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_ruleClassType16738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_ruleClassType16753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_ruleClassType16768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_ruleClassType16783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_ruleClassType16798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_98_in_ruleClassType16813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleClassType16828 = new BitSet(new long[]{0x0000000000000002L});

}