package org.openmodelica.modelicaml.view.componentstree.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;

public class ConfirmationWithCheckOptionDialog extends Dialog {
//	protected Shell shell;
	
	private String title = "Update Bindings Confirmation";
	private String text = "Shall all unambiguous bindings in the sub-tree of '\" + item.getName() +\"'.\" be updated? \r\nNote, this action cannot be undone.";
	private String checkOptionText = "Delete all bindings that are based on Value Mediators before updating.";
	private Button btnCheckOption;
	

	protected Button bttOK = null;
	protected Button bttCancel = null;

	private boolean deleteAllBindings = false;

	private Label lblImage;
	private Label lblText;
	
	public ConfirmationWithCheckOptionDialog( Shell parent, String title, String text, String checkOptionText) {
		super(parent);
		this.title = title;
		this.text = text;
		this.checkOptionText = checkOptionText;
		setShellStyle(getShellStyle());
	}
	
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText(title);
		newShell.setImage(SWTResourceManager.getImage(ConfirmationWithCheckOptionDialog.class, "/org/eclipse/jface/dialogs/images/help.gif")); 
		super.configureShell(newShell);
	}


	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			setDeleteAllBindings(btnCheckOption.getSelection());
		}
		super.buttonPressed(buttonId);
	}


	@Override
	protected Control createDialogArea(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBounds(0, 0 , 488, 172);
			
		lblImage = new Label(composite, SWT.NONE);
		lblImage.setImage(SWTResourceManager.getImage(ConfirmationWithCheckOptionDialog.class, "/org/eclipse/jface/dialogs/images/help.gif"));
		lblImage.setBounds(10, 10, 24, 24);

		lblText = new Label(composite, SWT.NONE);
		lblText.setBounds(45, 10, 500, 53);
		lblText.setText( text );
		
		btnCheckOption = new Button(composite, SWT.CHECK);
		btnCheckOption.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDeleteAllBindings(btnCheckOption.getSelection());
			}
		});
		btnCheckOption.setBounds(40, 69, 392, 16);
		btnCheckOption.setText( checkOptionText );

		return parent;
	}

	public void setDeleteAllBindings(boolean deleteAllBindings) {
		this.deleteAllBindings = deleteAllBindings;
	}

	public boolean deleteAllBindings() {
		return deleteAllBindings;
	}

		
}
