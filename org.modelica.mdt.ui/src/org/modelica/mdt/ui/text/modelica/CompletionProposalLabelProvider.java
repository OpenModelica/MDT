/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.text.modelica;

import org.eclipse.core.runtime.Assert;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;

/**
 * Provides labels for java content assist proposals. The functionality is
 * similar to the one provided by {@link org.eclipse.jdt.ui.JavaElementLabels},
 * but based on signatures and {@link CompletionProposal}s.
 *
 * @see Signature
 * @since 3.1
 */
public class CompletionProposalLabelProvider {


	/**
	 * The completion context.
	 *
	 * @since 3.2
	 */
	private CompletionContext fContext;

	/**
	 * Creates a new label provider.
	 */
	public CompletionProposalLabelProvider() {
	}


	/**
	 * Sets the completion context.
	 *
	 * @param context the completion context
	 * @since 3.2
	 */
	void setContext(CompletionContext context) {
		fContext= context;
	}

}
