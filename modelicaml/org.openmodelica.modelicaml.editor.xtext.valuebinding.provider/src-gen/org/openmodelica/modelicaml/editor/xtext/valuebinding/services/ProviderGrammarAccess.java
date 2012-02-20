/*
* generated by Xtext
*/

package org.openmodelica.modelicaml.editor.xtext.valuebinding.services;

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
public class ProviderGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class ProviderOperationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ProviderOperation");
		private final Assignment cExprAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cExprExpressionParserRuleCall_0 = (RuleCall)cExprAssignment.eContents().get(0);
		
		//// optional expression
		//ProviderOperation:
		//	Expr=expression?;
		public ParserRule getRule() { return rule; }

		//Expr=expression?
		public Assignment getExprAssignment() { return cExprAssignment; }

		//expression
		public RuleCall getExprExpressionParserRuleCall_0() { return cExprExpressionParserRuleCall_0; }
	}

	public class NameElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "name");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFullStopKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cName_IDAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cName_IDIDENTTerminalRuleCall_1_0 = (RuleCall)cName_IDAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cFullStopKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cNam_IDAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cNam_IDIDENTTerminalRuleCall_2_1_0 = (RuleCall)cNam_IDAssignment_2_1.eContents().get(0);
		
		//// Note: these are identical to modeleditor. It is neccesary to overwrite it in order to be able to modify the code completion and validation.
		//name:
		//	"."? name_ID=IDENT ("." nam_ID+=IDENT)*;
		public ParserRule getRule() { return rule; }

		//"."? name_ID=IDENT ("." nam_ID+=IDENT)*
		public Group getGroup() { return cGroup; }

		//"."?
		public Keyword getFullStopKeyword_0() { return cFullStopKeyword_0; }

		//name_ID=IDENT
		public Assignment getName_IDAssignment_1() { return cName_IDAssignment_1; }

		//IDENT
		public RuleCall getName_IDIDENTTerminalRuleCall_1_0() { return cName_IDIDENTTerminalRuleCall_1_0; }

		//("." nam_ID+=IDENT)*
		public Group getGroup_2() { return cGroup_2; }

		//"."
		public Keyword getFullStopKeyword_2_0() { return cFullStopKeyword_2_0; }

		//nam_ID+=IDENT
		public Assignment getNam_IDAssignment_2_1() { return cNam_IDAssignment_2_1; }

		//IDENT
		public RuleCall getNam_IDIDENTTerminalRuleCall_2_1_0() { return cNam_IDIDENTTerminalRuleCall_2_1_0; }
	}

	public class Component_referenceElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "component_reference");
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
		
		////primary :
		////num=UNSIGNED_NUMBER
		////| int=INT
		////| str=STRING
		////| Bool=BOOL_VAL
		////| Name_Function=name_Function
		////| Initial_ref=initial_ref
		////| Expr=ExprDer
		//////| specificFunction=SpecificFunction // only for Value Provider
		////| Component_reference=component_reference
		////| '(' output_expr_list=output_expression_list ')'
		////| '[' Expre_list=expression_list ( ';' Expression_list+=expression_list)* ']'
		////| '{' f_arguments=function_arguments '}'
		////| End='end'
		////;
		////SpecificFunction : // only for Value Provider
		////	'getOwnPath' Brackets
		////	;
		////
		////Brackets : 
		////	'(' ')';
		//// override the definition from the reused grammar.
		////name : {name}
		////	('.')? name_ID=IDENT ( '.' nam_ID+=IDENT )*
		////	| ('.')? name_ID=SpecificFunction ( '.' nam_ID+=IDENT )*
		////;
		////component_reference:
		////	ref=IDENT (subscripts1=array_subscripts)? ('.' ref1+=IDENT (subscripts+=array_subscripts)?)*
		////	| ref=SpecificFunction (subscripts1=array_subscripts)? ('.' ref1+=IDENT (subscripts+=array_subscripts)?)*
		////	| ('.')? ref=IDENT (subscripts1=array_subscripts)? ('.' ref1+=IDENT (subscripts+=array_subscripts)?)*
		////	; component_reference:
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
	
	
	private ProviderOperationElements pProviderOperation;
	private NameElements pName;
	private Component_referenceElements pComponent_reference;
	
	private final GrammarProvider grammarProvider;

	private ModeleditorGrammarAccess gaModeleditor;

	@Inject
	public ProviderGrammarAccess(GrammarProvider grammarProvider,
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

	
	//// optional expression
	//ProviderOperation:
	//	Expr=expression?;
	public ProviderOperationElements getProviderOperationAccess() {
		return (pProviderOperation != null) ? pProviderOperation : (pProviderOperation = new ProviderOperationElements());
	}
	
	public ParserRule getProviderOperationRule() {
		return getProviderOperationAccess().getRule();
	}

	//// Note: these are identical to modeleditor. It is neccesary to overwrite it in order to be able to modify the code completion and validation.
	//name:
	//	"."? name_ID=IDENT ("." nam_ID+=IDENT)*;
	public NameElements getNameAccess() {
		return (pName != null) ? pName : (pName = new NameElements());
	}
	
	public ParserRule getNameRule() {
		return getNameAccess().getRule();
	}

	////primary :
	////num=UNSIGNED_NUMBER
	////| int=INT
	////| str=STRING
	////| Bool=BOOL_VAL
	////| Name_Function=name_Function
	////| Initial_ref=initial_ref
	////| Expr=ExprDer
	//////| specificFunction=SpecificFunction // only for Value Provider
	////| Component_reference=component_reference
	////| '(' output_expr_list=output_expression_list ')'
	////| '[' Expre_list=expression_list ( ';' Expression_list+=expression_list)* ']'
	////| '{' f_arguments=function_arguments '}'
	////| End='end'
	////;
	////SpecificFunction : // only for Value Provider
	////	'getOwnPath' Brackets
	////	;
	////
	////Brackets : 
	////	'(' ')';
	//// override the definition from the reused grammar.
	////name : {name}
	////	('.')? name_ID=IDENT ( '.' nam_ID+=IDENT )*
	////	| ('.')? name_ID=SpecificFunction ( '.' nam_ID+=IDENT )*
	////;
	////component_reference:
	////	ref=IDENT (subscripts1=array_subscripts)? ('.' ref1+=IDENT (subscripts+=array_subscripts)?)*
	////	| ref=SpecificFunction (subscripts1=array_subscripts)? ('.' ref1+=IDENT (subscripts+=array_subscripts)?)*
	////	| ('.')? ref=IDENT (subscripts1=array_subscripts)? ('.' ref1+=IDENT (subscripts+=array_subscripts)?)*
	////	; component_reference:
	//	"."? ref=IDENT subscripts1=array_subscripts? ("." ref1+=IDENT subscripts+=array_subscripts?)*;
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
