package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.report.XMLReportGenerator;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.eclipse.swt.widgets.Label;

public class SelectScenarioToReqRelationsToCreateDialog extends TitleAreaDialog {

	private GeneratedModelsData gmd;
	
	// positive scenario to requirements relations, i.e., scenario was used to stimulate the system mode and lead to an evaluation of the requirements
	private HashMap<Element,HashSet<Element>> positiveRelationsToCreate = new HashMap<Element,HashSet<Element>>();

	// negative scenario to requirements relations, i.e., scenario was used to stimulate the system mode and did NOT lead to an evaluation of the requirements
	private HashMap<Element,HashSet<Element>> negativeRelationsToCreate = new HashMap<Element,HashSet<Element>>();
	
	// positive scenario to requirements relations, i.e., scenario was used to stimulate the system mode and lead to an evaluation of the requirements
	private HashMap<Element,HashSet<Element>> positiveRelationsToDelete = new HashMap<Element,HashSet<Element>>();

	// negative scenario to requirements relations, i.e., scenario was used to stimulate the system mode and did NOT lead to an evaluation of the requirements
	private HashMap<Element,HashSet<Element>> negativeRelationsToDelete = new HashMap<Element,HashSet<Element>>();

	
	/*  All tree items to iterate over when required.
	 *  Note, this should be an ordered list in order to enable a simple run through when "SelectAll"/"DeselectAll" is clicked 
	 */
	private List<TreeItem> treeItemsPositiveRelations = new ArrayList<TreeItem>();
	private List<TreeItem> treeItemsNegativeRelations = new ArrayList<TreeItem>();
	
	private final ImageDescriptor warningImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	private final ImageDescriptor errorImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);

	private final static String TAB_TITLE_POSITIVE_RELATIONS = "New Positive Relations";
	private final static String TAB_TITLE_NEGATIVE_RELATIONS = "New Negative Relations";
	
	
	private TabFolder tabFolder;
	private Button btnSave;
	
	
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectScenarioToReqRelationsToCreateDialog(Shell parentShell, GeneratedModelsData gmd) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		this.gmd = gmd;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/correlationset.gif"));
		newShell.setText("New Relations from Scenarios to Requirements");
	}
	
	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		setMessage("Note that you can select all children by selecting the parent item.");
		setTitle("Select relations that should created between scenarios and requirements.");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(7, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1);
		gd_tabFolder.widthHint = 626;
		tabFolder.setLayoutData(gd_tabFolder);

		/*
		 * TAB Negative relations
		 */
		TabItem tbtmnewPositiveRelations = new TabItem(tabFolder, SWT.NONE);
		String metricPreSelectedPositiveRelations = "(" + gmd.getNewRelationsCount(true) + ")";
		tbtmnewPositiveRelations.setText(TAB_TITLE_POSITIVE_RELATIONS + " " + metricPreSelectedPositiveRelations);
		
		final Tree treePreSelectedNewPositiveRelations = new Tree(tabFolder, SWT.CHECK);
		buildTree(treePreSelectedNewPositiveRelations, true, true);
		
		// Add listeners
		treePreSelectedNewPositiveRelations.addSelectionListener(new CheckboxTreeSelectionListener());
		tbtmnewPositiveRelations.setControl(treePreSelectedNewPositiveRelations);
		
		/*
		 * TAB: Negative relations
		 */
		TabItem tbtmNewNegativeRelations = new TabItem(tabFolder, SWT.NONE);
		String metricNegativeRelations = "(" + gmd.getNewRelationsCount(false) + ")";
		tbtmNewNegativeRelations.setText(TAB_TITLE_NEGATIVE_RELATIONS + " " + metricNegativeRelations);
		
		final Tree treeNewNegativeRelations = new Tree(tabFolder, SWT.CHECK);
		buildTree(treeNewNegativeRelations, false, false);
		
		// Add listeners
		treeNewNegativeRelations.addSelectionListener(new CheckboxTreeSelectionListener());
		tbtmNewNegativeRelations.setControl(treeNewNegativeRelations);
		
		
		/*
		 * TAB: Not simulated models
		 */
		TabItem tbtmNotSimulated = new TabItem(tabFolder, SWT.NONE);
		tbtmNotSimulated.setText("Errors (0)");
		final Tree failedSimulationModelsTree = new Tree(tabFolder, SWT.NONE);
		createNotSimulatedItems(failedSimulationModelsTree);
		tbtmNotSimulated.setControl(failedSimulationModelsTree);
		
				
		Button btnSelectAll = new Button(container, SWT.NONE);
		btnSelectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (isPositiveRelationsTabActive(tabFolder)) {
					for (TreeItem treeItem : treeItemsPositiveRelations) {
						treeItem.setChecked(true);
						updateFinalLists(treeItem);
					}
				}
				else if (isNegativeRelationsTabActive(tabFolder)) {
					for (TreeItem treeItem : treeItemsNegativeRelations) {
						treeItem.setChecked(true);
						updateFinalLists(treeItem);
					}
				}
				
			}
		});
		btnSelectAll.setText("Select All");
		
		Button btnDeselectAll = new Button(container, SWT.NONE);
		btnDeselectAll.setEnabled(true);
		btnDeselectAll.setText("Deselect All");
		btnDeselectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (isPositiveRelationsTabActive(tabFolder)) {
					for (TreeItem treeItem : treeItemsPositiveRelations) {
						treeItem.setChecked(false);
						updateFinalLists(treeItem);
					}
				}
				else if (isNegativeRelationsTabActive(tabFolder)) {
					for (TreeItem treeItem : treeItemsNegativeRelations) {
						treeItem.setChecked(false);
						updateFinalLists(treeItem);
					}
				}
			}
		});
		
		
		
		
		
		
