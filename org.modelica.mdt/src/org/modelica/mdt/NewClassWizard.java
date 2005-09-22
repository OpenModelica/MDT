package org.modelica.mdt;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.ide.IDE;

public class NewClassWizard extends Wizard implements INewWizard
{
	public class NewClassPage extends WizardPage
	{
		private Text sourceFolder;
		private Text className;
		private Combo classType;
		
	
		private IPath selection = null;
		
		
		/* regexp pattern of a valid modelica class name */
		//TODO add complete regexp for modelcia class name 
		// see modelica specification page 9 (and perhaps some other pages as well)
		// http://www.modelica.org/documents/ModelicaSpec22.pdf
        Pattern classNamePattern = Pattern.compile("[a-zA-Z]\\w*");
        
		private boolean classNameValid = false;
		private boolean sourceFolderValid = false;		
		
		public NewClassPage()
		{
			super("");
		}

		public void createControl(Composite parent)
		{
			setPageComplete(false);
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

	        /* source folder field */
	        Label l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Source folder:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        sourceFolder = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
	        sourceFolder.setLayoutData(gd);
	        setSourceFolder(selection);
	        
	        sourceFolder.addModifyListener(new ModifyListener()
	    	{
	    	      	/* check if entered path exists */
	    	       	public void modifyText(ModifyEvent e)
	    	       	{
	    	       		sourceFolderChanged();
	    	       	}
	    	});


	        Button b = new Button(composite, SWT.PUSH);
	        b.setText("Browse...");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.END;
	        b.setLayoutData(gd);
			b.addSelectionListener(new SelectionAdapter()
			{
				public void widgetSelected(SelectionEvent e)
				{
					handleBrowse();
				}
			});

	        
	        /* class name field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Name:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        className = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        className.setLayoutData(gd);

	        className.addModifyListener(new ModifyListener()
	        {
	        	/* check if entered classname is valid */
	        	public void modifyText(ModifyEvent e)
	        	{
	        		if (!isLegalClassName(className.getText()))
	        		{
	        			classNameValid  = false;
	        			updateStatus("Class name is not valid. Illegal identifier.",
	        						DialogPage.WARNING);
	        		}
	        		else
	        		{
	        			classNameValid  = true;
	        			updateStatus(null, DialogPage.NONE);
	        		}
	        	}
	        });
	        className.setFocus();

	        /* class type field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Type:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        classType = new Combo(composite, SWT.READ_ONLY);
	        classType.setItems(new String [] {"class", "model", "connector", "record",
	        		"block", "type", "function"});
	        classType.setVisibleItemCount(7);
	        classType.select(0);
	        		
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        classType.setLayoutData(gd);

		}

		private void sourceFolderChanged()
		{
			IResource container = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(new Path(sourceFolder.getText()));

			sourceFolderValid = false;
			if (sourceFolder.getText().length() == 0) {
				updateStatus("File container must be specified", DialogPage.ERROR);
				return;
			}
			if (container == null
					|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
				updateStatus("File container must exist", DialogPage.ERROR);
				return;
			}
			if (!container.isAccessible()) {
				updateStatus("Project must be writable", DialogPage.ERROR);
				return;
			}
			sourceFolderValid = true;
			updateStatus(null, DialogPage.NONE);
		}

		/**
		 * @return true if name is a valid modelica class name
		 */
		protected boolean isLegalClassName(String name)
		{
			return classNamePattern.matcher(name).matches();
		}

		public void init(IStructuredSelection selection)
		{
			if (selection == null || selection.size() != 1)
			{
				return;
			}
			if (!(selection.getFirstElement() instanceof IResource))
			{
				return;
			}
			
			/* 
			 * now we know that a single element of type IResource is selected
			 */
			IResource sel = (IResource) selection.getFirstElement();

			this.selection = sel.getFullPath();
			if (sel.getType() == IResource.FILE)
			{
				this.selection = this.selection.removeLastSegments(1);
			}
		}
		
		private void handleBrowse()
		{
			ContainerSelectionDialog dialog =
				new ContainerSelectionDialog(
					getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
					"Choose a source folder:");
			
			if (dialog.open() == ContainerSelectionDialog.OK)
			{
				Object[] result = dialog.getResult();
				if (result.length == 1)
				{
					setSourceFolder((IPath) result[0]);
				}
			}
		}
		
		private void updateStatus(String message, int messageType)
		{
			setMessage(message, messageType);
			setPageComplete((classNameValid && sourceFolderValid));
		}


		public void setSourceFolder(IPath path)
		{
	        if (path != null)
	        {	        	
	        	sourceFolder.setText(path.makeRelative().toString());
	        	setPageComplete(isPageComplete());
	        	sourceFolderValid = true;
	        }	        			
		}

	}


	private NewClassPage classPage = new NewClassPage();

	public NewClassWizard()
	{
		super();
	}

	@Override
	public boolean performFinish()
	{
		final String sourceFolder = classPage.sourceFolder.getText();
		final String className = classPage.className.getText();
		final String classType = classPage.classType.getText();
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) 
			                    throws InvocationTargetException
			 {
				try 
				{
					doFinish(sourceFolder, className, classType, monitor);
				} 
				catch (CoreException e)
				{
					throw new InvocationTargetException(e);
				}
				finally 
				{
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	protected void doFinish(String sourceFolder, String className, 
			String classType, IProgressMonitor monitor)
	throws CoreException
	{
		// create a sample file
		monitor.beginTask("Creating " + className, 2);
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(sourceFolder));
		
		IContainer container = (IContainer) resource;
		
		final IFile file = container.getFile(new Path(className+".mo"));
		String contents = 
			classType + " " + className + "\n\n" +
			(classType.equals("record") ? "" : "equation\n\n" ) + 
			"end " + className + ";";
		
		try {
			InputStream stream = new ByteArrayInputStream(contents.getBytes());
			if (file.exists()) {
				file.appendContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);

	}

	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		classPage.init(selection);
		setWindowTitle("New Modelica Class");
	}

	@Override
	public void addPages()
	{
		addPage(classPage);
	}
}
