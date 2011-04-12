package org.modelica.uml.sysml.diagram2.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelica.uml.sysml.DeriveReqRelationship;
import org.modelica.uml.sysml.GeneralRelationship;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaClassifier;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaConnection;
import org.modelica.uml.sysml.ModelicaEquationProperty;
import org.modelica.uml.sysml.ModelicaConnectorProperty;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.ModelicaRationale;
import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.RationaleRelationship;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.RequirementHierarchyRelationship;
import org.modelica.uml.sysml.SatisfiedBy;
import org.modelica.uml.sysml.SatisfiedByProperty;
import org.modelica.uml.sysml.SatisfiedByRelationship;
import org.modelica.uml.sysml.Satisfies;
import org.modelica.uml.sysml.SatisfiesProperty;
import org.modelica.uml.sysml.SatisfyRelationship;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.diagram2.edit.parts.DeriveReqRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.GeneralizationEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClass2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnections2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassConnectionsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquations2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassEquationsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNested2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassNestedEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParameters2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParametersEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassParts2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassPartsEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariables2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaClassVariablesEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaCompositionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaConnectionEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaEquationPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty2EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty3EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaProperty4EditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaRationaleEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.ModelicaTypeEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RationaleRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.RequirementHierarchyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiedByEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiedByPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiedByRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiedBySatisfiedByCompartmentEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiesEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiesPropertyEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfiesSatisfiesCompartmentEditPart;
import org.modelica.uml.sysml.diagram2.edit.parts.SatisfyRelationshipEditPart;
import org.modelica.uml.sysml.diagram2.providers.SysmlElementTypes;

/**
 * @generated
 */
/**
 * @author Administrator
 *
 */
public class SysmlDiagramUpdater {

