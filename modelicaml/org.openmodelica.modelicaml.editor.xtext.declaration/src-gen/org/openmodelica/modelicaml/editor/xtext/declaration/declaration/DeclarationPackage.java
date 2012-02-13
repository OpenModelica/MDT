/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.declaration.declaration;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.DeclarationFactory
 * @model kind="package"
 * @generated
 */
public interface DeclarationPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "declaration";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/declaration/Declaration";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "declaration";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DeclarationPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.component_declarationImpl <em>component declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.component_declarationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationPackageImpl#getcomponent_declaration()
   * @generated
   */
  int COMPONENT_DECLARATION = 0;

  /**
   * The feature id for the '<em><b>Mod</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__MOD = 0;

  /**
   * The feature id for the '<em><b>Conditional Att</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__CONDITIONAL_ATT = 1;

  /**
   * The number of structural features of the '<em>component declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.modificationImpl <em>modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.modificationImpl
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationPackageImpl#getmodification()
   * @generated
   */
  int MODIFICATION = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION__EXPR = 0;

  /**
   * The number of structural features of the '<em>modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.conditional_attributeImpl <em>conditional attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.conditional_attributeImpl
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationPackageImpl#getconditional_attribute()
   * @generated
   */
  int CONDITIONAL_ATTRIBUTE = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_ATTRIBUTE__EXPR = 0;

  /**
   * The number of structural features of the '<em>conditional attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_ATTRIBUTE_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration <em>component declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>component declaration</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration
   * @generated
   */
  EClass getcomponent_declaration();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getMod <em>Mod</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mod</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getMod()
   * @see #getcomponent_declaration()
   * @generated
   */
  EReference getcomponent_declaration_Mod();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getConditional_Att <em>Conditional Att</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Conditional Att</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.component_declaration#getConditional_Att()
   * @see #getcomponent_declaration()
   * @generated
   */
  EReference getcomponent_declaration_Conditional_Att();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.modification <em>modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>modification</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.modification
   * @generated
   */
  EClass getmodification();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.modification#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.modification#getExpr()
   * @see #getmodification()
   * @generated
   */
  EReference getmodification_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.conditional_attribute <em>conditional attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>conditional attribute</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.conditional_attribute
   * @generated
   */
  EClass getconditional_attribute();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.conditional_attribute#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.conditional_attribute#getExpr()
   * @see #getconditional_attribute()
   * @generated
   */
  EReference getconditional_attribute_Expr();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DeclarationFactory getDeclarationFactory();

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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.component_declarationImpl <em>component declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.component_declarationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationPackageImpl#getcomponent_declaration()
     * @generated
     */
    EClass COMPONENT_DECLARATION = eINSTANCE.getcomponent_declaration();

    /**
     * The meta object literal for the '<em><b>Mod</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_DECLARATION__MOD = eINSTANCE.getcomponent_declaration_Mod();

    /**
     * The meta object literal for the '<em><b>Conditional Att</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_DECLARATION__CONDITIONAL_ATT = eINSTANCE.getcomponent_declaration_Conditional_Att();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.modificationImpl <em>modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.modificationImpl
     * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationPackageImpl#getmodification()
     * @generated
     */
    EClass MODIFICATION = eINSTANCE.getmodification();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODIFICATION__EXPR = eINSTANCE.getmodification_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.conditional_attributeImpl <em>conditional attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.conditional_attributeImpl
     * @see org.openmodelica.modelicaml.editor.xtext.declaration.declaration.impl.DeclarationPackageImpl#getconditional_attribute()
     * @generated
     */
    EClass CONDITIONAL_ATTRIBUTE = eINSTANCE.getconditional_attribute();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_ATTRIBUTE__EXPR = eINSTANCE.getconditional_attribute_Expr();

  }

} //DeclarationPackage
