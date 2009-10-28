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

import org.modelica.xtext.modelicaBase.Annotation;
import org.modelica.xtext.modelicaBase.EquationClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.equation_rule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Equation Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.EquationClauseImpl#getEquationRules <em>Equation Rules</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.EquationClauseImpl#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EquationClauseImpl extends initial_equation_rule_clauseImpl implements EquationClause
{
  /**
   * The cached value of the '{@link #getEquationRules() <em>Equation Rules</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEquationRules()
   * @generated
   * @ordered
   */
  protected EList<equation_rule> equationRules;

  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList<Annotation> annotations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EquationClauseImpl()
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
    return ModelicaBasePackage.Literals.EQUATION_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation_rule> getEquationRules()
  {
    if (equationRules == null)
    {
      equationRules = new EObjectContainmentEList<equation_rule>(equation_rule.class, this, ModelicaBasePackage.EQUATION_CLAUSE__EQUATION_RULES);
    }
    return equationRules;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Annotation> getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectContainmentEList<Annotation>(Annotation.class, this, ModelicaBasePackage.EQUATION_CLAUSE__ANNOTATIONS);
    }
    return annotations;
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
      case ModelicaBasePackage.EQUATION_CLAUSE__EQUATION_RULES:
        return ((InternalEList<?>)getEquationRules()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.EQUATION_CLAUSE__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.EQUATION_CLAUSE__EQUATION_RULES:
        return getEquationRules();
      case ModelicaBasePackage.EQUATION_CLAUSE__ANNOTATIONS:
        return getAnnotations();
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
      case ModelicaBasePackage.EQUATION_CLAUSE__EQUATION_RULES:
        getEquationRules().clear();
        getEquationRules().addAll((Collection<? extends equation_rule>)newValue);
        return;
      case ModelicaBasePackage.EQUATION_CLAUSE__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends Annotation>)newValue);
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
      case ModelicaBasePackage.EQUATION_CLAUSE__EQUATION_RULES:
        getEquationRules().clear();
        return;
      case ModelicaBasePackage.EQUATION_CLAUSE__ANNOTATIONS:
        getAnnotations().clear();
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
      case ModelicaBasePackage.EQUATION_CLAUSE__EQUATION_RULES:
        return equationRules != null && !equationRules.isEmpty();
      case ModelicaBasePackage.EQUATION_CLAUSE__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //EquationClauseImpl
