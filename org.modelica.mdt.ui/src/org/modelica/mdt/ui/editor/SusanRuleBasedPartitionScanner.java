/**
 * This Class is for the Susan-Editor-Extension;
 * stantard implementation of the RuleBasedPartitionScanner Class
 * provides a Scanner to scan for Comments and Templates
 * 
 */

package org.modelica.mdt.ui.editor;

import java.util.ArrayList;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class SusanRuleBasedPartitionScanner extends RuleBasedPartitionScanner
{
	public final static String SUSAN_SINGLELINE_COMMENT_ID = "__susan_singleline_comment";
	public final static String SUSAN_MULTILINE_STRING_ID = "__susan_multiline_string";
	public final static String SUSAN_MULTILINE_COMMENT_ID = "__susan_multiline_comment";
	public final static String SUSAN_TARGET_CODE_ID1 = "__susan_target_code1";
	public final static String SUSAN_TARGET_CODE_ID2 = "__susan_target_code2";
	public final static String SUSAN_TARGET_CODE_ID3 = "__susan_target_code3";
	public final static String SUSAN_PARTITIONING = "__susan_partitioning";
	public final static String SUSAN_KEYWORDS_ID = "__susan_keywords";
	public static final String[] PARTITION_TYPES = { SUSAN_SINGLELINE_COMMENT_ID, SUSAN_MULTILINE_COMMENT_ID, SUSAN_MULTILINE_STRING_ID, SUSAN_TARGET_CODE_ID1, SUSAN_TARGET_CODE_ID2, SUSAN_TARGET_CODE_ID3, SUSAN_KEYWORDS_ID };
	
	/**
	 * A RuleBasedPartitionScanner, scanning for for Comments and Templates in Susan-Files
	 * 
	 * provides the rules to scan for comments, strings and templates
	 */
	public SusanRuleBasedPartitionScanner()
	{
		
		IToken singleLineCommentToken = new Token(new TextAttribute(SusanEditorColors.SINGLELINE_COMMENT_COLOR));
		IRule singleLineCommentRule = new EndOfLineRule("//", singleLineCommentToken);

		ArrayList<String> EndSequenceList1 = new ArrayList<String>();
		EndSequenceList1.add(">>");
		EndSequenceList1.add("<%");	

		ArrayList<String> EndSequenceList2 = new ArrayList<String>();
		EndSequenceList2.add(">>");
		EndSequenceList2.add("<%");
		EndSequenceList2.add("'");

		ArrayList<String> EndSequenceList3 = new ArrayList<String>();
		EndSequenceList3.add("'");
		EndSequenceList3.add("<%");

		ArrayList<String> commentList = new ArrayList<String>();
		commentList.add("*/");
		IToken targetCodeToken1 = new Token(SUSAN_TARGET_CODE_ID1);
		IToken targetCodeToken2 = new Token(SUSAN_TARGET_CODE_ID2);
		IToken targetCodeToken3 = new Token(SUSAN_TARGET_CODE_ID3);
		IToken comment = new Token(SUSAN_MULTILINE_COMMENT_ID);
		IToken mlString = new Token(SUSAN_MULTILINE_STRING_ID);
		IPredicateRule[] rules = new IPredicateRule[6];
		rules[0] = new ExtendedMultiLineRule("<<", EndSequenceList1, targetCodeToken1, '\0', false);
		rules[1] = new MultiLineRule("\"", "\"", mlString);
		rules[2] = new ExtendedMultiLineRule("/*", commentList , comment, '\0',false);
		rules[3] = new ExtendedMultiLineRule("%>", EndSequenceList2, targetCodeToken2, '\0', false);
		rules[4] = new ExtendedMultiLineRule("'", EndSequenceList3, targetCodeToken3, '\0', false);
		rules[5] = (IPredicateRule) singleLineCommentRule;
		setPredicateRules(rules);

	}

}
