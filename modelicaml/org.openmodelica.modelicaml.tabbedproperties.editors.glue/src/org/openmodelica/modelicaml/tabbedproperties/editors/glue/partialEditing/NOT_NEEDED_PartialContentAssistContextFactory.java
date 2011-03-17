/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.ui.editor.contentassist.antlr.ParserBasedContentAssistContextFactory;

import com.google.inject.Inject;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating NOT_NEEDED_PartialContentAssistContext objects.
 *
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class NOT_NEEDED_PartialContentAssistContextFactory extends ParserBasedContentAssistContextFactory {

	/**
	 * Sets the partial parser.
	 *
	 * @param partialContentAssistParser the new partial parser
	 */
	@Inject
	public void setPartialParser(IPartialContentAssistParser partialContentAssistParser) {
		super.setParser(partialContentAssistParser);
	}

	/**
	 * Initialize for.
	 *
	 * @param rule the rule
	 */
	public void initializeFor(AbstractRule rule) {
		((IPartialContentAssistParser) getParser()).initializeFor(rule);
	}
	
}
