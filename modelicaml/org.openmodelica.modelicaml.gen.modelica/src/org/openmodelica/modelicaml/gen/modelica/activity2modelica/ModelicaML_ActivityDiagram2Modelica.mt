<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLMacros

%>

<%script type="uml.Activity" name="Activity2ModelicaCode"%>

// code generated from the Activity "<%name%>" (<%getAppliedStereotypes().name%>)

<%if (hasStereotype(getProperty("s_conditionalAlgorithm"))){%>
	<%if (getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_loopKind")).name != "" ){%>
		<%if (getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_loopKind")).name == "for"){%>
			<%if (getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_forIndices")) != ""){%>
		for <%getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_forIndices"))%> loop
		<%ConditionalActivity.trim()%>
		end for;
			<%}else{%>
			ERROR: No for-indices are defined for the Activity "<%name%>".
			<%}%>
		<%}else{%>
			<%if (getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_loopKind")).name == "while"){%>
				<%if (getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_whileExpression")) != ""){%>
			while <%getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_whileExpression"))%> loop
			<%ConditionalActivity.trim()%>
			end while;	
				<%}else{%>
				ERROR: No while-expression is defined for the Activity "<%name%>".
				<%}%>
			<%}%>
		<%}%>
	<%}else{%>
<%ConditionalActivity.trim()%>
	<%}%>
<%}%>
<%if (hasStereotype(getProperty("s_conditionalEquation"))){%>
	<%if (getStereotypeValue(getProperty("s_conditionalEquation"), getProperty("s_p_loopKind")).name != "" ){%>
		<%if (getStereotypeValue(getProperty("s_conditionalEquation"), getProperty("s_p_loopKind")).name == "for"){%>
			<%if (getStereotypeValue(getProperty("s_conditionalEquation"), getProperty("s_p_forIndices")) != "" ){%>
		for <%getStereotypeValue(getProperty("s_conditionalEquation"), getProperty("s_p_forIndices"))%> loop
		<%ConditionalEquations.trim()%>
		end for;
			<%}else{%>
			ERROR: No for-indices are defined for the Activity "<%name%>".
			<%}%>
		<%}%>
	<%}else{%>
<%ConditionalEquations.trim()%>
	<%}%>
<%}%>


<%script type="uml.Activity" name="ConditionalActivity"%>
<%if (ownedElement.filter("InitialNode").outgoing.nSize() == 1){%>
<%ownedElement.filter("InitialNode").outgoing.filter("ControlFlow").target.filter("ActivityNode").determineControlNodeType.trim()%>
<%}else{%>
ERROR(e0): Invalid InitialNode. Only one InitialNode with excatly one outgoing Transition (without guard) is allowed.
<%}%>



<%script type="uml.OpaqueAction" name="OpaqueActionCode"%>
// OpaqueAction: "<%activity.name%>.<%name%>"
<%body.expandGENCodeMacros()%> 
<%if (outgoing.filter("ControlFlow").nSize() > 1){%>
ERROR(e1): Excactly one outgoing ControlFlow is allowed for OpaqueActions. ("<%name%>")
<%}else{%>
	<%for (outgoing.filter("ControlFlow")){%>
<%target.filter("ActivityNode").determineControlNodeType.trim()%>
	<%}%>
<%}%>



<%script type="uml.ActivityNode" name="determineControlNodeType"%>
<%if (eClass().name == "OpaqueAction"){%>
<%filter("OpaqueAction").OpaqueActionCode.trim()%>
<%}%>
<%if (eClass().name == "DecisionNode"){%>
<%filter("DecisionNode").DecisionNodeCode.trim()%>
<%}%>
<%if (eClass().name == "MergeNode"){%>
<%filter("MergeNode").MergeNodeCode.trim()%>
<%}%>
<%if (eClass().name == "CallBehaviorAction"){%>
<%if (filter("CallBehaviorAction").behavior.hasStereotype(getProperty("s_conditionalAlgorithm")) && filter("CallBehaviorAction").behavior.eClass().name == "Activity"){%>
<%filter("CallBehaviorAction").behavior.filter("Activity").ConditionalActivity.trim()%>
<%}else{%>
ERROR: Only Activities with the stereotype "ConditionalAlgorithm(Diagram)" are allowed as behavior of this CallBehaviorAction.
<%}%>
<%}%>




<%script type="uml.DecisionNode" name="DecisionNodeCode"%>
<%if (outgoing[guard.filter("LiteralString").value.trim()=="else"] !=null){%>
	// if/when-else code
<%DecisionNodeCode_if_when_else.trim()%>
<%}else{%>
	// if/when code
<%DecisionNodeCode_if_when.trim()%>
<%}%>




