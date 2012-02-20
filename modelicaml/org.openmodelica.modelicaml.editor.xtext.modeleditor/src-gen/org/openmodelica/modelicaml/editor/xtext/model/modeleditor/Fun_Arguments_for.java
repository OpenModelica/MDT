/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fun Arguments for</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for#getFor_indices <em>For indices</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getFun_Arguments_for()
 * @model
 * @generated
 */
public interface Fun_Arguments_for extends EObject
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
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getFun_Arguments_for_For_indices()
   * @model containment="true"
   * @generated
   */
  for_indices getFor_indices();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for#getFor_indices <em>For indices</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For indices</em>' containment reference.
   * @see #getFor_indices()
   * @generated
   */
  void setFor_indices(for_indices value);

} // Fun_Arguments_for
