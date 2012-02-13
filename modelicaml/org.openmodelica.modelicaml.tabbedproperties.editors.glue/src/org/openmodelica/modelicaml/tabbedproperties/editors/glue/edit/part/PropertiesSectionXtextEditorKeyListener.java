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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part;

import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.VerifyEvent;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving propertiesSectionXtextEditorKey events.
 * The class that is interested in processing a propertiesSectionXtextEditorKey
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addPropertiesSectionXtextEditorKeyListener<code> method. When
 * the propertiesSectionXtextEditorKey event occurs, that object's appropriate
 * method is invoked.
 *
 * @author koehnlein
 */
public class PropertiesSectionXtextEditorKeyListener extends KeyAdapter implements VerifyKeyListener {

	/** The popup xtext editor helper. */
	private final PropertiesSectionXtextEditorHelper popupXtextEditorHelper;

	/** The content assistant. */
	private ContentAssistant contentAssistant;

	/** The is ignore next esc. */
	private boolean isIgnoreNextESC;

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param popupXtextEditorHelper the popup xtext editor helper
	 * @param contentAssistant the content assistant
	 */
	public PropertiesSectionXtextEditorKeyListener(PropertiesSectionXtextEditorHelper popupXtextEditorHelper, IContentAssistant contentAssistant) {
		this.popupXtextEditorHelper = popupXtextEditorHelper;
		this.contentAssistant = contentAssistant instanceof ContentAssistant ? (ContentAssistant) contentAssistant
				: null;
		isIgnoreNextESC = false;
	}

	/**
	 * Key pressed.
	 *
	 * @param e the e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.keyCode;
		if ((e.stateMask & SWT.CTRL) != 0 && ((keyCode == SWT.KEYPAD_CR) || (keyCode == SWT.CR))) {
//			this.popupXtextEditorHelper.closeEditor(false);
		}
		if (keyCode == SWT.ESC) {
			if (isIgnoreNextESC) {
				isIgnoreNextESC = false;
			} else {
//				this.popupXtextEditorHelper.closeEditor(false);
			}
		}
		if ((e.stateMask & SWT.CTRL) != 0 && (keyCode == ' ')) {
			this.contentAssistant.showPossibleCompletions() ;
			this.isIgnoreNextESC = true ;
		}
	}

	/**
	 * Verify key.
	 *
	 * @param e the e
	 */
	public void verifyKey(VerifyEvent e) {
		if (e.keyCode == SWT.ESC && isContentAssistActive()) {
			isIgnoreNextESC = true;
		}
		if ((e.stateMask & SWT.CTRL) != 0 && ((e.keyCode == SWT.KEYPAD_CR) || (e.keyCode == SWT.CR))) {
			e.doit = false;
		}
	}

	/**
	 * Checks if is content assist active.
	 *
	 * @return true, if is content assist active
	 */
	private boolean isContentAssistActive() {
		return contentAssistant != null && contentAssistant.hasProposalPopupFocus();
	}
	
}