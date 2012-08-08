package org.openmodelica.modelicaml.helper.generators;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.instantiation.ClassInstantiation;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.ModelicaMLServices;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.helper.datacollection.VerificationDataCollector;
import org.openmodelica.modelicaml.helper.structures.VeMReqCombinationsCreator;

public class GeneratorVeMRequirementsBased extends Observable implements IRunnableWithProgress {
	
	public GeneratorVeMRequirementsBased(
			HashSet<Element> sourceModels,
			Element targetPackage, 
			Element requirementsPackage,
			Element testScenariosPackage, 
			Element valueMediatorsPackage,
			Element superClass,
			boolean includeRequirementsWithPositiveRelations,
			boolean includeRequirementsWithNegativeRelations,
			boolean includeRequirementsWitnUnknownRelations
			) {
		
		super();
		
		this.sourceModels = sourceModels;
		this.targetPackage = targetPackage;
//		this.requirementsPackage = requirementsPackage;
		this.valueBindingsPackage = valueMediatorsPackage;
		this.superClass = superClass;
	}
	

	public GeneratorVeMRequirementsBased() {
		super();
	}
	
	// the selected model to generate the simulation models for
	private HashSet<Element> sourceModels = null;

	// the package for simulation models
	private Element targetPackage = null;
	
	// the package to containing the requirements to be used
//	private Element requirementsPackage = null;

	// the package to containing the value mediators to be used
	private Element valueBindingsPackage = null;
	
	// super class that each generated model should inherit from
	private Element superClass = null;
	
//	private RequirementsCollector rc;

	// all requirements that were collected from the specified requirements package
	private HashSet<Element> allRequirements = new HashSet<Element>();

	/* all requirements that were selected for instantiation because 
	 * the selected test scenarios reference them and all their clients are satisfied by the selected system model
	 */
	private HashSet<Element> requirementsToBeInstantiated = new HashSet<Element>();
	/* all requirements that were discarded because  
	 * some of their clients are NOT satisfied by the selected system model.
	 */
	private HashSet<Element> requirementsDiscarded = new HashSet<Element>();
	
	// messages collected during translation
	private String log = "";
	
	// indicated if the generation was canceled by the user.
	private boolean canceled = false;
	
	VeMReqCombinationsCreator rsmc;
	VerificationDataCollector collector;
	
	// indicates if there was at at least one model for which bindings could not be generated 
	private boolean bindingErrorsDetected = false;
	
	public void generate(){
		
		if (sourceModels != null) {

			/* Collect all requirements in order to be able to determine 
			 * which are not covered by simulation models.
			 */

			collector = new VerificationDataCollector(ModelicaMLServices.getCommonRootModel(sourceModels));
			allRequirements.addAll(collector.getAllRequirements());
			
			/* For each of the selected system models create a package containing classes that 
			 * instantiate all possible combinations of test scenarios and requirements that 
			 * can be tested using the given test scenario.
			 */
			for (Element sourceModel : sourceModels) {
				// clear all lists because the translation for each source model is individual.
				clearAllLists();
				
				try {
					// create combinations
					new ProgressMonitorDialog(ModelicaMLServices.getShell()).run(true, true, this);
					createCombinationsForSimulationModels();

				} catch (InvocationTargetException e) {
					e.printStackTrace();
					MessageDialog.openError(ModelicaMLServices.getShell(), "Simulation Models Generation Process Error", "It was not possible to invoce the generation of simulation models operation.");
				} catch (InterruptedException e) {
					e.printStackTrace();
					MessageDialog.openError(ModelicaMLServices.getShell(), "Simulation Models Generation Process Abort", "The generation of simulation models was canceled.");
				}
				
				// generate simulation models for the source model 
				generateSimulationModels(sourceModel);
			}
		}
	}
	
	
	private void clearAllLists(){
		requirementsToBeInstantiated.clear();
		requirementsDiscarded.clear();
	}
	
