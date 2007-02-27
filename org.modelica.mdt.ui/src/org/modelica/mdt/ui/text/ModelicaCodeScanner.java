/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Link�pings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;


import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.rules.Token;
import org.modelica.mdt.ui.editor.ModelicaEditorColors;

/**
 * @author Adrian Pop
 * @author Peter Bunus
 * @author Andreas Remar, x05andre@ida.liu.se
 * 
 * 2005-09-14, Andreas Remar
 *    Recoded from scratch, most code abducted from PyDev
 * 2006-03-27, Adrian Pop
 *    Changed so that bold is used only for keywords and error tokens.
 * 2006-04-28, Adrian Pop
 *    Splited into:
 *    - CodeScanner
 *    - String Scanner 
 *    - SingleCommentScanner
 *    - MultiCommentScanner
 */
public class ModelicaCodeScanner extends RuleBasedScanner
{
	/**
	 * Whitespace detector
	 */
	static private class WhiteSpace implements IWhitespaceDetector
	{
		public boolean isWhitespace(char c)
		{
			return Character.isWhitespace(c);
		}
	}
	
	/**
	 * Modelica keyword detector
	 */
	static private class ModelicaKeywordDetector implements IWordDetector
	{
		/* keywords list has to be alphabetized for this to work properly */
		static public String[] keywords = ModelicaKeywords.keywords;
		
		public ModelicaKeywordDetector() 
		{
		}

		public boolean isWordStart(char c) 
		{
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) 
		{
			return Character.isJavaIdentifierPart(c);
		}
	}

	/**
	 * Modelica type detector
	 */
	static private class ModelicaTypeDetector implements IWordDetector 
	{
		/* keywords list has to be alphabetized for this to work properly */
		static public String[] keywords = 
		{
			"Any", /* Any is in MetaModelica */
			"Boolean",
			"Integer",
			"Option", /* Option is in MetaModelica */
			"Real",
			"String"
		};

		public ModelicaTypeDetector() 
		{
		}

		public boolean isWordStart(char c) 
		{
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) 
		{
			return Character.isJavaIdentifierPart(c);
		}
	}


	static public class NumberDetector implements IWordDetector
	{
        /**
         * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
         */
        public boolean isWordStart(char c) 
        {
            return (Character.isDigit(c));
        }

        /**
         * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
         */
        public boolean isWordPart(char c) 
        {
            return Character.isDigit(c) || c == 'e'  || c == '.';
        }
	}	

	/**
	 * MDT builtin relation detector
	 */
	static private class ModelicaBuiltinRelationsDetector implements IWordDetector 
	{
		/* keywords list has to be alphabetized for this to work properly */
		static public String[] keywords = 
		{
		      /* booleans */
			  "bool_and","bool_not","bool_or",
			  "boolAnd","boolNot","boolOr",
			  /* integers */
			  "int_abs","int_add","int_string_char","int_div","int_eq","int_ge","int_gt",
			  "int_le","int_lt","int_max","int_min","int_mod","int_mul","int_ne","int_neg","int_real",
			  "int_string","int_sub",
			  "intAbs","intAdd","intChar","intStringChar","intDiv","intEq","intGe","intGt","intLe","intLt",
			  "intMax","intMin","intMod","intMul","intNe","intNeg","intReal","intString","intSub",
			  /* lists */
			  "list_append","list_delete","list_length","list_member","list_nth","list_reverse",
			  "string_char_list_string","list_vector","list_array",	  
			  "listAppend","listDelete","listLength","listMember","listNth","listReverse",
			  "stringCharListString","listVector","listArray",
			  /* logical variables */
			  "lvarGet","lvarNew","lvarSet",
			  "lvar_get","lvar_new","lvar_set",
			  /* reals */
			  "real_abs","real_add","real_atan","real_cos","real_div","real_eq","real_exp","real_floor",
			  "real_ge","real_gt","real_int","real_le","real_ln","real_lt","real_max","real_min",
			  "real_mod","real_mul","real_ne","real_neg","real_pow","real_sin","real_sqrt",
			  "real_string","real_sub",
			  "realAbs","realAdd","realAtan","realCos","realDiv","realEq","realExp","realFloor","realGe",
			  "realGt","realInt","realLe","realLn","realLt","realMax","realMin","realMod","realMul",
			  "realNe","realNeg","realPow","realSin","realSqrt","realString","realSub",
			  /* strings */
			  "string_append","string_int","string_length","string_list_string_char",
			  "string_nth","string_nth_string_char","string_get","string_get_string_char",
			  "string_setnth","string_setnth_string_char","string_update","string_update_string_char",
			  "string_equal","string_compare",
			  "stringAppend","stringInt","stringLength","stringListStringChar","stringNth",
			  "stringNthStringChar","stringGet","stringGetStringChar","stringSetNth","stringSetNthStringChar",
			  "stringUpdate","stringUpdateStringChar","stringEqual","stringCompare",
			  /* mutable arrays */      
			  "array_length","array_list","array_nth","array_get","array_setnth",
			  "array_update","array_create","array_add","array_vector","array_copy",      
			  "arrayLength","arrayList","arrayNth","arrayGet","arraySetNth","arrayUpdate",	  
			  "arrayCreate","arrayAdd","arrayVector","arrayCopy",
			  /* misc */
			  "fill",
			  "ifExp", "if_exp",
			  "clock","print","tick",
			  "cons"
		};

