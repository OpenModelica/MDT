package org.modelica.uml.sysml.diagram2.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
public class ModelicaClassCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public ModelicaClassCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated NOT
	 */
	protected EClass getEClassToEdit() {
		if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaClass_1001)
			return UMLPackage.eINSTANCE.getPackage();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaModel_1001)
			return UMLPackage.eINSTANCE.getPackage();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaBlock_1001)
			return UMLPackage.eINSTANCE.getPackage();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaFunction_1001)
			return UMLPackage.eINSTANCE.getPackage();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaConnector_1001)
			return UMLPackage.eINSTANCE.getPackage();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaRecord_1001)
			return UMLPackage.eINSTANCE.getPackage();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaClass_2005)
			return UMLPackage.eINSTANCE.getClass_();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaModel_2005)
			return UMLPackage.eINSTANCE.getClass_();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaBlock_2005)
			return UMLPackage.eINSTANCE.getClass_();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaFunction_2005)
			return UMLPackage.eINSTANCE.getClass_();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaConnector_2005)
			return UMLPackage.eINSTANCE.getClass_();
		else if (((CreateElementRequest) this.getRequest()).getElementType() == SysmlElementTypes.ModelicaRecord_2005)
			return UMLPackage.eINSTANCE.getClass_();
		return null;
	}
}
