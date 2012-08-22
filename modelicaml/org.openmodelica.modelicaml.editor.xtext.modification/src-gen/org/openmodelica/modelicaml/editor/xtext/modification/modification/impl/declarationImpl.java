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
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.modification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.declarationImpl#getRedeclaredComponentName <em>Redeclared Component Name</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.declarationImpl#getArraySubs <em>Array Subs</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.declarationImpl#getMod <em>Mod</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class declarationImpl extends component_declaration1Impl implements declaration
{
  /**
   * The default value of the '{@link #getRedeclaredComponentName() <em>Redeclared Component Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRedeclaredComponentName()
   * @generated
   * @ordered
   */
  protected static final String REDECLARED_COMPONENT_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRedeclaredComponentName() <em>Redeclared Component Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRedeclaredComponentName()
   * @generated
   * @ordered
   */
  protected String redeclaredComponentName = REDECLARED_COMPONENT_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getArraySubs() <em>Array Subs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArraySubs()
   * @generated
   * @ordered
   */
  protected array_subscripts arraySubs;

  /**
   * The cached value of the '{@link #getMod() <em>Mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMod()
   * @generated
   * @ordered
   */
  protected modification mod;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected declarationImpl()
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
    return ModificationPackage.Literals.DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRedeclaredComponentName()
  {
    return redeclaredComponentName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRedeclaredComponentName(String newRedeclaredComponentName)
  {
    String oldRedeclaredComponentName = redeclaredComponentName;
    redeclaredComponentName = newRedeclaredComponentName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.DECLARATION__REDECLARED_COMPONENT_NAME, oldRedeclaredComponentName, redeclaredComponentName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public array_subscripts getArraySubs()
  {
    return arraySubs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArraySubs(array_subscripts newArraySubs, NotificationChain msgs)
  {
    array_subscripts oldArraySubs = arraySubs;
    arraySubs = newArraySubs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.DECLARATION__ARRAY_SUBS, oldArraySubs, newArraySubs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArraySubs(array_subscripts newArraySubs)
  {
    if (newArraySubs != arraySubs)
    {
      NotificationChain msgs = null;
      if (arraySubs != null)
        msgs = ((InternalEObject)arraySubs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.DECLARATION__ARRAY_SUBS, null, msgs);
      if (newArraySubs != null)
        msgs = ((InternalEObject)newArraySubs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.DECLARATION__ARRAY_SUBS, null, msgs);
      msgs = basicSetArraySubs(newArraySubs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.DECLARATION__ARRAY_SUBS, newArraySubs, newArraySubs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public modification getMod()
  {
    return mod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMod(modification newMod, NotificationChain msgs)
  {
    modification oldMod = mod;
    mod = newMod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.DECLARATION__MOD, oldMod, newMod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMod(modification newMod)
  {
    if (newMod != mod)
    {
      NotificationChain msgs = null;
      if (mod != null)
        msgs = ((InternalEObject)mod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.DECLARATION__MOD, null, msgs);
      if (newMod != null)
        msgs = ((InternalEObject)newMod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.DECLARATION__MOD, null, msgs);
      msgs = basicSetMod(newMod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.DECLARATION__MOD, newMod, newMod));
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
      case ModificationPackage.DECLARATION__ARRAY_SUBS:
        return basicSetArraySubs(null, msgs);
      case ModificationPackage.DECLARATION__MOD:
        return basicSetMod(null, msgs);
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
      case ModificationPackage.DECLARATION__REDECLARED_COMPONENT_NAME:
        return getRedeclaredComponentName();
      case ModificationPackage.DECLARATION__ARRAY_SUBS:
        return getArraySubs();
      case ModificationPackage.DECLARATION__MOD:
        return getMod();
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
      case ModificationPackage.DECLARATION__REDECLARED_COMPONENT_NAME:
        setRedeclaredComponentName((String)newValue);
        return;
      case ModificationPackage.DECLARATION__ARRAY_SUBS:
        setArraySubs((array_subscripts)newValue);
        return;
      case ModificationPackage.DECLARATION__MOD:
        setMod((modification)newValue);
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
      case ModificationPackage.DECLARATION__REDECLARED_COMPONENT_NAME:
        setRedeclaredComponentName(REDECLARED_COMPONENT_NAME_EDEFAULT);
        return;
      case ModificationPackage.DECLARATION__ARRAY_SUBS:
        setArraySubs((array_subscripts)null);
        return;
      case ModificationPackage.DECLARATION__MOD:
        setMod((modification)null);
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
      case ModificationPackage.DECLARATION__REDECLARED_COMPONENT_NAME:
        return REDECLARED_COMPONENT_NAME_EDEFAULT == null ? redeclaredComponentName != null : !REDECLARED_COMPONENT_NAME_EDEFAULT.equals(redeclaredComponentName);
      case ModificationPackage.DECLARATION__ARRAY_SUBS:
        return arraySubs != null;
      case ModificationPackage.DECLARATION__MOD:
        return mod != null;
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
    result.append(" (redeclaredComponentName: ");
    result.append(redeclaredComponentName);
    result.append(')');
    return result.toString();
  }

} //declarationImpl
