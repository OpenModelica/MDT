/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Replaceable Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause#getElement <em>Element</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementReplaceableClause()
 * @model
 * @generated
 */
public interface ElementReplaceableClause extends argument, ElementRedeclaration
{
  /**
   * Returns the value of the '<em><b>Element</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element</em>' containment reference.
   * @see #setElement(ElementRedeclaration)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementReplaceableClause_Element()
   * @model containment="true"
   * @generated
   */
  ElementRedeclaration getElement();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause#getElement <em>Element</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element</em>' containment reference.
   * @see #getElement()
   * @generated
   */
  void setElement(ElementRedeclaration value);

  /**
   * Returns the value of the '<em><b>Constraint</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constraint</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraint</em>' containment reference.
   * @see #setConstraint(ConstrainingClause)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementReplaceableClause_Constraint()
   * @model containment="true"
   * @generated
   */
  ConstrainingClause getConstraint();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause#getConstraint <em>Constraint</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constraint</em>' containment reference.
   * @see #getConstraint()
   * @generated
   */
  void setConstraint(ConstrainingClause value);

} // ElementReplaceableClause
