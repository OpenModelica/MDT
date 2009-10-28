/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>algorithm clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.algorithm_clause#getAlgorithmRules <em>Algorithm Rules</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.algorithm_clause#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getalgorithm_clause()
 * @model
 * @generated
 */
public interface algorithm_clause extends initial_algorithm_clause
{
  /**
   * Returns the value of the '<em><b>Algorithm Rules</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.algorithm}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Algorithm Rules</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Algorithm Rules</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getalgorithm_clause_AlgorithmRules()
   * @model containment="true"
   * @generated
   */
  EList<algorithm> getAlgorithmRules();

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getalgorithm_clause_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<Annotation> getAnnotations();

} // algorithm_clause
