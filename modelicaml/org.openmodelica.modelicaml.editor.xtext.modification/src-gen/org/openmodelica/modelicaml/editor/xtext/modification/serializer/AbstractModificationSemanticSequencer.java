package org.openmodelica.modelicaml.editor.xtext.modification.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ExprDer;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_exp;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.Fun_Arguments_for;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.ModeleditorPackage;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.arithmetic_expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.array_subscripts;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.component_reference;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.conditional_expr;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.expression_list;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.for_index;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_arguments;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.function_call_args;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.logical_factor;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.name;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.named_argument;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.output_expression_list;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.primary;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.simple_expression;
import org.openmodelica.modelicaml.editor.xtext.model.modeleditor.subscript;
import org.openmodelica.modelicaml.editor.xtext.model.serializer.ModeleditorSemanticSequencer;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.ModificationPackage;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.algorithm_clause;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.annotation;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_modification_action;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.class_specifier;
import org.openmodelica.modelicaml.editor.xtext.modification.modification.component_declaration;
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
import org.openmodelica.modelicaml.editor.xtext.modification.services.ModificationGrammarAccess;

import com.google.inject.Inject;
import com.google.inject.Provider;

@SuppressWarnings("restriction")
public class AbstractModificationSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected ModificationGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	@Inject
	protected Provider<ModeleditorSemanticSequencer> superSequencerProvider;
	 
	protected ModeleditorSemanticSequencer superSequencer; 
	
	@Override
	public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
		super.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.genericSequencer = genericSequencerProvider.get();
		this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.superSequencer = superSequencerProvider.get();
		this.superSequencer.init(sequencer, sequenceAcceptor, errorAcceptor); 
	}
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == ModeleditorPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ModeleditorPackage.EXPR_DER:
				if(context == grammarAccess.getExprDerRule() ||
				   context == grammarAccess.getPrimaryRule()) {
					sequence_ExprDer(context, (ExprDer) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getFactorRule()) {
					sequence_factor(context, (ExprDer) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTermRule()) {
					sequence_term(context, (ExprDer) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUN_ARGUMENTS_EXP:
				if(context == grammarAccess.getFun_Arguments_expRule()) {
					sequence_Fun_Arguments_exp(context, (Fun_Arguments_exp) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUN_ARGUMENTS_FOR:
				if(context == grammarAccess.getFun_Arguments_forRule()) {
					sequence_Fun_Arguments_for(context, (Fun_Arguments_for) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.ARITHMETIC_EXPRESSION:
				if(context == grammarAccess.getArithmetic_expressionRule()) {
					sequence_arithmetic_expression(context, (arithmetic_expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getRelationRule()) {
					sequence_relation(context, (arithmetic_expression) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.ARRAY_SUBSCRIPTS:
				if(context == grammarAccess.getArray_subscriptsRule()) {
					sequence_array_subscripts(context, (array_subscripts) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.COMPONENT_REFERENCE:
				if(context == grammarAccess.getComponent_referenceRule()) {
					sequence_component_reference(context, (component_reference) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.CONDITIONAL_EXPR:
				if(context == grammarAccess.getConditional_exprRule() ||
				   context == grammarAccess.getExpressionRule()) {
					sequence_conditional_expr(context, (conditional_expr) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.EXPRESSION:
				if(context == grammarAccess.getExpressionRule()) {
					sequence_expression(context, (expression) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.EXPRESSION_LIST:
				if(context == grammarAccess.getExpression_listRule()) {
					sequence_expression_list(context, (expression_list) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FOR_INDEX:
				if(context == grammarAccess.getFor_indexRule()) {
					sequence_for_index(context, (for_index) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getFor_indicesRule()) {
					sequence_for_indices(context, (for_index) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUNCTION_ARGUMENTS:
				if(context == grammarAccess.getFunction_argumentsRule()) {
					sequence_function_arguments(context, (function_arguments) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.FUNCTION_CALL_ARGS:
				if(context == grammarAccess.getFactorRule()) {
					sequence_factor(context, (function_call_args) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getFunction_call_argsRule() ||
				   context == grammarAccess.getInitial_refRule() ||
				   context == grammarAccess.getPrimaryRule()) {
					sequence_function_call_args(context, (function_call_args) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTermRule()) {
					sequence_term(context, (function_call_args) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.LOGICAL_FACTOR:
				if(context == grammarAccess.getLogical_expressionRule()) {
					sequence_logical_expression(context, (logical_factor) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getLogical_factorRule()) {
					sequence_logical_factor(context, (logical_factor) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getLogical_termRule()) {
					sequence_logical_term(context, (logical_factor) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.NAME:
				if(context == grammarAccess.getFactorRule()) {
					sequence_factor(context, (name) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getName_FunctionRule() ||
				   context == grammarAccess.getPrimaryRule()) {
					sequence_name_Function(context, (name) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getNameRule()) {
					sequence_name(context, (name) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTermRule()) {
					sequence_term(context, (name) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.NAMED_ARGUMENT:
				if(context == grammarAccess.getNamed_argumentRule()) {
					sequence_named_argument(context, (named_argument) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getNamed_argumentsRule()) {
					sequence_named_arguments(context, (named_argument) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.OUTPUT_EXPRESSION_LIST:
				if(context == grammarAccess.getOutput_expression_listRule()) {
					sequence_output_expression_list(context, (output_expression_list) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.PRIMARY:
				if(context == grammarAccess.getFactorRule()) {
					sequence_factor(context, (primary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getPrimaryRule()) {
					sequence_primary(context, (primary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTermRule()) {
					sequence_term(context, (primary) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.SIMPLE_EXPRESSION:
				if(context == grammarAccess.getSimple_expressionRule()) {
					sequence_simple_expression(context, (simple_expression) semanticObject); 
					return; 
				}
				else break;
			case ModeleditorPackage.SUBSCRIPT:
				if(context == grammarAccess.getSubscriptRule()) {
					sequence_subscript(context, (subscript) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == ModificationPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ModificationPackage.ALGORITHM_CLAUSE:
				if(context == grammarAccess.getAlgorithm_sectionRule()) {
					sequence_algorithm_section(context, (algorithm_clause) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ANNOTATION:
				if(context == grammarAccess.getAnnotationRule()) {
					sequence_annotation(context, (annotation) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.CLASS_MODIFICATION_ACTION:
				if(context == grammarAccess.getClass_modificationRule()) {
					sequence_class_modification(context, (class_modification_action) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getModificationRule()) {
					sequence_modification(context, (class_modification_action) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.CLASS_SPECIFIER:
				if(context == grammarAccess.getClass_definitionRule() ||
				   context == grammarAccess.getClass_specifierRule()) {
					sequence_class_specifier(context, (class_specifier) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.COMPONENT_DECLARATION:
				if(context == grammarAccess.getComponent_declarationRule()) {
					sequence_component_declaration(context, (component_declaration) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getComponent_listRule()) {
					sequence_component_list(context, (component_declaration) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.COMPOSITION:
				if(context == grammarAccess.getCompositionRule()) {
					sequence_composition(context, (composition) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.CONDITIONAL_ATTRIBUTE:
				if(context == grammarAccess.getConditional_attributeRule()) {
					sequence_conditional_attribute(context, (conditional_attribute) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.CONNECT_CLAUSE:
				if(context == grammarAccess.getConnect_clauseRule()) {
					sequence_connect_clause(context, (connect_clause) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.CONSTRAINING_CLAUSE:
				if(context == grammarAccess.getConstraining_clauseRule()) {
					sequence_constraining_clause(context, (constraining_clause) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.DECLARATION:
				if(context == grammarAccess.getComponent_declaration1Rule() ||
				   context == grammarAccess.getDeclarationRule()) {
					sequence_declaration(context, (declaration) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ELEMENT:
				if(context == grammarAccess.getElementRule()) {
					sequence_element(context, (element) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ELEMENT_LIST:
				if(context == grammarAccess.getElement_listRule()) {
					sequence_element_list(context, (element_list) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ELEMENT_MODIFICATION:
				if(context == grammarAccess.getElement_modificationRule()) {
					sequence_element_modification(context, (element_modification) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ELEMENT_MODIFICATION_OR_REPLACEABLE:
				if(context == grammarAccess.getArgumentRule() ||
				   context == grammarAccess.getArgument_listRule() ||
				   context == grammarAccess.getElement_modification_or_replaceableRule()) {
					sequence_element_modification_or_replaceable(context, (element_modification_or_replaceable) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ELEMENT_REDECLARATION:
				if(context == grammarAccess.getArgumentRule() ||
				   context == grammarAccess.getArgument_listRule() ||
				   context == grammarAccess.getElement_redeclarationRule()) {
					sequence_element_redeclaration(context, (element_redeclaration) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ELEMENT_REPLACEABLE:
				if(context == grammarAccess.getElement_replaceableRule()) {
					sequence_element_replaceable(context, (element_replaceable) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.ENUM_LIST:
				if(context == grammarAccess.getEnum_listRule()) {
					sequence_enum_list(context, (enum_list) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.EQUATION:
				if(context == grammarAccess.getEquationRule()) {
					sequence_equation(context, (equation) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.EQUATION_SECTION:
				if(context == grammarAccess.getEquation_sectionRule()) {
					sequence_equation_section(context, (equation_section) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.EXTENDS_CLAUSE:
				if(context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getExtends_clauseRule()) {
					sequence_extends_clause(context, (extends_clause) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.EXTERNAL_FUNCTION_CALL:
				if(context == grammarAccess.getExternal_function_callRule()) {
					sequence_external_function_call(context, (external_function_call) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.FOR_EQUATION:
				if(context == grammarAccess.getFor_equationRule()) {
					sequence_for_equation(context, (for_equation) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.FOR_STATEMENT:
				if(context == grammarAccess.getFor_statementRule()) {
					sequence_for_statement(context, (for_statement) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.IF_EQUATION:
				if(context == grammarAccess.getIf_equationRule()) {
					sequence_if_equation(context, (if_equation) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.IF_STATEMENT:
				if(context == grammarAccess.getIf_statementRule()) {
					sequence_if_statement(context, (if_statement) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.IMPORT_CLAUSE:
				if(context == grammarAccess.getElementRule() ||
				   context == grammarAccess.getImport_clauseRule()) {
					sequence_import_clause(context, (import_clause) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.LANGUAGE_SPECIFICATION:
				if(context == grammarAccess.getLanguage_specificationRule()) {
					sequence_language_specification(context, (language_specification) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.LEFT_HAND_COMPONENT_REFERENCE:
				if(context == grammarAccess.getLeft_hand_component_referenceRule()) {
					sequence_left_hand_component_reference(context, (left_hand_component_reference) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getModification_alternativesRule()) {
					sequence_modification_alternatives(context, (left_hand_component_reference) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.MODIFICATION:
				if(context == grammarAccess.getModificationRule()) {
					sequence_modification(context, (modification) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.MODIFICATION_ALTERNATIVES:
				if(context == grammarAccess.getModification_alternativesRule()) {
					sequence_modification_alternatives(context, (modification_alternatives) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.STATE_COMP_REF:
				if(context == grammarAccess.getState_Comp_RefRule()) {
					sequence_state_Comp_Ref(context, (state_Comp_Ref) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.STATE_OUTPUT_EXPR_LIST:
				if(context == grammarAccess.getState_OutputExprListRule()) {
					sequence_state_OutputExprList(context, (state_OutputExprList) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.STATEMENT:
				if(context == grammarAccess.getStatementRule()) {
					sequence_statement(context, (statement) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.TYPE_SPECIFIER:
				if(context == grammarAccess.getComponent_clause1Rule()) {
					sequence_component_clause1(context, (type_specifier) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getComponent_clauseRule()) {
					sequence_component_clause(context, (type_specifier) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getType_specifierRule()) {
					sequence_type_specifier(context, (type_specifier) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.WHEN_EQUATION:
				if(context == grammarAccess.getWhen_equationRule()) {
					sequence_when_equation(context, (when_equation) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.WHEN_STATEMENT:
				if(context == grammarAccess.getWhen_statementRule()) {
					sequence_when_statement(context, (when_statement) semanticObject); 
					return; 
				}
				else break;
			case ModificationPackage.WHILE_STATEMENT:
				if(context == grammarAccess.getWhile_statementRule()) {
					sequence_while_statement(context, (while_statement) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     functionArgs=function_call_args
	 *
	 * Features:
	 *    functionArgs[1, 1]
	 */
	protected void sequence_ExprDer(EObject context, ExprDer semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Args=function_arguments
	 *
	 * Features:
	 *    Args[1, 1]
	 */
	protected void sequence_Fun_Arguments_exp(EObject context, Fun_Arguments_exp semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     For_indices=for_indices
	 *
	 * Features:
	 *    For_indices[1, 1]
	 */
	protected void sequence_Fun_Arguments_for(EObject context, Fun_Arguments_for semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Algorithm+=statement*)
	 *
	 * Features:
	 *    Algorithm[0, *]
	 */
	protected void sequence_algorithm_section(EObject context, algorithm_clause semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Class_Mod=class_modification
	 *
	 * Features:
	 *    Class_Mod[1, 1]
	 */
	protected void sequence_annotation(EObject context, annotation semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.ANNOTATION__CLASS_MOD) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.ANNOTATION__CLASS_MOD));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAnnotationAccess().getClass_ModClass_modificationParserRuleCall_1_0(), semanticObject.getClass_Mod());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (opr=add_op_plus | opr=add_op_minus | opr=add_op_dotplus | opr=add_op_dotminus)? 
	 *         Term=term 
	 *         ((Oper1+=add_op_plus | Oper1+=add_op_minus | Oper1+=add_op_dotplus | Oper1+=add_op_dotminus) Term1+=term)*
	 *     )
	 *
	 * Features:
	 *    opr[0, 4]
	 *    Term[1, 1]
	 *    Oper1[0, *]
	 *         SAME_OR_LESS Term1
	 *    Term1[0, *]
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 */
	protected void sequence_arithmetic_expression(EObject context, arithmetic_expression semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Sub=subscript Subscript+=subscript*)
	 *
	 * Features:
	 *    Sub[1, 1]
	 *    Subscript[0, *]
	 */
	protected void sequence_array_subscripts(EObject context, array_subscripts semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Arg=argument_list?)
	 *
	 * Features:
	 *    Arg[0, 1]
	 */
	protected void sequence_class_modification(EObject context, class_modification_action semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Comp=composition | 
	 *         (Name=name Array_Subs=array_subscripts? Class_mod=class_modification?) | 
	 *         (var+=IDENT Enum_list=enum_list?) | 
	 *         Name=name | 
	 *         (Class_mod=class_modification? Comp=composition)
	 *     )
	 *
	 * Features:
	 *    Comp[0, 2]
	 *         EXCLUDE_IF_SET Name
	 *         EXCLUDE_IF_SET Array_Subs
	 *         EXCLUDE_IF_SET Class_mod
	 *         EXCLUDE_IF_SET var
	 *         EXCLUDE_IF_SET Enum_list
	 *         EXCLUDE_IF_SET Name
	 *    Name[0, 2]
	 *         EXCLUDE_IF_SET Comp
	 *         EXCLUDE_IF_SET var
	 *         EXCLUDE_IF_SET Enum_list
	 *         EXCLUDE_IF_SET Class_mod
	 *         EXCLUDE_IF_SET Comp
	 *    Array_Subs[0, 1]
	 *         EXCLUDE_IF_UNSET Name
	 *         EXCLUDE_IF_SET Comp
	 *         EXCLUDE_IF_SET var
	 *         EXCLUDE_IF_SET Enum_list
	 *         EXCLUDE_IF_SET Name
	 *         EXCLUDE_IF_SET Class_mod
	 *         EXCLUDE_IF_SET Comp
	 *    Class_mod[0, 2]
	 *         EXCLUDE_IF_SET Comp
	 *         EXCLUDE_IF_SET var
	 *         EXCLUDE_IF_SET Enum_list
	 *         EXCLUDE_IF_SET Name
	 *    var[0, 1]
	 *         MANDATORY_IF_SET Enum_list
	 *         EXCLUDE_IF_SET Comp
	 *         EXCLUDE_IF_SET Name
	 *         EXCLUDE_IF_SET Array_Subs
	 *         EXCLUDE_IF_SET Class_mod
	 *         EXCLUDE_IF_SET Name
	 *         EXCLUDE_IF_SET Class_mod
	 *         EXCLUDE_IF_SET Comp
	 *    Enum_list[0, 1]
	 *         EXCLUDE_IF_UNSET var
	 *         EXCLUDE_IF_SET Comp
	 *         EXCLUDE_IF_SET Name
	 *         EXCLUDE_IF_SET Array_Subs
	 *         EXCLUDE_IF_SET Class_mod
	 *         EXCLUDE_IF_SET Name
	 *         EXCLUDE_IF_SET Class_mod
	 *         EXCLUDE_IF_SET Comp
	 */
	protected void sequence_class_specifier(EObject context, class_specifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Name=name Com_Dec=component_declaration1)
	 *
	 * Features:
	 *    Com_Dec[1, 1]
	 *    Name[1, 1]
	 */
	protected void sequence_component_clause1(EObject context, type_specifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Name=name Array_Subs=array_subscripts? Com_List=component_list)
	 *
	 * Features:
	 *    Array_Subs[0, 1]
	 *    Com_List[1, 1]
	 *    Name[1, 1]
	 */
	protected void sequence_component_clause(EObject context, type_specifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Dec=declaration Conditional_Att=conditional_attribute?)
	 *
	 * Features:
	 *    Dec[1, 1]
	 *    Conditional_Att[0, 1]
	 */
	protected void sequence_component_declaration(EObject context, component_declaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Dec=declaration Conditional_Att=conditional_attribute? component_Dec+=component_declaration*)
	 *
	 * Features:
	 *    component_Dec[0, *]
	 *    Dec[1, 1]
	 *    Conditional_Att[0, 1]
	 */
	protected void sequence_component_list(EObject context, component_declaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ref=IDENT subscripts1=array_subscripts? (ref1+=IDENT subscripts+=array_subscripts?)*)
	 *
	 * Features:
	 *    ref[1, 1]
	 *    subscripts1[0, 1]
	 *    ref1[0, *]
	 *         SAME_OR_MORE subscripts
	 *    subscripts[0, *]
	 *         SAME_OR_LESS ref1
	 */
	protected void sequence_component_reference(EObject context, component_reference semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         E_List_Initial=element_list 
	 *         (E_List_Public+=element_list | E_List_Protected+=element_list | Eqn_section+=equation_section | Alg_section+=algorithm_section)* 
	 *         (Lang_Spec=language_specification? External_F_C=external_function_call? Annotation1=annotation?)? 
	 *         Annotation2=annotation?
	 *     )
	 *
	 * Features:
	 *    E_List_Initial[1, 1]
	 *    E_List_Public[0, *]
	 *    E_List_Protected[0, *]
	 *    Eqn_section[0, *]
	 *    Alg_section[0, *]
	 *    Lang_Spec[0, 1]
	 *    External_F_C[0, 1]
	 *    Annotation1[0, 1]
	 *    Annotation2[0, 1]
	 */
	protected void sequence_composition(EObject context, composition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Expr=expression
	 *
	 * Features:
	 *    Expr[1, 1]
	 */
	protected void sequence_conditional_attribute(EObject context, conditional_attribute semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.CONDITIONAL_ATTRIBUTE__EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.CONDITIONAL_ATTRIBUTE__EXPR));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getConditional_attributeAccess().getExprExpressionParserRuleCall_1_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (ifexpr=expression thenexpr=expression (elseifexpr+=expression trueexpr+=expression)* falseexpr=expression)
	 *
	 * Features:
	 *    ifexpr[1, 1]
	 *    thenexpr[1, 1]
	 *    elseifexpr[0, *]
	 *         SAME trueexpr
	 *    trueexpr[0, *]
	 *         SAME elseifexpr
	 *    falseexpr[1, 1]
	 */
	protected void sequence_conditional_expr(EObject context, conditional_expr semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (connector1=component_reference connector2=component_reference)
	 *
	 * Features:
	 *    connector1[1, 1]
	 *    connector2[1, 1]
	 */
	protected void sequence_connect_clause(EObject context, connect_clause semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.CONNECT_CLAUSE__CONNECTOR1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.CONNECT_CLAUSE__CONNECTOR1));
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.CONNECT_CLAUSE__CONNECTOR2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.CONNECT_CLAUSE__CONNECTOR2));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getConnect_clauseAccess().getConnector1Component_referenceParserRuleCall_2_0(), semanticObject.getConnector1());
		feeder.accept(grammarAccess.getConnect_clauseAccess().getConnector2Component_referenceParserRuleCall_4_0(), semanticObject.getConnector2());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (Name=name Class_Mod=class_modification?)
	 *
	 * Features:
	 *    Name[1, 1]
	 *    Class_Mod[0, 1]
	 */
	protected void sequence_constraining_clause(EObject context, constraining_clause semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (redeclaredComponentName=IDENT ArraySubs=array_subscripts? Mod=modification?)
	 *
	 * Features:
	 *    redeclaredComponentName[1, 1]
	 *    ArraySubs[0, 1]
	 *    Mod[0, 1]
	 */
	protected void sequence_declaration(EObject context, declaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Class_Def=class_definition | 
	 *         Comp_Clause=component_clause | 
	 *         ((Class_Def=class_definition | Comp_Clause=component_clause) Constrain_Clause=constraining_clause?)
	 *     )
	 *
	 * Features:
	 *    Class_Def[0, 2]
	 *         EXCLUDE_IF_SET Comp_Clause
	 *         EXCLUDE_IF_SET Comp_Clause
	 *    Comp_Clause[0, 2]
	 *         EXCLUDE_IF_SET Class_Def
	 *         EXCLUDE_IF_SET Class_Def
	 *    Constrain_Clause[0, 1]
	 *         EXCLUDE_IF_SET Class_Def
	 *         EXCLUDE_IF_SET Comp_Clause
	 */
	protected void sequence_element(EObject context, element semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Element+=element*)
	 *
	 * Features:
	 *    Element[0, *]
	 */
	protected void sequence_element_list(EObject context, element_list semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Name=name Modification=modification?)
	 *
	 * Features:
	 *    Name[1, 1]
	 *    Modification[0, 1]
	 */
	protected void sequence_element_modification(EObject context, element_modification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Element_mod=element_modification | Element_rep=element_replaceable)
	 *
	 * Features:
	 *    Element_mod[0, 1]
	 *         EXCLUDE_IF_SET Element_rep
	 *    Element_rep[0, 1]
	 *         EXCLUDE_IF_SET Element_mod
	 */
	protected void sequence_element_modification_or_replaceable(EObject context, element_modification_or_replaceable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Class_Def=class_definition | Comp_clause1=component_clause1 | Element_R=element_replaceable)
	 *
	 * Features:
	 *    Class_Def[0, 1]
	 *         EXCLUDE_IF_SET Comp_clause1
	 *         EXCLUDE_IF_SET Element_R
	 *    Comp_clause1[0, 1]
	 *         EXCLUDE_IF_SET Class_Def
	 *         EXCLUDE_IF_SET Element_R
	 *    Element_R[0, 1]
	 *         EXCLUDE_IF_SET Class_Def
	 *         EXCLUDE_IF_SET Comp_clause1
	 */
	protected void sequence_element_redeclaration(EObject context, element_redeclaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((Class_Def=class_definition | Comp_clause2=component_clause1) Constrain_Clause=constraining_clause?)
	 *
	 * Features:
	 *    Class_Def[0, 1]
	 *         EXCLUDE_IF_SET Comp_clause2
	 *    Comp_clause2[0, 1]
	 *         EXCLUDE_IF_SET Class_Def
	 *    Constrain_Clause[0, 1]
	 */
	protected void sequence_element_replaceable(EObject context, element_replaceable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Ennum_Lit=enumeration_literal E_literal+=enumeration_literal*)
	 *
	 * Features:
	 *    Ennum_Lit[1, 1]
	 *    E_literal[0, *]
	 */
	protected void sequence_enum_list(EObject context, enum_list semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         ((sim=simple_expression expr=expression) | IF_EQN=if_equation | FOR_EQN=for_equation | Con=connect_clause | WHEN_EQN=when_equation) 
	 *         Comment=comment
	 *     )
	 *
	 * Features:
	 *    sim[0, 1]
	 *         EXCLUDE_IF_UNSET expr
	 *         MANDATORY_IF_SET expr
	 *         EXCLUDE_IF_SET IF_EQN
	 *         EXCLUDE_IF_SET FOR_EQN
	 *         EXCLUDE_IF_SET Con
	 *         EXCLUDE_IF_SET WHEN_EQN
	 *    expr[0, 1]
	 *         EXCLUDE_IF_UNSET sim
	 *         MANDATORY_IF_SET sim
	 *         EXCLUDE_IF_SET IF_EQN
	 *         EXCLUDE_IF_SET FOR_EQN
	 *         EXCLUDE_IF_SET Con
	 *         EXCLUDE_IF_SET WHEN_EQN
	 *    IF_EQN[0, 1]
	 *         EXCLUDE_IF_SET sim
	 *         EXCLUDE_IF_SET expr
	 *         EXCLUDE_IF_SET FOR_EQN
	 *         EXCLUDE_IF_SET Con
	 *         EXCLUDE_IF_SET WHEN_EQN
	 *    FOR_EQN[0, 1]
	 *         EXCLUDE_IF_SET sim
	 *         EXCLUDE_IF_SET expr
	 *         EXCLUDE_IF_SET IF_EQN
	 *         EXCLUDE_IF_SET Con
	 *         EXCLUDE_IF_SET WHEN_EQN
	 *    Con[0, 1]
	 *         EXCLUDE_IF_SET sim
	 *         EXCLUDE_IF_SET expr
	 *         EXCLUDE_IF_SET IF_EQN
	 *         EXCLUDE_IF_SET FOR_EQN
	 *         EXCLUDE_IF_SET WHEN_EQN
	 *    WHEN_EQN[0, 1]
	 *         EXCLUDE_IF_SET sim
	 *         EXCLUDE_IF_SET expr
	 *         EXCLUDE_IF_SET IF_EQN
	 *         EXCLUDE_IF_SET FOR_EQN
	 *         EXCLUDE_IF_SET Con
	 *    Comment[1, 1]
	 */
	protected void sequence_equation(EObject context, equation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Eqn+=equation*)
	 *
	 * Features:
	 *    Eqn[0, *]
	 */
	protected void sequence_equation_section(EObject context, equation_section semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Expr=simple_expression
	 *
	 * Features:
	 *    Expr[1, 1]
	 */
	protected void sequence_expression(EObject context, expression semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expr=expression Expre+=expression*)
	 *
	 * Features:
	 *    expr[1, 1]
	 *    Expre[0, *]
	 */
	protected void sequence_expression_list(EObject context, expression_list semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Name=name Class_Mod=class_modification? Annotation=annotation?)
	 *
	 * Features:
	 *    Name[1, 1]
	 *    Class_Mod[0, 1]
	 *    Annotation[0, 1]
	 */
	protected void sequence_extends_clause(EObject context, extends_clause semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (component_reference=component_reference? E_List=expression_list?)
	 *
	 * Features:
	 *    component_reference[0, 1]
	 *    E_List[0, 1]
	 */
	protected void sequence_external_function_call(EObject context, external_function_call semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (functionArgs=function_call_args Primary=primary?)
	 *
	 * Features:
	 *    Primary[0, 1]
	 *    functionArgs[1, 1]
	 */
	protected void sequence_factor(EObject context, ExprDer semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (f_arg=function_arguments? Primary=primary?)
	 *
	 * Features:
	 *    Primary[0, 1]
	 *    f_arg[0, 1]
	 */
	protected void sequence_factor(EObject context, function_call_args semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name_ID=IDENT nam_ID+=IDENT* Function_call_args=function_call_args Primary=primary?)
	 *
	 * Features:
	 *    Primary[0, 1]
	 *    Function_call_args[1, 1]
	 *    name_ID[1, 1]
	 *    nam_ID[0, *]
	 */
	protected void sequence_factor(EObject context, name semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             num=UNSIGNED_NUMBER | 
	 *             int=INT | 
	 *             str=STRING | 
	 *             Bool=BOOL_VAL | 
	 *             Component_reference=component_reference | 
	 *             output_expr_list=output_expression_list | 
	 *             (Expre_list=expression_list Expression_list+=expression_list*) | 
	 *             f_arguments=function_arguments | 
	 *             End='end'
	 *         ) 
	 *         Primary=primary?
	 *     )
	 *
	 * Features:
	 *    Primary[0, 1]
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    str[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Bool[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    output_expr_list[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Expre_list[0, 1]
	 *         MANDATORY_IF_SET Expression_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Expression_list[0, *]
	 *         EXCLUDE_IF_UNSET Expre_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    f_arguments[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET End
	 *    End[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 */
	protected void sequence_factor(EObject context, primary semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (for_loop=for_indices For_Eqn+=equation)
	 *
	 * Features:
	 *    for_loop[1, 1]
	 *    For_Eqn[1, 1]
	 */
	protected void sequence_for_equation(EObject context, for_equation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (index=IDENT expr=expression?)
	 *
	 * Features:
	 *    index[1, 1]
	 *    expr[0, 1]
	 */
	protected void sequence_for_index(EObject context, for_index semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (index=IDENT expr=expression? For_index+=for_index*)
	 *
	 * Features:
	 *    For_index[0, *]
	 *    index[1, 1]
	 *    expr[0, 1]
	 */
	protected void sequence_for_indices(EObject context, for_index semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (For_indices=for_indices For_statement+=for_statement*)
	 *
	 * Features:
	 *    For_indices[1, 1]
	 *    For_statement[0, *]
	 */
	protected void sequence_for_statement(EObject context, for_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)?)
	 *
	 * Features:
	 *    ArgExp[1, 1]
	 *    Fun_Arg_Expr[0, 1]
	 *         EXCLUDE_IF_SET Fun_Arg_For
	 *    Fun_Arg_For[0, 1]
	 *         EXCLUDE_IF_SET Fun_Arg_Expr
	 */
	protected void sequence_function_arguments(EObject context, function_arguments semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (f_arg=function_arguments?)
	 *
	 * Features:
	 *    f_arg[0, 1]
	 */
	protected void sequence_function_call_args(EObject context, function_call_args semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exprtrue=expression Eqn+=equation* (exprStilltrue+=expression thenEqn+=equation*)* elseEqn+=equation*)
	 *
	 * Features:
	 *    exprtrue[1, 1]
	 *    Eqn[0, *]
	 *    exprStilltrue[0, *]
	 *         MANDATORY_IF_SET thenEqn
	 *    thenEqn[0, *]
	 *         EXCLUDE_IF_UNSET exprStilltrue
	 *    elseEqn[0, *]
	 */
	protected void sequence_if_equation(EObject context, if_equation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exprtrue=expression state+=statement* (exprStilltrue+=expression state+=statement*)* state+=statement*)
	 *
	 * Features:
	 *    exprtrue[1, 1]
	 *    state[0, *]
	 *    exprStilltrue[0, *]
	 *         MANDATORY_IF_SET state
	 */
	protected void sequence_if_statement(EObject context, if_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Name=name | Name=name)
	 *
	 * Features:
	 *    Name[0, 2]
	 */
	protected void sequence_import_clause(EObject context, import_clause semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Str=STRING
	 *
	 * Features:
	 *    Str[1, 1]
	 */
	protected void sequence_language_specification(EObject context, language_specification semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.LANGUAGE_SPECIFICATION__STR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.LANGUAGE_SPECIFICATION__STR));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLanguage_specificationAccess().getStrSTRINGTerminalRuleCall_0(), semanticObject.getStr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (ref=IDENT subscripts1=array_subscripts? (ref1+=IDENT subscripts+=array_subscripts?)*)
	 *
	 * Features:
	 *    ref[1, 1]
	 *    subscripts1[0, 1]
	 *    ref1[0, *]
	 *         SAME_OR_MORE subscripts
	 *    subscripts[0, *]
	 *         SAME_OR_LESS ref1
	 */
	protected void sequence_left_hand_component_reference(EObject context, left_hand_component_reference semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Relation=relation Logical_factor+=logical_factor* Logical_term+=logical_term*)
	 *
	 * Features:
	 *    Logical_term[0, *]
	 *    Logical_factor[0, *]
	 *    Relation[1, 1]
	 */
	protected void sequence_logical_expression(EObject context, logical_factor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Relation=relation
	 *
	 * Features:
	 *    Relation[1, 1]
	 */
	protected void sequence_logical_factor(EObject context, logical_factor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Relation=relation Logical_factor+=logical_factor*)
	 *
	 * Features:
	 *    Logical_factor[0, *]
	 *    Relation[1, 1]
	 */
	protected void sequence_logical_term(EObject context, logical_factor semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ref=IDENT subscripts1=array_subscripts? (ref1+=IDENT subscripts+=array_subscripts?)* Expr=expression)
	 *
	 * Features:
	 *    Expr[1, 1]
	 *    ref[1, 1]
	 *    subscripts1[0, 1]
	 *    ref1[0, *]
	 *         SAME_OR_MORE subscripts
	 *    subscripts[0, *]
	 *         SAME_OR_LESS ref1
	 */
	protected void sequence_modification_alternatives(EObject context, left_hand_component_reference semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Modification_Rule=modification?
	 *
	 * Features:
	 *    Modification_Rule[0, 1]
	 */
	protected void sequence_modification_alternatives(EObject context, modification_alternatives semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Arg=argument_list? Expr=expression?)
	 *
	 * Features:
	 *    Expr[0, 1]
	 *    Arg[0, 1]
	 */
	protected void sequence_modification(EObject context, class_modification_action semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Expr=expression | Expr=expression)
	 *
	 * Features:
	 *    Expr[0, 2]
	 */
	protected void sequence_modification(EObject context, modification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name_ID=IDENT nam_ID+=IDENT* Function_call_args=function_call_args)
	 *
	 * Features:
	 *    Function_call_args[1, 1]
	 *    name_ID[1, 1]
	 *    nam_ID[0, *]
	 */
	protected void sequence_name_Function(EObject context, name semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name_ID=IDENT nam_ID+=IDENT*)
	 *
	 * Features:
	 *    name_ID[1, 1]
	 *    nam_ID[0, *]
	 */
	protected void sequence_name(EObject context, name semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (arg=IDENT expr=expression)
	 *
	 * Features:
	 *    arg[1, 1]
	 *    expr[1, 1]
	 */
	protected void sequence_named_argument(EObject context, named_argument semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (arg=IDENT expr=expression Named_arguments=named_arguments?)
	 *
	 * Features:
	 *    Named_arguments[0, 1]
	 *    arg[1, 1]
	 *    expr[1, 1]
	 */
	protected void sequence_named_arguments(EObject context, named_argument semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (epr=expression? Expr+=expression*)
	 *
	 * Features:
	 *    epr[0, 1]
	 *    Expr[0, *]
	 */
	protected void sequence_output_expression_list(EObject context, output_expression_list semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         num=UNSIGNED_NUMBER | 
	 *         int=INT | 
	 *         str=STRING | 
	 *         Bool=BOOL_VAL | 
	 *         Component_reference=component_reference | 
	 *         output_expr_list=output_expression_list | 
	 *         (Expre_list=expression_list Expression_list+=expression_list*) | 
	 *         f_arguments=function_arguments | 
	 *         End='end'
	 *     )
	 *
	 * Features:
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    str[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Bool[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    output_expr_list[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Expre_list[0, 1]
	 *         MANDATORY_IF_SET Expression_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Expression_list[0, *]
	 *         EXCLUDE_IF_UNSET Expre_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    f_arguments[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET End
	 *    End[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 */
	protected void sequence_primary(EObject context, primary semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (opr=add_op_plus | opr=add_op_minus | opr=add_op_dotplus | opr=add_op_dotminus)? 
	 *         Term=term 
	 *         ((Oper1+=add_op_plus | Oper1+=add_op_minus | Oper1+=add_op_dotplus | Oper1+=add_op_dotminus) Term1+=term)* 
	 *         (
	 *             (
	 *                 op=rel_op_Less_then | 
	 *                 op=rel_op_Less_equal | 
	 *                 op=rel_op_greater_then | 
	 *                 op=rel_op_greater_equal | 
	 *                 op=rel_op_assignment | 
	 *                 op=rel_op_Oper
	 *             ) 
	 *             Arithmetic_expression=arithmetic_expression
	 *         )?
	 *     )
	 *
	 * Features:
	 *    op[0, 6]
	 *         EXCLUDE_IF_UNSET Arithmetic_expression
	 *    Arithmetic_expression[0, 1]
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *         MANDATORY_IF_SET op
	 *    opr[0, 4]
	 *    Term[1, 1]
	 *    Oper1[0, *]
	 *         SAME_OR_LESS Term1
	 *    Term1[0, *]
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 *         SAME_OR_MORE Oper1
	 */
	protected void sequence_relation(EObject context, arithmetic_expression semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Log_Exp=logical_expression (S_Logical_expression=logical_expression L_Logical_expression=logical_expression?)?)
	 *
	 * Features:
	 *    Log_Exp[1, 1]
	 *    S_Logical_expression[0, 1]
	 *         MANDATORY_IF_SET L_Logical_expression
	 *    L_Logical_expression[0, 1]
	 *         EXCLUDE_IF_UNSET S_Logical_expression
	 */
	protected void sequence_simple_expression(EObject context, simple_expression semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (component_reference=component_reference (expr=expression | fun_call_args=function_call_args))
	 *
	 * Features:
	 *    component_reference[1, 1]
	 *    expr[0, 1]
	 *         EXCLUDE_IF_SET fun_call_args
	 *    fun_call_args[0, 1]
	 *         EXCLUDE_IF_SET expr
	 */
	protected void sequence_state_Comp_Ref(EObject context, state_Comp_Ref semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (output_expression_list=output_expression_list Component_ref=component_reference fun_call_args=function_call_args)
	 *
	 * Features:
	 *    output_expression_list[1, 1]
	 *    Component_ref[1, 1]
	 *    fun_call_args[1, 1]
	 */
	protected void sequence_state_OutputExprList(EObject context, state_OutputExprList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.STATE_OUTPUT_EXPR_LIST__OUTPUT_EXPRESSION_LIST));
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.STATE_OUTPUT_EXPR_LIST__COMPONENT_REF));
			if(transientValues.isValueTransient(semanticObject, ModificationPackage.Literals.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModificationPackage.Literals.STATE_OUTPUT_EXPR_LIST__FUN_CALL_ARGS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getState_OutputExprListAccess().getOutput_expression_listOutput_expression_listParserRuleCall_1_0(), semanticObject.getOutput_expression_list());
		feeder.accept(grammarAccess.getState_OutputExprListAccess().getComponent_refComponent_referenceParserRuleCall_4_0(), semanticObject.getComponent_ref());
		feeder.accept(grammarAccess.getState_OutputExprListAccess().getFun_call_argsFunction_call_argsParserRuleCall_5_0(), semanticObject.getFun_call_args());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             If_statement=if_statement | 
	 *             State_OutputExprList=state_OutputExprList | 
	 *             state_Comp_Ref=state_Comp_Ref | 
	 *             For_statement=for_statement | 
	 *             When_statement=when_statement | 
	 *             While_statement=while_statement
	 *         )?
	 *     )
	 *
	 * Features:
	 *    If_statement[0, 1]
	 *         EXCLUDE_IF_SET State_OutputExprList
	 *         EXCLUDE_IF_SET state_Comp_Ref
	 *         EXCLUDE_IF_SET For_statement
	 *         EXCLUDE_IF_SET When_statement
	 *         EXCLUDE_IF_SET While_statement
	 *    State_OutputExprList[0, 1]
	 *         EXCLUDE_IF_SET If_statement
	 *         EXCLUDE_IF_SET state_Comp_Ref
	 *         EXCLUDE_IF_SET For_statement
	 *         EXCLUDE_IF_SET When_statement
	 *         EXCLUDE_IF_SET While_statement
	 *    state_Comp_Ref[0, 1]
	 *         EXCLUDE_IF_SET If_statement
	 *         EXCLUDE_IF_SET State_OutputExprList
	 *         EXCLUDE_IF_SET For_statement
	 *         EXCLUDE_IF_SET When_statement
	 *         EXCLUDE_IF_SET While_statement
	 *    For_statement[0, 1]
	 *         EXCLUDE_IF_SET If_statement
	 *         EXCLUDE_IF_SET State_OutputExprList
	 *         EXCLUDE_IF_SET state_Comp_Ref
	 *         EXCLUDE_IF_SET When_statement
	 *         EXCLUDE_IF_SET While_statement
	 *    When_statement[0, 1]
	 *         EXCLUDE_IF_SET If_statement
	 *         EXCLUDE_IF_SET State_OutputExprList
	 *         EXCLUDE_IF_SET state_Comp_Ref
	 *         EXCLUDE_IF_SET For_statement
	 *         EXCLUDE_IF_SET While_statement
	 *    While_statement[0, 1]
	 *         EXCLUDE_IF_SET If_statement
	 *         EXCLUDE_IF_SET State_OutputExprList
	 *         EXCLUDE_IF_SET state_Comp_Ref
	 *         EXCLUDE_IF_SET For_statement
	 *         EXCLUDE_IF_SET When_statement
	 */
	protected void sequence_statement(EObject context, statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {subscript}
	 *
	 * Features:
	 */
	protected void sequence_subscript(EObject context, subscript semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (functionArgs=function_call_args Primary=primary? ((op+=mul_op_mul | op+=mul_op_div | op+=mul_op_dotmul | op+=mul_op_dotdiv) Factor+=factor)*)
	 *
	 * Features:
	 *    op[0, *]
	 *         SAME_OR_LESS Factor
	 *    Factor[0, *]
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *    Primary[0, 1]
	 *    functionArgs[1, 1]
	 */
	protected void sequence_term(EObject context, ExprDer semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (f_arg=function_arguments? Primary=primary? ((op+=mul_op_mul | op+=mul_op_div | op+=mul_op_dotmul | op+=mul_op_dotdiv) Factor+=factor)*)
	 *
	 * Features:
	 *    op[0, *]
	 *         SAME_OR_LESS Factor
	 *    Factor[0, *]
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *    Primary[0, 1]
	 *    f_arg[0, 1]
	 */
	protected void sequence_term(EObject context, function_call_args semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name_ID=IDENT 
	 *         nam_ID+=IDENT* 
	 *         Function_call_args=function_call_args 
	 *         Primary=primary? 
	 *         ((op+=mul_op_mul | op+=mul_op_div | op+=mul_op_dotmul | op+=mul_op_dotdiv) Factor+=factor)*
	 *     )
	 *
	 * Features:
	 *    op[0, *]
	 *         SAME_OR_LESS Factor
	 *    Factor[0, *]
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *    Primary[0, 1]
	 *    Function_call_args[1, 1]
	 *    name_ID[1, 1]
	 *    nam_ID[0, *]
	 */
	protected void sequence_term(EObject context, name semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             num=UNSIGNED_NUMBER | 
	 *             int=INT | 
	 *             str=STRING | 
	 *             Bool=BOOL_VAL | 
	 *             Component_reference=component_reference | 
	 *             output_expr_list=output_expression_list | 
	 *             (Expre_list=expression_list Expression_list+=expression_list*) | 
	 *             f_arguments=function_arguments | 
	 *             End='end'
	 *         ) 
	 *         Primary=primary? 
	 *         ((op+=mul_op_mul | op+=mul_op_div | op+=mul_op_dotmul | op+=mul_op_dotdiv) Factor+=factor)*
	 *     )
	 *
	 * Features:
	 *    op[0, *]
	 *         SAME_OR_LESS Factor
	 *    Factor[0, *]
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *         SAME_OR_MORE op
	 *    Primary[0, 1]
	 *    num[0, 1]
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    int[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    str[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Bool[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Component_reference[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    output_expr_list[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Expre_list[0, 1]
	 *         MANDATORY_IF_SET Expression_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    Expression_list[0, *]
	 *         EXCLUDE_IF_UNSET Expre_list
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET f_arguments
	 *         EXCLUDE_IF_SET End
	 *    f_arguments[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET End
	 *    End[0, 1]
	 *         EXCLUDE_IF_SET num
	 *         EXCLUDE_IF_SET int
	 *         EXCLUDE_IF_SET str
	 *         EXCLUDE_IF_SET Bool
	 *         EXCLUDE_IF_SET Component_reference
	 *         EXCLUDE_IF_SET output_expr_list
	 *         EXCLUDE_IF_SET Expre_list
	 *         EXCLUDE_IF_SET Expression_list
	 *         EXCLUDE_IF_SET f_arguments
	 */
	protected void sequence_term(EObject context, primary semanticObject) {
		superSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Name=name
	 *
	 * Features:
	 *    Name[1, 1]
	 */
	protected void sequence_type_specifier(EObject context, type_specifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (whenEpr=expression Eqn_Then+=equation* (elseWhenEpr+=expression Eqn_Then+=equation*)*)
	 *
	 * Features:
	 *    whenEpr[1, 1]
	 *    Eqn_Then[0, *]
	 *    elseWhenEpr[0, *]
	 *         MANDATORY_IF_SET Eqn_Then
	 */
	protected void sequence_when_equation(EObject context, when_equation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (When_expr=expression When_stat_true+=statement* (Else_When_expr+=expression Then_statement+=statement*)*)
	 *
	 * Features:
	 *    When_expr[1, 1]
	 *    When_stat_true[0, *]
	 *    Else_When_expr[0, *]
	 *         MANDATORY_IF_SET Then_statement
	 *    Then_statement[0, *]
	 *         EXCLUDE_IF_UNSET Else_When_expr
	 */
	protected void sequence_when_statement(EObject context, when_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (while_expr=expression While_stat+=while_statement*)
	 *
	 * Features:
	 *    while_expr[1, 1]
	 *    While_stat[0, *]
	 */
	protected void sequence_while_statement(EObject context, while_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
