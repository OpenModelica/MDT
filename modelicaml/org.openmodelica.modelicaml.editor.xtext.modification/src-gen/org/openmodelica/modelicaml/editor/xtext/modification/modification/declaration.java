/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getRedeclaredComponentName <em>Redeclared Component Name</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getArraySubs <em>Array Subs</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getMod <em>Mod</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getdeclaration()
 * @model
 * @generated
 */
public interface declaration extends component_declaration1
{
  /**
   * Returns the value of the '<em><b>Redeclared Component Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Redeclared Component Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Redeclared Component Name</em>' attribute.
   * @see #setRedeclaredComponentName(String)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getdeclaration_RedeclaredComponentName()
   * @model
   * @generated
   */
  String getRedeclaredComponentName();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getRedeclaredComponentName <em>Redeclared Component Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Redeclared Component Name</em>' attribute.
   * @see #getRedeclaredComponentName()
   * @generated
   */
  void setRedeclaredComponentName(String value);

  /**
   * Returns the value of the '<em><b>Array Subs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Subs</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Subs</em>' containment reference.
   * @see #setArraySubs(array_subscripts)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getdeclaration_ArraySubs()
   * @model containment="true"
   * @generated
   */
  array_subscripts getArraySubs();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getArraySubs <em>Array Subs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Subs</em>' containment reference.
   * @see #getArraySubs()
   * @generated
   */
  void setArraySubs(array_subscripts value);

  /**
   * Returns the value of the '<em><b>Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mod</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mod</em>' containment reference.
   * @see #setMod(modification)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getdeclaration_Mod()
   * @model containment="true"
   * @generated
   */
  modification getMod();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getMod <em>Mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mod</em>' containment reference.
   * @see #getMod()
   * @generated
   */
  void setMod(modification value);

} // declaration
