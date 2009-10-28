/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition Algorithm Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getElseifClauses <em>Elseif Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getElseAlgotithmClauses <em>Else Algotithm Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionAlgorithmRule()
 * @model
 * @generated
 */
public interface ConditionAlgorithmRule extends algorithm
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionAlgorithmRule_Condition()
   * @model
   * @generated
   */
  String getCondition();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getCondition <em>Condition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' attribute.
   * @see #getCondition()
   * @generated
   */
  void setCondition(String value);

  /**
   * Returns the value of the '<em><b>Then Algorithm Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.algorithm}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then Algorithm Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then Algorithm Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionAlgorithmRule_ThenAlgorithmClauses()
   * @model containment="true"
   * @generated
   */
  EList<algorithm> getThenAlgorithmClauses();

  /**
   * Returns the value of the '<em><b>Elseif Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.algorithm_elseif}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elseif Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elseif Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionAlgorithmRule_ElseifClauses()
   * @model containment="true"
   * @generated
   */
  EList<algorithm_elseif> getElseifClauses();

  /**
   * Returns the value of the '<em><b>Else Algotithm Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.algorithm}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else Algotithm Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else Algotithm Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionAlgorithmRule_ElseAlgotithmClauses()
   * @model containment="true"
   * @generated
   */
  EList<algorithm> getElseAlgotithmClauses();

} // ConditionAlgorithmRule
