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
 * A representation of the model object '<em><b>Element List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementList#getElements <em>Elements</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementList#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementList()
 * @model
 * @generated
 */
public interface ElementList extends EObject
{
  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.ClassElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementList_Elements()
   * @model containment="true"
   * @generated
   */
  EList<ClassElement> getElements();

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.Annotation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementList_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<Annotation> getAnnotations();

} // ElementList
