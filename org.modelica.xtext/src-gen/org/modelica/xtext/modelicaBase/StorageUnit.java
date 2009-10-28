/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Storage Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.StorageUnit#getClassDefinition <em>Class Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getStorageUnit()
 * @model
 * @generated
 */
public interface StorageUnit extends EObject
{
  /**
   * Returns the value of the '<em><b>Class Definition</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.ClassDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class Definition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Definition</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getStorageUnit_ClassDefinition()
   * @model containment="true"
   * @generated
   */
  EList<ClassDefinition> getClassDefinition();

} // StorageUnit
