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
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.ui.texteditor.ITextEditor;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.ui.editor.EditorUtility;
import org.modelica.mdt.ui.editor.ModelicaEditor;

/**
 * Modelica element hyperlink detector.
 *
 * @since 0.6.8
 */
public class ModelicaElementHyperlinkDetector implements IHyperlinkDetector {

	private ITextEditor fTextEditor;

	/**
	 * Creates a new Modelica element hyperlink detector.
	 *
	 * @param editor the editor in which to detect the hyperlink
	 */
	public ModelicaElementHyperlinkDetector(ITextEditor editor) {
		Assert.isNotNull(editor);
		fTextEditor= editor;
	}

	/*
	 * @see org.eclipse.jface.text.hyperlink.IHyperlinkDetector#detectHyperlinks(org.eclipse.jface.text.ITextViewer, org.eclipse.jface.text.IRegion, boolean)
	 */
	public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks) {
		if (region == null || !(fTextEditor instanceof ModelicaEditor))
			return null;

		IAction openAction= fTextEditor.getAction("OpenAction"); //$NON-NLS-1$
		if (openAction == null)
			return null;

		int offset= region.getOffset();

		IModelicaElement input= EditorUtility.getEditorInputModelicaElement(fTextEditor);
		if (input == null)
			return null;

		try {
			IDocument document= fTextEditor.getDocumentProvider().getDocument(fTextEditor.getEditorInput());
			IRegion wordRegion = ModelicaWordFinder.findWord(document, offset);
			if (wordRegion == null)
				return null;
			return new IHyperlink[] {new ModelicaElementHyperlink(wordRegion, openAction)};
		} 
		catch (ClassCastException e) 
		{
			e.printStackTrace();
			return null;
		}

		//return null;
	}

}
