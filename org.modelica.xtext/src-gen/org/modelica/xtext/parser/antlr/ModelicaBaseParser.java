/*
* generated by Xtext
*/
package org.modelica.xtext.parser.antlr;

import org.antlr.runtime.ANTLRInputStream;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

import com.google.inject.Inject;

import org.modelica.xtext.services.ModelicaBaseGrammarAccess;

public class ModelicaBaseParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject 
    protected ITokenDefProvider antlrTokenDefProvider;
	
	@Inject
	private ModelicaBaseGrammarAccess grammarAccess;
	
	@Override
	protected IParseResult parse(String ruleName, ANTLRInputStream in) {
		org.modelica.xtext.parser.antlr.internal.InternalModelicaBaseLexer lexer = new org.modelica.xtext.parser.antlr.internal.InternalModelicaBaseLexer(in);
		XtextTokenStream stream = new XtextTokenStream(lexer, antlrTokenDefProvider);
		stream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
		org.modelica.xtext.parser.antlr.internal.InternalModelicaBaseParser parser = new org.modelica.xtext.parser.antlr.internal.InternalModelicaBaseParser(
				stream, getElementFactory(), grammarAccess);
		parser.setTokenTypeMap(antlrTokenDefProvider.getTokenDefMap());
		try {
			if(ruleName != null)
				return parser.parse(ruleName);
			return parser.parse();
		} catch (Exception re) {
			throw new ParseException(re.getMessage(),re);
		}
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "StorageUnit";
	}
	
	public ModelicaBaseGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ModelicaBaseGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
