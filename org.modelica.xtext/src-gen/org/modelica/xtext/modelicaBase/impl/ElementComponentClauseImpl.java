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

import org.modelica.xtext.modelicaBase.Comment;
import org.modelica.xtext.modelicaBase.ElementComponentClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.Modification;
import org.modelica.xtext.modelicaBase.TypeSpecifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Component Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl#getTypePrefix <em>Type Prefix</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl#getTypeSpecifier <em>Type Specifier</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl#getSubscripts <em>Subscripts</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementComponentClauseImpl extends ElementRedeclarationImpl implements ElementComponentClause
{
  /**
   * The default value of the '{@link #getTypePrefix() <em>Type Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypePrefix()
   * @generated
   * @ordered
   */
  protected static final String TYPE_PREFIX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTypePrefix() <em>Type Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypePrefix()
   * @generated
   * @ordered
   */
  protected String typePrefix = TYPE_PREFIX_EDEFAULT;

  /**
   * The cached value of the '{@link #getTypeSpecifier() <em>Type Specifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeSpecifier()
   * @generated
   * @ordered
   */
  protected TypeSpecifier typeSpecifier;

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
  protected ElementComponentClauseImpl()
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
    return ModelicaBasePackage.Literals.ELEMENT_COMPONENT_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTypePrefix()
  {
    return typePrefix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypePrefix(String newTypePrefix)
  {
    String oldTypePrefix = typePrefix;
    typePrefix = newTypePrefix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX, oldTypePrefix, typePrefix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeSpecifier getTypeSpecifier()
  {
    return typeSpecifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTypeSpecifier(TypeSpecifier newTypeSpecifier, NotificationChain msgs)
  {
    TypeSpecifier oldTypeSpecifier = typeSpecifier;
    typeSpecifier = newTypeSpecifier;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER, oldTypeSpecifier, newTypeSpecifier);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeSpecifier(TypeSpecifier newTypeSpecifier)
  {
    if (newTypeSpecifier != typeSpecifier)
    {
      NotificationChain msgs = null;
      if (typeSpecifier != null)
        msgs = ((InternalEObject)typeSpecifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER, null, msgs);
      if (newTypeSpecifier != null)
        msgs = ((InternalEObject)newTypeSpecifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER, null, msgs);
      msgs = basicSetTypeSpecifier(newTypeSpecifier, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER, newTypeSpecifier, newTypeSpecifier));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS, oldSubscripts, subscripts));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION, oldModification, newModification);
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
        msgs = ((InternalEObject)modification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION, null, msgs);
      if (newModification != null)
        msgs = ((InternalEObject)newModification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION, null, msgs);
      msgs = basicSetModification(newModification, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION, newModification, newModification));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT, oldComment, newComment);
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
        msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT, null, msgs);
      if (newComment != null)
        msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT, null, msgs);
      msgs = basicSetComment(newComment, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT, newComment, newComment));
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
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER:
        return basicSetTypeSpecifier(null, msgs);
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION:
        return basicSetModification(null, msgs);
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX:
        return getTypePrefix();
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER:
        return getTypeSpecifier();
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS:
        return getSubscripts();
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION:
        return getModification();
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX:
        setTypePrefix((String)newValue);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER:
        setTypeSpecifier((TypeSpecifier)newValue);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS:
        setSubscripts((String)newValue);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION:
        setModification((Modification)newValue);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX:
        setTypePrefix(TYPE_PREFIX_EDEFAULT);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER:
        setTypeSpecifier((TypeSpecifier)null);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS:
        setSubscripts(SUBSCRIPTS_EDEFAULT);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION:
        setModification((Modification)null);
        return;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT:
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
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX:
        return TYPE_PREFIX_EDEFAULT == null ? typePrefix != null : !TYPE_PREFIX_EDEFAULT.equals(typePrefix);
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER:
        return typeSpecifier != null;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS:
        return SUBSCRIPTS_EDEFAULT == null ? subscripts != null : !SUBSCRIPTS_EDEFAULT.equals(subscripts);
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__MODIFICATION:
        return modification != null;
      case ModelicaBasePackage.ELEMENT_COMPONENT_CLAUSE__COMMENT:
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
    result.append(" (typePrefix: ");
    result.append(typePrefix);
    result.append(", subscripts: ");
    result.append(subscripts);
    result.append(')');
    return result.toString();
  }

} //ElementComponentClauseImpl
