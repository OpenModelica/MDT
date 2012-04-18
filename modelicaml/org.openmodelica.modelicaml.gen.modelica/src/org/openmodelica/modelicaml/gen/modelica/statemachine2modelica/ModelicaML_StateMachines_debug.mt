<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices

import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLMacros
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLEventsServices

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._0_ main_uml2modelica 

%>

<%-- FOR TEST ONLY ###################################################################### --%>

<%-- exclude also: import org.openmodelica.modelicaml.uml2modelica.core._0_ main_uml2modelica --%>
<%--<%script type="uml.StateMachine" name="test" post="trim()" file="<%name%>.txt" %>
<%if (getProperty("c_debugMode")){%>
<%region.regionDebugCode(name.replaceSpecChar())%>
<%}%>
--%>
<%-- FOR TEST ONLY END ###################################################################### --%>

<%script type="uml.Region" name="regionDebugVariables" post="trim()"%>
<%if (getProperty("c_debugMode")){%>
/* M@ debug variables */
Integer numberOfActiveStates;

<%}%>

<%script type="uml.Region" name="regionDebugCode" post="trim()"%>
/* M@ reset debug variabels */
<%args(0)%>.<%name.replaceSpecChar()%>.numberOfActiveStates := 0; 

/* M@ start debug code of region "----<%args(0)%>" ***/
<%for (ownedElement.filter("State")){%>
if <%args(0)%>.<%container.name.replaceSpecChar()%>.<%name.replaceSpecChar()%>.active then 
	<%args(0)%>.<%container.name.replaceSpecChar()%>.numberOfActiveStates := <%args(0)%>.<%container.name.replaceSpecChar()%>.numberOfActiveStates + 1; 
end if;
<%}%>
<%for (ownedElement.filter("Pseudostate")[kind=="initial" || kind=="shallowHistory"]){%>
if <%args(0)%>.<%container.name.replaceSpecChar()%>.<%name.replaceSpecChar()%>.active then 
	<%args(0)%>.<%container.name.replaceSpecChar()%>.numberOfActiveStates := <%args(0)%>.<%container.name.replaceSpecChar()%>.numberOfActiveStates + 1; 
end if;
<%}%>

/* M@ validation code start*/

if <%args(0)%>.active then 
	assert(not (<%args(0)%>.<%name.replaceSpecChar()%>.numberOfActiveStates < 1), "<%args(0)%>.<%name.replaceSpecChar()%> has no active states although the parent state is active!");
	assert(not (<%args(0)%>.<%name.replaceSpecChar()%>.numberOfActiveStates > 1), "<%args(0)%>.<%name.replaceSpecChar()%> has multiple active states which are mutually exclusive!");
end if;

if not <%args(0)%>.active then
	assert(<%args(0)%>.<%name.replaceSpecChar()%>.numberOfActiveStates == 0, "<%args(0)%>.<%name.replaceSpecChar()%> has active states although the parent state is not active!");
end if;

<%-- Terminate() is not yet supported by MathModelica. Using terminate() the compiler will return true (i.e. a successful simulation termintated at cretain time. 
This is not the purpose of this validation code. It should report an error. That is the reason why assert is used.)
if (<%args(0)%>.active) and (<%args(0)%>.<%name.replaceSpecChar()%>.numberOfActiveStates < 1) then 
	//terminate("<%args(0)%>.<%name.replaceSpecChar()%> has no active states!");
end if;
if (<%args(0)%>.active) and (<%args(0)%>.<%name.replaceSpecChar()%>.numberOfActiveStates > 1) then 
	//terminate("<%args(0)%>.<%name.replaceSpecChar()%> has multiple active states which are mutually exclusive!");
end if;
if (not <%args(0)%>.active) and (<%args(0)%>.<%name.replaceSpecChar()%>.numberOfActiveStates > 0) then 
	//terminate("<%args(0)%>.<%name.replaceSpecChar()%> has active states although the parent state is not active!");
end if;
--%>
/* M@ validation code start*/

<%for (ownedElement.filter("State")[metamodel::isComposite]){%>
/* M@ start composite <%name%> */
	<%for (region.sortRegions().filter("Region")){%>
	<%regionDebugCode(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar()).indentTab()%>
	<%}%>
/* M@ end composite <%name%> */
<%}%>

<%for (ownedElement.filter("State")[metamodel::isSubmachineState]){%>
/* M@ start submachine composite <%name%> */
	<%submachine.subMachineRegionDebugCode(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
/* M@ end submachine composite <%name%> */
<%}%>

/* M@ end debug code of region "<%name%>" ***/


<%script type="uml.StateMachine" name="subMachineRegionDebugCode"%>
<%for (region.sortRegions().filter("Region")){%>
<%regionDebugCode(args(0)).indentTab()%>
<%}%>



