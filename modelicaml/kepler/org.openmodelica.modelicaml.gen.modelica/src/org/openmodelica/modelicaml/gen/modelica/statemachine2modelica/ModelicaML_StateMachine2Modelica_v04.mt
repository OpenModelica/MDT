<%--encoding=utf-8--%>
<%
metamodel http://www.eclipse.org/uml2/2.1.0/UML
import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.services.ModelicaMLServices
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices

import org.openmodelica.modelicaml.gen.modelica.activity2modelica.ModelicaML_ActivityDiagram2Modelica
import org.openmodelica.modelicaml.gen.modelica.annotation.ModelicaML_annotations

import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachinesInstantiation_v04
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_entry_do_exit
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_events
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_debug
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_terminate
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_preemption
import org.openmodelica.modelicaml.gen.modelica.statemachine2modelica.ModelicaML_StateMachines_sampling

%>
<%script type="uml.StateMachine" name="stateMachineBehaviorCode" post="trim()"%>
<%-- set statemachine active if it is not a substatemachine --%>
<%if (submachineState == null){%> 
/*** start behaviour code of state machine "<%name.replaceSpecChar()%>" ***/
algorithm <%-- START: UPDATED 23.01.2013 --%>
	<%if (getStereotypeValue(getProperty("s_modelicaStateMachine"), getProperty("s_p_additionalCode")) != null){%>
	/* Additional code */
	<%getStereotypeValue(getProperty("s_modelicaStateMachine"), getProperty("s_p_additionalCode"))%><%}%>
	
	<%signalEventsStatements%>
	<%singalPropertiesStatements%>
	<%relativeTimeEventsStatements%>
	/* initial state machine "<%name.replaceSpecChar()%>" activation */
	<%name.replaceSpecChar()%>.startBehaviour:=true; 
<%-- Not correct according the Modelica spec. Improved version see below.	
	when <%name.replaceSpecChar()%>.startBehaviour then
	<%name.replaceSpecChar()%>.active:=true; 
		<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
		<%setInitalActive(owner.filter("StateMachine").name.replaceSpecChar()+"."+name.replaceSpecChar())%>
		<%}%>			
	end when;
--%>
	/* activate all initial states during initialization phase */
	when initial() then
		if <%name.replaceSpecChar()%>.startBehaviour then 
			<%name.replaceSpecChar()%>.active:=true; 
			<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
			<%setInitalActive(owner.filter("StateMachine").name.replaceSpecChar()+"."+name.replaceSpecChar())%>
		<%}%>
		end if;			
	end when;
	/* aux. variable for calculating the timer of the state machine '<%name.replaceSpecChar()%>' */
	<%setTimeAtActivationToTime(name.replaceSpecChar())%>
	/* timer for the state machine '<%name.replaceSpecChar()%>' */
	<%setStime(name.replaceSpecChar())%><%-- END: UPDATED 23.01.2013 --%>
<%--	<%setTimeAtActivationToNull(name.replaceSpecChar())%>	--%>
	<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%regionBehaviorCode(owner.filter("StateMachine").name.replaceSpecChar(),self(),self().filter("Region").stateMachine)%>
	<%}%>
	/*start terminate code for state machine "<%name.replaceSpecChar()%>"*/
	if (not <%name.replaceSpecChar()%>.active) then
	<%region.regionTerminateCode(name.replaceSpecChar())%>
	end if;
	/*end terminate code for state machine "<%name.replaceSpecChar()%>"*/
/*** end behaviour code of state machine "<%name.replaceSpecChar()%>" ***/	
	<%}%>
	<%if (submachineState == null){%>
<%region.regionDebugCode(name.replaceSpecChar())%>
	<%}%>

<%script type="uml.Region" name="setInitalActive" post="trim()"%>
<%for (ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst()){%>
<%args(0)+"."+name.replaceSpecChar()%>.active:=true;
<%}%>

<%script type="uml.NamedElement" name="setTimeAtActivationToTime" post="trim()"%>
<%--args(0) is the complete element dot name --%>
	when {<%args(0)%>.active, <%args(0)%>.selfTransitionActivated} then
		<%args(0)%>.timeAtActivation := time;
		<%args(0)%>.selfTransitionActivated := false;
end when;
<%script type="uml.NamedElement" name="setTimeAtActivationToNull"%>
<%--args(0) is the complete element dot name--%>

<%--directly after the transition that activates the target state --%>
<%--if not active for source state --%>
	if not <%args(0)%>.active then 
		<%args(0)%>.timeAtActivation := 0;
	end if;

<%script type="uml.NamedElement" name="setStime"%>
<%--if active for target state --%>
if <%args(0)%>.active then 
	<%args(0)%>.stime := time - <%args(0)%>.timeAtActivation;
end if;
if not <%args(0)%>.active then
	<%args(0)%>.stime := 0;
end if;

