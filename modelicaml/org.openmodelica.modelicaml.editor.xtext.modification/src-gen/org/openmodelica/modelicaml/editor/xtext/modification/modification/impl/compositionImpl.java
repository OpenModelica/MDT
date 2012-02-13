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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.composition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>composition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getE_List_Initial <em>EList Initial</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getE_List_Public <em>EList Public</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getE_List_Protected <em>EList Protected</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getEqn_section <em>Eqn section</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getAlg_section <em>Alg section</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getLang_Spec <em>Lang Spec</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getExternal_F_C <em>External FC</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getAnnotation1 <em>Annotation1</em>}</li>
 *   <li>{@link org.openmodelica.modelicaml.editor.xtext.modification.modification.impl.compositionImpl#getAnnotation2 <em>Annotation2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class compositionImpl extends MinimalEObjectImpl.Container implements composition
{
  /**
   * The cached value of the '{@link #getE_List_Initial() <em>EList Initial</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getE_List_Initial()
   * @generated
   * @ordered
   */
  protected element_list e_List_Initial;

  /**
   * The cached value of the '{@link #getE_List_Public() <em>EList Public</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getE_List_Public()
   * @generated
   * @ordered
   */
  protected EList<element_list> e_List_Public;

  /**
   * The cached value of the '{@link #getE_List_Protected() <em>EList Protected</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getE_List_Protected()
   * @generated
   * @ordered
   */
  protected EList<element_list> e_List_Protected;

  /**
   * The cached value of the '{@link #getEqn_section() <em>Eqn section</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEqn_section()
   * @generated
   * @ordered
   */
  protected EList<equation_section> eqn_section;

  /**
   * The cached value of the '{@link #getAlg_section() <em>Alg section</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlg_section()
   * @generated
   * @ordered
   */
  protected EList<algorithm_section> alg_section;

  /**
   * The cached value of the '{@link #getLang_Spec() <em>Lang Spec</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLang_Spec()
   * @generated
   * @ordered
   */
  protected language_specification lang_Spec;

  /**
   * The cached value of the '{@link #getExternal_F_C() <em>External FC</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExternal_F_C()
   * @generated
   * @ordered
   */
  protected external_function_call external_F_C;

  /**
   * The cached value of the '{@link #getAnnotation1() <em>Annotation1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation1()
   * @generated
   * @ordered
   */
  protected annotation annotation1;

  /**
   * The cached value of the '{@link #getAnnotation2() <em>Annotation2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation2()
   * @generated
   * @ordered
   */
  protected annotation annotation2;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected compositionImpl()
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
    return ModificationPackage.Literals.COMPOSITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public element_list getE_List_Initial()
  {
    return e_List_Initial;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetE_List_Initial(element_list newE_List_Initial, NotificationChain msgs)
  {
    element_list oldE_List_Initial = e_List_Initial;
    e_List_Initial = newE_List_Initial;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__ELIST_INITIAL, oldE_List_Initial, newE_List_Initial);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setE_List_Initial(element_list newE_List_Initial)
  {
    if (newE_List_Initial != e_List_Initial)
    {
      NotificationChain msgs = null;
      if (e_List_Initial != null)
        msgs = ((InternalEObject)e_List_Initial).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__ELIST_INITIAL, null, msgs);
      if (newE_List_Initial != null)
        msgs = ((InternalEObject)newE_List_Initial).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__ELIST_INITIAL, null, msgs);
      msgs = basicSetE_List_Initial(newE_List_Initial, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__ELIST_INITIAL, newE_List_Initial, newE_List_Initial));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<element_list> getE_List_Public()
  {
    if (e_List_Public == null)
    {
      e_List_Public = new EObjectContainmentEList<element_list>(element_list.class, this, ModificationPackage.COMPOSITION__ELIST_PUBLIC);
    }
    return e_List_Public;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<element_list> getE_List_Protected()
  {
    if (e_List_Protected == null)
    {
      e_List_Protected = new EObjectContainmentEList<element_list>(element_list.class, this, ModificationPackage.COMPOSITION__ELIST_PROTECTED);
    }
    return e_List_Protected;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<equation_section> getEqn_section()
  {
    if (eqn_section == null)
    {
      eqn_section = new EObjectContainmentEList<equation_section>(equation_section.class, this, ModificationPackage.COMPOSITION__EQN_SECTION);
    }
    return eqn_section;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<algorithm_section> getAlg_section()
  {
    if (alg_section == null)
    {
      alg_section = new EObjectContainmentEList<algorithm_section>(algorithm_section.class, this, ModificationPackage.COMPOSITION__ALG_SECTION);
    }
    return alg_section;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public language_specification getLang_Spec()
  {
    return lang_Spec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLang_Spec(language_specification newLang_Spec, NotificationChain msgs)
  {
    language_specification oldLang_Spec = lang_Spec;
    lang_Spec = newLang_Spec;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__LANG_SPEC, oldLang_Spec, newLang_Spec);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLang_Spec(language_specification newLang_Spec)
  {
    if (newLang_Spec != lang_Spec)
    {
      NotificationChain msgs = null;
      if (lang_Spec != null)
        msgs = ((InternalEObject)lang_Spec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__LANG_SPEC, null, msgs);
      if (newLang_Spec != null)
        msgs = ((InternalEObject)newLang_Spec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__LANG_SPEC, null, msgs);
      msgs = basicSetLang_Spec(newLang_Spec, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__LANG_SPEC, newLang_Spec, newLang_Spec));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public external_function_call getExternal_F_C()
  {
    return external_F_C;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExternal_F_C(external_function_call newExternal_F_C, NotificationChain msgs)
  {
    external_function_call oldExternal_F_C = external_F_C;
    external_F_C = newExternal_F_C;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__EXTERNAL_FC, oldExternal_F_C, newExternal_F_C);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExternal_F_C(external_function_call newExternal_F_C)
  {
    if (newExternal_F_C != external_F_C)
    {
      NotificationChain msgs = null;
      if (external_F_C != null)
        msgs = ((InternalEObject)external_F_C).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__EXTERNAL_FC, null, msgs);
      if (newExternal_F_C != null)
        msgs = ((InternalEObject)newExternal_F_C).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__EXTERNAL_FC, null, msgs);
      msgs = basicSetExternal_F_C(newExternal_F_C, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__EXTERNAL_FC, newExternal_F_C, newExternal_F_C));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public annotation getAnnotation1()
  {
    return annotation1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation1(annotation newAnnotation1, NotificationChain msgs)
  {
    annotation oldAnnotation1 = annotation1;
    annotation1 = newAnnotation1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__ANNOTATION1, oldAnnotation1, newAnnotation1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation1(annotation newAnnotation1)
  {
    if (newAnnotation1 != annotation1)
    {
      NotificationChain msgs = null;
      if (annotation1 != null)
        msgs = ((InternalEObject)annotation1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__ANNOTATION1, null, msgs);
      if (newAnnotation1 != null)
        msgs = ((InternalEObject)newAnnotation1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__ANNOTATION1, null, msgs);
      msgs = basicSetAnnotation1(newAnnotation1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__ANNOTATION1, newAnnotation1, newAnnotation1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public annotation getAnnotation2()
  {
    return annotation2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation2(annotation newAnnotation2, NotificationChain msgs)
  {
    annotation oldAnnotation2 = annotation2;
    annotation2 = newAnnotation2;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__ANNOTATION2, oldAnnotation2, newAnnotation2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation2(annotation newAnnotation2)
  {
    if (newAnnotation2 != annotation2)
    {
      NotificationChain msgs = null;
      if (annotation2 != null)
        msgs = ((InternalEObject)annotation2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__ANNOTATION2, null, msgs);
      if (newAnnotation2 != null)
        msgs = ((InternalEObject)newAnnotation2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModificationPackage.COMPOSITION__ANNOTATION2, null, msgs);
      msgs = basicSetAnnotation2(newAnnotation2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModificationPackage.COMPOSITION__ANNOTATION2, newAnnotation2, newAnnotation2));
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
      case ModificationPackage.COMPOSITION__ELIST_INITIAL:
        return basicSetE_List_Initial(null, msgs);
      case ModificationPackage.COMPOSITION__ELIST_PUBLIC:
        return ((InternalEList<?>)getE_List_Public()).basicRemove(otherEnd, msgs);
      case ModificationPackage.COMPOSITION__ELIST_PROTECTED:
        return ((InternalEList<?>)getE_List_Protected()).basicRemove(otherEnd, msgs);
      case ModificationPackage.COMPOSITION__EQN_SECTION:
        return ((InternalEList<?>)getEqn_section()).basicRemove(otherEnd, msgs);
      case ModificationPackage.COMPOSITION__ALG_SECTION:
        return ((InternalEList<?>)getAlg_section()).basicRemove(otherEnd, msgs);
      case ModificationPackage.COMPOSITION__LANG_SPEC:
        return basicSetLang_Spec(null, msgs);
      case ModificationPackage.COMPOSITION__EXTERNAL_FC:
        return basicSetExternal_F_C(null, msgs);
      case ModificationPackage.COMPOSITION__ANNOTATION1:
        return basicSetAnnotation1(null, msgs);
      case ModificationPackage.COMPOSITION__ANNOTATION2:
        return basicSetAnnotation2(null, msgs);
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
      case ModificationPackage.COMPOSITION__ELIST_INITIAL:
        return getE_List_Initial();
      case ModificationPackage.COMPOSITION__ELIST_PUBLIC:
        return getE_List_Public();
      case ModificationPackage.COMPOSITION__ELIST_PROTECTED:
        return getE_List_Protected();
      case ModificationPackage.COMPOSITION__EQN_SECTION:
        return getEqn_section();
      case ModificationPackage.COMPOSITION__ALG_SECTION:
        return getAlg_section();
      case ModificationPackage.COMPOSITION__LANG_SPEC:
        return getLang_Spec();
      case ModificationPackage.COMPOSITION__EXTERNAL_FC:
        return getExternal_F_C();
      case ModificationPackage.COMPOSITION__ANNOTATION1:
        return getAnnotation1();
      case ModificationPackage.COMPOSITION__ANNOTATION2:
        return getAnnotation2();
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
      case ModificationPackage.COMPOSITION__ELIST_INITIAL:
        setE_List_Initial((element_list)newValue);
        return;
      case ModificationPackage.COMPOSITION__ELIST_PUBLIC:
        getE_List_Public().clear();
        getE_List_Public().addAll((Collection<? extends element_list>)newValue);
        return;
      case ModificationPackage.COMPOSITION__ELIST_PROTECTED:
        getE_List_Protected().clear();
        getE_List_Protected().addAll((Collection<? extends element_list>)newValue);
        return;
      case ModificationPackage.COMPOSITION__EQN_SECTION:
        getEqn_section().clear();
        getEqn_section().addAll((Collection<? extends equation_section>)newValue);
        return;
      case ModificationPackage.COMPOSITION__ALG_SECTION:
        getAlg_section().clear();
        getAlg_section().addAll((Collection<? extends algorithm_section>)newValue);
        return;
      case ModificationPackage.COMPOSITION__LANG_SPEC:
        setLang_Spec((language_specification)newValue);
        return;
      case ModificationPackage.COMPOSITION__EXTERNAL_FC:
        setExternal_F_C((external_function_call)newValue);
        return;
      case ModificationPackage.COMPOSITION__ANNOTATION1:
        setAnnotation1((annotation)newValue);
        return;
      case ModificationPackage.COMPOSITION__ANNOTATION2:
        setAnnotation2((annotation)newValue);
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
      case ModificationPackage.COMPOSITION__ELIST_INITIAL:
        setE_List_Initial((element_list)null);
        return;
      case ModificationPackage.COMPOSITION__ELIST_PUBLIC:
        getE_List_Public().clear();
        return;
      case ModificationPackage.COMPOSITION__ELIST_PROTECTED:
        getE_List_Protected().clear();
        return;
      case ModificationPackage.COMPOSITION__EQN_SECTION:
        getEqn_section().clear();
        return;
      case ModificationPackage.COMPOSITION__ALG_SECTION:
        getAlg_section().clear();
        return;
      case ModificationPackage.COMPOSITION__LANG_SPEC:
        setLang_Spec((language_specification)null);
        return;
      case ModificationPackage.COMPOSITION__EXTERNAL_FC:
        setExternal_F_C((external_function_call)null);
        return;
      case ModificationPackage.COMPOSITION__ANNOTATION1:
        setAnnotation1((annotation)null);
        return;
      case ModificationPackage.COMPOSITION__ANNOTATION2:
        setAnnotation2((annotation)null);
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
      case ModificationPackage.COMPOSITION__ELIST_INITIAL:
        return e_List_Initial != null;
      case ModificationPackage.COMPOSITION__ELIST_PUBLIC:
        return e_List_Public != null && !e_List_Public.isEmpty();
      case ModificationPackage.COMPOSITION__ELIST_PROTECTED:
        return e_List_Protected != null && !e_List_Protected.isEmpty();
      case ModificationPackage.COMPOSITION__EQN_SECTION:
        return eqn_section != null && !eqn_section.isEmpty();
      case ModificationPackage.COMPOSITION__ALG_SECTION:
        return alg_section != null && !alg_section.isEmpty();
      case ModificationPackage.COMPOSITION__LANG_SPEC:
        return lang_Spec != null;
      case ModificationPackage.COMPOSITION__EXTERNAL_FC:
        return external_F_C != null;
      case ModificationPackage.COMPOSITION__ANNOTATION1:
        return annotation1 != null;
      case ModificationPackage.COMPOSITION__ANNOTATION2:
        return annotation2 != null;
    }
    return super.eIsSet(featureID);
  }

} //compositionImpl
