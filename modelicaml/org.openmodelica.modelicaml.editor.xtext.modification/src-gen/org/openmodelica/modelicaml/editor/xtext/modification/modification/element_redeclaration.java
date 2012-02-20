/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>element redeclaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getClass_Def <em>Class Def</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getComp_clause1 <em>Comp clause1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getElement_R <em>Element R</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_redeclaration()
 * @model
 * @generated
 */
public interface element_redeclaration extends argument
{
  /**
   * Returns the value of the '<em><b>Class Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Def</em>' containment reference.
   * @see #setClass_Def(class_definition)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_redeclaration_Class_Def()
   * @model containment="true"
   * @generated
   */
  class_definition getClass_Def();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getClass_Def <em>Class Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Def</em>' containment reference.
   * @see #getClass_Def()
   * @generated
   */
  void setClass_Def(class_definition value);

  /**
   * Returns the value of the '<em><b>Comp clause1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp clause1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp clause1</em>' containment reference.
   * @see #setComp_clause1(component_clause1)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_redeclaration_Comp_clause1()
   * @model containment="true"
   * @generated
   */
  component_clause1 getComp_clause1();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getComp_clause1 <em>Comp clause1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comp clause1</em>' containment reference.
   * @see #getComp_clause1()
   * @generated
   */
  void setComp_clause1(component_clause1 value);

  /**
   * Returns the value of the '<em><b>Element R</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element R</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element R</em>' containment reference.
   * @see #setElement_R(element_replaceable)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_redeclaration_Element_R()
   * @model containment="true"
   * @generated
   */
  element_replaceable getElement_R();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getElement_R <em>Element R</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element R</em>' containment reference.
   * @see #getElement_R()
   * @generated
   */
  void setElement_R(element_replaceable value);

} // element_redeclaration
