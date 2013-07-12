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
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage
 * @generated
 */
public class AlgorithmsectionAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static AlgorithmsectionPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AlgorithmsectionAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = AlgorithmsectionPackage.eINSTANCE;
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
  protected AlgorithmsectionSwitch<Adapter> modelSwitch =
    new AlgorithmsectionSwitch<Adapter>()
    {
      @Override
      public Adapter casealgorithm_clause(algorithm_clause object)
      {
        return createalgorithm_clauseAdapter();
      }
      @Override
      public Adapter casestatement(statement object)
      {
        return createstatementAdapter();
      }
      @Override
      public Adapter caseAlgorithm_Macros_GEN_SIGNAL(Algorithm_Macros_GEN_SIGNAL object)
      {
        return createAlgorithm_Macros_GEN_SIGNALAdapter();
      }
      @Override
      public Adapter caseAlgorithm_Macros_GEN_INC(Algorithm_Macros_GEN_INC object)
      {
        return createAlgorithm_Macros_GEN_INCAdapter();
      }
      @Override
      public Adapter caseAlgorithm_Macros_GEN_CHANGE(Algorithm_Macros_GEN_CHANGE object)
      {
        return createAlgorithm_Macros_GEN_CHANGEAdapter();
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
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause <em>algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause
   * @generated
   */
  public Adapter createalgorithm_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement <em>statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement
   * @generated
   */
  public Adapter createstatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL <em>Algorithm Macros GEN SIGNAL</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_SIGNAL
   * @generated
   */
  public Adapter createAlgorithm_Macros_GEN_SIGNALAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC <em>Algorithm Macros GEN INC</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_INC
   * @generated
   */
  public Adapter createAlgorithm_Macros_GEN_INCAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE <em>Algorithm Macros GEN CHANGE</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.Algorithm_Macros_GEN_CHANGE
   * @generated
   */
  public Adapter createAlgorithm_Macros_GEN_CHANGEAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList <em>state Output Expr List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList
   * @generated
   */
  public Adapter createstate_OutputExprListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref <em>state Comp Ref</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_Comp_Ref
   * @generated
   */
  public Adapter createstate_Comp_RefAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement <em>when statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement
   * @generated
   */
  public Adapter createwhen_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement <em>while statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.while_statement
   * @generated
   */
  public Adapter createwhile_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement <em>for statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement
   * @generated
   */
  public Adapter createfor_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement <em>if statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.if_statement
   * @generated
   */
  public Adapter createif_statementAdapter()
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

} //AlgorithmsectionAdapterFactory
