package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.eclipse.uml2.uml.Namespace;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.impl.TestScenariosCollector;

public class SelectTestScenariosAndRequirementsDialog extends TitleAreaDialog {

	// test scenarios that are appropriate for the system model
	private HashSet<Element> selectedTestScenarios;
	// test scenarios that are NOT appropriate for the system model 
	private HashSet<Element> discardedTestScenarios;
	
	// requirements that are referenced by the test scenarios and for which all clients are satisfied 
	private HashSet<Element> selectedRequirements;

	// requirements that are referenced by the test scenarios and for which NOT all clients are satisfied 
	private HashSet<Element> discardedRequirements;
	
	// selected system model (user clicked on initially) to create test simulation models for
	private Element systemModel;
	
	// the entire test scenarios and requirements collection.
	private TestScenariosCollector tsc;
	
	// the result of the collection
	private String collectionLog;
	
	// test scenarios with requirements that are finally selected by user
	private HashMap<Element,HashSet<Element>> selectedTestScenariosWithRequirements = new HashMap<Element,HashSet<Element>>();

	/*  All tree items to iterate over when required.
	 *  Note, this should be an ordered list in order to enable a simple run trough when "SelectAll"/"DeselectAll" is clicked 
	 */
	private List<TreeItem> treeItems = new ArrayList<TreeItem>();

	private static final int DECORATION_WARNING = 0 ;
	private static final int DECORATION_ERROR = 1 ;
	private static final String testScenarioNamePrefix = "ts: ";
	private static final String requirementNamePrefix = "req: ";
	private static final String discardedPrefix = "(DISCARDED) ";
	
	private final ImageDescriptor warningImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	private final ImageDescriptor errorImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectTestScenariosAndRequirementsDialog(Shell parentShell,
			HashSet<Element> selectedTestScenarios,
			HashSet<Element> discardedTestScenarios,
			HashSet<Element> selectedRequirements,
			HashSet<Element> discardedRequirements,
			Element systemModel,
			TestScenariosCollector tsc,
			String collectionLog) {
		
		super(parentShell);
		
		this.selectedTestScenarios = selectedTestScenarios;
		this.discardedTestScenarios = discardedTestScenarios;
		this.selectedRequirements = selectedRequirements;
		this.discardedRequirements = discardedRequirements;
		this.systemModel = systemModel;
		this.tsc = tsc;
		this.collectionLog = collectionLog;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/generateSimMopdels.png"));
		newShell.setText("Test Simulation Models Generation");
	}
	
	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/generateSimMopdels.png"));
		setMessage("Select test scenarios and requirements for which simulation models should be generated.");
//		setTitle("Test Scenarios and Requirements Selection");
		setTitle("System Under Test: " + ((NamedElement)systemModel).getName());
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(10, 10, 616, 315);
		
		TabItem tbtmTestScenarios = new TabItem(tabFolder, SWT.NONE);
//		tbtmTestScenarios.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/elcl16/close_view.gif"));
		tbtmTestScenarios.setText("Test Scenarios");
		
		final Tree tree = new Tree(tabFolder, SWT.CHECK);
		buildTree(tree);
		
		// Add the listener
		tree.addSelectionListener(new CheckboxTreeSelectionListener());
		
		tbtmTestScenarios.setControl(tree);

