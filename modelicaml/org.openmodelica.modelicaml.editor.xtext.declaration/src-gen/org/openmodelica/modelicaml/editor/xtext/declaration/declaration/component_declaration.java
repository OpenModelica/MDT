/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.declaration.declaration;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>component declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getMod <em>Mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getConditional_Att <em>Conditional Att</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationPackage#getcomponent_declaration()
 * @model
 * @generated
 */
public interface component_declaration extends EObject
{
  /**
   * Returns the value of the '<em><b>Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mod</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mod</em>' containment reference.
   * @see #setMod(modification)
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationPackage#getcomponent_declaration_Mod()
   * @model containment="true"
   * @generated
   */
  modification getMod();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getMod <em>Mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mod</em>' containment reference.
   * @see #getMod()
   * @generated
   */
  void setMod(modification value);

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
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationPackage#getcomponent_declaration_Conditional_Att()
   * @model containment="true"
   * @generated
   */
  conditional_attribute getConditional_Att();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getConditional_Att <em>Conditional Att</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conditional Att</em>' containment reference.
   * @see #getConditional_Att()
   * @generated
   */
  void setConditional_Att(conditional_attribute value);

} // component_declaration
