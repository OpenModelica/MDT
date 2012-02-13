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
package org.openmodelica.modelicaml.editor.xtext.algorithm.parseTreeConstruction;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.parsetree.reconstr.IEObjectConsumer;
import org.eclipse.xtext.parsetree.reconstr.impl.AbstractParseTreeConstructor;
import org.openmodelica.modelicaml.editor.xtext.algorithm.services.AlgorithmsectionGrammarAccess;

import com.google.inject.Inject;

@SuppressWarnings("all")
public class AlgorithmsectionParsetreeConstructor extends AbstractParseTreeConstructor {
		
	@Inject
	private AlgorithmsectionGrammarAccess grammarAccess;
	
	@Override
	protected AbstractToken getRootToken(IEObjectConsumer inst) {
		return new ThisRootNode(inst);	
	}
	
protected class ThisRootNode extends RootToken {
	public ThisRootNode(IEObjectConsumer inst) {
		super(inst);
	}
	
	@Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_clause_Group(this, this, 0, inst);
			case 1: return new Statement_Group(this, this, 1, inst);
			case 2: return new Algorithm_Macros_GEN_SIGNAL_Group(this, this, 2, inst);
			case 3: return new Algorithm_Macros_GEN_INC_Group(this, this, 3, inst);
			case 4: return new Algorithm_Macros_GEN_CHANGE_Group(this, this, 4, inst);
			case 5: return new State_OutputExprList_Group(this, this, 5, inst);
			case 6: return new State_Comp_Ref_Group(this, this, 6, inst);
			case 7: return new When_statement_Group(this, this, 7, inst);
			case 8: return new While_statement_Group(this, this, 8, inst);
			case 9: return new For_statement_Group(this, this, 9, inst);
			case 10: return new If_statement_Group(this, this, 10, inst);
			case 11: return new Expression_Alternatives(this, this, 11, inst);
			case 12: return new Simple_expression_Group(this, this, 12, inst);
			case 13: return new Conditional_expr_Group(this, this, 13, inst);
			case 14: return new Logical_expression_Group(this, this, 14, inst);
			case 15: return new Logical_term_Group(this, this, 15, inst);
			case 16: return new Logical_factor_Group(this, this, 16, inst);
			case 17: return new Relation_Group(this, this, 17, inst);
			case 18: return new Arithmetic_expression_Group(this, this, 18, inst);
			case 19: return new Term_Group(this, this, 19, inst);
			case 20: return new Factor_Group(this, this, 20, inst);
			case 21: return new Primary_Alternatives(this, this, 21, inst);
			case 22: return new Name_Function_Group(this, this, 22, inst);
			case 23: return new Initial_ref_Group(this, this, 23, inst);
			case 24: return new ExprDer_Group(this, this, 24, inst);
			case 25: return new Function_call_args_Group(this, this, 25, inst);
			case 26: return new Expression_list_Group(this, this, 26, inst);
			case 27: return new Name_Group(this, this, 27, inst);
			case 28: return new Component_reference_Group(this, this, 28, inst);
			case 29: return new Output_expression_list_Group(this, this, 29, inst);
			case 30: return new Array_subscripts_Group(this, this, 30, inst);
			case 31: return new Subscript_Alternatives(this, this, 31, inst);
			case 32: return new Function_arguments_Alternatives(this, this, 32, inst);
			case 33: return new Fun_Arguments_exp_Group(this, this, 33, inst);
			case 34: return new Fun_Arguments_for_Group(this, this, 34, inst);
			case 35: return new Named_arguments_Group(this, this, 35, inst);
			case 36: return new Named_argument_Group(this, this, 36, inst);
			case 37: return new For_indices_Group(this, this, 37, inst);
			case 38: return new For_index_Group(this, this, 38, inst);
			default: return null;
		}	
	}	
}
	

/************ begin Rule algorithm_clause ****************
 *
 * //////////////////////////////////////////Algorithm//////////////////////
 * algorithm_clause:
 * 	{algorithm_clause} (Algorithm+=statement ";")*;
 *
 **/

// {algorithm_clause} (Algorithm+=statement ";")*
protected class Algorithm_clause_Group extends GroupToken {
	
	public Algorithm_clause_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getAlgorithm_clauseAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_clause_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Algorithm_clause_Algorithm_clauseAction_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getAlgorithm_clauseAccess().getAlgorithm_clauseAction_0().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// {algorithm_clause}
protected class Algorithm_clause_Algorithm_clauseAction_0 extends ActionToken  {

	public Algorithm_clause_Algorithm_clauseAction_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Action getGrammarElement() {
		return grammarAccess.getAlgorithm_clauseAccess().getAlgorithm_clauseAction_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(!eObjectConsumer.isConsumed()) return null;
		return eObjectConsumer;
	}
}

// (Algorithm+=statement ";")*
protected class Algorithm_clause_Group_1 extends GroupToken {
	
	public Algorithm_clause_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getAlgorithm_clauseAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_clause_SemicolonKeyword_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Algorithm+=statement
protected class Algorithm_clause_AlgorithmAssignment_1_0 extends AssignmentToken  {
	
