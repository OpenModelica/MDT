/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationFactory;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationPackage;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.conditional_attribute;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.modification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeclarationFactoryImpl extends EFactoryImpl implements DeclarationFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DeclarationFactory init()
  {
    try
    {
      DeclarationFactory theDeclarationFactory = (DeclarationFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/declaration/Declaration"); 
      if (theDeclarationFactory != null)
      {
        return theDeclarationFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DeclarationFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeclarationFactoryImpl()
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
      case DeclarationPackage.COMPONENT_DECLARATION: return createcomponent_declaration();
      case DeclarationPackage.MODIFICATION: return createmodification();
      case DeclarationPackage.CONDITIONAL_ATTRIBUTE: return createconditional_attribute();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_declaration createcomponent_declaration()
  {
    component_declarationImpl component_declaration = new component_declarationImpl();
    return component_declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public modification createmodification()
  {
    modificationImpl modification = new modificationImpl();
    return modification;
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
  public DeclarationPackage getDeclarationPackage()
  {
    return (DeclarationPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static DeclarationPackage getPackage()
  {
    return DeclarationPackage.eINSTANCE;
  }

} //DeclarationFactoryImpl
