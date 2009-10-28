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
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.algorithm;
import org.modelica.xtext.modelicaBase.algorithm_clause;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>algorithm clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.algorithm_clauseImpl#getAlgorithmRules <em>Algorithm Rules</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.algorithm_clauseImpl#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class algorithm_clauseImpl extends initial_algorithm_clauseImpl implements algorithm_clause
{
  /**
   * The cached value of the '{@link #getAlgorithmRules() <em>Algorithm Rules</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlgorithmRules()
   * @generated
   * @ordered
   */
  protected EList<algorithm> algorithmRules;

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
  protected algorithm_clauseImpl()
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
    return ModelicaBasePackage.Literals.ALGORITHM_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<algorithm> getAlgorithmRules()
  {
    if (algorithmRules == null)
    {
      algorithmRules = new EObjectContainmentEList<algorithm>(algorithm.class, this, ModelicaBasePackage.ALGORITHM_CLAUSE__ALGORITHM_RULES);
    }
    return algorithmRules;
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
      annotations = new EObjectContainmentEList<Annotation>(Annotation.class, this, ModelicaBasePackage.ALGORITHM_CLAUSE__ANNOTATIONS);
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
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ALGORITHM_RULES:
        return ((InternalEList<?>)getAlgorithmRules()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ANNOTATIONS:
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
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ALGORITHM_RULES:
        return getAlgorithmRules();
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ANNOTATIONS:
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
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ALGORITHM_RULES:
        getAlgorithmRules().clear();
        getAlgorithmRules().addAll((Collection<? extends algorithm>)newValue);
        return;
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ANNOTATIONS:
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
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ALGORITHM_RULES:
        getAlgorithmRules().clear();
        return;
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ANNOTATIONS:
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
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ALGORITHM_RULES:
        return algorithmRules != null && !algorithmRules.isEmpty();
      case ModelicaBasePackage.ALGORITHM_CLAUSE__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //algorithm_clauseImpl