	public void createCombinationsForSimulationModels(){
		monitorText1 = "Collecting data ...";
		monitorText2 = "Analyzing combinations ...";
		
		for (Element sourceModel : this.sourceModels) {
			
			if (sourceModel instanceof Class) {
				
				Class systemModel = (Class) sourceModel;
				
				if (collector.getAllRequirements().size() == 0) {
					String message = "INFO: No requirements were found.";
					addToLog(message);
				}

				HashSet<Class> requirementsToBeUsed = new HashSet<Class>();
				for (Element req : collector.getAllRequirements()) {
					if (req instanceof Class) {
						requirementsToBeUsed.add((Class) req);
					}
					else {
						String message = "NOT VALID: Requirement '" + req.toString() + "' is not a UML::Class";
						addToLog(message);
					}
				}
				
				rsmc = new VeMReqCombinationsCreator(systemModel, 
						requirementsToBeUsed,
						(Package) valueBindingsPackage,
						collector.getAlwaysInclude(),
						collector.getModelToItsRequiredModels(),
						null,
						collector
						);
				
				// add to selected or discarded requirements
				HashSet<Class> requirements = rsmc.getRequirements();
				for (Element requirement : requirements) {
					if (rsmc.getUnsatisfiedRequiredClients(requirement) != null) {
						requirementsDiscarded.add(requirement);
					}
					else {
						requirementsToBeInstantiated.add(requirement);
					}
				}

				// add log messages
				addToLog(rsmc.getLog().trim());
				
			}
			else {
				String message = "NOT VALID: System model '" + sourceModel.toString() + "' is not a UML Class.";
				addToLog(message);
			}
		}
	}
	
	private void generateSimulationModels(Element sourceModel){
		
		String errorTitle = "Verification Models Generation Helper";
		String errorMessage = "No requirements were found that can be verified for " +
				"'"+((NamedElement)sourceModel).getName()+"'.";

		if (collector.getAllRequirements().size() > 0) {
			createSimulationModels(sourceModel);
		}
		else {
			reportError(errorTitle, errorMessage);
		}
	}
	
	private void reportError(String title, String message){
		MessageDialog.openError(ModelicaMLServices.getShell(), title, message);
	}
	
