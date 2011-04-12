/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.uml2.uml.DataType;

import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;

import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.ModelicaClassifier;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.PropertyKind;
import org.modelica.uml.sysml.SysmlFactory;
import org.modelica.uml.sysml.SysmlPackage;
import org.modelica.uml.sysml.VariabilityKind;

import org.modelica.uml.sysml.ModelicaType;
import org.modelica.uml.sysml.ModelicaReal;
import org.modelica.uml.sysml.ModelicaString;
import org.modelica.uml.sysml.ModelicaInteger;
import org.modelica.uml.sysml.ModelicaBoolean;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Modelica Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaPropertyImpl#getVariability <em>Variability</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaPropertyImpl#isIsFlow <em>Is Flow</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaPropertyImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaPropertyImpl#getPropertyKind <em>Property Kind</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaPropertyImpl#getArrayDimension <em>Array Dimension</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.ModelicaPropertyImpl#getAccess <em>Access</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelicaPropertyImpl extends BlockPropertyImpl implements
		ModelicaProperty {
	/**
	 * The default value of the '{@link #getVariability() <em>Variability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVariability()
	 * @generated
	 * @ordered
	 */
	protected static final VariabilityKind VARIABILITY_EDEFAULT = VariabilityKind.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getVariability() <em>Variability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getVariability()
	 * @generated
	 * @ordered
	 */
	protected VariabilityKind variability = VARIABILITY_EDEFAULT;

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
	 * The default value of the '{@link #getPropertyKind() <em>Property Kind</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPropertyKind()
	 * @generated
	 * @ordered
	 */
	protected static final PropertyKind PROPERTY_KIND_EDEFAULT = PropertyKind.PARAMETER_LITERAL;

	/**
	 * The cached value of the '{@link #getPropertyKind() <em>Property Kind</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPropertyKind()
	 * @generated
	 * @ordered
	 */
	protected PropertyKind propertyKind = PROPERTY_KIND_EDEFAULT;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelicaPropertyImpl() {
		super();
	}

	@SuppressWarnings("restriction")
	@Override
	public void setType(Type newType) {

		if (newType == null) {
			return;
		}
/*
		if (this.getType() instanceof ModelicaType) {
			this.getType().destroy();
		}
*/
		Type typeInstance = newType;
		if (newType instanceof PrimitiveType
				&& newType.getName().equals("Real")) {
			if(!(typeInstance instanceof ModelicaReal)){
			ModelicaReal realType = SysmlFactory.eINSTANCE.createModelicaReal();
			typeInstance = newType.getModel().createOwnedType("Real",
					realType.eClass());
			}
		} else if (newType instanceof PrimitiveType
				&& newType.getName().equals("String")) {
			if(!(typeInstance instanceof ModelicaString)){
			ModelicaString stringType = SysmlFactory.eINSTANCE
					.createModelicaString();
			typeInstance = newType.getModel().createOwnedType("String",
					stringType.eClass());
			}
		} else if (newType instanceof PrimitiveType
				&& newType.getName().equals("Boolean")) {
			if(!(typeInstance instanceof ModelicaBoolean)){
			ModelicaBoolean booleanType = SysmlFactory.eINSTANCE
					.createModelicaBoolean();
			typeInstance = newType.getModel().createOwnedType("Boolean",
					booleanType.eClass());
			}
		} else if (newType instanceof PrimitiveType
				&& newType.getName().equals("Integer")) {
			if(!(typeInstance instanceof ModelicaInteger)){
			ModelicaInteger integerType = SysmlFactory.eINSTANCE
					.createModelicaInteger();
			typeInstance = newType.getModel().createOwnedType("Integer",
					integerType.eClass());
			}
		}
//		type = newType;	
		super.setType(newType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SysmlPackage.Literals.MODELICA_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public VariabilityKind getVariability() {
		return variability;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariability(VariabilityKind newVariability) {
		VariabilityKind oldVariability = variability;
		variability = newVariability == null ? VARIABILITY_EDEFAULT : newVariability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_PROPERTY__VARIABILITY, oldVariability, variability));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_PROPERTY__IS_FLOW, oldIsFlow, isFlow));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_PROPERTY__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyKind getPropertyKind() {
		return propertyKind;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyKind(PropertyKind newPropertyKind) {
		PropertyKind oldPropertyKind = propertyKind;
		propertyKind = newPropertyKind == null ? PROPERTY_KIND_EDEFAULT : newPropertyKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_PROPERTY__PROPERTY_KIND, oldPropertyKind, propertyKind));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_PROPERTY__ARRAY_DIMENSION, oldArrayDimension, arrayDimension));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.MODELICA_PROPERTY__ACCESS, oldAccess, access));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SysmlPackage.MODELICA_PROPERTY__VARIABILITY:
				return getVariability();
			case SysmlPackage.MODELICA_PROPERTY__IS_FLOW:
				return isIsFlow() ? Boolean.TRUE : Boolean.FALSE;
			case SysmlPackage.MODELICA_PROPERTY__DIRECTION:
				return getDirection();
			case SysmlPackage.MODELICA_PROPERTY__PROPERTY_KIND:
				return getPropertyKind();
			case SysmlPackage.MODELICA_PROPERTY__ARRAY_DIMENSION:
				return getArrayDimension();
			case SysmlPackage.MODELICA_PROPERTY__ACCESS:
				return getAccess();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SysmlPackage.MODELICA_PROPERTY__VARIABILITY:
				setVariability((VariabilityKind)newValue);
				return;
			case SysmlPackage.MODELICA_PROPERTY__IS_FLOW:
				setIsFlow(((Boolean)newValue).booleanValue());
				return;
			case SysmlPackage.MODELICA_PROPERTY__DIRECTION:
				setDirection((FlowDirection)newValue);
				return;
			case SysmlPackage.MODELICA_PROPERTY__PROPERTY_KIND:
				setPropertyKind((PropertyKind)newValue);
				return;
			case SysmlPackage.MODELICA_PROPERTY__ARRAY_DIMENSION:
				setArrayDimension((String)newValue);
				return;
			case SysmlPackage.MODELICA_PROPERTY__ACCESS:
				setAccess((AccessKind)newValue);
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
			case SysmlPackage.MODELICA_PROPERTY__VARIABILITY:
				setVariability(VARIABILITY_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_PROPERTY__IS_FLOW:
				setIsFlow(IS_FLOW_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_PROPERTY__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_PROPERTY__PROPERTY_KIND:
				setPropertyKind(PROPERTY_KIND_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_PROPERTY__ARRAY_DIMENSION:
				setArrayDimension(ARRAY_DIMENSION_EDEFAULT);
				return;
			case SysmlPackage.MODELICA_PROPERTY__ACCESS:
				setAccess(ACCESS_EDEFAULT);
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
			case SysmlPackage.MODELICA_PROPERTY__VARIABILITY:
				return variability != VARIABILITY_EDEFAULT;
			case SysmlPackage.MODELICA_PROPERTY__IS_FLOW:
				return isFlow != IS_FLOW_EDEFAULT;
			case SysmlPackage.MODELICA_PROPERTY__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case SysmlPackage.MODELICA_PROPERTY__PROPERTY_KIND:
				return propertyKind != PROPERTY_KIND_EDEFAULT;
			case SysmlPackage.MODELICA_PROPERTY__ARRAY_DIMENSION:
				return ARRAY_DIMENSION_EDEFAULT == null ? arrayDimension != null : !ARRAY_DIMENSION_EDEFAULT.equals(arrayDimension);
			case SysmlPackage.MODELICA_PROPERTY__ACCESS:
				return access != ACCESS_EDEFAULT;
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
		result.append(" (variability: ");
		result.append(variability);
		result.append(", isFlow: ");
		result.append(isFlow);
		result.append(", direction: ");
		result.append(direction);
		result.append(", propertyKind: ");
		result.append(propertyKind);
		result.append(", arrayDimension: ");
		result.append(arrayDimension);
		result.append(", access: ");
		result.append(access);
		result.append(')');
		return result.toString();
	}

} // ModelicaPropertyImpl
