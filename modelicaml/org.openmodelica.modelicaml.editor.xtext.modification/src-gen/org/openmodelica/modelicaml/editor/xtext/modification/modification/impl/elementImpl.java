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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl#getClass_Def <em>Class Def</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl#getComp_Clause <em>Comp Clause</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl#getConstrain_Clause <em>Constrain Clause</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.elementImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class elementImpl extends MinimalEObjectImpl.Container implements element
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
   * The cached value of the '{@link #getComp_Clause() <em>Comp Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp_Clause()
   * @generated
   * @ordered
   */
  protected component_clause comp_Clause;

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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected name name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected elementImpl()
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
    return ModificationPackage.Literals.ELEMENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__CLASS_DEF, oldClass_Def, newClass_Def);
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
        msgs = ((InternalEObject)class_Def).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__CLASS_DEF, null, msgs);
      if (newClass_Def != null)
        msgs = ((InternalEObject)newClass_Def).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__CLASS_DEF, null, msgs);
      msgs = basicSetClass_Def(newClass_Def, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__CLASS_DEF, newClass_Def, newClass_Def));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_clause getComp_Clause()
  {
    return comp_Clause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComp_Clause(component_clause newComp_Clause, NotificationChain msgs)
  {
    component_clause oldComp_Clause = comp_Clause;
    comp_Clause = newComp_Clause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__COMP_CLAUSE, oldComp_Clause, newComp_Clause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComp_Clause(component_clause newComp_Clause)
  {
    if (newComp_Clause != comp_Clause)
    {
      NotificationChain msgs = null;
      if (comp_Clause != null)
        msgs = ((InternalEObject)comp_Clause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__COMP_CLAUSE, null, msgs);
      if (newComp_Clause != null)
        msgs = ((InternalEObject)newComp_Clause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__COMP_CLAUSE, null, msgs);
      msgs = basicSetComp_Clause(newComp_Clause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__COMP_CLAUSE, newComp_Clause, newComp_Clause));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE, oldConstrain_Clause, newConstrain_Clause);
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
        msgs = ((InternalEObject)constrain_Clause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE, null, msgs);
      if (newConstrain_Clause != null)
        msgs = ((InternalEObject)newConstrain_Clause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE, null, msgs);
      msgs = basicSetConstrain_Clause(newConstrain_Clause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE, newConstrain_Clause, newConstrain_Clause));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(name newName, NotificationChain msgs)
  {
    name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT__NAME, newName, newName));
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
      case ModificationPackage.ELEMENT__CLASS_DEF:
        return basicSetClass_Def(null, msgs);
      case ModificationPackage.ELEMENT__COMP_CLAUSE:
        return basicSetComp_Clause(null, msgs);
      case ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE:
        return basicSetConstrain_Clause(null, msgs);
      case ModificationPackage.ELEMENT__NAME:
        return basicSetName(null, msgs);
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
      case ModificationPackage.ELEMENT__CLASS_DEF:
        return getClass_Def();
      case ModificationPackage.ELEMENT__COMP_CLAUSE:
        return getComp_Clause();
      case ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE:
        return getConstrain_Clause();
      case ModificationPackage.ELEMENT__NAME:
        return getName();
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
      case ModificationPackage.ELEMENT__CLASS_DEF:
        setClass_Def((class_definition)newValue);
        return;
      case ModificationPackage.ELEMENT__COMP_CLAUSE:
        setComp_Clause((component_clause)newValue);
        return;
      case ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE:
        setConstrain_Clause((constraining_clause)newValue);
        return;
      case ModificationPackage.ELEMENT__NAME:
        setName((name)newValue);
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
      case ModificationPackage.ELEMENT__CLASS_DEF:
        setClass_Def((class_definition)null);
        return;
      case ModificationPackage.ELEMENT__COMP_CLAUSE:
        setComp_Clause((component_clause)null);
        return;
      case ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE:
        setConstrain_Clause((constraining_clause)null);
        return;
      case ModificationPackage.ELEMENT__NAME:
        setName((name)null);
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
      case ModificationPackage.ELEMENT__CLASS_DEF:
        return class_Def != null;
      case ModificationPackage.ELEMENT__COMP_CLAUSE:
        return comp_Clause != null;
      case ModificationPackage.ELEMENT__CONSTRAIN_CLAUSE:
        return constrain_Clause != null;
      case ModificationPackage.ELEMENT__NAME:
        return name != null;
    }
    return super.eIsSet(featureID);
  }

} //elementImpl
