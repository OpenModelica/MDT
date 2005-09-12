/*
 * Created on Jan 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.modelica.mdt.editor;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import java.lang.String;

/**
 * @author Peter Bunus
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ModelicaRuleScanner extends RuleBasedScanner
								 implements ModelicaSyntax {

	//comments are shown in green 4 (0,139,0)
	private static Color COMMENT_COLOR = new Color(Display.getCurrent(), new RGB(0, 139, 0));
	//Modelica reserved keywords are shown in red4 (139,0,0)
	private static Color KEYWORD_COLOR = new Color(Display.getCurrent(), new RGB(139, 0, 0));
	//Modelica types  are shown in blue4 (0,0,139)
	private static Color TYPES_COLOR = new Color(Display.getCurrent(), new RGB(0, 0, 139));

	public ModelicaRuleScanner() {
		
		IToken commentToken = new Token(new TextAttribute(COMMENT_COLOR,null,SWT.BOLD));
		IToken keywordToken = new Token(new TextAttribute(KEYWORD_COLOR,null,SWT.BOLD));
		IToken typesToken   = new Token(new TextAttribute(TYPES_COLOR,null,SWT.BOLD));
			
		IRule[] rules = new IRule[4];
			
	    		
		// Add word rule for keywords, types, and constants.
		WordRule wordRule = new WordRule(new ModelicaKeywordDetector());
		for (int i = 0; i < reservedwords.length; i++)
		      wordRule.addWord(reservedwords[i], keywordToken);
		for (int i = 0; i < types.length; i++)
		      wordRule.addWord(types[i], typesToken);
		for (int i = 0; i < constants.length; i++)
		      wordRule.addWord(constants[i], keywordToken);
		rules[0]=wordRule;
		
//		 this will match single line Modelica comments given between quotes "this is a comment"
		String begging = "this if \\u0022" ;
		System.out.println(begging);
		rules[1] = (new SingleLineRule( "\"", "\"", commentToken,'\\'));
		// this will match sinle line Modelica comments precceded by double backslash
		rules[2] = (new EndOfLineRule("//", commentToken,'\\'));
		//Add rules for multi-line comments
		rules[3] = (new MultiLineRule("/*", "*/", commentToken, (char) 0, true)); 
		
		setRules(rules);
	}

}
