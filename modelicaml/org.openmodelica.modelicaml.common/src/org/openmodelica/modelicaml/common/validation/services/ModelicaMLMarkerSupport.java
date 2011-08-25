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
package org.openmodelica.modelicaml.common.validation.services;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.resource.uml.UmlModel;
import org.eclipse.papyrus.resource.uml.UmlUtils;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLMarkerSupport.
 */
public class ModelicaMLMarkerSupport {

	
	/** The uml model. */
	private static UmlModel umlModel;
	
	/** The model name. */
	private static String modelName;
	
	/** The project name. */
	private static String projectName;
	
	/** The workspace. */
	private static IWorkspace workspace;
	
	/** The root. */
	private static IWorkspaceRoot root;
	
	/** The project. */
	public static IProject project;
	
	/** The Constant markerTypeName. */
	private final static String markerTypeName = "org.openmodelica.modelicaml.modelicamlMarker.actionCode";
	
	/**
	 * Generate marker.
	 *
	 * @param message the message
	 * @param severity the severity
	 * @param sourceElement the source element
	 */
	public static void generateMarker(String message, String severity, Element sourceElement ){
		
		// set the current Papyrus project 
		setProject();
		
		// create a marker for the uml model element
		if (sourceElement != null) {
			if (sourceElement instanceof Generalization) {
				if (((Generalization)sourceElement).getSpecific() instanceof NamedElement) {
					if (!markerExist(message, ((Generalization)sourceElement).getSpecific() ) ) {
						ModelicaMLMarkerSupport.createMarker(((Generalization)sourceElement).getSpecific(), severity, message);	
					}
				}
			}
			else if (sourceElement instanceof NamedElement){
				if (!markerExist(message, sourceElement) ) {
					//ModelicaMLMarkerSupport.createMarker(ModelicaMLContentAssist.getSelectedSourceElement(), severity, message); 
					ModelicaMLMarkerSupport.createMarker(sourceElement, severity, message);// TODO: is that correct? test it in comparison with the line above!
				}
			}
		}
	}
	
	/**
	 * Sets the project.
	 */
	private static void setProject(){
		umlModel = UmlUtils.getUmlModel();
		if (umlModel != null) {
			modelName = umlModel.getResourceURI().lastSegment();
			//projectName = umlModel.getResourceURI().path().replace(modelName, "").replace("/resource/", "");
			projectName = umlModel.getResource().getURI().segment(1);
			
			workspace = ResourcesPlugin.getWorkspace();
			root = workspace.getRoot();
			project = root.getProject(projectName);
		}
	}
	
	/**
	 * Marker exist.
	 *
	 * @param message the message
	 * @param sourceElement the source element
	 * @return true, if successful
	 */
	private static boolean markerExist(String message, Element sourceElement) {
		
		if (sourceElement instanceof NamedElement && project != null) {
			IMarker[] markers = null;
			try {
				markers = project.findMarkers(null, true,IResource.DEPTH_INFINITE);
				for (IMarker marker : markers) {
					Object qualifiedName = marker.getAttribute(IMarker.LOCATION);
					Object markerMessage = marker.getAttribute(IMarker.MESSAGE);
					if (qualifiedName != null && markerMessage != null) {
						if (qualifiedName.equals(((NamedElement)sourceElement).getQualifiedName())
								&& markerMessage.equals(message)) {
							return true;
						}
					}
				}
			} catch (CoreException e) {
				// e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Delete marker.
	 *
	 * @param message the message
	 * @param sourceElement the source element
	 */
	public static void deleteMarker(String message, Element sourceElement) {
		if (sourceElement instanceof NamedElement && project != null) {
			setProject();
			
			IMarker[] markers = null;
			try {
				markers = project.findMarkers(null, true,
						IResource.DEPTH_INFINITE);

				for (IMarker marker : markers) {
					Object qualifiedName = marker.getAttribute(IMarker.LOCATION);
					Object markerMessage = marker.getAttribute(IMarker.MESSAGE);
					if (qualifiedName != null && markerMessage != null) {
						if (qualifiedName.equals( ((NamedElement)sourceElement).getQualifiedName())
								&& markerMessage.equals(message)) {
							marker.delete();
						}
					}
				}

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	/**
	 * Creates the marker.
	 *
	 * @param elt the elt
	 * @param criticality the criticality
	 * @param msg the msg
	 * @return the i marker
	 */
	public static IMarker createMarker(Element elt, String criticality, String msg){
		if (elt instanceof NamedElement) { 
			IResource r = null;
			if (elt.eResource() != null) { // TODO: somtimes it is null, why?
				URI eUri = elt.eResource().getURI();
				
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
				//	marker.setAttribute(EValidator.URI_ATTRIBUTE, elt.eResource().getURI().toString());
					marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());
					
					//IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					//ModelicaMLValidationView.openMarkerInEditor(marker, page);
					return marker;
				
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		else {
			System.err.println("Cannot create a ModelicaML marker with message " + msg + "  for " + elt.toString());
		}
		return null;
	}
	
}
