package org.openmodelica.modelicaml.editor.xtext.model.ui.autoedit;

import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;

public class ModelicaMLAutoEditStrategyProvider extends
		DefaultAutoEditStrategyProvider {

	@Override
	protected void configure(IEditStrategyAcceptor acceptor) {
		configureIndentationEditStrategy(acceptor);
		configureStringLiteral(acceptor);
		
		/*
		 * The auto-editing (e.g. automatic closing) of parenthesis, brackets and braces is 
		 * disabled because there are issues with editors in composites.
		 */
		
		//configureParenthesis(acceptor);
		//configureSquareBrackets(acceptor);
		//configureCurlyBracesBlock(acceptor);
		configureMultilineComments(acceptor);
		//configureCompoundBracesBlocks(acceptor);
	}
	
}
