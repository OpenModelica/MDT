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

package org.modelica.mdt.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author Peter Bunus
 * @author Andreas Remar, x05andre@ida.liu.se
 * 
 * 2005-09-14, Andreas Remar
 *    Recoded from scratch, most code abducted from PyDev
 */
public class ModelicaRuleScanner extends RuleBasedScanner 
{

	private static Color COMMENT_COLOR 
		= new Color(Display.getCurrent(), new RGB(0, 139, 0));
	private static Color KEYWORD_COLOR 
		= new Color(Display.getCurrent(), new RGB(139, 0, 0));
	private static Color CODE_COLOR
		= new Color(Display.getCurrent(), new RGB(0, 0, 0));
	private static Color TYPE_COLOR 
		= new Color(Display.getCurrent(), new RGB(0, 0, 139));
	private static Color NUMBER_COLOR 
		= new Color(Display.getCurrent(), new RGB(139, 0, 139));
	private static Color ERROR_COLOR 
		= new Color(Display.getCurrent(), new RGB(255, 0, 0));

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
		static public String[] keywords = {
			"algorithm","and","annotation","assert",
			"block","break",
			"class","connect","connector","constant",
			"der","discrete",
			"each","else","elseif","elsewhen","encapsulated","end",
			"enumeration","equation",
			"extends","external","false","final",
			"flow","for","function",
			"if","import","in","initial","inner","input",
			"loop","model","not","or","outer",
			"output","overload","package","parameter",
			"partial","protected","public","record",
			"redeclare","replacable","return", "terminate",
			"then","time","true","type","when","while","within",
			
			/* The following are Meta-Modelica Keywords */
			"as", "case", "list", "local", "match", "matchcontinue" ,
			"tuple", "uniontype"
		};
		
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
			"Boolean","Integer","Real","String"
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
            return Character.isDigit(c);
        }

        /**
         * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
         */
        public boolean isWordPart(char c) 
        {
            return Character.isDigit(c) || c == 'e'  || c == '.';
        }
	}	
	
	public ModelicaRuleScanner() 
	{
		super();
		
		setupRules();
	}

	private void setupRules()
	{
		IToken commentToken = 
			new Token(new TextAttribute(COMMENT_COLOR,null,SWT.BOLD));
		IToken keywordToken = 
			new Token(new TextAttribute(KEYWORD_COLOR,null,SWT.BOLD));
		IToken defaultToken = 
			new Token(new TextAttribute(CODE_COLOR,null,SWT.BOLD));
		IToken typeToken = 
			new Token(new TextAttribute(TYPE_COLOR,null,SWT.BOLD));
		IToken numberToken = 
			new Token(new TextAttribute(NUMBER_COLOR,null,SWT.BOLD));
		IToken errorToken = 
			new Token(new TextAttribute(ERROR_COLOR,null,SWT.BOLD));

		setDefaultReturnToken(errorToken);
		List<IRule> rules = new ArrayList<IRule>();
		
		rules.add(new WhitespaceRule(new WhiteSpace()));
		
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
		rules.add(wordRule);

		rules.add(new MultiLineRule( "\"", "\"", commentToken,'\\'));
		rules.add(new EndOfLineRule("//", commentToken,'\\'));
		rules.add(new MultiLineRule("/*", "*/", commentToken, (char) 0, true));
		rules.add(new WordRule(new NumberDetector(), numberToken));
		
		IRule[] result = new IRule[rules.size()];
		rules.toArray(result);
		setRules(result);
	}
}
