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
 * A representation of the literals of the enumeration '<em><b>Property Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.modelica.uml.sysml.SysmlPackage#getPropertyKind()
 * @model
 * @generated
 */
public final class PropertyKind extends AbstractEnumerator {
	/**
	 * The '<em><b>Parameter</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Parameter</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARAMETER_LITERAL
	 * @model name="parameter"
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER = 0;

	/**
	 * The '<em><b>Part</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Part</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PART_LITERAL
	 * @model name="part"
	 * @generated
	 * @ordered
	 */
	public static final int PART = 1;

	/**
	 * The '<em><b>Variable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Variable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VARIABLE_LITERAL
	 * @model name="variable"
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE = 2;

	/**
	 * The '<em><b>Equation</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Equation</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EQUATION_LITERAL
	 * @model name="equation"
	 * @generated
	 * @ordered
	 */
	public static final int EQUATION = 3;

	/**
	 * The '<em><b>Parameter</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARAMETER
	 * @generated
	 * @ordered
	 */
	public static final PropertyKind PARAMETER_LITERAL = new PropertyKind(PARAMETER, "parameter", "parameter");

	/**
	 * The '<em><b>Part</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PART
	 * @generated
	 * @ordered
	 */
	public static final PropertyKind PART_LITERAL = new PropertyKind(PART, "part", "part");

	/**
	 * The '<em><b>Variable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VARIABLE
	 * @generated
	 * @ordered
	 */
	public static final PropertyKind VARIABLE_LITERAL = new PropertyKind(VARIABLE, "variable", "variable");

	/**
	 * The '<em><b>Equation</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUATION
	 * @generated
	 * @ordered
	 */
	public static final PropertyKind EQUATION_LITERAL = new PropertyKind(EQUATION, "equation", "equation");

	/**
	 * An array of all the '<em><b>Property Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PropertyKind[] VALUES_ARRAY =
		new PropertyKind[] {
			PARAMETER_LITERAL,
			PART_LITERAL,
			VARIABLE_LITERAL,
			EQUATION_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Property Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Property Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PropertyKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PropertyKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Property Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PropertyKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PropertyKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Property Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PropertyKind get(int value) {
		switch (value) {
			case PARAMETER: return PARAMETER_LITERAL;
			case PART: return PART_LITERAL;
			case VARIABLE: return VARIABLE_LITERAL;
			case EQUATION: return EQUATION_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PropertyKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //PropertyKind
