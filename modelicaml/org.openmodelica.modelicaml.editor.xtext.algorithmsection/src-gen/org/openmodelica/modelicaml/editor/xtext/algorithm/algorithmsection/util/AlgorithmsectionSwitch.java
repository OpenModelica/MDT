/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement;

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
 * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage
 * @generated
 */
public class AlgorithmsectionSwitch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static AlgorithmsectionPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AlgorithmsectionSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = AlgorithmsectionPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case AlgorithmsectionPackage.ALGORITHM_CLAUSE:
      {
        algorithm_clause algorithm_clause = (algorithm_clause)theEObject;
        T result = casealgorithm_clause(algorithm_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.STATEMENT:
      {
        statement statement = (statement)theEObject;
        T result = casestatement(statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.ALGORITHM_MACROS_GEN_SIGNAL:
      {
        Algorithm_Macros_GEN_SIGNAL algorithm_Macros_GEN_SIGNAL = (Algorithm_Macros_GEN_SIGNAL)theEObject;
        T result = caseAlgorithm_Macros_GEN_SIGNAL(algorithm_Macros_GEN_SIGNAL);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.ALGORITHM_MACROS_GEN_INC:
      {
        Algorithm_Macros_GEN_INC algorithm_Macros_GEN_INC = (Algorithm_Macros_GEN_INC)theEObject;
        T result = caseAlgorithm_Macros_GEN_INC(algorithm_Macros_GEN_INC);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.ALGORITHM_MACROS_GEN_CHANGE:
      {
        Algorithm_Macros_GEN_CHANGE algorithm_Macros_GEN_CHANGE = (Algorithm_Macros_GEN_CHANGE)theEObject;
        T result = caseAlgorithm_Macros_GEN_CHANGE(algorithm_Macros_GEN_CHANGE);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST:
      {
        state_OutputExprList state_OutputExprList = (state_OutputExprList)theEObject;
        T result = casestate_OutputExprList(state_OutputExprList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.STATE_COMP_REF:
      {
        state_Comp_Ref state_Comp_Ref = (state_Comp_Ref)theEObject;
        T result = casestate_Comp_Ref(state_Comp_Ref);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.WHEN_STATEMENT:
      {
        when_statement when_statement = (when_statement)theEObject;
        T result = casewhen_statement(when_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.WHILE_STATEMENT:
      {
        while_statement while_statement = (while_statement)theEObject;
        T result = casewhile_statement(while_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.FOR_STATEMENT:
      {
        for_statement for_statement = (for_statement)theEObject;
        T result = casefor_statement(for_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case AlgorithmsectionPackage.IF_STATEMENT:
      {
        if_statement if_statement = (if_statement)theEObject;
        T result = caseif_statement(if_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>algorithm clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>algorithm clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casealgorithm_clause(algorithm_clause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestatement(statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Algorithm Macros GEN SIGNAL</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Algorithm Macros GEN SIGNAL</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAlgorithm_Macros_GEN_SIGNAL(Algorithm_Macros_GEN_SIGNAL object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Algorithm Macros GEN INC</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Algorithm Macros GEN INC</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAlgorithm_Macros_GEN_INC(Algorithm_Macros_GEN_INC object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Algorithm Macros GEN CHANGE</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Algorithm Macros GEN CHANGE</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAlgorithm_Macros_GEN_CHANGE(Algorithm_Macros_GEN_CHANGE object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>state Output Expr List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>state Output Expr List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestate_OutputExprList(state_OutputExprList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>state Comp Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>state Comp Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestate_Comp_Ref(state_Comp_Ref object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>when statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>when statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casewhen_statement(when_statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>while statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>while statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casewhile_statement(while_statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefor_statement(for_statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>if statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>if statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseif_statement(if_statement object)
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
  public T defaultCase(EObject object)
  {
    return null;
  }

} //AlgorithmsectionSwitch
