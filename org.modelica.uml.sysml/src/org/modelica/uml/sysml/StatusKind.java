/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Status Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.modelica.uml.sysml.SysmlPackage#getStatusKind()
 * @model
 * @generated
 */
public final class StatusKind extends AbstractEnumerator {
	/**
	 * The '<em><b>Draft</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Draft</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DRAFT_LITERAL
	 * @model name="draft"
	 * @generated
	 * @ordered
	 */
	public static final int DRAFT = 0;

	/**
	 * The '<em><b>Rejected</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Rejected</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REJECTED_LITERAL
	 * @model name="rejected"
	 * @generated
	 * @ordered
	 */
	public static final int REJECTED = 1;

	/**
	 * The '<em><b>Incomplete</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Incomplete</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCOMPLETE_LITERAL
	 * @model name="incomplete"
	 * @generated
	 * @ordered
	 */
	public static final int INCOMPLETE = 2;

	/**
	 * The '<em><b>Postponed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Postponed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POSTPONED_LITERAL
	 * @model name="postponed"
	 * @generated
	 * @ordered
	 */
	public static final int POSTPONED = 3;

	/**
	 * The '<em><b>Internally Prioritized</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Internally Prioritized</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INTERNALLY_PRIORITIZED_LITERAL
	 * @model name="internallyPrioritized"
	 * @generated
	 * @ordered
	 */
	public static final int INTERNALLY_PRIORITIZED = 4;

	/**
	 * The '<em><b>Planned</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Planned</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLANNED_LITERAL
	 * @model name="planned"
	 * @generated
	 * @ordered
	 */
	public static final int PLANNED = 5;

	/**
	 * The '<em><b>Refined</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Refined</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REFINED_LITERAL
	 * @model name="refined"
	 * @generated
	 * @ordered
	 */
	public static final int REFINED = 6;

	/**
	 * The '<em><b>Started</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Started</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STARTED_LITERAL
	 * @model name="started"
	 * @generated
	 * @ordered
	 */
	public static final int STARTED = 7;

	/**
	 * The '<em><b>Done</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Done</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DONE_LITERAL
	 * @model name="done"
	 * @generated
	 * @ordered
	 */
	public static final int DONE = 8;

	/**
	 * The '<em><b>Draft</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DRAFT
	 * @generated
	 * @ordered
	 */
	public static final StatusKind DRAFT_LITERAL = new StatusKind(DRAFT, "draft", "draft");

	/**
	 * The '<em><b>Rejected</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REJECTED
	 * @generated
	 * @ordered
	 */
	public static final StatusKind REJECTED_LITERAL = new StatusKind(REJECTED, "rejected", "rejected");

	/**
	 * The '<em><b>Incomplete</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCOMPLETE
	 * @generated
	 * @ordered
	 */
	public static final StatusKind INCOMPLETE_LITERAL = new StatusKind(INCOMPLETE, "incomplete", "incomplete");

	/**
	 * The '<em><b>Postponed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POSTPONED
	 * @generated
	 * @ordered
	 */
	public static final StatusKind POSTPONED_LITERAL = new StatusKind(POSTPONED, "postponed", "postponed");

	/**
	 * The '<em><b>Internally Prioritized</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INTERNALLY_PRIORITIZED
	 * @generated
	 * @ordered
	 */
	public static final StatusKind INTERNALLY_PRIORITIZED_LITERAL = new StatusKind(INTERNALLY_PRIORITIZED, "internallyPrioritized", "internallyPrioritized");

	/**
	 * The '<em><b>Planned</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLANNED
	 * @generated
	 * @ordered
	 */
	public static final StatusKind PLANNED_LITERAL = new StatusKind(PLANNED, "planned", "planned");

	/**
	 * The '<em><b>Refined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REFINED
	 * @generated
	 * @ordered
	 */
	public static final StatusKind REFINED_LITERAL = new StatusKind(REFINED, "refined", "refined");

	/**
	 * The '<em><b>Started</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STARTED
	 * @generated
	 * @ordered
	 */
	public static final StatusKind STARTED_LITERAL = new StatusKind(STARTED, "started", "started");

	/**
	 * The '<em><b>Done</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DONE
	 * @generated
	 * @ordered
	 */
	public static final StatusKind DONE_LITERAL = new StatusKind(DONE, "done", "done");

	/**
	 * An array of all the '<em><b>Status Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StatusKind[] VALUES_ARRAY =
		new StatusKind[] {
			DRAFT_LITERAL,
			REJECTED_LITERAL,
			INCOMPLETE_LITERAL,
			POSTPONED_LITERAL,
			INTERNALLY_PRIORITIZED_LITERAL,
			PLANNED_LITERAL,
			REFINED_LITERAL,
			STARTED_LITERAL,
			DONE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Status Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Status Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatusKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StatusKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Status Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatusKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StatusKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Status Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StatusKind get(int value) {
		switch (value) {
			case DRAFT: return DRAFT_LITERAL;
			case REJECTED: return REJECTED_LITERAL;
			case INCOMPLETE: return INCOMPLETE_LITERAL;
			case POSTPONED: return POSTPONED_LITERAL;
			case INTERNALLY_PRIORITIZED: return INTERNALLY_PRIORITIZED_LITERAL;
			case PLANNED: return PLANNED_LITERAL;
			case REFINED: return REFINED_LITERAL;
			case STARTED: return STARTED_LITERAL;
			case DONE: return DONE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private StatusKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //StatusKind
