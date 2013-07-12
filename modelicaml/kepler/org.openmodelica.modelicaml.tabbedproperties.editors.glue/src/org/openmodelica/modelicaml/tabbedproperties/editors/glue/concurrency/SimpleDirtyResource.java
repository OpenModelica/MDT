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
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.concurrency;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Manager;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.IDirtyResource;

// TODO: Auto-generated Javadoc
/**
 * An implementation of {@link IDirtyResource} for resources without an underlying document.
 * 
 * @author koehnlein - Initial contribution and API
 */
public class SimpleDirtyResource implements IDirtyResource {

	/** The resource. */
	private XtextResource resource;

	/** The resource description manager. */
	private Manager resourceDescriptionManager;

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param resource the resource
	 * @param resourceServiceProvider the resource service provider
	 */
	public SimpleDirtyResource(XtextResource resource, IResourceServiceProvider resourceServiceProvider) {
		this.resource = resource;
		this.resourceDescriptionManager = resourceServiceProvider.getResourceDescriptionManager();
	}

	/**
	 * Gets the contents.
	 *
	 * @return the contents
	 */
	public String getContents() {
		return resource.getSerializer().serialize(resource.getContents().get(0));
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public IResourceDescription getDescription() {
		return resourceDescriptionManager.getResourceDescription(resource);
	}

	/**
	 * Gets the uRI.
	 *
	 * @return the uRI
	 */
	public URI getURI() {
		return resource.getURI();
	}

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 * @return Resource
	 */
	public Resource getResource() {
		return resource;
	}

	public String getActualContents() {
		// TODO Auto-generated method stub
		return null;
	}

}
