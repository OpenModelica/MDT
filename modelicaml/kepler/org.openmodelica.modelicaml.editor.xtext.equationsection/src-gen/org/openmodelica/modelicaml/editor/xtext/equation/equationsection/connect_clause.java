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
