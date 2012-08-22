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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.helper.datacollection;

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
