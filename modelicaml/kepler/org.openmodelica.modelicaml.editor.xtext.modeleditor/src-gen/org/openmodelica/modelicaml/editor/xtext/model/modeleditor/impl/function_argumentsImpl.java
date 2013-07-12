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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_exp;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_arguments;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>function arguments</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl#getArgExp <em>Arg Exp</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl#getFun_Arg_Expr <em>Fun Arg Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl#getFun_Arg_For <em>Fun Arg For</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_argumentsImpl#getName_arg <em>Name arg</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class function_argumentsImpl extends MinimalEObjectImpl.Container implements function_arguments
{
  /**
   * The cached value of the '{@link #getArgExp() <em>Arg Exp</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArgExp()
   * @generated
   * @ordered
   */
  protected EList<expression> argExp;

  /**
   * The cached value of the '{@link #getFun_Arg_Expr() <em>Fun Arg Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFun_Arg_Expr()
   * @generated
   * @ordered
   */
  protected Fun_Arguments_exp fun_Arg_Expr;

  /**
   * The cached value of the '{@link #getFun_Arg_For() <em>Fun Arg For</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFun_Arg_For()
   * @generated
   * @ordered
   */
  protected Fun_Arguments_for fun_Arg_For;

  /**
   * The cached value of the '{@link #getName_arg() <em>Name arg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName_arg()
   * @generated
   * @ordered
   */
  protected named_arguments name_arg;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected function_argumentsImpl()
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
    return ModeleditorPackage.Literals.FUNCTION_ARGUMENTS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression> getArgExp()
  {
    if (argExp == null)
    {
      argExp = new EObjectContainmentEList<expression>(expression.class, this, ModeleditorPackage.FUNCTION_ARGUMENTS__ARG_EXP);
    }
    return argExp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Fun_Arguments_exp getFun_Arg_Expr()
  {
    return fun_Arg_Expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFun_Arg_Expr(Fun_Arguments_exp newFun_Arg_Expr, NotificationChain msgs)
  {
    Fun_Arguments_exp oldFun_Arg_Expr = fun_Arg_Expr;
    fun_Arg_Expr = newFun_Arg_Expr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR, oldFun_Arg_Expr, newFun_Arg_Expr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFun_Arg_Expr(Fun_Arguments_exp newFun_Arg_Expr)
  {
    if (newFun_Arg_Expr != fun_Arg_Expr)
    {
      NotificationChain msgs = null;
      if (fun_Arg_Expr != null)
        msgs = ((InternalEObject)fun_Arg_Expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR, null, msgs);
      if (newFun_Arg_Expr != null)
        msgs = ((InternalEObject)newFun_Arg_Expr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR, null, msgs);
      msgs = basicSetFun_Arg_Expr(newFun_Arg_Expr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR, newFun_Arg_Expr, newFun_Arg_Expr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Fun_Arguments_for getFun_Arg_For()
  {
    return fun_Arg_For;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFun_Arg_For(Fun_Arguments_for newFun_Arg_For, NotificationChain msgs)
  {
    Fun_Arguments_for oldFun_Arg_For = fun_Arg_For;
    fun_Arg_For = newFun_Arg_For;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR, oldFun_Arg_For, newFun_Arg_For);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFun_Arg_For(Fun_Arguments_for newFun_Arg_For)
  {
    if (newFun_Arg_For != fun_Arg_For)
    {
      NotificationChain msgs = null;
      if (fun_Arg_For != null)
        msgs = ((InternalEObject)fun_Arg_For).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR, null, msgs);
      if (newFun_Arg_For != null)
        msgs = ((InternalEObject)newFun_Arg_For).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR, null, msgs);
      msgs = basicSetFun_Arg_For(newFun_Arg_For, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR, newFun_Arg_For, newFun_Arg_For));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public named_arguments getName_arg()
  {
    return name_arg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName_arg(named_arguments newName_arg, NotificationChain msgs)
  {
    named_arguments oldName_arg = name_arg;
    name_arg = newName_arg;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG, oldName_arg, newName_arg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName_arg(named_arguments newName_arg)
  {
    if (newName_arg != name_arg)
    {
      NotificationChain msgs = null;
      if (name_arg != null)
        msgs = ((InternalEObject)name_arg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG, null, msgs);
      if (newName_arg != null)
        msgs = ((InternalEObject)newName_arg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG, null, msgs);
      msgs = basicSetName_arg(newName_arg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG, newName_arg, newName_arg));
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
      case ModeleditorPackage.FUNCTION_ARGUMENTS__ARG_EXP:
        return ((InternalEList<?>)getArgExp()).basicRemove(otherEnd, msgs);
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR:
        return basicSetFun_Arg_Expr(null, msgs);
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR:
        return basicSetFun_Arg_For(null, msgs);
      case ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG:
        return basicSetName_arg(null, msgs);
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
      case ModeleditorPackage.FUNCTION_ARGUMENTS__ARG_EXP:
        return getArgExp();
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR:
        return getFun_Arg_Expr();
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR:
        return getFun_Arg_For();
      case ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG:
        return getName_arg();
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
      case ModeleditorPackage.FUNCTION_ARGUMENTS__ARG_EXP:
        getArgExp().clear();
        getArgExp().addAll((Collection<? extends expression>)newValue);
        return;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR:
        setFun_Arg_Expr((Fun_Arguments_exp)newValue);
        return;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR:
        setFun_Arg_For((Fun_Arguments_for)newValue);
        return;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG:
        setName_arg((named_arguments)newValue);
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
      case ModeleditorPackage.FUNCTION_ARGUMENTS__ARG_EXP:
        getArgExp().clear();
        return;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR:
        setFun_Arg_Expr((Fun_Arguments_exp)null);
        return;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR:
        setFun_Arg_For((Fun_Arguments_for)null);
        return;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG:
        setName_arg((named_arguments)null);
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
      case ModeleditorPackage.FUNCTION_ARGUMENTS__ARG_EXP:
        return argExp != null && !argExp.isEmpty();
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_EXPR:
        return fun_Arg_Expr != null;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__FUN_ARG_FOR:
        return fun_Arg_For != null;
      case ModeleditorPackage.FUNCTION_ARGUMENTS__NAME_ARG:
        return name_arg != null;
    }
    return super.eIsSet(featureID);
  }

} //function_argumentsImpl
