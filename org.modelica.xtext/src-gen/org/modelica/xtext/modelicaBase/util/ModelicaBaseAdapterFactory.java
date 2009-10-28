/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.modelica.xtext.modelicaBase.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage
 * @generated
 */
public class ModelicaBaseAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ModelicaBasePackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelicaBaseAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ModelicaBasePackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelicaBaseSwitch<Adapter> modelSwitch =
    new ModelicaBaseSwitch<Adapter>()
    {
      @Override
      public Adapter caseStorageUnit(StorageUnit object)
      {
        return createStorageUnitAdapter();
      }
      @Override
      public Adapter caseEntityDefinition(EntityDefinition object)
      {
        return createEntityDefinitionAdapter();
      }
      @Override
      public Adapter caseClassDefinition(ClassDefinition object)
      {
        return createClassDefinitionAdapter();
      }
      @Override
      public Adapter casePartialDerivative(PartialDerivative object)
      {
        return createPartialDerivativeAdapter();
      }
      @Override
      public Adapter caseClassModification(ClassModification object)
      {
        return createClassModificationAdapter();
      }
      @Override
      public Adapter caseClassOverloading(ClassOverloading object)
      {
        return createClassOverloadingAdapter();
      }
      @Override
      public Adapter caseEnumeration(Enumeration object)
      {
        return createEnumerationAdapter();
      }
      @Override
      public Adapter caseExternalClause(ExternalClause object)
      {
        return createExternalClauseAdapter();
      }
      @Override
      public Adapter caseElementList(ElementList object)
      {
        return createElementListAdapter();
      }
      @Override
      public Adapter caseClassElement(ClassElement object)
      {
        return createClassElementAdapter();
      }
      @Override
      public Adapter caseReplaceableElementClause(ReplaceableElementClause object)
      {
        return createReplaceableElementClauseAdapter();
      }
      @Override
      public Adapter caseImportClause(ImportClause object)
      {
        return createImportClauseAdapter();
      }
      @Override
      public Adapter caseExtendsClause(ExtendsClause object)
      {
        return createExtendsClauseAdapter();
      }
      @Override
      public Adapter caseConstrainingClause(ConstrainingClause object)
      {
        return createConstrainingClauseAdapter();
      }
      @Override
      public Adapter caseComponentClause(ComponentClause object)
      {
        return createComponentClauseAdapter();
      }
      @Override
      public Adapter caseTypeSpecifier(TypeSpecifier object)
      {
        return createTypeSpecifierAdapter();
      }
      @Override
      public Adapter caseComponentDeclaration(ComponentDeclaration object)
      {
        return createComponentDeclarationAdapter();
      }
      @Override
      public Adapter caseModification(Modification object)
      {
        return createModificationAdapter();
      }
      @Override
      public Adapter caseSimpleModification(SimpleModification object)
      {
        return createSimpleModificationAdapter();
      }
      @Override
      public Adapter caseargument(argument object)
      {
        return createargumentAdapter();
      }
      @Override
      public Adapter caseElementModification(ElementModification object)
      {
        return createElementModificationAdapter();
      }
      @Override
      public Adapter caseElementRedeclaration(ElementRedeclaration object)
      {
        return createElementRedeclarationAdapter();
      }
      @Override
      public Adapter caseElementReplaceableClause(ElementReplaceableClause object)
      {
        return createElementReplaceableClauseAdapter();
      }
      @Override
      public Adapter caseElementComponentClause(ElementComponentClause object)
      {
        return createElementComponentClauseAdapter();
      }
      @Override
      public Adapter caseEquationClause(EquationClause object)
      {
        return createEquationClauseAdapter();
      }
      @Override
      public Adapter casealgorithm_clause(algorithm_clause object)
      {
        return createalgorithm_clauseAdapter();
      }
      @Override
      public Adapter caseinitial_equation_rule_clause(initial_equation_rule_clause object)
      {
        return createinitial_equation_rule_clauseAdapter();
      }
      @Override
      public Adapter caseinitial_algorithm_clause(initial_algorithm_clause object)
      {
        return createinitial_algorithm_clauseAdapter();
      }
      @Override
      public Adapter caseequation_rule(equation_rule object)
      {
        return createequation_ruleAdapter();
      }
      @Override
      public Adapter casealgorithm(algorithm object)
      {
        return createalgorithmAdapter();
      }
      @Override
      public Adapter caseConditionalEquationRule(ConditionalEquationRule object)
      {
        return createConditionalEquationRuleAdapter();
      }
      @Override
      public Adapter caseConditionAlgorithmRule(ConditionAlgorithmRule object)
      {
        return createConditionAlgorithmRuleAdapter();
      }
      @Override
      public Adapter caseForEquationClause(ForEquationClause object)
      {
        return createForEquationClauseAdapter();
      }
      @Override
      public Adapter caseForAlgorithmClause(ForAlgorithmClause object)
      {
        return createForAlgorithmClauseAdapter();
      }
      @Override
      public Adapter caseWhileClause(WhileClause object)
      {
        return createWhileClauseAdapter();
      }
      @Override
      public Adapter casewhen_clause_e(when_clause_e object)
      {
        return createwhen_clause_eAdapter();
      }
      @Override
      public Adapter caseelse_when_e(else_when_e object)
      {
        return createelse_when_eAdapter();
      }
      @Override
      public Adapter casewhen_clause_a(when_clause_a object)
      {
        return createwhen_clause_aAdapter();
      }
      @Override
      public Adapter caseelse_when_a(else_when_a object)
      {
        return createelse_when_aAdapter();
      }
      @Override
      public Adapter caseequation_rule_elseif(equation_rule_elseif object)
      {
        return createequation_rule_elseifAdapter();
      }
      @Override
      public Adapter casealgorithm_elseif(algorithm_elseif object)
      {
        return createalgorithm_elseifAdapter();
      }
      @Override
      public Adapter caseComment(Comment object)
      {
        return createCommentAdapter();
      }
      @Override
      public Adapter caseAnnotation(Annotation object)
      {
        return createAnnotationAdapter();
      }
      @Override
      public Adapter casePublicElementList(PublicElementList object)
      {
        return createPublicElementListAdapter();
      }
      @Override
      public Adapter caseProtectesElementList(ProtectesElementList object)
      {
        return createProtectesElementListAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.StorageUnit <em>Storage Unit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.StorageUnit
   * @generated
   */
  public Adapter createStorageUnitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.EntityDefinition <em>Entity Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.EntityDefinition
   * @generated
   */
  public Adapter createEntityDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ClassDefinition <em>Class Definition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition
   * @generated
   */
  public Adapter createClassDefinitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.PartialDerivative <em>Partial Derivative</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.PartialDerivative
   * @generated
   */
  public Adapter createPartialDerivativeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ClassModification <em>Class Modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ClassModification
   * @generated
   */
  public Adapter createClassModificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ClassOverloading <em>Class Overloading</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ClassOverloading
   * @generated
   */
  public Adapter createClassOverloadingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.Enumeration <em>Enumeration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.Enumeration
   * @generated
   */
  public Adapter createEnumerationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ExternalClause <em>External Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ExternalClause
   * @generated
   */
  public Adapter createExternalClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ElementList <em>Element List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ElementList
   * @generated
   */
  public Adapter createElementListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ClassElement <em>Class Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ClassElement
   * @generated
   */
  public Adapter createClassElementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause <em>Replaceable Element Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ReplaceableElementClause
   * @generated
   */
  public Adapter createReplaceableElementClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ImportClause <em>Import Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ImportClause
   * @generated
   */
  public Adapter createImportClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ExtendsClause <em>Extends Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ExtendsClause
   * @generated
   */
  public Adapter createExtendsClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ConstrainingClause <em>Constraining Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ConstrainingClause
   * @generated
   */
  public Adapter createConstrainingClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ComponentClause <em>Component Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ComponentClause
   * @generated
   */
  public Adapter createComponentClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.TypeSpecifier <em>Type Specifier</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.TypeSpecifier
   * @generated
   */
  public Adapter createTypeSpecifierAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration <em>Component Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ComponentDeclaration
   * @generated
   */
  public Adapter createComponentDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.Modification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.Modification
   * @generated
   */
  public Adapter createModificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.SimpleModification <em>Simple Modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.SimpleModification
   * @generated
   */
  public Adapter createSimpleModificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.argument <em>argument</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.argument
   * @generated
   */
  public Adapter createargumentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ElementModification <em>Element Modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ElementModification
   * @generated
   */
  public Adapter createElementModificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ElementRedeclaration <em>Element Redeclaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ElementRedeclaration
   * @generated
   */
  public Adapter createElementRedeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause <em>Element Replaceable Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ElementReplaceableClause
   * @generated
   */
  public Adapter createElementReplaceableClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ElementComponentClause <em>Element Component Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ElementComponentClause
   * @generated
   */
  public Adapter createElementComponentClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.EquationClause <em>Equation Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.EquationClause
   * @generated
   */
  public Adapter createEquationClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.algorithm_clause <em>algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.algorithm_clause
   * @generated
   */
  public Adapter createalgorithm_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.initial_equation_rule_clause <em>initial equation rule clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.initial_equation_rule_clause
   * @generated
   */
  public Adapter createinitial_equation_rule_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.initial_algorithm_clause <em>initial algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.initial_algorithm_clause
   * @generated
   */
  public Adapter createinitial_algorithm_clauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.equation_rule <em>equation rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.equation_rule
   * @generated
   */
  public Adapter createequation_ruleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.algorithm <em>algorithm</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.algorithm
   * @generated
   */
  public Adapter createalgorithmAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule <em>Conditional Equation Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ConditionalEquationRule
   * @generated
   */
  public Adapter createConditionalEquationRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule <em>Condition Algorithm Rule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ConditionAlgorithmRule
   * @generated
   */
  public Adapter createConditionAlgorithmRuleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ForEquationClause <em>For Equation Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ForEquationClause
   * @generated
   */
  public Adapter createForEquationClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ForAlgorithmClause <em>For Algorithm Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ForAlgorithmClause
   * @generated
   */
  public Adapter createForAlgorithmClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.WhileClause <em>While Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.WhileClause
   * @generated
   */
  public Adapter createWhileClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.when_clause_e <em>when clause e</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.when_clause_e
   * @generated
   */
  public Adapter createwhen_clause_eAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.else_when_e <em>else when e</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.else_when_e
   * @generated
   */
  public Adapter createelse_when_eAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.when_clause_a <em>when clause a</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.when_clause_a
   * @generated
   */
  public Adapter createwhen_clause_aAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.else_when_a <em>else when a</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.else_when_a
   * @generated
   */
  public Adapter createelse_when_aAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.equation_rule_elseif <em>equation rule elseif</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.equation_rule_elseif
   * @generated
   */
  public Adapter createequation_rule_elseifAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.algorithm_elseif <em>algorithm elseif</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.algorithm_elseif
   * @generated
   */
  public Adapter createalgorithm_elseifAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.Comment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.Comment
   * @generated
   */
  public Adapter createCommentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.Annotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.Annotation
   * @generated
   */
  public Adapter createAnnotationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.PublicElementList <em>Public Element List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.PublicElementList
   * @generated
   */
  public Adapter createPublicElementListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.modelica.xtext.modelicaBase.ProtectesElementList <em>Protectes Element List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.modelica.xtext.modelicaBase.ProtectesElementList
   * @generated
   */
  public Adapter createProtectesElementListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //ModelicaBaseAdapterFactory
