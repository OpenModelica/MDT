/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.antlr.IAntlrParser;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.eclipse.xtext.parsetree.NodeAdapter;
import org.eclipse.xtext.parsetree.NodeUtil;
import org.eclipse.xtext.parsetree.SyntaxError;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.contentassist.XtextContentAssistProcessor;
import org.eclipse.xtext.util.StringInputStream;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.Activator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.editingdomain.UpdateXtextResourceTextCommand;

import com.google.inject.Inject;
import com.google.inject.Injector;

// TODO: Auto-generated Javadoc
/**
 * Wraps a (partial) Xtext parser in a GMF {@link IParser}.
 * 
 * @author koehnlein
 */
public class NOT_NEEDED_AntlrParserWrapper implements IParser {

	/** The xtext content assist processor. */
	@Inject
	private XtextContentAssistProcessor xtextContentAssistProcessor;

	/** The xtext parser. */
	@Inject
	private IAntlrParser xtextParser;

	/** The parser rule name. */
	private final String parserRuleName;

	/** The original parser. */
	private final IParser originalParser;

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param parserRuleName the parser rule name
	 * @param originalParser the original parser
	 * @param xtextInjector the xtext injector
	 */
	public NOT_NEEDED_AntlrParserWrapper(String parserRuleName, IParser originalParser, Injector xtextInjector) {
		this.parserRuleName = parserRuleName;
		this.originalParser = originalParser;
		xtextInjector.injectMembers(this);
	}

	/**
	 * Gets the completion processor.
	 *
	 * @param element the element
	 * @return the completion processor
	 */
	public IContentAssistProcessor getCompletionProcessor(IAdaptable element) {
		// TODO does not work. need to fake an XtextDocument
		return xtextContentAssistProcessor;
	}

	/**
	 * Gets the edits the string.
	 *
	 * @param element the element
	 * @param flags the flags
	 * @return the edits the string
	 */
	public String getEditString(IAdaptable element, int flags) {
		EObject semanticElement = (EObject) element.getAdapter(EObject.class);
		if (semanticElement != null) {
			NodeAdapter nodeAdapter = NodeUtil.getNodeAdapter(semanticElement);
			if (nodeAdapter != null) {
				return nodeAdapter.getParserNode().serialize().trim();
			}
		}
		return "invalid";
	}

	/**
	 * Gets the parses the command.
	 *
	 * @param element the element
	 * @param newString the new string
	 * @param flags the flags
	 * @return the parses the command
	 */
	public ICommand getParseCommand(IAdaptable element, final String newString, int flags) {
		EObject semanticElement = (EObject) element.getAdapter(EObject.class);
		if (semanticElement != null) {
			IParseResult parseResult = xtextParser.parse(parserRuleName, new StringInputStream(newString));
			if (isValidParseResult(parseResult, semanticElement)) {
				NodeAdapter nodeAdapter = NodeUtil.getNodeAdapter(semanticElement);
				if (nodeAdapter != null) {
					final CompositeNode parserNode = nodeAdapter.getParserNode();
					final XtextResource semanticResource = (XtextResource) semanticElement.eResource();
					ICommand reparseCommand = UpdateXtextResourceTextCommand.createUpdateCommand(semanticResource,
							parserNode.getOffset(), parserNode.getLength(), newString);
					return reparseCommand;
				}
			}
		}
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Gets the prints the string.
	 *
	 * @param element the element
	 * @param flags the flags
	 * @return the prints the string
	 */
	public String getPrintString(IAdaptable element, int flags) {
		return originalParser.getPrintString(element, flags);
	}

	/**
	 * Checks if is affecting event.
	 *
	 * @param event the event
	 * @param flags the flags
	 * @return true, if is affecting event
	 */
	public boolean isAffectingEvent(Object event, int flags) {
		return false;
	}

	/**
	 * Checks if is valid edit string.
	 *
	 * @param element the element
	 * @param editString the edit string
	 * @return the i parser edit status
	 */
	public IParserEditStatus isValidEditString(IAdaptable element, String editString) {
		try {
			IParseResult parseResult = xtextParser.parse(parserRuleName, new StringInputStream(editString));
			if (isValidParseResult(parseResult, (EObject) element.getAdapter(EObject.class))) {
				return new ParserEditStatus(IStatus.OK, Activator.PLUGIN_ID, IParserEditStatus.EDITABLE, "OK", null);
			} else {
				SyntaxError syntaxError = parseResult.getParseErrors().get(0);
				return new ParserEditStatus(IStatus.INFO, Activator.PLUGIN_ID, IParserEditStatus.UNEDITABLE,
						syntaxError.getMessage(), null);
			}
		} catch (Exception exc) {
			return new ParserEditStatus(IStatus.INFO, Activator.PLUGIN_ID, IParserEditStatus.UNEDITABLE, exc
					.getMessage(), exc);
		}
	}

	/**
	 * Checks if is valid parse result.
	 *
	 * @param parseResult the parse result
	 * @param semanticElement the semantic element
	 * @return true, if is valid parse result
	 */
	private boolean isValidParseResult(IParseResult parseResult, EObject semanticElement) {
		EObject rootASTElement = parseResult.getRootASTElement();
		return parseResult.getParseErrors().isEmpty() && rootASTElement != null && semanticElement != null
				&& semanticElement.eClass() == rootASTElement.eClass();
	}

}
