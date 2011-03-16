/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.ArraysubscriptFactory;
import org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.ArraysubscriptPackage;
import org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.subscript;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ArraysubscriptFactoryImpl extends EFactoryImpl implements ArraysubscriptFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ArraysubscriptFactory init()
  {
    try
    {
      ArraysubscriptFactory theArraysubscriptFactory = (ArraysubscriptFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/array/Arraysubscript"); 
      if (theArraysubscriptFactory != null)
      {
        return theArraysubscriptFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ArraysubscriptFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArraysubscriptFactoryImpl()
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
      case ArraysubscriptPackage.SUBSCRIPT: return createsubscript();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public subscript createsubscript()
  {
    subscriptImpl subscript = new subscriptImpl();
    return subscript;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArraysubscriptPackage getArraysubscriptPackage()
  {
    return (ArraysubscriptPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ArraysubscriptPackage getPackage()
  {
    return ArraysubscriptPackage.eINSTANCE;
  }

} //ArraysubscriptFactoryImpl
