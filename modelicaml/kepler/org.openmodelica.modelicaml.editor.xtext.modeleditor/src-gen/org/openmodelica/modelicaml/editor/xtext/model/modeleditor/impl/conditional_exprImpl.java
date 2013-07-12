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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>conditional expr</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl#getIfexpr <em>Ifexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl#getThenexpr <em>Thenexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl#getElseifexpr <em>Elseifexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl#getTrueexpr <em>Trueexpr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.conditional_exprImpl#getFalseexpr <em>Falseexpr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class conditional_exprImpl extends expressionImpl implements conditional_expr
{
  /**
   * The cached value of the '{@link #getIfexpr() <em>Ifexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIfexpr()
   * @generated
   * @ordered
   */
  protected expression ifexpr;

  /**
   * The cached value of the '{@link #getThenexpr() <em>Thenexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThenexpr()
   * @generated
   * @ordered
   */
  protected expression thenexpr;

  /**
   * The cached value of the '{@link #getElseifexpr() <em>Elseifexpr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseifexpr()
   * @generated
   * @ordered
   */
  protected EList<expression> elseifexpr;

  /**
   * The cached value of the '{@link #getTrueexpr() <em>Trueexpr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTrueexpr()
   * @generated
   * @ordered
   */
  protected EList<expression> trueexpr;

  /**
   * The cached value of the '{@link #getFalseexpr() <em>Falseexpr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFalseexpr()
   * @generated
   * @ordered
   */
  protected expression falseexpr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected conditional_exprImpl()
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
    return ModeleditorPackage.Literals.CONDITIONAL_EXPR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getIfexpr()
  {
    return ifexpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIfexpr(expression newIfexpr, NotificationChain msgs)
  {
    expression oldIfexpr = ifexpr;
    ifexpr = newIfexpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR, oldIfexpr, newIfexpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIfexpr(expression newIfexpr)
  {
    if (newIfexpr != ifexpr)
    {
      NotificationChain msgs = null;
      if (ifexpr != null)
        msgs = ((InternalEObject)ifexpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR, null, msgs);
      if (newIfexpr != null)
        msgs = ((InternalEObject)newIfexpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR, null, msgs);
      msgs = basicSetIfexpr(newIfexpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR, newIfexpr, newIfexpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getThenexpr()
  {
    return thenexpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetThenexpr(expression newThenexpr, NotificationChain msgs)
  {
    expression oldThenexpr = thenexpr;
    thenexpr = newThenexpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR, oldThenexpr, newThenexpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setThenexpr(expression newThenexpr)
  {
    if (newThenexpr != thenexpr)
    {
      NotificationChain msgs = null;
      if (thenexpr != null)
        msgs = ((InternalEObject)thenexpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR, null, msgs);
      if (newThenexpr != null)
        msgs = ((InternalEObject)newThenexpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR, null, msgs);
      msgs = basicSetThenexpr(newThenexpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR, newThenexpr, newThenexpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression> getElseifexpr()
  {
    if (elseifexpr == null)
    {
      elseifexpr = new EObjectContainmentEList<expression>(expression.class, this, ModeleditorPackage.CONDITIONAL_EXPR__ELSEIFEXPR);
    }
    return elseifexpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression> getTrueexpr()
  {
    if (trueexpr == null)
    {
      trueexpr = new EObjectContainmentEList<expression>(expression.class, this, ModeleditorPackage.CONDITIONAL_EXPR__TRUEEXPR);
    }
    return trueexpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getFalseexpr()
  {
    return falseexpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFalseexpr(expression newFalseexpr, NotificationChain msgs)
  {
    expression oldFalseexpr = falseexpr;
    falseexpr = newFalseexpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR, oldFalseexpr, newFalseexpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFalseexpr(expression newFalseexpr)
  {
    if (newFalseexpr != falseexpr)
    {
      NotificationChain msgs = null;
      if (falseexpr != null)
        msgs = ((InternalEObject)falseexpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR, null, msgs);
      if (newFalseexpr != null)
        msgs = ((InternalEObject)newFalseexpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR, null, msgs);
      msgs = basicSetFalseexpr(newFalseexpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR, newFalseexpr, newFalseexpr));
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
      case ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR:
        return basicSetIfexpr(null, msgs);
      case ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR:
        return basicSetThenexpr(null, msgs);
      case ModeleditorPackage.CONDITIONAL_EXPR__ELSEIFEXPR:
        return ((InternalEList<?>)getElseifexpr()).basicRemove(otherEnd, msgs);
      case ModeleditorPackage.CONDITIONAL_EXPR__TRUEEXPR:
        return ((InternalEList<?>)getTrueexpr()).basicRemove(otherEnd, msgs);
      case ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR:
        return basicSetFalseexpr(null, msgs);
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
      case ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR:
        return getIfexpr();
      case ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR:
        return getThenexpr();
      case ModeleditorPackage.CONDITIONAL_EXPR__ELSEIFEXPR:
        return getElseifexpr();
      case ModeleditorPackage.CONDITIONAL_EXPR__TRUEEXPR:
        return getTrueexpr();
      case ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR:
        return getFalseexpr();
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
      case ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR:
        setIfexpr((expression)newValue);
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR:
        setThenexpr((expression)newValue);
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__ELSEIFEXPR:
        getElseifexpr().clear();
        getElseifexpr().addAll((Collection<? extends expression>)newValue);
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__TRUEEXPR:
        getTrueexpr().clear();
        getTrueexpr().addAll((Collection<? extends expression>)newValue);
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR:
        setFalseexpr((expression)newValue);
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
      case ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR:
        setIfexpr((expression)null);
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR:
        setThenexpr((expression)null);
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__ELSEIFEXPR:
        getElseifexpr().clear();
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__TRUEEXPR:
        getTrueexpr().clear();
        return;
      case ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR:
        setFalseexpr((expression)null);
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
      case ModeleditorPackage.CONDITIONAL_EXPR__IFEXPR:
        return ifexpr != null;
      case ModeleditorPackage.CONDITIONAL_EXPR__THENEXPR:
        return thenexpr != null;
      case ModeleditorPackage.CONDITIONAL_EXPR__ELSEIFEXPR:
        return elseifexpr != null && !elseifexpr.isEmpty();
      case ModeleditorPackage.CONDITIONAL_EXPR__TRUEEXPR:
        return trueexpr != null && !trueexpr.isEmpty();
      case ModeleditorPackage.CONDITIONAL_EXPR__FALSEEXPR:
        return falseexpr != null;
    }
    return super.eIsSet(featureID);
  }

} //conditional_exprImpl
