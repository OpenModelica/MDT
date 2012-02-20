/*
* generated by Xtext
*/
package org.openmodelica.modelicaml.editor.xtext.model.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.openmodelica.modelicaml.editor.xtext.model.services.ModeleditorGrammarAccess;

public class ModeleditorParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private ModeleditorGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.openmodelica.modelicaml.editor.xtext.model.parser.antlr.internal.InternalModeleditorParser createParser(XtextTokenStream stream) {
		return new org.openmodelica.modelicaml.editor.xtext.model.parser.antlr.internal.InternalModeleditorParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "expression";
	}
	
	public ModeleditorGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ModeleditorGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
