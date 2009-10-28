/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.modelica.xtext.modelicaBase.*;

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
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage
 * @generated
 */
public class ModelicaBaseSwitch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ModelicaBasePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelicaBaseSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ModelicaBasePackage.eINSTANCE;
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
      case ModelicaBasePackage.STORAGE_UNIT:
      {
        StorageUnit storageUnit = (StorageUnit)theEObject;
        T result = caseStorageUnit(storageUnit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ENTITY_DEFINITION:
      {
        EntityDefinition entityDefinition = (EntityDefinition)theEObject;
        T result = caseEntityDefinition(entityDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.CLASS_DEFINITION:
      {
        ClassDefinition classDefinition = (ClassDefinition)theEObject;
        T result = caseClassDefinition(classDefinition);
        if (result == null) result = caseEntityDefinition(classDefinition);
        if (result == null) result = caseClassElement(classDefinition);
        if (result == null) result = caseElementRedeclaration(classDefinition);
        if (result == null) result = caseargument(classDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.PARTIAL_DERIVATIVE:
      {
        PartialDerivative partialDerivative = (PartialDerivative)theEObject;
        T result = casePartialDerivative(partialDerivative);
        if (result == null) result = caseEntityDefinition(partialDerivative);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.CLASS_MODIFICATION:
      {
        ClassModification classModification = (ClassModification)theEObject;
        T result = caseClassModification(classModification);
        if (result == null) result = caseEntityDefinition(classModification);
        if (result == null) result = caseModification(classModification);
        if (result == null) result = caseAnnotation(classModification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.CLASS_OVERLOADING:
      {
        ClassOverloading classOverloading = (ClassOverloading)theEObject;
        T result = caseClassOverloading(classOverloading);
        if (result == null) result = caseEntityDefinition(classOverloading);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ENUMERATION:
      {
        Enumeration enumeration = (Enumeration)theEObject;
        T result = caseEnumeration(enumeration);
        if (result == null) result = caseEntityDefinition(enumeration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.EXTERNAL_CLAUSE:
      {
        ExternalClause externalClause = (ExternalClause)theEObject;
        T result = caseExternalClause(externalClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ELEMENT_LIST:
      {
        ElementList elementList = (ElementList)theEObject;
        T result = caseElementList(elementList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.CLASS_ELEMENT:
      {
        ClassElement classElement = (ClassElement)theEObject;
        T result = caseClassElement(classElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE:
      {
        ReplaceableElementClause replaceableElementClause = (ReplaceableElementClause)theEObject;
        T result = caseReplaceableElementClause(replaceableElementClause);
        if (result == null) result = caseClassElement(replaceableElementClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.IMPORT_CLAUSE:
      {
        ImportClause importClause = (ImportClause)theEObject;
        T result = caseImportClause(importClause);
        if (result == null) result = caseClassElement(importClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.EXTENDS_CLAUSE:
      {
        ExtendsClause extendsClause = (ExtendsClause)theEObject;
        T result = caseExtendsClause(extendsClause);
        if (result == null) result = caseClassElement(extendsClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.CONSTRAINING_CLAUSE:
      {
        ConstrainingClause constrainingClause = (ConstrainingClause)theEObject;
        T result = caseConstrainingClause(constrainingClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.COMPONENT_CLAUSE:
      {
        ComponentClause componentClause = (ComponentClause)theEObject;
        T result = caseComponentClause(componentClause);
        if (result == null) result = caseClassElement(componentClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.TYPE_SPECIFIER:
      {
        TypeSpecifier typeSpecifier = (TypeSpecifier)theEObject;
        T result = caseTypeSpecifier(typeSpecifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.COMPONENT_DECLARATION:
      {
        ComponentDeclaration componentDeclaration = (ComponentDeclaration)theEObject;
        T result = caseComponentDeclaration(componentDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.MODIFICATION:
      {
        Modification modification = (Modification)theEObject;
        T result = caseModification(modification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.SIMPLE_MODIFICATION:
      {
        SimpleModification simpleModification = (SimpleModification)theEObject;
        T result = caseSimpleModification(simpleModification);
        if (result == null) result = caseModification(simpleModification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ARGUMENT:
      {
        argument argument = (argument)theEObject;
        T result = caseargument(argument);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ELEMENT_MODIFICATION:
      {
        ElementModification elementModification = (ElementModification)theEObject;
        T result = caseElementModification(elementModification);
        if (result == null) result = caseargument(elementModification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ELEMENT_REDECLARATION:
      {
        ElementRedeclaration elementRedeclaration = (ElementRedeclaration)theEObject;
        T result = caseElementRedeclaration(elementRedeclaration);
        if (result == null) result = caseargument(elementRedeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE:
      {
        ElementReplaceableClause elementReplaceableClause = (ElementReplaceableClause)theEObject;
        T result = caseElementReplaceableClause(elementReplaceableClause);
        if (result == null) result = caseElementRedeclaration(elementReplaceableClause);
        if (result == null) result = caseargument(elementReplaceableClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE:
      {
        ElementComponentClause elementComponentClause = (ElementComponentClause)theEObject;
        T result = caseElementComponentClause(elementComponentClause);
        if (result == null) result = caseElementRedeclaration(elementComponentClause);
        if (result == null) result = caseargument(elementComponentClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.EQUATION_CLAUSE:
      {
        EquationClause equationClause = (EquationClause)theEObject;
        T result = caseEquationClause(equationClause);
        if (result == null) result = caseinitial_equation_rule_clause(equationClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ALGORITHM_CLAUSE:
      {
        algorithm_clause algorithm_clause = (algorithm_clause)theEObject;
        T result = casealgorithm_clause(algorithm_clause);
        if (result == null) result = caseinitial_algorithm_clause(algorithm_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.INITIAL_EQUATION_RULE_CLAUSE:
      {
        initial_equation_rule_clause initial_equation_rule_clause = (initial_equation_rule_clause)theEObject;
        T result = caseinitial_equation_rule_clause(initial_equation_rule_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.INITIAL_ALGORITHM_CLAUSE:
      {
        initial_algorithm_clause initial_algorithm_clause = (initial_algorithm_clause)theEObject;
        T result = caseinitial_algorithm_clause(initial_algorithm_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.EQUATION_RULE:
      {
        equation_rule equation_rule = (equation_rule)theEObject;
        T result = caseequation_rule(equation_rule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ALGORITHM:
      {
        algorithm algorithm = (algorithm)theEObject;
        T result = casealgorithm(algorithm);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE:
      {
        ConditionalEquationRule conditionalEquationRule = (ConditionalEquationRule)theEObject;
        T result = caseConditionalEquationRule(conditionalEquationRule);
        if (result == null) result = caseequation_rule(conditionalEquationRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE:
      {
        ConditionAlgorithmRule conditionAlgorithmRule = (ConditionAlgorithmRule)theEObject;
        T result = caseConditionAlgorithmRule(conditionAlgorithmRule);
        if (result == null) result = casealgorithm(conditionAlgorithmRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE:
      {
        ForEquationClause forEquationClause = (ForEquationClause)theEObject;
        T result = caseForEquationClause(forEquationClause);
        if (result == null) result = caseequation_rule(forEquationClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE:
      {
        ForAlgorithmClause forAlgorithmClause = (ForAlgorithmClause)theEObject;
        T result = caseForAlgorithmClause(forAlgorithmClause);
        if (result == null) result = casealgorithm(forAlgorithmClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.WHILE_CLAUSE:
      {
        WhileClause whileClause = (WhileClause)theEObject;
        T result = caseWhileClause(whileClause);
        if (result == null) result = casealgorithm(whileClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.WHEN_CLAUSE_E:
      {
        when_clause_e when_clause_e = (when_clause_e)theEObject;
        T result = casewhen_clause_e(when_clause_e);
        if (result == null) result = caseequation_rule(when_clause_e);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ELSE_WHEN_E:
      {
        else_when_e else_when_e = (else_when_e)theEObject;
        T result = caseelse_when_e(else_when_e);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.WHEN_CLAUSE_A:
      {
        when_clause_a when_clause_a = (when_clause_a)theEObject;
        T result = casewhen_clause_a(when_clause_a);
        if (result == null) result = casealgorithm(when_clause_a);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ELSE_WHEN_A:
      {
        else_when_a else_when_a = (else_when_a)theEObject;
        T result = caseelse_when_a(else_when_a);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF:
      {
        equation_rule_elseif equation_rule_elseif = (equation_rule_elseif)theEObject;
        T result = caseequation_rule_elseif(equation_rule_elseif);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ALGORITHM_ELSEIF:
      {
        algorithm_elseif algorithm_elseif = (algorithm_elseif)theEObject;
        T result = casealgorithm_elseif(algorithm_elseif);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.COMMENT:
      {
        Comment comment = (Comment)theEObject;
        T result = caseComment(comment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.ANNOTATION:
      {
        Annotation annotation = (Annotation)theEObject;
        T result = caseAnnotation(annotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.PUBLIC_ELEMENT_LIST:
      {
        PublicElementList publicElementList = (PublicElementList)theEObject;
        T result = casePublicElementList(publicElementList);
        if (result == null) result = caseElementList(publicElementList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModelicaBasePackage.PROTECTES_ELEMENT_LIST:
      {
        ProtectesElementList protectesElementList = (ProtectesElementList)theEObject;
        T result = caseProtectesElementList(protectesElementList);
        if (result == null) result = caseElementList(protectesElementList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Storage Unit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Storage Unit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStorageUnit(StorageUnit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Entity Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEntityDefinition(EntityDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassDefinition(ClassDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Partial Derivative</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Partial Derivative</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePartialDerivative(PartialDerivative object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class Modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class Modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassModification(ClassModification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class Overloading</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class Overloading</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassOverloading(ClassOverloading object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEnumeration(Enumeration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>External Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>External Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExternalClause(ExternalClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementList(ElementList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassElement(ClassElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Replaceable Element Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Replaceable Element Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReplaceableElementClause(ReplaceableElementClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Import Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Import Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseImportClause(ImportClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Extends Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extends Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExtendsClause(ExtendsClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constraining Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraining Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstrainingClause(ConstrainingClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Component Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComponentClause(ComponentClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Specifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Specifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTypeSpecifier(TypeSpecifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Component Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComponentDeclaration(ComponentDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModification(Modification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Simple Modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Simple Modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSimpleModification(SimpleModification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>argument</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>argument</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseargument(argument object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element Modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementModification(ElementModification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element Redeclaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Redeclaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementRedeclaration(ElementRedeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element Replaceable Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Replaceable Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementReplaceableClause(ElementReplaceableClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element Component Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Component Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementComponentClause(ElementComponentClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Equation Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Equation Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEquationClause(EquationClause object)
  {
    return null;
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
   * Returns the result of interpreting the object as an instance of '<em>initial equation rule clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>initial equation rule clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinitial_equation_rule_clause(initial_equation_rule_clause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>initial algorithm clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>initial algorithm clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinitial_algorithm_clause(initial_algorithm_clause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>equation rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>equation rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseequation_rule(equation_rule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>algorithm</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>algorithm</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casealgorithm(algorithm object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Conditional Equation Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Conditional Equation Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConditionalEquationRule(ConditionalEquationRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Condition Algorithm Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Condition Algorithm Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConditionAlgorithmRule(ConditionAlgorithmRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>For Equation Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>For Equation Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForEquationClause(ForEquationClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>For Algorithm Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>For Algorithm Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForAlgorithmClause(ForAlgorithmClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>While Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>While Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhileClause(WhileClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>when clause e</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>when clause e</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casewhen_clause_e(when_clause_e object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>else when e</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>else when e</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelse_when_e(else_when_e object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>when clause a</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>when clause a</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casewhen_clause_a(when_clause_a object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>else when a</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>else when a</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelse_when_a(else_when_a object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>equation rule elseif</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>equation rule elseif</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseequation_rule_elseif(equation_rule_elseif object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>algorithm elseif</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>algorithm elseif</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casealgorithm_elseif(algorithm_elseif object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComment(Comment object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnnotation(Annotation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Public Element List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Public Element List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePublicElementList(PublicElementList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Protectes Element List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Protectes Element List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProtectesElementList(ProtectesElementList object)
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

} //ModelicaBaseSwitch
