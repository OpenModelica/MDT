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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.relations.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.relations.Activator;
import org.openmodelica.modelicaml.relations.dialogs.SearchDialog;
import org.openmodelica.modelicaml.relations.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.relations.model.TreeBuilder;
import org.openmodelica.modelicaml.relations.model.TreeObject;
import org.openmodelica.modelicaml.relations.model.TreeParent;



public class RelationsView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.openmodelica.modelicaml.relations.views.RelationsView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
//	private Action doubleClickAction;

	private TreeParent invisibleRoot;
	
	TreeBuilder treeBuilder = new TreeBuilder();
	public final int DEFAULT_EXPAND_LEVEL = 2;
	
	// actions
	private Action actionLinkWithEditor;
	private Action actionLocateInPapyrusModelExplorer;
	private Action actionLocateDependencyInPapyrusModelExplorer;
	private Action actionLocateInheritanceInPapyrusModelExplorer;
	private Action actionCollapseAll;
	private Action actionReload;

	
	//##################### Selection handling
		 private ISelectionListener selectionListener = new ISelectionListener() {
			 public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
				 if (actionLinkWithEditor.isChecked() && sourcepart != RelationsView.this && selection instanceof IStructuredSelection) {
		        	List<Object> selectedElements = getCurrentSelections();
					List<Object> selectedElementsAdopted = new ArrayList<Object>();

		        	if (selectedElements != null && selectedElements.size() > 0 ) {
						for (Object object : selectedElements) {
							EObject eObj = adaptSelectedElement(object);
							if (eObj != null) {
								selectedElementsAdopted.add(eObj);
							}
						}
					}
		        	
		        	List<TreeObject> list = new ArrayList<TreeObject>();
		        	list.addAll(treeBuilder.findTreeItems(selectedElementsAdopted.toArray()));
					
		        	if ( selectedElementsAdopted.size() > 0 ) {
						viewer.setSelection(new StructuredSelection(list), true);
					}
		        }
		    }
		};

		private Action actionFind;

		private Action actionShowRelationsFromLibs;
	

	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				if (invisibleRoot==null) initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}
		
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject)child).getParent();
			}
			return null;
		}
		
		public Object [] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent)parent).getChildren();
			}
			return new Object[0];
		}
		
		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent)parent).hasChildren();
			return false;
		}

		private void initialize() {
			// add the selection listener
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);

			invisibleRoot = new TreeParent("");
		}
	}

	
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public RelationsView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProviderStyledCell());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.relations.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		
		// double click for opening/closing the sub tree
		viewer.addOpenListener(new IOpenListener() {
			public void open(final OpenEvent event) {
				Object firstElement = ((IStructuredSelection)
				event.getSelection()).getFirstElement();
				if (viewer.getExpandedState(firstElement)) {
					viewer.collapseToLevel(firstElement, AbstractTreeViewer.ALL_LEVELS);
				} 
				else {
					viewer.expandToLevel(firstElement, 1);
				}
			}
		});
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				RelationsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(new Separator());
		manager.add(actionShowRelationsFromLibs);
	}

	@SuppressWarnings("unchecked")
	private void fillContextMenu(IMenuManager manager) {
		ISelection selectedElements = viewer.getSelection();
		List<Object> selectedTreeItems = null;
		if (selectedElements instanceof IStructuredSelection) {
			selectedTreeItems = ((IStructuredSelection)selectedElements).toList();
		}
		
		Object firstSelectedTreeItem = selectedTreeItems.get(0);
		if (firstSelectedTreeItem instanceof TreeObject && ((TreeObject)firstSelectedTreeItem).getUmlElement() != null) {
			manager.add(actionLocateInPapyrusModelExplorer);
		}
		if (firstSelectedTreeItem instanceof TreeObject && ((TreeObject)firstSelectedTreeItem).getDependencyElement() != null) {
			manager.add(actionLocateDependencyInPapyrusModelExplorer);
		}
		if (firstSelectedTreeItem instanceof TreeObject && ((TreeObject)firstSelectedTreeItem).getGeneralzationElement() != null) {
			manager.add(actionLocateInheritanceInPapyrusModelExplorer);
		}
		
		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionFind);
		manager.add(new Separator());
		manager.add(actionReload);
		manager.add(new Separator());
		manager.add(actionCollapseAll);
		manager.add(actionLinkWithEditor);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	
	private void makeActions() {

		actionReload = new Action() {
			public void run() {
				
				Object[] expandedElements = viewer.getExpandedElements();
				TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
				
				treeBuilder.initialize(actionShowRelationsFromLibs.isChecked());
				treeBuilder.showTree(invisibleRoot);
				viewer.refresh();
				
				viewer.setExpandedElements(expandedElements);
				viewer.setExpandedTreePaths(expandedTreePaths);

				viewer.expandToLevel(2);
			}
		};
		actionReload.setText("Reload");
		actionReload.setToolTipText("Reload");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/refresh.gif"));
		
		actionLinkWithEditor = new Action("actionLinkWithEditor", 2) { //check box 
			public void run() {
			}
		};
		actionLinkWithEditor.setText("Link with Model Explorer");
		actionLinkWithEditor.setToolTipText("Link with Model Explorer");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
		
		
		
		actionLocateInPapyrusModelExplorer = new Action("actionLocateInPapyrusModelExplorer") {
			public void run() {
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();

					if (obj instanceof TreeObject ) {
						Object object = ((TreeObject)obj).getUmlElement();
						EditorServices.locateInModelExplorer(object, false);
					}
				}
			}
		};
		actionLocateInPapyrusModelExplorer.setText("Locate in Model Explorer");
		actionLocateInPapyrusModelExplorer.setToolTipText("Locate in Model Explorer");
		actionLocateInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png")));
		
		
		actionLocateDependencyInPapyrusModelExplorer = new Action("actionLocateDependencyInPapyrusModelExplorer") {
			public void run() {
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					if (obj instanceof TreeObject ) {
						Object object = ((TreeObject)obj).getDependencyElement();
						EditorServices.locateInModelExplorer(object, false);
					}
				}
			}
		};
		actionLocateDependencyInPapyrusModelExplorer.setText("Locate Dependency in Model Explorer");
		actionLocateDependencyInPapyrusModelExplorer.setToolTipText("Locate Dependency in Model Explorer");
		actionLocateDependencyInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/Dependency.gif"));
		
		actionLocateInheritanceInPapyrusModelExplorer = new Action("actionLocateInheritanceInPapyrusModelExplorer") {
			public void run() {
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					if (obj instanceof TreeObject ) {
						Object object = ((TreeObject)obj).getGeneralzationElement();
						EditorServices.locateInModelExplorer(object, false);
					}
				}
			}
		};
		actionLocateInheritanceInPapyrusModelExplorer.setText("Locate Generalization in Model Explorer");
		actionLocateInheritanceInPapyrusModelExplorer.setToolTipText("Locate Generalization in Model Explorer");
		actionLocateInheritanceInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/Generalization.gif"));

		
		actionCollapseAll = new Action("actionCollapseAll") { //obviously a check box style
			public void run() {
				TreeObject[] children = invisibleRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					if (children[i] instanceof TreeParent) {
						TreeObject[] children2Level = ((TreeParent) children[i]).getChildren();
						for (TreeObject treeObject : children2Level) {
							viewer.collapseToLevel(treeObject, AbstractTreeViewer.ALL_LEVELS);	
						}
					}
					else {
						viewer.collapseToLevel(children[i], AbstractTreeViewer.ALL_LEVELS);	
					}
				}
			}
		};
		actionCollapseAll.setText("Collapse all");
		actionCollapseAll.setToolTipText("Collapse all");
		actionCollapseAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
		
		
		actionFind = new Action("actionFind") {
			public void run() {
				SearchDialog searchDialog = new SearchDialog(ModelicaMLServices.getShell(), viewer, invisibleRoot);
				searchDialog.open();
			}
		};
		actionFind.setText("Find");
		actionFind.setToolTipText("Find");
		actionFind.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/find.png"));
		
		
		actionShowRelationsFromLibs = new Action("actionShowRelationsFromLibs", 2) {
			public void run() {
				actionReload.run();
			}
		};
		actionShowRelationsFromLibs.setText("Show also relations from libraries");
		actionShowRelationsFromLibs.setChecked(false);
		actionShowRelationsFromLibs.setToolTipText("Show also relations from libraries");
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
//				doubleClickAction.run();
			}
		});
	}
	

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Object> getCurrentSelections() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			return structuredSelection.toList();
		}
		return null;
	}
	
	protected EObject adaptSelectedElement( Object selection) {
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
}