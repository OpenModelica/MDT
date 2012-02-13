/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>composition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Initial <em>EList Initial</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Public <em>EList Public</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Protected <em>EList Protected</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getEqn_section <em>Eqn section</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAlg_section <em>Alg section</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getLang_Spec <em>Lang Spec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getExternal_F_C <em>External FC</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation1 <em>Annotation1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation2 <em>Annotation2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition()
 * @model
 * @generated
 */
public interface composition extends EObject
{
  /**
   * Returns the value of the '<em><b>EList Initial</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>EList Initial</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EList Initial</em>' containment reference.
   * @see #setE_List_Initial(element_list)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_E_List_Initial()
   * @model containment="true"
   * @generated
   */
  element_list getE_List_Initial();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Initial <em>EList Initial</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>EList Initial</em>' containment reference.
   * @see #getE_List_Initial()
   * @generated
   */
  void setE_List_Initial(element_list value);

  /**
   * Returns the value of the '<em><b>EList Public</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>EList Public</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EList Public</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_E_List_Public()
   * @model containment="true"
   * @generated
   */
  EList<element_list> getE_List_Public();

  /**
   * Returns the value of the '<em><b>EList Protected</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>EList Protected</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EList Protected</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_E_List_Protected()
   * @model containment="true"
   * @generated
   */
  EList<element_list> getE_List_Protected();

  /**
   * Returns the value of the '<em><b>Eqn section</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Eqn section</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Eqn section</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_Eqn_section()
   * @model containment="true"
   * @generated
   */
  EList<equation_section> getEqn_section();

  /**
   * Returns the value of the '<em><b>Alg section</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Alg section</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Alg section</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_Alg_section()
   * @model containment="true"
   * @generated
   */
  EList<algorithm_section> getAlg_section();

  /**
   * Returns the value of the '<em><b>Lang Spec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lang Spec</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lang Spec</em>' containment reference.
   * @see #setLang_Spec(language_specification)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_Lang_Spec()
   * @model containment="true"
   * @generated
   */
  language_specification getLang_Spec();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getLang_Spec <em>Lang Spec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lang Spec</em>' containment reference.
   * @see #getLang_Spec()
   * @generated
   */
  void setLang_Spec(language_specification value);

  /**
   * Returns the value of the '<em><b>External FC</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>External FC</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>External FC</em>' containment reference.
   * @see #setExternal_F_C(external_function_call)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_External_F_C()
   * @model containment="true"
   * @generated
   */
  external_function_call getExternal_F_C();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getExternal_F_C <em>External FC</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>External FC</em>' containment reference.
   * @see #getExternal_F_C()
   * @generated
   */
  void setExternal_F_C(external_function_call value);

  /**
   * Returns the value of the '<em><b>Annotation1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation1</em>' containment reference.
   * @see #setAnnotation1(annotation)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_Annotation1()
   * @model containment="true"
   * @generated
   */
  annotation getAnnotation1();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation1 <em>Annotation1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation1</em>' containment reference.
   * @see #getAnnotation1()
   * @generated
   */
  void setAnnotation1(annotation value);

  /**
   * Returns the value of the '<em><b>Annotation2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation2</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation2</em>' containment reference.
   * @see #setAnnotation2(annotation)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getcomposition_Annotation2()
   * @model containment="true"
   * @generated
   */
  annotation getAnnotation2();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation2 <em>Annotation2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation2</em>' containment reference.
   * @see #getAnnotation2()
   * @generated
   */
  void setAnnotation2(annotation value);

} // composition
