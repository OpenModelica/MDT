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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.editor.xtext.modification.serializer;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.openmodelica.modelicaml.editor.xtext.modification.services.ModificationGrammarAccess;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class AbstractModificationSyntacticSequencer extends AbstractSyntacticSequencer {

	protected ModificationGrammarAccess grammarAccess;
	protected AbstractElementAlias match_algorithm_section_InitialKeyword_1_q;
	protected AbstractElementAlias match_class_definition_ConnectorKeyword_2_4_1_or___BlockKeyword_2_3_or_ClassKeyword_2_0_or_FunctionKeyword_2_7_or_ModelKeyword_2_1_or_OperatorKeyword_2_8_or_PackageKeyword_2_6_or_RecordKeyword_2_2_or_TypeKeyword_2_5_or___OperatorKeyword_2_10_0_RecordKeyword_2_10_1___or___OperatorKeyword_2_9_0_FunctionKeyword_2_9_1_____or___ExpandableKeyword_2_4_0_ConnectorKeyword_2_4_1__;
	protected AbstractElementAlias match_class_definition_EncapsulatedKeyword_0_q;
	protected AbstractElementAlias match_class_definition_PartialKeyword_1_q;
	protected AbstractElementAlias match_class_specifier_ColonKeyword_1_2_4_1_q;
	protected AbstractElementAlias match_class_specifier___CommaKeyword_1_3_7_0_IDENTTerminalRuleCall_1_3_7_1__a;
	protected AbstractElementAlias match_component_reference_FullStopKeyword_0_q;
	protected AbstractElementAlias match_composition___ExternalKeyword_2_0_SemicolonKeyword_2_4__q;
	protected AbstractElementAlias match_element_FinalKeyword_2_1_q;
	protected AbstractElementAlias match_element_InnerKeyword_2_2_q;
	protected AbstractElementAlias match_element_OuterKeyword_2_3_q;
	protected AbstractElementAlias match_element_RedeclareKeyword_2_0_q;
	protected AbstractElementAlias match_element_modification_or_replaceable_EachKeyword_1_q;
	protected AbstractElementAlias match_element_modification_or_replaceable_FinalKeyword_2_q;
	protected AbstractElementAlias match_element_redeclaration_EachKeyword_2_q;
	protected AbstractElementAlias match_element_redeclaration_FinalKeyword_3_q;
	protected AbstractElementAlias match_equation_section_InitialKeyword_1_q;
	protected AbstractElementAlias match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1;
	protected AbstractElementAlias match_if_equation_ElseKeyword_5_0_q;
	protected AbstractElementAlias match_if_statement_ElseKeyword_5_0_q;
	protected AbstractElementAlias match_import_clause___FullStopKeyword_1_1_1_0_AsteriskKeyword_1_1_1_1__q;
	protected AbstractElementAlias match_left_hand_component_reference_FullStopKeyword_0_q;
	protected AbstractElementAlias match_logical_factor_NotKeyword_0_q;
	protected AbstractElementAlias match_name_FullStopKeyword_0_q;
	protected AbstractElementAlias match_output_expression_list_CommaKeyword_2_0_a;
	protected AbstractElementAlias match_output_expression_list_CommaKeyword_2_0_p;
	protected AbstractElementAlias match_statement_BreakKeyword_1_7_or_ReturnKeyword_1_2;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (ModificationGrammarAccess) access;
		match_algorithm_section_InitialKeyword_1_q = new TokenAlias(true, false, grammarAccess.getAlgorithm_sectionAccess().getInitialKeyword_1());
		match_class_definition_ConnectorKeyword_2_4_1_or___BlockKeyword_2_3_or_ClassKeyword_2_0_or_FunctionKeyword_2_7_or_ModelKeyword_2_1_or_OperatorKeyword_2_8_or_PackageKeyword_2_6_or_RecordKeyword_2_2_or_TypeKeyword_2_5_or___OperatorKeyword_2_10_0_RecordKeyword_2_10_1___or___OperatorKeyword_2_9_0_FunctionKeyword_2_9_1_____or___ExpandableKeyword_2_4_0_ConnectorKeyword_2_4_1__ = new AlternativeAlias(false, false, new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getOperatorKeyword_2_10_0()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getRecordKeyword_2_10_1())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getOperatorKeyword_2_9_0()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getFunctionKeyword_2_9_1())), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getBlockKeyword_2_3()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getClassKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getFunctionKeyword_2_7()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getModelKeyword_2_1()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getOperatorKeyword_2_8()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getPackageKeyword_2_6()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getRecordKeyword_2_2()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getTypeKeyword_2_5())), new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getExpandableKeyword_2_4_0()), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getConnectorKeyword_2_4_1())), new TokenAlias(false, false, grammarAccess.getClass_definitionAccess().getConnectorKeyword_2_4_1()));
		match_class_definition_EncapsulatedKeyword_0_q = new TokenAlias(true, false, grammarAccess.getClass_definitionAccess().getEncapsulatedKeyword_0());
		match_class_definition_PartialKeyword_1_q = new TokenAlias(true, false, grammarAccess.getClass_definitionAccess().getPartialKeyword_1());
		match_class_specifier_ColonKeyword_1_2_4_1_q = new TokenAlias(true, false, grammarAccess.getClass_specifierAccess().getColonKeyword_1_2_4_1());
		match_class_specifier___CommaKeyword_1_3_7_0_IDENTTerminalRuleCall_1_3_7_1__a = new GroupAlias(true, true, new TokenAlias(false, false, grammarAccess.getClass_specifierAccess().getCommaKeyword_1_3_7_0()), new TokenAlias(false, false, grammarAccess.getClass_specifierAccess().getIDENTTerminalRuleCall_1_3_7_1()));
		match_component_reference_FullStopKeyword_0_q = new TokenAlias(true, false, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0());
		match_composition___ExternalKeyword_2_0_SemicolonKeyword_2_4__q = new GroupAlias(true, false, new TokenAlias(false, false, grammarAccess.getCompositionAccess().getExternalKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getCompositionAccess().getSemicolonKeyword_2_4()));
		match_element_FinalKeyword_2_1_q = new TokenAlias(true, false, grammarAccess.getElementAccess().getFinalKeyword_2_1());
		match_element_InnerKeyword_2_2_q = new TokenAlias(true, false, grammarAccess.getElementAccess().getInnerKeyword_2_2());
		match_element_OuterKeyword_2_3_q = new TokenAlias(true, false, grammarAccess.getElementAccess().getOuterKeyword_2_3());
		match_element_RedeclareKeyword_2_0_q = new TokenAlias(true, false, grammarAccess.getElementAccess().getRedeclareKeyword_2_0());
		match_element_modification_or_replaceable_EachKeyword_1_q = new TokenAlias(true, false, grammarAccess.getElement_modification_or_replaceableAccess().getEachKeyword_1());
		match_element_modification_or_replaceable_FinalKeyword_2_q = new TokenAlias(true, false, grammarAccess.getElement_modification_or_replaceableAccess().getFinalKeyword_2());
		match_element_redeclaration_EachKeyword_2_q = new TokenAlias(true, false, grammarAccess.getElement_redeclarationAccess().getEachKeyword_2());
		match_element_redeclaration_FinalKeyword_3_q = new TokenAlias(true, false, grammarAccess.getElement_redeclarationAccess().getFinalKeyword_3());
		match_equation_section_InitialKeyword_1_q = new TokenAlias(true, false, grammarAccess.getEquation_sectionAccess().getInitialKeyword_1());
		match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1()));
		match_if_equation_ElseKeyword_5_0_q = new TokenAlias(true, false, grammarAccess.getIf_equationAccess().getElseKeyword_5_0());
		match_if_statement_ElseKeyword_5_0_q = new TokenAlias(true, false, grammarAccess.getIf_statementAccess().getElseKeyword_5_0());
		match_import_clause___FullStopKeyword_1_1_1_0_AsteriskKeyword_1_1_1_1__q = new GroupAlias(true, false, new TokenAlias(false, false, grammarAccess.getImport_clauseAccess().getFullStopKeyword_1_1_1_0()), new TokenAlias(false, false, grammarAccess.getImport_clauseAccess().getAsteriskKeyword_1_1_1_1()));
		match_left_hand_component_reference_FullStopKeyword_0_q = new TokenAlias(true, false, grammarAccess.getLeft_hand_component_referenceAccess().getFullStopKeyword_0());
		match_logical_factor_NotKeyword_0_q = new TokenAlias(true, false, grammarAccess.getLogical_factorAccess().getNotKeyword_0());
		match_name_FullStopKeyword_0_q = new TokenAlias(true, false, grammarAccess.getNameAccess().getFullStopKeyword_0());
		match_output_expression_list_CommaKeyword_2_0_a = new TokenAlias(true, true, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
		match_output_expression_list_CommaKeyword_2_0_p = new TokenAlias(false, true, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
		match_statement_BreakKeyword_1_7_or_ReturnKeyword_1_2 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getStatementAccess().getBreakKeyword_1_7()), new TokenAlias(false, false, grammarAccess.getStatementAccess().getReturnKeyword_1_2()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getIDENTRule())
			return getIDENTToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getBase_prefixRule())
			return getbase_prefixToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getCommentRule())
			return getcommentToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getString_commentRule())
			return getstring_commentToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getType_prefixRule())
			return gettype_prefixToken(semanticObject, ruleCall, node);
		return "";
	}
	
	protected String getIDENTToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	protected String getbase_prefixToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	protected String getcommentToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	protected String getstring_commentToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	protected String gettype_prefixToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_algorithm_section_InitialKeyword_1_q.equals(syntax))
				emit_algorithm_section_InitialKeyword_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_class_definition_ConnectorKeyword_2_4_1_or___BlockKeyword_2_3_or_ClassKeyword_2_0_or_FunctionKeyword_2_7_or_ModelKeyword_2_1_or_OperatorKeyword_2_8_or_PackageKeyword_2_6_or_RecordKeyword_2_2_or_TypeKeyword_2_5_or___OperatorKeyword_2_10_0_RecordKeyword_2_10_1___or___OperatorKeyword_2_9_0_FunctionKeyword_2_9_1_____or___ExpandableKeyword_2_4_0_ConnectorKeyword_2_4_1__.equals(syntax))
				emit_class_definition_ConnectorKeyword_2_4_1_or___BlockKeyword_2_3_or_ClassKeyword_2_0_or_FunctionKeyword_2_7_or_ModelKeyword_2_1_or_OperatorKeyword_2_8_or_PackageKeyword_2_6_or_RecordKeyword_2_2_or_TypeKeyword_2_5_or___OperatorKeyword_2_10_0_RecordKeyword_2_10_1___or___OperatorKeyword_2_9_0_FunctionKeyword_2_9_1_____or___ExpandableKeyword_2_4_0_ConnectorKeyword_2_4_1__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_class_definition_EncapsulatedKeyword_0_q.equals(syntax))
				emit_class_definition_EncapsulatedKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_class_definition_PartialKeyword_1_q.equals(syntax))
				emit_class_definition_PartialKeyword_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_class_specifier_ColonKeyword_1_2_4_1_q.equals(syntax))
				emit_class_specifier_ColonKeyword_1_2_4_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_class_specifier___CommaKeyword_1_3_7_0_IDENTTerminalRuleCall_1_3_7_1__a.equals(syntax))
				emit_class_specifier___CommaKeyword_1_3_7_0_IDENTTerminalRuleCall_1_3_7_1__a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_component_reference_FullStopKeyword_0_q.equals(syntax))
				emit_component_reference_FullStopKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_composition___ExternalKeyword_2_0_SemicolonKeyword_2_4__q.equals(syntax))
				emit_composition___ExternalKeyword_2_0_SemicolonKeyword_2_4__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_FinalKeyword_2_1_q.equals(syntax))
				emit_element_FinalKeyword_2_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_InnerKeyword_2_2_q.equals(syntax))
				emit_element_InnerKeyword_2_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_OuterKeyword_2_3_q.equals(syntax))
				emit_element_OuterKeyword_2_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_RedeclareKeyword_2_0_q.equals(syntax))
				emit_element_RedeclareKeyword_2_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_modification_or_replaceable_EachKeyword_1_q.equals(syntax))
				emit_element_modification_or_replaceable_EachKeyword_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_modification_or_replaceable_FinalKeyword_2_q.equals(syntax))
				emit_element_modification_or_replaceable_FinalKeyword_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_redeclaration_EachKeyword_2_q.equals(syntax))
				emit_element_redeclaration_EachKeyword_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_element_redeclaration_FinalKeyword_3_q.equals(syntax))
				emit_element_redeclaration_FinalKeyword_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_equation_section_InitialKeyword_1_q.equals(syntax))
				emit_equation_section_InitialKeyword_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1.equals(syntax))
				emit_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_if_equation_ElseKeyword_5_0_q.equals(syntax))
				emit_if_equation_ElseKeyword_5_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_if_statement_ElseKeyword_5_0_q.equals(syntax))
				emit_if_statement_ElseKeyword_5_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_import_clause___FullStopKeyword_1_1_1_0_AsteriskKeyword_1_1_1_1__q.equals(syntax))
				emit_import_clause___FullStopKeyword_1_1_1_0_AsteriskKeyword_1_1_1_1__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_left_hand_component_reference_FullStopKeyword_0_q.equals(syntax))
				emit_left_hand_component_reference_FullStopKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_logical_factor_NotKeyword_0_q.equals(syntax))
				emit_logical_factor_NotKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_name_FullStopKeyword_0_q.equals(syntax))
				emit_name_FullStopKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_output_expression_list_CommaKeyword_2_0_a.equals(syntax))
				emit_output_expression_list_CommaKeyword_2_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_output_expression_list_CommaKeyword_2_0_p.equals(syntax))
				emit_output_expression_list_CommaKeyword_2_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_statement_BreakKeyword_1_7_or_ReturnKeyword_1_2.equals(syntax))
				emit_statement_BreakKeyword_1_7_or_ReturnKeyword_1_2(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     'initial'?
	 */
	protected void emit_algorithm_section_InitialKeyword_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (
	     'connector' | 
	     ('expandable' 'connector') | 
	     (
	         ('operator' 'record') | 
	         'class' | 
	         'package' | 
	         'function' | 
	         'operator' | 
	         'model' | 
	         'type' | 
	         ('operator' 'function') | 
	         'block' | 
	         'record'
	     )
	 )
	 */
	protected void emit_class_definition_ConnectorKeyword_2_4_1_or___BlockKeyword_2_3_or_ClassKeyword_2_0_or_FunctionKeyword_2_7_or_ModelKeyword_2_1_or_OperatorKeyword_2_8_or_PackageKeyword_2_6_or_RecordKeyword_2_2_or_TypeKeyword_2_5_or___OperatorKeyword_2_10_0_RecordKeyword_2_10_1___or___OperatorKeyword_2_9_0_FunctionKeyword_2_9_1_____or___ExpandableKeyword_2_4_0_ConnectorKeyword_2_4_1__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'encapsulated'?
	 */
	protected void emit_class_definition_EncapsulatedKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'partial'?
	 */
	protected void emit_class_definition_PartialKeyword_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ':'?
	 */
	protected void emit_class_specifier_ColonKeyword_1_2_4_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (',' IDENT)*
	 */
	protected void emit_class_specifier___CommaKeyword_1_3_7_0_IDENTTerminalRuleCall_1_3_7_1__a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '.'?
	 */
	protected void emit_component_reference_FullStopKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('external' ';')?
	 */
	protected void emit_composition___ExternalKeyword_2_0_SemicolonKeyword_2_4__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'final'?
	 */
	protected void emit_element_FinalKeyword_2_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'inner'?
	 */
	protected void emit_element_InnerKeyword_2_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'outer'?
	 */
	protected void emit_element_OuterKeyword_2_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'redeclare'?
	 */
	protected void emit_element_RedeclareKeyword_2_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'each'?
	 */
	protected void emit_element_modification_or_replaceable_EachKeyword_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'final'?
	 */
	protected void emit_element_modification_or_replaceable_FinalKeyword_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'each'?
	 */
	protected void emit_element_redeclaration_EachKeyword_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'final'?
	 */
	protected void emit_element_redeclaration_FinalKeyword_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'initial'?
	 */
	protected void emit_equation_section_InitialKeyword_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '^' | '.^'
	 */
	protected void emit_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'else'?
	 */
	protected void emit_if_equation_ElseKeyword_5_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'else'?
	 */
	protected void emit_if_statement_ElseKeyword_5_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('.' '*')?
	 */
	protected void emit_import_clause___FullStopKeyword_1_1_1_0_AsteriskKeyword_1_1_1_1__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '.'?
	 */
	protected void emit_left_hand_component_reference_FullStopKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'not'?
	 */
	protected void emit_logical_factor_NotKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '.'?
	 */
	protected void emit_name_FullStopKeyword_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','*
	 */
	protected void emit_output_expression_list_CommaKeyword_2_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ','+
	 */
	protected void emit_output_expression_list_CommaKeyword_2_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'return' | 'break'
	 */
	protected void emit_statement_BreakKeyword_1_7_or_ReturnKeyword_1_2(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
