/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.array.arraysubscript;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.ArraysubscriptFactory
 * @model kind="package"
 * @generated
 */
public interface ArraysubscriptPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "arraysubscript";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/array/Arraysubscript";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "arraysubscript";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ArraysubscriptPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.ArraysubscriptPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.subscriptImpl <em>subscript</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.subscriptImpl
   * @see org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.ArraysubscriptPackageImpl#getsubscript()
   * @generated
   */
  int SUBSCRIPT = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUBSCRIPT__EXPR = ModeleditorPackage.SUBSCRIPT__EXPR;

  /**
   * The number of structural features of the '<em>subscript</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUBSCRIPT_FEATURE_COUNT = ModeleditorPackage.SUBSCRIPT_FEATURE_COUNT + 0;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.subscript <em>subscript</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>subscript</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.subscript
   * @generated
   */
  EClass getsubscript();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ArraysubscriptFactory getArraysubscriptFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.subscriptImpl <em>subscript</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.subscriptImpl
     * @see org.openmodelica.modelicaml.editor.xtext.array.arraysubscript.impl.ArraysubscriptPackageImpl#getsubscript()
     * @generated
     */
    EClass SUBSCRIPT = eINSTANCE.getsubscript();

  }

} //ArraysubscriptPackage
