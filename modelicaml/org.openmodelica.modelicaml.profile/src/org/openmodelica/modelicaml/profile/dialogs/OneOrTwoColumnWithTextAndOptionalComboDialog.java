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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;

// TODO: Auto-generated Javadoc
/**
 * A simple input dialog for soliciting an input string from the user.
 * <p>
 * This concrete dialog class can be instantiated as is, or further subclassed as
 * required.
 * </p>
 */
public class OneOrTwoColumnWithTextAndOptionalComboDialog extends Dialog implements IInputValidator {
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

    /**
     * Ok button widget.
     */
    private Button okButton;

    /**
     * Error message label widget.
     */
    private StyledText errorMessageText;
    
    /**
     * Error message string.
     */
    private String errorMessage;

    /** The initial value. */
	private String textInitialValue = "";//$NON-NLS-1$

	/** The number of items. */
	private int numberOfItems;
	
	/** The first col values edit. */
	private ArrayList<Text> firstColValuesEdit = new ArrayList<Text>();
	
	/** The second col values edit. */
	private ArrayList<CCombo> secondColValuesEdit = new ArrayList<CCombo>();
	
	/** The first col values. */
	private ArrayList<String> firstColValues = new ArrayList<String>();
	
	/** The second col values. */
	private ArrayList<String> secondColValues = new ArrayList<String>();
	
	/** The existing named elements list. */
	private EList<Element> existingNamedElementsList = new BasicEList<Element>();
	
	/** The existing names. */
	private List<String> existingNames = new ArrayList<String>();
	
	/** The meta class for new elements. */
	private EClass metaClassForNewElements;
	
	/** The parent element. */
	private Element parentElement;
	
	/** The combo items. */
	private String[] comboItems;

	/** The lbl text. */
	private String lblText;

	/** The lbl combo. */
	private String lblCombo;
	
