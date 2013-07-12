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
 * A representation of the model object '<em><b>arithmetic expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOp <em>Op</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getArithmetic_expression <em>Arithmetic expression</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOpr <em>Opr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getTerm <em>Term</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOper1 <em>Oper1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getTerm1 <em>Term1</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getarithmetic_expression()
 * @model
 * @generated
 */
public interface arithmetic_expression extends relation
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getarithmetic_expression_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Arithmetic expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arithmetic expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arithmetic expression</em>' containment reference.
   * @see #setArithmetic_expression(arithmetic_expression)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getarithmetic_expression_Arithmetic_expression()
   * @model containment="true"
   * @generated
   */
  arithmetic_expression getArithmetic_expression();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getArithmetic_expression <em>Arithmetic expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arithmetic expression</em>' containment reference.
   * @see #getArithmetic_expression()
   * @generated
   */
  void setArithmetic_expression(arithmetic_expression value);

  /**
   * Returns the value of the '<em><b>Opr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Opr</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Opr</em>' attribute.
   * @see #setOpr(String)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getarithmetic_expression_Opr()
   * @model
   * @generated
   */
  String getOpr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOpr <em>Opr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Opr</em>' attribute.
   * @see #getOpr()
   * @generated
   */
  void setOpr(String value);

  /**
   * Returns the value of the '<em><b>Term</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Term</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Term</em>' containment reference.
   * @see #setTerm(term)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getarithmetic_expression_Term()
   * @model containment="true"
   * @generated
   */
  term getTerm();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getTerm <em>Term</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Term</em>' containment reference.
   * @see #getTerm()
   * @generated
   */
  void setTerm(term value);

  /**
   * Returns the value of the '<em><b>Oper1</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Oper1</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Oper1</em>' attribute list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getarithmetic_expression_Oper1()
   * @model unique="false"
   * @generated
   */
  EList<String> getOper1();

  /**
   * Returns the value of the '<em><b>Term1</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.term}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Term1</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Term1</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getarithmetic_expression_Term1()
   * @model containment="true"
   * @generated
   */
  EList<term> getTerm1();

} // arithmetic_expression
