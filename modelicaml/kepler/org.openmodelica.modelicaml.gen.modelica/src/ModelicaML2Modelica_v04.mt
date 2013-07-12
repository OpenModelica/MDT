<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._0_ main_uml2modelica
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._4_behavior_getter
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UML2ModelicaServices
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.additional.ModelicaML_additional
import org.openmodelica.modelicaml.gen.modelica.annotation.ModelicaML_annotations

import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachine2Modelica_v04
import org.openmodelica.modelicaml.gen.modelica.activity2modelica.ModelicaML_ActivityDiagram2Modelica

import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLMacros

import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachinesInstantiation_v04
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_events

%>


<%-- 	############################################################# 	--%>
<%-- 	This templates imports the definitions from the org.openmodelica.modelicaml.uml2modelica.core 
		and overrides some of them in order to include ModelicaML specific features. 		
																		--%>
<%-- 	############################################################# 	--%>


<%-- ############## Overriden: Behavior (equation and algorithm) --%>
<%script type="uml.Classifier" name="equation" post="trim()"%><%-- overriden--%>
<%equations_from_UML_OpaqueBehavior%><%-- NOTE: ModelicaML specific. --%>
<%asserts%><%-- NOTE: ModelicaML specific, not supported by SysML4Modelica.  --%>

<%script type="uml.Classifier" name="equations_from_UML_OpaqueBehavior" post="trim()"%><%-- overriden--%>
<%if (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_equation"))] != null){%>
	<%for (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_equation"))]){%>
	<%initial%>equation
		<%if (getStereotypeValue(getProperty("s_equation"), getProperty("s_p_loopKind")).name != ""){%>
			<%if (getStereotypeValue(getProperty("s_equation"), getProperty("s_p_loopKind")).name == "for"){%>
				<%if (getStereotypeValue(getProperty("s_equation"), getProperty("s_p_forIndices")) != ""){%>
	for <%getStereotypeValue(getProperty("s_equation"), getProperty("s_p_forIndices"))%> loop
	<%body.expandGENCodeMacros().indentTab()%>
	end for;
				<%}else{%>
				ERROR: No for-indices are defined for OpaqueBehavior <%qualifiedName%>.
				<%}%>
			<%}%>
		<%}else{%>
<%body.expandGENCodeMacros().indentTab()%>
		<%}%>
	<%}%>
<%}%>

<%script type="uml.Classifier" name="algorithm" post="trim()"%><%-- overriden--%>
<%if (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_algorithm"))] != null){%>
	<%for (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_algorithm"))]){%>
	<%initial%>algorithm
		<%if (getStereotypeValue(getProperty("s_algorithm"), getProperty("s_p_loopKind")).name != ""){%>
			<%if (getStereotypeValue(getProperty("s_algorithm"), getProperty("s_p_loopKind")).name == "for"){%>
				<%if (getStereotypeValue(getProperty("s_algorithm"), getProperty("s_p_forIndices")) != ""){%>
	for <%getStereotypeValue(getProperty("s_algorithm"), getProperty("s_p_forIndices"))%> loop
	<%body.expandGENCodeMacros().indentTab()%>
	end for;
				<%}else{%>
				ERROR: No for-indices are defined for OpaqueBehavior <%qualifiedName%>.
				<%}%>
			<%}else{%>
				<%if (getStereotypeValue(getProperty("s_algorithm"), getProperty("s_p_loopKind")).name == "while"){%>
					<%if (getStereotypeValue(getProperty("s_algorithm"), getProperty("s_p_whileExpression")) != ""){%>
	while <%getStereotypeValue(getProperty("s_algorithm"), getProperty("s_p_whileExpression"))%> loop
	<%body.expandGENCodeMacros().indentTab()%>
	end while;	
					<%}else{%>
					ERROR: No while-expression is defined for OpaqueBehavior <%qualifiedName%>.
					<%}%>
				<%}%>
			<%}%>
		<%}else{%>
<%body.expandGENCodeMacros().indentTab()%>
		<%}%>
	<%}%>
<%}%>
<%script type="uml.FunctionBehavior" name="algorithm" post="trim()"%>
<%if (body!=""){%>
algorithm
<%body.indentTab()%><%}else{%><%}%>
<%-- ############## Overriden: Behavior (equation and algorithm) END --%>



<%-- ############## Overriden: Additional includes --%>
<%script type="uml.Classifier" name="add_inc_structure" post="trim()"%><%-- overriden--%>
<%ownedElement.filter("StateMachine")[submachineState == null && hasStereotype(getProperty("s_modelicaStateMachine"))].StateMachinesModelicaClass%>
<%ownedElement.filter("StateMachine")[submachineState == null && hasStereotype(getProperty("s_modelicaStateMachine"))].StateMachineInstances%>
<%changeEventsInstantiation.indentTab()%>
<%absoluteTimeEventsInstantiation.indentTab()%>

<%script type="uml.Classifier" name="add_inc_behavior" post="trim()"%><%-- overriden--%>
<%if (ownedElement.filter("StateMachine")[submachineState == null && hasStereotype(getProperty("s_modelicaStateMachine"))] != null){%> 
<%changeEventsStatements%>
<%absoluteTimeEventsTimeStatements%>

<%ownedElement.filter("StateMachine").stateMachineBehaviorCode%>
<%}%>
<%if (ownedElement.filter("Activity")[hasStereotype(getProperty("s_conditionalAlgorithm"))] != null && !getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_isSubActivity"))){%>
algorithm 
<%ownedElement.filter("Activity")[hasStereotype(getProperty("s_conditionalAlgorithm")) && !getStereotypeValue(getProperty("s_conditionalAlgorithm"), getProperty("s_p_isSubActivity"))].Activity2ModelicaCode%>
<%}%>
<%if (ownedElement.filter("Activity")[hasStereotype(getProperty("s_conditionalEquation"))] != null && !getStereotypeValue(getProperty("s_conditionalEquation"), getProperty("s_p_isSubActivity"))){%>
equation
<%ownedElement.filter("Activity")[hasStereotype(getProperty("s_conditionalEquation")) && !getStereotypeValue(getProperty("s_conditionalEquation"), getProperty("s_p_isSubActivity"))].Activity2ModelicaCode%>
<%}%>

<%script type="uml.NamedElement" name="add_inc_top_file"%><%-- overriden--%>
//This code is generated from a ModelicaML model.
<%--<%copyright%>--%>

<%--<%script type="uml.Package" name="add_inc_validation" post="trim()"%>--%><%-- overriden--%>
<%--<%validation%>--%>
<%--<%script type="uml.Classifier" name="add_inc_validation" post="trim()"%>--%><%-- overriden--%>
<%--<%validation%>--%>
<%script type="uml.Package" name="add_inc_top_annotation"%>
annotation(<%g_annotation%>);
<%-- ############## Overriden: Additional includes END --%>




<%-- ############## Overriden: Annotation --%>
<%script type="uml.Class" name="annotation" post="trim()"%><%-- overriden--%>
<%if (ownedComment[hasStereotype(getProperty("s_annotation"))] != null){%>annotation(<%g_annotation%>)<%}%>
<%--
<%if (ownedComment[hasStereotype(getProperty("s_annotation"))] != null){%>
  annotation(<%if (g_annotation != "" ){%><%g_annotation%>, <%}%><%ownedComment[hasStereotype(getProperty("s_annotation"))].body.sepStr(", ")%>)
<%}else{%>annotation(<%g_annotation%>)
<%}%>
--%>
<%-- ############## Overriden: Annotation END --%>


