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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.validation;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.validation.Check;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;

public class ProviderJavaValidator extends AbstractProviderJavaValidator {

	@Check
	public void checkComponent_reference(component_reference cr) {
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();
		// this is added in order to avoid concurrent modifications. TODO: test it!
		cList.addAll(ModelicaMLContentAssist.getFullModifiedComponentReferenceSortedList()); 
		
		List<String> cListCopy = new ArrayList<String>();
		cListCopy.addAll(cList);
		for (String string : cListCopy) {
			if (string.startsWith(ModelicaMLContentAssist.getPropertyName())) {
				cList.remove(string);
				cList.add(string.replaceFirst(ModelicaMLContentAssist.getPropertyName(), Constants.MACRO_providerPath));
			}
		}
//		for (String string : cList) {
//			System.err.println("reference:" + string);
//		}

		String dotPath = cr.getRef();
		String ref1DotPath = ""; 
		
		if (cr.getRef1().size() > 0) {
			for (String string : cr.getRef1()) {
				ref1DotPath = ref1DotPath + "." + string;
			}
			dotPath = dotPath + ref1DotPath;
		}
		if ( !cList.contains(dotPath) ) {
			error("component_reference '" + dotPath + "' cannot be resolved to a class component", null, ModeleditorPackage.COMPONENT_REFERENCE);
		}
	}
	
	
	@Check
	public void checkname(name cr) {
		List<String> cList = ModelicaMLContentAssist.getFullComponentReferenceSortedList();
		// this is added in order to avoid concurrent modifications. TODO: test it!
		cList.addAll(ModelicaMLContentAssist.getFullModifiedComponentReferenceSortedList()); 

		
		List<String> cListCopy = new ArrayList<String>();
		cListCopy.addAll(cList);
		for (String string : cListCopy) {
			if (string.startsWith(ModelicaMLContentAssist.getPropertyName())) {
				cList.remove(string);
				cList.add(string.replaceFirst(ModelicaMLContentAssist.getPropertyName(), Constants.MACRO_providerPath));
			}
		}
//		for (String string : cList) {
//			System.err.println("name:" + string);
//		}

		cList.addAll(ModelicaMLContentAssist.getTypeSpecifierSortedList()); // used for redeclare in modifications ...
		
		if ( !cList.contains(cr.getName_ID()) ) {
			error("name '" + cr.getName_ID() + "' cannot be resolved", null, ModeleditorPackage.NAME__NAME_ID);
		}
	}

}
