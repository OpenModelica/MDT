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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.editingdomain;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Lifecycle;
import org.eclipse.emf.transaction.TransactionalEditingDomainEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomainListener;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.eclipse.xtext.parsetree.NodeAdapter;
import org.eclipse.xtext.parsetree.NodeUtil;
import org.eclipse.xtext.parsetree.reconstr.Serializer;
import org.eclipse.xtext.resource.XtextResource;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.Activator;

// TODO: Auto-generated Javadoc
/**
 * Reconciles the node models of all XtextResources in a TransactionalEditingDomain with semantic changes.
 * 
 * @author koehnlein
 */
public class XtextNodeModelReconciler extends AdapterImpl implements TransactionalEditingDomainListener,
		IOperationHistoryListener {

	/** The editing domain. */
	private TransactionalEditingDomain editingDomain;

	/** The change aggregator. */
	private ChangeAggregatorAdapter changeAggregator;

	/**
	 * Instantiates a new xtext node model reconciler.
	 *
	 * @param editingDomain the editing domain
	 */
	private XtextNodeModelReconciler(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		Lifecycle lifecycle = TransactionUtil.getAdapter(editingDomain, Lifecycle.class);
		lifecycle.addTransactionalEditingDomainListener(this);
		changeAggregator = new ChangeAggregatorAdapter();
		editingDomain.getResourceSet().eAdapters().add(changeAggregator);
		CommandStack commandStack = editingDomain.getCommandStack();
		if (commandStack instanceof IWorkspaceCommandStack) {
			IOperationHistory operationHistory = ((IWorkspaceCommandStack) commandStack).getOperationHistory();
			operationHistory.addOperationHistoryListener(this);
		}
		changeAggregator.beginRecording();
	}

	/**
	 * Editing domain disposing.
	 *
	 * @param event the event
	 */
	public void editingDomainDisposing(TransactionalEditingDomainEvent event) {
		changeAggregator.endRecording();
		CommandStack commandStack = editingDomain.getCommandStack();
		if (commandStack instanceof IWorkspaceCommandStack) {
			IOperationHistory operationHistory = ((IWorkspaceCommandStack) commandStack).getOperationHistory();
			operationHistory.removeOperationHistoryListener(this);
		}
		editingDomain.getResourceSet().eAdapters().remove(changeAggregator);
		Lifecycle lifecycle = TransactionUtil.getAdapter(editingDomain, Lifecycle.class);
		lifecycle.removeTransactionalEditingDomainListener(XtextNodeModelReconciler.this);
	}

	/**
	 * Transaction closed.
	 *
	 * @param event the event
	 */
	public void transactionClosed(TransactionalEditingDomainEvent event) {
		// ignore
	}

	/**
	 * Transaction closing.
	 *
	 * @param event the event
	 */
	public void transactionClosing(TransactionalEditingDomainEvent event) {
		// ignore
	}

	/**
	 * Transaction interrupted.
	 *
	 * @param event the event
	 */
	public void transactionInterrupted(TransactionalEditingDomainEvent event) {
		// ignore
	}

	/**
	 * Transaction started.
	 *
	 * @param event the event
	 */
	public void transactionStarted(TransactionalEditingDomainEvent event) {
		// ignore
	}

	/**
	 * Transaction starting.
	 *
	 * @param event the event
	 */
	public void transactionStarting(TransactionalEditingDomainEvent event) {
		// ignore
	}

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param editingDomain the editing domain
	 * @return XtextNodeModelReconciler
	 */
	public static XtextNodeModelReconciler adapt(TransactionalEditingDomain editingDomain) {
		XtextNodeModelReconciler adapter = (XtextNodeModelReconciler) EcoreUtil.getAdapter(editingDomain
				.getResourceSet().eAdapters(), XtextNodeModelReconciler.class);
		if (adapter == null) {
			adapter = new XtextNodeModelReconciler(editingDomain);
		}
		return adapter;
	}

	/**
	 * History notification.
	 *
	 * @param event the event
	 */
	public void historyNotification(OperationHistoryEvent event) {
		int eventType = event.getEventType();
		switch (eventType) {
			case OperationHistoryEvent.DONE:
			case OperationHistoryEvent.UNDONE:
			case OperationHistoryEvent.REDONE:
				changeAggregator.endRecording();
				ICommand updateXtextResourceTextCommand = null;
				for (EObject modificationRoot : changeAggregator.getModificationRoots()) {
					XtextResource xtextResource = (XtextResource) modificationRoot.eResource();
					NodeAdapter nodeAdapter = NodeUtil.getNodeAdapter(modificationRoot);
					CompositeNode parserNode = nodeAdapter.getParserNode();
					Serializer serializer = xtextResource.getSerializer();
					String newText = serializer.serialize(modificationRoot);
					ICommand newCommand = UpdateXtextResourceTextCommand.createUpdateCommand(xtextResource, parserNode
							.getOffset(), parserNode.getLength(), newText);
					if (updateXtextResourceTextCommand == null) {
						updateXtextResourceTextCommand = newCommand;
					} else {
						updateXtextResourceTextCommand.compose(newCommand);
					}
				}
				try {
					if (updateXtextResourceTextCommand != null) {
						updateXtextResourceTextCommand.execute(null, null);
					}
				} catch (ExecutionException exc) {
					Activator.logError(exc);
				}
				changeAggregator.beginRecording();
				break;
			default:
				// ignore
		}

	}
}
