/**
 *  Copyright (c) 2011 Atos.
 *  
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *  Atos - Initial API and implementation
 * 
 */
package org.eclipse.papyrus.uml.modelexplorer.queries;

import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.infra.core.editorsfactory.IPageIconsRegistryExtended;
import org.eclipse.papyrus.views.modelexplorer.queries.AbstractGetEditorIconQuery;

/**
 * Return the path to the icon of the corresponding diagram * FIXME : delete this class when the bug EMF-Facet 365744 will be corrected!
 * 
 * @Deprecated : use oep.infra.gmfdiag.modelexplorer#queries.GetDiagramIcon
 */
@Deprecated
public class GetDiagramIcon extends AbstractGetEditorIconQuery implements IJavaModelQuery<Diagram, String> {

	public String evaluate(final Diagram context, final ParameterValueList parameterValues) throws ModelQueryExecutionException {
		return "/" + ((IPageIconsRegistryExtended)getEditorRegistry()).getEditorURLIcon(context); //$NON-NLS-1$
	}
}
