/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.ConditionalattributePackage
 * @generated
 */
public interface ConditionalattributeFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ConditionalattributeFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.condition.conditionalattribute.impl.ConditionalattributeFactoryImpl.init();

  /**
   * Returns a new object of class '<em>conditional attribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>conditional attribute</em>'.
   * @generated
   */
  conditional_attribute createconditional_attribute();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ConditionalattributePackage getConditionalattributePackage();

} //ConditionalattributeFactory
