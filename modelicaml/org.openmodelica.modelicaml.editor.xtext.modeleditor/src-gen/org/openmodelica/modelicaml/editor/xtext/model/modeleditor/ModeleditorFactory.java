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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage
 * @generated
 */
public interface ModeleditorFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModeleditorFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorFactoryImpl.init();

  /**
   * Returns a new object of class '<em>expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>expression</em>'.
   * @generated
   */
  expression createexpression();

  /**
   * Returns a new object of class '<em>simple expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>simple expression</em>'.
   * @generated
   */
  simple_expression createsimple_expression();

  /**
   * Returns a new object of class '<em>conditional expr</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>conditional expr</em>'.
   * @generated
   */
  conditional_expr createconditional_expr();

  /**
   * Returns a new object of class '<em>logical expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>logical expression</em>'.
   * @generated
   */
  logical_expression createlogical_expression();

  /**
   * Returns a new object of class '<em>logical term</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>logical term</em>'.
   * @generated
   */
  logical_term createlogical_term();

  /**
   * Returns a new object of class '<em>logical factor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>logical factor</em>'.
   * @generated
   */
  logical_factor createlogical_factor();

  /**
   * Returns a new object of class '<em>relation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>relation</em>'.
   * @generated
   */
  relation createrelation();

  /**
   * Returns a new object of class '<em>arithmetic expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>arithmetic expression</em>'.
   * @generated
   */
  arithmetic_expression createarithmetic_expression();

  /**
   * Returns a new object of class '<em>term</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>term</em>'.
   * @generated
   */
  term createterm();

  /**
   * Returns a new object of class '<em>factor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>factor</em>'.
   * @generated
   */
  factor createfactor();

  /**
   * Returns a new object of class '<em>primary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>primary</em>'.
   * @generated
   */
  primary createprimary();

  /**
   * Returns a new object of class '<em>name Function</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>name Function</em>'.
   * @generated
   */
  name_Function createname_Function();

  /**
   * Returns a new object of class '<em>initial ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>initial ref</em>'.
   * @generated
   */
  initial_ref createinitial_ref();

  /**
   * Returns a new object of class '<em>Expr Der</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expr Der</em>'.
   * @generated
   */
  ExprDer createExprDer();

  /**
   * Returns a new object of class '<em>function call args</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>function call args</em>'.
   * @generated
   */
  function_call_args createfunction_call_args();

  /**
   * Returns a new object of class '<em>expression list</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>expression list</em>'.
   * @generated
   */
  expression_list createexpression_list();

  /**
   * Returns a new object of class '<em>name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>name</em>'.
   * @generated
   */
  name createname();

  /**
   * Returns a new object of class '<em>component reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component reference</em>'.
   * @generated
   */
  component_reference createcomponent_reference();

  /**
   * Returns a new object of class '<em>output expression list</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>output expression list</em>'.
   * @generated
   */
  output_expression_list createoutput_expression_list();

  /**
   * Returns a new object of class '<em>array subscripts</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>array subscripts</em>'.
   * @generated
   */
  array_subscripts createarray_subscripts();

  /**
   * Returns a new object of class '<em>subscript</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>subscript</em>'.
   * @generated
   */
  subscript createsubscript();

  /**
   * Returns a new object of class '<em>function arguments</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>function arguments</em>'.
   * @generated
   */
  function_arguments createfunction_arguments();

  /**
   * Returns a new object of class '<em>Fun Arguments exp</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Fun Arguments exp</em>'.
   * @generated
   */
  Fun_Arguments_exp createFun_Arguments_exp();

  /**
   * Returns a new object of class '<em>Fun Arguments for</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Fun Arguments for</em>'.
   * @generated
   */
  Fun_Arguments_for createFun_Arguments_for();

  /**
   * Returns a new object of class '<em>named arguments</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>named arguments</em>'.
   * @generated
   */
  named_arguments createnamed_arguments();

  /**
   * Returns a new object of class '<em>named argument</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>named argument</em>'.
   * @generated
   */
  named_argument createnamed_argument();

  /**
   * Returns a new object of class '<em>for indices</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>for indices</em>'.
   * @generated
   */
  for_indices createfor_indices();

  /**
   * Returns a new object of class '<em>for index</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>for index</em>'.
   * @generated
   */
  for_index createfor_index();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ModeleditorPackage getModeleditorPackage();

} //ModeleditorFactory
