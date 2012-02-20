/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.term;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.ProviderOperation;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.ProviderPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.component_reference;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.name;

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
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.provider.ProviderPackage
 * @generated
 */
public class ProviderSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ProviderPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProviderSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ProviderPackage.eINSTANCE;
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
      case ProviderPackage.PROVIDER_OPERATION:
      {
        ProviderOperation providerOperation = (ProviderOperation)theEObject;
        T result = caseProviderOperation(providerOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ProviderPackage.NAME:
      {
        name name = (name)theEObject;
        T result = casename(name);
        if (result == null) result = caseModeleditor_name(name);
        if (result == null) result = casename_Function(name);
        if (result == null) result = caseprimary(name);
        if (result == null) result = casefactor(name);
        if (result == null) result = caseterm(name);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ProviderPackage.COMPONENT_REFERENCE:
      {
        component_reference component_reference = (component_reference)theEObject;
        T result = casecomponent_reference(component_reference);
        if (result == null) result = caseModeleditor_component_reference(component_reference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProviderOperation(ProviderOperation object)
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
  public T caseModeleditor_name(org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name object)
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
  public T caseModeleditor_component_reference(org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference object)
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

} //ProviderSwitch
