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
package org.openmodelica.modelicaml.editor.xtext.modification.modification;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationFactory
 * @model kind="package"
 * @generated
 */
public interface ModificationPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "modification";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/modification/Modification";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "modification";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModificationPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modification_alternativesImpl <em>modification alternatives</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modification_alternativesImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getmodification_alternatives()
   * @generated
   */
  int MODIFICATION_ALTERNATIVES = 0;

  /**
   * The feature id for the '<em><b>Modification Rule</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION_ALTERNATIVES__MODIFICATION_RULE = 0;

  /**
   * The number of structural features of the '<em>modification alternatives</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION_ALTERNATIVES_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl <em>left hand component reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getleft_hand_component_reference()
   * @generated
   */
  int LEFT_HAND_COMPONENT_REFERENCE = 1;

  /**
   * The feature id for the '<em><b>Modification Rule</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__MODIFICATION_RULE = MODIFICATION_ALTERNATIVES__MODIFICATION_RULE;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__EXPR = MODIFICATION_ALTERNATIVES_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__REF = MODIFICATION_ALTERNATIVES_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Subscripts1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1 = MODIFICATION_ALTERNATIVES_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Ref1</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__REF1 = MODIFICATION_ALTERNATIVES_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS = MODIFICATION_ALTERNATIVES_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>left hand component reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE_FEATURE_COUNT = MODIFICATION_ALTERNATIVES_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modificationImpl <em>modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modificationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getmodification()
   * @generated
   */
  int MODIFICATION = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION__EXPR = 0;

  /**
   * The number of structural features of the '<em>modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modificationImpl <em>class modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modificationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_modification()
   * @generated
   */
  int CLASS_MODIFICATION = 3;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__EXPR = MODIFICATION__EXPR;

  /**
   * The number of structural features of the '<em>class modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argument_listImpl <em>argument list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argument_listImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getargument_list()
   * @generated
   */
  int ARGUMENT_LIST = 4;

  /**
   * The number of structural features of the '<em>argument list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT_LIST_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argumentImpl <em>argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argumentImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getargument()
   * @generated
   */
  int ARGUMENT = 5;

  /**
   * The number of structural features of the '<em>argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT_FEATURE_COUNT = ARGUMENT_LIST_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modification_or_replaceableImpl <em>element modification or replaceable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modification_or_replaceableImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_modification_or_replaceable()
   * @generated
   */
  int ELEMENT_MODIFICATION_OR_REPLACEABLE = 6;

  /**
   * The feature id for the '<em><b>Element mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD = ARGUMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Element rep</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP = ARGUMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>element modification or replaceable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION_OR_REPLACEABLE_FEATURE_COUNT = ARGUMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modificationImpl <em>element modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modificationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_modification()
   * @generated
   */
  int ELEMENT_MODIFICATION = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION__NAME = 0;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION__MODIFICATION = 1;

  /**
   * The number of structural features of the '<em>element modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_replaceableImpl <em>element replaceable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_replaceableImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_replaceable()
   * @generated
   */
  int ELEMENT_REPLACEABLE = 8;

  /**
   * The feature id for the '<em><b>Class Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REPLACEABLE__CLASS_DEF = 0;

  /**
   * The feature id for the '<em><b>Comp clause2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REPLACEABLE__COMP_CLAUSE2 = 1;

  /**
   * The feature id for the '<em><b>Constrain Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE = 2;

  /**
   * The number of structural features of the '<em>element replaceable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REPLACEABLE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.constraining_clauseImpl <em>constraining clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.constraining_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getconstraining_clause()
   * @generated
   */
  int CONSTRAINING_CLAUSE = 9;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINING_CLAUSE__NAME = 0;

  /**
   * The feature id for the '<em><b>Class Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINING_CLAUSE__CLASS_MOD = 1;

  /**
   * The number of structural features of the '<em>constraining clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINING_CLAUSE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_redeclarationImpl <em>element redeclaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_redeclarationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_redeclaration()
   * @generated
   */
  int ELEMENT_REDECLARATION = 10;

  /**
   * The feature id for the '<em><b>Class Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REDECLARATION__CLASS_DEF = ARGUMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Comp clause1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REDECLARATION__COMP_CLAUSE1 = ARGUMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Element R</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REDECLARATION__ELEMENT_R = ARGUMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>element redeclaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REDECLARATION_FEATURE_COUNT = ARGUMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clauseImpl <em>component clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_clause()
   * @generated
   */
  int COMPONENT_CLAUSE = 11;

  /**
   * The number of structural features of the '<em>component clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_CLAUSE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_listImpl <em>component list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_listImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_list()
   * @generated
   */
  int COMPONENT_LIST = 12;

  /**
   * The number of structural features of the '<em>component list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_LIST_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declarationImpl <em>component declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declarationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_declaration()
   * @generated
   */
  int COMPONENT_DECLARATION = 13;

  /**
   * The feature id for the '<em><b>Component Dec</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__COMPONENT_DEC = COMPONENT_LIST_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Dec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__DEC = COMPONENT_LIST_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Conditional Att</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__CONDITIONAL_ATT = COMPONENT_LIST_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>component declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION_FEATURE_COUNT = COMPONENT_LIST_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.conditional_attributeImpl <em>conditional attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.conditional_attributeImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getconditional_attribute()
   * @generated
   */
  int CONDITIONAL_ATTRIBUTE = 14;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_ATTRIBUTE__EXPR = 0;

  /**
   * The number of structural features of the '<em>conditional attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_ATTRIBUTE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clause1Impl <em>component clause1</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clause1Impl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_clause1()
   * @generated
   */
  int COMPONENT_CLAUSE1 = 15;

  /**
   * The number of structural features of the '<em>component clause1</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_CLAUSE1_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declaration1Impl <em>component declaration1</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declaration1Impl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_declaration1()
   * @generated
   */
  int COMPONENT_DECLARATION1 = 16;

  /**
   * The number of structural features of the '<em>component declaration1</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION1_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl <em>type specifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#gettype_specifier()
   * @generated
   */
  int TYPE_SPECIFIER = 17;

  /**
   * The feature id for the '<em><b>Array Subs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER__ARRAY_SUBS = COMPONENT_CLAUSE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Com List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER__COM_LIST = COMPONENT_CLAUSE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Com Dec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER__COM_DEC = COMPONENT_CLAUSE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER__NAME = COMPONENT_CLAUSE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>type specifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER_FEATURE_COUNT = COMPONENT_CLAUSE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.declarationImpl <em>declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.declarationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getdeclaration()
   * @generated
   */
  int DECLARATION = 18;

  /**
   * The feature id for the '<em><b>Redeclared Component Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION__REDECLARED_COMPONENT_NAME = COMPONENT_DECLARATION1_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Array Subs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION__ARRAY_SUBS = COMPONENT_DECLARATION1_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION__MOD = COMPONENT_DECLARATION1_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECLARATION_FEATURE_COUNT = COMPONENT_DECLARATION1_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_definitionImpl <em>class definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_definitionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_definition()
   * @generated
   */
  int CLASS_DEFINITION = 19;

  /**
   * The number of structural features of the '<em>class definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl <em>class specifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_specifier()
   * @generated
   */
  int CLASS_SPECIFIER = 20;

  /**
   * The feature id for the '<em><b>Comp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_SPECIFIER__COMP = CLASS_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_SPECIFIER__NAME = CLASS_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Array Subs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_SPECIFIER__ARRAY_SUBS = CLASS_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Class mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_SPECIFIER__CLASS_MOD = CLASS_DEFINITION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_SPECIFIER__VAR = CLASS_DEFINITION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Enum list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_SPECIFIER__ENUM_LIST = CLASS_DEFINITION_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>class specifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_SPECIFIER_FEATURE_COUNT = CLASS_DEFINITION_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.enum_listImpl <em>enum list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.enum_listImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getenum_list()
   * @generated
   */
  int ENUM_LIST = 21;

  /**
   * The feature id for the '<em><b>Ennum Lit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIST__ENNUM_LIT = 0;

  /**
   * The feature id for the '<em><b>Eliteral</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIST__ELITERAL = 1;

  /**
   * The number of structural features of the '<em>enum list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl <em>composition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomposition()
   * @generated
   */
  int COMPOSITION = 22;

  /**
   * The feature id for the '<em><b>EList Initial</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__ELIST_INITIAL = 0;

  /**
   * The feature id for the '<em><b>EList Public</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__ELIST_PUBLIC = 1;

  /**
   * The feature id for the '<em><b>EList Protected</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__ELIST_PROTECTED = 2;

  /**
   * The feature id for the '<em><b>Eqn section</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__EQN_SECTION = 3;

  /**
   * The feature id for the '<em><b>Alg section</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__ALG_SECTION = 4;

  /**
   * The feature id for the '<em><b>Lang Spec</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__LANG_SPEC = 5;

  /**
   * The feature id for the '<em><b>External FC</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__EXTERNAL_FC = 6;

  /**
   * The feature id for the '<em><b>Annotation1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__ANNOTATION1 = 7;

  /**
   * The feature id for the '<em><b>Annotation2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION__ANNOTATION2 = 8;

  /**
   * The number of structural features of the '<em>composition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPOSITION_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_listImpl <em>element list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_listImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_list()
   * @generated
   */
  int ELEMENT_LIST = 23;

  /**
   * The feature id for the '<em><b>Element</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_LIST__ELEMENT = 0;

  /**
   * The number of structural features of the '<em>element list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl <em>element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement()
   * @generated
   */
  int ELEMENT = 24;

  /**
   * The feature id for the '<em><b>Class Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__CLASS_DEF = 0;

  /**
   * The feature id for the '<em><b>Comp Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__COMP_CLAUSE = 1;

  /**
   * The feature id for the '<em><b>Constrain Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__CONSTRAIN_CLAUSE = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__NAME = 3;

  /**
   * The number of structural features of the '<em>element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equation_sectionImpl <em>equation section</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equation_sectionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getequation_section()
   * @generated
   */
  int EQUATION_SECTION = 25;

  /**
   * The feature id for the '<em><b>Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_SECTION__EQN = 0;

  /**
   * The number of structural features of the '<em>equation section</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_SECTION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl <em>equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getequation()
   * @generated
   */
  int EQUATION = 26;

  /**
   * The feature id for the '<em><b>Sim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__SIM = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__EXPR = 1;

  /**
   * The feature id for the '<em><b>IF EQN</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__IF_EQN = 2;

  /**
   * The feature id for the '<em><b>FOR EQN</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__FOR_EQN = 3;

  /**
   * The feature id for the '<em><b>Con</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__CON = 4;

  /**
   * The feature id for the '<em><b>WHEN EQN</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__WHEN_EQN = 5;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__COMMENT = 6;

  /**
   * The number of structural features of the '<em>equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_equationImpl <em>when equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getwhen_equation()
   * @generated
   */
  int WHEN_EQUATION = 27;

  /**
   * The feature id for the '<em><b>When Epr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__WHEN_EPR = 0;

  /**
   * The feature id for the '<em><b>Eqn Then</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__EQN_THEN = 1;

  /**
   * The feature id for the '<em><b>Else When Epr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__ELSE_WHEN_EPR = 2;

  /**
   * The number of structural features of the '<em>when equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl <em>if equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getif_equation()
   * @generated
   */
  int IF_EQUATION = 28;

  /**
   * The feature id for the '<em><b>Exprtrue</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__EXPRTRUE = 0;

  /**
   * The feature id for the '<em><b>Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__EQN = 1;

  /**
   * The feature id for the '<em><b>Expr Stilltrue</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__EXPR_STILLTRUE = 2;

  /**
   * The feature id for the '<em><b>Then Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__THEN_EQN = 3;

  /**
   * The feature id for the '<em><b>Else Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__ELSE_EQN = 4;

  /**
   * The number of structural features of the '<em>if equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_equationImpl <em>for equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getfor_equation()
   * @generated
   */
  int FOR_EQUATION = 29;

  /**
   * The feature id for the '<em><b>For loop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION__FOR_LOOP = 0;

  /**
   * The feature id for the '<em><b>For Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION__FOR_EQN = 1;

  /**
   * The number of structural features of the '<em>for equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.connect_clauseImpl <em>connect clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.connect_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getconnect_clause()
   * @generated
   */
  int CONNECT_CLAUSE = 30;

  /**
   * The feature id for the '<em><b>Connector1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE__CONNECTOR1 = 0;

  /**
   * The feature id for the '<em><b>Connector2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE__CONNECTOR2 = 1;

  /**
   * The number of structural features of the '<em>connect clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_sectionImpl <em>algorithm section</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_sectionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getalgorithm_section()
   * @generated
   */
  int ALGORITHM_SECTION = 31;

  /**
   * The number of structural features of the '<em>algorithm section</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_SECTION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl <em>statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getstatement()
   * @generated
   */
  int STATEMENT = 32;

  /**
   * The feature id for the '<em><b>If statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__IF_STATEMENT = 0;

  /**
   * The feature id for the '<em><b>State Output Expr List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__STATE_OUTPUT_EXPR_LIST = 1;

  /**
   * The feature id for the '<em><b>State Comp Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__STATE_COMP_REF = 2;

  /**
   * The feature id for the '<em><b>For statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__FOR_STATEMENT = 3;

  /**
   * The feature id for the '<em><b>When statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__WHEN_STATEMENT = 4;

  /**
   * The feature id for the '<em><b>While statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__WHILE_STATEMENT = 5;

  /**
   * The number of structural features of the '<em>statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_OutputExprListImpl <em>state Output Expr List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_OutputExprListImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getstate_OutputExprList()
   * @generated
   */
  int STATE_OUTPUT_EXPR_LIST = 33;

  /**
   * The feature id for the '<em><b>Output expression list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST = 0;

  /**
   * The feature id for the '<em><b>Component ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_OUTPUT_EXPR_LIST__COMPONENT_REF = 1;

  /**
   * The feature id for the '<em><b>Fun call args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS = 2;

  /**
   * The number of structural features of the '<em>state Output Expr List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_OUTPUT_EXPR_LIST_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_Comp_RefImpl <em>state Comp Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_Comp_RefImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getstate_Comp_Ref()
   * @generated
   */
  int STATE_COMP_REF = 34;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_COMP_REF__COMPONENT_REFERENCE = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_COMP_REF__EXPR = 1;

  /**
   * The feature id for the '<em><b>Fun call args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_COMP_REF__FUN_CALL_ARGS = 2;

  /**
   * The number of structural features of the '<em>state Comp Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATE_COMP_REF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl <em>when statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getwhen_statement()
   * @generated
   */
  int WHEN_STATEMENT = 35;

  /**
   * The feature id for the '<em><b>When expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT__WHEN_EXPR = 0;

  /**
   * The feature id for the '<em><b>When stat true</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT__WHEN_STAT_TRUE = 1;

  /**
   * The feature id for the '<em><b>Else When expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT__ELSE_WHEN_EXPR = 2;

  /**
   * The feature id for the '<em><b>Then statement</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT__THEN_STATEMENT = 3;

  /**
   * The number of structural features of the '<em>when statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.while_statementImpl <em>while statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.while_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getwhile_statement()
   * @generated
   */
  int WHILE_STATEMENT = 36;

  /**
   * The feature id for the '<em><b>While expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_STATEMENT__WHILE_EXPR = 0;

  /**
   * The feature id for the '<em><b>While stat</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_STATEMENT__WHILE_STAT = 1;

  /**
   * The number of structural features of the '<em>while statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_STATEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_statementImpl <em>for statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getfor_statement()
   * @generated
   */
  int FOR_STATEMENT = 37;

  /**
   * The feature id for the '<em><b>For indices</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__FOR_INDICES = 0;

  /**
   * The feature id for the '<em><b>For statement</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__FOR_STATEMENT = 1;

  /**
   * The number of structural features of the '<em>for statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_statementImpl <em>if statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getif_statement()
   * @generated
   */
  int IF_STATEMENT = 38;

  /**
   * The feature id for the '<em><b>Exprtrue</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT__EXPRTRUE = 0;

  /**
   * The feature id for the '<em><b>State</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT__STATE = 1;

  /**
   * The feature id for the '<em><b>Expr Stilltrue</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT__EXPR_STILLTRUE = 2;

  /**
   * The number of structural features of the '<em>if statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.extends_clauseImpl <em>extends clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.extends_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getextends_clause()
   * @generated
   */
  int EXTENDS_CLAUSE = 39;

  /**
   * The feature id for the '<em><b>Class Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__CLASS_DEF = ELEMENT__CLASS_DEF;

  /**
   * The feature id for the '<em><b>Comp Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__COMP_CLAUSE = ELEMENT__COMP_CLAUSE;

  /**
   * The feature id for the '<em><b>Constrain Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__CONSTRAIN_CLAUSE = ELEMENT__CONSTRAIN_CLAUSE;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__NAME = ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Class Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__CLASS_MOD = ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__ANNOTATION = ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>extends clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.annotationImpl <em>annotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.annotationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getannotation()
   * @generated
   */
  int ANNOTATION = 40;

  /**
   * The feature id for the '<em><b>Class Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__CLASS_MOD = 0;

  /**
   * The number of structural features of the '<em>annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.import_clauseImpl <em>import clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.import_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getimport_clause()
   * @generated
   */
  int IMPORT_CLAUSE = 41;

  /**
   * The feature id for the '<em><b>Class Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE__CLASS_DEF = ELEMENT__CLASS_DEF;

  /**
   * The feature id for the '<em><b>Comp Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE__COMP_CLAUSE = ELEMENT__COMP_CLAUSE;

  /**
   * The feature id for the '<em><b>Constrain Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE__CONSTRAIN_CLAUSE = ELEMENT__CONSTRAIN_CLAUSE;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE__NAME = ELEMENT__NAME;

  /**
   * The number of structural features of the '<em>import clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.language_specificationImpl <em>language specification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.language_specificationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getlanguage_specification()
   * @generated
   */
  int LANGUAGE_SPECIFICATION = 42;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LANGUAGE_SPECIFICATION__STR = 0;

  /**
   * The number of structural features of the '<em>language specification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LANGUAGE_SPECIFICATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.external_function_callImpl <em>external function call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.external_function_callImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getexternal_function_call()
   * @generated
   */
  int EXTERNAL_FUNCTION_CALL = 43;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_FUNCTION_CALL__COMPONENT_REFERENCE = 0;

  /**
   * The feature id for the '<em><b>EList</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_FUNCTION_CALL__ELIST = 1;

  /**
   * The number of structural features of the '<em>external function call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_FUNCTION_CALL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modification_actionImpl <em>class modification action</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modification_actionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_modification_action()
   * @generated
   */
  int CLASS_MODIFICATION_ACTION = 44;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION_ACTION__EXPR = CLASS_MODIFICATION__EXPR;

  /**
   * The feature id for the '<em><b>Arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION_ACTION__ARG = CLASS_MODIFICATION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>class modification action</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION_ACTION_FEATURE_COUNT = CLASS_MODIFICATION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_clauseImpl <em>algorithm clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getalgorithm_clause()
   * @generated
   */
  int ALGORITHM_CLAUSE = 45;

  /**
   * The feature id for the '<em><b>Algorithm</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_CLAUSE__ALGORITHM = ALGORITHM_SECTION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>algorithm clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_CLAUSE_FEATURE_COUNT = ALGORITHM_SECTION_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives <em>modification alternatives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>modification alternatives</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives
   * @generated
   */
  EClass getmodification_alternatives();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives#getModification_Rule <em>Modification Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification Rule</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives#getModification_Rule()
   * @see #getmodification_alternatives()
   * @generated
   */
  EReference getmodification_alternatives_Modification_Rule();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference <em>left hand component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>left hand component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference
   * @generated
   */
  EClass getleft_hand_component_reference();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getExpr()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EReference getleft_hand_component_reference_Expr();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getRef()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EAttribute getleft_hand_component_reference_Ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getSubscripts1 <em>Subscripts1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Subscripts1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getSubscripts1()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EReference getleft_hand_component_reference_Subscripts1();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getRef1 <em>Ref1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Ref1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getRef1()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EAttribute getleft_hand_component_reference_Ref1();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getSubscripts <em>Subscripts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Subscripts</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference#getSubscripts()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EReference getleft_hand_component_reference_Subscripts();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification <em>modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>modification</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.modification
   * @generated
   */
  EClass getmodification();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.modification#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.modification#getExpr()
   * @see #getmodification()
   * @generated
   */
  EReference getmodification_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification <em>class modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>class modification</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification
   * @generated
   */
  EClass getclass_modification();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.argument_list <em>argument list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>argument list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.argument_list
   * @generated
   */
  EClass getargument_list();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.argument <em>argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>argument</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.argument
   * @generated
   */
  EClass getargument();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable <em>element modification or replaceable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>element modification or replaceable</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable
   * @generated
   */
  EClass getelement_modification_or_replaceable();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_mod <em>Element mod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Element mod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_mod()
   * @see #getelement_modification_or_replaceable()
   * @generated
   */
  EReference getelement_modification_or_replaceable_Element_mod();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_rep <em>Element rep</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Element rep</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable#getElement_rep()
   * @see #getelement_modification_or_replaceable()
   * @generated
   */
  EReference getelement_modification_or_replaceable_Element_rep();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification <em>element modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>element modification</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification
   * @generated
   */
  EClass getelement_modification();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification#getName()
   * @see #getelement_modification()
   * @generated
   */
  EReference getelement_modification_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification#getModification()
   * @see #getelement_modification()
   * @generated
   */
  EReference getelement_modification_Modification();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable <em>element replaceable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>element replaceable</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable
   * @generated
   */
  EClass getelement_replaceable();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable#getClass_Def <em>Class Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Class Def</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable#getClass_Def()
   * @see #getelement_replaceable()
   * @generated
   */
  EReference getelement_replaceable_Class_Def();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable#getComp_clause2 <em>Comp clause2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comp clause2</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable#getComp_clause2()
   * @see #getelement_replaceable()
   * @generated
   */
  EReference getelement_replaceable_Comp_clause2();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable#getConstrain_Clause <em>Constrain Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constrain Clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable#getConstrain_Clause()
   * @see #getelement_replaceable()
   * @generated
   */
  EReference getelement_replaceable_Constrain_Clause();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause <em>constraining clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>constraining clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause
   * @generated
   */
  EClass getconstraining_clause();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause#getName()
   * @see #getconstraining_clause()
   * @generated
   */
  EReference getconstraining_clause_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause#getClass_Mod <em>Class Mod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Class Mod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause#getClass_Mod()
   * @see #getconstraining_clause()
   * @generated
   */
  EReference getconstraining_clause_Class_Mod();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration <em>element redeclaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>element redeclaration</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration
   * @generated
   */
  EClass getelement_redeclaration();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getClass_Def <em>Class Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Class Def</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getClass_Def()
   * @see #getelement_redeclaration()
   * @generated
   */
  EReference getelement_redeclaration_Class_Def();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getComp_clause1 <em>Comp clause1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comp clause1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getComp_clause1()
   * @see #getelement_redeclaration()
   * @generated
   */
  EReference getelement_redeclaration_Comp_clause1();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getElement_R <em>Element R</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Element R</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration#getElement_R()
   * @see #getelement_redeclaration()
   * @generated
   */
  EReference getelement_redeclaration_Element_R();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause <em>component clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause
   * @generated
   */
  EClass getcomponent_clause();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_list <em>component list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_list
   * @generated
   */
  EClass getcomponent_list();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration <em>component declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component declaration</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration
   * @generated
   */
  EClass getcomponent_declaration();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getComponent_Dec <em>Component Dec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Component Dec</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getComponent_Dec()
   * @see #getcomponent_declaration()
   * @generated
   */
  EReference getcomponent_declaration_Component_Dec();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getDec <em>Dec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dec</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getDec()
   * @see #getcomponent_declaration()
   * @generated
   */
  EReference getcomponent_declaration_Dec();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getConditional_Att <em>Conditional Att</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Conditional Att</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration#getConditional_Att()
   * @see #getcomponent_declaration()
   * @generated
   */
  EReference getcomponent_declaration_Conditional_Att();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute <em>conditional attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>conditional attribute</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute
   * @generated
   */
  EClass getconditional_attribute();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute#getExpr()
   * @see #getconditional_attribute()
   * @generated
   */
  EReference getconditional_attribute_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1 <em>component clause1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component clause1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1
   * @generated
   */
  EClass getcomponent_clause1();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration1 <em>component declaration1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component declaration1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration1
   * @generated
   */
  EClass getcomponent_declaration1();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier <em>type specifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>type specifier</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier
   * @generated
   */
  EClass gettype_specifier();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getArray_Subs <em>Array Subs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array Subs</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getArray_Subs()
   * @see #gettype_specifier()
   * @generated
   */
  EReference gettype_specifier_Array_Subs();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_List <em>Com List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Com List</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_List()
   * @see #gettype_specifier()
   * @generated
   */
  EReference gettype_specifier_Com_List();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_Dec <em>Com Dec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Com Dec</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getCom_Dec()
   * @see #gettype_specifier()
   * @generated
   */
  EReference gettype_specifier_Com_Dec();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier#getName()
   * @see #gettype_specifier()
   * @generated
   */
  EReference gettype_specifier_Name();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration <em>declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>declaration</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration
   * @generated
   */
  EClass getdeclaration();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getRedeclaredComponentName <em>Redeclared Component Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Redeclared Component Name</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getRedeclaredComponentName()
   * @see #getdeclaration()
   * @generated
   */
  EAttribute getdeclaration_RedeclaredComponentName();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getArraySubs <em>Array Subs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array Subs</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getArraySubs()
   * @see #getdeclaration()
   * @generated
   */
  EReference getdeclaration_ArraySubs();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getMod <em>Mod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration#getMod()
   * @see #getdeclaration()
   * @generated
   */
  EReference getdeclaration_Mod();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition <em>class definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>class definition</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition
   * @generated
   */
  EClass getclass_definition();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier <em>class specifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>class specifier</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier
   * @generated
   */
  EClass getclass_specifier();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getComp <em>Comp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comp</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getComp()
   * @see #getclass_specifier()
   * @generated
   */
  EReference getclass_specifier_Comp();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getName()
   * @see #getclass_specifier()
   * @generated
   */
  EReference getclass_specifier_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getArray_Subs <em>Array Subs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Array Subs</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getArray_Subs()
   * @see #getclass_specifier()
   * @generated
   */
  EReference getclass_specifier_Array_Subs();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getClass_mod <em>Class mod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Class mod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getClass_mod()
   * @see #getclass_specifier()
   * @generated
   */
  EReference getclass_specifier_Class_mod();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Var</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getVar()
   * @see #getclass_specifier()
   * @generated
   */
  EAttribute getclass_specifier_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getEnum_list <em>Enum list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Enum list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier#getEnum_list()
   * @see #getclass_specifier()
   * @generated
   */
  EReference getclass_specifier_Enum_list();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list <em>enum list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>enum list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list
   * @generated
   */
  EClass getenum_list();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list#getEnnum_Lit <em>Ennum Lit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ennum Lit</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list#getEnnum_Lit()
   * @see #getenum_list()
   * @generated
   */
  EAttribute getenum_list_Ennum_Lit();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list#getE_literal <em>Eliteral</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Eliteral</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list#getE_literal()
   * @see #getenum_list()
   * @generated
   */
  EAttribute getenum_list_E_literal();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition <em>composition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>composition</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition
   * @generated
   */
  EClass getcomposition();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Initial <em>EList Initial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>EList Initial</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Initial()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_E_List_Initial();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Public <em>EList Public</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>EList Public</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Public()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_E_List_Public();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Protected <em>EList Protected</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>EList Protected</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getE_List_Protected()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_E_List_Protected();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getEqn_section <em>Eqn section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Eqn section</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getEqn_section()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_Eqn_section();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAlg_section <em>Alg section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Alg section</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAlg_section()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_Alg_section();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getLang_Spec <em>Lang Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lang Spec</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getLang_Spec()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_Lang_Spec();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getExternal_F_C <em>External FC</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>External FC</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getExternal_F_C()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_External_F_C();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation1 <em>Annotation1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Annotation1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation1()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_Annotation1();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation2 <em>Annotation2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Annotation2</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.composition#getAnnotation2()
   * @see #getcomposition()
   * @generated
   */
  EReference getcomposition_Annotation2();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list <em>element list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>element list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list
   * @generated
   */
  EClass getelement_list();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Element</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list#getElement()
   * @see #getelement_list()
   * @generated
   */
  EReference getelement_list_Element();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element <em>element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>element</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element
   * @generated
   */
  EClass getelement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getClass_Def <em>Class Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Class Def</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getClass_Def()
   * @see #getelement()
   * @generated
   */
  EReference getelement_Class_Def();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getComp_Clause <em>Comp Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comp Clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getComp_Clause()
   * @see #getelement()
   * @generated
   */
  EReference getelement_Comp_Clause();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getConstrain_Clause <em>Constrain Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constrain Clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getConstrain_Clause()
   * @see #getelement()
   * @generated
   */
  EReference getelement_Constrain_Clause();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.element#getName()
   * @see #getelement()
   * @generated
   */
  EReference getelement_Name();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section <em>equation section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>equation section</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section
   * @generated
   */
  EClass getequation_section();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section#getEqn <em>Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section#getEqn()
   * @see #getequation_section()
   * @generated
   */
  EReference getequation_section_Eqn();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation <em>equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation
   * @generated
   */
  EClass getequation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getSim <em>Sim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sim</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getSim()
   * @see #getequation()
   * @generated
   */
  EReference getequation_Sim();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getExpr()
   * @see #getequation()
   * @generated
   */
  EReference getequation_Expr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getIF_EQN <em>IF EQN</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>IF EQN</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getIF_EQN()
   * @see #getequation()
   * @generated
   */
  EReference getequation_IF_EQN();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getFOR_EQN <em>FOR EQN</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>FOR EQN</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getFOR_EQN()
   * @see #getequation()
   * @generated
   */
  EReference getequation_FOR_EQN();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getCon <em>Con</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Con</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getCon()
   * @see #getequation()
   * @generated
   */
  EReference getequation_Con();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getWHEN_EQN <em>WHEN EQN</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>WHEN EQN</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getWHEN_EQN()
   * @see #getequation()
   * @generated
   */
  EReference getequation_WHEN_EQN();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.equation#getComment()
   * @see #getequation()
   * @generated
   */
  EAttribute getequation_Comment();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation <em>when equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>when equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation
   * @generated
   */
  EClass getwhen_equation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation#getWhenEpr <em>When Epr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>When Epr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation#getWhenEpr()
   * @see #getwhen_equation()
   * @generated
   */
  EReference getwhen_equation_WhenEpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation#getEqn_Then <em>Eqn Then</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Eqn Then</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation#getEqn_Then()
   * @see #getwhen_equation()
   * @generated
   */
  EReference getwhen_equation_Eqn_Then();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation#getElseWhenEpr <em>Else When Epr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else When Epr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation#getElseWhenEpr()
   * @see #getwhen_equation()
   * @generated
   */
  EReference getwhen_equation_ElseWhenEpr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation <em>if equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>if equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation
   * @generated
   */
  EClass getif_equation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getExprtrue <em>Exprtrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Exprtrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getExprtrue()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_Exprtrue();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getEqn <em>Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getEqn()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_Eqn();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getExprStilltrue <em>Expr Stilltrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr Stilltrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getExprStilltrue()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_ExprStilltrue();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getThenEqn <em>Then Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getThenEqn()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_ThenEqn();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getElseEqn <em>Else Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation#getElseEqn()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_ElseEqn();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation <em>for equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>for equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation
   * @generated
   */
  EClass getfor_equation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation#getFor_loop <em>For loop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For loop</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation#getFor_loop()
   * @see #getfor_equation()
   * @generated
   */
  EReference getfor_equation_For_loop();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation#getFor_Eqn <em>For Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>For Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation#getFor_Eqn()
   * @see #getfor_equation()
   * @generated
   */
  EReference getfor_equation_For_Eqn();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause <em>connect clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>connect clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause
   * @generated
   */
  EClass getconnect_clause();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause#getConnector1 <em>Connector1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Connector1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause#getConnector1()
   * @see #getconnect_clause()
   * @generated
   */
  EReference getconnect_clause_Connector1();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause#getConnector2 <em>Connector2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Connector2</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause#getConnector2()
   * @see #getconnect_clause()
   * @generated
   */
  EReference getconnect_clause_Connector2();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section <em>algorithm section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>algorithm section</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section
   * @generated
   */
  EClass getalgorithm_section();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement <em>statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement
   * @generated
   */
  EClass getstatement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getIf_statement <em>If statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>If statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getIf_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_If_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_OutputExprList <em>State Output Expr List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>State Output Expr List</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_OutputExprList()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_State_OutputExprList();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_Comp_Ref <em>State Comp Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>State Comp Ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getState_Comp_Ref()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_State_Comp_Ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getFor_statement <em>For statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getFor_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_For_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhen_statement <em>When statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>When statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhen_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_When_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhile_statement <em>While statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>While statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.statement#getWhile_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_While_statement();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList <em>state Output Expr List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>state Output Expr List</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList
   * @generated
   */
  EClass getstate_OutputExprList();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList#getOutput_expression_list <em>Output expression list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output expression list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList#getOutput_expression_list()
   * @see #getstate_OutputExprList()
   * @generated
   */
  EReference getstate_OutputExprList_Output_expression_list();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList#getComponent_ref <em>Component ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList#getComponent_ref()
   * @see #getstate_OutputExprList()
   * @generated
   */
  EReference getstate_OutputExprList_Component_ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList#getFun_call_args <em>Fun call args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fun call args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList#getFun_call_args()
   * @see #getstate_OutputExprList()
   * @generated
   */
  EReference getstate_OutputExprList_Fun_call_args();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref <em>state Comp Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>state Comp Ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref
   * @generated
   */
  EClass getstate_Comp_Ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref#getComponent_reference()
   * @see #getstate_Comp_Ref()
   * @generated
   */
  EReference getstate_Comp_Ref_Component_reference();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref#getExpr()
   * @see #getstate_Comp_Ref()
   * @generated
   */
  EReference getstate_Comp_Ref_Expr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref#getFun_call_args <em>Fun call args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fun call args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref#getFun_call_args()
   * @see #getstate_Comp_Ref()
   * @generated
   */
  EReference getstate_Comp_Ref_Fun_call_args();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement <em>when statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>when statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement
   * @generated
   */
  EClass getwhen_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getWhen_expr <em>When expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>When expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getWhen_expr()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_When_expr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getWhen_stat_true <em>When stat true</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>When stat true</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getWhen_stat_true()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_When_stat_true();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getElse_When_expr <em>Else When expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else When expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getElse_When_expr()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_Else_When_expr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getThen_statement <em>Then statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement#getThen_statement()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_Then_statement();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement <em>while statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>while statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement
   * @generated
   */
  EClass getwhile_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement#getWhile_expr <em>While expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>While expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement#getWhile_expr()
   * @see #getwhile_statement()
   * @generated
   */
  EReference getwhile_statement_While_expr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement#getWhile_stat <em>While stat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>While stat</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement#getWhile_stat()
   * @see #getwhile_statement()
   * @generated
   */
  EReference getwhile_statement_While_stat();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement <em>for statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>for statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement
   * @generated
   */
  EClass getfor_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_indices <em>For indices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For indices</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_indices()
   * @see #getfor_statement()
   * @generated
   */
  EReference getfor_statement_For_indices();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_statement <em>For statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>For statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement#getFor_statement()
   * @see #getfor_statement()
   * @generated
   */
  EReference getfor_statement_For_statement();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement <em>if statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>if statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement
   * @generated
   */
  EClass getif_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getExprtrue <em>Exprtrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Exprtrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getExprtrue()
   * @see #getif_statement()
   * @generated
   */
  EReference getif_statement_Exprtrue();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getState <em>State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>State</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getState()
   * @see #getif_statement()
   * @generated
   */
  EReference getif_statement_State();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getExprStilltrue <em>Expr Stilltrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr Stilltrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement#getExprStilltrue()
   * @see #getif_statement()
   * @generated
   */
  EReference getif_statement_ExprStilltrue();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause <em>extends clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>extends clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause
   * @generated
   */
  EClass getextends_clause();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getClass_Mod <em>Class Mod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Class Mod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getClass_Mod()
   * @see #getextends_clause()
   * @generated
   */
  EReference getextends_clause_Class_Mod();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getAnnotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Annotation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause#getAnnotation()
   * @see #getextends_clause()
   * @generated
   */
  EReference getextends_clause_Annotation();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation <em>annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>annotation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation
   * @generated
   */
  EClass getannotation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation#getClass_Mod <em>Class Mod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Class Mod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation#getClass_Mod()
   * @see #getannotation()
   * @generated
   */
  EReference getannotation_Class_Mod();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.import_clause <em>import clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>import clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.import_clause
   * @generated
   */
  EClass getimport_clause();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification <em>language specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>language specification</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification
   * @generated
   */
  EClass getlanguage_specification();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification#getStr()
   * @see #getlanguage_specification()
   * @generated
   */
  EAttribute getlanguage_specification_Str();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call <em>external function call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>external function call</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call
   * @generated
   */
  EClass getexternal_function_call();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getComponent_reference()
   * @see #getexternal_function_call()
   * @generated
   */
  EReference getexternal_function_call_Component_reference();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getE_List <em>EList</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>EList</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call#getE_List()
   * @see #getexternal_function_call()
   * @generated
   */
  EReference getexternal_function_call_E_List();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action <em>class modification action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>class modification action</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action
   * @generated
   */
  EClass getclass_modification_action();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action#getArg <em>Arg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arg</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action#getArg()
   * @see #getclass_modification_action()
   * @generated
   */
  EReference getclass_modification_action_Arg();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause <em>algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>algorithm clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause
   * @generated
   */
  EClass getalgorithm_clause();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause#getAlgorithm <em>Algorithm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Algorithm</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause#getAlgorithm()
   * @see #getalgorithm_clause()
   * @generated
   */
  EReference getalgorithm_clause_Algorithm();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ModificationFactory getModificationFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modification_alternativesImpl <em>modification alternatives</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modification_alternativesImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getmodification_alternatives()
     * @generated
     */
    EClass MODIFICATION_ALTERNATIVES = eINSTANCE.getmodification_alternatives();

    /**
     * The meta object literal for the '<em><b>Modification Rule</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODIFICATION_ALTERNATIVES__MODIFICATION_RULE = eINSTANCE.getmodification_alternatives_Modification_Rule();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl <em>left hand component reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getleft_hand_component_reference()
     * @generated
     */
    EClass LEFT_HAND_COMPONENT_REFERENCE = eINSTANCE.getleft_hand_component_reference();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEFT_HAND_COMPONENT_REFERENCE__EXPR = eINSTANCE.getleft_hand_component_reference_Expr();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEFT_HAND_COMPONENT_REFERENCE__REF = eINSTANCE.getleft_hand_component_reference_Ref();

    /**
     * The meta object literal for the '<em><b>Subscripts1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1 = eINSTANCE.getleft_hand_component_reference_Subscripts1();

    /**
     * The meta object literal for the '<em><b>Ref1</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEFT_HAND_COMPONENT_REFERENCE__REF1 = eINSTANCE.getleft_hand_component_reference_Ref1();

    /**
     * The meta object literal for the '<em><b>Subscripts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS = eINSTANCE.getleft_hand_component_reference_Subscripts();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modificationImpl <em>modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modificationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getmodification()
     * @generated
     */
    EClass MODIFICATION = eINSTANCE.getmodification();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODIFICATION__EXPR = eINSTANCE.getmodification_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modificationImpl <em>class modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modificationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_modification()
     * @generated
     */
    EClass CLASS_MODIFICATION = eINSTANCE.getclass_modification();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argument_listImpl <em>argument list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argument_listImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getargument_list()
     * @generated
     */
    EClass ARGUMENT_LIST = eINSTANCE.getargument_list();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argumentImpl <em>argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.argumentImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getargument()
     * @generated
     */
    EClass ARGUMENT = eINSTANCE.getargument();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modification_or_replaceableImpl <em>element modification or replaceable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modification_or_replaceableImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_modification_or_replaceable()
     * @generated
     */
    EClass ELEMENT_MODIFICATION_OR_REPLACEABLE = eINSTANCE.getelement_modification_or_replaceable();

    /**
     * The meta object literal for the '<em><b>Element mod</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD = eINSTANCE.getelement_modification_or_replaceable_Element_mod();

    /**
     * The meta object literal for the '<em><b>Element rep</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP = eINSTANCE.getelement_modification_or_replaceable_Element_rep();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modificationImpl <em>element modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modificationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_modification()
     * @generated
     */
    EClass ELEMENT_MODIFICATION = eINSTANCE.getelement_modification();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_MODIFICATION__NAME = eINSTANCE.getelement_modification_Name();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_MODIFICATION__MODIFICATION = eINSTANCE.getelement_modification_Modification();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_replaceableImpl <em>element replaceable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_replaceableImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_replaceable()
     * @generated
     */
    EClass ELEMENT_REPLACEABLE = eINSTANCE.getelement_replaceable();

    /**
     * The meta object literal for the '<em><b>Class Def</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REPLACEABLE__CLASS_DEF = eINSTANCE.getelement_replaceable_Class_Def();

    /**
     * The meta object literal for the '<em><b>Comp clause2</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REPLACEABLE__COMP_CLAUSE2 = eINSTANCE.getelement_replaceable_Comp_clause2();

    /**
     * The meta object literal for the '<em><b>Constrain Clause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE = eINSTANCE.getelement_replaceable_Constrain_Clause();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.constraining_clauseImpl <em>constraining clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.constraining_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getconstraining_clause()
     * @generated
     */
    EClass CONSTRAINING_CLAUSE = eINSTANCE.getconstraining_clause();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINING_CLAUSE__NAME = eINSTANCE.getconstraining_clause_Name();

    /**
     * The meta object literal for the '<em><b>Class Mod</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINING_CLAUSE__CLASS_MOD = eINSTANCE.getconstraining_clause_Class_Mod();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_redeclarationImpl <em>element redeclaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_redeclarationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_redeclaration()
     * @generated
     */
    EClass ELEMENT_REDECLARATION = eINSTANCE.getelement_redeclaration();

    /**
     * The meta object literal for the '<em><b>Class Def</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REDECLARATION__CLASS_DEF = eINSTANCE.getelement_redeclaration_Class_Def();

    /**
     * The meta object literal for the '<em><b>Comp clause1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REDECLARATION__COMP_CLAUSE1 = eINSTANCE.getelement_redeclaration_Comp_clause1();

    /**
     * The meta object literal for the '<em><b>Element R</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REDECLARATION__ELEMENT_R = eINSTANCE.getelement_redeclaration_Element_R();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clauseImpl <em>component clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_clause()
     * @generated
     */
    EClass COMPONENT_CLAUSE = eINSTANCE.getcomponent_clause();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_listImpl <em>component list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_listImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_list()
     * @generated
     */
    EClass COMPONENT_LIST = eINSTANCE.getcomponent_list();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declarationImpl <em>component declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declarationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_declaration()
     * @generated
     */
    EClass COMPONENT_DECLARATION = eINSTANCE.getcomponent_declaration();

    /**
     * The meta object literal for the '<em><b>Component Dec</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_DECLARATION__COMPONENT_DEC = eINSTANCE.getcomponent_declaration_Component_Dec();

    /**
     * The meta object literal for the '<em><b>Dec</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_DECLARATION__DEC = eINSTANCE.getcomponent_declaration_Dec();

    /**
     * The meta object literal for the '<em><b>Conditional Att</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_DECLARATION__CONDITIONAL_ATT = eINSTANCE.getcomponent_declaration_Conditional_Att();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.conditional_attributeImpl <em>conditional attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.conditional_attributeImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getconditional_attribute()
     * @generated
     */
    EClass CONDITIONAL_ATTRIBUTE = eINSTANCE.getconditional_attribute();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_ATTRIBUTE__EXPR = eINSTANCE.getconditional_attribute_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clause1Impl <em>component clause1</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_clause1Impl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_clause1()
     * @generated
     */
    EClass COMPONENT_CLAUSE1 = eINSTANCE.getcomponent_clause1();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declaration1Impl <em>component declaration1</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declaration1Impl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomponent_declaration1()
     * @generated
     */
    EClass COMPONENT_DECLARATION1 = eINSTANCE.getcomponent_declaration1();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl <em>type specifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#gettype_specifier()
     * @generated
     */
    EClass TYPE_SPECIFIER = eINSTANCE.gettype_specifier();

    /**
     * The meta object literal for the '<em><b>Array Subs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_SPECIFIER__ARRAY_SUBS = eINSTANCE.gettype_specifier_Array_Subs();

    /**
     * The meta object literal for the '<em><b>Com List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_SPECIFIER__COM_LIST = eINSTANCE.gettype_specifier_Com_List();

    /**
     * The meta object literal for the '<em><b>Com Dec</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_SPECIFIER__COM_DEC = eINSTANCE.gettype_specifier_Com_Dec();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_SPECIFIER__NAME = eINSTANCE.gettype_specifier_Name();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.declarationImpl <em>declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.declarationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getdeclaration()
     * @generated
     */
    EClass DECLARATION = eINSTANCE.getdeclaration();

    /**
     * The meta object literal for the '<em><b>Redeclared Component Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DECLARATION__REDECLARED_COMPONENT_NAME = eINSTANCE.getdeclaration_RedeclaredComponentName();

    /**
     * The meta object literal for the '<em><b>Array Subs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECLARATION__ARRAY_SUBS = eINSTANCE.getdeclaration_ArraySubs();

    /**
     * The meta object literal for the '<em><b>Mod</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DECLARATION__MOD = eINSTANCE.getdeclaration_Mod();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_definitionImpl <em>class definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_definitionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_definition()
     * @generated
     */
    EClass CLASS_DEFINITION = eINSTANCE.getclass_definition();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl <em>class specifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_specifier()
     * @generated
     */
    EClass CLASS_SPECIFIER = eINSTANCE.getclass_specifier();

    /**
     * The meta object literal for the '<em><b>Comp</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_SPECIFIER__COMP = eINSTANCE.getclass_specifier_Comp();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_SPECIFIER__NAME = eINSTANCE.getclass_specifier_Name();

    /**
     * The meta object literal for the '<em><b>Array Subs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_SPECIFIER__ARRAY_SUBS = eINSTANCE.getclass_specifier_Array_Subs();

    /**
     * The meta object literal for the '<em><b>Class mod</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_SPECIFIER__CLASS_MOD = eINSTANCE.getclass_specifier_Class_mod();

    /**
     * The meta object literal for the '<em><b>Var</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_SPECIFIER__VAR = eINSTANCE.getclass_specifier_Var();

    /**
     * The meta object literal for the '<em><b>Enum list</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_SPECIFIER__ENUM_LIST = eINSTANCE.getclass_specifier_Enum_list();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.enum_listImpl <em>enum list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.enum_listImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getenum_list()
     * @generated
     */
    EClass ENUM_LIST = eINSTANCE.getenum_list();

    /**
     * The meta object literal for the '<em><b>Ennum Lit</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_LIST__ENNUM_LIT = eINSTANCE.getenum_list_Ennum_Lit();

    /**
     * The meta object literal for the '<em><b>Eliteral</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_LIST__ELITERAL = eINSTANCE.getenum_list_E_literal();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl <em>composition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getcomposition()
     * @generated
     */
    EClass COMPOSITION = eINSTANCE.getcomposition();

    /**
     * The meta object literal for the '<em><b>EList Initial</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__ELIST_INITIAL = eINSTANCE.getcomposition_E_List_Initial();

    /**
     * The meta object literal for the '<em><b>EList Public</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__ELIST_PUBLIC = eINSTANCE.getcomposition_E_List_Public();

    /**
     * The meta object literal for the '<em><b>EList Protected</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__ELIST_PROTECTED = eINSTANCE.getcomposition_E_List_Protected();

    /**
     * The meta object literal for the '<em><b>Eqn section</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__EQN_SECTION = eINSTANCE.getcomposition_Eqn_section();

    /**
     * The meta object literal for the '<em><b>Alg section</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__ALG_SECTION = eINSTANCE.getcomposition_Alg_section();

    /**
     * The meta object literal for the '<em><b>Lang Spec</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__LANG_SPEC = eINSTANCE.getcomposition_Lang_Spec();

    /**
     * The meta object literal for the '<em><b>External FC</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__EXTERNAL_FC = eINSTANCE.getcomposition_External_F_C();

    /**
     * The meta object literal for the '<em><b>Annotation1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__ANNOTATION1 = eINSTANCE.getcomposition_Annotation1();

    /**
     * The meta object literal for the '<em><b>Annotation2</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPOSITION__ANNOTATION2 = eINSTANCE.getcomposition_Annotation2();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_listImpl <em>element list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_listImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement_list()
     * @generated
     */
    EClass ELEMENT_LIST = eINSTANCE.getelement_list();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_LIST__ELEMENT = eINSTANCE.getelement_list_Element();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl <em>element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getelement()
     * @generated
     */
    EClass ELEMENT = eINSTANCE.getelement();

    /**
     * The meta object literal for the '<em><b>Class Def</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT__CLASS_DEF = eINSTANCE.getelement_Class_Def();

    /**
     * The meta object literal for the '<em><b>Comp Clause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT__COMP_CLAUSE = eINSTANCE.getelement_Comp_Clause();

    /**
     * The meta object literal for the '<em><b>Constrain Clause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT__CONSTRAIN_CLAUSE = eINSTANCE.getelement_Constrain_Clause();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT__NAME = eINSTANCE.getelement_Name();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equation_sectionImpl <em>equation section</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equation_sectionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getequation_section()
     * @generated
     */
    EClass EQUATION_SECTION = eINSTANCE.getequation_section();

    /**
     * The meta object literal for the '<em><b>Eqn</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION_SECTION__EQN = eINSTANCE.getequation_section_Eqn();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl <em>equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getequation()
     * @generated
     */
    EClass EQUATION = eINSTANCE.getequation();

    /**
     * The meta object literal for the '<em><b>Sim</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION__SIM = eINSTANCE.getequation_Sim();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION__EXPR = eINSTANCE.getequation_Expr();

    /**
     * The meta object literal for the '<em><b>IF EQN</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION__IF_EQN = eINSTANCE.getequation_IF_EQN();

    /**
     * The meta object literal for the '<em><b>FOR EQN</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION__FOR_EQN = eINSTANCE.getequation_FOR_EQN();

    /**
     * The meta object literal for the '<em><b>Con</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION__CON = eINSTANCE.getequation_Con();

    /**
     * The meta object literal for the '<em><b>WHEN EQN</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION__WHEN_EQN = eINSTANCE.getequation_WHEN_EQN();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EQUATION__COMMENT = eINSTANCE.getequation_Comment();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_equationImpl <em>when equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getwhen_equation()
     * @generated
     */
    EClass WHEN_EQUATION = eINSTANCE.getwhen_equation();

    /**
     * The meta object literal for the '<em><b>When Epr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_EQUATION__WHEN_EPR = eINSTANCE.getwhen_equation_WhenEpr();

    /**
     * The meta object literal for the '<em><b>Eqn Then</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_EQUATION__EQN_THEN = eINSTANCE.getwhen_equation_Eqn_Then();

    /**
     * The meta object literal for the '<em><b>Else When Epr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_EQUATION__ELSE_WHEN_EPR = eINSTANCE.getwhen_equation_ElseWhenEpr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl <em>if equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getif_equation()
     * @generated
     */
    EClass IF_EQUATION = eINSTANCE.getif_equation();

    /**
     * The meta object literal for the '<em><b>Exprtrue</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EQUATION__EXPRTRUE = eINSTANCE.getif_equation_Exprtrue();

    /**
     * The meta object literal for the '<em><b>Eqn</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EQUATION__EQN = eINSTANCE.getif_equation_Eqn();

    /**
     * The meta object literal for the '<em><b>Expr Stilltrue</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EQUATION__EXPR_STILLTRUE = eINSTANCE.getif_equation_ExprStilltrue();

    /**
     * The meta object literal for the '<em><b>Then Eqn</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EQUATION__THEN_EQN = eINSTANCE.getif_equation_ThenEqn();

    /**
     * The meta object literal for the '<em><b>Else Eqn</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EQUATION__ELSE_EQN = eINSTANCE.getif_equation_ElseEqn();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_equationImpl <em>for equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getfor_equation()
     * @generated
     */
    EClass FOR_EQUATION = eINSTANCE.getfor_equation();

    /**
     * The meta object literal for the '<em><b>For loop</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_EQUATION__FOR_LOOP = eINSTANCE.getfor_equation_For_loop();

    /**
     * The meta object literal for the '<em><b>For Eqn</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_EQUATION__FOR_EQN = eINSTANCE.getfor_equation_For_Eqn();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.connect_clauseImpl <em>connect clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.connect_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getconnect_clause()
     * @generated
     */
    EClass CONNECT_CLAUSE = eINSTANCE.getconnect_clause();

    /**
     * The meta object literal for the '<em><b>Connector1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONNECT_CLAUSE__CONNECTOR1 = eINSTANCE.getconnect_clause_Connector1();

    /**
     * The meta object literal for the '<em><b>Connector2</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONNECT_CLAUSE__CONNECTOR2 = eINSTANCE.getconnect_clause_Connector2();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_sectionImpl <em>algorithm section</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_sectionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getalgorithm_section()
     * @generated
     */
    EClass ALGORITHM_SECTION = eINSTANCE.getalgorithm_section();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl <em>statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getstatement()
     * @generated
     */
    EClass STATEMENT = eINSTANCE.getstatement();

    /**
     * The meta object literal for the '<em><b>If statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__IF_STATEMENT = eINSTANCE.getstatement_If_statement();

    /**
     * The meta object literal for the '<em><b>State Output Expr List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__STATE_OUTPUT_EXPR_LIST = eINSTANCE.getstatement_State_OutputExprList();

    /**
     * The meta object literal for the '<em><b>State Comp Ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__STATE_COMP_REF = eINSTANCE.getstatement_State_Comp_Ref();

    /**
     * The meta object literal for the '<em><b>For statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__FOR_STATEMENT = eINSTANCE.getstatement_For_statement();

    /**
     * The meta object literal for the '<em><b>When statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__WHEN_STATEMENT = eINSTANCE.getstatement_When_statement();

    /**
     * The meta object literal for the '<em><b>While statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__WHILE_STATEMENT = eINSTANCE.getstatement_While_statement();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_OutputExprListImpl <em>state Output Expr List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_OutputExprListImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getstate_OutputExprList()
     * @generated
     */
    EClass STATE_OUTPUT_EXPR_LIST = eINSTANCE.getstate_OutputExprList();

    /**
     * The meta object literal for the '<em><b>Output expression list</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST = eINSTANCE.getstate_OutputExprList_Output_expression_list();

    /**
     * The meta object literal for the '<em><b>Component ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATE_OUTPUT_EXPR_LIST__COMPONENT_REF = eINSTANCE.getstate_OutputExprList_Component_ref();

    /**
     * The meta object literal for the '<em><b>Fun call args</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS = eINSTANCE.getstate_OutputExprList_Fun_call_args();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_Comp_RefImpl <em>state Comp Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.state_Comp_RefImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getstate_Comp_Ref()
     * @generated
     */
    EClass STATE_COMP_REF = eINSTANCE.getstate_Comp_Ref();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATE_COMP_REF__COMPONENT_REFERENCE = eINSTANCE.getstate_Comp_Ref_Component_reference();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATE_COMP_REF__EXPR = eINSTANCE.getstate_Comp_Ref_Expr();

    /**
     * The meta object literal for the '<em><b>Fun call args</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATE_COMP_REF__FUN_CALL_ARGS = eINSTANCE.getstate_Comp_Ref_Fun_call_args();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl <em>when statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getwhen_statement()
     * @generated
     */
    EClass WHEN_STATEMENT = eINSTANCE.getwhen_statement();

    /**
     * The meta object literal for the '<em><b>When expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_STATEMENT__WHEN_EXPR = eINSTANCE.getwhen_statement_When_expr();

    /**
     * The meta object literal for the '<em><b>When stat true</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_STATEMENT__WHEN_STAT_TRUE = eINSTANCE.getwhen_statement_When_stat_true();

    /**
     * The meta object literal for the '<em><b>Else When expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_STATEMENT__ELSE_WHEN_EXPR = eINSTANCE.getwhen_statement_Else_When_expr();

    /**
     * The meta object literal for the '<em><b>Then statement</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_STATEMENT__THEN_STATEMENT = eINSTANCE.getwhen_statement_Then_statement();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.while_statementImpl <em>while statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.while_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getwhile_statement()
     * @generated
     */
    EClass WHILE_STATEMENT = eINSTANCE.getwhile_statement();

    /**
     * The meta object literal for the '<em><b>While expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHILE_STATEMENT__WHILE_EXPR = eINSTANCE.getwhile_statement_While_expr();

    /**
     * The meta object literal for the '<em><b>While stat</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHILE_STATEMENT__WHILE_STAT = eINSTANCE.getwhile_statement_While_stat();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_statementImpl <em>for statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getfor_statement()
     * @generated
     */
    EClass FOR_STATEMENT = eINSTANCE.getfor_statement();

    /**
     * The meta object literal for the '<em><b>For indices</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__FOR_INDICES = eINSTANCE.getfor_statement_For_indices();

    /**
     * The meta object literal for the '<em><b>For statement</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__FOR_STATEMENT = eINSTANCE.getfor_statement_For_statement();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_statementImpl <em>if statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getif_statement()
     * @generated
     */
    EClass IF_STATEMENT = eINSTANCE.getif_statement();

    /**
     * The meta object literal for the '<em><b>Exprtrue</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_STATEMENT__EXPRTRUE = eINSTANCE.getif_statement_Exprtrue();

    /**
     * The meta object literal for the '<em><b>State</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_STATEMENT__STATE = eINSTANCE.getif_statement_State();

    /**
     * The meta object literal for the '<em><b>Expr Stilltrue</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_STATEMENT__EXPR_STILLTRUE = eINSTANCE.getif_statement_ExprStilltrue();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.extends_clauseImpl <em>extends clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.extends_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getextends_clause()
     * @generated
     */
    EClass EXTENDS_CLAUSE = eINSTANCE.getextends_clause();

    /**
     * The meta object literal for the '<em><b>Class Mod</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS_CLAUSE__CLASS_MOD = eINSTANCE.getextends_clause_Class_Mod();

    /**
     * The meta object literal for the '<em><b>Annotation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS_CLAUSE__ANNOTATION = eINSTANCE.getextends_clause_Annotation();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.annotationImpl <em>annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.annotationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getannotation()
     * @generated
     */
    EClass ANNOTATION = eINSTANCE.getannotation();

    /**
     * The meta object literal for the '<em><b>Class Mod</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATION__CLASS_MOD = eINSTANCE.getannotation_Class_Mod();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.import_clauseImpl <em>import clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.import_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getimport_clause()
     * @generated
     */
    EClass IMPORT_CLAUSE = eINSTANCE.getimport_clause();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.language_specificationImpl <em>language specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.language_specificationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getlanguage_specification()
     * @generated
     */
    EClass LANGUAGE_SPECIFICATION = eINSTANCE.getlanguage_specification();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LANGUAGE_SPECIFICATION__STR = eINSTANCE.getlanguage_specification_Str();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.external_function_callImpl <em>external function call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.external_function_callImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getexternal_function_call()
     * @generated
     */
    EClass EXTERNAL_FUNCTION_CALL = eINSTANCE.getexternal_function_call();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTERNAL_FUNCTION_CALL__COMPONENT_REFERENCE = eINSTANCE.getexternal_function_call_Component_reference();

    /**
     * The meta object literal for the '<em><b>EList</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTERNAL_FUNCTION_CALL__ELIST = eINSTANCE.getexternal_function_call_E_List();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modification_actionImpl <em>class modification action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_modification_actionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getclass_modification_action()
     * @generated
     */
    EClass CLASS_MODIFICATION_ACTION = eINSTANCE.getclass_modification_action();

    /**
     * The meta object literal for the '<em><b>Arg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_MODIFICATION_ACTION__ARG = eINSTANCE.getclass_modification_action_Arg();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_clauseImpl <em>algorithm clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.algorithm_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.ModificationPackageImpl#getalgorithm_clause()
     * @generated
     */
    EClass ALGORITHM_CLAUSE = eINSTANCE.getalgorithm_clause();

    /**
     * The meta object literal for the '<em><b>Algorithm</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM_CLAUSE__ALGORITHM = eINSTANCE.getalgorithm_clause_Algorithm();

  }

} //ModificationPackage
