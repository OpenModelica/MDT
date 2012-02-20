/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.factor;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.term;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.guardExpression;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage
 * @generated
 */
public class StatetransitionguardexpressionAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static StatetransitionguardexpressionPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StatetransitionguardexpressionAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = StatetransitionguardexpressionPackage.eINSTANCE;
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
  protected StatetransitionguardexpressionSwitch<Adapter> modelSwitch =
    new StatetransitionguardexpressionSwitch<Adapter>()
    {
      @Override
      public Adapter caseguardExpression(guardExpression object)
      {
        return createguardExpressionAdapter();
      }
      @Override
      public Adapter caseexpression(expression object)
      {
        return createexpressionAdapter();
      }
      @Override
      public Adapter caseprimary(primary object)
      {
        return createprimaryAdapter();
      }
      @Override
      public Adapter caseMacro_After(Macro_After object)
      {
        return createMacro_AfterAdapter();
      }
      @Override
      public Adapter caseMacro_BEFORE(Macro_BEFORE object)
      {
        return createMacro_BEFOREAdapter();
      }
      @Override
      public Adapter caseMacro_SIGNAL(Macro_SIGNAL object)
      {
        return createMacro_SIGNALAdapter();
      }
      @Override
      public Adapter caseMacro_EVENT(Macro_EVENT object)
      {
        return createMacro_EVENTAdapter();
      }
      @Override
      public Adapter caseMacro_CHANGE(Macro_CHANGE object)
      {
        return createMacro_CHANGEAdapter();
      }
      @Override
      public Adapter caseModeleditor_expression(org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression object)
      {
        return createModeleditor_expressionAdapter();
      }
      @Override
      public Adapter caseterm(term object)
      {
        return createtermAdapter();
      }
      @Override
      public Adapter casefactor(factor object)
      {
        return createfactorAdapter();
      }
      @Override
      public Adapter caseModeleditor_primary(org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary object)
      {
        return createModeleditor_primaryAdapter();
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
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.guardExpression <em>guard Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.guardExpression
   * @generated
   */
  public Adapter createguardExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression
   * @generated
   */
  public Adapter createexpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary
   * @generated
   */
  public Adapter createprimaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After <em>Macro After</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After
   * @generated
   */
  public Adapter createMacro_AfterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE <em>Macro BEFORE</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE
   * @generated
   */
  public Adapter createMacro_BEFOREAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL <em>Macro SIGNAL</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL
   * @generated
   */
  public Adapter createMacro_SIGNALAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT <em>Macro EVENT</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT
   * @generated
   */
  public Adapter createMacro_EVENTAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE <em>Macro CHANGE</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE
   * @generated
   */
  public Adapter createMacro_CHANGEAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.expression
   * @generated
   */
  public Adapter createModeleditor_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.term <em>term</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.term
   * @generated
   */
  public Adapter createtermAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.factor <em>factor</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.factor
   * @generated
   */
  public Adapter createfactorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link modeleditor.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see modeleditor.primary
   * @generated
   */
  public Adapter createModeleditor_primaryAdapter()
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

} //StatetransitionguardexpressionAdapterFactory
