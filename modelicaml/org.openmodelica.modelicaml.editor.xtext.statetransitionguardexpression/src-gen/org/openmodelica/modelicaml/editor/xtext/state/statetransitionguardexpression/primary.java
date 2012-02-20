/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_A <em>Mac A</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_E <em>Mac E</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_C <em>Mac C</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_S <em>Mac S</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getElse <em>Else</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary()
 * @model
 * @generated
 */
public interface primary extends org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary
{
  /**
   * Returns the value of the '<em><b>Name Function</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name Function</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name Function</em>' containment reference.
   * @see #setName_Function(name_Function)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Name_Function()
   * @model containment="true"
   * @generated
   */
  name_Function getName_Function();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getName_Function <em>Name Function</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name Function</em>' containment reference.
   * @see #getName_Function()
   * @generated
   */
  void setName_Function(name_Function value);

  /**
   * Returns the value of the '<em><b>Initial ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial ref</em>' containment reference.
   * @see #setInitial_ref(initial_ref)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Initial_ref()
   * @model containment="true"
   * @generated
   */
  initial_ref getInitial_ref();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getInitial_ref <em>Initial ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initial ref</em>' containment reference.
   * @see #getInitial_ref()
   * @generated
   */
  void setInitial_ref(initial_ref value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(ExprDer)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Expr()
   * @model containment="true"
   * @generated
   */
  ExprDer getExpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(ExprDer value);

  /**
   * Returns the value of the '<em><b>Mac A</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mac A</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mac A</em>' containment reference.
   * @see #setMac_A(Macro_After)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Mac_A()
   * @model containment="true"
   * @generated
   */
  Macro_After getMac_A();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_A <em>Mac A</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mac A</em>' containment reference.
   * @see #getMac_A()
   * @generated
   */
  void setMac_A(Macro_After value);

  /**
   * Returns the value of the '<em><b>Mac E</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mac E</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mac E</em>' containment reference.
   * @see #setMac_E(Macro_EVENT)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Mac_E()
   * @model containment="true"
   * @generated
   */
  Macro_EVENT getMac_E();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_E <em>Mac E</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mac E</em>' containment reference.
   * @see #getMac_E()
   * @generated
   */
  void setMac_E(Macro_EVENT value);

  /**
   * Returns the value of the '<em><b>Mac C</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mac C</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mac C</em>' containment reference.
   * @see #setMac_C(Macro_CHANGE)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Mac_C()
   * @model containment="true"
   * @generated
   */
  Macro_CHANGE getMac_C();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_C <em>Mac C</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mac C</em>' containment reference.
   * @see #getMac_C()
   * @generated
   */
  void setMac_C(Macro_CHANGE value);

  /**
   * Returns the value of the '<em><b>Mac S</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mac S</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mac S</em>' containment reference.
   * @see #setMac_S(Macro_SIGNAL)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Mac_S()
   * @model containment="true"
   * @generated
   */
  Macro_SIGNAL getMac_S();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_S <em>Mac S</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mac S</em>' containment reference.
   * @see #getMac_S()
   * @generated
   */
  void setMac_S(Macro_SIGNAL value);

  /**
   * Returns the value of the '<em><b>Else</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else</em>' attribute.
   * @see #setElse(String)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getprimary_Else()
   * @model
   * @generated
   */
  String getElse();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getElse <em>Else</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else</em>' attribute.
   * @see #getElse()
   * @generated
   */
  void setElse(String value);

} // primary
