/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing;

import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.validation.IValidationIssueProcessor;

import com.google.inject.Injector;

// TODO: Auto-generated Javadoc
/**
 * The Class SourceViewerHandle.
 */
public class SourceViewerHandle {
	
	/** The issue processor. */
	private IValidationIssueProcessor issueProcessor;
	
	/** The viewer. */
	private final XtextSourceViewer viewer;
	
	/** The resource provider. */
	private final ISyntheticResourceProvider resourceProvider;
	
	/** The document. */
	private final XtextDocument document;
	
	/** The configuration. */
	private final XtextSourceViewerConfiguration configuration;

	/**
	 * Instantiates a new source viewer handle.
	 *
	 * @param document the document
	 * @param viewer the viewer
	 * @param configuration the configuration
	 * @param resourceProvider the resource provider
	 * @param xtextInjector the xtext injector
	 */
	SourceViewerHandle(XtextDocument document, XtextSourceViewer viewer, XtextSourceViewerConfiguration configuration, ISyntheticResourceProvider resourceProvider, Injector xtextInjector) {
		this.document = document;
		this.viewer = viewer;
		this.configuration = configuration;
		this.resourceProvider = resourceProvider;
	}

	/**
	 * Sets the issue processor.
	 *
	 * @param issueProcessor the new issue processor
	 */
	public void setIssueProcessor(IValidationIssueProcessor issueProcessor) {
		this.issueProcessor = issueProcessor;
	}
	
	/**
	 * Gets the issue processor.
	 *
	 * @return IValidationIssueProcessor
	 */
	public IValidationIssueProcessor getIssueProcessor() {
		return issueProcessor;
	}
	
	/**
	 * Gets the viewer.
	 *
	 * @return XtextSourceViewer
	 */
	public XtextSourceViewer getViewer() {
		return viewer;
	}
	
	/**
	 * Gets the document.
	 *
	 * @return XtextDocument
	 */
	public XtextDocument getDocument() {
		return document;
	}
	
	/**
	 * Gets the configuration.
	 *
	 * @return XtextSourceViewerConfiguration
	 */
	public XtextSourceViewerConfiguration getConfiguration() {
		return configuration;
	}
	
	/**
	 * Creates the partial editor.
	 *
	 * @param prefix the prefix
	 * @param editablePart the editable part
	 * @param suffix the suffix
	 * @return PartialModelEditor
	 */
	public PartialModelEditor createPartialEditor(String prefix, String editablePart, String suffix) {
		PartialModelEditor result = new PartialModelEditor(viewer, resourceProvider, false);
		result.setModel(getDocument(), prefix, editablePart, suffix);
		return result;
	}
	
	/**
	 * Creates the partial editor.
	 *
	 * @return PartialModelEditor
	 */
	public PartialModelEditor createPartialEditor() {
		return createPartialEditor("", "", "");
	}
}