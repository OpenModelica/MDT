/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_indices;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>for statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_statementImpl#getFor_indices <em>For indices</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.for_statementImpl#getFor_statement <em>For statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class for_statementImpl extends MinimalEObjectImpl.Container implements for_statement
{
  /**
   * The cached value of the '{@link #getFor_indices() <em>For indices</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFor_indices()
   * @generated
   * @ordered
   */
  protected for_indices for_indices;

  /**
   * The cached value of the '{@link #getFor_statement() <em>For statement</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFor_statement()
   * @generated
   * @ordered
   */
  protected EList<for_statement> for_statement;

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
    return ModificationPackage.Literals.FOR_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_indices getFor_indices()
  {
    return for_indices;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFor_indices(for_indices newFor_indices, NotificationChain msgs)
  {
    for_indices oldFor_indices = for_indices;
    for_indices = newFor_indices;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.FOR_STATEMENT__FOR_INDICES, oldFor_indices, newFor_indices);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFor_indices(for_indices newFor_indices)
  {
    if (newFor_indices != for_indices)
    {
      NotificationChain msgs = null;
      if (for_indices != null)
        msgs = ((InternalEObject)for_indices).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.FOR_STATEMENT__FOR_INDICES, null, msgs);
      if (newFor_indices != null)
        msgs = ((InternalEObject)newFor_indices).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.FOR_STATEMENT__FOR_INDICES, null, msgs);
      msgs = basicSetFor_indices(newFor_indices, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.FOR_STATEMENT__FOR_INDICES, newFor_indices, newFor_indices));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<for_statement> getFor_statement()
  {
    if (for_statement == null)
    {
      for_statement = new EObjectContainmentEList<for_statement>(for_statement.class, this, ModificationPackage.FOR_STATEMENT__FOR_STATEMENT);
    }
    return for_statement;
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
      case ModificationPackage.FOR_STATEMENT__FOR_INDICES:
        return basicSetFor_indices(null, msgs);
      case ModificationPackage.FOR_STATEMENT__FOR_STATEMENT:
        return ((InternalEList<?>)getFor_statement()).basicRemove(otherEnd, msgs);
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
      case ModificationPackage.FOR_STATEMENT__FOR_INDICES:
        return getFor_indices();
      case ModificationPackage.FOR_STATEMENT__FOR_STATEMENT:
        return getFor_statement();
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
      case ModificationPackage.FOR_STATEMENT__FOR_INDICES:
        setFor_indices((for_indices)newValue);
        return;
      case ModificationPackage.FOR_STATEMENT__FOR_STATEMENT:
        getFor_statement().clear();
        getFor_statement().addAll((Collection<? extends for_statement>)newValue);
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
      case ModificationPackage.FOR_STATEMENT__FOR_INDICES:
        setFor_indices((for_indices)null);
        return;
      case ModificationPackage.FOR_STATEMENT__FOR_STATEMENT:
        getFor_statement().clear();
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
      case ModificationPackage.FOR_STATEMENT__FOR_INDICES:
        return for_indices != null;
      case ModificationPackage.FOR_STATEMENT__FOR_STATEMENT:
        return for_statement != null && !for_statement.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //for_statementImpl
