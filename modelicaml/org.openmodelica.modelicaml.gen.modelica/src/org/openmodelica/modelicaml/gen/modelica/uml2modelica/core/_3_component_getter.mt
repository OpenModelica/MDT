<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper
import org.openmodelica.modelicaml.common.services.StringUtls

%>

<%-- 	############################################################# 	--%>
<%-- 							Components Getter						--%>
<%-- 	############################################################# 	--%>
<%script type="uml.TypedElement" name="final"%>
<%if ( filter("Property").isLeaf 
		|| isSetModelicaComponentStereotypeValueBoolean("s_p_final") == "true"){%>final <%}%>

<%script type="uml.TypedElement" name="public_protected"%>
<%--<%if ( visibility == "protected" || getModelicaComponentStereotypeValueEnum("s_p_visibility") == "protected" ){%>protected <%}%>--%>
<%if ( visibility == "protected" ){%>protected <%}%><%-- NOTE: 'public' is default. --%>


<%script type="uml.TypedElement" name="innerouter"%>
<%-- ISSUE: TODO: inner/outer for components/ports, etc.? <%if (getStereotypeValue(getProperty("s_component"), getProperty("s_p_innerouter"))){%><%getStereotypeValue(getProperty("s_component"), getProperty("s_p_innerouter")).name%> <%}%>--%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_innerouter"))){%><%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_innerouter")).name%> <%}%>

<%script type="uml.TypedElement" name="replaceable"%>
<%if ( isSetModelicaComponentStereotypeValueBoolean("s_p_replaceable") ){%>replaceable <%}%>

<%script type="uml.TypedElement" name="flowFlag"%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_flowFlag"))){%><%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_flowFlag")).name%> <%}%>

<%script type="uml.TypedElement" name="variability"%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_variability"))){%><%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_variability")).name != "continuous"){%><%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_variability")).name%> <%}%><%}%>
<%if (getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_variability"))){%><%if (getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_variability")).name != "continuous"){%><%getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_variability")).name%> <%}%><%}%>

<%script type="uml.TypedElement" name="causality"%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_causality"))){%><%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_causality")).name%> <%}%>
<%if (getStereotypeValue(getProperty("s_port"), getProperty("s_p_causality"))){%><%getStereotypeValue(getProperty("s_port"), getProperty("s_p_causality")).name%> <%}%>
<%if (getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_causality"))){%><%getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_causality")).name%> <%}%>

<%script type="uml.TypedElement" name="conditional_attribute" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_component"), getProperty("s_p_conditionalAttribute"))){%>if <%getStereotypeValue(getProperty("s_component"), getProperty("s_p_conditionalAttribute"))%><%}%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_conditionalAttribute"))){%>if <%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_conditionalAttribute"))%><%}%>
<%if (getStereotypeValue(getProperty("s_port"), getProperty("s_p_conditionalAttribute"))){%>if <%getStereotypeValue(getProperty("s_port"), getProperty("s_p_conditionalAttribute"))%><%}%>
<%-- NOTE: ModelicaML specific. --%>
<%if (getStereotypeValue(getProperty("s_requirementInstance"), getProperty("s_p_conditionalAttribute"))){%>if <%getStereotypeValue(getProperty("s_component"), getProperty("s_requirementInstance"))%><%}%>
<%if (getStereotypeValue(getProperty("s_calculatedProperty"), getProperty("s_p_conditionalAttribute"))){%>if <%getStereotypeValue(getProperty("s_calculatedProperty"), getProperty("s_p_conditionalAttribute"))%><%}%>
<%-- NOTE: ModelicaML specific. END --%>

<%script type="uml.TypedElement" name="declaration_equation" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_declarationEquation")) != "" ){%><%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_declarationEquation"))%><%}%>
<%if (getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_declarationEquation")) != "" ){%><%getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_declarationEquation"))%><%}%>

<%script type="uml.TypedElement" name="modification" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_component"), getProperty("s_p_modification"))){%>(<%getStereotypeValue(getProperty("s_component"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_modification"))){%>(<%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%>
<%if (getStereotypeValue(getProperty("s_port"), getProperty("s_p_modification"))){%>(<%getStereotypeValue(getProperty("s_port"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%>
<%if (getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_modification"))){%>(<%getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%>
<%-- NOTE: ModelicaML specific. --%>
<%if (getStereotypeValue(getProperty("s_requirementInstance"), getProperty("s_p_modification"))){%>(<%getStereotypeValue(getProperty("s_requirementInstance"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%>
<%if (getStereotypeValue(getProperty("s_calculatedProperty"), getProperty("s_p_modification"))){%>(<%getStereotypeValue(getProperty("s_calculatedProperty"), getProperty("s_p_modification")).nGet(0, length()).removeOutterBraces().sepStr(", ")%>)<%}%>
<%-- NOTE: ModelicaML specific. END --%>

<%script type="uml.TypedElement" name="array_subscripts" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_component"), getProperty("s_p_arraySubscripts"))){%>[<%getStereotypeValue(getProperty("s_component"), getProperty("s_p_arraySubscripts")).sepStr(", ")%>]<%}%>
<%if (getStereotypeValue(getProperty("s_variable"), getProperty("s_p_arraySubscripts"))){%>[<%getStereotypeValue(getProperty("s_variable"), getProperty("s_p_arraySubscripts")).sepStr(", ")%>]<%}%>
<%if (getStereotypeValue(getProperty("s_port"), getProperty("s_p_arraySubscripts"))){%>[<%getStereotypeValue(getProperty("s_port"), getProperty("s_p_arraySubscripts")).sepStr(", ")%>]<%}%>
<%if (getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_arraySubscripts"))){%>[<%getStereotypeValue(getProperty("s_functionArgument"), getProperty("s_p_arraySubscripts")).sepStr(", ")%>]<%}%>
<%-- NOTE: ModelicaML specific. --%>
<%if (getStereotypeValue(getProperty("s_requirementInstance"), getProperty("s_p_arraySubscripts"))){%>[<%getStereotypeValue(getProperty("s_requirementInstance"), getProperty("s_p_arraySubscripts")).sepStr(", ")%>]<%}%>
<%if (getStereotypeValue(getProperty("s_calculatedProperty"), getProperty("s_p_arraySubscripts"))){%>[<%getStereotypeValue(getProperty("s_calculatedProperty"), getProperty("s_p_arraySubscripts")).sepStr(", ")%>]<%}%>
<%-- NOTE: ModelicaML specific. END --%>

<%script type="uml.Type" name="type_path" post="trim()"%>
<%if (	name == "ModelicaReal"
		|| name == "ModelicaInteger"
		|| name == "ModelicaString"
		|| name == "ModelicaBoolean"){%><%name.replaceAll("Modelica", "").replaceSpecChar()%> <%}else{%>
		<%if (hasStereotype(getProperty("s_ModelicaModelReference"))){%>
			<%if (getStereotypeValue(getProperty("s_ModelicaModelReference"), getProperty("s_p_modelPath")) != null){%><%getStereotypeValue(getProperty("s_ModelicaModelReference"), getProperty("s_p_modelPath"))%>
				<%}else{%>ERROR: Missing reference to Modelica model. 
			<%}%>
		<%}else{%><%qualifiedName.replaceSpecCharExceptThis("::").replaceAll("::","." )%> <%}%><%}%>
