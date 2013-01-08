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
package org.openmodelica.modelicaml.view.valuebindings.views;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.modelexplorer.ModelExplorerPage;
import org.openmodelica.modelicaml.profile.handlers.CreateValueMediatorHandler;
import org.openmodelica.modelicaml.profile.handlers.CreateValueMediatorsContainerHandler;
import org.openmodelica.modelicaml.view.valuebindings.Activator;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.ElementSelectionDialog;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.SearchDialog;
import org.openmodelica.modelicaml.view.valuebindings.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.view.valuebindings.handlers.DeleteCommandHandler;
import org.openmodelica.modelicaml.view.valuebindings.listeners.DropListener;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeBuilder;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeObject;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;
import org.openmodelica.modelicaml.view.valuebindings.validation.ValueBindingsValidator;

public class ValueBindingsView extends ViewPart implements ITabbedPropertySheetPageContributor, IAdaptable, IGotoMarker {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	
	private Action actionAssociateValueClient;
	private Action actionAssociateValueProvider;
	
	private Action actionDeleteReference;
	
	private Action doubleClickAction;

	public Action actionReload;
	private Action actionLinkWithEditor;

	private Action actionAddValueMediator;
	private Action actionAddValueMediatorContainer;
	private Action actionCollapseAll;
	private Action actionDeleteModelElement;
	private Action actionLocateInPapyrusModelExplorer;
	
	private TreeParent invisibleRoot = null;
	public TreeBuilder treeBuilder = new TreeBuilder();

	private Action actionShowClientPerspective;
	private IAction actionShowMediatorPerspective;
	private IAction actionShowProviderPerspective;

	private Action actionInstantiatedClassMode;

	private Action actionShowUserNoteForReadOnlyNodes;

	private Action actionValidate;

	private Action actionShowRequiredClients;

	private Action actionClear;

	private Action actionFind;
	
	public final static int DEFAULT_EXPAND_LEVEL = 2;
	public final static int DEFAULT_EXPAND_LEVEL_CLIENTS = 1;


	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

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
			
			
			
			ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(ModelicaMLServices.getShell());
			progressDialog.getProgressMonitor().setTaskName("Collecting building data ...");
			
			try {
				progressDialog.run(false, true, new IRunnableWithProgress() {
					
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,
							InterruptedException {

						monitor.subTask("Building tree from the ModelicaML model ...");
						
						invisibleRoot = new TreeParent("");
						treeBuilder.buildTreeFromUmlModel(invisibleRoot);
						((ViewLabelProviderStyledCell)viewer.getLabelProvider()).setUmlModel(treeBuilder.getUmlModel());
						
						monitor.done();
					}
				});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				MessageDialog.openError(ModelicaMLServices.getShell(), "Collecting Binding Data Error", "Could not invoke bindings reload. Please click on the diagram editor to activate the ModelicaML editor and try again. ");
			} catch (InterruptedException e) {
				e.printStackTrace();
				MessageDialog.openInformation(ModelicaMLServices.getShell(), "Collecting Binding Data Canceled", "Bindings reload was canceled");
			}
			
//			invisibleRoot = new TreeParent("");
//			treeBuilder.buildTreeFromUmlModel(invisibleRoot);
//			((ViewLabelProviderStyledCell)viewer.getLabelProvider()).setUmlModel(treeBuilder.getUmlModel());
		}
	}
	
	
	class NameSorter extends ViewerSorter {
		
	}


	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
//		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setLabelProvider(new ViewLabelProviderStyledCell());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		
		ViewerFilter[] filters = {mediatorPerspectiveFilter};
		viewer.setFilters(filters);
		viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.view.valuebindings.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();

		// add a selection provider.
		getSite().setSelectionProvider(viewer);

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
		
		//add selection listener for reloading in case if uml resource were modified
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof StructuredSelection) {
					Object selectedTreeObject = ((StructuredSelection)event.getSelection()).getFirstElement();
					if ( selectedTreeObject instanceof TreeObject) {
						TreeObject item = (TreeObject)selectedTreeObject;
						// After saving of loaded resources the tree items uml elements are proxies that need to be resolved.
						// detect it : if it is a mediator and its uml element is a proxy or it is client/provider and its mediator is not loaded.
						// and reload the view.
	
						if (	!item.isReadOnly() && (
									(item.getUmlElement() != null && ((EObject)item.getUmlElement()).eIsProxy()) 
									|| ((item.isValueClient() || item.isValueProvider()) && !TreeUtls.mediatorIsLoaded(item))
								)
							) {
							
							// reload the view and set the element that was selected before reload.
							actionReload.run();
							
//							List<Object> list = new ArrayList<Object>();
//							list.add(item);
//							ISelection selection = new StructuredSelection(list);
//							viewer.setSelection(selection);
						}
					}
				}
			}
		});
		
		// add drop support
		int operations = DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[]{ LocalSelectionTransfer.getTransfer()};
		viewer.addDropSupport(operations, transferTypes, new DropListener(viewer));
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ValueBindingsView.this.fillContextMenu(manager);
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
		manager.add(actionShowMediatorPerspective);
		manager.add(actionShowClientPerspective);
		manager.add(actionShowProviderPerspective);
		manager.add(new Separator());
		manager.add(actionShowRequiredClients);
