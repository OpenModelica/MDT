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
package org.openmodelica.modelicaml.view.componentstree.dialogs;

import java.util.HashSet;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.constants.Constants;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.instantiation.TreeParent;
import org.openmodelica.modelicaml.common.instantiation.TreeUtls;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.editor.xtext.array.ui.internal.ArraysubscriptActivator;
import org.openmodelica.modelicaml.editor.xtext.modification.ui.internal.ModificationActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;
import org.openmodelica.modelicaml.view.componentstree.Activator;
import org.openmodelica.modelicaml.view.valuebindings.dialogs.DialogDerivedCode;
import org.openmodelica.modelicaml.view.valuebindings.helpers.DeriveValueBindingCodeHelper;

import com.google.inject.Injector;

public class DialogComponentModification extends Dialog {
    private String title;
    private String message;
    private String value = "";//$NON-NLS-1$

    private Button okButton;
    private Text text;
    private String initialValue;
	private TreeObject treeObject;
	private TreeParent root;
	private PropertiesSectionXtextEditorHelper editor;
	private Injector injector;
	private String fileExtension;
//	private Element selectedElement;
	private String stereotypePropertyName;

	private Button deriveButton;
	private HashSet<Element> preCollectedMediators;
	private boolean reCollecMediatorsIfEmpty = false;
	
