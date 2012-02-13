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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>connect clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.connect_clauseImpl#getConnector1 <em>Connector1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.connect_clauseImpl#getConnector2 <em>Connector2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class connect_clauseImpl extends MinimalEObjectImpl.Container implements connect_clause
{
  /**
   * The cached value of the '{@link #getConnector1() <em>Connector1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConnector1()
   * @generated
   * @ordered
   */
  protected component_reference connector1;

  /**
   * The cached value of the '{@link #getConnector2() <em>Connector2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConnector2()
   * @generated
   * @ordered
   */
  protected component_reference connector2;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected connect_clauseImpl()
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
    return ModificationPackage.Literals.CONNECT_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference getConnector1()
  {
    return connector1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConnector1(component_reference newConnector1, NotificationChain msgs)
  {
    component_reference oldConnector1 = connector1;
    connector1 = newConnector1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.CONNECT_CLAUSE__CONNECTOR1, oldConnector1, newConnector1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConnector1(component_reference newConnector1)
  {
    if (newConnector1 != connector1)
    {
      NotificationChain msgs = null;
      if (connector1 != null)
        msgs = ((InternalEObject)connector1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CONNECT_CLAUSE__CONNECTOR1, null, msgs);
      if (newConnector1 != null)
        msgs = ((InternalEObject)newConnector1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CONNECT_CLAUSE__CONNECTOR1, null, msgs);
      msgs = basicSetConnector1(newConnector1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.CONNECT_CLAUSE__CONNECTOR1, newConnector1, newConnector1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference getConnector2()
  {
    return connector2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConnector2(component_reference newConnector2, NotificationChain msgs)
  {
    component_reference oldConnector2 = connector2;
    connector2 = newConnector2;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.CONNECT_CLAUSE__CONNECTOR2, oldConnector2, newConnector2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConnector2(component_reference newConnector2)
  {
    if (newConnector2 != connector2)
    {
      NotificationChain msgs = null;
      if (connector2 != null)
        msgs = ((InternalEObject)connector2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CONNECT_CLAUSE__CONNECTOR2, null, msgs);
      if (newConnector2 != null)
        msgs = ((InternalEObject)newConnector2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CONNECT_CLAUSE__CONNECTOR2, null, msgs);
      msgs = basicSetConnector2(newConnector2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.CONNECT_CLAUSE__CONNECTOR2, newConnector2, newConnector2));
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
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR1:
        return basicSetConnector1(null, msgs);
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR2:
        return basicSetConnector2(null, msgs);
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
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR1:
        return getConnector1();
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR2:
        return getConnector2();
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
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR1:
        setConnector1((component_reference)newValue);
        return;
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR2:
        setConnector2((component_reference)newValue);
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
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR1:
        setConnector1((component_reference)null);
        return;
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR2:
        setConnector2((component_reference)null);
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
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR1:
        return connector1 != null;
      case ModificationPackage.CONNECT_CLAUSE__CONNECTOR2:
        return connector2 != null;
    }
    return super.eIsSet(featureID);
  }

} //connect_clauseImpl
