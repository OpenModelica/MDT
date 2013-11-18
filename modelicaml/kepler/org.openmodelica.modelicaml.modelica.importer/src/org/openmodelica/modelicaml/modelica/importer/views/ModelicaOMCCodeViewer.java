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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.modelica.importer.views;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.papyrus.infra.core.listenerservice.IPapyrusListener;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.utils.BusinessModelResolver;
import org.eclipse.papyrus.uml.tools.model.ExtendedUmlModel;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
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
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.modelexplorer.ModelExplorerPage;
import org.openmodelica.modelicaml.modelica.importer.Activator;
import org.openmodelica.modelicaml.modelica.importer.creators.ModelicaMLElementsCreator;
import org.openmodelica.modelicaml.modelica.importer.dialogs.SynchronizeOptionsDialog;
import org.openmodelica.modelicaml.modelica.importer.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.modelica.importer.model.ClassItem;
import org.openmodelica.modelicaml.modelica.importer.model.ComponentItem;
import org.openmodelica.modelicaml.modelica.importer.model.TreeBuilder;
import org.openmodelica.modelicaml.modelica.importer.model.TreeObject;
import org.openmodelica.modelicaml.modelica.importer.model.TreeParent;
import org.openmodelica.modelicaml.modelica.importer.model.Utilities;

public class ModelicaOMCCodeViewer extends ViewPart implements IGotoMarker {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.openmodelica.modelicaml.modelica.importer.views.ModelicaOMCCodeViewer";

	// Viewer 
	private TreeViewer viewer;
	private ViewLabelProviderStyledCell labelProvider = new ViewLabelProviderStyledCell();
	private DrillDownAdapter drillDownAdapter;
	
	// ModelicaML Model 
	private UmlModel umlModel;
	private EObject ModelicaMLRoot;

	// Model editing domain  
	private ServicesRegistry serviceRegistry;
	private TransactionalEditingDomain editingDomain;
	
	// Tree handling 
	private TreeBuilder treeBuilder = new TreeBuilder();
	private ModelicaMLElementsCreator ec;
	private TreeParent treeRoot = null;

	// Actions
	private Action actionReload;
	private Action actionLoadSubTree;

	private Action actionSynchronize;
	private Action actionSynchronizeSubTree;

	private Action doubleClickAction;
	private Action actionRefreshAndValidate;

	private Action actionClear;
	private Action actionSort;
	
	private Action actionCollapseAll;
	private Action actionExpandCollapse;
	
	private Action actionLocateInPapyrusModelExplorer;
	private Action actionLinkWithEditor;
	
	private Action actionGenerateOMCMarkers;
	private Action actionValidateProxies;
	private Action actionDecorateTreeItems;

	private Action actionReloadView;

	private Action actionSyncOnlyPublicElements; // indicates that only public elements should be displayed and synchronized
	
	// Default values for sync. options
	private boolean createProxiesAfterLoadingModelicaClasses = false;
	private boolean applyProxyStereotype = true;
	private boolean update = true;
	private boolean deleteNotUsedProxies = false;
	private boolean fullImport = false;
	
	// Listeners
	private ISelectionListener selectionListener = new ISelectionListener() {
		 public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			 if (actionLinkWithEditor != null && actionLinkWithEditor.isChecked() && sourcepart != ModelicaOMCCodeViewer.this && selection instanceof IStructuredSelection) {
	        	EObject selectedElement = null;
	        	if (getCurrentSelections() != null && getCurrentSelections().size() > 0 ) {
					selectedElement = (EObject) adaptSelectedElement(getCurrentSelections().get(0));
				}
				if ( selectedElement != null ) {
					HashSet<Object> objects = findTreeItem(selectedElement);
					List<Object> newSelectionItems = new ArrayList<Object>();
					newSelectionItems.addAll(objects);
					viewer.setSelection(new StructuredSelection(newSelectionItems), true);
				}
	        }
	    }
	};


	// Jobs
	private Job collectProxiesJob = new Job("Empty") {
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	private Job reloadSubTreeJob = new Job("Empty") {
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	



	
	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {
		private TreeParent invisibleRoot;

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
			TreeParent root = new TreeParent(Constants.folderName_code_sync);
			treeRoot = root;
			
			invisibleRoot = new TreeParent("");
			invisibleRoot.addChild(root);
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(selectionListener);
		}
	}

	
	
	// alphabetic sourter
	class NameSorter extends ViewerSorter {
	}

	
	
	/* TODO
	 *  This class is not initialized by Papyrus extension...
	 *  remove proxies from tree builder when they are deleted in Papyrus 
	 */
	public class PapyrusListener implements IPapyrusListener {

		@Override
		public void notifyChanged(Notification notification) {
			
			if ( notification.getEventType() == Notification.REMOVE && notification.getNewValue() == null ) {
//				System.err.println("Event type: " + notification.getEventType());
////			System.err.println("Notifier: " + ((NamedElement)notification.getNotifier()).getName() );
//				System.err.println("Notifier: " + notification.getNotifier() );
//				System.err.println("OldValue: " + notification.getOldValue());
//				System.err.println("NewValue: " + notification.getNewValue());
				
				Object oldObj = notification.getOldValue();

				// if it is a UML element that was deleted
				if (oldObj instanceof Element) {
					Element oldElt = (Element) oldObj;
					
					if (oldElt.getAppliedStereotype(Constants.stereotypeQName_ModelicaModelProxy) != null) {
						// remove from tree builder
						System.err.println("Remove: " + oldElt);
					}
					
					if (oldElt instanceof Property && oldElt.getOwner().getAppliedStereotype(Constants.stereotypeQName_ModelicaModelProxy) != null) {
						// remove from tree builder
						System.err.println("Remove: " + oldElt);
					}
				}
			}
		}
	}
	
	
	/**
	 * The constructor.
	 */
	public ModelicaOMCCodeViewer() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
//		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer = new TreeViewer(parent, SWT.VIRTUAL | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(labelProvider);
		
//		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		Boolean loadMSLBeforeSynchronizing = Platform.getPreferencesService().getBoolean(Constants.MODELICAML_PERSPECTIVE_PLUGIN, "syncOnlyPublicElements", true, null);
		
		if (loadMSLBeforeSynchronizing) {
			// default filters
			ViewerFilter[] filters = {showOnlyPublicComponentsFilter};
			viewer.setFilters(filters);
		}
		
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.modelica.importer.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		
		// add a selection provider.
		getSite().setSelectionProvider(viewer);

//		// get Papyrus Model Explorer
//		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);
//
//		ModelExplorerPageBookView modelExplorerPageBookView = null;
//		if (view instanceof ModelExplorerPageBookView) {
//			modelExplorerPageBookView = (ModelExplorerPageBookView)view;
//		}
//
//		CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
////		List<Object> items = new ArrayList<Object>();
////		items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
////		// set new selection
////		modelExplorerView.setSelection(new StructuredSelection(items), true);
//		
//		// add drop support
//		int operations = DND.DROP_MOVE;
//		Transfer[] transferTypes = new Transfer[]{ LocalSelectionTransfer.getTransfer()};
//		modelExplorerView.addDropSupport(operations, transferTypes, new DropListener(viewer));
		
		
//		// add drag support to this view
//		int dragOperations = DND.DROP_MOVE;
//		Transfer[] dragTransferTypes = new Transfer[]{ LocalSelectionTransfer.getTransfer()};
//		viewer.addDragSupport(dragOperations, dragTransferTypes, new DragListener(viewer));
		
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ModelicaOMCCodeViewer.this.fillContextMenu(manager);
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
		manager.add(actionGenerateOMCMarkers);
		manager.add(actionValidateProxies);

		manager.add(new Separator());
		manager.add(actionDecorateTreeItems);
		
		manager.add(new Separator());
		manager.add(actionSyncOnlyPublicElements);
	}

	private void fillContextMenu(IMenuManager manager) {

		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection)selection).getFirstElement();

		String title = "sub-tree";
		if (obj instanceof ClassItem) {
			String restriction = ((ClassItem)obj).getClassRestriction();
//			String firstLetter = restriction.substring(0, 1);
//			title = firstLetter.toUpperCase() + restriction.substring(1,restriction.length());
			title = restriction;
		}
		
		else if (obj instanceof ComponentItem) {
			title = "Component";
		}
		
		// action for (re)loading sub-trees
		if (obj instanceof ClassItem ) {
			manager.add(actionLoadSubTree);
			actionLoadSubTree.setText("(Re)Load this " + title);
			manager.add(new Separator());
			
			// Disable this action if the reload sub tree job is running 
			if (reloadSubTreeJob.getState() == Job.RUNNING) {
				actionLoadSubTree.setEnabled(false);
				actionSynchronizeSubTree.setEnabled(false);
			}
			else {
				actionLoadSubTree.setEnabled(true);
				actionSynchronizeSubTree.setEnabled(true);
			}
		}
		
		// If it is a class or component -> enable the "synchronize sub-tree" action
		if (obj instanceof ClassItem || obj instanceof ComponentItem) {
			manager.add(actionSynchronizeSubTree);
			actionSynchronizeSubTree.setText("Synchronize this " + title);
			manager.add(new Separator());
		}
		
		// Add locate in Papyrus action if a corresponding proxy exists
		if (obj instanceof TreeObject ) {
			TreeObject treeObject = (TreeObject)obj;
			if (treeObject.getModelicaMLProxy() != null) {
				manager.add(actionLocateInPapyrusModelExplorer);
				manager.add(new Separator());
			}
		}
		
		manager.add(new Separator());
		
		// If it is the root (folder) or a class -> add expand/collapse action
		if (obj instanceof ClassItem || (obj instanceof TreeParent && ((TreeParent)obj).getName().equals(Constants.folderName_code_sync) )) {
			if (!viewer.getExpandedState(obj)) {
				actionExpandCollapse.setText("Expand");
				actionExpandCollapse.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/expand.gif"));

				// Disable expand action when the reload sub tree job is running
				if (reloadSubTreeJob.getState() == Job.RUNNING) {
					actionExpandCollapse.setEnabled(false);
				}
				else {
					actionExpandCollapse.setEnabled(true);
				}
			}
			else {
				actionExpandCollapse.setText("Collapse");
				actionExpandCollapse.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/collapse.gif"));

				actionExpandCollapse.setEnabled(true);
			}
			manager.add(actionExpandCollapse);
			manager.add(new Separator());
		}
		
