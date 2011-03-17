/*
 * This file is part of OpenModelica.
 *
 * Copyright (c) 1998-CurrentYear, Open Source Modelica Consortium (OSMC),
 * c/o Link�pings universitet, Department of Computer and Information Science,
 * SE-58183 Link�ping, Sweden.
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
package org.openmodelica.modelicaml.preferences.impl;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.openmodelica.modelicaml.preferences.Activator;

// TODO: Auto-generated Javadoc
/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class ModelicaMLOMCPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	/**
	 * Instantiates a new modelica ml preference page.
	 */
	public ModelicaMLOMCPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("ModelicaML preferences for the communication with OpenModelica compiler:");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
//		addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH, 
//				"&Directory preference:", getFieldEditorParent()));

		addField(
//				new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
//					"Generate Modelica Code (in background) after each save of the ModelicaML model.",
//					getFieldEditorParent()));
					new BooleanFieldEditor("ValidateModelicaMLModelBeforeAutomaticCodeGeneration",
							"Always validate ModelicaML model before each automatic code generation.",
							getFieldEditorParent()));
		
		addField(
//			new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
//				"Generate Modelica Code (in background) after each save of the ModelicaML model.",
//				getFieldEditorParent()));
				new BooleanFieldEditor("GenerateModelicaCodeInBackgroundAfterSave",
						"Always (re)generate Modelica code automatically in background after each save of the ModelicaML model.",
						getFieldEditorParent()));

		addField(
//				new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
//					"Generate Modelica Code (in background) after each save of the ModelicaML model.",
//					getFieldEditorParent()));
					new BooleanFieldEditor("LoadInOMCAfterEachCodeGeneration",
							"Always (re)load the ModelicaML model into OMC after each automatic code generation.",
							getFieldEditorParent()));

		addField(
					new BooleanFieldEditor("ClearOMCBeforeReloadingCode",
							"Always clear OMC before loading the ModelicaML model.",
							getFieldEditorParent()));
		
		
		addField(
//				new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
//					"Generate Modelica Code (in background) after each save of the ModelicaML model.",
//					getFieldEditorParent()));
					new BooleanFieldEditor("RegenerateCodeBeforeEachSimulation",
							"Always (re)generate Modelica code before simulation.",
							getFieldEditorParent()));

		
		addField(new RadioGroupFieldEditor(
				"synchronizationWithOMC",
			"Synchronization of the ModelicaML model with OMC:",
			1,
			new String[][] { { "Always (Re)generate .mo files and (re)load them in OMC.", "files" }, {
				"Always use the CORBA interface to send the (re)generated code as string.", "corba" }
		}, getFieldEditorParent()));
//		addField(
//			new StringFieldEditor("GenCodeProjectFolder", "Generated Code Project Folder:", getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}