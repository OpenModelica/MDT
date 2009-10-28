/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partial Derivative</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.PartialDerivative#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getPartialDerivative()
 * @model
 * @generated
 */
public interface PartialDerivative extends EntityDefinition
{
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getPartialDerivative_Comment()
   * @model containment="true"
   * @generated
   */
  Comment getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.PartialDerivative#getComment <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' containment reference.
   * @see #getComment()
   * @generated
   */
  void setComment(Comment value);

} // PartialDerivative
