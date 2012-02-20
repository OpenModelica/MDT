/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>left hand component reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getRef <em>Ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getSubscripts1 <em>Subscripts1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getRef1 <em>Ref1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getSubscripts <em>Subscripts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getleft_hand_component_reference()
 * @model
 * @generated
 */
public interface left_hand_component_reference extends EObject
{
  /**
   * Returns the value of the '<em><b>Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref</em>' attribute.
   * @see #setRef(String)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getleft_hand_component_reference_Ref()
   * @model
   * @generated
   */
  String getRef();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getRef <em>Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ref</em>' attribute.
   * @see #getRef()
   * @generated
   */
  void setRef(String value);

  /**
   * Returns the value of the '<em><b>Subscripts1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subscripts1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subscripts1</em>' containment reference.
   * @see #setSubscripts1(array_subscripts)
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getleft_hand_component_reference_Subscripts1()
   * @model containment="true"
   * @generated
   */
  array_subscripts getSubscripts1();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getSubscripts1 <em>Subscripts1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subscripts1</em>' containment reference.
   * @see #getSubscripts1()
   * @generated
   */
  void setSubscripts1(array_subscripts value);

  /**
   * Returns the value of the '<em><b>Ref1</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref1</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref1</em>' attribute list.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getleft_hand_component_reference_Ref1()
   * @model unique="false"
   * @generated
   */
  EList<String> getRef1();

  /**
   * Returns the value of the '<em><b>Subscripts</b></em>' containment reference list.
   * The list contents are of type {@link modeleditor.array_subscripts}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subscripts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subscripts</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#getleft_hand_component_reference_Subscripts()
   * @model containment="true"
   * @generated
   */
  EList<array_subscripts> getSubscripts();

} // left_hand_component_reference
