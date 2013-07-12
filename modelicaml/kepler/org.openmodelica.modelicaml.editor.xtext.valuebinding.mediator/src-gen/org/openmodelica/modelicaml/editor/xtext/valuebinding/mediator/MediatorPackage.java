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
package org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorFactory
 * @model kind="package"
 * @generated
 */
public interface MediatorPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "mediator";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/valuebinding/Mediator";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "mediator";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MediatorPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorOperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorOperationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorPackageImpl#getMediatorOperation()
   * @generated
   */
  int MEDIATOR_OPERATION = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MEDIATOR_OPERATION__EXPR = 0;

  /**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MEDIATOR_OPERATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl <em>primary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorPackageImpl#getprimary()
   * @generated
   */
  int PRIMARY = 1;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OP = ModeleditorPackage.PRIMARY__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FACTOR = ModeleditorPackage.PRIMARY__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__PRIMARY = ModeleditorPackage.PRIMARY__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NUM = ModeleditorPackage.PRIMARY__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__INT = ModeleditorPackage.PRIMARY__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__STR = ModeleditorPackage.PRIMARY__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__BOOL = ModeleditorPackage.PRIMARY__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__COMPONENT_REFERENCE = ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OUTPUT_EXPR_LIST = ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRE_LIST = ModeleditorPackage.PRIMARY__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRESSION_LIST = ModeleditorPackage.PRIMARY__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FARGUMENTS = ModeleditorPackage.PRIMARY__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__END = ModeleditorPackage.PRIMARY__END;

  /**
   * The feature id for the '<em><b>Name Function</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NAME_FUNCTION = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Initial ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__INITIAL_REF = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPR = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Sum</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__SUM = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Prod</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__PROD = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MIN = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MAX = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Avg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__AVG = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__SIZE = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>To Array</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__TO_ARRAY = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>And</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__AND = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 10;

  /**
   * The feature id for the '<em><b>Or</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OR = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 11;

  /**
   * The feature id for the '<em><b>Xor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__XOR = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 12;

  /**
   * The feature id for the '<em><b>Single Provider</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__SINGLE_PROVIDER = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 13;

  /**
   * The number of structural features of the '<em>primary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY_FEATURE_COUNT = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 14;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.component_referenceImpl <em>component reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.component_referenceImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorPackageImpl#getcomponent_reference()
   * @generated
   */
  int COMPONENT_REFERENCE = 2;

  /**
   * The feature id for the '<em><b>Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__REF = ModeleditorPackage.COMPONENT_REFERENCE__REF;

  /**
   * The feature id for the '<em><b>Subscripts1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__SUBSCRIPTS1 = ModeleditorPackage.COMPONENT_REFERENCE__SUBSCRIPTS1;

  /**
   * The feature id for the '<em><b>Ref1</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__REF1 = ModeleditorPackage.COMPONENT_REFERENCE__REF1;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE__SUBSCRIPTS = ModeleditorPackage.COMPONENT_REFERENCE__SUBSCRIPTS;

  /**
   * The number of structural features of the '<em>component reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_REFERENCE_FEATURE_COUNT = ModeleditorPackage.COMPONENT_REFERENCE_FEATURE_COUNT + 0;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorOperation
   * @generated
   */
  EClass getMediatorOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorOperation#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorOperation#getExpr()
   * @see #getMediatorOperation()
   * @generated
   */
  EReference getMediatorOperation_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>primary</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary
   * @generated
   */
  EClass getprimary();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getName_Function <em>Name Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name Function</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getName_Function()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Name_Function();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getInitial_ref <em>Initial ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getInitial_ref()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Initial_ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getExpr()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Expr();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSum <em>Sum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sum</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSum()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Sum();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getProd <em>Prod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Prod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getProd()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Prod();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMin <em>Min</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Min</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMin()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Min();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMax <em>Max</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Max</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getMax()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Max();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAvg <em>Avg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Avg</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAvg()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Avg();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSize <em>Size</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Size</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSize()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Size();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getToArray <em>To Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>To Array</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getToArray()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_ToArray();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAnd <em>And</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>And</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getAnd()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_And();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getOr <em>Or</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Or</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getOr()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Or();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getXor <em>Xor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xor</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getXor()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Xor();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSingleProvider <em>Single Provider</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Single Provider</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary#getSingleProvider()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_SingleProvider();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.component_reference <em>component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.component_reference
   * @generated
   */
  EClass getcomponent_reference();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MediatorFactory getMediatorFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorOperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorOperationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorPackageImpl#getMediatorOperation()
     * @generated
     */
    EClass MEDIATOR_OPERATION = eINSTANCE.getMediatorOperation();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MEDIATOR_OPERATION__EXPR = eINSTANCE.getMediatorOperation_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl <em>primary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorPackageImpl#getprimary()
     * @generated
     */
    EClass PRIMARY = eINSTANCE.getprimary();

    /**
     * The meta object literal for the '<em><b>Name Function</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__NAME_FUNCTION = eINSTANCE.getprimary_Name_Function();

    /**
     * The meta object literal for the '<em><b>Initial ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__INITIAL_REF = eINSTANCE.getprimary_Initial_ref();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__EXPR = eINSTANCE.getprimary_Expr();

    /**
     * The meta object literal for the '<em><b>Sum</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__SUM = eINSTANCE.getprimary_Sum();

    /**
     * The meta object literal for the '<em><b>Prod</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__PROD = eINSTANCE.getprimary_Prod();

    /**
     * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__MIN = eINSTANCE.getprimary_Min();

    /**
     * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__MAX = eINSTANCE.getprimary_Max();

    /**
     * The meta object literal for the '<em><b>Avg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__AVG = eINSTANCE.getprimary_Avg();

    /**
     * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__SIZE = eINSTANCE.getprimary_Size();

    /**
     * The meta object literal for the '<em><b>To Array</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__TO_ARRAY = eINSTANCE.getprimary_ToArray();

    /**
     * The meta object literal for the '<em><b>And</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__AND = eINSTANCE.getprimary_And();

    /**
     * The meta object literal for the '<em><b>Or</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__OR = eINSTANCE.getprimary_Or();

    /**
     * The meta object literal for the '<em><b>Xor</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__XOR = eINSTANCE.getprimary_Xor();

    /**
     * The meta object literal for the '<em><b>Single Provider</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__SINGLE_PROVIDER = eINSTANCE.getprimary_SingleProvider();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.component_referenceImpl <em>component reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.component_referenceImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.MediatorPackageImpl#getcomponent_reference()
     * @generated
     */
    EClass COMPONENT_REFERENCE = eINSTANCE.getcomponent_reference();

  }

} //MediatorPackage
