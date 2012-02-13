/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>component declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getComponent_Dec <em>Component Dec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getDec <em>Dec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getConditional_Att <em>Conditional Att</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomponent_declaration()
 * @model
 * @generated
 */
public interface component_declaration extends component_list
{
  /**
   * Returns the value of the '<em><b>Component Dec</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component Dec</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component Dec</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomponent_declaration_Component_Dec()
   * @model containment="true"
   * @generated
   */
  EList<component_declaration> getComponent_Dec();

  /**
   * Returns the value of the '<em><b>Dec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dec</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dec</em>' containment reference.
   * @see #setDec(declaration)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomponent_declaration_Dec()
   * @model containment="true"
   * @generated
   */
  declaration getDec();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getDec <em>Dec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dec</em>' containment reference.
   * @see #getDec()
   * @generated
   */
  void setDec(declaration value);

  /**
   * Returns the value of the '<em><b>Conditional Att</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conditional Att</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conditional Att</em>' containment reference.
   * @see #setConditional_Att(conditional_attribute)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomponent_declaration_Conditional_Att()
   * @model containment="true"
   * @generated
   */
  conditional_attribute getConditional_Att();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getConditional_Att <em>Conditional Att</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conditional Att</em>' containment reference.
   * @see #getConditional_Att()
   * @generated
   */
  void setConditional_Att(conditional_attribute value);

} // component_declaration
