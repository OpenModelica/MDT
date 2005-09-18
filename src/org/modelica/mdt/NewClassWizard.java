package org.modelica.mdt;


import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewClassWizard extends Wizard implements INewWizard
{
	public class NewClassPage extends WizardPage
	{
		public NewClassPage()
		{
			super("wee");
		}

		public void createControl(Composite parent)
		{
			initializeDialogUnits(parent);
			
			/*
			 * configure description of this page
			 */
			setTitle("Modelica Class");
			setDescription("Create a new Modelica class.");
			
			// TODO set image descriptor			
			//setImageDescriptor(...);
			
			/*
			 * setup widgets for this page
			 */
			Composite composite= new Composite(parent, SWT.NONE);
			setControl(composite);
			composite.setFont(parent.getFont());
			
	        GridLayout layout = new GridLayout();
	        layout.numColumns = 3;
	        composite.setLayout(layout);
	        
	        GridData gd;

	        /* source folder filed */
	        Label l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Source folder:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        Text t = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
	        t.setLayoutData(gd);

	        Button b = new Button(composite, SWT.PUSH);
	        b.setText("Browse...");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.END;
	        b.setLayoutData(gd);
	        
	        /* class name field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Name:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        t = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        t.setLayoutData(gd);

	        /* superclass field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Superclass:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        t = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        t.setLayoutData(gd);

	        /* type field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Type:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        Combo c = new Combo(composite, SWT.READ_ONLY);
	        c.setItems(new String [] {"class", "model", "connector", "record",
	        		"block", "type", "function"});
	        c.setVisibleItemCount(7);
	        c.select(0);
	        		
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        c.setLayoutData(gd);
	        
		}
		
	}

	private IStructuredSelection selection;
	
	public NewClassWizard()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean performFinish()
	{
		// TODO Auto-generated method stub
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		this.selection = selection;
		setWindowTitle("New Modelica Class");
	}

	@Override
	public void addPages()
	{
		NewClassPage page = new NewClassPage();
		addPage(page);
	}
}
