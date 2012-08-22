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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client;

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
 * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientFactory
 * @model kind="package"
 * @generated
 */
public interface ClientPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "client";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/valuebinding/Client";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "client";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ClientPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getClientOperation()
   * @generated
   */
  int CLIENT_OPERATION = 0;

  /**
   * The feature id for the '<em><b>Left hand</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLIENT_OPERATION__LEFT_HAND = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLIENT_OPERATION__EXPR = 1;

  /**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLIENT_OPERATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.left_hand_component_referenceImpl <em>left hand component reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.left_hand_component_referenceImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getleft_hand_component_reference()
   * @generated
   */
  int LEFT_HAND_COMPONENT_REFERENCE = 1;

  /**
   * The feature id for the '<em><b>Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__REF = 0;

  /**
   * The feature id for the '<em><b>Subscripts1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1 = 1;

  /**
   * The feature id for the '<em><b>Ref1</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__REF1 = 2;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS = 3;

  /**
   * The number of structural features of the '<em>left hand component reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEFT_HAND_COMPONENT_REFERENCE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl <em>primary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getprimary()
   * @generated
   */
  int PRIMARY = 2;

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
   * The feature id for the '<em><b>Binding</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__BINDING = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>primary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY_FEATURE_COUNT = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl <em>component reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getcomponent_reference()
   * @generated
   */
  int COMPONENT_REFERENCE = 3;

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
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation
   * @generated
   */
  EClass getClientOperation();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getLeft_hand <em>Left hand</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left hand</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getLeft_hand()
   * @see #getClientOperation()
   * @generated
   */
  EReference getClientOperation_Left_hand();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation#getExpr()
   * @see #getClientOperation()
   * @generated
   */
  EReference getClientOperation_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference <em>left hand component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>left hand component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference
   * @generated
   */
  EClass getleft_hand_component_reference();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getRef()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EAttribute getleft_hand_component_reference_Ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getSubscripts1 <em>Subscripts1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Subscripts1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getSubscripts1()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EReference getleft_hand_component_reference_Subscripts1();

  /**
   * Returns the meta object for the attribute list '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getRef1 <em>Ref1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Ref1</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getRef1()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EAttribute getleft_hand_component_reference_Ref1();

  /**
   * Returns the meta object for the containment reference list '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getSubscripts <em>Subscripts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Subscripts</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference#getSubscripts()
   * @see #getleft_hand_component_reference()
   * @generated
   */
  EReference getleft_hand_component_reference_Subscripts();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>primary</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary
   * @generated
   */
  EClass getprimary();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function <em>Name Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name Function</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getName_Function()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Name_Function();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref <em>Initial ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getInitial_ref()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Initial_ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getExpr()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Expr();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getBinding <em>Binding</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Binding</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary#getBinding()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Binding();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference <em>component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference
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
  ClientFactory getClientFactory();

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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getClientOperation()
     * @generated
     */
    EClass CLIENT_OPERATION = eINSTANCE.getClientOperation();

    /**
     * The meta object literal for the '<em><b>Left hand</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLIENT_OPERATION__LEFT_HAND = eINSTANCE.getClientOperation_Left_hand();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLIENT_OPERATION__EXPR = eINSTANCE.getClientOperation_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.left_hand_component_referenceImpl <em>left hand component reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.left_hand_component_referenceImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getleft_hand_component_reference()
     * @generated
     */
    EClass LEFT_HAND_COMPONENT_REFERENCE = eINSTANCE.getleft_hand_component_reference();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEFT_HAND_COMPONENT_REFERENCE__REF = eINSTANCE.getleft_hand_component_reference_Ref();

    /**
     * The meta object literal for the '<em><b>Subscripts1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1 = eINSTANCE.getleft_hand_component_reference_Subscripts1();

    /**
     * The meta object literal for the '<em><b>Ref1</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEFT_HAND_COMPONENT_REFERENCE__REF1 = eINSTANCE.getleft_hand_component_reference_Ref1();

    /**
     * The meta object literal for the '<em><b>Subscripts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS = eINSTANCE.getleft_hand_component_reference_Subscripts();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl <em>primary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getprimary()
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
     * The meta object literal for the '<em><b>Binding</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__BINDING = eINSTANCE.getprimary_Binding();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl <em>component reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.component_referenceImpl
     * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientPackageImpl#getcomponent_reference()
     * @generated
     */
    EClass COMPONENT_REFERENCE = eINSTANCE.getcomponent_reference();

  }

} //ClientPackage
