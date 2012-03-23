
package org.modelica.mdt.ui.hover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.actions.IModelicaEditorActionDefinitionIds;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.text.ModelicaWordFinder;
import org.modelica.mdt.ui.util.AnalyzeRegion;
import org.osgi.framework.Bundle;

/**
 * Abstract class for providing hover information for Modelica elements.
 *
 * @since 2.1
 */
public abstract class AbstractModelicaEditorTextHover implements IModelicaEditorTextHover, ITextHoverExtension {

	/**
	 * The style sheet (css).
	 * @since 3.2
	 */
	private static String fgStyleSheet;
	private ModelicaEditor fEditor;
	
	private IBindingService fBindingService;
	{
		fBindingService= (IBindingService)PlatformUI.getWorkbench().getAdapter(IBindingService.class);
	}

	/*
	 * @see IModelicaEditorTextHover#setEditor(IEditorPart)
	 */
	@Override
	public void setEditor(IEditorPart editor) {
		fEditor= (ModelicaEditor)editor;
	}

	protected IEditorPart getEditor() {
		return fEditor;
	}

	/*
	 * @see ITextHover#getHoverRegion(ITextViewer, int)
	 */
	@Override
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		return ModelicaWordFinder.findWord(textViewer.getDocument(), offset);
	}

	/**
	 * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
	 */
		@Override
		public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
			IDocument document = textViewer.getDocument();
			String hoverInfo = new AnalyzeRegion().performAnalysis(hoverRegion, document, fEditor);
			
			return hoverInfo;
		}

	/**
	 * Provides hover information for the given Modelica elements.
	 *
	 * @param javaElements the Modelica elements for which to provide hover information
	 * @return the hover information string
	 * @since 2.1
	 */
	protected String getHoverInfo(IModelicaElement[] javaElements) {
		return null;
	}

	/*
	 * @see ITextHoverExtension#getHoverControlCreator()
	 * @since 3.0
	 */
	@Override
	public IInformationControlCreator getHoverControlCreator() {
		return new IInformationControlCreator() {
			@Override
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent, SWT.NONE, new HTMLTextPresenter(true), getTooltipAffordanceString());
			}
		};
	}

	/**
	 * Returns the tool tip affordance string.
	 *
	 * @return the affordance string or <code>null</code> if disabled or no key binding is defined
	 * @since 3.0
	 */
	protected String getTooltipAffordanceString() {
		if (fBindingService == null) {
			return null;
		}

		String keySequence= fBindingService.getBestActiveBindingFormattedFor(IModelicaEditorActionDefinitionIds.SHOW_MODELICADOC);
		if (keySequence == null) {
			return null;
		}
		
		return Messages.format(ModelicaHoverMessages.ModelicaTextHover_makeStickyHint, keySequence == null ? "" : keySequence); //$NON-NLS-1$
	}

	/**
	 * Returns the style sheet.
	 *
	 * @since 3.2
	 */
	protected static String getStyleSheet() {
		if (fgStyleSheet == null) {
			Bundle bundle= Platform.getBundle(UIPlugin.PLUGIN_ID);
			URL styleSheetURL= bundle.getEntry("/ModelicaDocumentationHoverStyleSheet.css"); //$NON-NLS-1$
			if (styleSheetURL != null) {
				try {
					styleSheetURL= FileLocator.toFileURL(styleSheetURL);
					BufferedReader reader= new BufferedReader(new InputStreamReader(styleSheetURL.openStream()));
					StringBuffer buffer= new StringBuffer(200);
					String line= reader.readLine();
					while (line != null) {
						buffer.append(line);
						buffer.append('\n');
						line= reader.readLine();
					}
					fgStyleSheet= buffer.toString();
				}
				catch (IOException e) {
					e.printStackTrace();
					fgStyleSheet= ""; //$NON-NLS-1$
				}
			}
		}
		return fgStyleSheet;
	}

	/*
	 * @see org.eclipse.jface.text.ITextHoverExtension2#getInformationPresenterControlCreator()
	 * @since 3.4
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		return new IInformationControlCreator() {
			@Override
			public IInformationControl createInformationControl(Shell shell) {
				return new DefaultInformationControl(shell, true);
			}
		};
	}
}
