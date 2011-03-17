/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing;

import java.util.List;

import com.google.inject.Binding;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActionContributor.
 */
public interface IActionContributor {
	
	/**
	 * hook used to contribute any actions on editor start up.
	 *
	 * @param editor the editor
	 */
	public void contributeActions(PartialModelEditor editor);
	
	
	/**
	 * composite action contributor delegating call to all registered {@link IActionContributor}.
	 */
	@Singleton
	public class CompositeImpl implements IActionContributor {
		
		/** The injector. */
		@Inject
		private Injector injector;

		/**
		 * Contribute actions.
		 *
		 * @param editor the editor
		 */
		public void contributeActions(PartialModelEditor editor) {
			List<Binding<IActionContributor>> bindingsByType = injector.findBindingsByType(TypeLiteral.get(IActionContributor.class));
			for (Binding<IActionContributor> binding : bindingsByType) {
				IActionContributor actionContributor = injector.getInstance(binding.getKey());
				actionContributor.contributeActions(editor);
			}
		}

	}
}