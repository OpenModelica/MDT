/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>component declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declarationImpl#getComponent_Dec <em>Component Dec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declarationImpl#getDec <em>Dec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.component_declarationImpl#getConditional_Att <em>Conditional Att</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class component_declarationImpl extends component_listImpl implements component_declaration
{
  /**
   * The cached value of the '{@link #getComponent_Dec() <em>Component Dec</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponent_Dec()
   * @generated
   * @ordered
   */
  protected EList<component_declaration> component_Dec;

  /**
   * The cached value of the '{@link #getDec() <em>Dec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDec()
   * @generated
   * @ordered
   */
  protected declaration dec;

  /**
   * The cached value of the '{@link #getConditional_Att() <em>Conditional Att</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConditional_Att()
   * @generated
   * @ordered
   */
  protected conditional_attribute conditional_Att;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected component_declarationImpl()
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
    return ModificationPackage.Literals.COMPONENT_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<component_declaration> getComponent_Dec()
  {
    if (component_Dec == null)
    {
      component_Dec = new EObjectContainmentEList<component_declaration>(component_declaration.class, this, ModificationPackage.COMPONENT_DECLARATION__COMPONENT_DEC);
    }
    return component_Dec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public declaration getDec()
  {
    return dec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDec(declaration newDec, NotificationChain msgs)
  {
    declaration oldDec = dec;
    dec = newDec;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPONENT_DECLARATION__DEC, oldDec, newDec);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDec(declaration newDec)
  {
    if (newDec != dec)
    {
      NotificationChain msgs = null;
      if (dec != null)
        msgs = ((InternalEObject)dec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPONENT_DECLARATION__DEC, null, msgs);
      if (newDec != null)
        msgs = ((InternalEObject)newDec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPONENT_DECLARATION__DEC, null, msgs);
      msgs = basicSetDec(newDec, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPONENT_DECLARATION__DEC, newDec, newDec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_attribute getConditional_Att()
  {
    return conditional_Att;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConditional_Att(conditional_attribute newConditional_Att, NotificationChain msgs)
  {
    conditional_attribute oldConditional_Att = conditional_Att;
    conditional_Att = newConditional_Att;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, oldConditional_Att, newConditional_Att);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConditional_Att(conditional_attribute newConditional_Att)
  {
    if (newConditional_Att != conditional_Att)
    {
      NotificationChain msgs = null;
      if (conditional_Att != null)
        msgs = ((InternalEObject)conditional_Att).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, null, msgs);
      if (newConditional_Att != null)
        msgs = ((InternalEObject)newConditional_Att).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, null, msgs);
      msgs = basicSetConditional_Att(newConditional_Att, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, newConditional_Att, newConditional_Att));
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
      case ModificationPackage.COMPONENT_DECLARATION__COMPONENT_DEC:
        return ((InternalEList<?>)getComponent_Dec()).basicRemove(otherEnd, msgs);
      case ModificationPackage.COMPONENT_DECLARATION__DEC:
        return basicSetDec(null, msgs);
      case ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
        return basicSetConditional_Att(null, msgs);
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
      case ModificationPackage.COMPONENT_DECLARATION__COMPONENT_DEC:
        return getComponent_Dec();
      case ModificationPackage.COMPONENT_DECLARATION__DEC:
        return getDec();
      case ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
        return getConditional_Att();
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
      case ModificationPackage.COMPONENT_DECLARATION__COMPONENT_DEC:
        getComponent_Dec().clear();
        getComponent_Dec().addAll((Collection<? extends component_declaration>)newValue);
        return;
      case ModificationPackage.COMPONENT_DECLARATION__DEC:
        setDec((declaration)newValue);
        return;
      case ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
        setConditional_Att((conditional_attribute)newValue);
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
      case ModificationPackage.COMPONENT_DECLARATION__COMPONENT_DEC:
        getComponent_Dec().clear();
        return;
      case ModificationPackage.COMPONENT_DECLARATION__DEC:
        setDec((declaration)null);
        return;
      case ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
        setConditional_Att((conditional_attribute)null);
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
      case ModificationPackage.COMPONENT_DECLARATION__COMPONENT_DEC:
        return component_Dec != null && !component_Dec.isEmpty();
      case ModificationPackage.COMPONENT_DECLARATION__DEC:
        return dec != null;
      case ModificationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
        return conditional_Att != null;
    }
    return super.eIsSet(featureID);
  }

} //component_declarationImpl
