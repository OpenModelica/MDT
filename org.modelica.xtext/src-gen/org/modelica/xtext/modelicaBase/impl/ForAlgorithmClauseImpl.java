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

import org.modelica.xtext.modelicaBase.ForAlgorithmClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.algorithm;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Algorithm Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ForAlgorithmClauseImpl#getForIndices <em>For Indices</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ForAlgorithmClauseImpl#getAlgorithmClauses <em>Algorithm Clauses</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForAlgorithmClauseImpl extends algorithmImpl implements ForAlgorithmClause
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
   * The cached value of the '{@link #getAlgorithmClauses() <em>Algorithm Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlgorithmClauses()
   * @generated
   * @ordered
   */
  protected EList<algorithm> algorithmClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ForAlgorithmClauseImpl()
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
    return ModelicaBasePackage.Literals.FOR_ALGORITHM_CLAUSE;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__FOR_INDICES, oldForIndices, forIndices));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<algorithm> getAlgorithmClauses()
  {
    if (algorithmClauses == null)
    {
      algorithmClauses = new EObjectContainmentEList<algorithm>(algorithm.class, this, ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES);
    }
    return algorithmClauses;
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
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES:
        return ((InternalEList<?>)getAlgorithmClauses()).basicRemove(otherEnd, msgs);
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
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__FOR_INDICES:
        return getForIndices();
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES:
        return getAlgorithmClauses();
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
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__FOR_INDICES:
        setForIndices((String)newValue);
        return;
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES:
        getAlgorithmClauses().clear();
        getAlgorithmClauses().addAll((Collection<? extends algorithm>)newValue);
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
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__FOR_INDICES:
        setForIndices(FOR_INDICES_EDEFAULT);
        return;
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES:
        getAlgorithmClauses().clear();
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
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__FOR_INDICES:
        return FOR_INDICES_EDEFAULT == null ? forIndices != null : !FOR_INDICES_EDEFAULT.equals(forIndices);
      case ModelicaBasePackage.FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES:
        return algorithmClauses != null && !algorithmClauses.isEmpty();
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

} //ForAlgorithmClauseImpl
