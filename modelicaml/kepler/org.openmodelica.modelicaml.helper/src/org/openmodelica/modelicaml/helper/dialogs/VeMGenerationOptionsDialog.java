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
package org.openmodelica.modelicaml.helper.dialogs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.helper.generators.GeneratorVeMRequirementsBased;
import org.openmodelica.modelicaml.helper.generators.GeneratorVeMScenariosBased;
import org.openmodelica.modelicaml.helper.generators.example.GenerateVerificationModels;

public class VeMGenerationOptionsDialog extends Dialog {

	private Element rootPackage;
	
	private Element systemModel;
	private Element targetPackage;
	private Element requirementsPackage;
	private Element scenariosPackage;
	private Element bindingsPackage;

	// class that should be inherited by every generated verification model
	private Element superClass; 
	
	private String systemModelNamePrefix = "System model: ";
	private String targetPackgeNamePrefix = "Package to store generated models in: ";
	private String requirementsPackageNamePrefix = "Package for requirements search: ";
	private String scenariosPackageNamePrefix = "Package for scenarios search: ";
	private String bindingsPackageNamePrefix = "Package for bindings search: ";
	private String superClassNamePrefix = "Each generated model shall inherit from: ";
	
	private String systemModelSelectionMessage = "Click on an element in the model explorer " +
			"\r\nto select the system model.";
	private String targetPackgeSelectionMessage = "Click on an element in the model explorer " +
			"\r\nto select the target package to store models in.";
	private String requirementsPackageSelectionMessage = "Click on an element in the model explorer " +
			"\r\nto select the requirements package.";
	private String scenariosPackageSelectionMessage = "Click on an element in the model explorer " +
			"\r\nto select the scenarios package.";
	private String bindingsPackageSelectionMessage = "Click on an element in the model explorer " +
			"\r\nto select the bindings package.";
	private String superClassSelectionMessage = "Click on an element in the model explorer " +
			"\r\nto select the super class that shall be inherited by any generated model.";
	
	/*
	 * Indicates that there was at least one model for which no correct bindings could be generated.
	 */
//	private boolean bindingErrorsDetected = false;
	
	
	/*
	 * The mode indicates if this dialog is used for verification models generation or scenarios to requirements <<UsedToVerify>> relations discovery
	 */

	private int mode;
	
	
	
	private String messageVemGen = "This helper will create a package that will contain verification models composed of the selected system model, " +
			"\r\nscenario (if selected) and requirements. Requirements are combined with scenarios based on known " +
			"\r\nrelations. The composition of the verification models is based on bindings which must be defined correctly.";

	private String messageScenarioToReqRelationDiscovery = 
			"This helper will create a package with models composed of the selected system model, scenario and requirements " +
			"\r\nthat can be verified, then simulate and analize results in order to determine new relations between scenarios " +
			"\r\nand requirements. The composition of the verification models is based on bindings which must be defined correctly.";
	
	private String messageAutomaticScenarioBasedVerification = 
			"This helper will create a package with models composed of the selected system model, scenarios and requirements " +
			"\r\nthat can be verified, then simulate them, and then analize results. If the 'minimize' option is selected, requierements " +
			"\r\nwith positive relations will be combined only with referenced scenarios. Requirements with negative relations will not be combined " +
			"\r\nwith referenced scenarios. The composition of the verification models is based on bindings which must be defined correctly.";

	
	private String dialogMessage = "";
	
	private HashSet<Button> buttons = new HashSet<Button>();
	private HashMap<Button,Label> maptButtonsToLabels = new HashMap<Button,Label>();
	
	private CLabel lblSelectionHint;
	
	private Button btnSystemModel;
	private Button btnTargetPackage;
	private Button btnRequirementsPackage;
	private Button btnScenariosPackage;
	private Button btnBindingsPackage;
	private Button btnSuperClass;
	

	// default options selection
	private boolean scenariosBasedGeneration = true;
	private boolean requirementsBasedGeneration = false;
	
	private boolean considerPositiveRequirementsRelations = true;
	private boolean considerNegativeRequirementsRelations = false;
	private boolean considerAllUnknownRequirementsRelations = false;

	/*
	 * If true then the generator will ONLY combine a requirement with a scenario if this 
	 * scenario explicitly references this requirement (by <<UseToVerify>> relation) or
	 * if there is no scenario at all that references this requirement either by 
	 * positive (<<UseToVerify>> relation) or negative (<<DoNotUseToVerify>> relation)
	 */
	private boolean minimizeNumberOfRequirementInstantiations = false;
	
	
	// scenario based models generator
	private GeneratorVeMScenariosBased smg;

	// scenario based models generator
	private GeneratorVeMRequirementsBased rmg;
	
	
	private static final String defaultNegativeString = "Not specified ... ";

