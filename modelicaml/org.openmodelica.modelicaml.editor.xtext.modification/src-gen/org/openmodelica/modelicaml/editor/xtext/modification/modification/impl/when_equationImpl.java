/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
 * Main author: Wladimir Schamai, EADS Innovation Works / Link�ping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>when equation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_equationImpl#getWhenEpr <em>When Epr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_equationImpl#getEqn_Then <em>Eqn Then</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.when_equationImpl#getElseWhenEpr <em>Else When Epr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class when_equationImpl extends MinimalEObjectImpl.Container implements when_equation
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
    return ModificationPackage.Literals.WHEN_EQUATION;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.WHEN_EQUATION__WHEN_EPR, oldWhenEpr, newWhenEpr);
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
        msgs = ((InternalEObject)whenEpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.WHEN_EQUATION__WHEN_EPR, null, msgs);
      if (newWhenEpr != null)
        msgs = ((InternalEObject)newWhenEpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.WHEN_EQUATION__WHEN_EPR, null, msgs);
      msgs = basicSetWhenEpr(newWhenEpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.WHEN_EQUATION__WHEN_EPR, newWhenEpr, newWhenEpr));
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
      eqn_Then = new EObjectContainmentEList<equation>(equation.class, this, ModificationPackage.WHEN_EQUATION__EQN_THEN);
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
      elseWhenEpr = new EObjectContainmentEList<expression>(expression.class, this, ModificationPackage.WHEN_EQUATION__ELSE_WHEN_EPR);
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
      case ModificationPackage.WHEN_EQUATION__WHEN_EPR:
        return basicSetWhenEpr(null, msgs);
      case ModificationPackage.WHEN_EQUATION__EQN_THEN:
        return ((InternalEList<?>)getEqn_Then()).basicRemove(otherEnd, msgs);
      case ModificationPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
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
      case ModificationPackage.WHEN_EQUATION__WHEN_EPR:
        return getWhenEpr();
      case ModificationPackage.WHEN_EQUATION__EQN_THEN:
        return getEqn_Then();
      case ModificationPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
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
      case ModificationPackage.WHEN_EQUATION__WHEN_EPR:
        setWhenEpr((expression)newValue);
        return;
      case ModificationPackage.WHEN_EQUATION__EQN_THEN:
        getEqn_Then().clear();
        getEqn_Then().addAll((Collection<? extends equation>)newValue);
        return;
      case ModificationPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
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
      case ModificationPackage.WHEN_EQUATION__WHEN_EPR:
        setWhenEpr((expression)null);
        return;
      case ModificationPackage.WHEN_EQUATION__EQN_THEN:
        getEqn_Then().clear();
        return;
      case ModificationPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
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
      case ModificationPackage.WHEN_EQUATION__WHEN_EPR:
        return whenEpr != null;
      case ModificationPackage.WHEN_EQUATION__EQN_THEN:
        return eqn_Then != null && !eqn_Then.isEmpty();
      case ModificationPackage.WHEN_EQUATION__ELSE_WHEN_EPR:
        return elseWhenEpr != null && !elseWhenEpr.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //when_equationImpl
