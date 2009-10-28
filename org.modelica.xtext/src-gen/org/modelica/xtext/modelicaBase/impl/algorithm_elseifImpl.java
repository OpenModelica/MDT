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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.algorithm_elseif;
import org.modelica.xtext.modelicaBase.equation_rule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>algorithm elseif</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.algorithm_elseifImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.algorithm_elseifImpl#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class algorithm_elseifImpl extends MinimalEObjectImpl.Container implements algorithm_elseif
{
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
   * The cached value of the '{@link #getThenAlgorithmClauses() <em>Then Algorithm Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThenAlgorithmClauses()
   * @generated
   * @ordered
   */
  protected EList<equation_rule> thenAlgorithmClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected algorithm_elseifImpl()
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
    return ModelicaBasePackage.Literals.ALGORITHM_ELSEIF;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.ALGORITHM_ELSEIF__CONDITION, oldCondition, condition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation_rule> getThenAlgorithmClauses()
  {
    if (thenAlgorithmClauses == null)
    {
      thenAlgorithmClauses = new EObjectContainmentEList<equation_rule>(equation_rule.class, this, ModelicaBasePackage.ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES);
    }
    return thenAlgorithmClauses;
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
      case ModelicaBasePackage.ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES:
        return ((InternalEList<?>)getThenAlgorithmClauses()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.ALGORITHM_ELSEIF__CONDITION:
        return getCondition();
      case ModelicaBasePackage.ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES:
        return getThenAlgorithmClauses();
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
      case ModelicaBasePackage.ALGORITHM_ELSEIF__CONDITION:
        setCondition((String)newValue);
        return;
      case ModelicaBasePackage.ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES:
        getThenAlgorithmClauses().clear();
        getThenAlgorithmClauses().addAll((Collection<? extends equation_rule>)newValue);
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
      case ModelicaBasePackage.ALGORITHM_ELSEIF__CONDITION:
        setCondition(CONDITION_EDEFAULT);
        return;
      case ModelicaBasePackage.ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES:
        getThenAlgorithmClauses().clear();
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
      case ModelicaBasePackage.ALGORITHM_ELSEIF__CONDITION:
        return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
      case ModelicaBasePackage.ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES:
        return thenAlgorithmClauses != null && !thenAlgorithmClauses.isEmpty();
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
    result.append(" (condition: ");
    result.append(condition);
    result.append(')');
    return result.toString();
  }

} //algorithm_elseifImpl
