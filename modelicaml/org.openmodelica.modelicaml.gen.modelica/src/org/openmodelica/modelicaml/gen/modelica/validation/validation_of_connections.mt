<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper

import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper
%>

<%script type="uml.Connector" name="Connector_validation" post="trim()"%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A UML connector must have a ModelicaML stareotype. --%>
<%if (!hasStereotype(getProperty("s_connect"))){%>
<%alert("error", "MISSING(cc01): Stereotype for the " + eClass().name + " '"+name+"' between '" + end.nGet(0).role.name + "' and '" + end.nGet(1).role.name +"'.")%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A UML connector end must be set. --%>
	<%if (end.nGet(0).role == null){%>
<%alert("error", "NOT VALID(cc02): Defintion of the connection '" +name+ "'")%>
	<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A UML connector end must be set. --%>
	<%if (end.nGet(1).role == null){%>
<%alert("error", "NOT VALID(cc03): Defintion of the connection '" +name+ "'")%>	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML connection can only connect ports. --%>
<%-- TODO: check if the ports have ModelicaML stereotypes --%>
<%if (end.nGet(0).role.eClass().name != "Port" || end.nGet(1).role.eClass().name != "Port"){%>
<%alert("error", "NOT VALID(cc04): Connectors can only connect Ports. Connector name: '" +name+ "'")%>
<%}%>
<%-- ############################################################### --%>