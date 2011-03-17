/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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