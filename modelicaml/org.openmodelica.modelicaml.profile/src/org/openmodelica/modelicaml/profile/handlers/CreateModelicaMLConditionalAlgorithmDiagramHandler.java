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
 * THIS OSMC PUBLIC LICENSE (OSMC-PL) VERSION 1.2. 
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
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011, implementation of simulation plugins
 */
package org.openmodelica.modelicaml.profile.handlers;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.uml.diagram.activity.CreateActivityDiagramCommand;
import org.eclipse.papyrus.uml.tools.utils.NamedElementUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.openmodelica.modelicaml.common.constants.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateModelicaMLConditionalAlgorithmDiagramHandler.
 */
public class CreateModelicaMLConditionalAlgorithmDiagramHandler extends
		CreateActivityDiagramCommand implements IHandler {
	
	/*
	 * TODO: does not work. The UML::Activity and the diagram are created.
	 * But the created UML::Activity is not attached to the diagram.
	 */
	
//	/** The behavior. */
//	private Behavior behavior = null;
//	
//	/* (non-Javadoc)
//	 * @see org.eclipse.papyrus.diagram.common.commands.CreateBehavioredClassifierDiagramCommand#initializeDiagram(org.eclipse.emf.ecore.EObject)
//	 */
//	@Override
//	protected void initializeDiagram(EObject diagram) {
//		if(diagram instanceof Diagram) {
//			Diagram diag = (Diagram)diagram;
//			if(behavior != null) {
//				//ModelicaML specific: apply stereotype 
//				applyStereotype();
//				diag.setElement(behavior);
//				createBehaviorView(diag);
//			}
//			diag.setName(getName());
//		}
//
//	}
//	
//	@Override
//	protected Diagram createDiagram(Resource diagramResource, EObject owner, String name) {
//		Diagram diagram = null;
//		if(owner instanceof org.eclipse.uml2.uml.Package) {
//			diagram = ViewService.createDiagram(owner, getDiagramNotationID(), getPreferenceHint());
//		} else if(owner instanceof BehavioredClassifier) {
//			diagram = ViewService.createDiagram(((BehavioredClassifier)owner).getNearestPackage(), getDiagramNotationID(), getPreferenceHint());
//		}
//		// create diagram
//		if(diagram != null) {
//			setName(name);
//
//			initializeModel(owner);
//			initializeDiagram(diagram);
//			diagramResource.getContents().add(diagram);
//		}
//		return diagram;
//	}
//	
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	protected void initializeModel(EObject owner) {
//
//		if(owner.eClass() == getBehaviorEClass()) {
//			behavior = (Behavior)owner;
//
//		} else {
//			behavior = (Behavior)UMLFactory.eINSTANCE.create(getBehaviorEClass());
//
//			if(owner instanceof BehavioredClassifier) {
//				BehavioredClassifier behaviorClassifier = (BehavioredClassifier)owner;
//				behaviorClassifier.getOwnedBehaviors().add(behavior);
//
//			} else if(owner instanceof Package) {
//				org.eclipse.uml2.uml.Package pack = (org.eclipse.uml2.uml.Package)owner;
//				pack.getPackagedElements().add(behavior);
//
//			}
//			behavior.setName(NamedElementUtil.getName(behavior));
//		}
//	}
//	
//	/**
//	 * Creates the behavior view.
//	 * 
//	 * @param diagram
//	 *            the diagram
//	 */
//	private void createBehaviorView(Diagram diagram) {
//		ViewService.getInstance().createView(Node.class, new EObjectAdapter(behavior), diagram, null, ViewUtil.APPEND, true, getPreferenceHint());
//	}
//
//	//ModelicaML specific
//	/**
//	 * Apply stereotype.
//	 */
//	private void applyStereotype(){
//		if (behavior != null) {
//			Stereotype s = behavior.getApplicableStereotype(Constants.stereotypeQName_ConditionalAlgorithm);
//			if (s != null ) {
//				behavior.applyStereotype(s);
//			}
//			else {
//				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//				MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype 'ConditionalAlgorithm(Diagram)' to " 
//						+ behavior.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");
//
//			}
//		}
//	}
	
}
