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
package org.openmodelica.modelicaml.tabbedproperties.editors.glue.concurrency;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Lifecycle;
import org.eclipse.emf.transaction.TransactionalEditingDomainEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomainListener;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.IDirtyResource;
import org.eclipse.xtext.ui.editor.IDirtyStateManager;
import org.eclipse.xtext.ui.shared.Access;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.Activator;

import com.google.common.collect.Lists;
import com.google.inject.internal.Maps;

// TODO: Auto-generated Javadoc
/**
 * This element comes from the XText/GMF integration example, and was not originally documented.
 */
public class EditingDomainAdapter extends AdapterImpl implements ResourceSetListener,
		TransactionalEditingDomainListener {

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 */
	public static class Factory extends AdapterFactoryImpl {
		
		/**
		 * Checks if is factory for type.
		 *
		 * @param type the type
		 * @return true, if is factory for type
		 */
		@Override
		public boolean isFactoryForType(Object type) {
			return super.isFactoryForType(type == EditingDomainAdapter.class);
		}

		/**
		 * Resolve.
		 *
		 * @param object the object
		 * @param type the type
		 * @return the object
		 */
		@Override
		protected Object resolve(Object object, Object type) {
			if (object instanceof TransactionalEditingDomain) {
				return adapt(((TransactionalEditingDomain) object).getResourceSet(), type);
			}
			return super.resolve(object, type);
		}

		/**
		 * Creates the adapter.
		 *
		 * @param target the target
		 * @return the adapter
		 */
		@Override
		protected Adapter createAdapter(Notifier target) {
			if (target instanceof ResourceSet) {
				return new EditingDomainAdapter(TransactionUtil.getEditingDomain(target));
			} else {
				return null;
			}
		}
	}

	/** The editing domain. */
	private final TransactionalEditingDomain editingDomain;

	/** The dirty state manager. */
	private IDirtyStateManager dirtyStateManager;

	/** The uri2dirty resource. */
	private Map<URI, IDirtyResource> uri2dirtyResource;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(EditingDomainAdapter.class);

	/**
	 * Instantiates a new editing domain adapter.
	 *
	 * @param editingDomain the editing domain
	 */
	protected EditingDomainAdapter(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		editingDomain.addResourceSetListener(this);
		dirtyStateManager = Access.getIDirtyStateManager().get();
		uri2dirtyResource = Maps.newHashMap();
		Lifecycle lifecycle = TransactionUtil.getAdapter(editingDomain, Lifecycle.class);
		lifecycle.addTransactionalEditingDomainListener(this);
	}

	/**
	 * Gets the filter.
	 *
	 * @return the filter
	 */
	public NotificationFilter getFilter() {
		return null;
	}

	/**
	 * Checks if is aggregate precommit listener.
	 *
	 * @return true, if is aggregate precommit listener
	 */
	public boolean isAggregatePrecommitListener() {
		return true;
	}

	/**
	 * Checks if is postcommit only.
	 *
	 * @return true, if is postcommit only
	 */
	public boolean isPostcommitOnly() {
		return false;
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
		List<URI> remainingURIs = Lists.newArrayList(uri2dirtyResource.keySet());
		for (Resource resource : editingDomain.getResourceSet().getResources()) {
			if (resource instanceof XtextResource) {
				XtextResource xtextResource = (XtextResource) resource;
				remainingURIs.remove(xtextResource.getURI());
				IDirtyResource dirtyResource = uri2dirtyResource.get(xtextResource.getURI());
				if (xtextResource.isModified()) {
					if (dirtyResource == null) {
						createAndRegisterDirtyState(xtextResource);
					}
				} else {
					if (dirtyResource != null) {
						uri2dirtyResource.remove(xtextResource.getURI());
						dirtyStateManager.discardDirtyState(dirtyResource);
					}
				}
			}
		}
		// removed resources
		for (URI remainingURI : remainingURIs) {
			IDirtyResource dirtyResource = uri2dirtyResource.get(remainingURI);
			dirtyStateManager.discardDirtyState(dirtyResource);
			uri2dirtyResource.remove(remainingURI);
		}
	}

	/**
	 * Transaction about to commit.
	 *
	 * @param event the event
	 * @return the command
	 * @throws RollbackException the rollback exception
	 */
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		List<XtextResource> resourcesWithConflicts = null;
		for (Resource resource : editingDomain.getResourceSet().getResources()) {
			if (resource instanceof XtextResource && resource.isModified()) {
				XtextResource xtextResource = (XtextResource) resource;
				IDirtyResource dirtyResource = uri2dirtyResource.get(xtextResource.getURI());
				if (dirtyResource == null) {
					if (!createAndRegisterDirtyState(xtextResource)) {
						if (resourcesWithConflicts == null) {
							resourcesWithConflicts = Lists.newArrayList();
						}
						resourcesWithConflicts.add(xtextResource);
					}
				}
			}
		}
		if (resourcesWithConflicts != null) {
			if (queryApplyChanges()) {
				for (XtextResource resourceWithConflicts : resourcesWithConflicts) {
					try {
						IDirtyResource dirtyResource = createDirtyResource(resourceWithConflicts);
						// assert resource is serializable
						dirtyResource.getContents();
						dirtyStateManager.announceDirtyStateChanged(dirtyResource);
					} catch (Exception exc) {
						LOG.error("Error serializing resource", exc);
					}
				}
			} else {
				throw new RollbackException(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
						"Transaction aborted by user"));
			}
		}
		return null;
	}

	/**
	 * Creates the and register dirty state.
	 *
	 * @param xtextResource the xtext resource
	 * @return true, if successful
	 */
	protected boolean createAndRegisterDirtyState(XtextResource xtextResource) {
		IDirtyResource dirtyResource = createDirtyResource(xtextResource);
		if (dirtyResource == null) {
			return true;
		} else {
			boolean isSuccess = dirtyStateManager.manageDirtyState(dirtyResource);
			if (isSuccess) {
				uri2dirtyResource.put(xtextResource.getURI(), dirtyResource);
			}
			return isSuccess;
		}
	}

	/**
	 * Creates the dirty resource.
	 *
	 * @param xtextResource the xtext resource
	 * @return the i dirty resource
	 */
	protected IDirtyResource createDirtyResource(XtextResource xtextResource) {
		IResourceServiceProvider resourceServiceProvider = IResourceServiceProvider.Registry.INSTANCE
				.getResourceServiceProvider(xtextResource.getURI());
		if (resourceServiceProvider == null) {
			return null;
		}
		return new SimpleDirtyResource(xtextResource, resourceServiceProvider);
	}

	/**
	 * Editing domain disposing.
	 *
	 * @param event the event
	 */
	public void editingDomainDisposing(TransactionalEditingDomainEvent event) {
		dispose();
	}

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 */
	public void dispose() {
		if (uri2dirtyResource != null) {
			for (IDirtyResource dirtyResource : uri2dirtyResource.values()) {
				dirtyStateManager.discardDirtyState(dirtyResource);
			}
			uri2dirtyResource = null;
		}
		Lifecycle lifecycle = TransactionUtil.getAdapter(editingDomain, Lifecycle.class);
		if (lifecycle != null) {
			lifecycle.removeTransactionalEditingDomainListener(this);
		}
		editingDomain.removeResourceSetListener(this);
	}

	/**
	 * Query apply changes.
	 *
	 * @return true, if successful
	 */
	protected boolean queryApplyChanges() {
		DialogPrompter dialogPrompter = new DialogPrompter();
		Display.getDefault().syncExec(dialogPrompter);
		boolean yesResult = dialogPrompter.isYesResult();
		return yesResult;
	}

	/**
	 * The Class DialogPrompter.
	 */
	protected class DialogPrompter implements Runnable {

		/** The is yes result. */
		private boolean isYesResult;

		/**
		 * Checks if is yes result.
		 *
		 * @return true, if is yes result
		 */
		public boolean isYesResult() {
			return isYesResult;
		}

		/**
		 * Run.
		 */
		public void run() {
			Shell shell = Display.getDefault().getActiveShell();
			isYesResult = MessageDialog.open(MessageDialog.QUESTION, shell, "Concurrent Modification",
					"Other editors have a modified version of models you are going to change.\n"
							+ "If apply your changes you are loosing the possibility to save the others.\n"
							+ "Apply changes anyway?", SWT.NONE);
		}
	}

	/**
	 * Transaction closed.
	 *
	 * @param event the event
	 */
	public void transactionClosed(TransactionalEditingDomainEvent event) {
		// do nothing
	}

	/**
	 * Transaction closing.
	 *
	 * @param event the event
	 */
	public void transactionClosing(TransactionalEditingDomainEvent event) {
		// do nothing
	}

	/**
	 * Transaction interrupted.
	 *
	 * @param event the event
	 */
	public void transactionInterrupted(TransactionalEditingDomainEvent event) {
		// do nothing
	}

	/**
	 * Transaction started.
	 *
	 * @param event the event
	 */
	public void transactionStarted(TransactionalEditingDomainEvent event) {
		// do nothing
	}

	/**
	 * Transaction starting.
	 *
	 * @param event the event
	 */
	public void transactionStarting(TransactionalEditingDomainEvent event) {
		// do nothing
	}

}
