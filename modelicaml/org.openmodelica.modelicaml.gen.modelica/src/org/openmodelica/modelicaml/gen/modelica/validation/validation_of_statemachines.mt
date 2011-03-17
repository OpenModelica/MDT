<%
metamodel http://www.eclipse.org/uml2/3.0.0/UML

import org.openmodelica.modelicaml.common.services.StringUtls
import org.openmodelica.modelicaml.gen.modelica.uml2modelica.services.UmlServices
import org.openmodelica.modelicaml.gen.modelica.services.validation.StateMachineValidation
import org.openmodelica.modelicaml.gen.modelica.services.validation.NamesCheck

import org.openmodelica.modelicaml.gen.modelica.validation.validation_helper
%>


<%script type="uml.StateMachine" name="StateMachine_validation" post="trim()"%>
<%statemachine_reference_loop_validation%>
<%statemachine_validation_rules.trim()%>
<%region.region_validation_rules.trim()%>
<%region.ownedElement.filter("State").region.region_validation_rules.trim()%>


<%script type="uml.StateMachine" name="statemachine_validation_rules" post="trim()"%>
<%for (ownedElement.filter("Pseudostate")){%>
	<%if (kind=="entryPoint" && transitionsFromEntryPointLeadToTargetsInSameRegion()){%>
			<%}else{%>
<%alert("error","NOT VALID(sm01): All transitions from an entryPoint must lead to the targets that are containted in the same region.")%>
		<%}%>
	<%if (kind=="exitPoint"){%>
		<%if (incoming.nSize() > 1){%>
<%alert("error","NOT VALID (sm02): exitPoints can have at most one imcoming transition.")%>
		<%}%>
	<%}%>
	<%if (kind=="entryPoint"){%>
		<%if (incoming.nSize() > 0 ){%>
<%alert("error","NOT VALID (sm03): entryPoints cannot have incoming transitions.")%>
		<%}%>
	<%}%>
		<%if (kind=="exitPoint"){%>
		<%if (outgoing.nSize() > 0 ){%>
<%alert("error","NOT VALID (sm04): exitPoints cannot have outgoing transitions.")%>
		<%}%>
	<%}%>
<%}%>


<%script type="uml.StateMachine" name="statemachine_reference_loop_validation" post="trim()"%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: There must be no loops in referencing sub-state machines. --%>
<%if (submachineState == null){%>
<%alert("error",StateMachineReferenceLoopDetection)%>
<%}%>


