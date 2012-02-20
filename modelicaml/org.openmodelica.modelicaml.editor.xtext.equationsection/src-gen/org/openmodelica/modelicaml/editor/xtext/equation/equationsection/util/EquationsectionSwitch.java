/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation_section;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation;

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
 * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage
 * @generated
 */
public class EquationsectionSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static EquationsectionPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EquationsectionSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = EquationsectionPackage.eINSTANCE;
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
      case EquationsectionPackage.EQUATION_SECTION:
      {
        equation_section equation_section = (equation_section)theEObject;
        T result = caseequation_section(equation_section);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EquationsectionPackage.EQUATION:
      {
        equation equation = (equation)theEObject;
        T result = caseequation(equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EquationsectionPackage.WHEN_EQUATION:
      {
        when_equation when_equation = (when_equation)theEObject;
        T result = casewhen_equation(when_equation);
        if (result == null) result = caseequation(when_equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EquationsectionPackage.IF_EQUATION:
      {
        if_equation if_equation = (if_equation)theEObject;
        T result = caseif_equation(if_equation);
        if (result == null) result = caseequation(if_equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EquationsectionPackage.FOR_EQUATION:
      {
        for_equation for_equation = (for_equation)theEObject;
        T result = casefor_equation(for_equation);
        if (result == null) result = caseequation(for_equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EquationsectionPackage.CONNECT_CLAUSE:
      {
        connect_clause connect_clause = (connect_clause)theEObject;
        T result = caseconnect_clause(connect_clause);
        if (result == null) result = caseequation(connect_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>equation section</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>equation section</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseequation_section(equation_section object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>equation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>equation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseequation(equation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>when equation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>when equation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casewhen_equation(when_equation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>if equation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>if equation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseif_equation(if_equation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>for equation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>for equation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefor_equation(for_equation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>connect clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>connect clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconnect_clause(connect_clause object)
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

} //EquationsectionSwitch
