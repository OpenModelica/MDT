/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Linköpings universitet, Department of Computer and Information Science,
 * SE-58183 Linköping, Sweden.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR 
 * THIS OSMC PUBLIC LICENSE (OSMC-PL). 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES RECIPIENT'S ACCEPTANCE
 * OF THE OSMC PUBLIC LICENSE OR THE GPL VERSION 3, ACCORDING TO RECIPIENTS CHOICE. 
 *
 * The OpenModelica software and the Open Source Modelica
 * Consortium (OSMC) Public License (OSMC-PL) are obtained
 * from OSMC, either from the above address,
 * from the URLs: http://www.ida.liu.se/projects/OpenModelica or  
 * http://www.openmodelica.org, and in the OpenModelica distribution. 
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH
 * IN THE BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF OSMC-PL.
 *
 * See the full OSMC Public License conditions for more details.
 *
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-now
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adoptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.profile.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.diagram.common.commands.SemanticAdapter;
import org.eclipse.papyrus.diagram.statemachine.CreateStateMachineDiagramCommand;
import org.eclipse.papyrus.diagram.statemachine.custom.helpers.Zone;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.StateMachineCompartmentEditPart;
import org.eclipse.papyrus.diagram.statemachine.edit.parts.StateMachineNameEditPart;
import org.eclipse.papyrus.diagram.statemachine.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.diagram.statemachine.providers.ElementInitializers;
import org.eclipse.papyrus.diagram.statemachine.providers.UMLElementTypes;
import org.eclipse.papyrus.umlutils.NamedElementUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateModelicaMLStateMachineDiagramHandler.
 */
public class CreateModelicaMLStateMachineDiagramHandler extends
		CreateStateMachineDiagramCommand implements IHandler {

	/** The state machine. */
	private StateMachine stateMachine = null;

	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.diagram.statemachine.CreateStateMachineDiagramCommand#initializeModel(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected void initializeModel(EObject owner) {
		if(owner.eClass() == getBehaviorEClass()) {
			stateMachine = (StateMachine)owner;

		} else {
			stateMachine = UMLFactory.eINSTANCE.createStateMachine();
			if(owner instanceof BehavioredClassifier) {
				BehavioredClassifier behaviorClassifier = (BehavioredClassifier)owner;
				behaviorClassifier.getOwnedBehaviors().add(stateMachine);

			} else if(owner instanceof Package) {
				org.eclipse.uml2.uml.Package pack = (org.eclipse.uml2.uml.Package)owner;
				pack.getPackagedElements().add(stateMachine);

			}
			ElementInitializers.getInstance().init_StateMachine_2000(stateMachine);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.diagram.statemachine.CreateStateMachineDiagramCommand#initializeDiagram(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected void initializeDiagram(EObject diagram) {
		if(diagram instanceof Diagram) {
			Diagram diag = (Diagram)diagram;
			if(stateMachine != null) {
				
				//ModelicaML specific: apply ModelicaML stereotype
				applyStereotype();
				
				diag.setElement(stateMachine);
				View stateMachineView = ViewService.getInstance().createView(Node.class, new EObjectAdapter(stateMachine), diag, null, ViewUtil.APPEND, true, getPreferenceHint());
				Zone.setX(stateMachineView, defaultX);
				Zone.setY(stateMachineView, defaultY);
				Zone.setWidth(stateMachineView, defaultWidth);
				Zone.setHeight(stateMachineView, defaultHeight);

				View compartmentView = null;

				Iterator<Node> it = stateMachineView.getChildren().iterator();

				while(it.hasNext()) {
					Node currentNode = it.next();
					if(currentNode.getLayoutConstraint() == null) {
						currentNode.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
					}
					if(UMLVisualIDRegistry.getVisualID(currentNode.getType()) == StateMachineNameEditPart.VISUAL_ID) {
						Zone.setWidth(currentNode, defaultWidth);
						Zone.setHeight(currentNode, defaultHeader);
					} else if(UMLVisualIDRegistry.getVisualID(currentNode.getType()) == StateMachineCompartmentEditPart.VISUAL_ID) {
						Zone.setY(currentNode, defaultHeader);
						Zone.setWidth(currentNode, defaultWidth);
						Zone.setHeight(currentNode, defaultHeight - defaultHeader);
						compartmentView = currentNode;
					}
				}

				Region region = null;
				EList<Region> regions = stateMachine.getRegions();
				if (regions.isEmpty()) {
					region = UMLFactory.eINSTANCE.createRegion();
					regions.add(region);
					region.setName(NamedElementUtil.getDefaultNameWithIncrement(region, regions));
				} else {
					region = stateMachine.getRegions().get(0);
				}

				IAdaptable regionAdaptable = new SemanticAdapter(region, null);
				String semanticHint = ((IHintedType)UMLElementTypes.Region_3000).getSemanticHint();

				if(compartmentView != null) {
					Node regionNode = ViewService.getInstance().createNode(regionAdaptable, compartmentView, semanticHint, -1, getPreferenceHint());
					if(regionNode.getLayoutConstraint() == null) {
						regionNode.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
					}
					// add region specifics
					Zone.createRegionDefaultAnnotation(regionNode);
					Zone.setWidth(regionNode, defaultWidth);
					Zone.setHeight(regionNode, defaultHeight - defaultHeader);

				}
			}
			diag.setName(getName());
		}
	}
	
	//ModelicaML specific
	/**
	 * Apply stereotype.
	 */
	private void applyStereotype(){
		if (stateMachine != null) {
			Stereotype s = stateMachine.getApplicableStereotype("ModelicaML::ModelicaBehaviorConstructs::ModelicaMLStateMachine");
			if (s != null ) {
				stateMachine.applyStereotype(s);
			}
			else {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype ModelicaMLStateMachine to " + stateMachine.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");

			}
		}
	}
}