		public ModelicaBuiltinRelationsDetector() 
		{
		}
				
		static public Vector<String> getSortedKeywordsList()
		{
			Vector<String> keys = new Vector<String>(keywords.length);
			for (int i=0; i< keywords.length;i++) 
			{
				keys.add(keywords[i]);
			}
			Collections.sort(keys, new Comparator<String>() {
				   public int compare(String o1, String o2) {
					      return o1.compareTo(o2);
					   }
					});
			return keys;
		}

		public boolean isWordStart(char c) 
		{
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) 
		{
			if (c == '.') { return true; } 
			return Character.isJavaIdentifierPart(c); 
		}
	}
	
	/**
	 * Modelica constructor detector
	 */	
	static public class ModelicaConstructorDetector implements IWordDetector
	{
        /**
         * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
         */
		public boolean isWordStart(char c) 
		{
			if (c == '_') { return false; }
			return Character.isJavaIdentifierStart(c) && Character.isUpperCase(c);
		}

		public boolean isWordPart(char c) 
		{
			if (c == '_') { return true; }			
			if (Character.isDigit(c)) {return true; }
			return Character.isJavaIdentifierPart(c) && Character.isUpperCase(c);
		}
	}	
	
	public class WordRuleExtended extends WordRule implements IRule
	{
		
		public WordRuleExtended(IWordDetector detector)
		{
			super(detector);
		}
		
        public WordRuleExtended(IWordDetector detector, IToken defaultToken)
        {
        	super(detector, defaultToken);
        }
        
        public IToken evaluate(ICharacterScanner scanner)
        {
        	IToken token = super.evaluate(scanner);
        	int chr = scanner.read();
        	if (Character.isWhitespace(chr) || 
        	    (!Character.isLetterOrDigit(chr) && (char)chr != '.')) 
        	{
        		scanner.unread();
        		return token;
        	}
        	else 
        	{
        		scanner.unread();
        		return Token.UNDEFINED;
        	}
        }

	}		
	
	public ModelicaCodeScanner() 
	{
		super();
		setupRules();
	}

	private void setupRules()
	{
		IToken keywordToken     = new Token(ModelicaEditorColors.KEYWORD_ATTRIBUTE);
		IToken defaultToken     = new Token(ModelicaEditorColors.CODE_ATTRIBUTE);
		IToken typeToken        = new Token(ModelicaEditorColors.TYPE_ATTRIBUTE);
		IToken numberToken      = new Token(ModelicaEditorColors.NUMBER_ATTRIBUTE);
		IToken errorToken       = new Token(ModelicaEditorColors.ERROR_ATTRIBUTE);
		IToken constructorToken = new Token(ModelicaEditorColors.CONSTRUCTOR_ATTRIBUTE);
		IToken builtinToken     = new Token(ModelicaEditorColors.BUILTIN_ATTRIBUTE);
		IToken functionToken    = new Token(ModelicaEditorColors.FUNCTION_ATTRIBUTE);		
		IToken operatorsToken   = new Token(ModelicaEditorColors.OPERATORS_ATTRIBUTE);
		
		setDefaultReturnToken(errorToken);
		List<IRule> rules = new ArrayList<IRule>();
		
		rules.add(new WhitespaceRule(new WhiteSpace()));
		
		rules.add(new WordRule(new NumberDetector(), numberToken));
		WordRule wordRule = 
			new WordRule(new ModelicaKeywordDetector(), defaultToken);
		for (int i=0; i<ModelicaKeywordDetector.keywords.length;i++) 
		{
			wordRule.addWord(ModelicaKeywordDetector.keywords[i], keywordToken);
		}
		for (int i=0; i<ModelicaTypeDetector.keywords.length;i++) 
		{
			wordRule.addWord(ModelicaTypeDetector.keywords[i], typeToken);
		}
		Vector<String> keys = ModelicaBuiltinRelationsDetector.getSortedKeywordsList();
		for (int i=0; i< keys.size(); i++) 
		{
			wordRule.addWord(keys.elementAt(i), builtinToken);
		}		
		rules.add(wordRule);


		IRule[] result = new IRule[rules.size()];
		rules.toArray(result);
		setRules(result);
	}
}
