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
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage
 * @generated
 */
public interface ModificationFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModificationFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationFactoryImpl.init();

  /**
   * Returns a new object of class '<em>modification alternatives</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>modification alternatives</em>'.
   * @generated
   */
  modification_alternatives createmodification_alternatives();

  /**
   * Returns a new object of class '<em>left hand component reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>left hand component reference</em>'.
   * @generated
   */
  left_hand_component_reference createleft_hand_component_reference();

  /**
   * Returns a new object of class '<em>modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>modification</em>'.
   * @generated
   */
  modification createmodification();

  /**
   * Returns a new object of class '<em>class modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>class modification</em>'.
   * @generated
   */
  class_modification createclass_modification();

  /**
   * Returns a new object of class '<em>argument list</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>argument list</em>'.
   * @generated
   */
  argument_list createargument_list();

  /**
   * Returns a new object of class '<em>argument</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>argument</em>'.
   * @generated
   */
  argument createargument();

  /**
   * Returns a new object of class '<em>element modification or replaceable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>element modification or replaceable</em>'.
   * @generated
   */
  element_modification_or_replaceable createelement_modification_or_replaceable();

  /**
   * Returns a new object of class '<em>element modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>element modification</em>'.
   * @generated
   */
  element_modification createelement_modification();

  /**
   * Returns a new object of class '<em>element replaceable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>element replaceable</em>'.
   * @generated
   */
  element_replaceable createelement_replaceable();

  /**
   * Returns a new object of class '<em>constraining clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>constraining clause</em>'.
   * @generated
   */
  constraining_clause createconstraining_clause();

  /**
   * Returns a new object of class '<em>element redeclaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>element redeclaration</em>'.
   * @generated
   */
  element_redeclaration createelement_redeclaration();

  /**
   * Returns a new object of class '<em>component clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component clause</em>'.
   * @generated
   */
  component_clause createcomponent_clause();

  /**
   * Returns a new object of class '<em>component list</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component list</em>'.
   * @generated
   */
  component_list createcomponent_list();

  /**
   * Returns a new object of class '<em>component declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component declaration</em>'.
   * @generated
   */
  component_declaration createcomponent_declaration();

  /**
   * Returns a new object of class '<em>conditional attribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>conditional attribute</em>'.
   * @generated
   */
  conditional_attribute createconditional_attribute();

  /**
   * Returns a new object of class '<em>component clause1</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component clause1</em>'.
   * @generated
   */
  component_clause1 createcomponent_clause1();

  /**
   * Returns a new object of class '<em>component declaration1</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>component declaration1</em>'.
   * @generated
   */
  component_declaration1 createcomponent_declaration1();

  /**
   * Returns a new object of class '<em>type specifier</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>type specifier</em>'.
   * @generated
   */
  type_specifier createtype_specifier();

  /**
   * Returns a new object of class '<em>declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>declaration</em>'.
   * @generated
   */
  declaration createdeclaration();

  /**
   * Returns a new object of class '<em>class definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>class definition</em>'.
   * @generated
   */
  class_definition createclass_definition();

  /**
   * Returns a new object of class '<em>class specifier</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>class specifier</em>'.
   * @generated
   */
  class_specifier createclass_specifier();

  /**
   * Returns a new object of class '<em>enum list</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>enum list</em>'.
   * @generated
   */
  enum_list createenum_list();

  /**
   * Returns a new object of class '<em>composition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>composition</em>'.
   * @generated
   */
  composition createcomposition();

  /**
   * Returns a new object of class '<em>element list</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>element list</em>'.
   * @generated
   */
  element_list createelement_list();

  /**
   * Returns a new object of class '<em>element</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>element</em>'.
   * @generated
   */
  element createelement();

  /**
   * Returns a new object of class '<em>equation section</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>equation section</em>'.
   * @generated
   */
  equation_section createequation_section();

  /**
   * Returns a new object of class '<em>equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>equation</em>'.
   * @generated
   */
  equation createequation();

  /**
   * Returns a new object of class '<em>when equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>when equation</em>'.
   * @generated
   */
  when_equation createwhen_equation();

  /**
   * Returns a new object of class '<em>if equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>if equation</em>'.
   * @generated
   */
  if_equation createif_equation();

  /**
   * Returns a new object of class '<em>for equation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>for equation</em>'.
   * @generated
   */
  for_equation createfor_equation();

  /**
   * Returns a new object of class '<em>connect clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>connect clause</em>'.
   * @generated
   */
  connect_clause createconnect_clause();

  /**
   * Returns a new object of class '<em>algorithm section</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>algorithm section</em>'.
   * @generated
   */
  algorithm_section createalgorithm_section();

  /**
   * Returns a new object of class '<em>statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>statement</em>'.
   * @generated
   */
  statement createstatement();

  /**
   * Returns a new object of class '<em>state Output Expr List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>state Output Expr List</em>'.
   * @generated
   */
  state_OutputExprList createstate_OutputExprList();

  /**
   * Returns a new object of class '<em>state Comp Ref</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>state Comp Ref</em>'.
   * @generated
   */
  state_Comp_Ref createstate_Comp_Ref();

  /**
   * Returns a new object of class '<em>when statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>when statement</em>'.
   * @generated
   */
  when_statement createwhen_statement();

  /**
   * Returns a new object of class '<em>while statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>while statement</em>'.
   * @generated
   */
  while_statement createwhile_statement();

  /**
   * Returns a new object of class '<em>for statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>for statement</em>'.
   * @generated
   */
  for_statement createfor_statement();

  /**
   * Returns a new object of class '<em>if statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>if statement</em>'.
   * @generated
   */
  if_statement createif_statement();

  /**
   * Returns a new object of class '<em>extends clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>extends clause</em>'.
   * @generated
   */
  extends_clause createextends_clause();

  /**
   * Returns a new object of class '<em>annotation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>annotation</em>'.
   * @generated
   */
  annotation createannotation();

  /**
   * Returns a new object of class '<em>import clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>import clause</em>'.
   * @generated
   */
  import_clause createimport_clause();

  /**
   * Returns a new object of class '<em>language specification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>language specification</em>'.
   * @generated
   */
  language_specification createlanguage_specification();

  /**
   * Returns a new object of class '<em>external function call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>external function call</em>'.
   * @generated
   */
  external_function_call createexternal_function_call();

  /**
   * Returns a new object of class '<em>class modification action</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>class modification action</em>'.
   * @generated
   */
  class_modification_action createclass_modification_action();

  /**
   * Returns a new object of class '<em>algorithm clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>algorithm clause</em>'.
   * @generated
   */
  algorithm_clause createalgorithm_clause();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ModificationPackage getModificationPackage();

} //ModificationFactory
