/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.modelica.uml.sysml.diagram2.edit.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Connection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.modelica.uml.sysml.diagram2.edit.policies.ModelicaConnectionItemSemanticEditPolicy;

/**
 * Implements a Connection Editpart to represent a connection.
 * 
 */
public class ModelicaConnectionEditPart extends ConnectionNodeEditPart
		implements PropertyChangeListener {

	public static final int VISUAL_ID = 3009;

	public ModelicaConnectionEditPart(View view) {
		super(view);
	}

	public static final Color lineColor = new Color(Display.getDefault(), 192,
			192, 192);

	/**
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModelicaConnectionItemSemanticEditPolicy());
	}

	public void activateFigure() {
		super.activateFigure();
		/*
		 * Once the figure has been added to the ConnectionLayer, start
		 * listening for its router to change.
		 */
		getFigure().addPropertyChangeListener(
				Connection.PROPERTY_CONNECTION_ROUTER, this);
	}

	/**
	 * Method createConnectionFigure.
	 * 
	 * @return Connection
	 */
	protected Connection createConnectionFigure() {
		if (getModel() == null)
			return null;

		Connection connx = new PolylineConnectionEx();
		return connx;
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#handlePropertyChangeEvent(java.beans.PropertyChangeEvent)
	 */
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		if (NotationPackage.eINSTANCE.getIdentityAnchor_Id().equals(
				event.getFeature())) {
			anchorChange();
		}
	}

	/**
	 * Refreshes the visual aspects of this, based upon the model. It changes
	 * the wire color depending on the state of Wire.
	 * 
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();

		getFigure().setForegroundColor(lineColor);
	}

}
