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
package org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.MediatorPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getSum <em>Sum</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getProd <em>Prod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getMax <em>Max</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getAvg <em>Avg</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getToArray <em>To Array</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getAnd <em>And</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getOr <em>Or</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getXor <em>Xor</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.mediator.impl.primaryImpl#getSingleProvider <em>Single Provider</em>}</li>
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
   * The default value of the '{@link #getSum() <em>Sum</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSum()
   * @generated
   * @ordered
   */
  protected static final String SUM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSum() <em>Sum</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSum()
   * @generated
   * @ordered
   */
  protected String sum = SUM_EDEFAULT;

  /**
   * The default value of the '{@link #getProd() <em>Prod</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProd()
   * @generated
   * @ordered
   */
  protected static final String PROD_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProd() <em>Prod</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProd()
   * @generated
   * @ordered
   */
  protected String prod = PROD_EDEFAULT;

  /**
   * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMin()
   * @generated
   * @ordered
   */
  protected static final String MIN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMin()
   * @generated
   * @ordered
   */
  protected String min = MIN_EDEFAULT;

  /**
   * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMax()
   * @generated
   * @ordered
   */
  protected static final String MAX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMax()
   * @generated
   * @ordered
   */
  protected String max = MAX_EDEFAULT;

  /**
   * The default value of the '{@link #getAvg() <em>Avg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAvg()
   * @generated
   * @ordered
   */
  protected static final String AVG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAvg() <em>Avg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAvg()
   * @generated
   * @ordered
   */
  protected String avg = AVG_EDEFAULT;

  /**
   * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSize()
   * @generated
   * @ordered
   */
  protected static final String SIZE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSize()
   * @generated
   * @ordered
   */
  protected String size = SIZE_EDEFAULT;

  /**
   * The default value of the '{@link #getToArray() <em>To Array</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToArray()
   * @generated
   * @ordered
   */
  protected static final String TO_ARRAY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getToArray() <em>To Array</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToArray()
   * @generated
   * @ordered
   */
  protected String toArray = TO_ARRAY_EDEFAULT;

  /**
   * The default value of the '{@link #getAnd() <em>And</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnd()
   * @generated
   * @ordered
   */
  protected static final String AND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAnd() <em>And</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnd()
   * @generated
   * @ordered
   */
  protected String and = AND_EDEFAULT;

  /**
   * The default value of the '{@link #getOr() <em>Or</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOr()
   * @generated
   * @ordered
   */
  protected static final String OR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOr() <em>Or</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOr()
   * @generated
   * @ordered
   */
  protected String or = OR_EDEFAULT;

  /**
   * The default value of the '{@link #getXor() <em>Xor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXor()
   * @generated
   * @ordered
   */
  protected static final String XOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getXor() <em>Xor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXor()
   * @generated
   * @ordered
   */
  protected String xor = XOR_EDEFAULT;

  /**
   * The default value of the '{@link #getSingleProvider() <em>Single Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSingleProvider()
   * @generated
   * @ordered
   */
  protected static final String SINGLE_PROVIDER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSingleProvider() <em>Single Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSingleProvider()
   * @generated
   * @ordered
   */
  protected String singleProvider = SINGLE_PROVIDER_EDEFAULT;

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
    return MediatorPackage.Literals.PRIMARY;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__NAME_FUNCTION, oldName_Function, newName_Function);
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
        msgs = ((InternalEObject)name_Function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MediatorPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      if (newName_Function != null)
        msgs = ((InternalEObject)newName_Function).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MediatorPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      msgs = basicSetName_Function(newName_Function, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__NAME_FUNCTION, newName_Function, newName_Function));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__INITIAL_REF, oldInitial_ref, newInitial_ref);
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
        msgs = ((InternalEObject)initial_ref).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MediatorPackage.PRIMARY__INITIAL_REF, null, msgs);
      if (newInitial_ref != null)
        msgs = ((InternalEObject)newInitial_ref).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MediatorPackage.PRIMARY__INITIAL_REF, null, msgs);
      msgs = basicSetInitial_ref(newInitial_ref, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__INITIAL_REF, newInitial_ref, newInitial_ref));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__EXPR, oldExpr, newExpr);
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
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MediatorPackage.PRIMARY__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MediatorPackage.PRIMARY__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__EXPR, newExpr, newExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSum()
  {
    return sum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSum(String newSum)
  {
    String oldSum = sum;
    sum = newSum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__SUM, oldSum, sum));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getProd()
  {
    return prod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProd(String newProd)
  {
    String oldProd = prod;
    prod = newProd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__PROD, oldProd, prod));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMin()
  {
    return min;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMin(String newMin)
  {
    String oldMin = min;
    min = newMin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__MIN, oldMin, min));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMax()
  {
    return max;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMax(String newMax)
  {
    String oldMax = max;
    max = newMax;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__MAX, oldMax, max));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAvg()
  {
    return avg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAvg(String newAvg)
  {
    String oldAvg = avg;
    avg = newAvg;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__AVG, oldAvg, avg));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSize()
  {
    return size;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSize(String newSize)
  {
    String oldSize = size;
    size = newSize;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__SIZE, oldSize, size));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getToArray()
  {
    return toArray;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setToArray(String newToArray)
  {
    String oldToArray = toArray;
    toArray = newToArray;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__TO_ARRAY, oldToArray, toArray));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAnd()
  {
    return and;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnd(String newAnd)
  {
    String oldAnd = and;
    and = newAnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__AND, oldAnd, and));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOr()
  {
    return or;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOr(String newOr)
  {
    String oldOr = or;
    or = newOr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__OR, oldOr, or));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getXor()
  {
    return xor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setXor(String newXor)
  {
    String oldXor = xor;
    xor = newXor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__XOR, oldXor, xor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSingleProvider()
  {
    return singleProvider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSingleProvider(String newSingleProvider)
  {
    String oldSingleProvider = singleProvider;
    singleProvider = newSingleProvider;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MediatorPackage.PRIMARY__SINGLE_PROVIDER, oldSingleProvider, singleProvider));
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
      case MediatorPackage.PRIMARY__NAME_FUNCTION:
        return basicSetName_Function(null, msgs);
      case MediatorPackage.PRIMARY__INITIAL_REF:
        return basicSetInitial_ref(null, msgs);
      case MediatorPackage.PRIMARY__EXPR:
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
      case MediatorPackage.PRIMARY__NAME_FUNCTION:
        return getName_Function();
      case MediatorPackage.PRIMARY__INITIAL_REF:
        return getInitial_ref();
      case MediatorPackage.PRIMARY__EXPR:
        return getExpr();
      case MediatorPackage.PRIMARY__SUM:
        return getSum();
      case MediatorPackage.PRIMARY__PROD:
        return getProd();
      case MediatorPackage.PRIMARY__MIN:
        return getMin();
      case MediatorPackage.PRIMARY__MAX:
        return getMax();
      case MediatorPackage.PRIMARY__AVG:
        return getAvg();
      case MediatorPackage.PRIMARY__SIZE:
        return getSize();
      case MediatorPackage.PRIMARY__TO_ARRAY:
        return getToArray();
      case MediatorPackage.PRIMARY__AND:
        return getAnd();
      case MediatorPackage.PRIMARY__OR:
        return getOr();
      case MediatorPackage.PRIMARY__XOR:
        return getXor();
      case MediatorPackage.PRIMARY__SINGLE_PROVIDER:
        return getSingleProvider();
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
      case MediatorPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)newValue);
        return;
      case MediatorPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)newValue);
        return;
      case MediatorPackage.PRIMARY__EXPR:
        setExpr((ExprDer)newValue);
        return;
      case MediatorPackage.PRIMARY__SUM:
        setSum((String)newValue);
        return;
      case MediatorPackage.PRIMARY__PROD:
        setProd((String)newValue);
        return;
      case MediatorPackage.PRIMARY__MIN:
        setMin((String)newValue);
        return;
      case MediatorPackage.PRIMARY__MAX:
        setMax((String)newValue);
        return;
      case MediatorPackage.PRIMARY__AVG:
        setAvg((String)newValue);
        return;
      case MediatorPackage.PRIMARY__SIZE:
        setSize((String)newValue);
        return;
      case MediatorPackage.PRIMARY__TO_ARRAY:
        setToArray((String)newValue);
        return;
      case MediatorPackage.PRIMARY__AND:
        setAnd((String)newValue);
        return;
      case MediatorPackage.PRIMARY__OR:
        setOr((String)newValue);
        return;
      case MediatorPackage.PRIMARY__XOR:
        setXor((String)newValue);
        return;
      case MediatorPackage.PRIMARY__SINGLE_PROVIDER:
        setSingleProvider((String)newValue);
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
      case MediatorPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)null);
        return;
      case MediatorPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)null);
        return;
      case MediatorPackage.PRIMARY__EXPR:
        setExpr((ExprDer)null);
        return;
      case MediatorPackage.PRIMARY__SUM:
        setSum(SUM_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__PROD:
        setProd(PROD_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__MIN:
        setMin(MIN_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__MAX:
        setMax(MAX_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__AVG:
        setAvg(AVG_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__SIZE:
        setSize(SIZE_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__TO_ARRAY:
        setToArray(TO_ARRAY_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__AND:
        setAnd(AND_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__OR:
        setOr(OR_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__XOR:
        setXor(XOR_EDEFAULT);
        return;
      case MediatorPackage.PRIMARY__SINGLE_PROVIDER:
        setSingleProvider(SINGLE_PROVIDER_EDEFAULT);
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
      case MediatorPackage.PRIMARY__NAME_FUNCTION:
        return name_Function != null;
      case MediatorPackage.PRIMARY__INITIAL_REF:
        return initial_ref != null;
      case MediatorPackage.PRIMARY__EXPR:
        return expr != null;
      case MediatorPackage.PRIMARY__SUM:
        return SUM_EDEFAULT == null ? sum != null : !SUM_EDEFAULT.equals(sum);
      case MediatorPackage.PRIMARY__PROD:
        return PROD_EDEFAULT == null ? prod != null : !PROD_EDEFAULT.equals(prod);
      case MediatorPackage.PRIMARY__MIN:
        return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
      case MediatorPackage.PRIMARY__MAX:
        return MAX_EDEFAULT == null ? max != null : !MAX_EDEFAULT.equals(max);
      case MediatorPackage.PRIMARY__AVG:
        return AVG_EDEFAULT == null ? avg != null : !AVG_EDEFAULT.equals(avg);
      case MediatorPackage.PRIMARY__SIZE:
        return SIZE_EDEFAULT == null ? size != null : !SIZE_EDEFAULT.equals(size);
      case MediatorPackage.PRIMARY__TO_ARRAY:
        return TO_ARRAY_EDEFAULT == null ? toArray != null : !TO_ARRAY_EDEFAULT.equals(toArray);
      case MediatorPackage.PRIMARY__AND:
        return AND_EDEFAULT == null ? and != null : !AND_EDEFAULT.equals(and);
      case MediatorPackage.PRIMARY__OR:
        return OR_EDEFAULT == null ? or != null : !OR_EDEFAULT.equals(or);
      case MediatorPackage.PRIMARY__XOR:
        return XOR_EDEFAULT == null ? xor != null : !XOR_EDEFAULT.equals(xor);
      case MediatorPackage.PRIMARY__SINGLE_PROVIDER:
        return SINGLE_PROVIDER_EDEFAULT == null ? singleProvider != null : !SINGLE_PROVIDER_EDEFAULT.equals(singleProvider);
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
    result.append(" (sum: ");
    result.append(sum);
    result.append(", prod: ");
    result.append(prod);
    result.append(", min: ");
    result.append(min);
    result.append(", max: ");
    result.append(max);
    result.append(", avg: ");
    result.append(avg);
    result.append(", size: ");
    result.append(size);
    result.append(", toArray: ");
    result.append(toArray);
    result.append(", and: ");
    result.append(and);
    result.append(", or: ");
    result.append(or);
    result.append(", xor: ");
    result.append(xor);
    result.append(", singleProvider: ");
    result.append(singleProvider);
    result.append(')');
    return result.toString();
  }

} //primaryImpl
