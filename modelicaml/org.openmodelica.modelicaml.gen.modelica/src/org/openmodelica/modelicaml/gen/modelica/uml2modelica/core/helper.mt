<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
%>


<%-- 	############################################################# 	--%>
<%-- 				Helper for Modelica classes 						--%>
<%-- 	############################################################# 	--%>
<%script type="uml.NamedElement" name="restricted_class_name"%>
<%if (hasStereotype(getProperty("s_class"))){%>class <%}%>
<%if (hasStereotype(getProperty("s_package"))){%>package <%}%>
<%if (hasStereotype(getProperty("s_operator"))){%>operator <%}%>
<%if (hasStereotype(getProperty("s_model"))){%>model <%}%>
<%if (hasStereotype(getProperty("s_block"))){%>block <%}%>
<%if (hasStereotype(getProperty("s_connector"))){%><%expandable%>connector <%}%>
<%if (hasStereotype(getProperty("s_record"))){%>record <%}%>
<%if (hasStereotype(getProperty("s_type"))){%>type <%}%>
<%if (hasStereotype(getProperty("s_function"))){%>function <%}%>
<%if (hasStereotype(getProperty("s_enumeration"))){%>enumeration <%}%>
<%-- NOTE: ModelicaML specific. --%>
<%if (hasStereotype(getProperty("s_calculationModel"))){%>model <%}%>
<%if (hasStereotype(getProperty("s_requirement"))){%>model <%}%>
<%if (hasStereotype(getProperty("s_requirementRepresentative"))){%>model <%}%>
<%if (hasStereotype(getProperty("s_measureOfEffectiveness"))){%>model <%}%>
<%-- NOTE: ModelicaML specific. END--%>

<%script type="uml.NamedElement" name="expandable"%>
<%if (getStereotypeValue(getProperty("s_connector"), getProperty("s_p_expandable"))){%>expandable <%}%>

<%script type="uml.NamedElement" name="isModelicaRestrictedClass" post="trim()"%>
<%if (hasStereotype(getProperty("s_package")) 
	|| hasStereotype(getProperty("s_operator")) 
	|| hasStereotype(getProperty("s_class")) 
	|| hasStereotype(getProperty("s_model")) 
	|| hasStereotype(getProperty("s_block")) 
	|| hasStereotype(getProperty("s_record")) 
	|| hasStereotype(getProperty("s_connector")) 
	|| hasStereotype(getProperty("s_type"))
	|| hasStereotype(getProperty("s_function"))
	|| hasStereotype(getProperty("s_enumeration"))
	
	|| hasStereotype(getProperty("s_calculationModel")) 
	|| hasStereotype(getProperty("s_requirement"))
	|| hasStereotype(getProperty("s_requirementRepresentative"))
	|| hasStereotype(getProperty("s_measureOfEffectiveness"))
	){%>true<%}else{%>false<%}%>

<%script type="uml.NamedElement" name="isPartialDerivativeFunction" post="trim()"%>
<%if (clientDependency.nFirst().hasStereotype(getProperty("s_partialDerivativeFunction"))){%>true<%}else{%>false<%}%>

<%script type="uml.Classifier" name="isProtected" post="trim()"%>
<%if (filter("Class").visibility == "protected"){%>true<%}else{%>false<%}%><%-- NOTE: 'public' is default. --%>

<%script type="uml.NamedElement" name="isRedeclare" post="trim()"%>
<%if ( clientDependency[hasStereotype(getProperty("s_redeclare"))] != null ){%>true<%}else{%>false<%}%>

<%script type="uml.NamedElement" name="isReplaceable" post="trim()"%>
<%if ( isSetModelicaClassStereotypeValueBoolean("s_p_replaceable") == "true" ){%>true<%}else{%>false<%}%>

