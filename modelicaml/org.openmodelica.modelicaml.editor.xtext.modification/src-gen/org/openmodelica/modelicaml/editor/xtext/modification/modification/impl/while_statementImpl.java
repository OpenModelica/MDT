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
import org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>while statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.while_statementImpl#getWhile_expr <em>While expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.while_statementImpl#getWhile_stat <em>While stat</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class while_statementImpl extends MinimalEObjectImpl.Container implements while_statement
{
  /**
   * The cached value of the '{@link #getWhile_expr() <em>While expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhile_expr()
   * @generated
   * @ordered
   */
  protected expression while_expr;

  /**
   * The cached value of the '{@link #getWhile_stat() <em>While stat</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhile_stat()
   * @generated
   * @ordered
   */
  protected EList<while_statement> while_stat;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected while_statementImpl()
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
    return ModificationPackage.Literals.WHILE_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getWhile_expr()
  {
    return while_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhile_expr(expression newWhile_expr, NotificationChain msgs)
  {
    expression oldWhile_expr = while_expr;
    while_expr = newWhile_expr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.WHILE_STATEMENT__WHILE_EXPR, oldWhile_expr, newWhile_expr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhile_expr(expression newWhile_expr)
  {
    if (newWhile_expr != while_expr)
    {
      NotificationChain msgs = null;
      if (while_expr != null)
        msgs = ((InternalEObject)while_expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.WHILE_STATEMENT__WHILE_EXPR, null, msgs);
      if (newWhile_expr != null)
        msgs = ((InternalEObject)newWhile_expr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.WHILE_STATEMENT__WHILE_EXPR, null, msgs);
      msgs = basicSetWhile_expr(newWhile_expr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.WHILE_STATEMENT__WHILE_EXPR, newWhile_expr, newWhile_expr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<while_statement> getWhile_stat()
  {
    if (while_stat == null)
    {
      while_stat = new EObjectContainmentEList<while_statement>(while_statement.class, this, ModificationPackage.WHILE_STATEMENT__WHILE_STAT);
    }
    return while_stat;
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
      case ModificationPackage.WHILE_STATEMENT__WHILE_EXPR:
        return basicSetWhile_expr(null, msgs);
      case ModificationPackage.WHILE_STATEMENT__WHILE_STAT:
        return ((InternalEList<?>)getWhile_stat()).basicRemove(otherEnd, msgs);
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
      case ModificationPackage.WHILE_STATEMENT__WHILE_EXPR:
        return getWhile_expr();
      case ModificationPackage.WHILE_STATEMENT__WHILE_STAT:
        return getWhile_stat();
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
      case ModificationPackage.WHILE_STATEMENT__WHILE_EXPR:
        setWhile_expr((expression)newValue);
        return;
      case ModificationPackage.WHILE_STATEMENT__WHILE_STAT:
        getWhile_stat().clear();
        getWhile_stat().addAll((Collection<? extends while_statement>)newValue);
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
      case ModificationPackage.WHILE_STATEMENT__WHILE_EXPR:
        setWhile_expr((expression)null);
        return;
      case ModificationPackage.WHILE_STATEMENT__WHILE_STAT:
        getWhile_stat().clear();
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
      case ModificationPackage.WHILE_STATEMENT__WHILE_EXPR:
        return while_expr != null;
      case ModificationPackage.WHILE_STATEMENT__WHILE_STAT:
        return while_stat != null && !while_stat.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //while_statementImpl
