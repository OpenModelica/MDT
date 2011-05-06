package org.openmodelica.modelicaml.view.componentstree.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class DialogMessage extends Dialog {

	private StyledText text;
	private Label lblMessage;
	private String title = "";
	private String message = "";
	private String infoText = "";
	
	public DialogMessage(Shell parentShell,String title, String infoText, String message) {
		super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.APPLICATION_MODAL);
        this.title = title;
        this.infoText = infoText;
        this.message = message;
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
   		shell.setText(this.title);
//    	shell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png"));
    }
	
//	@Override
//	protected void okPressed() {
//		super.okPressed();
//	}

	
	@Override
	protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        lblMessage = new Label(composite, SWT.NONE);
		lblMessage.setBounds(10, 10, 380, 13);

		lblMessage.setText(this.infoText);

		text = new StyledText(composite, SWT.BORDER | SWT.V_SCROLL);
		text.setLayout(new GridLayout());
        GridData textGD = new GridData(SWT.FILL, SWT.FILL, true, true);
        textGD.heightHint = convertHorizontalDLUsToPixels(130);
        text.setLayoutData(textGD);
        
		text.setText(this.message + "\n\n");	
        text.setEditable(false);
        
        return parent;
	}
}
