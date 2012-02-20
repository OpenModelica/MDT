/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>activity Control Flow Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression#getEpression <em>Epression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage#getactivityControlFlowExpression()
 * @model
 * @generated
 */
public interface activityControlFlowExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Epression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Epression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Epression</em>' containment reference.
   * @see #setEpression(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage#getactivityControlFlowExpression_Epression()
   * @model containment="true"
   * @generated
   */
  expression getEpression();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression#getEpression <em>Epression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Epression</em>' containment reference.
   * @see #getEpression()
   * @generated
   */
  void setEpression(expression value);

} // activityControlFlowExpression
