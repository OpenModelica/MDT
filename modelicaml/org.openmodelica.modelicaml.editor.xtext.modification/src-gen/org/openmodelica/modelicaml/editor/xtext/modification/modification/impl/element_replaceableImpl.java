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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>element replaceable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_replaceableImpl#getClass_Def <em>Class Def</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_replaceableImpl#getComp_clause2 <em>Comp clause2</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_replaceableImpl#getConstrain_Clause <em>Constrain Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class element_replaceableImpl extends MinimalEObjectImpl.Container implements element_replaceable
{
  /**
   * The cached value of the '{@link #getClass_Def() <em>Class Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClass_Def()
   * @generated
   * @ordered
   */
  protected class_definition class_Def;

  /**
   * The cached value of the '{@link #getComp_clause2() <em>Comp clause2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp_clause2()
   * @generated
   * @ordered
   */
  protected component_clause1 comp_clause2;

  /**
   * The cached value of the '{@link #getConstrain_Clause() <em>Constrain Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstrain_Clause()
   * @generated
   * @ordered
   */
  protected constraining_clause constrain_Clause;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected element_replaceableImpl()
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
    return ModificationPackage.Literals.ELEMENT_REPLACEABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class_definition getClass_Def()
  {
    return class_Def;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetClass_Def(class_definition newClass_Def, NotificationChain msgs)
  {
    class_definition oldClass_Def = class_Def;
    class_Def = newClass_Def;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF, oldClass_Def, newClass_Def);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClass_Def(class_definition newClass_Def)
  {
    if (newClass_Def != class_Def)
    {
      NotificationChain msgs = null;
      if (class_Def != null)
        msgs = ((InternalEObject)class_Def).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF, null, msgs);
      if (newClass_Def != null)
        msgs = ((InternalEObject)newClass_Def).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF, null, msgs);
      msgs = basicSetClass_Def(newClass_Def, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF, newClass_Def, newClass_Def));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_clause1 getComp_clause2()
  {
    return comp_clause2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComp_clause2(component_clause1 newComp_clause2, NotificationChain msgs)
  {
    component_clause1 oldComp_clause2 = comp_clause2;
    comp_clause2 = newComp_clause2;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2, oldComp_clause2, newComp_clause2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComp_clause2(component_clause1 newComp_clause2)
  {
    if (newComp_clause2 != comp_clause2)
    {
      NotificationChain msgs = null;
      if (comp_clause2 != null)
        msgs = ((InternalEObject)comp_clause2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2, null, msgs);
      if (newComp_clause2 != null)
        msgs = ((InternalEObject)newComp_clause2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2, null, msgs);
      msgs = basicSetComp_clause2(newComp_clause2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2, newComp_clause2, newComp_clause2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public constraining_clause getConstrain_Clause()
  {
    return constrain_Clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstrain_Clause(constraining_clause newConstrain_Clause, NotificationChain msgs)
  {
    constraining_clause oldConstrain_Clause = constrain_Clause;
    constrain_Clause = newConstrain_Clause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE, oldConstrain_Clause, newConstrain_Clause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstrain_Clause(constraining_clause newConstrain_Clause)
  {
    if (newConstrain_Clause != constrain_Clause)
    {
      NotificationChain msgs = null;
      if (constrain_Clause != null)
        msgs = ((InternalEObject)constrain_Clause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE, null, msgs);
      if (newConstrain_Clause != null)
        msgs = ((InternalEObject)newConstrain_Clause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE, null, msgs);
      msgs = basicSetConstrain_Clause(newConstrain_Clause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE, newConstrain_Clause, newConstrain_Clause));
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
      case ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF:
        return basicSetClass_Def(null, msgs);
      case ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2:
        return basicSetComp_clause2(null, msgs);
      case ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE:
        return basicSetConstrain_Clause(null, msgs);
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
      case ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF:
        return getClass_Def();
      case ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2:
        return getComp_clause2();
      case ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE:
        return getConstrain_Clause();
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
      case ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF:
        setClass_Def((class_definition)newValue);
        return;
      case ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2:
        setComp_clause2((component_clause1)newValue);
        return;
      case ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE:
        setConstrain_Clause((constraining_clause)newValue);
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
      case ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF:
        setClass_Def((class_definition)null);
        return;
      case ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2:
        setComp_clause2((component_clause1)null);
        return;
      case ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE:
        setConstrain_Clause((constraining_clause)null);
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
      case ModificationPackage.ELEMENT_REPLACEABLE__CLASS_DEF:
        return class_Def != null;
      case ModificationPackage.ELEMENT_REPLACEABLE__COMP_CLAUSE2:
        return comp_clause2 != null;
      case ModificationPackage.ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE:
        return constrain_Clause != null;
    }
    return super.eIsSet(featureID);
  }

} //element_replaceableImpl
