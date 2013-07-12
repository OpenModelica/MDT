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
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModeleditorFactoryImpl extends EFactoryImpl implements ModeleditorFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ModeleditorFactory init()
  {
    try
    {
      ModeleditorFactory theModeleditorFactory = (ModeleditorFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/model/Modeleditor"); 
      if (theModeleditorFactory != null)
      {
        return theModeleditorFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ModeleditorFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModeleditorFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ModeleditorPackage.EXPRESSION: return createexpression();
      case ModeleditorPackage.SIMPLE_EXPRESSION: return createsimple_expression();
      case ModeleditorPackage.CONDITIONAL_EXPR: return createconditional_expr();
      case ModeleditorPackage.LOGICAL_EXPRESSION: return createlogical_expression();
      case ModeleditorPackage.LOGICAL_TERM: return createlogical_term();
      case ModeleditorPackage.LOGICAL_FACTOR: return createlogical_factor();
      case ModeleditorPackage.RELATION: return createrelation();
      case ModeleditorPackage.ARITHMETIC_EXPRESSION: return createarithmetic_expression();
      case ModeleditorPackage.TERM: return createterm();
      case ModeleditorPackage.FACTOR: return createfactor();
      case ModeleditorPackage.PRIMARY: return createprimary();
      case ModeleditorPackage.NAME_FUNCTION: return createname_Function();
      case ModeleditorPackage.INITIAL_REF: return createinitial_ref();
      case ModeleditorPackage.EXPR_DER: return createExprDer();
      case ModeleditorPackage.FUNCTION_CALL_ARGS: return createfunction_call_args();
      case ModeleditorPackage.EXPRESSION_LIST: return createexpression_list();
      case ModeleditorPackage.NAME: return createname();
      case ModeleditorPackage.COMPONENT_REFERENCE: return createcomponent_reference();
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST: return createoutput_expression_list();
      case ModeleditorPackage.ARRAY_SUBSCRIPTS: return createarray_subscripts();
      case ModeleditorPackage.SUBSCRIPT: return createsubscript();
      case ModeleditorPackage.FUNCTION_ARGUMENTS: return createfunction_arguments();
      case ModeleditorPackage.FUN_ARGUMENTS_EXP: return createFun_Arguments_exp();
      case ModeleditorPackage.FUN_ARGUMENTS_FOR: return createFun_Arguments_for();
      case ModeleditorPackage.NAMED_ARGUMENTS: return createnamed_arguments();
      case ModeleditorPackage.NAMED_ARGUMENT: return createnamed_argument();
      case ModeleditorPackage.FOR_INDICES: return createfor_indices();
      case ModeleditorPackage.FOR_INDEX: return createfor_index();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression createexpression()
  {
    expressionImpl expression = new expressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simple_expression createsimple_expression()
  {
    simple_expressionImpl simple_expression = new simple_expressionImpl();
    return simple_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_expr createconditional_expr()
  {
    conditional_exprImpl conditional_expr = new conditional_exprImpl();
    return conditional_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public logical_expression createlogical_expression()
  {
    logical_expressionImpl logical_expression = new logical_expressionImpl();
    return logical_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public logical_term createlogical_term()
  {
    logical_termImpl logical_term = new logical_termImpl();
    return logical_term;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public logical_factor createlogical_factor()
  {
    logical_factorImpl logical_factor = new logical_factorImpl();
    return logical_factor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public relation createrelation()
  {
    relationImpl relation = new relationImpl();
    return relation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public arithmetic_expression createarithmetic_expression()
  {
    arithmetic_expressionImpl arithmetic_expression = new arithmetic_expressionImpl();
    return arithmetic_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public term createterm()
  {
    termImpl term = new termImpl();
    return term;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public factor createfactor()
  {
    factorImpl factor = new factorImpl();
    return factor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public primary createprimary()
  {
    primaryImpl primary = new primaryImpl();
    return primary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public name_Function createname_Function()
  {
    name_FunctionImpl name_Function = new name_FunctionImpl();
    return name_Function;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public initial_ref createinitial_ref()
  {
    initial_refImpl initial_ref = new initial_refImpl();
    return initial_ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExprDer createExprDer()
  {
    ExprDerImpl exprDer = new ExprDerImpl();
    return exprDer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_call_args createfunction_call_args()
  {
    function_call_argsImpl function_call_args = new function_call_argsImpl();
    return function_call_args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression_list createexpression_list()
  {
    expression_listImpl expression_list = new expression_listImpl();
    return expression_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public name createname()
  {
    nameImpl name = new nameImpl();
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference createcomponent_reference()
  {
    component_referenceImpl component_reference = new component_referenceImpl();
    return component_reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public output_expression_list createoutput_expression_list()
  {
    output_expression_listImpl output_expression_list = new output_expression_listImpl();
    return output_expression_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public array_subscripts createarray_subscripts()
  {
    array_subscriptsImpl array_subscripts = new array_subscriptsImpl();
    return array_subscripts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public subscript createsubscript()
  {
    subscriptImpl subscript = new subscriptImpl();
    return subscript;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_arguments createfunction_arguments()
  {
    function_argumentsImpl function_arguments = new function_argumentsImpl();
    return function_arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Fun_Arguments_exp createFun_Arguments_exp()
  {
    Fun_Arguments_expImpl fun_Arguments_exp = new Fun_Arguments_expImpl();
    return fun_Arguments_exp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Fun_Arguments_for createFun_Arguments_for()
  {
    Fun_Arguments_forImpl fun_Arguments_for = new Fun_Arguments_forImpl();
    return fun_Arguments_for;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public named_arguments createnamed_arguments()
  {
    named_argumentsImpl named_arguments = new named_argumentsImpl();
    return named_arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public named_argument createnamed_argument()
  {
    named_argumentImpl named_argument = new named_argumentImpl();
    return named_argument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_indices createfor_indices()
  {
    for_indicesImpl for_indices = new for_indicesImpl();
    return for_indices;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_index createfor_index()
  {
    for_indexImpl for_index = new for_indexImpl();
    return for_index;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModeleditorPackage getModeleditorPackage()
  {
    return (ModeleditorPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ModeleditorPackage getPackage()
  {
    return ModeleditorPackage.eINSTANCE;
  }

} //ModeleditorFactoryImpl
