/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>connect clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector1 <em>Connector1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector2 <em>Connector2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage#getconnect_clause()
 * @model
 * @generated
 */
public interface connect_clause extends equation
{
  /**
   * Returns the value of the '<em><b>Connector1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Connector1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Connector1</em>' containment reference.
   * @see #setConnector1(component_reference)
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage#getconnect_clause_Connector1()
   * @model containment="true"
   * @generated
   */
  component_reference getConnector1();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector1 <em>Connector1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Connector1</em>' containment reference.
   * @see #getConnector1()
   * @generated
   */
  void setConnector1(component_reference value);

  /**
   * Returns the value of the '<em><b>Connector2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Connector2</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Connector2</em>' containment reference.
   * @see #setConnector2(component_reference)
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage#getconnect_clause_Connector2()
   * @model containment="true"
   * @generated
   */
  component_reference getConnector2();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector2 <em>Connector2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Connector2</em>' containment reference.
   * @see #getConnector2()
   * @generated
   */
  void setConnector2(component_reference value);

} // connect_clause
