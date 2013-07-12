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
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Macro BEFORE</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_BEFOREImpl#getInt <em>Int</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_BEFOREImpl#getNum <em>Num</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_BEFOREImpl#getComponent_reference <em>Component reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Macro_BEFOREImpl extends MinimalEObjectImpl.Container implements Macro_BEFORE
{
  /**
   * The default value of the '{@link #getInt() <em>Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInt()
   * @generated
   * @ordered
   */
  protected static final int INT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getInt() <em>Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInt()
   * @generated
   * @ordered
   */
  protected int int_ = INT_EDEFAULT;

  /**
   * The default value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected static final String NUM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected String num = NUM_EDEFAULT;

  /**
   * The cached value of the '{@link #getComponent_reference() <em>Component reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponent_reference()
   * @generated
   * @ordered
   */
  protected component_reference component_reference;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Macro_BEFOREImpl()
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
    return StatetransitionguardexpressionPackage.Literals.MACRO_BEFORE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getInt()
  {
    return int_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInt(int newInt)
  {
    int oldInt = int_;
    int_ = newInt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.MACRO_BEFORE__INT, oldInt, int_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNum()
  {
    return num;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNum(String newNum)
  {
    String oldNum = num;
    num = newNum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.MACRO_BEFORE__NUM, oldNum, num));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference getComponent_reference()
  {
    return component_reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComponent_reference(component_reference newComponent_reference, NotificationChain msgs)
  {
    component_reference oldComponent_reference = component_reference;
    component_reference = newComponent_reference;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE, oldComponent_reference, newComponent_reference);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponent_reference(component_reference newComponent_reference)
  {
    if (newComponent_reference != component_reference)
    {
      NotificationChain msgs = null;
      if (component_reference != null)
        msgs = ((InternalEObject)component_reference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE, null, msgs);
      if (newComponent_reference != null)
        msgs = ((InternalEObject)newComponent_reference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE, null, msgs);
      msgs = basicSetComponent_reference(newComponent_reference, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE, newComponent_reference, newComponent_reference));
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
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE:
        return basicSetComponent_reference(null, msgs);
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
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__INT:
        return getInt();
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__NUM:
        return getNum();
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE:
        return getComponent_reference();
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
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__INT:
        setInt((Integer)newValue);
        return;
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__NUM:
        setNum((String)newValue);
        return;
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE:
        setComponent_reference((component_reference)newValue);
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
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__INT:
        setInt(INT_EDEFAULT);
        return;
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__NUM:
        setNum(NUM_EDEFAULT);
        return;
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE:
        setComponent_reference((component_reference)null);
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
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__INT:
        return int_ != INT_EDEFAULT;
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__NUM:
        return NUM_EDEFAULT == null ? num != null : !NUM_EDEFAULT.equals(num);
      case StatetransitionguardexpressionPackage.MACRO_BEFORE__COMPONENT_REFERENCE:
        return component_reference != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (int: ");
    result.append(int_);
    result.append(", num: ");
    result.append(num);
    result.append(')');
    return result.toString();
  }

} //Macro_BEFOREImpl
