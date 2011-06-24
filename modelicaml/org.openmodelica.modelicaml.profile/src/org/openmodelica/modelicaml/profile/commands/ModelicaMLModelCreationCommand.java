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
package org.openmodelica.modelicaml.profile.commands;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.core.extension.commands.ModelCreationCommandBase;
import org.eclipse.papyrus.umlutils.PackageUtil;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelicaMLModelCreationCommand.
 */
public class ModelicaMLModelCreationCommand extends ModelCreationCommandBase {

	/**
	 * Instantiates a new modelica ml model creation command.
	 */
	public ModelicaMLModelCreationCommand() {
		// TODO Auto-generated constructor stub
	}

	/** The Constant COMMAND_ID. */
	public static final String COMMAND_ID = "modelicaml"; 
	//public static final String COMMAND_ID = "uml";
	/** The Constant PROFILES_PATHMAP. */
	public static final String PROFILES_PATHMAP = "pathmap://PALETTE_PROFILE/"; //$NON-NLS-1$
	
	/** The Constant MODELICAML_PROFILE_URI. */
	public static final String MODELICAML_PROFILE_URI = PROFILES_PATHMAP + "ModelicaML.profile.uml"; //$NON-NLS-1$
//	public static final String MODELICAML_TESTING_PROFILE_URI = PROFILES_PATHMAP + "ModelicaMLTesting.profile.uml";
	
	//public static final String PROTEUS_PROFILE_URI = PROFILES_PATHMAP + "SysML.profile.uml"; //$NON-NLS-1$
	
	/**
	 * Creates the root element.
	 *
	 * @return the e object
	 * @see org.eclipse.papyrus.core.extension.commands.ModelCreationCommandBase#createRootElement()
	 */

	@Override
	protected EObject createRootElement() {
		return UMLFactory.eINSTANCE.createModel();
	}

	/**
	 * Initialize model.
	 *
	 * @param owner the owner
	 * @see org.eclipse.papyrus.core.extension.commands.ModelCreationCommandBase#initializeModel(org.eclipse.emf.ecore.EObject)
	 */

	@Override
	protected void initializeModel(EObject owner) {
		super.initializeModel(owner);
		((org.eclipse.uml2.uml.Package)owner).setName(getModelName());

		// Retrieve ModelicaML profile and apply with Sub-profile
		Profile modelicaml = (Profile)PackageUtil.loadPackage(URI.createURI(MODELICAML_PROFILE_URI), owner.eResource().getResourceSet());
		if(modelicaml != null) {
			PackageUtil.applyProfile(((org.eclipse.uml2.uml.Package)owner), modelicaml, true);
		}
//		Profile modelicamlTesting = (Profile)PackageUtil.loadPackage(URI.createURI(MODELICAML_TESTING_PROFILE_URI), owner.eResource().getResourceSet());
//		if(modelicamlTesting != null) {
//			PackageUtil.applyProfile(((org.eclipse.uml2.uml.Package)owner), modelicamlTesting, true);
//		}
	}

	/**
	 * Gets the model name.
	 * 
	 * @return the model name
	 */
	protected String getModelName() {
		return "ModelicaMLModel";
	}
}
