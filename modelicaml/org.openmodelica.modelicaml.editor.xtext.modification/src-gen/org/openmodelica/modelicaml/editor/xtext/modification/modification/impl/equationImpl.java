/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>equation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl#getSim <em>Sim</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl#getIF_EQN <em>IF EQN</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl#getFOR_EQN <em>FOR EQN</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl#getCon <em>Con</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl#getWHEN_EQN <em>WHEN EQN</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.equationImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class equationImpl extends MinimalEObjectImpl.Container implements equation
{
  /**
   * The cached value of the '{@link #getSim() <em>Sim</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSim()
   * @generated
   * @ordered
   */
  protected simple_expression sim;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected expression expr;

  /**
   * The cached value of the '{@link #getIF_EQN() <em>IF EQN</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIF_EQN()
   * @generated
   * @ordered
   */
  protected if_equation iF_EQN;

  /**
   * The cached value of the '{@link #getFOR_EQN() <em>FOR EQN</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFOR_EQN()
   * @generated
   * @ordered
   */
  protected for_equation foR_EQN;

  /**
   * The cached value of the '{@link #getCon() <em>Con</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCon()
   * @generated
   * @ordered
   */
  protected connect_clause con;

  /**
   * The cached value of the '{@link #getWHEN_EQN() <em>WHEN EQN</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWHEN_EQN()
   * @generated
   * @ordered
   */
  protected when_equation wheN_EQN;

  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected String comment = COMMENT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected equationImpl()
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
    return ModificationPackage.Literals.EQUATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simple_expression getSim()
  {
    return sim;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSim(simple_expression newSim, NotificationChain msgs)
  {
    simple_expression oldSim = sim;
    sim = newSim;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__SIM, oldSim, newSim);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSim(simple_expression newSim)
  {
    if (newSim != sim)
    {
      NotificationChain msgs = null;
      if (sim != null)
        msgs = ((InternalEObject)sim).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__SIM, null, msgs);
      if (newSim != null)
        msgs = ((InternalEObject)newSim).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__SIM, null, msgs);
      msgs = basicSetSim(newSim, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__SIM, newSim, newSim));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(expression newExpr, NotificationChain msgs)
  {
    expression oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(expression newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__EXPR, newExpr, newExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public if_equation getIF_EQN()
  {
    return iF_EQN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIF_EQN(if_equation newIF_EQN, NotificationChain msgs)
  {
    if_equation oldIF_EQN = iF_EQN;
    iF_EQN = newIF_EQN;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__IF_EQN, oldIF_EQN, newIF_EQN);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIF_EQN(if_equation newIF_EQN)
  {
    if (newIF_EQN != iF_EQN)
    {
      NotificationChain msgs = null;
      if (iF_EQN != null)
        msgs = ((InternalEObject)iF_EQN).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__IF_EQN, null, msgs);
      if (newIF_EQN != null)
        msgs = ((InternalEObject)newIF_EQN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__IF_EQN, null, msgs);
      msgs = basicSetIF_EQN(newIF_EQN, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__IF_EQN, newIF_EQN, newIF_EQN));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public for_equation getFOR_EQN()
  {
    return foR_EQN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFOR_EQN(for_equation newFOR_EQN, NotificationChain msgs)
  {
    for_equation oldFOR_EQN = foR_EQN;
    foR_EQN = newFOR_EQN;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__FOR_EQN, oldFOR_EQN, newFOR_EQN);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFOR_EQN(for_equation newFOR_EQN)
  {
    if (newFOR_EQN != foR_EQN)
    {
      NotificationChain msgs = null;
      if (foR_EQN != null)
        msgs = ((InternalEObject)foR_EQN).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__FOR_EQN, null, msgs);
      if (newFOR_EQN != null)
        msgs = ((InternalEObject)newFOR_EQN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__FOR_EQN, null, msgs);
      msgs = basicSetFOR_EQN(newFOR_EQN, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__FOR_EQN, newFOR_EQN, newFOR_EQN));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public connect_clause getCon()
  {
    return con;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCon(connect_clause newCon, NotificationChain msgs)
  {
    connect_clause oldCon = con;
    con = newCon;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__CON, oldCon, newCon);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCon(connect_clause newCon)
  {
    if (newCon != con)
    {
      NotificationChain msgs = null;
      if (con != null)
        msgs = ((InternalEObject)con).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__CON, null, msgs);
      if (newCon != null)
        msgs = ((InternalEObject)newCon).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__CON, null, msgs);
      msgs = basicSetCon(newCon, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__CON, newCon, newCon));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public when_equation getWHEN_EQN()
  {
    return wheN_EQN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWHEN_EQN(when_equation newWHEN_EQN, NotificationChain msgs)
  {
    when_equation oldWHEN_EQN = wheN_EQN;
    wheN_EQN = newWHEN_EQN;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__WHEN_EQN, oldWHEN_EQN, newWHEN_EQN);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWHEN_EQN(when_equation newWHEN_EQN)
  {
    if (newWHEN_EQN != wheN_EQN)
    {
      NotificationChain msgs = null;
      if (wheN_EQN != null)
        msgs = ((InternalEObject)wheN_EQN).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__WHEN_EQN, null, msgs);
      if (newWHEN_EQN != null)
        msgs = ((InternalEObject)newWHEN_EQN).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.EQUATION__WHEN_EQN, null, msgs);
      msgs = basicSetWHEN_EQN(newWHEN_EQN, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__WHEN_EQN, newWHEN_EQN, newWHEN_EQN));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    String oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.EQUATION__COMMENT, oldComment, comment));
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
      case ModificationPackage.EQUATION__SIM:
        return basicSetSim(null, msgs);
      case ModificationPackage.EQUATION__EXPR:
        return basicSetExpr(null, msgs);
      case ModificationPackage.EQUATION__IF_EQN:
        return basicSetIF_EQN(null, msgs);
      case ModificationPackage.EQUATION__FOR_EQN:
        return basicSetFOR_EQN(null, msgs);
      case ModificationPackage.EQUATION__CON:
        return basicSetCon(null, msgs);
      case ModificationPackage.EQUATION__WHEN_EQN:
        return basicSetWHEN_EQN(null, msgs);
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
      case ModificationPackage.EQUATION__SIM:
        return getSim();
      case ModificationPackage.EQUATION__EXPR:
        return getExpr();
      case ModificationPackage.EQUATION__IF_EQN:
        return getIF_EQN();
      case ModificationPackage.EQUATION__FOR_EQN:
        return getFOR_EQN();
      case ModificationPackage.EQUATION__CON:
        return getCon();
      case ModificationPackage.EQUATION__WHEN_EQN:
        return getWHEN_EQN();
      case ModificationPackage.EQUATION__COMMENT:
        return getComment();
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
      case ModificationPackage.EQUATION__SIM:
        setSim((simple_expression)newValue);
        return;
      case ModificationPackage.EQUATION__EXPR:
        setExpr((expression)newValue);
        return;
      case ModificationPackage.EQUATION__IF_EQN:
        setIF_EQN((if_equation)newValue);
        return;
      case ModificationPackage.EQUATION__FOR_EQN:
        setFOR_EQN((for_equation)newValue);
        return;
      case ModificationPackage.EQUATION__CON:
        setCon((connect_clause)newValue);
        return;
      case ModificationPackage.EQUATION__WHEN_EQN:
        setWHEN_EQN((when_equation)newValue);
        return;
      case ModificationPackage.EQUATION__COMMENT:
        setComment((String)newValue);
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
      case ModificationPackage.EQUATION__SIM:
        setSim((simple_expression)null);
        return;
      case ModificationPackage.EQUATION__EXPR:
        setExpr((expression)null);
        return;
      case ModificationPackage.EQUATION__IF_EQN:
        setIF_EQN((if_equation)null);
        return;
      case ModificationPackage.EQUATION__FOR_EQN:
        setFOR_EQN((for_equation)null);
        return;
      case ModificationPackage.EQUATION__CON:
        setCon((connect_clause)null);
        return;
      case ModificationPackage.EQUATION__WHEN_EQN:
        setWHEN_EQN((when_equation)null);
        return;
      case ModificationPackage.EQUATION__COMMENT:
        setComment(COMMENT_EDEFAULT);
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
      case ModificationPackage.EQUATION__SIM:
        return sim != null;
      case ModificationPackage.EQUATION__EXPR:
        return expr != null;
      case ModificationPackage.EQUATION__IF_EQN:
        return iF_EQN != null;
      case ModificationPackage.EQUATION__FOR_EQN:
        return foR_EQN != null;
      case ModificationPackage.EQUATION__CON:
        return con != null;
      case ModificationPackage.EQUATION__WHEN_EQN:
        return wheN_EQN != null;
      case ModificationPackage.EQUATION__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
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
    result.append(" (Comment: ");
    result.append(comment);
    result.append(')');
    return result.toString();
  }

} //equationImpl
