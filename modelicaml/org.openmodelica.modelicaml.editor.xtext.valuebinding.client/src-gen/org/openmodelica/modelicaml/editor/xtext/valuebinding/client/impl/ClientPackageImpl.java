/**
 * <copyright>
 * </copyright>
 *

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
  public EReference getClientOperation_Expr()
  {
    return (EReference)clientOperationEClass.getEStructuralFeatures().get(0);
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
  public EAttribute getprimary_Sum()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprimary_Prod()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprimary_Min()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprimary_Max()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprimary_Result()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(7);
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
    createEReference(clientOperationEClass, CLIENT_OPERATION__EXPR);

    primaryEClass = createEClass(PRIMARY);
    createEReference(primaryEClass, PRIMARY__NAME_FUNCTION);
    createEReference(primaryEClass, PRIMARY__INITIAL_REF);
    createEReference(primaryEClass, PRIMARY__EXPR);
    createEAttribute(primaryEClass, PRIMARY__SUM);
    createEAttribute(primaryEClass, PRIMARY__PROD);
    createEAttribute(primaryEClass, PRIMARY__MIN);
    createEAttribute(primaryEClass, PRIMARY__MAX);
    createEAttribute(primaryEClass, PRIMARY__RESULT);

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
    initEReference(getClientOperation_Expr(), theModeleditorPackage.getexpression(), null, "Expr", null, 0, 1, ClientOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(primaryEClass, primary.class, "primary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getprimary_Name_Function(), theModeleditorPackage.getname_Function(), null, "Name_Function", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Initial_ref(), theModeleditorPackage.getinitial_ref(), null, "Initial_ref", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Expr(), theModeleditorPackage.getExprDer(), null, "Expr", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getprimary_Sum(), ecorePackage.getEString(), "sum", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getprimary_Prod(), ecorePackage.getEString(), "prod", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getprimary_Min(), ecorePackage.getEString(), "min", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getprimary_Max(), ecorePackage.getEString(), "max", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getprimary_Result(), ecorePackage.getEString(), "result", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(component_referenceEClass, component_reference.class, "component_reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //ClientPackageImpl
