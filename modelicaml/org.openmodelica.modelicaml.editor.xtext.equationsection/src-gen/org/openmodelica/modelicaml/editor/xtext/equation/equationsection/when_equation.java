/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection;

import org.eclipse.emf.common.util.EList;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>when equation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getWhenEpr <em>When Epr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getEqn_Then <em>Eqn Then</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getElseWhenEpr <em>Else When Epr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage#getwhen_equation()
 * @model
 * @generated
 */
public interface when_equation extends equation
{
  /**
   * Returns the value of the '<em><b>When Epr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>When Epr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>When Epr</em>' containment reference.
   * @see #setWhenEpr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage#getwhen_equation_WhenEpr()
   * @model containment="true"
   * @generated
   */
  expression getWhenEpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getWhenEpr <em>When Epr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>When Epr</em>' containment reference.
   * @see #getWhenEpr()
   * @generated
   */
  void setWhenEpr(expression value);

  /**
   * Returns the value of the '<em><b>Eqn Then</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Eqn Then</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Eqn Then</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage#getwhen_equation_Eqn_Then()
   * @model containment="true"
   * @generated
   */
  EList<equation> getEqn_Then();

  /**
   * Returns the value of the '<em><b>Else When Epr</b></em>' containment reference list.
   * The list contents are of type {@link modeleditor.expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else When Epr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else When Epr</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage#getwhen_equation_ElseWhenEpr()
   * @model containment="true"
   * @generated
   */
  EList<expression> getElseWhenEpr();

} // when_equation
