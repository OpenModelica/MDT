/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.modelica.xtext.modelicaBase.Annotation;
import org.modelica.xtext.modelicaBase.ClassDefinition;
import org.modelica.xtext.modelicaBase.ClassElement;
import org.modelica.xtext.modelicaBase.ClassModification;
import org.modelica.xtext.modelicaBase.ClassOverloading;
import org.modelica.xtext.modelicaBase.ClassType;
import org.modelica.xtext.modelicaBase.Comment;
import org.modelica.xtext.modelicaBase.ComponentClause;
import org.modelica.xtext.modelicaBase.ComponentDeclaration;
import org.modelica.xtext.modelicaBase.ConditionAlgorithmRule;
import org.modelica.xtext.modelicaBase.ConditionalEquationRule;
import org.modelica.xtext.modelicaBase.ConstrainingClause;
import org.modelica.xtext.modelicaBase.ElementComponentClause;
import org.modelica.xtext.modelicaBase.ElementList;
import org.modelica.xtext.modelicaBase.ElementModification;
import org.modelica.xtext.modelicaBase.ElementRedeclaration;
import org.modelica.xtext.modelicaBase.ElementReplaceableClause;
import org.modelica.xtext.modelicaBase.EntityDefinition;
import org.modelica.xtext.modelicaBase.Enumeration;
import org.modelica.xtext.modelicaBase.EquationClause;
import org.modelica.xtext.modelicaBase.ExtendsClause;
import org.modelica.xtext.modelicaBase.ExternalClause;
import org.modelica.xtext.modelicaBase.ForAlgorithmClause;
import org.modelica.xtext.modelicaBase.ForEquationClause;
import org.modelica.xtext.modelicaBase.ImportClause;
import org.modelica.xtext.modelicaBase.ModelicaBaseFactory;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.Modification;
import org.modelica.xtext.modelicaBase.PartialDerivative;
import org.modelica.xtext.modelicaBase.ProtectesElementList;
import org.modelica.xtext.modelicaBase.PublicElementList;
import org.modelica.xtext.modelicaBase.ReplaceableElementClause;
import org.modelica.xtext.modelicaBase.SimpleModification;
import org.modelica.xtext.modelicaBase.StorageUnit;
import org.modelica.xtext.modelicaBase.TypeSpecifier;
import org.modelica.xtext.modelicaBase.WhileClause;
import org.modelica.xtext.modelicaBase.algorithm;
import org.modelica.xtext.modelicaBase.algorithm_clause;
import org.modelica.xtext.modelicaBase.algorithm_elseif;
import org.modelica.xtext.modelicaBase.argument;
import org.modelica.xtext.modelicaBase.else_when_a;
import org.modelica.xtext.modelicaBase.else_when_e;
import org.modelica.xtext.modelicaBase.equation_rule;
import org.modelica.xtext.modelicaBase.equation_rule_elseif;
import org.modelica.xtext.modelicaBase.initial_algorithm_clause;
import org.modelica.xtext.modelicaBase.initial_equation_rule_clause;
import org.modelica.xtext.modelicaBase.when_clause_a;
import org.modelica.xtext.modelicaBase.when_clause_e;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelicaBasePackageImpl extends EPackageImpl implements ModelicaBasePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass storageUnitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass entityDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass classDefinitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass partialDerivativeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass classModificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass classOverloadingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumerationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass externalClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass classElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass replaceableElementClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass importClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendsClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constrainingClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass componentClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeSpecifierEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass componentDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simpleModificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argumentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementModificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementRedeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementReplaceableClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementComponentClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass equationClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass algorithm_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass initial_equation_rule_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass initial_algorithm_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass equation_ruleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass algorithmEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditionalEquationRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditionAlgorithmRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass forEquationClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass forAlgorithmClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass whileClauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass when_clause_eEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass else_when_eEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass when_clause_aEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass else_when_aEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass equation_rule_elseifEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass algorithm_elseifEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass commentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass publicElementListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass protectesElementListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum classTypeEEnum = null;

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ModelicaBasePackageImpl()
  {
    super(eNS_URI, ModelicaBaseFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ModelicaBasePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ModelicaBasePackage init()
  {
    if (isInited) return (ModelicaBasePackage)EPackage.Registry.INSTANCE.getEPackage(ModelicaBasePackage.eNS_URI);

    // Obtain or create and register package
    ModelicaBasePackageImpl theModelicaBasePackage = (ModelicaBasePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelicaBasePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelicaBasePackageImpl());

    isInited = true;

    // Create package meta-data objects
    theModelicaBasePackage.createPackageContents();

    // Initialize created meta-data
    theModelicaBasePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theModelicaBasePackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ModelicaBasePackage.eNS_URI, theModelicaBasePackage);
    return theModelicaBasePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStorageUnit()
  {
    return storageUnitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStorageUnit_ClassDefinition()
  {
    return (EReference)storageUnitEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEntityDefinition()
  {
    return entityDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEntityDefinition_Name()
  {
    return (EAttribute)entityDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClassDefinition()
  {
    return classDefinitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassDefinition_Encapsulated()
  {
    return (EAttribute)classDefinitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassDefinition_Partial()
  {
    return (EAttribute)classDefinitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassDefinition_Expandable()
  {
    return (EAttribute)classDefinitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassDefinition_ClassType()
  {
    return (EAttribute)classDefinitionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassDefinition_Comment()
  {
    return (EAttribute)classDefinitionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassDefinition_Modification()
  {
    return (EReference)classDefinitionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassDefinition_ElemList()
  {
    return (EReference)classDefinitionEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassDefinition_InitialEquationClauses()
  {
    return (EReference)classDefinitionEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassDefinition_InitialAlgorithmClauses()
  {
    return (EReference)classDefinitionEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassDefinition_EquationClauses()
  {
    return (EReference)classDefinitionEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassDefinition_AlgorithmClauses()
  {
    return (EReference)classDefinitionEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassDefinition_ExtClause()
  {
    return (EReference)classDefinitionEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPartialDerivative()
  {
    return partialDerivativeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPartialDerivative_Comment()
  {
    return (EReference)partialDerivativeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClassModification()
  {
    return classModificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassModification_ClassType()
  {
    return (EAttribute)classModificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassModification_TypeSpecifier()
  {
    return (EReference)classModificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassModification_Modification()
  {
    return (EReference)classModificationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassModification_Comment()
  {
    return (EReference)classModificationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassModification_Arguments()
  {
    return (EReference)classModificationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClassOverloading()
  {
    return classOverloadingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClassOverloading_ClassType()
  {
    return (EAttribute)classOverloadingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClassOverloading_Comment()
  {
    return (EReference)classOverloadingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumeration()
  {
    return enumerationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumeration_ClassType()
  {
    return (EAttribute)enumerationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumeration_Ident()
  {
    return (EAttribute)enumerationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumeration_EnumComments()
  {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumeration_Comment()
  {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExternalClause()
  {
    return externalClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExternalClause_LanguageSpecification()
  {
    return (EAttribute)externalClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExternalClause_Annotation()
  {
    return (EReference)externalClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExternalClause_ExtAnnotation()
  {
    return (EReference)externalClauseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getElementList()
  {
    return elementListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementList_Elements()
  {
    return (EReference)elementListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementList_Annotations()
  {
    return (EReference)elementListEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClassElement()
  {
    return classElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getReplaceableElementClause()
  {
    return replaceableElementClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReplaceableElementClause_Entity()
  {
    return (EReference)replaceableElementClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReplaceableElementClause_Constrain()
  {
    return (EReference)replaceableElementClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImportClause()
  {
    return importClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImportClause_AsName()
  {
    return (EAttribute)importClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImportClause_Name()
  {
    return (EAttribute)importClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImportClause_Comment()
  {
    return (EReference)importClauseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtendsClause()
  {
    return extendsClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendsClause_BaseClass()
  {
    return (EAttribute)extendsClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtendsClause_Modification()
  {
    return (EReference)extendsClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtendsClause_Annotation()
  {
    return (EReference)extendsClauseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstrainingClause()
  {
    return constrainingClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstrainingClause_Name()
  {
    return (EAttribute)constrainingClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstrainingClause_Modification()
  {
    return (EReference)constrainingClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstrainingClause_Comment()
  {
    return (EReference)constrainingClauseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getComponentClause()
  {
    return componentClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComponentClause_TypePrefix()
  {
    return (EAttribute)componentClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComponentClause_TypeSpecifier()
  {
    return (EReference)componentClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComponentClause_Declarations()
  {
    return (EReference)componentClauseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTypeSpecifier()
  {
    return typeSpecifierEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTypeSpecifier_Name()
  {
    return (EAttribute)typeSpecifierEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTypeSpecifier_Subscripts()
  {
    return (EAttribute)typeSpecifierEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getComponentDeclaration()
  {
    return componentDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComponentDeclaration_Subscripts()
  {
    return (EAttribute)componentDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComponentDeclaration_Modification()
  {
    return (EReference)componentDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComponentDeclaration_Condition()
  {
    return (EAttribute)componentDeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComponentDeclaration_Comment()
  {
    return (EReference)componentDeclarationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModification()
  {
    return modificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModification_Expression()
  {
    return (EAttribute)modificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSimpleModification()
  {
    return simpleModificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getargument()
  {
    return argumentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getElementModification()
  {
    return elementModificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getElementModification_ComponetRef()
  {
    return (EAttribute)elementModificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementModification_Modification()
  {
    return (EReference)elementModificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getElementModification_Comment()
  {
    return (EAttribute)elementModificationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getElementRedeclaration()
  {
    return elementRedeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getElementReplaceableClause()
  {
    return elementReplaceableClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementReplaceableClause_Element()
  {
    return (EReference)elementReplaceableClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementReplaceableClause_Constraint()
  {
    return (EReference)elementReplaceableClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getElementComponentClause()
  {
    return elementComponentClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getElementComponentClause_TypePrefix()
  {
    return (EAttribute)elementComponentClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementComponentClause_TypeSpecifier()
  {
    return (EReference)elementComponentClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getElementComponentClause_Subscripts()
  {
    return (EAttribute)elementComponentClauseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementComponentClause_Modification()
  {
    return (EReference)elementComponentClauseEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getElementComponentClause_Comment()
  {
    return (EReference)elementComponentClauseEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEquationClause()
  {
    return equationClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEquationClause_EquationRules()
  {
    return (EReference)equationClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEquationClause_Annotations()
  {
    return (EReference)equationClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getalgorithm_clause()
  {
    return algorithm_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getalgorithm_clause_AlgorithmRules()
  {
    return (EReference)algorithm_clauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getalgorithm_clause_Annotations()
  {
    return (EReference)algorithm_clauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getinitial_equation_rule_clause()
  {
    return initial_equation_rule_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getinitial_algorithm_clause()
  {
    return initial_algorithm_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getequation_rule()
  {
    return equation_ruleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_rule_Comment()
  {
    return (EReference)equation_ruleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getalgorithm()
  {
    return algorithmEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getalgorithm_Comment()
  {
    return (EReference)algorithmEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConditionalEquationRule()
  {
    return conditionalEquationRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalEquationRule_ThenEquationClauses()
  {
    return (EReference)conditionalEquationRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalEquationRule_ElseifClauses()
  {
    return (EReference)conditionalEquationRuleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalEquationRule_ElseEquationClauses()
  {
    return (EReference)conditionalEquationRuleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConditionAlgorithmRule()
  {
    return conditionAlgorithmRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConditionAlgorithmRule_Condition()
  {
    return (EAttribute)conditionAlgorithmRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionAlgorithmRule_ThenAlgorithmClauses()
  {
    return (EReference)conditionAlgorithmRuleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionAlgorithmRule_ElseifClauses()
  {
    return (EReference)conditionAlgorithmRuleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionAlgorithmRule_ElseAlgotithmClauses()
  {
    return (EReference)conditionAlgorithmRuleEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getForEquationClause()
  {
    return forEquationClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getForEquationClause_ForIndices()
  {
    return (EAttribute)forEquationClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForEquationClause_EquationClauses()
  {
    return (EReference)forEquationClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getForAlgorithmClause()
  {
    return forAlgorithmClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getForAlgorithmClause_ForIndices()
  {
    return (EAttribute)forAlgorithmClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForAlgorithmClause_AlgorithmClauses()
  {
    return (EReference)forAlgorithmClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWhileClause()
  {
    return whileClauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWhileClause_Condition()
  {
    return (EAttribute)whileClauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWhileClause_AlgorithmClauses()
  {
    return (EReference)whileClauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getwhen_clause_e()
  {
    return when_clause_eEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getwhen_clause_e_Condition()
  {
    return (EAttribute)when_clause_eEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_clause_e_ThenEquationClauses()
  {
    return (EReference)when_clause_eEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_clause_e_ElseWhenClauses()
  {
    return (EReference)when_clause_eEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelse_when_e()
  {
    return else_when_eEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getelse_when_e_Condition()
  {
    return (EAttribute)else_when_eEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelse_when_e_ThenEquationClauses()
  {
    return (EReference)else_when_eEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getwhen_clause_a()
  {
    return when_clause_aEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getwhen_clause_a_Condition()
  {
    return (EAttribute)when_clause_aEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_clause_a_ThenAlgorithmClauses()
  {
    return (EReference)when_clause_aEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_clause_a_ElseWhenClauses()
  {
    return (EReference)when_clause_aEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelse_when_a()
  {
    return else_when_aEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getelse_when_a_Condition()
  {
    return (EAttribute)else_when_aEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelse_when_a_ThenAlgorithmClauses()
  {
    return (EReference)else_when_aEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getequation_rule_elseif()
  {
    return equation_rule_elseifEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getequation_rule_elseif_Condition()
  {
    return (EAttribute)equation_rule_elseifEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_rule_elseif_ThenEquationClauses()
  {
    return (EReference)equation_rule_elseifEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getalgorithm_elseif()
  {
    return algorithm_elseifEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getalgorithm_elseif_Condition()
  {
    return (EAttribute)algorithm_elseifEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getalgorithm_elseif_ThenAlgorithmClauses()
  {
    return (EReference)algorithm_elseifEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getComment()
  {
    return commentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getComment_String()
  {
    return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getComment_Annotation()
  {
    return (EReference)commentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotation()
  {
    return annotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPublicElementList()
  {
    return publicElementListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProtectesElementList()
  {
    return protectesElementListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getClassType()
  {
    return classTypeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelicaBaseFactory getModelicaBaseFactory()
  {
    return (ModelicaBaseFactory)getEFactoryInstance();
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
    storageUnitEClass = createEClass(STORAGE_UNIT);
    createEReference(storageUnitEClass, STORAGE_UNIT__CLASS_DEFINITION);

    entityDefinitionEClass = createEClass(ENTITY_DEFINITION);
    createEAttribute(entityDefinitionEClass, ENTITY_DEFINITION__NAME);

    classDefinitionEClass = createEClass(CLASS_DEFINITION);
    createEAttribute(classDefinitionEClass, CLASS_DEFINITION__ENCAPSULATED);
    createEAttribute(classDefinitionEClass, CLASS_DEFINITION__PARTIAL);
    createEAttribute(classDefinitionEClass, CLASS_DEFINITION__EXPANDABLE);
    createEAttribute(classDefinitionEClass, CLASS_DEFINITION__CLASS_TYPE);
    createEAttribute(classDefinitionEClass, CLASS_DEFINITION__COMMENT);
    createEReference(classDefinitionEClass, CLASS_DEFINITION__MODIFICATION);
    createEReference(classDefinitionEClass, CLASS_DEFINITION__ELEM_LIST);
    createEReference(classDefinitionEClass, CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES);
    createEReference(classDefinitionEClass, CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES);
    createEReference(classDefinitionEClass, CLASS_DEFINITION__EQUATION_CLAUSES);
    createEReference(classDefinitionEClass, CLASS_DEFINITION__ALGORITHM_CLAUSES);
    createEReference(classDefinitionEClass, CLASS_DEFINITION__EXT_CLAUSE);

    partialDerivativeEClass = createEClass(PARTIAL_DERIVATIVE);
    createEReference(partialDerivativeEClass, PARTIAL_DERIVATIVE__COMMENT);

    classModificationEClass = createEClass(CLASS_MODIFICATION);
    createEAttribute(classModificationEClass, CLASS_MODIFICATION__CLASS_TYPE);
    createEReference(classModificationEClass, CLASS_MODIFICATION__TYPE_SPECIFIER);
    createEReference(classModificationEClass, CLASS_MODIFICATION__MODIFICATION);
    createEReference(classModificationEClass, CLASS_MODIFICATION__COMMENT);
    createEReference(classModificationEClass, CLASS_MODIFICATION__ARGUMENTS);

    classOverloadingEClass = createEClass(CLASS_OVERLOADING);
    createEAttribute(classOverloadingEClass, CLASS_OVERLOADING__CLASS_TYPE);
    createEReference(classOverloadingEClass, CLASS_OVERLOADING__COMMENT);

    enumerationEClass = createEClass(ENUMERATION);
    createEAttribute(enumerationEClass, ENUMERATION__CLASS_TYPE);
    createEAttribute(enumerationEClass, ENUMERATION__IDENT);
    createEReference(enumerationEClass, ENUMERATION__ENUM_COMMENTS);
    createEReference(enumerationEClass, ENUMERATION__COMMENT);

    externalClauseEClass = createEClass(EXTERNAL_CLAUSE);
    createEAttribute(externalClauseEClass, EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION);
    createEReference(externalClauseEClass, EXTERNAL_CLAUSE__ANNOTATION);
    createEReference(externalClauseEClass, EXTERNAL_CLAUSE__EXT_ANNOTATION);

    elementListEClass = createEClass(ELEMENT_LIST);
    createEReference(elementListEClass, ELEMENT_LIST__ELEMENTS);
    createEReference(elementListEClass, ELEMENT_LIST__ANNOTATIONS);

    classElementEClass = createEClass(CLASS_ELEMENT);

    replaceableElementClauseEClass = createEClass(REPLACEABLE_ELEMENT_CLAUSE);
    createEReference(replaceableElementClauseEClass, REPLACEABLE_ELEMENT_CLAUSE__ENTITY);
    createEReference(replaceableElementClauseEClass, REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN);

    importClauseEClass = createEClass(IMPORT_CLAUSE);
    createEAttribute(importClauseEClass, IMPORT_CLAUSE__AS_NAME);
    createEAttribute(importClauseEClass, IMPORT_CLAUSE__NAME);
    createEReference(importClauseEClass, IMPORT_CLAUSE__COMMENT);

    extendsClauseEClass = createEClass(EXTENDS_CLAUSE);
    createEAttribute(extendsClauseEClass, EXTENDS_CLAUSE__BASE_CLASS);
    createEReference(extendsClauseEClass, EXTENDS_CLAUSE__MODIFICATION);
    createEReference(extendsClauseEClass, EXTENDS_CLAUSE__ANNOTATION);

    constrainingClauseEClass = createEClass(CONSTRAINING_CLAUSE);
    createEAttribute(constrainingClauseEClass, CONSTRAINING_CLAUSE__NAME);
    createEReference(constrainingClauseEClass, CONSTRAINING_CLAUSE__MODIFICATION);
    createEReference(constrainingClauseEClass, CONSTRAINING_CLAUSE__COMMENT);

    componentClauseEClass = createEClass(COMPONENT_CLAUSE);
    createEAttribute(componentClauseEClass, COMPONENT_CLAUSE__TYPE_PREFIX);
    createEReference(componentClauseEClass, COMPONENT_CLAUSE__TYPE_SPECIFIER);
    createEReference(componentClauseEClass, COMPONENT_CLAUSE__DECLARATIONS);

    typeSpecifierEClass = createEClass(TYPE_SPECIFIER);
    createEAttribute(typeSpecifierEClass, TYPE_SPECIFIER__NAME);
    createEAttribute(typeSpecifierEClass, TYPE_SPECIFIER__SUBSCRIPTS);

    componentDeclarationEClass = createEClass(COMPONENT_DECLARATION);
    createEAttribute(componentDeclarationEClass, COMPONENT_DECLARATION__SUBSCRIPTS);
    createEReference(componentDeclarationEClass, COMPONENT_DECLARATION__MODIFICATION);
    createEAttribute(componentDeclarationEClass, COMPONENT_DECLARATION__CONDITION);
    createEReference(componentDeclarationEClass, COMPONENT_DECLARATION__COMMENT);

    modificationEClass = createEClass(MODIFICATION);
    createEAttribute(modificationEClass, MODIFICATION__EXPRESSION);

    simpleModificationEClass = createEClass(SIMPLE_MODIFICATION);

    argumentEClass = createEClass(ARGUMENT);

    elementModificationEClass = createEClass(ELEMENT_MODIFICATION);
    createEAttribute(elementModificationEClass, ELEMENT_MODIFICATION__COMPONET_REF);
    createEReference(elementModificationEClass, ELEMENT_MODIFICATION__MODIFICATION);
    createEAttribute(elementModificationEClass, ELEMENT_MODIFICATION__COMMENT);

    elementRedeclarationEClass = createEClass(ELEMENT_REDECLARATION);

    elementReplaceableClauseEClass = createEClass(ELEMENT_REPLACEABLE_CLAUSE);
    createEReference(elementReplaceableClauseEClass, ELEMENT_REPLACEABLE_CLAUSE__ELEMENT);
    createEReference(elementReplaceableClauseEClass, ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT);

    elementComponentClauseEClass = createEClass(ELEMENT_COMPONENT_CLAUSE);
    createEAttribute(elementComponentClauseEClass, ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX);
    createEReference(elementComponentClauseEClass, ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER);
    createEAttribute(elementComponentClauseEClass, ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS);
    createEReference(elementComponentClauseEClass, ELEMENT_COMPONENT_CLAUSE__MODIFICATION);
    createEReference(elementComponentClauseEClass, ELEMENT_COMPONENT_CLAUSE__COMMENT);

    equationClauseEClass = createEClass(EQUATION_CLAUSE);
    createEReference(equationClauseEClass, EQUATION_CLAUSE__EQUATION_RULES);
    createEReference(equationClauseEClass, EQUATION_CLAUSE__ANNOTATIONS);

    algorithm_clauseEClass = createEClass(ALGORITHM_CLAUSE);
    createEReference(algorithm_clauseEClass, ALGORITHM_CLAUSE__ALGORITHM_RULES);
    createEReference(algorithm_clauseEClass, ALGORITHM_CLAUSE__ANNOTATIONS);

    initial_equation_rule_clauseEClass = createEClass(INITIAL_EQUATION_RULE_CLAUSE);

    initial_algorithm_clauseEClass = createEClass(INITIAL_ALGORITHM_CLAUSE);

    equation_ruleEClass = createEClass(EQUATION_RULE);
    createEReference(equation_ruleEClass, EQUATION_RULE__COMMENT);

    algorithmEClass = createEClass(ALGORITHM);
    createEReference(algorithmEClass, ALGORITHM__COMMENT);

    conditionalEquationRuleEClass = createEClass(CONDITIONAL_EQUATION_RULE);
    createEReference(conditionalEquationRuleEClass, CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES);
    createEReference(conditionalEquationRuleEClass, CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES);
    createEReference(conditionalEquationRuleEClass, CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES);

    conditionAlgorithmRuleEClass = createEClass(CONDITION_ALGORITHM_RULE);
    createEAttribute(conditionAlgorithmRuleEClass, CONDITION_ALGORITHM_RULE__CONDITION);
    createEReference(conditionAlgorithmRuleEClass, CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES);
    createEReference(conditionAlgorithmRuleEClass, CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES);
    createEReference(conditionAlgorithmRuleEClass, CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES);

    forEquationClauseEClass = createEClass(FOR_EQUATION_CLAUSE);
    createEAttribute(forEquationClauseEClass, FOR_EQUATION_CLAUSE__FOR_INDICES);
    createEReference(forEquationClauseEClass, FOR_EQUATION_CLAUSE__EQUATION_CLAUSES);

    forAlgorithmClauseEClass = createEClass(FOR_ALGORITHM_CLAUSE);
    createEAttribute(forAlgorithmClauseEClass, FOR_ALGORITHM_CLAUSE__FOR_INDICES);
    createEReference(forAlgorithmClauseEClass, FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES);

    whileClauseEClass = createEClass(WHILE_CLAUSE);
    createEAttribute(whileClauseEClass, WHILE_CLAUSE__CONDITION);
    createEReference(whileClauseEClass, WHILE_CLAUSE__ALGORITHM_CLAUSES);

    when_clause_eEClass = createEClass(WHEN_CLAUSE_E);
    createEAttribute(when_clause_eEClass, WHEN_CLAUSE_E__CONDITION);
    createEReference(when_clause_eEClass, WHEN_CLAUSE_E__THEN_EQUATION_CLAUSES);
    createEReference(when_clause_eEClass, WHEN_CLAUSE_E__ELSE_WHEN_CLAUSES);

    else_when_eEClass = createEClass(ELSE_WHEN_E);
    createEAttribute(else_when_eEClass, ELSE_WHEN_E__CONDITION);
    createEReference(else_when_eEClass, ELSE_WHEN_E__THEN_EQUATION_CLAUSES);

    when_clause_aEClass = createEClass(WHEN_CLAUSE_A);
    createEAttribute(when_clause_aEClass, WHEN_CLAUSE_A__CONDITION);
    createEReference(when_clause_aEClass, WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES);
    createEReference(when_clause_aEClass, WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES);

    else_when_aEClass = createEClass(ELSE_WHEN_A);
    createEAttribute(else_when_aEClass, ELSE_WHEN_A__CONDITION);
    createEReference(else_when_aEClass, ELSE_WHEN_A__THEN_ALGORITHM_CLAUSES);

    equation_rule_elseifEClass = createEClass(EQUATION_RULE_ELSEIF);
    createEAttribute(equation_rule_elseifEClass, EQUATION_RULE_ELSEIF__CONDITION);
    createEReference(equation_rule_elseifEClass, EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES);

    algorithm_elseifEClass = createEClass(ALGORITHM_ELSEIF);
    createEAttribute(algorithm_elseifEClass, ALGORITHM_ELSEIF__CONDITION);
    createEReference(algorithm_elseifEClass, ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES);

    commentEClass = createEClass(COMMENT);
    createEAttribute(commentEClass, COMMENT__STRING);
    createEReference(commentEClass, COMMENT__ANNOTATION);

    annotationEClass = createEClass(ANNOTATION);

    publicElementListEClass = createEClass(PUBLIC_ELEMENT_LIST);

    protectesElementListEClass = createEClass(PROTECTES_ELEMENT_LIST);

    // Create enums
    classTypeEEnum = createEEnum(CLASS_TYPE);
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

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    classDefinitionEClass.getESuperTypes().add(this.getEntityDefinition());
    classDefinitionEClass.getESuperTypes().add(this.getClassElement());
    classDefinitionEClass.getESuperTypes().add(this.getElementRedeclaration());
    partialDerivativeEClass.getESuperTypes().add(this.getEntityDefinition());
    classModificationEClass.getESuperTypes().add(this.getEntityDefinition());
    classModificationEClass.getESuperTypes().add(this.getModification());
    classModificationEClass.getESuperTypes().add(this.getAnnotation());
    classOverloadingEClass.getESuperTypes().add(this.getEntityDefinition());
    enumerationEClass.getESuperTypes().add(this.getEntityDefinition());
    replaceableElementClauseEClass.getESuperTypes().add(this.getClassElement());
    importClauseEClass.getESuperTypes().add(this.getClassElement());
    extendsClauseEClass.getESuperTypes().add(this.getClassElement());
    componentClauseEClass.getESuperTypes().add(this.getClassElement());
    simpleModificationEClass.getESuperTypes().add(this.getModification());
    elementModificationEClass.getESuperTypes().add(this.getargument());
    elementRedeclarationEClass.getESuperTypes().add(this.getargument());
    elementReplaceableClauseEClass.getESuperTypes().add(this.getargument());
    elementReplaceableClauseEClass.getESuperTypes().add(this.getElementRedeclaration());
    elementComponentClauseEClass.getESuperTypes().add(this.getElementRedeclaration());
    equationClauseEClass.getESuperTypes().add(this.getinitial_equation_rule_clause());
    algorithm_clauseEClass.getESuperTypes().add(this.getinitial_algorithm_clause());
    conditionalEquationRuleEClass.getESuperTypes().add(this.getequation_rule());
    conditionAlgorithmRuleEClass.getESuperTypes().add(this.getalgorithm());
    forEquationClauseEClass.getESuperTypes().add(this.getequation_rule());
    forAlgorithmClauseEClass.getESuperTypes().add(this.getalgorithm());
    whileClauseEClass.getESuperTypes().add(this.getalgorithm());
    when_clause_eEClass.getESuperTypes().add(this.getequation_rule());
    when_clause_aEClass.getESuperTypes().add(this.getalgorithm());
    publicElementListEClass.getESuperTypes().add(this.getElementList());
    protectesElementListEClass.getESuperTypes().add(this.getElementList());

    // Initialize classes and features; add operations and parameters
    initEClass(storageUnitEClass, StorageUnit.class, "StorageUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getStorageUnit_ClassDefinition(), this.getClassDefinition(), null, "classDefinition", null, 0, -1, StorageUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(entityDefinitionEClass, EntityDefinition.class, "EntityDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEntityDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, EntityDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(classDefinitionEClass, ClassDefinition.class, "ClassDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getClassDefinition_Encapsulated(), ecorePackage.getEBoolean(), "encapsulated", null, 0, 1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getClassDefinition_Partial(), ecorePackage.getEBoolean(), "partial", null, 0, 1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getClassDefinition_Expandable(), ecorePackage.getEBoolean(), "expandable", null, 0, 1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getClassDefinition_ClassType(), this.getClassType(), "classType", null, 0, 1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getClassDefinition_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassDefinition_Modification(), this.getClassModification(), null, "modification", null, 0, 1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassDefinition_ElemList(), this.getElementList(), null, "elemList", null, 0, -1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassDefinition_InitialEquationClauses(), this.getinitial_equation_rule_clause(), null, "initialEquationClauses", null, 0, -1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassDefinition_InitialAlgorithmClauses(), this.getinitial_algorithm_clause(), null, "initialAlgorithmClauses", null, 0, -1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassDefinition_EquationClauses(), this.getEquationClause(), null, "equationClauses", null, 0, -1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassDefinition_AlgorithmClauses(), this.getalgorithm_clause(), null, "algorithmClauses", null, 0, -1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassDefinition_ExtClause(), this.getExternalClause(), null, "extClause", null, 0, 1, ClassDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(partialDerivativeEClass, PartialDerivative.class, "PartialDerivative", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPartialDerivative_Comment(), this.getComment(), null, "comment", null, 0, 1, PartialDerivative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(classModificationEClass, ClassModification.class, "ClassModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getClassModification_ClassType(), this.getClassType(), "classType", null, 0, 1, ClassModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassModification_TypeSpecifier(), this.getTypeSpecifier(), null, "typeSpecifier", null, 0, 1, ClassModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassModification_Modification(), this.getClassModification(), null, "modification", null, 0, 1, ClassModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassModification_Comment(), this.getComment(), null, "comment", null, 0, 1, ClassModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassModification_Arguments(), this.getargument(), null, "arguments", null, 0, -1, ClassModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(classOverloadingEClass, ClassOverloading.class, "ClassOverloading", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getClassOverloading_ClassType(), this.getClassType(), "classType", null, 0, 1, ClassOverloading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClassOverloading_Comment(), this.getComment(), null, "comment", null, 0, 1, ClassOverloading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumerationEClass, Enumeration.class, "Enumeration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEnumeration_ClassType(), this.getClassType(), "classType", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEnumeration_Ident(), ecorePackage.getEString(), "ident", null, 0, -1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumeration_EnumComments(), this.getComment(), null, "enumComments", null, 0, -1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumeration_Comment(), this.getComment(), null, "comment", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(externalClauseEClass, ExternalClause.class, "ExternalClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExternalClause_LanguageSpecification(), ecorePackage.getEString(), "languageSpecification", null, 0, 1, ExternalClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExternalClause_Annotation(), this.getAnnotation(), null, "annotation", null, 0, 1, ExternalClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExternalClause_ExtAnnotation(), this.getAnnotation(), null, "extAnnotation", null, 0, 1, ExternalClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(elementListEClass, ElementList.class, "ElementList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getElementList_Elements(), this.getClassElement(), null, "elements", null, 0, -1, ElementList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElementList_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, ElementList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(classElementEClass, ClassElement.class, "ClassElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(replaceableElementClauseEClass, ReplaceableElementClause.class, "ReplaceableElementClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getReplaceableElementClause_Entity(), this.getClassElement(), null, "entity", null, 0, 1, ReplaceableElementClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getReplaceableElementClause_Constrain(), this.getConstrainingClause(), null, "constrain", null, 0, 1, ReplaceableElementClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(importClauseEClass, ImportClause.class, "ImportClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImportClause_AsName(), ecorePackage.getEString(), "asName", null, 0, 1, ImportClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImportClause_Name(), ecorePackage.getEString(), "name", null, 0, 1, ImportClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getImportClause_Comment(), this.getComment(), null, "comment", null, 0, 1, ImportClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendsClauseEClass, ExtendsClause.class, "ExtendsClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExtendsClause_BaseClass(), ecorePackage.getEString(), "baseClass", null, 0, 1, ExtendsClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExtendsClause_Modification(), this.getClassModification(), null, "modification", null, 0, 1, ExtendsClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExtendsClause_Annotation(), this.getAnnotation(), null, "annotation", null, 0, 1, ExtendsClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(constrainingClauseEClass, ConstrainingClause.class, "ConstrainingClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getConstrainingClause_Name(), ecorePackage.getEString(), "name", null, 0, 1, ConstrainingClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConstrainingClause_Modification(), this.getClassModification(), null, "modification", null, 0, 1, ConstrainingClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConstrainingClause_Comment(), this.getComment(), null, "comment", null, 0, 1, ConstrainingClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(componentClauseEClass, ComponentClause.class, "ComponentClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getComponentClause_TypePrefix(), ecorePackage.getEString(), "typePrefix", null, 0, 1, ComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getComponentClause_TypeSpecifier(), this.getTypeSpecifier(), null, "typeSpecifier", null, 0, 1, ComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getComponentClause_Declarations(), this.getComponentDeclaration(), null, "declarations", null, 0, -1, ComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(typeSpecifierEClass, TypeSpecifier.class, "TypeSpecifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTypeSpecifier_Name(), ecorePackage.getEString(), "name", null, 0, 1, TypeSpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTypeSpecifier_Subscripts(), ecorePackage.getEString(), "subscripts", null, 0, 1, TypeSpecifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(componentDeclarationEClass, ComponentDeclaration.class, "ComponentDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getComponentDeclaration_Subscripts(), ecorePackage.getEString(), "subscripts", null, 0, 1, ComponentDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getComponentDeclaration_Modification(), this.getModification(), null, "modification", null, 0, 1, ComponentDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getComponentDeclaration_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, ComponentDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getComponentDeclaration_Comment(), this.getComment(), null, "comment", null, 0, 1, ComponentDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modificationEClass, Modification.class, "Modification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getModification_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Modification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simpleModificationEClass, SimpleModification.class, "SimpleModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(argumentEClass, argument.class, "argument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(elementModificationEClass, ElementModification.class, "ElementModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getElementModification_ComponetRef(), ecorePackage.getEString(), "componetRef", null, 0, 1, ElementModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElementModification_Modification(), this.getModification(), null, "modification", null, 0, 1, ElementModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getElementModification_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, ElementModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(elementRedeclarationEClass, ElementRedeclaration.class, "ElementRedeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(elementReplaceableClauseEClass, ElementReplaceableClause.class, "ElementReplaceableClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getElementReplaceableClause_Element(), this.getElementRedeclaration(), null, "element", null, 0, 1, ElementReplaceableClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElementReplaceableClause_Constraint(), this.getConstrainingClause(), null, "constraint", null, 0, 1, ElementReplaceableClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(elementComponentClauseEClass, ElementComponentClause.class, "ElementComponentClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getElementComponentClause_TypePrefix(), ecorePackage.getEString(), "typePrefix", null, 0, 1, ElementComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElementComponentClause_TypeSpecifier(), this.getTypeSpecifier(), null, "typeSpecifier", null, 0, 1, ElementComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getElementComponentClause_Subscripts(), ecorePackage.getEString(), "subscripts", null, 0, 1, ElementComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElementComponentClause_Modification(), this.getModification(), null, "modification", null, 0, 1, ElementComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getElementComponentClause_Comment(), this.getComment(), null, "comment", null, 0, 1, ElementComponentClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(equationClauseEClass, EquationClause.class, "EquationClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEquationClause_EquationRules(), this.getequation_rule(), null, "equationRules", null, 0, -1, EquationClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEquationClause_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, EquationClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(algorithm_clauseEClass, algorithm_clause.class, "algorithm_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getalgorithm_clause_AlgorithmRules(), this.getalgorithm(), null, "algorithmRules", null, 0, -1, algorithm_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getalgorithm_clause_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, algorithm_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(initial_equation_rule_clauseEClass, initial_equation_rule_clause.class, "initial_equation_rule_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(initial_algorithm_clauseEClass, initial_algorithm_clause.class, "initial_algorithm_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(equation_ruleEClass, equation_rule.class, "equation_rule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getequation_rule_Comment(), this.getComment(), null, "comment", null, 0, 1, equation_rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(algorithmEClass, algorithm.class, "algorithm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getalgorithm_Comment(), this.getComment(), null, "comment", null, 0, 1, algorithm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditionalEquationRuleEClass, ConditionalEquationRule.class, "ConditionalEquationRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConditionalEquationRule_ThenEquationClauses(), this.getequation_rule(), null, "thenEquationClauses", null, 0, -1, ConditionalEquationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalEquationRule_ElseifClauses(), this.getequation_rule_elseif(), null, "elseifClauses", null, 0, -1, ConditionalEquationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalEquationRule_ElseEquationClauses(), this.getequation_rule(), null, "elseEquationClauses", null, 0, -1, ConditionalEquationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditionAlgorithmRuleEClass, ConditionAlgorithmRule.class, "ConditionAlgorithmRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getConditionAlgorithmRule_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, ConditionAlgorithmRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionAlgorithmRule_ThenAlgorithmClauses(), this.getalgorithm(), null, "thenAlgorithmClauses", null, 0, -1, ConditionAlgorithmRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionAlgorithmRule_ElseifClauses(), this.getalgorithm_elseif(), null, "elseifClauses", null, 0, -1, ConditionAlgorithmRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionAlgorithmRule_ElseAlgotithmClauses(), this.getalgorithm(), null, "elseAlgotithmClauses", null, 0, -1, ConditionAlgorithmRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(forEquationClauseEClass, ForEquationClause.class, "ForEquationClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getForEquationClause_ForIndices(), ecorePackage.getEString(), "forIndices", null, 0, 1, ForEquationClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForEquationClause_EquationClauses(), this.getequation_rule(), null, "equationClauses", null, 0, -1, ForEquationClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(forAlgorithmClauseEClass, ForAlgorithmClause.class, "ForAlgorithmClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getForAlgorithmClause_ForIndices(), ecorePackage.getEString(), "forIndices", null, 0, 1, ForAlgorithmClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForAlgorithmClause_AlgorithmClauses(), this.getalgorithm(), null, "algorithmClauses", null, 0, -1, ForAlgorithmClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(whileClauseEClass, WhileClause.class, "WhileClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getWhileClause_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, WhileClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWhileClause_AlgorithmClauses(), this.getalgorithm(), null, "algorithmClauses", null, 0, -1, WhileClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(when_clause_eEClass, when_clause_e.class, "when_clause_e", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getwhen_clause_e_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, when_clause_e.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_clause_e_ThenEquationClauses(), this.getequation_rule(), null, "thenEquationClauses", null, 0, -1, when_clause_e.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_clause_e_ElseWhenClauses(), this.getelse_when_e(), null, "elseWhenClauses", null, 0, -1, when_clause_e.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(else_when_eEClass, else_when_e.class, "else_when_e", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getelse_when_e_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, else_when_e.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelse_when_e_ThenEquationClauses(), this.getequation_rule(), null, "thenEquationClauses", null, 0, -1, else_when_e.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(when_clause_aEClass, when_clause_a.class, "when_clause_a", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getwhen_clause_a_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, when_clause_a.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_clause_a_ThenAlgorithmClauses(), this.getequation_rule(), null, "thenAlgorithmClauses", null, 0, -1, when_clause_a.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_clause_a_ElseWhenClauses(), this.getelse_when_a(), null, "elseWhenClauses", null, 0, -1, when_clause_a.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(else_when_aEClass, else_when_a.class, "else_when_a", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getelse_when_a_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, else_when_a.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelse_when_a_ThenAlgorithmClauses(), this.getequation_rule(), null, "thenAlgorithmClauses", null, 0, -1, else_when_a.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(equation_rule_elseifEClass, equation_rule_elseif.class, "equation_rule_elseif", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getequation_rule_elseif_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, equation_rule_elseif.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getequation_rule_elseif_ThenEquationClauses(), this.getequation_rule(), null, "thenEquationClauses", null, 0, -1, equation_rule_elseif.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(algorithm_elseifEClass, algorithm_elseif.class, "algorithm_elseif", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getalgorithm_elseif_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, algorithm_elseif.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getalgorithm_elseif_ThenAlgorithmClauses(), this.getequation_rule(), null, "thenAlgorithmClauses", null, 0, -1, algorithm_elseif.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getComment_String(), ecorePackage.getEString(), "string", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getComment_Annotation(), this.getAnnotation(), null, "annotation", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(publicElementListEClass, PublicElementList.class, "PublicElementList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(protectesElementListEClass, ProtectesElementList.class, "ProtectesElementList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Initialize enums and add enum literals
    initEEnum(classTypeEEnum, ClassType.class, "ClassType");
    addEEnumLiteral(classTypeEEnum, ClassType.CLASS);
    addEEnumLiteral(classTypeEEnum, ClassType.MODEL);
    addEEnumLiteral(classTypeEEnum, ClassType.RECORD);
    addEEnumLiteral(classTypeEEnum, ClassType.BLOCK);
    addEEnumLiteral(classTypeEEnum, ClassType.CONNECTOR);
    addEEnumLiteral(classTypeEEnum, ClassType.TYPE);
    addEEnumLiteral(classTypeEEnum, ClassType.PACKAGE);
    addEEnumLiteral(classTypeEEnum, ClassType.FUNCTION);

    // Create resource
    createResource(eNS_URI);
  }

} //ModelicaBasePackageImpl
