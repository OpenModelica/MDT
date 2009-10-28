/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Component Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getTypePrefix <em>Type Prefix</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getTypeSpecifier <em>Type Specifier</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getSubscripts <em>Subscripts</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementComponentClause()
 * @model
 * @generated
 */
public interface ElementComponentClause extends ElementRedeclaration
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementComponentClause_TypePrefix()
   * @model
   * @generated
   */
  String getTypePrefix();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getTypePrefix <em>Type Prefix</em>}' attribute.
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementComponentClause_TypeSpecifier()
   * @model containment="true"
   * @generated
   */
  TypeSpecifier getTypeSpecifier();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getTypeSpecifier <em>Type Specifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Specifier</em>' containment reference.
   * @see #getTypeSpecifier()
   * @generated
   */
  void setTypeSpecifier(TypeSpecifier value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementComponentClause_Subscripts()
   * @model
   * @generated
   */
  String getSubscripts();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getSubscripts <em>Subscripts</em>}' attribute.
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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementComponentClause_Modification()
   * @model containment="true"
   * @generated
   */
  Modification getModification();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getModification <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modification</em>' containment reference.
   * @see #getModification()
   * @generated
   */
  void setModification(Modification value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getElementComponentClause_Comment()
   * @model containment="true"
   * @generated
   */
  Comment getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getComment <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' containment reference.
   * @see #getComment()
   * @generated
   */
  void setComment(Comment value);

} // ElementComponentClause