	private static final String selectionHintMessage = "Click on an element in the model explorer ... ";
	private static final String notValidString = "Not valid. ";
	
	
	// only for debugging
//	public static void main(String[] args) {
//		VerificationModelsGenerationOptionsDialog dialog = new VerificationModelsGenerationOptionsDialog(new Shell(), null, null, null, null, null);
//		dialog.open();
//	}

	
	public VeMGenerationOptionsDialog(Shell parentShell,
			Element rootPackage,
			Element systemModel,
			Element targetPackage,
			Element requirementsPackage,
			Element scenariosPackage,
			Element bindingsPackage,
			int mode) {

		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP );
		
		this.rootPackage = rootPackage;
		this.systemModel = systemModel;
		this.targetPackage = targetPackage;
		this.requirementsPackage = requirementsPackage;
		this.scenariosPackage = scenariosPackage;
		this.bindingsPackage = bindingsPackage;
		
		this.mode = mode;
		
		// set the mode the dialog message and preset options
		if (getMode()== Constants.MODE_VEM_GENERATION) {
			dialogMessage = messageVemGen;
			
			considerPositiveRequirementsRelations = true;
			considerNegativeRequirementsRelations = false;
			considerAllUnknownRequirementsRelations = false;
			minimizeNumberOfRequirementInstantiations = false;
		}
		else if (getMode() == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY){
			dialogMessage = messageScenarioToReqRelationDiscovery;
			
			considerPositiveRequirementsRelations = true;
			considerNegativeRequirementsRelations = true;
			considerAllUnknownRequirementsRelations = true;
			minimizeNumberOfRequirementInstantiations = false;
		}
		else if (getMode() == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			dialogMessage = messageAutomaticScenarioBasedVerification;

			// actually this does not matter here ...
			considerPositiveRequirementsRelations = true;
			considerNegativeRequirementsRelations = true;
			considerAllUnknownRequirementsRelations = true;
			minimizeNumberOfRequirementInstantiations = true;
		}
		
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
	}

