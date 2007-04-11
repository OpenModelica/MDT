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

package org.modelica.mdt.ui;

import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelica.mdt.core.IModelicaProject;
import org.modelica.mdt.core.IModelicaRoot;
import org.modelica.mdt.core.IParent;
import org.modelica.mdt.core.ModelicaCore;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.internal.core.CorePlugin;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.ui.ModelicaElementChangeListener;

/**
 * Content provider for a tree viewer. This content provider works only
 * whith TreeViewer:s to keep things simple. If you want to use it for
 * other viewers some addditional hacking is required on this class.
 * 
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se]
 * @author Elmir Jagudin
 */
public class ModelicaElementContentProvider extends ModelicaElementChangeListener implements ITreeContentProvider
{
	
	public ModelicaElementContentProvider()
	{
		super(false);
	}
	
	public Object[] getElements(Object inputElement)
	{
		if (inputElement instanceof IModelicaRoot)
		{
			return ((IModelicaRoot)inputElement).getProjects();
		}		
		if (inputElement instanceof IWorkspaceRoot)
		{
			return ((IWorkspaceRoot)inputElement).getProjects();
		}		
		
		ErrorManager.logBug(UIPlugin.getSymbolicName(),	"Elements of an object of unexpected type " + inputElement.getClass().getName() + " requested.");
		
		return new Object[] {};
	}
	
	public void dispose()
	{
	}

	public void inputChanged(Viewer fViewer, Object oldInput, Object newInput)
	{
		/* we are interested in changes to modelica elements tree */
		setViewer(fViewer);
		// TODO! see about oldInput!
		ModelicaCore.getModelicaRoot().addModelicaElementChangeListener(this);
	}

	public Object[] getChildren(Object parent)
	{
		if (parent instanceof IContainer)
		{
			try
			{
				IResource[] files = ((IContainer)parent).members();
				// sort so package.mo is the first!!!!
				int i = 0;
				for (i=0; i < files.length; i++)
				{
					if (files[i].getName().equals("package.mo")) break;
				}
				// we know here that package.mo has index i
				IResource[] sortedFiles	= null; 
				if (i > 0 && i < files.length)
				{
					sortedFiles = new IResource[files.length];
					sortedFiles[0] = files[i];
					int j = 1;
					while (j < files.length)
					{
						if (j != i) 
							sortedFiles[j] = files[j];
						else
							sortedFiles[j] = files[0];
						j++;
					}
				}
				else // no package.mo, don't bother! 
					sortedFiles = files;
				
				 return sortedFiles;
			}
			catch (CoreException e)
			{
				ErrorManager.logError(e);
			}
		}
		else if (parent instanceof IModelicaProject)
		{
			IModelicaProject modelicaProj = (IModelicaProject)parent;
			if ( !modelicaProj.getWrappedProject().isOpen())
			{
				/* we have no children if we are closed */
				return new Object[0];
			}

			Collection<?> list = null;
			boolean hasModelicaNature = false;
			try
			{
				list = modelicaProj.getRootFolder().getChildren();
				hasModelicaNature = modelicaProj.getWrappedProject().getDescription().hasNature(CorePlugin.MODELICA_NATURE);
			}
			catch (CompilerException e)
			{
				/* on OMC error, show message and return empty children array */
				ErrorManager.showCompilerError(e);
				return new Object[0];
			}
			catch (CoreException e)
			{
				/* on core error, show message and return empty children array */
				ErrorManager.showCoreError(e);
				return new Object[0];
			}

			if (!hasModelicaNature)
			{
				/* don't add system library to non modelica projects */
				return list.toArray();
			}
			
			/* TODO! FIXME! IF WE ALREADY HAVE MODELICA, DON'T ADD THE STANDARD LIBRARY! */
			/*
			 * add as last element system library
			 */			
			Object[] children = new Object[list.size()+1];
			list.toArray(children);
			children[children.length-1] = ModelicaCore.getModelicaRoot().getStandardLibrary((IModelicaElement)parent);	

			return children; 			
		}
		else if (parent instanceof IParent)
		{
			try
			{
				Collection<? extends IModelicaElement> childs = ((IParent)parent).getChildren();
				
				Object[] sortedFiles = new Object[childs.size()];
				IModelicaElement pkg = null;

				for (IModelicaElement e : childs)
				{
					if (e.getElementName().equals("package.mo")) pkg = e;
				}
				
				if (pkg != null)
				{
					sortedFiles[0] = pkg;
					int j = 1;
					for (IModelicaElement e : childs)
					{
						if (e.equals(pkg)) continue;
						else {
							sortedFiles[j] = e;
							j++;
						}
					}
				}
				else // no package.mo, don't bother! 
				{
					int j = 0;
					for (IModelicaElement e : childs)
					{
						sortedFiles[j] = e;
						j++;
					}
				}				
				
				return sortedFiles;
			}
			catch (CompilerException e)
			{
				/* on OMC error, show message and return empty children array */
				ErrorManager.showCompilerError(e);
				return new Object[0];
			}
			catch (CoreException e)
			{
				/* on core error, show message and return empty children array */
				ErrorManager.showCoreError(e);
				return new Object[0];
			}
		}
		return null;
	}

	public Object getParent(Object element)
	{
		return null;
	}

	public boolean hasChildren(Object element)
	{
		if (element instanceof IProject)
		{
			return ((IProject)element).isOpen();
		}
		else if (element instanceof IFolder)
		{
			return true;
		}
		else if (element instanceof IModelicaProject)
		{
			return ((IModelicaProject)element).getWrappedProject().isOpen();
		}
		else if (element instanceof IParent)
		{
			try 
			{
				return ((IParent)element).hasChildren();
			} 
			catch (CoreException e) 
			{
				/* 
				 * on Core error, show message and return that 
				 * there are no children 
				 */
				ErrorManager.showCoreError(e);
				return false;
			}
			catch (CompilerException e)
			{
				/* 
				 * on OMC error, show message and return that 
				 * there are no children 
				 */
				ErrorManager.showCompilerError(e);
				return false;
			}
		}
		return false;
	}
	
}
