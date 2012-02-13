<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core.helper
%>

<%script type="uml.Classifier" name="equation" post="trim()"%>
<%equations_from_UML_Constraints%><%-- NOTE: SysML specific. --%>
<%equations_from_UML_OpaqueBehavior%><%-- NOTE: ModelicaML specific. --%>
<%asserts%><%-- NOTE: ModelicaML specific, not supported by SysML4Modelica.  --%>

<%-- ### Equations --%>
<%script type="uml.Classifier" name="equations_from_UML_Constraints" post="trim()"%><%-- NOTE: SysML specific. --%>
<%if (ownedElement.filter("Constraint")[hasStereotype(getProperty("s_equation"))] != null){%>
	<%for (ownedElement.filter("Constraint")[hasStereotype(getProperty("s_equation"))]){%>
	<%initial%>equation
<%if (specification.filter("OpaqueExpression") != null){%><%specification.filter("OpaqueExpression").body.indentTab()%><%}%><%if (specification.filter("LiteralString") != null){%><%specification.filter("LiteralString").value.indentTab()%><%}%><%-- NOTE: to cover both cases ... --%>
	<%}%>
<%}%>

<%script type="uml.Classifier" name="equations_from_UML_OpaqueBehavior" post="trim()"%><%-- NOTE: ModelicaML specific. --%>
<%if (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_equation"))] != null){%>
	<%for (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_equation"))]){%>
	<%initial%>equation
<%body.indentTab()%>
	<%}%>
<%}%>

