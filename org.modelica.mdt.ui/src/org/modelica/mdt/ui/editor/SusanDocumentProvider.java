package org.modelica.mdt.ui.editor;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class SusanDocumentProvider extends FileDocumentProvider
{

	protected IDocument createDocument(Object element) throws CoreException
	{

		// Call super for the case of FileEditorInput.
		IDocument doc = super.createDocument(element);

		// File outside of the workspace? That is, opened via 'File->Open File'.
		if (doc == null) {
			if (element instanceof IURIEditorInput) {
				IURIEditorInput ei = (IURIEditorInput) element;
				doc = createEmptyDocument();

				// Open the stream
				InputStream is = null;
				try {
					is = ei.getURI().toURL().openStream();
					setDocumentContent(doc, is, getEncoding(element));

				} catch (IOException ex) {
					// Activator.getDefault().log(IStatus.ERROR, ex);
				} finally {
					try {
						if (is != null) {
							is.close();
						}
					} catch (IOException x) {
						// Activator.getDefault().log(IStatus.ERROR, x);
					}
				}
			}
		}

		if (doc != null) {
			SusanRuleBasedPartitionScanner scanner = new SusanRuleBasedPartitionScanner();
			IDocumentPartitioner partitioner = new FastPartitioner(scanner, SusanRuleBasedPartitionScanner.PARTITION_TYPES);
			doc.setDocumentPartitioner(partitioner);
			partitioner.connect(doc);
		}

		return doc;
	}

	public boolean isModifiable(Object element)
	{
		boolean modifiable = super.isModifiable(element);
		if (!modifiable) {
			modifiable = (element instanceof IURIEditorInput);
		}
		return modifiable;
	}

}
