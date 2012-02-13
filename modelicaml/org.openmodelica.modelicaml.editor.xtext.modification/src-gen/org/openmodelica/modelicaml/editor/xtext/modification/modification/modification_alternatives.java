/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>modification alternatives</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives#getModification_Rule <em>Modification Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getmodification_alternatives()
 * @model
 * @generated
 */
public interface modification_alternatives extends EObject
{
  /**
   * Returns the value of the '<em><b>Modification Rule</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modification Rule</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modification Rule</em>' containment reference.
   * @see #setModification_Rule(modification)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getmodification_alternatives_Modification_Rule()
   * @model containment="true"
   * @generated
   */
  modification getModification_Rule();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives#getModification_Rule <em>Modification Rule</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modification Rule</em>' containment reference.
   * @see #getModification_Rule()
   * @generated
   */
  void setModification_Rule(modification value);

} // modification_alternatives
