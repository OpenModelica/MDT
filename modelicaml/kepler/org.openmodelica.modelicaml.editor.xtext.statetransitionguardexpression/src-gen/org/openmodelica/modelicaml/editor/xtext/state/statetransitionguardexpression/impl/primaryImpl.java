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
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getMac_A <em>Mac A</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getMac_E <em>Mac E</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getMac_C <em>Mac C</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getMac_S <em>Mac S</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl#getElse <em>Else</em>}</li>
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
   * The cached value of the '{@link #getMac_A() <em>Mac A</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMac_A()
   * @generated
   * @ordered
   */
  protected Macro_After mac_A;

  /**
   * The cached value of the '{@link #getMac_E() <em>Mac E</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMac_E()
   * @generated
   * @ordered
   */
  protected Macro_EVENT mac_E;

  /**
   * The cached value of the '{@link #getMac_C() <em>Mac C</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMac_C()
   * @generated
   * @ordered
   */
  protected Macro_CHANGE mac_C;

  /**
   * The cached value of the '{@link #getMac_S() <em>Mac S</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMac_S()
   * @generated
   * @ordered
   */
  protected Macro_SIGNAL mac_S;

  /**
   * The default value of the '{@link #getElse() <em>Else</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElse()
   * @generated
   * @ordered
   */
  protected static final String ELSE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getElse() <em>Else</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElse()
   * @generated
   * @ordered
   */
  protected String else_ = ELSE_EDEFAULT;

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
    return StatetransitionguardexpressionPackage.Literals.PRIMARY;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION, oldName_Function, newName_Function);
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
        msgs = ((InternalEObject)name_Function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      if (newName_Function != null)
        msgs = ((InternalEObject)newName_Function).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      msgs = basicSetName_Function(newName_Function, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION, newName_Function, newName_Function));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF, oldInitial_ref, newInitial_ref);
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
        msgs = ((InternalEObject)initial_ref).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF, null, msgs);
      if (newInitial_ref != null)
        msgs = ((InternalEObject)newInitial_ref).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF, null, msgs);
      msgs = basicSetInitial_ref(newInitial_ref, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF, newInitial_ref, newInitial_ref));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__EXPR, oldExpr, newExpr);
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
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__EXPR, newExpr, newExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_After getMac_A()
  {
    return mac_A;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMac_A(Macro_After newMac_A, NotificationChain msgs)
  {
    Macro_After oldMac_A = mac_A;
    mac_A = newMac_A;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_A, oldMac_A, newMac_A);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMac_A(Macro_After newMac_A)
  {
    if (newMac_A != mac_A)
    {
      NotificationChain msgs = null;
      if (mac_A != null)
        msgs = ((InternalEObject)mac_A).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_A, null, msgs);
      if (newMac_A != null)
        msgs = ((InternalEObject)newMac_A).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_A, null, msgs);
      msgs = basicSetMac_A(newMac_A, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_A, newMac_A, newMac_A));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_EVENT getMac_E()
  {
    return mac_E;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMac_E(Macro_EVENT newMac_E, NotificationChain msgs)
  {
    Macro_EVENT oldMac_E = mac_E;
    mac_E = newMac_E;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_E, oldMac_E, newMac_E);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMac_E(Macro_EVENT newMac_E)
  {
    if (newMac_E != mac_E)
    {
      NotificationChain msgs = null;
      if (mac_E != null)
        msgs = ((InternalEObject)mac_E).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_E, null, msgs);
      if (newMac_E != null)
        msgs = ((InternalEObject)newMac_E).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_E, null, msgs);
      msgs = basicSetMac_E(newMac_E, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_E, newMac_E, newMac_E));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_CHANGE getMac_C()
  {
    return mac_C;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMac_C(Macro_CHANGE newMac_C, NotificationChain msgs)
  {
    Macro_CHANGE oldMac_C = mac_C;
    mac_C = newMac_C;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_C, oldMac_C, newMac_C);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMac_C(Macro_CHANGE newMac_C)
  {
    if (newMac_C != mac_C)
    {
      NotificationChain msgs = null;
      if (mac_C != null)
        msgs = ((InternalEObject)mac_C).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_C, null, msgs);
      if (newMac_C != null)
        msgs = ((InternalEObject)newMac_C).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_C, null, msgs);
      msgs = basicSetMac_C(newMac_C, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_C, newMac_C, newMac_C));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Macro_SIGNAL getMac_S()
  {
    return mac_S;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMac_S(Macro_SIGNAL newMac_S, NotificationChain msgs)
  {
    Macro_SIGNAL oldMac_S = mac_S;
    mac_S = newMac_S;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_S, oldMac_S, newMac_S);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMac_S(Macro_SIGNAL newMac_S)
  {
    if (newMac_S != mac_S)
    {
      NotificationChain msgs = null;
      if (mac_S != null)
        msgs = ((InternalEObject)mac_S).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_S, null, msgs);
      if (newMac_S != null)
        msgs = ((InternalEObject)newMac_S).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StatetransitionguardexpressionPackage.PRIMARY__MAC_S, null, msgs);
      msgs = basicSetMac_S(newMac_S, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__MAC_S, newMac_S, newMac_S));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getElse()
  {
    return else_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElse(String newElse)
  {
    String oldElse = else_;
    else_ = newElse;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, StatetransitionguardexpressionPackage.PRIMARY__ELSE, oldElse, else_));
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
      case StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION:
        return basicSetName_Function(null, msgs);
      case StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF:
        return basicSetInitial_ref(null, msgs);
      case StatetransitionguardexpressionPackage.PRIMARY__EXPR:
        return basicSetExpr(null, msgs);
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_A:
        return basicSetMac_A(null, msgs);
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_E:
        return basicSetMac_E(null, msgs);
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_C:
        return basicSetMac_C(null, msgs);
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_S:
        return basicSetMac_S(null, msgs);
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
      case StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION:
        return getName_Function();
      case StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF:
        return getInitial_ref();
      case StatetransitionguardexpressionPackage.PRIMARY__EXPR:
        return getExpr();
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_A:
        return getMac_A();
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_E:
        return getMac_E();
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_C:
        return getMac_C();
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_S:
        return getMac_S();
      case StatetransitionguardexpressionPackage.PRIMARY__ELSE:
        return getElse();
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
      case StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)newValue);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)newValue);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__EXPR:
        setExpr((ExprDer)newValue);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_A:
        setMac_A((Macro_After)newValue);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_E:
        setMac_E((Macro_EVENT)newValue);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_C:
        setMac_C((Macro_CHANGE)newValue);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_S:
        setMac_S((Macro_SIGNAL)newValue);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__ELSE:
        setElse((String)newValue);
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
      case StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)null);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)null);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__EXPR:
        setExpr((ExprDer)null);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_A:
        setMac_A((Macro_After)null);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_E:
        setMac_E((Macro_EVENT)null);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_C:
        setMac_C((Macro_CHANGE)null);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_S:
        setMac_S((Macro_SIGNAL)null);
        return;
      case StatetransitionguardexpressionPackage.PRIMARY__ELSE:
        setElse(ELSE_EDEFAULT);
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
      case StatetransitionguardexpressionPackage.PRIMARY__NAME_FUNCTION:
        return name_Function != null;
      case StatetransitionguardexpressionPackage.PRIMARY__INITIAL_REF:
        return initial_ref != null;
      case StatetransitionguardexpressionPackage.PRIMARY__EXPR:
        return expr != null;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_A:
        return mac_A != null;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_E:
        return mac_E != null;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_C:
        return mac_C != null;
      case StatetransitionguardexpressionPackage.PRIMARY__MAC_S:
        return mac_S != null;
      case StatetransitionguardexpressionPackage.PRIMARY__ELSE:
        return ELSE_EDEFAULT == null ? else_ != null : !ELSE_EDEFAULT.equals(else_);
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
    result.append(" (Else: ");
    result.append(else_);
    result.append(')');
    return result.toString();
  }

} //primaryImpl
