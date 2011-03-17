<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper

%>
<%script type="uml.Activity" name="Activity_validation" post="trim()"%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("Action")){%>
	<%if (eClass().name != "OpaqueAction" && eClass().name != "CallBehaviorAction"){%>
<%alert("error","DISCARDED(ac02): '" + eClass().name + "(e)s' are not supported. Only 'OpaqueAction' and 'CallBehaviorAction' are supported. Name: '"+ name+"' in '"+activity.name+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("ActivityEdge")){%>
	<%if (eClass().name != "ControlFlow"){%>
<%alert("error","DISCARDED(ac03): '" + eClass().name + "(e)s' are not supported. Only 'ControlFlow' is supported. Name: '"+ name+"' in '"+activity.name+"'.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("ControlNode")){%>
	<%if (eClass().name != "DecisionNode" && eClass().name != "InitialNode" && eClass().name != "MergeNode"){%>
<%alert("error","DISCARDED(ac04): '" + eClass().name + "(e)s' are not supported. Only 'InitialNode' and 'DecisionNode' are supported. Name: '"+ name+"' in '"+activity.name+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("DecisionNode")){%>
	<%if (hasStereotype(getProperty("s_if")) || hasStereotype(getProperty("s_when"))){%>
	<%}else{%>
<%alert("error","MISSING(ac04): 'If' or 'When' stereotype for "+ eClass().name + " '"+ name+"' in '"+activity.name+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: An ModelicaML Activity must have exactly one initial node. --%>
<%if (ownedElement.filter("InitialNode").nSize() > 1){%>
<%alert("error","NOT VALID(ac05): Only one InitialNode is allowed in '"+name+"'")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: An ModelicaML Activity must have exactly one outgoing transition of an initial node. --%>
<%if (ownedElement.filter("InitialNode").nSize() == 1 && ownedElement.filter("InitialNode").outgoing.nSize() != 1){%>
<%alert("error","MISSING(ac06): ControlFlow from the InitialNode in '"+name+"'")%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("MergeNode")){%>
	<%if (outgoing.filter("ControlFlow").nSize() > 1 ){%>
<%alert("error","NOT VALID(ac08): The MergeNode '" + name + " in '"+activity.name+"' can only have one outgoing ControlFlow.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("MergeNode")){%>
	<%for (outgoing[guard.filter("LiteralString").value != ""]){%>
	<%alert("error","NOT VALID(ac09): The outgoing ControlFlow of the MergeNode '" + name + " in '"+activity.name+"' is not allowed to have a guard.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("DecisionNode")){%>
	<%if (incoming.filter("ControlFlow").nSize() > 1 ){%>
<%alert("error","NOT VALID(ac10): The DecisionNode '" + name + " in '"+activity.name+"' can only have one incoming ControlFlow.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>