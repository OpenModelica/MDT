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
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>if equation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl#getExprtrue <em>Exprtrue</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl#getEqn <em>Eqn</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl#getExprStilltrue <em>Expr Stilltrue</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl#getThenEqn <em>Then Eqn</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.if_equationImpl#getElseEqn <em>Else Eqn</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class if_equationImpl extends MinimalEObjectImpl.Container implements if_equation
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
   * The cached value of the '{@link #getEqn() <em>Eqn</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEqn()
   * @generated
   * @ordered
   */
  protected EList<equation> eqn;

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
   * The cached value of the '{@link #getThenEqn() <em>Then Eqn</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThenEqn()
   * @generated
   * @ordered
   */
  protected EList<equation> thenEqn;

  /**
   * The cached value of the '{@link #getElseEqn() <em>Else Eqn</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseEqn()
   * @generated
   * @ordered
   */
  protected EList<equation> elseEqn;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected if_equationImpl()
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
    return ModificationPackage.Literals.IF_EQUATION;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.IF_EQUATION__EXPRTRUE, oldExprtrue, newExprtrue);
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
        msgs = ((InternalEObject)exprtrue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.IF_EQUATION__EXPRTRUE, null, msgs);
      if (newExprtrue != null)
        msgs = ((InternalEObject)newExprtrue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.IF_EQUATION__EXPRTRUE, null, msgs);
      msgs = basicSetExprtrue(newExprtrue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.IF_EQUATION__EXPRTRUE, newExprtrue, newExprtrue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation> getEqn()
  {
    if (eqn == null)
    {
      eqn = new EObjectContainmentEList<equation>(equation.class, this, ModificationPackage.IF_EQUATION__EQN);
    }
    return eqn;
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
      exprStilltrue = new EObjectContainmentEList<expression>(expression.class, this, ModificationPackage.IF_EQUATION__EXPR_STILLTRUE);
    }
    return exprStilltrue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation> getThenEqn()
  {
    if (thenEqn == null)
    {
      thenEqn = new EObjectContainmentEList<equation>(equation.class, this, ModificationPackage.IF_EQUATION__THEN_EQN);
    }
    return thenEqn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation> getElseEqn()
  {
    if (elseEqn == null)
    {
      elseEqn = new EObjectContainmentEList<equation>(equation.class, this, ModificationPackage.IF_EQUATION__ELSE_EQN);
    }
    return elseEqn;
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
      case ModificationPackage.IF_EQUATION__EXPRTRUE:
        return basicSetExprtrue(null, msgs);
      case ModificationPackage.IF_EQUATION__EQN:
        return ((InternalEList<?>)getEqn()).basicRemove(otherEnd, msgs);
      case ModificationPackage.IF_EQUATION__EXPR_STILLTRUE:
        return ((InternalEList<?>)getExprStilltrue()).basicRemove(otherEnd, msgs);
      case ModificationPackage.IF_EQUATION__THEN_EQN:
        return ((InternalEList<?>)getThenEqn()).basicRemove(otherEnd, msgs);
      case ModificationPackage.IF_EQUATION__ELSE_EQN:
        return ((InternalEList<?>)getElseEqn()).basicRemove(otherEnd, msgs);
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
      case ModificationPackage.IF_EQUATION__EXPRTRUE:
        return getExprtrue();
      case ModificationPackage.IF_EQUATION__EQN:
        return getEqn();
      case ModificationPackage.IF_EQUATION__EXPR_STILLTRUE:
        return getExprStilltrue();
      case ModificationPackage.IF_EQUATION__THEN_EQN:
        return getThenEqn();
      case ModificationPackage.IF_EQUATION__ELSE_EQN:
        return getElseEqn();
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
      case ModificationPackage.IF_EQUATION__EXPRTRUE:
        setExprtrue((expression)newValue);
        return;
      case ModificationPackage.IF_EQUATION__EQN:
        getEqn().clear();
        getEqn().addAll((Collection<? extends equation>)newValue);
        return;
      case ModificationPackage.IF_EQUATION__EXPR_STILLTRUE:
        getExprStilltrue().clear();
        getExprStilltrue().addAll((Collection<? extends expression>)newValue);
        return;
      case ModificationPackage.IF_EQUATION__THEN_EQN:
        getThenEqn().clear();
        getThenEqn().addAll((Collection<? extends equation>)newValue);
        return;
      case ModificationPackage.IF_EQUATION__ELSE_EQN:
        getElseEqn().clear();
        getElseEqn().addAll((Collection<? extends equation>)newValue);
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
      case ModificationPackage.IF_EQUATION__EXPRTRUE:
        setExprtrue((expression)null);
        return;
      case ModificationPackage.IF_EQUATION__EQN:
        getEqn().clear();
        return;
      case ModificationPackage.IF_EQUATION__EXPR_STILLTRUE:
        getExprStilltrue().clear();
        return;
      case ModificationPackage.IF_EQUATION__THEN_EQN:
        getThenEqn().clear();
        return;
      case ModificationPackage.IF_EQUATION__ELSE_EQN:
        getElseEqn().clear();
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
      case ModificationPackage.IF_EQUATION__EXPRTRUE:
        return exprtrue != null;
      case ModificationPackage.IF_EQUATION__EQN:
        return eqn != null && !eqn.isEmpty();
      case ModificationPackage.IF_EQUATION__EXPR_STILLTRUE:
        return exprStilltrue != null && !exprStilltrue.isEmpty();
      case ModificationPackage.IF_EQUATION__THEN_EQN:
        return thenEqn != null && !thenEqn.isEmpty();
      case ModificationPackage.IF_EQUATION__ELSE_EQN:
        return elseEqn != null && !elseEqn.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //if_equationImpl