	public void createSimulationModels(Element systemModel){
		
		clearLog();
		
		monitorText1 = "Preparing models to be created ...";
		monitorText2 = "Creating models...";

		String pkgName = Constants.simModelsPackageNamePrefix + ((NamedElement)systemModel).getName();
		String postFix = ModelicaMLServices.getNamePostFix((Package)targetPackage, pkgName);
		PackageableElement simulationModelsPackage = ((Package)targetPackage).createPackagedElement(pkgName + postFix,UMLPackage.Literals.PACKAGE);

				
		// create the verification (simulation) model class
		String simulationModelName = Constants.simModelsNamePrefix + ((NamedElement)systemModel).getName();
		Class simulationModel = ((Package)simulationModelsPackage).createOwnedClass(simulationModelName, false);
		
		// create extends relation if specified
		if (superClass instanceof Class) {
			Generalization extendsRelation = simulationModel.createGeneralization((Classifier) superClass);
			if (extendsRelation != null) {
				Stereotype stereotypeExtends = extendsRelation.getApplicableStereotype(Constants.stereotypeQName_ExtendsRelation);
				if (stereotypeExtends != null) {
					extendsRelation.applyStereotype(stereotypeExtends);
				}
			}
		}
		

		Stereotype s_model = simulationModel.getApplicableStereotype(Constants.stereotypeQName_Model);
		simulationModel.applyStereotype(s_model);

		Stereotype s_test = simulationModel.getApplicableStereotype(Constants.stereotypeQName_VerificationModel);
		simulationModel.applyStereotype(s_test);

		//************************************************************************************
		// add system model property
		Property p_systemModel = simulationModel.createOwnedAttribute(
				Constants.systemModelPropertyNamePrefix 
				+ StringUtls.replaceSpecChar(((NamedElement)systemModel).getName().toLowerCase()), 
				(Type)systemModel);
		Stereotype s_component = p_systemModel.getApplicableStereotype(Constants.stereotypeQName_Component);
		if (s_component != null) {
			p_systemModel.applyStereotype(s_component);
		}
		else {
			String msg = "ERROR: Cannot apply the stereotype 'Component' to '" 
						+ p_systemModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
			addToLog(msg);
		}

		/*
		 * Add additional models that are required by the system model.
		 * Hereby, prune all that are not used, i.e. those that don't have at least
		 * one provider used in the combination.
		 */
		
		createAdditionalModels(simulationModel, rsmc.getAdditionalSystemModelModels(true), 
				Constants.systemModelPropertyNamePrefix + Constants.additionalModelPrefix);
		
		//************************************************************************************
		
		// add requirements
		InstantiatorRequirements ri = new InstantiatorRequirements();
		HashSet<Element> reqList = new HashSet<Element>();

		reqList.addAll(rsmc.getValidRequirements());
		List<Element> requirements_sorted = ModelicaMLServices.getSortedByName(reqList);
		
		
		// reset the indication of if bindings could not be generated
		setBindingErrorsDetected(false);
		
		for (Element requirement : requirements_sorted) {
			if (requirement instanceof Classifier) {
			
				// get the number of required instantiations
				int requiredNumberOfInstantions = ri.getMaxNumberOfProviders(simulationModel, (Class) requirement, collector.getAllMediators());
				String requiredNumberOfInstantionsString = "";
				
				for (int i = 0; i < requiredNumberOfInstantions; i++) {
				
					if (requiredNumberOfInstantions > 1) {
						requiredNumberOfInstantionsString = String.valueOf(i) + "_";
					}
					Property p_req = simulationModel.createOwnedAttribute(
							Constants.reqirementPropertyNamePrefix + 
							ModelicaMLServices.getRequirementID((NamedElement) requirement) + "_" + 
							requiredNumberOfInstantionsString +
							StringUtls.replaceSpecChar(((NamedElement)requirement).getName().toLowerCase()), 
							(Type) requirement);
					
					Stereotype s_reqInst = p_req.getApplicableStereotype(Constants.stereotypeQName_RequirementInstance);
					
					if (s_reqInst != null) {
						p_req.applyStereotype(s_reqInst);
					}
					else {
						String msg = "ERROR: Cannot apply the stereotype 'requirementInstantance' to '" + p_req.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
						addToLog(msg);
					}
				}
				
				/*
				 * Add additional models that are required by the requirement model.
				 * Hereby, prune all that are not used, i.e. those that don't have at least
				 * one provider used in the combination.
				 */
				createAdditionalModels(simulationModel, 
						rsmc.getAdditionalRequirementModels(requirement, true), 
						Constants.reqirementPropertyNamePrefix
							+ ModelicaMLServices.getRequirementID((NamedElement) requirement) 
							+ "_" + Constants.additionalModelPrefix
						);
			}
		}
		
		// add to log
		addToLog("\n" + rsmc.getLog());
		
		/* 
		 * Instantiate the created simulation model class
		 * (it now contains all components)
		 */
		ClassInstantiation ci = new ClassInstantiation((Class) simulationModel, true, false);
		// pass pre-collected mediators in order to avoid another search
		ci.setAllMediators(collector.getAllMediators());
		ci.createTree();
		ci.collectValueClientsAndProvidersFromUmlModel();
		
		// update all bindings in the created simulation model class
		CreatorValueBinding vbc = new CreatorValueBinding();
		vbc.setAllMediators(collector.getAllMediators());
		
		/* Note, the updateAllBindings() is called with the last argument simulateOnly = false  
		 * so that modifications ARE created in components.
		 */
		vbc.updateAllBindings((Package)valueBindingsPackage, ci, ci.getTreeRoot(), ci.getTreeRoot(), false, true, false, false);
		
		/*
		 * Determine if there were models for which no correct bindings could be generated
		 */
		HashSet<TreeObject> allMandClients = vbc.getAllRequiredClientsFound();
		HashSet<TreeObject> allMandClientsWithPossibleBindins = vbc.getAllClientsWithPossibleBindingCodeDerivation();
		
		if (!allMandClientsWithPossibleBindins.containsAll(allMandClients)) {
			setBindingErrorsDetected(true);
		}
		
		/*
		 * Create verdict code
		 */
		CreatorVerificationVerdictElements.createVerificationVerdictElements(simulationModel);

	}
	
	
	
