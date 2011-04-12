/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.uml2.uml.Interface;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.FlowSpecification#getOwnedFlowProperty <em>Owned Flow Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getFlowSpecification()
 * @model
 * @generated
 */
public interface FlowSpecification extends Interface {
	/**
	 * Returns the value of the '<em><b>Owned Flow Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelica.uml.sysml.FlowProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Flow Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Flow Property</em>' containment reference list.
	 * @see org.modelica.uml.sysml.SysmlPackage#getFlowSpecification_OwnedFlowProperty()
	 * @model type="org.modelica.uml.sysml.FlowProperty" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList getOwnedFlowProperty();

} // FlowSpecification