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
package org.openmodelica.modelicaml.modelica.importer.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.modelica.importer.helper.ModelicaModelProxiesCollector;
import org.openmodelica.modelicaml.modelica.importer.helper.StringHandler;

public class TreeBuilder implements IRunnableWithProgress{

	private EList<TreeObject> treeItems = new BasicEList<TreeObject>(); // created tree clients

	// Model that is currently open in Papyrus
	private UmlModel ModelicaMLModel = null;
	private EObject ModelicaMLRoot = null;

	private OpenModelicaCompilerCommunication omcc;
	
	private HashSet<Element> proxies = new HashSet<Element>();
	
	private HashSet<String> proxyQNames = new HashSet<String>();
	private HashMap<String,Element> proxyQNameToElement = new HashMap<String, Element>();

	private HashSet<String> modelsToBeExcluded = new HashSet<String>();
	
	private boolean createOMCMarker = false;
	private boolean validateProxies = false;
	private boolean fullImport = false; // indicates of annotations, equations and imports should be included in synchronization
	
	class ModelicaComponentData {
		private String typeQName;
		private Element type;
		private String name;
		private String comment; 
		private String visibility;
		private boolean isFinal;
		private boolean isFlow;
		private boolean isStream;
		private boolean isReplaceable;
		private String variability;
		private String innerouter;
		private String causality;
		private EList<String> arraySize;
		
	}

	public TreeBuilder(){
		// create a new omc proxy object
		omcc = new OpenModelicaCompilerCommunication();
	}
	
