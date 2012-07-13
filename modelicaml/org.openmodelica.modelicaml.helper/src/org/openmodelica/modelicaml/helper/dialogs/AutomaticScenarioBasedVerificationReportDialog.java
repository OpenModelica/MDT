package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.Activator;
import org.openmodelica.modelicaml.helper.handlers.ScenariosToRequirementsRelationsDiscoveryToolbarHandler.GeneratedModelsData;


public class AutomaticScenarioBasedVerificationReportDialog extends Dialog {

	private GeneratedModelsData gmd;
	
	private final ImageDescriptor warningImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	private final ImageDescriptor errorImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	private final ImageDescriptor okStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/success_ovr.gif");
	private final ImageDescriptor questionStateImageDescriptor = ResourceManager.getImageDescriptor(Activator.class, "/icons/question_ov.gif");
	
	private final static String TAB_TITLE_Violated_Requirements = "Violated Requirements";
	private final static String TAB_TITLE_Not_Violated_Requirements = "Not Violated Requirements";
	private final static String TAB_TITLE_Not_Evaluated_Requirement =  "Not Evaluated Requirements";
	private final static String TAB_TITLE_Simulation_Errors =  "Simulation Errors";
	
	private TabFolder tabFolder;
	

	private Tree treeViolatedRequirements;
	private Tree treeNotViolatedRequirement;
	private Tree treeNotEvaluatedRequirements;
	private Tree failedSimulationModelsTree;
	
	public AutomaticScenarioBasedVerificationReportDialog(Shell parentShell, GeneratedModelsData gmd) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		this.gmd = gmd;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/automaticScenarioBasedVerification.gif"));
		newShell.setText("Automatic Scenario-Based Verification Report");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
//		setMessage("Note that you can select all children by selecting the parent item.");
//		setTitle("Select relations that should created between scenarios and requirements.");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(6, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1);
		gd_tabFolder.widthHint = 626;
		tabFolder.setLayoutData(gd_tabFolder);

