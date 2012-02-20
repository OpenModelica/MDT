/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>enum list</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.enum_listImpl#getEnnum_Lit <em>Ennum Lit</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.enum_listImpl#getE_literal <em>Eliteral</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class enum_listImpl extends MinimalEObjectImpl.Container implements enum_list
{
  /**
   * The default value of the '{@link #getEnnum_Lit() <em>Ennum Lit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnnum_Lit()
   * @generated
   * @ordered
   */
  protected static final String ENNUM_LIT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEnnum_Lit() <em>Ennum Lit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnnum_Lit()
   * @generated
   * @ordered
   */
  protected String ennum_Lit = ENNUM_LIT_EDEFAULT;

  /**
   * The cached value of the '{@link #getE_literal() <em>Eliteral</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getE_literal()
   * @generated
   * @ordered
   */
  protected EList<String> e_literal;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected enum_listImpl()
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
    return ModificationPackage.Literals.ENUM_LIST;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEnnum_Lit()
  {
    return ennum_Lit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnnum_Lit(String newEnnum_Lit)
  {
    String oldEnnum_Lit = ennum_Lit;
    ennum_Lit = newEnnum_Lit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.ENUM_LIST__ENNUM_LIT, oldEnnum_Lit, ennum_Lit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getE_literal()
  {
    if (e_literal == null)
    {
      e_literal = new EDataTypeEList<String>(String.class, this, ModificationPackage.ENUM_LIST__ELITERAL);
    }
    return e_literal;
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
      case ModificationPackage.ENUM_LIST__ENNUM_LIT:
        return getEnnum_Lit();
      case ModificationPackage.ENUM_LIST__ELITERAL:
        return getE_literal();
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
      case ModificationPackage.ENUM_LIST__ENNUM_LIT:
        setEnnum_Lit((String)newValue);
        return;
      case ModificationPackage.ENUM_LIST__ELITERAL:
        getE_literal().clear();
        getE_literal().addAll((Collection<? extends String>)newValue);
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
      case ModificationPackage.ENUM_LIST__ENNUM_LIT:
        setEnnum_Lit(ENNUM_LIT_EDEFAULT);
        return;
      case ModificationPackage.ENUM_LIST__ELITERAL:
        getE_literal().clear();
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
      case ModificationPackage.ENUM_LIST__ENNUM_LIT:
        return ENNUM_LIT_EDEFAULT == null ? ennum_Lit != null : !ENNUM_LIT_EDEFAULT.equals(ennum_Lit);
      case ModificationPackage.ENUM_LIST__ELITERAL:
        return e_literal != null && !e_literal.isEmpty();
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
    result.append(" (Ennum_Lit: ");
    result.append(ennum_Lit);
    result.append(", E_literal: ");
    result.append(e_literal);
    result.append(')');
    return result.toString();
  }

} //enum_listImpl
