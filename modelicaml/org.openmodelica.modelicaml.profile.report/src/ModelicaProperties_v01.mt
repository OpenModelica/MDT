<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import eadsiw.modelicaml.UmlServices
%>

<%script type="uml.Stereotype" name="ModelicaProperties"%>
<%if ((getAllExtendedMetaclasses().nSize() > 0) && getAllAttributes().nSize() > 0  && !hasStereotype("NotAModelicaConcept")){%>
 ;«<%name%>»;<%getAllExtendedMetaclasses().name.sepStr(" or ")%>;<%for (getAllAttributes().filter("Property")){%>
<%if (type.name == "String" && !name.startsWith("base_") && !hasStereotype("NotAModelicaConcept")){%>'<%name%>' or <%}%><%}%>###end;
<%}%>