/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.argument;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.argument_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration1;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.composition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.import_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement;

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
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage
 * @generated
 */
public class ModificationSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ModificationPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModificationSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ModificationPackage.eINSTANCE;
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
      case ModificationPackage.MODIFICATION_ALTERNATIVES:
      {
        modification_alternatives modification_alternatives = (modification_alternatives)theEObject;
        T result = casemodification_alternatives(modification_alternatives);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE:
      {
        left_hand_component_reference left_hand_component_reference = (left_hand_component_reference)theEObject;
        T result = caseleft_hand_component_reference(left_hand_component_reference);
        if (result == null) result = casemodification_alternatives(left_hand_component_reference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.MODIFICATION:
      {
        modification modification = (modification)theEObject;
        T result = casemodification(modification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.CLASS_MODIFICATION:
      {
        class_modification class_modification = (class_modification)theEObject;
        T result = caseclass_modification(class_modification);
        if (result == null) result = casemodification(class_modification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ARGUMENT_LIST:
      {
        argument_list argument_list = (argument_list)theEObject;
        T result = caseargument_list(argument_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ARGUMENT:
      {
        argument argument = (argument)theEObject;
        T result = caseargument(argument);
        if (result == null) result = caseargument_list(argument);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE:
      {
        element_modification_or_replaceable element_modification_or_replaceable = (element_modification_or_replaceable)theEObject;
        T result = caseelement_modification_or_replaceable(element_modification_or_replaceable);
        if (result == null) result = caseargument(element_modification_or_replaceable);
        if (result == null) result = caseargument_list(element_modification_or_replaceable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ELEMENT_MODIFICATION:
      {
        element_modification element_modification = (element_modification)theEObject;
        T result = caseelement_modification(element_modification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ELEMENT_REPLACEABLE:
      {
        element_replaceable element_replaceable = (element_replaceable)theEObject;
        T result = caseelement_replaceable(element_replaceable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.CONSTRAINING_CLAUSE:
      {
        constraining_clause constraining_clause = (constraining_clause)theEObject;
        T result = caseconstraining_clause(constraining_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ELEMENT_REDECLARATION:
      {
        element_redeclaration element_redeclaration = (element_redeclaration)theEObject;
        T result = caseelement_redeclaration(element_redeclaration);
        if (result == null) result = caseargument(element_redeclaration);
        if (result == null) result = caseargument_list(element_redeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.COMPONENT_CLAUSE:
      {
        component_clause component_clause = (component_clause)theEObject;
        T result = casecomponent_clause(component_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.COMPONENT_LIST:
      {
        component_list component_list = (component_list)theEObject;
        T result = casecomponent_list(component_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.COMPONENT_DECLARATION:
      {
        component_declaration component_declaration = (component_declaration)theEObject;
        T result = casecomponent_declaration(component_declaration);
        if (result == null) result = casecomponent_list(component_declaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.CONDITIONAL_ATTRIBUTE:
      {
        conditional_attribute conditional_attribute = (conditional_attribute)theEObject;
        T result = caseconditional_attribute(conditional_attribute);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.COMPONENT_CLAUSE1:
      {
        component_clause1 component_clause1 = (component_clause1)theEObject;
        T result = casecomponent_clause1(component_clause1);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.COMPONENT_DECLARATION1:
      {
        component_declaration1 component_declaration1 = (component_declaration1)theEObject;
        T result = casecomponent_declaration1(component_declaration1);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.TYPE_SPECIFIER:
      {
        type_specifier type_specifier = (type_specifier)theEObject;
        T result = casetype_specifier(type_specifier);
        if (result == null) result = casecomponent_clause(type_specifier);
        if (result == null) result = casecomponent_clause1(type_specifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.DECLARATION:
      {
        declaration declaration = (declaration)theEObject;
        T result = casedeclaration(declaration);
        if (result == null) result = casecomponent_declaration1(declaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.CLASS_DEFINITION:
      {
        class_definition class_definition = (class_definition)theEObject;
        T result = caseclass_definition(class_definition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.CLASS_SPECIFIER:
      {
        class_specifier class_specifier = (class_specifier)theEObject;
        T result = caseclass_specifier(class_specifier);
        if (result == null) result = caseclass_definition(class_specifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ENUM_LIST:
      {
        enum_list enum_list = (enum_list)theEObject;
        T result = caseenum_list(enum_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.COMPOSITION:
      {
        composition composition = (composition)theEObject;
        T result = casecomposition(composition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ELEMENT_LIST:
      {
        element_list element_list = (element_list)theEObject;
        T result = caseelement_list(element_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ELEMENT:
      {
        element element = (element)theEObject;
        T result = caseelement(element);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.EQUATION_SECTION:
      {
        equation_section equation_section = (equation_section)theEObject;
        T result = caseequation_section(equation_section);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.EQUATION:
      {
        equation equation = (equation)theEObject;
        T result = caseequation(equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.WHEN_EQUATION:
      {
        when_equation when_equation = (when_equation)theEObject;
        T result = casewhen_equation(when_equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.IF_EQUATION:
      {
        if_equation if_equation = (if_equation)theEObject;
        T result = caseif_equation(if_equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.FOR_EQUATION:
      {
        for_equation for_equation = (for_equation)theEObject;
        T result = casefor_equation(for_equation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.CONNECT_CLAUSE:
      {
        connect_clause connect_clause = (connect_clause)theEObject;
        T result = caseconnect_clause(connect_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ALGORITHM_SECTION:
      {
        algorithm_section algorithm_section = (algorithm_section)theEObject;
        T result = casealgorithm_section(algorithm_section);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.STATEMENT:
      {
        statement statement = (statement)theEObject;
        T result = casestatement(statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.STATE_OUTPUT_EXPR_LIST:
      {
        state_OutputExprList state_OutputExprList = (state_OutputExprList)theEObject;
        T result = casestate_OutputExprList(state_OutputExprList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.STATE_COMP_REF:
      {
        state_Comp_Ref state_Comp_Ref = (state_Comp_Ref)theEObject;
        T result = casestate_Comp_Ref(state_Comp_Ref);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.WHEN_STATEMENT:
      {
        when_statement when_statement = (when_statement)theEObject;
        T result = casewhen_statement(when_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.WHILE_STATEMENT:
      {
        while_statement while_statement = (while_statement)theEObject;
        T result = casewhile_statement(while_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.FOR_STATEMENT:
      {
        for_statement for_statement = (for_statement)theEObject;
        T result = casefor_statement(for_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.IF_STATEMENT:
      {
        if_statement if_statement = (if_statement)theEObject;
        T result = caseif_statement(if_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.EXTENDS_CLAUSE:
      {
        extends_clause extends_clause = (extends_clause)theEObject;
        T result = caseextends_clause(extends_clause);
        if (result == null) result = caseelement(extends_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ANNOTATION:
      {
        annotation annotation = (annotation)theEObject;
        T result = caseannotation(annotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.IMPORT_CLAUSE:
      {
        import_clause import_clause = (import_clause)theEObject;
        T result = caseimport_clause(import_clause);
        if (result == null) result = caseelement(import_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.LANGUAGE_SPECIFICATION:
      {
        language_specification language_specification = (language_specification)theEObject;
        T result = caselanguage_specification(language_specification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.EXTERNAL_FUNCTION_CALL:
      {
        external_function_call external_function_call = (external_function_call)theEObject;
        T result = caseexternal_function_call(external_function_call);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.CLASS_MODIFICATION_ACTION:
      {
        class_modification_action class_modification_action = (class_modification_action)theEObject;
        T result = caseclass_modification_action(class_modification_action);
        if (result == null) result = caseclass_modification(class_modification_action);
        if (result == null) result = casemodification(class_modification_action);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModificationPackage.ALGORITHM_CLAUSE:
      {
        algorithm_clause algorithm_clause = (algorithm_clause)theEObject;
        T result = casealgorithm_clause(algorithm_clause);
        if (result == null) result = casealgorithm_section(algorithm_clause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>modification alternatives</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>modification alternatives</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemodification_alternatives(modification_alternatives object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>left hand component reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>left hand component reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseleft_hand_component_reference(left_hand_component_reference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemodification(modification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>class modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>class modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseclass_modification(class_modification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>argument list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>argument list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseargument_list(argument_list object)
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
   * Returns the result of interpreting the object as an instance of '<em>element modification or replaceable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>element modification or replaceable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelement_modification_or_replaceable(element_modification_or_replaceable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>element modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>element modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelement_modification(element_modification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>element replaceable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>element replaceable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelement_replaceable(element_replaceable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>constraining clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>constraining clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconstraining_clause(constraining_clause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>element redeclaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>element redeclaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelement_redeclaration(element_redeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>component clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>component clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecomponent_clause(component_clause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>component list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>component list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecomponent_list(component_list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>component declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>component declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecomponent_declaration(component_declaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional attribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional attribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditional_attribute(conditional_attribute object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>component clause1</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>component clause1</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecomponent_clause1(component_clause1 object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>component declaration1</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>component declaration1</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecomponent_declaration1(component_declaration1 object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>type specifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>type specifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetype_specifier(type_specifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedeclaration(declaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>class definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>class definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseclass_definition(class_definition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>class specifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>class specifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseclass_specifier(class_specifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>enum list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>enum list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseenum_list(enum_list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>composition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>composition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casecomposition(composition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>element list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>element list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelement_list(element_list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseelement(element object)
  {
    return null;
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
   * Returns the result of interpreting the object as an instance of '<em>algorithm section</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>algorithm section</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casealgorithm_section(algorithm_section object)
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
   * Returns the result of interpreting the object as an instance of '<em>extends clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>extends clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseextends_clause(extends_clause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>annotation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>annotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseannotation(annotation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>import clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>import clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseimport_clause(import_clause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>language specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>language specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselanguage_specification(language_specification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>external function call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>external function call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexternal_function_call(external_function_call object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>class modification action</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>class modification action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseclass_modification_action(class_modification_action object)
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

} //ModificationSwitch
