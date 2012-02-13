/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>element modification or replaceable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_mod <em>Element mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_rep <em>Element rep</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_modification_or_replaceable()
 * @model
 * @generated
 */
public interface element_modification_or_replaceable extends argument
{
  /**
   * Returns the value of the '<em><b>Element mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element mod</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element mod</em>' containment reference.
   * @see #setElement_mod(element_modification)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_modification_or_replaceable_Element_mod()
   * @model containment="true"
   * @generated
   */
  element_modification getElement_mod();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_mod <em>Element mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element mod</em>' containment reference.
   * @see #getElement_mod()
   * @generated
   */
  void setElement_mod(element_modification value);

  /**
   * Returns the value of the '<em><b>Element rep</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element rep</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element rep</em>' containment reference.
   * @see #setElement_rep(element_replaceable)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_modification_or_replaceable_Element_rep()
   * @model containment="true"
   * @generated
   */
  element_replaceable getElement_rep();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_rep <em>Element rep</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element rep</em>' containment reference.
   * @see #getElement_rep()
   * @generated
   */
  void setElement_rep(element_replaceable value);

} // element_modification_or_replaceable
