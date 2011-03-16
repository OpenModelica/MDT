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
 * A representation of the model object '<em><b>for equation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation#getFor_loop <em>For loop</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation#getFor_Eqn <em>For Eqn</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_equation()
 * @model
 * @generated
 */
public interface for_equation extends EObject
{
  /**
   * Returns the value of the '<em><b>For loop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For loop</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For loop</em>' containment reference.
   * @see #setFor_loop(for_indices)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_equation_For_loop()
   * @model containment="true"
   * @generated
   */
  for_indices getFor_loop();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation#getFor_loop <em>For loop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For loop</em>' containment reference.
   * @see #getFor_loop()
   * @generated
   */
  void setFor_loop(for_indices value);

  /**
   * Returns the value of the '<em><b>For Eqn</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For Eqn</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For Eqn</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getfor_equation_For_Eqn()
   * @model containment="true"
   * @generated
   */
  EList<equation> getFor_Eqn();

} // for_equation
