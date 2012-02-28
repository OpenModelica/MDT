package org.openmodelica.modelicaml.modelica.importer.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.core.services.ServiceException;
import org.eclipse.papyrus.core.services.ServicesRegistry;
import org.eclipse.papyrus.core.utils.ServiceUtils;
import org.eclipse.papyrus.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.resource.NotFoundException;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.papyrus.umlutils.OpaqueBehaviorUtil;
import org.eclipse.papyrus.umlutils.PackageUtil;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.modelica.importer.model.ClassItem;
import org.openmodelica.modelicaml.modelica.importer.model.ComponentItem;
import org.openmodelica.modelicaml.modelica.importer.model.ExtendsRelationItem;
import org.openmodelica.modelicaml.modelica.importer.model.ImportRelationItem;
import org.openmodelica.modelicaml.modelica.importer.model.TreeBuilder;
import org.openmodelica.modelicaml.modelica.importer.model.TreeObject;
import org.openmodelica.modelicaml.modelica.importer.model.TreeParent;

public class ModelicaMLElementsCreator implements IRunnableWithProgress {
	
	private ServicesRegistry serviceRegistry = null;
	private TransactionalEditingDomain editingDomain = null;
	private UmlModel umlModel = null;
	private EObject ModelicaMLRoot = null;
	private TreeBuilder treeBuilder;
	
	public final static String COMPONENT_DESCRIPTION_PORT = "port";
	public final static String COMPONENT_DESCRIPTION_VARIABLE = "variable";
	public final static String COMPONENT_DESCRIPTION_COMPONENT= "component";
	private List<Element> createdClasses = new ArrayList<Element>();
	private List<Element> createdProperties = new ArrayList<Element>();
	private List<Element> createdGeneralizations = new ArrayList<Element>();
	
	private List<String> deletedProxyQNames = new ArrayList<String>();
	
	private List<Element> updatedClasses = new ArrayList<Element>();
	private List<Element> updatedProperties = new ArrayList<Element>();
	private List<Element> updatedGeneralizations = new ArrayList<Element>();

	// log entries
	private List<String> log = new ArrayList<String>();
	
	
	
	public ModelicaMLElementsCreator(){
		setEditingDomain();
		setModelicaMLRootElement();
	}

	public ModelicaMLElementsCreator(
			ServicesRegistry serviceRegistry, 
			TransactionalEditingDomain editingDomain, 
			UmlModel umlModel, 
			EObject ModelicaMLRoot,
			TreeBuilder treeBuilder){
		
		this.serviceRegistry = serviceRegistry;
		this.editingDomain = editingDomain;
		this.umlModel = umlModel;
		this.ModelicaMLRoot = ModelicaMLRoot;
		this.treeBuilder = treeBuilder;
		
	}

	private void setModelicaMLRootElement(){
		umlModel = UmlUtils.getUmlModel();
		try {
			ModelicaMLRoot = umlModel.lookupRoot();
		} catch (NotFoundException e) {
			addToLog("   ***  ERROR: Could not access the root elelement of the Papyrus model.");
			e.printStackTrace();
		}
	}
	
	
	public void createElements(Element parent, TreeParent treeParent, boolean update, boolean applyProxyStereotype, boolean recursive){
		// clear the log
		this.log.clear();
		
		// first create classes, because they will type the properties
		createClasses(parent, treeParent, update, applyProxyStereotype, recursive);
		// create properties and extends relations
		createClassElements(treeParent, update, applyProxyStereotype, recursive);
	}
	
