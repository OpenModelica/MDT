<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices

import org.openmodelica.modelicaml.gen.modelica.activity2modelica.ModelicaML_ActivityDiagram2Modelica
import org.openmodelica.modelicaml.gen.modelica.annotation.ModelicaML_annotations

import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_events
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_debug

%>

<%script type="uml.NamedElement" name="ModelicaClassType" post="trim()"%>
record
<%script type="uml.NamedElement" name="LibClassProperties"%>
	// lib properties <%args(0)%>
	Boolean active; // indicates if the state is active.
	Real timeAtActivation; // time when the state is entered.
	Real stime; // stime means "state time". It is is a local timer. It starts when the state is entered and is equal to zero if the state is not active.
	Boolean selfTransitionActivated;

<%script type="uml.StateMachine" name="StateMachinesModelicaClass"%>
<%ModelicaClassType%> <%context.name.replaceSpecChar()%>_StateMachine_<%name.replaceSpecChar()%>
annotation(<%g_annotation%>);
	<%LibClassProperties("STATE MACHINE").trim()%>
	Boolean startBehaviour;
	// REGIONS instantiation
<%for (ownedElement.filter("Region")){%>
	<%stateMachine.name.replaceSpecChar()%>_<%name.replaceSpecChar()%> <%name.replaceSpecChar()%>;
<%}%>

	// REGIONS classes
	<%ownedElement.filter("Region").RegionModelicaClasses(name.replaceSpecChar())%>
	
	// library: SIMPLE STATE
	<%ModelicaClassType%> SimpleState
		annotation(<%g_annotation_state%>);
		<%LibClassProperties("SIMPLE STATE").trim()%>
	end SimpleState;
		
		// library: INITIAL STATE
	<%ModelicaClassType%> InitialState
		annotation(<%g_annotation_initialstate%>);
		<%LibClassProperties("INITIAL STATE").trim()%>
	end InitialState;	
	
			// library: FINAL STATE
	<%ModelicaClassType%> FinalState
		annotation(<%g_annotation_finalstate%>);
		<%LibClassProperties("FINAL STATE").trim()%>
	end FinalState;	
end <%context.name.replaceSpecChar()%>_StateMachine_<%name.replaceSpecChar()%>;

<%singalEventsDefinitions%>

<%script type="uml.Region" name="RegionModelicaClasses" post="trim()"%>
<%ModelicaClassType.trim()%> <%args(0)%>_<%name.replaceSpecChar()%>
	annotation(<%g_annotation%>);
	// SIMPLE STATES instantiation
	<%-- TODO:  || metamodel::isSubmachineState is added -> validate! --%>
	<%for (ownedElement.filter("State")[metamodel::isSimple && eClass.name!="FinalState"]){%>
	SimpleState <%name.replaceSpecChar()%>;
	<%}%>
	<%for (ownedElement.filter("FinalState")){%>
	FinalState <%name.replaceSpecChar()%>;
	<%}%>
	<%for (ownedElement.filter("Pseudostate")[ kind == "initial" ]){%>
	InitialState <%name.replaceSpecChar()%>;
	<%}%>
	<%for (ownedElement.filter("Pseudostate")[ kind == "shallowHistory" ]){%>
	<%owner.filter("Region").name.replaceSpecChar()%>_HistoryState <%name.replaceSpecChar()%>;
	<%}%>
	<%if (ownedElement.filter("State")[metamodel::isComposite] != null){%>
	// COMPOSITE STATES instantiation
		<%for (ownedElement.filter("State")[metamodel::isComposite]){%>
	<%container.name.replaceSpecChar()%>_<%name.replaceSpecChar()%> <%name.replaceSpecChar()%>;
		<%}%>	
	<%}%>
	<%if (ownedElement.filter("State")[metamodel::isComposite] != null){%>
	// COMPOSITE STATES classes
		<%for (ownedElement.filter("State")[metamodel::isComposite]){%>
	<%ModelicaClassType.trim()%> <%container.name%>_<%name.replaceSpecChar()%>
		annotation(<%g_annotation_state%>);
		<%LibClassProperties("COMPOSITE STATE").trim()%>
		// REGIONS instantiation
				<%for (ownedElement.filter("Region")){%>
		<%StateOrStateMahchineName.trim()%>_<%name.replaceSpecChar()%> <%name.replaceSpecChar()%>;
				<%}%>			
		<%ownedElement.filter("Region").RegionModelicaClasses(name.replaceSpecChar())%>
	end <%container.name%>_<%name.replaceSpecChar()%>;
		<%}%>
	<%}%>	
<%if (ownedElement.filter("State")[metamodel::isSubmachineState] != null){%>
	// Submachine STATES instantiation
		<%for (ownedElement.filter("State")[metamodel::isSubmachineState]){%>
	<%container.name.replaceSpecChar()%>_<%name.replaceSpecChar()%> <%name.replaceSpecChar()%>;
		<%}%>	
	<%}%>	
	<%if (ownedElement.filter("State")[metamodel::isSubmachineState] != null){%>
	// Submachine STATES classes
		<%for (ownedElement.filter("State")[metamodel::isSubmachineState]){%>
	<%ModelicaClassType.trim()%> <%container.name%>_<%name.replaceSpecChar()%>
		annotation(<%g_annotation_state%>);
		<%LibClassProperties("SUBMACHINE STATE").trim()%>
		// Submachine REGIONS instantiation
		<%submachine.submachineInstantiation(name.replaceSpecChar())%>
		<%submachine.ownedElement.filter("Region").RegionModelicaClasses(name.replaceSpecChar())%> //test	
	end <%container.name%>_<%name.replaceSpecChar()%>;
		<%}%>
	<%}%>		
	<%if (ownedElement.filter("Pseudostate")[ kind == "shallowHistory" ]){%>
		<%HistoryModelicaClasses("")%>
	<%}%>
	
	<%regionDebugVariables%>
	
end <%args(0)%>_<%name.replaceSpecChar()%>;

<%script type="uml.StateMachine" name="submachineInstantiation"%>
<%for (ownedElement.filter("Region")){%>
		<%args(0)%>_<%name.replaceSpecChar()%> <%name.replaceSpecChar()%>;
<%}%>	
<%script type="uml.Region" name="HistoryModelicaClasses" post="trim()"%>

	<%ModelicaClassType%> <%name.replaceSpecChar()%>_HistoryState
		annotation(<%g_annotation_historystate%>);
		<%LibClassProperties("HISTORY STATE").trim()%>
		type HistoryStateT=enumeration( 
		<%ownedElement.filter("State").name.replaceSpecChar().sepStr(", ")%>);
		HistoryStateT lastActive<%--(start="<%ownedElement.filter("Pseudostate")[kind == "shallowHistory"].outgoing.target.name%>")--%>;
	end <%name.replaceSpecChar()%>_HistoryState;
	
<%script type="uml.Region" name="StateOrStateMahchineName" post="trim()"%>
<%if (stateMachine!=null && stateMachine.submachineState != null){%>
<%stateMachine.submachineState.name.replaceSpecChar()%> //TEST
<%}else{%>
<%if (stateMachine==null){%><%state.name.replaceSpecChar()%><%}else{%><%stateMachine.name.replaceSpecChar()%><%}%>
<%}%>

<%-- STATE MACHNES instantiation ########################################################################################### --%>
<%script type="uml.StateMachine" name="StateMachineInstances"%>

// STATE MACHNE instantiation 
	<%context.name.replaceSpecChar()%>_StateMachine_<%name.replaceSpecChar()%> <%name.replaceSpecChar()%>;
	<%signalEventsInstantiation.indentTab()%>
	<%relativeTimeEventsInstantiation%>
