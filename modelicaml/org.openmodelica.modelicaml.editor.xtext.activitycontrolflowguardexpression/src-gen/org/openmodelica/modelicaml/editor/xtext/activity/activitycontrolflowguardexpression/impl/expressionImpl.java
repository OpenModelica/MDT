/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.ActivitycontrolflowguardexpressionPackage;
import org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.expression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.activity.activitycontrolflowguardexpression.impl.expressionImpl#getElseKeyword <em>Else Keyword</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class expressionImpl extends org.openmodelica.modelicaml.editor.xtext.model.modeleditor.impl.expressionImpl implements expression
{
  /**
   * The default value of the '{@link #getElseKeyword() <em>Else Keyword</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseKeyword()
   * @generated
   * @ordered
   */
  protected static final String ELSE_KEYWORD_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getElseKeyword() <em>Else Keyword</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseKeyword()
   * @generated
   * @ordered
   */
  protected String elseKeyword = ELSE_KEYWORD_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected expressionImpl()
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
    return ActivitycontrolflowguardexpressionPackage.Literals.EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getElseKeyword()
  {
    return elseKeyword;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElseKeyword(String newElseKeyword)
  {
    String oldElseKeyword = elseKeyword;
    elseKeyword = newElseKeyword;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivitycontrolflowguardexpressionPackage.EXPRESSION__ELSE_KEYWORD, oldElseKeyword, elseKeyword));
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
      case ActivitycontrolflowguardexpressionPackage.EXPRESSION__ELSE_KEYWORD:
        return getElseKeyword();
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
      case ActivitycontrolflowguardexpressionPackage.EXPRESSION__ELSE_KEYWORD:
        setElseKeyword((String)newValue);
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
      case ActivitycontrolflowguardexpressionPackage.EXPRESSION__ELSE_KEYWORD:
        setElseKeyword(ELSE_KEYWORD_EDEFAULT);
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
      case ActivitycontrolflowguardexpressionPackage.EXPRESSION__ELSE_KEYWORD:
        return ELSE_KEYWORD_EDEFAULT == null ? elseKeyword != null : !ELSE_KEYWORD_EDEFAULT.equals(elseKeyword);
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
    result.append(" (ElseKeyword: ");
    result.append(elseKeyword);
    result.append(')');
    return result.toString();
  }

} //expressionImpl