<%script type="uml.Region" name="regionBehaviorCode" post="trim()"%>
<%--args(2) == StateMachine--%>
/*** start behaviour code of region "<%args(0)%>" ***/
	<%for (ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"]){%>
<%setTimeAtActivationToTime(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
	<%}%>
	<%for (ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"]){%>
<%setStime(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
	<%}%>
	<%for (ownedElement.filter("State")){%>
<%setTimeAtActivationToTime(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
	<%}%>
	<%for (ownedElement.filter("State")){%>
<%setStime(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
	<%}%>

<%-- lastActive code --%>	
<%if (ownedElement.filter("Pseudostate")[kind == "shallowHistory"] != null){%>
/*set lastActive */
<%for (ownedElement.filter("State")){%>
if (<%getQualifiedDotName(args(0))%>.active) then 
	<%container.ownedElement.filter("Pseudostate")[kind == "shallowHistory"].getQualifiedDotName(args(0))%>.lastActive := (<%container.ownedElement.filter("Pseudostate")[kind == "shallowHistory"].getQualifiedDotName(args(0))%>.HistoryStateT.<%name.replaceSpecChar()%>);
end if;
<%}%>
<%}%>	
<%-- transition code --%>
/*start transition code*/
<%args(2).filter("StateMachine").startSamplingTag%>
<%initialBehaviorCode(args(0),args(1).filter("State"))%>	
<%if (ownedElement.filter("Pseudostate")[kind == "shallowHistory"].length>0){%>
<%shallowHistoryBehaviorCode(args(0))%>	
<%}%>
<%--first State--%>
<%if (owner.filter("StateMachine") || owner.filter("State")[metamodel::isComposite || metamodel::isSubmachineState]){%>
<%--TODO Add suspension
--%>
/*test the composite state is still active*/
if(<%args(0)%>.active <%owner.filter("State").freezeExpression%> <%owner.filter("StateMachine").freezeExpression%>) then 
<%}%>
<%if (ownedElement.filter("State")[outgoing != null].length()>0){%>
<%for (ownedElement.filter("State")[outgoing != null].nFirst()){%>
	 if pre(<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active) then
	<%transitionCode(args(0),args(1).filter("State")).indentTab()%>
<%}%>	
<%for (ownedElement.filter("State")[outgoing != null].nGet(1,ownedElement.filter("State")[outgoing != null].length() )){%>
elseif pre(<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active) then
	<%transitionCode(args(0),args(1).filter("State")).indentTab()%>
<%}%>
end if;
<%}%>	
<%if (owner.filter("StateMachine") ||owner.filter("State")[metamodel::isComposite || metamodel::isSubmachineState]){%>
 end if; 
<%}%>
/*end transition code*/
/*start do Code*/
<%for (ownedElement.filter("State")){%>
<%if (doActivity){%>
<%DoCode(args(0))%>
<%}%>
<%AssertsInStates%>
<%}%>
/*end do Code*/
<%--join code
--%><%for (ownedElement.filter("State")[incoming != null]){%>
<%for (incoming.sortTransitions().source.filter("Pseudostate")[kind == "join"]){%>
<%joinTargetCode(args(0))%>
<%}%>
<%}%>
<%--end join code
--%>
<%args(2).filter("StateMachine").endSamplingTag%>
<%for (ownedElement.filter("State")[metamodel::isComposite]){%>
/*start composite <%name%> */
	<%for (region.sortRegions().filter("Region")){%>
	<%regionBehaviorCode(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar(),args(1).filter("State"),args(2).filter("StateMachine")).indentTab()%>
	<%}%>
/*end composite <%name%> */
<%}%>
<%for (ownedElement.filter("State")[metamodel::isSubmachineState]){%>
/*start submachine composite <%name%> */
<%--	<%for (submachine.region.sortRegions().filter("Region")){%>
	<%regionBehaviorCode(args(0)+"."+owner.filter("StateMachine").submachineState.container.name.replaceSpecChar()+"."+owner.filter("StateMachine").submachineState.name.replaceSpecChar()).indentTab()%>
	<%}%>
--%><%submachine.subMachineRegionCode(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),addSubMachienStatetoList(args(1).filter("State")).filter("State"),args(2).filter("StateMachine")).indentTab()%>
/*end submachine composite <%name%> */
<%}%>

/*** end behaviour code of region "<%name%>" ***/


<%script type="uml.StateMachine" name="subMachineRegionCode"%>
<%--args(2) == start StateMachine
--%><%for (region.sortRegions().filter("Region")){%>
	<%regionBehaviorCode(args(0),args(1).filter("State"),args(2).filter("StateMachine")).indentTab()%>
<%}%>


<%script type="uml.Region" name="shallowHistoryBehaviorCode" post="trim()"%>
<%for (ownedElement.filter("Pseudostate")[kind == "shallowHistory"]){%>
/*start activate lastActive state*/
if /*pre*/(<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active) then	
	<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active := false;
	<%if (outgoing.nFirst().effect){%><%outgoing.nFirst().Effect%><%}%>
	<%shallowHistoryLastActive(args(0),name.replaceSpecChar())%>
end if;
/*end activate lastActive state*/
<%}%>

<%script type="uml.Pseudostate" name="shallowHistoryLastActive" post="trim()"%>
/*shallowHistoryLastActive: <%args(0)%>*/
<%for (owner.filter("Region").ownedElement.filter("State").nGet(0)){%>
if(<%args(0)+"."+container.name.replaceSpecChar()+"."+args(1)%>.lastActive == <%args(0)+"."+container.name.replaceSpecChar()+"."+owner.ownedElement.filter("Pseudostate")[kind == "shallowHistory"].name.replaceSpecChar()%>.<%"HistoryStateT."+name.replaceSpecChar()%>) then
		<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active := true;	
		<%if (entry){%>
		<%Entry(args(0)+"."+container.name.replaceSpecChar()+"."+name)%>
		<%}%>
		<%if (metamodel::isComposite){%>
		<%for (ownedElement.filter("Region").sortRegions()){%>
			<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
		<%}%>
		<%}%>
	<%if (metamodel::isSubmachineState){%>
		<%submachine.submachineTargetStateMachine(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
		<%}%>			
<%}%>
	<%for (owner.filter("Region").ownedElement.filter("State").nGet(1,owner.filter("Region").ownedElement.filter("State").length() )){%>
	elseif(<%args(0)+"."+container.name.replaceSpecChar()+"."+args(1)%>.lastActive == <%args(0)+"."+container.name.replaceSpecChar()+"."+owner.ownedElement.filter("Pseudostate")[kind == "shallowHistory"].name.replaceSpecChar()%>.<%"HistoryStateT."+name.replaceSpecChar()%>) then
		<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active := true;	
		<%if (entry){%>
		<%Entry(args(0)+"."+container.name.replaceSpecChar()+"."+name)%>
		<%}%>
		<%if (metamodel::isComposite){%>
		<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
		<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
		<%}%>
		<%}%>
		<%if (metamodel::isSubmachineState){%>
		<%submachine.submachineTargetStateMachine(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
		<%}%>
	<%}%>
	else
		<%args(0)+"."+container.name.replaceSpecChar()+"."+outgoing.target.name.replaceSpecChar()%>.active := true;	
		<%if (outgoing.nGet(0).suppressEntryActions!="suppressEntryActions"){%>
		<%outgoing.nGet(0).target.Entry(args(0)+"."+container.name.replaceSpecChar()+"."+outgoing.nGet(0).target.name.replaceSpecChar())%>
		<%}%>		
		<%if (outgoing.nGet(0).target.filter("State")[metamodel::isComposite]){%>
<%for (outgoing.nGet(0).target.ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
<%}%>
<%}%>
<%for (outgoing.nGet(0).target.filter("State")[metamodel::isSubmachineState]){%>
<%submachine.submachineTargetStateMachine(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%> 
<%}%>
	end if;
	
<%script type="uml.Vertex" name="compositeTarget"%>	
<%if (outgoing.nGet(0).target.filter("State")[metamodel::isComposite]){%>
<%for (outgoing.nGet(0).target.ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
<%}%>
<%}%>
<%for (outgoing.nGet(0).target.filter("State")[metamodel::isSubmachineState]){%>
<%submachine.submachineTargetStateMachine(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%> 
<%}%>
	
<%script type="uml.Region" name="initialBehaviorCode" post="trim()"%>
<%for (ownedElement.filter("Pseudostate")[kind == "initial"]){%>
	<%for (outgoing.filter("Transition")){%>
		<%if (target.eClass().name == "State" || eClass().name == "FinalState"){%>
if (<%args(0)+"."+container.name.replaceSpecChar()+"."+source.name.replaceSpecChar()%>.active) then	
	<%args(0)+"."+container.name.replaceSpecChar()+"."+source.name.replaceSpecChar()%>.active := false;
	<%if (effect){%><%Effect%><%}%>	
	<%args(0)+"."+container.name.replaceSpecChar()+"."+target.name.replaceSpecChar()%>.active := true; 
	<%if (suppressEntryActions!="suppressEntryActions"){%>	
	<%target.Entry(args(0)+"."+container.name.replaceSpecChar()+"."+target.name.replaceSpecChar())%>
	<%}%>
<%if (target.filter("State")[metamodel::isComposite]){%>
<%for (target.ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
<%}%>
<%}%>	
<%if (target.filter("State")[metamodel::isSubmachineState]){%>
<%target.filter("State").submachine.submachineTargetStateMachine(args(0)+"."+target.container.name.replaceSpecChar()+"."+target.name.replaceSpecChar())%>
<%}%>	
end if;
<%}else{%>
if(<%args(0)+"."+container.name.replaceSpecChar()+"."+source.name.replaceSpecChar()%>.active) then	
 	if <%pseudostateTargetTransitionCode(args(0),args(1))%>
	end if;
end if;
		<%}%>
	<%}%>
<%}%>




<%script type="uml.Pseudostate" name="setInitialHistoryActive"%>
<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active := true; 

<%script type="uml.StateMachine" name="submachineTargetStateMachine"%>
<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0))%> 
<%}%>

<%script type="uml.State" name="transitionCode" post="trim()"%>
<%--args(1) == submachineStateList--%>
<%for (outgoing.filter("Transition").filterJoinTargetsOut().filter("Transition").sortTransitions().filter("Transition").nFirst()){%>
    if <%if (source.container.qualifiedName == target.container.qualifiedName){%>
    <%if (source.eClass().name == "State" && (target.eClass().name == "State" || target.eClass().name == "FinalState")){%>
    <%simpleTransitionCode(args(0))%>
    <%}else{%>
    <%pseudostateTargetTransitionCode(args(0),args(1).filter("State"))%>
    <%}%>
     <%}else{%>
<%--     /*start interlevel transition <%args(0)%>*/ 
--%>     <%if (source.eClass().name == "State" && (target.eClass().name == "State" || target.eClass().name == "FinalState")){%>
		<%interLevelTransition(args(0))%>		
		 <%}else{%>
<%pseudostateTargetTransitionCode(args(0),args(1).filter("State"))%>
		<%}%>
      /*end interlevel transition*/
      <%}%>
<%}%>
<%for (outgoing.filter("Transition").filterJoinTargetsOut().filter("Transition").sortTransitions().filter("Transition").nGet(1, outgoing.filter("Transition").filterJoinTargetsOut().filter("Transition").length())){%>
	elseif	<%if (source.container.qualifiedName == target.container.qualifiedName){%>
			    <%if (source.eClass().name == "State" && (target.eClass().name == "State" || target.eClass().name == "FinalState")){%>
			    <%simpleTransitionCode(args(0))%>
			    <%}else{%>
<%pseudostateTargetTransitionCode(args(0),args(1).filter("State"))%>
			    <%}%>
		<%}else{%>
		/*start interleveltransition*/
				<%if (source.eClass().name == "State" && (target.eClass().name == "State" || target.eClass().name == "FinalState")){%>
		<%interLevelTransition(args(0))%>		
		 <%}else{%>
<%pseudostateTargetTransitionCode(args(0),args(1).filter("State"))%>
		<%}%>
		/*end interleveltransition*/
		<%}%>	
<%}%>
<%if (outgoing.filter("Transition").target.filter("State").nSize()>0){%>
end if;
<%}else{%>
<%if (outgoing.filter("Transition").target.filter("Pseudostate")[! (kind == "join") ].nSize() > 0){%>
end if; 
<%}%>
<%}%>

<%script type="uml.Transition" name="pseudostateTargetTransitionCode" post="trim()"%>
<%--if guard is true deactivate source/ composite state and evaluate effect. dynamic choice.
--%>
<%if (target.filter("Pseudostate")[kind == "terminate"]){%>
<%guardCode(source.getQualifiedDotName(args(0)))%> then
<%args(0).substring(0,args(0).indexOf("."))%>.active := false;
end if;
<%}%>
<%if (target.filter("ConnectionPointReference").entry.filter("Pseudostate")[kind == "entryPoint"].nFirst()){%>
<%guardCode(source.getQualifiedDotName(args(0)))%> then
<%deactivateActiveSubstate(args(0))%>
<%source.container.lowestCommonAncestorExit(args(0),lowestCommonAncestorQualifiedName())%>
<%if (suppressEntryActions!="suppressEntryActions"){%>	
<%target.filter("ConnectionPointReference").entry.filter("Pseudostate")[kind == "entryPoint"].nFirst().entryTargetCodeBehaviour(source.container.getContainerName(args(0),target.filter("ConnectionPointReference").state.container.qualifiedName ),target.filter("ConnectionPointReference").state.filter("State"),Effect,"")%>
<%}else{%>
<%target.filter("ConnectionPointReference").entry.filter("Pseudostate")[kind == "entryPoint"].nFirst().entryTargetCodeBehaviour(source.container.getContainerName(args(0),target.filter("ConnectionPointReference").state.container.qualifiedName ),target.filter("ConnectionPointReference").state.filter("State"),Effect,"suppressEntryActions")%>
<%}%>
end if;
/*end entryPoint behaviour*/
<%}%>
<%--EXITPOINT--%>
<%if (target.filter("Pseudostate")[kind == "exitPoint"] && target.filter("Pseudostate")[kind == "exitPoint"].incoming.nSize() == 1){%>
<%guardCode(source.getQualifiedDotName(args(0)))%> then
/*ExitPoint*/ 
<%deactivateActiveSubstate(args(0))%>
<%source.container.lowestCommonAncestorExit(args(0),lowestCommonAncestorQualifiedName())%>
<%target.filter("Pseudostate").getConnectionPointReference(args(1).filter("State").nGet(0)).filter("ConnectionPointReference").exitTargetCode(args(0),source,args(1).filter("State"),Effect)%>
<%-- TODO: this "end if;" seemts to be too much ... test it --%>
<%--end if;--%>
<%}%>

<%if (target.filter("Pseudostate")[kind == "choice"]){%>
<%guardCode(args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar())%> then
<%deactivateActiveSubstate(args(0))%>
<%if (effect){%>
	<%Effect%>
<%}%>
<%target.filter("Pseudostate")[kind == "choice"].choiceTargetCode(args(0),source)%>
<%}%>
<%if (target.filter("Pseudostate")[kind == "fork"]){%>
<%--A fork segment must not have guards or triggers. UML Spec. todo? replace guard code with true
--%><%guardCode(source.getQualifiedDotName(args(0)))%> then
<%deactivateActiveSubstate(args(0))%>
<%if (effect){%>
	<%Effect%>
<%}%>
<%target.filter("Pseudostate").forkTargetCode(args(0),source)%>
<%}%>
<%if (target.filter("Pseudostate")[kind == "junction"]){%>
<%guardCode(source.getQualifiedDotName(args(0)))%> then
<%if (suppressExitActions=="suppressExitActions"){%>
<%target.filter("Pseudostate").junctionTargetCode(args(0),source,Effect,"suppressExitActions")%>
<%}else{%>
<%target.filter("Pseudostate").junctionTargetCode(args(0),source,Effect,"")%>
<%}%>
<%}%>

<%script type="uml.Transition" name="deactivateActiveSubstate" post="trim()"%>
<%if (suppressExitActions == "suppressExitActions"){%>
<%if (source.filter("State")[metamodel::isComposite]){%>
/*start composite highlevel transition deactivate active substate*/
<%source.filter("State").highlevelTransition(args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar(),"suppressExitActions")%>
/*end composite highlevel transition deactivate active substate*/
<%}%>
<%if (source.filter("State")[metamodel::isSubmachineState]){%>
/*start submachine highlevel transition deactivate active substate*/
<%source.filter("State").submachine.filter("StateMachine").highlevelTransition(args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar(),"suppressExitActions")%>
/*end submachine highlevel transition deactivate active substate*/
<%}%>
<%args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar()%>.active := false; 
<%}%>

<%if (source.filter("State")[metamodel::isComposite]){%>
/*start composite highlevel transition deactivate active substate*/
<%source.filter("State").highlevelTransition(args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar(),"")%>
/*end composite highlevel transition deactivate active substate*/
<%}%>
<%if (source.filter("State")[metamodel::isSubmachineState]){%>
/*start submachine highlevel transition deactivate active substate*/
<%source.filter("State").submachine.filter("StateMachine").highlevelTransition(args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar(),"")%>
/*end submachine highlevel transition deactivate active substate*/
<%}%>
<%if (source.filter("State").exit){%>
<%source.filter("State").Exit(args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar())%>
<%}%>
<%args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar()%>.active := false; 

<%script type="uml.Pseudostate" name="choiceTargetCode" post="trim()"%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].sortTransitions().nGet(0)){%>
if <%guardCode(args(1).filter("State").getQualifiedDotName(args(0)))%> then
<%determineTransitionTarget(args(0),args(1))%>
<%}%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].sortTransitions().nGet(1, outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].length())){%>
elseif <%guardCode(args(1).filter("State").getQualifiedDotName(args(0)))%> then
<%determineTransitionTarget(args(0),args(1))%>
<%}%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() == "else"]){%>
else
<%determineTransitionTarget(args(0),args(1))%>
<%}%>
end if;


