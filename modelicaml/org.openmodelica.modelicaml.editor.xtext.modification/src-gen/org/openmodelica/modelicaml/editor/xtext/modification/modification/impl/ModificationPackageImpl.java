/**
 * <copyright>
 * </copyright>
 *

 */
package org.openmodelica.modelicaml.editor.xtext.modification.modification.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationFactory;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_section;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.argument;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.argument_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_definition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_clause1;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration1;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.composition;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.conditional_attribute;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.connect_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.constraining_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.declaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_modification_or_replaceable;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_redeclaration;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.element_replaceable;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.enum_list;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.equation_section;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.extends_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.external_function_call;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.for_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.for_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.if_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.import_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.language_specification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.left_hand_component_reference;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.modification;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.modification_alternatives;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.state_Comp_Ref;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.state_OutputExprList;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.type_specifier;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_equation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.when_statement;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.while_statement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModificationPackageImpl extends EPackageImpl implements ModificationPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modification_alternativesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass left_hand_component_referenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass class_modificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argument_listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argumentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass element_modification_or_replaceableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass element_modificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass element_replaceableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constraining_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass element_redeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass component_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass component_listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass component_declarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditional_attributeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass component_clause1EClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass component_declaration1EClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass type_specifierEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass class_definitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass class_specifierEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enum_listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compositionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass element_listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass elementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass equation_sectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass equationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass when_equationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass if_equationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass for_equationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass connect_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass algorithm_sectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass state_OutputExprListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass state_Comp_RefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass when_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass while_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass for_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass if_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extends_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass import_clauseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass language_specificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass external_function_callEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass class_modification_actionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass algorithm_clauseEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ModificationPackageImpl()
  {
    super(eNS_URI, ModificationFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link ModificationPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ModificationPackage init()
  {
    if (isInited) return (ModificationPackage)EPackage.Registry.INSTANCE.getEPackage(ModificationPackage.eNS_URI);

    // Obtain or create and register package
    ModificationPackageImpl theModificationPackage = (ModificationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModificationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModificationPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    ModeleditorPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theModificationPackage.createPackageContents();

    // Initialize created meta-data
    theModificationPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theModificationPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ModificationPackage.eNS_URI, theModificationPackage);
    return theModificationPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmodification_alternatives()
  {
    return modification_alternativesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodification_alternatives_Modification_Rule()
  {
    return (EReference)modification_alternativesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getleft_hand_component_reference()
  {
    return left_hand_component_referenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getleft_hand_component_reference_Expr()
  {
    return (EReference)left_hand_component_referenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getleft_hand_component_reference_Ref()
  {
    return (EAttribute)left_hand_component_referenceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getleft_hand_component_reference_Subscripts1()
  {
    return (EReference)left_hand_component_referenceEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getleft_hand_component_reference_Ref1()
  {
    return (EAttribute)left_hand_component_referenceEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getleft_hand_component_reference_Subscripts()
  {
    return (EReference)left_hand_component_referenceEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmodification()
  {
    return modificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodification_Expr()
  {
    return (EReference)modificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getclass_modification()
  {
    return class_modificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getargument_list()
  {
    return argument_listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getargument()
  {
    return argumentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelement_modification_or_replaceable()
  {
    return element_modification_or_replaceableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_modification_or_replaceable_Element_mod()
  {
    return (EReference)element_modification_or_replaceableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_modification_or_replaceable_Element_rep()
  {
    return (EReference)element_modification_or_replaceableEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelement_modification()
  {
    return element_modificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_modification_Name()
  {
    return (EReference)element_modificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_modification_Modification()
  {
    return (EReference)element_modificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelement_replaceable()
  {
    return element_replaceableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_replaceable_Class_Def()
  {
    return (EReference)element_replaceableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_replaceable_Comp_clause2()
  {
    return (EReference)element_replaceableEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_replaceable_Constrain_Clause()
  {
    return (EReference)element_replaceableEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconstraining_clause()
  {
    return constraining_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconstraining_clause_Name()
  {
    return (EReference)constraining_clauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconstraining_clause_Class_Mod()
  {
    return (EReference)constraining_clauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelement_redeclaration()
  {
    return element_redeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_redeclaration_Class_Def()
  {
    return (EReference)element_redeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_redeclaration_Comp_clause1()
  {
    return (EReference)element_redeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_redeclaration_Element_R()
  {
    return (EReference)element_redeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcomponent_clause()
  {
    return component_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcomponent_list()
  {
    return component_listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcomponent_declaration()
  {
    return component_declarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomponent_declaration_Component_Dec()
  {
    return (EReference)component_declarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomponent_declaration_Dec()
  {
    return (EReference)component_declarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomponent_declaration_Conditional_Att()
  {
    return (EReference)component_declarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconditional_attribute()
  {
    return conditional_attributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconditional_attribute_Expr()
  {
    return (EReference)conditional_attributeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcomponent_clause1()
  {
    return component_clause1EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcomponent_declaration1()
  {
    return component_declaration1EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettype_specifier()
  {
    return type_specifierEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettype_specifier_Array_Subs()
  {
    return (EReference)type_specifierEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettype_specifier_Com_List()
  {
    return (EReference)type_specifierEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettype_specifier_Com_Dec()
  {
    return (EReference)type_specifierEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettype_specifier_Name()
  {
    return (EReference)type_specifierEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdeclaration()
  {
    return declarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaration_RedeclaredComponentName()
  {
    return (EAttribute)declarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaration_ArraySubs()
  {
    return (EReference)declarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaration_Mod()
  {
    return (EReference)declarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getclass_definition()
  {
    return class_definitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getclass_specifier()
  {
    return class_specifierEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getclass_specifier_Comp()
  {
    return (EReference)class_specifierEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getclass_specifier_Name()
  {
    return (EReference)class_specifierEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getclass_specifier_Array_Subs()
  {
    return (EReference)class_specifierEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getclass_specifier_Class_mod()
  {
    return (EReference)class_specifierEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getclass_specifier_Var()
  {
    return (EAttribute)class_specifierEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getclass_specifier_Enum_list()
  {
    return (EReference)class_specifierEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getenum_list()
  {
    return enum_listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getenum_list_Ennum_Lit()
  {
    return (EAttribute)enum_listEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getenum_list_E_literal()
  {
    return (EAttribute)enum_listEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcomposition()
  {
    return compositionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_E_List_Initial()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_E_List_Public()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_E_List_Protected()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_Eqn_section()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_Alg_section()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_Lang_Spec()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_External_F_C()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_Annotation1()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcomposition_Annotation2()
  {
    return (EReference)compositionEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelement_list()
  {
    return element_listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_list_Element()
  {
    return (EReference)element_listEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getelement()
  {
    return elementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_Class_Def()
  {
    return (EReference)elementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_Comp_Clause()
  {
    return (EReference)elementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_Constrain_Clause()
  {
    return (EReference)elementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getelement_Name()
  {
    return (EReference)elementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getequation_section()
  {
    return equation_sectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_section_Eqn()
  {
    return (EReference)equation_sectionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getequation()
  {
    return equationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_Sim()
  {
    return (EReference)equationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_Expr()
  {
    return (EReference)equationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_IF_EQN()
  {
    return (EReference)equationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_FOR_EQN()
  {
    return (EReference)equationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_Con()
  {
    return (EReference)equationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getequation_WHEN_EQN()
  {
    return (EReference)equationEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getequation_Comment()
  {
    return (EAttribute)equationEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getwhen_equation()
  {
    return when_equationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_equation_WhenEpr()
  {
    return (EReference)when_equationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_equation_Eqn_Then()
  {
    return (EReference)when_equationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_equation_ElseWhenEpr()
  {
    return (EReference)when_equationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getif_equation()
  {
    return if_equationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_equation_Exprtrue()
  {
    return (EReference)if_equationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_equation_Eqn()
  {
    return (EReference)if_equationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_equation_ExprStilltrue()
  {
    return (EReference)if_equationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_equation_ThenEqn()
  {
    return (EReference)if_equationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_equation_ElseEqn()
  {
    return (EReference)if_equationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfor_equation()
  {
    return for_equationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfor_equation_For_loop()
  {
    return (EReference)for_equationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfor_equation_For_Eqn()
  {
    return (EReference)for_equationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconnect_clause()
  {
    return connect_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconnect_clause_Connector1()
  {
    return (EReference)connect_clauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconnect_clause_Connector2()
  {
    return (EReference)connect_clauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getalgorithm_section()
  {
    return algorithm_sectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstatement()
  {
    return statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_If_statement()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_State_OutputExprList()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_State_Comp_Ref()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_For_statement()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_When_statement()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_While_statement()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstate_OutputExprList()
  {
    return state_OutputExprListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_OutputExprList_Output_expression_list()
  {
    return (EReference)state_OutputExprListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_OutputExprList_Component_ref()
  {
    return (EReference)state_OutputExprListEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_OutputExprList_Fun_call_args()
  {
    return (EReference)state_OutputExprListEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstate_Comp_Ref()
  {
    return state_Comp_RefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_Comp_Ref_Component_reference()
  {
    return (EReference)state_Comp_RefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_Comp_Ref_Expr()
  {
    return (EReference)state_Comp_RefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_Comp_Ref_Fun_call_args()
  {
    return (EReference)state_Comp_RefEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getwhen_statement()
  {
    return when_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_statement_When_expr()
  {
    return (EReference)when_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_statement_When_stat_true()
  {
    return (EReference)when_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_statement_Else_When_expr()
  {
    return (EReference)when_statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhen_statement_Then_statement()
  {
    return (EReference)when_statementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getwhile_statement()
  {
    return while_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhile_statement_While_expr()
  {
    return (EReference)while_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getwhile_statement_While_stat()
  {
    return (EReference)while_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfor_statement()
  {
    return for_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfor_statement_For_indices()
  {
    return (EReference)for_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfor_statement_For_statement()
  {
    return (EReference)for_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getif_statement()
  {
    return if_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_statement_Exprtrue()
  {
    return (EReference)if_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_statement_State()
  {
    return (EReference)if_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getif_statement_ExprStilltrue()
  {
    return (EReference)if_statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getextends_clause()
  {
    return extends_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getextends_clause_Class_Mod()
  {
    return (EReference)extends_clauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getextends_clause_Annotation()
  {
    return (EReference)extends_clauseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getannotation()
  {
    return annotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getannotation_Class_Mod()
  {
    return (EReference)annotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getimport_clause()
  {
    return import_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getlanguage_specification()
  {
    return language_specificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getlanguage_specification_Str()
  {
    return (EAttribute)language_specificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getexternal_function_call()
  {
    return external_function_callEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getexternal_function_call_Component_reference()
  {
    return (EReference)external_function_callEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getexternal_function_call_E_List()
  {
    return (EReference)external_function_callEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getclass_modification_action()
  {
    return class_modification_actionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getclass_modification_action_Arg()
  {
    return (EReference)class_modification_actionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getalgorithm_clause()
  {
    return algorithm_clauseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getalgorithm_clause_Algorithm()
  {
    return (EReference)algorithm_clauseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModificationFactory getModificationFactory()
  {
    return (ModificationFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    modification_alternativesEClass = createEClass(MODIFICATION_ALTERNATIVES);
    createEReference(modification_alternativesEClass, MODIFICATION_ALTERNATIVES__MODIFICATION_RULE);

    left_hand_component_referenceEClass = createEClass(LEFT_HAND_COMPONENT_REFERENCE);
    createEReference(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__EXPR);
    createEAttribute(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__REF);
    createEReference(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS1);
    createEAttribute(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__REF1);
    createEReference(left_hand_component_referenceEClass, LEFT_HAND_COMPONENT_REFERENCE__SUBSCRIPTS);

    modificationEClass = createEClass(MODIFICATION);
    createEReference(modificationEClass, MODIFICATION__EXPR);

    class_modificationEClass = createEClass(CLASS_MODIFICATION);

    argument_listEClass = createEClass(ARGUMENT_LIST);

    argumentEClass = createEClass(ARGUMENT);

    element_modification_or_replaceableEClass = createEClass(ELEMENT_MODIFICATION_OR_REPLACEABLE);
    createEReference(element_modification_or_replaceableEClass, ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_MOD);
    createEReference(element_modification_or_replaceableEClass, ELEMENT_MODIFICATION_OR_REPLACEABLE__ELEMENT_REP);

    element_modificationEClass = createEClass(ELEMENT_MODIFICATION);
    createEReference(element_modificationEClass, ELEMENT_MODIFICATION__NAME);
    createEReference(element_modificationEClass, ELEMENT_MODIFICATION__MODIFICATION);

    element_replaceableEClass = createEClass(ELEMENT_REPLACEABLE);
    createEReference(element_replaceableEClass, ELEMENT_REPLACEABLE__CLASS_DEF);
    createEReference(element_replaceableEClass, ELEMENT_REPLACEABLE__COMP_CLAUSE2);
    createEReference(element_replaceableEClass, ELEMENT_REPLACEABLE__CONSTRAIN_CLAUSE);

    constraining_clauseEClass = createEClass(CONSTRAINING_CLAUSE);
    createEReference(constraining_clauseEClass, CONSTRAINING_CLAUSE__NAME);
    createEReference(constraining_clauseEClass, CONSTRAINING_CLAUSE__CLASS_MOD);

    element_redeclarationEClass = createEClass(ELEMENT_REDECLARATION);
    createEReference(element_redeclarationEClass, ELEMENT_REDECLARATION__CLASS_DEF);
    createEReference(element_redeclarationEClass, ELEMENT_REDECLARATION__COMP_CLAUSE1);
    createEReference(element_redeclarationEClass, ELEMENT_REDECLARATION__ELEMENT_R);

    component_clauseEClass = createEClass(COMPONENT_CLAUSE);

    component_listEClass = createEClass(COMPONENT_LIST);

    component_declarationEClass = createEClass(COMPONENT_DECLARATION);
    createEReference(component_declarationEClass, COMPONENT_DECLARATION__COMPONENT_DEC);
    createEReference(component_declarationEClass, COMPONENT_DECLARATION__DEC);
    createEReference(component_declarationEClass, COMPONENT_DECLARATION__CONDITIONAL_ATT);

    conditional_attributeEClass = createEClass(CONDITIONAL_ATTRIBUTE);
    createEReference(conditional_attributeEClass, CONDITIONAL_ATTRIBUTE__EXPR);

    component_clause1EClass = createEClass(COMPONENT_CLAUSE1);

    component_declaration1EClass = createEClass(COMPONENT_DECLARATION1);

    type_specifierEClass = createEClass(TYPE_SPECIFIER);
    createEReference(type_specifierEClass, TYPE_SPECIFIER__ARRAY_SUBS);
    createEReference(type_specifierEClass, TYPE_SPECIFIER__COM_LIST);
    createEReference(type_specifierEClass, TYPE_SPECIFIER__COM_DEC);
    createEReference(type_specifierEClass, TYPE_SPECIFIER__NAME);

    declarationEClass = createEClass(DECLARATION);
    createEAttribute(declarationEClass, DECLARATION__REDECLARED_COMPONENT_NAME);
    createEReference(declarationEClass, DECLARATION__ARRAY_SUBS);
    createEReference(declarationEClass, DECLARATION__MOD);

    class_definitionEClass = createEClass(CLASS_DEFINITION);

    class_specifierEClass = createEClass(CLASS_SPECIFIER);
    createEReference(class_specifierEClass, CLASS_SPECIFIER__COMP);
    createEReference(class_specifierEClass, CLASS_SPECIFIER__NAME);
    createEReference(class_specifierEClass, CLASS_SPECIFIER__ARRAY_SUBS);
    createEReference(class_specifierEClass, CLASS_SPECIFIER__CLASS_MOD);
    createEAttribute(class_specifierEClass, CLASS_SPECIFIER__VAR);
    createEReference(class_specifierEClass, CLASS_SPECIFIER__ENUM_LIST);

    enum_listEClass = createEClass(ENUM_LIST);
    createEAttribute(enum_listEClass, ENUM_LIST__ENNUM_LIT);
    createEAttribute(enum_listEClass, ENUM_LIST__ELITERAL);

    compositionEClass = createEClass(COMPOSITION);
    createEReference(compositionEClass, COMPOSITION__ELIST_INITIAL);
    createEReference(compositionEClass, COMPOSITION__ELIST_PUBLIC);
    createEReference(compositionEClass, COMPOSITION__ELIST_PROTECTED);
    createEReference(compositionEClass, COMPOSITION__EQN_SECTION);
    createEReference(compositionEClass, COMPOSITION__ALG_SECTION);
    createEReference(compositionEClass, COMPOSITION__LANG_SPEC);
    createEReference(compositionEClass, COMPOSITION__EXTERNAL_FC);
    createEReference(compositionEClass, COMPOSITION__ANNOTATION1);
    createEReference(compositionEClass, COMPOSITION__ANNOTATION2);

    element_listEClass = createEClass(ELEMENT_LIST);
    createEReference(element_listEClass, ELEMENT_LIST__ELEMENT);

    elementEClass = createEClass(ELEMENT);
    createEReference(elementEClass, ELEMENT__CLASS_DEF);
    createEReference(elementEClass, ELEMENT__COMP_CLAUSE);
    createEReference(elementEClass, ELEMENT__CONSTRAIN_CLAUSE);
    createEReference(elementEClass, ELEMENT__NAME);

    equation_sectionEClass = createEClass(EQUATION_SECTION);
    createEReference(equation_sectionEClass, EQUATION_SECTION__EQN);

    equationEClass = createEClass(EQUATION);
    createEReference(equationEClass, EQUATION__SIM);
    createEReference(equationEClass, EQUATION__EXPR);
    createEReference(equationEClass, EQUATION__IF_EQN);
    createEReference(equationEClass, EQUATION__FOR_EQN);
    createEReference(equationEClass, EQUATION__CON);
    createEReference(equationEClass, EQUATION__WHEN_EQN);
    createEAttribute(equationEClass, EQUATION__COMMENT);

    when_equationEClass = createEClass(WHEN_EQUATION);
    createEReference(when_equationEClass, WHEN_EQUATION__WHEN_EPR);
    createEReference(when_equationEClass, WHEN_EQUATION__EQN_THEN);
    createEReference(when_equationEClass, WHEN_EQUATION__ELSE_WHEN_EPR);

    if_equationEClass = createEClass(IF_EQUATION);
    createEReference(if_equationEClass, IF_EQUATION__EXPRTRUE);
    createEReference(if_equationEClass, IF_EQUATION__EQN);
    createEReference(if_equationEClass, IF_EQUATION__EXPR_STILLTRUE);
    createEReference(if_equationEClass, IF_EQUATION__THEN_EQN);
    createEReference(if_equationEClass, IF_EQUATION__ELSE_EQN);

    for_equationEClass = createEClass(FOR_EQUATION);
    createEReference(for_equationEClass, FOR_EQUATION__FOR_LOOP);
    createEReference(for_equationEClass, FOR_EQUATION__FOR_EQN);

    connect_clauseEClass = createEClass(CONNECT_CLAUSE);
    createEReference(connect_clauseEClass, CONNECT_CLAUSE__CONNECTOR1);
    createEReference(connect_clauseEClass, CONNECT_CLAUSE__CONNECTOR2);

    algorithm_sectionEClass = createEClass(ALGORITHM_SECTION);

    statementEClass = createEClass(STATEMENT);
    createEReference(statementEClass, STATEMENT__IF_STATEMENT);
    createEReference(statementEClass, STATEMENT__STATE_OUTPUT_EXPR_LIST);
    createEReference(statementEClass, STATEMENT__STATE_COMP_REF);
    createEReference(statementEClass, STATEMENT__FOR_STATEMENT);
    createEReference(statementEClass, STATEMENT__WHEN_STATEMENT);
    createEReference(statementEClass, STATEMENT__WHILE_STATEMENT);

    state_OutputExprListEClass = createEClass(STATE_OUTPUT_EXPR_LIST);
    createEReference(state_OutputExprListEClass, STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST);
    createEReference(state_OutputExprListEClass, STATE_OUTPUT_EXPR_LIST__COMPONENT_REF);
    createEReference(state_OutputExprListEClass, STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS);

    state_Comp_RefEClass = createEClass(STATE_COMP_REF);
    createEReference(state_Comp_RefEClass, STATE_COMP_REF__COMPONENT_REFERENCE);
    createEReference(state_Comp_RefEClass, STATE_COMP_REF__EXPR);
    createEReference(state_Comp_RefEClass, STATE_COMP_REF__FUN_CALL_ARGS);

    when_statementEClass = createEClass(WHEN_STATEMENT);
    createEReference(when_statementEClass, WHEN_STATEMENT__WHEN_EXPR);
    createEReference(when_statementEClass, WHEN_STATEMENT__WHEN_STAT_TRUE);
    createEReference(when_statementEClass, WHEN_STATEMENT__ELSE_WHEN_EXPR);
    createEReference(when_statementEClass, WHEN_STATEMENT__THEN_STATEMENT);

    while_statementEClass = createEClass(WHILE_STATEMENT);
    createEReference(while_statementEClass, WHILE_STATEMENT__WHILE_EXPR);
    createEReference(while_statementEClass, WHILE_STATEMENT__WHILE_STAT);

    for_statementEClass = createEClass(FOR_STATEMENT);
    createEReference(for_statementEClass, FOR_STATEMENT__FOR_INDICES);
    createEReference(for_statementEClass, FOR_STATEMENT__FOR_STATEMENT);

    if_statementEClass = createEClass(IF_STATEMENT);
    createEReference(if_statementEClass, IF_STATEMENT__EXPRTRUE);
    createEReference(if_statementEClass, IF_STATEMENT__STATE);
    createEReference(if_statementEClass, IF_STATEMENT__EXPR_STILLTRUE);

    extends_clauseEClass = createEClass(EXTENDS_CLAUSE);
    createEReference(extends_clauseEClass, EXTENDS_CLAUSE__CLASS_MOD);
    createEReference(extends_clauseEClass, EXTENDS_CLAUSE__ANNOTATION);

    annotationEClass = createEClass(ANNOTATION);
    createEReference(annotationEClass, ANNOTATION__CLASS_MOD);

    import_clauseEClass = createEClass(IMPORT_CLAUSE);

    language_specificationEClass = createEClass(LANGUAGE_SPECIFICATION);
    createEAttribute(language_specificationEClass, LANGUAGE_SPECIFICATION__STR);

    external_function_callEClass = createEClass(EXTERNAL_FUNCTION_CALL);
    createEReference(external_function_callEClass, EXTERNAL_FUNCTION_CALL__COMPONENT_REFERENCE);
    createEReference(external_function_callEClass, EXTERNAL_FUNCTION_CALL__ELIST);

    class_modification_actionEClass = createEClass(CLASS_MODIFICATION_ACTION);
    createEReference(class_modification_actionEClass, CLASS_MODIFICATION_ACTION__ARG);

    algorithm_clauseEClass = createEClass(ALGORITHM_CLAUSE);
    createEReference(algorithm_clauseEClass, ALGORITHM_CLAUSE__ALGORITHM);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    ModeleditorPackage theModeleditorPackage = (ModeleditorPackage)EPackage.Registry.INSTANCE.getEPackage(ModeleditorPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    left_hand_component_referenceEClass.getESuperTypes().add(this.getmodification_alternatives());
    class_modificationEClass.getESuperTypes().add(this.getmodification());
    argumentEClass.getESuperTypes().add(this.getargument_list());
    element_modification_or_replaceableEClass.getESuperTypes().add(this.getargument());
    element_redeclarationEClass.getESuperTypes().add(this.getargument());
    component_declarationEClass.getESuperTypes().add(this.getcomponent_list());
    type_specifierEClass.getESuperTypes().add(this.getcomponent_clause());
    type_specifierEClass.getESuperTypes().add(this.getcomponent_clause1());
    declarationEClass.getESuperTypes().add(this.getcomponent_declaration1());
    class_specifierEClass.getESuperTypes().add(this.getclass_definition());
    extends_clauseEClass.getESuperTypes().add(this.getelement());
    import_clauseEClass.getESuperTypes().add(this.getelement());
    class_modification_actionEClass.getESuperTypes().add(this.getclass_modification());
    algorithm_clauseEClass.getESuperTypes().add(this.getalgorithm_section());

    // Initialize classes and features; add operations and parameters
    initEClass(modification_alternativesEClass, modification_alternatives.class, "modification_alternatives", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmodification_alternatives_Modification_Rule(), this.getmodification(), null, "Modification_Rule", null, 0, 1, modification_alternatives.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(left_hand_component_referenceEClass, left_hand_component_reference.class, "left_hand_component_reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getleft_hand_component_reference_Expr(), theModeleditorPackage.getexpression(), null, "Expr", null, 0, 1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getleft_hand_component_reference_Ref(), ecorePackage.getEString(), "ref", null, 0, 1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getleft_hand_component_reference_Subscripts1(), theModeleditorPackage.getarray_subscripts(), null, "subscripts1", null, 0, 1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getleft_hand_component_reference_Ref1(), ecorePackage.getEString(), "ref1", null, 0, -1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getleft_hand_component_reference_Subscripts(), theModeleditorPackage.getarray_subscripts(), null, "subscripts", null, 0, -1, left_hand_component_reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modificationEClass, modification.class, "modification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmodification_Expr(), theModeleditorPackage.getexpression(), null, "Expr", null, 0, 1, modification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(class_modificationEClass, class_modification.class, "class_modification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(argument_listEClass, argument_list.class, "argument_list", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(argumentEClass, argument.class, "argument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(element_modification_or_replaceableEClass, element_modification_or_replaceable.class, "element_modification_or_replaceable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getelement_modification_or_replaceable_Element_mod(), this.getelement_modification(), null, "Element_mod", null, 0, 1, element_modification_or_replaceable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_modification_or_replaceable_Element_rep(), this.getelement_replaceable(), null, "Element_rep", null, 0, 1, element_modification_or_replaceable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(element_modificationEClass, element_modification.class, "element_modification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getelement_modification_Name(), theModeleditorPackage.getname(), null, "Name", null, 0, 1, element_modification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_modification_Modification(), this.getmodification(), null, "Modification", null, 0, 1, element_modification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(element_replaceableEClass, element_replaceable.class, "element_replaceable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getelement_replaceable_Class_Def(), this.getclass_definition(), null, "Class_Def", null, 0, 1, element_replaceable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_replaceable_Comp_clause2(), this.getcomponent_clause1(), null, "Comp_clause2", null, 0, 1, element_replaceable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_replaceable_Constrain_Clause(), this.getconstraining_clause(), null, "Constrain_Clause", null, 0, 1, element_replaceable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(constraining_clauseEClass, constraining_clause.class, "constraining_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getconstraining_clause_Name(), theModeleditorPackage.getname(), null, "Name", null, 0, 1, constraining_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getconstraining_clause_Class_Mod(), this.getclass_modification(), null, "Class_Mod", null, 0, 1, constraining_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(element_redeclarationEClass, element_redeclaration.class, "element_redeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getelement_redeclaration_Class_Def(), this.getclass_definition(), null, "Class_Def", null, 0, 1, element_redeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_redeclaration_Comp_clause1(), this.getcomponent_clause1(), null, "Comp_clause1", null, 0, 1, element_redeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_redeclaration_Element_R(), this.getelement_replaceable(), null, "Element_R", null, 0, 1, element_redeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(component_clauseEClass, component_clause.class, "component_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(component_listEClass, component_list.class, "component_list", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(component_declarationEClass, component_declaration.class, "component_declaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getcomponent_declaration_Component_Dec(), this.getcomponent_declaration(), null, "component_Dec", null, 0, -1, component_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomponent_declaration_Dec(), this.getdeclaration(), null, "Dec", null, 0, 1, component_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomponent_declaration_Conditional_Att(), this.getconditional_attribute(), null, "Conditional_Att", null, 0, 1, component_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditional_attributeEClass, conditional_attribute.class, "conditional_attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getconditional_attribute_Expr(), theModeleditorPackage.getexpression(), null, "Expr", null, 0, 1, conditional_attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(component_clause1EClass, component_clause1.class, "component_clause1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(component_declaration1EClass, component_declaration1.class, "component_declaration1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(type_specifierEClass, type_specifier.class, "type_specifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(gettype_specifier_Array_Subs(), theModeleditorPackage.getarray_subscripts(), null, "Array_Subs", null, 0, 1, type_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(gettype_specifier_Com_List(), this.getcomponent_list(), null, "Com_List", null, 0, 1, type_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(gettype_specifier_Com_Dec(), this.getcomponent_declaration1(), null, "Com_Dec", null, 0, 1, type_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(gettype_specifier_Name(), theModeleditorPackage.getname(), null, "Name", null, 0, 1, type_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(declarationEClass, declaration.class, "declaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getdeclaration_RedeclaredComponentName(), ecorePackage.getEString(), "redeclaredComponentName", null, 0, 1, declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getdeclaration_ArraySubs(), theModeleditorPackage.getarray_subscripts(), null, "ArraySubs", null, 0, 1, declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getdeclaration_Mod(), this.getmodification(), null, "Mod", null, 0, 1, declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(class_definitionEClass, class_definition.class, "class_definition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(class_specifierEClass, class_specifier.class, "class_specifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getclass_specifier_Comp(), this.getcomposition(), null, "Comp", null, 0, 1, class_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getclass_specifier_Name(), theModeleditorPackage.getname(), null, "Name", null, 0, 1, class_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getclass_specifier_Array_Subs(), theModeleditorPackage.getarray_subscripts(), null, "Array_Subs", null, 0, 1, class_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getclass_specifier_Class_mod(), this.getclass_modification(), null, "Class_mod", null, 0, 1, class_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getclass_specifier_Var(), ecorePackage.getEString(), "var", null, 0, -1, class_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getclass_specifier_Enum_list(), this.getenum_list(), null, "Enum_list", null, 0, 1, class_specifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enum_listEClass, enum_list.class, "enum_list", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getenum_list_Ennum_Lit(), ecorePackage.getEString(), "Ennum_Lit", null, 0, 1, enum_list.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getenum_list_E_literal(), ecorePackage.getEString(), "E_literal", null, 0, -1, enum_list.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(compositionEClass, composition.class, "composition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getcomposition_E_List_Initial(), this.getelement_list(), null, "E_List_Initial", null, 0, 1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_E_List_Public(), this.getelement_list(), null, "E_List_Public", null, 0, -1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_E_List_Protected(), this.getelement_list(), null, "E_List_Protected", null, 0, -1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_Eqn_section(), this.getequation_section(), null, "Eqn_section", null, 0, -1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_Alg_section(), this.getalgorithm_section(), null, "Alg_section", null, 0, -1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_Lang_Spec(), this.getlanguage_specification(), null, "Lang_Spec", null, 0, 1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_External_F_C(), this.getexternal_function_call(), null, "External_F_C", null, 0, 1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_Annotation1(), this.getannotation(), null, "Annotation1", null, 0, 1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getcomposition_Annotation2(), this.getannotation(), null, "Annotation2", null, 0, 1, composition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(element_listEClass, element_list.class, "element_list", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getelement_list_Element(), this.getelement(), null, "Element", null, 0, -1, element_list.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(elementEClass, element.class, "element", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getelement_Class_Def(), this.getclass_definition(), null, "Class_Def", null, 0, 1, element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_Comp_Clause(), this.getcomponent_clause(), null, "Comp_Clause", null, 0, 1, element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_Constrain_Clause(), this.getconstraining_clause(), null, "Constrain_Clause", null, 0, 1, element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getelement_Name(), theModeleditorPackage.getname(), null, "Name", null, 0, 1, element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(equation_sectionEClass, equation_section.class, "equation_section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getequation_section_Eqn(), this.getequation(), null, "Eqn", null, 0, -1, equation_section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(equationEClass, equation.class, "equation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getequation_Sim(), theModeleditorPackage.getsimple_expression(), null, "sim", null, 0, 1, equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getequation_Expr(), theModeleditorPackage.getexpression(), null, "expr", null, 0, 1, equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getequation_IF_EQN(), this.getif_equation(), null, "IF_EQN", null, 0, 1, equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getequation_FOR_EQN(), this.getfor_equation(), null, "FOR_EQN", null, 0, 1, equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getequation_Con(), this.getconnect_clause(), null, "Con", null, 0, 1, equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getequation_WHEN_EQN(), this.getwhen_equation(), null, "WHEN_EQN", null, 0, 1, equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getequation_Comment(), ecorePackage.getEString(), "Comment", null, 0, 1, equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(when_equationEClass, when_equation.class, "when_equation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getwhen_equation_WhenEpr(), theModeleditorPackage.getexpression(), null, "whenEpr", null, 0, 1, when_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_equation_Eqn_Then(), this.getequation(), null, "Eqn_Then", null, 0, -1, when_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_equation_ElseWhenEpr(), theModeleditorPackage.getexpression(), null, "elseWhenEpr", null, 0, -1, when_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(if_equationEClass, if_equation.class, "if_equation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getif_equation_Exprtrue(), theModeleditorPackage.getexpression(), null, "exprtrue", null, 0, 1, if_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getif_equation_Eqn(), this.getequation(), null, "Eqn", null, 0, -1, if_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getif_equation_ExprStilltrue(), theModeleditorPackage.getexpression(), null, "exprStilltrue", null, 0, -1, if_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getif_equation_ThenEqn(), this.getequation(), null, "thenEqn", null, 0, -1, if_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getif_equation_ElseEqn(), this.getequation(), null, "elseEqn", null, 0, -1, if_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(for_equationEClass, for_equation.class, "for_equation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getfor_equation_For_loop(), theModeleditorPackage.getfor_indices(), null, "for_loop", null, 0, 1, for_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfor_equation_For_Eqn(), this.getequation(), null, "For_Eqn", null, 0, -1, for_equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(connect_clauseEClass, connect_clause.class, "connect_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getconnect_clause_Connector1(), theModeleditorPackage.getcomponent_reference(), null, "connector1", null, 0, 1, connect_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getconnect_clause_Connector2(), theModeleditorPackage.getcomponent_reference(), null, "connector2", null, 0, 1, connect_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(algorithm_sectionEClass, algorithm_section.class, "algorithm_section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(statementEClass, statement.class, "statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getstatement_If_statement(), this.getif_statement(), null, "If_statement", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_State_OutputExprList(), this.getstate_OutputExprList(), null, "State_OutputExprList", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_State_Comp_Ref(), this.getstate_Comp_Ref(), null, "state_Comp_Ref", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_For_statement(), this.getfor_statement(), null, "For_statement", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_When_statement(), this.getwhen_statement(), null, "When_statement", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_While_statement(), this.getwhile_statement(), null, "While_statement", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(state_OutputExprListEClass, state_OutputExprList.class, "state_OutputExprList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getstate_OutputExprList_Output_expression_list(), theModeleditorPackage.getoutput_expression_list(), null, "output_expression_list", null, 0, 1, state_OutputExprList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstate_OutputExprList_Component_ref(), theModeleditorPackage.getcomponent_reference(), null, "Component_ref", null, 0, 1, state_OutputExprList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstate_OutputExprList_Fun_call_args(), theModeleditorPackage.getfunction_call_args(), null, "fun_call_args", null, 0, 1, state_OutputExprList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(state_Comp_RefEClass, state_Comp_Ref.class, "state_Comp_Ref", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getstate_Comp_Ref_Component_reference(), theModeleditorPackage.getcomponent_reference(), null, "component_reference", null, 0, 1, state_Comp_Ref.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstate_Comp_Ref_Expr(), theModeleditorPackage.getexpression(), null, "expr", null, 0, 1, state_Comp_Ref.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstate_Comp_Ref_Fun_call_args(), theModeleditorPackage.getfunction_call_args(), null, "fun_call_args", null, 0, 1, state_Comp_Ref.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(when_statementEClass, when_statement.class, "when_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getwhen_statement_When_expr(), theModeleditorPackage.getexpression(), null, "When_expr", null, 0, 1, when_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_statement_When_stat_true(), this.getstatement(), null, "When_stat_true", null, 0, -1, when_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_statement_Else_When_expr(), theModeleditorPackage.getexpression(), null, "Else_When_expr", null, 0, -1, when_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhen_statement_Then_statement(), this.getstatement(), null, "Then_statement", null, 0, -1, when_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(while_statementEClass, while_statement.class, "while_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getwhile_statement_While_expr(), theModeleditorPackage.getexpression(), null, "while_expr", null, 0, 1, while_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getwhile_statement_While_stat(), this.getwhile_statement(), null, "While_stat", null, 0, -1, while_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(for_statementEClass, for_statement.class, "for_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getfor_statement_For_indices(), theModeleditorPackage.getfor_indices(), null, "For_indices", null, 0, 1, for_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfor_statement_For_statement(), this.getfor_statement(), null, "For_statement", null, 0, -1, for_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(if_statementEClass, if_statement.class, "if_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getif_statement_Exprtrue(), theModeleditorPackage.getexpression(), null, "exprtrue", null, 0, 1, if_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getif_statement_State(), this.getstatement(), null, "state", null, 0, -1, if_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getif_statement_ExprStilltrue(), theModeleditorPackage.getexpression(), null, "exprStilltrue", null, 0, -1, if_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extends_clauseEClass, extends_clause.class, "extends_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getextends_clause_Class_Mod(), this.getclass_modification(), null, "Class_Mod", null, 0, 1, extends_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getextends_clause_Annotation(), this.getannotation(), null, "Annotation", null, 0, 1, extends_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(annotationEClass, annotation.class, "annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getannotation_Class_Mod(), this.getclass_modification(), null, "Class_Mod", null, 0, 1, annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(import_clauseEClass, import_clause.class, "import_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(language_specificationEClass, language_specification.class, "language_specification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getlanguage_specification_Str(), ecorePackage.getEString(), "Str", null, 0, 1, language_specification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(external_function_callEClass, external_function_call.class, "external_function_call", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getexternal_function_call_Component_reference(), theModeleditorPackage.getcomponent_reference(), null, "component_reference", null, 0, 1, external_function_call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getexternal_function_call_E_List(), theModeleditorPackage.getexpression_list(), null, "E_List", null, 0, 1, external_function_call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(class_modification_actionEClass, class_modification_action.class, "class_modification_action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getclass_modification_action_Arg(), this.getargument_list(), null, "Arg", null, 0, 1, class_modification_action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(algorithm_clauseEClass, algorithm_clause.class, "algorithm_clause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getalgorithm_clause_Algorithm(), this.getstatement(), null, "Algorithm", null, 0, -1, algorithm_clause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ModificationPackageImpl
