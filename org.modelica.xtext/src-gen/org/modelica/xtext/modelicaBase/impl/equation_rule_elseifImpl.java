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
import org.modelica.xtext.modelicaBase.equation_rule;
import org.modelica.xtext.modelicaBase.equation_rule_elseif;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>equation rule elseif</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.equation_rule_elseifImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.equation_rule_elseifImpl#getThenEquationClauses <em>Then Equation Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class equation_rule_elseifImpl extends MinimalEObjectImpl.Container implements equation_rule_elseif
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
   * The cached value of the '{@link #getThenEquationClauses() <em>Then Equation Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThenEquationClauses()
   * @generated
   * @ordered
   */
  protected EList<equation_rule> thenEquationClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected equation_rule_elseifImpl()
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
    return ModelicaBasePackage.Literals.EQUATION_RULE_ELSEIF;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.EQUATION_RULE_ELSEIF__CONDITION, oldCondition, condition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation_rule> getThenEquationClauses()
  {
    if (thenEquationClauses == null)
    {
      thenEquationClauses = new EObjectContainmentEList<equation_rule>(equation_rule.class, this, ModelicaBasePackage.EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES);
    }
    return thenEquationClauses;
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
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES:
        return ((InternalEList<?>)getThenEquationClauses()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__CONDITION:
        return getCondition();
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES:
        return getThenEquationClauses();
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
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__CONDITION:
        setCondition((String)newValue);
        return;
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES:
        getThenEquationClauses().clear();
        getThenEquationClauses().addAll((Collection<? extends equation_rule>)newValue);
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
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__CONDITION:
        setCondition(CONDITION_EDEFAULT);
        return;
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES:
        getThenEquationClauses().clear();
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
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__CONDITION:
        return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
      case ModelicaBasePackage.EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES:
        return thenEquationClauses != null && !thenEquationClauses.isEmpty();
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

} //equation_rule_elseifImpl
