/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.annotationImpl#getClass_Mod <em>Class Mod</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class annotationImpl extends MinimalEObjectImpl.Container implements annotation
{
  /**
   * The cached value of the '{@link #getClass_Mod() <em>Class Mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClass_Mod()
   * @generated
   * @ordered
   */
  protected class_modification class_Mod;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected annotationImpl()
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
    return ModificationPackage.Literals.ANNOTATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class_modification getClass_Mod()
  {
    return class_Mod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetClass_Mod(class_modification newClass_Mod, NotificationChain msgs)
  {
    class_modification oldClass_Mod = class_Mod;
    class_Mod = newClass_Mod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ANNOTATION__CLASS_MOD, oldClass_Mod, newClass_Mod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClass_Mod(class_modification newClass_Mod)
  {
    if (newClass_Mod != class_Mod)
    {
      NotificationChain msgs = null;
      if (class_Mod != null)
        msgs = ((InternalEObject)class_Mod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ANNOTATION__CLASS_MOD, null, msgs);
      if (newClass_Mod != null)
        msgs = ((InternalEObject)newClass_Mod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ANNOTATION__CLASS_MOD, null, msgs);
      msgs = basicSetClass_Mod(newClass_Mod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ANNOTATION__CLASS_MOD, newClass_Mod, newClass_Mod));
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
      case ModificationPackage.ANNOTATION__CLASS_MOD:
        return basicSetClass_Mod(null, msgs);
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
      case ModificationPackage.ANNOTATION__CLASS_MOD:
        return getClass_Mod();
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
      case ModificationPackage.ANNOTATION__CLASS_MOD:
        setClass_Mod((class_modification)newValue);
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
      case ModificationPackage.ANNOTATION__CLASS_MOD:
        setClass_Mod((class_modification)null);
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
      case ModificationPackage.ANNOTATION__CLASS_MOD:
        return class_Mod != null;
    }
    return super.eIsSet(featureID);
  }

} //annotationImpl
