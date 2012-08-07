package org.openmodelica.modelicaml.modelica.importer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.constants.Constants;

public class Utilities {

	// Marker *****************************************
	
	/*
	 * ModelicaMLModel needs to be set from outside prior to calling one of the methods of this class. 
	 * The reason is the following: these methods are often called from jobs.
	 * In jobs it is not possible to get the model from Papyrus because there is 
	 * no access to Eclipse GUI when the job is executed. 
	 */
	public static UmlModel ModelicaMLModel;
	
	private static String markerType = Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES;
	
	
	public static IMarker createMarker(Element elt, String sourceID, String criticality, String msg){
		if (elt != null) {
			IResource r = null;
			URI eUri = elt.eResource().getURI();
			
			if (eUri.isPlatformResource()) {
				String platformString = eUri.toPlatformString(true);
				r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
//					r = ResourcesPlugin.getWorkspace().getRoot().findMember(umlModel.getResource().getURI().toPlatformString(true));
			}
			try {
				
				IMarker marker = r.createMarker(markerType);
				marker.setAttribute(IMarker.MESSAGE, msg);
				if ( criticality.equals("error") ) 	{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);	}
				else 								{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) ; }
				marker.setAttribute(IMarker.SOURCE_ID, sourceID);
				
				if (elt instanceof NamedElement) {
					marker.setAttribute(IMarker.LOCATION, ((NamedElement)elt).getQualifiedName());	
				}
				else{
					marker.setAttribute(IMarker.LOCATION, elt.toString());
				}
				marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(elt).toString());//elt.eResource().getURI().toString());

				return marker;
				
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static IMarker createOMCMarker(TreeObject treeObject, String criticality, String msg){
		if (!msg.trim().equals("") ) {
			
			String markerType = Constants.MARKERTYPE_MODELICA_MODELS_LOADING;
			
			if ( ModelicaMLModel != null ) {
				IResource r = null;
				URI eUri = ModelicaMLModel.getResource().getURI();
				
				if (eUri.isPlatformResource()) {
					String platformString = eUri.toPlatformString(true);
					r = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
				}
				try {
					IMarker marker = r.createMarker(markerType);
					marker.setAttribute(IMarker.MESSAGE, msg);
					
					if ( criticality.equals("error") ) 	{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);	}
					else 								{ marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) ; }
					
					if ( treeObject != null) {
						marker.setAttribute(IMarker.SOURCE_ID, treeObject.getQName());
						marker.setAttribute(IMarker.LOCATION, treeObject.getQName());	
					}
					return marker;
					
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	public static void deleteProxyValidationMarkers(IProject iProject, String namespace) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				List<String> markerTypes = new ArrayList<String>();
				markerTypes.add(Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES);
				
				for (String markerType : markerTypes) {
					markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
					for (IMarker marker : markers) {
						if ( namespace!= null && !namespace.trim().equals("") && marker.getAttribute(IMarker.LOCATION, "").startsWith(namespace) ) {
							marker.delete();
						}
					}
				}
			}
		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	public static void deleteProxyValidationMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				List<String> markerTypes = new ArrayList<String>();
				markerTypes.add(Constants.MARKERTYPE_MODELICAML_MODELICA_MODEL_PROXIES);
				
				for (String markerType : markerTypes) {
					markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
					for (IMarker marker : markers) {
						marker.delete();
					}
				}
			}
		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	public static void deleteOMCLoadingMarkers(IProject iProject) {
		IMarker[] markers = null;
		try {
			if (iProject != null) {
				List<String> markerTypes = new ArrayList<String>();
				markerTypes.add(Constants.MARKERTYPE_MODELICA_MODELS_LOADING);
				
				for (String markerType : markerTypes) {
					markers = iProject.findMarkers(markerType, true, IResource.DEPTH_INFINITE);
					for (IMarker marker : markers) {
						marker.delete();
					}
				}
			}
		} catch (CoreException e) {
			//e.printStackTrace();
		}
	}
	
	
	public static String extractErrorMessage(String fullString){
		String errorString = "";
		Pattern patternVisibility = Pattern.compile("Error:.*");
		Matcher matcherVisibility = patternVisibility.matcher(fullString);
		while (matcherVisibility.find()) {
			errorString = matcherVisibility.group(0).replaceFirst("Error:", "").trim();
		}
		return errorString;
	}

}
