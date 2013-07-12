<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLMacros
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._4_behavior_getter

import org.openmodelica.modelicaml.gen.modelica.activity2modelica.ModelicaML_ActivityDiagram2Modelica
import org.openmodelica.modelicaml.gen.modelica.annotation.ModelicaML_annotations

%>
<%-- ###################################################################### --%>
<%script type="uml.Vertex" name="Entry" post="trim()"%>
<%if (filter("State").entry != null){%>
	//state "<%args(0)%>": entry behavior
	<%if (filter("State").entry.eClass().name == "OpaqueBehavior" ){%>
	<%filter("State").entry.filter("OpaqueBehavior").body.expandGENCodeMacros()%>
	<%}else{%>
		<%if (filter("State").entry.eClass().name == "Activity"){%>
			<%filter("State").entry.filter("Activity").Activity2ModelicaCode%>
		<%}else{%>
			<%if (filter("State").entry.eClass().name == "StateMachine"){%>
			ERROR: StateMachines are not supported as entry/exit/do behavior
			<%}%>
		<%}%>
	<%}%>
<%}%>

<%-- ###################################################################### --%>
<%script type="uml.Vertex" name="DoCode" post="trim()"%>
<%if (filter("State").doActivity != null){%>
if <%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active then
	<%Do(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
end if;
<%}%>

<%-- ###################################################################### --%>
<%script type="uml.Vertex" name="Do" post="trim()"%>
//state "<%args(0)%>": do behavior
<%if (filter("State").doActivity.eClass().name == "OpaqueBehavior" ){%>
<%filter("State").doActivity.filter("OpaqueBehavior").body.expandGENCodeMacros()%>
<%}else{%>
	<%if (filter("State").doActivity.eClass().name == "Activity"){%>
		<%filter("State").doActivity.filter("Activity").Activity2ModelicaCode%>
	<%}else{%>
		<%if (filter("State").doActivity.eClass().name == "StateMachine"){%>
		ERROR: StateMachines are not supported as entry/exit/do behavior
		<%}%>
	<%}%>
<%}%>

<%-- ###################################################################### --%>
<%script type="uml.Vertex" name="Exit" post="trim()"%>
<%if (filter("State").exit != null){%>
	//state "<%args(0)%>": exit behavior
	<%if (filter("State").exit.eClass().name == "OpaqueBehavior" ){%>
	<%filter("State").exit.filter("OpaqueBehavior").body.expandGENCodeMacros()%>
	<%}else{%>
		<%if (filter("State").exit.eClass().name == "Activity"){%>
			<%filter("State").exit.filter("Activity").Activity2ModelicaCode%>
		<%}else{%>
			<%if (filter("State").exit.eClass().name == "StateMachine"){%>
			ERROR: StateMachines are not supported as entry/exit/do behavior
			<%}%>
		<%}%>
	<%}%>
<%}%>

<%-- ###################################################################### --%>
<%script type="uml.Transition" name="Effect"%>
<%if (effect != null){%>
	//transition "<%name%>": effect behavior 
	<%if (effect.eClass().name == "OpaqueBehavior" ){%>
	<%effect.filter("OpaqueBehavior").body.expandGENCodeMacros()%>
	<%}else{%>
		<%if (effect.eClass().name == "Activity"){%>
			<%effect.filter("Activity").Activity2ModelicaCode%>
		<%}else{%>
			<%if (effect.eClass().name == "StateMachine"){%>
			ERROR: StateMachines are not supported as entry/exit/do or effect behavior.
			<%}%>
		<%}%>
	<%}%>
<%}%>

<%-- ###################################################################### --%>
<%script type="uml.Vertex" name="AssertsInStates" post="trim()"%>
<%if (getProperty("c_includeAsserts") && ownedElement.filter("Constraint")[hasStereotype(getProperty("s_assert"))] != null){%>
	<%for (ownedElement.filter("Constraint")[hasStereotype(getProperty("s_assert"))]){%>
<%--assert(<%if (specification.filter("OpaqueExpression") != null){%><%specification.filter("OpaqueExpression").body%><%}%><%if (specification.filter("LiteralString") != null){%><%specification.filter("LiteralString").value%><%}%><%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_condition"))%>, "<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_message")).trim()%>", AssertionLevel.<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_level")).name%>); --%>
<%--assert(<%if (specification.filter("OpaqueExpression") != null){%><%specification.filter("OpaqueExpression").body%><%}%><%if (specification.filter("LiteralString") != null){%><%specification.filter("LiteralString").value%><%}%><%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_condition"))%>, "<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_message")).trim()%>");--%>
<%if (getStereotypeValue(getProperty("s_assert"), getProperty("s_p_condition")) != null && getStereotypeValue(getProperty("s_assert"), getProperty("s_p_message")) != null){%>
assert(<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_condition"))%>, "<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_message")).trim()%>");
<%}%>
	<%}%>
<%}%>
