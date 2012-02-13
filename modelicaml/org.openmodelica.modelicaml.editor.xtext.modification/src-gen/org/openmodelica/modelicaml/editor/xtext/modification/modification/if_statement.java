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
 * A representation of the model object '<em><b>if statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getExprtrue <em>Exprtrue</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getState <em>State</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getExprStilltrue <em>Expr Stilltrue</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getif_statement()
 * @model
 * @generated
 */
public interface if_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>Exprtrue</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exprtrue</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exprtrue</em>' containment reference.
   * @see #setExprtrue(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getif_statement_Exprtrue()
   * @model containment="true"
   * @generated
   */
  expression getExprtrue();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getExprtrue <em>Exprtrue</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exprtrue</em>' containment reference.
   * @see #getExprtrue()
   * @generated
   */
  void setExprtrue(expression value);

  /**
   * Returns the value of the '<em><b>State</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getif_statement_State()
   * @model containment="true"
   * @generated
   */
  EList<statement> getState();

  /**
   * Returns the value of the '<em><b>Expr Stilltrue</b></em>' containment reference list.
   * The list contents are of type {@link modeleditor.expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr Stilltrue</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr Stilltrue</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getif_statement_ExprStilltrue()
   * @model containment="true"
   * @generated
   */
  EList<expression> getExprStilltrue();

} // if_statement