//	@Override
//	public Shell getShell() {
//		return getParentShell();
//	}

	public Shell getDialogShell() {
		return getParentShell();
	}

	
	@Override
	protected Control createContents(Composite parent) {
		
		Control control = super.createContents(parent);
		
		// pre-settings: If no system model is selected then activate the button
		if (getSystemModel() == null) {
			btnSystemModel.setSelection(true);
			updateButton(btnSystemModel);
		}
		
		// validate the selection and enable/disable the ok button.
    	setOKButtonEnablement();
    	
		return control;
	}
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.horizontalSpacing = 1;
		container.setLayout(gl_container);
		
		CLabel lblInfo = new CLabel(container, SWT.NONE);
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		GridData gd_lblInfo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_lblInfo.widthHint = 750;
		lblInfo.setLayoutData(gd_lblInfo);
		lblInfo.setTopMargin(5);
		lblInfo.setBottomMargin(5);
		lblInfo.setRightMargin(5);
		lblInfo.setLeftMargin(5);
		lblInfo.setText(dialogMessage);
		
		Group grpSources = new Group(container, SWT.SHADOW_ETCHED_IN);
		GridData gd_grpSources = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_grpSources.heightHint = 209;
		gd_grpSources.widthHint = 614;
		grpSources.setLayoutData(gd_grpSources);
		grpSources.setText("Sources");
		
		// selection hint
		lblSelectionHint = new CLabel(grpSources, SWT.BORDER | SWT.SHADOW_OUT);
		lblSelectionHint.setTouchEnabled(true);
		lblSelectionHint.setToolTipText(selectionHintMessage);
		lblSelectionHint.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		lblSelectionHint.setBounds(67, 10, 410, 41);
		lblSelectionHint.setText(selectionHintMessage);
		lblSelectionHint.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png"));
		lblSelectionHint.setVisible(false);
		
		/*
		 * System Model Selection
		 */
		btnSystemModel = new Button(grpSources, SWT.TOGGLE | SWT.CENTER);
		btnSystemModel.setToolTipText("Click on this button to enable selection of another element.");
		btnSystemModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButton(btnSystemModel);
			}
		});
		btnSystemModel.setBounds(10, 17, 37, 24);
		
		Label lblSystemModel = new Label(grpSources, SWT.NONE);
		lblSystemModel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblSystemModel.setBounds(53, 22, 553, 15);

		// add to lists
		buttons.add(btnSystemModel);
		maptButtonsToLabels.put(btnSystemModel, lblSystemModel);
		// update the image and the label text
		updateButton(btnSystemModel);
		
		/*
		 * Target Package Selection
		 */
		btnTargetPackage = new Button(grpSources, SWT.TOGGLE | SWT.CENTER);
		btnTargetPackage.setToolTipText("Click on this button to enable selection of another element.");
		btnTargetPackage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButton(btnTargetPackage);
			}
		});
		btnTargetPackage.setBounds(10, 57, 37, 24);
		
		Label lblTargetPackage = new Label(grpSources, SWT.NONE);
		lblTargetPackage.setBounds(53, 62, 553, 15);

		// add to lists
		buttons.add(btnTargetPackage);
		maptButtonsToLabels.put(btnTargetPackage, lblTargetPackage);
		// update the image and the label text
		updateButton(btnTargetPackage);
		
		
		/*
		 * Requirements Package Selection
		 */
		btnRequirementsPackage = new Button(grpSources, SWT.TOGGLE | SWT.CENTER);
		btnRequirementsPackage.setToolTipText("Click on this button to enable selection of another element.");
		btnRequirementsPackage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButton(btnRequirementsPackage);
			}
		});
		btnRequirementsPackage.setBounds(10, 87, 37, 24);
		
		Label lblRequirementsPackage = new Label(grpSources, SWT.NONE);
		lblRequirementsPackage.setBounds(53, 92, 553, 15);

		// add to lists
		buttons.add(btnRequirementsPackage);
		maptButtonsToLabels.put(btnRequirementsPackage, lblRequirementsPackage);
		// update the image and the label text
		updateButton(btnRequirementsPackage);
		
		/*
		 * Scenarios Package Selection
		 */
		btnScenariosPackage = new Button(grpSources, SWT.TOGGLE | SWT.CENTER);
		btnScenariosPackage.setToolTipText("Click on this button to enable selection of another element.");
		btnScenariosPackage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButton(btnScenariosPackage);
			}
		});
		btnScenariosPackage.setBounds(10, 117, 37, 24);
		
		Label lbScenariosPackage = new Label(grpSources, SWT.NONE);
		lbScenariosPackage.setBounds(53, 122, 553, 15);

		// add to lists
		buttons.add(btnScenariosPackage);
		maptButtonsToLabels.put(btnScenariosPackage, lbScenariosPackage);
		// update the image and the label text
		updateButton(btnScenariosPackage);
		
		/*
		 * Bindings Package Selection
		 */
		btnBindingsPackage = new Button(grpSources, SWT.TOGGLE | SWT.CENTER);
		btnBindingsPackage.setToolTipText("Click on this button to enable selection of another element.");
		btnBindingsPackage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButton(btnBindingsPackage);
			}
		});
		btnBindingsPackage.setBounds(10, 147, 37, 24);
		
		Label lbBindingsPackage = new Label(grpSources, SWT.NONE);
		lbBindingsPackage.setBounds(53, 152, 553, 15);

		// add to lists
		buttons.add(btnBindingsPackage);
		maptButtonsToLabels.put(btnBindingsPackage, lbBindingsPackage);
		// update the image and the label text
		updateButton(btnBindingsPackage);
		
		Label sep2 = new Label(grpSources, SWT.SEPARATOR | SWT.HORIZONTAL);
		sep2.setBounds(10, 177, 694, 2);
		
		
		/*
		 * Bindings Package Selection
		 */
		btnSuperClass = new Button(grpSources, SWT.TOGGLE | SWT.CENTER);
		btnSuperClass.setToolTipText("Click on this button to enable selection of another element.");
		btnSuperClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateButton(btnSuperClass);
			}
		});
		btnSuperClass.setBounds(10, 187, 37, 24);
		
		Label lbSuperClass = new Label(grpSources, SWT.NONE);
		lbSuperClass.setBounds(53, 192, 553, 15);

		// add to lists
		buttons.add(btnSuperClass);
		maptButtonsToLabels.put(btnSuperClass, lbSuperClass);
		// update the image and the label text
		updateButton(btnSuperClass);
		
		Label sep1 = new Label(grpSources, SWT.SEPARATOR | SWT.HORIZONTAL);
		sep1.setBounds(10, 47, 694, 2);
		
		
		
		// Options Group
		Group grpOptions = new Group(container, SWT.NONE);
		GridData gd_grpOptions = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_grpOptions.heightHint = 166;
		grpOptions.setLayoutData(gd_grpOptions);
		grpOptions.setText("Options");
		
		
		
		final Button btnScenariosBased = new Button(grpOptions, SWT.RADIO);
		btnScenariosBased.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnScenariosBased.getSelection()) {
					scenariosBasedGeneration = true;
				}
				else {
					scenariosBasedGeneration = false;
				}
			}
		});
		btnScenariosBased.setSelection(true);
		btnScenariosBased.setBounds(10, 26, 625, 16);
		btnScenariosBased.setText("Create models based on combinations of scenarios and requirements");
		if (mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			btnScenariosBased.setEnabled(false);
		}
		
		
		final Button btnRequirementsBased = new Button(grpOptions, SWT.RADIO);
		btnRequirementsBased.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnRequirementsBased.getSelection()) {
					requirementsBasedGeneration = true;
				}
				else {
					requirementsBasedGeneration = false;
				}
			}
		});
		btnRequirementsBased.setBounds(10, 139, 625, 16);
		btnRequirementsBased.setText("Create only one model containing the selected system model and all requirements that can be verified");
		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY) {
			btnRequirementsBased.setEnabled(false); 
		}
		if (mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			btnRequirementsBased.setEnabled(false);
		}
		
		
		
		final Button btnConsiderPositiveRelations = new Button(grpOptions, SWT.CHECK);
		btnConsiderPositiveRelations.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnConsiderPositiveRelations.getSelection()) {
					considerPositiveRequirementsRelations = true;
				}
				else {
					considerPositiveRequirementsRelations = false;
				}
			}
		});
		btnConsiderPositiveRelations.setSelection(considerPositiveRequirementsRelations);
		btnConsiderPositiveRelations.setBounds(20, 46, 615, 16);
		btnConsiderPositiveRelations.setText("For a scenario include all requirements referenced by 'UseToVerify'");
