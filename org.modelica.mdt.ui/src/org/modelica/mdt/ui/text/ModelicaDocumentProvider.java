
package org.modelica.mdt.ui.text;

import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.editors.text.ForwardingDocumentProvider;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;

import org.modelica.mdt.ui.text.ModelicaDocumentSetupParticipant;

public class ModelicaDocumentProvider extends TextFileDocumentProvider 
{

	public ModelicaDocumentProvider()
	{
		super();		
		IDocumentProvider provider= new 
			ForwardingDocumentProvider(
					IModelicaPartitions.MODELICA_PARTITIONING, 
					new ModelicaDocumentSetupParticipant(), 
					new TextFileDocumentProvider());
		setParentDocumentProvider(provider);
	}
	
	/*
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) 
		{
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new ModelicaPartitionScanner(),
					IModelicaPartitions.legalPartitionTypes);
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
	*/

}