<%script type="uml.Pseudostate" name="forkTargetCode" post="trim()"%>
/*start fork code*/
<%for (outgoing[target.filter("State")].sortTransitions().filter("Transition").nGet(0)){%>
<%if (effect){%>
	<%Effect%>
<%}%>
<%target.filter("State").lowestCommonAncestorEntryForkStart(args(0),lowestCommonAncestorQualifiedNameforPseudostates(args(1).filter("State")),source)%>
<%}%>	
<%for (outgoing[target.filter("State")].sortTransitions().filter("Transition").nGet(1,outgoing[target.filter("State")].length())){%>	
<%if (effect){%>
	<%Effect%>
<%}%>
		<%target.filter("State").getFullQualifiedDotName(args(0))%>.active := true;
	<%if (suppressEntryActions!="suppressEntryActions"){%>
	<%if (target.filter("State").entry){%>
	<%target.filter("State").Entry(target.filter("State").getFullQualifiedDotName(args(0)))%>
	<%}%>
	<%}%>
	<%if (target.filter("State")[metamodel::isComposite]){%>
<%for (target.ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(owner.filter("State").getFullQualifiedDotName(args(0)))%>
<%}%>
<%}%>
<%if (target.filter("State")[metamodel::isSubmachineState]){%>
<%target.filter("State").submachine.submachineTargetStateMachine(target.filter("State").getFullQualifiedDotName(args(0)))%>
<%}%>
<%}%>
<%for (outgoing[target.filter("State")].sortTransitions().filter("Transition").nGet(0)){%>
<%--activate missing region
--%>
<%for (source.filter("Pseudostate").getMissingForkRegion(target.filter("State").container.owner.filter("State"))){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().getFullQualifiedDotName(args(0))%>.active := true;
<%}%>
<%--activate parallel states--%>
<%for (target.filter("State").getInterlevelEntryList(args(1)).nGet(0,target.filter("State").getInterlevelEntryList(args(1)).nSize()-1).filter("State").getMissingRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().getFullQualifiedDotName(args(0))%>.active := true;
<%}%>
<%}%>

