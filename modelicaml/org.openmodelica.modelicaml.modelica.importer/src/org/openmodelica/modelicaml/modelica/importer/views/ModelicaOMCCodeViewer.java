package org.openmodelica.modelicaml.modelica.importer.views;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.papyrus.core.listenerservice.IPapyrusListener;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.services.ServicesRegistry;
import org.eclipse.papyrus.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.core.utils.ServiceUtils;
import org.eclipse.papyrus.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.modelexplorer.ModelExplorerView;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.ExtendedUmlModel;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.dialogs.DialogMessage;
import org.openmodelica.modelicaml.common.services.PapyrusServices;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.modelica.importer.Activator;
import org.openmodelica.modelicaml.modelica.importer.dialogs.SynchronizeOptionsDialog;
import org.openmodelica.modelicaml.modelica.importer.display.ViewLabelProviderStyledCell;
import org.openmodelica.modelicaml.modelica.importer.helper.ModelicaMLElementsCreator;
import org.openmodelica.modelicaml.modelica.importer.model.ClassItem;
import org.openmodelica.modelicaml.modelica.importer.model.ComponentItem;
import org.openmodelica.modelicaml.modelica.importer.model.TreeBuilder;
import org.openmodelica.modelicaml.modelica.importer.model.TreeObject;
import org.openmodelica.modelicaml.modelica.importer.model.TreeParent;

public class ModelicaOMCCodeViewer extends ViewPart {

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
	
	private Action actionCollapseAll;
	private Action actionExpandCollapse;
	
	private Action actionLocateInPapyrusModelExplorer;
	private Action actionLinkWithEditor;
	
	private Action actionGenerateOMCMarkers;
	private Action actionValidateProxies;
	private Action actionDecorateTreeItems;

	// Default values for sync. options
	private boolean createProxiesAfterLoadingModelicaClasses = false;
	private boolean applyProxyStereotype = true;
	private boolean update = true;
	private boolean deleteNotUsedProxies = false;
	private boolean fullImport = false;
	
	// Listeners
	private ISelectionListener selectionListener = new ISelectionListener() {
		 public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
			 if (actionLinkWithEditor.isChecked() && sourcepart != ModelicaOMCCodeViewer.this && selection instanceof IStructuredSelection) {
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
	
	
//	class ViewLabelProvider extends LabelProvider {
//
//		public String getText(Object obj) {
//			return obj.toString();
//		}
//		public Image getImage(Object obj) {
//			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
//			if (obj instanceof TreeParent)
//			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
//			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
//		}
//	}
	
	
	
	
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
		viewer = new TreeViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(labelProvider);
		
//		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.openmodelica.modelicaml.modelica.importer.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		

//		// get Papyrus Model Explorer
//		IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.papyrus.modelexplorer.modelexplorer");
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
		
	}

	private void fillContextMenu(IMenuManager manager) {

		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection)selection).getFirstElement();

		String title = "Sub-Tree";
		if (obj instanceof ClassItem) {
			String restriction = ((ClassItem)obj).getClassRestriction();
			String firstLetter = restriction.substring(0, 1);
			title = firstLetter.toUpperCase() + restriction.substring(1,restriction.length()); 
		}
		else if (obj instanceof ComponentItem) {
			title = "Component";
		}
		
