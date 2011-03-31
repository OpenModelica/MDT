/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
 * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.StatetransitionguardexpressionFactory
 * @model kind="package"
 * @generated
 */
public interface StatetransitionguardexpressionPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "statetransitionguardexpression";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.openmodelica.org/modelicaml/editor/xtext/state/Statetransitionguardexpression";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "statetransitionguardexpression";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  StatetransitionguardexpressionPackage eINSTANCE = org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl.init();

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.expressionImpl <em>expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.expressionImpl
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getexpression()
   * @generated
   */
  int EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__EXPR = ModeleditorPackage.EXPRESSION__EXPR;

  /**
   * The feature id for the '<em><b>Simple Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__SIMPLE_EXPR = ModeleditorPackage.EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = ModeleditorPackage.EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl <em>primary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getprimary()
   * @generated
   */
  int PRIMARY = 1;

  /**
   * The feature id for the '<em><b>Op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OP = ModeleditorPackage.PRIMARY__OP;

  /**
   * The feature id for the '<em><b>Factor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FACTOR = ModeleditorPackage.PRIMARY__FACTOR;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__PRIMARY = ModeleditorPackage.PRIMARY__PRIMARY;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NUM = ModeleditorPackage.PRIMARY__NUM;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__INT = ModeleditorPackage.PRIMARY__INT;

  /**
   * The feature id for the '<em><b>Str</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__STR = ModeleditorPackage.PRIMARY__STR;

  /**
   * The feature id for the '<em><b>Bool</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__BOOL = ModeleditorPackage.PRIMARY__BOOL;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__COMPONENT_REFERENCE = ModeleditorPackage.PRIMARY__COMPONENT_REFERENCE;

  /**
   * The feature id for the '<em><b>Output expr list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__OUTPUT_EXPR_LIST = ModeleditorPackage.PRIMARY__OUTPUT_EXPR_LIST;

  /**
   * The feature id for the '<em><b>Expre list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRE_LIST = ModeleditorPackage.PRIMARY__EXPRE_LIST;

  /**
   * The feature id for the '<em><b>Expression list</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPRESSION_LIST = ModeleditorPackage.PRIMARY__EXPRESSION_LIST;

  /**
   * The feature id for the '<em><b>Farguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FARGUMENTS = ModeleditorPackage.PRIMARY__FARGUMENTS;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__END = ModeleditorPackage.PRIMARY__END;

  /**
   * The feature id for the '<em><b>Name Function</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NAME_FUNCTION = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Initial ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__INITIAL_REF = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__EXPR = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Mac A</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MAC_A = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Mac E</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MAC_E = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Mac C</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MAC_C = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Mac S</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__MAC_S = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Else</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__ELSE = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 7;

  /**
   * The number of structural features of the '<em>primary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY_FEATURE_COUNT = ModeleditorPackage.PRIMARY_FEATURE_COUNT + 8;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_AfterImpl <em>Macro After</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_AfterImpl
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_After()
   * @generated
   */
  int MACRO_AFTER = 2;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_AFTER__INT = 0;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_AFTER__NUM = 1;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_AFTER__COMPONENT_REFERENCE = 2;

  /**
   * The number of structural features of the '<em>Macro After</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_AFTER_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_BEFOREImpl <em>Macro BEFORE</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_BEFOREImpl
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_BEFORE()
   * @generated
   */
  int MACRO_BEFORE = 3;

  /**
   * The feature id for the '<em><b>Int</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_BEFORE__INT = 0;

  /**
   * The feature id for the '<em><b>Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_BEFORE__NUM = 1;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_BEFORE__COMPONENT_REFERENCE = 2;

  /**
   * The number of structural features of the '<em>Macro BEFORE</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_BEFORE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_SIGNALImpl <em>Macro SIGNAL</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_SIGNALImpl
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_SIGNAL()
   * @generated
   */
  int MACRO_SIGNAL = 4;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_SIGNAL__COMPONENT_REFERENCE = 0;

  /**
   * The number of structural features of the '<em>Macro SIGNAL</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_SIGNAL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_EVENTImpl <em>Macro EVENT</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_EVENTImpl
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_EVENT()
   * @generated
   */
  int MACRO_EVENT = 5;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_EVENT__COMPONENT_REFERENCE = 0;

  /**
   * The number of structural features of the '<em>Macro EVENT</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_EVENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_CHANGEImpl <em>Macro CHANGE</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_CHANGEImpl
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_CHANGE()
   * @generated
   */
  int MACRO_CHANGE = 6;

  /**
   * The feature id for the '<em><b>Component reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_CHANGE__COMPONENT_REFERENCE = 0;

  /**
   * The number of structural features of the '<em>Macro CHANGE</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MACRO_CHANGE_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>expression</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression
   * @generated
   */
  EClass getexpression();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression#getSimple_Expr <em>Simple Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simple Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.expression#getSimple_Expr()
   * @see #getexpression()
   * @generated
   */
  EReference getexpression_Simple_Expr();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>primary</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary
   * @generated
   */
  EClass getprimary();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getName_Function <em>Name Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name Function</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getName_Function()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Name_Function();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getInitial_ref <em>Initial ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial ref</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getInitial_ref()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Initial_ref();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getExpr()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Expr();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_A <em>Mac A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mac A</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_A()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Mac_A();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_E <em>Mac E</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mac E</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_E()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Mac_E();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_C <em>Mac C</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mac C</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_C()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Mac_C();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_S <em>Mac S</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mac S</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getMac_S()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Mac_S();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getElse <em>Else</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Else</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.primary#getElse()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Else();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After <em>Macro After</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Macro After</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After
   * @generated
   */
  EClass getMacro_After();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After#getInt <em>Int</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Int</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After#getInt()
   * @see #getMacro_After()
   * @generated
   */
  EAttribute getMacro_After_Int();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After#getNum <em>Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Num</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After#getNum()
   * @see #getMacro_After()
   * @generated
   */
  EAttribute getMacro_After_Num();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_After#getComponent_reference()
   * @see #getMacro_After()
   * @generated
   */
  EReference getMacro_After_Component_reference();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE <em>Macro BEFORE</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Macro BEFORE</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE
   * @generated
   */
  EClass getMacro_BEFORE();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getInt <em>Int</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Int</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getInt()
   * @see #getMacro_BEFORE()
   * @generated
   */
  EAttribute getMacro_BEFORE_Int();

  /**
   * Returns the meta object for the attribute '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getNum <em>Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Num</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getNum()
   * @see #getMacro_BEFORE()
   * @generated
   */
  EAttribute getMacro_BEFORE_Num();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_BEFORE#getComponent_reference()
   * @see #getMacro_BEFORE()
   * @generated
   */
  EReference getMacro_BEFORE_Component_reference();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL <em>Macro SIGNAL</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Macro SIGNAL</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL
   * @generated
   */
  EClass getMacro_SIGNAL();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_SIGNAL#getComponent_reference()
   * @see #getMacro_SIGNAL()
   * @generated
   */
  EReference getMacro_SIGNAL_Component_reference();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT <em>Macro EVENT</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Macro EVENT</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT
   * @generated
   */
  EClass getMacro_EVENT();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_EVENT#getComponent_reference()
   * @see #getMacro_EVENT()
   * @generated
   */
  EReference getMacro_EVENT_Component_reference();

  /**
   * Returns the meta object for class '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE <em>Macro CHANGE</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Macro CHANGE</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE
   * @generated
   */
  EClass getMacro_CHANGE();

  /**
   * Returns the meta object for the containment reference '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE#getComponent_reference <em>Component reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Component reference</em>'.
   * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.Macro_CHANGE#getComponent_reference()
   * @see #getMacro_CHANGE()
   * @generated
   */
  EReference getMacro_CHANGE_Component_reference();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  StatetransitionguardexpressionFactory getStatetransitionguardexpressionFactory();

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
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.expressionImpl <em>expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.expressionImpl
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getexpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getexpression();

    /**
     * The meta object literal for the '<em><b>Simple Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION__SIMPLE_EXPR = eINSTANCE.getexpression_Simple_Expr();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl <em>primary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.primaryImpl
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getprimary()
     * @generated
     */
    EClass PRIMARY = eINSTANCE.getprimary();

    /**
     * The meta object literal for the '<em><b>Name Function</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__NAME_FUNCTION = eINSTANCE.getprimary_Name_Function();

    /**
     * The meta object literal for the '<em><b>Initial ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__INITIAL_REF = eINSTANCE.getprimary_Initial_ref();

    /**
     * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__EXPR = eINSTANCE.getprimary_Expr();

    /**
     * The meta object literal for the '<em><b>Mac A</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__MAC_A = eINSTANCE.getprimary_Mac_A();

    /**
     * The meta object literal for the '<em><b>Mac E</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__MAC_E = eINSTANCE.getprimary_Mac_E();

    /**
     * The meta object literal for the '<em><b>Mac C</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__MAC_C = eINSTANCE.getprimary_Mac_C();

    /**
     * The meta object literal for the '<em><b>Mac S</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__MAC_S = eINSTANCE.getprimary_Mac_S();

    /**
     * The meta object literal for the '<em><b>Else</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__ELSE = eINSTANCE.getprimary_Else();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_AfterImpl <em>Macro After</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_AfterImpl
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_After()
     * @generated
     */
    EClass MACRO_AFTER = eINSTANCE.getMacro_After();

    /**
     * The meta object literal for the '<em><b>Int</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MACRO_AFTER__INT = eINSTANCE.getMacro_After_Int();

    /**
     * The meta object literal for the '<em><b>Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MACRO_AFTER__NUM = eINSTANCE.getMacro_After_Num();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MACRO_AFTER__COMPONENT_REFERENCE = eINSTANCE.getMacro_After_Component_reference();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_BEFOREImpl <em>Macro BEFORE</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_BEFOREImpl
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_BEFORE()
     * @generated
     */
    EClass MACRO_BEFORE = eINSTANCE.getMacro_BEFORE();

    /**
     * The meta object literal for the '<em><b>Int</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MACRO_BEFORE__INT = eINSTANCE.getMacro_BEFORE_Int();

    /**
     * The meta object literal for the '<em><b>Num</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MACRO_BEFORE__NUM = eINSTANCE.getMacro_BEFORE_Num();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MACRO_BEFORE__COMPONENT_REFERENCE = eINSTANCE.getMacro_BEFORE_Component_reference();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_SIGNALImpl <em>Macro SIGNAL</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_SIGNALImpl
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_SIGNAL()
     * @generated
     */
    EClass MACRO_SIGNAL = eINSTANCE.getMacro_SIGNAL();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MACRO_SIGNAL__COMPONENT_REFERENCE = eINSTANCE.getMacro_SIGNAL_Component_reference();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_EVENTImpl <em>Macro EVENT</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_EVENTImpl
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_EVENT()
     * @generated
     */
    EClass MACRO_EVENT = eINSTANCE.getMacro_EVENT();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MACRO_EVENT__COMPONENT_REFERENCE = eINSTANCE.getMacro_EVENT_Component_reference();

    /**
     * The meta object literal for the '{@link org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_CHANGEImpl <em>Macro CHANGE</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.Macro_CHANGEImpl
     * @see org.openmodelica.modelicaml.editor.xtext.state.statetransitionguardexpression.impl.StatetransitionguardexpressionPackageImpl#getMacro_CHANGE()
     * @generated
     */
    EClass MACRO_CHANGE = eINSTANCE.getMacro_CHANGE();

    /**
     * The meta object literal for the '<em><b>Component reference</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MACRO_CHANGE__COMPONENT_REFERENCE = eINSTANCE.getMacro_CHANGE_Component_reference();

  }

} //StatetransitionguardexpressionPackage
