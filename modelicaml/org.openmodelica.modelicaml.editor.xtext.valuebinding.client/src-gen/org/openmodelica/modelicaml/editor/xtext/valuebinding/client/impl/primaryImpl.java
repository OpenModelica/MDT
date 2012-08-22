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
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class primaryImpl extends org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl implements primary
{
  /**
   * The cached value of the '{@link #getName_Function() <em>Name Function</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName_Function()
   * @generated
   * @ordered
   */
  protected name_Function name_Function;

  /**
   * The cached value of the '{@link #getInitial_ref() <em>Initial ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitial_ref()
   * @generated
   * @ordered
   */
  protected initial_ref initial_ref;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected ExprDer expr;

  /**
   * The default value of the '{@link #getBinding() <em>Binding</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinding()
   * @generated
   * @ordered
   */
  protected static final String BINDING_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBinding() <em>Binding</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinding()
   * @generated
   * @ordered
   */
  protected String binding = BINDING_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected primaryImpl()
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
    return ClientPackage.Literals.PRIMARY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public name_Function getName_Function()
  {
    return name_Function;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName_Function(name_Function newName_Function, NotificationChain msgs)
  {
    name_Function oldName_Function = name_Function;
    name_Function = newName_Function;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__NAME_FUNCTION, oldName_Function, newName_Function);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName_Function(name_Function newName_Function)
  {
    if (newName_Function != name_Function)
    {
      NotificationChain msgs = null;
      if (name_Function != null)
        msgs = ((InternalEObject)name_Function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      if (newName_Function != null)
        msgs = ((InternalEObject)newName_Function).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      msgs = basicSetName_Function(newName_Function, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__NAME_FUNCTION, newName_Function, newName_Function));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public initial_ref getInitial_ref()
  {
    return initial_ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInitial_ref(initial_ref newInitial_ref, NotificationChain msgs)
  {
    initial_ref oldInitial_ref = initial_ref;
    initial_ref = newInitial_ref;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__INITIAL_REF, oldInitial_ref, newInitial_ref);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInitial_ref(initial_ref newInitial_ref)
  {
    if (newInitial_ref != initial_ref)
    {
      NotificationChain msgs = null;
      if (initial_ref != null)
        msgs = ((InternalEObject)initial_ref).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__INITIAL_REF, null, msgs);
      if (newInitial_ref != null)
        msgs = ((InternalEObject)newInitial_ref).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__INITIAL_REF, null, msgs);
      msgs = basicSetInitial_ref(newInitial_ref, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__INITIAL_REF, newInitial_ref, newInitial_ref));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExprDer getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(ExprDer newExpr, NotificationChain msgs)
  {
    ExprDer oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(ExprDer newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__EXPR, newExpr, newExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBinding()
  {
    return binding;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBinding(String newBinding)
  {
    String oldBinding = binding;
    binding = newBinding;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__BINDING, oldBinding, binding));
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        return basicSetName_Function(null, msgs);
      case ClientPackage.PRIMARY__INITIAL_REF:
        return basicSetInitial_ref(null, msgs);
      case ClientPackage.PRIMARY__EXPR:
        return basicSetExpr(null, msgs);
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        return getName_Function();
      case ClientPackage.PRIMARY__INITIAL_REF:
        return getInitial_ref();
      case ClientPackage.PRIMARY__EXPR:
        return getExpr();
      case ClientPackage.PRIMARY__BINDING:
        return getBinding();
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)newValue);
        return;
      case ClientPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)newValue);
        return;
      case ClientPackage.PRIMARY__EXPR:
        setExpr((ExprDer)newValue);
        return;
      case ClientPackage.PRIMARY__BINDING:
        setBinding((String)newValue);
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)null);
        return;
      case ClientPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)null);
        return;
      case ClientPackage.PRIMARY__EXPR:
        setExpr((ExprDer)null);
        return;
      case ClientPackage.PRIMARY__BINDING:
        setBinding(BINDING_EDEFAULT);
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        return name_Function != null;
      case ClientPackage.PRIMARY__INITIAL_REF:
        return initial_ref != null;
      case ClientPackage.PRIMARY__EXPR:
        return expr != null;
      case ClientPackage.PRIMARY__BINDING:
        return BINDING_EDEFAULT == null ? binding != null : !BINDING_EDEFAULT.equals(binding);
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
    result.append(" (binding: ");
    result.append(binding);
    result.append(')');
    return result.toString();
  }

} //primaryImpl
