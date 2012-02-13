/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributeFactory;
import org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributePackage;
import org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.conditional_attribute;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConditionalattributeFactoryImpl extends EFactoryImpl implements ConditionalattributeFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConditionalattributeFactory init()
  {
    try
    {
      ConditionalattributeFactory theConditionalattributeFactory = (ConditionalattributeFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/condition/Conditionalattribute"); 
      if (theConditionalattributeFactory != null)
      {
        return theConditionalattributeFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ConditionalattributeFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalattributeFactoryImpl()
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
      case ConditionalattributePackage.CONDITIONAL_ATTRIBUTE: return createconditional_attribute();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_attribute createconditional_attribute()
  {
    conditional_attributeImpl conditional_attribute = new conditional_attributeImpl();
    return conditional_attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalattributePackage getConditionalattributePackage()
  {
    return (ConditionalattributePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ConditionalattributePackage getPackage()
  {
    return ConditionalattributePackage.eINSTANCE;
  }

} //ConditionalattributeFactoryImpl
