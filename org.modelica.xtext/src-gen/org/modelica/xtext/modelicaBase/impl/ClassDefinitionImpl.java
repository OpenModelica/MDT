/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.modelica.xtext.modelicaBase.ClassDefinition;
import org.modelica.xtext.modelicaBase.ClassModification;
import org.modelica.xtext.modelicaBase.ClassType;
import org.modelica.xtext.modelicaBase.ElementList;
import org.modelica.xtext.modelicaBase.EquationClause;
import org.modelica.xtext.modelicaBase.ExternalClause;
import org.modelica.xtext.modelicaBase.ModelicaBasePackage;
import org.modelica.xtext.modelicaBase.algorithm_clause;
import org.modelica.xtext.modelicaBase.initial_algorithm_clause;
import org.modelica.xtext.modelicaBase.initial_equation_rule_clause;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#isEncapsulated <em>Encapsulated</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#isPartial <em>Partial</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#isExpandable <em>Expandable</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getModification <em>Modification</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getElemList <em>Elem List</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getInitialEquationClauses <em>Initial Equation Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getInitialAlgorithmClauses <em>Initial Algorithm Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getEquationClauses <em>Equation Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getAlgorithmClauses <em>Algorithm Clauses</em>}</li>
 *   <li>{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl#getExtClause <em>Ext Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassDefinitionImpl extends EntityDefinitionImpl implements ClassDefinition
{
  /**
   * The default value of the '{@link #isEncapsulated() <em>Encapsulated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEncapsulated()
   * @generated
   * @ordered
   */
  protected static final boolean ENCAPSULATED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isEncapsulated() <em>Encapsulated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEncapsulated()
   * @generated
   * @ordered
   */
  protected boolean encapsulated = ENCAPSULATED_EDEFAULT;

  /**
   * The default value of the '{@link #isPartial() <em>Partial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPartial()
   * @generated
   * @ordered
   */
  protected static final boolean PARTIAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPartial() <em>Partial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPartial()
   * @generated
   * @ordered
   */
  protected boolean partial = PARTIAL_EDEFAULT;

  /**
   * The default value of the '{@link #isExpandable() <em>Expandable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExpandable()
   * @generated
   * @ordered
   */
  protected static final boolean EXPANDABLE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isExpandable() <em>Expandable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExpandable()
   * @generated
   * @ordered
   */
  protected boolean expandable = EXPANDABLE_EDEFAULT;

  /**
   * The default value of the '{@link #getClassType() <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassType()
   * @generated
   * @ordered
   */
  protected static final ClassType CLASS_TYPE_EDEFAULT = ClassType.CLASS;

  /**
   * The cached value of the '{@link #getClassType() <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassType()
   * @generated
   * @ordered
   */
  protected ClassType classType = CLASS_TYPE_EDEFAULT;

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
   * The cached value of the '{@link #getModification() <em>Modification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModification()
   * @generated
   * @ordered
   */
  protected ClassModification modification;

  /**
   * The cached value of the '{@link #getElemList() <em>Elem List</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElemList()
   * @generated
   * @ordered
   */
  protected EList<ElementList> elemList;

  /**
   * The cached value of the '{@link #getInitialEquationClauses() <em>Initial Equation Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitialEquationClauses()
   * @generated
   * @ordered
   */
  protected EList<initial_equation_rule_clause> initialEquationClauses;

  /**
   * The cached value of the '{@link #getInitialAlgorithmClauses() <em>Initial Algorithm Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitialAlgorithmClauses()
   * @generated
   * @ordered
   */
  protected EList<initial_algorithm_clause> initialAlgorithmClauses;

  /**
   * The cached value of the '{@link #getEquationClauses() <em>Equation Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEquationClauses()
   * @generated
   * @ordered
   */
  protected EList<EquationClause> equationClauses;

  /**
   * The cached value of the '{@link #getAlgorithmClauses() <em>Algorithm Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlgorithmClauses()
   * @generated
   * @ordered
   */
  protected EList<algorithm_clause> algorithmClauses;

  /**
   * The cached value of the '{@link #getExtClause() <em>Ext Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtClause()
   * @generated
   * @ordered
   */
  protected ExternalClause extClause;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClassDefinitionImpl()
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
    return ModelicaBasePackage.Literals.CLASS_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isEncapsulated()
  {
    return encapsulated;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEncapsulated(boolean newEncapsulated)
  {
    boolean oldEncapsulated = encapsulated;
    encapsulated = newEncapsulated;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__ENCAPSULATED, oldEncapsulated, encapsulated));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPartial()
  {
    return partial;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPartial(boolean newPartial)
  {
    boolean oldPartial = partial;
    partial = newPartial;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__PARTIAL, oldPartial, partial));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isExpandable()
  {
    return expandable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpandable(boolean newExpandable)
  {
    boolean oldExpandable = expandable;
    expandable = newExpandable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__EXPANDABLE, oldExpandable, expandable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassType getClassType()
  {
    return classType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClassType(ClassType newClassType)
  {
    ClassType oldClassType = classType;
    classType = newClassType == null ? CLASS_TYPE_EDEFAULT : newClassType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__CLASS_TYPE, oldClassType, classType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClassModification getModification()
  {
    return modification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModification(ClassModification newModification, NotificationChain msgs)
  {
    ClassModification oldModification = modification;
    modification = newModification;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION, oldModification, newModification);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModification(ClassModification newModification)
  {
    if (newModification != modification)
    {
      NotificationChain msgs = null;
      if (modification != null)
        msgs = ((InternalEObject)modification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION, null, msgs);
      if (newModification != null)
        msgs = ((InternalEObject)newModification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION, null, msgs);
      msgs = basicSetModification(newModification, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION, newModification, newModification));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ElementList> getElemList()
  {
    if (elemList == null)
    {
      elemList = new EObjectContainmentEList<ElementList>(ElementList.class, this, ModelicaBasePackage.CLASS_DEFINITION__ELEM_LIST);
    }
    return elemList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<initial_equation_rule_clause> getInitialEquationClauses()
  {
    if (initialEquationClauses == null)
    {
      initialEquationClauses = new EObjectContainmentEList<initial_equation_rule_clause>(initial_equation_rule_clause.class, this, ModelicaBasePackage.CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES);
    }
    return initialEquationClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<initial_algorithm_clause> getInitialAlgorithmClauses()
  {
    if (initialAlgorithmClauses == null)
    {
      initialAlgorithmClauses = new EObjectContainmentEList<initial_algorithm_clause>(initial_algorithm_clause.class, this, ModelicaBasePackage.CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES);
    }
    return initialAlgorithmClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EquationClause> getEquationClauses()
  {
    if (equationClauses == null)
    {
      equationClauses = new EObjectContainmentEList<EquationClause>(EquationClause.class, this, ModelicaBasePackage.CLASS_DEFINITION__EQUATION_CLAUSES);
    }
    return equationClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<algorithm_clause> getAlgorithmClauses()
  {
    if (algorithmClauses == null)
    {
      algorithmClauses = new EObjectContainmentEList<algorithm_clause>(algorithm_clause.class, this, ModelicaBasePackage.CLASS_DEFINITION__ALGORITHM_CLAUSES);
    }
    return algorithmClauses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExternalClause getExtClause()
  {
    return extClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExtClause(ExternalClause newExtClause, NotificationChain msgs)
  {
    ExternalClause oldExtClause = extClause;
    extClause = newExtClause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE, oldExtClause, newExtClause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExtClause(ExternalClause newExtClause)
  {
    if (newExtClause != extClause)
    {
      NotificationChain msgs = null;
      if (extClause != null)
        msgs = ((InternalEObject)extClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE, null, msgs);
      if (newExtClause != null)
        msgs = ((InternalEObject)newExtClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE, null, msgs);
      msgs = basicSetExtClause(newExtClause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE, newExtClause, newExtClause));
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
      case ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION:
        return basicSetModification(null, msgs);
      case ModelicaBasePackage.CLASS_DEFINITION__ELEM_LIST:
        return ((InternalEList<?>)getElemList()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES:
        return ((InternalEList<?>)getInitialEquationClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES:
        return ((InternalEList<?>)getInitialAlgorithmClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CLASS_DEFINITION__EQUATION_CLAUSES:
        return ((InternalEList<?>)getEquationClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CLASS_DEFINITION__ALGORITHM_CLAUSES:
        return ((InternalEList<?>)getAlgorithmClauses()).basicRemove(otherEnd, msgs);
      case ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE:
        return basicSetExtClause(null, msgs);
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
      case ModelicaBasePackage.CLASS_DEFINITION__ENCAPSULATED:
        return isEncapsulated();
      case ModelicaBasePackage.CLASS_DEFINITION__PARTIAL:
        return isPartial();
      case ModelicaBasePackage.CLASS_DEFINITION__EXPANDABLE:
        return isExpandable();
      case ModelicaBasePackage.CLASS_DEFINITION__CLASS_TYPE:
        return getClassType();
      case ModelicaBasePackage.CLASS_DEFINITION__COMMENT:
        return getComment();
      case ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION:
        return getModification();
      case ModelicaBasePackage.CLASS_DEFINITION__ELEM_LIST:
        return getElemList();
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES:
        return getInitialEquationClauses();
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES:
        return getInitialAlgorithmClauses();
      case ModelicaBasePackage.CLASS_DEFINITION__EQUATION_CLAUSES:
        return getEquationClauses();
      case ModelicaBasePackage.CLASS_DEFINITION__ALGORITHM_CLAUSES:
        return getAlgorithmClauses();
      case ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE:
        return getExtClause();
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
      case ModelicaBasePackage.CLASS_DEFINITION__ENCAPSULATED:
        setEncapsulated((Boolean)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__PARTIAL:
        setPartial((Boolean)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__EXPANDABLE:
        setExpandable((Boolean)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__CLASS_TYPE:
        setClassType((ClassType)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__COMMENT:
        setComment((String)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION:
        setModification((ClassModification)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__ELEM_LIST:
        getElemList().clear();
        getElemList().addAll((Collection<? extends ElementList>)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES:
        getInitialEquationClauses().clear();
        getInitialEquationClauses().addAll((Collection<? extends initial_equation_rule_clause>)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES:
        getInitialAlgorithmClauses().clear();
        getInitialAlgorithmClauses().addAll((Collection<? extends initial_algorithm_clause>)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__EQUATION_CLAUSES:
        getEquationClauses().clear();
        getEquationClauses().addAll((Collection<? extends EquationClause>)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__ALGORITHM_CLAUSES:
        getAlgorithmClauses().clear();
        getAlgorithmClauses().addAll((Collection<? extends algorithm_clause>)newValue);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE:
        setExtClause((ExternalClause)newValue);
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
      case ModelicaBasePackage.CLASS_DEFINITION__ENCAPSULATED:
        setEncapsulated(ENCAPSULATED_EDEFAULT);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__PARTIAL:
        setPartial(PARTIAL_EDEFAULT);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__EXPANDABLE:
        setExpandable(EXPANDABLE_EDEFAULT);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__CLASS_TYPE:
        setClassType(CLASS_TYPE_EDEFAULT);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION:
        setModification((ClassModification)null);
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__ELEM_LIST:
        getElemList().clear();
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES:
        getInitialEquationClauses().clear();
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES:
        getInitialAlgorithmClauses().clear();
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__EQUATION_CLAUSES:
        getEquationClauses().clear();
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__ALGORITHM_CLAUSES:
        getAlgorithmClauses().clear();
        return;
      case ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE:
        setExtClause((ExternalClause)null);
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
      case ModelicaBasePackage.CLASS_DEFINITION__ENCAPSULATED:
        return encapsulated != ENCAPSULATED_EDEFAULT;
      case ModelicaBasePackage.CLASS_DEFINITION__PARTIAL:
        return partial != PARTIAL_EDEFAULT;
      case ModelicaBasePackage.CLASS_DEFINITION__EXPANDABLE:
        return expandable != EXPANDABLE_EDEFAULT;
      case ModelicaBasePackage.CLASS_DEFINITION__CLASS_TYPE:
        return classType != CLASS_TYPE_EDEFAULT;
      case ModelicaBasePackage.CLASS_DEFINITION__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case ModelicaBasePackage.CLASS_DEFINITION__MODIFICATION:
        return modification != null;
      case ModelicaBasePackage.CLASS_DEFINITION__ELEM_LIST:
        return elemList != null && !elemList.isEmpty();
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES:
        return initialEquationClauses != null && !initialEquationClauses.isEmpty();
      case ModelicaBasePackage.CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES:
        return initialAlgorithmClauses != null && !initialAlgorithmClauses.isEmpty();
      case ModelicaBasePackage.CLASS_DEFINITION__EQUATION_CLAUSES:
        return equationClauses != null && !equationClauses.isEmpty();
      case ModelicaBasePackage.CLASS_DEFINITION__ALGORITHM_CLAUSES:
        return algorithmClauses != null && !algorithmClauses.isEmpty();
      case ModelicaBasePackage.CLASS_DEFINITION__EXT_CLAUSE:
        return extClause != null;
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
    result.append(" (encapsulated: ");
    result.append(encapsulated);
    result.append(", partial: ");
    result.append(partial);
    result.append(", expandable: ");
    result.append(expandable);
    result.append(", classType: ");
    result.append(classType);
    result.append(", comment: ");
    result.append(comment);
    result.append(')');
    return result.toString();
  }

} //ClassDefinitionImpl
