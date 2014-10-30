/**
 * This Class is for the Susan-Editor-Extension;
 * standart implemention of the TextEditor Class
 * contains SusanEditor(), getAdapter(), dispose()
 * 
 */
package org.modelica.mdt.ui.editor;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class SusanEditor extends TextEditor
{
	private SusanContentOutlinePage fOutlinePage;
	
	/** 
	 * Creates a new SusanEditor and is setting the DocumentProvider
	 */
	public SusanEditor()
	{
		super();
		setSourceViewerConfiguration(new SusanSourceViewerConfig());
		setDocumentProvider(new SusanDocumentProvider());
	}

	
	
	@Override
	protected void editorSaved() {
		super.editorSaved();
		
		if (fOutlinePage != null)
			fOutlinePage.update();
	}
	
	@Override
	protected void updatePropertyDependentActions() {
		super.updatePropertyDependentActions();
//		if (fOutlinePage != null)
//			fOutlinePage.update();
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
	}

	/** 
	 * Creates the Outline for the SusanEditor
	 * <br><br>
	 * input:  <code>IContentOutlinePage</code><br>
	 * return: <code>SusanContentOutlinePage</code>
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class required)
	{

		if (IContentOutlinePage.class.equals(required)) {
			if (fOutlinePage == null) {
				fOutlinePage = new SusanContentOutlinePage(getDocumentProvider(), this);
				if (getEditorInput() != null)
					fOutlinePage.setInput(getEditorInput());
			}
			return fOutlinePage;
		}

		return super.getAdapter(required);
	}

}
