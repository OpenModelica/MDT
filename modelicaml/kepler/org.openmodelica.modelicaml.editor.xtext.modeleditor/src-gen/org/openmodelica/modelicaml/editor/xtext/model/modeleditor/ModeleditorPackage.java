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
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor;

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
 * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorFactory
 * @model kind="package"
 * @generated
 */
public interface ModeleditorPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "modeleditor";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/model/Modeleditor";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "modeleditor";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModeleditorPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expressionImpl <em>expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expressionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getexpression()
   * @generated
   */
  int EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__EXPR = 0;

  /**
   * The number of structural features of the '<em>expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.simple_expressionImpl <em>simple expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.simple_expressionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getsimple_expression()
   * @generated
   */
  int SIMPLE_EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>Log Exp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_EXPRESSION__LOG_EXP = 0;

  /**
   * The feature id for the '<em><b>SLogical expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>LLogical expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION = 2;

  /**
   * The number of structural features of the '<em>simple expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_EXPRESSION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl <em>conditional expr</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getconditional_expr()
   * @generated
   */
  int CONDITIONAL_EXPR = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPR__EXPR = EXPRESSION__EXPR;

  /**
   * The feature id for the '<em><b>Ifexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPR__IFEXPR = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Thenexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPR__THENEXPR = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Elseifexpr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPR__ELSEIFEXPR = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Trueexpr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPR__TRUEEXPR = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Falseexpr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPR__FALSEEXPR = EXPRESSION_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>conditional expr</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_expressionImpl <em>logical expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_expressionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getlogical_expression()
   * @generated
   */
  int LOGICAL_EXPRESSION = 3;

  /**
   * The number of structural features of the '<em>logical expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_termImpl <em>logical term</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_termImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getlogical_term()
   * @generated
   */
  int LOGICAL_TERM = 4;

  /**
   * The feature id for the '<em><b>Logical term</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_TERM__LOGICAL_TERM = LOGICAL_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>logical term</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_TERM_FEATURE_COUNT = LOGICAL_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_factorImpl <em>logical factor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_factorImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getlogical_factor()
   * @generated
   */
  int LOGICAL_FACTOR = 5;

  /**
   * The feature id for the '<em><b>Logical term</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_FACTOR__LOGICAL_TERM = LOGICAL_TERM__LOGICAL_TERM;

  /**
   * The feature id for the '<em><b>Logical factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_FACTOR__LOGICAL_FACTOR = LOGICAL_TERM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Relation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_FACTOR__RELATION = LOGICAL_TERM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>logical factor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_FACTOR_FEATURE_COUNT = LOGICAL_TERM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.relationImpl <em>relation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.relationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getrelation()
   * @generated
   */
  int RELATION = 6;

  /**
   * The number of structural features of the '<em>relation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl <em>arithmetic expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getarithmetic_expression()
   * @generated
   */
  int ARITHMETIC_EXPRESSION = 7;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARITHMETIC_EXPRESSION__OP = RELATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Arithmetic expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION = RELATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Opr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARITHMETIC_EXPRESSION__OPR = RELATION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Term</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARITHMETIC_EXPRESSION__TERM = RELATION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Oper1</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARITHMETIC_EXPRESSION__OPER1 = RELATION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Term1</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARITHMETIC_EXPRESSION__TERM1 = RELATION_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>arithmetic expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARITHMETIC_EXPRESSION_FEATURE_COUNT = RELATION_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.termImpl <em>term</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.termImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getterm()
   * @generated
   */
  int TERM = 8;

  /**
   * The number of structural features of the '<em>term</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TERM_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.factorImpl <em>factor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.factorImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfactor()
   * @generated
   */
  int FACTOR = 9;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FACTOR__OP = TERM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FACTOR__FACTOR = TERM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>factor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FACTOR_FEATURE_COUNT = TERM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl <em>primary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getprimary()
   * @generated
   */
  int PRIMARY = 10;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OP = FACTOR__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FACTOR = FACTOR__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__PRIMARY = FACTOR_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NUM = FACTOR_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__INT = FACTOR_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__STR = FACTOR_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__BOOL = FACTOR_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__COMPONENT_REFERENCE = FACTOR_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OUTPUT_EXPR_LIST = FACTOR_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRE_LIST = FACTOR_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRESSION_LIST = FACTOR_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FARGUMENTS = FACTOR_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__END = FACTOR_FEATURE_COUNT + 10;

  /**
   * The number of structural features of the '<em>primary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY_FEATURE_COUNT = FACTOR_FEATURE_COUNT + 11;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.name_FunctionImpl <em>name Function</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.name_FunctionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getname_Function()
   * @generated
   */
  int NAME_FUNCTION = 11;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__OP = PRIMARY__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__FACTOR = PRIMARY__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__PRIMARY = PRIMARY__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__NUM = PRIMARY__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__INT = PRIMARY__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__STR = PRIMARY__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__BOOL = PRIMARY__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__COMPONENT_REFERENCE = PRIMARY__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__OUTPUT_EXPR_LIST = PRIMARY__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__EXPRE_LIST = PRIMARY__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__EXPRESSION_LIST = PRIMARY__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__FARGUMENTS = PRIMARY__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION__END = PRIMARY__END;

  /**
   * The number of structural features of the '<em>name Function</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FUNCTION_FEATURE_COUNT = PRIMARY_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.initial_refImpl <em>initial ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.initial_refImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getinitial_ref()
   * @generated
   */
  int INITIAL_REF = 12;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__OP = PRIMARY__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__FACTOR = PRIMARY__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__PRIMARY = PRIMARY__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__NUM = PRIMARY__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__INT = PRIMARY__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__STR = PRIMARY__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__BOOL = PRIMARY__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__COMPONENT_REFERENCE = PRIMARY__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__OUTPUT_EXPR_LIST = PRIMARY__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__EXPRE_LIST = PRIMARY__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__EXPRESSION_LIST = PRIMARY__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__FARGUMENTS = PRIMARY__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF__END = PRIMARY__END;

  /**
   * The number of structural features of the '<em>initial ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_REF_FEATURE_COUNT = PRIMARY_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ExprDerImpl <em>Expr Der</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ExprDerImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getExprDer()
   * @generated
   */
  int EXPR_DER = 13;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__OP = PRIMARY__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__FACTOR = PRIMARY__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__PRIMARY = PRIMARY__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__NUM = PRIMARY__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__INT = PRIMARY__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__STR = PRIMARY__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__BOOL = PRIMARY__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__COMPONENT_REFERENCE = PRIMARY__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__OUTPUT_EXPR_LIST = PRIMARY__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__EXPRE_LIST = PRIMARY__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__EXPRESSION_LIST = PRIMARY__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__FARGUMENTS = PRIMARY__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__END = PRIMARY__END;

  /**
   * The feature id for the '<em><b>Function Args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER__FUNCTION_ARGS = PRIMARY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expr Der</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPR_DER_FEATURE_COUNT = PRIMARY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_call_argsImpl <em>function call args</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_call_argsImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfunction_call_args()
   * @generated
   */
  int FUNCTION_CALL_ARGS = 14;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__OP = INITIAL_REF__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__FACTOR = INITIAL_REF__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__PRIMARY = INITIAL_REF__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__NUM = INITIAL_REF__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__INT = INITIAL_REF__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__STR = INITIAL_REF__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__BOOL = INITIAL_REF__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__COMPONENT_REFERENCE = INITIAL_REF__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__OUTPUT_EXPR_LIST = INITIAL_REF__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__EXPRE_LIST = INITIAL_REF__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__EXPRESSION_LIST = INITIAL_REF__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__FARGUMENTS = INITIAL_REF__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__END = INITIAL_REF__END;

  /**
   * The feature id for the '<em><b>Farg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS__FARG = INITIAL_REF_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>function call args</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_ARGS_FEATURE_COUNT = INITIAL_REF_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expression_listImpl <em>expression list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expression_listImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getexpression_list()
   * @generated
   */
  int EXPRESSION_LIST = 15;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_LIST__EXPR = 0;

  /**
   * The feature id for the '<em><b>Expre</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_LIST__EXPRE = 1;

  /**
   * The number of structural features of the '<em>expression list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.nameImpl <em>name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.nameImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getname()
   * @generated
   */
  int NAME = 16;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__OP = NAME_FUNCTION__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__FACTOR = NAME_FUNCTION__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__PRIMARY = NAME_FUNCTION__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__NUM = NAME_FUNCTION__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__INT = NAME_FUNCTION__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__STR = NAME_FUNCTION__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__BOOL = NAME_FUNCTION__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__COMPONENT_REFERENCE = NAME_FUNCTION__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__OUTPUT_EXPR_LIST = NAME_FUNCTION__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__EXPRE_LIST = NAME_FUNCTION__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__EXPRESSION_LIST = NAME_FUNCTION__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__FARGUMENTS = NAME_FUNCTION__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__END = NAME_FUNCTION__END;

  /**
   * The feature id for the '<em><b>Function call args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__FUNCTION_CALL_ARGS = NAME_FUNCTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__NAME_ID = NAME_FUNCTION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Nam ID</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__NAM_ID = NAME_FUNCTION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FEATURE_COUNT = NAME_FUNCTION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.component_referenceImpl <em>component reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.component_referenceImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getcomponent_reference()
   * @generated
   */
  int COMPONENT_REFERENCE = 17;

  /**
   * The feature id for the '<em><b>Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__REF = 0;

  /**
   * The feature id for the '<em><b>Subscripts1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__SUBSCRIPTS1 = 1;

  /**
   * The feature id for the '<em><b>Ref1</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__REF1 = 2;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__SUBSCRIPTS = 3;

  /**
   * The number of structural features of the '<em>component reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.output_expression_listImpl <em>output expression list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.output_expression_listImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getoutput_expression_list()
   * @generated
   */
  int OUTPUT_EXPRESSION_LIST = 18;

  /**
   * The feature id for the '<em><b>Epr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_EXPRESSION_LIST__EPR = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_EXPRESSION_LIST__EXPR = 1;

  /**
   * The number of structural features of the '<em>output expression list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_EXPRESSION_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.array_subscriptsImpl <em>array subscripts</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.array_subscriptsImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getarray_subscripts()
   * @generated
   */
  int ARRAY_SUBSCRIPTS = 19;

  /**
   * The feature id for the '<em><b>Sub</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUBSCRIPTS__SUB = 0;

  /**
   * The feature id for the '<em><b>Subscript</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUBSCRIPTS__SUBSCRIPT = 1;

  /**
   * The number of structural features of the '<em>array subscripts</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_SUBSCRIPTS_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.subscriptImpl <em>subscript</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.subscriptImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getsubscript()
   * @generated
   */
  int SUBSCRIPT = 20;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUBSCRIPT__EXPR = 0;

  /**
   * The number of structural features of the '<em>subscript</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUBSCRIPT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl <em>function arguments</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfunction_arguments()
   * @generated
   */
  int FUNCTION_ARGUMENTS = 21;

  /**
   * The feature id for the '<em><b>Arg Exp</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_ARGUMENTS__ARG_EXP = 0;

  /**
   * The feature id for the '<em><b>Fun Arg Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_ARGUMENTS__FUN_ARG_EXPR = 1;

  /**
   * The feature id for the '<em><b>Fun Arg For</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_ARGUMENTS__FUN_ARG_FOR = 2;

  /**
   * The feature id for the '<em><b>Name arg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_ARGUMENTS__NAME_ARG = 3;

  /**
   * The number of structural features of the '<em>function arguments</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_ARGUMENTS_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_expImpl <em>Fun Arguments exp</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_expImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getFun_Arguments_exp()
   * @generated
   */
  int FUN_ARGUMENTS_EXP = 22;

  /**
   * The feature id for the '<em><b>Args</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_ARGUMENTS_EXP__ARGS = 0;

  /**
   * The number of structural features of the '<em>Fun Arguments exp</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_ARGUMENTS_EXP_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_forImpl <em>Fun Arguments for</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_forImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getFun_Arguments_for()
   * @generated
   */
  int FUN_ARGUMENTS_FOR = 23;

  /**
   * The feature id for the '<em><b>For indices</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_ARGUMENTS_FOR__FOR_INDICES = 0;

  /**
   * The number of structural features of the '<em>Fun Arguments for</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_ARGUMENTS_FOR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentsImpl <em>named arguments</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentsImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getnamed_arguments()
   * @generated
   */
  int NAMED_ARGUMENTS = 24;

  /**
   * The number of structural features of the '<em>named arguments</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ARGUMENTS_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentImpl <em>named argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getnamed_argument()
   * @generated
   */
  int NAMED_ARGUMENT = 25;

  /**
   * The feature id for the '<em><b>Named arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ARGUMENT__NAMED_ARGUMENTS = NAMED_ARGUMENTS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Arg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ARGUMENT__ARG = NAMED_ARGUMENTS_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ARGUMENT__EXPR = NAMED_ARGUMENTS_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>named argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ARGUMENT_FEATURE_COUNT = NAMED_ARGUMENTS_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indicesImpl <em>for indices</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indicesImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfor_indices()
   * @generated
   */
  int FOR_INDICES = 26;

  /**
   * The number of structural features of the '<em>for indices</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_INDICES_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indexImpl <em>for index</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indexImpl
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfor_index()
   * @generated
   */
  int FOR_INDEX = 27;

  /**
   * The feature id for the '<em><b>For index</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_INDEX__FOR_INDEX = FOR_INDICES_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_INDEX__INDEX = FOR_INDICES_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_INDEX__EXPR = FOR_INDICES_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>for index</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_INDEX_FEATURE_COUNT = FOR_INDICES_FEATURE_COUNT + 3;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression
   * @generated
   */
  EClass getexpression();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression#getExpr()
   * @see #getexpression()
   * @generated
   */
  EReference getexpression_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression <em>simple expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>simple expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression
   * @generated
   */
  EClass getsimple_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getLog_Exp <em>Log Exp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Log Exp</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getLog_Exp()
   * @see #getsimple_expression()
   * @generated
   */
  EReference getsimple_expression_Log_Exp();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getS_Logical_expression <em>SLogical expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>SLogical expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getS_Logical_expression()
   * @see #getsimple_expression()
   * @generated
   */
  EReference getsimple_expression_S_Logical_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getL_Logical_expression <em>LLogical expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>LLogical expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression#getL_Logical_expression()
   * @see #getsimple_expression()
   * @generated
   */
  EReference getsimple_expression_L_Logical_expression();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr <em>conditional expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>conditional expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr
   * @generated
   */
  EClass getconditional_expr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getIfexpr <em>Ifexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ifexpr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getIfexpr()
   * @see #getconditional_expr()
   * @generated
   */
  EReference getconditional_expr_Ifexpr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getThenexpr <em>Thenexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Thenexpr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getThenexpr()
   * @see #getconditional_expr()
   * @generated
   */
  EReference getconditional_expr_Thenexpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getElseifexpr <em>Elseifexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elseifexpr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getElseifexpr()
   * @see #getconditional_expr()
   * @generated
   */
  EReference getconditional_expr_Elseifexpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getTrueexpr <em>Trueexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Trueexpr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getTrueexpr()
   * @see #getconditional_expr()
   * @generated
   */
  EReference getconditional_expr_Trueexpr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getFalseexpr <em>Falseexpr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Falseexpr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr#getFalseexpr()
   * @see #getconditional_expr()
   * @generated
   */
  EReference getconditional_expr_Falseexpr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_expression <em>logical expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>logical expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_expression
   * @generated
   */
  EClass getlogical_expression();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_term <em>logical term</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>logical term</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_term
   * @generated
   */
  EClass getlogical_term();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_term#getLogical_term <em>Logical term</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Logical term</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_term#getLogical_term()
   * @see #getlogical_term()
   * @generated
   */
  EReference getlogical_term_Logical_term();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor <em>logical factor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>logical factor</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor
   * @generated
   */
  EClass getlogical_factor();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor#getLogical_factor <em>Logical factor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Logical factor</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor#getLogical_factor()
   * @see #getlogical_factor()
   * @generated
   */
  EReference getlogical_factor_Logical_factor();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor#getRelation <em>Relation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Relation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor#getRelation()
   * @see #getlogical_factor()
   * @generated
   */
  EReference getlogical_factor_Relation();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.relation <em>relation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>relation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.relation
   * @generated
   */
  EClass getrelation();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression <em>arithmetic expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>arithmetic expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression
   * @generated
   */
  EClass getarithmetic_expression();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Op</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOp()
   * @see #getarithmetic_expression()
   * @generated
   */
  EAttribute getarithmetic_expression_Op();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getArithmetic_expression <em>Arithmetic expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arithmetic expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getArithmetic_expression()
   * @see #getarithmetic_expression()
   * @generated
   */
  EReference getarithmetic_expression_Arithmetic_expression();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOpr <em>Opr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Opr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOpr()
   * @see #getarithmetic_expression()
   * @generated
   */
  EAttribute getarithmetic_expression_Opr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getTerm <em>Term</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Term</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getTerm()
   * @see #getarithmetic_expression()
   * @generated
   */
  EReference getarithmetic_expression_Term();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOper1 <em>Oper1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Oper1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getOper1()
   * @see #getarithmetic_expression()
   * @generated
   */
  EAttribute getarithmetic_expression_Oper1();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getTerm1 <em>Term1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Term1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression#getTerm1()
   * @see #getarithmetic_expression()
   * @generated
   */
  EReference getarithmetic_expression_Term1();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.term <em>term</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>term</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.term
   * @generated
   */
  EClass getterm();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor <em>factor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>factor</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor
   * @generated
   */
  EClass getfactor();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Op</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor#getOp()
   * @see #getfactor()
   * @generated
   */
  EAttribute getfactor_Op();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor#getFactor <em>Factor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Factor</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor#getFactor()
   * @see #getfactor()
   * @generated
   */
  EReference getfactor_Factor();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>primary</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary
   * @generated
   */
  EClass getprimary();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getPrimary <em>Primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Primary</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getPrimary()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Primary();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getNum <em>Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Num</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getNum()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Num();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getInt <em>Int</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Int</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getInt()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Int();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getStr <em>Str</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Str</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getStr()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Str();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getBool <em>Bool</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Bool</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getBool()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Bool();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getComponent_reference()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Component_reference();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getOutput_expr_list <em>Output expr list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output expr list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getOutput_expr_list()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Output_expr_list();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getExpre_list <em>Expre list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expre list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getExpre_list()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Expre_list();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getExpression_list <em>Expression list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getExpression_list()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Expression_list();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getF_arguments <em>Farguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Farguments</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getF_arguments()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_F_arguments();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary#getEnd()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_End();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function <em>name Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>name Function</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function
   * @generated
   */
  EClass getname_Function();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref <em>initial ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>initial ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref
   * @generated
   */
  EClass getinitial_ref();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer <em>Expr Der</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expr Der</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer
   * @generated
   */
  EClass getExprDer();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer#getFunctionArgs <em>Function Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer#getFunctionArgs()
   * @see #getExprDer()
   * @generated
   */
  EReference getExprDer_FunctionArgs();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args <em>function call args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>function call args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args
   * @generated
   */
  EClass getfunction_call_args();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args#getF_arg <em>Farg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Farg</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args#getF_arg()
   * @see #getfunction_call_args()
   * @generated
   */
  EReference getfunction_call_args_F_arg();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list <em>expression list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>expression list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list
   * @generated
   */
  EClass getexpression_list();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list#getExpr()
   * @see #getexpression_list()
   * @generated
   */
  EReference getexpression_list_Expr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list#getExpre <em>Expre</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expre</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list#getExpre()
   * @see #getexpression_list()
   * @generated
   */
  EReference getexpression_list_Expre();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name <em>name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>name</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name
   * @generated
   */
  EClass getname();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name#getFunction_call_args <em>Function call args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function call args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name#getFunction_call_args()
   * @see #getname()
   * @generated
   */
  EReference getname_Function_call_args();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name#getName_ID <em>Name ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name ID</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name#getName_ID()
   * @see #getname()
   * @generated
   */
  EAttribute getname_Name_ID();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name#getNam_ID <em>Nam ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Nam ID</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name#getNam_ID()
   * @see #getname()
   * @generated
   */
  EAttribute getname_Nam_ID();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference <em>component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference
   * @generated
   */
  EClass getcomponent_reference();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getRef()
   * @see #getcomponent_reference()
   * @generated
   */
  EAttribute getcomponent_reference_Ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getSubscripts1 <em>Subscripts1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Subscripts1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getSubscripts1()
   * @see #getcomponent_reference()
   * @generated
   */
  EReference getcomponent_reference_Subscripts1();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getRef1 <em>Ref1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Ref1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getRef1()
   * @see #getcomponent_reference()
   * @generated
   */
  EAttribute getcomponent_reference_Ref1();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getSubscripts <em>Subscripts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Subscripts</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference#getSubscripts()
   * @see #getcomponent_reference()
   * @generated
   */
  EReference getcomponent_reference_Subscripts();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list <em>output expression list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>output expression list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list
   * @generated
   */
  EClass getoutput_expression_list();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list#getEpr <em>Epr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Epr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list#getEpr()
   * @see #getoutput_expression_list()
   * @generated
   */
  EReference getoutput_expression_list_Epr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list#getExpr()
   * @see #getoutput_expression_list()
   * @generated
   */
  EReference getoutput_expression_list_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts <em>array subscripts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>array subscripts</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts
   * @generated
   */
  EClass getarray_subscripts();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts#getSub <em>Sub</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sub</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts#getSub()
   * @see #getarray_subscripts()
   * @generated
   */
  EReference getarray_subscripts_Sub();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts#getSubscript <em>Subscript</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Subscript</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts#getSubscript()
   * @see #getarray_subscripts()
   * @generated
   */
  EReference getarray_subscripts_Subscript();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.subscript <em>subscript</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>subscript</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.subscript
   * @generated
   */
  EClass getsubscript();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.subscript#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.subscript#getExpr()
   * @see #getsubscript()
   * @generated
   */
  EReference getsubscript_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments <em>function arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>function arguments</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments
   * @generated
   */
  EClass getfunction_arguments();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getArgExp <em>Arg Exp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arg Exp</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getArgExp()
   * @see #getfunction_arguments()
   * @generated
   */
  EReference getfunction_arguments_ArgExp();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_Expr <em>Fun Arg Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fun Arg Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_Expr()
   * @see #getfunction_arguments()
   * @generated
   */
  EReference getfunction_arguments_Fun_Arg_Expr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_For <em>Fun Arg For</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fun Arg For</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getFun_Arg_For()
   * @see #getfunction_arguments()
   * @generated
   */
  EReference getfunction_arguments_Fun_Arg_For();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getName_arg <em>Name arg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name arg</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments#getName_arg()
   * @see #getfunction_arguments()
   * @generated
   */
  EReference getfunction_arguments_Name_arg();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_exp <em>Fun Arguments exp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fun Arguments exp</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_exp
   * @generated
   */
  EClass getFun_Arguments_exp();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_exp#getArgs <em>Args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_exp#getArgs()
   * @see #getFun_Arguments_exp()
   * @generated
   */
  EReference getFun_Arguments_exp_Args();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for <em>Fun Arguments for</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fun Arguments for</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for
   * @generated
   */
  EClass getFun_Arguments_for();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for#getFor_indices <em>For indices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For indices</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for#getFor_indices()
   * @see #getFun_Arguments_for()
   * @generated
   */
  EReference getFun_Arguments_for_For_indices();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_arguments <em>named arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>named arguments</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_arguments
   * @generated
   */
  EClass getnamed_arguments();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument <em>named argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>named argument</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument
   * @generated
   */
  EClass getnamed_argument();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument#getNamed_arguments <em>Named arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Named arguments</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument#getNamed_arguments()
   * @see #getnamed_argument()
   * @generated
   */
  EReference getnamed_argument_Named_arguments();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument#getArg <em>Arg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arg</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument#getArg()
   * @see #getnamed_argument()
   * @generated
   */
  EAttribute getnamed_argument_Arg();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument#getExpr()
   * @see #getnamed_argument()
   * @generated
   */
  EReference getnamed_argument_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_indices <em>for indices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>for indices</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_indices
   * @generated
   */
  EClass getfor_indices();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index <em>for index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>for index</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index
   * @generated
   */
  EClass getfor_index();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index#getFor_index <em>For index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>For index</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index#getFor_index()
   * @see #getfor_index()
   * @generated
   */
  EReference getfor_index_For_index();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Index</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index#getIndex()
   * @see #getfor_index()
   * @generated
   */
  EAttribute getfor_index_Index();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index#getExpr()
   * @see #getfor_index()
   * @generated
   */
  EReference getfor_index_Expr();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ModeleditorFactory getModeleditorFactory();

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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expressionImpl <em>expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expressionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getexpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getexpression();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION__EXPR = eINSTANCE.getexpression_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.simple_expressionImpl <em>simple expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.simple_expressionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getsimple_expression()
     * @generated
     */
    EClass SIMPLE_EXPRESSION = eINSTANCE.getsimple_expression();

    /**
     * The meta object literal for the '<em><b>Log Exp</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMPLE_EXPRESSION__LOG_EXP = eINSTANCE.getsimple_expression_Log_Exp();

    /**
     * The meta object literal for the '<em><b>SLogical expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION = eINSTANCE.getsimple_expression_S_Logical_expression();

    /**
     * The meta object literal for the '<em><b>LLogical expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION = eINSTANCE.getsimple_expression_L_Logical_expression();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl <em>conditional expr</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getconditional_expr()
     * @generated
     */
    EClass CONDITIONAL_EXPR = eINSTANCE.getconditional_expr();

    /**
     * The meta object literal for the '<em><b>Ifexpr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPR__IFEXPR = eINSTANCE.getconditional_expr_Ifexpr();

    /**
     * The meta object literal for the '<em><b>Thenexpr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPR__THENEXPR = eINSTANCE.getconditional_expr_Thenexpr();

    /**
     * The meta object literal for the '<em><b>Elseifexpr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPR__ELSEIFEXPR = eINSTANCE.getconditional_expr_Elseifexpr();

    /**
     * The meta object literal for the '<em><b>Trueexpr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPR__TRUEEXPR = eINSTANCE.getconditional_expr_Trueexpr();

    /**
     * The meta object literal for the '<em><b>Falseexpr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPR__FALSEEXPR = eINSTANCE.getconditional_expr_Falseexpr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_expressionImpl <em>logical expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_expressionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getlogical_expression()
     * @generated
     */
    EClass LOGICAL_EXPRESSION = eINSTANCE.getlogical_expression();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_termImpl <em>logical term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_termImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getlogical_term()
     * @generated
     */
    EClass LOGICAL_TERM = eINSTANCE.getlogical_term();

    /**
     * The meta object literal for the '<em><b>Logical term</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOGICAL_TERM__LOGICAL_TERM = eINSTANCE.getlogical_term_Logical_term();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_factorImpl <em>logical factor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_factorImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getlogical_factor()
     * @generated
     */
    EClass LOGICAL_FACTOR = eINSTANCE.getlogical_factor();

    /**
     * The meta object literal for the '<em><b>Logical factor</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOGICAL_FACTOR__LOGICAL_FACTOR = eINSTANCE.getlogical_factor_Logical_factor();

    /**
     * The meta object literal for the '<em><b>Relation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOGICAL_FACTOR__RELATION = eINSTANCE.getlogical_factor_Relation();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.relationImpl <em>relation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.relationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getrelation()
     * @generated
     */
    EClass RELATION = eINSTANCE.getrelation();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl <em>arithmetic expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getarithmetic_expression()
     * @generated
     */
    EClass ARITHMETIC_EXPRESSION = eINSTANCE.getarithmetic_expression();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARITHMETIC_EXPRESSION__OP = eINSTANCE.getarithmetic_expression_Op();

    /**
     * The meta object literal for the '<em><b>Arithmetic expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION = eINSTANCE.getarithmetic_expression_Arithmetic_expression();

    /**
     * The meta object literal for the '<em><b>Opr</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARITHMETIC_EXPRESSION__OPR = eINSTANCE.getarithmetic_expression_Opr();

    /**
     * The meta object literal for the '<em><b>Term</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARITHMETIC_EXPRESSION__TERM = eINSTANCE.getarithmetic_expression_Term();

    /**
     * The meta object literal for the '<em><b>Oper1</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARITHMETIC_EXPRESSION__OPER1 = eINSTANCE.getarithmetic_expression_Oper1();

    /**
     * The meta object literal for the '<em><b>Term1</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARITHMETIC_EXPRESSION__TERM1 = eINSTANCE.getarithmetic_expression_Term1();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.termImpl <em>term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.termImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getterm()
     * @generated
     */
    EClass TERM = eINSTANCE.getterm();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.factorImpl <em>factor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.factorImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfactor()
     * @generated
     */
    EClass FACTOR = eINSTANCE.getfactor();

    /**
     * The meta object literal for the '<em><b>Op</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FACTOR__OP = eINSTANCE.getfactor_Op();

    /**
     * The meta object literal for the '<em><b>Factor</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FACTOR__FACTOR = eINSTANCE.getfactor_Factor();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl <em>primary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getprimary()
     * @generated
     */
    EClass PRIMARY = eINSTANCE.getprimary();

    /**
     * The meta object literal for the '<em><b>Primary</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__PRIMARY = eINSTANCE.getprimary_Primary();

    /**
     * The meta object literal for the '<em><b>Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__NUM = eINSTANCE.getprimary_Num();

    /**
     * The meta object literal for the '<em><b>Int</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__INT = eINSTANCE.getprimary_Int();

    /**
     * The meta object literal for the '<em><b>Str</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__STR = eINSTANCE.getprimary_Str();

    /**
     * The meta object literal for the '<em><b>Bool</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__BOOL = eINSTANCE.getprimary_Bool();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__COMPONENT_REFERENCE = eINSTANCE.getprimary_Component_reference();

    /**
     * The meta object literal for the '<em><b>Output expr list</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__OUTPUT_EXPR_LIST = eINSTANCE.getprimary_Output_expr_list();

    /**
     * The meta object literal for the '<em><b>Expre list</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__EXPRE_LIST = eINSTANCE.getprimary_Expre_list();

    /**
     * The meta object literal for the '<em><b>Expression list</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__EXPRESSION_LIST = eINSTANCE.getprimary_Expression_list();

    /**
     * The meta object literal for the '<em><b>Farguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__FARGUMENTS = eINSTANCE.getprimary_F_arguments();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__END = eINSTANCE.getprimary_End();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.name_FunctionImpl <em>name Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.name_FunctionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getname_Function()
     * @generated
     */
    EClass NAME_FUNCTION = eINSTANCE.getname_Function();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.initial_refImpl <em>initial ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.initial_refImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getinitial_ref()
     * @generated
     */
    EClass INITIAL_REF = eINSTANCE.getinitial_ref();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ExprDerImpl <em>Expr Der</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ExprDerImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getExprDer()
     * @generated
     */
    EClass EXPR_DER = eINSTANCE.getExprDer();

    /**
     * The meta object literal for the '<em><b>Function Args</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPR_DER__FUNCTION_ARGS = eINSTANCE.getExprDer_FunctionArgs();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_call_argsImpl <em>function call args</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_call_argsImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfunction_call_args()
     * @generated
     */
    EClass FUNCTION_CALL_ARGS = eINSTANCE.getfunction_call_args();

    /**
     * The meta object literal for the '<em><b>Farg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_CALL_ARGS__FARG = eINSTANCE.getfunction_call_args_F_arg();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expression_listImpl <em>expression list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expression_listImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getexpression_list()
     * @generated
     */
    EClass EXPRESSION_LIST = eINSTANCE.getexpression_list();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_LIST__EXPR = eINSTANCE.getexpression_list_Expr();

    /**
     * The meta object literal for the '<em><b>Expre</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_LIST__EXPRE = eINSTANCE.getexpression_list_Expre();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.nameImpl <em>name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.nameImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getname()
     * @generated
     */
    EClass NAME = eINSTANCE.getname();

    /**
     * The meta object literal for the '<em><b>Function call args</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAME__FUNCTION_CALL_ARGS = eINSTANCE.getname_Function_call_args();

    /**
     * The meta object literal for the '<em><b>Name ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAME__NAME_ID = eINSTANCE.getname_Name_ID();

    /**
     * The meta object literal for the '<em><b>Nam ID</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAME__NAM_ID = eINSTANCE.getname_Nam_ID();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.component_referenceImpl <em>component reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.component_referenceImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getcomponent_reference()
     * @generated
     */
    EClass COMPONENT_REFERENCE = eINSTANCE.getcomponent_reference();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMPONENT_REFERENCE__REF = eINSTANCE.getcomponent_reference_Ref();

    /**
     * The meta object literal for the '<em><b>Subscripts1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_REFERENCE__SUBSCRIPTS1 = eINSTANCE.getcomponent_reference_Subscripts1();

    /**
     * The meta object literal for the '<em><b>Ref1</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMPONENT_REFERENCE__REF1 = eINSTANCE.getcomponent_reference_Ref1();

    /**
     * The meta object literal for the '<em><b>Subscripts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_REFERENCE__SUBSCRIPTS = eINSTANCE.getcomponent_reference_Subscripts();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.output_expression_listImpl <em>output expression list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.output_expression_listImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getoutput_expression_list()
     * @generated
     */
    EClass OUTPUT_EXPRESSION_LIST = eINSTANCE.getoutput_expression_list();

    /**
     * The meta object literal for the '<em><b>Epr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OUTPUT_EXPRESSION_LIST__EPR = eINSTANCE.getoutput_expression_list_Epr();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OUTPUT_EXPRESSION_LIST__EXPR = eINSTANCE.getoutput_expression_list_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.array_subscriptsImpl <em>array subscripts</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.array_subscriptsImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getarray_subscripts()
     * @generated
     */
    EClass ARRAY_SUBSCRIPTS = eINSTANCE.getarray_subscripts();

    /**
     * The meta object literal for the '<em><b>Sub</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_SUBSCRIPTS__SUB = eINSTANCE.getarray_subscripts_Sub();

    /**
     * The meta object literal for the '<em><b>Subscript</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARRAY_SUBSCRIPTS__SUBSCRIPT = eINSTANCE.getarray_subscripts_Subscript();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.subscriptImpl <em>subscript</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.subscriptImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getsubscript()
     * @generated
     */
    EClass SUBSCRIPT = eINSTANCE.getsubscript();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SUBSCRIPT__EXPR = eINSTANCE.getsubscript_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl <em>function arguments</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfunction_arguments()
     * @generated
     */
    EClass FUNCTION_ARGUMENTS = eINSTANCE.getfunction_arguments();

    /**
     * The meta object literal for the '<em><b>Arg Exp</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_ARGUMENTS__ARG_EXP = eINSTANCE.getfunction_arguments_ArgExp();

    /**
     * The meta object literal for the '<em><b>Fun Arg Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_ARGUMENTS__FUN_ARG_EXPR = eINSTANCE.getfunction_arguments_Fun_Arg_Expr();

    /**
     * The meta object literal for the '<em><b>Fun Arg For</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_ARGUMENTS__FUN_ARG_FOR = eINSTANCE.getfunction_arguments_Fun_Arg_For();

    /**
     * The meta object literal for the '<em><b>Name arg</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_ARGUMENTS__NAME_ARG = eINSTANCE.getfunction_arguments_Name_arg();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_expImpl <em>Fun Arguments exp</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_expImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getFun_Arguments_exp()
     * @generated
     */
    EClass FUN_ARGUMENTS_EXP = eINSTANCE.getFun_Arguments_exp();

    /**
     * The meta object literal for the '<em><b>Args</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUN_ARGUMENTS_EXP__ARGS = eINSTANCE.getFun_Arguments_exp_Args();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_forImpl <em>Fun Arguments for</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_forImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getFun_Arguments_for()
     * @generated
     */
    EClass FUN_ARGUMENTS_FOR = eINSTANCE.getFun_Arguments_for();

    /**
     * The meta object literal for the '<em><b>For indices</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUN_ARGUMENTS_FOR__FOR_INDICES = eINSTANCE.getFun_Arguments_for_For_indices();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentsImpl <em>named arguments</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentsImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getnamed_arguments()
     * @generated
     */
    EClass NAMED_ARGUMENTS = eINSTANCE.getnamed_arguments();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentImpl <em>named argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.named_argumentImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getnamed_argument()
     * @generated
     */
    EClass NAMED_ARGUMENT = eINSTANCE.getnamed_argument();

    /**
     * The meta object literal for the '<em><b>Named arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ARGUMENT__NAMED_ARGUMENTS = eINSTANCE.getnamed_argument_Named_arguments();

    /**
     * The meta object literal for the '<em><b>Arg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMED_ARGUMENT__ARG = eINSTANCE.getnamed_argument_Arg();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ARGUMENT__EXPR = eINSTANCE.getnamed_argument_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indicesImpl <em>for indices</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indicesImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfor_indices()
     * @generated
     */
    EClass FOR_INDICES = eINSTANCE.getfor_indices();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indexImpl <em>for index</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.for_indexImpl
     * @see org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ModeleditorPackageImpl#getfor_index()
     * @generated
     */
    EClass FOR_INDEX = eINSTANCE.getfor_index();

    /**
     * The meta object literal for the '<em><b>For index</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_INDEX__FOR_INDEX = eINSTANCE.getfor_index_For_index();

    /**
     * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_INDEX__INDEX = eINSTANCE.getfor_index_Index();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_INDEX__EXPR = eINSTANCE.getfor_index_Expr();

  }

} //ModeleditorPackage