<%script type="uml.DecisionNode" name="DecisionNodeCode_if_when_else"%>
<%for (outgoing[guard.filter("LiteralString").value.trim()!="else"].filter("ControlFlow").sortControlFlows().nGet(0)){%>
	<%if (source.filter("DecisionNode").hasStereotype(getProperty("s_if"))){%>if<%}%><%if (source.filter("DecisionNode").hasStereotype(getProperty("s_when"))){%>when<%}%> <%Guards.trim()%> then
		<%target.filter("ActivityNode").determineControlNodeType.trim()%>
<%}%>
<%for (outgoing[guard.filter("LiteralString").value.trim()!="else"].filter("ControlFlow").sortControlFlows().nGet(1, outgoing[guard.filter("LiteralString").value.trim()!="else"].length())){%>
	else<%if (source.filter("DecisionNode").hasStereotype(getProperty("s_if"))){%>if<%}%><%if (source.filter("DecisionNode").hasStereotype(getProperty("s_when"))){%>when<%}%> <%Guards.trim()%> then
		<%target.filter("ActivityNode").determineControlNodeType.trim()%>
<%}%>
<%for (outgoing[guard.filter("LiteralString").value.trim()=="else"]){%>
	else
		<%target.filter("ActivityNode").determineControlNodeType.trim()%>
<%}%>
end <%if (hasStereotype(getProperty("s_if"))){%>if<%}%><%if (hasStereotype(getProperty("s_when"))){%>when<%}%>;




<%script type="uml.DecisionNode" name="DecisionNodeCode_if_when"%>
<%for (outgoing[guard.filter("LiteralString").value.trim()!="else"].filter("ControlFlow").sortControlFlows().nGet(0)){%>
	<%if (source.filter("DecisionNode").hasStereotype(getProperty("s_if"))){%>if<%}%><%if (source.filter("DecisionNode").hasStereotype(getProperty("s_when"))){%>when<%}%> <%Guards.trim()%> then
		<%target.filter("ActivityNode").determineControlNodeType.trim()%>
<%}%>
<%for (outgoing[guard.filter("LiteralString").value.trim()!="else"].filter("ControlFlow").sortControlFlows().nGet(1, outgoing[guard.filter("LiteralString").value.trim()!="else"].length())){%>
	else<%if (source.filter("DecisionNode").hasStereotype(getProperty("s_if"))){%>if<%}%><%if (source.filter("DecisionNode").hasStereotype(getProperty("s_when"))){%>when<%}%> <%Guards.trim()%> then
		<%target.filter("ActivityNode").determineControlNodeType.trim()%>
<%}%>
end <%if (hasStereotype(getProperty("s_if"))){%>if<%}%><%if (hasStereotype(getProperty("s_when"))){%>when<%}%>;




<%script type="uml.MergeNode" name="MergeNodeCode"%>
<%if (outgoing.filter("ControlFlow").nSize() > 1){%>
ERROR(e2): Exactly one outgoing ControlFlow is allowed for a MergeNode.
<%}else{%>
<%outgoing.filter("ControlFlow").target.filter("ActivityNode").determineControlNodeType.trim()%>
<%}%>







<%script type="uml.Activity" name="ConditionalEquations"%>
<%for (ownedElement.filter("OpaqueAction")[outgoing.filter("ControlFlow").nSize() == 0 && incoming.filter("ControlFlow").nSize() == 0]){%>
// OpaqueAction: "<%activity.name%>.<%name%>"
<%body.expandGENCodeMacros()%>
<%}%>
<%for (ownedElement.filter("CallBehaviorAction")){%>
	<%if (behavior.hasStereotype(getProperty("s_conditionalEquation")) && behavior.eClass().name == "Activity"){%>
<%filter("CallBehaviorAction").behavior.filter("Activity").ConditionalEquations.trim()%>
	<%}else{%>
ERROR: Only Activities with the stereotype "ConditionalEquations(Diagram)" are allowed as behavior of this CallBehaviorAction.
	<%}%>
<%}%>
<%if (ownedElement.filter("InitialNode").nSize() > 0 ){%>
<%ConditionalActivity.trim()%>
<%}%>





<%script type="uml.ControlFlow" name="Guards"%>
<%GuardsInc.trim().replaceAll("or  true", "").replaceAll("or true", "").replaceAll("and  true", "").replaceAll("and true", "")%>


<%script type="uml.ControlFlow" name="GuardsInc"%>
<%if (getStereotypeValue(getProperty("s_stateDependency"), getProperty("s_p_isInState")) != null){%>
	<%getStereotypeValue(getProperty("s_stateDependency"), getProperty("s_p_isInState")).isInState(activity.context.qualifiedName, getStereotypeValue(getProperty("s_stateDependency"), getProperty("s_p_logicalOperator")).name)%> <%}%>
<%if (guard.filter("LiteralString").value != ""){%> 
<%guard.filter("LiteralString").value%>
<%}else{%>true
<%}%>

<%script type="uml.State" name="isInState"%>
<%qualifiedName.replaceFirst(args(0) + "::", "").replaceSpecCharExceptThis("::").replaceAll("::","." )%>.active <%args(1)%> 
