<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper

%>

<%script type="uml.Classifier" name="Generalization_validation" post="trim()"%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A UML Generalization must have a ModelicaML stereotype indicating that this is a Modelica extends or type extends relation. --%>
<%for (ownedElement.filter("Generalization")){%>
	<%if (hasStereotype(getProperty("s_extends")) || hasStereotype(getProperty("s_typeExtend"))){%> <%-- do nothing --%>
	<%}else{%>
<%alert("error", "MISSING(i00): Stereotype for the "+ eClass().name + " in '" + owner.filter("NamedElement").name + "'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML model  or class can only extend a model, block or record. --%>
<%if (hasStereotype(getProperty("s_model")) || hasStereotype(getProperty("s_class")) ){%>
	<%for (general){%>
		<%if (hasStereotype(getProperty("s_model")) || hasStereotype(getProperty("s_block")) || hasStereotype(getProperty("s_record")) ){%><%-- do nothing --%>
		<%}else{%>
<%alert("error", "NOT VALID(i01): The model '"+args(0) +"' ("+args(1)+") cannot extend '"+name+"'")%>
		<%}%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML block can only extend a block or record. --%>
<%if (hasStereotype(getProperty("s_block"))){%>
	<%for (general){%>
		<%if (hasStereotype(getProperty("s_block")) || hasStereotype(getProperty("s_record")) ){%><%-- do nothing --%>
		<%}else{%>
<%alert("error", "NOT VALID(i02): The block '"+args(0) +"' ("+args(1)+") cannot extend '"+name+"'")%>
		<%}%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML connector can only extend a type, record or connector. --%>
<%if (hasStereotype(getProperty("s_connector"))){%>
	<%for (general){%>
		<%if (hasStereotype(getProperty("s_type")) || hasStereotype(getProperty("s_record")) || hasStereotype(getProperty("s_connector")) ){%>
		<%}else{%>
<%alert("error", "NOT VALID(i03): The connector '"+args(0)+"' ("+args(1)+") cannot extend '"+name+"'")%>
		<%}%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML record can only extend a record. --%>
<%if (hasStereotype(getProperty("s_record"))){%>
	<%for (general){%>
		<%if (hasStereotype(getProperty("s_record"))){%>
		<%}else{%>
<%alert("error", "NOT VALID(i04): The record '"+args(0)+"' ("+args(1)+") cannot extend '"+name+"'")%>
		<%}%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML type can only extend a type. --%>
<%if (hasStereotype(getProperty("s_type"))){%>
	<%for (general){%>
		<%if (hasStereotype(getProperty("s_type")) || eClass().name == "PrimitiveType"){%>
		<%}else{%>
<%alert("error", "NOT VALID(i05): The type '"+args(0)+"' ("+args(1)+") cannot extend '"+name+"'")%>
		<%}%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML function can only extend a function. --%>
<%if (hasStereotype(getProperty("s_function"))){%>
	<%for (general){%>
		<%if (hasStereotype(getProperty("s_function"))){%>
		<%}else{%>
<%alert("error", "NOT VALID(i06): The function '"+args(0)+"' ("+args(1)+") cannot extend '"+name+"'")%>
		<%}%>
	<%}%>
<%}%>