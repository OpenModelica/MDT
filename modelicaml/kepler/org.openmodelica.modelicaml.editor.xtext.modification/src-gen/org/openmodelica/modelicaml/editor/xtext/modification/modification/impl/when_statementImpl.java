/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
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
import org.openmodelica.modelicaml.editor.xtext.modification.modification.statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>when statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl#getWhen_expr <em>When expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl#getWhen_stat_true <em>When stat true</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl#getElse_When_expr <em>Else When expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_statementImpl#getThen_statement <em>Then statement</em>}</li>
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
    return ModificationPackage.Literals.WHEN_STATEMENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.WHEN_STATEMENT__WHEN_EXPR, oldWhen_expr, newWhen_expr);
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
        msgs = ((InternalEObject)when_expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.WHEN_STATEMENT__WHEN_EXPR, null, msgs);
      if (newWhen_expr != null)
        msgs = ((InternalEObject)newWhen_expr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.WHEN_STATEMENT__WHEN_EXPR, null, msgs);
      msgs = basicSetWhen_expr(newWhen_expr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.WHEN_STATEMENT__WHEN_EXPR, newWhen_expr, newWhen_expr));
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
      when_stat_true = new EObjectContainmentEList<statement>(statement.class, this, ModificationPackage.WHEN_STATEMENT__WHEN_STAT_TRUE);
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
      else_When_expr = new EObjectContainmentEList<expression>(expression.class, this, ModificationPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR);
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
      then_statement = new EObjectContainmentEList<statement>(statement.class, this, ModificationPackage.WHEN_STATEMENT__THEN_STATEMENT);
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
      case ModificationPackage.WHEN_STATEMENT__WHEN_EXPR:
        return basicSetWhen_expr(null, msgs);
      case ModificationPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        return ((InternalEList<?>)getWhen_stat_true()).basicRemove(otherEnd, msgs);
      case ModificationPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        return ((InternalEList<?>)getElse_When_expr()).basicRemove(otherEnd, msgs);
      case ModificationPackage.WHEN_STATEMENT__THEN_STATEMENT:
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
      case ModificationPackage.WHEN_STATEMENT__WHEN_EXPR:
        return getWhen_expr();
      case ModificationPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        return getWhen_stat_true();
      case ModificationPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        return getElse_When_expr();
      case ModificationPackage.WHEN_STATEMENT__THEN_STATEMENT:
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
      case ModificationPackage.WHEN_STATEMENT__WHEN_EXPR:
        setWhen_expr((expression)newValue);
        return;
      case ModificationPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        getWhen_stat_true().clear();
        getWhen_stat_true().addAll((Collection<? extends statement>)newValue);
        return;
      case ModificationPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        getElse_When_expr().clear();
        getElse_When_expr().addAll((Collection<? extends expression>)newValue);
        return;
      case ModificationPackage.WHEN_STATEMENT__THEN_STATEMENT:
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
      case ModificationPackage.WHEN_STATEMENT__WHEN_EXPR:
        setWhen_expr((expression)null);
        return;
      case ModificationPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        getWhen_stat_true().clear();
        return;
      case ModificationPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        getElse_When_expr().clear();
        return;
      case ModificationPackage.WHEN_STATEMENT__THEN_STATEMENT:
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
      case ModificationPackage.WHEN_STATEMENT__WHEN_EXPR:
        return when_expr != null;
      case ModificationPackage.WHEN_STATEMENT__WHEN_STAT_TRUE:
        return when_stat_true != null && !when_stat_true.isEmpty();
      case ModificationPackage.WHEN_STATEMENT__ELSE_WHEN_EXPR:
        return else_When_expr != null && !else_When_expr.isEmpty();
      case ModificationPackage.WHEN_STATEMENT__THEN_STATEMENT:
        return then_statement != null && !then_statement.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //when_statementImpl
