/******************************************************************************
 * @author Adrian Pop
 * Copyright (c) 2006 Adrian Pop, adrpo@ida.liu.se
 * All rights reserved. 
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for NON-COMMERCIAL purposes and without fee is hereby 
 * granted, provided that this copyright notice appear in all copies and 
 * that both the copyright notice and this permission notice and warranty
 * disclaimer appear in supporting documentation, and that the name of
 * The Author not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission.
 * 
 * COMMERCIAL use, copy, modification and distribution 
 * is NOT permitted without prior agreement with Adrian Pop.
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 *****************************************************************************/

package org.modelica.mdt.ui.text;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;

/**
 * Modelica element hyperlink.
 *
 * @since 0.6.8
 */
public class ModelicaElementHyperlink implements IHyperlink {

	private final IRegion fRegion;
	private final IAction fOpenAction;


	/**
	 * Creates a new Modelica element hyperlink.
	 */
	public ModelicaElementHyperlink(IRegion region, IAction openAction) {
		Assert.isNotNull(openAction);
		Assert.isNotNull(region);

		fRegion= region;
		fOpenAction= openAction;
	}

	/*
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkRegion()
	 * @since 0.6.8
	 */
	public IRegion getHyperlinkRegion() {
		return fRegion;
	}

	/*
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#open()
	 * @since 0.6.8
	 */
	public void open() 
	{		
		fOpenAction.run();
	}

	/*
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getTypeLabel()
	 * @since 0.6.8
	 */
	public String getTypeLabel() {
		return null;
	}

	/*
	 * @see org.eclipse.jface.text.hyperlink.IHyperlink#getHyperlinkText()
	 * @since 0.6.8
	 */
	public String getHyperlinkText() {
		return null;
	}
}