		// action for (re)loading sub-trees
		if (obj instanceof ClassItem ) {
			manager.add(actionLoadSubTree);
			actionLoadSubTree.setText("(Re)Load This " + title);
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
			actionSynchronizeSubTree.setText("Synchronize This " + title);
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
		
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		
		// If it is the root (folder) or a class -> add expand/collapse action
		if (obj instanceof ClassItem || (obj instanceof TreeParent && ((TreeParent)obj).getName().equals(Constants.folderName_code_sync) )) {
			if (!viewer.getExpandedState(obj)) {
				actionExpandCollapse.setText("Expand");
//				actionExpandCollapse.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
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
//				actionExpandCollapse.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
				actionExpandCollapse.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/collapse.gif"));

				actionExpandCollapse.setEnabled(true);
			}
			manager.add(actionExpandCollapse);
			manager.add(new Separator());
		}
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {

		manager.add(actionCollapseAll);

		manager.add(actionLinkWithEditor);
		manager.add(new Separator());

		manager.add(actionReload);
		manager.add(actionSynchronize);
		manager.add(actionRefreshAndValidate);
		manager.add(actionClear);

		manager.add(new Separator());
		
//		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		
		// Load job listener
		final JobChangeAdapter loadJobChangeAdapter = new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
	            if (event.getResult().isOK()) {
	            	Display.getDefault().asyncExec(new Runnable() {
	        			public void run() {
	        				
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
								PapyrusServices.locateWithReselection(treeBuilder.getModelicaMLRoot());

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
		        				
		        				DialogMessage dialog = new DialogMessage(getSite().getShell(), "Modelica Model Loading Report", null, message);
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
				
				if (!PapyrusServices.isVisiblePapyrusModelExplorerView()) {
					MessageDialog.openError(getSite().getShell(), "Modelica Model Proxies Synchronization Error", 
							"When synchronizing proxies the Papyrus Model Explorer View must be visible " +
							"so that the viewer selection can be reset in order to avoid cuncurrent access to " +
							"proxies that are displayed in Papyrus Properties View and are modified by " +
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
					if (serviceRegistry != null && editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
						
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
								
								TreeObject[] children = treeRoot.getChildren();
								for (int i = 0; i < children.length; i++) {
									treeRoot.removeChild(children[i]);
								}
								
								// set the uml model to encapsulate the job required data
								treeBuilder.setModelicaMLModel(umlModel);
								treeBuilder.setModelicaMLRoot(ModelicaMLRoot);
//										treeBuilder.setModelicamlProfile();
								
								// delete OMC markers
								String projectName = umlModel.getResource().getURI().segment(1);
								IWorkspace workspace = ResourcesPlugin.getWorkspace();
								IWorkspaceRoot root = workspace.getRoot();
								IProject iProject = root.getProject(projectName);

								treeBuilder.deleteOMCMarkers(iProject);

								// build tree
								treeBuilder.buildTree(treeRoot, null);
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
				
				//******************************** Using ProgressMonitor
//				try {
//					new ProgressMonitorDialog(new Shell()).run(true, true, treeBuilder);
//
//					Object[] expandedElements = viewer.getExpandedElements();
//					TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
//					
//					TreeObject[] children = treeRoot.getChildren();
//					for (int i = 0; i < children.length; i++) {
//						treeRoot.removeChild(children[i]);
//					}
//					
//					treeBuilder.buildTree(treeRoot);
//					
//					if (treeRoot.hasChildren()) {
//						actionSynchronize.setEnabled(true);
//					}
//					else {
//						actionSynchronize.setEnabled(false);
//					}
//
//					viewer.setInput(getViewSite());
//					
//					viewer.setExpandedElements(expandedElements);
//					viewer.setExpandedTreePaths(expandedTreePaths);
//
//				} catch (InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				

				//************************** Using a GUI Job
//				UmlModel umlModel = UmlUtils.getUmlModel();
//				Object ModelicaMLRoot = null;
//				try {
//					ModelicaMLRoot = umlModel.lookupRoot();
//				} catch (NotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				ServicesRegistry serviceRegistry = null;
//				TransactionalEditingDomain editingDomain = null;
//				try {
//					serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
//					editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
//				} catch (ServiceException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//				if (serviceRegistry != null && editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
//					UIJob UIjob = new UIJob("Loading Modelica models ...") {
//						public IStatus runInUIThread(IProgressMonitor monitor) {
//							
//							Object[] expandedElements = viewer.getExpandedElements();
//							TreePath[] expandedTreePaths = viewer.getExpandedTreePaths();
//							
//							TreeObject[] children = treeRoot.getChildren();
//							for (int i = 0; i < children.length; i++) {
//								treeRoot.removeChild(children[i]);
//							}
//
//							treeBuilder.buildTree(treeRoot);
//							
//							if (treeRoot.hasChildren()) {
//								actionSynchronize.setEnabled(true);
//							}
//							else {
//								actionSynchronize.setEnabled(false);
//							}
//
//							viewer.setInput(getViewSite());
//							
//							viewer.setExpandedElements(expandedElements);
//							viewer.setExpandedTreePaths(expandedTreePaths);
//							
//							return Status.OK_STATUS;
//						}
//					};
//					UIjob.setUser(true);
//					UIjob.schedule();
//				}
//				else {
//					MessageDialog.openError(new Shell(), "Invocation error", "Could not load Modelica models.");
//				}
			}
		};
		actionReload.setText("(Re)Load All");
		actionReload.setToolTipText("(Re)Load All");
//		actionReload.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEF_VIEW));
//		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/load.png"));
//		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/import_obj.gif"));
		actionReload.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/import_resource_wiz.gif"));
		
		
		actionCollapseAll = new Action("actionCollapseAll") { //obviously a check box style
			public void run() {
				TreeObject[] children = treeRoot.getChildren();
				for (int i = 0; i < children.length; i++) {
					viewer.collapseToLevel(children[i], AbstractTreeViewer.ALL_LEVELS);
//					viewer.collapseToLevel(children[i], 1); // collapse current element in the tree
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
						"Please make sure that the ModelicaML model is open in Papyrus editor.");
				}
				
				viewer.refresh();
			}
		};
		actionRefreshAndValidate.setText("Validate All");
		actionRefreshAndValidate.setToolTipText("Validate All");
//		actionRefresh.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/reload.png"));
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
	        				
	        				DialogMessage dialog = new DialogMessage(getSite().getShell(), "Modelica Model Proxies Synchronization Report", null, message);
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
								treeBuilder);
						
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
						return Status.OK_STATUS;
					}
				};
				
				return job;
			}
			
			public void run() {
				
				//********** with ProgressMonitor
//				try {
//					new ProgressMonitorDialog(new Shell()).run(true, true,
//							new IRunnableWithProgress() {
//								@Override
//								public void run(final IProgressMonitor monitor)
//										throws InvocationTargetException,
//										InterruptedException {
//									monitor.beginTask("Creating ModelicaML Proxies ...",IProgressMonitor.UNKNOWN);
//									
//									for (int total = 1; total < 2; total++) {
//										if (monitor.isCanceled())
//											throw new OperationCanceledException();
//										Thread.sleep(1000l); //TODO [20100506] CAN BE DELETED
//										
//										switch (total) {
//										case 1:
//											monitor.setTaskName("Creating ModelicaML Elements ...");
//											getSite().getShell().getDisplay().asyncExec(new Runnable() {
//												public void run() {
//													ModelicaMLElementsCreator ec = new ModelicaMLElementsCreator();
//													TreeObject[] modelicaRoots = treeRoot.getChildren();
//													for (int i = 0; i < modelicaRoots.length; i++) {
//														TreeObject treeObject = modelicaRoots[i];
//														EObject modelicaRoot = ec.createProxyRoot(treeObject.getName());
//														
//														if (modelicaRoot instanceof Element && treeObject instanceof TreeParent) {
//															ec.createClasses((Element)modelicaRoot, (TreeParent)treeObject, false);
//														}
//													}
//												}
//											});
//											monitor.worked(1);
//											break;
//										case 2:
//											monitor.setTaskName("Step: " + total + ", Creating ModelicaML Elements ...");
//											getSite().getShell().getDisplay().asyncExec(new Runnable() {
//												public void run() {
//
//												}
//											});
//											monitor.worked(2);
//											break;
//										default:
//											break;
//										}
//									}
//									monitor.done();
//								}
//							});
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					System.out.println("InterruptedException");
//				} catch (OperationCanceledException e){
//					System.out.println("OperationCanceledException");
//				}
								
				
				//***************** simple
//				ModelicaMLElementsCreator ec = new ModelicaMLElementsCreator();
//				TreeObject[] modelicaRoots = treeRoot.getChildren();
//				for (int i = 0; i < modelicaRoots.length; i++) {
//					TreeObject treeObject = modelicaRoots[i];
//					EObject modelicaRoot = ec.createProxyRoot(treeObject.getName());
//					
//					if (modelicaRoot instanceof Element && treeObject instanceof TreeParent) {
//						try {
//							new ProgressMonitorDialog(new Shell()).run(true, true, ec);
//							ec.createClasses((Element)modelicaRoot, (TreeParent)treeObject, false);
//						} catch (InvocationTargetException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
////						ec.createClasses((Element)modelicaRoot, (TreeParent)treeObject, false);
//					}
//				}
				
				
				if (!PapyrusServices.isVisiblePapyrusModelExplorerView()) {
					MessageDialog.openError(getSite().getShell(), "Modelica Model Proxies Synchronization Error", 
							"When synchronizing proxies the Papyrus Model Explorer View must be visible " +
							"so that the viewer selection can be reset in order to avoid parallel access to " +
							"proxies that are displayed in Papyrus Properties View and are modified by " +
							"the synchronization job at the same time." +
							
							"\n\nPlease make the Papyrus Model Explorer View visible and do not " +
							"select existing Modelica model proxies while the synchronization is running.");
					
					return;
				}
				
				/*
				 * TODO: if the element that is being updated is selected in GUI (i.e. in Papyrus modeling tool)
				 * then there will be a "SWT invalid thread exception because this job will modify it.
				 * WORAROUND: before creating elements set Papyrus Model Explorer Selection to 
				 * an element that will not be modified, e.g. the ModelicaML root. 
				 */
				PapyrusServices.locateWithReselection(treeBuilder.getModelicaMLRoot());
				
				// As a job
				TreeObject[] children = treeRoot.getChildren();
				HashSet<TreeObject> invalidFirstLevelClasses = new HashSet<TreeObject>();

				for (int i = 0; i < children.length; i++) {
					TreeObject treeObject = children[i];
					
					if (treeObject instanceof ClassItem && ((ClassItem)treeObject).getClassRestriction().equals("package")) {
						// ok, do nothing
					}
					else {
						invalidFirstLevelClasses.add(treeObject);
					}
				}
				
				if (invalidFirstLevelClasses.size() > 0) {
					String title = "ModelicaML Proxies Synchronization Error";
					String message = "To use the ModelicaML Proxies Synchronization feature all first level Modelica classes " +
							"must be packages. Moreover, these packages must not have extends relations " +
							" to other packages and not be encapsulated, partial, final or replaceable. " +
							"The following first level classes cannot be synchronized: \n\n";
					
					String invalideClassesString = "";
					for (TreeObject invalideClass : invalidFirstLevelClasses) {
						invalideClassesString = invalideClassesString  + "             -" + invalideClass.getQName() + "\n";
					}
					message = message + invalideClassesString;
					MessageDialog.openError(getSite().getShell(), title, message);
				}

				// Set all project- and model-related data
				configureTreeBuilder();
				
				// refresh in order to update the proxies (in case there were deleted in the mean time)
				actionRefreshAndValidate.run();

				if (serviceRegistry != null && editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
										
					if (createProxiesAfterLoadingModelicaClasses) {
						// no dialog. use default values
						
//							// default values
//							boolean applyProxyStereotype = true;
//							boolean update = true;
//							boolean deleteNotUsedProxies = false;
						
						Job job = createSynchJob(
//								serviceRegistry, 
//								editingDomain, 
//								umlModel, 
//								ModelicaMLRoot, 
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
						
//							UIJob UIjob = new UIJob("Create ModelicaML Proxies ...") {
//							
//							public IStatus runInUIThread(IProgressMonitor monitor) {
//								ModelicaMLElementsCreator ec = new ModelicaMLElementsCreator(serviceRegistry, editingDomain, umlModel, ModelicaMLRoot);
//								TreeObject[] modelicaRoots = treeRoot.getChildren();
//								for (int i = 0; i < modelicaRoots.length; i++) {
//									
//									TreeObject treeObject = modelicaRoots[i];
//									
//									EObject modelicaRoot = ec.createProxyRoot(treeObject.getName());
//									ec.createClasses((Element)modelicaRoot, (TreeParent)treeObject, false);
//								}
//								return Status.OK_STATUS;
//							}
//							
//							
//						};
//						UIjob.setUser(true);
//						UIjob.schedule();
					}
			}
		};
		actionSynchronize.setText("Synchronize All");
		actionSynchronize.setToolTipText("Synchronize All");
//		actionSynchronize.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
//		actionSynchronize.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/compare.png"));
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
//		actionLoadSubTree.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/load.png"));
//		actionLoadSubTree.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/import_obj.gif"));
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
//		actionSynchronizeSubTree.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/compare.png"));
		actionSynchronizeSubTree.setImageDescriptor(ImageDescriptor.createFromFile(Activator.class, "/icons/copy_edit.gif"));
		
		
		
		actionLocateInPapyrusModelExplorer = new Action("actionLocateInPapyrusModelExplorer") {
			public void run() {
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Constants.VIEW_PAPYRUS_MODELEXPLORER);

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
							items.add(modelExplorerPageBookView.findElementForEObject( modelExplorerView, (EObject)object));
							
							modelExplorerView.getControl().setFocus();
							modelExplorerView.setSelection(new StructuredSelection(items), true);
						}
					}
				}
			}
		};
		actionLocateInPapyrusModelExplorer.setText("Locate in Papyrus");
		actionLocateInPapyrusModelExplorer.setToolTipText("Locate in Papyrus Model Explorer");
		actionLocateInPapyrusModelExplorer.setImageDescriptor(ImageDescriptor.createFromImage(ResourceManager.getPluginImage("org.eclipse.papyrus.modelexplorer", "/icons/ModelExplorer.gif")));
		
		
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
		actionGenerateOMCMarkers.setText("Show OMC Errors");
		actionGenerateOMCMarkers.setToolTipText("Show OMC Errors");
		// set default 
		actionGenerateOMCMarkers.setChecked(true);
		treeBuilder.setCreateOMCMarker(actionGenerateOMCMarkers.isChecked());
		

		
		
		
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
		actionValidateProxies.setText("Mark Inconsistent ModelicaML Proxies");
		actionValidateProxies.setToolTipText("Mark Redundant Proxies");
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
		actionDecorateTreeItems.setText("Decorate Items (disable it for large models)");
		actionDecorateTreeItems.setToolTipText("Decorate Items (disable it for large models)");
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
	        				
	        				MessageDialog.openError(getSite().getShell(), "ModelicaML <-> Modelica Models Synchronization Error", "Could not complete the loading of ModelicaML Proxies.");
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
					 * * TODO: check if it is safe to rely on the fact that the ModelicaML model is set or not set in the tree builder?!  
					 */
					if (treeBuilder.getModelicaMLModel() == null) {
						
						// Set all project- and model-related data
						configureTreeBuilder();
						
						collectProxiesJob = new Job("Collecting ModelicaML Proxies") {
							
							@Override
							protected IStatus run(IProgressMonitor monitor) {
								treeBuilder.collectModelicaModelProxies();
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
					if (!viewer.getExpandedState(obj) && obj instanceof TreeParent) {
						final TreeParent parent = (TreeParent) obj;
						
						// If there are no children yet -> create children
						if (!parent.hasChildren()) {
							
							if (parent instanceof ClassItem || parent.getName().equals(Constants.folderName_code_sync)) {

								
								Job expandJob = new Job("Expanding '"+parent.getQName()+" ' ...") {
									
									@Override
									protected IStatus run(IProgressMonitor monitor) {

										// create nested classes nodes
//										ArrayList<TreeObject> createdNestedClasses = treeBuilder.createClassNodes(parent, parent.getQName(), false);
										treeBuilder.createClassNodes(parent, parent.getQName(), false);
									
										// create components and extends relations nodes.
//										ArrayList<TreeObject> createdClassElements = treeBuilder.createComponentNodes(parent, false);
										treeBuilder.createClassElementNodes(parent, false);

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
						treeBuilder);

				Element UMLParent = null;

				// create or update parents and the selected class
				List<TreeObject> list = getTopDownPathItems(treeObject);
				
				for (TreeObject topDownParent : list) {
					// only create classes
					if (topDownParent instanceof ClassItem) {
						
						// if it is a root -> get or create the model root
						if (topDownParent.getParent().getName().equals(Constants.folderName_code_sync)) {
							UMLParent = (Element) ec.createProxyRoot(topDownParent.getName(), applyProxyStereotype);

							topDownParent.setModelicaMLProxy(UMLParent);
							treeBuilder.addProxyToMaps((NamedElement) UMLParent);
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
				if (treeObject instanceof ClassItem) {
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
					
					treeBuilder.deleteProxyValidationMarkers(getProject(), treeObject.getQName());
				}
				
				/*
				 * Validate the sub-tree proxies after sync. 
				 */
				IProject project = getProject();
				if (project != null && treeObject.getModelicaMLProxy() != null) {
					treeBuilder.validateProxies(project, (TreeParent)treeObject);
//					System.err.println("Validating sub-tree starting with " + parent.getName());
				}
				else {
					MessageDialog.openError(getSite().getShell(), "Modelica Models Sync. Error", "Could not validate the sub-tree starting with '" + treeObject.getName() + "'" );
				}
				
				
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
//						System.err.println("Validating sub-tree starting with " + parent.getName());
					}
					else{
//						System.err.println("Could not validate the sub-tree starting with " + parent.getName());
					}
					
					return Status.OK_STATUS;
				}
			};
			
			return job;
		}

	private void synchronizeSubTree(TreeObject treeObject 
//			boolean update, 
//			boolean applyProxyStereotype,
//			boolean deleteInconsistentProxies
			) {
		
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
		PapyrusServices.locateWithReselection(treeBuilder.getModelicaMLRoot());
		
		// start sync
		if (umlModel != null) {
			if (ModelicaMLRoot != null && serviceRegistry != null && editingDomain != null) {
				
				// Sync sub tree job listener
				final JobChangeAdapter syncSubTreeJobChangeAdapter = new JobChangeAdapter() {
					public void done(IJobChangeEvent event) {
			            if (event.getResult().isOK()) {
			            	Display.getDefault().asyncExec(new Runnable() {
			        			public void run() {
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
				try {
					serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
					editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
					
					if (serviceRegistry != null && editingDomain != null && ModelicaMLRoot != null && umlModel != null) {
						
						// set the uml model to encapsulate the job required data
						treeBuilder.setModelicaMLModel(umlModel);
						treeBuilder.setModelicaMLRoot(ModelicaMLRoot);
						
//						IWorkspace workspace = ResourcesPlugin.getWorkspace();
//						IWorkspaceRoot root = workspace.getRoot();
//						IProject iProject = root.getProject(projectName);
//						treeBuilder.deleteOMCMarkers(iProject);
        				
					}
					else {
						MessageDialog.openError(getSite().getShell(), "Loading error", 
								"Could not access the Papyrus editing domain and the uml model.");
					}
					
				} catch (ServiceException e) {
					MessageDialog.openError(getSite().getShell(), 
							"ModelicaML Model Editing Domain Access Error", 
						"Cannot acceess the ModelicaML model or its editing domain. " +
						"Please make sure that the ModelicaML model is open in the active editor.");

					e.printStackTrace();
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
	
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(), "Modelica Models Synchronization", message);
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

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}