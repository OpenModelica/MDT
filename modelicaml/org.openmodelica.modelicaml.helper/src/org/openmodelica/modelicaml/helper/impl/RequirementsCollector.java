package org.openmodelica.modelicaml.helper.impl;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;

public class RequirementsCollector {

	// all requirements that are found
	private HashSet<Element> allRequirements = new HashSet<Element>();
	
	// additional models that should always be instantiated
	private HashSet<Element> alwaysInclude = new HashSet<Element>();

	// models to their additional models
	private HashMap<Element,HashSet<Element>> modelToItsRequiredModels = new HashMap<Element,HashSet<Element>>();

	// selected requirements that were provided for finding matching test cases.
	private HashSet<Element> selectedReq = null;

	// UML root model
	private Model umlRoolModel = null;
	
	
	// Constructors
	public RequirementsCollector(){}
	
	public RequirementsCollector(Model model){
		this.umlRoolModel = model;
	}
	
	public boolean collectRequirementsFromModel(){
		
		// clear the list in order to enable multiple calls of this method in the the same object
		this.allRequirements.clear();
		this.alwaysInclude.clear();
		this.modelToItsRequiredModels.clear();
		
		if ( umlRoolModel != null ) {
			
			VerificationDataCollector ec = new VerificationDataCollector(umlRoolModel);
			
			this.alwaysInclude.addAll(ec.getAlwaysInclude());
			this.modelToItsRequiredModels.putAll(ec.getModelToItsRequiredModels());
			
			this.allRequirements.addAll(ec.getAllRequirements());
			
			return true; // successful execution
		}
		
		return false;
	}
	
	public boolean collectRequirementsFromPackage(Package rootPackage){
		// clear the list in order to enable multiple calls of this method in the the same object
		this.alwaysInclude.clear();
		this.modelToItsRequiredModels.clear();

		if ( rootPackage != null ) {
			VerificationDataCollector ec = new VerificationDataCollector((EObject) rootPackage);

			this.allRequirements.addAll(ec.getAllRequirements());
			this.alwaysInclude.addAll(ec.getAlwaysInclude());
			this.modelToItsRequiredModels.putAll(ec.getModelToItsRequiredModels());
			
			return true; // successful execution
		}
		
		return false;
	}
	

	
	// GETTER/SETTER ############################################################

	public Model getUmlRoolModel() {
		return umlRoolModel;
	}
	public void setUmlRoolModel(Model umlRoolModel) {
		this.umlRoolModel = umlRoolModel;
	}

	public void setSelectedReq(HashSet<Element> selectedReq) {
		this.selectedReq = selectedReq;
	}

	public HashSet<Element> getSelectedReq() {
		return selectedReq;
	}

	public HashSet<Element> getAlwaysInclude() {
		return alwaysInclude;
	}
	
	public HashMap<Element, HashSet<Element>> getModelToItsRequiredModels() {
		return modelToItsRequiredModels;
	}

	public HashSet<Element> getAllRequirements() {
		return allRequirements;
	}
	
}
