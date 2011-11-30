/*******************************************************************************
 * Copyright (c) 2006 Adrian Pop, adrpo@ida.liu.se
 *******************************************************************************/
package org.modelica.mdt.ui.text;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

import org.modelica.mdt.ui.text.IModelicaPartitions;


/**
 * @author Adrian Pop, adrpo@ida.liu.se
 * This scanner recognizes:
 *  - Modelica C multiline comments
 *  - Modelica single line comments 
 *  - Modelica strings
 *  - Modelica code
 */
public class ModelicaPartitionScanner extends RuleBasedPartitionScanner implements IModelicaPartitions {

	/**
	 * Detector for empty comments.
	 */
	static class EmptyCommentDetector implements IWordDetector {

		/*
		 * @see IWordDetector#isWordStart
		 */
		public boolean isWordStart(char c) {
			return (c == '/');
		}

		/*
		 * @see IWordDetector#isWordPart
		 */
		public boolean isWordPart(char c) {
			return (c == '*' || c == '/');
		}
	}


	/**
	 * Word rule for empty comments.
	 */
	static class EmptyCommentRule extends WordRule implements IPredicateRule {

		private IToken fSuccessToken;
		/**
		 * Constructor for EmptyCommentRule.
		 * @param successToken
		 */
		public EmptyCommentRule(IToken successToken) {
			super(new EmptyCommentDetector());
			fSuccessToken= successToken;
			addWord("/**/", fSuccessToken); //$NON-NLS-1$
		}

		/*
		 * @see IPredicateRule#evaluate(ICharacterScanner, boolean)
		 */
		public IToken evaluate(ICharacterScanner scanner, boolean resume) {
			return evaluate(scanner);
		}

		/*
		 * @see IPredicateRule#getSuccessToken()
		 */
		public IToken getSuccessToken() {
			return fSuccessToken;
		}
	}



	/**
	 * Creates the partitioner and sets up the appropriate rules.
	 */
	public ModelicaPartitionScanner() {
		IToken modelicaString = new Token(MODELICA_STRING);		
		IToken modelicaMultiLineComment= new Token(MODELICA_MULTI_LINE_COMMENT);
		IToken modelicaSingleLineComment= new Token(MODELICA_SINGLE_LINE_COMMENT);		

		List<IPredicateRule> rules= new ArrayList<IPredicateRule>();

		// Add rule for single line comments.
		rules.add(new EndOfLineRule("//", modelicaSingleLineComment)); 

		// Add rule for strings.		
		rules.add(new MultiLineRule("\"", "\"", modelicaString, '\\', true)); 		

		
		// Add special case word rule.
		EmptyCommentRule wordRule= new EmptyCommentRule(modelicaMultiLineComment);
		rules.add(wordRule);
		rules.add(new MultiLineRule("/*", "*/", modelicaMultiLineComment, (char)0, true)); 
		
		IPredicateRule[] result = rules.toArray(new IPredicateRule[0]);

		setPredicateRules(result);
	}
}
