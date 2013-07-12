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
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.partialEditing;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.ui.texteditor.IUpdate;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving operationHistory events.
 * The class that is interested in processing a operationHistory
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOperationHistoryListener<code> method. When
 * the operationHistory event occurs, that object's appropriate
 * method is invoked.
 *
 * @see OperationHistoryEvent
 */
public class OperationHistoryListener implements IOperationHistoryListener {
	
	/** The context. */
	private final IUndoContext context;
	
	/** The update. */
	private final IUpdate update;

	/**
	 * Instantiates a new operation history listener.
	 *
	 * @param context the context
	 * @param update the update
	 */
	public OperationHistoryListener(IUndoContext context, IUpdate update) {
		this.context = context;
		this.update = update;
	}

	/**
	 * History notification.
	 *
	 * @param event the event
	 */
	public void historyNotification(OperationHistoryEvent event) {
		final int type= event.getEventType();
		switch (type) {
			case OperationHistoryEvent.UNDONE:
			case OperationHistoryEvent.REDONE:
			case OperationHistoryEvent.OPERATION_NOT_OK:
			// if this is one of our operations
			if (event.getOperation().hasContext(context)) {
				update.update();
			}		
		}	
	}
}