<%script type="uml.Pseudostate" name="junctionTargetCode" post="trim()"%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].sortTransitions().nGet(0).filter("Transition")){%>
if <%guardCode(args(1).filter("State").getQualifiedDotName(args(0)))%> then
<%if (target.filter("Pseudostate")[kind == "junction"]){%>
<%target.filter("Pseudostate").junctionTargetCode(args(0),args(1).filter("State"),args(2)+Effect,args(3))%>
<%}else{%>
<%if (args(1).filter("State")[metamodel::isComposite]){%>
/*start highlevel transition deactivate active substate*/
<%args(1).filter("State").highlevelTransition(args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar(),args(3))%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (args(1).filter("State")[metamodel::isSubmachineState]){%>
/*start highlevel transition deactivate active substate*/
<%args(1).filter("State").submachine.filter("StateMachine").highlevelTransition(args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar(),args(3))%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (args(3)!="suppressExitActions"){%>
<%if (args(1).filter("State").exit){%>
<%args(1).filter("State").Exit(args(1).getQualifiedDotName(args(0)))%>
<%}%>
<%}%>
<%args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar()%>.active := false;
<%--args(2) = Effects
--%><%args(2)%>
<%determineTransitionTarget(args(0),args(1).filter("State"))%>
<%--deactivate source state
--%><%}%>
<%}%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].sortTransitions().nGet(1, outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].length()).filter("Transition")){%>
elseif <%guardCode(args(1).filter("State").getQualifiedDotName(args(0)))%> then
<%if (target.filter("Pseudostate")[kind == "junction"]){%>
<%target.filter("Pseudostate").junctionTargetCode(args(0),args(1),args(2)+Effect,args(3))%>
<%}else{%>
<%if (args(1).filter("State")[metamodel::isComposite]){%>
/*start highlevel transition deactivate active substate*/
<%args(1).filter("State").highlevelTransition(args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar(),args(3))%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (args(1).filter("State")[metamodel::isSubmachineState]){%>
/*start highlevel transition deactivate active substate*/
<%args(1).filter("State").submachine.filter("StateMachine").highlevelTransition(args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar(),args(3))%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (args(3)!="suppressExitActions"){%>
<%if (args(1).filter("State").exit){%>
<%args(1).filter("State").Exit(args(1).getQualifiedDotName(args(0)))%>
<%}%>
<%}%>
<%args(1).filter("State").getQualifiedDotName(args(0))%>.active := false;
<%args(2)%>
<%determineTransitionTarget(args(0),args(1).filter("State"))%>
<%--deactivate source state
--%><%}%>
<%}%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() == "else"]){%>
else
<%if (target.filter("Pseudostate")[kind == "junction"]){%>
<%target.filter("Pseudostate").junctionTargetCode(args(0),args(1),args(2)+Effect)%>
<%}else{%>
<%if (args(1).filter("State")[metamodel::isComposite]){%>
/*start highlevel transition deactivate active substate*/
<%args(1).filter("State").highlevelTransition(args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar(),args(3))%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (args(1).filter("State")[metamodel::isSubmachineState]){%>
/*start highlevel transition deactivate active substate*/
<%args(1).filter("State").submachine.filter("StateMachine").highlevelTransition(args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar(),args(3))%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (args(3)!="suppressExitActions"){%>
<%if (args(1).filter("State").exit){%>
<%args(1).filter("State").Exit(args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar())%>
<%}%>
<%}%>
<%args(0)+"."+args(1).filter("State").container.name.replaceSpecChar()+"."+args(1).filter("State").name.replaceSpecChar()%>.active := false;
<%args(2)%>
<%determineTransitionTarget(args(0),args(1).filter("State"))%>
<%--deactivate source state
--%><%}%>
<%}%>
end if;


<%script type="uml.Pseudostate" name="entryTargetCodeBehaviour" post="trim()"%>
//test5<%args(3)%>
<%if (args(3)!="suppressEntryActions"){%>
<%if (args(1).nFirst().filter("State").entry){%>
<%args(1).nFirst().filter("State").Entry(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar())%>
<%}%>
<%}%>
<%args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar()%>.active := true;
<%if (!outgoing.isForkEntry()){%>
<%for (outgoing[guardCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar()).trim() != "else"].sortTransitions().filter("Transition").nGet(0).filter("Transition")){%>
if <%guardCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar())%> then
<%entryTargetOutgoingCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar(),args(2)+Effect,args(1))%>
<%}%>
<%for (outgoing[guardCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar()).trim() != "else"].sortTransitions().filter("Transition").nGet(1, outgoing[guardCode(args(1).nFirst().filter("State").getQualifiedDotName(args(0))).trim() != "else"].length())){%>
elseif <%guardCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar())%> then
<%entryTargetOutgoingCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar(),args(2)+Effect,args(1))%>
<%}%>
<%for (outgoing[guardCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar()).trim() == "else"]){%>
else
<%guardCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar())%> then
<%entryTargetOutgoingCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar(),args(2)+Effect,args(1))%>
<%}%>
end if;
<%}else{%>
//fork
<%entryforkTargetCode(args(0)+"."+args(1).nFirst().filter("State").container.name.replaceSpecChar()+"."+args(1).nFirst().filter("State").name.replaceSpecChar(),args(2),args(1))%>
<%}%>

