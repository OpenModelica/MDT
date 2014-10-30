/**
 * This Class is for the Susan-Editor-Extension;
 * stantard implementation of the RuleBasedScanner Class
 * provides a Scanner to scan for SingleLine-Objects[ Comments, Keywords ]
 * 
 */
package org.modelica.mdt.ui.editor;

import java.util.HashMap;
import java.util.LinkedHashSet;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class SusanRuleBasedScanner extends RuleBasedScanner
{
	public final static HashMap<String[], IToken> ObjectMap = new HashMap<String[], IToken>();

	public final static String[] SUSAN_KEYWORDS = { "import", "package", "String" };
	public final static String[] TEMPLATE_KEYWORDS = { "template", "end" };
	public final static String[] CASE_KEYWORDS = { "case", "if", "then", "else", "as", "match" };
	public final static String[] VAR_KEYWORDS = { "let", "hasindex" };

	/**
	 * A Scanner for SingleLine-Objects[ Comments, a List of Keywords ]
	 * 
	 */
	public SusanRuleBasedScanner()
	{

		IToken singleLineCommentToken = new Token(new TextAttribute(SusanEditorColors.SINGLELINE_COMMENT_COLOR));
		IToken multiLineCommentToken = new Token(new TextAttribute(SusanEditorColors.MULTILINE_COMMENT_COLOR));
		IToken targetCodeToken = new Token(new TextAttribute(SusanEditorColors.FUNCTION_COLOR));
		IToken keywordToken = new Token(SusanEditorColors.KEYWORD_ATTRIBUTE);
		IToken templateToken = new Token(SusanEditorColors.TEMPLATE_ATTRIBUTE);
		IToken StringToken = new Token(SusanEditorColors.STRING_ATTRIBUTE);
		IToken caseToken = new Token(SusanEditorColors.BUILTIN_ATTRIBUTE);
		IToken varToken = new Token(SusanEditorColors.VAR_ATTRIBUTE);

		ObjectMap.put(SUSAN_KEYWORDS, keywordToken);
		ObjectMap.put(TEMPLATE_KEYWORDS, templateToken);
		ObjectMap.put(CASE_KEYWORDS, caseToken);

		IRule singleLineCommentRule = new EndOfLineRule("//", singleLineCommentToken);
		IRule multiLineCommentRule = new MultiLineRule("/*", "*/", multiLineCommentToken);
		IRule targetCodeRule = new MultiLineRule("<<", ">>", targetCodeToken);
		IRule StringRule = new MultiLineRule("'", "'", StringToken);

		LinkedHashSet<String> keyWords = new LinkedHashSet<String>();
		for (int i = 0; i < SUSAN_KEYWORDS.length; i++) {
			keyWords.add(SUSAN_KEYWORDS[i]);
		}

		SusanKeywords detector = new SusanKeywords();

		IRule importRule = new WordRule(detector); // Add Keywords

		for (int i = 0; i < SUSAN_KEYWORDS.length; i++) {
			((WordRule) importRule).addWord(SUSAN_KEYWORDS[i], keywordToken);
		}

		for (int i = 0; i < TEMPLATE_KEYWORDS.length; i++) // add other keywords
		{
			((WordRule) importRule).addWord(TEMPLATE_KEYWORDS[i], templateToken);
		}

		for (int i = 0; i < CASE_KEYWORDS.length; i++) // add other keywords
		{
			((WordRule) importRule).addWord(CASE_KEYWORDS[i], caseToken);
		}

		for (int i = 0; i < VAR_KEYWORDS.length; i++) // add other keywords
		{
			((WordRule) importRule).addWord(VAR_KEYWORDS[i], varToken);
		}
		// add rules

		IRule[] rules = new IRule[] { singleLineCommentRule, multiLineCommentRule, targetCodeRule, importRule, StringRule };
		setRules(rules);

	}

}
