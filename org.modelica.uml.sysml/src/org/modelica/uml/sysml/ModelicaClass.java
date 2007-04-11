/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.modelica.uml.sysml;

import java.util.List;

import org.eclipse.uml2.uml.Type;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modelica Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.modelica.uml.sysml.ModelicaClass#isIsEncapsulatedClass <em>Is Encapsulated Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaClass()
 * @model
 * @generated
 */
public interface ModelicaClass extends Block, ModelicaClassifier {
	
	public ModelicaComposition createModelicaComposition(
			String end1Name, Type end1Type, String end2Name);
	
	
	public List getCompositionProperties();
	
	/**
	 * Returns the value of the '<em><b>Is Encapsulated Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Encapsulated Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Encapsulated Class</em>' attribute.
	 * @see #setIsEncapsulatedClass(boolean)
	 * @see org.modelica.uml.sysml.SysmlPackage#getModelicaClass_IsEncapsulatedClass()
	 * @model dataType="org.eclipse.uml2.uml.Boolean"
	 * @generated
	 */
	boolean isIsEncapsulatedClass();

	/**
	 * Sets the value of the '{@link org.modelica.uml.sysml.ModelicaClass#isIsEncapsulatedClass <em>Is Encapsulated Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Encapsulated Class</em>' attribute.
	 * @see #isIsEncapsulatedClass()
	 * @generated
	 */
	void setIsEncapsulatedClass(boolean value);

} // ModelicaClass