/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getLevel <em>Level</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getId <em>Id</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getDerivedFrom <em>Derived From</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getDerived <em>Derived</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getStatus <em>Status</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getSatisfiedBy <em>Satisfied By</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getVerifiedBy <em>Verified By</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getTracedTo <em>Traced To</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getRefinedBy <em>Refined By</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getMaster <em>Master</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.Requirement#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement()
 * @model
 * @generated
 */
public interface Requirement extends Block {
	/**
	 * Returns the value of the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level</em>' attribute.
	 * @see #setLevel(int)
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_Level()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getLevel();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Requirement#getLevel <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' attribute.
	 * @see #getLevel()
	 * @generated
	 */
	void setLevel(int value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_Id()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Requirement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(int)
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_Priority()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getPriority();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Requirement#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(int value);

	/**
	 * Returns the value of the '<em><b>Derived From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived From</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived From</em>' reference.
	 * @see #setDerivedFrom(Requirement)
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_DerivedFrom()
	 * @model
	 * @generated
	 */
	Requirement getDerivedFrom();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Requirement#getDerivedFrom <em>Derived From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derived From</em>' reference.
	 * @see #getDerivedFrom()
	 * @generated
	 */
	void setDerivedFrom(Requirement value);

	/**
	 * Returns the value of the '<em><b>Derived</b></em>' reference list.
	 * The list contents are of type {@link org.modelica.uml.sysml.Requirement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived</em>' reference list.
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_Derived()
	 * @model type="org.modelica.uml.sysml.Requirement"
	 * @generated
	 */
	EList getDerived();

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelica.uml.sysml.StatusKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see org.modelica.uml.sysml.StatusKind
	 * @see #setStatus(StatusKind)
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_Status()
	 * @model
	 * @generated
	 */
	StatusKind getStatus();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Requirement#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see org.modelica.uml.sysml.StatusKind
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(StatusKind value);

	/**
	 * Returns the value of the '<em><b>Satisfied By</b></em>' reference list.
	 * The list contents are of type {@link org.modelica.uml.sysml.ModelicaClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Satisfied By</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Satisfied By</em>' reference list.
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_SatisfiedBy()
	 * @model type="org.modelica.uml.sysml.ModelicaClass"
	 * @generated
	 */
	EList getSatisfiedBy();

	/**
	 * Returns the value of the '<em><b>Verified By</b></em>' reference list.
	 * The list contents are of type {@link org.modelica.uml.sysml.ModelicaClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verified By</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verified By</em>' reference list.
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_VerifiedBy()
	 * @model type="org.modelica.uml.sysml.ModelicaClass"
	 * @generated
	 */
	EList getVerifiedBy();

	/**
	 * Returns the value of the '<em><b>Traced To</b></em>' reference list.
	 * The list contents are of type {@link org.modelica.uml.sysml.ModelicaClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traced To</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traced To</em>' reference list.
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_TracedTo()
	 * @model type="org.modelica.uml.sysml.ModelicaClass"
	 * @generated
	 */
	EList getTracedTo();

	/**
	 * Returns the value of the '<em><b>Refined By</b></em>' reference list.
	 * The list contents are of type {@link org.modelica.uml.sysml.ModelicaClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refined By</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refined By</em>' reference list.
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_RefinedBy()
	 * @model type="org.modelica.uml.sysml.ModelicaClass"
	 * @generated
	 */
	EList getRefinedBy();

	/**
	 * Returns the value of the '<em><b>Master</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master</em>' reference.
	 * @see #setMaster(Requirement)
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_Master()
	 * @model
	 * @generated
	 */
	Requirement getMaster();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Requirement#getMaster <em>Master</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Master</em>' reference.
	 * @see #getMaster()
	 * @generated
	 */
	void setMaster(Requirement value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getRequirement_Description()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.Requirement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // Requirement