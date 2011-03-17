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
 * Contributors: 
 *   Wladimir Schamai, EADS Innovation Works 2009-2011
 *   Uwe Pohlmann, University of Paderborn 2009-2010
 *   Parham Vasaiely, EADS Innovation Works / Hamburg University of Applied Sciences 2009-2011
 */
package org.openmodelica.modelicaml.profile.handlers;

import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.papyrus.diagram.activity.CreateActivityDiagramCommand;
import org.eclipse.papyrus.umlutils.NamedElementUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateModelicaMLConditionalEquationsDiagramHandler.
 */
public class CreateModelicaMLConditionalEquationsDiagramHandler extends
		CreateActivityDiagramCommand implements IHandler {
	
	/** The behavior. */
	private Behavior behavior = null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.diagram.common.commands.CreateBehavioredClassifierDiagramCommand#initializeDiagram(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected void initializeDiagram(EObject diagram) {
		if(diagram instanceof Diagram) {
			Diagram diag = (Diagram)diagram;
			if(behavior != null) {
				//ModelicaML specific: apply stereotype 
				applyStereotype();
				diag.setElement(behavior);
				createBehaviorView(diag);
			}
			diag.setName(getName());
		}

	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.diagram.common.commands.CreateBehavioredClassifierDiagramCommand#initializeModel(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected void initializeModel(EObject owner) {

		if(owner.eClass() == getBehaviorEClass()) {
			behavior = (Behavior)owner;

		} else {
			behavior = (Behavior)UMLFactory.eINSTANCE.create(getBehaviorEClass());

			if(owner instanceof BehavioredClassifier) {
				BehavioredClassifier behaviorClassifier = (BehavioredClassifier)owner;
				behaviorClassifier.getOwnedBehaviors().add(behavior);

			} else if(owner instanceof Package) {
				org.eclipse.uml2.uml.Package pack = (org.eclipse.uml2.uml.Package)owner;
				pack.getPackagedElements().add(behavior);

			}
			behavior.setName(NamedElementUtil.getName(behavior));
		}
	}
	
	/**
	 * Creates the behavior view.
	 * 
	 * @param diagram
	 *            the diagram
	 */
	private void createBehaviorView(Diagram diagram) {
		ViewService.getInstance().createView(Node.class, new EObjectAdapter(behavior), diagram, null, ViewUtil.APPEND, true, getPreferenceHint());
	}

	//ModelicaML specific
	/**
	 * Apply stereotype.
	 */
	private void applyStereotype(){
		if (behavior != null) {
			Stereotype s = behavior.getApplicableStereotype("ModelicaML::ModelicaBehaviorConstructs::ConditionalEquations(Diagram)");
			if (s != null ) {
				behavior.applyStereotype(s);
			}
			else {
				Shell shell = new Shell();
				MessageDialog.openError(shell, "Error:", "Cannot apply ModelicaML stereotype 'ConditionalEquations(Diagram)' to " + behavior.getName() + ". Please make sure that ModelicaML is applied to the top-level model/package.");

			}
		}
	}
	
}
