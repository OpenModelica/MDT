/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.initial_ref;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name_Function;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.ClientPackage;
import org.openmodelica.modelicaml.editor.xtext.valuebinding.client.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getName_Function <em>Name Function</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getInitial_ref <em>Initial ref</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getSum <em>Sum</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getProd <em>Prod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getMax <em>Max</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.valuebinding.client.impl.primaryImpl#getResult <em>Result</em>}</li>
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
   * The default value of the '{@link #getResult() <em>Result</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResult()
   * @generated
   * @ordered
   */
  protected static final String RESULT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getResult() <em>Result</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResult()
   * @generated
   * @ordered
   */
  protected String result = RESULT_EDEFAULT;

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
    return ClientPackage.Literals.PRIMARY;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__NAME_FUNCTION, oldName_Function, newName_Function);
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
        msgs = ((InternalEObject)name_Function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      if (newName_Function != null)
        msgs = ((InternalEObject)newName_Function).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__NAME_FUNCTION, null, msgs);
      msgs = basicSetName_Function(newName_Function, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__NAME_FUNCTION, newName_Function, newName_Function));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__INITIAL_REF, oldInitial_ref, newInitial_ref);
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
        msgs = ((InternalEObject)initial_ref).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__INITIAL_REF, null, msgs);
      if (newInitial_ref != null)
        msgs = ((InternalEObject)newInitial_ref).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__INITIAL_REF, null, msgs);
      msgs = basicSetInitial_ref(newInitial_ref, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__INITIAL_REF, newInitial_ref, newInitial_ref));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__EXPR, oldExpr, newExpr);
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
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ClientPackage.PRIMARY__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__EXPR, newExpr, newExpr));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__SUM, oldSum, sum));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__PROD, oldProd, prod));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__MIN, oldMin, min));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__MAX, oldMax, max));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getResult()
  {
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResult(String newResult)
  {
    String oldResult = result;
    result = newResult;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.PRIMARY__RESULT, oldResult, result));
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        return basicSetName_Function(null, msgs);
      case ClientPackage.PRIMARY__INITIAL_REF:
        return basicSetInitial_ref(null, msgs);
      case ClientPackage.PRIMARY__EXPR:
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        return getName_Function();
      case ClientPackage.PRIMARY__INITIAL_REF:
        return getInitial_ref();
      case ClientPackage.PRIMARY__EXPR:
        return getExpr();
      case ClientPackage.PRIMARY__SUM:
        return getSum();
      case ClientPackage.PRIMARY__PROD:
        return getProd();
      case ClientPackage.PRIMARY__MIN:
        return getMin();
      case ClientPackage.PRIMARY__MAX:
        return getMax();
      case ClientPackage.PRIMARY__RESULT:
        return getResult();
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)newValue);
        return;
      case ClientPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)newValue);
        return;
      case ClientPackage.PRIMARY__EXPR:
        setExpr((ExprDer)newValue);
        return;
      case ClientPackage.PRIMARY__SUM:
        setSum((String)newValue);
        return;
      case ClientPackage.PRIMARY__PROD:
        setProd((String)newValue);
        return;
      case ClientPackage.PRIMARY__MIN:
        setMin((String)newValue);
        return;
      case ClientPackage.PRIMARY__MAX:
        setMax((String)newValue);
        return;
      case ClientPackage.PRIMARY__RESULT:
        setResult((String)newValue);
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        setName_Function((name_Function)null);
        return;
      case ClientPackage.PRIMARY__INITIAL_REF:
        setInitial_ref((initial_ref)null);
        return;
      case ClientPackage.PRIMARY__EXPR:
        setExpr((ExprDer)null);
        return;
      case ClientPackage.PRIMARY__SUM:
        setSum(SUM_EDEFAULT);
        return;
      case ClientPackage.PRIMARY__PROD:
        setProd(PROD_EDEFAULT);
        return;
      case ClientPackage.PRIMARY__MIN:
        setMin(MIN_EDEFAULT);
        return;
      case ClientPackage.PRIMARY__MAX:
        setMax(MAX_EDEFAULT);
        return;
      case ClientPackage.PRIMARY__RESULT:
        setResult(RESULT_EDEFAULT);
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
      case ClientPackage.PRIMARY__NAME_FUNCTION:
        return name_Function != null;
      case ClientPackage.PRIMARY__INITIAL_REF:
        return initial_ref != null;
      case ClientPackage.PRIMARY__EXPR:
        return expr != null;
      case ClientPackage.PRIMARY__SUM:
        return SUM_EDEFAULT == null ? sum != null : !SUM_EDEFAULT.equals(sum);
      case ClientPackage.PRIMARY__PROD:
        return PROD_EDEFAULT == null ? prod != null : !PROD_EDEFAULT.equals(prod);
      case ClientPackage.PRIMARY__MIN:
        return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
      case ClientPackage.PRIMARY__MAX:
        return MAX_EDEFAULT == null ? max != null : !MAX_EDEFAULT.equals(max);
      case ClientPackage.PRIMARY__RESULT:
        return RESULT_EDEFAULT == null ? result != null : !RESULT_EDEFAULT.equals(result);
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
    result.append(", result: ");
    result.append(result);
    result.append(')');
    return result.toString();
  }

} //primaryImpl
