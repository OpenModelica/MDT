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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.view.valuebindings.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.ElementSelectionDialog;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeParent;
import org.openmodelica.modelicaml.view.valuebindings.model.TreeUtls;


public class DropListener extends ViewerDropAdapter {
	
	//	public static final String ATT_CLASS = "class";//$NON-NLS-1$
	private final TreeViewer viewer;
	
	public DropListener(TreeViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	
	@Override
	public void drop(DropTargetEvent event) {
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
//		System.err.println("performDrop -> selection: " + selection);

		// get selected objects
		List<Object> selectionList = new ArrayList<Object>();
		if (!selection.isEmpty()) {
			if (selection instanceof IStructuredSelection) {
				Iterator<?> iterator = ((IStructuredSelection) selection).iterator();
				while (iterator.hasNext()) {
					Object obj = iterator.next();
					selectionList.add(obj);
				}
			}
		}
		
		// get data for binding tree 
		org.openmodelica.modelicaml.view.valuebindings.model.TreeObject bindingTreeObject = null;
		if(event.item.getData() instanceof org.openmodelica.modelicaml.view.valuebindings.model.TreeObject) {
			bindingTreeObject = (org.openmodelica.modelicaml.view.valuebindings.model.TreeObject) event.item.getData();
		}
		org.openmodelica.modelicaml.view.valuebindings.model.TreeObject valueMediatorTreeItem = TreeUtls.getNearestMediator( (org.openmodelica.modelicaml.view.valuebindings.model.TreeObject) bindingTreeObject);
		
		List<String> listOfAllowedMetaClassesNames = new ArrayList<String>();
		listOfAllowedMetaClassesNames.add("Property");
		String title = "";
		String message = "";
		int mode = -1; // means it is not set.
		Object preSelectedElement = null;
		
		for (Object object : selectionList) {
			if (object instanceof TreeObject) {
				TreeObject instantiationTreeObject = (TreeObject)object;
				if (valueMediatorTreeItem instanceof TreeParent) {
					preSelectedElement = instantiationTreeObject;
				}
			}
			else if (object instanceof IAdaptable) {
				Object eObject = ((IAdaptable)object).getAdapter(EObject.class);
				if (eObject instanceof NamedElement) {
					preSelectedElement = eObject;
				}
			}
			
			// open dialog if the object can be preselected.
			if ( preSelectedElement != null ) {
				
				if (bindingTreeObject != null && bindingTreeObject.isValueClientsNode()) {
					title = "Value Client Selection";
					message = "Click on a model element to be associated as Value Client to the Value Mediator."; // '" + ((TreeParent)selectedTreeParent).getName() + "'.";
					mode = Constants.MODE_ADD_CLIENT;
				}
				else if (bindingTreeObject != null && bindingTreeObject.isValueProvidersNode()) {
					title = "Value Provider Selection";
					message = "Click on a model element to be associated as Value Provider to the Value Mediator."; // '" + ((TreeParent)selectedTreeParent).getName() + "'."
					mode = Constants.MODE_ADD_PROVIDER;
				}
				
				
				// get the shell
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
				
				ElementSelectionDialog dialog = new ElementSelectionDialog(
						//shell, // don't do this because it will block the window underneath
						new Shell(),
						SWTResourceManager.getImage(ElementSelectionDialog.class,"/icons/selectOnly.png"), 
						title, 
						message, 
						listOfAllowedMetaClassesNames,
						(TreeParent)valueMediatorTreeItem,
						viewer,
						mode, 
						preSelectedElement, 
						null);
				
				dialog.open();
			}
		}
	}


	
	
	@Override
	public void dragOver(DropTargetEvent event) {
		// check item of the event is not null (Bug on scrollbars)
		if(event.item == null) {
			return;
		}
		// if the cursor is over clients or providers node -> allow
		if(event.item.getData() instanceof org.openmodelica.modelicaml.view.valuebindings.model.TreeObject) {
			org.openmodelica.modelicaml.view.valuebindings.model.TreeObject treeObject = (org.openmodelica.modelicaml.view.valuebindings.model.TreeObject) event.item.getData();
//			System.err.println("treeObject" + treeObject);
			if (!treeObject.isReadOnly() && (treeObject.isValueClientsNode() || treeObject.isValueProvidersNode() ) ) {
				event.detail = DND.DROP_MOVE;
			}
			else {
				event.detail = DND.DROP_NONE;	
			}
		} 
		//Defines the event feedback: auto-expand, select, auto-scroll
		event.feedback=DND.FEEDBACK_EXPAND|DND.FEEDBACK_SELECT|DND.FEEDBACK_SCROLL;
	}

	
	
	
	@Override
	public boolean performDrop(Object data) {
		return false;
	}

	
	@Override
	public void dropAccept(DropTargetEvent event) {
	}

	
	@Override
	public boolean validateDrop(Object target, int operation, TransferData transferType) {
		return true;
	}

	
	
	
	
	
	
	
	
	
//	// This method performs the actual drop
//	// We simply add the String we receive to the model and trigger a refresh of the 
//	// viewer by calling its setInput method.
//	@Override
//	public boolean performDrop(Object data) {
//		
//		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
//		System.err.println("performDrop -> selection: " + selection);

////		HashSet<Object> list = TreeUtls.findTreeItems(data.toString(), TreeUtls.componentsTreeRoot, new HashSet<Object>());
////		for (Object object : list) {
////			if (object instanceof TreeObject) {
////				TreeObject treeObject = (TreeObject)object;
////				System.err.println( ((NamedElement)treeObject.getUmlElement()).getQualifiedName());
////			}
////		}
////		ContentProviderTree.INSTANCE.getModel().add( data.toString());
////		viewer.setInput(ContentProviderTree.INSTANCE.getModel());
//		return false;
//	}
	
	
	
	
//	 /**
//	  * Loads the class that will perform the action associated with the given drop
//	  * data.
//	  *
//	  */
//	protected static IDropActionDelegate getPluginAdapter( PluginTransferData data) throws CoreException {
//		IExtensionRegistry registry = Platform.getExtensionRegistry();
//		String adapterName = data.getExtensionId();
//		IExtensionPoint xpt = registry.getExtensionPoint(PlatformUI.PLUGIN_ID,
//		IWorkbenchRegistryConstants.PL_DROP_ACTIONS);
//		IExtension[] extensions = xpt.getExtensions();
//		for (int i = 0; i < extensions.length; i++) {
//			IConfigurationElement[] configs = extensions[i].getConfigurationElements();
//			if (configs != null && configs.length > 0) {
//				String id = configs[0].getAttribute("id");//$NON-NLS-1$
//				if (id != null && id.equals(adapterName)) {
//					return (IDropActionDelegate) WorkbenchPlugin.createExtension(configs[0], ATT_CLASS);
//				}
//			}
//		}
//		return null;
//}
	
//	/* (non-Javadoc)
//	 * Method declared on DropTargetAdapter.
//	 * The user has dropped something on the desktop viewer.
//	 */
//	public void drop(DropTargetEvent event) {
//		try {
//			if (PluginTransfer.getInstance().isSupportedType(event.currentDataType)) {
//				PluginTransferData pluginData = (PluginTransferData) event.data;
//				IDropActionDelegate delegate = getPluginAdapter(pluginData);
//				if (!delegate.run(pluginData.getData(), getCurrentTarget())) {
//					event.detail = DND.DROP_NONE;
//				}
//			} else {
//				super.drop(event);
//			}
//		} catch (CoreException e) {
//			WorkbenchPlugin.log("Drop Failed", e.getStatus());//$NON-NLS-1$
//		}
//}
	
	
//	@Override
//	public void drop(DropTargetEvent event) {
//		int location = this.determineLocation(event);
//		String target = (String) determineTarget(event);
//		String translatedLocation ="";
//		switch (location){
//		case 1 :
//			translatedLocation = "Dropped before the target ";
//			break;
//		case 2 :
//			translatedLocation = "Dropped after the target ";
//			break;
//		case 3 :
//			translatedLocation = "Dropped on the target ";
//			break;
//		case 4 :
//			translatedLocation = "Dropped into nothing ";
//			break;
//		}
//		System.out.println(translatedLocation);
//		System.out.println("The drop was done on the element: " + target);
//		super.drop(event);
//	}
}
