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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */

package org.openmodelica.modelicaml.editor.xtext.algorithm.services;

import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;
import org.openmodelica.modelicaml.editor.xtext.model.services.ModeleditorGrammarAccess;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AlgorithmsectionGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class Algorithm_clauseElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "algorithm_clause");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cAlgorithm_clauseAction_0 = (Action)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cAlgorithmAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cAlgorithmStatementParserRuleCall_1_0_0 = (RuleCall)cAlgorithmAssignment_1_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		
		////////////////////////////////////////////Algorithm//////////////////////
		//algorithm_clause:
		//	{algorithm_clause} (Algorithm+=statement ";")*;
		public ParserRule getRule() { return rule; }

		//{algorithm_clause} (Algorithm+=statement ";")*
		public Group getGroup() { return cGroup; }

		//{algorithm_clause}
		public Action getAlgorithm_clauseAction_0() { return cAlgorithm_clauseAction_0; }

		//(Algorithm+=statement ";")*
		public Group getGroup_1() { return cGroup_1; }

		//Algorithm+=statement
		public Assignment getAlgorithmAssignment_1_0() { return cAlgorithmAssignment_1_0; }

		//statement
		public RuleCall getAlgorithmStatementParserRuleCall_1_0_0() { return cAlgorithmStatementParserRuleCall_1_0_0; }

		//";"
		public Keyword getSemicolonKeyword_1_1() { return cSemicolonKeyword_1_1; }
	}

	public class StatementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cStatementAction_0 = (Action)cGroup.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Assignment cIf_statementAssignment_1_0 = (Assignment)cAlternatives_1.eContents().get(0);
		private final RuleCall cIf_statementIf_statementParserRuleCall_1_0_0 = (RuleCall)cIf_statementAssignment_1_0.eContents().get(0);
		private final Assignment cState_OutputExprListAssignment_1_1 = (Assignment)cAlternatives_1.eContents().get(1);
		private final RuleCall cState_OutputExprListState_OutputExprListParserRuleCall_1_1_0 = (RuleCall)cState_OutputExprListAssignment_1_1.eContents().get(0);
		private final Assignment cAlgo_M_GAssignment_1_2 = (Assignment)cAlternatives_1.eContents().get(2);
		private final RuleCall cAlgo_M_GAlgorithm_Macros_GEN_SIGNALParserRuleCall_1_2_0 = (RuleCall)cAlgo_M_GAssignment_1_2.eContents().get(0);
		private final Keyword cReturnKeyword_1_3 = (Keyword)cAlternatives_1.eContents().get(3);
		private final Assignment cState_Comp_RefAssignment_1_4 = (Assignment)cAlternatives_1.eContents().get(4);
		private final RuleCall cState_Comp_RefState_Comp_RefParserRuleCall_1_4_0 = (RuleCall)cState_Comp_RefAssignment_1_4.eContents().get(0);
		private final Assignment cFor_statementAssignment_1_5 = (Assignment)cAlternatives_1.eContents().get(5);
		private final RuleCall cFor_statementFor_statementParserRuleCall_1_5_0 = (RuleCall)cFor_statementAssignment_1_5.eContents().get(0);
		private final Assignment cWhen_statementAssignment_1_6 = (Assignment)cAlternatives_1.eContents().get(6);
		private final RuleCall cWhen_statementWhen_statementParserRuleCall_1_6_0 = (RuleCall)cWhen_statementAssignment_1_6.eContents().get(0);
		private final Assignment cWhile_statementAssignment_1_7 = (Assignment)cAlternatives_1.eContents().get(7);
		private final RuleCall cWhile_statementWhile_statementParserRuleCall_1_7_0 = (RuleCall)cWhile_statementAssignment_1_7.eContents().get(0);
		private final Assignment cAlgo_G_IAssignment_1_8 = (Assignment)cAlternatives_1.eContents().get(8);
		private final RuleCall cAlgo_G_IAlgorithm_Macros_GEN_INCParserRuleCall_1_8_0 = (RuleCall)cAlgo_G_IAssignment_1_8.eContents().get(0);
		private final Assignment cAlgo_G_CAssignment_1_9 = (Assignment)cAlternatives_1.eContents().get(9);
		private final RuleCall cAlgo_G_CAlgorithm_Macros_GEN_CHANGEParserRuleCall_1_9_0 = (RuleCall)cAlgo_G_CAssignment_1_9.eContents().get(0);
		private final Keyword cBreakKeyword_1_10 = (Keyword)cAlternatives_1.eContents().get(10);
		private final RuleCall cCommentParserRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		
		//statement:
		//	{statement} (If_statement=if_statement | State_OutputExprList=state_OutputExprList |
		//	Algo_M_G=Algorithm_Macros_GEN_SIGNAL | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
		//	When_statement=when_statement | While_statement=while_statement | Algo_G_I=Algorithm_Macros_GEN_INC |
		//	Algo_G_C=Algorithm_Macros_GEN_CHANGE | "break") comment;
		public ParserRule getRule() { return rule; }

		//{statement} (If_statement=if_statement | State_OutputExprList=state_OutputExprList |
		//Algo_M_G=Algorithm_Macros_GEN_SIGNAL | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
		//When_statement=when_statement | While_statement=while_statement | Algo_G_I=Algorithm_Macros_GEN_INC |
		//Algo_G_C=Algorithm_Macros_GEN_CHANGE | "break") comment
		public Group getGroup() { return cGroup; }

		//{statement}
		public Action getStatementAction_0() { return cStatementAction_0; }

		//If_statement=if_statement | State_OutputExprList=state_OutputExprList | Algo_M_G=Algorithm_Macros_GEN_SIGNAL | "return"
		//| state_Comp_Ref=state_Comp_Ref | For_statement=for_statement | When_statement=when_statement |
		//While_statement=while_statement | Algo_G_I=Algorithm_Macros_GEN_INC | Algo_G_C=Algorithm_Macros_GEN_CHANGE | "break"
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//If_statement=if_statement
		public Assignment getIf_statementAssignment_1_0() { return cIf_statementAssignment_1_0; }

		//if_statement
		public RuleCall getIf_statementIf_statementParserRuleCall_1_0_0() { return cIf_statementIf_statementParserRuleCall_1_0_0; }

		//State_OutputExprList=state_OutputExprList
		public Assignment getState_OutputExprListAssignment_1_1() { return cState_OutputExprListAssignment_1_1; }

		//state_OutputExprList
		public RuleCall getState_OutputExprListState_OutputExprListParserRuleCall_1_1_0() { return cState_OutputExprListState_OutputExprListParserRuleCall_1_1_0; }

		//Algo_M_G=Algorithm_Macros_GEN_SIGNAL
		public Assignment getAlgo_M_GAssignment_1_2() { return cAlgo_M_GAssignment_1_2; }

		//Algorithm_Macros_GEN_SIGNAL
		public RuleCall getAlgo_M_GAlgorithm_Macros_GEN_SIGNALParserRuleCall_1_2_0() { return cAlgo_M_GAlgorithm_Macros_GEN_SIGNALParserRuleCall_1_2_0; }

		//"return"
		public Keyword getReturnKeyword_1_3() { return cReturnKeyword_1_3; }

		//state_Comp_Ref=state_Comp_Ref
		public Assignment getState_Comp_RefAssignment_1_4() { return cState_Comp_RefAssignment_1_4; }

		//state_Comp_Ref
		public RuleCall getState_Comp_RefState_Comp_RefParserRuleCall_1_4_0() { return cState_Comp_RefState_Comp_RefParserRuleCall_1_4_0; }

		//For_statement=for_statement
		public Assignment getFor_statementAssignment_1_5() { return cFor_statementAssignment_1_5; }

		//for_statement
		public RuleCall getFor_statementFor_statementParserRuleCall_1_5_0() { return cFor_statementFor_statementParserRuleCall_1_5_0; }

		//When_statement=when_statement
		public Assignment getWhen_statementAssignment_1_6() { return cWhen_statementAssignment_1_6; }

		//when_statement
		public RuleCall getWhen_statementWhen_statementParserRuleCall_1_6_0() { return cWhen_statementWhen_statementParserRuleCall_1_6_0; }

		//While_statement=while_statement
		public Assignment getWhile_statementAssignment_1_7() { return cWhile_statementAssignment_1_7; }

		//while_statement
		public RuleCall getWhile_statementWhile_statementParserRuleCall_1_7_0() { return cWhile_statementWhile_statementParserRuleCall_1_7_0; }

		//Algo_G_I=Algorithm_Macros_GEN_INC
		public Assignment getAlgo_G_IAssignment_1_8() { return cAlgo_G_IAssignment_1_8; }

		//Algorithm_Macros_GEN_INC
		public RuleCall getAlgo_G_IAlgorithm_Macros_GEN_INCParserRuleCall_1_8_0() { return cAlgo_G_IAlgorithm_Macros_GEN_INCParserRuleCall_1_8_0; }

		//Algo_G_C=Algorithm_Macros_GEN_CHANGE
		public Assignment getAlgo_G_CAssignment_1_9() { return cAlgo_G_CAssignment_1_9; }

		//Algorithm_Macros_GEN_CHANGE
		public RuleCall getAlgo_G_CAlgorithm_Macros_GEN_CHANGEParserRuleCall_1_9_0() { return cAlgo_G_CAlgorithm_Macros_GEN_CHANGEParserRuleCall_1_9_0; }

		//"break"
		public Keyword getBreakKeyword_1_10() { return cBreakKeyword_1_10; }

		//comment
		public RuleCall getCommentParserRuleCall_2() { return cCommentParserRuleCall_2; }
	}

	public class Algorithm_Macros_GEN_SIGNALElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Algorithm_Macros_GEN_SIGNAL");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cGEN_SIGNALKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_0 = (RuleCall)cComponent_referenceAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		/////////////////////////////////////Macros//////////////////////////////////
		//Algorithm_Macros_GEN_SIGNAL:
		//	"GEN_SIGNAL" "(" Component_reference=component_reference ")";
		public ParserRule getRule() { return rule; }

		//"GEN_SIGNAL" "(" Component_reference=component_reference ")"
		public Group getGroup() { return cGroup; }

		//"GEN_SIGNAL"
		public Keyword getGEN_SIGNALKeyword_0() { return cGEN_SIGNALKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2() { return cComponent_referenceAssignment_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Algorithm_Macros_GEN_INCElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Algorithm_Macros_GEN_INC");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cGEN_INCKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_0 = (RuleCall)cComponent_referenceAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Algorithm_Macros_GEN_INC:
		//	"GEN_INC" "(" Component_reference=component_reference ")";
		public ParserRule getRule() { return rule; }

		//"GEN_INC" "(" Component_reference=component_reference ")"
		public Group getGroup() { return cGroup; }

		//"GEN_INC"
		public Keyword getGEN_INCKeyword_0() { return cGEN_INCKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2() { return cComponent_referenceAssignment_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class Algorithm_Macros_GEN_CHANGEElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Algorithm_Macros_GEN_CHANGE");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cGEN_CHANGEKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_2_0 = (RuleCall)cComponent_referenceAssignment_2.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//Algorithm_Macros_GEN_CHANGE:
		//	"GEN_CHANGE" "(" Component_reference=component_reference ")";
		public ParserRule getRule() { return rule; }

		//"GEN_CHANGE" "(" Component_reference=component_reference ")"
		public Group getGroup() { return cGroup; }

		//"GEN_CHANGE"
		public Keyword getGEN_CHANGEKeyword_0() { return cGEN_CHANGEKeyword_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_2() { return cComponent_referenceAssignment_2; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_2_0() { return cComponent_referenceComponent_referenceParserRuleCall_2_0; }

		//")"
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}

	public class State_OutputExprListElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "state_OutputExprList");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftParenthesisKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cOutput_expression_listAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cOutput_expression_listOutput_expression_listParserRuleCall_1_0 = (RuleCall)cOutput_expression_listAssignment_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Keyword cColonEqualsSignKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cComponent_refAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cComponent_refComponent_referenceParserRuleCall_4_0 = (RuleCall)cComponent_refAssignment_4.eContents().get(0);
		private final Assignment cFun_call_argsAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cFun_call_argsFunction_call_argsParserRuleCall_5_0 = (RuleCall)cFun_call_argsAssignment_5.eContents().get(0);
		
		/////////////////////////////////////Statements//////////////////////////////
		//state_OutputExprList:
		//	"(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
		//	fun_call_args=function_call_args;
		public ParserRule getRule() { return rule; }

		//"(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
		//fun_call_args=function_call_args
		public Group getGroup() { return cGroup; }

		//"("
		public Keyword getLeftParenthesisKeyword_0() { return cLeftParenthesisKeyword_0; }

		//output_expression_list=output_expression_list
		public Assignment getOutput_expression_listAssignment_1() { return cOutput_expression_listAssignment_1; }

		//output_expression_list
		public RuleCall getOutput_expression_listOutput_expression_listParserRuleCall_1_0() { return cOutput_expression_listOutput_expression_listParserRuleCall_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_2() { return cRightParenthesisKeyword_2; }

		//":="
		public Keyword getColonEqualsSignKeyword_3() { return cColonEqualsSignKeyword_3; }

		//Component_ref=component_reference
		public Assignment getComponent_refAssignment_4() { return cComponent_refAssignment_4; }

		//component_reference
		public RuleCall getComponent_refComponent_referenceParserRuleCall_4_0() { return cComponent_refComponent_referenceParserRuleCall_4_0; }

		//fun_call_args=function_call_args
		public Assignment getFun_call_argsAssignment_5() { return cFun_call_argsAssignment_5; }

		//function_call_args
		public RuleCall getFun_call_argsFunction_call_argsParserRuleCall_5_0() { return cFun_call_argsFunction_call_argsParserRuleCall_5_0; }
	}

	public class State_Comp_RefElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "state_Comp_Ref");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cComponent_referenceAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_0_0 = (RuleCall)cComponent_referenceAssignment_0.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cAlternatives_1.eContents().get(0);
		private final Keyword cColonEqualsSignKeyword_1_0_0 = (Keyword)cGroup_1_0.eContents().get(0);
		private final Assignment cExprAssignment_1_0_1 = (Assignment)cGroup_1_0.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_1_0_1_0 = (RuleCall)cExprAssignment_1_0_1.eContents().get(0);
		private final Assignment cFun_call_argsAssignment_1_1 = (Assignment)cAlternatives_1.eContents().get(1);
		private final RuleCall cFun_call_argsFunction_call_argsParserRuleCall_1_1_0 = (RuleCall)cFun_call_argsAssignment_1_1.eContents().get(0);
		
		//state_Comp_Ref:
		//	component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args);
		public ParserRule getRule() { return rule; }

		//component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args)
		public Group getGroup() { return cGroup; }

		//component_reference=component_reference
		public Assignment getComponent_referenceAssignment_0() { return cComponent_referenceAssignment_0; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_0_0() { return cComponent_referenceComponent_referenceParserRuleCall_0_0; }

		//":=" expr=expression | fun_call_args=function_call_args
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//":=" expr=expression
		public Group getGroup_1_0() { return cGroup_1_0; }

		//":="
		public Keyword getColonEqualsSignKeyword_1_0_0() { return cColonEqualsSignKeyword_1_0_0; }

		//expr=expression
		public Assignment getExprAssignment_1_0_1() { return cExprAssignment_1_0_1; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_1_0_1_0() { return cExprExpressionParserRuleCall_1_0_1_0; }

		//fun_call_args=function_call_args
		public Assignment getFun_call_argsAssignment_1_1() { return cFun_call_argsAssignment_1_1; }

		//function_call_args
		public RuleCall getFun_call_argsFunction_call_argsParserRuleCall_1_1_0() { return cFun_call_argsFunction_call_argsParserRuleCall_1_1_0; }
	}

	public class When_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "when_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cWhenKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cWhen_exprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cWhen_exprExpressionParserRuleCall_1_0 = (RuleCall)cWhen_exprAssignment_1.eContents().get(0);
		private final Keyword cThenKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cWhen_stat_trueAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cWhen_stat_trueStatementParserRuleCall_3_0_0 = (RuleCall)cWhen_stat_trueAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cElsewhenKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cElse_When_exprAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cElse_When_exprExpressionParserRuleCall_4_1_0 = (RuleCall)cElse_When_exprAssignment_4_1.eContents().get(0);
		private final Keyword cThenKeyword_4_2 = (Keyword)cGroup_4.eContents().get(2);
		private final Group cGroup_4_3 = (Group)cGroup_4.eContents().get(3);
		private final Assignment cThen_statementAssignment_4_3_0 = (Assignment)cGroup_4_3.eContents().get(0);
		private final RuleCall cThen_statementStatementParserRuleCall_4_3_0_0 = (RuleCall)cThen_statementAssignment_4_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_4_3_1 = (Keyword)cGroup_4_3.eContents().get(1);
		private final Keyword cEndKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Keyword cWhenKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		//when_statement:
		//	"when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
		//	(Then_statement+=statement ";")*)* "end" "when";
		public ParserRule getRule() { return rule; }

		//"when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
		//(Then_statement+=statement ";")*)* "end" "when"
		public Group getGroup() { return cGroup; }

		//"when"
		public Keyword getWhenKeyword_0() { return cWhenKeyword_0; }

		//When_expr=expression
		public Assignment getWhen_exprAssignment_1() { return cWhen_exprAssignment_1; }

		//expression
		public RuleCall getWhen_exprExpressionParserRuleCall_1_0() { return cWhen_exprExpressionParserRuleCall_1_0; }

		//"then"
		public Keyword getThenKeyword_2() { return cThenKeyword_2; }

		//(When_stat_true+=statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//When_stat_true+=statement
		public Assignment getWhen_stat_trueAssignment_3_0() { return cWhen_stat_trueAssignment_3_0; }

		//statement
		public RuleCall getWhen_stat_trueStatementParserRuleCall_3_0_0() { return cWhen_stat_trueStatementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//("elsewhen" Else_When_expr+=expression "then" (Then_statement+=statement ";")*)*
		public Group getGroup_4() { return cGroup_4; }

		//"elsewhen"
		public Keyword getElsewhenKeyword_4_0() { return cElsewhenKeyword_4_0; }

		//Else_When_expr+=expression
		public Assignment getElse_When_exprAssignment_4_1() { return cElse_When_exprAssignment_4_1; }

		//expression
		public RuleCall getElse_When_exprExpressionParserRuleCall_4_1_0() { return cElse_When_exprExpressionParserRuleCall_4_1_0; }

		//"then"
		public Keyword getThenKeyword_4_2() { return cThenKeyword_4_2; }

		//(Then_statement+=statement ";")*
		public Group getGroup_4_3() { return cGroup_4_3; }

		//Then_statement+=statement
		public Assignment getThen_statementAssignment_4_3_0() { return cThen_statementAssignment_4_3_0; }

		//statement
		public RuleCall getThen_statementStatementParserRuleCall_4_3_0_0() { return cThen_statementStatementParserRuleCall_4_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_4_3_1() { return cSemicolonKeyword_4_3_1; }

		//"end"
		public Keyword getEndKeyword_5() { return cEndKeyword_5; }

		//"when"
		public Keyword getWhenKeyword_6() { return cWhenKeyword_6; }
	}

	public class While_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "while_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cWhileKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cWhile_exprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cWhile_exprExpressionParserRuleCall_1_0 = (RuleCall)cWhile_exprAssignment_1.eContents().get(0);
		private final Keyword cLoopKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cWhile_statAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cWhile_statStatementParserRuleCall_3_0_0 = (RuleCall)cWhile_statAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cWhileKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//while_statement:
		//	"while" while_expr=expression "loop" (While_stat+=statement ";")* "end" "while";
		public ParserRule getRule() { return rule; }

		//"while" while_expr=expression "loop" (While_stat+=statement ";")* "end" "while"
		public Group getGroup() { return cGroup; }

		//"while"
		public Keyword getWhileKeyword_0() { return cWhileKeyword_0; }

		//while_expr=expression
		public Assignment getWhile_exprAssignment_1() { return cWhile_exprAssignment_1; }

		//expression
		public RuleCall getWhile_exprExpressionParserRuleCall_1_0() { return cWhile_exprExpressionParserRuleCall_1_0; }

		//"loop"
		public Keyword getLoopKeyword_2() { return cLoopKeyword_2; }

		//(While_stat+=statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//While_stat+=statement
		public Assignment getWhile_statAssignment_3_0() { return cWhile_statAssignment_3_0; }

		//statement
		public RuleCall getWhile_statStatementParserRuleCall_3_0_0() { return cWhile_statStatementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//"end"
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }

		//"while"
		public Keyword getWhileKeyword_5() { return cWhileKeyword_5; }
	}

	public class For_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "for_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cForKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cFor_loopAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cFor_loopFor_indicesParserRuleCall_1_0 = (RuleCall)cFor_loopAssignment_1.eContents().get(0);
		private final Keyword cLoopKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cFor_algoAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cFor_algoStatementParserRuleCall_3_0_0 = (RuleCall)cFor_algoAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cForKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//for_statement:
		//	"for" for_loop=for_indices "loop" (For_algo+=statement ";")* "end" "for";
		public ParserRule getRule() { return rule; }

		//"for" for_loop=for_indices "loop" (For_algo+=statement ";")* "end" "for"
		public Group getGroup() { return cGroup; }

		//"for"
		public Keyword getForKeyword_0() { return cForKeyword_0; }

		//for_loop=for_indices
		public Assignment getFor_loopAssignment_1() { return cFor_loopAssignment_1; }

		//for_indices
		public RuleCall getFor_loopFor_indicesParserRuleCall_1_0() { return cFor_loopFor_indicesParserRuleCall_1_0; }

		//"loop"
		public Keyword getLoopKeyword_2() { return cLoopKeyword_2; }

		//(For_algo+=statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//For_algo+=statement
		public Assignment getFor_algoAssignment_3_0() { return cFor_algoAssignment_3_0; }

		//statement
		public RuleCall getFor_algoStatementParserRuleCall_3_0_0() { return cFor_algoStatementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//"end"
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }

		//"for"
		public Keyword getForKeyword_5() { return cForKeyword_5; }
	}

	public class If_statementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "if_statement");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cIfKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cExprtrueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cExprtrueExpressionParserRuleCall_1_0 = (RuleCall)cExprtrueAssignment_1.eContents().get(0);
		private final Keyword cThenKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Assignment cStateAssignment_3_0 = (Assignment)cGroup_3.eContents().get(0);
		private final RuleCall cStateStatementParserRuleCall_3_0_0 = (RuleCall)cStateAssignment_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cElseifKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cExprStilltrueAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cExprStilltrueExpressionParserRuleCall_4_1_0 = (RuleCall)cExprStilltrueAssignment_4_1.eContents().get(0);
		private final Keyword cThenKeyword_4_2 = (Keyword)cGroup_4.eContents().get(2);
		private final Group cGroup_4_3 = (Group)cGroup_4.eContents().get(3);
		private final Assignment cStateAssignment_4_3_0 = (Assignment)cGroup_4_3.eContents().get(0);
		private final RuleCall cStateStatementParserRuleCall_4_3_0_0 = (RuleCall)cStateAssignment_4_3_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_4_3_1 = (Keyword)cGroup_4_3.eContents().get(1);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cElseKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Group cGroup_5_1 = (Group)cGroup_5.eContents().get(1);
		private final Assignment cStateAssignment_5_1_0 = (Assignment)cGroup_5_1.eContents().get(0);
		private final RuleCall cStateStatementParserRuleCall_5_1_0_0 = (RuleCall)cStateAssignment_5_1_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_5_1_1 = (Keyword)cGroup_5_1.eContents().get(1);
		private final Keyword cEndKeyword_6 = (Keyword)cGroup.eContents().get(6);
		private final Keyword cIfKeyword_7 = (Keyword)cGroup.eContents().get(7);
		
		//if_statement:
		//	"if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
		//	";")*)* ("else" (state+=statement ";")*)? "end" "if";
		public ParserRule getRule() { return rule; }

		//"if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
		//";")*)* ("else" (state+=statement ";")*)? "end" "if"
		public Group getGroup() { return cGroup; }

		//"if"
		public Keyword getIfKeyword_0() { return cIfKeyword_0; }

		//exprtrue=expression
		public Assignment getExprtrueAssignment_1() { return cExprtrueAssignment_1; }

		//expression
		public RuleCall getExprtrueExpressionParserRuleCall_1_0() { return cExprtrueExpressionParserRuleCall_1_0; }

		//"then"
		public Keyword getThenKeyword_2() { return cThenKeyword_2; }

		//(state+=statement ";")*
		public Group getGroup_3() { return cGroup_3; }

		//state+=statement
		public Assignment getStateAssignment_3_0() { return cStateAssignment_3_0; }

		//statement
		public RuleCall getStateStatementParserRuleCall_3_0_0() { return cStateStatementParserRuleCall_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//("elseif" exprStilltrue+=expression "then" (state+=statement ";")*)*
		public Group getGroup_4() { return cGroup_4; }

		//"elseif"
		public Keyword getElseifKeyword_4_0() { return cElseifKeyword_4_0; }

		//exprStilltrue+=expression
		public Assignment getExprStilltrueAssignment_4_1() { return cExprStilltrueAssignment_4_1; }

		//expression
		public RuleCall getExprStilltrueExpressionParserRuleCall_4_1_0() { return cExprStilltrueExpressionParserRuleCall_4_1_0; }

		//"then"
		public Keyword getThenKeyword_4_2() { return cThenKeyword_4_2; }

		//(state+=statement ";")*
		public Group getGroup_4_3() { return cGroup_4_3; }

		//state+=statement
		public Assignment getStateAssignment_4_3_0() { return cStateAssignment_4_3_0; }

		//statement
		public RuleCall getStateStatementParserRuleCall_4_3_0_0() { return cStateStatementParserRuleCall_4_3_0_0; }

		//";"
		public Keyword getSemicolonKeyword_4_3_1() { return cSemicolonKeyword_4_3_1; }

		//("else" (state+=statement ";")*)?
		public Group getGroup_5() { return cGroup_5; }

		//"else"
		public Keyword getElseKeyword_5_0() { return cElseKeyword_5_0; }

		//(state+=statement ";")*
		public Group getGroup_5_1() { return cGroup_5_1; }

		//state+=statement
		public Assignment getStateAssignment_5_1_0() { return cStateAssignment_5_1_0; }

		//statement
		public RuleCall getStateStatementParserRuleCall_5_1_0_0() { return cStateStatementParserRuleCall_5_1_0_0; }

		//";"
		public Keyword getSemicolonKeyword_5_1_1() { return cSemicolonKeyword_5_1_1; }

		//"end"
		public Keyword getEndKeyword_6() { return cEndKeyword_6; }

		//"if"
		public Keyword getIfKeyword_7() { return cIfKeyword_7; }
	}
	
	
	private Algorithm_clauseElements pAlgorithm_clause;
	private StatementElements pStatement;
	private Algorithm_Macros_GEN_SIGNALElements pAlgorithm_Macros_GEN_SIGNAL;
	private Algorithm_Macros_GEN_INCElements pAlgorithm_Macros_GEN_INC;
	private Algorithm_Macros_GEN_CHANGEElements pAlgorithm_Macros_GEN_CHANGE;
	private State_OutputExprListElements pState_OutputExprList;
	private State_Comp_RefElements pState_Comp_Ref;
	private When_statementElements pWhen_statement;
	private While_statementElements pWhile_statement;
	private For_statementElements pFor_statement;
	private If_statementElements pIf_statement;
	
	private final GrammarProvider grammarProvider;

	private ModeleditorGrammarAccess gaModeleditor;

	@Inject
	public AlgorithmsectionGrammarAccess(GrammarProvider grammarProvider,
		ModeleditorGrammarAccess gaModeleditor) {
		this.grammarProvider = grammarProvider;
		this.gaModeleditor = gaModeleditor;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public ModeleditorGrammarAccess getModeleditorGrammarAccess() {
		return gaModeleditor;
	}

	
	////////////////////////////////////////////Algorithm//////////////////////
	//algorithm_clause:
	//	{algorithm_clause} (Algorithm+=statement ";")*;
	public Algorithm_clauseElements getAlgorithm_clauseAccess() {
		return (pAlgorithm_clause != null) ? pAlgorithm_clause : (pAlgorithm_clause = new Algorithm_clauseElements());
	}
	
	public ParserRule getAlgorithm_clauseRule() {
		return getAlgorithm_clauseAccess().getRule();
	}

	//statement:
	//	{statement} (If_statement=if_statement | State_OutputExprList=state_OutputExprList |
	//	Algo_M_G=Algorithm_Macros_GEN_SIGNAL | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
	//	When_statement=when_statement | While_statement=while_statement | Algo_G_I=Algorithm_Macros_GEN_INC |
	//	Algo_G_C=Algorithm_Macros_GEN_CHANGE | "break") comment;
	public StatementElements getStatementAccess() {
		return (pStatement != null) ? pStatement : (pStatement = new StatementElements());
	}
	
	public ParserRule getStatementRule() {
		return getStatementAccess().getRule();
	}

	/////////////////////////////////////Macros//////////////////////////////////
	//Algorithm_Macros_GEN_SIGNAL:
	//	"GEN_SIGNAL" "(" Component_reference=component_reference ")";
	public Algorithm_Macros_GEN_SIGNALElements getAlgorithm_Macros_GEN_SIGNALAccess() {
		return (pAlgorithm_Macros_GEN_SIGNAL != null) ? pAlgorithm_Macros_GEN_SIGNAL : (pAlgorithm_Macros_GEN_SIGNAL = new Algorithm_Macros_GEN_SIGNALElements());
	}
	
	public ParserRule getAlgorithm_Macros_GEN_SIGNALRule() {
		return getAlgorithm_Macros_GEN_SIGNALAccess().getRule();
	}

	//Algorithm_Macros_GEN_INC:
	//	"GEN_INC" "(" Component_reference=component_reference ")";
	public Algorithm_Macros_GEN_INCElements getAlgorithm_Macros_GEN_INCAccess() {
		return (pAlgorithm_Macros_GEN_INC != null) ? pAlgorithm_Macros_GEN_INC : (pAlgorithm_Macros_GEN_INC = new Algorithm_Macros_GEN_INCElements());
	}
	
	public ParserRule getAlgorithm_Macros_GEN_INCRule() {
		return getAlgorithm_Macros_GEN_INCAccess().getRule();
	}

	//Algorithm_Macros_GEN_CHANGE:
	//	"GEN_CHANGE" "(" Component_reference=component_reference ")";
	public Algorithm_Macros_GEN_CHANGEElements getAlgorithm_Macros_GEN_CHANGEAccess() {
		return (pAlgorithm_Macros_GEN_CHANGE != null) ? pAlgorithm_Macros_GEN_CHANGE : (pAlgorithm_Macros_GEN_CHANGE = new Algorithm_Macros_GEN_CHANGEElements());
	}
	
	public ParserRule getAlgorithm_Macros_GEN_CHANGERule() {
		return getAlgorithm_Macros_GEN_CHANGEAccess().getRule();
	}

	/////////////////////////////////////Statements//////////////////////////////
	//state_OutputExprList:
	//	"(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
	//	fun_call_args=function_call_args;
	public State_OutputExprListElements getState_OutputExprListAccess() {
		return (pState_OutputExprList != null) ? pState_OutputExprList : (pState_OutputExprList = new State_OutputExprListElements());
	}
	
	public ParserRule getState_OutputExprListRule() {
		return getState_OutputExprListAccess().getRule();
	}

	//state_Comp_Ref:
	//	component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args);
	public State_Comp_RefElements getState_Comp_RefAccess() {
		return (pState_Comp_Ref != null) ? pState_Comp_Ref : (pState_Comp_Ref = new State_Comp_RefElements());
	}
	
	public ParserRule getState_Comp_RefRule() {
		return getState_Comp_RefAccess().getRule();
	}

	//when_statement:
	//	"when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
	//	(Then_statement+=statement ";")*)* "end" "when";
	public When_statementElements getWhen_statementAccess() {
		return (pWhen_statement != null) ? pWhen_statement : (pWhen_statement = new When_statementElements());
	}
	
	public ParserRule getWhen_statementRule() {
		return getWhen_statementAccess().getRule();
	}

	//while_statement:
	//	"while" while_expr=expression "loop" (While_stat+=statement ";")* "end" "while";
	public While_statementElements getWhile_statementAccess() {
		return (pWhile_statement != null) ? pWhile_statement : (pWhile_statement = new While_statementElements());
	}
	
	public ParserRule getWhile_statementRule() {
		return getWhile_statementAccess().getRule();
	}

	//for_statement:
	//	"for" for_loop=for_indices "loop" (For_algo+=statement ";")* "end" "for";
	public For_statementElements getFor_statementAccess() {
		return (pFor_statement != null) ? pFor_statement : (pFor_statement = new For_statementElements());
	}
	
	public ParserRule getFor_statementRule() {
		return getFor_statementAccess().getRule();
	}

	//if_statement:
	//	"if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
	//	";")*)* ("else" (state+=statement ";")*)? "end" "if";
	public If_statementElements getIf_statementAccess() {
		return (pIf_statement != null) ? pIf_statement : (pIf_statement = new If_statementElements());
	}
	
	public ParserRule getIf_statementRule() {
		return getIf_statementAccess().getRule();
	}

	//////////////////////////////////////////Expressions////////////////////////
	//expression:
	//	Expr=simple_expression | conditional_expr;
	public ModeleditorGrammarAccess.ExpressionElements getExpressionAccess() {
		return gaModeleditor.getExpressionAccess();
	}
	
	public ParserRule getExpressionRule() {
		return getExpressionAccess().getRule();
	}

	//simple_expression:
	//	Log_Exp=logical_expression (":" S_Logical_expression=logical_expression (":"
	//	L_Logical_expression=logical_expression)?)?;
	public ModeleditorGrammarAccess.Simple_expressionElements getSimple_expressionAccess() {
		return gaModeleditor.getSimple_expressionAccess();
	}
	
	public ParserRule getSimple_expressionRule() {
		return getSimple_expressionAccess().getRule();
	}

	//conditional_expr:
	//	"if" ifexpr=expression "then" thenexpr=expression ("elseif" elseifexpr+=expression "then" trueexpr+=expression)*
	//	("else" falseexpr=expression);
	public ModeleditorGrammarAccess.Conditional_exprElements getConditional_exprAccess() {
		return gaModeleditor.getConditional_exprAccess();
	}
	
	public ParserRule getConditional_exprRule() {
		return getConditional_exprAccess().getRule();
	}

	//logical_expression:
	//	logical_term ("or" Logical_term+=logical_term)*;
	public ModeleditorGrammarAccess.Logical_expressionElements getLogical_expressionAccess() {
		return gaModeleditor.getLogical_expressionAccess();
	}
	
	public ParserRule getLogical_expressionRule() {
		return getLogical_expressionAccess().getRule();
	}

	//logical_term:
	//	logical_factor ("and" Logical_factor+=logical_factor)*;
	public ModeleditorGrammarAccess.Logical_termElements getLogical_termAccess() {
		return gaModeleditor.getLogical_termAccess();
	}
	
	public ParserRule getLogical_termRule() {
		return getLogical_termAccess().getRule();
	}

	//logical_factor:
	//	"not"? Relation=relation;
	public ModeleditorGrammarAccess.Logical_factorElements getLogical_factorAccess() {
		return gaModeleditor.getLogical_factorAccess();
	}
	
	public ParserRule getLogical_factorRule() {
		return getLogical_factorAccess().getRule();
	}

	//relation:
	//	arithmetic_expression (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal |
	//	rel_op_assignment | rel_op_Oper) Arithmetic_expression=arithmetic_expression)?;
	public ModeleditorGrammarAccess.RelationElements getRelationAccess() {
		return gaModeleditor.getRelationAccess();
	}
	
	public ParserRule getRelationRule() {
		return getRelationAccess().getRule();
	}

	//arithmetic_expression:
	//	opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)? Term=term (Oper1+=(add_op_plus | add_op_minus |
	//	add_op_dotplus | add_op_dotminus) Term1+=term)*;
	public ModeleditorGrammarAccess.Arithmetic_expressionElements getArithmetic_expressionAccess() {
		return gaModeleditor.getArithmetic_expressionAccess();
	}
	
	public ParserRule getArithmetic_expressionRule() {
		return getArithmetic_expressionAccess().getRule();
	}

	//term:
	//	factor (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*;
	public ModeleditorGrammarAccess.TermElements getTermAccess() {
		return gaModeleditor.getTermAccess();
	}
	
	public ParserRule getTermRule() {
		return getTermAccess().getRule();
	}

	//factor:
	//	primary (("^" | ".^") Primary=primary)?;
	public ModeleditorGrammarAccess.FactorElements getFactorAccess() {
		return gaModeleditor.getFactorAccess();
	}
	
	public ParserRule getFactorRule() {
		return getFactorAccess().getRule();
	}

	////////////////////////////////////////////////////Primary///////////////////////////////
	//primary:
	//	num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | name_Function | initial_ref | ExprDer |
	//	Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
	//	Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
	//	End="end";
	public ModeleditorGrammarAccess.PrimaryElements getPrimaryAccess() {
		return gaModeleditor.getPrimaryAccess();
	}
	
	public ParserRule getPrimaryRule() {
		return getPrimaryAccess().getRule();
	}

	//name_Function:
	//	name Function_call_args=function_call_args;
	public ModeleditorGrammarAccess.Name_FunctionElements getName_FunctionAccess() {
		return gaModeleditor.getName_FunctionAccess();
	}
	
	public ParserRule getName_FunctionRule() {
		return getName_FunctionAccess().getRule();
	}

	//initial_ref:
	//	"initial" function_call_args;
	public ModeleditorGrammarAccess.Initial_refElements getInitial_refAccess() {
		return gaModeleditor.getInitial_refAccess();
	}
	
	public ParserRule getInitial_refRule() {
		return getInitial_refAccess().getRule();
	}

	//ExprDer:
	//	"der" functionArgs=function_call_args;
	public ModeleditorGrammarAccess.ExprDerElements getExprDerAccess() {
		return gaModeleditor.getExprDerAccess();
	}
	
	public ParserRule getExprDerRule() {
		return getExprDerAccess().getRule();
	}

	//function_call_args:
	//	{function_call_args} "(" f_arg=function_arguments? ")";
	public ModeleditorGrammarAccess.Function_call_argsElements getFunction_call_argsAccess() {
		return gaModeleditor.getFunction_call_argsAccess();
	}
	
	public ParserRule getFunction_call_argsRule() {
		return getFunction_call_argsAccess().getRule();
	}

	//expression_list:
	//	expr=expression ("," Expre+=expression)*;
	public ModeleditorGrammarAccess.Expression_listElements getExpression_listAccess() {
		return gaModeleditor.getExpression_listAccess();
	}
	
	public ParserRule getExpression_listRule() {
		return getExpression_listAccess().getRule();
	}

	/////////////////////////////////Component Reference////////////////////////
	//name:
	//	"."? name_ID=IDENT ("." nam_ID+=IDENT)*;
	public ModeleditorGrammarAccess.NameElements getNameAccess() {
		return gaModeleditor.getNameAccess();
	}
	
	public ParserRule getNameRule() {
		return getNameAccess().getRule();
	}

	//component_reference:
	//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
	public ModeleditorGrammarAccess.Component_referenceElements getComponent_referenceAccess() {
		return gaModeleditor.getComponent_referenceAccess();
	}
	
	public ParserRule getComponent_referenceRule() {
		return getComponent_referenceAccess().getRule();
	}

	//output_expression_list:
	//	{output_expression_list} epr=expression? ("," Expr+=expression?)*;
	public ModeleditorGrammarAccess.Output_expression_listElements getOutput_expression_listAccess() {
		return gaModeleditor.getOutput_expression_listAccess();
	}
	
	public ParserRule getOutput_expression_listRule() {
		return getOutput_expression_listAccess().getRule();
	}

	//array_subscripts:
	//	"[" Sub=subscript ("," Subscript+=subscript)* "]";
	public ModeleditorGrammarAccess.Array_subscriptsElements getArray_subscriptsAccess() {
		return gaModeleditor.getArray_subscriptsAccess();
	}
	
	public ParserRule getArray_subscriptsRule() {
		return getArray_subscriptsAccess().getRule();
	}

	//subscript:
	//	{subscript} ":" | expr=expression;
	public ModeleditorGrammarAccess.SubscriptElements getSubscriptAccess() {
		return gaModeleditor.getSubscriptAccess();
	}
	
	public ParserRule getSubscriptRule() {
		return getSubscriptAccess().getRule();
	}

	//function_arguments:
	//	{function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)? |
	//	name_arg=named_arguments;
	public ModeleditorGrammarAccess.Function_argumentsElements getFunction_argumentsAccess() {
		return gaModeleditor.getFunction_argumentsAccess();
	}
	
	public ParserRule getFunction_argumentsRule() {
		return getFunction_argumentsAccess().getRule();
	}

	//Fun_Arguments_exp:
	//	"," Args=function_arguments;
	public ModeleditorGrammarAccess.Fun_Arguments_expElements getFun_Arguments_expAccess() {
		return gaModeleditor.getFun_Arguments_expAccess();
	}
	
	public ParserRule getFun_Arguments_expRule() {
		return getFun_Arguments_expAccess().getRule();
	}

	//Fun_Arguments_for:
	//	"for" For_indices=for_indices;
	public ModeleditorGrammarAccess.Fun_Arguments_forElements getFun_Arguments_forAccess() {
		return gaModeleditor.getFun_Arguments_forAccess();
	}
	
	public ParserRule getFun_Arguments_forRule() {
		return getFun_Arguments_forAccess().getRule();
	}

	//named_arguments:
	//	named_argument ("," Named_arguments=named_arguments)?;
	public ModeleditorGrammarAccess.Named_argumentsElements getNamed_argumentsAccess() {
		return gaModeleditor.getNamed_argumentsAccess();
	}
	
	public ParserRule getNamed_argumentsRule() {
		return getNamed_argumentsAccess().getRule();
	}

	//named_argument:
	//	arg=IDENT "=" expr=expression;
	public ModeleditorGrammarAccess.Named_argumentElements getNamed_argumentAccess() {
		return gaModeleditor.getNamed_argumentAccess();
	}
	
	public ParserRule getNamed_argumentRule() {
		return getNamed_argumentAccess().getRule();
	}

	//for_indices:
	//	for_index ("," For_index+=for_index)*;
	public ModeleditorGrammarAccess.For_indicesElements getFor_indicesAccess() {
		return gaModeleditor.getFor_indicesAccess();
	}
	
	public ParserRule getFor_indicesRule() {
		return getFor_indicesAccess().getRule();
	}

	//for_index:
	//	index=IDENT ("in" expr=expression)?;
	public ModeleditorGrammarAccess.For_indexElements getFor_indexAccess() {
		return gaModeleditor.getFor_indexAccess();
	}
	
	public ParserRule getFor_indexRule() {
		return getFor_indexAccess().getRule();
	}

	/////////////////////////////////Operators/////////////////////////////////////////
	//mul_op_mul returns ecore::EString:
	//	"*";
	public ModeleditorGrammarAccess.Mul_op_mulElements getMul_op_mulAccess() {
		return gaModeleditor.getMul_op_mulAccess();
	}
	
	public ParserRule getMul_op_mulRule() {
		return getMul_op_mulAccess().getRule();
	}

	//mul_op_div returns ecore::EString:
	//	"/";
	public ModeleditorGrammarAccess.Mul_op_divElements getMul_op_divAccess() {
		return gaModeleditor.getMul_op_divAccess();
	}
	
	public ParserRule getMul_op_divRule() {
		return getMul_op_divAccess().getRule();
	}

	//mul_op_dotmul returns ecore::EString:
	//	".*";
	public ModeleditorGrammarAccess.Mul_op_dotmulElements getMul_op_dotmulAccess() {
		return gaModeleditor.getMul_op_dotmulAccess();
	}
	
	public ParserRule getMul_op_dotmulRule() {
		return getMul_op_dotmulAccess().getRule();
	}

	//mul_op_dotdiv returns ecore::EString:
	//	"./";
	public ModeleditorGrammarAccess.Mul_op_dotdivElements getMul_op_dotdivAccess() {
		return gaModeleditor.getMul_op_dotdivAccess();
	}
	
	public ParserRule getMul_op_dotdivRule() {
		return getMul_op_dotdivAccess().getRule();
	}

	//add_op_plus returns ecore::EString:
	//	"+";
	public ModeleditorGrammarAccess.Add_op_plusElements getAdd_op_plusAccess() {
		return gaModeleditor.getAdd_op_plusAccess();
	}
	
	public ParserRule getAdd_op_plusRule() {
		return getAdd_op_plusAccess().getRule();
	}

	//add_op_minus returns ecore::EString:
	//	"-";
	public ModeleditorGrammarAccess.Add_op_minusElements getAdd_op_minusAccess() {
		return gaModeleditor.getAdd_op_minusAccess();
	}
	
	public ParserRule getAdd_op_minusRule() {
		return getAdd_op_minusAccess().getRule();
	}

	//add_op_dotplus returns ecore::EString:
	//	".+";
	public ModeleditorGrammarAccess.Add_op_dotplusElements getAdd_op_dotplusAccess() {
		return gaModeleditor.getAdd_op_dotplusAccess();
	}
	
	public ParserRule getAdd_op_dotplusRule() {
		return getAdd_op_dotplusAccess().getRule();
	}

	//add_op_dotminus returns ecore::EString:
	//	".-";
	public ModeleditorGrammarAccess.Add_op_dotminusElements getAdd_op_dotminusAccess() {
		return gaModeleditor.getAdd_op_dotminusAccess();
	}
	
	public ParserRule getAdd_op_dotminusRule() {
		return getAdd_op_dotminusAccess().getRule();
	}

	//rel_op_Less_then returns ecore::EString:
	//	"<";
	public ModeleditorGrammarAccess.Rel_op_Less_thenElements getRel_op_Less_thenAccess() {
		return gaModeleditor.getRel_op_Less_thenAccess();
	}
	
	public ParserRule getRel_op_Less_thenRule() {
		return getRel_op_Less_thenAccess().getRule();
	}

	//rel_op_Less_equal returns ecore::EString:
	//	"<=";
	public ModeleditorGrammarAccess.Rel_op_Less_equalElements getRel_op_Less_equalAccess() {
		return gaModeleditor.getRel_op_Less_equalAccess();
	}
	
	public ParserRule getRel_op_Less_equalRule() {
		return getRel_op_Less_equalAccess().getRule();
	}

	//rel_op_greater_then returns ecore::EString:
	//	">";
	public ModeleditorGrammarAccess.Rel_op_greater_thenElements getRel_op_greater_thenAccess() {
		return gaModeleditor.getRel_op_greater_thenAccess();
	}
	
	public ParserRule getRel_op_greater_thenRule() {
		return getRel_op_greater_thenAccess().getRule();
	}

	//rel_op_greater_equal returns ecore::EString:
	//	">=";
	public ModeleditorGrammarAccess.Rel_op_greater_equalElements getRel_op_greater_equalAccess() {
		return gaModeleditor.getRel_op_greater_equalAccess();
	}
	
	public ParserRule getRel_op_greater_equalRule() {
		return getRel_op_greater_equalAccess().getRule();
	}

	//rel_op_assignment returns ecore::EString:
	//	"==";
	public ModeleditorGrammarAccess.Rel_op_assignmentElements getRel_op_assignmentAccess() {
		return gaModeleditor.getRel_op_assignmentAccess();
	}
	
	public ParserRule getRel_op_assignmentRule() {
		return getRel_op_assignmentAccess().getRule();
	}

	//rel_op_Oper returns ecore::EString:
	//	"<>";
	public ModeleditorGrammarAccess.Rel_op_OperElements getRel_op_OperAccess() {
		return gaModeleditor.getRel_op_OperAccess();
	}
	
	public ParserRule getRel_op_OperRule() {
		return getRel_op_OperAccess().getRule();
	}

	/////////////////////////////////Comments/////////////////////////////////////
	//comment returns ecore::EString:
	//	string_comment;
	public ModeleditorGrammarAccess.CommentElements getCommentAccess() {
		return gaModeleditor.getCommentAccess();
	}
	
	public ParserRule getCommentRule() {
		return getCommentAccess().getRule();
	}

	//string_comment returns ecore::EString:
	//	(STRING ("+" STRING)*)?;
	public ModeleditorGrammarAccess.String_commentElements getString_commentAccess() {
		return gaModeleditor.getString_commentAccess();
	}
	
	public ParserRule getString_commentRule() {
		return getString_commentAccess().getRule();
	}

	/////////////////////////////////////////// Terminals////////////////////////
	//terminal UNSIGNED_NUMBER:
	//	"0".."9"+ "." "0".."9"* (("E" | "e") ("+" | "-")? "0".."9"+)? | "0".."9"+ ("E" | "e") ("+" | "-")? "0".."9"+;
	public TerminalRule getUNSIGNED_NUMBERRule() {
		return gaModeleditor.getUNSIGNED_NUMBERRule();
	} 

	//terminal BOOL_VAL:
	//	"true" | "false";
	public TerminalRule getBOOL_VALRule() {
		return gaModeleditor.getBOOL_VALRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaModeleditor.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"";
	public TerminalRule getSTRINGRule() {
		return gaModeleditor.getSTRINGRule();
	} 

	//terminal IDENT:
	//	("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9" | ".")*;
	public TerminalRule getIDENTRule() {
		return gaModeleditor.getIDENTRule();
	} 

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaModeleditor.getIDRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaModeleditor.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaModeleditor.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaModeleditor.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaModeleditor.getANY_OTHERRule();
	} 
}
