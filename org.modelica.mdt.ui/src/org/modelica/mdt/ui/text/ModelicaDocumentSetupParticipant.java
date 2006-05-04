/*******************************************************************************
 * 
 *******************************************************************************/

package org.modelica.mdt.ui.text;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.modelica.mdt.ui.UIPlugin;


public class ModelicaDocumentSetupParticipant implements IDocumentSetupParticipant
{

	public ModelicaDocumentSetupParticipant()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
	 */
	public void setup(IDocument document) {
		ModelicaTextTools tools= UIPlugin.getDefault().getModelicaTextTools();
		tools.setupModelicaDocumentPartitioner(document, IModelicaPartitions.MODELICA_PARTITIONING);
	}

}
