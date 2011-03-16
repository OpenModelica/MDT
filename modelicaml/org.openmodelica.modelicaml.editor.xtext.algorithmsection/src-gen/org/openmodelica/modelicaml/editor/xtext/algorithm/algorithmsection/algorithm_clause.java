/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>algorithm clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause#getAlgorithm <em>Algorithm</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getalgorithm_clause()
 * @model
 * @generated
 */
public interface algorithm_clause extends EObject
{
  /**
   * Returns the value of the '<em><b>Algorithm</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Algorithm</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Algorithm</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getalgorithm_clause_Algorithm()
   * @model containment="true"
   * @generated
   */
  EList<statement> getAlgorithm();

} // algorithm_clause
