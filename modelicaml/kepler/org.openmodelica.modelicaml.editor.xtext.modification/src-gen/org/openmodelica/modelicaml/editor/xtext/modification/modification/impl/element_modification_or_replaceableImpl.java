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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>element modification or replaceable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modification_or_replaceableImpl#getElement_mod <em>Element mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_modification_or_replaceableImpl#getElement_rep <em>Element rep</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class element_modification_or_replaceableImpl extends argumentImpl implements element_modification_or_replaceable
{
  /**
   * The cached value of the '{@link #getElement_mod() <em>Element mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElement_mod()
   * @generated
   * @ordered
   */
  protected element_modification element_mod;

  /**
   * The cached value of the '{@link #getElement_rep() <em>Element rep</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElement_rep()
   * @generated
   * @ordered
   */
  protected element_replaceable element_rep;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected element_modification_or_replaceableImpl()
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
    return ModificationPackage.Literals.ELEMENT_MODIFICATION_OR_REPLACEABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_modification getElement_mod()
  {
    return element_mod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElement_mod(element_modification newElement_mod, NotificationChain msgs)
  {
    element_modification oldElement_mod = element_mod;
    element_mod = newElement_mod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD, oldElement_mod, newElement_mod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElement_mod(element_modification newElement_mod)
  {
    if (newElement_mod != element_mod)
    {
      NotificationChain msgs = null;
      if (element_mod != null)
        msgs = ((InternalEObject)element_mod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD, null, msgs);
      if (newElement_mod != null)
        msgs = ((InternalEObject)newElement_mod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD, null, msgs);
      msgs = basicSetElement_mod(newElement_mod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD, newElement_mod, newElement_mod));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_replaceable getElement_rep()
  {
    return element_rep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElement_rep(element_replaceable newElement_rep, NotificationChain msgs)
  {
    element_replaceable oldElement_rep = element_rep;
    element_rep = newElement_rep;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP, oldElement_rep, newElement_rep);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElement_rep(element_replaceable newElement_rep)
  {
    if (newElement_rep != element_rep)
    {
      NotificationChain msgs = null;
      if (element_rep != null)
        msgs = ((InternalEObject)element_rep).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP, null, msgs);
      if (newElement_rep != null)
        msgs = ((InternalEObject)newElement_rep).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP, null, msgs);
      msgs = basicSetElement_rep(newElement_rep, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP, newElement_rep, newElement_rep));
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
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD:
        return basicSetElement_mod(null, msgs);
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP:
        return basicSetElement_rep(null, msgs);
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
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD:
        return getElement_mod();
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP:
        return getElement_rep();
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
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD:
        setElement_mod((element_modification)newValue);
        return;
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP:
        setElement_rep((element_replaceable)newValue);
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
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD:
        setElement_mod((element_modification)null);
        return;
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP:
        setElement_rep((element_replaceable)null);
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
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD:
        return element_mod != null;
      case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP:
        return element_rep != null;
    }
    return super.eIsSet(featureID);
  }

} //element_modification_or_replaceableImpl
