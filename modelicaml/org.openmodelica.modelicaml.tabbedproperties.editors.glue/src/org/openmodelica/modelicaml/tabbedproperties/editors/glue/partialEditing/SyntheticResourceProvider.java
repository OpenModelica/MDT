/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;

import com.google.inject.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class SyntheticResourceProvider.
 *
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class SyntheticResourceProvider implements ISyntheticResourceProvider {

	/** The Constant SYNTHETIC_SCHEME. */
	public static final String SYNTHETIC_SCHEME = "synthetic";
	
	/** The resource set provider. */
	@Inject 
	private IResourceSetProvider resourceSetProvider;
	
	/** The grammar access. */
	@Inject
	private IGrammarAccess grammarAccess;
	
	/**
	 * Creates the resource.
	 *
	 * @return the xtext resource
	 */
	public XtextResource createResource() {
		ResourceSet resourceSet = resourceSetProvider.get(null);
		Resource grammarResource = resourceSet.createResource(
				URI.createURI(SYNTHETIC_SCHEME + ":/" + grammarAccess.getGrammar().getName() + ".xtext"));
		grammarResource.getContents().add(EcoreUtil.copy(grammarAccess.getGrammar()));
		XtextResource result = (XtextResource) resourceSet.createResource(
				URI.createURI(SYNTHETIC_SCHEME + ":/" + grammarAccess.getGrammar().getName() + "." + PropertiesSectionXtextEditorHelper.fileExtension));
		resourceSet.getResources().add(result);
		return result;
	}
}
