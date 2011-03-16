/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionFactory;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StatetransitionguardexpressionPackageImpl extends EPackageImpl implements StatetransitionguardexpressionPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expressionEClass = null;

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
  private EClass macro_AfterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass macro_BEFOREEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass macro_SIGNALEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass macro_EVENTEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass macro_CHANGEEClass = null;

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
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private StatetransitionguardexpressionPackageImpl()
  {
    super(eNS_URI, StatetransitionguardexpressionFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link StatetransitionguardexpressionPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static StatetransitionguardexpressionPackage init()
  {
    if (isInited) return (StatetransitionguardexpressionPackage)EPackage.Registry.INSTANCE.getEPackage(StatetransitionguardexpressionPackage.eNS_URI);

    // Obtain or create and register package
    StatetransitionguardexpressionPackageImpl theStatetransitionguardexpressionPackage = (StatetransitionguardexpressionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StatetransitionguardexpressionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StatetransitionguardexpressionPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    ModeleditorPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theStatetransitionguardexpressionPackage.createPackageContents();

    // Initialize created meta-data
    theStatetransitionguardexpressionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theStatetransitionguardexpressionPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(StatetransitionguardexpressionPackage.eNS_URI, theStatetransitionguardexpressionPackage);
    return theStatetransitionguardexpressionPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getexpression()
  {
    return expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getexpression_Simple_Expr()
  {
    return (EReference)expressionEClass.getEStructuralFeatures().get(0);
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
  public EReference getprimary_Mac_A()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprimary_Mac_E()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprimary_Mac_C()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprimary_Mac_S()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMacro_After()
  {
    return macro_AfterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMacro_After_Int()
  {
    return (EAttribute)macro_AfterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMacro_After_Num()
  {
    return (EAttribute)macro_AfterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMacro_After_Component_reference()
  {
    return (EReference)macro_AfterEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMacro_BEFORE()
  {
    return macro_BEFOREEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMacro_BEFORE_Int()
  {
    return (EAttribute)macro_BEFOREEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMacro_BEFORE_Num()
  {
    return (EAttribute)macro_BEFOREEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMacro_BEFORE_Component_reference()
  {
    return (EReference)macro_BEFOREEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMacro_SIGNAL()
  {
    return macro_SIGNALEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMacro_SIGNAL_Component_reference()
  {
    return (EReference)macro_SIGNALEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMacro_EVENT()
  {
    return macro_EVENTEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMacro_EVENT_Component_reference()
  {
    return (EReference)macro_EVENTEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMacro_CHANGE()
  {
    return macro_CHANGEEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMacro_CHANGE_Component_reference()
  {
    return (EReference)macro_CHANGEEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StatetransitionguardexpressionFactory getStatetransitionguardexpressionFactory()
  {
    return (StatetransitionguardexpressionFactory)getEFactoryInstance();
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
    expressionEClass = createEClass(EXPRESSION);
    createEReference(expressionEClass, EXPRESSION__SIMPLE_EXPR);

    primaryEClass = createEClass(PRIMARY);
    createEReference(primaryEClass, PRIMARY__NAME_FUNCTION);
    createEReference(primaryEClass, PRIMARY__INITIAL_REF);
    createEReference(primaryEClass, PRIMARY__EXPR);
    createEReference(primaryEClass, PRIMARY__MAC_A);
    createEReference(primaryEClass, PRIMARY__MAC_E);
    createEReference(primaryEClass, PRIMARY__MAC_C);
    createEReference(primaryEClass, PRIMARY__MAC_S);

    macro_AfterEClass = createEClass(MACRO_AFTER);
    createEAttribute(macro_AfterEClass, MACRO_AFTER__INT);
    createEAttribute(macro_AfterEClass, MACRO_AFTER__NUM);
    createEReference(macro_AfterEClass, MACRO_AFTER__COMPONENT_REFERENCE);

    macro_BEFOREEClass = createEClass(MACRO_BEFORE);
    createEAttribute(macro_BEFOREEClass, MACRO_BEFORE__INT);
    createEAttribute(macro_BEFOREEClass, MACRO_BEFORE__NUM);
    createEReference(macro_BEFOREEClass, MACRO_BEFORE__COMPONENT_REFERENCE);

    macro_SIGNALEClass = createEClass(MACRO_SIGNAL);
    createEReference(macro_SIGNALEClass, MACRO_SIGNAL__COMPONENT_REFERENCE);

    macro_EVENTEClass = createEClass(MACRO_EVENT);
    createEReference(macro_EVENTEClass, MACRO_EVENT__COMPONENT_REFERENCE);

    macro_CHANGEEClass = createEClass(MACRO_CHANGE);
    createEReference(macro_CHANGEEClass, MACRO_CHANGE__COMPONENT_REFERENCE);
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
    expressionEClass.getESuperTypes().add(theModeleditorPackage.getexpression());
    primaryEClass.getESuperTypes().add(theModeleditorPackage.getprimary());

    // Initialize classes and features; add operations and parameters
    initEClass(expressionEClass, expression.class, "expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getexpression_Simple_Expr(), theModeleditorPackage.getsimple_expression(), null, "simple_Expr", null, 0, 1, expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(primaryEClass, primary.class, "primary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getprimary_Name_Function(), theModeleditorPackage.getname_Function(), null, "Name_Function", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Initial_ref(), theModeleditorPackage.getinitial_ref(), null, "Initial_ref", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Expr(), theModeleditorPackage.getExprDer(), null, "Expr", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Mac_A(), this.getMacro_After(), null, "Mac_A", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Mac_E(), this.getMacro_EVENT(), null, "Mac_E", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Mac_C(), this.getMacro_CHANGE(), null, "Mac_C", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Mac_S(), this.getMacro_SIGNAL(), null, "Mac_S", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(macro_AfterEClass, Macro_After.class, "Macro_After", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMacro_After_Int(), ecorePackage.getEInt(), "int", null, 0, 1, Macro_After.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMacro_After_Num(), ecorePackage.getEString(), "num", null, 0, 1, Macro_After.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMacro_After_Component_reference(), theModeleditorPackage.getcomponent_reference(), null, "Component_reference", null, 0, 1, Macro_After.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(macro_BEFOREEClass, Macro_BEFORE.class, "Macro_BEFORE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMacro_BEFORE_Int(), ecorePackage.getEInt(), "int", null, 0, 1, Macro_BEFORE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMacro_BEFORE_Num(), ecorePackage.getEString(), "num", null, 0, 1, Macro_BEFORE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMacro_BEFORE_Component_reference(), theModeleditorPackage.getcomponent_reference(), null, "Component_reference", null, 0, 1, Macro_BEFORE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(macro_SIGNALEClass, Macro_SIGNAL.class, "Macro_SIGNAL", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMacro_SIGNAL_Component_reference(), theModeleditorPackage.getcomponent_reference(), null, "Component_reference", null, 0, 1, Macro_SIGNAL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(macro_EVENTEClass, Macro_EVENT.class, "Macro_EVENT", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMacro_EVENT_Component_reference(), theModeleditorPackage.getcomponent_reference(), null, "Component_reference", null, 0, 1, Macro_EVENT.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(macro_CHANGEEClass, Macro_CHANGE.class, "Macro_CHANGE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMacro_CHANGE_Component_reference(), theModeleditorPackage.getcomponent_reference(), null, "Component_reference", null, 0, 1, Macro_CHANGE.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //StatetransitionguardexpressionPackageImpl
