package org.modelica.mdt.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.ide.IDE;
import org.modelica.mdt.MdtPlugin;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaFile;
import org.modelica.mdt.core.IModelicaFolder;
import org.modelica.mdt.core.IModelicaPackage;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.ISystemLibrary;
import org.modelica.mdt.editor.ModelicaFileEditorInput;
import org.modelica.mdt.internal.omcproxy.CompilerException;

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
				openInternalFile((IFile)((IModelicaFile)obj).getResource());
			} 
			catch (CompilerException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* open files in an editor on double click */
		else if (obj instanceof IFile)
		{
			openInternalFile((IFile)obj);
		}
		else if(obj instanceof IModelicaClass)
		{
			try
			{
				openExternalFile(((IModelicaClass)obj).getFile());
			}
			catch (CompilerException e)
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
	private void openInternalFile(IFile file)
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

	private void openExternalFile(File file)
	{
		System.out.println("Open external file " + file);
		
		IEditorInput input = new ModelicaFileEditorInput(file);
		
		String editorId = getEditorId(file);
		
		IWorkbenchPage page = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		
		
		try
		{
			page.openEditor(input, editorId);
		}
		catch (PartInitException e)
		{
			MdtPlugin.log(e.getStatus());
		}
	}
	
	private String getEditorId(File file)
	{
		IWorkbench workbench= PlatformUI.getWorkbench(); 
		IEditorRegistry editorRegistry= workbench.getEditorRegistry();
		IEditorDescriptor descriptor= editorRegistry.getDefaultEditor(file.getName(), getContentType(file));
		if (descriptor != null)
			return descriptor.getId();
		return EditorsUI.DEFAULT_TEXT_EDITOR_ID;
	}
	
	private IContentType getContentType (File file)
	{
		if (file == null)
			return null;

		InputStream stream= null;
		try {
			stream= new FileInputStream(file);
			return Platform.getContentTypeManager().findContentTypeFor(stream, file.getName());
		} catch (IOException x) {
			MdtPlugin.log(x);
			return null;
		} finally {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException x) {
				MdtPlugin.log(x);
			}
		}
	}
}
