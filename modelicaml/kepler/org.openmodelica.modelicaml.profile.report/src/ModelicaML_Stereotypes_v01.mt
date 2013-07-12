<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import eadsiw.modelicaml.UmlServices
%>

<%script type="uml.Stereotype" name="Stereotypes_v01"%>
<%if (getAllExtendedMetaclasses().nSize()>0 && !hasStereotype("NotAModelicaConcept")){%><%name.replaceAll("Modelica", "")%>;<%getAllExtendedMetaclasses().name.sepStr(" or ")%>;«<%name%>»;
<%}%>