//		manager.add(new Separator());
		
//		drillDownAdapter.addNavigationActions(manager);
		
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {

		manager.add(actionReloadView);
		manager.add(actionClear);
		manager.add(actionSort);
		manager.add(new Separator());
		
		manager.add(actionCollapseAll);

		manager.add(actionLinkWithEditor);
		manager.add(new Separator());

		manager.add(actionReload);
		manager.add(actionSynchronize);
		manager.add(actionRefreshAndValidate);
		manager.add(new Separator());
		
//		manager.add(actionReloadView);
//		manager.add(new Separator());
		
//		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
	}

	
	private void showErrorLogDialog(){
		// show error log 
		String errorLog = treeBuilder.getErrorLog();
		if(!errorLog.trim().isEmpty()){
			DialogMessage dialog = new DialogMessage(new Shell(), "Modelica Models Loading Error Log", "The following errors were detected: ", errorLog, true);
			dialog.open();
		}
	}
	
	private void makeActions() {
		
		// Load job listener
		final JobChangeAdapter loadJobChangeAdapter = new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
	            if (event.getResult().isOK()) {
	            	Display.getDefault().asyncExec(new Runnable() {
	        			public void run() {
	        				
	        				showErrorLogDialog();
	        				
	        				Object[] expandedElements = viewer.getExpandedElements();
	        				TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
	        				
	        				if (treeRoot.hasChildren()) {
	        					actionSynchronize.setEnabled(true);
	        					actionRefreshAndValidate.setEnabled(true);
	        				}
	        				else {
	        					actionSynchronize.setEnabled(false);
	        					actionRefreshAndValidate.setEnabled(false);
	        				}
	        				
	        				viewer.setInput(getViewSite());
	        				
	        				viewer.setExpandedElements(expandedElements);
	        				viewer.setExpandedTreePaths(expandedTreePaths);
	        				
	        				actionRefreshAndValidate.run();

	        				if (createProxiesAfterLoadingModelicaClasses) {
								
	        					/*
								 * TODO: if the element that is being updated is selected in GUI (i.e. in Papyrus modeling tool)
								 * then there will be a "SWT invalid thread exception because this job will modify it.
								 * WORKAROUND: before creating elements set Papyrus Model Explorer Selection to 
								 * an element that will not be modified, e.g. the ModelicaML root. 
								 */
//								PapyrusServices.locateWithReselection(treeBuilder.getModelicaMLRoot());
								EditorServices.locateInModelExplorer(treeBuilder.getModelicaMLRoot(), true);

								// run the synch action after loading
	        					actionSynchronize.run();
							}
	        				else {
	        					// report what was created/updated
		        				String message = "";
		        				int loadedClassesNumber = treeBuilder.getLoadedClasses().size();
		        				int loadedComponentsNumber = treeBuilder.getLoadedComponents().size();
		        				int loadedExtendsRelationsNumber = treeBuilder.getLoadedExtendsRelations().size();
		        				
		        				message = message + "Number of loaded classes: " + loadedClassesNumber + "\n";
		        				message = message + "Number of loaded components: " + loadedComponentsNumber + "\n";
		        				message = message + "Number of loaded extends relations: " + loadedExtendsRelationsNumber + "\n";
		        				
		        				boolean isError = loadedClassesNumber == 0;
		        				DialogMessage dialog = new DialogMessage(new Shell(), "Modelica Models Synchronization Report", null, message, isError);
		        				dialog.open();
	        				}
	        			}
	        		});
	            }
	            else {
	            	System.err.println("Could not complete the loading of Modelica Models through OMC.");
	            	}
				}
	         };
	        
	         
		actionReload = new Action() {
			public void run() {
				
				if (!EditorServices.isVisiblePapyrusModelExplorerView()) {
					MessageDialog.openError(getSite().getShell(), "Modelica Model Proxies Synchronization Error", 
							"When synchronizing proxies the Model Explorer View must be visible " +
							"so that the viewer selection can be reset in order to avoid cuncurrent access to " +
							"proxies that are displayed in Properties View and are modified by " +
							"the synchronization job at the same time." +
							"\n\n Please make the Papyrus Model Explorer View visible and do not " +
							"select existing Modelica model proxies while the synchronization is running.");
					
					return;
				}
				
				// Set all project- and model-related data
				configureTreeBuilder();
				
				// As a job
				if (umlModel == null) {
					MessageDialog.openError(getSite().getShell(), 
							"ModelicaML Model Editing Domain Access Error", 
							"Cannot acceess the ModelicaML model or its editing domain. " +
							"Please make sure that the ModelicaML model is open in the active editor.");
				}
				else {
//					if (serviceRegistry != null && editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
					if (editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
						
						// Open a dialog to as for this option
						String title = "Modelica Proxies Synchronization Option";
						String msg = "This action will now load the Modelica models from the " +
								"'" + Constants.folderName_code_sync + "' folder. "
								+ "\n\nShould it synchronize the ModelicaML proxies after loading? " +
								"\nNote, the synchronization can be launched manually at any time.";
						
						createProxiesAfterLoadingModelicaClasses = MessageDialog.openQuestion(getSite().getShell(), title, msg);
						
						// ask for synchronization options when elements should be created
						if (createProxiesAfterLoadingModelicaClasses) {
							SynchronizeOptionsDialog dialog = new SynchronizeOptionsDialog(getSite().getShell());
							dialog.open();
							
							int result = dialog.getReturnCode();
							if (result == IDialogConstants.OK_ID) {
								// set options
								applyProxyStereotype = dialog.isApplyProxyStereotype();
								update = dialog.isUpdate();
								deleteNotUsedProxies = dialog.isDeleteNotUsedProxies();

								fullImport = dialog.isFullImport();
								treeBuilder.setFullImport(fullImport);
							}
							else {
								// the dialog was canceled -> no elements should be created.
								createProxiesAfterLoadingModelicaClasses = false;
							}
						}
						
						
						Job job = new Job("Loading Modelica Models from '"+Constants.folderName_code_sync+"' folder..."){

							@Override
							protected IStatus run(IProgressMonitor monitor) {
								
								monitor.beginTask("Loading Modelica Models from '"+Constants.folderName_code_sync+"' folder...", 3);
								
								treeBuilder.setMonitor(monitor);
								
								monitor.setTaskName("Cleaing up ...");
								TreeObject[] children = treeRoot.getChildren();
								for (int i = 0; i < children.length; i++) {
									treeRoot.removeChild(children[i]);
								}
								
								// set the uml model to encapsulate the job required data
								treeBuilder.setModelicaMLModel(umlModel);
								treeBuilder.setModelicaMLRoot(ModelicaMLRoot);
//										treeBuilder.setModelicamlProfile();
								monitor.worked(1);
								
								monitor.setTaskName("Deleting old markers ...");
								// delete OMC markers
								String projectName = umlModel.getResource().getURI().segment(1);
								IWorkspace workspace = ResourcesPlugin.getWorkspace();
								IWorkspaceRoot root = workspace.getRoot();
								IProject iProject = root.getProject(projectName);

								Utilities.deleteOMCLoadingMarkers(iProject);
								monitor.worked(2);

								// build tree
								monitor.setTaskName("Analyzing models ...");
								treeBuilder.buildTree(treeRoot, null);
								
								// check if the first level classes are all packages 
								checkFirstLevelClasses(false);
								
								monitor.worked(3);
								
								monitor.done();
								done(Status.OK_STATUS);
								return Status.OK_STATUS;
							}
						};
						job.addJobChangeListener(loadJobChangeAdapter);
						job.setUser(true);
						job.schedule();
					}
					else {
						MessageDialog.openError(getSite().getShell(), "Loading error", 
								"Could not access the Papyrus editing domain and the uml model.");
					}
				}
			}
		};
		actionReload.setText("(Re)Load All");
		actionReload.setToolTipText("(Re)Load All");
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/import_resource_wiz.gif"));
		
		
		actionCollapseAll = new Action("actionCollapseAll") { //obviously a check box style
			public void run() {
				TreeObject[] children = treeRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					viewer.collapseToLevel(children[i], AbstractTreeViewer.ALL_LEVELS);
				}			
			}
		};
		actionCollapseAll.setText("Collapse All");
		actionCollapseAll.setToolTipText("Collapse All");
		actionCollapseAll.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));

		
		actionRefreshAndValidate = new Action() {
			public void run() {
				
				treeBuilder.collectModelicaModelProxies();
				treeBuilder.updateTreeItemProxies(treeRoot);
				
				// set up marker data
				UmlModel umlModel = (ExtendedUmlModel) UmlUtils.getUmlModel();
				if (umlModel != null) {
					String projectName = umlModel.getResource().getURI().segment(1);
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					IWorkspaceRoot root = workspace.getRoot();
					IProject iProject = root.getProject(projectName);
					
					/*
					 * Note: There is an option to enable/disable this validation feature.
					 * This may be needed for large models where the validation 
					 * and the number of markers are too large ...
					 */
					treeBuilder.validateProxies(iProject);
				}
				else {
					MessageDialog.openError(getSite().getShell(), 
						"ModelicaML Model Access Error", 
						"Cannot acceess the ModelicaML model. " +
						"Please make sure that the ModelicaML model is open in editor.");
				}
				
				viewer.refresh();
			}
		};
		actionRefreshAndValidate.setText("Validate All");
		actionRefreshAndValidate.setToolTipText("Validate All");
		actionRefreshAndValidate.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/validate_co.gif"));
		actionRefreshAndValidate.setEnabled(false);
		
		actionClear = new Action() {
			public void run() {
				
				// remove tree items
				TreeObject[] children = treeRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					TreeObject treeObject = children[i];
					treeRoot.removeChild(treeObject);
				}
				
				// clear the tree builder
				treeBuilder.unSetModelicaMLModel();
				treeBuilder.unModelicaMLRoot();
				treeBuilder.clearAll();

				// clear the compiler
				treeBuilder.getOmcc().clear();

				// Set project- and model-related data in case the active Papyrus model changed
				setTreeRootProjectName();
				
				// delete OMC markers
				String projectName = umlModel.getResource().getURI().segment(1);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				IProject iProject = root.getProject(projectName);

				Utilities.deleteOMCLoadingMarkers(iProject);
				
				// collapse the tree root and refresh
				viewer.setExpandedState(treeRoot, false);
				viewer.refresh();
				
				actionRefreshAndValidate.setEnabled(false);
				actionSynchronize.setEnabled(false);
			}
		};
		actionClear.setText("Clear");
		actionClear.setToolTipText("Clear");
		actionClear.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_CLEAR));
		
		
		
		actionSort = new Action("Sort", 2) {
			public void run() {
				if (actionSort.isChecked()){
					viewer.setSorter(new NameSorter());
				}
				else {
					viewer.setSorter(null);
				}
			}
		};
		actionSort.setText("Sort");
		actionSort.setToolTipText("Sort");
		actionSort.setImageDescriptor(ImageDescriptor.createFromFile(Constants.class, "/icons/papyrus/sort.gif"));
		// set default
		actionSort.setChecked(viewer.getSorter() != null);

		
		
		actionReloadView = new Action() {
			public void run() {
				// clear all
				actionClear.run();
				// expand the first level
				doubleClickAction.run();
			}
		};
		actionReloadView.setText("Clear and Expand The First Level");
		actionReloadView.setToolTipText("Clear and Expand The First Level");
		actionReloadView.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/refresh_nav.gif"));

		
		actionExpandCollapse = new Action() {
			public void run() {
				doubleClickAction.run();
			}
		};
		actionExpandCollapse.setText("Expand / Collapse");
		actionExpandCollapse.setToolTipText("Expand / Collapse");
		actionExpandCollapse.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
		
		
		
		// Synch job listener
		final JobChangeAdapter synchJobChangeAdapter = new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
	            if (event.getResult().isOK()) {
	            	Display.getDefault().asyncExec(new Runnable() {
	        			public void run() {
	        				// refresh in order to reflect changes in the view
	        				actionRefreshAndValidate.run();
	        				
	        				// reset the option
	        				createProxiesAfterLoadingModelicaClasses = false;
	        				applyProxyStereotype = true;
	        				update = true;
	        				deleteNotUsedProxies = false;
	        				
	        				// create report string
	        				String message = "";
	        				
	        				// LOADED
	        				int loadedClassesNumber = treeBuilder.getLoadedClasses().size();
	        				int loadedComponentsNumber = treeBuilder.getLoadedComponents().size();
	        				int loadedExtendsRelationsNumber = treeBuilder.getLoadedExtendsRelations().size();
	        				
	        				message = message + "Number of loaded classes: " + loadedClassesNumber + "\n";
	        				message = message + "Number of loaded components: " + loadedComponentsNumber + "\n";
	        				message = message + "Number of loaded extends relations: " + loadedExtendsRelationsNumber + "\n";
	        				
	        				message = message + "\n";
	        				
	        				// CREATED OR UPDATED 
	        				int createdClassesNumber = ec.getCreatedClasses().size();
	        				String createdClassesNames = "";
	        				for (Element element : ec.getCreatedClasses()) {
								if (element instanceof NamedElement) {
									createdClassesNames = createdClassesNames + "        - " + ((NamedElement)element).getQualifiedName() + "\n";
								}
							}
	        				
	        				int updatedClassesNumber = ec.getUpdatedClasses().size();
	        				String updatedClassesNames = "";
	        				for (Element element : ec.getUpdatedClasses()) {
								if (element instanceof NamedElement) {
									updatedClassesNames = updatedClassesNames + "        - " + ((NamedElement)element).getQualifiedName() + "\n";
								}
							}
	        				
	        				int createdPropertiesNumber = ec.getCreatedProperties().size();
	        				String createdPropertiesNames = "";
	        				for (Element element : ec.getCreatedProperties()) {
								if (element instanceof NamedElement) {
									createdPropertiesNames = createdPropertiesNames + "        - " + ((NamedElement)element).getQualifiedName() + "\n";
								}
							}
	        				
	        				int updatedPropertiesNumber = ec.getUpdatedProperties().size();
	        				String updatedPropertiesNames = "";
	        				for (Element element : ec.getUpdatedProperties()) {
								if (element instanceof NamedElement) {
									updatedPropertiesNames = updatedPropertiesNames + "        - " + ((NamedElement)element).getQualifiedName() + "\n";
								}
							}
	        				
	        				int createdGeneralizationNumber = ec.getCreatedGeneralizations().size();
	        				int updateGeneralizationNumber = ec.getUpdatedGeneralizations().size();

	        				// classes
	        				if (createdClassesNumber > 0) {
		        				message = message + "Number of created classes: " + createdClassesNumber + "\n";
	        					message = message + createdClassesNames+ "\n";	
							}
	        				if (updatedClassesNumber > 0) {
		        				message = message + "Number of updated classes: " + updatedClassesNumber + "\n";
	        					message = message + updatedClassesNames+ "\n";	
							}

	        				// properties
	        				if (createdPropertiesNumber > 0) {
		        				message = message + "Number of created components: " + createdPropertiesNumber + "\n";
	        					message = message + createdPropertiesNames+ "\n";	
							}
	        				if (updatedPropertiesNumber > 0) {
		        				message = message + "Number of updated components: " + updatedPropertiesNumber+ "\n";
	        					message = message + updatedPropertiesNames+ "\n";	
							}
	        				
	        				// extends relations
	        				if (createdGeneralizationNumber > 0) {
		        				message = message + "Number of created extends relations: " + createdGeneralizationNumber + "\n";
							}
	        				if (updateGeneralizationNumber > 0) {
		        				message = message + "Number of updated extends relations: " + updateGeneralizationNumber + "\n";
							}
	        				
	        				// add log
	        				message = message + "\n\n********************   LOG ********************\n";
	        				for (String logEntry : ec.getLog()) {
	        					message = message + logEntry + "\n"; 
	        				}
	        				
	        				DialogMessage dialog = new DialogMessage(new Shell(), "Modelica Model Proxies Synchronization Report", null, message, false);
	        				dialog.open();
	        			}
	        		});
	            }
	            else {
	            	System.err.println("Could not complete the synchronization of Modelica Models Proxies.");
	            	}
				}
	         };
		
		actionSynchronize = new Action() {
			
			public Job createSynchJob(
					final boolean applyProxyStereotype,
					final boolean update,
					final boolean deleteNotUsedProxies
					){
						
				Job job = new Job("Creating ModelicaML Proxies for Modelica Models ..."){

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						
						ec = new ModelicaMLElementsCreator(serviceRegistry, 
								editingDomain, 
								umlModel, 
								ModelicaMLRoot,
								treeBuilder,
								monitor);
						
						TreeObject[] modelicaRoots = treeRoot.getChildren();
						for (int i = 0; i < modelicaRoots.length; i++) {
							
							TreeObject treeObject = modelicaRoots[i];
							
							if (treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("package")) {
								EObject modelicaRoot = ec.createProxyRoot(treeObject.getName(), true);
								treeObject.setModelicaMLProxy((Element) modelicaRoot);
								treeBuilder.addProxyToMaps((NamedElement) modelicaRoot);
								
								ec.createElements((Element)modelicaRoot, (TreeParent)treeObject, update, applyProxyStereotype, true);
								
								if (deleteNotUsedProxies) {
									ec.deleteInvalidProxyElements("");
								}
								
							}
						}
						
						done(Status.OK_STATUS);
						return Status.OK_STATUS;
					}
				};
				
				return job;
			}
			
			public void run() {
				
				if (!EditorServices.isVisiblePapyrusModelExplorerView()) {
					MessageDialog.openError(ModelicaMLServices.getShell(), "Modelica Model Proxies Synchronization Error", 
							"When synchronizing proxies the Model Explorer View must be visible " +
							"so that the viewer selection can be reset in order to avoid parallel access to " +
							"proxies that are displayed in Properties View and are modified by " +
							"the synchronization job at the same time." +
							
							"\n\nPlease make the Papyrus Model Explorer View visible and do not " +
							"select existing Modelica model proxies while the synchronization is running.");
					
					return;
				}
				
				/*
				 * TODO: if the element that is being updated is selected in GUI (i.e. in Papyrus modeling tool)
				 * then there will be a "SWT invalid thread exception because this job will modify it.
				 * WORK AROUND: Before creating elements set Papyrus Model Explorer Selection to 
				 * an element that will not be modified, e.g. the ModelicaML root. 
				 */
				EditorServices.locateInModelExplorer(treeBuilder.getModelicaMLRoot(), true);
				
				// check if all classes at the first level are packages.
				checkFirstLevelClasses(false);
				
				// Set all project- and model-related data
				configureTreeBuilder();
				
				// refresh in order to update the proxies (in case there were deleted in the mean time)
				actionRefreshAndValidate.run();

//				if (serviceRegistry != null && editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
				if (editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
										
					if (createProxiesAfterLoadingModelicaClasses) {

						// no dialog. use default values
						Job job = createSynchJob(
								applyProxyStereotype, 
								update, 
								deleteNotUsedProxies);

						job.addJobChangeListener(synchJobChangeAdapter);
						job.setUser(true);
						job.schedule();
						
					}
					else {
						// ask user for options
						
						/*
						 * Dialog for selecting the options: 
						 * 	- apply proxy stereotype
						 * 		A proxy stereotype indicates that the a ModelicaML element is not complete. 
						 *  	It is a proxy that reflects only information about a Modelica model that is needed to its usage.
						 *  	No code is generated from a proxy.
						 * 	- update existing proxies
						 * 	- delete proxies that do not exist in the loaded Modelica models (NOT RECOMMENDED). 
						 * 		If proxies are referenced by other elements then the references should 
						 * 		be redirected to new proxies before deleting the old proxies.
						 */

						
						SynchronizeOptionsDialog dialog = new SynchronizeOptionsDialog(getSite().getShell());
						dialog.open();
						
						int result = dialog.getReturnCode();
						
						if (result == IDialogConstants.OK_ID) {
							
							// set options
							final boolean applyProxyStereotype = dialog.isApplyProxyStereotype();
							final boolean update = dialog.isUpdate();
							final boolean deleteNotUsedProxies = dialog.isDeleteNotUsedProxies();
							
							final boolean fullImport = dialog.isFullImport();
							treeBuilder.setFullImport(fullImport);
							
							Job job = createSynchJob(applyProxyStereotype, update, deleteNotUsedProxies);

							job.addJobChangeListener(synchJobChangeAdapter);
							job.setUser(true);
							job.schedule();
						}
					}
				}
			}
		};
		actionSynchronize.setText("Synchronize All");
		actionSynchronize.setToolTipText("Synchronize All");
		actionSynchronize.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/copy_edit.gif"));
		actionSynchronize.setEnabled(false);
		
		
		actionLoadSubTree = new Action("actionLoadSubTree") {
			public void run() {
				ISelection selection = viewer.getSelection();
				if(selection instanceof StructuredSelection){
					
					for (Object obj : ((StructuredSelection)selection).toList()){
						if (obj instanceof ClassItem) {
							loadSubTree((ClassItem)obj);
						}
					}
				}
			}
		};
		actionLoadSubTree.setText("(Re)Load Sub-Tree");
		actionLoadSubTree.setToolTipText("(Re)Load Sub-Tree");
		actionLoadSubTree.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/import_resource_wiz.gif"));
		
		
		
		actionSynchronizeSubTree = new Action("actionSynchronizeSubTree") {
			public void run() {
				ISelection selection = viewer.getSelection();
				if(selection instanceof StructuredSelection){
					for (Object obj : ((StructuredSelection)selection).toList()){
						if (obj instanceof TreeObject) {
							synchronizeSubTree((TreeObject)obj);
						}
					}
				}
			}
		};
		actionSynchronizeSubTree.setText("Synchronize Sub-Tree");
		actionSynchronizeSubTree.setToolTipText("Synchronize Sub-Tree");
		actionSynchronizeSubTree.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/copy_edit.gif"));
		
		
		
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
						Object object = ((TreeObject)obj).getModelicaMLProxy();
						
						if (object instanceof EObject) {
							CommonViewer modelExplorerView = ((ModelExplorerView) modelExplorerPageBookView.getAdapter(ModelExplorerView.class)).getCommonViewer();
							List<Object> items = new ArrayList<Object>();
//							items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
							items.add((EObject) object);
							ModelExplorerView.reveal(items, modelExplorerView);
							
							modelExplorerView.getControl().setFocus();
//							modelExplorerView.setSelection(new StructuredSelection(items), true);
						}
					}
				}
			}
		};
		actionLocateInPapyrusModelExplorer.setText("Locate in Model Explorer");
		actionLocateInPapyrusModelExplorer.setToolTipText("Locate in Model Explorer");
		actionLocateInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.common", "/icons/editor/ModelExplorer.png")));
		
		
		actionLinkWithEditor = new Action("actionLinkWithEditor", 2) { //obviously a check box style
			public void run() {
			}
		};
