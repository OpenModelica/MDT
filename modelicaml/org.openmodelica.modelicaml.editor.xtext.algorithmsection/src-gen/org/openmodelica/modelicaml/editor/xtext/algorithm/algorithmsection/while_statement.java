/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>while statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement#getWhile_expr <em>While expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement#getWhile_stat <em>While stat</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getwhile_statement()
 * @model
 * @generated
 */
public interface while_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>While expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>While expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>While expr</em>' containment reference.
   * @see #setWhile_expr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getwhile_statement_While_expr()
   * @model containment="true"
   * @generated
   */
  expression getWhile_expr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement#getWhile_expr <em>While expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>While expr</em>' containment reference.
   * @see #getWhile_expr()
   * @generated
   */
  void setWhile_expr(expression value);

  /**
   * Returns the value of the '<em><b>While stat</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>While stat</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>While stat</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage#getwhile_statement_While_stat()
   * @model containment="true"
   * @generated
   */
  EList<statement> getWhile_stat();

} // while_statement
