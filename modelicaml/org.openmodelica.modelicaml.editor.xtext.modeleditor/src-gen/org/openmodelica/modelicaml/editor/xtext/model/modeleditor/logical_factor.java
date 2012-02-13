/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>logical factor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor#getLogical_factor <em>Logical factor</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor#getRelation <em>Relation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getlogical_factor()
 * @model
 * @generated
 */
public interface logical_factor extends logical_term
{
  /**
   * Returns the value of the '<em><b>Logical factor</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Logical factor</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Logical factor</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getlogical_factor_Logical_factor()
   * @model containment="true"
   * @generated
   */
  EList<logical_factor> getLogical_factor();

  /**
   * Returns the value of the '<em><b>Relation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relation</em>' containment reference.
   * @see #setRelation(relation)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getlogical_factor_Relation()
   * @model containment="true"
   * @generated
   */
  relation getRelation();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor#getRelation <em>Relation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Relation</em>' containment reference.
   * @see #getRelation()
   * @generated
   */
  void setRelation(relation value);

} // logical_factor