	private void createAdditionalModels(Class owningClass, HashSet<Element> additionalModels, String namePrefix){
		if (additionalModels != null && additionalModels.size() > 0) {
			List<Element> addtitionalModels_sorted = ModelicaMLServices.getSortedByName(additionalModels);
			
			for (int i = 0; i < addtitionalModels_sorted.size(); i++) {
				Element additionalModel= addtitionalModels_sorted.get(i);
				
				// create property
				Property p_additionalModel = owningClass.createOwnedAttribute(
						namePrefix + i + "_" + StringUtls.replaceSpecChar(((NamedElement)additionalModel).getName().toLowerCase()), 
						(Type)additionalModel);
				
				// apply stereotype
				Stereotype s_additionalModel= null;
				if (additionalModel.getAppliedStereotype(Constants.stereotypeQName_CalculationModel) != null
						|| additionalModel.getAppliedStereotype(Constants.stereotypeQName_VerificationScenario) != null) {
					
					s_additionalModel = p_additionalModel.getApplicableStereotype(Constants.stereotypeQName_CalculatedProperty);	
				}
				else {
					s_additionalModel = p_additionalModel.getApplicableStereotype(Constants.stereotypeQName_Component);
				}
				if (s_additionalModel != null) {
					p_additionalModel.applyStereotype(s_additionalModel);
				}
				else {
					String msg = "ERROR: Cannot apply the required stereotype to '" 
						+ p_additionalModel.getQualifiedName() + "'. Check if the ModelicaML profile is applied.";
					addToLog(msg);
				}
			}
		}
	}

	
	
	private void addToLog(String msg){
		this.log = this.log + msg + "\n";
	}

	private void clearLog(){
		this.log = "";
	}
	
	// GETTERS ############################################
	
	public String getLog() {
		return log;
	}
	
	public HashSet<Element> getAllRequirements() {
		return allRequirements;
	}

	public void setAllRequirements(HashSet<Element> allRequirements) {
		this.allRequirements = allRequirements;
	}

	public HashSet<Element> getRequirementsToBeInstantiated() {
		return requirementsToBeInstantiated;
	}

	public void setRequirementsToBeInstantiated(
			HashSet<Element> requirementsToBeInstantiated) {
		this.requirementsToBeInstantiated = requirementsToBeInstantiated;
	}

	public HashSet<Element> getRequirementsDiscarded() {
		return requirementsDiscarded;
	}

	public void setRequirementsDiscarded(HashSet<Element> requirementsDiscarded) {
		this.requirementsDiscarded = requirementsDiscarded;
	}
	
	public void setTestSimulationModelGenerationCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public boolean isTestSimulationModelGenerationCanceled() {
		return canceled;
	}
	
	public Element getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(Element targetPackage) {
		this.targetPackage = targetPackage;
	}

	
	// The total sleep time
	private static final int TOTAL_TIME = 100;
	// The increment sleep time
	private static final int INCREMENT = 10;
	// process time´is unknown
	private boolean indeterminate = true; 
	
	private String progressMonitorTitle = "Test Simulation Models Generator ";
	private String monitorText1 = "Collecting data ...";
	private String monitorText2 = "Analyzing combinations ...";
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		
		if (!this.isTestSimulationModelGenerationCanceled()) {
			monitor.beginTask(progressMonitorTitle + " is running." , indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
		    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
		      Thread.sleep(INCREMENT);
		      monitor.worked(INCREMENT);
		      if (total == TOTAL_TIME / 4) monitor.subTask(monitorText1);
		      if (total == TOTAL_TIME / 2) monitor.subTask(monitorText2);
		    }
		    monitor.done();
		    if (monitor.isCanceled()){
		    	throw new InterruptedException(progressMonitorTitle + " was cancelled.");
		    }   
		}
	}


	public boolean isBindingErrorsDetected() {
		return bindingErrorsDetected;
	}


	public void setBindingErrorsDetected(boolean bindingErrorsDetected) {
		this.bindingErrorsDetected = bindingErrorsDetected;
	}
}