	/**
	 * @generated
	 */
	public static boolean isShortcutOrphaned(View view) {
		return !view.isSetElement() || view.getElement() == null
				|| view.getElement().eIsProxy();
	}

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (SysmlVisualIDRegistry.getVisualID(view)) {
		case ModelicaClassParametersEditPart.VISUAL_ID:
			return getModelicaClassParameters_5001SemanticChildren(view);
		case ModelicaClassPartsEditPart.VISUAL_ID:
			return getModelicaClassParts_5002SemanticChildren(view);
		case ModelicaClassVariablesEditPart.VISUAL_ID:
			return getModelicaClassVariables_5003SemanticChildren(view);
		case ModelicaClassConnectionsEditPart.VISUAL_ID:
			return getModelicaClassConnections_5004SemanticChildren(view);
		case ModelicaClassNestedEditPart.VISUAL_ID:
			return getModelicaClassNested_5005SemanticChildren(view);
		case ModelicaClassEquationsEditPart.VISUAL_ID:
			return getModelicaClassEquations_5006SemanticChildren(view);
		case ModelicaClassParameters2EditPart.VISUAL_ID:
			return getModelicaClassParameters_5007SemanticChildren(view);
		case ModelicaClassParts2EditPart.VISUAL_ID:
			return getModelicaClassParts_5008SemanticChildren(view);
		case ModelicaClassVariables2EditPart.VISUAL_ID:
			return getModelicaClassVariables_5009SemanticChildren(view);
		case ModelicaClassConnections2EditPart.VISUAL_ID:
			return getModelicaClassConnections_5010SemanticChildren(view);
		case ModelicaClassNested2EditPart.VISUAL_ID:
			return getModelicaClassNested_5011SemanticChildren(view);
		case ModelicaClassEquations2EditPart.VISUAL_ID:
			return getModelicaClassEquations_5012SemanticChildren(view);
		case SatisfiedBySatisfiedByCompartmentEditPart.VISUAL_ID:
			return getSatisfiedBySatisfiedByCompartment_5013SemanticChildren(view);
		case SatisfiesSatisfiesCompartmentEditPart.VISUAL_ID:
			return getSatisfiesSatisfiesCompartment_5014SemanticChildren(view);
		case ModelEditPart.VISUAL_ID:
			return getModel_79SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassParameters_5001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaPropertyEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassParts_5002SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaProperty2EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassVariables_5003SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaProperty3EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassConnections_5004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaProperty4EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassNested_5005SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNestedClassifiers().iterator(); it
				.hasNext();) {
			Classifier childElement = (Classifier) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaClass2EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassEquations_5006SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaEquationPropertyEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassParameters_5007SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaPropertyEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassParts_5008SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaProperty2EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassVariables_5009SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaProperty3EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassConnections_5010SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaProperty4EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassNested_5011SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNestedClassifiers().iterator(); it
				.hasNext();) {
			Classifier childElement = (Classifier) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaClass2EditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClassEquations_5012SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ModelicaClass modelElement = (ModelicaClass) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOwnedAttributes().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaEquationPropertyEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedBySatisfiedByCompartment_5013SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		SatisfiedBy modelElement = (SatisfiedBy) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getSatisfiedBy().iterator(); it
				.hasNext();) {
			SatisfiedByProperty childElement = (SatisfiedByProperty) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SatisfiedByPropertyEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiesSatisfiesCompartment_5014SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Satisfies modelElement = (Satisfies) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getSatisfies().iterator(); it.hasNext();) {
			SatisfiesProperty childElement = (SatisfiesProperty) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SatisfiesPropertyEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModel_79SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Model modelElement = (Model) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getPackagedElements().iterator(); it
				.hasNext();) {
			PackageableElement childElement = (PackageableElement) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaClassEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ModelicaTypeEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RequirementEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SatisfiedByEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SatisfiesEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getOwnedComments().iterator(); it
				.hasNext();) {
			Comment childElement = (Comment) it.next();
			int visualID = SysmlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ModelicaRationaleEditPart.VISUAL_ID) {
				result.add(new SysmlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (SysmlVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_79ContainedLinks(view);
		case ModelicaClassEditPart.VISUAL_ID:
			return getModelicaClass_1001ContainedLinks(view);
		case ModelicaRationaleEditPart.VISUAL_ID:
			return getModelicaRationale_1002ContainedLinks(view);
		case ModelicaTypeEditPart.VISUAL_ID:
			return getModelicaType_1003ContainedLinks(view);
		case RequirementEditPart.VISUAL_ID:
			return getRequirement_1004ContainedLinks(view);
		case SatisfiedByEditPart.VISUAL_ID:
			return getSatisfiedBy_1005ContainedLinks(view);
		case SatisfiesEditPart.VISUAL_ID:
			return getSatisfies_1006ContainedLinks(view);
		case ModelicaPropertyEditPart.VISUAL_ID:
			return getModelicaProperty_2001ContainedLinks(view);
		case ModelicaProperty2EditPart.VISUAL_ID:
			return getModelicaProperty_2002ContainedLinks(view);
		case ModelicaProperty3EditPart.VISUAL_ID:
			return getModelicaProperty_2003ContainedLinks(view);
		case ModelicaProperty4EditPart.VISUAL_ID:
			return getModelicaProperty_2004ContainedLinks(view);
		case ModelicaClass2EditPart.VISUAL_ID:
			return getModelicaClass_2005ContainedLinks(view);
		case ModelicaEquationPropertyEditPart.VISUAL_ID:
			return getModelicaEquationProperty_2006ContainedLinks(view);
		case SatisfiedByPropertyEditPart.VISUAL_ID:
			return getSatisfiedByProperty_2007ContainedLinks(view);
		case SatisfiesPropertyEditPart.VISUAL_ID:
			return getSatisfiesProperty_2008ContainedLinks(view);
		case GeneralizationEditPart.VISUAL_ID:
			return getGeneralization_3001ContainedLinks(view);
		case SatisfyRelationshipEditPart.VISUAL_ID:
			return getSatisfyRelationship_3002ContainedLinks(view);
		case DeriveReqRelationshipEditPart.VISUAL_ID:
			return getDeriveReqRelationship_3003ContainedLinks(view);
		case ModelicaCompositionEditPart.VISUAL_ID:
			return getModelicaComposition_3004ContainedLinks(view);
		case RationaleRelationshipEditPart.VISUAL_ID:
			return getRationaleRelationship_3005ContainedLinks(view);
		case SatisfiedByRelationshipEditPart.VISUAL_ID:
			return getSatisfiedByRelationship_3006ContainedLinks(view);
		case GeneralRelationshipEditPart.VISUAL_ID:
			return getGeneralRelationship_3007ContainedLinks(view);
		case RequirementHierarchyRelationshipEditPart.VISUAL_ID:
			return getRequirementHierarchyRelationship_3008ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (SysmlVisualIDRegistry.getVisualID(view)) {
		case ModelicaClassEditPart.VISUAL_ID:
			return getModelicaClass_1001IncomingLinks(view);
		case ModelicaRationaleEditPart.VISUAL_ID:
			return getModelicaRationale_1002IncomingLinks(view);
		case ModelicaTypeEditPart.VISUAL_ID:
			return getModelicaType_1003IncomingLinks(view);
		case RequirementEditPart.VISUAL_ID:
			return getRequirement_1004IncomingLinks(view);
		case SatisfiedByEditPart.VISUAL_ID:
			return getSatisfiedBy_1005IncomingLinks(view);
		case SatisfiesEditPart.VISUAL_ID:
			return getSatisfies_1006IncomingLinks(view);
		case ModelicaPropertyEditPart.VISUAL_ID:
			return getModelicaProperty_2001IncomingLinks(view);
		case ModelicaProperty2EditPart.VISUAL_ID:
			return getModelicaProperty_2002IncomingLinks(view);
		case ModelicaProperty3EditPart.VISUAL_ID:
			return getModelicaProperty_2003IncomingLinks(view);
		case ModelicaProperty4EditPart.VISUAL_ID:
			return getModelicaProperty_2004IncomingLinks(view);
		case ModelicaClass2EditPart.VISUAL_ID:
			return getModelicaClass_2005IncomingLinks(view);
		case ModelicaEquationPropertyEditPart.VISUAL_ID:
			return getModelicaEquationProperty_2006IncomingLinks(view);
		case SatisfiedByPropertyEditPart.VISUAL_ID:
			return getSatisfiedByProperty_2007IncomingLinks(view);
		case SatisfiesPropertyEditPart.VISUAL_ID:
			return getSatisfiesProperty_2008IncomingLinks(view);
		case GeneralizationEditPart.VISUAL_ID:
			return getGeneralization_3001IncomingLinks(view);
		case SatisfyRelationshipEditPart.VISUAL_ID:
			return getSatisfyRelationship_3002IncomingLinks(view);
		case DeriveReqRelationshipEditPart.VISUAL_ID:
			return getDeriveReqRelationship_3003IncomingLinks(view);
		case ModelicaCompositionEditPart.VISUAL_ID:
			return getModelicaComposition_3004IncomingLinks(view);
		case RationaleRelationshipEditPart.VISUAL_ID:
			return getRationaleRelationship_3005IncomingLinks(view);
		case SatisfiedByRelationshipEditPart.VISUAL_ID:
			return getSatisfiedByRelationship_3006IncomingLinks(view);
		case GeneralRelationshipEditPart.VISUAL_ID:
			return getGeneralRelationship_3007IncomingLinks(view);
		case RequirementHierarchyRelationshipEditPart.VISUAL_ID:
			return getRequirementHierarchyRelationship_3008IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (SysmlVisualIDRegistry.getVisualID(view)) {
		case ModelicaClassEditPart.VISUAL_ID:
			return getModelicaClass_1001OutgoingLinks(view);
		case ModelicaRationaleEditPart.VISUAL_ID:
			return getModelicaRationale_1002OutgoingLinks(view);
		case ModelicaTypeEditPart.VISUAL_ID:
			return getModelicaType_1003OutgoingLinks(view);
		case RequirementEditPart.VISUAL_ID:
			return getRequirement_1004OutgoingLinks(view);
		case SatisfiedByEditPart.VISUAL_ID:
			return getSatisfiedBy_1005OutgoingLinks(view);
		case SatisfiesEditPart.VISUAL_ID:
			return getSatisfies_1006OutgoingLinks(view);
		case ModelicaPropertyEditPart.VISUAL_ID:
			return getModelicaProperty_2001OutgoingLinks(view);
		case ModelicaProperty2EditPart.VISUAL_ID:
			return getModelicaProperty_2002OutgoingLinks(view);
		case ModelicaProperty3EditPart.VISUAL_ID:
			return getModelicaProperty_2003OutgoingLinks(view);
		case ModelicaProperty4EditPart.VISUAL_ID:
			return getModelicaProperty_2004OutgoingLinks(view);
		case ModelicaClass2EditPart.VISUAL_ID:
			return getModelicaClass_2005OutgoingLinks(view);
		case ModelicaEquationPropertyEditPart.VISUAL_ID:
			return getModelicaEquationProperty_2006OutgoingLinks(view);
		case SatisfiedByPropertyEditPart.VISUAL_ID:
			return getSatisfiedByProperty_2007OutgoingLinks(view);
		case SatisfiesPropertyEditPart.VISUAL_ID:
			return getSatisfiesProperty_2008OutgoingLinks(view);
		case GeneralizationEditPart.VISUAL_ID:
			return getGeneralization_3001OutgoingLinks(view);
		case SatisfyRelationshipEditPart.VISUAL_ID:
			return getSatisfyRelationship_3002OutgoingLinks(view);
		case DeriveReqRelationshipEditPart.VISUAL_ID:
			return getDeriveReqRelationship_3003OutgoingLinks(view);
		case ModelicaCompositionEditPart.VISUAL_ID:
			return getModelicaComposition_3004OutgoingLinks(view);
		case RationaleRelationshipEditPart.VISUAL_ID:
			return getRationaleRelationship_3005OutgoingLinks(view);
		case SatisfiedByRelationshipEditPart.VISUAL_ID:
			return getSatisfiedByRelationship_3006OutgoingLinks(view);
		case GeneralRelationshipEditPart.VISUAL_ID:
			return getGeneralRelationship_3007OutgoingLinks(view);
		case RequirementHierarchyRelationshipEditPart.VISUAL_ID:
			return getRequirementHierarchyRelationship_3008OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModel_79ContainedLinks(View view) {
		Model modelElement = (Model) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_SatisfyRelationship_3002(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_DeriveReqRelationship_3003(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_SatisfiedByRelationship_3006(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated NOT
	 */
	public static List getModelicaClass_1001ContainedLinks(View view) {
		ModelicaClass modelElement = (ModelicaClass) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_ModelicaConnection_3009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaRationale_1002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModelicaType_1003ContainedLinks(View view) {
		ModelicaType modelElement = (ModelicaType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRequirement_1004ContainedLinks(View view) {
		Requirement modelElement = (Requirement) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedBy_1005ContainedLinks(View view) {
		SatisfiedBy modelElement = (SatisfiedBy) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfies_1006ContainedLinks(View view) {
		Satisfies modelElement = (Satisfies) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClass_2005ContainedLinks(View view) {
		ModelicaClass modelElement = (ModelicaClass) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaEquationProperty_2006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedByProperty_2007ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiesProperty_2008ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGeneralization_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSatisfyRelationship_3002ContainedLinks(View view) {
		SatisfyRelationship modelElement = (SatisfyRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDeriveReqRelationship_3003ContainedLinks(View view) {
		DeriveReqRelationship modelElement = (DeriveReqRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaComposition_3004ContainedLinks(View view) {
		ModelicaComposition modelElement = (ModelicaComposition) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRationaleRelationship_3005ContainedLinks(View view) {
		RationaleRelationship modelElement = (RationaleRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedByRelationship_3006ContainedLinks(View view) {
		SatisfiedByRelationship modelElement = (SatisfiedByRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGeneralRelationship_3007ContainedLinks(View view) {
		GeneralRelationship modelElement = (GeneralRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * 
	 * @param view
	 * @return
	 */
	public static List getModelicaPort_2070ContainedLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		List result = new LinkedList();
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRequirementHierarchyRelationship_3008ContainedLinks(
			View view) {
		RequirementHierarchyRelationship modelElement = (RequirementHierarchyRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClass_1001IncomingLinks(View view) {
		ModelicaClass modelElement = (ModelicaClass) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_SatisfyRelationship_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaRationale_1002IncomingLinks(View view) {
		ModelicaRationale modelElement = (ModelicaRationale) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result
				.addAll(getIncomingTypeModelFacetLinks_RationaleRelationship_3005(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaType_1003IncomingLinks(View view) {
		ModelicaType modelElement = (ModelicaType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRequirement_1004IncomingLinks(View view) {
		Requirement modelElement = (Requirement) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result
				.addAll(getIncomingTypeModelFacetLinks_DeriveReqRelationship_3003(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		result
				.addAll(getIncomingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(
						modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedBy_1005IncomingLinks(View view) {
		SatisfiedBy modelElement = (SatisfiedBy) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result
				.addAll(getIncomingTypeModelFacetLinks_SatisfiedByRelationship_3006(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfies_1006IncomingLinks(View view) {
		Satisfies modelElement = (Satisfies) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2001IncomingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2002IncomingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2003IncomingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2004IncomingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClass_2005IncomingLinks(View view) {
		ModelicaClass modelElement = (ModelicaClass) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_SatisfyRelationship_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaEquationProperty_2006IncomingLinks(View view) {
		ModelicaEquationProperty modelElement = (ModelicaEquationProperty) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedByProperty_2007IncomingLinks(View view) {
		SatisfiedByProperty modelElement = (SatisfiedByProperty) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiesProperty_2008IncomingLinks(View view) {
		SatisfiesProperty modelElement = (SatisfiesProperty) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGeneralization_3001IncomingLinks(View view) {
		Generalization modelElement = (Generalization) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfyRelationship_3002IncomingLinks(View view) {
		SatisfyRelationship modelElement = (SatisfyRelationship) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDeriveReqRelationship_3003IncomingLinks(View view) {
		DeriveReqRelationship modelElement = (DeriveReqRelationship) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaComposition_3004IncomingLinks(View view) {
		ModelicaComposition modelElement = (ModelicaComposition) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRationaleRelationship_3005IncomingLinks(View view) {
		RationaleRelationship modelElement = (RationaleRelationship) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedByRelationship_3006IncomingLinks(View view) {
		SatisfiedByRelationship modelElement = (SatisfiedByRelationship) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGeneralRelationship_3007IncomingLinks(View view) {
		GeneralRelationship modelElement = (GeneralRelationship) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRequirementHierarchyRelationship_3008IncomingLinks(
			View view) {
		RequirementHierarchyRelationship modelElement = (RequirementHierarchyRelationship) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClass_1001OutgoingLinks(View view) {
		ModelicaClass modelElement = (ModelicaClass) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaRationale_1002OutgoingLinks(View view) {
		ModelicaRationale modelElement = (ModelicaRationale) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaType_1003OutgoingLinks(View view) {
		ModelicaType modelElement = (ModelicaType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRequirement_1004OutgoingLinks(View view) {
		Requirement modelElement = (Requirement) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_SatisfyRelationship_3002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_DeriveReqRelationship_3003(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_SatisfiedByRelationship_3006(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedBy_1005OutgoingLinks(View view) {
		SatisfiedBy modelElement = (SatisfiedBy) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfies_1006OutgoingLinks(View view) {
		Satisfies modelElement = (Satisfies) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2001OutgoingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2002OutgoingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2003OutgoingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaProperty_2004OutgoingLinks(View view) {
		ModelicaProperty modelElement = (ModelicaProperty) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaClass_2005OutgoingLinks(View view) {
		ModelicaClass modelElement = (ModelicaClass) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaEquationProperty_2006OutgoingLinks(View view) {
		ModelicaEquationProperty modelElement = (ModelicaEquationProperty) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedByProperty_2007OutgoingLinks(View view) {
		SatisfiedByProperty modelElement = (SatisfiedByProperty) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiesProperty_2008OutgoingLinks(View view) {
		SatisfiesProperty modelElement = (SatisfiesProperty) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGeneralization_3001OutgoingLinks(View view) {
		Generalization modelElement = (Generalization) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfyRelationship_3002OutgoingLinks(View view) {
		SatisfyRelationship modelElement = (SatisfyRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDeriveReqRelationship_3003OutgoingLinks(View view) {
		DeriveReqRelationship modelElement = (DeriveReqRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModelicaComposition_3004OutgoingLinks(View view) {
		ModelicaComposition modelElement = (ModelicaComposition) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRationaleRelationship_3005OutgoingLinks(View view) {
		RationaleRelationship modelElement = (RationaleRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getSatisfiedByRelationship_3006OutgoingLinks(View view) {
		SatisfiedByRelationship modelElement = (SatisfiedByRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGeneralRelationship_3007OutgoingLinks(View view) {
		GeneralRelationship modelElement = (GeneralRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRequirementHierarchyRelationship_3008OutgoingLinks(
			View view) {
		RequirementHierarchyRelationship modelElement = (RequirementHierarchyRelationship) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Generalization_3001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Generalization_3001(
			Classifier container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getGeneralizations().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof Generalization) {
				continue;
			}
			Generalization link = (Generalization) linkObject;
			if (GeneralizationEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Classifier dst = link.getGeneral();
			result.add(new SysmlLinkDescriptor(container, dst, link,
					SysmlElementTypes.Generalization_3001,
					GeneralizationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_SatisfyRelationship_3002(
			Package container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof SatisfyRelationship) {
				continue;
			}
			SatisfyRelationship link = (SatisfyRelationship) linkObject;
			if (SatisfyRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ModelicaClass dst = link.getTarget();
			Requirement src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.SatisfyRelationship_3002,
					SatisfyRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_DeriveReqRelationship_3003(
			Package container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof DeriveReqRelationship) {
				continue;
			}
			DeriveReqRelationship link = (DeriveReqRelationship) linkObject;
			if (DeriveReqRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Requirement dst = link.getTarget();
			Requirement src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.DeriveReqRelationship_3003,
					DeriveReqRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_ModelicaComposition_3004(
			Package container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof ModelicaComposition) {
				continue;
			}
			ModelicaComposition link = (ModelicaComposition) linkObject;
			if (ModelicaCompositionEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTarget();
			Element src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.ModelicaComposition_3004,
					ModelicaCompositionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_RationaleRelationship_3005(
			Package container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof RationaleRelationship) {
				continue;
			}
			RationaleRelationship link = (RationaleRelationship) linkObject;
			if (RationaleRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ModelicaRationale dst = link.getTarget();
			ModelicaClassifier src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.RationaleRelationship_3005,
					RationaleRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_SatisfiedByRelationship_3006(
			Package container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof SatisfiedByRelationship) {
				continue;
			}
			SatisfiedByRelationship link = (SatisfiedByRelationship) linkObject;
			if (SatisfiedByRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			SatisfiedBy dst = link.getTarget();
			Requirement src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.SatisfiedByRelationship_3006,
					SatisfiedByRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_GeneralRelationship_3007(
			Package container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof GeneralRelationship) {
				continue;
			}
			GeneralRelationship link = (GeneralRelationship) linkObject;
			if (GeneralRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTarget();
			Element src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.GeneralRelationship_3007,
					GeneralRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_RequirementHierarchyRelationship_3008(
			Package container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof RequirementHierarchyRelationship) {
				continue;
			}
			RequirementHierarchyRelationship link = (RequirementHierarchyRelationship) linkObject;
			if (RequirementHierarchyRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Requirement dst = link.getTarget();
			Element src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.RequirementHierarchyRelationship_3008,
					RequirementHierarchyRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	private static Collection getContainedTypeModelFacetLinks_ModelicaConnection_3009(
			Class container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getOwnedConnectors().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof ModelicaConnection) {
				continue;
			}
			ModelicaConnection link = (ModelicaConnection) linkObject;
			if (ModelicaConnectionEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ModelicaProperty dst = (ModelicaProperty) link.getEnds().get(0);
			ModelicaProperty src = (ModelicaProperty) link.getEnds().get(1);
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.ModelicaConnection_3009,
					ModelicaConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Generalization_3001(
			Classifier target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != UMLPackage.eINSTANCE
					.getGeneralization_General()
					|| false == setting.getEObject() instanceof Generalization) {
				continue;
			}
			Generalization link = (Generalization) setting.getEObject();
			if (GeneralizationEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			if (false == link.eContainer() instanceof Classifier) {
				continue;
			}
			Classifier container = (Classifier) link.eContainer();
			result.add(new SysmlLinkDescriptor(container, target, link,
					SysmlElementTypes.Generalization_3001,
					GeneralizationEditPart.VISUAL_ID));

		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_SatisfyRelationship_3002(
			ModelicaClass target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != SysmlPackage.eINSTANCE
					.getSatisfyRelationship_Target()
					|| false == setting.getEObject() instanceof SatisfyRelationship) {
				continue;
			}
			SatisfyRelationship link = (SatisfyRelationship) setting
					.getEObject();
			if (SatisfyRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Requirement src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, target, link,
					SysmlElementTypes.SatisfyRelationship_3002,
					SatisfyRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_DeriveReqRelationship_3003(
			Requirement target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != SysmlPackage.eINSTANCE
					.getDeriveReqRelationship_Target()
					|| false == setting.getEObject() instanceof DeriveReqRelationship) {
				continue;
			}
			DeriveReqRelationship link = (DeriveReqRelationship) setting
					.getEObject();
			if (DeriveReqRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Requirement src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, target, link,
					SysmlElementTypes.DeriveReqRelationship_3003,
					DeriveReqRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_ModelicaComposition_3004(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != SysmlPackage.eINSTANCE
					.getGeneralRelationship_Target()
					|| false == setting.getEObject() instanceof ModelicaComposition) {
				continue;
			}
			ModelicaComposition link = (ModelicaComposition) setting
					.getEObject();
			if (ModelicaCompositionEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, target, link,
					SysmlElementTypes.ModelicaComposition_3004,
					ModelicaCompositionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_RationaleRelationship_3005(
			ModelicaRationale target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != SysmlPackage.eINSTANCE
					.getRationaleRelationship_Target()
					|| false == setting.getEObject() instanceof RationaleRelationship) {
				continue;
			}
			RationaleRelationship link = (RationaleRelationship) setting
					.getEObject();
			if (RationaleRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ModelicaClassifier src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, target, link,
					SysmlElementTypes.RationaleRelationship_3005,
					RationaleRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_SatisfiedByRelationship_3006(
			SatisfiedBy target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != SysmlPackage.eINSTANCE
					.getSatisfiedByRelationship_Target()
					|| false == setting.getEObject() instanceof SatisfiedByRelationship) {
				continue;
			}
			SatisfiedByRelationship link = (SatisfiedByRelationship) setting
					.getEObject();
			if (SatisfiedByRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Requirement src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, target, link,
					SysmlElementTypes.SatisfiedByRelationship_3006,
					SatisfiedByRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_GeneralRelationship_3007(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != SysmlPackage.eINSTANCE
					.getGeneralRelationship_Target()
					|| false == setting.getEObject() instanceof GeneralRelationship) {
				continue;
			}
			GeneralRelationship link = (GeneralRelationship) setting
					.getEObject();
			if (GeneralRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, target, link,
					SysmlElementTypes.GeneralRelationship_3007,
					GeneralRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(
			Requirement target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != SysmlPackage.eINSTANCE
					.getRequirementHierarchyRelationship_Target()
					|| false == setting.getEObject() instanceof RequirementHierarchyRelationship) {
				continue;
			}
			RequirementHierarchyRelationship link = (RequirementHierarchyRelationship) setting
					.getEObject();
			if (RequirementHierarchyRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getSource();
			result.add(new SysmlLinkDescriptor(src, target, link,
					SysmlElementTypes.RequirementHierarchyRelationship_3008,
					RequirementHierarchyRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_SatisfyRelationship_3002(
			Requirement source) {
		Package container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof SatisfyRelationship) {
				continue;
			}
			SatisfyRelationship link = (SatisfyRelationship) linkObject;
			if (SatisfyRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ModelicaClass dst = link.getTarget();
			Requirement src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.SatisfyRelationship_3002,
					SatisfyRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_DeriveReqRelationship_3003(
			Requirement source) {
		Package container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof DeriveReqRelationship) {
				continue;
			}
			DeriveReqRelationship link = (DeriveReqRelationship) linkObject;
			if (DeriveReqRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Requirement dst = link.getTarget();
			Requirement src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.DeriveReqRelationship_3003,
					DeriveReqRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_ModelicaComposition_3004(
			Element source) {
		Package container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof ModelicaComposition) {
				continue;
			}
			ModelicaComposition link = (ModelicaComposition) linkObject;
			if (ModelicaCompositionEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTarget();
			Element src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.ModelicaComposition_3004,
					ModelicaCompositionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_RationaleRelationship_3005(
			ModelicaClassifier source) {
		Package container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof RationaleRelationship) {
				continue;
			}
			RationaleRelationship link = (RationaleRelationship) linkObject;
			if (RationaleRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			ModelicaRationale dst = link.getTarget();
			ModelicaClassifier src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.RationaleRelationship_3005,
					RationaleRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_SatisfiedByRelationship_3006(
			Requirement source) {
		Package container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof SatisfiedByRelationship) {
				continue;
			}
			SatisfiedByRelationship link = (SatisfiedByRelationship) linkObject;
			if (SatisfiedByRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			SatisfiedBy dst = link.getTarget();
			Requirement src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.SatisfiedByRelationship_3006,
					SatisfiedByRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_GeneralRelationship_3007(
			Element source) {
		Package container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof GeneralRelationship) {
				continue;
			}
			GeneralRelationship link = (GeneralRelationship) linkObject;
			if (GeneralRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTarget();
			Element src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.GeneralRelationship_3007,
					GeneralRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_RequirementHierarchyRelationship_3008(
			Element source) {
		Package container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Package) {
				container = (Package) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getPackagedElements().iterator(); links
				.hasNext();) {
			Object linkObject = links.next();
			if (false == linkObject instanceof RequirementHierarchyRelationship) {
				continue;
			}
			RequirementHierarchyRelationship link = (RequirementHierarchyRelationship) linkObject;
			if (RequirementHierarchyRelationshipEditPart.VISUAL_ID != SysmlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Requirement dst = link.getTarget();
			Element src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new SysmlLinkDescriptor(src, dst, link,
					SysmlElementTypes.RequirementHierarchyRelationship_3008,
					RequirementHierarchyRelationshipEditPart.VISUAL_ID));
		}
		return result;
	}

}
