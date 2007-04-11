/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.internal.impl.AssociationImpl;

import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Modelica Composition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
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
