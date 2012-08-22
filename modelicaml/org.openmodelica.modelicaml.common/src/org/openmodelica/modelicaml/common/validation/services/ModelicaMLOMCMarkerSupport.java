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
package org.openmodelica.modelicaml.common.validation.services;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.NamedElement;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLOMCMarkerSupport.
 */
public class ModelicaMLOMCMarkerSupport {
	
	/** The Constant markerTypeName. */
	private final static String markerTypeName = "org.openmodelica.modelicaml.modelicamlMarker.omc";

	/**
	 * Delete all omc markers.
	 *
	 * @param iProject the i project
	 */
	public static void deleteAllOMCMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				markers = iProject.findMarkers(null, true,IResource.DEPTH_INFINITE);
				for (IMarker marker : markers) {
					if (marker.getType().equals(markerTypeName)) {
						marker.delete();
					}
				}
			}
//			else {
//				System.err.println("Cannot find the Papyrus MDT ModelicaML iProject to generate a marker for.");
//			}
		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	
	/**
	 * Delete validation markers.
	 *
	 * @param iProject the i project
	 */
	public static void deleteValidationMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				markers = iProject.findMarkers(null, true, IResource.DEPTH_INFINITE);
				for (IMarker marker : markers) {
					if (marker.getType().equals("org.openmodelica.modelicaml.modelicamlMarker.validation")) {
						marker.delete();
					}
				}
				
			}

		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	/**
	 * Creates the omc marker.
	 *
	 * @param elt the elt
	 * @param criticality the criticality
	 * @param msg the msg
	 * @return the i marker
	 */
	public static IMarker createOMCMarker(Object elt, String criticality, String msg){
		if (elt instanceof NamedElement) { // TODO
			IResource r = null;
			URI eUri = ((NamedElement)elt).eResource().getURI();
			
			if (eUri.isPlatformResource()) {
				String platformString = eUri.toPlatformString(true);
				r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
			}
				
			try {
			//	IMarker marker = r.createMarker(EValidator.MARKER);
				IMarker marker = r.createMarker(markerTypeName);
				
				marker.setAttribute(IMarker.MESSAGE, msg);
				
				Integer crit = null;
				if ( criticality.equals("error") ) 	{ crit = IMarker.SEVERITY_ERROR;	}
				else 							{ crit = IMarker.SEVERITY_INFO; }
				
				marker.setAttribute(IMarker.SEVERITY, crit);
				marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());
				marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI((EObject) elt).toString());//elt.eResource().getURI().toString());
				
				return marker;
			
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
//		else {
//			System.err.println("Cannot create a ModelicaML OMC marker with message " + msg + "  for " + elt.toString());
//		}
		return null;
	}
	
}
