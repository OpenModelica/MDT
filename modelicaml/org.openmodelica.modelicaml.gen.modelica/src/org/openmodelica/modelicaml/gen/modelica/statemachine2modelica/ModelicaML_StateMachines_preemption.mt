<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
 
%>

<%-- Helper ############################## --%>
<%script type="uml.Transition" name="suppressExitActions" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_StateTransitionSpecification"), getProperty("s_p_suppressExitActions"))){%>suppressExitActions<%}else{%><%}%>

<%script type="uml.Transition" name="suppressEntryActions" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_StateTransitionSpecification"), getProperty("s_p_suppressEntryActions"))){%>suppressEntryActions<%}else{%><%}%>



<%script type="uml.State" name="freezeExpression" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_StateSpecification"), getProperty("s_p_freezeExpression")) != null){%> and (not (<%getStereotypeValue(getProperty("s_StateSpecification"), getProperty("s_p_freezeExpression"))%>)) <%}else{%><%}%>

<%script type="uml.StateMachine" name="freezeExpression" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_StateSpecification"), getProperty("s_p_freezeExpression")) != null){%> and (not (<%getStereotypeValue(getProperty("s_StateSpecification"), getProperty("s_p_freezeExpression"))%>)) <%}else{%><%}%>