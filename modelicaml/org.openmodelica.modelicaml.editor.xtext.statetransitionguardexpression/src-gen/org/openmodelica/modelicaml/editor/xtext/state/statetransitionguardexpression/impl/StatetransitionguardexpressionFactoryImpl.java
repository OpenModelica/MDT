/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionFactory;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.guardExpression;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatetransitionguardexpressionFactoryImpl extends EFactoryImpl implements StatetransitionguardexpressionFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static StatetransitionguardexpressionFactory init()
  {
    try
    {
      StatetransitionguardexpressionFactory theStatetransitionguardexpressionFactory = (StatetransitionguardexpressionFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/state/Statetransitionguardexpression"); 
      if (theStatetransitionguardexpressionFactory != null)
      {
        return theStatetransitionguardexpressionFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new StatetransitionguardexpressionFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StatetransitionguardexpressionFactoryImpl()
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
      case StatetransitionguardexpressionPackage.GUARD_EXPRESSION: return createguardExpression();
      case StatetransitionguardexpressionPackage.EXPRESSION: return createexpression();
      case StatetransitionguardexpressionPackage.PRIMARY: return createprimary();
      case StatetransitionguardexpressionPackage.MACRO_AFTER: return createMacro_After();
      case StatetransitionguardexpressionPackage.MACRO_BEFORE: return createMacro_BEFORE();
      case StatetransitionguardexpressionPackage.MACRO_SIGNAL: return createMacro_SIGNAL();
      case StatetransitionguardexpressionPackage.MACRO_EVENT: return createMacro_EVENT();
      case StatetransitionguardexpressionPackage.MACRO_CHANGE: return createMacro_CHANGE();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public guardExpression createguardExpression()
  {
    guardExpressionImpl guardExpression = new guardExpressionImpl();
    return guardExpression;
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
  public primary createprimary()
  {
    primaryImpl primary = new primaryImpl();
    return primary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_After createMacro_After()
  {
    Macro_AfterImpl macro_After = new Macro_AfterImpl();
    return macro_After;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_BEFORE createMacro_BEFORE()
  {
    Macro_BEFOREImpl macro_BEFORE = new Macro_BEFOREImpl();
    return macro_BEFORE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_SIGNAL createMacro_SIGNAL()
  {
    Macro_SIGNALImpl macro_SIGNAL = new Macro_SIGNALImpl();
    return macro_SIGNAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_EVENT createMacro_EVENT()
  {
    Macro_EVENTImpl macro_EVENT = new Macro_EVENTImpl();
    return macro_EVENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_CHANGE createMacro_CHANGE()
  {
    Macro_CHANGEImpl macro_CHANGE = new Macro_CHANGEImpl();
    return macro_CHANGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StatetransitionguardexpressionPackage getStatetransitionguardexpressionPackage()
  {
    return (StatetransitionguardexpressionPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static StatetransitionguardexpressionPackage getPackage()
  {
    return StatetransitionguardexpressionPackage.eINSTANCE;
  }

} //StatetransitionguardexpressionFactoryImpl