//		Button btnSeeLog = new Button(container, SWT.NONE);
//		btnSeeLog.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseDown(MouseEvent e) {
//				String infoText = "Data Collection for Simulation Models Generation Log: ";
//				DialogMessage dialog = new DialogMessage(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Result", infoText, collectionLog);
//				dialog.open();
//			}
//		});
//		btnSeeLog.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/log.png"));
//		btnSeeLog.setBounds(558, 331, 68, 23);
//		btnSeeLog.setText("Log");
		
		Button btnRestore = new Button(container, SWT.NONE);
		btnRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				treePreSelectedNewPositiveRelations.removeAll();
				treeNewNegativeRelations.removeAll();
				
				clearAllLists();
				
				buildTree(treePreSelectedNewPositiveRelations, true, true);
				buildTree(treeNewNegativeRelations, false, false);
			}
		});
		btnRestore.setImage(ResourceManager.getPluginImage("org.eclipse.emf.common.ui", "/org/eclipse/emf/common/ui/Restore.gif"));
		btnRestore.setText("Restore");
		
		Composite composite = new Composite(container, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.widthHint = 311;
		gd_composite.heightHint = 20;
		composite.setLayoutData(gd_composite);
		
		/*
		 * Save as File 
		 */
		new Label(container, SWT.NONE);
		Button btnSaveasfile = new Button(container, SWT.NONE);
		btnSaveasfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				ExtendedUmlModel umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
				if (umlModel != null) {

					// get project data
					String projectName = umlModel.getResource().getURI().segment(1);
//					IWorkspace workspace = ResourcesPlugin.getWorkspace();
//					IWorkspaceRoot root = workspace.getRoot();
//					IProject iProject = root.getProject(projectName);
					
					// set folder name and file name
					String folderName = Constants.folderName_relationsDiscovery;
					String fileName = Constants.fileName_relationsDiscovery + "_" + System.currentTimeMillis() + ".xml";
					
					// create report
					XMLReportGenerator reportGenerator = new XMLReportGenerator(gmd, XMLReportGenerator.XMLContent);
					String filePath = reportGenerator.createFile(projectName, folderName, fileName, false);
					
					if (filePath != null) {
						String message = "The file was stored: \n"+filePath+"";
						DialogMessage dialog = new DialogMessage(new Shell(), "Report Generation", "", message, false);
						dialog.open();
					}
				}
				else {
					MessageDialog.openError(new Shell(), "Report Generation", "Could not access the ModelicaML model in order to determine the project name. Please open a Papyrus model. ");
				}
			}
		});
		btnSaveasfile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnSaveasfile.setText("Create Report");
		btnSaveasfile.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "/icons/report.gif"));
		
		
		/*
		 * Store dependencies 
		 */
		btnSave = new Button(container, SWT.NONE);
		btnSave.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// save relations
				storeRelations();
			}
		});
