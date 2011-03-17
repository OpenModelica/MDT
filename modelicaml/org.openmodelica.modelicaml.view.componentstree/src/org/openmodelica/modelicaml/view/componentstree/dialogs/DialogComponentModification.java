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
package org.openmodelica.modelicaml.view.componentstree.dialogs;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.LabelProvider;
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
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.ast.TreeObject;
import org.openmodelica.modelicaml.common.ast.TreeParent;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.services.StringUtls;
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
public class DialogComponentModification extends Dialog {
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
    
    /** The add button. */
    private Button addButton;

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

	/** The text2. */
	private Text text2;
	
	/** The Comments. */
	private EList<Comment> Comments;

	/** The tree object. */
	private TreeObject treeObject;

	/** The add value provider button. */
	private Button addValueProviderButton;
	
	
	/** The root. */
	private TreeParent root;
	
	/** The variables list. */
	private HashSet<String> variablesList;
	
	/** The linked variables list. */
	private HashSet<String> linkedVariablesList;
	
	
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
	 * @param root
	 *            the root
	 */
    public DialogComponentModification(Shell parentShell, String dialogTitle,
            String dialogMessage, String initialValue, TreeParent treeObject, IInputValidator validator, TreeParent root) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.title = dialogTitle;
        this.initialValue = initialValue;
        this.treeObject = treeObject;
        this.root = root;

        message = dialogMessage;
        if (initialValue == null) {
			value = "";//$NON-NLS-1$
		} else {
			value = initialValue;
		}
        this.validator = validator;
        
        this.variablesList = getVariablesList();
        this.linkedVariablesList= getLinkedVariablesList();
        
        this.selectedElement = treeObject.getProperty();
        this.stereotypePropertyName = "modification"; // this dialog is used only for modifications.
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            //value = text.getText();
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

    /**
     * Gets the right hand modification string.
     *
     * @return the right hand modification string
     */
    private String getRightHandModificationString(){
    	String rightHandString = null;
    	
    	return rightHandString;
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

    
    /**
	 * Open element list selection dialog.
	 * 
	 * @param title
	 *            the title
	 * @param msg
	 *            the msg
	 * @param listOfDotPath
	 *            the list of dot path
	 */
    protected void openElementListSelectionDialog(String title, String msg, HashSet<String> listOfDotPath){
    	Shell shell = new Shell();
		final ElementListSelectionDialog lsd = new ElementListSelectionDialog(shell,new LabelProvider());	    
		lsd.setTitle(title + " (" + listOfDotPath.size() + ")");
		lsd.setMessage(listOfDotPath.size() + " variables were found in this class. " + msg);
		lsd.setHelpAvailable(false);
		
		lsd.setElements(listOfDotPath.toArray());
		lsd.setMultipleSelection(true);
		lsd.setFilter("*");
		lsd.open();
		
		Object[] results = lsd.getResult();
		String selVar = "";
		if (results != null) {
			for (int i = 0; i < results.length; i++) {
				selVar = selVar  + "     " + results[i];
			}
			//String s = this.text.getText(); // text in the edited field.
			String s = this.editor.getText(); // text in the edited field.
			
			// split the text based on the cursor position.
//			String s1 = s.substring(0,this.text.getCaretPosition());
//			String s2 = s.substring(this.text.getCaretPosition(), s.length());
			String s1 = s.substring(0,editor.getEditorWidget().getCaretOffset());
			String s2 = s.substring(editor.getEditorWidget().getCaretOffset(), s.length());

//			this.text.setText(s1 + " " + selVar + " " + s2); // add it at the position of the cursor
//			this.text.setSelection(s1.length() + 1, s1.length() + 1 + selVar.length());
			
			this.editor.setTextToEdit(s1 + " " + selVar + " " + s2); // add it at the position of the cursor
			this.editor.getEditorWidget().setSelection(s1.length() + 1, s1.length() + 1 + selVar.length());

			//this.text.selectAll();
		}
    }
    
    
    /**
	 * Gets the variables list.
	 * 
	 * @return the variables list
	 */
    private HashSet<String> getVariablesList(){
    	HashSet<String> listOfDotPath = ((TreeParent)treeObject).getAllPrimitiveVariablesDotPath(this.root);
    	return listOfDotPath;
    }

    /**
	 * Gets the linked variables list.
	 * 
	 * @return the linked variables list
	 */
    private HashSet<String> getLinkedVariablesList(){
    	HashSet<String> list = new HashSet<String>();

    	// for using dependencies
    	// TODO: remove it. this is now replaced by the value-proxy concept (see below). 
    	HashSet<String> listOfDotPath = ((TreeParent)treeObject).getAllLinkedPrimitiveVariablesDotPath(this.treeObject.getProperty(), this.root);
    	list.addAll(listOfDotPath);
    	
    	// for using proxies.
    	String valueBindingStereotypeQName = "ModelicaMLTesting::ValueBinding::ValueClient"; 
		String valueBindingPropertyName= "obtainsValueFrom";
    	HashSet<Property> listOfProxies = ((TreeParent)treeObject).getValueBindings(this.treeObject.getProperty(), valueBindingStereotypeQName, valueBindingPropertyName);
    	HashSet<String> listOfDotPathThroughProxies = ((TreeParent)treeObject).getAllLinkedPrimitiveVariablesDotPathThroughProxy(this.treeObject.getProperty(), listOfProxies, this.root);
    	list.addAll(listOfDotPathThroughProxies);
    	
    	return list;
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
    	addButton = createButton(parent, 1, "Find Variables"+ " (" + variablesList.size() + ")", true);
		addButton.addListener(3, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String title = "List of Instances";
				String msg = "You can select one or more variables from the list below and " +
						"\nclick 'OK' in order to return their dot-path to the editor.";
				//openElementListSelectionDialog( title, msg, getVariablesList());
				openElementListSelectionDialog( title, msg, variablesList);
			}
		});
		
