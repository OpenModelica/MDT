<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._1_file_name
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._3_component_getter
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._2_class_getter
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._4_behavior_getter
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.common
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

%>
<%-- 	############################################################# 	--%>
<%-- 	This impementation is based on the Modelica 3.2 specification.	--%>
<%-- 	############################################################# 	--%>



<%-- 	############################################################# 	--%>
<%-- 					B.2.1 Stored Definition							--%>
<%-- 	############################################################# 	--%>
<%script type="uml.Classifier" name="stored_definition" file="<%file_name%>"%>
<%add_inc_top_file%><%add_inc_validation%><%-- NOTE: Not part of the Modelica grammar. --%>
<%within%>
<%final%><%class_definition%><%-- NOTE: the ";" is moved to the end of the "class_specifier"  --%>



<%-- 	############################################################# 	--%>
<%-- 					B.2.2 Class Definition							--%>
<%-- 	############################################################# 	--%>
<%script type="uml.Classifier" name="class_definition" post="trim()"%>
<%encapsulated%><%partial%><%restricted_class_name%><%class_specifier%>

<%script type="uml.Enumeration" name="class_definition" post="trim()"%>
<%encapsulated%><%partial%> type <%name.replaceSpecChar()%> = enumeration(<%if (ownedElement.filter("EnumerationLiteral") == null){%>:<%}else{%><%ownedElement.filter("EnumerationLiteral").name.replaceSpecChar().sepStr(", ")%><%}%>);

