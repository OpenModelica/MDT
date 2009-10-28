/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelica.xtext.modelicaBase.ClassDefinition;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.StorageUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Storage Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.StorageUnitImpl#getClassDefinition <em>Class Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StorageUnitImpl extends MinimalEObjectImpl.Container implements StorageUnit
{
  /**
   * The cached value of the '{@link #getClassDefinition() <em>Class Definition</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassDefinition()
   * @generated
   * @ordered
   */
  protected EList<ClassDefinition> classDefinition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StorageUnitImpl()
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
    return ModelicaBasePackage.Literals.STORAGE_UNIT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ClassDefinition> getClassDefinition()
  {
    if (classDefinition == null)
    {
      classDefinition = new EObjectContainmentEList<ClassDefinition>(ClassDefinition.class, this, ModelicaBasePackage.STORAGE_UNIT__CLASS_DEFINITION);
    }
    return classDefinition;
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
      case ModelicaBasePackage.STORAGE_UNIT__CLASS_DEFINITION:
        return ((InternalEList<?>)getClassDefinition()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.STORAGE_UNIT__CLASS_DEFINITION:
        return getClassDefinition();
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
      case ModelicaBasePackage.STORAGE_UNIT__CLASS_DEFINITION:
        getClassDefinition().clear();
        getClassDefinition().addAll((Collection<? extends ClassDefinition>)newValue);
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
      case ModelicaBasePackage.STORAGE_UNIT__CLASS_DEFINITION:
        getClassDefinition().clear();
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
      case ModelicaBasePackage.STORAGE_UNIT__CLASS_DEFINITION:
        return classDefinition != null && !classDefinition.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //StorageUnitImpl
