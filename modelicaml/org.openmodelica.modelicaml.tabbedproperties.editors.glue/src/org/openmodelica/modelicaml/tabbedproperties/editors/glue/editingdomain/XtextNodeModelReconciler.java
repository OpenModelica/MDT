/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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
