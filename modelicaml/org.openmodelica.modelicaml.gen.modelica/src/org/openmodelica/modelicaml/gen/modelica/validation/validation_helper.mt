<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.gen.modelica.services.MoldelicaMLValidator
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices
%>

<%script type="uml.Element" name="alert" post="trim()"%>
<%if (args(1) != ""){%>
<%put("error_detected")%><%-- Store the fact that error was detected. --%>
<%--<%validationAlert(args(0), args(1) + " (in " + owner.filter("NamedElement").qualifiedName + ")")%>--%><%-- Display the error message in the Acceleo Log View. --%>
<%modelicaMLValidationAlert(args(0), args(1))%><%-- Display the error message in the ModelicaML View. --%>

<<%args(0)%>>
	<message><%args(1)%></message>
	<qualifiedName><%if (filter("NamedElement").qualifiedName != null){%><%filter("NamedElement").qualifiedName%><%}else{%>QualifiedName not found. Owner is <%owner.filter("NamedElement").qualifiedName%><%}%></qualifiedName>
	<xmi_id><%getXMIID()%></xmi_id>
</<%args(0)%>>
<%}%>

