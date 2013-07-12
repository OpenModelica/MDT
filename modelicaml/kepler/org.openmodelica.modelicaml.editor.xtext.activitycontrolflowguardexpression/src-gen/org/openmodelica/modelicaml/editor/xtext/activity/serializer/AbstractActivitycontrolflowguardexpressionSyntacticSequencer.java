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
package org.openmodelica.modelicaml.editor.xtext.activity.serializer;

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
import org.openmodelica.modelicaml.editor.xtext.activity.services.ActivitycontrolflowguardexpressionGrammarAccess;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class AbstractActivitycontrolflowguardexpressionSyntacticSequencer extends AbstractSyntacticSequencer {

	protected ActivitycontrolflowguardexpressionGrammarAccess grammarAccess;
	protected AbstractElementAlias match_component_reference_FullStopKeyword_0_q;
	protected AbstractElementAlias match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1;
	protected AbstractElementAlias match_logical_factor_NotKeyword_0_q;
	protected AbstractElementAlias match_name_FullStopKeyword_0_q;
	protected AbstractElementAlias match_output_expression_list_CommaKeyword_2_0_a;
	protected AbstractElementAlias match_output_expression_list_CommaKeyword_2_0_p;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (ActivitycontrolflowguardexpressionGrammarAccess) access;
		match_component_reference_FullStopKeyword_0_q = new TokenAlias(true, false, grammarAccess.getComponent_referenceAccess().getFullStopKeyword_0());
		match_factor_CircumflexAccentKeyword_1_0_0_or_FullStopCircumflexAccentKeyword_1_0_1 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0()), new TokenAlias(false, false, grammarAccess.getFactorAccess().getFullStopCircumflexAccentKeyword_1_0_1()));
		match_logical_factor_NotKeyword_0_q = new TokenAlias(true, false, grammarAccess.getLogical_factorAccess().getNotKeyword_0());
		match_name_FullStopKeyword_0_q = new TokenAlias(true, false, grammarAccess.getNameAccess().getFullStopKeyword_0());
		match_output_expression_list_CommaKeyword_2_0_a = new TokenAlias(true, true, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
		match_output_expression_list_CommaKeyword_2_0_p = new TokenAlias(false, true, grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
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
			else if(match_logical_factor_NotKeyword_0_q.equals(syntax))
				emit_logical_factor_NotKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_name_FullStopKeyword_0_q.equals(syntax))
				emit_name_FullStopKeyword_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_output_expression_list_CommaKeyword_2_0_a.equals(syntax))
				emit_output_expression_list_CommaKeyword_2_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_output_expression_list_CommaKeyword_2_0_p.equals(syntax))
				emit_output_expression_list_CommaKeyword_2_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
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
	
}
