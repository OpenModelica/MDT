/**
 * <copyright>
 * </copyright>
 *

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
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>output expression list</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.output_expression_listImpl#getEpr <em>Epr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.output_expression_listImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class output_expression_listImpl extends MinimalEObjectImpl.Container implements output_expression_list
{
  /**
   * The cached value of the '{@link #getEpr() <em>Epr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEpr()
   * @generated
   * @ordered
   */
  protected expression epr;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected EList<expression> expr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected output_expression_listImpl()
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
    return ModeleditorPackage.Literals.OUTPUT_EXPRESSION_LIST;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getEpr()
  {
    return epr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEpr(expression newEpr, NotificationChain msgs)
  {
    expression oldEpr = epr;
    epr = newEpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR, oldEpr, newEpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEpr(expression newEpr)
  {
    if (newEpr != epr)
    {
      NotificationChain msgs = null;
      if (epr != null)
        msgs = ((InternalEObject)epr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR, null, msgs);
      if (newEpr != null)
        msgs = ((InternalEObject)newEpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR, null, msgs);
      msgs = basicSetEpr(newEpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR, newEpr, newEpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression> getExpr()
  {
    if (expr == null)
    {
      expr = new EObjectContainmentEList<expression>(expression.class, this, ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EXPR);
    }
    return expr;
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
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR:
        return basicSetEpr(null, msgs);
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EXPR:
        return ((InternalEList<?>)getExpr()).basicRemove(otherEnd, msgs);
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
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR:
        return getEpr();
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EXPR:
        return getExpr();
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
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR:
        setEpr((expression)newValue);
        return;
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EXPR:
        getExpr().clear();
        getExpr().addAll((Collection<? extends expression>)newValue);
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
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR:
        setEpr((expression)null);
        return;
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EXPR:
        getExpr().clear();
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
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EPR:
        return epr != null;
      case ModeleditorPackage.OUTPUT_EXPRESSION_LIST__EXPR:
        return expr != null && !expr.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //output_expression_listImpl
