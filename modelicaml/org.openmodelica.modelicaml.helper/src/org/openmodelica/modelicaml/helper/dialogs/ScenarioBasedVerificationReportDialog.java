package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.openmodelica.modelicaml.common.helpers.VerificationExecutionServices;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.Activator;
import org.openmodelica.modelicaml.helper.report.XMLReportGenerator;
import org.openmodelica.modelicaml.helper.structures.GeneratedModelsData;
import org.openmodelica.modelicaml.simulation.testexecution.actions.PlotResultsFileAction;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class ScenarioBasedVerificationReportDialog extends Dialog {

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
	private Button btnNewRelations;
	
	boolean openedFromNewRelationsDialog = false;
	private TabItem tbtmViolatedRequirements;
	private TabItem tbtmNotViolatedRequirements;
	private TabItem tbtmNotEvaluatedRequirements;
	private TabItem tbtmNotSimulated;
	private TabItem tbtmNotImplementedRequirements;
	private TabItem tbtmNotUsedScenarios;

	private Tree treeNotImplementedRequirements;

	private Tree treeNotUsedScenarios;
	
	// map of models and the corresponding simulation result paths (absolute)
	private HashMap<Element,String> simulationResultsFiles = new HashMap<Element, String>();
	private Button btnPlot;

	public ScenarioBasedVerificationReportDialog(Shell parentShell, 
			GeneratedModelsData gmd, 
			HashMap<Element,String> simulationResultsFiles,
			boolean openedFromNewRelationsDialog) {
		
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM);
		
		this.setGmd(gmd);
		this.setSimulationResultsFiles(simulationResultsFiles);
		this.setOpenedFromNewRelationsDialog(openedFromNewRelationsDialog);
		
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile","resources/icons/icons16/automaticScenarioBasedVerification.gif"));
		newShell.setText("Scenario-Based Verification: Report");
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
		tbtmViolatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmViolatedRequirements.setText(TAB_TITLE_Violated_Requirements);
		if (gmd.getScenariosWithViolatedRequirements().size() > 0) {
			tbtmViolatedRequirements.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeViolatedRequirements = new Tree(tabFolder, SWT.NONE);
//		buildTree(treeViolatedRequirements, true, false, false);
		getEvaluatedRequirementItems(treeViolatedRequirements, true, false, false);
		tbtmViolatedRequirements.setControl(treeViolatedRequirements);
		
		/*
		 * TAB: Not violated requirements 
		 */
		tbtmNotViolatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotViolatedRequirements.setText(TAB_TITLE_Not_Violated_Requirements);
		if (gmd.getScenariosWithNotViolatedRequirements().size() > 0) {
			tbtmNotViolatedRequirements.setImage(decorateOK(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeNotViolatedRequirement = new Tree(tabFolder, SWT.NONE);
//		buildTree(treeNotViolatedRequirement, false, true, false);
		getEvaluatedRequirementItems(treeNotViolatedRequirement, false, true, false);
		tbtmNotViolatedRequirements.setControl(treeNotViolatedRequirement);
		
		/*
		 * TAB: Not evaluated requirements 
		 */
		tbtmNotEvaluatedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotEvaluatedRequirements.setText(TAB_TITLE_Not_Evaluated_Requirement);
		if (gmd.getNotEvaluatedRequirementElements().size() > 0) {
			tbtmNotEvaluatedRequirements.setImage(decorateQuestion(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
		}
		
		treeNotEvaluatedRequirements = new Tree(tabFolder, SWT.NONE);
//		buildTree(treeNotEvaluatedRequirements, false, false, true);
		getNotEvaluatedRequirementItems(treeNotEvaluatedRequirements);
		tbtmNotEvaluatedRequirements.setControl(treeNotEvaluatedRequirements);

		
		/*
		 * TAB: Not implemented requirements 
		 */
		tbtmNotImplementedRequirements = new TabItem(tabFolder, SWT.NONE);
		tbtmNotImplementedRequirements.setText("Not Implemented Requirements");
		treeNotImplementedRequirements = new Tree(tabFolder, SWT.NONE);
//		buildTree(treeNotEvaluatedRequirements, false, false, true);
		getNotImplementedRequirementItems(treeNotEvaluatedRequirements);
		tbtmNotImplementedRequirements.setControl(treeNotImplementedRequirements);
		
		/*
		 * TAB: Not used scenarios
		 */
		tbtmNotUsedScenarios = new TabItem(tabFolder, SWT.NONE);
		tbtmNotUsedScenarios.setText("Not Used Scenarios");
		treeNotUsedScenarios = new Tree(tabFolder, SWT.NONE);
//		buildTree(treeNotEvaluatedRequirements, false, false, true);
		getNotUsedScenarioItems(treeNotUsedScenarios);
		tbtmNotUsedScenarios.setControl(treeNotUsedScenarios);
		
		
		/*
		 * TAB: Simulation errors 
		 */
		
		
		tbtmNotSimulated = new TabItem(tabFolder, SWT.NONE);
		tbtmNotSimulated.setText(TAB_TITLE_Simulation_Errors);
		if (gmd.getSimulationFailedList().size() > 0 ) {
			tbtmNotSimulated.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/obj16/error_tsk.gif"));
		}

		failedSimulationModelsTree = new Tree(tabFolder, SWT.NONE);
		createNotSimulatedItems(failedSimulationModelsTree);
		tbtmNotSimulated.setControl(failedSimulationModelsTree);
		
		
		
		/*
		 * Open relations dialog
		 */
		btnNewRelations = new Button(container, SWT.NONE);
		btnNewRelations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				SelectScenarioToReqRelationsToCreateDialog dialog = new SelectScenarioToReqRelationsToCreateDialog(new Shell(), gmd, getSimulationResultsFiles(), true);
				dialog.open();
			}
		});
		btnNewRelations.setText("New Relations");
		btnNewRelations.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "/resources/icons/icons16/correlationset.gif"));
		if (gmd.getNewRelationsCount(true) > 0 || gmd.getNewRelationsCount(false) > 0) {
			btnNewRelations.setEnabled(true);
		}
		else {
			btnNewRelations.setEnabled(false);
		}
		// disable this button if dialog was opened from another dialog that can be opened from this dialog to avoid cycles
		if (isOpenedFromNewRelationsDialog()) {
			btnNewRelations.setEnabled(false);
		}
		
		
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
							if (data != null && data.getUMLElement() != null) {
								selectedObjects.add(data.getUMLElement());
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

		btnPlot = new Button(container, SWT.NONE);
		btnPlot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Tree activeTree = getActiveTree();
//				HashSet<Object> selectedObjects = new HashSet<Object>();
				
				if (activeTree != null) {
					TreeItem[] items = activeTree.getSelection();
					for (TreeItem treeItem : items) {
						TreeItemData data = (TreeItemData) treeItem.getData();
						if (data != null) {
							String filePath= null;
							String dotPath= null;
							
							// VeM
							if (data.isVeM()) {
								Element VeMElement = data.getUMLElement();
								filePath = getSimulationResultsFiles().get(VeMElement);
							}
							
							// a property of the VeM to plot
							TreeObject treeObject = data.getTreeObject();
							if (treeObject != null) {
								if (data.isClientProperty()) {
									// plot client property
									dotPath = treeObject.getDotPath();
									
									// get the VeM (2 levels up)
									TreeItem VeMItem = treeItem.getParentItem().getParentItem();
									Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
									filePath = getSimulationResultsFiles().get(VeMElement);
									
								}
								else if (data.isProperty()) {
									
									if (data.isRequirement()) {
										// plot the status attribute
										dotPath = treeObject.getDotPath() + "." + gmd.requirementStatusPropertyName;
									}
									// scenarios or system models properties are not in the tree ...
									
									// get the VeM (1 level up)
									TreeItem VeMItem = treeItem.getParentItem();
									Element VeMElement = ((TreeItemData)VeMItem.getData()).getUMLElement();
									filePath = getSimulationResultsFiles().get(VeMElement);
								}
							}
							
							if (filePath != null) {
								// plot
								PlotResultsFileAction plotAction = new PlotResultsFileAction();
								plotAction.setFilePath(filePath);
								HashSet<String> preselectedVariablesToPlot = new HashSet<String>();
								preselectedVariablesToPlot.add(dotPath);
								if (dotPath != null) {
									plotAction.setPreSelectedVariablesToPlot(preselectedVariablesToPlot);
								}
								plotAction.run(null);
							}
						}
					}
				}
			}
		});
		btnPlot.setText("Plot");
		btnPlot.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "/icons/plot.png"));
		
		// disable if there are no files
		if (getSimulationResultsFiles() == null || getSimulationResultsFiles().size() == 0) {
			btnPlot.setEnabled(false);
		}

		/*
		 * Save as File 
		 */
		
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

	
	
	
	/*
	 * Build tree
	 */
	
	private void getEvaluatedRequirementItems(Tree parent, boolean onlyViolatedRequirements, boolean onlyNotViolatedRequirements, boolean onlyNotEvaluatedRequirements){

		// counter
		int i = 0;
		
		HashSet<Element> evaluatedRequirements = new HashSet<Element>();
		if (onlyViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsViolatedInScenarios();

		}
		else if (onlyNotViolatedRequirements) {
			evaluatedRequirements = gmd.getRequirementsNotViolatedInScenarios();
		}

		for (Element evaluatedRequirement : evaluatedRequirements) {
			
			i++ ;
			
			TreeItem item = new TreeItem(parent, SWT.NONE);
			String name = ModelicaMLServices.getRequirementID(evaluatedRequirement) + " " +  ModelicaMLServices.getName(evaluatedRequirement);
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
			if (onlyViolatedRequirements) {
				item.setImage(decorateError(item.getImage()));
			}
			else if (onlyNotViolatedRequirements) {
				item.setImage(decorateOK(item.getImage()));
			}
			
			TreeItemData data = new TreeItemData();
			data.setRequirementElement(evaluatedRequirement);
			data.setIsRequirement(true);
			item.setData(data);
			
			// loop over all VeM and see if this requirement is in the 
			getVeMForRequirement(item, evaluatedRequirement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
		}
		
		// update the count of the tree tab
		if (onlyViolatedRequirements) {
			tbtmViolatedRequirements.setText(tbtmViolatedRequirements.getText() + " ("+i+")");
		}
		else if (onlyNotViolatedRequirements) {
			tbtmNotViolatedRequirements.setText(tbtmNotViolatedRequirements.getText() + " ("+i+")");
		}
	}

	
	private void getNotEvaluatedRequirementItems(Tree parent){
		
		int i = 0;
		boolean showOnlyViolated = false;
		boolean showOnlyNotViolated = false;
		boolean showOnlyNotEvaluated = true;
		
		HashSet<Element> evaluatedRequirements = gmd.getNotEvaluatedRequirementElements();

		for (Element evaluatedRequirement : evaluatedRequirements) {
			
			i++ ;
			
			TreeItem item = new TreeItem(parent, SWT.NONE);
			String name = ModelicaMLServices.getRequirementID(evaluatedRequirement) + " " +  ModelicaMLServices.getName(evaluatedRequirement);
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
			item.setImage(decorateQuestion(item.getImage()));
			
			TreeItemData data = new TreeItemData();
			data.setRequirementElement(evaluatedRequirement);
			data.setIsRequirement(true);
			item.setData(data);
			
			// loop over all VeM and see if this requirement is in the 
			getVeMForRequirement(item, evaluatedRequirement, showOnlyViolated, showOnlyNotViolated, showOnlyNotEvaluated);
		}
		
		// update the count of the tree tab
		tbtmNotEvaluatedRequirements.setText(tbtmNotEvaluatedRequirements.getText() + " ("+i+")");
	}
	
	
	private void getNotImplementedRequirementItems(Tree parent){
		int i = 0;
		
		HashSet<Element> allFoundRequirements = gmd.getAllFoundRequirements();
		HashSet<Element> allUsedRequirements= gmd.getAllUsedRequirements();
		
		allFoundRequirements.removeAll(allUsedRequirements);
		
		HashSet<Element> allNotImplementedRequirements = allFoundRequirements;
		if (allNotImplementedRequirements != null && allNotImplementedRequirements.size() > 0) {
			
			for (Element requirement : allNotImplementedRequirements) {
				
				// counter 
				i++ ;
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = ModelicaMLServices.getRequirementID(requirement) + " " +  ModelicaMLServices.getName(requirement);
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
				item.setImage(decorateQuestion(item.getImage()));
				
				TreeItemData data = new TreeItemData();
				data.setRequirementElement(requirement);
				data.setIsRequirement(true);
				item.setData(data);
			}
			
			// update the count of the tree tab
			tbtmNotImplementedRequirements.setText(tbtmNotImplementedRequirements.getText() + " ("+i+")");
		}
	}
	
	
	private String getNotUsedScenarioItems(Tree parent){
		String string = "";
		int i = 0;
		
		HashSet<Element> allFoundScenarios = gmd.getAllFoundScenarios();
		HashSet<Element> allUsedScenarios = gmd.getAllScenarios();
		
		allFoundScenarios.removeAll(allUsedScenarios);
		
		HashSet<Element> allNotUsedScenarios = allFoundScenarios;
		if (allNotUsedScenarios != null && allNotUsedScenarios.size() > 0) {
			
			for (Element scenario : allNotUsedScenarios) {
				// counter
				i++;
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = ModelicaMLServices.getName(scenario);
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif"));
				item.setImage(decorateQuestion(item.getImage()));
				
				TreeItemData data = new TreeItemData();
				data.setScenarioElement(scenario);
				data.setScenario(true);
				item.setData(data);
			}
		}
		
		// update the count of the tree tab
		tbtmNotUsedScenarios.setText(tbtmNotUsedScenarios.getText() + " ("+i+")");
		
		return string;
	}
	
	
	
	private void getVeMForRequirement(TreeItem parent, Element evaluatedRequirement, boolean onlyViolatedRequirements, boolean onlyNotViolatedRequirements, boolean onlyNotEvaluatedRequirements){
		
		for (Element VeM : gmd.getGeneratedModels()) {
			HashSet<TreeObject> requirements = gmd.getRequirements(VeM);
			boolean modelContains  = false;
			if (requirements != null) {
				for (TreeObject requirementTreeObject : requirements) {
					Element type = requirementTreeObject.getComponentType();
					if (evaluatedRequirement.equals(type)) {
						
						String statusDotPath = requirementTreeObject.getDotPath().trim() + "." + gmd.requirementStatusPropertyName;
						String key = gmd.getModelToTreeItemKeyString(VeM, statusDotPath);

						boolean isViolated = gmd.getViolatedRequirements().contains(key);
						boolean isEvaluated = gmd.getEvaluatedRequirements().contains(key);
						boolean isNotEvaluated = gmd.getNotEvaluatedRequirements().contains(key);
						
						// if only violated requirements should be shown
						if (onlyViolatedRequirements && isViolated) {
							modelContains = true;
						}
						// if only not violated should be shown
						else if (onlyNotViolatedRequirements && isEvaluated && !isViolated) {
							modelContains = true; 
						}
						// of only not evaluated should be shown
						else if (onlyNotEvaluatedRequirements && isNotEvaluated) {
							modelContains = true; 
						} 
					}
				}
			}
			
			if (modelContains) {
				getVeMItem(parent, VeM, evaluatedRequirement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
			}
		}
	}
	
	
	private void getVeMItem(TreeItem parent, 
			Element model, 
			Element filterForRequirementElement, 
			boolean onlyViolatedRequirements, 
			boolean onlyNotViolatedRequirements, 
			boolean onlyNotEvaluatedRequirements){
		

		TreeItem item = new TreeItem(parent, SWT.NONE);
		String name = ModelicaMLServices.getName(model);
		item.setText(name);
		
		item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif"));
		
		TreeItemData data = new TreeItemData();

		data.setIsVeM(true);
		data.setVeMElement(model);
		item.setData(data);
		
		ClassInstantiation ci = ModelicaMLServices.getModelInstantiation(model, gmd.getPreparedInstantiations());
		
		getSystemModelItems(item, ci);
		
		getScenarioItems(item, ci);
		
		getRequirementItems(item, ci, filterForRequirementElement, onlyViolatedRequirements, onlyNotViolatedRequirements, onlyNotEvaluatedRequirements);
	}
	
	
	private void getSystemModelItems(TreeItem parent, ClassInstantiation ci){

		Element VeM = ci.getSelectedClass();
		for (TreeObject treeObject : ci.getAllTreeObjects()) {
			
			Element type = treeObject.getComponentType();
			
			if (gmd.getSystemModels().contains(type)) {
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = treeObject.getDotPath() + " ("+ModelicaMLServices.getName(type)+")";
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
				
				TreeItemData data = new TreeItemData();

				data.setIsProperty(true);
				data.setPropertyElement(treeObject.getUmlElement());
				data.setTreeObject(treeObject);
				item.setData(data);
				
				// Add clients (isMandatory, binding, plot link)
				getClientItems(item, VeM, treeObject);
			}
		}
	}
	
	
	
	private void getScenarioItems(TreeItem parent, ClassInstantiation ci){
		
		Element VeM = ci.getSelectedClass();
		
		if (VeM != null) {
			HashSet<TreeObject> scenarios = gmd.getScenarios(VeM);
			for (TreeObject scenario : scenarios) {
				
				TreeItem item = new TreeItem(parent, SWT.NONE);
				String name = scenario.getDotPath() + " ("+ModelicaMLServices.getName(scenario.getComponentType())+")";
				item.setText(name);
				
				item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
				
				TreeItemData data = new TreeItemData();

				data.setIsProperty(true);
				data.setPropertyElement(scenario.getUmlElement());
				data.setTreeObject(scenario);
				item.setData(data);

				// Add clients (isMandatory, binding, plot link)
				getClientItems(item, VeM, scenario);
			}
		}
	}
	
	
	private void  getRequirementItems(TreeItem parent,
			ClassInstantiation ci, 
			Element filterForRequirementElement, 
			boolean onlyViolatedRequirements, 
			boolean onlyNotViolatedRequirements,
			boolean onlyNotEvaluatedRequirements){
	
	Element VeM = ci.getSelectedClass();
	HashSet<TreeObject> requirements = gmd.getRequirements(VeM);
	
	for (TreeObject requirement : requirements) {
		
		Element type = requirement.getComponentType();
		String statusDotPath = requirement.getDotPath().trim() + "." + gmd.requirementStatusPropertyName;
		String key = gmd.getModelToTreeItemKeyString(VeM, statusDotPath);

		boolean isViolated = gmd.getViolatedRequirements().contains(key);
		boolean isEvaluated = gmd.getEvaluatedRequirements().contains(key);
		boolean isNotEvaluated = gmd.getNotEvaluatedRequirements().contains(key);
		
		
		String isViolatedString = "";
		if (isViolated) {
			isViolatedString = "VIOLATED";
		}
		
		String isEvaluatedString = " NOT EVALUATED";
		if (isEvaluated) {
			isEvaluatedString = "";
		}
		
		boolean skip = false;
		// if the requirements list should only include requirements with type "filterForRequirementElement"
		if (filterForRequirementElement != null && !type.equals(filterForRequirementElement)) {
			skip = true;
		}
		// if only violated requirements should be shown
		else if (onlyViolatedRequirements && !isViolated) {
			skip = true;
		}
		// if only not violated should be shown
		else if (onlyNotViolatedRequirements && !gmd.getNotViolatedRequirements().contains(key)) {
			skip = true;
		}
		// of only not evaluated should be shown
		else if (onlyNotEvaluatedRequirements && !isNotEvaluated) {
			 skip = true;
		}
		
		if (!skip) {

			TreeItem item = new TreeItem(parent, SWT.NONE);
			String name = isViolatedString + isEvaluatedString + " " + requirement.getDotPath() + " ("+ModelicaMLServices.getName(type)+")";
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
			if (isViolated) {
				item.setImage(decorateError(item.getImage()));
				propagateError(item);
			}
			else if (!isViolated && isEvaluated) {
				item.setImage(decorateOK(item.getImage()));
			}
			else if (isNotEvaluated) {
				item.setImage(decorateQuestion(item.getImage()));
			}
			
			TreeItemData data = new TreeItemData();

			data.setIsProperty(true);
			data.setIsRequirement(true);
			
			data.setTreeObject(requirement);
			data.setPropertyElement(requirement.getUmlElement());
			
			item.setData(data);

			// Add clients (isMandatory, binding)
			getClientItems(item, VeM, requirement);
			
			}
		}
	}
	
	private void getClientItems(TreeItem parent, Element VeM, TreeObject treeObject){
		
		for (TreeObject client : VerificationExecutionServices.getClientsTreeItems(treeObject, new HashSet<TreeObject>())) {
			String isMandatory = "";
			if (client.isValueClient_required()) {
				isMandatory = "(mandatory) ";
			}
			
			TreeItem item = new TreeItem(parent, SWT.NONE);
			String name = "client " + isMandatory  + ": " + client.getDotPath() + " = " + client.getFinalModificationRightHand();
			item.setText(name);
			
			item.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Property.gif"));
			
			TreeItemData data = new TreeItemData();

			data.setIsProperty(true);
			data.setClientProperty(true);
			data.setTreeObject(client);
			data.setPropertyElement(client.getUmlElement());
			item.setData(data);
		}
	}
	
	
	
	private void createNotSimulatedItems(Tree treeRoot){

		for (String modelQName : gmd.getSimulationFailedList()) {

			for (Element modelElement : gmd.getGeneratedModels()) {
				if (getModelQName((NamedElement) modelElement).equals(modelQName)) {
					TreeItem failedModelItem = new TreeItem(treeRoot,SWT.NONE);
					
					failedModelItem.setText(getModelName(modelElement) + "  ("+getModelQName(modelElement)+")");
					// decorate with an error overlay
					failedModelItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif")));
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private void buildTree(Tree treeRoot, boolean isViolatedRequirementsTree, boolean isNotViolatedRequirementsTree, boolean isNotEvaluatedRequirementsTree){
//
//		// if the not evaluated requirements tree should be build 
//		if (isNotEvaluatedRequirementsTree) {
//			List<Element> notEvaluatedRequirementsSorted =  ModelicaMLServices.getSortedByName(gmd.getNotEvaluatedRequirementElements());
//			if (notEvaluatedRequirementsSorted != null && notEvaluatedRequirementsSorted.size() > 0) {
//				List<Element> packages = getNearestPackages(notEvaluatedRequirementsSorted);
//				createPkgTreeItems(treeRoot, packages);
//				for (Element requirement : notEvaluatedRequirementsSorted) {
//					createNotEvaluatedRequirement(treeRoot, requirement);
//				}
//			}
//			
//			// stop here, do not create any other nodes.
//			return;
//		}
//		
//		List<Element> scenariosSorted = new ArrayList<Element>();
//		
//		if (isViolatedRequirementsTree) {
//			// if there are scenarios with not violated requirements at all
//			if (gmd.getScenariosWithViolatedRequirements().size() > 0) {
//				scenariosSorted = ModelicaMLServices.getSortedByName(gmd.getScenariosWithViolatedRequirements()); 
//				
//				List<Element> packagesOfScenarios = getNearestPackages(scenariosSorted);
//				createPkgTreeItems(treeRoot, packagesOfScenarios);
//			}
//		}
//		else if (isNotViolatedRequirementsTree) {
//			// if there are scenarios with not violated requirements at all
//			if (gmd.getScenariosWithNotViolatedRequirements().size() > 0) {
//				scenariosSorted = ModelicaMLServices.getSortedByName(gmd.getScenariosWithNotViolatedRequirements());
//				
//				List<Element> packagesOfScenarios = getNearestPackages(scenariosSorted);
//				createPkgTreeItems(treeRoot, packagesOfScenarios);
//			}
//		}
//		
//		// create scenario nodes
//		for (Element scenario : scenariosSorted) {
//			if (scenario instanceof NamedElement) {
//				createScenarioTreeItem(treeRoot, scenario, isViolatedRequirementsTree, isNotViolatedRequirementsTree, isNotEvaluatedRequirementsTree);
//			}
//		}
//
//	}
//
//	private List<Element> getNearestPackages(List<Element> models){
//		HashSet<Element> pkgListSet = new HashSet<Element>();
//		if (models != null ) {
//			for (Element model : models) {
//				pkgListSet.add(model.getNearestPackage());
//			}
//		}
//		return ModelicaMLServices.getSortedByName(pkgListSet);
//	}
//	
//	private void createPkgTreeItems(Tree treeRoot, List<Element> pkgElements){
//		// create package nodes at the 1 level
//		for (Element pkg : pkgElements) {
//
//			TreeItem pkgItem = new TreeItem(treeRoot, 0);
//			
//			TreeItemData data = new TreeItemData();
//			data.setIsPackage(true);
//			data.setPackageElement(pkg);
//
//			pkgItem.setData(data);
//
//			pkgItem.setText(getModelName(pkg) + "  ("+getModelQName(pkg)+")");
//			pkgItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.helper", "icons/Package.gif"));
//		}
//	}
//	
//	
//	private void createNotEvaluatedRequirement(Tree treeRoot, Element requirement){
//		
//		TreeItem pkgItem = getPackageTreeItem(treeRoot, requirement);
//		TreeItem requirementItem = null;
//		if (pkgItem != null) {
//			requirementItem = new TreeItem(pkgItem, 0);	
//		}
//		else {
//			requirementItem = new TreeItem(treeRoot, 0);
//		}
//		
//		TreeItemData data = new TreeItemData();
//		data.setIsRequirement(true);
//		data.setRequirementElement(requirement);
//		
//		requirementItem.setData(data);
//		requirementItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
//		requirementItem.setText(getModelName(requirement));
//	}
//	
//	
//	private void createNotSimulatedItems(Tree treeRoot){
//
//		for (String modelQName : gmd.getSimulationFailedList()) {
//
//			for (Element modelElement : gmd.getGeneratedModels()) {
//				if (getModelQName((NamedElement) modelElement).equals(modelQName)) {
//					TreeItem failedModelItem = new TreeItem(treeRoot,SWT.NONE);
//					
//					failedModelItem.setText(getModelName(modelElement) + "  ("+getModelQName(modelElement)+")");
//					// decorate with an error overlay
//					failedModelItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "icons/Class.gif")));
//				}
//			}
//		}
//	}
//	
//	private TreeItem getPackageTreeItem(Tree treeRoot, Element testSimulationModel){
//		
//		TreeItem pkgItem = null;
//		// find the right package node at the 1 tree-level.
//		TreeItem[] pkgItems = treeRoot.getItems();
//		for (TreeItem treeItem : pkgItems) {
//			TreeItemData data = (TreeItemData) treeItem.getData();
//			if (data.isPackage && data.getPackageElement().equals(testSimulationModel.getNearestPackage())) {
//				return treeItem; 
//			}
//		}
//		return pkgItem;
//	}
//	
//
//	
//	private void createScenarioTreeItem(Tree treeRoot, Element scenario, boolean isViolatedRequirementsTree, boolean isNotViolatedRequirementsTree, boolean isNotEvaluatedRequirementsTree){
//		
//		// if this tree has requirements 
//		HashSet<Element> requirements = null;
//		if (isViolatedRequirementsTree) {
//			requirements = gmd.getScenarioToViolatedRequirements().get(scenario);
//		}
//		else if (isNotViolatedRequirementsTree) {
//			HashSet<Element> evaluatedRequirements = gmd.getScenarioToEvaluatedRequirements().get(scenario);
//			HashSet<Element> violatedRequirements = gmd.getScenarioToViolatedRequirements().get(scenario);
//			HashSet<Element> notViolatedRequirements = new HashSet<Element>();
//			if (evaluatedRequirements != null) {
//				notViolatedRequirements.addAll(evaluatedRequirements);
//				if (violatedRequirements != null) {
//					notViolatedRequirements.removeAll(violatedRequirements);
//				}
//			}
//			requirements = notViolatedRequirements;
//		}
////		else if (isNotEvaluatedRequirementsTree) {
////			requirements = gmd.getScenarioToNotEvaluatedRequirements().get(scenario);
////		}
//			
//		if (requirements != null && requirements.size() > 0 ) {
//		
//			TreeItem pkgItem = getPackageTreeItem(treeRoot, scenario);
//			TreeItem scenarioItem = null;
//			if (pkgItem != null) {
//				scenarioItem = new TreeItem(pkgItem, 0);	
//			}
//			else {
//				scenarioItem = new TreeItem(treeRoot, 0);
//			}
//			
//			TreeItemData data = new TreeItemData();
//			data.setScenario(true);
//			data.setScenarioElement(scenario);
//			
//			scenarioItem.setData(data);
//			
//			scenarioItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
//			scenarioItem.setText(getModelName(scenario));
//		
//			// create requirement nodes
//			for (Element requirement : ModelicaMLServices.getSortedByName(requirements)) {
//				createRequirementTreeItem(requirement, scenarioItem,  isViolatedRequirementsTree, isNotViolatedRequirementsTree, isNotEvaluatedRequirementsTree);
//			}
//		}
//	}
//	
//	
//	private void createRequirementTreeItem(Element requirement, TreeItem scenarioItem, boolean isViolatedRequirementsTree, boolean isNotViolatedRequirementsTree, boolean isNotEvaluatedRequirementsTree){
//
//		TreeItem requirementItem = new TreeItem(scenarioItem, 0);	
//
////		TreeItemData scenarioData = (TreeItemData) scenarioItem.getData();
////		Element scenario = scenarioData.getScenarioElement();
//
//		TreeItemData data = new TreeItemData();
//		data.setIsRequirement(true);
//		data.setRequirementElement(requirement);
//		
//		requirementItem.setData(data);
//		
//		requirementItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
//
//		String name = getModelName(requirement);
//		
//		if (isViolatedRequirementsTree) {
////			name = "(VIOLATED) " + name;
////			requirementItem.setImage(decorateWarning(requirementItem.getImage()));
////			propagateWarning(requirementItem);
//			
//			requirementItem.setImage(decorateError(requirementItem.getImage()));
//			propagateError(requirementItem);
//		}
//		
//		requirementItem.setText(name);
//	}

	// Utls ************************************************************************ 

	/*
	 * Propagates 2 levels up
	 */
	private void propagateError(TreeItem requirementItem){
		TreeItem parentItem1 = requirementItem.getParentItem();
		TreeItem parentItem2 = parentItem1.getParentItem();
		
		parentItem1.setImage(decorateError(parentItem1.getImage()));
		parentItem2.setImage(decorateError(parentItem2.getImage()));
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
		private boolean isVeM = false;

		private boolean isRequirement = false;
		private boolean isPackage = false;
		
		private boolean isProperty = false;
		private boolean isClientProperty = false;
		
		private Element UMLElement;
		private TreeObject treeObject;
		
		private Element scenarioElement;
		private Element requirementElement;
		private Element packageElement;
		private Element VeMElement;
		private Element propertyElement;
		
		
		
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
		
		public boolean isRequirement() {
			return isRequirement;
		}
		public void setIsRequirement(boolean isRequirement) {
			this.isRequirement = isRequirement;
		}
		public boolean isProperty() {
			return isProperty;
		}
		public void setIsProperty(boolean isProperty) {
			this.isProperty = isProperty;
		}
		public boolean isVeM() {
			return isVeM;
		}
		public void setIsVeM(boolean isModel) {
			this.isVeM = isModel;
		}
		public boolean isClientProperty() {
			return isClientProperty;
		}
		public void setClientProperty(boolean isClientProperty) {
			this.isClientProperty = isClientProperty;
		}
				
		

		public void setPackageElement(Element _packageElement) {
			packageElement = _packageElement;
			setUMLElement(_packageElement);
		}
		public Element getPackageElement() {
			return packageElement;
		}

		public Element getScenarioElement() {
			return scenarioElement;
		}
		public void setScenarioElement(Element scenarioElement) {
			this.scenarioElement = scenarioElement;
			setUMLElement(scenarioElement);
		}
		public Element getRequirementElement() {
			return requirementElement;
		}
		public void setRequirementElement(Element requirementElement) {
			this.requirementElement = requirementElement;
			setUMLElement(requirementElement);
		}

		public Element getVeMElement() {
			return VeMElement;
		}
		public void setVeMElement(Element modelElement) {
			this.VeMElement = modelElement;
			setUMLElement(modelElement);
		}
		public Element getPropertyElement() {
			return propertyElement;
		}
		public void setPropertyElement(Element propertyElement) {
			this.propertyElement = propertyElement;
			setUMLElement(propertyElement);
		}
		public Element getUMLElement() {
			return UMLElement;
		}
		public void setUMLElement(Element uMLElement) {
			UMLElement = uMLElement;
		}
		public TreeObject getTreeObject() {
			return treeObject;
		}
		public void setTreeObject(TreeObject treeObject) {
			this.treeObject = treeObject;
		}

	}
	
	
	
	
	public boolean isOpenedFromNewRelationsDialog() {
		return openedFromNewRelationsDialog;
	}

	public void setOpenedFromNewRelationsDialog(boolean openedFromNewRelationsDialog) {
		this.openedFromNewRelationsDialog = openedFromNewRelationsDialog;
	}

	public HashMap<Element,String> getSimulationResultsFiles() {
		return simulationResultsFiles;
	}

	public void setSimulationResultsFiles(HashMap<Element,String> simulationResultsFiles) {
		this.simulationResultsFiles = simulationResultsFiles;
	}
	
	public GeneratedModelsData getGmd() {
		return gmd;
	}

	public void setGmd(GeneratedModelsData gmd) {
		this.gmd = gmd;
	}
}

