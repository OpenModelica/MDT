/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ComponentClause#getTypePrefix <em>Type Prefix</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ComponentClause#getTypeSpecifier <em>Type Specifier</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ComponentClause#getDeclarations <em>Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentClause()
 * @model
 * @generated
 */
public interface ComponentClause extends ClassElement
{
  /**
   * Returns the value of the '<em><b>Type Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Prefix</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Prefix</em>' attribute.
   * @see #setTypePrefix(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentClause_TypePrefix()
   * @model
   * @generated
   */
  String getTypePrefix();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ComponentClause#getTypePrefix <em>Type Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Prefix</em>' attribute.
   * @see #getTypePrefix()
   * @generated
   */
  void setTypePrefix(String value);

  /**
   * Returns the value of the '<em><b>Type Specifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Specifier</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Specifier</em>' containment reference.
   * @see #setTypeSpecifier(TypeSpecifier)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentClause_TypeSpecifier()
   * @model containment="true"
   * @generated
   */
  TypeSpecifier getTypeSpecifier();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ComponentClause#getTypeSpecifier <em>Type Specifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Specifier</em>' containment reference.
   * @see #getTypeSpecifier()
   * @generated
   */
  void setTypeSpecifier(TypeSpecifier value);

  /**
   * Returns the value of the '<em><b>Declarations</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.ComponentDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Declarations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Declarations</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentClause_Declarations()
   * @model containment="true"
   * @generated
   */
  EList<ComponentDeclaration> getDeclarations();

} // ComponentClause
