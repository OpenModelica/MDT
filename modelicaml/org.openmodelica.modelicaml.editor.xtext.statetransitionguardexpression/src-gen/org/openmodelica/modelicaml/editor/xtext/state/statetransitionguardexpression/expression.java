/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression#getSimple_Expr <em>Simple Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getexpression()
 * @model
 * @generated
 */
public interface expression extends org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression
{
  /**
   * Returns the value of the '<em><b>Simple Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simple Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simple Expr</em>' containment reference.
   * @see #setSimple_Expr(simple_expression)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getexpression_Simple_Expr()
   * @model containment="true"
   * @generated
   */
  simple_expression getSimple_Expr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression#getSimple_Expr <em>Simple Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Simple Expr</em>' containment reference.
   * @see #getSimple_Expr()
   * @generated
   */
  void setSimple_Expr(simple_expression value);

} // expression