//		btnSave.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/etool16/saveall_edit.gif"));
		btnSave.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/Dependency.gif"));
		btnSave.setText("Save Realations");

		return area;
	}

	
	
	
//	private HashSet<Element> geAllSelectedRequirement(){
//		HashSet<Element> selectedRequirements = new HashSet<Element>();
//		
//		for (Element element: positiveRelationsToCreate.keySet()) {
//			HashSet<Element> values = positiveRelationsToCreate.get(element);
//			if (values != null ) {
//				selectedRequirements.addAll(values);
//			}
//		}
//		
//		for (Element element: negativeRelationsToCreate.keySet()) {
//			HashSet<Element> values = negativeRelationsToCreate.get(element);
//			if (values != null ) {
//				selectedRequirements.addAll(values);
//			}
//		}
//		
//		return selectedRequirements;
//	}
	
	private Package getGeneratedDependenciesOwner(Element dependencySource){
		Package owner = dependencySource.getNearestPackage();
		
		Package generatedDependenciesOwner = null;
		
		// find an existing one
		for (Element element : owner.getOwnedElements()) {
			if (element instanceof Package && ((Package)element).getName().equals(Constants.relationDiscoveryDependeciesPackageName)) {
				generatedDependenciesOwner = (Package) element;
				return generatedDependenciesOwner;
			}
		}
		
		// create a new one
		if (generatedDependenciesOwner == null) {
			if (owner instanceof Package) {
				
			}
			generatedDependenciesOwner = owner.createNestedPackage(Constants.relationDiscoveryDependeciesPackageName, UMLPackage.Literals.PACKAGE);
		}
		return generatedDependenciesOwner;
	}
	
	private String getNewDependencyName(String stereotypeQName){
		String dependencyName = "NotDefined";
		String[] splitted = stereotypeQName.split("::");
		if (splitted != null && splitted.length	> 0) {
			dependencyName = splitted[splitted.length - 1];
		}
		else {
			dependencyName = stereotypeQName;
		}
		
		return dependencyName;
	}
	
	
	private void storeDependencies(HashMap<Element, HashSet<Element>> map, String stereoTypeQName){
		for (Element scenario : map.keySet()) {
			String dependencyName = getNewDependencyName(stereoTypeQName);
			 
			if (scenario instanceof NamedElement) {
				HashSet<Element> requirements = map.get(scenario);
				if (requirements != null) {
					for (Element requirement : requirements) {

						if (gmd.isNewRelation(scenario, requirement, stereoTypeQName)) {
//							Dependency dependency = ((NamedElement)scenario).createDependency((NamedElement) requirement);
							Dependency dependency = (Dependency) getGeneratedDependenciesOwner(scenario).createPackagedElement(dependencyName, UMLPackage.Literals.DEPENDENCY);

							// Set the source and target
							dependency.getClients().add((NamedElement) scenario);
							dependency.getSuppliers().add((NamedElement) requirement);
							
							// apply stereotype
							Stereotype stereotype = dependency.getApplicableStereotype(stereoTypeQName);
							if (stereotype != null) {
								dependency.applyStereotype(stereotype);
							}
						}
					}
				}
			}
		}
	}
	
	private void removeDependencies(HashMap<Element, HashSet<Element>> map, String stereoTypeQName){
		for (Element scenario : map.keySet()) {
			 
			if (scenario instanceof NamedElement) {
				HashSet<Element> requirements = map.get(scenario);
				if (requirements != null) {
					for (Element requirement : requirements) {
						
						Dependency exisitngDependency = gmd.getExistingDependency(scenario, requirement, stereoTypeQName);
						if (exisitngDependency != null) {
							exisitngDependency.destroy();
						}
					}
				}
			}

			// remove the generated package if it is empty
			Package dependenciesOwningPackage = getGeneratedDependenciesOwner(scenario);
			if (dependenciesOwningPackage.getOwnedElements().size() == 0) {
				dependenciesOwningPackage.destroy();
			}
		}
		
	}
	
	protected Command getCommand(TransactionalEditingDomain editingDomain) {
		CompoundCommand cc = new CompoundCommand("Storing discovered relations between scenarios and requirements");
		Command command = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				storeDependencies(positiveRelationsToCreate, Constants.stereotypeQName_UsedToVerify);
				storeDependencies(negativeRelationsToCreate, Constants.stereotypeQName_DoNotUseToVerify);
				
				removeDependencies(positiveRelationsToDelete, Constants.stereotypeQName_UsedToVerify);
				removeDependencies(negativeRelationsToDelete, Constants.stereotypeQName_DoNotUseToVerify);
			}
		};
		cc.append(command);
		return (cc.unwrap());
	}
	
	
	private void storeRelations(){
		TransactionalEditingDomain  editingDomain = PapyrusServices.getPapyrusEditingDomain();
		if (editingDomain != null) {
			// execute 
			editingDomain.getCommandStack().execute(getCommand(editingDomain));
		}
		else {
			MessageDialog.openError(new Shell(), "Store Discovered Realations Error", "Could not access the editing domain.");
		}
		btnSave.setEnabled(false);
	}
	
	
	
