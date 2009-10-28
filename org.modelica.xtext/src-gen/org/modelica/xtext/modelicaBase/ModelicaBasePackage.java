/**
 * <copyright>
 * </copyright>
 *
 */
package org.modelica.xtext.modelicaBase;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.modelica.xtext.modelicaBase.ModelicaBaseFactory
 * @model kind="package"
 * @generated
 */
public interface ModelicaBasePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "modelicaBase";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.modelica.org/xtext/ModelicaBase";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "modelicaBase";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ModelicaBasePackage eINSTANCE = org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl.init();

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.StorageUnitImpl <em>Storage Unit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.StorageUnitImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getStorageUnit()
   * @generated
   */
  int STORAGE_UNIT = 0;

  /**
   * The feature id for the '<em><b>Class Definition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STORAGE_UNIT__CLASS_DEFINITION = 0;

  /**
   * The number of structural features of the '<em>Storage Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STORAGE_UNIT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.EntityDefinitionImpl <em>Entity Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.EntityDefinitionImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getEntityDefinition()
   * @generated
   */
  int ENTITY_DEFINITION = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DEFINITION__NAME = 0;

  /**
   * The number of structural features of the '<em>Entity Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_DEFINITION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl <em>Class Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassDefinition()
   * @generated
   */
  int CLASS_DEFINITION = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__NAME = ENTITY_DEFINITION__NAME;

  /**
   * The feature id for the '<em><b>Encapsulated</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__ENCAPSULATED = ENTITY_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Partial</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__PARTIAL = ENTITY_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Expandable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__EXPANDABLE = ENTITY_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Class Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__CLASS_TYPE = ENTITY_DEFINITION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__COMMENT = ENTITY_DEFINITION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__MODIFICATION = ENTITY_DEFINITION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Elem List</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__ELEM_LIST = ENTITY_DEFINITION_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Initial Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES = ENTITY_DEFINITION_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Initial Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES = ENTITY_DEFINITION_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__EQUATION_CLAUSES = ENTITY_DEFINITION_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__ALGORITHM_CLAUSES = ENTITY_DEFINITION_FEATURE_COUNT + 10;

  /**
   * The feature id for the '<em><b>Ext Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION__EXT_CLAUSE = ENTITY_DEFINITION_FEATURE_COUNT + 11;

  /**
   * The number of structural features of the '<em>Class Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_DEFINITION_FEATURE_COUNT = ENTITY_DEFINITION_FEATURE_COUNT + 12;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.PartialDerivativeImpl <em>Partial Derivative</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.PartialDerivativeImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getPartialDerivative()
   * @generated
   */
  int PARTIAL_DERIVATIVE = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARTIAL_DERIVATIVE__NAME = ENTITY_DEFINITION__NAME;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARTIAL_DERIVATIVE__COMMENT = ENTITY_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Partial Derivative</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARTIAL_DERIVATIVE_FEATURE_COUNT = ENTITY_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl <em>Class Modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ClassModificationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassModification()
   * @generated
   */
  int CLASS_MODIFICATION = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__NAME = ENTITY_DEFINITION__NAME;

  /**
   * The feature id for the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__EXPRESSION = ENTITY_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Class Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__CLASS_TYPE = ENTITY_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Type Specifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__TYPE_SPECIFIER = ENTITY_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__MODIFICATION = ENTITY_DEFINITION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__COMMENT = ENTITY_DEFINITION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION__ARGUMENTS = ENTITY_DEFINITION_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Class Modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_MODIFICATION_FEATURE_COUNT = ENTITY_DEFINITION_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ClassOverloadingImpl <em>Class Overloading</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ClassOverloadingImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassOverloading()
   * @generated
   */
  int CLASS_OVERLOADING = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_OVERLOADING__NAME = ENTITY_DEFINITION__NAME;

  /**
   * The feature id for the '<em><b>Class Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_OVERLOADING__CLASS_TYPE = ENTITY_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_OVERLOADING__COMMENT = ENTITY_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Class Overloading</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_OVERLOADING_FEATURE_COUNT = ENTITY_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.EnumerationImpl <em>Enumeration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.EnumerationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getEnumeration()
   * @generated
   */
  int ENUMERATION = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUMERATION__NAME = ENTITY_DEFINITION__NAME;

  /**
   * The feature id for the '<em><b>Class Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUMERATION__CLASS_TYPE = ENTITY_DEFINITION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Ident</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUMERATION__IDENT = ENTITY_DEFINITION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Enum Comments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUMERATION__ENUM_COMMENTS = ENTITY_DEFINITION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUMERATION__COMMENT = ENTITY_DEFINITION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Enumeration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUMERATION_FEATURE_COUNT = ENTITY_DEFINITION_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ExternalClauseImpl <em>External Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ExternalClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getExternalClause()
   * @generated
   */
  int EXTERNAL_CLAUSE = 7;

  /**
   * The feature id for the '<em><b>Language Specification</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION = 0;

  /**
   * The feature id for the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_CLAUSE__ANNOTATION = 1;

  /**
   * The feature id for the '<em><b>Ext Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_CLAUSE__EXT_ANNOTATION = 2;

  /**
   * The number of structural features of the '<em>External Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTERNAL_CLAUSE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ElementListImpl <em>Element List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ElementListImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementList()
   * @generated
   */
  int ELEMENT_LIST = 8;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_LIST__ELEMENTS = 0;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_LIST__ANNOTATIONS = 1;

  /**
   * The number of structural features of the '<em>Element List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ClassElementImpl <em>Class Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ClassElementImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassElement()
   * @generated
   */
  int CLASS_ELEMENT = 9;

  /**
   * The number of structural features of the '<em>Class Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ReplaceableElementClauseImpl <em>Replaceable Element Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ReplaceableElementClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getReplaceableElementClause()
   * @generated
   */
  int REPLACEABLE_ELEMENT_CLAUSE = 10;

  /**
   * The feature id for the '<em><b>Entity</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPLACEABLE_ELEMENT_CLAUSE__ENTITY = CLASS_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Constrain</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN = CLASS_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Replaceable Element Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPLACEABLE_ELEMENT_CLAUSE_FEATURE_COUNT = CLASS_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ImportClauseImpl <em>Import Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ImportClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getImportClause()
   * @generated
   */
  int IMPORT_CLAUSE = 11;

  /**
   * The feature id for the '<em><b>As Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE__AS_NAME = CLASS_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE__NAME = CLASS_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE__COMMENT = CLASS_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Import Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_CLAUSE_FEATURE_COUNT = CLASS_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ExtendsClauseImpl <em>Extends Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ExtendsClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getExtendsClause()
   * @generated
   */
  int EXTENDS_CLAUSE = 12;

  /**
   * The feature id for the '<em><b>Base Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__BASE_CLASS = CLASS_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__MODIFICATION = CLASS_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE__ANNOTATION = CLASS_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Extends Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_CLAUSE_FEATURE_COUNT = CLASS_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ConstrainingClauseImpl <em>Constraining Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ConstrainingClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getConstrainingClause()
   * @generated
   */
  int CONSTRAINING_CLAUSE = 13;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINING_CLAUSE__NAME = 0;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINING_CLAUSE__MODIFICATION = 1;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINING_CLAUSE__COMMENT = 2;

  /**
   * The number of structural features of the '<em>Constraining Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINING_CLAUSE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ComponentClauseImpl <em>Component Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ComponentClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getComponentClause()
   * @generated
   */
  int COMPONENT_CLAUSE = 14;

  /**
   * The feature id for the '<em><b>Type Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_CLAUSE__TYPE_PREFIX = CLASS_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type Specifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_CLAUSE__TYPE_SPECIFIER = CLASS_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Declarations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_CLAUSE__DECLARATIONS = CLASS_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Component Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_CLAUSE_FEATURE_COUNT = CLASS_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.TypeSpecifierImpl <em>Type Specifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.TypeSpecifierImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getTypeSpecifier()
   * @generated
   */
  int TYPE_SPECIFIER = 15;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER__NAME = 0;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER__SUBSCRIPTS = 1;

  /**
   * The number of structural features of the '<em>Type Specifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SPECIFIER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl <em>Component Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getComponentDeclaration()
   * @generated
   */
  int COMPONENT_DECLARATION = 16;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__SUBSCRIPTS = 0;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__MODIFICATION = 1;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__CONDITION = 2;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION__COMMENT = 3;

  /**
   * The number of structural features of the '<em>Component Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMPONENT_DECLARATION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ModificationImpl <em>Modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ModificationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getModification()
   * @generated
   */
  int MODIFICATION = 17;

  /**
   * The feature id for the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION__EXPRESSION = 0;

  /**
   * The number of structural features of the '<em>Modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODIFICATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.SimpleModificationImpl <em>Simple Modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.SimpleModificationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getSimpleModification()
   * @generated
   */
  int SIMPLE_MODIFICATION = 18;

  /**
   * The feature id for the '<em><b>Expression</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_MODIFICATION__EXPRESSION = MODIFICATION__EXPRESSION;

  /**
   * The number of structural features of the '<em>Simple Modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_MODIFICATION_FEATURE_COUNT = MODIFICATION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.argumentImpl <em>argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.argumentImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getargument()
   * @generated
   */
  int ARGUMENT = 19;

  /**
   * The number of structural features of the '<em>argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ElementModificationImpl <em>Element Modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ElementModificationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementModification()
   * @generated
   */
  int ELEMENT_MODIFICATION = 20;

  /**
   * The feature id for the '<em><b>Componet Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION__COMPONET_REF = ARGUMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION__MODIFICATION = ARGUMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION__COMMENT = ARGUMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Element Modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_MODIFICATION_FEATURE_COUNT = ARGUMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ElementRedeclarationImpl <em>Element Redeclaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ElementRedeclarationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementRedeclaration()
   * @generated
   */
  int ELEMENT_REDECLARATION = 21;

  /**
   * The number of structural features of the '<em>Element Redeclaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REDECLARATION_FEATURE_COUNT = ARGUMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ElementReplaceableClauseImpl <em>Element Replaceable Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ElementReplaceableClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementReplaceableClause()
   * @generated
   */
  int ELEMENT_REPLACEABLE_CLAUSE = 22;

  /**
   * The feature id for the '<em><b>Element</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REPLACEABLE_CLAUSE__ELEMENT = ARGUMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Constraint</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT = ARGUMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Element Replaceable Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REPLACEABLE_CLAUSE_FEATURE_COUNT = ARGUMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl <em>Element Component Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementComponentClause()
   * @generated
   */
  int ELEMENT_COMPONENT_CLAUSE = 23;

  /**
   * The feature id for the '<em><b>Type Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX = ELEMENT_REDECLARATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type Specifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER = ELEMENT_REDECLARATION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Subscripts</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS = ELEMENT_REDECLARATION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Modification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_COMPONENT_CLAUSE__MODIFICATION = ELEMENT_REDECLARATION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_COMPONENT_CLAUSE__COMMENT = ELEMENT_REDECLARATION_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Element Component Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_COMPONENT_CLAUSE_FEATURE_COUNT = ELEMENT_REDECLARATION_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.initial_equation_rule_clauseImpl <em>initial equation rule clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.initial_equation_rule_clauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getinitial_equation_rule_clause()
   * @generated
   */
  int INITIAL_EQUATION_RULE_CLAUSE = 26;

  /**
   * The number of structural features of the '<em>initial equation rule clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_EQUATION_RULE_CLAUSE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.EquationClauseImpl <em>Equation Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.EquationClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getEquationClause()
   * @generated
   */
  int EQUATION_CLAUSE = 24;

  /**
   * The feature id for the '<em><b>Equation Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_CLAUSE__EQUATION_RULES = INITIAL_EQUATION_RULE_CLAUSE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_CLAUSE__ANNOTATIONS = INITIAL_EQUATION_RULE_CLAUSE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Equation Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_CLAUSE_FEATURE_COUNT = INITIAL_EQUATION_RULE_CLAUSE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.initial_algorithm_clauseImpl <em>initial algorithm clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.initial_algorithm_clauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getinitial_algorithm_clause()
   * @generated
   */
  int INITIAL_ALGORITHM_CLAUSE = 27;

  /**
   * The number of structural features of the '<em>initial algorithm clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIAL_ALGORITHM_CLAUSE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.algorithm_clauseImpl <em>algorithm clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.algorithm_clauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getalgorithm_clause()
   * @generated
   */
  int ALGORITHM_CLAUSE = 25;

  /**
   * The feature id for the '<em><b>Algorithm Rules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_CLAUSE__ALGORITHM_RULES = INITIAL_ALGORITHM_CLAUSE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_CLAUSE__ANNOTATIONS = INITIAL_ALGORITHM_CLAUSE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>algorithm clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_CLAUSE_FEATURE_COUNT = INITIAL_ALGORITHM_CLAUSE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.equation_ruleImpl <em>equation rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.equation_ruleImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getequation_rule()
   * @generated
   */
  int EQUATION_RULE = 28;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_RULE__COMMENT = 0;

  /**
   * The number of structural features of the '<em>equation rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_RULE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.algorithmImpl <em>algorithm</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.algorithmImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getalgorithm()
   * @generated
   */
  int ALGORITHM = 29;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM__COMMENT = 0;

  /**
   * The number of structural features of the '<em>algorithm</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ConditionalEquationRuleImpl <em>Conditional Equation Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ConditionalEquationRuleImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getConditionalEquationRule()
   * @generated
   */
  int CONDITIONAL_EQUATION_RULE = 30;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EQUATION_RULE__COMMENT = EQUATION_RULE__COMMENT;

  /**
   * The feature id for the '<em><b>Then Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES = EQUATION_RULE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Elseif Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES = EQUATION_RULE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES = EQUATION_RULE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Conditional Equation Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EQUATION_RULE_FEATURE_COUNT = EQUATION_RULE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl <em>Condition Algorithm Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getConditionAlgorithmRule()
   * @generated
   */
  int CONDITION_ALGORITHM_RULE = 31;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_ALGORITHM_RULE__COMMENT = ALGORITHM__COMMENT;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_ALGORITHM_RULE__CONDITION = ALGORITHM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES = ALGORITHM_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Elseif Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES = ALGORITHM_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Else Algotithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES = ALGORITHM_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Condition Algorithm Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_ALGORITHM_RULE_FEATURE_COUNT = ALGORITHM_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ForEquationClauseImpl <em>For Equation Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ForEquationClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getForEquationClause()
   * @generated
   */
  int FOR_EQUATION_CLAUSE = 32;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION_CLAUSE__COMMENT = EQUATION_RULE__COMMENT;

  /**
   * The feature id for the '<em><b>For Indices</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION_CLAUSE__FOR_INDICES = EQUATION_RULE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION_CLAUSE__EQUATION_CLAUSES = EQUATION_RULE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>For Equation Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_EQUATION_CLAUSE_FEATURE_COUNT = EQUATION_RULE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ForAlgorithmClauseImpl <em>For Algorithm Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ForAlgorithmClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getForAlgorithmClause()
   * @generated
   */
  int FOR_ALGORITHM_CLAUSE = 33;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_ALGORITHM_CLAUSE__COMMENT = ALGORITHM__COMMENT;

  /**
   * The feature id for the '<em><b>For Indices</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_ALGORITHM_CLAUSE__FOR_INDICES = ALGORITHM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES = ALGORITHM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>For Algorithm Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_ALGORITHM_CLAUSE_FEATURE_COUNT = ALGORITHM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.WhileClauseImpl <em>While Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.WhileClauseImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getWhileClause()
   * @generated
   */
  int WHILE_CLAUSE = 34;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_CLAUSE__COMMENT = ALGORITHM__COMMENT;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_CLAUSE__CONDITION = ALGORITHM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_CLAUSE__ALGORITHM_CLAUSES = ALGORITHM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>While Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHILE_CLAUSE_FEATURE_COUNT = ALGORITHM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.when_clause_eImpl <em>when clause e</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.when_clause_eImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getwhen_clause_e()
   * @generated
   */
  int WHEN_CLAUSE_E = 35;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_E__COMMENT = EQUATION_RULE__COMMENT;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_E__CONDITION = EQUATION_RULE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_E__THEN_EQUATION_CLAUSES = EQUATION_RULE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else When Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_E__ELSE_WHEN_CLAUSES = EQUATION_RULE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>when clause e</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_E_FEATURE_COUNT = EQUATION_RULE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.else_when_eImpl <em>else when e</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.else_when_eImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getelse_when_e()
   * @generated
   */
  int ELSE_WHEN_E = 36;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_WHEN_E__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Then Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_WHEN_E__THEN_EQUATION_CLAUSES = 1;

  /**
   * The number of structural features of the '<em>else when e</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_WHEN_E_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.when_clause_aImpl <em>when clause a</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.when_clause_aImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getwhen_clause_a()
   * @generated
   */
  int WHEN_CLAUSE_A = 37;

  /**
   * The feature id for the '<em><b>Comment</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_A__COMMENT = ALGORITHM__COMMENT;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_A__CONDITION = ALGORITHM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES = ALGORITHM_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else When Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES = ALGORITHM_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>when clause a</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHEN_CLAUSE_A_FEATURE_COUNT = ALGORITHM_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.else_when_aImpl <em>else when a</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.else_when_aImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getelse_when_a()
   * @generated
   */
  int ELSE_WHEN_A = 38;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_WHEN_A__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_WHEN_A__THEN_ALGORITHM_CLAUSES = 1;

  /**
   * The number of structural features of the '<em>else when a</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELSE_WHEN_A_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.equation_rule_elseifImpl <em>equation rule elseif</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.equation_rule_elseifImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getequation_rule_elseif()
   * @generated
   */
  int EQUATION_RULE_ELSEIF = 39;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_RULE_ELSEIF__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Then Equation Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES = 1;

  /**
   * The number of structural features of the '<em>equation rule elseif</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EQUATION_RULE_ELSEIF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.algorithm_elseifImpl <em>algorithm elseif</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.algorithm_elseifImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getalgorithm_elseif()
   * @generated
   */
  int ALGORITHM_ELSEIF = 40;

  /**
   * The feature id for the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_ELSEIF__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES = 1;

  /**
   * The number of structural features of the '<em>algorithm elseif</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ALGORITHM_ELSEIF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.CommentImpl <em>Comment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.CommentImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getComment()
   * @generated
   */
  int COMMENT = 41;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMENT__STRING = 0;

  /**
   * The feature id for the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMENT__ANNOTATION = 1;

  /**
   * The number of structural features of the '<em>Comment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.AnnotationImpl <em>Annotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.AnnotationImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getAnnotation()
   * @generated
   */
  int ANNOTATION = 42;

  /**
   * The number of structural features of the '<em>Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.PublicElementListImpl <em>Public Element List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.PublicElementListImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getPublicElementList()
   * @generated
   */
  int PUBLIC_ELEMENT_LIST = 43;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PUBLIC_ELEMENT_LIST__ELEMENTS = ELEMENT_LIST__ELEMENTS;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PUBLIC_ELEMENT_LIST__ANNOTATIONS = ELEMENT_LIST__ANNOTATIONS;

  /**
   * The number of structural features of the '<em>Public Element List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PUBLIC_ELEMENT_LIST_FEATURE_COUNT = ELEMENT_LIST_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.impl.ProtectesElementListImpl <em>Protectes Element List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.impl.ProtectesElementListImpl
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getProtectesElementList()
   * @generated
   */
  int PROTECTES_ELEMENT_LIST = 44;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROTECTES_ELEMENT_LIST__ELEMENTS = ELEMENT_LIST__ELEMENTS;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROTECTES_ELEMENT_LIST__ANNOTATIONS = ELEMENT_LIST__ANNOTATIONS;

  /**
   * The number of structural features of the '<em>Protectes Element List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROTECTES_ELEMENT_LIST_FEATURE_COUNT = ELEMENT_LIST_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.modelica.xtext.modelicaBase.ClassType <em>Class Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.modelica.xtext.modelicaBase.ClassType
   * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassType()
   * @generated
   */
  int CLASS_TYPE = 45;


  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.StorageUnit <em>Storage Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Storage Unit</em>'.
   * @see org.modelica.xtext.modelicaBase.StorageUnit
   * @generated
   */
  EClass getStorageUnit();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.StorageUnit#getClassDefinition <em>Class Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Class Definition</em>'.
   * @see org.modelica.xtext.modelicaBase.StorageUnit#getClassDefinition()
   * @see #getStorageUnit()
   * @generated
   */
  EReference getStorageUnit_ClassDefinition();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.EntityDefinition <em>Entity Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity Definition</em>'.
   * @see org.modelica.xtext.modelicaBase.EntityDefinition
   * @generated
   */
  EClass getEntityDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.EntityDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.modelica.xtext.modelicaBase.EntityDefinition#getName()
   * @see #getEntityDefinition()
   * @generated
   */
  EAttribute getEntityDefinition_Name();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ClassDefinition <em>Class Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Class Definition</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition
   * @generated
   */
  EClass getClassDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ClassDefinition#isEncapsulated <em>Encapsulated</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Encapsulated</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#isEncapsulated()
   * @see #getClassDefinition()
   * @generated
   */
  EAttribute getClassDefinition_Encapsulated();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ClassDefinition#isPartial <em>Partial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Partial</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#isPartial()
   * @see #getClassDefinition()
   * @generated
   */
  EAttribute getClassDefinition_Partial();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ClassDefinition#isExpandable <em>Expandable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Expandable</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#isExpandable()
   * @see #getClassDefinition()
   * @generated
   */
  EAttribute getClassDefinition_Expandable();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Type</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getClassType()
   * @see #getClassDefinition()
   * @generated
   */
  EAttribute getClassDefinition_ClassType();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getComment()
   * @see #getClassDefinition()
   * @generated
   */
  EAttribute getClassDefinition_Comment();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getModification()
   * @see #getClassDefinition()
   * @generated
   */
  EReference getClassDefinition_Modification();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getElemList <em>Elem List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elem List</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getElemList()
   * @see #getClassDefinition()
   * @generated
   */
  EReference getClassDefinition_ElemList();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getInitialEquationClauses <em>Initial Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initial Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getInitialEquationClauses()
   * @see #getClassDefinition()
   * @generated
   */
  EReference getClassDefinition_InitialEquationClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getInitialAlgorithmClauses <em>Initial Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initial Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getInitialAlgorithmClauses()
   * @see #getClassDefinition()
   * @generated
   */
  EReference getClassDefinition_InitialAlgorithmClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getEquationClauses <em>Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getEquationClauses()
   * @see #getClassDefinition()
   * @generated
   */
  EReference getClassDefinition_EquationClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getAlgorithmClauses <em>Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getAlgorithmClauses()
   * @see #getClassDefinition()
   * @generated
   */
  EReference getClassDefinition_AlgorithmClauses();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ClassDefinition#getExtClause <em>Ext Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ext Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassDefinition#getExtClause()
   * @see #getClassDefinition()
   * @generated
   */
  EReference getClassDefinition_ExtClause();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.PartialDerivative <em>Partial Derivative</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Partial Derivative</em>'.
   * @see org.modelica.xtext.modelicaBase.PartialDerivative
   * @generated
   */
  EClass getPartialDerivative();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.PartialDerivative#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.PartialDerivative#getComment()
   * @see #getPartialDerivative()
   * @generated
   */
  EReference getPartialDerivative_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ClassModification <em>Class Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Class Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassModification
   * @generated
   */
  EClass getClassModification();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ClassModification#getClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Type</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassModification#getClassType()
   * @see #getClassModification()
   * @generated
   */
  EAttribute getClassModification_ClassType();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ClassModification#getTypeSpecifier <em>Type Specifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Specifier</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassModification#getTypeSpecifier()
   * @see #getClassModification()
   * @generated
   */
  EReference getClassModification_TypeSpecifier();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ClassModification#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassModification#getModification()
   * @see #getClassModification()
   * @generated
   */
  EReference getClassModification_Modification();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ClassModification#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassModification#getComment()
   * @see #getClassModification()
   * @generated
   */
  EReference getClassModification_Comment();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ClassModification#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassModification#getArguments()
   * @see #getClassModification()
   * @generated
   */
  EReference getClassModification_Arguments();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ClassOverloading <em>Class Overloading</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Class Overloading</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassOverloading
   * @generated
   */
  EClass getClassOverloading();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ClassOverloading#getClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Type</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassOverloading#getClassType()
   * @see #getClassOverloading()
   * @generated
   */
  EAttribute getClassOverloading_ClassType();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ClassOverloading#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassOverloading#getComment()
   * @see #getClassOverloading()
   * @generated
   */
  EReference getClassOverloading_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.Enumeration <em>Enumeration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration</em>'.
   * @see org.modelica.xtext.modelicaBase.Enumeration
   * @generated
   */
  EClass getEnumeration();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.Enumeration#getClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class Type</em>'.
   * @see org.modelica.xtext.modelicaBase.Enumeration#getClassType()
   * @see #getEnumeration()
   * @generated
   */
  EAttribute getEnumeration_ClassType();

  /**
   * Returns the meta object for the attribute list '{@link org.modelica.xtext.modelicaBase.Enumeration#getIdent <em>Ident</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Ident</em>'.
   * @see org.modelica.xtext.modelicaBase.Enumeration#getIdent()
   * @see #getEnumeration()
   * @generated
   */
  EAttribute getEnumeration_Ident();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.Enumeration#getEnumComments <em>Enum Comments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Enum Comments</em>'.
   * @see org.modelica.xtext.modelicaBase.Enumeration#getEnumComments()
   * @see #getEnumeration()
   * @generated
   */
  EReference getEnumeration_EnumComments();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.Enumeration#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.Enumeration#getComment()
   * @see #getEnumeration()
   * @generated
   */
  EReference getEnumeration_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ExternalClause <em>External Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>External Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ExternalClause
   * @generated
   */
  EClass getExternalClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ExternalClause#getLanguageSpecification <em>Language Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Language Specification</em>'.
   * @see org.modelica.xtext.modelicaBase.ExternalClause#getLanguageSpecification()
   * @see #getExternalClause()
   * @generated
   */
  EAttribute getExternalClause_LanguageSpecification();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ExternalClause#getAnnotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Annotation</em>'.
   * @see org.modelica.xtext.modelicaBase.ExternalClause#getAnnotation()
   * @see #getExternalClause()
   * @generated
   */
  EReference getExternalClause_Annotation();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ExternalClause#getExtAnnotation <em>Ext Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ext Annotation</em>'.
   * @see org.modelica.xtext.modelicaBase.ExternalClause#getExtAnnotation()
   * @see #getExternalClause()
   * @generated
   */
  EReference getExternalClause_ExtAnnotation();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ElementList <em>Element List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element List</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementList
   * @generated
   */
  EClass getElementList();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ElementList#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementList#getElements()
   * @see #getElementList()
   * @generated
   */
  EReference getElementList_Elements();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ElementList#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementList#getAnnotations()
   * @see #getElementList()
   * @generated
   */
  EReference getElementList_Annotations();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ClassElement <em>Class Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Class Element</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassElement
   * @generated
   */
  EClass getClassElement();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause <em>Replaceable Element Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Replaceable Element Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ReplaceableElementClause
   * @generated
   */
  EClass getReplaceableElementClause();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause#getEntity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Entity</em>'.
   * @see org.modelica.xtext.modelicaBase.ReplaceableElementClause#getEntity()
   * @see #getReplaceableElementClause()
   * @generated
   */
  EReference getReplaceableElementClause_Entity();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ReplaceableElementClause#getConstrain <em>Constrain</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constrain</em>'.
   * @see org.modelica.xtext.modelicaBase.ReplaceableElementClause#getConstrain()
   * @see #getReplaceableElementClause()
   * @generated
   */
  EReference getReplaceableElementClause_Constrain();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ImportClause <em>Import Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ImportClause
   * @generated
   */
  EClass getImportClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ImportClause#getAsName <em>As Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>As Name</em>'.
   * @see org.modelica.xtext.modelicaBase.ImportClause#getAsName()
   * @see #getImportClause()
   * @generated
   */
  EAttribute getImportClause_AsName();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ImportClause#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.modelica.xtext.modelicaBase.ImportClause#getName()
   * @see #getImportClause()
   * @generated
   */
  EAttribute getImportClause_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ImportClause#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ImportClause#getComment()
   * @see #getImportClause()
   * @generated
   */
  EReference getImportClause_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ExtendsClause <em>Extends Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extends Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ExtendsClause
   * @generated
   */
  EClass getExtendsClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ExtendsClause#getBaseClass <em>Base Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Base Class</em>'.
   * @see org.modelica.xtext.modelicaBase.ExtendsClause#getBaseClass()
   * @see #getExtendsClause()
   * @generated
   */
  EAttribute getExtendsClause_BaseClass();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ExtendsClause#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ExtendsClause#getModification()
   * @see #getExtendsClause()
   * @generated
   */
  EReference getExtendsClause_Modification();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ExtendsClause#getAnnotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Annotation</em>'.
   * @see org.modelica.xtext.modelicaBase.ExtendsClause#getAnnotation()
   * @see #getExtendsClause()
   * @generated
   */
  EReference getExtendsClause_Annotation();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ConstrainingClause <em>Constraining Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraining Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ConstrainingClause
   * @generated
   */
  EClass getConstrainingClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ConstrainingClause#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.modelica.xtext.modelicaBase.ConstrainingClause#getName()
   * @see #getConstrainingClause()
   * @generated
   */
  EAttribute getConstrainingClause_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ConstrainingClause#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ConstrainingClause#getModification()
   * @see #getConstrainingClause()
   * @generated
   */
  EReference getConstrainingClause_Modification();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ConstrainingClause#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ConstrainingClause#getComment()
   * @see #getConstrainingClause()
   * @generated
   */
  EReference getConstrainingClause_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ComponentClause <em>Component Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentClause
   * @generated
   */
  EClass getComponentClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ComponentClause#getTypePrefix <em>Type Prefix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type Prefix</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentClause#getTypePrefix()
   * @see #getComponentClause()
   * @generated
   */
  EAttribute getComponentClause_TypePrefix();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ComponentClause#getTypeSpecifier <em>Type Specifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Specifier</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentClause#getTypeSpecifier()
   * @see #getComponentClause()
   * @generated
   */
  EReference getComponentClause_TypeSpecifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ComponentClause#getDeclarations <em>Declarations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Declarations</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentClause#getDeclarations()
   * @see #getComponentClause()
   * @generated
   */
  EReference getComponentClause_Declarations();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.TypeSpecifier <em>Type Specifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Specifier</em>'.
   * @see org.modelica.xtext.modelicaBase.TypeSpecifier
   * @generated
   */
  EClass getTypeSpecifier();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.TypeSpecifier#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.modelica.xtext.modelicaBase.TypeSpecifier#getName()
   * @see #getTypeSpecifier()
   * @generated
   */
  EAttribute getTypeSpecifier_Name();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.TypeSpecifier#getSubscripts <em>Subscripts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Subscripts</em>'.
   * @see org.modelica.xtext.modelicaBase.TypeSpecifier#getSubscripts()
   * @see #getTypeSpecifier()
   * @generated
   */
  EAttribute getTypeSpecifier_Subscripts();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration <em>Component Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Component Declaration</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentDeclaration
   * @generated
   */
  EClass getComponentDeclaration();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getSubscripts <em>Subscripts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Subscripts</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentDeclaration#getSubscripts()
   * @see #getComponentDeclaration()
   * @generated
   */
  EAttribute getComponentDeclaration_Subscripts();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentDeclaration#getModification()
   * @see #getComponentDeclaration()
   * @generated
   */
  EReference getComponentDeclaration_Modification();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentDeclaration#getCondition()
   * @see #getComponentDeclaration()
   * @generated
   */
  EAttribute getComponentDeclaration_Condition();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ComponentDeclaration#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ComponentDeclaration#getComment()
   * @see #getComponentDeclaration()
   * @generated
   */
  EReference getComponentDeclaration_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.Modification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.Modification
   * @generated
   */
  EClass getModification();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.Modification#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Expression</em>'.
   * @see org.modelica.xtext.modelicaBase.Modification#getExpression()
   * @see #getModification()
   * @generated
   */
  EAttribute getModification_Expression();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.SimpleModification <em>Simple Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.SimpleModification
   * @generated
   */
  EClass getSimpleModification();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.argument <em>argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>argument</em>'.
   * @see org.modelica.xtext.modelicaBase.argument
   * @generated
   */
  EClass getargument();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ElementModification <em>Element Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementModification
   * @generated
   */
  EClass getElementModification();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ElementModification#getComponetRef <em>Componet Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Componet Ref</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementModification#getComponetRef()
   * @see #getElementModification()
   * @generated
   */
  EAttribute getElementModification_ComponetRef();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ElementModification#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementModification#getModification()
   * @see #getElementModification()
   * @generated
   */
  EReference getElementModification_Modification();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ElementModification#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementModification#getComment()
   * @see #getElementModification()
   * @generated
   */
  EAttribute getElementModification_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ElementRedeclaration <em>Element Redeclaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Redeclaration</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementRedeclaration
   * @generated
   */
  EClass getElementRedeclaration();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause <em>Element Replaceable Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Replaceable Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementReplaceableClause
   * @generated
   */
  EClass getElementReplaceableClause();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Element</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementReplaceableClause#getElement()
   * @see #getElementReplaceableClause()
   * @generated
   */
  EReference getElementReplaceableClause_Element();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ElementReplaceableClause#getConstraint <em>Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constraint</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementReplaceableClause#getConstraint()
   * @see #getElementReplaceableClause()
   * @generated
   */
  EReference getElementReplaceableClause_Constraint();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ElementComponentClause <em>Element Component Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Component Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementComponentClause
   * @generated
   */
  EClass getElementComponentClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getTypePrefix <em>Type Prefix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type Prefix</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementComponentClause#getTypePrefix()
   * @see #getElementComponentClause()
   * @generated
   */
  EAttribute getElementComponentClause_TypePrefix();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getTypeSpecifier <em>Type Specifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Specifier</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementComponentClause#getTypeSpecifier()
   * @see #getElementComponentClause()
   * @generated
   */
  EReference getElementComponentClause_TypeSpecifier();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getSubscripts <em>Subscripts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Subscripts</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementComponentClause#getSubscripts()
   * @see #getElementComponentClause()
   * @generated
   */
  EAttribute getElementComponentClause_Subscripts();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getModification <em>Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modification</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementComponentClause#getModification()
   * @see #getElementComponentClause()
   * @generated
   */
  EReference getElementComponentClause_Modification();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.ElementComponentClause#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.ElementComponentClause#getComment()
   * @see #getElementComponentClause()
   * @generated
   */
  EReference getElementComponentClause_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.EquationClause <em>Equation Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Equation Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.EquationClause
   * @generated
   */
  EClass getEquationClause();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.EquationClause#getEquationRules <em>Equation Rules</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Equation Rules</em>'.
   * @see org.modelica.xtext.modelicaBase.EquationClause#getEquationRules()
   * @see #getEquationClause()
   * @generated
   */
  EReference getEquationClause_EquationRules();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.EquationClause#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.modelica.xtext.modelicaBase.EquationClause#getAnnotations()
   * @see #getEquationClause()
   * @generated
   */
  EReference getEquationClause_Annotations();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.algorithm_clause <em>algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>algorithm clause</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm_clause
   * @generated
   */
  EClass getalgorithm_clause();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.algorithm_clause#getAlgorithmRules <em>Algorithm Rules</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Algorithm Rules</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm_clause#getAlgorithmRules()
   * @see #getalgorithm_clause()
   * @generated
   */
  EReference getalgorithm_clause_AlgorithmRules();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.algorithm_clause#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm_clause#getAnnotations()
   * @see #getalgorithm_clause()
   * @generated
   */
  EReference getalgorithm_clause_Annotations();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.initial_equation_rule_clause <em>initial equation rule clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>initial equation rule clause</em>'.
   * @see org.modelica.xtext.modelicaBase.initial_equation_rule_clause
   * @generated
   */
  EClass getinitial_equation_rule_clause();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.initial_algorithm_clause <em>initial algorithm clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>initial algorithm clause</em>'.
   * @see org.modelica.xtext.modelicaBase.initial_algorithm_clause
   * @generated
   */
  EClass getinitial_algorithm_clause();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.equation_rule <em>equation rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>equation rule</em>'.
   * @see org.modelica.xtext.modelicaBase.equation_rule
   * @generated
   */
  EClass getequation_rule();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.equation_rule#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.equation_rule#getComment()
   * @see #getequation_rule()
   * @generated
   */
  EReference getequation_rule_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.algorithm <em>algorithm</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>algorithm</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm
   * @generated
   */
  EClass getalgorithm();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.algorithm#getComment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm#getComment()
   * @see #getalgorithm()
   * @generated
   */
  EReference getalgorithm_Comment();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule <em>Conditional Equation Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Conditional Equation Rule</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionalEquationRule
   * @generated
   */
  EClass getConditionalEquationRule();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule#getThenEquationClauses <em>Then Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionalEquationRule#getThenEquationClauses()
   * @see #getConditionalEquationRule()
   * @generated
   */
  EReference getConditionalEquationRule_ThenEquationClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule#getElseifClauses <em>Elseif Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elseif Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionalEquationRule#getElseifClauses()
   * @see #getConditionalEquationRule()
   * @generated
   */
  EReference getConditionalEquationRule_ElseifClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ConditionalEquationRule#getElseEquationClauses <em>Else Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionalEquationRule#getElseEquationClauses()
   * @see #getConditionalEquationRule()
   * @generated
   */
  EReference getConditionalEquationRule_ElseEquationClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule <em>Condition Algorithm Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Condition Algorithm Rule</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionAlgorithmRule
   * @generated
   */
  EClass getConditionAlgorithmRule();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getCondition()
   * @see #getConditionAlgorithmRule()
   * @generated
   */
  EAttribute getConditionAlgorithmRule_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getThenAlgorithmClauses()
   * @see #getConditionAlgorithmRule()
   * @generated
   */
  EReference getConditionAlgorithmRule_ThenAlgorithmClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getElseifClauses <em>Elseif Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elseif Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getElseifClauses()
   * @see #getConditionAlgorithmRule()
   * @generated
   */
  EReference getConditionAlgorithmRule_ElseifClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getElseAlgotithmClauses <em>Else Algotithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else Algotithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ConditionAlgorithmRule#getElseAlgotithmClauses()
   * @see #getConditionAlgorithmRule()
   * @generated
   */
  EReference getConditionAlgorithmRule_ElseAlgotithmClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ForEquationClause <em>For Equation Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>For Equation Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ForEquationClause
   * @generated
   */
  EClass getForEquationClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ForEquationClause#getForIndices <em>For Indices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>For Indices</em>'.
   * @see org.modelica.xtext.modelicaBase.ForEquationClause#getForIndices()
   * @see #getForEquationClause()
   * @generated
   */
  EAttribute getForEquationClause_ForIndices();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ForEquationClause#getEquationClauses <em>Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ForEquationClause#getEquationClauses()
   * @see #getForEquationClause()
   * @generated
   */
  EReference getForEquationClause_EquationClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ForAlgorithmClause <em>For Algorithm Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>For Algorithm Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.ForAlgorithmClause
   * @generated
   */
  EClass getForAlgorithmClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.ForAlgorithmClause#getForIndices <em>For Indices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>For Indices</em>'.
   * @see org.modelica.xtext.modelicaBase.ForAlgorithmClause#getForIndices()
   * @see #getForAlgorithmClause()
   * @generated
   */
  EAttribute getForAlgorithmClause_ForIndices();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.ForAlgorithmClause#getAlgorithmClauses <em>Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.ForAlgorithmClause#getAlgorithmClauses()
   * @see #getForAlgorithmClause()
   * @generated
   */
  EReference getForAlgorithmClause_AlgorithmClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.WhileClause <em>While Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>While Clause</em>'.
   * @see org.modelica.xtext.modelicaBase.WhileClause
   * @generated
   */
  EClass getWhileClause();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.WhileClause#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.WhileClause#getCondition()
   * @see #getWhileClause()
   * @generated
   */
  EAttribute getWhileClause_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.WhileClause#getAlgorithmClauses <em>Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.WhileClause#getAlgorithmClauses()
   * @see #getWhileClause()
   * @generated
   */
  EReference getWhileClause_AlgorithmClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.when_clause_e <em>when clause e</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>when clause e</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_e
   * @generated
   */
  EClass getwhen_clause_e();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.when_clause_e#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_e#getCondition()
   * @see #getwhen_clause_e()
   * @generated
   */
  EAttribute getwhen_clause_e_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.when_clause_e#getThenEquationClauses <em>Then Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_e#getThenEquationClauses()
   * @see #getwhen_clause_e()
   * @generated
   */
  EReference getwhen_clause_e_ThenEquationClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.when_clause_e#getElseWhenClauses <em>Else When Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else When Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_e#getElseWhenClauses()
   * @see #getwhen_clause_e()
   * @generated
   */
  EReference getwhen_clause_e_ElseWhenClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.else_when_e <em>else when e</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>else when e</em>'.
   * @see org.modelica.xtext.modelicaBase.else_when_e
   * @generated
   */
  EClass getelse_when_e();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.else_when_e#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.else_when_e#getCondition()
   * @see #getelse_when_e()
   * @generated
   */
  EAttribute getelse_when_e_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.else_when_e#getThenEquationClauses <em>Then Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.else_when_e#getThenEquationClauses()
   * @see #getelse_when_e()
   * @generated
   */
  EReference getelse_when_e_ThenEquationClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.when_clause_a <em>when clause a</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>when clause a</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_a
   * @generated
   */
  EClass getwhen_clause_a();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.when_clause_a#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_a#getCondition()
   * @see #getwhen_clause_a()
   * @generated
   */
  EAttribute getwhen_clause_a_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.when_clause_a#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_a#getThenAlgorithmClauses()
   * @see #getwhen_clause_a()
   * @generated
   */
  EReference getwhen_clause_a_ThenAlgorithmClauses();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.when_clause_a#getElseWhenClauses <em>Else When Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else When Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.when_clause_a#getElseWhenClauses()
   * @see #getwhen_clause_a()
   * @generated
   */
  EReference getwhen_clause_a_ElseWhenClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.else_when_a <em>else when a</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>else when a</em>'.
   * @see org.modelica.xtext.modelicaBase.else_when_a
   * @generated
   */
  EClass getelse_when_a();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.else_when_a#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.else_when_a#getCondition()
   * @see #getelse_when_a()
   * @generated
   */
  EAttribute getelse_when_a_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.else_when_a#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.else_when_a#getThenAlgorithmClauses()
   * @see #getelse_when_a()
   * @generated
   */
  EReference getelse_when_a_ThenAlgorithmClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.equation_rule_elseif <em>equation rule elseif</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>equation rule elseif</em>'.
   * @see org.modelica.xtext.modelicaBase.equation_rule_elseif
   * @generated
   */
  EClass getequation_rule_elseif();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.equation_rule_elseif#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.equation_rule_elseif#getCondition()
   * @see #getequation_rule_elseif()
   * @generated
   */
  EAttribute getequation_rule_elseif_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.equation_rule_elseif#getThenEquationClauses <em>Then Equation Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Equation Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.equation_rule_elseif#getThenEquationClauses()
   * @see #getequation_rule_elseif()
   * @generated
   */
  EReference getequation_rule_elseif_ThenEquationClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.algorithm_elseif <em>algorithm elseif</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>algorithm elseif</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm_elseif
   * @generated
   */
  EClass getalgorithm_elseif();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.algorithm_elseif#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Condition</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm_elseif#getCondition()
   * @see #getalgorithm_elseif()
   * @generated
   */
  EAttribute getalgorithm_elseif_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.modelica.xtext.modelicaBase.algorithm_elseif#getThenAlgorithmClauses <em>Then Algorithm Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then Algorithm Clauses</em>'.
   * @see org.modelica.xtext.modelicaBase.algorithm_elseif#getThenAlgorithmClauses()
   * @see #getalgorithm_elseif()
   * @generated
   */
  EReference getalgorithm_elseif_ThenAlgorithmClauses();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.Comment <em>Comment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Comment</em>'.
   * @see org.modelica.xtext.modelicaBase.Comment
   * @generated
   */
  EClass getComment();

  /**
   * Returns the meta object for the attribute '{@link org.modelica.xtext.modelicaBase.Comment#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see org.modelica.xtext.modelicaBase.Comment#getString()
   * @see #getComment()
   * @generated
   */
  EAttribute getComment_String();

  /**
   * Returns the meta object for the containment reference '{@link org.modelica.xtext.modelicaBase.Comment#getAnnotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Annotation</em>'.
   * @see org.modelica.xtext.modelicaBase.Comment#getAnnotation()
   * @see #getComment()
   * @generated
   */
  EReference getComment_Annotation();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.Annotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation</em>'.
   * @see org.modelica.xtext.modelicaBase.Annotation
   * @generated
   */
  EClass getAnnotation();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.PublicElementList <em>Public Element List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Public Element List</em>'.
   * @see org.modelica.xtext.modelicaBase.PublicElementList
   * @generated
   */
  EClass getPublicElementList();

  /**
   * Returns the meta object for class '{@link org.modelica.xtext.modelicaBase.ProtectesElementList <em>Protectes Element List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Protectes Element List</em>'.
   * @see org.modelica.xtext.modelicaBase.ProtectesElementList
   * @generated
   */
  EClass getProtectesElementList();

  /**
   * Returns the meta object for enum '{@link org.modelica.xtext.modelicaBase.ClassType <em>Class Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Class Type</em>'.
   * @see org.modelica.xtext.modelicaBase.ClassType
   * @generated
   */
  EEnum getClassType();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ModelicaBaseFactory getModelicaBaseFactory();

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
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.StorageUnitImpl <em>Storage Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.StorageUnitImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getStorageUnit()
     * @generated
     */
    EClass STORAGE_UNIT = eINSTANCE.getStorageUnit();

    /**
     * The meta object literal for the '<em><b>Class Definition</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STORAGE_UNIT__CLASS_DEFINITION = eINSTANCE.getStorageUnit_ClassDefinition();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.EntityDefinitionImpl <em>Entity Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.EntityDefinitionImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getEntityDefinition()
     * @generated
     */
    EClass ENTITY_DEFINITION = eINSTANCE.getEntityDefinition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY_DEFINITION__NAME = eINSTANCE.getEntityDefinition_Name();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl <em>Class Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ClassDefinitionImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassDefinition()
     * @generated
     */
    EClass CLASS_DEFINITION = eINSTANCE.getClassDefinition();

    /**
     * The meta object literal for the '<em><b>Encapsulated</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_DEFINITION__ENCAPSULATED = eINSTANCE.getClassDefinition_Encapsulated();

    /**
     * The meta object literal for the '<em><b>Partial</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_DEFINITION__PARTIAL = eINSTANCE.getClassDefinition_Partial();

    /**
     * The meta object literal for the '<em><b>Expandable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_DEFINITION__EXPANDABLE = eINSTANCE.getClassDefinition_Expandable();

    /**
     * The meta object literal for the '<em><b>Class Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_DEFINITION__CLASS_TYPE = eINSTANCE.getClassDefinition_ClassType();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_DEFINITION__COMMENT = eINSTANCE.getClassDefinition_Comment();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_DEFINITION__MODIFICATION = eINSTANCE.getClassDefinition_Modification();

    /**
     * The meta object literal for the '<em><b>Elem List</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_DEFINITION__ELEM_LIST = eINSTANCE.getClassDefinition_ElemList();

    /**
     * The meta object literal for the '<em><b>Initial Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_DEFINITION__INITIAL_EQUATION_CLAUSES = eINSTANCE.getClassDefinition_InitialEquationClauses();

    /**
     * The meta object literal for the '<em><b>Initial Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_DEFINITION__INITIAL_ALGORITHM_CLAUSES = eINSTANCE.getClassDefinition_InitialAlgorithmClauses();

    /**
     * The meta object literal for the '<em><b>Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_DEFINITION__EQUATION_CLAUSES = eINSTANCE.getClassDefinition_EquationClauses();

    /**
     * The meta object literal for the '<em><b>Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_DEFINITION__ALGORITHM_CLAUSES = eINSTANCE.getClassDefinition_AlgorithmClauses();

    /**
     * The meta object literal for the '<em><b>Ext Clause</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_DEFINITION__EXT_CLAUSE = eINSTANCE.getClassDefinition_ExtClause();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.PartialDerivativeImpl <em>Partial Derivative</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.PartialDerivativeImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getPartialDerivative()
     * @generated
     */
    EClass PARTIAL_DERIVATIVE = eINSTANCE.getPartialDerivative();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARTIAL_DERIVATIVE__COMMENT = eINSTANCE.getPartialDerivative_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ClassModificationImpl <em>Class Modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ClassModificationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassModification()
     * @generated
     */
    EClass CLASS_MODIFICATION = eINSTANCE.getClassModification();

    /**
     * The meta object literal for the '<em><b>Class Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_MODIFICATION__CLASS_TYPE = eINSTANCE.getClassModification_ClassType();

    /**
     * The meta object literal for the '<em><b>Type Specifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_MODIFICATION__TYPE_SPECIFIER = eINSTANCE.getClassModification_TypeSpecifier();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_MODIFICATION__MODIFICATION = eINSTANCE.getClassModification_Modification();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_MODIFICATION__COMMENT = eINSTANCE.getClassModification_Comment();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_MODIFICATION__ARGUMENTS = eINSTANCE.getClassModification_Arguments();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ClassOverloadingImpl <em>Class Overloading</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ClassOverloadingImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassOverloading()
     * @generated
     */
    EClass CLASS_OVERLOADING = eINSTANCE.getClassOverloading();

    /**
     * The meta object literal for the '<em><b>Class Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CLASS_OVERLOADING__CLASS_TYPE = eINSTANCE.getClassOverloading_ClassType();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CLASS_OVERLOADING__COMMENT = eINSTANCE.getClassOverloading_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.EnumerationImpl <em>Enumeration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.EnumerationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getEnumeration()
     * @generated
     */
    EClass ENUMERATION = eINSTANCE.getEnumeration();

    /**
     * The meta object literal for the '<em><b>Class Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUMERATION__CLASS_TYPE = eINSTANCE.getEnumeration_ClassType();

    /**
     * The meta object literal for the '<em><b>Ident</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUMERATION__IDENT = eINSTANCE.getEnumeration_Ident();

    /**
     * The meta object literal for the '<em><b>Enum Comments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUMERATION__ENUM_COMMENTS = eINSTANCE.getEnumeration_EnumComments();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUMERATION__COMMENT = eINSTANCE.getEnumeration_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ExternalClauseImpl <em>External Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ExternalClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getExternalClause()
     * @generated
     */
    EClass EXTERNAL_CLAUSE = eINSTANCE.getExternalClause();

    /**
     * The meta object literal for the '<em><b>Language Specification</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTERNAL_CLAUSE__LANGUAGE_SPECIFICATION = eINSTANCE.getExternalClause_LanguageSpecification();

    /**
     * The meta object literal for the '<em><b>Annotation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTERNAL_CLAUSE__ANNOTATION = eINSTANCE.getExternalClause_Annotation();

    /**
     * The meta object literal for the '<em><b>Ext Annotation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTERNAL_CLAUSE__EXT_ANNOTATION = eINSTANCE.getExternalClause_ExtAnnotation();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ElementListImpl <em>Element List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ElementListImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementList()
     * @generated
     */
    EClass ELEMENT_LIST = eINSTANCE.getElementList();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_LIST__ELEMENTS = eINSTANCE.getElementList_Elements();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_LIST__ANNOTATIONS = eINSTANCE.getElementList_Annotations();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ClassElementImpl <em>Class Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ClassElementImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassElement()
     * @generated
     */
    EClass CLASS_ELEMENT = eINSTANCE.getClassElement();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ReplaceableElementClauseImpl <em>Replaceable Element Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ReplaceableElementClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getReplaceableElementClause()
     * @generated
     */
    EClass REPLACEABLE_ELEMENT_CLAUSE = eINSTANCE.getReplaceableElementClause();

    /**
     * The meta object literal for the '<em><b>Entity</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPLACEABLE_ELEMENT_CLAUSE__ENTITY = eINSTANCE.getReplaceableElementClause_Entity();

    /**
     * The meta object literal for the '<em><b>Constrain</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REPLACEABLE_ELEMENT_CLAUSE__CONSTRAIN = eINSTANCE.getReplaceableElementClause_Constrain();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ImportClauseImpl <em>Import Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ImportClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getImportClause()
     * @generated
     */
    EClass IMPORT_CLAUSE = eINSTANCE.getImportClause();

    /**
     * The meta object literal for the '<em><b>As Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT_CLAUSE__AS_NAME = eINSTANCE.getImportClause_AsName();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT_CLAUSE__NAME = eINSTANCE.getImportClause_Name();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPORT_CLAUSE__COMMENT = eINSTANCE.getImportClause_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ExtendsClauseImpl <em>Extends Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ExtendsClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getExtendsClause()
     * @generated
     */
    EClass EXTENDS_CLAUSE = eINSTANCE.getExtendsClause();

    /**
     * The meta object literal for the '<em><b>Base Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDS_CLAUSE__BASE_CLASS = eINSTANCE.getExtendsClause_BaseClass();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS_CLAUSE__MODIFICATION = eINSTANCE.getExtendsClause_Modification();

    /**
     * The meta object literal for the '<em><b>Annotation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS_CLAUSE__ANNOTATION = eINSTANCE.getExtendsClause_Annotation();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ConstrainingClauseImpl <em>Constraining Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ConstrainingClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getConstrainingClause()
     * @generated
     */
    EClass CONSTRAINING_CLAUSE = eINSTANCE.getConstrainingClause();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTRAINING_CLAUSE__NAME = eINSTANCE.getConstrainingClause_Name();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINING_CLAUSE__MODIFICATION = eINSTANCE.getConstrainingClause_Modification();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINING_CLAUSE__COMMENT = eINSTANCE.getConstrainingClause_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ComponentClauseImpl <em>Component Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ComponentClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getComponentClause()
     * @generated
     */
    EClass COMPONENT_CLAUSE = eINSTANCE.getComponentClause();

    /**
     * The meta object literal for the '<em><b>Type Prefix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMPONENT_CLAUSE__TYPE_PREFIX = eINSTANCE.getComponentClause_TypePrefix();

    /**
     * The meta object literal for the '<em><b>Type Specifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_CLAUSE__TYPE_SPECIFIER = eINSTANCE.getComponentClause_TypeSpecifier();

    /**
     * The meta object literal for the '<em><b>Declarations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_CLAUSE__DECLARATIONS = eINSTANCE.getComponentClause_Declarations();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.TypeSpecifierImpl <em>Type Specifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.TypeSpecifierImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getTypeSpecifier()
     * @generated
     */
    EClass TYPE_SPECIFIER = eINSTANCE.getTypeSpecifier();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TYPE_SPECIFIER__NAME = eINSTANCE.getTypeSpecifier_Name();

    /**
     * The meta object literal for the '<em><b>Subscripts</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TYPE_SPECIFIER__SUBSCRIPTS = eINSTANCE.getTypeSpecifier_Subscripts();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl <em>Component Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ComponentDeclarationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getComponentDeclaration()
     * @generated
     */
    EClass COMPONENT_DECLARATION = eINSTANCE.getComponentDeclaration();

    /**
     * The meta object literal for the '<em><b>Subscripts</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMPONENT_DECLARATION__SUBSCRIPTS = eINSTANCE.getComponentDeclaration_Subscripts();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_DECLARATION__MODIFICATION = eINSTANCE.getComponentDeclaration_Modification();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMPONENT_DECLARATION__CONDITION = eINSTANCE.getComponentDeclaration_Condition();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMPONENT_DECLARATION__COMMENT = eINSTANCE.getComponentDeclaration_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ModificationImpl <em>Modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ModificationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getModification()
     * @generated
     */
    EClass MODIFICATION = eINSTANCE.getModification();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODIFICATION__EXPRESSION = eINSTANCE.getModification_Expression();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.SimpleModificationImpl <em>Simple Modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.SimpleModificationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getSimpleModification()
     * @generated
     */
    EClass SIMPLE_MODIFICATION = eINSTANCE.getSimpleModification();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.argumentImpl <em>argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.argumentImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getargument()
     * @generated
     */
    EClass ARGUMENT = eINSTANCE.getargument();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ElementModificationImpl <em>Element Modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ElementModificationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementModification()
     * @generated
     */
    EClass ELEMENT_MODIFICATION = eINSTANCE.getElementModification();

    /**
     * The meta object literal for the '<em><b>Componet Ref</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_MODIFICATION__COMPONET_REF = eINSTANCE.getElementModification_ComponetRef();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_MODIFICATION__MODIFICATION = eINSTANCE.getElementModification_Modification();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_MODIFICATION__COMMENT = eINSTANCE.getElementModification_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ElementRedeclarationImpl <em>Element Redeclaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ElementRedeclarationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementRedeclaration()
     * @generated
     */
    EClass ELEMENT_REDECLARATION = eINSTANCE.getElementRedeclaration();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ElementReplaceableClauseImpl <em>Element Replaceable Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ElementReplaceableClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementReplaceableClause()
     * @generated
     */
    EClass ELEMENT_REPLACEABLE_CLAUSE = eINSTANCE.getElementReplaceableClause();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REPLACEABLE_CLAUSE__ELEMENT = eINSTANCE.getElementReplaceableClause_Element();

    /**
     * The meta object literal for the '<em><b>Constraint</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REPLACEABLE_CLAUSE__CONSTRAINT = eINSTANCE.getElementReplaceableClause_Constraint();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl <em>Element Component Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ElementComponentClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getElementComponentClause()
     * @generated
     */
    EClass ELEMENT_COMPONENT_CLAUSE = eINSTANCE.getElementComponentClause();

    /**
     * The meta object literal for the '<em><b>Type Prefix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_COMPONENT_CLAUSE__TYPE_PREFIX = eINSTANCE.getElementComponentClause_TypePrefix();

    /**
     * The meta object literal for the '<em><b>Type Specifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_COMPONENT_CLAUSE__TYPE_SPECIFIER = eINSTANCE.getElementComponentClause_TypeSpecifier();

    /**
     * The meta object literal for the '<em><b>Subscripts</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT_COMPONENT_CLAUSE__SUBSCRIPTS = eINSTANCE.getElementComponentClause_Subscripts();

    /**
     * The meta object literal for the '<em><b>Modification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_COMPONENT_CLAUSE__MODIFICATION = eINSTANCE.getElementComponentClause_Modification();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_COMPONENT_CLAUSE__COMMENT = eINSTANCE.getElementComponentClause_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.EquationClauseImpl <em>Equation Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.EquationClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getEquationClause()
     * @generated
     */
    EClass EQUATION_CLAUSE = eINSTANCE.getEquationClause();

    /**
     * The meta object literal for the '<em><b>Equation Rules</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION_CLAUSE__EQUATION_RULES = eINSTANCE.getEquationClause_EquationRules();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION_CLAUSE__ANNOTATIONS = eINSTANCE.getEquationClause_Annotations();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.algorithm_clauseImpl <em>algorithm clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.algorithm_clauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getalgorithm_clause()
     * @generated
     */
    EClass ALGORITHM_CLAUSE = eINSTANCE.getalgorithm_clause();

    /**
     * The meta object literal for the '<em><b>Algorithm Rules</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM_CLAUSE__ALGORITHM_RULES = eINSTANCE.getalgorithm_clause_AlgorithmRules();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM_CLAUSE__ANNOTATIONS = eINSTANCE.getalgorithm_clause_Annotations();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.initial_equation_rule_clauseImpl <em>initial equation rule clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.initial_equation_rule_clauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getinitial_equation_rule_clause()
     * @generated
     */
    EClass INITIAL_EQUATION_RULE_CLAUSE = eINSTANCE.getinitial_equation_rule_clause();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.initial_algorithm_clauseImpl <em>initial algorithm clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.initial_algorithm_clauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getinitial_algorithm_clause()
     * @generated
     */
    EClass INITIAL_ALGORITHM_CLAUSE = eINSTANCE.getinitial_algorithm_clause();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.equation_ruleImpl <em>equation rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.equation_ruleImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getequation_rule()
     * @generated
     */
    EClass EQUATION_RULE = eINSTANCE.getequation_rule();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION_RULE__COMMENT = eINSTANCE.getequation_rule_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.algorithmImpl <em>algorithm</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.algorithmImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getalgorithm()
     * @generated
     */
    EClass ALGORITHM = eINSTANCE.getalgorithm();

    /**
     * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM__COMMENT = eINSTANCE.getalgorithm_Comment();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ConditionalEquationRuleImpl <em>Conditional Equation Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ConditionalEquationRuleImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getConditionalEquationRule()
     * @generated
     */
    EClass CONDITIONAL_EQUATION_RULE = eINSTANCE.getConditionalEquationRule();

    /**
     * The meta object literal for the '<em><b>Then Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EQUATION_RULE__THEN_EQUATION_CLAUSES = eINSTANCE.getConditionalEquationRule_ThenEquationClauses();

    /**
     * The meta object literal for the '<em><b>Elseif Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EQUATION_RULE__ELSEIF_CLAUSES = eINSTANCE.getConditionalEquationRule_ElseifClauses();

    /**
     * The meta object literal for the '<em><b>Else Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EQUATION_RULE__ELSE_EQUATION_CLAUSES = eINSTANCE.getConditionalEquationRule_ElseEquationClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl <em>Condition Algorithm Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ConditionAlgorithmRuleImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getConditionAlgorithmRule()
     * @generated
     */
    EClass CONDITION_ALGORITHM_RULE = eINSTANCE.getConditionAlgorithmRule();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONDITION_ALGORITHM_RULE__CONDITION = eINSTANCE.getConditionAlgorithmRule_Condition();

    /**
     * The meta object literal for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION_ALGORITHM_RULE__THEN_ALGORITHM_CLAUSES = eINSTANCE.getConditionAlgorithmRule_ThenAlgorithmClauses();

    /**
     * The meta object literal for the '<em><b>Elseif Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION_ALGORITHM_RULE__ELSEIF_CLAUSES = eINSTANCE.getConditionAlgorithmRule_ElseifClauses();

    /**
     * The meta object literal for the '<em><b>Else Algotithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION_ALGORITHM_RULE__ELSE_ALGOTITHM_CLAUSES = eINSTANCE.getConditionAlgorithmRule_ElseAlgotithmClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ForEquationClauseImpl <em>For Equation Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ForEquationClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getForEquationClause()
     * @generated
     */
    EClass FOR_EQUATION_CLAUSE = eINSTANCE.getForEquationClause();

    /**
     * The meta object literal for the '<em><b>For Indices</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_EQUATION_CLAUSE__FOR_INDICES = eINSTANCE.getForEquationClause_ForIndices();

    /**
     * The meta object literal for the '<em><b>Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_EQUATION_CLAUSE__EQUATION_CLAUSES = eINSTANCE.getForEquationClause_EquationClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ForAlgorithmClauseImpl <em>For Algorithm Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ForAlgorithmClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getForAlgorithmClause()
     * @generated
     */
    EClass FOR_ALGORITHM_CLAUSE = eINSTANCE.getForAlgorithmClause();

    /**
     * The meta object literal for the '<em><b>For Indices</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_ALGORITHM_CLAUSE__FOR_INDICES = eINSTANCE.getForAlgorithmClause_ForIndices();

    /**
     * The meta object literal for the '<em><b>Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_ALGORITHM_CLAUSE__ALGORITHM_CLAUSES = eINSTANCE.getForAlgorithmClause_AlgorithmClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.WhileClauseImpl <em>While Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.WhileClauseImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getWhileClause()
     * @generated
     */
    EClass WHILE_CLAUSE = eINSTANCE.getWhileClause();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WHILE_CLAUSE__CONDITION = eINSTANCE.getWhileClause_Condition();

    /**
     * The meta object literal for the '<em><b>Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHILE_CLAUSE__ALGORITHM_CLAUSES = eINSTANCE.getWhileClause_AlgorithmClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.when_clause_eImpl <em>when clause e</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.when_clause_eImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getwhen_clause_e()
     * @generated
     */
    EClass WHEN_CLAUSE_E = eINSTANCE.getwhen_clause_e();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WHEN_CLAUSE_E__CONDITION = eINSTANCE.getwhen_clause_e_Condition();

    /**
     * The meta object literal for the '<em><b>Then Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_CLAUSE_E__THEN_EQUATION_CLAUSES = eINSTANCE.getwhen_clause_e_ThenEquationClauses();

    /**
     * The meta object literal for the '<em><b>Else When Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_CLAUSE_E__ELSE_WHEN_CLAUSES = eINSTANCE.getwhen_clause_e_ElseWhenClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.else_when_eImpl <em>else when e</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.else_when_eImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getelse_when_e()
     * @generated
     */
    EClass ELSE_WHEN_E = eINSTANCE.getelse_when_e();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELSE_WHEN_E__CONDITION = eINSTANCE.getelse_when_e_Condition();

    /**
     * The meta object literal for the '<em><b>Then Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELSE_WHEN_E__THEN_EQUATION_CLAUSES = eINSTANCE.getelse_when_e_ThenEquationClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.when_clause_aImpl <em>when clause a</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.when_clause_aImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getwhen_clause_a()
     * @generated
     */
    EClass WHEN_CLAUSE_A = eINSTANCE.getwhen_clause_a();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WHEN_CLAUSE_A__CONDITION = eINSTANCE.getwhen_clause_a_Condition();

    /**
     * The meta object literal for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_CLAUSE_A__THEN_ALGORITHM_CLAUSES = eINSTANCE.getwhen_clause_a_ThenAlgorithmClauses();

    /**
     * The meta object literal for the '<em><b>Else When Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WHEN_CLAUSE_A__ELSE_WHEN_CLAUSES = eINSTANCE.getwhen_clause_a_ElseWhenClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.else_when_aImpl <em>else when a</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.else_when_aImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getelse_when_a()
     * @generated
     */
    EClass ELSE_WHEN_A = eINSTANCE.getelse_when_a();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELSE_WHEN_A__CONDITION = eINSTANCE.getelse_when_a_Condition();

    /**
     * The meta object literal for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELSE_WHEN_A__THEN_ALGORITHM_CLAUSES = eINSTANCE.getelse_when_a_ThenAlgorithmClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.equation_rule_elseifImpl <em>equation rule elseif</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.equation_rule_elseifImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getequation_rule_elseif()
     * @generated
     */
    EClass EQUATION_RULE_ELSEIF = eINSTANCE.getequation_rule_elseif();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EQUATION_RULE_ELSEIF__CONDITION = eINSTANCE.getequation_rule_elseif_Condition();

    /**
     * The meta object literal for the '<em><b>Then Equation Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EQUATION_RULE_ELSEIF__THEN_EQUATION_CLAUSES = eINSTANCE.getequation_rule_elseif_ThenEquationClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.algorithm_elseifImpl <em>algorithm elseif</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.algorithm_elseifImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getalgorithm_elseif()
     * @generated
     */
    EClass ALGORITHM_ELSEIF = eINSTANCE.getalgorithm_elseif();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ALGORITHM_ELSEIF__CONDITION = eINSTANCE.getalgorithm_elseif_Condition();

    /**
     * The meta object literal for the '<em><b>Then Algorithm Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ALGORITHM_ELSEIF__THEN_ALGORITHM_CLAUSES = eINSTANCE.getalgorithm_elseif_ThenAlgorithmClauses();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.CommentImpl <em>Comment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.CommentImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getComment()
     * @generated
     */
    EClass COMMENT = eINSTANCE.getComment();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMMENT__STRING = eINSTANCE.getComment_String();

    /**
     * The meta object literal for the '<em><b>Annotation</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMMENT__ANNOTATION = eINSTANCE.getComment_Annotation();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.AnnotationImpl <em>Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.AnnotationImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getAnnotation()
     * @generated
     */
    EClass ANNOTATION = eINSTANCE.getAnnotation();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.PublicElementListImpl <em>Public Element List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.PublicElementListImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getPublicElementList()
     * @generated
     */
    EClass PUBLIC_ELEMENT_LIST = eINSTANCE.getPublicElementList();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.impl.ProtectesElementListImpl <em>Protectes Element List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.impl.ProtectesElementListImpl
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getProtectesElementList()
     * @generated
     */
    EClass PROTECTES_ELEMENT_LIST = eINSTANCE.getProtectesElementList();

    /**
     * The meta object literal for the '{@link org.modelica.xtext.modelicaBase.ClassType <em>Class Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.modelica.xtext.modelicaBase.ClassType
     * @see org.modelica.xtext.modelicaBase.impl.ModelicaBasePackageImpl#getClassType()
     * @generated
     */
    EEnum CLASS_TYPE = eINSTANCE.getClassType();

  }

} //ModelicaBasePackage
