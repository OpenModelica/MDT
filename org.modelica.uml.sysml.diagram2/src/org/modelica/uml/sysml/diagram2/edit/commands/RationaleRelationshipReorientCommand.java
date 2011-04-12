package org.modelica.uml.sysml.diagram2.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.uml2.uml.Package;
import org.modelica.uml.sysml.ModelicaClassifier;
import org.modelica.uml.sysml.ModelicaRationale;
import org.modelica.uml.sysml.RationaleRelationship;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class RationaleRelationshipReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public RationaleRelationshipReorientCommand(
			ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (!(getElementToEdit() instanceof RationaleRelationship)) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof ModelicaClassifier && newEnd instanceof ModelicaClassifier)) {
			return false;
		}
		ModelicaRationale target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof Package)) {
			return false;
		}
		Package container = (Package) getLink().eContainer();
		return SysmlBaseItemSemanticEditPolicy.LinkConstraints
				.canExistRationaleRelationship_3005(container, getNewSource(),
						target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof ModelicaRationale && newEnd instanceof ModelicaRationale)) {
			return false;
		}
		ModelicaClassifier source = getLink().getSource();
		if (!(getLink().eContainer() instanceof Package)) {
			return false;
		}
		Package container = (Package) getLink().eContainer();
		return SysmlBaseItemSemanticEditPolicy.LinkConstraints
				.canExistRationaleRelationship_3005(container, source,
						getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected RationaleRelationship getLink() {
		return (RationaleRelationship) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected ModelicaClassifier getOldSource() {
		return (ModelicaClassifier) oldEnd;
	}

	/**
	 * @generated
	 */
	protected ModelicaClassifier getNewSource() {
		return (ModelicaClassifier) newEnd;
	}

	/**
	 * @generated
	 */
	protected ModelicaRationale getOldTarget() {
		return (ModelicaRationale) oldEnd;
	}

	/**
	 * @generated
	 */
	protected ModelicaRationale getNewTarget() {
		return (ModelicaRationale) newEnd;
	}
}