		/*
		 * TAB: Violated requirements 
		 */
		TabItem tbtmViolatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmViolatedRequirements.setText(TAB_TITLE_Violated_Requirements + "("+gmd.getRequirementsViolatedInScenarios().size()+")");
		if (gmd.getScenariosWithViolatedRequirements().size() > 0) {
			tbtmViolatedRequirements.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeViolatedRequirements = new Tree(tabFolder, SWT.NONE);
		buildTree(treeViolatedRequirements, true, false, false);
		tbtmViolatedRequirements.setControl(treeViolatedRequirements);
		
		/*
		 * TAB: Not violated requirements 
		 */
		TabItem tbtmNotViolatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotViolatedRequirements.setText(TAB_TITLE_Not_Violated_Requirements + "("+gmd.getRequirementsNotViolatedInScenarios().size()+")");
		if (gmd.getScenariosWithNotViolatedRequirements().size() > 0) {
			tbtmNotViolatedRequirements.setImage(decorateOK(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeNotViolatedRequirement = new Tree(tabFolder, SWT.NONE);
		buildTree(treeNotViolatedRequirement, false, true, false);
		tbtmNotViolatedRequirements.setControl(treeNotViolatedRequirement);
		

		/*
		 * TAB: Not evaluated requirements 
		 */
		TabItem tbtmNotEvaluatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotEvaluatedRequirements.setText(TAB_TITLE_Not_Evaluated_Requirement + "("+gmd.getNotEvaluatedRequirements().size()+")");
		if (gmd.getNotEvaluatedRequirements().size() > 0) {
			tbtmNotEvaluatedRequirements.setImage(decorateQuestion(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		
		treeNotEvaluatedRequirements = new Tree(tabFolder, SWT.NONE);
		buildTree(treeNotEvaluatedRequirements, false, false, true);
		tbtmNotEvaluatedRequirements.setControl(treeNotEvaluatedRequirements);

		/*
		 * TAB: Simulation errors 
		 */
		TabItem tbtmNotSimulated = new TabItem(tabFolder, SWT.NONE);
		tbtmNotSimulated.setText(TAB_TITLE_Simulation_Errors + " ("+gmd.getSimulationFailedList().size()+")");
		if (gmd.getSimulationFailedList().size() > 0 ) {
			tbtmNotSimulated.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/obj16/error_tsk.gif"));
		}

		failedSimulationModelsTree = new Tree(tabFolder, SWT.NONE);
		createNotSimulatedItems(failedSimulationModelsTree);
		tbtmNotSimulated.setControl(failedSimulationModelsTree);
		
		/*
		 * Open relations dialog
		 */
		Button btnDiscoverRelations = new Button(container, SWT.NONE);
		btnDiscoverRelations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				SelectScenarioToReqRelationsToCreateDialog dialog = new SelectScenarioToReqRelationsToCreateDialog(new Shell(), gmd);
				dialog.open();
			}
		});
		btnDiscoverRelations.setText("Discover Relations");
		btnDiscoverRelations.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "/resources/icons/icons16/correlationset.gif"));
		
		Button btnLocate = new Button(container, SWT.NONE);
		btnLocate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					Tree activeTree = getActiveTree();
					HashSet<Object> selectedObjects = new HashSet<Object>();
					if (activeTree != null) {
						TreeItem[] items = activeTree.getSelection();
						for (TreeItem treeItem : items) {
							TreeItemData data = (TreeItemData) treeItem.getData();
							if (data != null) {
								if (data.isRequirement) {
									selectedObjects.add(data.getRequirementElement());
								}
								else if (data.isScenario) {
									selectedObjects.add(data.getScenarioElement());
								}
								else if (data.isPackage) {
									selectedObjects.add(data.getPackageElement());
								}
							}
						}
					}
					if (selectedObjects.size() > 0) {
						CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
						List<Object> items = new ArrayList<Object>();
						for (Object object2 : selectedObjects) {
							if (object2 instanceof EObject) {
								items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject) object2));
							}
						}
						modelExplorerView.setSelection(new StructuredSelection(items), true);
					}
				}
			}
		});
		btnLocate.setText("Locate");
		btnLocate.setImage(ResourceManager.getPluginImage("org.eclipse.papyrus.modelexplorer", "/icons/ModelExplorer.gif"));


		/*
		 * Save as File 
		 */
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
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
					String folderName = Constants.folderName_automaticScenarioBasedDesignVerificationDiscovery;
					String fileName = Constants.fileName_automaticScenarioBasedDesignVerification + "_" + System.currentTimeMillis() + ".xml";
					
					// create report
					String fileContent = XMLReportGenerator.getXMLReportContent(gmd);
					String filePath = XMLReportGenerator.createFile(projectName, folderName, fileName, fileContent, false);
					
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

		return area;
	}

	
	private Tree getActiveTree() {
		TabItem selectedTab = tabFolder.getSelection()[0];
		if (selectedTab != null) {
			if ( selectedTab.getText().startsWith(TAB_TITLE_Violated_Requirements)) {
				return treeViolatedRequirements;
			}
			else if (selectedTab.getText().startsWith(TAB_TITLE_Not_Violated_Requirements)) {
				return treeNotViolatedRequirement;
			}
			else if (selectedTab.getText().startsWith(TAB_TITLE_Not_Evaluated_Requirement)) {
				return treeNotEvaluatedRequirements;
			}
			else if (selectedTab.getText().startsWith(TAB_TITLE_Simulation_Errors)) {
				return failedSimulationModelsTree;
			}
		}
		return null;
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

	
	private void buildTree(Tree treeRoot, boolean isViolatedRequirementsTree, boolean isNotViolatedRequirementsTree, boolean isNotEvaluatedRequirementsTree){

		// if the not evaluated requirements tree should be build 
		if (isNotEvaluatedRequirementsTree) {
			List<Element> notEvaluatedRequirementsSorted =  ModelicaMLServices.getSortedByName(gmd.getNotEvaluatedRequirements());
			if (notEvaluatedRequirementsSorted != null && notEvaluatedRequirementsSorted.size() > 0) {
				List<Element> packages = getNearestPackages(notEvaluatedRequirementsSorted);
				createPkgTreeItems(treeRoot, packages);
				for (Element requirement : notEvaluatedRequirementsSorted) {
					createNotEvaluatedRequirement(treeRoot, requirement);
				}
			}
			
			// stop here, do not create any other nodes.
			return;
		}
		
		List<Element> scenariosSorted = new ArrayList<Element>();
		
		if (isViolatedRequirementsTree) {
			// if there are scenarios with not violated requirements at all
			if (gmd.getScenariosWithViolatedRequirements().size() > 0) {
				scenariosSorted = ModelicaMLServices.getSortedByName(gmd.getScenariosWithViolatedRequirements()); 
				
				List<Element> packagesOfScenarios = getNearestPackages(scenariosSorted);
				createPkgTreeItems(treeRoot, packagesOfScenarios);
			}
		}
		else if (isNotViolatedRequirementsTree) {
			// if there are scenarios with not violated requirements at all
			if (gmd.getScenariosWithNotViolatedRequirements().size() > 0) {
				scenariosSorted = ModelicaMLServices.getSortedByName(gmd.getScenariosWithNotViolatedRequirements());
				
				List<Element> packagesOfScenarios = getNearestPackages(scenariosSorted);
				createPkgTreeItems(treeRoot, packagesOfScenarios);
			}
		}
		
		// create scenario nodes
		for (Element scenario : scenariosSorted) {
			if (scenario instanceof NamedElement) {
				createScenarioTreeItem(treeRoot, scenario, isViolatedRequirementsTree, isNotViolatedRequirementsTree, isNotEvaluatedRequirementsTree);
			}
		}

	}

	private List<Element> getNearestPackages(List<Element> models){
		HashSet<Element> pkgListSet = new HashSet<Element>();
		if (models != null ) {
			for (Element model : models) {
				pkgListSet.add(model.getNearestPackage());
			}
		}
		return ModelicaMLServices.getSortedByName(pkgListSet);
	}
	
	private void createPkgTreeItems(Tree treeRoot, List<Element> pkgElements){
		// create package nodes at the 1 level
		for (Element pkg : pkgElements) {

			TreeItem pkgItem = new TreeItem(treeRoot, 0);
			
			TreeItemData data = new TreeItemData();
			data.setIsPackage(true);
			data.setPackageElement(pkg);

			pkgItem.setData(data);

			pkgItem.setText(getModelName(pkg) + "  ("+getModelQName(pkg)+")");
			pkgItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
		}
	}
	
	
	private void createNotEvaluatedRequirement(Tree treeRoot, Element requirement){
		
		TreeItem pkgItem = getPackageTreeItem(treeRoot, requirement);
		TreeItem requirementItem = null;
		if (pkgItem != null) {
			requirementItem = new TreeItem(pkgItem, 0);	
		}
		else {
			requirementItem = new TreeItem(treeRoot, 0);
		}
		
		TreeItemData data = new TreeItemData();
		data.setIsRequirement(true);
		data.setRequirementElement(requirement);
		
		requirementItem.setData(data);
		requirementItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
		requirementItem.setText(getModelName(requirement));
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
	

	
	private void createScenarioTreeItem(Tree treeRoot, Element scenario, boolean isViolatedRequirementsTree, boolean isNotViolatedRequirementsTree, boolean isNotEvaluatedRequirementsTree){
		
		// if this tree has requirements 
		HashSet<Element> requirements = null;
		if (isViolatedRequirementsTree) {
			requirements = gmd.getScenarioToViolatedRequirements().get(scenario);
		}
		else if (isNotViolatedRequirementsTree) {
			HashSet<Element> evaluatedRequirements = gmd.getScenarioToEvaluatedRequirements().get(scenario);
			HashSet<Element> violatedRequirements = gmd.getScenarioToViolatedRequirements().get(scenario);
			HashSet<Element> notViolatedRequirements = new HashSet<Element>();
			if (evaluatedRequirements != null) {
				notViolatedRequirements.addAll(evaluatedRequirements);
				if (violatedRequirements != null) {
					notViolatedRequirements.removeAll(violatedRequirements);
				}
			}
			requirements = notViolatedRequirements;
		}
//		else if (isNotEvaluatedRequirementsTree) {
//			requirements = gmd.getScenarioToNotEvaluatedRequirements().get(scenario);
//		}
			
		if (requirements != null && requirements.size() > 0 ) {
		
			TreeItem pkgItem = getPackageTreeItem(treeRoot, scenario);
			TreeItem scenarioItem = null;
			if (pkgItem != null) {
				scenarioItem = new TreeItem(pkgItem, 0);	
			}
			else {
				scenarioItem = new TreeItem(treeRoot, 0);
			}
			
			TreeItemData data = new TreeItemData();
			data.setScenario(true);
			data.setScenarioElement(scenario);
			
			scenarioItem.setData(data);
			
			scenarioItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
			scenarioItem.setText(getModelName(scenario));
		
			// create requirement nodes
			for (Element requirement : ModelicaMLServices.getSortedByName(requirements)) {
				createRequirementTreeItem(requirement, scenarioItem,  isViolatedRequirementsTree, isNotViolatedRequirementsTree, isNotEvaluatedRequirementsTree);
			}
		}
	}
	
	
	private void createRequirementTreeItem(Element requirement, TreeItem scenarioItem, boolean isViolatedRequirementsTree, boolean isNotViolatedRequirementsTree, boolean isNotEvaluatedRequirementsTree){

		TreeItem requirementItem = new TreeItem(scenarioItem, 0);	

//		TreeItemData scenarioData = (TreeItemData) scenarioItem.getData();
//		Element scenario = scenarioData.getScenarioElement();

		TreeItemData data = new TreeItemData();
		data.setIsRequirement(true);
		data.setRequirementElement(requirement);
		
		requirementItem.setData(data);
		
		requirementItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));

		String name = getModelName(requirement);
		
		if (isViolatedRequirementsTree) {
//			name = "(VIOLATED) " + name;
//			requirementItem.setImage(decorateWarning(requirementItem.getImage()));
//			propagateWarning(requirementItem);
			
			requirementItem.setImage(decorateError(requirementItem.getImage()));
			propagateError(requirementItem);
		}
		
		requirementItem.setText(name);
	}

	// Utls ************************************************************************ 

//	private void propagateWarning(TreeItem requirementItem){
//		TreeItem scenarioItem = requirementItem.getParentItem();
//		TreeItem packageItem = scenarioItem.getParentItem();
//		
//		scenarioItem.setImage(decorateWarning(scenarioItem.getImage()));
//		packageItem.setImage(decorateWarning(packageItem.getImage()));
//	}
	
	private void propagateError(TreeItem requirementItem){
		TreeItem scenarioItem = requirementItem.getParentItem();
		TreeItem packageItem = scenarioItem.getParentItem();
		
		scenarioItem.setImage(decorateError(scenarioItem.getImage()));
		packageItem.setImage(decorateError(packageItem.getImage()));
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

	
	// Image handling ************************************************************************


	
	public Image decorateWarning(Image image) {
		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateOK(Image image) {
		return new DecorationOverlayIcon(image, okStateImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateQuestion(Image image) {
		return new DecorationOverlayIcon(image, questionStateImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
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
	
}

