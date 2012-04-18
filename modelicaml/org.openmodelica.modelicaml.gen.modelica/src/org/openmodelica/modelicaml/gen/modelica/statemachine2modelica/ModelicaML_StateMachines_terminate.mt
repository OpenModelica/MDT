<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UML2ModelicaServices
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices

import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLMacros
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLEventsServices

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._0_ main_uml2modelica 

%>


<%script type="uml.Region" name="regionTerminateCode" post="trim()"%>
/* M@ start terminate code of region "<%args(0)%>" ***/
<%for (ownedElement.filter("Pseudostate")[kind=="initial" || kind=="shallowHistory"]){%>
<%args(0)%>.<%container.name.replaceSpecChar()%>.<%name.replaceSpecChar()%>.active := false;
<%}%>
<%for (ownedElement.filter("State")){%>
<%args(0)%>.<%container.name.replaceSpecChar()%>.<%name.replaceSpecChar()%>.active := false; 
<%}%>
<%for (ownedElement.filter("State")[metamodel::isComposite]){%>
/* M@ start composite <%name%> */
<%for (region.sortRegions().filter("Region")){%>
<%regionTerminateCode(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
<%}%>
/* M@ end composite <%name%> */
<%}%>
<%for (ownedElement.filter("State")[metamodel::isSubmachineState]){%>
/* M@ start submachine composite <%name%> */
<%submachine.subMachineRegionTerminateCode(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
/* M@ end submachine composite <%name%> */
<%}%>
/* M@ end terminate code of region "<%name%>" ***/


<%script type="uml.StateMachine" name="subMachineRegionTerminateCode" post="trim()"%>
<%for (region.sortRegions().filter("Region")){%>
<%regionTerminateCode(args(0))%>
<%}%>
