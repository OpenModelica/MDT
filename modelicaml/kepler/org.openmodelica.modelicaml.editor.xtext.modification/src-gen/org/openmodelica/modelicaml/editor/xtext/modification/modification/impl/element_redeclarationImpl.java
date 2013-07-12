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
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>element redeclaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_redeclarationImpl#getClass_Def <em>Class Def</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_redeclarationImpl#getComp_clause1 <em>Comp clause1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.element_redeclarationImpl#getElement_R <em>Element R</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class element_redeclarationImpl extends argumentImpl implements element_redeclaration
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
   * The cached value of the '{@link #getComp_clause1() <em>Comp clause1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp_clause1()
   * @generated
   * @ordered
   */
  protected component_clause1 comp_clause1;

  /**
   * The cached value of the '{@link #getElement_R() <em>Element R</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElement_R()
   * @generated
   * @ordered
   */
  protected element_replaceable element_R;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected element_redeclarationImpl()
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
    return ModificationPackage.Literals.ELEMENT_REDECLARATION;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF, oldClass_Def, newClass_Def);
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
        msgs = ((InternalEObject)class_Def).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF, null, msgs);
      if (newClass_Def != null)
        msgs = ((InternalEObject)newClass_Def).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF, null, msgs);
      msgs = basicSetClass_Def(newClass_Def, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF, newClass_Def, newClass_Def));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_clause1 getComp_clause1()
  {
    return comp_clause1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComp_clause1(component_clause1 newComp_clause1, NotificationChain msgs)
  {
    component_clause1 oldComp_clause1 = comp_clause1;
    comp_clause1 = newComp_clause1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1, oldComp_clause1, newComp_clause1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComp_clause1(component_clause1 newComp_clause1)
  {
    if (newComp_clause1 != comp_clause1)
    {
      NotificationChain msgs = null;
      if (comp_clause1 != null)
        msgs = ((InternalEObject)comp_clause1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1, null, msgs);
      if (newComp_clause1 != null)
        msgs = ((InternalEObject)newComp_clause1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1, null, msgs);
      msgs = basicSetComp_clause1(newComp_clause1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1, newComp_clause1, newComp_clause1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_replaceable getElement_R()
  {
    return element_R;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElement_R(element_replaceable newElement_R, NotificationChain msgs)
  {
    element_replaceable oldElement_R = element_R;
    element_R = newElement_R;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R, oldElement_R, newElement_R);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElement_R(element_replaceable newElement_R)
  {
    if (newElement_R != element_R)
    {
      NotificationChain msgs = null;
      if (element_R != null)
        msgs = ((InternalEObject)element_R).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R, null, msgs);
      if (newElement_R != null)
        msgs = ((InternalEObject)newElement_R).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R, null, msgs);
      msgs = basicSetElement_R(newElement_R, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R, newElement_R, newElement_R));
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
      case ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF:
        return basicSetClass_Def(null, msgs);
      case ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1:
        return basicSetComp_clause1(null, msgs);
      case ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R:
        return basicSetElement_R(null, msgs);
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
      case ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF:
        return getClass_Def();
      case ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1:
        return getComp_clause1();
      case ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R:
        return getElement_R();
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
      case ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF:
        setClass_Def((class_definition)newValue);
        return;
      case ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1:
        setComp_clause1((component_clause1)newValue);
        return;
      case ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R:
        setElement_R((element_replaceable)newValue);
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
      case ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF:
        setClass_Def((class_definition)null);
        return;
      case ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1:
        setComp_clause1((component_clause1)null);
        return;
      case ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R:
        setElement_R((element_replaceable)null);
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
      case ModificationPackage.ELEMENT_REDECLARATION__CLASS_DEF:
        return class_Def != null;
      case ModificationPackage.ELEMENT_REDECLARATION__COMP_CLAUSE1:
        return comp_clause1 != null;
      case ModificationPackage.ELEMENT_REDECLARATION__ELEMENT_R:
        return element_R != null;
    }
    return super.eIsSet(featureID);
  }

} //element_redeclarationImpl
