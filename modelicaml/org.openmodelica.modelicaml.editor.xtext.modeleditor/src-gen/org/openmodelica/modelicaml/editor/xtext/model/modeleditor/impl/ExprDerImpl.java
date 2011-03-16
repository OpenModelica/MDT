/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expr Der</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.ExprDerImpl#getFunctionArgs <em>Function Args</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExprDerImpl extends primaryImpl implements ExprDer
{
  /**
   * The cached value of the '{@link #getFunctionArgs() <em>Function Args</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionArgs()
   * @generated
   * @ordered
   */
  protected function_call_args functionArgs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExprDerImpl()
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
    return ModeleditorPackage.Literals.EXPR_DER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_call_args getFunctionArgs()
  {
    return functionArgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionArgs(function_call_args newFunctionArgs, NotificationChain msgs)
  {
    function_call_args oldFunctionArgs = functionArgs;
    functionArgs = newFunctionArgs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.EXPR_DER__FUNCTION_ARGS, oldFunctionArgs, newFunctionArgs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionArgs(function_call_args newFunctionArgs)
  {
    if (newFunctionArgs != functionArgs)
    {
      NotificationChain msgs = null;
      if (functionArgs != null)
        msgs = ((InternalEObject)functionArgs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.EXPR_DER__FUNCTION_ARGS, null, msgs);
      if (newFunctionArgs != null)
        msgs = ((InternalEObject)newFunctionArgs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.EXPR_DER__FUNCTION_ARGS, null, msgs);
      msgs = basicSetFunctionArgs(newFunctionArgs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.EXPR_DER__FUNCTION_ARGS, newFunctionArgs, newFunctionArgs));
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
      case ModeleditorPackage.EXPR_DER__FUNCTION_ARGS:
        return basicSetFunctionArgs(null, msgs);
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
      case ModeleditorPackage.EXPR_DER__FUNCTION_ARGS:
        return getFunctionArgs();
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
      case ModeleditorPackage.EXPR_DER__FUNCTION_ARGS:
        setFunctionArgs((function_call_args)newValue);
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
      case ModeleditorPackage.EXPR_DER__FUNCTION_ARGS:
        setFunctionArgs((function_call_args)null);
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
      case ModeleditorPackage.EXPR_DER__FUNCTION_ARGS:
        return functionArgs != null;
    }
    return super.eIsSet(featureID);
  }

} //ExprDerImpl