<%script type="uml.Pseudostate" name="entryforkTargetCode"%>
//Effect
<%args(1)%>
<%for (outgoing.nFirst()){%>
<%target.filter("State").lowestCommonAncestorEntryFork(args(0),source.filter("Pseudostate")[kind == "entryPoint"].stateMachine.qualifiedName)%>
<%}%>
//TODO test if target is composite 
<%--<%for (container.owner.filter("StateMachine").region.getOtherRegions(container).filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().getFullQualifiedDotName(args(0))%>.active := true;
<%}%>
--%><%for (getMissingForkRegion(outgoing.nFirst().target.filter("State").container.owner.filter("State"))){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().getFullQualifiedDotName(args(0))%>.active := true; //test3
<%}%>
//TODO
<%args(2).nGet(0).entryTargetOutgoingCodeParallelActivate(args(0).substring(0,args(0).lastIndexOf("."+args(2).nGet(0).container.name.replaceSpecChar()+"."+args(2).nGet(0).name.replaceSpecChar())),args(1),args(2).nGet(0,args(2).nSize()-1))%>


<%script type="uml.ConnectionPointReference" name="exitTargetCode" post="trim()"%>
<%exitTargetCodeBehaviour(args(0).substring(0,args(0).lastIndexOf("."+state.container.name.replaceSpecChar()+"."+state.name.replaceSpecChar())),args(1),args(2),args(3))%>

