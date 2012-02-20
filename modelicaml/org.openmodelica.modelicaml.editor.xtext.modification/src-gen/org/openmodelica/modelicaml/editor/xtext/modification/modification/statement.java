/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getIf_statement <em>If statement</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_OutputExprList <em>State Output Expr List</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_Comp_Ref <em>State Comp Ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getFor_statement <em>For statement</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhen_statement <em>When statement</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhile_statement <em>While statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getstatement()
 * @model
 * @generated
 */
public interface statement extends EObject
{
  /**
   * Returns the value of the '<em><b>If statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>If statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>If statement</em>' containment reference.
   * @see #setIf_statement(if_statement)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getstatement_If_statement()
   * @model containment="true"
   * @generated
   */
  if_statement getIf_statement();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getIf_statement <em>If statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>If statement</em>' containment reference.
   * @see #getIf_statement()
   * @generated
   */
  void setIf_statement(if_statement value);

  /**
   * Returns the value of the '<em><b>State Output Expr List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State Output Expr List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State Output Expr List</em>' containment reference.
   * @see #setState_OutputExprList(state_OutputExprList)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getstatement_State_OutputExprList()
   * @model containment="true"
   * @generated
   */
  state_OutputExprList getState_OutputExprList();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_OutputExprList <em>State Output Expr List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>State Output Expr List</em>' containment reference.
   * @see #getState_OutputExprList()
   * @generated
   */
  void setState_OutputExprList(state_OutputExprList value);

  /**
   * Returns the value of the '<em><b>State Comp Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State Comp Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State Comp Ref</em>' containment reference.
   * @see #setState_Comp_Ref(state_Comp_Ref)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getstatement_State_Comp_Ref()
   * @model containment="true"
   * @generated
   */
  state_Comp_Ref getState_Comp_Ref();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_Comp_Ref <em>State Comp Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>State Comp Ref</em>' containment reference.
   * @see #getState_Comp_Ref()
   * @generated
   */
  void setState_Comp_Ref(state_Comp_Ref value);

  /**
   * Returns the value of the '<em><b>For statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>For statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>For statement</em>' containment reference.
   * @see #setFor_statement(for_statement)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getstatement_For_statement()
   * @model containment="true"
   * @generated
   */
  for_statement getFor_statement();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getFor_statement <em>For statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>For statement</em>' containment reference.
   * @see #getFor_statement()
   * @generated
   */
  void setFor_statement(for_statement value);

  /**
   * Returns the value of the '<em><b>When statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>When statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>When statement</em>' containment reference.
   * @see #setWhen_statement(when_statement)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getstatement_When_statement()
   * @model containment="true"
   * @generated
   */
  when_statement getWhen_statement();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhen_statement <em>When statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>When statement</em>' containment reference.
   * @see #getWhen_statement()
   * @generated
   */
  void setWhen_statement(when_statement value);

  /**
   * Returns the value of the '<em><b>While statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>While statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>While statement</em>' containment reference.
   * @see #setWhile_statement(while_statement)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getstatement_While_statement()
   * @model containment="true"
   * @generated
   */
  while_statement getWhile_statement();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhile_statement <em>While statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>While statement</em>' containment reference.
   * @see #getWhile_statement()
   * @generated
   */
  void setWhile_statement(while_statement value);

} // statement
