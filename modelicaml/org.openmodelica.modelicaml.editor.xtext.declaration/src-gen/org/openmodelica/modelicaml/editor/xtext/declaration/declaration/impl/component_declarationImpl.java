/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationPackage;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.conditional_attribute;
import org.openmodelica.modelicaml.editor.xtext.declaration.declaration.modification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>component declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.component_declarationImpl#getMod <em>Mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.component_declarationImpl#getConditional_Att <em>Conditional Att</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class component_declarationImpl extends MinimalEObjectImpl.Container implements component_declaration
{
  /**
   * The cached value of the '{@link #getMod() <em>Mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMod()
   * @generated
   * @ordered
   */
  protected modification mod;

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
    return DeclarationPackage.Literals.COMPONENT_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public modification getMod()
  {
    return mod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMod(modification newMod, NotificationChain msgs)
  {
    modification oldMod = mod;
    mod = newMod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DeclarationPackage.COMPONENT_DECLARATION__MOD, oldMod, newMod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMod(modification newMod)
  {
    if (newMod != mod)
    {
      NotificationChain msgs = null;
      if (mod != null)
        msgs = ((InternalEObject)mod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DeclarationPackage.COMPONENT_DECLARATION__MOD, null, msgs);
      if (newMod != null)
        msgs = ((InternalEObject)newMod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DeclarationPackage.COMPONENT_DECLARATION__MOD, null, msgs);
      msgs = basicSetMod(newMod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeclarationPackage.COMPONENT_DECLARATION__MOD, newMod, newMod));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, oldConditional_Att, newConditional_Att);
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
        msgs = ((InternalEObject)conditional_Att).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, null, msgs);
      if (newConditional_Att != null)
        msgs = ((InternalEObject)newConditional_Att).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, null, msgs);
      msgs = basicSetConditional_Att(newConditional_Att, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT, newConditional_Att, newConditional_Att));
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
      case DeclarationPackage.COMPONENT_DECLARATION__MOD:
        return basicSetMod(null, msgs);
      case DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
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
      case DeclarationPackage.COMPONENT_DECLARATION__MOD:
        return getMod();
      case DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
        return getConditional_Att();
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
      case DeclarationPackage.COMPONENT_DECLARATION__MOD:
        setMod((modification)newValue);
        return;
      case DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
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
      case DeclarationPackage.COMPONENT_DECLARATION__MOD:
        setMod((modification)null);
        return;
      case DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
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
      case DeclarationPackage.COMPONENT_DECLARATION__MOD:
        return mod != null;
      case DeclarationPackage.COMPONENT_DECLARATION__CONDITIONAL_ATT:
        return conditional_Att != null;
    }
    return super.eIsSet(featureID);
  }

} //component_declarationImpl
