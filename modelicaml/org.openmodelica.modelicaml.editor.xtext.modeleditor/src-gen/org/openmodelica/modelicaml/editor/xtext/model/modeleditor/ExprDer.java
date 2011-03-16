/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expr Der</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer#getFunctionArgs <em>Function Args</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getExprDer()
 * @model
 * @generated
 */
public interface ExprDer extends primary
{
  /**
   * Returns the value of the '<em><b>Function Args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Args</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Args</em>' containment reference.
   * @see #setFunctionArgs(function_call_args)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getExprDer_FunctionArgs()
   * @model containment="true"
   * @generated
   */
  function_call_args getFunctionArgs();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer#getFunctionArgs <em>Function Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Args</em>' containment reference.
   * @see #getFunctionArgs()
   * @generated
   */
  void setFunctionArgs(function_call_args value);

} // ExprDer
