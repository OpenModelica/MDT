/*
* generated by Xtext
*/
package org.openmodelica.modelicaml.editor.xtext.equation.ui.contentassist.antlr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.openmodelica.modelicaml.editor.xtext.equation.services.EquationsectionGrammarAccess;

import com.google.inject.Inject;

public class EquationsectionParser extends AbstractContentAssistParser {
	
	@Inject
	private EquationsectionGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.openmodelica.modelicaml.editor.xtext.equation.ui.contentassist.antlr.internal.InternalEquationsectionParser createParser() {
		org.openmodelica.modelicaml.editor.xtext.equation.ui.contentassist.antlr.internal.InternalEquationsectionParser result = new org.openmodelica.modelicaml.editor.xtext.equation.ui.contentassist.antlr.internal.InternalEquationsectionParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getEquationAccess().getAlternatives_0(), "rule__Equation__Alternatives_0");
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
					put(grammarAccess.getEquation_sectionAccess().getGroup(), "rule__Equation_section__Group__0");
					put(grammarAccess.getEquation_sectionAccess().getGroup_1(), "rule__Equation_section__Group_1__0");
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
					put(grammarAccess.getEquation_sectionAccess().getEqnAssignment_1_0(), "rule__Equation_section__EqnAssignment_1_0");
					put(grammarAccess.getEquationAccess().getSimAssignment_0_0_0(), "rule__Equation__SimAssignment_0_0_0");
					put(grammarAccess.getEquationAccess().getExprAssignment_0_0_2(), "rule__Equation__ExprAssignment_0_0_2");
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
			org.openmodelica.modelicaml.editor.xtext.equation.ui.contentassist.antlr.internal.InternalEquationsectionParser typedParser = (org.openmodelica.modelicaml.editor.xtext.equation.ui.contentassist.antlr.internal.InternalEquationsectionParser) parser;
			typedParser.entryRuleequation_section();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public EquationsectionGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(EquationsectionGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
