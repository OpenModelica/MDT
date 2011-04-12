/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.Requirement;
import org.modelica.uml.sysml.StatusKind;
import org.modelica.uml.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getLevel <em>Level</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getDerivedFrom <em>Derived From</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getDerived <em>Derived</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getSatisfiedBy <em>Satisfied By</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getVerifiedBy <em>Verified By</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getTracedTo <em>Traced To</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getRefinedBy <em>Refined By</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getMaster <em>Master</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.impl.RequirementImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequirementImpl extends BlockImpl implements Requirement {
	/**
	 * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected static final int LEVEL_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected int level = LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final int PRIORITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected int priority = PRIORITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDerivedFrom() <em>Derived From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedFrom()
	 * @generated
	 * @ordered
	 */
	protected Requirement derivedFrom;

	/**
	 * The cached value of the '{@link #getDerived() <em>Derived</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerived()
	 * @generated
	 * @ordered
	 */
	protected EList derived;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final StatusKind STATUS_EDEFAULT = StatusKind.DRAFT_LITERAL;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected StatusKind status = STATUS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSatisfiedBy() <em>Satisfied By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSatisfiedBy()
	 * @generated
	 * @ordered
	 */
	protected EList satisfiedBy;

	/**
	 * The cached value of the '{@link #getVerifiedBy() <em>Verified By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerifiedBy()
	 * @generated
	 * @ordered
	 */
	protected EList verifiedBy;

	/**
	 * The cached value of the '{@link #getTracedTo() <em>Traced To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTracedTo()
	 * @generated
	 * @ordered
	 */
	protected EList tracedTo;

	/**
	 * The cached value of the '{@link #getRefinedBy() <em>Refined By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefinedBy()
	 * @generated
	 * @ordered
	 */
	protected EList refinedBy;

	/**
	 * The cached value of the '{@link #getMaster() <em>Master</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaster()
	 * @generated
	 * @ordered
	 */
	protected Requirement master;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SysmlPackage.Literals.REQUIREMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevel(int newLevel) {
		int oldLevel = level;
		level = newLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.REQUIREMENT__LEVEL, oldLevel, level));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.REQUIREMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(int newPriority) {
		int oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.REQUIREMENT__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Requirement getDerivedFrom() {
		if (derivedFrom != null && derivedFrom.eIsProxy()) {
			InternalEObject oldDerivedFrom = (InternalEObject)derivedFrom;
			derivedFrom = (Requirement)eResolveProxy(oldDerivedFrom);
			if (derivedFrom != oldDerivedFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SysmlPackage.REQUIREMENT__DERIVED_FROM, oldDerivedFrom, derivedFrom));
			}
		}
		return derivedFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Requirement basicGetDerivedFrom() {
		return derivedFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDerivedFrom(Requirement newDerivedFrom) {
		Requirement oldDerivedFrom = derivedFrom;
		derivedFrom = newDerivedFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.REQUIREMENT__DERIVED_FROM, oldDerivedFrom, derivedFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDerived() {
		if (derived == null) {
			derived = new EObjectResolvingEList(Requirement.class, this, SysmlPackage.REQUIREMENT__DERIVED);
		}
		return derived;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StatusKind getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(StatusKind newStatus) {
		StatusKind oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.REQUIREMENT__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSatisfiedBy() {
		if (satisfiedBy == null) {
			satisfiedBy = new EObjectResolvingEList(ModelicaClass.class, this, SysmlPackage.REQUIREMENT__SATISFIED_BY);
		}
		return satisfiedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getVerifiedBy() {
		if (verifiedBy == null) {
			verifiedBy = new EObjectResolvingEList(ModelicaClass.class, this, SysmlPackage.REQUIREMENT__VERIFIED_BY);
		}
		return verifiedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTracedTo() {
		if (tracedTo == null) {
			tracedTo = new EObjectResolvingEList(ModelicaClass.class, this, SysmlPackage.REQUIREMENT__TRACED_TO);
		}
		return tracedTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRefinedBy() {
		if (refinedBy == null) {
			refinedBy = new EObjectResolvingEList(ModelicaClass.class, this, SysmlPackage.REQUIREMENT__REFINED_BY);
		}
		return refinedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Requirement getMaster() {
		if (master != null && master.eIsProxy()) {
			InternalEObject oldMaster = (InternalEObject)master;
			master = (Requirement)eResolveProxy(oldMaster);
			if (master != oldMaster) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SysmlPackage.REQUIREMENT__MASTER, oldMaster, master));
			}
		}
		return master;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Requirement basicGetMaster() {
		return master;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaster(Requirement newMaster) {
		Requirement oldMaster = master;
		master = newMaster;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.REQUIREMENT__MASTER, oldMaster, master));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SysmlPackage.REQUIREMENT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SysmlPackage.REQUIREMENT__LEVEL:
				return new Integer(getLevel());
			case SysmlPackage.REQUIREMENT__ID:
				return getId();
			case SysmlPackage.REQUIREMENT__PRIORITY:
				return new Integer(getPriority());
			case SysmlPackage.REQUIREMENT__DERIVED_FROM:
				if (resolve) return getDerivedFrom();
				return basicGetDerivedFrom();
			case SysmlPackage.REQUIREMENT__DERIVED:
				return getDerived();
			case SysmlPackage.REQUIREMENT__STATUS:
				return getStatus();
			case SysmlPackage.REQUIREMENT__SATISFIED_BY:
				return getSatisfiedBy();
			case SysmlPackage.REQUIREMENT__VERIFIED_BY:
				return getVerifiedBy();
			case SysmlPackage.REQUIREMENT__TRACED_TO:
				return getTracedTo();
			case SysmlPackage.REQUIREMENT__REFINED_BY:
				return getRefinedBy();
			case SysmlPackage.REQUIREMENT__MASTER:
				if (resolve) return getMaster();
				return basicGetMaster();
			case SysmlPackage.REQUIREMENT__DESCRIPTION:
				return getDescription();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SysmlPackage.REQUIREMENT__LEVEL:
				setLevel(((Integer)newValue).intValue());
				return;
			case SysmlPackage.REQUIREMENT__ID:
				setId((String)newValue);
				return;
			case SysmlPackage.REQUIREMENT__PRIORITY:
				setPriority(((Integer)newValue).intValue());
				return;
			case SysmlPackage.REQUIREMENT__DERIVED_FROM:
				setDerivedFrom((Requirement)newValue);
				return;
			case SysmlPackage.REQUIREMENT__DERIVED:
				getDerived().clear();
				getDerived().addAll((Collection)newValue);
				return;
			case SysmlPackage.REQUIREMENT__STATUS:
				setStatus((StatusKind)newValue);
				return;
			case SysmlPackage.REQUIREMENT__SATISFIED_BY:
				getSatisfiedBy().clear();
				getSatisfiedBy().addAll((Collection)newValue);
				return;
			case SysmlPackage.REQUIREMENT__VERIFIED_BY:
				getVerifiedBy().clear();
				getVerifiedBy().addAll((Collection)newValue);
				return;
			case SysmlPackage.REQUIREMENT__TRACED_TO:
				getTracedTo().clear();
				getTracedTo().addAll((Collection)newValue);
				return;
			case SysmlPackage.REQUIREMENT__REFINED_BY:
				getRefinedBy().clear();
				getRefinedBy().addAll((Collection)newValue);
				return;
			case SysmlPackage.REQUIREMENT__MASTER:
				setMaster((Requirement)newValue);
				return;
			case SysmlPackage.REQUIREMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SysmlPackage.REQUIREMENT__LEVEL:
				setLevel(LEVEL_EDEFAULT);
				return;
			case SysmlPackage.REQUIREMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case SysmlPackage.REQUIREMENT__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case SysmlPackage.REQUIREMENT__DERIVED_FROM:
				setDerivedFrom((Requirement)null);
				return;
			case SysmlPackage.REQUIREMENT__DERIVED:
				getDerived().clear();
				return;
			case SysmlPackage.REQUIREMENT__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case SysmlPackage.REQUIREMENT__SATISFIED_BY:
				getSatisfiedBy().clear();
				return;
			case SysmlPackage.REQUIREMENT__VERIFIED_BY:
				getVerifiedBy().clear();
				return;
			case SysmlPackage.REQUIREMENT__TRACED_TO:
				getTracedTo().clear();
				return;
			case SysmlPackage.REQUIREMENT__REFINED_BY:
				getRefinedBy().clear();
				return;
			case SysmlPackage.REQUIREMENT__MASTER:
				setMaster((Requirement)null);
				return;
			case SysmlPackage.REQUIREMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SysmlPackage.REQUIREMENT__LEVEL:
				return level != LEVEL_EDEFAULT;
			case SysmlPackage.REQUIREMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case SysmlPackage.REQUIREMENT__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
			case SysmlPackage.REQUIREMENT__DERIVED_FROM:
				return derivedFrom != null;
			case SysmlPackage.REQUIREMENT__DERIVED:
				return derived != null && !derived.isEmpty();
			case SysmlPackage.REQUIREMENT__STATUS:
				return status != STATUS_EDEFAULT;
			case SysmlPackage.REQUIREMENT__SATISFIED_BY:
				return satisfiedBy != null && !satisfiedBy.isEmpty();
			case SysmlPackage.REQUIREMENT__VERIFIED_BY:
				return verifiedBy != null && !verifiedBy.isEmpty();
			case SysmlPackage.REQUIREMENT__TRACED_TO:
				return tracedTo != null && !tracedTo.isEmpty();
			case SysmlPackage.REQUIREMENT__REFINED_BY:
				return refinedBy != null && !refinedBy.isEmpty();
			case SysmlPackage.REQUIREMENT__MASTER:
				return master != null;
			case SysmlPackage.REQUIREMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (level: ");
		result.append(level);
		result.append(", id: ");
		result.append(id);
		result.append(", priority: ");
		result.append(priority);
		result.append(", status: ");
		result.append(status);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //RequirementImpl