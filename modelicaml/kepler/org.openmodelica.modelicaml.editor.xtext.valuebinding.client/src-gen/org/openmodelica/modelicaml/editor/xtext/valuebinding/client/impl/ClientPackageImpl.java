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
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientFactory;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.component_reference;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ClientPackageImpl extends EPackageImpl implements ClientPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass clientOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass left_hand_component_referenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass primaryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass component_referenceEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ClientPackageImpl()
  {
    super(eNS_URI, ClientFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link ClientPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ClientPackage init()
  {
    if (isInited) return (ClientPackage)EPackage.Registry.INSTANCE.getEPackage(ClientPackage.eNS_URI);

    // Obtain or create and register package
    ClientPackageImpl theClientPackage = (ClientPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ClientPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ClientPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    ModeleditorPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theClientPackage.createPackageContents();

    // Initialize created meta-data
    theClientPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theClientPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ClientPackage.eNS_URI, theClientPackage);
    return theClientPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClientOperation()
  {
    return clientOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClientOperation_Left_hand()
  {
    return (EReference)clientOperationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClientOperation_Expr()
  {
    return (EReference)clientOperationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getleft_hand_component_reference()
  {
    return left_hand_component_referenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getleft_hand_component_reference_Ref()
  {
    return (EAttribute)left_hand_component_referenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getleft_hand_component_reference_Subscripts1()
  {
    return (EReference)left_hand_component_referenceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getleft_hand_component_reference_Ref1()
  {
    return (EAttribute)left_hand_component_referenceEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getleft_hand_component_reference_Subscripts()
  {
    return (EReference)left_hand_component_referenceEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getprimary()
  {
    return primaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprimary_Name_Function()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprimary_Initial_ref()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprimary_Expr()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprimary_Binding()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcomponent_reference()
  {
    return component_referenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClientFactory getClientFactory()
  {
    return (ClientFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    clientOperationEClass = createEClass(CLIENT_OPERATION);
    createEReference(clientOperationEClass, CLIENT_OPERATION__LEFT_HAND);
    createEReference(clientOperationEClass, CLIENT_OPERATION__EXPR);

    left_hand_component_referenceEClass = createEClass(LEFT_HAND_COMPONENT_REFERENCE);
    createEAttribute(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__REF);
    createEReference(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1);
    createEAttribute(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__REF1);
    createEReference(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS);

    primaryEClass = createEClass(PRIMARY);
    createEReference(primaryEClass, PRIMARY__NAME_FUNCTION);
    createEReference(primaryEClass, PRIMARY__INITIAL_REF);
    createEReference(primaryEClass, PRIMARY__EXPR);
    createEAttribute(primaryEClass, PRIMARY__BINDING);

    component_referenceEClass = createEClass(COMPONENT_REFERENCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    ModeleditorPackage theModeleditorPackage = (ModeleditorPackage)EPackage.Registry.INSTANCE.getEPackage(ModeleditorPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    primaryEClass.getESuperTypes().add(theModeleditorPackage.getprimary());
    component_referenceEClass.getESuperTypes().add(theModeleditorPackage.getcomponent_reference());

    // Initialize classes and features; add operations and parameters
    initEClass(clientOperationEClass, ClientOperation.class, "ClientOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getClientOperation_Left_hand(), this.getleft_hand_component_reference(), null, "left_hand", null, 0, 1, ClientOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClientOperation_Expr(), theModeleditorPackage.getexpression(), null, "Expr", null, 0, 1, ClientOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(left_hand_component_referenceEClass, left_hand_component_reference.class, "left_hand_component_reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getleft_hand_component_reference_Ref(), ecorePackage.getEString(), "ref", null, 0, 1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getleft_hand_component_reference_Subscripts1(), theModeleditorPackage.getarray_subscripts(), null, "subscripts1", null, 0, 1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getleft_hand_component_reference_Ref1(), ecorePackage.getEString(), "ref1", null, 0, -1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getleft_hand_component_reference_Subscripts(), theModeleditorPackage.getarray_subscripts(), null, "subscripts", null, 0, -1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(primaryEClass, primary.class, "primary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getprimary_Name_Function(), theModeleditorPackage.getname_Function(), null, "Name_Function", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Initial_ref(), theModeleditorPackage.getinitial_ref(), null, "Initial_ref", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Expr(), theModeleditorPackage.getExprDer(), null, "Expr", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getprimary_Binding(), ecorePackage.getEString(), "binding", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(component_referenceEClass, component_reference.class, "component_reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //ClientPackageImpl
