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
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.composition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>class specifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl#getComp <em>Comp</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl#getArray_Subs <em>Array Subs</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl#getClass_mod <em>Class mod</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl#getVar <em>Var</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.class_specifierImpl#getEnum_list <em>Enum list</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class class_specifierImpl extends class_definitionImpl implements class_specifier
{
  /**
   * The cached value of the '{@link #getComp() <em>Comp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp()
   * @generated
   * @ordered
   */
  protected composition comp;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected name name;

  /**
   * The cached value of the '{@link #getArray_Subs() <em>Array Subs</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArray_Subs()
   * @generated
   * @ordered
   */
  protected array_subscripts array_Subs;

  /**
   * The cached value of the '{@link #getClass_mod() <em>Class mod</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClass_mod()
   * @generated
   * @ordered
   */
  protected class_modification class_mod;

  /**
   * The cached value of the '{@link #getVar() <em>Var</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected EList<String> var;

  /**
   * The cached value of the '{@link #getEnum_list() <em>Enum list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnum_list()
   * @generated
   * @ordered
   */
  protected enum_list enum_list;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected class_specifierImpl()
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
    return ModificationPackage.Literals.CLASS_SPECIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public composition getComp()
  {
    return comp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComp(composition newComp, NotificationChain msgs)
  {
    composition oldComp = comp;
    comp = newComp;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__COMP, oldComp, newComp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComp(composition newComp)
  {
    if (newComp != comp)
    {
      NotificationChain msgs = null;
      if (comp != null)
        msgs = ((InternalEObject)comp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__COMP, null, msgs);
      if (newComp != null)
        msgs = ((InternalEObject)newComp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__COMP, null, msgs);
      msgs = basicSetComp(newComp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__COMP, newComp, newComp));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(name newName, NotificationChain msgs)
  {
    name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public array_subscripts getArray_Subs()
  {
    return array_Subs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArray_Subs(array_subscripts newArray_Subs, NotificationChain msgs)
  {
    array_subscripts oldArray_Subs = array_Subs;
    array_Subs = newArray_Subs;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS, oldArray_Subs, newArray_Subs);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArray_Subs(array_subscripts newArray_Subs)
  {
    if (newArray_Subs != array_Subs)
    {
      NotificationChain msgs = null;
      if (array_Subs != null)
        msgs = ((InternalEObject)array_Subs).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS, null, msgs);
      if (newArray_Subs != null)
        msgs = ((InternalEObject)newArray_Subs).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS, null, msgs);
      msgs = basicSetArray_Subs(newArray_Subs, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS, newArray_Subs, newArray_Subs));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class_modification getClass_mod()
  {
    return class_mod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetClass_mod(class_modification newClass_mod, NotificationChain msgs)
  {
    class_modification oldClass_mod = class_mod;
    class_mod = newClass_mod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__CLASS_MOD, oldClass_mod, newClass_mod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClass_mod(class_modification newClass_mod)
  {
    if (newClass_mod != class_mod)
    {
      NotificationChain msgs = null;
      if (class_mod != null)
        msgs = ((InternalEObject)class_mod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__CLASS_MOD, null, msgs);
      if (newClass_mod != null)
        msgs = ((InternalEObject)newClass_mod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__CLASS_MOD, null, msgs);
      msgs = basicSetClass_mod(newClass_mod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__CLASS_MOD, newClass_mod, newClass_mod));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getVar()
  {
    if (var == null)
    {
      var = new EDataTypeEList<String>(String.class, this, ModificationPackage.CLASS_SPECIFIER__VAR);
    }
    return var;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public enum_list getEnum_list()
  {
    return enum_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEnum_list(enum_list newEnum_list, NotificationChain msgs)
  {
    enum_list oldEnum_list = enum_list;
    enum_list = newEnum_list;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__ENUM_LIST, oldEnum_list, newEnum_list);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnum_list(enum_list newEnum_list)
  {
    if (newEnum_list != enum_list)
    {
      NotificationChain msgs = null;
      if (enum_list != null)
        msgs = ((InternalEObject)enum_list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__ENUM_LIST, null, msgs);
      if (newEnum_list != null)
        msgs = ((InternalEObject)newEnum_list).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.CLASS_SPECIFIER__ENUM_LIST, null, msgs);
      msgs = basicSetEnum_list(newEnum_list, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.CLASS_SPECIFIER__ENUM_LIST, newEnum_list, newEnum_list));
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
      case ModificationPackage.CLASS_SPECIFIER__COMP:
        return basicSetComp(null, msgs);
      case ModificationPackage.CLASS_SPECIFIER__NAME:
        return basicSetName(null, msgs);
      case ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS:
        return basicSetArray_Subs(null, msgs);
      case ModificationPackage.CLASS_SPECIFIER__CLASS_MOD:
        return basicSetClass_mod(null, msgs);
      case ModificationPackage.CLASS_SPECIFIER__ENUM_LIST:
        return basicSetEnum_list(null, msgs);
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
      case ModificationPackage.CLASS_SPECIFIER__COMP:
        return getComp();
      case ModificationPackage.CLASS_SPECIFIER__NAME:
        return getName();
      case ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS:
        return getArray_Subs();
      case ModificationPackage.CLASS_SPECIFIER__CLASS_MOD:
        return getClass_mod();
      case ModificationPackage.CLASS_SPECIFIER__VAR:
        return getVar();
      case ModificationPackage.CLASS_SPECIFIER__ENUM_LIST:
        return getEnum_list();
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
      case ModificationPackage.CLASS_SPECIFIER__COMP:
        setComp((composition)newValue);
        return;
      case ModificationPackage.CLASS_SPECIFIER__NAME:
        setName((name)newValue);
        return;
      case ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS:
        setArray_Subs((array_subscripts)newValue);
        return;
      case ModificationPackage.CLASS_SPECIFIER__CLASS_MOD:
        setClass_mod((class_modification)newValue);
        return;
      case ModificationPackage.CLASS_SPECIFIER__VAR:
        getVar().clear();
        getVar().addAll((Collection<? extends String>)newValue);
        return;
      case ModificationPackage.CLASS_SPECIFIER__ENUM_LIST:
        setEnum_list((enum_list)newValue);
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
      case ModificationPackage.CLASS_SPECIFIER__COMP:
        setComp((composition)null);
        return;
      case ModificationPackage.CLASS_SPECIFIER__NAME:
        setName((name)null);
        return;
      case ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS:
        setArray_Subs((array_subscripts)null);
        return;
      case ModificationPackage.CLASS_SPECIFIER__CLASS_MOD:
        setClass_mod((class_modification)null);
        return;
      case ModificationPackage.CLASS_SPECIFIER__VAR:
        getVar().clear();
        return;
      case ModificationPackage.CLASS_SPECIFIER__ENUM_LIST:
        setEnum_list((enum_list)null);
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
      case ModificationPackage.CLASS_SPECIFIER__COMP:
        return comp != null;
      case ModificationPackage.CLASS_SPECIFIER__NAME:
        return name != null;
      case ModificationPackage.CLASS_SPECIFIER__ARRAY_SUBS:
        return array_Subs != null;
      case ModificationPackage.CLASS_SPECIFIER__CLASS_MOD:
        return class_mod != null;
      case ModificationPackage.CLASS_SPECIFIER__VAR:
        return var != null && !var.isEmpty();
      case ModificationPackage.CLASS_SPECIFIER__ENUM_LIST:
        return enum_list != null;
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
    result.append(" (var: ");
    result.append(var);
    result.append(')');
    return result.toString();
  }

} //class_specifierImpl
