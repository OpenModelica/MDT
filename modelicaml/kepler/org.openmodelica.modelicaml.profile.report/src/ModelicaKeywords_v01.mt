<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import eadsiw.modelicaml.UmlServices
%>

<%script type="uml.Stereotype" name="ModelicaKeywords_v01"%>
<%if (getAllExtendedMetaclasses().nSize() > 0){%>
	<%for (allOwnedElements().filter("Property")){%>
		<%if (!name.startsWith("base_") && !hasStereotype("NotAModelicaConcept")){%>
			<%if (type.ownedElement.filter("EnumerationLiteral") != null){%>
'<%type.ownedElement.filter("EnumerationLiteral").name.sepStr("' or '")%>';«<%class.name%>»;<%for (class.ownedElement.filter("Property")){%><%if (name.startsWith("base_")){%><%name.replaceAll("base_", "")%> or <%}%><%}%>###end;"<%name%>" (of the «<%class.name%>» stereotype);
<%}%>
			<%if (type.name == "Boolean" && !hasStereotype("NotAModelicaConcept")){%>
'<%name%>';«<%class.name%>»;<%for (class.ownedElement.filter("Property")){%><%if (name.startsWith("base_")){%><%name.replaceAll("base_", "")%> or <%}%><%}%>###end;"<%name%>" (of the «<%class.name%>» stereotype);
<%}%>
		<%}%>
	<%}%>
<%}%>