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
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection;

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
 * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionFactory
 * @model kind="package"
 * @generated
 */
public interface AlgorithmsectionPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "algorithmsection";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/algorithm/Algorithmsection";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "algorithmsection";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  AlgorithmsectionPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.algorithm_clauseImpl <em>algorithm clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.algorithm_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getalgorithm_clause()
   * @generated
   */
  int ALGORITHM_CLAUSE = 0;

  /**
   * The feature id for the '<em><b>Algorithm</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_CLAUSE__ALGORITHM = 0;

  /**
   * The number of structural features of the '<em>algorithm clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_CLAUSE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.statementImpl <em>statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getstatement()
   * @generated
   */
  int STATEMENT = 1;

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
   * The feature id for the '<em><b>Algo MG</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__ALGO_MG = 2;

  /**
   * The feature id for the '<em><b>State Comp Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__STATE_COMP_REF = 3;

  /**
   * The feature id for the '<em><b>For statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__FOR_STATEMENT = 4;

  /**
   * The feature id for the '<em><b>When statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__WHEN_STATEMENT = 5;

  /**
   * The feature id for the '<em><b>While statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__WHILE_STATEMENT = 6;

  /**
   * The feature id for the '<em><b>Algo GI</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__ALGO_GI = 7;

  /**
   * The feature id for the '<em><b>Algo GC</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__ALGO_GC = 8;

  /**
   * The number of structural features of the '<em>statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_SIGNALImpl <em>Algorithm Macros GEN SIGNAL</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_SIGNALImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getAlgorithm_Macros_GEN_SIGNAL()
   * @generated
   */
  int ALGORITHM_MACROS_GEN_SIGNAL = 2;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_MACROS_GEN_SIGNAL__COMPONENT_REFERENCE = 0;

  /**
   * The number of structural features of the '<em>Algorithm Macros GEN SIGNAL</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_MACROS_GEN_SIGNAL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_INCImpl <em>Algorithm Macros GEN INC</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_INCImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getAlgorithm_Macros_GEN_INC()
   * @generated
   */
  int ALGORITHM_MACROS_GEN_INC = 3;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_MACROS_GEN_INC__COMPONENT_REFERENCE = 0;

  /**
   * The number of structural features of the '<em>Algorithm Macros GEN INC</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_MACROS_GEN_INC_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_CHANGEImpl <em>Algorithm Macros GEN CHANGE</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_CHANGEImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getAlgorithm_Macros_GEN_CHANGE()
   * @generated
   */
  int ALGORITHM_MACROS_GEN_CHANGE = 4;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_MACROS_GEN_CHANGE__COMPONENT_REFERENCE = 0;

  /**
   * The number of structural features of the '<em>Algorithm Macros GEN CHANGE</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_MACROS_GEN_CHANGE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_OutputExprListImpl <em>state Output Expr List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_OutputExprListImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getstate_OutputExprList()
   * @generated
   */
  int STATE_OUTPUT_EXPR_LIST = 5;

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
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_Comp_RefImpl <em>state Comp Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_Comp_RefImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getstate_Comp_Ref()
   * @generated
   */
  int STATE_COMP_REF = 6;

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
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl <em>when statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getwhen_statement()
   * @generated
   */
  int WHEN_STATEMENT = 7;

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
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.while_statementImpl <em>while statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.while_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getwhile_statement()
   * @generated
   */
  int WHILE_STATEMENT = 8;

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
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.for_statementImpl <em>for statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.for_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getfor_statement()
   * @generated
   */
  int FOR_STATEMENT = 9;

  /**
   * The feature id for the '<em><b>For loop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__FOR_LOOP = 0;

  /**
   * The feature id for the '<em><b>For algo</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT__FOR_ALGO = 1;

  /**
   * The number of structural features of the '<em>for statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_STATEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.if_statementImpl <em>if statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.if_statementImpl
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getif_statement()
   * @generated
   */
  int IF_STATEMENT = 10;

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
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause <em>algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>algorithm clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause
   * @generated
   */
  EClass getalgorithm_clause();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause#getAlgorithm <em>Algorithm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Algorithm</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause#getAlgorithm()
   * @see #getalgorithm_clause()
   * @generated
   */
  EReference getalgorithm_clause_Algorithm();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement <em>statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement
   * @generated
   */
  EClass getstatement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getIf_statement <em>If statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>If statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getIf_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_If_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getState_OutputExprList <em>State Output Expr List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>State Output Expr List</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getState_OutputExprList()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_State_OutputExprList();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getAlgo_M_G <em>Algo MG</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Algo MG</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getAlgo_M_G()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_Algo_M_G();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getState_Comp_Ref <em>State Comp Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>State Comp Ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getState_Comp_Ref()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_State_Comp_Ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getFor_statement <em>For statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getFor_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_For_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getWhen_statement <em>When statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>When statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getWhen_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_When_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getWhile_statement <em>While statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>While statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getWhile_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_While_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getAlgo_G_I <em>Algo GI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Algo GI</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getAlgo_G_I()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_Algo_G_I();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getAlgo_G_C <em>Algo GC</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Algo GC</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement#getAlgo_G_C()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_Algo_G_C();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL <em>Algorithm Macros GEN SIGNAL</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Algorithm Macros GEN SIGNAL</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL
   * @generated
   */
  EClass getAlgorithm_Macros_GEN_SIGNAL();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL#getComponent_reference()
   * @see #getAlgorithm_Macros_GEN_SIGNAL()
   * @generated
   */
  EReference getAlgorithm_Macros_GEN_SIGNAL_Component_reference();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC <em>Algorithm Macros GEN INC</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Algorithm Macros GEN INC</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC
   * @generated
   */
  EClass getAlgorithm_Macros_GEN_INC();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC#getComponent_reference()
   * @see #getAlgorithm_Macros_GEN_INC()
   * @generated
   */
  EReference getAlgorithm_Macros_GEN_INC_Component_reference();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE <em>Algorithm Macros GEN CHANGE</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Algorithm Macros GEN CHANGE</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE
   * @generated
   */
  EClass getAlgorithm_Macros_GEN_CHANGE();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE#getComponent_reference()
   * @see #getAlgorithm_Macros_GEN_CHANGE()
   * @generated
   */
  EReference getAlgorithm_Macros_GEN_CHANGE_Component_reference();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList <em>state Output Expr List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>state Output Expr List</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList
   * @generated
   */
  EClass getstate_OutputExprList();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getOutput_expression_list <em>Output expression list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output expression list</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getOutput_expression_list()
   * @see #getstate_OutputExprList()
   * @generated
   */
  EReference getstate_OutputExprList_Output_expression_list();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getComponent_ref <em>Component ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getComponent_ref()
   * @see #getstate_OutputExprList()
   * @generated
   */
  EReference getstate_OutputExprList_Component_ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getFun_call_args <em>Fun call args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fun call args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList#getFun_call_args()
   * @see #getstate_OutputExprList()
   * @generated
   */
  EReference getstate_OutputExprList_Fun_call_args();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref <em>state Comp Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>state Comp Ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref
   * @generated
   */
  EClass getstate_Comp_Ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref#getComponent_reference()
   * @see #getstate_Comp_Ref()
   * @generated
   */
  EReference getstate_Comp_Ref_Component_reference();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref#getExpr()
   * @see #getstate_Comp_Ref()
   * @generated
   */
  EReference getstate_Comp_Ref_Expr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref#getFun_call_args <em>Fun call args</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fun call args</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref#getFun_call_args()
   * @see #getstate_Comp_Ref()
   * @generated
   */
  EReference getstate_Comp_Ref_Fun_call_args();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement <em>when statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>when statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement
   * @generated
   */
  EClass getwhen_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getWhen_expr <em>When expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>When expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getWhen_expr()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_When_expr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getWhen_stat_true <em>When stat true</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>When stat true</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getWhen_stat_true()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_When_stat_true();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getElse_When_expr <em>Else When expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else When expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getElse_When_expr()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_Else_When_expr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getThen_statement <em>Then statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement#getThen_statement()
   * @see #getwhen_statement()
   * @generated
   */
  EReference getwhen_statement_Then_statement();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement <em>while statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>while statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement
   * @generated
   */
  EClass getwhile_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement#getWhile_expr <em>While expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>While expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement#getWhile_expr()
   * @see #getwhile_statement()
   * @generated
   */
  EReference getwhile_statement_While_expr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement#getWhile_stat <em>While stat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>While stat</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement#getWhile_stat()
   * @see #getwhile_statement()
   * @generated
   */
  EReference getwhile_statement_While_stat();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement <em>for statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>for statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement
   * @generated
   */
  EClass getfor_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement#getFor_loop <em>For loop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For loop</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement#getFor_loop()
   * @see #getfor_statement()
   * @generated
   */
  EReference getfor_statement_For_loop();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement#getFor_algo <em>For algo</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>For algo</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement#getFor_algo()
   * @see #getfor_statement()
   * @generated
   */
  EReference getfor_statement_For_algo();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement <em>if statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>if statement</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement
   * @generated
   */
  EClass getif_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement#getExprtrue <em>Exprtrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Exprtrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement#getExprtrue()
   * @see #getif_statement()
   * @generated
   */
  EReference getif_statement_Exprtrue();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement#getState <em>State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>State</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement#getState()
   * @see #getif_statement()
   * @generated
   */
  EReference getif_statement_State();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement#getExprStilltrue <em>Expr Stilltrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr Stilltrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement#getExprStilltrue()
   * @see #getif_statement()
   * @generated
   */
  EReference getif_statement_ExprStilltrue();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  AlgorithmsectionFactory getAlgorithmsectionFactory();

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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.algorithm_clauseImpl <em>algorithm clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.algorithm_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getalgorithm_clause()
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

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.statementImpl <em>statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getstatement()
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
     * The meta object literal for the '<em><b>Algo MG</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__ALGO_MG = eINSTANCE.getstatement_Algo_M_G();

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
     * The meta object literal for the '<em><b>Algo GI</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__ALGO_GI = eINSTANCE.getstatement_Algo_G_I();

    /**
     * The meta object literal for the '<em><b>Algo GC</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__ALGO_GC = eINSTANCE.getstatement_Algo_G_C();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_SIGNALImpl <em>Algorithm Macros GEN SIGNAL</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_SIGNALImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getAlgorithm_Macros_GEN_SIGNAL()
     * @generated
     */
    EClass ALGORITHM_MACROS_GEN_SIGNAL = eINSTANCE.getAlgorithm_Macros_GEN_SIGNAL();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM_MACROS_GEN_SIGNAL__COMPONENT_REFERENCE = eINSTANCE.getAlgorithm_Macros_GEN_SIGNAL_Component_reference();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_INCImpl <em>Algorithm Macros GEN INC</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_INCImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getAlgorithm_Macros_GEN_INC()
     * @generated
     */
    EClass ALGORITHM_MACROS_GEN_INC = eINSTANCE.getAlgorithm_Macros_GEN_INC();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM_MACROS_GEN_INC__COMPONENT_REFERENCE = eINSTANCE.getAlgorithm_Macros_GEN_INC_Component_reference();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_CHANGEImpl <em>Algorithm Macros GEN CHANGE</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.Algorithm_Macros_GEN_CHANGEImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getAlgorithm_Macros_GEN_CHANGE()
     * @generated
     */
    EClass ALGORITHM_MACROS_GEN_CHANGE = eINSTANCE.getAlgorithm_Macros_GEN_CHANGE();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM_MACROS_GEN_CHANGE__COMPONENT_REFERENCE = eINSTANCE.getAlgorithm_Macros_GEN_CHANGE_Component_reference();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_OutputExprListImpl <em>state Output Expr List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_OutputExprListImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getstate_OutputExprList()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_Comp_RefImpl <em>state Comp Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_Comp_RefImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getstate_Comp_Ref()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl <em>when statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getwhen_statement()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.while_statementImpl <em>while statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.while_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getwhile_statement()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.for_statementImpl <em>for statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.for_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getfor_statement()
     * @generated
     */
    EClass FOR_STATEMENT = eINSTANCE.getfor_statement();

    /**
     * The meta object literal for the '<em><b>For loop</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__FOR_LOOP = eINSTANCE.getfor_statement_For_loop();

    /**
     * The meta object literal for the '<em><b>For algo</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_STATEMENT__FOR_ALGO = eINSTANCE.getfor_statement_For_algo();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.if_statementImpl <em>if statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.if_statementImpl
     * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.AlgorithmsectionPackageImpl#getif_statement()
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

  }

} //AlgorithmsectionPackage
