/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.relation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>logical factor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_factorImpl#getLogical_factor <em>Logical factor</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.logical_factorImpl#getRelation <em>Relation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class logical_factorImpl extends logical_termImpl implements logical_factor
{
  /**
   * The cached value of the '{@link #getLogical_factor() <em>Logical factor</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogical_factor()
   * @generated
   * @ordered
   */
  protected EList<logical_factor> logical_factor;

  /**
   * The cached value of the '{@link #getRelation() <em>Relation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelation()
   * @generated
   * @ordered
   */
  protected relation relation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected logical_factorImpl()
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
    return ModeleditorPackage.Literals.LOGICAL_FACTOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<logical_factor> getLogical_factor()
  {
    if (logical_factor == null)
    {
      logical_factor = new EObjectContainmentEList<logical_factor>(logical_factor.class, this, ModeleditorPackage.LOGICAL_FACTOR__LOGICAL_FACTOR);
    }
    return logical_factor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public relation getRelation()
  {
    return relation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRelation(relation newRelation, NotificationChain msgs)
  {
    relation oldRelation = relation;
    relation = newRelation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.LOGICAL_FACTOR__RELATION, oldRelation, newRelation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRelation(relation newRelation)
  {
    if (newRelation != relation)
    {
      NotificationChain msgs = null;
      if (relation != null)
        msgs = ((InternalEObject)relation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.LOGICAL_FACTOR__RELATION, null, msgs);
      if (newRelation != null)
        msgs = ((InternalEObject)newRelation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.LOGICAL_FACTOR__RELATION, null, msgs);
      msgs = basicSetRelation(newRelation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.LOGICAL_FACTOR__RELATION, newRelation, newRelation));
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
      case ModeleditorPackage.LOGICAL_FACTOR__LOGICAL_FACTOR:
        return ((InternalEList<?>)getLogical_factor()).basicRemove(otherEnd, msgs);
      case ModeleditorPackage.LOGICAL_FACTOR__RELATION:
        return basicSetRelation(null, msgs);
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
      case ModeleditorPackage.LOGICAL_FACTOR__LOGICAL_FACTOR:
        return getLogical_factor();
      case ModeleditorPackage.LOGICAL_FACTOR__RELATION:
        return getRelation();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModeleditorPackage.LOGICAL_FACTOR__LOGICAL_FACTOR:
        getLogical_factor().clear();
        getLogical_factor().addAll((Collection<? extends logical_factor>)newValue);
        return;
      case ModeleditorPackage.LOGICAL_FACTOR__RELATION:
        setRelation((relation)newValue);
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
      case ModeleditorPackage.LOGICAL_FACTOR__LOGICAL_FACTOR:
        getLogical_factor().clear();
        return;
      case ModeleditorPackage.LOGICAL_FACTOR__RELATION:
        setRelation((relation)null);
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
      case ModeleditorPackage.LOGICAL_FACTOR__LOGICAL_FACTOR:
        return logical_factor != null && !logical_factor.isEmpty();
      case ModeleditorPackage.LOGICAL_FACTOR__RELATION:
        return relation != null;
    }
    return super.eIsSet(featureID);
  }

} //logical_factorImpl
