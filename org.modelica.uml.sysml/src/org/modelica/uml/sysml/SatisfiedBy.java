/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Satisfied By</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.SatisfiedBy#getSatisfiedBy <em>Satisfied By</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getSatisfiedBy()
 * @model
 * @generated
 */
public interface SatisfiedBy extends ModelicaClassifier {
	/**
	 * Returns the value of the '<em><b>Satisfied By</b></em>' containment reference list.
	 * The list contents are of type {@link org.modelica.uml.sysml.SatisfiedByProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Satisfied By</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Satisfied By</em>' containment reference list.
	 * @see org.modelica.uml.sysml.SysmlPackage#getSatisfiedBy_SatisfiedBy()
	 * @model type="org.modelica.uml.sysml.SatisfiedByProperty" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList getSatisfiedBy();

} // SatisfiedBy