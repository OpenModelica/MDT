/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005-2010, Linköpings universitet, 
 * Department of Computer and Information Science, PELAB
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
 * * Neither the name of Linköpings universitet nor the names of its
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
			"Boolean",
			"Integer",
			"Real",
			"String",
			"Any",    /* Any is in MetaModelica */
			"Option", /* Option is in MetaModelica */			
			"Array",  /* Array  is in MetaModelica */
			"List",   /* List   is in MetaModelica */
			"Tuple",  /* Tuple  is in MetaModelica */
			"LVar",   /* LVar  is in MetaModelica */
			"option", /* Option is in MetaModelica */			
			"array",  /* Array  is in MetaModelica */
			"list",   /* List   is in MetaModelica */
			"tuple",  /* Tuple  is in MetaModelica */
			"lvar",   /* lvar  is in MetaModelica */
			"SOME",   /* SOME  is in MetaModelica */
			"NONE"    /* SOME  is in MetaModelica */
		};

//		public ModelicaTypeDetector() {
//		}

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
		      /* roots */
		      "set_global_root",   "setGlobalRoot",
		      "get_global_root",   "getGlobalRoot",

		      /* references */
		      "reference_eq",      "referenceEq",
		      "reference_integer", "referenceInteger",
		      "reference_update",  "referenceUpdate",
		      "reference_setnth",  "referenceSetNth",
		      "reference_share",   "referenceShare",
		      
		      /* values */
		      "value_constructor", "valueConstructor",
		      "value_slots",       "valueSlots",
		      "value_eq",          "valueEq",
		      "value_match",       "valueMatch",

		      /* booleans */
		      "bool_and",    "boolAnd",
		      "bool_not",    "boolNot",
		      "bool_or",     "boolOr",
		      "bool_eq",     "boolEq",
		      "bool_string", "boolString",
		      
		      /* characters */
		      "char_int", "charInt",
		      "string_char_int", "stringCharInt",
		      
		      /* integers */
		      "int_abs",         "intAbs",
		      "int_add",         "intAdd",
		      "int_char",        "intChar",
		      "int_string_char", "intStringChar",
		      "int_div",         "intDiv",
		      "int_eq",          "intEq",
		      "int_ge",          "intGe",
		      "int_gt",          "intGt",
		      "int_le",          "intLe",
		      "int_lt",          "intLt",
		      "int_max",         "intMax",
		      "int_min",         "intMin",
		      "int_mod",         "intMod",
		      "int_mul",         "intMul",
		      "int_ne",          "intNe",
		      "int_neg",         "intNeg",
		      "int_real",        "intReal",
		      "int_string",      "intString",
		      "int_sub",         "intSub",
		      "int_bit_not",     "intBitNot",
		      "int_bit_and",     "intBitAnd",
		      "int_bit_or",      "intBitOr",
		      "int_bit_xor",     "intBitXor",
		      "int_bit_lshift",  "intBitLShift",
		      "int_bit_rshift",  "intBitRShift",
		      
		      /* lists */
		      "list_append",             "listAppend",
		      "list_delete",             "listDelete",
		      "list_length",             "listLength",
		      "list_member",             "listMember",
		      "list_nth",                "listNth",
		      "list_get",                "listGet", 
		      "list_reverse",            "listReverse", 
		      "list_string",             "listString",
		      "string_char_list_string", "stringCharListString",
		      "string_list_string_char", "stringListStringChar",
		      "list_vector",             "listVector",
		      "list_array",              "listArray",
		      "list_append_usafe",       "listAppendUnsafe",
		      /*
		      "list_map",                "listMap",
		      */
		      
		      /* logical variables */
		      "lvar_get", "listGet",
		      "lvar_new", "lvarNew",
		      "lvar_set", "lvarSet",

		      /* reals */
		      "real_abs",    "realAbs",
		      "real_add",    "realAdd",
		      "real_atan",   "realAtan",
		      "real_cos",    "realCos",
		      "real_div",    "realDiv",
		      "real_eq",     "realEq",
		      "real_exp",    "realExp",
		      "real_floor",  "realFloor",
		      "real_ceil",   "realCeil",
		      "real_ge",     "realGe",
		      "real_gt",     "realGt",
		      "real_int",    "realInt",
		      "real_le",     "realLe",
		      "real_ln",     "realLn",
		      "real_lt",     "realLt",
		      "real_max",    "realMax",
		      "real_min",    "realMin",
		      "real_mod",    "realMod",
		      "real_mul",    "realMul",
		      "real_ne",     "realNe",
		      "real_neg",    "realNeg",
		      "real_pow",    "realPow",
		      "real_sin",    "realSin",
		      "real_sqrt",   "realSqrt",
		      "real_string", "realString",
		      "real_sub",    "realSub",
		      "real_asin",   "realAsin",
		      "real_acos",   "realAcos",
		      "real_atan2",  "realAtan2",
		      "real_cosh",   "realCosh",
		      "real_log",    "realLog",
		      "real_log10",  "realLog10",
		      "real_sinh",   "realSinh",
		      "real_tanh",   "realTanh",
		      
		      /* strings */
		      "string_append",             "stringAppend",
		      "string_int",                "stringInt",
		      "string_length",             "stringLength",
		      "string_list",               "stringList",
		      "string_nth",                "stringNth",
		      "string_nth_string_char",    "stringNthStringChar",
		      "string_get",                "stringGet",
		      "string_get_string_char",    "stringGetStringChar",
		      "string_setnth",             "stringSetNth",
		      "string_setnth_string_char", "stringSetNthStringChar",
		      "string_update",             "stringUpdate",
		      "string_update_string_char", "stringUpdateStringChar",
		      "string_equal",              "stringEqual",
		      "string_eq",                 "stringEq",
		      "string_compare",            "stringCompare",
		      "string_append_list",        "stringAppendList",
		      "string_hash",               "stringHash",
		      "string_hash_djb2",          "stringHashDjb2",
		      "string_hash_sdbm",           "stringHashSdbm",
		      
		      /* immutable vectors */
		      "vector_length", "vectorLength",
		      "vector_list",   "vectorList",
		      "vector_nth",    "vectorNth",
		      "vector_get",    "vectorGet",
		      "vector_setnth", "vectorSetNth",
		      "vector_update", "vectorUpdate",
		      "vector_create", "vectorCreate",
		      "vector_add",    "vectorAdd",
		      "vector_array",  "vectorArray",
		      "vector_copy",   "vectorCopy",
		      
		      /* mutable arrays */      
		      "array_length",  "arrayLength",
		      "array_list",    "arrayList",
		      "array_nth",     "arrayNth",
		      "array_get",     "arrayGet",
		      "array_setnth",  "arraySetNth",
		      "array_update",  "arrayUpdate",
		      "array_create",  "arrayCreate",
		      "array_add",     "arrayAdd",
		      "array_vector",  "arrayVector",
		      "array_copy",    "arrayCopy",

		      /* if expressions */
		      "if_exp", "ifExp",
		      /* misc */
		      "clock", 
		      "print", 
		      "tick", 
		      "unique", 
		      "enable_trace",  "enableTrace",
		      "disable_trace", "disableTrace", 
		      "cons", "fill" 
		};

//		public ModelicaBuiltinRelationsDetector() {
//		}
				
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
		IToken builtinToken     = new Token(ModelicaEditorColors.BUILTIN_ATTRIBUTE);
		//IToken functionToken    = new Token(ModelicaEditorColors.FUNCTION_ATTRIBUTE);		
		//IToken operatorsToken   = new Token(ModelicaEditorColors.OPERATORS_ATTRIBUTE);
		
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
