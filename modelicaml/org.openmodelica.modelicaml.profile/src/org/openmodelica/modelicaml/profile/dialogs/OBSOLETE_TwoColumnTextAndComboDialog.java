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
package org.openmodelica.modelicaml.profile.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * A simple input dialog for soliciting an input string from the user.
 * <p>
 * This concrete dialog class can be instantiated as is, or further subclassed as
 * required.
 * </p>
 */
public class OBSOLETE_TwoColumnTextAndComboDialog extends Dialog {
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
//    private Text text;

    /**
     * Error message label widget.
     */
    private Text errorMessageText;
    
    /**
     * Error message string.
     */
    private String errorMessage;
//    private String initialValue;

//	public String var1NameString = "";
	/** The var1 name. */
private Text var1Name;//$NON-NLS-1$
	
//	public String var2NameString = "";
	/** The var2 name. */
private Text var2Name;
	
//	public String var3NameString = "";
	/** The var3 name. */
private Text var3Name;
	
//	public String var4NameString = "";
	/** The var4 name. */
private Text var4Name;
	
//	public String var5NameString = "";
	/** The var5 name. */
private Text var5Name;

	/** The var list. */
	private List<OBSOLETE_VariableData> varList = new ArrayList<OBSOLETE_VariableData>();
	
	/** The var1 type. */
	private CCombo var1Type;
	
	/** The var2 type. */
	private CCombo var2Type;
	
	/** The var3 type. */
	private CCombo var3Type;
	
	/** The var4 type. */
	private CCombo var4Type;
	
	/** The var5 type. */
	private CCombo var5Type;

	/** The initial value. */
private String initialValue = "";//$NON-NLS-1$
	
	/** The var1 type label. */
	private CLabel var1TypeLabel = null;
	
	/** The Modelica predefined types. */
	private String[] ModelicaPredefinedTypes = {"ModelicaReal", "ModelicaInteger", "ModelicaString", "ModelicaBoolean"};
	
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
     */
    public OBSOLETE_TwoColumnTextAndComboDialog(Shell parentShell, String dialogTitle,
            String dialogMessage, String initialValue, IInputValidator validator) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.title = dialogTitle;
        this.initialValue = initialValue;
        message = dialogMessage;
//        if (initialValue == null) {
//			value = "";//$NON-NLS-1$
//		} else {
//			value = initialValue;
//		}
        this.validator = validator;
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
        	if (!var1Name.getText().equals("")) {
				OBSOLETE_VariableData var = new OBSOLETE_VariableData(var1Name.getText(), var1Type.getText());
        		varList.add(var);
			}
        	if (!var2Name.getText().equals("")) {
				OBSOLETE_VariableData var = new OBSOLETE_VariableData(var2Name.getText(), var2Type.getText());
        		varList.add(var);
			}
        	if (!var3Name.getText().equals("")) {
				OBSOLETE_VariableData var = new OBSOLETE_VariableData(var3Name.getText(), var3Type.getText());
        		varList.add(var);
			}
        	if (!var4Name.getText().equals("")) {
				OBSOLETE_VariableData var = new OBSOLETE_VariableData(var4Name.getText(), var4Type.getText());
        		varList.add(var);
			}
        	if (!var5Name.getText().equals("")) {
				OBSOLETE_VariableData var = new OBSOLETE_VariableData(var5Name.getText(), var5Type.getText());
        		varList.add(var);
			}

        } else {
            //value = initialValue;
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
        
        //do this here because setting the text will set enablement on the OK button
        var1Name.setFocus();
        if (initialValue != null) {
        	var1Name.setText(initialValue);
        	var1Name.selectAll();
        	
        	var1Type.select(0);
        	var2Type.select(0);
        	var3Type.select(0);
        	var4Type.select(0);
        	var5Type.select(0);
        }
    }
    
    
    /**
	 * Gets the var list.
	 * 
	 * @return the var list
	 */
    public List<OBSOLETE_VariableData> getVarList(){
    	return this.varList;
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected Control createDialogArea(Composite parent) {
        // create composite
        Composite shell = (Composite) super.createDialogArea(parent);
        // create message
        
//        if (message != null) {
//            Label label = new Label(shell, SWT.WRAP);
//            label.setText(message);
//            GridData data = new GridData(GridData.GRAB_HORIZONTAL
//                    | GridData.HORIZONTAL_ALIGN_FILL
//                    | GridData.VERTICAL_ALIGN_CENTER);
//            data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//            label.setLayoutData(data);
//            label.setFont(parent.getFont());
//        }
       
        shell.setLayout(new GridLayout(4, false));
        
        Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Name:");
		
		var1Name = new Text(shell, SWT.BORDER);
		GridData gd_var1Name = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_var1Name.widthHint = 237;
		var1Name.setLayoutData(gd_var1Name);
		
		Label lblType = new Label(shell, SWT.NONE);
		lblType.setText("Type:");
		
		var1Type = new CCombo(shell, SWT.BORDER);
		var1Type.setText("ModelicaReal");
		var1Type.setEditable(false);
		var1Type.setItems(new String[] {"ModelicaReal", "ModelicaInteger", "ModelicaBoolean", "ModelicaString"});
		
		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Name:");
		
		var2Name = new Text(shell, SWT.BORDER);
		var2Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("Type:");
		
		var2Type = new CCombo(shell, SWT.BORDER);
		var2Type.setText("ModelicaReal");
		var2Type.setEditable(false);
		var2Type.setItems(new String[] {"ModelicaReal", "ModelicaInteger", "ModelicaBoolean", "ModelicaString"});
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("Name:");
		
		var3Name = new Text(shell, SWT.BORDER);
		var3Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("Type: ");
		
		var3Type = new CCombo(shell, SWT.BORDER);
		var3Type.setText("ModelicaReal");
		var3Type.setEditable(false);
		var3Type.setItems(new String[] {"ModelicaReal", "ModelicaInteger", "ModelicaBoolean", "ModelicaString"});
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("Name:");
		
		var4Name = new Text(shell, SWT.BORDER);
		var4Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("Type:");
		
		var4Type = new CCombo(shell, SWT.BORDER);
		var4Type.setText("ModelicaReal");
		var4Type.setEditable(false);
		var4Type.setItems(new String[] {"ModelicaReal", "ModelicaInteger", "ModelicaBoolean", "ModelicaString"});
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("Name:");
		
		var5Name = new Text(shell, SWT.BORDER);
		var5Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("Type");
		
		var5Type = new CCombo(shell, SWT.BORDER);
		var5Type.setEditable(false);
		var5Type.setText("ModelicaReal");
		var5Type.setItems(new String[] {"ModelicaReal", "ModelicaInteger", "ModelicaBoolean", "ModelicaString"});


////        errorMessageText = new Text(composite, SWT.READ_ONLY | SWT.WRAP);
////        errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
////                | GridData.HORIZONTAL_ALIGN_FILL));
////        errorMessageText.setBackground(errorMessageText.getDisplay()
////                .getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
////        // Set the error message text
////        // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292
////        setErrorMessage(errorMessage);
//
//        applyDialogFont(composite);
        return shell;
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
            //errorMessage = validator.isValid(text.getText());
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
		//return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL;
		return SWT.MULTI | SWT.BORDER;
	}
}
