/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.common.util.EList;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>class specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getComp <em>Comp</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getName <em>Name</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getArray_Subs <em>Array Subs</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getClass_mod <em>Class mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getVar <em>Var</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getEnum_list <em>Enum list</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getclass_specifier()
 * @model
 * @generated
 */
public interface class_specifier extends class_definition
{
  /**
   * Returns the value of the '<em><b>Comp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp</em>' containment reference.
   * @see #setComp(composition)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getclass_specifier_Comp()
   * @model containment="true"
   * @generated
   */
  composition getComp();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getComp <em>Comp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comp</em>' containment reference.
   * @see #getComp()
   * @generated
   */
  void setComp(composition value);

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
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getclass_specifier_Name()
   * @model containment="true"
   * @generated
   */
  name getName();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(name value);

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
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getclass_specifier_Array_Subs()
   * @model containment="true"
   * @generated
   */
  array_subscripts getArray_Subs();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getArray_Subs <em>Array Subs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Subs</em>' containment reference.
   * @see #getArray_Subs()
   * @generated
   */
  void setArray_Subs(array_subscripts value);

  /**
   * Returns the value of the '<em><b>Class mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class mod</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class mod</em>' containment reference.
   * @see #setClass_mod(class_modification)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getclass_specifier_Class_mod()
   * @model containment="true"
   * @generated
   */
  class_modification getClass_mod();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getClass_mod <em>Class mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class mod</em>' containment reference.
   * @see #getClass_mod()
   * @generated
   */
  void setClass_mod(class_modification value);

  /**
   * Returns the value of the '<em><b>Var</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' attribute list.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getclass_specifier_Var()
   * @model unique="false"
   * @generated
   */
  EList<String> getVar();

  /**
   * Returns the value of the '<em><b>Enum list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Enum list</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Enum list</em>' containment reference.
   * @see #setEnum_list(enum_list)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getclass_specifier_Enum_list()
   * @model containment="true"
   * @generated
   */
  enum_list getEnum_list();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getEnum_list <em>Enum list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Enum list</em>' containment reference.
   * @see #getEnum_list()
   * @generated
   */
  void setEnum_list(enum_list value);

} // class_specifier
