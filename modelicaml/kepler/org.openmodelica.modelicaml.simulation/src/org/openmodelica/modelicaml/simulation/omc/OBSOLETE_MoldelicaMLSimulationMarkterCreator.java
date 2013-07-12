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
package org.openmodelica.modelicaml.simulation.omc;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Element;


// TODO: Auto-generated Javadoc
/**
 * The Class MoldelicaMLSimulationMarkterCreator.
 */
public class OBSOLETE_MoldelicaMLSimulationMarkterCreator {
	
	/**
	 * Modelica ml simulation alert.
	 * 
	 * @param elt
	 *            the elt
	 * @param criticality
	 *            the criticality
	 * @param msg
	 *            the msg
	 */
	public static void modelicaMLSimulationAlert (Element elt, String criticality, String msg){
		IResource r1 = null;
		URI eUri = elt.eResource().getURI();
		
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			r1 = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		}
			
		try {
			IMarker marker = r1.createMarker("com.eadsiw.modelicaml.simulation.modelicamlMarker");
			
			marker.setAttribute(IMarker.MESSAGE, msg);
			
			Integer crit = null;
			if ( criticality.equals("error") ) 	{ crit = IMarker.SEVERITY_ERROR;	}
			else 							{ crit = IMarker.SEVERITY_INFO; }
			
			marker.setAttribute(IMarker.SEVERITY, crit);
		
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
