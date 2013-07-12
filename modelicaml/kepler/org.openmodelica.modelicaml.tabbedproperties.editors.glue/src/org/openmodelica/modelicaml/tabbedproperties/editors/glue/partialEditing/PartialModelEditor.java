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

import java.util.Collections;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ISynchronizable;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.util.StringInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class PartialModelEditor.
 */
public class PartialModelEditor {

	/** The viewer. */
	private final SourceViewer viewer;
	
	/** The insert line breaks. */
	private final boolean insertLineBreaks;
	
	/** The resource provider. */
	private final ISyntheticResourceProvider resourceProvider;
	//private final Injector xtextInjector ;

	/**
	 * Instantiates a new partial model editor.
	 *
	 * @param viewer the viewer
	 * @param resourceProvider the resource provider
	 * @param insertLineBreaks the insert line breaks
	 */
	public PartialModelEditor(SourceViewer viewer, ISyntheticResourceProvider resourceProvider, boolean insertLineBreaks) {
		//this.xtextInjector = xtextInjector ;
		this.viewer = viewer;
		this.resourceProvider = resourceProvider;
		this.insertLineBreaks = insertLineBreaks;
	}
	
	/**
	 * Sets the model.
	 *
	 * @param document the document
	 * @param prefix the prefix
	 * @param editablePart the editable part
	 * @param suffix the suffix
	 */
	public void setModel(XtextDocument document, String prefix, String editablePart, String suffix) {
		if (insertLineBreaks) {
			String delimiter = document.getLegalLineDelimiters()[0];
			prefix = prefix + delimiter;
			suffix = delimiter + suffix;
		}
		String model = prefix + editablePart + suffix;
		document.set(model);
		XtextResource resource = createResource(model);
		document.setInput(resource);
		AnnotationModel annotationModel = new AnnotationModel();
		if (document instanceof ISynchronizable) {
			Object lock= ((ISynchronizable)document).getLockObject();
			if (lock == null) {
				lock= new Object();
				((ISynchronizable)document).setLockObject(lock);
			}
			((ISynchronizable) annotationModel).setLockObject(lock);
		}
		viewer.setDocument(document, annotationModel, prefix.length(), editablePart.length());
		viewer.getUndoManager().reset();
	}
	
	/**
	 * Creates the resource.
	 *
	 * @param content the content
	 * @return XtextResource
	 */
	public XtextResource createResource(String content) {
		XtextResource result = resourceProvider.createResource();
		try {
			result.load(new StringInputStream(content, result.getEncoding()), Collections.emptyMap());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * Update model.
	 *
	 * @param prefix the prefix
	 * @param editablePart the editable part
	 * @param suffix the suffix
	 */
	public void updateModel(String prefix, String editablePart, String suffix) {
		IDocument document= viewer.getDocument();
		if (insertLineBreaks) {
			String delimiter = document.getLegalLineDelimiters()[0];
			prefix = prefix + delimiter;
			suffix = delimiter + suffix;
		}
		String model = prefix + editablePart + suffix;
		viewer.setRedraw(false);
		viewer.getUndoManager().disconnect();
		document.set(model);
		viewer.setVisibleRegion(prefix.length(), editablePart.length());
		viewer.getUndoManager().connect(viewer);
		viewer.setRedraw(true);
	}
	
	/**
	 * Update prefix.
	 *
	 * @param prefix the prefix
	 */
	public void updatePrefix(String prefix) {
		try {
			IDocument document= viewer.getDocument();
			IRegion visibleRegion = viewer.getVisibleRegion();
			String editablePart = document.get(visibleRegion.getOffset(), visibleRegion.getLength());
			int suffixOffset = visibleRegion.getOffset() + visibleRegion.getLength();
			String suffix = "";
			if (document.getLength() - suffixOffset > 0) {
				suffix = document.get(suffixOffset, document.getLength() - suffixOffset);	
				if (insertLineBreaks) {
					String delimiter = document.getLegalLineDelimiters()[0];
					suffix = suffix.substring(delimiter.length());
				}
			}
			updateModel(prefix, editablePart, suffix);
		} catch(BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the editable part.
	 *
	 * @return String
	 */
	public String getEditablePart() {
		IDocument doc= viewer.getDocument();
		IRegion visible= viewer.getVisibleRegion();
		try {
			return doc.get(visible.getOffset(), visible.getLength());
		} catch (BadLocationException e) {
			return ""; //$NON-NLS-1$
		}
	}
	
	/**
	 * Gets the serialized model.
	 *
	 * @return String
	 */
	public String getSerializedModel() {
		return viewer.getDocument().get();
	}
	
}
