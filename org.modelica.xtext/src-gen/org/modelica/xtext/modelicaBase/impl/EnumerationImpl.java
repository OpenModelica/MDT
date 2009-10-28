/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.impl;

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

import org.modelica.xtext.modelicaBase.ClassType;
import org.modelica.xtext.modelicaBase.Comment;
import org.modelica.xtext.modelicaBase.Enumeration;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.EnumerationImpl#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.EnumerationImpl#getIdent <em>Ident</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.EnumerationImpl#getEnumComments <em>Enum Comments</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.EnumerationImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationImpl extends EntityDefinitionImpl implements Enumeration
{
  /**
   * The default value of the '{@link #getClassType() <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassType()
   * @generated
   * @ordered
   */
  protected static final ClassType CLASS_TYPE_EDEFAULT = ClassType.CLASS;

  /**
   * The cached value of the '{@link #getClassType() <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassType()
   * @generated
   * @ordered
   */
  protected ClassType classType = CLASS_TYPE_EDEFAULT;

  /**
   * The cached value of the '{@link #getIdent() <em>Ident</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdent()
   * @generated
   * @ordered
   */
  protected EList<String> ident;

  /**
   * The cached value of the '{@link #getEnumComments() <em>Enum Comments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnumComments()
   * @generated
   * @ordered
   */
  protected EList<Comment> enumComments;

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
  protected EnumerationImpl()
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
    return ModelicaBasePackage.Literals.ENUMERATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassType getClassType()
  {
    return classType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClassType(ClassType newClassType)
  {
    ClassType oldClassType = classType;
    classType = newClassType == null ? CLASS_TYPE_EDEFAULT : newClassType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ENUMERATION__CLASS_TYPE, oldClassType, classType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getIdent()
  {
    if (ident == null)
    {
      ident = new EDataTypeEList<String>(String.class, this, ModelicaBasePackage.ENUMERATION__IDENT);
    }
    return ident;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Comment> getEnumComments()
  {
    if (enumComments == null)
    {
      enumComments = new EObjectContainmentEList<Comment>(Comment.class, this, ModelicaBasePackage.ENUMERATION__ENUM_COMMENTS);
    }
    return enumComments;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ENUMERATION__COMMENT, oldComment, newComment);
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
        msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ENUMERATION__COMMENT, null, msgs);
      if (newComment != null)
        msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ENUMERATION__COMMENT, null, msgs);
      msgs = basicSetComment(newComment, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ENUMERATION__COMMENT, newComment, newComment));
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
      case ModelicaBasePackage.ENUMERATION__ENUM_COMMENTS:
        return ((InternalEList<?>)getEnumComments()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.ENUMERATION__COMMENT:
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
      case ModelicaBasePackage.ENUMERATION__CLASS_TYPE:
        return getClassType();
      case ModelicaBasePackage.ENUMERATION__IDENT:
        return getIdent();
      case ModelicaBasePackage.ENUMERATION__ENUM_COMMENTS:
        return getEnumComments();
      case ModelicaBasePackage.ENUMERATION__COMMENT:
        return getComment();
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
      case ModelicaBasePackage.ENUMERATION__CLASS_TYPE:
        setClassType((ClassType)newValue);
        return;
      case ModelicaBasePackage.ENUMERATION__IDENT:
        getIdent().clear();
        getIdent().addAll((Collection<? extends String>)newValue);
        return;
      case ModelicaBasePackage.ENUMERATION__ENUM_COMMENTS:
        getEnumComments().clear();
        getEnumComments().addAll((Collection<? extends Comment>)newValue);
        return;
      case ModelicaBasePackage.ENUMERATION__COMMENT:
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
      case ModelicaBasePackage.ENUMERATION__CLASS_TYPE:
        setClassType(CLASS_TYPE_EDEFAULT);
        return;
      case ModelicaBasePackage.ENUMERATION__IDENT:
        getIdent().clear();
        return;
      case ModelicaBasePackage.ENUMERATION__ENUM_COMMENTS:
        getEnumComments().clear();
        return;
      case ModelicaBasePackage.ENUMERATION__COMMENT:
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
      case ModelicaBasePackage.ENUMERATION__CLASS_TYPE:
        return classType != CLASS_TYPE_EDEFAULT;
      case ModelicaBasePackage.ENUMERATION__IDENT:
        return ident != null && !ident.isEmpty();
      case ModelicaBasePackage.ENUMERATION__ENUM_COMMENTS:
        return enumComments != null && !enumComments.isEmpty();
      case ModelicaBasePackage.ENUMERATION__COMMENT:
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
    result.append(" (classType: ");
    result.append(classType);
    result.append(", ident: ");
    result.append(ident);
    result.append(')');
    return result.toString();
  }

} //EnumerationImpl
