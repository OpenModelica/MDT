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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.activityControlFlowExpression;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>activity Control Flow Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.activityControlFlowExpressionImpl#getEpression <em>Epression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class activityControlFlowExpressionImpl extends MinimalEObjectImpl.Container implements activityControlFlowExpression
{
  /**
   * The cached value of the '{@link #getEpression() <em>Epression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEpression()
   * @generated
   * @ordered
   */
  protected expression epression;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected activityControlFlowExpressionImpl()
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
    return ActivitycontrolflowguardexpressionPackage.Literals.ACTIVITY_CONTROL_FLOW_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getEpression()
  {
    return epression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEpression(expression newEpression, NotificationChain msgs)
  {
    expression oldEpression = epression;
    epression = newEpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION, oldEpression, newEpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEpression(expression newEpression)
  {
    if (newEpression != epression)
    {
      NotificationChain msgs = null;
      if (epression != null)
        msgs = ((InternalEObject)epression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION, null, msgs);
      if (newEpression != null)
        msgs = ((InternalEObject)newEpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION, null, msgs);
      msgs = basicSetEpression(newEpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION, newEpression, newEpression));
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
      case ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION:
        return basicSetEpression(null, msgs);
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
      case ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION:
        return getEpression();
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
      case ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION:
        setEpression((expression)newValue);
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
      case ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION:
        setEpression((expression)null);
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
      case ActivitycontrolflowguardexpressionPackage.ACTIVITY_CONTROL_FLOW_EXPRESSION__EPRESSION:
        return epression != null;
    }
    return super.eIsSet(featureID);
  }

} //activityControlFlowExpressionImpl
