/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.TypeSpecifier#getName <em>Name</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.TypeSpecifier#getSubscripts <em>Subscripts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getTypeSpecifier()
 * @model
 * @generated
 */
public interface TypeSpecifier extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getTypeSpecifier_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.TypeSpecifier#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Subscripts</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subscripts</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subscripts</em>' attribute.
   * @see #setSubscripts(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getTypeSpecifier_Subscripts()
   * @model
   * @generated
   */
  String getSubscripts();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.TypeSpecifier#getSubscripts <em>Subscripts</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subscripts</em>' attribute.
   * @see #getSubscripts()
   * @generated
   */
  void setSubscripts(String value);

} // TypeSpecifier
