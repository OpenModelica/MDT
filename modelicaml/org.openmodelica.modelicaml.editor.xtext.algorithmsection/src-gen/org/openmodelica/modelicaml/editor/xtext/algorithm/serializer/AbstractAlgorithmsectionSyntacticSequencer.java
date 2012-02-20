package org.openmodelica.modelicaml.editor.xtext.algorithm.serializer;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.openmodelica.modelicaml.editor.xtext.algorithm.services.AlgorithmsectionGrammarAccess;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class AbstractAlgorithmsectionSyntacticSequencer extends AbstractSyntacticSequencer {

	protected AlgorithmsectionGrammarAccess grammarAccess;
	protected AbstractElementAlias match_component_reference_FullStopKeyword_0_q;
	protected AbstractElementAlias match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1;
	protected AbstractElementAlias match_if_statement_ElseKeyword_5_0_q;
	protected AbstractElementAlias match_logical_factor_NotKeyword_0_q;
	protected AbstractElementAlias match_name_FullStopKeyword_0_q;
	protected AbstractElementAlias match_output_expression_list_CommaKeyword_2_0_a;
	protected AbstractElementAlias match_output_expression_list_CommaKeyword_2_0_p;
	protected AbstractElementAlias match_statement_BreakKeyword_1_10_or_ReturnKeyword_1_3;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (AlgorithmsectionGrammarAccess) access;
		match_component_reference_FullStopKeyword_0_q = new TokenAlias(true, false, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0());
		match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1()));
		match_if_statement_ElseKeyword_5_0_q = new TokenAlias(true, false, grammarAccess.getIf_statementAccess().getElseKeyword_5_0());
		match_logical_factor_NotKeyword_0_q = new TokenAlias(true, false, grammarAccess.getLogical_factorAccess().getNotKeyword_0());
		match_name_FullStopKeyword_0_q = new TokenAlias(true, false, grammarAccess.getNameAccess().getFullStopKeyword_0());
		match_output_expression_list_CommaKeyword_2_0_a = new TokenAlias(true, true, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
		match_output_expression_list_CommaKeyword_2_0_p = new TokenAlias(false, true, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
		match_statement_BreakKeyword_1_10_or_ReturnKeyword_1_3 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getStatementAccess().getBreakKeyword_1_10()), new TokenAlias(false, false, grammarAccess.getStatementAccess().getReturnKeyword_1_3()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getCommentRule())
			return getcommentToken(semanticObject, ruleCall, node);
		return "";
	}
	
	protected String getcommentToken(EObject semanticObject, RuleCall ruleCall, INode node) {
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
			if(match_component_reference_FullStopKeyword_0_q.equals(syntax))
				emit_component_reference_FullStopKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1.equals(syntax))
				emit_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_if_statement_ElseKeyword_5_0_q.equals(syntax))
				emit_if_statement_ElseKeyword_5_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_logical_factor_NotKeyword_0_q.equals(syntax))
				emit_logical_factor_NotKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_name_FullStopKeyword_0_q.equals(syntax))
				emit_name_FullStopKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_output_expression_list_CommaKeyword_2_0_a.equals(syntax))
				emit_output_expression_list_CommaKeyword_2_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_output_expression_list_CommaKeyword_2_0_p.equals(syntax))
				emit_output_expression_list_CommaKeyword_2_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_statement_BreakKeyword_1_10_or_ReturnKeyword_1_3.equals(syntax))
				emit_statement_BreakKeyword_1_10_or_ReturnKeyword_1_3(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
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
	 *     '.^' | '^'
	 */
	protected void emit_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
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
	 *     'break' | 'return'
	 */
	protected void emit_statement_BreakKeyword_1_10_or_ReturnKeyword_1_3(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
