/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
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
