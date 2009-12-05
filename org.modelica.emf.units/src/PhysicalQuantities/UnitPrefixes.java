/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PhysicalQuantities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Unit Prefixes</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see PhysicalQuantities.PhysicalQuantitiesPackage#getUnitPrefixes()
 * @model
 * @generated
 */
public enum UnitPrefixes implements Enumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "NONE", "NONE"),

	/**
	 * The '<em><b>YOTTA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YOTTA_VALUE
	 * @generated
	 * @ordered
	 */
	YOTTA(24, "YOTTA", "Y"),

	/**
	 * The '<em><b>ZETTA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ZETTA_VALUE
	 * @generated
	 * @ordered
	 */
	ZETTA(21, "ZETTA", "Z"),

	/**
	 * The '<em><b>EXA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EXA_VALUE
	 * @generated
	 * @ordered
	 */
	EXA(18, "EXA", "E"),

	/**
	 * The '<em><b>PETA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PETA_VALUE
	 * @generated
	 * @ordered
	 */
	PETA(15, "PETA", "P"),

	/**
	 * The '<em><b>TERA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TERA_VALUE
	 * @generated
	 * @ordered
	 */
	TERA(12, "TERA", "T"),

	/**
	 * The '<em><b>GIGA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GIGA_VALUE
	 * @generated
	 * @ordered
	 */
	GIGA(9, "GIGA", "G"),

	/**
	 * The '<em><b>MEGA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MEGA_VALUE
	 * @generated
	 * @ordered
	 */
	MEGA(6, "MEGA", "M"),

	/**
	 * The '<em><b>KILO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #KILO_VALUE
	 * @generated
	 * @ordered
	 */
	KILO(3, "KILO", "K"),

	/**
	 * The '<em><b>HECTO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HECTO_VALUE
	 * @generated
	 * @ordered
	 */
	HECTO(2, "HECTO", "h"),

	/**
	 * The '<em><b>DEKA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEKA_VALUE
	 * @generated
	 * @ordered
	 */
	DEKA(1, "DEKA", "D"),

	/**
	 * The '<em><b>DECI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DECI_VALUE
	 * @generated
	 * @ordered
	 */
	DECI(-1, "DECI", "d"),

	/**
	 * The '<em><b>CENTI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CENTI_VALUE
	 * @generated
	 * @ordered
	 */
	CENTI(-2, "CENTI", "c"),

	/**
	 * The '<em><b>MILLI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MILLI_VALUE
	 * @generated
	 * @ordered
	 */
	MILLI(-3, "MILLI", "m"),

	/**
	 * The '<em><b>MICRO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MICRO_VALUE
	 * @generated
	 * @ordered
	 */
	MICRO(-6, "MICRO", "u"),

	/**
	 * The '<em><b>NANO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NANO_VALUE
	 * @generated
	 * @ordered
	 */
	NANO(-9, "NANO", "n"),

	/**
	 * The '<em><b>PICO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PICO_VALUE
	 * @generated
	 * @ordered
	 */
	PICO(-12, "PICO", "p"),

	/**
	 * The '<em><b>FEMTO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FEMTO_VALUE
	 * @generated
	 * @ordered
	 */
	FEMTO(-15, "FEMTO", "f"),

	/**
	 * The '<em><b>ATTO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ATTO_VALUE
	 * @generated
	 * @ordered
	 */
	ATTO(-18, "ATTO", "a"),

	/**
	 * The '<em><b>ZEPTO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ZEPTO_VALUE
	 * @generated
	 * @ordered
	 */
	ZEPTO(-21, "ZEPTO", "z"),

	/**
	 * The '<em><b>YOCTO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #YOCTO_VALUE
	 * @generated
	 * @ordered
	 */
	YOCTO(-24, "YOCTO", "y");

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>YOTTA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YOTTA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #YOTTA
	 * @model literal="Y"
	 * @generated
	 * @ordered
	 */
	public static final int YOTTA_VALUE = 24;

	/**
	 * The '<em><b>ZETTA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ZETTA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ZETTA
	 * @model literal="Z"
	 * @generated
	 * @ordered
	 */
	public static final int ZETTA_VALUE = 21;

	/**
	 * The '<em><b>EXA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EXA
	 * @model literal="E"
	 * @generated
	 * @ordered
	 */
	public static final int EXA_VALUE = 18;

	/**
	 * The '<em><b>PETA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PETA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PETA
	 * @model literal="P"
	 * @generated
	 * @ordered
	 */
	public static final int PETA_VALUE = 15;

	/**
	 * The '<em><b>TERA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TERA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TERA
	 * @model literal="T"
	 * @generated
	 * @ordered
	 */
	public static final int TERA_VALUE = 12;

	/**
	 * The '<em><b>GIGA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GIGA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GIGA
	 * @model literal="G"
	 * @generated
	 * @ordered
	 */
	public static final int GIGA_VALUE = 9;

	/**
	 * The '<em><b>MEGA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MEGA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MEGA
	 * @model literal="M"
	 * @generated
	 * @ordered
	 */
	public static final int MEGA_VALUE = 6;

	/**
	 * The '<em><b>KILO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>KILO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #KILO
	 * @model literal="K"
	 * @generated
	 * @ordered
	 */
	public static final int KILO_VALUE = 3;

	/**
	 * The '<em><b>HECTO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HECTO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HECTO
	 * @model literal="h"
	 * @generated
	 * @ordered
	 */
	public static final int HECTO_VALUE = 2;

	/**
	 * The '<em><b>DEKA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DEKA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DEKA
	 * @model literal="D"
	 * @generated
	 * @ordered
	 */
	public static final int DEKA_VALUE = 1;

	/**
	 * The '<em><b>DECI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DECI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DECI
	 * @model literal="d"
	 * @generated
	 * @ordered
	 */
	public static final int DECI_VALUE = -1;

	/**
	 * The '<em><b>CENTI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CENTI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CENTI
	 * @model literal="c"
	 * @generated
	 * @ordered
	 */
	public static final int CENTI_VALUE = -2;

	/**
	 * The '<em><b>MILLI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MILLI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MILLI
	 * @model literal="m"
	 * @generated
	 * @ordered
	 */
	public static final int MILLI_VALUE = -3;

	/**
	 * The '<em><b>MICRO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MICRO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MICRO
	 * @model literal="u"
	 * @generated
	 * @ordered
	 */
	public static final int MICRO_VALUE = -6;

	/**
	 * The '<em><b>NANO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NANO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NANO
	 * @model literal="n"
	 * @generated
	 * @ordered
	 */
	public static final int NANO_VALUE = -9;

	/**
	 * The '<em><b>PICO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PICO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PICO
	 * @model literal="p"
	 * @generated
	 * @ordered
	 */
	public static final int PICO_VALUE = -12;

	/**
	 * The '<em><b>FEMTO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FEMTO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FEMTO
	 * @model literal="f"
	 * @generated
	 * @ordered
	 */
	public static final int FEMTO_VALUE = -15;

	/**
	 * The '<em><b>ATTO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ATTO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ATTO
	 * @model literal="a"
	 * @generated
	 * @ordered
	 */
	public static final int ATTO_VALUE = -18;

	/**
	 * The '<em><b>ZEPTO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ZEPTO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ZEPTO
	 * @model literal="z"
	 * @generated
	 * @ordered
	 */
	public static final int ZEPTO_VALUE = -21;

	/**
	 * The '<em><b>YOCTO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YOCTO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #YOCTO
	 * @model literal="y"
	 * @generated
	 * @ordered
	 */
	public static final int YOCTO_VALUE = -24;

	/**
	 * An array of all the '<em><b>Unit Prefixes</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final UnitPrefixes[] VALUES_ARRAY =
		new UnitPrefixes[] {
			NONE,
			YOTTA,
			ZETTA,
			EXA,
			PETA,
			TERA,
			GIGA,
			MEGA,
			KILO,
			HECTO,
			DEKA,
			DECI,
			CENTI,
			MILLI,
			MICRO,
			NANO,
			PICO,
			FEMTO,
			ATTO,
			ZEPTO,
			YOCTO,
		};

	/**
	 * A public read-only list of all the '<em><b>Unit Prefixes</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<UnitPrefixes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Unit Prefixes</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnitPrefixes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnitPrefixes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Unit Prefixes</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnitPrefixes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			UnitPrefixes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Unit Prefixes</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UnitPrefixes get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case YOTTA_VALUE: return YOTTA;
			case ZETTA_VALUE: return ZETTA;
			case EXA_VALUE: return EXA;
			case PETA_VALUE: return PETA;
			case TERA_VALUE: return TERA;
			case GIGA_VALUE: return GIGA;
			case MEGA_VALUE: return MEGA;
			case KILO_VALUE: return KILO;
			case HECTO_VALUE: return HECTO;
			case DEKA_VALUE: return DEKA;
			case DECI_VALUE: return DECI;
			case CENTI_VALUE: return CENTI;
			case MILLI_VALUE: return MILLI;
			case MICRO_VALUE: return MICRO;
			case NANO_VALUE: return NANO;
			case PICO_VALUE: return PICO;
			case FEMTO_VALUE: return FEMTO;
			case ATTO_VALUE: return ATTO;
			case ZEPTO_VALUE: return ZEPTO;
			case YOCTO_VALUE: return YOCTO;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private UnitPrefixes(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //UnitPrefixes
