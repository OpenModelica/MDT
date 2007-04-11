/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.Block#isIsEncapsulated <em>Is Encapsulated</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getBlock()
 * @model
 * @generated
 */
public interface Block extends org.eclipse.uml2.uml.Class {
	/**
	 * Returns the value of the '<em><b>Is Encapsulated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Encapsulated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Encapsulated</em>' attribute.
	 * @see #setIsEncapsulated(boolean)
	 * @see org.modelica.uml.sysml.SysmlPackage#getBlock_IsEncapsulated()
	 * @model dataType="org.eclipse.uml2.uml.Boolean"
	 * @generated
	 */
	boolean isIsEncapsulated();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Block#isIsEncapsulated <em>Is Encapsulated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Encapsulated</em>' attribute.
	 * @see #isIsEncapsulated()
	 * @generated
	 */
	void setIsEncapsulated(boolean value);

} // Block