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

import org.modelica.xtext.modelicaBase.ClassElement;
import org.modelica.xtext.modelicaBase.ConstrainingClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.ReplaceableElementClause;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Replaceable Element Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ReplaceableElementClauseImpl#getEntity <em>Entity</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ReplaceableElementClauseImpl#getConstrain <em>Constrain</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReplaceableElementClauseImpl extends ClassElementImpl implements ReplaceableElementClause
{
  /**
   * The cached value of the '{@link #getEntity() <em>Entity</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntity()
   * @generated
   * @ordered
   */
  protected ClassElement entity;

  /**
   * The cached value of the '{@link #getConstrain() <em>Constrain</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstrain()
   * @generated
   * @ordered
   */
  protected ConstrainingClause constrain;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReplaceableElementClauseImpl()
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
    return ModelicaBasePackage.Literals.REPLACEABLE_ELEMENT_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassElement getEntity()
  {
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEntity(ClassElement newEntity, NotificationChain msgs)
  {
    ClassElement oldEntity = entity;
    entity = newEntity;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY, oldEntity, newEntity);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEntity(ClassElement newEntity)
  {
    if (newEntity != entity)
    {
      NotificationChain msgs = null;
      if (entity != null)
        msgs = ((InternalEObject)entity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY, null, msgs);
      if (newEntity != null)
        msgs = ((InternalEObject)newEntity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY, null, msgs);
      msgs = basicSetEntity(newEntity, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY, newEntity, newEntity));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstrainingClause getConstrain()
  {
    return constrain;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstrain(ConstrainingClause newConstrain, NotificationChain msgs)
  {
    ConstrainingClause oldConstrain = constrain;
    constrain = newConstrain;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN, oldConstrain, newConstrain);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstrain(ConstrainingClause newConstrain)
  {
    if (newConstrain != constrain)
    {
      NotificationChain msgs = null;
      if (constrain != null)
        msgs = ((InternalEObject)constrain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN, null, msgs);
      if (newConstrain != null)
        msgs = ((InternalEObject)newConstrain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN, null, msgs);
      msgs = basicSetConstrain(newConstrain, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN, newConstrain, newConstrain));
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
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY:
        return basicSetEntity(null, msgs);
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN:
        return basicSetConstrain(null, msgs);
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
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY:
        return getEntity();
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN:
        return getConstrain();
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
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY:
        setEntity((ClassElement)newValue);
        return;
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN:
        setConstrain((ConstrainingClause)newValue);
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
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY:
        setEntity((ClassElement)null);
        return;
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN:
        setConstrain((ConstrainingClause)null);
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
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__ENTITY:
        return entity != null;
      case ModelicaBasePackage.REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN:
        return constrain != null;
    }
    return super.eIsSet(featureID);
  }

} //ReplaceableElementClauseImpl
