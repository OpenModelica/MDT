<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._3_component_getter

import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_inheritance

%>

<%script type="uml.TypedElement" name="TypedElement_validation" post="trim()"%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A Modelica component must have a type. --%>
<%if (type.name == ""){%>
<%alert("error","MISSING(cc01): Type for the property '" + name + "'")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A component of a ModelicaML block must have causality (input/output) defined . --%>
<%if (owner.filter("Classifier").hasStereotype(getProperty("s_block"))){%>
	<%if (causality.replaceAll(" ", "").trim() != ""){%>
	<%}else{%>
<%alert("error","MISSING(cc02): Causality (input/output) definition for the " + eClass().name + " '" + name + "'")%>
	<%}%>
<%}%>