//		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		ISelection selection = viewer.getSelection();
		Object selectedTreeObject = ((IStructuredSelection)selection).getFirstElement();
		if (selectedTreeObject instanceof TreeObject) {
			TreeObject item = ((TreeObject)selectedTreeObject);
			
			if (item.isPackage()) {
//				actionAddValueMediatorContainer.setText("Add Value Mediator Container to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				actionAddValueMediatorContainer.setText("Add Value Mediator Container");
				manager.add(actionAddValueMediatorContainer);
				
//				manager.add(new Separator());
//				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
//				manager.add(actionDeleteModelElement);
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if (item.isValueMediatorContainer()) {
//				actionAddValueMediator.setText("Add Value Mediator to '" + ((TreeObject)selectedTreeObject).getName() + "'");
				actionAddValueMediator.setText("Add Value Mediator");
				manager.add(actionAddValueMediator);
				
//				manager.add(new Separator());
//				actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
//				manager.add(actionDeleteModelElement);
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if (item.isValueMediator()) {
				if ( !item.isReadOnly() ) { // read only items shall not be modified
//					actionAssociateValueClient.setText("Add Value Client to '" + ((TreeObject)selectedTreeObject).getName() + "'");
					actionAssociateValueClient.setText("Add Value Client");
					manager.add(actionAssociateValueClient);
					
//					actionAssociateValueProvider.setText("Add Value Provider to '" + ((TreeObject)selectedTreeObject).getName() + "'");
					actionAssociateValueProvider.setText("Add Value Provider");
					manager.add(actionAssociateValueProvider);
					
//					manager.add(new Separator());
//					actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName());
//					manager.add(actionDeleteModelElement);
				}
				else {
					manager.add(actionShowUserNoteForReadOnlyNodes);
				}
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if ( item.isValueClient() || item.isValueProvider()) {
				if ( !item.isReadOnly() ) { // read only items shall not be modified
					actionDeleteReference.setText("Delete the reference to \"" + ((TreeObject)selectedTreeObject).getParent().getParent().getName() + "\"");
					manager.add(actionDeleteReference);
				}
				else {
					manager.add(actionShowUserNoteForReadOnlyNodes);
				}
				
				manager.add(new Separator());
				manager.add(actionLocateInPapyrusModelExplorer);
			}
			else if (item.isValueClientsNode()) {
				if ( !item.isReadOnly() ) { // read only items shall not be modified
					actionAssociateValueClient.setText("Add Value Client");
					manager.add(actionAssociateValueClient);
				}
				else {
					manager.add(actionShowUserNoteForReadOnlyNodes);
				}
			}
			else if (item.isValueProvidersNode()) {
				if ( !item.isReadOnly() ) { // read only items shall not be modified
					actionAssociateValueProvider.setText("Add Value Provider");
					manager.add(actionAssociateValueProvider);
				}
				else {
					manager.add(actionShowUserNoteForReadOnlyNodes);
				}
			}
			
			if (item.isValueMediatorContainer() || item.isValueMediator() ) {
				if ( !item.isReadOnly() ) { // read only items shall not be modified
					manager.add(new Separator());
//					actionDeleteModelElement.setText("Delete '" + ((TreeObject)selectedTreeObject).getName() + "'");
					actionDeleteModelElement.setText("Delete");
					manager.add(actionDeleteModelElement);
				}
			}
		}

//		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
		
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		
		manager.add(actionFind);
		manager.add(new Separator());
		manager.add(actionClear);
		manager.add(new Separator());
		manager.add(actionReload);
		manager.add(new Separator());
		manager.add(actionCollapseAll);
		manager.add(actionLinkWithEditor);
//		manager.add(new Separator());
//		manager.add(actionValidate);
		manager.add(new Separator());
		manager.add(actionInstantiatedClassMode);
		manager.add(new Separator());
		
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {

		actionShowUserNoteForReadOnlyNodes = new Action("actionShowUserNoteForReadOnlyNodes", IAction.AS_PUSH_BUTTON) { 
			public void run() {
				String message = "This is a read-only node. No actions can be performed on it. " +
						"\n\nYou can select a client, mediator or provider and switch " +
						"to the Mediator Perspective in order to enable actions. "
						;
				showMessage(message);
			}
		};
		actionShowUserNoteForReadOnlyNodes.setText("User Note ...");
		actionShowUserNoteForReadOnlyNodes.setToolTipText("User Note ...");
		actionShowUserNoteForReadOnlyNodes.setChecked(true);
//		actionShowUserNoteForReadOnlyNodes.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/reload.png"));
		
		actionShowMediatorPerspective = new Action("actionShowMediatorPerspective", 8) { 
			public void run() {
				if (actionShowMediatorPerspective.isChecked()) {
					
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					ViewerFilter[] filters = {mediatorPerspectiveFilter};
					viewer.setFilters(filters);
					
					// select in view
					TreeUtls.selectInView(obj, invisibleRoot, viewer);
					
					actionShowRequiredClients.setEnabled(true);
				}
				else {
					actionShowRequiredClients.setEnabled(false);
				}
			}
		};
		actionShowMediatorPerspective.setText("Mediator Perspective");
		actionShowMediatorPerspective.setToolTipText("Show Mediator Perspective");
		actionShowMediatorPerspective.setChecked(true);
//		actionShowMediatorPerspective.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/reload.png"));

		
		actionShowClientPerspective = new Action("actionShowClientPerspective", 8) { 
			public void run() {
				if (actionShowClientPerspective.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					viewer.removeFilter(requiredClientFilter);
					actionShowRequiredClients.setChecked(false);
					
					ViewerFilter[] filters = {clientPerspectiveFilter};
					viewer.setFilters(filters);

					// select in view
					TreeUtls.selectInView(obj, invisibleRoot, viewer);
					viewer.expandToLevel(ValueBindingsView.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowClientPerspective.setText("Client Perspective (read-only)");
		actionShowClientPerspective.setToolTipText("Show Client Perspective (read-only)");
//		actionShowClientPerspective.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/reload.png"));
		
		
		actionShowProviderPerspective = new Action("actionShowProviderPerspective", 8) { 
			public void run() {
				if (actionShowProviderPerspective.isChecked()) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					
					viewer.removeFilter(requiredClientFilter);
					actionShowRequiredClients.setChecked(false);
					
					ViewerFilter[] filters = {providerPerspectiveFilter};
					viewer.setFilters(filters);

					// select in view
					TreeUtls.selectInView(obj, invisibleRoot, viewer);
					viewer.expandToLevel(ValueBindingsView.DEFAULT_EXPAND_LEVEL);
				}
			}
		};
		actionShowProviderPerspective.setText("Provider Perspective (read-only)");
		actionShowProviderPerspective.setToolTipText("Show Provider Perspective (read-only)");
//		actionShowProviderPerspective.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/reload.png"));
		
		
		
		actionShowRequiredClients = new Action("actionShowRequiredClientPerspective", 2) { 
			public void run() {
				if (actionShowRequiredClients.isChecked()) {
					Object[] expandedElements = viewer.getExpandedElements();
					TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
					
//					ViewerFilter[] filters = {requiredClientFilter};
//					viewer.setFilters(filters);
					viewer.addFilter(requiredClientFilter);
					
					viewer.setExpandedElements(expandedElements);
					viewer.setExpandedTreePaths(expandedTreePaths);

				}
				else {
					Object[] expandedElements = viewer.getExpandedElements();
					TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
					
					viewer.removeFilter(requiredClientFilter);
//					ViewerFilter[] filters = {mediatorPerspectiveFilter};
//					viewer.setFilters(filters);
					
					viewer.setExpandedElements(expandedElements);
					viewer.setExpandedTreePaths(expandedTreePaths);
				}
			}
		};
		actionShowRequiredClients.setText("Show only required clients");
		actionShowRequiredClients.setToolTipText("Show only required clients");
//		actionShowRequiredClientPerspective.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/reload.png"));
		
		actionLocateInPapyrusModelExplorer = new Action("actionLocateInPapyrusModelExplorer") {
			public void run() {
//				showMessage("The view will be reloaded");
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);

				ModelExplorerPageBookView modelExplorerPageBookView = null;
				if (view instanceof ModelExplorerPageBookView) {
					modelExplorerPageBookView = (ModelExplorerPageBookView)view;
				   }
				
				if (modelExplorerPageBookView != null) {
					ISelection selection = viewer.getSelection();
					Object obj = ((IStructuredSelection)selection).getFirstElement();
					if (obj instanceof TreeObject ) {
						Object object = ((TreeObject)obj).getAdapter(EObject.class);
						if (object instanceof EObject) {
							CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
							if (modelExplorerView != null) {
								List<Object> items = new ArrayList<Object>();
//								items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
								items.add((EObject) object);
								ModelExplorerView.reveal(items, modelExplorerView);
								
//								modelExplorerView.setSelection(new StructuredSelection(items), true);
							}
						}
					}
				}
			}
		};
//		actionLocateInPapyrusModelExplorer.setText("Locate in  Model Explorer");
		actionLocateInPapyrusModelExplorer.setText("Locate in Model Explorer");
		actionLocateInPapyrusModelExplorer.setToolTipText("Locate in Model Explorer");
		actionLocateInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png")));
		
		
		actionReload = new Action("actionReload") {
			public void run() {
				
				ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(ModelicaMLServices.getShell());
				progressDialog.getProgressMonitor().setTaskName("Reloading bindings view");
				
				try {
					progressDialog.run(false, true, new IRunnableWithProgress() {
						
						@Override
						public void run(IProgressMonitor monitor) throws InvocationTargetException,
								InterruptedException {

//							ISelection selection = viewer.getSelection();
//							Object obj = ((IStructuredSelection)selection).getFirstElement();

							Object[] expandedElements = viewer.getExpandedElements();
							TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
							
							TreeObject[] children = invisibleRoot.getChildren();
							for (int i = 0; i < children.length; i++) {
								invisibleRoot.removeChild(children[i]);
							}
							
							if (actionInstantiatedClassMode.isChecked()) {
								
								monitor.subTask("Building tree from the instantiated class ...");
								
								treeBuilder.buildTreeFromInstantiatedClass(invisibleRoot, org.openmodelica.modelicaml.common.instantiation.TreeUtls.classInstantiation, org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot);
								((ViewLabelProviderStyledCell)viewer.getLabelProvider()).setUmlModel(treeBuilder.getUmlModel());
							}
							else {
								
								monitor.subTask("Building tree from the ModelicaML model ...");
								
								treeBuilder.buildTreeFromUmlModel(invisibleRoot);
								((ViewLabelProviderStyledCell)viewer.getLabelProvider()).setUmlModel(treeBuilder.getUmlModel());
							}
							viewer.setInput(getViewSite());

							// validate the client, mediator or provider operation code
							monitor.subTask("Validating clients, mediators and provider operation code ...");
							
							for (int i = 0; i < children.length; i++) {
								TreeObject treeObject = children[i];
								if (!treeObject.isReadOnly() && treeObject instanceof TreeParent) {
									ValueBindingsValidator validator = new ValueBindingsValidator((TreeParent) treeObject);
									validator.setUmlModel(treeBuilder.getUmlModel());
									validator.validate();
									
									viewer.refresh();
								}
							}
							
							// refresh the viewer
							viewer.setExpandedElements(expandedElements);
							viewer.setExpandedTreePaths(expandedTreePaths);
							
//							// select in view
//							TreeUtls.selectInView(obj, invisibleRoot, viewer);
//							viewer.expandToLevel(ValueBindingsView.DEFAULT_EXPAND_LEVEL);
							
							monitor.done();
						}
					});
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					MessageDialog.openError(ModelicaMLServices.getShell(), "Bindings View Reload Error", "Could not invoke bindings reload.");
				} catch (InterruptedException e) {
					e.printStackTrace();
					MessageDialog.openInformation(ModelicaMLServices.getShell(), "Bindings View Reload Canceled", "Bindings reload was canceled");
				}

			}
		};
		actionReload.setText("(Re)load and validate");
		actionReload.setToolTipText("(Re)load and validate");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/refresh.gif"));
		
		
		
		
		actionClear = new Action("actionClear") {
			public void run() {
				// remove all tree items
				TreeObject[] children = invisibleRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					invisibleRoot.removeChild(children[i]);
				}
				
				viewer.setInput(getViewSite());
			}
		};
		actionClear.setText("Clear");
		actionClear.setToolTipText("Clear");
		actionClear.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		
		actionFind = new Action("actionFind") {
			public void run() {
				TreeObject[] children =  invisibleRoot.getChildren();
				TreeParent root = invisibleRoot;
				
				if (children != null && children.length > 0) {
					if (children[0] instanceof TreeParent) {
						root = (TreeParent) children[0];
					}
				}
				SearchDialog searchDialog = new SearchDialog(ModelicaMLServices.getShell(), viewer, root, actionShowClientPerspective.isChecked(), actionShowProviderPerspective.isChecked());
				searchDialog.open();
			}
		};
		actionFind.setText("Find");
		actionFind.setToolTipText("Find");
		actionFind.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/find.png"));
		
		
		
		actionValidate = new Action("actionValidate") { //obviously a check box style
			public void run() {
				TreeObject[] children = invisibleRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					TreeObject treeObject = children[i];
					if (!treeObject.isReadOnly() && treeObject instanceof TreeParent) {
						
						final ValueBindingsValidator validator = new ValueBindingsValidator((TreeParent) treeObject);
						validator.setUmlModel(treeBuilder.getUmlModel());
						
//						Job job = new Job("Value Bindings Operations Validation") {
//							protected IStatus run(IProgressMonitor monitor) {
//								validator.validate();
//								return Status.OK_STATUS;
//							}
//						};
						
						// UIJob is needed because composites are used for xtext editors. 
						// TODO: refactor the editors glue code in order to don't use the any UI objects for the validation of action code. 
						UIJob UIjob = new UIJob("Value Bindings Operations Validation") {
							public IStatus runInUIThread(IProgressMonitor monitor) {
								validator.validate();
								viewer.refresh();
								return Status.OK_STATUS;
							}
						};
						
						UIjob.setUser(true);
						UIjob.schedule();
						
//						try {
//							validator.validate();
//							new ProgressMonitorDialog(new Shell()).run(true, true, validator);
//						} catch (InvocationTargetException e) {
//							e.printStackTrace();
//							MessageDialog.openError(new Shell(), "Value Bindings Operations Validation", 
//									"It was not possible to invoke the Value Bindings Operations Validation.");
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//							MessageDialog.openError(new Shell(), "Value Bindings Operations Validation Process Abort", 
//									"The Value Bindings Operations Validation was canceled.");
//						}
					}
				}
//				viewer.refresh();
			}
		};
		actionValidate.setText("Validate Client/Mediator/Privider Operations");
		actionValidate.setToolTipText("Validate Client/Mediator/Privider Operations");
		actionValidate.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/validate.gif"));

		
		actionCollapseAll = new Action("actionCollapseAll") { //obviously a check box style
			public void run() {
//				actionReload.run();
				TreeObject[] children = invisibleRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					viewer.collapseToLevel(children[i], AbstractTreeViewer.ALL_LEVELS);
//					viewer.collapseToLevel(children[i], 1); // collapse current element in the tree
				}
			}
		};
		actionCollapseAll.setText("Collapse all");
		actionCollapseAll.setToolTipText("Collapse all");
		actionCollapseAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));

		
		
		actionLinkWithEditor = new Action("actionLinkWithEditor", 2) { //obviously a check box style
			public void run() {
			}
		};
//		actionLinkWithEditor.setChecked(true);
		actionLinkWithEditor.setText("Link with other Model Views");
		actionLinkWithEditor.setToolTipText("Link with other Model Views");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));


		actionInstantiatedClassMode = new Action("actionInstantiatedClassMode", 2) { //obviously a check box style
			public void run() {
				
				ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(ModelicaMLServices.getShell());
//				progressDialog.getProgressMonitor().setTaskName("Collecting building data ...");
				
				try {
					progressDialog.run(false, true, new IRunnableWithProgress() {
						
						@Override
						public void run(IProgressMonitor monitor) throws InvocationTargetException,
								InterruptedException {

							if (actionInstantiatedClassMode.isChecked()) {
								
								monitor.subTask("Building tree from the instantiated class ...");
								
								TreeObject[] children = invisibleRoot.getChildren();
								for (int i = 0; i < children.length; i++) {
									invisibleRoot.removeChild(children[i]);
								}
								treeBuilder.buildTreeFromInstantiatedClass(invisibleRoot, org.openmodelica.modelicaml.common.instantiation.TreeUtls.classInstantiation, org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot);
								viewer.setInput(getViewSite());
								viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
							}
							else {
								
								monitor.subTask("Building tree from the ModelicaML model ...");
								
								TreeObject[] children = invisibleRoot.getChildren();
								for (int i = 0; i < children.length; i++) {
									invisibleRoot.removeChild(children[i]);
								}
								treeBuilder.buildTreeFromUmlModel(invisibleRoot);
								((ViewLabelProviderStyledCell)viewer.getLabelProvider()).setUmlModel(treeBuilder.getUmlModel());
								
								viewer.setInput(getViewSite());
								viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
							}
							
							monitor.done();
						}
					});
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					MessageDialog.openError(ModelicaMLServices.getShell(), "Collecting Binding Data Error", "Could not invoke bindings reload.");
				} catch (InterruptedException e) {
					e.printStackTrace();
					MessageDialog.openInformation(ModelicaMLServices.getShell(), "Collecting Binding Data Canceled", "Bindings reload was canceled");
				}
				
				
				
//				if (actionInstantiatedClassMode.isChecked()) {
//					TreeObject[] children = invisibleRoot.getChildren();
//					for (int i = 0; i < children.length; i++) {
//						invisibleRoot.removeChild(children[i]);
//					}
//					treeBuilder.buildTreeFromInstantiatedClass(invisibleRoot, org.openmodelica.modelicaml.common.instantiation.TreeUtls.classInstantiation, org.openmodelica.modelicaml.common.instantiation.TreeUtls.componentsTreeRoot);
//					viewer.setInput(getViewSite());
//					viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
//				}
//				else {
//					TreeObject[] children = invisibleRoot.getChildren();
//					for (int i = 0; i < children.length; i++) {
//						invisibleRoot.removeChild(children[i]);
//					}
//					treeBuilder.buildTreeFromUmlModel(invisibleRoot);
//					((ViewLabelProviderStyledCell)viewer.getLabelProvider()).setUmlModel(treeBuilder.getUmlModel());
//					
//					viewer.setInput(getViewSite());
//					viewer.expandToLevel(DEFAULT_EXPAND_LEVEL);
//				}
			}
		};
