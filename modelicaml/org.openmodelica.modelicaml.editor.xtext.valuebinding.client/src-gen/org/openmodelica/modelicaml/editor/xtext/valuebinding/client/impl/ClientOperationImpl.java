/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientOperation;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.left_hand_component_reference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl#getLeft_hand <em>Left hand</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.ClientOperationImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClientOperationImpl extends MinimalEObjectImpl.Container implements ClientOperation
{
  /**
   * The cached value of the '{@link #getLeft_hand() <em>Left hand</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeft_hand()
   * @generated
   * @ordered
   */
  protected left_hand_component_reference left_hand;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected expression expr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClientOperationImpl()
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
    return ClientPackage.Literals.CLIENT_OPERATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public left_hand_component_reference getLeft_hand()
  {
    return left_hand;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLeft_hand(left_hand_component_reference newLeft_hand, NotificationChain msgs)
  {
    left_hand_component_reference oldLeft_hand = left_hand;
    left_hand = newLeft_hand;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.CLIENT_OPERATION__LEFT_HAND, oldLeft_hand, newLeft_hand);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLeft_hand(left_hand_component_reference newLeft_hand)
  {
    if (newLeft_hand != left_hand)
    {
      NotificationChain msgs = null;
      if (left_hand != null)
        msgs = ((InternalEObject)left_hand).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.CLIENT_OPERATION__LEFT_HAND, null, msgs);
      if (newLeft_hand != null)
        msgs = ((InternalEObject)newLeft_hand).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.CLIENT_OPERATION__LEFT_HAND, null, msgs);
      msgs = basicSetLeft_hand(newLeft_hand, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.CLIENT_OPERATION__LEFT_HAND, newLeft_hand, newLeft_hand));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(expression newExpr, NotificationChain msgs)
  {
    expression oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.CLIENT_OPERATION__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(expression newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.CLIENT_OPERATION__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.CLIENT_OPERATION__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.CLIENT_OPERATION__EXPR, newExpr, newExpr));
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
      case ClientPackage.CLIENT_OPERATION__LEFT_HAND:
        return basicSetLeft_hand(null, msgs);
      case ClientPackage.CLIENT_OPERATION__EXPR:
        return basicSetExpr(null, msgs);
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
      case ClientPackage.CLIENT_OPERATION__LEFT_HAND:
        return getLeft_hand();
      case ClientPackage.CLIENT_OPERATION__EXPR:
        return getExpr();
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
      case ClientPackage.CLIENT_OPERATION__LEFT_HAND:
        setLeft_hand((left_hand_component_reference)newValue);
        return;
      case ClientPackage.CLIENT_OPERATION__EXPR:
        setExpr((expression)newValue);
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
      case ClientPackage.CLIENT_OPERATION__LEFT_HAND:
        setLeft_hand((left_hand_component_reference)null);
        return;
      case ClientPackage.CLIENT_OPERATION__EXPR:
        setExpr((expression)null);
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
      case ClientPackage.CLIENT_OPERATION__LEFT_HAND:
        return left_hand != null;
      case ClientPackage.CLIENT_OPERATION__EXPR:
        return expr != null;
    }
    return super.eIsSet(featureID);
  }

} //ClientOperationImpl
