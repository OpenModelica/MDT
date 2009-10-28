/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Equation Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule#getThenEquationClauses <em>Then Equation Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule#getElseifClauses <em>Elseif Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule#getElseEquationClauses <em>Else Equation Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionalEquationRule()
 * @model
 * @generated
 */
public interface ConditionalEquationRule extends equation_rule
{
  /**
   * Returns the value of the '<em><b>Then Equation Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.equation_rule}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then Equation Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then Equation Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionalEquationRule_ThenEquationClauses()
   * @model containment="true"
   * @generated
   */
  EList<equation_rule> getThenEquationClauses();

  /**
   * Returns the value of the '<em><b>Elseif Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.equation_rule_elseif}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elseif Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elseif Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionalEquationRule_ElseifClauses()
   * @model containment="true"
   * @generated
   */
  EList<equation_rule_elseif> getElseifClauses();

  /**
   * Returns the value of the '<em><b>Else Equation Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.equation_rule}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else Equation Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else Equation Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getConditionalEquationRule_ElseEquationClauses()
   * @model containment="true"
   * @generated
   */
  EList<equation_rule> getElseEquationClauses();

} // ConditionalEquationRule
