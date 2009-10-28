/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Equation Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ForEquationClause#getForIndices <em>For Indices</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ForEquationClause#getEquationClauses <em>Equation Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getForEquationClause()
 * @model
 * @generated
 */
public interface ForEquationClause extends equation_rule
{
  /**
   * Returns the value of the '<em><b>For Indices</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For Indices</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For Indices</em>' attribute.
   * @see #setForIndices(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getForEquationClause_ForIndices()
   * @model
   * @generated
   */
  String getForIndices();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ForEquationClause#getForIndices <em>For Indices</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For Indices</em>' attribute.
   * @see #getForIndices()
   * @generated
   */
  void setForIndices(String value);

  /**
   * Returns the value of the '<em><b>Equation Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.equation_rule}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Equation Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Equation Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getForEquationClause_EquationClauses()
   * @model containment="true"
   * @generated
   */
  EList<equation_rule> getEquationClauses();

} // ForEquationClause
