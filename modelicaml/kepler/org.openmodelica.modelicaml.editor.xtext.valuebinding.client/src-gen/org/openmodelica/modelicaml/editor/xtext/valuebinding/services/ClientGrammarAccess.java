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

package org.openmodelica.modelicaml.editor.xtext.valuebinding.services;

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
public class ClientGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class ClientOperationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ClientOperation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cLeft_handAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cLeft_handLeft_hand_component_referenceParserRuleCall_0_0 = (RuleCall)cLeft_handAssignment_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cExprAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cExprExpressionParserRuleCall_2_0 = (RuleCall)cExprAssignment_2.eContents().get(0);
		private final Keyword cSemicolonKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//// ModelicaML specific. All rules are made optional in order to not create validation error markers if nothing was entered by the user.
		////a simple component equality assignment modification rule without "(" and ")". Added for better code completion support (see the left_hand_component_reference that returns only the subcomponents of the component being modified)
		//ClientOperation:
		//	(left_hand=left_hand_component_reference "=" Expr=expression ";")*;
		public ParserRule getRule() { return rule; }

		//(left_hand=left_hand_component_reference "=" Expr=expression ";")*
		public Group getGroup() { return cGroup; }

		//left_hand=left_hand_component_reference
		public Assignment getLeft_handAssignment_0() { return cLeft_handAssignment_0; }

		//left_hand_component_reference
		public RuleCall getLeft_handLeft_hand_component_referenceParserRuleCall_0_0() { return cLeft_handLeft_hand_component_referenceParserRuleCall_0_0; }

		//"="
		public Keyword getEqualsSignKeyword_1() { return cEqualsSignKeyword_1; }

		//Expr=expression
		public Assignment getExprAssignment_2() { return cExprAssignment_2; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_2_0() { return cExprExpressionParserRuleCall_2_0; }

		//";"
		public Keyword getSemicolonKeyword_3() { return cSemicolonKeyword_3; }
	}

	public class Left_hand_component_referenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "left_hand_component_reference");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFullStopKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cRefAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cRefIDENTTerminalRuleCall_1_0 = (RuleCall)cRefAssignment_1.eContents().get(0);
		private final Assignment cSubscripts1Assignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cSubscripts1Array_subscriptsParserRuleCall_2_0 = (RuleCall)cSubscripts1Assignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cFullStopKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cRef1Assignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cRef1IDENTTerminalRuleCall_3_1_0 = (RuleCall)cRef1Assignment_3_1.eContents().get(0);
		private final Assignment cSubscriptsAssignment_3_2 = (Assignment)cGroup_3.eContents().get(2);
		private final RuleCall cSubscriptsArray_subscriptsParserRuleCall_3_2_0 = (RuleCall)cSubscriptsAssignment_3_2.eContents().get(0);
		
		//// ModelicaML specific. 
		//left_hand_component_reference:
		//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
		public ParserRule getRule() { return rule; }

		//"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup() { return cGroup; }

		//"."?
		public Keyword getFullStopKeyword_0() { return cFullStopKeyword_0; }

		//ref=IDENT
		public Assignment getRefAssignment_1() { return cRefAssignment_1; }

		//IDENT
		public RuleCall getRefIDENTTerminalRuleCall_1_0() { return cRefIDENTTerminalRuleCall_1_0; }

		//subscripts1=array_subscripts?
		public Assignment getSubscripts1Assignment_2() { return cSubscripts1Assignment_2; }

		//array_subscripts
		public RuleCall getSubscripts1Array_subscriptsParserRuleCall_2_0() { return cSubscripts1Array_subscriptsParserRuleCall_2_0; }

		//("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup_3() { return cGroup_3; }

		//"."
		public Keyword getFullStopKeyword_3_0() { return cFullStopKeyword_3_0; }

		//ref1+=IDENT
		public Assignment getRef1Assignment_3_1() { return cRef1Assignment_3_1; }

		//IDENT
		public RuleCall getRef1IDENTTerminalRuleCall_3_1_0() { return cRef1IDENTTerminalRuleCall_3_1_0; }

		//subscripts+=array_subscripts?
		public Assignment getSubscriptsAssignment_3_2() { return cSubscriptsAssignment_3_2; }

		//array_subscripts
		public RuleCall getSubscriptsArray_subscriptsParserRuleCall_3_2_0() { return cSubscriptsArray_subscriptsParserRuleCall_3_2_0; }
	}

	public class PrimaryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "primary");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Assignment cNumAssignment_0 = (Assignment)cAlternatives.eContents().get(0);
		private final RuleCall cNumUNSIGNED_NUMBERTerminalRuleCall_0_0 = (RuleCall)cNumAssignment_0.eContents().get(0);
		private final Assignment cIntAssignment_1 = (Assignment)cAlternatives.eContents().get(1);
		private final RuleCall cIntINTTerminalRuleCall_1_0 = (RuleCall)cIntAssignment_1.eContents().get(0);
		private final Assignment cStrAssignment_2 = (Assignment)cAlternatives.eContents().get(2);
		private final RuleCall cStrSTRINGTerminalRuleCall_2_0 = (RuleCall)cStrAssignment_2.eContents().get(0);
		private final Assignment cBoolAssignment_3 = (Assignment)cAlternatives.eContents().get(3);
		private final RuleCall cBoolBOOL_VALTerminalRuleCall_3_0 = (RuleCall)cBoolAssignment_3.eContents().get(0);
		private final Assignment cName_FunctionAssignment_4 = (Assignment)cAlternatives.eContents().get(4);
		private final RuleCall cName_FunctionName_FunctionParserRuleCall_4_0 = (RuleCall)cName_FunctionAssignment_4.eContents().get(0);
		private final Assignment cInitial_refAssignment_5 = (Assignment)cAlternatives.eContents().get(5);
		private final RuleCall cInitial_refInitial_refParserRuleCall_5_0 = (RuleCall)cInitial_refAssignment_5.eContents().get(0);
		private final Assignment cExprAssignment_6 = (Assignment)cAlternatives.eContents().get(6);
		private final RuleCall cExprExprDerParserRuleCall_6_0 = (RuleCall)cExprAssignment_6.eContents().get(0);
		private final Assignment cBindingAssignment_7 = (Assignment)cAlternatives.eContents().get(7);
		private final RuleCall cBindingGetBindingFunctionParserRuleCall_7_0 = (RuleCall)cBindingAssignment_7.eContents().get(0);
		private final Assignment cComponent_referenceAssignment_8 = (Assignment)cAlternatives.eContents().get(8);
		private final RuleCall cComponent_referenceComponent_referenceParserRuleCall_8_0 = (RuleCall)cComponent_referenceAssignment_8.eContents().get(0);
		private final Group cGroup_9 = (Group)cAlternatives.eContents().get(9);
		private final Keyword cLeftParenthesisKeyword_9_0 = (Keyword)cGroup_9.eContents().get(0);
		private final Assignment cOutput_expr_listAssignment_9_1 = (Assignment)cGroup_9.eContents().get(1);
		private final RuleCall cOutput_expr_listOutput_expression_listParserRuleCall_9_1_0 = (RuleCall)cOutput_expr_listAssignment_9_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_9_2 = (Keyword)cGroup_9.eContents().get(2);
		private final Group cGroup_10 = (Group)cAlternatives.eContents().get(10);
		private final Keyword cLeftSquareBracketKeyword_10_0 = (Keyword)cGroup_10.eContents().get(0);
		private final Assignment cExpre_listAssignment_10_1 = (Assignment)cGroup_10.eContents().get(1);
		private final RuleCall cExpre_listExpression_listParserRuleCall_10_1_0 = (RuleCall)cExpre_listAssignment_10_1.eContents().get(0);
		private final Group cGroup_10_2 = (Group)cGroup_10.eContents().get(2);
		private final Keyword cSemicolonKeyword_10_2_0 = (Keyword)cGroup_10_2.eContents().get(0);
		private final Assignment cExpression_listAssignment_10_2_1 = (Assignment)cGroup_10_2.eContents().get(1);
		private final RuleCall cExpression_listExpression_listParserRuleCall_10_2_1_0 = (RuleCall)cExpression_listAssignment_10_2_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_10_3 = (Keyword)cGroup_10.eContents().get(3);
		private final Group cGroup_11 = (Group)cAlternatives.eContents().get(11);
		private final Keyword cLeftCurlyBracketKeyword_11_0 = (Keyword)cGroup_11.eContents().get(0);
		private final Assignment cF_argumentsAssignment_11_1 = (Assignment)cGroup_11.eContents().get(1);
		private final RuleCall cF_argumentsFunction_argumentsParserRuleCall_11_1_0 = (RuleCall)cF_argumentsAssignment_11_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_11_2 = (Keyword)cGroup_11.eContents().get(2);
		private final Assignment cEndAssignment_12 = (Assignment)cAlternatives.eContents().get(12);
		private final Keyword cEndEndKeyword_12_0 = (Keyword)cEndAssignment_12.eContents().get(0);
		
		//primary:
		//	num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | Name_Function=name_Function | Initial_ref=initial_ref |
		//	Expr=ExprDer | binding=GetBindingFunction | Component_reference=component_reference | "("
		//	output_expr_list=output_expression_list ")" | "[" Expre_list=expression_list (";" Expression_list+=expression_list)*
		//	"]" | "{" f_arguments=function_arguments "}" | End="end";
		public ParserRule getRule() { return rule; }

		//num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | Name_Function=name_Function | Initial_ref=initial_ref |
		//Expr=ExprDer | binding=GetBindingFunction | Component_reference=component_reference | "("
		//output_expr_list=output_expression_list ")" | "[" Expre_list=expression_list (";" Expression_list+=expression_list)*
		//"]" | "{" f_arguments=function_arguments "}" | End="end"
		public Alternatives getAlternatives() { return cAlternatives; }

		//num=UNSIGNED_NUMBER
		public Assignment getNumAssignment_0() { return cNumAssignment_0; }

		//UNSIGNED_NUMBER
		public RuleCall getNumUNSIGNED_NUMBERTerminalRuleCall_0_0() { return cNumUNSIGNED_NUMBERTerminalRuleCall_0_0; }

		//int=INT
		public Assignment getIntAssignment_1() { return cIntAssignment_1; }

		//INT
		public RuleCall getIntINTTerminalRuleCall_1_0() { return cIntINTTerminalRuleCall_1_0; }

		//str=STRING
		public Assignment getStrAssignment_2() { return cStrAssignment_2; }

		//STRING
		public RuleCall getStrSTRINGTerminalRuleCall_2_0() { return cStrSTRINGTerminalRuleCall_2_0; }

		//Bool=BOOL_VAL
		public Assignment getBoolAssignment_3() { return cBoolAssignment_3; }

		//BOOL_VAL
		public RuleCall getBoolBOOL_VALTerminalRuleCall_3_0() { return cBoolBOOL_VALTerminalRuleCall_3_0; }

		//Name_Function=name_Function
		public Assignment getName_FunctionAssignment_4() { return cName_FunctionAssignment_4; }

		//name_Function
		public RuleCall getName_FunctionName_FunctionParserRuleCall_4_0() { return cName_FunctionName_FunctionParserRuleCall_4_0; }

		//Initial_ref=initial_ref
		public Assignment getInitial_refAssignment_5() { return cInitial_refAssignment_5; }

		//initial_ref
		public RuleCall getInitial_refInitial_refParserRuleCall_5_0() { return cInitial_refInitial_refParserRuleCall_5_0; }

		//Expr=ExprDer
		public Assignment getExprAssignment_6() { return cExprAssignment_6; }

		//ExprDer
		public RuleCall getExprExprDerParserRuleCall_6_0() { return cExprExprDerParserRuleCall_6_0; }

		//binding=GetBindingFunction
		public Assignment getBindingAssignment_7() { return cBindingAssignment_7; }

		//GetBindingFunction
		public RuleCall getBindingGetBindingFunctionParserRuleCall_7_0() { return cBindingGetBindingFunctionParserRuleCall_7_0; }

		//Component_reference=component_reference
		public Assignment getComponent_referenceAssignment_8() { return cComponent_referenceAssignment_8; }

		//component_reference
		public RuleCall getComponent_referenceComponent_referenceParserRuleCall_8_0() { return cComponent_referenceComponent_referenceParserRuleCall_8_0; }

		//"(" output_expr_list=output_expression_list ")"
		public Group getGroup_9() { return cGroup_9; }

		//"("
		public Keyword getLeftParenthesisKeyword_9_0() { return cLeftParenthesisKeyword_9_0; }

		//output_expr_list=output_expression_list
		public Assignment getOutput_expr_listAssignment_9_1() { return cOutput_expr_listAssignment_9_1; }

		//output_expression_list
		public RuleCall getOutput_expr_listOutput_expression_listParserRuleCall_9_1_0() { return cOutput_expr_listOutput_expression_listParserRuleCall_9_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_9_2() { return cRightParenthesisKeyword_9_2; }

		//"[" Expre_list=expression_list (";" Expression_list+=expression_list)* "]"
		public Group getGroup_10() { return cGroup_10; }

		//"["
		public Keyword getLeftSquareBracketKeyword_10_0() { return cLeftSquareBracketKeyword_10_0; }

		//Expre_list=expression_list
		public Assignment getExpre_listAssignment_10_1() { return cExpre_listAssignment_10_1; }

		//expression_list
		public RuleCall getExpre_listExpression_listParserRuleCall_10_1_0() { return cExpre_listExpression_listParserRuleCall_10_1_0; }

		//(";" Expression_list+=expression_list)*
		public Group getGroup_10_2() { return cGroup_10_2; }

		//";"
		public Keyword getSemicolonKeyword_10_2_0() { return cSemicolonKeyword_10_2_0; }

		//Expression_list+=expression_list
		public Assignment getExpression_listAssignment_10_2_1() { return cExpression_listAssignment_10_2_1; }

		//expression_list
		public RuleCall getExpression_listExpression_listParserRuleCall_10_2_1_0() { return cExpression_listExpression_listParserRuleCall_10_2_1_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_10_3() { return cRightSquareBracketKeyword_10_3; }

		//"{" f_arguments=function_arguments "}"
		public Group getGroup_11() { return cGroup_11; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_11_0() { return cLeftCurlyBracketKeyword_11_0; }

		//f_arguments=function_arguments
		public Assignment getF_argumentsAssignment_11_1() { return cF_argumentsAssignment_11_1; }

		//function_arguments
		public RuleCall getF_argumentsFunction_argumentsParserRuleCall_11_1_0() { return cF_argumentsFunction_argumentsParserRuleCall_11_1_0; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_11_2() { return cRightCurlyBracketKeyword_11_2; }

		//End="end"
		public Assignment getEndAssignment_12() { return cEndAssignment_12; }

		//"end"
		public Keyword getEndEndKeyword_12_0() { return cEndEndKeyword_12_0; }
	}

	public class GetBindingFunctionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "GetBindingFunction");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cGetBindingKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cBracketsParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		
		//GetBindingFunction:
		//	"getBinding" Brackets;
		public ParserRule getRule() { return rule; }

		//"getBinding" Brackets
		public Group getGroup() { return cGroup; }

		//"getBinding"
		public Keyword getGetBindingKeyword_0() { return cGetBindingKeyword_0; }

		//Brackets
		public RuleCall getBracketsParserRuleCall_1() { return cBracketsParserRuleCall_1; }
	}

	public class BracketsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Brackets");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftParenthesisKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		
		//Brackets:
		//	"(" ")";
		public ParserRule getRule() { return rule; }

		//"(" ")"
		public Group getGroup() { return cGroup; }

		//"("
		public Keyword getLeftParenthesisKeyword_0() { return cLeftParenthesisKeyword_0; }

		//")"
		public Keyword getRightParenthesisKeyword_1() { return cRightParenthesisKeyword_1; }
	}

	public class Component_referenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_reference");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Assignment cRefAssignment_0_0 = (Assignment)cGroup_0.eContents().get(0);
		private final RuleCall cRefIDENTTerminalRuleCall_0_0_0 = (RuleCall)cRefAssignment_0_0.eContents().get(0);
		private final Assignment cSubscripts1Assignment_0_1 = (Assignment)cGroup_0.eContents().get(1);
		private final RuleCall cSubscripts1Array_subscriptsParserRuleCall_0_1_0 = (RuleCall)cSubscripts1Assignment_0_1.eContents().get(0);
		private final Group cGroup_0_2 = (Group)cGroup_0.eContents().get(2);
		private final Keyword cFullStopKeyword_0_2_0 = (Keyword)cGroup_0_2.eContents().get(0);
		private final Assignment cRef1Assignment_0_2_1 = (Assignment)cGroup_0_2.eContents().get(1);
		private final RuleCall cRef1IDENTTerminalRuleCall_0_2_1_0 = (RuleCall)cRef1Assignment_0_2_1.eContents().get(0);
		private final Assignment cSubscriptsAssignment_0_2_2 = (Assignment)cGroup_0_2.eContents().get(2);
		private final RuleCall cSubscriptsArray_subscriptsParserRuleCall_0_2_2_0 = (RuleCall)cSubscriptsAssignment_0_2_2.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Keyword cFullStopKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cRefAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cRefIDENTTerminalRuleCall_1_1_0 = (RuleCall)cRefAssignment_1_1.eContents().get(0);
		private final Assignment cSubscripts1Assignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cSubscripts1Array_subscriptsParserRuleCall_1_2_0 = (RuleCall)cSubscripts1Assignment_1_2.eContents().get(0);
		private final Group cGroup_1_3 = (Group)cGroup_1.eContents().get(3);
		private final Keyword cFullStopKeyword_1_3_0 = (Keyword)cGroup_1_3.eContents().get(0);
		private final Assignment cRef1Assignment_1_3_1 = (Assignment)cGroup_1_3.eContents().get(1);
		private final RuleCall cRef1IDENTTerminalRuleCall_1_3_1_0 = (RuleCall)cRef1Assignment_1_3_1.eContents().get(0);
		private final Assignment cSubscriptsAssignment_1_3_2 = (Assignment)cGroup_1_3.eContents().get(2);
		private final RuleCall cSubscriptsArray_subscriptsParserRuleCall_1_3_2_0 = (RuleCall)cSubscriptsAssignment_1_3_2.eContents().get(0);
		
		//component_reference:
		//	ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)* | "."? ref=IDENT
		//	subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
		public ParserRule getRule() { return rule; }

		//ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)* | "."? ref=IDENT
		//subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Alternatives getAlternatives() { return cAlternatives; }

		//ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup_0() { return cGroup_0; }

		//ref=IDENT
		public Assignment getRefAssignment_0_0() { return cRefAssignment_0_0; }

		//IDENT
		public RuleCall getRefIDENTTerminalRuleCall_0_0_0() { return cRefIDENTTerminalRuleCall_0_0_0; }

		//subscripts1=array_subscripts?
		public Assignment getSubscripts1Assignment_0_1() { return cSubscripts1Assignment_0_1; }

		//array_subscripts
		public RuleCall getSubscripts1Array_subscriptsParserRuleCall_0_1_0() { return cSubscripts1Array_subscriptsParserRuleCall_0_1_0; }

		//("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup_0_2() { return cGroup_0_2; }

		//"."
		public Keyword getFullStopKeyword_0_2_0() { return cFullStopKeyword_0_2_0; }

		//ref1+=IDENT
		public Assignment getRef1Assignment_0_2_1() { return cRef1Assignment_0_2_1; }

		//IDENT
		public RuleCall getRef1IDENTTerminalRuleCall_0_2_1_0() { return cRef1IDENTTerminalRuleCall_0_2_1_0; }

		//subscripts+=array_subscripts?
		public Assignment getSubscriptsAssignment_0_2_2() { return cSubscriptsAssignment_0_2_2; }

		//array_subscripts
		public RuleCall getSubscriptsArray_subscriptsParserRuleCall_0_2_2_0() { return cSubscriptsArray_subscriptsParserRuleCall_0_2_2_0; }

		//"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup_1() { return cGroup_1; }

		//"."?
		public Keyword getFullStopKeyword_1_0() { return cFullStopKeyword_1_0; }

		//ref=IDENT
		public Assignment getRefAssignment_1_1() { return cRefAssignment_1_1; }

		//IDENT
		public RuleCall getRefIDENTTerminalRuleCall_1_1_0() { return cRefIDENTTerminalRuleCall_1_1_0; }

		//subscripts1=array_subscripts?
		public Assignment getSubscripts1Assignment_1_2() { return cSubscripts1Assignment_1_2; }

		//array_subscripts
		public RuleCall getSubscripts1Array_subscriptsParserRuleCall_1_2_0() { return cSubscripts1Array_subscriptsParserRuleCall_1_2_0; }

		//("." ref1+=IDENT subscripts+=array_subscripts?)*
		public Group getGroup_1_3() { return cGroup_1_3; }

		//"."
		public Keyword getFullStopKeyword_1_3_0() { return cFullStopKeyword_1_3_0; }

		//ref1+=IDENT
		public Assignment getRef1Assignment_1_3_1() { return cRef1Assignment_1_3_1; }

		//IDENT
		public RuleCall getRef1IDENTTerminalRuleCall_1_3_1_0() { return cRef1IDENTTerminalRuleCall_1_3_1_0; }

		//subscripts+=array_subscripts?
		public Assignment getSubscriptsAssignment_1_3_2() { return cSubscriptsAssignment_1_3_2; }

		//array_subscripts
		public RuleCall getSubscriptsArray_subscriptsParserRuleCall_1_3_2_0() { return cSubscriptsArray_subscriptsParserRuleCall_1_3_2_0; }
	}
	
	
	private ClientOperationElements pClientOperation;
	private Left_hand_component_referenceElements pLeft_hand_component_reference;
	private PrimaryElements pPrimary;
	private GetBindingFunctionElements pGetBindingFunction;
	private BracketsElements pBrackets;
	private Component_referenceElements pComponent_reference;
	
	private final GrammarProvider grammarProvider;

	private ModeleditorGrammarAccess gaModeleditor;

	@Inject
	public ClientGrammarAccess(GrammarProvider grammarProvider,
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

	
	//// ModelicaML specific. All rules are made optional in order to not create validation error markers if nothing was entered by the user.
	////a simple component equality assignment modification rule without "(" and ")". Added for better code completion support (see the left_hand_component_reference that returns only the subcomponents of the component being modified)
	//ClientOperation:
	//	(left_hand=left_hand_component_reference "=" Expr=expression ";")*;
	public ClientOperationElements getClientOperationAccess() {
		return (pClientOperation != null) ? pClientOperation : (pClientOperation = new ClientOperationElements());
	}
	
	public ParserRule getClientOperationRule() {
		return getClientOperationAccess().getRule();
	}

	//// ModelicaML specific. 
	//left_hand_component_reference:
	//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
	public Left_hand_component_referenceElements getLeft_hand_component_referenceAccess() {
		return (pLeft_hand_component_reference != null) ? pLeft_hand_component_reference : (pLeft_hand_component_reference = new Left_hand_component_referenceElements());
	}
	
	public ParserRule getLeft_hand_component_referenceRule() {
		return getLeft_hand_component_referenceAccess().getRule();
	}

	//primary:
	//	num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | Name_Function=name_Function | Initial_ref=initial_ref |
	//	Expr=ExprDer | binding=GetBindingFunction | Component_reference=component_reference | "("
	//	output_expr_list=output_expression_list ")" | "[" Expre_list=expression_list (";" Expression_list+=expression_list)*
	//	"]" | "{" f_arguments=function_arguments "}" | End="end";
	public PrimaryElements getPrimaryAccess() {
		return (pPrimary != null) ? pPrimary : (pPrimary = new PrimaryElements());
	}
	
	public ParserRule getPrimaryRule() {
		return getPrimaryAccess().getRule();
	}

	//GetBindingFunction:
	//	"getBinding" Brackets;
	public GetBindingFunctionElements getGetBindingFunctionAccess() {
		return (pGetBindingFunction != null) ? pGetBindingFunction : (pGetBindingFunction = new GetBindingFunctionElements());
	}
	
	public ParserRule getGetBindingFunctionRule() {
		return getGetBindingFunctionAccess().getRule();
	}

	//Brackets:
	//	"(" ")";
	public BracketsElements getBracketsAccess() {
		return (pBrackets != null) ? pBrackets : (pBrackets = new BracketsElements());
	}
	
	public ParserRule getBracketsRule() {
		return getBracketsAccess().getRule();
	}

	//component_reference:
	//	ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)* | "."? ref=IDENT
	//	subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
	public Component_referenceElements getComponent_referenceAccess() {
		return (pComponent_reference != null) ? pComponent_reference : (pComponent_reference = new Component_referenceElements());
	}
	
	public ParserRule getComponent_referenceRule() {
		return getComponent_referenceAccess().getRule();
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
	//mul_op_mul:
	//	"*";
	public ModeleditorGrammarAccess.Mul_op_mulElements getMul_op_mulAccess() {
		return gaModeleditor.getMul_op_mulAccess();
	}
	
	public ParserRule getMul_op_mulRule() {
		return getMul_op_mulAccess().getRule();
	}

	//mul_op_div:
	//	"/";
	public ModeleditorGrammarAccess.Mul_op_divElements getMul_op_divAccess() {
		return gaModeleditor.getMul_op_divAccess();
	}
	
	public ParserRule getMul_op_divRule() {
		return getMul_op_divAccess().getRule();
	}

	//mul_op_dotmul:
	//	".*";
	public ModeleditorGrammarAccess.Mul_op_dotmulElements getMul_op_dotmulAccess() {
		return gaModeleditor.getMul_op_dotmulAccess();
	}
	
	public ParserRule getMul_op_dotmulRule() {
		return getMul_op_dotmulAccess().getRule();
	}

	//mul_op_dotdiv:
	//	"./";
	public ModeleditorGrammarAccess.Mul_op_dotdivElements getMul_op_dotdivAccess() {
		return gaModeleditor.getMul_op_dotdivAccess();
	}
	
	public ParserRule getMul_op_dotdivRule() {
		return getMul_op_dotdivAccess().getRule();
	}

	//add_op_plus:
	//	"+";
	public ModeleditorGrammarAccess.Add_op_plusElements getAdd_op_plusAccess() {
		return gaModeleditor.getAdd_op_plusAccess();
	}
	
	public ParserRule getAdd_op_plusRule() {
		return getAdd_op_plusAccess().getRule();
	}

	//add_op_minus:
	//	"-";
	public ModeleditorGrammarAccess.Add_op_minusElements getAdd_op_minusAccess() {
		return gaModeleditor.getAdd_op_minusAccess();
	}
	
	public ParserRule getAdd_op_minusRule() {
		return getAdd_op_minusAccess().getRule();
	}

	//add_op_dotplus:
	//	".+";
	public ModeleditorGrammarAccess.Add_op_dotplusElements getAdd_op_dotplusAccess() {
		return gaModeleditor.getAdd_op_dotplusAccess();
	}
	
	public ParserRule getAdd_op_dotplusRule() {
		return getAdd_op_dotplusAccess().getRule();
	}

	//add_op_dotminus:
	//	".-";
	public ModeleditorGrammarAccess.Add_op_dotminusElements getAdd_op_dotminusAccess() {
		return gaModeleditor.getAdd_op_dotminusAccess();
	}
	
	public ParserRule getAdd_op_dotminusRule() {
		return getAdd_op_dotminusAccess().getRule();
	}

	//rel_op_Less_then:
	//	"<";
	public ModeleditorGrammarAccess.Rel_op_Less_thenElements getRel_op_Less_thenAccess() {
		return gaModeleditor.getRel_op_Less_thenAccess();
	}
	
	public ParserRule getRel_op_Less_thenRule() {
		return getRel_op_Less_thenAccess().getRule();
	}

	//rel_op_Less_equal:
	//	"<=";
	public ModeleditorGrammarAccess.Rel_op_Less_equalElements getRel_op_Less_equalAccess() {
		return gaModeleditor.getRel_op_Less_equalAccess();
	}
	
	public ParserRule getRel_op_Less_equalRule() {
		return getRel_op_Less_equalAccess().getRule();
	}

	//rel_op_greater_then:
	//	">";
	public ModeleditorGrammarAccess.Rel_op_greater_thenElements getRel_op_greater_thenAccess() {
		return gaModeleditor.getRel_op_greater_thenAccess();
	}
	
	public ParserRule getRel_op_greater_thenRule() {
		return getRel_op_greater_thenAccess().getRule();
	}

	//rel_op_greater_equal:
	//	">=";
	public ModeleditorGrammarAccess.Rel_op_greater_equalElements getRel_op_greater_equalAccess() {
		return gaModeleditor.getRel_op_greater_equalAccess();
	}
	
	public ParserRule getRel_op_greater_equalRule() {
		return getRel_op_greater_equalAccess().getRule();
	}

	//rel_op_assignment:
	//	"==";
	public ModeleditorGrammarAccess.Rel_op_assignmentElements getRel_op_assignmentAccess() {
		return gaModeleditor.getRel_op_assignmentAccess();
	}
	
	public ParserRule getRel_op_assignmentRule() {
		return getRel_op_assignmentAccess().getRule();
	}

	//rel_op_Oper:
	//	"<>";
	public ModeleditorGrammarAccess.Rel_op_OperElements getRel_op_OperAccess() {
		return gaModeleditor.getRel_op_OperAccess();
	}
	
	public ParserRule getRel_op_OperRule() {
		return getRel_op_OperAccess().getRule();
	}

	/////////////////////////////////Comments/////////////////////////////////////
	//comment:
	//	string_comment;
	public ModeleditorGrammarAccess.CommentElements getCommentAccess() {
		return gaModeleditor.getCommentAccess();
	}
	
	public ParserRule getCommentRule() {
		return getCommentAccess().getRule();
	}

	//string_comment:
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
