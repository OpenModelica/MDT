/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>else when a</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.else_when_a#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.else_when_a#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getelse_when_a()
 * @model
 * @generated
 */
public interface else_when_a extends EObject
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' attribute.
   * @see #setCondition(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getelse_when_a_Condition()
   * @model
   * @generated
   */
  String getCondition();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.else_when_a#getCondition <em>Condition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' attribute.
   * @see #getCondition()
   * @generated
   */
  void setCondition(String value);

  /**
   * Returns the value of the '<em><b>Then Algorithm Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.equation_rule}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then Algorithm Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then Algorithm Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getelse_when_a_ThenAlgorithmClauses()
   * @model containment="true"
   * @generated
   */
  EList<equation_rule> getThenAlgorithmClauses();

} // else_when_a
