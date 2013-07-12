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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>modification alternatives</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.modification_alternativesImpl#getModification_Rule <em>Modification Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class modification_alternativesImpl extends MinimalEObjectImpl.Container implements modification_alternatives
{
  /**
   * The cached value of the '{@link #getModification_Rule() <em>Modification Rule</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModification_Rule()
   * @generated
   * @ordered
   */
  protected modification modification_Rule;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected modification_alternativesImpl()
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
    return ModificationPackage.Literals.MODIFICATION_ALTERNATIVES;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public modification getModification_Rule()
  {
    return modification_Rule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModification_Rule(modification newModification_Rule, NotificationChain msgs)
  {
    modification oldModification_Rule = modification_Rule;
    modification_Rule = newModification_Rule;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE, oldModification_Rule, newModification_Rule);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModification_Rule(modification newModification_Rule)
  {
    if (newModification_Rule != modification_Rule)
    {
      NotificationChain msgs = null;
      if (modification_Rule != null)
        msgs = ((InternalEObject)modification_Rule).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE, null, msgs);
      if (newModification_Rule != null)
        msgs = ((InternalEObject)newModification_Rule).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE, null, msgs);
      msgs = basicSetModification_Rule(newModification_Rule, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE, newModification_Rule, newModification_Rule));
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
      case ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE:
        return basicSetModification_Rule(null, msgs);
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
      case ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE:
        return getModification_Rule();
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
      case ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE:
        setModification_Rule((modification)newValue);
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
      case ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE:
        setModification_Rule((modification)null);
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
      case ModificationPackage.MODIFICATION_ALTERNATIVES__MODIFICATION_RULE:
        return modification_Rule != null;
    }
    return super.eIsSet(featureID);
  }

} //modification_alternativesImpl
