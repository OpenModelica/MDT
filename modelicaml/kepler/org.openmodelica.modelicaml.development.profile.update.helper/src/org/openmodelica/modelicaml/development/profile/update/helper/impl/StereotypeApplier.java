package org.openmodelica.modelicaml.development.profile.update.helper.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.services.EditorServices;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;

public class StereotypeApplier {
	
	private HashSet<Class> classes = new HashSet<Class>();
	private HashSet<PrimitiveType> types = new HashSet<PrimitiveType>();
	private HashSet<Property> properties = new HashSet<Property>();
	
	private HashSet<Port> ports = new HashSet<Port>();
	private HashSet<Connector> connectors = new HashSet<Connector>();
	private HashSet<Dependency> dependecies = new HashSet<Dependency>();
	private HashSet<Generalization> generalizations = new HashSet<Generalization>();
	
	private HashSet<FunctionBehavior> functions = new HashSet<FunctionBehavior>();
	private HashSet<Parameter> parameters = new HashSet<Parameter>();
	
	private HashSet<OpaqueBehavior> opaqueBehaviors = new HashSet<OpaqueBehavior>();
	private HashSet<StateMachine> stateMachines = new HashSet<StateMachine>();

	// elements for which stereotype could be found
//	private List<EObject> handledElements = new ArrayList<EObject>();

	
	// elements for which no appropriate stereotype could be found
	private List<Element> unhandledElements = new ArrayList<Element>();
	

	private String mMessage = "Applying ModelicaML stereotypes";
	
//	private ServicesRegistry serviceRegistry = null;
	private TransactionalEditingDomain editingDomain = null;
	
	private ProgressMonitorDialog progressMonitor;
	
	// number of elements considered
	private int numberOfElementsConsidered = 0;





	private void clearLists(){
		classes.clear();
		types.clear();
		properties.clear();
		ports.clear();
		connectors.clear();
		dependecies.clear();
		generalizations.clear();
		functions.clear();
		parameters.clear();
		opaqueBehaviors.clear();
		stateMachines.clear();
		
		// elements for which no appropriate stereotype could be found.
		unhandledElements.clear();
	}
	
	
	
	public void applyStereotypes(final EObject root){
		if (root instanceof Element) {
			
			//clear all lists before the new run
			clearLists();
			
			// open dialog
			progressMonitor = new ProgressMonitorDialog(ModelicaMLServices.getShell());
			
			try {
				progressMonitor.run(false, true, new IRunnableWithProgress() {
					
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
						
						monitor.subTask("Collecting data ...");
						
						// collect before modifying
						collectElements(root);

						monitor.beginTask(mMessage, numberOfElementsConsidered);

						// apply stereotypes
						try {
							applyStereotypesCommand();
							
						} catch (ServiceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						monitor.done();
					}
				});
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				MessageDialog.openError(ModelicaMLServices.getShell(), "ModelicaML Stereotypes Update", "Could not invoke data collection.");
			} catch (InterruptedException e) {
				e.printStackTrace();
				MessageDialog.openInformation(ModelicaMLServices.getShell(), "ModelicaML Stereotypes Update", "Service was interrupted.");		
			}
		}
	}
	