<%script type="uml.NamedElement" name="isSetModelicaClassStereotypeValueBoolean" post="trim()"%>
<%if ( getStereotypeValue(getProperty("s_package"), getProperty(args(0)))  
	|| getStereotypeValue(getProperty("s_operator"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_class"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_model"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_block"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_connector"), getProperty(args(0)))  
	|| getStereotypeValue(getProperty("s_record"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_type"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_function"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_enumeration"), getProperty(args(0)))
	
	|| getStereotypeValue(getProperty("s_calculationModel"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_requirement"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_requirementRepresentative"), getProperty(args(0))) 
	|| getStereotypeValue(getProperty("s_measureOfEffectiveness"), getProperty(args(0)))
	){%>true<%}else{%>false<%}%>

<%script type="uml.NamedElement" name="getModelicaClassStereotypeValueEnum" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_package"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_package"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_operator"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_operator"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_class"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_class"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_model"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_model"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_block"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_block"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_connector"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_connector"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_record"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_record"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_type"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_type"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_function"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_function"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_enumeration"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_enumeration"), getProperty(args(0))).name%><%}%>
<%-- NOTE: ModelicaML specific. --%>
<%if (getStereotypeValue(getProperty("s_calculationModel"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_calculationModel"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_requirement"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_requirement"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_requirementRepresentative"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_requirementRepresentative"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_measureOfEffectiveness"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_measureOfEffectiveness"), getProperty(args(0))).name%><%}%>
<%-- NOTE: ModelicaML specific. END --%>

<%-- 	############################################################# 	--%>
<%-- 				Helper for Modelica components 						--%>
<%-- 	############################################################# 	--%>
<%script type="uml.TypedElement" name="isModelicaComponent" post="trim()"%>
<%if (hasStereotype(getProperty("s_component")) 
		|| hasStereotype(getProperty("s_variable")) 
		|| hasStereotype(getProperty("s_port")) 
		|| hasStereotype(getProperty("s_functionArgument")) 
		
		|| hasStereotype(getProperty("s_requirementInstance")) 
		|| hasStereotype(getProperty("s_calculatedProperty")) 
	){%>true<%}else{%>false<%}%>


<%script type="uml.TypedElement" name="isSetModelicaComponentStereotypeValueBoolean" post="trim()"%>
<%if ( 	getStereotypeValue(getProperty("s_component"), getProperty(args(0)))
		|| getStereotypeValue(getProperty("s_variable"), getProperty(args(0)))
		|| getStereotypeValue(getProperty("s_port"), getProperty(args(0)))
		|| getStereotypeValue(getProperty("s_functionArgument"), getProperty(args(0)))
		
		|| getStereotypeValue(getProperty("s_requirementInstance"), getProperty(args(0)))
		|| getStereotypeValue(getProperty("s_calculatedProperty"), getProperty(args(0)))
		){%>true<%}else{%>false<%}%>

<%script type="uml.TypedElement" name="isSetModelicaComponentStereotypeValueEnum" post="trim()"%>
<%if ( getStereotypeValue(getProperty("s_component"), getProperty(args(0))) != null
		|| getStereotypeValue(getProperty("s_variable"), getProperty(args(0))) != null
		|| getStereotypeValue(getProperty("s_port"), getProperty(args(0))) != null
		|| getStereotypeValue(getProperty("s_functionArgument"), getProperty(args(0))) != null
		
		|| getStereotypeValue(getProperty("s_requirementInstance"), getProperty(args(0))) != null
		|| getStereotypeValue(getProperty("s_calculatedProperty"), getProperty(args(0))) != null
		){%>true<%}else{%>false<%}%>

<%script type="uml.TypedElement" name="getModelicaComponentStereotypeValueEnum" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_component"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_component"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_variable"), getProperty(args(0))).name%><%}%>
<%if ( getStereotypeValue(getProperty("s_port"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_port"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_functionArgument"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_functionArgument"), getProperty(args(0))).name%><%}%>
<%-- NOTE: ModelicaML specific. --%>
<%if (getStereotypeValue(getProperty("s_requirementInstance"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_requirementInstance"), getProperty(args(0))).name%><%}%>
<%if (getStereotypeValue(getProperty("s_calculatedProperty"), getProperty(args(0))) != null){%><%getStereotypeValue(getProperty("s_calculatedProperty"), getProperty(args(0))).name%><%}%>
<%-- NOTE: ModelicaML specific. END --%>

<%-- 	############################################################# 	--%>
<%-- 				Helper for Modelica Behavior 						--%>
<%-- 	############################################################# 	--%>
<%script type="uml.Behavior" name="isModelicaBehavior" post="trim()"%>
<%if (hasStereotype(getProperty("s_equation")) 
	|| hasStereotype(getProperty("s_algorithm")) 
	
	|| hasStereotype(getProperty("s_modelicaStateMachine")) 
	|| hasStereotype(getProperty("s_conditionalEquation")) 
	|| hasStereotype(getProperty("s_conditionalAlgorithm")) 
	){%>true<%}else{%>false<%}%>