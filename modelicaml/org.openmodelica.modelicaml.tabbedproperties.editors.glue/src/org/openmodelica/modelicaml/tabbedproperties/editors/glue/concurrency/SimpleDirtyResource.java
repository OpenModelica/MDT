/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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

}
