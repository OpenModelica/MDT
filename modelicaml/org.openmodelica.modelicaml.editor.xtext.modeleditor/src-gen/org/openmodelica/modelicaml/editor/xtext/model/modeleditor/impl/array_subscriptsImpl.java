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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.subscript;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>array subscripts</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.array_subscriptsImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.array_subscriptsImpl#getSubscript <em>Subscript</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class array_subscriptsImpl extends MinimalEObjectImpl.Container implements array_subscripts
{
  /**
   * The cached value of the '{@link #getSub() <em>Sub</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSub()
   * @generated
   * @ordered
   */
  protected subscript sub;

  /**
   * The cached value of the '{@link #getSubscript() <em>Subscript</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubscript()
   * @generated
   * @ordered
   */
  protected EList<subscript> subscript;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected array_subscriptsImpl()
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
    return ModeleditorPackage.Literals.ARRAY_SUBSCRIPTS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public subscript getSub()
  {
    return sub;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSub(subscript newSub, NotificationChain msgs)
  {
    subscript oldSub = sub;
    sub = newSub;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB, oldSub, newSub);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSub(subscript newSub)
  {
    if (newSub != sub)
    {
      NotificationChain msgs = null;
      if (sub != null)
        msgs = ((InternalEObject)sub).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB, null, msgs);
      if (newSub != null)
        msgs = ((InternalEObject)newSub).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB, null, msgs);
      msgs = basicSetSub(newSub, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB, newSub, newSub));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<subscript> getSubscript()
  {
    if (subscript == null)
    {
      subscript = new EObjectContainmentEList<subscript>(subscript.class, this, ModeleditorPackage.ARRAY_SUBSCRIPTS__SUBSCRIPT);
    }
    return subscript;
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
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB:
        return basicSetSub(null, msgs);
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUBSCRIPT:
        return ((InternalEList<?>)getSubscript()).basicRemove(otherEnd, msgs);
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
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB:
        return getSub();
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUBSCRIPT:
        return getSubscript();
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
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB:
        setSub((subscript)newValue);
        return;
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUBSCRIPT:
        getSubscript().clear();
        getSubscript().addAll((Collection<? extends subscript>)newValue);
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
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB:
        setSub((subscript)null);
        return;
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUBSCRIPT:
        getSubscript().clear();
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
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUB:
        return sub != null;
      case ModeleditorPackage.ARRAY_SUBSCRIPTS__SUBSCRIPT:
        return subscript != null && !subscript.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //array_subscriptsImpl
