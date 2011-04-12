package org.modelica.uml.sysml.diagram2.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.ModelicaClassifier;
import org.modelica.uml.sysml.ModelicaRationale;
import org.modelica.uml.sysml.RationaleRelationship;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class RationaleRelationshipCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	private final EObject source;

	/**
	 * @generated
	 */
	private final EObject target;

	/**
	 * @generated
	 */
	private Package container;

	/**
	 * @generated
	 */
	public RationaleRelationshipCreateCommand(
			CreateRelationshipRequest request, EObject source, EObject target) {
		super(request);
		this.source = source;
		this.target = target;
		if (request.getContainmentFeature() == null) {
			setContainmentFeature(UMLPackage.eINSTANCE
					.getPackage_PackagedElement());
		}

		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
				super.setElementToEdit(container);
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && !(source instanceof ModelicaClassifier)) {
			return false;
		}
		if (target != null && !(target instanceof ModelicaRationale)) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return SysmlBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateRationaleRelationship_3005(getContainer(),
						getSource(), getTarget());
	}

	/**
	 * @generated
	 */
	protected EObject doDefaultElementCreation() {
		// org.modelica.uml.sysml.RationaleRelationship newElement = (org.modelica.uml.sysml.RationaleRelationship) super.doDefaultElementCreation();
		RationaleRelationship newElement = SysmlFactory.eINSTANCE
				.createRationaleRelationship();
		getContainer().getPackagedElements().add(newElement);
		newElement.setSource(getSource());
		newElement.setTarget(getTarget());
		return newElement;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return UMLPackage.eINSTANCE.getPackage();
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in create link command"); //$NON-NLS-1$
		}
		return super.doExecuteWithResult(monitor, info);
	}

	/**
	 * @generated
	 */
	protected ConfigureRequest createConfigureRequest() {
		ConfigureRequest request = super.createConfigureRequest();
		request.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		request.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		return request;
	}

	/**
	 * @generated
	 */
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected ModelicaClassifier getSource() {
		return (ModelicaClassifier) source;
	}

	/**
	 * @generated
	 */
	protected ModelicaRationale getTarget() {
		return (ModelicaRationale) target;
	}

	/**
	 * @generated
	 */
	public Package getContainer() {
		return container;
	}
}
