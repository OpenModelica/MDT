/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionFactory;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation_section;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.for_equation;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.if_equation;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EquationsectionFactoryImpl extends EFactoryImpl implements EquationsectionFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EquationsectionFactory init()
  {
    try
    {
      EquationsectionFactory theEquationsectionFactory = (EquationsectionFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openmodelica.org/modelicaml/editor/xtext/equation/Equationsection"); 
      if (theEquationsectionFactory != null)
      {
        return theEquationsectionFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EquationsectionFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EquationsectionFactoryImpl()
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
      case EquationsectionPackage.EQUATION_SECTION: return createequation_section();
      case EquationsectionPackage.EQUATION: return createequation();
      case EquationsectionPackage.WHEN_EQUATION: return createwhen_equation();
      case EquationsectionPackage.IF_EQUATION: return createif_equation();
      case EquationsectionPackage.FOR_EQUATION: return createfor_equation();
      case EquationsectionPackage.CONNECT_CLAUSE: return createconnect_clause();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public equation_section createequation_section()
  {
    equation_sectionImpl equation_section = new equation_sectionImpl();
    return equation_section;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public equation createequation()
  {
    equationImpl equation = new equationImpl();
    return equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public when_equation createwhen_equation()
  {
    when_equationImpl when_equation = new when_equationImpl();
    return when_equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public if_equation createif_equation()
  {
    if_equationImpl if_equation = new if_equationImpl();
    return if_equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_equation createfor_equation()
  {
    for_equationImpl for_equation = new for_equationImpl();
    return for_equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public connect_clause createconnect_clause()
  {
    connect_clauseImpl connect_clause = new connect_clauseImpl();
    return connect_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EquationsectionPackage getEquationsectionPackage()
  {
    return (EquationsectionPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static EquationsectionPackage getPackage()
  {
    return EquationsectionPackage.eINSTANCE;
  }

} //EquationsectionFactoryImpl