<%script type="uml.ConnectionPointReference" name="exitTargetCodeBehaviour" post="trim()"%>
<%if (state.exit){%>
<%state.Exit(args(0)+"."+state.container.name.replaceSpecChar()+"."+state.name.replaceSpecChar())%>
<%}%>
<%args(0)+"."+state.container.name.replaceSpecChar()+"."+state.name.replaceSpecChar()%>.active := false;
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].sortTransitions().filter("Transition").nGet(0).filter("Transition")){%>
if <%guardCode(args(1).filter("State").getQualifiedDotName(args(0)))%> then
<%exitTargetOutgoingCode(args(0),args(1),args(2),args(3)+Effect)%>
<%}%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].sortTransitions().filter("Transition").nGet(1, outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() != "else"].length())){%>
elseif <%guardCode(args(1).filter("State").getQualifiedDotName(args(0)))%> then
<%exitTargetOutgoingCode(args(0),args(1),args(2),args(3)+Effect)%>
<%}%>
<%for (outgoing[guardCode(args(1).filter("State").getQualifiedDotName(args(0))).trim() == "else"]){%>
else
<%exitTargetOutgoingCode(args(0),args(1),args(2),args(3)+Effect)%>
<%}%>
end if;

<%script type="uml.Transition" name="entryTargetOutgoingCode" post="trim()"%>
<%--args(1) == effect args(2) == statelist--%>
//test6 <%args(0)%>
<%if (target.filter("State")){%>
//Effect
<%args(1)%>
<%target.filter("State").lowestCommonAncestorEntry(args(0),source.filter("Pseudostate")[kind == "entryPoint"].stateMachine.qualifiedName)%>
<%for (container.owner.filter("StateMachine").region.getOtherRegions(target.container.getHighestRegionforEntry()).filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().getFullQualifiedDotName(args(0))%>.active := true;
<%}%>
<%args(2).nGet(0).entryTargetOutgoingCodeParallelActivate(args(0).substring(0,args(0).lastIndexOf("."+args(2).nGet(0).container.name.replaceSpecChar()+"."+args(2).nGet(0).name.replaceSpecChar())),args(1),args(2).nGet(0,args(2).nSize()-1))%>
<%}else{%>
<%if (target.filter("ConnectionPointReference")){%>
<%if (suppressEntryActions!="suppressEntryActions"){%>	
<%target.filter("ConnectionPointReference").entry.filter("Pseudostate")[kind == "entryPoint"].nFirst().entryTargetCodeBehaviour(args(0),target.filter("ConnectionPointReference").state.addSubMachienStatetoList(args(2)),args(1)+Effect,"")%>
<%}else{%>
<%target.filter("ConnectionPointReference").entry.filter("Pseudostate")[kind == "entryPoint"].nFirst().entryTargetCodeBehaviour(args(0),target.filter("ConnectionPointReference").state.addSubMachienStatetoList(args(2)),args(1)+Effect,"suppressEntryActions")%>
<%}%>
<%}%>
<%}%>

<%script type="uml.State" name="entryTargetOutgoingCodeParallelActivate" post="trim()"%>
<%for (container.owner.filter("StateMachine").region.getOtherRegions(container).filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().getFullQualifiedDotName(args(0))%>.active := true;
<%}%>
<%args(2).nGet(1).entryTargetOutgoingCodeParallelActivate(args(0).substring(0,args(0).lastIndexOf("."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()) ),args(1),args(2).nGet(1,args(2).nSize() ))%>

<%script type="uml.Transition" name="exitTargetOutgoingCode" post="trim()"%>
<%if (target.filter("State")){%>
//Effect
<%args(3)%>
<%target.filter("State").lowestCommonAncestorEntry(args(0).substring(0,args(0).lastIndexOf("."+args(2).filter("State").nGet(0).container.name.replaceSpecChar()+"."+args(2).filter("State").nGet(0).name.replaceSpecChar())),args(2).filter("State").nGet(0).container.qualifiedName)%>
<%}else{%>
<%if (target.filter("Pseudostate")[kind == "exitPoint"]){%>
<%for (target.filter("Pseudostate").owner.filter("StateMachine").region.filter("Region").getOtherRegions(source.filter("ConnectionPointReference").state.container).filter("Region").sortRegions().filter("Region")){%>
<%lowestCommonAncestorExitOtherRegion(args(0))%>
<%}%>
<%target.filter("Pseudostate").exitPointTarget(args(0).substring(0, args(0).lastIndexOf("."+args(2).filter("State").nGet(0).container.name.replaceSpecChar()+"."+args(2).filter("State").nGet(0).name.replaceSpecChar()) ),args(1),args(2).filter("State").nGet(1,args(2).filter("State").nSize()),args(3))%>
<%}%>
<%}%>

<%script type="uml.Pseudostate" name="exitPointTarget"%>
<%getConnectionPointReference(args(2).filter("State").nGet(0)).filter("ConnectionPointReference").exitTargetCode(args(0),args(1),args(2),args(3))%>


<%script type="uml.Pseudostate" name="joinTargetCode"%>
/*start join Code*/
if (<%for (incoming.sortTransitions().filter("Transition").sep(" and ")){%>
pre(<%source.filter("State").getFullQualifiedDotName(args(0))%>.active)
  <%}%>)	 then
