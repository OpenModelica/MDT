/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>extends clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getClass_Mod <em>Class Mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getextends_clause()
 * @model
 * @generated
 */
public interface extends_clause extends element
{
  /**
   * Returns the value of the '<em><b>Class Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class Mod</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Mod</em>' containment reference.
   * @see #setClass_Mod(class_modification)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getextends_clause_Class_Mod()
   * @model containment="true"
   * @generated
   */
  class_modification getClass_Mod();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getClass_Mod <em>Class Mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Mod</em>' containment reference.
   * @see #getClass_Mod()
   * @generated
   */
  void setClass_Mod(class_modification value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(annotation)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getextends_clause_Annotation()
   * @model containment="true"
   * @generated
   */
  annotation getAnnotation();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(annotation value);

} // extends_clause
