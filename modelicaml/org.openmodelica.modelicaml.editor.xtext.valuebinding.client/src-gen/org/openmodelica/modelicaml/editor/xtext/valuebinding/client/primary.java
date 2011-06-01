/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client;

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
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary()
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Name_Function()
   * @model containment="true"
   * @generated
   */
  name_Function getName_Function();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function <em>Name Function</em>}' containment reference.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Initial_ref()
   * @model containment="true"
   * @generated
   */
  initial_ref getInitial_ref();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref <em>Initial ref</em>}' containment reference.
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
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Expr()
   * @model containment="true"
   * @generated
   */
  ExprDer getExpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(ExprDer value);

  /**
   * Returns the value of the '<em><b>Binding</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Binding</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Binding</em>' attribute.
   * @see #setBinding(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getprimary_Binding()
   * @model
   * @generated
   */
  String getBinding();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getBinding <em>Binding</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Binding</em>' attribute.
   * @see #getBinding()
   * @generated
   */
  void setBinding(String value);

} // primary
