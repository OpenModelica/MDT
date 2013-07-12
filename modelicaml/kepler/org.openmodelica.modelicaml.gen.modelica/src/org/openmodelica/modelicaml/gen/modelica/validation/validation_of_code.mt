<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper
import org.openmodelica.modelicaml.gen.modelica.services.validation.ValidateAssigmentsInAlgorithmSections

%>

<%script type="uml.Class" name="validateSingleAssignementRuleForAlgorithmSections" post="trim()"%>
<%alert("error",getErrorMessageForSingleAssignementRuleInAlgorithmSections)%>