<%for (incoming.sortTransitions().filter("Transition")){%>
<%if (source.filter("State").metamodel::isComposite){%>
/*start highlevel transition deactivate active substate <%source.filter("State").name%>*/
<%if (suppressExitActions=="suppressExitActions"){%>
<%source.filter("State").highlevelTransition(getFullQualifiedDotName(args(0)).replaceSpecChar(),"suppressExitActions")%>
<%}else{%>
<%source.filter("State").highlevelTransition(getFullQualifiedDotName(args(0)).replaceSpecChar(),"")%>
<%}%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (source.filter("State").metamodel::isSubmachineState){%>
/*start highlevel transition deactivate active substate */
<%if (suppressExitActions=="suppressExitActions"){%>
<%source.filter("State").submachine.filter("StateMachine").highlevelTransition(getFullQualifiedDotName(args(0)).replaceSpecChar(),"suppressExitActions")%>
<%}else{%>
<%source.filter("State").submachine.filter("StateMachine").highlevelTransition(getFullQualifiedDotName(args(0)).replaceSpecChar(),"")%>
<%}%>
/*end highlevel transition deactivate active substate*/
<%}%>
<%if (suppressExitActions!="suppressExitActions"){%>
<%if (source.filter("State").exit){%>
	<%source.filter("State").Exit(source.filter("State").getFullQualifiedDotName(args(0)))%>	
<%}%>
<%}%>
<%source.filter("State").getFullQualifiedDotName(args(0))%>.active := false; 
<%}%>
<%--exit orthogonal owners
--%>
<%incoming.sortTransitions().filter("Transition").nLast().source.container.lowestCommonAncestorExitJoin(incoming.sortTransitions().filter("Transition").nLast().source.container.owner.getFullQualifiedDotName(args(0)),incoming.sortTransitions().filter("Transition").nLast().lowestCommonAncestorQualifiedName(),self().filter("Pseudostate"))%>
<%for (incoming.sortTransitions().filter("Transition")){%>
<%if (effect){%>
<%Effect%>
<%}%>
<%}%>
<%if (outgoing.effect){%>
<%outgoing.nGet(0).Effect%>
<%}%>
  <%outgoing.nGet(0).target.filter("State").getFullQualifiedDotName(args(0))%>.active := true;
 	<%if (outgoing.nGet(0).suppressEntryActions!="suppressEntryActions"){%>
  <%if (outgoing.nGet(0).target.filter("State").entry){%>
	<%outgoing.nGet(0).target.filter("State").Entry(outgoing.target.filter("State").getFullQualifiedDotName(args(0)))%>
	<%}%>
	<%}%>
  <%if (outgoing.nGet(0).target.filter("State")[metamodel::isComposite]){%>
<%for (outgoing.nGet(0).target.filter("State").nGet(0).ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
<%}%>
<%}%>
  <%if (outgoing.nGet(0).target.filter("State")[metamodel::isSubmachineState]){%>
<%outgoing.nGet(0).target.filter("State").nGet(0).submachine.submachineTargetStateMachine(args(0)+"."+outgoing.nGet(0).target.filter("State").container.name.replaceSpecChar()+"."+outgoing.nGet(0).target.filter("State").name.replaceSpecChar())%>
<%}%>
end if;
/*end join Code*/



<%script type="uml.Transition" name="determineTransitionTarget" post="trim()"%>
<%if (target.filter("Pseudostate")[kind == "choice"]){%>
<%if (effect){%>
	<%Effect%>
<%}%>
<%target.filter("Pseudostate")[kind == "choice"].choiceTargetCode(args(0),args(1))%>
<%}%>
<%if (target.filter("Pseudostate")[kind == "fork"]){%>
<%if (effect){%>
	<%Effect%>
<%}%>
<%target.filter("Pseudostate").forkTargetCode(args(0),args(1))%>
<%}%>
<%if (target.filter("Pseudostate")[kind == "junction"]){%>
<%target.filter("Pseudostate").junctionTargetCode(args(0),source)%>
<%}%>
<%if (target.filter("State")){%>
<%if (effect){%>
	<%Effect%>
<%}%>
<%target.filter("State").targetIsState(args(0),args(1),self())%>
<%}%>
<%if (target.filter("Pseudostate")[kind == "terminate"]){%>
<%args(0).substring(0,args(0).indexOf("."))%>.active := false;
<%}%>


<%script type="uml.State" name="targetIsState" post="trim()"%>
<%--args(1)== source, args(2)== transition
--%>
<%if (args(1).container.qualifiedName == container.qualifiedName){%>
<%--  kein interlevel
--%> 

<%getQualifiedDotName(args(0))%>.active := true;
	<%if (entry){%>
	<%Entry(getQualifiedDotName(args(0)))%>
	<%}%>
 <%}else{%>
<%-- interlevel
--%>  <%args(1).filter("State").container.lowestCommonAncestorExit(args(0),args(2).lowestCommonAncestorQualifiedNameforPseudostates(args(1).filter("State")))%>
<%lowestCommonAncestorEntryStart(args(0),args(2).lowestCommonAncestorQualifiedNameforPseudostates(args(1).filter("State")),args(1).filter("State"))%>
 <%}%>


<%script type="uml.Transition" name="interLevelTransition" post="trim()"%>
<%guardCode(args(0)+"."+source.container.name.replaceSpecChar()+"."+source.name.replaceSpecChar())%> then
/*start interlevel transition <%args(0)%>*/ 
<%deactivateActiveSubstate(args(0))%>
<%if (effect){%>
<%Effect%>
<%}%>
<%source.container.lowestCommonAncestorExit(args(0),lowestCommonAncestorQualifiedName())%>
<%target.filter("State").lowestCommonAncestorEntryStart(args(0),lowestCommonAncestorQualifiedName(),source)%>

<%script type="uml.State" name="highlevelTransition" post="trim()"%>
<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%for (ownedElement.filter("State")){%>
<%if (metamodel::isComposite){%>
<%highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),args(1))%>
<%}%>
<%if (metamodel::isSubmachineState){%>
<%submachine.filter("StateMachine").highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),args(1))%>
<%}%>
if (<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active) then
<%if (args(1)!="suppressExitActions"){%>
<%if (exit){%>
	<%Exit(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
<%}%>
<%}%>
	<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active:=false;
end if;
<%}%>
<%}%>

<%script type="uml.StateMachine" name="highlevelTransition" post="trim()"%>
//HIGHLEVEL
<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%for (ownedElement.filter("State")){%>
<%if (metamodel::isComposite){%>
<%highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),args(1))%>
<%}%>
<%if (metamodel::isSubmachineState){%>
<%submachine.filter("StateMachine").highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),args(1))%>
<%}%>
if (<%getQualifiedDotName(args(0))%>.active) then
<%if (exit){%>
	<%Exit(getQualifiedDotName(args(0)))%>
<%}%>
	<%getQualifiedDotName(args(0))%>.active:=false;
end if;
<%}%>
<%}%>

