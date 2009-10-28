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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelica.xtext.modelicaBase.ConditionalEquationRule;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.equation_rule;
import org.modelica.xtext.modelicaBase.equation_rule_elseif;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Equation Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConditionalEquationRuleImpl#getThenEquationClauses <em>Then Equation Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConditionalEquationRuleImpl#getElseifClauses <em>Elseif Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ConditionalEquationRuleImpl#getElseEquationClauses <em>Else Equation Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalEquationRuleImpl extends equation_ruleImpl implements ConditionalEquationRule
{
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
   * The cached value of the '{@link #getElseifClauses() <em>Elseif Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseifClauses()
   * @generated
   * @ordered
   */
  protected EList<equation_rule_elseif> elseifClauses;

  /**
   * The cached value of the '{@link #getElseEquationClauses() <em>Else Equation Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseEquationClauses()
   * @generated
   * @ordered
   */
  protected EList<equation_rule> elseEquationClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConditionalEquationRuleImpl()
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
    return ModelicaBasePackage.Literals.CONDITIONAL_EQUATION_RULE;
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
      thenEquationClauses = new EObjectContainmentEList<equation_rule>(equation_rule.class, this, ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES);
    }
    return thenEquationClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation_rule_elseif> getElseifClauses()
  {
    if (elseifClauses == null)
    {
      elseifClauses = new EObjectContainmentEList<equation_rule_elseif>(equation_rule_elseif.class, this, ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES);
    }
    return elseifClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation_rule> getElseEquationClauses()
  {
    if (elseEquationClauses == null)
    {
      elseEquationClauses = new EObjectContainmentEList<equation_rule>(equation_rule.class, this, ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES);
    }
    return elseEquationClauses;
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
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES:
        return ((InternalEList<?>)getThenEquationClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES:
        return ((InternalEList<?>)getElseifClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES:
        return ((InternalEList<?>)getElseEquationClauses()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES:
        return getThenEquationClauses();
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES:
        return getElseifClauses();
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES:
        return getElseEquationClauses();
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
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES:
        getThenEquationClauses().clear();
        getThenEquationClauses().addAll((Collection<? extends equation_rule>)newValue);
        return;
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES:
        getElseifClauses().clear();
        getElseifClauses().addAll((Collection<? extends equation_rule_elseif>)newValue);
        return;
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES:
        getElseEquationClauses().clear();
        getElseEquationClauses().addAll((Collection<? extends equation_rule>)newValue);
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
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES:
        getThenEquationClauses().clear();
        return;
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES:
        getElseifClauses().clear();
        return;
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES:
        getElseEquationClauses().clear();
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
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES:
        return thenEquationClauses != null && !thenEquationClauses.isEmpty();
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES:
        return elseifClauses != null && !elseifClauses.isEmpty();
      case ModelicaBasePackage.CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES:
        return elseEquationClauses != null && !elseEquationClauses.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ConditionalEquationRuleImpl
