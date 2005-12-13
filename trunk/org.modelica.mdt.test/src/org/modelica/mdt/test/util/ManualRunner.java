package org.modelica.mdt.test.util;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.modelica.mdt.test.TestModelicaRoot;

/**
 * Debuggin aid. Runs some code in run() when an button on ze toolbar is clicked
 */
public class ManualRunner implements IWorkbenchWindowActionDelegate 
{

	class foo extends TestModelicaRoot
	{
		public void setUp() throws CoreException
		{
			super.setUp();
		}
	}
	
	public ManualRunner() 
	{
	}
	
	/**
	 * Put the code you want to run on click here!
	 */
	public void run(IAction action) 
	{
		foo Foo = new foo();
		
		try {
			Foo.setUp();
			Foo.testChangesToWorkspace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void selectionChanged(IAction action, ISelection selection) 
	{
	}

	public void dispose() 
	{
	}

	public void init(IWorkbenchWindow window) 
	{
	}
}