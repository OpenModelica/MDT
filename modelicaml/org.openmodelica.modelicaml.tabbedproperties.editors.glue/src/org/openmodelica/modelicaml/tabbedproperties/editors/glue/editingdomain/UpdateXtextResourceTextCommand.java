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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.xtext.resource.XtextResource;

// TODO: Auto-generated Javadoc
/**
 * A Command that deactivates the {@link ChangeAggregatorAdapter} and updates a textual section of an Xtext model in an
 * Xtext resource. Used to avoid cycles in the change aggregation.
 * 
 * @author koehnlein
 */
public class UpdateXtextResourceTextCommand {

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param xtextResource the xtext resource
	 * @param offset the offset
	 * @param length the length
	 * @param newText the new text
	 * @return Command
	 */
	public static Command createEMFCommand(final XtextResource xtextResource, final int offset, final int length,
			final String newText) {
		final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(xtextResource);
		if (editingDomain == null) {
			return null;
		}
		ResourceSet resourceSet = editingDomain.getResourceSet();
		final ChangeAggregatorAdapter changeAggregator = (ChangeAggregatorAdapter) EcoreUtil.getAdapter(resourceSet
				.eAdapters(), ChangeAggregatorAdapter.class);
		return new RecordingCommand(editingDomain, "update xtext resource") {
			@Override
			protected void doExecute() {
				try {
					if (changeAggregator != null) {
						changeAggregator.setSuspended(true);
					}
					xtextResource.update(offset, length, newText);
					xtextResource.setModified(true);
				} finally {
					if (changeAggregator != null) {
						changeAggregator.setSuspended(false);
					}
				}

			}
		};
	}

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 *
	 * @param xtextResource the xtext resource
	 * @param offset the offset
	 * @param length the length
	 * @param newText the new text
	 * @return ICommand
	 */
	public static ICommand createUpdateCommand(final XtextResource xtextResource, final int offset, final int length,
			final String newText) {
		final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(xtextResource);
		if (editingDomain == null) {
			return null;
		}
		ResourceSet resourceSet = editingDomain.getResourceSet();
		final ChangeAggregatorAdapter changeAggregator = (ChangeAggregatorAdapter) EcoreUtil.getAdapter(resourceSet
				.eAdapters(), ChangeAggregatorAdapter.class);
		return new AbstractTransactionalCommand(editingDomain, "update xtext resource", null) {
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				try {
					if (changeAggregator != null) {
						changeAggregator.setSuspended(true);
					}
					xtextResource.update(offset, length, newText);
					xtextResource.setModified(true);
					return CommandResult.newOKCommandResult();
				} catch (Exception exc) {
					return CommandResult.newErrorCommandResult(exc);
				} finally {
					if (changeAggregator != null) {
						changeAggregator.setSuspended(false);
					}
				}
			}
		};
	}

}
