package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.datacollection.VerificationScenariosCollector;
import org.openmodelica.modelicaml.helper.structures.VeMScenarioReqCombinationsCreator;

public class SelectScenariosAndRequirementsDialog extends TitleAreaDialog {

	// test scenarios that are appropriate for the system model
	private HashSet<Element> preselectedTestScenarios;
	// test scenarios that are NOT appropriate for the system model 
	private HashSet<Element> discardedTestScenarios;
	
//	// requirements that are referenced by the test scenarios and for which all clients are satisfied 
	private HashSet<Element> preselectedRequirements;

	// requirements that are referenced by the test scenarios and for which NOT all clients are satisfied 
	private HashSet<Element> discardedRequirements;
	
	// selected system model (user clicked on initially) to create test simulation models for
	private Element systemModel;
	
	// the entire test scenarios and requirements collection.
	private VerificationScenariosCollector tsc;
	
	// the result of the collection
	private String collectionLog;
	
	// scenarios with requirements that are finally selected by user
	private HashMap<Element,HashSet<Element>> selectedScenariosWithRequirements = new HashMap<Element,HashSet<Element>>();
	
	// ONLY FOR MODE: DISCOVERY of relation between scenarios and requirements 
	private HashSet<Element> selectedScenarios = new HashSet<Element>(); 
	private HashSet<Element> selectedRequirements = new HashSet<Element>();
	
	
	// all requirements that are not referenced by scenarios by <<UseToVerify>> relation or not referenced at all. 
	private HashSet<Element> requirements = new HashSet<Element>();
	
	/*  All tree items to iterate over when required.
	 *  Note, this should be an ordered list in order to enable a simple run trough when "SelectAll"/"DeselectAll" is clicked 
	 */
	private List<TreeItem> treeItems = new ArrayList<TreeItem>();

	private HashMap<Element, VeMScenarioReqCombinationsCreator> tsToTestSimulationModelCombination;
	
	private static final int DECORATION_WARNING = 0 ;
	private static final int DECORATION_ERROR = 1 ;
	private static final String testScenarioNamePrefix = "vs: ";
	private static final String requirementNamePrefix = "req: ";
	private static final String discardedPrefix = "(DISCARDED) ";
	
	private final ImageDescriptor warningImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/warning_ovr.gif");
	private final ImageDescriptor errorImageDescriptor = ResourceManager.getPluginImageDescriptor("org.openmodelica.modelicaml.common", "icons/overlay/error_ovr.gif");
	
	// requirements selection options - default settings
	// include requirements that are referenced by scenarios relations stereotyped with <<UsedToVerify>>
	private boolean includeRequirementsWithPositiveRelations = true;
	// include requirements that are referenced by scenarios relations stereotyped with <<DoNotUseToVerify>>
	private boolean includeRequirementsWithNegativeRelations = false;
	// include all requirements that are not referenced by scenarios at all
	private boolean includeRequirementsWitnUnknownRelations = false;
	
	private int mode;
	
	// tree for all requirements that are not referenced by scenarios by <<UseToVerify>> relation or not referenced at all. 
	private Tree treeRequirements;

	private static final String TAB_TITLE_Preselected_Scenarios = "Preselected Scenarios";
	private static final String TAB_TITLE_Discarded_Scenarios = "Discarded Scenarios";
	private static final String TAB_TITLE_Requirements = "Requirements";

	
	
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectScenariosAndRequirementsDialog(Shell parentShell,
			HashSet<Element> selectedTestScenarios,
			HashSet<Element> discardedTestScenarios,
			HashSet<Element> selectedRequirements,
			HashSet<Element> discardedRequirements,
			Element systemModel,
			VerificationScenariosCollector tsc,
			String collectionLog,
			HashMap<Element, VeMScenarioReqCombinationsCreator> tsToTestSimulationModelCombination,
			boolean includeRequirementsWithPositiveRelations,
			boolean includeRequirementsWithNegativeRelations,
			boolean includeRequirementsWitnUnknownRelations,
			int mode) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
		
		this.preselectedTestScenarios = selectedTestScenarios;
		this.discardedTestScenarios = discardedTestScenarios;
		this.preselectedRequirements = selectedRequirements;
		this.discardedRequirements = discardedRequirements;
		this.systemModel = systemModel;
		this.tsc = tsc;
		this.collectionLog = collectionLog;
		this.tsToTestSimulationModelCombination = tsToTestSimulationModelCombination;

		this.includeRequirementsWithPositiveRelations = includeRequirementsWithPositiveRelations;
		this.includeRequirementsWithNegativeRelations = includeRequirementsWithNegativeRelations;
		this.includeRequirementsWitnUnknownRelations = includeRequirementsWitnUnknownRelations;
		
