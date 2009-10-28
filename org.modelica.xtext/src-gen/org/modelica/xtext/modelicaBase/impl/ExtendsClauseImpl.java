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

import org.modelica.xtext.modelicaBase.Annotation;
import org.modelica.xtext.modelicaBase.ClassModification;
import org.modelica.xtext.modelicaBase.ExtendsClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extends Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ExtendsClauseImpl#getBaseClass <em>Base Class</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ExtendsClauseImpl#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ExtendsClauseImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendsClauseImpl extends ClassElementImpl implements ExtendsClause
{
  /**
   * The default value of the '{@link #getBaseClass() <em>Base Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBaseClass()
   * @generated
   * @ordered
   */
  protected static final String BASE_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBaseClass() <em>Base Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBaseClass()
   * @generated
   * @ordered
   */
  protected String baseClass = BASE_CLASS_EDEFAULT;

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
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected Annotation annotation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExtendsClauseImpl()
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
    return ModelicaBasePackage.Literals.EXTENDS_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBaseClass()
  {
    return baseClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBaseClass(String newBaseClass)
  {
    String oldBaseClass = baseClass;
    baseClass = newBaseClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTENDS_CLAUSE__BASE_CLASS, oldBaseClass, baseClass));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION, oldModification, newModification);
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
        msgs = ((InternalEObject)modification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION, null, msgs);
      if (newModification != null)
        msgs = ((InternalEObject)newModification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION, null, msgs);
      msgs = basicSetModification(newModification, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION, newModification, newModification));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Annotation getAnnotation()
  {
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation(Annotation newAnnotation, NotificationChain msgs)
  {
    Annotation oldAnnotation = annotation;
    annotation = newAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation(Annotation newAnnotation)
  {
    if (newAnnotation != annotation)
    {
      NotificationChain msgs = null;
      if (annotation != null)
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION, newAnnotation, newAnnotation));
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
      case ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION:
        return basicSetModification(null, msgs);
      case ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION:
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
      case ModelicaBasePackage.EXTENDS_CLAUSE__BASE_CLASS:
        return getBaseClass();
      case ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION:
        return getModification();
      case ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION:
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
      case ModelicaBasePackage.EXTENDS_CLAUSE__BASE_CLASS:
        setBaseClass((String)newValue);
        return;
      case ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION:
        setModification((ClassModification)newValue);
        return;
      case ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION:
        setAnnotation((Annotation)newValue);
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
      case ModelicaBasePackage.EXTENDS_CLAUSE__BASE_CLASS:
        setBaseClass(BASE_CLASS_EDEFAULT);
        return;
      case ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION:
        setModification((ClassModification)null);
        return;
      case ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION:
        setAnnotation((Annotation)null);
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
      case ModelicaBasePackage.EXTENDS_CLAUSE__BASE_CLASS:
        return BASE_CLASS_EDEFAULT == null ? baseClass != null : !BASE_CLASS_EDEFAULT.equals(baseClass);
      case ModelicaBasePackage.EXTENDS_CLAUSE__MODIFICATION:
        return modification != null;
      case ModelicaBasePackage.EXTENDS_CLAUSE__ANNOTATION:
        return annotation != null;
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
    result.append(" (baseClass: ");
    result.append(baseClass);
    result.append(')');
    return result.toString();
  }

} //ExtendsClauseImpl
