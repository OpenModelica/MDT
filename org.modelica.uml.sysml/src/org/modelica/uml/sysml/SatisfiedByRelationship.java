/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Satisfied By Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.SatisfiedByRelationship#getSource <em>Source</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.SatisfiedByRelationship#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getSatisfiedByRelationship()
 * @model
 * @generated
 */
public interface SatisfiedByRelationship extends ModelicaClassifier {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Requirement)
	 * @see org.modelica.uml.sysml.SysmlPackage#getSatisfiedByRelationship_Source()
	 * @model
	 * @generated
	 */
	Requirement getSource();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.SatisfiedByRelationship#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Requirement value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(SatisfiedBy)
	 * @see org.modelica.uml.sysml.SysmlPackage#getSatisfiedByRelationship_Target()
	 * @model
	 * @generated
	 */
	SatisfiedBy getTarget();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.SatisfiedByRelationship#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SatisfiedBy value);

} // SatisfiedByRelationship