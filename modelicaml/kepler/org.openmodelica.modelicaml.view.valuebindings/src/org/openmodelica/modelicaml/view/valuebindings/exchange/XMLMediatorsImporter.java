package org.openmodelica.modelicaml.view.valuebindings.exchange;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class XMLMediatorsImporter {
	private final String FILE_EXTENSION = "xml";
	
	private String filePath;
	
	public void importMediators(){
		// Let the user select the file for each model 
		FileDialog fileDialog = new FileDialog(new Shell());
		fileDialog.getParent().setFocus();
//		fileDialog.setFilterPath(getResultFilesFolderPath());
		String[] extensionFilter = {"*." + FILE_EXTENSION, "*.*"};
		fileDialog.setFilterExtensions(extensionFilter);
		
//		fileDialog.setFileName(getGeneratedModelsData().getSimulationResultsFile().get(element));
		fileDialog.setText("Select file to be imported.");
		
		filePath = fileDialog.open();

		// ... TODO
		
//		System.err.println(selectedFilePath);
	}
}
