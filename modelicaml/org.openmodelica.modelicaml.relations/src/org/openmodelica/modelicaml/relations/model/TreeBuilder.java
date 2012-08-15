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
package org.openmodelica.modelicaml.relations.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.RelationsCollector;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;


public class TreeBuilder {

	private HashSet<TreeObject> treeItems = new HashSet<TreeObject>(); // created tree clients

	private Element rootPackage;

//	private NamedElement selectedElement;
//	private Element targetPackage;
//	private Element requirementsPackage; 
//	private Element scenariosPackage; 
//	private Element valueMediatorsPackage;

	private TreeParent useToVerifyTreeRoot;
	private TreeParent doNotUseToVerifyTreeRoot;
	private TreeParent requiresTreeRoot;
	private TreeParent requiredForTreeRoot;
	
	private TreeParent importsTreeRoot;
	private TreeParent extendsTreeRoot;
	
	private TreeParent redeclareTreeRoot;
	private TreeParent constrainedByTreeRoot;
	private TreeParent partialDerivativeOfFunctionTreeRoot;
	
	private final int DECORATION_ERROR = 0;
//	private final int DECORATION_WARNING = 1;
	
	
	private UmlModel umlModel;
	
	private RelationsCollector collector;
	
	public final String NODE_TITLE_USE_TO_VERIFY = "Use to verify relations";
	public final String NODE_TITLE_DO_NOT_USE_TO_VERIFY = "Do not use to verify relations";
	public final String NODE_TITLE_REQUIRES = "Additional models (requires) relations";
	public final String NODE_TITLE_REQUIRED_FOR = "Additional models (required for) relations";
	public final String NODE_TITLE_IMPORT = "Import relations";
	public final String NODE_TITLE_INHERITANCE = "Extend relations";
	public final String NODE_TITLE_REDECLARE = "Redeclare relations";
	public final String NODE_TITLE_CONSTRAINEDBY = "Constrained by relations";
	public final String NODE_TITLE_PARTIAL_DERIVATIVE = "Partial Derivative of functions relations";
	
	public final String ITEM_NAME_POSTFIX_USE_TO_VERIFY = " (can be used to verify ...)";
	public final String ITEM_NAME_POSTFIX_DO_NOT_USE_TO_VERIFY = " (should not be used to verify ...)";
	public final String ITEM_NAME_POSTFIX_REQUIRES = " (requires ...)";
	public final String ITEM_NAME_POSTFIX_REQUIRED_FOR = " (is required for ...)";
	public final String ITEM_NAME_POSTFIX_IMPORT = " (imports ...)";
	public final String ITEM_NAME_POSTFIX_INHERITANCE = " (extends ...)";
	public final String ITEM_NAME_POSTFIX_REDECLARE =  " (redeclares ...)";
	public final String ITEM_NAME_POSTFIX_CONSTRAINEDBY = " ( is constrained by ...)";
	public final String ITEM_NAME_POSTFIX_PARTIAL_DERIVATIVE = " (is partial derivative of function ...";

	private TreeParent treeRoot;
	
	
	public void initialize(){
		
		// get the uml model that is open in Papyrus.
		umlModel = UmlUtils.getUmlModel();
		
		if (umlModel != null ) {
			try {
				
				Element root = (Element) umlModel.lookupRoot();
				
				setRootPackage(root);
				
//				targetPackage = root;
//				requirementsPackage = root;
//				scenariosPackage = root;
//				valueMediatorsPackage = root;
				
			} catch (NotFoundException e) {
				e.printStackTrace();
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
						"Packages Selection", 
						"Cannot access the root model in editor. Please open die model in editor and try it again.");
			}
		}
		
