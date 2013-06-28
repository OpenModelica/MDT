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

	private String title = "ModelicaML: Modelica Models Synchronization Options";
	
	protected Button bttOK = null;
	protected Button bttCancel = null;

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
		lblInfoMessage.setText("This helper will now (re)load the selected Modelica models " +
				"and translate them into ModelicaML model proxies. \n" +
				"Proxies only reflect the Modelica models from the 'code-sync' folder.\n" +
				"They should be re-synchronized whenever the Modelica models have been changed.\n\n");
	
		
		// 5 raw
		new Label(composite, SWT.NONE);

		final Button btnFullImport = new Button(composite, SWT.CHECK);
		
		btnFullImport.setSelection(false); 	// preselect this option.
		setFullImport(true);
		
		btnFullImport.setText("Sync. only the model structure (models, inheritance and components; no behavior, no connections, etc.)");
		btnFullImport.setToolTipText("Sync. only the model structure (classes, inheritance and components; NO behavior, NO connections, etc.)");
		btnFullImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// if selected then the full import is unset, else the opposite.
				setFullImport(!btnFullImport.getSelection());
			}
		});
		
		
		// 6 raw
		new Label(composite, SWT.NONE);

		final Button btnDeleteNotUsedProxies = new Button(composite, SWT.CHECK);
		
		// preselect this option.
		btnDeleteNotUsedProxies.setSelection(false);
		setDeleteNotUsedProxies(false);
		
		btnDeleteNotUsedProxies.setText("Delete proxies that do not exist anymore in the Modelica models (not recommded*)");
		btnDeleteNotUsedProxies.setToolTipText("* Note: If proxies are referenced by other elements then the references should be\n" +
				"redirected to new proxies before deleting old proxies.");
		
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
		info.setText("*Note: If proxies are referenced by other elements then the references should be\n" +
				"redirected to new proxies before deleting old proxies.");
		
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
