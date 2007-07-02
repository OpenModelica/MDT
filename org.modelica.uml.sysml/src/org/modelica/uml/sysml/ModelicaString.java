/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modelica String</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ModelicaString#getValue <em>Value</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaString#getStart <em>Start</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaString()
 * @model
 * @generated
 */
public interface ModelicaString extends ModelicaPrimitiveType {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaString_Value()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaString#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaString_Start()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getStart();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaString#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(String value);

} // ModelicaString