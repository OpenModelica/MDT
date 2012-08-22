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
package org.openmodelica.modelicaml.common.datacollection;

import java.util.HashSet;

import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.openmodelica.modelicaml.common.constants.Constants;

public class RelationsCollector extends ElementsCollector {

	private HashSet<Element> useToVerifyDependencies = new HashSet<Element>();
	private HashSet<Element> doNotUseToVerifyDependencies = new HashSet<Element>();
	private HashSet<Element> requiresDependencies = new HashSet<Element>();
	private HashSet<Element> requiredForDependencies = new HashSet<Element>();
	private HashSet<Element> importDependencies = new HashSet<Element>();
	private HashSet<Element> redeclareDependencies = new HashSet<Element>();
	private HashSet<Element> constrainedByDependencies = new HashSet<Element>();
	private HashSet<Element> partialDerivativeOfFunctionDependencies = new HashSet<Element>();
	
	
	private HashSet<Element> inheritanceRelations = new HashSet<Element>();
	
	@Override
	protected void collect(Element element, boolean isImported) {
		
		
		if (element instanceof Dependency) {
			
			// add to overall list in order to enable import cycles detection
			elements.add(element);
			
			if (element.getAppliedStereotype(Constants.stereotypeQName_UseToVerify) != null) {
				useToVerifyDependencies.add(element);
			}
			else if (element.getAppliedStereotype(Constants.stereotypeQName_DoNotUseToVerify) != null) {
				doNotUseToVerifyDependencies.add(element);
			}
			else if (element.getAppliedStereotype(Constants.stereotypeQName_Requires) != null) {
				requiresDependencies.add(element);
			}
			else if (element.getAppliedStereotype(Constants.stereotypeQName_RequiredFor) != null) {
				requiredForDependencies.add(element);
			}
			else if (element.getAppliedStereotype(Constants.stereotypeQName_ImportRelation) != null) {
				importDependencies.add(element);
			}
			else if (element.getAppliedStereotype(Constants.stereotypeQName_RedeclareRelation) != null) {
				redeclareDependencies.add(element);
			}
			else if (element.getAppliedStereotype(Constants.stereotypeQName_ConstrainedByRelation) != null) {
				constrainedByDependencies.add(element);
			}
			else if (element.getAppliedStereotype(Constants.stereotypeQName_PartialDerivativeOfFunctionRelation) != null) {
				partialDerivativeOfFunctionDependencies.add(element);
			}
		}
		else if (element instanceof Generalization) {
			// add to overall list in order to enable import cycles detection
			elements.add(element);
			
			if (element.getAppliedStereotype(Constants.stereotypeQName_ExtendsRelation) != null || element.getAppliedStereotype(Constants.stereotypeQName_TypeRelation) != null) {
				inheritanceRelations.add(element);
			}
		}
	}
	
	
	// getter and setter
	public HashSet<Element> getUseToVerifyDependencies() {
		return useToVerifyDependencies;
	}

	public void setUseToVerifyDependencies(HashSet<Element> useToVerifyDependencies) {
		this.useToVerifyDependencies = useToVerifyDependencies;
	}

	public HashSet<Element> getDoNotUseToVerifyDependencies() {
		return doNotUseToVerifyDependencies;
	}

	public void setDoNotUseToVerifyDependencies(
			HashSet<Element> doNotUseToVerifyDependencies) {
		this.doNotUseToVerifyDependencies = doNotUseToVerifyDependencies;
	}

	public HashSet<Element> getRequiresDependencies() {
		return requiresDependencies;
	}

	public void setRequiresDependencies(HashSet<Element> requiresDependencies) {
		this.requiresDependencies = requiresDependencies;
	}

	public HashSet<Element> getRequiredForDependencies() {
		return requiredForDependencies;
	}

	public void setRequiredForDependencies(HashSet<Element> requiredForDependencies) {
		this.requiredForDependencies = requiredForDependencies;
	}


	public HashSet<Element> getImportDependencies() {
		return importDependencies;
	}


	public void setImportDependencies(HashSet<Element> importDependencies) {
		this.importDependencies = importDependencies;
	}


	public HashSet<Element> getInheritanceRelations() {
		return inheritanceRelations;
	}


	public void setInheritanceRelations(HashSet<Element> inheritanceRelations) {
		this.inheritanceRelations = inheritanceRelations;
	}


	public HashSet<Element> getRedeclareDependencies() {
		return redeclareDependencies;
	}


	public void setRedeclareDependencies(HashSet<Element> redeclareDependencies) {
		this.redeclareDependencies = redeclareDependencies;
	}


	public HashSet<Element> getConstrainedByDependencies() {
		return constrainedByDependencies;
	}


	public void setConstrainedByDependencies(
			HashSet<Element> constrainedByDependencies) {
		this.constrainedByDependencies = constrainedByDependencies;
	}


	public HashSet<Element> getPartialDerivativeOfFunctionDependencies() {
		return partialDerivativeOfFunctionDependencies;
	}


	public void setPartialDerivativeOfFunctionDependencies(
			HashSet<Element> partialDerivativeOfFunctionDependencies) {
		this.partialDerivativeOfFunctionDependencies = partialDerivativeOfFunctionDependencies;
	}
}
