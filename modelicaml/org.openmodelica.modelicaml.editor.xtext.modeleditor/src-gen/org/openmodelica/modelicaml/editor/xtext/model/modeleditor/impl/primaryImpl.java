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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>primary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getPrimary <em>Primary</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getNum <em>Num</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getInt <em>Int</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getStr <em>Str</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getBool <em>Bool</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getComponent_reference <em>Component reference</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getOutput_expr_list <em>Output expr list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getExpre_list <em>Expre list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getExpression_list <em>Expression list</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getF_arguments <em>Farguments</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.primaryImpl#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class primaryImpl extends factorImpl implements primary
{
  /**
   * The cached value of the '{@link #getPrimary() <em>Primary</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimary()
   * @generated
   * @ordered
   */
  protected primary primary;

  /**
   * The default value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected static final String NUM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNum() <em>Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNum()
   * @generated
   * @ordered
   */
  protected String num = NUM_EDEFAULT;

  /**
   * The default value of the '{@link #getInt() <em>Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInt()
   * @generated
   * @ordered
   */
  protected static final int INT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getInt() <em>Int</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInt()
   * @generated
   * @ordered
   */
  protected int int_ = INT_EDEFAULT;

  /**
   * The default value of the '{@link #getStr() <em>Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStr()
   * @generated
   * @ordered
   */
  protected static final String STR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStr() <em>Str</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStr()
   * @generated
   * @ordered
   */
  protected String str = STR_EDEFAULT;

  /**
   * The default value of the '{@link #getBool() <em>Bool</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBool()
   * @generated
   * @ordered
   */
  protected static final String BOOL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBool() <em>Bool</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBool()
   * @generated
   * @ordered
   */
  protected String bool = BOOL_EDEFAULT;

  /**
   * The cached value of the '{@link #getComponent_reference() <em>Component reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponent_reference()
   * @generated
   * @ordered
   */
  protected component_reference component_reference;

  /**
   * The cached value of the '{@link #getOutput_expr_list() <em>Output expr list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutput_expr_list()
   * @generated
   * @ordered
   */
  protected output_expression_list output_expr_list;

  /**
   * The cached value of the '{@link #getExpre_list() <em>Expre list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpre_list()
   * @generated
   * @ordered
   */
  protected expression_list expre_list;

  /**
   * The cached value of the '{@link #getExpression_list() <em>Expression list</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression_list()
   * @generated
   * @ordered
   */
  protected EList<expression_list> expression_list;

  /**
   * The cached value of the '{@link #getF_arguments() <em>Farguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getF_arguments()
   * @generated
   * @ordered
   */
  protected function_arguments f_arguments;

  /**
   * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected static final String END_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected String end = END_EDEFAULT;

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
    return ModeleditorPackage.Literals.PRIMARY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public primary getPrimary()
  {
    return primary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPrimary(primary newPrimary, NotificationChain msgs)
  {
    primary oldPrimary = primary;
    primary = newPrimary;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__PRIMARY, oldPrimary, newPrimary);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrimary(primary newPrimary)
  {
    if (newPrimary != primary)
    {
      NotificationChain msgs = null;
      if (primary != null)
        msgs = ((InternalEObject)primary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__PRIMARY, null, msgs);
      if (newPrimary != null)
        msgs = ((InternalEObject)newPrimary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__PRIMARY, null, msgs);
      msgs = basicSetPrimary(newPrimary, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__PRIMARY, newPrimary, newPrimary));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNum()
  {
    return num;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNum(String newNum)
  {
    String oldNum = num;
    num = newNum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__NUM, oldNum, num));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getInt()
  {
    return int_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInt(int newInt)
  {
    int oldInt = int_;
    int_ = newInt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__INT, oldInt, int_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStr()
  {
    return str;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStr(String newStr)
  {
    String oldStr = str;
    str = newStr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__STR, oldStr, str));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBool()
  {
    return bool;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBool(String newBool)
  {
    String oldBool = bool;
    bool = newBool;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__BOOL, oldBool, bool));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public component_reference getComponent_reference()
  {
    return component_reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComponent_reference(component_reference newComponent_reference, NotificationChain msgs)
  {
    component_reference oldComponent_reference = component_reference;
    component_reference = newComponent_reference;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE, oldComponent_reference, newComponent_reference);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponent_reference(component_reference newComponent_reference)
  {
    if (newComponent_reference != component_reference)
    {
      NotificationChain msgs = null;
      if (component_reference != null)
        msgs = ((InternalEObject)component_reference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE, null, msgs);
      if (newComponent_reference != null)
        msgs = ((InternalEObject)newComponent_reference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE, null, msgs);
      msgs = basicSetComponent_reference(newComponent_reference, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE, newComponent_reference, newComponent_reference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public output_expression_list getOutput_expr_list()
  {
    return output_expr_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOutput_expr_list(output_expression_list newOutput_expr_list, NotificationChain msgs)
  {
    output_expression_list oldOutput_expr_list = output_expr_list;
    output_expr_list = newOutput_expr_list;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST, oldOutput_expr_list, newOutput_expr_list);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutput_expr_list(output_expression_list newOutput_expr_list)
  {
    if (newOutput_expr_list != output_expr_list)
    {
      NotificationChain msgs = null;
      if (output_expr_list != null)
        msgs = ((InternalEObject)output_expr_list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST, null, msgs);
      if (newOutput_expr_list != null)
        msgs = ((InternalEObject)newOutput_expr_list).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST, null, msgs);
      msgs = basicSetOutput_expr_list(newOutput_expr_list, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST, newOutput_expr_list, newOutput_expr_list));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression_list getExpre_list()
  {
    return expre_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpre_list(expression_list newExpre_list, NotificationChain msgs)
  {
    expression_list oldExpre_list = expre_list;
    expre_list = newExpre_list;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__EXPRE_LIST, oldExpre_list, newExpre_list);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpre_list(expression_list newExpre_list)
  {
    if (newExpre_list != expre_list)
    {
      NotificationChain msgs = null;
      if (expre_list != null)
        msgs = ((InternalEObject)expre_list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__EXPRE_LIST, null, msgs);
      if (newExpre_list != null)
        msgs = ((InternalEObject)newExpre_list).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__EXPRE_LIST, null, msgs);
      msgs = basicSetExpre_list(newExpre_list, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__EXPRE_LIST, newExpre_list, newExpre_list));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<expression_list> getExpression_list()
  {
    if (expression_list == null)
    {
      expression_list = new EObjectContainmentEList<expression_list>(expression_list.class, this, ModeleditorPackage.PRIMARY__EXPRESSION_LIST);
    }
    return expression_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_arguments getF_arguments()
  {
    return f_arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetF_arguments(function_arguments newF_arguments, NotificationChain msgs)
  {
    function_arguments oldF_arguments = f_arguments;
    f_arguments = newF_arguments;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__FARGUMENTS, oldF_arguments, newF_arguments);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setF_arguments(function_arguments newF_arguments)
  {
    if (newF_arguments != f_arguments)
    {
      NotificationChain msgs = null;
      if (f_arguments != null)
        msgs = ((InternalEObject)f_arguments).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__FARGUMENTS, null, msgs);
      if (newF_arguments != null)
        msgs = ((InternalEObject)newF_arguments).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.PRIMARY__FARGUMENTS, null, msgs);
      msgs = basicSetF_arguments(newF_arguments, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__FARGUMENTS, newF_arguments, newF_arguments));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(String newEnd)
  {
    String oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.PRIMARY__END, oldEnd, end));
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
      case ModeleditorPackage.PRIMARY__PRIMARY:
        return basicSetPrimary(null, msgs);
      case ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE:
        return basicSetComponent_reference(null, msgs);
      case ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST:
        return basicSetOutput_expr_list(null, msgs);
      case ModeleditorPackage.PRIMARY__EXPRE_LIST:
        return basicSetExpre_list(null, msgs);
      case ModeleditorPackage.PRIMARY__EXPRESSION_LIST:
        return ((InternalEList<?>)getExpression_list()).basicRemove(otherEnd, msgs);
      case ModeleditorPackage.PRIMARY__FARGUMENTS:
        return basicSetF_arguments(null, msgs);
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
      case ModeleditorPackage.PRIMARY__PRIMARY:
        return getPrimary();
      case ModeleditorPackage.PRIMARY__NUM:
        return getNum();
      case ModeleditorPackage.PRIMARY__INT:
        return getInt();
      case ModeleditorPackage.PRIMARY__STR:
        return getStr();
      case ModeleditorPackage.PRIMARY__BOOL:
        return getBool();
      case ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE:
        return getComponent_reference();
      case ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST:
        return getOutput_expr_list();
      case ModeleditorPackage.PRIMARY__EXPRE_LIST:
        return getExpre_list();
      case ModeleditorPackage.PRIMARY__EXPRESSION_LIST:
        return getExpression_list();
      case ModeleditorPackage.PRIMARY__FARGUMENTS:
        return getF_arguments();
      case ModeleditorPackage.PRIMARY__END:
        return getEnd();
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
      case ModeleditorPackage.PRIMARY__PRIMARY:
        setPrimary((primary)newValue);
        return;
      case ModeleditorPackage.PRIMARY__NUM:
        setNum((String)newValue);
        return;
      case ModeleditorPackage.PRIMARY__INT:
        setInt((Integer)newValue);
        return;
      case ModeleditorPackage.PRIMARY__STR:
        setStr((String)newValue);
        return;
      case ModeleditorPackage.PRIMARY__BOOL:
        setBool((String)newValue);
        return;
      case ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE:
        setComponent_reference((component_reference)newValue);
        return;
      case ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST:
        setOutput_expr_list((output_expression_list)newValue);
        return;
      case ModeleditorPackage.PRIMARY__EXPRE_LIST:
        setExpre_list((expression_list)newValue);
        return;
      case ModeleditorPackage.PRIMARY__EXPRESSION_LIST:
        getExpression_list().clear();
        getExpression_list().addAll((Collection<? extends expression_list>)newValue);
        return;
      case ModeleditorPackage.PRIMARY__FARGUMENTS:
        setF_arguments((function_arguments)newValue);
        return;
      case ModeleditorPackage.PRIMARY__END:
        setEnd((String)newValue);
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
      case ModeleditorPackage.PRIMARY__PRIMARY:
        setPrimary((primary)null);
        return;
      case ModeleditorPackage.PRIMARY__NUM:
        setNum(NUM_EDEFAULT);
        return;
      case ModeleditorPackage.PRIMARY__INT:
        setInt(INT_EDEFAULT);
        return;
      case ModeleditorPackage.PRIMARY__STR:
        setStr(STR_EDEFAULT);
        return;
      case ModeleditorPackage.PRIMARY__BOOL:
        setBool(BOOL_EDEFAULT);
        return;
      case ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE:
        setComponent_reference((component_reference)null);
        return;
      case ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST:
        setOutput_expr_list((output_expression_list)null);
        return;
      case ModeleditorPackage.PRIMARY__EXPRE_LIST:
        setExpre_list((expression_list)null);
        return;
      case ModeleditorPackage.PRIMARY__EXPRESSION_LIST:
        getExpression_list().clear();
        return;
      case ModeleditorPackage.PRIMARY__FARGUMENTS:
        setF_arguments((function_arguments)null);
        return;
      case ModeleditorPackage.PRIMARY__END:
        setEnd(END_EDEFAULT);
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
      case ModeleditorPackage.PRIMARY__PRIMARY:
        return primary != null;
      case ModeleditorPackage.PRIMARY__NUM:
        return NUM_EDEFAULT == null ? num != null : !NUM_EDEFAULT.equals(num);
      case ModeleditorPackage.PRIMARY__INT:
        return int_ != INT_EDEFAULT;
      case ModeleditorPackage.PRIMARY__STR:
        return STR_EDEFAULT == null ? str != null : !STR_EDEFAULT.equals(str);
      case ModeleditorPackage.PRIMARY__BOOL:
        return BOOL_EDEFAULT == null ? bool != null : !BOOL_EDEFAULT.equals(bool);
      case ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE:
        return component_reference != null;
      case ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST:
        return output_expr_list != null;
      case ModeleditorPackage.PRIMARY__EXPRE_LIST:
        return expre_list != null;
      case ModeleditorPackage.PRIMARY__EXPRESSION_LIST:
        return expression_list != null && !expression_list.isEmpty();
      case ModeleditorPackage.PRIMARY__FARGUMENTS:
        return f_arguments != null;
      case ModeleditorPackage.PRIMARY__END:
        return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
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
    result.append(" (num: ");
    result.append(num);
    result.append(", int: ");
    result.append(int_);
    result.append(", str: ");
    result.append(str);
    result.append(", Bool: ");
    result.append(bool);
    result.append(", End: ");
    result.append(end);
    result.append(')');
    return result.toString();
  }

} //primaryImpl