	public void buildTree(TreeParent treeRoot, ArrayList<String> excludeModels){

		clearAll();
		
//		// create a new omc proxy object
//		omcc = new OpenModelicaCompilerCommunication();
		
		loadModels();
		
		// look for model proxies in the current ModelicaML model.
		collectModelicaModelProxies();
		
		// models/libraries to be excluded
		if (excludeModels != null) {
			modelsToBeExcluded.addAll(excludeModels);
			
		}
		
		// create class nodes
		createClassNodes(treeRoot, "", true);
		
		// create component nodes
		TreeObject[] children = treeRoot.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject treeObject = children[i];
			if (treeObject instanceof ClassItem && treeObject instanceof TreeParent) {
				// create components and extends relation nodes
				createClassElementNodes((TreeParent)treeObject, true);
			}
		}
	}
	
	
	public void clearAll(){
		treeItems.clear();
		
		proxies.clear();
		proxyQNames.clear();
		proxyQNameToElement.clear();
	}
	
	public void removeItem(TreeObject item){
		treeItems.remove(item);
		if (item.getModelicaMLProxy() != null) {
			proxies.remove(item.getModelicaMLProxy());
			proxyQNames.remove(item.getQName());
			proxyQNameToElement.remove(item.getQName());
		}
	}
	
	public void addProxyToMaps(NamedElement proxy){
		if ( proxy instanceof Element) {
			String qName = StringUtls.replaceSpecCharExceptThis(((NamedElement)proxy).getQualifiedName(), "::").replaceAll("::", ".");
			proxyQNames.add(qName);
			proxyQNameToElement.put(qName, proxy);
		}
	}
	
	public void addPredefinedTypeProxy(NamedElement proxy){
		if ( proxy instanceof Element) {
			String name = StringUtls.replaceSpecChar(((NamedElement)proxy).getName().replaceFirst("Modelica", ""));
			proxyQNames.add(name);
			proxyQNameToElement.put(name, proxy);
		}
	}
	
	public void updateTreeItemProxies(TreeParent parent){
		
		TreeObject[] children = parent.getChildren();
		
		for (TreeObject treeObject : children) {
			
			// any item (e.g. class or component)
			treeObject.setModelicaMLProxy(proxyQNameToElement.get(treeObject.getQName()));
		
			// component
			if (treeObject instanceof ComponentItem) {
				((ComponentItem)treeObject).setComponentTypeProxy(proxyQNameToElement.get(((ComponentItem)treeObject).getComponentTypeQame()));
			}
			// extends relation 
			else if (treeObject instanceof ExtendsRelationItem) {
				((ExtendsRelationItem)treeObject).setSource(proxyQNameToElement.get(((ExtendsRelationItem)treeObject).getSourceQname()));
				((ExtendsRelationItem)treeObject).setTarget(proxyQNameToElement.get(((ExtendsRelationItem)treeObject).getTargetQname()));
			}
			// import relation 
			else if (treeObject instanceof ImportRelationItem) {
				((ImportRelationItem)treeObject).setSource(proxyQNameToElement.get(((ImportRelationItem)treeObject).getSourceQname()));
				((ImportRelationItem)treeObject).setTarget(proxyQNameToElement.get(((ImportRelationItem)treeObject).getTargetQname()));
			}
			
			if (treeObject instanceof TreeParent) {
				updateTreeItemProxies((TreeParent) treeObject);
			}
		}
	}
	
	
	public ArrayList<TreeObject> createClassNodes(TreeParent treeParent, String classQName, boolean recursive){
		ArrayList<TreeObject> createdItems = new ArrayList<TreeObject>();
		if (classQName != null) {
			List<String> classes = getItems(omcc.getClassNames(classQName));
			if (classes != null) {
				for (String className : classes) {
					
					// exclude Modelica predefined functions defined in OMC
					if (!className.startsWith("'")) { 
						String qName = "";
						
						// set the qualified name
						if (classQName.equals("")) {
							qName = className;
						}
						else {
							qName = classQName + "." + className;
						}

						if ( !modelsToBeExcluded.contains(qName) ) { // take into account that some models should not be loaded

							// create tree item
							ClassItem item = new ClassItem(className);

							treeParent.addChild(item);
							treeItems.add(item);
							
							item.setQName(qName);
							item.setModelicaMLProxy(proxyQNameToElement.get(qName));
							
							// update class node
							updateClassNode(item);
							
							// add to the return list
							createdItems.add(item);
							
							if (recursive) {
								// recursive call
								createdItems.addAll(createClassNodes(item, qName, recursive));
							}
						}
					}
				}
			}
		}
		return createdItems;
	}
	
	
	public void updateClassNode(ClassItem item){
		
		// set attributes
		setClassProperties(item, item.getQName());
		
		if (isFullImport()) {
			// set annotations
			setClassAnnotation(item, item.getQName());
			
			// set behaviors (i.e. (initial) algorithms/equations )
			setClassBehavior(item, item.getQName());
			
		}
	}
	
	
	public ArrayList<TreeObject> createClassElementNodes(TreeParent treeParent, boolean recursive){

		ArrayList<TreeObject> createdItems = new ArrayList<TreeObject>();
		String classQName = treeParent.getQName();
		
		if (!classQName.equals("")) {

			if (treeParent instanceof ClassItem && ((ClassItem)treeParent).isEnumeration() ) {
				createdItems.addAll(createEnumerationLiteralNodes(treeParent));
			}
			else {
				// Create Import relations nodes
				createdItems.addAll(createImportRelationNodes(treeParent, classQName));
				
				// Create extends relation nodes
				List<String> inheritedClasses = omcc.getInheritedClasses(classQName);
				if (inheritedClasses.size() > 0) {
					for (String inheritedClassQName : inheritedClasses) {
						
						// create tree item
						ExtendsRelationItem item = new ExtendsRelationItem(inheritedClassQName);
						
						treeParent.addChild(item);
						treeItems.add(item);
						
						// set attributes
						item.setSource(getTypeElement(classQName));
						item.setSourceQname(classQName);
						item.setTarget(getTypeElement(inheritedClassQName));
						item.setTargetQname(inheritedClassQName);
						
						// Note, UML Generalization is not a NamedElement. It is not possible to set
//						item.setModelicaMLProxy(proxyQNameToElement.get(qName));
						
						// set modifications
						item.setModifications(getExtendsModifications(item, classQName));
						
						// Set arraySize
						item.setArraySize( ((ClassItem)treeParent).getArraySize() );
						
						// add to return list
						createdItems.add(item);
					}
				}
				
				ArrayList<ModelicaComponentData> components = getComponentData((ClassItem) treeParent, omcc.getComponents(classQName, "useQuotes = true"), proxyQNameToElement.get(classQName));
				
				// create components nodes
				if (components.size() > 0) {
//					for (ModelicaComponentData component : components) {
					for (int i = 0; i < components.size(); i++) {
						
						ModelicaComponentData component = components.get(i);
						
						// set the qualified name
						String qName = "";
						if (classQName.equals("")) {
							qName = component.name;
						}
						else {
							qName = classQName + "." + component.name;
						}
						
						// create tree item
						ComponentItem item = new ComponentItem (component.name);

						treeParent.addChild(item);
						treeItems.add(item);
						
						// set attributes
						item.setQName(qName);

						// set the loaded component type 
						item.setComponentTypeQName(component.typeQName);
						item.setComponentTypeTreeItem(findTreeItem(component.typeQName));

						// set component type proxy 
						item.setComponentTypeProxy(component.type);
						
						// set properties
						item.setFinal(component.isFinal);
						item.setComment(component.comment);
						item.setVisibility(component.visibility);
						item.setFinal(component.isFinal);
						item.setFlow(component.isFlow);
						item.setStream(component.isStream);
						item.setReplaceable(component.isReplaceable);
						item.setVariability(component.variability);
						item.setInnerouter(component.innerouter);
						item.setCausality(component.causality);
						item.setArraySize(component.arraySize);
						
						// Set the ModelicaML proxy for this element
						item.setModelicaMLProxy(proxyQNameToElement.get(qName));
						
						// Set component modifications
						item.setModifications(getComponentModifications(item, classQName));
						
						// Set the declaration 
						item.setDeclaration(getComponentDeclarationEquation(item, classQName));
						
						// Set the conditional expression for the component
						item.setConditionalExpression(omcc.getNthComponentCondition(classQName, i + 1));
						
						// Add to return list
						createdItems.add(item);
					}
				}
			}
			
			if (recursive) {
				// recursive call
				TreeObject[] children = treeParent.getChildren();
				for (int i = 0; i < children.length; i++) {
					TreeObject treeObject = children[i];
					if (treeObject instanceof ClassItem && treeObject instanceof TreeParent) {
						
						createdItems.addAll(createClassElementNodes((TreeParent)treeObject, recursive));
						
					}
				}
			}
		}
		return createdItems;
	}

	
	private List<TreeObject> createImportRelationNodes(TreeParent classItem, String classQName){

		List<TreeObject> createdItems = new ArrayList<TreeObject>();
		
		int n = omcc.getImportCount(classQName);
		if (n > 0) {
			for (int i = 0; i <= n; i++) {
				String reply = omcc.getNthImport(classQName, i);
				if (!reply.trim().equals("") && !reply.equals("Error") && !reply.equals("error") && !reply.equals("false")) {
					ArrayList<String> items = StringHandler.unparseStrings(reply.trim());
					if (items.size() > 2) {
						String importedElementName = items.get(0);
						String alias = items.get(1);
						String kind = items.get(2);
						
						// create tree item
						ImportRelationItem item = new ImportRelationItem(importedElementName);
						
						classItem.addChild(item);
						treeItems.add(item);
						
						// set attributes
						item.setSource(getTypeElement(classQName));
						item.setSourceQname(classQName);
						
						Element targetElement = getTypeElement(importedElementName);
//						if (targetElement == null) {
//							// get the qualified name
//							if (classItem.getParent() != null ) {
//								String nameSpace = classItem.getParent().getQName();	
//								targetElement = getTypeElement(nameSpace + "."+ importedElementName);
//							}
//						}
						
						item.setTarget(targetElement);
						item.setTargetQname(importedElementName);
						
						item.setAlias(alias);
						item.setKind(kind);
						
						// Note, UML Dependencies cannot have ModelicaML proxies because they may not have names to be used for the identification.
//						item.setModelicaMLProxy(proxyQNameToElement.get(importedElementName));
						
						// add to return list
						createdItems.add(item);
					}
				}
			}
		}
		return createdItems;
	}
	
	
	private List<TreeObject> createEnumerationLiteralNodes(TreeParent classItem){
		
		List<TreeObject> createdItems = new ArrayList<TreeObject>();
		String reply = omcc.getEnumerationLiterals(classItem.getQName());
		
		if (!reply.trim().equals("") && !reply.equals("Error") && !reply.equals("error") && !reply.equals("false")) {
			
			ArrayList<String> items = StringHandler.unparseStrings(reply.trim());
			if (items.size() > 0) {
				for (int i = 0; i < items.size(); i++) {
			
					String literalName = items.get(i);
					ComponentItem literatItem = new ComponentItem(literalName);
					
					classItem.addChild(literatItem);
					treeItems.add(literatItem);
					
					// set the qualified name
					String qName = "";
					if (classItem.getName().equals("")) {
						qName = literatItem.getName();
					}
					else {
						qName = classItem.getQName() + "." + literatItem.getName();
					}
					
					literatItem.setModelicaMLProxy(proxyQNameToElement.get(qName));
					
					// add to return list
					createdItems.add(literatItem);
				}
			}
		}
		
		return createdItems;
	}
	
	
	private boolean isBehavioralClass(ClassItem item){
		if (item.getClassRestriction().equals("class")) { return true; }
		if (item.getClassRestriction().equals("model")) { return true; }
		if (item.getClassRestriction().equals("block")) { return true; }
		if (item.getClassRestriction().contains("function")) { return true; }
		return false;
	}
	
	private void setClassBehavior(ClassItem item, String classQName){
		if (isBehavioralClass(item)) {
			List<String> intialAlgorithms = omcc.getInitialAlgorithms(classQName);
			if (intialAlgorithms.size() > 0 ) {
				item.setInitialAlgorithms(intialAlgorithms);
			}
			
			List<String> algorithms = omcc.getAlgorithms(classQName);
			if (algorithms.size() > 0 ) {
				item.setAlgorithms(algorithms);
			}
			
			List<String> intialEquations = omcc.getInitialEquations(classQName);
			if (intialEquations.size() > 0 ) {
				item.setInitialEquations(intialEquations);
			}
			
			List<String> equations = omcc.getEquations(classQName);
			if (equations.size() > 0 ) {
				item.setEquations(equations);
			}
		}
	}
	
	
	private void setClassAnnotation(ClassItem item, String classQName){
		List<String> annotations = omcc.getAnnotations(classQName);
		if (annotations.size() > 0 ) {
			item.setAnnotations(annotations);
		}
	}
	
	
	private void setClassProperties(ClassItem item, String classQName){
		
		/*
		 * 	>> getClassInformation(ModelicaMLModel.Modelica.Fluid.Utilities)
			<< {"package",
			"",
			"D:/__PROJECTS/2008_PhD/tools/eclipse_3_6_modeling/runtime-New_configuration/modelicaml.example.potableWaterSystem_v26_TEST/code-sync/ModelicaMLModel/../ModelicaMLModel/Modelica/Fluid/Utilities/package.mo",
			{false,false,false},
			{"writable",3,1,8,14},
			{}}
		 *	
		 *	Explanation: 
		 *		-> Restriction, 
		 *		-> comment,
		 *		-> filename,
		 *		-> {partial,final,encapsulated}
		 * 		? {"writable",3,1,8,14},
		 * 		-> array size
		 */
		
		String classInfo = omcc.getClassInformation(classQName);
		
		if (!classInfo.trim().equals("") && !classInfo.equals("Error") && !classInfo.trim().equals("false") ) {

			// get class data
			ArrayList<String> classData = StringHandler.unparseStrings(classInfo);

			// set class restriction
			String classRestriction = "class"; // default restriction
			if (classData.size() > 0 ) {
				classRestriction = classData.get(0); 
			}
			item.setClassRestriction(classRestriction);
			
			// set is enumeration
			String isEnumerationReply = omcc.isEnumeration(classQName);
			if (isEnumerationReply.trim().contains("true")) {
				item.setIsEnumeration(true);
			}
			
			// set is replaceable if there is parent that is a Modelica class
			TreeParent parent = item.getParent();
			if (parent instanceof ClassItem) {
				String isReplaceableReply = omcc.isReplaceable(parent.getQName(), classQName);
				if (isReplaceableReply != null && isReplaceableReply.length() > 0 && isReplaceableReply.trim().contains("true")) {
					item.setIsReplaceable(true);
				}
			}

			if (classData.size() > 2) {
				// set class array size
				String arraySizeString = StringHandler.removeFirstLastCurlBrackets(classData.get(2));
				EList<String> arraySize = new BasicEList<String>();
				String[] splited = arraySizeString.split(",");
				for (int i = 0; i < splited.length; i++) {
					String string = splited[i];
					if (!string.trim().equals("")) {
						arraySize.add(string);
					}
				}
				if (arraySize.size() > 0 ) {
					item.setArraySize(arraySize);
				}
				
				// set comment
				String comment = StringHandler.unparseStrings(classInfo).get(1);
				item.setComment(comment);
			}
			
			// set partial, final and encapsulated
			boolean isPartial = false;
			boolean isFinal = false;
			boolean isEncapsulated = false;

			// get the boolean items (i.e. true or false)
			Pattern patternBooleanItems = Pattern.compile("(true|false)");
			Matcher matcherBooleanItems = patternBooleanItems.matcher(classInfo);
			List<String> booleanItems = new ArrayList<String>();
			while (matcherBooleanItems.find()) {
				booleanItems.add(matcherBooleanItems.group());
			}
			
			if (booleanItems.size() > 2) {
				if (booleanItems.get(0).trim().equals("true")) {
					isPartial = true;
				}
				if (booleanItems.get(1).trim().equals("true")) {
					isFinal = true;
				}
				if (booleanItems.get(2).trim().equals("true")) {
					isEncapsulated = true;
				}
			}
			
			// set the tree item data
			item.setFinal(isFinal);
			item.setPartial(isPartial);
			item.setEncapsulated(isEncapsulated);
		}
		
		if (classInfo.equals("Error") ) {
			// TODO: collect errors
			String errorString = omcc.getErrorString();	
			String msg = extractErrorMessage(errorString);
			
			// generate markers
			createOMCMarker(item, "error", msg);
		}
	}
	

	
	private ArrayList<ModelicaComponentData> getComponentData(ClassItem classItem, String string, Element owningClass){
		
		/*
		 * 	>> getComponents(Modelica.StateGraph.Examples.Utilities.CompositeStep2)
			<< {{Modelica.StateGraph.Transition,
			transition,
			"", 
			"public", 
			false, 
			false, 
			false, 
			false, 
			"unspecified", 
			"none", 
			"unspecified",
			{}}
		 * 
		 	-	item[0] = type qualified name
			-	item[1] = component name
			-	item[2] = comment
			-	item[3] = visibility (public/protected)
			-	item[4] = Final (true/false)
			-	item[5] = Flow (true/false)
			-	item[6] = Stream (true/false)
			-	item[7] = Replaceable (true/false)
			-	item[8] = variability (constant/discrete/parameter/unspecified)
			-	item[9] = inner/outer/innerouter/none
			-	item[10] = causality (input/output/unspecified)
			-	item[11] = bit unsure about this value but perhaps its length of array 
		 */
		
		ArrayList<ModelicaComponentData> list = new ArrayList<TreeBuilder.ModelicaComponentData>();
		
		for (String stringFromArray : StringHandler.unparseArrays(string)) {
		
			ArrayList<String> items = StringHandler.unparseStrings(stringFromArray);
			
			if (items.size() > 10 ) {
				
				ModelicaComponentData data = new ModelicaComponentData();

				data.typeQName = items.get(0).trim();
				data.type = getTypeElement(data.typeQName);
				data.name = items.get(1).trim();
				
				data.comment =  items.get(2);
				data.visibility = items.get(3);
				
				// default values
				boolean isFinal = false;
				boolean isFlow = false;
				boolean isStream = false;
				boolean isReplaceable= false;
				
				if (items.get(4).trim().equals("true")) {
					isFinal = true;
				}
				if (items.get(5).trim().equals("true")) {
					isFlow = true;
				}
				if (items.get(6).trim().equals("true")) {
					isStream = true;
				}
				if (items.get(7).trim().equals("true")) {
					isReplaceable = true;
				}
				
				data.isFinal = isFinal;
				data.isFlow = isFlow;
				data.isStream = isStream;
				data.isReplaceable = isReplaceable;
				
				data.variability = items.get(8);
				data.innerouter = items.get(9);
				data.causality = items.get(10);
				
				String[] arraySizeItems = StringHandler.removeFirstLastCurlBrackets(items.get(11).trim()).split(",");
				if ( arraySizeItems.length > 0 ) {
					EList<String> arraySizeItemsList = new BasicEList<String>();
					for (int i = 0; i < arraySizeItems.length; i++) {
						if (!arraySizeItems[i].trim().equals("")) {
							arraySizeItemsList.add(arraySizeItems[i]);
						}
					}
					if (arraySizeItemsList.size() > 0) {
						data.arraySize = arraySizeItemsList;
					}
				}
				
				list.add(data);
			}
		}

		if (string.equals("Error")) {
			// TODO: collect errors
			String errorString = omcc.getErrorString();	
			String msg = extractErrorMessage(errorString);

			// generate markers
			createOMCMarker(classItem, "error", msg);
		}
		
		return list;
	}
	
	
	private String getComponentDeclarationEquation(ComponentItem component, String classQName){
		String declaration = null;
		if (!classQName.equals("") && component != null) {
			String declarationString = omcc.getComponentModifierValue(classQName, component.getName()).trim();
			if (!declarationString.equals("Error") && !declarationString.equals("false") && !declarationString.equals("")) {
				declaration = "= " + declarationString; 
			}
			if (declarationString.equals("Error")) {
				// TODO: collect errors
				String errorString = omcc.getErrorString();	
				String msg = extractErrorMessage(errorString);

				// generate markers
				createOMCMarker(component, "error", msg);

			}
		}
		return declaration;
	}
	
	
	
	private EList<String> getComponentModifications(ComponentItem component, String classQName){
		EList<String> modifications = new BasicEList<String>();
		if (!classQName.equals("")) {
			List<String> componentModifiers = getItems(omcc.getComponentModifierNames(classQName, component.getName()));
			
			if (componentModifiers != null && componentModifiers.size() > 0) {
				for (String modifier : componentModifiers) {
					String modificationItem = modifier + omcc.getComponentModifierValue(classQName, component.getName() + "." + modifier);
					modifications.add(modificationItem.trim());
				}
			}
		}
		return modifications;
	}
	
	private EList<String> getExtendsModifications(ExtendsRelationItem extendsRelationItem, String classQName){
		EList<String> modifications = new BasicEList<String>();
		if (!extendsRelationItem.getSourceQname().equals("") && !extendsRelationItem.getTargetQname().equals("")) {

			List<String> componentModifiers = getItems(omcc.getExtendsModifierNames(extendsRelationItem.getSourceQname(), 
					extendsRelationItem.getTargetQname()));
			
			if (componentModifiers != null && componentModifiers.size() > 0) {
				for (String modifier : componentModifiers) {
					String modificationItem = modifier + omcc.getExtendsModifierValue(extendsRelationItem.getSourceQname(), 
							extendsRelationItem.getTargetQname(), modifier);
					
					modifications.add(modificationItem.trim());
				}
			}
		}
		else {
			System.err.println("Target is not known for '" + extendsRelationItem.getSourceQname() + "'");
		}
		return modifications;
	}
	
	
	private HashSet<TreeObject> getAllTreeItems(TreeParent treeParent){
		
		HashSet<TreeObject> allTreeItems = new HashSet<TreeObject>();
		allTreeItems.add(treeParent);
		
		TreeObject[] children = treeParent.getChildren();
		for (int i = 0; i < children.length; i++) {
			allTreeItems.add(children[i]);
			if (children[i] instanceof TreeParent) {
				allTreeItems.addAll(getAllTreeItems((TreeParent)children[i]));
			}
		}
		
		return allTreeItems;
	}


	private TreeObject findTreeItem(String qName){
		TreeObject foundObject = null;
		
		// to avoid concurrent modifications
		ArrayList<TreeObject> items = new ArrayList<TreeObject>();
		items.addAll(treeItems);
		
		for (TreeObject treeObject : items) {
			if (treeObject.getQName().equals(qName)) {
				foundObject = treeObject;
			}
		}
		return foundObject;
	}


	private List<String> getItems(String string){
		List<String> items = new ArrayList<String>();
		
		if (string != null && string.trim().length() > 0 && !string.trim().equals("Error") && !string.trim().equals("false")) {
			String[] splitted = string.trim().substring(1, string.length() - 2).split(",");
			if (splitted.length > 0 ) {
				for (int i = 0; i < splitted.length; i++) {
					String item = splitted[i].trim();
					if (!item.equals("")) {
						items.add(item);
					}
				}
			}
		}
		if (string.trim().equals("Error")){
			// TODO: collect errors
			String errorString = omcc.getErrorString();	
		}
		
		if (items.size() > 0 ) {
			return items;	
		}
		else {
			return null;
		}
	}


	public Element getTypeElement(String typeQName){
		Element  type = proxyQNameToElement.get(typeQName);
		// if the type is not a predefined type
		if (type != null) {
			return type;
		}
		// if the type is predefined type -> use element to access the predefined modelicaml types in the modelicaml profile 
//		if (element != null) {
		if (ModelicaMLRoot instanceof Model) {
//		if ( getModelicamlProfile() != null ) {
			// predefined types
			if (typeQName.equals("Real") || typeQName.equals("ModelicaReal") || typeQName.equals("TypeReal")) {
//				return type = element.getModel().getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_real);
				return type = ((Model)ModelicaMLRoot).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_real);
//				return type = ((Package) getModelicamlProfile().getOwnedMember(Constants.predefinedTypesProfileQName)).getOwnedType(Constants.predefinedTypeName_real);
			}
			if (typeQName.equals("Integer") || typeQName.equals("ModelicaInteger") || typeQName.equals("TypeInteger")) {
//				return type = element.getModel().getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_integer);
				return type = ((Model)ModelicaMLRoot).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_integer);
//				return type = ((Package) getModelicamlProfile().getOwnedMember(Constants.predefinedTypesProfileQName)).getOwnedType(Constants.predefinedTypeName_integer);
			}
			if (typeQName.equals("Boolean") || typeQName.equals("ModelicaBoolean") || typeQName.equals("TypeBoolean")) {
//				return type = element.getModel().getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_boolean);
				return type = ((Model)ModelicaMLRoot).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_boolean);
