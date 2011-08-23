<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import ModelicaKeywords_v01
import ModelicaML_Stereotypes_v01
import ModelicaML_extended_UML_meta_classes_01
import ModelicaProperties_v01
import eadsiw.modelicaml.UmlServices

%>
<%script type="uml.Profile" name="ProfileName"%>
<%if (name == "ModelicaML"){%><%name%>_guideline_profile_version_<%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst().replaceAll("\.", "_")%>.csv<%}%>


<%script type="uml.Profile" name="Stereotypes" file="<%ProfileName%>"%>
Note: You need to format the file as follows:
-> resize the rows in order to see all data properly
-> replace all "Â" with ""
-> replace all the "or ###end" with ""
-> copy data into empty col. from another col. if required
-> sort the data if required
-> delete all rows that are empty or not relevant
-> highlight (font, color, etc. ) the headlines or title rows if usefull

ModelicaML (UML Profile);
Profile Version: <%eAnnotations.contents.nFirst().eAllContents().nGet(1).split(":").nLast().trim().split("\)").nFirst()%>;

Which ModelicaML stereotypes can I apply to which UML meta-classes?
To a UML ... ; you can apply the ModelicaML stereotype ...;
<%for (ownedElement.filter("Profile")){%><%if (!hasStereotype("ExcludeFromReport")){%><%ownedElement.filter("Stereotype").ModelicaML_extended_UML_meta_classes_01%><%}%><%}%>

Which UML elements should I create in order to represent a Modelica ... ?;
In order to create a Modelica ...; create a UML ...; and apply the ModelicaML Stereotype ...;
<%for (ownedElement.filter("Profile")){%><%if (!hasStereotype("ExcludeFromReport")){%><%ownedElement.filter("Stereotype").Stereotypes_v01%><%}%><%}%>

Where can I set the Modelica keyword ... ?;
In order to set the Modelica keyword ... ; apply the ModelicaML stereotype ...; to a UML ...; and use the attribute ...;
<%for (ownedElement.filter("Profile")){%><%if (!hasStereotype("ExcludeFromReport")){%><%ownedElement.filter("Stereotype").ModelicaKeywords_v01%><%}%><%}%>

Where can I define ... ?;
You can define a ... using the attribute (with the same name) ...; of the ModelicaML stereotype ...; that is applied to a UML ...; !!! copy this column into the first column!!!;
<%for (ownedElement.filter("Profile")){%><%if (!hasStereotype("ExcludeFromReport")){%><%ownedElement.filter("Stereotype").ModelicaProperties%><%}%><%}%>
