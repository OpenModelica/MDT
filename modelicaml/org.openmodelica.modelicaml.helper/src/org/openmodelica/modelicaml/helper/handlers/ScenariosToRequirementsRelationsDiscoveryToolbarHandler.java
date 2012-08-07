package org.openmodelica.modelicaml.helper.handlers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;

public class ScenariosToRequirementsRelationsDiscoveryToolbarHandler extends VeMGenaratorToobarHandler{
	
	protected GeneratedModelsData gmd;

	@Override
	protected void openDialog() {
		super.setMode(Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY);
		super.openDialog();
	}
	
	protected void openDialog(int mode) {
		super.setMode(mode);
		super.openDialog();
	}

	
	@Override
	protected void proceed(){
		
		boolean go = true;
		
		if (getModelsWithBindingErrors() != null && getModelsWithBindingErrors().size() > 0 ) {
			
			NamedElement generatedPackage = (NamedElement) smg.getGeneratedPackages().toArray()[0];
			
			go = MessageDialog.openQuestion(getShell(), "Model Generation Binding Errors", 
					"For some of the generated model no bindings could be generated, " +
					"\nsee models and markers in " + "'"+generatedPackage.getName()+"'" +
					
					"\n\nIt is recommended to first correct the bindings before proceeding. " +
					"You can store providers selection by adding the selected provider to 'preferred providers'." +

					"\n\nDo you want to proceed anyway? ");
		}
		
		if (go && !smg.isTestSimulationModelGenerationCanceled()) {

			// collect data
			gmd = new GeneratedModelsData(smg);
			
			if (gmd.getGeneratedModels() != null && gmd.getGeneratedModels().size() > 0) {
				
				AnalyzeSimulationResultsToolbarHandler handler = new AnalyzeSimulationResultsToolbarHandler();
				handler.setGeneratedModelsData(gmd);
				handler.setMode(getMode());
				
				try {
					handler.execute(null);
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageDialog.openError(getShell(), "Verificaiton Analysis Error", "Could not execute the verification analysis.");
				}
			}
			else {
				MessageDialog.openInformation(getShell(), "Models Generation", "No models were generated.");
			}
		}
	}
}
