/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ExternalClause#getLanguageSpecification <em>Language Specification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ExternalClause#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ExternalClause#getExtAnnotation <em>Ext Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExternalClause()
 * @model
 * @generated
 */
public interface ExternalClause extends EObject
{
  /**
   * Returns the value of the '<em><b>Language Specification</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Language Specification</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Language Specification</em>' attribute.
   * @see #setLanguageSpecification(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExternalClause_LanguageSpecification()
   * @model
   * @generated
   */
  String getLanguageSpecification();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ExternalClause#getLanguageSpecification <em>Language Specification</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Language Specification</em>' attribute.
   * @see #getLanguageSpecification()
   * @generated
   */
  void setLanguageSpecification(String value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(Annotation)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExternalClause_Annotation()
   * @model containment="true"
   * @generated
   */
  Annotation getAnnotation();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ExternalClause#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(Annotation value);

  /**
   * Returns the value of the '<em><b>Ext Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ext Annotation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ext Annotation</em>' containment reference.
   * @see #setExtAnnotation(Annotation)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExternalClause_ExtAnnotation()
   * @model containment="true"
   * @generated
   */
  Annotation getExtAnnotation();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ExternalClause#getExtAnnotation <em>Ext Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ext Annotation</em>' containment reference.
   * @see #getExtAnnotation()
   * @generated
   */
  void setExtAnnotation(Annotation value);

} // ExternalClause
