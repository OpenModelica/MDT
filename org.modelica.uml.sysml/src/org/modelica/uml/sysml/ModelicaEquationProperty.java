/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modelica Equation Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ModelicaEquationProperty#getEquation <em>Equation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaEquationProperty()
 * @model
 * @generated
 */
public interface ModelicaEquationProperty extends ModelicaProperty {
	/**
	 * Returns the value of the '<em><b>Equation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equation</em>' attribute.
	 * @see #setEquation(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaEquationProperty_Equation()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getEquation();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaEquationProperty#getEquation <em>Equation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Equation</em>' attribute.
	 * @see #getEquation()
	 * @generated
	 */
	void setEquation(String value);

} // ModelicaEquationProperty