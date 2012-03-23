package org.openmodelica.modelicaml.simulation.testexecution.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.openmodelica.modelicaml.simulation.Activator;
import org.openmodelica.modelicaml.simulation.testexecution.dialogs.DialogPlot;
import org.openmodelica.modelicaml.simulation.xml.SimulationResult_XML_reader;

public class PlotTestResultsAction implements
		IWorkbenchWindowActionDelegate {

	private String xmlFilePath = null;
	
	@Override
	public void run(IAction action) {
		if (this.xmlFilePath == null) {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
			if(selection instanceof IStructuredSelection) {
				Object firstElement = ((IStructuredSelection)selection).getFirstElement();
				if (firstElement instanceof IFile) {
					this.xmlFilePath = ((IFile)firstElement).getLocationURI().toString().replaceFirst("file:\\/", "");
				}
			}
		}
		
		if (this.xmlFilePath != null) {
			xmlFilePath = xmlFilePath.replaceFirst("file://", "");
//			xmlFilePath = "C:/Projects/ModelicaML/runtime-ModelicaML/modelicaml.example.potableWaterSystem_v31/verification-gen/verification-session_20120312172819/ModelicaMLModel.GenVeMs_for__SPWS_Environment_1.VeM_for__s1_Fill_and_Drain_Tank_res.xml";
//			System.err.println("ALLE DATEN: " + xmlFilePath);
			Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().setResults(SimulationResult_XML_reader.getAllResultsAsStringFromXML(xmlFilePath));
//			System.out.println(Activator.getSimulationCenter_NonInteractive().getSimulationResultManager().getResults());

			DialogPlot dialog = new DialogPlot(new Shell(), this.xmlFilePath);
			dialog.open();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}


	public void setXMLFilePath(String path){
		this.xmlFilePath = path;
	}
}
