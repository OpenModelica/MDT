/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extends Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ExtendsClause#getBaseClass <em>Base Class</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ExtendsClause#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ExtendsClause#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExtendsClause()
 * @model
 * @generated
 */
public interface ExtendsClause extends ClassElement
{
  /**
   * Returns the value of the '<em><b>Base Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Base Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base Class</em>' attribute.
   * @see #setBaseClass(String)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExtendsClause_BaseClass()
   * @model
   * @generated
   */
  String getBaseClass();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ExtendsClause#getBaseClass <em>Base Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base Class</em>' attribute.
   * @see #getBaseClass()
   * @generated
   */
  void setBaseClass(String value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExtendsClause_Modification()
   * @model containment="true"
   * @generated
   */
  ClassModification getModification();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ExtendsClause#getModification <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modification</em>' containment reference.
   * @see #getModification()
   * @generated
   */
  void setModification(ClassModification value);

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
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getExtendsClause_Annotation()
   * @model containment="true"
   * @generated
   */
  Annotation getAnnotation();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ExtendsClause#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(Annotation value);

} // ExtendsClause
