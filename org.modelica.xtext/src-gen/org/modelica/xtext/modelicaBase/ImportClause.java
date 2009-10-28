/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ImportClause#getAsName <em>As Name</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ImportClause#getName <em>Name</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ImportClause#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getImportClause()
 * @model
 * @generated
 */
public interface ImportClause extends ClassElement
{
  /**
   * Returns the value of the '<em><b>As Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>As Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>As Name</em>' attribute.
   * @see #setAsName(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getImportClause_AsName()
   * @model
   * @generated
   */
  String getAsName();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ImportClause#getAsName <em>As Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>As Name</em>' attribute.
   * @see #getAsName()
   * @generated
   */
  void setAsName(String value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getImportClause_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ImportClause#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getImportClause_Comment()
   * @model containment="true"
   * @generated
   */
  Comment getComment();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ImportClause#getComment <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' containment reference.
   * @see #getComment()
   * @generated
   */
  void setComment(Comment value);

} // ImportClause