//	private void printMapString(HashMap<Element,HashSet<Element>> map, String mapName){
//		for (Element key : map.keySet()) {
//			if (key instanceof NamedElement) {
//				String keyName = ((NamedElement)key).getName();
//				
//				System.err.println(mapName + ": "+ keyName);
//				
//				HashSet<Element> values = map.get(key);
//				for (Element element : values) {
//					if (element instanceof NamedElement) {
//						String valueName = ((NamedElement)element).getName();
//						
//						System.err.println("          - " + valueName);
//					}
//				}
//				System.err.println();
//			}
//		}
//	}
	
	
	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
//		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
//				true);
//		createButton(parent, IDialogConstants.CANCEL_ID,
//				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(797, 509);
	}

	
	private void buildTree(Tree treeRoot, boolean isPositiveRelationsTree, boolean isPreselected){

		
		if (isPositiveRelationsTree) {
			// if there are scenarios with new POSITIVE relations -> create tree
			if (gmd.getScenarioWithNewPositiveRelationsToRequirements().keySet().size() > 0 ) {
				
				// create package tree items
				List<Element> nearestPackages = ModelicaMLServices.getSortedByName( getNearestPackages( new HashSet<Element>(gmd.getScenarioWithNewPositiveRelationsToRequirements().keySet()) ) );
				createPkgTreeItems(treeRoot, nearestPackages, isPositiveRelationsTree, isPreselected);

				// create scenarios tree items
				List<Element> scenariosSorted = ModelicaMLServices.getSortedByName(new HashSet<Element>(gmd.getScenarioWithNewPositiveRelationsToRequirements().keySet()) );
				for (Element scenario : scenariosSorted) {
					if (scenario instanceof NamedElement) {
						createScenarioTreeItem(treeRoot, scenario, isPositiveRelationsTree, isPreselected);
					}
				}
			}
		}
		
		else {
			// if there are scenarios with new NEGATIVE relations -> create tree
			if (gmd.getScenarioWithNewNegativeRelationsToRequirements().keySet().size() > 0 ) {

				// create package tree items
				List<Element> nearestPackages = ModelicaMLServices.getSortedByName( getNearestPackages( new HashSet<Element>(gmd.getScenarioWithNewNegativeRelationsToRequirements().keySet()) ) );
				createPkgTreeItems(treeRoot, nearestPackages, isPositiveRelationsTree, isPreselected);

				// create scenarios tree items
				List<Element> scenariosSorted = ModelicaMLServices.getSortedByName( new HashSet<Element>(gmd.getScenarioWithNewNegativeRelationsToRequirements().keySet()) );
				for (Element scenario : scenariosSorted) {
					if (scenario instanceof NamedElement) {
						createScenarioTreeItem(treeRoot, scenario, isPositiveRelationsTree, isPreselected);
					}
				}
			}
		}
	}

	private HashSet<Element> getNearestPackages(HashSet<Element> testSimulationModels){
		HashSet<Element> pkgList = new HashSet<Element>();
		if (testSimulationModels != null ) {
			for (Element testScenario : testSimulationModels) {
				pkgList.add(testScenario.getNearestPackage());
			}
		}
		return pkgList;
	}
	
	private void createPkgTreeItems(Tree treeRoot, List<Element> pkgElements, boolean isPositiveRelationsTree, boolean isPreselected){
		// create package nodes at the 1 level
		for (Element pkg : pkgElements) {

			TreeItem pkgItem = new TreeItem(treeRoot, 0);
			
			if (isPositiveRelationsTree) {
				treeItemsPositiveRelations.add(pkgItem);
			}
			else {
				treeItemsNegativeRelations.add(pkgItem);
			}
			
			TreeItemData data = new TreeItemData();
			data.setIsPackage(true);
			data.setPackageElement(pkg);

			pkgItem.setData(data);
			
			pkgItem.setText(getModelName(pkg) + "  ("+getModelQName(pkg)+")");
			pkgItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
			
			// preselect
			pkgItem.setChecked(isPreselected);
		}
	}
	
	
	private void createNotSimulatedItems(Tree treeRoot){

		for (String modelQName : gmd.getSimulationFailedList()) {

			for (Element modelElement : gmd.getGeneratedModels()) {
				if (getModelQName((NamedElement) modelElement).equals(modelQName)) {
					TreeItem failedModelItem = new TreeItem(treeRoot,SWT.NONE);

					failedModelItem.setText(getModelName(modelElement) + "  ("+getModelQName(modelElement)+")");
					// decorate with an error overlay
					failedModelItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Class.gif")));
				}
			}
		}
	}
	
	private TreeItem getPackageTreeItem(Tree treeRoot, Element testSimulationModel){
		
		TreeItem pkgItem = null;
		// find the right package node at the 1 tree-level.
		TreeItem[] pkgItems = treeRoot.getItems();
		for (TreeItem treeItem : pkgItems) {
			TreeItemData data = (TreeItemData) treeItem.getData();
			if (data.isPackage && data.getPackageElement().equals(testSimulationModel.getNearestPackage())) {
				return treeItem; 
			}
		}
		return pkgItem;
	}
	

	
	private void createScenarioTreeItem(Tree treeRoot, Element scenario, boolean isPositiveRelationsTree, boolean isPreselected){
		
		// if this tree has requirements 
//		HashSet<Element> requirements = gmd.getAnalizedRequirements(scenario, isPositiveRelationsTree);
		
		HashSet<Element> requirements = null;
		if (isPositiveRelationsTree) {
			requirements = gmd.getScenarioWithNewPositiveRelationsToRequirements().get(scenario);
		}
		else {
			requirements = gmd.getScenarioWithNewNegativeRelationsToRequirements().get(scenario);
		}
		
		
		if (requirements != null && requirements.size() > 0 ) {
		
			TreeItem pkgItem = getPackageTreeItem(treeRoot, scenario);
			TreeItem scenarioItem = null;
			if (pkgItem != null) {
				scenarioItem = new TreeItem(pkgItem, 0);	
			}
			else {
				scenarioItem = new TreeItem(treeRoot, 0);
			}
			
			if (isPositiveRelationsTree) {
				treeItemsPositiveRelations.add(scenarioItem);
			}
			else {
				treeItemsNegativeRelations.add(scenarioItem);
			}
			
			TreeItemData data = new TreeItemData();
			data.setScenario(true);
			data.setScenarioElement(scenario);
			
			scenarioItem.setData(data);
			
			scenarioItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
			scenarioItem.setChecked(isPreselected);
			
			scenarioItem.setText(getModelName(scenario));
		
			// create requirement nodes
			for (Element requirement : requirements) {
				createRequirementTreeItem(requirement, scenarioItem, isPositiveRelationsTree, isPreselected);
			}
		}
	}
	
	
	private void createRequirementTreeItem(Element requirement, TreeItem scenarioItem, boolean isPositiveRelationsTree, boolean isPreselected){

		TreeItem requirementItem = new TreeItem(scenarioItem, 0);	

		TreeItemData scenarioData = (TreeItemData) scenarioItem.getData();
		Element scenario = scenarioData.getScenarioElement();

		// add to final map
		if (isPositiveRelationsTree) {
			treeItemsPositiveRelations.add(requirementItem);
			
			if (isPreselected) {
				
				addToMap(positiveRelationsToCreate, scenario, requirement);
			}
		
		}
		else { // negative relations 
			treeItemsNegativeRelations.add(requirementItem);
			
			if (isPreselected) {
				// add to final map
				addToMap(negativeRelationsToCreate, scenario, requirement);
			}
		}
		
		TreeItemData data = new TreeItemData();
		data.setIsRequirement(true);
		data.setRequirementElement(requirement);
		
		requirementItem.setData(data);
		
		requirementItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
		requirementItem.setChecked(isPreselected);

		String name = getModelName(requirement);

		//Get the requirement TreeObject and determine it was violated
		// Add it to the name as indication and decorate as warning
		
		if (gmd.isRequirementViolatedInScenario(requirement, scenario)) {
			name = "(VIOLATED) " + name;
			requirementItem.setImage(decorateWarning(requirementItem.getImage()));
			propagateWarning(requirementItem);
		}
		
		requirementItem.setText(name);
		
	}

	// Utls ************************************************************************ 

	private void propagateWarning(TreeItem requirementItem){
		TreeItem scenarioItem = requirementItem.getParentItem();
		TreeItem packageItem = scenarioItem.getParentItem();
		
		scenarioItem.setImage(decorateWarning(scenarioItem.getImage()));
		packageItem.setImage(decorateWarning(packageItem.getImage()));
	}
	
	private boolean isPositiveRelationsTabActive(TabFolder tabFolder){
		TabItem tab = tabFolder.getSelection()[0];
		if (tab.getText().startsWith(TAB_TITLE_POSITIVE_RELATIONS)) {
			return true;
		}
		return false;
	}
	
	private boolean isNegativeRelationsTabActive(TabFolder tabFolder){
		TabItem tab = tabFolder.getSelection()[0];
		if (tab.getText().startsWith(TAB_TITLE_NEGATIVE_RELATIONS)) {
			return true;
		}
		return false;
	}
	
	
	private String getModelQName(NamedElement model) {
		return StringUtls.replaceSpecCharExceptThis( model.getQualifiedName(), "::").replaceAll("::", ".");
	}
	
	private void clearAllLists(){
		treeItemsPositiveRelations.clear();
		treeItemsNegativeRelations.clear();
	}
	
	
	private String getModelQName(Element model) {
		if (model instanceof NamedElement && ((NamedElement)model).getQualifiedName() != null) {
			return StringUtls.replaceSpecCharExceptThis( ((NamedElement)model).getQualifiedName(), "::").replaceAll("::", ".");
		}
		else {
			return "Qualified name is unknown ...";
		}
	}
	
	private String getModelName(Element model) {
		if (model instanceof NamedElement && ((NamedElement)model).getName() != null) {
			return StringUtls.replaceSpecCharExceptThis( ((NamedElement)model).getName(), "::").replaceAll("::", ".");
		}
		else {
			return "Qualified name is unknown ...";
		}
	}
	
	
