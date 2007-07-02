/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modelica Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ModelicaProperty#getVariability <em>Variability</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaProperty#isIsFlow <em>Is Flow</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaProperty#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaProperty#getPropertyKind <em>Property Kind</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaProperty#getArrayDimension <em>Array Dimension</em>}</li>
 *   <li>{@link org.modelica.uml.sysml.ModelicaProperty#getAccess <em>Access</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaProperty()
 * @model
 * @generated
 */
public interface ModelicaProperty extends BlockProperty {
	/**
	 * Returns the value of the '<em><b>Variability</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelica.uml.sysml.VariabilityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variability</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variability</em>' attribute.
	 * @see org.modelica.uml.sysml.VariabilityKind
	 * @see #setVariability(VariabilityKind)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaProperty_Variability()
	 * @model
	 * @generated
	 */
	VariabilityKind getVariability();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaProperty#getVariability <em>Variability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variability</em>' attribute.
	 * @see org.modelica.uml.sysml.VariabilityKind
	 * @see #getVariability()
	 * @generated
	 */
	void setVariability(VariabilityKind value);

	/**
	 * Returns the value of the '<em><b>Is Flow</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Flow</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Flow</em>' attribute.
	 * @see #setIsFlow(boolean)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaProperty_IsFlow()
	 * @model dataType="org.eclipse.uml2.uml.Boolean"
	 * @generated
	 */
	boolean isIsFlow();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaProperty#isIsFlow <em>Is Flow</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Flow</em>' attribute.
	 * @see #isIsFlow()
	 * @generated
	 */
	void setIsFlow(boolean value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The default value is <code>"inout"</code>.
	 * The literals are from the enumeration {@link org.modelica.uml.sysml.FlowDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see org.modelica.uml.sysml.FlowDirection
	 * @see #setDirection(FlowDirection)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaProperty_Direction()
	 * @model default="inout"
	 * @generated
	 */
	FlowDirection getDirection();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaProperty#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see org.modelica.uml.sysml.FlowDirection
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(FlowDirection value);

	/**
	 * Returns the value of the '<em><b>Property Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelica.uml.sysml.PropertyKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Kind</em>' attribute.
	 * @see org.modelica.uml.sysml.PropertyKind
	 * @see #setPropertyKind(PropertyKind)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaProperty_PropertyKind()
	 * @model
	 * @generated
	 */
	PropertyKind getPropertyKind();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaProperty#getPropertyKind <em>Property Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Kind</em>' attribute.
	 * @see org.modelica.uml.sysml.PropertyKind
	 * @see #getPropertyKind()
	 * @generated
	 */
	void setPropertyKind(PropertyKind value);

	/**
	 * Returns the value of the '<em><b>Array Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Dimension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Dimension</em>' attribute.
	 * @see #setArrayDimension(String)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaProperty_ArrayDimension()
	 * @model dataType="org.eclipse.uml2.uml.String"
	 * @generated
	 */
	String getArrayDimension();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaProperty#getArrayDimension <em>Array Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Dimension</em>' attribute.
	 * @see #getArrayDimension()
	 * @generated
	 */
	void setArrayDimension(String value);

	/**
	 * Returns the value of the '<em><b>Access</b></em>' attribute.
	 * The literals are from the enumeration {@link org.modelica.uml.sysml.AccessKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access</em>' attribute.
	 * @see org.modelica.uml.sysml.AccessKind
	 * @see #setAccess(AccessKind)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaProperty_Access()
	 * @model
	 * @generated
	 */
	AccessKind getAccess();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaProperty#getAccess <em>Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access</em>' attribute.
	 * @see org.modelica.uml.sysml.AccessKind
	 * @see #getAccess()
	 * @generated
	 */
	void setAccess(AccessKind value);

} // ModelicaProperty