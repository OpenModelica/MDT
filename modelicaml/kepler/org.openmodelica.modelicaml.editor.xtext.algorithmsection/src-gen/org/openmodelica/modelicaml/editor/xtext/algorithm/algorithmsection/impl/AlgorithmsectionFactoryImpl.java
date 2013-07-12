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
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionFactory;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AlgorithmsectionFactoryImpl extends EFactoryImpl implements AlgorithmsectionFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static AlgorithmsectionFactory init()
  {
    try
    {
      AlgorithmsectionFactory theAlgorithmsectionFactory = (AlgorithmsectionFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/algorithm/Algorithmsection"); 
      if (theAlgorithmsectionFactory != null)
      {
        return theAlgorithmsectionFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new AlgorithmsectionFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AlgorithmsectionFactoryImpl()
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
      case AlgorithmsectionPackage.ALGORITHM_CLAUSE: return createalgorithm_clause();
      case AlgorithmsectionPackage.STATEMENT: return createstatement();
      case AlgorithmsectionPackage.ALGORITHM_MACROS_GEN_SIGNAL: return createAlgorithm_Macros_GEN_SIGNAL();
      case AlgorithmsectionPackage.ALGORITHM_MACROS_GEN_INC: return createAlgorithm_Macros_GEN_INC();
      case AlgorithmsectionPackage.ALGORITHM_MACROS_GEN_CHANGE: return createAlgorithm_Macros_GEN_CHANGE();
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST: return createstate_OutputExprList();
      case AlgorithmsectionPackage.STATE_COMP_REF: return createstate_Comp_Ref();
      case AlgorithmsectionPackage.WHEN_STATEMENT: return createwhen_statement();
      case AlgorithmsectionPackage.WHILE_STATEMENT: return createwhile_statement();
      case AlgorithmsectionPackage.FOR_STATEMENT: return createfor_statement();
      case AlgorithmsectionPackage.IF_STATEMENT: return createif_statement();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
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
  public Algorithm_Macros_GEN_SIGNAL createAlgorithm_Macros_GEN_SIGNAL()
  {
    Algorithm_Macros_GEN_SIGNALImpl algorithm_Macros_GEN_SIGNAL = new Algorithm_Macros_GEN_SIGNALImpl();
    return algorithm_Macros_GEN_SIGNAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Algorithm_Macros_GEN_INC createAlgorithm_Macros_GEN_INC()
  {
    Algorithm_Macros_GEN_INCImpl algorithm_Macros_GEN_INC = new Algorithm_Macros_GEN_INCImpl();
    return algorithm_Macros_GEN_INC;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Algorithm_Macros_GEN_CHANGE createAlgorithm_Macros_GEN_CHANGE()
  {
    Algorithm_Macros_GEN_CHANGEImpl algorithm_Macros_GEN_CHANGE = new Algorithm_Macros_GEN_CHANGEImpl();
    return algorithm_Macros_GEN_CHANGE;
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
  public AlgorithmsectionPackage getAlgorithmsectionPackage()
  {
    return (AlgorithmsectionPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static AlgorithmsectionPackage getPackage()
  {
    return AlgorithmsectionPackage.eINSTANCE;
  }

} //AlgorithmsectionFactoryImpl
