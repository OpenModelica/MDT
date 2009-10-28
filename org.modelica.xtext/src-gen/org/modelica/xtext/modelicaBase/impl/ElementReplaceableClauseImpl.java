/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.modelica.xtext.modelicaBase.ConstrainingClause;
import org.modelica.xtext.modelicaBase.ElementRedeclaration;
import org.modelica.xtext.modelicaBase.ElementReplaceableClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Replaceable Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ElementReplaceableClauseImpl#getElement <em>Element</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ElementReplaceableClauseImpl#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementReplaceableClauseImpl extends argumentImpl implements ElementReplaceableClause
{
  /**
   * The cached value of the '{@link #getElement() <em>Element</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElement()
   * @generated
   * @ordered
   */
  protected ElementRedeclaration element;

  /**
   * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraint()
   * @generated
   * @ordered
   */
  protected ConstrainingClause constraint;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ElementReplaceableClauseImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ModelicaBasePackage.Literals.ELEMENT_REPLACEABLE_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementRedeclaration getElement()
  {
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElement(ElementRedeclaration newElement, NotificationChain msgs)
  {
    ElementRedeclaration oldElement = element;
    element = newElement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT, oldElement, newElement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElement(ElementRedeclaration newElement)
  {
    if (newElement != element)
    {
      NotificationChain msgs = null;
      if (element != null)
        msgs = ((InternalEObject)element).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT, null, msgs);
      if (newElement != null)
        msgs = ((InternalEObject)newElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT, null, msgs);
      msgs = basicSetElement(newElement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT, newElement, newElement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstrainingClause getConstraint()
  {
    return constraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstraint(ConstrainingClause newConstraint, NotificationChain msgs)
  {
    ConstrainingClause oldConstraint = constraint;
    constraint = newConstraint;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT, oldConstraint, newConstraint);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstraint(ConstrainingClause newConstraint)
  {
    if (newConstraint != constraint)
    {
      NotificationChain msgs = null;
      if (constraint != null)
        msgs = ((InternalEObject)constraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT, null, msgs);
      if (newConstraint != null)
        msgs = ((InternalEObject)newConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT, null, msgs);
      msgs = basicSetConstraint(newConstraint, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT, newConstraint, newConstraint));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT:
        return basicSetElement(null, msgs);
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT:
        return basicSetConstraint(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT:
        return getElement();
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT:
        return getConstraint();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT:
        setElement((ElementRedeclaration)newValue);
        return;
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT:
        setConstraint((ConstrainingClause)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT:
        setElement((ElementRedeclaration)null);
        return;
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT:
        setConstraint((ConstrainingClause)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__ELEMENT:
        return element != null;
      case ModelicaBasePackage.ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT:
        return constraint != null;
    }
    return super.eIsSet(featureID);
  }

} //ElementReplaceableClauseImpl
