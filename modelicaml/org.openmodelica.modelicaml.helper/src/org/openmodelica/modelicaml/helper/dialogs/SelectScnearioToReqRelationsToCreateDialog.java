package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
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
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;

public class SelectScnearioToReqRelationsToCreateDialog extends TitleAreaDialog {

	
	// positive scenario to requirements relations, i.e., scenario was used to stimulate the system mode and lead to an evaluation of the requirements
	private HashMap<TreeObject, HashSet<TreeObject>> newPositiveRelations = new HashMap<TreeObject, HashSet<TreeObject>>();

	// negative scenario to requirements relations, i.e., scenario was used to stimulate the system mode and did NOT lead to an evaluation of the requirements
	private HashMap<TreeObject, HashSet<TreeObject>> newNegativeRelations = new HashMap<TreeObject, HashSet<TreeObject>>();
	
	// positive scenario to requirements relations, i.e., scenario was used to stimulate the system mode and lead to an evaluation of the requirements
	private HashMap<TreeObject, HashSet<TreeObject>> userSelectedNewPositiveRelations = new HashMap<TreeObject, HashSet<TreeObject>>();

	// negative scenario to requirements relations, i.e., scenario was used to stimulate the system mode and did NOT lead to an evaluation of the requirements
	private HashMap<TreeObject, HashSet<TreeObject>> userSelectedNewNegativeRelations = new HashMap<TreeObject, HashSet<TreeObject>>();

	
	// the final selection done by user
	private HashSet<Element> userSelectedTestSimulationModels = new HashSet<Element>();
	
	/*  All tree items to iterate over when required.
	 *  Note, this should be an ordered list in order to enable a simple run through when "SelectAll"/"DeselectAll" is clicked 
	 */
	private List<TreeItem> treeItems = new ArrayList<TreeItem>();
	