<%script type="uml.Classifier" name="asserts" post="trim()"%><%-- NOTE: ModelicaML specific, not supported by SysML4Modelica. --%>
<%if (getProperty("c_includeAsserts") && ownedElement.filter("Constraint")[hasStereotype(getProperty("s_assert"))] != null){%>
	equation
	<%for (ownedElement.filter("Constraint")[hasStereotype(getProperty("s_assert"))]){%>
<%--assert(<%if (specification.filter("OpaqueExpression") != null){%><%specification.filter("OpaqueExpression").body%><%}%><%if (specification.filter("LiteralString") != null){%><%specification.filter("LiteralString").value%><%}%><%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_condition"))%>, "<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_message")).trim()%>", AssertionLevel.<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_level")).name%>); --%> <%-- TODO AssertionLevel.warning does not work in Dymola and MathModelica --%> 
<%--assert(<%if (specification.filter("OpaqueExpression") != null){%><%specification.filter("OpaqueExpression").body%><%}%><%if (specification.filter("LiteralString") != null){%><%specification.filter("LiteralString").value%><%}%><%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_condition"))%>, "<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_message")).trim()%>");--%>
assert(<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_condition"))%>, "<%getStereotypeValue(getProperty("s_assert"), getProperty("s_p_message")).trim()%>"); 
	<%}%>
<%}%>
<%-- ### Equations END--%>


<%script type="uml.Classifier" name="algorithm" post="trim()"%>
<%if (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_algorithm"))] != null){%>
	<%for (ownedElement.filter("OpaqueBehavior")[hasStereotype(getProperty("s_algorithm"))]){%>
	<%initial%>algorithm
<%body.indentTab()%>
	<%}%>
<%}%>

<%script type="uml.FunctionBehavior" name="algorithm" post="trim()"%>
<%if (body!=""){%>
algorithm
<%body.indentTab()%><%}else{%><%}%>

<%script type="uml.NamedElement" name="initial"%>
<%if (getStereotypeValue(getProperty("s_equation"),getProperty("s_p_initial"))){%>initial <%}%>
<%if (getStereotypeValue(getProperty("s_algorithm"),getProperty("s_p_initial"))){%>initial <%}%>

<%-- connect equations  ############################################--%>
<%script type="uml.Class" name="connect_equations" post="trim()"%>
<%if (ownedElement.filter("Connector")[hasStereotype(getProperty("s_connect"))] != null){%>	
	equation
	<%for (ownedElement.filter("Connector")[hasStereotype(getProperty("s_connect"))]){%>
		<%-- NOTE: If explicit ends are defined take them first --%>
		<%if (getStereotypeValue(getProperty("s_connect"), getProperty("s_p_connectionEnds")) != ""){%>
	connect(<%getStereotypeValue(getProperty("s_connect"), getProperty("s_p_connectionEnds"))%>);	
		<%}else{%>
			<%if (ownedElement.filter("ConnectorEnd").nFirst().filter("ConnectorEnd").role.type.qualifiedName 
			!= ownedElement.filter("ConnectorEnd").nLast().filter("ConnectorEnd").role.type.qualifiedName){%><%-- NOTE: The types are nominally different -> try to match the next level of connector components using properties names and types. If there is no match then just connect. --%>
	<%tryToMatchConnections(ownedElement.filter("ConnectorEnd").nFirst().ConnectorInstance)%>
			<%}else{%><%-- NOTE: The types are nominally the same -> generate a simple connection --%>
	connect(<%ownedElement.filter("ConnectorEnd").nFirst().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nFirst().role.name.replaceSpecChar().trim()%>, <%ownedElement.filter("ConnectorEnd").nLast().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nLast().role.name.replaceSpecChar().trim()%>);
			<%}%>
		<%}%>	
	<%}%>
<%}%>

<%script type="uml.Connector" name="tryToMatchConnections" post="trim()"%>
<%-- NOTE: try to match from left --%><%if (ownedElement.filter("ConnectorEnd").nFirst().role.type.containsAPropertyOfTheSameNameAndType(ownedElement.filter("ConnectorEnd").nLast().role.name, ownedElement.filter("ConnectorEnd").nLast().role.type.qualifiedName, ownedElement.filter("ConnectorEnd").nFirst().role.name) != ""){%>
connect(<%ownedElement.filter("ConnectorEnd").nFirst().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nFirst().role.type.containsAPropertyOfTheSameNameAndType(ownedElement.filter("ConnectorEnd").nLast().role.name, ownedElement.filter("ConnectorEnd").nLast().role.type.qualifiedName, ownedElement.filter("ConnectorEnd").nFirst().role.name)%>, <%ownedElement.filter("ConnectorEnd").nLast().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nLast().role.name.replaceSpecChar().trim()%>);
<%}else{%>
<%-- NOTE: try to match from right --%><%if (ownedElement.filter("ConnectorEnd").nLast().role.type.containsAPropertyOfTheSameNameAndType(ownedElement.filter("ConnectorEnd").nFirst().role.name, ownedElement.filter("ConnectorEnd").nFirst().role.type.qualifiedName, ownedElement.filter("ConnectorEnd").nLast().role.name) != ""){%>
connect(<%ownedElement.filter("ConnectorEnd").nLast().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nLast().role.type.containsAPropertyOfTheSameNameAndType(ownedElement.filter("ConnectorEnd").nFirst().role.name, ownedElement.filter("ConnectorEnd").nFirst().role.type.qualifiedName, ownedElement.filter("ConnectorEnd").nLast().role.name)%>, <%ownedElement.filter("ConnectorEnd").nFirst().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nFirst().role.name.replaceSpecChar().trim()%>);
<%-- NOTE: No match -> just connect. NOTE: No further structural types check is performed! --%><%}else{%>
connect(<%ownedElement.filter("ConnectorEnd").nFirst().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nFirst().role.name.replaceSpecChar().trim()%>, <%ownedElement.filter("ConnectorEnd").nLast().ConnectorInstance%><%ownedElement.filter("ConnectorEnd").nLast().role.name.replaceSpecChar().trim()%>);
	<%}%>
<%}%>

<%script type="uml.Type" name="containsAPropertyOfTheSameNameAndType" post="trim()"%>
<%ownedElement.filter("Property").matchPropertyNameAndType(args(0), args(1), args(2))%>

<%script type="uml.Property" name="matchPropertyNameAndType" post="trim()"%>
<%if (name == args(0)){%><%-- These names match. --%>
	 <%if ( type.qualifiedName == args(1) ){%><%args(2).replaceSpecChar()%>.<%name.replaceSpecChar()%> 
	<%}else{%><%--Names matche but not the types. --%>
	<%}%>
<%}else{%><%-- The names do not match. --%>
<%}%>

<%script type="uml.ConnectorEnd" name="ConnectorInstance" post="trim()"%>
<%if (partWithPort.name != null){%>
<%partWithPort.name.replaceSpecChar().trim()%>.
<%}else{%><%}%>
<%-- END connect equations  ############################################--%>
