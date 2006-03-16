package org.modelica.mdt.test.util;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.modelica.mdt.test.TestModelicaCompletionProcessor;

/**
 * Debuggin aid. Runs some code in run() when an button on ze toolbar is clicked
 */
public class ManualRunner implements IWorkbenchWindowActionDelegate 
{

	public ManualRunner() 
	{
	}
	
	private class Foo extends TestModelicaCompletionProcessor
	{

		@Override
		public void setUp() throws Exception 
		{
			// TODO Auto-generated method stub
			super.setUp();
		}
	}
	
	/**
	 * Put the code you want to run on click here!
	 */
	public void run(IAction action) 
	{
		Foo foo = new Foo();
		
		try {
			foo.setUp();
			//foo.testComputeCompletionProposals();
		} catch (Exception e) {
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