<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper

%>

<%script type="uml.Classifier" name="Dependency_validation" post="trim()"%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML class may have only one redeclare relation. --%>
<%if (clientDependency[hasStereotype(getProperty("s_redeclare"))].nSize() > 1 ){%>
<%alert("error","NOT VALID(d00): The " + eClass().name + " '"+name+"' can have only one redeclare relaration")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML class may have only one partial derivative relation. --%>
<%if (clientDependency[hasStereotype(getProperty("s_partialDerivativeFunction"))].nSize() > 1 ){%>
<%alert("error","NOT VALID(d02): The " + eClass().name + " '"+name+"' can have only one partial derivative relaration")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML class may have only one constrainedby relation. --%>
<%if (clientDependency[hasStereotype(getProperty("s_constrainedby"))].nSize() > 1 ){%>
<%alert("error","NOT VALID(d03): The " + eClass().name + " '"+name+"' can have only one constrainedby relaration")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML function can only have a partial derivative relation to another function. --%>
<%if (hasStereotype(getProperty("s_function"))){%>
	<%if (clientDependency[hasStereotype(getProperty("s_partialDerivativeFunction"))].target.hasStereotype(getProperty("s_function")) ){%><%-- do nothing --%>
	<%}else{%>
<%alert("error","NOT VALID(d03): The function '"+name+"' can only have a partial derivative relation to another function")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- TODO: only functions can have a partial derivative relaration! --%>