		this.mode = mode;
		
		requirements.addAll(this.discardedRequirements);
		requirements.addAll(this.preselectedRequirements);
		
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/new_testsuite.gif"));
		newShell.setText("Simulation Models Generation");
	}
	
	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
//		setTitleImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/generateSimMopdels.png"));
		setMessage("Select scenarios and requirements for which simulation models should be generated.");
		setTitle("System Model: " + ((NamedElement)systemModel).getName());
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		GridData gd_container = new GridData(GridData.FILL_BOTH);
		gd_container.widthHint = 500;
		gd_container.heightHint = 400;
		container.setLayoutData(gd_container);

		final TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tabFolder.heightHint = 284;
		gd_tabFolder.widthHint = 711;
		tabFolder.setLayoutData(gd_tabFolder);

		// TAB: Pre-selected test scenarios
		TabItem tbtmPreSelectedTestScenarios = new TabItem(tabFolder, SWT.NONE);
//		tbtmTestScenarios.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/elcl16/close_view.gif"));
//		String metricPreSelected = "(" + getScenariosWithRequirementsCount() + " of " +  tsc.getAllScenarios().size() + ")";
		tbtmPreSelectedTestScenarios.setText(TAB_TITLE_Preselected_Scenarios + " " + getScenariosWithRequirementsCountString());
		
		final Tree treePreSelectedTestScenarios = new Tree(tabFolder, SWT.CHECK);
		buildTree(treePreSelectedTestScenarios, false);
		
		// Add listeners
		treePreSelectedTestScenarios.addSelectionListener(new CheckboxTreeSelectionListener());
		treePreSelectedTestScenarios.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDescriptionText(treePreSelectedTestScenarios.getSelection()[0]);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tbtmPreSelectedTestScenarios.setControl(treePreSelectedTestScenarios);
		
		// TAB: Discarded test scenarios
		TabItem tbtmDiscardedTestScenarios = new TabItem(tabFolder, SWT.NONE);
		String metricDiscarded  = "("+discardedTestScenarios.size()+" of " + tsc.getAllScenarios().size() + ")";
		tbtmDiscardedTestScenarios.setText(TAB_TITLE_Discarded_Scenarios + " " + metricDiscarded);
		
		final Tree treeDiscardedTestScenarios = new Tree(tabFolder, SWT.CHECK);
		buildTree(treeDiscardedTestScenarios, true);
		
		// Add listeners
		treeDiscardedTestScenarios.addSelectionListener(new CheckboxTreeSelectionListener());
		treeDiscardedTestScenarios.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDescriptionText(treeDiscardedTestScenarios.getSelection()[0]);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tbtmDiscardedTestScenarios.setControl(treeDiscardedTestScenarios);
		
		/*
		 * If we are going to discover relations between scenarios and requirements
		 * then build tree that shows all requirements (i.e. also those that
		 * are referenced by scenarios with <<DoNotUseToVerify)>> and all that are not referenced 
		 * by scenarios at all.  
		 */
		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			TabItem tbtmAllRequirements = new TabItem(tabFolder, SWT.NONE);
			tbtmAllRequirements.setText(TAB_TITLE_Requirements + " " + " ("+requirements.size()+")");
			
			treeRequirements = new Tree(tabFolder, SWT.CHECK);
			buildAllRequirementsTree(treeRequirements);
			
			tbtmAllRequirements.setControl(treeRequirements);
			treeRequirements.addSelectionListener(new CheckboxTreeSelectionListener());
			treeRequirements.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					setDescriptionText(treeRequirements.getSelection()[0]);
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			tbtmAllRequirements.setControl(treeRequirements);
		}
		
		descriptionText = new StyledText(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_descriptionText.heightHint = 98;
		descriptionText.setLayoutData(gd_descriptionText);
		descriptionText.setBottomMargin(3);
		descriptionText.setTopMargin(3);
		descriptionText.setRightMargin(3);
		descriptionText.setLeftMargin(3);
		
		Composite buttonsBar = new Composite(container, SWT.NONE);
		GridData gd_buttonsBar = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_buttonsBar.widthHint = 338;
		buttonsBar.setLayoutData(gd_buttonsBar);
		buttonsBar.setBounds(0, 0, 64, 64);
		

		Button btnSelectAll = new Button(buttonsBar, SWT.NONE);
		btnSelectAll.setBounds(0, 0, 60, 25);
		btnSelectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItems) {

					TreeItemData data = (TreeItemData) treeItem.getData();
					if (data.isRequirement) {
						
						if (isRequirementsTab(tabFolder)) {
							
							// select in tree
							treeItem.setChecked(true);
							
							// add to lists
							if (mode == Constants.MODE_VEM_GENERATION) {
								addToTestScenarioToRequirementsMap(data.getTestScenarioElement(), data.getRequirementElement());
							}

							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION ) {
								// for DISCOVERY MODE ONLY
								selectedRequirements.add(data.getRequirementElement());
							}
						}
					}
					else if (data.isTestScenario) {
						
						if (isScenariosTab(tabFolder)) {
							// select in tree
							treeItem.setChecked(true);

							// add to lists
							if (mode == Constants.MODE_VEM_GENERATION) {
								selectedScenariosWithRequirements.put(data.getTestScenarioElement(), new HashSet<Element>());
							}
							
							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION ) {
								// for DISCOVERY MODE ONLY
								selectedScenarios.add(data.getTestScenarioElement());
							}
						}
					}
				}
			}
		});
		btnSelectAll.setText("Select All");
		
		Button btnDeselectAll = new Button(buttonsBar, SWT.NONE);
		btnDeselectAll.setBounds(69, 0, 73, 25);
		btnDeselectAll.setEnabled(true);
		btnDeselectAll.setText("Deselect All");
		
		Button btnRestore = new Button(buttonsBar, SWT.NONE);
		btnRestore.setBounds(150, -1, 75, 26);
		btnRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				treePreSelectedTestScenarios.removeAll();
				treeDiscardedTestScenarios.removeAll();
				if (treeRequirements != null) {
					treeRequirements.removeAll();
				}
				
				clearAllLists();
				
				buildTree(treePreSelectedTestScenarios, false);
				buildTree(treeDiscardedTestScenarios, true);
				if (treeRequirements != null) {
					buildAllRequirementsTree(treeRequirements);
				}

			}
		});
		btnRestore.setImage(ResourceManager.getPluginImage("org.eclipse.emf.common.ui", "/org/eclipse/emf/common/ui/Restore.gif"));
		btnRestore.setText("Restore");
		
		Button btnSeeLog = new Button(buttonsBar, SWT.NONE);
		btnSeeLog.setBounds(231, -1, 56, 26);
		btnSeeLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String infoText = "Data Collection for Simulation Models Generation Log: ";
				DialogMessage dialog = new DialogMessage( getCurrentShell(), "Result", infoText, collectionLog, false);
				dialog.open();
			}
		});
		btnSeeLog.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "icons/log.png"));
		btnSeeLog.setText("Log");
		
		
		btnDeselectAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for (TreeItem treeItem : treeItems) {
					
					
					// remove from to maps.
					TreeItemData data = (TreeItemData) treeItem.getData();
					if (data.isRequirement) {
						if (isRequirementsTab(tabFolder)) {
							
							// de-select from tree
							treeItem.setChecked(false);
							
							// remove from lists
							
							if (data.getTestScenarioElement() != null) {
								if (mode == Constants.MODE_VEM_GENERATION) {
									removeFromTestScenarioToRequirementsMap(data.getTestScenarioElement(), data.getRequirementElement());
								}
							}
							
							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION ) {
								// for DISCOVERY MODE ONLY
								selectedRequirements.remove(data.getRequirementElement());
							}
						}
						
					}
					else if (data.isTestScenario) {
						
						if (isScenariosTab(tabFolder)) {
							// de-select from tree
							treeItem.setChecked(false);
							
							// remove from lists
							if (mode == Constants.MODE_VEM_GENERATION) {
								selectedScenariosWithRequirements.remove(data.getTestScenarioElement());
							}
							
							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION ) {
								// for DISCOVERY MODE ONLY
								selectedScenarios.remove(data.getTestScenarioElement());
							}
						}
					}
				}
			}
		});

		return area;
	}

	
	private boolean isScenariosTab(TabFolder tabFolder){
		if (isPreselectedScenariosTab(tabFolder) || isDiscardedScenariosTab(tabFolder)) {
			return true;
		}
		return false;
	}
	
	private boolean isPreselectedScenariosTab(TabFolder tabFolder){
		TabItem tab = tabFolder.getSelection()[0];
		if (tab.getText().equals(TAB_TITLE_Preselected_Scenarios)) {
			return true;
		}
		return false;
	}

	private boolean isDiscardedScenariosTab(TabFolder tabFolder){
		TabItem tab = tabFolder.getSelection()[0];
		if (tab.getText().equals(TAB_TITLE_Discarded_Scenarios)) {
			return true;
		}
		return false;
	}

	
	private boolean isRequirementsTab(TabFolder tabFolder){
		TabItem tab = tabFolder.getSelection()[0];
		if (tab.getText().equals(TAB_TITLE_Requirements)) {
			return true;
		}
		return false;
	}
	
	private void setDescriptionText(TreeItem item){
		descriptionText.setText(getDescription(item, false));
	}
	
	private Shell getCurrentShell(){
		Shell shell = null;
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			if (win != null) {
				shell = win.getShell();
			}
		}
		if (shell == null) {
			shell = new Shell();
		}
		return shell;
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
		return new Point(738, 617);
	}

	
	private void buildAllRequirementsTree(Tree treeRoot){
		if (requirements.size() > 0) {
			TreeItem allReqRoot = new TreeItem(treeRoot, 0);
			
			allReqRoot.setText("Requirements to be considered in addition for all scenarios");
			allReqRoot.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
			
			TreeItemData data = new TreeItemData();
			data.setIsPackage(true);
			allReqRoot.setData(data);

			treeItems.add(allReqRoot);
			
			// sort requirements by requirement id
			List<Element> allReqSorted= ModelicaMLServices.getSortedByRequirementId(this.requirements);
			for (Element req : allReqSorted) {
				createRequirementTreeItem(allReqRoot, req);
			}
		}
	}
	
	
