/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>when statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getWhen_expr <em>When expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getWhen_stat_true <em>When stat true</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getElse_When_expr <em>Else When expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getThen_statement <em>Then statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getwhen_statement()
 * @model
 * @generated
 */
public interface when_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>When expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>When expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>When expr</em>' containment reference.
   * @see #setWhen_expr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getwhen_statement_When_expr()
   * @model containment="true"
   * @generated
   */
  expression getWhen_expr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getWhen_expr <em>When expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>When expr</em>' containment reference.
   * @see #getWhen_expr()
   * @generated
   */
  void setWhen_expr(expression value);

  /**
   * Returns the value of the '<em><b>When stat true</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>When stat true</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>When stat true</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getwhen_statement_When_stat_true()
   * @model containment="true"
   * @generated
   */
  EList<statement> getWhen_stat_true();

  /**
   * Returns the value of the '<em><b>Else When expr</b></em>' containment reference list.
   * The list contents are of type {@link modeleditor.expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else When expr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else When expr</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getwhen_statement_Else_When_expr()
   * @model containment="true"
   * @generated
   */
  EList<expression> getElse_When_expr();

  /**
   * Returns the value of the '<em><b>Then statement</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then statement</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then statement</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getwhen_statement_Then_statement()
   * @model containment="true"
   * @generated
   */
  EList<statement> getThen_statement();

} // when_statement
