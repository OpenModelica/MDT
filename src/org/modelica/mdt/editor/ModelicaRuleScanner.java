/*
 * Created on Jan 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
								 implements ModelicaSyntax {

	private static Color COMMENT_COLOR = new Color(Display.getCurrent(), new RGB(0, 139, 0));
	private static Color KEYWORD_COLOR = new Color(Display.getCurrent(), new RGB(139, 0, 0));
	private static Color CODE_COLOR = new Color(Display.getCurrent(), new RGB(0, 0, 0));
	private static Color TYPE_COLOR = new Color(Display.getCurrent(), new RGB(0, 0, 139));
	private static Color NUMBER_COLOR = new Color(Display.getCurrent(), new RGB(139, 0, 139));
	private static Color ERROR_COLOR = new Color(Display.getCurrent(), new RGB(255, 0, 0));

	/**
	 * Whitespace detector
	 */
	static private class WhiteSpace implements IWhitespaceDetector {
		public boolean isWhitespace(char c) {return Character.isWhitespace(c);}
	}
	
	/**
	 * Modelica keyword detector
	 */
	static private class ModelicaKeywordDetector implements IWordDetector {
		// keywords list has to be alphabetized for this to work properly
		static public String[] keywords = {
			"algorithm","and","annotation","block","break",
			"class","connector","constant",
			"der","discrete",
			"each","elseif","elsewhen","encapsulated","end",
			"enumeration","equation",
			"extends","external","false","final",
			"flow","for","function",
			"if","import","in","initial","inner","input",
			"loop","model","not","or","outer",
			"output","package","parameter",
			"partial","protected","public","record",
			"redeclare","replacable","return",
			"then","time","true","type","when","while","within"
		};

		public ModelicaKeywordDetector() {
		}

		public boolean isWordStart(char c) {
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) {
			return Character.isJavaIdentifierPart(c);
		}
	}

	/**
	 * Modelica type detector
	 */
	static private class ModelicaTypeDetector implements IWordDetector {
		// keywords list has to be alphabetized for this to work properly
		static public String[] keywords = {
			"Boolean","Integer","Real","String"
		};

		public ModelicaTypeDetector() {
		}

		public boolean isWordStart(char c) {
			return Character.isJavaIdentifierStart(c);
		}

		public boolean isWordPart(char c) {
			return Character.isJavaIdentifierPart(c);
		}
	}

	static public class NumberDetector implements IWordDetector{
        /**
         * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
         */
        public boolean isWordStart(char c) {
            return Character.isDigit(c);
        }

        /**
         * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
         */
        public boolean isWordPart(char c) {
            return Character.isDigit(c) || c == 'e'  || c == '.';
        }
	}	
	
	public ModelicaRuleScanner() {
		super();
		
		setupRules();
	}

	private void setupRules()
	{
		IToken commentToken = new Token(new TextAttribute(COMMENT_COLOR,null,SWT.BOLD));
		IToken keywordToken = new Token(new TextAttribute(KEYWORD_COLOR,null,SWT.BOLD));
		IToken defaultToken = new Token(new TextAttribute(CODE_COLOR,null,SWT.BOLD));
		IToken typeToken = new Token(new TextAttribute(TYPE_COLOR,null,SWT.BOLD));
		IToken numberToken = new Token(new TextAttribute(NUMBER_COLOR,null,SWT.BOLD));
		IToken errorToken = new Token(new TextAttribute(ERROR_COLOR,null,SWT.BOLD));

		setDefaultReturnToken(errorToken);
		List rules = new ArrayList();
		
		rules.add(new WhitespaceRule(new WhiteSpace()));
		
		WordRule wordRule = new WordRule(new ModelicaKeywordDetector(), defaultToken);
		for (int i=0; i<ModelicaKeywordDetector.keywords.length;i++) {
			wordRule.addWord(ModelicaKeywordDetector.keywords[i], keywordToken);
		}
		for (int i=0; i<ModelicaTypeDetector.keywords.length;i++) {
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
