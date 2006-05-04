/*******************************************************************************
 *
 *******************************************************************************/
package org.modelica.mdt.ui.text;


import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

/**
 * Tools required to configure a Modelica text viewer.
 * The color manager and all scanner exist only one time, i.e.
 * the same instances are returned to all clients. Thus, clients
 * share those tools.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 */
public class ModelicaTextTools {

	/** The Modelica partitions scanner.
	 *  replace maybe with the FastModelicaPartitionScanner later 
	 */	
	private ModelicaPartitionScanner fPartitionScanner;


	/**
	 * Creates a new Modelica text tools collection.
	 */
	public ModelicaTextTools() 
	{
		/* replace maybe with the FastModelicaPartitionScanner later */
		fPartitionScanner= new ModelicaPartitionScanner();
	}

	/**
	 * Disposes all the individual tools of this tools collection.
	 */
	public void dispose() 
	{
		fPartitionScanner= null;
	}

	/**
	 * Returns a scanner which is configured to scan
	 * Modelica-specific partitions, which are multi-line comments,
	 * Modelicadoc comments, and regular Modelica source code.
	 *
	 * @return a Modelica partition scanner
	 */
	public IPartitionTokenScanner getPartitionScanner() {
		return fPartitionScanner;
	}

	/**
	 * Factory method for creating a Modelica-specific document partitioner
	 * using this object's partitions scanner. This method is a
	 * convenience method.
	 *
	 * @return a newly created Modelica document partitioner
	 */
	public IDocumentPartitioner createDocumentPartitioner() {
		return new FastPartitioner(getPartitionScanner(), IModelicaPartitions.legalPartitionTypes);
	}


	/**
	 * Sets up the Modelica document partitioner for the given document for the default partitioning.
	 *
	 * @param document the document to be set up
	 */
	public void setupModelicaDocumentPartitioner(IDocument document) {
		setupModelicaDocumentPartitioner(document, IDocumentExtension3.DEFAULT_PARTITIONING);
	}

	/**
	 * Sets up the Modelica document partitioner for the given document for the given partitioning.
	 *
	 * @param document the document to be set up
	 * @param partitioning the document partitioning

	 */
	public void setupModelicaDocumentPartitioner(IDocument document, String partitioning) {
		IDocumentPartitioner partitioner= createDocumentPartitioner();
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension3= (IDocumentExtension3) document;
			extension3.setDocumentPartitioner(partitioning, partitioner);
		} else {
			document.setDocumentPartitioner(partitioner);
		}
		partitioner.connect(document);
	}
}
