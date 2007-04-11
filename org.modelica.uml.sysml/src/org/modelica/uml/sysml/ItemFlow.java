/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import org.eclipse.uml2.uml.InformationFlow;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Item Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ItemFlow#getItemProperty <em>Item Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getItemFlow()
 * @model
 * @generated
 */
public interface ItemFlow extends InformationFlow {
	/**
	 * Returns the value of the '<em><b>Item Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item Property</em>' reference.
	 * @see #setItemProperty(BlockProperty)
	 * @see org.modelica.uml.sysml.SysmlPackage#getItemFlow_ItemProperty()
	 * @model
	 * @generated
	 */
	BlockProperty getItemProperty();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ItemFlow#getItemProperty <em>Item Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Item Property</em>' reference.
	 * @see #getItemProperty()
	 * @generated
	 */
	void setItemProperty(BlockProperty value);

} // ItemFlow