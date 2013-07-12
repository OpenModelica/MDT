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
package org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.EquationsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.equation;
import org.openmodelica.modelicaml.editor.xtext.equation.equationsection.when_equation;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>when equation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.when_equationImpl#getWhenEpr <em>When Epr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.when_equationImpl#getEqn_Then <em>Eqn Then</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.equation.equationsection.impl.when_equationImpl#getElseWhenEpr <em>Else When Epr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class when_equationImpl extends equationImpl implements when_equation
{
  /**
   * The cached value of the '{@link #getWhenEpr() <em>When Epr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhenEpr()
   * @generated
   * @ordered
   */
  protected expression whenEpr;

  /**
   * The cached value of the '{@link #getEqn_Then() <em>Eqn Then</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEqn_Then()
   * @generated
   * @ordered
   */
  protected EList<equation> eqn_Then;

  /**
   * The cached value of the '{@link #getElseWhenEpr() <em>Else When Epr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseWhenEpr()
   * @generated
   * @ordered
   */
  protected EList<expression> elseWhenEpr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected when_equationImpl()
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
    return EquationsectionPackage.Literals.WHEN_EQUATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getWhenEpr()
  {
    return whenEpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhenEpr(expression newWhenEpr, NotificationChain msgs)
  {
    expression oldWhenEpr = whenEpr;
    whenEpr = newWhenEpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EquationsectionPackage.WHEN_EQUATION__WHEN_EPR, oldWhenEpr, newWhenEpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWhenEpr(expression newWhenEpr)
  {
    if (newWhenEpr != whenEpr)
    {
      NotificationChain msgs = null;
      if (whenEpr != null)
        msgs = ((InternalEObject)whenEpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EquationsectionPackage.WHEN_EQUATION__WHEN_EPR, null, msgs);
      if (newWhenEpr != null)
        msgs = ((InternalEObject)newWhenEpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EquationsectionPackage.WHEN_EQUATION__WHEN_EPR, null, msgs);
      msgs = basicSetWhenEpr(newWhenEpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EquationsectionPackage.WHEN_EQUATION__WHEN_EPR, newWhenEpr, newWhenEpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation> getEqn_Then()
  {
    if (eqn_Then == null)
    {
      eqn_Then = new EObjectContainmentEList<equation>(equation.class, this, EquationsectionPackage.WHEN_EQUATION__EQN_THEN);
    }
    return eqn_Then;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression> getElseWhenEpr()
  {
    if (elseWhenEpr == null)
    {
      elseWhenEpr = new EObjectContainmentEList<expression>(expression.class, this, EquationsectionPackage.WHEN_EQUATION__ELSE_WHEN_EPR);
    }
    return elseWhenEpr;
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
      case EquationsectionPackage.WHEN_EQUATION__WHEN_EPR:
        return basicSetWhenEpr(null, msgs);
      case EquationsectionPackage.WHEN_EQUATION__EQN_THEN:
        return ((InternalEList<?>)getEqn_Then()).basicRemove(otherEnd, msgs);
      case EquationsectionPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
        return ((InternalEList<?>)getElseWhenEpr()).basicRemove(otherEnd, msgs);
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
      case EquationsectionPackage.WHEN_EQUATION__WHEN_EPR:
        return getWhenEpr();
      case EquationsectionPackage.WHEN_EQUATION__EQN_THEN:
        return getEqn_Then();
      case EquationsectionPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
        return getElseWhenEpr();
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
      case EquationsectionPackage.WHEN_EQUATION__WHEN_EPR:
        setWhenEpr((expression)newValue);
        return;
      case EquationsectionPackage.WHEN_EQUATION__EQN_THEN:
        getEqn_Then().clear();
        getEqn_Then().addAll((Collection<? extends equation>)newValue);
        return;
      case EquationsectionPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
        getElseWhenEpr().clear();
        getElseWhenEpr().addAll((Collection<? extends expression>)newValue);
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
      case EquationsectionPackage.WHEN_EQUATION__WHEN_EPR:
        setWhenEpr((expression)null);
        return;
      case EquationsectionPackage.WHEN_EQUATION__EQN_THEN:
        getEqn_Then().clear();
        return;
      case EquationsectionPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
        getElseWhenEpr().clear();
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
      case EquationsectionPackage.WHEN_EQUATION__WHEN_EPR:
        return whenEpr != null;
      case EquationsectionPackage.WHEN_EQUATION__EQN_THEN:
        return eqn_Then != null && !eqn_Then.isEmpty();
      case EquationsectionPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
        return elseWhenEpr != null && !elseWhenEpr.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //when_equationImpl
