/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage
 * @generated
 */
public class ActivitycontrolflowguardexpressionAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ActivitycontrolflowguardexpressionPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ActivitycontrolflowguardexpressionAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ActivitycontrolflowguardexpressionPackage.eINSTANCE;
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
  protected ActivitycontrolflowguardexpressionSwitch<Adapter> modelSwitch =
    new ActivitycontrolflowguardexpressionSwitch<Adapter>()
    {
      @Override
      public Adapter caseactivityControlFlowExpression(activityControlFlowExpression object)
      {
        return createactivityControlFlowExpressionAdapter();
      }
      @Override
      public Adapter caseexpression(expression object)
      {
        return createexpressionAdapter();
      }
      @Override
      public Adapter caseModeleditor_expression(org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression object)
      {
        return createModeleditor_expressionAdapter();
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
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression <em>activity Control Flow Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression
   * @generated
   */
  public Adapter createactivityControlFlowExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression
   * @generated
   */
  public Adapter createexpressionAdapter()
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

} //ActivitycontrolflowguardexpressionAdapterFactory
