/*
* generated by Xtext
*/
package org.openmodelica.modelicaml.editor.xtext.valuebinding.parser.antlr;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.services.ProviderGrammarAccess;

import com.google.inject.Inject;

public class ProviderParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private ProviderGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.openmodelica.modelicaml.editor.xtext.valuebinding.parser.antlr.internal.InternalProviderParser createParser(XtextTokenStream stream) {
		return new org.openmodelica.modelicaml.editor.xtext.valuebinding.parser.antlr.internal.InternalProviderParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "ProviderOperation";
	}
	
	public ProviderGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ProviderGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
