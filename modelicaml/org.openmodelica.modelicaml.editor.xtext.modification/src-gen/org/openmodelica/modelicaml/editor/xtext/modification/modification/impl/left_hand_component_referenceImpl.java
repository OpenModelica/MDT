/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>left hand component reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl#getRef <em>Ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl#getSubscripts1 <em>Subscripts1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl#getRef1 <em>Ref1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.left_hand_component_referenceImpl#getSubscripts <em>Subscripts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class left_hand_component_referenceImpl extends modification_alternativesImpl implements left_hand_component_reference
{
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
   * The default value of the '{@link #getRef() <em>Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef()
   * @generated
   * @ordered
   */
  protected static final String REF_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRef() <em>Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef()
   * @generated
   * @ordered
   */
  protected String ref = REF_EDEFAULT;

  /**
   * The cached value of the '{@link #getSubscripts1() <em>Subscripts1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubscripts1()
   * @generated
   * @ordered
   */
  protected array_subscripts subscripts1;

  /**
   * The cached value of the '{@link #getRef1() <em>Ref1</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef1()
   * @generated
   * @ordered
   */
  protected EList<String> ref1;

  /**
   * The cached value of the '{@link #getSubscripts() <em>Subscripts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubscripts()
   * @generated
   * @ordered
   */
  protected EList<array_subscripts> subscripts;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected left_hand_component_referenceImpl()
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
    return ModificationPackage.Literals.LEFT_HAND_COMPONENT_REFERENCE;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR, oldExpr, newExpr);
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
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR, newExpr, newExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRef()
  {
    return ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRef(String newRef)
  {
    String oldRef = ref;
    ref = newRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF, oldRef, ref));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public array_subscripts getSubscripts1()
  {
    return subscripts1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSubscripts1(array_subscripts newSubscripts1, NotificationChain msgs)
  {
    array_subscripts oldSubscripts1 = subscripts1;
    subscripts1 = newSubscripts1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1, oldSubscripts1, newSubscripts1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubscripts1(array_subscripts newSubscripts1)
  {
    if (newSubscripts1 != subscripts1)
    {
      NotificationChain msgs = null;
      if (subscripts1 != null)
        msgs = ((InternalEObject)subscripts1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1, null, msgs);
      if (newSubscripts1 != null)
        msgs = ((InternalEObject)newSubscripts1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1, null, msgs);
      msgs = basicSetSubscripts1(newSubscripts1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1, newSubscripts1, newSubscripts1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getRef1()
  {
    if (ref1 == null)
    {
      ref1 = new EDataTypeEList<String>(String.class, this, ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF1);
    }
    return ref1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<array_subscripts> getSubscripts()
  {
    if (subscripts == null)
    {
      subscripts = new EObjectContainmentEList<array_subscripts>(array_subscripts.class, this, ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS);
    }
    return subscripts;
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
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR:
        return basicSetExpr(null, msgs);
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1:
        return basicSetSubscripts1(null, msgs);
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS:
        return ((InternalEList<?>)getSubscripts()).basicRemove(otherEnd, msgs);
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
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR:
        return getExpr();
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF:
        return getRef();
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1:
        return getSubscripts1();
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF1:
        return getRef1();
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS:
        return getSubscripts();
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
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR:
        setExpr((expression)newValue);
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF:
        setRef((String)newValue);
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1:
        setSubscripts1((array_subscripts)newValue);
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF1:
        getRef1().clear();
        getRef1().addAll((Collection<? extends String>)newValue);
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS:
        getSubscripts().clear();
        getSubscripts().addAll((Collection<? extends array_subscripts>)newValue);
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
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR:
        setExpr((expression)null);
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF:
        setRef(REF_EDEFAULT);
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1:
        setSubscripts1((array_subscripts)null);
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF1:
        getRef1().clear();
        return;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS:
        getSubscripts().clear();
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
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__EXPR:
        return expr != null;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF:
        return REF_EDEFAULT == null ? ref != null : !REF_EDEFAULT.equals(ref);
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1:
        return subscripts1 != null;
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__REF1:
        return ref1 != null && !ref1.isEmpty();
      case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS:
        return subscripts != null && !subscripts.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (ref: ");
    result.append(ref);
    result.append(", ref1: ");
    result.append(ref1);
    result.append(')');
    return result.toString();
  }

} //left_hand_component_referenceImpl
