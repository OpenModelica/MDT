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
package org.openmodelica.modelicaml.tabbedproperties.editors.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.validation.services.ModelicaMLMarkerSupport;
import org.openmodelica.modelicaml.editor.xtext.array.ui.internal.ArraysubscriptActivator;
import org.openmodelica.modelicaml.editor.xtext.modification.ui.internal.ModificationActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;

import com.google.inject.Injector;

// TODO: Auto-generated Javadoc
/**
 * A simple input dialog for soliciting an input string from the user.
 * <p>
 * This concrete dialog class can be instantiated as is, or further subclassed as
 * required.
 * </p>
 */
public class EditorDialog extends Dialog {
    /**
     * The title of the dialog.
     */
    private String title;

    /**
     * The message to display, or <code>null</code> if none.
     */
    private String message;

    /**
     * The input value; the empty string by default.
     */
    private String value = "";//$NON-NLS-1$

    /**
     * The input validator, or <code>null</code> if none.
     */
    private IInputValidator validator;

    /**
     * Ok button widget.
     */
    private Button okButton;

    /**
     * Input text widget.
     */
    private Text text;

    /**
     * Error message label widget.
     */
    private Text errorMessageText;
    
    /**
     * Error message string.
     */
    private String errorMessage;
    
    /** The initial value. */
    private String initialValue;

	/** The editor. */
	private PropertiesSectionXtextEditorHelper editor;
	
	/** The injector. */
	private Injector injector;
	
	/** The file extension. */
	private String fileExtension;

	/** The selected element. */
	private Element selectedElement;

	/** The stereotype property name. */
	private String stereotypePropertyName;

	/** The item number. */
	private int itemNumber;

    
    
    /**
	 * Creates an input dialog with OK and Cancel buttons. Note that the dialog
	 * will have no visual representation (no widgets) until it is told to open.
	 * <p>
	 * Note that the <code>open</code> method blocks for input dialogs.
	 * </p>
	 * 
	 * @param parentShell
	 *            the parent shell, or <code>null</code> to create a top-level
	 *            shell
	 * @param dialogTitle
	 *            the dialog title, or <code>null</code> if none
	 * @param dialogMessage
	 *            the dialog message, or <code>null</code> if none
	 * @param initialValue
	 *            the initial input value, or <code>null</code> if none
	 *            (equivalent to the empty string)
	 * @param validator
	 *            an input validator, or <code>null</code> if none
	 * @param selectedElement
	 *            the selected element
	 * @param stereotypePropertyName
	 *            the stereotype property name
	 * @param itemNumber
	 *            the item number
	 */
    public EditorDialog(Shell parentShell, String dialogTitle,
            String dialogMessage, 
            String initialValue, 
            IInputValidator validator, 
            Element selectedElement, 
            String stereotypePropertyName,
            int itemNumber ) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.title = dialogTitle;
        this.initialValue = initialValue;
        message = dialogMessage;
        
        if (initialValue == null) {
			value = "";//$NON-NLS-1$
		} else {
			value = initialValue;
		}
        this.validator = validator;
        this.selectedElement = selectedElement;
        this.stereotypePropertyName = stereotypePropertyName;
        if (itemNumber < 0 ) {
			this.itemNumber = 0;
		}
        else {
            this.itemNumber = itemNumber;
        }
        
        //System.err.println("itemNumber: " + this.itemNumber);
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            //value = text.getText();
        	value = editor.getText();
        	//value = editor.getText().trim().replaceFirst("\\(", "").substring(0, editor.getText().trim().length() - 1);
        	
