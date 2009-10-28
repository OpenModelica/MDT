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

import org.modelica.xtext.modelicaBase.Annotation;
import org.modelica.xtext.modelicaBase.ExternalClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ExternalClauseImpl#getLanguageSpecification <em>Language Specification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ExternalClauseImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ExternalClauseImpl#getExtAnnotation <em>Ext Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalClauseImpl extends MinimalEObjectImpl.Container implements ExternalClause
{
  /**
   * The default value of the '{@link #getLanguageSpecification() <em>Language Specification</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLanguageSpecification()
   * @generated
   * @ordered
   */
  protected static final String LANGUAGE_SPECIFICATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLanguageSpecification() <em>Language Specification</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLanguageSpecification()
   * @generated
   * @ordered
   */
  protected String languageSpecification = LANGUAGE_SPECIFICATION_EDEFAULT;

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
   * The cached value of the '{@link #getExtAnnotation() <em>Ext Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtAnnotation()
   * @generated
   * @ordered
   */
  protected Annotation extAnnotation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExternalClauseImpl()
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
    return ModelicaBasePackage.Literals.EXTERNAL_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLanguageSpecification()
  {
    return languageSpecification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLanguageSpecification(String newLanguageSpecification)
  {
    String oldLanguageSpecification = languageSpecification;
    languageSpecification = newLanguageSpecification;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION, oldLanguageSpecification, languageSpecification));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION, oldAnnotation, newAnnotation);
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION, newAnnotation, newAnnotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Annotation getExtAnnotation()
  {
    return extAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExtAnnotation(Annotation newExtAnnotation, NotificationChain msgs)
  {
    Annotation oldExtAnnotation = extAnnotation;
    extAnnotation = newExtAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION, oldExtAnnotation, newExtAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExtAnnotation(Annotation newExtAnnotation)
  {
    if (newExtAnnotation != extAnnotation)
    {
      NotificationChain msgs = null;
      if (extAnnotation != null)
        msgs = ((InternalEObject)extAnnotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION, null, msgs);
      if (newExtAnnotation != null)
        msgs = ((InternalEObject)newExtAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION, null, msgs);
      msgs = basicSetExtAnnotation(newExtAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION, newExtAnnotation, newExtAnnotation));
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
      case ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION:
        return basicSetAnnotation(null, msgs);
      case ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION:
        return basicSetExtAnnotation(null, msgs);
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
      case ModelicaBasePackage.EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION:
        return getLanguageSpecification();
      case ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION:
        return getAnnotation();
      case ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION:
        return getExtAnnotation();
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
      case ModelicaBasePackage.EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION:
        setLanguageSpecification((String)newValue);
        return;
      case ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION:
        setAnnotation((Annotation)newValue);
        return;
      case ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION:
        setExtAnnotation((Annotation)newValue);
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
      case ModelicaBasePackage.EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION:
        setLanguageSpecification(LANGUAGE_SPECIFICATION_EDEFAULT);
        return;
      case ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION:
        setAnnotation((Annotation)null);
        return;
      case ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION:
        setExtAnnotation((Annotation)null);
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
      case ModelicaBasePackage.EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION:
        return LANGUAGE_SPECIFICATION_EDEFAULT == null ? languageSpecification != null : !LANGUAGE_SPECIFICATION_EDEFAULT.equals(languageSpecification);
      case ModelicaBasePackage.EXTERNAL_CLAUSE__ANNOTATION:
        return annotation != null;
      case ModelicaBasePackage.EXTERNAL_CLAUSE__EXT_ANNOTATION:
        return extAnnotation != null;
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
    result.append(" (languageSpecification: ");
    result.append(languageSpecification);
    result.append(')');
    return result.toString();
  }

} //ExternalClauseImpl
