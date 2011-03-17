<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper

import org.openmodelica.modelicaml.gen.modelica.services.validation.NamesCheck


import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_inheritance
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_components
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_connections
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_statemachines
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_activities
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_dependencies
import org.openmodelica.modelicaml.gen.modelica.validation.validation_of_code

%>

<%script type="uml.Classifier" name="validation" post="trim()"%>
<%-- NOTE: Classes that are validated have ModelicaML stereotypes! Hence, all elemented contained are going to be validated. --%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Class must have a name. --%>
<%if ( name.trim() == ""){%>
<%alert("error", "MISSING(c00): Name for "+ eClass().name + " in '" + owner.filter("NamedElement").qualifiedName+ "'")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A UML Property of a ModelicaML class must a have stereotype applied which indicates that this is a ModelicaML Propoerty (i.e. instance of a class). --%>
<%for (ownedElement.filter("Property")[isModelicaComponent == "false"]){%>
<%alert("error", "MISSING(c01): Stereotype for the "+ eClass().name + " '" + name + "'")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: UML Behavior must a have stereotype applied which indicates that this is a part of the ModelicaML class behavior (i.e. euqation or algorithm sections). --%>
<%for (ownedElement.filter("Behavior")[isModelicaBehavior == "false"]){%>
	<%if (eClass().name != "FunctionBehavior"){%>
<%alert("error", "MISSING(c02): Stereotype for the "+ eClass().name + " '" + filter("NamedElement").name + "'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: UML Class must have stereotype applied indicating that this is a ModelicaML class (e.g. model, block, record, connector, etc.). --%>
<%for (ownedElement.filter("Class")[!isBehavior() && isModelicaRestrictedClass == "false"]){%>
<%alert("error", "MISSING(c03): Stereotype for the "+ eClass().name + " '" + filter("NamedElement").name + "'")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: UML Primitive Type must have stereotype applied indicating that this is a ModelicaML class (e.g. model, block, record, connector, etc.). --%>
<%for (ownedElement.filter("PrimitiveType")[isModelicaRestrictedClass == "false"]){%>
<%alert("error", "MISSING(c04): Stereotype for the "+ eClass().name + " '" + filter("NamedElement").name + "'")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML class may only contain: ... --%>
<%for (ownedElement){%>
	<%if (	eClass().name == "Class"
			|| eClass().name == "Enumeration"
			|| eClass().name == "PrimitiveType"
			|| eClass().name == "Property" 
			|| eClass().name == "Parameter"
			|| eClass().name == "StateMachine" 
			|| eClass().name == "Activity" 
			|| eClass().name == "OpaqueBehavior"
			|| eClass().name == "Port" 
			|| eClass().name == "FunctionBehavior"
			|| eClass().name == "Comment"
			|| eClass().name == "Connector"
			|| eClass().name == "Constraint"
			|| eClass().name == "Generalization"
			|| eClass().name == "Dependency"
			|| eClass().name == "Signal"
	){%>
	<%}else{%>
<%alert("error", "DISCARDED(ce01): UML " + eClass().name + "(e)s are not supported.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML package may only contain the following elements: --%>
<%if (hasStereotype(getProperty("s_package"))){%>
	<%for (ownedElement[
	eClass().name != "Class"
	&& eClass().name != "Property" 
	&& eClass().name != "PrimitiveType" 
	&& eClass().name != "Enumeration" 
	&& eClass().name != "FunctionBehavior"
	&& eClass().name != "Comment"]){%>
<%alert("error", "NOT VALID(cp01): Modelica package '"+owner.filter("Class").name+"' cannot contain the "+eClass().name+" '"+filter("NamedElement").name+"'").trim()%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A Modelica package, record, connector or type cannot have behavior. --%>
<%if ( 	hasStereotype(getProperty("s_package")) 
		|| hasStereotype(getProperty("s_record"))
		|| hasStereotype(getProperty("s_connector"))
		|| hasStereotype(getProperty("s_type")) 
	){%>
	<%if (ownedElement.filter("Behavior")[eClass().name != "FunctionBehavior" ].nSize() > 0){%>
<%alert("error", "NOT VALID (cb01): "+ eClass().name + " '" + filter("NamedElement").name + "' cannot have behavior.")%>
	<%}%>
<%}%>

<%-- ############################################################### --%>
<%-- Validate Inheritance --%>
<%Generalization_validation(name, "in '" + owner.filter("NamedElement").qualifiedName + "'")%>

<%-- ############################################################### --%>
<%-- Validate Dependecies --%>
<%--TODO: BUGGY! <%Dependency_validation%>--%>

<%-- ############################################################### --%>
<%-- Validate Components--%>
<%ownedElement.filter("TypedElement")[isModelicaComponent == "true"].TypedElement_validation%>

<%-- ############################################################### --%>
<%-- Validate Connections--%>
<%ownedElement.filter("Connector").Connector_validation%>

<%-- ############################################################### --%>
<%-- Validate StateMachines --%>
<%ownedElement.filter("StateMachine")[hasStereotype(getProperty("s_modelicaStateMachine"))].StateMachine_validation%>

<%-- ############################################################### --%>
<%-- Validate Activities --%>
<%ownedElement.filter("Activity")[isModelicaBehavior == "true" ].Activity_validation%>

<%-- Under TEST --%>
<%filter("Class").validateSingleAssignementRuleForAlgorithmSections%>

<%-- Under TEST: test if there is a state machines which is not referenced as sub-state machine--%>
<%if (ownedElement.filter("StateMachine").nSize() > 0){%>
	<%if (ownedElement.filter("StateMachine")[submachineState == null].nSize() < 1){%>
<%alert("error", "NOT VALID (cb02): All state machines in '"+qualifiedName+"' are referenced as sub-state machine. This leads to a loop. At least one of the state machines must be NOT a sub-state machine.")%>
	<%}%>
<%}%>

<%alert("error",checkForNameDuplicatesOfOwnedAttributes())%>
<%alert("error",checkForNameDuplicatesOfNestedClasses())%>

<%-- ############################################################### --%>
<%-- Recursive call to the validation of nested classes (only for classes which have ModelicaML stereotypes, other classes will be discarded). --%>
<%ownedElement.filter("Classifier")[isModelicaRestrictedClass == "true"].validation%>

