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
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>extends clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.extends_clauseImpl#getClass_Mod <em>Class Mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.extends_clauseImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class extends_clauseImpl extends elementImpl implements extends_clause
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
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected annotation annotation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected extends_clauseImpl()
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
    return ModificationPackage.Literals.EXTENDS_CLAUSE;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD, oldClass_Mod, newClass_Mod);
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
        msgs = ((InternalEObject)class_Mod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD, null, msgs);
      if (newClass_Mod != null)
        msgs = ((InternalEObject)newClass_Mod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD, null, msgs);
      msgs = basicSetClass_Mod(newClass_Mod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD, newClass_Mod, newClass_Mod));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public annotation getAnnotation()
  {
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation(annotation newAnnotation, NotificationChain msgs)
  {
    annotation oldAnnotation = annotation;
    annotation = newAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EXTENDS_CLAUSE__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation(annotation newAnnotation)
  {
    if (newAnnotation != annotation)
    {
      NotificationChain msgs = null;
      if (annotation != null)
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EXTENDS_CLAUSE__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EXTENDS_CLAUSE__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EXTENDS_CLAUSE__ANNOTATION, newAnnotation, newAnnotation));
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
      case ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD:
        return basicSetClass_Mod(null, msgs);
      case ModificationPackage.EXTENDS_CLAUSE__ANNOTATION:
        return basicSetAnnotation(null, msgs);
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
      case ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD:
        return getClass_Mod();
      case ModificationPackage.EXTENDS_CLAUSE__ANNOTATION:
        return getAnnotation();
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
      case ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD:
        setClass_Mod((class_modification)newValue);
        return;
      case ModificationPackage.EXTENDS_CLAUSE__ANNOTATION:
        setAnnotation((annotation)newValue);
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
      case ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD:
        setClass_Mod((class_modification)null);
        return;
      case ModificationPackage.EXTENDS_CLAUSE__ANNOTATION:
        setAnnotation((annotation)null);
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
      case ModificationPackage.EXTENDS_CLAUSE__CLASS_MOD:
        return class_Mod != null;
      case ModificationPackage.EXTENDS_CLAUSE__ANNOTATION:
        return annotation != null;
    }
    return super.eIsSet(featureID);
  }

} //extends_clauseImpl
