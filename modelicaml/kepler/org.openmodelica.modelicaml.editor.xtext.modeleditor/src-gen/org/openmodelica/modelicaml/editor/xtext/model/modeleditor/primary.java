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
 * A representation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getPrimary <em>Primary</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getNum <em>Num</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getInt <em>Int</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getStr <em>Str</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getBool <em>Bool</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getComponent_reference <em>Component reference</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getOutput_expr_list <em>Output expr list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getExpre_list <em>Expre list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getExpression_list <em>Expression list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getF_arguments <em>Farguments</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary()
 * @model
 * @generated
 */
public interface primary extends factor
{
  /**
   * Returns the value of the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Primary</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Primary</em>' containment reference.
   * @see #setPrimary(primary)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Primary()
   * @model containment="true"
   * @generated
   */
  primary getPrimary();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getPrimary <em>Primary</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Primary</em>' containment reference.
   * @see #getPrimary()
   * @generated
   */
  void setPrimary(primary value);

  /**
   * Returns the value of the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Num</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Num</em>' attribute.
   * @see #setNum(String)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Num()
   * @model
   * @generated
   */
  String getNum();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getNum <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Num</em>' attribute.
   * @see #getNum()
   * @generated
   */
  void setNum(String value);

  /**
   * Returns the value of the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Int</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Int</em>' attribute.
   * @see #setInt(int)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Int()
   * @model
   * @generated
   */
  int getInt();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getInt <em>Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Int</em>' attribute.
   * @see #getInt()
   * @generated
   */
  void setInt(int value);

  /**
   * Returns the value of the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Str</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Str</em>' attribute.
   * @see #setStr(String)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Str()
   * @model
   * @generated
   */
  String getStr();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getStr <em>Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Str</em>' attribute.
   * @see #getStr()
   * @generated
   */
  void setStr(String value);

  /**
   * Returns the value of the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bool</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bool</em>' attribute.
   * @see #setBool(String)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Bool()
   * @model
   * @generated
   */
  String getBool();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getBool <em>Bool</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bool</em>' attribute.
   * @see #getBool()
   * @generated
   */
  void setBool(String value);

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
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Component_reference()
   * @model containment="true"
   * @generated
   */
  component_reference getComponent_reference();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getComponent_reference <em>Component reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Component reference</em>' containment reference.
   * @see #getComponent_reference()
   * @generated
   */
  void setComponent_reference(component_reference value);

  /**
   * Returns the value of the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output expr list</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output expr list</em>' containment reference.
   * @see #setOutput_expr_list(output_expression_list)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Output_expr_list()
   * @model containment="true"
   * @generated
   */
  output_expression_list getOutput_expr_list();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getOutput_expr_list <em>Output expr list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output expr list</em>' containment reference.
   * @see #getOutput_expr_list()
   * @generated
   */
  void setOutput_expr_list(output_expression_list value);

  /**
   * Returns the value of the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expre list</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expre list</em>' containment reference.
   * @see #setExpre_list(expression_list)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Expre_list()
   * @model containment="true"
   * @generated
   */
  expression_list getExpre_list();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getExpre_list <em>Expre list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expre list</em>' containment reference.
   * @see #getExpre_list()
   * @generated
   */
  void setExpre_list(expression_list value);

  /**
   * Returns the value of the '<em><b>Expression list</b></em>' containment reference list.
   * The list contents are of type {@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression list</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression list</em>' containment reference list.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_Expression_list()
   * @model containment="true"
   * @generated
   */
  EList<expression_list> getExpression_list();

  /**
   * Returns the value of the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Farguments</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Farguments</em>' containment reference.
   * @see #setF_arguments(function_arguments)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_F_arguments()
   * @model containment="true"
   * @generated
   */
  function_arguments getF_arguments();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getF_arguments <em>Farguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Farguments</em>' containment reference.
   * @see #getF_arguments()
   * @generated
   */
  void setF_arguments(function_arguments value);

  /**
   * Returns the value of the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' attribute.
   * @see #setEnd(String)
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage#getprimary_End()
   * @model
   * @generated
   */
  String getEnd();

  /**
   * Sets the value of the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getEnd <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' attribute.
   * @see #getEnd()
   * @generated
   */
  void setEnd(String value);

} // primary
