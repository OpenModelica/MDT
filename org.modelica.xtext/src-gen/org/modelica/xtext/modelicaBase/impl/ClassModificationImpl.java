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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelica.xtext.modelicaBase.Annotation;
import org.modelica.xtext.modelicaBase.ClassModification;
import org.modelica.xtext.modelicaBase.ClassType;
import org.modelica.xtext.modelicaBase.Comment;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.Modification;
import org.modelica.xtext.modelicaBase.TypeSpecifier;
import org.modelica.xtext.modelicaBase.argument;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Modification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl#getTypeSpecifier <em>Type Specifier</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassModificationImpl extends EntityDefinitionImpl implements ClassModification
{
  /**
   * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected static final String EXPRESSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected String expression = EXPRESSION_EDEFAULT;

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
   * The cached value of the '{@link #getTypeSpecifier() <em>Type Specifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeSpecifier()
   * @generated
   * @ordered
   */
  protected TypeSpecifier typeSpecifier;

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
   * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArguments()
   * @generated
   * @ordered
   */
  protected EList<argument> arguments;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClassModificationImpl()
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
    return ModelicaBasePackage.Literals.CLASS_MODIFICATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getExpression()
  {
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpression(String newExpression)
  {
    String oldExpression = expression;
    expression = newExpression;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__EXPRESSION, oldExpression, expression));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__CLASS_TYPE, oldClassType, classType));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER, oldTypeSpecifier, newTypeSpecifier);
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
        msgs = ((InternalEObject)typeSpecifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER, null, msgs);
      if (newTypeSpecifier != null)
        msgs = ((InternalEObject)newTypeSpecifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER, null, msgs);
      msgs = basicSetTypeSpecifier(newTypeSpecifier, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER, newTypeSpecifier, newTypeSpecifier));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION, oldModification, newModification);
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
        msgs = ((InternalEObject)modification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION, null, msgs);
      if (newModification != null)
        msgs = ((InternalEObject)newModification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION, null, msgs);
      msgs = basicSetModification(newModification, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION, newModification, newModification));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__COMMENT, oldComment, newComment);
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
        msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_MODIFICATION__COMMENT, null, msgs);
      if (newComment != null)
        msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_MODIFICATION__COMMENT, null, msgs);
      msgs = basicSetComment(newComment, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_MODIFICATION__COMMENT, newComment, newComment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<argument> getArguments()
  {
    if (arguments == null)
    {
      arguments = new EObjectContainmentEList<argument>(argument.class, this, ModelicaBasePackage.CLASS_MODIFICATION__ARGUMENTS);
    }
    return arguments;
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
      case ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER:
        return basicSetTypeSpecifier(null, msgs);
      case ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION:
        return basicSetModification(null, msgs);
      case ModelicaBasePackage.CLASS_MODIFICATION__COMMENT:
        return basicSetComment(null, msgs);
      case ModelicaBasePackage.CLASS_MODIFICATION__ARGUMENTS:
        return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.CLASS_MODIFICATION__EXPRESSION:
        return getExpression();
      case ModelicaBasePackage.CLASS_MODIFICATION__CLASS_TYPE:
        return getClassType();
      case ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER:
        return getTypeSpecifier();
      case ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION:
        return getModification();
      case ModelicaBasePackage.CLASS_MODIFICATION__COMMENT:
        return getComment();
      case ModelicaBasePackage.CLASS_MODIFICATION__ARGUMENTS:
        return getArguments();
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
      case ModelicaBasePackage.CLASS_MODIFICATION__EXPRESSION:
        setExpression((String)newValue);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__CLASS_TYPE:
        setClassType((ClassType)newValue);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER:
        setTypeSpecifier((TypeSpecifier)newValue);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION:
        setModification((ClassModification)newValue);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__COMMENT:
        setComment((Comment)newValue);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__ARGUMENTS:
        getArguments().clear();
        getArguments().addAll((Collection<? extends argument>)newValue);
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
      case ModelicaBasePackage.CLASS_MODIFICATION__EXPRESSION:
        setExpression(EXPRESSION_EDEFAULT);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__CLASS_TYPE:
        setClassType(CLASS_TYPE_EDEFAULT);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER:
        setTypeSpecifier((TypeSpecifier)null);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION:
        setModification((ClassModification)null);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__COMMENT:
        setComment((Comment)null);
        return;
      case ModelicaBasePackage.CLASS_MODIFICATION__ARGUMENTS:
        getArguments().clear();
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
      case ModelicaBasePackage.CLASS_MODIFICATION__EXPRESSION:
        return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
      case ModelicaBasePackage.CLASS_MODIFICATION__CLASS_TYPE:
        return classType != CLASS_TYPE_EDEFAULT;
      case ModelicaBasePackage.CLASS_MODIFICATION__TYPE_SPECIFIER:
        return typeSpecifier != null;
      case ModelicaBasePackage.CLASS_MODIFICATION__MODIFICATION:
        return modification != null;
      case ModelicaBasePackage.CLASS_MODIFICATION__COMMENT:
        return comment != null;
      case ModelicaBasePackage.CLASS_MODIFICATION__ARGUMENTS:
        return arguments != null && !arguments.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == Modification.class)
    {
      switch (derivedFeatureID)
      {
        case ModelicaBasePackage.CLASS_MODIFICATION__EXPRESSION: return ModelicaBasePackage.MODIFICATION__EXPRESSION;
        default: return -1;
      }
    }
    if (baseClass == Annotation.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == Modification.class)
    {
      switch (baseFeatureID)
      {
        case ModelicaBasePackage.MODIFICATION__EXPRESSION: return ModelicaBasePackage.CLASS_MODIFICATION__EXPRESSION;
        default: return -1;
      }
    }
    if (baseClass == Annotation.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (expression: ");
    result.append(expression);
    result.append(", classType: ");
    result.append(classType);
    result.append(')');
    return result.toString();
  }

} //ClassModificationImpl
