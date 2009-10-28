/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Replaceable Element Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause#getEntity <em>Entity</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause#getConstrain <em>Constrain</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getReplaceableElementClause()
 * @model
 * @generated
 */
public interface ReplaceableElementClause extends ClassElement
{
  /**
   * Returns the value of the '<em><b>Entity</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entity</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entity</em>' containment reference.
   * @see #setEntity(ClassElement)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getReplaceableElementClause_Entity()
   * @model containment="true"
   * @generated
   */
  ClassElement getEntity();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause#getEntity <em>Entity</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Entity</em>' containment reference.
   * @see #getEntity()
   * @generated
   */
  void setEntity(ClassElement value);

  /**
   * Returns the value of the '<em><b>Constrain</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constrain</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constrain</em>' containment reference.
   * @see #setConstrain(ConstrainingClause)
   * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getReplaceableElementClause_Constrain()
   * @model containment="true"
   * @generated
   */
  ConstrainingClause getConstrain();

  /**
   * Sets the value of the '{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause#getConstrain <em>Constrain</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constrain</em>' containment reference.
   * @see #getConstrain()
   * @generated
   */
  void setConstrain(ConstrainingClause value);

} // ReplaceableElementClause
