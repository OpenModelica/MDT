/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Algorithm Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ForAlgorithmClause#getForIndices <em>For Indices</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ForAlgorithmClause#getAlgorithmClauses <em>Algorithm Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getForAlgorithmClause()
 * @model
 * @generated
 */
public interface ForAlgorithmClause extends algorithm
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getForAlgorithmClause_ForIndices()
   * @model
   * @generated
   */
  String getForIndices();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ForAlgorithmClause#getForIndices <em>For Indices</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For Indices</em>' attribute.
   * @see #getForIndices()
   * @generated
   */
  void setForIndices(String value);

  /**
   * Returns the value of the '<em><b>Algorithm Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.algorithm}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Algorithm Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Algorithm Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getForAlgorithmClause_AlgorithmClauses()
   * @model containment="true"
   * @generated
   */
  EList<algorithm> getAlgorithmClauses();

} // ForAlgorithmClause
