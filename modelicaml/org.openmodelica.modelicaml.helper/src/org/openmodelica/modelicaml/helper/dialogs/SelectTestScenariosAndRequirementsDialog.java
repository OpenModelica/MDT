package org.openmodelica.modelicaml.helper.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.helper.impl.TestScenariosCollector;

public class SelectTestScenariosAndRequirementsDialog extends TitleAreaDialog {

	private HashSet<Element> selectedTestScenarios;
	private HashSet<Element> discardedTestScenarios;
	private HashSet<Element> selectedRequirements;
	private HashSet<Element> discardedRequirements;
	private Element systemModel;
	private TestScenariosCollector tsc;
	
	private String collectionLog;
	
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
//		tbtmTestScenarios.setImage(org.eclipse.wb.swt.ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/obj16/info_tsk.gif"));
//		tbtmTestScenarios.setImage(org.eclipse.wb.swt.ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/elcl16/close_view.gif"));
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

	private void clearAllLists(){
		selectedTestScenariosWithRequirements.clear();
		treeItems.clear();
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

	
	private HashMap<Element,HashSet<Element>> selectedTestScenariosWithRequirements = new HashMap<Element,HashSet<Element>>();
	public HashMap<Element, HashSet<Element>> getSelectedTestScenariosWithRequirements() {
		return selectedTestScenariosWithRequirements;
	}


	private HashSet<TreeItem> treeItems = new HashSet<TreeItem>();
	
	private void buildTree(Tree treeRoot){
		String testScenarioNamePrefix = "ts: ";
		String requirementNamePrefix = "req: ";
		if (this.selectedTestScenarios != null) {

			// Sort
			List<Element> selectedTestScenariosSorted = getSortedByName(this.selectedTestScenarios);
			for (Element testScenario : selectedTestScenariosSorted) {
				if (testScenario instanceof NamedElement) {
					
					TreeItem testScenarioItem = new TreeItem(treeRoot, 0);
					treeItems.add(testScenarioItem);
					testScenarioItem.setText(testScenarioNamePrefix + ((NamedElement)testScenario).getName() + "  ("+((NamedElement)testScenario).getQualifiedName()+")");
					testScenarioItem.setData(testScenario);
					testScenarioItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
					// preselect
					testScenarioItem.setChecked(true);
					// initially add to map
					selectedTestScenariosWithRequirements.put(testScenario, new HashSet<Element>());
					
//					HashSet<Element> associatedRequirements = tsc.getTsToReq().get(testScenario);
					// Sort 
					List<Element> associatedRequirements = getSortedByRequirementId(tsc.getTsToReq().get(testScenario));
					if (associatedRequirements != null) {
						
						TreeItem testScenarioReqInfoNodeItem = new TreeItem(testScenarioItem, 0);
//						treeItems.add(testScenarioReqInfoNodeItem);
						testScenarioReqInfoNodeItem.setText("Can be used to verify '" + ((NamedElement)systemModel).getName() + "' against the following requirements: ");
						testScenarioReqInfoNodeItem.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/obj16/info_tsk.gif"));
						testScenarioReqInfoNodeItem.setGrayed(true);
						
						for (Element req : associatedRequirements) {
							String prefix = requirementNamePrefix;
							
//							TreeItem reqItem = new TreeItem(testScenarioItem, 0);
							TreeItem reqItem = new TreeItem(testScenarioReqInfoNodeItem, 0);
							treeItems.add(reqItem);
							
							if (this.discardedRequirements.contains(req)) {
								prefix = prefix + "(INCONSISTENT) ";
//								reqItem.setImage(ResourceManager.getPluginImage("org.eclipse.jdt.ui", "/icons/full/eview16/errorwarning_tab.gif"));
								reqItem.setImage(decorateError(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif")));
								
								// indicate inconsistencies
								testScenarioItem.setImage(decorateWarning(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif")));

								// preselect 
								reqItem.setChecked(false);
							}
							else {
								reqItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/requirement.gif"));
								reqItem.setChecked(true);
								
								// initially add to map
								addToTestScenarioToRequirementsMap(testScenario, req);
							}
							prefix = prefix + ModelicaMLServices.getRequirementID(req) + " - ";

							reqItem.setText(prefix + ((NamedElement)req).getName()  + "  ("+((NamedElement)req).getQualifiedName()+")");
							
							RequirementTreeItemData rData = new RequirementTreeItemData();
							rData.setRequirementElement(req);
							rData.setTesScenarioElement(testScenario);
							
							reqItem.setData(rData);
						}
					}
				}
			}
			
			// add discarded test scenarios so that the user can still decide to generate the simulation model for them
			for (Element discardedTestScenario : discardedTestScenarios) {
				TreeItem testScenarioItem = new TreeItem(treeRoot, 0);
				treeItems.add(testScenarioItem);
				testScenarioItem.setText(testScenarioNamePrefix + "(DISCARDED) " + ((NamedElement)discardedTestScenario).getName() + "  ("+((NamedElement)discardedTestScenario).getQualifiedName()+")");
				testScenarioItem.setData(discardedTestScenario);

				testScenarioItem.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif"));
//				testScenarioItem.setImage(decorateWarning(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/elcl16/close_view.gif")));
				testScenarioItem.setImage(decorateWarning(ResourceManager.getPluginImage("org.openmodelica.modelicaml.profile", "resources/icons/icons16/calculationModel.gif")));
				testScenarioItem.setChecked(false);

			}
		}
	}
	
	//********* Utls
	
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
	
	// ******* Image
	
	private final ImageDescriptor warningImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);
	private final ImageDescriptor errorImageDescriptor = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	
	public Image decorateWarning(Image image) {
		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
	public Image decorateError(Image image) {
		return new DecorationOverlayIcon(image, errorImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
	}
	
//	public Image decorateDiscarded(Image image) {
//		return new DecorationOverlayIcon(image, warningImageDescriptor, IDecoration.BOTTOM_RIGHT).createImage();
//	}
	
	
	// ************ Listener
	
	class CheckboxTreeSelectionListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			if (event.detail == SWT.CHECK && event.item instanceof TreeItem) {
				// requirement item
				if (event.item.getData() instanceof RequirementTreeItemData) {
					RequirementTreeItemData data = (RequirementTreeItemData) event.item.getData(); 
					Element reqElement = data.getRequirementElement();
					Element testScenarioElement = data.getTesScenarioElement();
					
					if ( ((TreeItem)event.item).getChecked() ) {
						addToTestScenarioToRequirementsMap(testScenarioElement, reqElement);
//						System.err.println("ADD requirement to map.");
					}
					else {
						removeFromTestScenarioToRequirementsMap(testScenarioElement, reqElement);
//						System.err.println("remove requirement from map.");
					}
				}
				
				// test scenario tree item
				else if (event.item.getData() instanceof NamedElement) {
					Element tesSscenario = (Element) event.item.getData();
					
					if ( ((TreeItem)event.item).getChecked() ) {
//						System.err.println("ADD test scenario to map.");
					
						selectedTestScenariosWithRequirements.put(tesSscenario, new HashSet<Element>());
						
						TreeItem[] children = ((TreeItem)event.item).getItems();
						for (int i = 0; i < children.length; i++) {
							TreeItem reqItem = children[i];
							if (reqItem.getChecked()) {
								RequirementTreeItemData data = (RequirementTreeItemData) reqItem.getData();
								addToTestScenarioToRequirementsMap(tesSscenario, data.getRequirementElement());
							}
						}
					}
					else {
						selectedTestScenariosWithRequirements.remove(tesSscenario);
//						System.err.println("remove test scenario from map");	
					}
				}
			}
		}
	}
	
	
	class RequirementTreeItemData{
		private Element requirementElement;
		private Element tesScenarioElement;
		public void setRequirementElement(Element requirementElement) {
			this.requirementElement = requirementElement;
		}
		public Element getRequirementElement() {
			return requirementElement;
		}
		public void setTesScenarioElement(Element tesScenarioElement) {
			this.tesScenarioElement = tesScenarioElement;
		}
		public Element getTesScenarioElement() {
			return tesScenarioElement;
		}
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
		HashSet<Element> set = selectedTestScenariosWithRequirements.get(testScenario);
		HashSet<Element> updatedSet = new HashSet<Element>();
		
		updatedSet.addAll(set);
		updatedSet.remove(requirement);
		selectedTestScenariosWithRequirements.put(testScenario, updatedSet);
		
	}

	
	
	
	
	
//	private void buildTestScenariosTree(TestScenariosAndRequirementsSelectionTreeParent parent) {
//		HashSet<Element> selectedTestScenarios = this.selectedTestScenarios;
//		if (selectedTestScenarios != null) {
//			for (Element element : selectedTestScenarios) {
//				if (element instanceof NamedElement) {
//					TestScenariosAndRequirementsSelectionTreeParent testScenarioItem = new TestScenariosAndRequirementsSelectionTreeParent(((NamedElement)element).getName());
//					testScenarioItem.setUmlElement(element);
//					parent.addChild(testScenarioItem);
//					
//					HashSet<Element> associatedRequirements = tsc.getTsToReq().get(element);
//					if (associatedRequirements != null) {
//						for (Element req : associatedRequirements) {
//							String prefix = "";
//							if (this.discardedRequirements.contains(req)) {
//								prefix = prefix + "(DISCARDED )";
//							}
//							TestScenariosAndRequirementsSelectionTreeObject reqItem = 
//								new TestScenariosAndRequirementsSelectionTreeObject(prefix + ((NamedElement)req).getName());
//							reqItem.setUmlElement(req);
//							
//							testScenarioItem.addChild(reqItem);
//						}
//					}
//				}
//			}
//		}
//		
//	}

	
//	// **************** Label Provider
//	class LabelProvider extends StyledCellLabelProvider{
//		@Override
//		public void update(ViewerCell cell) {
//			Object element = cell.getElement();
//			cell.setText(element.toString());
////			cell.setStyleRanges(text.getStyleRanges());
//			super.update(cell);
//		}
//
//	}
	
	
	// **************** INPUTS
//	
//	
//	class TestScenariosInput{
//	}
//
//	class RequirementsInput{
//		
//	}

	
	//************** Tree handling
	
//	private TestScenariosAndRequirementsSelectionTreeParent invisibleRoot = null;
//	
//	class SimulationModelsGenerationSelectionTreeContentProvider implements
//			ITreeContentProvider {
//
//		@Override
//		public void dispose() {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public Object[] getElements(Object inputElement) {
//			invisibleRoot = new TestScenariosAndRequirementsSelectionTreeParent("");
//			if (inputElement instanceof TestScenariosInput) {
//				buildTestScenariosTree(invisibleRoot);	
//			}
//			return getChildren(invisibleRoot);
//		}
//
//		@Override
//		public Object[] getChildren(Object parentElement) {
//			if (parentElement instanceof TestScenariosAndRequirementsSelectionTreeParent) {
//				return ((TestScenariosAndRequirementsSelectionTreeParent) parentElement)
//						.getChildren();
//			}
//			return new Object[0];
//		}
//
//		@Override
//		public Object getParent(Object element) {
//			if (element instanceof TestScenariosAndRequirementsSelectionTreeObject) {
//				return ((TestScenariosAndRequirementsSelectionTreeObject) element)
//						.getParent();
//			}
//			return null;
//		}
//
//		@Override
//		public boolean hasChildren(Object element) {
//			if (element instanceof TestScenariosAndRequirementsSelectionTreeParent)
//				return ((TestScenariosAndRequirementsSelectionTreeParent) element)
//						.hasChildren();
//			return false;
//		}
//
//	}
	
	
	
	
//	// ****************** Listeners 
//	/**
//	 * Implements a SelectionListener with standard behaviour for Checkbox-Trees.
//	 * 
//	 * Example:
//	 * Display display = new Display();
//	 * Shell shell = new Shell(display);
//	 * Tree tree = new Tree(shell, SWT.BORDER | SWT.CHECK);
//	 * tree.addSelectionListener(new CheckboxTreeSelectionListener());
//	 * 
//	 * @author Torsten Witte
//	 */
//	
//	class CheckboxTreeSelectionListener extends SelectionAdapter {
//
//		/** 
//		 * {@inheritDoc}
//		 */
//		public void widgetSelected(SelectionEvent event) {
//			if (event.detail == SWT.CHECK) {
//				this.handleCheckedItem((TreeItem) event.item);
//			}
//		}
//		
//		/**
//		 * Updates the check-status of every affected <code>TreeItem</code> if the given item has been checked or unchecked. 
//		 * @param item the <code>TreeItem</code>
//		 */
//		private void handleCheckedItem(TreeItem item) {
//			//somebody selected the item, so it can't be grayed
//			item.setGrayed(false);
//			//check all children of the selected item
//			this.checkAllChildren(item);
//			//check all parents of the selected item
//			this.checkAllParents(item);
//		}
//		
//		/**
//		 * Checks all children of the given <code>TreeItem</code> like that item (checked or unchecked).
//		 * <code>Grayed</code> is set to <code>false</code> at all children in any case, because every child
//		 * just can be checked or unchecked.
//		 * @param parent the <code>TreeItem</code>
//		 */
//		private void checkAllChildren(TreeItem parent) {
//			for (TreeItem child : parent.getItems()) {
//				child.setChecked(parent.getChecked());
//				child.setGrayed(false);
//				this.checkAllChildren(child);
//			}
//		}
//		
//		/**
//		 * Checks all parents of the given <code>TreeItem</code> depend on the check-status of their cildren.
//		 * @param item the <code>TreeItem</code>
//		 */
//		private void checkAllParents(TreeItem item) {
//			TreeItem parent = item.getParentItem();
//			if (parent!=null) {
//				if (allDirectChildrenChecked(parent)) {
//					parent.setGrayed(false);
//					parent.setChecked(true);
//				} else {
//					if (allDirectChildrenUnchecked(parent)) {
//						parent.setGrayed(false);
//						parent.setChecked(false);
//					} else {
//						parent.setGrayed(true);
//						parent.setChecked(true);
//					}
//				}
//				this.checkAllParents(parent);
//			}
//		}
//		
//		/**
//		 * Returns whether all direct children of the given parent are really checked or not.
//		 * Really checked means, that all children are checked without being grayed.
//		 * @param parent the <code>TreeItem</code>
//		 * @return <code>true</code>, if all direct children of the given parent are really checked, <code>false</code> otherwise
//		 */
//		private boolean allDirectChildrenChecked(TreeItem parent) {
//			TreeItem[] children = parent.getItems();
//			for (TreeItem child : children) {
//				if (!child.getChecked() || (child.getChecked() && child.getGrayed())) {
//					return false;
//				}
//			}
//			return true;
//		}
//		
//		/**
//		 * Returns whether all direct children of the given parent are really unchecked or not.
//		 * Really unchecked means, that all children are unchecked never mind being grayed or not.
//		 * @param parent the <code>TreeItem</code>
//		 * @return <code>true</code>, if all direct children of the given parent are unchecked, <code>false</code> otherwise
//		 */
//		private boolean allDirectChildrenUnchecked(TreeItem parent) {
//			TreeItem[] children = parent.getItems();
//			for (TreeItem child : children) {
//				if (child.getChecked()) {
//					return false;
//				}
//			}
//			return true;
//		}
//
//	}
}