//		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY) {
//			btnConsiderPositiveRelations.setEnabled(false); 
//		}
		if (mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			btnConsiderPositiveRelations.setEnabled(false);
		}
		
		
		final Button btnConsiderNegativeRelations = new Button(grpOptions, SWT.CHECK);
		btnConsiderNegativeRelations.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnConsiderNegativeRelations.getSelection()) {
					considerNegativeRequirementsRelations = true;
				}
				else {
					considerNegativeRequirementsRelations = false;
				}
			}
		});
		btnConsiderNegativeRelations.setText("For a scenario exclude all requirements referenced by 'DoNotUseToVerify'");
		btnConsiderNegativeRelations.setBounds(20, 68, 615, 16);
		btnConsiderNegativeRelations.setSelection(considerNegativeRequirementsRelations);
//		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY) {
//			btnConsiderNegativeRelations.setEnabled(false); 
//		}
		if (mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			btnConsiderNegativeRelations.setEnabled(false); 
		}
		
		
		
		final Button btnConsiderAllRequirements = new Button(grpOptions, SWT.CHECK);
		btnConsiderAllRequirements.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnConsiderAllRequirements.getSelection()) {
					considerAllUnknownRequirementsRelations = true;
				}
				else {
					considerAllUnknownRequirementsRelations = false;
				}
			}
		});
		btnConsiderAllRequirements.setText("For a scenario include all requirements with unknown relations");
		btnConsiderAllRequirements.setBounds(20, 90, 615, 16);
		btnConsiderAllRequirements.setSelection(considerAllUnknownRequirementsRelations);
		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY) {
			btnConsiderAllRequirements.setEnabled(false); 
		}
		if (mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			btnConsiderAllRequirements.setEnabled(false); 
		}
		
		
		
		final Button btnMinimizeNumberOfRequirementInstantiations = new Button(grpOptions, SWT.CHECK);
		btnMinimizeNumberOfRequirementInstantiations.setBounds(20, 112, 694, 16);
		btnMinimizeNumberOfRequirementInstantiations.setText("Minimize the number of requirement instantiations");
		btnMinimizeNumberOfRequirementInstantiations.setSelection(minimizeNumberOfRequirementInstantiations);
		btnMinimizeNumberOfRequirementInstantiations.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnMinimizeNumberOfRequirementInstantiations.getSelection()) {
					setMinimizeNumberOfRequirementInstantiations(true);
				}
				else {
					setMinimizeNumberOfRequirementInstantiations(false);
				}
			}
		});
		if (!(mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION)) {
			btnMinimizeNumberOfRequirementInstantiations.setEnabled(false); 
		}
		
		
		// set images for all buttons
		updateButtonImages();
		
		
		return container;
	}
	

	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setMinimumSize(new Point(750, 500));
		super.configureShell(newShell);
//		super.configureShell(new Shell(getParentShell(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.ON_TOP | SWT.SHELL_TRIM));
		
		if (getMode()== Constants.MODE_VEM_GENERATION) {
			newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper","icons/new_testsuite.gif"));
			newShell.setText("Verirication Models Generation Options");
		}
		else if (getMode() == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY){
			newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper","icons/discoverRelations2.gif"));
			newShell.setText("Discovery of Scenarios to Requirements Relations: Models Generation Options");
		}
		else if (getMode() == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper","icons/automaticScenarioBasedVerification.gif"));
			newShell.setText("Automatic Scenario-Based Verification: Models Generation Options");
		}
