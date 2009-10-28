/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage
 * @generated
 */
public interface ModelicaBaseFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModelicaBaseFactory eINSTANCE = org.modelica.xtext.modelicaBase.impl.ModelicaBaseFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Storage Unit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Storage Unit</em>'.
   * @generated
   */
  StorageUnit createStorageUnit();

  /**
   * Returns a new object of class '<em>Entity Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Entity Definition</em>'.
   * @generated
   */
  EntityDefinition createEntityDefinition();

  /**
   * Returns a new object of class '<em>Class Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Class Definition</em>'.
   * @generated
   */
  ClassDefinition createClassDefinition();

  /**
   * Returns a new object of class '<em>Partial Derivative</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Partial Derivative</em>'.
   * @generated
   */
  PartialDerivative createPartialDerivative();

  /**
   * Returns a new object of class '<em>Class Modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Class Modification</em>'.
   * @generated
   */
  ClassModification createClassModification();

  /**
   * Returns a new object of class '<em>Class Overloading</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Class Overloading</em>'.
   * @generated
   */
  ClassOverloading createClassOverloading();

  /**
   * Returns a new object of class '<em>Enumeration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Enumeration</em>'.
   * @generated
   */
  Enumeration createEnumeration();

  /**
   * Returns a new object of class '<em>External Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>External Clause</em>'.
   * @generated
   */
  ExternalClause createExternalClause();

  /**
   * Returns a new object of class '<em>Element List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Element List</em>'.
   * @generated
   */
  ElementList createElementList();

  /**
   * Returns a new object of class '<em>Class Element</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Class Element</em>'.
   * @generated
   */
  ClassElement createClassElement();

  /**
   * Returns a new object of class '<em>Replaceable Element Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Replaceable Element Clause</em>'.
   * @generated
   */
  ReplaceableElementClause createReplaceableElementClause();

  /**
   * Returns a new object of class '<em>Import Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import Clause</em>'.
   * @generated
   */
  ImportClause createImportClause();

  /**
   * Returns a new object of class '<em>Extends Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Extends Clause</em>'.
   * @generated
   */
  ExtendsClause createExtendsClause();

  /**
   * Returns a new object of class '<em>Constraining Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constraining Clause</em>'.
   * @generated
   */
  ConstrainingClause createConstrainingClause();

  /**
   * Returns a new object of class '<em>Component Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Clause</em>'.
   * @generated
   */
  ComponentClause createComponentClause();

  /**
   * Returns a new object of class '<em>Type Specifier</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Specifier</em>'.
   * @generated
   */
  TypeSpecifier createTypeSpecifier();

  /**
   * Returns a new object of class '<em>Component Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Component Declaration</em>'.
   * @generated
   */
  ComponentDeclaration createComponentDeclaration();

  /**
   * Returns a new object of class '<em>Modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Modification</em>'.
   * @generated
   */
  Modification createModification();

  /**
   * Returns a new object of class '<em>Simple Modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simple Modification</em>'.
   * @generated
   */
  SimpleModification createSimpleModification();

  /**
   * Returns a new object of class '<em>argument</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>argument</em>'.
   * @generated
   */
  argument createargument();

  /**
   * Returns a new object of class '<em>Element Modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Element Modification</em>'.
   * @generated
   */
  ElementModification createElementModification();

  /**
   * Returns a new object of class '<em>Element Redeclaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Element Redeclaration</em>'.
   * @generated
   */
  ElementRedeclaration createElementRedeclaration();

  /**
   * Returns a new object of class '<em>Element Replaceable Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Element Replaceable Clause</em>'.
   * @generated
   */
  ElementReplaceableClause createElementReplaceableClause();

  /**
   * Returns a new object of class '<em>Element Component Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Element Component Clause</em>'.
   * @generated
   */
  ElementComponentClause createElementComponentClause();

  /**
   * Returns a new object of class '<em>Equation Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Equation Clause</em>'.
   * @generated
   */
  EquationClause createEquationClause();

  /**
   * Returns a new object of class '<em>algorithm clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>algorithm clause</em>'.
   * @generated
   */
  algorithm_clause createalgorithm_clause();

  /**
   * Returns a new object of class '<em>initial equation rule clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>initial equation rule clause</em>'.
   * @generated
   */
  initial_equation_rule_clause createinitial_equation_rule_clause();

  /**
   * Returns a new object of class '<em>initial algorithm clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>initial algorithm clause</em>'.
   * @generated
   */
  initial_algorithm_clause createinitial_algorithm_clause();

  /**
   * Returns a new object of class '<em>equation rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>equation rule</em>'.
   * @generated
   */
  equation_rule createequation_rule();

  /**
   * Returns a new object of class '<em>algorithm</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>algorithm</em>'.
   * @generated
   */
  algorithm createalgorithm();

  /**
   * Returns a new object of class '<em>Conditional Equation Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Conditional Equation Rule</em>'.
   * @generated
   */
  ConditionalEquationRule createConditionalEquationRule();

  /**
   * Returns a new object of class '<em>Condition Algorithm Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Condition Algorithm Rule</em>'.
   * @generated
   */
  ConditionAlgorithmRule createConditionAlgorithmRule();

  /**
   * Returns a new object of class '<em>For Equation Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>For Equation Clause</em>'.
   * @generated
   */
  ForEquationClause createForEquationClause();

  /**
   * Returns a new object of class '<em>For Algorithm Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>For Algorithm Clause</em>'.
   * @generated
   */
  ForAlgorithmClause createForAlgorithmClause();

  /**
   * Returns a new object of class '<em>While Clause</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>While Clause</em>'.
   * @generated
   */
  WhileClause createWhileClause();

  /**
   * Returns a new object of class '<em>when clause e</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>when clause e</em>'.
   * @generated
   */
  when_clause_e createwhen_clause_e();

  /**
   * Returns a new object of class '<em>else when e</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>else when e</em>'.
   * @generated
   */
  else_when_e createelse_when_e();

  /**
   * Returns a new object of class '<em>when clause a</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>when clause a</em>'.
   * @generated
   */
  when_clause_a createwhen_clause_a();

  /**
   * Returns a new object of class '<em>else when a</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>else when a</em>'.
   * @generated
   */
  else_when_a createelse_when_a();

  /**
   * Returns a new object of class '<em>equation rule elseif</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>equation rule elseif</em>'.
   * @generated
   */
  equation_rule_elseif createequation_rule_elseif();

  /**
   * Returns a new object of class '<em>algorithm elseif</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>algorithm elseif</em>'.
   * @generated
   */
  algorithm_elseif createalgorithm_elseif();

  /**
   * Returns a new object of class '<em>Comment</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Comment</em>'.
   * @generated
   */
  Comment createComment();

  /**
   * Returns a new object of class '<em>Annotation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Annotation</em>'.
   * @generated
   */
  Annotation createAnnotation();

  /**
   * Returns a new object of class '<em>Public Element List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Public Element List</em>'.
   * @generated
   */
  PublicElementList createPublicElementList();

  /**
   * Returns a new object of class '<em>Protectes Element List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Protectes Element List</em>'.
   * @generated
   */
  ProtectesElementList createProtectesElementList();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ModelicaBasePackage getModelicaBasePackage();

} //ModelicaBaseFactory
