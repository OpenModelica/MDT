/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing;

import org.eclipse.xtext.resource.XtextResource;

import com.google.inject.ImplementedBy;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISyntheticResourceProvider.
 */
@ImplementedBy(SyntheticResourceProvider.class)
public interface ISyntheticResourceProvider {
	
	/**
	 * Creates the resource.
	 *
	 * @return XtextResource
	 */
	XtextResource createResource();
}