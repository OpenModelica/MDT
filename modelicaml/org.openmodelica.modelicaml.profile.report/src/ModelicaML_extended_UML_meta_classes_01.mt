<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import eadsiw.modelicaml.UmlServices
%>
<%script type="uml.Stereotype" name="ModelicaML_extended_UML_meta_classes_01"%>
<%if (getAllExtendedMetaclasses().nSize()>0 && !hasStereotype("ExcludeFromReport")){%>
<%getAllExtendedMetaclasses().name.sepStr(" or ")%>;«<%name%>»;
<%}%>


