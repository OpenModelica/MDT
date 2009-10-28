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

import org.modelica.xtext.modelicaBase.ClassModification;
import org.modelica.xtext.modelicaBase.Comment;
import org.modelica.xtext.modelicaBase.ConstrainingClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraining Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConstrainingClauseImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConstrainingClauseImpl#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConstrainingClauseImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstrainingClauseImpl extends MinimalEObjectImpl.Container implements ConstrainingClause
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getModification() <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModification()
   * @generated
   * @ordered
   */
  protected ClassModification modification;

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
  protected ConstrainingClauseImpl()
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
    return ModelicaBasePackage.Literals.CONSTRAINING_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CONSTRAINING_CLAUSE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassModification getModification()
  {
    return modification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModification(ClassModification newModification, NotificationChain msgs)
  {
    ClassModification oldModification = modification;
    modification = newModification;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION, oldModification, newModification);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModification(ClassModification newModification)
  {
    if (newModification != modification)
    {
      NotificationChain msgs = null;
      if (modification != null)
        msgs = ((InternalEObject)modification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION, null, msgs);
      if (newModification != null)
        msgs = ((InternalEObject)newModification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION, null, msgs);
      msgs = basicSetModification(newModification, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION, newModification, newModification));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT, oldComment, newComment);
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
        msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT, null, msgs);
      if (newComment != null)
        msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT, null, msgs);
      msgs = basicSetComment(newComment, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT, newComment, newComment));
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
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION:
        return basicSetModification(null, msgs);
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__NAME:
        return getName();
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION:
        return getModification();
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__NAME:
        setName((String)newValue);
        return;
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION:
        setModification((ClassModification)newValue);
        return;
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION:
        setModification((ClassModification)null);
        return;
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__MODIFICATION:
        return modification != null;
      case ModelicaBasePackage.CONSTRAINING_CLAUSE__COMMENT:
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ConstrainingClauseImpl
