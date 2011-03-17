<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UML2ModelicaServices
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLMacros
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLEventsServices

import org.openmodelica.modelicaml.gen.modelica.uml2modelica.core._0_ main_uml2modelica 
%>



<%-- FOR TEST ONLY ###################################################################### --%>

<%-- exclude also: import org.openmodelica.modelicaml.uml2modelica.core._0_ main_uml2modelica --%>

<%--<%script type="uml.StateMachine" name="test" post="trim()" file="<%name%>.txt" %>
<%singalEventsDefinitions%>

<%instantiateEvents%>

<%changeEventsStatements%>

<%signalEventsStatements%>

<%singalPropertiesStatements%>

<%region.forAllTransistions%>

<%script type="uml.Region" name="forAllTransistions" post="trim()"%>
<%for (ownedElement.filter("Transition")){%>
<%name%> Transistion condition: <%guardCode("state_stime_parth")%>	
<%}%>
--%>
<%-- FOR TEST ONLY END ###################################################################### --%>





<%-- GUARD (condition including triggers code ) ###################################################################### --%>
<%script type="uml.Transition" name="guardCode" post="trim()"%>
<%triggersCode.expandTriggersMacros(args(0))%> <%guardCodeInc(args(0))%>

<%script type="uml.Transition" name="guardCodeInc" post="trim()"%>
<%if (getStereotypeValue(getProperty("s_stateDependency"), getProperty("s_p_isInState")) != null){%>
	<%getStereotypeValue(getProperty("s_stateDependency"), getProperty("s_p_isInState")).isInState(metamodel::redefinitionContext.qualifiedName, getStereotypeValue(getProperty("s_stateDependency"), getProperty("s_p_logicalOperator")).name).indentSpace()%> 
<%}%>
<%if (guard.specification != null){%>
	<%-- <%guard.specification.filter("LiteralString").value.expandGuardMacros(args(0)+".stime" )%> --%>
	<%guard.specification.filter("OpaqueExpression").body.expandGuardMacros(args(0)+".stime" )%>
<%}else{%>
	true
<%}%>

<%script type="uml.State" name="isInState" post="trim()"%>
<%-- TODO: BUG: replaceFirst(args(0) + "::", "") does not work!--%><%qualifiedName.replaceFirst(args(0) + "::", "").replaceSpecCharExceptThis("::").replaceAll("::","." )%>.active <%args(1)%> 


<%-- EVENTS ###################################################################### --%>



<%-- Signal EVENTS ###################################################################### --%>
<%script type="uml.StateMachine" name="singalEventsDefinitions" post="trim()"%><%-- DEPRECATED, NOT USED ANYMORE--%>
<%for (getEvents().filter("SignalEvent")){%>
/* M@ SignalEvent: <%signal.name%> definition*/
record <%signal.name.replaceSpecChar()%>_Definition
	Integer counter; // indicates how many times the the signal has occured.
	Boolean occurrence; // indicates that signal has occured.
	<%--  Real lastSent; // TEST: represents the last time when the SignalEvent occured. --%>
	/* M@SignalEvent, Property set*/
	<%signal.singalProperties%>
end <%signal.name.replaceSpecChar()%>_Definition;
<%}%>

<%script type="uml.Signal" name="singalProperties" post="trim()"%>
<%for (ownedElement.filter("Property")){%>
<%component_clause%>
<%}%>

<%script type="uml.StateMachine" name="signalEventsInstantiation" post="trim()"%>
<%for (getEvents().filter("SignalEvent")){%>
<%signal.name.replaceSpecChar()%>_Definition <%signal.name.replaceSpecChar()%>; /* M@ SignalEvent "<%signal.name%>": instantiation*/
<%}%>


<%script type="uml.StateMachine" name="singalPropertiesStatements" post="trim()"%>
<%for (getEvents().filter("SignalEvent")){%>
/* M@SignalEvent "<%signal.name%>": default statements for the contained properties */
<%--/* M@INFO: These are default statements for all signal properties for the case they are not used (set) in the actions of the state machine. */
--%><%signal.singalPropertiesStatements%>
<%}%>

<%script type="uml.Signal" name="singalPropertiesStatements" post="trim()"%>
<%for (ownedElement.filter("Property")){%>
<%owner.filter("Signal").name.replaceSpecChar()%>.<%name.replaceSpecChar()%> := <%owner.filter("Signal").name.replaceSpecChar()%>.<%name.replaceSpecChar()%>; 
<%}%>

<%script type="uml.StateMachine" name="signalEventsStatements" post="trim()"%>
<%for (getEvents().filter("SignalEvent")){%>
<%--/* M@SignalEvent "<%signal.name.%>" processing */  --%>
<%-- if <%signal.name.replaceSpecChar()%>.occurrence then --%>  
<%--  if <%signal.name.replaceSpecChar()%>.occurrence and time > <%signal.name.replaceSpecChar()%>.lastSent then  --%><%-- // TEST: A SignalEvent is consumed after time has advanced (i.e., it is present in event iterations) --%>
<%--	<%signal.name.replaceSpecChar()%>.occurrence := false;
end if;
--%><%}%>
<%for (owner.filter("Class").ownedElement.filter("Signal")){%>
if <%name%>.occurrence then 
	<%name%>.counter := pre(<%name%>.counter) + 1;
end if;
<%name%>.occurrence := false;
<%}%>




