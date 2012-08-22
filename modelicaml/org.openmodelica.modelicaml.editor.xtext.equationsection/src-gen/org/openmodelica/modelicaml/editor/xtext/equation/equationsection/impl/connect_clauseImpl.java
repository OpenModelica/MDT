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
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.connect_clause;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>connect clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.connect_clauseImpl#getConnector1 <em>Connector1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.connect_clauseImpl#getConnector2 <em>Connector2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class connect_clauseImpl extends equationImpl implements connect_clause
{
  /**
   * The cached value of the '{@link #getConnector1() <em>Connector1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConnector1()
   * @generated
   * @ordered
   */
  protected component_reference connector1;

  /**
   * The cached value of the '{@link #getConnector2() <em>Connector2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConnector2()
   * @generated
   * @ordered
   */
  protected component_reference connector2;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected connect_clauseImpl()
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
    return EquationsectionPackage.Literals.CONNECT_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference getConnector1()
  {
    return connector1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConnector1(component_reference newConnector1, NotificationChain msgs)
  {
    component_reference oldConnector1 = connector1;
    connector1 = newConnector1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1, oldConnector1, newConnector1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConnector1(component_reference newConnector1)
  {
    if (newConnector1 != connector1)
    {
      NotificationChain msgs = null;
      if (connector1 != null)
        msgs = ((InternalEObject)connector1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1, null, msgs);
      if (newConnector1 != null)
        msgs = ((InternalEObject)newConnector1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1, null, msgs);
      msgs = basicSetConnector1(newConnector1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1, newConnector1, newConnector1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference getConnector2()
  {
    return connector2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConnector2(component_reference newConnector2, NotificationChain msgs)
  {
    component_reference oldConnector2 = connector2;
    connector2 = newConnector2;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2, oldConnector2, newConnector2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConnector2(component_reference newConnector2)
  {
    if (newConnector2 != connector2)
    {
      NotificationChain msgs = null;
      if (connector2 != null)
        msgs = ((InternalEObject)connector2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2, null, msgs);
      if (newConnector2 != null)
        msgs = ((InternalEObject)newConnector2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2, null, msgs);
      msgs = basicSetConnector2(newConnector2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2, newConnector2, newConnector2));
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
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1:
        return basicSetConnector1(null, msgs);
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2:
        return basicSetConnector2(null, msgs);
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
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1:
        return getConnector1();
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2:
        return getConnector2();
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
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1:
        setConnector1((component_reference)newValue);
        return;
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2:
        setConnector2((component_reference)newValue);
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
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1:
        setConnector1((component_reference)null);
        return;
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2:
        setConnector2((component_reference)null);
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
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR1:
        return connector1 != null;
      case EquationsectionPackage.CONNECT_CLAUSE__CONNECTOR2:
        return connector2 != null;
    }
    return super.eIsSet(featureID);
  }

} //connect_clauseImpl
