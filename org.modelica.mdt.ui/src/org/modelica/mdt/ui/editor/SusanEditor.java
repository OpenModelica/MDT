package org.modelica.mdt.ui.editor;

import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class SusanEditor extends TextEditor
{
	private SusanContentOutlinePage fOutlinePage;

	public SusanEditor()
	{
		super();
		setSourceViewerConfiguration(new SusanSourceViewerConfig());
		setDocumentProvider(new SusanDocumentProvider());

	}

	@Override
	public void dispose()
	{
		super.dispose();
	}

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
