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
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.term;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>arithmetic expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl#getArithmetic_expression <em>Arithmetic expression</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl#getOpr <em>Opr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl#getTerm <em>Term</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl#getOper1 <em>Oper1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.arithmetic_expressionImpl#getTerm1 <em>Term1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class arithmetic_expressionImpl extends relationImpl implements arithmetic_expression
{
  /**
   * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected static final String OP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected String op = OP_EDEFAULT;

  /**
   * The cached value of the '{@link #getArithmetic_expression() <em>Arithmetic expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArithmetic_expression()
   * @generated
   * @ordered
   */
  protected arithmetic_expression arithmetic_expression;

  /**
   * The default value of the '{@link #getOpr() <em>Opr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpr()
   * @generated
   * @ordered
   */
  protected static final String OPR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOpr() <em>Opr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpr()
   * @generated
   * @ordered
   */
  protected String opr = OPR_EDEFAULT;

  /**
   * The cached value of the '{@link #getTerm() <em>Term</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTerm()
   * @generated
   * @ordered
   */
  protected term term;

  /**
   * The cached value of the '{@link #getOper1() <em>Oper1</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOper1()
   * @generated
   * @ordered
   */
  protected EList<String> oper1;

  /**
   * The cached value of the '{@link #getTerm1() <em>Term1</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTerm1()
   * @generated
   * @ordered
   */
  protected EList<term> term1;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected arithmetic_expressionImpl()
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
    return ModeleditorPackage.Literals.ARITHMETIC_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(String newOp)
  {
    String oldOp = op;
    op = newOp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARITHMETIC_EXPRESSION__OP, oldOp, op));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public arithmetic_expression getArithmetic_expression()
  {
    return arithmetic_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArithmetic_expression(arithmetic_expression newArithmetic_expression, NotificationChain msgs)
  {
    arithmetic_expression oldArithmetic_expression = arithmetic_expression;
    arithmetic_expression = newArithmetic_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION, oldArithmetic_expression, newArithmetic_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArithmetic_expression(arithmetic_expression newArithmetic_expression)
  {
    if (newArithmetic_expression != arithmetic_expression)
    {
      NotificationChain msgs = null;
      if (arithmetic_expression != null)
        msgs = ((InternalEObject)arithmetic_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION, null, msgs);
      if (newArithmetic_expression != null)
        msgs = ((InternalEObject)newArithmetic_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION, null, msgs);
      msgs = basicSetArithmetic_expression(newArithmetic_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION, newArithmetic_expression, newArithmetic_expression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOpr()
  {
    return opr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOpr(String newOpr)
  {
    String oldOpr = opr;
    opr = newOpr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARITHMETIC_EXPRESSION__OPR, oldOpr, opr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public term getTerm()
  {
    return term;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTerm(term newTerm, NotificationChain msgs)
  {
    term oldTerm = term;
    term = newTerm;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM, oldTerm, newTerm);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTerm(term newTerm)
  {
    if (newTerm != term)
    {
      NotificationChain msgs = null;
      if (term != null)
        msgs = ((InternalEObject)term).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM, null, msgs);
      if (newTerm != null)
        msgs = ((InternalEObject)newTerm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM, null, msgs);
      msgs = basicSetTerm(newTerm, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM, newTerm, newTerm));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getOper1()
  {
    if (oper1 == null)
    {
      oper1 = new EDataTypeEList<String>(String.class, this, ModeleditorPackage.ARITHMETIC_EXPRESSION__OPER1);
    }
    return oper1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<term> getTerm1()
  {
    if (term1 == null)
    {
      term1 = new EObjectContainmentEList<term>(term.class, this, ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM1);
    }
    return term1;
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
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION:
        return basicSetArithmetic_expression(null, msgs);
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM:
        return basicSetTerm(null, msgs);
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM1:
        return ((InternalEList<?>)getTerm1()).basicRemove(otherEnd, msgs);
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
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OP:
        return getOp();
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION:
        return getArithmetic_expression();
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPR:
        return getOpr();
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM:
        return getTerm();
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPER1:
        return getOper1();
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM1:
        return getTerm1();
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
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OP:
        setOp((String)newValue);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION:
        setArithmetic_expression((arithmetic_expression)newValue);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPR:
        setOpr((String)newValue);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM:
        setTerm((term)newValue);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPER1:
        getOper1().clear();
        getOper1().addAll((Collection<? extends String>)newValue);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM1:
        getTerm1().clear();
        getTerm1().addAll((Collection<? extends term>)newValue);
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
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OP:
        setOp(OP_EDEFAULT);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION:
        setArithmetic_expression((arithmetic_expression)null);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPR:
        setOpr(OPR_EDEFAULT);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM:
        setTerm((term)null);
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPER1:
        getOper1().clear();
        return;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM1:
        getTerm1().clear();
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
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OP:
        return OP_EDEFAULT == null ? op != null : !OP_EDEFAULT.equals(op);
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__ARITHMETIC_EXPRESSION:
        return arithmetic_expression != null;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPR:
        return OPR_EDEFAULT == null ? opr != null : !OPR_EDEFAULT.equals(opr);
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM:
        return term != null;
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__OPER1:
        return oper1 != null && !oper1.isEmpty();
      case ModeleditorPackage.ARITHMETIC_EXPRESSION__TERM1:
        return term1 != null && !term1.isEmpty();
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
    result.append(" (op: ");
    result.append(op);
    result.append(", opr: ");
    result.append(opr);
    result.append(", Oper1: ");
    result.append(oper1);
    result.append(')');
    return result.toString();
  }

} //arithmetic_expressionImpl
