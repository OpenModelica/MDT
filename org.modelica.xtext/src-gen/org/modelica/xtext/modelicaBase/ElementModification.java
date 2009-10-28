/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Modification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementModification#getComponetRef <em>Componet Ref</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementModification#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementModification#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementModification()
 * @model
 * @generated
 */
public interface ElementModification extends argument
{
  /**
   * Returns the value of the '<em><b>Componet Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Componet Ref</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Componet Ref</em>' attribute.
   * @see #setComponetRef(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementModification_ComponetRef()
   * @model
   * @generated
   */
  String getComponetRef();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementModification#getComponetRef <em>Componet Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Componet Ref</em>' attribute.
   * @see #getComponetRef()
   * @generated
   */
  void setComponetRef(String value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementModification_Modification()
   * @model containment="true"
   * @generated
   */
  Modification getModification();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementModification#getModification <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modification</em>' containment reference.
   * @see #getModification()
   * @generated
   */
  void setModification(Modification value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementModification_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementModification#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

} // ElementModification
