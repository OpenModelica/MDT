package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.handlers.ScenariosToRequirementsRelationsDiscoveryToolbarHandler.GeneratedModelsData;

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
		container.setLayout(new GridLayout(5, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1);
		gd_tabFolder.widthHint = 626;
		tabFolder.setLayoutData(gd_tabFolder);

		/*
		 * TAB Negative relations
		 */
		TabItem tbtmnewPositiveRelations = new TabItem(tabFolder, SWT.NONE);
		String metricPreSelectedPositiveRelations = "("+getMapSize(gmd.getNewPositiveRelationsElements()) + ")";
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
		String metricNegativeRelations = "("+getMapSize(gmd.getNewNegativeRelationsElements()) + ")";
		tbtmNewNegativeRelations.setText(TAB_TITLE_NEGATIVE_RELATIONS + " " + metricNegativeRelations);
		
		final Tree treeNewNegativeRelations = new Tree(tabFolder, SWT.CHECK);
		buildTree(treeNewNegativeRelations, false, false);
		
		// Add listeners
		treeNewNegativeRelations.addSelectionListener(new CheckboxTreeSelectionListener());
		tbtmNewNegativeRelations.setControl(treeNewNegativeRelations);
		
		
		TabItem tbtmNotSimulated = new TabItem(tabFolder, SWT.NONE);
		tbtmNotSimulated.setText("Errors (0)");
		final Tree failedSimulationModelsTree = new Tree(tabFolder, SWT.NONE);
		createNotSimulatedItems(failedSimulationModelsTree);
		
		
		
		Button btnSelectAll = new Button(container, SWT.NONE);
		btnSelectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItemsPositiveRelations) {
					treeItem.setChecked(true);
					updateFinalLists(treeItem);
				}
				
				for (TreeItem treeItem : treeItemsNegativeRelations) {
					treeItem.setChecked(true);
					updateFinalLists(treeItem);
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
				for (TreeItem treeItem : treeItemsPositiveRelations) {
					treeItem.setChecked(false);
					updateFinalLists(treeItem);
				}
				
				for (TreeItem treeItem : treeItemsNegativeRelations) {
					treeItem.setChecked(false);
					updateFinalLists(treeItem);
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
		gd_composite.widthHint = 39;
		gd_composite.heightHint = 20;
		composite.setLayoutData(gd_composite);
		
		Button btnSave = new Button(container, SWT.NONE);
		btnSave.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// save relations
			}
		});
		btnSave.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/etool16/saveall_edit.gif"));
		btnSave.setText("Save all");

		return area;
	}

	
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

		List<Element> packagesOfTheSelectedTestScenariosSorted = ModelicaMLServices.getSortedByName(getScenarioPackages(gmd.getAllScenarios()));
		createPkgTreeItems(treeRoot, packagesOfTheSelectedTestScenariosSorted, isPositiveRelationsTree, isPreselected);
		
		List<Element> scenariosSorted = ModelicaMLServices.getSortedByName(gmd.getAllScenarios());
		for (Element scenario : scenariosSorted) {
			if (scenario instanceof NamedElement) {
				createScenarioTreeItem(treeRoot, scenario, isPositiveRelationsTree, isPreselected);
				// add to the initial pre-selection
//				userSelectedTestSimulationModels.add(testSimulationModel);
			}
		}
	}

	private HashSet<Element> getScenarioPackages(HashSet<Element> testSimulationModels){
		HashSet<Element> pkgList = new HashSet<Element>();
		if (testSimulationModels != null ) {
			for (Element testScenario : testSimulationModels) {
				pkgList.add(testScenario.getOwner());
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
			
			String qName = "? No name is defined ?";
			if (((NamedElement)pkg).getQualifiedName() != null) {
				qName = ((NamedElement)pkg).getQualifiedName();
			}
			pkgItem.setText(((NamedElement)pkg).getName() + "  ("+qName+")");
			pkgItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
			
			// preselect
			pkgItem.setChecked(isPreselected);
		}
	}
	
	

	
	private void createNotSimulatedItems(Tree treeRoot){
		// create package nodes at the 1 level
		for (String modelQName : gmd.getSimulationFailedList()) {

			for (Element modelElement : gmd.getGeneratedModels()) {
				if (getModelQName((NamedElement) modelElement).equals(modelQName)) {
					TreeItem failedModelItem = new TreeItem(treeRoot,2);
					
					String qName = "? No name is defined ?";
					if (((NamedElement)modelElement).getQualifiedName() != null) {
						qName = ((NamedElement)modelElement).getQualifiedName();
					}
					failedModelItem.setText(((NamedElement)modelElement).getName() + "  ("+qName+")");
					failedModelItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Class.gif"));
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
			if (data.isPackage && data.getPackageElement().equals(testSimulationModel.getOwner())) {
				return treeItem; 
			}
		}
		return pkgItem;
	}
	

	
	private void createScenarioTreeItem(Tree treeRoot, Element scenario, boolean isPositiveRelationsTree, boolean isPreselected){
		
		// if this tree has requirements 
		HashSet<Element> requirements = gmd.getRequirements(scenario, isPositiveRelationsTree);
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
			
			String name = "? No name is defined ?";
			if (((NamedElement)scenario).getName() != null) {
				name = ((NamedElement)scenario).getName();
			}
			scenarioItem.setText(name);
		
		
			// create requirement nodes
			for (Element requirement : requirements) {
				createRequirementTreeItem(requirement, scenarioItem, isPositiveRelationsTree, isPreselected);
			}
		}
	}
	
	
	private void createRequirementTreeItem(Element requirement, TreeItem scenarioItem, boolean isPositiveRelationsTree, boolean isPreselected){

		TreeItem requirementItem = new TreeItem(scenarioItem, 0);	
		

		if (isPositiveRelationsTree) {
			treeItemsPositiveRelations.add(requirementItem);
			
			if (isPreselected) {
				// add to final map
				TreeItemData data = (TreeItemData) scenarioItem.getData();
				Element scenario = data.getScenarioElement();
				addToMap(positiveRelationsToCreate, scenario, requirement);
			}
		
		}
		else { // negative relations 
			treeItemsNegativeRelations.add(requirementItem);
			
			if (isPreselected) {
				// add to final map
				TreeItemData data = (TreeItemData) scenarioItem.getData();
				Element scenario = data.getScenarioElement();
				addToMap(negativeRelationsToCreate, scenario, requirement);
			}
		}
		
		TreeItemData data = new TreeItemData();
		data.setIsRequirement(true);
		data.setRequirementElement(requirement);
		
		requirementItem.setData(data);
		
		requirementItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
		requirementItem.setChecked(isPreselected);

		String name = "? No name is defined ?";
		if (((NamedElement)requirement).getName() != null) {
			name = ((NamedElement)requirement).getName();
		}
		requirementItem.setText(name);
		
	}
	
	
	// Utls ************************************************************************ 

	private boolean isPositiveRelationsTabActive(TabFolder tabFolder){
		TabItem tab = tabFolder.getSelection()[0];
		if (tab.getText().equals(TAB_TITLE_POSITIVE_RELATIONS)) {
			return true;
		}
		return false;
	}
	
	private boolean isNegativeRelationsTabActive(TabFolder tabFolder){
		TabItem tab = tabFolder.getSelection()[0];
		if (tab.getText().equals(TAB_TITLE_NEGATIVE_RELATIONS)) {
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
	
	
	private int getMapSize(HashMap<Element, HashSet<Element>> map){
		int number = 0;
		if (map != null) {
			for (Object key : map.keySet()) {
				// get the list for the key and add it to the count
				number = number + map.get(key).size(); 
			}
		}
		return number;
	}
	
	
	private void addToMap(HashMap<Element, HashSet<Element>> map, Element key, Element item){
		HashSet<Element> set = map.get(key);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(item);
			map.put(key, updatedSet);
		}
		else {
			updatedSet.add(item);
			map.put(key, updatedSet);
		}
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
		// requirement item
		if (treeItem.getData() instanceof TreeItemData) {
			TreeItemData data = (TreeItemData) treeItem.getData(); 
			
			
			// TODO: ......
			// test scenario item.
			
			
			if (data.isScenario) {
				if ( treeItem.getChecked() ) {
					// add
				}
				else {
					// remove
				}
			}
			// package item
			else if (data.isPackage) {
				// Check or uncheck all test simulation models underneath
				if ( treeItem.getChecked() ) {
					TreeItem[] testSimulationModelsItems = treeItem.getItems();
					for (int i = 0; i < testSimulationModelsItems.length; i++) {
						TreeItem testScenarioItem = testSimulationModelsItems[i];
						testScenarioItem.setChecked(true);
						TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
						
						// add
					}
				}
				else {
					TreeItem[] testScenariosItems = treeItem.getItems();
					for (int i = 0; i < testScenariosItems.length; i++) {
						TreeItem testScenarioItem = testScenariosItems[i];
						testScenarioItem.setChecked(false);
						TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
						
						// remove
					}
				}
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

