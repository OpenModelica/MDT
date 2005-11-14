package org.modelica.mdt.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ISystemLibrary;
import org.modelica.mdt.internal.omcproxy.InitializationException;

public class ProjectsViewDoubleClickAction extends Action 
{
	private TreeViewer viewer;
	
	ProjectsViewDoubleClickAction(TreeViewer viewer)
	{
		this.viewer = viewer;
	}

	@Override
	public void run()
	{
		/* get the object that was double clicked */
		ISelection selection = viewer.getSelection();
		Object obj = ((IStructuredSelection)selection).getFirstElement();
		
		/* open modelica files in an editor on double click */
		if (obj instanceof IModelicaFile)
		{
			try
			{
				openFile((IFile)((IModelicaFile)obj).getResource());
			} 
			catch (InitializationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* open files in an editor on double click */
		else if (obj instanceof IFile)
		{
			openFile((IFile)obj);
		}
		else if(obj instanceof IModelicaClass)
		{
			System.out.println("!");
			try
			{
				openFile((IFile)((IModelicaClass)obj).getResource());
			}
			catch (InitializationException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* expands a bunch of objects on double click */
		else if (obj instanceof IModelicaProject ||
				 obj instanceof IModelicaFolder  ||
				 obj instanceof IModelicaPackage ||
				 obj instanceof ISystemLibrary   ||
				 obj instanceof IProject         ||
				 obj instanceof IFolder          )
				  
		{
			expandElement(obj);
		}
	}

	/**
	 * expands element in the treeviewer, so that it's immediate children are
	 * visible
	 * @param element element to expand
	 */
	private void expandElement(Object element) 
	{
		viewer.expandToLevel(element, 1);
	}

	/**
	 * Opens file in the default editor
	 * param file file to open
	 */
	private void openFile(IFile file)
	{
	
		IWorkbenchPage page = PlatformUI.getWorkbench().
			getActiveWorkbenchWindow().getActivePage();

		if (page != null)
		{
		
			try
			{
			
				IDE.openEditor(page, file);
			} 
			catch (PartInitException e)  
			{
				// TODO proper error handling
				e.printStackTrace();
			}
		}

	}

}