//		actionLinkWithEditor.setChecked(true);
		actionLinkWithEditor.setText("Link with other Model Views");
		actionLinkWithEditor.setToolTipText("Link with other Model Views");
		actionLinkWithEditor.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));

		
		
		
		actionGenerateOMCMarkers = new Action("actionGenerateOMCMarkers", 2) {
			public void run() {
				if (actionGenerateOMCMarkers.isChecked()) {
					treeBuilder.setCreateOMCMarker(true);
				}
				else {
					treeBuilder.setCreateOMCMarker(false);
				}
			}
		};
		actionGenerateOMCMarkers.setText("Show OMC errors");
		actionGenerateOMCMarkers.setToolTipText("Show OMC errors");
		// set default 
		actionGenerateOMCMarkers.setChecked(true);
		treeBuilder.setCreateOMCMarker(actionGenerateOMCMarkers.isChecked());
		
		
		
		
		// Option to synchronize the protected elements or not
		actionSyncOnlyPublicElements = new Action("actionSyncOnlyPublicElements", 2) {
			public void run() {
				if (actionSyncOnlyPublicElements.isChecked()) {
					ViewerFilter[] filters = {showOnlyPublicComponentsFilter};
					viewer.setFilters(filters);
				}
				else {
					viewer.removeFilter(showOnlyPublicComponentsFilter);
				}
				
				// pass the selected option to tree builder
				treeBuilder.setSyncOnlyPublicComponents(actionSyncOnlyPublicElements.isChecked());
			}
		};
		actionSyncOnlyPublicElements.setText("Synchronize only public elements");
		actionSyncOnlyPublicElements.setToolTipText("Synchronize only public elements");

		// set default value for the option
		Boolean syncOnlyPublicElements = Platform.getPreferencesService().getBoolean("org.openmodelica.modelicaml.preferences", "syncOnlyPublicElements", true, null);
		actionSyncOnlyPublicElements.setChecked(syncOnlyPublicElements);
		
		treeBuilder.setSyncOnlyPublicComponents(actionSyncOnlyPublicElements.isChecked()); // default value when the view is started
		
		
		
		
		actionValidateProxies = new Action("actionValidateProxies", 2) {
			public void run() {
				if (actionValidateProxies.isChecked()) {
					treeBuilder.setValidateProxies(true);
				}
				else {
					treeBuilder.setValidateProxies(false);
				}
			}
		};
		actionValidateProxies.setText("Mark inconsistent ModelicaML proxies");
		actionValidateProxies.setToolTipText("Mark inconsistent ModelicaML proxies");
		// set default 
		actionValidateProxies.setChecked(true);
		treeBuilder.setValidateProxies(actionValidateProxies.isChecked());
		
		
				
		
		actionDecorateTreeItems = new Action("actionDecorateTreeItems", 2) {
			public void run() {
				if (actionDecorateTreeItems.isChecked()) {
					labelProvider.setDecorateItem(true);
				}
				else {
					labelProvider.setDecorateItem(false);
				}
			}
		};
		actionDecorateTreeItems.setText("Decorate items (disable it for large models)");
		actionDecorateTreeItems.setToolTipText("Decorate items (disable it for large models)");
		// set default 
		actionDecorateTreeItems.setChecked(true);
		labelProvider.setDecorateItem(true);
		
				
		
		// Collect proxies job listener
		final JobChangeAdapter collectProxiesJobChangeAdapter = new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
	            if (event.getResult().isOK()) {
	            	Display.getDefault().asyncExec(new Runnable() {
	        			public void run() {
	        				
	        				treeBuilder.updateTreeItemProxies(treeRoot);
	        				viewer.refresh();
	        			}
	        		});
	            }
	            else {
	            	Display.getDefault().asyncExec(new Runnable() {
	        			public void run() {
	        				
	        				MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML <-> Modelica Models Synchronization Error", "Could not complete the loading of ModelicaML proxies.");
	        			}
	        		});
	            	}
				}
	         };
		
	         
	         
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();

				// don't expand if the reload sub-tree is running
				if (!(reloadSubTreeJob.getState() == Job.RUNNING)) {

					/*
					 * NOTE: The very first time the user double clicks on the root tree item: 
					 * - configure the tree builder 
					 * - clear compiler, load models
					 * TODO: check if it is safe to rely on the fact that the ModelicaML model is set or not set in the tree builder?!  
					 * TODO: now if the root node is expanded we clear and load omc each time ... how to make it better? 
					 */
					if (treeBuilder.getModelicaMLModel() == null || obj == treeRoot) {
						
						// Set all project- and model-related data
						configureTreeBuilder();
						
						collectProxiesJob = new Job("Collecting ModelicaML Proxies") {
							
							@Override
							protected IStatus run(IProgressMonitor monitor) {
								treeBuilder.setMonitor(monitor);
								treeBuilder.collectModelicaModelProxies();
								
								done(Status.OK_STATUS);
								return Status.OK_STATUS;
							}
						};
						collectProxiesJob.addJobChangeListener(collectProxiesJobChangeAdapter);
						collectProxiesJob.setUser(true);
						collectProxiesJob.schedule();
						
						try {
							new ProgressMonitorDialog(getSite().getShell()).run(true, true,
									new IRunnableWithProgress() {
										@Override
										public void run(final IProgressMonitor monitor)
												throws InvocationTargetException,
												InterruptedException {
											
											monitor.beginTask("Loading Modelica Models ...", IProgressMonitor.UNKNOWN);
											treeBuilder.setMonitor(monitor);
											treeBuilder.loadModels();
											monitor.done();
										}
									});
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					// Set all project- and model-related data
					configureTreeBuilder();
					
					// expand or collapse node
					if (viewer != null && !viewer.getExpandedState(obj) && obj instanceof TreeParent) {
						final TreeParent parent = (TreeParent) obj;
						
						// If there are no children yet -> create children
						if (!parent.hasChildren()) {
							
							if (parent instanceof ClassItem || parent.getName().equals(Constants.folderName_code_sync)) {

								
								Job expandJob = new Job("Expanding '"+parent.getQName()+" ' ...") {
									
									@Override
									protected IStatus run(IProgressMonitor monitor) {
										
										treeBuilder.setMonitor(monitor);
										
										// create nested classes nodes
//										ArrayList<TreeObject> createdNestedClasses = treeBuilder.createClassNodes(parent, parent.getQName(), false);
										treeBuilder.createClassNodes(parent, parent.getQName(), false);
									
										// create components and extends relations nodes.
//										ArrayList<TreeObject> createdClassElements = treeBuilder.createComponentNodes(parent, false);
										treeBuilder.createClassElementNodes(parent, false);

										monitor.done();
										done(Status.OK_STATUS);
										return Status.OK_STATUS;
									}
								};
								
								// Load sub tree job listener
								final JobChangeAdapter expandSubTreeJobChangeAdapter = new JobChangeAdapter() {
									public void done(IJobChangeEvent event) {
							            if (event.getResult().isOK()) {
							            	Display.getDefault().asyncExec(new Runnable() {
							        			public void run() {
							        				
							        				// refresh and expand the parent
							        				viewer.refresh(parent);
							    					viewer.expandToLevel(parent, 1);
							    					
							    					if (!treeRoot.hasChildren()) {
														MessageDialog.openInformation(getSite().getShell(), "Loading of Modelica Models", 
																"No Modelica models were found in " + getProjectName() + "/" + Constants.folderName_code_sync + "/");
													}
							        			}
							        		});
							            }
							            else {
							            	System.err.println("Could not complete the expanding of tree item.");
							            	}
										}
							         };
								
								expandJob.addJobChangeListener(expandSubTreeJobChangeAdapter);
								expandJob.setUser(false);
								expandJob.schedule();
							}
						}

						// expand selected item
						viewer.expandToLevel(parent, 1);
					}
					else { //Collapse this item
						if (obj instanceof ClassItem || (obj instanceof TreeParent && ((TreeParent)obj).getName().equals(Constants.folderName_code_sync)) ) {
							viewer.collapseToLevel(obj, 1);	
						}
					}
				}
				else {
					//Collapse this item if it is expanded
					if (viewer.getExpandedState(obj) && obj instanceof ClassItem || (obj instanceof TreeParent && ((TreeParent)obj).getName().equals(Constants.folderName_code_sync))) {
							viewer.collapseToLevel(obj, 1);	
					}
					else { // inform user to wait
						MessageDialog.openInformation(getSite().getShell(), "Please Wait", "Please wait until the reload sub-tree job has finished.");
					}
				}
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

	
	private Job getSynchronizeSubTreeJob(final TreeObject treeObject, 
			final boolean update, 
			final boolean applyProxyStereotype,
			final boolean deleteIncositentProxies
			) {
		
		Job job = new Job("Synchronizing " + treeObject.getName() + " ...") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				/*
				 * - get all parents top-down
				 * - create/update all parents and the selected item (NOT recursively!) 
				 * - synchronize starting from the selected item 
				 */
				
				ec = new ModelicaMLElementsCreator(
						serviceRegistry, 
						editingDomain, 
						umlModel, 
						ModelicaMLRoot,
						treeBuilder,
						monitor);

				Element UMLParent = null;

				// create or update parents and the selected class
				List<TreeObject> list = getTopDownPathItems(treeObject);
				
				for (TreeObject topDownParent : list) {
					// only create classes
					if (topDownParent instanceof ClassItem) {
						
						// if it is a root -> get or create the model root
						if (topDownParent.getParent().getName().equals(Constants.folderName_code_sync)) {
							
							if (topDownParent instanceof ClassItem && 
									!( ((ClassItem)topDownParent).getClassRestriction().equals("package") || ((ClassItem)topDownParent).getClassRestriction().contains("operator")) ) {

								// Forward the model to the utilities class for creating markers
								Utilities.ModelicaMLModel = treeBuilder.getModelicaMLModel();

								// generate marker
								// No need to generate the marker because it was already generated when (re)loading the sub-tree
//								Utilities.createOMCMarker(topDownParent, "error", "The model '"+topDownParent.getQName()+"' is not a package. " + "At the top level all elements must be packages.");
							}
							else {
								// only create a model if it is a package
								UMLParent = (Element) ec.createProxyRoot(topDownParent.getName(), applyProxyStereotype);

								topDownParent.setModelicaMLProxy(UMLParent);
								treeBuilder.addProxyToMaps((NamedElement) UMLParent);
							}
						}
						// if a proxy exists -> update it
						else if (topDownParent.getModelicaMLProxy() != null) {
							ec.updateClass(topDownParent.getModelicaMLProxy(), (ClassItem) topDownParent, applyProxyStereotype);
							UMLParent = topDownParent.getModelicaMLProxy();

							topDownParent.setModelicaMLProxy(UMLParent);
							treeBuilder.addProxyToMaps((NamedElement) UMLParent);
						}
						else { // create a new proxy
							UMLParent = (Element) ec.createClass(UMLParent, (ClassItem) topDownParent, applyProxyStereotype);
							ec.updateClass(UMLParent, (ClassItem) topDownParent, applyProxyStereotype);

							topDownParent.setModelicaMLProxy(UMLParent);
							treeBuilder.addProxyToMaps((NamedElement) UMLParent);
						}
					}
				}
				
				// If the selected element is a class -> sync. class recursively
				if (treeObject instanceof ClassItem && UMLParent != null) {
					ec.createElements(UMLParent, (TreeParent) treeObject, update, applyProxyStereotype, true);
				}
				
				// If the selected item is a component -> sync. only this component
				else if (treeObject instanceof ComponentItem) {
					Element existingProperty = ((Classifier)UMLParent).getOwnedMember(treeObject.getName());
					if (existingProperty instanceof Property) {
						
						ec.updateProperty(UMLParent, existingProperty, (ComponentItem) treeObject, applyProxyStereotype);
						treeObject.setModelicaMLProxy(existingProperty);
						treeBuilder.addProxyToMaps((Property) existingProperty);
					}
					else {
						NamedElement property = (NamedElement) ec.createProperty(UMLParent, (ComponentItem) treeObject, applyProxyStereotype);

						ec.updateProperty(UMLParent, property, (ComponentItem) treeObject, applyProxyStereotype);
						treeObject.setModelicaMLProxy(property);
						treeBuilder.addProxyToMaps(property);
					}
				}
				
				/*
				 * Delete all proxies that are not used, i.e. which are not present in the loaded Modelica models.
				 * Delete markers
				 */
				if (deleteIncositentProxies) {
					ec.deleteInvalidProxyElements(treeObject.getQName());
					Utilities.deleteProxyValidationMarkers(getProject(), treeObject.getQName());
				}
				
				/*
				 * Validate the sub-tree proxies after sync. 
				 */
				IProject project = getProject();
				if (project != null && treeObject.getModelicaMLProxy() != null) {
					treeBuilder.validateProxies(project, (TreeParent)treeObject);
				}
				else {
//					Utilities.createOMCMarker(treeObject, "error", "Could not validate the sub-tree starting with '" + treeObject.getName() + "'.");
				}
				
				done(Status.OK_STATUS);
				return Status.OK_STATUS;
			}
		};
		return job;
	}

	
	private Job getReloadSubTreeJob(final TreeParent parent){
		
			Job job = new Job("Loading '"+parent.getQName()+" ' ...") {
				
				@Override
				protected IStatus run(IProgressMonitor monitor) {
	
					// remove children
					TreeObject[] children = parent.getChildren();
					for (int i = 0; i < children.length; i++) {
						TreeObject treeObject = children[i];
						parent.removeChild(treeObject);
					}
					
					treeBuilder.setMonitor(monitor);
					
					//reload models in order reflect changes
					treeBuilder.loadModels();
					
					// update class node
					if (parent instanceof ClassItem) {
						treeBuilder.updateClassNode((ClassItem) parent);						
					}
					
					// add parent components
					treeBuilder.createClassElementNodes(parent, false);
					
					// add nested classes (recursively) their components (not recursively because once createClassNodes was called with recursive=true it will give all nested classes)
					ArrayList<TreeObject> classes = treeBuilder.createClassNodes(parent, parent.getQName(), true);
					
					// For each nested class create its components
					for (TreeObject addedClass : classes) {
						treeBuilder.createClassElementNodes((TreeParent) addedClass, false);
					}
					
					// validate the subtree
					IProject project = getProject();
					if (project != null && parent.getModelicaMLProxy() != null) {
						treeBuilder.validateProxies(project, parent);
					}
					else{
//						System.err.println("Could not validate the sub-tree starting with " + parent.getName());
					}
					
					monitor.done();
					done(Status.OK_STATUS);
					monitor.setTaskName("");
					
					return Status.OK_STATUS;
				}
			};
			
			return job;
		}

	private void synchronizeSubTree(TreeObject treeObject) {
		
		if ( collectProxiesJob.getState() == Job.RUNNING ) {
			MessageDialog.openInformation(getSite().getShell(), "Please Wait", 
					"Please wait until the ModelicaML proxies are collected.");
			return;
		}
		
		SynchronizeOptionsDialog dialog = new SynchronizeOptionsDialog(getSite().getShell());
		dialog.open();
		
		int result = dialog.getReturnCode();

		// set synchronization options
		boolean applyProxyStereotype = dialog.isApplyProxyStereotype();
		boolean update = dialog.isUpdate();
		boolean deleteInconsistentProxies = dialog.isDeleteNotUsedProxies();
		
		boolean fullImport = dialog.isFullImport();
		treeBuilder.setFullImport(fullImport);

		if (! (result == IDialogConstants.OK_ID)) {
			return;
		}
		
		/*
		 * TODO: if the element that is being updated is selected in GUI (i.e. in Papyrus modeling tool)
		 * then there will be a "SWT invalid thread exception because this job will modify it.
		 * WORKAROUND: before creating elements set Papyrus Model Explorer Selection to 
		 * an element that will not be modified, e.g. the ModelicaML root. 
		 */
		EditorServices.locateInModelExplorer(treeBuilder.getModelicaMLRoot(), true);
		
		// start sync
		if (umlModel != null) {
//			if (ModelicaMLRoot != null && serviceRegistry != null && editingDomain != null) {
			if (ModelicaMLRoot != null && editingDomain != null) {
				
				// Sync sub tree job listener
				final JobChangeAdapter syncSubTreeJobChangeAdapter = new JobChangeAdapter() {
					public void done(IJobChangeEvent event) {
			            if (event.getResult().isOK()) {
			            	Display.getDefault().asyncExec(new Runnable() {
			        			public void run() {
			        				
//			        				showErrorLogDialog();
			        				
			        				viewer.refresh(); 				
			        			}
			        		});
			            }
			            else {
			            	System.err.println("Could not complete the loading of sub-tree.");
			            	}
						}
			         };
			        
			    // sync sub tree job
				final Job syncSubTreeJob = getSynchronizeSubTreeJob(treeObject, update, applyProxyStereotype, deleteInconsistentProxies);
				syncSubTreeJob.addJobChangeListener(syncSubTreeJobChangeAdapter);
				
				// first reload the sub tree to make sure that the tree is complete
				if (treeObject instanceof ClassItem) {
					
					// Set all project- and model-related data
					configureTreeBuilder();
					
					reloadSubTreeJob = getReloadSubTreeJob((TreeParent) treeObject);
					
					// Load sub tree job listener
					final JobChangeAdapter loadSubTreeJobChangeAdapter = new JobChangeAdapter() {
						public void done(IJobChangeEvent event) {
				            if (event.getResult().isOK()) {
				            	Display.getDefault().asyncExec(new Runnable() {
				        			public void run() {
				        				
				        				showErrorLogDialog();
				        				
				        				syncSubTreeJob.setUser(true);
				        				syncSubTreeJob.schedule();				
				        			}
				        		});
				            }
				            else {
				            	System.err.println("Could not complete the loading of sub-tree.");
				            	}
							}
				         };
					
					reloadSubTreeJob.addJobChangeListener(loadSubTreeJobChangeAdapter);
					reloadSubTreeJob.setUser(true);
					
					if (!(reloadSubTreeJob.getState() == Job.RUNNING)) {
						reloadSubTreeJob.schedule();
					}
				}
				else {
					// if the selected item is a component -> launch the sync. sub tree job
					syncSubTreeJob.setUser(true);
					syncSubTreeJob.schedule();
				}	
			}
			else {
				MessageDialog.openError(getSite().getShell(), "Synchronization Error", 
						"Cannot access the editing domain of the active ModelicaML model. " +
						"Please make sure that the ModelicaML model is open in the active editor and try it again.");
			}
		}
		else {
			MessageDialog.openError(getSite().getShell(), "Synchronization Error", 
					"Cannot access the ModelicaML model. " +
					"Please make sure that the ModelicaML model is open in the active editor.");
		}
	}
	

	private void loadSubTree(final TreeParent parent){
		
		if (parent instanceof ClassItem) {
			
			// Set all project- and model-related data
			configureTreeBuilder();
			
			reloadSubTreeJob = getReloadSubTreeJob(parent);
			
			// Load sub tree job listener
			final JobChangeAdapter loadSubTreeJobChangeAdapter = new JobChangeAdapter() {
				public void done(IJobChangeEvent event) {
		            if (event.getResult().isOK()) {
		            	Display.getDefault().asyncExec(new Runnable() {
		        			public void run() {
		        				
		        				showErrorLogDialog();
		        				
		        				viewer.refresh(parent);
		    					viewer.expandToLevel(parent, 1);
		        			}
		        		});
		            }
		            else {
		            	System.err.println("Could not complete the loading of sub-tree.");
		            	}
					}
		         };
			
			reloadSubTreeJob.addJobChangeListener(loadSubTreeJobChangeAdapter);
			reloadSubTreeJob.setUser(true);
			
			if (!(reloadSubTreeJob.getState() == Job.RUNNING)) {
				reloadSubTreeJob.schedule();
			}
		}
	}
	
	
	
	private void setTreeRootProjectName(){
		umlModel = UmlUtils.getUmlModel();
		labelProvider.setProjectName(" - ?");
		if (umlModel != null) {
			String projectName = umlModel.getResource().getURI().segment(1);
			labelProvider.setProjectName(projectName);
		}
	}
	
	private void configureTreeBuilder(){
		
		// TODO: this should be set via user dialog
//		treeBuilder.setFullImport(true);
		
		// As a job
		umlModel = UmlUtils.getUmlModel();
		if (umlModel == null) {
			MessageDialog.openError(getSite().getShell(), 
					"ModelicaML Model Editing Domain Access Error", 
					"Cannot acceess the ModelicaML model or its editing domain. " +
					"Please make sure that the ModelicaML model is open in the active editor.");
		}
		else {
			
			String projectName = umlModel.getResource().getURI().segment(1);
			labelProvider.setProjectName(projectName);

			try {
				ModelicaMLRoot = umlModel.lookupRoot();
//					serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
//					editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
					editingDomain = EditorServices.getPapyrusEditingDomain();
					
//					if (serviceRegistry != null && editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
					if (editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
						
						// set the uml model to encapsulate the job required data
						treeBuilder.setModelicaMLModel(umlModel);
						treeBuilder.setModelicaMLRoot(ModelicaMLRoot);
						
						
						/*
						 * Set models that should not be loaded. 
						 * The main purpose is to avoid loading libraries that are not yet supported.  
						 */
						HashSet<String> modelsToBeExcluded = new HashSet<String>();
//						modelsToBeExcluded.add("Modelica.Fluid");
//						modelsToBeExcluded.add("Modelica.UsersGuide");
//						modelsToBeExcluded.add("ModelicaServices");
						
						treeBuilder.setModelsToBeExcluded(modelsToBeExcluded);
        				
					}
					else {
						MessageDialog.openError(getSite().getShell(), "Loading error", 
								"Could not access the Papyrus editing domain and the uml model.");
					}
			} catch (NotFoundException e) {
				MessageDialog.openError(getSite().getShell(), 
						"ModelicaML Model Access Error", 
					"Cannot acceess the ModelicaML model. " +
					"Please make sure that the ModelicaML model is open in the active editor.");
				
				e.printStackTrace();
			}
		}
	}

	
	public void checkFirstLevelClasses(boolean openErrorDioalog){
		TreeObject[] children = treeRoot.getChildren();
		HashSet<TreeObject> invalidFirstLevelClasses = new HashSet<TreeObject>();

		for (int i = 0; i < children.length; i++) {
			TreeObject treeObject = children[i];
			
//			System.err.println(((ClassItem)treeObject).getClassRestriction());
			if (treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("package")) {
				// ok, do nothing
			}
			else {
				invalidFirstLevelClasses.add(treeObject);
			}
		}
		
		if (invalidFirstLevelClasses.size() > 0) {
			String title = "ModelicaML Proxies Synchronization Error";
			String message = "To use the ModelicaML Modelica models synchronization feature all first level Modelica classes " +
					"must be packages. Moreover, these packages must not have extends relations " +
					" to other packages and not be encapsulated, partial, final or replaceable. " +
					"The following first level classes cannot be synchronized: \n\n";
			
			String invalidClassesString = "";
			for (TreeObject invalideClass : invalidFirstLevelClasses) {
				invalidClassesString = invalidClassesString  + "             -" + invalideClass.getQName() + "\n";
			}
			message = message + invalidClassesString;
			
			if (openErrorDioalog) {
				MessageDialog.openError(getSite().getShell(), title, message);
			}
			
			Utilities.createOMCMarker(treeRoot, "error", 
					invalidClassesString + " cannot be synchronized because they are not packages.");
		}
	}
	
	
	 private List<TreeObject> getTopDownPathItems(TreeObject startItem){
		List<TreeObject> listOfSegments = new ArrayList<TreeObject>();
		listOfSegments.add(startItem);
		listOfSegments.addAll(getPathFromItem(startItem, new ArrayList<TreeObject>()) );
		
		// reverse the path to get the top-down path
		Collections.reverse(listOfSegments);

		return listOfSegments;
	}
	
	private ArrayList<TreeObject> getPathFromItem(TreeObject item, ArrayList<TreeObject> listOfSegments){
		TreeObject parent = item.getParent();
		if (parent != null && !parent.getName().equals(Constants.folderName_code_sync)) { // the root node represents the folder. It should not appear in the path.  
			listOfSegments.add(parent);
			listOfSegments.addAll( getPathFromItem(parent, new ArrayList<TreeObject>()) );
		}
		return listOfSegments;
	}
	
	private TreeObject findTreeItem(String path){
		TreeObject item = null;
		
		for (TreeObject treeObject : treeBuilder.getTreeItems()) {
			if (treeObject instanceof TreeParent) {
				if (path.equals( ((TreeParent)treeObject).getQName()) ) {
					return treeObject;
				}
			}
		}
		return item;
	}
	
	