    /**
	 * Creates an input dialog with OK and Cancel buttons. Note that the dialog
	 * will have no visual representation (no widgets) until it is told to open.
	 * <p>
	 * Note that the <code>open</code> method blocks for input dialogs.
	 * </p>
	 * 
	 * @param parentShell
	 *            the parent shell
	 * @param dialogTitle
	 *            the dialog title
	 * @param dialogMessage
	 *            the dialog message
	 * @param parentElement
	 *            the parent element
	 * @param metaClassForNewElements
	 *            the meta class for new elements
	 * @param numberOfItems
	 *            the number of items
	 * @param lblText
	 *            the lbl text
	 * @param textInitialValue
	 *            the text initial value
	 * @param lblCombo
	 *            the lbl combo
	 * @param comboItems
	 *            the combo items
	 */
    public OneOrTwoColumnWithTextAndOptionalComboDialog(	Shell parentShell, 
    							String dialogTitle,
    							String dialogMessage, 
    							 
    							Element parentElement, 
    							EClass metaClassForNewElements,

    							int numberOfItems,
    							
    							String lblText,
    							String textInitialValue,
    							String lblCombo,
    							String[] comboItems ) {
    	
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this.title = dialogTitle;
        this.message = dialogMessage;
        
        this.parentElement = parentElement;
        this.metaClassForNewElements = metaClassForNewElements;
        
        this.numberOfItems = numberOfItems;
        
        this.lblText = lblText;
        this.textInitialValue = textInitialValue;
        this.lblCombo = lblCombo;
        this.comboItems = comboItems;
        
         // get the names of element that exist in the parentElement
        if (this.parentElement instanceof NamedElement) {
        	existingNamedElementsList.addAll(((NamedElement)this.parentElement).getOwnedElements());
        	for (Element element : existingNamedElementsList) {
        		if (element.eClass().equals(this.metaClassForNewElements)) { // TODO: improve to enable the check like instanceof in order to support subclassing, i.e. to treat, for example, UML Ports as UML Properties
        		//if (element.eClass().isInstance(this.metaClassForNewElements)) { 
        		//if (element.eClass().isInstance(this.metaClassForNewElements)|| this.metaClassForNewElements.isSuperTypeOf(element.eClass())) { 
        			existingNames.add(((NamedElement)element).getName());
        			existingNames.add(StringUtls.replaceSpecChar( ((NamedElement)element).getName() )); // in order to avoid entering names that will be the same after the code generation.
				}
			}
		}
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            for (int i = 0; i < numberOfItems; i++) {
            	Text text = firstColValuesEdit.get(i);
            	if (text != null && !text.getText().trim().equals("")) {
            		firstColValues.add(text.getText().trim());
            		if (this.comboItems != null) {
            			secondColValues.add(secondColValuesEdit.get(i).getText().trim());
					}
				}
    		}
        } else {
        	// nothing to do here.
        }
        super.buttonPressed(buttonId);
    }

    /**
	 * Gets the first col values.
	 * 
	 * @return the first col values
	 */
    public ArrayList<String> getFirstColValues(){
    	return this.firstColValues;
    }
    
    /**
	 * Gets the second col values.
	 * 
	 * @return the second col values
	 */
    public ArrayList<String> getSecondColValues(){
    	return this.secondColValues;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
    	if (this.comboItems != null && this.lblCombo != null) { // with second column
    		//shell.setSize(590, 270);
    	}
    	else { // without second column
    		//shell.setSize(450, 270);
    	}
    	
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
       firstColValuesEdit.get(0).setFocus();
       
       if (textInitialValue != null) {
    	   firstColValuesEdit.get(0).setText(textInitialValue);
    	   firstColValuesEdit.get(0).selectAll();
       }
    }
    
    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    protected Control createDialogArea(Composite parent) {
       
    	// create composite
        Composite shell = (Composite) super.createDialogArea(parent);
        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        if (this.comboItems != null && this.lblCombo != null) { // if the column with combo shall be displayed
    		shell.setLayout(new GridLayout(2, false));
    	}
    	else {
    		shell.setLayout(new GridLayout(1, false));
    	}
        
        //#####################################################################################
        if (message != null) {
			CLabel lblMessage = new CLabel(shell, SWT.NONE);
			lblMessage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			GridData gd_lblMessage = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
			gd_lblMessage.heightHint = 16;
			gd_lblMessage.widthHint = 400;
			lblMessage.setLayoutData(gd_lblMessage);
			lblMessage.setText(message);
        }

		//#####################################################################################
        Composite errorText = new Composite(shell, SWT.NONE);
        errorText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_composite_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_composite_1.widthHint = 400;
		errorText.setLayoutData(gd_composite_1);
		
		errorMessageText = new StyledText(errorText, SWT.NONE);
		errorMessageText.setFont(SWTResourceManager.getFont("Arial", 8, SWT.NORMAL));
		//errorMessageText.setText("the name already exists.");
		errorMessageText.setEditable(false);
		errorMessageText.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		errorMessageText.setBounds(0, 0, 400, 13);
		setErrorMessage(errorMessage);

        for (int i = 0; i < numberOfItems; i++) {
    		//#####################################################################################
        	// text-field
        	Composite columns = new Composite(shell, SWT.NONE );
        	columns.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
    		GridData gd_composite_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
    		gd_composite_2.widthHint = 400;
    		columns.setLayoutData(gd_composite_2);
        	
        	CLabel lblName = new CLabel(columns, SWT.NONE);
    		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
    		lblName.setBounds(0, 0, 40, 20);
    		lblName.setText(this.lblText);
    		
    		final Text name = new Text(columns, SWT.BORDER);
    		name.setBounds(40, 0, 360, 20);
    		//name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    		
    		final ControlDecoration controlDecoration = new ControlDecoration(name, SWT.LEFT | SWT.TOP);
    		
    		name.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					errorMessage = isValid(name.getText().trim());
					setErrorMessage(errorMessage);
					
					if (errorMessage != null) {
						controlDecoration.show();
						for (Text text : firstColValuesEdit) {
							if (text != name) {
								text.setEnabled(false);
							}
							name.forceFocus();
						}
					}
					else {
						controlDecoration.hide();
						for (Text text : firstColValuesEdit) {
							text.setEnabled(true);
						}
					}
				}
			});
    		
    		name.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					if (!name.getText().trim().equals("")) {
						existingNames.add(name.getText().trim());
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					existingNames.remove(name.getText().trim());
					
					errorMessage = isValid(name.getText().trim());
					setErrorMessage(errorMessage);
				}
			});
    		
    		// add text to the local list.
    		this.firstColValuesEdit.add(name);
    		
    		
    		//#####################################################################################
    		// 2 Column: combo-box
    		if (this.comboItems != null && this.lblCombo != null) {
    			Composite comboComposite = new Composite(shell, SWT.NONE);
        		comboComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        		GridData gd_comboComposite = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        		gd_comboComposite.widthHint = 230;
        		gd_comboComposite.heightHint = 20;
        		comboComposite.setLayoutData(gd_comboComposite);
        		
        		Label lblCombo = new Label(comboComposite, SWT.NONE);
        		lblCombo.setFont(SWTResourceManager.getFont("Arial", 8, SWT.NORMAL));
        		lblCombo.setAlignment(SWT.RIGHT);
        		//lblCombo.setTopMargin(1);
        		lblCombo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        		lblCombo.setBounds(0, 0, 70, 20);
        		//lblCombo.setText("Restriction:");
        		lblCombo.setText(this.lblCombo);
        		
        		final CCombo combo = new CCombo(comboComposite, SWT.BORDER);
        		combo.setEditable(false);
        		combo.setFont(SWTResourceManager.getFont("Arial", 8, SWT.NORMAL));
        		//combo.setItems(new String[] {"Model", "Block", "Connector", "Record", "Type"});
        		combo.setItems(this.comboItems);
        		combo.setBounds(70, 0, 100, 20);
        		combo.select(0);
        		
        		// add the combo to the local list.
        		this.secondColValuesEdit.add(combo);
        		
        		
        		Button btnApplyToAll = new Button(comboComposite, SWT.NONE);
        		if (i != 0 ) { btnApplyToAll.setVisible(false); // only the first is visible }
        		
        		}
        		btnApplyToAll.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseDown(MouseEvent e) {
        				updateAllCombos(combo.getText());
        			}
        		});
        		btnApplyToAll.setBounds(175, 0, 50, 20);
        		btnApplyToAll.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        		btnApplyToAll.setText("set to all");
			}
		}

        // vertical spacer (only for layout)
        Composite spacer = new Composite(shell, SWT.NONE);
        //spacer.setBounds(0, 0, 10, 10);
        spacer.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        GridData gd_spacer = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        gd_spacer.widthHint = 20;
        gd_spacer.heightHint = 20;
        spacer.setLayoutData(gd_spacer);
        
        applyDialogFont(shell);
        return shell;
    }

    /**
     * Update all combos.
     *
     * @param text the text
     */
    private void updateAllCombos(String text) {
		for (CCombo combo : this.secondColValuesEdit) {
			combo.setText(text);
		}
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
	

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
	 */
	@Override
	public String isValid(String newText) {
		if (existingNames.contains(newText.trim())) {
			return "'" + newText + "' already exists.";
		}
		return null;
	}
}
