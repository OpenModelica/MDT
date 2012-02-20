/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>logical term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_term#getLogical_term <em>Logical term</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getlogical_term()
 * @model
 * @generated
 */
public interface logical_term extends logical_expression
{
  /**
   * Returns the value of the '<em><b>Logical term</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_term}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Logical term</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Logical term</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getlogical_term_Logical_term()
   * @model containment="true"
   * @generated
   */
  EList<logical_term> getLogical_term();

} // logical_term
