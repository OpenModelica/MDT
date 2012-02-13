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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>if statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_statementImpl#getExprtrue <em>Exprtrue</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_statementImpl#getState <em>State</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_statementImpl#getExprStilltrue <em>Expr Stilltrue</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class if_statementImpl extends MinimalEObjectImpl.Container implements if_statement
{
  /**
   * The cached value of the '{@link #getExprtrue() <em>Exprtrue</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExprtrue()
   * @generated
   * @ordered
   */
  protected expression exprtrue;

  /**
   * The cached value of the '{@link #getState() <em>State</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getState()
   * @generated
   * @ordered
   */
  protected EList<statement> state;

  /**
   * The cached value of the '{@link #getExprStilltrue() <em>Expr Stilltrue</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExprStilltrue()
   * @generated
   * @ordered
   */
  protected EList<expression> exprStilltrue;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected if_statementImpl()
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
    return ModificationPackage.Literals.IF_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getExprtrue()
  {
    return exprtrue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExprtrue(expression newExprtrue, NotificationChain msgs)
  {
    expression oldExprtrue = exprtrue;
    exprtrue = newExprtrue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.IF_STATEMENT__EXPRTRUE, oldExprtrue, newExprtrue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExprtrue(expression newExprtrue)
  {
    if (newExprtrue != exprtrue)
    {
      NotificationChain msgs = null;
      if (exprtrue != null)
        msgs = ((InternalEObject)exprtrue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.IF_STATEMENT__EXPRTRUE, null, msgs);
      if (newExprtrue != null)
        msgs = ((InternalEObject)newExprtrue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.IF_STATEMENT__EXPRTRUE, null, msgs);
      msgs = basicSetExprtrue(newExprtrue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.IF_STATEMENT__EXPRTRUE, newExprtrue, newExprtrue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<statement> getState()
  {
    if (state == null)
    {
      state = new EObjectContainmentEList<statement>(statement.class, this, ModificationPackage.IF_STATEMENT__STATE);
    }
    return state;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression> getExprStilltrue()
  {
    if (exprStilltrue == null)
    {
      exprStilltrue = new EObjectContainmentEList<expression>(expression.class, this, ModificationPackage.IF_STATEMENT__EXPR_STILLTRUE);
    }
    return exprStilltrue;
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
      case ModificationPackage.IF_STATEMENT__EXPRTRUE:
        return basicSetExprtrue(null, msgs);
      case ModificationPackage.IF_STATEMENT__STATE:
        return ((InternalEList<?>)getState()).basicRemove(otherEnd, msgs);
      case ModificationPackage.IF_STATEMENT__EXPR_STILLTRUE:
        return ((InternalEList<?>)getExprStilltrue()).basicRemove(otherEnd, msgs);
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
      case ModificationPackage.IF_STATEMENT__EXPRTRUE:
        return getExprtrue();
      case ModificationPackage.IF_STATEMENT__STATE:
        return getState();
      case ModificationPackage.IF_STATEMENT__EXPR_STILLTRUE:
        return getExprStilltrue();
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
      case ModificationPackage.IF_STATEMENT__EXPRTRUE:
        setExprtrue((expression)newValue);
        return;
      case ModificationPackage.IF_STATEMENT__STATE:
        getState().clear();
        getState().addAll((Collection<? extends statement>)newValue);
        return;
      case ModificationPackage.IF_STATEMENT__EXPR_STILLTRUE:
        getExprStilltrue().clear();
        getExprStilltrue().addAll((Collection<? extends expression>)newValue);
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
      case ModificationPackage.IF_STATEMENT__EXPRTRUE:
        setExprtrue((expression)null);
        return;
      case ModificationPackage.IF_STATEMENT__STATE:
        getState().clear();
        return;
      case ModificationPackage.IF_STATEMENT__EXPR_STILLTRUE:
        getExprStilltrue().clear();
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
      case ModificationPackage.IF_STATEMENT__EXPRTRUE:
        return exprtrue != null;
      case ModificationPackage.IF_STATEMENT__STATE:
        return state != null && !state.isEmpty();
      case ModificationPackage.IF_STATEMENT__EXPR_STILLTRUE:
        return exprStilltrue != null && !exprStilltrue.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //if_statementImpl
