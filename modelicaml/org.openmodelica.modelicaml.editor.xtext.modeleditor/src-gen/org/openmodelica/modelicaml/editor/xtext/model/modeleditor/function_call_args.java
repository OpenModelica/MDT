/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>function call args</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args#getF_arg <em>Farg</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getfunction_call_args()
 * @model
 * @generated
 */
public interface function_call_args extends initial_ref
{
  /**
   * Returns the value of the '<em><b>Farg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Farg</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Farg</em>' containment reference.
   * @see #setF_arg(function_arguments)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getfunction_call_args_F_arg()
   * @model containment="true"
   * @generated
   */
  function_arguments getF_arg();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args#getF_arg <em>Farg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Farg</em>' containment reference.
   * @see #getF_arg()
   * @generated
   */
  void setF_arg(function_arguments value);

} // function_call_args
