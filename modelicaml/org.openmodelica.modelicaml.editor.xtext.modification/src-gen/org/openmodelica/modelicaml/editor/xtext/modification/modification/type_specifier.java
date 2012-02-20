/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>type specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getArray_Subs <em>Array Subs</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_List <em>Com List</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_Dec <em>Com Dec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#gettype_specifier()
 * @model
 * @generated
 */
public interface type_specifier extends component_clause, component_clause1
{
  /**
   * Returns the value of the '<em><b>Array Subs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Subs</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Subs</em>' containment reference.
   * @see #setArray_Subs(array_subscripts)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#gettype_specifier_Array_Subs()
   * @model containment="true"
   * @generated
   */
  array_subscripts getArray_Subs();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getArray_Subs <em>Array Subs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Subs</em>' containment reference.
   * @see #getArray_Subs()
   * @generated
   */
  void setArray_Subs(array_subscripts value);

  /**
   * Returns the value of the '<em><b>Com List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Com List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Com List</em>' containment reference.
   * @see #setCom_List(component_list)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#gettype_specifier_Com_List()
   * @model containment="true"
   * @generated
   */
  component_list getCom_List();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_List <em>Com List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Com List</em>' containment reference.
   * @see #getCom_List()
   * @generated
   */
  void setCom_List(component_list value);

  /**
   * Returns the value of the '<em><b>Com Dec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Com Dec</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Com Dec</em>' containment reference.
   * @see #setCom_Dec(component_declaration1)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#gettype_specifier_Com_Dec()
   * @model containment="true"
   * @generated
   */
  component_declaration1 getCom_Dec();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_Dec <em>Com Dec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Com Dec</em>' containment reference.
   * @see #getCom_Dec()
   * @generated
   */
  void setCom_Dec(component_declaration1 value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(name)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#gettype_specifier_Name()
   * @model containment="true"
   * @generated
   */
  name getName();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(name value);

} // type_specifier
