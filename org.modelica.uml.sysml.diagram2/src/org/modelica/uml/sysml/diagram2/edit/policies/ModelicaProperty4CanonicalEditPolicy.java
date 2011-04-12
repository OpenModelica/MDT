package org.modelica.uml.sysml.diagram2.edit.policies;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaConnector;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;
import org.modelica.uml.sysml.ModelicaConnectorProperty;
import org.modelica.uml.sysml.impl.SysmlFactoryImpl;
import org.modelica.uml.sysml.diagram2.edit.parts.IPortOwnerEditPart;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @generated
 */
public class ModelicaProperty4CanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated NOT
	 */
	protected List getSemanticChildrenList() {
		ModelicaProperty property = this.getModelicaProperty();

		if (property == null)
			return Collections.EMPTY_LIST;

		List result = new LinkedList();

		Type type = property.getType();
		if (type instanceof ModelicaClass) {
			ModelicaClass modelicaObject = (ModelicaClass) type;
			List attributes = modelicaObject.getOwnedAttributes();

			Iterator attrIter = attributes.iterator();
			while (attrIter.hasNext()) {
				Property attr = (Property) attrIter.next();
				if (attr.getType() instanceof ModelicaConnector) {
					result.add(attr);
				}
			}

			ModelicaClass modelicaClass = (ModelicaClass) type;
			List compositionProperties = modelicaClass
					.getCompositionProperties();
			for (Iterator values = compositionProperties.iterator(); values
					.hasNext();) {
				Property nextValue = (Property) values.next();

				result.add(nextValue);

			}
		}

		return result;
	}

	/**
	 * 
	 */
	protected CreateViewRequest.ViewDescriptor getViewDescriptor(EObject element) {

		if (element instanceof ModelicaProperty) {
			//
			// create the view descritor
			String factoryHint = getDefaultFactoryHint();
			IAdaptable elementAdapter = new CanonicalElementAdapter(element,
					factoryHint);

			int pos = getViewIndexFor(element);
			CreateViewRequest.ViewDescriptor descriptor = new CreateViewRequest.ViewDescriptor(
					elementAdapter, Node.class, "2070", pos, false, host()
							.getDiagramPreferencesHint());

			return descriptor;
		}

		else
			return super.getViewDescriptor(element);
	}

	/**
	 * @return semantic element for Modelica shape
	 */
	private final ModelicaProperty getModelicaProperty() {
		return (ModelicaProperty) ViewUtil.resolveSemanticElement((View) (this
				.host().getModel()));
	}

	/**
	 * @generated
	 */
	protected boolean shouldDeleteView(View view) {
		return view.isSetElement() && view.getElement() != null
				&& view.getElement().eIsProxy();
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

}
