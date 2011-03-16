/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression;

import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Macro BEFORE</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getInt <em>Int</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getNum <em>Num</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getComponent_reference <em>Component reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getMacro_BEFORE()
 * @model
 * @generated
 */
public interface Macro_BEFORE extends EObject
{
  /**
   * Returns the value of the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Int</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Int</em>' attribute.
   * @see #setInt(int)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getMacro_BEFORE_Int()
   * @model
   * @generated
   */
  int getInt();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getInt <em>Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Int</em>' attribute.
   * @see #getInt()
   * @generated
   */
  void setInt(int value);

  /**
   * Returns the value of the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Num</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Num</em>' attribute.
   * @see #setNum(String)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getMacro_BEFORE_Num()
   * @model
   * @generated
   */
  String getNum();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getNum <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Num</em>' attribute.
   * @see #getNum()
   * @generated
   */
  void setNum(String value);

  /**
   * Returns the value of the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component reference</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component reference</em>' containment reference.
   * @see #setComponent_reference(component_reference)
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#getMacro_BEFORE_Component_reference()
   * @model containment="true"
   * @generated
   */
  component_reference getComponent_reference();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getComponent_reference <em>Component reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Component reference</em>' containment reference.
   * @see #getComponent_reference()
   * @generated
   */
  void setComponent_reference(component_reference value);

} // Macro_BEFORE
