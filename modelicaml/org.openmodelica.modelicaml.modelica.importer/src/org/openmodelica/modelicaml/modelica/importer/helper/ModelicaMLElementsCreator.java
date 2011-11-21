package org.openmodelica.modelicaml.modelica.importer.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
import org.eclipse.papyrus.umlutils.PackageUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
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
	
	
	public void createElements(Element parent, TreeParent treeParent, boolean update, boolean applyProxyStereotype){
		// clear the log
		this.log.clear();
		
		// first create classes, because they will type the properties
		createClasses(parent, treeParent, update, applyProxyStereotype);
		// create properties and extends relations
		createPropertiesAndGeneralizations(treeParent, update, applyProxyStereotype);
	}
	
	
	public void deleteNotUsedProxyElements(){
		/* 
		 * Delete all proxies or their owned elements 
		 * that are not contained in the loaded Modelica models. 
		 */
		
		CompoundCommand cc = new CompoundCommand("Delete ModelicaML Proxy");
		Command command = new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				
				// (re)collect proxies 
//				treeBuilder.collectModelicaModelProxies();
				
				HashSet<String> modelicaModelQNames = new HashSet<String>();
				for (TreeObject treeObject : treeBuilder.getTreeItems()) {
					modelicaModelQNames.add(treeObject.getQName());
				}
				
				for (Element element : treeBuilder.getProxies()) {
					if (element instanceof NamedElement) {

						// don't delete predefined types.
						if (!(element instanceof PrimitiveType && isPredefinedModelicaType(element)) ) {
							String qName = StringUtls.replaceSpecCharExceptThis(((NamedElement)element).getQualifiedName(), "::").replaceAll("::", ".");
							if (!modelicaModelQNames.contains(qName)) {
								deletedProxyQNames.add(((NamedElement)element).getQualifiedName());
								addToLog("Deleting " + ((NamedElement)element).getQualifiedName());
								element.destroy();
							}
						}
					}
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
	
	
	
	public void createPropertiesAndGeneralizations(TreeParent treeParent, boolean update, boolean applyProxyStereotype){

		Element owningClass = treeParent.getModelicaMLProxy();
		
		if (treeParent instanceof ClassItem && treeParent.hasChildren()) {
			TreeObject[] children = treeParent.getChildren();
			
			for (int i = 0; i < children.length; i++) {
				TreeObject treeObject = children[i];
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
				
				// recursive  call
				if (treeObject instanceof ClassItem) {
					createPropertiesAndGeneralizations((TreeParent) treeObject, update, applyProxyStereotype);
				}
			}
		}
	}
	
	
	public void createClasses(Element parent, TreeParent treeParent, boolean update, boolean applyProxyStereotype){

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
//								System.out.println("Updating component: " + ((NamedElement)modelicaMLProxy).getQualifiedName());
								addToLog("Updating component: " + ((NamedElement)modelicaMLProxy).getQualifiedName());
							}
							// update
							updateClass(modelicaMLProxy, (ClassItem) treeObject, applyProxyStereotype);			
						}
					}
					
					// recursive  call
					if (modelicaMLProxy instanceof NamedElement) {
						createClasses(modelicaMLProxy, (TreeParent) treeObject, update, applyProxyStereotype);
					}
				}
			}
		}
	}
	
	private Element createClass(
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
				if (parent instanceof Package) {
//					createdElement = ((Package)parent).createOwnedClass(treeObject.getName(), isPartial);
					if (classItem.getClassRestriction().equals("function")) {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.FUNCTION_BEHAVIOR);
					}
					else if (classItem.getClassRestriction().equals("type")) {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.PRIMITIVE_TYPE);
					}
					else if (classItem.getClassRestriction().equals("enumeration")) {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.ENUMERATION);
					}
					else {
						createdElement = ((Package)parent).createPackagedElement(classItem.getName(), UMLPackage.Literals.CLASS);
					}
				}
				else if (parent instanceof Class) {
					if (classItem.getClassRestriction().equals("function")) {
						createdElement = ((Class)parent).createNestedClassifier(classItem.getName(), UMLPackage.Literals.FUNCTION_BEHAVIOR);
					}
					else if (classItem.getClassRestriction().equals("type")) {
						createdElement = ((Class)parent).createNestedClassifier(classItem.getName(), UMLPackage.Literals.PRIMITIVE_TYPE);
					}
					else if (classItem.getClassRestriction().equals("enumeration")) {
						createdElement = ((Class)parent).createNestedClassifier(classItem.getName(), UMLPackage.Literals.ENUMERATION);
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
//					MessageDialog.openError(new Shell(), "Error", "Could not apply ModelicaML stereotype '"+treeObject.getClassRestriction()+"'. Please make sure that the ModelicaML profile is applied.");
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
	
	private Element createProperty(final Element owningClass, final ComponentItem componentTreeObject, final boolean applyProxyStereotype){
		
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

				if (componentTreeObject.isPort()) {
					createdElement = ((Class)owningClass).createOwnedPort(componentTreeObject.getName(), (Type) componentTreeObject.getComponentTypeProxy());
				}
				else if (componentTreeObject.isFunctionArgument()) {
					createdElement = ((FunctionBehavior)owningClass).createOwnedParameter(componentTreeObject.getName(), (Type) componentTreeObject.getComponentTypeProxy());
				}
				else if (componentTreeObject.isEnumarationLiteral()) {
					createdElement = ((Enumeration)owningClass).createOwnedLiteral(componentTreeObject.getName());
				}
				else {
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
				if (!extendsRelationElement.isStereotypeApplied(appropriateStereotype)) {
					extendsRelationElement.applyStereotype(appropriateStereotype);	
				}
				
				if (appropriateStereotype != null) {
					// set modifications
					if (extendsRelationItem.getModifications() != null) {
						extendsRelationElement.setValue(appropriateStereotype, Constants.propertyName_modification, extendsRelationItem.getModifications());
					}
					
					// set array size 
					if (appropriateStereotype.getQualifiedName().equals(Constants.stereotypeQName_Type)
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
	
	
	private Element updateClass(
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
	
	
	
	private Element updateProperty(
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
			else if (!componentItem.isComponent() && componentItem.getInnerouter().equals("parameter")) {
				Object enumeration = componentElement.getModel().getAppliedProfile(Constants.predefinedEnumerationsProfileQName).getOwnedMember("ModelicaVariability", true, UMLPackage.Literals.ENUMERATION);
				if (enumeration instanceof Enumeration) {
					EnumerationLiteral literal = ((Enumeration)enumeration).getOwnedLiteral("parameter");
					componentElement.setValue(componentStereotype, Constants.propertyName_variability, literal);
				}
			}
			else if (!componentItem.isComponent() && componentItem.getInnerouter().equals("constant")) {
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
		else {
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
//								MessageDialog.openError(new Shell(), "Error", "Could not apply the <<ModelicaModelProxy>> stereotype. Please make sure that the ModelicaML profile is applied.");
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
//			MessageDialog.openError(new Shell(), "Error", "Cannot access the ModelicaML root element.");
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
		System.err.println(msg);
		this.getLog().add(msg);
	}
	
	public List<String> getLog() {
		return log;
	}

}
