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
 * Main author: Wladimir Schamai, EADS Innovation Works / Linköping University, 2009-2013
 *
 * Contributors: 
 *   Uwe Pohlmann, University of Paderborn 2009-2010, contribution to the Modelica code generation for state machine behavior, contribution to Papyrus GUI adaptations
 */
package org.openmodelica.modelicaml.simulation.testexecution.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class OBSOLETE_DialogMessage extends Dialog {

	private StyledText text;
	private Label lblMessage;
	private String title = "";
	private String message = "";
	private String infoText = "";
	private boolean isError = false;
	
	public OBSOLETE_DialogMessage(Shell parentShell, String title, String infoText, String message, boolean isError) {
		super(parentShell);
		setBlockOnOpen(false);

//      setShellStyle(getShellStyle() | SWT.RESIZE | SWT.APPLICATION_MODAL);
		setShellStyle( SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL | SWT.SHELL_TRIM );
		
        this.title = title;
        this.infoText = infoText;
        this.message = message;
        this.isError = isError;
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
   		
   		if (this.isError) {
   	    	shell.setImage(shell.getDisplay().getSystemImage(SWT.ERROR_CANNOT_GET_ITEM));
		}
   		else {
   			shell.setImage(shell.getDisplay().getSystemImage(SWT.ICON_INFORMATION));
   		}
    }
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        if (this.infoText != null) {
            lblMessage = new Label(composite, SWT.NONE);
    		lblMessage.setBounds(10, 10, 380, 13);

    		lblMessage.setText(this.infoText);
		}

		text = new StyledText(composite, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.WRAP);
		text.setRightMargin(5);
		text.setTopMargin(5);
		text.setLeftMargin(5);
		text.setWrapIndent(10);
		text.setLayout(new GridLayout());
        GridData textGD = new GridData(SWT.FILL, SWT.FILL, true, true);
        textGD.heightHint = convertHorizontalDLUsToPixels(130);
        textGD.widthHint = convertHorizontalDLUsToPixels(400);
        text.setLayoutData(textGD);
        
		text.setText(this.message + "\n\n");	
        text.setEditable(false);
        
        return parent;
	}
}
