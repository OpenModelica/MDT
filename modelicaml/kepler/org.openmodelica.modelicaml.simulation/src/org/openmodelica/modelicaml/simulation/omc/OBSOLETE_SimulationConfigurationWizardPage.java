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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.simulation.omc;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class SimulationConfigurationWizardPage.
 */
public class OBSOLETE_SimulationConfigurationWizardPage extends WizardPage {

	/** The composite1. */
	private Composite composite1;
	
	/** The composite model tree. */
	private Composite compositeModelTree;
	
	/** The composite search. */
	private Composite compositeSearch;
	
	/** The composite buttons. */
	private Composite compositeButtons;
	
	/** The sash form model data. */
	private SashForm sashFormModelData;
	
	/** The button search. */
	private Button buttonSearch;
	
	/** The text search. */
	private Text textSearch;
	
	/** The label search. */
	private Label labelSearch;

	/** The tree model. */
	Tree treeModel;
	
	/** The group simulation setup. */
	private Group groupSimulationSetup;	
	
	/** The combo solver. */
	Combo comboSolver;
	
	/** The label solver. */
	private Label labelSolver;

	/** The text tolerance. */
	Text textTolerance;
	
	/** The label tolerance. */
	private Label labelTolerance;

	/** The text start time. */
	Text textStartTime;
	
	/** The label start time. */
	private Label labelStartTime;
	
	/** The text stop time. */
	Text textStopTime;
	
	/** The label stop time. */
	private Label labelStopTime;
	
	/** The combo interval unit. */
	private Combo comboIntervalUnit;
	
	/** The text interval. */
	private Text textInterval;
	
	/** The label interval. */
	private Label labelInterval;
	
	/** The composite simulation setup interval. */
	private Composite compositeSimulationSetupInterval;

	/** The model element. */
	private Element modelElement;
	
	/** The plot var path list. */
	ArrayList<String> plotVarPathList = new ArrayList<String>();

	/** The found position. */
	private int foundPosition;
	
	/** The found tree item. */
	private List<TreeItem> foundTreeItem = new ArrayList<TreeItem>();;

