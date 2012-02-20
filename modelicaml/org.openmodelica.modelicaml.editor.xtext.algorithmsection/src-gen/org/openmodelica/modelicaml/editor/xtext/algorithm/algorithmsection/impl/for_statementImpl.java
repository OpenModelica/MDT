/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl;

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
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.for_statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_indices;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>for statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.for_statementImpl#getFor_loop <em>For loop</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.for_statementImpl#getFor_algo <em>For algo</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class for_statementImpl extends MinimalEObjectImpl.Container implements for_statement
{
  /**
   * The cached value of the '{@link #getFor_loop() <em>For loop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFor_loop()
   * @generated
   * @ordered
   */
  protected for_indices for_loop;

  /**
   * The cached value of the '{@link #getFor_algo() <em>For algo</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFor_algo()
   * @generated
   * @ordered
   */
  protected EList<statement> for_algo;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected for_statementImpl()
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
    return AlgorithmsectionPackage.Literals.FOR_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_indices getFor_loop()
  {
    return for_loop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFor_loop(for_indices newFor_loop, NotificationChain msgs)
  {
    for_indices oldFor_loop = for_loop;
    for_loop = newFor_loop;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP, oldFor_loop, newFor_loop);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFor_loop(for_indices newFor_loop)
  {
    if (newFor_loop != for_loop)
    {
      NotificationChain msgs = null;
      if (for_loop != null)
        msgs = ((InternalEObject)for_loop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP, null, msgs);
      if (newFor_loop != null)
        msgs = ((InternalEObject)newFor_loop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP, null, msgs);
      msgs = basicSetFor_loop(newFor_loop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP, newFor_loop, newFor_loop));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<statement> getFor_algo()
  {
    if (for_algo == null)
    {
      for_algo = new EObjectContainmentEList<statement>(statement.class, this, AlgorithmsectionPackage.FOR_STATEMENT__FOR_ALGO);
    }
    return for_algo;
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
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP:
        return basicSetFor_loop(null, msgs);
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_ALGO:
        return ((InternalEList<?>)getFor_algo()).basicRemove(otherEnd, msgs);
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
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP:
        return getFor_loop();
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_ALGO:
        return getFor_algo();
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
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP:
        setFor_loop((for_indices)newValue);
        return;
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_ALGO:
        getFor_algo().clear();
        getFor_algo().addAll((Collection<? extends statement>)newValue);
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
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP:
        setFor_loop((for_indices)null);
        return;
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_ALGO:
        getFor_algo().clear();
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
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_LOOP:
        return for_loop != null;
      case AlgorithmsectionPackage.FOR_STATEMENT__FOR_ALGO:
        return for_algo != null && !for_algo.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //for_statementImpl
