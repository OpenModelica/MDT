/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.DirectedRelationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modelica Composition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ModelicaComposition#isIsFlow <em>Is Flow</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaComposition#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaComposition#getArrayDimension <em>Array Dimension</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaComposition#getAccess <em>Access</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaComposition#getDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaComposition()
 * @model
 * @generated
 */
public interface ModelicaComposition extends Association, GeneralRelationship {

	
	/**
	 * Returns the value of the '<em><b>Is Flow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Flow</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Flow</em>' attribute.
	 * @see #setIsFlow(boolean)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaComposition_IsFlow()
	 * @model dataType="org.eclipse.uml2.uml.Boolean"
	 * @generated
	 */
	boolean isIsFlow();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaComposition#isIsFlow <em>Is Flow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Flow</em>' attribute.
	 * @see #isIsFlow()
	 * @generated
	 */
	void setIsFlow(boolean value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The default value is <code>"inout"</code>.
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
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaComposition_Direction()
	 * @model default="inout"
	 * @generated
	 */
	FlowDirection getDirection();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaComposition#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see org.modelica.uml.sysml.FlowDirection
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(FlowDirection value);

	/**
	 * Returns the value of the '<em><b>Array Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Dimension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Dimension</em>' attribute.
	 * @see #setArrayDimension(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaComposition_ArrayDimension()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getArrayDimension();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaComposition#getArrayDimension <em>Array Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Dimension</em>' attribute.
	 * @see #getArrayDimension()
	 * @generated
	 */
	void setArrayDimension(String value);

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
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaComposition_Access()
	 * @model
	 * @generated
	 */
	AccessKind getAccess();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaComposition#getAccess <em>Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access</em>' attribute.
	 * @see org.modelica.uml.sysml.AccessKind
	 * @see #getAccess()
	 * @generated
	 */
	void setAccess(AccessKind value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaComposition_Default()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaComposition#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

} // ModelicaComposition