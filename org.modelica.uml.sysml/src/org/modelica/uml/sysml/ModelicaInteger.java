/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modelica Integer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ModelicaInteger#getValue <em>Value</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaInteger#getMin <em>Min</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaInteger#getMax <em>Max</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaInteger#getStart <em>Start</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaInteger#isFixed <em>Fixed</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaInteger()
 * @model
 * @generated
 */
public interface ModelicaInteger extends ModelicaType {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(int)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaInteger_Value()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getValue();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaInteger#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(int value);

	/**
	 * Returns the value of the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min</em>' attribute.
	 * @see #setMin(int)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaInteger_Min()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getMin();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaInteger#getMin <em>Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(int value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(int)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaInteger_Max()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getMax();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaInteger#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(int value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(int)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaInteger_Start()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getStart();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaInteger#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(int value);

	/**
	 * Returns the value of the '<em><b>Fixed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fixed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fixed</em>' attribute.
	 * @see #setFixed(boolean)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaInteger_Fixed()
	 * @model dataType="org.eclipse.uml2.uml.Boolean"
	 * @generated
	 */
	boolean isFixed();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaInteger#isFixed <em>Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed</em>' attribute.
	 * @see #isFixed()
	 * @generated
	 */
	void setFixed(boolean value);

} // ModelicaInteger