//		else {
//			newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper","icons/new_testsuite.gif"));
//			newShell.setText("Simulation Models Generation");
//		}
	}
	
	
	@Override
	protected void okPressed() {

		if (isValidSelection()) {

			// remove listener
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);

			super.okPressed();
			
			/*
			 * Generate models based on valid combinations of the selected system model, scenarios and requirements that can be verified
			 */
			if (isScenariosBasedGeneration()) {
				
				HashSet<Element> systemModels = new HashSet<Element>();
				systemModels.add(getSystemModel());
				
//				ServicesRegistry  serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
//				TransactionalEditingDomain  editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
				TransactionalEditingDomain  editingDomain = EditorServices.getPapyrusEditingDomain();
				
				
				// -----------------------------------------------------
				// TEST: this is only for the validation of the algorithm. 
				// REMOVE after testing.
//				GenerateVerificationModels g = new GenerateVerificationModels(
//						getSystemModel(), 
//						getScenariosPackage(), 
//						getRequirementsPackage(), 
//						getBindingsPackage(), rootPackage, rootPackage);
//				
//				g.generateVeMs();
				// -----------------------------------------------------
				
				
				smg = new GeneratorVeMScenariosBased(
						rootPackage,
						systemModels, 
						getTargetPackge(), 
						getRequirementsPackage(), 
						getScenariosPackage(), 
						getBindingsPackage(), 
						getSuperClass(),
						isConsiderPositiveRequirementsRelations(), 
						isConsiderNegativeRequirementsRelations(), 
						isConsiderAllUnknownRequirementsRelations(),
						isMinimizeNumberOfRequirementInstantiations(),
						getMode());
				
				if (!smg.isTestSimulationModelGenerationCanceled()) {

					// execute 
					editingDomain.getCommandStack().execute(getCommand(editingDomain));
					
					if (getMode()== Constants.MODE_VEM_GENERATION) {
						// show log
						String msg = "Generation of Verification Models for '" + ((NamedElement)getSystemModel()).getName() + "'\n" +
								 "Number of created models: " + smg.getUserSelectedTestScenarios().size() + "\n\n";

						DialogMessage dialog = new DialogMessage(getShell(), "Verification Models Generation Log", 
							"Data collecation and models generation log entries:", msg + smg.getLog().trim(), false);
						dialog.open();
					}
				}
			}
			/*
			 * Generate only one model that contains the 
			 * the selected system model (i.e. a "self-running" system model that does not require a scenario), 
			 * NO scenario BUT all requirements that can be verified
			 */
			else if (isRequirementsBasedGeneration()) {
				HashSet<Element> systemModels = new HashSet<Element>();
				systemModels.add(getSystemModel());
				
				TransactionalEditingDomain editingDomain = EditorServices.getPapyrusEditingDomain();
				
				rmg = new GeneratorVeMRequirementsBased(
						systemModels, 
						getTargetPackge(), 
						getRequirementsPackage(), 
						getScenariosPackage(), 
						getBindingsPackage(), 
						getSuperClass(),
						isConsiderPositiveRequirementsRelations(), 
						isConsiderNegativeRequirementsRelations(), 
						isConsiderAllUnknownRequirementsRelations()
						);
				
				if (!rmg.isTestSimulationModelGenerationCanceled()) {

					// execute 
					editingDomain.getCommandStack().execute(getCommand(editingDomain));

					if (getMode()== Constants.MODE_VEM_GENERATION) {
						// show log
						String msg = "Generation of Verification Models for '" + ((NamedElement)getSystemModel()).getName() + "'\n" +
									 "Number of created models: 1\n\n";

						DialogMessage dialog = new DialogMessage(getShell(), "Verification Models Generation Log", 
								"Data collecation and models generation log entries:", msg + rmg.getLog().trim(), false);
						dialog.open();
					}
				}
			} 
			
			// dispose the shell
			getParentShell().dispose();

		}
	}
	

	protected Command getCommand(TransactionalEditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand("Generating simulation models for '" + ((Class)getSystemModel()).getQualifiedName() + "'");
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// TODO: implement the progress monitor correctly!  
				
				if (isScenariosBasedGeneration()) {
					// generate models
					smg.generate();
				}
				else if (isRequirementsBasedGeneration()) {
					rmg.generate();
				}
				
				
//				try {
//					if (isScenariosBasedGeneration()) {
//						// generate models
//						smg.generate();
//						new ProgressMonitorDialog(getShell()).run(true, true, smg);
//
////						smg.notifyObservers();
//						// set the indication of there were errors during bindings generation
////						setBindingErrorsDetected(smg.isBindingErrorsDetected());
//					}
//					else if (isRequirementsBasedGeneration()) {
//						rmg.generate();
//						new ProgressMonitorDialog(getShell()).run(true, true, rmg);
//						
////						rmg.notifyObservers();
//						// set the indication of there were errors during bindings generation
////						setBindingErrorsDetected(rmg.isBindingErrorsDetected());
//					}
//					
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//					MessageDialog.openError(getShell(), "Simulation Models Generation Process Error", "It was not possible to invoke the generation of simulation models operation.");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//					MessageDialog.openError(getShell(), "Simulation Models Generation Process Abort", "The generation of simulation models was canceled.");
//				}
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	
	protected ISelectionListener selectionListener = new ISelectionListener() {
        public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
        if (sourcepart != VeMGenerationOptionsDialog.this && selection instanceof IStructuredSelection) {

        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
        		EObject selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));

      			Button button = getActiveButton();
      			if (button != null && !button.isDisposed()) {
      				if (button.equals(btnSystemModel)) {
            			// Update data and button
      					
      					if (selectedElement instanceof Class && 
      							( 
      								((Class) selectedElement).getAppliedStereotype(Constants.stereotypeQName_VerificationModel) != null 
      								|| ((Class) selectedElement).getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null
      								|| ((Class) selectedElement).getAppliedStereotype(Constants.stereotypeQName_Function) != null
      								|| ((Class) selectedElement).getAppliedStereotype(Constants.stereotypeQName_Record) != null
      								|| ((Class) selectedElement).getAppliedStereotype(Constants.stereotypeQName_Requirement) != null
      								
      							)
      						){
      								
      						setSelectionHintMode(true, notValidString + "Verification models, scenarios, requirements, functions or records are not allowed.");
      						
      					}
      					else if (selectedElement instanceof Class && hasModelicaMLStereotype((Element) selectedElement)) {
            				setSystemModel((Element) selectedElement);
            				button.setSelection(false);
            				updateButton(button);
    					}
            			else {
            				setSelectionHintMode(true, notValidString + "Only ModelicaML classes are allowed.");
            			}
    				}
            		else if (button.equals(btnTargetPackage)) {
    					// Update data and button 
            			if (selectedElement instanceof Package) {
            				setTargetPackge((Element) selectedElement);
            				button.setSelection(false);
            				updateButton(button);
    					}
            			else {
            				setSelectionHintMode(true, notValidString + "Only packages are allowed.");
            			}
    				}
            		else if (button.equals(btnRequirementsPackage)) {
    					// Update data and button 
            			if (selectedElement instanceof Package) {
            				setRequirementsPackage((Element) selectedElement);
            				button.setSelection(false);
            				updateButton(button);
    					}
            			else {
            				setSelectionHintMode(true, notValidString + "Only packages are allowed.");
            			}
    				}
            		else if (button.equals(btnScenariosPackage)) {
    					// Update data and button 
            			if (selectedElement instanceof Package) {
            				setScenariosPackage((Element) selectedElement);
            				button.setSelection(false);
            				updateButton(button);
    					}
            			else {
            				setSelectionHintMode(true, notValidString + "Only packages are allowed.");
            			}
    				}
            		else if (button.equals(btnBindingsPackage)) {
    					// Update data and button 
            			if (selectedElement instanceof Package) {
            				setBindingsPackage((Element) selectedElement);
            				button.setSelection(false);
            				updateButton(button);
    					}
            			else {
            				setSelectionHintMode(true, notValidString + "Only packages are allowed.");
            			}
    				}
            		else if (button.equals(btnSuperClass)) {
            			// Update data and button
            			if (selectedElement instanceof Class && hasModelicaMLStereotype((Element) selectedElement)) {
    						// Check ModelicaML Stereotype? 
            				setSuperClass((Element) selectedElement);
            				button.setSelection(false);
            				updateButton(button);
    					}
            			else {
            				setSelectionHintMode(true, notValidString + "Only ModelicaML classes are allowed.");
            			}
    				}
				}
			}
        	
        	// validate the selection and enable/disable the ok button.
        	setOKButtonEnablement();
        }
    }
	};
	
	
	
	/* ************************************************************************************************ */
	
	// Utilities

	@SuppressWarnings("unchecked")
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
		return null;
	}
	
	
	protected EObject adaptSelectedElement( Object selection ) {
		EObject eObject = null;
		if(selection != null) {
			
			if (selection instanceof org.openmodelica.modelicaml.common.instantiation.TreeParent) { // this is an object from components tree view plugin
				return ((org.openmodelica.modelicaml.common.instantiation.TreeParent)selection).getProperty();
			}
			if(selection instanceof IAdaptable) {
				selection = ((IAdaptable)selection).getAdapter(EObject.class);
			}
			Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(selection);
			if(businessObject instanceof EObject) {
				eObject = (EObject)businessObject;
			}
		}
		return eObject;
	}
	
	private boolean hasModelicaMLStereotype(Element element){
		EList<Stereotype> sList = element.getAppliedStereotypes();
		for (Stereotype stereotype : sList) {
			if (stereotype.getQualifiedName().startsWith(Constants.modelingLanguageName)) {
				return true;
			}
		}
		return false;
	}
	
	private void setSelectionHintMode(boolean isError, String message){
		if (!isError) { //normal
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			lblSelectionHint.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png"));
			lblSelectionHint.setText(message);
		}
		if (isError) { // error
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			lblSelectionHint.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/icons16/error_log.gif"));
//			lblSelectionHint.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEC_FIELD_ERROR));
			lblSelectionHint.setText(message);
		}
	}
	
	private void setOKButtonEnablement(){
		if (isValidSelection() && getButton(IDialogConstants.OK_ID) != null && !getButton(IDialogConstants.OK_ID).isDisposed()) {
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
		else if (getButton(IDialogConstants.OK_ID) != null  && !getButton(IDialogConstants.OK_ID).isDisposed()) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}
	}


	private boolean isValidSelection(){
		if (systemModel != null && targetPackage != null && requirementsPackage != null && scenariosPackage != null && bindingsPackage != null) {
//			getButton(IDialogConstants.OK_ID).setEnabled(true);
			return true;
		}
		return false;
	}
	
	private String getName(Element el){
		String name = defaultNegativeString;
		if (el == null) {
			return name;
		}
		if (el instanceof NamedElement) {
			String elName = ((NamedElement) el).getName();
			if (elName != null) {
				name = elName;
			}
		}
		return name;
	}
	
	private String getQName(Element el){
		String name = defaultNegativeString;
		if (el == null) {
			return name;
		}
		if (el instanceof NamedElement) {
			String elName = ((NamedElement) el).getQualifiedName();
			if (elName != null) {
				name = elName;
			}
		}
		return name;
	}
	
	private Button getActiveButton(){
		Button activeButton = null;
		for (Button button : buttons) {
			if (button!= null && !button.isDisposed() && button.getSelection()) {
				return button;
			}
		}
		
		return activeButton;
	}
	
	private void updateButton(Button button){
		
		if (button.getSelection()) {

			// Deactivate other buttons
			deactivateOtherButtons(button);

			// show hint
			lblSelectionHint.setBounds(getActiveButton().getBounds().x + 40, getActiveButton().getBounds().y - 3, lblSelectionHint.getBounds().width, lblSelectionHint.getBounds().height);
			lblSelectionHint.setVisible(true);
		}
		else {
			lblSelectionHint.setVisible(false);
			setSelectionHintMode(false, getButtonSelectionHint(button));
		}
		
		// get a specific selection hint message for button
		setSelectionHintMode(false, getButtonSelectionHint(button));
		
		updateButtonLabelText(button);
	}
	

	private String getButtonSelectionHint(Button button){
		
		String msg = selectionHintMessage;
		
		if (button.getSelection()) {
			
			if (button.equals(btnSystemModel)) {
				return systemModelSelectionMessage;
			}
			else if (button.equals(btnTargetPackage)) {
				return targetPackgeSelectionMessage;
			}
			else if (button.equals(btnRequirementsPackage)) {
				return requirementsPackageSelectionMessage;
			}
			else if (button.equals(btnScenariosPackage)) {
				return scenariosPackageSelectionMessage;
			}
			else if (button.equals(btnBindingsPackage)) {
				return bindingsPackageSelectionMessage;
			}
			else if (button.equals(btnSuperClass)) {
				return superClassSelectionMessage;
			}
		}
		
		return msg;
	}

	private void highlightLabels(String string, Button button){
		// if the name is empty 
		if (string.equals(defaultNegativeString)) {
			// red font color
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			maptButtonsToLabels.get(button).setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
		else {
			lblSelectionHint.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			maptButtonsToLabels.get(button).setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		}
	}
	
	private void updateButtonLabelText(Button button){
		
		String elementName = defaultNegativeString;
		String elementQName = defaultNegativeString;
		String elementNameMsg;
		
		if (button.equals(btnSystemModel)) {
			elementName = getName(systemModel);
			elementNameMsg = systemModelNamePrefix + getName(systemModel);
			maptButtonsToLabels.get(btnSystemModel).setText(elementNameMsg);
			elementQName = getQName(systemModel);
			maptButtonsToLabels.get(btnSystemModel).setToolTipText(elementQName);
			
			// check if the name is empty. if yes, highlight the label
			highlightLabels(elementName, button);
		}
		else if (button.equals(btnTargetPackage)) {
			elementName = getName(targetPackage);
			elementNameMsg = targetPackgeNamePrefix + getName(targetPackage);
			maptButtonsToLabels.get(btnTargetPackage).setText(elementNameMsg);
			elementQName = getQName(targetPackage);
			maptButtonsToLabels.get(btnTargetPackage).setToolTipText(elementQName);
			
			// check if the name is empty. if yes, highlight the label
			highlightLabels(elementName, button);
		}
		else if (button.equals(btnRequirementsPackage)) {
			elementName = getName(requirementsPackage);
			elementNameMsg = requirementsPackageNamePrefix + getName(requirementsPackage);
			maptButtonsToLabels.get(btnRequirementsPackage).setText(elementNameMsg);
			elementQName = getQName(requirementsPackage);
			maptButtonsToLabels.get(btnRequirementsPackage).setToolTipText(elementQName);
			
			// check if the name is empty. if yes, highlight the label
			highlightLabels(elementName, button);
		}
		else if (button.equals(btnScenariosPackage)) {
			elementName = getName(scenariosPackage);
			elementNameMsg = scenariosPackageNamePrefix + getName(scenariosPackage);
			maptButtonsToLabels.get(btnScenariosPackage).setText(elementNameMsg);
			elementQName = getQName(scenariosPackage);
			maptButtonsToLabels.get(btnScenariosPackage).setToolTipText(elementQName);

			// check if the name is empty. if yes, highlight the label
			highlightLabels(elementName, button);

		}
		else if (button.equals(btnBindingsPackage)) {
			elementName = getName(bindingsPackage);
			elementNameMsg = bindingsPackageNamePrefix + getName(bindingsPackage);
			maptButtonsToLabels.get(btnBindingsPackage).setText(elementNameMsg);
			elementQName = getQName(bindingsPackage);
			maptButtonsToLabels.get(btnBindingsPackage).setToolTipText(elementQName);
			
			// check if the name is empty. if yes, highlight the label
			highlightLabels(elementName, button);
		}
		
		else if (button.equals(btnSuperClass)) {
			elementName = getName(superClass);
			elementNameMsg = superClassNamePrefix + getName(superClass);
			maptButtonsToLabels.get(btnSuperClass).setText(elementNameMsg);
			elementQName = getQName(superClass);
			maptButtonsToLabels.get(btnSuperClass).setToolTipText(elementQName);

			// check if the name is empty. if yes, highlight the label
			// optional -> do not highlight if not set.
//			highlightLabels(elementName, button);
		}
	}

	private void updateButtonImages(){
		for (Button button : buttons) {
			if (button.equals(btnSystemModel)) {
				button.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/model.gif"));
			}
			else if (button.equals(btnTargetPackage)) {
				button.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/UMLPackage.gif"));
			}
			else if (button.equals(btnRequirementsPackage)) {
				button.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/UMLPackage.gif"));
			}
			else if (button.equals(btnScenariosPackage)) {
				button.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/UMLPackage.gif"));
			}
			else if (button.equals(btnBindingsPackage)) {
				button.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/UMLPackage.gif"));
			}
			else if (button.equals(btnSuperClass)) {
				button.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/model.gif"));
			}
		}
	}
	
	
	private void deactivateOtherButtons(Button activeButton){
		for (Button button : buttons) {
			if (!button.equals(activeButton)) {
				button.setSelection(false);
			}
		}
	}

//	private String getLastSegment(String string, String separator){
//		if (string != null) {
//			String[] splitted = string.split(separator);
//			return splitted[splitted.length - 1];
//		}
//		return string;
//	}
	
	
	
	
	/* ************************************************************************************************ */
	// getter and setter
	
	public Element getSystemModel() {
		return systemModel;
	}

	public void setSystemModel(Element systemModel) {
		this.systemModel = systemModel;
	}
	
	public Element getTargetPackge() {
		return targetPackage;
	}

	public void setTargetPackge(Element targetPackge) {
		this.targetPackage = targetPackge;
	}

	public Element getRequirementsPackage() {
		return requirementsPackage;
	}

	public void setRequirementsPackage(Element requirementsPackage) {
		this.requirementsPackage = requirementsPackage;
	}

	public Element getScenariosPackage() {
		return scenariosPackage;
	}

	public void setScenariosPackage(Element scenariosPackage) {
		this.scenariosPackage = scenariosPackage;
	}

	public Element getBindingsPackage() {
		return bindingsPackage;
	}

	public void setBindingsPackage(Element bindingsPackage) {
		this.bindingsPackage = bindingsPackage;
	}

	public Element getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Element superClass) {
		this.superClass = superClass;
	}
	
	public boolean isScenariosBasedGeneration() {
		return scenariosBasedGeneration;
	}

	public void setScenariosBasedGeneration(boolean scenariosBasedGeneration) {
		this.scenariosBasedGeneration = scenariosBasedGeneration;
	}

	public boolean isRequirementsBasedGeneration() {
		return requirementsBasedGeneration;
	}

	public void setRequirementsBasedGeneration(boolean requirementsBasedGeneration) {
		this.requirementsBasedGeneration = requirementsBasedGeneration;
	}

	public boolean isConsiderPositiveRequirementsRelations() {
		return considerPositiveRequirementsRelations;
	}

	public void setConsiderPositiveRequirementsRelations(
			boolean considerPositiveRequirementsRelations) {
		this.considerPositiveRequirementsRelations = considerPositiveRequirementsRelations;
	}

	public boolean isConsiderNegativeRequirementsRelations() {
		return considerNegativeRequirementsRelations;
	}

	public void setConsiderNegativeRequirementsRelations(
			boolean considerNegativeRequirementsRelations) {
		this.considerNegativeRequirementsRelations = considerNegativeRequirementsRelations;
	}

	public boolean isConsiderAllUnknownRequirementsRelations() {
		return considerAllUnknownRequirementsRelations;
	}

	public void setConsiderAllUnknownRequirementsRelations(
			boolean considerAllUnknownRequirementsRelations) {
		this.considerAllUnknownRequirementsRelations = considerAllUnknownRequirementsRelations;
	}
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
//	public boolean isBindingErrorsDetected() {
//		return bindingErrorsDetected;
//	}
//
//	public void setBindingErrorsDetected(boolean bindingErrorsDetected) {
//		this.bindingErrorsDetected = bindingErrorsDetected;
//	}
	
	public GeneratorVeMScenariosBased getSmg() {
		return smg;
	}
	
	public boolean isMinimizeNumberOfRequirementInstantiations() {
		return minimizeNumberOfRequirementInstantiations;
	}

	public void setMinimizeNumberOfRequirementInstantiations(
			boolean minimizeNumberOfRequirementInstantiations) {
		this.minimizeNumberOfRequirementInstantiations = minimizeNumberOfRequirementInstantiations;
	}
}
