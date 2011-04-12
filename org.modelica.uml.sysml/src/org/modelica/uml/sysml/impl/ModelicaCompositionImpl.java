/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import java.util.Collection;
import java.util.Map;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.RedefinableElement;
import org.eclipse.uml2.uml.StringExpression;
import org.eclipse.uml2.uml.Substitution;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.internal.impl.DirectedRelationshipImpl;
import org.eclipse.uml2.uml.util.UMLValidator;
import org.eclipse.uml2.uml.internal.impl.AssociationImpl;

import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.GeneralRelationship;
import org.modelica.uml.sysml.ModelicaClassifier;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Modelica Composition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaCompositionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaCompositionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaCompositionImpl#isIsFlow <em>Is Flow</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaCompositionImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaCompositionImpl#getArrayDimension <em>Array Dimension</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaCompositionImpl#getAccess <em>Access</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaCompositionImpl#getDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelicaCompositionImpl extends AssociationImpl implements
		ModelicaComposition {

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Element source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Element target;

	@SuppressWarnings("restriction")
	public Property createOwnedEnd(String name, Type type) {
		return createOwnedEnd(name, type,
				SysmlPackage.Literals.MODELICA_PROPERTY);
	}

	/**
	 * The default value of the '{@link #isIsFlow() <em>Is Flow</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isIsFlow()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_FLOW_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsFlow() <em>Is Flow</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isIsFlow()
	 * @generated
	 * @ordered
	 */
	protected boolean isFlow = IS_FLOW_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final FlowDirection DIRECTION_EDEFAULT = FlowDirection.INOUT_LITERAL;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected FlowDirection direction = DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getArrayDimension() <em>Array Dimension</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArrayDimension()
	 * @generated
	 * @ordered
	 */
	protected static final String ARRAY_DIMENSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArrayDimension() <em>Array Dimension</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArrayDimension()
	 * @generated
	 * @ordered
	 */
	protected String arrayDimension = ARRAY_DIMENSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAccess() <em>Access</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAccess()
	 * @generated
	 * @ordered
	 */
	protected static final AccessKind ACCESS_EDEFAULT = AccessKind.PUBLIC_LITERAL;

	/**
	 * The cached value of the '{@link #getAccess() <em>Access</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAccess()
	 * @generated
	 * @ordered
	 */
	protected AccessKind access = ACCESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected String default_ = DEFAULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelicaCompositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SysmlPackage.Literals.MODELICA_COMPOSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Element)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SysmlPackage.MODELICA_COMPOSITION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Element newSource) {
		Element oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_COMPOSITION__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Element)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SysmlPackage.MODELICA_COMPOSITION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Element newTarget) {
		Element oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_COMPOSITION__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsFlow() {
		return isFlow;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsFlow(boolean newIsFlow) {
		boolean oldIsFlow = isFlow;
		isFlow = newIsFlow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_COMPOSITION__IS_FLOW, oldIsFlow, isFlow));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FlowDirection getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(FlowDirection newDirection) {
		FlowDirection oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_COMPOSITION__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getArrayDimension() {
		return arrayDimension;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayDimension(String newArrayDimension) {
		String oldArrayDimension = arrayDimension;
		arrayDimension = newArrayDimension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_COMPOSITION__ARRAY_DIMENSION, oldArrayDimension, arrayDimension));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AccessKind getAccess() {
		return access;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccess(AccessKind newAccess) {
		AccessKind oldAccess = access;
		access = newAccess == null ? ACCESS_EDEFAULT : newAccess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_COMPOSITION__ACCESS, oldAccess, access));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(String newDefault) {
		String oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_COMPOSITION__DEFAULT, oldDefault, default_));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SysmlPackage.MODELICA_COMPOSITION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case SysmlPackage.MODELICA_COMPOSITION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case SysmlPackage.MODELICA_COMPOSITION__IS_FLOW:
				return isIsFlow() ? Boolean.TRUE : Boolean.FALSE;
			case SysmlPackage.MODELICA_COMPOSITION__DIRECTION:
				return getDirection();
			case SysmlPackage.MODELICA_COMPOSITION__ARRAY_DIMENSION:
				return getArrayDimension();
			case SysmlPackage.MODELICA_COMPOSITION__ACCESS:
				return getAccess();
			case SysmlPackage.MODELICA_COMPOSITION__DEFAULT:
				return getDefault();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SysmlPackage.MODELICA_COMPOSITION__SOURCE:
				setSource((Element)newValue);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__TARGET:
				setTarget((Element)newValue);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__IS_FLOW:
				setIsFlow(((Boolean)newValue).booleanValue());
				return;
			case SysmlPackage.MODELICA_COMPOSITION__DIRECTION:
				setDirection((FlowDirection)newValue);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__ARRAY_DIMENSION:
				setArrayDimension((String)newValue);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__ACCESS:
				setAccess((AccessKind)newValue);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__DEFAULT:
				setDefault((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SysmlPackage.MODELICA_COMPOSITION__SOURCE:
				setSource((Element)null);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__TARGET:
				setTarget((Element)null);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__IS_FLOW:
				setIsFlow(IS_FLOW_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__ARRAY_DIMENSION:
				setArrayDimension(ARRAY_DIMENSION_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__ACCESS:
				setAccess(ACCESS_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_COMPOSITION__DEFAULT:
				setDefault(DEFAULT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SysmlPackage.MODELICA_COMPOSITION__SOURCE:
				return source != null;
			case SysmlPackage.MODELICA_COMPOSITION__TARGET:
				return target != null;
			case SysmlPackage.MODELICA_COMPOSITION__IS_FLOW:
				return isFlow != IS_FLOW_EDEFAULT;
			case SysmlPackage.MODELICA_COMPOSITION__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case SysmlPackage.MODELICA_COMPOSITION__ARRAY_DIMENSION:
				return ARRAY_DIMENSION_EDEFAULT == null ? arrayDimension != null : !ARRAY_DIMENSION_EDEFAULT.equals(arrayDimension);
			case SysmlPackage.MODELICA_COMPOSITION__ACCESS:
				return access != ACCESS_EDEFAULT;
			case SysmlPackage.MODELICA_COMPOSITION__DEFAULT:
				return DEFAULT_EDEFAULT == null ? default_ != null : !DEFAULT_EDEFAULT.equals(default_);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == ModelicaClassifier.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == GeneralRelationship.class) {
			switch (derivedFeatureID) {
				case SysmlPackage.MODELICA_COMPOSITION__SOURCE: return SysmlPackage.GENERAL_RELATIONSHIP__SOURCE;
				case SysmlPackage.MODELICA_COMPOSITION__TARGET: return SysmlPackage.GENERAL_RELATIONSHIP__TARGET;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == ModelicaClassifier.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == GeneralRelationship.class) {
			switch (baseFeatureID) {
				case SysmlPackage.GENERAL_RELATIONSHIP__SOURCE: return SysmlPackage.MODELICA_COMPOSITION__SOURCE;
				case SysmlPackage.GENERAL_RELATIONSHIP__TARGET: return SysmlPackage.MODELICA_COMPOSITION__TARGET;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isFlow: ");
		result.append(isFlow);
		result.append(", direction: ");
		result.append(direction);
		result.append(", arrayDimension: ");
		result.append(arrayDimension);
		result.append(", access: ");
		result.append(access);
		result.append(", default: ");
		result.append(default_);
		result.append(')');
		return result.toString();
	}

} // ModelicaCompositionImpl
