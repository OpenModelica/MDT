/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>enum list</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list#getEnnum_Lit <em>Ennum Lit</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list#getE_literal <em>Eliteral</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getenum_list()
 * @model
 * @generated
 */
public interface enum_list extends EObject
{
  /**
   * Returns the value of the '<em><b>Ennum Lit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ennum Lit</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ennum Lit</em>' attribute.
   * @see #setEnnum_Lit(String)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getenum_list_Ennum_Lit()
   * @model
   * @generated
   */
  String getEnnum_Lit();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list#getEnnum_Lit <em>Ennum Lit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ennum Lit</em>' attribute.
   * @see #getEnnum_Lit()
   * @generated
   */
  void setEnnum_Lit(String value);

  /**
   * Returns the value of the '<em><b>Eliteral</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Eliteral</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Eliteral</em>' attribute list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getenum_list_E_literal()
   * @model unique="false"
   * @generated
   */
  EList<String> getE_literal();

} // enum_list