    public DialogComponentModification(Shell parentShell, 
    		String dialogTitle,
            String dialogMessage, 
            String initialValue, 
            TreeParent treeObject, 
            TreeParent root,
            HashSet<Element> preCollectedMediators,
            boolean reCollecMediatorsIfEmpty) {
    	
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.title = dialogTitle;
        this.initialValue = initialValue;
        this.treeObject = treeObject;
        this.root = root;
        this.setPreCollectedMediators(preCollectedMediators);
        this.setReCollecMediatorsIfEmpty(reCollecMediatorsIfEmpty);
        
        message = dialogMessage;
        if (initialValue == null) {
			value = "";//$NON-NLS-1$
		} else {
			value = initialValue;
		}
//        this.selectedElement = treeObject.getProperty();
        this.stereotypePropertyName = Constants.propertyName_modification; // this dialog is used only for modifications.
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
        	value = editor.getText().trim();
        	
//        	// TEST
//        	XtextResource resource = editor.getXtextResource();
//        	Iterator<EObject> i = resource.getAllContents();
//        	while (i.hasNext()) {
//    			EObject object = i.next();
//    			System.err.println(object);
////    			if (object instanceof modification_alternatives) {
////					
////				}
////    			if (object instanceof left_hand_component_reference) {
////    				
////    				String ref = ((left_hand_component_reference)object).getRef();
////    				array_subscripts subscripts1 = ((left_hand_component_reference)object).getSubscripts1();
////    				EList<array_subscripts> subscripts = ((left_hand_component_reference)object).getSubscripts();
////    				
////    				EList<String> ref1 = ((left_hand_component_reference)object).getRef1();
////    				
////    				System.err.println(object);
////    				
////    				System.err.println( "Ref: " + ((left_hand_component_reference)object).getRef());
////    				System.err.println("Expression: " + ((left_hand_component_reference)object).getExpr());
//////    				System.err.println(((left_hand_component_reference)object).toString());
//////    				System.err.println(((left_hand_component_reference)object).getRef());
//////    				System.err.println(((left_hand_component_reference)object).getRef1());
////				}
//    			
//    		}
        	// TEST END
        	
        } else {
            value = initialValue;
        }
        super.buttonPressed(buttonId);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        if (title != null) {
			shell.setText(title);
		}
    }

    protected void createButtonsForButtonBar(Composite parent) {
 
    	final DeriveValueBindingCodeHelper deriveValueBindingCodeHelper = new DeriveValueBindingCodeHelper();
    	deriveValueBindingCodeHelper.initialize(root.getSelectedClass().getModel(), TreeUtls.classInstantiation, root, getPreCollectedMediators(), isReCollecMediatorsIfEmpty());
    	
    	// the user shall select the providers, so don't activate the automatic selection of preferred providers and the deletion of old bindings!
    	deriveValueBindingCodeHelper.deriveBindingCodeForClient(treeObject, false, false);

    	deriveButton = createButton(parent, 11, "Derive Code ...    ", false);
    	deriveButton.addListener(3, new Listener() {
			@Override
			public void handleEvent(Event event) {

				// clear logs
				deriveValueBindingCodeHelper.clearErorrString();
				deriveValueBindingCodeHelper.clearErrorsDetected();
				deriveValueBindingCodeHelper.clearLog();

				// derive code once again, but now with user guidance
				deriveValueBindingCodeHelper.deriveBindingCodeForClient(treeObject, true, false);
				String code = deriveValueBindingCodeHelper.getCode();
				if (code != null) {
					DialogDerivedCode dialog = new DialogDerivedCode(getParentShell(), treeObject.getDotPathWithoutFirstLevelComponent() + " = " + code, DialogDerivedCode.MODE_CODE, deriveValueBindingCodeHelper, treeObject);
					dialog.open();
					if (dialog.getReturnCode() == IDialogConstants.OK_ID) {
						String string = dialog.getCode();
						if (string != null) {
							editor.setTextToEdit(string);
						}
					}
				}
				else {
					String log = deriveValueBindingCodeHelper.getErrorString();
					DialogDerivedCode dialog = new DialogDerivedCode(getParentShell(), log, DialogDerivedCode.MODE_ERROR_MESSAGE, deriveValueBindingCodeHelper, treeObject);
					dialog.open(); // do nothing after closing the dialog.
				}
			}
		});
    	
    	if (deriveValueBindingCodeHelper.isUserSelectionRequired()) {
    		deriveButton.setImage(ResourceManager.getPluginImage("org.eclipse.ui", "/icons/full/obj16/warn_tsk.gif"));
		}
    	else if (deriveValueBindingCodeHelper.errorsDetected()) {
    		deriveButton.setImage(SWTResourceManager.getImage(Activator.class, "/org/eclipse/jface/dialogs/images/message_error.gif"));
    	}
    	else {
    		deriveButton.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png"));
    	}

    	
    	setButtonLayoutData(deriveButton);
    	
    	// create OK and Cancel buttons by default
        okButton = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, true);
       
        createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);

        //do this here because setting the text will set enablement on the ok
        editor.getEditorWidget().setFocus();
        if (value != null) {
            editor.setTextToEdit(value);
            editor.getEditorWidget().setCaretOffset(value.length());
        }
    }
    
    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected Control createDialogArea(Composite parent) {
        // create composite
        Composite composite = (Composite) super.createDialogArea(parent);
        // create message
        if (message != null) {
            Label label = new Label(composite, SWT.WRAP);
            label.setText("* " + message);
            GridData data = new GridData(GridData.GRAB_HORIZONTAL
                    | GridData.HORIZONTAL_ALIGN_FILL
                    | GridData.VERTICAL_ALIGN_CENTER);
            data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
            label.setLayoutData(data);
            label.setFont(parent.getFont());
        }
        
        
        //#######################################################################
        // XText editor instead of the simple text.
        Composite editorComposite = new Composite(composite, SWT.BORDER);
        editorComposite.setLayout(new GridLayout());
        GridData editorCompositeGD = new GridData(SWT.FILL, SWT.FILL, true, true);
        editorCompositeGD.heightHint = convertHorizontalDLUsToPixels(130);
        //editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        editorComposite.setLayoutData(editorCompositeGD);
        
		
        if (stereotypePropertyName.equals("arraySize")) {
    		// ################################ Adjust start
    		injector = ArraysubscriptActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.array.Arraysubscript");
    		fileExtension = "" + ".modelicamlarraysubscript"; 
		}
        else if (stereotypePropertyName.equals("modification")) {
    		// ################################ Adjust start
    		injector = ModificationActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.modification.Modification");
    		fileExtension = "" + ".modelicamlmodification"; 
		}

		editor = new PropertiesSectionXtextEditorHelper(treeObject.getFirstLevelComponent(), injector, null, "No text yet ...", fileExtension);
		// ################################ Adjust end

		
		// set code completion and marker support data
		ModelicaMLContentAssist.setSelectedSourceElement(treeObject.getFirstLevelComponent()); 
		
		// for modification: this is used to obtain the list of the modified component attributes.
		if (treeObject.getFirstLevelComponent() instanceof Property) {
			//System.err.println("Property name: " + ((Property)selectedElement).getName());
			ModelicaMLContentAssist.setPropertyName( StringUtls.replaceSpecChar( ((Property)treeObject.getFirstLevelComponent()).getName()) );
			//System.err.println(ModelicaMLContentAssist.getModifiedComponentReferenceSortedList());

			Element owningClass = treeObject.getFirstLevelComponent().getOwner(); 
			if (owningClass instanceof Class) { // components of the owner of the property being modified
				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
			}
		}
		
		editor.showEditor(editorComposite, SWT.BORDER);
		editor.setTextToEdit(this.initialValue);
        //#######################################################################
        

        Label note_label = new Label(composite, SWT.WRAP);
        String info ="";
        if (treeObject.getModificationStoreLocation() instanceof Generalization) {
			info = " extension modification in " + ((Class)((Generalization)treeObject.getModificationStoreLocation()).getSources().get(0)).getName();
		}
        else {
        	info = " component " + treeObject.getFirstLevelComponent().getName();
        }
        note_label.setText("* Note, this modification will be stored in " + info);
        GridData note_label_data = new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL
                | GridData.VERTICAL_ALIGN_CENTER);
        note_label_data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
        note_label.setLayoutData(note_label_data);
        note_label.setFont(parent.getFont());
       

        applyDialogFont(composite);

        return composite;
    }


    /**
     * Returns the ok button.
     * 
     * @return the ok button
     */
    protected Button getOkButton() {
        return okButton;
    }

    /**
     * Returns the text area.
     * 
     * @return the text area
     */
    protected Text getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    
	/**
	 * Returns the style bits that should be used for the input text field.
	 * Defaults to a single line entry. Subclasses may override.
	 * 
	 * @return the integer style bits that should be used when creating the
	 *         input text
	 * 
	 * @since 3.4
	 */
	protected int getInputTextStyle() {
		//return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL;
		return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL;
		//return SWT.MULTI | SWT.BORDER;
	}

	public HashSet<Element> getPreCollectedMediators() {
		return preCollectedMediators;
	}

	public void setPreCollectedMediators(HashSet<Element> preCollectedMediators) {
		this.preCollectedMediators = preCollectedMediators;
	}

	public boolean isReCollecMediatorsIfEmpty() {
		return reCollecMediatorsIfEmpty;
	}

	public void setReCollecMediatorsIfEmpty(boolean reCollecMediatorsIfEmpty) {
		this.reCollecMediatorsIfEmpty = reCollecMediatorsIfEmpty;
	}
}
