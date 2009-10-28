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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.modelica.xtext.modelicaBase.Comment;
import org.modelica.xtext.modelicaBase.ComponentDeclaration;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.Modification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl#getSubscripts <em>Subscripts</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentDeclarationImpl extends MinimalEObjectImpl.Container implements ComponentDeclaration
{
  /**
   * The default value of the '{@link #getSubscripts() <em>Subscripts</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubscripts()
   * @generated
   * @ordered
   */
  protected static final String SUBSCRIPTS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSubscripts() <em>Subscripts</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubscripts()
   * @generated
   * @ordered
   */
  protected String subscripts = SUBSCRIPTS_EDEFAULT;

  /**
   * The cached value of the '{@link #getModification() <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModification()
   * @generated
   * @ordered
   */
  protected Modification modification;

  /**
   * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
  protected static final String CONDITION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
  protected String condition = CONDITION_EDEFAULT;

  /**
   * The cached value of the '{@link #getComment() <em>Comment</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected Comment comment;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComponentDeclarationImpl()
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
    return ModelicaBasePackage.Literals.COMPONENT_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSubscripts()
  {
    return subscripts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubscripts(String newSubscripts)
  {
    String oldSubscripts = subscripts;
    subscripts = newSubscripts;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_DECLARATION__SUBSCRIPTS, oldSubscripts, subscripts));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Modification getModification()
  {
    return modification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModification(Modification newModification, NotificationChain msgs)
  {
    Modification oldModification = modification;
    modification = newModification;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION, oldModification, newModification);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModification(Modification newModification)
  {
    if (newModification != modification)
    {
      NotificationChain msgs = null;
      if (modification != null)
        msgs = ((InternalEObject)modification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION, null, msgs);
      if (newModification != null)
        msgs = ((InternalEObject)newModification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION, null, msgs);
      msgs = basicSetModification(newModification, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION, newModification, newModification));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCondition()
  {
    return condition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCondition(String newCondition)
  {
    String oldCondition = condition;
    condition = newCondition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_DECLARATION__CONDITION, oldCondition, condition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Comment getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComment(Comment newComment, NotificationChain msgs)
  {
    Comment oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT, oldComment, newComment);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(Comment newComment)
  {
    if (newComment != comment)
    {
      NotificationChain msgs = null;
      if (comment != null)
        msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT, null, msgs);
      if (newComment != null)
        msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT, null, msgs);
      msgs = basicSetComment(newComment, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT, newComment, newComment));
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
      case ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION:
        return basicSetModification(null, msgs);
      case ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT:
        return basicSetComment(null, msgs);
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
      case ModelicaBasePackage.COMPONENT_DECLARATION__SUBSCRIPTS:
        return getSubscripts();
      case ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION:
        return getModification();
      case ModelicaBasePackage.COMPONENT_DECLARATION__CONDITION:
        return getCondition();
      case ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT:
        return getComment();
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
      case ModelicaBasePackage.COMPONENT_DECLARATION__SUBSCRIPTS:
        setSubscripts((String)newValue);
        return;
      case ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION:
        setModification((Modification)newValue);
        return;
      case ModelicaBasePackage.COMPONENT_DECLARATION__CONDITION:
        setCondition((String)newValue);
        return;
      case ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT:
        setComment((Comment)newValue);
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
      case ModelicaBasePackage.COMPONENT_DECLARATION__SUBSCRIPTS:
        setSubscripts(SUBSCRIPTS_EDEFAULT);
        return;
      case ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION:
        setModification((Modification)null);
        return;
      case ModelicaBasePackage.COMPONENT_DECLARATION__CONDITION:
        setCondition(CONDITION_EDEFAULT);
        return;
      case ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT:
        setComment((Comment)null);
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
      case ModelicaBasePackage.COMPONENT_DECLARATION__SUBSCRIPTS:
        return SUBSCRIPTS_EDEFAULT == null ? subscripts != null : !SUBSCRIPTS_EDEFAULT.equals(subscripts);
      case ModelicaBasePackage.COMPONENT_DECLARATION__MODIFICATION:
        return modification != null;
      case ModelicaBasePackage.COMPONENT_DECLARATION__CONDITION:
        return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
      case ModelicaBasePackage.COMPONENT_DECLARATION__COMMENT:
        return comment != null;
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
    result.append(" (subscripts: ");
    result.append(subscripts);
    result.append(", condition: ");
    result.append(condition);
    result.append(')');
    return result.toString();
  }

} //ComponentDeclarationImpl
