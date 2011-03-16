/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributeFactory;
import org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributePackage;
import org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.conditional_attribute;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConditionalattributePackageImpl extends EPackageImpl implements ConditionalattributePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditional_attributeEClass = null;

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
   * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ConditionalattributePackageImpl()
  {
    super(eNS_URI, ConditionalattributeFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ConditionalattributePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ConditionalattributePackage init()
  {
    if (isInited) return (ConditionalattributePackage)EPackage.Registry.INSTANCE.getEPackage(ConditionalattributePackage.eNS_URI);

    // Obtain or create and register package
    ConditionalattributePackageImpl theConditionalattributePackage = (ConditionalattributePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConditionalattributePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConditionalattributePackageImpl());

    isInited = true;

    // Initialize simple dependencies
    ModeleditorPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theConditionalattributePackage.createPackageContents();

    // Initialize created meta-data
    theConditionalattributePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theConditionalattributePackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ConditionalattributePackage.eNS_URI, theConditionalattributePackage);
    return theConditionalattributePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconditional_attribute()
  {
    return conditional_attributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconditional_attribute_Expr()
  {
    return (EReference)conditional_attributeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalattributeFactory getConditionalattributeFactory()
  {
    return (ConditionalattributeFactory)getEFactoryInstance();
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
    conditional_attributeEClass = createEClass(CONDITIONAL_ATTRIBUTE);
    createEReference(conditional_attributeEClass, CONDITIONAL_ATTRIBUTE__EXPR);
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

    // Initialize classes and features; add operations and parameters
    initEClass(conditional_attributeEClass, conditional_attribute.class, "conditional_attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getconditional_attribute_Expr(), theModeleditorPackage.getexpression(), null, "expr", null, 0, 1, conditional_attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ConditionalattributePackageImpl
