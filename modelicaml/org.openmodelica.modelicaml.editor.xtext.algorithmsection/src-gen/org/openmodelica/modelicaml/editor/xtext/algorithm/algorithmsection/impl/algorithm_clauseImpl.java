/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.algorithm_clause;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>algorithm clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.algorithm_clauseImpl#getAlgorithm <em>Algorithm</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class algorithm_clauseImpl extends MinimalEObjectImpl.Container implements algorithm_clause
{
  /**
   * The cached value of the '{@link #getAlgorithm() <em>Algorithm</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlgorithm()
   * @generated
   * @ordered
   */
  protected EList<statement> algorithm;

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
    return AlgorithmsectionPackage.Literals.ALGORITHM_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<statement> getAlgorithm()
  {
    if (algorithm == null)
    {
      algorithm = new EObjectContainmentEList<statement>(statement.class, this, AlgorithmsectionPackage.ALGORITHM_CLAUSE__ALGORITHM);
    }
    return algorithm;
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
      case AlgorithmsectionPackage.ALGORITHM_CLAUSE__ALGORITHM:
        return ((InternalEList<?>)getAlgorithm()).basicRemove(otherEnd, msgs);
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
      case AlgorithmsectionPackage.ALGORITHM_CLAUSE__ALGORITHM:
        return getAlgorithm();
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
      case AlgorithmsectionPackage.ALGORITHM_CLAUSE__ALGORITHM:
        getAlgorithm().clear();
        getAlgorithm().addAll((Collection<? extends statement>)newValue);
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
      case AlgorithmsectionPackage.ALGORITHM_CLAUSE__ALGORITHM:
        getAlgorithm().clear();
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
      case AlgorithmsectionPackage.ALGORITHM_CLAUSE__ALGORITHM:
        return algorithm != null && !algorithm.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //algorithm_clauseImpl