		if (getRootPackage() != null) {
			
			collectData();
			
			// create a tree root in order to show the UML::Model name
			treeRoot = new TreeParent("Relations found in " + ModelicaMLServices.getName(rootPackage));
			treeRoot.setUmlElement(rootPackage);
			treeRoot.setRoot(true);
			
				
			useToVerifyTreeRoot = new TreeParent(NODE_TITLE_USE_TO_VERIFY);
			useToVerifyTreeRoot.setDependeciesNode(true);
			treeItems.add(useToVerifyTreeRoot);
			
			// build tree 
			createRelationsTree(useToVerifyTreeRoot, getCollector().getUseToVerifyDependencies(), ITEM_NAME_POSTFIX_USE_TO_VERIFY );

			
			doNotUseToVerifyTreeRoot = new TreeParent(NODE_TITLE_DO_NOT_USE_TO_VERIFY);
			doNotUseToVerifyTreeRoot.setDependeciesNode(true);
			treeItems.add(doNotUseToVerifyTreeRoot);
			
			// build tree 
			createRelationsTree(doNotUseToVerifyTreeRoot, getCollector().getDoNotUseToVerifyDependencies(), ITEM_NAME_POSTFIX_DO_NOT_USE_TO_VERIFY );

			
			requiresTreeRoot= new TreeParent(NODE_TITLE_REQUIRES);
			requiresTreeRoot.setDependeciesNode(true);
			treeItems.add(requiresTreeRoot);
			
			// build tree 
			createRelationsTree(requiresTreeRoot, getCollector().getRequiresDependencies(), ITEM_NAME_POSTFIX_REQUIRES );

			
			requiredForTreeRoot = new TreeParent(NODE_TITLE_REQUIRED_FOR);
			requiredForTreeRoot.setDependeciesNode(true);
			treeItems.add(requiredForTreeRoot);
			
			// build tree 
			createRelationsTree(requiredForTreeRoot, getCollector().getRequiredForDependencies(), ITEM_NAME_POSTFIX_REQUIRED_FOR );
			
			
			importsTreeRoot = new TreeParent(NODE_TITLE_IMPORT);
			importsTreeRoot.setDependeciesNode(true);
			treeItems.add(importsTreeRoot);
			
			// build tree 
			createRelationsTree(importsTreeRoot, getCollector().getImportDependencies(), ITEM_NAME_POSTFIX_IMPORT);
			

			extendsTreeRoot = new TreeParent(NODE_TITLE_INHERITANCE);
			extendsTreeRoot.setInheritanceNode(true);
			treeItems.add(extendsTreeRoot);
			
			// build tree 
			createRelationsTree(extendsTreeRoot, getCollector().getInheritanceRelations(), ITEM_NAME_POSTFIX_INHERITANCE);
			
			
			redeclareTreeRoot = new TreeParent(NODE_TITLE_REDECLARE);
			redeclareTreeRoot.setDependeciesNode(true);
			treeItems.add(redeclareTreeRoot);
			
			// build tree 
			createRelationsTree(redeclareTreeRoot, getCollector().getRedeclareDependencies(), ITEM_NAME_POSTFIX_REDECLARE);
			
		
			constrainedByTreeRoot = new TreeParent(NODE_TITLE_CONSTRAINEDBY);
			constrainedByTreeRoot.setDependeciesNode(true);
			treeItems.add(constrainedByTreeRoot);
			
			// build tree 
			createRelationsTree(constrainedByTreeRoot, getCollector().getConstrainedByDependencies(), ITEM_NAME_POSTFIX_CONSTRAINEDBY);
			
			partialDerivativeOfFunctionTreeRoot = new TreeParent(NODE_TITLE_PARTIAL_DERIVATIVE);
			partialDerivativeOfFunctionTreeRoot.setDependeciesNode(true);
			treeItems.add(partialDerivativeOfFunctionTreeRoot);
			
			// build tree 
			createRelationsTree(partialDerivativeOfFunctionTreeRoot, getCollector().getPartialDerivativeOfFunctionDependencies(), ITEM_NAME_POSTFIX_PARTIAL_DERIVATIVE);
		}
	}

	
	public void showTree(TreeParent parent){
			
		// remove old trees
		TreeObject[] children = parent.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject treeObject = children[i];
			parent.removeChild(treeObject);
		}

		// show new trees
		parent.addChild(getTreeRoot());
		
		if (getUseToVerifyTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getUseToVerifyTreeRoot());
		}
		if (getDoNotuseToVerifyTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getDoNotuseToVerifyTreeRoot());
		}
		if (getRequiresTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getRequiresTreeRoot());
		}
		if (getRequiredForTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getRequiredForTreeRoot());
		}
		if (getImportsTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getImportsTreeRoot());
		}
		if (getExtendsTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getExtendsTreeRoot());
		}
		if (getRedeclareTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getRedeclareTreeRoot());
		}
		if (getConstrainedByTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getConstrainedByTreeRoot());
		}
		if (getPartialDerivativeOfFunctionTreeRoot().hasChildren()) {
			getTreeRoot().addChild(getPartialDerivativeOfFunctionTreeRoot());
		}
	}
	
	
	private void collectData(){
		
		// clear all lists from previous iterations
		clearAll();
		
		ProgressMonitorDialog progressMonitor = new ProgressMonitorDialog(ModelicaMLServices.getShell());
		
		try {
			progressMonitor.run(false, true, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {

					monitor.beginTask("Collecting ModelicaML Relations Data ...", 100);
					
					collector = new RelationsCollector();
					collector.collectElementsFromModel(getRootPackage());
				}
			});
		} catch (InvocationTargetException e) {
			MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Relations Data Collection Error", "Could not invoce the data collection for ModelicaML Relations view. ");
		} catch (InterruptedException e) {
			MessageDialog.openInformation(ModelicaMLServices.getShell(), "ModelicaML Relations Data Collection", "Data collection was interrupted.");		}
	}
	

	private Element getSource(Element relation){
		if (relation instanceof Dependency) {
			if (((Dependency)relation).getClients().size() > 0 ) {
				// NOTE: we always take the first because we do not use Dependency Sets in ModelicaML
				return ((Dependency)relation).getClients().get(0);
			}
		}
		else if (relation instanceof Generalization) {
			if (((Generalization)relation).getSources().size() > 0 ) {
				return ((Generalization)relation).getSources().get(0);
			}
		}
		return null;
	}
	
	private Element getTarget(Element relation){
		if (relation instanceof Dependency) {
			if (((Dependency)relation).getTargets().size() > 0 ) {
				// NOTE: we take the first
				return ((Dependency)relation).getTargets().get(0);
			}
		}
		else if (relation instanceof Generalization) {
			if (((Generalization)relation).getTargets().size() > 0 ) {
				// NOTE: we take the first
				return ((Generalization)relation).getTargets().get(0);
			}
		}
		return null;
	}
	
	
	
	private void createRelationsTree(TreeParent parent, HashSet<Element> relations, String sourceItemNamePostFix){
		for (Element relation : relations) {

			Element source = getSource(relation);
			Element target= getTarget(relation);
			
			// parent node to create children for
			TreeParent parentNode = parent;
			// in case a relation has no source or error we create an extra error node which becomes parent
			TreeParent errorNode = null;
			
			boolean inValidRelation = source == null || target == null;
			
			if (inValidRelation) {
				String errorPostFix = "";
				if (source == null) {
					errorPostFix += " (has no source)"; 
				}
				if (target == null) {
					errorPostFix += " (has no target)";
				}
				
				errorNode = new TreeParent(getName(relation) + errorPostFix);
				errorNode.setHasErrors(true);
				errorNode.setDependeciesNode(true);
				if (relation instanceof Dependency) {
					errorNode.setDependencyElement((Dependency) relation);	
				}
				else if (relation instanceof Generalization) {
					errorNode.setGeneralzationElement((Generalization) relation);
				}
				
				parentNode.addChild(errorNode);
			}
			
			// set the parent to be the error node if it was created
			if (inValidRelation) {
				parentNode = errorNode;
			}
			
			// get the source of the relation
			String namePrefixForSubNodes = "";
			
			/*
			 * In case of an invalid relation we add relation "source" or "target" to sub nodes names.
			 */
			if (inValidRelation) {
				namePrefixForSubNodes = "Source-> ";
			}

			// create new item
			TreeParent sourceItem = new TreeParent(namePrefixForSubNodes + getName(source) + sourceItemNamePostFix);
			sourceItem.setUmlElement(source);
			treeItems.add(sourceItem);
			
			// add to parent
			parentNode.addChild(sourceItem);
			
			// create target of the relation
			if (inValidRelation) {
				namePrefixForSubNodes = "Target-> ";
			}

			// create new item
			TreeParent targetItem = new TreeParent(namePrefixForSubNodes + getName(target));
			targetItem.setUmlElement(target);
			treeItems.add(targetItem);
			
			// set the dependency element
			if (relation instanceof Dependency) {
				targetItem.setDependencyElement((Dependency) relation);	
			}
			else if (relation instanceof Generalization) {
				targetItem.setGeneralzationElement((Generalization) relation);
			}
			
			// add to parent
			if (inValidRelation) {
				errorNode.addChild(targetItem);
			}
			else {
				sourceItem.addChild(targetItem);
			}
			
			// propagate errors
			if (inValidRelation) {
				propagateError(errorNode, DECORATION_ERROR);
			}
		}
	}
	
	

	
	
	private void propagateError(TreeObject treeObject, int decoration){
		treeObject.setHasErrors(true);
		
		TreeParent parent = treeObject.getParent(); 
		if ( parent != null) {
			propagateError(parent, decoration);
		}
	}
	
	private String getName(Element element) {
		if (element!= null && element.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null) {
			return ModelicaMLServices.getRequirementID(element) + " - " + ModelicaMLServices.getName(element);
		}
		return ModelicaMLServices.getName(element);
	}
	

	// ******************************************************************************************************************************
	
	public void clearAll(){
		treeItems.clear();
	}
	
	public List<TreeObject> findTreeItems(Object[] viewerSelection){

		List<TreeObject> foundTreeItems = new ArrayList<TreeObject>();
		
		// to avoid concurrent modifications
		ArrayList<TreeObject> items = new ArrayList<TreeObject>();
		items.addAll(getTreeItems());
		
		for (TreeObject treeItem: items) {
			
			for (Object object : viewerSelection) {
				
				if (object instanceof TreeObject) {
					Element umlElement1 = treeItem.getUmlElement();
					Element umlElement2 = ((TreeObject)object).getUmlElement();
					if (umlElement1!= null && umlElement2!=null && umlElement1.equals(umlElement2)) {
						foundTreeItems.add(treeItem);
					}
					/*
					 * The code below only works if the equal and hashCode are overridden ...
					 */
//					if ( ((TreeObject)object).equals(treeItem)) {
//						foundTreeItems.add(treeItem);
//					}
				}
				// structured selection, i.e. objects from other views
				else if (adaptSelectedElement(object) != null) {
					EObject eObj = adaptSelectedElement(object);
					if ( ((TreeObject)treeItem).getUmlElement() != null && ((TreeObject)treeItem).getUmlElement().equals(eObj)) {
						foundTreeItems.add(treeItem);
					}
				}
			}
		}
		return foundTreeItems;
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
	
	//***********************************
	
	
	public HashSet<TreeObject> getTreeItems() {
		return treeItems;
	}
	
	
	public void setUmlModel(UmlModel umlModel) {
		this.umlModel = umlModel;
	}



	public UmlModel getUmlModel() {
		return umlModel;
	}


	public Element getRootPackage() {
		return rootPackage;
	}


	public void setRootPackage(Element rootPackage) {
		this.rootPackage = rootPackage;
	}


	public TreeParent getUseToVerifyTreeRoot() {
		return useToVerifyTreeRoot;
	}


	public void setUseToVerifyTreeRoot(TreeParent useToVerifyTreeRoot) {
		this.useToVerifyTreeRoot = useToVerifyTreeRoot;
	}


	public TreeParent getDoNotuseToVerifyTreeRoot() {
		return doNotUseToVerifyTreeRoot;
	}


	public void setDoNotuseToVerifyTreeRoot(TreeParent doNotuseToVerifyTreeRoot) {
		this.doNotUseToVerifyTreeRoot = doNotuseToVerifyTreeRoot;
	}


	public TreeParent getRequiresTreeRoot() {
		return requiresTreeRoot;
	}


	public void setRequiresTreeRoot(TreeParent requiresTreeRoot) {
		this.requiresTreeRoot = requiresTreeRoot;
	}


	public TreeParent getRequiredForTreeRoot() {
		return requiredForTreeRoot;
	}


	public void setRequiredForTreeRoot(TreeParent requiredForTreeRoot) {
		this.requiredForTreeRoot = requiredForTreeRoot;
	}
	
	public RelationsCollector getCollector() {
		return collector;
	}


	public void setCollector(RelationsCollector collector) {
		this.collector = collector;
	}


	public TreeParent getTreeRoot() {
		return treeRoot;
	}


	public void setTreeRoot(TreeParent treeRoot) {
		this.treeRoot = treeRoot;
	}


	public TreeParent getImportsTreeRoot() {
		return importsTreeRoot;
	}


	public void setImportsTreeRoot(TreeParent importsTreeRoot) {
		this.importsTreeRoot = importsTreeRoot;
	}


	public TreeParent getExtendsTreeRoot() {
		return extendsTreeRoot;
	}


	public void setExtendsTreeRoot(TreeParent extendsTreeRoot) {
		this.extendsTreeRoot = extendsTreeRoot;
	}


	public TreeParent getRedeclareTreeRoot() {
		return redeclareTreeRoot;
	}


	public void setRedeclareTreeRoot(TreeParent redeclareTreeRoot) {
		this.redeclareTreeRoot = redeclareTreeRoot;
	}


	public TreeParent getConstrainedByTreeRoot() {
		return constrainedByTreeRoot;
	}


	public void setConstrainedByTreeRoot(TreeParent constrainedByTreeRoot) {
		this.constrainedByTreeRoot = constrainedByTreeRoot;
	}


	public TreeParent getPartialDerivativeOfFunctionTreeRoot() {
		return partialDerivativeOfFunctionTreeRoot;
	}


	public void setPartialDerivativeOfFunctionTreeRoot(
			TreeParent partialDerivativeOfFunctionTreeRoot) {
		this.partialDerivativeOfFunctionTreeRoot = partialDerivativeOfFunctionTreeRoot;
	}

}

