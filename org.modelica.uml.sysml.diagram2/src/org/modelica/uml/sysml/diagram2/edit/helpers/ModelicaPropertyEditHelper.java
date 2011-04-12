package org.modelica.uml.sysml.diagram2.edit.helpers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;

import org.modelica.uml.sysml.ModelicaConnectorProperty;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;
import org.modelica.uml.sysml.diagram2.edit.commands.ConfigureClassPartCommand;

/**
 * @generated
 */
public class ModelicaPropertyEditHelper extends SysmlBaseEditHelper {

	/**
	 * Gets a command to configure a class part.
	 */
	protected ICommand getConfigureCommand(final ConfigureRequest req) {

		if (req.getTypeToConfigure().equals(
				SysmlElementTypes.ModelicaProperty_2004)) {

			return new ConfigureClassPartCommand(req, SysmlPackage.eINSTANCE
					.getModelicaProperty()) {

				ModelicaProperty classPart = (ModelicaProperty) req
						.getElementToConfigure();

				protected CommandResult doExecuteWithResult(
						IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {

					createConnector(classPart, monitor);

					return CommandResult.newOKCommandResult(classPart);

				}

			};
		}

		return null;
	}

}