<%script type="uml.PrimitiveType" name="class_definition" post="trim()"%>
<%encapsulated%><%partial%> type <%name.replaceSpecChar()%> = <%for (generalization){%><%if (general.name != null){%><%general.qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","." ).replaceAll(getProperty("p_path_ModelicaPredefinedTypes").replaceAll("::", ".")+".", "").replaceAll("Modelica", "")%><%if (getStereotypeValue(getProperty("s_typeExtend"), getProperty("s_p_arraySubscripts")) ){%>[<%getStereotypeValue(getProperty("s_typeExtend"), getProperty("s_p_arraySubscripts")).sepStr(", ")%>]<%}%><%if (getStereotypeValue(getProperty("s_typeExtend"), getProperty("s_p_modification")) ){%>(<%getStereotypeValue(getProperty("s_typeExtend"), getProperty("s_p_modification")).removeOutterBraces().sepStr(", ")%>)<%}%><%}%><%}%>;


<%script type="uml.Classifier" name="class_specifier" post="trim()"%>
<%if ( isPartialDerivativeFunction == "false" ){%>
<%IDENT("start")%> <%string_comment%> <%constraining_clause%>
	<%composition%>
	<%add_inc_behavior%><%-- NOTE: Not part of the Modelica grammar. --%>
end <%IDENT("end")%>;<%-- NOTE: the ";" was originally in "stored_definition"  --%>
<%}else{%><%-- Partial Derivative Function --%>
<%name.replaceSpecChar()%> = der(<%clientDependency.target.filter("NamedElement").name.replaceSpecChar()%>, <%partialDerivativeFunctionArguments%><%clientDependency.nFirst().partialDerivativeFunctionArguments%>);<%-- NOTE: the ";" was originally in "stored_definition"  --%>
<%}%>

<%script type="uml.Classifier" name="composition" post="trim()"%>
<%annotation%><%if (annotation != ""){%>;<%}%>
<%element_list%>
<%equation_section%>
<%algorithm_section%>
<%external%>
<%--<%annotation%><%if (annotation != ""){%>;<%}%>--%>

<%script type="uml.Classifier" name="element_list" post="trim()"%>
<%element%><%-- NOTE: the ";" is moved to the end of the "class_specifier"  --%>
<%add_inc_structure%><%-- NOTE: Not part of the Modelica grammar. --%>

<%script type="uml.Classifier" name="element" post="trim()"%>
<%import_clause%>
<%extends_clause%>
<%nested_classes%><%-- NOTE: Includes the class_definition. --%>
<%components%><%-- NOTE: Includes the component_clause. --%>
<%signals%><%-- NOTE: Create variables that represent signals. This is not a Modelica construct. --%>

<%-- UML Signals ###################################################################### --%>
<%script type="uml.Classifier" name="signals" post="trim()"%>
<%for (ownedElement.filter("Signal")){%>
record <%name.replaceSpecChar()%>_Definition
	Integer counter; // indicates how many times the the signal has occured.
	Boolean occurrence; // indicates that signal has occured. 
	
	<%for (ownedElement.filter("Property")){%>
		<%component_clause%>
	<%}%>
end <%name.replaceSpecChar()%>_Definition;

<%name.replaceSpecChar()%>_Definition <%name.replaceSpecChar()%>;

<%}%>
<%-- UML Signals END ###################################################################### --%>


<%script type="uml.Classifier" name="import_clause" post="trim()"%>
<%for (clientDependency[hasStereotype(getProperty("s_import"))]){%><%--  TODO: Use the <<Import>> stereotype. --%>
import <%target.filter("NamedElement").getQualifiedName().replaceSpecCharExceptThis("::").replaceAll("::", ".")%>;
<%}%>
<%-- TODO: stereotypes  for imports  --%>


<%script type="uml.Classifier" name="nested_classes" post="trim()"%>
<%for (ownedElement.filter("Class")[isModelicaRestrictedClass == "true" && isStoredDefinition == "false"]){%>
<%nested_class_definition%><%-- NOTE: Includes the class_definition. --%>
<%}%>
<%for (ownedElement.filter("Enumeration")[isModelicaRestrictedClass == "true" && isStoredDefinition == "false"]){%>
<%nested_class_definition%><%-- NOTE: Includes the class_definition. --%>
<%}%>
<%for (ownedElement.filter("PrimitiveType")[isModelicaRestrictedClass == "true" && isStoredDefinition == "false"]){%>
<%nested_class_definition%><%-- NOTE: Includes the class_definition. --%>
<%}%>

<%script type="uml.Classifier" name="nested_class_definition" post="trim()"%>
<%add_inc_validation%><%-- NOTE: Not part of the Modelica grammar. --%>
<%public_protected%><%redeclare%><%final%><%replaceable%><%class_definition%>

<%script type="uml.Classifier" name="components"%>
<%for (ownedElement.filter("TypedElement")[isModelicaComponent == "true" ]){%>
<%component_clause%>
<%}%>

<%script type="uml.FunctionBehavior" name="components"%>
<%for (ownedElement.filter("Parameter")[isModelicaComponent == "true" ]){%>
<%component_clause%>
<%}%>
protected
<%for (ownedElement.filter("Property")[isModelicaComponent == "true" ]){%>
	<%component_clause%>
<%}%>


<%-- 	############################################################# 	--%>
<%-- 					B.2.3 Extends									--%>
<%-- 	############################################################# 	--%>
<%script type="uml.Classifier" name="extends_clause" post="trim()"%>
<%for (generalization){%><%if (general.name != null){%>
	extends <%general.qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","." ).replaceAll(getProperty("p_path_ModelicaPredefinedTypes").replaceAll("::", ".")+".", "")%><%if (getStereotypeValue(getProperty("s_extends"), getProperty("s_p_modification")) != null ){%>(<%getStereotypeValue(getProperty("s_extends"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%> <%annotation%>;
<%}%><%}%>

<%script type="uml.NamedElement" name="constraining_clause" post="trim()"%>
<%if (clientDependency[hasStereotype(getProperty("s_constrainedby"))] != null){%>
	<%for (clientDependency[hasStereotype(getProperty("s_constrainedby"))]){%>
constrainedby <%target.filter("NamedElement").qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","." ).replaceAll(getProperty("p_path_ModelicaPredefinedTypes").replaceAll("::", ".")+".", "")%><%if (getStereotypeValue(getProperty("s_constrainedby"), getProperty("s_p_modification")) != null ){%>(<%getStereotypeValue(getProperty("s_constrainedby"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%> <%comment%>
	<%}%>
<%}%>



<%-- 	############################################################# 	--%>
<%-- 					B.2.4 Component Clause 							--%>
<%-- 	############################################################# 	--%>
<%script type="uml.TypedElement" name="component_clause" post="trim()"%>
<%public_protected%><%redeclare%><%final%><%innerouter%><%replaceable%><%type_prefix%><%type_specifier%><%-- NOTE: array_subscripts are inserted at the end of the component <%array_subscripts%> --%> <%component_list%>;

<%script type="uml.TypedElement" name="type_prefix"%>
<%flowFlag%><%variability%><%causality%>

<%script type="uml.TypedElement" name="type_specifier"%>
<%if (type != null){%><%if (type.filter("Classifier").qualifiedName.startsWith(owner.filter("Classifier").qualifiedName + "::")){%><%type.qualifiedName.replaceFirst(owner.filter("Classifier").qualifiedName + "::", "").replaceSpecCharExceptThis("::").replaceAll("::","." )%><%}else{%><%type.type_path%><%}%><%}%>


<%script type="uml.TypedElement" name="component_list"%>
<%component_declaration%><%-- NOTE: A list-notation for components of the same type is not supported. Components are listed separatly. --%>

<%script type="uml.TypedElement" name="component_declaration" post="trim()"%>
<%declaration%><%conditional_attribute%> <%comment%> <%constraining_clause%>

<%script type="uml.TypedElement" name="declaration" post="trim()"%>
<%name.replaceSpecChar()%><%array_subscripts%><%modification%><%declaration_equation%><%-- NOTE: declaration_equation does not appear as such in the Modelica grammar. --%>



<%-- 	############################################################# 	--%>
<%-- 					B.2.5 Modifications								--%>
<%-- 	############################################################# 	--%>
<%-- NOTE: Modifications are captured as text in SysML models. They are included as they are ... --%>



<%-- 	############################################################# 	--%>
<%-- 					B.2.6 Equations									--%>
<%-- 	############################################################# 	--%>
<%script type="uml.Classifier" name="equation_section" post="trim()"%>
<%equation%>
<%filter("Class").connect_equations%>

<%script type="uml.Classifier" name="algorithm_section" post="trim()"%>
<%algorithm%>


<%-- 	############################################################# 	--%>
<%-- 					Additional: UML Packge Code						--%>
<%-- In order to reflect the model structure UML packages are also translated into Modelica packages. --%>
<%-- NOTE: Since no stereotype is available for UML Package in the profile no properties of a Modelica class can be set. --%>
<%-- 	############################################################# 	--%>
<%script type="uml.Package" name="package_code" file="<%file_name%>"%>
<%add_inc_top_file%><%add_inc_validation%><%-- NOTE: Not part of the Modelica grammar. --%>
<%within%>
package <%name.replaceSpecChar()%>
<%add_inc_top_annotation%>
<%for (ownedElement.filter("Class")[isModelicaRestrictedClass == "true" && isStoredDefinition == "false"]){%>
	<%nested_class_definition%>
<%}%>
<%for (ownedElement.filter("Enumeration")[isModelicaRestrictedClass == "true" && isStoredDefinition == "false"]){%>
	<%nested_class_definition%>
<%}%>
<%for (ownedElement.filter("PrimitiveType")[isModelicaRestrictedClass == "true" && isStoredDefinition == "false"]){%>
	<%nested_class_definition%>
<%}%>
end <%name.replaceSpecChar()%>;