	private final ImageDescriptor warningImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	private final ImageDescriptor errorImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectScnearioToReqRelationsToCreateDialog(Shell parentShell,
			HashMap<TreeObject, HashSet<TreeObject>> newPositiveRelations,
			HashMap<TreeObject, HashSet<TreeObject>> newNegativeRelations) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);

		this.newPositiveRelations = newPositiveRelations;
		this.newNegativeRelations = newNegativeRelations;
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

		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1);
		gd_tabFolder.widthHint = 626;
		tabFolder.setLayoutData(gd_tabFolder);

		// TAB: Pre-selected positive relations
		TabItem tbtmnewPositiveRelations = new TabItem(tabFolder, SWT.NONE);
		String metricPreSelected = "("+getMapSize(this.newPositiveRelations) + ")";
		tbtmnewPositiveRelations.setText("New Positive Relations (0)");
		
		final Tree treePreSelectedNewPositiveRelations = new Tree(tabFolder, SWT.CHECK);
		buildTree(treePreSelectedNewPositiveRelations);
		
		// Add listeners
		treePreSelectedNewPositiveRelations.addSelectionListener(new CheckboxTreeSelectionListener());
		tbtmnewPositiveRelations.setControl(treePreSelectedNewPositiveRelations);
		
		TabItem tbtmNewNegativeRelations = new TabItem(tabFolder, SWT.NONE);
		tbtmNewNegativeRelations.setText("New Negative Relations (0)");
		
		TabItem tbtmNotSimulated = new TabItem(tabFolder, SWT.NONE);
		tbtmNotSimulated.setText("Errors (0)");
		
		Button btnSelectAll = new Button(container, SWT.NONE);
		btnSelectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItems) {
					treeItem.setChecked(true);
					// add to maps.
					TreeItemData data = (TreeItemData) treeItem.getData();
					if (data.isScenario) {
						userSelectedTestSimulationModels.add(data.getScenarioElement());
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
						for (TreeItem treeItem : treeItems) {
							
							treeItem.setChecked(false);
							// remove
							TreeItemData data = (TreeItemData) treeItem.getData();
							if (data.isScenario) {
								userSelectedTestSimulationModels.remove(data.getScenarioElement());
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
				clearAllLists();
				buildTree(treePreSelectedNewPositiveRelations);
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

	
	private void buildTree(Tree treeRoot){
		
		HashSet<Element> testSimulationModels = new HashSet<Element>();
//		if (this.selectedTestSimulationModels!= null ) {
//			testSimulationModels.addAll(this.selectedTestSimulationModels);
//		}
//		
		List<Element> packagesOfTheSelectedTestScenariosSorted = ModelicaMLServices.getSortedByName(getTestSimulationModelsPackages(testSimulationModels));
		createPkgTreeItems(treeRoot, packagesOfTheSelectedTestScenariosSorted, true);
		
		List<Element> testSimulationModelsSorted = ModelicaMLServices.getSortedByName(testSimulationModels);
		for (Element testSimulationModel : testSimulationModelsSorted) {
			if (testSimulationModel instanceof NamedElement) {
				createTestSimulationModelTreeItem(treeRoot, testSimulationModel);
				// add to the initial pre-selection
				userSelectedTestSimulationModels.add(testSimulationModel);
			}
		}
	}

	private HashSet<Element> getTestSimulationModelsPackages(HashSet<Element> testSimulationModels){
		HashSet<Element> pkgList = new HashSet<Element>();
		if (testSimulationModels != null ) {
			for (Element testScenario : testSimulationModels) {
				pkgList.add(testScenario.getOwner());
			}
		}
		return pkgList;
	}
	
	private void createPkgTreeItems(Tree treeRoot, List<Element> pkgElements, boolean isPreSelected){
		// create package nodes at the 1 level
		for (Element pkg : pkgElements) {

			TreeItem pkgItem = new TreeItem(treeRoot, 0);
			treeItems.add(pkgItem);
			
			TreeItemData data = new TreeItemData();
			data.setIsPackage(true);
			data.setPackageElement(pkg);

			pkgItem.setData(data);
			
			pkgItem.setText(((NamedElement)pkg).getName() + "  ("+((NamedElement)pkg).getQualifiedName()+")");
			pkgItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
			
			// preselect
			pkgItem.setChecked(isPreSelected);
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
	
	private void createTestSimulationModelTreeItem(Tree treeRoot, Element testSimulationModel){

		TreeItem pkgItem = getPackageTreeItem(treeRoot, testSimulationModel);
		TreeItem testSimulationModelItem = null;
		if (pkgItem != null) {
			testSimulationModelItem = new TreeItem(pkgItem, 0);	
		}
		else {
			testSimulationModelItem = new TreeItem(treeRoot, 0);
		}
		
		treeItems.add(testSimulationModelItem);
		
		TreeItemData data = new TreeItemData();
		data.setScenario(true);
		data.setScenarioElement(testSimulationModel);
		
		testSimulationModelItem.setData(data);
		
		testSimulationModelItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
		testSimulationModelItem.setChecked(true);

		testSimulationModelItem.setText(((NamedElement)testSimulationModel).getName());
		
	}
	
	
	// Utls ************************************************************************ 

	private void clearAllLists(){
		userSelectedTestSimulationModels.clear();
		treeItems.clear();
	}
	
	
	private int getMapSize(HashMap<TreeObject, HashSet<TreeObject>> newPositiveRelations2){
		Set<TreeObject> keysetNumber = newPositiveRelations2.keySet();
		int number = 0;
		for (Object object : keysetNumber) {
			number = number + newPositiveRelations2.get(object).size(); 
		}
		
		return number;
	}
	
	// Image handling ************************************************************************

	public Image decorateWarning(Image image) {
		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}

	
	// Listener ************************************************************************
	
	class CheckboxTreeSelectionListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			if (event.detail == SWT.CHECK && event.item instanceof TreeItem) {
				
				// requirement item
				if (event.item.getData() instanceof TreeItemData) {
					TreeItemData data = (TreeItemData) event.item.getData(); 
					
					// test scenario item.
					if (data.isScenario) {
						Element tesSimulationModel = data.getScenarioElement();
						
						if ( ((TreeItem)event.item).getChecked() ) {
//							System.err.println("ADD test scenario to map.");
							userSelectedTestSimulationModels.add(tesSimulationModel);
						}
						else {
							userSelectedTestSimulationModels.remove(tesSimulationModel);
//							System.err.println("remove test scenario from map");	
						}
					}
					// package item
					else if (data.isPackage) {
						// Check or uncheck all test simulation models underneath
						if ( ((TreeItem)event.item).getChecked() ) {
							TreeItem[] testSimulationModelsItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testSimulationModelsItems.length; i++) {
								TreeItem testScenarioItem = testSimulationModelsItems[i];
								testScenarioItem.setChecked(true);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								userSelectedTestSimulationModels.add(tsData.getScenarioElement());
							}
						}
						else {
							TreeItem[] testScenariosItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testScenariosItems.length; i++) {
								TreeItem testScenarioItem = testScenariosItems[i];
								testScenarioItem.setChecked(false);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								userSelectedTestSimulationModels.remove(tsData.getScenarioElement());
							}
						}
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
	}
	
	// GETTER/SETTER ************************************************************************
	public HashSet<Element> getUserSelectedTestSimulationModels() {
		return userSelectedTestSimulationModels;
	}
	
}

