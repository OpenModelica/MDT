/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection;

import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>state Output Expr List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getOutput_expression_list <em>Output expression list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getComponent_ref <em>Component ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getFun_call_args <em>Fun call args</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getstate_OutputExprList()
 * @model
 * @generated
 */
public interface state_OutputExprList extends EObject
{
  /**
   * Returns the value of the '<em><b>Output expression list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output expression list</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output expression list</em>' containment reference.
   * @see #setOutput_expression_list(output_expression_list)
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getstate_OutputExprList_Output_expression_list()
   * @model containment="true"
   * @generated
   */
  output_expression_list getOutput_expression_list();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getOutput_expression_list <em>Output expression list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output expression list</em>' containment reference.
   * @see #getOutput_expression_list()
   * @generated
   */
  void setOutput_expression_list(output_expression_list value);

  /**
   * Returns the value of the '<em><b>Component ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component ref</em>' containment reference.
   * @see #setComponent_ref(component_reference)
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getstate_OutputExprList_Component_ref()
   * @model containment="true"
   * @generated
   */
  component_reference getComponent_ref();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getComponent_ref <em>Component ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Component ref</em>' containment reference.
   * @see #getComponent_ref()
   * @generated
   */
  void setComponent_ref(component_reference value);

  /**
   * Returns the value of the '<em><b>Fun call args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fun call args</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fun call args</em>' containment reference.
   * @see #setFun_call_args(function_call_args)
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getstate_OutputExprList_Fun_call_args()
   * @model containment="true"
   * @generated
   */
  function_call_args getFun_call_args();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getFun_call_args <em>Fun call args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fun call args</em>' containment reference.
   * @see #getFun_call_args()
   * @generated
   */
  void setFun_call_args(function_call_args value);

} // state_OutputExprList
