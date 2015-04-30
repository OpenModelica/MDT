/**
 * This Class is for the Susan-Editor-Extension;
 * stantard implementation of the RuleBasedScanner Class
 * provides a Scanner to scan for SingleLine-Objects[ Comments, Keywords ]
 * 
 */
package org.modelica.mdt.ui.editor;

import java.util.HashMap;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class SusanRuleBasedScanner extends RuleBasedScanner
{
	public final static HashMap<String[], IToken> ObjectMap = new HashMap<String[], IToken>();

	public final static String[] SUSAN_KEYWORDS = { "import", "package", "annotation" };
	public final static String[] TEMPLATE_KEYWORDS = { "template", "end" };
	public final static String[] CASE_KEYWORDS = { "case", "if", "then", "else", "as", "match" };
	public final static String[] VAR_KEYWORDS = { "let", "hasindex", "Text" };

	/**
	 * A Scanner for SingleLine-Objects[ Comments, a List of Keywords ]
	 * 
	 */
	
	private class SusanWordRule extends WordRule
	{

		private StringBuffer fBuffer= new StringBuffer();
		private int last = -1;
		
		public SusanWordRule(IWordDetector detector)
		{
			super(detector);
		}
		
		/**
		 * evaluates from the position in the text if a token is starting at this position or not
		 */
		@Override
		public IToken evaluate(ICharacterScanner scanner) {
			
			if (last == -1)
			{
				scanner.unread();
				last = scanner.read();
			}
			
			int c= scanner.read();
			
			// return if here, if there is a keyword inside a word  so that the keyword is not detexted
			//e.g. superString -> the word String would still be detected 
			if ( ( ( (last >= 'a') && (last <= 'z') ) || ( (last >= 'A') && (last <= 'Z') ) ) )
			{
				last = c;
				scanner.unread();
				return Token.UNDEFINED;
			}
			//scan for the word
			if (c != ICharacterScanner.EOF && fDetector.isWordStart((char) c)) {
				if (fColumn == UNDEFINED || (fColumn == scanner.getColumn() - 1)) {
					
					last = c;
					
					fBuffer.setLength(0);
					do {
						fBuffer.append((char) c);
						c= scanner.read();
					} while (c != ICharacterScanner.EOF && fDetector.isWordPart((char) c));
					scanner.unread();

					String buffer= fBuffer.toString();
					
					IToken token= (IToken)fWords.get(buffer);

					if (token != null)
						return token;

					if (fDefaultToken.isUndefined())
						unreadBuffer(scanner);

					return fDefaultToken;
				}
			}
			
			if (c == ICharacterScanner.EOF)
				last = -1;

			scanner.unread();
			
			return Token.UNDEFINED;
		}
		
		@Override
		protected void unreadBuffer(ICharacterScanner scanner) {
			for (int i= fBuffer.length() - 1; i >= 0; i--)
				scanner.unread();
		}
		
	}
	
	
	public SusanRuleBasedScanner()
	{

		IToken singleLineCommentToken = new Token(new TextAttribute(SusanEditorColors.SINGLELINE_COMMENT_COLOR));
		IToken keywordToken = new Token(SusanEditorColors.KEYWORD_ATTRIBUTE);
		IToken templateToken = new Token(SusanEditorColors.TEMPLATE_ATTRIBUTE);
		IToken caseToken = new Token(SusanEditorColors.BUILTIN_ATTRIBUTE);
		IToken varToken = new Token(SusanEditorColors.VAR_ATTRIBUTE);

		ObjectMap.put(SUSAN_KEYWORDS, keywordToken);
		ObjectMap.put(TEMPLATE_KEYWORDS, templateToken);
		ObjectMap.put(CASE_KEYWORDS, caseToken);

		IRule singleLineCommentRule = new EndOfLineRule("//", singleLineCommentToken);

		SusanKeywords detector = new SusanKeywords();

		IRule importRule = new SusanWordRule(detector); // Add Keywords

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

		IRule[] rules = new IRule[] { singleLineCommentRule, /* multiLineCommentRule, targetCodeRule, */ importRule /*, StringRule */ };
		setRules(rules);

	}

}