//	private ArrayList<TreeObject> findTreeItems(ArrayList<String> parentPaths){
//		ArrayList<TreeObject> items = new ArrayList<TreeObject>();
//		
//		for (TreeObject treeObject : treeBuilder.getTreeItems()) {
//			if (treeObject instanceof TreeParent) {
//				if (parentPaths.contains( ((TreeParent)treeObject).getQName()) ) {
//					items.add(treeObject);
//				}
//			}
//		}
//		return items;
//	}
	
	
	public void locate(Element selectedElement){
		HashSet<Object> objects = findTreeItem(selectedElement);
		List<Object> newSelectionItems = new ArrayList<Object>();
		newSelectionItems.addAll(objects);
		viewer.setSelection(new StructuredSelection(newSelectionItems), true);
	}
	
	public void locate(String qName){
		TreeObject treeObject = findTreeItem(qName);
		List<Object> newSelectionItems = new ArrayList<Object>();
		newSelectionItems.add(treeObject);
		viewer.setSelection(new StructuredSelection(newSelectionItems), true);
	}
	
	private HashSet<Object> findTreeItem(EObject selectedElement){
		HashSet<Object> items = new HashSet<Object>();
		
		for (TreeObject treeObject : treeBuilder.getTreeItems()) {
			Element proxy = treeObject.getModelicaMLProxy();
			if (proxy != null && proxy.equals(selectedElement)) {
				items.add(treeObject);
			}
		}
		return items;
	}
	
	private IProject getProject(){
		if (umlModel != null && umlModel.getResource() != null) {
			String projectName = umlModel.getResource().getURI().segment(1);
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject iProject = root.getProject(projectName);
			return iProject;
		}
		return null;
	}
	
	private String getProjectName(){
		if (umlModel != null && umlModel.getResource() != null) {
			String projectName = umlModel.getResource().getURI().segment(1);
			return projectName;
		}
		return "? Unknown project";
	}
	
	//************************************** Filters
	
	// Filter for "Sync only public components" 
	ShowOnlyPublicComponentsFilter showOnlyPublicComponentsFilter = new ShowOnlyPublicComponentsFilter();
	class ShowOnlyPublicComponentsFilter extends ViewerFilter {
			@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
				
				//protected components
				if (element instanceof ComponentItem) {
					ComponentItem item = ((ComponentItem)element); 
					//System.err.println(item.getName() + ": " + item.getVisibility());
					if (item.getVisibility().trim().equals("protected")) {
						return false;
					}
				}
				
				//protected classes
				if (element instanceof ClassItem) {
					ClassItem item = ((ClassItem)element); 
					//System.err.println(item.getName() + ": " + item.isProtected());
					if (item.isProtected()) {
						return false;
					}
				}
			return true;
		}
	}
	
	
	
//	private void showMessage(String message) {
//		MessageDialog.openInformation(
//			viewer.getControl().getShell(), "Modelica Models Synchronization", message);
//	}

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

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	public TreeBuilder getTreeBuilder() {
		return treeBuilder;
	}

	public TreeParent getTreeRoot() {
		return treeRoot;
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
			
			// Modelica code view 
			IViewPart viewPartModelicaCodeSynchonization = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELICA_CODE_SYNCHRONIZATION);
			if(viewPartModelicaCodeSynchonization instanceof ModelicaOMCCodeViewer) {
				((ModelicaOMCCodeViewer)viewPartModelicaCodeSynchonization).locate((Element) eObject);
			}
		}
		
		// Modelica code view for markers without UML elements but having LOCATION path defined 
		String location = marker.getAttribute(IMarker.LOCATION, null);
		if (!location.isEmpty() && location != null) {
			IViewPart viewPartModelicaCodeSynchonization = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_MODELICA_CODE_SYNCHRONIZATION);
			if(viewPartModelicaCodeSynchonization instanceof ModelicaOMCCodeViewer) {
				// for code importer use the marker attribute location
				((ModelicaOMCCodeViewer)viewPartModelicaCodeSynchonization).locate(location);
			}
		}
	}

}