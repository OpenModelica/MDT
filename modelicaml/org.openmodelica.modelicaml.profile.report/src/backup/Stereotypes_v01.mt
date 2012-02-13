<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
%>

<%script type="uml.Stereotype" name="Stereotypes_v01"%>
<%if (getAllExtendedMetaclasses().nSize()>0){%><%name.replaceAll("Modelica", "")%>
- Create an UML Element(s): <%getAllExtendedMetaclasses().name.sepStr(" or ")%> 
- Apply the stereotype «<%name%>»

<%}%>