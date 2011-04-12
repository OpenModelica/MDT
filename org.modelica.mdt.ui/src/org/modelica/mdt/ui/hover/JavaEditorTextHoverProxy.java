/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.modelica.mdt.ui.hover;

import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProviderExtension2;

import org.eclipse.ui.IEditorPart;


/**
 * Proxy for JavaEditorTextHovers.
 *
 * @since 2.1
 */
public class JavaEditorTextHoverProxy extends AbstractModelicaEditorTextHover {

	private IModelicaEditorTextHover fHover;

	public JavaEditorTextHoverProxy(IEditorPart editor) {
		setEditor(editor);
	}

	/*
	 * @see IJavaEditorTextHover#setEditor(IEditorPart)
	 */
	public void setEditor(IEditorPart editor) {
		super.setEditor(editor);

		if (fHover != null)
			fHover.setEditor(getEditor());
	}

	public boolean isEnabled() {
		return true;
	}

	/*
	 * @see ITextHover#getHoverRegion(ITextViewer, int)
	 */
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		if (ensureHoverCreated())
			return fHover.getHoverRegion(textViewer, offset);

		return null;
	}

	/*
	 * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
	 */
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		if (ensureHoverCreated())
			return fHover.getHoverInfo(textViewer, hoverRegion);

		return null;
	}

	/*
	 * @see org.eclipse.jface.text.ITextHoverExtension2#getHoverInfo2(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion)
	 * @since 3.4
	 */
	public Object getHoverInfo2(ITextViewer textViewer, IRegion hoverRegion) {
		if (ensureHoverCreated()) {
			if (fHover instanceof ITextHoverExtension2)
				return ((ITextHoverExtension2) fHover).getHoverInfo2(textViewer, hoverRegion);
			else
				return fHover.getHoverInfo(textViewer, hoverRegion);
		}

		return null;
	}

	private boolean ensureHoverCreated() {
		if (!isEnabled())
			return false;
		return isCreated() || createHover();
	}

	private boolean isCreated() {
		return fHover != null;
	}

	private boolean createHover() {
		fHover= new ProblemHover();
		if (fHover != null)
			fHover.setEditor(getEditor());
		return isCreated();
	}

	/*
	 * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
	 * @since 3.0
	 */
	public IInformationControlCreator getHoverControlCreator() {
		if (ensureHoverCreated() && (fHover instanceof ITextHoverExtension))
			return ((ITextHoverExtension)fHover).getHoverControlCreator();

		return null;
	}

	/*
	 * @see org.eclipse.jface.text.information.IInformationProviderExtension2#getInformationPresenterControlCreator()
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		if (ensureHoverCreated()) {
			if (fHover instanceof IInformationProviderExtension2) // this is wrong, but left here for backwards compatibility
				return ((IInformationProviderExtension2) fHover).getInformationPresenterControlCreator();
		}

		return null;
	}
}