<%script type="uml.Region" name="lowestCommonAncestorExit" post="trim()"%>
/*lowestCommonAncestorExit*/
<%--not tested todo
--%>
<%for (owner.filter("State").region.filter("Region").getOtherRegions(self()).filter("Region").sortRegions().filter("Region")){%>
<%lowestCommonAncestorExitOtherRegion(args(0))%>
<%}%>
<%for (owner.filter("StateMachine").region.filter("Region").getOtherRegions(self()).filter("Region").sortRegions().filter("Region")){%>
<%lowestCommonAncestorExitOtherRegion(args(0))%>
<%}%>
<%if ( args(1) != qualifiedName){%>
<%if (owner.filter("State").exit){%>
<%owner.filter("Vertex").Exit(args(0))%>
<%}%>
<%args(0)%>.active := false;
<%owner.filter("State").container.lowestCommonAncestorExit(args(0).substring(0,args(0).lastIndexOf("."+owner.filter("State").container.filter("Region").name+"."+owner.filter("State").name)),args(1))%>
<%}else{%>
<%}%>

<%script type="uml.Region" name="lowestCommonAncestorExitOtherRegion" post="trim()"%>
<%for (ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"]){%>
if(<%getQualifiedDotName(args(0))%>.active) then
<%getQualifiedDotName(args(0))%>.active := false; /*tes*/
end if;
<%}%>
<%for (ownedElement.filter("State")){%>
if(<%getQualifiedDotName(args(0))%>.active) then
<%if (metamodel::isComposite){%>
<%highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),"").indentTab()%>
<%}%>
<%if (metamodel::isSubmachineState){%>
<%submachine.filter("StateMachine").highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),"").indentTab()%>
<%}%>
	<%if (exit){%>
	<%Exit(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
	<%}%>
	<%args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar()%>.active := false;
end if;
<%}%>

<%script type="uml.Region" name="lowestCommonAncestorExitJoin" post="trim()"%>
<%--not tested todo
--%>
<%if ( args(1) != qualifiedName){%>
<%for (owner.filter("State").region.filter("Region").getOtherJoinRegions(args(2)).filter("Region").sortRegions().filter("Region")){%>
<%for (ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"]){%>
if(<%args(0)+"."+container.name.replaceSpecChar()+"."+.name.replaceSpecChar()%>.active) then
<%args(0)+"."+container.name.replaceSpecChar()+"."+.name.replaceSpecChar()%>.active := false; /*tes*/
end if;
<%}%>
<%for (ownedElement.filter("State")){%>
if(<%args(0)+"."+container.name.replaceSpecChar()+"."+.name.replaceSpecChar()%>.active) then
<%if (metamodel::isComposite){%>
<%highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),"").indentTab()%>
<%}%>
<%if (metamodel::isSubmachineState){%>
<%submachine.filter("StateMachine").highlevelTransition(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar(),"").indentTab()%>
<%}%>
	<%if (exit){%>
	<%Exit(args(0)+"."+container.name.replaceSpecChar()+"."+.name.replaceSpecChar())%>
	<%}%>
	<%args(0)+"."+container.name.replaceSpecChar()+"."+.name.replaceSpecChar()%>.active := false;
end if;
<%}%>
<%}%>
<%if (owner.filter("State").exit){%>
<%owner.filter("Vertex").Exit(args(0))%>
<%}%>
<%args(0)%>.active := false;
<%owner.filter("State").container.lowestCommonAncestorExit(args(0).substring(0,args(0).lastIndexOf("."+owner.filter("State").container.filter("Region").name+"."+owner.filter("State").name)),args(1))%>
<%}else{%>
<%}%>

<%script type="uml.State" name="lowestCommonAncestorEntryStart" post="trim()"%>
<%lowestCommonAncestorEntry(args(2).filter("State").container.getContainerName(args(0),args(1)),args(1))%>

<%script type="uml.State" name="lowestCommonAncestorEntryForkStart" post="trim()"%>
<%lowestCommonAncestorEntryFork(args(2).filter("Vertex").container.getContainerName(args(0),args(1)),args(1))%>


<%script type="uml.State" name="lowestCommonAncestorEntry" post="trim()"%>
<%for (getInterlevelEntryList(args(1)).filter("State")){%>
<%getFullQualifiedDotName(args(0))%>.active := true; 
<%if (entry){%>
<%Entry(getFullQualifiedDotName(args(0)))%>
<%}%>
<%}%>
<%--activate composite--%>
<%if (metamodel::isComposite){%>
<%for (ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
<%}%>
<%}%>
<%if (metamodel::isSubmachineState){%>
		<%submachine.submachineTargetStateMachine(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
<%}%>	
<%for (getInterlevelEntryList(args(1)).filter("State").getMissingRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().getFullQualifiedDotName(args(0))%>.active := true;
<%}%>

<%script type="uml.State" name="lowestCommonAncestorEntryFork" post="trim()"%>
<%for (getInterlevelEntryList(args(1)).filter("State")){%>
<%getFullQualifiedDotName(args(0))%>.active := true;
<%if (entry){%>
<%Entry(getFullQualifiedDotName(args(0)))%>
<%}%>
<%}%>

<%script type="uml.Transition" name="simpleTransitionCode" post="trim()"%>
	<%guardCode(args(0)+"."+container.name.replaceSpecChar()+"."+source.name.replaceSpecChar())%> then
<%deactivateActiveSubstate(args(0))%>
<%if (effect){%>
	<%Effect%>
<%}%>
	<%args(0)+"."+container.name.replaceSpecChar()+"."+target.name.replaceSpecChar()%>.active := true; 
	<%if (source.qualifiedName == target.qualifiedName){%>
	<%args(0)+"."+container.name.replaceSpecChar()+"."+target.name.replaceSpecChar()%>.selfTransitionActivated := true;
	<%}%>
	<%if (suppressEntryActions!="suppressEntryActions"){%>	
	<%if (target.filter("State").entry){%>
	<%target.Entry(args(0)+"."+container.name.replaceSpecChar()+"."+target.name.replaceSpecChar())%>
	<%}%>
	<%}%>
<%--test if target is composite. when it is composite activate inital or history
--%>
<%for (target.filter("State")[metamodel::isComposite].ownedElement.filter("Region").sortRegions().filter("Region")){%>
<%ownedElement.filter("Pseudostate")[kind == "initial" || kind == "shallowHistory"].nFirst().setInitialHistoryActive(args(0)+"."+owner.filter("State").container.name.replaceSpecChar()+"."+owner.filter("State").name.replaceSpecChar())%>
<%}%>
<%for (target.filter("State")[metamodel::isSubmachineState]){%>
<%submachine.submachineTargetStateMachine(args(0)+"."+container.name.replaceSpecChar()+"."+name.replaceSpecChar())%>
<%}%>
