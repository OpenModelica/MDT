/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.modelica.xtext.modelicaBase.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelicaBaseFactoryImpl extends EFactoryImpl implements ModelicaBaseFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ModelicaBaseFactory init()
  {
    try
    {
      ModelicaBaseFactory theModelicaBaseFactory = (ModelicaBaseFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.modelica.org/xtext/ModelicaBase"); 
      if (theModelicaBaseFactory != null)
      {
        return theModelicaBaseFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ModelicaBaseFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelicaBaseFactoryImpl()
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
      case ModelicaBasePackage.STORAGE_UNIT: return createStorageUnit();
      case ModelicaBasePackage.ENTITY_DEFINITION: return createEntityDefinition();
      case ModelicaBasePackage.CLASS_DEFINITION: return createClassDefinition();
      case ModelicaBasePackage.PARTIAL_DERIVATIVE: return createPartialDerivative();
      case ModelicaBasePackage.CLASS_MODIFICATION: return createClassModification();
      case ModelicaBasePackage.CLASS_OVERLOADING: return createClassOverloading();
      case ModelicaBasePackage.ENUMERATION: return createEnumeration();
      case ModelicaBasePackage.EXTERNAL_CLAUSE: return createExternalClause();
      case ModelicaBasePackage.ELEMENT_LIST: return createElementList();
      case ModelicaBasePackage.CLASS_ELEMENT: return createClassElement();
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE: return createReplaceableElementClause();
      case ModelicaBasePackage.IMPORT_CLAUSE: return createImportClause();
      case ModelicaBasePackage.EXTENDS_CLAUSE: return createExtendsClause();
      case ModelicaBasePackage.CONSTRAINING_CLAUSE: return createConstrainingClause();
      case ModelicaBasePackage.COMPONENT_CLAUSE: return createComponentClause();
      case ModelicaBasePackage.TYPE_SPECIFIER: return createTypeSpecifier();
      case ModelicaBasePackage.COMPONENT_DECLARATION: return createComponentDeclaration();
      case ModelicaBasePackage.MODIFICATION: return createModification();
      case ModelicaBasePackage.SIMPLE_MODIFICATION: return createSimpleModification();
      case ModelicaBasePackage.ARGUMENT: return createargument();
      case ModelicaBasePackage.ELEMENT_MODIFICATION: return createElementModification();
      case ModelicaBasePackage.ELEMENT_REDECLARATION: return createElementRedeclaration();
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE: return createElementReplaceableClause();
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE: return createElementComponentClause();
      case ModelicaBasePackage.EQUATION_CLAUSE: return createEquationClause();
      case ModelicaBasePackage.ALGORITHM_CLAUSE: return createalgorithm_clause();
      case ModelicaBasePackage.INITIAL_EQUATION_RULE_CLAUSE: return createinitial_equation_rule_clause();
      case ModelicaBasePackage.INITIAL_ALGORITHM_CLAUSE: return createinitial_algorithm_clause();
      case ModelicaBasePackage.EQUATION_RULE: return createequation_rule();
      case ModelicaBasePackage.ALGORITHM: return createalgorithm();
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE: return createConditionalEquationRule();
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE: return createConditionAlgorithmRule();
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE: return createForEquationClause();
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE: return createForAlgorithmClause();
      case ModelicaBasePackage.WHILE_CLAUSE: return createWhileClause();
      case ModelicaBasePackage.WHEN_CLAUSE_E: return createwhen_clause_e();
      case ModelicaBasePackage.ELSE_WHEN_E: return createelse_when_e();
      case ModelicaBasePackage.WHEN_CLAUSE_A: return createwhen_clause_a();
      case ModelicaBasePackage.ELSE_WHEN_A: return createelse_when_a();
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF: return createequation_rule_elseif();
      case ModelicaBasePackage.ALGORITHM_ELSEIF: return createalgorithm_elseif();
      case ModelicaBasePackage.COMMENT: return createComment();
      case ModelicaBasePackage.ANNOTATION: return createAnnotation();
      case ModelicaBasePackage.PUBLIC_ELEMENT_LIST: return createPublicElementList();
      case ModelicaBasePackage.PROTECTES_ELEMENT_LIST: return createProtectesElementList();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ModelicaBasePackage.CLASS_TYPE:
        return createClassTypeFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ModelicaBasePackage.CLASS_TYPE:
        return convertClassTypeToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StorageUnit createStorageUnit()
  {
    StorageUnitImpl storageUnit = new StorageUnitImpl();
    return storageUnit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntityDefinition createEntityDefinition()
  {
    EntityDefinitionImpl entityDefinition = new EntityDefinitionImpl();
    return entityDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassDefinition createClassDefinition()
  {
    ClassDefinitionImpl classDefinition = new ClassDefinitionImpl();
    return classDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PartialDerivative createPartialDerivative()
  {
    PartialDerivativeImpl partialDerivative = new PartialDerivativeImpl();
    return partialDerivative;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassModification createClassModification()
  {
    ClassModificationImpl classModification = new ClassModificationImpl();
    return classModification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassOverloading createClassOverloading()
  {
    ClassOverloadingImpl classOverloading = new ClassOverloadingImpl();
    return classOverloading;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Enumeration createEnumeration()
  {
    EnumerationImpl enumeration = new EnumerationImpl();
    return enumeration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExternalClause createExternalClause()
  {
    ExternalClauseImpl externalClause = new ExternalClauseImpl();
    return externalClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementList createElementList()
  {
    ElementListImpl elementList = new ElementListImpl();
    return elementList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassElement createClassElement()
  {
    ClassElementImpl classElement = new ClassElementImpl();
    return classElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReplaceableElementClause createReplaceableElementClause()
  {
    ReplaceableElementClauseImpl replaceableElementClause = new ReplaceableElementClauseImpl();
    return replaceableElementClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImportClause createImportClause()
  {
    ImportClauseImpl importClause = new ImportClauseImpl();
    return importClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendsClause createExtendsClause()
  {
    ExtendsClauseImpl extendsClause = new ExtendsClauseImpl();
    return extendsClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstrainingClause createConstrainingClause()
  {
    ConstrainingClauseImpl constrainingClause = new ConstrainingClauseImpl();
    return constrainingClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentClause createComponentClause()
  {
    ComponentClauseImpl componentClause = new ComponentClauseImpl();
    return componentClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeSpecifier createTypeSpecifier()
  {
    TypeSpecifierImpl typeSpecifier = new TypeSpecifierImpl();
    return typeSpecifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentDeclaration createComponentDeclaration()
  {
    ComponentDeclarationImpl componentDeclaration = new ComponentDeclarationImpl();
    return componentDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Modification createModification()
  {
    ModificationImpl modification = new ModificationImpl();
    return modification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleModification createSimpleModification()
  {
    SimpleModificationImpl simpleModification = new SimpleModificationImpl();
    return simpleModification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public argument createargument()
  {
    argumentImpl argument = new argumentImpl();
    return argument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementModification createElementModification()
  {
    ElementModificationImpl elementModification = new ElementModificationImpl();
    return elementModification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementRedeclaration createElementRedeclaration()
  {
    ElementRedeclarationImpl elementRedeclaration = new ElementRedeclarationImpl();
    return elementRedeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementReplaceableClause createElementReplaceableClause()
  {
    ElementReplaceableClauseImpl elementReplaceableClause = new ElementReplaceableClauseImpl();
    return elementReplaceableClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementComponentClause createElementComponentClause()
  {
    ElementComponentClauseImpl elementComponentClause = new ElementComponentClauseImpl();
    return elementComponentClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EquationClause createEquationClause()
  {
    EquationClauseImpl equationClause = new EquationClauseImpl();
    return equationClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public algorithm_clause createalgorithm_clause()
  {
    algorithm_clauseImpl algorithm_clause = new algorithm_clauseImpl();
    return algorithm_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public initial_equation_rule_clause createinitial_equation_rule_clause()
  {
    initial_equation_rule_clauseImpl initial_equation_rule_clause = new initial_equation_rule_clauseImpl();
    return initial_equation_rule_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public initial_algorithm_clause createinitial_algorithm_clause()
  {
    initial_algorithm_clauseImpl initial_algorithm_clause = new initial_algorithm_clauseImpl();
    return initial_algorithm_clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public equation_rule createequation_rule()
  {
    equation_ruleImpl equation_rule = new equation_ruleImpl();
    return equation_rule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public algorithm createalgorithm()
  {
    algorithmImpl algorithm = new algorithmImpl();
    return algorithm;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalEquationRule createConditionalEquationRule()
  {
    ConditionalEquationRuleImpl conditionalEquationRule = new ConditionalEquationRuleImpl();
    return conditionalEquationRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionAlgorithmRule createConditionAlgorithmRule()
  {
    ConditionAlgorithmRuleImpl conditionAlgorithmRule = new ConditionAlgorithmRuleImpl();
    return conditionAlgorithmRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForEquationClause createForEquationClause()
  {
    ForEquationClauseImpl forEquationClause = new ForEquationClauseImpl();
    return forEquationClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForAlgorithmClause createForAlgorithmClause()
  {
    ForAlgorithmClauseImpl forAlgorithmClause = new ForAlgorithmClauseImpl();
    return forAlgorithmClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhileClause createWhileClause()
  {
    WhileClauseImpl whileClause = new WhileClauseImpl();
    return whileClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public when_clause_e createwhen_clause_e()
  {
    when_clause_eImpl when_clause_e = new when_clause_eImpl();
    return when_clause_e;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public else_when_e createelse_when_e()
  {
    else_when_eImpl else_when_e = new else_when_eImpl();
    return else_when_e;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public when_clause_a createwhen_clause_a()
  {
    when_clause_aImpl when_clause_a = new when_clause_aImpl();
    return when_clause_a;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public else_when_a createelse_when_a()
  {
    else_when_aImpl else_when_a = new else_when_aImpl();
    return else_when_a;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public equation_rule_elseif createequation_rule_elseif()
  {
    equation_rule_elseifImpl equation_rule_elseif = new equation_rule_elseifImpl();
    return equation_rule_elseif;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public algorithm_elseif createalgorithm_elseif()
  {
    algorithm_elseifImpl algorithm_elseif = new algorithm_elseifImpl();
    return algorithm_elseif;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Comment createComment()
  {
    CommentImpl comment = new CommentImpl();
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Annotation createAnnotation()
  {
    AnnotationImpl annotation = new AnnotationImpl();
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PublicElementList createPublicElementList()
  {
    PublicElementListImpl publicElementList = new PublicElementListImpl();
    return publicElementList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProtectesElementList createProtectesElementList()
  {
    ProtectesElementListImpl protectesElementList = new ProtectesElementListImpl();
    return protectesElementList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassType createClassTypeFromString(EDataType eDataType, String initialValue)
  {
    ClassType result = ClassType.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertClassTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelicaBasePackage getModelicaBasePackage()
  {
    return (ModelicaBasePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ModelicaBasePackage getPackage()
  {
    return ModelicaBasePackage.eINSTANCE;
  }

} //ModelicaBaseFactoryImpl
