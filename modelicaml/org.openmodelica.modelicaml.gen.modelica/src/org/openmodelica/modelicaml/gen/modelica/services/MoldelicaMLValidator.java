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
package org.openmodelica.modelicaml.gen.modelica.services;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;

import fr.obeo.acceleo.gen.AcceleoEcoreGenPlugin;


/**
 * The Class MoldelicaMLValidator.
 */
public class MoldelicaMLValidator {
	
	/**
	 * Display.
	 * 
	 * @param elt
	 *            the elt
	 * @param msg
	 *            the msg
	 * @param criticality
	 *            the criticality
	 */
	public static void display(Element elt, String msg, String criticality){
		if (!criticality.equals("")) {
			AcceleoEcoreGenPlugin.getDefault().log(msg, true);			
		}
		else {
			AcceleoEcoreGenPlugin.getDefault().log(msg, false);

		}
	}
	
	/**
	 * Validation alert.
	 * 
	 * @param elt
	 *            the elt
	 * @param criticality
	 *            the criticality
	 * @param msg
	 *            the msg
	 */
	public static void validationAlert (Element elt, String criticality, String msg){
		if (!criticality.equals("")) {
			AcceleoEcoreGenPlugin.getDefault().log(msg, true);			
		}
		else {
			AcceleoEcoreGenPlugin.getDefault().log(msg, false);
		}
		modelicaMLValidationAlert(elt, criticality, msg);
	}

	
	/**
	 * Modelica ml validation alert.
	 * 
	 * @param elt
	 *            the elt
	 * @param criticality
	 *            the criticality
	 * @param msg
	 *            the msg
	 */
	public static void modelicaMLValidationAlert (Element elt, String criticality, String msg){
		IResource r = null;
		URI eUri = elt.eResource().getURI();
		
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		}
			
		try {
		//	IMarker marker = r.createMarker(EValidator.MARKER);
			IMarker marker = r.createMarker(Constants.MARKERTYPE_VALIDATION_PROBLEM);
			
			marker.setAttribute(IMarker.MESSAGE, msg);
			
			Integer crit = null;
			if ( criticality.equals("error") ) 	{ crit = IMarker.SEVERITY_ERROR;	}
			else 							{ crit = IMarker.SEVERITY_INFO; }
			
			marker.setAttribute(IMarker.SEVERITY, crit);
			marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());
		//	marker.setAttribute(EValidator.URI_ATTRIBUTE, elt.eResource().getURI().toString());
			marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());
			
			//IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			//ModelicaMLValidationView.openMarkerInEditor(marker, page);
		
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Creates the modelica ml code validation marker.
	 * 
	 * @param elt
	 *            the elt
	 * @param criticality
	 *            the criticality
	 * @param msg
	 *            the msg
	 * @return the i marker
	 */
	public static IMarker createModelicaMLCodeValidationMarker(Element elt, String criticality, String msg){
		IResource r = null;
		URI eUri = elt.eResource().getURI();
		
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
		}
			
		try {
		//	IMarker marker = r.createMarker(EValidator.MARKER);
			IMarker marker = r.createMarker(Constants.MARKERTYPE_VALIDATION_PROBLEM);
			
			marker.setAttribute(IMarker.MESSAGE, msg);
			
			Integer crit = null;
			if ( criticality.equals("error") ) 	{ crit = IMarker.SEVERITY_ERROR;	}
			else 							{ crit = IMarker.SEVERITY_INFO; }
			
			marker.setAttribute(IMarker.SEVERITY, crit);
			marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());
		//	marker.setAttribute(EValidator.URI_ATTRIBUTE, elt.eResource().getURI().toString());
			marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());
			
			//IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			//ModelicaMLValidationView.openMarkerInEditor(marker, page);
			return marker;
		
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
