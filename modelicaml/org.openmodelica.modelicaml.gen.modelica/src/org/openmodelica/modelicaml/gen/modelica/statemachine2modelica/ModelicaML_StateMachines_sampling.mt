<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
 
%>

<%script type="uml.StateMachine" name="startSamplingTag" post="trim()"%>
<%if (isSetSampling == "true"){%>/* M@ sampling expression start */ 
when <%getStereotypeValue(getProperty("s_modelicaStateMachine"), getProperty("s_p_samplingExpression"))%> then <%}%>

<%script type="uml.StateMachine" name="endSamplingTag" post="trim()"%>
<%if (isSetSampling == "true"){%>/* M@ sampling expression end */
end when;<%}%>



<%-- Helper ############################## --%>
<%script type="uml.StateMachine" name="isSetSampling" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_modelicaStateMachine"), getProperty("s_p_samplingExpression")) != null){%>
true<%}else{%>false<%}%>



<%-- FOR TESTING ONLY ############################## --%>

<%--<%script type="uml.StateMachine" name="test" post="trim()" file="<%name%>.txt" %>
<%startSamplingTag%>

<%endSamplingTag%>
--%>
<%-- FOR TESTING ONLY END ############################## --%>


