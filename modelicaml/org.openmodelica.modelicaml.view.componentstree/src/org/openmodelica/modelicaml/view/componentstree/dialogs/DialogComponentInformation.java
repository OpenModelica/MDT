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
package org.openmodelica.modelicaml.view.componentstree.dialogs;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.openmodelica.modelicaml.common.ast.TreeObject;
import org.openmodelica.modelicaml.common.ast.TreeParent;
import org.openmodelica.modelicaml.common.services.UmlServices;



// TODO: Auto-generated Javadoc
/**
 * A simple input dialog for soliciting an input string from the user.
 * <p>
 * This concrete dialog class can be instantiated as is, or further subclassed as
 * required.
 * </p>
 */
public class DialogComponentInformation extends Dialog {
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
	
	/** The tree object. */
	private TreeObject treeObject;
	
	/** The modifications text. */
	private Text modificationsText;

	/** The comments text. */
	private Text commentsText;

	/** The declaration text. */
	private Text declarationText;

	/** The modification text. */
	private Text modificationText;

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
	 * @param treeObject
	 *            the tree object
	 * @param validator
	 *            an input validator, or <code>null</code> if none
	 */
    public DialogComponentInformation(Shell parentShell, String dialogTitle,
            String dialogMessage, String initialValue, TreeParent treeObject, IInputValidator validator) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.title = dialogTitle;
        this.initialValue = initialValue;
        message = dialogMessage;
        this.treeObject = treeObject;
        if (initialValue == null) {
			value = "";//$NON-NLS-1$
		} else {
			value = initialValue;
		}
        this.validator = validator;
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            value = text.getText();
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
        text.setFocus();
        if (value != null) {
            text.setText(value);
            text.selectAll();
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
            label.setText(message);
            GridData data = new GridData(GridData.GRAB_HORIZONTAL
                    | GridData.HORIZONTAL_ALIGN_FILL
                    | GridData.VERTICAL_ALIGN_CENTER);
            data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
            label.setLayoutData(data);
            label.setFont(parent.getFont());
        }

        
        Label label_titleDothPath = new Label(composite, SWT.WRAP);
        label_titleDothPath.setText("Component path:");
        label_titleDothPath.setToolTipText(value);
        GridData label_titleDothPath_data = new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL
                | GridData.VERTICAL_ALIGN_CENTER);
        label_titleDothPath_data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
        label_titleDothPath.setLayoutData(label_titleDothPath_data);
        label_titleDothPath.setFont(parent.getFont());
        
        text = new Text(composite,  getInputTextStyle() | SWT.RESIZE);
        GridData data = new GridData(GridData.GRAB_HORIZONTAL
                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
                | GridData.VERTICAL_ALIGN_FILL);
        data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//  	data.grabExcessVerticalSpace = true;
//		data.heightHint = convertHorizontalDLUsToPixels(200);
        data.heightHint = convertHorizontalDLUsToPixels(12);
        text.setBackground(new Color(null,255,255,255));
        text.setLayoutData(data);
        text.setEditable(false);
//        text.addModifyListener(new ModifyListener() {
//            public void modifyText(ModifyEvent e) {
//                validateInput();
//            }
//        });

        
        // Component type
        //if (treeObject.getProperty() != null ) {
        if (treeObject.getProperty() != null && treeObject.getComponentType() != null ) {
        	Classifier cOriginalType = (Classifier) treeObject.getProperty().getType();
        	Classifier cFinalType = (Classifier) treeObject.getComponentType();
            String cTypeString = "Not defined";

            if (treeObject.hasRedeclaredType() && cFinalType!= null && cOriginalType!= null) {
				cTypeString = "redeclared to '" + cFinalType.getName() + "' (original type was " + cOriginalType.getName() + " )";  
			}
            else if (cFinalType != null) {
            	cTypeString = "'" + cFinalType.getName() + "'";
            }
            
            Label label_type = new Label(composite, SWT.WRAP);
            label_type.setText("Component type: " + cTypeString);
            label_type.setToolTipText(cFinalType.getQualifiedName());
		}
        
        // Causality
        if (treeObject.isInput() || treeObject.isOutput()) {
            Label label_causality = new Label(composite, SWT.WRAP);
            if (treeObject.isInput()) {
                label_causality.setText("Component causality: input");				
			}
            else if (treeObject.isOutput()) {
                label_causality.setText("Component causality: output");				
			}
		}
        
 //		if ( title_label != null) {