	private void setMonitorText(EObject eObject, String string){
		if (eObject instanceof NamedElement) {
			progressMonitor.getProgressMonitor().subTask(string + eObject.eClass().getName() + " '" + ((NamedElement)eObject).getQualifiedName() + "'");
		}
	}
	
	
	private void collectElements(EObject root){
		
		setMonitorText(root, "Found ");
		
		// collect the selected element
		collectElement(root);
		
		// collect all elements underneath the selected element
		Iterator<EObject> i = root.eAllContents();
		while (i.hasNext()) {
			EObject object = i.next() ;
			collectElement(object);
			if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		}
	}
	
	
	private void collectElement(EObject object){
		if (object instanceof Class 
				&& !(object instanceof FunctionBehavior) 
				&& !(object instanceof OpaqueBehavior)
				&& !(object instanceof StateMachine) ) {
			
			setMonitorText(object, "Found ");
			classes.add((Class) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof PrimitiveType) {
			setMonitorText(object, "Found ");
			types.add((PrimitiveType) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof StateMachine) {
			setMonitorText(object, "Found ");
			stateMachines.add((StateMachine) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof FunctionBehavior) {
			setMonitorText(object, "Found ");
			functions.add((FunctionBehavior) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof OpaqueBehavior) {
			OpaqueBehavior ob = (OpaqueBehavior) object;
			Element owner = ob.getOwner();
			// make sure that not state entry/exit OpaqueBehaviors are collected, but only those owned by classes
			if (owner instanceof Class) {
				setMonitorText(object, "Found ");
				opaqueBehaviors.add((OpaqueBehavior) object);
				numberOfElementsConsidered ++;
			}
		}
		else if (object instanceof Property && !(object instanceof Port)) {
			setMonitorText(object, "Found ");
			properties.add((Property) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof Parameter) {
			setMonitorText(object, "Found ");
			parameters.add((Parameter) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof Port) {
			setMonitorText(object, "Found ");
			ports.add((Port) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof Connector) {
			setMonitorText(object, "Found ");
			connectors.add((Connector) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof Dependency) {
			setMonitorText(object, "Found ");
			dependecies.add((Dependency) object);
			numberOfElementsConsidered ++;
		}
		else if (object instanceof Generalization) {
			setMonitorText(object, "Found ");
			generalizations.add((Generalization) object);
			numberOfElementsConsidered ++;
		}
	}
	
	
	private void applyStereotypesCommand() throws ServiceException {
		
//		serviceRegistry = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry();
//		editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
		
		editingDomain = EditorServices.getPapyrusEditingDomain();
		
		CompoundCommand cc = new CompoundCommand("Unapplying ModelicaML Stereotypes");
		Command command = new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				applyStereotypes();
			}
		};
		
		cc.append(command);
		// Execute command
		editingDomain.getCommandStack().execute(cc);
	}
	
	
	
	
	/*
	 * applies stereotypes.
	 */
	private void applyStereotypes(){
		
		int numberOfUpdatedElements = 0;

		// Dependencies
		/*
		 * First set stereotypes for dependencies identification of appropriate class stereotypes
		 */
		if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		numberOfUpdatedElements += dependecies.size();
		progressMonitor.getProgressMonitor().setTaskName("(" + numberOfUpdatedElements +" of " + numberOfElementsConsidered + "): Applying stereotypes to dependecies ...");
		applyStereotypesForDependencies();
//		System.err.println("worked:" + numberOfUpdatedElements);
		progressMonitor.getProgressMonitor().worked(numberOfUpdatedElements);

		// Classes
		/*
		 * For identifying components to be instances of requirements, stereotype for classes must be set before
		 * setting stereotypes for properties.
		 */
		if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		numberOfUpdatedElements += classes.size();
		progressMonitor.getProgressMonitor().setTaskName("(" + numberOfUpdatedElements +" of " + numberOfElementsConsidered + "): Applying stereotypes to classes ...");
		applyStereotypesForClasses();
//		System.err.println("worked:" + numberOfUpdatedElements);
		progressMonitor.getProgressMonitor().worked(numberOfUpdatedElements);
		
		
		// Primitive Types
		if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		numberOfUpdatedElements += types.size();
		progressMonitor.getProgressMonitor().setTaskName("(" + numberOfUpdatedElements +" of " + numberOfElementsConsidered + "): Applying stereotypes to types  ...");
		applyStereotypesForTypes();
//		System.err.println("worked:" + numberOfUpdatedElements);
		progressMonitor.getProgressMonitor().worked(numberOfUpdatedElements);
		
		
		// Properties: variables, ports, function arguments
		if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		numberOfUpdatedElements = properties.size();
		progressMonitor.getProgressMonitor().setTaskName("(" + numberOfUpdatedElements +" of " + numberOfElementsConsidered + "): Applying stereotypes to properties ...");
		applyStereotypesForProperties();
//		System.err.println("worked:" + numberOfUpdatedElements);
		progressMonitor.getProgressMonitor().worked(numberOfUpdatedElements);
		
		
		// Behaviors: equations or algorithm sections, functions, state machines
		if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		numberOfUpdatedElements += opaqueBehaviors.size() + stateMachines.size() + functions.size();
		progressMonitor.getProgressMonitor().setTaskName("(" + numberOfUpdatedElements +" of " + numberOfElementsConsidered + "): Applying stereotypes to behvaior ...");
		applyStereotypesForBehaviors();
//		System.err.println("worked:" + numberOfUpdatedElements);
		progressMonitor.getProgressMonitor().worked(numberOfUpdatedElements);
		
		// Generalizations
		if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		numberOfUpdatedElements += generalizations.size();
		progressMonitor.getProgressMonitor().setTaskName("(" + numberOfUpdatedElements +" of " + numberOfElementsConsidered + "): Applying stereotypes to generalizations ...");
		applyStereotypesForGeneralizations();
//		System.err.println("worked:" + numberOfUpdatedElements);
		progressMonitor.getProgressMonitor().worked(numberOfUpdatedElements);
		
		//Connections
		if (progressMonitor.getProgressMonitor().isCanceled()) { return;}
		numberOfUpdatedElements += connectors.size();
		progressMonitor.getProgressMonitor().setTaskName("(" + numberOfUpdatedElements +" of " + numberOfElementsConsidered + "): Applying stereotypes to connectors ...");
		applyStereotypesForConnections();
//		System.err.println("worked:" + numberOfUpdatedElements);
		progressMonitor.getProgressMonitor().worked(numberOfUpdatedElements);
	}

	
	
	
	
	private void applyStereotypesForDependencies(){
		
		for (Dependency dep : dependecies) {
			
			// bindings relations
			if (dep.getName().toLowerCase().contains("obtains value")) {
				Stereotype s = dep.getApplicableStereotype(Constants.stereotypeQName_ObtainsValueFrom);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(dep)) {
					
					dep.applyStereotype(s);
					
					for (Element source: dep.getSources()) {
						if (source instanceof Property) {
							applyMediatorStereotype(source);
						}
					}
				}
			}
			else if(dep.getName().toLowerCase().contains("provides value")) {
				Stereotype s = dep.getApplicableStereotype(Constants.stereotypeQName_ProvidesValueFor);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(dep)) {
					dep.applyStereotype(s);
					
					for (Element source: dep.getSources()) {
						if (source instanceof Property) {
							applyMediatorStereotype(source);
						}
					}
				}
			}
			
			// scenarios to requirements relations
			else if (dep.getName().toLowerCase().contains("usetoverify") || dep.getName().toLowerCase().contains("usedtoverify")) {
				Stereotype s = dep.getApplicableStereotype(Constants.stereotypeQName_UseToVerify);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(dep)) {
					
					dep.applyStereotype(s);
					applyScenarioAndRequirementStereotypes(dep);
					
				}
			}
			else if(dep.getName().toLowerCase().contains("donotusetoverify")) {
				Stereotype s = dep.getApplicableStereotype(Constants.stereotypeQName_DoNotUseToVerify);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(dep)) {
					dep.applyStereotype(s);
					applyScenarioAndRequirementStereotypes(dep);				
				}
			}
			
			// additional models
			else if(dep.getName().toLowerCase().contains("requires")) {
				Stereotype s = dep.getApplicableStereotype(Constants.stereotypeQName_Requires);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(dep)) {
					dep.applyStereotype(s);
					applyScenarioAndRequirementStereotypes(dep);				
				}
			}
			else if(dep.getName().toLowerCase().contains("requiredfor")) {
				Stereotype s = dep.getApplicableStereotype(Constants.stereotypeQName_RequiredFor);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(dep)) {
					dep.applyStereotype(s);
					applyScenarioAndRequirementStereotypes(dep);				
				}
			}
			
			// import
			else if(dep.getName().toLowerCase().contains("import")) {
				Stereotype s = dep.getApplicableStereotype(Constants.stereotypeQName_ImportRelation);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(dep)) {
					dep.applyStereotype(s);
				}
			}
			else {
				addToUnhandledElements(dep);
			}
		}
	}
	

	
	private void applyStereotypesForClasses(){
		for (Class clazz : classes) {
			
			// verification model
			progressMonitor.getProgressMonitor().subTask("Identifying a verificaiton model ...");
			if (clazz.getName().startsWith(Constants.automaticScenarioBasedVerificationModelsNamePrefix)
					|| clazz.getName().startsWith(Constants.relationDiscoveryModelsNamePrefix)) {
				
				Stereotype s = clazz.getApplicableStereotype(Constants.stereotypeQName_Model);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(clazz, Constants.stereotypeQName_Model)) {
					clazz.applyStereotype(s);
				}
				
				Stereotype sVem = clazz.getApplicableStereotype(Constants.stereotypeQName_VerificationModel);
				if (sVem!= null && !ModelicaMLServices.isModelicaMLStereotypeApplied(clazz, Constants.stereotypeQName_VerificationModel)) {
					clazz.applyStereotype(sVem);
				}
				Stereotype sSimulation = clazz.getApplicableStereotype(Constants.stereotypeQName_Simulation);
				if (sSimulation!= null && !ModelicaMLServices.isModelicaMLStereotypeApplied(clazz, Constants.stereotypeQName_Simulation)) {
					clazz.applyStereotype(sSimulation);
				}
			}
			
			// requirement
			progressMonitor.getProgressMonitor().subTask("Identifying a requirement ...");
			if (hasReqStatusAttribute(clazz)){
				Stereotype sRequirement = clazz.getApplicableStereotype(Constants.stereotypeQName_Requirement);
				if (sRequirement!= null && !ModelicaMLServices.isModelicaMLStereotypeApplied(clazz, Constants.stereotypeQName_Requirement)) {
					clazz.applyStereotype(sRequirement);
				}
			}
			
			// connectors
			progressMonitor.getProgressMonitor().subTask("Identifying a connector ...");
			if (ownerNameContains(clazz, "interface")) {
				Stereotype sConnector = clazz.getApplicableStereotype(Constants.stereotypeQName_Connector);
				if (sConnector!= null && !ModelicaMLServices.isModelicaMLStereotypeApplied(clazz, Constants.stereotypeQName_Connector)) {
					clazz.applyStereotype(sConnector);
				}
			}
			
			// default stereotype if no appropriate stereotype could be found
			Stereotype s = clazz.getApplicableStereotype(Constants.stereotypeQName_Model);
			if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(clazz)) {
				clazz.applyStereotype(s);
			}
			else {
				addToUnhandledElements(clazz);
			}
		}
	}
	

	private void applyStereotypesForTypes(){
		for (PrimitiveType type : types) {
			Stereotype s = type.getApplicableStereotype(Constants.stereotypeQName_Type);
			if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(type)) {
				type.applyStereotype(s);
			}
			else {
				addToUnhandledElements(type);
			}
		}
	}
	
	private void applyStereotypesForBehaviors(){
		for (OpaqueBehavior behavior : opaqueBehaviors) {
			
			String name = behavior.getName();
			
			if (name.startsWith("alg") || name.toLowerCase().contains("algorithm")) {
				Stereotype s = behavior.getApplicableStereotype(Constants.stereotypeQName_Algorithm);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(behavior, Constants.stereotypeQName_Algorithm)) {
					behavior.applyStereotype(s);
				}
			}
			else if (name.startsWith("eq") || name.toLowerCase().contains("equation")) {
				Stereotype s = behavior.getApplicableStereotype(Constants.stereotypeQName_Equations);
				if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(behavior, Constants.stereotypeQName_Equations)) {
					behavior.applyStereotype(s);
				}
			}
			else {
				addToUnhandledElements(behavior);
			}
		}
		
		// state machines
		for (StateMachine sm: stateMachines) {
			Stereotype s = sm.getApplicableStereotype(Constants.stereotypeQName_ModelicaMLStateMachine);
			if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(sm, Constants.stereotypeQName_ModelicaMLStateMachine)) {
				sm.applyStereotype(s);
			}
			else {
				addToUnhandledElements(sm);
			}
		}
		
		// functions
		for (FunctionBehavior function: functions) {
			Stereotype s = function.getApplicableStereotype(Constants.stereotypeQName_Function);
			if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(function, Constants.stereotypeQName_Function)) {
				function.applyStereotype(s);
			}
			else {
				addToUnhandledElements(function);
			}
		}
	}
	
	
	private void applyStereotypesForGeneralizations(){
		for (Generalization generalization: generalizations) {
			Stereotype sExtends = generalization.getApplicableStereotype(Constants.stereotypeQName_ExtendsRelation);
			Stereotype sExtendsType = generalization.getApplicableStereotype(Constants.stereotypeQName_TypeRelation);
			if (generalization.getGeneral() instanceof PrimitiveType) {
				if (sExtendsType != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(generalization, Constants.stereotypeQName_TypeRelation)) {
					generalization.applyStereotype(sExtendsType);
				}
			}
			else if (sExtends != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(generalization, Constants.stereotypeQName_ExtendsRelation)) {
				generalization.applyStereotype(sExtends);
			}
			else {
				addToUnhandledElements(generalization);
			}
		}
	}
	
	private void applyStereotypesForConnections(){
		for (Connector connection : connectors) {
			Stereotype s = connection.getApplicableStereotype(Constants.stereotypeQName_Connection);
			if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(connection, Constants.stereotypeQName_Connection)) {
				connection.applyStereotype(s);
			}
			else {
				addToUnhandledElements(connection);
			}
		}
	}
	
	private void applyStereotypesForProperties(){

		// ports
		progressMonitor.getProgressMonitor().subTask("Applying stereotypes to ports ...");
		for (Port port : ports) {
			Stereotype s = port.getApplicableStereotype(Constants.stereotypeQName_ConnectionPort);
			if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(port, Constants.stereotypeQName_ConnectionPort)) {
				port.applyStereotype(s);
			}
			else {
				addToUnhandledElements(port);
			}
		}
		
		// variables, components, etc.
		progressMonitor.getProgressMonitor().subTask("Applying stereotypes to variables and components ...");
		for (Property property : properties) {
			
			Stereotype sVariable = property.getApplicableStereotype(Constants.stereotypeQName_Variable);
			Stereotype sComponent = property.getApplicableStereotype(Constants.stereotypeQName_Component);
//			Stereotype sCalculatedProperty= property.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);
			Stereotype sRequirementInstance= property.getApplicableStereotype(Constants.stereotypeQName_RequirementInstance);
			
			Type type = property.getType();
			
			if (type instanceof PrimitiveType) {
				if (sVariable != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(property)) {
					property.applyStereotype(sVariable);
				}
			}
			else if(type != null && type.getAppliedStereotype(Constants.stereotypeQName_Requirement) != null){
				if (sRequirementInstance !=null && !ModelicaMLServices.isModelicaMLStereotypeApplied(property)) {
					property.applyStereotype(sRequirementInstance);
				}
			}
			else if (sComponent != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(property)) {
				property.applyStereotype(sComponent);
			}
			else {
				addToUnhandledElements(property);
			}
		}
		
		// functions arguments
		progressMonitor.getProgressMonitor().subTask("Applying stereotypes to function parameters ...");
		for (Parameter parameter : parameters) {
			Stereotype s = parameter.getApplicableStereotype(Constants.stereotypeQName_FunctionArgument);
			if (s != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(parameter)) {
				parameter.applyStereotype(s);
				if (parameter.getDirection().equals(ParameterDirectionKind.IN_LITERAL)) {
					parameter.setValue(s, Constants.propertyName_causality, "input");
				}
				if (parameter.getDirection().equals(ParameterDirectionKind.OUT_LITERAL)) {
					parameter.setValue(s, Constants.propertyName_causality, "output");
				}
			}
			else {
				addToUnhandledElements(parameter);
			}
		}
	}
	
	private void applyMediatorStereotype(Element element){
		
		progressMonitor.getProgressMonitor().subTask("Applying stereotypes to mediators ...");
		if (element instanceof Property) {
			
			// apply mediator 
			Stereotype sMediator = element.getApplicableStereotype(Constants.stereotypeQName_ValueMediator);
			if (sMediator != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(element, Constants.stereotypeQName_ValueMediator)) {
				element.applyStereotype(sMediator);								
			}
			
			// apply mediator container
			Element container = element.getOwner();
			if (container instanceof Class) {
				Stereotype sMediatorConainer = container.getApplicableStereotype(Constants.stereotypeQName_ValueMediatorsContainer);
				if (sMediatorConainer != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(container, Constants.stereotypeQName_ValueMediatorsContainer)) {
					container.applyStereotype(sMediatorConainer);								
				}	
			}
		}
	}
	
	private void applyScenarioAndRequirementStereotypes(Dependency dep){
		progressMonitor.getProgressMonitor().subTask("Applying stereotypes to scenarios and requirements ...");
		// Scenario
		for (Element source: dep.getClients()) {
			if (source instanceof Class) {
				Stereotype sScenario = source.getApplicableStereotype(Constants.stereotypeQName_VerificationScenario);
				Stereotype sCalculationModel = source.getApplicableStereotype(Constants.stereotypeQName_CalculationModel);
				if (sScenario != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(source, Constants.stereotypeQName_VerificationScenario)) {
					source.applyStereotype(sScenario);
				}
				if (sCalculationModel != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(source, Constants.stereotypeQName_CalculationModel)) {
					source.applyStereotype(sCalculationModel);
				}
			}
		}
		// Requirement
		for (Element target: dep.getSuppliers()) {
			if (target instanceof Class) {
				Stereotype sRequierment = target.getApplicableStereotype(Constants.stereotypeQName_Requirement);
				if (sRequierment != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(target, Constants.stereotypeQName_Requirement)) {
					target.applyStereotype(sRequierment);
				}
			}
		}
	}
	
	
	private boolean hasReqStatusAttribute(Element element){

		List<Property> statusProperties = new ArrayList<Property>();
		
		if (element instanceof Class) {
			List<Property> properties = ((Class)element).getAllAttributes();
			for (Property property : properties) {
				Type type = property.getType();
				if (property.getName().equals(Constants.propertyName_mStatus)) {
					
					if (type instanceof PrimitiveType && type.getName().equals(Constants.predefinedTypeName_integer)) {
						statusProperties.add(property);						
					}
				}
			}
		}
		
		if (statusProperties.size() == 1) {
			
			// set causality of the status attribute
			Property mStatusProperty = statusProperties.get(0);
			Stereotype sVariable = mStatusProperty.getApplicableStereotype(Constants.stereotypeQName_Variable);
			if (sVariable != null && !ModelicaMLServices.isModelicaMLStereotypeApplied(mStatusProperty, Constants.stereotypeQName_Variable)) {
				mStatusProperty.applyStereotype(sVariable);
			}
			mStatusProperty.setValue(sVariable, Constants.propertyName_causality, Constants.propertyName_output);
			
			return true;
		}
		
		return false;
	}
	
	private boolean ownerNameContains(Element element, String string){
		Element owner = element.getOwner();
		if (owner instanceof NamedElement) {
			String name = ((NamedElement)owner).getName();
			if (name.toLowerCase().contains(string)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	private void addToUnhandledElements(Element element){
		if (!(element instanceof Package) && !ModelicaMLServices.isModelicaMLStereotypeApplied(element)) {
			unhandledElements.add(element);
		}
	}
	
	
	/*
	 * Getters
	 */
	
	public List<EObject> getConsideredElements(){
		
		List<EObject> elements = new ArrayList<EObject>();
		
		elements.addAll(classes);
		elements.addAll(properties);
		elements.addAll(ports);
		elements.addAll(connectors);
		elements.addAll(dependecies);
		elements.addAll(generalizations);
		elements.addAll(functions);
		elements.addAll(parameters);
		elements.addAll(opaqueBehaviors);
		elements.addAll(stateMachines);
		
		return elements;
	}
	
	public List<Element> getUnhandledElements() {
		return unhandledElements;
	}
	
	public int getNumberOfElementsConsidered() {
		return numberOfElementsConsidered;
	}
	
}
