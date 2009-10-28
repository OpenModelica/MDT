/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.Enumeration#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.Enumeration#getIdent <em>Ident</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.Enumeration#getEnumComments <em>Enum Comments</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.Enumeration#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEnumeration()
 * @model
 * @generated
 */
public interface Enumeration extends EntityDefinition
{
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEnumeration_ClassType()
   * @model
   * @generated
   */
  ClassType getClassType();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.Enumeration#getClassType <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Type</em>' attribute.
   * @see org.modelica.xtext.modelicaBase.ClassType
   * @see #getClassType()
   * @generated
   */
  void setClassType(ClassType value);

  /**
   * Returns the value of the '<em><b>Ident</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ident</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ident</em>' attribute list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEnumeration_Ident()
   * @model unique="false"
   * @generated
   */
  EList<String> getIdent();

  /**
   * Returns the value of the '<em><b>Enum Comments</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.Comment}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Enum Comments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Enum Comments</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEnumeration_EnumComments()
   * @model containment="true"
   * @generated
   */
  EList<Comment> getEnumComments();

  /**
   * Returns the value of the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Comment</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' containment reference.
   * @see #setComment(Comment)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getEnumeration_Comment()
   * @model containment="true"
   * @generated
   */
  Comment getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.Enumeration#getComment <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' containment reference.
   * @see #getComment()
   * @generated
   */
  void setComment(Comment value);

} // Enumeration
