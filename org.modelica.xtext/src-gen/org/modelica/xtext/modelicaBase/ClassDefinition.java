/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#isEncapsulated <em>Encapsulated</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#isPartial <em>Partial</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#isExpandable <em>Expandable</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getComment <em>Comment</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getElemList <em>Elem List</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getInitialEquationClauses <em>Initial Equation Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getInitialAlgorithmClauses <em>Initial Algorithm Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getEquationClauses <em>Equation Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getAlgorithmClauses <em>Algorithm Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassDefinition#getExtClause <em>Ext Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition()
 * @model
 * @generated
 */
public interface ClassDefinition extends EntityDefinition, ClassElement, ElementRedeclaration
{
  /**
   * Returns the value of the '<em><b>Encapsulated</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Encapsulated</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Encapsulated</em>' attribute.
   * @see #setEncapsulated(boolean)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_Encapsulated()
   * @model
   * @generated
   */
  boolean isEncapsulated();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassDefinition#isEncapsulated <em>Encapsulated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Encapsulated</em>' attribute.
   * @see #isEncapsulated()
   * @generated
   */
  void setEncapsulated(boolean value);

  /**
   * Returns the value of the '<em><b>Partial</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Partial</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Partial</em>' attribute.
   * @see #setPartial(boolean)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_Partial()
   * @model
   * @generated
   */
  boolean isPartial();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassDefinition#isPartial <em>Partial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Partial</em>' attribute.
   * @see #isPartial()
   * @generated
   */
  void setPartial(boolean value);

  /**
   * Returns the value of the '<em><b>Expandable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expandable</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expandable</em>' attribute.
   * @see #setExpandable(boolean)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_Expandable()
   * @model
   * @generated
   */
  boolean isExpandable();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassDefinition#isExpandable <em>Expandable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expandable</em>' attribute.
   * @see #isExpandable()
   * @generated
   */
  void setExpandable(boolean value);

  /**
   * Returns the value of the '<em><b>Class Type</b></em>' attribute.
   * The literals are from the enumeration {@link org.modelica.xtext.modelicaBase.ClassType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Type</em>' attribute.
   * @see org.modelica.xtext.modelicaBase.ClassType
   * @see #setClassType(ClassType)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_ClassType()
   * @model
   * @generated
   */
  ClassType getClassType();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getClassType <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Type</em>' attribute.
   * @see org.modelica.xtext.modelicaBase.ClassType
   * @see #getClassType()
   * @generated
   */
  void setClassType(ClassType value);

  /**
   * Returns the value of the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' attribute.
   * @see #setComment(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modification</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modification</em>' containment reference.
   * @see #setModification(ClassModification)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_Modification()
   * @model containment="true"
   * @generated
   */
  ClassModification getModification();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getModification <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modification</em>' containment reference.
   * @see #getModification()
   * @generated
   */
  void setModification(ClassModification value);

  /**
   * Returns the value of the '<em><b>Elem List</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.ElementList}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elem List</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elem List</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_ElemList()
   * @model containment="true"
   * @generated
   */
  EList<ElementList> getElemList();

  /**
   * Returns the value of the '<em><b>Initial Equation Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.initial_equation_rule_clause}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial Equation Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial Equation Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_InitialEquationClauses()
   * @model containment="true"
   * @generated
   */
  EList<initial_equation_rule_clause> getInitialEquationClauses();

  /**
   * Returns the value of the '<em><b>Initial Algorithm Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.initial_algorithm_clause}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial Algorithm Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial Algorithm Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_InitialAlgorithmClauses()
   * @model containment="true"
   * @generated
   */
  EList<initial_algorithm_clause> getInitialAlgorithmClauses();

  /**
   * Returns the value of the '<em><b>Equation Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.EquationClause}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Equation Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Equation Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_EquationClauses()
   * @model containment="true"
   * @generated
   */
  EList<EquationClause> getEquationClauses();

  /**
   * Returns the value of the '<em><b>Algorithm Clauses</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.algorithm_clause}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Algorithm Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Algorithm Clauses</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_AlgorithmClauses()
   * @model containment="true"
   * @generated
   */
  EList<algorithm_clause> getAlgorithmClauses();

  /**
   * Returns the value of the '<em><b>Ext Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ext Clause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ext Clause</em>' containment reference.
   * @see #setExtClause(ExternalClause)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassDefinition_ExtClause()
   * @model containment="true"
   * @generated
   */
  ExternalClause getExtClause();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getExtClause <em>Ext Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ext Clause</em>' containment reference.
   * @see #getExtClause()
   * @generated
   */
  void setExtClause(ExternalClause value);

} // ClassDefinition
