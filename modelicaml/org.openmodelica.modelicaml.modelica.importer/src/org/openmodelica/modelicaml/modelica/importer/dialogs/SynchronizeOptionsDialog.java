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
package org.openmodelica.modelicaml.modelica.importer.dialogs;

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
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.modelica.importer.Activator;

public class SynchronizeOptionsDialog extends Dialog {

	private String title = "ModelicaML Proxies Synchronization Options";
	
	protected Button bttOK = null;
	protected Button bttCancel = null;

	private Label lblImage;
	private Label lblText;
	
	boolean applyProxyStereotype = true;



	boolean update = true;
	boolean deleteNotUsedProxies = false;
	boolean fullImport = true;
	
	public SynchronizeOptionsDialog( Shell parent ) {
		super(parent);
		setShellStyle(getShellStyle());
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);

		newShell.setText(title);
		newShell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/code2.png")); 
		newShell.setImage(SWTResourceManager.getImage(Activator.class, "/icons/copy_edit.gif"));
		
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBounds(0, 0 , 520, 172);
		composite.setLayout(new GridLayout(2, false));
		
		// 1 raw
		new Label(composite, SWT.NONE);
		
		Label lblInfoMessage = new Label(composite, SWT.NONE);
		lblInfoMessage.setText("This helper will now (re)load the Modelica models that are in the 'code-sync' folder \r\nand translate them into ModelicaML Modelica model proxies. Note that proxies only \r\nrepresent the Modelica models from the 'code-sync' folder. ModelicaML proxies should \r\nbe synchronized whenever the Modelica models have been changed. \r\n\r\nThe translation time depends on the size of the models. \r\nIt may take few seconds up to few hours. Do you want to proceed? \r\n\r\n");
		
//		// 2 raw
//		lblImage = new Label(composite, SWT.NONE);
//		lblImage.setImage(SWTResourceManager.getImage(SynchronizeOptionsDialog.class, "/org/eclipse/jface/dialogs/images/help.gif"));
//		
//		lblText = new Label(composite, SWT.NONE);
//		lblText.setText( "Select ModelicaML proxies synchronization options: " );
		
//		// 3 raw
//		new Label(composite, SWT.NONE);
//		new Label(composite, SWT.NONE);
//
//		
		//***************** Buttons 
		
//		// 4 raw
//		new Label(composite, SWT.NONE);
//		
//		final Button btnApplyProxyStereotype = new Button(composite, SWT.CHECK);
//		btnApplyProxyStereotype.setSelection(true);
//		btnApplyProxyStereotype.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				setApplyProxyStereotype(btnApplyProxyStereotype.getSelection());
//			}
//		});
//		btnApplyProxyStereotype.setText( "Apply proxy stereotype to ModelicaML proxy elements (recommended)." );
//		btnApplyProxyStereotype.setToolTipText("A proxy stereotype indicates that the a ModelicaML element is not complete. " +
//				"A proxy repersents a Modelica model and reflects only information required for the usage of the represented Modelica model." +
//				"No code is generated from a proxy.");
//		
//		
//		// 5 raw
//		new Label(composite, SWT.NONE);
//
//		final Button btnUpdate = new Button(composite, SWT.CHECK);
//		
//		btnUpdate.setSelection(true); 	// preselect this option.
//		setUpdate(true);
//		
//		btnUpdate.setText("Update existing proxy elements (recommended).");
//		btnUpdate.setToolTipText("All data of the exising proxies will be updated.");
//		btnUpdate.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				setUpdate(btnUpdate.getSelection());
//			}
//		});

		
		
//		// 5 raw
//		new Label(composite, SWT.NONE);
//
//		final Button btnFullImport = new Button(composite, SWT.CHECK);
//		
//		btnFullImport.setSelection(true); 	// preselect this option.
//		setFullImport(true);
//		
//		btnFullImport.setText("Import all model data (including behavior, imports, comments and annotations)");
//		btnFullImport.setToolTipText("Import all model data (including behavior, imports, comments and annotations)");
//		btnFullImport.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				setFullImport(btnFullImport.getSelection());
//			}
//		});
		
		
		// 6 raw
		new Label(composite, SWT.NONE);

		final Button btnDeleteNotUsedProxies = new Button(composite, SWT.CHECK);
		
		// preselect this option.
		btnDeleteNotUsedProxies.setSelection(false);
		setDeleteNotUsedProxies(false);
		
		btnDeleteNotUsedProxies.setText("Delete proxies that do not exist in the loaded Modelica models (not recommded)");
		btnDeleteNotUsedProxies.setToolTipText("If proxies are referenced by other elements then the references should " +
				"be redirected to new proxies before deleting the old proxies.");
		
//		btnDeleteNotUsedProxies.setText("Delete proxies that do not exist in the loaded Modelica models (NOT RECOMMENDED)." +
//				"\n\rNOTE: If existing proxies are referenced by other elements " +
//				"\n\r(e.g. they are used as types or in value bindings) then the references should be" +
//				"\n\rredirected to new proxies before deleting the old proxies.");
//		btnDeleteNotUsedProxies.setToolTipText("If proxies are referenced by other elements then the references should " +
//				"be redirected to new proxies before deleting the old proxies.");
		
		btnDeleteNotUsedProxies.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDeleteNotUsedProxies(btnDeleteNotUsedProxies.getSelection());
			}
		});
		
		// 7 raw
		new Label(composite, SWT.NONE);
		Label info = new Label(composite, SWT.NONE);
		info.setImage(SWTResourceManager.getImage(Activator.class, "/icons/message_warning.gif"));
		info.setText("If Modelica model proxies are referenced by other elements then the references should be \r\nredirected to new proxies before deleting the old proxies.");
		
		return parent;
	}
	
	
	public boolean isApplyProxyStereotype() {
		return applyProxyStereotype;
	}


	public void setApplyProxyStereotype(boolean applyProxyStereotype) {
		this.applyProxyStereotype = applyProxyStereotype;
	}


	public boolean isUpdate() {
		return update;
	}


	public void setUpdate(boolean update) {
		this.update = update;
	}


	public boolean isDeleteNotUsedProxies() {
		return deleteNotUsedProxies;
	}


	public void setDeleteNotUsedProxies(boolean deleteNotUsedProxies) {
		this.deleteNotUsedProxies = deleteNotUsedProxies;
	}
	
	public boolean isFullImport() {
		return fullImport;
	}

	public void setFullImport(boolean fullImport) {
		this.fullImport = fullImport;
	}
	
}
