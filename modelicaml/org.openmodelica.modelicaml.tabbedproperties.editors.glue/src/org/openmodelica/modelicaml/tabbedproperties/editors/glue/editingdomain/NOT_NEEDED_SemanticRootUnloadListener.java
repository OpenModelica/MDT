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
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.editingdomain;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.xtext.resource.XtextResource;

// TODO: Auto-generated Javadoc
/**
 * Reloads the semantic root element (the element of the diagram) on changes and refreshes the diagram. GMF does by
 * default not listen to such events which can occur in an {@link XtextResource}, if the document changes close to the
 * root element.
 * 
 * Activate an instance of this in the {@link EditPart#activate()} method of the {@link DiagramEditPart}.
 * 
 * @author koehnlein
 */
public class NOT_NEEDED_SemanticRootUnloadListener implements ResourceSetListener {

	/** The root edit part. */
	private DiagramEditPart rootEditPart;
	
	/** The semantic root element. */
	private EObject semanticRootElement;

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param rootEditPart the root edit part
	 */
	public NOT_NEEDED_SemanticRootUnloadListener(DiagramEditPart rootEditPart) {
		this.rootEditPart = rootEditPart;
		this.semanticRootElement = rootEditPart.resolveSemanticElement();
	}

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 */
	public void activate() {
		rootEditPart.getEditingDomain().addResourceSetListener(this);
	}

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 */
	public void deactivate() {
		rootEditPart.getEditingDomain().removeResourceSetListener(this);
	}

	/**
	 * Gets the filter.
	 *
	 * @return the filter
	 */
	public NotificationFilter getFilter() {
		return new NotificationFilter.Custom() {
			@Override
			public boolean matches(Notification notification) {
				int featureID = notification.getFeatureID(Resource.class);
				Object notifier = notification.getNotifier();
				int eventType = notification.getEventType();
				return notification.getOldValue() == semanticRootElement && featureID == Resource.RESOURCE__CONTENTS
						&& (eventType == Notification.REMOVE || eventType == Notification.SET)
						&& notifier instanceof Resource;
			}
		};
	}

	/**
	 * Checks if is aggregate precommit listener.
	 *
	 * @return true, if is aggregate precommit listener
	 */
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	/**
	 * Checks if is postcommit only.
	 *
	 * @return true, if is postcommit only
	 */
	public boolean isPostcommitOnly() {
		return true;
	}

	/**
	 * Checks if is precommit only.
	 *
	 * @return true, if is precommit only
	 */
	public boolean isPrecommitOnly() {
		return false;
	}

	/**
	 * Resource set changed.
	 *
	 * @param event the event
	 */
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		semanticRootElement = rootEditPart.resolveSemanticElement();
		rootEditPart.refresh();
	}

	/**
	 * Transaction about to commit.
	 *
	 * @param event the event
	 * @return the command
	 * @throws RollbackException the rollback exception
	 */
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		return null;
	}

}
