/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionFactory;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ActivitycontrolflowguardexpressionFactoryImpl extends EFactoryImpl implements ActivitycontrolflowguardexpressionFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ActivitycontrolflowguardexpressionFactory init()
  {
    try
    {
      ActivitycontrolflowguardexpressionFactory theActivitycontrolflowguardexpressionFactory = (ActivitycontrolflowguardexpressionFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/activity/Activitycontrolflowguardexpression"); 
      if (theActivitycontrolflowguardexpressionFactory != null)
      {
        return theActivitycontrolflowguardexpressionFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ActivitycontrolflowguardexpressionFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ActivitycontrolflowguardexpressionFactoryImpl()
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
      case ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION: return createactivityControlFlowExpression();
      case ActivitycontrolflowguardexpressionPackage.EXPRESSION: return createexpression();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public activityControlFlowExpression createactivityControlFlowExpression()
  {
    activityControlFlowExpressionImpl activityControlFlowExpression = new activityControlFlowExpressionImpl();
    return activityControlFlowExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression createexpression()
  {
    expressionImpl expression = new expressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ActivitycontrolflowguardexpressionPackage getActivitycontrolflowguardexpressionPackage()
  {
    return (ActivitycontrolflowguardexpressionPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ActivitycontrolflowguardexpressionPackage getPackage()
  {
    return ActivitycontrolflowguardexpressionPackage.eINSTANCE;
  }

} //ActivitycontrolflowguardexpressionFactoryImpl
