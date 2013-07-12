/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.editor.xtext.modification.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.openmodelica.modelicaml.editor.xtext.modification.services.ModificationGrammarAccess;

public class ModificationParser extends AbstractContentAssistParser {
	
	@Inject
	private ModificationGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.openmodelica.modelicaml.editor.xtext.modification.ui.contentassist.antlr.internal.InternalModificationParser createParser() {
		org.openmodelica.modelicaml.editor.xtext.modification.ui.contentassist.antlr.internal.InternalModificationParser result = new org.openmodelica.modelicaml.editor.xtext.modification.ui.contentassist.antlr.internal.InternalModificationParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getModification_alternativesAccess().getAlternatives(), "rule__Modification_alternatives__Alternatives");
					put(grammarAccess.getModificationAccess().getAlternatives(), "rule__Modification__Alternatives");
					put(grammarAccess.getArgumentAccess().getAlternatives(), "rule__Argument__Alternatives");
					put(grammarAccess.getElement_modification_or_replaceableAccess().getAlternatives_3(), "rule__Element_modification_or_replaceable__Alternatives_3");
					put(grammarAccess.getElement_replaceableAccess().getAlternatives_2(), "rule__Element_replaceable__Alternatives_2");
					put(grammarAccess.getElement_redeclarationAccess().getAlternatives_4(), "rule__Element_redeclaration__Alternatives_4");
					put(grammarAccess.getElement_redeclarationAccess().getAlternatives_4_0(), "rule__Element_redeclaration__Alternatives_4_0");
					put(grammarAccess.getClass_definitionAccess().getAlternatives_2(), "rule__Class_definition__Alternatives_2");
					put(grammarAccess.getClass_specifierAccess().getAlternatives_1(), "rule__Class_specifier__Alternatives_1");
					put(grammarAccess.getClass_specifierAccess().getAlternatives_1_2_4(), "rule__Class_specifier__Alternatives_1_2_4");
					put(grammarAccess.getType_prefixAccess().getAlternatives_0(), "rule__Type_prefix__Alternatives_0");
					put(grammarAccess.getType_prefixAccess().getAlternatives_1(), "rule__Type_prefix__Alternatives_1");
					put(grammarAccess.getType_prefixAccess().getAlternatives_2(), "rule__Type_prefix__Alternatives_2");
					put(grammarAccess.getCompositionAccess().getAlternatives_1(), "rule__Composition__Alternatives_1");
					put(grammarAccess.getElementAccess().getAlternatives(), "rule__Element__Alternatives");
					put(grammarAccess.getElementAccess().getAlternatives_2_4(), "rule__Element__Alternatives_2_4");
					put(grammarAccess.getElementAccess().getAlternatives_2_4_0(), "rule__Element__Alternatives_2_4_0");
					put(grammarAccess.getElementAccess().getAlternatives_2_4_1_1(), "rule__Element__Alternatives_2_4_1_1");
					put(grammarAccess.getEquationAccess().getAlternatives_0(), "rule__Equation__Alternatives_0");
					put(grammarAccess.getStatementAccess().getAlternatives_1(), "rule__Statement__Alternatives_1");
					put(grammarAccess.getState_Comp_RefAccess().getAlternatives_1(), "rule__State_Comp_Ref__Alternatives_1");
					put(grammarAccess.getImport_clauseAccess().getAlternatives_1(), "rule__Import_clause__Alternatives_1");
					put(grammarAccess.getExpressionAccess().getAlternatives(), "rule__Expression__Alternatives");
					put(grammarAccess.getRelationAccess().getOpAlternatives_1_0_0(), "rule__Relation__OpAlternatives_1_0_0");
					put(grammarAccess.getArithmetic_expressionAccess().getOprAlternatives_0_0(), "rule__Arithmetic_expression__OprAlternatives_0_0");
					put(grammarAccess.getArithmetic_expressionAccess().getOper1Alternatives_2_0_0(), "rule__Arithmetic_expression__Oper1Alternatives_2_0_0");
					put(grammarAccess.getTermAccess().getOpAlternatives_1_0_0(), "rule__Term__OpAlternatives_1_0_0");
					put(grammarAccess.getFactorAccess().getAlternatives_1_0(), "rule__Factor__Alternatives_1_0");
					put(grammarAccess.getPrimaryAccess().getAlternatives(), "rule__Primary__Alternatives");
					put(grammarAccess.getSubscriptAccess().getAlternatives(), "rule__Subscript__Alternatives");
					put(grammarAccess.getFunction_argumentsAccess().getAlternatives(), "rule__Function_arguments__Alternatives");
					put(grammarAccess.getFunction_argumentsAccess().getAlternatives_0_2(), "rule__Function_arguments__Alternatives_0_2");
					put(grammarAccess.getModification_alternativesAccess().getGroup_0(), "rule__Modification_alternatives__Group_0__0");
					put(grammarAccess.getLeft_hand_component_referenceAccess().getGroup(), "rule__Left_hand_component_reference__Group__0");
					put(grammarAccess.getLeft_hand_component_referenceAccess().getGroup_3(), "rule__Left_hand_component_reference__Group_3__0");
					put(grammarAccess.getModificationAccess().getGroup_0(), "rule__Modification__Group_0__0");
					put(grammarAccess.getModificationAccess().getGroup_0_1(), "rule__Modification__Group_0_1__0");
					put(grammarAccess.getModificationAccess().getGroup_1(), "rule__Modification__Group_1__0");
					put(grammarAccess.getModificationAccess().getGroup_2(), "rule__Modification__Group_2__0");
					put(grammarAccess.getClass_modificationAccess().getGroup(), "rule__Class_modification__Group__0");
					put(grammarAccess.getElement_modification_or_replaceableAccess().getGroup(), "rule__Element_modification_or_replaceable__Group__0");
					put(grammarAccess.getElement_modificationAccess().getGroup(), "rule__Element_modification__Group__0");
					put(grammarAccess.getElement_replaceableAccess().getGroup(), "rule__Element_replaceable__Group__0");
					put(grammarAccess.getConstraining_clauseAccess().getGroup(), "rule__Constraining_clause__Group__0");
					put(grammarAccess.getElement_redeclarationAccess().getGroup(), "rule__Element_redeclaration__Group__0");
					put(grammarAccess.getComponent_clauseAccess().getGroup(), "rule__Component_clause__Group__0");
					put(grammarAccess.getComponent_listAccess().getGroup(), "rule__Component_list__Group__0");
					put(grammarAccess.getComponent_listAccess().getGroup_1(), "rule__Component_list__Group_1__0");
					put(grammarAccess.getComponent_declarationAccess().getGroup(), "rule__Component_declaration__Group__0");
					put(grammarAccess.getConditional_attributeAccess().getGroup(), "rule__Conditional_attribute__Group__0");
					put(grammarAccess.getComponent_clause1Access().getGroup(), "rule__Component_clause1__Group__0");
					put(grammarAccess.getComponent_declaration1Access().getGroup(), "rule__Component_declaration1__Group__0");
					put(grammarAccess.getDeclarationAccess().getGroup(), "rule__Declaration__Group__0");
					put(grammarAccess.getClass_definitionAccess().getGroup(), "rule__Class_definition__Group__0");
					put(grammarAccess.getClass_definitionAccess().getGroup_2_4(), "rule__Class_definition__Group_2_4__0");
					put(grammarAccess.getClass_definitionAccess().getGroup_2_9(), "rule__Class_definition__Group_2_9__0");
					put(grammarAccess.getClass_definitionAccess().getGroup_2_10(), "rule__Class_definition__Group_2_10__0");
					put(grammarAccess.getClass_specifierAccess().getGroup(), "rule__Class_specifier__Group__0");
					put(grammarAccess.getClass_specifierAccess().getGroup_1_0(), "rule__Class_specifier__Group_1_0__0");
					put(grammarAccess.getClass_specifierAccess().getGroup_1_1(), "rule__Class_specifier__Group_1_1__0");
					put(grammarAccess.getClass_specifierAccess().getGroup_1_2(), "rule__Class_specifier__Group_1_2__0");
					put(grammarAccess.getClass_specifierAccess().getGroup_1_3(), "rule__Class_specifier__Group_1_3__0");
					put(grammarAccess.getClass_specifierAccess().getGroup_1_3_7(), "rule__Class_specifier__Group_1_3_7__0");
					put(grammarAccess.getClass_specifierAccess().getGroup_1_4(), "rule__Class_specifier__Group_1_4__0");
					put(grammarAccess.getType_prefixAccess().getGroup(), "rule__Type_prefix__Group__0");
					put(grammarAccess.getEnum_listAccess().getGroup(), "rule__Enum_list__Group__0");
					put(grammarAccess.getEnum_listAccess().getGroup_2(), "rule__Enum_list__Group_2__0");
					put(grammarAccess.getEnumeration_literalAccess().getGroup(), "rule__Enumeration_literal__Group__0");
					put(grammarAccess.getCompositionAccess().getGroup(), "rule__Composition__Group__0");
					put(grammarAccess.getCompositionAccess().getGroup_1_0(), "rule__Composition__Group_1_0__0");
					put(grammarAccess.getCompositionAccess().getGroup_1_1(), "rule__Composition__Group_1_1__0");
					put(grammarAccess.getCompositionAccess().getGroup_2(), "rule__Composition__Group_2__0");
					put(grammarAccess.getCompositionAccess().getGroup_3(), "rule__Composition__Group_3__0");
					put(grammarAccess.getElement_listAccess().getGroup(), "rule__Element_list__Group__0");
					put(grammarAccess.getElement_listAccess().getGroup_1(), "rule__Element_list__Group_1__0");
					put(grammarAccess.getElementAccess().getGroup_2(), "rule__Element__Group_2__0");
					put(grammarAccess.getElementAccess().getGroup_2_4_1(), "rule__Element__Group_2_4_1__0");
					put(grammarAccess.getElementAccess().getGroup_2_4_1_2(), "rule__Element__Group_2_4_1_2__0");
					put(grammarAccess.getEquation_sectionAccess().getGroup(), "rule__Equation_section__Group__0");
					put(grammarAccess.getEquation_sectionAccess().getGroup_3(), "rule__Equation_section__Group_3__0");
					put(grammarAccess.getEquationAccess().getGroup(), "rule__Equation__Group__0");
					put(grammarAccess.getEquationAccess().getGroup_0_0(), "rule__Equation__Group_0_0__0");
					put(grammarAccess.getWhen_equationAccess().getGroup(), "rule__When_equation__Group__0");
					put(grammarAccess.getWhen_equationAccess().getGroup_3(), "rule__When_equation__Group_3__0");
					put(grammarAccess.getWhen_equationAccess().getGroup_4(), "rule__When_equation__Group_4__0");
					put(grammarAccess.getWhen_equationAccess().getGroup_4_3(), "rule__When_equation__Group_4_3__0");
					put(grammarAccess.getIf_equationAccess().getGroup(), "rule__If_equation__Group__0");
					put(grammarAccess.getIf_equationAccess().getGroup_3(), "rule__If_equation__Group_3__0");
					put(grammarAccess.getIf_equationAccess().getGroup_4(), "rule__If_equation__Group_4__0");
					put(grammarAccess.getIf_equationAccess().getGroup_4_3(), "rule__If_equation__Group_4_3__0");
					put(grammarAccess.getIf_equationAccess().getGroup_5(), "rule__If_equation__Group_5__0");
					put(grammarAccess.getIf_equationAccess().getGroup_5_1(), "rule__If_equation__Group_5_1__0");
					put(grammarAccess.getFor_equationAccess().getGroup(), "rule__For_equation__Group__0");
					put(grammarAccess.getFor_equationAccess().getGroup_3(), "rule__For_equation__Group_3__0");
					put(grammarAccess.getConnect_clauseAccess().getGroup(), "rule__Connect_clause__Group__0");
					put(grammarAccess.getAlgorithm_sectionAccess().getGroup(), "rule__Algorithm_section__Group__0");
					put(grammarAccess.getAlgorithm_sectionAccess().getGroup_3(), "rule__Algorithm_section__Group_3__0");
					put(grammarAccess.getStatementAccess().getGroup(), "rule__Statement__Group__0");
					put(grammarAccess.getState_OutputExprListAccess().getGroup(), "rule__State_OutputExprList__Group__0");
					put(grammarAccess.getState_Comp_RefAccess().getGroup(), "rule__State_Comp_Ref__Group__0");
					put(grammarAccess.getState_Comp_RefAccess().getGroup_1_0(), "rule__State_Comp_Ref__Group_1_0__0");
					put(grammarAccess.getWhen_statementAccess().getGroup(), "rule__When_statement__Group__0");
					put(grammarAccess.getWhen_statementAccess().getGroup_3(), "rule__When_statement__Group_3__0");
					put(grammarAccess.getWhen_statementAccess().getGroup_4(), "rule__When_statement__Group_4__0");
					put(grammarAccess.getWhen_statementAccess().getGroup_4_3(), "rule__When_statement__Group_4_3__0");
					put(grammarAccess.getWhile_statementAccess().getGroup(), "rule__While_statement__Group__0");
					put(grammarAccess.getWhile_statementAccess().getGroup_3(), "rule__While_statement__Group_3__0");
					put(grammarAccess.getFor_statementAccess().getGroup(), "rule__For_statement__Group__0");
					put(grammarAccess.getFor_statementAccess().getGroup_3(), "rule__For_statement__Group_3__0");
					put(grammarAccess.getIf_statementAccess().getGroup(), "rule__If_statement__Group__0");
					put(grammarAccess.getIf_statementAccess().getGroup_3(), "rule__If_statement__Group_3__0");
					put(grammarAccess.getIf_statementAccess().getGroup_4(), "rule__If_statement__Group_4__0");
					put(grammarAccess.getIf_statementAccess().getGroup_4_3(), "rule__If_statement__Group_4_3__0");
					put(grammarAccess.getIf_statementAccess().getGroup_5(), "rule__If_statement__Group_5__0");
					put(grammarAccess.getIf_statementAccess().getGroup_5_1(), "rule__If_statement__Group_5_1__0");
					put(grammarAccess.getExtends_clauseAccess().getGroup(), "rule__Extends_clause__Group__0");
					put(grammarAccess.getAnnotationAccess().getGroup(), "rule__Annotation__Group__0");
					put(grammarAccess.getImport_clauseAccess().getGroup(), "rule__Import_clause__Group__0");
					put(grammarAccess.getImport_clauseAccess().getGroup_1_0(), "rule__Import_clause__Group_1_0__0");
					put(grammarAccess.getImport_clauseAccess().getGroup_1_1(), "rule__Import_clause__Group_1_1__0");
					put(grammarAccess.getImport_clauseAccess().getGroup_1_1_1(), "rule__Import_clause__Group_1_1_1__0");
					put(grammarAccess.getExternal_function_callAccess().getGroup(), "rule__External_function_call__Group__0");
					put(grammarAccess.getExternal_function_callAccess().getGroup_1(), "rule__External_function_call__Group_1__0");
					put(grammarAccess.getSimple_expressionAccess().getGroup(), "rule__Simple_expression__Group__0");
					put(grammarAccess.getSimple_expressionAccess().getGroup_1(), "rule__Simple_expression__Group_1__0");
					put(grammarAccess.getSimple_expressionAccess().getGroup_1_2(), "rule__Simple_expression__Group_1_2__0");
					put(grammarAccess.getConditional_exprAccess().getGroup(), "rule__Conditional_expr__Group__0");
					put(grammarAccess.getConditional_exprAccess().getGroup_4(), "rule__Conditional_expr__Group_4__0");
					put(grammarAccess.getConditional_exprAccess().getGroup_5(), "rule__Conditional_expr__Group_5__0");
					put(grammarAccess.getLogical_expressionAccess().getGroup(), "rule__Logical_expression__Group__0");
					put(grammarAccess.getLogical_expressionAccess().getGroup_1(), "rule__Logical_expression__Group_1__0");
					put(grammarAccess.getLogical_termAccess().getGroup(), "rule__Logical_term__Group__0");
					put(grammarAccess.getLogical_termAccess().getGroup_1(), "rule__Logical_term__Group_1__0");
					put(grammarAccess.getLogical_factorAccess().getGroup(), "rule__Logical_factor__Group__0");
					put(grammarAccess.getRelationAccess().getGroup(), "rule__Relation__Group__0");
					put(grammarAccess.getRelationAccess().getGroup_1(), "rule__Relation__Group_1__0");
					put(grammarAccess.getArithmetic_expressionAccess().getGroup(), "rule__Arithmetic_expression__Group__0");
					put(grammarAccess.getArithmetic_expressionAccess().getGroup_2(), "rule__Arithmetic_expression__Group_2__0");
					put(grammarAccess.getTermAccess().getGroup(), "rule__Term__Group__0");
					put(grammarAccess.getTermAccess().getGroup_1(), "rule__Term__Group_1__0");
					put(grammarAccess.getFactorAccess().getGroup(), "rule__Factor__Group__0");
					put(grammarAccess.getFactorAccess().getGroup_1(), "rule__Factor__Group_1__0");
					put(grammarAccess.getPrimaryAccess().getGroup_8(), "rule__Primary__Group_8__0");
					put(grammarAccess.getPrimaryAccess().getGroup_9(), "rule__Primary__Group_9__0");
					put(grammarAccess.getPrimaryAccess().getGroup_9_2(), "rule__Primary__Group_9_2__0");
					put(grammarAccess.getPrimaryAccess().getGroup_10(), "rule__Primary__Group_10__0");
					put(grammarAccess.getName_FunctionAccess().getGroup(), "rule__Name_Function__Group__0");
					put(grammarAccess.getInitial_refAccess().getGroup(), "rule__Initial_ref__Group__0");
					put(grammarAccess.getExprDerAccess().getGroup(), "rule__ExprDer__Group__0");
					put(grammarAccess.getFunction_call_argsAccess().getGroup(), "rule__Function_call_args__Group__0");
					put(grammarAccess.getExpression_listAccess().getGroup(), "rule__Expression_list__Group__0");
					put(grammarAccess.getExpression_listAccess().getGroup_1(), "rule__Expression_list__Group_1__0");
					put(grammarAccess.getNameAccess().getGroup(), "rule__Name__Group__0");
					put(grammarAccess.getNameAccess().getGroup_2(), "rule__Name__Group_2__0");
					put(grammarAccess.getComponent_referenceAccess().getGroup(), "rule__Component_reference__Group__0");
					put(grammarAccess.getComponent_referenceAccess().getGroup_3(), "rule__Component_reference__Group_3__0");
					put(grammarAccess.getOutput_expression_listAccess().getGroup(), "rule__Output_expression_list__Group__0");
					put(grammarAccess.getOutput_expression_listAccess().getGroup_2(), "rule__Output_expression_list__Group_2__0");
					put(grammarAccess.getArray_subscriptsAccess().getGroup(), "rule__Array_subscripts__Group__0");
					put(grammarAccess.getArray_subscriptsAccess().getGroup_2(), "rule__Array_subscripts__Group_2__0");
					put(grammarAccess.getSubscriptAccess().getGroup_0(), "rule__Subscript__Group_0__0");
					put(grammarAccess.getFunction_argumentsAccess().getGroup_0(), "rule__Function_arguments__Group_0__0");
					put(grammarAccess.getFun_Arguments_expAccess().getGroup(), "rule__Fun_Arguments_exp__Group__0");
					put(grammarAccess.getFun_Arguments_forAccess().getGroup(), "rule__Fun_Arguments_for__Group__0");
					put(grammarAccess.getNamed_argumentsAccess().getGroup(), "rule__Named_arguments__Group__0");
					put(grammarAccess.getNamed_argumentsAccess().getGroup_1(), "rule__Named_arguments__Group_1__0");
					put(grammarAccess.getNamed_argumentAccess().getGroup(), "rule__Named_argument__Group__0");
					put(grammarAccess.getFor_indicesAccess().getGroup(), "rule__For_indices__Group__0");
					put(grammarAccess.getFor_indicesAccess().getGroup_1(), "rule__For_indices__Group_1__0");
					put(grammarAccess.getFor_indexAccess().getGroup(), "rule__For_index__Group__0");
					put(grammarAccess.getFor_indexAccess().getGroup_1(), "rule__For_index__Group_1__0");
					put(grammarAccess.getString_commentAccess().getGroup(), "rule__String_comment__Group__0");
					put(grammarAccess.getString_commentAccess().getGroup_1(), "rule__String_comment__Group_1__0");
					put(grammarAccess.getModification_alternativesAccess().getExprAssignment_0_2(), "rule__Modification_alternatives__ExprAssignment_0_2");
					put(grammarAccess.getModification_alternativesAccess().getModification_RuleAssignment_1(), "rule__Modification_alternatives__Modification_RuleAssignment_1");
					put(grammarAccess.getLeft_hand_component_referenceAccess().getRefAssignment_1(), "rule__Left_hand_component_reference__RefAssignment_1");
					put(grammarAccess.getLeft_hand_component_referenceAccess().getSubscripts1Assignment_2(), "rule__Left_hand_component_reference__Subscripts1Assignment_2");
					put(grammarAccess.getLeft_hand_component_referenceAccess().getRef1Assignment_3_1(), "rule__Left_hand_component_reference__Ref1Assignment_3_1");
					put(grammarAccess.getLeft_hand_component_referenceAccess().getSubscriptsAssignment_3_2(), "rule__Left_hand_component_reference__SubscriptsAssignment_3_2");
					put(grammarAccess.getModificationAccess().getExprAssignment_0_1_1(), "rule__Modification__ExprAssignment_0_1_1");
					put(grammarAccess.getModificationAccess().getExprAssignment_1_1(), "rule__Modification__ExprAssignment_1_1");
					put(grammarAccess.getModificationAccess().getExprAssignment_2_1(), "rule__Modification__ExprAssignment_2_1");
					put(grammarAccess.getClass_modificationAccess().getArgAssignment_2(), "rule__Class_modification__ArgAssignment_2");
					put(grammarAccess.getElement_modification_or_replaceableAccess().getElement_modAssignment_3_0(), "rule__Element_modification_or_replaceable__Element_modAssignment_3_0");
					put(grammarAccess.getElement_modification_or_replaceableAccess().getElement_repAssignment_3_1(), "rule__Element_modification_or_replaceable__Element_repAssignment_3_1");
					put(grammarAccess.getElement_modificationAccess().getNameAssignment_0(), "rule__Element_modification__NameAssignment_0");
					put(grammarAccess.getElement_modificationAccess().getModificationAssignment_1(), "rule__Element_modification__ModificationAssignment_1");
					put(grammarAccess.getElement_replaceableAccess().getClass_DefAssignment_2_0(), "rule__Element_replaceable__Class_DefAssignment_2_0");
					put(grammarAccess.getElement_replaceableAccess().getComp_clause2Assignment_2_1(), "rule__Element_replaceable__Comp_clause2Assignment_2_1");
					put(grammarAccess.getElement_replaceableAccess().getConstrain_ClauseAssignment_3(), "rule__Element_replaceable__Constrain_ClauseAssignment_3");
					put(grammarAccess.getConstraining_clauseAccess().getNameAssignment_1(), "rule__Constraining_clause__NameAssignment_1");
					put(grammarAccess.getConstraining_clauseAccess().getClass_ModAssignment_2(), "rule__Constraining_clause__Class_ModAssignment_2");
					put(grammarAccess.getElement_redeclarationAccess().getClass_DefAssignment_4_0_0(), "rule__Element_redeclaration__Class_DefAssignment_4_0_0");
					put(grammarAccess.getElement_redeclarationAccess().getComp_clause1Assignment_4_0_1(), "rule__Element_redeclaration__Comp_clause1Assignment_4_0_1");
					put(grammarAccess.getElement_redeclarationAccess().getElement_RAssignment_4_1(), "rule__Element_redeclaration__Element_RAssignment_4_1");
					put(grammarAccess.getComponent_clauseAccess().getArray_SubsAssignment_2(), "rule__Component_clause__Array_SubsAssignment_2");
					put(grammarAccess.getComponent_clauseAccess().getCom_ListAssignment_3(), "rule__Component_clause__Com_ListAssignment_3");
					put(grammarAccess.getComponent_listAccess().getComponent_DecAssignment_1_1(), "rule__Component_list__Component_DecAssignment_1_1");
					put(grammarAccess.getComponent_declarationAccess().getDecAssignment_0(), "rule__Component_declaration__DecAssignment_0");
					put(grammarAccess.getComponent_declarationAccess().getConditional_AttAssignment_1(), "rule__Component_declaration__Conditional_AttAssignment_1");
					put(grammarAccess.getConditional_attributeAccess().getExprAssignment_1(), "rule__Conditional_attribute__ExprAssignment_1");
					put(grammarAccess.getComponent_clause1Access().getCom_DecAssignment_2(), "rule__Component_clause1__Com_DecAssignment_2");
					put(grammarAccess.getType_specifierAccess().getNameAssignment(), "rule__Type_specifier__NameAssignment");
					put(grammarAccess.getDeclarationAccess().getRedeclaredComponentNameAssignment_1(), "rule__Declaration__RedeclaredComponentNameAssignment_1");
					put(grammarAccess.getDeclarationAccess().getArraySubsAssignment_2(), "rule__Declaration__ArraySubsAssignment_2");
					put(grammarAccess.getDeclarationAccess().getModAssignment_3(), "rule__Declaration__ModAssignment_3");
					put(grammarAccess.getClass_specifierAccess().getCompAssignment_1_0_2(), "rule__Class_specifier__CompAssignment_1_0_2");
					put(grammarAccess.getClass_specifierAccess().getNameAssignment_1_1_3(), "rule__Class_specifier__NameAssignment_1_1_3");
					put(grammarAccess.getClass_specifierAccess().getArray_SubsAssignment_1_1_4(), "rule__Class_specifier__Array_SubsAssignment_1_1_4");
					put(grammarAccess.getClass_specifierAccess().getClass_modAssignment_1_1_5(), "rule__Class_specifier__Class_modAssignment_1_1_5");
					put(grammarAccess.getClass_specifierAccess().getVarAssignment_1_2_0(), "rule__Class_specifier__VarAssignment_1_2_0");
					put(grammarAccess.getClass_specifierAccess().getEnum_listAssignment_1_2_4_0(), "rule__Class_specifier__Enum_listAssignment_1_2_4_0");
					put(grammarAccess.getClass_specifierAccess().getNameAssignment_1_3_4(), "rule__Class_specifier__NameAssignment_1_3_4");
					put(grammarAccess.getClass_specifierAccess().getClass_modAssignment_1_4_2(), "rule__Class_specifier__Class_modAssignment_1_4_2");
					put(grammarAccess.getClass_specifierAccess().getCompAssignment_1_4_4(), "rule__Class_specifier__CompAssignment_1_4_4");
					put(grammarAccess.getEnum_listAccess().getEnnum_LitAssignment_1(), "rule__Enum_list__Ennum_LitAssignment_1");
					put(grammarAccess.getEnum_listAccess().getE_literalAssignment_2_1(), "rule__Enum_list__E_literalAssignment_2_1");
					put(grammarAccess.getCompositionAccess().getE_List_InitialAssignment_0(), "rule__Composition__E_List_InitialAssignment_0");
					put(grammarAccess.getCompositionAccess().getE_List_PublicAssignment_1_0_1(), "rule__Composition__E_List_PublicAssignment_1_0_1");
					put(grammarAccess.getCompositionAccess().getE_List_ProtectedAssignment_1_1_1(), "rule__Composition__E_List_ProtectedAssignment_1_1_1");
					put(grammarAccess.getCompositionAccess().getEqn_sectionAssignment_1_2(), "rule__Composition__Eqn_sectionAssignment_1_2");
					put(grammarAccess.getCompositionAccess().getAlg_sectionAssignment_1_3(), "rule__Composition__Alg_sectionAssignment_1_3");
					put(grammarAccess.getCompositionAccess().getLang_SpecAssignment_2_1(), "rule__Composition__Lang_SpecAssignment_2_1");
					put(grammarAccess.getCompositionAccess().getExternal_F_CAssignment_2_2(), "rule__Composition__External_F_CAssignment_2_2");
					put(grammarAccess.getCompositionAccess().getAnnotation1Assignment_2_3(), "rule__Composition__Annotation1Assignment_2_3");
					put(grammarAccess.getCompositionAccess().getAnnotation2Assignment_3_0(), "rule__Composition__Annotation2Assignment_3_0");
					put(grammarAccess.getElement_listAccess().getElementAssignment_1_0(), "rule__Element_list__ElementAssignment_1_0");
					put(grammarAccess.getElementAccess().getClass_DefAssignment_2_4_0_0(), "rule__Element__Class_DefAssignment_2_4_0_0");
					put(grammarAccess.getElementAccess().getComp_ClauseAssignment_2_4_0_1(), "rule__Element__Comp_ClauseAssignment_2_4_0_1");
					put(grammarAccess.getElementAccess().getClass_DefAssignment_2_4_1_1_0(), "rule__Element__Class_DefAssignment_2_4_1_1_0");
					put(grammarAccess.getElementAccess().getComp_ClauseAssignment_2_4_1_1_1(), "rule__Element__Comp_ClauseAssignment_2_4_1_1_1");
					put(grammarAccess.getElementAccess().getConstrain_ClauseAssignment_2_4_1_2_0(), "rule__Element__Constrain_ClauseAssignment_2_4_1_2_0");
					put(grammarAccess.getEquation_sectionAccess().getEqnAssignment_3_0(), "rule__Equation_section__EqnAssignment_3_0");
					put(grammarAccess.getEquationAccess().getSimAssignment_0_0_0(), "rule__Equation__SimAssignment_0_0_0");
					put(grammarAccess.getEquationAccess().getExprAssignment_0_0_2(), "rule__Equation__ExprAssignment_0_0_2");
					put(grammarAccess.getEquationAccess().getIF_EQNAssignment_0_1(), "rule__Equation__IF_EQNAssignment_0_1");
					put(grammarAccess.getEquationAccess().getFOR_EQNAssignment_0_2(), "rule__Equation__FOR_EQNAssignment_0_2");
					put(grammarAccess.getEquationAccess().getConAssignment_0_3(), "rule__Equation__ConAssignment_0_3");
					put(grammarAccess.getEquationAccess().getWHEN_EQNAssignment_0_4(), "rule__Equation__WHEN_EQNAssignment_0_4");
					put(grammarAccess.getEquationAccess().getCommentAssignment_1(), "rule__Equation__CommentAssignment_1");
					put(grammarAccess.getWhen_equationAccess().getWhenEprAssignment_1(), "rule__When_equation__WhenEprAssignment_1");
					put(grammarAccess.getWhen_equationAccess().getEqn_ThenAssignment_3_0(), "rule__When_equation__Eqn_ThenAssignment_3_0");
					put(grammarAccess.getWhen_equationAccess().getElseWhenEprAssignment_4_1(), "rule__When_equation__ElseWhenEprAssignment_4_1");
					put(grammarAccess.getWhen_equationAccess().getEqn_ThenAssignment_4_3_0(), "rule__When_equation__Eqn_ThenAssignment_4_3_0");
					put(grammarAccess.getIf_equationAccess().getExprtrueAssignment_1(), "rule__If_equation__ExprtrueAssignment_1");
					put(grammarAccess.getIf_equationAccess().getEqnAssignment_3_0(), "rule__If_equation__EqnAssignment_3_0");
					put(grammarAccess.getIf_equationAccess().getExprStilltrueAssignment_4_1(), "rule__If_equation__ExprStilltrueAssignment_4_1");
					put(grammarAccess.getIf_equationAccess().getThenEqnAssignment_4_3_0(), "rule__If_equation__ThenEqnAssignment_4_3_0");
					put(grammarAccess.getIf_equationAccess().getElseEqnAssignment_5_1_0(), "rule__If_equation__ElseEqnAssignment_5_1_0");
					put(grammarAccess.getFor_equationAccess().getFor_loopAssignment_1(), "rule__For_equation__For_loopAssignment_1");
					put(grammarAccess.getFor_equationAccess().getFor_EqnAssignment_3_0(), "rule__For_equation__For_EqnAssignment_3_0");
					put(grammarAccess.getConnect_clauseAccess().getConnector1Assignment_2(), "rule__Connect_clause__Connector1Assignment_2");
					put(grammarAccess.getConnect_clauseAccess().getConnector2Assignment_4(), "rule__Connect_clause__Connector2Assignment_4");
					put(grammarAccess.getAlgorithm_sectionAccess().getAlgorithmAssignment_3_0(), "rule__Algorithm_section__AlgorithmAssignment_3_0");
					put(grammarAccess.getStatementAccess().getIf_statementAssignment_1_0(), "rule__Statement__If_statementAssignment_1_0");
					put(grammarAccess.getStatementAccess().getState_OutputExprListAssignment_1_1(), "rule__Statement__State_OutputExprListAssignment_1_1");
					put(grammarAccess.getStatementAccess().getState_Comp_RefAssignment_1_3(), "rule__Statement__State_Comp_RefAssignment_1_3");
					put(grammarAccess.getStatementAccess().getFor_statementAssignment_1_4(), "rule__Statement__For_statementAssignment_1_4");
					put(grammarAccess.getStatementAccess().getWhen_statementAssignment_1_5(), "rule__Statement__When_statementAssignment_1_5");
					put(grammarAccess.getStatementAccess().getWhile_statementAssignment_1_6(), "rule__Statement__While_statementAssignment_1_6");
					put(grammarAccess.getState_OutputExprListAccess().getOutput_expression_listAssignment_1(), "rule__State_OutputExprList__Output_expression_listAssignment_1");
					put(grammarAccess.getState_OutputExprListAccess().getComponent_refAssignment_4(), "rule__State_OutputExprList__Component_refAssignment_4");
					put(grammarAccess.getState_OutputExprListAccess().getFun_call_argsAssignment_5(), "rule__State_OutputExprList__Fun_call_argsAssignment_5");
					put(grammarAccess.getState_Comp_RefAccess().getComponent_referenceAssignment_0(), "rule__State_Comp_Ref__Component_referenceAssignment_0");
					put(grammarAccess.getState_Comp_RefAccess().getExprAssignment_1_0_1(), "rule__State_Comp_Ref__ExprAssignment_1_0_1");
					put(grammarAccess.getState_Comp_RefAccess().getFun_call_argsAssignment_1_1(), "rule__State_Comp_Ref__Fun_call_argsAssignment_1_1");
					put(grammarAccess.getWhen_statementAccess().getWhen_exprAssignment_1(), "rule__When_statement__When_exprAssignment_1");
					put(grammarAccess.getWhen_statementAccess().getWhen_stat_trueAssignment_3_0(), "rule__When_statement__When_stat_trueAssignment_3_0");
					put(grammarAccess.getWhen_statementAccess().getElse_When_exprAssignment_4_1(), "rule__When_statement__Else_When_exprAssignment_4_1");
					put(grammarAccess.getWhen_statementAccess().getThen_statementAssignment_4_3_0(), "rule__When_statement__Then_statementAssignment_4_3_0");
					put(grammarAccess.getWhile_statementAccess().getWhile_exprAssignment_1(), "rule__While_statement__While_exprAssignment_1");
					put(grammarAccess.getWhile_statementAccess().getWhile_statAssignment_3_0(), "rule__While_statement__While_statAssignment_3_0");
					put(grammarAccess.getFor_statementAccess().getFor_indicesAssignment_1(), "rule__For_statement__For_indicesAssignment_1");
					put(grammarAccess.getFor_statementAccess().getFor_statementAssignment_3_0(), "rule__For_statement__For_statementAssignment_3_0");
					put(grammarAccess.getIf_statementAccess().getExprtrueAssignment_1(), "rule__If_statement__ExprtrueAssignment_1");
					put(grammarAccess.getIf_statementAccess().getStateAssignment_3_0(), "rule__If_statement__StateAssignment_3_0");
					put(grammarAccess.getIf_statementAccess().getExprStilltrueAssignment_4_1(), "rule__If_statement__ExprStilltrueAssignment_4_1");
					put(grammarAccess.getIf_statementAccess().getStateAssignment_4_3_0(), "rule__If_statement__StateAssignment_4_3_0");
					put(grammarAccess.getIf_statementAccess().getStateAssignment_5_1_0(), "rule__If_statement__StateAssignment_5_1_0");
					put(grammarAccess.getExtends_clauseAccess().getNameAssignment_1(), "rule__Extends_clause__NameAssignment_1");
					put(grammarAccess.getExtends_clauseAccess().getClass_ModAssignment_2(), "rule__Extends_clause__Class_ModAssignment_2");
					put(grammarAccess.getExtends_clauseAccess().getAnnotationAssignment_3(), "rule__Extends_clause__AnnotationAssignment_3");
					put(grammarAccess.getAnnotationAccess().getClass_ModAssignment_1(), "rule__Annotation__Class_ModAssignment_1");
					put(grammarAccess.getImport_clauseAccess().getNameAssignment_1_0_2(), "rule__Import_clause__NameAssignment_1_0_2");
					put(grammarAccess.getImport_clauseAccess().getNameAssignment_1_1_0(), "rule__Import_clause__NameAssignment_1_1_0");
					put(grammarAccess.getLanguage_specificationAccess().getStrAssignment(), "rule__Language_specification__StrAssignment");
					put(grammarAccess.getExternal_function_callAccess().getComponent_referenceAssignment_1_0(), "rule__External_function_call__Component_referenceAssignment_1_0");
					put(grammarAccess.getExternal_function_callAccess().getE_ListAssignment_4(), "rule__External_function_call__E_ListAssignment_4");
					put(grammarAccess.getExpressionAccess().getExprAssignment_0(), "rule__Expression__ExprAssignment_0");
					put(grammarAccess.getSimple_expressionAccess().getLog_ExpAssignment_0(), "rule__Simple_expression__Log_ExpAssignment_0");
					put(grammarAccess.getSimple_expressionAccess().getS_Logical_expressionAssignment_1_1(), "rule__Simple_expression__S_Logical_expressionAssignment_1_1");
					put(grammarAccess.getSimple_expressionAccess().getL_Logical_expressionAssignment_1_2_1(), "rule__Simple_expression__L_Logical_expressionAssignment_1_2_1");
					put(grammarAccess.getConditional_exprAccess().getIfexprAssignment_1(), "rule__Conditional_expr__IfexprAssignment_1");
					put(grammarAccess.getConditional_exprAccess().getThenexprAssignment_3(), "rule__Conditional_expr__ThenexprAssignment_3");
					put(grammarAccess.getConditional_exprAccess().getElseifexprAssignment_4_1(), "rule__Conditional_expr__ElseifexprAssignment_4_1");
					put(grammarAccess.getConditional_exprAccess().getTrueexprAssignment_4_3(), "rule__Conditional_expr__TrueexprAssignment_4_3");
					put(grammarAccess.getConditional_exprAccess().getFalseexprAssignment_5_1(), "rule__Conditional_expr__FalseexprAssignment_5_1");
					put(grammarAccess.getLogical_expressionAccess().getLogical_termAssignment_1_1(), "rule__Logical_expression__Logical_termAssignment_1_1");
					put(grammarAccess.getLogical_termAccess().getLogical_factorAssignment_1_1(), "rule__Logical_term__Logical_factorAssignment_1_1");
					put(grammarAccess.getLogical_factorAccess().getRelationAssignment_1(), "rule__Logical_factor__RelationAssignment_1");
					put(grammarAccess.getRelationAccess().getOpAssignment_1_0(), "rule__Relation__OpAssignment_1_0");
					put(grammarAccess.getRelationAccess().getArithmetic_expressionAssignment_1_1(), "rule__Relation__Arithmetic_expressionAssignment_1_1");
					put(grammarAccess.getArithmetic_expressionAccess().getOprAssignment_0(), "rule__Arithmetic_expression__OprAssignment_0");
					put(grammarAccess.getArithmetic_expressionAccess().getTermAssignment_1(), "rule__Arithmetic_expression__TermAssignment_1");
					put(grammarAccess.getArithmetic_expressionAccess().getOper1Assignment_2_0(), "rule__Arithmetic_expression__Oper1Assignment_2_0");
					put(grammarAccess.getArithmetic_expressionAccess().getTerm1Assignment_2_1(), "rule__Arithmetic_expression__Term1Assignment_2_1");
					put(grammarAccess.getTermAccess().getOpAssignment_1_0(), "rule__Term__OpAssignment_1_0");
					put(grammarAccess.getTermAccess().getFactorAssignment_1_1(), "rule__Term__FactorAssignment_1_1");
					put(grammarAccess.getFactorAccess().getPrimaryAssignment_1_1(), "rule__Factor__PrimaryAssignment_1_1");
					put(grammarAccess.getPrimaryAccess().getNumAssignment_0(), "rule__Primary__NumAssignment_0");
					put(grammarAccess.getPrimaryAccess().getIntAssignment_1(), "rule__Primary__IntAssignment_1");
					put(grammarAccess.getPrimaryAccess().getStrAssignment_2(), "rule__Primary__StrAssignment_2");
					put(grammarAccess.getPrimaryAccess().getBoolAssignment_3(), "rule__Primary__BoolAssignment_3");
					put(grammarAccess.getPrimaryAccess().getComponent_referenceAssignment_7(), "rule__Primary__Component_referenceAssignment_7");
					put(grammarAccess.getPrimaryAccess().getOutput_expr_listAssignment_8_1(), "rule__Primary__Output_expr_listAssignment_8_1");
					put(grammarAccess.getPrimaryAccess().getExpre_listAssignment_9_1(), "rule__Primary__Expre_listAssignment_9_1");
					put(grammarAccess.getPrimaryAccess().getExpression_listAssignment_9_2_1(), "rule__Primary__Expression_listAssignment_9_2_1");
					put(grammarAccess.getPrimaryAccess().getF_argumentsAssignment_10_1(), "rule__Primary__F_argumentsAssignment_10_1");
					put(grammarAccess.getPrimaryAccess().getEndAssignment_11(), "rule__Primary__EndAssignment_11");
					put(grammarAccess.getName_FunctionAccess().getFunction_call_argsAssignment_1(), "rule__Name_Function__Function_call_argsAssignment_1");
					put(grammarAccess.getExprDerAccess().getFunctionArgsAssignment_1(), "rule__ExprDer__FunctionArgsAssignment_1");
					put(grammarAccess.getFunction_call_argsAccess().getF_argAssignment_2(), "rule__Function_call_args__F_argAssignment_2");
					put(grammarAccess.getExpression_listAccess().getExprAssignment_0(), "rule__Expression_list__ExprAssignment_0");
					put(grammarAccess.getExpression_listAccess().getExpreAssignment_1_1(), "rule__Expression_list__ExpreAssignment_1_1");
					put(grammarAccess.getNameAccess().getName_IDAssignment_1(), "rule__Name__Name_IDAssignment_1");
					put(grammarAccess.getNameAccess().getNam_IDAssignment_2_1(), "rule__Name__Nam_IDAssignment_2_1");
					put(grammarAccess.getComponent_referenceAccess().getRefAssignment_1(), "rule__Component_reference__RefAssignment_1");
					put(grammarAccess.getComponent_referenceAccess().getSubscripts1Assignment_2(), "rule__Component_reference__Subscripts1Assignment_2");
					put(grammarAccess.getComponent_referenceAccess().getRef1Assignment_3_1(), "rule__Component_reference__Ref1Assignment_3_1");
					put(grammarAccess.getComponent_referenceAccess().getSubscriptsAssignment_3_2(), "rule__Component_reference__SubscriptsAssignment_3_2");
					put(grammarAccess.getOutput_expression_listAccess().getEprAssignment_1(), "rule__Output_expression_list__EprAssignment_1");
					put(grammarAccess.getOutput_expression_listAccess().getExprAssignment_2_1(), "rule__Output_expression_list__ExprAssignment_2_1");
					put(grammarAccess.getArray_subscriptsAccess().getSubAssignment_1(), "rule__Array_subscripts__SubAssignment_1");
					put(grammarAccess.getArray_subscriptsAccess().getSubscriptAssignment_2_1(), "rule__Array_subscripts__SubscriptAssignment_2_1");
					put(grammarAccess.getSubscriptAccess().getExprAssignment_1(), "rule__Subscript__ExprAssignment_1");
					put(grammarAccess.getFunction_argumentsAccess().getArgExpAssignment_0_1(), "rule__Function_arguments__ArgExpAssignment_0_1");
					put(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprAssignment_0_2_0(), "rule__Function_arguments__Fun_Arg_ExprAssignment_0_2_0");
					put(grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForAssignment_0_2_1(), "rule__Function_arguments__Fun_Arg_ForAssignment_0_2_1");
					put(grammarAccess.getFunction_argumentsAccess().getName_argAssignment_1(), "rule__Function_arguments__Name_argAssignment_1");
					put(grammarAccess.getFun_Arguments_expAccess().getArgsAssignment_1(), "rule__Fun_Arguments_exp__ArgsAssignment_1");
					put(grammarAccess.getFun_Arguments_forAccess().getFor_indicesAssignment_1(), "rule__Fun_Arguments_for__For_indicesAssignment_1");
					put(grammarAccess.getNamed_argumentsAccess().getNamed_argumentsAssignment_1_1(), "rule__Named_arguments__Named_argumentsAssignment_1_1");
					put(grammarAccess.getNamed_argumentAccess().getArgAssignment_0(), "rule__Named_argument__ArgAssignment_0");
					put(grammarAccess.getNamed_argumentAccess().getExprAssignment_2(), "rule__Named_argument__ExprAssignment_2");
					put(grammarAccess.getFor_indicesAccess().getFor_indexAssignment_1_1(), "rule__For_indices__For_indexAssignment_1_1");
					put(grammarAccess.getFor_indexAccess().getIndexAssignment_0(), "rule__For_index__IndexAssignment_0");
					put(grammarAccess.getFor_indexAccess().getExprAssignment_1_1(), "rule__For_index__ExprAssignment_1_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.openmodelica.modelicaml.editor.xtext.modification.ui.contentassist.antlr.internal.InternalModificationParser typedParser = (org.openmodelica.modelicaml.editor.xtext.modification.ui.contentassist.antlr.internal.InternalModificationParser) parser;
			typedParser.entryRulemodification_alternatives();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public ModificationGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ModificationGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
