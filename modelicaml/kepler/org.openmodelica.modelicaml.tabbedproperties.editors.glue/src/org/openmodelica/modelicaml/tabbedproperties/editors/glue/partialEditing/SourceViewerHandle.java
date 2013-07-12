/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
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
//		PartialModelEditor result = new PartialModelEditor(viewer, resourceProvider, false);
		PartialModelEditor result = new PartialModelEditor(viewer, resourceProvider, true);
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