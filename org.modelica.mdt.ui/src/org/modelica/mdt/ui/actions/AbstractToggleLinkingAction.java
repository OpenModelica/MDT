/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.actions;

import org.eclipse.jface.action.Action;

import org.modelica.mdt.ui.ModelicaImages;

/**
 * This is an action template for actions that toggle whether
 * it links its selection to the active editor.
 * 
 * @since 3.0
 */
public abstract class AbstractToggleLinkingAction extends Action {
	
	/**
	 * Constructs a new action.
	 */
	public AbstractToggleLinkingAction() {
		super("Link With Editor"); 
		setDescription("Link With Editor"); 
		setToolTipText("Link With Editor"); 
		this.setImageDescriptor(ModelicaImages.getImageDescriptor(ModelicaImages.IMG_OUTLINE_SYNCED));
	}

	/**
	 * Runs the action.
	 */
	public abstract void run();
}
