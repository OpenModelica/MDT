/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getSubscripts <em>Subscripts</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentDeclaration()
 * @model
 * @generated
 */
public interface ComponentDeclaration extends EObject
{
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentDeclaration_Subscripts()
   * @model
   * @generated
   */
  String getSubscripts();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getSubscripts <em>Subscripts</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Subscripts</em>' attribute.
   * @see #getSubscripts()
   * @generated
   */
  void setSubscripts(String value);

  /**
   * Returns the value of the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modification</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modification</em>' containment reference.
   * @see #setModification(Modification)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentDeclaration_Modification()
   * @model containment="true"
   * @generated
   */
  Modification getModification();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getModification <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modification</em>' containment reference.
   * @see #getModification()
   * @generated
   */
  void setModification(Modification value);

  /**
   * Returns the value of the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' attribute.
   * @see #setCondition(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentDeclaration_Condition()
   * @model
   * @generated
   */
  String getCondition();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getCondition <em>Condition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' attribute.
   * @see #getCondition()
   * @generated
   */
  void setCondition(String value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getComponentDeclaration_Comment()
   * @model containment="true"
   * @generated
   */
  Comment getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getComment <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' containment reference.
   * @see #getComment()
   * @generated
   */
  void setComment(Comment value);

} // ComponentDeclaration