	public void deleteInvalidProxyElements(String namespace){
		/* 
		 * Delete all proxies or their owned elements 
		 * that are not contained in the loaded Modelica models. 
		 */
		
		HashSet<String> modelicaModelQNames = new HashSet<String>();
		EList<TreeObject> treeItems = treeBuilder.getTreeItems(); 
		for (TreeObject treeObject : treeItems) {
			modelicaModelQNames.add(treeObject.getQName());
		}
		
		HashSet<Element> proxies = treeBuilder.getProxies();
		HashSet<Element> invalidProxies = new HashSet<Element>();
		HashSet<Element> invalidExtendsRelations = new HashSet<Element>();
		
		// find invalid proxies
		for (Element element : proxies) {
			
			if (element instanceof NamedElement && ((NamedElement)element).getQualifiedName().startsWith(namespace) ) {

				/*
				 *  IMPORTANT:  
				 *  - don't delete predefined types from the ModelicaML profile.
				 *  - do not delete elements which getModel() is (i.e. has Stereotype) "InstalledLibrary"
				 */
				if ( !(element instanceof PrimitiveType && isPredefinedModelicaType(element))  
						&& !(element.getModel().getAppliedStereotype(Constants.stereotypeQName_InstalledLibrary) != null)
						) {
					
					/*
					 * Find class proxies or their properties that do not exist in the loaded Modelica models.
					 */
					String qName = StringUtls.replaceSpecCharExceptThis(((NamedElement)element).getQualifiedName(), "::").replaceAll("::", ".");
					if ( !modelicaModelQNames.contains(qName) ) {
						deletedProxyQNames.add(((NamedElement)element).getQualifiedName());
						addToLog("Deleting " + ((NamedElement)element).getQualifiedName());
						invalidProxies.add(element);
					}
					
					/*
					 * Find class extends relations (inheritance) that do not have targets.
					 */
					if (element instanceof Classifier) {
						Classifier classifier = (Classifier) element;
						EList<Generalization> classExtendsRelations = classifier.getGeneralizations();
						for (Generalization generalization : classExtendsRelations) {
							EList<Element> targets = generalization.getTargets();
							if (targets != null && targets.size() > 0) {
								// ok
							}
							else {
								addToLog("Deleting invalid extends relation in " + ((NamedElement)element).getQualifiedName());
								invalidExtendsRelations.add(generalization);
							}
						}
					}
				}
			}
		}
		
		// Delete elements
		deleteElements(invalidProxies);
		deleteElements(invalidExtendsRelations);
	}
	
	
	public void deleteElements(final HashSet<Element> elements){
		/* 
		 * Delete elements form the given list  
		 */
		CompoundCommand cc = new CompoundCommand("Delete ModelicaML Proxy Element");
		Command command = new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				Iterator<Element> i = elements.iterator();
				while (i.hasNext()) {
					Object object = (Object) i.next();
					((Element)object).destroy();
				}
			}
		};
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);
	}
	
	
	private boolean isPredefinedModelicaType(Element element) {
		if (element instanceof NamedElement) {
			NamedElement namedElement = (NamedElement)element;
			if (namedElement.getName().equals(Constants.predefinedTypeName_real)) 		{ return true; }
			if (namedElement.getName().equals(Constants.predefinedTypeName_integer)) 	{ return true; }
			if (namedElement.getName().equals(Constants.predefinedTypeName_boolean)) 	{ return true; }
			if (namedElement.getName().equals(Constants.predefinedTypeName_string)) 	{ return true; }
		}
		return false;
	}
	
	
	public void createImportRelation(Class owningClass, TreeParent treeItem){
		
		// delete all existing import dependencies
		EList<Dependency> depList = owningClass.getClientDependencies();
		HashSet<Element> depToBeDeleted = new HashSet<Element>();
		for (Dependency dependency : depList) {
			if (dependency.getAppliedStereotype(Constants.stereotypeQName_ImportRelation) != null) {
				depToBeDeleted.add(dependency);
			}
		}
		
		deleteElements(depToBeDeleted);
		
		// Create new import dependencies
		List<ImportRelationItem> importRelations = new ArrayList<ImportRelationItem>();
		TreeObject[] children = treeItem.getChildren();
		for (int i = 0; i < children.length; i++) {
			TreeObject treeObject = children[i];
			if (treeObject instanceof ImportRelationItem) {
				importRelations.add((ImportRelationItem) treeObject);
			}
			
		}

		for (ImportRelationItem importRelation : importRelations) {
			Element target = treeBuilder.getTypeElement(importRelation.getTargetQname());
			if (target != null) {
				Element createdElement = createDependency(owningClass, (NamedElement) target, Constants.stereotypeQName_ImportRelation, importRelation);
				// TODO: add to created Imports? 
			}
		}
		
	}
	
	
	public Element createDependency(final Class sourceClass, final NamedElement targetClass, final String StereotypeQname, final TreeObject treeObject){
		
		CompoundCommand cc = new CompoundCommand("Create a ModelicaML Class Dependency");
		Command command = new RecordingCommand(editingDomain) {
			
			Element createdElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (createdElement != null) {
					collection.add(createdElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				createdElement = sourceClass.createDependency(targetClass);
				
				Stereotype s = createdElement.getApplicableStereotype(StereotypeQname);
				if (s != null) {
					createdElement.applyStereotype(s);
					if (treeObject instanceof ImportRelationItem) {
						ImportRelationItem importRelationItem = (ImportRelationItem) treeObject;
						((Dependency)createdElement).setName(importRelationItem.getSourceQname() + " imports " + importRelationItem.getTargetQname());
						createdElement.setValue(s, Constants.propertyName_alias, importRelationItem.getAlias());
					}
				}
				else {
					addToLog("Could not apply the import stereotype to the element '" + treeObject.getQName() + "'");
				}
				
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}
	
	
	
	public void createClassElements(TreeParent treeParent, boolean update, boolean applyProxyStereotype, boolean recursive){
		
		Element owningClass = treeParent.getModelicaMLProxy();
		
		if (treeParent instanceof ClassItem && treeParent.hasChildren()) {
			
			// create an import relation
			if (owningClass instanceof Class) {
				createImportRelation((Class) owningClass, treeParent);
			}
			
			TreeObject[] children = treeParent.getChildren();
			
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];
				
				// create a component
				if (treeObject instanceof ComponentItem && owningClass instanceof Classifier) {
					
					ComponentItem component = ((ComponentItem)treeObject);
					Element modelicaMLPropertyProxy = component.getModelicaMLProxy();
					
					if (modelicaMLPropertyProxy == null) { // if there is no proxy -> create one
						
						addToLog("Creating component: " + component.getQName());
//						System.out.println("Creating component: " + component.getQName());
						
						// set the component type (uml class that was just created) 
						Element type = treeBuilder.getTypeElement(component.getComponentTypeQame());
						component.setComponentTypeProxy(type);
						
						modelicaMLPropertyProxy = createProperty(owningClass, component, applyProxyStereotype);
						createdProperties.add(modelicaMLPropertyProxy);
						
						// set proxy
						component.setModelicaMLProxy(modelicaMLPropertyProxy);
						treeBuilder.addProxyToMaps((NamedElement) modelicaMLPropertyProxy);
						
						// update (stereotype) properties
						updateProperty(owningClass, modelicaMLPropertyProxy, component, applyProxyStereotype);
					}
					else { // update the existing proxy
						if (update) {
							if (modelicaMLPropertyProxy instanceof NamedElement) {
								// indicate
								addToLog("Updating component: " + ((NamedElement)modelicaMLPropertyProxy).getQualifiedName());
//								System.out.println("Updating component: " + ((NamedElement)modelicaMLPropertyProxy).getQualifiedName());
							}
							// update
							
							// set the component type (uml class that was just created) 
							Element type = treeBuilder.getTypeElement(component.getComponentTypeQame());
							component.setComponentTypeProxy(type);
							
							updateProperty(owningClass, modelicaMLPropertyProxy, component, applyProxyStereotype);			
						}
					}
				}
				
				// create extends relations
				if (treeObject instanceof ExtendsRelationItem && owningClass instanceof Classifier) {
					
					ExtendsRelationItem extendsRelation = ((ExtendsRelationItem)treeObject);
					Element modelicaMLGeneralizationProxy = extendsRelation.getModelicaMLProxy();
					
					// find the uml elements (classes that were just created) and set the source and target for this extends relations
					Element source = treeBuilder.getTypeElement(extendsRelation.getSourceQname());
					Element target = treeBuilder.getTypeElement(extendsRelation.getTargetQname());
					
					extendsRelation.setSource(source);
					extendsRelation.setTarget(target);
					
					EList<Element> exisitingTargets = new BasicEList<Element>();
					if (owningClass instanceof Classifier) {
						EList<Generalization> generalizations = ((Classifier)owningClass).getGeneralizations();
						for (Generalization generalization : generalizations) {
							exisitingTargets.addAll(generalization.getTargets());
							
							if (generalization.getTargets().contains(target)) {
								modelicaMLGeneralizationProxy = generalization;
							}
						}
					}
					// if there is no generalization pointing to the same target -> create a new one
					if ( !exisitingTargets.contains(target) ) {
						// create element
						modelicaMLGeneralizationProxy = createExtendsRelation(owningClass, extendsRelation, applyProxyStereotype);

						// set proxy
						extendsRelation.setModelicaMLProxy(modelicaMLGeneralizationProxy);
						
						if (modelicaMLGeneralizationProxy != null) {
							// update
							updateGeneralization(modelicaMLGeneralizationProxy, extendsRelation, applyProxyStereotype);
						}
					}
					else {
						if (update) {
							if ( modelicaMLGeneralizationProxy != null ) {
								// indicate
								addToLog("Updating generalization: " + extendsRelation.getSourceQname() + " -> " + 
										extendsRelation.getTargetQname());
//								System.out.println("Updating generalization: " + extendsRelation.getSourceQname() + " -> " + extendsRelation.getTargetQname());
								
								// update
								updateGeneralization(modelicaMLGeneralizationProxy, extendsRelation, applyProxyStereotype);			
							}
						}
					}
				}
				
				// recursive  call to create elements of the nested  classes
				if (recursive) {
					if (treeObject instanceof ClassItem) {
						createClassElements((TreeParent) treeObject, update, applyProxyStereotype, recursive);
					}
				}
			}
			
			// After the class components are created -> create class behavior and resolve connect equations
			createBehaviors(owningClass, treeParent);
		}
	}
	
	
	public void createClasses(Element parent, TreeParent treeParent, boolean update, boolean applyProxyStereotype, boolean recursive){

		if (parent != null && treeParent.hasChildren()) {
			TreeObject[] children = treeParent.getChildren();
			
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];

				if (treeObject instanceof ClassItem) {
					Element modelicaMLProxy = treeObject.getModelicaMLProxy();
					
					if (modelicaMLProxy == null) {
						// create element
						if (parent instanceof NamedElement) {
//							System.out.println("Creating class: " + treeObject.getQName());
							addToLog("Creating class: " + treeObject.getQName());
							
							modelicaMLProxy = createClass(parent, (ClassItem) treeObject, applyProxyStereotype);
							createdClasses.add(modelicaMLProxy);
							
							treeObject.setModelicaMLProxy(modelicaMLProxy);
							treeBuilder.addProxyToMaps((NamedElement) modelicaMLProxy);
							
							// update
							updateClass(modelicaMLProxy, (ClassItem) treeObject, applyProxyStereotype);
						}
					}
					else {
						if (update) {
							if (modelicaMLProxy instanceof NamedElement) {
								// indicate
//								System.out.println("Updating class: " + ((NamedElement)modelicaMLProxy).getQualifiedName());
								addToLog("Updating class: " + ((NamedElement)modelicaMLProxy).getQualifiedName());
							}
							// update
							updateClass(modelicaMLProxy, (ClassItem) treeObject, applyProxyStereotype);			
						}
					}
					
					// recursive  call
					if (recursive) {
						if (modelicaMLProxy instanceof NamedElement) {
							createClasses(modelicaMLProxy, (TreeParent) treeObject, update, applyProxyStereotype, recursive);
						}
					}
				}
			}
		}
	}
	
	public Element createClass(
			final Element parent, 
			final ClassItem classItem, 
			final boolean applyProxyStereotype){
		
		CompoundCommand cc = new CompoundCommand("Create a ModelicaML Proxy Class");
		Command command = new RecordingCommand(editingDomain) {
			
			Element createdElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (createdElement != null) {
					collection.add(createdElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				/*
				 * Note, the order of of clauses is important 
				 * to make sure that function behavior is checked before class and enum befor promitive type 
				 */
				if (parent instanceof Package) {
					if (classItem.getClassRestriction().equals("function")) {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.FUNCTION_BEHAVIOR);
					}
					else if (classItem.isEnumeration()) {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.ENUMERATION);
					}
					else if (classItem.getClassRestriction().equals("type")) {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.PRIMITIVE_TYPE);
					}
					else {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.CLASS);
					}
				}
				else if (parent instanceof Class) {
					if (classItem.getClassRestriction().equals("function")) {
						createdElement = ((Class)parent).createNestedClassifier(classItem.getName(), UMLPackage.Literals.FUNCTION_BEHAVIOR);
					}
					else if (classItem.isEnumeration()) {
						createdElement = ((Class)parent).createNestedClassifier(classItem.getName(), UMLPackage.Literals.ENUMERATION);
					}
					else if (classItem.getClassRestriction().equals("type")) {
						createdElement = ((Class)parent).createNestedClassifier(classItem.getName(), UMLPackage.Literals.PRIMITIVE_TYPE);
					}
					else {
						createdElement = ((Class)parent).createNestedClassifier(classItem.getName(), UMLPackage.Literals.CLASS);
					}
				}
				if (createdElement instanceof Element) {
//					Stereotype appliedStereotype = applyModelicaMLClassStereotype((Element)createdElement, (ClassItem) treeObject, applyProxyStereotype);
				}			
				else {
					addToLog("Could not create a ModelicaML class '"+classItem.getClassRestriction()+"'.");
//					System.err.println("Could not create a ModelicaML class '"+classItem.getClassRestriction()+"'.");
//					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Could not apply ModelicaML stereotype '"+treeObject.getClassRestriction()+"'. Please make sure that the ModelicaML profile is applied.");
				}
			}
			
			
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}
	
	
	private Element createExtendsRelation(final Element owningClass, final ExtendsRelationItem extendsRelationTreeObject, final boolean applyProxyStereotype){
		
		CompoundCommand cc = new CompoundCommand("Create a ModelicaML Class Extends Relation");
		Command command = new RecordingCommand(editingDomain) {
			
			Element createdElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (createdElement != null) {
					collection.add(createdElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				
				ExtendsRelationItem extendsRelation = (extendsRelationTreeObject);
				
//				Element source = owningClass;
				Element target = treeBuilder.getTypeElement(extendsRelation.getTargetQname());
				
				if (target instanceof Classifier) {
//					System.out.println("Creating extends relation: " + extendsRelationTreeObject.getSourceQname() + " -> " + extendsRelation.getTargetQname());
					addToLog("Creating extends relation: " + extendsRelationTreeObject.getSourceQname() + " -> " + extendsRelation.getTargetQname());
					
					createdElement = ((Classifier)owningClass).createGeneralization((Classifier)target);
					createdGeneralizations.add(createdElement);
				}
				else {
					addToLog("Could not create Generalization for " + extendsRelationTreeObject.getSourceQname());
//					System.err.println("Could not create Generalization for " + extendsRelationTreeObject.getSourceQname());
				}
				
				if (createdElement instanceof Generalization) {
				}			
				else {
					addToLog("Could not apply ModelicaML stereotype 'ExtendsRelation'. Please make sure that the ModelicaML profile is applied.");
//					System.err.println("Could not apply ModelicaML stereotype 'ExtendsRelation'. Please make sure that the ModelicaML profile is applied.");
				}
				
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}
	
	public Element createProperty(final Element owningClass, final ComponentItem componentTreeObject, final boolean applyProxyStereotype){
		
		CompoundCommand cc = new CompoundCommand("Create a ModelicaML Property Proxy");
		Command command = new RecordingCommand(editingDomain) {
			
			Element createdElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (createdElement != null) {
					collection.add(createdElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				
				// alert that the type is missing
				if (!(componentTreeObject.getComponentTypeProxy() instanceof Classifier)) {
					addToLog("Could not resolve the type with the qualified name '"+componentTreeObject.getComponentTypeQame()+"'");
//					System.err.println("Could not resolve the type with the qualified name '"+componentTreeObject.getComponentTypeQame()+"'");
				}

				// port
				if (componentTreeObject.isPort() && owningClass instanceof Class) {
					createdElement = ((Class)owningClass).createOwnedPort(componentTreeObject.getName(), (Type) componentTreeObject.getComponentTypeProxy());
				}
				// function argument
				else if (componentTreeObject.isFunctionArgument() && owningClass instanceof FunctionBehavior) {
					createdElement = ((FunctionBehavior)owningClass).createOwnedParameter(componentTreeObject.getName(), (Type) componentTreeObject.getComponentTypeProxy());
				}
				// enum literal
				else if (componentTreeObject.isEnumarationLiteral() && owningClass instanceof Enumeration) {
					createdElement = ((Enumeration)owningClass).createOwnedLiteral(componentTreeObject.getName());
				}
				// component
				else if (owningClass instanceof Class) {
					createdElement = ((Class)owningClass).createOwnedAttribute(componentTreeObject.getName(), (Type) componentTreeObject.getComponentTypeProxy());
				}
				
				// alert that the element could not be created
				if (!( createdElement instanceof Element )) {
					addToLog("Could not create ModelicaML component '"+componentTreeObject.getQName()+"'.");
//					System.err.println("Could not create ModelicaML component '"+componentTreeObject.getQName()+"'.");
				}
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}
	
	private Element updateGeneralization(
			final Element extendsRelationElement, 
			final ExtendsRelationItem extendsRelationItem, 
			final boolean applyProxyStereotype){
		
		CompoundCommand cc = new CompoundCommand("Update ModelicaML Generalization Proxy");
		Command command = new RecordingCommand(editingDomain) {
			
			Element updatedElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (updatedElement != null) {
					collection.add(updatedElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				
				// get or apply the appropriate stereotype 
				Stereotype appropriateStereotype = null;
				if (extendsRelationItem.getTarget() instanceof PrimitiveType) {
					appropriateStereotype = extendsRelationElement.getApplicableStereotype(Constants.stereotypeQName_TypeRelation);
				}
				else {
					appropriateStereotype = extendsRelationElement.getApplicableStereotype(Constants.stereotypeQName_ExtendsRelation);
				}
				
				if (appropriateStereotype == null) {
					addToLog("Could not find appropriate stereotype for " + extendsRelationElement);
				}
				
				if (appropriateStereotype != null && !extendsRelationElement.isStereotypeApplied(appropriateStereotype)) {
					extendsRelationElement.applyStereotype(appropriateStereotype);	
				}
				
				if (appropriateStereotype != null) {
					
					// set modifications
					if (extendsRelationItem.getModifications() != null) {
						extendsRelationElement.setValue(appropriateStereotype, Constants.propertyName_modification, extendsRelationItem.getModifications());
					}
					
					// set array size 
					if (appropriateStereotype.getQualifiedName().equals(Constants.stereotypeQName_TypeRelation)
							&& extendsRelationItem.getArraySize() != null) {
						extendsRelationElement.setValue(appropriateStereotype, Constants.propertyName_arraySize, extendsRelationItem.getArraySize());
					}
				}
				
				// delete unappropriated stereotypes
				// get all possible ModelicaML property stereotypes
				HashSet<Stereotype> possibleStereotype = new HashSet<Stereotype>();
				possibleStereotype.add(extendsRelationElement.getApplicableStereotype(Constants.stereotypeQName_TypeRelation));
				possibleStereotype.add(extendsRelationElement.getApplicableStereotype(Constants.stereotypeQName_ExtendsRelation));
				
				// unapply all stereotypes from the list (see above) except the appropriate one!
				for (Stereotype stereotype : possibleStereotype) {
					if (stereotype != null && !stereotype.equals(appropriateStereotype)) {
						if (extendsRelationElement.isStereotypeApplied(stereotype)) {
							extendsRelationElement.unapplyStereotype(stereotype);	
						}
					}
				}
				
				// add to updated classes list
				updatedGeneralizations.add(extendsRelationElement);
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}
	
	
	public Element updateClass(
			final Element classElement, 
			final ClassItem classItem, 
			final boolean applyProxyStereotype){
		
		CompoundCommand cc = new CompoundCommand("Update ModelicaML Class Proxy");
		Command command = new RecordingCommand(editingDomain) {
			
			Element updatedElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (updatedElement != null) {
					collection.add(updatedElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				
				// get or apply the appropriate stereotype 
				Stereotype appropriateStereotype = null;
				appropriateStereotype = applyModelicaMLClassStereotype(classElement, classItem, applyProxyStereotype);
				
				if (classElement instanceof Classifier && appropriateStereotype != null) {
					
					// set encapsulated
					((Classifier)classElement).setValue(appropriateStereotype, Constants.propertyName_encapsulated, classItem.isEncapsulated());
					
					// set partial
					((Classifier)classElement).setValue(appropriateStereotype, Constants.propertyName_partial, classItem.isPartial());
					((Classifier)classElement).setIsAbstract(classItem.isPartial());
					
					// set final
					((Classifier)classElement).setValue(appropriateStereotype, Constants.propertyName_final, classItem.isFinal());
					
					// set replaceable
					((Classifier)classElement).setValue(appropriateStereotype, Constants.propertyName_replaceable, classItem.isReplaceable());
					
					// set expandable connector
					if (classItem.getClassRestriction().contains(Constants.propertyName_expandable)) {
						((Classifier)classElement).setValue(appropriateStereotype, Constants.propertyName_expandable, true);
					}
					
					// sync. comment and annotations
					updateCommentsAndAnnotation(classElement, classItem);
					
					// delete unappropriated stereotypes
					// get all possible ModelicaML property stereotypes
					HashSet<Stereotype> possibleStereotype = getClassApplicableStereotypes(classElement);
					
					// unapply all stereotypes from the list (see above) except the appropriate one!
					for (Stereotype stereotype : possibleStereotype) {
						if (stereotype != null && !stereotype.equals(appropriateStereotype)) {
							if (classElement.isStereotypeApplied(stereotype)) {
								classElement.unapplyStereotype(stereotype);	
							}
						}
					}
					
					// add to updated classes list
					updatedClasses.add(classElement);
				}
				else {
					addToLog("The proxy for '" + classItem.getQName() + "' is not a UML Classifier.");
//					System.err.println("The proxy for '" + classItem.getQName() + "' is not a UML Classifier.");
				}
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}
	
	
	private void updateCommentsAndAnnotation(Element element, TreeObject treeObject){
		if (treeObject instanceof ClassItem && element instanceof Classifier) {
			Classifier clazz = (Classifier)element;
			
			// delete existing comments and annotation (note, that ModeicaML annotation is a sub-type of comment)
			EList<Comment> existingComments = clazz.getOwnedComments();
			deleteElements(new HashSet<Element>(existingComments));
			
			// Create new comments
			String comment = ((ClassItem)treeObject).getComment();
			if ( comment != null && !comment.replaceAll("\"", "").trim().equals("")) {
				Comment newComment = ((Classifier)element).createOwnedComment();
				newComment.setBody(comment);
			}
			
			// Create annotations
			List<String> annotations = ((ClassItem)treeObject).getAnnotations();
			if (annotations!= null && annotations.size() > 0 ) {
				for (String string : annotations) {
					Comment annotation = ((Classifier)element).createOwnedComment();
					Stereotype stereotype = annotation.getApplicableStereotype(Constants.stereotypeQName_Annotation);
					
					// replace \\" with \" 
					String updatedString = string.replaceAll("\\\\\\\\\"", "\\\\\"");
					
					if (stereotype != null) {
						annotation.applyStereotype(stereotype);
						annotation.setValue(stereotype, Constants.propertyName_fullAnnotationString, updatedString);
					}
					annotation.setBody(updatedString);
				}
			}
		}
		
		// TODO: for the components
	}
	
	
	public Element createBehaviors(final Element element, final TreeObject treeObject){
		
		CompoundCommand cc = new CompoundCommand("Create ModelicaML Class Behavior");
		Command command = new RecordingCommand(editingDomain) {
			
			Element updatedElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (updatedElement != null) {
					collection.add(updatedElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				// update class behavior 
				updateBehaviors(element, treeObject);
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}

	
	
	private void updateBehaviors(Element element, TreeObject treeObject){
		
		if (treeObject instanceof ClassItem && element instanceof Classifier) {
			Classifier clazz = (Classifier)element;
			
			/*
			 * Delete existing OpaqueBehaviors before recreating class algorithm or equation sections.
			 * Note, we assume that the proxies are not modified in the ModelicaML model. So that there are no State Machines
			 * or other behavior except OpaqueBehaviors. That is why we only delete OpaqueBehaviors.
			 * One exception: FunctionBehavaior is a sub-type of OpaqueBehavior. These should not be deleted. 
			 */
			EList<Element> existingElements = clazz.getOwnedElements();
			EList<Behavior> existingBehaviors = new BasicEList<Behavior>();

			for (Element existingElement : existingElements) {
				if (existingElement instanceof OpaqueBehavior && !(existingElement instanceof FunctionBehavior)) {
					existingBehaviors.add((Behavior) existingElement);
				}
			}
			
			deleteElements(new HashSet<Element>(existingBehaviors));
			
			
			// Create initialAlgorithms
			List<String> initialAlgorithms = ((ClassItem)treeObject).getInitialAlgorithms();
			if (initialAlgorithms!= null && initialAlgorithms.size() > 0 ) {
				int i = 0;
				for (String string : initialAlgorithms) {
					i ++;
					string = string.replaceAll("\\\\\\\\\"", "\\\\\"");
					/*
					 * If it is a Modelica functions which is translated to UML::FunctionBehavior 
					 * 	-> add the body to UML::FunctionBehavior, else create a new UML::OpaqueBehavor
					 */
					if (element instanceof FunctionBehavior ) {
						OpaqueBehaviorUtil.setBody((FunctionBehavior) element, string, Constants.actionLanguage);
					}
					else {
						OpaqueBehavior behavior = (OpaqueBehavior) ((Class)element).createOwnedBehavior("initial algorithm " + i,  UMLPackage.Literals.OPAQUE_BEHAVIOR);
						Stereotype stereotype = behavior.getApplicableStereotype(Constants.stereotypeQName_Algorithm);
						if (stereotype != null) {
							behavior.applyStereotype(stereotype);
							behavior.setValue(stereotype, Constants.propertyName_initial, true);
						}
						
						OpaqueBehaviorUtil.setBody(behavior, string, Constants.actionLanguage);
					}
				}
			}
			
			// Create initialEquations
			List<String> initialEquations = ((ClassItem)treeObject).getInitialEquations();
			if (initialEquations != null && initialEquations.size() > 0 ) {
				int i = 0;
				for (String string : initialEquations) {
					i ++;
					string = string.replaceAll("\\\\\\\\\"", "\\\\\"");
					/*
					 * If it is a Modelica functions which is translated to UML::FunctionBehavior 
					 * 	-> add the body to UML::FunctionBehavior, else create a new UML::OpaqueBehavor
					 */
					if (element instanceof FunctionBehavior ) {
						OpaqueBehaviorUtil.setBody((FunctionBehavior) element, string, Constants.actionLanguage);
					}
					else {
						OpaqueBehavior behavior = (OpaqueBehavior) ((Class)element).createOwnedBehavior("initial equations " + i,  UMLPackage.Literals.OPAQUE_BEHAVIOR);
						Stereotype stereotype = behavior.getApplicableStereotype(Constants.stereotypeQName_Equations);
						if (stereotype != null) {
							behavior.applyStereotype(stereotype);
							behavior.setValue(stereotype, Constants.propertyName_initial, true);
						}
						OpaqueBehaviorUtil.setBody(behavior, string, Constants.actionLanguage);
					}
				}
			}
			
			// Create algorithms
			List<String> algorithms = ((ClassItem)treeObject).getAlgorithms();
			if (algorithms!= null && algorithms.size() > 0 ) {
				int i = 0;
				for (String string : algorithms) {
					i ++;
					string = string.replaceAll("\\\\\\\\\"", "\\\\\"");
					/*
					 * If it is a Modelica functions which is translated to UML::FunctionBehavior 
					 * 	-> add the body to UML::FunctionBehavior, else create a new UML::OpaqueBehavor
					 */
					if (element instanceof FunctionBehavior ) {
						OpaqueBehaviorUtil.setBody((FunctionBehavior) element, string, Constants.actionLanguage);
					}
					else {
						OpaqueBehavior behavior = (OpaqueBehavior) ((Class)element).createOwnedBehavior("algorithm " + i,  UMLPackage.Literals.OPAQUE_BEHAVIOR);
						Stereotype stereotype = behavior.getApplicableStereotype(Constants.stereotypeQName_Algorithm);
						if (stereotype != null) {
							behavior.applyStereotype(stereotype);
						}
						OpaqueBehaviorUtil.setBody(behavior, string, Constants.actionLanguage);
					}
				}
			}
			
			// Create equations
			/*
			 * Only in equation sections the connects are resolved replaced by UML Connectors if possible.
			 */
			List<String> equations = ((ClassItem)treeObject).getEquations();
			
			// Delete all existing class connectors
			if (element instanceof Class) {
				deleteConnectors((Class)element);
			}

			if (equations != null && equations.size() > 0 ) {
				int i = 0;


				for (String string : equations) {
					i ++;
					string = string.replaceAll("\\\\\\\\\"", "\\\\\"");
					/*
					 * If it is a Modelica functions which is translated to UML::FunctionBehavior 
					 * 	-> add the body to UML::FunctionBehavior, else create a new UML::OpaqueBehavor
					 */
					if (element instanceof FunctionBehavior ) {
						OpaqueBehaviorUtil.setBody((FunctionBehavior) element, string, Constants.actionLanguage);
					}
					else {

						// Resolve connect statements, create UML Connectors if possible, remove connect equations from code.
						string = resolveConnects((Class)element, string);

						OpaqueBehavior behavior = (OpaqueBehavior) ((Class)element).createOwnedBehavior("equations " + i,  UMLPackage.Literals.OPAQUE_BEHAVIOR);
						Stereotype stereotype = behavior.getApplicableStereotype(Constants.stereotypeQName_Equations);
						if (stereotype != null) {
							behavior.applyStereotype(stereotype);
						}
						
						OpaqueBehaviorUtil.setBody(behavior, string, Constants.actionLanguage);
					}
				}
			}
			
		}
	}
	
	
	private void deleteConnectors(Class owningClass){
		EList<Connector> list = owningClass.getOwnedConnectors();
		deleteElements(new HashSet<Element>(list));
	}
	
	private String resolveConnects(Class owningClass, String string){
		String updatedString = string;
		/*
		 * Note, the following regex does not match for loop equations,
		 * i.e. connects containing "[" or "]" for capturing the indices.
		 * For loop connects and connects that use array sub-scripts 
		 * are not translated into UML connectors, but left within equations section code.
		 */
//		Pattern pattern = Pattern.compile("connect(\\s)*\\((\\w|,|\\.|\\s|\n|\r|\t)+\\)(\\s)*;");
//		Pattern pattern = Pattern.compile("connect(\\s)*\\((\\w|,|\\.|\\s|\n|\r|\t)+\\)(\\s)*(annotation\\((.)*\\))*(\\s)*;");
		Pattern pattern = Pattern.compile("connect(\\s)*\\((\\w|,|\\.|\\s|\n|\r|\r\n|\t)+\\)");
		
		Matcher matcher = pattern.matcher(string);
        
		boolean result = matcher.find();
        // Loop through and create a new String with the replacements
        while(result) {
        	boolean resolved = resolveConnect(owningClass, matcher.group());
        	// if a UML Connector could be created -> remove the connect statement from code
        	if (resolved) {
//    			updatedString = updatedString.replace(matcher.group(), "");
        		int nextSemicolonPosition = StringHandler.getNextSemicolonPosition(string, matcher.start(), matcher.group());
//    			updatedString = updatedString.replace(matcher.group(), "/* " + matcher.group() + " was REPLACED by a UML Connector. */");
        		if (nextSemicolonPosition > 0) {
            		updatedString = updatedString.replace(string.substring(matcher.start(), nextSemicolonPosition + 1), 
							"/* REPLACED by a UML Connector -> " 	+ string.substring(matcher.start(), nextSemicolonPosition + 1) 
									+ "*/");
				}
			}

        	result = matcher.find();
        }

        return updatedString;
	}
	
	private boolean resolveConnect(Class owningClass, String connectStatement) {
		ArrayList<String> ends = StringHandler.getConnectEnds(connectStatement);
		if (ends != null && ends.size() == 2) {
			String portQName1 = ends.get(0);
			String portQName2 = ends.get(1);

			Property partWithPort1 = getPartWithPortToBeConnected(owningClass, portQName1);
			Port port1 = getPortToBeConnected(owningClass, portQName1);
			
			Property partWithPort2 = getPartWithPortToBeConnected(owningClass, portQName2);
			Port port2 = getPortToBeConnected(owningClass, portQName2);
			
			if ( port1 != null && port2 != null ) {
				// Create a connector
				Connector connector = owningClass.createOwnedConnector(ends.get(0) + "," + ends.get(1));
				Stereotype s = connector.getApplicableStereotype(Constants.stereotypeQName_Connection);
				if (s != null) {
					connector.applyStereotype(s);
					/*
					 * Set the explicit connection ends.
					 * Note, the stereotype property "explicitConnectionEnds" is only necessary 
					 * for connecting the ports that are the nested within ports. For the sake of simplicity 
					 * this is set for all UML Connectors when synchronizing code with ModelicaML model.
					 */
					connector.setValue(s, Constants.propertyName_explicitConnectionEnds, ends.get(0) + "," + ends.get(1) );
				}
				ConnectorEnd end1 = connector.createEnd();
				end1.setRole(port1);
				end1.setPartWithPort(partWithPort1);
				ConnectorEnd end2 = connector.createEnd();
				end2.setRole(port2);
				end2.setPartWithPort(partWithPort2);
				
				return true;
			}
		}
		
		return false;
	}

	
	// BACKUP
//	private boolean resolveConnect(Class owningClass, String connectStatement) {
//		String string = connectStatement.trim().replaceFirst("connect", "").trim();
//		// remove semicolon and the enclosing brackets
//		String connectionEnds = StringHandler.removeFirstLastBrackets(string.substring(0, string.length()-1));
//		
//		String[] splitted = connectionEnds.split(",");
//		if (splitted.length == 2) {
//			String portQName1 = splitted[0];
//			String portQName2 = splitted[1];
//
//			Property partWithPort1 = getPartWithPortToBeConnected(owningClass, portQName1);
//			Port port1 = getPortToBeConnected(owningClass, portQName1);
//			
//			Property partWithPort2 = getPartWithPortToBeConnected(owningClass, portQName2);
//			Port port2 = getPortToBeConnected(owningClass, portQName2);
//			
//
//			if ( port1 != null && port2 != null ) {
//				// Create a connector
//				Connector connector = owningClass.createOwnedConnector(string);
//				Stereotype s = connector.getApplicableStereotype(Constants.stereotypeQName_Connection);
//				if (s != null) {
//					connector.applyStereotype(s);
//					/*
//					 * Set the explicit connection ends.
//					 * Note, the stereotype property "explicitConnectionEnds" is only necessary 
//					 * for connecting the ports that are the nested within ports. For the sake of simplicity 
//					 * this is set for all UML Connectors when synchronizing code with ModelicaML model.
//					 */
//					connector.setValue(s, Constants.propertyName_explicitConnectionEnds, connectionEnds);
//				}
//				ConnectorEnd end1 = connector.createEnd();
//				end1.setRole(port1);
//				end1.setPartWithPort(partWithPort1);
//				ConnectorEnd end2 = connector.createEnd();
//				end2.setRole(port2);
//				end2.setPartWithPort(partWithPort2);
//				
//				return true;
//			}
//		}
//		return false;
//	}
	
	
	
	private Port getPortToBeConnected(Class owningClass, String portQName){

		if (portQName.split("\\.").length == 1) {
			EList<Port> ports = owningClass.getOwnedPorts();
			for (Port port : ports) {
				if (port.getName().equals(portQName)) {
					return port;
				}
			}
		}
		
		else {
			String[] splitted = portQName.trim().split("\\.");
			if (splitted.length >= 2 ) {
				String propertyName = splitted[0].trim();
				String portName = splitted[1].trim();
				
				EList<Property> properties = owningClass.getAttributes(); // this is important to get all inherited ports!
				for (Property property : properties) {

					if (property.getName().equals(propertyName)) {
						Type type = property.getType();
						if (type instanceof Class) {
							EList<Property> allProperties = ((Class)type).getAllAttributes();
							for (Property typeProperty : allProperties) {
								if (typeProperty instanceof Port  && typeProperty.getName().equals(portName)) {
									return (Port) typeProperty;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	
	private Property getPartWithPortToBeConnected(Class owningClass, String portQName){

		String[] splitted = portQName.trim().split("\\.");
		if (splitted.length >= 2 ) {
			
			String propertyName = splitted[0];
			
			EList<Property> properties = owningClass.getAttributes();
			for (Property property : properties) {

				if (property.getName().equals(propertyName)) {
					return property;
				}
			}
		}

		return null;
	}

	
	public Element updateProperty(
			final Element owningClass, 
			final Element property, 
			final ComponentItem componentTreeObject, 
			final boolean applyProxyStereotype){
		
		CompoundCommand cc = new CompoundCommand("Update ModelicaML Property Proxy");
		Command command = new RecordingCommand(editingDomain) {
			
			Element updatedElement = null;
			
			@Override
			public Collection<?> getResult() {
				List<Element> collection = new ArrayList<Element>();
				if (updatedElement != null) {
					collection.add(updatedElement);
				}
				return collection;
			};
			
			@Override
			protected void doExecute() {
				
				// update component type
				if (property instanceof TypedElement) {
					// update type
					Element type = componentTreeObject.getComponentTypeProxy();
					if (type instanceof Classifier) {
						((TypedElement)property).setType((Classifier)type);
					}
					else if (componentTreeObject.getComponentTypeProxy() == null) {
						((TypedElement)property).setType(null);
					}
				}
				else {
					addToLog("The proxy for '" + componentTreeObject.getQName() + "' is not a UML TypedElement.");
//					System.err.println("The proxy for '" + componentTreeObject.getQName() + "' is not a UML TypedElement.");
				}

				// get or apply the appropriate stereotype 
				Stereotype appropriateStereotype = null;
				appropriateStereotype = applyModelicaMLPropertyStereotype(property, componentTreeObject, applyProxyStereotype);
				
				// update stereotype properties
				updateComponentStereotypeProperties(componentTreeObject, property, appropriateStereotype);

				// delete unappropriated stereotypes
				// get all possible ModelicaML property stereotypes
				HashSet<Stereotype> possibleStereotype = getCompomentApplicableStereotypes(property);
				// unapply all stereotypes from the list (see above) except the appropriate one!
				for (Stereotype stereotype : possibleStereotype) {
					if (stereotype != null && !stereotype.equals(appropriateStereotype)) {
						if (property.isStereotypeApplied(stereotype)) {
							property.unapplyStereotype(stereotype);	
						}
					}
				}
				
				// add to updated components list
				updatedProperties.add(property);
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);

		Collection<?> result = command.getResult();
		for (Object object : result) {
			if (object instanceof Element) {
				return (Element)object;
			}
		}
		return null;
	}
	
	
	private void updateComponentStereotypeProperties(ComponentItem componentItem, Element componentElement, Stereotype componentStereotype){
		if (componentStereotype != null ) {
			
			// set visibility
			if (componentItem.getVisibility().equals("protected") && componentElement instanceof TypedElement) {
				((TypedElement)componentElement).setVisibility(VisibilityKind.PROTECTED_LITERAL);
			}
			else {
				((TypedElement)componentElement).setVisibility(VisibilityKind.PUBLIC_LITERAL);
			}
			
			// set inner/outer
			if (!componentItem.isComponent() && componentItem.getInnerouter().equals("inner")) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaScope", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("inner");
					componentElement.setValue(componentStereotype, Constants.propertyName_scope, literal);
				}
			}
			else if (!componentItem.isComponent() && componentItem.getInnerouter().equals("outer")) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaScope", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("outer");
					componentElement.setValue(componentStereotype, Constants.propertyName_scope, literal);
				}
			}
			
			// set isFinal
			if (!componentItem.isComponent()) {
				componentElement.setValue(componentStereotype, Constants.propertyName_final, componentItem.isFinal());	
			}
			
			// set modifications
			if (componentItem.getModifications() != null) {
				componentElement.setValue(componentStereotype, Constants.propertyName_modification, componentItem.getModifications());
			}
			
			// set arraySize
			if (componentItem.getArraySize() != null) {
				componentElement.setValue(componentStereotype, Constants.propertyName_arraySize, componentItem.getArraySize());
			}

			// set conditionalExpression
			if (componentItem.getConditionalExpression() != null) {
				componentElement.setValue(componentStereotype, Constants.propertyName_conditionalExpression, componentItem.getConditionalExpression());
			}
			
			// set declarationEquationOrAssignment
			if (!componentItem.isComponent() && !componentItem.isPort() && componentItem.getDeclaration() != null) {
				componentElement.setValue(componentStereotype, Constants.propertyName_declarationEquationOrAssignment, componentItem.getDeclaration());
			}
			
			// set flow or stream
			if (componentItem.isVariable() && componentItem.isFlow()) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaFlowFlag", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("flow");
					componentElement.setValue(componentStereotype, Constants.propertyName_flowFlag, literal);
				}
			}
			else if (componentItem.isVariable() && componentItem.isStream()) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaFlowFlag", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("stream");
					componentElement.setValue(componentStereotype, Constants.propertyName_flowFlag, literal);
				}
			}
			
			// set causality for UML Parameter
			if (componentItem.isFunctionArgument() && componentElement instanceof Parameter) {
				if (componentItem.isInput()) {
					((Parameter)componentElement).setDirection(ParameterDirectionKind.IN_LITERAL);
				}
				else if (componentItem.isOutput()) {
					((Parameter)componentElement).setDirection(ParameterDirectionKind.OUT_LITERAL);
				}
			}
			
			// set causality
			if (!componentItem.isComponent() && componentItem.isInput()) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaCausality", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("input");
					componentElement.setValue(componentStereotype, Constants.propertyName_causality, literal);
				}
			}
			else if (!componentItem.isComponent() && componentItem.isOutput()) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaCausality", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("output");
					componentElement.setValue(componentStereotype, Constants.propertyName_causality, literal);
				}
			}
			
			// set variability
			if (!componentItem.isComponent() && componentItem.getVariability().equals("discrete")) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaVariability", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("discrete");
					componentElement.setValue(componentStereotype, Constants.propertyName_variability, literal);
				}
			}
			else if (!componentItem.isComponent() && componentItem.getVariability().equals("parameter")) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaVariability", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("parameter");
					componentElement.setValue(componentStereotype, Constants.propertyName_variability, literal);
				}
			}
			else if (!componentItem.isComponent() && componentItem.getVariability().equals("constant")) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaVariability", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("constant");
					componentElement.setValue(componentStereotype, Constants.propertyName_variability, literal);
				}
			}
		}
		else { 
			addToLog("Not Stereotype found for '"+componentItem.getQName()+"'");
//			System.err.println("Not Stereotype found for '"+componentItem.getQName()+"'");
		}
	}
	
	
	
	private HashSet<Stereotype> getCompomentApplicableStereotypes(Element property){
		HashSet<Stereotype> stereotypes = new HashSet<Stereotype>();
		if (property instanceof Parameter) {
			Stereotype stereotype = property.getApplicableStereotype(Constants.stereotypeQName_FunctionArgument);
			if (stereotype != null) {
				stereotypes.add(stereotype);
			}
			return stereotypes;
		}
		else if (property instanceof Property) {

			Stereotype stereotype = property.getApplicableStereotype(Constants.stereotypeQName_Component);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = property.getApplicableStereotype(Constants.stereotypeQName_Component);
			if ( stereotype != null ) { stereotypes.add(stereotype); }
			
			stereotype = property.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = property.getApplicableStereotype(Constants.stereotypeQName_RequirementInstance);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = property.getApplicableStereotype(Constants.stereotypeQName_ConnectionPort);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = property.getApplicableStereotype(Constants.stereotypeQName_Variable);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

		}
		return stereotypes;
	}
	
	
	private HashSet<Stereotype> getClassApplicableStereotypes(Element classElement){
		HashSet<Stereotype> stereotypes = new HashSet<Stereotype>();
		Stereotype stereotype = null;
		if (classElement instanceof Classifier) {
			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_ModelicaClass);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_ModelicaPackage);
			if ( stereotype != null ) { stereotypes.add(stereotype); }
			
			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_Type);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_Block);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_Connector);
			if ( stereotype != null ) { stereotypes.add(stereotype); }

			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_Record);
			if ( stereotype != null ) { stereotypes.add(stereotype); }
			
			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_Operator);
			if ( stereotype != null ) { stereotypes.add(stereotype); }
		}
		else if (classElement instanceof FunctionBehavior) {
			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_Function);
			if ( stereotype != null ) { stereotypes.add(stereotype); }
		}
		else if (classElement instanceof Enumeration) {
			stereotype = classElement.getApplicableStereotype(Constants.stereotypeQName_ModelicaEnumeration);
			if ( stereotype != null ) { stereotypes.add(stereotype); }
		}
		return stereotypes;
	}
	
	
	public String getComponentDescription(Type type){
		if (type instanceof PrimitiveType) {
			return COMPONENT_DESCRIPTION_VARIABLE;
		}
		if (type instanceof Class) {
			Stereotype s = type.getAppliedStereotype(Constants.stereotypeQName_Connector);
			if (s != null) {
				return COMPONENT_DESCRIPTION_PORT;
			}
		}
		return COMPONENT_DESCRIPTION_COMPONENT;
	}
	
	
	private Stereotype applyModelicaMLPropertyStereotype(
			Element element, 
			ComponentItem componentTreeObject, 
			boolean applyProxyStereotype){
		
		Stereotype appliedStereotype = null;
		if (element != null) {
			
			if (componentTreeObject.getComponentTypeProxy() == null) {
				addToLog("No type is defined for '"+componentTreeObject.getQName()+"'.");
//				System.err.println("No type is defined for '"+componentTreeObject.getQName()+"'.");
			}
			
			// check if the right stereotype is already applied
			appliedStereotype = element.getAppliedStereotype(getPropertyStereotypeQName(componentTreeObject));

			if (appliedStereotype == null ) {
			
				Stereotype stereotype = element.getApplicableStereotype(getPropertyStereotypeQName(componentTreeObject));
				if (stereotype != null) {
					element.applyStereotype(stereotype);
					
					// return the appliedStereotype
					appliedStereotype = stereotype;
				}
				else {
					addToLog("Could not apply ModelicaML stereotype for " +
							"component '"+componentTreeObject.getQName()+"'. " +
					"Please make sure the the ModelicaML profile is applied.");
					
//					System.err.println("Could not apply ModelicaML stereotype for " +
//							"component '"+componentTreeObject.getQName()+"'. " +
//									"Please make sure the the ModelicaML profile is applied.");
				}
			}
			
			/*
			 * Note: It is not necessary to mark components as proxies because they
			 * are always contained by classes that have the proxy stereotype. 
			 */
			
//			if (applyProxyStereotype) {
//				Stereotype proxyStereotype = applyProxyStereotype(element);
//				if (proxyStereotype == null ) {
//					System.err.println("Could not apply ModelicaML stereotype 'Proxy' for component '"
//							+componentTreeObject.getQName()+"'. Please make sure the the ModelicaML profile is applied.");
//				}
//			}
		}
		else {
			addToLog("The UML element is defined for '"+componentTreeObject.getQName()+"'");
//			System.err.println("The UML element is defined for '"+componentTreeObject.getQName()+"'");
		}
		
		return appliedStereotype;
	}
	
	
