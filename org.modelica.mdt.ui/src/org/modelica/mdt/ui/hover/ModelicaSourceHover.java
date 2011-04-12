/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.modelica.mdt.ui.hover;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProviderExtension2;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.IWorkbenchPartOrientation;
import org.modelica.mdt.core.IModelicaElement;

/**
 * Provides source as hover info for Modelica elements.
 */
public class ModelicaSourceHover extends AbstractModelicaEditorTextHover 
	implements ITextHoverExtension, ITextHoverExtension2, IInformationProviderExtension2 {

	/*
	 * @see ModelicaElementHover
	 */
	protected String getHoverInfo(IModelicaElement[] result) {
		int nResults= result.length;

		if (nResults > 1)
			return null;

		IModelicaElement curr= result[0];
		return curr.getElementName();
	}


	/*
	 * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
	 * @since 3.0
	 */
	public IInformationControlCreator getHoverControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				IEditorPart editor= getEditor(); 
				int shellStyle= SWT.TOOL | SWT.NO_TRIM;
				if (editor instanceof IWorkbenchPartOrientation)
					shellStyle |= ((IWorkbenchPartOrientation)editor).getOrientation();
				return new SourceViewerInformationControl(parent, shellStyle, SWT.NONE, getTooltipAffordanceString());
			}
		};
	}

	/*
	 * @see IInformationProviderExtension2#getInformationPresenterControlCreator()
	 * @since 3.0
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				int style= SWT.V_SCROLL | SWT.H_SCROLL;
				int shellStyle= SWT.RESIZE | SWT.TOOL;
				IEditorPart editor= getEditor(); 
				if (editor instanceof IWorkbenchPartOrientation)
					shellStyle |= ((IWorkbenchPartOrientation)editor).getOrientation();
				return new SourceViewerInformationControl(parent, shellStyle, style);
			}
		};
	}


	public Object getHoverInfo2(ITextViewer textViewer, IRegion hoverRegion)
	{
		// TODO Auto-generated method stub
		return getHoverInfo(textViewer, hoverRegion);
	}
}