//				return type = ((Package) getModelicamlProfile().getOwnedMember(Constants.predefinedTypesProfileQName)).getOwnedType(Constants.predefinedTypeName_boolean);
			}
			if (typeQName.equals("String") || typeQName.equals("ModelicaString") || typeQName.equals("TypeString")) {
//				return type = element.getModel().getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_string);
				return type = ((Model)ModelicaMLRoot).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_string);
//				return type = ((Package) getModelicamlProfile().getOwnedMember(Constants.predefinedTypesProfileQName)).getOwnedType(Constants.predefinedTypeName_string);
			}	
		}
		else {
			System.err.println("Cannot access the root model element in order to obtain the predefined Modelica types from the ModelicaML profile.");
		}
		return null;
	}
	
	
	public void loadModels(){
//		UmlModel umlModel = UmlUtils.getUmlModel();
		UmlModel umlModel = ModelicaMLModel;
		
		// clear the omc first
		omcc.clear();

		if (umlModel != null && umlModel.getResource() != null) {
			String projectName = umlModel.getResource().getURI().segment(1);
			
			// Set the folder paths 
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject iProject = root.getProject(projectName);
			String projectAbsolutePath = iProject.getLocationURI().toString().replaceFirst("file:\\/", "");
			String codeIncAbsolutePath = projectAbsolutePath+"/"+Constants.folderName_code_sync + "/";

			List<String> filesToLoad = ModelicaMLServices.getFilesToLoad(codeIncAbsolutePath);
			for (String fileToLoad : filesToLoad) {
				
				String reply = "";
				reply = omcc.loadFile(fileToLoad);
				
				if (reply.trim().equals("Error")) {
					String errorString = omcc.getErrorString();	
					String msg = extractErrorMessage(errorString);

					// generate markers
					createOMCMarker(null, "error", msg);
				}
				else if (reply.trim().equals("false")) {
					String errorString = omcc.getErrorString();	
					String msg = extractErrorMessage(errorString);
					
					// generate markers
					createOMCMarker(null, "error", msg);
				}
				
			}
		}
		else {
			System.err.println("Cannot access the Papyrus UML model");
		}
		
	}

	public void validateProxies(IProject iProject){
		if (isValidateProxies()) {
			
			// Delete all old markers
			deleteProxyValidationMarkers(iProject);
			
			// Get proxies
			HashSet<Element> collectedProxies = new HashSet<Element>();
			collectedProxies.addAll(proxies);
			
			// Get Modelica model names
			HashSet<String> modelicaModelQNames = new HashSet<String>();
			for (TreeObject treeObject : treeItems) {
				modelicaModelQNames.add(treeObject.getQName());
			}
			
			// validate
			validateProxies(collectedProxies, modelicaModelQNames);
	
		}
	}


	private void validateProxies(HashSet<Element> proxies,  HashSet<String> modelicaModelQNames){
			for (Element element : proxies) {
				if (element instanceof NamedElement) {
					Model topLevelModel = element.getModel();
					// don't check installed library elements 
					if ( topLevelModel != null  
							&& topLevelModel.getAppliedStereotype(Constants.stereotypeQName_InstalledLibrary) == null) {
						String qName = StringUtls.replaceSpecCharExceptThis(((NamedElement)element).getQualifiedName(), "::").replaceAll("::", ".");
						
						// check the proxy exists
						if (!modelicaModelQNames.contains(qName)) {
							createMarker(element, ((NamedElement)element).getQualifiedName(), "error", "Proxy '"+((NamedElement)element).getQualifiedName()
									+"' does not exist in the loaded Modelica models.");
						}
						
						// check if a property has type defined
						if (element instanceof Property) {
							if ( ((Property)element).getType() == null ) {
								createMarker(element, 
										((NamedElement)element).getQualifiedName(), 
										"error", "No type is defined for '"+((NamedElement)element).getQualifiedName()+"'.");
							}
						}
						
						// find extends relations without target
						if (element instanceof Classifier) {
							Classifier classifier = (Classifier) element;
							EList<Generalization> classExtendsRelations = classifier.getGeneralizations();
							for (Generalization generalization : classExtendsRelations) {
								
								EList<Element> targets = generalization.getTargets();
								
								if (targets != null && targets.size() > 0) {
									// ok
								}
								else {
	//									createMarker(element, ((NamedElement)element).getQualifiedName(), 
									createMarker(generalization, ((NamedElement)element).getQualifiedName(),
											"error", 
											"NOT VALID: No target in the extends relation of the class '"+((NamedElement)element).getQualifiedName()+"' has no target.");
								}
							}
						}
					}
				}
			}
		}


	public void validateProxies(IProject iProject, TreeParent treeItem){
			
		// delete all old markers
		if (treeItem.getModelicaMLProxy() instanceof NamedElement) {
			deleteProxyValidationMarkers(iProject, ((NamedElement)treeItem.getModelicaMLProxy()).getQualifiedName());
		}
		// ´Collect proxies 
		HashSet<Element> proxies = new HashSet<Element>();
		Iterator<EObject> i = treeItem.getModelicaMLProxy().eAllContents();
		while (i.hasNext()) {
			EObject object = i.next() ;
			
			// collect all imported elements 
			if ((object instanceof Class && ((Class)object).getAppliedStereotype(Constants.stereotypeQName_ModelicaModelProxy) != null) || object instanceof Property) {
				proxies.add((Element) object);
			}
		}
		
		// Get Modelica model names
		HashSet<String> modelicaModelQNames = new HashSet<String>();
		for (TreeObject treeObject : getAllTreeItems(treeItem)) {
			modelicaModelQNames.add(treeObject.getQName());
		}
	
		// validate
		validateProxies(proxies, modelicaModelQNames);
	}


	public void collectModelicaModelProxies(){
			
			proxies.clear();
			proxyQNames.clear();
			proxyQNameToElement.clear();
			
			final UmlModel umlModel = getModelicaMLModel();
			
			if (umlModel != null && umlModel.getResource() != null) {
				try {
					EObject root = umlModel.lookupRoot();
					
					setModelicaMLModel(umlModel);
					setModelicaMLRoot(root);
					
					if (root instanceof NamedElement) {
						ModelicaModelProxiesCollector pc = new ModelicaModelProxiesCollector();
						pc.collectElementsFromModel(root, Constants.stereotypeQName_ModelicaModelProxy);
	//					proxies = pc.getElements();
						proxies.addAll(pc.getElements());
						
						EList<Element> classAttributesProxies = new BasicEList<Element>();
						EList<Element> functionParametersProxies = new BasicEList<Element>();
						EList<Element> enumerationLiteralProxies = new BasicEList<Element>();
						
						// in order to avoid concurrent modifications
						HashSet<Element> proxiesCopy = new HashSet<Element>();
						proxiesCopy.addAll(proxies);
						
	//					for (Element proxy : proxies) {
						for (Element proxy : proxiesCopy) {
							if (proxy instanceof NamedElement) {
								addProxyToMaps((NamedElement)proxy);
								
								// add all class properties
								if (proxy instanceof Class) {
									EList<Property> properties = ((Class)proxy).getOwnedAttributes();
									for (Property property : properties) {
										// collect attributes
										classAttributesProxies.add(property);
										addProxyToMaps((NamedElement)property);
									}
								}
								// add function behavior paramters
								if (proxy instanceof FunctionBehavior) {
									EList<Parameter> parameters = ((FunctionBehavior)proxy).getOwnedParameters();
									for (Parameter parameter : parameters) {
										// collect attributes
										functionParametersProxies.add(parameter);
										addProxyToMaps((NamedElement)parameter);
									}
								}
								// add enumeration literals
								if (proxy instanceof Enumeration) {
									EList<EnumerationLiteral> literals = ((Enumeration)proxy).getOwnedLiterals();
//									System.err.println("Collected literals: " + literals);
									for (EnumerationLiteral literal : literals) {
										// collect literals
										enumerationLiteralProxies.add(literal);
										addProxyToMaps((NamedElement)literal);
									}
								}
							}
						}
						
						// add components to proxies list
						proxies.addAll(classAttributesProxies);
						proxies.addAll(functionParametersProxies);
						proxies.addAll(enumerationLiteralProxies);
						
						// Add Modelica predefined types
						if (root instanceof Model) {
							proxies.add(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_real));
							addPredefinedTypeProxy(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_real));
							
							proxies.add(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_integer));
							addPredefinedTypeProxy(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_integer));
							
							proxies.add(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_boolean));
							addPredefinedTypeProxy(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_boolean));
							
							proxies.add(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_string));
							addPredefinedTypeProxy(((Model)root).getAppliedProfile(Constants.predefinedTypesProfileQName).getOwnedType(Constants.predefinedTypeName_string));
						}
					}
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.err.println("Could not access the ModelicaML root element.");
			}
		}


	public void setModelicaMLModel(UmlModel modelicaMLModel) {
		ModelicaMLModel = modelicaMLModel;
		try {
			EObject modelicaMLRoot = ModelicaMLModel.lookupRoot();
			setModelicaMLRoot(modelicaMLRoot);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unSetModelicaMLModel() {
		ModelicaMLModel = null;
	}
	
	public void unModelicaMLRoot() {
		ModelicaMLRoot = null;
	}
	
	public UmlModel getModelicaMLModel() {
		return ModelicaMLModel;
	}

	public void setModelicaMLRoot(EObject modelicaMLRoot) {
		ModelicaMLRoot = modelicaMLRoot;
	}

	public EObject getModelicaMLRoot() {
		return ModelicaMLRoot;
	}

	public EList<TreeObject> getTreeItems() {
		return treeItems;
	}
	
	
	public List<TreeObject> getLoadedClasses(){
		List<TreeObject> loadedClasses = new ArrayList<TreeObject>();
		for (TreeObject treeObject : treeItems) {
			if (treeObject instanceof ClassItem) {
				loadedClasses.add(treeObject);
			}
		}
		return loadedClasses;
	}

	public List<TreeObject> getLoadedComponents(){
		List<TreeObject> loadedComponents = new ArrayList<TreeObject>();
		for (TreeObject treeObject : treeItems) {
			if (treeObject instanceof ComponentItem) {
				loadedComponents.add(treeObject);
			}
		}
		return loadedComponents;
	}

	public List<TreeObject> getLoadedExtendsRelations(){
		List<TreeObject> loadedExtendsRelation= new ArrayList<TreeObject>();
		for (TreeObject treeObject : treeItems) {
			if (treeObject instanceof ExtendsRelationItem) {
				loadedExtendsRelation.add(treeObject);
			}
		}
		return loadedExtendsRelation;
	}

	
	public HashSet<Element> getProxies() {
		return proxies;
	}
	
	
	public void setModelsToBeExcluded(HashSet<String> modelsToBeExcluded) {
		this.modelsToBeExcluded = modelsToBeExcluded;
	}

	public HashSet<String> getModelsToBeExcluded() {
		return modelsToBeExcluded;
	}

	public OpenModelicaCompilerCommunication getOmcc() {
		return omcc;
	}


	public void setCreateOMCMarker(boolean createOMCMarker) {
		this.createOMCMarker = createOMCMarker;
	}

	public boolean isCreateOMCMarker() {
		return createOMCMarker;
	}
	
	public boolean isValidateProxies() {
		return validateProxies;
	}

	public void setValidateProxies(boolean validateProxies) {
		this.validateProxies = validateProxies;
	}
	
	
	
	
	
	
	
	// Marker *****************************************
	
	private String markerType = Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES;
	
	public IMarker createMarker(Element elt, String sourceID, String criticality, String msg){
		if (elt != null) {
			IResource r = null;
			URI eUri = elt.eResource().getURI();
			
			if (eUri.isPlatformResource()) {
				String platformString = eUri.toPlatformString(true);
				r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
//				r = ResourcesPlugin.getWorkspace().getRoot().findMember(umlModel.getResource().getURI().toPlatformString(true));
			}
			try {
				
				IMarker marker = r.createMarker(markerType);
				marker.setAttribute(IMarker.MESSAGE, msg);
				if ( criticality.equals("error") ) 	{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);	}
				else 								{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) ; }
				marker.setAttribute(IMarker.SOURCE_ID, sourceID);
				
				if (elt instanceof NamedElement) {
					marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());	
				}
				else{
					marker.setAttribute(IMarker.LOCATION, elt.toString());
				}
				marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());

				return marker;
				
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private String extractErrorMessage(String fullString){
		String errorString = "";
		Pattern patternVisibility = Pattern.compile("Error:.*");
		Matcher matcherVisibility = patternVisibility.matcher(fullString);
		while (matcherVisibility.find()) {
			errorString = matcherVisibility.group(0).replaceFirst("Error:", "").trim();
		}
		return errorString;
	}
	
	
	public IMarker createOMCMarker(TreeObject treeObject, String criticality, String msg){
		if (isCreateOMCMarker() && !msg.trim().equals("") ) {
			String markerType = Constants.MARKERTYPE_MODELICA_MODELS_LOADING;
			
			if ( ModelicaMLModel != null ) {
				IResource r = null;
				URI eUri = ModelicaMLModel.getResource().getURI();
				try {
					ModelicaMLRoot = ModelicaMLModel.lookupRoot();
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (eUri.isPlatformResource()) {
					String platformString = eUri.toPlatformString(true);
					r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
				}
				try {
					IMarker marker = r.createMarker(markerType);
					marker.setAttribute(IMarker.MESSAGE, msg);
					
					if ( criticality.equals("error") ) 	{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);	}
					else 								{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) ; }
					
					if ( treeObject != null) {
						marker.setAttribute(IMarker.SOURCE_ID, treeObject.getQName());
						marker.setAttribute(IMarker.LOCATION, treeObject.getQName());	
					}
					return marker;
					
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	public void deleteProxyValidationMarkers(IProject iProject, String namespace) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				List<String> markerTypes = new ArrayList<String>();
				markerTypes.add(Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES);
				
				for (String markerType : markerTypes) {
					markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
					for (IMarker marker : markers) {
						if ( namespace!= null && !namespace.trim().equals("") && marker.getAttribute(IMarker.LOCATION, "").startsWith(namespace) ) {
							marker.delete();
						}
					}
				}
			}
		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	public void deleteProxyValidationMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				List<String> markerTypes = new ArrayList<String>();
				markerTypes.add(Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES);
				
				for (String markerType : markerTypes) {
					markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
					for (IMarker marker : markers) {
						marker.delete();
					}
				}
			}
		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	public void deleteOMCMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				List<String> markerTypes = new ArrayList<String>();
				markerTypes.add(Constants.MARKERTYPE_MODELICA_MODELS_LOADING);
				
				for (String markerType : markerTypes) {
					markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
					for (IMarker marker : markers) {
						marker.delete();
					}
				}
			}
		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}


	
	
	
	
	

	// Progress monitor 
	
	// The total sleep time
	private static final int TOTAL_TIME = 3000;
	// The increment sleep time
	private static final int INCREMENT = 10;
	// process time´is unknown
	private boolean indeterminate = true; 
	
	private String progressMonitorTitle = "Loading Modelica Models using OMC API";
	private String monitorText1 = "Establishing connection to OMC...";
	private String monitorText2 = "Quering OMC...";
	private String monitorText3 = "Preparing tree visualization ...";
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		
		monitor.beginTask(progressMonitorTitle + " is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
	    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
	      Thread.sleep(INCREMENT);
	      monitor.worked(INCREMENT);
	      if (total == TOTAL_TIME / 100) monitor.subTask(monitorText1);
	      if (total == TOTAL_TIME / 4) monitor.subTask(monitorText2);
	      if (total == TOTAL_TIME / 2) monitor.subTask(monitorText3);
	    }
	    monitor.done();
	    if (monitor.isCanceled()){
	    	throw new InterruptedException(progressMonitorTitle + " was cancelled.");
	    }   
	}


	public void setFullImport(boolean fullImport) {
		this.fullImport = fullImport;
	}


	public boolean isFullImport() {
		return fullImport;
	}
}

