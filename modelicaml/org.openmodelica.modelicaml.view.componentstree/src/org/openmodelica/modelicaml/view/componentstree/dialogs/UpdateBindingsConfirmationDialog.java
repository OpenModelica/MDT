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
		lblText.setText( "Shall all unambiguous bindings in " +
						"the sub-tree of '" + rootTreeObject.getDotPath() + "'.\" be updated? " +
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
		btnCheckOption.setText( "Delete bindings for which the user selected the providers." );
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
