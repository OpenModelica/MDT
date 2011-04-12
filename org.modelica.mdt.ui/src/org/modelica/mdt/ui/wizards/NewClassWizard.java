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


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.ui.ModelicaImages;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.editor.EditorUtility;

public class NewClassWizard extends Wizard implements INewWizard
{
	/* key widgets' tags for abbot */
	public static final String CLASS_NAME_TAG = "classNameTag";
	public static final String CLASS_TYPE_TAG = "classTypeTag";
	public static final String INITIAL_EQUATION_TAG = "initEqTag";
	public static final String PARTIAL_CLASS_TAG = "partialTypeTag";
	public static final String EXTERNAL_BODY_TAG = "extBodyTag";
	

	private NewClassPage classPage = new NewClassPage();
	
	/* 
	 * the class type selection is saved so that next time
	 * this wizard is displayed, same selection is presented to the user
	 */ 
	public static int lastClassTypeSelected = 0; 
	
	public class NewClassPage extends NewTypePage
	{
		private Text className;
		private Combo classType;
		private Button initialEquation;
		private Button partialClass;
		private Button externalBody;
	
		private boolean classNameValid = false;
		
		public void createControl(Composite parent)
		{
			super.createControl(parent);
			setPageComplete(false);
			
			Composite composite = getControl();			
			/*
			 * configure description of this page
			 */
			setTitle("Modelica Class");
			setDescription("Create a new Modelica class.");
			
			setImageDescriptor(ModelicaImages.getImageDescriptor(
		             ModelicaImages.IMG_WIZBAN_CLASS));
			
			/*
			 * setup widgets for this page
			 */
	        GridData gd;
	        
	        /* class name field */
	        Label l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Name:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        className = new Text(composite,  SWT.SINGLE | SWT.BORDER);
	        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL |
	        				  GridData.GRAB_HORIZONTAL);
	        gd.horizontalSpan = 2;
	        className.setLayoutData(gd);
	        UIPlugin.tag(className, CLASS_NAME_TAG);

	        className.addModifyListener(new ModifyListener()
	        {
	        	/* check if entered classname is valid */
	        	public void modifyText(ModifyEvent e)
	        	{
	        		if (ModelicaCore.isLegalIdentifierName(className.getText()))
	        		{
	        			classNameValid  = true;
	        			setErrorMessage(null);
	        			setPageComplete(isPageComplete());
	        		}
	        		else
	        		{
	        			classNameValid  = false;
	        			setErrorMessage
	        				("Class name is not valid. Illegal identifier.");
	        			setPageComplete(isPageComplete());
	        		}
	        	}
	        });
	        className.setFocus();

	        /* class type field */
	        l = new Label(composite, SWT.LEFT | SWT.WRAP);
	        l.setText("Restriction:");
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        l.setLayoutData(gd);
	        
	        classType = new Combo(composite, SWT.READ_ONLY);
	        classType.setItems(new String [] {"model", "class", "connector", "record", "block", "type", "function", "package", "operator"});
	        classType.setVisibleItemCount(7);
	        UIPlugin.tag(classType, CLASS_TYPE_TAG);
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        classType.setLayoutData(gd);
	        
	        classType.addSelectionListener(new SelectionListener()
	        {

				public void widgetSelected(SelectionEvent e)
				{
					restrictionChanged();
	        		lastClassTypeSelected = classType.getSelectionIndex();
				}

				public void widgetDefaultSelected(SelectionEvent e)	{}
	        	
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
	        UIPlugin.tag(initialEquation, INITIAL_EQUATION_TAG);
	        
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
	        UIPlugin.tag(partialClass, PARTIAL_CLASS_TAG);

	        /* external body */
	        new Label(composite, SWT.NONE); /* empty label for padding */
	        externalBody = new Button(composite, SWT.CHECK);
	        externalBody.setText("have external body");
	        externalBody.setEnabled(false);
	        UIPlugin.tag(externalBody, EXTERNAL_BODY_TAG);
	        
	        gd = new GridData();
	        gd.horizontalAlignment = GridData.BEGINNING;
	        gd.horizontalSpan = 2;
	        externalBody.setLayoutData(gd);

	        /*
	         * set class type to the same selection as it was last
	         * time dialog was used
	         * 
	         * this must be done after initialEquation, partialClass
	         * and externalBody widgets are created becouse class type selection
	         * affects thiers status (can't set status on unexsiting widgets)
	         */
	        classType.select(lastClassTypeSelected);
	        restrictionChanged();
		}

		/**
		 * updates the internal state of the wizard when class type
		 * is changed in the combo.
		 * 
		 * This method should be called whenever changes are made to the
		 * combo's selection.
		 */
		private void restrictionChanged()
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

		@Override
		public boolean isPageComplete()
		{
			return super.isPageComplete() && classNameValid;
		}

	}


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
				|| classType.equals("function")
				|| classType.equals("package")
				|| classType.equals("operator"));
	}
	
	/**
	 * @param classType name of the class type, e.g. 'record'
	 * @return false if class type can't have equations, e.g.
	 * false is returned for type
	 */
	public static boolean canBePartial(String classType)
	{
		return 	
		(!(classType.equals("type") || classType.equals("function"))); 
	}

	
	@Override
	public boolean performFinish()
	{
		final String sourceFolder = classPage.getSourceFolder();
		final String parentPackage = classPage.getParentPackge();
		final String className = classPage.className.getText();
		final String classType = classPage.classType.getText();
		final boolean initialEquationBlock = 
			classPage.initialEquation.getSelection();
		final boolean partialClass = 
			classPage.partialClass.getSelection();
		final boolean externalBody = 
			classPage.externalBody.getSelection();

		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException
			 {
				try 
				{
					doFinish(sourceFolder, parentPackage, className, classType, 
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

	protected void doFinish(String sourceFolder, String parentPackage,
			String className, String classType, boolean initialEquationBlock, 
			boolean partialClass, boolean haveExternalBody,
			IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask("Creating " + className, 2);
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(sourceFolder));
		
		IContainer container = (IContainer) resource;
		
		final IFile file = container.getFile(new Path(className+".mo"));
		
		try
		{
			String contents = generateClassContents(className, parentPackage,
					classType, initialEquationBlock, partialClass, 
					haveExternalBody);
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
				try 
				{
					/*
					 * find the modelica class reference object
					 * and open it in the editor
					 */
					IModelicaProject proj = 
						ModelicaCore.getModelicaRoot().getProject(file.getProject().
								getName());
					EditorUtility.openInEditor
						(proj.findElement(file.getProjectRelativePath()));
				}
				catch (PartInitException e) 
				{
					ErrorDialog.openError(null, "Error", 
							"Could not open class file in an editor.",
							(new Status(IStatus.ERROR,
									CorePlugin.getSymbolicName(), 0, 
									"error starting editor for " + 
									file.getName(), e)));
				}
				catch (CompilerException e)
				{
					ErrorManager.showCompilerError(e);
				}
				catch (CoreException e)
				{
					ErrorManager.showCoreError(e);
				}

			}
		});
		monitor.worked(1);

	}

	private String generateClassContents(String className, String parentPackage,
			String classType, boolean initialEquationBlock, 
			boolean partialClass, boolean haveExternalBody) 
	{
		/*
		 * the gory logic of generating a skeleton for a modelica class
		 */
		String contents = "";
		
		if (!parentPackage.equals(""))
		{
			/* generate within clause if we are not top level package */
			contents += "within " + parentPackage + ";\n\n";
		}

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
		else
		{
			contents += "\n";
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
