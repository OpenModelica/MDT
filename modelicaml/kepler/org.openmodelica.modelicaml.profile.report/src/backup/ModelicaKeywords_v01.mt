<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
%>

<%script type="uml.Stereotype" name="ModelicaKeywords_v01"%>
<%if (getAllExtendedMetaclasses().nSize() > 0){%>
	<%for (allOwnedElements().filter("Property")){%>
		<%if (!name.startsWith("base_")){%>
			<%if (type.ownedElement.filter("EnumerationLiteral") != null){%>
'<%type.ownedElement.filter("EnumerationLiteral").name.sepStr("' or '")%>'
	- Apply «<%class.name%>» to UML Element(s): <%for (class.ownedElement.filter("Property")){%><%if (name.startsWith("base_")){%><%name.replaceAll("base_", "")%> <%}%><%}%> 
	- Use the attribute "<%name%>" (of the «<%class.name%>» stereotype) to select the keyword
		
			<%}%>
			<%if (type.name == "Boolean"){%>
'<%name%>'
	- Apply «<%class.name%>» to UML Element(s): <%for (class.ownedElement.filter("Property")){%><%if (name.startsWith("base_")){%><%name.replaceAll("base_", "")%> <%}%><%}%> 
	- Use the attribute "<%name%>" (of the «<%class.name%>» stereotype) to select the keyword
	
			<%}%>
		<%}%>
	<%}%>
<%}%>