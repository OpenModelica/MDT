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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>function call args</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.function_call_argsImpl#getF_arg <em>Farg</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class function_call_argsImpl extends initial_refImpl implements function_call_args
{
  /**
   * The cached value of the '{@link #getF_arg() <em>Farg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getF_arg()
   * @generated
   * @ordered
   */
  protected function_arguments f_arg;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected function_call_argsImpl()
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
    return ModeleditorPackage.Literals.FUNCTION_CALL_ARGS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_arguments getF_arg()
  {
    return f_arg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetF_arg(function_arguments newF_arg, NotificationChain msgs)
  {
    function_arguments oldF_arg = f_arg;
    f_arg = newF_arg;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_CALL_ARGS__FARG, oldF_arg, newF_arg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setF_arg(function_arguments newF_arg)
  {
    if (newF_arg != f_arg)
    {
      NotificationChain msgs = null;
      if (f_arg != null)
        msgs = ((InternalEObject)f_arg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_CALL_ARGS__FARG, null, msgs);
      if (newF_arg != null)
        msgs = ((InternalEObject)newF_arg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.FUNCTION_CALL_ARGS__FARG, null, msgs);
      msgs = basicSetF_arg(newF_arg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.FUNCTION_CALL_ARGS__FARG, newF_arg, newF_arg));
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
      case ModeleditorPackage.FUNCTION_CALL_ARGS__FARG:
        return basicSetF_arg(null, msgs);
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
      case ModeleditorPackage.FUNCTION_CALL_ARGS__FARG:
        return getF_arg();
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
      case ModeleditorPackage.FUNCTION_CALL_ARGS__FARG:
        setF_arg((function_arguments)newValue);
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
      case ModeleditorPackage.FUNCTION_CALL_ARGS__FARG:
        setF_arg((function_arguments)null);
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
      case ModeleditorPackage.FUNCTION_CALL_ARGS__FARG:
        return f_arg != null;
    }
    return super.eIsSet(featureID);
  }

} //function_call_argsImpl
