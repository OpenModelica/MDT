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
package org.openmodelica.modelicaml.view.valuebindings.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.editor.xtext.modification.ui.internal.ModificationActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;
import org.openmodelica.modelicaml.view.valuebindings.Activator;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeHelper;

import com.google.inject.Injector;

public class DialogDerivedCode extends Dialog {

	private StyledText text;
	
	private Label lblMessage;

	private String string = null; // string is the derived code, error or log string depending on the mode
	
	public final static int MODE_CODE = 0;
	public final static int MODE_ERROR_MESSAGE = 1;
	public final static int MODE_LOG = 2;
	
	private int mode = MODE_ERROR_MESSAGE; //set it by default
	
	private Injector injector;
	private String fileExtension;
	private PropertiesSectionXtextEditorHelper editor;
	private TreeObject treeObject;
	private DeriveValueBindingCodeHelper deriveValueBindingCodeHelper;
	
	public DialogDerivedCode(Shell parentShell, 
									String string, 
									int mode, 
									DeriveValueBindingCodeHelper deriveValueBindingCodeHelper,
									TreeObject treeObject) {
		super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.APPLICATION_MODAL);
		this.mode = mode;
		this.deriveValueBindingCodeHelper = deriveValueBindingCodeHelper;
		this.treeObject = treeObject;
        this.string = string;
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
    	if (mode == MODE_CODE) {
    		shell.setText("Derived Code");
    		shell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png"));

		}
    	else if (mode == MODE_ERROR_MESSAGE) {
    		shell.setText("Code Derivation Validation Results");
    		shell.setImage(SWTResourceManager.getImage(Activator.class, "/org/eclipse/jface/dialogs/images/message_error.gif"));
		}
    	else if (mode == MODE_LOG) {
    		shell.setText("Code Derivation Log");
    		shell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/log.png"));
		}
    }
	
	
	protected void createButtonsForButtonBar(Composite parent) {
		// this button shall not be shown again if the logs are actually displayed.
		if (this.mode != MODE_LOG) { 
			Button logsButton = createButton(parent, 11, "See Logs ...    ", false);
	    	logsButton.addListener(3, new Listener() {
				@Override
				public void handleEvent(Event event) {
						String log = deriveValueBindingCodeHelper.getLogString();
						DialogDerivedCode dialog = new DialogDerivedCode(getParentShell(), log, DialogDerivedCode.MODE_LOG, deriveValueBindingCodeHelper, treeObject);
						dialog.open(); // do nothing after closing the dialog.
				}

			});
	    	logsButton.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/log.png"));
	    	setButtonLayoutData(logsButton);
		}
    	
    	// create OK and Cancel buttons by default
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }
	
	
	
	
	@Override
	protected void okPressed() {
		if (mode == MODE_CODE) {
			string = editor.getText();
		}
		super.okPressed();
	}

	public String getString(){
		return string;
	}
	
	public String getCode(){
		if (mode == MODE_CODE) {
			return string;	
		}
		return null;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        
        if (this.mode == MODE_CODE) {
            lblMessage = new Label(composite, SWT.NONE);
    		lblMessage.setBounds(10, 10, 380, 13);
		}

		if (mode == MODE_CODE) {
			lblMessage.setText("The following Value Client binding code was derived based on the selected Value Mediators and Providers: ");			

			Composite editorComposite = new Composite(composite, SWT.BORDER);
	        editorComposite.setLayout(new GridLayout());
	        GridData editorCompositeGD = new GridData(SWT.FILL, SWT.FILL, true, true);
	        editorCompositeGD.heightHint = convertHorizontalDLUsToPixels(130);
	        //editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	        editorComposite.setLayoutData(editorCompositeGD);
    		injector = ModificationActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.modification.Modification");
    		fileExtension = "" + ".modelicamlmodification"; 
    		editor = new PropertiesSectionXtextEditorHelper(treeObject.getFirstLevelComponent(), injector, null, "No text yet ...", fileExtension);

    		// set code completion and marker support data
    		ModelicaMLContentAssist.setSelectedSourceElement(treeObject.getFirstLevelComponent()); 
    		
    		// for modification: this is used to obtain the list of the modified component attributes.
    		if (treeObject.getFirstLevelComponent() instanceof Property) {
    			ModelicaMLContentAssist.setPropertyName( StringUtls.replaceSpecChar( ((Property)treeObject.getFirstLevelComponent()).getName()) );
    			Element owningClass = treeObject.getFirstLevelComponent().getOwner(); 
    			if (owningClass instanceof Class) { // components of the owner of the property being modified
    				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
    			}
    		}
    		
    		editor.showEditor(editorComposite, SWT.BORDER);
    		editor.setTextToEdit(this.string);
    		
		} 
		else if (mode == MODE_ERROR_MESSAGE) {
//			lblMessage.setText("The following inconsistencies are detected: ");

			text = new StyledText(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
			text.setLayout(new GridLayout());
	        GridData textGD = new GridData(SWT.FILL, SWT.FILL, true, true);
	        textGD.heightHint = convertHorizontalDLUsToPixels(130);
	        textGD.widthHint = convertHorizontalDLUsToPixels(400);
	        text.setLayoutData(textGD);
	        
			text.setText(this.string + "\n\n");	
	        text.setEditable(false);
		}
		else if (mode == MODE_LOG) {
//			lblMessage.setText("");

			text = new StyledText(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
			text.setLayout(new GridLayout());
	        GridData textGD = new GridData(SWT.FILL, SWT.FILL, true, true);
	        textGD.heightHint = convertHorizontalDLUsToPixels(130);
	        textGD.widthHint = convertHorizontalDLUsToPixels(400);
	        text.setLayoutData(textGD);
	        
			text.setText(this.string + "\n\n");	
	        text.setEditable(false);
		}
        
        return parent;
	}
}
