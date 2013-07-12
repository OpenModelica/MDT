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
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>simple expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.simple_expressionImpl#getLog_Exp <em>Log Exp</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.simple_expressionImpl#getS_Logical_expression <em>SLogical expression</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.simple_expressionImpl#getL_Logical_expression <em>LLogical expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class simple_expressionImpl extends MinimalEObjectImpl.Container implements simple_expression
{
  /**
   * The cached value of the '{@link #getLog_Exp() <em>Log Exp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLog_Exp()
   * @generated
   * @ordered
   */
  protected logical_expression log_Exp;

  /**
   * The cached value of the '{@link #getS_Logical_expression() <em>SLogical expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getS_Logical_expression()
   * @generated
   * @ordered
   */
  protected logical_expression s_Logical_expression;

  /**
   * The cached value of the '{@link #getL_Logical_expression() <em>LLogical expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getL_Logical_expression()
   * @generated
   * @ordered
   */
  protected logical_expression l_Logical_expression;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected simple_expressionImpl()
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
    return ModeleditorPackage.Literals.SIMPLE_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public logical_expression getLog_Exp()
  {
    return log_Exp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLog_Exp(logical_expression newLog_Exp, NotificationChain msgs)
  {
    logical_expression oldLog_Exp = log_Exp;
    log_Exp = newLog_Exp;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP, oldLog_Exp, newLog_Exp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLog_Exp(logical_expression newLog_Exp)
  {
    if (newLog_Exp != log_Exp)
    {
      NotificationChain msgs = null;
      if (log_Exp != null)
        msgs = ((InternalEObject)log_Exp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP, null, msgs);
      if (newLog_Exp != null)
        msgs = ((InternalEObject)newLog_Exp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP, null, msgs);
      msgs = basicSetLog_Exp(newLog_Exp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP, newLog_Exp, newLog_Exp));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public logical_expression getS_Logical_expression()
  {
    return s_Logical_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetS_Logical_expression(logical_expression newS_Logical_expression, NotificationChain msgs)
  {
    logical_expression oldS_Logical_expression = s_Logical_expression;
    s_Logical_expression = newS_Logical_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION, oldS_Logical_expression, newS_Logical_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setS_Logical_expression(logical_expression newS_Logical_expression)
  {
    if (newS_Logical_expression != s_Logical_expression)
    {
      NotificationChain msgs = null;
      if (s_Logical_expression != null)
        msgs = ((InternalEObject)s_Logical_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION, null, msgs);
      if (newS_Logical_expression != null)
        msgs = ((InternalEObject)newS_Logical_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION, null, msgs);
      msgs = basicSetS_Logical_expression(newS_Logical_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION, newS_Logical_expression, newS_Logical_expression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public logical_expression getL_Logical_expression()
  {
    return l_Logical_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetL_Logical_expression(logical_expression newL_Logical_expression, NotificationChain msgs)
  {
    logical_expression oldL_Logical_expression = l_Logical_expression;
    l_Logical_expression = newL_Logical_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION, oldL_Logical_expression, newL_Logical_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setL_Logical_expression(logical_expression newL_Logical_expression)
  {
    if (newL_Logical_expression != l_Logical_expression)
    {
      NotificationChain msgs = null;
      if (l_Logical_expression != null)
        msgs = ((InternalEObject)l_Logical_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION, null, msgs);
      if (newL_Logical_expression != null)
        msgs = ((InternalEObject)newL_Logical_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION, null, msgs);
      msgs = basicSetL_Logical_expression(newL_Logical_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION, newL_Logical_expression, newL_Logical_expression));
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
      case ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP:
        return basicSetLog_Exp(null, msgs);
      case ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION:
        return basicSetS_Logical_expression(null, msgs);
      case ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION:
        return basicSetL_Logical_expression(null, msgs);
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
      case ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP:
        return getLog_Exp();
      case ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION:
        return getS_Logical_expression();
      case ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION:
        return getL_Logical_expression();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP:
        setLog_Exp((logical_expression)newValue);
        return;
      case ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION:
        setS_Logical_expression((logical_expression)newValue);
        return;
      case ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION:
        setL_Logical_expression((logical_expression)newValue);
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
      case ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP:
        setLog_Exp((logical_expression)null);
        return;
      case ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION:
        setS_Logical_expression((logical_expression)null);
        return;
      case ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION:
        setL_Logical_expression((logical_expression)null);
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
      case ModeleditorPackage.SIMPLE_EXPRESSION__LOG_EXP:
        return log_Exp != null;
      case ModeleditorPackage.SIMPLE_EXPRESSION__SLOGICAL_EXPRESSION:
        return s_Logical_expression != null;
      case ModeleditorPackage.SIMPLE_EXPRESSION__LLOGICAL_EXPRESSION:
        return l_Logical_expression != null;
    }
    return super.eIsSet(featureID);
  }

} //simple_expressionImpl
