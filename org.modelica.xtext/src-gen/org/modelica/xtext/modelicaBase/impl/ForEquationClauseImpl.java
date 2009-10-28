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

import org.modelica.xtext.modelicaBase.ForEquationClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.equation_rule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Equation Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ForEquationClauseImpl#getForIndices <em>For Indices</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ForEquationClauseImpl#getEquationClauses <em>Equation Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForEquationClauseImpl extends equation_ruleImpl implements ForEquationClause
{
  /**
   * The default value of the '{@link #getForIndices() <em>For Indices</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForIndices()
   * @generated
   * @ordered
   */
  protected static final String FOR_INDICES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getForIndices() <em>For Indices</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForIndices()
   * @generated
   * @ordered
   */
  protected String forIndices = FOR_INDICES_EDEFAULT;

  /**
   * The cached value of the '{@link #getEquationClauses() <em>Equation Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEquationClauses()
   * @generated
   * @ordered
   */
  protected EList<equation_rule> equationClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ForEquationClauseImpl()
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
    return ModelicaBasePackage.Literals.FOR_EQUATION_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getForIndices()
  {
    return forIndices;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForIndices(String newForIndices)
  {
    String oldForIndices = forIndices;
    forIndices = newForIndices;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.FOR_EQUATION_CLAUSE__FOR_INDICES, oldForIndices, forIndices));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation_rule> getEquationClauses()
  {
    if (equationClauses == null)
    {
      equationClauses = new EObjectContainmentEList<equation_rule>(equation_rule.class, this, ModelicaBasePackage.FOR_EQUATION_CLAUSE__EQUATION_CLAUSES);
    }
    return equationClauses;
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
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__EQUATION_CLAUSES:
        return ((InternalEList<?>)getEquationClauses()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__FOR_INDICES:
        return getForIndices();
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__EQUATION_CLAUSES:
        return getEquationClauses();
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
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__FOR_INDICES:
        setForIndices((String)newValue);
        return;
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__EQUATION_CLAUSES:
        getEquationClauses().clear();
        getEquationClauses().addAll((Collection<? extends equation_rule>)newValue);
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
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__FOR_INDICES:
        setForIndices(FOR_INDICES_EDEFAULT);
        return;
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__EQUATION_CLAUSES:
        getEquationClauses().clear();
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
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__FOR_INDICES:
        return FOR_INDICES_EDEFAULT == null ? forIndices != null : !FOR_INDICES_EDEFAULT.equals(forIndices);
      case ModelicaBasePackage.FOR_EQUATION_CLAUSE__EQUATION_CLAUSES:
        return equationClauses != null && !equationClauses.isEmpty();
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
    result.append(" (forIndices: ");
    result.append(forIndices);
    result.append(')');
    return result.toString();
  }

} //ForEquationClauseImpl
