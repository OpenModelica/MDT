<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper
%>

<%-- 	############################################################# 	--%>
<%-- 							Class Getter							--%>
<%-- 	############################################################# 	--%>
<%script type="uml.NamedElement" name="within"%>
<%if (qualifiedName != name){%>
within <%qualifiedName.substring(0,qualifiedName.length() - name.length() - 2).replaceSpecCharExceptThis("::").replaceAll("::","." )%>;
<%}%>
<%-- ISSUE: should we use fully qulaified names for within? --%><%--ALTERNATIVE: <%if (owner.filter("Class") != null){%>within <%owner.filter("Class").name.replaceAll("::","." )%>;<%}%>--%>

<%script type="uml.NamedElement" name="IDENT" post="trim()"%>
<%if ( clientDependency[hasStereotype(getProperty("s_redeclare"))] != null ){%>
<%if ( args(0) != "end" ){%>extends <%}%><%clientDependency[hasStereotype(getProperty("s_redeclare"))].target.filter("NamedElement").name.replaceSpecChar()%><%if (args(0) != "end"){%><%clientDependency.redeclare_class_modification%><%}%>
<%}else{%><%name.replaceSpecChar()%><%}%>

<%script type="uml.NamedElement" name="final"%>
<%if ( isSetModelicaClassStereotypeValueBoolean("s_p_final") == "true" ){%>final <%}%>

<%script type="uml.NamedElement" name="partial"%>
<%if ( isSetModelicaClassStereotypeValueBoolean("s_p_partial") == "true" ){%>partial <%}%>
	
<%script type="uml.NamedElement" name="encapsulated"%>
<%if ( isSetModelicaClassStereotypeValueBoolean("s_p_encapsulated") == "true" ){%>encapsulated <%}%>

<%script type="uml.Classifier" name="public_protected"%>
<%if ( isProtected == "true" ){%>protected <%}%><%-- NOTE: 'public' is default. --%>

<%script type="uml.NamedElement" name="redeclare"%>
<%if ( clientDependency[hasStereotype(getProperty("s_redeclare"))] != null ){%>redeclare <%}%>

<%script type="uml.NamedElement" name="redeclare_class_modification" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_redeclare"), getProperty("s_p_modification"))){%>(<%getStereotypeValue(getProperty("s_redeclare"), getProperty("s_p_modification")).nGet(0, length()).removeFirstChar("(").removeLastChar(")").sepStr(", ")%>)<%}%>

<%script type="uml.NamedElement" name="partialDerivativeFunctionArguments" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_partialDerivativeFunction"), getProperty("s_p_partialDerivativeFunctionArguments"))){%><%getStereotypeValue(getProperty("s_partialDerivativeFunction"), getProperty("s_p_partialDerivativeFunctionArguments")).nGet(0, length()).sepStr(", ")%><%}%>

<%script type="uml.NamedElement" name="replaceable"%>
<%if ( isSetModelicaClassStereotypeValueBoolean("s_p_replaceable") == "true" ){%>replaceable <%}%>

<%script type="uml.NamedElement" name="innerouter"%>
<%getModelicaClassStereotypeValueEnum("s_p_innerouter")%>

<%script type="uml.NamedElement" name="external" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_function"), getProperty("s_p_externalLanguageSpecification")) != ""){%>external "<%getStereotypeValue(getProperty("s_function"), getProperty("s_p_externalLanguageSpecification"))%>"<%}%>
<%if (getStereotypeValue(getProperty("s_function"), getProperty("s_p_externalFunctionCall")) != ""){%> <%getStereotypeValue(getProperty("s_function"), getProperty("s_p_externalFunctionCall"))%><%}%>
<%if (getStereotypeValue(getProperty("s_function"), getProperty("s_p_externalAnnotation")) != ""){%> annotation(<%getStereotypeValue(getProperty("s_function"), getProperty("s_p_externalAnnotation"))%>)<%}%>
<%if (getStereotypeValue(getProperty("s_function"), getProperty("s_p_externalFunctionCall")) != ""){%>;<%}%>
