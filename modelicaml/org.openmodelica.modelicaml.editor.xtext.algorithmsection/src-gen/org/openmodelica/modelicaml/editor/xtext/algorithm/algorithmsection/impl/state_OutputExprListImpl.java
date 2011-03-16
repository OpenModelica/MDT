/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.AlgorithmsectionPackage;
import org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.state_OutputExprList;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>state Output Expr List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_OutputExprListImpl#getOutput_expression_list <em>Output expression list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_OutputExprListImpl#getComponent_ref <em>Component ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.algorithm.algorithmsection.impl.state_OutputExprListImpl#getFun_call_args <em>Fun call args</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class state_OutputExprListImpl extends MinimalEObjectImpl.Container implements state_OutputExprList
{
  /**
   * The cached value of the '{@link #getOutput_expression_list() <em>Output expression list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutput_expression_list()
   * @generated
   * @ordered
   */
  protected output_expression_list output_expression_list;

  /**
   * The cached value of the '{@link #getComponent_ref() <em>Component ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponent_ref()
   * @generated
   * @ordered
   */
  protected component_reference component_ref;

  /**
   * The cached value of the '{@link #getFun_call_args() <em>Fun call args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFun_call_args()
   * @generated
   * @ordered
   */
  protected function_call_args fun_call_args;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected state_OutputExprListImpl()
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
    return AlgorithmsectionPackage.Literals.STATE_OUTPUT_EXPR_LIST;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public output_expression_list getOutput_expression_list()
  {
    return output_expression_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOutput_expression_list(output_expression_list newOutput_expression_list, NotificationChain msgs)
  {
    output_expression_list oldOutput_expression_list = output_expression_list;
    output_expression_list = newOutput_expression_list;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST, oldOutput_expression_list, newOutput_expression_list);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutput_expression_list(output_expression_list newOutput_expression_list)
  {
    if (newOutput_expression_list != output_expression_list)
    {
      NotificationChain msgs = null;
      if (output_expression_list != null)
        msgs = ((InternalEObject)output_expression_list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST, null, msgs);
      if (newOutput_expression_list != null)
        msgs = ((InternalEObject)newOutput_expression_list).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST, null, msgs);
      msgs = basicSetOutput_expression_list(newOutput_expression_list, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST, newOutput_expression_list, newOutput_expression_list));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference getComponent_ref()
  {
    return component_ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComponent_ref(component_reference newComponent_ref, NotificationChain msgs)
  {
    component_reference oldComponent_ref = component_ref;
    component_ref = newComponent_ref;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF, oldComponent_ref, newComponent_ref);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponent_ref(component_reference newComponent_ref)
  {
    if (newComponent_ref != component_ref)
    {
      NotificationChain msgs = null;
      if (component_ref != null)
        msgs = ((InternalEObject)component_ref).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF, null, msgs);
      if (newComponent_ref != null)
        msgs = ((InternalEObject)newComponent_ref).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF, null, msgs);
      msgs = basicSetComponent_ref(newComponent_ref, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF, newComponent_ref, newComponent_ref));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_call_args getFun_call_args()
  {
    return fun_call_args;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFun_call_args(function_call_args newFun_call_args, NotificationChain msgs)
  {
    function_call_args oldFun_call_args = fun_call_args;
    fun_call_args = newFun_call_args;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS, oldFun_call_args, newFun_call_args);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFun_call_args(function_call_args newFun_call_args)
  {
    if (newFun_call_args != fun_call_args)
    {
      NotificationChain msgs = null;
      if (fun_call_args != null)
        msgs = ((InternalEObject)fun_call_args).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS, null, msgs);
      if (newFun_call_args != null)
        msgs = ((InternalEObject)newFun_call_args).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS, null, msgs);
      msgs = basicSetFun_call_args(newFun_call_args, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS, newFun_call_args, newFun_call_args));
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
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST:
        return basicSetOutput_expression_list(null, msgs);
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF:
        return basicSetComponent_ref(null, msgs);
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS:
        return basicSetFun_call_args(null, msgs);
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
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST:
        return getOutput_expression_list();
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF:
        return getComponent_ref();
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS:
        return getFun_call_args();
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
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST:
        setOutput_expression_list((output_expression_list)newValue);
        return;
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF:
        setComponent_ref((component_reference)newValue);
        return;
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS:
        setFun_call_args((function_call_args)newValue);
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
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST:
        setOutput_expression_list((output_expression_list)null);
        return;
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF:
        setComponent_ref((component_reference)null);
        return;
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS:
        setFun_call_args((function_call_args)null);
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
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST:
        return output_expression_list != null;
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF:
        return component_ref != null;
      case AlgorithmsectionPackage.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS:
        return fun_call_args != null;
    }
    return super.eIsSet(featureID);
  }

} //state_OutputExprListImpl