	/**
	 * Instantiates a new simulation configuration wizard page.
	 * 
	 * @param pageName
	 *            the page name
	 * @param modelElement
	 *            the model element
	 */
	protected OBSOLETE_SimulationConfigurationWizardPage(String pageName, Element modelElement) {
		super(pageName);
		setTitle("OMC Simulation Configuration");
        setMessage("Specify simulation parameters and select variables to be plotted." +
        		"\nAfter a successful simulation OMC will open a separate plot window.");
        this.modelElement = modelElement;
	}

	
	private void setSimulationParameters(){
		
		if (this.modelElement instanceof NamedElement) {
			
			NamedElement element  = (NamedElement) this.modelElement;
			Stereotype stereotype = element.getAppliedStereotype(Constants.stereotypeQName_Simulation);
			
			if (stereotype == null) {
				stereotype = element.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario);
			}
			
			if (stereotype != null) {
				Object startTime = element.getValue(stereotype, Constants.propertyName_startTime);
				if (startTime != null) {
					textStartTime.setText(startTime.toString());
				}
				
				Object stopTime = element.getValue(stereotype, Constants.propertyName_stopTime);
				if (startTime != null) {
					textStopTime.setText(stopTime.toString());
				}

				Object tolerance = element.getValue(stereotype, Constants.propertyName_tolerance);
				if (tolerance != null) {
					textTolerance.setText(tolerance.toString());
				}
				
//				Object interval = element.getValue(stereotype, Constants.propertyName_numberOfIntervals);
//				if (interval != null) {
//					textInterval.setText(interval.toString());
//				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		{
			composite1 = new Composite(parent, SWT.NONE);
			GridLayout composite1Layout = new GridLayout();
			composite1Layout.makeColumnsEqualWidth = true;
			composite1.setLayout(composite1Layout);
			{
				sashFormModelData = new SashForm(composite1, SWT.BORDER);
				GridData sashFormModelDataLData = new GridData();
				sashFormModelDataLData.horizontalAlignment = GridData.FILL;
				sashFormModelDataLData.grabExcessHorizontalSpace = true;
				sashFormModelDataLData.verticalAlignment = GridData.FILL;
				sashFormModelDataLData.grabExcessVerticalSpace = true;
				sashFormModelData.setLayoutData(sashFormModelDataLData);
				sashFormModelData.setSize(60, 30);
				sashFormModelData.setOrientation(SWT.VERTICAL);
				{
					compositeModelTree = new Composite(sashFormModelData, SWT.NONE);
					GridLayout compositeModelTreeLayout = new GridLayout();
					compositeModelTreeLayout.makeColumnsEqualWidth = true;
					compositeModelTree.setLayout(compositeModelTreeLayout);
					{
						GridData treeModelLData = new GridData();
						treeModelLData.horizontalAlignment = GridData.FILL;
						treeModelLData.grabExcessHorizontalSpace = true;
						treeModelLData.verticalAlignment = GridData.FILL;
						treeModelLData.grabExcessVerticalSpace = true;
						treeModelLData.widthHint = 600;
						treeModelLData.heightHint = 300;
						treeModel = new Tree(compositeModelTree, SWT.CHECK | SWT.BORDER);
						treeModel.setLayoutData(treeModelLData);
						
						treeModel.addListener(SWT.Selection, new Listener() {
						      public void handleEvent(Event event) {
						    	  if (event.detail == SWT.CHECK) {
						    		  Object data = ((TreeItem)event.item).getData();
						    		  if ( ((TreeItem)event.item).getChecked()  ) {
						    			  if (data != null) {
						    				  plotVarPathList.add(data.toString());
										}
						    			  else {
						    				  Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						    				  MessageDialog.openError(shell, "Selection Validation", "This selection is not valid.\nYou can only select primitive variables (leaves of this tree).");
						    				  ((TreeItem)event.item).setChecked(false);
						    			  }
									} else if ( !((TreeItem)event.item).getChecked()) {
										if (data != null) {
						    				  plotVarPathList.remove(data.toString());
										}
									}
						    	  }
						      }
						    });
						new OBSOLETE_ModelicaMLClassInstanceTreeBuilder(modelElement, treeModel);
						
					}
				}
			}
			{
				compositeSearch = new Composite(composite1, SWT.BORDER);
				GridLayout compositeSearchLayout = new GridLayout();
				compositeSearchLayout.makeColumnsEqualWidth = true;
				compositeSearchLayout.numColumns = 4;
				GridData compositeSearchLData = new GridData();
				compositeSearchLData.horizontalAlignment = GridData.FILL;
				compositeSearchLData.grabExcessHorizontalSpace = true;
				compositeSearch.setLayoutData(compositeSearchLData);
				compositeSearch.setLayout(compositeSearchLayout);
				{
					labelSearch = new Label(compositeSearch, SWT.NONE);
					GridData labelSearchLData = new GridData();
					labelSearch.setLayoutData(labelSearchLData);
					labelSearch.setText("Search for: ");
				}
				{
					GridData textSearchLData = new GridData();
					textSearchLData.horizontalSpan = 2;
					textSearchLData.horizontalAlignment = GridData.FILL;
					textSearchLData.grabExcessHorizontalSpace = true;
					textSearch = new Text(compositeSearch, SWT.BORDER);
					textSearch.setLayoutData(textSearchLData);
					textSearch.addModifyListener(new ModifyListener() {

						public void modifyText(ModifyEvent evt) {
							foundTreeItem.clear();
							foundPosition = 0;
							buttonSearch.setText("Search");
						}
					});
				}
				{
					buttonSearch = new Button(compositeSearch, SWT.PUSH | SWT.CENTER);
					GridData buttonSearchLData = new GridData();
					buttonSearchLData.horizontalAlignment = GridData.FILL;
					buttonSearch.setLayoutData(buttonSearchLData);
					buttonSearch.setText("Search");
					buttonSearch.addSelectionListener(new SelectionAdapter() {
						
						@Override
						public void widgetSelected(SelectionEvent e) {
							super.widgetSelected(e);
							if(foundTreeItem.isEmpty() && !textSearch.getText().isEmpty()){ //Is a new search
								search(textSearch.getText());
								buttonSearch.setText("Next");
								showNextSearchResult();
//								if(doAdoptionOfProperty(foundTreeItem.get(foundPosition))){
//									showNextSearchResult();
//								}
							} else{ //Go to next found element
								showNextSearchResult();
//								if(doAdoptionOfProperty(foundTreeItem.get(foundPosition))){
//									showNextSearchResult();
//								}
							}
						}
					});
				}
			}
			{
				compositeButtons = new Composite(composite1, SWT.NONE);
				GridLayout compositeButtonsLayout = new GridLayout();
				compositeButtonsLayout.makeColumnsEqualWidth = true;
				GridData compositeButtonsLData = new GridData();
				compositeButtonsLData.horizontalAlignment = GridData.FILL;
				compositeButtonsLData.grabExcessHorizontalSpace = true;
				compositeButtons.setLayoutData(compositeButtonsLData);
				compositeButtons.setLayout(compositeButtonsLayout);
			}
		}
		setControl(composite1);
		
		
		{
			groupSimulationSetup = new Group(composite1, SWT.NONE);
			GridLayout groupSimulationSetupLayout = new GridLayout();
			groupSimulationSetupLayout.makeColumnsEqualWidth = true;
			groupSimulationSetup.setLayout(groupSimulationSetupLayout);
			GridData groupSimulationSetupLData = new GridData();
			groupSimulationSetupLData.horizontalAlignment = GridData.FILL;
			groupSimulationSetupLData.grabExcessHorizontalSpace = true;
			groupSimulationSetup.setLayoutData(groupSimulationSetupLData);
			groupSimulationSetup.setText("Simulation Parameters");
			{
				compositeSimulationSetupInterval = new Composite(
						groupSimulationSetup, SWT.NONE);
				GridLayout compositeSimulationSetupIntervalLayout = new GridLayout();
				compositeSimulationSetupIntervalLayout.makeColumnsEqualWidth = true;
				compositeSimulationSetupIntervalLayout.numColumns = 3;
				GridData compositeSimulationSetupIntervalLData = new GridData();
				compositeSimulationSetupIntervalLData.horizontalAlignment = GridData.FILL;
				compositeSimulationSetupIntervalLData.grabExcessHorizontalSpace = true;
				compositeSimulationSetupInterval
						.setLayoutData(compositeSimulationSetupIntervalLData);
				compositeSimulationSetupInterval
						.setLayout(compositeSimulationSetupIntervalLayout);
				
				
				
				
				
				{
					labelStartTime = new Label(
							compositeSimulationSetupInterval, SWT.NONE);
					GridData labelStartTimeData = new GridData();
					labelStartTime.setLayoutData(labelStartTimeData);
					labelStartTime.setText("StartTime:");
				}
				{
					textStartTime = new Text(compositeSimulationSetupInterval,
							SWT.BORDER);
					GridData textStartTimeData = new GridData();
					textStartTimeData.horizontalSpan = 2;
					textStartTimeData.horizontalAlignment = GridData.FILL;
					textStartTime.setLayoutData(textStartTimeData);
					textStartTime.setText("0");
					textStartTime.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent evt) {
							dialogChanged();
						}
					});
				}
				
				
				{
					labelStopTime = new Label(
							compositeSimulationSetupInterval, SWT.NONE);
					GridData labelStopTimeData = new GridData();
					labelStopTime.setLayoutData(labelStopTimeData);
					labelStopTime.setText("StopTime:");
				}
				{
					textStopTime = new Text(compositeSimulationSetupInterval,
							SWT.BORDER);
					GridData textStopTimeData = new GridData();
					textStopTimeData.horizontalSpan = 2;
					textStopTimeData.horizontalAlignment = GridData.FILL;
					textStopTime.setLayoutData(textStopTimeData);
					textStopTime.setText("10");
					textStopTime.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent evt) {
							dialogChanged();
						}
					});
				}
				