		addValueProviderButton = createButton(parent, 1, "Find Linked Variables" + " (" + linkedVariablesList.size() + ")", true);
		addValueProviderButton.addListener(3, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String title = "List of Linked Instances";
				String msg = "You can select one or more variables from the list below and " +
				"\nclick 'OK' in order to return their dot-path to the editor.";
//				openElementListSelectionDialog( title, msg, getLinkedVariablesList());
				openElementListSelectionDialog( title, msg, linkedVariablesList);
			}
		});
    	
    	// create OK and Cancel buttons by default
        okButton = createButton(parent, IDialogConstants.OK_ID,
                IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
        //do this here because setting the text will set enablement on the ok
        // button
//        text.setFocus();
//        if (value != null) {
//            text.setText(value);
//            text.selectAll();
//        }
        
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

        
//        Label label_titleDothPath = new Label(composite, SWT.WRAP);
//        label_titleDothPath.setText("Component dot-notation path:");
//        GridData label_titleDothPath_data = new GridData(GridData.GRAB_HORIZONTAL
//                | GridData.HORIZONTAL_ALIGN_FILL
//                | GridData.VERTICAL_ALIGN_CENTER);
//        label_titleDothPath_data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
//        label_titleDothPath.setLayoutData(label_titleDothPath_data);
//        label_titleDothPath.setFont(parent.getFont());
        
        
        
        
        //#######################################################################
        // REPLACED BY XTEXT EDITOR BELOW
//        text = new Text(composite,  getInputTextStyle() | SWT.RESIZE);
//        GridData data = new GridData(GridData.GRAB_HORIZONTAL
//                | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
//                | GridData.VERTICAL_ALIGN_FILL);
//        data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
////  	data.grabExcessVerticalSpace = true;
////		data.heightHint = convertHorizontalDLUsToPixels(200);
//        data.heightHint = convertHorizontalDLUsToPixels(130);
//        text.setBackground(new Color(null,255,255,255));
//        text.setLayoutData(data);
//        //text.setEditable(false);
//        text.addModifyListener(new ModifyListener() {
//            public void modifyText(ModifyEvent e) {
//                validateInput();
//            }
//        });
        //#######################################################################
        
        
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
//			if (root.getSelectedClass() instanceof Class) { // components from the root class being instantiated
//				ModelicaMLContentAssist.addComponentsOfThisClassToComponentReferencelist( (Class)root.getSelectedClass() ); 
//			}
		}
//		else if (selectedElement instanceof Generalization) {
//			Element owningClass = ((Generalization)selectedElement).getSpecific(); 
//			if (owningClass instanceof Class) {
//				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
//			}
//		}
		
		editor.showEditor(editorComposite, SWT.BORDER);
		editor.setTextToEdit(this.initialValue);
        //#######################################################################
        

		
		
        
        Label note_label = new Label(composite, SWT.WRAP);
        Element c = treeObject.getFirstLevelComponent().getOwner();
        String className = ((Classifier)c).getName();
        note_label.setText("* Note, this modification will be stored in '" + className +"."+ treeObject.getFirstLevelComponent().getName() + "'");
        GridData note_label_data = new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL
                | GridData.VERTICAL_ALIGN_CENTER);
        note_label_data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
        note_label.setLayoutData(note_label_data);
        note_label.setFont(parent.getFont());
       
        
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
		return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL;
		//return SWT.MULTI | SWT.BORDER;
	}
}
