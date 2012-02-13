<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.gen.modelica.services.MoldelicaMLValidator

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper

import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_classes

%>

<%script type="uml.Model" name="ModelicaML_standalone_validator" file="validation-gen/<%qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","\/" )%>_validation_results.xml"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<validation_results>
	<general>
		<name><%name%></name>
		<qualifiedName><%qualifiedName%></qualifiedName>
	</general>
	<%validation%>
	<message><%if (get("error_detected") == null){%><%validationAlert("","ModelicaML validation: No errors were detected for '" + name + "'.")%>ModelicaML validation: No errors were detected for "<%name%>".<%}else{%>ModelicaML validation: Errors were detected for "<%name%>". <%}%></message>
</validation_results>

<%-- NOTE: is it not possible to open a message dialog since no UI objects are avaialable when executing the chain. The following does not work.
<%messageDialog("ModelicaML Model Validation", "Validation completed: No errors were detected.")%>--%>

<%script type="uml.Package" name="validation" post="trim()"%>
<%-- NOTE: Call the validation of classes (hoewever only for classes which have ModelicaML stereotypes, other classes will be discarded). --%>
<%ownedElement.filter("Classifier")[isModelicaRestrictedClass == "true"].validation%>
<%-- recursive call validation of packages --%>
<%ownedElement.filter("Package").validation%>
