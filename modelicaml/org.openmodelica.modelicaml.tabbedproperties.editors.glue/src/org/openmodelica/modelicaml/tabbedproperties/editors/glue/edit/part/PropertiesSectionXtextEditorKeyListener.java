/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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