package org.openmodelica.modelicaml.view.valuebindings.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.openmodelica.modelicaml.common.contentassist.ModelicaMLContentAssist;
import org.openmodelica.modelicaml.common.instantiation.TreeObject;
import org.openmodelica.modelicaml.common.services.StringUtls;
import org.openmodelica.modelicaml.common.utls.ResourceManager;
import org.openmodelica.modelicaml.common.utls.SWTResourceManager;
import org.openmodelica.modelicaml.editor.xtext.modification.ui.internal.ModificationActivator;
import org.openmodelica.modelicaml.tabbedproperties.editors.glue.edit.part.PropertiesSectionXtextEditorHelper;
import org.openmodelica.modelicaml.view.valuebindings.Activator;

import com.google.inject.Injector;

public class DialogDerivedCode extends Dialog {

	private StyledText text;
	private Label lblMessage;
	private String string = null;
	public static int CODE = 0;
	public final static int ERROR_MESSAGE = 1;
	private int mode = ERROR_MESSAGE;
	
	private Injector injector;
	private String fileExtension;
	private PropertiesSectionXtextEditorHelper editor;
	private TreeObject treeObject;
	
	public DialogDerivedCode(Shell parentShell, String string, int mode, TreeObject treeObject) {
		super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE | SWT.APPLICATION_MODAL);
		this.mode = mode;
		this.treeObject = treeObject;
        this.string = string;
	}
	
	protected void configureShell(Shell shell) {
        super.configureShell(shell);
    	if (mode == CODE) {
    		shell.setText("Derived Code");
    		shell.setImage(ResourceManager.getPluginImage("org.openmodelica.modelicaml.view.valuebindings", "/icons/valueMediator.png"));

		}
    	else if (mode == ERROR_MESSAGE) {
    		shell.setText("Validation results");
    		shell.setImage(SWTResourceManager.getImage(Activator.class, "/org/eclipse/jface/dialogs/images/message_error.gif"));
		}
    }
	
	@Override
	protected void okPressed() {
		if (mode == CODE) {
			string = editor.getText();
		}
		super.okPressed();
	}

	public String getString(){
		return string;
	}
	
	public String getCode(){
		if (mode == CODE) {
			return string;	
		}
		return null;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        lblMessage = new Label(composite, SWT.NONE);
		lblMessage.setBounds(10, 10, 380, 13);

		if (mode == CODE) {
			lblMessage.setText("The following code was derived based on linked Value Mediators and Providers: ");			

			Composite editorComposite = new Composite(composite, SWT.BORDER);
	        editorComposite.setLayout(new GridLayout());
	        GridData editorCompositeGD = new GridData(SWT.FILL, SWT.FILL, true, true);
	        editorCompositeGD.heightHint = convertHorizontalDLUsToPixels(130);
	        //editorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	        editorComposite.setLayoutData(editorCompositeGD);
    		injector = ModificationActivator.getInstance().getInjector("org.openmodelica.modelicaml.editor.xtext.modification.Modification");
    		fileExtension = "" + ".modelicamlmodification"; 
    		editor = new PropertiesSectionXtextEditorHelper(treeObject.getFirstLevelComponent(), injector, null, "No text yet ...", fileExtension);

    		// set code completion and marker support data
    		ModelicaMLContentAssist.setSelectedSourceElement(treeObject.getFirstLevelComponent()); 
    		
    		// for modification: this is used to obtain the list of the modified component attributes.
    		if (treeObject.getFirstLevelComponent() instanceof Property) {
    			ModelicaMLContentAssist.setPropertyName( StringUtls.replaceSpecChar( ((Property)treeObject.getFirstLevelComponent()).getName()) );
    			Element owningClass = treeObject.getFirstLevelComponent().getOwner(); 
    			if (owningClass instanceof Class) { // components of the owner of the property being modified
    				ModelicaMLContentAssist.createComponentReferencelist((Class)owningClass);
    			}
    		}
    		
    		editor.showEditor(editorComposite, SWT.BORDER);
    		editor.setTextToEdit(this.string);
    		
		} 
		else if (mode == ERROR_MESSAGE) {
			lblMessage.setText("The following inconsistancies are detected: ");

			text = new StyledText(composite, SWT.BORDER | SWT.V_SCROLL);
			text.setLayout(new GridLayout());
	        GridData textGD = new GridData(SWT.FILL, SWT.FILL, true, true);
	        textGD.heightHint = convertHorizontalDLUsToPixels(130);
	        text.setLayoutData(textGD);
	        
			text.setText(this.string + "\n\n");	
	        text.setEditable(false);
		}
        
        return parent;
	}
}