        	// generate marker
        	generateUMLModelMarker();
        	
        } else {
            value = initialValue;
        }
        super.buttonPressed(buttonId);
    }

	// Marker support #########################################################
	/**
	 * Generate uml model marker.
	 */
	private void generateUMLModelMarker(){
		if (itemNumber >= 0) {
			Element element = selectedElement;
			
			
			if (element instanceof NamedElement) { 
				// create a marker for the uml model element
				String message = "The " + ((NamedElement)element).eClass().getName() 
									+ " '" + ((NamedElement)element).getName() + "' has errors in its '" + stereotypePropertyName + "' Modelica code in item " + itemNumber;
				
				if (editor.isDocumentHasErrors()) {
					ModelicaMLMarkerSupport.generateMarker(message, "error", (NamedElement)element);
				}
				else {
					ModelicaMLMarkerSupport.deleteMarker( message, (NamedElement)element);
				}
			}
			else if (element instanceof Generalization) {
				// create a marker for the uml model element
				String message = "The " + ( ((Generalization)element).getSpecific() ).eClass().getName() 
									+ " '" + (((Generalization)element).getSpecific()).getName() + "' has errors in its '" + stereotypePropertyName + "' Modelica code in item " + itemNumber;
				
				if (editor.isDocumentHasErrors()) {
					ModelicaMLMarkerSupport.generateMarker(message, "error", ((Generalization)element).getSpecific() );
				}
				else {
					ModelicaMLMarkerSupport.deleteMarker( message, ((Generalization)element).getSpecific() );
				}
			}
		}
	}
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        //shell.setBounds(getDefaultOrientation(), getDefaultOrientation(), 500, 350);
        shell.setBounds(100, 100, 500, 350);
        if (title != null) {
			shell.setText(title);
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        // create OK and Cancel buttons by default
        okButton = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
        //do this here because setting the text will set enablement on the ok
        // button
        editor.getEditorWidget().setFocus();
        if (value != null) {
            editor.setTextToEdit(value);
            editor.getEditorWidget().selectAll();
        }
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected Control createDialogArea(Composite parent) {

    	// create composite
        Composite composite = (Composite) super.createDialogArea(parent);
        // create message
        
        
        Composite editorComposite = new Composite(composite, SWT.BORDER);
        editorComposite.setLayout(new GridLayout());
        editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
        if (stereotypePropertyName.equals("arraySize")) {
    		// ################################ Adjust start
    		injector = ArraysubscriptActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.array.Arraysubscript");
    		fileExtension = "" + ".modelicamlarraysubscript"; // TODO rename the dsl extension to "modelicamlequationoralgorithm"
		}
        else if (stereotypePropertyName.equals("modification")) {
    		// ################################ Adjust start
    		injector = ModificationActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.modification.Modification");
    		fileExtension = "" + ".modelicamlmodification"; // TODO rename the dsl extension to "modelicamlequationoralgorithm"
		}
        

		editor = new PropertiesSectionXtextEditorHelper(selectedElement, injector, null, "No text yet ...", fileExtension);
		// ################################ Adjust end

		
		// set code completion and marker support data
		ModelicaMLContentAssist.setSelectedSourceElement(selectedElement);
		
		// for modification: this is used to obtain the list of the modified component attributes.
		if (selectedElement instanceof Property) {
			//System.err.println("Property name: " + ((Property)selectedElement).getName());
			ModelicaMLContentAssist.setPropertyName( StringUtls.replaceSpecChar( ((Property)selectedElement).getName()) );
			//System.err.println(ModelicaMLContentAssist.getModifiedComponentReferenceSortedList());

			Element owningClass = selectedElement.getOwner(); 
			if (owningClass instanceof Class) {
				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
			}
		}
		else if (selectedElement instanceof Generalization) {
			Element owningClass = ((Generalization)selectedElement).getSpecific(); 
			if (owningClass instanceof Class) {
				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
			}
		}
		
				
		editor.showEditor(editorComposite, SWT.BORDER);
		editor.setTextToEdit(this.initialValue);
		//editor.setTextToEdit("\\(" + this.initialValue + "\\)");
		
    	// generate marker
    	generateUMLModelMarker();
 
    	
    	
        
//        if (message != null) {
//            Label label = new Label(composite, SWT.WRAP);
//            label.setText(message);
//            GridData data = new GridData(GridData.GRAB_HORIZONTAL
//                    | GridData.HORIZONTAL_ALIGN_FILL
//                    | GridData.VERTICAL_ALIGN_CENTER);
//            data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//            label.setLayoutData(data);
//            label.setFont(parent.getFont());
//        }
//        text = new Text(composite,  getInputTextStyle() | SWT.RESIZE);
//        GridData data = new GridData(GridData.GRAB_HORIZONTAL
//                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
//                | GridData.VERTICAL_ALIGN_FILL);
//        data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//     //  data.grabExcessVerticalSpace = true;
//       data.heightHint = convertHorizontalDLUsToPixels(200);
//        text.setLayoutData(data);
//        text.addModifyListener(new ModifyListener() {
//            public void modifyText(ModifyEvent e) {
//                validateInput();
//            }
//        });
////        errorMessageText = new Text(composite, SWT.READ_ONLY | SWT.WRAP);
////        errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
////                | GridData.HORIZONTAL_ALIGN_FILL));
////        errorMessageText.setBackground(errorMessageText.getDisplay()
////                .getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
////        // Set the error message text
////        // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292
////        setErrorMessage(errorMessage);

        applyDialogFont(composite);
        return composite;
    }

    /**
     * Returns the error message label.
     * 
     * @return the error message label
     * @deprecated use setErrorMessage(String) instead
     */
    protected Label getErrorMessageLabel() {
        return null;
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

    /**
     * Returns the validator.
     * 
     * @return the validator
     */
    protected IInputValidator getValidator() {
        return validator;
    }

    /**
     * Returns the string typed into this input dialog.
     * 
     * @return the input string
     */
    public String getValue() {
        return value;
    }

    /**
     * Validates the input.
     * <p>
     * The default implementation of this framework method delegates the request
     * to the supplied input validator object; if it finds the input invalid,
     * the error message is displayed in the dialog's message line. This hook
     * method is called whenever the text changes in the input field.
     * </p>
     */
    protected void validateInput() {
        String errorMessage = null;
        if (validator != null) {
            errorMessage = validator.isValid(text.getText());
        }
        // Bug 16256: important not to treat "" (blank error) the same as null
        // (no error)
        setErrorMessage(errorMessage);
    }

    /**
     * Sets or clears the error message.
     * If not <code>null</code>, the OK button is disabled.
     * 
     * @param errorMessage
     *            the error message, or <code>null</code> to clear
     * @since 3.0
     */
    public void setErrorMessage(String errorMessage) {
    	this.errorMessage = errorMessage;
    	if (errorMessageText != null && !errorMessageText.isDisposed()) {
    		errorMessageText.setText(errorMessage == null ? " \n " : errorMessage); //$NON-NLS-1$
    		// Disable the error message text control if there is no error, or
    		// no error text (empty or whitespace only).  Hide it also to avoid
    		// color change.
    		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=130281
    		boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
    		errorMessageText.setEnabled(hasError);
    		errorMessageText.setVisible(hasError);
    		errorMessageText.getParent().update();
    		// Access the ok button by id, in case clients have overridden button creation.
    		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=113643
    		Control button = getButton(IDialogConstants.OK_ID);
    		if (button != null) {
    			button.setEnabled(errorMessage == null);
    		}
    	}
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
		return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL;
	}
}