//		actionInstantiatedClassMode.setChecked(true);
		actionInstantiatedClassMode.setText("Instantiated Class Mode");
		actionInstantiatedClassMode.setToolTipText("Instantiated Class Mode");
		actionInstantiatedClassMode.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/instantiatedClassTree.png"));
		
		
		actionAssociateValueClient = new Action("actionAssociateValueClient") {
			public void run() {
//				showMessage("actionAssociateValueClient is not implemented yet.");
				
				ISelection selection = viewer.getSelection();
				Object selectedTreeItem = ((IStructuredSelection)selection).getFirstElement();
				
//				TreeParent valueMediatorTreeItem = getValueMediator((TreeObject) selectedTreeItem);
				TreeObject valueMediatorTreeItem = TreeUtls.getNearestMediator( (TreeObject) selectedTreeItem);
				
				if (valueMediatorTreeItem instanceof TreeParent) {
					List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
					listOfAllowedMetaClassesNames.add("Property");
					String title = "Value Client Selection";
					String message = "Click on a model element to be associated as Value Client to the Value Mediator."; // '" + ((TreeParent)selectedTreeParent).getName() + "'.";
//					String mode = "addValueClient";
					int mode = Constants.MODE_ADD_CLIENT;
					
					ElementSelectionDialog dialog = new ElementSelectionDialog(
							//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), // don't use this because it will block the window underneath.
							new Shell(),
							SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectOnly.png"), 
							title, 
							message, 
							listOfAllowedMetaClassesNames,
							(TreeParent)valueMediatorTreeItem,
							viewer,
							mode, 
							null,
							treeBuilder);
					
					dialog.open();
				}
			}
		};
		actionAssociateValueClient.setText("Add Value Client");
		actionAssociateValueClient.setToolTipText("Add Value Client");