<%-- Change EVENTS ###################################################################### --%>
<%script type="uml.Classifier" name="changeEventsInstantiation" post="trim()"%>
<%for (ownedElement.filter("StateMachine")[hasStereotype(getProperty("s_modelicaStateMachine"))].getEventsFromAllStateMachines().filter("ChangeEvent")){%>
Boolean <%name.replaceSpecChar()%>; /* M@ChangeEvent instantiation*/
<%}%>

<%script type="uml.Classifier" name="changeEventsStatements" post="trim()"%>
algorithm
<%for (ownedElement.filter("StateMachine")[hasStereotype(getProperty("s_modelicaStateMachine"))].getEventsFromAllStateMachines().filter("ChangeEvent")){%>
	<%name.replaceSpecChar()%> := <%changeExpression.filter("LiteralString").value%>; /* M@ChangeEvent statement */
<%}%>





<%-- Absolute Time EVENTS ###################################################################### --%>
<%script type="uml.Classifier" name="absoluteTimeEventsInstantiation" post="trim()"%>
<%for (ownedElement.filter("StateMachine")[hasStereotype(getProperty("s_modelicaStateMachine"))].getEventsFromAllStateMachines().filter("TimeEvent")[!isRelative]){%>
Boolean <%name.replaceSpecChar()%>; /* M@ TimeEvent (absolute) instantiation*/
<%}%>

<%script type="uml.Classifier" name="absoluteTimeEventsTimeStatements" post="trim()"%>
algorithm
<%for (ownedElement.filter("StateMachine")[hasStereotype(getProperty("s_modelicaStateMachine"))].getEventsFromAllStateMachines().filter("TimeEvent")[!isRelative]){%>
	<%name.replaceSpecChar()%> := time > <%when.filter("TimeExpression").name%>; /* M@ TimeEvent (absolute) statement */
<%}%>




<%-- Relative Time EVENTS ###################################################################### --%>
<%script type="uml.StateMachine" name="relativeTimeEventsInstantiation" post="trim()"%>
<%region.getRelativeTimeEventsForRegion(name.replaceSpecChar(), "instantiation" )%>

<%script type="uml.Region" name="getRelativeTimeEventsForRegion" post="trim()"%>
<%for (ownedElement.filter("State")){%>
<%getRelativeTimeEventFromState(args(0) + "." + container.name.replaceSpecChar() + "." + name.replaceSpecChar(), args(1))%>
<%}%>

<%for (ownedElement.filter("State")[metamodel::isComposite]){%>
/* M@ start composite <%name%> */
	<%for (region.filter("Region")){%>
<%getRelativeTimeEventsForRegion(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar(), args(1)).indentTab()%>
	<%}%>
/* M@ end composite <%name%> */
<%}%>

<%for (ownedElement.filter("State")[metamodel::isSubmachineState]){%>
/* M@ start submachine composite <%name%> */
<%submachine.subMachineRegionGetRelativeTimeEvents(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(), args(1))%>
/* M@ end submachine composite <%name%> */
<%}%>

<%script type="uml.StateMachine" name="subMachineRegionGetRelativeTimeEvents"%>
<%for (region.filter("Region")){%>
<%getRelativeTimeEventsForRegion(args(0), args(1)).indentTab()%>
<%}%>

<%script type="uml.State" name="getRelativeTimeEventFromState" post="trim()"%>
<%if (args(1) == "instantiation"){%>
	<%for (getRelativeTimeEvents().filter("TimeEvent")){%>
Boolean <%args(0).replaceAll("\\.", "_")%>_<%name.replaceSpecChar()%>; /* M@ TimeEvent (relative to state machine state) instantiation*/
	<%}%>
<%}%>
<%if (args(1) == "statements"){%>
	<%for (getRelativeTimeEvents().filter("TimeEvent")){%>
<%args(0).replaceAll("\\.", "_")%>_<%name.replaceSpecChar()%> := <%args(0)%>.stime > <%when.filter("TimeExpression").name%>; /* M@ TimeEvent (relative) statement */
	<%}%>
<%}%>


<%script type="uml.StateMachine" name="relativeTimeEventsStatements" post="trim()"%>
<%region.getRelativeTimeEventsForRegion(name.replaceSpecChar(), "statements" )%>









<%-- Triggers EVENTS ###################################################################### --%>
<%script type="uml.Transition" name="triggersCode" post="trim()"%>
<%if (trigger.eventCode != ""){%>(<%trigger.eventCode.sepStr(" or ")%>) and <%}%>

<%script type="uml.Trigger" name="eventCode" post="trim()"%>
<%if (event.eClass().name == "SignalEvent"){%>
change(<%event.filter("SignalEvent").signal.name.replaceSpecChar()%>.counter) <%-- change() functions is not necessary for SignaEvents. However, without it Dymola returns wrong results. --%>
	<%}else{%>
		<%if (event.eClass().name == "ChangeEvent"){%>
edge(<%event.name.replaceSpecChar()%>)
		<%}else{%>
			<%if (event.eClass().name == "TimeEvent" && !event.filter("TimeEvent").isRelative){%>
edge(<%event.name.replaceSpecChar()%>)
			<%}else{%>
				<%if (event.eClass().name == "TimeEvent" && event.filter("TimeEvent").isRelative){%>
edge(LOCAL_TIMER_PATH_PREFIX_<%event.name.replaceSpecChar()%>)
				<%}else{%>
			<%}%>
		<%}%>
	<%}%>
<%}%>



