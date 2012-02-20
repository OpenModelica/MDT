/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression;

import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Macro SIGNAL</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL#getComponent_reference <em>Component reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getMacro_SIGNAL()
 * @model
 * @generated
 */
public interface Macro_SIGNAL extends EObject
{
  /**
   * Returns the value of the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component reference</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component reference</em>' containment reference.
   * @see #setComponent_reference(component_reference)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getMacro_SIGNAL_Component_reference()
   * @model containment="true"
   * @generated
   */
  component_reference getComponent_reference();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL#getComponent_reference <em>Component reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Component reference</em>' containment reference.
   * @see #getComponent_reference()
   * @generated
   */
  void setComponent_reference(component_reference value);

} // Macro_SIGNAL
