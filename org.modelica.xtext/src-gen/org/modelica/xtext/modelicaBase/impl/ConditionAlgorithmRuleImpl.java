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

import org.modelica.xtext.modelicaBase.ConditionAlgorithmRule;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.algorithm;
import org.modelica.xtext.modelicaBase.algorithm_elseif;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition Algorithm Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl#getElseifClauses <em>Elseif Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl#getElseAlgotithmClauses <em>Else Algotithm Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionAlgorithmRuleImpl extends algorithmImpl implements ConditionAlgorithmRule
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
  protected EList<algorithm> thenAlgorithmClauses;

  /**
   * The cached value of the '{@link #getElseifClauses() <em>Elseif Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseifClauses()
   * @generated
   * @ordered
   */
  protected EList<algorithm_elseif> elseifClauses;

  /**
   * The cached value of the '{@link #getElseAlgotithmClauses() <em>Else Algotithm Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseAlgotithmClauses()
   * @generated
   * @ordered
   */
  protected EList<algorithm> elseAlgotithmClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConditionAlgorithmRuleImpl()
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
    return ModelicaBasePackage.Literals.CONDITION_ALGORITHM_RULE;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CONDITION_ALGORITHM_RULE__CONDITION, oldCondition, condition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<algorithm> getThenAlgorithmClauses()
  {
    if (thenAlgorithmClauses == null)
    {
      thenAlgorithmClauses = new EObjectContainmentEList<algorithm>(algorithm.class, this, ModelicaBasePackage.CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES);
    }
    return thenAlgorithmClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<algorithm_elseif> getElseifClauses()
  {
    if (elseifClauses == null)
    {
      elseifClauses = new EObjectContainmentEList<algorithm_elseif>(algorithm_elseif.class, this, ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES);
    }
    return elseifClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<algorithm> getElseAlgotithmClauses()
  {
    if (elseAlgotithmClauses == null)
    {
      elseAlgotithmClauses = new EObjectContainmentEList<algorithm>(algorithm.class, this, ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES);
    }
    return elseAlgotithmClauses;
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
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES:
        return ((InternalEList<?>)getThenAlgorithmClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES:
        return ((InternalEList<?>)getElseifClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES:
        return ((InternalEList<?>)getElseAlgotithmClauses()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__CONDITION:
        return getCondition();
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES:
        return getThenAlgorithmClauses();
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES:
        return getElseifClauses();
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES:
        return getElseAlgotithmClauses();
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
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__CONDITION:
        setCondition((String)newValue);
        return;
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES:
        getThenAlgorithmClauses().clear();
        getThenAlgorithmClauses().addAll((Collection<? extends algorithm>)newValue);
        return;
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES:
        getElseifClauses().clear();
        getElseifClauses().addAll((Collection<? extends algorithm_elseif>)newValue);
        return;
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES:
        getElseAlgotithmClauses().clear();
        getElseAlgotithmClauses().addAll((Collection<? extends algorithm>)newValue);
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
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__CONDITION:
        setCondition(CONDITION_EDEFAULT);
        return;
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES:
        getThenAlgorithmClauses().clear();
        return;
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES:
        getElseifClauses().clear();
        return;
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES:
        getElseAlgotithmClauses().clear();
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
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__CONDITION:
        return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES:
        return thenAlgorithmClauses != null && !thenAlgorithmClauses.isEmpty();
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES:
        return elseifClauses != null && !elseifClauses.isEmpty();
      case ModelicaBasePackage.CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES:
        return elseAlgotithmClauses != null && !elseAlgotithmClauses.isEmpty();
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

} //ConditionAlgorithmRuleImpl
