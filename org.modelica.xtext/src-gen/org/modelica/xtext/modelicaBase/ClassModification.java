/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Modification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassModification#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassModification#getTypeSpecifier <em>Type Specifier</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassModification#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassModification#getComment <em>Comment</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassModification#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassModification()
 * @model
 * @generated
 */
public interface ClassModification extends EntityDefinition, Modification, Annotation
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassModification_ClassType()
   * @model
   * @generated
   */
  ClassType getClassType();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassModification#getClassType <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Type</em>' attribute.
   * @see org.modelica.xtext.modelicaBase.ClassType
   * @see #getClassType()
   * @generated
   */
  void setClassType(ClassType value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassModification_TypeSpecifier()
   * @model containment="true"
   * @generated
   */
  TypeSpecifier getTypeSpecifier();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassModification#getTypeSpecifier <em>Type Specifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Specifier</em>' containment reference.
   * @see #getTypeSpecifier()
   * @generated
   */
  void setTypeSpecifier(TypeSpecifier value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassModification_Modification()
   * @model containment="true"
   * @generated
   */
  ClassModification getModification();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassModification#getModification <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modification</em>' containment reference.
   * @see #getModification()
   * @generated
   */
  void setModification(ClassModification value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassModification_Comment()
   * @model containment="true"
   * @generated
   */
  Comment getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassModification#getComment <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' containment reference.
   * @see #getComment()
   * @generated
   */
  void setComment(Comment value);

  /**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
   * The list contents are of type {@link org.modelica.xtext.modelicaBase.argument}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference list.
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassModification_Arguments()
   * @model containment="true"
   * @generated
   */
  EList<argument> getArguments();

} // ClassModification
