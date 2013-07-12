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

import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>external function call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getComponent_reference <em>Component reference</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getE_List <em>EList</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getexternal_function_call()
 * @model
 * @generated
 */
public interface external_function_call extends EObject
{
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
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getexternal_function_call_Component_reference()
   * @model containment="true"
   * @generated
   */
  component_reference getComponent_reference();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getComponent_reference <em>Component reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Component reference</em>' containment reference.
   * @see #getComponent_reference()
   * @generated
   */
  void setComponent_reference(component_reference value);

  /**
   * Returns the value of the '<em><b>EList</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>EList</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EList</em>' containment reference.
   * @see #setE_List(expression_list)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getexternal_function_call_E_List()
   * @model containment="true"
   * @generated
   */
  expression_list getE_List();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getE_List <em>EList</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>EList</em>' containment reference.
   * @see #getE_List()
   * @generated
   */
  void setE_List(expression_list value);

} // external_function_call