//	private Stereotype applyProxyStereotype(Element element){
//		Stereotype appliedStereotype = element.getAppliedStereotype(Constants.stereotypeQName_ModelicaModelProxy);
//		if (appliedStereotype != null ) {
//			return appliedStereotype;
//		}
//		else {
//			Stereotype stereotypeToBeApplied = element.getApplicableStereotype(Constants.stereotypeQName_ModelicaModelProxy);
//			if (stereotypeToBeApplied != null && !element.isStereotypeApplied(stereotypeToBeApplied)) {
//				element.applyStereotype(stereotypeToBeApplied);
//				return stereotypeToBeApplied;
//			}
//		}
//		return null;
//	}
	
	private Stereotype applyModelicaMLClassStereotype(Element element, ClassItem classItemObject, boolean applyProxyStereotype){
		Stereotype appliedStereotype = null;
		
		if (element != null) {
			appliedStereotype = element.getAppliedStereotype(getClassRestrictionStereotypeQName(classItemObject.getClassRestriction()));
			if (appliedStereotype == null) {
				Stereotype stereotype = element.getApplicableStereotype(getClassRestrictionStereotypeQName(classItemObject.getClassRestriction()));
				if (stereotype != null) {
					element.applyStereotype(stereotype);
					appliedStereotype = stereotype;
				}
				else {
					addToLog("Could not apply ModelicaML stereotype '"
							+classItemObject.getClassRestriction()+"' " +
							"to the class '"+classItemObject.getQName()+"'. " +
									"Please make sure the the ModelicaML profile is applied.");
//					System.err.println("Could not apply ModelicaML stereotype '"
//							+classItemObject.getClassRestriction()+"' " +
//									"to the class '"+classItemObject.getQName()+"'. " +
//											"Please make sure the the ModelicaML profile is applied.");
				}
			}
			
			// apply proxy stereotype
			if (applyProxyStereotype) {
				Stereotype proxyStereotype = element.getApplicableStereotype(Constants.stereotypeQName_ModelicaModelProxy);
				if (proxyStereotype != null) {
					if (!element.isStereotypeApplied(proxyStereotype)) {
						element.applyStereotype(proxyStereotype);	
					}
				}
				else {
					addToLog("Could not apply ModelicaML stereotype 'Proxy' to class '"+classItemObject.getQName()
							+"'. Please make sure the the ModelicaML profile is applied.");
//					System.err.println("Could not apply ModelicaML stereotype 'Proxy' to class '"+classItemObject.getQName()
//							+"'. Please make sure the the ModelicaML profile is applied.");
				}
			}
		}
		
		return appliedStereotype;
	}
	
	private String getPropertyStereotypeQName(ComponentItem componentTreeObject){
		if (componentTreeObject.isComponent()) 			{ return Constants.stereotypeQName_Component;}
		if (componentTreeObject.isPort()) 				{ return Constants.stereotypeQName_ConnectionPort;}
		if (componentTreeObject.isVariable()) 			{ return Constants.stereotypeQName_Variable;}
		if (componentTreeObject.isFunctionArgument()) 	{ return Constants.stereotypeQName_FunctionArgument;}
		// Note, an enumeration literal does not need a stereotype
		if (!componentTreeObject.isEnumarationLiteral()) {
			addToLog("Could not find appropriate ModelicaML stereotype for the '"+componentTreeObject.getQName()+"'. Applying a default stereotype.");
//			System.err.println("Could not find appropriate ModelicaML stereotype for the '"+componentTreeObject.getQName()+"'. Applying a default stereotype.");
			return Constants.stereotypeQName_Variable;
		}
		return "";
	}

	
	private String getClassRestrictionStereotypeQName(String restriction){
		if (restriction.equals("class")) 	{ return Constants.stereotypeQName_ModelicaClass;}
		if (restriction.equals("model")) 	{ return Constants.stereotypeQName_Model;}
		if (restriction.equals("package")) 	{ return Constants.stereotypeQName_ModelicaPackage;}
		if (restriction.equals("block")) 	{ return Constants.stereotypeQName_Block;}
		if (restriction.equals("record")) 	{ return Constants.stereotypeQName_Record;}
		if (restriction.equals("connector")){ return Constants.stereotypeQName_Connector;}
		if (restriction.equals("type")) 	{ return Constants.stereotypeQName_Type;}
		if (restriction.equals("function")) { return Constants.stereotypeQName_Function;}
		
		if (restriction.contains("operator")) { return Constants.stereotypeQName_Operator;}

		return "";
	}
	
	public EObject createProxyRoot(String name, final boolean applyProxyStereotype){
		
		EObject createdElement = null;
		
		if (ModelicaMLRoot != null) {
			
			final EObject modelicaMLModelRoot = ModelicaMLRoot;
			
			// find an existing root with the same name
			EList<EObject> roots = modelicaMLModelRoot.eResource().getContents();
			for (EObject root : roots) {
				if (root instanceof NamedElement) {
					String rootName = ((NamedElement)root).getName();
					Stereotype proxyStereotype = ((NamedElement)root).getAppliedStereotype(Constants.stereotypeQName_ModelicaModelProxy);
					
					if (rootName.trim().equals(name) && proxyStereotype != null) {
						return root;
					}
				}
			}
			
			// if no root is found -> create one ...
			final EObject newRoot = EcoreUtil.create(modelicaMLModelRoot.eClass());
//			final EObject newRoot = EcoreUtil.create(UMLPackage.Literals.CLASS.eClass());
			((Model)newRoot).setName(name);
			
			createdElement = newRoot;
			if (newRoot instanceof Element) {
				createdClasses.add((Element) newRoot);	
			}
			
			CompoundCommand cc = new CompoundCommand("Create a new Root Model");
			Command command = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					
					// add the new element to the modelicaml tree
					modelicaMLModelRoot.eResource().getContents().add(newRoot);
					
					// Retrieve ModelicaML profile and apply with Sub-profile
					Profile modelicamlProfile = (Profile)PackageUtil.loadPackage(URI.createURI(Constants.MODELICAML_PROFILE_URI), newRoot.eResource().getResourceSet());
					
					if( modelicamlProfile != null ) {
						
						//apply the profile
						PackageUtil.applyProfile(((org.eclipse.uml2.uml.Package)newRoot), modelicamlProfile, true);
						
						// apply proxy stereotype 
						if (applyProxyStereotype) {
							Stereotype stereotype = ((Model)newRoot).getApplicableStereotype(Constants.stereotypeQName_ModelicaModelProxy);
							if (stereotype != null) {
								((Model)newRoot).applyStereotype(stereotype);
							}
							else {
								addToLog("Could not apply the 'ModelicaModelProxy' stereotype. Please make sure that the ModelicaML profile is applied.");
//								System.err.println("Could not apply the 'ModelicaModelProxy' stereotype. Please make sure that the ModelicaML profile is applied.");
//								MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Could not apply the <<ModelicaModelProxy>> stereotype. Please make sure that the ModelicaML profile is applied.");
							}
						}
					}
					else {
						addToLog("Could not apply ModelicaML profile.");
//						System.err.println("Could not apply ModelicaML profile.");
					}
				}
			};
			
			cc.append(command);
			
			// Execute command
			editingDomain.getCommandStack().execute(cc);
		}
		else {
//			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", "Cannot access the ModelicaML root element.");
			addToLog("Cannot access the ModelicaML root element.");
//			System.err.println("Cannot access the ModelicaML root element.");

		}
		return createdElement;
	}

	
	
	// Editing domain
	private void setEditingDomain(){
		try {
			serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
			editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	// The total sleep time
	private static final int TOTAL_TIME = 1000;
	// The increment sleep time
	private static final int INCREMENT = 10;
	// process time´is unknown
	private boolean indeterminate = true; 
	
	private String progressMonitorTitle = "Modelica Model Proxies Creation ";
	private String monitorText1 = "Collecting data ...";
	private String monitorText2 = "Creating elements ...";
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		
		monitor.beginTask(progressMonitorTitle + " is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
	    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
	      Thread.sleep(INCREMENT);
	      monitor.worked(INCREMENT);
	      if (total == TOTAL_TIME / 100) {
	    	  monitor.subTask(monitorText1);
	      }
	      else {
	    	  monitor.subTask(monitorText2);
	      }
//	      if (total == TOTAL_TIME / 2) monitor.subTask(monitorText2);
	    }
	    monitor.done();
	    if (monitor.isCanceled()){
	    	throw new InterruptedException(progressMonitorTitle + " was cancelled.");
	    }   
	}

	public void setCreatedClasses(List<Element> createdClasses) {
		this.createdClasses = createdClasses;
	}

	public List<Element> getCreatedClasses() {
		return createdClasses;
	}

	public void setCreatedProperties(List<Element> createdProperties) {
		this.createdProperties = createdProperties;
	}

	public List<Element> getCreatedProperties() {
		return createdProperties;
	}

	public void setCreatedGeneralizations(List<Element> createdGeneralizations) {
		this.createdGeneralizations = createdGeneralizations;
	}

	public List<Element> getCreatedGeneralizations() {
		return createdGeneralizations;
	}

	public void setUpdatedClasses(List<Element> updatedClasses) {
		this.updatedClasses = updatedClasses;
	}

	public List<Element> getUpdatedClasses() {
		updatedClasses.removeAll(createdClasses);
		return updatedClasses;
	}

	public void setUpdatedGeneralizations(List<Element> updatedGeneralizations) {
		this.updatedGeneralizations = updatedGeneralizations;
	}

	public List<Element> getUpdatedGeneralizations() {
		return updatedGeneralizations;
	}
	
	public List<Element> getUpdatedProperties() {
		updatedProperties.removeAll(createdProperties);
		return updatedProperties;
	}

	public void setDeletedProxyQNames(List<String> deletedProxyQNames) {
		this.deletedProxyQNames = deletedProxyQNames;
	}

	public List<String> getDeletedProxyQNames() {
		return deletedProxyQNames;
	}

	public void setLog(List<String> log) {
		this.log = log;
	}

	public void addToLog(String msg) {
//		System.err.println(msg);
		this.getLog().add(msg);
	}
	
	public List<String> getLog() {
		return log;
	}

}
