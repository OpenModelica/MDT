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
package org.openmodelica.modelicaml.properties;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Package;
import org.osgi.framework.ServiceException;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLSectionTester.
 */
public class ModelicaMLSectionTester extends PropertyTester {

	/**
	 * Instantiates a new modelica ml section tester.
	 */
	public ModelicaMLSectionTester() {
		// TODO Auto-generated constructor stub
	}

	/** Tester ID for UML Model nature. */
	public final static String IS_MODELICAML_MODEL = "isModelicaMLModel"; //$NON-NLS-N$
	
	/** The MODELICAM l_ id. */
	public static String MODELICAML_ID = "ModelicaML";


	/**
	 * Test the receiver against the selected property.
	 *
	 * @param receiver the receiver
	 * @param property the property
	 * @param args the args
	 * @param expectedValue the expected value
	 * @return true, if successful
	 */
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

		// Ensure Papyrus is the active editor
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if((editor == null) || (!(editor instanceof PapyrusMultiDiagramEditor))) {
			return false;
		}
		Object currentValue = null;
		if(IS_MODELICAML_MODEL.equals(property)) {
			currentValue = testProteusModelNature(receiver);
			return (currentValue == expectedValue);
		}

		return false;
	}

	/**
	 * True is root object is a UML Model with Proteus Profile (and sub profiles) applied.
	 *
	 * @param receiver the receiver
	 * @return true, if successful
	 */
	protected boolean testProteusModelNature(Object receiver) {
		boolean isProteusModel = false;

		try {
			ServiceUtilsForActionHandlers serviceUtils = new ServiceUtilsForActionHandlers();
			UmlModel openedModel;
			try {
				openedModel = (UmlModel)serviceUtils.getModelSet().getModel(UmlModel.MODEL_ID);
				if(openedModel != null) {

					EObject root = openedModel.lookupRoot();
					if(root instanceof Package) {
						Profile proteus = ((Package)root).getAppliedProfile(MODELICAML_ID);
						if(proteus != null) {
							isProteusModel = true;
						}
					}
				}
			} catch (org.eclipse.papyrus.infra.core.services.ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			//Activator.log.error(e);
		} catch (NotFoundException e) {
			//Activator.log.error(e);
		}

		return isProteusModel;
	}

}
