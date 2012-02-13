<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import ModelicaKeywords_v01
import ModelicaML_Stereotypes_v01

%>

<%script type="uml.Profile" name="Stereotypes" file="ModelicaML_Language_Guide.txt"%>
Modelica concepts, corresponding ModelicaML stereotypes

<%for (ownedElement.filter("Profile")){%><%ownedElement.filter("Stereotype").Stereotypes_v01%><%}%>

Modelica keywords, corresponding ModelicaML stereotypes:

<%for (ownedElement.filter("Profile")){%><%ownedElement.filter("Stereotype").ModelicaKeywords_v01%><%}%>
