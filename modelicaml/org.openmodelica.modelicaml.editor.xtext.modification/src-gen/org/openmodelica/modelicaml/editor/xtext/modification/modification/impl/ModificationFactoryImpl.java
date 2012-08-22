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
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationFactory;
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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModificationFactoryImpl extends EFactoryImpl implements ModificationFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ModificationFactory init()
  {
    try
    {
      ModificationFactory theModificationFactory = (ModificationFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/modification/Modification"); 
      if (theModificationFactory != null)
      {
        return theModificationFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ModificationFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModificationFactoryImpl()
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
      case ModificationPackage.MODIFICATION_ALTERNATIVES: return createmodification_alternatives();
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE: return createleft_hand_component_reference();
      case ModificationPackage.MODIFICATION: return createmodification();
      case ModificationPackage.CLASS_MODIFICATION: return createclass_modification();
      case ModificationPackage.ARGUMENT_LIST: return createargument_list();
      case ModificationPackage.ARGUMENT: return createargument();
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE: return createelement_modification_or_replaceable();
      case ModificationPackage.ELEMENT_MODIFICATION: return createelement_modification();
      case ModificationPackage.ELEMENT_REPLACEABLE: return createelement_replaceable();
      case ModificationPackage.CONSTRAINING_CLAUSE: return createconstraining_clause();
      case ModificationPackage.ELEMENT_REDECLARATION: return createelement_redeclaration();
      case ModificationPackage.COMPONENT_CLAUSE: return createcomponent_clause();
      case ModificationPackage.COMPONENT_LIST: return createcomponent_list();
      case ModificationPackage.COMPONENT_DECLARATION: return createcomponent_declaration();
      case ModificationPackage.CONDITIONAL_ATTRIBUTE: return createconditional_attribute();
      case ModificationPackage.COMPONENT_CLAUSE1: return createcomponent_clause1();
      case ModificationPackage.COMPONENT_DECLARATION1: return createcomponent_declaration1();
      case ModificationPackage.TYPE_SPECIFIER: return createtype_specifier();
      case ModificationPackage.DECLARATION: return createdeclaration();
      case ModificationPackage.CLASS_DEFINITION: return createclass_definition();
      case ModificationPackage.CLASS_SPECIFIER: return createclass_specifier();
      case ModificationPackage.ENUM_LIST: return createenum_list();
      case ModificationPackage.COMPOSITION: return createcomposition();
      case ModificationPackage.ELEMENT_LIST: return createelement_list();
      case ModificationPackage.ELEMENT: return createelement();
      case ModificationPackage.EQUATION_SECTION: return createequation_section();
      case ModificationPackage.EQUATION: return createequation();
      case ModificationPackage.WHEN_EQUATION: return createwhen_equation();
      case ModificationPackage.IF_EQUATION: return createif_equation();
      case ModificationPackage.FOR_EQUATION: return createfor_equation();
      case ModificationPackage.CONNECT_CLAUSE: return createconnect_clause();
      case ModificationPackage.ALGORITHM_SECTION: return createalgorithm_section();
      case ModificationPackage.STATEMENT: return createstatement();
      case ModificationPackage.STATE_OUTPUT_EXPR_LIST: return createstate_OutputExprList();
      case ModificationPackage.STATE_COMP_REF: return createstate_Comp_Ref();
      case ModificationPackage.WHEN_STATEMENT: return createwhen_statement();
      case ModificationPackage.WHILE_STATEMENT: return createwhile_statement();
      case ModificationPackage.FOR_STATEMENT: return createfor_statement();
      case ModificationPackage.IF_STATEMENT: return createif_statement();
      case ModificationPackage.EXTENDS_CLAUSE: return createextends_clause();
      case ModificationPackage.ANNOTATION: return createannotation();
      case ModificationPackage.IMPORT_CLAUSE: return createimport_clause();
      case ModificationPackage.LANGUAGE_SPECIFICATION: return createlanguage_specification();
      case ModificationPackage.EXTERNAL_FUNCTION_CALL: return createexternal_function_call();
      case ModificationPackage.CLASS_MODIFICATION_ACTION: return createclass_modification_action();
      case ModificationPackage.ALGORITHM_CLAUSE: return createalgorithm_clause();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public modification_alternatives createmodification_alternatives()
  {
    modification_alternativesImpl modification_alternatives = new modification_alternativesImpl();
    return modification_alternatives;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public left_hand_component_reference createleft_hand_component_reference()
  {
    left_hand_component_referenceImpl left_hand_component_reference = new left_hand_component_referenceImpl();
    return left_hand_component_reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public modification createmodification()
  {
    modificationImpl modification = new modificationImpl();
    return modification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class_modification createclass_modification()
  {
    class_modificationImpl class_modification = new class_modificationImpl();
    return class_modification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public argument_list createargument_list()
  {
    argument_listImpl argument_list = new argument_listImpl();
    return argument_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public argument createargument()
  {
    argumentImpl argument = new argumentImpl();
    return argument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_modification_or_replaceable createelement_modification_or_replaceable()
  {
    element_modification_or_replaceableImpl element_modification_or_replaceable = new element_modification_or_replaceableImpl();
    return element_modification_or_replaceable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_modification createelement_modification()
  {
    element_modificationImpl element_modification = new element_modificationImpl();
    return element_modification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_replaceable createelement_replaceable()
  {
    element_replaceableImpl element_replaceable = new element_replaceableImpl();
    return element_replaceable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constraining_clause createconstraining_clause()
  {
    constraining_clauseImpl constraining_clause = new constraining_clauseImpl();
    return constraining_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_redeclaration createelement_redeclaration()
  {
    element_redeclarationImpl element_redeclaration = new element_redeclarationImpl();
    return element_redeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_clause createcomponent_clause()
  {
    component_clauseImpl component_clause = new component_clauseImpl();
    return component_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_list createcomponent_list()
  {
    component_listImpl component_list = new component_listImpl();
    return component_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_declaration createcomponent_declaration()
  {
    component_declarationImpl component_declaration = new component_declarationImpl();
    return component_declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_attribute createconditional_attribute()
  {
    conditional_attributeImpl conditional_attribute = new conditional_attributeImpl();
    return conditional_attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_clause1 createcomponent_clause1()
  {
    component_clause1Impl component_clause1 = new component_clause1Impl();
    return component_clause1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_declaration1 createcomponent_declaration1()
  {
    component_declaration1Impl component_declaration1 = new component_declaration1Impl();
    return component_declaration1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public type_specifier createtype_specifier()
  {
    type_specifierImpl type_specifier = new type_specifierImpl();
    return type_specifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaration createdeclaration()
  {
    declarationImpl declaration = new declarationImpl();
    return declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class_definition createclass_definition()
  {
    class_definitionImpl class_definition = new class_definitionImpl();
    return class_definition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class_specifier createclass_specifier()
  {
    class_specifierImpl class_specifier = new class_specifierImpl();
    return class_specifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public enum_list createenum_list()
  {
    enum_listImpl enum_list = new enum_listImpl();
    return enum_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public composition createcomposition()
  {
    compositionImpl composition = new compositionImpl();
    return composition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_list createelement_list()
  {
    element_listImpl element_list = new element_listImpl();
    return element_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element createelement()
  {
    elementImpl element = new elementImpl();
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public equation_section createequation_section()
  {
    equation_sectionImpl equation_section = new equation_sectionImpl();
    return equation_section;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public equation createequation()
  {
    equationImpl equation = new equationImpl();
    return equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public when_equation createwhen_equation()
  {
    when_equationImpl when_equation = new when_equationImpl();
    return when_equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public if_equation createif_equation()
  {
    if_equationImpl if_equation = new if_equationImpl();
    return if_equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_equation createfor_equation()
  {
    for_equationImpl for_equation = new for_equationImpl();
    return for_equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public connect_clause createconnect_clause()
  {
    connect_clauseImpl connect_clause = new connect_clauseImpl();
    return connect_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public algorithm_section createalgorithm_section()
  {
    algorithm_sectionImpl algorithm_section = new algorithm_sectionImpl();
    return algorithm_section;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public statement createstatement()
  {
    statementImpl statement = new statementImpl();
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state_OutputExprList createstate_OutputExprList()
  {
    state_OutputExprListImpl state_OutputExprList = new state_OutputExprListImpl();
    return state_OutputExprList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public state_Comp_Ref createstate_Comp_Ref()
  {
    state_Comp_RefImpl state_Comp_Ref = new state_Comp_RefImpl();
    return state_Comp_Ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public when_statement createwhen_statement()
  {
    when_statementImpl when_statement = new when_statementImpl();
    return when_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public while_statement createwhile_statement()
  {
    while_statementImpl while_statement = new while_statementImpl();
    return while_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_statement createfor_statement()
  {
    for_statementImpl for_statement = new for_statementImpl();
    return for_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public if_statement createif_statement()
  {
    if_statementImpl if_statement = new if_statementImpl();
    return if_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public extends_clause createextends_clause()
  {
    extends_clauseImpl extends_clause = new extends_clauseImpl();
    return extends_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public annotation createannotation()
  {
    annotationImpl annotation = new annotationImpl();
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public import_clause createimport_clause()
  {
    import_clauseImpl import_clause = new import_clauseImpl();
    return import_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public language_specification createlanguage_specification()
  {
    language_specificationImpl language_specification = new language_specificationImpl();
    return language_specification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public external_function_call createexternal_function_call()
  {
    external_function_callImpl external_function_call = new external_function_callImpl();
    return external_function_call;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class_modification_action createclass_modification_action()
  {
    class_modification_actionImpl class_modification_action = new class_modification_actionImpl();
    return class_modification_action;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public algorithm_clause createalgorithm_clause()
  {
    algorithm_clauseImpl algorithm_clause = new algorithm_clauseImpl();
    return algorithm_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModificationPackage getModificationPackage()
  {
    return (ModificationPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ModificationPackage getPackage()
  {
    return ModificationPackage.eINSTANCE;
  }

} //ModificationFactoryImpl
