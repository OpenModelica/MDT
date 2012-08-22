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
package org.openmodelica.modelicaml.gen.modelica.postactions;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.modelica.ConnectException;
import org.modelica.OMCProxy;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLOMCMarkerSupport;
import org.openmodelica.modelicaml.gen.modelica.listeners.PapyrusListener;

import fr.obeo.acceleo.chain.tools.IPostGeneration;
import fr.obeo.acceleo.tools.resources.Resources;

// TODO: Auto-generated Javadoc
/**
 * The Class PostGenerationForAutomaticBuild.
 */
public class PostGenerationForAutomaticBuild implements IPostGeneration {

	/** The proxy. */
	private static OMCProxy proxy = new OMCProxy();
	
	/** The generation type. */
	public static String generationType = "files"; 

	/* (non-Javadoc)
	 * @see fr.obeo.acceleo.chain.tools.IPostGeneration#postFileGeneration(org.eclipse.core.resources.IContainer, org.eclipse.core.runtime.IPath, java.lang.StringBuffer, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void postFileGeneration(IContainer arg0, IPath arg1,
			StringBuffer arg2, IProgressMonitor arg3) throws CoreException {
		
		String status = null;
		if (generationType.equals("doNotGenerateFiles")) {
			// Don't write into files or send anything using CORBA, etc. Only markers will be generated. 
		}
		else if (generationType.equals("corba")) {
			try {
				UMLResource umlModelResource = PapyrusListener.getUmlResource();
				EObject umlModel = umlModelResource.getContents().get(0);
				
				status = proxy.sendExpression(arg2.toString());
				
				if (!status.contains("false") && !status.contains("error") && !status.contains("Error")) {
					// Status is OK. No need for markers ... 
//					if (umlModel instanceof NamedElement) {
//						ModelicaMLOMCMarkerSupport.createOMCMarker(umlModel, "", "'" + arg1 + "'" + " was reloaded in OMC.");
//					}
				}
				else {
					// status is not OK. generate marker.  
					if (umlModel instanceof NamedElement) {
						ModelicaMLOMCMarkerSupport.createOMCMarker(umlModel, "error", status);
					}
				}
			} catch (ConnectException e) {
				e.printStackTrace();
			}
		}
		else { // Default: Generate files.
			Resources.createFile(arg0, arg1, arg2.toString(), arg3);	
		}
	}

	/* (non-Javadoc)
	 * @see fr.obeo.acceleo.chain.tools.IPostGeneration#postProcessing(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void postProcessing(IProgressMonitor arg0) {
		// TODO Auto-generated method stub

	}

}
