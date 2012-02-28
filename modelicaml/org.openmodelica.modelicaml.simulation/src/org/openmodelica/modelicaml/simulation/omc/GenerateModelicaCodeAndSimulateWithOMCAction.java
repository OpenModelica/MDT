package org.openmodelica.modelicaml.simulation.omc;
//package com.eadsiw.modelicaml.gen.modelica.simulation.omc;
///*******************************************************************************
// * Copyright (c) 2008 CEA LIST.
// * All rights reserved. This program and the accompanying materials
// * are property of the CEA, their use is subject to specific agreement 
// * with the CEA.
// * 
// * Contributors:
// *    CEA LIST - initial API and implementation
// *******************************************************************************/
//
//package com.eadsiw.modelicaml.simulation;
//
//import java.io.IOException;
//
//import org.eclipse.core.resources.IFile;
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Status;
//import org.eclipse.core.runtime.jobs.Job;
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.common.util.URI;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.resource.Resource;
//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
//import org.eclipse.jface.action.IAction;
//import org.eclipse.jface.viewers.IStructuredSelection;
//import org.eclipse.jface.wizard.WizardDialog;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.ui.IViewPart;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.uml2.uml.Element;
//import org.eclipse.uml2.uml.NamedElement;
//
//import com.cea.papyrus.core.editor.ModelManager;
//import com.cea.papyrus.core.editpart.IUMLElementEditPart;
//import com.eadsiw.modelicaml.popupactions.Action;
//import com.modelicaml.utls.Utls;
//
//import fr.obeo.acceleo.chain.File;
//import fr.obeo.acceleo.chain.impl.spec.CChain;
//import fr.obeo.acceleo.chain.impl.spec.CFolder;
//import fr.obeo.acceleo.chain.impl.spec.CLog;
//import fr.obeo.acceleo.chain.impl.spec.CModel;
//import fr.obeo.acceleo.gen.IGenFilter;
//import fr.obeo.acceleo.gen.template.eval.LaunchManager;
//
///**
// * Implementation class for ClassAction action
// */
//public class GenerateModelicaCodeAndSimulateWithOMCAction extends Action {
//	private ModelManager modelManager = null;
//	private String project = null;
//	private CChain myChain = null;
//	private String modelFileURI = null;
//	
//	private String projectPath = null;
//	private IFile modelFile = null;
//	private String modelName = null;
//	private String modelElementQualifiedName = null;
//	private String modelElementDotPath = null;
//	private String temp_modelElementWoSpecChar= null;
//	private Element selectedClass = null;
//	
//	private String packageMoFilePath = null;
//	private Element modelElement = null;
//	private String folderPath = null;
//	
//	private String plotCommand = null;
//	private String simPar = null;
//	
//	private Boolean go = true;
//	
//	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//
//	
//	IGenFilter filter = new IGenFilter() {
//		public boolean filter(java.io.File script, IFile targetFile, EObject object) throws CoreException {
//		return true;
//		}
//		};
//
//	
//	public void run(IAction action) { 
//		
//		IStructuredSelection selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
//
//		if (selection.getFirstElement() instanceof IUMLElementEditPart<?>) {
//			modelManager = ((IUMLElementEditPart<?>) selection.getFirstElement()).getModelManager();
//			modelFileURI = modelManager.getDiResourceSet().getUMLModelResource().getURI().toString();
//			project = modelManager.getDiResourceSet().getUMLifile().getProject().getName();
//			
//			modelFile = modelManager.getDiResourceSet().getUMLifile();
//			projectPath = modelFile.getProject().getLocationURI().toString().replaceFirst("file:/", "");
//			Object model = ((IUMLElementEditPart<?>)selection.getFirstElement()).getUmlElement().getModel();
//			modelName = ((NamedElement)model).getName();
//			
//			selectedClass = ((IUMLElementEditPart<?>)selection.getFirstElement()).getUmlElement();
//			modelElement = ((IUMLElementEditPart<?>)selection.getFirstElement()).getUmlElement();
//			modelElementQualifiedName = ((NamedElement)modelElement).getQualifiedName();
//			temp_modelElementWoSpecChar = Utls.replaceSpecCharExceptThis(modelElementQualifiedName, "::");
//			modelElementDotPath = temp_modelElementWoSpecChar.replaceAll("::", ".");
//			packageMoFilePath = projectPath+"/code-gen/"+modelName + "/" +"package.mo";
//			folderPath = projectPath + "/code-gen";
//	
//		}
//	
//		URI chainURI = URI.createPlatformPluginURI("/com.eadsiw.modelicaml.gen.modelica/bin/code_generation.chain", true); 
//		ResourceSet rs = new ResourceSetImpl(); 
//		Resource r = (Resource) rs.createResource(chainURI); 
//		try {
//			r.load(null);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//
//		myChain = (CChain) r.getContents().get(0); 
//
//		String modelFilePath = modelFileURI.replace("platform:/resource/", "");
//		String outputFolderPath = project;
//		String logPath = project+"/errors.log"; 
//
//		// Don't create Parameter Files... simply set the correct path into them 
//		EList<File> files = myChain.getParametersFiles();
//		for (File file : files) {
//			if (file instanceof CModel) {
//				file.setPath(modelFilePath);
//			} else if (file instanceof CFolder) {
//				file.setPath(outputFolderPath);
//			} else if (file instanceof CLog) {
//				file.setPath(logPath);
//			}
//		}
//   
//	    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//	    WizardDialog wizard = new WizardDialog(shell, new SimulationConfigurationWizard(modelElement));
//	    wizard.open();
//
//	    setGo(wizard.getReturnCode());
//	    
//		if (go) {
//			simPar = " startTime=" + OMCSimulationDataStorage.startTime
//			+ ", stopTime=" + OMCSimulationDataStorage.stopTime
//			+ ", tolerance=" + OMCSimulationDataStorage.tolerance
//			+ ", method=" + OMCSimulationDataStorage.Solver;
//
//			String vars = "";
//			plotCommand = null;
//			
//			if (OMCSimulationDataStorage.selectedVarPathList.size() > 0) {
//				vars = OMCSimulationDataStorage.selectedVarPathList.toString().substring(1, OMCSimulationDataStorage.selectedVarPathList.toString().length() - 1);
//				plotCommand = "plot({" + vars + "})";
//			}
//			Job job = new Job("Modelica Code Generation and Simulation with OMC") {
//				protected IStatus run(IProgressMonitor monitor) {
//					runchain(monitor);
//					new OMCCommandExecuter(modelElement, folderPath, packageMoFilePath,
//							modelElementDotPath, plotCommand, simPar);
//					return Status.OK_STATUS;
//				}
//			};
//			job.setUser(true);
//			job.schedule();
//		}
//	}
//
////	public String getInstanceSelection(Element modelElement){
////		String plotCommand = null;
////		if (modelElement != null) {
////			ModelicaMLClassInstanceTreeBuilder iTree = new ModelicaMLClassInstanceTreeBuilder(modelElement);
////			
////			ElementListSelectionDialog lsd = new ElementListSelectionDialog(shell,new LabelProvider());	    
////			lsd.setTitle("OMC Simulation Setup - Variables to be Plotted");
////			lsd.setMessage("Select variables (you can select multiple) of the class '"+ ((NamedElement)selectedClass).getName() +"' that should be displayed in plot." +
////					"\n" +
////					"\nNote: After Modelica Code Generation the model will be loaded into OMC. OMC responses are displayed in the console." +
////					"\nAfter a successful simulation OMC will open a separate plot window.                                                                                 " 		
////			);
////			//lsd.setHelpAvailable(true);
////			lsd.setElements(iTree.getInstacePathList().toArray());
////			lsd.setMultipleSelection(true);
////			lsd.setFilter("*");
////			lsd.open();
////			
////			setGo(lsd.getReturnCode()); 
////			
////			Object[] result = lsd.getResult();
////
////			String plotVars = null;
////			if (result != null) {
////							for (int i = 0; i < result.length; i++) {
////					if (i==0) {
////						plotVars = result[i].toString();
////					}
////					else {
////						plotVars = plotVars + "," + result[i].toString();
////					}
////				}				
////			}
////			
////			if (plotVars != null) {
////				plotCommand = "plot({"+plotVars+"})";	
////			}
////		}
////		return plotCommand;
////	}
//	
////	public String getSimulationParameters(){
////		String simPar = null;
////		final InputDialog id = new InputDialog(shell, "OMC Simulation Setup - Simulation Parameters", "Enter simulation parameters from the following list:" +
////				
////				"\n" +
////				"\n              startTime " +
////				"\n              stopTime " +
////				"\n              numberOfIntervals " +
////				"\n              outputInterval " +
////				"\n              method " +
////				"\n              tolerance " +
////				"\n              fixedStepSize" +
////				"\n" +
////				"\n separated by comma (e.g. 'startTime = 0, stopTime = 100,' etc.)." +
////				"\n"
////				,"stopTime = 10", new IInputValidator() {
////			public String isValid(String newText) {
////				if (!newText.contains("stopTime")) {
////					return "Please specify at least the stopTime";
////				}
////				return null;
////			}
////		});
////
////		id.open();
////		setGo(id.getReturnCode()); 
////		simPar = id.getValue();
////		
////		return simPar;
////	}
////	
//	private void setGo(Integer returnCode){
//		if (returnCode == 1) {
//			this.go = false;
//		}
//		if (returnCode == 0) {
//			this.go = true;
//		}
//	}
//
//	
//	public void runchain(IProgressMonitor monitor)
//	{
//		try {
//			 myChain.launch(filter, monitor, LaunchManager.create("run", true));
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//
//	public void init(IViewPart view) {
//		// TODO Auto-generated method stub
//
//	}
//}