	public Algorithm_clause_AlgorithmAssignment_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getAlgorithm_clauseAccess().getAlgorithmAssignment_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Algorithm",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Algorithm");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getAlgorithm_clauseAccess().getAlgorithmStatementParserRuleCall_1_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Algorithm_clause_Group_1(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new Algorithm_clause_Algorithm_clauseAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class Algorithm_clause_SemicolonKeyword_1_1 extends KeywordToken  {
	
	public Algorithm_clause_SemicolonKeyword_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_clauseAccess().getSemicolonKeyword_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_clause_AlgorithmAssignment_1_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}



/************ end Rule algorithm_clause ****************/


/************ begin Rule statement ****************
 *
 * statement:
 * 	{statement} (If_statement=if_statement | State_OutputExprList=state_OutputExprList |
 * 	Algo_M_G=Algorithm_Macros_GEN_SIGNAL | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
 * 	When_statement=when_statement | While_statement=while_statement | Algo_G_I=Algorithm_Macros_GEN_INC |
 * 	Algo_G_C=Algorithm_Macros_GEN_CHANGE | "break") comment;
 *
 **/

// {statement} (If_statement=if_statement | State_OutputExprList=state_OutputExprList |
// Algo_M_G=Algorithm_Macros_GEN_SIGNAL | "return" | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement |
// When_statement=when_statement | While_statement=while_statement | Algo_G_I=Algorithm_Macros_GEN_INC |
// Algo_G_C=Algorithm_Macros_GEN_CHANGE | "break") comment
protected class Statement_Group extends GroupToken {
	
	public Statement_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getStatementAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_CommentParserRuleCall_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getStatementAccess().getStatementAction_0().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// {statement}
protected class Statement_StatementAction_0 extends ActionToken  {

	public Statement_StatementAction_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Action getGrammarElement() {
		return grammarAccess.getStatementAccess().getStatementAction_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(!eObjectConsumer.isConsumed()) return null;
		return eObjectConsumer;
	}
}

// If_statement=if_statement | State_OutputExprList=state_OutputExprList | Algo_M_G=Algorithm_Macros_GEN_SIGNAL | "return"
// | state_Comp_Ref=state_Comp_Ref | For_statement=for_statement | When_statement=when_statement |
// While_statement=while_statement | Algo_G_I=Algorithm_Macros_GEN_INC | Algo_G_C=Algorithm_Macros_GEN_CHANGE | "break"
protected class Statement_Alternatives_1 extends AlternativesToken {

	public Statement_Alternatives_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getStatementAccess().getAlternatives_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_If_statementAssignment_1_0(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Statement_State_OutputExprListAssignment_1_1(lastRuleCallOrigin, this, 1, inst);
			case 2: return new Statement_Algo_M_GAssignment_1_2(lastRuleCallOrigin, this, 2, inst);
			case 3: return new Statement_ReturnKeyword_1_3(lastRuleCallOrigin, this, 3, inst);
			case 4: return new Statement_State_Comp_RefAssignment_1_4(lastRuleCallOrigin, this, 4, inst);
			case 5: return new Statement_For_statementAssignment_1_5(lastRuleCallOrigin, this, 5, inst);
			case 6: return new Statement_When_statementAssignment_1_6(lastRuleCallOrigin, this, 6, inst);
			case 7: return new Statement_While_statementAssignment_1_7(lastRuleCallOrigin, this, 7, inst);
			case 8: return new Statement_Algo_G_IAssignment_1_8(lastRuleCallOrigin, this, 8, inst);
			case 9: return new Statement_Algo_G_CAssignment_1_9(lastRuleCallOrigin, this, 9, inst);
			default: return null;
		}	
	}

}

// If_statement=if_statement
protected class Statement_If_statementAssignment_1_0 extends AssignmentToken  {
	
	public Statement_If_statementAssignment_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getIf_statementAssignment_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("If_statement",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("If_statement");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getIf_statementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getIf_statementIf_statementParserRuleCall_1_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// State_OutputExprList=state_OutputExprList
protected class Statement_State_OutputExprListAssignment_1_1 extends AssignmentToken  {
	
	public Statement_State_OutputExprListAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getState_OutputExprListAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_OutputExprList_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("State_OutputExprList",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("State_OutputExprList");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getState_OutputExprListRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getState_OutputExprListState_OutputExprListParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// Algo_M_G=Algorithm_Macros_GEN_SIGNAL
protected class Statement_Algo_M_GAssignment_1_2 extends AssignmentToken  {
	
	public Statement_Algo_M_GAssignment_1_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getAlgo_M_GAssignment_1_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_SIGNAL_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Algo_M_G",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Algo_M_G");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getAlgorithm_Macros_GEN_SIGNALRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getAlgo_M_GAlgorithm_Macros_GEN_SIGNALParserRuleCall_1_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "return"
protected class Statement_ReturnKeyword_1_3 extends KeywordToken  {
	
	public Statement_ReturnKeyword_1_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getStatementAccess().getReturnKeyword_1_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// state_Comp_Ref=state_Comp_Ref
protected class Statement_State_Comp_RefAssignment_1_4 extends AssignmentToken  {
	
	public Statement_State_Comp_RefAssignment_1_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getState_Comp_RefAssignment_1_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_Comp_Ref_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("state_Comp_Ref",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("state_Comp_Ref");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getState_Comp_RefRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getState_Comp_RefState_Comp_RefParserRuleCall_1_4_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// For_statement=for_statement
protected class Statement_For_statementAssignment_1_5 extends AssignmentToken  {
	
	public Statement_For_statementAssignment_1_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getFor_statementAssignment_1_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("For_statement",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("For_statement");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFor_statementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getFor_statementFor_statementParserRuleCall_1_5_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// When_statement=when_statement
protected class Statement_When_statementAssignment_1_6 extends AssignmentToken  {
	
	public Statement_When_statementAssignment_1_6(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getWhen_statementAssignment_1_6();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("When_statement",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("When_statement");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getWhen_statementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getWhen_statementWhen_statementParserRuleCall_1_6_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// While_statement=while_statement
protected class Statement_While_statementAssignment_1_7 extends AssignmentToken  {
	
	public Statement_While_statementAssignment_1_7(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getWhile_statementAssignment_1_7();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new While_statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("While_statement",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("While_statement");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getWhile_statementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getWhile_statementWhile_statementParserRuleCall_1_7_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// Algo_G_I=Algorithm_Macros_GEN_INC
protected class Statement_Algo_G_IAssignment_1_8 extends AssignmentToken  {
	
	public Statement_Algo_G_IAssignment_1_8(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getAlgo_G_IAssignment_1_8();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_INC_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Algo_G_I",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Algo_G_I");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getAlgorithm_Macros_GEN_INCRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getAlgo_G_IAlgorithm_Macros_GEN_INCParserRuleCall_1_8_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// Algo_G_C=Algorithm_Macros_GEN_CHANGE
protected class Statement_Algo_G_CAssignment_1_9 extends AssignmentToken  {
	
	public Statement_Algo_G_CAssignment_1_9(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getStatementAccess().getAlgo_G_CAssignment_1_9();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_CHANGE_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Algo_G_C",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Algo_G_C");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getAlgorithm_Macros_GEN_CHANGERule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getStatementAccess().getAlgo_G_CAlgorithm_Macros_GEN_CHANGEParserRuleCall_1_9_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Statement_StatementAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


// comment
protected class Statement_CommentParserRuleCall_2 extends UnassignedTextToken {

	public Statement_CommentParserRuleCall_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getStatementAccess().getCommentParserRuleCall_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Alternatives_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule statement ****************/


/************ begin Rule Algorithm_Macros_GEN_SIGNAL ****************
 *
 * ///////////////////////////////////Macros//////////////////////////////////
 * Algorithm_Macros_GEN_SIGNAL:
 * 	"GEN_SIGNAL" "(" Component_reference=component_reference ")";
 *
 **/

// "GEN_SIGNAL" "(" Component_reference=component_reference ")"
protected class Algorithm_Macros_GEN_SIGNAL_Group extends GroupToken {
	
	public Algorithm_Macros_GEN_SIGNAL_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_SIGNALAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_SIGNAL_RightParenthesisKeyword_3(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getAlgorithm_Macros_GEN_SIGNALRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "GEN_SIGNAL"
protected class Algorithm_Macros_GEN_SIGNAL_GEN_SIGNALKeyword_0 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_SIGNAL_GEN_SIGNALKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_SIGNALAccess().getGEN_SIGNALKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// "("
protected class Algorithm_Macros_GEN_SIGNAL_LeftParenthesisKeyword_1 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_SIGNAL_LeftParenthesisKeyword_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_SIGNALAccess().getLeftParenthesisKeyword_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_SIGNAL_GEN_SIGNALKeyword_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Component_reference=component_reference
protected class Algorithm_Macros_GEN_SIGNAL_Component_referenceAssignment_2 extends AssignmentToken  {
	
	public Algorithm_Macros_GEN_SIGNAL_Component_referenceAssignment_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_SIGNALAccess().getComponent_referenceAssignment_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Component_reference",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Component_reference");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getComponent_referenceRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getAlgorithm_Macros_GEN_SIGNALAccess().getComponent_referenceComponent_referenceParserRuleCall_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_SIGNAL_LeftParenthesisKeyword_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ")"
protected class Algorithm_Macros_GEN_SIGNAL_RightParenthesisKeyword_3 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_SIGNAL_RightParenthesisKeyword_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_SIGNALAccess().getRightParenthesisKeyword_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_SIGNAL_Component_referenceAssignment_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule Algorithm_Macros_GEN_SIGNAL ****************/


/************ begin Rule Algorithm_Macros_GEN_INC ****************
 *
 * Algorithm_Macros_GEN_INC:
 * 	"GEN_INC" "(" Component_reference=component_reference ")";
 *
 **/

// "GEN_INC" "(" Component_reference=component_reference ")"
protected class Algorithm_Macros_GEN_INC_Group extends GroupToken {
	
	public Algorithm_Macros_GEN_INC_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_INCAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_INC_RightParenthesisKeyword_3(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getAlgorithm_Macros_GEN_INCRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "GEN_INC"
protected class Algorithm_Macros_GEN_INC_GEN_INCKeyword_0 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_INC_GEN_INCKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_INCAccess().getGEN_INCKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// "("
protected class Algorithm_Macros_GEN_INC_LeftParenthesisKeyword_1 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_INC_LeftParenthesisKeyword_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_INCAccess().getLeftParenthesisKeyword_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_INC_GEN_INCKeyword_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Component_reference=component_reference
protected class Algorithm_Macros_GEN_INC_Component_referenceAssignment_2 extends AssignmentToken  {
	
	public Algorithm_Macros_GEN_INC_Component_referenceAssignment_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_INCAccess().getComponent_referenceAssignment_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Component_reference",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Component_reference");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getComponent_referenceRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getAlgorithm_Macros_GEN_INCAccess().getComponent_referenceComponent_referenceParserRuleCall_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_INC_LeftParenthesisKeyword_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ")"
protected class Algorithm_Macros_GEN_INC_RightParenthesisKeyword_3 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_INC_RightParenthesisKeyword_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_INCAccess().getRightParenthesisKeyword_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_INC_Component_referenceAssignment_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule Algorithm_Macros_GEN_INC ****************/


/************ begin Rule Algorithm_Macros_GEN_CHANGE ****************
 *
 * Algorithm_Macros_GEN_CHANGE:
 * 	"GEN_CHANGE" "(" Component_reference=component_reference ")";
 *
 **/

// "GEN_CHANGE" "(" Component_reference=component_reference ")"
protected class Algorithm_Macros_GEN_CHANGE_Group extends GroupToken {
	
	public Algorithm_Macros_GEN_CHANGE_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_CHANGEAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_CHANGE_RightParenthesisKeyword_3(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getAlgorithm_Macros_GEN_CHANGERule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "GEN_CHANGE"
protected class Algorithm_Macros_GEN_CHANGE_GEN_CHANGEKeyword_0 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_CHANGE_GEN_CHANGEKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_CHANGEAccess().getGEN_CHANGEKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// "("
protected class Algorithm_Macros_GEN_CHANGE_LeftParenthesisKeyword_1 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_CHANGE_LeftParenthesisKeyword_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_CHANGEAccess().getLeftParenthesisKeyword_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_CHANGE_GEN_CHANGEKeyword_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Component_reference=component_reference
protected class Algorithm_Macros_GEN_CHANGE_Component_referenceAssignment_2 extends AssignmentToken  {
	
	public Algorithm_Macros_GEN_CHANGE_Component_referenceAssignment_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_CHANGEAccess().getComponent_referenceAssignment_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Component_reference",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Component_reference");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getComponent_referenceRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getAlgorithm_Macros_GEN_CHANGEAccess().getComponent_referenceComponent_referenceParserRuleCall_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_CHANGE_LeftParenthesisKeyword_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ")"
protected class Algorithm_Macros_GEN_CHANGE_RightParenthesisKeyword_3 extends KeywordToken  {
	
	public Algorithm_Macros_GEN_CHANGE_RightParenthesisKeyword_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getAlgorithm_Macros_GEN_CHANGEAccess().getRightParenthesisKeyword_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Algorithm_Macros_GEN_CHANGE_Component_referenceAssignment_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule Algorithm_Macros_GEN_CHANGE ****************/


/************ begin Rule state_OutputExprList ****************
 *
 * ///////////////////////////////////Statements//////////////////////////////
 * state_OutputExprList:
 * 	"(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
 * 	fun_call_args=function_call_args;
 *
 **/

// "(" output_expression_list=output_expression_list ")" ":=" Component_ref=component_reference
// fun_call_args=function_call_args
protected class State_OutputExprList_Group extends GroupToken {
	
	public State_OutputExprList_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getState_OutputExprListAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_OutputExprList_Fun_call_argsAssignment_5(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getState_OutputExprListRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "("
protected class State_OutputExprList_LeftParenthesisKeyword_0 extends KeywordToken  {
	
	public State_OutputExprList_LeftParenthesisKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getState_OutputExprListAccess().getLeftParenthesisKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// output_expression_list=output_expression_list
protected class State_OutputExprList_Output_expression_listAssignment_1 extends AssignmentToken  {
	
	public State_OutputExprList_Output_expression_listAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getState_OutputExprListAccess().getOutput_expression_listAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Output_expression_list_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("output_expression_list",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("output_expression_list");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getOutput_expression_listRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getState_OutputExprListAccess().getOutput_expression_listOutput_expression_listParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new State_OutputExprList_LeftParenthesisKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ")"
protected class State_OutputExprList_RightParenthesisKeyword_2 extends KeywordToken  {
	
	public State_OutputExprList_RightParenthesisKeyword_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getState_OutputExprListAccess().getRightParenthesisKeyword_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_OutputExprList_Output_expression_listAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ":="
protected class State_OutputExprList_ColonEqualsSignKeyword_3 extends KeywordToken  {
	
	public State_OutputExprList_ColonEqualsSignKeyword_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getState_OutputExprListAccess().getColonEqualsSignKeyword_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_OutputExprList_RightParenthesisKeyword_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Component_ref=component_reference
protected class State_OutputExprList_Component_refAssignment_4 extends AssignmentToken  {
	
	public State_OutputExprList_Component_refAssignment_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getState_OutputExprListAccess().getComponent_refAssignment_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Component_ref",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Component_ref");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getComponent_referenceRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getState_OutputExprListAccess().getComponent_refComponent_referenceParserRuleCall_4_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new State_OutputExprList_ColonEqualsSignKeyword_3(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// fun_call_args=function_call_args
protected class State_OutputExprList_Fun_call_argsAssignment_5 extends AssignmentToken  {
	
	public State_OutputExprList_Fun_call_argsAssignment_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getState_OutputExprListAccess().getFun_call_argsAssignment_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("fun_call_args",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("fun_call_args");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFunction_call_argsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getState_OutputExprListAccess().getFun_call_argsFunction_call_argsParserRuleCall_5_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new State_OutputExprList_Component_refAssignment_4(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


/************ end Rule state_OutputExprList ****************/


/************ begin Rule state_Comp_Ref ****************
 *
 * state_Comp_Ref:
 * 	component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args);
 *
 **/

// component_reference=component_reference (":=" expr=expression | fun_call_args=function_call_args)
protected class State_Comp_Ref_Group extends GroupToken {
	
	public State_Comp_Ref_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getState_Comp_RefAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_Comp_Ref_Alternatives_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getState_Comp_RefRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// component_reference=component_reference
protected class State_Comp_Ref_Component_referenceAssignment_0 extends AssignmentToken  {
	
	public State_Comp_Ref_Component_referenceAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getState_Comp_RefAccess().getComponent_referenceAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("component_reference",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("component_reference");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getComponent_referenceRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getState_Comp_RefAccess().getComponent_referenceComponent_referenceParserRuleCall_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}

// ":=" expr=expression | fun_call_args=function_call_args
protected class State_Comp_Ref_Alternatives_1 extends AlternativesToken {

	public State_Comp_Ref_Alternatives_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getState_Comp_RefAccess().getAlternatives_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_Comp_Ref_Group_1_0(lastRuleCallOrigin, this, 0, inst);
			case 1: return new State_Comp_Ref_Fun_call_argsAssignment_1_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// ":=" expr=expression
protected class State_Comp_Ref_Group_1_0 extends GroupToken {
	
	public State_Comp_Ref_Group_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getState_Comp_RefAccess().getGroup_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_Comp_Ref_ExprAssignment_1_0_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ":="
protected class State_Comp_Ref_ColonEqualsSignKeyword_1_0_0 extends KeywordToken  {
	
	public State_Comp_Ref_ColonEqualsSignKeyword_1_0_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getState_Comp_RefAccess().getColonEqualsSignKeyword_1_0_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new State_Comp_Ref_Component_referenceAssignment_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// expr=expression
protected class State_Comp_Ref_ExprAssignment_1_0_1 extends AssignmentToken  {
	
	public State_Comp_Ref_ExprAssignment_1_0_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getState_Comp_RefAccess().getExprAssignment_1_0_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getState_Comp_RefAccess().getExprExpressionParserRuleCall_1_0_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new State_Comp_Ref_ColonEqualsSignKeyword_1_0_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


// fun_call_args=function_call_args
protected class State_Comp_Ref_Fun_call_argsAssignment_1_1 extends AssignmentToken  {
	
	public State_Comp_Ref_Fun_call_argsAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getState_Comp_RefAccess().getFun_call_argsAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("fun_call_args",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("fun_call_args");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFunction_call_argsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getState_Comp_RefAccess().getFun_call_argsFunction_call_argsParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new State_Comp_Ref_Component_referenceAssignment_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule state_Comp_Ref ****************/


/************ begin Rule when_statement ****************
 *
 * when_statement:
 * 	"when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
 * 	(Then_statement+=statement ";")*)* "end" "when";
 *
 **/

// "when" When_expr=expression "then" (When_stat_true+=statement ";")* ("elsewhen" Else_When_expr+=expression "then"
// (Then_statement+=statement ";")*)* "end" "when"
protected class When_statement_Group extends GroupToken {
	
	public When_statement_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_WhenKeyword_6(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getWhen_statementRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "when"
protected class When_statement_WhenKeyword_0 extends KeywordToken  {
	
	public When_statement_WhenKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getWhenKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// When_expr=expression
protected class When_statement_When_exprAssignment_1 extends AssignmentToken  {
	
	public When_statement_When_exprAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getWhen_exprAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("When_expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("When_expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getWhen_statementAccess().getWhen_exprExpressionParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new When_statement_WhenKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "then"
protected class When_statement_ThenKeyword_2 extends KeywordToken  {
	
	public When_statement_ThenKeyword_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getThenKeyword_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_When_exprAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// (When_stat_true+=statement ";")*
protected class When_statement_Group_3 extends GroupToken {
	
	public When_statement_Group_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getGroup_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_SemicolonKeyword_3_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// When_stat_true+=statement
protected class When_statement_When_stat_trueAssignment_3_0 extends AssignmentToken  {
	
	public When_statement_When_stat_trueAssignment_3_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getWhen_stat_trueAssignment_3_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("When_stat_true",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("When_stat_true");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getWhen_statementAccess().getWhen_stat_trueStatementParserRuleCall_3_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new When_statement_Group_3(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new When_statement_ThenKeyword_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class When_statement_SemicolonKeyword_3_1 extends KeywordToken  {
	
	public When_statement_SemicolonKeyword_3_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getSemicolonKeyword_3_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_When_stat_trueAssignment_3_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// ("elsewhen" Else_When_expr+=expression "then" (Then_statement+=statement ";")*)*
protected class When_statement_Group_4 extends GroupToken {
	
	public When_statement_Group_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getGroup_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_Group_4_3(lastRuleCallOrigin, this, 0, inst);
			case 1: return new When_statement_ThenKeyword_4_2(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// "elsewhen"
protected class When_statement_ElsewhenKeyword_4_0 extends KeywordToken  {
	
	public When_statement_ElsewhenKeyword_4_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getElsewhenKeyword_4_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_Group_4(lastRuleCallOrigin, this, 0, inst);
			case 1: return new When_statement_Group_3(lastRuleCallOrigin, this, 1, inst);
			case 2: return new When_statement_ThenKeyword_2(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

}

// Else_When_expr+=expression
protected class When_statement_Else_When_exprAssignment_4_1 extends AssignmentToken  {
	
	public When_statement_Else_When_exprAssignment_4_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getElse_When_exprAssignment_4_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Else_When_expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Else_When_expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getWhen_statementAccess().getElse_When_exprExpressionParserRuleCall_4_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new When_statement_ElsewhenKeyword_4_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "then"
protected class When_statement_ThenKeyword_4_2 extends KeywordToken  {
	
	public When_statement_ThenKeyword_4_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getThenKeyword_4_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_Else_When_exprAssignment_4_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// (Then_statement+=statement ";")*
protected class When_statement_Group_4_3 extends GroupToken {
	
	public When_statement_Group_4_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getGroup_4_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_SemicolonKeyword_4_3_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Then_statement+=statement
protected class When_statement_Then_statementAssignment_4_3_0 extends AssignmentToken  {
	
	public When_statement_Then_statementAssignment_4_3_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getThen_statementAssignment_4_3_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Then_statement",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Then_statement");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getWhen_statementAccess().getThen_statementStatementParserRuleCall_4_3_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new When_statement_Group_4_3(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new When_statement_ThenKeyword_4_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class When_statement_SemicolonKeyword_4_3_1 extends KeywordToken  {
	
	public When_statement_SemicolonKeyword_4_3_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getSemicolonKeyword_4_3_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_Then_statementAssignment_4_3_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}



// "end"
protected class When_statement_EndKeyword_5 extends KeywordToken  {
	
	public When_statement_EndKeyword_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getEndKeyword_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_Group_4(lastRuleCallOrigin, this, 0, inst);
			case 1: return new When_statement_Group_3(lastRuleCallOrigin, this, 1, inst);
			case 2: return new When_statement_ThenKeyword_2(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

}

// "when"
protected class When_statement_WhenKeyword_6 extends KeywordToken  {
	
	public When_statement_WhenKeyword_6(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhen_statementAccess().getWhenKeyword_6();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new When_statement_EndKeyword_5(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule when_statement ****************/


/************ begin Rule while_statement ****************
 *
 * while_statement:
 * 	"while" while_expr=expression "loop" (While_stat+=statement ";")* "end" "while";
 *
 **/

// "while" while_expr=expression "loop" (While_stat+=statement ";")* "end" "while"
protected class While_statement_Group extends GroupToken {
	
	public While_statement_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new While_statement_WhileKeyword_5(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getWhile_statementRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "while"
protected class While_statement_WhileKeyword_0 extends KeywordToken  {
	
	public While_statement_WhileKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getWhileKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// while_expr=expression
protected class While_statement_While_exprAssignment_1 extends AssignmentToken  {
	
	public While_statement_While_exprAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getWhile_exprAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("while_expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("while_expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getWhile_statementAccess().getWhile_exprExpressionParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new While_statement_WhileKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "loop"
protected class While_statement_LoopKeyword_2 extends KeywordToken  {
	
	public While_statement_LoopKeyword_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getLoopKeyword_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new While_statement_While_exprAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// (While_stat+=statement ";")*
protected class While_statement_Group_3 extends GroupToken {
	
	public While_statement_Group_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getGroup_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new While_statement_SemicolonKeyword_3_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// While_stat+=statement
protected class While_statement_While_statAssignment_3_0 extends AssignmentToken  {
	
	public While_statement_While_statAssignment_3_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getWhile_statAssignment_3_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("While_stat",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("While_stat");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getWhile_statementAccess().getWhile_statStatementParserRuleCall_3_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new While_statement_Group_3(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new While_statement_LoopKeyword_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class While_statement_SemicolonKeyword_3_1 extends KeywordToken  {
	
	public While_statement_SemicolonKeyword_3_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getSemicolonKeyword_3_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new While_statement_While_statAssignment_3_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// "end"
protected class While_statement_EndKeyword_4 extends KeywordToken  {
	
	public While_statement_EndKeyword_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getEndKeyword_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new While_statement_Group_3(lastRuleCallOrigin, this, 0, inst);
			case 1: return new While_statement_LoopKeyword_2(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// "while"
protected class While_statement_WhileKeyword_5 extends KeywordToken  {
	
	public While_statement_WhileKeyword_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getWhile_statementAccess().getWhileKeyword_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new While_statement_EndKeyword_4(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule while_statement ****************/


/************ begin Rule for_statement ****************
 *
 * for_statement:
 * 	"for" for_loop=for_indices "loop" (For_algo+=statement ";")* "end" "for";
 *
 **/

// "for" for_loop=for_indices "loop" (For_algo+=statement ";")* "end" "for"
protected class For_statement_Group extends GroupToken {
	
	public For_statement_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_statement_ForKeyword_5(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFor_statementRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "for"
protected class For_statement_ForKeyword_0 extends KeywordToken  {
	
	public For_statement_ForKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getForKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// for_loop=for_indices
protected class For_statement_For_loopAssignment_1 extends AssignmentToken  {
	
	public For_statement_For_loopAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getFor_loopAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_indices_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("for_loop",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("for_loop");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFor_indicesRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFor_statementAccess().getFor_loopFor_indicesParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new For_statement_ForKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "loop"
protected class For_statement_LoopKeyword_2 extends KeywordToken  {
	
	public For_statement_LoopKeyword_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getLoopKeyword_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_statement_For_loopAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// (For_algo+=statement ";")*
protected class For_statement_Group_3 extends GroupToken {
	
	public For_statement_Group_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getGroup_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_statement_SemicolonKeyword_3_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// For_algo+=statement
protected class For_statement_For_algoAssignment_3_0 extends AssignmentToken  {
	
	public For_statement_For_algoAssignment_3_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getFor_algoAssignment_3_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("For_algo",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("For_algo");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFor_statementAccess().getFor_algoStatementParserRuleCall_3_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new For_statement_Group_3(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new For_statement_LoopKeyword_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class For_statement_SemicolonKeyword_3_1 extends KeywordToken  {
	
	public For_statement_SemicolonKeyword_3_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getSemicolonKeyword_3_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_statement_For_algoAssignment_3_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// "end"
protected class For_statement_EndKeyword_4 extends KeywordToken  {
	
	public For_statement_EndKeyword_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getEndKeyword_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_statement_Group_3(lastRuleCallOrigin, this, 0, inst);
			case 1: return new For_statement_LoopKeyword_2(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// "for"
protected class For_statement_ForKeyword_5 extends KeywordToken  {
	
	public For_statement_ForKeyword_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFor_statementAccess().getForKeyword_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_statement_EndKeyword_4(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule for_statement ****************/


/************ begin Rule if_statement ****************
 *
 * if_statement:
 * 	"if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
 * 	";")*)* ("else" (state+=statement ";")*)? "end" "if";
 *
 **/

// "if" exprtrue=expression "then" (state+=statement ";")* ("elseif" exprStilltrue+=expression "then" (state+=statement
// ";")*)* ("else" (state+=statement ";")*)? "end" "if"
protected class If_statement_Group extends GroupToken {
	
	public If_statement_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_IfKeyword_7(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getIf_statementRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "if"
protected class If_statement_IfKeyword_0 extends KeywordToken  {
	
	public If_statement_IfKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getIfKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// exprtrue=expression
protected class If_statement_ExprtrueAssignment_1 extends AssignmentToken  {
	
	public If_statement_ExprtrueAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getExprtrueAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("exprtrue",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("exprtrue");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getIf_statementAccess().getExprtrueExpressionParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new If_statement_IfKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "then"
protected class If_statement_ThenKeyword_2 extends KeywordToken  {
	
	public If_statement_ThenKeyword_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getThenKeyword_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_ExprtrueAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// (state+=statement ";")*
protected class If_statement_Group_3 extends GroupToken {
	
	public If_statement_Group_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getGroup_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_SemicolonKeyword_3_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// state+=statement
protected class If_statement_StateAssignment_3_0 extends AssignmentToken  {
	
	public If_statement_StateAssignment_3_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getStateAssignment_3_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("state",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("state");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getIf_statementAccess().getStateStatementParserRuleCall_3_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new If_statement_Group_3(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new If_statement_ThenKeyword_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class If_statement_SemicolonKeyword_3_1 extends KeywordToken  {
	
	public If_statement_SemicolonKeyword_3_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getSemicolonKeyword_3_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_StateAssignment_3_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// ("elseif" exprStilltrue+=expression "then" (state+=statement ";")*)*
protected class If_statement_Group_4 extends GroupToken {
	
	public If_statement_Group_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getGroup_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_Group_4_3(lastRuleCallOrigin, this, 0, inst);
			case 1: return new If_statement_ThenKeyword_4_2(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// "elseif"
protected class If_statement_ElseifKeyword_4_0 extends KeywordToken  {
	
	public If_statement_ElseifKeyword_4_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getElseifKeyword_4_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_Group_4(lastRuleCallOrigin, this, 0, inst);
			case 1: return new If_statement_Group_3(lastRuleCallOrigin, this, 1, inst);
			case 2: return new If_statement_ThenKeyword_2(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

}

// exprStilltrue+=expression
protected class If_statement_ExprStilltrueAssignment_4_1 extends AssignmentToken  {
	
	public If_statement_ExprStilltrueAssignment_4_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getExprStilltrueAssignment_4_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("exprStilltrue",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("exprStilltrue");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getIf_statementAccess().getExprStilltrueExpressionParserRuleCall_4_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new If_statement_ElseifKeyword_4_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "then"
protected class If_statement_ThenKeyword_4_2 extends KeywordToken  {
	
	public If_statement_ThenKeyword_4_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getThenKeyword_4_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_ExprStilltrueAssignment_4_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// (state+=statement ";")*
protected class If_statement_Group_4_3 extends GroupToken {
	
	public If_statement_Group_4_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getGroup_4_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_SemicolonKeyword_4_3_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// state+=statement
protected class If_statement_StateAssignment_4_3_0 extends AssignmentToken  {
	
	public If_statement_StateAssignment_4_3_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getStateAssignment_4_3_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("state",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("state");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getIf_statementAccess().getStateStatementParserRuleCall_4_3_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new If_statement_Group_4_3(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new If_statement_ThenKeyword_4_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class If_statement_SemicolonKeyword_4_3_1 extends KeywordToken  {
	
	public If_statement_SemicolonKeyword_4_3_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getSemicolonKeyword_4_3_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_StateAssignment_4_3_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}



// ("else" (state+=statement ";")*)?
protected class If_statement_Group_5 extends GroupToken {
	
	public If_statement_Group_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getGroup_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_Group_5_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "else"
protected class If_statement_ElseKeyword_5_0 extends KeywordToken  {
	
	public If_statement_ElseKeyword_5_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getElseKeyword_5_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_Group_4(lastRuleCallOrigin, this, 0, inst);
			case 1: return new If_statement_Group_3(lastRuleCallOrigin, this, 1, inst);
			case 2: return new If_statement_ThenKeyword_2(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

}

// (state+=statement ";")*
protected class If_statement_Group_5_1 extends GroupToken {
	
	public If_statement_Group_5_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getGroup_5_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_SemicolonKeyword_5_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// state+=statement
protected class If_statement_StateAssignment_5_1_0 extends AssignmentToken  {
	
	public If_statement_StateAssignment_5_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getStateAssignment_5_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Statement_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("state",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("state");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getStatementRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getIf_statementAccess().getStateStatementParserRuleCall_5_1_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new If_statement_Group_5_1(lastRuleCallOrigin, next, actIndex, consumed);
			case 1: return new If_statement_ElseKeyword_5_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ";"
protected class If_statement_SemicolonKeyword_5_1_1 extends KeywordToken  {
	
	public If_statement_SemicolonKeyword_5_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getSemicolonKeyword_5_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_StateAssignment_5_1_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}



// "end"
protected class If_statement_EndKeyword_6 extends KeywordToken  {
	
	public If_statement_EndKeyword_6(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getEndKeyword_6();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_Group_5(lastRuleCallOrigin, this, 0, inst);
			case 1: return new If_statement_Group_4(lastRuleCallOrigin, this, 1, inst);
			case 2: return new If_statement_Group_3(lastRuleCallOrigin, this, 2, inst);
			case 3: return new If_statement_ThenKeyword_2(lastRuleCallOrigin, this, 3, inst);
			default: return null;
		}	
	}

}

// "if"
protected class If_statement_IfKeyword_7 extends KeywordToken  {
	
	public If_statement_IfKeyword_7(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getIf_statementAccess().getIfKeyword_7();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new If_statement_EndKeyword_6(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


/************ end Rule if_statement ****************/


/************ begin Rule expression ****************
 *
 * ////////////////////////////////////////Expressions////////////////////////
 * expression:
 * 	Expr=simple_expression | conditional_expr;
 *
 **/

// Expr=simple_expression | conditional_expr
protected class Expression_Alternatives extends AlternativesToken {

	public Expression_Alternatives(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getExpressionAccess().getAlternatives();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_ExprAssignment_0(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Expression_Conditional_exprParserRuleCall_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getConditional_exprRule().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getExpressionRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// Expr=simple_expression
protected class Expression_ExprAssignment_0 extends AssignmentToken  {
	
	public Expression_ExprAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getExpressionAccess().getExprAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Simple_expression_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getExpressionRule().getType().getClassifier())
			return null;
		if((value = eObjectConsumer.getConsumable("Expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getSimple_expressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getExpressionAccess().getExprSimple_expressionParserRuleCall_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}

// conditional_expr
protected class Expression_Conditional_exprParserRuleCall_1 extends RuleCallToken {
	
	public Expression_Conditional_exprParserRuleCall_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getExpressionAccess().getConditional_exprParserRuleCall_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getConditional_exprRule().getType().getClassifier())
			return null;
		if(checkForRecursion(Conditional_expr_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}


/************ end Rule expression ****************/


/************ begin Rule simple_expression ****************
 *
 * simple_expression:
 * 	Log_Exp=logical_expression (":" S_Logical_expression=logical_expression (":"
 * 	L_Logical_expression=logical_expression)?)?;
 *
 **/

// Log_Exp=logical_expression (":" S_Logical_expression=logical_expression (":" L_Logical_expression=logical_expression)?)?
protected class Simple_expression_Group extends GroupToken {
	
	public Simple_expression_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Simple_expression_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Simple_expression_Log_ExpAssignment_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getSimple_expressionRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// Log_Exp=logical_expression
protected class Simple_expression_Log_ExpAssignment_0 extends AssignmentToken  {
	
	public Simple_expression_Log_ExpAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getLog_ExpAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_expression_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Log_Exp",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Log_Exp");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getLogical_expressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getSimple_expressionAccess().getLog_ExpLogical_expressionParserRuleCall_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}

// (":" S_Logical_expression=logical_expression (":" L_Logical_expression=logical_expression)?)?
protected class Simple_expression_Group_1 extends GroupToken {
	
	public Simple_expression_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Simple_expression_Group_1_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Simple_expression_S_Logical_expressionAssignment_1_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// ":"
protected class Simple_expression_ColonKeyword_1_0 extends KeywordToken  {
	
	public Simple_expression_ColonKeyword_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getColonKeyword_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Simple_expression_Log_ExpAssignment_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// S_Logical_expression=logical_expression
protected class Simple_expression_S_Logical_expressionAssignment_1_1 extends AssignmentToken  {
	
	public Simple_expression_S_Logical_expressionAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getS_Logical_expressionAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_expression_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("S_Logical_expression",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("S_Logical_expression");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getLogical_expressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getSimple_expressionAccess().getS_Logical_expressionLogical_expressionParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Simple_expression_ColonKeyword_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// (":" L_Logical_expression=logical_expression)?
protected class Simple_expression_Group_1_2 extends GroupToken {
	
	public Simple_expression_Group_1_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getGroup_1_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Simple_expression_L_Logical_expressionAssignment_1_2_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ":"
protected class Simple_expression_ColonKeyword_1_2_0 extends KeywordToken  {
	
	public Simple_expression_ColonKeyword_1_2_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getColonKeyword_1_2_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Simple_expression_S_Logical_expressionAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// L_Logical_expression=logical_expression
protected class Simple_expression_L_Logical_expressionAssignment_1_2_1 extends AssignmentToken  {
	
	public Simple_expression_L_Logical_expressionAssignment_1_2_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getSimple_expressionAccess().getL_Logical_expressionAssignment_1_2_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_expression_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("L_Logical_expression",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("L_Logical_expression");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getLogical_expressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getSimple_expressionAccess().getL_Logical_expressionLogical_expressionParserRuleCall_1_2_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Simple_expression_ColonKeyword_1_2_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}




/************ end Rule simple_expression ****************/


/************ begin Rule conditional_expr ****************
 *
 * conditional_expr:
 * 	"if" ifexpr=expression "then" thenexpr=expression ("elseif" elseifexpr+=expression "then" trueexpr+=expression)*
 * 	("else" falseexpr=expression);
 *
 **/

// "if" ifexpr=expression "then" thenexpr=expression ("elseif" elseifexpr+=expression "then" trueexpr+=expression)* ("else"
// falseexpr=expression)
protected class Conditional_expr_Group extends GroupToken {
	
	public Conditional_expr_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_Group_5(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getConditional_exprRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "if"
protected class Conditional_expr_IfKeyword_0 extends KeywordToken  {
	
	public Conditional_expr_IfKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getIfKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// ifexpr=expression
protected class Conditional_expr_IfexprAssignment_1 extends AssignmentToken  {
	
	public Conditional_expr_IfexprAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getIfexprAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("ifexpr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("ifexpr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getConditional_exprAccess().getIfexprExpressionParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Conditional_expr_IfKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "then"
protected class Conditional_expr_ThenKeyword_2 extends KeywordToken  {
	
	public Conditional_expr_ThenKeyword_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getThenKeyword_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_IfexprAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// thenexpr=expression
protected class Conditional_expr_ThenexprAssignment_3 extends AssignmentToken  {
	
	public Conditional_expr_ThenexprAssignment_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getThenexprAssignment_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("thenexpr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("thenexpr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getConditional_exprAccess().getThenexprExpressionParserRuleCall_3_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Conditional_expr_ThenKeyword_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ("elseif" elseifexpr+=expression "then" trueexpr+=expression)*
protected class Conditional_expr_Group_4 extends GroupToken {
	
	public Conditional_expr_Group_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getGroup_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_TrueexprAssignment_4_3(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "elseif"
protected class Conditional_expr_ElseifKeyword_4_0 extends KeywordToken  {
	
	public Conditional_expr_ElseifKeyword_4_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getElseifKeyword_4_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_Group_4(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Conditional_expr_ThenexprAssignment_3(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// elseifexpr+=expression
protected class Conditional_expr_ElseifexprAssignment_4_1 extends AssignmentToken  {
	
	public Conditional_expr_ElseifexprAssignment_4_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getElseifexprAssignment_4_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("elseifexpr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("elseifexpr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getConditional_exprAccess().getElseifexprExpressionParserRuleCall_4_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Conditional_expr_ElseifKeyword_4_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "then"
protected class Conditional_expr_ThenKeyword_4_2 extends KeywordToken  {
	
	public Conditional_expr_ThenKeyword_4_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getThenKeyword_4_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_ElseifexprAssignment_4_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// trueexpr+=expression
protected class Conditional_expr_TrueexprAssignment_4_3 extends AssignmentToken  {
	
	public Conditional_expr_TrueexprAssignment_4_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getTrueexprAssignment_4_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("trueexpr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("trueexpr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getConditional_exprAccess().getTrueexprExpressionParserRuleCall_4_3_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Conditional_expr_ThenKeyword_4_2(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


// "else" falseexpr=expression
protected class Conditional_expr_Group_5 extends GroupToken {
	
	public Conditional_expr_Group_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getGroup_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_FalseexprAssignment_5_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "else"
protected class Conditional_expr_ElseKeyword_5_0 extends KeywordToken  {
	
	public Conditional_expr_ElseKeyword_5_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getElseKeyword_5_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Conditional_expr_Group_4(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Conditional_expr_ThenexprAssignment_3(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// falseexpr=expression
protected class Conditional_expr_FalseexprAssignment_5_1 extends AssignmentToken  {
	
	public Conditional_expr_FalseexprAssignment_5_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getConditional_exprAccess().getFalseexprAssignment_5_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("falseexpr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("falseexpr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getConditional_exprAccess().getFalseexprExpressionParserRuleCall_5_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Conditional_expr_ElseKeyword_5_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule conditional_expr ****************/


/************ begin Rule logical_expression ****************
 *
 * logical_expression:
 * 	logical_term ("or" Logical_term+=logical_term)*;
 *
 **/

// logical_term ("or" Logical_term+=logical_term)*
protected class Logical_expression_Group extends GroupToken {
	
	public Logical_expression_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getLogical_expressionAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_expression_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Logical_expression_Logical_termParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getLogical_factorRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// logical_term
protected class Logical_expression_Logical_termParserRuleCall_0 extends RuleCallToken {
	
	public Logical_expression_Logical_termParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getLogical_expressionAccess().getLogical_termParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_term_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Logical_term_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// ("or" Logical_term+=logical_term)*
protected class Logical_expression_Group_1 extends GroupToken {
	
	public Logical_expression_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getLogical_expressionAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_expression_Logical_termAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "or"
protected class Logical_expression_OrKeyword_1_0 extends KeywordToken  {
	
	public Logical_expression_OrKeyword_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getLogical_expressionAccess().getOrKeyword_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_expression_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Logical_expression_Logical_termParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// Logical_term+=logical_term
protected class Logical_expression_Logical_termAssignment_1_1 extends AssignmentToken  {
	
	public Logical_expression_Logical_termAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getLogical_expressionAccess().getLogical_termAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_term_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Logical_term",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Logical_term");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getLogical_termRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getLogical_expressionAccess().getLogical_termLogical_termParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Logical_expression_OrKeyword_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule logical_expression ****************/


/************ begin Rule logical_term ****************
 *
 * logical_term:
 * 	logical_factor ("and" Logical_factor+=logical_factor)*;
 *
 **/

// logical_factor ("and" Logical_factor+=logical_factor)*
protected class Logical_term_Group extends GroupToken {
	
	public Logical_term_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getLogical_termAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_term_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Logical_term_Logical_factorParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getLogical_factorRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// logical_factor
protected class Logical_term_Logical_factorParserRuleCall_0 extends RuleCallToken {
	
	public Logical_term_Logical_factorParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getLogical_termAccess().getLogical_factorParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_factor_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Logical_factor_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// ("and" Logical_factor+=logical_factor)*
protected class Logical_term_Group_1 extends GroupToken {
	
	public Logical_term_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getLogical_termAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_term_Logical_factorAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "and"
protected class Logical_term_AndKeyword_1_0 extends KeywordToken  {
	
	public Logical_term_AndKeyword_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getLogical_termAccess().getAndKeyword_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_term_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Logical_term_Logical_factorParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// Logical_factor+=logical_factor
protected class Logical_term_Logical_factorAssignment_1_1 extends AssignmentToken  {
	
	public Logical_term_Logical_factorAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getLogical_termAccess().getLogical_factorAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_factor_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Logical_factor",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Logical_factor");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getLogical_factorRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getLogical_termAccess().getLogical_factorLogical_factorParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Logical_term_AndKeyword_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule logical_term ****************/


/************ begin Rule logical_factor ****************
 *
 * logical_factor:
 * 	"not"? Relation=relation;
 *
 **/

// "not"? Relation=relation
protected class Logical_factor_Group extends GroupToken {
	
	public Logical_factor_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getLogical_factorAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Logical_factor_RelationAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getLogical_factorRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// Relation=relation
protected class Logical_factor_RelationAssignment_1 extends AssignmentToken  {
	
	public Logical_factor_RelationAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getLogical_factorAccess().getRelationAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Relation_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Relation",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Relation");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getRelationRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getLogical_factorAccess().getRelationRelationParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}


/************ end Rule logical_factor ****************/


/************ begin Rule relation ****************
 *
 * relation:
 * 	arithmetic_expression (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal |
 * 	rel_op_assignment | rel_op_Oper) Arithmetic_expression=arithmetic_expression)?;
 *
 **/

// arithmetic_expression (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal |
// rel_op_assignment | rel_op_Oper) Arithmetic_expression=arithmetic_expression)?
protected class Relation_Group extends GroupToken {
	
	public Relation_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getRelationAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Relation_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Relation_Arithmetic_expressionParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getArithmetic_expressionRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// arithmetic_expression
protected class Relation_Arithmetic_expressionParserRuleCall_0 extends RuleCallToken {
	
	public Relation_Arithmetic_expressionParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getRelationAccess().getArithmetic_expressionParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Arithmetic_expression_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Arithmetic_expression_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// (op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal | rel_op_assignment |
// rel_op_Oper) Arithmetic_expression=arithmetic_expression)?
protected class Relation_Group_1 extends GroupToken {
	
	public Relation_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getRelationAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Relation_Arithmetic_expressionAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// op=(rel_op_Less_then | rel_op_Less_equal | rel_op_greater_then | rel_op_greater_equal | rel_op_assignment | rel_op_Oper)
protected class Relation_OpAssignment_1_0 extends AssignmentToken  {
	
	public Relation_OpAssignment_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getRelationAccess().getOpAssignment_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Relation_Arithmetic_expressionParserRuleCall_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("op",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("op");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getRelationAccess().getOpRel_op_Less_thenParserRuleCall_1_0_0_0(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getRelationAccess().getOpRel_op_Less_thenParserRuleCall_1_0_0_0();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getRelationAccess().getOpRel_op_Less_equalParserRuleCall_1_0_0_1(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getRelationAccess().getOpRel_op_Less_equalParserRuleCall_1_0_0_1();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getRelationAccess().getOpRel_op_greater_thenParserRuleCall_1_0_0_2(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getRelationAccess().getOpRel_op_greater_thenParserRuleCall_1_0_0_2();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getRelationAccess().getOpRel_op_greater_equalParserRuleCall_1_0_0_3(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getRelationAccess().getOpRel_op_greater_equalParserRuleCall_1_0_0_3();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getRelationAccess().getOpRel_op_assignmentParserRuleCall_1_0_0_4(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getRelationAccess().getOpRel_op_assignmentParserRuleCall_1_0_0_4();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getRelationAccess().getOpRel_op_OperParserRuleCall_1_0_0_5(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getRelationAccess().getOpRel_op_OperParserRuleCall_1_0_0_5();
			return obj;
		}
		return null;
	}

}

// Arithmetic_expression=arithmetic_expression
protected class Relation_Arithmetic_expressionAssignment_1_1 extends AssignmentToken  {
	
	public Relation_Arithmetic_expressionAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getRelationAccess().getArithmetic_expressionAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Arithmetic_expression_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Arithmetic_expression",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Arithmetic_expression");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getArithmetic_expressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getRelationAccess().getArithmetic_expressionArithmetic_expressionParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Relation_OpAssignment_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule relation ****************/


/************ begin Rule arithmetic_expression ****************
 *
 * arithmetic_expression:
 * 	opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)? Term=term (Oper1+=(add_op_plus | add_op_minus |
 * 	add_op_dotplus | add_op_dotminus) Term1+=term)*;
 *
 **/

// opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)? Term=term (Oper1+=(add_op_plus | add_op_minus |
// add_op_dotplus | add_op_dotminus) Term1+=term)*
protected class Arithmetic_expression_Group extends GroupToken {
	
	public Arithmetic_expression_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getArithmetic_expressionAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Arithmetic_expression_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Arithmetic_expression_TermAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getArithmetic_expressionRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// opr=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)?
protected class Arithmetic_expression_OprAssignment_0 extends AssignmentToken  {
	
	public Arithmetic_expression_OprAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getArithmetic_expressionAccess().getOprAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("opr",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("opr");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_plusParserRuleCall_0_0_0(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_plusParserRuleCall_0_0_0();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_minusParserRuleCall_0_0_1(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_minusParserRuleCall_0_0_1();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotplusParserRuleCall_0_0_2(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotplusParserRuleCall_0_0_2();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotminusParserRuleCall_0_0_3(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOprAdd_op_dotminusParserRuleCall_0_0_3();
			return obj;
		}
		return null;
	}

}

// Term=term
protected class Arithmetic_expression_TermAssignment_1 extends AssignmentToken  {
	
	public Arithmetic_expression_TermAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getArithmetic_expressionAccess().getTermAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Term_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Term",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Term");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getTermRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getArithmetic_expressionAccess().getTermTermParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Arithmetic_expression_OprAssignment_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index - 1, consumed);
		}	
	}	
}

// (Oper1+=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus) Term1+=term)*
protected class Arithmetic_expression_Group_2 extends GroupToken {
	
	public Arithmetic_expression_Group_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getArithmetic_expressionAccess().getGroup_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Arithmetic_expression_Term1Assignment_2_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Oper1+=(add_op_plus | add_op_minus | add_op_dotplus | add_op_dotminus)
protected class Arithmetic_expression_Oper1Assignment_2_0 extends AssignmentToken  {
	
	public Arithmetic_expression_Oper1Assignment_2_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getArithmetic_expressionAccess().getOper1Assignment_2_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Arithmetic_expression_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Arithmetic_expression_TermAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Oper1",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Oper1");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_plusParserRuleCall_2_0_0_0(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_plusParserRuleCall_2_0_0_0();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_minusParserRuleCall_2_0_0_1(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_minusParserRuleCall_2_0_0_1();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotplusParserRuleCall_2_0_0_2(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotplusParserRuleCall_2_0_0_2();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotminusParserRuleCall_2_0_0_3(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getArithmetic_expressionAccess().getOper1Add_op_dotminusParserRuleCall_2_0_0_3();
			return obj;
		}
		return null;
	}

}

// Term1+=term
protected class Arithmetic_expression_Term1Assignment_2_1 extends AssignmentToken  {
	
	public Arithmetic_expression_Term1Assignment_2_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getArithmetic_expressionAccess().getTerm1Assignment_2_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Term_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Term1",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Term1");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getTermRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getArithmetic_expressionAccess().getTerm1TermParserRuleCall_2_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Arithmetic_expression_Oper1Assignment_2_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule arithmetic_expression ****************/


/************ begin Rule term ****************
 *
 * term:
 * 	factor (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*;
 *
 **/

// factor (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*
protected class Term_Group extends GroupToken {
	
	public Term_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getTermAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Term_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Term_FactorParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getExprDerRule().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getNameRule().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// factor
protected class Term_FactorParserRuleCall_0 extends RuleCallToken {
	
	public Term_FactorParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getTermAccess().getFactorParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Factor_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Factor_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// (op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv) Factor+=factor)*
protected class Term_Group_1 extends GroupToken {
	
	public Term_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getTermAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Term_FactorAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// op+=(mul_op_mul | mul_op_div | mul_op_dotmul | mul_op_dotdiv)
protected class Term_OpAssignment_1_0 extends AssignmentToken  {
	
	public Term_OpAssignment_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getTermAccess().getOpAssignment_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Term_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Term_FactorParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("op",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("op");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getTermAccess().getOpMul_op_mulParserRuleCall_1_0_0_0(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getTermAccess().getOpMul_op_mulParserRuleCall_1_0_0_0();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getTermAccess().getOpMul_op_divParserRuleCall_1_0_0_1(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getTermAccess().getOpMul_op_divParserRuleCall_1_0_0_1();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getTermAccess().getOpMul_op_dotmulParserRuleCall_1_0_0_2(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getTermAccess().getOpMul_op_dotmulParserRuleCall_1_0_0_2();
			return obj;
		}
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getTermAccess().getOpMul_op_dotdivParserRuleCall_1_0_0_3(), value, null)) {
			type = AssignmentType.DATATYPE_RULE_CALL;
			element = grammarAccess.getTermAccess().getOpMul_op_dotdivParserRuleCall_1_0_0_3();
			return obj;
		}
		return null;
	}

}

// Factor+=factor
protected class Term_FactorAssignment_1_1 extends AssignmentToken  {
	
	public Term_FactorAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getTermAccess().getFactorAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Factor_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Factor",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Factor");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFactorRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getTermAccess().getFactorFactorParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Term_OpAssignment_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule term ****************/


/************ begin Rule factor ****************
 *
 * factor:
 * 	primary (("^" | ".^") Primary=primary)?;
 *
 **/

// primary (("^" | ".^") Primary=primary)?
protected class Factor_Group extends GroupToken {
	
	public Factor_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFactorAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Factor_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Factor_PrimaryParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getExprDerRule().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getNameRule().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// primary
protected class Factor_PrimaryParserRuleCall_0 extends RuleCallToken {
	
	public Factor_PrimaryParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getFactorAccess().getPrimaryParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Primary_Alternatives.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// (("^" | ".^") Primary=primary)?
protected class Factor_Group_1 extends GroupToken {
	
	public Factor_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFactorAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Factor_PrimaryAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "^" | ".^"
protected class Factor_Alternatives_1_0 extends AlternativesToken {

	public Factor_Alternatives_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getFactorAccess().getAlternatives_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Factor_CircumflexAccentKeyword_1_0_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "^"
protected class Factor_CircumflexAccentKeyword_1_0_0 extends KeywordToken  {
	
	public Factor_CircumflexAccentKeyword_1_0_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFactorAccess().getCircumflexAccentKeyword_1_0_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Factor_PrimaryParserRuleCall_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// Primary=primary
protected class Factor_PrimaryAssignment_1_1 extends AssignmentToken  {
	
	public Factor_PrimaryAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFactorAccess().getPrimaryAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Primary",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Primary");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getPrimaryRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFactorAccess().getPrimaryPrimaryParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Factor_Alternatives_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule factor ****************/


/************ begin Rule primary ****************
 *
 * //////////////////////////////////////////////////Primary///////////////////////////////
 * primary:
 * 	num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | name_Function | initial_ref | ExprDer |
 * 	Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
 * 	Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
 * 	End="end";
 *
 **/

// num=UNSIGNED_NUMBER | int=INT | str=STRING | Bool=BOOL_VAL | name_Function | initial_ref | ExprDer |
// Component_reference=component_reference | "(" output_expr_list=output_expression_list ")" | "["
// Expre_list=expression_list (";" Expression_list+=expression_list)* "]" | "{" f_arguments=function_arguments "}" |
// End="end"
protected class Primary_Alternatives extends AlternativesToken {

	public Primary_Alternatives(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getAlternatives();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_NumAssignment_0(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Primary_IntAssignment_1(lastRuleCallOrigin, this, 1, inst);
			case 2: return new Primary_StrAssignment_2(lastRuleCallOrigin, this, 2, inst);
			case 3: return new Primary_BoolAssignment_3(lastRuleCallOrigin, this, 3, inst);
			case 4: return new Primary_Name_FunctionParserRuleCall_4(lastRuleCallOrigin, this, 4, inst);
			case 5: return new Primary_Initial_refParserRuleCall_5(lastRuleCallOrigin, this, 5, inst);
			case 6: return new Primary_ExprDerParserRuleCall_6(lastRuleCallOrigin, this, 6, inst);
			case 7: return new Primary_Component_referenceAssignment_7(lastRuleCallOrigin, this, 7, inst);
			case 8: return new Primary_Group_8(lastRuleCallOrigin, this, 8, inst);
			case 9: return new Primary_Group_9(lastRuleCallOrigin, this, 9, inst);
			case 10: return new Primary_Group_10(lastRuleCallOrigin, this, 10, inst);
			case 11: return new Primary_EndAssignment_11(lastRuleCallOrigin, this, 11, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getExprDerRule().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getNameRule().getType().getClassifier() && 
		   getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// num=UNSIGNED_NUMBER
protected class Primary_NumAssignment_0 extends AssignmentToken  {
	
	public Primary_NumAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getNumAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		if((value = eObjectConsumer.getConsumable("num",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("num");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getPrimaryAccess().getNumUNSIGNED_NUMBERTerminalRuleCall_0_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getPrimaryAccess().getNumUNSIGNED_NUMBERTerminalRuleCall_0_0();
			return obj;
		}
		return null;
	}

}

// int=INT
protected class Primary_IntAssignment_1 extends AssignmentToken  {
	
	public Primary_IntAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getIntAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		if((value = eObjectConsumer.getConsumable("int",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("int");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getPrimaryAccess().getIntINTTerminalRuleCall_1_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getPrimaryAccess().getIntINTTerminalRuleCall_1_0();
			return obj;
		}
		return null;
	}

}

// str=STRING
protected class Primary_StrAssignment_2 extends AssignmentToken  {
	
	public Primary_StrAssignment_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getStrAssignment_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		if((value = eObjectConsumer.getConsumable("str",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("str");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getPrimaryAccess().getStrSTRINGTerminalRuleCall_2_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getPrimaryAccess().getStrSTRINGTerminalRuleCall_2_0();
			return obj;
		}
		return null;
	}

}

// Bool=BOOL_VAL
protected class Primary_BoolAssignment_3 extends AssignmentToken  {
	
	public Primary_BoolAssignment_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getBoolAssignment_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		if((value = eObjectConsumer.getConsumable("Bool",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Bool");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getPrimaryAccess().getBoolBOOL_VALTerminalRuleCall_3_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getPrimaryAccess().getBoolBOOL_VALTerminalRuleCall_3_0();
			return obj;
		}
		return null;
	}

}

// name_Function
protected class Primary_Name_FunctionParserRuleCall_4 extends RuleCallToken {
	
	public Primary_Name_FunctionParserRuleCall_4(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getName_FunctionParserRuleCall_4();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Name_Function_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getNameRule().getType().getClassifier())
			return null;
		if(checkForRecursion(Name_Function_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// initial_ref
protected class Primary_Initial_refParserRuleCall_5 extends RuleCallToken {
	
	public Primary_Initial_refParserRuleCall_5(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getInitial_refParserRuleCall_5();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Initial_ref_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0().getType().getClassifier())
			return null;
		if(checkForRecursion(Initial_ref_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// ExprDer
protected class Primary_ExprDerParserRuleCall_6 extends RuleCallToken {
	
	public Primary_ExprDerParserRuleCall_6(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getExprDerParserRuleCall_6();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new ExprDer_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getExprDerRule().getType().getClassifier())
			return null;
		if(checkForRecursion(ExprDer_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// Component_reference=component_reference
protected class Primary_Component_referenceAssignment_7 extends AssignmentToken  {
	
	public Primary_Component_referenceAssignment_7(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getComponent_referenceAssignment_7();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		if((value = eObjectConsumer.getConsumable("Component_reference",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Component_reference");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getComponent_referenceRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getPrimaryAccess().getComponent_referenceComponent_referenceParserRuleCall_7_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}

// "(" output_expr_list=output_expression_list ")"
protected class Primary_Group_8 extends GroupToken {
	
	public Primary_Group_8(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getGroup_8();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_RightParenthesisKeyword_8_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "("
protected class Primary_LeftParenthesisKeyword_8_0 extends KeywordToken  {
	
	public Primary_LeftParenthesisKeyword_8_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_8_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// output_expr_list=output_expression_list
protected class Primary_Output_expr_listAssignment_8_1 extends AssignmentToken  {
	
	public Primary_Output_expr_listAssignment_8_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getOutput_expr_listAssignment_8_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Output_expression_list_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("output_expr_list",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("output_expr_list");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getOutput_expression_listRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getPrimaryAccess().getOutput_expr_listOutput_expression_listParserRuleCall_8_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Primary_LeftParenthesisKeyword_8_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ")"
protected class Primary_RightParenthesisKeyword_8_2 extends KeywordToken  {
	
	public Primary_RightParenthesisKeyword_8_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_8_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_Output_expr_listAssignment_8_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// "[" Expre_list=expression_list (";" Expression_list+=expression_list)* "]"
protected class Primary_Group_9 extends GroupToken {
	
	public Primary_Group_9(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getGroup_9();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_RightSquareBracketKeyword_9_3(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "["
protected class Primary_LeftSquareBracketKeyword_9_0 extends KeywordToken  {
	
	public Primary_LeftSquareBracketKeyword_9_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getLeftSquareBracketKeyword_9_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// Expre_list=expression_list
protected class Primary_Expre_listAssignment_9_1 extends AssignmentToken  {
	
	public Primary_Expre_listAssignment_9_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getExpre_listAssignment_9_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_list_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Expre_list",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Expre_list");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpression_listRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getPrimaryAccess().getExpre_listExpression_listParserRuleCall_9_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Primary_LeftSquareBracketKeyword_9_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// (";" Expression_list+=expression_list)*
protected class Primary_Group_9_2 extends GroupToken {
	
	public Primary_Group_9_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getGroup_9_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_Expression_listAssignment_9_2_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ";"
protected class Primary_SemicolonKeyword_9_2_0 extends KeywordToken  {
	
	public Primary_SemicolonKeyword_9_2_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getSemicolonKeyword_9_2_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_Group_9_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Primary_Expre_listAssignment_9_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// Expression_list+=expression_list
protected class Primary_Expression_listAssignment_9_2_1 extends AssignmentToken  {
	
	public Primary_Expression_listAssignment_9_2_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getExpression_listAssignment_9_2_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_list_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Expression_list",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Expression_list");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpression_listRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getPrimaryAccess().getExpression_listExpression_listParserRuleCall_9_2_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Primary_SemicolonKeyword_9_2_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


// "]"
protected class Primary_RightSquareBracketKeyword_9_3 extends KeywordToken  {
	
	public Primary_RightSquareBracketKeyword_9_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getRightSquareBracketKeyword_9_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_Group_9_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Primary_Expre_listAssignment_9_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}


// "{" f_arguments=function_arguments "}"
protected class Primary_Group_10 extends GroupToken {
	
	public Primary_Group_10(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getGroup_10();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_RightCurlyBracketKeyword_10_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "{"
protected class Primary_LeftCurlyBracketKeyword_10_0 extends KeywordToken  {
	
	public Primary_LeftCurlyBracketKeyword_10_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getLeftCurlyBracketKeyword_10_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// f_arguments=function_arguments
protected class Primary_F_argumentsAssignment_10_1 extends AssignmentToken  {
	
	public Primary_F_argumentsAssignment_10_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getF_argumentsAssignment_10_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_arguments_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("f_arguments",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("f_arguments");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFunction_argumentsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getPrimaryAccess().getF_argumentsFunction_argumentsParserRuleCall_10_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Primary_LeftCurlyBracketKeyword_10_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// "}"
protected class Primary_RightCurlyBracketKeyword_10_2 extends KeywordToken  {
	
	public Primary_RightCurlyBracketKeyword_10_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getRightCurlyBracketKeyword_10_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Primary_F_argumentsAssignment_10_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// End="end"
protected class Primary_EndAssignment_11 extends AssignmentToken  {
	
	public Primary_EndAssignment_11(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getPrimaryAccess().getEndAssignment_11();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getPrimaryRule().getType().getClassifier())
			return null;
		if((value = eObjectConsumer.getConsumable("End",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("End");
		if(keywordSerializer.isValid(obj.getEObject(), grammarAccess.getPrimaryAccess().getEndEndKeyword_11_0(), value, null)) {
			type = AssignmentType.KEYWORD;
			element = grammarAccess.getPrimaryAccess().getEndEndKeyword_11_0();
			return obj;
		}
		return null;
	}

}


/************ end Rule primary ****************/


/************ begin Rule name_Function ****************
 *
 * name_Function:
 * 	name Function_call_args=function_call_args;
 *
 **/

// name Function_call_args=function_call_args
protected class Name_Function_Group extends GroupToken {
	
	public Name_Function_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getName_FunctionAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Name_Function_Function_call_argsAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getNameRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// name
protected class Name_Function_NameParserRuleCall_0 extends RuleCallToken {
	
	public Name_Function_NameParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getName_FunctionAccess().getNameParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Name_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Name_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// Function_call_args=function_call_args
protected class Name_Function_Function_call_argsAssignment_1 extends AssignmentToken  {
	
	public Name_Function_Function_call_argsAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getName_FunctionAccess().getFunction_call_argsAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Function_call_args",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Function_call_args");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFunction_call_argsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getName_FunctionAccess().getFunction_call_argsFunction_call_argsParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Name_Function_NameParserRuleCall_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


/************ end Rule name_Function ****************/


/************ begin Rule initial_ref ****************
 *
 * initial_ref:
 * 	"initial" function_call_args;
 *
 **/

// "initial" function_call_args
protected class Initial_ref_Group extends GroupToken {
	
	public Initial_ref_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getInitial_refAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Initial_ref_Function_call_argsParserRuleCall_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "initial"
protected class Initial_ref_InitialKeyword_0 extends KeywordToken  {
	
	public Initial_ref_InitialKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getInitial_refAccess().getInitialKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// function_call_args
protected class Initial_ref_Function_call_argsParserRuleCall_1 extends RuleCallToken {
	
	public Initial_ref_Function_call_argsParserRuleCall_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getInitial_refAccess().getFunction_call_argsParserRuleCall_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Function_call_args_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Initial_ref_InitialKeyword_0(lastRuleCallOrigin, next, actIndex, inst);
			default: return null;
		}	
	}	
}


/************ end Rule initial_ref ****************/


/************ begin Rule ExprDer ****************
 *
 * ExprDer:
 * 	"der" functionArgs=function_call_args;
 *
 **/

// "der" functionArgs=function_call_args
protected class ExprDer_Group extends GroupToken {
	
	public ExprDer_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getExprDerAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new ExprDer_FunctionArgsAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getExprDerRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "der"
protected class ExprDer_DerKeyword_0 extends KeywordToken  {
	
	public ExprDer_DerKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getExprDerAccess().getDerKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// functionArgs=function_call_args
protected class ExprDer_FunctionArgsAssignment_1 extends AssignmentToken  {
	
	public ExprDer_FunctionArgsAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getExprDerAccess().getFunctionArgsAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("functionArgs",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("functionArgs");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFunction_call_argsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getExprDerAccess().getFunctionArgsFunction_call_argsParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new ExprDer_DerKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


/************ end Rule ExprDer ****************/


/************ begin Rule function_call_args ****************
 *
 * function_call_args:
 * 	{function_call_args} "(" f_arg=function_arguments? ")";
 *
 **/

// {function_call_args} "(" f_arg=function_arguments? ")"
protected class Function_call_args_Group extends GroupToken {
	
	public Function_call_args_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFunction_call_argsAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_RightParenthesisKeyword_3(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// {function_call_args}
protected class Function_call_args_Function_call_argsAction_0 extends ActionToken  {

	public Function_call_args_Function_call_argsAction_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Action getGrammarElement() {
		return grammarAccess.getFunction_call_argsAccess().getFunction_call_argsAction_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(!eObjectConsumer.isConsumed()) return null;
		return eObjectConsumer;
	}
}

// "("
protected class Function_call_args_LeftParenthesisKeyword_1 extends KeywordToken  {
	
	public Function_call_args_LeftParenthesisKeyword_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFunction_call_argsAccess().getLeftParenthesisKeyword_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_Function_call_argsAction_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// f_arg=function_arguments?
protected class Function_call_args_F_argAssignment_2 extends AssignmentToken  {
	
	public Function_call_args_F_argAssignment_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFunction_call_argsAccess().getF_argAssignment_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_arguments_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("f_arg",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("f_arg");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFunction_argumentsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFunction_call_argsAccess().getF_argFunction_argumentsParserRuleCall_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Function_call_args_LeftParenthesisKeyword_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ")"
protected class Function_call_args_RightParenthesisKeyword_3 extends KeywordToken  {
	
	public Function_call_args_RightParenthesisKeyword_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFunction_call_argsAccess().getRightParenthesisKeyword_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_call_args_F_argAssignment_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Function_call_args_LeftParenthesisKeyword_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}


/************ end Rule function_call_args ****************/


/************ begin Rule expression_list ****************
 *
 * expression_list:
 * 	expr=expression ("," Expre+=expression)*;
 *
 **/

// expr=expression ("," Expre+=expression)*
protected class Expression_list_Group extends GroupToken {
	
	public Expression_list_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getExpression_listAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_list_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Expression_list_ExprAssignment_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getExpression_listRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// expr=expression
protected class Expression_list_ExprAssignment_0 extends AssignmentToken  {
	
	public Expression_list_ExprAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getExpression_listAccess().getExprAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getExpression_listAccess().getExprExpressionParserRuleCall_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}

// ("," Expre+=expression)*
protected class Expression_list_Group_1 extends GroupToken {
	
	public Expression_list_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getExpression_listAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_list_ExpreAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ","
protected class Expression_list_CommaKeyword_1_0 extends KeywordToken  {
	
	public Expression_list_CommaKeyword_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getExpression_listAccess().getCommaKeyword_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_list_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Expression_list_ExprAssignment_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// Expre+=expression
protected class Expression_list_ExpreAssignment_1_1 extends AssignmentToken  {
	
	public Expression_list_ExpreAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getExpression_listAccess().getExpreAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Expre",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Expre");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getExpression_listAccess().getExpreExpressionParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Expression_list_CommaKeyword_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule expression_list ****************/


/************ begin Rule name ****************
 *
 * ///////////////////////////////Component Reference////////////////////////
 * name:
 * 	"."? name_ID=IDENT ("." nam_ID+=IDENT)*;
 *
 **/

// "."? name_ID=IDENT ("." nam_ID+=IDENT)*
protected class Name_Group extends GroupToken {
	
	public Name_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getNameAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Name_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Name_Name_IDAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getNameRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// name_ID=IDENT
protected class Name_Name_IDAssignment_1 extends AssignmentToken  {
	
	public Name_Name_IDAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getNameAccess().getName_IDAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("name_ID",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("name_ID");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getNameAccess().getName_IDIDENTTerminalRuleCall_1_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getNameAccess().getName_IDIDENTTerminalRuleCall_1_0();
			return obj;
		}
		return null;
	}

}

// ("." nam_ID+=IDENT)*
protected class Name_Group_2 extends GroupToken {
	
	public Name_Group_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getNameAccess().getGroup_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Name_Nam_IDAssignment_2_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "."
protected class Name_FullStopKeyword_2_0 extends KeywordToken  {
	
	public Name_FullStopKeyword_2_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getNameAccess().getFullStopKeyword_2_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Name_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Name_Name_IDAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// nam_ID+=IDENT
protected class Name_Nam_IDAssignment_2_1 extends AssignmentToken  {
	
	public Name_Nam_IDAssignment_2_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getNameAccess().getNam_IDAssignment_2_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Name_FullStopKeyword_2_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("nam_ID",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("nam_ID");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getNameAccess().getNam_IDIDENTTerminalRuleCall_2_1_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getNameAccess().getNam_IDIDENTTerminalRuleCall_2_1_0();
			return obj;
		}
		return null;
	}

}



/************ end Rule name ****************/


/************ begin Rule component_reference ****************
 *
 * component_reference:
 * 	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
 *
 **/

// "."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*
protected class Component_reference_Group extends GroupToken {
	
	public Component_reference_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getComponent_referenceAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group_3(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Component_reference_Subscripts1Assignment_2(lastRuleCallOrigin, this, 1, inst);
			case 2: return new Component_reference_RefAssignment_1(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getComponent_referenceRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// ref=IDENT
protected class Component_reference_RefAssignment_1 extends AssignmentToken  {
	
	public Component_reference_RefAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getComponent_referenceAccess().getRefAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("ref",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("ref");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getComponent_referenceAccess().getRefIDENTTerminalRuleCall_1_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getComponent_referenceAccess().getRefIDENTTerminalRuleCall_1_0();
			return obj;
		}
		return null;
	}

}

// subscripts1=array_subscripts?
protected class Component_reference_Subscripts1Assignment_2 extends AssignmentToken  {
	
	public Component_reference_Subscripts1Assignment_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getComponent_referenceAccess().getSubscripts1Assignment_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Array_subscripts_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("subscripts1",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("subscripts1");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getArray_subscriptsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getComponent_referenceAccess().getSubscripts1Array_subscriptsParserRuleCall_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Component_reference_RefAssignment_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ("." ref1+=IDENT subscripts+=array_subscripts?)*
protected class Component_reference_Group_3 extends GroupToken {
	
	public Component_reference_Group_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getComponent_referenceAccess().getGroup_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_SubscriptsAssignment_3_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Component_reference_Ref1Assignment_3_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// "."
protected class Component_reference_FullStopKeyword_3_0 extends KeywordToken  {
	
	public Component_reference_FullStopKeyword_3_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getComponent_referenceAccess().getFullStopKeyword_3_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_Group_3(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Component_reference_Subscripts1Assignment_2(lastRuleCallOrigin, this, 1, inst);
			case 2: return new Component_reference_RefAssignment_1(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

}

// ref1+=IDENT
protected class Component_reference_Ref1Assignment_3_1 extends AssignmentToken  {
	
	public Component_reference_Ref1Assignment_3_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getComponent_referenceAccess().getRef1Assignment_3_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Component_reference_FullStopKeyword_3_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("ref1",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("ref1");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getComponent_referenceAccess().getRef1IDENTTerminalRuleCall_3_1_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getComponent_referenceAccess().getRef1IDENTTerminalRuleCall_3_1_0();
			return obj;
		}
		return null;
	}

}

// subscripts+=array_subscripts?
protected class Component_reference_SubscriptsAssignment_3_2 extends AssignmentToken  {
	
	public Component_reference_SubscriptsAssignment_3_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getComponent_referenceAccess().getSubscriptsAssignment_3_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Array_subscripts_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("subscripts",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("subscripts");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getArray_subscriptsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getComponent_referenceAccess().getSubscriptsArray_subscriptsParserRuleCall_3_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Component_reference_Ref1Assignment_3_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule component_reference ****************/


/************ begin Rule output_expression_list ****************
 *
 * output_expression_list:
 * 	{output_expression_list} epr=expression? ("," Expr+=expression?)*;
 *
 **/

// {output_expression_list} epr=expression? ("," Expr+=expression?)*
protected class Output_expression_list_Group extends GroupToken {
	
	public Output_expression_list_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getOutput_expression_listAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Output_expression_list_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Output_expression_list_EprAssignment_1(lastRuleCallOrigin, this, 1, inst);
			case 2: return new Output_expression_list_Output_expression_listAction_0(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getOutput_expression_listAccess().getOutput_expression_listAction_0().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// {output_expression_list}
protected class Output_expression_list_Output_expression_listAction_0 extends ActionToken  {

	public Output_expression_list_Output_expression_listAction_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Action getGrammarElement() {
		return grammarAccess.getOutput_expression_listAccess().getOutput_expression_listAction_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(!eObjectConsumer.isConsumed()) return null;
		return eObjectConsumer;
	}
}

// epr=expression?
protected class Output_expression_list_EprAssignment_1 extends AssignmentToken  {
	
	public Output_expression_list_EprAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getOutput_expression_listAccess().getEprAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("epr",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("epr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getOutput_expression_listAccess().getEprExpressionParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Output_expression_list_Output_expression_listAction_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ("," Expr+=expression?)*
protected class Output_expression_list_Group_2 extends GroupToken {
	
	public Output_expression_list_Group_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getOutput_expression_listAccess().getGroup_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Output_expression_list_ExprAssignment_2_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ","
protected class Output_expression_list_CommaKeyword_2_0 extends KeywordToken  {
	
	public Output_expression_list_CommaKeyword_2_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getOutput_expression_listAccess().getCommaKeyword_2_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Output_expression_list_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Output_expression_list_EprAssignment_1(lastRuleCallOrigin, this, 1, inst);
			case 2: return new Output_expression_list_Output_expression_listAction_0(lastRuleCallOrigin, this, 2, inst);
			default: return null;
		}	
	}

}

// Expr+=expression?
protected class Output_expression_list_ExprAssignment_2_1 extends AssignmentToken  {
	
	public Output_expression_list_ExprAssignment_2_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getOutput_expression_listAccess().getExprAssignment_2_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Expr",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getOutput_expression_listAccess().getExprExpressionParserRuleCall_2_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Output_expression_list_CommaKeyword_2_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule output_expression_list ****************/


/************ begin Rule array_subscripts ****************
 *
 * array_subscripts:
 * 	"[" Sub=subscript ("," Subscript+=subscript)* "]";
 *
 **/

// "[" Sub=subscript ("," Subscript+=subscript)* "]"
protected class Array_subscripts_Group extends GroupToken {
	
	public Array_subscripts_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getArray_subscriptsAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Array_subscripts_RightSquareBracketKeyword_3(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getArray_subscriptsRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "["
protected class Array_subscripts_LeftSquareBracketKeyword_0 extends KeywordToken  {
	
	public Array_subscripts_LeftSquareBracketKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getArray_subscriptsAccess().getLeftSquareBracketKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// Sub=subscript
protected class Array_subscripts_SubAssignment_1 extends AssignmentToken  {
	
	public Array_subscripts_SubAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getArray_subscriptsAccess().getSubAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Subscript_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Sub",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Sub");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getSubscriptRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getArray_subscriptsAccess().getSubSubscriptParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Array_subscripts_LeftSquareBracketKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// ("," Subscript+=subscript)*
protected class Array_subscripts_Group_2 extends GroupToken {
	
	public Array_subscripts_Group_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getArray_subscriptsAccess().getGroup_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Array_subscripts_SubscriptAssignment_2_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ","
protected class Array_subscripts_CommaKeyword_2_0 extends KeywordToken  {
	
	public Array_subscripts_CommaKeyword_2_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getArray_subscriptsAccess().getCommaKeyword_2_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Array_subscripts_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Array_subscripts_SubAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// Subscript+=subscript
protected class Array_subscripts_SubscriptAssignment_2_1 extends AssignmentToken  {
	
	public Array_subscripts_SubscriptAssignment_2_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getArray_subscriptsAccess().getSubscriptAssignment_2_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Subscript_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Subscript",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Subscript");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getSubscriptRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getArray_subscriptsAccess().getSubscriptSubscriptParserRuleCall_2_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Array_subscripts_CommaKeyword_2_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


// "]"
protected class Array_subscripts_RightSquareBracketKeyword_3 extends KeywordToken  {
	
	public Array_subscripts_RightSquareBracketKeyword_3(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getArray_subscriptsAccess().getRightSquareBracketKeyword_3();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Array_subscripts_Group_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Array_subscripts_SubAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}


/************ end Rule array_subscripts ****************/


/************ begin Rule subscript ****************
 *
 * subscript:
 * 	{subscript} ":" | expr=expression;
 *
 **/

// {subscript} ":" | expr=expression
protected class Subscript_Alternatives extends AlternativesToken {

	public Subscript_Alternatives(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getSubscriptAccess().getAlternatives();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Subscript_Group_0(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Subscript_ExprAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getSubscriptRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// {subscript} ":"
protected class Subscript_Group_0 extends GroupToken {
	
	public Subscript_Group_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getSubscriptAccess().getGroup_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Subscript_ColonKeyword_0_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// {subscript}
protected class Subscript_SubscriptAction_0_0 extends ActionToken  {

	public Subscript_SubscriptAction_0_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Action getGrammarElement() {
		return grammarAccess.getSubscriptAccess().getSubscriptAction_0_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(!eObjectConsumer.isConsumed()) return null;
		return eObjectConsumer;
	}
}

// ":"
protected class Subscript_ColonKeyword_0_1 extends KeywordToken  {
	
	public Subscript_ColonKeyword_0_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getSubscriptAccess().getColonKeyword_0_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Subscript_SubscriptAction_0_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}


// expr=expression
protected class Subscript_ExprAssignment_1 extends AssignmentToken  {
	
	public Subscript_ExprAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getSubscriptAccess().getExprAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getSubscriptAccess().getExprExpressionParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}


/************ end Rule subscript ****************/


/************ begin Rule function_arguments ****************
 *
 * function_arguments:
 * 	{function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)? |
 * 	name_arg=named_arguments;
 *
 **/

// {function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)? |
// name_arg=named_arguments
protected class Function_arguments_Alternatives extends AlternativesToken {

	public Function_arguments_Alternatives(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getAlternatives();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_arguments_Group_0(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Function_arguments_Name_argAssignment_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFunction_argumentsRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// {function_arguments} ArgExp+=expression (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)?
protected class Function_arguments_Group_0 extends GroupToken {
	
	public Function_arguments_Group_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getGroup_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_arguments_Alternatives_0_2(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Function_arguments_ArgExpAssignment_0_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// {function_arguments}
protected class Function_arguments_Function_argumentsAction_0_0 extends ActionToken  {

	public Function_arguments_Function_argumentsAction_0_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Action getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getFunction_argumentsAction_0_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(!eObjectConsumer.isConsumed()) return null;
		return eObjectConsumer;
	}
}

// ArgExp+=expression
protected class Function_arguments_ArgExpAssignment_0_1 extends AssignmentToken  {
	
	public Function_arguments_ArgExpAssignment_0_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getArgExpAssignment_0_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("ArgExp",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("ArgExp");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFunction_argumentsAccess().getArgExpExpressionParserRuleCall_0_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Function_arguments_Function_argumentsAction_0_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// (Fun_Arg_Expr=Fun_Arguments_exp | Fun_Arg_For=Fun_Arguments_for)?
protected class Function_arguments_Alternatives_0_2 extends AlternativesToken {

	public Function_arguments_Alternatives_0_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Alternatives getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getAlternatives_0_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_arguments_Fun_Arg_ExprAssignment_0_2_0(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Function_arguments_Fun_Arg_ForAssignment_0_2_1(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// Fun_Arg_Expr=Fun_Arguments_exp
protected class Function_arguments_Fun_Arg_ExprAssignment_0_2_0 extends AssignmentToken  {
	
	public Function_arguments_Fun_Arg_ExprAssignment_0_2_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprAssignment_0_2_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Fun_Arguments_exp_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Fun_Arg_Expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Fun_Arg_Expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFun_Arguments_expRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFunction_argumentsAccess().getFun_Arg_ExprFun_Arguments_expParserRuleCall_0_2_0_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Function_arguments_ArgExpAssignment_0_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}

// Fun_Arg_For=Fun_Arguments_for
protected class Function_arguments_Fun_Arg_ForAssignment_0_2_1 extends AssignmentToken  {
	
	public Function_arguments_Fun_Arg_ForAssignment_0_2_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForAssignment_0_2_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Fun_Arguments_for_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Fun_Arg_For",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Fun_Arg_For");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFun_Arguments_forRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFunction_argumentsAccess().getFun_Arg_ForFun_Arguments_forParserRuleCall_0_2_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Function_arguments_ArgExpAssignment_0_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



// name_arg=named_arguments
protected class Function_arguments_Name_argAssignment_1 extends AssignmentToken  {
	
	public Function_arguments_Name_argAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFunction_argumentsAccess().getName_argAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_arguments_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("name_arg",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("name_arg");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getNamed_argumentsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFunction_argumentsAccess().getName_argNamed_argumentsParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, consumed);
		}	
	}	
}


/************ end Rule function_arguments ****************/


/************ begin Rule Fun_Arguments_exp ****************
 *
 * Fun_Arguments_exp:
 * 	"," Args=function_arguments;
 *
 **/

// "," Args=function_arguments
protected class Fun_Arguments_exp_Group extends GroupToken {
	
	public Fun_Arguments_exp_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFun_Arguments_expAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Fun_Arguments_exp_ArgsAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFun_Arguments_expRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// ","
protected class Fun_Arguments_exp_CommaKeyword_0 extends KeywordToken  {
	
	public Fun_Arguments_exp_CommaKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFun_Arguments_expAccess().getCommaKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// Args=function_arguments
protected class Fun_Arguments_exp_ArgsAssignment_1 extends AssignmentToken  {
	
	public Fun_Arguments_exp_ArgsAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFun_Arguments_expAccess().getArgsAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Function_arguments_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Args",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Args");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFunction_argumentsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFun_Arguments_expAccess().getArgsFunction_argumentsParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Fun_Arguments_exp_CommaKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


/************ end Rule Fun_Arguments_exp ****************/


/************ begin Rule Fun_Arguments_for ****************
 *
 * Fun_Arguments_for:
 * 	"for" For_indices=for_indices;
 *
 **/

// "for" For_indices=for_indices
protected class Fun_Arguments_for_Group extends GroupToken {
	
	public Fun_Arguments_for_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFun_Arguments_forAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Fun_Arguments_for_For_indicesAssignment_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFun_Arguments_forRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// "for"
protected class Fun_Arguments_for_ForKeyword_0 extends KeywordToken  {
	
	public Fun_Arguments_for_ForKeyword_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFun_Arguments_forAccess().getForKeyword_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

}

// For_indices=for_indices
protected class Fun_Arguments_for_For_indicesAssignment_1 extends AssignmentToken  {
	
	public Fun_Arguments_for_For_indicesAssignment_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFun_Arguments_forAccess().getFor_indicesAssignment_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_indices_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("For_indices",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("For_indices");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFor_indicesRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFun_Arguments_forAccess().getFor_indicesFor_indicesParserRuleCall_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Fun_Arguments_for_ForKeyword_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


/************ end Rule Fun_Arguments_for ****************/


/************ begin Rule named_arguments ****************
 *
 * named_arguments:
 * 	named_argument ("," Named_arguments=named_arguments)?;
 *
 **/

// named_argument ("," Named_arguments=named_arguments)?
protected class Named_arguments_Group extends GroupToken {
	
	public Named_arguments_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getNamed_argumentsAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_arguments_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new Named_arguments_Named_argumentParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getNamed_argumentRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// named_argument
protected class Named_arguments_Named_argumentParserRuleCall_0 extends RuleCallToken {
	
	public Named_arguments_Named_argumentParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getNamed_argumentsAccess().getNamed_argumentParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_argument_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(Named_argument_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// ("," Named_arguments=named_arguments)?
protected class Named_arguments_Group_1 extends GroupToken {
	
	public Named_arguments_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getNamed_argumentsAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_arguments_Named_argumentsAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ","
protected class Named_arguments_CommaKeyword_1_0 extends KeywordToken  {
	
	public Named_arguments_CommaKeyword_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getNamed_argumentsAccess().getCommaKeyword_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_arguments_Named_argumentParserRuleCall_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// Named_arguments=named_arguments
protected class Named_arguments_Named_argumentsAssignment_1_1 extends AssignmentToken  {
	
	public Named_arguments_Named_argumentsAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getNamed_argumentsAccess().getNamed_argumentsAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_arguments_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("Named_arguments",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("Named_arguments");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getNamed_argumentsRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getNamed_argumentsAccess().getNamed_argumentsNamed_argumentsParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Named_arguments_CommaKeyword_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule named_arguments ****************/


/************ begin Rule named_argument ****************
 *
 * named_argument:
 * 	arg=IDENT "=" expr=expression;
 *
 **/

// arg=IDENT "=" expr=expression
protected class Named_argument_Group extends GroupToken {
	
	public Named_argument_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getNamed_argumentAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_argument_ExprAssignment_2(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getNamed_argumentRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// arg=IDENT
protected class Named_argument_ArgAssignment_0 extends AssignmentToken  {
	
	public Named_argument_ArgAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getNamed_argumentAccess().getArgAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("arg",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("arg");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getNamed_argumentAccess().getArgIDENTTerminalRuleCall_0_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getNamed_argumentAccess().getArgIDENTTerminalRuleCall_0_0();
			return obj;
		}
		return null;
	}

}

// "="
protected class Named_argument_EqualsSignKeyword_1 extends KeywordToken  {
	
	public Named_argument_EqualsSignKeyword_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getNamed_argumentAccess().getEqualsSignKeyword_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Named_argument_ArgAssignment_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// expr=expression
protected class Named_argument_ExprAssignment_2 extends AssignmentToken  {
	
	public Named_argument_ExprAssignment_2(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getNamed_argumentAccess().getExprAssignment_2();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("expr",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getNamed_argumentAccess().getExprExpressionParserRuleCall_2_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new Named_argument_EqualsSignKeyword_1(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}


/************ end Rule named_argument ****************/


/************ begin Rule for_indices ****************
 *
 * for_indices:
 * 	for_index ("," For_index+=for_index)*;
 *
 **/

// for_index ("," For_index+=for_index)*
protected class For_indices_Group extends GroupToken {
	
	public For_indices_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFor_indicesAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_indices_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new For_indices_For_indexParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFor_indexRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// for_index
protected class For_indices_For_indexParserRuleCall_0 extends RuleCallToken {
	
	public For_indices_For_indexParserRuleCall_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public RuleCall getGrammarElement() {
		return grammarAccess.getFor_indicesAccess().getFor_indexParserRuleCall_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_index_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(checkForRecursion(For_index_Group.class, eObjectConsumer)) return null;
		return eObjectConsumer;
	}
	
    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(next, actIndex , index, inst);
		}	
	}	
}

// ("," For_index+=for_index)*
protected class For_indices_Group_1 extends GroupToken {
	
	public For_indices_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFor_indicesAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_indices_For_indexAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// ","
protected class For_indices_CommaKeyword_1_0 extends KeywordToken  {
	
	public For_indices_CommaKeyword_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFor_indicesAccess().getCommaKeyword_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_indices_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new For_indices_For_indexParserRuleCall_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

}

// For_index+=for_index
protected class For_indices_For_indexAssignment_1_1 extends AssignmentToken  {
	
	public For_indices_For_indexAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFor_indicesAccess().getFor_indexAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_index_Group(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("For_index",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("For_index");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getFor_indexRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFor_indicesAccess().getFor_indexFor_indexParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new For_indices_CommaKeyword_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule for_indices ****************/


/************ begin Rule for_index ****************
 *
 * for_index:
 * 	index=IDENT ("in" expr=expression)?;
 *
 **/

// index=IDENT ("in" expr=expression)?
protected class For_index_Group extends GroupToken {
	
	public For_index_Group(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFor_indexAccess().getGroup();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_index_Group_1(lastRuleCallOrigin, this, 0, inst);
			case 1: return new For_index_IndexAssignment_0(lastRuleCallOrigin, this, 1, inst);
			default: return null;
		}	
	}

    @Override
	public IEObjectConsumer tryConsume() {
		if(getEObject().eClass() != grammarAccess.getFor_indexRule().getType().getClassifier())
			return null;
		return eObjectConsumer;
	}

}

// index=IDENT
protected class For_index_IndexAssignment_0 extends AssignmentToken  {
	
	public For_index_IndexAssignment_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFor_indexAccess().getIndexAssignment_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			default: return lastRuleCallOrigin.createFollowerAfterReturn(this, index, index, inst);
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("index",true)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("index");
		if(valueSerializer.isValid(obj.getEObject(), grammarAccess.getFor_indexAccess().getIndexIDENTTerminalRuleCall_0_0(), value, null)) {
			type = AssignmentType.TERMINAL_RULE_CALL;
			element = grammarAccess.getFor_indexAccess().getIndexIDENTTerminalRuleCall_0_0();
			return obj;
		}
		return null;
	}

}

// ("in" expr=expression)?
protected class For_index_Group_1 extends GroupToken {
	
	public For_index_Group_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Group getGrammarElement() {
		return grammarAccess.getFor_indexAccess().getGroup_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_index_ExprAssignment_1_1(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// "in"
protected class For_index_InKeyword_1_0 extends KeywordToken  {
	
	public For_index_InKeyword_1_0(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Keyword getGrammarElement() {
		return grammarAccess.getFor_indexAccess().getInKeyword_1_0();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new For_index_IndexAssignment_0(lastRuleCallOrigin, this, 0, inst);
			default: return null;
		}	
	}

}

// expr=expression
protected class For_index_ExprAssignment_1_1 extends AssignmentToken  {
	
	public For_index_ExprAssignment_1_1(AbstractToken lastRuleCallOrigin, AbstractToken next, int transitionIndex, IEObjectConsumer eObjectConsumer) {
		super(lastRuleCallOrigin, next, transitionIndex, eObjectConsumer);
	}
	
	@Override
	public Assignment getGrammarElement() {
		return grammarAccess.getFor_indexAccess().getExprAssignment_1_1();
	}

    @Override
	public AbstractToken createFollower(int index, IEObjectConsumer inst) {
		switch(index) {
			case 0: return new Expression_Alternatives(this, this, 0, inst);
			default: return null;
		}	
	}

    @Override	
	public IEObjectConsumer tryConsume() {
		if((value = eObjectConsumer.getConsumable("expr",false)) == null) return null;
		IEObjectConsumer obj = eObjectConsumer.cloneAndConsume("expr");
		if(value instanceof EObject) { // org::eclipse::xtext::impl::RuleCallImpl
			IEObjectConsumer param = createEObjectConsumer((EObject)value);
			if(param.isInstanceOf(grammarAccess.getExpressionRule().getType().getClassifier())) {
				type = AssignmentType.PARSER_RULE_CALL;
				element = grammarAccess.getFor_indexAccess().getExprExpressionParserRuleCall_1_1_0(); 
				consumed = obj;
				return param;
			}
		}
		return null;
	}

    @Override
	public AbstractToken createFollowerAfterReturn(AbstractToken next,	int actIndex, int index, IEObjectConsumer inst) {
		if(value == inst.getEObject() && !inst.isConsumed()) return null;
		switch(index) {
			case 0: return new For_index_InKeyword_1_0(lastRuleCallOrigin, next, actIndex, consumed);
			default: return null;
		}	
	}	
}



/************ end Rule for_index ****************/

















}
