/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>guard Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.guardExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getguardExpression()
 * @model
 * @generated
 */
public interface guardExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getguardExpression_Expression()
   * @model containment="true"
   * @generated
   */
  expression getExpression();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.guardExpression#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(expression value);

} // guardExpression
