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
package org.openmodelica.modelicaml.editor.xtext.modification.modification.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage
 * @generated
 */
public class ModificationAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ModificationPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModificationAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ModificationPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModificationSwitch<Adapter> modelSwitch =
    new ModificationSwitch<Adapter>()
    {
      @Override
      public Adapter casemodification_alternatives(modification_alternatives object)
      {
        return createmodification_alternativesAdapter();
      }
      @Override
      public Adapter caseleft_hand_component_reference(left_hand_component_reference object)
      {
        return createleft_hand_component_referenceAdapter();
      }
      @Override
      public Adapter casemodification(modification object)
      {
        return createmodificationAdapter();
      }
      @Override
      public Adapter caseclass_modification(class_modification object)
      {
        return createclass_modificationAdapter();
      }
      @Override
      public Adapter caseargument_list(argument_list object)
      {
        return createargument_listAdapter();
      }
      @Override
      public Adapter caseargument(argument object)
      {
        return createargumentAdapter();
      }
      @Override
      public Adapter caseelement_modification_or_replaceable(element_modification_or_replaceable object)
      {
        return createelement_modification_or_replaceableAdapter();
      }
      @Override
      public Adapter caseelement_modification(element_modification object)
      {
        return createelement_modificationAdapter();
      }
      @Override
      public Adapter caseelement_replaceable(element_replaceable object)
      {
        return createelement_replaceableAdapter();
      }
      @Override
      public Adapter caseconstraining_clause(constraining_clause object)
      {
        return createconstraining_clauseAdapter();
      }
      @Override
      public Adapter caseelement_redeclaration(element_redeclaration object)
      {
        return createelement_redeclarationAdapter();
      }
      @Override
      public Adapter casecomponent_clause(component_clause object)
      {
        return createcomponent_clauseAdapter();
      }
      @Override
      public Adapter casecomponent_list(component_list object)
      {
        return createcomponent_listAdapter();
      }
      @Override
      public Adapter casecomponent_declaration(component_declaration object)
      {
        return createcomponent_declarationAdapter();
      }
      @Override
      public Adapter caseconditional_attribute(conditional_attribute object)
      {
        return createconditional_attributeAdapter();
      }
      @Override
      public Adapter casecomponent_clause1(component_clause1 object)
      {
        return createcomponent_clause1Adapter();
      }
      @Override
      public Adapter casecomponent_declaration1(component_declaration1 object)
      {
        return createcomponent_declaration1Adapter();
      }
      @Override
      public Adapter casetype_specifier(type_specifier object)
      {
        return createtype_specifierAdapter();
      }
      @Override
      public Adapter casedeclaration(declaration object)
      {
        return createdeclarationAdapter();
      }
      @Override
      public Adapter caseclass_definition(class_definition object)
      {
        return createclass_definitionAdapter();
      }
      @Override
      public Adapter caseclass_specifier(class_specifier object)
      {
        return createclass_specifierAdapter();
      }
      @Override
      public Adapter caseenum_list(enum_list object)
      {
        return createenum_listAdapter();
      }
      @Override
      public Adapter casecomposition(composition object)
      {
        return createcompositionAdapter();
      }
      @Override
      public Adapter caseelement_list(element_list object)
      {
        return createelement_listAdapter();
      }
      @Override
      public Adapter caseelement(element object)
      {
        return createelementAdapter();
      }
      @Override
      public Adapter caseequation_section(equation_section object)
      {
        return createequation_sectionAdapter();
      }
      @Override
      public Adapter caseequation(equation object)
      {
        return createequationAdapter();
      }
      @Override
      public Adapter casewhen_equation(when_equation object)
      {
        return createwhen_equationAdapter();
      }
      @Override
      public Adapter caseif_equation(if_equation object)
      {
        return createif_equationAdapter();
      }
      @Override
      public Adapter casefor_equation(for_equation object)
      {
        return createfor_equationAdapter();
      }
      @Override
      public Adapter caseconnect_clause(connect_clause object)
      {
        return createconnect_clauseAdapter();
      }
      @Override
      public Adapter casealgorithm_section(algorithm_section object)
      {
        return createalgorithm_sectionAdapter();
      }
      @Override
      public Adapter casestatement(statement object)
      {
        return createstatementAdapter();
      }
      @Override
      public Adapter casestate_OutputExprList(state_OutputExprList object)
      {
        return createstate_OutputExprListAdapter();
      }
      @Override
      public Adapter casestate_Comp_Ref(state_Comp_Ref object)
      {
        return createstate_Comp_RefAdapter();
      }
      @Override
      public Adapter casewhen_statement(when_statement object)
      {
        return createwhen_statementAdapter();
      }
      @Override
      public Adapter casewhile_statement(while_statement object)
      {
        return createwhile_statementAdapter();
      }
      @Override
      public Adapter casefor_statement(for_statement object)
      {
        return createfor_statementAdapter();
      }
      @Override
      public Adapter caseif_statement(if_statement object)
      {
        return createif_statementAdapter();
      }
      @Override
      public Adapter caseextends_clause(extends_clause object)
      {
        return createextends_clauseAdapter();
      }
      @Override
      public Adapter caseannotation(annotation object)
      {
        return createannotationAdapter();
      }
      @Override
      public Adapter caseimport_clause(import_clause object)
      {
        return createimport_clauseAdapter();
      }
      @Override
      public Adapter caselanguage_specification(language_specification object)
      {
        return createlanguage_specificationAdapter();
      }
      @Override
      public Adapter caseexternal_function_call(external_function_call object)
      {
        return createexternal_function_callAdapter();
      }
      @Override
      public Adapter caseclass_modification_action(class_modification_action object)
      {
        return createclass_modification_actionAdapter();
      }
      @Override
      public Adapter casealgorithm_clause(algorithm_clause object)
      {
        return createalgorithm_clauseAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives <em>modification alternatives</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives
   * @generated
   */
  public Adapter createmodification_alternativesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference <em>left hand component reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference
   * @generated
   */
  public Adapter createleft_hand_component_referenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification <em>modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.modification
   * @generated
   */
  public Adapter createmodificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification <em>class modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification
   * @generated
   */
  public Adapter createclass_modificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.argument_list <em>argument list</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.argument_list
   * @generated
   */
  public Adapter createargument_listAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.argument <em>argument</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.argument
   * @generated
   */
  public Adapter createargumentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable <em>element modification or replaceable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable
   * @generated
   */
  public Adapter createelement_modification_or_replaceableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification <em>element modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification
   * @generated
   */
  public Adapter createelement_modificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable <em>element replaceable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable
   * @generated
   */
  public Adapter createelement_replaceableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause <em>constraining clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause
   * @generated
   */
  public Adapter createconstraining_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration <em>element redeclaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration
   * @generated
   */
  public Adapter createelement_redeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause <em>component clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause
   * @generated
   */
  public Adapter createcomponent_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_list <em>component list</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_list
   * @generated
   */
  public Adapter createcomponent_listAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration <em>component declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration
   * @generated
   */
  public Adapter createcomponent_declarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute <em>conditional attribute</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute
   * @generated
   */
  public Adapter createconditional_attributeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1 <em>component clause1</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1
   * @generated
   */
  public Adapter createcomponent_clause1Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration1 <em>component declaration1</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration1
   * @generated
   */
  public Adapter createcomponent_declaration1Adapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier <em>type specifier</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier
   * @generated
   */
  public Adapter createtype_specifierAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration <em>declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration
   * @generated
   */
  public Adapter createdeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition <em>class definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition
   * @generated
   */
  public Adapter createclass_definitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier <em>class specifier</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier
   * @generated
   */
  public Adapter createclass_specifierAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list <em>enum list</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list
   * @generated
   */
  public Adapter createenum_listAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition <em>composition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition
   * @generated
   */
  public Adapter createcompositionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list <em>element list</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list
   * @generated
   */
  public Adapter createelement_listAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element <em>element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element
   * @generated
   */
  public Adapter createelementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section <em>equation section</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section
   * @generated
   */
  public Adapter createequation_sectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation <em>equation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation
   * @generated
   */
  public Adapter createequationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation <em>when equation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation
   * @generated
   */
  public Adapter createwhen_equationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation <em>if equation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation
   * @generated
   */
  public Adapter createif_equationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation <em>for equation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation
   * @generated
   */
  public Adapter createfor_equationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause <em>connect clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause
   * @generated
   */
  public Adapter createconnect_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section <em>algorithm section</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section
   * @generated
   */
  public Adapter createalgorithm_sectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement <em>statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement
   * @generated
   */
  public Adapter createstatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList <em>state Output Expr List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList
   * @generated
   */
  public Adapter createstate_OutputExprListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref <em>state Comp Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref
   * @generated
   */
  public Adapter createstate_Comp_RefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement <em>when statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement
   * @generated
   */
  public Adapter createwhen_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement <em>while statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement
   * @generated
   */
  public Adapter createwhile_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement <em>for statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement
   * @generated
   */
  public Adapter createfor_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement <em>if statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement
   * @generated
   */
  public Adapter createif_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause <em>extends clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause
   * @generated
   */
  public Adapter createextends_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation <em>annotation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation
   * @generated
   */
  public Adapter createannotationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.import_clause <em>import clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.import_clause
   * @generated
   */
  public Adapter createimport_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification <em>language specification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification
   * @generated
   */
  public Adapter createlanguage_specificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call <em>external function call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call
   * @generated
   */
  public Adapter createexternal_function_callAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action <em>class modification action</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action
   * @generated
   */
  public Adapter createclass_modification_actionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause <em>algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause
   * @generated
   */
  public Adapter createalgorithm_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //ModificationAdapterFactory