<%script type="uml.Region" name="region_validation_rules" post="trim()"%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("Pseudostate")){%>
	<%if (kind=="deepHistory"){%>
<%alert("error","DISCARDED(sm10): Pseudostates of kind 'deepHistory' are  not supported.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML StateMachine may contain only one initial node. --%>
<%if (ownedElement.filter("Pseudostate")[kind=="initial"].nSize() > 1){%>
<%alert("error","NOT VALID(sm11): A ModelicaML StateMachine can contain only one initial node.")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML StateMachine may contain only one shalloHistory node. --%>
<%if (ownedElement.filter("Pseudostate")[kind=="shallowHistory"].nSize() > 1){%>
<%alert("error","NOT VALID(sm11): A ModelicaML StateMachine can contain only one shalloHistory node.")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML StateMachine must have exactly one initial node or a shallowHistory node. --%>
<%if (ownedElement.filter("Pseudostate")[kind=="initial"].nSize() < 1 && ownedElement.filter("Pseudostate")[kind=="shallowHistory"].nSize() < 1){%>
<%alert("error","MISSING(sm12.1): Initial node or ShallowHistory node in: '"+stateMachine.name+state.name+"'")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML StateMachine can have only one initial node or one shallowHistory node. --%>
<%if (ownedElement.filter("Pseudostate")[kind=="initial"].nSize() > 0 && ownedElement.filter("Pseudostate")[kind=="shallowHistory"].nSize() > 0 ){%>
<%alert("error","NOT VALID(sm12.2): A ModelicaML StateMachine can have only one initial node or one shallowHistory node")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML StateMachine initial node can have only one outgoing transition. --%>
<%if (ownedElement.filter("Pseudostate")[kind=="initial"].nSize() == 1 && ownedElement.filter("Pseudostate")[kind=="initial"].outgoing.nSize() > 1){%>
<%alert("error","MISSING(sm13.1): initial node can have only one outgoing transition.")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A ModelicaML StateMachine shallowHistory node can have only one outgoing transition. --%>
<%if (ownedElement.filter("Pseudostate")[kind=="shallowHistory"].nSize() == 1 && ownedElement.filter("Pseudostate")[kind=="shallowHistory"].outgoing.nSize() > 1){%>
<%alert("error","MISSING(sm13.2): shallowHistory node can have only one outgoing transition.")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: The outgoing transition from the initial node may not have a guard on an initial transition. --%>
<%if (ownedElement.filter("Transition")[source.filter("Pseudostate").kind=="initial" && guard.specification.filter("LiteralString").value != ""]){%>
<%alert("error","NOT VALID(sm14): Transitions from the InitialNode cannot have guards.")%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: The outgoing transition from the initial node or shalloHistory node may not be an inter-level transition. --%>
<%for (ownedElement.filter("Transition")[source.filter("Pseudostate").kind=="initial" || source.filter("Pseudostate").kind=="shallowHistory"]){%>
<%if (source.container.qualifiedName != target.container.qualifiedName){%>
<%alert("error","NOT VALID(sm15): The outgoing transition from the "+kind+" node cannot be an inter-level transition.")%>
<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: The outgoing transition from the shallowHistory node may not have a guard on an initial transition. --%>
<%if (ownedElement.filter("Transition")[source.filter("Pseudostate").kind=="shallowHistory" && guard.specification.filter("LiteralString").value != ""]){%>
<%alert("error","NOT VALID(sm16): Transitions from the shallowHistory node are not allowed to have guards.")%>
<%}%>




<%for (ownedElement.filter("Pseudostate")[kind=="choice" || kind=="junction"]){%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A choice/junction Pseudostate can only have one incoming transition. --%>
	<%if (incoming.nSize() > 1){%>
	<%alert("error","NOT VALID(sm19.1):  A "+eClass().name+" with kind '"+kind+"' can only have one incoming transition.")%>
	<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: A choice/junction Pseudostate must have incoming and outgoing transitions. --%>
	<%if (incoming.nSize() == 0 || outgoing.nSize() == 0){%>
	<%alert("error","NOT VALID(sm19.2): A "+eClass().name+" with kind '"+kind+"' must have incoming and outgoing transitions.")%>
	<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Choice or Junction should have an else branch. --%>
	<%if (outgoing[guard.specification.filter("LiteralString").value.trim() =="else"].nSize() < 1 ){%>
<%alert("warning","MISSING(sm33): One outgoing transition with 'else' guard. This may lead to an unexpected behavior if the guards of multiple transitions can be true at the same time.")%>
	<%}%>
<%}%>





<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Transitions to forks must have state as source.  --%>
<%for (ownedElement.filter("Transition")[target.filter("Pseudostate").kind=="fork"]){%>
	<%if (source.eClass().name == "State" || source.filter("Pseudostate").kind == "initial"){%>
		<%}else{%>
<%alert("error","NOT VALID(sm20): Transitions to forks must have state or initial node as source. Source state is: '"+source.qualifiedName +", target state is:" + target.qualifiedName+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Transitions to forks may not be inter-level. --%>
<%for (ownedElement.filter("Transition")[target.filter("Pseudostate").kind=="fork"]){%>
	<%if ( source.container.qualifiedName != target.container.qualifiedName){%>
<%alert("error","NOT VALID(sm21): Transitions to forks may not be inter-level. Source state is: '"+source.qualifiedName +", target state is:" + target.qualifiedName+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: In a complete statemachine, a fork vertex must have at least two outgoing transitions and exactly one incoming transition. --%>
<%for (ownedElement.filter("Pseudostate")[kind=="fork"]){%>
	<%if (incoming.nSize() != 1 || outgoing.nSize() < 2){%>
<%alert("error","NOT VALID(sm25): In a complete statemachine, a fork vertex must have at least two outgoing transitions and exactly one incoming transition.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("Pseudostate")[kind=="fork"]){%>
<%-- CONSTRAINT DESCRIPTION: Fork-outgoing transitions must lead to states inside the same composite state. --%>
	<%if (transitionsFromForkLeadToTargetsInSameCompositeState()){%>
		<%}else{%>
<%alert("error","NOT VALID(sm27.1): Fork-outgoing transitions must lead to states inside the same composite state.")%>
	<%}%>
<%-- CONSTRAINT DESCRIPTION: Fork-outgoing transitions must lead to states that are in orthogonal regions. --%>
	<%if (transitionsFromForkLeadToTargetsInOrthogonalRegions()){%>
		<%}else{%>
<%alert("error","NOT VALID(sm27.2): Fork-outgoing transitions must lead to states that are in orthogonal regions.")%>
	<%}%>
<%}%>




<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Transitions from join must have State or FinalState as target. --%>
<%for (ownedElement.filter("Transition")[source.filter("Pseudostate").kind=="join"]){%>
	<%if (target.eClass().name == "State" || target.eClass().name == "FinalState"){%>
		<%}else{%>
<%alert("error","NOT VALID(sm22.1): Transitions from join must have State or FinalState as target. Source is: '"+source.qualifiedName +", target state is:" + target.qualifiedName+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Transitions from join may not be inter-level. --%>
<%for (ownedElement.filter("Transition")[source.filter("Pseudostate").kind=="join"]){%>
	<%if ( target.container.qualifiedName != source.container.qualifiedName){%>
<%alert("error","NOT VALID(sm22.2): Transitions from join may not be inter-level. Source state is: '"+source.qualifiedName +", target state is:" + target.qualifiedName+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: In a complete statemachine, a join vertex must have at least two incoming transitions and exactly one outgoing transition. --%>
<%for (ownedElement.filter("Pseudostate")[kind=="join"]){%>
	<%if (incoming.nSize() < 2 || outgoing.nSize() != 1){%>
<%alert("error","NOT VALID(sm22.3): In a complete statemachine, a join vertex must have at least two incoming transitions and exactly one outgoing transition. Name:'"+qualifiedName+"'")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%for (ownedElement.filter("Pseudostate")[kind=="join"]){%>
<%-- CONSTRAINT DESCRIPTION: Join-incoming transitions must originate from states inside the same composite state. --%>
	<%if (transitionsToJoinOriginateFromSameCompositeState()){%>
		<%}else{%>
<%alert("error","NOT VALID(sm22.4): Join-incoming transitions must originate from states inside the same composite state.")%>
	<%}%>
<%-- CONSTRAINT DESCRIPTION: Join-incoming transitions must originate from orthogonal regions. --%>
	<%if (transitionsToJoinOriginateFromTargetsInOrthogonalRegions()){%>
		<%}else{%>
<%alert("error","NOT VALID(sm22.5): Join-incoming transitions must originate from orthogonal regions.")%>
	<%}%>
	
<%}%>




<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Source and target must be set for a transition. --%>
<%for (ownedElement.filter("Transition")){%>
	<%if (target != null && source != null){%>
		<%}else{%>
<%alert("error","NOT VALID(sm00): Source and target must be set for a transition.")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Transitions cannot cross region borders inside the same composite state.--%>
<%for (ownedElement.filter("Transition")){%>
	<%if (	source.eClass().name != "ConnectionPointReference"
			&& source.filter("Pseudostate").kind != "entryPoint"
			&& source.filter("Pseudostate").kind != "exitPoint"

			&& target.eClass().name != "ConnectionPointReference"
			&& target.filter("Pseudostate").kind != "entryPoint"
			&& target.filter("Pseudostate").kind != "exitPoint"
			){%>
		<%if (source.container.qualifiedName != target.container.qualifiedName){%>
			<%if (source.getContainingStateQualifiedName == target.getContainingStateQualifiedName){%>
<%alert("error","NOT VALID(sm17): Transitions cannot cross region borders inside the same composite state. Transition name: '"+name+"' in StateMachine or Composite State: '"+container.stateMachine.name+container.state.name+"'")%>
			<%}%>
		<%}%>	
	<%}%>
<%}%>




<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: ConnectionPointReference may only reference one entryPoint/exitPoits.
- ConnectionPointReference darf nur auf ein entry knoten oder ein exit knoten verweisen. --%>
<%for (ownedElement.filter("State")[metamodel::isSubmachineState]){%>
	<%for (ownedElement.filter("ConnectionPointReference")){%>
		<%if (entry.nSize() > 1){%>
<%alert("error","NOT VALID(sm30.1): ConnectionPointReference may only reference one entryPoint.")%>
		<%}else{%>
			<%if (exit.nSize() > 1){%>
<%alert("error","NOT VALID(sm30.2): ConnectionPointReference may only reference one exitPoint.")%>
				<%}else{%>
			<%}%>
		<%}%>
	<%if (entry.nSize() == 1){%>
		<%if (outgoing.nSize() > 0){%>
<%alert("error","NOT VALID(sm30.3): ConnectionPoint referencing an entryPoint may not have outgoing transitions.")%>		
		<%}%>
	<%}%>
	<%if (exit.nSize() == 1){%>
		<%if (incoming.nSize() > 0){%>
<%alert("error","NOT VALID(sm30.3): ConnectionPoint referencing an exitPoint may not have incoming transitions.")%>		
		<%}%>
	<%}%>
	<%if (entry.nSize() == 0 && exit.nSize() == 0){%>
<%alert("error","NOT VALID(sm30.3): ConnectionPoint must include references to entry- or exitPoits.")%>
	<%}%>
	<%if (exit.nSize() > 0){%>
		<%if ((!exit.incoming.nSize() == 0) && outgoing.nSize() == 0){%>
<%alert("error","NOT VALID(sm30.4): ConnectionPoint referencing an exitPoint must have an outgoing transition.")%>		
		<%}%>
	<%}%>
<%}%>
<%}%>




<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Final state may not have outgoing transitions. --%>
<%for (ownedElement.filter("FinalState")){%>
	<%if (outgoing.nSize() > 0 ){%>
<%alert("error","NOT VALID(sm31): Final state '"+name+"' may not have outgoing transitions")%>
	<%}%>
<%}%>
<%-- ############################################################### --%>
<%-- CONSTRAINT DESCRIPTION: Terminate state may not have outgoing transitions. --%>
<%for (ownedElement.filter("Pseudostate")[kind=="terminate"]){%>
	<%if (outgoing.nSize() > 0 ){%>
<%alert("error","NOT VALID(sm32): Terminate state '"+name+"' may not have outgoing transitions")%>
	<%}%>
<%}%>


<%alert("error",checkRegionForNameDuplicatesOfStates())%>



<%-- ############################################################### --%>
<%-- Helper  														--%>
<%-- ############################################################### --%>
<%script type="uml.Vertex" name="getContainingStateQualifiedName" post="trim()"%>
<%if (container.state != null){%><%container.state.qualifiedName%><%}else{%><%container.owner.filter("StateMachine").qualifiedName%><%}%>
