/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client;

import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getLeft_hand <em>Left hand</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getClientOperation()
 * @model
 * @generated
 */
public interface ClientOperation extends EObject
{
  /**
   * Returns the value of the '<em><b>Left hand</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left hand</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left hand</em>' containment reference.
   * @see #setLeft_hand(left_hand_component_reference)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getClientOperation_Left_hand()
   * @model containment="true"
   * @generated
   */
  left_hand_component_reference getLeft_hand();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getLeft_hand <em>Left hand</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left hand</em>' containment reference.
   * @see #getLeft_hand()
   * @generated
   */
  void setLeft_hand(left_hand_component_reference value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getClientOperation_Expr()
   * @model containment="true"
   * @generated
   */
  expression getExpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(expression value);

} // ClientOperation
