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

import org.modelica.xtext.modelicaBase.ComponentClause;
import org.modelica.xtext.modelicaBase.ComponentDeclaration;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.TypeSpecifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ComponentClauseImpl#getTypePrefix <em>Type Prefix</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ComponentClauseImpl#getTypeSpecifier <em>Type Specifier</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ComponentClauseImpl#getDeclarations <em>Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentClauseImpl extends ClassElementImpl implements ComponentClause
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
   * The cached value of the '{@link #getDeclarations() <em>Declarations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeclarations()
   * @generated
   * @ordered
   */
  protected EList<ComponentDeclaration> declarations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComponentClauseImpl()
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
    return ModelicaBasePackage.Literals.COMPONENT_CLAUSE;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_PREFIX, oldTypePrefix, typePrefix));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER, oldTypeSpecifier, newTypeSpecifier);
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
        msgs = ((InternalEObject)typeSpecifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER, null, msgs);
      if (newTypeSpecifier != null)
        msgs = ((InternalEObject)newTypeSpecifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER, null, msgs);
      msgs = basicSetTypeSpecifier(newTypeSpecifier, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER, newTypeSpecifier, newTypeSpecifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ComponentDeclaration> getDeclarations()
  {
    if (declarations == null)
    {
      declarations = new EObjectContainmentEList<ComponentDeclaration>(ComponentDeclaration.class, this, ModelicaBasePackage.COMPONENT_CLAUSE__DECLARATIONS);
    }
    return declarations;
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
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER:
        return basicSetTypeSpecifier(null, msgs);
      case ModelicaBasePackage.COMPONENT_CLAUSE__DECLARATIONS:
        return ((InternalEList<?>)getDeclarations()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_PREFIX:
        return getTypePrefix();
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER:
        return getTypeSpecifier();
      case ModelicaBasePackage.COMPONENT_CLAUSE__DECLARATIONS:
        return getDeclarations();
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
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_PREFIX:
        setTypePrefix((String)newValue);
        return;
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER:
        setTypeSpecifier((TypeSpecifier)newValue);
        return;
      case ModelicaBasePackage.COMPONENT_CLAUSE__DECLARATIONS:
        getDeclarations().clear();
        getDeclarations().addAll((Collection<? extends ComponentDeclaration>)newValue);
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
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_PREFIX:
        setTypePrefix(TYPE_PREFIX_EDEFAULT);
        return;
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER:
        setTypeSpecifier((TypeSpecifier)null);
        return;
      case ModelicaBasePackage.COMPONENT_CLAUSE__DECLARATIONS:
        getDeclarations().clear();
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
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_PREFIX:
        return TYPE_PREFIX_EDEFAULT == null ? typePrefix != null : !TYPE_PREFIX_EDEFAULT.equals(typePrefix);
      case ModelicaBasePackage.COMPONENT_CLAUSE__TYPE_SPECIFIER:
        return typeSpecifier != null;
      case ModelicaBasePackage.COMPONENT_CLAUSE__DECLARATIONS:
        return declarations != null && !declarations.isEmpty();
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
    result.append(')');
    return result.toString();
  }

} //ComponentClauseImpl