//		actionAssociateValueClient.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		actionAssociateValueClient.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/addValueClient.png"));
		
		
		
		
		actionAssociateValueProvider = new Action("actionAssociateValueProvider") {
			public void run() {
//				showMessage("actionAssociateValueProvider is not implemented yet.");
				
				ISelection selection = viewer.getSelection();
				Object selectedTreeItem = ((IStructuredSelection)selection).getFirstElement();
				
//				TreeParent valueMediatorTreeItem = getValueMediator((TreeObject) selectedTreeItem);
				TreeObject valueMediatorTreeItem = TreeUtls.getNearestMediator( (TreeObject) selectedTreeItem);

				if (valueMediatorTreeItem instanceof TreeParent) {
					List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
					listOfAllowedMetaClassesNames.add("Property");
					String title = "Value Provider Selection";

					String message = "Click on a model element to be associated as Value Provider to the Value Mediator."; // '" + ((TreeParent)selectedTreeParent).getName() + "'."
//					String mode = "addValueProvider";
					int mode = Constants.MODE_ADD_PROVIDER;
					ElementSelectionDialog dialog = new ElementSelectionDialog(
							//PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), // don't use this because it will block the window underneath.
							new Shell(),
							SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectOnly.png"), 
							title, 
							message, 
							listOfAllowedMetaClassesNames,
							(TreeParent)valueMediatorTreeItem,
							viewer,
							mode,
							null,
							treeBuilder);
					
					dialog.open();
				}
			}
		};
		actionAssociateValueProvider.setText("Add Value Provider");
		actionAssociateValueProvider.setToolTipText("Add Value Provider");
		actionAssociateValueProvider.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/addValueProviders.png"));

		
		
		actionAddValueMediator= new Action("actionAddValueMediator") {
			public void run() {
				CreateValueMediatorHandler h = new CreateValueMediatorHandler();
				try {
					EList<Element> createdElements = h.execute(null); // execute handler

					// add tree item
					if (createdElements instanceof EList) {
						ISelection selection = viewer.getSelection();
						Object parentItem = ((IStructuredSelection)selection).getFirstElement();
						
						for (Element createdElement : createdElements) {
							if (parentItem instanceof TreeParent) {
								TreeParent item  = new TreeParent( ((NamedElement)createdElement ).getName() );
								item.setUmlElement((NamedElement)createdElement);
								((TreeParent)parentItem).addChild(item);
								viewer.add(parentItem, item);
								
								// add clients node
								TreeParent clientsNode  = new TreeParent( Constants.valueClientsNodeName );
								item.addChild(clientsNode);
								viewer.add(item, clientsNode);
								
								// add providers node
								TreeParent providersNode  = new TreeParent( Constants.valueProvidersNodeName );
								item.addChild(providersNode);
								viewer.add(item, providersNode);
								
								viewer.refresh();
							}
						}
					}
					else {
						showMessage("Could not finish the action AddValueMediator ...");
					}
					
				} catch (ExecutionException e) {
					e.printStackTrace();
					showMessage("Could not finish the action AddValueMediator ...");
				}
//				showMessage("actionValueMediator is not implemented yet.");
			}
		};
		actionAddValueMediator.setText("Add Value Mediator");
		actionAddValueMediator.setToolTipText("Add Value Mediator");
		actionAddValueMediator.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/valueMediator.png"));

		
		
		actionAddValueMediatorContainer= new Action("actionAddValueMediatorContainer") {
			public void run() {
				CreateValueMediatorsContainerHandler h = new CreateValueMediatorsContainerHandler();
				try {
					Object createdElement = h.execute(null);
					if (createdElement instanceof NamedElement) {
						ISelection selection = viewer.getSelection();
						Object parentItem = ((IStructuredSelection)selection).getFirstElement();

						if (parentItem instanceof TreeParent) {
							TreeParent item  = new TreeParent( ((NamedElement)createdElement ).getName() );
							item.setUmlElement((NamedElement)createdElement);
							((TreeParent)parentItem).addChild(item);
							viewer.add(parentItem, item);
							viewer.refresh();
						}
					}
					else {
						showMessage("Could not finish the action AddValueMediatorContainer ...");
					}
					
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage("Could not finish the action AddValueMediatorContainer ...");
				}
//				showMessage("actionValueMediatorContainer is not implemented yet.");
			}
		};
		actionAddValueMediatorContainer.setText("Add Value Mediator Container");
		actionAddValueMediatorContainer.setToolTipText("Add Value Mediator Container");
		actionAddValueMediatorContainer.setImageDescriptor(ImageDescriptor.createFromFile(ValueBindingsView.class, "/icons/valueMediatorsContainer.png"));

		

		
		actionDeleteReference = new Action("actionDeleteReference") {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				String name = "";
				
				if (obj instanceof TreeObject) {
				
					name = ((TreeObject)obj).getName();
					String title = "Delete Reference";
					String message = "Are you sure you want to delete " + "'" + name + "'?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message);

					if (go) {
						//delete dependency from mediator in UML model
						boolean deleted = TreeUtls.deleteDependencyFromMediator((TreeObject)obj);
						if (deleted) {
							// remove deleted item from its parent in tree model
							TreeParent parentItem = ((TreeObject)obj).getParent();
							if (parentItem != null ) {
								parentItem.removeChild((TreeObject)obj);
							}
							
							// remove deleted item from its parent in tree viewer
							viewer.remove((TreeObject)obj);
							viewer.refresh();
						}
					}
				}
			}
		};
		actionDeleteReference.setText("Delete this reference");
		actionDeleteReference.setToolTipText("Delete this reference");
		actionDeleteReference.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		

		actionDeleteModelElement = new Action("actionDeleteModelElement") {
			public void run() {
//				showMessage("actionDeleteModelElement is not implemented yet.");

				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				String name = "";
				if (obj instanceof TreeObject) {
					name = ((TreeObject)obj).getName();
					String title = "Delete Element";
					String message = "Are you sure you want to delete " + "'" + name + "'?" +
							"\nThis action cannot be undone.";
					Boolean go = MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message);

					if (go) {
						DeleteCommandHandler h = new DeleteCommandHandler();
						try {
							// delete from the Papyrus UML model
							// Note: this should lead to the deletion in the tree because of the listener that reacts to the changes of Papyrus model.
							h.execute(null);
							
							// remove from tree
							/*
							 * Note: This is necessary if the actual bindings model is imported in another model.
							 * Then the Papyrus listener obviously is not notified and does not delete the tree item. 
							 * So we need to delete the tree item manually.
							 */
							if (obj != null) {
								
								if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
									// get the value bindings view
									IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_VALUE_BINDINGS);
									ValueBindingsView myView = null;
									
									if (view instanceof ValueBindingsView) {
									    myView = (ValueBindingsView)view;			
									}
									
									if (myView != null) {
										TreeUtls.removeTreeItem(((TreeObject) obj).getUmlElement(), myView.getTreeRoot(), myView.getViewer());
										myView.getViewer().refresh(); // refresh the entire viewer
//										System.err.println("REMOVED: " + ((NamedElement)notification.getOldValue()).getName());
									}
								}
							}

						} catch (ExecutionException e) {
							e.printStackTrace();
							MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Could not delete the element '" + name + "'.");
//							showMessage("Could not finish the action DeleteModelElement ...");
						}
					}
				}
			}
		};
		actionDeleteModelElement.setText("Delete");
		actionDeleteModelElement.setToolTipText("Delete");
		actionDeleteModelElement.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_DELETE));

		
		doubleClickAction = new Action() {
			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
//				showMessage("Double-click action for " + obj.toString() + " is not implemented yet.");
			}
		};
	}

	
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(), "Value Bindings View", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	//####################### FILTERS 
	
	// Filter for "Show Value Mediator Perspective" 
	class MediatorPerspectiveFilter extends ViewerFilter {
			@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 
				if ( item.isReadOnly() ) { 
					return false;
				}
			}
			return true;
		}
	}
	MediatorPerspectiveFilter mediatorPerspectiveFilter = new MediatorPerspectiveFilter();
	
	
	// Filter for "Show Value Client Perspective" 
	class ClientPerspectiveFilter extends ViewerFilter {
			@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 

				// invisible tree root -> show
				if (item == invisibleRoot) {
					return true;
				}
				// adjust here
				// providers node at the top level -> hide
				else if (item.isValueProvidersNode() && item.getParent() != null && item.getParent() == invisibleRoot) {
					return false;
				}
				// read only -> show (read-only nodes are the perspective specific additional tree nodes that are hidden by default)
				else if ( item.isReadOnly()) { 
					return true;
				}
				// providers node underneath a mediator for a client -> show
				else if (item.isValueProvidersNode()) { 
					return true;
				}
				// adjust here
				// value client underneath a clients node at the top level -> show
				else if (item.isValueClient() && item.getParent() != null && item.getParent().isReadOnly()) {
					return true;
				}
				else {
					return false;
				}
			}
			return false;
		}
	}
	ClientPerspectiveFilter clientPerspectiveFilter = new ClientPerspectiveFilter();
	
	// Filter for "Show Value Client Perspective" 
	class RequiredClientFilter extends ViewerFilter {
			@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof TreeParent) {
				TreeParent item = ((TreeParent)element); 
				return hasRequiredClients(item);
			}
			return false;
		}
	}
	RequiredClientFilter requiredClientFilter = new RequiredClientFilter();
	
	private boolean hasRequiredClients(TreeObject item){
		if (item.isValueClient_required()) {
			return true;
		}
		else if (item instanceof TreeParent) {
			HashSet<TreeObject> list= findNextRequiredClient((TreeParent) item);
			if (list != null && list.size() > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private HashSet<TreeObject> findNextRequiredClient(TreeParent item){
		HashSet<TreeObject> list = new HashSet<TreeObject>();
		
			TreeObject[] children = item.getChildren();
			for (int i = 0; i < children.length; i++) {
				TreeObject child = children[i];
				if (child.isValueClient_required()) {
					list.add(child);
					return list;
				}
				else if (child instanceof TreeParent) {
					list.addAll(findNextRequiredClient( (TreeParent)child ));	
				}
			}
		 return list;
	}
	
	// Filter for "Show Value Provider Perspective" 
	class ProviderPerspectiveFilter extends ViewerFilter {
			@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof TreeParent) {
					TreeParent item = ((TreeParent)element); 
					
					// invisible tree root -> show
					if (item == invisibleRoot) {
						return true;
					}
					// adjust here
					// clients node at the top level -> hide
					else if (item.isValueClientsNode() && item.getParent() != null && item.getParent() == invisibleRoot) {
						return false;
					}
					// read only -> show (read-only nodes are the perspective specific additional tree nodes that are hidden by default)
					else if ( item.isReadOnly()) { 
						return true;
					}
					// clients node underneath a mediator for a provider -> show
					else if (item.isValueClientsNode()) { 
						return true;
					}
					// adjust here
					// value provider underneath a providers node at the top level -> show
					else if (item.isValueProvider() && item.getParent() != null && item.getParent().isReadOnly()) {
						return true;
					}
					else {
						return false;
					}
				}
				return false;
		}
	}
	
	ProviderPerspectiveFilter providerPerspectiveFilter = new ProviderPerspectiveFilter();
	
	
	//##################### Selection handling
	 private ISelectionListener selectionListener = new ISelectionListener() {
		 public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			 if (actionLinkWithEditor != null && actionLinkWithEditor.isChecked() && sourcepart != null && sourcepart != ValueBindingsView.this && selection instanceof IStructuredSelection) {
	        	EObject selectedElement = null;
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
					selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				}
				if ( selectedElement != null ) {
					HashSet<Object> objects = TreeUtls.findTreeItems(selectedElement, invisibleRoot, new HashSet<Object>());
					List<Object> newSelectionItems = new ArrayList<Object>();
					newSelectionItems.addAll(objects);
					viewer.setSelection(new StructuredSelection(newSelectionItems), true);
				}
	        }
	    }
	};
	
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
	
	public TreeBuilder getTreeBuilder(){
		return this.treeBuilder;
	}
	
	public TreeParent getTreeRoot(){
		return this.invisibleRoot;
	}

	public TreeViewer getViewer(){
		return this.viewer;
	}

	@Override
	public String getContributorId() {
		 return getSite().getId();
	}

	@SuppressWarnings("rawtypes")
	@Override
    public Object getAdapter(java.lang.Class adapter) {
        if (adapter == IPropertySheetPage.class) {
        	return new TabbedPropertySheetPage(this);
        }
        return super.getAdapter(adapter);
    }

	@Override
	public void gotoMarker(IMarker marker) {
		String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
		if(uriAttribute != null) {
			
			URI uri = URI.createURI(uriAttribute);
			
			IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELEXPLORER);
			EObject eObject = null;
			if(viewPart instanceof ModelExplorerPageBookView) {
				org.eclipse.ui.part.IPage page =  ((ModelExplorerPageBookView)viewPart).getCurrentPage();
				
				if (page instanceof ModelExplorerPage) {
					ModelExplorerView modelExplorerView = (ModelExplorerView) ( (ModelExplorerPage) ((ModelExplorerPageBookView)viewPart ).getCurrentPage() ).getViewer();
					EditingDomain domain = modelExplorerView.getEditingDomain();
					eObject = domain.getResourceSet().getEObject(uri, false);
					if(eObject != null) {
						CommonViewer treeViewer = ((ModelExplorerView)modelExplorerView).getCommonViewer();
						// The common viewer is in fact a tree viewer bug enhancement: use function in ModelExplorerView instead of findElementForEObject
						List<Object> list = new ArrayList<Object>();
						list.add(eObject);
						ModelExplorerView.reveal(list, treeViewer);
					}
				}
			}
			
			// bindings view 
			IViewPart viewPartBindigns = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_VALUE_BINDINGS);
			if(viewPartBindigns instanceof ValueBindingsView) {
				if (eObject instanceof Element) {
					TreeUtls.locate((Element)eObject, ((ValueBindingsView)viewPartBindigns).getTreeRoot(), ((ValueBindingsView)viewPartBindigns).getViewer());
				}
			}
		}
	}
}