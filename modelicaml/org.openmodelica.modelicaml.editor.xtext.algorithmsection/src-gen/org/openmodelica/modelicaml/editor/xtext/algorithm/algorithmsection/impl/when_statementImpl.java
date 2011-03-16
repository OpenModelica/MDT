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
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.statement;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.when_statement;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>when statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl#getWhen_expr <em>When expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl#getWhen_stat_true <em>When stat true</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl#getElse_When_expr <em>Else When expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.when_statementImpl#getThen_statement <em>Then statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class when_statementImpl extends MinimalEObjectImpl.Container implements when_statement
{
  /**
   * The cached value of the '{@link #getWhen_expr() <em>When expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhen_expr()
   * @generated
   * @ordered
   */
  protected expression when_expr;

  /**
   * The cached value of the '{@link #getWhen_stat_true() <em>When stat true</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhen_stat_true()
   * @generated
   * @ordered
   */
  protected EList<statement> when_stat_true;

  /**
   * The cached value of the '{@link #getElse_When_expr() <em>Else When expr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElse_When_expr()
   * @generated
   * @ordered
   */
  protected EList<expression> else_When_expr;

  /**
   * The cached value of the '{@link #getThen_statement() <em>Then statement</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThen_statement()
   * @generated
   * @ordered
   */
  protected EList<statement> then_statement;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected when_statementImpl()
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
    return AlgorithmsectionPackage.Literals.WHEN_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getWhen_expr()
  {
    return when_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhen_expr(expression newWhen_expr, NotificationChain msgs)
  {
    expression oldWhen_expr = when_expr;
    when_expr = newWhen_expr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR, oldWhen_expr, newWhen_expr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhen_expr(expression newWhen_expr)
  {
    if (newWhen_expr != when_expr)
    {
      NotificationChain msgs = null;
      if (when_expr != null)
        msgs = ((InternalEObject)when_expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR, null, msgs);
      if (newWhen_expr != null)
        msgs = ((InternalEObject)newWhen_expr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR, null, msgs);
      msgs = basicSetWhen_expr(newWhen_expr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR, newWhen_expr, newWhen_expr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<statement> getWhen_stat_true()
  {
    if (when_stat_true == null)
    {
      when_stat_true = new EObjectContainmentEList<statement>(statement.class, this, AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_STAT_TRUE);
    }
    return when_stat_true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression> getElse_When_expr()
  {
    if (else_When_expr == null)
    {
      else_When_expr = new EObjectContainmentEList<expression>(expression.class, this, AlgorithmsectionPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR);
    }
    return else_When_expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<statement> getThen_statement()
  {
    if (then_statement == null)
    {
      then_statement = new EObjectContainmentEList<statement>(statement.class, this, AlgorithmsectionPackage.WHEN_STATEMENT__THEN_STATEMENT);
    }
    return then_statement;
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
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR:
        return basicSetWhen_expr(null, msgs);
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        return ((InternalEList<?>)getWhen_stat_true()).basicRemove(otherEnd, msgs);
      case AlgorithmsectionPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        return ((InternalEList<?>)getElse_When_expr()).basicRemove(otherEnd, msgs);
      case AlgorithmsectionPackage.WHEN_STATEMENT__THEN_STATEMENT:
        return ((InternalEList<?>)getThen_statement()).basicRemove(otherEnd, msgs);
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
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR:
        return getWhen_expr();
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        return getWhen_stat_true();
      case AlgorithmsectionPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        return getElse_When_expr();
      case AlgorithmsectionPackage.WHEN_STATEMENT__THEN_STATEMENT:
        return getThen_statement();
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
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR:
        setWhen_expr((expression)newValue);
        return;
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        getWhen_stat_true().clear();
        getWhen_stat_true().addAll((Collection<? extends statement>)newValue);
        return;
      case AlgorithmsectionPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        getElse_When_expr().clear();
        getElse_When_expr().addAll((Collection<? extends expression>)newValue);
        return;
      case AlgorithmsectionPackage.WHEN_STATEMENT__THEN_STATEMENT:
        getThen_statement().clear();
        getThen_statement().addAll((Collection<? extends statement>)newValue);
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
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR:
        setWhen_expr((expression)null);
        return;
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        getWhen_stat_true().clear();
        return;
      case AlgorithmsectionPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        getElse_When_expr().clear();
        return;
      case AlgorithmsectionPackage.WHEN_STATEMENT__THEN_STATEMENT:
        getThen_statement().clear();
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
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_EXPR:
        return when_expr != null;
      case AlgorithmsectionPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        return when_stat_true != null && !when_stat_true.isEmpty();
      case AlgorithmsectionPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        return else_When_expr != null && !else_When_expr.isEmpty();
      case AlgorithmsectionPackage.WHEN_STATEMENT__THEN_STATEMENT:
        return then_statement != null && !then_statement.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //when_statementImpl
