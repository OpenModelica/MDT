/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>simple expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getLog_Exp <em>Log Exp</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getS_Logical_expression <em>SLogical expression</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getL_Logical_expression <em>LLogical expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getsimple_expression()
 * @model
 * @generated
 */
public interface simple_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Log Exp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Log Exp</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Log Exp</em>' containment reference.
   * @see #setLog_Exp(logical_expression)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getsimple_expression_Log_Exp()
   * @model containment="true"
   * @generated
   */
  logical_expression getLog_Exp();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getLog_Exp <em>Log Exp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Log Exp</em>' containment reference.
   * @see #getLog_Exp()
   * @generated
   */
  void setLog_Exp(logical_expression value);

  /**
   * Returns the value of the '<em><b>SLogical expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>SLogical expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>SLogical expression</em>' containment reference.
   * @see #setS_Logical_expression(logical_expression)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getsimple_expression_S_Logical_expression()
   * @model containment="true"
   * @generated
   */
  logical_expression getS_Logical_expression();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getS_Logical_expression <em>SLogical expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>SLogical expression</em>' containment reference.
   * @see #getS_Logical_expression()
   * @generated
   */
  void setS_Logical_expression(logical_expression value);

  /**
   * Returns the value of the '<em><b>LLogical expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>LLogical expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>LLogical expression</em>' containment reference.
   * @see #setL_Logical_expression(logical_expression)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getsimple_expression_L_Logical_expression()
   * @model containment="true"
   * @generated
   */
  logical_expression getL_Logical_expression();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getL_Logical_expression <em>LLogical expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>LLogical expression</em>' containment reference.
   * @see #getL_Logical_expression()
   * @generated
   */
  void setL_Logical_expression(logical_expression value);

} // simple_expression