//	private HashSet<Element> getAllRequirementsReferencedBy(String dependencyStereotypeQName){
//		HashSet<Element> reqList = new HashSet<Element>();
//		
//		// positive relations
//		if (dependencyStereotypeQName.equals(Constants.stereotypeQName_UsedToVerify)) {
//			for (Element scenario : tsc.getScenarioToReq().keySet()) {
//				reqList.addAll(tsc.getScenarioToReq().get(scenario));
//			}
//		}
//		// negative relations
//		else if (dependencyStereotypeQName.equals(Constants.stereotypeQName_DoNotUseToVerify)){
//			for (Element scenario : tsc.getScenariosWithNegativeRelationsToReq().keySet()) {
//				reqList.addAll(tsc.getScenariosWithNegativeRelationsToReq().get(scenario));
//			}
//		}
//		return reqList;
//	}
	
	private void buildTree(Tree treeRoot, boolean isDiscardedTree){
		
		HashSet<Element> testScenarios = new HashSet<Element>();
		if (this.preselectedTestScenarios != null && !isDiscardedTree) {
			testScenarios.addAll(this.preselectedTestScenarios);
		}
		else if (this.discardedTestScenarios != null && isDiscardedTree) {
			testScenarios.addAll(this.discardedTestScenarios);
		}
		
		List<Element> packagesOfTheSelectedTestScenariosSorted = ModelicaMLServices.getSortedByName(getTestScenarioPackages(testScenarios));
		createPkgTreeItems(treeRoot, packagesOfTheSelectedTestScenariosSorted, !isDiscardedTree, isDiscardedTree);
		
		List<Element> testScenariosSorted = ModelicaMLServices.getSortedByName(testScenarios);
		for (Element testScenario : testScenariosSorted) {
			if (testScenario instanceof NamedElement) {
				createScenarioTreeItem(treeRoot, testScenario, isDiscardedTree);
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
		
		// create packages in Vem_GENERATION mode only if there are scenarios with scenarios 
		if (tsc.getAllScenarios().size() > 0 && 
				(	mode==Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION
					|| (mode == Constants.MODE_VEM_GENERATION && getScenariosWithRequirementsCount() > 0)) ) {
			
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
	
	
	
	private boolean scenarioHasRequirements(HashSet<Element> requirements){
		if (requirements != null && requirements.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	
	private int getScenariosWithRequirementsCount(){
		int count = 0;
		
		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION ) {
			count = selectedScenarios.size();
		}
		else if (mode == Constants.MODE_VEM_GENERATION) {
			for (Element scenario : tsc.getScenarioToReq().keySet()) {
				HashSet<Element> requirements = tsc.getScenarioToReq().get(scenario);
				
				if (scenarioHasRequirements(requirements)) {
					count ++;
				}
			}
		}
		return count;
	}
	
	private String getScenariosWithRequirementsCountString(){
		String string = "";
		
		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION ) {
			if (getScenariosWithRequirementsCount() == 0) {
				// return nothing
			}
		}
		else if (mode == Constants.MODE_VEM_GENERATION) {
			string = "(" + getScenariosWithRequirementsCount() + " of " + tsc.getAllScenarios().size() + ")";
		}
		return string;
	}
	
	
	private void createScenarioTreeItem(Tree treeRoot, Element testScenario, boolean isDiscarded){

		// if there are scenarios with positive relations then do not show this scenario for VeM Generation
		if ( this.mode == Constants.MODE_VEM_GENERATION && !scenarioHasRequirements(tsc.getScenarioToReq().get(testScenario)) ) {
			return;
		}
		
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

			if (mode == Constants.MODE_VEM_GENERATION) {
				selectedScenariosWithRequirements.put(testScenario, new HashSet<Element>());
			}
			
			if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
				// for DISCOVERY MODE
				selectedScenarios.add(testScenario);
			}
			
			testScenarioItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
			// preselect
			testScenarioItem.setChecked(true);
			discardedPrefixString = "";
		}
		else {
			testScenarioItem.setImage(decorateWarning(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif")));
			testScenarioItem.setChecked(false);
		}

		testScenarioItem.setText(testScenarioNamePrefix + discardedPrefixString + ((NamedElement)testScenario).getName());
		
		// do not create requirement nodes if we are in discovery mode!
		if (this.mode == Constants.MODE_VEM_GENERATION) {
			List<Element> associatedRequirements = ModelicaMLServices.getSortedByRequirementId(tsc.getScenarioToReq().get(testScenario));

			// create requirement items
			if (associatedRequirements != null && associatedRequirements.size() > 0 ) {
				for (Element req : associatedRequirements) {
					createRequirementTreeItem(testScenarioItem, req, testScenario, isDiscarded);
				}
			}
		}
	}
	
	/*
	 * For Verification Models Generation MODE
	 */
	private void createRequirementTreeItem(TreeItem parentItem, Element req, Element testScenario, boolean isDiscarded){
		String prefix = requirementNamePrefix;
		TreeItem reqItem = new TreeItem(parentItem, 0);
		treeItems.add(reqItem);
		TreeItemData reqData = new TreeItemData();
		reqData.setIsRequirement(true);
		reqData.setRequirementElement(req);
		reqData.setTestScenarioElement(testScenario);
		
		// if this is a requirement for which not all required clients are satisfied:
		if (this.discardedRequirements.contains(req)) {
			// DO NOT preselect 
			reqItem.setChecked(false);
			reqData.setIsDiscarded(true);
			reqItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
			// propagate error to all parents
			propagateDecoration(reqItem, DECORATION_ERROR);
		}
		else {
			reqData.setIsDiscarded(false);
			reqItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
			
			// if the discarded tree is being built then do not preselect requirements and add them to the initial map.
			if (!isDiscarded) {
				// initially add to map
				addToTestScenarioToRequirementsMap(testScenario, req);
				// preselect
				reqItem.setChecked(true);
			}
		}
		
		prefix = prefix + ModelicaMLServices.getRequirementID(req) + " - ";

		reqItem.setText(prefix + ((NamedElement)req).getName()  + "  ("+((NamedElement)req.getOwner()).getQualifiedName()+")");
		reqItem.setData(reqData);
	}

	
	/*
	 * RELATIONS DISCOVERY MODE
	 */

	private void createRequirementTreeItem(TreeItem parentItem, Element req){
		
		if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
		
			String prefix = requirementNamePrefix;
			TreeItem reqItem = new TreeItem(parentItem, 0);
			treeItems.add(reqItem);
			TreeItemData reqData = new TreeItemData();
			reqData.setIsRequirement(true);
			reqData.setRequirementElement(req);
			
			// if this is a requirement for which not all required clients are satisfied:
			if (this.discardedRequirements.contains(req)) {
				// DO NOT preselect 
				reqItem.setChecked(false);
				reqData.setIsDiscarded(true);
				reqItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
				// propagate error to all parents
				propagateDecoration(reqItem, DECORATION_ERROR);
			}
			else {
				// preselect because this requirement has all mand. clients satisfied by the system model or other models in that combination
				reqItem.setChecked(true);
				
				// for DISCOVERY MODE ONLY
				selectedRequirements.add(req);
				
				reqData.setIsDiscarded(false);
				reqItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
			}
			
			prefix = prefix + ModelicaMLServices.getRequirementID(req) + " - ";
			reqItem.setText(prefix + ((NamedElement)req).getName()  + "  ("+((NamedElement)req.getOwner()).getQualifiedName()+")");
			reqItem.setData(reqData);
		}
	}
	
	
	// Tree Item Description Handling ************************************************************************ 
	
//	private void openDescription(TreeItem item){
//		Object data = item.getData();
//    	if (data instanceof TreeItemData) {
//			Element testSceanrio = null;
//    		if ( ((TreeItemData)data).isRequirement) {
//				// get the test scenario and create a description of the combination
//				testSceanrio = ((TreeItemData)data).getTestScenarioElement();
//			}
//			if ( ((TreeItemData)data).isTestScenario) {
//				// get the test scenario and create a description of the combination
//				testSceanrio = ((TreeItemData)data).getTestScenarioElement();
//			}
//			
//			if (testSceanrio != null) {
//				String description = createDescription(testSceanrio);
//				DialogMessage dialog = new DialogMessage( getCurrentShell(), "Verification Model: Details", 
//						"Details of the selected combination of system model and scenario:", 
//						description, false);
//				dialog.open();
//			}
//		}
//	}
	
	
	private String getDescription(TreeItem item, boolean openInSeparateDialog){
		String description = "";
		Object data = item.getData();
    	if (data instanceof TreeItemData) {
			Element scenario = null;
			Element requirement = null;
    		if ( ((TreeItemData)data).isRequirement) {
				// get the test scenario and create a description of the combination
				scenario = ((TreeItemData)data).getTestScenarioElement();
				requirement = ((TreeItemData)data).getRequirementElement();
			}
			if ( ((TreeItemData)data).isTestScenario) {
				// get the test scenario and create a description of the combination
				scenario = ((TreeItemData)data).getTestScenarioElement();
			}
			
			if (scenario != null) {
				// for a requirement only return the requirement id and text
				if (((TreeItemData)data).isRequirement) {
					VeMScenarioReqCombinationsCreator tsmc = tsToTestSimulationModelCombination.get(scenario);
					if (tsmc != null) {
						description = description + getRequirementsString("", requirement, tsmc);
						return description;
					}
				}
				// for a scenario create a summary
				description = createDescription(scenario);
			}
			else { // if it is a requirement item without scenario
				description = description + getRequirementsString("", requirement);
			}
		}
    	
    	if (openInSeparateDialog) {
    		DialogMessage dialog = new DialogMessage( getCurrentShell(), "Verification Model: Details", 
					"Details of the selected combination of system model and scenario:", 
					description, false);
			dialog.open();
		}
    	
    	return description;
	}
	
	private String createDescription(Element testScenario){
		String description = "";
		VeMScenarioReqCombinationsCreator tsmc = tsToTestSimulationModelCombination.get(testScenario);
		if (tsmc != null) {
			Element systemModel = tsmc.getSystemModel();
			description = description +
					lineDelimiterString + 
					"\nSystem Model: '"+((NamedElement)systemModel).getName()+"' ("+((NamedElement)systemModel).getQualifiedName()+")" +
					getAdditionalModelsString(tsmc.getRequiredModels_systemModel(), tsmc, systemModel) + 
					
					lineDelimiterString + 
					"\nTest Scenario: '"+((NamedElement)testScenario).getName()+"'("+((NamedElement)testScenario).getQualifiedName()+")" +
					getAdditionalModelsString(tsmc.getRequiredModels_testScenario(), tsmc, testScenario) 
					;
			
			if (mode == Constants.MODE_VEM_GENERATION) {
				description = description + getRequirementsString("", tsmc.getRequirements(), tsmc);
			}
		}
		return description.trim();
	}
	
	private static final String lineDelimiterString = "\n";
	private StyledText descriptionText;
//		"\n------------------------------------------------------------------------------ \n";

	
	private String getAdditionalModelsString(HashSet<Element> set, VeMScenarioReqCombinationsCreator tsmc, Element model){
		String string = "";
		if (set != null && set.size() > 0) {
			string = "\n    - models required in addition: \n";
			string = string + getAdditionalModelsStringParts(set, "         ", tsmc)
			 + getUnsatisfiedClients("    ", model, tsmc);
		}
		
		return string;
	}
	
	private String getRequirementsString(String prefix, HashSet<Element> set, VeMScenarioReqCombinationsCreator tsmc){
		String string  = "";
		HashSet<Element> tempSet = new HashSet<Element>();
		for (Element element : set) {
			tempSet.add(element);
		}
		
		List<Element> sortedList = ModelicaMLServices.getSortedByName(tempSet);
		for (Element requirement : sortedList) {
			if (requirement instanceof NamedElement) {
				string = string + lineDelimiterString;
				string = string + prefix +"Requirement: '" + ((NamedElement)requirement).getName() 
						+ "' ("+((NamedElement)requirement).getQualifiedName()+")";
				String addModelsString = getAdditionalModelsString(tsmc.getRequiredModels_requirements().get(requirement), tsmc, requirement);
				if (addModelsString.trim().equals("")) {
					string = string + getAdditionalModelsString(tsmc.getRequiredModels_requirements().get(requirement), tsmc, requirement) + "\n";
				}
				string = string + getUnsatisfiedClients("    ", requirement, tsmc);
			}
		}
		return string;
	}
	
	private String getRequirementsString(String prefix, Element requirement, VeMScenarioReqCombinationsCreator tsmc){
		String string  = "";

		if (requirement instanceof NamedElement) {
//			string = string + lineDelimiterString;
			String id = ModelicaMLServices.getRequirementID(requirement);
			String text = ModelicaMLServices.getRequirementText(requirement);
					
			string = string + prefix +"Requirement '" + ((NamedElement)requirement).getName() + "' ("+((NamedElement)requirement).getQualifiedName()+")\n\n";
			string = string + id + ": " + text + "\n\n";
			string = string + getAdditionalModelsString(tsmc.getRequiredModels_requirements().get(requirement), tsmc, requirement) + "\n";
			string = string + getUnsatisfiedClients("    ", requirement, tsmc);
		}
		return string;
	}
	
	
	private String getRequirementsString(String prefix, Element requirement){
		String string  = "";

		if (requirement instanceof NamedElement) {
//			string = string + lineDelimiterString;
			String id = ModelicaMLServices.getRequirementID(requirement);
			String text = ModelicaMLServices.getRequirementText(requirement);
					
			string = string + prefix +"Requirement '" + ((NamedElement)requirement).getName() + "' ("+((NamedElement)requirement).getQualifiedName()+")\n\n";
			string = string + id + ": " + text + "\n\n";
//			string = string + getAdditionalModelsString(tsmc.getRequiredModels_requirements().get(requirement), tsmc, requirement) + "\n";
//			string = string + getUnsatisfiedClients("    ", requirement, tsmc);
		}
		return string;
	}
	
	
	private String getAdditionalModelsStringParts(HashSet<Element> set, String prefix, VeMScenarioReqCombinationsCreator tsmc){
		String string  = "";
		List<Element> sortedList = ModelicaMLServices.getSortedByName(set);
		for (Element element : sortedList) {
			if (element instanceof NamedElement) {
				string = string + prefix + "- " + getAlwaysIncludeString(element, tsmc) + ((NamedElement)element).getName() 
				+ " ("+((NamedElement)element).getQualifiedName()+")\n";
			}
		}
		return string;
	}
	
	private String getAlwaysIncludeString(Element model, VeMScenarioReqCombinationsCreator tsmc){
		String string = "";
		if (tsmc.getAlwaysInclude().contains(model)) {
			return "(always) ";
		}
		
		return string;
	}
	
	private String getUnsatisfiedClients(String prefix, Element element, VeMScenarioReqCombinationsCreator tsmc){
		String string = "";
		String unsatisfiedClientsString = tsmc.getClientsDotPathAsString(tsmc.getUnsatisfiedRequiredClients(element));
		if (!unsatisfiedClientsString.trim().equals("")) {
			string = prefix + "- unsatisfied required clients: " + "\n";
			string = string + prefix + "         " + unsatisfiedClientsString;
		}
		return string;
	}
	
	// Utls ************************************************************************ 

	private void clearAllLists(){
		
		// clear all lists for a new run
		selectedScenariosWithRequirements.clear();
		selectedRequirements.clear();
		selectedScenarios.clear();
		
		treeItems.clear();
	}
	
	private void addToTestScenarioToRequirementsMap(Element testScenario, Element requirement){
		HashSet<Element> set = selectedScenariosWithRequirements.get(testScenario);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		if (set != null && set.size() > 0 ) {
			updatedSet.addAll(set);
			updatedSet.add(requirement);
			selectedScenariosWithRequirements.put(testScenario, updatedSet);
		}
		else {
			updatedSet.add(requirement);
			selectedScenariosWithRequirements.put(testScenario, updatedSet);
		}
	}
	
	private void removeFromTestScenarioToRequirementsMap(Element testScenario, Element requirement){
		if (testScenario != null && requirement != null) {
			HashSet<Element> set = selectedScenariosWithRequirements.get(testScenario);
			HashSet<Element> updatedSet = new HashSet<Element>();
			
			if (set != null) {
				updatedSet.addAll(set);
				updatedSet.remove(requirement);
				selectedScenariosWithRequirements.put(testScenario, updatedSet);
			}
		}
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
							
							if (mode == Constants.MODE_VEM_GENERATION) {
								addToTestScenarioToRequirementsMap(testScenarioElement, reqElement);
							}
							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
								// for DISCOVERY MODE
								selectedRequirements.add(data.getRequirementElement());
							}
						}
						else {
							if (mode == Constants.MODE_VEM_GENERATION) {
								removeFromTestScenarioToRequirementsMap(testScenarioElement, reqElement);
//								System.err.println("remove requirement from map.");
							}
							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
								// for DISCOVERY MODE
								selectedRequirements.remove(data.getRequirementElement());
							}
						}
					}
					
					// test scenario item.
					else if (data.isTestScenario) {
						Element tesSscenario = data.getTestScenarioElement();
						
						if ( ((TreeItem)event.item).getChecked() ) {

							if (mode == Constants.MODE_VEM_GENERATION) {
								selectedScenariosWithRequirements.put(tesSscenario, new HashSet<Element>());
								
								TreeItem[] requirementsItems = ((TreeItem)event.item).getItems();
								for (int i = 0; i < requirementsItems.length; i++) {
									TreeItem reqItem = requirementsItems[i];
									if (reqItem.getChecked()) {
										TreeItemData reqData = (TreeItemData) reqItem.getData();
										addToTestScenarioToRequirementsMap(tesSscenario, reqData.getRequirementElement());
									}
								}
							}
							
							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
								// for DISCOVERY MODE
								selectedScenarios.add(data.getTestScenarioElement());
							}
						}
						
						else {
							if (mode == Constants.MODE_VEM_GENERATION) {
								selectedScenariosWithRequirements.remove(tesSscenario);
//								System.err.println("remove test scenario from map");	
							}
							
							if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
								// for DISCOVERY MODE
								selectedScenarios.remove(data.getTestScenarioElement());
							}
						}
					}
					// package item
					else if (data.isPackage) {
						// Check or un-check all scenarios underneath
						if ( ((TreeItem)event.item).getChecked() ) {
							TreeItem[] testScenariosItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testScenariosItems.length; i++) {
								TreeItem testScenarioItem = testScenariosItems[i];
								testScenarioItem.setChecked(true);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								
								if (mode == Constants.MODE_VEM_GENERATION) {
									selectedScenariosWithRequirements.put(tsData.getTestScenarioElement(), new HashSet<Element>());
								
									if (testScenarioItem.getItems() != null && testScenarioItem.getItems().length > 0) {
	
										TreeItem[] requirementsItems = testScenarioItem.getItems();
										for (int j = 0; j < requirementsItems.length; j++) {
											TreeItem reqItem = requirementsItems[j];
											if (reqItem.getChecked()) {
												TreeItemData reqData = (TreeItemData) reqItem.getData();
												addToTestScenarioToRequirementsMap(tsData.getTestScenarioElement(), reqData.getRequirementElement());
											}
										}
									}
								}
								
								if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
									selectedScenarios.add(data.getTestScenarioElement());
								}
							}
						}
						else {
							TreeItem[] testScenariosItems = ((TreeItem)event.item).getItems();
							for (int i = 0; i < testScenariosItems.length; i++) {
								TreeItem testScenarioItem = testScenariosItems[i];
								testScenarioItem.setChecked(false);
								TreeItemData tsData = (TreeItemData) testScenarioItem.getData();
								
								if (mode == Constants.MODE_VEM_GENERATION) {
									selectedScenariosWithRequirements.remove(tsData.getTestScenarioElement());
								}
								
								if (mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
									selectedScenarios.remove(tsData.getTestScenarioElement());
								}
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

	public HashMap<Element, HashSet<Element>> getSelectedScenariosWithRequirements() {
		
		// MODE: DISCOVERY of relation between scenarios and requirements 
		if (this.mode == Constants.MODE_SCENARIOS_TO_REQUIREMENTS_RELATION_DISCOVERY || mode == Constants.MODE_AUTOMATIC_SCENARIO_BASED_VERIFICATION) {
			HashMap<Element, HashSet<Element>> selectedScenariosWithAllRequirements = new HashMap<Element, HashSet<Element>>();
			if (selectedRequirements.size() > 0) {
				// selected scenarios
				for (Element scenario : selectedScenarios ) {
					
					// for each scenario put all selected requirements according to selected options
					
					HashSet<Element> reqWithPositiveRelations = tsc.getScenarioToReq().get(scenario);
					HashSet<Element> reqWithNegativeRelations = tsc.getScenariosWithNegativeRelationsToReq().get(scenario);
					
					HashSet<Element> finalRequirementsList = new HashSet<Element>();
					finalRequirementsList = selectedRequirements;

					// remove known relations if selected
					if (!includeRequirementsWithPositiveRelations) {
						finalRequirementsList.removeAll(reqWithPositiveRelations);
					}

					if (!includeRequirementsWithNegativeRelations) {
						finalRequirementsList.removeAll(reqWithNegativeRelations);
					}
					
					// all selected requirements to be added to all scenarios 
//					selectedScenariosWithAllRequirements.put(scenario, selectedRequirements);
					
					// requirements list that is filtered based on selected options
					selectedScenariosWithAllRequirements.put(scenario, finalRequirementsList); 
				}
			}
			return selectedScenariosWithAllRequirements;
		}
		
		// default: VeM Generation mode
		return selectedScenariosWithRequirements;
	}
}

