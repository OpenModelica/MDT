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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration1;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>type specifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl#getArray_Subs <em>Array Subs</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl#getCom_List <em>Com List</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl#getCom_Dec <em>Com Dec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.type_specifierImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class type_specifierImpl extends component_clauseImpl implements type_specifier
{
  /**
   * The cached value of the '{@link #getArray_Subs() <em>Array Subs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArray_Subs()
   * @generated
   * @ordered
   */
  protected array_subscripts array_Subs;

  /**
   * The cached value of the '{@link #getCom_List() <em>Com List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCom_List()
   * @generated
   * @ordered
   */
  protected component_list com_List;

  /**
   * The cached value of the '{@link #getCom_Dec() <em>Com Dec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCom_Dec()
   * @generated
   * @ordered
   */
  protected component_declaration1 com_Dec;

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
  protected type_specifierImpl()
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
    return ModificationPackage.Literals.TYPE_SPECIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public array_subscripts getArray_Subs()
  {
    return array_Subs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArray_Subs(array_subscripts newArray_Subs, NotificationChain msgs)
  {
    array_subscripts oldArray_Subs = array_Subs;
    array_Subs = newArray_Subs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS, oldArray_Subs, newArray_Subs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArray_Subs(array_subscripts newArray_Subs)
  {
    if (newArray_Subs != array_Subs)
    {
      NotificationChain msgs = null;
      if (array_Subs != null)
        msgs = ((InternalEObject)array_Subs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS, null, msgs);
      if (newArray_Subs != null)
        msgs = ((InternalEObject)newArray_Subs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS, null, msgs);
      msgs = basicSetArray_Subs(newArray_Subs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS, newArray_Subs, newArray_Subs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_list getCom_List()
  {
    return com_List;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCom_List(component_list newCom_List, NotificationChain msgs)
  {
    component_list oldCom_List = com_List;
    com_List = newCom_List;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__COM_LIST, oldCom_List, newCom_List);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCom_List(component_list newCom_List)
  {
    if (newCom_List != com_List)
    {
      NotificationChain msgs = null;
      if (com_List != null)
        msgs = ((InternalEObject)com_List).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__COM_LIST, null, msgs);
      if (newCom_List != null)
        msgs = ((InternalEObject)newCom_List).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__COM_LIST, null, msgs);
      msgs = basicSetCom_List(newCom_List, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__COM_LIST, newCom_List, newCom_List));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_declaration1 getCom_Dec()
  {
    return com_Dec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCom_Dec(component_declaration1 newCom_Dec, NotificationChain msgs)
  {
    component_declaration1 oldCom_Dec = com_Dec;
    com_Dec = newCom_Dec;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__COM_DEC, oldCom_Dec, newCom_Dec);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCom_Dec(component_declaration1 newCom_Dec)
  {
    if (newCom_Dec != com_Dec)
    {
      NotificationChain msgs = null;
      if (com_Dec != null)
        msgs = ((InternalEObject)com_Dec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__COM_DEC, null, msgs);
      if (newCom_Dec != null)
        msgs = ((InternalEObject)newCom_Dec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__COM_DEC, null, msgs);
      msgs = basicSetCom_Dec(newCom_Dec, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__COM_DEC, newCom_Dec, newCom_Dec));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.TYPE_SPECIFIER__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.TYPE_SPECIFIER__NAME, newName, newName));
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
      case ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS:
        return basicSetArray_Subs(null, msgs);
      case ModificationPackage.TYPE_SPECIFIER__COM_LIST:
        return basicSetCom_List(null, msgs);
      case ModificationPackage.TYPE_SPECIFIER__COM_DEC:
        return basicSetCom_Dec(null, msgs);
      case ModificationPackage.TYPE_SPECIFIER__NAME:
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
      case ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS:
        return getArray_Subs();
      case ModificationPackage.TYPE_SPECIFIER__COM_LIST:
        return getCom_List();
      case ModificationPackage.TYPE_SPECIFIER__COM_DEC:
        return getCom_Dec();
      case ModificationPackage.TYPE_SPECIFIER__NAME:
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
      case ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS:
        setArray_Subs((array_subscripts)newValue);
        return;
      case ModificationPackage.TYPE_SPECIFIER__COM_LIST:
        setCom_List((component_list)newValue);
        return;
      case ModificationPackage.TYPE_SPECIFIER__COM_DEC:
        setCom_Dec((component_declaration1)newValue);
        return;
      case ModificationPackage.TYPE_SPECIFIER__NAME:
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
      case ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS:
        setArray_Subs((array_subscripts)null);
        return;
      case ModificationPackage.TYPE_SPECIFIER__COM_LIST:
        setCom_List((component_list)null);
        return;
      case ModificationPackage.TYPE_SPECIFIER__COM_DEC:
        setCom_Dec((component_declaration1)null);
        return;
      case ModificationPackage.TYPE_SPECIFIER__NAME:
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
      case ModificationPackage.TYPE_SPECIFIER__ARRAY_SUBS:
        return array_Subs != null;
      case ModificationPackage.TYPE_SPECIFIER__COM_LIST:
        return com_List != null;
      case ModificationPackage.TYPE_SPECIFIER__COM_DEC:
        return com_Dec != null;
      case ModificationPackage.TYPE_SPECIFIER__NAME:
        return name != null;
    }
    return super.eIsSet(featureID);
  }

} //type_specifierImpl
