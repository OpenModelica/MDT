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
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>conditional expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getIfexpr <em>Ifexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getThenexpr <em>Thenexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getElseifexpr <em>Elseifexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getTrueexpr <em>Trueexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getFalseexpr <em>Falseexpr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getconditional_expr()
 * @model
 * @generated
 */
public interface conditional_expr extends expression
{
  /**
   * Returns the value of the '<em><b>Ifexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ifexpr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ifexpr</em>' containment reference.
   * @see #setIfexpr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getconditional_expr_Ifexpr()
   * @model containment="true"
   * @generated
   */
  expression getIfexpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getIfexpr <em>Ifexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ifexpr</em>' containment reference.
   * @see #getIfexpr()
   * @generated
   */
  void setIfexpr(expression value);

  /**
   * Returns the value of the '<em><b>Thenexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Thenexpr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Thenexpr</em>' containment reference.
   * @see #setThenexpr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getconditional_expr_Thenexpr()
   * @model containment="true"
   * @generated
   */
  expression getThenexpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getThenexpr <em>Thenexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Thenexpr</em>' containment reference.
   * @see #getThenexpr()
   * @generated
   */
  void setThenexpr(expression value);

  /**
   * Returns the value of the '<em><b>Elseifexpr</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elseifexpr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elseifexpr</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getconditional_expr_Elseifexpr()
   * @model containment="true"
   * @generated
   */
  EList<expression> getElseifexpr();

  /**
   * Returns the value of the '<em><b>Trueexpr</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Trueexpr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Trueexpr</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getconditional_expr_Trueexpr()
   * @model containment="true"
   * @generated
   */
  EList<expression> getTrueexpr();

  /**
   * Returns the value of the '<em><b>Falseexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Falseexpr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Falseexpr</em>' containment reference.
   * @see #setFalseexpr(expression)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getconditional_expr_Falseexpr()
   * @model containment="true"
   * @generated
   */
  expression getFalseexpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getFalseexpr <em>Falseexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Falseexpr</em>' containment reference.
   * @see #getFalseexpr()
   * @generated
   */
  void setFalseexpr(expression value);

} // conditional_expr
