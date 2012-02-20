/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_indices;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fun Arguments for</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.Fun_Arguments_forImpl#getFor_indices <em>For indices</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Fun_Arguments_forImpl extends MinimalEObjectImpl.Container implements Fun_Arguments_for
{
  /**
   * The cached value of the '{@link #getFor_indices() <em>For indices</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFor_indices()
   * @generated
   * @ordered
   */
  protected for_indices for_indices;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Fun_Arguments_forImpl()
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
    return ModeleditorPackage.Literals.FUN_ARGUMENTS_FOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_indices getFor_indices()
  {
    return for_indices;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFor_indices(for_indices newFor_indices, NotificationChain msgs)
  {
    for_indices oldFor_indices = for_indices;
    for_indices = newFor_indices;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES, oldFor_indices, newFor_indices);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFor_indices(for_indices newFor_indices)
  {
    if (newFor_indices != for_indices)
    {
      NotificationChain msgs = null;
      if (for_indices != null)
        msgs = ((InternalEObject)for_indices).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES, null, msgs);
      if (newFor_indices != null)
        msgs = ((InternalEObject)newFor_indices).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES, null, msgs);
      msgs = basicSetFor_indices(newFor_indices, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES, newFor_indices, newFor_indices));
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
      case ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES:
        return basicSetFor_indices(null, msgs);
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
      case ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES:
        return getFor_indices();
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
      case ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES:
        setFor_indices((for_indices)newValue);
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
      case ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES:
        setFor_indices((for_indices)null);
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
      case ModeleditorPackage.FUN_ARGUMENTS_FOR__FOR_INDICES:
        return for_indices != null;
    }
    return super.eIsSet(featureID);
  }

} //Fun_Arguments_forImpl
