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

package org.modelica.mdt.test.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Semaphore;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;

import abbot.finder.matchers.swt.TextMatcher;
import abbot.finder.swt.BasicFinder;
import abbot.finder.swt.Matcher;
import abbot.finder.swt.MultipleWidgetsFoundException;
import abbot.finder.swt.TestHierarchy;
import abbot.finder.swt.WidgetNotFoundException;

/**
 * @author Elmir Jagudin
 *
 * This class contains some utility code for assisting the testcases
 */
public class Utility 
{

	/**
	 * creates and opens a wizard
	 *  
	 * @param wizardID the ID of the wizard to create and open
	 * @param selection the selection which is used to initialize the wizard
	 */
	public static IWorkbenchWizard openWizard(String wizardID, 
			IStructuredSelection selection)
	{
		
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWizardDescriptor wizDesc = 
			workbench.getNewWizardRegistry().findWizard(wizardID);	
		Assert.assertNotNull("wizard " + wizardID + " not found", wizDesc);
		
		IWorkbenchWizard wizard = null;
		try
		{
			wizard = wizDesc.createWizard();
		}
		catch (CoreException e)
		{
			Assert.fail("Could not create " + wizardID + 
					" wizard, CoreException thrown\n" + e.getMessage());
		}
		Assert.assertNotNull(wizard);
		
		wizard.init(workbench, selection);
		final WizardDialog dialog = 
			new WizardDialog(workbench.getActiveWorkbenchWindow().getShell(), wizard);
		dialog.create();

		final Semaphore sem = new Semaphore(0);
		
		dialog.getShell().getDisplay().syncExec(new Runnable()
		{
			public void run()
			{
				dialog.setBlockOnOpen(false);
				dialog.open();
				sem.release();
			}
		});
		
		try
		{
			sem.acquire();
		} 
		catch (InterruptedException e)
		{
			Assert.fail("interruped while waiting for dialog to open");
		}
		
		return wizard;
	}
	/**
	 * creates and opens a wizard initialized with empty selection
	 *  
	 * @param wizardID the ID of the wizard to create and open
	 */
	public static IWorkbenchWizard openWizard(String wizardID)
	{
		return openWizard(wizardID, StructuredSelection.EMPTY);
	}
	
	/**
	 * 
	 * @return currently displayed button with text '&Finish'
	 * this function will fail if not exactly one finish button is
	 * on the screen
	 */
	public static Button findButtonByText(String buttonText)
	{
		BasicFinder finder =  /* find finish button */
			new BasicFinder(new TestHierarchy(PlatformUI.getWorkbench().getDisplay()));

		try
		{
			return (Button) finder.find(new TextMatcher(buttonText));
		}
		catch (WidgetNotFoundException e)
		{
			Assert.fail("Finish button not found.");
		} 
		catch (MultipleWidgetsFoundException e) 
		{
			Assert.fail("Multiple finish buttons found.");
		}
		
		Assert.fail("this is not happening");
		return null;
	}
	/*
	 * sleeps for approx time seconds
	 * this method does not garantee that it will sleep any particular time
	 */
	public static void sleep(Object mutex, long time)
	{
		try 
		{
			synchronized (mutex)
			{
				mutex.wait(time);
			}
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Tries to find a project by name, this method fails (Assert.fail)
	 * if a modelica project by that name is not found. This method also
	 *  fails if any exception is thrown while searching for the project.
	 * 
	 * @param name name of the project to find
	 * @return the modelica project handle named name
	 */
	public static IModelicaProject getProject(String name)
	{
		for (Object proj : ModelicaCore.getModelicaRoot().getProjects())
		{
			if (proj instanceof IModelicaProject)
			{
				if (((IModelicaProject)proj).getElementName().equals(name))
				{
					return (IModelicaProject)proj;
				}
			}
		}
		
		Assert.fail("No modelica project named '" + name + "' found.");
		return null; /* this is not happening */
	}
	
	public static Widget getInstrumentedWidget(final String tag)
	{
		/*
		 * find classType combo by tag
		 */
		Widget widget;
		BasicFinder finder = new BasicFinder(new TestHierarchy
				(PlatformUI.getWorkbench().getDisplay()));
		
		try 
		{			
			widget = finder.find(new Matcher()
			{
				public boolean matches(Widget w) 
			    {
					Object widgetTag = w.getData("name");

			        if (widgetTag == null || !(widgetTag instanceof String))
			        {
			        	return false;
			        }
			        return ((String)widgetTag).equals(tag);
			    }
			                
			});
			
			return widget;
		} 
		catch (WidgetNotFoundException e) 
		{
			/* fall through */
		} 
		catch (MultipleWidgetsFoundException e) 
		{
			/* fall through */
		}
		
		/* exception thrown, no/more than one widgets found */
		return null;
	}
	
	/**
	 * Compares the content of a file with a provided string
	 * @param file
	 * @param expectedContent
	 * @return true if file's content exactly matches the expectedContent 
	 * string
	 */
	public static boolean compareContent(IFile file, String expectedContent)
	{
		InputStream fileContent = null;
		
		try
		{
			fileContent = file.getContents();
		}
		catch (CoreException e) 
		{
			Assert.fail("could not fetch contents of the created class");
		}

		byte[] buf = new byte[expectedContent.length()];
		
		try
		{
			fileContent.read(buf);
			int i = fileContent.read();

			if (-1 != i)
			{
				Assert.fail("file is to longer than expected");
			}
		}
		catch (IOException e)
		{
			Assert.fail("could not read contents of the file");
		}
		return expectedContent.equals(new String(buf));
	}
	
	/**
	 * convinience method to 'convert' a string into a InputStream.
	 * 
	 * @return an input stream that have the same content as the provided string
	 */
	public static InputStream getByteStream(String content) 
	{
		return new ByteArrayInputStream(content.getBytes());
	}
	
	/**
	 * Convinience method to find a modelica file in a modelica folder.
	 * If the file is not found, then this method will fail 
	 * (e.g. call Assert.fail()) the invocing test. 
	 * 
	 * @param folder the folder where to look
	 * @param fileName the name of the file to look for
	 * 
	 * @return the modelica file found 
	 * @throws CoreException 
	 * @throws InvocationError 
	 * @throws UnexpectedReplyException 
	 * @throws ConnectException 
	 * @throws CompilerInstantiationException 
	 */
	public static IModelicaSourceFile findModelicaFileInFolder(IModelicaFolder folder, 
			String fileName) 
	throws ConnectException, UnexpectedReplyException, 
		InvocationError, CoreException, CompilerInstantiationException 
	{
		for (Object child : folder.getChildren())
		{
				if (child instanceof IModelicaSourceFile)
				{
					IModelicaSourceFile f = (IModelicaSourceFile) child;
					if (f.getElementName().equals(fileName))
					{
						return f;
					}
				}
		}
		
		Assert.fail("No modelica file named '" + fileName + 
				"' found in folder '" + folder.getElementName() + "'");  
		return null; /* this is not happening */

	}
	
	public static void listAllProjects() {
		IModelicaRoot modelicaRoot = ModelicaCore.getModelicaRoot();
		
		IModelicaProject[] allProjects = modelicaRoot.getProjects();
		
		for (IModelicaProject modelicaProject : allProjects) {
			String name = modelicaProject.getElementName();
			
			System.out.println(name);
		}
	}
}
