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

import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.else_when_a;
import org.modelica.xtext.modelicaBase.equation_rule;
import org.modelica.xtext.modelicaBase.when_clause_a;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>when clause a</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.when_clause_aImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.when_clause_aImpl#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.when_clause_aImpl#getElseWhenClauses <em>Else When Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class when_clause_aImpl extends algorithmImpl implements when_clause_a
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
   * The cached value of the '{@link #getElseWhenClauses() <em>Else When Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseWhenClauses()
   * @generated
   * @ordered
   */
  protected EList<else_when_a> elseWhenClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected when_clause_aImpl()
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
    return ModelicaBasePackage.Literals.WHEN_CLAUSE_A;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.WHEN_CLAUSE_A__CONDITION, oldCondition, condition));
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
      thenAlgorithmClauses = new EObjectContainmentEList<equation_rule>(equation_rule.class, this, ModelicaBasePackage.WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES);
    }
    return thenAlgorithmClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<else_when_a> getElseWhenClauses()
  {
    if (elseWhenClauses == null)
    {
      elseWhenClauses = new EObjectContainmentEList<else_when_a>(else_when_a.class, this, ModelicaBasePackage.WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES);
    }
    return elseWhenClauses;
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
      case ModelicaBasePackage.WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES:
        return ((InternalEList<?>)getThenAlgorithmClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES:
        return ((InternalEList<?>)getElseWhenClauses()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.WHEN_CLAUSE_A__CONDITION:
        return getCondition();
      case ModelicaBasePackage.WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES:
        return getThenAlgorithmClauses();
      case ModelicaBasePackage.WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES:
        return getElseWhenClauses();
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
      case ModelicaBasePackage.WHEN_CLAUSE_A__CONDITION:
        setCondition((String)newValue);
        return;
      case ModelicaBasePackage.WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES:
        getThenAlgorithmClauses().clear();
        getThenAlgorithmClauses().addAll((Collection<? extends equation_rule>)newValue);
        return;
      case ModelicaBasePackage.WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES:
        getElseWhenClauses().clear();
        getElseWhenClauses().addAll((Collection<? extends else_when_a>)newValue);
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
      case ModelicaBasePackage.WHEN_CLAUSE_A__CONDITION:
        setCondition(CONDITION_EDEFAULT);
        return;
      case ModelicaBasePackage.WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES:
        getThenAlgorithmClauses().clear();
        return;
      case ModelicaBasePackage.WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES:
        getElseWhenClauses().clear();
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
      case ModelicaBasePackage.WHEN_CLAUSE_A__CONDITION:
        return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
      case ModelicaBasePackage.WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES:
        return thenAlgorithmClauses != null && !thenAlgorithmClauses.isEmpty();
      case ModelicaBasePackage.WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES:
        return elseWhenClauses != null && !elseWhenClauses.isEmpty();
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

} //when_clause_aImpl
