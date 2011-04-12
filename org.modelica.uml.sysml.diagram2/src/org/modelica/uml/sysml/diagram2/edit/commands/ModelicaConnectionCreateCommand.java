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
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaConnection;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.diagram2.edit.policies.SysmlBaseItemSemanticEditPolicy;
import org.modelica.uml.sysml.impl.SysmlFactoryImpl;

public class ModelicaConnectionCreateCommand extends CreateElementCommand{
	
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
	private ModelicaClass container;

	
	/**
	 * @generated NOT
	 */
	public ModelicaConnectionCreateCommand(CreateRelationshipRequest request,
			EObject source, EObject target) {
		super(request);
		
		
		this.source = source;
		this.target = target;

		if(request.getContainer() instanceof ModelicaClass)
			this.container = (ModelicaClass)request.getContainer();
		
		if (target instanceof ModelicaProperty) {
			ModelicaProperty nTarget = (ModelicaProperty) target;
			if (nTarget.eContainer() instanceof ModelicaClass)
				this.container = (ModelicaClass)nTarget.eContainer();	
		}
		
		if (request.getContainmentFeature() == null) {
			setContainmentFeature(UMLPackage.eINSTANCE
					.getStructuredClassifier_OwnedConnector());
		}

		
	}
	
	public boolean canExecute() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && !(source instanceof Element)) {
			return false;
		}
		if (target != null && !(target instanceof Element)) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}	
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}		
		if (source instanceof ModelicaProperty) {
			ModelicaProperty nSource = (ModelicaProperty) source;
			if(nSource.eContainer() instanceof ModelicaComposition){
				ModelicaComposition econtainer = (ModelicaComposition)nSource.eContainer();
				if (econtainer.getDirection() != FlowDirection.OUT_LITERAL)
					return false;
			}				
		} 

		if (target instanceof ModelicaProperty) {	
			ModelicaProperty nTarget = (ModelicaProperty) target;
			if(nTarget.eContainer() instanceof ModelicaComposition){
				ModelicaComposition econtainer = (ModelicaComposition)nTarget.eContainer();
				if (econtainer.getDirection() != FlowDirection.IN_LITERAL)
					return false;
			}				
		} 
		return true;
	}
		
		/**
		 * @generated NOT
		 */
		protected EObject doDefaultElementCreation() {
			// org.modelica.uml.sysml.ModelicaComposition newElement = (org.modelica.uml.sysml.ModelicaComposition) super.doDefaultElementCreation();
			/*ModelicaComposition newElement = SysmlFactory.eINSTANCE
					.createModelicaComposition();
			getContainer().getPackagedElements().add(newElement);*/
			
			ModelicaConnection modelicaConnector = SysmlFactoryImpl.eINSTANCE.createModelicaConnection();	
			ConnectorEnd sourceEnd = modelicaConnector.createEnd();
			sourceEnd.setRole((ModelicaProperty)getSource());
			
			ConnectorEnd targetEnd = modelicaConnector.createEnd();
			targetEnd.setRole((ModelicaProperty)getTarget());
			modelicaConnector.setName("connector");
			
			((ModelicaClass) getElementToEdit()).
				getOwnedConnectors().add(modelicaConnector);
			return modelicaConnector;
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return SysmlPackage.eINSTANCE.getModelicaClass();
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
		protected Element getSource() {
			return (Element) source;
		}

		/**
		 * @generated
		 */
		protected Element getTarget() {
			return (Element) target;
		}

		/**
		 * @generated
		 */
		public ModelicaClass getContainer() {
			return container;
		}
		
		protected EObject getElementToEdit() {
			return getContainer();
		}

}
