/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>General Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.GeneralRelationship#getSource <em>Source</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.GeneralRelationship#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getGeneralRelationship()
 * @model
 * @generated
 */
public interface GeneralRelationship extends ModelicaClassifier {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Element)
	 * @see org.modelica.uml.sysml.SysmlPackage#getGeneralRelationship_Source()
	 * @model
	 * @generated
	 */
	Element getSource();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.GeneralRelationship#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Element value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Element)
	 * @see org.modelica.uml.sysml.SysmlPackage#getGeneralRelationship_Target()
	 * @model
	 * @generated
	 */
	Element getTarget();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.GeneralRelationship#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Element value);

} // GeneralRelationship