/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage
 * @generated
 */
public class ModeleditorSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ModeleditorPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModeleditorSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ModeleditorPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case ModeleditorPackage.EXPRESSION:
      {
        expression expression = (expression)theEObject;
        T result = caseexpression(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.SIMPLE_EXPRESSION:
      {
        simple_expression simple_expression = (simple_expression)theEObject;
        T result = casesimple_expression(simple_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.CONDITIONAL_EXPR:
      {
        conditional_expr conditional_expr = (conditional_expr)theEObject;
        T result = caseconditional_expr(conditional_expr);
        if (result == null) result = caseexpression(conditional_expr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.LOGICAL_EXPRESSION:
      {
        logical_expression logical_expression = (logical_expression)theEObject;
        T result = caselogical_expression(logical_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.LOGICAL_TERM:
      {
        logical_term logical_term = (logical_term)theEObject;
        T result = caselogical_term(logical_term);
        if (result == null) result = caselogical_expression(logical_term);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.LOGICAL_FACTOR:
      {
        logical_factor logical_factor = (logical_factor)theEObject;
        T result = caselogical_factor(logical_factor);
        if (result == null) result = caselogical_term(logical_factor);
        if (result == null) result = caselogical_expression(logical_factor);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.RELATION:
      {
        relation relation = (relation)theEObject;
        T result = caserelation(relation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.ARITHMETIC_EXPRESSION:
      {
        arithmetic_expression arithmetic_expression = (arithmetic_expression)theEObject;
        T result = casearithmetic_expression(arithmetic_expression);
        if (result == null) result = caserelation(arithmetic_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.TERM:
      {
        term term = (term)theEObject;
        T result = caseterm(term);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.FACTOR:
      {
        factor factor = (factor)theEObject;
        T result = casefactor(factor);
        if (result == null) result = caseterm(factor);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.PRIMARY:
      {
        primary primary = (primary)theEObject;
        T result = caseprimary(primary);
        if (result == null) result = casefactor(primary);
        if (result == null) result = caseterm(primary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.NAME_FUNCTION:
      {
        name_Function name_Function = (name_Function)theEObject;
        T result = casename_Function(name_Function);
        if (result == null) result = caseprimary(name_Function);
        if (result == null) result = casefactor(name_Function);
        if (result == null) result = caseterm(name_Function);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.INITIAL_REF:
      {
        initial_ref initial_ref = (initial_ref)theEObject;
        T result = caseinitial_ref(initial_ref);
        if (result == null) result = caseprimary(initial_ref);
        if (result == null) result = casefactor(initial_ref);
        if (result == null) result = caseterm(initial_ref);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.EXPR_DER:
      {
        ExprDer exprDer = (ExprDer)theEObject;
        T result = caseExprDer(exprDer);
        if (result == null) result = caseprimary(exprDer);
        if (result == null) result = casefactor(exprDer);
        if (result == null) result = caseterm(exprDer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.FUNCTION_CALL_ARGS:
      {
        function_call_args function_call_args = (function_call_args)theEObject;
        T result = casefunction_call_args(function_call_args);
        if (result == null) result = caseinitial_ref(function_call_args);
        if (result == null) result = caseprimary(function_call_args);
        if (result == null) result = casefactor(function_call_args);
        if (result == null) result = caseterm(function_call_args);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.EXPRESSION_LIST:
      {
        expression_list expression_list = (expression_list)theEObject;
        T result = caseexpression_list(expression_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.NAME:
      {
        name name = (name)theEObject;
        T result = casename(name);
        if (result == null) result = casename_Function(name);
        if (result == null) result = caseprimary(name);
        if (result == null) result = casefactor(name);
        if (result == null) result = caseterm(name);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.COMPONENT_REFERENCE:
      {
        component_reference component_reference = (component_reference)theEObject;
        T result = casecomponent_reference(component_reference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST:
      {
        output_expression_list output_expression_list = (output_expression_list)theEObject;
        T result = caseoutput_expression_list(output_expression_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.ARRAY_SUBSCRIPTS:
      {
        array_subscripts array_subscripts = (array_subscripts)theEObject;
        T result = casearray_subscripts(array_subscripts);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.SUBSCRIPT:
      {
        subscript subscript = (subscript)theEObject;
        T result = casesubscript(subscript);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.FUNCTION_ARGUMENTS:
      {
        function_arguments function_arguments = (function_arguments)theEObject;
        T result = casefunction_arguments(function_arguments);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.FUN_ARGUMENTS_EXP:
      {
        Fun_Arguments_exp fun_Arguments_exp = (Fun_Arguments_exp)theEObject;
        T result = caseFun_Arguments_exp(fun_Arguments_exp);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.FUN_ARGUMENTS_FOR:
      {
        Fun_Arguments_for fun_Arguments_for = (Fun_Arguments_for)theEObject;
        T result = caseFun_Arguments_for(fun_Arguments_for);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.NAMED_ARGUMENTS:
      {
        named_arguments named_arguments = (named_arguments)theEObject;
        T result = casenamed_arguments(named_arguments);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.NAMED_ARGUMENT:
      {
        named_argument named_argument = (named_argument)theEObject;
        T result = casenamed_argument(named_argument);
        if (result == null) result = casenamed_arguments(named_argument);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.FOR_INDICES:
      {
        for_indices for_indices = (for_indices)theEObject;
        T result = casefor_indices(for_indices);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModeleditorPackage.FOR_INDEX:
      {
        for_index for_index = (for_index)theEObject;
        T result = casefor_index(for_index);
        if (result == null) result = casefor_indices(for_index);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexpression(expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>simple expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>simple expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesimple_expression(simple_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditional_expr(conditional_expr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>logical expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>logical expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselogical_expression(logical_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>logical term</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>logical term</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselogical_term(logical_term object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>logical factor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>logical factor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselogical_factor(logical_factor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>relation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>relation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caserelation(relation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>arithmetic expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>arithmetic expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casearithmetic_expression(arithmetic_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>term</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>term</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseterm(term object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>factor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>factor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefactor(factor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>primary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>primary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseprimary(primary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>name Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>name Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casename_Function(name_Function object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>initial ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>initial ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinitial_ref(initial_ref object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expr Der</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expr Der</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExprDer(ExprDer object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function call args</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function call args</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunction_call_args(function_call_args object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>expression list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>expression list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexpression_list(expression_list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casename(name object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>component reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>component reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecomponent_reference(component_reference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>output expression list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>output expression list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseoutput_expression_list(output_expression_list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>array subscripts</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>array subscripts</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casearray_subscripts(array_subscripts object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>subscript</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>subscript</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesubscript(subscript object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function arguments</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function arguments</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunction_arguments(function_arguments object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fun Arguments exp</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fun Arguments exp</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFun_Arguments_exp(Fun_Arguments_exp object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fun Arguments for</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fun Arguments for</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFun_Arguments_for(Fun_Arguments_for object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>named arguments</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>named arguments</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casenamed_arguments(named_arguments object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>named argument</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>named argument</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casenamed_argument(named_argument object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for indices</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for indices</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefor_indices(for_indices object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for index</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for index</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefor_index(for_index object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //ModeleditorSwitch
