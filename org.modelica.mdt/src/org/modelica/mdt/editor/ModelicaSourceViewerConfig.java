/*
 * Created on Jan 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.modelica.mdt.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author Peter Bunus
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ModelicaSourceViewerConfig extends SourceViewerConfiguration {

	private ModelicaRuleScanner scanner;
	// default tag color is black
	private static Color DEFAULT_TAG_COLOR= new Color(Display.getCurrent(), new RGB(0, 0, 0));

	public ModelicaSourceViewerConfig() {

	}

	protected ModelicaRuleScanner getTagScanner() {
		if (scanner == null) {
			scanner = new ModelicaRuleScanner();
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
			DEFAULT_TAG_COLOR)));
		}
		return scanner;
	}

	/**
	 * Define reconciler for MyEditor
	 */
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getTagScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		return reconciler;
	}

}
