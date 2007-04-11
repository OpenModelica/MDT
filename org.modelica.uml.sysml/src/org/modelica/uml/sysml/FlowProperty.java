/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.uml2.uml.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.FlowProperty#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.FlowProperty#getAccess <em>Access</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getFlowProperty()
 * @model
 * @generated
 */
public interface FlowProperty extends Property {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelica.uml.sysml.FlowDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see org.modelica.uml.sysml.FlowDirection
	 * @see #setDirection(FlowDirection)
	 * @see org.modelica.uml.sysml.SysmlPackage#getFlowProperty_Direction()
	 * @model
	 * @generated
	 */
	FlowDirection getDirection();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.FlowProperty#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see org.modelica.uml.sysml.FlowDirection
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(FlowDirection value);

	/**
	 * Returns the value of the '<em><b>Access</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelica.uml.sysml.AccessKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access</em>' attribute.
	 * @see org.modelica.uml.sysml.AccessKind
	 * @see #setAccess(AccessKind)
	 * @see org.modelica.uml.sysml.SysmlPackage#getFlowProperty_Access()
	 * @model
	 * @generated
	 */
	AccessKind getAccess();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.FlowProperty#getAccess <em>Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access</em>' attribute.
	 * @see org.modelica.uml.sysml.AccessKind
	 * @see #getAccess()
	 * @generated
	 */
	void setAccess(AccessKind value);

} // FlowProperty