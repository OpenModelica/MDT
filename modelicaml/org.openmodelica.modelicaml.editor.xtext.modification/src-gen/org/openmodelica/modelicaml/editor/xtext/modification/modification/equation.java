/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>equation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getSim <em>Sim</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getIF_EQN <em>IF EQN</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getFOR_EQN <em>FOR EQN</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getCon <em>Con</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getWHEN_EQN <em>WHEN EQN</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation()
 * @model
 * @generated
 */
public interface equation extends EObject
{
  /**
   * Returns the value of the '<em><b>Sim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sim</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sim</em>' containment reference.
   * @see #setSim(simple_expression)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation_Sim()
   * @model containment="true"
   * @generated
   */
  simple_expression getSim();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getSim <em>Sim</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sim</em>' containment reference.
   * @see #getSim()
   * @generated
   */
  void setSim(simple_expression value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation_Expr()
   * @model containment="true"
   * @generated
   */
  expression getExpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(expression value);

  /**
   * Returns the value of the '<em><b>IF EQN</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>IF EQN</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>IF EQN</em>' containment reference.
   * @see #setIF_EQN(if_equation)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation_IF_EQN()
   * @model containment="true"
   * @generated
   */
  if_equation getIF_EQN();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getIF_EQN <em>IF EQN</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>IF EQN</em>' containment reference.
   * @see #getIF_EQN()
   * @generated
   */
  void setIF_EQN(if_equation value);

  /**
   * Returns the value of the '<em><b>FOR EQN</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>FOR EQN</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>FOR EQN</em>' containment reference.
   * @see #setFOR_EQN(for_equation)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation_FOR_EQN()
   * @model containment="true"
   * @generated
   */
  for_equation getFOR_EQN();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getFOR_EQN <em>FOR EQN</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>FOR EQN</em>' containment reference.
   * @see #getFOR_EQN()
   * @generated
   */
  void setFOR_EQN(for_equation value);

  /**
   * Returns the value of the '<em><b>Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Con</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Con</em>' containment reference.
   * @see #setCon(connect_clause)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation_Con()
   * @model containment="true"
   * @generated
   */
  connect_clause getCon();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getCon <em>Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Con</em>' containment reference.
   * @see #getCon()
   * @generated
   */
  void setCon(connect_clause value);

  /**
   * Returns the value of the '<em><b>WHEN EQN</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>WHEN EQN</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>WHEN EQN</em>' containment reference.
   * @see #setWHEN_EQN(when_equation)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation_WHEN_EQN()
   * @model containment="true"
   * @generated
   */
  when_equation getWHEN_EQN();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getWHEN_EQN <em>WHEN EQN</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>WHEN EQN</em>' containment reference.
   * @see #getWHEN_EQN()
   * @generated
   */
  void setWHEN_EQN(when_equation value);

  /**
   * Returns the value of the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' attribute.
   * @see #setComment(String)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getequation_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

} // equation
