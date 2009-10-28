/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equation Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.EquationClause#getEquationRules <em>Equation Rules</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.EquationClause#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEquationClause()
 * @model
 * @generated
 */
public interface EquationClause extends initial_equation_rule_clause
{
  /**
   * Returns the value of the '<em><b>Equation Rules</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.equation_rule}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Equation Rules</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Equation Rules</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEquationClause_EquationRules()
   * @model containment="true"
   * @generated
   */
  EList<equation_rule> getEquationRules();

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.Annotation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEquationClause_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<Annotation> getAnnotations();

} // EquationClause
