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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;

public class UpdateBindingsConfirmationDialog extends Dialog {

	private String title = "Update Bindings Confirmation";
//	private String text = "Shall all unambiguous bindings in the sub-tree of '\" + item.getName() +\"'.\" be updated? \r\nNote, this action cannot be undone.";
//	private String checkOptionText = "Delete bindings, that are based on Value Mediators, before updating.";
//	private Button btnCheckOption;
	
	private TreeObject rootTreeObject;
	
	protected Button bttOK = null;
	protected Button bttCancel = null;

	private boolean deleteAllBindings = false;
	private boolean automaticSelectionOfProviders = false;

	private Label lblImage;
	private Label lblText;
	
	public UpdateBindingsConfirmationDialog( Shell parent, TreeObject rootTreeObject ) {
		super(parent);
		this.rootTreeObject = rootTreeObject;
//		this.title = title;
//		this.text = text;
//		this.checkOptionText = checkOptionText;
		setShellStyle(getShellStyle());
	}
	
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(title);
		newShell.setImage(SWTResourceManager.getImage(UpdateBindingsConfirmationDialog.class, "/org/eclipse/jface/dialogs/images/help.gif")); 
		super.configureShell(newShell);
	}


//	@Override
//	protected void buttonPressed(int buttonId) {
//		if (buttonId == IDialogConstants.OK_ID) {
//			setDeleteAllBindings(btnCheckOption.getSelection());
//		}
//		super.buttonPressed(buttonId);
//	}


	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBounds(0, 0 , 520, 172);
		composite.setLayout(new GridLayout(2, false));
		
		// 1 raw
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		
		// 2 raw
		lblImage = new Label(composite, SWT.NONE);
		lblImage.setImage(SWTResourceManager.getImage(UpdateBindingsConfirmationDialog.class, "/org/eclipse/jface/dialogs/images/help.gif"));
		
		lblText = new Label(composite, SWT.NONE);
		lblText.setText( "Shall all unambiguous bindings in all sub-components of " +
						"'" + getDotPath(rootTreeObject) + "' be updated? " +
						"\r\nNote, this action cannot be undone." );
		
		// 3 raw
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		// 4 raw
		new Label(composite, SWT.NONE);
		
		final Button btnCheckOption = new Button(composite, SWT.CHECK);
		btnCheckOption.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDeleteAllBindings(btnCheckOption.getSelection());
			}
		});
		btnCheckOption.setText( "Delete bindings for which user selected the providers manually." );
		btnCheckOption.setToolTipText("If there are bindings available for a component " +
				"then it will be deleted regardless of it will " +
				"\nbe possible to derive binding code or not. " +
				"\n\nThis option is used in order to reset bindings " +
				"\nfor which the user had to make a manual selection." +
				"\n");
		
		
		// 5 raw
		new Label(composite, SWT.NONE);

		final Button btnAutomaticselectionofproviders = new Button(composite, SWT.CHECK);
		
		// preselect this option.
		btnAutomaticselectionofproviders.setSelection(true);
		setAutomaticSelectionOfProviders(true);
		
		btnAutomaticselectionofproviders.setToolTipText("If this option is selected, " +
				"then preferred providers will automatically be selected for clients. ");
		btnAutomaticselectionofproviders.setText("Enable automatic selection of preferred providers.");
		btnAutomaticselectionofproviders.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setAutomaticSelectionOfProviders(btnAutomaticselectionofproviders.getSelection());
			}
		});

		
		return parent;
	}
	
	private String getDotPath(TreeObject component){
		if (component.isRoot()) {
			return component.getName();
		}
		return component.getDotPath();
	}
	
	public void setDeleteAllBindings(boolean deleteAllBindings) {
		this.deleteAllBindings = deleteAllBindings;
	}

	public boolean deleteAllBindings() {
		return deleteAllBindings;
	}


	public void setAutomaticSelectionOfProviders(
			boolean automaticSelectionOfProviders) {
		this.automaticSelectionOfProviders = automaticSelectionOfProviders;
	}


	public boolean isAutomaticSelectionOfProviders() {
		return automaticSelectionOfProviders;
	}
}
