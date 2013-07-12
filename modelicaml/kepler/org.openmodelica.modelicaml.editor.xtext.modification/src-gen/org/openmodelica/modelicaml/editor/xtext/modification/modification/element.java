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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getClass_Def <em>Class Def</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getComp_Clause <em>Comp Clause</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getConstrain_Clause <em>Constrain Clause</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement()
 * @model
 * @generated
 */
public interface element extends EObject
{
  /**
   * Returns the value of the '<em><b>Class Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Def</em>' containment reference.
   * @see #setClass_Def(class_definition)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_Class_Def()
   * @model containment="true"
   * @generated
   */
  class_definition getClass_Def();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getClass_Def <em>Class Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Def</em>' containment reference.
   * @see #getClass_Def()
   * @generated
   */
  void setClass_Def(class_definition value);

  /**
   * Returns the value of the '<em><b>Comp Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comp Clause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comp Clause</em>' containment reference.
   * @see #setComp_Clause(component_clause)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_Comp_Clause()
   * @model containment="true"
   * @generated
   */
  component_clause getComp_Clause();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getComp_Clause <em>Comp Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comp Clause</em>' containment reference.
   * @see #getComp_Clause()
   * @generated
   */
  void setComp_Clause(component_clause value);

  /**
   * Returns the value of the '<em><b>Constrain Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constrain Clause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constrain Clause</em>' containment reference.
   * @see #setConstrain_Clause(constraining_clause)
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_Constrain_Clause()
   * @model containment="true"
   * @generated
   */
  constraining_clause getConstrain_Clause();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getConstrain_Clause <em>Constrain Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constrain Clause</em>' containment reference.
   * @see #getConstrain_Clause()
   * @generated
   */
  void setConstrain_Clause(constraining_clause value);

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
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#getelement_Name()
   * @model containment="true"
   * @generated
   */
  name getName();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(name value);

} // element
