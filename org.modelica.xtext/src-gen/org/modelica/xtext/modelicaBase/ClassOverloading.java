/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Overloading</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassOverloading#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ClassOverloading#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassOverloading()
 * @model
 * @generated
 */
public interface ClassOverloading extends EntityDefinition
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassOverloading_ClassType()
   * @model
   * @generated
   */
  ClassType getClassType();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassOverloading#getClassType <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Type</em>' attribute.
   * @see org.modelica.xtext.modelicaBase.ClassType
   * @see #getClassType()
   * @generated
   */
  void setClassType(ClassType value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassOverloading_Comment()
   * @model containment="true"
   * @generated
   */
  Comment getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ClassOverloading#getComment <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' containment reference.
   * @see #getComment()
   * @generated
   */
  void setComment(Comment value);

} // ClassOverloading