//	private int getMapSize(HashMap<Element, HashSet<Element>> map){
//		int number = 0;
//		if (map != null) {
//			for (Object key : map.keySet()) {
//				// get the list for the key and add it to the count
//				number = number + map.get(key).size(); 
//			}
//		}
//		return number;
//	}
	
	
	private HashMap<Element, HashSet<Element>> addToMap(HashMap<Element, HashSet<Element>> map, Element key, Element value){
		HashSet<Element> set = map.get(key);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(value);
			map.put(key, updatedSet);
		}
		else {
			updatedSet.add(value);
			map.put(key, updatedSet);
		}
		
		return map;
	}
	
	
	private HashMap<Element, HashSet<Element>> removeFromMap(HashMap<Element, HashSet<Element>> map, Element key, Element value){
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		HashSet<Element> values = map.get(key);
		if (values == null) {
			return map;
		}
		else {
			updatedSet = values;
			updatedSet.remove(value);
			
			map.put(key, updatedSet);
		}
		
		return map;
	}
	
	// Image handling ************************************************************************

	public Image decorateWarning(Image image) {
		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}

	
	// Listener ************************************************************************
	
	public HashMap<Element,HashSet<Element>> getPositiveRelationsToCreate() {
		return positiveRelationsToCreate;
	}

	public void setPositiveRelationsToCreate(
			HashMap<Element,HashSet<Element>> positiveRelationsToCreate) {
		this.positiveRelationsToCreate = positiveRelationsToCreate;
	}


	public HashMap<Element,HashSet<Element>> getNegativeRelationsToCreate() {
		return negativeRelationsToCreate;
	}

	public void setNegativeRelationsToCreate(
			HashMap<Element,HashSet<Element>> negativeRelationsToCreate) {
		this.negativeRelationsToCreate = negativeRelationsToCreate;
	}


	public HashMap<Element,HashSet<Element>> getPositiveRelationsToDelete() {
		return positiveRelationsToDelete;
	}

	public void setPositiveRelationsToDelete(
			HashMap<Element,HashSet<Element>> positiveRelationsToDelete) {
		this.positiveRelationsToDelete = positiveRelationsToDelete;
	}


	public HashMap<Element,HashSet<Element>> getNegativeRelationsToDelete() {
		return negativeRelationsToDelete;
	}

	public void setNegativeRelationsToDelete(
			HashMap<Element,HashSet<Element>> negativeRelationsToDelete) {
		this.negativeRelationsToDelete = negativeRelationsToDelete;
	}


	class CheckboxTreeSelectionListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			if (event.detail == SWT.CHECK && event.item instanceof TreeItem) {
				updateFinalLists((TreeItem) event.item);
			}
		}
	}
	
	
	
	private void updateFinalLists(TreeItem treeItem){
		if (treeItem.getData() instanceof TreeItemData) {
			TreeItemData data = (TreeItemData) treeItem.getData(); 
			if (data.isPackage()) {
				// update based only on the package selection, i.e. select or unselect all scenarios and requirements underneath 
				updatePackageItemSelection(treeItem, treeItem.getChecked());
			}
			else if (data.isScenario()) {
				// update based only on the scenario selection, i.e. select or unselect all requirements underneath 
				updateScenarioItemSelection(treeItem, treeItem.getChecked());
			}
			else if (data.isRequirement()) {
				// update based only on the requirement selection
				updateRequirementItemSelection(treeItem, null, treeItem.getChecked());
			}
			
			// activate the save button
			btnSave.setEnabled(true);
		}
	}
	
	private void updateRequirementItemSelection(TreeItem requirementItem, TreeItem scenarioItem, boolean setChecked){
		TreeItemData data = (TreeItemData) requirementItem.getData(); 
		
		// if the parent item was selected in the tree then its selection is propagated to all child items
		requirementItem.setChecked(setChecked);
		
		if (data.isRequirement()) {
			Element requirement = data.getRequirementElement();
			
			if (scenarioItem == null) {
				scenarioItem = requirementItem.getParentItem();
			}
			TreeItemData scenarioItemData = (TreeItemData) scenarioItem.getData();
			Element scenario = scenarioItemData.getScenarioElement();
			
			// if a requirement tree item was selected
			if (scenario != null && requirement != null) {

				if (requirementItem.getChecked()) {
					// positive relations tree
					if (isPositiveRelationsTabActive(tabFolder)) {
						addToMap(positiveRelationsToCreate, scenario, requirement);
						removeFromMap(positiveRelationsToDelete, scenario, requirement);
					}
					// negative relations tree
					else if (isNegativeRelationsTabActive(tabFolder)) {
						addToMap(negativeRelationsToCreate, scenario, requirement);
						removeFromMap(negativeRelationsToDelete, scenario, requirement);
					}
				}
				else { // if the tree item was unselected
					
					// positive relations tree
					if (isPositiveRelationsTabActive(tabFolder)) {
						removeFromMap(positiveRelationsToCreate, scenario, requirement);
						addToMap(positiveRelationsToDelete, scenario, requirement);
					}
					// negative relations tree
					else if (isNegativeRelationsTabActive(tabFolder)) {
						removeFromMap(negativeRelationsToCreate, scenario, requirement);
						addToMap(negativeRelationsToDelete, scenario, requirement);
					}
				}
			}
		}
	}
	
	private void updateScenarioItemSelection(TreeItem scenarioItem, boolean setChecked){
		TreeItemData data = (TreeItemData) scenarioItem.getData(); 
		
		// if the parent item was selected in the tree then its selection is propagated to all child items
		scenarioItem.setChecked(setChecked);
		
		if (data.isScenario()) {
			TreeItem[] requirementItems = scenarioItem.getItems();
			for (TreeItem requirementItem : requirementItems) {
				updateRequirementItemSelection(requirementItem, scenarioItem, setChecked);
			}
		}
	}
	
	private void updatePackageItemSelection(TreeItem packageItem, boolean setChecked){
		TreeItemData data = (TreeItemData) packageItem.getData(); 
		
		if (data.isPackage()) {
			TreeItem[] scenarioItems = packageItem.getItems();
			for (TreeItem scenarioItem : scenarioItems) {
				updateScenarioItemSelection(scenarioItem, setChecked);
			}
		}
	}
	
	
	
	// Data structure ************************************************************************
	
	class TreeItemData{
		
		private boolean isScenario = false;

		private boolean isRequirement = false;
		private boolean isPackage = false;
		
		private Element scenarioElement;
		private Element requirementElement;
		private Element packageElement;
		
		public boolean isScenario() {
			return isScenario;
		}
		public void setScenario(boolean isScenario) {
			this.isScenario = isScenario;
		}

		public void setIsPackage(boolean isPackage) {
			this.isPackage = isPackage;
		}
		public boolean isPackage() {
			return isPackage;
		}

		public void setPackageElement(Element _packageElement) {
			packageElement = _packageElement;
		}
		public Element getPackageElement() {
			return packageElement;
		}
		public boolean isRequirement() {
			return isRequirement;
		}
		public void setIsRequirement(boolean isRequirement) {
			this.isRequirement = isRequirement;
		}
		public Element getScenarioElement() {
			return scenarioElement;
		}
		public void setScenarioElement(Element scenarioElement) {
			this.scenarioElement = scenarioElement;
		}
		public Element getRequirementElement() {
			return requirementElement;
		}
		public void setRequirementElement(Element requirementElement) {
			this.requirementElement = requirementElement;
		}
	}
	
	// GETTER/SETTER ************************************************************************
//	public HashSet<Element> getUserSelectedTestSimulationModels() {
//		return userSelectedTestSimulationModels;
//	}
	
}

