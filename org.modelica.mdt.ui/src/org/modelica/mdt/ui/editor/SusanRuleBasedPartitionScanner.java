/**
 * This Class is for the Susan-Editor-Extension;
 * stantard implementation of the RuleBasedPartitionScanner Class
 * provides a Scanner to scan for Comments and Templates
 * 
 */

package org.modelica.mdt.ui.editor;

import java.util.ArrayList;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class SusanRuleBasedPartitionScanner extends RuleBasedPartitionScanner
{
	public final static String SUSAN_SINGLELINE_COMMENT_ID = "__susan_singleline_comment";
	public final static String SUSAN_MULTILINE_COMMENT_ID = "__susan_multiline_comment";
	public final static String SUSAN_TARGET_CODE_ID = "__susan_target_code";
	public final static String SUSAN_PARTITIONING = "__susan_partitioning";
	public final static String SUSAN_KEYWORDS_ID = "__susan_keywords";
	public static final String[] PARTITION_TYPES = { SUSAN_SINGLELINE_COMMENT_ID, SUSAN_MULTILINE_COMMENT_ID, SUSAN_TARGET_CODE_ID, SUSAN_KEYWORDS_ID };

	public final static String[] SUSAN_KEYWORDS = { "import", "package" };
	
	/**
	 * A RuleBasedPartitionScanner, scanning for for Comments and Templates in Susan-Files
	 */
	public SusanRuleBasedPartitionScanner()
	{

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

		IToken targetCodeToken = new Token(SUSAN_TARGET_CODE_ID);
		IToken comment = new Token(SUSAN_MULTILINE_COMMENT_ID);
		IPredicateRule[] rules = new IPredicateRule[5];
		rules[0] = new MultiLineRule("/*", "*/", comment);
		rules[1] = new MultiLineRule("\"", "\"", comment);
		rules[2] = new ExtendedMultiLineRule("<<", EndSequenceList1, targetCodeToken, '\0', false);
		rules[3] = new ExtendedMultiLineRule("%>", EndSequenceList2, targetCodeToken, '\0', false);
		rules[4] = new ExtendedMultiLineRule("'", EndSequenceList3, targetCodeToken, '\0', false);
		setPredicateRules(rules);

	}

}
