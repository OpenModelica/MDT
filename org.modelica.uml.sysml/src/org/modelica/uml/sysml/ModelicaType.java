/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.uml2.uml.Classifier;

import org.eclipse.uml2.uml.PrimitiveType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modelica Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ModelicaType#getTypeData <em>Type Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaType()
 * @model
 * @generated
 */
public interface ModelicaType extends ModelicaClassifier {
	/**
	 * Returns the value of the '<em><b>Type Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Data</em>' containment reference.
	 * @see #setTypeData(PrimitiveType)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaType_TypeData()
	 * @model containment="true"
	 * @generated
	 */
	PrimitiveType getTypeData();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaType#getTypeData <em>Type Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Data</em>' containment reference.
	 * @see #getTypeData()
	 * @generated
	 */
	void setTypeData(PrimitiveType value);

} // ModelicaType