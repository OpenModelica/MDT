/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_indices;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>for statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_indices <em>For indices</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_statement <em>For statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_statement()
 * @model
 * @generated
 */
public interface for_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>For indices</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For indices</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For indices</em>' containment reference.
   * @see #setFor_indices(for_indices)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_statement_For_indices()
   * @model containment="true"
   * @generated
   */
  for_indices getFor_indices();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_indices <em>For indices</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For indices</em>' containment reference.
   * @see #getFor_indices()
   * @generated
   */
  void setFor_indices(for_indices value);

  /**
   * Returns the value of the '<em><b>For statement</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For statement</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For statement</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_statement_For_statement()
   * @model containment="true"
   * @generated
   */
  EList<for_statement> getFor_statement();

} // for_statement
