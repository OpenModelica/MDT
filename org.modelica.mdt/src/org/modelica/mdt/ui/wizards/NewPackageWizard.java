/*
 * This file is part of Modelica Development Tooling.
 *
 * Copyright (c) 2005, Link�pings universitet, Department of
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
 * * Neither the name of Link�pings universitet nor the names of its
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

import org.eclipse.core.resources.IResource;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.internal.core.ModelicaPackage;
import org.modelica.mdt.ui.ModelicaImages;

public class NewPackageWizard extends Wizard implements INewWizard
{
	private Text sourceFolder;
	private Text parentPackage;
	private Text packageName;
	private Text packageDesc;
	public Button isEncapsulated;
	
	

	private NewPackagePage packagePage = new NewPackagePage();

	public class NewPackagePage extends WizardPage
	{

		@SuppressWarnings("unused")
		private Object selection = null;

		public NewPackagePage()
		{
			super("");
		}

		public void createControl(Composite parent)
		{
			setPageComplete(false);
			/*
			 * configure description of this page
			 */
			setTitle("Modelica Package");
			setDescription("Create a new Modelica package.");
			
			setImageDescriptor(ModelicaImages.getImageDescriptor(
					             ModelicaImages.IMG_WIZBAN_PACKAGE));
			
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
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        					GridData.GRAB_HORIZONTAL);
	        sourceFolder.setLayoutData(gd);

	        
	        sourceFolder.addModifyListener(new ModifyListener()
	    	{
	    	      	/* check if entered path exists */
	    	       	public void modifyText(ModifyEvent e)
	    	       	{
	    	       		//sourceFolderChanged();
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
					//handleBrowse();
				}
			});

	        /* parent package field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Parent package:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        parentPackage = new Text(composite, SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        					GridData.GRAB_HORIZONTAL);
	        parentPackage.setLayoutData(gd);

	        
	        parentPackage.addModifyListener(new ModifyListener()
	    	{
	    	      	/* check if entered path exists */
	    	       	public void modifyText(ModifyEvent e)
	    	       	{
	    	       		//sourceFolderChanged();
	    	       	}
	    	});


	        b = new Button(composite, SWT.PUSH);
	        b.setText("Browse...");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.END;
	        b.setLayoutData(gd);
			b.addSelectionListener(new SelectionAdapter()
			{
				public void widgetSelected(SelectionEvent e)
				{
					//handleBrowse();
				}
			});
			
	        /* create separator */
	        l = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | 
	        				  GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 3;
	        l.setLayoutData(gd);
			
	        
	        /* package name field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Name:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        packageName = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        		          GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        packageName.setLayoutData(gd);

	        packageName.addModifyListener(new ModifyListener()
	        {
	        	/* check if entered package name is valid */
	        	public void modifyText(ModifyEvent e)
	        	{
	        	}
	        });
	        packageName.setFocus();

	        /* package description field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Description:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        packageDesc = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        		          GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        packageDesc.setLayoutData(gd);
	        
	        /* is encapsulated package field */
	        new Label(composite, SWT.NONE); /* empty label for padding */
	        isEncapsulated = new Button(composite, SWT.CHECK);
	        isEncapsulated.setText("is encapsulated package");
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        gd.horizontalSpan = 2;
	        isEncapsulated.setLayoutData(gd);
	        
	        setSelection();
		}

		private void setSelection() 
		{
			if (selection instanceof ModelicaPackage)
			{
				setParentPackage((ModelicaPackage)selection);
				System.out.println("filesystem package");
			}
			else if (selection instanceof IModelicaFolder || 
					 selection instanceof IModelicaClass  ||
					 selection instanceof IModelicaFile)
			{	
				System.out.println("modelica element " +
						(IModelicaElement)selection);
			}
			else if (selection instanceof IResource)
			{
				/* a file or a folder is selected */
				System.out.println("resource ");
			}
			/* ignore all other types of selections */
		}

		/**
		 * set parent package field in the dialog
		 * 
		 * @param parentPkg the parent package to set or null to set
		 * empty parent package 
		 */
		private void setParentPackage(ModelicaPackage parentPkg) 
		{
			if (parentPkg == null)
			{
				parentPackage.setText("");
			}
			else
			{
			//	parentPgk;
			}
		}

		public void init(IStructuredSelection selection)
		{
			if (selection == null || selection.size() != 1)
			{
				return;
			}
			
			/* 
			 * now we know that a single element is selected
			 */
			this.selection = selection.getFirstElement();
		}
		
		
		@SuppressWarnings("unused")
		private void updateStatus(String message, int messageType)
		{
			setMessage(message, messageType);
		}

	}


	public NewPackageWizard()
	{
		super();
	}

	
	@Override
	public boolean performFinish()
	{
		return true;
	}


	public void init(IWorkbench workbench, IStructuredSelection selection)
	{
		packagePage.init(selection);
		setWindowTitle("New Modelica Package");
	}

	@Override
	public void addPages()
	{
		addPage(packagePage);
	}
}