//
//			Label label_modificationsText = new Label(composite, SWT.WRAP);
//			label_modificationsText.setText(title_label);
//	        
//			modificationsText = new Text(composite,  SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.RESIZE);
//	        GridData modificationsTextData = new GridData(GridData.GRAB_HORIZONTAL
//	                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
//	                | GridData.VERTICAL_ALIGN_FILL);
//	        modificationsTextData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
////	  		modificationsTextData.grabExcessVerticalSpace = true;
////			modificationsTextData.heightHint = convertHorizontalDLUsToPixels(200);
//	        modificationsTextData.heightHint = convertHorizontalDLUsToPixels(25);
//	        modificationsText.setBackground(new Color(null,255,255,255));
//	        modificationsText.setLayoutData(modificationsTextData);
//	        modificationsText.setText(declarationString);
//	        modificationsText.setEditable(false);
//	        modificationsText.addModifyListener(new ModifyListener() {
//	            public void modifyText(ModifyEvent e) {
//	                validateInput();
//	            }
//	        });
//		}
		
		
        // Declaration
		if (treeObject.isLeaf() && treeObject.getProperty() != null ) {
	        String declarationString = "";
			if (treeObject.getProperty() != null) {
				Stereotype stereotype = treeObject.getProperty().getAppliedStereotype("ModelicaML::ModelicaCompositeConstructs::Variable");
				if (stereotype != null) {
					Object declarationEquationOrAssignment = UmlServices.getStereotypeValue((Element)treeObject.getProperty(), stereotype.getName(), "declarationEquationOrAssignment");
					if (declarationEquationOrAssignment instanceof String) {
						declarationString = " " + declarationEquationOrAssignment.toString().trim();
					}						
				}
			}
			
			Label label_declaration = new Label(composite, SWT.WRAP);
			label_declaration.setText("Component declaration: ");
	        
			declarationText = new Text(composite,  SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.RESIZE);
	        GridData declarationTextData = new GridData(GridData.GRAB_HORIZONTAL
	                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
	                | GridData.VERTICAL_ALIGN_FILL);
	        declarationTextData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
	//  	modificationsTextData.grabExcessVerticalSpace = true;
	//		modificationsTextData.heightHint = convertHorizontalDLUsToPixels(200);
	        declarationTextData.heightHint = convertHorizontalDLUsToPixels(25);
	        declarationText.setBackground(new Color(null,255,255,255));
	        declarationText.setLayoutData(declarationTextData);
	        declarationText.setText(declarationString);
	        declarationText.setEditable(false);
	        declarationText.addModifyListener(new ModifyListener() {
	            public void modifyText(ModifyEvent e) {
	                validateInput();
	            }
	        });
		}
        
        // Modifications
        if (treeObject.getFinalModificationRightHand() != null) {
			Label label_modification = new Label(composite, SWT.WRAP);
			String modSource = "";
//			if (ModificationManager.isInModModListOfComponent(treeObject.getFirstLevelComponent(), treeObject.getDotPathWithoutFirstLevelComponent())) {
//				modSource = " in '" + treeObject.getFirstLevelComponent().getName() + "' ";
//			}
			NamedElement modSourceElement = treeObject.getFinalModificationSource();
			if (modSourceElement != null ) {
//				modSource = modSourceElement.eClass().getName() + " '" + modSourceElement.getQualifiedName() + "' ";
				modSource = "'" + modSourceElement.getQualifiedName() + "' ";
			}
			String modDescription = "";
			if (treeObject.getFinalModificationDescription() != null) {
				modDescription = treeObject.getFinalModificationDescription();
			}
			label_modification.setText("Overriden " + modDescription  + " in " + modSource + ": ");
			label_modification.setToolTipText(modSource);
			
			modificationText = new Text(composite,  SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.RESIZE);
	        GridData modificationTextData = new GridData(GridData.GRAB_HORIZONTAL
	                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
	                | GridData.VERTICAL_ALIGN_FILL);
	        modificationTextData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//  		modificationsTextData.grabExcessVerticalSpace = true;
//			modificationsTextData.heightHint = convertHorizontalDLUsToPixels(200);
	        modificationTextData.heightHint = convertHorizontalDLUsToPixels(25);
	        modificationText.setBackground(new Color(null,255,255,255));
	        modificationText.setLayoutData(modificationTextData);
	        modificationText.setText(" = " + treeObject.getFinalModificationRightHand());
	        modificationText.setEditable(false);
	        modificationText.addModifyListener(new ModifyListener() {
	            public void modifyText(ModifyEvent e) {
	                validateInput();
	            }
	        });
		}
        
		
        // Comments
        String commentString = "";
        EList<Comment> comments = treeObject.getComments();
        if (comments.size() > 0 ) {
        	//commentString = commentString + "Comments of '" + treeObject.getProperty().getName()+"':\n"; 
        	for (Comment comment : comments) {
    			commentString = commentString + comment.getBody() + " ";
    		}
        }
        
		if (!commentString.equals("")) {
			commentString = commentString + "\n\n";
		}
		
		if (treeObject.getProperty() != null ) {
			if (treeObject.getProperty().getType() != null) {
				 EList<Comment> commentsOfComponentType = treeObject.getProperty().getType().getOwnedComments();
				 if (commentsOfComponentType.size() > 0 ) {
					 commentString = commentString + "Comments from '" + ((Classifier)treeObject.getProperty().getType()).getName()+"' (component type):\n"; 
					 for (Comment comment : commentsOfComponentType) {
						commentString = commentString + comment.getBody() + " ";
					}
				}
			}
		}
		
		if (!commentString.equals("")) {
	        
	        Label label_titleComments = new Label(composite, SWT.WRAP);
	        label_titleComments.setText("Comments: ");
	        GridData label_titleComments_data = new GridData(GridData.GRAB_HORIZONTAL
	        		| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
	                | GridData.VERTICAL_ALIGN_CENTER);
	        label_titleComments_data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
	        label_titleComments.setLayoutData(label_titleDothPath_data);
	        label_titleComments.setFont(parent.getFont());
			
	        commentsText = new Text(composite,  SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.RESIZE);
	        GridData commentsTextData = new GridData(GridData.GRAB_HORIZONTAL
	                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
	                | GridData.VERTICAL_ALIGN_FILL);
	        commentsTextData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//	  		modificationsTextData.grabExcessVerticalSpace = true;
//			modificationsTextData.heightHint = convertHorizontalDLUsToPixels(200);
	        commentsTextData.heightHint = convertHorizontalDLUsToPixels(40);
	        commentsText.setBackground(new Color(null,255,255,255));
	        commentsText.setLayoutData(commentsTextData);
	        commentsText.setText(commentString);
	        commentsText.setEditable(false);
	        commentsText.addModifyListener(new ModifyListener() {
	            public void modifyText(ModifyEvent e) {
	                validateInput();
	            }
	        });
		}
        
//        errorMessageText = new Text(composite, SWT.READ_ONLY | SWT.WRAP);
//        errorMessageText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
//                | GridData.HORIZONTAL_ALIGN_FILL));
//        errorMessageText.setBackground(errorMessageText.getDisplay()
//                .getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
//        // Set the error message text
//        // See https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292
//        setErrorMessage(errorMessage);

        applyDialogFont(composite);
        
//        text2 = new Text(composite,  getInputTextStyle() | SWT.RESIZE);
//        GridData data2 = new GridData(GridData.GRAB_HORIZONTAL
//                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
//                | GridData.VERTICAL_ALIGN_FILL);
//        data2.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//        data2.grabExcessVerticalSpace = true;
//       //data.heightHint = convertHorizontalDLUsToPixels(200);
//        data2.heightHint = convertHorizontalDLUsToPixels(15);
//        text2.setLayoutData(data2);
//        text2.setEditable(false);
//        text2.addModifyListener(new ModifyListener() {
//            public void modifyText(ModifyEvent e) {
//                validateInput();
//            }
//        });
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
		//return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL;
		return SWT.MULTI | SWT.BORDER;
	}
}
