/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Linköpings universitet, Department of
 * Computer and Information Science, PELAB
 *
 * All rights reserved.
 *
 * (The new BSD license, see also
 * http://www.opensource.org/licenses/bsd-license.php)
 *
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * * Neither the name of Linköpings universitet nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.modelica.mdt.ui.wizards;


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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.ErrorDialog;
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
import org.eclipse.swt.events.SelectionListener;
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
import org.modelica.mdt.MdtPlugin;

public class NewClassWizard extends Wizard implements INewWizard
{
	/* key widgets' tags for abbot */
	public static final String SOURCE_FOLDER_TAG = "sourceFolderTag";
	public static final String CLASS_NAME_TAG = "classNameTag";
	public static final String CLASS_TYPE_TAG = "classTypeTag";
	public static final String INITIAL_EQUATION_TAG = "initEqTag";
	public static final String PARTIAL_CLASS_TAG = "partialTypeTag";
	public static final String EXTERNAL_BODY_TAG = "extBodyTag";
	
	public class NewClassPage extends WizardPage
	{
		private Text sourceFolder;
		private Text className;
		private Combo classType;
		private Button initialEquation;
		private Button partialClass;
		private Button externalBody;
	
		private IPath selection = null;
		
		
		/* regexp pattern of a valid modelica class name */
		//TODO add complete regexp for modelcia class name 
		// see modelica specification page 9 (and perhaps some other pages as
		// well)
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
	        
	        sourceFolder = new Text(composite, SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
	        sourceFolder.setLayoutData(gd);
	        MdtPlugin.tag(sourceFolder, SOURCE_FOLDER_TAG);
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
	        MdtPlugin.tag(className, CLASS_NAME_TAG);

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
	        classType.setItems(new String [] {"model", "class", "connector", 
	        		"record", "block", "type", "function"});
	        classType.setVisibleItemCount(7);
	        classType.select(0);	        
	        MdtPlugin.tag(classType, CLASS_TYPE_TAG);
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        classType.setLayoutData(gd);
	        
	        classType.addSelectionListener(new SelectionListener()
	        {

				public void widgetSelected(SelectionEvent e)
				{
					/*
					 * update state of the modifiers checkboxes
					 */
					initialEquation.setEnabled
					(NewClassWizard.classTypeHaveEquations
							(classType.getText()));
					
					partialClass.setEnabled
					(NewClassWizard.canBePartial(classType.getText()));
					
					externalBody.setEnabled
						(classType.getText().equals("function"));

				}

				public void widgetDefaultSelected(SelectionEvent e)
				{}
	        	
	        });
	        
	        /* fill upp 'empty' space */
	        new Label(composite, SWT.NONE);

	        
	        /* create separator */
	        l = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | 
	        				  GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 3;
	        l.setLayoutData(gd);
	        
	        
	        /* modifiers section */
	        l = new Label(composite, SWT.NONE);
	        l.setText("Modifiers:");
	        
	        /* initial equation block */
	        initialEquation = new Button(composite, SWT.CHECK);
	        initialEquation.setText("include initial equation block");
	        MdtPlugin.tag(initialEquation, INITIAL_EQUATION_TAG);
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        gd.horizontalSpan = 2;
	        initialEquation.setLayoutData(gd);
	        
	        /* partial class */
	        new Label(composite, SWT.NONE); /* empty label for padding */
	        partialClass = new Button(composite, SWT.CHECK);
	        partialClass.setText("is partial class");
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        gd.horizontalSpan = 2;
	        partialClass.setLayoutData(gd);
	        MdtPlugin.tag(partialClass, PARTIAL_CLASS_TAG);

	        /* external body */
	        new Label(composite, SWT.NONE); /* empty label for padding */
	        externalBody = new Button(composite, SWT.CHECK);
	        externalBody.setText("have external body");
	        externalBody.setEnabled(false);
	        MdtPlugin.tag(externalBody, EXTERNAL_BODY_TAG);
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        gd.horizontalSpan = 2;
	        externalBody.setLayoutData(gd);
	        

		}

		private void sourceFolderChanged()
		{
			IResource container = ResourcesPlugin.getWorkspace().getRoot()
					.findMember(new Path(sourceFolder.getText()));

			sourceFolderValid = false;
			if (sourceFolder.getText().length() == 0) {
				updateStatus("File container must be specified", 
						DialogPage.ERROR);
				return;
			}
			if (container == null ||
			    (container.getType() & 
			      (IResource.PROJECT | IResource.FOLDER)) == 0) 
			{
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

	/**
	 * @param classType name of the class type, e.g. 'record'
	 * @return false if class type can't have equations, e.g.
	 * false is returned for record
	 */
	public static boolean classTypeHaveEquations(String classType)
	{
		return 	
		!(classType.equals("record") 
				|| classType.equals("type")
				|| classType.equals("connector") 
				|| classType.equals("function"));
	}
	
	/**
	 * @param classType name of the class type, e.g. 'record'
	 * @return false if class type can't have equations, e.g.
	 * false is returned for type
	 */
	public static boolean canBePartial(String classType)
	{
		return 	
		(!(classType.equals("type") 
				|| classType.equals("function"))); 
	}

	
	@Override
	public boolean performFinish()
	{
		final String sourceFolder = classPage.sourceFolder.getText();
		final String className = classPage.className.getText();
		final String classType = classPage.classType.getText();
		final boolean initialEquationBlock = 
			classPage.initialEquation.getSelection();
		final boolean partialClass = 
			classPage.partialClass.getSelection();
		final boolean externalBody = 
			classPage.externalBody.getSelection();

		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) 
			                    throws InvocationTargetException
			 {
				try 
				{
					doFinish(sourceFolder, className, classType, 
							 initialEquationBlock, partialClass, externalBody,
							 monitor);
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
		try
		{
			getContainer().run(true, false, op);
		}
		catch (InterruptedException e)
		{
			return false;
		}
		catch (InvocationTargetException e)
		{
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", 
					realException.getMessage());
			return false;
		}
		return true;
	}

	protected void doFinish(String sourceFolder, String className, 
			String classType, boolean initialEquationBlock, 
			boolean partialClass, boolean haveExternalBody,
			IProgressMonitor monitor)
	throws CoreException
	{
		// create a sample file
		monitor.beginTask("Creating " + className, 2);
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(sourceFolder));
		
		IContainer container = (IContainer) resource;
		
		final IFile file = container.getFile(new Path(className+".mo"));
		
		try
		{
			String contents = generateClassContentes(className, classType, 
					initialEquationBlock, partialClass, haveExternalBody);
			InputStream stream = 
				new ByteArrayInputStream(contents.getBytes());
			if (file.exists())
			{
				file.appendContents(stream, true, true, monitor);
			}
			else
			{
				file.create(stream, true, monitor);
			}
			stream.close();
		} 
		catch (IOException e)
		{
			/* ignored */
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() 
		{
			public void run()
			{
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try 
				{
					IDE.openEditor(page, file, true);
				}
				catch (PartInitException e) 
				{
					ErrorDialog.openError(null, "Error", 
							"Could not open class filer in an editor.",(new Status(IStatus.ERROR, 
							MdtPlugin.getSymbolicName(), 0, 
							"error starting editor for " + file.getName(), e)));
				}
			}
		});
		monitor.worked(1);

	}

	private String generateClassContentes(String className, String classType, 
			boolean initialEquationBlock, boolean partialClass, boolean haveExternalBody) 
	{
		/*
		 * the gory logic of generating a skeleton for a modelica class
		 */
		String contents = "";

		if (canBePartial(classType) && partialClass)
		{
			contents += "partial ";
		}
		
		contents += classType + " " + className;
		
		if (classTypeHaveEquations(classType))
		{
			contents += "\n\nequation\n";
			if (initialEquationBlock)
			{
				contents += "\ninitial equation\n";
			}
		}
		
		if (classType.equals("function"))
		{
			if (haveExternalBody)
			{
				contents += "\nexternal";
			}
			else
			{	
				contents += "\nalgorithm\n";
			}
		}

		if (!classType.equals("type"))
		{
			contents += "\nend " + className;
		}

		contents += ";";
		return contents ;
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
