
package org.modelica.mdt.ui.hover;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension2;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.modelica.mdt.ui.text.ModelicaWordFinder;


public class ModelicaInformationProvider implements IInformationProvider, IInformationProviderExtension2 {

	class EditorWatcher implements IPartListener {

		/**
		 * @see IPartListener#partOpened(IWorkbenchPart)
		 */
		public void partOpened(IWorkbenchPart part) {
		}

		/**
		 * @see IPartListener#partDeactivated(IWorkbenchPart)
		 */
		public void partDeactivated(IWorkbenchPart part) {
		}

		/**
		 * @see IPartListener#partClosed(IWorkbenchPart)
		 */
		public void partClosed(IWorkbenchPart part) {
			if (part == fEditor) {
				fEditor.getSite().getWorkbenchWindow().getPartService().removePartListener(fPartListener);
				fPartListener= null;
			}
		}

		/**
		 * @see IPartListener#partActivated(IWorkbenchPart)
		 */
		public void partActivated(IWorkbenchPart part) {
			update();
		}

		public void partBroughtToTop(IWorkbenchPart part) {
			update();
		}
	}

	protected IEditorPart fEditor;
	protected IPartListener fPartListener;

	protected String fCurrentPerspective;
	protected IModelicaEditorTextHover fImplementation;

	/**
	 * The presentation control creator.
	 * 
	 * @since 3.2
	 */
	private IInformationControlCreator fPresenterControlCreator;
	


	public ModelicaInformationProvider(IEditorPart editor) {

		fEditor= editor;

		if (fEditor != null) {

			fPartListener= new EditorWatcher();
			IWorkbenchWindow window= fEditor.getSite().getWorkbenchWindow();
			window.getPartService().addPartListener(fPartListener);

			update();
		}
	}

	protected void update() {

		IWorkbenchWindow window= fEditor.getSite().getWorkbenchWindow();
		IWorkbenchPage page= window.getActivePage();
		if (page != null) {

			IPerspectiveDescriptor perspective= page.getPerspective();
			if (perspective != null)  {
				String perspectiveId= perspective.getId();

				if (fCurrentPerspective == null || fCurrentPerspective != perspectiveId) {
					fCurrentPerspective= perspectiveId;

					fImplementation= new ModelicaDocumentationHover();
					fImplementation.setEditor(fEditor);
				}
			}
		}
	}

	/*
	 * @see IInformationProvider#getSubject(ITextViewer, int)
	 */
	public IRegion getSubject(ITextViewer textViewer, int offset) {

		if (textViewer != null)
			return ModelicaWordFinder.findWord(textViewer.getDocument(), offset);

		return null;
	}

	/*
	 * @see IInformationProvider#getInformation(ITextViewer, IRegion)
	 */
	public String getInformation(ITextViewer textViewer, IRegion subject) {
		if (fImplementation != null) {
			String s= fImplementation.getHoverInfo(textViewer, subject);
			if (s != null && s.trim().length() > 0) {
				return s;
			}
		}

		return null;
	}

	/*
	 * @see IInformationProviderExtension2#getInformationPresenterControlCreator()
	 * @since 3.1
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		if (fPresenterControlCreator == null) {
			fPresenterControlCreator= new AbstractReusableInformationControlCreator() {

				/*
				 * @see org.modelica.mdt.ui.text.java.hover.AbstractReusableInformationControlCreator#doCreateInformationControl(org.eclipse.swt.widgets.Shell)
				 */
				public IInformationControl doCreateInformationControl(Shell parent) {
					int shellStyle= SWT.RESIZE | SWT.TOOL;
					int style= SWT.V_SCROLL | SWT.H_SCROLL;
					return new DefaultInformationControl(parent, shellStyle, style, new HTMLTextPresenter(false));
				}
			};
		}
		return fPresenterControlCreator;
	}
}