		Button btnSelectAll = new Button(container, SWT.NONE);
		btnSelectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItems) {
					treeItem.setChecked(true);
					// add to maps.
					TreeItemData data = (TreeItemData) treeItem.getData();
					if (data.isRequirement) {
						addToTestScenarioToRequirementsMap(data.getTestScenarioElement(), data.getRequirementElement());
					}
					else if (data.isTestScenario) {
						selectedTestScenariosWithRequirements.put(data.getTestScenarioElement(), new HashSet<Element>());	
					}
				}
			}
		});
		btnSelectAll.setBounds(10, 331, 68, 23);
		btnSelectAll.setText("Select All");

		Button btnDeselectAll = new Button(container, SWT.NONE);
		btnDeselectAll.setEnabled(true);
		btnDeselectAll.setBounds(84, 331, 68, 23);
		btnDeselectAll.setText("Deselect All");
		btnDeselectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItems) {
					treeItem.setChecked(false);
					// remove from to maps.
					TreeItemData data = (TreeItemData) treeItem.getData();
					if (data.isRequirement && data.getTestScenarioElement() != null) {
						removeFromTestScenarioToRequirementsMap(data.getTestScenarioElement(), data.getRequirementElement());
					}
					else if (data.isTestScenario) {
						selectedTestScenariosWithRequirements.remove(data.getTestScenarioElement());
					}
				}
			}
		});
		
		Button btnSeeLog = new Button(container, SWT.NONE);
		btnSeeLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String infoText = "Data Collection for Simulation Models Generation Log: ";
				DialogMessage dialog = new DialogMessage(new Shell(), "Result", infoText, collectionLog);
				dialog.open();
			}
		});
		btnSeeLog.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/log.png"));
		btnSeeLog.setBounds(558, 331, 68, 23);
		btnSeeLog.setText("Log");
		
		Button btnRestore = new Button(container, SWT.NONE);
		btnRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				tree.removeAll();
				clearAllLists();
				buildTree(tree);
			}
		});
		btnRestore.setImage(ResourceManager.getPluginImage("org.eclipse.emf.common.ui", "/org/eclipse/emf/common/ui/Restore.gif"));
		btnRestore.setBounds(484, 331, 68, 23);
		btnRestore.setText("Restore");

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(642, 509);
	}

	
	private void buildTree(Tree treeRoot){
	
			if (this.selectedTestScenarios != null) {
	
				/*
				 *  collect packages (owner) of selected test scenarios and create tree items. 
				 */
				List<Element> packagesOfTheSelectedTestScenariosSorted = getSortedByName(getTestScenarioPackages(this.selectedTestScenarios));
				createPkgTreeItems(treeRoot, packagesOfTheSelectedTestScenariosSorted, true, false);
				
				// Sort test scenarios alphabetically 
				List<Element> selectedTestScenariosSorted = getSortedByName(this.selectedTestScenarios);
				for (Element testScenario : selectedTestScenariosSorted) {
					if (testScenario instanceof NamedElement) {
						createTestScenarioTreeItem(treeRoot, testScenario, false);
					}
				}				
			}
			
			if (discardedTestScenarios != null && discardedTestScenarios.size() > 0) {
				List<Element> packagesOfTheDiscardedTestScenariosSorted = getSortedByName(getTestScenarioPackages(discardedTestScenarios));
				createPkgTreeItems(treeRoot, packagesOfTheDiscardedTestScenariosSorted, false, true);
	
				// add discarded test scenarios so that the user can still decide to generate the simulation model for them
				for (Element discardedTestScenario : discardedTestScenarios) {
					createTestScenarioTreeItem(treeRoot, discardedTestScenario, true);
				}
			}
		}

	private HashSet<Element> getTestScenarioPackages(HashSet<Element> testScenarios){
		HashSet<Element> pkgList = new HashSet<Element>();
		if (testScenarios != null ) {
			for (Element testScenario : testScenarios) {
				pkgList.add(testScenario.getOwner());
			}
		}
		return pkgList;
	}
	
	private void createPkgTreeItems(Tree treeRoot, List<Element> pkgElements, boolean isPreSelected, boolean isDiscarded){
		// create package nodes at the 1 level
		for (Element pkg : pkgElements) {

			TreeItem pkgItem = new TreeItem(treeRoot, 0);
			treeItems.add(pkgItem);
			
			TreeItemData data = new TreeItemData();
			data.setIsPackage(true);
			data.setIsDiscarded(isDiscarded);
			data.setPackageElement(pkg);

			pkgItem.setData(data);
			
			// decorate the package image if isDiscarded
			if (isDiscarded) {
				pkgItem.setText(discardedPrefix + ((NamedElement)pkg).getName() + "  ("+((NamedElement)pkg).getQualifiedName()+")");
				pkgItem.setImage(decorateWarning(ResourceManager
						.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif")));	
			}
			else{
				pkgItem.setText(((NamedElement)pkg).getName() + "  ("+((NamedElement)pkg).getQualifiedName()+")");
				pkgItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
			}
			
			// preselect
			pkgItem.setChecked(isPreSelected);
		}
	}
	
	private TreeItem getPackageTreeItem(Tree treeRoot, Element testScenario, boolean isDiscarded){
		TreeItem pkgItem = null;
		// find the right package node at the 1 tree-level.
		TreeItem[] pkgItems = treeRoot.getItems();
		for (TreeItem treeItem : pkgItems) {
			TreeItemData data = (TreeItemData) treeItem.getData();
			// check if the discarded package should be found
			if (isDiscarded) {
				if (data.isPackage && data.isDiscarded && data.getPackageElement().equals(testScenario.getOwner())) {
					return treeItem; 
				}
			}
			else {
				if (data.isPackage && !data.isDiscarded && data.getPackageElement().equals(testScenario.getOwner())) {
					return treeItem; 
				}
			}
		}
		return pkgItem;
	}
	
	private void createTestScenarioTreeItem(Tree treeRoot, Element testScenario, boolean isDiscarded){

//		TreeItem testScenarioItem = new TreeItem(treeRoot, 0);
		TreeItem pkgItem = getPackageTreeItem(treeRoot, testScenario, isDiscarded);
		TreeItem testScenarioItem = null;
		if (pkgItem != null) {
			testScenarioItem = new TreeItem(pkgItem, 0);	
		}
		else {
			testScenarioItem = new TreeItem(treeRoot, 0);
		}
		
		treeItems.add(testScenarioItem);
		
		TreeItemData data = new TreeItemData();
		data.setIsTestScenario(true);
		data.setTestScenarioElement(testScenario);
		data.setIsDiscarded(isDiscarded);
		
		testScenarioItem.setData(data);
		
		String discardedPrefixString = discardedPrefix;
		
		if (!isDiscarded) {
			// initially add to map as selected test scenario
			selectedTestScenariosWithRequirements.put(testScenario, new HashSet<Element>());
			testScenarioItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
			// preselect
			testScenarioItem.setChecked(true);
			discardedPrefixString = "";
		}
		else {
			testScenarioItem.setImage(decorateWarning(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif")));
			testScenarioItem.setChecked(false);
		}

//		testScenarioItem.setText(testScenarioNamePrefix + discardedPrefixString + ((NamedElement)testScenario).getName() + "  ("+((NamedElement)testScenario).getQualifiedName()+")");
		testScenarioItem.setText(testScenarioNamePrefix + discardedPrefixString + ((NamedElement)testScenario).getName());
		
		// sort requirements by requirement id
		List<Element> associatedRequirements = getSortedByRequirementId(tsc.getTsToReq().get(testScenario));
		if (associatedRequirements != null) {
			
			// create info item
			TreeItem testScenarioReqInfoNodeItem = new TreeItem(testScenarioItem, 0);
//			treeItems.add(testScenarioReqInfoNodeItem); // there is no need to know info items.
			String infoNodeTitle = "";
			if (isDiscarded) {
				infoNodeTitle = "Linked requirements:";
			}
			else {
				infoNodeTitle = "Can be used to verify '" + ((NamedElement)systemModel).getName() + "' against the following requirements: ";
			}
			testScenarioReqInfoNodeItem.setText(infoNodeTitle);
			testScenarioReqInfoNodeItem.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/obj16/info_tsk.gif"));
			testScenarioReqInfoNodeItem.setGrayed(true);
			
			// create requirement items
			for (Element req : associatedRequirements) {
				createRequirementTreeItem(testScenarioReqInfoNodeItem, req, testScenario, isDiscarded);
			}
		}
	}
	
	private void createRequirementTreeItem(TreeItem parentItem, Element req, Element testScenario, boolean isDiscarded){
		String prefix = requirementNamePrefix;
		
//		TreeItem reqItem = new TreeItem(testScenarioItem, 0);
		TreeItem reqItem = new TreeItem(parentItem, 0);
		treeItems.add(reqItem);
		TreeItemData reqData = new TreeItemData();
		reqData.setIsRequirement(true);
		reqData.setRequirementElement(req);
		reqData.setTestScenarioElement(testScenario);
		
		if (this.discardedRequirements.contains(req)) {
			reqData.setIsDiscarded(true);
			// prefix = prefix + "(INCONSISTENT) ";
			reqItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
			
			// indicate inconsistencies
//			testScenarioItem.setImage(decorateWarning(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif")));

			// DO NOT preselect 
			reqItem.setChecked(false);
			
			// propagate error to all parents
			propagateDecoration(reqItem, DECORATION_ERROR);
		}
		else {
			reqData.setIsDiscarded(false);
			
			// preselect
			reqItem.setChecked(true);
			reqItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
			
			if (!isDiscarded) {
				// initially add to map
				addToTestScenarioToRequirementsMap(testScenario, req);
			}
		}
		
		prefix = prefix + ModelicaMLServices.getRequirementID(req) + " - ";

		reqItem.setText(prefix + ((NamedElement)req).getName()  + "  ("+((NamedElement)req.getOwner()).getQualifiedName()+")");
		reqItem.setData(reqData);
	}
	
	
	
	
	
	
	// Utls ************************************************************************ 
	
	private void clearAllLists(){
		selectedTestScenariosWithRequirements.clear();
		treeItems.clear();
	}
	
	private void addToTestScenarioToRequirementsMap(Element testScenario, Element requirement){
		HashSet<Element> set = selectedTestScenariosWithRequirements.get(testScenario);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(requirement);
			selectedTestScenariosWithRequirements.put(testScenario, updatedSet);
		}
		else {
			updatedSet.add(requirement);
			selectedTestScenariosWithRequirements.put(testScenario, updatedSet);
		}
	}
	
	private void removeFromTestScenarioToRequirementsMap(Element testScenario, Element requirement){
		if (testScenario != null && requirement != null) {
			HashSet<Element> set = selectedTestScenariosWithRequirements.get(testScenario);
			HashSet<Element> updatedSet = new HashSet<Element>();
			
			if (set != null) {
				updatedSet.addAll(set);
				updatedSet.remove(requirement);
				selectedTestScenariosWithRequirements.put(testScenario, updatedSet);
			}
		}
	}
	
	
	private List<Element> getSortedByName(HashSet<Element> set){
		if (set == null) { return null; }

		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				if (arg0 instanceof NamedElement) {
					return ((NamedElement)arg0).getName().compareToIgnoreCase( ((NamedElement)arg1).getName());
				}
				return arg0.toString().compareToIgnoreCase(arg1.toString());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	private List<Element> getSortedByQName(HashSet<Element> set){
		if (set == null) { return null; }

		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				if (arg0 instanceof NamedElement) {
					return ((NamedElement)arg0).getQualifiedName().compareToIgnoreCase( ((NamedElement)arg1).getQualifiedName());
				}
				return arg0.toString().compareToIgnoreCase(arg1.toString());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	private List<Element> getSortedByRequirementId(HashSet<Element> set){
		if (set == null) { return null; }
		List<Element> listSorted = new ArrayList<Element>(set);
		Comparator<Element> c = new Comparator<Element>() {
			@Override
			public int compare(Element arg0, Element arg1) {
				return ModelicaMLServices.getRequirementID(arg0).trim().compareToIgnoreCase( ModelicaMLServices.getRequirementID(arg1).trim());
			}
		};
		Collections.sort(listSorted, c);
		
		return listSorted;
	}
	
	
	
	// Image handling ************************************************************************

	private void propagateDecoration(TreeItem item, int decoration){
		Image image = item.getImage();
		if (image != null) {
			if (decoration == DECORATION_ERROR) {
				item.setImage(decorateError(image));
			}
			else if (decoration == DECORATION_WARNING) {
				item.setImage(decorateError(image));
			}
		}
		
		if (item.getParentItem() != null) {
			propagateDecoration(item.getParentItem(), decoration);
		}
	}

	public Image decorateWarning(Image image) {
		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
//	public Image decorateDiscarded(Image image) {
//		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
//	}
	
	
	// Listener ************************************************************************
	
	class CheckboxTreeSelectionListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			if (event.detail == SWT.CHECK && event.item instanceof TreeItem) {
				
				// requirement item
				if (event.item.getData() instanceof TreeItemData) {
					TreeItemData data = (TreeItemData) event.item.getData(); 
					if (data.isRequirement) {
						Element reqElement = data.getRequirementElement();
						Element testScenarioElement = data.getTestScenarioElement();
						
						if ( ((TreeItem)event.item).getChecked() ) {
							addToTestScenarioToRequirementsMap(testScenarioElement, reqElement);
//							System.err.println("ADD requirement to map.");
						}
						else {
							removeFromTestScenarioToRequirementsMap(testScenarioElement, reqElement);
//							System.err.println("remove requirement from map.");
						}
					}
					
					// test scenario item.
					else if (data.isTestScenario) {
						Element tesSscenario = data.getTestScenarioElement();
						
						if ( ((TreeItem)event.item).getChecked() ) {
//							System.err.println("ADD test scenario to map.");
							selectedTestScenariosWithRequirements.put(tesSscenario, new HashSet<Element>());
							
							// get the info node:
							TreeItem[] infoNode = ((TreeItem)event.item).getItems();
							
							// add all test scenario linked requirements to map
							TreeItem[] requirementsItems = infoNode[0].getItems();
							for (int i = 0; i < requirementsItems.length; i++) {
								TreeItem reqItem = requirementsItems[i];
								if (reqItem.getChecked()) {
									TreeItemData reqData = (TreeItemData) reqItem.getData();
									addToTestScenarioToRequirementsMap(tesSscenario, reqData.getRequirementElement());
								}
							}
						}
						
						else {
							selectedTestScenariosWithRequirements.remove(tesSscenario);
//							System.err.println("remove test scenario from map");	
						}
					}
					// package item
					else if (data.isPackage) {
						// Check or uncheck all test scenarios underneath
						if ( ((TreeItem)event.item).getChecked() ) {
							TreeItem[] testScenariosItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testScenariosItems.length; i++) {
								TreeItem testScenarioItem = testScenariosItems[i];
								testScenarioItem.setChecked(true);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								
								selectedTestScenariosWithRequirements.put(tsData.getTestScenarioElement(), new HashSet<Element>());
								
								if (testScenarioItem.getItems() != null && testScenarioItem.getItems().length > 0) {
								
									// get the info node
									TreeItem[] infoNode = testScenarioItem.getItems();
									
									// add all test scenario linked requirements to map
									TreeItem[] requirementsItems = infoNode[0].getItems();
									for (int j = 0; j < requirementsItems.length; j++) {
										TreeItem reqItem = requirementsItems[j];
										if (reqItem.getChecked()) {
											TreeItemData reqData = (TreeItemData) reqItem.getData();
											addToTestScenarioToRequirementsMap(tsData.getTestScenarioElement(), reqData.getRequirementElement());
										}
									}
								}
							}
						}
						else {
							TreeItem[] testScenariosItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testScenariosItems.length; i++) {
								TreeItem testScenarioItem = testScenariosItems[i];
								testScenarioItem.setChecked(false);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								selectedTestScenariosWithRequirements.remove(tsData.getTestScenarioElement());
							}
						}
					}
				}
			}
		}
	}
	
	// Data structure ************************************************************************
	
	class TreeItemData{
		
		private boolean isRequirement = false;
		private boolean isTestScenario = false;
		private boolean isPackage = false;
		
		private boolean isDiscarded = false;
		private Element RequirementElement;
		private Element TestScenarioElement;
		private Element PackageElement;
		
		
		public void setIsRequirement(boolean isRequirement) {
			this.isRequirement = isRequirement;
		}
		public boolean isRequirement() {
			return isRequirement;
		}
		public void setIsTestScenario(boolean isTestScenario) {
			this.isTestScenario = isTestScenario;
		}
		public boolean isTestScenario() {
			return isTestScenario;
		}
		public void setIsPackage(boolean isPackage) {
			this.isPackage = isPackage;
		}
		public boolean isPackage() {
			return isPackage;
		}
		public void setIsDiscarded(boolean isDiscarded) {
			this.isDiscarded = isDiscarded;
		}
		public boolean isDiscarded() {
			return isDiscarded;
		}
		public void setRequirementElement(Element requirementElement) {
			RequirementElement = requirementElement;
		}
		public Element getRequirementElement() {
			return RequirementElement;
		}
		public void setTestScenarioElement(Element testScenarioElement) {
			TestScenarioElement = testScenarioElement;
		}
		public Element getTestScenarioElement() {
			return TestScenarioElement;
		}
		public void setPackageElement(Element packageElement) {
			PackageElement = packageElement;
		}
		public Element getPackageElement() {
			return PackageElement;
		}
	}
	
	// GETTER/SETTER ************************************************************************

	public HashMap<Element, HashSet<Element>> getSelectedTestScenariosWithRequirements() {
		return selectedTestScenariosWithRequirements;
	}
	
}

