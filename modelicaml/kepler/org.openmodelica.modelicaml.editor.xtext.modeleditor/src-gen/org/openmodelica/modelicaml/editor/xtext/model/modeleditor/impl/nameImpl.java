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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.nameImpl#getFunction_call_args <em>Function call args</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.nameImpl#getName_ID <em>Name ID</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.nameImpl#getNam_ID <em>Nam ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class nameImpl extends name_FunctionImpl implements name
{
  /**
   * The cached value of the '{@link #getFunction_call_args() <em>Function call args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunction_call_args()
   * @generated
   * @ordered
   */
  protected function_call_args function_call_args;

  /**
   * The default value of the '{@link #getName_ID() <em>Name ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName_ID()
   * @generated
   * @ordered
   */
  protected static final String NAME_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName_ID() <em>Name ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName_ID()
   * @generated
   * @ordered
   */
  protected String name_ID = NAME_ID_EDEFAULT;

  /**
   * The cached value of the '{@link #getNam_ID() <em>Nam ID</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNam_ID()
   * @generated
   * @ordered
   */
  protected EList<String> nam_ID;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected nameImpl()
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
    return ModeleditorPackage.Literals.NAME;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_call_args getFunction_call_args()
  {
    return function_call_args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunction_call_args(function_call_args newFunction_call_args, NotificationChain msgs)
  {
    function_call_args oldFunction_call_args = function_call_args;
    function_call_args = newFunction_call_args;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.NAME__FUNCTION_CALL_ARGS, oldFunction_call_args, newFunction_call_args);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunction_call_args(function_call_args newFunction_call_args)
  {
    if (newFunction_call_args != function_call_args)
    {
      NotificationChain msgs = null;
      if (function_call_args != null)
        msgs = ((InternalEObject)function_call_args).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.NAME__FUNCTION_CALL_ARGS, null, msgs);
      if (newFunction_call_args != null)
        msgs = ((InternalEObject)newFunction_call_args).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.NAME__FUNCTION_CALL_ARGS, null, msgs);
      msgs = basicSetFunction_call_args(newFunction_call_args, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.NAME__FUNCTION_CALL_ARGS, newFunction_call_args, newFunction_call_args));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName_ID()
  {
    return name_ID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName_ID(String newName_ID)
  {
    String oldName_ID = name_ID;
    name_ID = newName_ID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.NAME__NAME_ID, oldName_ID, name_ID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getNam_ID()
  {
    if (nam_ID == null)
    {
      nam_ID = new EDataTypeEList<String>(String.class, this, ModeleditorPackage.NAME__NAM_ID);
    }
    return nam_ID;
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
      case ModeleditorPackage.NAME__FUNCTION_CALL_ARGS:
        return basicSetFunction_call_args(null, msgs);
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
      case ModeleditorPackage.NAME__FUNCTION_CALL_ARGS:
        return getFunction_call_args();
      case ModeleditorPackage.NAME__NAME_ID:
        return getName_ID();
      case ModeleditorPackage.NAME__NAM_ID:
        return getNam_ID();
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
      case ModeleditorPackage.NAME__FUNCTION_CALL_ARGS:
        setFunction_call_args((function_call_args)newValue);
        return;
      case ModeleditorPackage.NAME__NAME_ID:
        setName_ID((String)newValue);
        return;
      case ModeleditorPackage.NAME__NAM_ID:
        getNam_ID().clear();
        getNam_ID().addAll((Collection<? extends String>)newValue);
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
      case ModeleditorPackage.NAME__FUNCTION_CALL_ARGS:
        setFunction_call_args((function_call_args)null);
        return;
      case ModeleditorPackage.NAME__NAME_ID:
        setName_ID(NAME_ID_EDEFAULT);
        return;
      case ModeleditorPackage.NAME__NAM_ID:
        getNam_ID().clear();
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
      case ModeleditorPackage.NAME__FUNCTION_CALL_ARGS:
        return function_call_args != null;
      case ModeleditorPackage.NAME__NAME_ID:
        return NAME_ID_EDEFAULT == null ? name_ID != null : !NAME_ID_EDEFAULT.equals(name_ID);
      case ModeleditorPackage.NAME__NAM_ID:
        return nam_ID != null && !nam_ID.isEmpty();
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
    result.append(" (name_ID: ");
    result.append(name_ID);
    result.append(", nam_ID: ");
    result.append(nam_ID);
    result.append(')');
    return result.toString();
  }

} //nameImpl
