/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Class Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.modelica.xtext.modelicaBase.ModelicaBasePackage#getClassType()
 * @model
 * @generated
 */
public enum ClassType implements Enumerator
{
  /**
   * The '<em><b>CLASS</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CLASS_VALUE
   * @generated
   * @ordered
   */
  CLASS(0, "CLASS", "class"),

  /**
   * The '<em><b>MODEL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MODEL_VALUE
   * @generated
   * @ordered
   */
  MODEL(1, "MODEL", "model"),

  /**
   * The '<em><b>RECORD</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #RECORD_VALUE
   * @generated
   * @ordered
   */
  RECORD(2, "RECORD", "record"),

  /**
   * The '<em><b>BLOCK</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BLOCK_VALUE
   * @generated
   * @ordered
   */
  BLOCK(3, "BLOCK", "block"),

  /**
   * The '<em><b>CONNECTOR</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CONNECTOR_VALUE
   * @generated
   * @ordered
   */
  CONNECTOR(4, "CONNECTOR", "connector"),

  /**
   * The '<em><b>TYPE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #TYPE_VALUE
   * @generated
   * @ordered
   */
  TYPE(5, "TYPE", "type"),

  /**
   * The '<em><b>PACKAGE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PACKAGE_VALUE
   * @generated
   * @ordered
   */
  PACKAGE(6, "PACKAGE", "package"),

  /**
   * The '<em><b>FUNCTION</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FUNCTION_VALUE
   * @generated
   * @ordered
   */
  FUNCTION(7, "FUNCTION", "function");

  /**
   * The '<em><b>CLASS</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>CLASS</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CLASS
   * @model literal="class"
   * @generated
   * @ordered
   */
  public static final int CLASS_VALUE = 0;

  /**
   * The '<em><b>MODEL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>MODEL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MODEL
   * @model literal="model"
   * @generated
   * @ordered
   */
  public static final int MODEL_VALUE = 1;

  /**
   * The '<em><b>RECORD</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>RECORD</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #RECORD
   * @model literal="record"
   * @generated
   * @ordered
   */
  public static final int RECORD_VALUE = 2;

  /**
   * The '<em><b>BLOCK</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>BLOCK</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BLOCK
   * @model literal="block"
   * @generated
   * @ordered
   */
  public static final int BLOCK_VALUE = 3;

  /**
   * The '<em><b>CONNECTOR</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>CONNECTOR</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CONNECTOR
   * @model literal="connector"
   * @generated
   * @ordered
   */
  public static final int CONNECTOR_VALUE = 4;

  /**
   * The '<em><b>TYPE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>TYPE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #TYPE
   * @model literal="type"
   * @generated
   * @ordered
   */
  public static final int TYPE_VALUE = 5;

  /**
   * The '<em><b>PACKAGE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PACKAGE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #PACKAGE
   * @model literal="package"
   * @generated
   * @ordered
   */
  public static final int PACKAGE_VALUE = 6;

  /**
   * The '<em><b>FUNCTION</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>FUNCTION</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FUNCTION
   * @model literal="function"
   * @generated
   * @ordered
   */
  public static final int FUNCTION_VALUE = 7;

  /**
   * An array of all the '<em><b>Class Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final ClassType[] VALUES_ARRAY =
    new ClassType[]
    {
      CLASS,
      MODEL,
      RECORD,
      BLOCK,
      CONNECTOR,
      TYPE,
      PACKAGE,
      FUNCTION,
    };

  /**
   * A public read-only list of all the '<em><b>Class Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<ClassType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Class Type</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ClassType get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      ClassType result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Class Type</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ClassType getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      ClassType result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Class Type</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ClassType get(int value)
  {
    switch (value)
    {
      case CLASS_VALUE: return CLASS;
      case MODEL_VALUE: return MODEL;
      case RECORD_VALUE: return RECORD;
      case BLOCK_VALUE: return BLOCK;
      case CONNECTOR_VALUE: return CONNECTOR;
      case TYPE_VALUE: return TYPE;
      case PACKAGE_VALUE: return PACKAGE;
      case FUNCTION_VALUE: return FUNCTION;
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
  private ClassType(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //ClassType
