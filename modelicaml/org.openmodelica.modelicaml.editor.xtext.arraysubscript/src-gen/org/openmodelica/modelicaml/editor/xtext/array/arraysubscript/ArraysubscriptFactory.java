/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.array.arraysubscript;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.ArraysubscriptPackage
 * @generated
 */
public interface ArraysubscriptFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ArraysubscriptFactory eINSTANCE = org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.ArraysubscriptFactoryImpl.init();

  /**
   * Returns a new object of class '<em>subscript</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>subscript</em>'.
   * @generated
   */
  subscript createsubscript();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ArraysubscriptPackage getArraysubscriptPackage();

} //ArraysubscriptFactory
