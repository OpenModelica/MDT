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
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection;

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
 * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionFactory
 * @model kind="package"
 * @generated
 */
public interface EquationsectionPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "equationsection";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/equation/Equationsection";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "equationsection";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EquationsectionPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equation_sectionImpl <em>equation section</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equation_sectionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getequation_section()
   * @generated
   */
  int EQUATION_SECTION = 0;

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
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equationImpl <em>equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getequation()
   * @generated
   */
  int EQUATION = 1;

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
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION__COMMENT = 2;

  /**
   * The number of structural features of the '<em>equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.when_equationImpl <em>when equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.when_equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getwhen_equation()
   * @generated
   */
  int WHEN_EQUATION = 2;

  /**
   * The feature id for the '<em><b>Sim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__SIM = EQUATION__SIM;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__EXPR = EQUATION__EXPR;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__COMMENT = EQUATION__COMMENT;

  /**
   * The feature id for the '<em><b>When Epr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__WHEN_EPR = EQUATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Eqn Then</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__EQN_THEN = EQUATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else When Epr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION__ELSE_WHEN_EPR = EQUATION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>when equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_EQUATION_FEATURE_COUNT = EQUATION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.if_equationImpl <em>if equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.if_equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getif_equation()
   * @generated
   */
  int IF_EQUATION = 3;

  /**
   * The feature id for the '<em><b>Sim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__SIM = EQUATION__SIM;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__EXPR = EQUATION__EXPR;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__COMMENT = EQUATION__COMMENT;

  /**
   * The feature id for the '<em><b>Exprtrue</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__EXPRTRUE = EQUATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__EQN = EQUATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr Stilltrue</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__EXPR_STILLTRUE = EQUATION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Then Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__THEN_EQN = EQUATION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Else Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION__ELSE_EQN = EQUATION_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>if equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EQUATION_FEATURE_COUNT = EQUATION_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.for_equationImpl <em>for equation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.for_equationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getfor_equation()
   * @generated
   */
  int FOR_EQUATION = 4;

  /**
   * The feature id for the '<em><b>Sim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION__SIM = EQUATION__SIM;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION__EXPR = EQUATION__EXPR;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION__COMMENT = EQUATION__COMMENT;

  /**
   * The feature id for the '<em><b>For loop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION__FOR_LOOP = EQUATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>For Eqn</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION__FOR_EQN = EQUATION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>for equation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION_FEATURE_COUNT = EQUATION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.connect_clauseImpl <em>connect clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.connect_clauseImpl
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getconnect_clause()
   * @generated
   */
  int CONNECT_CLAUSE = 5;

  /**
   * The feature id for the '<em><b>Sim</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE__SIM = EQUATION__SIM;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE__EXPR = EQUATION__EXPR;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE__COMMENT = EQUATION__COMMENT;

  /**
   * The feature id for the '<em><b>Connector1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE__CONNECTOR1 = EQUATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Connector2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE__CONNECTOR2 = EQUATION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>connect clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONNECT_CLAUSE_FEATURE_COUNT = EQUATION_FEATURE_COUNT + 2;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation_section <em>equation section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>equation section</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation_section
   * @generated
   */
  EClass getequation_section();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation_section#getEqn <em>Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation_section#getEqn()
   * @see #getequation_section()
   * @generated
   */
  EReference getequation_section_Eqn();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation <em>equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation
   * @generated
   */
  EClass getequation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation#getSim <em>Sim</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sim</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation#getSim()
   * @see #getequation()
   * @generated
   */
  EReference getequation_Sim();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation#getExpr()
   * @see #getequation()
   * @generated
   */
  EReference getequation_Expr();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation#getComment()
   * @see #getequation()
   * @generated
   */
  EAttribute getequation_Comment();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation <em>when equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>when equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation
   * @generated
   */
  EClass getwhen_equation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getWhenEpr <em>When Epr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>When Epr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getWhenEpr()
   * @see #getwhen_equation()
   * @generated
   */
  EReference getwhen_equation_WhenEpr();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getEqn_Then <em>Eqn Then</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Eqn Then</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getEqn_Then()
   * @see #getwhen_equation()
   * @generated
   */
  EReference getwhen_equation_Eqn_Then();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getElseWhenEpr <em>Else When Epr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else When Epr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation#getElseWhenEpr()
   * @see #getwhen_equation()
   * @generated
   */
  EReference getwhen_equation_ElseWhenEpr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation <em>if equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>if equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation
   * @generated
   */
  EClass getif_equation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getExprtrue <em>Exprtrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Exprtrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getExprtrue()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_Exprtrue();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getEqn <em>Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getEqn()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_Eqn();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getExprStilltrue <em>Expr Stilltrue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr Stilltrue</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getExprStilltrue()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_ExprStilltrue();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getThenEqn <em>Then Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getThenEqn()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_ThenEqn();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getElseEqn <em>Else Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation#getElseEqn()
   * @see #getif_equation()
   * @generated
   */
  EReference getif_equation_ElseEqn();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation <em>for equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>for equation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation
   * @generated
   */
  EClass getfor_equation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation#getFor_loop <em>For loop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>For loop</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation#getFor_loop()
   * @see #getfor_equation()
   * @generated
   */
  EReference getfor_equation_For_loop();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation#getFor_Eqn <em>For Eqn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>For Eqn</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation#getFor_Eqn()
   * @see #getfor_equation()
   * @generated
   */
  EReference getfor_equation_For_Eqn();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause <em>connect clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>connect clause</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause
   * @generated
   */
  EClass getconnect_clause();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector1 <em>Connector1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Connector1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector1()
   * @see #getconnect_clause()
   * @generated
   */
  EReference getconnect_clause_Connector1();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector2 <em>Connector2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Connector2</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause#getConnector2()
   * @see #getconnect_clause()
   * @generated
   */
  EReference getconnect_clause_Connector2();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EquationsectionFactory getEquationsectionFactory();

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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equation_sectionImpl <em>equation section</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equation_sectionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getequation_section()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equationImpl <em>equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getequation()
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
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EQUATION__COMMENT = eINSTANCE.getequation_Comment();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.when_equationImpl <em>when equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.when_equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getwhen_equation()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.if_equationImpl <em>if equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.if_equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getif_equation()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.for_equationImpl <em>for equation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.for_equationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getfor_equation()
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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.connect_clauseImpl <em>connect clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.connect_clauseImpl
     * @see org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.EquationsectionPackageImpl#getconnect_clause()
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

  }

} //EquationsectionPackage