//				{
//					labelInterval = new Label(compositeSimulationSetupInterval,
//							SWT.NONE);
//					GridData labelIntervalLData = new GridData();
//					labelInterval.setLayoutData(labelIntervalLData);
//					labelInterval.setText("Output Interval:");
//				}
//				{
//					textInterval = new Text(compositeSimulationSetupInterval,
//							SWT.BORDER);
//					GridData textIntervalLData = new GridData();
//					textIntervalLData.horizontalAlignment = GridData.FILL;
//					textInterval.setLayoutData(textIntervalLData);
//					textInterval.setText("1");
//					textInterval.addModifyListener(new ModifyListener() {
//						public void modifyText(ModifyEvent evt) {
//							dialogChanged();
//						}
//					});
//				}
//				{
//					comboIntervalUnit = new Combo(
//							compositeSimulationSetupInterval, SWT.BORDER | SWT.READ_ONLY);
//					GridData comboIntervalUnitLData = new GridData();
//					comboIntervalUnitLData.horizontalAlignment = GridData.FILL;
//					comboIntervalUnit.setLayoutData(comboIntervalUnitLData);
//					comboIntervalUnit.setItems(new java.lang.String[] { "ms",
//							"s", "min", "h" });
//					comboIntervalUnit.addSelectionListener(new SelectionAdapter() {
//						@Override
//						public void widgetSelected(SelectionEvent e) {
//							dialogChanged();
//						}
//					});
//					comboIntervalUnit.select(1);
//				}
				
				{
					labelTolerance = new Label(
							compositeSimulationSetupInterval, SWT.NONE);
					GridData labelToleranceLData = new GridData();
					labelTolerance.setLayoutData(labelToleranceLData);
					labelTolerance.setText("Tolerance:");
				}
				{
					textTolerance = new Text(compositeSimulationSetupInterval,
							SWT.BORDER);
					GridData textToleranceLData = new GridData();
					textToleranceLData.horizontalSpan = 2;
					textToleranceLData.horizontalAlignment = GridData.FILL;
					textTolerance.setLayoutData(textToleranceLData);
					textTolerance.setText("1e-4");
					textTolerance.addModifyListener(new ModifyListener() {
						public void modifyText(ModifyEvent evt) {
							dialogChanged();
						}
					});
				}
				
				
				{
					labelSolver = new Label(compositeSimulationSetupInterval,
							SWT.NONE);
					GridData labelSolverLData = new GridData();
					labelSolver.setLayoutData(labelSolverLData);
					labelSolver.setText("Solver:");
				}
				
				{
					GridData comboSolverLData = new GridData();
					comboSolverLData.horizontalSpan = 2;
					comboSolverLData.horizontalAlignment = GridData.FILL;
					comboSolver = new Combo(compositeSimulationSetupInterval,
							SWT.BORDER | SWT.READ_ONLY);
					comboSolver.setLayoutData(comboSolverLData);
					comboSolver.setItems(new java.lang.String[] {"dassl","dassl2", "rungekutta", "dassl",  "euler" });
					comboSolver.select(0);
				}
			}
		}
		
		
        // Preset the simulation parameters 
        setSimulationParameters();
		
		//compositeSelectedValueData.setEnabled(false); //Can't set invisible because size will be corrupt...
	}
	
	
	
	/**
	 * Show the next search result, if the last found result has already been
	 * displayed start from beginning.
	 */
	private void showNextSearchResult(){
		treeModel.select(foundTreeItem.get(foundPosition));
		treeModel.showSelection();
		if(foundTreeItem.size()-1 <= foundPosition){
			foundPosition = 0; //Search from begining
		}else{
			foundPosition++;
		}
	}
	
	/**
	 * Searches recursively for an element using the rekSearch method.
	 * 
	 * @param name
	 *            the name
	 */
	private void search(String name) {
		rekSearch(treeModel.getItems()[0], name); //treeModel.getItems()[0] is the root element!
	}
	
	/**
	 * Rek search.
	 * 
	 * @param item
	 *            the item
	 * @param name
	 *            the name
	 */
	private void rekSearch(TreeItem item, String name){
		if(item.getItemCount() != 0){ //is node
			for(TreeItem child : item.getItems()){
				rekSearch(child, name);
			}
		} else{ //is leaf
			if(((String)item.getData()).toLowerCase().contains(name.toLowerCase())){
				foundTreeItem.add(item);
			}
		}
	}

	
	/**
	 * Dialog changed.
	 */
	private void dialogChanged() {
//		//VALIDATE project name
//		if (textSessionConfigurationName.getText() == null || textSessionConfigurationName.getText().isEmpty()) {
//			updateStatus("Please enter a configuration name.");
//			return;
//		}
//		
//		if (!textSessionConfigurationName.getText().matches("\\w*")) {
//			updateStatus("Please enter a valid configuration name. Only word characters are allowed [a-z A-Z _ 0-9].");
//			return;
//		}
//		
//		//VALIDATE project version
//		if (textVersion.getText() == null || textVersion.getText().isEmpty()) {
//			updateStatus("Please enter a configuration version.");
//			return;
//		}
//		
//		//TODO einzelnes zeichen geht nicht... [201009]
//		if (!textVersion.getText().matches("[a-zA-Z0-9][a-zA-Z0-9\\.]*[a-zA-Z0-9]")) {
//			updateStatus("Please enter a valid configuration version. Only word characters [a-z A-Z 0-9] and dots [.] are allowed.");
//			return;
//		}
		
//		//VALIDATE project CS Port
//		if (textCSPort.getText() == null || textCSPort.getText().isEmpty()) {
//			updateStatus("Please enter a configuration version.");
//			return;
//		}
//		
//		if (!textCSPort.getText().matches("[0-9]*")) {
//			updateStatus("Please enter a valid simulation control server port. Only digits [0-9] are allowed.");
//			return;
//		}
//		
//		if ( (new Integer(textCSPort.getText())) < 1024 || (new Integer(textCSPort.getText())) > 65535) {
//			updateStatus("Please enter a valid simulation control server port. Commonly user ports are 1024 - 65535.");
//			return;
//		}
//		
//		//TODO Check ob duplikate ports oder bereits in benutzung! [201009]
//		
//		//VALIDATE project CC Port
//		if (textCCPort.getText() == null || textCCPort.getText().isEmpty()) {
//			updateStatus("Please enter a configuration version.");
//			return;
//		}
//		
//		if (!textCCPort.getText().matches("[0-9]*")) {
//			updateStatus("Please enter a valid simulation control server port. Only digits [0-9] are allowed.");
//			return;
//		}
//		
//		if ( (new Integer(textCCPort.getText())) < 1024 || (new Integer(textCCPort.getText())) > 65535) {
//			updateStatus("Please enter a valid simulation control server port. Commonly user ports are 1024 - 65535.");
//			return;
//		}
//		
//		//TODO Check ob duplikate ports oder bereits in benutzung! [201009]
//		
//		//VALIDATE project TS Port
//		if (textTSPort.getText() == null || textTSPort.getText().isEmpty()) {
//			updateStatus("Please enter a configuration version.");
//			return;
//		}
//		
//		if (!textTSPort.getText().matches("[0-9]*")) {
//			updateStatus("Please enter a valid simulation control server port. Only digits [0-9] are allowed.");
//			return;
//		}
//		
//		if ( (new Integer(textTSPort.getText())) < 1024 || (new Integer(textTSPort.getText())) > 65535) {
//			updateStatus("Please enter a valid simulation control server port. Commonly user ports are 1024 - 65535.");
//			return;
//		}
		
		//TODO Check ob duplikate ports oder bereits in benutzung! [201009]
		
		//VALIDATE startTime
		if (textStartTime.getText() == null || textStartTime.getText().isEmpty()) {
			updateStatus("Please enter an output startTime value.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			new Double(textStartTime.getText());
		} catch (Exception e) {
			updateStatus("Please enter a valid output startTime value.");
			return;
		}
		
		//VALIDATE stopTime
		if (textStopTime.getText() == null || textStopTime.getText().isEmpty()) {
			updateStatus("Please enter an output stopTime value.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			new Double(textStopTime.getText());
		} catch (Exception e) {
			updateStatus("Please enter a valid output stopTime value.");
			return;
		}
		
		
//		//VALIDATE StepTime
//		if (textInterval.getText() == null || textInterval.getText().isEmpty()) {
//			updateStatus("Please enter an output interval value.");
//			return;
//		}
//		
//		//Check if its a double value no need to catch exponent e.g. 2e10
//		try {
//			new Double(textInterval.getText());
//		} catch (Exception e) {
//			updateStatus("Please enter a valid output interval value.");
//			return;
//		}
//		
//		Double simStepTimeInSeconds = 0.0;
//		
//		if(comboIntervalUnit.getText().equals("s")){
//			simStepTimeInSeconds = new Double(textInterval.getText());
//		}else if(comboIntervalUnit.getText().equals("ms")){
//			simStepTimeInSeconds = ((new Double(textInterval.getText())) / 1000);
//		}else if(comboIntervalUnit.getText().equals("min")){
//			simStepTimeInSeconds = ((new Double(textInterval.getText())) * 60);
//		}else if(comboIntervalUnit.getText().equals("h")){
//			simStepTimeInSeconds = ((new Double(textInterval.getText())) * 3600);
//		}
//		
//		if(simStepTimeInSeconds < 0.001){
//			updateStatus("Please enter a valid output interval. Its value must not under-run 0.001 s. Your value in sec: " + simStepTimeInSeconds);
//			return;
//		}
		
		//VALIDATE Tolerance
		if (textTolerance.getText() == null || textTolerance.getText().isEmpty()) {
			updateStatus("Please enter a tolerance value for the calculation.");
			return;
		}
		
		//Check if its a double value no need to catch exponent e.g. 2e10
		try {
			new Double(textTolerance.getText());
		} catch (Exception e) {
			updateStatus("Please enter a valid tolerance value.");
			return;
		}
		
		updateStatus(null); //all entries are ok
	}
	
	/**
	 * Update page status be signal if page is completed or not.
	 * 
	 * @param message
	 *            page is completed if message is null, otherwise the user will
	 *            get a message
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
}
